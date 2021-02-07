package br.com.tiporox.users.interceptor

import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import java.util.*
import javax.inject.Singleton

@Singleton
class TokenInterceptor : MethodInterceptor<Any, Any> {

    override fun intercept(context: MethodInvocationContext<Any, Any>): Any {

        val nullParam = context.methodName
        println("TokenInterceptor:" + context.methodName)
        return context.proceed()
    }

}