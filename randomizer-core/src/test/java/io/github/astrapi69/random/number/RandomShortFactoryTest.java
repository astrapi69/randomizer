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

import de.alpharogroup.math.MathExtensions;
import io.github.astrapi69.random.DefaultSecureRandom;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import java.security.SecureRandom;

import static org.testng.AssertJUnit.assertTrue;

/**
 * The unit test class for the class {@link RandomShortFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomShortFactoryTest
{

	/**
	 * Test method for {@link RandomShortFactory#randomShort()}
	 */
	@Test(enabled = true)
	public void testRandomShort()
	{
		for (int i = 0; i < 10; i++)
		{
			short randomShort = RandomShortFactory.randomShort();
			assertTrue(MathExtensions.isBetween(-32768, 32767, randomShort, true, true));
		}
	}

	/**
	 * Test method for {@link RandomShortFactory#randomShort(SecureRandom)}
	 */
	@Test
	public void testRandomShortSecureRandom()
	{
		for (int i = 0; i < 10; i++)
		{
			short randomShort = RandomShortFactory.randomShort(DefaultSecureRandom.get());
			assertTrue(MathExtensions.isBetween(-32768, 32767, randomShort, true, true));
		}
	}

	/**
	 * Test method for {@link RandomShortFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomShortFactory.class);
	}

}
