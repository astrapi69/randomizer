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

import de.alpharogroup.random.DefaultSecureRandom;
import org.meanbean.test.BeanTester;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.security.SecureRandom;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * The unit test class for the class {@link RandomBigDecimalFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomBigDecimalFactoryTest
{

	/**
	 * Test method for {@link RandomBigDecimalFactory#randomBigDecimal()}
	 */
	@Test
	public void testRandomBigDecimal()
	{
		BigDecimal randomBigDecimal = RandomBigDecimalFactory.randomBigDecimal();
		assertNotNull(randomBigDecimal);
	}

	/**
	 * Test method for {@link RandomBigDecimalFactory#randomBigDecimal(SecureRandom)}
	 */
	@Test
	public void testRandomBigDecimalSecureRandom()
	{
		BigDecimal randomBigDecimal = RandomBigDecimalFactory.randomBigDecimal(DefaultSecureRandom.get());
		assertNotNull(randomBigDecimal);
	}

	/**
	 * Test method for {@link RandomBigDecimalFactory#randomBigDecimal(int, int)}
	 */
	@Test
	public void testRandomBigDecimalIntInt()
	{
		BigDecimal randomBigDecimal = RandomBigDecimalFactory.randomBigDecimal(8, 8);
		assertNotNull(randomBigDecimal);
	}

	/**
	 * Test method for {@link RandomBigDecimalFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomBigDecimalFactory.class);
	}

}
