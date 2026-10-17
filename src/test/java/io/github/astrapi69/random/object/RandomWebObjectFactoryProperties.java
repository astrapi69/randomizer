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

import io.github.astrapi69.random.Seeds;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Combinators;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

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
}
