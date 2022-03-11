package com.hmju.lifecycle

/**
 * Description : onPermissions Result 에 대한 값을 ViewModel 에서 받을수 있도록 처리하는
 * Annotation Class
 *
 * Created by juhongmin on 2022/03/11
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnPermissionResult(val code : Int = -1)
