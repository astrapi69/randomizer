package de.alpharogroup.random.lotto;

import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.math.MathExtensions;

/**
 * The class {@link DrawLottoNumbersExtensionsTest}.
 */
public class DrawLottoNumbersExtensionsTest
{

	/**
	 * Test method for {@link DrawLottoNumbersExtensions#draw(int, int)}.
	 */
	@Test
	public void testDraw() throws Exception
	{
		throw new RuntimeException("not yet implemented");
	}

	/**
	 * Test method for {@link DrawLottoNumbersExtensions#drawSuperNumber(Set, int)}.
	 */
	@Test
	public void testDrawSuperNumber() throws Exception
	{
		Set<Integer> alreadyDrawnNumbers = SetExtensions.newTreeSet(6, 12, 15, 18, 25, 30);
		int superNumber = DrawLottoNumbersExtensions.drawSuperNumber(alreadyDrawnNumbers, 49);
		assertTrue(!alreadyDrawnNumbers.contains(superNumber));
		assertTrue(MathExtensions.isBetween(1, 49, superNumber, true, true));
	}

}
