package com.hmju.legacy.lifecycle

/**
 * Description : onPermissions Result 에 대한 값을 ViewModel 에서 받을수 있도록 처리하는
 * Annotation Class
 * @param permissions 전달 받고 싶은 권한
 * Created by juhongmin on 2022/03/11
 */
@Deprecated("테스트 결과 별로 좋지 않는 방향이라 Deprecated 합니다.")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnPermissionResult(val permissions: Array<String>)
