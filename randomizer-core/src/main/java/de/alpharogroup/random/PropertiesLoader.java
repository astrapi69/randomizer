package de.alpharogroup.random;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import de.alpharogroup.lang.ClassExtensions;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertiesLoader {

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
