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
package de.alpharogroup.random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.reflection.ReflectionExtensions;
import de.alpharogroup.string.StringExtensions;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link RandomExtensions}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomExtensionsTest extends BaseTestCase
{

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(RandomExtensionsTest.class.getName());

	boolean expected;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@BeforeMethod
	protected void setUp() throws Exception
	{
		super.setUp();
		Field sourceField = ReflectionExtensions.getDeclaredField(RandomExtensions.class,
			"secureRandom");
		sourceField.setAccessible(true);
		sourceField.set(null, SecureRandomBean.builder().buildQuietly());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@AfterMethod
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	// /**
	// * Test method for {@link RandomExtensions#randomSerialNumber()}.
	// */
	// @Test
	// public void testRandomSerialNumber()
	// {
	// BigInteger serialNumber = RandomExtensions.randomSerialNumber();
	// assertNotNull(serialNumber);
	// }
	//
	// /**
	// * Test method for {@link
	// RandomExtensions#getRandomPrimitiveByteArray(int)}.
	// */
	// @Test
	// public void testGetRandomPrimitiveByteArray()
	// {
	// final byte[] randomByteArray =
	// RandomExtensions.getRandomPrimitiveByteArray(5);
	// assertNotNull(randomByteArray);
	// }
	//
	// /**
	// * Test method for {@link RandomExtensions#newSalt()}.
	// */
	// @Test
	// public void testNewSalt()
	// {
	// }

	/**
	 * Test method for {@link RandomExtensions#getRandomBigDecimal(int, int)}.
	 */
	@Test(enabled = true)
	public void testGetRandomBigDecimal()
	{
		BigDecimal random = RandomExtensions.getRandomBigDecimal(
			RandomExtensions.randomIntBetween(0, 17), RandomExtensions.randomIntBetween(0, 17));
		assertNotNull(random);
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomByte()}.
	 */
	@Test(enabled = true)
	public void testGetRandomByte()
	{
		Byte random = RandomExtensions.getRandomByte();
		assertTrue(MathExtensions.isBetween(Byte.MIN_VALUE, Byte.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomByteArray(int)}.
	 */
	@Test(enabled = true)
	public void testGetRandomByteArray()
	{
		Byte[] randomByteArray = RandomExtensions.getRandomByteArray(5);
		assertTrue(randomByteArray.length == 5);
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEntry(java.util.List)} .
	 */
	@Test
	public void testGetRandomEntryList()
	{
		final List<String> list = new ArrayList<>();
		list.add("Anton");
		list.add("Kosta");
		list.add("Caesar");
		list.add("Asterios");
		list.add("Anastasia");
		list.add("Katerina");

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomEntry = RandomExtensions.getRandomEntry(list);

			actual = list.contains(randomEntry);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEntry(java.util.Map)} .
	 */
	@Test
	public void testGetRandomEntryMap()
	{
		final Map<String, String> map = new HashMap<>();
		map.put("1", "novalue");
		map.put("2", "somevalue");
		map.put("3", "othervalue");
		map.put("4", "value");
		map.put("5", "value");
		final Collection<String> values = map.values();

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomValue = (String)RandomExtensions.getRandomEntry(map);

			actual = values.contains(randomValue);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnum(Enum)} .
	 */
	@Test
	public void testGetRandomEnum()
	{
		final Gender enumEntry = Gender.FEMALE;
		final Gender randomEnumEntry = RandomExtensions.getRandomEnum(enumEntry);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnum(Enum[])} .
	 */
	@Test
	public void testGetRandomEnumArray()
	{
		final Gender[] genders = Gender.values();
		final Gender randomEnumEntry = RandomExtensions.getRandomEnum(genders);
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnum(Enum)} .
	 */
	@Test
	public void testGetRandomEnumClass()
	{
		final Gender randomEnumEntry = RandomExtensions.getRandomEnum(Gender.class);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnum(String)}
	 */
	@Test
	public void testGetRandomEnumNull()
	{
		Gender randomEnum = RandomExtensions.getRandomEnum((Gender)null);
		assertNull(randomEnum);

		randomEnum = RandomExtensions.getRandomEnum((String)null);
		assertNull(randomEnum);
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnum(String)} .
	 */
	@Test
	public void testGetRandomEnumString()
	{
		String enumClassName = "de.alpharogroup.test.objects.enums.Gender";
		Gender randomEnumEntry = RandomExtensions.getRandomEnum(enumClassName);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));

		enumClassName = "Gender";
		randomEnumEntry = RandomExtensions.getRandomEnum(enumClassName);
		assertNull(randomEnumEntry);

	}

	/**
	 * Test method for {@link RandomExtensions#getRandomFloat(int, int)} .
	 */
	@Test
	public void testGetRandomFloat()
	{
		final int beforeComma = 2;
		final int afterComma = 4;
		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final float randomFloat = RandomExtensions.getRandomFloat(afterComma, beforeComma);

			actual = 0 < randomFloat;
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomKey(java.util.Map)} .
	 */
	@Test
	public void testGetRandomKey()
	{
		final Map<String, String> map = new HashMap<>();
		map.put("1", "novalue");
		map.put("2", "somevalue");
		map.put("3", "othervalue");
		map.put("4", "value");
		map.put("5", "value");
		final Set<String> keys = map.keySet();
		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomKey = (String)RandomExtensions.getRandomKey(map);

			actual = keys.contains(randomKey);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomNumericString()}.
	 */
	@Test(enabled = true)
	public void testGetRandomNumericString()
	{
		String randomNumericString = RandomExtensions.getRandomNumericString();
		assertNotNull(randomNumericString);
		assertTrue(StringExtensions.isNumber(randomNumericString));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomPrimitiveByteArray(int)}.
	 */
	@Test(enabled = true)
	public void testGetRandomPrimitiveByteArray()
	{
		byte[] randomPrimitiveByteArray = RandomExtensions.getRandomPrimitiveByteArray(5);
		assertTrue(randomPrimitiveByteArray.length == 5);
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomString(int)}.
	 */
	@Test(enabled = true)
	public void testGetRandomStringInt()
	{
		String randomString = RandomExtensions.getRandomString(10);
		assertNotNull(randomString);
		assertTrue(randomString.length() < 11);
	}

	/**
	 * Test method for {@link RandomExtensions#newRandomPixel()}.
	 */
	@Test(enabled = true)
	public void testNewRandomPixel()
	{
		int random = RandomExtensions.newRandomPixel();
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#newRandomPixel(int, int, int, int)}.
	 */
	@Test(enabled = true)
	public void testNewRandomPixelIntIntIntInt()
	{
		int random = RandomExtensions.newRandomPixel(RandomExtensions.randomInt(256),
			RandomExtensions.randomInt(256), RandomExtensions.randomInt(256),
			RandomExtensions.randomInt(256));
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#newSalt()}.
	 */
	@Test(enabled = true)
	public void testNewSalt()
	{
		byte[] newSalt = RandomExtensions.newSalt();
		assertNotNull(newSalt);
	}

	/**
	 * Test method for {@link RandomExtensions#randomBoolean()}.
	 */
	@Test(enabled = true)
	public void testRandomBoolean()
	{
		boolean randomBoolean = RandomExtensions.randomBoolean();
		assertTrue(randomBoolean == true || randomBoolean == false);
	}

	/**
	 * Test method for {@link RandomExtensions#randomByteArray(int)}.
	 */
	@Test
	public void testRandomByteArray()
	{
		final byte[] randomByteArray = RandomExtensions.randomByteArray(8);
		logger.debug(new String(randomByteArray, Charset.forName("UTF-8")));
	}

	/**
	 * Test method for {@link RandomExtensions#randomChar(java.lang.String)} .
	 */
	@Test
	public void testRandomCharString()
	{
		final String string = Constants.LOWCASECHARS;

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final char randomChar = RandomExtensions.randomChar(string);
			final CharBuffer charBuffer = CharBuffer.allocate(1);
			charBuffer.put(randomChar);
			actual = string.contains(charBuffer);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomDouble(double)}.
	 */
	@Test(enabled = true)
	public void testRandomDouble()
	{
		double random = RandomExtensions
			.randomDouble(RandomExtensions.randomDoubleBetween(0.0, 10.0));
		assertTrue(MathExtensions.isBetween(Double.MIN_VALUE, Double.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#randomDoubleBetween(double, double)}.
	 */
	@Test(enabled = true)
	public void testRandomDoubleBetweenDoubleDouble()
	{
		double random = RandomExtensions.randomDoubleBetween(0.0, 10.0);
		assertTrue(MathExtensions.isBetween(0.0, 10.0, random));
	}

	/**
	 * Test method for {@link RandomExtensions#randomDoubleBetween(double, double, String)}.
	 * 
	 * @throws ParseException
	 */
	@Test(enabled = true)
	public void testRandomDoubleBetweenDoubleDoubleString() throws ParseException
	{
		double random = RandomExtensions.randomDoubleBetween(10000.0, 100000.0, "###,###.###");
		assertTrue(MathExtensions.isBetween(10000.0, 100000.0, random, true, true));
	}

	/**
	 * Test method for {@link RandomExtensions#randomFloat()}.
	 */
	@Test(enabled = true)
	public void testRandomFloat()
	{
		float random = RandomExtensions.randomFloat();
		assertTrue(MathExtensions.isBetween(Float.MIN_VALUE, Float.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#randomFloatBetween(float, float)}.
	 */
	@Test(enabled = true)
	public void testRandomFloatBetweenFloatFloat()
	{
		float random = RandomExtensions.randomFloatBetween(0.0f, 10.0f);
		assertTrue(MathExtensions.isBetween(0.0f, 10.0f, random));
	}

	/**
	 * Test method for {@link RandomExtensions#randomFloatBetween(float, float, String)}.
	 *
	 * @throws ParseException
	 *             is thrown if the beginning of the specified string cannot be parsed
	 */
	@Test(enabled = true)
	public void testRandomFloatBetweenFloatFloatString() throws ParseException
	{
		float random = RandomExtensions.randomFloatBetween(0.0f, 10.0f, "###,###.###");
		assertTrue(MathExtensions.isBetween(0.0f, 10.0f, random));
	}

	/**
	 * Test method for {@link RandomExtensions#randomFloat(float)}.
	 */
	@Test(enabled = true)
	public void testRandomFloatFloat()
	{
		float random = RandomExtensions
			.randomFloat(RandomExtensions.randomFloatBetween(0.0f, 10.0f));
		assertTrue(MathExtensions.isBetween(Float.MIN_VALUE, Float.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#randomInt()}.
	 */
	@Test(enabled = false)
	public void testRandomInt()
	{
		int random = RandomExtensions.randomInt();
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link de.alpharogroup.random.RandomExtensions#randomIntBetween(int, int)}.
	 */
	@Test
	public void testRandomIntBetween()
	{
		for (int i = 0; i < 100; i++)
		{
			final int randomIntBetween = RandomExtensions.randomIntBetween(1, 10);
			MathExtensions.isBetween(1, 9, randomIntBetween, true, true);
		}
	}

	/**
	 * Test method for
	 * {@link de.alpharogroup.random.RandomExtensions#randomIntBetween(int, int, boolean, boolean)}.
	 */
	@Test
	public void testRandomIntBetweenBooleanBoolean()
	{
		for (int i = 0; i < 100; i++)
		{
			final int randomIntBetween = RandomExtensions.randomIntBetween(1, 10, false, false);
			MathExtensions.isBetween(2, 9, randomIntBetween, true, true);
		}
		for (int i = 0; i < 100; i++)
		{
			final int randomIntBetween = RandomExtensions.randomIntBetween(1, 10, false, true);
			MathExtensions.isBetween(2, 10, randomIntBetween, true, true);
		}
		for (int i = 0; i < 100; i++)
		{
			final int randomIntBetween = RandomExtensions.randomIntBetween(1, 10, true, false);
			MathExtensions.isBetween(1, 9, randomIntBetween, true, true);
		}
		for (int i = 0; i < 100; i++)
		{
			final int randomIntBetween = RandomExtensions.randomIntBetween(1, 10, true, true);
			MathExtensions.isBetween(1, 10, randomIntBetween, true, true);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomInt(int)}.
	 */
	@Test
	public void testRandomIntInt()
	{
		logger.debug("Generate 100 secure random numbers:");
		for (int i = 0; i < 100; i++)
		{
			final int randomInt = RandomExtensions.randomInt(5);
			assertTrue("randomInt result is " + randomInt + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, randomInt));
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomLong()}.
	 */
	@Test(enabled = true)
	public void testRandomLong()
	{
		long random = RandomExtensions.randomLong();
		assertTrue(MathExtensions.isBetween(Long.MIN_VALUE, Long.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#randomLong(long)}.
	 */
	@Test(enabled = true)
	public void testRandomLongLong()
	{
		logger.debug("Generate 100 secure random numbers:");
		for (int i = 0; i < 100; i++)
		{
			final long randomLong = RandomExtensions.randomLong(5l);
			assertTrue("randomLong result is " + randomLong + " but should be between 0-4.",
				MathExtensions.isBetween(-1, 5, randomLong));
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomToken()} .
	 */
	@Test
	public void testRandomSalt()
	{
		final byte[] randomSalt = RandomExtensions.getRandomSalt(8, Charset.forName("UTF-8"));
		System.out.println(new String(randomSalt));
	}

	/**
	 * Test method for {@link RandomExtensions#randomSerialNumber()}.
	 */
	@Test(enabled = true)
	public void testRandomSerialNumber()
	{
		BigInteger randomSerialNumber = RandomExtensions.randomSerialNumber();
		assertNotNull(randomSerialNumber);
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomString(java.lang.String[])} .
	 */
	@Test
	public void testRandomStringStringArray()
	{
		final String[] array = { "blab", "flih", "klap", "teta", "brut", "gzft", "ccp" };
		final List<String> listFromArray = Arrays.asList(array);
		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomString = RandomExtensions.getRandomString(array);

			actual = listFromArray.contains(randomString);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomString(java.lang.String, int)} .
	 */
	@Test
	public void testRandomStringStringInt()
	{
		final CharBuffer charBuffer = CharBuffer.allocate(45);
		final int length = 5;
		final String chars = Constants.LCCHARSWNASC;
		charBuffer.put(chars);
		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomString = RandomExtensions.getRandomString(chars, length);

			actual = randomString.contains(charBuffer);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomToken()} .
	 */
	@Test
	public void testRandomToken()
	{
		final String randomToken = RandomExtensions.randomToken();
		assertNotNull(randomToken);
	}

	/**
	 * Test method for {@link RandomExtensions} with {@link BeanTester}
	 */
	@Test(enabled = false, expectedExceptions = { BeanTestException.class,
			InvocationTargetException.class, UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomExtensions.class);
	}

}
