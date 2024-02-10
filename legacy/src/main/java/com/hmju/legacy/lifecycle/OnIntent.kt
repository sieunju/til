package com.hmju.legacy.lifecycle

/**
 * Description : Activity or Fragment 에서
 * Lifecycle onCreate or onNewIntent Bundle 데이터를 받아서 처리
 * BaseViewModel 상속 받은 ViewModel 에서만 사용 가능
 * 함수 생성시 public, No Parameter
 *
 * Created by juhongmin on 2022/04/19
 */
@Deprecated("테스트 결과 별로 좋지 않는 방향이라 Deprecated 합니다.")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnIntent
