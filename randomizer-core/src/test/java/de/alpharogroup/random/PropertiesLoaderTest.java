package de.alpharogroup.random;

import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import de.alpharogroup.lang.PackageExtensions;

/**
 * The unit test class for the class {@link PropertiesLoader}
 */
public class PropertiesLoaderTest
{

	/**
	 * Test method for {@link PropertiesLoader#loadProperties(String)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testLoadPropertiesPackagePath() throws IOException
	{
		final String propertiesFilename = "resources.properties";
		final String pathFromObject = PackageExtensions.getPackagePathWithSlash(this);
		final String path = pathFromObject + propertiesFilename;

		final Properties prop = PropertiesLoader.loadProperties(path);
		final boolean result = null != prop;
		assertTrue("", result);
	}
}
