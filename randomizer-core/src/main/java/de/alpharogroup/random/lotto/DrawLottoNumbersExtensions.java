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
