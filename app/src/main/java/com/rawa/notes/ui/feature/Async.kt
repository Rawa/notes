package com.rawa.notes.ui.feature

// MvRx inspired Async class for loading and handling of Uninitialized states
sealed class Async<out T>(private val value: T?) {
    open operator fun invoke(): T? = value
}

object Uninitialized : Async<Nothing>(value = null)

data class Loading<out T>(private val value: T? = null) :
    Async<T>(value = value)

data class Success<out T>(private val value: T) :
    Async<T>(value = value) {
    override operator fun invoke(): T = value
}

data class Fail<out T>(val error: Throwable, private val value: T? = null) :
    Async<T>(value = value)
