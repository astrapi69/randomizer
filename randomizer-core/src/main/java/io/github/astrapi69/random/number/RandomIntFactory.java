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
import io.github.astrapi69.random.enums.RandomAlgorithm;

/**
 * Utility class for producing random primitive int types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomIntFactory
{

	private RandomIntFactory()
	{
	}

	/**
	 * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
	 * to 10 the random int is between 0-9.
	 *
	 * @param range
	 *            The range
	 * @param algorithm
	 *            the random algorithm
	 * @return an int not greater then the range
	 */
	public static int randomInt(final int range, final RandomAlgorithm algorithm)
	{
		return RandomIntFactory.randomInt(range, Objects.requireNonNull(algorithm),
			DefaultSecureRandom.get());
	}

	/**
	 * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
	 * to 10 the random int is between 0-9.
	 *
	 * @param range
	 *            The range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for int generation
	 * @return an int not greater then the range
	 */
	public static int randomInt(final int range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return Math.abs(secureRandom.nextInt()) % range;
			case MATH_RANDOM :
				return (int)(Math.random() * range);
			case RANDOM :
				int random = new Random(System.currentTimeMillis()).nextInt() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return (int)(secureRandom.nextDouble() * range);
		}
	}

	/**
	 * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
	 * to 10 the random int is between 0-9.
	 *
	 * @param range
	 *            The Range
	 * @param secureRandom
	 *            the secure random for int generation
	 * @return an int not greater then the range.
	 */
	public static int randomInt(final int range, SecureRandom secureRandom)
	{
		return randomInt(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * The Method randomInt() gets an int between the range 0-9.
	 *
	 * @param secureRandom
	 *            the secure random for int generation
	 *
	 * @return an int between the range 0-9.
	 */
	public static int randomInt(SecureRandom secureRandom)
	{
		return Objects.requireNonNull(secureRandom).nextInt();
	}

	/**
	 * The Method randomInt() gets an int between the range 0-9.
	 *
	 * @return an int between the range 0-9.
	 */
	public static int randomInt()
	{
		return RandomIntFactory.randomInt(DefaultSecureRandom.get());
	}

	/**
	 * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
	 * to 10 the random int is between 0-9.
	 *
	 * @param range
	 *            The Range.
	 * @return an int not greater then the range.
	 */
	public static int randomInt(final int range)
	{
		return randomInt(range, DefaultSecureRandom.get());
	}

	/**
	 * Returns a random int between the range from minVolume and maxVolume with the
	 * <code>Math.abs</code> method.
	 *
	 * @param minVolume
	 *            the min volume
	 * @param maxVolume
	 *            the max volume
	 * @param secureRandom
	 *            the secure random for number generation
	 * @return A random int between the range from minVolume and maxVolume
	 */
	public static int randomIntBetween(int minVolume, int maxVolume, SecureRandom secureRandom)
	{
		return minVolume + randomInt(maxVolume - minVolume, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

	/**
	 * Returns a random int between the range from start and end.
	 *
	 * @param start
	 *            The int from where the range starts.
	 * @param end
	 *            The int from where the range ends.
	 * @param includeMin
	 *            if true than min value is included
	 * @param includeMax
	 *            if true than max value is included
	 * @param secureRandom
	 *            the secure random for generation
	 * @return A random int between the range from start and end.
	 */
	public static int randomIntBetween(final int start, final int end, final boolean includeMin,
		final boolean includeMax, SecureRandom secureRandom)
	{
		int randomIntBetween = start + RandomIntFactory.randomInt(end - (start - 1), secureRandom);
		if (includeMin && !includeMax)
		{
			randomIntBetween = start + RandomIntFactory.randomInt(end - start, secureRandom);
		}
		if (!includeMin && includeMax && randomIntBetween == start)
		{
			randomIntBetween++;
		}
		if (!includeMin && !includeMax)
		{
			randomIntBetween = (start + 1)
				+ RandomIntFactory.randomInt(end - (start + 1), secureRandom);
		}
		return randomIntBetween;
	}

	/**
	 * Returns a random int between the range from start and end.
	 *
	 * @param start
	 *            The int from where the range starts.
	 * @param end
	 *            The int from where the range ends.
	 * @return A random int between the range from start and end.
	 */
	public static int randomIntBetween(final int start, final int end)
	{
		return RandomIntFactory.randomIntBetween(start, end, true, false);
	}

	/**
	 * Returns a random int between the range from start and end.
	 *
	 * @param start
	 *            The int from where the range starts.
	 * @param end
	 *            The int from where the range ends.
	 * @param includeMin
	 *            if true than min value is included
	 * @param includeMax
	 *            if true than max value is included
	 * @return A random int between the range from start and end.
	 */
	public static int randomIntBetween(final int start, final int end, final boolean includeMin,
		final boolean includeMax)
	{
		return RandomIntFactory.randomIntBetween(start, end, includeMin, includeMax,
			DefaultSecureRandom.get());
	}

}
