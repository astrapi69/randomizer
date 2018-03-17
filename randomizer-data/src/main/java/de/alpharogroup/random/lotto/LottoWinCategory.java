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
import lombok.Getter;

/**
 * The enum {@link LottoWinCategory} represents all lottery winning categories.
 */
public enum LottoWinCategory
{

	/** The first winning class. */
	FIRST_CLASS(WinCategory.builder().quantityOfWonNumbers(6).withSuperNumber(true).build()),
	/** The second winning class. */
	SECOND_CLASS(WinCategory.builder().quantityOfWonNumbers(6).withSuperNumber(false).build()),
	/** The third winning class. */
	THIRD_CLASS(WinCategory.builder().quantityOfWonNumbers(5).withSuperNumber(true).build()),
	/** The fourth winning class. */
	FOURTH_CLASS(WinCategory.builder().quantityOfWonNumbers(5).withSuperNumber(false).build()),
	/** The fifth winning class. */
	FIFTH_CLASS(WinCategory.builder().quantityOfWonNumbers(4).withSuperNumber(true).build()),
	/** The sixth winning class. */
	SIXTH_CLASS(WinCategory.builder().quantityOfWonNumbers(4).withSuperNumber(false).build()),
	/** The seventh winning class. */
	SEVENTH_CLASS(WinCategory.builder().quantityOfWonNumbers(3).withSuperNumber(true).build()),
	/** The eighth winning class. */
	EIGHTH_CLASS(WinCategory.builder().quantityOfWonNumbers(3).withSuperNumber(false).build()),
	/** The ninth winning class. */
	NINTH_CLASS(WinCategory.builder().quantityOfWonNumbers(2).withSuperNumber(true).build()),
	/** The none winning class. */
	NONE(null);

	/**
	 * Gets an {@linkplain Optional} with the {@linkplain LottoWinCategory} from the given
	 * Collection that contains the result of an intersection with the drawn lotto numbers.
	 *
	 * @param wonLotteryTicket
	 *            the won lottery ticket
	 * @param withSuperNumber
	 *            the with super number
	 * @return the lotto win category
	 */
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

	/**
	 * Gets an {@linkplain Optional} with the {@linkplain LottoWinCategory} that is an intersection
	 * of the given drawn lotto numbers and the given played lottery Ticket.
	 *
	 * @param drawnLotteryNumbers
	 *            the drawn lottery numbers
	 * @param playedLotteryTicket
	 *            the played lottery ticket
	 * @param withSuperNumber
	 *            the with super number
	 * @return the lotto win category
	 */
	public static Optional<LottoWinCategory> getLottoWinCategory(
		Collection<Integer> drawnLotteryNumbers, Collection<Integer> playedLotteryTicket,
		boolean withSuperNumber)
	{
		final Collection<Integer> wonNumbers = CollectionExtensions
			.intersection(SetExtensions.newTreeSet(drawnLotteryNumbers), playedLotteryTicket);
		return getLottoWinCategory(wonNumbers, withSuperNumber);
	}

	/** The bean that represents the win category. */
	@Getter
	private final WinCategory winCategory;

	/**
	 * Instantiates a new {@link LottoWinCategory}.
	 *
	 * @param winCategory
	 *            the win category
	 */
	private LottoWinCategory(final WinCategory winCategory)
	{
		this.winCategory = winCategory;
	}

}
