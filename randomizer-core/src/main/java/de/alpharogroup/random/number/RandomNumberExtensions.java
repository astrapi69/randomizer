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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.DefaultSecureRandom;
import de.alpharogroup.random.RandomCharacters;
import de.alpharogroup.random.RandomExtensions;

/**
 * Utility class for producing random numbers.
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomNumberExtensions
{

	/**
	 * The Method getRandomBigDecimal(int,int) gets an random BigDecimal.
	 *
	 * @param afterComma
	 *            How many decimal places after the comma.
	 * @param beforeComma
	 *            How many decimal places before the comma.
	 * @return The produced BigDecimal.
	 */
	public static BigDecimal getRandomBigDecimal(final int afterComma, final int beforeComma)
	{
		String randomFloatString;
		do
		{
			randomFloatString = getRandomFloatString(afterComma, beforeComma);
		}
		while (randomFloatString.equals("."));
		return new BigDecimal(randomFloatString);
	}

	/**
	 * Gets the random float string.
	 *
	 * @param afterComma
	 *            How many decimal places after the comma.
	 * @param beforeComma
	 *            How many decimal places before the comma.
	 * @return the random float string
	 */
	public static String getRandomFloatString(final int afterComma, final int beforeComma)
	{
		final String decimals = getRandomNumericString(afterComma);
		final String preDecimals = getRandomNumericString(beforeComma);
		final String randomFloatString = decimals + "." + preDecimals;
		return randomFloatString;
	}

	/**
	 * Generates a random numeric string.
	 *
	 * @return the generated random numeric string.
	 */
	public static String getRandomNumericString()
	{
		final int maxLength = Math.min(RandomPrimitivesExtensions.randomInt(1000), 1024);
		final StringBuilder sb = new StringBuilder(maxLength);
		for (int i = 0; i < maxLength; i++)
		{
			int randomInt = RandomPrimitivesExtensions.randomInt();
			if (MathExtensions.isNegative(randomInt))
			{
				sb.append(randomInt * (-1));
			}
			else
			{
				sb.append(randomInt);
			}
		}
		return sb.toString();
	}

	/**
	 * The Method getRandomNumericString(int) produces a random Number to the specified length.
	 *
	 * @param length
	 *            The length from the random number.
	 * @return The random number as String.
	 */
	public static String getRandomNumericString(final int length)
	{
		final String randomNumber = RandomExtensions
			.getRandomString(RandomCharacters.numbers.getCharacters(), length);
		return randomNumber;
	}

	/**
	 * Generates a random {@link BigDecimal}
	 *
	 * @return the random {@link BigDecimal}
	 */
	public static BigDecimal randomBigDecimal()
	{
		BigDecimal bigDecimal = new BigDecimal(RandomPrimitivesExtensions.randomDouble());
		bigDecimal.setScale(RandomPrimitivesExtensions.randomInt(2), RoundingMode.HALF_DOWN);
		return bigDecimal;
	}

	/**
	 * Generates a random {@link BigInteger}
	 *
	 * @return the random {@link BigInteger}
	 */
	public static BigInteger randomBigInteger()
	{
		return new BigInteger(RandomPrimitivesExtensions.randomInt(180), DefaultSecureRandom.get());
	}

	private RandomNumberExtensions()
	{
	}

}
