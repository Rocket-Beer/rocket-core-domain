package com.rocket.core.domain.error

/**
 * Sealed class which represents a Failure
 *
 * To manage the different errors in the app
 *
 * @param data the data stored as a failure
 * @property GenericFailure class which represents a generic failure
 * @property FeatureFailure class which represents a failure of a feature
 */
sealed class Failure(val data: Any? = null) {
    /**
     * Class of type Failure which represents a generic failure
     *
     * To manage a generic error in the app
     *
     * @param code the associated code of the error
     * @param data with the data stored as a failure
     */
    class GenericFailure(val code: String? = "0", data: Any? = null) : Failure(data)

    /**
     * Class of type Failure which represents failure of a feature
     *
     * To manage an error in a feature of the app
     *
     * @param data the data stored as a failure
     */
    abstract class FeatureFailure(data: Any? = null) : Failure(data)
}
