package com.hmju.legacy.lifecycle

import android.app.Activity

/**
 * Description : Fragment 에서 Activity Result 받을수 있는
 * Annotation Class
 * ex.)
 * @OnFragmentResult(RequestCode, ResultCode)
 * fun 함수명(data : Bundle?) {
 *   // Do Something..
 * }
 *
 * Created by juhongmin on 12/30/23
 */
@Suppress("unused")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnFragmentResult(val requestCode: Int, val resCode: Int = Activity.RESULT_CANCELED)
