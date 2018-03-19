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

import java.util.HashMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.set.SetExtensions;
import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;
import lombok.Builder;

/**
 * The unit test class for the class {@link EvaluatedLottoNumbers}.
 */
public class EvaluatedLottoNumbersTest
{
	/**
	 * Test method for creation of object {@link EvaluatedLottoNumbers}
	 */
	@Test
	public void testObjectCreation()
	{
		EvaluatedLottoNumbers object = EvaluatedLottoNumbers.builder().build();
		assertNotNull(object);

		/** The id. */
		Integer id = 1;

		/** The played lotto numbers. */
		Map<LottoGameType, List<Collection<Integer>>> wonLottoNumbers = new HashMap<>();

		/** The evaluated super six number. */
		Integer wonSuperSixNumber = 1;

		/** The evaluated super number. */
		Integer wonSuperNumber = 1;

		/** The evaluated game seventy seven. */
		Integer wonGameSeventySevenNumber = 1;

		object = new EvaluatedLottoNumbers(id, wonLottoNumbers, wonSuperSixNumber, wonSuperNumber, wonGameSeventySevenNumber);
		assertNotNull(object);
	}

	/**
	 * Test method for {@link EvaluatedLottoNumbers#equals(Object)} ,
	 * {@link EvaluatedLottoNumbers#hashCode()} and {@link EvaluatedLottoNumbers#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(EvaluatedLottoNumbers.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EvaluatedLottoNumbers}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(EvaluatedLottoNumbers.class);
	}

}
