import java.util.Arrays;

public class Hand {
	public Cardname[] cards;
	public Integer handVal;
	public rank category;

	public void sortCards(){
		Arrays.sort(this.cards);
	}
	public Hand(Cardname[] cards) {
		this.cards = cards;
	}

	public String toString() {
		String str = "";
		for (Cardname card : this.cards) {
			str += card.toString() + " ";
		}
		if(str.length() > 0)
			str+= "(" + this.getrank().getDesc() + ")";
		return str;
	}
		public Hand(String[] strArr) {
		if (strArr.length != 5) {
			System.out.println("hand format is wrong");
		} else {
			Cardname[] cards = new Cardname[5];
			for (int i = 0; i < 5; i++) {
				cards[i] = new Cardname(strArr[i]);
			}
			this.cards = cards;
		}
	}
	public Cardname getCard(int index) {
		if (index >= 5) {
			return null;
		}
		return cards[index];
	}


	public rank getrank() {
		return this.category;
	}

	public Integer getHandValue() {
		return this.handVal; 


	}

	public void evaluate(){

        if(this.allSameSuit() != -1 && this.straight() != -1)
	{
           
 if(this.getCard(0).getValue() == 10){
                this.category = rank.ROYAL_FLUSH;
                this.handVal = 9999;
                return;
            }
	else{
                this.category = rank.STRAIGHT_FLUSH;
                return;
            }
        }

        if(this.four() != -1){
            this.category = rank.FOUR_OF_A_KIND;
            return;
        }

        if(this.fullHouse() != -1){
            this.category = rank.FULL_HOUSE;
            return;
        }

        if(this.allSameSuit() != -1){
            this.category = rank.FLUSH;
            return;
        }

        if(this.straight() != -1){
            this.category = rank.STRAIGHT;
            return;
        }       

        if(this.three() != -1){
            this.category =rank.THREE_OF_A_KIND;
            return;
        }

        if(this.twoPairs() != -1){
            this.category = rank.TWO_PAIRS;
            return;
        }

        if(this.pair() != -1){
            this.category = rank.ONE_PAIR;
            return;
        }

        this.handVal = this.getCard(4).getValue();
        this.category = rank.HIGH_CARD;
    }

    private int pair(){
        int prev = this.cards[4].getValue();
        int total = 0, nOfCards = 1;

        for(int i = 3; i >= 0; i--){
            if(this.cards[i].getValue() == prev){
                total += this.cards[i].getValue();
                nOfCards++;             
            }

            if(nOfCards == 2){
                break;
            }
            prev = this.cards[i].getValue();
        }

        if(nOfCards == 2){
            this.handVal = total;
            return total;
        }
        return -1;
    }

    private int twoPairs(){
        int prev = this.cards[4].getValue();
        int i = 3, total = 0, nOfCards = 1;

        for(; i >= 0; i--){
            if(this.cards[i].getValue() == prev){
                total += this.cards[i].getValue();
                nOfCards++;             
            }

            if(nOfCards == 2){
                
                break;
            }else{
                total = 0;
                nOfCards = 1;   
            }
            prev = this.cards[i].getValue();
        }
        
        if(nOfCards == 2 && i > 0){
            nOfCards = 1;
            prev = this.cards[i-1].getValue();
            for(i = i-2; i >= 0; i--){
                if(this.cards[i].getValue() == prev){
                    total += this.cards[i].getValue();
                    nOfCards++;             
                }
                if(nOfCards == 2){
                    break;
                }else{
                    total = 0;
                    nOfCards = 1;   
                }
                prev = this.cards[i].getValue();
            }
        }else{
            return -1;
        }

        if(nOfCards == 2){
            this.handVal = total;
            return total;
        }
        return -1;
    }

    private int three(){
        int prev = this.cards[4].getValue();
        int total = 0, nOfCards = 1;

        for(int i = 3; i >= 0; i--){
            if(this.cards[i].getValue() == prev){
                total += this.cards[i].getValue();
                nOfCards++;             
            }else{
                total = 0;
                nOfCards = 1;
            }           

            prev = this.cards[i].getValue();
        }

        if(nOfCards == 3){
            this.handVal = total;
            return total;
        }
        return -1;
    }

    private int fullHouse(){
       
        boolean changed = false;
        int prev = this.cards[4].getValue();
        int total = 0, nOfCards = 1;

        for(int i = 3; i >= 0; i--){
            if(this.cards[i].getValue() == prev){
                total += this.cards[i].getValue();
                nOfCards++;             
            
            }else if(changed == false){
                changed = true;
                if(nOfCards < 2){
                    this.handVal = -1;
                    return -1;
                }

                if(nOfCards == 3)
                    this.handVal = total;

            }else{
                this.handVal = -1;
                return -1;
            }
            prev = this.cards[i].getValue();
        }
      
        this.handVal = total;
        return total;
        
    }

    private int four(){
        
        int prev = this.cards[4].getValue();
        int total = 0, nOfCards = 1;

        
        for(int i = 3; i >= 0 && nOfCards < 4; i--){
            if(this.cards[i].getValue() == prev){
                total += this.cards[i].getValue();
                nOfCards++;             
            }else{
                total = 0;
                nOfCards = 1;
            }   

            prev = this.cards[i].getValue();
        }

        if(nOfCards == 4){
            this.handVal = total;
            return total;
        }
        return -1;
    }

    private int allSameSuit(){
        
        char prev = this.cards[0].getSuit();
        int total = this.cards[0].getValue();

        for(int i = 1; i < 5; i++){
            if(this.cards[i].getSuit() != prev){
                return -1;
            }
            total += this.cards[i].getValue();
            prev = this.cards[i].getSuit();
        }
        this.handVal = total;
        return total;
    }

    private int straight(){
        
        int prev = this.cards[0].getValue();
        int total = prev;
        for(int i = 1; i < 5; i++){
            if(this.cards[i].getValue() != prev + 1){
                return -1;
            }
            prev = this.cards[i].getValue();
            total += 1;
        }
        this.handVal = total;
        return total;
    }
}