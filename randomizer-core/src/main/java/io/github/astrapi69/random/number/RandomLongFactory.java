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
import java.util.Random;

import de.alpharogroup.math.MathExtensions;
import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.SecureRandomFactory;
import io.github.astrapi69.random.enums.RandomAlgorithm;

/**
 * Utility class for producing random primitive long types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomLongFactory
{

	private RandomLongFactory()
	{
	}

	/**
	 * Gets a random long
	 *
	 * @return a random long
	 */
	public static long randomLong()
	{
		return randomLong(DefaultSecureRandom.get());
	}

	/**
	 * The Method randomLong(long) gets an long to the spezified range. For example: if you put
	 * range to 10 the random int is between 0-9.
	 *
	 * @param range
	 *            the range
	 * @return an long not greater then the range.
	 */
	public static long randomLong(final long range)
	{
		return randomLong(range, RandomAlgorithm.SECURE_RANDOM);
	}

	/**
	 * Gets an random long to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random int is between 0-9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @return an random long not greater then the range
	 */
	public static long randomLong(final long range, final RandomAlgorithm algorithm)
	{
		return randomLong(range, Objects.requireNonNull(algorithm), DefaultSecureRandom.get());
	}

	/**
	 * Returns a random long between the range from start and end.
	 *
	 * @param start
	 *            The long from where the range starts.
	 * @param end
	 *            The long from where the range ends.
	 * @return A random long between the range from start and end.
	 */
	public static long randomLongBetween(final long start, final long end)
	{
		return randomLongBetween(start, end, SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Gets an random long to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random int is between 0-9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for long generation
	 * @return an random long not greater then the range
	 */
	public static long randomLong(final long range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return (long)(Math.abs(secureRandom.nextDouble()) % range);
			case MATH_RANDOM :
				return (long)(Math.random() * range);
			case RANDOM :
				long random = (long)new Random(System.currentTimeMillis()).nextDouble() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return (long)(secureRandom.nextDouble() * range);
		}
	}

	/**
	 * Gets an random long to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random int is between 0-9
	 *
	 * @param range
	 *            the range
	 * @param secureRandom
	 *            the secure random for long generation
	 * @return an random long not greater then the range
	 */
	public static long randomLong(final long range, SecureRandom secureRandom)
	{
		return randomLong(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * Gets a random long
	 *
	 * @param secureRandom
	 *            the secure random for long generation
	 *
	 * @return a random long
	 */
	public static long randomLong(SecureRandom secureRandom)
	{
		return randomLong(Objects.requireNonNull(secureRandom).nextLong());
	}

	/**
	 * Returns a random long between the range from start and end.
	 *
	 * @param start
	 *            The long from where the range starts.
	 * @param end
	 *            The long from where the range ends.
	 * @param secureRandom
	 *            the secure random for long generation
	 * @return A random long between the range from start and end.
	 */
	public static long randomLongBetween(final long start, final long end,
		SecureRandom secureRandom)
	{
		return start + randomLong(end - start, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

}
