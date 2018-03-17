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

import java.util.Collection;
import java.util.Optional;

import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.random.lotto.neo.LottoBox;
import lombok.Getter;

public enum LottoWinCategory
{

	FIRST_CLASS(WinCategory.builder().quantityOfWonNumbers(6).withSuperNumber(true)
		.build()), SECOND_CLASS(WinCategory.builder().quantityOfWonNumbers(6).withSuperNumber(false)
			.build()), THIRD_CLASS(WinCategory.builder().quantityOfWonNumbers(5)
				.withSuperNumber(true).build()), FOURTH_CLASS(WinCategory.builder()
					.quantityOfWonNumbers(5).withSuperNumber(false).build()), FIFTH_CLASS(
						WinCategory.builder().quantityOfWonNumbers(4).withSuperNumber(true)
							.build()), SIXTH_CLASS(WinCategory.builder().quantityOfWonNumbers(4)
								.withSuperNumber(false).build()), SEVENTH_CLASS(
									WinCategory.builder().quantityOfWonNumbers(3)
										.withSuperNumber(true).build()), EIGHTH_CLASS(
											WinCategory.builder().quantityOfWonNumbers(3)
												.withSuperNumber(false).build()), NINTH_CLASS(
													WinCategory.builder().quantityOfWonNumbers(2)
														.withSuperNumber(true).build()), NONE(null);
	public static Optional<LottoWinCategory> getLottoWinCategory(
		Collection<Integer> wonLotteryTicket, boolean withSuperNumber)
	{
		int size = wonLotteryTicket.size();
		Optional<LottoWinCategory> optional = Optional.of(NONE);
		if (size == 6)
		{
			if (withSuperNumber)
			{
				optional = Optional.of(FIRST_CLASS);
			}
			if (!withSuperNumber)
			{
				optional = Optional.of(SECOND_CLASS);
			}
		}
		if (size == 5)
		{
			if (withSuperNumber)
			{
				optional = Optional.of(THIRD_CLASS);
			}
			if (!withSuperNumber)
			{
				optional = Optional.of(FOURTH_CLASS);
			}
		}
		if (size == 4)
		{
			if (withSuperNumber)
			{
				optional = Optional.of(FIFTH_CLASS);
			}
			if (!withSuperNumber)
			{
				optional = Optional.of(SIXTH_CLASS);
			}
		}
		if (size == 3)
		{
			if (withSuperNumber)
			{
				optional = Optional.of(SEVENTH_CLASS);
			}
			if (!withSuperNumber)
			{
				optional = Optional.of(EIGHTH_CLASS);
			}
		}
		if (size == 2 && withSuperNumber)
		{
			optional = Optional.of(NINTH_CLASS);
		}
		return optional;
	}

	public static Optional<LottoWinCategory> getLottoWinCategory(
		Collection<Integer> luckyLotteryNumbers, Collection<Integer> playedLotteryTicket,
		boolean withSuperNumber)
	{
		final Collection<Integer> wonNumbers = CollectionExtensions
			.intersection(SetExtensions.newTreeSet(luckyLotteryNumbers), playedLotteryTicket);
		return getLottoWinCategory(wonNumbers, withSuperNumber);
	}

	public static Optional<LottoWinCategory> getLottoWinCategory(LottoBox playedLottoBox,
		boolean withSuperNumber)
	{
		return getLottoWinCategory(playedLottoBox.getSelectedNumbers(), withSuperNumber);
	}

	public static Optional<LottoWinCategory> getLottoWinCategory(LottoBox luckyLottoBox,
		LottoBox playedLottoBox, boolean withSuperNumber)
	{
		final Collection<Integer> wonNumbers = CollectionExtensions
			.intersection(luckyLottoBox.getSelectedNumbers(), playedLottoBox.getSelectedNumbers());
		return getLottoWinCategory(wonNumbers, withSuperNumber);
	}

	@Getter
	private final WinCategory winCategory;

	LottoWinCategory(final WinCategory winCategory)
	{
		this.winCategory = winCategory;
	}

}
