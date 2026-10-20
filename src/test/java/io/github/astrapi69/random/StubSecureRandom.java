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
package io.github.astrapi69.random;

import java.security.SecureRandom;

/**
 * A test double of {@link SecureRandom} that returns one given value from {@link #nextInt()} and
 * {@link #nextLong()}, so a property can hand a generator every value a real {@link SecureRandom}
 * may draw, the extremes included. Every other method a generator could call throws, so a generator
 * that draws something else fails loudly instead of drawing from the operating system unnoticed.
 */
public final class StubSecureRandom extends SecureRandom
{

	private static final long serialVersionUID = 1L;

	private final int nextInt;

	private final long nextLong;

	private StubSecureRandom(int nextInt, long nextLong)
	{
		this.nextInt = nextInt;
		this.nextLong = nextLong;
	}

	/**
	 * Factory method for a {@link SecureRandom} whose {@link #nextInt()} returns the given value
	 *
	 * @param value
	 *            the value
	 * @return the stub
	 */
	public static StubSecureRandom nextIntReturning(int value)
	{
		return new StubSecureRandom(value, 0L);
	}

	/**
	 * Factory method for a {@link SecureRandom} whose {@link #nextLong()} returns the given value
	 *
	 * @param value
	 *            the value
	 * @return the stub
	 */
	public static StubSecureRandom nextLongReturning(long value)
	{
		return new StubSecureRandom(0, value);
	}

	@Override
	public int nextInt()
	{
		return nextInt;
	}

	@Override
	public long nextLong()
	{
		return nextLong;
	}

	@Override
	public int nextInt(int bound)
	{
		throw new UnsupportedOperationException("nextInt(int)");
	}

	@Override
	public double nextDouble()
	{
		throw new UnsupportedOperationException("nextDouble()");
	}

	@Override
	public float nextFloat()
	{
		throw new UnsupportedOperationException("nextFloat()");
	}

	@Override
	public boolean nextBoolean()
	{
		throw new UnsupportedOperationException("nextBoolean()");
	}

	@Override
	public void nextBytes(byte[] bytes)
	{
		throw new UnsupportedOperationException("nextBytes(byte[])");
	}
}
