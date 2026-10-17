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

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import io.github.astrapi69.random.Draws;
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
	 * A serial number is never negative, for every long the {@link SecureRandom} draws,
	 * Long.MIN_VALUE included, which stays negative when multiplied by -1
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

	/**
	 * {@link RandomBigIntegerFactory#randomBigInteger(SecureRandom)} draws from the given generator
	 * alone, its bit length included: the same seed gives the same sequence
	 *
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void sameSeedGivesTheSameBigIntegers(@ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		assertEquals(
			Stream.generate(() -> RandomBigIntegerFactory.randomBigInteger(first)).limit(20)
				.toList(),
			Stream.generate(() -> RandomBigIntegerFactory.randomBigInteger(second)).limit(20)
				.toList());
	}

	/**
	 * {@link RandomBigIntegerFactory#randomBigInteger(SecureRandom)} and
	 * {@link RandomBigIntegerFactory#randomBigInteger()} lie in [0, 2^179) (the bit length is drawn
	 * below 180) and do not repeat: 200 draws hold at least 150 distinct values
	 *
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void randomBigIntegerLiesBelowTwoToThe179(@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		BigInteger bound = BigInteger.TWO.pow(179);
		for (List<BigInteger> values : List.of(
			Draws.draw(() -> RandomBigIntegerFactory.randomBigInteger(secureRandom)),
			Draws.draw(RandomBigIntegerFactory::randomBigInteger)))
		{
			values.forEach(value -> assertTrue(0 <= value.signum() && value.compareTo(bound) < 0,
				() -> value + " is not in [0, 2^179)"));
			assertTrue(150 <= new HashSet<>(values).size());
		}
	}

	/**
	 * {@link RandomBigIntegerFactory#randomSerialNumber(SecureRandom)} and
	 * {@link RandomBigIntegerFactory#randomSerialNumber()} are not negative and do not repeat
	 *
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void serialNumbersAreNotNegativeAndDoNotRepeat(@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		for (List<BigInteger> values : List.of(
			Draws.draw(() -> RandomBigIntegerFactory.randomSerialNumber(secureRandom)),
			Draws.draw(RandomBigIntegerFactory::randomSerialNumber)))
		{
			values.forEach(value -> assertTrue(0 <= value.signum(), () -> value + " is negative"));
			assertTrue(150 <= new HashSet<>(values).size());
		}
	}
}
