package com.hmju.core.network.qualifiers

import javax.inject.Qualifier


@Qualifier
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class ApiHeaderInterceptor
