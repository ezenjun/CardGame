import java.util.Random;

public class DeckOfCards
{
	private Card[] deck;
	private int currentCard;
	private static final int NUMBER_OF_CARDS = 52;
	private static final Random randomNumbers = new Random();
	
	public DeckOfCards()
	{
		String[] faces = { "7", "2", "3", "4", "5", "6", "1", "8", "9", "10", "11", "12", "13" };
		String[] suits = { "C", "H", "D", "S" };
		
		deck = new Card[ NUMBER_OF_CARDS ];
		currentCard = 0;
		
		for(int count = 0; count < deck.length; count++) {
			if(count%13 == 0) deck[count] = new Card(faces[count%13], suits[count/13], count+6);
			else if(count%13 == 6) deck[count] = new Card(faces[count%13], suits[count/13], count-6);
			else deck[count] = new Card(faces[count%13], suits[count/13], count);
		}
	}
	
	public void shuffle()
	{
		currentCard = 0;
		
		for(int first = 0; first < deck.length; first++)
		{
			int second = randomNumbers.nextInt( NUMBER_OF_CARDS );
			
			Card temp = deck[first];
			deck[first] = deck[second];
			deck[second] = temp;
		}
	}
	
	public Card dealCard()
	{
		if(currentCard < deck.length )
			return deck[currentCard++];
		else {
			return null;
		}
	}
}