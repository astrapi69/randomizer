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
package de.alpharogroup.random.number

import de.alpharogroup.math.MathExtensions
import de.alpharogroup.random.DefaultSecureRandom
import de.alpharogroup.random.RandomCharacters
import de.alpharogroup.random.RandomExtensions
import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

/**
 * Utility class for producing random numbers.
 *
 * @version 1.1
 * @author Asterios Raptis
 */
class RandomNumberExtensions {
    companion object {

        /**
         * The Method getRandomBigDecimal(int,int) gets an random BigDecimal.
         *
         * @param afterComma
         * How many decimal places after the comma.
         * @param beforeComma
         * How many decimal places before the comma.
         * @return The produced BigDecimal.
         */
        fun getRandomBigDecimal(afterComma: Int, beforeComma: Int): BigDecimal {
            var randomFloatString: String
            do {
                randomFloatString = getRandomFloatString(afterComma, beforeComma)
            } while (randomFloatString == ".")
            return BigDecimal(randomFloatString)
        }

        /**
         * Gets the random float string.
         *
         * @param afterComma
         * How many decimal places after the comma.
         * @param beforeComma
         * How many decimal places before the comma.
         * @return the random float string
         */
        fun getRandomFloatString(afterComma: Int, beforeComma: Int): String {
            val nachkommastellen = getRandomNumericString(afterComma)
            val vorkommastellen = getRandomNumericString(beforeComma)
            return "$nachkommastellen.$vorkommastellen"
        }

        /**
         * Generates a random numeric string.
         *
         * @return the generated random numeric string.
         */
        val randomNumericString: String
            get() {
                val maxLength = Math.min(RandomPrimitivesExtensions.randomInt(1000), 1024)
                val sb = StringBuilder(maxLength)
                for (i in 0 until maxLength) {
                    val randomInt = RandomPrimitivesExtensions.randomInt()
                    if (MathExtensions.isNegative(randomInt)) {
                        sb.append(randomInt * -1)
                    } else {
                        sb.append(randomInt)
                    }
                }
                return sb.toString()
            }

        /**
         * The Method getRandomNumericString(int) produces a random Number to the specified length.
         *
         * @param length
         * The length from the random number.
         * @return The random number as String.
         */
        fun getRandomNumericString(length: Int): String {
            return RandomExtensions
                    .getRandomString(RandomCharacters.numbers.characters, length)
        }

        /**
         * Generates a random [BigInteger]
         *
         * @return the random [BigInteger]
         */
        fun randomBigInteger(): BigInteger {
            return BigInteger(RandomPrimitivesExtensions.randomInt(180), DefaultSecureRandom.get())
        }

        /**
         * Generates a random [BigDecimal]
         *
         * @return the random [BigDecimal]
         */
        fun randomBigDecimal(): BigDecimal {
            val bigDecimal = BigDecimal(RandomPrimitivesExtensions.randomDouble())
            bigDecimal.setScale(RandomPrimitivesExtensions.randomInt(2), RoundingMode.HALF_DOWN)
            return bigDecimal
        }
    }

}
