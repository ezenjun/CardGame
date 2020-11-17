import java.util.Arrays;

public class Player
{
	private String name;
	private Card[] cards;
	private Card[] sortedCards;
	private Ranking rank;
	private int rankFlagIndex;
	private int money;
	
	public Player()
	{
	}
	public Player(String name)
	{
		this.name = name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public void setCards(Card[] cards)
	{
		this.cards = cards;
	}
	public Card[] getCards()
	{
		return cards;
	}
	public Card[] getSortedCards()
	{
		return sortedCards;
	}
	public void sortCards()
	{
		sortedCards = cards.clone();
		Arrays.sort(sortedCards, (c1, c2)
				-> (c1.getFace().equals(c2.getFace())) ?		//condition
						c1.getNum()/13 - c2.getNum()/13 : 		//true
						c1.getNum()%13 - c2.getNum()%13);		//false
	}
	public void setRank(Ranking rank, int flagIndex)
	{
		this.rank = rank;
		rankFlagIndex = flagIndex;
	}
	public Ranking getRank()
	{
		return rank;
	}
	public int getFlagIndex()
	{
		return rankFlagIndex;
	}
	
}
