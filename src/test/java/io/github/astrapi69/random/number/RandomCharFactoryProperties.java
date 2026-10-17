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

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Stream;

import io.github.astrapi69.random.Seeds;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

/**
 * Properties of {@link RandomCharFactory}
 */
public class RandomCharFactoryProperties
{

	private static boolean isUppercaseLetter(char c)
	{
		return 'A' <= c && c <= 'Z';
	}

	private static boolean isDigit(char c)
	{
		return '0' <= c && c <= '9';
	}

	/**
	 * Every char a seeded generator draws is an uppercase letter from A to Z or a digit from 0 to
	 * 9, and both kinds occur among 200 draws
	 *
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void randomCharIsAnUppercaseLetterOrADigit(@ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		List<Character> chars = Stream.generate(() -> RandomCharFactory.randomChar(secureRandom))
			.limit(200).toList();
		for (char c : chars)
		{
			assertTrue(isUppercaseLetter(c) || isDigit(c),
				() -> "unexpected char U+" + Integer.toHexString(c) + " drawn with seed " + seed);
		}
		assertTrue(chars.stream().anyMatch(RandomCharFactoryProperties::isUppercaseLetter));
		assertTrue(chars.stream().anyMatch(RandomCharFactoryProperties::isDigit));
	}
}
