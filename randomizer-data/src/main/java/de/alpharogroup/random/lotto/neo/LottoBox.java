package de.alpharogroup.random.lotto.neo;

import de.alpharogroup.random.lotto.LottoGameType;
import de.alpharogroup.random.lotto.LottoWinCategory;
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
public class LottoBox
{
	Integer index;
	LottoGameType gameType;
	@Singular
	Set<Integer> selectedNumbers;
	LottoWinCategory winCategory;
}
