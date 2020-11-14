import java.util.Arrays;
import java.util.Comparator;

public class Rules
{
	private Rank rank = Rank.HighCard;
	
	enum Rank
	{
		RoyalFlush,
		StraightFlush,
		Quads,
		FullHouse,
		Flush,
		Straight,
		Trips,
		TwoPair,
		Pair,
		HighCard
	}

	int getRank(Card[] cards)
	{
		int tempFace = 0;
		int tempPairOne = 0;
		int tempPairTwo = 0;
		int tempSuit = 0;
		int straightCount = 0;
		int flushCount = 0;
		int pairCountOne = 0;
		int pairCountTwo = 0;
		
		cardSort(cards);
		rank = Rank.HighCard;
		
		tempFace = cards[0].getNum()%13;
		tempSuit = cards[0].getNum()/13;
		
		for(int i = 1; i < 5; i++)
		{
			if(cards[i].getNum()%13 == tempFace+1)
			{
				tempFace++;
				straightCount++;
			}
			else if(cards[i].getNum()%13 == tempFace+9)
			{
				tempFace += 9;
				straightCount++;
			}
			if(cards[i].getNum()/13 == tempSuit) flushCount++;
		}
		
		if(straightCount == 4 && flushCount == 4) rank = Rank.StraightFlush;
		else if(straightCount == 4) rank = Rank.Straight;
		else if(flushCount == 4) rank = Rank.Flush;
		if(rank==Rank.RoyalFlush || rank==Rank.StraightFlush || rank==Rank.Straight || rank==Rank.Flush)
			return rank.ordinal();
		
		tempPairOne = cards[1].getNum()%13;		//b
		tempPairTwo = cards[3].getNum()%13;		//d
		
		if(tempPairOne == tempPairTwo) pairCountOne = 3;
		
		if(tempPairOne == cards[2].getNum()%13) pairCountOne = 2;
		else if(tempPairTwo == cards[2].getNum()%13) pairCountTwo = 2;
		
		if (tempPairOne == cards[0].getNum()%13)
		{
			if(pairCountOne != 0) pairCountOne++;
			else pairCountOne = 2;
			
		}
		if (tempPairTwo == cards[4].getNum()%13)
		{
			if(pairCountTwo != 0) pairCountTwo++;
			else pairCountTwo = 2;
		}
		
		if(pairCountOne == 2 && pairCountTwo == 3) rank = Rank.FullHouse;
		else if(pairCountOne == 3 && pairCountTwo == 2) rank = Rank.FullHouse;
		else if(pairCountOne == 2 && pairCountTwo == 2) rank = Rank.TwoPair;
		else if(pairCountOne == 3) rank = Rank.Trips;
		else if(pairCountTwo == 3) rank = Rank.Trips;
		else if(pairCountOne == 2) rank = Rank.Pair;
		else if(pairCountTwo == 2) rank = Rank.Pair;
		else rank = Rank.HighCard;
		
		
		
		return rank.ordinal();
	}
	
	Card[] cardSort(Card[] cards)
	{
		Arrays.sort(cards, new Comparator<Card>() {
			@Override
			public int compare(Card c1, Card c2) {
				if(c1.getNum()%13 == c2.getNum()%13)
				{
					return c1.getNum()/13 - c2.getNum()/13;
				}
				return c1.getNum()%13 - c2.getNum()%13;
			}
		});
		return cards;
	}
}
