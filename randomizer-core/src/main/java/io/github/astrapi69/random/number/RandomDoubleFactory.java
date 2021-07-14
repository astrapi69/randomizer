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
 * Utility class for producing random primitive double types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomDoubleFactory
{

	private RandomDoubleFactory()
	{
	}

	/**
	 * Gets an random double to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @return an random double not greater then the range
	 */
	public static double randomDouble(final double range, final RandomAlgorithm algorithm)
	{
		return randomDouble(range, Objects.requireNonNull(algorithm), DefaultSecureRandom.get());
	}

	/**
	 * Gets an random double to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return an random double not greater then the range
	 */
	public static double randomDouble(final double range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return Math.abs(secureRandom.nextDouble()) % range;
			case MATH_RANDOM :
				return Math.random() * range;
			case RANDOM :
				double random = new Random(System.currentTimeMillis()).nextDouble() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return secureRandom.nextDouble() * range;
		}
	}

	/**
	 * Gets an random double to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return an random double not greater then the range
	 */
	public static double randomDouble(final double range, SecureRandom secureRandom)
	{
		return randomDouble(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * Gets an random double to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return an random double not greater then the range
	 */
	public static double randomDouble(SecureRandom secureRandom)
	{
		return Objects.requireNonNull(secureRandom).nextDouble();
	}

	/**
	 * The Method randomDouble(double) gets an double to the spezified range. For example: if you
	 * put range to 10.0 the random int is between 0.0-9.9.
	 *
	 * @param range
	 *            the range
	 * @return the double
	 */
	public static double randomDouble(final double range)
	{
		return randomDouble(range, DefaultSecureRandom.get());
	}

	/**
	 * The Method randomDouble() gets a random double
	 *
	 * @return the random double
	 */
	public static double randomDouble()
	{
		return randomDouble(Double.MAX_VALUE);
	}

	/**
	 * Gets the random double between the range from start and end.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return the random double between
	 */
	public static double randomDoubleBetween(final double start, final double end,
		SecureRandom secureRandom)
	{
		return start + randomDouble(end - start, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

	/**
	 * Gets the random double between the range from start and end.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the random double between
	 */
	public static double randomDoubleBetween(final double start, final double end)
	{
		return randomDoubleBetween(start, end, DefaultSecureRandom.get());
	}

}
