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
import io.github.astrapi69.random.enums.RandomAlgorithm;

/**
 * The unit test class for the class {@link RandomFloatFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomFloatFactoryTest
{

	/**
	 * Test method for {@link RandomFloatFactory#randomFloat()}
	 */
	@Test(enabled = true)
	public void testRandomFloat()
	{
		float random = RandomFloatFactory.randomFloat();
		assertTrue(MathExtensions.isBetween(Float.MIN_VALUE, Float.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomFloatFactory#randomFloatBetween(float, float)}
	 */
	@Test(enabled = true)
	public void testRandomFloatBetweenFloatFloat()
	{
		float random = RandomFloatFactory.randomFloatBetween(0.0f, 10.0f);
		assertTrue(MathExtensions.isBetween(0.0f, 10.0f, random));
	}

	/**
	 * Test method for {@link RandomFloatFactory#randomFloat(float)}
	 */
	@Test(enabled = true)
	public void testRandomFloatFloat()
	{
		float random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5f);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
	}

	/**
	 * Test method for {@link RandomFloatFactory#randomFloat(float, RandomAlgorithm)}
	 */
	@Test
	public void testRandomFloatRandomAlgorithm()
	{
		RandomAlgorithm algorithm;
		float random;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5f, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5f, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5f, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
	}

	/**
	 * Test method for {@link RandomFloatFactory#randomFloatBetween(float, float, SecureRandom)}
	 */
	@Test
	public void testRandomFloatBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final float randomFloatBetween = RandomFloatFactory.randomFloatBetween(0.0F, 10.0F,
				DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0.0F, 10.0F, randomFloatBetween, true,
				true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for {@link RandomFloatFactory#randomFloat(float, RandomAlgorithm, SecureRandom)}
	 */
	@Test
	public void testRandomFloatFloatRandomAlgorithmSecureRandom()
	{
		RandomAlgorithm algorithm;
		float random;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5F, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1F, 5F, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5F, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1F, 5F, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5F, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1F, 5F, random));
		}
	}

	/**
	 * Test method for {@link RandomFloatFactory#randomFloat(float, SecureRandom)}
	 */
	@Test
	public void testRandomFloatFloatSecureRandom()
	{
		float random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomFloatFactory.randomFloat(5f, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
	}

	/**
	 * Test method for {@link RandomFloatFactory#randomFloat(SecureRandom)}
	 */
	@Test
	public void testRandomFloatSecureRandom()
	{
		float random = RandomFloatFactory.randomFloat(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Float.MIN_VALUE, Float.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomFloatFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomFloatFactory.class);
	}

}
