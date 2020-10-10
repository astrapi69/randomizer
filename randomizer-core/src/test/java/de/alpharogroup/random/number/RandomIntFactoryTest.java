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

import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.DefaultSecureRandom;
import de.alpharogroup.random.enums.RandomAlgorithm;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import java.security.SecureRandom;
import java.util.Map;

import static org.testng.AssertJUnit.assertTrue;

/**
 * The unit test class for the class {@link RandomIntFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomIntFactoryTest
{

	/**
	 * Test method for {@link RandomIntFactory#randomIntBetween(int, int, SecureRandom)}
	 */
	@Test
	public void testRandomIntBetweenSecureRandom()
	{
		for (int i = 0; i < 100; i++)
		{
			final int randomIntBetween = RandomIntFactory.randomIntBetween(0, 10,
					DefaultSecureRandom.get());
			boolean isBetween = MathExtensions.isBetween(0, 10, randomIntBetween, true, true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for {@link RandomIntFactory#randomIntBetween(int, int)}
	 */
	@Test
	public void testRandomIntBetween()
	{
		for (int i = 0; i < 1000; i++)
		{
			final int randomIntBetween = RandomIntFactory.randomIntBetween(1, 10);
			System.out.println(randomIntBetween);
			boolean isBetween = MathExtensions.isBetween(1, 9, randomIntBetween, true, true);
			assertTrue(isBetween);
		}
	}

	/**
	 * Test method for {@link RandomIntFactory#randomInt(int, RandomAlgorithm, SecureRandom)}
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
			random = RandomIntFactory.randomInt(5, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
					MathExtensions.isBetween(-1, 5, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomIntFactory.randomInt(5, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
					MathExtensions.isBetween(-1, 5, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomIntFactory.randomInt(5, algorithm, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
					MathExtensions.isBetween(-1, 5, random));
		}
	}

	/**
	 * Test method for {@link RandomIntFactory#randomInt(int, SecureRandom)}
	 */
	@Test
	public void testRandomIntIntSecureRandom()
	{
		int random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomIntFactory.randomInt(5, DefaultSecureRandom.get());
			assertTrue("random result is " + random + " but should be between 0-4.",
					MathExtensions.isBetween(-1, 5, random));
		}
	}

	/**
	 * Test method for {@link RandomIntFactory#randomInt(SecureRandom)}
	 */
	@Test
	public void testRandomIntSecureRandom()
	{
		int random = RandomIntFactory.randomInt(DefaultSecureRandom.get());
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomIntFactory#randomInt()}
	 */
	@Test(enabled = true)
	public void testRandomInt()
	{
		int random = RandomIntFactory.randomInt();
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for
	 * {@link RandomIntFactory#randomIntBetween(int, int, boolean, boolean)}
	 */
	@Test
	public void testRandomIntBetweenBooleanBoolean()
	{
		int start;
		int end;
		int iterations;
		int minVolume;
		int maxVolume;
		boolean includeMin;
		boolean includeMax;
		// test data
		start = 1;
		end = 10;
		iterations = 100;
		// new scenario
		minVolume = start + 1;
		maxVolume = end - 1;
		includeMin = false;
		includeMax = false;
		testScenarioRandomIntBetween(start, end, minVolume, maxVolume, includeMin, includeMax,
				iterations);
		// new scenario
		minVolume = start + 1;
		maxVolume = end;
		includeMin = false;
		includeMax = true;
		testScenarioRandomIntBetween(start, end, minVolume, maxVolume, includeMin, includeMax,
				iterations);

		// new scenario
		minVolume = start;
		maxVolume = end - 1;
		includeMin = true;
		includeMax = false;
		testScenarioRandomIntBetween(start, end, minVolume, maxVolume, includeMin, includeMax,
				iterations);

		// new scenario
		minVolume = start;
		maxVolume = end;
		includeMin = true;
		includeMax = true;
		testScenarioRandomIntBetween(start, end, minVolume, maxVolume, includeMin, includeMax,
				iterations);
	}

	/**
	 * Test method for {@link RandomIntFactory#randomInt(int)}
	 */
	@Test
	public void testRandomIntInt()
	{
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomIntFactory.randomInt(5);
			assertTrue("randomInt result is " + randomInt + " but should be between 0-4.",
					MathExtensions.isBetween(-1, 5, randomInt));
		}
	}

	/**
	 * Test method for
	 * {@link RandomIntFactory#randomInt(int, de.alpharogroup.random.enums.RandomAlgorithm)}
	 */
	@Test
	public void testRandomIntRandomAlgorithm()
	{
		RandomAlgorithm algorithm;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomIntFactory.randomInt(5, algorithm);
			assertTrue("randomInt result is " + randomInt + " but should be between 0-4.",
					MathExtensions.isBetween(-1, 5, randomInt));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomIntFactory.randomInt(5, algorithm);
			assertTrue("randomInt result is " + randomInt + " but should be between 0-4.",
					MathExtensions.isBetween(-1, 5, randomInt));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomIntFactory.randomInt(5, algorithm);
			assertTrue(
					"randomInt result is " + randomInt
							+ " but should be between Integer.MIN_VALUE-Integer.MAX_VALUE.",
					MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, randomInt));
		}
	}
	/**
	 * Test method for {@link RandomIntFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomIntFactory.class);
	}

	private void testScenarioRandomIntBetween(int start, int end, int minVolume, int maxVolume,
											  boolean includeMin, boolean includeMax, int iterations)
	{
		Map<Integer, Integer> testMap = MapFactory.newNumberCounterMap(minVolume, maxVolume);
		System.out.println("start:" + start + "\n" + "end:" + end + "\n" + "minVolume:" + minVolume
				+ "\n" + "maxVolume:" + maxVolume + "\n" + "includeMin:" + includeMin + "\n"
				+ "includeMax:" + includeMax + "\n");
		for (int i = 0; i < iterations; i++)
		{
			final int randomIntBetween = RandomIntFactory.randomIntBetween(start, end,
					includeMin, includeMax);
			System.out.println(randomIntBetween);
			testMap.merge(randomIntBetween, 1, Integer::sum);
		}
		for (int i = minVolume; i <= maxVolume; i++)
		{
			assertTrue(0 < testMap.get(i));
		}
	}
}
