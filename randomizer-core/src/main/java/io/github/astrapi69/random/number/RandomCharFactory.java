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
package io.github.astrapi69.random.number;

import io.github.astrapi69.random.DefaultSecureRandom;

import java.security.SecureRandom;
import java.util.Objects;

/**
 * Utility class for producing random primitive char types
 *
 * @version 1.1
 * @author Asterios Raptis
 */
public final class RandomCharFactory
{

	/**
	 * Returns a random char.
	 *
	 * @return The generated random char.
	 */
	public static char randomChar()
	{
		return randomChar(DefaultSecureRandom.get());
	}

	/**
	 * Returns a random char.
	 *
	 * @param secureRandom
	 *            the secure random for char generation
	 *
	 * @return The generated random char.
	 */
	public static char randomChar(SecureRandom secureRandom)
	{
		Objects.requireNonNull(secureRandom);
		if (secureRandom.nextBoolean())
		{
			// random character
			return (char)(secureRandom.nextInt(26) + 65);
		}
		else
		{
			// random digit
			return (char)secureRandom.nextInt(10);
		}
	}

	/**
	 * The Method randomChar(String) selects a random char from the given String.
	 *
	 * @param string
	 *            The String from who to select the char.
	 * @return The selected char.
	 */
	public static char randomChar(final String string)
	{
		Objects.requireNonNull(string);
		return string.charAt(RandomIntFactory.randomInt(string.length()));
	}

	private RandomCharFactory()
	{
	}

}
