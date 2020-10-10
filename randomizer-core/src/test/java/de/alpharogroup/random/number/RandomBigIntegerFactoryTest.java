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

import de.alpharogroup.random.DefaultSecureRandom;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * The unit test class for the class {@link RandomBigIntegerFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomBigIntegerFactoryTest
{

	/**
	 * Test method for {@link RandomBigIntegerFactory#randomBigInteger()}
	 */
	@Test
	public void testRandomBigInteger()
	{
		BigInteger randomBigInteger = RandomBigIntegerFactory.randomBigInteger();
		assertNotNull(randomBigInteger);
	}

	/**
	 * Test method for {@link RandomBigIntegerFactory#randomBigInteger(SecureRandom)}
	 */
	@Test
	public void testRandomBigIntegerSecureRandom()
	{
		BigInteger randomBigInteger = RandomBigIntegerFactory.randomBigInteger(DefaultSecureRandom.get());
		assertNotNull(randomBigInteger);
	}

	/**
	 * Test method for {@link RandomBigIntegerFactory#randomSerialNumber(SecureRandom)} 
	 */
	@Test(enabled = true)
	public void testRandomSerialNumberSecureRandom()
	{
		for (int i = 0; i < 10; i++)
		{
			BigInteger randomSerialNumber = RandomBigIntegerFactory.randomSerialNumber(DefaultSecureRandom.get());
			assertNotNull(randomSerialNumber);
		}
	}

	/**
	 * Test method for {@link RandomBigIntegerFactory#randomSerialNumber()} 
	 */
	@Test(enabled = true)
	public void testRandomSerialNumber()
	{
		for (int i = 0; i < 10; i++)
		{
			BigInteger randomSerialNumber = RandomBigIntegerFactory.randomSerialNumber();
			assertNotNull(randomSerialNumber);
		}
	}
	/**
	 * Test method for {@link RandomBigIntegerFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomBigIntegerFactory.class);
	}

}
