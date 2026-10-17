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
import java.util.List;

import io.github.astrapi69.random.Seeds;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

/**
 * Properties of {@link RandomByteFactory}
 */
public class RandomByteFactoryProperties
{

	/**
	 * Asserts that the bytes hold negative and positive values. A correct generator draws a
	 * negative byte with a probability near one half, so it misses one sign among 200 draws with a
	 * probability far below 2^-100
	 */
	private static void assertBothSigns(List<Byte> values)
	{
		assertTrue(values.stream().anyMatch(value -> value < 0), "no negative byte");
		assertTrue(values.stream().anyMatch(value -> 0 < value), "no positive byte");
	}

	private static long zeros(byte[] bytes)
	{
		long zeros = 0;
		for (byte b : bytes)
		{
			if (b == 0)
			{
				zeros++;
			}
		}
		return zeros;
	}

	/**
	 * {@link RandomByteFactory#randomByte(SecureRandom)} and {@link RandomByteFactory#randomByte()}
	 * draw negative and positive bytes
	 *
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void randomByteDrawsBothSigns(@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertBothSigns(draw(() -> RandomByteFactory.randomByte(secureRandom)));
		assertBothSigns(draw(RandomByteFactory::randomByte));
	}

	/**
	 * {@link RandomByteFactory#randomByteArray(int, SecureRandom)} has the requested length, and of
	 * 100 and more bytes at most a tenth are 0: a correct generator draws 0 with a probability of
	 * about 1/256
	 *
	 * @param length
	 *            the length
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void randomByteArrayHasTheLengthAndFewZeros(@ForAll @IntRange(max = 1000) int length,
		@ForAll long seed)
	{
		byte[] bytes = RandomByteFactory.randomByteArray(length, Seeds.secureRandom(seed));
		assertEquals(length, bytes.length);
		if (100 <= length)
		{
			assertTrue(zeros(bytes) <= length / 10, () -> zeros(bytes) + " zeros in " + length);
		}
	}

	/**
	 * {@link RandomByteFactory#randomByteArray(int)} has the requested length, and of 100 and more
	 * bytes at most a tenth are 0
	 *
	 * @param length
	 *            the length
	 */
	@Property(seed = Seeds.SEED)
	void randomByteArrayWithoutSecureRandomHasTheLengthAndFewZeros(
		@ForAll @IntRange(max = 1000) int length)
	{
		byte[] bytes = RandomByteFactory.randomByteArray(length);
		assertEquals(length, bytes.length);
		if (100 <= length)
		{
			assertTrue(zeros(bytes) <= length / 10, () -> zeros(bytes) + " zeros in " + length);
		}
	}
}
