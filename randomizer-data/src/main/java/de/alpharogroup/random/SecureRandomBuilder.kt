/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.random

import java.security.SecureRandom

/**
 * The class [SecureRandomBuilder] builds a [SecureRandom] from the given algorithm and
 * provider. If nothing is set the default [SecureRandom] object with the default algorithm
 * will be build.
 */
class SecureRandomBuilder
/**
 * Instantiates a new [SecureRandomBuilder]
 */
private constructor() {

    /** The algorithm.  */
    private var algorithm: String? = null

    /** The provider.  */
    private var provider: String? = null

    /**
     * Sets the algorithm.
     *
     * @param algorithm
     * the algorithm
     * @return this [SecureRandomBuilder] object. For chaining.
     */
    fun algorithm(algorithm: String): SecureRandomBuilder {
        this.algorithm = algorithm
        return this
    }

    /**
     * Builds a [SecureRandom] from the given algorithm and provider. If nothing is set the
     * default [SecureRandom] object with the default algorithm will be build.
     *
     * @return the new [SecureRandom] object
     */
    fun build(): SecureRandom {
        return RandomFactory.newSecureRandom(algorithm, provider)
    }

    /**
     * Sets the provider.
     *
     * @param provider
     * the provider
     * @return this [SecureRandomBuilder] object. For chaining.
     */
    fun provider(provider: String): SecureRandomBuilder {
        this.provider = provider
        return this
    }

    companion object {

        /** The Constant DEFAULT_ALGORITHM  */
        val DEFAULT_ALGORITHM = "SHA1PRNG"

        /**
         * Gets an instance of [SecureRandomBuilder] with the default algorithm and provider
         *
         * @return the new [SecureRandomBuilder] object
         */
        val instance: SecureRandomBuilder
            get() = SecureRandomBuilder.newInstance()

        /**
         * Gets an instance of [SecureRandomBuilder] from the given algorithm and provider
         *
         * @param algorithm
         * the algorithm
         * @return the new [SecureRandomBuilder] object
         */
        fun getInstance(algorithm: String): SecureRandomBuilder {
            return SecureRandomBuilder.newInstance().algorithm(algorithm)
        }

        /**
         * Gets an instance of [SecureRandomBuilder] from the given algorithm and provider
         *
         * @param algorithm
         * the algorithm
         * @param provider
         * the provider
         * @return the new [SecureRandomBuilder] object
         */
        fun getInstance(algorithm: String, provider: String): SecureRandomBuilder {
            return SecureRandomBuilder.newInstance().algorithm(algorithm).provider(provider)
        }

        /**
         * Gets an new instance of [SecureRandomBuilder] for build a [SecureRandom] object
         *
         * @return the [SecureRandomBuilder]
         */
        private fun newInstance(): SecureRandomBuilder {
            return SecureRandomBuilder()
        }
    }
}
