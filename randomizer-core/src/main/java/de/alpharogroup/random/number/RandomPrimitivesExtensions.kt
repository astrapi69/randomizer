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
import de.alpharogroup.random.enums.RandomAlgorithm
import java.text.DecimalFormat
import java.text.ParseException
import java.util.*

/**
 * Utility class for producing random privimitive types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
class RandomPrimitivesExtensions {
    companion object {

        /**
         * Returns a random boolean.
         *
         * @return The random boolean.
         */
        fun randomBoolean(): Boolean {
            return randomInt(2) == 0
        }

        /**
         * The Method randomByte() selects a random byte.
         *
         * @return The random byte.
         */
        fun randomByte(): Byte {
            return randomInt(255).toByte()
        }

        /**
         * The Method randomByteArray(int) generates a random byte array.
         *
         * @param length
         * the length.
         * @return the byte[]
         */
        fun randomByteArray(length: Int): ByteArray {
            val randomByteArray = ByteArray(length)
            val randomByteBox = ByteArray(1)
            for (i in 0 until length) {
                if (RandomPrimitivesExtensions.randomBoolean()) {
                    randomByteArray[i] = RandomPrimitivesExtensions.randomByte()
                } else {
                    DefaultSecureRandom.get().nextBytes(randomByteBox)
                    randomByteArray[i] = randomByteBox[0]
                }
            }
            return randomByteArray
        }

        /**
         * Returns a random char.
         *
         * @return The generated random char.
         */
        fun randomChar(): Char {
            return if (DefaultSecureRandom.get().nextBoolean()) {
                // random character
                (DefaultSecureRandom.get().nextInt(26) + 65).toChar()
            } else {
                // random digit
                DefaultSecureRandom.get().nextInt(10).toChar()
            }
        }

        /**
         * Returns a random short
         *
         * @return The generated random short
         */
        fun randomShort(): Short {
            return if (DefaultSecureRandom.get().nextBoolean()) {
                (DefaultSecureRandom.get().nextInt(65536) - 32768).toShort()
            } else {
                DefaultSecureRandom.get().nextInt(java.lang.Short.MAX_VALUE + 1).toShort()
            }
        }

        /**
         * The Method randomChar(String) selects a random char from the given String.
         *
         * @param string
         * The String from who to select the char.
         * @return The selected char.
         */
        fun randomChar(string: String): Char {
            return string[randomInt(string.length)]
        }

        /**
         * Gets an random long to the given range with the given random algorithm <br></br>
         * <br></br>
         * For example: if you put range to 10 the random int is between 0-9
         *
         * @param range
         * the range
         * @param algorithm
         * the random algorithm
         * @return an random long not greater then the range
         */
        @JvmOverloads
        fun randomLong(range: Long = Random(System.currentTimeMillis()).nextInt().toLong(), algorithm: RandomAlgorithm = RandomAlgorithm.SECURE_RANDOM): Long {
            when (algorithm) {
                RandomAlgorithm.MATH_ABS -> return (Math.abs(DefaultSecureRandom.get().nextDouble()) % range).toLong()
                RandomAlgorithm.MATH_RANDOM -> return (Math.random() * range).toLong()
                RandomAlgorithm.RANDOM -> {
                    val random = Random(System.currentTimeMillis()).nextDouble().toLong() % range
                    return if (MathExtensions.isPositive(random)) random else random * -1
                }
                RandomAlgorithm.SECURE_RANDOM -> return (DefaultSecureRandom.get().nextDouble() * range).toLong()
                else -> return (DefaultSecureRandom.get().nextDouble() * range).toLong()
            }
        }

        /**
         * Returns a random long between the range from start and end.
         *
         * @param start
         * The long from where the range starts.
         * @param end
         * The long from where the range ends.
         * @return A random long between the range from start and end.
         */
        fun randomLongBetween(start: Long, end: Long): Long {
            return start + randomLong(end - start)
        }

        /**
         * Gets an random double to the given range with the given random algorithm <br></br>
         * <br></br>
         * For example: if you put range to 10 the random float is between 0.0-9.9
         *
         * @param range
         * the range
         * @param algorithm
         * the random algorithm
         * @return an random double not greater then the range
         */
        @JvmOverloads
        fun randomDouble(range: Double = java.lang.Double.MAX_VALUE, algorithm: RandomAlgorithm = RandomAlgorithm.SECURE_RANDOM): Double {
            when (algorithm) {
                RandomAlgorithm.MATH_ABS -> return Math.abs(DefaultSecureRandom.get().nextDouble()) % range
                RandomAlgorithm.MATH_RANDOM -> return Math.random() * range
                RandomAlgorithm.RANDOM -> {
                    val random = Random(System.currentTimeMillis()).nextDouble() % range
                    return if (MathExtensions.isPositive(random)) random else random * -1
                }
                RandomAlgorithm.SECURE_RANDOM -> return DefaultSecureRandom.get().nextDouble() * range
                else -> return DefaultSecureRandom.get().nextDouble() * range
            }
        }

        /**
         * Gets the random double between the range from start and end.
         *
         * @param start
         * the start
         * @param end
         * the end
         * @return the random double between
         */
        fun randomDoubleBetween(start: Double, end: Double): Double {
            return start + randomDouble(end - start)
        }

        /**
         * Gets the random double between the range from start and end in the given pattern. Refer to
         * class @see [java.text.DecimalFormat].
         *
         * @param start
         * the start
         * @param end
         * the end
         * @param pattern
         * the pattern
         * @return the random double between
         * @throws ParseException
         * is thrown if the beginning of the specified string cannot be parsed
         */
        @Throws(ParseException::class)
        fun randomDoubleBetween(start: Double, end: Double,
                                pattern: String): Double {
            val formatter = DecimalFormat(pattern)
            val rd = formatter.format(randomDoubleBetween(start, end))
            val randomDouble = formatter.parse(rd)
            return randomDouble.toDouble()
        }


        /**
         * Gets an random float to the given range with the given random algorithm <br></br>
         * <br></br>
         * For example: if you put range to 10 the random float is between 0.0-9.9
         *
         * @param range
         * the range
         * @param algorithm
         * the random algorithm
         * @return an random float not greater then the range
         */
        @JvmOverloads
        fun randomFloat(range: Float = DefaultSecureRandom.get().nextFloat(), algorithm: RandomAlgorithm = RandomAlgorithm.SECURE_RANDOM): Float {
            when (algorithm) {
                RandomAlgorithm.MATH_ABS -> return (Math.abs(DefaultSecureRandom.get().nextDouble()) % range).toFloat()
                RandomAlgorithm.MATH_RANDOM -> return (Math.random() * range).toFloat()
                RandomAlgorithm.RANDOM -> {
                    val random = Random(System.currentTimeMillis()).nextDouble().toFloat() % range
                    return if (MathExtensions.isPositive(random)) random else random * -1
                }
                RandomAlgorithm.SECURE_RANDOM -> return (DefaultSecureRandom.get().nextDouble() * range).toFloat()
                else -> return (DefaultSecureRandom.get().nextDouble() * range).toFloat()
            }
        }

        /**
         * The Method getRandomFloat(int,int) gets an random float.
         *
         * @param afterComma
         * How many decimal places after the comma.
         * @param beforeComma
         * How many decimal places before the comma.
         * @return The produced float.
         */
        fun randomFloat(afterComma: Int, beforeComma: Int): Float {
            return java.lang.Float.parseFloat(getRandomFloatString(afterComma, beforeComma))
        }

        /**
         * Gets the random float as [String] object.
         *
         * @param afterComma
         * How many decimal places after the comma.
         * @param beforeComma
         * How many decimal places before the comma.
         * @return the random float string
         */
        private fun getRandomFloatString(afterComma: Int, beforeComma: Int): String {
            return (RandomNumberExtensions.getRandomNumericString(afterComma) + "."
                    + RandomNumberExtensions.getRandomNumericString(beforeComma))
        }

        /**
         * Gets the random float between the range from start and end.
         *
         * @param start
         * the start
         * @param end
         * the end
         * @return the random float between
         */
        fun randomFloatBetween(start: Float, end: Float): Float {
            return start + randomFloat(end - start)
        }

        /**
         * Gets the random float between the range from start and end in the given pattern. Refer to
         * class @see [java.text.DecimalFormat].
         *
         * @param start
         * the start
         * @param end
         * the end
         * @param pattern
         * the pattern
         * @return the random float between
         * @throws ParseException
         * is thrown if the beginning of the specified string cannot be parsed
         */
        @Throws(ParseException::class)
        fun randomFloatBetween(start: Float, end: Float, pattern: String): Float {
            val formatter = DecimalFormat(pattern)
            val rf = formatter.format(randomFloatBetween(start, end).toDouble())
            val randomFloat = formatter.parse(rf)
            return randomFloat.toFloat()
        }

        /**
         * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
         * to 10 the random int is between 0-9.
         *
         * @param range
         * The range
         * @param algorithm
         * the random algorithm
         * @return an int not greater then the range
         */
        @JvmOverloads
        fun randomInt(range: Int = DefaultSecureRandom.get().nextInt(), algorithm: RandomAlgorithm = RandomAlgorithm.SECURE_RANDOM): Int {
            when (algorithm) {
                RandomAlgorithm.MATH_ABS -> return Math.abs(DefaultSecureRandom.get().nextInt()) % range
                RandomAlgorithm.MATH_RANDOM -> return (Math.random() * range).toInt()
                RandomAlgorithm.RANDOM -> {
                    val random = Random(System.currentTimeMillis()).nextInt() % range
                    return if (MathExtensions.isPositive(random)) random else random * -1
                }
                RandomAlgorithm.SECURE_RANDOM -> return (DefaultSecureRandom.get().nextDouble() * range).toInt()
                else -> return (DefaultSecureRandom.get().nextDouble() * range).toInt()
            }
        }

        /**
         * Returns a random int between the range from start and end.
         *
         * @param start
         * The int from where the range starts.
         * @param end
         * The int from where the range ends.
         * @return A random int between the range from start and end.
         */
        fun randomIntBetween(start: Int, end: Int): Int {
            return RandomPrimitivesExtensions.randomIntBetween(start, end, true, false)
        }

        /**
         * Returns a random int between the range from start and end.
         *
         * @param start
         * The int from where the range starts.
         * @param end
         * The int from where the range ends.
         * @param includeMin
         * if true than min value is included
         * @param includeMax
         * if true than max value is included
         * @return A random int between the range from start and end.
         */
        fun randomIntBetween(start: Int, end: Int, includeMin: Boolean,
                             includeMax: Boolean): Int {
            var randomIntBetween = start + randomInt(end - start)

            if (includeMin && includeMax) {
                randomIntBetween = start + randomInt(end - (start + 1))
            }
            if (includeMin && !includeMax) {
                randomIntBetween = start + randomInt(end - start)
            }
            if (!includeMin && includeMax) {
                randomIntBetween = start + 1 + randomInt(end - (start + 1))
            }
            if (!includeMin && !includeMax) {
                randomIntBetween = start + 1 + randomInt(end - start)
            }
            return randomIntBetween
        }

        /**
         * Returns a random int between the range from minVolume and maxVolume with the
         * `Math.abs` method.
         *
         * @param minVolume
         * the min volume
         * @param maxVolume
         * the max volume
         * @return A random int between the range from minVolume and maxVolume
         */
        fun getRandomIntBetween(minVolume: Int, maxVolume: Int): Int {
            return minVolume + Math.abs(DefaultSecureRandom.get().nextInt()) % maxVolume
        }
    }

}
/**
 * Gets a random long
 *
 * @return a random long
 */
/**
 * The Method randomLong(long) gets an long to the spezified range. For example: if you put
 * range to 10 the random int is between 0-9.
 *
 * @param range
 * the range
 * @return an long not greater then the range.
 */
/**
 * Generates a random float between the range 0.0-9.9.
 *
 * @return the generated random float between the range 0.0-9.9.
 */
/**
 * The Method randomDouble(double) gets an double to the spezified range. For example: if you
 * put range to 10.0 the random int is between 0.0-9.9.
 *
 * @param range
 * the range
 * @return the double
 */
/**
 * The Method randomDouble() gets a random double
 *
 * @return the random double
 */
/**
 * The Method randomFloat(float) gets an float to the spezified range. For example: if you put
 * range to 10.0 the random int is between 0.0-9.9.
 *
 * @param range
 * the range
 * @return the float
 */
/**
 * The Method randomInt() gets an int between the range 0-9.
 *
 * @return an int between the range 0-9.
 */
/**
 * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
 * to 10 the random int is between 0-9.
 *
 * @param range
 * The Range.
 * @return an int not greater then the range.
 */
