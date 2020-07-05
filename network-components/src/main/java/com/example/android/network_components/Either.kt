package com.example.android.network_components

sealed class Either<out F, out S> {

    /** * Represents the left side of [Either] class which by convention is a "Failure". */
    data class Failure<out L>(val a: L) : Either<L, Nothing>()

    /** * Represents the right side of [Either] class which by convention is a "Success". */
    data class Success<out S>(val b: S) : Either<Nothing, S>()

    val isRight get() = this is Success<S>
    val isLeft get() = this is Failure<F>

    fun <L> left(a: L) = Failure(a)
    fun <S> right(b: S) = Success(b)

    fun either(fnL: (F) -> Any, fnR: (S) -> Any): Any =
        when (this) {
            is Failure -> fnL(a)
            is Success -> fnR(b)
        }

    private fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
        f(this(it))
    }

    fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
        when (this) {
            is Failure -> Failure(a)
            is Success -> fn(b)
        }

    fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))
}