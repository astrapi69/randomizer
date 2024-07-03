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
package io.github.astrapi69.random.number;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;

import io.github.astrapi69.string.StringExtensions;
import io.github.astrapi69.test.base.BaseTestCase;

/**
 * The unit test class for the class {@link RandomNumberExtensions}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomNumberExtensionsTest extends BaseTestCase
{

	boolean expected;

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
	 * Test method for {@link RandomNumberExtensions#getRandomNumberString(int, int)}
	 */
	@Test
	public void testGetRandomFloatString()
	{
		int beforeComma;
		int afterComma;
		beforeComma = 3;
		afterComma = 2;
		String randomFloatString = RandomNumberExtensions.getRandomNumberString(afterComma,
			beforeComma);
		assertNotNull(randomFloatString);
		Float floatObj = Float.valueOf(randomFloatString);
		assertNotNull(floatObj);
	}

	/**
	 * Test method for {@link RandomNumberExtensions#getRandomNumericString()}
	 */
	@Test
	public void testGetRandomNumericString()
	{
		String randomNumericString = RandomNumberExtensions.getRandomNumericString();
		assertNotNull(randomNumericString);
		assertTrue(StringExtensions.isNumber(randomNumericString));
	}


	/**
	 * Test method for {@link RandomNumberExtensions} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomNumberExtensions.class);
	}

}
