package de.alpharogroup.random.object;

import de.alpharogroup.random.RandomCharacters;
import de.alpharogroup.random.number.RandomPrimitivesExtensions;

public final class RandomStringFactory {
    private RandomStringFactory(){}

    /**
     * Generates a random string with a length between 3 and 25
     *
     * @return The produced random String.
     */
    public static String newRandomString()
    {
        return newRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.getCharacters(),
                RandomPrimitivesExtensions.randomIntBetween(3, 25));
    }

    /**
     * Generates a random string.
     *
     * @param length
     *            the specified length.
     * @return the generated random string.
     */
    public static String newRandomString(final int length)
    {
        final int maxLength = Math.min(length, 1024);
        final StringBuilder sb = new StringBuilder(maxLength);
        for (int i = 0; i < maxLength; i++)
        {
            sb.append(RandomPrimitivesExtensions.randomChar());
        }
        return sb.toString();
    }

    /**
     * Generates a random string with a length between the given start and end
     *
     * @param start
     *            the start
     * @param end
     *            the end
     * @return the generated random string
     */
    public static String newRandomString(final int start, int end)
    {
        return newRandomString(RandomPrimitivesExtensions.randomIntBetween(start, end));
    }

    /**
     * The Method randomString(String, int) makes an random String from the given String and to the
     * spezified length. This can be used to produce passwords.
     *
     * @param chars
     *            The String to get the random chars.
     * @param length
     *            The length from the random String.
     * @return The produced random String.
     */
    public static String newRandomString(final String chars, final int length)
    {
        final StringBuffer ergebnis = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            ergebnis.append(RandomPrimitivesExtensions.randomChar(chars));
        }
        return ergebnis.toString();
    }

    /**
     * The Method randomString(String []) a random String from the Array For example: The
     * Stringarray test as argument. Possible values: "blab", "flih", "klap", "teta", "brut",
     * "gzft", "ccp". Possible selection can be one value from the Stringarray like "blab" or
     * "klap".
     *
     * @param array
     *            The array with the String to be selected.
     * @return The selected String from the array.
     */
    public static String newRandomString(final String[] array)
    {
        return array[RandomPrimitivesExtensions.randomInt(array.length)];
    }
}
