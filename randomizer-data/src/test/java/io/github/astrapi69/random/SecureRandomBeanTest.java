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
package io.github.astrapi69.random;

import static org.testng.Assert.assertNotNull;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

/**
 * The unit test class for the class {@link SecureRandomBean}
 */
public class SecureRandomBeanTest
{

	/**
	 * Test method for {@link SecureRandomBean#equals(Object)}
	 */
	@Test
	public void equalsContract()
	{
		EqualsVerifier.forClass(SecureRandomBean.class).verify();
	}

	/**
	 * Test method for {@link SecureRandomBean} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		SecureRandomBean model = new SecureRandomBean();
		assertNotNull(model);
		model = new SecureRandomBean("SHA1PRNG", "SUN", System.currentTimeMillis());
		assertNotNull(model);
		model = SecureRandomBean.builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link SecureRandomBean}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(SecureRandomBean.class);
	}

}
