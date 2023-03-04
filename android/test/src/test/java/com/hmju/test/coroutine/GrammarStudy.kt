package com.hmju.test.coroutine

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.junit.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

/**
 * Description : 코루틴 문법 테스트
 *
 * Created by juhongmin on 2023/03/03
 */
class GrammarStudy {

    sealed class ApiTestResponse<out T> {
        data class Success<out T>(val data: T) : ApiTestResponse<T>()
        data class Error(val ex: Throwable) : ApiTestResponse<Nothing>()
    }

    private suspend fun coroutineString(delay: Long): String {
        delay(delay)
        return "coroutineString ${delay}MS"
    }

    private suspend fun coroutineInt(delay: Long): Int {
        delay(delay)
        return Random.nextInt()
    }

    private suspend fun coroutineDouble(delay: Long): Double {
        delay(delay)
        return Random.nextDouble()
    }

    private suspend fun coroutineErrorString(delay: Long): String {
        delay(delay)
        throw NullPointerException("NPR")
        // return "coroutineString ${delay}MS"
    }

    private suspend fun fetchApiResponse(delay: Long): ApiTestResponse<String> {
        delay(delay)
        return if (Random.nextBoolean()) {
            ApiTestResponse.Success("ApiTestResponse Success")
        } else {
            ApiTestResponse.Error(NullPointerException("NPR"))
        }
    }

    @Test
    fun flowNormalTest() {
        runBlocking {
            val flow = (1..10).asFlow()
                .onEach {
                    delay(1000)
                    println("${it}_FlowNormal")
                }
            println("End FlowNormal Test")
            // Rx subscribe 로 생각하면됨
            flow.collect()
        }
    }


    @Test
    fun flowCombineTest() {
        runBlocking {
            // 콤바인 연산자의 경우 방출하는 값이 아래와 같다면 방출이 다끝난 값들은 최신 값들을 계속해서 방출한다.
            // Example
            //ZIP 1_100
            //ZIP 2_101
            //ZIP 3_102
            //ZIP 4_103
            //ZIP 5_104
            //ZIP 5_105
            //ZIP 5_106
            //ZIP 5_107
            //ZIP 5_108
            //ZIP 5_109
            //ZIP 5_110
            val flow1 = (1..5).asFlow()
            val flow2 = (100..110).asFlow()
            combine(flow1, flow2)
            { first, second ->
                first to second
            }.onEach { println("ZIP ${it.first}_${it.second}") }.collect()
        }
    }

    @Test
    fun flowZipTest() {
        runBlocking {
            val flow1 = (1..10).asFlow()
            val flow2 = (100..120).asFlow()
            flow1.zip(flow2) { first, second ->
                first to second
            }.onEach { println("ZIP ${it.first}_${it.second}") }.collect()
        }
    }

    @Test
    fun 병렬로_ZIP_좋은_방식() {
        runBlocking {
            val time = measureTimeMillis {
                val model = withContext(Dispatchers.IO) {
                    val coString = async { coroutineString(1000) }
                    val coString2 = async { coroutineString(1000) }
                    val coString3 = async { coroutineString(1000) }
                    val coString4 = async { coroutineString(1000) }
                    val coInt = async { coroutineInt(200) }
                    val str = StringBuilder()
                    str.append(coString.await())
                    str.append(coString2.await())
                    str.append(coString3.await())
                    str.append(coString4.await())
                    str.append(coInt.await())

                    str.toString()
                }
                println("SUCC $model")
            }
            // 1000MS
            println("걸린 시간 $time")
        }
    }

    @Test
    fun ZIP_안좋은_방식() {
        runBlocking {
            val time = measureTimeMillis {
                val model = withContext(Dispatchers.IO) {
                    val coString = coroutineString(1000)
                    val coString2 = coroutineString(1000)
                    val coString3 = coroutineString(1000)
                    val coString4 = coroutineString(1000)
                    val coInt = coroutineInt(200)
                    val str = StringBuilder()
                    str.append(coString)
                    str.append(coString2)
                    str.append(coString3)
                    str.append(coString4)
                    str.append(coInt)

                    str.toString()
                }
                println("SUCC $model")
            }
            // 4200MS 이상
            println("걸린 시간 $time")
        }
    }

    @Test
    fun 에러_테스트() {
        runBlocking {
            val time = measureTimeMillis {
                withContext(Dispatchers.IO) { coroutineErrorString(33) }
            }
            // Error 에 대응을 안하면 에러 발생
            println("걸린 시간 $time")
        }
    }

    @Test
    fun API_에러_테스트(){
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = async { fetchApiResponse(300) }
                val flow2 = async { fetchApiResponse(1300) }
                println("Flow1 ${flow1.await()} Flow2 ${flow2.await()}")
            }
            println("걸린 시간 $time")
        }
    }
}
