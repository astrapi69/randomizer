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
package de.alpharogroup.random.number;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;

import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.BaseTestCase;
import de.alpharogroup.string.StringExtensions;

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
	 * Test method for {@link RandomNumberExtensions#getRandomBigDecimal(int, int)}
	 */
	@Test
	public void testGetRandomBigDecimal()
	{
		int beforeComma;
		int afterComma;
		beforeComma = 3;
		afterComma = 2;
		BigDecimal randomBigDecimal = RandomNumberExtensions.getRandomBigDecimal(afterComma,
			beforeComma);
		assertNotNull(randomBigDecimal);
	}

	/**
	 * Test method for {@link RandomNumberExtensions#getRandomFloatString(int, int)}
	 */
	@Test(enabled = true)
	public void testGetRandomFloatString()
	{
		int beforeComma;
		int afterComma;
		beforeComma = 3;
		afterComma = 2;
		String randomFloatString = RandomNumberExtensions.getRandomFloatString(afterComma,
			beforeComma);
		assertNotNull(randomFloatString);
		Float floatObj = Float.valueOf(randomFloatString);
		assertNotNull(floatObj);
	}

	/**
	 * Test method for {@link RandomNumberExtensions#getRandomNumericString()}
	 */
	@Test(enabled = true)
	public void testGetRandomNumericString()
	{
		String randomNumericString = RandomNumberExtensions.getRandomNumericString();
		assertNotNull(randomNumericString);
		assertTrue(StringExtensions.isNumber(randomNumericString));
	}

	/**
	 * Test method for {@link RandomNumberExtensions#randomBigInteger()}
	 */
	@Test
	public void testRandomBigDecimal()
	{
		BigDecimal randomBigDecimal = RandomNumberExtensions.randomBigDecimal();
		assertNotNull(randomBigDecimal);
	}

	/**
	 * Test method for {@link RandomNumberExtensions#randomBigInteger()}
	 */
	@Test
	public void testRandomBigInteger()
	{
		BigInteger randomBigInteger = RandomNumberExtensions.randomBigInteger();
		assertNotNull(randomBigInteger);
	}

	/**
	 * Test method for {@link RandomNumberExtensions} with {@link BeanTester}
	 */
	@Test(enabled = true, expectedExceptions = { BeanTestException.class,
			InvocationTargetException.class, UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomNumberExtensions.class);
	}

}
