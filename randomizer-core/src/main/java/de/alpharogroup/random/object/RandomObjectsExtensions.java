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
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import de.alpharogroup.random.RandomCharacters;
import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.random.number.RandomNumberExtensions;
import de.alpharogroup.random.number.RandomPrimitivesExtensions;
import de.alpharogroup.reflection.ReflectionExtensions;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link RandomObjectsExtensions} is a utility class to create random objects.
 */
@UtilityClass
public final class RandomObjectsExtensions
{

	/**
	 * Gets an infomail address from the given url.
	 *
	 * @param url
	 *            The url.
	 * @return Returns an infomail address from the given url.
	 */
	public static String getInfomailFromWebsite(final String url)
	{
		int startIndex = url.indexOf("www.");
		final StringBuilder email = new StringBuilder();
		if (0 < startIndex)
		{
			final String emailprefix = "info";
			email.append(emailprefix);
			email.append("@");
			email.append(url.substring(startIndex + 4, url.length()));
		}
		else
		{
			if (0 == startIndex)
			{
				final String emailprefix = "info";
				email.append(emailprefix);
				email.append("@");
				email.append(url.substring(startIndex + 2, url.length()));
			}
			else
			{
				throw new IllegalArgumentException(url);
			}
		}
		return email.toString();
	}

	/**
	 * The Method getRandomEmail() gets a random email-address.
	 *
	 * @return The random email-address.
	 */
	public static String getRandomEmail()
	{
		final StringBuffer email = new StringBuffer();
		final String emailprefix = RandomExtensions.getRandomString(
			RandomCharacters.lowcaseWithNumbers.getCharacters(),
			RandomPrimitivesExtensions.randomInt(20) + 1);
		final String domain = RandomExtensions.getRandomString(
			RandomCharacters.lowcase.getCharacters(), RandomPrimitivesExtensions.randomInt(12) + 1);
		final String topDomain = RandomExtensions
			.getRandomString(RandomCharacters.lowcase.getCharacters(), 2);
		email.append(emailprefix);
		email.append("@");
		email.append(domain);
		email.append(".");
		email.append(topDomain);
		return email.toString();
	}

	/**
	 * Gets a random faxnumber from a phone.
	 *
	 * @param phonenumber
	 *            The phonenumber.
	 * @return Return's a random faxnumber from a phone.
	 */
	public static String getRandomFaxnumber(final String phonenumber)
	{
		final StringBuffer sb = new StringBuffer();
		final String randomFax = phonenumber.substring(0, phonenumber.length() - 2);
		sb.append(randomFax);
		final String phoneExtension = phonenumber.substring(phonenumber.length() - 2,
			phonenumber.length());
		final Integer phEx = new Integer(phoneExtension);
		final int pe = phEx + 1;
		sb.append(pe);
		return sb.toString();
	}

	/**
	 * Gets a random mobil number from a mobilphone.
	 *
	 * @return Return's a random mobil number from a mobilphone.
	 */
	public static String getRandomMobilnumber()
	{
		final StringBuffer randomPhonenumber = new StringBuffer();
		randomPhonenumber.append("0");
		randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(3));
		randomPhonenumber.append("/");
		randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(7));
		return randomPhonenumber.toString();
	}

	/**
	 * The Method getRandomPassword(int) produces a random password.
	 *
	 * @param length
	 *            The length from the password.
	 * @return The password.
	 */
	public static String getRandomPassword(final int length)
	{
		final String password = RandomExtensions.getRandomString(
			RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(), length);
		return password;
	}

	/**
	 * The Method getRandomPassword(int) produces a random password.
	 *
	 * @param length
	 *            The length from the password as Optional.
	 * @return The password.
	 */
	public static String getRandomPassword(final Optional<Integer> length)
	{
		if (length.isPresent())
		{
			final String password = RandomExtensions.getRandomString(
				RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(), length.get());
			return password;
		}
		return RandomExtensions
			.getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(), 8);
	}

	/**
	 * Gets a random phonenumber.
	 *
	 * @return Return's a random phonenumber.
	 */
	public static String getRandomPhonenumber()
	{
		final StringBuffer randomPhonenumber = new StringBuffer();
		randomPhonenumber.append("0");
		randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(4));
		randomPhonenumber.append("/");
		randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(7));
		return randomPhonenumber.toString();
	}

	/**
	 * Gets a random name for a website.
	 *
	 * @return Returns a random name for a website.
	 */
	public static String getRandomWebsite()
	{
		final StringBuffer website = new StringBuffer();
		final String websitePrefix = "http://www";
		final String domain = RandomExtensions.getRandomString(
			RandomCharacters.lowcase.getCharacters(), RandomPrimitivesExtensions.randomInt(12) + 1);
		final String topDomain = RandomExtensions
			.getRandomString(RandomCharacters.lowcase.getCharacters(), 2);
		website.append(websitePrefix);
		website.append(".");
		website.append(domain);
		website.append(".");
		website.append(topDomain);
		return website.toString();
	}

	/**
	 * Factory method for create a new random id and returns it
	 *
	 * @return the created random id.
	 */
	public static String newRandomId()
	{
		final StringBuffer sb = new StringBuffer();
		sb.append(RandomExtensions
			.getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(), 2));
		sb.append(".");
		sb.append(RandomExtensions
			.getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(), 4));
		sb.append(".");
		sb.append(RandomExtensions
			.getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(), 2));
		sb.append(".");
		sb.append(System.currentTimeMillis());
		sb.append(".");
		sb.append(RandomExtensions
			.getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(), 2));
		return sb.toString();
	}

	/**
	 * Factory method for create a new random name from the donated char array
	 *
	 * @param donatedChars
	 *            The Characters for the name
	 * @return A random Name.
	 */
	public static String newRandomName(final char[] donatedChars)
	{
		final StringBuffer sb = new StringBuffer(donatedChars.length);
		final List<Character> dc = new ArrayList<>(donatedChars.length);
		for (final char donatedChar : donatedChars)
		{
			dc.add(donatedChar);
		}
		boolean fullList = true;
		while (fullList)
		{
			final int randomIndex = RandomPrimitivesExtensions.randomInt(dc.size());
			final Character c = dc.get(randomIndex);
			sb.append(c);
			dc.remove(randomIndex);
			if (dc.isEmpty())
			{
				fullList = false;
			}
		}
		return sb.toString();
	}

	/**
	 * Factory method for create a new random object of the given {@link Class}
	 *
	 * @param <T>
	 *            the generic type
	 * @param cls
	 *            the class
	 * @return the new random object
	 *
	 * @throws IllegalAccessException
	 *             is thrown if the class or its default constructor is not accessible.
	 * @throws InstantiationException
	 *             is thrown if this {@code Class} represents an abstract class, an interface, an
	 *             array class, a primitive type, or void; or if the class has no default
	 *             constructor; or if the instantiation fails for some other reason.
	 * @throws NoSuchFieldException
	 *             is thrown if no such field exists
	 * @deprecated use instead the same name method in the factory class<br>
	 *             <br>
	 *             Note: will be remove in the next minor release
	 */
	@Deprecated
	public static <T> T newRandomObject(final @NonNull Class<T> cls)
		throws IllegalAccessException, InstantiationException, NoSuchFieldException
	{
		T instance = ReflectionExtensions.newInstance(cls);
		Field[] allDeclaredFields = ReflectionExtensions.getAllDeclaredFields(cls);
		for (Field field : allDeclaredFields)
		{
			if (Modifier.isFinal(field.getModifiers()))
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
	 * @deprecated use instead the same name method in the factory class<br>
	 *             <br>
	 *             Note: will be remove in the next minor release
	 */
	@Deprecated
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
			return RandomExtensions.randomInt();
		}
		else if (type.equals(Long.TYPE) || type.equals(Long.class))
		{
			return RandomExtensions.randomLong();
		}
		else if (type.equals(Double.TYPE) || type.equals(Double.class))
		{
			return RandomExtensions.randomDouble();
		}
		else if (type.equals(Float.TYPE) || type.equals(Float.class))
		{
			return RandomExtensions.randomFloat();
		}
		else if (type.equals(String.class))
		{
			return RandomExtensions.getRandomString();
		}
		else if (type.equals(BigInteger.class))
		{
			return RandomExtensions.randomSerialNumber();
		}
		return newRandomObject(type);
	}

}
