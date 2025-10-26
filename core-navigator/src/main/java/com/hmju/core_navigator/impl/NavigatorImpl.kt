package com.hmju.core_navigator.impl

import com.hmju.core_navigator.Navigator
import javax.inject.Inject

/**
 * Description :
 *
 * Created by juhongmin on 2025. 10. 20.
 */
internal class NavigatorImpl @Inject constructor(
 private val navigatorMap: Map<String, @JvmSuppressWildcards Navigator>
) {
  public
}