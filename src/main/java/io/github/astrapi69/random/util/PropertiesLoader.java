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
package io.github.astrapi69.random.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import io.github.astrapi69.lang.ClassExtensions;

/**
 * The class {@link PropertiesLoader} provide a method for load a {@link Properties} object from a
 * given path
 */
public final class PropertiesLoader
{
	private PropertiesLoader()
	{
	}

	/**
	 * Gives a Properties-object from the given packagepath.
	 *
	 * @param packagePath
	 *            The package-path and the name from the resource as a String.
	 * @return The Properties-object from the given packagepath.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Properties loadProperties(final String packagePath) throws IOException
	{
		Properties properties = null;
		final URL url = ClassExtensions.getResource(packagePath);
		if (url != null)
		{
			properties = new Properties();
			properties.load(url.openStream());
		}
		else
		{
			final InputStream is = ClassExtensions.getResourceAsStream(packagePath);
			if (is != null)
			{
				properties = new Properties();
				properties.load(is);
			}
		}
		return properties;
	}

}
