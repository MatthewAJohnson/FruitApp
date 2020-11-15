package com.example.fruitapp

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>
    val right  get() = (this as Right).b
    val left get() = (this as? Left)?.a

    fun either(fnL:(L) -> Any, fnR: (R) -> Any): Any =
        when(this){
            is Left -> fnL(a)
            is Right -> fnR(b)
        }

    fun <T> mapTo(f: (R) -> T):  Either<L,T> =
    when(this) {
        is Left -> this
        is Right -> Right(f(this.b))
    }
}
