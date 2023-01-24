package com.rocket.core.domain.usecase

import kotlinx.coroutines.flow.Flow

/**
 * Abstract class which represents a UserCase
 *
 * To manage a user case to perform an action related to the app or business logic
 */
abstract class UseCase<out Type, in Params> where Type : Any? {
    /**
     * To perform the action of the user case defined
     * @param params the parameters used in the function
     * @return of the data of type specified
     */
    abstract fun run(params: Params? = null): Type

    /**
     * Function to invocation the execution of the run function
     */
    @JvmOverloads
    operator fun invoke(
        params: Params? = null
    ): Type = run(params)
}

/**
 * Abstract class which represents a UserCase with a suspend function
 *
 * To manage a user case to perform an action related to the app or business logic
 */
abstract class UseCaseSuspend<out Type, in Params> where Type : Any? {
    /**
     * To perform the suspend action of the user case defined
     * @param params the parameters used in the function
     * @return of the data of type specified
     */
    abstract suspend fun run(params: Params? = null): Type

    /**
     * Function to invocation the execution of the run function
     */
    @JvmOverloads
    suspend operator fun invoke(
        params: Params? = null
    ): Type = run(params)
}

/**
 * Abstract class which represents a UserCase with a Flow function
 *
 * To manage a user case to perform an action related to the app or business logic
 */
abstract class UseCaseFlow<out Type, in Params> where Type : Any? {
    /**
     * To perform the action of the user case defined
     * @param params the parameters used in the function
     * @return of the Flow data of type specified
     */
    abstract fun run(params: Params? = null): Flow<Type>

    /**
     * Function to invocation the execution of the run function
     */
    @JvmOverloads
    operator fun invoke(
        params: Params? = null
    ): Flow<Type> = run(params)
}
