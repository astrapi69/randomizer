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
/**
 * 
 */
package de.alpharogroup.random;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link Constants}.
 */
public class ConstantsTest
{

	/**
	 * Test method for evaluate the concatenated constants.
	 */
	@Test
	public void testConcatenatedConst()
	{
		assertEquals(Constants.LCCHARSWN, "abcdefghijklmnopqrstuvwxyz0123456789");
		assertEquals(Constants.LCCHARSWN, Constants.LOWCASECHARS_WITH_NUMBERS);
		assertEquals(Constants.UCCHARSWN, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		assertEquals(Constants.UCCHARSWN, Constants.UPPERCASECHARS_WITH_NUMBERS);
		assertEquals(Constants.LCCHARSWNASC, "abcdefghijklmnopqrstuvwxyz0123456789#@$%^&*?!");
		assertEquals(Constants.LCCHARSWNASC, Constants.LOWCASECHARS_WITH_NUMBERS_AND_SPECIALCHARS);
		assertEquals(Constants.UCCHARSWNASC, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!");
		assertEquals(Constants.UCCHARSWNASC,
			Constants.UPPERCASECHARS_WITH_NUMBERS_AND_SPECIALCHARS);
		assertEquals(Constants.LCUCCHARSWN,
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		assertEquals(Constants.LCUCCHARSWN, Constants.LOWCASECHARS_UPPERCASECHARS_WITH_NUMBERS);
		assertEquals(Constants.LCUCCHARSWNASC,
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!");
		assertEquals(Constants.LCUCCHARSWNASC,
			Constants.LOWCASECHARS_UPPERCASECHARS_WITH_NUMBERS_AND_SPECIALCHARS);
	}

	/**
	 * Test method for evaluate the constants.
	 */
	@Test
	public void testConstants()
	{
		assertEquals(Constants.SPECIALCHARS, "#@$%^&*?!");

		assertEquals(Constants.NUMBERS, "0123456789");

		assertEquals(Constants.LOWCASECHARS, "abcdefghijklmnopqrstuvwxyz");

		assertEquals(Constants.UPPERCASECHARS, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
}
