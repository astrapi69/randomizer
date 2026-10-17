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
package io.github.astrapi69.random.object;

import static io.github.astrapi69.random.Draws.draw;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.github.astrapi69.random.RandomCharacters;
import io.github.astrapi69.random.Seeds;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.CharRange;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.StringLength;
import net.jqwik.api.constraints.UniqueElements;

/**
 * Properties of {@link RandomStringFactory}
 */
public class RandomStringFactoryProperties
{

	/**
	 * Asserts that the string has the length and consists of chars of the alphabet
	 */
	private static void assertLengthAndAlphabet(String string, long length, String alphabet)
	{
		assertEquals(length, string.length(), string);
		string.chars().forEach(c -> assertTrue(0 <= alphabet.indexOf(c),
			() -> "U+" + Integer.toHexString(c) + " is not in " + alphabet));
	}

	/**
	 * {@link RandomStringFactory#newRandomString(String, int)} and
	 * {@link RandomStringFactory#newRandomLongString(String, long)} have the requested length and
	 * draw only from the given chars
	 *
	 * @param chars
	 *            the chars to draw from
	 * @param length
	 *            the length
	 */
	@Property(seed = Seeds.SEED)
	void stringFromCharsHasTheLengthAndTheChars(
		@ForAll @StringLength(min = 1, max = 20) @CharRange(from = '!', to = '~') String chars,
		@ForAll @IntRange(max = 200) int length)
	{
		assertLengthAndAlphabet(RandomStringFactory.newRandomString(chars, length), length, chars);
		assertLengthAndAlphabet(RandomStringFactory.newRandomLongString(chars, length), length,
			chars);
	}

	/**
	 * {@link RandomStringFactory#newRandomString(int)} has the requested length up to 1024 and
	 * consists of uppercase letters and digits, the chars
	 * {@link io.github.astrapi69.random.number.RandomCharFactory#randomChar()} draws
	 *
	 * @param length
	 *            the length
	 */
	@Property(seed = Seeds.SEED)
	void stringOfALengthHasTheLength(@ForAll @IntRange(max = 1024) int length)
	{
		assertLengthAndAlphabet(RandomStringFactory.newRandomString(length), length,
			RandomCharacters.uppercaseWithNumbers.getCharacters());
	}

	/**
	 * {@link RandomStringFactory#newRandomLongString(long)} has the requested length and draws from
	 * letters, digits, special chars, escape sequences and the blank
	 *
	 * @param length
	 *            the length
	 */
	@Property(seed = Seeds.SEED)
	void longStringHasTheLength(@ForAll @IntRange(max = 1000) int length)
	{
		assertLengthAndAlphabet(RandomStringFactory.newRandomLongString(length), length,
			RandomCharacters.lowcaseWithUppercaseAndNumbersAndSpecial.getCharacters()
				+ RandomCharacters.escapeSequencesWithWhitespace.getCharacters());
	}

	/**
	 * {@link RandomStringFactory#newRandomString()} has 3 to 25 letters and digits, and
	 * {@link RandomStringFactory#newRandomString(int, int)} a length in [start, end)
	 *
	 * @param start
	 *            the shortest length
	 * @param width
	 *            the number of possible lengths
	 */
	@Property(seed = Seeds.SEED)
	void stringsOfALengthBetweenHaveALengthInTheRange(@ForAll @IntRange(max = 100) int start,
		@ForAll @IntRange(min = 1, max = 100) int width)
	{
		String string = RandomStringFactory.newRandomString();
		assertTrue(3 <= string.length() && string.length() <= 25, string);
		assertTrue(string.matches("[a-zA-Z0-9]*"), string);
		int length = RandomStringFactory.newRandomString(start, start + width).length();
		assertTrue(start <= length && length < start + width,
			() -> length + " is not in [" + start + ", " + (start + width) + ")");
	}

	/**
	 * {@link RandomStringFactory#newRandomString(String[])} draws every element of an array of at
	 * most 5 and nothing else (a correct generator misses one with a probability below 5 *
	 * (4/5)^200)
	 *
	 * @param elements
	 *            the elements of the array
	 */
	@Property(seed = Seeds.SEED)
	void stringFromAnArrayReachesEveryElement(
		@ForAll @Size(min = 1, max = 5) @UniqueElements List<String> elements)
	{
		String[] array = elements.toArray(String[]::new);
		Set<String> drawn = new HashSet<>(draw(() -> RandomStringFactory.newRandomString(array)));
		assertEquals(new HashSet<>(elements), drawn);
	}

	/**
	 * {@link RandomStringFactory#randomHexString(int)} has the requested number of hexadecimal
	 * digits
	 *
	 * @param length
	 *            the number of digits
	 */
	@Property(seed = Seeds.SEED)
	void hexStringHasTheLength(@ForAll @IntRange(max = 200) int length)
	{
		assertLengthAndAlphabet(RandomStringFactory.randomHexString(length), length,
			"0123456789abcdef");
	}
}
