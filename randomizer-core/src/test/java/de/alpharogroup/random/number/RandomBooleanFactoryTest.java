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

import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.DefaultSecureRandom;
import de.alpharogroup.random.enums.RandomAlgorithm;
import org.meanbean.test.BeanTester;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;
import java.util.Map;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

/**
 * The unit test class for the class {@link RandomBooleanFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomBooleanFactoryTest
{

	/**
	 * Test method for {@link RandomBooleanFactory#randomBoolean(SecureRandom)}
	 */
	@Test
	public void testRandomBooleanSecureRandom()
	{
		boolean randomBoolean = RandomBooleanFactory.randomBoolean(DefaultSecureRandom.get());
		assertNotNull(Boolean.valueOf(randomBoolean));
	}

	/**
	 * Test method for {@link RandomBooleanFactory#randomBoolean()}
	 */
	@Test(enabled = true)
	public void testRandomBoolean()
	{
		boolean randomBoolean = RandomBooleanFactory.randomBoolean();
		Assert.assertNotNull(Boolean.valueOf(randomBoolean));
	}

	/**
	 * Test method for {@link RandomBooleanFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomBooleanFactory.class);
	}

}
