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
package io.github.astrapi69.random.date;

import static io.github.astrapi69.random.Draws.draw;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import io.github.astrapi69.date.CalculateDateExtensions;
import io.github.astrapi69.random.Seeds;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.LongRange;

/**
 * Properties of {@link RandomDateFactory}. Days are counted with {@link CalculateDateExtensions},
 * the library the factory computes with, so a change to or from daylight saving time between two
 * dates does not count as a different number of days.
 */
public class RandomDateFactoryProperties
{

	/** 2023-11-14, a fixed date to count from */
	private static final Date FROM = new Date(1_700_000_000_000L);

	private static void assertBetween(Date lowest, Date highest, Date date)
	{
		assertTrue(!date.before(lowest) && !date.after(highest),
			() -> date + " is not between " + lowest + " and " + highest);
	}

	/**
	 * {@link RandomDateFactory#randomDateBetween(Date, int, int)} lies startDays to endDays - 1
	 * days after the date
	 *
	 * @param startDays
	 *            the first day after the date
	 * @param width
	 *            the number of days in the range
	 */
	@Property(seed = Seeds.SEED)
	void randomDateBetweenDaysLiesInTheDays(@ForAll @IntRange(max = 1000) int startDays,
		@ForAll @IntRange(min = 1, max = 1000) int width)
	{
		Date date = RandomDateFactory.randomDateBetween(FROM, startDays, startDays + width);
		assertBetween(CalculateDateExtensions.addDays(FROM, startDays),
			CalculateDateExtensions.addDays(FROM, startDays + width - 1), date);
	}

	/**
	 * {@link RandomDateFactory#randomDateBetween(Date, Date, SecureRandom)} lies in [start, end)
	 * and spreads over it, a date in each half, before 1970 as after
	 *
	 * @param start
	 *            the start in milliseconds since 1970
	 * @param width
	 *            the length of the range in milliseconds
	 * @param seed
	 *            the seed of the generator
	 */
	@Property(seed = Seeds.SEED)
	void randomDateBetweenLiesInTheRange(
		@ForAll @LongRange(min = -4_000_000_000_000L, max = 4_000_000_000_000L) long start,
		@ForAll @LongRange(min = 2, max = 4_000_000_000_000L) long width, @ForAll long seed)
	{
		SecureRandom secureRandom = Seeds.secureRandom(seed);
		Date from = new Date(start);
		Date till = new Date(start + width);
		List<Date> dates = draw(
			() -> RandomDateFactory.randomDateBetween(from, till, secureRandom));
		dates.forEach(date -> assertTrue(start <= date.getTime() && date.getTime() < start + width,
			() -> date.getTime() + " is not in [" + start + ", " + (start + width) + ")"));
		// 2 * time < 2 * start + width: below the middle, which may lie between two milliseconds
		assertTrue(dates.stream().anyMatch(date -> 2 * date.getTime() < 2 * start + width),
			"no date in the first half");
		assertTrue(dates.stream().anyMatch(date -> 2 * start + width <= 2 * date.getTime()),
			"no date in the second half");
	}

	/**
	 * {@link RandomDateFactory#randomBirthday()} lies, as documented, between 9 and 55 years ago:
	 * between 20000 days (about 55 years) and 3000 days (about 9 years) before now, the bounds the
	 * method names
	 */
	@Property(seed = Seeds.SEED, tries = 200)
	void birthdayLiesBetween9And55YearsAgo()
	{
		Date before = new Date();
		Date birthday = RandomDateFactory.randomBirthday();
		Date after = new Date();
		assertBetween(CalculateDateExtensions.substractDaysFromDate(before, 20_000),
			CalculateDateExtensions.substractDaysFromDate(after, 3_000), birthday);
	}
}
