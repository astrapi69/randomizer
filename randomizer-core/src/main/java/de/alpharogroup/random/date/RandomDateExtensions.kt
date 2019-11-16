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
package de.alpharogroup.random.date

import de.alpharogroup.collections.list.ListFactory
import de.alpharogroup.date.CalculateDateExtensions
import de.alpharogroup.random.SecureRandomFactory
import de.alpharogroup.random.number.RandomPrimitivesExtensions
import java.security.SecureRandom
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.util.*

/**
 * The class [RandomDateExtensions] is a utility class for creating random random dates.
 *
 * @version 1.0
 * @author Asterios Raptis
 */
class RandomDateExtensions {
    companion object {

        /** The secure random.  */
        private var secureRandom: SecureRandom? = null

        init {
            secureRandom = SecureRandomFactory.newSecureRandom()
        }

        /**
         * Creates a random [LocalDateTime] object
         *
         * @return the zone id
         */
        fun randomZoneId(): ZoneId {
            val availableZoneIds = ListFactory.newArrayList(ZoneId.getAvailableZoneIds())
            return ZoneId.of(
                    availableZoneIds[RandomPrimitivesExtensions.randomInt(availableZoneIds.size)])
        }

        /**
         * Creates a random [LocalDateTime] object
         *
         * @return the random [LocalDateTime] object
         */
        fun randomLocalDateTime(): LocalDateTime {
            return LocalDateTime.of(randomLocalDate(), randomLocalTime())
        }

        /**
         * Creates a random [LocalTime] object
         *
         * @return the random [LocalTime] object
         */
        fun randomLocalTime(): LocalTime {
            val randomLocalTime: LocalTime
            val now = LocalTime.now()
            if (RandomPrimitivesExtensions.randomBoolean()) {
                randomLocalTime = now.plusHours(RandomPrimitivesExtensions.randomLong(23))
                        .plusMinutes(RandomPrimitivesExtensions.randomLong(59))
                        .plusSeconds(RandomPrimitivesExtensions.randomLong(59))
            } else {
                randomLocalTime = now.minusHours(RandomPrimitivesExtensions.randomLong(23))
                        .minusMinutes(RandomPrimitivesExtensions.randomLong(59))
                        .minusSeconds(RandomPrimitivesExtensions.randomLong(59))
            }
            return randomLocalTime
        }

        /**
         * Creates a random [LocalDate] object
         *
         * @return the random [LocalDate] object
         */
        fun randomLocalDate(): LocalDate {
            val randomLocalDate: LocalDate
            val now = LocalDate.now()
            if (RandomPrimitivesExtensions.randomBoolean()) {
                randomLocalDate = now.plusDays(RandomPrimitivesExtensions.randomLong())
            } else {
                randomLocalDate = now.minusDays(RandomPrimitivesExtensions.randomLong())
            }
            return randomLocalDate
        }

        /**
         * Creates a random Date that is after from the given Date.
         *
         * @param date
         * The Date from where to compute the future date.
         * @param range
         * The range.
         *
         * @return The random Date in the future.
         */
        @JvmOverloads
        fun dateAfter(date: Date, range: Int = RandomPrimitivesExtensions.randomInt(10000)): Date {
            return CalculateDateExtensions.addDays(date, RandomPrimitivesExtensions.randomInt(range))
        }

        /**
         * Creates a random date that is before from the given date.
         *
         * @param date
         * The date from where to compute the past date.
         * @param range
         * The range.
         *
         * @return The random Date in the past.
         */
        @JvmOverloads
        fun dateBefore(date: Date, range: Int = 10000): Date {
            return CalculateDateExtensions.substractDaysFromDate(date, range)
        }

        /**
         * Creates a java.sql.Timestamp from now.
         *
         * @return Timestamp.
         */
        val timestamp: Timestamp
            get() = getTimestamp(Date())

        /**
         * Creates a java.sql.Timestamp(to match the ones in the database) from the given date.
         *
         * @param date
         * The date
         *
         * @return Timestamp.
         */
        fun getTimestamp(date: Date): Timestamp {
            val gregCal = GregorianCalendar()
            gregCal.time = date
            gregCal.set(Calendar.HOUR_OF_DAY, 0)
            gregCal.set(Calendar.MINUTE, 0)
            gregCal.set(Calendar.SECOND, 0)
            gregCal.set(Calendar.MILLISECOND, 0)
            return Timestamp(gregCal.time.time)
        }

        /**
         * Creates a random birthday-date between 9 and 55 years.
         *
         * @return 's the random date.
         */
        fun randomBirthday(): Date {
            val now = Date(System.currentTimeMillis())
            // About 55 years.
            val past = dateBefore(now, 20000)
            // About 9 years.
            val recentlyPast = dateBefore(now, 3000)
            return randomBirthday(recentlyPast, past)
        }

        /**
         * Creates a random birthday-date between the two given date-objects.
         *
         * @param from
         * The date from where to start.
         * @param till
         * The date from where to end.
         * @return 's the random date.
         */
        fun randomBirthday(from: Date, till: Date): Date {
            return randomDatebetween(from, till)
        }

        /**
         * Creates a random date.
         *
         * @param from
         * The date from where to begin.
         * @return The random date.
         */
        fun randomDate(from: Date): Date {
            val randDouble = -secureRandom!!.nextDouble() * from.time
            val randomDouble = from.time - secureRandom!!.nextDouble()
            val result = randDouble / 99999 * (randomDouble / 99999)
            return Date(result.toLong())
        }

        /**
         * Creates a random [Date]
         *
         * @return The random [Date]
         */
        fun randomDate(): Date {
            val now = Date(System.currentTimeMillis())
            return if (RandomPrimitivesExtensions.randomBoolean()) {
                dateAfter(now, RandomPrimitivesExtensions.randomInt(10000))
            } else dateBefore(now, RandomPrimitivesExtensions.randomInt(10000))
        }

        /**
         * Creates a random Date between the range from start and end.
         *
         * @param start
         * The Date from where the range starts.
         * @param end
         * The Date from where the range ends.
         * @return A random Date between the range from start and end.
         */
        fun randomDatebetween(start: Date, end: Date): Date {
            val randomLong = (start.time + secureRandom!!.nextDouble() * (end.time - start.time)).toLong()
            return Date(randomLong)
        }

        /**
         * Creates a random Date between the range from startDays and endDays from the given Date and
         * gives it back as a string to the given format.
         *
         * @param startDate
         * The date from where to start as a long.
         * @param endDate
         * The date from where to end as a long.
         * @param format
         * The format for the date.
         * @return The random date as a String.
         */
        @JvmOverloads
        fun randomDatebetween(startDate: Long, endDate: Long,
                              format: String = "dd.MM.yyyy HH:mm:ss"): String {
            val sdf = SimpleDateFormat(format)
            val randomLongBetween = RandomPrimitivesExtensions.randomLongBetween(startDate, endDate)
            val between = Date(randomLongBetween)
            return sdf.format(between)
        }

        /**
         * Creates a random Date between the range from startDays and endDays from the given Date.
         *
         * @param from
         * The Date from where to the random Date to start.
         * @param startDays
         * The int that represents the days from where the range starts.
         * @param endDays
         * The int that represents the days from where the range ends.
         * @return A random Date between the range from startDays and endDays from the given Date.
         */
        fun randomDateBetween(from: Date, startDays: Int, endDays: Int): Date {
            return dateAfter(from, RandomPrimitivesExtensions.randomIntBetween(startDays, endDays))
        }
    }

}
/**
 * Creates a random Date that is after from the given Date.
 *
 * @param date
 * The Date from where to compute the future date.
 *
 * @return The random Date in the future.
 */
/**
 * Creates a random date that is before from the given date.
 *
 * @param date
 * The date from where to compute the Past date.
 *
 * @return The random Date in the past.
 */
/**
 * Creates a random Date between the range from startDays and endDays from the given Date and
 * gives it back as a string to the default "dd.MM.yyyy HH:mm:ss" format.
 *
 * @param startDate
 * The date from where to start as a long.
 * @param endDate
 * The date from where to end as a long.
 * @return The random date as a String.
 */
