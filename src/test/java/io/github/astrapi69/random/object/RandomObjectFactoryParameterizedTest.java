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
package io.github.astrapi69.random.object;

import org.testng.annotations.DataProvider;

import io.github.astrapi69.test.object.A;
import io.github.astrapi69.test.object.AlgorithmModel;
import io.github.astrapi69.test.object.ClonableObject;
import io.github.astrapi69.test.object.Company;
import io.github.astrapi69.test.object.Customer;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.test.object.EmployeeList;
import io.github.astrapi69.test.object.Light;
import io.github.astrapi69.test.object.Member;
import io.github.astrapi69.test.object.NotSerializable;
import io.github.astrapi69.test.object.Permission;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.PremiumMember;
import io.github.astrapi69.test.object.Television;
import io.github.astrapi69.test.object.annotation.classtype.AnnotatedClass;
import io.github.astrapi69.test.object.annotation.classtype.AnnotatedTestClass;
import io.github.astrapi69.test.object.annotation.classtype.ClassExtendsAnnotatedInterface;
import io.github.astrapi69.test.object.annotation.classtype.SubAnnotatedClass;
import io.github.astrapi69.test.object.auth.AccessRight;
import io.github.astrapi69.test.object.auth.Role;
import io.github.astrapi69.test.object.auth.Roles;
import io.github.astrapi69.test.object.exception.ExceptionEvent;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertNotNull;

/**
 * The unit test class for the class {@link RandomObjectFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomObjectFactoryParameterizedTest
{

	public static final String TEST_OBJECTS = "testObjects";

	@DataProvider(name = TEST_OBJECTS)
	public static Object[][] testObjects()
	{
		return new Object[][] { { Person.class }, { AnnotatedClass.class },
				{ AnnotatedTestClass.class }, { ClassExtendsAnnotatedInterface.class },
				{ SubAnnotatedClass.class }, { AccessRight.class }, { Roles.class }, { Role.class },
				{ AlgorithmModel.class }, { A.class }, { ClonableObject.class }, { Company.class },
				{ Customer.class }, { EmployeeList.class }, { Employee.class }, { Light.class },
				{ Member.class }, { NotSerializable.class }, { Permission.class }, { Person.class },
				{ PremiumMember.class }, { Television.class }, };
	}

	@Test(dataProvider = TEST_OBJECTS)
	public void test(Class<?> val)
		throws NoSuchFieldException, IllegalAccessException, InstantiationException
	{
		assertNotNull(RandomObjectFactory.newRandomObject(val));
	}
}
