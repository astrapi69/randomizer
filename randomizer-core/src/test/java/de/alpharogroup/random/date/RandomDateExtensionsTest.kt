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
package de.alpharogroup.random.date

import de.alpharogroup.BaseTestCase
import de.alpharogroup.date.CalculateDateExtensions
import de.alpharogroup.date.DatePatterns
import de.alpharogroup.date.ParseDateExtensions
import org.meanbean.test.BeanTestException
import org.meanbean.test.BeanTester
import org.testng.AssertJUnit.*
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.InvocationTargetException
import java.text.ParseException
import java.util.*

/**
 * The unit test class for the class [RandomDateExtensions].
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomDateExtensionsTest : BaseTestCase() {

    internal var expected: Boolean = false

    /** The date for now.  */
    private var now: Date? = null

    /**
     * {@inheritDoc}
     */
    @BeforeMethod
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        this.now = Date(System.currentTimeMillis())
    }

    /**
     * {@inheritDoc}
     */
    @AfterMethod
    @Throws(Exception::class)
    override fun tearDown() {
        super.tearDown()
        this.now = null
    }

    /**
     * Test method for [RandomDateExtensions.randomBirthday]
     */
    @Test
    fun testCreateRandomBirthday() {
        // About 55 years.
        val past = RandomDateExtensions.dateBefore(this.now!!, 20000)
        // About 9 years.
        val recentlyPast = RandomDateExtensions.dateBefore(this.now!!, 3000)
        for (i in 0..9) {
            val randomBirthday = RandomDateExtensions.randomBirthday()
            actual = CalculateDateExtensions.isBetween(past, recentlyPast, randomBirthday)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomDateExtensions.randomBirthday] .
     */
    @Test
    fun testCreateRandomBirthdayDateDate() {
        val from = CalculateDateExtensions.substractDaysFromDate(this.now!!, 20000)
        val till = CalculateDateExtensions.substractDaysFromDate(this.now!!, 3000)
        for (i in 0..9) {
            val randomBirthday = RandomDateExtensions.randomBirthday(from, till)
            actual = CalculateDateExtensions.isBetween(from, till, randomBirthday)
            assertTrue("", actual)
        }
    }

    /**
     * Test method for [RandomDateExtensions.randomDate]
     */
    @Test
    fun testCreateRandomDate() {
        for (i in 0..6) {
            val randomDate = RandomDateExtensions.randomDate()
            assertNotNull(randomDate)

            actual = randomDate != this.now
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomDateExtensions.randomDateBetween]
     */
    @Test
    fun testCreateRandomDateBetween() {
        val from = this.now
        val startDays = 0
        val endDays = 30
        val till = CalculateDateExtensions.addDays(this.now!!, 30)
        val randomDate = RandomDateExtensions.randomDateBetween(from!!, startDays, endDays)
        actual = CalculateDateExtensions.isBetween(this.now!!, till, randomDate)
        assertTrue("", actual)
    }

    /**
     * Test method for
     * [RandomDateExtensions.randomDatebetween] .
     */
    @Test
    fun testCreateRandomDatebetweenDateDate() {
        val end = CalculateDateExtensions.addDays(this.now!!, 30)
        val start = this.now
        val randomDate = RandomDateExtensions.randomDatebetween(start!!, end)
        actual = CalculateDateExtensions.isBetween(start, end, randomDate)
        assertTrue("", actual)
    }

    /**
     * Test method for [RandomDateExtensions.randomDatebetween].
     *
     * @throws ParseException
     * occurs when their are problems with parsing the String to Date.
     */
    @Test
    @Throws(ParseException::class)
    fun testCreateRandomDatebetweenLongLong() {
        val till = CalculateDateExtensions.addDays(this.now!!, 30)
        val endDate = till.time
        val startDate = this.now!!.time
        val randomDate = RandomDateExtensions.randomDatebetween(startDate, endDate)
        val compare = ParseDateExtensions.parseToDate(randomDate,
                DatePatterns.DOT_DD_MM_YYYY_HH_MM_SS)
        actual = CalculateDateExtensions.isBetween(this.now!!, till, compare)
        assertTrue("", actual)
    }

    /**
     * Test method for [RandomDateExtensions.randomDatebetween]
     * .
     *
     * @throws ParseException
     * occurs when their are problems with parsing the String to Date.
     */
    @Test
    @Throws(ParseException::class)
    fun testCreateRandomDatebetweenLongLongString() {
        val from = CalculateDateExtensions.substractDaysFromDate(this.now!!, 1)
        val till = CalculateDateExtensions.addDays(this.now!!, 30)
        val endDate = till.time
        val startDate = from.time
        val format = DatePatterns.DOT_DD_MM_YYYY_HH_MM_SS
        val randomDate = RandomDateExtensions.randomDatebetween(startDate, endDate,
                format)
        val compare = ParseDateExtensions.parseToDate(randomDate, format)
        actual = CalculateDateExtensions.isBetween(from, till, compare)
        assertTrue("", actual)
    }

    /**
     * Test method for [RandomDateExtensions.randomDate]
     */
    @Test
    fun testCreateRandomDateWithFromDate() {
        val from = this.now
        val randomDate = RandomDateExtensions.randomDate(from!!)
        assertNotNull(randomDate)
    }

    /**
     * Test method for [RandomDateExtensions.dateAfter].
     */
    @Test
    fun testDateAfterDate() {
        val expected: Boolean
        val end = CalculateDateExtensions.addDays(now!!, 10001)
        val dateAfter = RandomDateExtensions.dateAfter(now!!)

        expected = true
        actual = CalculateDateExtensions.isBetween(now!!, end, dateAfter)
        assertEquals(actual, expected)
    }

    /**
     * Test method for [RandomDateExtensions.dateAfter].
     */
    @Test
    fun testDateAfterDateInt() {
        val expected: Boolean
        val end = CalculateDateExtensions.addDays(now!!, 1001)
        val dateAfter = RandomDateExtensions.dateAfter(now!!, 1000)

        expected = true
        actual = CalculateDateExtensions.isBetween(now!!, end, dateAfter)
        assertEquals(actual, expected)
    }

    /**
     * Test method for [RandomDateExtensions.dateBefore].
     */
    @Test
    fun testDateBefore() {
        val expected: Boolean
        val start = CalculateDateExtensions.substractDaysFromDate(now!!, 10001)
        val dateBefore = RandomDateExtensions.dateBefore(now!!)

        expected = true
        actual = CalculateDateExtensions.isBetween(start, now!!, dateBefore)
        assertEquals(actual, expected)
    }

    /**
     * Test method for [RandomDateExtensions.dateBefore].
     */
    @Test
    fun testDateBeforeInt() {
        val expected: Boolean
        val start = CalculateDateExtensions.substractDaysFromDate(now!!, 1001)
        val dateBefore = RandomDateExtensions.dateBefore(now!!, 1000)

        expected = true
        actual = CalculateDateExtensions.isBetween(start, now!!, dateBefore)
        assertEquals(actual, expected)
    }

    /**
     * Test method for [RandomDateExtensions.getTimestamp].
     */
    @Test
    fun testGetTimestamp() {
        val timestamp = RandomDateExtensions.timestamp
        assertNotNull(timestamp)
    }

    /**
     * Test method for [RandomDateExtensions.getTimestamp].
     */
    @Test
    fun testGetTimestampDate() {
        val timestamp = RandomDateExtensions.getTimestamp(now!!)
        assertNotNull(timestamp)
    }

    /**
     * Test method for [RandomDateExtensions.randomLocalDate]
     */
    @Test
    fun testRandomLocalDate() {
        for (i in 0..6) {
            val randomObject = RandomDateExtensions.randomLocalDate()
            assertNotNull(randomObject)
        }
    }

    /**
     * Test method for [RandomDateExtensions.randomLocalDateTime]
     */
    @Test
    fun testRandomLocalDateTime() {
        val randomObject = RandomDateExtensions.randomLocalDateTime()
        assertNotNull(randomObject)
    }

    /**
     * Test method for [RandomDateExtensions.randomLocalTime]
     */
    @Test
    fun testRandomLocalTime() {
        for (i in 0..6) {
            val randomObject = RandomDateExtensions.randomLocalTime()
            assertNotNull(randomObject)
        }
    }

    /**
     * Test method for [RandomDateExtensions.randomZoneId]
     */
    @Test
    fun testRandomZoneId() {
        val randomObject = RandomDateExtensions.randomZoneId()
        assertNotNull(randomObject)
    }

    /**
     * Test method for [RandomDateExtensions] with [BeanTester]
     */
    @Test fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(RandomDateExtensions::class.java)
    }

}
