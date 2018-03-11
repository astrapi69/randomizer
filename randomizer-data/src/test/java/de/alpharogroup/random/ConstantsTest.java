/**
 * 
 */
package de.alpharogroup.random;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link Constants}.
 */
public class ConstantsTest {

	/**
	 * Test method for evaluate the constants.
	 */
	@Test
	public void testConstants()
	{
		assertEquals(Constants.SPECIALCHARS, "#@$%^&*?!");

		assertEquals(Constants.NUMBERS, "0123456789");

		assertEquals(Constants.LOWCASECHARS, "abcdefghijklmnopqrstuvwxyz");

		assertEquals(Constants.UPPERCASECHARS, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	}
	
	/**
	 * Test method for evaluate the concatenated constants.
	 */
	@Test
	public void testConcatenatedConst()
	{
		assertEquals(Constants.LCCHARSWN, "abcdefghijklmnopqrstuvwxyz0123456789");
		assertEquals(Constants.LCCHARSWN, Constants.LOWCASECHARS_WITH_NUMBERS);
		assertEquals(Constants.UCCHARSWN, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		assertEquals(Constants.UCCHARSWN, Constants.UPPERCASECHARS_WITH_NUMBERS);
		assertEquals(Constants.LCCHARSWNASC, "abcdefghijklmnopqrstuvwxyz0123456789#@$%^&*?!");
		assertEquals(Constants.LCCHARSWNASC, Constants.LOWCASECHARS_WITH_NUMBERS_AND_SPECIALCHARS);
		assertEquals(Constants.UCCHARSWNASC, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!");
		assertEquals(Constants.UCCHARSWNASC, Constants.UPPERCASECHARS_WITH_NUMBERS_AND_SPECIALCHARS);
		assertEquals(Constants.LCUCCHARSWN, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		assertEquals(Constants.LCUCCHARSWN, Constants.LOWCASECHARS_UPPERCASECHARS_WITH_NUMBERS);
		assertEquals(Constants.LCUCCHARSWNASC, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$%^&*?!");
		assertEquals(Constants.LCUCCHARSWNASC, Constants.LOWCASECHARS_UPPERCASECHARS_WITH_NUMBERS_AND_SPECIALCHARS);
	}
}
