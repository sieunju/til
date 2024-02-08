package com.hmju.core.ui.lifecycle

import android.app.Activity

/**
 * Description : onActivityResult 에 대한 값을 ViewModel 에서 받을수 있도록 처리하는
 * Annotation Class
 * ex.)
 * @OnActivityResult(RequestCode, ResultCode)
 * fun 함수명(data : Bundle?) {
 *   // Do Something..
 * }
 * Created by juhongmin on 2022/03/11
 */
@Deprecated("테스트 결과 별로 좋지 않는 방향이라 Deprecated 합니다.")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class OnActivityResult(val requestCode: Int, val resCode: Int = Activity.RESULT_CANCELED)
