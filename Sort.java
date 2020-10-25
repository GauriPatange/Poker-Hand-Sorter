
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sort {
	public static final String TIE_BREAK = "Oops! It is a tie";
	
		public static void main(String[] args) {
		int winnerplayer1 = 0;
		int winnerplayer2 = 0;

		BufferedReader br = null;

		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Let's Play! Please Enter Cards for player 1 and player 2");
			
				while (true) {
				

				String input = br.readLine();

				if (input == null) {
					break;
				}
				
				
				if(!input.matches("(?:[2-9TJQKA][SCHD] ){9}[2-9TJQKA][SCHD]")){
					//System.out.println("Wrong input format.");
					break;
				}
				String[] cards = input.split(" ");
				String[] playeronehand = Arrays.copyOfRange(cards, 0, 5);
				String[] playertwohand = Arrays.copyOfRange(cards, 5, 10);

				Hand handOne = new Hand(playeronehand);
				Hand handTwo = new Hand(playertwohand);

				handOne.sortCards();
				handTwo.sortCards();
				

				handOne.evaluate();
				handTwo.evaluate();


				int a = winner(handOne, handTwo);
				
				if (a == 1) {
					winnerplayer1++;
				} else if (a == 2) {
					winnerplayer2++;
				} else {
					System.out.println(TIE_BREAK);
				}
			}
			System.out.println("---Game Result----");
			System.out.println("Player 1: " + winnerplayer1 + " hands");
			System.out.println("Player 2: " + winnerplayer2 + " hands");

			System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static int winner(Hand hand1, Hand hand2) {
		

		if (hand1.getrank().getValue() > hand2.getrank().getValue()) {
			return 1;
		} else if (hand1.getrank().getValue() < hand2.getrank().getValue()) {
			return 2;
		} else if (hand1.getHandValue() > hand2.getHandValue()) {
			return 1;
		} else if (hand1.getHandValue() < hand2.getHandValue()) {
			return 2;
		} else {
			
			for (int i = 4; i >= 0; i--) {
				if (hand1.getCard(i).getValue() > hand2.getCard(i).getValue()) {
					return 1;
				} else if (hand1.getCard(i).getValue() < hand2.getCard(i).getValue()) {
					return 2;
				}
			}
			
			return -1;
		}

	}
}




