package com.rocket.core.domain.di

@Suppress("unused")
open class CoreProvider {
    object CoreProviderProperty {
        const val PRINT_LOGS = "PRINT_LOGS"
    }

    inline fun <reified T> getPropertyOrNull(key: String): T? {
        return try {
            val value = properties?.getValue(key)
            when (T::class) {
                String::class -> value ?: ""
                Int::class -> value?.toInt()
                Long::class -> value?.toLong()
                Boolean::class -> value?.toBoolean()
                else -> null
            } as T?
        } catch (_: Exception) {
            null
        }
    }

    companion object CoreProperties {
        var properties: Map<String, String>? = null
    }
}
