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
package de.alpharogroup.random.date;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.date.CalculateDateExtensions;
import de.alpharogroup.date.ConvertDateExtensions;
import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.random.SecureRandomFactory;
import de.alpharogroup.random.number.RandomPrimitivesExtensions;

/**
 * The class {@link RandomDateExtensions} is a utility class for creating random random dates.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public final class RandomDateExtensions
{

	/**
	 * Creates a random Date that is after from the given Date.
	 *
	 * @param date
	 *            The Date from where to compute the future date.
	 *
	 * @return The random Date in the future.
	 */
	public static Date dateAfter(final Date date)
	{
		return dateAfter(date, RandomPrimitivesExtensions.randomInt(10000));
	}

	/**
	 * Creates a random Date that is after from the given Date.
	 *
	 * @param date
	 *            The Date from where to compute the future date.
	 * @param range
	 *            The range.
	 *
	 * @return The random Date in the future.
	 */
	public static Date dateAfter(final Date date, final int range)
	{
		return CalculateDateExtensions.addDays(date, RandomPrimitivesExtensions.randomInt(range));
	}

	/**
	 * Creates a random date that is before from the given date.
	 *
	 * @param date
	 *            The date from where to compute the Past date.
	 *
	 * @return The random Date in the past.
	 */
	public static Date dateBefore(final Date date)
	{
		return dateBefore(date, 10000);
	}

	/**
	 * Creates a random date that is before from the given date.
	 *
	 * @param date
	 *            The date from where to compute the past date.
	 * @param range
	 *            The range.
	 *
	 * @return The random Date in the past.
	 */
	public static Date dateBefore(final Date date, final int range)
	{
		return RandomDateFactory.dateBefore(date, range, SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Creates a java.sql.Timestamp from now.
	 *
	 * @return Timestamp.
	 * @deprecated use instead method <code>toTimestamp</code> from the class
	 *             <code>ConvertDateExtensions</code><br>
	 *             <br>
	 *             Note: will be removed in the next minor release
	 */
	@Deprecated
	public static Timestamp getTimestamp()
	{
		return getTimestamp(new Date());
	}

	/**
	 * Creates a java.sql.Timestamp(to match the ones in the database) from the given date.
	 *
	 * @param date
	 *            The date
	 *
	 * @return Timestamp.
	 * @deprecated use instead method <code>toTimestamp</code> from the class
	 *             <code>ConvertDateExtensions</code><br>
	 *             <br>
	 *             Note: will be removed in the next minor release
	 */
	@Deprecated
	public static Timestamp getTimestamp(final Date date)
	{
		return ConvertDateExtensions.toTimestamp(date);
	}

	/**
	 * Creates a random birthday-date between 9 and 55 years.
	 *
	 * @return 's the random date.
	 */
	public static Date randomBirthday()
	{
		final Date now = CreateDateExtensions.now();
		// About 55 years.
		final Date past = dateBefore(now, 20000);
		// About 9 years.
		final Date recentlyPast = dateBefore(now, 3000);
		return randomBirthday(recentlyPast, past);
	}

	/**
	 * Creates a random birthday-date between the two given date-objects.
	 *
	 * @param from
	 *            The date from where to start.
	 * @param till
	 *            The date from where to end.
	 * @return 's the random date.
	 */
	public static Date randomBirthday(final Date from, final Date till)
	{
		return randomDatebetween(from, till);
	}

	/**
	 * Creates a random {@link Date}
	 *
	 * @return The random {@link Date}
	 */
	public static Date randomDate()
	{
		return RandomDateFactory.randomDate(SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Creates a random date.
	 *
	 * @param from
	 *            The date from where to begin.
	 * @return The random date.
	 */
	public static Date randomDate(final Date from)
	{
		return randomDate(from, SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Creates a random date.
	 *
	 * @param from
	 *            The date from where to begin.
	 * @param secureRandom
	 *            the secure random for date generation
	 * @return The random date.
	 */
	public static Date randomDate(final Date from, SecureRandom secureRandom)
	{
		return RandomDateFactory.randomDate(from, secureRandom);
	}

	/**
	 * Creates a random Date between the range from start and end.
	 *
	 * @param start
	 *            The Date from where the range starts.
	 * @param end
	 *            The Date from where the range ends.
	 * @return A random Date between the range from start and end.
	 */
	public static Date randomDatebetween(final Date start, final Date end)
	{
		return RandomDateFactory.randomDateBetween(start, end,
			SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Creates a random Date between the range from startDays and endDays from the given Date and
	 * gives it back as a string to the default "dd.MM.yyyy HH:mm:ss" format.
	 *
	 * @param startDate
	 *            The date from where to start as a long.
	 * @param endDate
	 *            The date from where to end as a long.
	 * @return The random date as a String.
	 */
	public static String randomDatebetween(final long startDate, final long endDate)
	{
		return randomDatebetween(startDate, endDate, "dd.MM.yyyy HH:mm:ss");
	}

	/**
	 * Creates a random Date between the range from startDays and endDays from the given Date and
	 * gives it back as a string to the given format.
	 *
	 * @param startDate
	 *            The date from where to start as a long.
	 * @param endDate
	 *            The date from where to end as a long.
	 * @param format
	 *            The format for the date.
	 * @return The random date as a String.
	 */
	public static String randomDatebetween(final long startDate, final long endDate,
		final String format)
	{
		return RandomDateFactory.randomDateBetween(startDate, endDate, format,
			SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Creates a random Date between the range from startDays and endDays from the given Date.
	 *
	 * @param from
	 *            The Date from where to the random Date to start.
	 * @param startDays
	 *            The int that represents the days from where the range starts.
	 * @param endDays
	 *            The int that represents the days from where the range ends.
	 * @return A random Date between the range from startDays and endDays from the given Date.
	 */
	public static Date randomDateBetween(final Date from, final int startDays, final int endDays)
	{
		return dateAfter(from, RandomPrimitivesExtensions.randomIntBetween(startDays, endDays));
	}

	/**
	 * Creates a random {@link LocalDate} object
	 *
	 * @return the random {@link LocalDate} object
	 */
	public static LocalDate randomLocalDate()
	{
		LocalDate randomLocalDate;
		LocalDate now = LocalDate.now();
		if (RandomPrimitivesExtensions.randomBoolean())
		{
			randomLocalDate = now
				.plusDays(RandomPrimitivesExtensions.randomLongBetween(-999999999L, 999999999L));
		}
		else
		{
			randomLocalDate = now
				.minusDays(RandomPrimitivesExtensions.randomLongBetween(-999999999L, 999999999L));
		}
		return randomLocalDate;
	}

	/**
	 * Creates a random {@link LocalDateTime} object
	 *
	 * @return the random {@link LocalDateTime} object
	 */
	public static LocalDateTime randomLocalDateTime()
	{
		return LocalDateTime.of(randomLocalDate(), randomLocalTime());
	}

	/**
	 * Creates a random {@link LocalTime} object
	 *
	 * @return the random {@link LocalTime} object
	 */
	public static LocalTime randomLocalTime()
	{
		LocalTime randomLocalTime;
		LocalTime now = LocalTime.now();
		if (RandomPrimitivesExtensions.randomBoolean())
		{
			randomLocalTime = now.plusHours(RandomPrimitivesExtensions.randomLong(23))
				.plusMinutes(RandomPrimitivesExtensions.randomLong(59))
				.plusSeconds(RandomPrimitivesExtensions.randomLong(59));
		}
		else
		{
			randomLocalTime = now.minusHours(RandomPrimitivesExtensions.randomLong(23))
				.minusMinutes(RandomPrimitivesExtensions.randomLong(59))
				.minusSeconds(RandomPrimitivesExtensions.randomLong(59));
		}
		return randomLocalTime;
	}

	/**
	 * Creates a random {@link LocalDateTime} object
	 *
	 * @return the zone id
	 */
	public static ZoneId randomZoneId()
	{
		List<String> availableZoneIds = ListFactory.newArrayList(ZoneId.getAvailableZoneIds());
		return ZoneId.of(
			availableZoneIds.get(RandomPrimitivesExtensions.randomInt(availableZoneIds.size())));
	}

	private RandomDateExtensions()
	{
	}

}
