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

import java.security.SecureRandom;

import javax.annotation.Nonnull;

/**
 * The class {@link SecureRandomBuilder} builds a {@link SecureRandom} from the given algorithm and
 * provider. If nothing is set the default {@link SecureRandom} object with the default algorithm
 * will be build.
 */
public final class SecureRandomBuilder
{

	/** The Constant DEFAULT_ALGORITHM */
	public static final String DEFAULT_ALGORITHM = "SHA1PRNG";

	/**
	 * Gets an instance of {@link SecureRandomBuilder} with the default algorithm and provider
	 *
	 * @return the new {@link SecureRandomBuilder} object
	 */
	public static SecureRandomBuilder getInstance()
	{
		return SecureRandomBuilder.newInstance();
	}

	/**
	 * Gets an instance of {@link SecureRandomBuilder} from the given algorithm and provider
	 *
	 * @param algorithm
	 *            the algorithm
	 * @return the new {@link SecureRandomBuilder} object
	 */
	public static SecureRandomBuilder getInstance(final String algorithm)
	{
		return SecureRandomBuilder.newInstance().algorithm(algorithm);
	}

	/**
	 * Gets an instance of {@link SecureRandomBuilder} from the given algorithm and provider
	 *
	 * @param algorithm
	 *            the algorithm
	 * @param provider
	 *            the provider
	 * @return the new {@link SecureRandomBuilder} object
	 */
	public static SecureRandomBuilder getInstance(final String algorithm, final String provider)
	{
		return SecureRandomBuilder.newInstance().algorithm(algorithm).provider(provider);
	}

	/**
	 * Gets an instance of {@link SecureRandomBuilder} from the given algorithm and provider
	 *
	 * @param algorithm the algorithm
	 * @param provider  the provider
	 * @param seed      the seed
	 * @return the new {@link SecureRandomBuilder} object
	 */
	public static SecureRandomBuilder getInstance(final String algorithm, final String provider,
		final long seed)
	{
		return SecureRandomBuilder.newInstance().algorithm(algorithm).provider(provider).seed(seed);
	}

	/**
	 * Gets an new instance of {@link SecureRandomBuilder} for build a {@link SecureRandom} object
	 *
	 * @return the {@link SecureRandomBuilder}
	 */
	private static SecureRandomBuilder newInstance()
	{
		return new SecureRandomBuilder();
	}

	/** The algorithm. */
	private String algorithm;

	/** The provider. */
	private String provider;

	/** The seed. */
	private long seed = System.currentTimeMillis();

	/**
	 * Instantiates a new {@link SecureRandomBuilder}
	 */
	private SecureRandomBuilder()
	{
	}

	/**
	 * Sets the algorithm.
	 *
	 * @param algorithm
	 *            the algorithm
	 * @return this {@link SecureRandomBuilder} object. For chaining.
	 */
	public SecureRandomBuilder algorithm(@Nonnull final String algorithm)
	{
		this.algorithm = algorithm;
		return this;
	}

	/**
	 * Builds a {@link SecureRandom} from the given algorithm and provider. If nothing is set the
	 * default {@link SecureRandom} object with the default algorithm will be build.
	 *
	 * @return the new {@link SecureRandom} object
	 */
	public SecureRandom build()
	{
		return RandomFactory.newSecureRandom(algorithm, provider, seed);
	}

	/**
	 * Sets the provider.
	 *
	 * @param provider
	 *            the provider
	 * @return this {@link SecureRandomBuilder} object. For chaining.
	 */
	public SecureRandomBuilder provider(@Nonnull final String provider)
	{
		this.provider = provider;
		return this;
	}

	/**
	 * Sets the seed.
	 *
	 * @param seed
	 *            the seed
	 * @return this {@link SecureRandomBuilder} object. For chaining.
	 */
	public SecureRandomBuilder seed(final long seed)
	{
		this.seed = seed;
		return this;
	}
}
