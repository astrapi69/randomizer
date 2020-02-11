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

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.nio.CharBuffer;
import java.text.ParseException;
import java.util.Map;

import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.RandomCharacters;
import de.alpharogroup.random.enums.RandomAlgorithm;

/**
 * The unit test class for the class {@link RandomPrimitivesExtensions}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomPrimitivesExtensionsTest extends BaseTestCase
{

	/**
	 * Factory method for create a map for count drawn numbers
	 *
	 * @param minVolume
	 *            the min volume
	 * @param maxVolume
	 *            the max volume
	 * @return the new map with the initial values
	 */
	public static Map<Integer, Integer> newNumberCounterMap(int minVolume, int maxVolume)
	{
		Map<Integer, Integer> numberCount = MapFactory.newHashMap();
		for (int i = minVolume; i <= maxVolume; i++)
		{
			numberCount.put(i, 0);
		}
		return numberCount;
	}

	boolean expected;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@AfterMethod
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#getRandomIntBetween(int, int)}
	 */
	@Test
	public void testGetRandomIntBetween()
	{
		for (int i = 0; i < 10; i++)
		{
			final int randomIntBetween = RandomPrimitivesExtensions.getRandomIntBetween(1, 10);
			MathExtensions.isBetween(1, 10, randomIntBetween, true, true);
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomBoolean()}
	 */
	@Test(enabled = true)
	public void testRandomBoolean()
	{
		boolean randomBoolean = RandomPrimitivesExtensions.randomBoolean();
		assertTrue(randomBoolean == true || randomBoolean == false);
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomByte()}
	 */
	@Test
	public void testRandomByte()
	{
		final byte randomByte = RandomPrimitivesExtensions.randomByte();
		assertTrue(
			MathExtensions.isBetween(Byte.MIN_VALUE, Byte.MAX_VALUE, randomByte, true, true));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomByteArray(int)}
	 */
	@Test
	public void testRandomByteArray()
	{
		final byte[] randomByteArray = RandomPrimitivesExtensions.randomByteArray(8);
		assertTrue(randomByteArray.length == 8);
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomChar(String)}
	 */
	@Test
	public void testRandomCharString()
	{
		final String string = RandomCharacters.lowcase.getCharacters();

		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final char randomChar = RandomPrimitivesExtensions.randomChar(string);
			final CharBuffer charBuffer = CharBuffer.allocate(1);
			charBuffer.put(randomChar);
			actual = string.contains(charBuffer);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomDouble()}
	 */
	@Test(enabled = true)
	public void testRandomDouble()
	{
		double random = RandomPrimitivesExtensions.randomDouble();
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomDoubleBetween(double, double)}
	 */
	@Test(enabled = true)
	public void testRandomDoubleBetweenDoubleDouble()
	{
		double random = RandomPrimitivesExtensions.randomDoubleBetween(0.0, 10.0);
		assertTrue(MathExtensions.isBetween(0.0, 10.0, random));
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesExtensions#randomDoubleBetween(double, double, String)}
	 *
	 * @throws ParseException
	 */
	@Test(enabled = true)
	public void testRandomDoubleBetweenDoubleDoubleString() throws ParseException
	{
		double random = RandomPrimitivesExtensions.randomDoubleBetween(10000.0, 100000.0,
			"###,###.###");
		assertTrue(MathExtensions.isBetween(10000.0, 100000.0, random, true, true));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomDouble(double)}
	 */
	@Test(enabled = true)
	public void testRandomDoubleDouble()
	{
		double random = RandomPrimitivesExtensions
			.randomDouble(RandomPrimitivesExtensions.randomDoubleBetween(0.0, 10.0));
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomDouble(double, RandomAlgorithm)}
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
			random = RandomPrimitivesExtensions.randomDouble(5d, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesExtensions.randomDouble(5d, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesExtensions.randomDouble(5d, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1d, 5d, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomFloat()}
	 */
	@Test(enabled = true)
	public void testRandomFloat()
	{
		float random = RandomPrimitivesExtensions.randomFloat();
		assertTrue(MathExtensions.isBetween(Float.MIN_VALUE, Float.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomFloatBetween(float, float)}
	 */
	@Test(enabled = true)
	public void testRandomFloatBetweenFloatFloat()
	{
		float random = RandomPrimitivesExtensions.randomFloatBetween(0.0f, 10.0f);
		assertTrue(MathExtensions.isBetween(0.0f, 10.0f, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomFloatBetween(float, float, String)}
	 *
	 * @throws ParseException
	 *             is thrown if the beginning of the specified string cannot be parsed
	 */
	@Test(enabled = true)
	public void testRandomFloatBetweenFloatFloatString() throws ParseException
	{
		float random = RandomPrimitivesExtensions.randomFloatBetween(0.0f, 10.0f, "###,###.###");
		assertTrue(MathExtensions.isBetween(0.0f, 10.0f, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomFloat(float)}
	 */
	@Test(enabled = true)
	public void testRandomFloatFloat()
	{
		float random;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesExtensions.randomFloat(5f);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomFloat(float, RandomAlgorithm)}
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
			random = RandomPrimitivesExtensions.randomFloat(5f, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesExtensions.randomFloat(5f, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			random = RandomPrimitivesExtensions.randomFloat(5f, algorithm);
			assertTrue("random result is " + random + " but should be between 0-4.",
				MathExtensions.isBetween(-1f, 5f, random));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomInt()}
	 */
	@Test(enabled = true)
	public void testRandomInt()
	{
		int random = RandomPrimitivesExtensions.randomInt();
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomIntBetween(int, int)}
	 */
	@Test
	public void testRandomIntBetween()
	{
		for (int i = 0; i < 10; i++)
		{
			final int randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10);
			MathExtensions.isBetween(1, 9, randomIntBetween, true, true);
		}
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesExtensions#randomIntBetween(int, int, boolean, boolean)}
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
	 * Test method for {@link RandomPrimitivesExtensions#randomInt(int)}
	 */
	@Test
	public void testRandomIntInt()
	{
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomPrimitivesExtensions.randomInt(5);
			assertTrue("randomInt result is " + randomInt + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, randomInt));
		}
	}

	/**
	 * Test method for
	 * {@link RandomPrimitivesExtensions#randomInt(int, de.alpharogroup.random.enums.RandomAlgorithm)}
	 */
	@Test
	public void testRandomIntRandomAlgorithm()
	{
		RandomAlgorithm algorithm;
		// scenario with RandomAlgorithm.MATH_ABS
		algorithm = RandomAlgorithm.MATH_ABS;
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomPrimitivesExtensions.randomInt(5, algorithm);
			assertTrue("randomInt result is " + randomInt + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, randomInt));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomPrimitivesExtensions.randomInt(5, algorithm);
			assertTrue("randomInt result is " + randomInt + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, randomInt));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			final int randomInt = RandomPrimitivesExtensions.randomInt(5, algorithm);
			assertTrue(
				"randomInt result is " + randomInt
					+ " but should be between Integer.MIN_VALUE-Integer.MAX_VALUE.",
				MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, randomInt));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomLong()}
	 */
	@Test(enabled = true)
	public void testRandomLong()
	{
		long random = RandomPrimitivesExtensions.randomLong();
		assertTrue(MathExtensions.isBetween(Long.MIN_VALUE, Long.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomLong(long)}
	 */
	@Test(enabled = true)
	public void testRandomLongLong()
	{
		for (int i = 0; i < 10; i++)
		{
			final long randomLong = RandomPrimitivesExtensions.randomLong(5l);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, randomLong));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomLong(long, RandomAlgorithm)}
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
			randomLong = RandomPrimitivesExtensions.randomLong(5l, algorithm);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1l, 5l, randomLong));
		}
		// scenario with RandomAlgorithm.MATH_RANDOM
		algorithm = RandomAlgorithm.MATH_RANDOM;
		for (int i = 0; i < 10; i++)
		{
			randomLong = RandomPrimitivesExtensions.randomLong(5l, algorithm);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1l, 5l, randomLong));
		}
		// scenario with RandomAlgorithm.RANDOM
		algorithm = RandomAlgorithm.RANDOM;
		for (int i = 0; i < 10; i++)
		{
			randomLong = RandomPrimitivesExtensions.randomLong(5l, algorithm);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1l, 5l, randomLong));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions#randomShort()}
	 */
	@Test(enabled = true)
	public void testRandomShort()
	{
		for (int i = 0; i < 10; i++)
		{
			short randomShort = RandomPrimitivesExtensions.randomShort();
			assertTrue(MathExtensions.isBetween(-32768, 32767, randomShort, true, true));
		}
	}

	private void testScenarioRandomIntBetween(int start, int end, int minVolume, int maxVolume,
		boolean includeMin, boolean includeMax, int iterations)
	{
		Map<Integer, Integer> testMap = newNumberCounterMap(minVolume, maxVolume);
		for (int i = 0; i < iterations; i++)
		{
			final int randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(start, end,
				includeMin, includeMax);
			testMap.merge(randomIntBetween, 1, Integer::sum);
			MathExtensions.isBetween(minVolume, maxVolume, randomIntBetween, true, true);
		}
		for (int i = minVolume; i <= maxVolume; i++)
		{
			assertTrue(0 < testMap.get(i));
		}
	}

	/**
	 * Test method for {@link RandomPrimitivesExtensions} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomPrimitivesExtensions.class);
	}

}
