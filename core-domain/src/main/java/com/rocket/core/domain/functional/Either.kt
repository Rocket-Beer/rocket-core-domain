package com.rocket.core.domain.functional

sealed class Either<out L, out R> {
    abstract fun isLeft(): Boolean
    abstract fun isRight(): Boolean

    inline fun <C> fold(ifLeft: (L) -> C, ifRight: (R) -> C): C = when (this) {
        is Left -> ifLeft(l)
        is Right -> ifRight(r)
    }

    fun swap(): Either<R, L> = fold({ Right(it) }, { Left(it) })

    fun <C> map(f: (R) -> C): Either<L, C> = flatMap { Right(f(it)) }

    fun <C> flatMap(f: (R) -> Right<C>): Either<L, C> = when (this) {
        is Left -> this
        is Right -> f(r)
    }

    class Left<out L> constructor(val l: L) : Either<L, Nothing>() {
        override fun isLeft(): Boolean = true
        override fun isRight(): Boolean = false
    }

    class Right<out R> constructor(val r: R) : Either<Nothing, R>() {
        override fun isLeft(): Boolean = false
        override fun isRight(): Boolean = true
    }
}