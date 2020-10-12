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
package de.alpharogroup.random.object;

import de.alpharogroup.random.RandomCharacters;
import de.alpharogroup.random.number.RandomCharFactory;
import de.alpharogroup.random.number.RandomIntFactory;

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
                RandomIntFactory.randomIntBetween(3, 25));
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
            sb.append(RandomCharFactory.randomChar());
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
        return newRandomString(RandomIntFactory.randomIntBetween(start, end));
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
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
        {
            sb.append(RandomCharFactory.randomChar(chars));
        }
        return sb.toString();
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
        return array[RandomIntFactory.randomInt(array.length)];
    }


    /**
     * Generates a random hexadecimal {@link String}
     *
     * @param numberOfCharacters
     *            the number of characters
     * @return the generated random hexadecimal {@link String}
     */
    public static String randomHexString(int numberOfCharacters)
    {
        StringBuilder sb = new StringBuilder();
        while (sb.length() < numberOfCharacters)
        {
            sb.append(Integer.toHexString(RandomIntFactory.randomInt()));
        }
        return sb.toString().substring(0, numberOfCharacters);
    }
}
