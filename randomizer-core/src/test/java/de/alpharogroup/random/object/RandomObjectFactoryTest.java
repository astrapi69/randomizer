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
package de.alpharogroup.random.object;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.random.enums.RandomAlgorithm;
import de.alpharogroup.test.objects.Person;

/**
 * The unit test class for the class {@link RandomObjectFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomObjectFactoryTest
{

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
