package com.effective.utils.collections

fun<T, R: Any> Iterable<T>.saveMapNotNull(transform: (T) -> R?): List<R> {
    return mapNotNull {
        try { transform(it) } catch (_ : IllegalArgumentException) { null }
    }
}