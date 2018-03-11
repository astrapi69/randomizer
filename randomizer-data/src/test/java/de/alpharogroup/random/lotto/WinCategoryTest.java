package de.alpharogroup.random.lotto;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The class {@link WinCategory}.
 */
public class WinCategoryTest {

	/**
	 * Test method for {@link WinCategory#equals(Object)} , {@link WinCategory#hashCode()} and
	 * {@link WinCategory#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual =SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(WinCategory.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link WinCategory}
	 */
	@Test(expectedExceptions = { ObjectCreationException.class, BeanTestException.class,  NoSuchMethodException.class,
		UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(WinCategory.class);
	}
	
}
