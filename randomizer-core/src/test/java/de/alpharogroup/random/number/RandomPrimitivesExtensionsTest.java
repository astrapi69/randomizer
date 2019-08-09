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

import java.lang.reflect.InvocationTargetException;
import java.nio.CharBuffer;
import java.text.ParseException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.RandomCharacters;

/**
 * The unit test class for the class {@link RandomPrimitivesExtensions}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomPrimitivesExtensionsTest extends BaseTestCase
{

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
		float random = RandomPrimitivesExtensions
			.randomFloat(RandomPrimitivesExtensions.randomFloatBetween(0.0f, 10.0f));
		assertTrue(MathExtensions.isBetween(Float.MIN_VALUE, Float.MAX_VALUE, random));
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
		for (int i = 0; i < 10; i++)
		{
			final int randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, false,
				false);
			MathExtensions.isBetween(2, 9, randomIntBetween, true, true);
		}
		for (int i = 0; i < 10; i++)
		{
			final int randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, false,
				true);
			MathExtensions.isBetween(2, 10, randomIntBetween, true, true);
		}
		for (int i = 0; i < 10; i++)
		{
			final int randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, true,
				false);
			MathExtensions.isBetween(1, 9, randomIntBetween, true, true);
		}
		for (int i = 0; i < 10; i++)
		{
			final int randomIntBetween = RandomPrimitivesExtensions.randomIntBetween(1, 10, true,
				true);
			MathExtensions.isBetween(1, 10, randomIntBetween, true, true);
		}
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

	/**
	 * Test method for {@link RandomPrimitivesExtensions} with {@link BeanTester}
	 */
	@Test(enabled = false, expectedExceptions = { BeanTestException.class,
			InvocationTargetException.class, UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomPrimitivesExtensions.class);
	}

}
