package de.alpharogroup.random.lotto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * The class {@link WinCategory} represents an win category for a lottery like lotto.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class WinCategory implements Cloneable {

	/** The quantity of winning numbers. */
	int quantityOfWonNumbers;
	
	/** The flag if the played super number is selected. */
	boolean withSuperNumber;
	
	@Override protected Object clone() throws CloneNotSupportedException
	{
		WinCategory clone = WinCategory.builder()
			.withSuperNumber(this.withSuperNumber)
			.quantityOfWonNumbers(this.quantityOfWonNumbers)
			.build();
		return clone;
	}
}
