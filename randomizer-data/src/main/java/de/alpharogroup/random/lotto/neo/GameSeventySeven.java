package de.alpharogroup.random.lotto.neo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameSeventySeven
{
	/** The game seventy seven number. */
	Integer number;

}
