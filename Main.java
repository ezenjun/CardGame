import java.util.Scanner;


public class Main
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		int num;
		String[] names = new String[5];
		DeckOfCards deck = new DeckOfCards();
//		deck.shuffle();
		Card[][] hands = new Card[5][5];
		Rules rank = new Rules();
		int[] ranks = new int[5];
		
//		System.out.print("how many player?: ");
//		num = in.nextInt();
		
//		for(int i = 0; i < num; i++)
//		{
//			System.out.printf("%dth, name?: ", i+1);
//			names[i] = in.next();
//		}
		
		num =  5;
		for(int i = 0; i < num; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				hands[i][j] = deck.dealCard();
			}
		}
		
		for(int i = 0; i < num; i++)
		{
			System.out.printf("%dth hands:", i+1);
			for(int j = 0; j < 5; j++)
			{
				System.out.printf(" %s%s", hands[i][j].getFace(), hands[i][j].getSuit());
			}
			System.out.println("");
		}
		System.out.println("");
		
		for(int i = 0; i < num; i++)
		{
//			ranks[i] = rank.cardSort(hands[i]);
			ranks[i] = rank.getRank(hands[i]);
		}
		
		for(int i = 0; i < num; i++)
		{
			System.out.printf("%dth hands:", i+1);
			for(int j = 0; j < 5; j++)
			{
				System.out.printf(" %s%s", hands[i][j].getFace(), hands[i][j].getSuit());
			}
			System.out.printf(" %d\n", ranks[i]);
		}
		System.out.println("");
	}
}
