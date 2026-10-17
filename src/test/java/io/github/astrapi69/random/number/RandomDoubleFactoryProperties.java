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

import io.github.astrapi69.random.Seeds;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.DoubleRange;

/**
 * Properties of {@link RandomDoubleFactory}
 */
public class RandomDoubleFactoryProperties
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
	 * Asserts that the values lie in [start, end]: the documentation promises a value "not greater
	 * than the range"
	 */
	private static void assertInRange(List<Double> values, double start, double end)
	{
		values.forEach(value -> assertTrue(start <= value && value <= end,
			() -> value + " is not in [" + start + ", " + end + "]"));
	}

	/**
	 * Asserts that the values lie on both sides of the middle of [start, end); a correct generator
	 * misses one side with a probability of 2 * 2^-200
	 */
	private static void assertSpreadsOverTheRange(List<Double> values, double start, double end)
	{
		double middle = start / 2 + end / 2;
		assertTrue(values.stream().anyMatch(value -> value < middle),
			() -> "no value below " + middle);
		assertTrue(values.stream().anyMatch(value -> middle <= value),
			() -> "no value from " + middle + " up");
	}

	/**
	 * For every algorithm,
	 * {@link RandomDoubleFactory#randomDouble(double, RandomAlgorithm, SecureRandom)} lies in [0,
	 * range]
	 */
	@Property(seed = Seeds.SEED)
	void randomDoubleLiesInTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @DoubleRange(min = 1) double range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertInRange(draw(() -> RandomDoubleFactory.randomDouble(range, algorithm, secureRandom)),
			0, range);
	}

	/**
	 * For every algorithm, the values spread over the whole range: among 200 draws there is one
	 * below the middle of the range and one above it
	 */
	@Property(seed = Seeds.SEED)
	void randomDoubleSpreadsOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @DoubleRange(min = 2) double range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertSpreadsOverTheRange(
			draw(() -> RandomDoubleFactory.randomDouble(range, algorithm, secureRandom)), 0, range);
	}

	/**
	 * For every algorithm, 200 draws from a range of a million hold at least 150 distinct values; a
	 * generator that returns the same value again and again (for example because it reseeds from
	 * the clock on every call) does not
	 */
	@Property(seed = Seeds.SEED)
	void randomDoubleDoesNotRepeatItself(@ForAll RandomAlgorithm algorithm, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		int distinct = new HashSet<>(
			draw(() -> RandomDoubleFactory.randomDouble(1_000_000d, algorithm, secureRandom)))
				.size();
		assertTrue(150 <= distinct,
			() -> "only " + distinct + " distinct values with " + algorithm);
	}

	/**
	 * For every algorithm that draws from the given {@link SecureRandom}, the same seed gives the
	 * same sequence
	 */
	@Property(seed = Seeds.SEED)
	void sameSeedGivesTheSameDoubles(@ForAll("seedableAlgorithms") RandomAlgorithm algorithm,
		@ForAll @DoubleRange(min = 1) double range, @ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		assertEquals(draw(() -> RandomDoubleFactory.randomDouble(range, algorithm, first)),
			draw(() -> RandomDoubleFactory.randomDouble(range, algorithm, second)),
			algorithm::toString);
	}

	/**
	 * {@link RandomDoubleFactory#randomDouble(SecureRandom)} lies in [0, 1) and spreads over it,
	 * the same seed giving the same sequence
	 */
	@Property(seed = Seeds.SEED)
	void randomDoubleWithoutRangeLiesInTheUnitInterval(@ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		List<Double> values = draw(() -> RandomDoubleFactory.randomDouble(first));
		assertEquals(values, draw(() -> RandomDoubleFactory.randomDouble(second)));
		values.forEach(
			value -> assertTrue(0 <= value && value < 1, () -> value + " is not in [0, 1)"));
		assertSpreadsOverTheRange(values, 0, 1);
	}

	/**
	 * The overloads without a {@link SecureRandom}:
	 * {@link RandomDoubleFactory#randomDouble(double)} and
	 * {@link RandomDoubleFactory#randomDouble(double, RandomAlgorithm)} draw from the default
	 * generator over [0, range], {@link RandomDoubleFactory#randomDouble()} over [0,
	 * Double.MAX_VALUE]
	 */
	@Property(seed = Seeds.SEED)
	void overloadsWithoutSecureRandomDrawOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @DoubleRange(min = 2) double range)
	{
		List<Double> values = draw(() -> RandomDoubleFactory.randomDouble(range, algorithm));
		assertInRange(values, 0, range);
		assertSpreadsOverTheRange(values, 0, range);
		List<Double> secureValues = draw(() -> RandomDoubleFactory.randomDouble(range));
		assertInRange(secureValues, 0, range);
		assertSpreadsOverTheRange(secureValues, 0, range);
		List<Double> anyValues = draw(RandomDoubleFactory::randomDouble);
		assertInRange(anyValues, 0, Double.MAX_VALUE);
		assertSpreadsOverTheRange(anyValues, 0, Double.MAX_VALUE);
	}

	/**
	 * {@link RandomDoubleFactory#randomDoubleBetween(double, double, SecureRandom)} and
	 * {@link RandomDoubleFactory#randomDoubleBetween(double, double)} lie in [start, end] and
	 * spread over it, for every start below end whose distance is a finite double
	 */
	@Property(seed = Seeds.SEED)
	void randomDoubleBetweenLiesInTheRange(@ForAll double start, @ForAll double end,
		@ForAll long seed)
	{
		Assume.that(start < end && Double.isFinite(end - start));
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		List<Double> values = draw(
			() -> RandomDoubleFactory.randomDoubleBetween(start, end, secureRandom));
		assertInRange(values, start, end);
		assertSpreadsOverTheRange(values, start, end);
		List<Double> defaultValues = draw(
			() -> RandomDoubleFactory.randomDoubleBetween(start, end));
		assertInRange(defaultValues, start, end);
		assertSpreadsOverTheRange(defaultValues, start, end);
	}
}
