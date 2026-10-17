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
package io.github.astrapi69.random;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Stream;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

/**
 * Properties of {@link SecureRandomBuilder}
 */
public class SecureRandomBuilderProperties
{

	private static List<Long> twentyLongs(SecureRandom secureRandom)
	{
		return Stream.generate(secureRandom::nextLong).limit(20).toList();
	}

	/**
	 * Two generators built with the same algorithm, provider and seed draw the same sequence, for
	 * every seed including 0
	 *
	 * @param seed
	 *            the seed
	 */
	@Property(seed = Seeds.SEED)
	void sameSeedGivesTheSameSequence(@ForAll long seed)
	{
		SecureRandomBuilder builder = SecureRandomBuilder.getInstance(
			SecureRandomBuilder.DEFAULT_ALGORITHM, SecureRandomBean.DEFAULT_PROVIDER, seed);
		assertEquals(twentyLongs(builder.build()), twentyLongs(builder.build()),
			() -> "seed " + seed);
	}
}
