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

import static io.github.astrapi69.random.Draws.draw;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import io.github.astrapi69.random.Draws;
import io.github.astrapi69.random.Seeds;
import io.github.astrapi69.random.enumeration.RandomAlgorithm;
import io.github.astrapi69.test.object.enumeration.Gender;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;

/**
 * Properties of {@link RandomObjectFactory}. Its methods draw from the library's default generator,
 * which no seed reaches; every property that needs several values names the probability that a
 * correct generator fails it.
 */
public class RandomObjectFactoryProperties
{

	/**
	 * {@link RandomObjectFactory#randomListEntry(List)} and
	 * {@link RandomObjectFactory#randomIndex(java.util.Collection)} draw every entry and index of a
	 * list of at most 5 entries and nothing else (a correct generator misses one with a probability
	 * below 5 * (4/5)^200)
	 *
	 * @param list
	 *            the list
	 */
	@Property(seed = Seeds.SEED)
	void randomListEntryAndIndexReachEveryEntry(
		@ForAll @Size(min = 1, max = 5) @UniqueElements List<String> list)
	{
		assertEquals(new HashSet<>(list),
			new HashSet<>(draw(() -> RandomObjectFactory.randomListEntry(list))));
		Set<Integer> indexes = new HashSet<>();
		for (int i = 0; i < list.size(); i++)
		{
			indexes.add(i);
		}
		assertEquals(indexes, new HashSet<>(draw(() -> RandomObjectFactory.randomIndex(list))));
	}

	/**
	 * {@link RandomObjectFactory#randomKey(Map)} draws every key and
	 * {@link RandomObjectFactory#randomMapEntry(Map)} every value of a map of at most 5 entries
	 *
	 * @param keys
	 *            the keys of the map
	 */
	@Property(seed = Seeds.SEED)
	void randomKeyAndMapEntryReachEveryEntry(
		@ForAll @Size(min = 1, max = 5) @UniqueElements List<Integer> keys)
	{
		Map<Integer, String> map = new HashMap<>();
		keys.forEach(key -> map.put(key, "value of " + key));
		assertEquals(map.keySet(), new HashSet<>(draw(() -> RandomObjectFactory.randomKey(map))));
		assertEquals(new HashSet<>(map.values()),
			new HashSet<>(draw(() -> RandomObjectFactory.randomMapEntry(map))));
	}

	/**
	 * The random enum methods draw every constant of the enum and nothing else
	 */
	@Property(seed = Seeds.SEED, tries = 1)
	void randomEnumsReachEveryConstant()
	{
		Set<Gender> genders = Set.of(Gender.values());
		assertEquals(genders,
			new HashSet<>(draw(() -> RandomObjectFactory.randomEnumFromClass(Gender.class))));
		assertEquals(genders, new HashSet<>(
			draw(() -> RandomObjectFactory.randomEnumFromEnumValues(Gender.values()))));
		assertEquals(genders,
			new HashSet<>(draw(() -> RandomObjectFactory.randomEnumFromObject(Gender.FEMALE))));
		assertEquals(genders, new HashSet<>(draw(
			() -> RandomObjectFactory.<Gender> randomEnumFromClassname(Gender.class.getName()))));
		assertEquals(Set.of(RandomAlgorithm.values()),
			new HashSet<>(draw(RandomObjectFactory::newRandomAlgorithm)));
	}

	/**
	 * {@link RandomObjectFactory#newRandomByteObjects(int)} has the length and no null element
	 *
	 * @param length
	 *            the length
	 */
	@Property(seed = Seeds.SEED)
	void newRandomByteObjectsHasTheLengthAndNoNull(@ForAll @IntRange(max = 64) int length)
	{
		Byte[] bytes = RandomObjectFactory.newRandomByteObjects(length);
		assertEquals(length, bytes.length);
		Arrays.stream(bytes).forEach(b -> assertNotNull(b));
	}

	/**
	 * {@link RandomObjectFactory#newRandomFloat(int, int)} stays below 10^beforeComma and varies (a
	 * correct generator draws one value 200 times with a probability below 100^-199)
	 *
	 * @param afterComma
	 *            the number of digits after the decimal point
	 * @param beforeComma
	 *            the number of digits before the decimal point
	 */
	@Property(seed = Seeds.SEED)
	void newRandomFloatHasTheDigitsBeforeTheCommaAndVaries(
		@ForAll @IntRange(min = 1, max = 6) int afterComma,
		@ForAll @IntRange(min = 1, max = 6) int beforeComma)
	{
		List<Float> values = draw(
			() -> RandomObjectFactory.newRandomFloat(afterComma, beforeComma));
		values.forEach(value -> assertTrue(0 <= value && value <= Math.pow(10, beforeComma),
			() -> value + " has more than " + beforeComma + " digits before the comma"));
		assertTrue(1 < new HashSet<>(values).size());
	}

	/**
	 * {@link RandomObjectFactory#randomPixel(int, int, int, int)} packs the four components into
	 * the bytes of an ARGB int: alpha in the highest, blue in the lowest
	 */
	@Property(seed = Seeds.SEED)
	void randomPixelPacksTheComponents(@ForAll @IntRange(max = 255) int red,
		@ForAll @IntRange(max = 255) int green, @ForAll @IntRange(max = 255) int blue,
		@ForAll @IntRange(max = 255) int alpha)
	{
		int pixel = RandomObjectFactory.randomPixel(red, green, blue, alpha);
		assertEquals(alpha, pixel >>> 24);
		assertEquals(red, (pixel >>> 16) & 0xFF);
		assertEquals(green, (pixel >>> 8) & 0xFF);
		assertEquals(blue, pixel & 0xFF);
	}

	/**
	 * {@link RandomObjectFactory#randomPixel()} varies: 200 draws from 2^32 pixels hold at least
	 * 150 distinct values
	 */
	@Property(seed = Seeds.SEED, tries = 1)
	void randomPixelVaries()
	{
		assertTrue(150 <= new HashSet<>(draw(RandomObjectFactory::randomPixel)).size());
	}

	/**
	 * {@link RandomObjectFactory#randomNeighborPoint(Point, boolean)} draws the eight neighbours of
	 * the point and nothing else; without negative values a coordinate that would turn negative
	 * stays 0. 800 draws reach each of the eight with a probability above 1 - 8 * (7/8)^800
	 *
	 * @param x
	 *            the x coordinate of the point
	 * @param y
	 *            the y coordinate of the point
	 * @param withNegativeValues
	 *            whether negative coordinates are allowed
	 */
	@Property(seed = Seeds.SEED)
	void randomNeighborPointDrawsTheEightNeighbours(
		@ForAll @IntRange(min = -1000, max = 1000) int x,
		@ForAll @IntRange(min = -1000, max = 1000) int y, @ForAll boolean withNegativeValues)
	{
		Assume.that(withNegativeValues || (0 <= x && 0 <= y));
		Point from = new Point(x, y);
		int left = withNegativeValues || 0 < x ? x - 1 : x;
		int top = withNegativeValues || 0 < y ? y - 1 : y;
		Set<Point> expected = new HashSet<>();
		for (int neighbourX : new int[] { left, x, x + 1 })
		{
			for (int neighbourY : new int[] { top, y, y + 1 })
			{
				if (neighbourX != x || neighbourY != y)
				{
					expected.add(new Point(neighbourX, neighbourY));
				}
			}
		}
		if (left == x || top == y)
		{
			// a clamped coordinate makes the point a neighbour of itself
			expected.add(from);
		}
		Set<Point> drawn = new HashSet<>(Draws
			.draw(() -> RandomObjectFactory.randomNeighborPoint(from, withNegativeValues), 800));
		assertEquals(expected, drawn);
		if (!withNegativeValues)
		{
			assertEquals(expected, new HashSet<>(
				Draws.draw(() -> RandomObjectFactory.randomNeighborPoint(from), 800)));
		}
	}

	/**
	 * {@link RandomObjectFactory#randomToken()} is a number of at most 130 bits in base 32 and does
	 * not repeat
	 */
	@Property(seed = Seeds.SEED, tries = 1)
	void randomTokenIsA130BitNumberInBase32()
	{
		List<String> tokens = draw(RandomObjectFactory::randomToken);
		for (String token : tokens)
		{
			assertTrue(token.matches("[0-9a-v]{1,26}"), token);
			assertTrue(new BigInteger(token, 32).bitLength() <= 130, token);
		}
		assertEquals(tokens.size(), new HashSet<>(tokens).size());
	}

	/**
	 * {@link RandomObjectFactory#randomSalt(int, java.nio.charset.Charset)} has the length and
	 * consists of letters and digits; {@link RandomObjectFactory#newSalt()} has 16 bytes and does
	 * not repeat
	 *
	 * @param length
	 *            the length
	 */
	@Property(seed = Seeds.SEED)
	void saltsHaveTheirLength(@ForAll @IntRange(max = 64) int length)
	{
		byte[] salt = RandomObjectFactory.randomSalt(length, StandardCharsets.UTF_8);
		assertEquals(length, salt.length);
		assertTrue(new String(salt, StandardCharsets.UTF_8).matches("[a-zA-Z0-9]*"));
		List<byte[]> newSalts = draw(RandomObjectFactory::newSalt);
		newSalts.forEach(newSalt -> assertEquals(16, newSalt.length));
		assertEquals(newSalts.size(), newSalts.stream().map(Arrays::toString).distinct().count());
	}

	/**
	 * {@link RandomObjectFactory#randomUUID()} is a random (version 4) UUID and does not repeat
	 */
	@Property(seed = Seeds.SEED, tries = 1)
	void randomUuidIsAVersion4Uuid()
	{
		List<UUID> uuids = draw(RandomObjectFactory::randomUUID);
		uuids.forEach(uuid -> assertEquals(4, uuid.version()));
		assertEquals(uuids.size(), new HashSet<>(uuids).size());
	}
}
