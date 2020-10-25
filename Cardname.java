import java.util.Arrays;

public class Cardname implements Comparable<Cardname> {
	private int value;
	private char suit;

	public Cardname(String str) {
		char val = str.charAt(0);
		switch (val) {
		case 'T':
			this.value = 10;
			break;
		case 'J':
			this.value = 11;
			break;
		case 'Q':
			this.value = 12;
			break;
		case 'K':
			this.value = 13;
			break;
		case 'A':
			this.value = 14;
			break;
		default:
			this.value = Integer.parseInt("" + val);
			break;
		}

		this.suit = str.charAt(1);

	}

	

	public String toString() {
		String str = "";
		str = String.valueOf(this.value) + this.suit;
		return str;
	}

	public int getValue() {
		return this.value;
	}

	public char getSuit() {
		return this.suit;
	}

	public int compareTo(Cardname compareCard) {

		int compareValue = ((Cardname) compareCard).getValue();
		return this.value - compareValue;

	}

}


