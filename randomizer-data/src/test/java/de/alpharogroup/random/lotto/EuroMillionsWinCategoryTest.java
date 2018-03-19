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
import static org.testng.AssertJUnit.assertEquals;

import java.util.Set;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link EuroMillionsWinCategory}.
 */
public class EuroMillionsWinCategoryTest
{

	/**
	 * Test method for creation of object {@link EuroMillionsWinCategory}
	 */
	@Test
	public void testObjectCreation()
	{
		EuroMillionsWinCategory object = EuroMillionsWinCategory.builder().build();
		assertNotNull(object);

		/** The quantity of winning numbers. */
		int quantityOfWonNumbers = 3;

		/** The quantity of winning star numbers. */
		int quantityOfWonStarNumbers = 2;

		/** The flag if the played super number is selected. */
		boolean withSuperNumber = false;

		/** The payout rate of this winning category. */
		double payoutRate = 0.1;

		/** The computation. */
		String computation = "foo";

		object = new EuroMillionsWinCategory(quantityOfWonNumbers, quantityOfWonStarNumbers, withSuperNumber, payoutRate, computation);
		assertNotNull(object);
	}

	/**
	 * Test method for {@link EuroMillionsWinCategory#equals(Object)} ,
	 * {@link EuroMillionsWinCategory#hashCode()} and {@link EuroMillionsWinCategory#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(EuroMillionsWinCategory.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EuroMillionsWinCategory}
	 */
	@Test(expectedExceptions = { ObjectCreationException.class, BeanTestException.class,
			NoSuchMethodException.class, UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(EuroMillionsWinCategory.class);
	}

}
