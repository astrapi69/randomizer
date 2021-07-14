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

import de.alpharogroup.math.MathExtensions;
import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.enums.RandomAlgorithm;

/**
 * The unit test class for the class {@link RandomDoubleFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomDoubleFactoryTest
{

	/**
	 * Test method for {@link RandomDoubleFactory#randomDouble()}
	 */
	@Test(enabled = true)
	public void testRandomDouble()
	{
		double random = RandomDoubleFactory.randomDouble();
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomDoubleFactory#randomDoubleBetween(double, double)}
	 */
	@Test(enabled = true)
	public void testRandomDoubleBetweenDoubleDouble()
	{
		double random = RandomDoubleFactory.randomDoubleBetween(0.0, 10.0);
		assertTrue(MathExtensions.isBetween(0.0, 10.0, random));
	}

	/**
	 * Test method for {@link RandomDoubleFactory#randomDouble(double)}
	 */
	@Test(enabled = true)
	public void testRandomDoubleDouble()
	{
		double random = RandomDoubleFactory
			.randomDouble(RandomDoubleFactory.randomDoubleBetween(0.0, 10.0));
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomDoubleFactory#randomDouble(double, RandomAlgorithm)}
	 */
	@Test
	public void testRandomDoubleRandomAlgorithm()
	{
		RandomAlgorithm algorithm;
		double random;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			random = RandomDoubleFactory.randomDouble(5d, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomDoubleFactory.randomDouble(5d, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomDoubleFactory.randomDouble(5d, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
	}

	/**
	 * Test method for {@link RandomDoubleFactory#randomDoubleBetween(double, double, SecureRandom)}
	 */
	@Test
	public void testRandomDoubleBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final double randomDoubleBetween = RandomDoubleFactory.randomDoubleBetween(0.0, 10.0,
				DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0.0, 10.0, randomDoubleBetween, true,
				true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for
	 * {@link RandomDoubleFactory#randomDouble(double, RandomAlgorithm, SecureRandom)}
	 */
	@Test
	public void testRandomDoubleDoubleRandomAlgorithmSecureRandom()
	{
		RandomAlgorithm algorithm;
		double random;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			random = RandomDoubleFactory.randomDouble(5d, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomDoubleFactory.randomDouble(5d, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomDoubleFactory.randomDouble(5d, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
	}

	/**
	 * Test method for {@link RandomDoubleFactory#randomDouble(double, SecureRandom)}
	 */
	@Test
	public void testRandomDoubleDoubleSecureRandom()
	{
		double random = RandomDoubleFactory.randomDouble(
			RandomDoubleFactory.randomDoubleBetween(0.0, 10.0), DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomDoubleFactory#randomDouble(SecureRandom)}
	 */
	@Test
	public void testRandomDoubleSecureRandom()
	{
		double random = RandomDoubleFactory.randomDouble(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomDoubleFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomDoubleFactory.class);
	}

}
