package de.alpharogroup.random.lotto;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

/**
 * The class {@link GameSeventySevenWinCategory}.
 */
public class GameSeventySevenWinCategoryTest
{

	/**
	 * Test method for {@link GameSeventySevenWinCategory#getGameSeventySevenWinCategory(java.lang.Integer, java.lang.Integer)}.
	 */
	@Test
	public void testGetGameSeventySevenWinCategory() throws Exception
	{
		GameSeventySevenWinCategory expected;
		GameSeventySevenWinCategory actual;

		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 7777777);
		expected = GameSeventySevenWinCategory.FIRST_CLASS;
		assertEquals(actual, expected);

		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 777777);
		expected = GameSeventySevenWinCategory.SECOND_CLASS;
		assertEquals(actual, expected);

		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 77777);
		expected = GameSeventySevenWinCategory.THIRD_CLASS;
		assertEquals(actual, expected);

		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 7777);
		expected = GameSeventySevenWinCategory.FOURTH_CLASS;
		assertEquals(actual, expected);
		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 777);
		expected = GameSeventySevenWinCategory.FIFTH_CLASS;
		assertEquals(actual, expected);

		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 77);
		expected = GameSeventySevenWinCategory.SIXTH_CLASS;
		assertEquals(actual, expected);

		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 7);
		expected = GameSeventySevenWinCategory.SEVENTH_CLASS;
		assertEquals(actual, expected);

		actual = GameSeventySevenWinCategory.getGameSeventySevenWinCategory(7777777, 1);
		expected = GameSeventySevenWinCategory.NONE;
		assertEquals(actual, expected);
	}

}
