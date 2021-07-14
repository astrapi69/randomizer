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
package io.github.astrapi69.random.object;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.math.MathExtensions;
import io.github.astrapi69.random.RandomCharacters;

public class RandomStringFactoryTest
{

	/**
	 * Test method for {@link RandomStringFactory#randomHexString(int)}
	 */
	@Test(enabled = true)
	public void testRandomHexString()
	{
		String randomHexString = RandomStringFactory.randomHexString(16);
		assertTrue(randomHexString.length() == 16);
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString()}
	 */
	@Test
	public void testNewRandomString()
	{
		String randomString = RandomStringFactory.newRandomString();
		assertNotNull(randomString);
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(int)}
	 */
	@Test
	public void testNewRandomStringInt()
	{
		String randomString = RandomStringFactory.newRandomString(10);
		assertNotNull(randomString);
		assertTrue(randomString.length() < 11);
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomLongString(long)}
	 */
	@Test
	public void testNewRandomLongString()
	{
		String randomString = RandomStringFactory.newRandomLongString(10000L);
		assertNotNull(randomString);
		assertTrue(randomString.length() == 10000);
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomLongString(String, long)}
	 */
	@Test
	public void testNewRandomLongStringLong()
	{
		String randomString = RandomStringFactory.newRandomLongString(10000L);
		assertNotNull(randomString);
		System.out.println(randomString);
		assertTrue(randomString.length() == 10000);
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(int, int)}
	 */
	@Test
	public void testNewRandomStringIntInt()
	{
		String randomString = RandomStringFactory.newRandomString(3, 25);
		assertNotNull(randomString);
		assertTrue(MathExtensions.isBetween(3, 25, randomString.length(), true, true));
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(String, int)}
	 */
	@Test
	public void testNewRandomStringStringInt()
	{
		boolean expected;
		boolean actual;
		final CharBuffer charBuffer = CharBuffer.allocate(45);
		final int length = 5;
		final String chars = RandomCharacters.lowcaseWithNumbersAndSpecial.getCharacters();
		charBuffer.put(chars);
		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final String randomString = RandomStringFactory.newRandomString(chars, length);

			actual = randomString.contains(charBuffer);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(String[])}
	 */
	@Test
	public void testTestNewRandomStringArray()
	{

		boolean expected;
		boolean actual;
		final String[] array = { "blab", "flih", "klap", "teta", "brut", "gzft", "ccp" };
		final List<String> listFromArray = Arrays.asList(array);
		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final String randomString = RandomStringFactory.newRandomString(array);

			actual = listFromArray.contains(randomString);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomStringFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomStringFactory.class);
	}

}
