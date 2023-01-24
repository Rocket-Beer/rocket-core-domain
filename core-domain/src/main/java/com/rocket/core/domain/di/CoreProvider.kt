package com.rocket.core.domain.di

/**
 * Class which represents a provider
 *
 * Contain all the properties of the provider
 *
 * @property CoreProviderProperty object with provider property
 * @property CoreProperties object to store all the properties
 */
@Suppress("unused")
open class CoreProvider {
    object CoreProviderProperty {
        const val PRINT_LOGS = "PRINT_LOGS"
    }

    /**
     * Get the different properties of the provider
     * @param key the property to obtain
     * @return the specified property. Might be null if there's an error
     */
    inline fun <reified T> getPropertyOrNull(key: String): T? {
        return try {
            val value = properties?.getValue(key)
            when (T::class) {
                String::class -> value ?: ""
                Int::class -> value as? Int
                Long::class -> value as? Long
                Boolean::class -> value as? Boolean
                else -> null
            } as T?
        } catch (_: Exception) {
            null
        }
    }

    companion object CoreProperties {
        var properties: Map<String, Any>? = null
    }
}
