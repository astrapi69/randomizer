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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import io.github.astrapi69.random.Draws;
import io.github.astrapi69.random.Seeds;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

/**
 * Properties of {@link RandomWebObjectFactory}
 */
public class RandomWebObjectFactoryProperties
{

	/**
	 * Host names such as example.com: one to twelve lowercase letters, a dot and a two letter top
	 * level domain
	 *
	 * @return the arbitrary of the host names
	 */
	@Provide
	Arbitrary<String> hosts()
	{
		Arbitrary<String> domain = Arbitraries.strings().withCharRange('a', 'z').ofMinLength(1)
			.ofMaxLength(12);
		Arbitrary<String> topDomain = Arbitraries.strings().withCharRange('a', 'z').ofLength(2);
		return Combinators.combine(domain, topDomain).as((d, t) -> d + "." + t);
	}

	/**
	 * What may stand in front of "www.": nothing or a scheme
	 *
	 * @return the arbitrary of the prefixes
	 */
	@Provide
	Arbitrary<String> prefixes()
	{
		return Arbitraries.of("", "http://", "https://");
	}

	/**
	 * The infomail address of a url is info@ and the host that follows "www.", whether the url
	 * starts with a scheme or with "www."
	 *
	 * @param prefix
	 *            what stands in front of "www."
	 * @param host
	 *            the host
	 */
	@Property(seed = Seeds.SEED)
	void infomailIsInfoAtTheHostAfterWww(@ForAll("prefixes") String prefix,
		@ForAll("hosts") String host)
	{
		assertEquals("info@" + host,
			RandomWebObjectFactory.getInfomailFromWebsite(prefix + "www." + host));
	}

	/**
	 * A url without "www." has no infomail address: an {@link IllegalArgumentException}
	 *
	 * @param prefix
	 *            what stands in front of the host
	 * @param host
	 *            the host
	 */
	@Property(seed = Seeds.SEED)
	void urlWithoutWwwHasNoInfomail(@ForAll("prefixes") String prefix, @ForAll("hosts") String host)
	{
		assertThrows(IllegalArgumentException.class,
			() -> RandomWebObjectFactory.getInfomailFromWebsite(prefix + host));
	}

	/**
	 * Asserts that every value the generator draws matches the pattern
	 */
	private static void assertEveryValueMatches(Supplier<String> generator, int draws,
		String pattern)
	{
		for (String value : Draws.draw(generator, draws))
		{
			assertTrue(value.matches(pattern), () -> value + " does not match " + pattern);
		}
	}

	/**
	 * Every generated address, number, website and id has its shape. The generators draw the
	 * lengths of their parts from the default generator; with 500 draws each length from 1 to 20
	 * occurs, so a part of length 0 would show, with a probability above 1 - (19/20)^500
	 */
	@Property(seed = Seeds.SEED, tries = 1)
	void generatedWebObjectsHaveTheirShape()
	{
		assertEveryValueMatches(RandomWebObjectFactory::randomEmail, 500,
			"[a-z0-9]{1,20}@[a-z]{1,12}\\.[a-z]{2}");
		assertEveryValueMatches(RandomWebObjectFactory::randomWebsite, 500,
			"http://www\\.[a-z]{1,12}\\.[a-z]{2}");
		assertEveryValueMatches(RandomWebObjectFactory::randomMobilnumber, 200, "0\\d{3}/\\d{7}");
		assertEveryValueMatches(RandomWebObjectFactory::randomPhonenumber, 200, "0\\d{4}/\\d{7}");
		assertEveryValueMatches(RandomWebObjectFactory::randomId, 200,
			"[a-zA-Z0-9]{2}\\.[a-zA-Z0-9]{4}\\.[a-zA-Z0-9]{2}\\.\\d+\\.[a-zA-Z0-9]{2}");
	}

	/**
	 * A password has the requested length and consists of letters and digits; without a length it
	 * has 8 characters
	 *
	 * @param length
	 *            the length
	 */
	@Property(seed = Seeds.SEED)
	void passwordHasTheLength(@ForAll @IntRange(max = 64) int length)
	{
		String pattern = "[a-zA-Z0-9]{" + length + "}";
		assertTrue(RandomWebObjectFactory.randomPassword(length).matches(pattern));
		assertTrue(RandomWebObjectFactory.randomPassword(Optional.of(length)).matches(pattern));
		assertTrue(
			RandomWebObjectFactory.randomPassword(Optional.empty()).matches("[a-zA-Z0-9]{8}"));
	}

	/**
	 * {@link RandomWebObjectFactory#randomName(char[])} is a permutation of the given chars: the
	 * same chars, each as often
	 *
	 * @param chars
	 *            the chars of the name
	 */
	@Property(seed = Seeds.SEED)
	void randomNameIsAPermutationOfTheChars(@ForAll @Size(min = 1, max = 30) List<Character> chars)
	{
		char[] donated = new char[chars.size()];
		for (int i = 0; i < donated.length; i++)
		{
			donated[i] = chars.get(i);
		}
		String name = RandomWebObjectFactory.randomName(donated);
		List<Character> expected = new ArrayList<>(chars);
		List<Character> actual = new ArrayList<>();
		name.chars().forEach(c -> actual.add((char)c));
		Collections.sort(expected);
		Collections.sort(actual);
		assertEquals(expected, actual);
	}
}
