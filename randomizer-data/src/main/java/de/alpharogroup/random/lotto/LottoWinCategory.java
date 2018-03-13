package de.alpharogroup.random.lotto;

import lombok.Getter;

public enum LottoWinCategory {

	FIRST_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(6)
			.withSuperNumber(true)
			.build()),
	SECOND_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(6)
			.withSuperNumber(false)
			.build()),
	THIRD_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(5)
			.withSuperNumber(true)
			.build()),
	FOURTH_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(5)
			.withSuperNumber(false)
			.build()),
	FIFTH_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(4)
			.withSuperNumber(true)
			.build()),
	SIXTH_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(4)
			.withSuperNumber(false)
			.build()),
	SEVENTH_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(3)
			.withSuperNumber(true)
			.build()),
	EIGHTH_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(3)
			.withSuperNumber(false)
			.build()),
	NINTH_CLASS(WinCategory.builder()
			.quantityOfWonNumbers(2)
			.withSuperNumber(true)
			.build()),
	;
	@Getter
	private final WinCategory winCategory;

	LottoWinCategory(final WinCategory winCategory) {
		this.winCategory = winCategory;
	}
}
