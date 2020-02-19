package de.alpharogroup.random.object;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.RandomCharacters;

public class RandomStringFactoryTest
{

	/**
	 * Test method for {@link RandomStringFactory#newRandomString()}
	 */
	@Test
	public void testNewRandomString()
	{
		String randomString = RandomStringFactory.newRandomString();
		assertNotNull(randomString);
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(int)}
	 */
	@Test
	public void testNewRandomStringInt()
	{
		String randomString = RandomStringFactory.newRandomString(10);
		assertNotNull(randomString);
		assertTrue(randomString.length() < 11);
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(int, int)}
	 */
	@Test
	public void testNewRandomStringIntInt()
	{
		String randomString = RandomStringFactory.newRandomString(3, 25);
		assertNotNull(randomString);
		assertTrue(MathExtensions.isBetween(3, 25, randomString.length(), true, true));
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(String, int)}
	 */
	@Test
	public void testNewRandomStringStringInt()
	{
		boolean expected;
		boolean actual;
		final CharBuffer charBuffer = CharBuffer.allocate(45);
		final int length = 5;
		final String chars = RandomCharacters.lowcaseWithNumbersAndSpecial.getCharacters();
		charBuffer.put(chars);
		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final String randomString = RandomStringFactory.newRandomString(chars, length);

			actual = randomString.contains(charBuffer);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomStringFactory#newRandomString(String[])}
	 */
	@Test
	public void testTestNewRandomStringArray()
	{

		boolean expected;
		boolean actual;
		final String[] array = { "blab", "flih", "klap", "teta", "brut", "gzft", "ccp" };
		final List<String> listFromArray = Arrays.asList(array);
		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final String randomString = RandomStringFactory.newRandomString(array);

			actual = listFromArray.contains(randomString);
			assertEquals(actual, expected);
		}
	}

	/**
	 * Test method for {@link RandomStringFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomStringFactory.class);
	}

}