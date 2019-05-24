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

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * A package private factory for creating {@link SecureRandom} objects
 */
class RandomFactory
{

	/**
	 * Factory method for create a new {@link SecureRandom} object
	 *
	 * @param algorithm the algorithm
	 * @param provider the provider
	 * @return the new {@link SecureRandom} object
	 */
	static SecureRandom newSecureRandom(final String algorithm, final String provider)
	{
		if (algorithm != null && provider != null)
		{
			try
			{
				return SecureRandom.getInstance(algorithm, provider);
			}
			catch (NoSuchAlgorithmException | NoSuchProviderException e)
			{
				throw new RuntimeException(e);
			}
		}
		if (algorithm != null)
		{
			try
			{
				return SecureRandom.getInstance(algorithm);
			}
			catch (NoSuchAlgorithmException e)
			{
				throw new RuntimeException(e);
			}
		}
		try
		{
			return SecureRandom.getInstance(SecureRandomBuilder.DEFAULT_ALGORITHM);
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
	}

}
