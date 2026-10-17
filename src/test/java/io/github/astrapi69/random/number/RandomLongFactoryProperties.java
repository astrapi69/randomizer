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

import static io.github.astrapi69.random.Draws.draw;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;

import io.github.astrapi69.random.Seeds;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.LongRange;

/**
 * Properties of {@link RandomLongFactory}
 */
public class RandomLongFactoryProperties
{

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
	 * Asserts that the values lie on both sides of the middle of [start, end): one below it and one
	 * from it up. For a width of at least 2 each side holds at least a third of the range, so a
	 * correct generator misses one with a probability below 2 * (2/3)^200
	 */
	private static void assertSpreadsOverTheRange(List<Long> values, long start, long end)
	{
		// 2 * value < start + end, as BigInteger: the sum of two longs does not fit in a long
		BigInteger sum = BigInteger.valueOf(start).add(BigInteger.valueOf(end));
		BigInteger two = BigInteger.TWO;
		assertTrue(
			values.stream()
				.anyMatch(value -> two.multiply(BigInteger.valueOf(value)).compareTo(sum) < 0),
			() -> "no value below the middle of [" + start + ", " + end + ")");
		assertTrue(
			values.stream()
				.anyMatch(value -> sum.compareTo(two.multiply(BigInteger.valueOf(value))) <= 0),
			() -> "no value from the middle of [" + start + ", " + end + ") up");
	}

	private static void assertInRange(List<Long> values, long start, long end)
	{
		values.forEach(value -> assertTrue(start <= value && value < end,
			() -> value + " is not in [" + start + ", " + end + ")"));
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
		assertInRange(draw(() -> RandomLongFactory.randomLong(range, algorithm, secureRandom)), 0,
			range);
	}

	/**
	 * For every algorithm, the values spread over the whole range: among 200 draws there is one
	 * below the middle of the range and one above it
	 */
	@Property(seed = Seeds.SEED)
	void randomLongSpreadsOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @LongRange(min = 2) long range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertSpreadsOverTheRange(
			draw(() -> RandomLongFactory.randomLong(range, algorithm, secureRandom)), 0, range);
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

	/**
	 * {@link RandomLongFactory#randomLong(SecureRandom)} draws from the given generator alone: the
	 * same seed gives the same sequence
	 *
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void sameSeedGivesTheSameRandomLongs(@ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		assertEquals(draw(() -> RandomLongFactory.randomLong(first)),
			draw(() -> RandomLongFactory.randomLong(second)));
	}

	/**
	 * {@link RandomLongFactory#randomLong(SecureRandom)} and {@link RandomLongFactory#randomLong()}
	 * draw any long: values below and above 0, and 200 draws hold at least 150 distinct values
	 */
	@Property(seed = Seeds.SEED)
	void randomLongWithoutRangeDrawsAnyLong(@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		for (List<Long> values : List.of(draw(() -> RandomLongFactory.randomLong(secureRandom)),
			draw(RandomLongFactory::randomLong)))
		{
			assertSpreadsOverTheRange(values, Long.MIN_VALUE, Long.MAX_VALUE);
			assertTrue(150 <= new HashSet<>(values).size());
		}
	}

	/**
	 * The overloads without a {@link SecureRandom}, {@link RandomLongFactory#randomLong(long)} and
	 * {@link RandomLongFactory#randomLong(long, RandomAlgorithm)}, draw from the default generator
	 * over the whole range
	 */
	@Property(seed = Seeds.SEED)
	void overloadsWithoutSecureRandomDrawOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @LongRange(min = 2) long range)
	{
		List<Long> values = draw(() -> RandomLongFactory.randomLong(range, algorithm));
		assertInRange(values, 0, range);
		assertSpreadsOverTheRange(values, 0, range);
		List<Long> secureValues = draw(() -> RandomLongFactory.randomLong(range));
		assertInRange(secureValues, 0, range);
		assertSpreadsOverTheRange(secureValues, 0, range);
	}

	/**
	 * {@link RandomLongFactory#randomLongBetween(long, long, SecureRandom)} lies in [start, end)
	 * for every start below end, the ranges wider than Long.MAX_VALUE included
	 */
	@Property(seed = Seeds.SEED)
	void randomLongBetweenLiesInTheRangeForEveryStartAndEnd(@ForAll long start, @ForAll long end,
		@ForAll long seed)
	{
		Assume.that(start < end);
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertInRange(draw(() -> RandomLongFactory.randomLongBetween(start, end, secureRandom)),
			start, end);
	}

	/**
	 * {@link RandomLongFactory#randomLongBetween(long, long, SecureRandom)} and
	 * {@link RandomLongFactory#randomLongBetween(long, long)} spread over [start, end) for every
	 * start and end at least 2 apart, the ranges wider than Long.MAX_VALUE included
	 */
	@Property(seed = Seeds.SEED)
	void randomLongBetweenSpreadsOverTheRange(@ForAll long start, @ForAll long end,
		@ForAll long seed)
	{
		Assume.that(start < end && start < end - 1);
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertSpreadsOverTheRange(
			draw(() -> RandomLongFactory.randomLongBetween(start, end, secureRandom)), start, end);
		List<Long> values = draw(() -> RandomLongFactory.randomLongBetween(start, end));
		assertInRange(values, start, end);
		assertSpreadsOverTheRange(values, start, end);
	}

	/**
	 * {@link RandomLongFactory#randomLong(long, SecureRandom)}, the SECURE_RANDOM algorithm, lies
	 * in [0, range) and spreads over it
	 */
	@Property(seed = Seeds.SEED)
	void randomLongWithSecureRandomDrawsOverTheRange(@ForAll @LongRange(min = 2) long range,
		@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		List<Long> values = draw(() -> RandomLongFactory.randomLong(range, secureRandom));
		assertInRange(values, 0, range);
		assertSpreadsOverTheRange(values, 0, range);
	}
}
