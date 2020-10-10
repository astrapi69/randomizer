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
package de.alpharogroup.random.number;

import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.DefaultSecureRandom;
import org.meanbean.test.BeanTester;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

/**
 * The unit test class for the class {@link RandomByteFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomByteFactoryTest
{

	/**
	 * Test method for {@link RandomByteFactory#randomByte()}
	 */
	@Test
	public void testRandomByte()
	{
		final byte randomByte = RandomByteFactory.randomByte();
		assertTrue(
				MathExtensions.isBetween(Byte.MIN_VALUE, Byte.MAX_VALUE, randomByte, true, true));
	}

	/**
	 * Test method for {@link RandomByteFactory#randomByte(SecureRandom)}
	 */
	@Test
	public void testRandomByteSecureRandom()
	{
		final byte randomByte = RandomByteFactory.randomByte(DefaultSecureRandom.get());
		assertTrue(
				MathExtensions.isBetween(Byte.MIN_VALUE, Byte.MAX_VALUE, randomByte, true, true));
	}

	/**
	 * Test method for {@link RandomByteFactory#randomByteArray(int)}
	 */
	@Test
	public void testRandomByteArrayInt()
	{
		final byte[] randomByteArray = RandomByteFactory.randomByteArray(8);
		assertTrue(randomByteArray.length == 8);
	}

	/**
	 * Test method for {@link RandomByteFactory#randomByteArray(int, SecureRandom)}
	 */
	@Test
	public void testRandomByteArrayIntSecureRandom()
	{
		final byte[] randomByteArray = RandomByteFactory.randomByteArray(8,
				DefaultSecureRandom.get());
		assertTrue(randomByteArray.length == 8);
	}

	/**
	 * Test method for {@link RandomByteFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomByteFactory.class);
	}

}
