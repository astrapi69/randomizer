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
package io.github.astrapi69.random.address;

import static io.github.astrapi69.random.Draws.draw;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.junit.jupiter.api.Test;

import io.github.astrapi69.random.util.PropertiesLoader;

/**
 * Tests of the street and zip lists that {@link RandomAddressExtensions} draws from
 */
public class AddressListsTest
{

	/**
	 * Every street of the bundled street list is a name: the list is what
	 * {@link RandomAddressExtensions#getRandomStreet(Properties)} draws from without properties of
	 * its own
	 *
	 * @throws IOException
	 *             if the list cannot be read
	 */
	@Test
	public void everyStreetOfTheBundledListIsAName() throws IOException
	{
		Properties streets = PropertiesLoader
			.loadProperties(RandomAddressExtensions.PROP_FILE_STREETS);
		streets.forEach((key, street) -> assertFalse(((String)street).isBlank(),
			() -> "the street " + key + " is blank"));
	}

	/**
	 * Every city of the bundled zip list is a name
	 *
	 * @throws IOException
	 *             if the list cannot be read
	 */
	@Test
	public void everyCityOfTheBundledZipListIsAName() throws IOException
	{
		Properties cities = PropertiesLoader
			.loadProperties(RandomAddressExtensions.PROP_FILE_ZIP_CITIES);
		cities.forEach((zip, city) -> assertFalse(((String)city).isBlank(),
			() -> "the city of " + zip + " is blank"));
	}

	private static Properties streetsAndZips()
	{
		Properties properties = new Properties();
		properties.setProperty("10115", "Invalidenstr.");
		properties.setProperty("80331", "Marienplatz");
		properties.setProperty("20095", "Jungfernstieg");
		return properties;
	}

	/**
	 * {@link RandomAddressExtensions#getRandomStreet(Properties)} draws every street of the given
	 * properties and nothing else (a correct generator misses one of three with a probability below
	 * 3 * (2/3)^200), and without properties a street of the bundled list
	 *
	 * @throws IOException
	 *             if the bundled list cannot be read
	 */
	@Test
	public void randomStreetComesFromTheGivenStreets() throws IOException
	{
		Properties streets = streetsAndZips();
		Set<Object> expected = new HashSet<>(streets.values());
		assertEquals(expected, new HashSet<>(draw(() -> {
			try
			{
				return RandomAddressExtensions.getRandomStreet(streets);
			}
			catch (IOException e)
			{
				throw new IllegalStateException(e);
			}
		})));
		Properties bundled = PropertiesLoader
			.loadProperties(RandomAddressExtensions.PROP_FILE_STREETS);
		assertTrue(bundled.containsValue(RandomAddressExtensions.getRandomStreet(null)));
	}

	/**
	 * {@link RandomAddressExtensions#getRandomStreetWithNumber(Properties)} is a street of the
	 * given properties, a blank and a number below 200;
	 * {@link RandomAddressExtensions#getRandomZip(Properties)} draws every key
	 *
	 * @throws IOException
	 *             never, the properties are given
	 */
	@Test
	public void randomStreetWithNumberAndZipComeFromTheGivenProperties() throws IOException
	{
		Properties properties = streetsAndZips();
		for (int i = 0; i < 50; i++)
		{
			String streetWithNumber = RandomAddressExtensions.getRandomStreetWithNumber(properties);
			int blank = streetWithNumber.lastIndexOf(' ');
			assertTrue(properties.containsValue(streetWithNumber.substring(0, blank)),
				streetWithNumber);
			int number = Integer.parseInt(streetWithNumber.substring(blank + 1));
			assertTrue(0 <= number && number < 200, streetWithNumber);
		}
		assertEquals(properties.stringPropertyNames(),
			new HashSet<>(draw(() -> RandomAddressExtensions.getRandomZip(properties))));
	}
}
