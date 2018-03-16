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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.lotto.neo.LottoBox;
import de.alpharogroup.random.lotto.neo.LottoTicket;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link LottoExtensions} provides utility methods for draw lotto, super numbers and
 * other gambling algorithms.
 */
@UtilityClass
@Slf4j
public final class LottoExtensions
{


	/**
	 * Checks the result if the drawn lotto numbers are equal to the given played numbers. The
	 * result is a {@link EvaluatedLottoNumbers} object that keep the winning numbers.
	 *
	 * @param drawnLottoNumbers
	 *            the lotto lucky numbers
	 * @param lottoPlayedNumbers
	 *            the lotto played numbers
	 * @return the won numbers
	 */
	public static EvaluatedLottoNumbers checkResult(final DrawnLottoNumbers drawnLottoNumbers,
		final LottoPlayedNumbers lottoPlayedNumbers)
	{

		final Set<Integer> drawnLuckyLottoNumbers = drawnLottoNumbers.getLottoNumbers();
		final Map<LottoGameType, List<Set<Integer>>> playedLottoNumbers = lottoPlayedNumbers
			.getPlayedLottoNumbers();
		final Set<LottoGameType> playedLotteryTickets = playedLottoNumbers.keySet();
		final EvaluatedLottoNumbers evaluatedLottoNumbersBean = EvaluatedLottoNumbers.builder()
			.build();
		final Map<LottoGameType, List<Collection<Integer>>> wonLottoNumbersMap = evaluatedLottoNumbersBean
			.getWonLottoNumbers();
		for (final LottoGameType lottoGameType : playedLotteryTickets)
		{
			final List<Set<Integer>> lotteryTicket = playedLottoNumbers.get(lottoGameType);
			final List<Collection<Integer>> currentWonLottoNumbersList = ListExtensions
				.newArrayList(wonLottoNumbersMap.get(lottoGameType));
			wonLottoNumbersMap.put(lottoGameType, currentWonLottoNumbersList);
			for (int i = 0; i < lotteryTicket.size(); i++)
			{
				Set<Integer> currentLottoPlayedBox = lotteryTicket.get(i);
				final Collection<Integer> wonNumbers = CollectionExtensions.intersection(
					SetExtensions.newTreeSet(drawnLuckyLottoNumbers), currentLottoPlayedBox);
				currentWonLottoNumbersList.add(wonNumbers);
			}
		}
		return evaluatedLottoNumbersBean;
	}

	public static void evaluate(DrawnLottoNumbers drawnLottoNumbers, LottoTicket playedLottoTicket)
	{
		Set<LottoBox> lottoBoxes = playedLottoTicket.getLottoBoxes();
		for (LottoBox lottoBox : lottoBoxes)
		{
			boolean withSuperNumber = lottoBox.getSelectedNumbers()
				.contains(drawnLottoNumbers.getSuperNumber());
			Optional<LottoWinCategory> lottoWinCategory = LottoWinCategory.getLottoWinCategory(
				drawnLottoNumbers.getLottoNumbers(), lottoBox.getSelectedNumbers(),
				withSuperNumber);
			lottoBox.setWinCategory(lottoWinCategory.get());
		}
	}

	public static void setWinCategories(final EvaluatedLottoNumbers evaluatedLottoNumbers)
	{
		final Map<LottoGameType, List<Collection<Integer>>> wonLottoNumbersMap = evaluatedLottoNumbers
			.getWonLottoNumbers();
		Set<LottoGameType> lottoGameTypeSet = wonLottoNumbersMap.keySet();
		boolean withSuperNumber = false;
		for (final LottoGameType lottoGameType : lottoGameTypeSet)
		{
			List<Collection<Integer>> currentWonLottoNumbersList = wonLottoNumbersMap
				.get(lottoGameType);
			for (Collection<Integer> wonLotteryTicket : currentWonLottoNumbersList)
			{
				Optional<LottoWinCategory> lottoWinCategory = LottoWinCategory
					.getLottoWinCategory(wonLotteryTicket, withSuperNumber);
				lottoWinCategory.ifPresent(l -> System.out.println(l.name()));
			}
		}
	}

	public static int calculateDraws(LottoTicket lottoTicket,
		@NonNull LottoWinCategory lottoWinCategory)
	{
		final long startTime = System.nanoTime();

		int count = 0;

		DrawnLottoNumbers luckyNumbers = DrawLottoNumbersExtensions.newRandomDrawnLottoNumbers();
		count++;
		boolean breakout = false;
		// int i1 = 3;
		while (!breakout)
		{
			evaluate(luckyNumbers, lottoTicket);
			Set<LottoBox> lottoBoxes = lottoTicket.getLottoBoxes();
			for (LottoBox box : lottoBoxes)
			{
				if (!box.getWinCategory().equals(LottoWinCategory.NONE))
				{
					log.info("current draw " + count + " and win category: "
						+ box.getWinCategory().name());
				}
				breakout = box.getWinCategory().equals(lottoWinCategory);
			}
			luckyNumbers = DrawLottoNumbersExtensions.newRandomDrawnLottoNumbers();
			count++;
		}

		log.info("Elapsed time till you have won something: "
			+ calculateElapsedTimeInSeconds(startTime));
		log.info("you have won after " + count + " drawings");
		log.info("you have won: " + lottoTicket);
		return count;
	}

	/**
	 * Calculate draws for statistics.
	 *
	 * @param lottoPlayedNumbers
	 *            the lotto played numbers
	 * @param winningNumbersCount
	 *            the winning numbers count
	 * @return the int
	 */
	public static int calculateDraws(final LottoPlayedNumbers lottoPlayedNumbers,
		final int winningNumbersCount)
	{
		if (!MathExtensions.isBetween(1, 6, winningNumbersCount))
		{
			log.info("winningNumbersCount have to be between 1 and 5");
			return -1;
		}
		final long startTime = System.nanoTime();
		int count = 0;
		DrawnLottoNumbers luckyNumbers = DrawLottoNumbersExtensions.newRandomDrawnLottoNumbers();
		count++;
		EvaluatedLottoNumbers evaluatedLottoNumbers = null;
		boolean breakout = false;
		// int i1 = 3;
		while (!breakout)
		{
			evaluatedLottoNumbers = LottoExtensions.checkResult(luckyNumbers, lottoPlayedNumbers);
			final Map<LottoGameType, List<Collection<Integer>>> wonLottoNumbers = evaluatedLottoNumbers
				.getWonLottoNumbers();
			if (!wonLottoNumbers.isEmpty())
			{
				final List<Collection<Integer>> collections = wonLottoNumbers
					.get(LottoGameType.SIX_OF_FOURTYNINE_NORMAL);
				for (int i = 0; i < collections.size(); i++)
				{
					final Collection<Integer> s = collections.get(i);
					if (winningNumbersCount < s.size())
					{
						breakout = true;
						break;
					}
				}
			}
			luckyNumbers = DrawLottoNumbersExtensions.newRandomDrawnLottoNumbers();
			count++;
			log.info("This is the " + count + " draw of the lotto queen: " + luckyNumbers);
		}

		log.info("Elapsed time till you have won something: "
			+ calculateElapsedTimeInSeconds(startTime));
		log.info("you have won after " + count + " drawings");
		log.info("you have won: " + evaluatedLottoNumbers);
		return count;
	}


	/**
	 * Calculate elapsed time in seconds from the given start time as long to the current system
	 * time. This is useful for benchmarking
	 *
	 * @param startTime
	 *            the start time
	 * @return The elapsed time in double
	 */
	public static double calculateElapsedTimeInSeconds(final long startTime)
	{
		final double elapsedTime = ((double)(System.nanoTime() - startTime)) / 1000000;
		return elapsedTime;
	}

}
