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

import static org.testng.AssertJUnit.assertTrue;

import java.security.SecureRandom;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.math.MathExtensions;
import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;

/**
 * The unit test class for the class {@link RandomLongFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomLongFactoryTest
{

	/**
	 * Test method for {@link RandomLongFactory#randomLongBetween(long, long, SecureRandom)}
	 */
	@Test
	public void testRandomLongBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final long randomFloatBetween = RandomLongFactory.randomLongBetween(0L, 10L,
				DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0L, 10L, randomFloatBetween, true, true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for {@link RandomLongFactory#randomLong(long, RandomAlgorithm, SecureRandom)}
	 */
	@Test
	public void testRandomLongLongRandomAlgorithmSecureRandom()
	{
		RandomAlgorithm algorithm;
		long random;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			random = RandomLongFactory.randomLong(5L, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomLongFactory.randomLong(5L, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomLongFactory.randomLong(5L, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
	}

	/**
	 * Test method for {@link RandomLongFactory#randomLong(long, SecureRandom)}
	 */
	@Test
	public void testRandomLongLongSecureRandom()
	{
		long random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomLongFactory.randomLong(5L, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
	}

	/**
	 * Test method for {@link RandomLongFactory#randomLong(SecureRandom)}
	 */
	@Test
	public void testRandomLongSecureRandom()
	{
		long random = RandomLongFactory.randomLong(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Long.MIN_VALUE, Long.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomLongFactory#randomLong()}
	 */
	@Test(enabled = true)
	public void testRandomLong()
	{
		long random = RandomLongFactory.randomLong();
		assertTrue(MathExtensions.isBetween(Long.MIN_VALUE, Long.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomLongFactory#randomLong(long)}
	 */
	@Test(enabled = true)
	public void testRandomLongLong()
	{
		for (int i = 0; i < 10; i++)
		{
			final long randomLong = RandomLongFactory.randomLong(5l);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, randomLong));
		}
	}

	/**
	 * Test method for {@link RandomLongFactory#randomLong(long, RandomAlgorithm)}
	 */
	@Test
	public void testRandomLongRandomAlgorithm()
	{
		RandomAlgorithm algorithm;
		long randomLong;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			randomLong = RandomLongFactory.randomLong(5l, algorithm);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1l, 5l, randomLong));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			randomLong = RandomLongFactory.randomLong(5l, algorithm);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1l, 5l, randomLong));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			randomLong = RandomLongFactory.randomLong(5l, algorithm);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1l, 5l, randomLong));
		}
	}

	/**
	 * Test method for {@link RandomLongFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomLongFactory.class);
	}

}
