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
package de.alpharogroup.random.`object`

import de.alpharogroup.random.RandomExtensions
import de.alpharogroup.random.date.RandomDateExtensions
import de.alpharogroup.random.enums.RandomAlgorithm
import de.alpharogroup.random.number.RandomNumberExtensions
import de.alpharogroup.random.number.RandomPrimitivesExtensions
import de.alpharogroup.reflection.ReflectionExtensions
import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*

/**
 * A factory for creating random objects
 */
class RandomObjectFactory {
    companion object {

        /**
         * Factory method for create a new random [RandomAlgorithm] object
         *
         * @return the random algorithm
         */
        fun newRandomAlgorithm(): RandomAlgorithm {
            return RandomExtensions.getRandomEnumFromEnumValues(RandomAlgorithm.values())
        }

        /**
         * Factory method for create a new random [Float] object
         *
         * @param afterComma
         * How many decimal places after the comma
         * @param beforeComma
         * How many decimal places before the comma
         * @return the new random [Float] object
         */
        fun newRandomFloat(afterComma: Int, beforeComma: Int): Float {
            return RandomPrimitivesExtensions.randomFloat(afterComma, beforeComma)
        }

        /**
         * Factory method for create a new random [Byte] object array
         *
         * @param length
         * the length.
         * @return the Byte[]
         * @return the new random [Byte] object array
         */
        fun newRandomByteObjects(length: Int): Array<Byte?> {
            val randomByteObjects = arrayOfNulls<Byte>(length)
            val randomBytes = RandomPrimitivesExtensions.randomByteArray(length)
            for (i in 0 until length) {
                randomByteObjects[i] = randomBytes[i]
            }
            return randomByteObjects
        }

        /**
         * Factory method for create a new random object of the given [Class].
         *
         * @param <T>
         * the generic type
         * @param cls
         * the class
         * @param ignoreFieldNames
         * an optional array with the field names that shell be ignored
         * @return the new random object
         * @throws IllegalAccessException
         * is thrown if the class or its default constructor is not accessible.
         * @throws InstantiationException
         * is thrown if this `Class` represents an abstract class, an interface, an
         * array class, a primitive type, or void; or if the class has no default
         * constructor; or if the instantiation fails for some other reason.
         * @throws NoSuchFieldException
         * is thrown if no such field exists
        </T> */
        @Throws(IllegalAccessException::class, InstantiationException::class, NoSuchFieldException::class)
        fun <T> newRandomObject( cls: Class<T>, vararg ignoreFieldNames: String): T {
            val instance = ReflectionExtensions.newInstance(cls)
            return setRandomValues(cls, instance, *ignoreFieldNames)
        }

        /**
         * Sets the random values to the fields of the given instance
         *
         * @param <T>
         * the generic type
         * @param cls
         * the cls
         * @param instance
         * the instance to set random values
         * @param ignoreFieldNames
         * the ignore field names
         * @return the new random object
         * @throws IllegalAccessException
         * is thrown if the class or its default constructor is not accessible.
         * @throws InstantiationException
         * is thrown if this `Class` represents an abstract class, an interface, an
         * array class, a primitive type, or void; or if the class has no default
         * constructor; or if the instantiation fails for some other reason.
         * @throws NoSuchFieldException
         * is thrown if no such field exists
        </T> */
        @Throws(IllegalAccessException::class, InstantiationException::class, NoSuchFieldException::class)
        fun <T> setRandomValues(cls: Class<T>, instance: T,
                                vararg ignoreFieldNames: String): T {
            val allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(cls)
            val toIgnoreFields = Arrays.asList(*ignoreFieldNames)
            for (field in allDeclaredFields) {
                if (Modifier.isFinal(field.modifiers) || toIgnoreFields.contains(field.name)) {
                    continue
                }
                val value = newRandomValue(field)
                ReflectionExtensions.setFieldValue(instance, field, value)
            }
            return instance
        }

        /**
         * Factory method for create a new random value for the given [field][Field]
         *
         * @param field
         * the field
         * @return the new random value
         * @throws IllegalAccessException
         * is thrown if the class or its default constructor is not accessible.
         * @throws InstantiationException
         * is thrown if this `Class` represents an abstract class, an interface, an
         * array class, a primitive type, or void; or if the class has no default
         * constructor; or if the instantiation fails for some other reason.
         * @throws NoSuchFieldException
         * is thrown if no such field exists
         */
        @Throws(IllegalAccessException::class, InstantiationException::class, NoSuchFieldException::class)
        fun newRandomValue(field: Field): Any? {
            val type = field.type
            if (type.isEnum) {
                return RandomExtensions
                        .getRandomEnumFromClassname(type.canonicalName)
            } else if (type == Void.TYPE || type == Void::class.java) {
                return null
            } else if (type == java.lang.Byte.TYPE || type == Byte::class.java) {
                return java.lang.Byte.valueOf(RandomPrimitivesExtensions.randomByte())
            } else if (type == Character.TYPE || type == Char::class.java) {
                return Character.valueOf(RandomPrimitivesExtensions.randomChar())
            } else if (type == java.lang.Short.TYPE || type == Short::class.java) {
                return java.lang.Short.valueOf(RandomPrimitivesExtensions.randomShort())
            } else if (type == java.lang.Boolean.TYPE || type == Boolean::class.java) {
                return java.lang.Boolean.valueOf(RandomPrimitivesExtensions.randomBoolean())
            } else if (type == Integer.TYPE || type == Int::class.java) {
                return Integer.valueOf(RandomPrimitivesExtensions.randomInt())
            } else if (type == java.lang.Long.TYPE || type == Long::class.java) {
                return java.lang.Long.valueOf(RandomPrimitivesExtensions.randomLong())
            } else if (type == java.lang.Double.TYPE || type == Double::class.java) {
                return java.lang.Double.valueOf(RandomPrimitivesExtensions.randomDouble())
            } else if (type == java.lang.Float.TYPE || type == Float::class.java) {
                return java.lang.Float.valueOf(RandomPrimitivesExtensions.randomFloat())
            } else if (type == String::class.java) {
                return RandomExtensions.randomString
            } else if (type == BigInteger::class.java) {
                return RandomNumberExtensions.randomBigInteger()
            } else if (type == BigDecimal::class.java) {
                return RandomNumberExtensions.randomBigDecimal()
            } else if (type == Date::class.java) {
                return RandomDateExtensions.randomDate()
            } else if (type == LocalDateTime::class.java) {
                return RandomDateExtensions.randomLocalDateTime()
            } else if (type == LocalDate::class.java) {
                return RandomDateExtensions.randomLocalDate()
            } else if (type == LocalTime::class.java) {
                return RandomDateExtensions.randomLocalTime()
            }
            return newRandomObject(type)
        }
    }

}
