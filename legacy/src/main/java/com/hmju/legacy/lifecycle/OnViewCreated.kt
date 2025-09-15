package com.hmju.legacy.lifecycle

/**
 * Description : Fragment
 * onViewCreated 상태일때 호출
 * BaseViewModel 상속 받은 ViewModel 에서만 사용 가능
 * 함수 생성시 public, No Parameter
 *
 * Created by juhongmin on 2022/03/09
 */
@Suppress("unused")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnViewCreated
