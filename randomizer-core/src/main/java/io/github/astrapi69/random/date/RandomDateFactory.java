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

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import de.alpharogroup.date.CalculateDateExtensions;
import de.alpharogroup.date.CreateDateExtensions;
import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.SecureRandomFactory;
import io.github.astrapi69.random.enums.RandomAlgorithm;
import io.github.astrapi69.random.number.RandomBooleanFactory;
import io.github.astrapi69.random.number.RandomIntFactory;
import io.github.astrapi69.random.number.RandomLongFactory;

/**
 * The factory class {@link RandomDateFactory} for creating random Date objects
 */
public final class RandomDateFactory
{

	/**
	 * Creates a random birthday-date between 9 and 55 years.
	 *
	 * @return 's the random date.
	 */
	public static Date randomBirthday()
	{
		final Date now = CreateDateExtensions.now();
		// About 55 years.
		final Date past = randomDateBefore(now, 20000);
		// About 9 years.
		final Date recentlyPast = randomDateBefore(now, 3000);
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
	 * Creates a random Date that is after from the given Date.
	 *
	 * @param date
	 *            The Date from where to compute the future date.
	 *
	 * @return The random Date in the future.
	 */
	public static Date randomDateAfter(final Date date)
	{
		return randomDateAfter(date, RandomIntFactory.randomInt(10000));
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
	public static Date randomDateAfter(final Date date, final int range)
	{
		return randomDateAfter(date, range, DefaultSecureRandom.get());
	}

	/**
	 * Creates a random Date that is after from the given Date.
	 *
	 * @param date
	 *            The Date from where to compute the future date.
	 * @param range
	 *            The range.
	 * @param secureRandom
	 *            the secure random for Date generation
	 *
	 * @return The random Date in the future.
	 */
	public static Date randomDateAfter(final Date date, final int range, SecureRandom secureRandom)
	{
		return CalculateDateExtensions.addDays(date,
				RandomIntFactory.randomInt(range, RandomAlgorithm.SECURE_RANDOM, secureRandom));
	}

	/**
	 * Creates a random date that is before from the given date.
	 *
	 * @param date
	 *            The date from where to compute the Past date.
	 *
	 * @return The random Date in the past.
	 */
	public static Date randomDateBefore(final Date date)
	{
		return randomDateBefore(date, 10000);
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
	public static Date randomDateBefore(final Date date, final int range)
	{
		return RandomDateFactory.randomDateBefore(date, range, SecureRandomFactory.newSecureRandom());
	}

	/**
	 * Creates a random date that is before from the given date.
	 *
	 * @param date
	 *            The date from where to compute the past date.
	 * @param range
	 *            The range.
	 * @param secureRandom
	 *            the secure random for Date generation
	 *
	 * @return The random Date in the past.
	 */
	public static Date randomDateBefore(final Date date, final int range, SecureRandom secureRandom)
	{
		return CalculateDateExtensions.substractDaysFromDate(date,
				RandomIntFactory.randomInt(range, RandomAlgorithm.SECURE_RANDOM, secureRandom));
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
		return RandomDateFactory.randomDate(from, SecureRandomFactory.newSecureRandom());
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
		Objects.requireNonNull(secureRandom);
		final double randDouble = -secureRandom.nextDouble() * from.getTime();
		final double randomDouble = from.getTime() - secureRandom.nextDouble();
		final double result = (randDouble / 99999) * (randomDouble / 99999);
		return new Date((long)result);
	}

	/**
	 * Creates a random {@link Date}
	 * 
	 * @param secureRandom
	 *            the secure random for {@link Date} generation
	 *
	 * @return The random {@link Date}
	 */
	public static Date randomDate(SecureRandom secureRandom)
	{
		final Date now = CreateDateExtensions.now();
		if (RandomBooleanFactory.randomBoolean(secureRandom))
		{
			return randomDateAfter(now, 10000, secureRandom);
		}
		return randomDateBefore(now, 10000, secureRandom);
	}

	/**
	 * Creates a random Date between the range from start and end.
	 *
	 * @param start
	 *            The Date from where the range starts.
	 * @param end
	 *            The Date from where the range ends.
	 * @param secureRandom
	 *            the secure random for date generation
	 * @return A random Date between the range from start and end.
	 */
	public static Date randomDateBetween(final Date start, final Date end,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(secureRandom);
		final long randomLong = (long)(start.getTime()
			+ (secureRandom.nextDouble() * (end.getTime() - start.getTime())));
		return new Date(randomLong);
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
	 * @param secureRandom
	 *            the secure random for String generation
	 * @return The random date as a String.
	 */
	public static String randomDateBetween(final long startDate, final long endDate,
		final String format, SecureRandom secureRandom)
	{
		Objects.requireNonNull(secureRandom);
		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		long randomLongBetween = RandomLongFactory.randomLongBetween(startDate, endDate,
			secureRandom);
		Date between = new Date(randomLongBetween);
		return sdf.format(between);
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
		return RandomDateFactory.randomDateAfter(from, RandomIntFactory.randomIntBetween(startDays, endDays));
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
		if (RandomBooleanFactory.randomBoolean())
		{
			randomLocalDate = now
					.plusDays(RandomLongFactory.randomLongBetween(-999999999L, 999999999L));
		}
		else
		{
			randomLocalDate = now
					.minusDays(RandomLongFactory.randomLongBetween(-999999999L, 999999999L));
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
		if (RandomBooleanFactory.randomBoolean())
		{
			randomLocalTime = now.plusHours(RandomLongFactory.randomLong(23))
					.plusMinutes(RandomLongFactory.randomLong(59))
					.plusSeconds(RandomLongFactory.randomLong(59));
		}
		else
		{
			randomLocalTime = now.minusHours(RandomLongFactory.randomLong(23))
					.minusMinutes(RandomLongFactory.randomLong(59))
					.minusSeconds(RandomLongFactory.randomLong(59));
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
		List<String> availableZoneIds = new ArrayList(ZoneId.getAvailableZoneIds());
		return ZoneId.of(
				availableZoneIds.get(RandomIntFactory.randomInt(availableZoneIds.size())));
	}

	private RandomDateFactory()
	{
	}
}
