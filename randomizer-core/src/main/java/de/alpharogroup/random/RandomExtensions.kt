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

import de.alpharogroup.lang.ClassExtensions
import de.alpharogroup.random.`object`.RandomObjectFactory
import de.alpharogroup.random.number.RandomPrimitivesExtensions
import java.math.BigInteger
import java.nio.charset.Charset
import java.util.*

/**
 * Utility class for producing random data. Existing name conventions:
 *
 * If the method starts with random* than it returns a primitive data type. If the method starts
 * with getRandom* than it returns an object.
 *
 * @version 1.1
 * @author Asterios Raptis
 */
class RandomExtensions {
    companion object {

        /**
         * Generates a random int for use with pixel.
         *
         * @param red
         * The red value.
         * @param green
         * The green value.
         * @param blue
         * The blue value.
         * @param alpha
         * The alpha value.
         * @return a random int for use with pixel.
         */
        @JvmOverloads
        fun newRandomPixel(red: Int = RandomPrimitivesExtensions.randomInt(256), green: Int = RandomPrimitivesExtensions.randomInt(256), blue: Int = RandomPrimitivesExtensions.randomInt(256),
                           alpha: Int = RandomPrimitivesExtensions.randomInt(256)): Int {
            return alpha shl 24 or (red shl 16) or (green shl 8) or blue
        }

        /**
         * Returns a random entry from the given List.
         *
         * @param <T>
         * the generic type
         * @param list
         * The List.
         * @return Return's a random entry from the List.
        </T> */
        fun <T> getRandomEntry(list: List<T>): T {
            return list[getRandomIndex(list)]
        }

        /**
         * Returns a random entry from the given map.
         *
         * @param <K>
         * the key type
         * @param <V>
         * the value type
         * @param map
         * The map.
         * @return Return's a random entry from the map.
        </V></K> */
        inline fun <K, reified V> getRandomEntry(map: Map<K, V>): Any {
            val entries = map.values.toTypedArray()
            return entries[RandomPrimitivesExtensions.randomInt(entries.size)]!!
        }

        /**
         * Gets the random enum.
         *
         * @param <T>
         * the generic type
         * @param clazz
         * the clazz
         * @return the random enum
        </T> */
        fun <T : Enum<*>> getRandomEnumFromClass(clazz: Class<T>): T {
            return getRandomEnumFromEnumValues(clazz.enumConstants)
        }

        /**
         * Gets the random enum.
         *
         * @param <T>
         * the generic type
         * @param classname
         * the classname
         * @return the random enum
        </T> */
        fun <T : Enum<*>> getRandomEnumFromClassname(classname: String?): T? {
            if (classname != null && !classname.isEmpty()) {
                try {
                    var enumClass = ClassExtensions.forName(classname) as Class<T>
                    return getRandomEnumFromClass(enumClass)
                } catch (e: ClassNotFoundException) {
                    return null
                }

            }
            return null
        }

        /**
         * Gets the random enum.
         *
         * @param <T>
         * the generic type
         * @param obj
         * the obj
         * @return the random enum
        </T> */
        fun <T : Enum<*>> getRandomEnumFromObject(obj: T?): T? {
            if (obj != null) {
                val clazz = obj.javaClass
                return getRandomEnumFromClass(clazz)
            }
            return null
        }

        /**
         * Gets the random enum.
         *
         * @param <T>
         * the generic type
         * @param values
         * the values
         * @return the random enum
        </T> */
        fun <T : Enum<*>> getRandomEnumFromEnumValues(values: Array<T>): T {
            return values[RandomPrimitivesExtensions.randomInt(values.size)]
        }

        /**
         * Returns a random index from the given List.
         *
         * @param <T>
         * the generic type
         * @param list
         * The List.
         * @return Return's a random index from the List.
        </T> */
        fun <T> getRandomIndex(list: Collection<T>): Int {
            return RandomPrimitivesExtensions.randomInt(list.size)
        }

        /**
         * Returns a random key from the given map.
         *
         * @param <K>
         * the key type
         * @param <V>
         * the value type
         * @param map
         * The map.
         * @return Return's a random key from the map.
        </V></K> */
        inline fun <reified K, V> getRandomKey(map: Map<K, V>): Any {
            val keySet = map.keys
            val keys = keySet.toTypedArray()
            return keys[RandomPrimitivesExtensions.randomInt(keys.size)]!!
        }

        /**
         * Generates a random string.
         *
         * @param length
         * the specified length.
         * @return the generated random string.
         */
        fun getRandomString(length: Int): String {
            val maxLength = Math.min(length, 1024)
            val sb = StringBuilder(maxLength)
            for (i in 0 until maxLength) {
                sb.append(RandomPrimitivesExtensions.randomChar())
            }
            return sb.toString()
        }

        /**
         * Generates a random hexadecimal [String]
         *
         * @param numberOfCharacters
         * the number of characters
         * @return the generated random hexadecimal [String]
         */
        fun getRandomHexString(numberOfCharacters: Int): String {
            val sb = StringBuilder()
            while (sb.length < numberOfCharacters) {
                sb.append(Integer.toHexString(RandomPrimitivesExtensions.randomInt()))
            }
            return sb.toString().substring(0, numberOfCharacters)
        }

        /**
         * The Method randomString(String, int) makes an random String from the given String and to the
         * spezified length. This can be used to produce passwords.
         *
         * @param chars
         * The String to get the random chars.
         * @param length
         * The length from the random String.
         * @return The produced random String.
         */
        fun getRandomString(chars: String, length: Int): String {
            val ergebnis = StringBuffer()
            for (i in 0 until length) {
                ergebnis.append(RandomPrimitivesExtensions.randomChar(chars))
            }
            return ergebnis.toString()
        }


        /**
         * Generates a random string with a length between 3 and 25
         *
         * @return The produced random String.
         */
        val randomString: String
            get() = getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.characters,
                    RandomPrimitivesExtensions.randomIntBetween(3, 25))


        /**
         * Generates a random string with a length between the given start and end
         *
         * @param start
         * the start
         * @param end
         * the end
         * @return the generated random string
         */
        fun getRandomString(start: Int, end: Int): String {
            return getRandomString(RandomPrimitivesExtensions.randomIntBetween(start, end))
        }

        /**
         * The Method randomString(String []) a random String from the Array For example: The
         * Stringarray test as argument. Possible values: "blab", "flih", "klap", "teta", "brut",
         * "gzft", "ccp". Possible selection can be one value from the Stringarray like "blab" or
         * "klap".
         *
         * @param array
         * The array with the String to be selected.
         * @return The selected String from the array.
         */
        fun getRandomString(array: Array<String>): String {
            return array[RandomPrimitivesExtensions.randomInt(array.size)]
        }

        /**
         * Factory method for create a new random [UUID]
         *
         * @return the new random [UUID]
         */
        fun randomUUID(): UUID {
            return UUID.randomUUID()
        }

        /**
         * Returns a random token for use in web services.
         *
         * @return A random token.
         */
        fun randomToken(): String {
            val token = BigInteger(130, DefaultSecureRandom.get())
            return token.toString(32)
        }

        /**
         * Returns a random serial number that can be used for a serial number.
         *
         * @return a random serial number as a [BigInteger] object.
         */
        fun randomSerialNumber(): BigInteger {
            var next = DefaultSecureRandom.get().nextLong()
            if (next < 0) {
                next = next * -1
            }
            return BigInteger.valueOf(next)
        }

        /**
         * Factory method for create a new random salt.
         *
         * @return the byte[] with the new random salt.
         */
        fun newSalt(): ByteArray {
            return RandomPrimitivesExtensions.randomByteArray(16)
        }

        /**
         * Gets the random salt.
         *
         * @param length
         * the length
         * @param charset
         * the charset
         * @return the random salt
         */
        fun getRandomSalt(length: Int, charset: Charset): ByteArray {
            return RandomExtensions
                    .getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.characters,
                            length)
                    .toByteArray(charset)
        }
    }

}
/**
 * Generates a random int for use with pixel.
 *
 * @return a random int for use with pixel.
 */
