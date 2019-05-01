package de.alpharogroup.random;

import static org.junit.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

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
	 * Test method for {@link RandomObjectFactory#newRandomObject(Class)}
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
		Person person = RandomObjectsExtensions.newRandomObject(Person.class);
		assertNotNull(person);
		Person person2 = RandomObjectsExtensions.newRandomObject(Person.class);
		assertNotNull(person2);
		assertNotEquals(person, person2);
	}

	/**
	 * Test method for {@link RandomObjectFactory} with {@link BeanTester}
	 */
	@Test(expectedExceptions = { BeanTestException.class, InvocationTargetException.class,
			UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomObjectFactory.class);
	}

}
