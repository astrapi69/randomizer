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
package de.alpharogroup.random.lotto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EuroMillionsWinCategory implements Cloneable
{

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

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		EuroMillionsWinCategory clone = EuroMillionsWinCategory.builder()
			.computation(this.computation).payoutRate(this.payoutRate)
			.withSuperNumber(this.withSuperNumber).quantityOfWonNumbers(this.quantityOfWonNumbers)
			.quantityOfWonStarNumbers(this.quantityOfWonStarNumbers).build();
		return clone;
	}
}
