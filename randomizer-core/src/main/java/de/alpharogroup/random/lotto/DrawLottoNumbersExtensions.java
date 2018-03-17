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

import java.security.SecureRandom;
import java.util.Set;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.random.SecureRandomBean;
import lombok.experimental.UtilityClass;

/**
 * The class {@link DrawLottoNumbersExtensions}.
 */
@UtilityClass
public final class DrawLottoNumbersExtensions
{

	/**
	 * Draws a super number that is not in the given already drawn numbers {@link Set}.
	 *
	 * @param alreadyDrawnNumbers
	 *            the already drawn numbers
	 * @param volume
	 *            the volume of the numbers starts from 1 till volume
	 * @return the drawn super number
	 */
	public static int drawSuperNumber(Set<Integer> alreadyDrawnNumbers, int volume)
	{
		final SecureRandom sr = SecureRandomBean.builder()
			.algorithm(SecureRandomBean.DEFAULT_ALGORITHM).buildQuietly();
		int superNumber = -1;
		boolean breakout = false;
		while (!breakout)
		{
			superNumber = 1 + Math.abs(sr.nextInt()) % volume;
			if (!alreadyDrawnNumbers.contains(superNumber))
			{
				breakout = true;
			}
		}
		return superNumber;
	}

	/**
	 * Draw.
	 *
	 * @param max
	 *            the max number to draw
	 * @param volume
	 *            the volume of the numbers starts from 1 till volume
	 * @return the sets the
	 */
	public static Set<Integer> draw(int max, int volume)
	{
		Set<Integer> numbers = SetExtensions.newTreeSet();
		final SecureRandom sr = SecureRandomBean.builder()
			.algorithm(SecureRandomBean.DEFAULT_ALGORITHM).buildQuietly();

		int cnt = 0;

		while (cnt < max)
		{
			final int num = 1 + Math.abs(sr.nextInt()) % volume;

			if (!numbers.contains(num))
			{
				numbers.add(num);
				++cnt;
			}
		}
		return numbers;
	}

	/**
	 * Factory method for create a new {@link DrawnLottoNumbers} object with all drawn numbers.
	 *
	 * @param max
	 *            the max number to draw
	 * @param volume
	 *            the volume of the numbers starts from 1 till volume
	 * @return the new {@link DrawnLottoNumbers}
	 */
	public static DrawnLottoNumbers newRandomDrawnLottoNumbers(int max, int volume)
	{
		final DrawnLottoNumbers drawnLottoNumbers = DrawnLottoNumbers.builder()
			.id(RandomExtensions.randomInt(Integer.MAX_VALUE))
			.lottoNumbers(SetExtensions.newTreeSet()).build();
		final SecureRandom sr = SecureRandomBean.builder()
			.algorithm(SecureRandomBean.DEFAULT_ALGORITHM).buildQuietly();
		int cnt = 0;

		while (cnt < max)
		{
			final int num = 1 + Math.abs(sr.nextInt()) % volume;

			if (!drawnLottoNumbers.getLottoNumbers().contains(num))
			{
				if (cnt == (max - 1))
				{
					drawnLottoNumbers.setSuperNumber(num);
				}
				else
				{
					drawnLottoNumbers.getLottoNumbers().add(num);
				}
				++cnt;
			}
		}
		drawnLottoNumbers.setSuperSixNumber(RandomExtensions.randomIntBetween(1, 10));
		return drawnLottoNumbers;
	}

	/**
	 * Factory method for create a new {@link DrawnLottoNumbers} object with all drawn numbers.
	 *
	 * @param max
	 *            the max number to draw
	 * @param volume
	 *            the volume of the numbers starts from 1 till volume
	 * @return the new {@link DrawnLottoNumbers}
	 */
	public static DrawnLottoNumbers newRandomDrawnLottoNumbersDefaultAlgorithm(int max, int volume)
	{
		Set<Integer> lottoNumbers = DrawLottoNumbersExtensions.draw(max, volume);
		int id = RandomExtensions.randomInt(Integer.MAX_VALUE);
		int superNumber = DrawLottoNumbersExtensions.drawSuperNumber(lottoNumbers, volume);
		int superSixNumber = RandomExtensions.randomIntBetween(1, 10);

		final DrawnLottoNumbers drawnLottoNumbers = DrawnLottoNumbers.builder().id(id)
			.lottoNumbers(lottoNumbers).superNumber(superNumber).superSixNumber(superSixNumber)
			.build();

		return drawnLottoNumbers;
	}

	/**
	 * Factory method for create a new {@link DrawnLottoNumbers} object with all drawn numbers
	 *
	 * @return the new {@link DrawnLottoNumbers}
	 */
	public static DrawnLottoNumbers newRandomDrawnLottoNumbers()
	{
		return newRandomDrawnLottoNumbers(7, 49);
	}

}
