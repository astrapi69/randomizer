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

import java.util.Objects;

import de.alpharogroup.random.DefaultSecureRandom;
import de.alpharogroup.random.SecureRandomFactory;
import de.alpharogroup.random.enums.RandomAlgorithm;

/**
 * Utility class for producing random privimitive types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomPrimitivesExtensions
{
	/**
	 * Gets the random float as {@link String} object.
	 *
	 * @param afterComma
	 *            How many decimal places after the comma.
	 * @param beforeComma
	 *            How many decimal places before the comma.
	 * @return the random float string
	 */
	private static String getRandomFloatString(final int afterComma, final int beforeComma)
	{
		return RandomNumberExtensions.getRandomNumericString(afterComma) + "."
			+ RandomNumberExtensions.getRandomNumericString(beforeComma);
	}

	/**
	 * Returns a random int between the range from minVolume and maxVolume with the
	 * <code>Math.abs</code> method.
	 *
	 * @param minVolume
	 *            the min volume
	 * @param maxVolume
	 *            the max volume
	 * @return A random int between the range from minVolume and maxVolume
	 */
	public static int getRandomIntBetween(int minVolume, int maxVolume)
	{
		return RandomPrimitivesFactory.randomIntBetween(minVolume, maxVolume,
			DefaultSecureRandom.get());
	}

	/**
	 * Returns a random boolean.
	 *
	 * @return The random boolean.
	 */
	public static boolean randomBoolean()
	{
		return randomInt(2) == 0;
	}

	/**
	 * The Method randomByte() selects a random byte.
	 *
	 * @return The random byte.
	 */
	public static byte randomByte()
	{
		return (byte)randomInt(255);
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
		return RandomPrimitivesFactory.randomByteArray(length, DefaultSecureRandom.get());
	}

	/**
	 * Returns a random char.
	 *
	 * @return The generated random char.
	 */
	public static char randomChar()
	{
		return RandomPrimitivesFactory.randomChar(DefaultSecureRandom.get());
	}

	/**
	 * The Method randomChar(String) selects a random char from the given String.
	 *
	 * @param string
	 *            The String from who to select the char.
	 * @return The selected char.
	 */
	public static char randomChar(final String string)
	{
		return string.charAt(randomInt(string.length()));
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
	 * The Method randomDouble(double) gets an double to the spezified range. For example: if you
	 * put range to 10.0 the random int is between 0.0-9.9.
	 *
	 * @param range
	 *            the range
	 * @return the double
	 */
	public static double randomDouble(final double range)
	{
		return randomDouble(range, RandomAlgorithm.SECURE_RANDOM);
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
		return RandomPrimitivesFactory.randomDouble(range, Objects.requireNonNull(algorithm),
			DefaultSecureRandom.get());
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
		return RandomPrimitivesFactory.randomDoubleBetween(start, end,
			SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Generates a random float between the range 0.0-9.9.
	 *
	 * @return the generated random float between the range 0.0-9.9.
	 */
	public static float randomFloat()
	{
		return RandomPrimitivesFactory.randomFloat(DefaultSecureRandom.get());
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
		return RandomPrimitivesFactory.randomFloat(range, Objects.requireNonNull(algorithm),
			DefaultSecureRandom.get());
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
		return Float.parseFloat(getRandomFloatString(afterComma, beforeComma));
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
		return RandomPrimitivesFactory.randomFloatBetween(start, end,
			SecureRandomFactory.newSecureRandom());
	}

	/**
	 * The Method randomInt() gets an int between the range 0-9.
	 *
	 * @return an int between the range 0-9.
	 */
	public static int randomInt()
	{
		return RandomPrimitivesFactory.randomInt(DefaultSecureRandom.get());
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
		return randomInt(range, RandomAlgorithm.SECURE_RANDOM);
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
		return RandomPrimitivesFactory.randomInt(range, Objects.requireNonNull(algorithm),
			DefaultSecureRandom.get());
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
		return RandomPrimitivesExtensions.randomIntBetween(start, end, true, false);
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
		int randomIntBetween = start + randomInt(end - (start - 1));
		if (includeMin && !includeMax)
		{
			randomIntBetween = start + randomInt(end - start);
		}
		if (!includeMin && includeMax && randomIntBetween == start)
		{
			randomIntBetween++;
		}
		if (!includeMin && !includeMax)
		{
			randomIntBetween = (start + 1) + randomInt(end - (start + 1));
		}
		return randomIntBetween;
	}

	/**
	 * Gets a random long
	 *
	 * @return a random long
	 */
	public static long randomLong()
	{
		return RandomPrimitivesFactory.randomLong(DefaultSecureRandom.get());
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
		return RandomPrimitivesFactory.randomLong(range, Objects.requireNonNull(algorithm),
			DefaultSecureRandom.get());
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
		return RandomPrimitivesFactory.randomLongBetween(start, end,
			SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Returns a random short
	 *
	 * @return The generated random short
	 */
	public static short randomShort()
	{
		return RandomPrimitivesFactory.randomShort(DefaultSecureRandom.get());
	}

	private RandomPrimitivesExtensions()
	{
	}

}
