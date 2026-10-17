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
import net.jqwik.api.constraints.FloatRange;

/**
 * Properties of {@link RandomFloatFactory}
 */
public class RandomFloatFactoryProperties
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
	private static void assertInRange(List<Float> values, float start, float end)
	{
		values.forEach(value -> assertTrue(start <= value && value <= end,
			() -> value + " is not in [" + start + ", " + end + "]"));
	}

	/**
	 * Asserts that the values lie on both sides of the middle of [start, end); a correct generator
	 * misses one side with a probability of 2 * 2^-200
	 */
	private static void assertSpreadsOverTheRange(List<Float> values, float start, float end)
	{
		float middle = start / 2 + end / 2;
		assertTrue(values.stream().anyMatch(value -> value < middle),
			() -> "no value below " + middle);
		assertTrue(values.stream().anyMatch(value -> middle <= value),
			() -> "no value from " + middle + " up");
	}

	/**
	 * For every algorithm,
	 * {@link RandomFloatFactory#randomFloat(float, RandomAlgorithm, SecureRandom)} lies in [0,
	 * range]
	 */
	@Property(seed = Seeds.SEED)
	void randomFloatLiesInTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @FloatRange(min = 1) float range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertInRange(draw(() -> RandomFloatFactory.randomFloat(range, algorithm, secureRandom)), 0,
			range);
	}

	/**
	 * For every algorithm, the values spread over the whole range: among 200 draws there is one
	 * below the middle of the range and one above it
	 */
	@Property(seed = Seeds.SEED)
	void randomFloatSpreadsOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @FloatRange(min = 2) float range, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertSpreadsOverTheRange(
			draw(() -> RandomFloatFactory.randomFloat(range, algorithm, secureRandom)), 0, range);
	}

	/**
	 * For every algorithm, 200 draws from a range of a million hold at least 150 distinct values; a
	 * generator that returns the same value again and again (for example because it reseeds from
	 * the clock on every call) does not
	 */
	@Property(seed = Seeds.SEED)
	void randomFloatDoesNotRepeatItself(@ForAll RandomAlgorithm algorithm, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		int distinct = new HashSet<>(
			draw(() -> RandomFloatFactory.randomFloat(1_000_000f, algorithm, secureRandom))).size();
		assertTrue(150 <= distinct,
			() -> "only " + distinct + " distinct values with " + algorithm);
	}

	/**
	 * For every algorithm that draws from the given {@link SecureRandom}, the same seed gives the
	 * same sequence
	 */
	@Property(seed = Seeds.SEED)
	void sameSeedGivesTheSameFloats(@ForAll("seedableAlgorithms") RandomAlgorithm algorithm,
		@ForAll @FloatRange(min = 1) float range, @ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		assertEquals(draw(() -> RandomFloatFactory.randomFloat(range, algorithm, first)),
			draw(() -> RandomFloatFactory.randomFloat(range, algorithm, second)),
			algorithm::toString);
	}

	/**
	 * {@link RandomFloatFactory#randomFloat(SecureRandom)} lies in [0, 1) and spreads over it, the
	 * same seed giving the same sequence
	 */
	@Property(seed = Seeds.SEED)
	void randomFloatWithoutRangeLiesInTheUnitInterval(@ForAll long seed)
	{
		SecureRandom first = Seeds.secureRandom(seed);
		SecureRandom second = Seeds.secureRandom(seed);
		List<Float> values = draw(() -> RandomFloatFactory.randomFloat(first));
		assertEquals(values, draw(() -> RandomFloatFactory.randomFloat(second)));
		values.forEach(
			value -> assertTrue(0 <= value && value < 1, () -> value + " is not in [0, 1)"));
		assertSpreadsOverTheRange(values, 0, 1);
	}

	/**
	 * The overloads without a {@link SecureRandom}: {@link RandomFloatFactory#randomFloat(float)}
	 * and {@link RandomFloatFactory#randomFloat(float, RandomAlgorithm)} draw from the default
	 * generator over [0, range], {@link RandomFloatFactory#randomFloat()} over [0, 1)
	 */
	@Property(seed = Seeds.SEED)
	void overloadsWithoutSecureRandomDrawOverTheRange(@ForAll RandomAlgorithm algorithm,
		@ForAll @FloatRange(min = 2) float range)
	{
		List<Float> values = draw(() -> RandomFloatFactory.randomFloat(range, algorithm));
		assertInRange(values, 0, range);
		assertSpreadsOverTheRange(values, 0, range);
		List<Float> secureValues = draw(() -> RandomFloatFactory.randomFloat(range));
		assertInRange(secureValues, 0, range);
		assertSpreadsOverTheRange(secureValues, 0, range);
		List<Float> unitValues = draw(RandomFloatFactory::randomFloat);
		unitValues.forEach(
			value -> assertTrue(0 <= value && value < 1, () -> value + " is not in [0, 1)"));
		assertSpreadsOverTheRange(unitValues, 0, 1);
	}

	/**
	 * {@link RandomFloatFactory#randomFloatBetween(float, float, SecureRandom)} and
	 * {@link RandomFloatFactory#randomFloatBetween(float, float)} lie in [start, end] and spread
	 * over it, for every start below end whose distance is a finite float
	 */
	@Property(seed = Seeds.SEED)
	void randomFloatBetweenLiesInTheRange(@ForAll float start, @ForAll float end, @ForAll long seed)
	{
		Assume.that(start < end && Float.isFinite(end - start));
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		List<Float> values = draw(
			() -> RandomFloatFactory.randomFloatBetween(start, end, secureRandom));
		assertInRange(values, start, end);
		assertSpreadsOverTheRange(values, start, end);
		List<Float> defaultValues = draw(() -> RandomFloatFactory.randomFloatBetween(start, end));
		assertInRange(defaultValues, start, end);
		assertSpreadsOverTheRange(defaultValues, start, end);
	}

	/**
	 * {@link RandomFloatFactory#randomFloat(float, SecureRandom)}, the SECURE_RANDOM algorithm,
	 * lies in [0, range] and spreads over it
	 */
	@Property(seed = Seeds.SEED)
	void randomFloatWithSecureRandomDrawsOverTheRange(@ForAll @FloatRange(min = 2) float range,
		@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		List<Float> values = draw(() -> RandomFloatFactory.randomFloat(range, secureRandom));
		assertInRange(values, 0, range);
		assertSpreadsOverTheRange(values, 0, range);
	}
}
