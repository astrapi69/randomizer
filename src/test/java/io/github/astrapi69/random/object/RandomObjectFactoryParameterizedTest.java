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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.FieldSource;
import org.junit.jupiter.params.provider.MethodSource;

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
import io.github.astrapi69.test.object.enumeration.Gender;

/**
 * The unit test class for the class {@link RandomObjectFactory}
 *
 * @version 1.0
 * @author Asterios Raptis
 */
public class RandomObjectFactoryParameterizedTest
{

	public static final String TEST_OBJECTS = "testObjects";

	static List<Class> testClasses = Arrays.asList(Person.class, AnnotatedClass.class,
		AnnotatedTestClass.class, ClassExtendsAnnotatedInterface.class, SubAnnotatedClass.class,
		AccessRight.class, Roles.class, Role.class, AlgorithmModel.class, A.class,
		ClonableObject.class, Company.class, Customer.class, EmployeeList.class, Employee.class,
		Light.class, Member.class, NotSerializable.class, Permission.class, Person.class,
		PremiumMember.class, Television.class);

	@ParameterizedTest
	@FieldSource("testClasses")
	public void test(Class<?> val)
		throws NoSuchFieldException, IllegalAccessException, InstantiationException
	{
		assertNotNull(RandomObjectFactory.newRandomObject(val));
	}

	/**
	 * One row per field of {@link AllFieldTypes}: the field and the type of the value
	 * {@link RandomObjectFactory#newRandomValue(java.lang.reflect.Field)} creates for it, the
	 * wrapper for a primitive
	 *
	 * @return the rows
	 */
	static Stream<Arguments> fieldTypes()
	{
		return Stream.of(argumentSet("byte", "primitiveByte", Byte.class),
			argumentSet("Byte", "byteObject", Byte.class),
			argumentSet("char", "primitiveChar", Character.class),
			argumentSet("Character", "characterObject", Character.class),
			argumentSet("short", "primitiveShort", Short.class),
			argumentSet("Short", "shortObject", Short.class),
			argumentSet("boolean", "primitiveBoolean", Boolean.class),
			argumentSet("Boolean", "booleanObject", Boolean.class),
			argumentSet("int", "primitiveInt", Integer.class),
			argumentSet("Integer", "integerObject", Integer.class),
			argumentSet("long", "primitiveLong", Long.class),
			argumentSet("Long", "longObject", Long.class),
			argumentSet("double", "primitiveDouble", Double.class),
			argumentSet("Double", "doubleObject", Double.class),
			argumentSet("float", "primitiveFloat", Float.class),
			argumentSet("Float", "floatObject", Float.class),
			argumentSet("String", "string", String.class),
			argumentSet("BigInteger", "bigInteger", BigInteger.class),
			argumentSet("BigDecimal", "bigDecimal", BigDecimal.class),
			argumentSet("Date", "date", Date.class),
			argumentSet("LocalDateTime", "localDateTime", LocalDateTime.class),
			argumentSet("LocalDate", "localDate", LocalDate.class),
			argumentSet("LocalTime", "localTime", LocalTime.class),
			argumentSet("enum", "gender", Gender.class),
			argumentSet("any other class", "person", Person.class));
	}

	/**
	 * {@link RandomObjectFactory#newRandomValue(java.lang.reflect.Field)} creates a value of the
	 * type of the field
	 */
	@ParameterizedTest
	@MethodSource("fieldTypes")
	public void newRandomValueHasTheTypeOfTheField(String fieldName, Class<?> expectedType)
		throws NoSuchFieldException, IllegalAccessException, InstantiationException
	{
		Object value = RandomObjectFactory
			.newRandomValue(AllFieldTypes.class.getDeclaredField(fieldName));
		assertTrue(expectedType.isInstance(value),
			() -> value + " is not a " + expectedType.getSimpleName());
	}

	/**
	 * {@link RandomObjectFactory#newRandomValue(java.lang.reflect.Field)} creates null for a field
	 * of type {@link Void}, the only value it has
	 */
	@Test
	public void newRandomValueOfVoidIsNull()
		throws NoSuchFieldException, IllegalAccessException, InstantiationException
	{
		assertNull(
			RandomObjectFactory.newRandomValue(AllFieldTypes.class.getDeclaredField("nothing")));
	}
}
