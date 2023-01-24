package com.rocket.core.domain.functional

/**
 * Sealed class which represents a Either with Left or Right value
 *
 * To manage the success or error response of a UseCase
 *
 * @property Left data class of Left side
 * @property Right data class of Right side
 */
sealed class Either<out L, out R> {
    /**
     * Get true if is in Left side
     * @return boolean that represents if is in Left side
     */
    abstract fun isLeft(): Boolean
    /**
     * Get true if is in Right side
     * @return boolean that represents if is in Right side
     */
    abstract fun isRight(): Boolean

    /**
     * Manage the response in the Left and Right side
     * @param ifLeft response to the Left side
     * @param ifRight response to the Right side
     */
    inline fun <C> fold(ifLeft: (L) -> C, ifRight: (R) -> C): C = when (this) {
        is Left -> ifLeft(l)
        is Right -> ifRight(r)
    }

    /**
     * To swap the response between Left and Right side using fold function
     */
    fun swap(): Either<R, L> = fold({ Right(it) }, { Left(it) })

    /**
     * To map the response in the Right side
     * @param f response
     */
    fun <C> map(f: (R) -> C): Either<L, C> = fold({ Left(it) }, { Right(f(it)) })

    /**
     * To map the response in the Left side
     * @param f response
     */
    fun <C> mapLeft(f: (L) -> C): Either<C, R> = fold({ Left(f(it)) }, { Right(it) })

    /**
     * Data class which represents the Left side of the Either
     *
     * To manage the response in the Left side
     *
     * @property l of the type indicated on the Left
     */
    data class Left<out L> constructor(val l: L) : Either<L, Nothing>() {
        override fun isLeft(): Boolean = true
        override fun isRight(): Boolean = false
    }

    /**
     * Data class which represents the Right side of the Either
     *
     * To manage the response in th Right side
     *
     * @property r of the type indicated on the Right
     */
    data class Right<out R> constructor(val r: R) : Either<Nothing, R>() {
        override fun isLeft(): Boolean = false
        override fun isRight(): Boolean = true
    }
}
