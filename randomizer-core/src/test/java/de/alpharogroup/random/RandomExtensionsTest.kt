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

import de.alpharogroup.BaseTestCase
import de.alpharogroup.math.MathExtensions
import de.alpharogroup.random.`object`.RandomObjectFactory
import de.alpharogroup.test.objects.enums.Gender
import org.apache.commons.lang3.ArrayUtils
import org.meanbean.test.BeanTestException
import org.meanbean.test.BeanTester
import org.testng.Assert.assertNull
import org.testng.AssertJUnit.assertNotNull
import org.testng.AssertJUnit.assertTrue
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.lang.reflect.InvocationTargetException
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.util.*

/**
 * The unit test class for the class [RandomExtensions].
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomExtensionsTest : BaseTestCase() {

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
     * Test method for [RandomExtensions.getRandomEntry] .
     */
    @Test
    fun testGetRandomEntryList() {
        val list = ArrayList<String>()
        list.add("Anton")
        list.add("Kosta")
        list.add("Caesar")
        list.add("Asterios")
        list.add("Anastasia")
        list.add("Katerina")

        for (i in 0..9) {
            val randomEntry = RandomExtensions.getRandomEntry(list)

            actual = list.contains(randomEntry)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomExtensions.getRandomEntry] .
     */
    @Test
    fun testGetRandomEntryMap() {
        val map = HashMap<String, String>()
        map["1"] = "novalue"
        map["2"] = "somevalue"
        map["3"] = "othervalue"
        map["4"] = "value"
        map["5"] = "value"
        val values = map.values

        for (i in 0..9) {
            val randomValue = RandomExtensions.getRandomEntry(map) as String

            actual = values.contains(randomValue)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomExtensions.getRandomEnumFromObject] .
     */
    @Test
    fun testGetRandomEnum() {
        val enumEntry = Gender.FEMALE
        val randomEnumEntry = RandomExtensions.getRandomEnumFromObject(enumEntry)

        val genders = Gender.values()
        assertTrue("Enum value should contain the random value.",
                ArrayUtils.contains(genders, randomEnumEntry))
    }

    /**
     * Test method for [RandomExtensions.getRandomEnumFromEnumValues] .
     */
    @Test
    fun testGetRandomEnumArray() {
        val genders = Gender.values()
        val randomEnumEntry = RandomExtensions.getRandomEnumFromEnumValues(genders)
        assertTrue("Enum value should contain the random value.",
                ArrayUtils.contains(genders, randomEnumEntry))
    }

    /**
     * Test method for [RandomExtensions.getRandomEnumFromObject] .
     */
    @Test
    fun testGetRandomEnumClass() {
        val randomEnumEntry = RandomExtensions.getRandomEnumFromClass<Gender>(Gender::class.java)

        val genders = Gender.values()
        assertTrue("Enum value should contain the random value.",
                ArrayUtils.contains(genders, randomEnumEntry))
    }

    /**
     * Test method for [RandomExtensions.getRandomEnumFromClassname]
     */
    @Test
    fun testGetRandomEnumNull() {
        var randomEnum = RandomExtensions.getRandomEnumFromObject<Gender>(null as Gender?)
        assertNull(randomEnum)

        randomEnum = RandomExtensions.getRandomEnumFromClassname(null as String?)
        assertNull(randomEnum)
    }

    /**
     * Test method for [RandomExtensions.getRandomEnumFromClassname] .
     */
    @Test
    fun testGetRandomEnumString() {
        var enumClassName = "de.alpharogroup.test.objects.enums.Gender"
        var randomEnumEntry = RandomExtensions.getRandomEnumFromClassname<Gender>(enumClassName)

        val genders = Gender.values()
        assertTrue("Enum value should contain the random value.",
                ArrayUtils.contains(genders, randomEnumEntry))

        enumClassName = "Gender"
        randomEnumEntry = RandomExtensions.getRandomEnumFromClassname(enumClassName)
        assertNull(randomEnumEntry)

    }

    /**
     * Test method for [RandomExtensions.getRandomHexString]
     */
    @Test(enabled = true)
    fun testgetRandomHexString() {
        val randomHexString = RandomExtensions.getRandomHexString(16)
        assertTrue(randomHexString.length == 16)
    }

    /**
     * Test method for [RandomExtensions.getRandomKey] .
     */
    @Test
    fun testGetRandomKey() {
        val map = HashMap<String, String>()
        map["1"] = "novalue"
        map["2"] = "somevalue"
        map["3"] = "othervalue"
        map["4"] = "value"
        map["5"] = "value"
        val keys = map.keys

        for (i in 0..9) {
            val randomKey = RandomExtensions.getRandomKey(map) as String

            actual = keys.contains(randomKey)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomExtensions.getRandomString]
     */
    @Test(enabled = true)
    fun testGetRandomStringInt() {
        val randomString = RandomExtensions.getRandomString(10)
        assertNotNull(randomString)
        assertTrue(randomString.length < 11)
    }

    /**
     * Test method for [RandomExtensions.getRandomString]
     */
    @Test(enabled = true)
    fun testGetRandomStringwithStartEnd() {
        val randomString = RandomExtensions.getRandomString(3, 25)
        assertNotNull(randomString)
        assertTrue(MathExtensions.isBetween(3, 25, randomString.length, true, true))
    }

    /**
     * Test method for [RandomExtensions.newRandomPixel]
     */
    @Test(enabled = true)
    fun testNewRandomPixel() {
        val random = RandomExtensions.newRandomPixel()
        assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random))
    }

    /**
     * Test method for [RandomExtensions.newSalt]
     */
    @Test(enabled = true)
    fun testNewSalt() {
        val newSalt = RandomExtensions.newSalt()
        assertNotNull(newSalt)
    }

    /**
     * Test method for [RandomExtensions.randomToken] .
     */
    @Test
    fun testRandomSalt() {
        val randomSalt = RandomExtensions.getRandomSalt(8, Charset.forName("UTF-8"))
        println(String(randomSalt))
    }

    /**
     * Test method for [RandomExtensions.randomSerialNumber]
     */
    @Test(enabled = true)
    fun testRandomSerialNumber() {
        for (i in 0..9) {
            val randomSerialNumber = RandomExtensions.randomSerialNumber()
            assertNotNull(randomSerialNumber)
        }
    }

    /**
     * Test method for [RandomExtensions.getRandomString]
     */
    @Test
    fun testRandomStringStringArray() {
        val array = arrayOf("blab", "flih", "klap", "teta", "brut", "gzft", "ccp")
        val listFromArray = Arrays.asList(*array)

        for (i in 0..9) {
            val randomString = RandomExtensions.getRandomString(array)

            actual = listFromArray.contains(randomString)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomExtensions.getRandomString]
     */
    @Test
    fun testRandomStringStringInt() {
        val charBuffer = CharBuffer.allocate(45)
        val length = 5
        val chars = RandomCharacters.lowcaseWithNumbersAndSpecial.characters
        charBuffer.put(chars)

        for (i in 0..9) {
            val randomString = RandomExtensions.getRandomString(chars, length)

            actual = randomString.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomExtensions.randomToken]
     */
    @Test
    fun testRandomToken() {
        val randomToken = RandomExtensions.randomToken()
        assertNotNull(randomToken)
    }

    /**
     * Test method for [RandomExtensions.randomUUID]
     */
    @Test
    fun testRandomUUID() {
        val randomUUID = RandomExtensions.randomUUID()
        assertNotNull(randomUUID)
    }

    /**
     * Test method for [RandomExtensions] with [BeanTester]
     */
    @Test fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(RandomExtensions::class.java)
    }

}
