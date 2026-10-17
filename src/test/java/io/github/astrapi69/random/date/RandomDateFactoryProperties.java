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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import io.github.astrapi69.date.CalculateDateExtensions;
import io.github.astrapi69.random.Seeds;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

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
}
