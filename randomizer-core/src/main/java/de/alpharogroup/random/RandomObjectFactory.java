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
package de.alpharogroup.random;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import de.alpharogroup.reflection.ReflectionExtensions;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * A factory for creating random objects
 */
@UtilityClass
public class RandomObjectFactory
{

	/**
	 * Factory method for create a new random object of the given {@link Class}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            the class
	 * @param ignoreFieldNames
	 *            an optional array with the field names that shell be ignored
	 * @return the new random object
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 */
	public static <T> T newRandomObject(final @NonNull Class<T> cls, String... ignoreFieldNames)
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		T instance = ReflectionExtensions.newInstance(cls);
		Field[] allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(cls);
		List<String> toIgnoreFields = Arrays.asList(ignoreFieldNames);
		for (Field field : allDeclaredFields)
		{
			if (Modifier.isFinal(field.getModifiers()) || toIgnoreFields.contains(field.getName()))
			{
				continue;
			}
			Object value = newRandomValue(field);
			ReflectionExtensions.setFieldValue(instance, field.getName(), value);
		}
		return instance;
	}

	/**
	 * Factory method for create a new random value for the given {@link Field field}
	 *
	 * @param field
	 *            the field
	 * @return the new random value
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 */
	public static Object newRandomValue(Field field)
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		Class<?> type = field.getType();
		if (type.isEnum())
		{
			Enum<?> randomEnum = RandomExtensions
				.getRandomEnumFromClassname(type.getCanonicalName());
			return randomEnum;
		}
		else if (type.equals(Void.TYPE) || type.equals(Void.class))
		{
			return null;
		}
		else if (type.equals(Byte.TYPE) || type.equals(Byte.class))
		{
			return Byte.valueOf(RandomExtensions.randomByte());
		}
		else if (type.equals(Character.TYPE) || type.equals(Character.class))
		{
			return Character.valueOf(RandomExtensions.randomChar());
		}
		else if (type.equals(Short.TYPE) || type.equals(Short.class))
		{
			return Short.valueOf(RandomExtensions.randomShort());
		}
		else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class))
		{
			return Boolean.valueOf(RandomExtensions.randomBoolean());
		}
		else if (type.equals(Integer.TYPE) || type.equals(Integer.class))
		{
			return Integer.valueOf(RandomExtensions.randomInt());
		}
		else if (type.equals(Long.TYPE) || type.equals(Long.class))
		{
			return Long.valueOf(RandomExtensions.randomLong());
		}
		else if (type.equals(Double.TYPE) || type.equals(Double.class))
		{
			return Double.valueOf(RandomExtensions.randomDouble());
		}
		else if (type.equals(Float.TYPE) || type.equals(Float.class))
		{
			return Float.valueOf(RandomExtensions.randomFloat());
		}
		else if (type.equals(String.class))
		{
			return RandomExtensions.getRandomString();
		}
		else if (type.equals(BigInteger.class))
		{
			return RandomExtensions.randomBigInteger();
		}
		else if (type.equals(BigDecimal.class))
		{
			return RandomExtensions.randomBigDecimal();
		}
		return newRandomObject(type);
	}

}
