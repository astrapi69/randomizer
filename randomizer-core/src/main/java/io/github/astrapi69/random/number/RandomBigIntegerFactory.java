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

import io.github.astrapi69.random.DefaultSecureRandom;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Utility class for producing random BigInteger types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomBigIntegerFactory {

	/**
	 * Generates a random {@link BigInteger}
	 *
	 * @return the random {@link BigInteger}
	 */
	public static BigInteger randomBigInteger()
	{
		return randomBigInteger(DefaultSecureRandom.get());
	}

	/**
	 * Generates a random {@link BigInteger}
	 *
	 * @param secureRandom
	 *            the secure random for generation
	 * @return the random {@link BigInteger}
	 */
	public static BigInteger randomBigInteger(SecureRandom secureRandom)
	{
		return new BigInteger(RandomIntFactory.randomInt(180), secureRandom);
	}

	/**
	 * Returns a random serial number that can be used for a serial number.
	 *
	 * @return a random serial number as a {@link BigInteger} object.
	 */
	public static BigInteger randomSerialNumber()
	{
		return randomSerialNumber(DefaultSecureRandom.get());
	}

	/**
	 * Returns a random serial number that can be used for a serial number.
	 *
	 * @param secureRandom
	 *            the secure random for generation
	 * @return a random serial number as a {@link BigInteger} object.
	 */
	public static BigInteger randomSerialNumber(SecureRandom secureRandom)
	{
		long next = secureRandom.nextLong();
		if (next < 0)
		{
			next = next * (-1);
		}
		final BigInteger serialNumber = BigInteger.valueOf(next);
		return serialNumber;
	}

	private RandomBigIntegerFactory() {
	}

}
