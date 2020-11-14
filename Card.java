public class Card
{
	private String face;
	private String suit;
	private int num;
	
	public Card(String cardFace, String cardSuit, int cardNum)
	{
		face = cardFace;
		suit = cardSuit;
		num = cardNum;
	}
	
	public String toString()
	{
		return suit+face;
	}
	
	public String getFace()
	{
		return face;
	}
	
	public String getSuit()
	{
		return suit;
	}
	
	public int getNum()
	{
		return num;
	}
}