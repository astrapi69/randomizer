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
 * The interface [HumanDataGenerator] for generate random names, gender, jobs and other human
 * data.
 */
interface HumanDataGenerator {

    /**
     * New family name.
     *
     * @param female
     * the female
     * @return the string
     */
    fun newFamilyName(female: Boolean): String

    /**
     * New first name.
     *
     * @param female
     * the female
     * @return the string
     */
    fun newFirstName(female: Boolean): String

    /**
     * New gender.
     *
     * @return the string
     */
    fun newGender(): String

    /**
     * New job name.
     *
     * @return the string
     */
    fun newJobName(): String

    /**
     * New language.
     *
     * @return the string
     */
    fun newLanguage(): String

    /**
     * New nickname.
     *
     * @return the string
     */
    fun newNickname(): String

    /**
     * New title.
     *
     * @return the string
     */
    fun newTitle(): String

    /**
     * New username.
     *
     * @return the string
     */
    fun newUsername(): String
}
