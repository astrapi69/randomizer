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
package de.alpharogroup.random;

import static org.testng.Assert.assertNotNull;

import java.security.SecureRandom;

import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link SecureRandomBuilder}.
 */
public class SecureRandomBuilderTest
{

	/**
	 * Test method for {@link SecureRandomBuilder#build()}.
	 */
	@Test
	public void testBuild() throws Exception
	{
		SecureRandomBuilder instance = SecureRandomBuilder.getInstance();
		assertNotNull(instance);
		SecureRandom secureRandom = instance.build();
		assertNotNull(secureRandom);
	}

	/**
	 * Test method for {@link SecureRandomBuilder#buildQueitly()}.
	 */
	@Test
	public void testBuildQueitly() throws Exception
	{
		SecureRandomBuilder instance = SecureRandomBuilder.getInstance();
		assertNotNull(instance);
		SecureRandom secureRandom = instance.buildQueitly();
		assertNotNull(secureRandom);
	}

	/**
	 * Test method for {@link SecureRandomBuilder#getInstance()}.
	 */
	@Test
	public void testGetInstance() throws Exception
	{
		SecureRandomBuilder instance = SecureRandomBuilder.getInstance();
		assertNotNull(instance);
	}

	/**
	 * Test method for {@link SecureRandomBuilder#getInstance(String)}.
	 */
	@Test
	public void testGetInstanceString() throws Exception
	{
		SecureRandomBuilder instance = SecureRandomBuilder
			.getInstance(SecureRandomBean.DEFAULT_ALGORITHM);
		assertNotNull(instance);
	}

	/**
	 * Test method for {@link SecureRandomBuilder#getInstance(String, String)}.
	 */
	@Test
	public void testGetInstanceStringString() throws Exception
	{
		SecureRandomBuilder instance = SecureRandomBuilder
			.getInstance(SecureRandomBean.DEFAULT_ALGORITHM, SecureRandomBean.DEFAULT_PROVIDER);
		assertNotNull(instance);
	}

}
