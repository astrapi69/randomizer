package de.alpharogroup.random.lotto.neo;

import de.alpharogroup.random.lotto.neo.LottoBox;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

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
