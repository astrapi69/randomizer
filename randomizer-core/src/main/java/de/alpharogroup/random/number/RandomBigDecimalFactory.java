/**
 * The MIT License
 * <p>
 * Copyright (C) 2015 Asterios Raptis
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.random.number;

import de.alpharogroup.random.DefaultSecureRandom;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;

/**
 * Utility class for producing random BigDecimal types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomBigDecimalFactory {

	/**
	 * Generates a random {@link BigDecimal}
	 *
	 * @return the random {@link BigDecimal}
	 */
	public static BigDecimal randomBigDecimal(SecureRandom secureRandom)
	{
		BigDecimal bigDecimal = BigDecimal.valueOf(RandomDoubleFactory.randomDouble(secureRandom));
		return bigDecimal;
	}

	/**
	 * Generates a random {@link BigDecimal}
	 *
	 * @return the random {@link BigDecimal}
	 */
	public static BigDecimal randomBigDecimal()
	{
		return randomBigDecimal(DefaultSecureRandom.get());
	}

	/**
	 * The Method getRandomBigDecimal(int,int) gets an random BigDecimal.
	 *
	 * @param afterComma
	 *            How many decimal places after the comma.
	 * @param beforeComma
	 *            How many decimal places before the comma.
	 * @return The produced BigDecimal.
	 */
	public static BigDecimal randomBigDecimal(final int afterComma, final int beforeComma)
	{
		String randomFloatString;
		do
		{
			randomFloatString = RandomNumberExtensions.getRandomNumberString(afterComma, beforeComma);
		}
		while (randomFloatString.equals("."));
		return new BigDecimal(randomFloatString);
	}

	private RandomBigDecimalFactory() {
	}

}
