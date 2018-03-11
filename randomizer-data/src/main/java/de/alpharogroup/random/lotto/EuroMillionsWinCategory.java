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
 * The class {@link EuroMillionsWinCategory} represents an win category for the EuroMillion lottery.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE)
public class EuroMillionsWinCategory implements Cloneable {

	/** The quantity of winning numbers. */
	int quantityOfWonNumbers;
	
	/** The quantity of winning star numbers. */
	int quantityOfWonStarNumbers;
	
	/** The flag if the played super number is selected. */
	boolean withSuperNumber;
	
	/** The payout rate of this winning category. */
	double payoutRate;
	
	/** The computation. */
	String computation;

	@Override protected Object clone() throws CloneNotSupportedException
	{
		EuroMillionsWinCategory clone = EuroMillionsWinCategory.builder()
			.computation(this.computation)
			.payoutRate(this.payoutRate)
			.withSuperNumber(this.withSuperNumber)
			.quantityOfWonNumbers(this.quantityOfWonNumbers)
			.quantityOfWonStarNumbers(this.quantityOfWonStarNumbers)
			.build();
		return clone;
	}
}
