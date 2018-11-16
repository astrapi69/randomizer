package de.alpharogroup.random;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public enum RandomCharacters
{

	/** The special. */
	special(RandomCharacters.SPECIALCHARS),

	/** The numbers. */
	numbers(RandomCharacters.NUMBERS),

	/** The lowcase. */
	lowcase(RandomCharacters.LOWCASECHARS),

	/** The uppercase. */
	uppercase(RandomCharacters.LOWCASECHARS.toUpperCase()),

	/** The lowcase with numbers. */
	lowcaseWithNumbers(RandomCharacters.LOWCASECHARS + RandomCharacters.NUMBERS),

	/** The uppercase with numbers. */
	uppercaseWithNumbers(RandomCharacters.LOWCASECHARS.toUpperCase() + RandomCharacters.NUMBERS),

	/** The lowcase with numbers and special. */
	lowcaseWithNumbersAndSpecial(
		RandomCharacters.LOWCASECHARS + RandomCharacters.NUMBERS + RandomCharacters.SPECIALCHARS),

	/** The uppercase with numbers and special. */
	uppercaseWithNumbersAndSpecial(RandomCharacters.LOWCASECHARS.toUpperCase()
		+ RandomCharacters.NUMBERS + RandomCharacters.SPECIALCHARS),

	/** The lowcase with uppercase and numbers. */
	lowcaseWithUppercaseAndNumbers(RandomCharacters.LOWCASECHARS
		+ RandomCharacters.LOWCASECHARS.toUpperCase() + RandomCharacters.NUMBERS),

	/** The lowcase with uppercase and numbers and special. */
	lowcaseWithUppercaseAndNumbersAndSpecial(
		RandomCharacters.LOWCASECHARS + RandomCharacters.LOWCASECHARS.toUpperCase()
			+ RandomCharacters.NUMBERS + RandomCharacters.SPECIALCHARS);

	/** All special Chars */
	private static final String SPECIALCHARS = "#@$%^&*?!";

	/** All digits. */
	private static final String NUMBERS = "0123456789";

	/** The alphabet-chars in lower case. */
	private static final String LOWCASECHARS = "abcdefghijklmnopqrstuvwxyz";


	@Getter
	String allowedCharacters;

	private RandomCharacters(String allowedCharacters)
	{
		this.allowedCharacters = allowedCharacters;
	}

}
