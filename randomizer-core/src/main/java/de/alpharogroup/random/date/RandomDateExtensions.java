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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.date.CalculateDateExtensions;
import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.random.SecureRandomFactory;
import lombok.experimental.UtilityClass;

/**
 * The class {@link RandomDateExtensions} is a utility class for creating random random dates.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
@UtilityClass
public class RandomDateExtensions
{

	/** The secure random. */
	private static SecureRandom secureRandom;

	static
	{
		secureRandom = SecureRandomFactory.newSecureRandom();
	}

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
		return dateAfter(date, RandomExtensions.randomInt(10000));
	}
	
	/**
	 * Creates a random {@link LocalDateTime} object
	 *
	 * @return the zone id
	 */
	public static ZoneId randomZoneId() {
		List<String> availableZoneIds = ListFactory.newArrayList(ZoneId.getAvailableZoneIds());
		return ZoneId.of(availableZoneIds.get(RandomExtensions.randomInt(availableZoneIds.size())));		
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
		if (RandomExtensions.randomBoolean())
		{
			randomLocalTime = now.plusHours(RandomExtensions.randomLong(23))
				.plusMinutes(RandomExtensions.randomLong(59))
				.plusSeconds(RandomExtensions.randomLong(59));
		}
		else
		{
			randomLocalTime = now.minusHours(RandomExtensions.randomLong(23))
				.minusMinutes(RandomExtensions.randomLong(59))
				.minusSeconds(RandomExtensions.randomLong(59));
		}
		return randomLocalTime;
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
		if (RandomExtensions.randomBoolean())
		{
			randomLocalDate = now.plusDays(RandomExtensions.randomLong());
		}
		else
		{
			randomLocalDate = now.minusDays(RandomExtensions.randomLong());
		}
		return randomLocalDate;
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
		return CalculateDateExtensions.addDays(date, RandomExtensions.randomInt(range));
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
		return CalculateDateExtensions.substractDaysFromDate(date, range);
	}

	/**
	 * Creates a java.sql.Timestamp from now.
	 *
	 * @return Timestamp.
	 */
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
	 */
	public static Timestamp getTimestamp(final Date date)
	{
		final Calendar gregCal = new GregorianCalendar();
		gregCal.setTime(date);
		gregCal.set(Calendar.HOUR_OF_DAY, 0);
		gregCal.set(Calendar.MINUTE, 0);
		gregCal.set(Calendar.SECOND, 0);
		gregCal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(gregCal.getTime().getTime());
	}

	/**
	 * Creates a random birthday-date between 9 and 55 years.
	 *
	 * @return 's the random date.
	 */
	public static Date randomBirthday()
	{
		final Date now = new Date(System.currentTimeMillis());
		// About 55 years.
		final Date past = dateBefore(now, 20000);
		// About 9 years.
		final Date recentlyPast = dateBefore(now, 3000);
		final Date randomBirthday = randomBirthday(recentlyPast, past);
		return randomBirthday;
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
		final Date randomBirthday = randomDatebetween(from, till);
		return randomBirthday;
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
		final double randDouble = -secureRandom.nextDouble() * from.getTime();
		final double randomDouble = from.getTime() - secureRandom.nextDouble();
		final double result = (randDouble / 99999) * (randomDouble / 99999);
		return new Date((long)result);
	}

	/**
	 * Creates a random {@link Date}
	 *
	 * @return The random {@link Date}
	 */
	public static Date randomDate()
	{
		final Date now = new Date(System.currentTimeMillis());
		if (RandomExtensions.randomBoolean())
		{
			return dateAfter(now, RandomExtensions.randomInt(10000));
		}
		return dateBefore(now, RandomExtensions.randomInt(10000));
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
		final long randomLong = (long)(start.getTime()
			+ (secureRandom.nextDouble() * (end.getTime() - start.getTime())));
		return new Date(randomLong);
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
		final SimpleDateFormat sdf = new SimpleDateFormat(format);
		long randomLongBetween = RandomExtensions.randomLongBetween(startDate, endDate);
		Date between = new Date(randomLongBetween);
		return sdf.format(between);
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
		return dateAfter(from, RandomExtensions.randomIntBetween(startDays, endDays));
	}

}
