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
package de.alpharogroup.random

import org.testng.Assert.assertNotNull

import java.lang.reflect.InvocationTargetException
import java.security.SecureRandom

import org.meanbean.test.BeanTestException
import org.meanbean.test.BeanTester
import org.testng.annotations.Test

/**
 * The unit test class for the class [SecureRandomFactory]
 */
class SecureRandomFactoryTest {

    /**
     * Test method for [SecureRandomFactory.newSecureRandom]
     */
    @Test
    fun testNewSecureRandom() {
        val secureRandom = SecureRandomFactory.newSecureRandom()
        assertNotNull(secureRandom)
    }

    /**
     * Test method for [SecureRandomFactory.newSecureRandom]
     */
    @Test
    fun testNewSecureRandomAlgorithm() {
        val secureRandom = SecureRandomFactory
                .newSecureRandom(SecureRandomBean.DEFAULT_ALGORITHM)
        assertNotNull(secureRandom)
    }

    /**
     * Test method for [SecureRandomFactory.newSecureRandom]
     */
    @Test
    fun testNewSecureRandomAlgorithmProvider() {
        val secureRandom = SecureRandomFactory
                .newSecureRandom(SecureRandomBean.DEFAULT_ALGORITHM, SecureRandomBean.DEFAULT_PROVIDER)
        assertNotNull(secureRandom)
    }

    /**
     * Test method for [SecureRandomFactory] with [BeanTester]
     */
    @Test(enabled = false, expectedExceptions = [BeanTestException::class, InvocationTargetException::class, UnsupportedOperationException::class])
    fun testWithBeanTester() {
        val beanTester = BeanTester()
        beanTester.testBean(SecureRandomFactory::class.java)
    }

}
