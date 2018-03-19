package de.alpharogroup.random.lotto;

import lombok.Getter;

/**
 * The enum {@link GameSeventySevenWinCategory}.
 */
@Getter
public enum GameSeventySevenWinCategory
{

	/** The first winning class. */
	FIRST_CLASS(7),
	/** The second winning class. */
	SECOND_CLASS(6),
	/** The third winning class. */
	THIRD_CLASS(5),
	/** The fourth winning class. */
	FOURTH_CLASS(4),
	/** The fifth winning class. */
	FIFTH_CLASS(3),
	/** The sixth winning class. */
	SIXTH_CLASS(2),
	/** The seventh winning class. */
	SEVENTH_CLASS(1),
	/** The none winning class. */
	NONE(0);

	private Integer equalNumbers;

	private GameSeventySevenWinCategory(Integer equalNumbers){
		this.equalNumbers = equalNumbers;
	}

	public static GameSeventySevenWinCategory getGameSeventySevenWinCategory(Integer drawnGameSeventySeven, Integer playedGameSeventySeven)
	{

		String dgss = String.format("%07d", drawnGameSeventySeven);
		String pgss = String.format("%07d", playedGameSeventySeven);

		String rdgss = new StringBuilder(dgss).reverse().toString();
		String rpgss = new StringBuilder(pgss).reverse().toString();

		if(rdgss.equals(rpgss))
		{
			return GameSeventySevenWinCategory.FIRST_CLASS;
		}

		String drawnSubstring = rdgss.substring(0, 6);
		String playedSubstring = rpgss.substring(0, 6);

		if(drawnSubstring.equals(playedSubstring))
		{
			return GameSeventySevenWinCategory.SECOND_CLASS;
		}

		drawnSubstring = rdgss.substring(0, 5);
		playedSubstring = rpgss.substring(0, 5);

		if(drawnSubstring.equals(playedSubstring))
		{
			return GameSeventySevenWinCategory.THIRD_CLASS;
		}

		drawnSubstring = rdgss.substring(0, 4);
		playedSubstring = rpgss.substring(0, 4);

		if(drawnSubstring.equals(playedSubstring))
		{
			return GameSeventySevenWinCategory.FOURTH_CLASS;
		}

		drawnSubstring = rdgss.substring(0, 3);
		playedSubstring = rpgss.substring(0, 3);

		if(drawnSubstring.equals(playedSubstring))
		{
			return GameSeventySevenWinCategory.FIFTH_CLASS;
		}

		drawnSubstring = rdgss.substring(0, 2);
		playedSubstring = rpgss.substring(0, 2);

		if(drawnSubstring.equals(playedSubstring))
		{
			return GameSeventySevenWinCategory.SIXTH_CLASS;
		}

		drawnSubstring = rdgss.substring(0, 1);
		playedSubstring = rpgss.substring(0, 1);

		if(drawnSubstring.equals(playedSubstring))
		{
			return GameSeventySevenWinCategory.SEVENTH_CLASS;
		}
		return GameSeventySevenWinCategory.NONE;
	}

}
