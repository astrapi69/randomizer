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

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Draws a fixed number of values from a generator, for the property-based tests.
 * <p>
 * A property over a generator states what holds for every value it draws (it lies in the range),
 * and what holds for the values together (they spread over the range, they do not repeat). For the
 * second kind the properties draw {@link #COUNT} values, or more where a property needs them; each
 * such property names the probability that a correct generator fails it. With a seeded generator
 * the draws are the same on every run anyway; for the overloads that draw from the library's
 * DefaultSecureRandom or Math.random(), which no seed reaches, the probability stays below 10^-18.
 */
public final class Draws
{

	/** The number of values a property draws from one generator */
	public static final int COUNT = 200;

	private Draws()
	{
	}

	/**
	 * Draws {@link #COUNT} values from the given generator
	 *
	 * @param <T>
	 *            the type of the values
	 * @param generator
	 *            the generator
	 * @return the values in the order drawn
	 */
	public static <T> List<T> draw(Supplier<T> generator)
	{
		return draw(generator, COUNT);
	}

	/**
	 * Draws the given number of values from the given generator
	 *
	 * @param <T>
	 *            the type of the values
	 * @param generator
	 *            the generator
	 * @param count
	 *            the number of values
	 * @return the values in the order drawn
	 */
	public static <T> List<T> draw(Supplier<T> generator, int count)
	{
		return Stream.generate(generator).limit(count).toList();
	}
}
