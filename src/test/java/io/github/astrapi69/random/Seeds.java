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

import java.security.SecureRandom;

/**
 * Shared settings for the property-based tests (jqwik) of this library.
 * <p>
 * Every property names {@link #SEED}, so the inputs jqwik generates are the same on every run, on
 * every machine and for every mutant PIT tries; the number of tries defaults to 50
 * (src/test/resources/junit-platform.properties), which keeps a PIT run fast. A property that needs
 * a random source gets a {@link SecureRandom} from {@link #secureRandom(long)}, seeded from a value
 * jqwik generated, so the values a generator draws are reproducible too.
 */
public final class Seeds
{

	/** The fixed jqwik seed of every property in this test suite */
	public static final String SEED = "20261017";

	private Seeds()
	{
	}

	/**
	 * Factory method for a reproducible {@link SecureRandom}: SHA1PRNG seeded before its first use
	 * returns the same sequence for the same seed
	 *
	 * @param seed
	 *            the seed
	 * @return the seeded {@link SecureRandom}
	 */
	public static SecureRandom secureRandom(final long seed)
	{
		return SecureRandomFactory.newSecureRandom(SecureRandomBuilder.DEFAULT_ALGORITHM, "SUN",
			seed);
	}
}
