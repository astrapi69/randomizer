package de.alpharogroup.random;

import static org.testng.Assert.assertNotNull;

import java.security.SecureRandom;

import org.testng.annotations.Test;

/**
 * The class {@link SecureRandomBuilderTest}.
 */
public class SecureRandomBuilderTest
{

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
		SecureRandomBuilder instance = SecureRandomBuilder.getInstance(SecureRandomBean.DEFAULT_ALGORITHM);
		assertNotNull(instance);
	}

	/**
	 * Test method for {@link SecureRandomBuilder#getInstance(String, String)}.
	 */
	@Test
	public void testGetInstanceStringString() throws Exception
	{
		SecureRandomBuilder instance = SecureRandomBuilder.getInstance(SecureRandomBean.DEFAULT_ALGORITHM, SecureRandomBean.DEFAULT_PROVIDER);
		assertNotNull(instance);
	}

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

}
