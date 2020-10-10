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

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import de.alpharogroup.lang.ClassExtensions;
import de.alpharogroup.random.number.RandomByteFactory;
import de.alpharogroup.random.number.RandomIntFactory;
import de.alpharogroup.random.object.RandomStringFactory;

/**
 * Utility class for producing random data. Existing name conventions:
 *
 * If the method starts with random* than it returns a primitive data type. If the method starts
 * with getRandom* than it returns an object.
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomExtensions
{

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
			Class<T> enumClass = null;
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
	 * Generates a random hexadecimal {@link String}
	 *
	 * @param numberOfCharacters
	 *            the number of characters
	 * @return the generated random hexadecimal {@link String}
	 */
	public static String randomHexString(int numberOfCharacters)
	{
		StringBuilder sb = new StringBuilder();
		while (sb.length() < numberOfCharacters)
		{
			sb.append(Integer.toHexString(RandomIntFactory.randomInt()));
		}
		return sb.toString().substring(0, numberOfCharacters);
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
		return randomPixel(RandomIntFactory.randomInt(256),
				RandomIntFactory.randomInt(256), RandomIntFactory.randomInt(256),
				RandomIntFactory.randomInt(256));
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
	public static int randomPixel(final int red, final int green, final int blue,
								  final int alpha)
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

	private RandomExtensions()
	{
	}

}
