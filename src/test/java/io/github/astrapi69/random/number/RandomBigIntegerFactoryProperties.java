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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

import io.github.astrapi69.random.Seeds;
import io.github.astrapi69.random.StubSecureRandom;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

/**
 * Properties of {@link RandomBigIntegerFactory}
 */
public class RandomBigIntegerFactoryProperties
{

	/**
	 * A serial number is never negative, for every long the {@link java.security.SecureRandom}
	 * draws, Long.MIN_VALUE included, which stays negative when multiplied by -1
	 *
	 * @param drawn
	 *            the long the generator draws
	 */
	@Property(seed = Seeds.SEED)
	void serialNumberIsNotNegativeForEveryDrawnLong(@ForAll long drawn)
	{
		BigInteger serialNumber = RandomBigIntegerFactory
			.randomSerialNumber(StubSecureRandom.nextLongReturning(drawn));
		assertTrue(0 <= serialNumber.signum(),
			() -> serialNumber + " is negative for the drawn long " + drawn);
	}
}
