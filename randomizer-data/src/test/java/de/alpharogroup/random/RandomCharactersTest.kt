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

import org.testng.AssertJUnit.assertEquals

import org.testng.annotations.Test

import de.alpharogroup.AbstractTestCase

/**
 * The unit test class for the class [RandomCharacters]
 */
class RandomCharactersTest : AbstractTestCase<String, String>() {

    /**
     * Test method for evaluate the values of the enum variables
     */
    @Test
    fun testConcatenatedConst() {
        actual = RandomCharacters.special.characters
        expected = "#@$%^&*?!"
        assertEquals(actual, expected)

        actual = RandomCharacters.numbers.characters
        expected = "0123456789"
        assertEquals(actual, expected)

        actual = RandomCharacters.lowcase.characters
        expected = "abcdefghijklmnopqrstuvwxyz"
        assertEquals(actual, expected)

        actual = RandomCharacters.uppercase.characters
        expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        assertEquals(actual, expected)

        actual = RandomCharacters.lowcaseWithNumbers.characters
        expected = "abcdefghijklmnopqrstuvwxyz0123456789"
        assertEquals(actual, expected)

        actual = RandomCharacters.uppercaseWithNumbers.characters
        expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        assertEquals(actual, expected)

        actual = RandomCharacters.lowcaseWithNumbersAndSpecial.characters
        expected = "abcdefghijklmnopqrstuvwxyz0123456789#@$%^&*?!"
        assertEquals(actual, expected)

        actual = RandomCharacters.uppercaseWithNumbersAndSpecial.characters
        expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!"
        assertEquals(actual, expected)

        actual = RandomCharacters.lowcaseWithUppercaseAndNumbers.characters
        expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        assertEquals(actual, expected)

        actual = RandomCharacters.lowcaseWithUppercaseAndNumbersAndSpecial.characters
        expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!"
        assertEquals(actual, expected)
    }

}
