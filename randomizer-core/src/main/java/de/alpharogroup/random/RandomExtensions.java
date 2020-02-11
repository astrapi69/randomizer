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
import de.alpharogroup.random.number.RandomPrimitivesExtensions;

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
	public static <T> T getRandomEntry(final List<T> list)
	{
		return list.get(getRandomIndex(list));
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
	public static <K, V> Object getRandomEntry(final Map<K, V> map)
	{
		final Object[] entries = map.values().toArray();
		return entries[RandomPrimitivesExtensions.randomInt(entries.length)];
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
	public static <T extends Enum<?>> T getRandomEnumFromClass(final Class<T> clazz)
	{
		return getRandomEnumFromEnumValues(clazz.getEnumConstants());
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
	public static <T extends Enum<?>> T getRandomEnumFromClassname(final String classname)
	{
		if (classname != null && !classname.isEmpty())
		{
			Class<T> enumClass = null;
			try
			{
				enumClass = (Class<T>)ClassExtensions.forName(classname);
				return getRandomEnumFromClass(enumClass);
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
	public static <T extends Enum<?>> T getRandomEnumFromEnumValues(final T[] values)
	{
		return values[RandomPrimitivesExtensions.randomInt(values.length)];
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
	public static <T extends Enum<?>> T getRandomEnumFromObject(final T obj)
	{
		if (obj != null)
		{
			final Class<T> clazz = (Class<T>)obj.getClass();
			return getRandomEnumFromClass(clazz);
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
	public static String getRandomHexString(int numberOfCharacters)
	{
		StringBuilder sb = new StringBuilder();
		while (sb.length() < numberOfCharacters)
		{
			sb.append(Integer.toHexString(RandomPrimitivesExtensions.randomInt()));
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
	public static <T> int getRandomIndex(final Collection<T> list)
	{
		return RandomPrimitivesExtensions.randomInt(list.size());
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
	public static <K, V> Object getRandomKey(final Map<K, V> map)
	{
		final Set<K> keySet = map.keySet();
		final Object[] keys = keySet.toArray();
		return keys[RandomPrimitivesExtensions.randomInt(keys.length)];
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
	public static byte[] getRandomSalt(final int length, final Charset charset)
	{
		return RandomExtensions
			.getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(),
				length)
			.getBytes(charset);
	}

	/**
	 * Generates a random string with a length between 3 and 25
	 *
	 * @return The produced random String.
	 */
	public static String getRandomString()
	{
		return getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(),
			RandomPrimitivesExtensions.randomIntBetween(3, 25));
	}

	/**
	 * Generates a random string.
	 *
	 * @param length
	 *            the specified length.
	 * @return the generated random string.
	 */
	public static String getRandomString(final int length)
	{
		final int maxLength = Math.min(length, 1024);
		final StringBuilder sb = new StringBuilder(maxLength);
		for (int i = 0; i < maxLength; i++)
		{
			sb.append(RandomPrimitivesExtensions.randomChar());
		}
		return sb.toString();
	}

	/**
	 * Generates a random string with a length between the given start and end
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @return the generated random string
	 */
	public static String getRandomString(final int start, int end)
	{
		return getRandomString(RandomPrimitivesExtensions.randomIntBetween(start, end));
	}

	/**
	 * The Method randomString(String, int) makes an random String from the given String and to the
	 * spezified length. This can be used to produce passwords.
	 *
	 * @param chars
	 *            The String to get the random chars.
	 * @param length
	 *            The length from the random String.
	 * @return The produced random String.
	 */
	public static String getRandomString(final String chars, final int length)
	{
		final StringBuffer ergebnis = new StringBuffer();
		for (int i = 0; i < length; i++)
		{
			ergebnis.append(RandomPrimitivesExtensions.randomChar(chars));
		}
		return ergebnis.toString();
	}

	/**
	 * The Method randomString(String []) a random String from the Array For example: The
	 * Stringarray test as argument. Possible values: "blab", "flih", "klap", "teta", "brut",
	 * "gzft", "ccp". Possible selection can be one value from the Stringarray like "blab" or
	 * "klap".
	 *
	 * @param array
	 *            The array with the String to be selected.
	 * @return The selected String from the array.
	 */
	public static String getRandomString(final String[] array)
	{
		return array[RandomPrimitivesExtensions.randomInt(array.length)];
	}

	/**
	 * Generates a random int for use with pixel.
	 *
	 * @return a random int for use with pixel.
	 */
	public static int newRandomPixel()
	{
		return newRandomPixel(RandomPrimitivesExtensions.randomInt(256),
			RandomPrimitivesExtensions.randomInt(256), RandomPrimitivesExtensions.randomInt(256),
			RandomPrimitivesExtensions.randomInt(256));
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
	public static int newRandomPixel(final int red, final int green, final int blue,
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
		return RandomPrimitivesExtensions.randomByteArray(16);
	}

	/**
	 * Returns a random serial number that can be used for a serial number.
	 *
	 * @return a random serial number as a {@link BigInteger} object.
	 */
	public static BigInteger randomSerialNumber()
	{
		long next = DefaultSecureRandom.get().nextLong();
		if (next < 0)
		{
			next = next * (-1);
		}
		final BigInteger serialNumber = BigInteger.valueOf(next);
		return serialNumber;
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
