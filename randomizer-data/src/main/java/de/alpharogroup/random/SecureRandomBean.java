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

import java.security.SecureRandom;

import lombok.NonNull;

/**
 * The class {@link SecureRandomBean} builds a {@link SecureRandom} from the given algorithm and
 * provider. If nothing is set the default {@link SecureRandom} object with the default algorithm
 * will be build.
 */
public class SecureRandomBean
{

	/** The Constant DEFAULT_ALGORITHM. */
	public static final String DEFAULT_ALGORITHM = "SHA1PRNG";
	/** The Constant DEFAULT_ALGORITHM. */
	public static final String DEFAULT_PROVIDER = "SUN";

	/**
	 * Gets an instance of {@link SecureRandomBean} for build a {@link SecureRandom} object.
	 *
	 * @return the {@link SecureRandomBean}
	 *
	 * @deprecated use the same name method of <code>SecureRandomBuilder</code> <br><br>
	 * Note: will be removed in the next minor version
	 */
	@Deprecated
	public static SecureRandomBean builder()
	{
		return new SecureRandomBean();
	}

	/** The algorithm. */
	private String algorithm;

	/** The provider. */
	private String provider;

	/**
	 * Instantiates a new {@link SecureRandomBean}.
	 */
	private SecureRandomBean()
	{
	}

	/**
	 * Sets the algorithm.
	 *
	 * @param algorithm
	 *            the algorithm
	 * @return this {@link SecureRandomBean} object. For chaining.
	 * @deprecated use the same name method of <code>SecureRandomBuilder</code> <br>
	 *             <br>
	 *             Note: will be removed in the next minor version
	 */
	@Deprecated
	public SecureRandomBean algorithm(@NonNull final String algorithm)
	{
		this.algorithm = algorithm;
		return this;
	}

	/**
	 * Builds a {@link SecureRandom} from the given algorithm and provider. If nothing is set the
	 * default {@link SecureRandom} object with the default algorithm will be build.
	 *
	 * @return the new {@link SecureRandom} object
	 * @deprecated use the same name method of <code>SecureRandomBuilder</code> <br>
	 *             <br>
	 *             Note: will be removed in the next minor version
	 */
	@Deprecated
	public SecureRandom build()
	{
		return RandomFactory.newSecureRandom(algorithm, provider);
	}

	/**
	 * Sets the provider.
	 *
	 * @param provider
	 *            the provider
	 * @return this {@link SecureRandomBean} object. For chaining.
	 * @deprecated use the same name method of <code>SecureRandomBuilder</code> <br>
	 *             <br>
	 *             Note: will be removed in the next minor version
	 */
	@Deprecated
	public SecureRandomBean provider(@NonNull final String provider)
	{
		this.provider = provider;
		return this;
	}
}
