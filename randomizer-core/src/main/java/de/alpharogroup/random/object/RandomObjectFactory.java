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
package de.alpharogroup.random.object;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.random.date.RandomDateExtensions;
import de.alpharogroup.random.enums.RandomAlgorithm;
import de.alpharogroup.random.number.RandomNumberExtensions;
import de.alpharogroup.random.number.RandomPrimitivesExtensions;
import de.alpharogroup.reflection.ReflectionExtensions;

/**
 * A factory for creating random objects
 */
public final class RandomObjectFactory
{
	/**
	 * Factory method for create a new random {@link RandomAlgorithm} object
	 *
	 * @return the random algorithm
	 */
	public static RandomAlgorithm newRandomAlgorithm()
	{
		return RandomExtensions.getRandomEnumFromEnumValues(RandomAlgorithm.values());
	}

	/**
	 * Factory method for create a new random {@link Byte} object array
	 *
	 * @param length
	 *            the length.
	 * @return the new random {@link Byte} object array
	 */
	public static Byte[] newRandomByteObjects(final int length)
	{
		final Byte[] randomByteObjects = new Byte[length];
		byte[] randomBytes = RandomPrimitivesExtensions.randomByteArray(length);
		for (int i = 0; i < length; i++)
		{
			randomByteObjects[i] = randomBytes[i];
		}
		return randomByteObjects;
	}

	/**
	 * Factory method for create a new random {@link Float} object
	 *
	 * @param afterComma
	 *            How many decimal places after the comma
	 * @param beforeComma
	 *            How many decimal places before the comma
	 * @return the new random {@link Float} object
	 */
	public static Float newRandomFloat(final int afterComma, final int beforeComma)
	{
		return RandomPrimitivesExtensions.randomFloat(afterComma, beforeComma);
	}

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
	public static <T> T newRandomObject(final Class<T> cls, String... ignoreFieldNames)
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		Objects.requireNonNull(cls);
		T instance = ReflectionExtensions.newInstance(cls);
		return setRandomValues(cls, instance, ignoreFieldNames);
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
			return Byte.valueOf(RandomPrimitivesExtensions.randomByte());
		}
		else if (type.equals(Character.TYPE) || type.equals(Character.class))
		{
			return Character.valueOf(RandomPrimitivesExtensions.randomChar());
		}
		else if (type.equals(Short.TYPE) || type.equals(Short.class))
		{
			return Short.valueOf(RandomPrimitivesExtensions.randomShort());
		}
		else if (type.equals(Boolean.TYPE) || type.equals(Boolean.class))
		{
			return Boolean.valueOf(RandomPrimitivesExtensions.randomBoolean());
		}
		else if (type.equals(Integer.TYPE) || type.equals(Integer.class))
		{
			return Integer.valueOf(RandomPrimitivesExtensions.randomInt());
		}
		else if (type.equals(Long.TYPE) || type.equals(Long.class))
		{
			return Long.valueOf(RandomPrimitivesExtensions.randomLong());
		}
		else if (type.equals(Double.TYPE) || type.equals(Double.class))
		{
			return Double.valueOf(RandomPrimitivesExtensions.randomDouble());
		}
		else if (type.equals(Float.TYPE) || type.equals(Float.class))
		{
			return Float.valueOf(RandomPrimitivesExtensions.randomFloat());
		}
		else if (type.equals(String.class))
		{
			return RandomStringFactory.newRandomString();
		}
		else if (type.equals(BigInteger.class))
		{
			return RandomNumberExtensions.randomBigInteger();
		}
		else if (type.equals(BigDecimal.class))
		{
			return RandomNumberExtensions.randomBigDecimal();
		}
		else if (type.equals(Date.class))
		{
			return RandomDateExtensions.randomDate();
		}
		else if (type.equals(LocalDateTime.class))
		{
			return RandomDateExtensions.randomLocalDateTime();
		}
		else if (type.equals(LocalDate.class))
		{
			return RandomDateExtensions.randomLocalDate();
		}
		else if (type.equals(LocalTime.class))
		{
			return RandomDateExtensions.randomLocalTime();
		}
		return newRandomObject(type);
	}

	/**
	 * Sets the random values to the fields of the given instance
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            the cls
	 * @param instance
	 *            the instance to set random values
	 * @param ignoreFieldNames
	 *            the ignore field names
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
	public static <T> T setRandomValues(final Class<T> cls, final T instance,
		String... ignoreFieldNames)
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		Objects.requireNonNull(cls);
		Objects.requireNonNull(instance);
		Field[] allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(cls);
		List<String> toIgnoreFields = Arrays.asList(ignoreFieldNames);
		for (Field field : allDeclaredFields)
		{
			if (Modifier.isFinal(field.getModifiers()) || toIgnoreFields.contains(field.getName()))
			{
				continue;
			}
			Object value = newRandomValue(field);
			ReflectionExtensions.setFieldValue(instance, field, value);
		}
		return instance;
	}

	private RandomObjectFactory()
	{
	}

}
