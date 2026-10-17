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
package io.github.astrapi69.random.object;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;

/**
 * A field of every type {@link RandomObjectFactory#newRandomValue(java.lang.reflect.Field)} knows,
 * as a primitive and as its wrapper, plus a final field and a field of a class it does not know.
 * The test-object library has no such class.
 */
public class AllFieldTypes
{

	/** The value of {@link #unchanged}; not a compile-time constant, so reads are not inlined */
	static final String UNCHANGED = String.valueOf("unchanged");

	byte primitiveByte;
	Byte byteObject;
	char primitiveChar;
	Character characterObject;
	short primitiveShort;
	Short shortObject;
	boolean primitiveBoolean;
	Boolean booleanObject;
	int primitiveInt;
	Integer integerObject;
	long primitiveLong;
	Long longObject;
	double primitiveDouble;
	Double doubleObject;
	float primitiveFloat;
	Float floatObject;
	String string;
	BigInteger bigInteger;
	BigDecimal bigDecimal;
	Date date;
	LocalDateTime localDateTime;
	LocalDate localDate;
	LocalTime localTime;
	Gender gender;
	Void nothing;
	Person person;
	final String unchanged = UNCHANGED;
}
