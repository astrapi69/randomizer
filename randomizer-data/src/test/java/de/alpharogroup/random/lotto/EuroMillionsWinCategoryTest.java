package de.alpharogroup.random.lotto;

import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The class {@link EuroMillionsWinCategory}.
 */
public class EuroMillionsWinCategoryTest {

	/**
	 * Test method for {@link EuroMillionsWinCategory#equals(Object)} , {@link EuroMillionsWinCategory#hashCode()} and
	 * {@link EuroMillionsWinCategory#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean expected;
		boolean actual;
		actual =SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(EuroMillionsWinCategory.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link EuroMillionsWinCategory}
	 */
	@Test(expectedExceptions = { ObjectCreationException.class, BeanTestException.class,  NoSuchMethodException.class,
		UnsupportedOperationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(EuroMillionsWinCategory.class);
	}
	
}
