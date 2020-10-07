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

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.ArrayUtils;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link RandomExtensions}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomExtensionsTest extends BaseTestCase
{

	boolean expected;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@BeforeMethod
	protected void setUp() throws Exception
	{
		super.setUp();
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
		for (int i = 0; i < 10; i++)
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
		for (int i = 0; i < 10; i++)
		{
			final String randomValue = (String)RandomExtensions.getRandomEntry(map);

			actual = values.contains(randomValue);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnumFromObject(Enum)} .
	 */
	@Test
	public void testGetRandomEnum()
	{
		final Gender enumEntry = Gender.FEMALE;
		final Gender randomEnumEntry = RandomExtensions.getRandomEnumFromObject(enumEntry);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnumFromEnumValues(Enum[])} .
	 */
	@Test
	public void testGetRandomEnumArray()
	{
		final Gender[] genders = Gender.values();
		final Gender randomEnumEntry = RandomExtensions.getRandomEnumFromEnumValues(genders);
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnumFromObject(Enum)} .
	 */
	@Test
	public void testGetRandomEnumClass()
	{
		final Gender randomEnumEntry = RandomExtensions.getRandomEnumFromClass(Gender.class);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnumFromClassname(String)}
	 */
	@Test
	public void testGetRandomEnumNull()
	{
		Gender randomEnum = RandomExtensions.getRandomEnumFromObject((Gender)null);
		assertNull(randomEnum);

		randomEnum = RandomExtensions.getRandomEnumFromClassname((String)null);
		assertNull(randomEnum);
	}

	/**
	 * Test method for {@link RandomExtensions#getRandomEnumFromClassname(String)} .
	 */
	@Test
	public void testGetRandomEnumString()
	{
		String enumClassName = "de.alpharogroup.test.objects.enums.Gender";
		Gender randomEnumEntry = RandomExtensions.getRandomEnumFromClassname(enumClassName);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));

		enumClassName = "Gender";
		randomEnumEntry = RandomExtensions.getRandomEnumFromClassname(enumClassName);
		assertNull(randomEnumEntry);

	}

	/**
	 * Test method for {@link RandomExtensions#getRandomHexString(int)}
	 */
	@Test(enabled = true)
	public void testgetRandomHexString()
	{
		String randomHexString = RandomExtensions.getRandomHexString(16);
		assertTrue(randomHexString.length() == 16);
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
		for (int i = 0; i < 10; i++)
		{
			final String randomKey = (String)RandomExtensions.getRandomKey(map);

			actual = keys.contains(randomKey);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#newRandomPixel()}
	 */
	@Test(enabled = true)
	public void testNewRandomPixel()
	{
		int random = RandomExtensions.newRandomPixel();
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomExtensions#newSalt()}
	 */
	@Test(enabled = true)
	public void testNewSalt()
	{
		byte[] newSalt = RandomExtensions.newSalt();
		assertNotNull(newSalt);
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
	 * Test method for {@link RandomExtensions#randomSerialNumber()}
	 */
	@Test(enabled = true)
	public void testRandomSerialNumber()
	{
		for (int i = 0; i < 10; i++)
		{
			BigInteger randomSerialNumber = RandomExtensions.randomSerialNumber();
			assertNotNull(randomSerialNumber);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomToken()}
	 */
	@Test
	public void testRandomToken()
	{
		final String randomToken = RandomExtensions.randomToken();
		assertNotNull(randomToken);
	}

	/**
	 * Test method for {@link RandomExtensions#randomUUID()}
	 */
	@Test
	public void testRandomUUID()
	{
		UUID randomUUID = RandomExtensions.randomUUID();
		assertNotNull(randomUUID);
	}

	/**
	 * Test method for {@link RandomExtensions} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomExtensions.class);
	}

}
