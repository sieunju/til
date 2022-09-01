package com.hmju.network.qualifiers

import javax.inject.Qualifier


@Qualifier
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class HeaderJsonInterceptor
