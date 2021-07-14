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
package io.github.astrapi69.random.number;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.nio.CharBuffer;
import java.security.SecureRandom;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.RandomCharacters;

/**
 * The unit test class for the class {@link RandomCharFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomCharFactoryTest
{

	/**
	 * Test method for {@link RandomCharFactory#randomChar()}
	 */
	@Test
	public void testRandomChar()
	{
		char randomChar = RandomCharFactory.randomChar();
		assertNotNull(Character.valueOf(randomChar));
	}

	/**
	 * Test method for {@link RandomCharFactory#randomChar(SecureRandom)}
	 */
	@Test
	public void testRandomCharSecureRandom()
	{
		char randomChar = RandomCharFactory.randomChar(DefaultSecureRandom.get());
		assertNotNull(Character.valueOf(randomChar));
	}

	/**
	 * Test method for {@link RandomCharFactory#randomChar(String)}
	 */
	@Test
	public void testRandomCharString()
	{
		boolean actual;
		boolean expected;

		final String string = RandomCharacters.lowcase.getCharacters();

		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final char randomChar = RandomCharFactory.randomChar(string);
			final CharBuffer charBuffer = CharBuffer.allocate(1);
			charBuffer.put(randomChar);
			actual = string.contains(charBuffer);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomCharFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomCharFactory.class);
	}

}
