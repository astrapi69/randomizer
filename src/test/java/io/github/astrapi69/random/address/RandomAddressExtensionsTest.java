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
/**
 *
 */
package io.github.astrapi69.random.address;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.random.util.PropertiesLoader;
import io.github.astrapi69.string.StringExtensions;
import io.github.astrapi69.test.base.BaseTestCase;

/**
 * The unit test class for the class {@link RandomAddressExtensions}.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomAddressExtensionsTest extends BaseTestCase
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	@BeforeEach
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@AfterEach
	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Test method for {@link RandomAddressExtensions#getRandomStreet(java.util.Properties)} .
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetRandomStreet() throws IOException
	{
		final Properties germanstreets = PropertiesLoader
			.loadProperties(RandomAddressExtensions.PROP_FILE_STREETS);
		final String germanStreet = RandomAddressExtensions.getRandomStreet(germanstreets);
		actual = germanStreet != null;
		assertTrue(actual);

		actual = germanstreets.contains(germanStreet);
		assertTrue(actual);
	}

	/**
	 * Test method for
	 * {@link RandomAddressExtensions#getRandomStreetWithNumber(java.util.Properties)} .
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetRandomStreetWithNumber() throws IOException
	{
		final Properties germanstreets = PropertiesLoader
			.loadProperties(RandomAddressExtensions.PROP_FILE_STREETS);
		final String germanStreetWithNumber = RandomAddressExtensions
			.getRandomStreetWithNumber(germanstreets);
		actual = germanStreetWithNumber != null;
		assertTrue(actual);
		final String lastChar = germanStreetWithNumber
			.substring(germanStreetWithNumber.length() - 1, germanStreetWithNumber.length());
		actual = StringExtensions.isNumber(lastChar);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RandomAddressExtensions#getRandomZip(java.util.Properties)} .
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetRandomZip() throws IOException
	{
		final Properties germanzips = PropertiesLoader
			.loadProperties(RandomAddressExtensions.PROP_FILE_ZIP_CITIES);

		final String randomZip = RandomAddressExtensions.getRandomZip(germanzips);
		actual = randomZip != null;
		assertTrue(actual);

		actual = StringExtensions.isNumber(randomZip);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RandomAddressExtensions} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomAddressExtensions.class);
	}

}
