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
package io.github.astrapi69.random;

/**
 * The enum {@link RandomCharacters}
 */
public enum RandomCharacters
{

	/** The lowcase. */
	lowcase(RandomCharacters.LOWCASECHARS),

	/** The lowcase with numbers. */
	lowcaseWithNumbers(RandomCharacters.LOWCASECHARS + RandomCharacters.NUMBERS),

	/** The lowcase with numbers and special. */
	lowcaseWithNumbersAndSpecial(
		RandomCharacters.LOWCASECHARS + RandomCharacters.NUMBERS + RandomCharacters.SPECIALCHARS),

	/** The lowcase with uppercase and numbers. */
	lowcaseWithUppercaseAndNumbers(RandomCharacters.LOWCASECHARS
		+ RandomCharacters.LOWCASECHARS.toUpperCase() + RandomCharacters.NUMBERS),

	/** The lowcase with uppercase and numbers and special. */
	lowcaseWithUppercaseAndNumbersAndSpecial(
		RandomCharacters.LOWCASECHARS + RandomCharacters.LOWCASECHARS.toUpperCase()
			+ RandomCharacters.NUMBERS + RandomCharacters.SPECIALCHARS),

	/** The numbers. */
	numbers(RandomCharacters.NUMBERS),

	/** The special. */
	special(RandomCharacters.SPECIALCHARS),

	/** The uppercase. */
	uppercase(RandomCharacters.LOWCASECHARS.toUpperCase()),

	/** The uppercase with numbers. */
	uppercaseWithNumbers(RandomCharacters.LOWCASECHARS.toUpperCase() + RandomCharacters.NUMBERS),

	/** The uppercase. */
	escapeSequences(RandomCharacters.ESCAPE_SEQUENCES),

	/** The escape sequences and white space. */
	escapeSequencesWithWhitespace(RandomCharacters.ESCAPE_SEQUENCES_CHARS),

	/** The uppercase with numbers and special. */
	uppercaseWithNumbersAndSpecial(RandomCharacters.LOWCASECHARS.toUpperCase()
		+ RandomCharacters.NUMBERS + RandomCharacters.SPECIALCHARS);

	/** The alphabet-characters in lower case. */
	public static final String LOWCASECHARS = "abcdefghijklmnopqrstuvwxyz";

	/** All digits. */
	public static final String NUMBERS = "0123456789";

	/** All special characters */
	public static final String SPECIALCHARS = "#@$%^&*?!";

	/** The white space character */
	public static final String WHITE_SPACE_CHAR = " ";

	/** The bracket characters */
	public static final String BRACKETS_CHAR = "(){}[]<>";

	/** The underscore character */
	public static final String UNDERSCORE_CHAR = "_";

	/** The slash character */
	public static final String SLASH_CHAR = "/";

	/** The plus character */
	public static final String PLUS_CHAR = "+";

	/** The minus character */
	public static final String MINUS_CHAR = "-";

	/** The plus and minus characters */
	public static final String MATH_OPERATOR_CHAR = PLUS_CHAR + MINUS_CHAR;

	/** All other special characters */
	public static final String OTHER_SPECIALCHARS = "°§=~.:,;µ|€²³^";

	/** The quotation marks characters */
	public static final String ESCAPE_QUOTATION_MARK_CHARACTERS = "\'\"";

	/** The quotation marks characters */
	public static final String QUOTATION_MARK_CHARACTERS = "´`" + ESCAPE_QUOTATION_MARK_CHARACTERS;

	/** All escape sequences characters */
	public static final String ESCAPE_SEQUENCES = "\t\b\n\r\f\\" + ESCAPE_QUOTATION_MARK_CHARACTERS;

	/** All escape sequences and white space characters */
	public static final String ESCAPE_SEQUENCES_CHARS = ESCAPE_SEQUENCES + WHITE_SPACE_CHAR;

	/** The allowed characters. */
	private final String characters;

	/**
	 * Instantiates a new {@link RandomCharacters}
	 *
	 * @param characters
	 *            the allowed characters
	 */
	RandomCharacters(String characters)
	{
		this.characters = characters;
	}

	public String getCharacters()
	{
		return characters;
	}
}
