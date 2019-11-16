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
package de.alpharogroup.random.`object`

import de.alpharogroup.test.objects.Person
import org.junit.Assert.assertNotEquals
import org.meanbean.test.BeanTestException
import org.meanbean.test.BeanTester
import org.testng.Assert.assertNotNull
import org.testng.AssertJUnit.assertTrue
import org.testng.annotations.Test
import java.lang.reflect.InvocationTargetException

/**
 * The unit test class for the class [RandomObjectFactory]
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomObjectFactoryTest {

    /**
     * Test method for [RandomObjectFactory.newRandomAlgorithm]
     */
    @Test(enabled = true)
    fun testNewRandomAlgorithm() {
        val randomAlgorithm = RandomObjectFactory.newRandomAlgorithm()
        assertNotNull(randomAlgorithm)
    }

    /**
     * Test method for [RandomObjectFactory.newRandomByteObjects]
     */
    @Test(enabled = true)
    fun testNewRandomByteObjects() {
        val randomByteArray = RandomObjectFactory.newRandomByteObjects(5)
        assertTrue(randomByteArray.size == 5)
    }

    /**
     * Test method for [RandomObjectFactory.newRandomObject]
     *
     * @throws IllegalAccessException
     * is thrown if the class or its default constructor is not accessible.
     * @throws InstantiationException
     * is thrown if this `Class` represents an abstract class, an interface, an
     * array class, a primitive type, or void; or if the class has no default
     * constructor; or if the instantiation fails for some other reason.
     * @throws NoSuchFieldException
     * is thrown if no such field exists
     */
    @Test
    @Throws(IllegalAccessException::class, InstantiationException::class, NoSuchFieldException::class)
    fun testNewRandomObject() {
        val person = RandomObjectFactory.newRandomObject<Person>(Person::class.java)
        assertNotNull(person)
        val person2 = RandomObjectFactory.newRandomObject<Person>(Person::class.java)
        assertNotNull(person2)
        assertNotEquals(person, person2)
    }

    /**
     * Test method for [RandomObjectFactory.newRandomObject]
     *
     * @throws IllegalAccessException
     * is thrown if the class or its default constructor is not accessible.
     * @throws InstantiationException
     * is thrown if this `Class` represents an abstract class, an interface, an
     * array class, a primitive type, or void; or if the class has no default
     * constructor; or if the instantiation fails for some other reason.
     * @throws NoSuchFieldException
     * is thrown if no such field exists
     */
    @Test
    @Throws(IllegalAccessException::class, InstantiationException::class, NoSuchFieldException::class)
    fun testNewRandomObjectVarargs() {
        val person = RandomObjectFactory.newRandomObject<Person>(Person::class.java, "name")
        assertNotNull(person)
    }

    /**
     * Test method for [RandomObjectFactory] with [BeanTester]
     */
    @Test fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(RandomObjectFactory::class.java)
    }

}
