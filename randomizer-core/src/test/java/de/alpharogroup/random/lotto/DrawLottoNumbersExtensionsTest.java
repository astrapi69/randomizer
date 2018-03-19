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
import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.math.MathExtensions;

/**
 * The class {@link DrawLottoNumbersExtensionsTest}.
 */
public class DrawLottoNumbersExtensionsTest {

	/**
	 * Test method for {@link DrawLottoNumbersExtensions#draw(int, int)}.
	 */
	@Test
	public void testDrawIntInt() throws Exception {
		Set<Integer> lottoNumbers;
		int volume;
		int max;

		max = 7;

		// simulate a draw of 7 of 39
		volume = 39;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 7 of 36
		volume = 36;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 7 of 35
		volume = 35;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		max = 6;

		// simulate a draw of 6 of 59
		volume = 59;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 6 of 49
		volume = 49;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 6 of 48
		volume = 48;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 6 of 45
		volume = 45;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 6 of 42
		volume = 42;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 6 of 30
		volume = 30;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		max = 5;

		// simulate a draw of 5 of 90
		volume = 90;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 5 of 75
		volume = 75;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 5 of 69
		volume = 69;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 5 of 49
		volume = 49;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 5 of 42
		volume = 42;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 5 of 40
		volume = 40;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}

		// simulate a draw of 5 of 35
		volume = 35;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(1, volume, lottoNumber, true, true));
		}
	}

	/**
	 * Test method for
	 * {@link DrawLottoNumbersExtensions#drawSuperNumber(Set, int)}.
	 */
	@Test
	public void testDrawSuperNumber() throws Exception {
		Set<Integer> alreadyDrawnNumbers = SetExtensions.newTreeSet(6, 12, 15, 18, 25, 30);
		int superNumber = DrawLottoNumbersExtensions.drawSuperNumber(alreadyDrawnNumbers, 49);
		assertTrue(!alreadyDrawnNumbers.contains(superNumber));
		assertTrue(MathExtensions.isBetween(1, 49, superNumber, true, true));
	}

	/**
	 * Test method for {@link DrawLottoNumbersExtensions#drawGameSeventySeven()}.
	 */
	@Test
	public void testDrawGameSeventySeven()
	{
		int actual;
		actual = DrawLottoNumbersExtensions.drawGameSeventySeven();
		assertTrue(MathExtensions.isBetween(0, 9999999, actual, true, true));
	}

	/**
	 * Test method for {@link DrawLottoNumbersExtensions#draw(int, int, int)}.
	 */
	@Test
	public void testDrawIntIntInt() {
		Set<Integer> lottoNumbers;
		int max;
		int minVolume;
		int maxVolume;

		minVolume = 5;
		max = 7;

		// simulate a draw of 7 numbers in between of 5 and 39
		maxVolume = 39;
		lottoNumbers = DrawLottoNumbersExtensions.draw(max, minVolume, maxVolume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(minVolume, maxVolume, lottoNumber, true, true));
		}
	}

	/**
	 * Test method for
	 * {@link DrawLottoNumbersExtensions#drawDefaultAlgorithm(int, int, int)}.
	 */
	@Test
	public void testDrawDefaultAlgorithm() {

		Set<Integer> lottoNumbers;
		int max;
		int minVolume;
		int maxVolume;

		minVolume = 5;
		max = 7;

		// simulate a draw of 7 numbers in between of 5 and 39
		maxVolume = 39;
		lottoNumbers = DrawLottoNumbersExtensions.drawDefaultAlgorithm(max, minVolume, maxVolume);
		assertTrue(lottoNumbers.size() == max);
		for (Integer lottoNumber : lottoNumbers) {
			assertTrue(MathExtensions.isBetween(minVolume, maxVolume, lottoNumber, true, true));
		}
	}

	/**
	 * Test method for
	 * {@link DrawLottoNumbersExtensions#newRandomDrawnLottoNumbers(int, int)}.
	 */
	@Test
	public void testNewRandomDrawnLottoNumbersIntInt() {
		final DrawnLottoNumbers luckyNumbers = DrawLottoNumbersExtensions
			.newRandomDrawnLottoNumbers(5, 48);
		assertNotNull(luckyNumbers);
	}

	/**
	 * Test method for
	 * {@link DrawLottoNumbersExtensions#newRandomDrawnLottoNumbersDefaultAlgorithm(int, int)}.
	 */
	@Test
	public void testNewRandomDrawnLottoNumbersDefaultAlgorithm() {
		final DrawnLottoNumbers luckyNumbers = DrawLottoNumbersExtensions
			.newRandomDrawnLottoNumbersDefaultAlgorithm(5, 48);
		assertNotNull(luckyNumbers);
	}

	/**
	 * Test method for
	 * {@link DrawLottoNumbersExtensions#newRandomDrawnLottoNumbers()}.
	 */
	@Test
	public void testNewRandomDrawnLottoNumbers() {
		final DrawnLottoNumbers luckyNumbers = DrawLottoNumbersExtensions
			.newRandomDrawnLottoNumbers();
		assertNotNull(luckyNumbers);
	}

}
