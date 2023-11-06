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

import java.awt.Point;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import io.github.astrapi69.collection.list.ListFactory;
import io.github.astrapi69.copy.object.CopyObjectExtensions;
import io.github.astrapi69.lang.ClassExtensions;
import io.github.astrapi69.random.DefaultSecureRandom;
import io.github.astrapi69.random.RandomCharacters;
import io.github.astrapi69.random.date.RandomDateFactory;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;
import io.github.astrapi69.random.number.RandomBigDecimalFactory;
import io.github.astrapi69.random.number.RandomBigIntegerFactory;
import io.github.astrapi69.random.number.RandomBooleanFactory;
import io.github.astrapi69.random.number.RandomByteFactory;
import io.github.astrapi69.random.number.RandomCharFactory;
import io.github.astrapi69.random.number.RandomDoubleFactory;
import io.github.astrapi69.random.number.RandomFloatFactory;
import io.github.astrapi69.random.number.RandomIntFactory;
import io.github.astrapi69.random.number.RandomLongFactory;
import io.github.astrapi69.random.number.RandomShortFactory;
import io.github.astrapi69.reflection.InstanceFactory;
import io.github.astrapi69.reflection.ReflectionExtensions;

/**
 * A factory for creating random objects
 */
public final class RandomObjectFactory
{
	private RandomObjectFactory()
	{
	}

	/**
	 * Factory method for create a new random {@link RandomAlgorithm} object
	 *
	 * @return the random algorithm
	 */
	public static RandomAlgorithm newRandomAlgorithm()
	{
		return randomEnumFromEnumValues(RandomAlgorithm.values());
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
		byte[] randomBytes = RandomByteFactory.randomByteArray(length);
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
		return RandomFloatFactory.randomFloat(afterComma, beforeComma);
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
		T instance = InstanceFactory.newInstance(cls);
		return setRandomValues(cls, instance, ignoreFieldNames);
	}

	/**
	 * Factory method for create a new random object of the given object
	 *
	 * @param <T>
	 *            the generic type
	 * @param obj
	 *            the object
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
	@SuppressWarnings("unchecked")
	public static <T> T newRandomObject(final T obj, String... ignoreFieldNames)
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		Objects.requireNonNull(obj);
		Class<T> cls = (Class<T>)obj.getClass();
		T copy = CopyObjectExtensions.copyObject(obj, ignoreFieldNames);
		return setRandomValues(cls, copy, ignoreFieldNames);
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
		Class<?> fieldType = field.getType();

		if (fieldType.isEnum())
		{
			Enum<?> randomEnum = randomEnumFromClassname(fieldType.getCanonicalName());
			return randomEnum;
		}
		else if (fieldType.equals(Void.TYPE) || fieldType.equals(Void.class))
		{
			return null;
		}
		else if (fieldType.equals(Byte.TYPE) || fieldType.equals(Byte.class))
		{
			return Byte.valueOf(RandomByteFactory.randomByte());
		}
		else if (fieldType.equals(Character.TYPE) || fieldType.equals(Character.class))
		{
			return Character.valueOf(RandomCharFactory.randomChar());
		}
		else if (fieldType.equals(Short.TYPE) || fieldType.equals(Short.class))
		{
			return Short.valueOf(RandomShortFactory.randomShort());
		}
		else if (fieldType.equals(Boolean.TYPE) || fieldType.equals(Boolean.class))
		{
			return Boolean.valueOf(RandomBooleanFactory.randomBoolean());
		}
		else if (fieldType.equals(Integer.TYPE) || fieldType.equals(Integer.class))
		{
			return Integer.valueOf(RandomIntFactory.randomInt());
		}
		else if (fieldType.equals(Long.TYPE) || fieldType.equals(Long.class))
		{
			return Long.valueOf(RandomLongFactory.randomLong());
		}
		else if (fieldType.equals(Double.TYPE) || fieldType.equals(Double.class))
		{
			return Double.valueOf(RandomDoubleFactory.randomDouble());
		}
		else if (fieldType.equals(Float.TYPE) || fieldType.equals(Float.class))
		{
			return Float.valueOf(RandomFloatFactory.randomFloat());
		}
		else if (fieldType.equals(String.class))
		{
			return RandomStringFactory.newRandomString();
		}
		else if (fieldType.equals(BigInteger.class))
		{
			return RandomBigIntegerFactory.randomBigInteger();
		}
		else if (fieldType.equals(BigDecimal.class))
		{
			return RandomBigDecimalFactory.randomBigDecimal();
		}
		else if (fieldType.equals(Date.class))
		{
			return RandomDateFactory.randomDate();
		}
		else if (fieldType.equals(LocalDateTime.class))
		{
			return RandomDateFactory.randomLocalDateTime();
		}
		else if (fieldType.equals(LocalDate.class))
		{
			return RandomDateFactory.randomLocalDate();
		}
		else if (fieldType.equals(LocalTime.class))
		{
			return RandomDateFactory.randomLocalTime();
		}
		return newRandomObject(fieldType);
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
	 *            the field names to ignore
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
		Field[] allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(cls,
			ignoreFieldNames);
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

	/**
	 * Returns a random entry from the given List.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            The List.
	 * @return Return's a random entry from the List.
	 */
	public static <T> T randomListEntry(final List<T> list)
	{
		return list.get(randomIndex(list));
	}

	/**
	 * Returns a random entry from the given map.
	 *
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @param map
	 *            The map.
	 * @return Return's a random entry from the map.
	 */
	public static <K, V> Object randomMapEntry(final Map<K, V> map)
	{
		final Object[] entries = map.values().toArray();
		return entries[RandomIntFactory.randomInt(entries.length)];
	}

	/**
	 * Gets the random enum.
	 *
	 * @param <T>
	 *            the generic type
	 * @param clazz
	 *            the clazz
	 * @return the random enum
	 */
	public static <T extends Enum<?>> T randomEnumFromClass(final Class<T> clazz)
	{
		return randomEnumFromEnumValues(clazz.getEnumConstants());
	}

	/**
	 * Gets the random enum.
	 *
	 * @param <T>
	 *            the generic type
	 * @param classname
	 *            the classname
	 * @return the random enum
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<?>> T randomEnumFromClassname(final String classname)
	{
		if (classname != null && !classname.isEmpty())
		{
			Class<T> enumClass;
			try
			{
				enumClass = (Class<T>)ClassExtensions.forName(classname);
				return randomEnumFromClass(enumClass);
			}
			catch (final ClassNotFoundException e)
			{
				return null;
			}
		}
		return null;
	}

	/**
	 * Gets the random enum.
	 *
	 * @param <T>
	 *            the generic type
	 * @param values
	 *            the values
	 * @return the random enum
	 */
	public static <T extends Enum<?>> T randomEnumFromEnumValues(final T[] values)
	{
		return values[RandomIntFactory.randomInt(values.length)];
	}

	/**
	 * Gets the random enum.
	 *
	 * @param <T>
	 *            the generic type
	 * @param obj
	 *            the obj
	 * @return the random enum
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<?>> T randomEnumFromObject(final T obj)
	{
		if (obj != null)
		{
			final Class<T> clazz = (Class<T>)obj.getClass();
			return randomEnumFromClass(clazz);
		}
		return null;
	}

	/**
	 * Returns a random index from the given List.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            The List.
	 * @return Return's a random index from the List.
	 */
	public static <T> int randomIndex(final Collection<T> list)
	{
		return RandomIntFactory.randomInt(list.size());
	}

	/**
	 * Returns a random key from the given map.
	 *
	 * @param <K>
	 *            the key type
	 * @param <V>
	 *            the value type
	 * @param map
	 *            The map.
	 * @return Return's a random key from the map.
	 */
	public static <K, V> Object randomKey(final Map<K, V> map)
	{
		final Set<K> keySet = map.keySet();
		final Object[] keys = keySet.toArray();
		return keys[RandomIntFactory.randomInt(keys.length)];
	}

	/**
	 * Gets the random salt.
	 *
	 * @param length
	 *            the length
	 * @param charset
	 *            the charset
	 * @return the random salt
	 */
	public static byte[] randomSalt(final int length, final Charset charset)
	{
		return RandomStringFactory
			.newRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(),
				length)
			.getBytes(charset);
	}

	/**
	 * Generates a random int for use with pixel.
	 *
	 * @return a random int for use with pixel.
	 */
	public static int randomPixel()
	{
		return randomPixel(RandomIntFactory.randomInt(256), RandomIntFactory.randomInt(256),
			RandomIntFactory.randomInt(256), RandomIntFactory.randomInt(256));
	}

	/**
	 * Generates a random int for use with pixel.
	 *
	 * @param red
	 *            The red value.
	 * @param green
	 *            The green value.
	 * @param blue
	 *            The blue value.
	 * @param alpha
	 *            The alpha value.
	 * @return a random int for use with pixel.
	 */
	public static int randomPixel(final int red, final int green, final int blue, final int alpha)
	{
		final int pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
		return pixel;
	}

	/**
	 * Factory method for create a new random salt.
	 *
	 * @return the byte[] with the new random salt.
	 */
	public static byte[] newSalt()
	{
		return RandomByteFactory.randomByteArray(16);
	}

	/**
	 * Gets a random neighbor {@link Point} object around of the given {@link Point} object
	 *
	 * @param fromPoint
	 *            The {@link Point} object to start
	 * @return a random point around from the given {@link Point} object
	 */
	public static Point randomNeighborPoint(final Point fromPoint)
	{
		return randomNeighborPoint(fromPoint, false);
	}

	/**
	 * Gets a random neighbor {@link Point} object around of the given {@link Point} object
	 *
	 * @param fromPoint
	 *            The {@link Point} object to start
	 * @param withNegativeValues
	 *            The flag if negative values are allowed for x or y
	 * @return a random point around from the given {@link Point} object
	 */
	public static Point randomNeighborPoint(final Point fromPoint, boolean withNegativeValues)
	{
		int yMinusOne;
		int xMinusOne;
		if (withNegativeValues)
		{
			yMinusOne = fromPoint.y - 1;
			xMinusOne = fromPoint.x - 1;
		}
		else
		{
			yMinusOne = 0 < fromPoint.y ? fromPoint.y - 1 : fromPoint.y;
			xMinusOne = 0 < fromPoint.x ? fromPoint.x - 1 : fromPoint.x;
		}
		int yPlusOne = fromPoint.y + 1;
		int xPlusOne = fromPoint.x + 1;
		Point top = new Point(fromPoint.x, yMinusOne);
		Point bottom = new Point(fromPoint.x, yPlusOne);
		Point right = new Point(xPlusOne, fromPoint.y);
		Point left = new Point(xMinusOne, fromPoint.y);
		Point topLeft = new Point(xMinusOne, yMinusOne);
		Point topRight = new Point(xPlusOne, yMinusOne);
		Point bottomRight = new Point(xPlusOne, yPlusOne);
		Point bottomLeft = new Point(xMinusOne, yPlusOne);
		List<Point> pointList = ListFactory.newArrayList(top, bottom, right, left, topLeft,
			topRight, bottomRight, bottomLeft);
		return randomListEntry(pointList);
	}

	/**
	 * Returns a random token for use in web services.
	 *
	 * @return A random token.
	 */
	public static String randomToken()
	{
		final BigInteger token = new BigInteger(130, DefaultSecureRandom.get());
		final String randomToken = token.toString(32);
		return randomToken;
	}

	/**
	 * Factory method for create a new random {@link UUID}
	 *
	 * @return the new random {@link UUID}
	 */
	public static UUID randomUUID()
	{
		return UUID.randomUUID();
	}

}
