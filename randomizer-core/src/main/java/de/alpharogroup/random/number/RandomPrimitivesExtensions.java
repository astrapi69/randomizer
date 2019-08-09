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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Random;

import de.alpharogroup.random.DefaultSecureRandom;
import lombok.experimental.UtilityClass;

/**
 * Utility class for producing random privimitive types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
@UtilityClass
public final class RandomPrimitivesExtensions
{

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
		final byte[] randomByteArray = new byte[length];
		for (int i = 0; i < length; i++)
		{
			randomByteArray[i] = randomByte();
		}
		return randomByteArray;
	}

	/**
	 * Returns a random char.
	 *
	 * @return The generated random char.
	 */
	public static char randomChar()
	{
		if (DefaultSecureRandom.get().nextBoolean())
		{
			// random character
			return (char)(DefaultSecureRandom.get().nextInt(26) + 65);
		}
		else
		{
			// random digit
			return (char)DefaultSecureRandom.get().nextInt(10);
		}
	}

	/**
	 * Returns a random short
	 *
	 * @return The generated random short
	 */
	public static short randomShort()
	{
		if (DefaultSecureRandom.get().nextBoolean())
		{
			return (short)(DefaultSecureRandom.get().nextInt(65536) - 32768);
		}
		else
		{
			return (short)DefaultSecureRandom.get().nextInt(Short.MAX_VALUE + 1);
		}
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
	 * Gets a random long
	 *
	 * @return a random long
	 */
	public static long randomLong()
	{
		return randomLong(new Random(System.currentTimeMillis()).nextInt());
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
		if (DefaultSecureRandom.get() != null)
		{
			return (long)(DefaultSecureRandom.get().nextDouble() * range);
		}
		return (long)(Math.random() * range);
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
		return start + randomLong(end - start);
	}

	/**
	 * Generates a random float between the range 0.0-9.9.
	 *
	 * @return the generated random float between the range 0.0-9.9.
	 */
	public static float randomFloat()
	{
		if (DefaultSecureRandom.get() != null)
		{
			return randomFloat(DefaultSecureRandom.get().nextFloat());
		}
		return randomFloat(new Random(System.currentTimeMillis()).nextFloat());
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
		if (DefaultSecureRandom.get() != null)
		{
			return DefaultSecureRandom.get().nextDouble() * range;
		}
		return Math.random() * range;
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
	 * @return the random double between
	 */
	public static double randomDoubleBetween(final double start, final double end)
	{
		return start + randomDouble(end - start);
	}

	/**
	 * Gets the random double between the range from start and end in the given pattern. Refer to
	 * class @see {@link java.text.DecimalFormat}.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param pattern
	 *            the pattern
	 * @return the random double between
	 * @throws ParseException
	 *             is thrown if the beginning of the specified string cannot be parsed
	 */
	public static double randomDoubleBetween(final double start, final double end,
		final String pattern) throws ParseException
	{
		final DecimalFormat formatter = new DecimalFormat(pattern);
		final String rd = formatter.format(randomDoubleBetween(start, end));
		Number randomDouble = formatter.parse(rd);
		return randomDouble.doubleValue();
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
		if (DefaultSecureRandom.get() != null)
		{
			return (float)(DefaultSecureRandom.get().nextDouble() * range);
		}
		return (float)(Math.random() * range);
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
		final String nachkommastellen = RandomNumberExtensions.getRandomNumericString(afterComma);
		final String vorkommastellen = RandomNumberExtensions.getRandomNumericString(beforeComma);
		final String result = nachkommastellen + "." + vorkommastellen;
		return result;
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
		return start + randomFloat(end - start);
	}

	/**
	 * Gets the random float between the range from start and end in the given pattern. Refer to
	 * class @see {@link java.text.DecimalFormat}.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param pattern
	 *            the pattern
	 * @return the random float between
	 * @throws ParseException
	 *             is thrown if the beginning of the specified string cannot be parsed
	 */
	public static float randomFloatBetween(final float start, final float end, final String pattern)
		throws ParseException
	{
		final NumberFormat formatter = new DecimalFormat(pattern);
		final String rf = formatter.format(randomFloatBetween(start, end));
		Number randomFloat = formatter.parse(rf);
		return randomFloat.floatValue();
	}

	/**
	 * The Method randomInt() gets an int between the range 0-9.
	 *
	 * @return an int between the range 0-9.
	 */
	public static int randomInt()
	{
		if (DefaultSecureRandom.get() != null)
		{
			return randomInt(DefaultSecureRandom.get().nextInt());
		}
		return randomInt(new Random(System.currentTimeMillis()).nextInt());
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
		if (DefaultSecureRandom.get() != null)
		{
			return (int)(DefaultSecureRandom.get().nextDouble() * range);
		}
		return (int)(Math.random() * range);
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
		int randomIntBetween = start + randomInt(end - start);

		if (includeMin && includeMax)
		{
			randomIntBetween = start + randomInt(end - (start + 1));
		}
		if (includeMin && !includeMax)
		{
			randomIntBetween = start + randomInt(end - start);
		}
		if (!includeMin && includeMax)
		{
			randomIntBetween = (start + 1) + randomInt(end - (start + 1));
		}
		if (!includeMin && !includeMax)
		{
			randomIntBetween = (start + 1) + randomInt(end - start);
		}
		return randomIntBetween;
	}

}
