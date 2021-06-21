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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import io.github.astrapi69.random.DefaultSecureRandom;
import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.date.CalculateDateExtensions;
import de.alpharogroup.date.DatePatterns;
import de.alpharogroup.date.ParseDateExtensions;

import static org.testng.AssertJUnit.*;


/**
 * The unit test class for the class {@link RandomDateFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomDateFactoryTest
{

	boolean actual;
	boolean expected;

	/** The date for now. */
	private Date now;

	@BeforeMethod
	protected void setUp()
	{
		this.now = new Date(System.currentTimeMillis());
	}

	/**
	 * Test method for {@link RandomDateFactory#randomBirthday()}
	 */
	@Test
	public void testCreateRandomBirthday()
	{
		// About 55 years.
		final Date past = CalculateDateExtensions.substractDaysFromDate(this.now, 21001);
		final Date recentlyPast = CalculateDateExtensions.substractDaysFromDate(this.now, 1);
		for (int i = 0; i < 10; i++)
		{
			final Date randomBirthday = RandomDateFactory.randomBirthday();
			actual = CalculateDateExtensions.isBetween(past, recentlyPast, randomBirthday);
			assertTrue(actual);
		}
	}

	/**
	 * Test method for {@link RandomDateFactory#randomBirthday(java.util.Date, java.util.Date)} .
	 */
	@Test
	public void testCreateRandomBirthdayDateDate()
	{
		final Date from = CalculateDateExtensions.substractDaysFromDate(this.now, 20000);
		final Date till = CalculateDateExtensions.substractDaysFromDate(this.now, 3000);
		for (int i = 0; i < 10; i++)
		{
			final Date randomBirthday = RandomDateFactory.randomBirthday(from, till);
			actual = CalculateDateExtensions.isBetween(from, till, randomBirthday);
			assertTrue("", actual);
		}
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateBetween(java.util.Date, int, int)}
	 */
	@Test
	public void testCreateRandomDateBetween()
	{
		final Date from = this.now;
		final int startDays = 0;
		final int endDays = 30;
		final Date till = CalculateDateExtensions.addDays(this.now, 30);
		final Date randomDate = RandomDateFactory.randomDateBetween(from, startDays, endDays);
		actual = CalculateDateExtensions.isBetween(this.now, till, randomDate);
		assertTrue("", actual);
	}

	/**
	 * Test method for
	 * {@link RandomDateFactory#randomDatebetween(java.util.Date, java.util.Date)} .
	 */
	@Test
	public void testCreateRandomDatebetweenDateDate()
	{
		final Date end = CalculateDateExtensions.addDays(this.now, 30);
		final Date start = this.now;
		final Date randomDate = RandomDateFactory.randomDatebetween(start, end);
		actual = CalculateDateExtensions.isBetween(start, end, randomDate);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDatebetween(long, long)}.
	 *
	 * @throws ParseException
	 *             occurs when their are problems with parsing the String to Date.
	 */
	@Test
	public void testCreateRandomDatebetweenLongLong() throws ParseException
	{
		final Date till = CalculateDateExtensions.addDays(this.now, 30);
		final long endDate = till.getTime();
		final long startDate = this.now.getTime();
		final String randomDate = RandomDateFactory.randomDatebetween(startDate, endDate);
		final Date compare = ParseDateExtensions.parseToDate(randomDate,
				DatePatterns.DOT_DD_MM_YYYY_HH_MM_SS);
		actual = CalculateDateExtensions.isBetween(this.now, till, compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDatebetween(long, long, java.lang.String)}
	 * .
	 *
	 * @throws ParseException
	 *             occurs when their are problems with parsing the String to Date.
	 */
	@Test
	public void testCreateRandomDatebetweenLongLongString() throws ParseException
	{
		final Date from = CalculateDateExtensions.substractDaysFromDate(this.now, 1);
		final Date till = CalculateDateExtensions.addDays(this.now, 30);
		final long endDate = till.getTime();
		final long startDate = from.getTime();
		final String format = DatePatterns.DOT_DD_MM_YYYY_HH_MM_SS;
		final String randomDate = RandomDateFactory.randomDatebetween(startDate, endDate,
				format);
		final Date compare = ParseDateExtensions.parseToDate(randomDate, format);
		actual = CalculateDateExtensions.isBetween(from, till, compare);
		assertTrue("", actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomLocalDate()}
	 */
	@Test
	public void testRandomLocalDate()
	{
		for (int i = 0; i < 7; i++)
		{
			LocalDate randomObject = RandomDateFactory.randomLocalDate();
			assertNotNull(randomObject);
		}
	}

	/**
	 * Test method for {@link RandomDateFactory#randomLocalDateTime()}
	 */
	@Test
	public void testRandomLocalDateTime()
	{
		LocalDateTime randomObject = RandomDateFactory.randomLocalDateTime();
		assertNotNull(randomObject);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomLocalTime()}
	 */
	@Test
	public void testRandomLocalTime()
	{
		for (int i = 0; i < 7; i++)
		{
			LocalTime randomObject = RandomDateFactory.randomLocalTime();
			assertNotNull(randomObject);
		}
	}

	/**
	 * Test method for {@link RandomDateFactory#randomZoneId()}
	 */
	@Test
	public void testRandomZoneId()
	{
		ZoneId randomObject = RandomDateFactory.randomZoneId();
		assertNotNull(randomObject);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateBefore(Date)}.
	 */
	@Test
	public void testDateBeforeDate()
	{
		Date start = CalculateDateExtensions.substractDaysFromDate(now, 10001);
		Date dateBefore = RandomDateFactory.randomDateBefore(now);

		expected = true;
		actual = CalculateDateExtensions.isBetween(start, now, dateBefore);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateBefore(Date, int)}.
	 */
	@Test
	public void testDateBeforeDateInt()
	{
		Date start = CalculateDateExtensions.substractDaysFromDate(now, 1001);
		Date dateBefore = RandomDateFactory.randomDateBefore(now, 1000);

		expected = true;
		actual = CalculateDateExtensions.isBetween(start, now, dateBefore);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateAfter(Date)}.
	 */
	@Test
	public void testDateAfterDate()
	{
		boolean actual;
		boolean expected;
		Date end = CalculateDateExtensions.addDays(now, 10001);
		Date dateAfter = RandomDateFactory.randomDateAfter(now);

		expected = true;
		actual = CalculateDateExtensions.isBetween(now, end, dateAfter);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateAfter(Date, int)}.
	 */
	@Test
	public void testDateAfterDateInt()
	{
		boolean actual;
		boolean expected;
		Date end = CalculateDateExtensions.addDays(now, 1001);
		Date dateAfter = RandomDateFactory.randomDateAfter(now, 1000);

		expected = true;
		actual = CalculateDateExtensions.isBetween(now, end, dateAfter);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDate(java.util.Date)}
	 */
	@Test
	public void testRandomDateDate()
	{
		boolean actual;
		final Date from = this.now;
		final Date randomDate = RandomDateFactory.randomDate(from);
		assertNotNull(randomDate);

		actual = !randomDate.equals(this.now);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDate()}
	 */
	@Test
	public void testRandomDate()
	{
		boolean actual;
		for (int i = 0; i < 7; i++)
		{
			final Date randomDate = RandomDateFactory.randomDate();
			assertNotNull(randomDate);

			actual = !randomDate.equals(this.now);
			assertTrue(actual);
		}
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateAfter(Date, int, SecureRandom)}
	 */
	@Test
	public void testDateAfter()
	{
		Date end = CalculateDateExtensions.addDays(now, 10001);
		Date dateAfter = RandomDateFactory.randomDateAfter(now, 10000, DefaultSecureRandom.get());

		boolean actual = CalculateDateExtensions.isBetween(now, end, dateAfter);
		assertTrue(actual);

	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateBefore(Date, int, SecureRandom)}
	 */
	@Test
	public void testDateBefore()
	{
		Date start = CalculateDateExtensions.substractDaysFromDate(now, 1001);
		Date dateBefore = RandomDateFactory.randomDateBefore(now, 1000, DefaultSecureRandom.get());

		boolean actual = CalculateDateExtensions.isBetween(start, now, dateBefore);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateBetween(Date, Date, SecureRandom)}
	 */
	@Test
	public void testRandomDateBetweenDateDateSecureRandom()
	{
		final Date end = CalculateDateExtensions.addDays(this.now, 30);
		final Date start = this.now;
		final Date randomDate = RandomDateFactory.randomDateBetween(start, end,
			DefaultSecureRandom.get());
		boolean actual = CalculateDateExtensions.isBetween(start, end, randomDate);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDateBetween(long, long, String, SecureRandom)}
	 *
	 * @throws ParseException
	 *             occurs when their are problems with parsing the String to Date.
	 */
	@Test
	public void testRandomDateBetweenLongLongStringSecureRandom() throws ParseException
	{
		final Date from = CalculateDateExtensions.substractDaysFromDate(this.now, 1);
		final Date till = CalculateDateExtensions.addDays(this.now, 30);
		final long endDate = till.getTime();
		final long startDate = from.getTime();
		final String format = DatePatterns.DOT_DD_MM_YYYY_HH_MM_SS;
		final String randomDate = RandomDateFactory.randomDateBetween(startDate, endDate, format,
			DefaultSecureRandom.get());
		final Date compare = ParseDateExtensions.parseToDate(randomDate, format);
		boolean actual = CalculateDateExtensions.isBetween(from, till, compare);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDate(Date, SecureRandom)}
	 */
	@Test
	public void testRandomDateDateSecureRandom()
	{
		Date randomDate = RandomDateFactory.randomDate(now, DefaultSecureRandom.get());
		assertNotNull(randomDate);

		boolean actual = !randomDate.equals(this.now);
		assertTrue(actual);
	}

	/**
	 * Test method for {@link RandomDateFactory#randomDate(SecureRandom)}
	 */
	@Test
	public void testRandomDateSecureRandom()
	{
		for (int i = 0; i < 7; i++)
		{
			Date randomDate = RandomDateFactory.randomDate(DefaultSecureRandom.get());
			assertNotNull(randomDate);

			boolean actual = !randomDate.equals(this.now);
			assertTrue(actual);
		}
	}

	/**
	 * Test method for {@link RandomDateFactory} with {@link BeanTester}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(RandomDateFactory.class);
	}
}
