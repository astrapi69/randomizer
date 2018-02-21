package de.alpharogroup.random.lotto;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.random.SecureRandomBean;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link LottoExtensions}.
 */
@UtilityClass
@Slf4j
public final class LottoExtensions
{

	private static final String sixOffourtynineGameType = "6 of 49";

	/**
	 * Factory method for create a new {@link LottoLuckyNumbers} object with all drawn numbers
	 *
	 * @return the new {@link LottoLuckyNumbers}
	 */
	public static LottoLuckyNumbers newLottoLuckyNumbers()
	{
		final LottoLuckyNumbers lottoLuckyNumbers = LottoLuckyNumbers.builder()
			.id(RandomExtensions.randomInt(Integer.MAX_VALUE))
			.lottoNumbers(SetExtensions.newTreeSet()).build();
		int cnt = 0;
		final SecureRandom sr = SecureRandomBean.builder()
			.algorithm(SecureRandomBean.DEFAULT_ALGORITHM).buildQuietly();

		while (cnt < 7)
		{
			final int num = 1 + Math.abs(sr.nextInt()) % 49;

			if (!lottoLuckyNumbers.getLottoNumbers().contains(num))
			{
				if (cnt == 6)
				{
					lottoLuckyNumbers.setSuperNumber(num);
				}
				else
				{
					lottoLuckyNumbers.getLottoNumbers().add(num);
				}
				++cnt;
			}
		}
		lottoLuckyNumbers.setSuperSixNumber(RandomExtensions.randomIntBetween(1, 10));
		lottoLuckyNumbers.setGameSeventySeven(RandomExtensions.randomIntBetween(0, 9999999));
		return lottoLuckyNumbers;
	}

	/**
	 * Checks the result if the drawn lotto numbers are equal to the given played numbers. The
	 * result is a {@link WonNumbers} object that keep the winning numbers.
	 *
	 * @param lottoLuckyNumbers
	 *            the lotto lucky numbers
	 * @param lottoPlayedNumbers
	 *            the lotto played numbers
	 * @return the won numbers
	 */
	public static WonNumbers checkResult(final LottoLuckyNumbers lottoLuckyNumbers,
		final LottoPlayedNumbers lottoPlayedNumbers)
	{

		final Set<Integer> lottoNumbers = lottoLuckyNumbers.getLottoNumbers();
		final Map<String, List<Set<Integer>>> playedLottoNumbers = lottoPlayedNumbers
			.getPlayedLottoNumbers();
		final Set<String> keySet = playedLottoNumbers.keySet();
		final WonNumbers wonNumbers = WonNumbers.builder().build();
		final Map<String, List<Collection<Integer>>> wonLottoNumbers1 = wonNumbers
			.getWonLottoNumbers();
		for (final String key : keySet)
		{
			final List<Set<Integer>> list = playedLottoNumbers.get(key);
			final List<Collection<Integer>> sets = ListExtensions
				.newArrayList(wonLottoNumbers1.get(key));
			wonLottoNumbers1.put(key, sets);
			for (final Set<Integer> set : list)
			{
				final Collection<Integer> wonNumbers1 = CollectionExtensions
					.intersection(SetExtensions.newTreeSet(lottoNumbers), set);
				sets.add(wonNumbers1);
			}
		}
		return wonNumbers;
	}

	/**
	 * Calculate draws for statistics.
	 *
	 * @param lottoPlayedNumbers the lotto played numbers
	 * @param winningNumbersCount the winning numbers count
	 * @return the int
	 */
	public static int calculateDraws(final LottoPlayedNumbers lottoPlayedNumbers, final int winningNumbersCount)
	{
		if (!MathExtensions.isBetween(1, 6, winningNumbersCount))
		{
			log.info("winningNumbersCount have to be between 1 and 5");
			return -1;
		}
		final long startTime = System.nanoTime();
		int count = 0;
		LottoLuckyNumbers luckyNumbers = LottoExtensions.newLottoLuckyNumbers();
		count++;
		WonNumbers wonNumbers = LottoExtensions.checkResult(luckyNumbers, lottoPlayedNumbers);
		boolean breakout = false;
		// int i1 = 3;
		while (!breakout)
		{
			wonNumbers = LottoExtensions.checkResult(luckyNumbers, lottoPlayedNumbers);
			final Map<String, List<Collection<Integer>>> wonLottoNumbers = wonNumbers
				.getWonLottoNumbers();
			if (!wonLottoNumbers.isEmpty())
			{
				final List<Collection<Integer>> collections = wonLottoNumbers
					.get(sixOffourtynineGameType);
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
			luckyNumbers = LottoExtensions.newLottoLuckyNumbers();
			count++;
			log.info("This is the " + count + " draw of the lotto queen: " + luckyNumbers);
		}

		log.info("Elapsed time till you have won something: "
			+ calculateElapsedTimeInSeconds(startTime));
		log.info("you have won after " + count + " drawings");
		log.info("you have won: " + wonNumbers);
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
