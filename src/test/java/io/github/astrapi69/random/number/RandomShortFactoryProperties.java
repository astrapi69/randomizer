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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;

import io.github.astrapi69.random.Seeds;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

/**
 * Properties of {@link RandomShortFactory}
 */
public class RandomShortFactoryProperties
{

	/**
	 * Asserts that the shorts hold negative and positive values and do not repeat: 200 draws of a
	 * correct generator from 65536 values collide less than once on average, so fewer than 150
	 * distinct values, or a sign missed, do not happen by chance
	 */
	private static void assertDrawsAnyShort(List<Short> values)
	{
		assertTrue(values.stream().anyMatch(value -> value < 0), "no negative short");
		assertTrue(values.stream().anyMatch(value -> 0 < value), "no positive short");
		int distinct = new HashSet<>(values).size();
		assertTrue(150 <= distinct, () -> "only " + distinct + " distinct shorts");
	}

	/**
	 * {@link RandomShortFactory#randomShort(SecureRandom)} and
	 * {@link RandomShortFactory#randomShort()} draw any short
	 *
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void randomShortDrawsAnyShort(@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		assertDrawsAnyShort(draw(() -> RandomShortFactory.randomShort(secureRandom)));
		assertDrawsAnyShort(draw(RandomShortFactory::randomShort));
	}
}
