package com.hmju.test.coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.createTestCoroutineScope
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis

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
        println("coroutineString Thread ${Thread.currentThread()}")
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
        println("ApiResponse Thread ${Thread.currentThread()}")
        delay(delay)
        return if (Random.nextBoolean()) {
            ApiTestResponse.Success("ApiTestResponse Success")
        } else {
            ApiTestResponse.Error(NullPointerException("NPR"))
        }
    }

    private fun fetchApiResponseV2(): ApiTestResponse<String> {
        println("fetchApiResponseV2 Thread ${Thread.currentThread()}")
        return if (Random.nextBoolean()) {
            ApiTestResponse.Success("ApiTestResponse Success")
        } else {
            ApiTestResponse.Error(NullPointerException("NPR"))
        }
    }

    private suspend fun toFlatmapString(value: Any): Flow<String> {
        return flow {
            when (value) {
                is Int -> {
                    delay(1000)
                    emit("Num $value")
                }
                is String -> {
                    emit(value)
                }
                else -> {
                    emit("Nothing")
                }
            }
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

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun 병렬로_ZIP_좋은_방식() {
        runTest {
            val coString = async { coroutineString(1000) }

        }
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
    fun API_에러_테스트() {
        runBlocking {
            val time = measureTimeMillis {
                val flow1 = async { fetchApiResponse(300) }
                val flow2 = async { fetchApiResponse(1300) }
                println("Flow1 ${flow1.await()} Flow2 ${flow2.await()}")
            }
            println("걸린 시간 $time")
        }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun FLOW_flatmapConcat() {
        runBlocking {
            val time = measureTimeMillis {
                val flow = flow<Any> {
                    emit(1)
                    emit(2)
                    emit("A")
                    emit("B")
                }
                // flatMapConcat 은 위에 emit 순서를 보장해서 방출하는 연산자
                // Rx concatMeager? 이거였나? 그거랑 비슷함
                flow.flatMapConcat { toFlatmapString(it) }
                    .collect {
                        println("Flatmap $it")
                    }
            }
            println("걸린 시간 $time")
        }
    }

    @OptIn(FlowPreview::class)
    @Test
    fun FLOW_FlatmaMerge() {
        runBlocking {
            val time = measureTimeMillis {
                val flow = flow<Any> {
                    emit(1)
                    emit(2)
                    emit("A")
                    emit("B")
                }
                // flatMapMerge은 순서 보장 X 먼저 방출되는 사람이 임자
                flow.flatMapMerge { toFlatmapString(it) }
                    .collect {
                        println("Flatmap $it")
                    }
            }
            println("걸린 시간 $time")
        }
    }

    @Test
    fun FLOW_MERGE() {
        runBlocking {
            val time = measureTimeMillis {
                coroutineScope {
                    // 흠...merge 가 약간 코루틴에선 의미가 없는 듯하다.
                    merge(
                        flowOf(fetchApiResponse(333)),
                        flowOf(coroutineString(333)),
                        flowOf(coroutineInt(3333))
                    ).flowOn(Dispatchers.Default)
                        .onEach { println("RECV ${it}") }
                        .collect()
                }
            }
            println("걸린 시간 $time")
        }
    }

    @Test
    fun MERGE_2(){
        val time = measureTimeMillis {
            runBlocking {
                coroutineScope {
                    // 좀더 머지같은 방식
                    // fetchApiResponse 함수를 실행하면 로그는 찍히는데 시간은 5초가 걸리는게 신기함...
                    // 이게바로 동시성인가..?
                    val work1 = async(Dispatchers.IO) { fetchApiResponse(4000) }
                    val work2 = async(Dispatchers.IO) { coroutineString(3333) }
                    val work3 = async(Dispatchers.IO) { coroutineInt(5000) }
                    val work4 = async(Dispatchers.IO) { coroutineInt(3000) }

                    delay(1000)
                    println("Start!!!!")
                    println("Work1 ${work1.await()}")
                    println("Work2 ${work2.await()}")
                    println("Work3 ${work3.await()}")
                    println("Work3 ${work4.await()}")
                }
            }

        }
        println("걸린 시간 $time")
    }

    @Test
    fun 잘못된_비동기방식_2(){
        val time = measureTimeMillis {
            runBlocking {
                coroutineScope {
                    // 여기서 바로 await 처리하면 순차적으로 처리함
                    // 즉, 별령로 어떤 처리를 할때에는 아래 방식으로 처리하지 말고 병렬로 처리할 작업들을 위에 "선언"만 하고
                    // 그것들을 마지막에 await 하면 병렬로 처리됨
                    val work1 = withContext(Dispatchers.IO) { fetchApiResponse(4000) }
                    val work2 = withContext(Dispatchers.IO) { coroutineString(3333) }
                    val work3 = withContext(Dispatchers.IO) { coroutineInt(5000) }
                    val work4 = withContext(Dispatchers.IO) { coroutineInt(3000) }

                    delay(1000)
                    println("Start!!!!")
                    println("Work1 $work1")
                    println("Work2 $work2")
                    println("Work3 $work3")
                    println("Work3 $work4")
                }
            }

        }
        println("걸린 시간 $time")
    }
}
