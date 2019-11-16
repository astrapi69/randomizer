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
package de.alpharogroup.random.address

import de.alpharogroup.BaseTestCase
import de.alpharogroup.random.util.PropertiesLoader
import de.alpharogroup.string.StringExtensions
import org.meanbean.test.BeanTestException
import org.meanbean.test.BeanTester
import org.testng.AssertJUnit
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.io.IOException
import java.lang.reflect.InvocationTargetException

/**
 * The unit test class for the class [RandomAddressExtensions].
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomAddressExtensionsTest : BaseTestCase() {

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
     * Test method for [RandomAddressExtensions.getRandomStreet] .
     *
     * @throws IOException
     * Signals that an I/O exception has occurred.
     */
    @Test
    @Throws(IOException::class)
    fun testGetRandomStreet() {
        val germanstreets = PropertiesLoader
                .loadProperties(RandomAddressExtensions.PROP_FILE_STREETS)
        val germanStreet = RandomAddressExtensions.getRandomStreet(germanstreets)

        actual = germanstreets!!.contains(germanStreet)
        AssertJUnit.assertTrue("", actual)
    }

    /**
     * Test method for
     * [RandomAddressExtensions.getRandomStreetWithNumber] .
     *
     * @throws IOException
     * Signals that an I/O exception has occurred.
     */
    @Test
    @Throws(IOException::class)
    fun testGetRandomStreetWithNumber() {
        val germanstreets = PropertiesLoader
                .loadProperties(RandomAddressExtensions.PROP_FILE_STREETS)
        val germanStreetWithNumber = germanstreets?.let {
            RandomAddressExtensions
                .getRandomStreetWithNumber(it)
        }
        actual = germanStreetWithNumber != null
        AssertJUnit.assertTrue("", actual)
        val lastChar = germanStreetWithNumber?.substring(germanStreetWithNumber.length - 1, germanStreetWithNumber.length)
        actual = StringExtensions.isNumber(lastChar)
        AssertJUnit.assertTrue("", actual)
    }

    /**
     * Test method for [RandomAddressExtensions.getRandomZip] .
     *
     * @throws IOException
     * Signals that an I/O exception has occurred.
     */
    @Test
    @Throws(IOException::class)
    fun testGetRandomZip() {
        val germanzips = PropertiesLoader
                .loadProperties(RandomAddressExtensions.PROP_FILE_ZIP_CITIES)

        val randomZip = RandomAddressExtensions.getRandomZip(germanzips!!)

        actual = StringExtensions.isNumber(randomZip)
        AssertJUnit.assertTrue("", actual)
    }

    /**
     * Test method for [RandomAddressExtensions] with [BeanTester]
     */
    @Test fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(RandomAddressExtensions::class.java)
    }

}
