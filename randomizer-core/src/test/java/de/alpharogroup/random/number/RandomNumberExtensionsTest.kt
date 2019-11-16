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
import de.alpharogroup.string.StringExtensions
import org.meanbean.test.BeanTestException
import org.meanbean.test.BeanTester
import org.testng.AssertJUnit.assertNotNull
import org.testng.AssertJUnit.assertTrue
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.InvocationTargetException

/**
 * The unit test class for the class [RandomNumberExtensions]
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomNumberExtensionsTest : BaseTestCase() {

    internal var expected: Boolean = false

    /**
     * {@inheritDoc}
     */
    @BeforeMethod
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
    }

    /**
     * {@inheritDoc}
     */
    @AfterMethod
    @Throws(Exception::class)
    override fun tearDown() {
        super.tearDown()
    }


    /**
     * Test method for [RandomNumberExtensions.getRandomBigDecimal]
     */
    @Test
    fun testGetRandomBigDecimal() {
        val beforeComma: Int
        val afterComma: Int
        beforeComma = 3
        afterComma = 2
        val randomBigDecimal = RandomNumberExtensions.getRandomBigDecimal(afterComma,
                beforeComma)
        assertNotNull(randomBigDecimal)
    }

    /**
     * Test method for [RandomNumberExtensions.getRandomFloatString]
     */
    @Test(enabled = true)
    fun testGetRandomFloatString() {
        val beforeComma: Int
        val afterComma: Int
        beforeComma = 3
        afterComma = 2
        val randomFloatString = RandomNumberExtensions.getRandomFloatString(afterComma,
                beforeComma)
        assertNotNull(randomFloatString)
        val floatObj = java.lang.Float.valueOf(randomFloatString)
        assertNotNull(floatObj)
    }

    /**
     * Test method for [RandomNumberExtensions.getRandomNumericString]
     */
    @Test(enabled = true)
    fun testGetRandomNumericString() {
        val randomNumericString = RandomNumberExtensions.randomNumericString
        assertNotNull(randomNumericString)
        assertTrue(StringExtensions.isNumber(randomNumericString))
    }


    /**
     * Test method for [RandomNumberExtensions.randomBigInteger]
     */
    @Test
    fun testRandomBigDecimal() {
        val randomBigDecimal = RandomNumberExtensions.randomBigDecimal()
        assertNotNull(randomBigDecimal)
    }

    /**
     * Test method for [RandomNumberExtensions.randomBigInteger]
     */
    @Test
    fun testRandomBigInteger() {
        val randomBigInteger = RandomNumberExtensions.randomBigInteger()
        assertNotNull(randomBigInteger)
    }

    /**
     * Test method for [RandomNumberExtensions] with [BeanTester]
     */
    @Test
    fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(RandomNumberExtensions::class.java)
    }

}
