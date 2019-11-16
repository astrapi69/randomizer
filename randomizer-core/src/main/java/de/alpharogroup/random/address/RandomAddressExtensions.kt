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
package de.alpharogroup.random.address

import de.alpharogroup.random.number.RandomPrimitivesExtensions
import de.alpharogroup.random.util.PropertiesLoader
import java.io.IOException
import java.util.*

/**
 * The class [RandomAddressExtensions] is a utility class to create random addresses.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomAddressExtensions {
    companion object {

        /** Resource for get german streets.  */
        val PROP_FILE_STREETS = "/resources/germanstreets.properties"

        /** Resource for get german zipcodes and the cities from it.  */
        val PROP_FILE_ZIP_CITIES = "/resources/de_zip_city.properties"

        /**
         * Returns a random german street.
         *
         * @param properties
         * The properties file with the streets.
         *
         * @return A random german street.
         * @throws IOException
         * Signals that an I/O exception has occurred.
         */
        @Throws(IOException::class)
        fun getRandomStreet(properties: Properties?): String {
            val p = properties ?: PropertiesLoader.loadProperties(PROP_FILE_STREETS)
            val size = p!!.size
            val keys = p.keys.toTypedArray()
            return p[keys[RandomPrimitivesExtensions.randomInt(size)]] as String
        }

        /**
         * Gets a random german street with a random number.
         *
         * @param properties
         * The properties file with the streets.
         *
         * @return Returns a random german street with a random number.
         * @throws IOException
         * Signals that an I/O exception has occurred.
         */
        @Throws(IOException::class)
        fun getRandomStreetWithNumber(properties: Properties): String {
            val street = getRandomStreet(properties)
            return street + " " + RandomPrimitivesExtensions.randomInt(200)
        }

        /**
         * Gets a random zip from the Properties.
         *
         * @param p
         * The Properties.
         * @return Returns a random zip.
         */
        fun getRandomZip(p: Properties): String {
            val keyset = p.keys
            val keys = keyset.toTypedArray()
            val randomIndex = RandomPrimitivesExtensions.randomInt(keys.size)
            return keys[randomIndex] as String
        }
    }

}