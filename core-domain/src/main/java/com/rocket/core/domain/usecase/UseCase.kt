package com.rocket.core.domain.usecase

abstract class UseCase<out Type, in Params> where Type : Any? {
    abstract fun run(params: Params? = null): Type

    @JvmOverloads
    operator fun invoke(
        params: Params? = null
    ): Type = run(params)
}

abstract class UseCaseSuspend<out Type, in Params> where Type : Any? {
    abstract suspend fun run(params: Params? = null): Type

    @JvmOverloads
    suspend operator fun invoke(
        params: Params? = null
    ): Type = run(params)
}

abstract class UseCaseFlow<out Type, in Params> where Type : Any? {
    abstract fun run(params: Params? = null): Flow<Type>

    @JvmOverloads
    operator fun invoke(
        params: Params? = null
    ): Flow<Type> = run(params)
}
