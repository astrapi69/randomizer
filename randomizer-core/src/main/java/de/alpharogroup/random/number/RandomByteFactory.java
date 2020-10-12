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
package de.alpharogroup.random.number;

import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.DefaultSecureRandom;
import de.alpharogroup.random.SecureRandomFactory;
import de.alpharogroup.random.enums.RandomAlgorithm;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

/**
 * Utility class for producing random primitive byte types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomByteFactory
{

	/**
	 * The Method randomByte() selects a random byte.
	 *
	 * @return The random byte.
	 */
	public static byte randomByte()
	{
		return randomByte(DefaultSecureRandom.get());
	}

	/**
	 * The Method randomByte() selects a random byte.
	 *
	 * @param secureRandom
	 *            the secure random for byte generation
	 *
	 * @return The random byte.
	 */
	public static byte randomByte(SecureRandom secureRandom)
	{
		return (byte)RandomIntFactory.randomInt(255, RandomAlgorithm.SECURE_RANDOM,
				Objects.requireNonNull(secureRandom));
	}

	/**
	 * The Method randomByteArray(int) generates a random byte array.
	 *
	 * @param length
	 *            the length.
	 * @return the byte[]
	 */
	public static byte[] randomByteArray(final int length)
	{
		return randomByteArray(length, DefaultSecureRandom.get());
	}

	/**
	 * The Method randomByteArray(int) generates a random byte array.
	 *
	 * @param length
	 *            the length
	 * @param secureRandom
	 *            the secure random for byte generation
	 * @return the byte[]
	 */
	public static byte[] randomByteArray(final int length, SecureRandom secureRandom)
	{
		Objects.requireNonNull(secureRandom);
		final byte[] randomByteArray = new byte[length];
		final byte[] randomByteBox = new byte[1];
		for (int i = 0; i < length; i++)
		{
			if (RandomBooleanFactory.randomBoolean(secureRandom))
			{
				randomByteArray[i] = randomByte(secureRandom);
			}
			else
			{
				secureRandom.nextBytes(randomByteBox);
				randomByteArray[i] = randomByteBox[0];
			}
		}
		return randomByteArray;
	}

	private RandomByteFactory()
	{
	}

}
