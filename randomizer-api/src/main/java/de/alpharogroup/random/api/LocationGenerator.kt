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
package de.alpharogroup.random.api

/**
 * The interface [LocationGenerator] for generate location data.
 */
interface LocationGenerator {

    /**
     * New address comment.
     *
     * @return the string
     */
    fun newAddressComment(): String

    /**
     * New city.
     *
     * @return the string
     */
    fun newCity(): String

    /**
     * New country.
     *
     * @return the string
     */
    fun newCountry(): String

    /**
     * New latitude.
     *
     * @return the string
     */
    fun newLatitude(): String

    /**
     * New longitude.
     *
     * @return the string
     */
    fun newLongitude(): String

    /**
     * New state.
     *
     * @return the string
     */
    fun newState(): String

    /**
     * New street name.
     *
     * @return the string
     */
    fun newStreetName(): String

    /**
     * New street number.
     *
     * @return the string
     */
    fun newStreetNumber(): String

    /**
     * New zip code.
     *
     * @return the string
     */
    fun newZipCode(): String
}
