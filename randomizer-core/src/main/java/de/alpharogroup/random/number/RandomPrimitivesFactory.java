package de.alpharogroup.random.number;

import java.security.SecureRandom;
import java.util.Objects;
import java.util.Random;

import de.alpharogroup.math.MathExtensions;
import de.alpharogroup.random.enums.RandomAlgorithm;

public final class RandomPrimitivesFactory
{
	/**
	 * Returns a random boolean.
	 * 
	 * @param secureRandom
	 *            the secure random for boolean generation
	 *
	 * @return The random boolean.
	 */
	public static boolean randomBoolean(SecureRandom secureRandom)
	{
		return randomInt(2, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom)) == 0;
	}

	/**
	 * The Method randomByte() selects a random byte.
	 * 
	 * @param secureRandom
	 *            the secure random for byte generation
	 *
	 * @return The random byte.
	 */
	public static byte randomByte(SecureRandom secureRandom)
	{
		return (byte)randomInt(255, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}


	/**
	 * The Method randomByteArray(int) generates a random byte array.
	 *
	 * @param length
	 *            the length
	 * @param secureRandom
	 *            the secure random for byte generation
	 * @return the byte[]
	 */
	public static byte[] randomByteArray(final int length, SecureRandom secureRandom)
	{
		Objects.requireNonNull(secureRandom);
		final byte[] randomByteArray = new byte[length];
		final byte[] randomByteBox = new byte[1];
		for (int i = 0; i < length; i++)
		{
			if (randomBoolean(secureRandom))
			{
				randomByteArray[i] = randomByte(secureRandom);
			}
			else
			{
				secureRandom.nextBytes(randomByteBox);
				randomByteArray[i] = randomByteBox[0];
			}
		}
		return randomByteArray;
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
	 * Gets an random double to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return an random double not greater then the range
	 */
	public static double randomDouble(final double range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return Math.abs(secureRandom.nextDouble()) % range;
			case MATH_RANDOM :
				return Math.random() * range;
			case RANDOM :
				double random = new Random(System.currentTimeMillis()).nextDouble() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return secureRandom.nextDouble() * range;
		}
	}

	/**
	 * Gets an random double to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return an random double not greater then the range
	 */
	public static double randomDouble(final double range, SecureRandom secureRandom)
	{
		return randomDouble(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * Gets an random double to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return an random double not greater then the range
	 */
	public static double randomDouble(SecureRandom secureRandom)
	{
		return RandomPrimitivesExtensions
			.randomDouble(Objects.requireNonNull(secureRandom).nextDouble());
	}

	/**
	 * Gets the random double between the range from start and end.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param secureRandom
	 *            the secure random for double generation
	 * @return the random double between
	 */
	public static double randomDoubleBetween(final double start, final double end,
		SecureRandom secureRandom)
	{
		return start + randomDouble(end - start, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

	/**
	 * Gets an random float to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for float generation
	 * @return an random float not greater then the range
	 */
	public static float randomFloat(final float range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return (float)(Math.abs(secureRandom.nextDouble()) % range);
			case MATH_RANDOM :
				return (float)(Math.random() * range);
			case RANDOM :
				float random = (float)new Random(System.currentTimeMillis()).nextDouble() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return (float)(secureRandom.nextDouble() * range);
		}
	}

	/**
	 * Gets an random float to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random float is between 0.0-9.9
	 *
	 * @param range
	 *            the range
	 * @param secureRandom
	 *            the secure random for float generation
	 * @return an random float not greater then the range
	 */
	public static float randomFloat(final float range, SecureRandom secureRandom)
	{
		return randomFloat(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * Generates a random float between the range 0.0-9.9.
	 *
	 * @param secureRandom
	 *            the secure random for float generation
	 *
	 * @return the generated random float between the range 0.0-9.9.
	 */
	public static float randomFloat(SecureRandom secureRandom)
	{
		return RandomPrimitivesExtensions
			.randomFloat(Objects.requireNonNull(secureRandom).nextFloat());
	}

	/**
	 * Gets the random float between the range from start and end.
	 *
	 * @param start
	 *            the start
	 * @param end
	 *            the end
	 * @param secureRandom
	 *            the secure random for float generation
	 * @return the random float between
	 */
	public static float randomFloatBetween(final float start, final float end,
		SecureRandom secureRandom)
	{
		return start + randomFloat(end - start, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

	/**
	 * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
	 * to 10 the random int is between 0-9.
	 *
	 * @param range
	 *            The range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for int generation
	 * @return an int not greater then the range
	 */
	public static int randomInt(final int range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return Math.abs(secureRandom.nextInt()) % range;
			case MATH_RANDOM :
				return (int)(Math.random() * range);
			case RANDOM :
				int random = new Random(System.currentTimeMillis()).nextInt() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return (int)(secureRandom.nextDouble() * range);
		}
	}

	/**
	 * The Method randomInt(int) gets an int to the spezified range. For example: if you put range
	 * to 10 the random int is between 0-9.
	 *
	 * @param range
	 *            The Range
	 * @param secureRandom
	 *            the secure random for int generation
	 * @return an int not greater then the range.
	 */
	public static long randomInt(final int range, SecureRandom secureRandom)
	{
		return randomInt(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * The Method randomInt() gets an int between the range 0-9.
	 *
	 * @param secureRandom
	 *            the secure random for int generation
	 *
	 * @return an int between the range 0-9.
	 */
	public static int randomInt(SecureRandom secureRandom)
	{
		return RandomPrimitivesExtensions.randomInt(Objects.requireNonNull(secureRandom).nextInt());
	}

	/**
	 * Returns a random int between the range from minVolume and maxVolume with the
	 * <code>Math.abs</code> method.
	 *
	 * @param minVolume
	 *            the min volume
	 * @param maxVolume
	 *            the max volume
	 * @param secureRandom
	 *            the secure random for number generation
	 * @return A random int between the range from minVolume and maxVolume
	 */
	public static int randomIntBetween(int minVolume, int maxVolume, SecureRandom secureRandom)
	{
		return minVolume + randomInt(maxVolume - minVolume, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

	/**
	 * Gets an random long to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random int is between 0-9
	 *
	 * @param range
	 *            the range
	 * @param algorithm
	 *            the random algorithm
	 * @param secureRandom
	 *            the secure random for long generation
	 * @return an random long not greater then the range
	 */
	public static long randomLong(final long range, final RandomAlgorithm algorithm,
		SecureRandom secureRandom)
	{
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(secureRandom);
		switch (algorithm)
		{
			case MATH_ABS :
				return (long)(Math.abs(secureRandom.nextDouble()) % range);
			case MATH_RANDOM :
				return (long)(Math.random() * range);
			case RANDOM :
				long random = (long)new Random(System.currentTimeMillis()).nextDouble() % range;
				return MathExtensions.isPositive(random) ? random : random * -1;
			case SECURE_RANDOM :
			default :
				return (long)(secureRandom.nextDouble() * range);
		}
	}

	/**
	 * Gets an random long to the given range with the given random algorithm <br>
	 * <br>
	 * For example: if you put range to 10 the random int is between 0-9
	 *
	 * @param range
	 *            the range
	 * @param secureRandom
	 *            the secure random for long generation
	 * @return an random long not greater then the range
	 */
	public static long randomLong(final long range, SecureRandom secureRandom)
	{
		return randomLong(range, RandomAlgorithm.SECURE_RANDOM, secureRandom);
	}

	/**
	 * Gets a random long
	 *
	 * @param secureRandom
	 *            the secure random for long generation
	 *
	 * @return a random long
	 */
	public static long randomLong(SecureRandom secureRandom)
	{
		return RandomPrimitivesExtensions
			.randomLong(Objects.requireNonNull(secureRandom).nextLong());
	}

	/**
	 * Returns a random long between the range from start and end.
	 *
	 * @param start
	 *            The long from where the range starts.
	 * @param end
	 *            The long from where the range ends.
	 * @param secureRandom
	 *            the secure random for long generation
	 * @return A random long between the range from start and end.
	 */
	public static long randomLongBetween(final long start, final long end,
		SecureRandom secureRandom)
	{
		return start + randomLong(end - start, RandomAlgorithm.SECURE_RANDOM,
			Objects.requireNonNull(secureRandom));
	}

	/**
	 * Returns a random short
	 * 
	 * @param secureRandom
	 *            the secure random for short generation
	 *
	 * @return The generated random short
	 */
	public static short randomShort(SecureRandom secureRandom)
	{
		Objects.requireNonNull(secureRandom);
		if (secureRandom.nextBoolean())
		{
			return (short)(secureRandom.nextInt(65536) - 32768);
		}
		else
		{
			return (short)secureRandom.nextInt(Short.MAX_VALUE + 1);
		}
	}

	private RandomPrimitivesFactory()
	{
	}

}
