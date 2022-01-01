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
package io.github.astrapi69.random.object;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.github.astrapi69.test.objects.PrimitiveArrays;
import org.apache.commons.lang3.ArrayUtils;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import io.github.astrapi69.math.MathExtensions;
import io.github.astrapi69.random.enums.RandomAlgorithm;
import io.github.astrapi69.test.objects.Person;
import io.github.astrapi69.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link RandomObjectFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomObjectFactoryTest
{

	boolean actual;
	boolean expected;

	/**
	 * Test method for {@link RandomObjectFactory#randomListEntry(java.util.List)} .
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
			final String randomEntry = RandomObjectFactory.randomListEntry(list);

			actual = list.contains(randomEntry);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomMapEntry(java.util.Map)} .
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
			final String randomValue = (String)RandomObjectFactory.randomMapEntry(map);

			actual = values.contains(randomValue);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomEnumFromObject(Enum)} .
	 */
	@Test
	public void testRandomEnum()
	{
		final Gender enumEntry = Gender.FEMALE;
		final Gender randomEnumEntry = RandomObjectFactory.randomEnumFromObject(enumEntry);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomEnumFromEnumValues(Enum[])} .
	 */
	@Test
	public void testRandomEnumArray()
	{
		final Gender[] genders = Gender.values();
		final Gender randomEnumEntry = RandomObjectFactory.randomEnumFromEnumValues(genders);
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomEnumFromObject(Enum)} .
	 */
	@Test
	public void testRandomEnumClass()
	{
		final Gender randomEnumEntry = RandomObjectFactory.randomEnumFromClass(Gender.class);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomEnumFromClassname(String)}
	 */
	@Test
	public void testRandomEnumNull()
	{
		Gender randomEnum = RandomObjectFactory.randomEnumFromObject((Gender)null);
		assertNull(randomEnum);

		randomEnum = RandomObjectFactory.randomEnumFromClassname((String)null);
		assertNull(randomEnum);
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomEnumFromClassname(String)} .
	 */
	@Test
	public void testRandomEnumString()
	{
		String enumClassName = "io.github.astrapi69.test.objects.enums.Gender";
		Gender randomEnumEntry = RandomObjectFactory.randomEnumFromClassname(enumClassName);

		final Gender[] genders = Gender.values();
		assertTrue("Enum value should contain the random value.",
			ArrayUtils.contains(genders, randomEnumEntry));

		enumClassName = "Gender";
		randomEnumEntry = RandomObjectFactory.randomEnumFromClassname(enumClassName);
		assertNull(randomEnumEntry);

	}

	/**
	 * Test method for {@link RandomObjectFactory#randomKey(java.util.Map)} .
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
			final String randomKey = (String)RandomObjectFactory.randomKey(map);

			actual = keys.contains(randomKey);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomPixel()}
	 */
	@Test(enabled = true)
	public void testRandomPixel()
	{
		int random = RandomObjectFactory.randomPixel();
		assertTrue(MathExtensions.isBetween(Integer.MIN_VALUE, Integer.MAX_VALUE, random));
	}

	/**
	 * Test method for {@link RandomObjectFactory#newSalt()}
	 */
	@Test(enabled = true)
	public void testNewSalt()
	{
		byte[] newSalt = RandomObjectFactory.newSalt();
		assertNotNull(newSalt);
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomSalt(int, Charset)}
	 */
	@Test
	public void testRandomSalt()
	{
		final byte[] randomSalt = RandomObjectFactory.randomSalt(8, Charset.forName("UTF-8"));
		System.out.println(new String(randomSalt));
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomToken()}
	 */
	@Test
	public void testRandomToken()
	{
		final String randomToken = RandomObjectFactory.randomToken();
		assertNotNull(randomToken);
	}

	/**
	 * Test method for {@link RandomObjectFactory#randomUUID()}
	 */
	@Test
	public void testRandomUUID()
	{
		UUID randomUUID = RandomObjectFactory.randomUUID();
		assertNotNull(randomUUID);
	}

	/**
	 * Test method for {@link RandomObjectFactory#newRandomFloat(int, int)}
	 */
	@Test
	public void testGetRandomFloatString()
	{
		float randomFloat = RandomObjectFactory.newRandomFloat(4, 4);
		assertNotNull(Float.valueOf(randomFloat));
	}

	/**
	 * Test method for {@link RandomObjectFactory#newRandomAlgorithm()}
	 */
	@Test(enabled = true)
	public void testNewRandomAlgorithm()
	{
		RandomAlgorithm randomAlgorithm = RandomObjectFactory.newRandomAlgorithm();
		assertNotNull(randomAlgorithm);
	}

	/**
	 * Test method for {@link RandomObjectFactory#newRandomByteObjects(int)}
	 */
	@Test(enabled = true)
	public void testNewRandomByteObjects()
	{
		int actual;
		int expected;
		expected = 5;
		Byte[] randomByteArray = RandomObjectFactory.newRandomByteObjects(expected);
		actual = randomByteArray.length;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link RandomObjectFactory#newRandomObject(Class, String...)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 */
	@Test
	public void testNewRandomObject()
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		Person person = RandomObjectFactory.newRandomObject(Person.class, "$jacocoData");
		assertNotNull(person);
		Person person2 = RandomObjectFactory.newRandomObject(Person.class, "$jacocoData");
		assertNotNull(person2);
		assertNotEquals(person, person2);
	}


	/**
	 * Test method for {@link RandomObjectFactory#newRandomObject(Class, String...)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 * @throws ClassNotFoundException
	 *             is thrown if the class cannot be located
	 */
	@Test
	public void testNewRandomObjectWithObject()
		throws IllegalAccessException, InstantiationException, NoSuchFieldException,
		ClassNotFoundException
	{
		Person person = RandomObjectFactory.newRandomObject(Person.class, "$jacocoData");
		assertNotNull(person);
		Person person2 = RandomObjectFactory.newRandomObject(person, "$jacocoData");
		assertNotNull(person2);
		assertNotEquals(person, person2);
		PrimitiveArrays primitiveArrays = RandomObjectFactory.newRandomObject(PrimitiveArrays.class,
			"$jacocoData");
		assertNotNull(primitiveArrays);

	}

	/**
	 * Test method for {@link RandomObjectFactory#newRandomObject(Class, String...)}
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 */
	@Test
	public void testNewRandomObjectVarargs()
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		Person person = RandomObjectFactory.newRandomObject(Person.class, "name", "$jacocoData");
		assertNotNull(person);
	}

	/**
	 * Test method for {@link RandomObjectFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomObjectFactory.class);
	}

}
