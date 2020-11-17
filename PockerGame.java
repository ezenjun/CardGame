import java.util.Arrays;

enum Ranking		//high ranking -> high value(integer)
{
	HighCard,
	Pair,
	TwoPair,
	Trips,
	Straight,
	Flush,
	FullHouse,
	Quads,
	StraightFlush,
	RoyalFlush
}

public class PockerGame
{
	private int bet;
	private Ranking rank;
	private int flagIndex;	//if pair -> pair card index(less index)
								//if two pair -> no pair card index
	private Integer[] grade;
	
	
	public PockerGame()
	{
		flagIndex = 0;
		rank = Ranking.HighCard;
	}
	public PockerGame(Player[] players, int num)
	{
		flagIndex = 0;
		rank = Ranking.HighCard;
		for(int i = 0; i < num; i++) calRank(players[i]);
	}
	public void sortRank(Player[] players, int num)
	{
		grade = new Integer[num];
		for(int i = 0; i < num; i++) grade[i] = i;
		
		Arrays.sort(grade, (g1, g2)
				-> (players[g1].getRank() == players[g2].getRank()) ?							//condition
						calSameRank(players[g1]) - calSameRank(players[g2]) :					//true
						players[g1].getRank().ordinal() - players[g2].getRank().ordinal());		//false
	}

	public void calRank(Player player)
	{
		Card[] cards = player.getSortedCards();
		int tempFace = 0;
		int tempSuit = 0;
		int tempPairOne = 0;
		int tempPairTwo = 0;
		int straightCount = 0;
		int flushCount = 0;
		int pairCountOne = 0;
		int pairCountTwo = 0;
		
		player.setRank(Ranking.HighCard, flagIndex);
		
		tempFace = cards[0].getNum()%13;
		tempSuit = cards[0].getNum()/13;
		
		for(int i = 1; i < 5; i++)		//check  straight & flush
		{
			if(cards[i].getNum()%13 == tempFace+1)		//check straight
			{
				tempFace++;
				straightCount++;
			}
			else if(tempFace == 0 && cards[i].getNum()%13 == 9)		//check ace and king
			{
				tempFace += 9;
				straightCount++;
			}
			if(cards[i].getNum()/13 == tempSuit) flushCount++;		//check flush
		}
		
		if(straightCount == 4 && flushCount == 4)
		{
			if(cards[0].getNum()%13 == 0) rank = Ranking.RoyalFlush;
			else rank = Ranking.StraightFlush;
		}
		else if(straightCount == 4) rank = Ranking.Straight;
		else if(flushCount == 4) rank = Ranking.Flush;
		if(rank==Ranking.RoyalFlush || rank==Ranking.StraightFlush || rank==Ranking.Straight || rank==Ranking.Flush)
		{
			player.setRank(rank, flagIndex);
			return;
		}		
		

		tempPairOne = cards[1].getNum()%13;		//2nd
		tempPairTwo = cards[3].getNum()%13;		//4th
		
		if(tempPairOne == tempPairTwo) pairCountOne = 3;	//if 2nd == 4th -> minimum trips
		else if(tempPairOne == cards[2].getNum()%13)		//else if 2nd == 3rd -> minimum pair
		{
			pairCountOne = 2;
			flagIndex = 2;
		}
		else if(tempPairTwo == cards[2].getNum()%13)		//else if 3rd == 4th -> minimum pair
		{
			pairCountTwo = 2;
			flagIndex = 3;
		}
		
		
		if (tempPairOne == cards[0].getNum()%13)		//if 1st == 2nd
		{
			if(pairCountOne != 0) pairCountOne++;		//if == 3rd -> trips or quad
			else										//if != 3rd -> pair or two_pair
			{
				pairCountOne = 2;
				if(flagIndex == 0) flagIndex = 1;		//if pair -> flag set pair_index (two_pair possible but only 1==2,4==5)
				else flagIndex = 5;						//if two_pair -> flag set no pair_index
			}
		}
		if (tempPairTwo == cards[4].getNum()%13)		//if 4th == 5th
		{
			if(pairCountTwo != 0) pairCountTwo++;		//if == 3rd -> trips or quad
			else										//if != 3rd -> pair or two_pair
			{
				pairCountTwo = 2;
				if(flagIndex == 0) flagIndex = 4;		//if pair -> flag set pair_index
				else if(flagIndex == 1) flagIndex = 3;	//if two_pair but flag already set to pair_index -> flag set no pair_index
				else flagIndex = 1;						//if two_pair -> flag set no pair_index
			}
		}

		if(pairCountOne == 2 && pairCountTwo == 3) rank = Ranking.FullHouse;
		else if(pairCountOne == 3 && pairCountTwo == 2) rank = Ranking.FullHouse;
		else if(pairCountOne == 2 && pairCountTwo == 2) rank = Ranking.TwoPair;
		else if(pairCountOne == 3) rank = Ranking.Trips;
		else if(pairCountTwo == 3) rank = Ranking.Trips;
		else if(pairCountOne == 2) rank = Ranking.Pair;
		else if(pairCountTwo == 2) rank = Ranking.Pair;
		else rank = Ranking.HighCard;
		
		player.setRank(rank, flagIndex);
		return;
	}
	
	public int calSameRank(Player player)				//return value is different structure by ranking
	{													//right is least significant bit
		Card[] sortedCards = player.getSortedCards();
		Ranking rank = player.getRank();
		int num = 0;
		
		switch (rank) {
		case RoyalFlush: case StraightFlush: case Straight:
			num = 100 * (sortedCards[4].getNum()%13);		//1. highest face
			num += sortedCards[4].getNum()/13;				//2. highest face's suit
		case Quads: case FullHouse: case Trips:
			num = sortedCards[2].getNum()%13;				//1. most face's face
		case Flush:
			for(int i = 4; i > 0; i--)					//1. high order face
			{
				num *= 100;
				num += sortedCards[i].getNum()%13;
			}
			num *= 10;
			num += sortedCards[0].getNum()%13;
			num *= 10;
			num += sortedCards[0].getNum()/13;			//2. suit
		case TwoPair:
			num = sortedCards[3].getNum()%13;			//1. pair's high face
			num *= 100;
			num += sortedCards[1].getNum()%13;			//2. pair's second  face
			num *= 100;
			num += sortedCards[flagIndex-1].getNum()%13;//3. remaining card's face
			num *= 100;
			num += sortedCards[flagIndex-1].getNum()/13;//4. remaining card's suit
		case Pair:
			num = sortedCards[flagIndex-1].getNum()%13; //1. pair's face
			for(int i = 4; i >= 0; i--)					//2. remaining card's high order face
			{
				if(i == flagIndex || i == flagIndex-1) continue;
				num *= 100;
				num += sortedCards[i].getNum()%13;
			}
			num *= 100;
			num += sortedCards[flagIndex].getNum()/13;	//3. pair's high suit
		case HighCard:
			num = 100 * (sortedCards[4].getNum()%13);	//1. highest face
			num += sortedCards[4].getNum()/13;			//2, highest suit
		}
		return num;
	}
}
