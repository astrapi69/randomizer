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

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.LongStream;

import io.github.astrapi69.random.Seeds;
import io.github.astrapi69.random.StubSecureRandom;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;

/**
 * Properties of {@link RandomIntFactory}
 */
public class RandomIntFactoryProperties
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
	private static void assertSpreadsOverTheRange(List<Integer> values, long start, long end)
	{
		// 2 * value < start + end, in long: the sum of two ints does not fit in an int
		assertTrue(values.stream().anyMatch(value -> 2L * value < start + end),
			() -> "no value below the middle of [" + start + ", " + end + ")");
		assertTrue(values.stream().anyMatch(value -> start + end <= 2L * value),
			() -> "no value from the middle of [" + start + ", " + end + ") up");
	}

	/**
	 * Asserts that the values are exactly the ints from lowest to highest: none outside, every one
	 * inside reached. For at most 9 values a correct generator misses one with a probability below
	 * 9 * (8/9)^200
	 */
	private static void assertReachesExactly(List<Integer> values, long lowest, long highest)
	{
		Set<Long> expected = new TreeSet<>();
		LongStream.rangeClosed(lowest, highest).forEach(expected::add);
		Set<Long> actual = new TreeSet<>();
		values.forEach(value -> actual.add(value.longValue()));
		assertEquals(expected, actual);
	}

	/**
	 * For every algorithm, {@link RandomIntFactory#randomInt(int, RandomAlgorithm, SecureRandom)}
	 * lies in [0, range)
	 */
	@Property(seed = Seeds.SEED)
	void randomIntLiesInTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @IntRange(min = 1) int range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		for (int value : draw(() -> RandomIntFactory.randomInt(range, algorithm, secureRandom)))
		{
			assertTrue(0 <= value && value < range,
				() -> value + " is not in [0, " + range + ") with " + algorithm);
		}
	}

	/**
	 * For every algorithm, the values spread over the whole range: among 200 draws there is one
	 * below the middle of the range and one above it
	 */
	@Property(seed = Seeds.SEED)
	void randomIntSpreadsOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @IntRange(min = 2) int range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertSpreadsOverTheRange(
			draw(() -> RandomIntFactory.randomInt(range, algorithm, secureRandom)), 0, range);
	}

	/**
	 * For every algorithm, 200 draws from a range of a million hold at least 150 distinct values; a
	 * generator that returns the same value again and again (for example because it reseeds from
	 * the clock on every call) does not. A correct generator falls below with a probability far
	 * below 2^-100: 200 draws from a million collide about 0.02 times
	 */
	@Property(seed = Seeds.SEED)
	void randomIntDoesNotRepeatItself(@ForAll RandomAlgorithm algorithm, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		int distinct = new HashSet<>(
			draw(() -> RandomIntFactory.randomInt(1_000_000, algorithm, secureRandom))).size();
		assertTrue(150 <= distinct,
			() -> "only " + distinct + " distinct values with " + algorithm);
	}

	/**
	 * For every algorithm that draws from the given {@link SecureRandom}, the same seed gives the
	 * same sequence
	 */
	@Property(seed = Seeds.SEED)
	void sameSeedGivesTheSameInts(@ForAll("seedableAlgorithms") RandomAlgorithm algorithm,
		@ForAll @IntRange(min = 1) int range, @ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		assertEquals(draw(() -> RandomIntFactory.randomInt(range, algorithm, first)),
			draw(() -> RandomIntFactory.randomInt(range, algorithm, second)), algorithm::toString);
	}

	/**
	 * MATH_ABS lies in [0, range) for every int the {@link SecureRandom} draws, Integer.MIN_VALUE
	 * included, whose absolute value is negative
	 */
	@Property(seed = Seeds.SEED)
	void mathAbsLiesInTheRangeForEveryDrawnInt(@ForAll int drawn,
		@ForAll @IntRange(min = 1) int range)
	{
		int value = RandomIntFactory.randomInt(range, RandomAlgorithm.MATH_ABS,
			StubSecureRandom.nextIntReturning(drawn));
		assertTrue(0 <= value && value < range,
			() -> value + " is not in [0, " + range + ") for the drawn int " + drawn);
	}

	/**
	 * The overloads without a {@link SecureRandom}, {@link RandomIntFactory#randomInt(int)} and
	 * {@link RandomIntFactory#randomInt(int, RandomAlgorithm)}, draw from the default generator
	 * over the whole range
	 */
	@Property(seed = Seeds.SEED)
	void overloadsWithoutSecureRandomDrawOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @IntRange(min = 2) int range)
	{
		List<Integer> values = draw(() -> RandomIntFactory.randomInt(range, algorithm));
		values.forEach(value -> assertTrue(0 <= value && value < range,
			() -> value + " is not in [0, " + range + ") with " + algorithm));
		assertSpreadsOverTheRange(values, 0, range);
		List<Integer> secureValues = draw(() -> RandomIntFactory.randomInt(range));
		secureValues.forEach(value -> assertTrue(0 <= value && value < range,
			() -> value + " is not in [0, " + range + ")"));
		assertSpreadsOverTheRange(secureValues, 0, range);
	}

	/**
	 * {@link RandomIntFactory#randomInt(SecureRandom)} and {@link RandomIntFactory#randomInt()}
	 * draw any int: 200 draws hold at least 150 distinct values, and a seeded generator gives the
	 * same sequence for the same seed
	 */
	@Property(seed = Seeds.SEED)
	void randomIntWithoutRangeDrawsAnyInt(@ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		List<Integer> sequence = draw(() -> RandomIntFactory.randomInt(first));
		assertEquals(sequence, draw(() -> RandomIntFactory.randomInt(second)));
		assertTrue(150 <= new HashSet<>(sequence).size());
		assertTrue(150 <= new HashSet<>(draw(RandomIntFactory::randomInt)).size());
	}

	/**
	 * {@link RandomIntFactory#randomIntBetween(int, int, SecureRandom)} lies in [start, end) for
	 * every start below end, the ranges wider than Integer.MAX_VALUE included
	 */
	@Property(seed = Seeds.SEED)
	void randomIntBetweenLiesInTheRangeForEveryStartAndEnd(@ForAll int start, @ForAll int end,
		@ForAll long seed)
	{
		Assume.that(start < end);
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		for (int value : draw(() -> RandomIntFactory.randomIntBetween(start, end, secureRandom)))
		{
			assertTrue(start <= value && value < end,
				() -> value + " is not in [" + start + ", " + end + ")");
		}
	}

	/**
	 * {@link RandomIntFactory#randomIntBetween(int, int, SecureRandom)} spreads over [start, end)
	 * for every start and end at least 2 apart
	 */
	@Property(seed = Seeds.SEED)
	void randomIntBetweenSpreadsOverTheRange(@ForAll int start, @ForAll int end, @ForAll long seed)
	{
		Assume.that(2 <= (long)end - start);
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertSpreadsOverTheRange(
			draw(() -> RandomIntFactory.randomIntBetween(start, end, secureRandom)), start, end);
	}

	/**
	 * {@link RandomIntFactory#randomIntBetween(int, int, SecureRandom)} on a small range draws
	 * every int from start to end - 1 and no other
	 */
	@Property(seed = Seeds.SEED)
	void randomIntBetweenReachesEveryValueOfASmallRange(
		@ForAll @IntRange(min = -1000, max = 1000) int start,
		@ForAll @IntRange(min = 1, max = 9) int width, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertReachesExactly(
			draw(() -> RandomIntFactory.randomIntBetween(start, start + width, secureRandom)),
			start, start + width - 1L);
	}

	/**
	 * {@link RandomIntFactory#randomIntBetween(int, int, boolean, boolean, SecureRandom)} keeps the
	 * bounds it is asked for: start only with includeMin, end only with includeMax, for every start
	 * and end at least 2 apart, the ranges wider than Integer.MAX_VALUE included
	 */
	@Property(seed = Seeds.SEED)
	void randomIntBetweenKeepsTheBoundsForEveryStartAndEnd(@ForAll int start, @ForAll int end,
		@ForAll boolean includeMin, @ForAll boolean includeMax, @ForAll long seed)
	{
		Assume.that(2 <= (long)end - start);
		long lowest = includeMin ? start : start + 1L;
		long highest = includeMax ? end : end - 1L;
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		for (int value : draw(() -> RandomIntFactory.randomIntBetween(start, end, includeMin,
			includeMax, secureRandom)))
		{
			assertTrue(lowest <= value && value <= highest, () -> value + " is not in [" + lowest
				+ ", " + highest + "] for start " + start + " and end " + end);
		}
	}

	/**
	 * {@link RandomIntFactory#randomIntBetween(int, int, boolean, boolean, SecureRandom)} on a
	 * small range draws every int between the bounds it is asked for and no other: start only with
	 * includeMin, end only with includeMax
	 */
	@Property(seed = Seeds.SEED)
	void randomIntBetweenReachesEveryValueBetweenTheBounds(
		@ForAll @IntRange(min = -1000, max = 1000) int start,
		@ForAll @IntRange(min = 2, max = 8) int width, @ForAll boolean includeMin,
		@ForAll boolean includeMax, @ForAll long seed)
	{
		int end = start + width;
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertReachesExactly(draw(() -> RandomIntFactory.randomIntBetween(start, end, includeMin,
			includeMax, secureRandom)), includeMin ? start : start + 1L,
			includeMax ? end : end - 1L);
	}

	/**
	 * The overloads without a {@link SecureRandom},
	 * {@link RandomIntFactory#randomIntBetween(int, int)} (start included, end excluded) and
	 * {@link RandomIntFactory#randomIntBetween(int, int, boolean, boolean)}, draw from the default
	 * generator every int between the bounds and no other. With at most 5 values a correct
	 * generator misses one with a probability below 5 * (4/5)^200
	 */
	@Property(seed = Seeds.SEED)
	void overloadsWithoutSecureRandomReachEveryValueBetweenTheBounds(
		@ForAll @IntRange(min = -1000, max = 1000) int start,
		@ForAll @IntRange(min = 2, max = 4) int width, @ForAll boolean includeMin,
		@ForAll boolean includeMax)
	{
		int end = start + width;
		assertReachesExactly(draw(() -> RandomIntFactory.randomIntBetween(start, end)), start,
			end - 1L);
		assertReachesExactly(
			draw(() -> RandomIntFactory.randomIntBetween(start, end, includeMin, includeMax)),
			includeMin ? start : start + 1L, includeMax ? end : end - 1L);
	}
}
