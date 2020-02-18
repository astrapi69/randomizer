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

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.security.SecureRandom;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.DefaultSecureRandom;
import de.alpharogroup.random.enums.RandomAlgorithm;

/**
 * The unit test class for the class {@link RandomPrimitivesFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomPrimitivesFactoryTest
{

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomBoolean(SecureRandom)}
	 */
	@Test
	public void testRandomBoolean()
	{
		boolean randomBoolean = RandomPrimitivesFactory.randomBoolean(DefaultSecureRandom.get());
		assertNotNull(Boolean.valueOf(randomBoolean));
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomByte(SecureRandom)}
	 */
	@Test
	public void testRandomByte()
	{
		final byte randomByte = RandomPrimitivesFactory.randomByte(DefaultSecureRandom.get());
		assertTrue(
			MathExtensions.isBetween(Byte.MIN_VALUE, Byte.MAX_VALUE, randomByte, true, true));
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomByteArray(int, SecureRandom)}
	 */
	@Test
	public void testRandomByteArray()
	{
		final byte[] randomByteArray = RandomPrimitivesFactory.randomByteArray(8,
			DefaultSecureRandom.get());
		assertTrue(randomByteArray.length == 8);
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomChar(SecureRandom)}
	 */
	@Test
	public void testRandomChar()
	{
		char randomChar = RandomPrimitivesFactory.randomChar(DefaultSecureRandom.get());
		assertNotNull(Character.valueOf(randomChar));
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesFactory#randomDouble(double, RandomAlgorithm, SecureRandom)}
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
			random = RandomPrimitivesFactory.randomDouble(5d, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomDouble(5d, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomDouble(5d, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomDouble(double, SecureRandom)}
	 */
	@Test
	public void testRandomDoubleDoubleSecureRandom()
	{
		double random = RandomPrimitivesFactory.randomDouble(
			RandomPrimitivesExtensions.randomDoubleBetween(0.0, 10.0), DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomDouble(SecureRandom)}
	 */
	@Test
	public void testRandomDoubleSecureRandom()
	{
		double random = RandomPrimitivesFactory.randomDouble(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesFactory#randomDoubleBetween(double, double, SecureRandom)}
	 */
	@Test
	public void testRandomDoubleBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final double randomDoubleBetween = RandomPrimitivesFactory.randomDoubleBetween(0.0,
				10.0, DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0.0, 10.0, randomDoubleBetween, true,
				true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesFactory#randomFloat(float, RandomAlgorithm, SecureRandom)}
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
			random = RandomPrimitivesFactory.randomFloat(5F, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1F, 5F, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomFloat(5F, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1F, 5F, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomFloat(5F, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1F, 5F, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomFloat(float, SecureRandom)}
	 */
	@Test
	public void testRandomFloatFloatSecureRandom()
	{
		float random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomFloat(5f, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomFloat(SecureRandom)}
	 */
	@Test
	public void testRandomFloatSecureRandom()
	{
		float random = RandomPrimitivesFactory.randomFloat(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Float.MIN_VALUE, Float.MAX_VALUE, random));
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesFactory#randomFloatBetween(float, float, SecureRandom)}
	 */
	@Test
	public void testRandomFloatBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final float randomFloatBetween = RandomPrimitivesFactory.randomFloatBetween(0.0F, 10.0F,
				DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0.0F, 10.0F, randomFloatBetween, true,
				true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomInt(int, RandomAlgorithm, SecureRandom)}
	 */
	@Test
	public void testRandomIntIntRandomAlgorithmSecureRandom()
	{
		RandomAlgorithm algorithm;
		int random;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomInt(5, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomInt(5, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomInt(5, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomInt(int, SecureRandom)}
	 */
	@Test
	public void testRandomIntIntSecureRandom()
	{
		int random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomInt(5, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomInt(SecureRandom)}
	 */
	@Test
	public void testRandomIntSecureRandom()
	{
		int random = RandomPrimitivesFactory.randomInt(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomIntBetween(int, int, SecureRandom)}
	 */
	@Test
	public void testRandomIntBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final int randomIntBetween = RandomPrimitivesFactory.randomIntBetween(0, 10,
				DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0, 10, randomIntBetween, true, true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesFactory#randomLong(long, RandomAlgorithm, SecureRandom)}
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
			random = RandomPrimitivesFactory.randomLong(5L, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomLong(5L, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomLong(5L, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomLong(long, SecureRandom)}
	 */
	@Test
	public void testRandomLongLongSecureRandom()
	{
		long random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesFactory.randomLong(5L, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1L, 5L, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomLong(SecureRandom)}
	 */
	@Test
	public void testRandomLongSecureRandom()
	{
		long random = RandomPrimitivesFactory.randomLong(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Long.MIN_VALUE, Long.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomLongBetween(long, long, SecureRandom)}
	 */
	@Test
	public void testRandomLongBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final long randomFloatBetween = RandomPrimitivesFactory.randomLongBetween(0L, 10L,
				DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0L, 10L, randomFloatBetween, true,
				true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory#randomShort(SecureRandom)}
	 */
	@Test
	public void testRandomShort()
	{
		for (int i = 0; i < 10; i++)
		{
			short randomShort = RandomPrimitivesFactory.randomShort(DefaultSecureRandom.get());
			assertTrue(MathExtensions.isBetween(-32768, 32767, randomShort, true, true));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomPrimitivesFactory.class);
	}
}
