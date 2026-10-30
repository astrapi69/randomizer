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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.astrapi69.random.Seeds;

/**
 * What the factories promise about their range, checked over a large sample from a seeded
 * generator, so every run draws the same values (#32). Each count of a uniform draw has to lie
 * within a fifth of what is expected: wide enough for chance at this sample size, far too narrow
 * for a value that is never drawn or drawn at half the rate.
 */
class RandomRangePromisesTest
{

	private static final long SEED = 20261030L;

	private static final int PER_VALUE = 2000;

	private static void assertUniformOverAllBytes(final long[] counts, final String what)
	{
		long total = 0;
		for (long count : counts)
		{
			total += count;
		}
		double expected = total / 256.0;
		for (int value = 0; value < 256; value++)
		{
			assertTrue(counts[value] > expected * 0.8 && counts[value] < expected * 1.2,
				what + ": the value 0x" + Integer.toHexString(value) + " was drawn " + counts[value]
					+ " times, where about " + Math.round(expected) + " are expected");
		}
	}

	@Test
	@DisplayName("randomByte draws all 256 byte values, each about equally often")
	void randomByte_drawsEveryByteValue_equallyOften()
	{
		SecureRandom secureRandom = Seeds.secureRandom(SEED);
		long[] counts = new long[256];

		for (int i = 0; i < 256 * PER_VALUE; i++)
		{
			counts[RandomByteFactory.randomByte(secureRandom) & 0xFF]++;
		}

		assertUniformOverAllBytes(counts, "randomByte");
	}

	@Test
	@DisplayName("randomByteArray fills its array with all 256 byte values, each about equally often")
	void randomByteArray_drawsEveryByteValue_equallyOften()
	{
		long[] counts = new long[256];

		for (byte value : RandomByteFactory.randomByteArray(256 * PER_VALUE,
			Seeds.secureRandom(SEED)))
		{
			counts[value & 0xFF]++;
		}

		assertUniformOverAllBytes(counts, "randomByteArray");
	}

	@Test
	@DisplayName("randomInt() draws from the whole int range, not 0 to 9")
	void randomInt_drawsFromTheWholeIntRange()
	{
		SecureRandom secureRandom = Seeds.secureRandom(SEED);
		boolean negative = false;
		boolean aboveNine = false;

		for (int i = 0; i < 1000; i++)
		{
			int value = RandomIntFactory.randomInt(secureRandom);
			negative |= value < 0;
			aboveNine |= value > 9;
		}

		assertTrue(negative && aboveNine,
			"1000 draws from the whole int range give negative values and values above 9");
	}

	@Test
	@DisplayName("randomFloat() draws from 0.0 inclusive to 1.0 exclusive")
	void randomFloat_drawsFromZeroToOne()
	{
		SecureRandom secureRandom = Seeds.secureRandom(SEED);
		float max = 0;

		for (int i = 0; i < 10_000; i++)
		{
			float value = RandomFloatFactory.randomFloat(secureRandom);
			assertTrue(0.0f <= value && value < 1.0f, "outside [0.0, 1.0): " + value);
			max = Math.max(max, value);
		}

		assertTrue(max > 0.99f, "10000 draws reach close to 1.0, the highest was " + max);
	}

	@Test
	@DisplayName("randomIntBetween and randomLongBetween include the start and exclude the end")
	void theBetweenMethods_includeTheStart_andExcludeTheEnd()
	{
		int minInt = Integer.MAX_VALUE;
		int maxInt = Integer.MIN_VALUE;
		long minLong = Long.MAX_VALUE;
		long maxLong = Long.MIN_VALUE;

		for (int i = 0; i < 10_000; i++)
		{
			int i3to6 = RandomIntFactory.randomIntBetween(3, 6);
			long l3to6 = RandomLongFactory.randomLongBetween(3, 6);
			minInt = Math.min(minInt, i3to6);
			maxInt = Math.max(maxInt, i3to6);
			minLong = Math.min(minLong, l3to6);
			maxLong = Math.max(maxLong, l3to6);
		}

		assertEquals(3, minInt);
		assertEquals(5, maxInt);
		assertEquals(3L, minLong);
		assertEquals(5L, maxLong);
	}
}
