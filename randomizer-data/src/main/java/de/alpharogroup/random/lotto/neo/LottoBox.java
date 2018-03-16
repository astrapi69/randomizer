package de.alpharogroup.random.lotto.neo;

import java.util.Set;

import de.alpharogroup.random.lotto.LottoGameType;
import de.alpharogroup.random.lotto.LottoWinCategory;
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
public class LottoBox
{
	Integer index;
	LottoGameType gameType;
	@Singular
	Set<Integer> selectedNumbers;
	LottoWinCategory winCategory;
}
