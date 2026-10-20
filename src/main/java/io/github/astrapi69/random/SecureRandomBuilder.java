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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Objects;

/**
 * The class {@link SecureRandomBuilder} builds a {@link SecureRandom} from the given algorithm and
 * provider. If nothing is set the default {@link SecureRandom} object with the default algorithm
 * will be build.
 * <p>
 * Without a {@link #seed(long) seed} the generator seeds itself from the entropy source of the
 * operating system, the first time it is asked for a value. With a seed it draws the same sequence
 * every time: use one to reproduce test data, never for tokens, salts, keys or anything else that
 * has to stay unpredictable.
 */
public final class SecureRandomBuilder
{

	/** The Constant DEFAULT_ALGORITHM */
	public static final String DEFAULT_ALGORITHM = "SHA1PRNG";
	/** The algorithm. */
	private String algorithm;
	/** The provider. */
	private String provider;
	/**
	 * The seed, or null when the generator seeds itself. Until 2026-10-17 the default was the
	 * current time in milliseconds, which made every generator built without a seed reproducible
	 * for anyone who knew roughly when it was built.
	 */
	private Long seed;

	/**
	 * Instantiates a new {@link SecureRandomBuilder}
	 */
	private SecureRandomBuilder()
	{
	}

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
	 * @param algorithm
	 *            the algorithm
	 * @param provider
	 *            the provider
	 * @param seed
	 *            the seed
	 * @return the new {@link SecureRandomBuilder} object
	 */
	public static SecureRandomBuilder getInstance(final String algorithm, final String provider,
		final long seed)
	{
		return SecureRandomBuilder.newInstance().algorithm(algorithm).provider(provider).seed(seed);
	}

	/**
	 * Gets an instance of {@link SecureRandomBuilder} from the given algorithm and provider
	 *
	 * @param algorithm
	 *            the algorithm
	 * @param provider
	 *            the provider
	 * @param seed
	 *            the seed as {@link Date} object
	 * @return the new {@link SecureRandomBuilder} object
	 */
	public static SecureRandomBuilder getInstance(final String algorithm, final String provider,
		final Date seed)
	{
		return SecureRandomBuilder.newInstance().algorithm(algorithm).provider(provider)
			.seed(seed.getTime());
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

	/**
	 * Sets the algorithm.
	 *
	 * @param algorithm
	 *            the algorithm
	 * @return this {@link SecureRandomBuilder} object. For chaining.
	 */
	public SecureRandomBuilder algorithm(final String algorithm)
	{
		Objects.requireNonNull(algorithm);
		this.algorithm = algorithm;
		return this;
	}

	/**
	 * Builds a {@link SecureRandom} from the given algorithm and provider. If nothing is set the
	 * default {@link SecureRandom} object with the default algorithm will be build. The generator
	 * is seeded only when a seed was given; otherwise it seeds itself.
	 *
	 * @return the new {@link SecureRandom} object
	 */
	public SecureRandom build()
	{
		SecureRandom secureRandom = null;
		if (algorithm != null && provider != null)
		{
			try
			{
				secureRandom = SecureRandom.getInstance(algorithm, provider);
			}
			catch (NoSuchAlgorithmException | NoSuchProviderException e)
			{
				throw new RuntimeException(e);
			}
		}
		else if (algorithm != null)
		{
			try
			{
				secureRandom = SecureRandom.getInstance(algorithm);
			}
			catch (NoSuchAlgorithmException e)
			{
				throw new RuntimeException(e);
			}
		}
		if (secureRandom == null)
			try
			{
				secureRandom = SecureRandom.getInstance(SecureRandomBuilder.DEFAULT_ALGORITHM);
			}
			catch (NoSuchAlgorithmException e)
			{
				throw new RuntimeException(e);
			}
		if (seed != null)
		{
			// SecureRandom.setSeed(long) ignores the seed 0. The byte form takes every seed; its
			// bytes are the little-endian order setSeed(long) uses, so every other seed gives the
			// same sequence as through setSeed(long). A seed set before the first value replaces
			// the self-seeding of SHA1PRNG: the sequence is then fully determined by the seed
			secureRandom.setSeed(ByteBuffer.allocate(Long.BYTES).order(ByteOrder.LITTLE_ENDIAN)
				.putLong(seed).array());
		}
		return secureRandom;
	}

	/**
	 * Sets the provider.
	 *
	 * @param provider
	 *            the provider
	 * @return this {@link SecureRandomBuilder} object. For chaining.
	 */
	public SecureRandomBuilder provider(final String provider)
	{
		Objects.requireNonNull(provider);
		this.provider = provider;
		return this;
	}

	/**
	 * Sets the seed. The generator then draws the same sequence on every build, which is what
	 * reproducible test data needs and what tokens, salts and keys must never have.
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
