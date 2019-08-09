package de.alpharogroup.random;

import java.security.SecureRandom;

import lombok.experimental.UtilityClass;

/**
 * The class {@link DefaultSecureRandom} holds a {@link SecureRandom} with the default algorithm and
 * provider
 */
@UtilityClass
public class DefaultSecureRandom
{

	/** The secure random */
	private static SecureRandom secureRandom;

	static
	{
		secureRandom = SecureRandomFactory.newSecureRandom();
	}

	/**
	 * Gets the secure random
	 *
	 * @return the secure random
	 */
	public static SecureRandom get()
	{
		return secureRandom;
	}
}
