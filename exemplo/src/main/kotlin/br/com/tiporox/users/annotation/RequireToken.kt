package br.com.tiporox.users.annotation

import br.com.tiporox.users.interceptor.TokenInterceptor
import io.micronaut.aop.Around
import io.micronaut.context.annotation.Type
import java.lang.annotation.Documented
import java.lang.annotation.Retention

import java.lang.annotation.RetentionPolicy.RUNTIME

@Documented
@Retention(RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Around
@Type(TokenInterceptor::class)
annotation class RequireToken {
}