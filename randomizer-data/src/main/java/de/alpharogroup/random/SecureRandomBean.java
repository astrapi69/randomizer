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

/**
 * The class {@link SecureRandomBean} holds the data for build a {@link SecureRandom} from the given
 * algorithm and provider.
 */
public final class SecureRandomBean
{
	public static class SecureRandomBeanBuilder
	{
		private String algorithm;
		private String provider;

		SecureRandomBeanBuilder()
		{
		}

		public SecureRandomBeanBuilder algorithm(final String algorithm)
		{
			this.algorithm = algorithm;
			return this;
		}

		public SecureRandomBean build()
		{
			return new SecureRandomBean(algorithm, provider);
		}

		public SecureRandomBeanBuilder provider(final String provider)
		{
			this.provider = provider;
			return this;
		}

		@Override
		public String toString()
		{
			return "SecureRandomBean.SecureRandomBeanBuilder(algorithm=" + this.algorithm
				+ ", provider=" + this.provider + ")";
		}
	}
	/**
	 * The Constant DEFAULT_ALGORITHM.
	 */
	public static final String DEFAULT_ALGORITHM = "SHA1PRNG";
	/**
	 * The Constant DEFAULT_ALGORITHM.
	 */
	public static final String DEFAULT_PROVIDER = "SUN";
	public static SecureRandomBeanBuilder builder()
	{
		return new SecureRandomBeanBuilder();
	}

	/**
	 * The algorithm.
	 */
	private final String algorithm;

	/**
	 * The provider.
	 */
	private final String provider;

	/**
	 * Instantiates a new {@link SecureRandomBean} object with the default values
	 */
	public SecureRandomBean()
	{
		this(DEFAULT_ALGORITHM, DEFAULT_PROVIDER);
	}

	/**
	 * Instantiates a new {@link SecureRandomBean} with the given values
	 *
	 * @param defaultAlgorithm
	 *            the default algorithm
	 * @param defaultProvider
	 *            the default provider
	 */
	public SecureRandomBean(String defaultAlgorithm, String defaultProvider)
	{
		this.algorithm = defaultAlgorithm;
		this.provider = defaultProvider;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (o == this)
		{
			return true;
		}
		if (!(o instanceof SecureRandomBean))
		{
			return false;
		}
		final SecureRandomBean other = (SecureRandomBean)o;
		final Object this$algorithm = this.getAlgorithm();
		final Object other$algorithm = other.getAlgorithm();
		if (this$algorithm == null
			? other$algorithm != null
			: !this$algorithm.equals(other$algorithm))
		{
			return false;
		}
		final Object this$provider = this.getProvider();
		final Object other$provider = other.getProvider();
		if (this$provider == null ? other$provider != null : !this$provider.equals(other$provider))
		{
			return false;
		}
		return true;
	}

	/**
	 * The algorithm.
	 *
	 * @return the algorithm
	 */
	public String getAlgorithm()
	{
		return this.algorithm;
	}

	/**
	 * The provider.
	 *
	 * @return the provider
	 */
	public String getProvider()
	{
		return this.provider;
	}

	@Override
	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object $algorithm = this.getAlgorithm();
		result = result * PRIME + ($algorithm == null ? 43 : $algorithm.hashCode());
		final Object $provider = this.getProvider();
		result = result * PRIME + ($provider == null ? 43 : $provider.hashCode());
		return result;
	}

	public SecureRandomBeanBuilder toBuilder()
	{
		return new SecureRandomBeanBuilder().algorithm(this.algorithm).provider(this.provider);
	}

	@Override
	public String toString()
	{
		return "SecureRandomBean(algorithm=" + this.getAlgorithm() + ", provider="
			+ this.getProvider() + ")";
	}
}
