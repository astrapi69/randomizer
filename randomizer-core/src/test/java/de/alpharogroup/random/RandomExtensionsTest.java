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
	 * Test method for {@link RandomExtensions#randomListEntry(java.util.List)} .
	 */
	@Test
	public void testRandomListEntry()
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
			final String randomEntry = RandomExtensions.randomListEntry(list);

			actual = list.contains(randomEntry);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomMapEntry(java.util.Map)} .
	 */
	@Test
	public void testRandomMapEntry()
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
			final String randomValue = (String)RandomExtensions.randomMapEntry(map);

			actual = values.contains(randomValue);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomEnumFromObject(Enum)} .
	 */
	@Test
	public void testRandomEnum()
	{
		final Gender enumEntry = Gender.FEMALE;
		final Gender randomEnumEntry = RandomExtensions.randomEnumFromObject(enumEntry);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#randomEnumFromEnumValues(Enum[])} .
	 */
	@Test
	public void testRandomEnumArray()
	{
		final Gender[] genders = Gender.values();
		final Gender randomEnumEntry = RandomExtensions.randomEnumFromEnumValues(genders);
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#randomEnumFromObject(Enum)} .
	 */
	@Test
	public void testRandomEnumClass()
	{
		final Gender randomEnumEntry = RandomExtensions.randomEnumFromClass(Gender.class);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomExtensions#randomEnumFromClassname(String)}
	 */
	@Test
	public void testRandomEnumNull()
	{
		Gender randomEnum = RandomExtensions.randomEnumFromObject((Gender)null);
		assertNull(randomEnum);

		randomEnum = RandomExtensions.randomEnumFromClassname((String)null);
		assertNull(randomEnum);
	}

	/**
	 * Test method for {@link RandomExtensions#randomEnumFromClassname(String)} .
	 */
	@Test
	public void testRandomEnumString()
	{
		String enumClassName = "de.alpharogroup.test.objects.enums.Gender";
		Gender randomEnumEntry = RandomExtensions.randomEnumFromClassname(enumClassName);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));

		enumClassName = "Gender";
		randomEnumEntry = RandomExtensions.randomEnumFromClassname(enumClassName);
		assertNull(randomEnumEntry);

	}

	/**
	 * Test method for {@link RandomExtensions#randomHexString(int)}
	 */
	@Test(enabled = true)
	public void testRandomHexString()
	{
		String randomHexString = RandomExtensions.randomHexString(16);
		assertTrue(randomHexString.length() == 16);
	}

	/**
	 * Test method for {@link RandomExtensions#randomKey(java.util.Map)} .
	 */
	@Test
	public void testRandomKey()
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
			final String randomKey = (String)RandomExtensions.randomKey(map);

			actual = keys.contains(randomKey);
			assertEquals(actual.booleanValue(), expected);
		}
	}

	/**
	 * Test method for {@link RandomExtensions#randomPixel()}
	 */
	@Test(enabled = true)
	public void testRandomPixel()
	{
		int random = RandomExtensions.randomPixel();
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
	 * Test method for {@link RandomExtensions#randomSalt(int, Charset)}
	 */
	@Test
	public void testRandomSalt()
	{
		final byte[] randomSalt = RandomExtensions.randomSalt(8, Charset.forName("UTF-8"));
		System.out.println(new String(randomSalt));
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
