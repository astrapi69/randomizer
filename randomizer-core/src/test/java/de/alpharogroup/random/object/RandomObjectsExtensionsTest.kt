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

import de.alpharogroup.BaseTestCase
import de.alpharogroup.random.RandomCharacters
import org.meanbean.test.BeanTester
import org.testng.Assert.assertNotNull
import org.testng.Assert.assertTrue
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import java.nio.CharBuffer
import java.util.*

/**
 * The unit test class for the class [RandomObjectsExtensions].
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomObjectsExtensionsTest : BaseTestCase() {

    internal var expected: Boolean = false

    /**
     * {@inheritDoc}
     */
    @BeforeMethod
    public override fun setUp() {
    }

    /**
     * {@inheritDoc}
     */
    @AfterMethod
    public override fun tearDown() {
    }

    /**
     * Test method for [RandomObjectsExtensions.getInfomailFromWebsite] .
     */
    @Test
    fun testGetInfomailFromWebsite() {
        val charBuffer = CharBuffer
                .allocate(RandomCharacters.lowcaseWithNumbers.characters.length)
        charBuffer.put(RandomCharacters.lowcaseWithNumbers.characters)
        val url = RandomObjectsExtensions.randomWebsite
        val emailprefix = "info@"

        for (i in 0..9) {
            val randomInfomail = RandomObjectsExtensions.getInfomailFromWebsite(url)
            actual = randomInfomail.startsWith(emailprefix)
            assertTrue(actual)

            actual = randomInfomail.contains(charBuffer)
            assertTrue(actual)
        }
        val shortUrl = url.substring(7)
        for (i in 0..9) {

            val randomInfomail = RandomObjectsExtensions.getInfomailFromWebsite(shortUrl)
            actual = randomInfomail.startsWith(emailprefix)
            assertTrue(actual)

            actual = randomInfomail.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.getInfomailFromWebsite] .
     */
    @Test(expectedExceptions = [IllegalArgumentException::class])
    fun testGetInfomailFromWebsiteExEx() {
        RandomObjectsExtensions.getInfomailFromWebsite("htp://ww.g.rw")
    }

    /**
     * Test method for [RandomObjectsExtensions.getRandomEmail].
     */
    @Test
    fun testGetRandomEmail() {
        val charBuffer = CharBuffer
                .allocate(RandomCharacters.lowcaseWithNumbers.characters.length)
        charBuffer.put(RandomCharacters.lowcaseWithNumbers.characters)

        for (i in 0..99) {
            val randomEmail = RandomObjectsExtensions.randomEmail
            actual = randomEmail.contains(charBuffer)

            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.getRandomFaxnumber].
     */
    @Test
    fun testGetRandomFaxnumber() {
        val charBuffer = CharBuffer
                .allocate(RandomCharacters.numbers.characters.length)
        charBuffer.put(RandomCharacters.numbers.characters)

        for (i in 0..99) {
            val randomPhonenumber = RandomObjectsExtensions.randomPhonenumber
            val randomFaxnumber = RandomObjectsExtensions
                    .getRandomFaxnumber(randomPhonenumber)
            actual = randomFaxnumber.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.getRandomMobilnumber].
     */
    @Test
    fun testGetRandomMobilnumber() {
        val charBuffer = CharBuffer
                .allocate(RandomCharacters.numbers.characters.length)
        charBuffer.put(RandomCharacters.numbers.characters)

        for (i in 0..99) {
            val randomMobilnumber = RandomObjectsExtensions.randomMobilnumber
            actual = randomMobilnumber.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.getRandomPassword].
     */
    @Test
    fun testGetRandomPasswordInt() {
        val charBuffer = CharBuffer.allocate(26)
        val length = 5
        val chars = RandomCharacters.lowcase.characters
        charBuffer.put(chars)

        for (i in 0..99) {
            val randomPassword = RandomObjectsExtensions.getRandomPassword(length)
            actual = randomPassword.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.getRandomPassword].
     */
    @Test
    fun testGetRandomPasswordOptionalInt() {
        val charBuffer = CharBuffer.allocate(26)
        val length = 5
        var optLength = Optional.of(length)
        val chars = RandomCharacters.lowcase.characters
        charBuffer.put(chars)

        for (i in 0..9) {
            val randomPassword = RandomObjectsExtensions.getRandomPassword(optLength)
            actual = randomPassword.contains(charBuffer)
            assertTrue(actual)
        }

        optLength = Optional.empty()

        for (i in 0..9) {
            val randomPassword = RandomObjectsExtensions.getRandomPassword(optLength)
            actual = randomPassword.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.getRandomPhonenumber].
     */
    @Test
    fun testGetRandomPhonenumber() {
        val charBuffer = CharBuffer
                .allocate(RandomCharacters.numbers.characters.length)
        charBuffer.put(RandomCharacters.numbers.characters)

        for (i in 0..99) {
            val randomPhonenumber = RandomObjectsExtensions.randomPhonenumber
            actual = randomPhonenumber.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.getRandomWebsite].
     */
    @Test
    fun testGetRandomWebsite() {
        val charBuffer = CharBuffer
                .allocate(RandomCharacters.lowcaseWithNumbers.characters.length)
        charBuffer.put(RandomCharacters.lowcaseWithNumbers.characters)

        for (i in 0..99) {
            val randomWebsite = RandomObjectsExtensions.randomWebsite
            actual = randomWebsite.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.newRandomId].
     */
    @Test
    fun testNewRandomId() {
        for (i in 0..9) {
            val newRandomId = RandomObjectsExtensions.newRandomId()
            assertNotNull(newRandomId)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions.newRandomName]
     */
    @Test
    fun testNewRandomName() {
        val charBuffer = CharBuffer
                .allocate(RandomCharacters.lowcaseWithNumbers.characters.length)
        charBuffer.put(RandomCharacters.lowcaseWithNumbers.characters)
        val donatedChars = RandomCharacters.lowcaseWithNumbers.characters
                .toCharArray()

        for (i in 0..99) {
            val randomName = RandomObjectsExtensions.newRandomName(donatedChars)
            actual = randomName.contains(charBuffer)
            assertTrue(actual)
        }
    }

    /**
     * Test method for [RandomObjectsExtensions] with [BeanTester]
     */
    @Test
    fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(RandomObjectsExtensions::class.java)
    }

}
