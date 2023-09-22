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

import io.github.astrapi69.math.MathExtensions;
import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.SecureRandomFactory;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;

/**
 * Utility class for producing random primitive float types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomFloatFactory
{

	private RandomFloatFactory()
	{
	}

	/**
	 * Gets an random float to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for float generation
	 * @return an random float not greater then the range
	 */
	public static float randomFloat(final float range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return (float)(Math.abs(secureRandom.nextDouble()) % range);
			case MATH_RANDOM :
				return (float)(Math.random() * range);
			case RANDOM :
				float random = (float)new Random(System.currentTimeMillis()).nextDouble() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return (float)(secureRandom.nextDouble() * range);
		}
	}

	/**
	 * Gets an random float to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param secureRandom
	 *            the secure random for float generation
	 * @return an random float not greater then the range
	 */
	public static float randomFloat(final float range, SecureRandom secureRandom)
	{
		return randomFloat(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * Generates a random float between the range 0.0-9.9.
	 *
	 * @param secureRandom
	 *            the secure random for float generation
	 *
	 * @return the generated random float between the range 0.0-9.9.
	 */
	public static float randomFloat(SecureRandom secureRandom)
	{
		return Objects.requireNonNull(secureRandom).nextFloat();
	}

	/**
	 * Generates a random float between the range 0.0-9.9.
	 *
	 * @return the generated random float between the range 0.0-9.9.
	 */
	public static float randomFloat()
	{
		return randomFloat(DefaultSecureRandom.get());
	}

	/**
	 * The Method randomFloat(float) gets an float to the spezified range. For example: if you put
	 * range to 10.0 the random int is between 0.0-9.9.
	 *
	 * @param range
	 *            the range
	 * @return the float
	 */
	public static float randomFloat(final float range)
	{
		return randomFloat(range, RandomAlgorithm.SECURE_RANDOM);
	}

	/**
	 * Gets an random float to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @return an random float not greater then the range
	 */
	public static float randomFloat(final float range, final RandomAlgorithm algorithm)
	{
		return randomFloat(range, Objects.requireNonNull(algorithm), DefaultSecureRandom.get());
	}

	/**
	 * The Method getRandomFloat(int,int) gets an random float.
	 *
	 * @param afterComma
	 *            How many decimal places after the comma.
	 * @param beforeComma
	 *            How many decimal places before the comma.
	 * @return The produced float.
	 */
	public static float randomFloat(final int afterComma, final int beforeComma)
	{
		return Float
			.parseFloat(RandomNumberExtensions.getRandomNumberString(afterComma, beforeComma));
	}

	/**
	 * Gets the random float between the range from start and end.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the random float between
	 */
	public static float randomFloatBetween(final float start, final float end)
	{
		return randomFloatBetween(start, end, SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Gets the random float between the range from start and end.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param secureRandom
	 *            the secure random for float generation
	 * @return the random float between
	 */
	public static float randomFloatBetween(final float start, final float end,
		SecureRandom secureRandom)
	{
		return start + randomFloat(end - start, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

}
