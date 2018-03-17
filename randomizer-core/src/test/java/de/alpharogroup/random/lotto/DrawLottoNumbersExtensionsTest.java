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
		Set<Integer> lottoNumbers = DrawLottoNumbersExtensions.draw(6, 49);
		assertTrue(lottoNumbers.size() == 6);
		for (Integer lottoNumber : lottoNumbers)
		{
			assertTrue(MathExtensions.isBetween(1, 49, lottoNumber, true, true));
		}
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
