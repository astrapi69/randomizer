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
package de.alpharogroup.random.`object`

import de.alpharogroup.random.RandomCharacters
import de.alpharogroup.random.RandomExtensions
import de.alpharogroup.random.number.RandomNumberExtensions
import de.alpharogroup.random.number.RandomPrimitivesExtensions
import java.util.*

/**
 * The class [RandomObjectsExtensions] is a utility class to create random objects.
 */
class RandomObjectsExtensions {
    companion object {

        /**
         * Gets an infomail address from the given url.
         *
         * @param url
         * The url.
         * @return Returns an infomail address from the given url.
         */
        fun getInfomailFromWebsite(url: String): String {
            val startIndex = url.indexOf("www.")
            val email = StringBuilder()
            if (0 < startIndex) {
                val emailprefix = "info"
                email.append(emailprefix)
                email.append("@")
                email.append(url.substring(startIndex + 4, url.length))
            } else {
                if (0 == startIndex) {
                    val emailprefix = "info"
                    email.append(emailprefix)
                    email.append("@")
                    email.append(url.substring(startIndex + 2, url.length))
                } else {
                    throw IllegalArgumentException(url)
                }
            }
            return email.toString()
        }

        /**
         * The Method getRandomEmail() gets a random email-address.
         *
         * @return The random email-address.
         */
        val randomEmail: String
            get() {
                val email = StringBuffer()
                val emailprefix = RandomExtensions.getRandomString(
                        RandomCharacters.lowcaseWithNumbers.characters,
                        RandomPrimitivesExtensions.randomInt(20) + 1)
                val domain = RandomExtensions.getRandomString(
                        RandomCharacters.lowcase.characters, RandomPrimitivesExtensions.randomInt(12) + 1)
                val topDomain = RandomExtensions
                        .getRandomString(RandomCharacters.lowcase.characters, 2)
                email.append(emailprefix)
                email.append("@")
                email.append(domain)
                email.append(".")
                email.append(topDomain)
                return email.toString()
            }

        /**
         * Gets a random faxnumber from a phone.
         *
         * @param phonenumber
         * The phonenumber.
         * @return Return's a random faxnumber from a phone.
         */
        fun getRandomFaxnumber(phonenumber: String): String {
            val sb = StringBuffer()
            val randomFax = phonenumber.substring(0, phonenumber.length - 2)
            sb.append(randomFax)
            val phoneExtension = phonenumber.substring(phonenumber.length - 2,
                    phonenumber.length)
            val pe = phoneExtension + 1
            sb.append(pe)
            return sb.toString()
        }

        /**
         * Gets a random mobil number from a mobilphone.
         *
         * @return Return's a random mobil number from a mobilphone.
         */
        val randomMobilnumber: String
            get() {
                val randomPhonenumber = StringBuffer()
                randomPhonenumber.append("0")
                randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(3))
                randomPhonenumber.append("/")
                randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(7))
                return randomPhonenumber.toString()
            }

        /**
         * The Method getRandomPassword(int) produces a random password.
         *
         * @param length
         * The length from the password.
         * @return The password.
         */
        fun getRandomPassword(length: Int): String {
            return RandomExtensions.getRandomString(
                    RandomCharacters.lowcaseWithUppercaseAndNumbers.characters, length)
        }

        /**
         * The Method getRandomPassword(int) produces a random password.
         *
         * @param length
         * The length from the password as Optional.
         * @return The password.
         */
        fun getRandomPassword(length: Optional<Int>): String {
            return if (length.isPresent) {
                RandomExtensions.getRandomString(
                        RandomCharacters.lowcaseWithUppercaseAndNumbers.characters, length.get())
            } else RandomExtensions
                    .getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.characters, 8)
        }

        /**
         * Gets a random phonenumber.
         *
         * @return Return's a random phonenumber.
         */
        val randomPhonenumber: String
            get() {
                val randomPhonenumber = StringBuffer()
                randomPhonenumber.append("0")
                randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(4))
                randomPhonenumber.append("/")
                randomPhonenumber.append(RandomNumberExtensions.getRandomNumericString(7))
                return randomPhonenumber.toString()
            }

        /**
         * Gets a random name for a website.
         *
         * @return Returns a random name for a website.
         */
        val randomWebsite: String
            get() {
                val website = StringBuffer()
                val websitePrefix = "http://www"
                val domain = RandomExtensions.getRandomString(
                        RandomCharacters.lowcase.characters, RandomPrimitivesExtensions.randomInt(12) + 1)
                val topDomain = RandomExtensions
                        .getRandomString(RandomCharacters.lowcase.characters, 2)
                website.append(websitePrefix)
                website.append(".")
                website.append(domain)
                website.append(".")
                website.append(topDomain)
                return website.toString()
            }

        /**
         * Factory method for create a new random id and returns it
         *
         * @return the created random id.
         */
        fun newRandomId(): String {
            val sb = StringBuffer()
            sb.append(RandomExtensions
                    .getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.characters, 2))
            sb.append(".")
            sb.append(RandomExtensions
                    .getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.characters, 4))
            sb.append(".")
            sb.append(RandomExtensions
                    .getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.characters, 2))
            sb.append(".")
            sb.append(System.currentTimeMillis())
            sb.append(".")
            sb.append(RandomExtensions
                    .getRandomString(RandomCharacters.lowcaseWithUppercaseAndNumbers.characters, 2))
            return sb.toString()
        }

        /**
         * Factory method for create a new random name from the donated char array
         *
         * @param donatedChars
         * The Characters for the name
         * @return A random Name.
         */
        fun newRandomName(donatedChars: CharArray): String {
            val sb = StringBuffer(donatedChars.size)
            val dc = ArrayList<Char>(donatedChars.size)
            for (donatedChar in donatedChars) {
                dc.add(donatedChar)
            }
            var fullList = true
            while (fullList) {
                val randomIndex = RandomPrimitivesExtensions.randomInt(dc.size)
                val c = dc[randomIndex]
                sb.append(c)
                dc.removeAt(randomIndex)
                if (dc.isEmpty()) {
                    fullList = false
                }
            }
            return sb.toString()
        }
    }

}
