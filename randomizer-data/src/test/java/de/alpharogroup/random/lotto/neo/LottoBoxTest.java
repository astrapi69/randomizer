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
package de.alpharogroup.random.lotto.neo;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;
import de.alpharogroup.random.lotto.LottoGameType;

/**
 * The unit test class for the class {@link LottoBox}.
 */
public class LottoBoxTest
{

	/**
	 * Test method for {@link LottoBox#equals(Object)} , {@link LottoBox#hashCode()} and
	 * {@link LottoBox#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(LottoBox.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for creation of object {@link LottoBox}
	 */
	@Test
	public void testObjectCreation()
	{
		LottoBox object = LottoBox.builder().build();
		assertNotNull(object);
		object = new LottoBox(1, LottoGameType.SIX_OF_FOURTYNINE_NORMAL,
			SetExtensions.newHashSet(1, 2, 3, 4, 5, 6), null);
		assertNotNull(object);
	}

	/**
	 * Test method for {@link LottoBox}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(LottoBox.class);
	}
}
