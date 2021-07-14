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
package io.github.astrapi69.random;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

import io.github.astrapi69.AbstractTestCase;

/**
 * The unit test class for the class {@link RandomCharacters}
 */
public class RandomCharactersTest extends AbstractTestCase<String, String>
{

	/**
	 * Test method for evaluate the values of the enum variables
	 */
	@Test
	public void testConcatenatedConst()
	{
		actual = RandomCharacters.special.getCharacters();
		expected = "#@$%^&*?!";
		assertEquals(actual, expected);

		actual = RandomCharacters.numbers.getCharacters();
		expected = "0123456789";
		assertEquals(actual, expected);

		actual = RandomCharacters.lowcase.getCharacters();
		expected = "abcdefghijklmnopqrstuvwxyz";
		assertEquals(actual, expected);

		actual = RandomCharacters.uppercase.getCharacters();
		expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		assertEquals(actual, expected);

		actual = RandomCharacters.lowcaseWithNumbers.getCharacters();
		expected = "abcdefghijklmnopqrstuvwxyz0123456789";
		assertEquals(actual, expected);

		actual = RandomCharacters.uppercaseWithNumbers.getCharacters();
		expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		assertEquals(actual, expected);

		actual = RandomCharacters.lowcaseWithNumbersAndSpecial.getCharacters();
		expected = "abcdefghijklmnopqrstuvwxyz0123456789#@$%^&*?!";
		assertEquals(actual, expected);

		actual = RandomCharacters.uppercaseWithNumbersAndSpecial.getCharacters();
		expected = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!";
		assertEquals(actual, expected);

		actual = RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters();
		expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		assertEquals(actual, expected);

		actual = RandomCharacters.lowcaseWithUppercaseAndNumbersAndSpecial.getCharacters();
		expected = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!";
		assertEquals(actual, expected);

		actual = RandomCharacters.BRACKETS_CHAR;
		expected = "(){}[]<>";
		assertEquals(actual, expected);

		actual = RandomCharacters.UNDERSCORE_CHAR;
		expected = "_";
		assertEquals(actual, expected);

		actual = RandomCharacters.SLASH_CHAR;
		expected = "/";
		assertEquals(actual, expected);

		actual = RandomCharacters.MATH_OPERATOR_CHAR;
		expected = "+-";
		assertEquals(actual, expected);

		actual = RandomCharacters.QUOTATION_MARK_CHARACTERS;
		expected = "´`\'\"";
		assertEquals(actual, expected);

		actual = RandomCharacters.OTHER_SPECIALCHARS;
		expected = "°§=~.:,;µ|€²³^°";
		assertEquals(actual, expected);

	}

}
