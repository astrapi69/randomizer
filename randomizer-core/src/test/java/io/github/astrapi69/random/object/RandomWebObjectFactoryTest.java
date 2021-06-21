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
import static org.testng.Assert.assertNotNull;

import java.nio.CharBuffer;
import java.util.Optional;

import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.astrapi69.BaseTestCase;
import io.github.astrapi69.random.RandomCharacters;

/**
 * The unit test class for the class {@link RandomWebObjectFactory}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomWebObjectFactoryTest extends BaseTestCase
{

	boolean expected;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@AfterMethod
	public void tearDown() throws Exception
	{
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#getInfomailFromWebsite(java.lang.String)} .
	 */
	@Test
	public void testGetInfomailFromWebsite()
	{
		final CharBuffer charBuffer = CharBuffer
			.allocate(RandomCharacters.lowcaseWithNumbers.getCharacters().length());
		charBuffer.put(RandomCharacters.lowcaseWithNumbers.getCharacters());
		final String url = RandomWebObjectFactory.randomWebsite();
		final String emailprefix = "info@";

		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final String randomInfomail = RandomWebObjectFactory.getInfomailFromWebsite(url);
			actual = randomInfomail.startsWith(emailprefix);

			assertEquals(expected, actual.booleanValue());

			actual = randomInfomail.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
		String shortUrl = url.substring(7);
		for (int i = 0; i < 10; i++)
		{

			final String randomInfomail = RandomWebObjectFactory.getInfomailFromWebsite(shortUrl);
			actual = randomInfomail.startsWith(emailprefix);

			assertEquals(expected, actual.booleanValue());

			actual = randomInfomail.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#getInfomailFromWebsite(java.lang.String)} .
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testGetInfomailFromWebsiteExEx()
	{
		RandomWebObjectFactory.getInfomailFromWebsite("htp://ww.g.rw");
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomEmail()}.
	 */
	@Test
	public void testGetRandomEmail()
	{
		final CharBuffer charBuffer = CharBuffer
			.allocate(RandomCharacters.lowcaseWithNumbers.getCharacters().length());
		charBuffer.put(RandomCharacters.lowcaseWithNumbers.getCharacters());

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomEmail = RandomWebObjectFactory.randomEmail();
			actual = randomEmail.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomFaxnumber(java.lang.String)}.
	 */
	@Test
	public void testGetRandomFaxnumber()
	{
		final CharBuffer charBuffer = CharBuffer
			.allocate(RandomCharacters.numbers.getCharacters().length());
		charBuffer.put(RandomCharacters.numbers.getCharacters());

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomPhonenumber = RandomWebObjectFactory.randomPhonenumber();
			final String randomFaxnumber = RandomWebObjectFactory
				.randomFaxnumber(randomPhonenumber);
			actual = randomFaxnumber.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomMobilnumber()}.
	 */
	@Test
	public void testGetRandomMobilnumber()
	{
		final CharBuffer charBuffer = CharBuffer
			.allocate(RandomCharacters.numbers.getCharacters().length());
		charBuffer.put(RandomCharacters.numbers.getCharacters());

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomMobilnumber = RandomWebObjectFactory.randomMobilnumber();
			actual = randomMobilnumber.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomPassword(int)}.
	 */
	@Test
	public void testGetRandomPasswordInt()
	{
		final CharBuffer charBuffer = CharBuffer.allocate(26);
		final int length = 5;
		final String chars = RandomCharacters.lowcase.getCharacters();
		charBuffer.put(chars);

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomPassword = RandomWebObjectFactory.randomPassword(length);
			actual = randomPassword.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomPassword(Optional)}.
	 */
	@Test
	public void testGetRandomPasswordOptionalInt()
	{
		final CharBuffer charBuffer = CharBuffer.allocate(26);
		final int length = 5;
		Optional<Integer> optLength = Optional.of(length);
		final String chars = RandomCharacters.lowcase.getCharacters();
		charBuffer.put(chars);

		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final String randomPassword = RandomWebObjectFactory.randomPassword(optLength);
			actual = randomPassword.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}

		optLength = Optional.empty();
		expected = true;
		for (int i = 0; i < 10; i++)
		{
			final String randomPassword = RandomWebObjectFactory.randomPassword(optLength);
			actual = randomPassword.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomPhonenumber()}.
	 */
	@Test
	public void testGetRandomPhonenumber()
	{
		final CharBuffer charBuffer = CharBuffer
			.allocate(RandomCharacters.numbers.getCharacters().length());
		charBuffer.put(RandomCharacters.numbers.getCharacters());

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomPhonenumber = RandomWebObjectFactory.randomPhonenumber();
			actual = randomPhonenumber.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomWebsite()}.
	 */
	@Test
	public void testGetRandomWebsite()
	{
		final CharBuffer charBuffer = CharBuffer
			.allocate(RandomCharacters.lowcaseWithNumbers.getCharacters().length());
		charBuffer.put(RandomCharacters.lowcaseWithNumbers.getCharacters());

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomWebsite = RandomWebObjectFactory.randomWebsite();
			actual = randomWebsite.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomId()}.
	 */
	@Test
	public void testNewRandomId()
	{
		for (int i = 0; i < 10; i++)
		{
			String newRandomId = RandomWebObjectFactory.randomId();
			assertNotNull(newRandomId);
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory#randomName(char[])}
	 */
	@Test
	public void testNewRandomName()
	{
		final CharBuffer charBuffer = CharBuffer
			.allocate(RandomCharacters.lowcaseWithNumbers.getCharacters().length());
		charBuffer.put(RandomCharacters.lowcaseWithNumbers.getCharacters());
		final char[] donatedChars = RandomCharacters.lowcaseWithNumbers.getCharacters()
			.toCharArray();

		expected = true;
		for (int i = 0; i < 100; i++)
		{
			final String randomName = RandomWebObjectFactory.randomName(donatedChars);
			actual = randomName.contains(charBuffer);

			assertEquals(expected, actual.booleanValue());
		}
	}

	/**
	 * Test method for {@link RandomWebObjectFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomWebObjectFactory.class);
	}

}
