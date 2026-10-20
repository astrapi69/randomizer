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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import java.util.Date;
import java.util.HashSet;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import io.github.astrapi69.random.date.RandomDateFactory;
import io.github.astrapi69.random.number.RandomFloatFactory;
import io.github.astrapi69.random.number.RandomLongFactory;

/**
 * The overloads that take no {@link java.security.SecureRandom} draw from the library's shared
 * generator, {@link DefaultSecureRandom}: consecutive calls do not repeat a value. One row per
 * overload; each draws from a range of at least ten thousand values, where 200 draws of a correct
 * generator collide about twice, so fewer than 150 distinct values do not happen by chance.
 */
public class OverloadsWithoutSecureRandomTest
{

	private static final Date FROM = new Date(1_700_000_000_000L);

	private static final Date ONE_YEAR_LATER = new Date(FROM.getTime() + 365L * 86_400_000L);

	static Stream<Arguments> overloads()
	{
		return Stream.of(
			argumentSet("RandomLongFactory.randomLongBetween(long, long)",
				(Supplier<Object>)() -> RandomLongFactory.randomLongBetween(0, 1_000_000)),
			argumentSet("RandomFloatFactory.randomFloatBetween(float, float)",
				(Supplier<Object>)() -> RandomFloatFactory.randomFloatBetween(0f, 1_000_000f)),
			argumentSet("RandomDateFactory.randomDateBefore(Date, int)",
				(Supplier<Object>)() -> RandomDateFactory.randomDateBefore(FROM, 10_000)),
			argumentSet("RandomDateFactory.randomDate()",
				(Supplier<Object>)RandomDateFactory::randomDate),
			argumentSet("RandomDateFactory.randomDate(Date)",
				(Supplier<Object>)() -> RandomDateFactory.randomDate(FROM)),
			argumentSet("RandomDateFactory.randomDatebetween(Date, Date)",
				(Supplier<Object>)() -> RandomDateFactory.randomDatebetween(FROM, ONE_YEAR_LATER)),
			argumentSet("RandomDateFactory.randomDatebetween(long, long)",
				(Supplier<Object>)() -> RandomDateFactory.randomDatebetween(FROM.getTime(),
					ONE_YEAR_LATER.getTime())));
	}

	@ParameterizedTest
	@MethodSource("overloads")
	void consecutiveCallsDoNotRepeatAValue(Supplier<Object> overload)
	{
		int distinct = new HashSet<>(Draws.draw(overload)).size();
		assertTrue(150 <= distinct, () -> "only " + distinct + " distinct values in 200 calls");
	}
}
