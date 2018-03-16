package de.alpharogroup.random.lotto.neo;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LottoTicket
{
	/** The id. */
	String id;

	@Singular
	Set<LottoBox> lottoBoxes;

	GameSeventySeven gameSeventySeven;

	/** The super six number. */
	Integer superSixNumber;

	/** The super number. */
	Integer superNumber;
}
