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
package de.alpharogroup.random.lotto.neo;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Set;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link PlayedLottoTickets}.
 */
public class PlayedLottoTicketsTest
{

	/**
	 * Test method for {@link PlayedLottoTickets#equals(Object)} ,
	 * {@link PlayedLottoTickets#hashCode()} and {@link PlayedLottoTickets#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(PlayedLottoTickets.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for creation of object {@link PlayedLottoTickets}
	 */
	@Test
	public void testObjectCreation()
	{
		PlayedLottoTickets object = PlayedLottoTickets.builder().build();
		assertNotNull(object);

		/** The user. */
		String user = "John Doe";

		/** The lotto tickets. */
		Set<LottoTicket> lottoTickets = SetExtensions.newHashSet(LottoTicket.builder().build());

		object = new PlayedLottoTickets(user, lottoTickets);
		assertNotNull(object);
	}

	/**
	 * Test method for {@link PlayedLottoTickets}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(PlayedLottoTickets.class);
	}

}