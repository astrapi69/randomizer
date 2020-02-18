package de.alpharogroup.random.date;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import de.alpharogroup.date.CalculateDateExtensions;
import de.alpharogroup.date.CreateDateExtensions;
import de.alpharogroup.random.enums.RandomAlgorithm;
import de.alpharogroup.random.number.RandomPrimitivesFactory;

/**
 * The factory class {@link RandomDateFactory} for creating random Date objects
 */
public final class RandomDateFactory
{
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
	public static Date dateAfter(final Date date, final int range, SecureRandom secureRandom)
	{
		return CalculateDateExtensions.addDays(date,
			RandomPrimitivesFactory.randomInt(range, RandomAlgorithm.SECURE_RANDOM, secureRandom));
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
	public static Date dateBefore(final Date date, final int range, SecureRandom secureRandom)
	{
		return CalculateDateExtensions.substractDaysFromDate(date,
			RandomPrimitivesFactory.randomInt(range, RandomAlgorithm.SECURE_RANDOM, secureRandom));
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
		if (RandomPrimitivesFactory.randomBoolean(secureRandom))
		{
			return dateAfter(now, 10000, secureRandom);
		}
		return dateBefore(now, 10000, secureRandom);
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
		long randomLongBetween = RandomPrimitivesFactory.randomLongBetween(startDate, endDate,
			secureRandom);
		Date between = new Date(randomLongBetween);
		return sdf.format(between);
	}

	private RandomDateFactory()
	{
	}
}
