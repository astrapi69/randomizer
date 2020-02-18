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

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;

import org.meanbean.test.BeanTester;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.date.CalculateDateExtensions;
import de.alpharogroup.date.DatePatterns;
import de.alpharogroup.date.ParseDateExtensions;
import de.alpharogroup.random.DefaultSecureRandom;


/**
 * The unit test class for the class {@link RandomDateFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomDateFactoryTest
{

	boolean expected;

	/** The date for now. */
	private Date now;

	@BeforeMethod
	protected void setUp()
	{
		this.now = new Date(System.currentTimeMillis());
	}

	/**
	 * Test method for {@link RandomDateFactory#dateAfter(Date, int, SecureRandom)}
	 */
	@Test
	public void testDateAfter()
	{
		Date end = CalculateDateExtensions.addDays(now, 10001);
		Date dateAfter = RandomDateFactory.dateAfter(now, 10000, DefaultSecureRandom.get());

		boolean actual = CalculateDateExtensions.isBetween(now, end, dateAfter);
		assertTrue(actual);

	}

	/**
	 * Test method for {@link RandomDateFactory#dateBefore(Date, int, SecureRandom)}
	 */
	@Test
	public void testDateBefore()
	{
		Date start = CalculateDateExtensions.substractDaysFromDate(now, 1001);
		Date dateBefore = RandomDateFactory.dateBefore(now, 1000, DefaultSecureRandom.get());

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
		Date randomDate = RandomDateFactory.randomDate(DefaultSecureRandom.get());
		assertNotNull(randomDate);

		boolean actual = !randomDate.equals(this.now);
		assertTrue(actual);
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
