/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 *
 */
package de.alpharogroup.random.lotto;

import java.util.List;
import java.util.Set;

import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.random.lotto.neo.LottoBox;
import de.alpharogroup.random.lotto.neo.LottoTicket;
import lombok.extern.slf4j.Slf4j;

/**
 * The unit test class for the class {@link LottoExtensions}.
 */
@Slf4j
public class LottoNeoExtensionsTest
{

	public static LottoTicket newLottoTicket(List<Set<Integer>> lottoSet)
	{
		Set<LottoBox> lottoBoxes = SetExtensions.newHashSet();
		for (Set<Integer> lottoNumbers : lottoSet)
		{
			lottoBoxes.add(LottoBox.builder().index(lottoSet.indexOf(lottoNumbers))
				.gameType(LottoGameType.SIX_OF_FOURTYNINE_NORMAL).selectedNumbers(lottoNumbers)
				.build());
		}
		LottoTicket lottoTicket = LottoTicket.builder().lottoBoxes(lottoBoxes).build();
		return lottoTicket;
	}

	@Test(enabled = false)
	public void testCalculateDrawsInFifthClass()
	{
		LottoTicket lottoTicket = newLottoTicket(LottoExtensionsTest.newLottoSets());
		LottoExtensions.calculateDraws(lottoTicket, LottoWinCategory.FIFTH_CLASS);
	}

	@Test(enabled = false)
	public void testCalculateDrawsInFourthClass()
	{
		LottoTicket lottoTicket = newLottoTicket(LottoExtensionsTest.newLottoSets());
		LottoExtensions.calculateDraws(lottoTicket, LottoWinCategory.FOURTH_CLASS);
	}

	@Test(enabled = true)
	public void testCalculateDrawsInSixthClass()
	{
		LottoTicket lottoTicket = newLottoTicket(LottoExtensionsTest.newLottoSets());
		LottoExtensions.calculateDraws(lottoTicket, LottoWinCategory.SIXTH_CLASS);
	}

	@Test(enabled = true)
	public void testEvaluate()
	{
		DrawnLottoNumbers drawnLottoNumbers = DrawLottoNumbersExtensions
			.newRandomDrawnLottoNumbers();
		LottoTicket lottoTicket = newLottoTicket(LottoExtensionsTest.newLottoSets());
		LottoExtensions.evaluate(drawnLottoNumbers, lottoTicket);

	}

//	public static void logWinning(DrawnLottoNumbers drawnLottoNumbers, LottoTicket playedLottoTicket)
//	{
//		Set<LottoBox> lottoBoxes = playedLottoTicket.getLottoBoxes();
//		for (LottoBox lottoBox : lottoBoxes)
//		{
//			log.info(lottoBox.getWinCategory());
//		}
//
//	}
//
//	@Test(enabled = false)
//	public void testWholeLotterySzenario()
//	{
//		// 1. you want to provide a lottery with draw 6 numbers of a pool of a number range, lets
//		// say 1 till 49.
//		// 2. you have to anounce the draw date and make marketing for it.
//		log.info("bla bla get lottery ticket and have fun bla bla");
//		// 3. bring the lottery tickets under the folk
//		LottoTicket lottoTicket = newLottoTicket(LottoExtensionsTest.newLottoSets());
//		// 4. make the draw
//		final DrawnLottoNumbers luckyNumbers = DrawLottoNumbersExtensions
//			.newRandomDrawnLottoNumbers();
//		// 5. announce the drawn numbers to the public
//		log.info(luckyNumbers);
//		// 6. evaluate who have won
//		LottoExtensions.evaluate(luckyNumbers, lottoTicket);
//		// 7. payout
//
//	}


}
