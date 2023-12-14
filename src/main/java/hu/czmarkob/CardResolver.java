package hu.czmarkob;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CardResolver {

	private final Path inputPath;

	public void resolve() {
		try {
			int seq = 1;
			List<String> lines = Files.readAllLines(inputPath);
			Map<Integer, Card> cards = new HashMap<>();
			for (String line : lines) {
				String[] parts = line.split(": ");
				String[] lists = parts[1].split(" \\| ");

				String[] winningNumberStrings = lists[0].split(" ");
				List<Integer> winningNumbers = parseNumberStringArray(winningNumberStrings);

				String[] numberStrings = lists[1].split(" ");
				List<Integer> numbers = parseNumberStringArray(numberStrings);

				cards.put(seq, new Card(winningNumbers, numbers));
				seq++;
			}

			int sum = 0;
			for (int i = 1; i <= lines.size(); ++i) {
				sum += cards.get(i).getValue();

				for (int k = 0; k < cards.get(i).getNumberOfCopies(); ++k) {
					for (int j = i + 1; j < i + 1 + cards.get(i).getNumberOfMatches(); ++j) {
						Card originalCard = cards.get(j);
						if (originalCard != null) {
							originalCard.setNumberOfCopies(originalCard.getNumberOfCopies() + 1);
						} else {
							break;
						}
					}
				}
			}

			int numberOfCopies = 0;
			for (int i = 1; i <= lines.size(); ++i) {
				numberOfCopies += cards.get(i).getNumberOfCopies();
			}

			System.out.println(sum);
			System.out.println(numberOfCopies);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private List<Integer> parseNumberStringArray(String[] numberStrings) {
		List<Integer> numbers = new ArrayList<>();
		for ( String number : numberStrings ) {
			Integer num = getNumber(number);
			if ( num != null ) {
				numbers.add(num);
			}
		}
		return numbers;
	}

	private Integer getNumber(String numberString) {
		try {
			return Integer.parseInt(numberString);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
