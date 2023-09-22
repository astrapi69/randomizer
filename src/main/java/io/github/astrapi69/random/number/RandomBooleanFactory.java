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

import java.security.SecureRandom;
import java.util.Objects;

import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;

/**
 * Utility class for producing random primitive boolean types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomBooleanFactory
{

	private RandomBooleanFactory()
	{
	}

	/**
	 * Returns a random boolean.
	 *
	 * @return The random boolean.
	 */
	public static boolean randomBoolean()
	{
		return randomBoolean(DefaultSecureRandom.get());
	}

	/**
	 * Returns a random boolean.
	 *
	 * @param secureRandom
	 *            the secure random for boolean generation
	 *
	 * @return The random boolean.
	 */
	public static boolean randomBoolean(SecureRandom secureRandom)
	{
		return RandomIntFactory.randomInt(2, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom)) == 0;
	}

}
