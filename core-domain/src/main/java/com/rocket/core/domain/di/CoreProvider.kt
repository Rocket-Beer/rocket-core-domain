package com.rocket.core.domain.di

@Suppress("unused")
open class CoreProvider {
    companion object CoreProperties {
        var properties: Map<String, String>? = null
    }

    @Suppress("SwallowedException")
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
        } catch (exception: NoSuchElementException) {
            null
        } catch (exception: NumberFormatException) {
            null
        } catch (exception: IllegalArgumentException) {
            null
        }
    }

    object CoreProviderProperty {
        const val PRINT_LOGS = "PRINT_LOGS"
    }
}
