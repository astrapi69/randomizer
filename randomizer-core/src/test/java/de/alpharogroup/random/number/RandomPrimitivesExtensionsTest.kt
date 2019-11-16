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

import de.alpharogroup.BaseTestCase
import de.alpharogroup.math.MathExtensions
import de.alpharogroup.random.RandomCharacters
import de.alpharogroup.random.enums.RandomAlgorithm
import org.meanbean.test.BeanTestException
import org.meanbean.test.BeanTester
import org.testng.AssertJUnit.assertTrue
import org.testng.annotations.AfterMethod
import org.testng.annotations.Test
import java.lang.reflect.InvocationTargetException
import java.nio.CharBuffer
import java.text.ParseException

/**
 * The unit test class for the class [RandomPrimitivesExtensions]
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomPrimitivesExtensionsTest : BaseTestCase() {

    internal var expected: Boolean = false

    /**
     * {@inheritDoc}
     */
    @AfterMethod
    @Throws(Exception::class)
    override fun tearDown() {
        super.tearDown()
    }

    /**
     * Test method for [RandomPrimitivesExtensions.getRandomIntBetween]
     */
    @Test
    fun testGetRandomIntBetween() {
        for (i in 0..9) {
            val randomIntBetween = RandomPrimitivesExtensions.getRandomIntBetween(1, 10)
            MathExtensions.isBetween(1, 10, randomIntBetween, true, true)
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomBoolean]
     */
    @Test(enabled = true)
    fun testRandomBoolean() {
        val randomBoolean = RandomPrimitivesExtensions.randomBoolean()
        assertTrue(randomBoolean == true || randomBoolean == false)
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomByte]
     */
    @Test
    fun testRandomByte() {
        val randomByte = RandomPrimitivesExtensions.randomByte()
        assertTrue(
                MathExtensions.isBetween(java.lang.Byte.MIN_VALUE.toInt(), java.lang.Byte.MAX_VALUE.toInt(), randomByte.toInt(), true, true))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomByteArray]
     */
    @Test
    fun testRandomByteArray() {
        val randomByteArray = RandomPrimitivesExtensions.randomByteArray(8)
        assertTrue(randomByteArray.size == 8)
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomChar]
     */
    @Test
    fun testRandomCharString() {
        val string = RandomCharacters.lowcase.characters

        for (i in 0..9) {
            val randomChar = RandomPrimitivesExtensions.randomChar(string)
            val charBuffer = CharBuffer.allocate(1)
            charBuffer.put(randomChar)
            actual = string.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomDouble]
     */
    @Test(enabled = true)
    fun testRandomDouble() {
        val random = RandomPrimitivesExtensions.randomDouble()
        assertTrue(MathExtensions.isBetween(java.lang.Double.MIN_VALUE, java.lang.Double.MAX_VALUE, random))
    }


    /**
     * Test method for [RandomPrimitivesExtensions.randomDoubleBetween]
     */
    @Test(enabled = true)
    fun testRandomDoubleBetweenDoubleDouble() {
        val random = RandomPrimitivesExtensions.randomDoubleBetween(0.0, 10.0)
        assertTrue(MathExtensions.isBetween(0.0, 10.0, random))
    }

    /**
     * Test method for
     * [RandomPrimitivesExtensions.randomDoubleBetween]
     *
     * @throws ParseException
     */
    @Test(enabled = true)
    @Throws(ParseException::class)
    fun testRandomDoubleBetweenDoubleDoubleString() {
        val random = RandomPrimitivesExtensions.randomDoubleBetween(10000.0, 100000.0,
                "###,###.###")
        assertTrue(MathExtensions.isBetween(10000.0, 100000.0, random, true, true))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomDouble]
     */
    @Test(enabled = true)
    fun testRandomDoubleDouble() {
        val random = RandomPrimitivesExtensions
                .randomDouble(RandomPrimitivesExtensions.randomDoubleBetween(0.0, 10.0))
        assertTrue(MathExtensions.isBetween(java.lang.Double.MIN_VALUE, java.lang.Double.MAX_VALUE, random))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomDouble]
     */
    @Test
    fun testRandomDoubleRandomAlgorithm() {
        var algorithm: RandomAlgorithm
        var random: Double
        // scenario with RandomAlgorithm.MATH_ABS
        algorithm = RandomAlgorithm.MATH_ABS
        for (i in 0..9) {
            random = RandomPrimitivesExtensions.randomDouble(5.0, algorithm)
            assertTrue("random result is $random but should be between 0-4.",
                    MathExtensions.isBetween(-1.0, 5.0, random))
        }
        // scenario with RandomAlgorithm.MATH_RANDOM
        algorithm = RandomAlgorithm.MATH_RANDOM
        for (i in 0..9) {
            random = RandomPrimitivesExtensions.randomDouble(5.0, algorithm)
            assertTrue("random result is $random but should be between 0-4.",
                    MathExtensions.isBetween(-1.0, 5.0, random))
        }
        // scenario with RandomAlgorithm.RANDOM
        algorithm = RandomAlgorithm.RANDOM
        for (i in 0..9) {
            random = RandomPrimitivesExtensions.randomDouble(5.0, algorithm)
            assertTrue("random result is $random but should be between 0-4.",
                    MathExtensions.isBetween(-1.0, 5.0, random))
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomFloat]
     */
    @Test(enabled = true)
    fun testRandomFloat() {
        val random = RandomPrimitivesExtensions.randomFloat()
        assertTrue(MathExtensions.isBetween(java.lang.Float.MIN_VALUE, java.lang.Float.MAX_VALUE, random))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomFloatBetween]
     */
    @Test(enabled = true)
    fun testRandomFloatBetweenFloatFloat() {
        val random = RandomPrimitivesExtensions.randomFloatBetween(0.0f, 10.0f)
        assertTrue(MathExtensions.isBetween(0.0f, 10.0f, random))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomFloatBetween]
     *
     * @throws ParseException
     * is thrown if the beginning of the specified string cannot be parsed
     */
    @Test(enabled = true)
    @Throws(ParseException::class)
    fun testRandomFloatBetweenFloatFloatString() {
        val random = RandomPrimitivesExtensions.randomFloatBetween(0.0f, 10.0f, "###,###.###")
        assertTrue(MathExtensions.isBetween(0.0f, 10.0f, random))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomFloat]
     */
    @Test(enabled = true)
    fun testRandomFloatFloat() {
        var random: Float
        for (i in 0..9) {
            random = RandomPrimitivesExtensions.randomFloat(5f)
            assertTrue("random result is $random but should be between 0-4.",
                    MathExtensions.isBetween(-1f, 5f, random))
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomFloat]
     */
    @Test
    fun testRandomFloatRandomAlgorithm() {
        var algorithm: RandomAlgorithm
        var random: Float
        // scenario with RandomAlgorithm.MATH_ABS
        algorithm = RandomAlgorithm.MATH_ABS
        for (i in 0..9) {
            random = RandomPrimitivesExtensions.randomFloat(5f, algorithm)
            assertTrue("random result is $random but should be between 0-4.",
                    MathExtensions.isBetween(-1f, 5f, random))
        }
        // scenario with RandomAlgorithm.MATH_RANDOM
        algorithm = RandomAlgorithm.MATH_RANDOM
        for (i in 0..9) {
            random = RandomPrimitivesExtensions.randomFloat(5f, algorithm)
            assertTrue("random result is $random but should be between 0-4.",
                    MathExtensions.isBetween(-1f, 5f, random))
        }
        // scenario with RandomAlgorithm.RANDOM
        algorithm = RandomAlgorithm.RANDOM
        for (i in 0..9) {
            random = RandomPrimitivesExtensions.randomFloat(5f, algorithm)
            assertTrue("random result is $random but should be between 0-4.",
                    MathExtensions.isBetween(-1f, 5f, random))
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomInt]
     */
    @Test(enabled = true)
    fun testRandomInt() {
        val random = RandomPrimitivesExtensions.randomInt()
        assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomIntBetween]
     */
    @Test
    fun testRandomIntBetween() {
        for (i in 0..9) {
            val randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10)
            MathExtensions.isBetween(1, 9, randomIntBetween, true, true)
        }
    }

    /**
     * Test method for
     * [RandomPrimitivesExtensions.randomIntBetween]
     */
    @Test
    fun testRandomIntBetweenBooleanBoolean() {
        for (i in 0..9) {
            val randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, false,
                    false)
            MathExtensions.isBetween(2, 9, randomIntBetween, true, true)
        }
        for (i in 0..9) {
            val randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, false,
                    true)
            MathExtensions.isBetween(2, 10, randomIntBetween, true, true)
        }
        for (i in 0..9) {
            val randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, true,
                    false)
            MathExtensions.isBetween(1, 9, randomIntBetween, true, true)
        }
        for (i in 0..9) {
            val randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, true,
                    true)
            MathExtensions.isBetween(1, 10, randomIntBetween, true, true)
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomInt]
     */
    @Test
    fun testRandomIntInt() {
        for (i in 0..9) {
            val randomInt = RandomPrimitivesExtensions.randomInt(5)
            assertTrue("randomInt result is $randomInt but should be between 0-4.",
                    MathExtensions.isBetween(-1, 5, randomInt))
        }
    }

    /**
     * Test method for
     * [RandomPrimitivesExtensions.randomInt]
     */
    @Test
    fun testRandomIntRandomAlgorithm() {
        var algorithm: RandomAlgorithm
        // scenario with RandomAlgorithm.MATH_ABS
        algorithm = RandomAlgorithm.MATH_ABS
        for (i in 0..9) {
            val randomInt = RandomPrimitivesExtensions.randomInt(5, algorithm)
            assertTrue("randomInt result is $randomInt but should be between 0-4.",
                    MathExtensions.isBetween(-1, 5, randomInt))
        }
        // scenario with RandomAlgorithm.MATH_RANDOM
        algorithm = RandomAlgorithm.MATH_RANDOM
        for (i in 0..9) {
            val randomInt = RandomPrimitivesExtensions.randomInt(5, algorithm)
            assertTrue("randomInt result is $randomInt but should be between 0-4.",
                    MathExtensions.isBetween(-1, 5, randomInt))
        }
        // scenario with RandomAlgorithm.RANDOM
        algorithm = RandomAlgorithm.RANDOM
        for (i in 0..9) {
            val randomInt = RandomPrimitivesExtensions.randomInt(5, algorithm)
            assertTrue(
                    "randomInt result is " + randomInt
                            + " but should be between Integer.MIN_VALUE-Integer.MAX_VALUE.",
                    MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, randomInt))
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomLong]
     */
    @Test(enabled = true)
    fun testRandomLong() {
        val random = RandomPrimitivesExtensions.randomLong()
        assertTrue(MathExtensions.isBetween(java.lang.Long.MIN_VALUE, java.lang.Long.MAX_VALUE, random))
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomLong]
     */
    @Test(enabled = true)
    fun testRandomLongLong() {
        for (i in 0..9) {
            val randomLong = RandomPrimitivesExtensions.randomLong(5L)
            assertTrue("randomLong result is $randomLong but should be between 0-4.",
                    MathExtensions.isBetween(-1, 5, randomLong))
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomLong]
     */
    @Test
    fun testRandomLongRandomAlgorithm() {
        var algorithm: RandomAlgorithm
        var randomLong: Long
        // scenario with RandomAlgorithm.MATH_ABS
        algorithm = RandomAlgorithm.MATH_ABS
        for (i in 0..9) {
            randomLong = RandomPrimitivesExtensions.randomLong(5L, algorithm)
            assertTrue("randomLong result is $randomLong but should be between 0-4.",
                    MathExtensions.isBetween(-1L, 5L, randomLong))
        }
        // scenario with RandomAlgorithm.MATH_RANDOM
        algorithm = RandomAlgorithm.MATH_RANDOM
        for (i in 0..9) {
            randomLong = RandomPrimitivesExtensions.randomLong(5L, algorithm)
            assertTrue("randomLong result is $randomLong but should be between 0-4.",
                    MathExtensions.isBetween(-1L, 5L, randomLong))
        }
        // scenario with RandomAlgorithm.RANDOM
        algorithm = RandomAlgorithm.RANDOM
        for (i in 0..9) {
            randomLong = RandomPrimitivesExtensions.randomLong(5L, algorithm)
            assertTrue("randomLong result is $randomLong but should be between 0-4.",
                    MathExtensions.isBetween(-1L, 5L, randomLong))
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions.randomShort]
     */
    @Test(enabled = true)
    fun testRandomShort() {
        for (i in 0..9) {
            val randomShort = RandomPrimitivesExtensions.randomShort()
            assertTrue(MathExtensions.isBetween(-32768, 32767, randomShort.toInt(), true, true))
        }
    }

    /**
     * Test method for [RandomPrimitivesExtensions] with [BeanTester]
     */
    @Test(enabled = true, expectedExceptions = [BeanTestException::class, InvocationTargetException::class, UnsupportedOperationException::class])
    fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(RandomPrimitivesExtensions::class.java)
    }

}
