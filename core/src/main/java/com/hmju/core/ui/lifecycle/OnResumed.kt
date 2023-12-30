package com.hmju.core.ui.lifecycle

/**
 * Description : Activity or Fragment
 * onStop -> onResume 호출
 * onCreate -> onResume 인 경우에는 호출 X
 * BaseViewModel 상속 받은 ViewModel 에서만 사용 가능
 * 함수 생성시 public, No Parameter
 * Created by juhongmin on 2022/03/01
 */
@Deprecated("테스트 결과 별로 좋지 않는 방향이라 Deprecated 합니다.")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnResumed
