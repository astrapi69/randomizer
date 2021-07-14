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

import de.alpharogroup.math.MathExtensions;
import io.github.astrapi69.random.RandomCharacters;
import io.github.astrapi69.random.object.RandomStringFactory;

/**
 * Utility class for producing random numbers.
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomNumberExtensions
{

	private RandomNumberExtensions()
	{
	}

	/**
	 * Gets the random number string
	 *
	 * @param afterComma
	 *            How many decimal places after the comma.
	 * @param beforeComma
	 *            How many decimal places before the comma.
	 * @return the random float string
	 */
	public static String getRandomNumberString(final int afterComma, final int beforeComma)
	{
		final String decimals = getRandomNumericString(afterComma);
		final String preDecimals = getRandomNumericString(beforeComma);
		final String randomNumberString = decimals + "." + preDecimals;
		return randomNumberString;
	}

	/**
	 * Generates a random numeric string.
	 *
	 * @return the generated random numeric string.
	 */
	public static String getRandomNumericString()
	{
		final int maxLength = Math.min(RandomIntFactory.randomInt(1000), 1024);
		final StringBuilder sb = new StringBuilder(maxLength);
		for (int i = 0; i < maxLength; i++)
		{
			int randomInt = RandomIntFactory.randomInt();
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
		final String randomNumber = RandomStringFactory
			.newRandomString(RandomCharacters.numbers.getCharacters(), length);
		return randomNumber;
	}

}
