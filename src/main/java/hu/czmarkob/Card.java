package hu.czmarkob;

import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class Card {

	private final List<Integer> winningNumbers;

	private final List<Integer> numbers;

	@Getter
	@Setter
	private int numberOfCopies = 1;

	public Integer getValue() {
		int value = 0;
		for (Integer number : numbers) {
			if (winningNumbers.contains(number))  {
				value = value == 0 ? 1 : value * 2;
			}
		}
		return value;
	}

	public Integer getNumberOfMatches() {
		int numberOfMatches = 0;
		for (Integer number : numbers) {
			if (winningNumbers.contains(number))  {
				numberOfMatches += 1;
			}
		}
		return numberOfMatches;
	}
}
