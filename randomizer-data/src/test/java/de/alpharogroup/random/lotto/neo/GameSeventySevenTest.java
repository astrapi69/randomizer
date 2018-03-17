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

import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link GameSeventySeven}.
 */
public class GameSeventySevenTest
{

	/**
	 * Test method for {@link GameSeventySeven#builder()}
	 */
	@Test
	public void testBuilder() {
		GameSeventySeven gameSeventySeven = GameSeventySeven.builder().number(1236547).build();
		assertNotNull(gameSeventySeven);
		gameSeventySeven = new GameSeventySeven(1236547);
		assertNotNull(gameSeventySeven);
	}

	/**
	 * Test method for {@link GameSeventySeven#equals(Object)} , {@link GameSeventySeven#hashCode()}
	 * and {@link GameSeventySeven#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(GameSeventySeven.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link GameSeventySeven}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(GameSeventySeven.class);
	}

}
