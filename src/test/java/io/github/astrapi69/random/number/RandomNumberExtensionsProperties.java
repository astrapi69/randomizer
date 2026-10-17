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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import io.github.astrapi69.random.Seeds;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

/**
 * Properties of {@link RandomNumberExtensions}
 */
public class RandomNumberExtensionsProperties
{

	/**
	 * {@link RandomNumberExtensions#getRandomNumberString(int, int)} has beforeComma digits before
	 * the decimal point and afterComma digits after it
	 *
	 * @param afterComma
	 *            the number of digits after the decimal point
	 * @param beforeComma
	 *            the number of digits before the decimal point
	 */
	@Property(seed = Seeds.SEED)
	void numberStringHasTheDigitsBeforeAndAfterTheComma(@ForAll @IntRange(max = 12) int afterComma,
		@ForAll @IntRange(max = 12) int beforeComma)
	{
		String number = RandomNumberExtensions.getRandomNumberString(afterComma, beforeComma);
		assertTrue(number.matches("\\d{" + beforeComma + "}\\.\\d{" + afterComma + "}"),
			() -> number + " for " + beforeComma + " digits before and " + afterComma
				+ " after the comma");
	}

	/**
	 * {@link RandomBigDecimalFactory#randomBigDecimal(int, int)} has afterComma decimal places and
	 * stays below 10^beforeComma
	 *
	 * @param afterComma
	 *            the number of digits after the decimal point
	 * @param beforeComma
	 *            the number of digits before the decimal point
	 */
	@Property(seed = Seeds.SEED)
	void bigDecimalHasTheDigitsBeforeAndAfterTheComma(@ForAll @IntRange(max = 12) int afterComma,
		@ForAll @IntRange(min = 1, max = 12) int beforeComma)
	{
		BigDecimal number = RandomBigDecimalFactory.randomBigDecimal(afterComma, beforeComma);
		assertEquals(afterComma, number.scale(), () -> number + " has the wrong scale");
		assertTrue(number.compareTo(BigDecimal.TEN.pow(beforeComma)) < 0,
			() -> number + " has more than " + beforeComma + " digits before the comma");
	}

	/**
	 * {@link RandomFloatFactory#randomFloat(int, int)} stays below 10^beforeComma (up to the
	 * rounding of float)
	 *
	 * @param afterComma
	 *            the number of digits after the decimal point
	 * @param beforeComma
	 *            the number of digits before the decimal point
	 */
	@Property(seed = Seeds.SEED)
	void floatHasTheDigitsBeforeTheComma(@ForAll @IntRange(min = 1, max = 6) int afterComma,
		@ForAll @IntRange(min = 1, max = 6) int beforeComma)
	{
		float number = RandomFloatFactory.randomFloat(afterComma, beforeComma);
		assertTrue(0 <= number && number <= Math.pow(10, beforeComma),
			() -> number + " has more than " + beforeComma + " digits before the comma");
	}
}
