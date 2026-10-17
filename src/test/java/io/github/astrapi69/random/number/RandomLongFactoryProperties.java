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

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import io.github.astrapi69.random.Seeds;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.LongRange;

/**
 * Properties of {@link RandomLongFactory}
 */
public class RandomLongFactoryProperties
{

	/** The number of values a property draws from one generator */
	private static final int DRAWS = 200;

	private static <T> List<T> draw(Supplier<T> generator)
	{
		return Stream.generate(generator).limit(DRAWS).toList();
	}

	/**
	 * The algorithms that draw only from the given {@link SecureRandom}; MATH_RANDOM draws from
	 * {@link Math#random()}, which no seed reaches
	 *
	 * @return the arbitrary of the seedable algorithms
	 */
	@Provide
	Arbitrary<RandomAlgorithm> seedableAlgorithms()
	{
		return Arbitraries.of(RandomAlgorithm.MATH_ABS, RandomAlgorithm.RANDOM,
			RandomAlgorithm.SECURE_RANDOM);
	}

	/**
	 * For every algorithm,
	 * {@link RandomLongFactory#randomLong(long, RandomAlgorithm, SecureRandom)} lies in [0, range)
	 */
	@Property(seed = Seeds.SEED)
	void randomLongLiesInTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @LongRange(min = 1) long range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		for (long value : draw(() -> RandomLongFactory.randomLong(range, algorithm, secureRandom)))
		{
			assertTrue(0 <= value && value < range,
				() -> value + " is not in [0, " + range + ") with " + algorithm);
		}
	}

	/**
	 * For every algorithm, the values spread over the whole range: among 200 draws there is one
	 * below the middle of the range and one above it. Each half holds at least a third of the
	 * range, so a correct generator misses one with a probability below (2/3)^200
	 */
	@Property(seed = Seeds.SEED)
	void randomLongSpreadsOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @LongRange(min = 2) long range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		List<Long> values = draw(
			() -> RandomLongFactory.randomLong(range, algorithm, secureRandom));
		long middle = range / 2;
		assertTrue(values.stream().anyMatch(value -> value < middle),
			() -> "no value below " + middle + " with " + algorithm);
		assertTrue(values.stream().anyMatch(value -> middle <= value),
			() -> "no value from " + middle + " up with " + algorithm);
	}

	/**
	 * For every algorithm, 200 draws from a range of a million hold at least 150 distinct values; a
	 * generator that returns the same value again and again (for example because it reseeds from
	 * the clock on every call) does not
	 */
	@Property(seed = Seeds.SEED)
	void randomLongDoesNotRepeatItself(@ForAll RandomAlgorithm algorithm, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		int distinct = new HashSet<>(
			draw(() -> RandomLongFactory.randomLong(1_000_000L, algorithm, secureRandom))).size();
		assertTrue(150 <= distinct,
			() -> "only " + distinct + " distinct values with " + algorithm);
	}

	/**
	 * For every algorithm that draws from the given {@link SecureRandom}, the same seed gives the
	 * same sequence
	 */
	@Property(seed = Seeds.SEED)
	void sameSeedGivesTheSameLongs(@ForAll("seedableAlgorithms") RandomAlgorithm algorithm,
		@ForAll @LongRange(min = 1) long range, @ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		assertEquals(draw(() -> RandomLongFactory.randomLong(range, algorithm, first)),
			draw(() -> RandomLongFactory.randomLong(range, algorithm, second)),
			algorithm::toString);
	}
}
