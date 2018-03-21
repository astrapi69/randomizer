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
package de.alpharogroup.random.lotto;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

import java.util.Collection;
import java.util.Optional;

import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;

/**
 * The unit test class for the enum class {@link LottoWinCategory}.
 */
public class LottoWinCategoryTest
{

	/**
	 * Test method for creation of object {@link LottoWinCategory}
	 */
	@Test
	public void testObjectCreation()
	{
		WinCategory expected;
		WinCategory actual;
		LottoWinCategory object = LottoWinCategory.EIGHTH_CLASS;
		assertNotNull(object);

		expected = WinCategory.builder().quantityOfWonNumbers(3).withSuperNumber(false).build();
		actual = object.getWinCategory();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link LottoWinCategory#getLottoWinCategory(Collection, boolean)}
	 */
	@Test
	public void testGetLottoWinCategoryCollectionOfIntegerBoolean()
	{
		Optional<LottoWinCategory> expected;
		Optional<LottoWinCategory> actual;
		Collection<Integer> wonLotteryTicket;
		boolean withSuperNumber;

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25, 30);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.FIRST_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25, 30);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.SECOND_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.THIRD_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.FOURTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.FIFTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.SIXTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.SEVENTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.EIGHTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.NINTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.NONE);
		actual = LottoWinCategory.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link LottoWinCategory#getLottoWinCategory(Collection, Collection, boolean)}
	 */
	@Test
	public void testGetLottoWinCategoryCollectionOfIntegerCollectionOfIntegerBoolean()
	{
		Optional<LottoWinCategory> expected;
		Optional<LottoWinCategory> actual;
		Collection<Integer> drawnLotteryNumbers;
		Collection<Integer> wonLotteryTicket;
		boolean withSuperNumber;

		drawnLotteryNumbers = SetExtensions.newTreeSet(6, 12, 15, 18, 25, 30);
		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25, 30);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.FIRST_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25, 30);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.SECOND_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.THIRD_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18, 25);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.FOURTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.FIFTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15, 18);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.SIXTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.SEVENTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12, 15);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.EIGHTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12);
		withSuperNumber = true;
		expected = Optional.of(LottoWinCategory.NINTH_CLASS);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);

		wonLotteryTicket = SetExtensions.newTreeSet(6, 12);
		withSuperNumber = false;
		expected = Optional.of(LottoWinCategory.NONE);
		actual = LottoWinCategory.getLottoWinCategory(drawnLotteryNumbers, wonLotteryTicket,
			withSuperNumber);
		assertEquals(actual, expected);
	}
}
