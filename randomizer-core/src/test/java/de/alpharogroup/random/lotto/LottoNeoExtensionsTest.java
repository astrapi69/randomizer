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
/**
 *
 */
package de.alpharogroup.random.lotto;

import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.map.MapExtensions;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.random.lotto.neo.LottoBox;
import de.alpharogroup.random.lotto.neo.LottoTicket;
import lombok.extern.slf4j.Slf4j;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static de.alpharogroup.random.lotto.LottoExtensions.newRandomDrawnLottoNumbers;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * The unit test class for the class {@link LottoExtensions}.
 */
@Slf4j
public class LottoNeoExtensionsTest
{

	public static LottoTicket newLottoTicket(List<Set<Integer>> lottoSet) {
		Set<LottoBox> lottoBoxes = SetExtensions.newHashSet();
		for(Set<Integer> lottoNumbers : lottoSet) {
			lottoBoxes.add(LottoBox.builder()
				.index(lottoSet.indexOf(lottoNumbers))
				.gameType(LottoGameType.SIX_OF_FOURTYNINE_NORMAL)
				.selectedNumbers(lottoNumbers)
				.build());
		}
		LottoTicket lottoTicket = LottoTicket.builder()
			.lottoBoxes(lottoBoxes)
			.build();
		return lottoTicket;
	}

	@Test(enabled = true)
	public void testEvaluate() {
		DrawnLottoNumbers drawnLottoNumbers = newRandomDrawnLottoNumbers();
		LottoTicket lottoTicket = newLottoTicket(LottoExtensionsTest.newLottoSets());
		LottoExtensions.evaluate(drawnLottoNumbers, lottoTicket);
		log.info(lottoTicket.toString());
	}

	@Test(enabled = true)
	public void testCalculateDraws() {
		LottoTicket lottoTicket = newLottoTicket(LottoExtensionsTest.newLottoSets());
		LottoExtensions.calculateDraws(lottoTicket, LottoWinCategory.FOURTH_CLASS);
	}


}
