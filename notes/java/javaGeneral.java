/*		Static Initializer
**
**	Executed when the class is loaded/initialized (before main)
**	Works as a "class constructor"
**
** 	Usage:	Mostly used for database connections, API inits, logging...
**	
**	NB: 	arguments, this, super, and return statements are NOT supported. 
*/
public class StaticInit {
	private static final int var;

	static {
		a = 29;
		method(a);
	}

	private static final void method(int x) {}
}



/*		Instance Initializer
**
**	Executed on object construction only
**	Called at the beginning of the constructor, after the super constructor
**	but before any subsequent constructor code is called.
**
** 	Usage:	When a class have multiple constructors and needs the same init
**			for all constuctors.
**	
**	NB: 	Avoid calling non-final methods here (just like other constructors)
*/
public class InstanceInit {
	private final int a;

	{
		a = 29;
	}

	public InstanceInit() {}
	public InstanceInit(int b) {
		if(b > a) a = b; //bad example
	}
}


/*		EnumSet
**
**	Can be used to create subranges in the enums.
**
*/
import java.util.EnumSet;
public class SubRange {
	enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, 
				FRIDAY, SATURDAY, SUNDAY}

	public static void main(String[]args) {
		//Only prints the weekdays.
		EnumSet.range(Day.MONDAY, Day.FRIDAY).forEach(System.out::println);
	}
}

/*		Multiple Enums Example
**
*/
import java.util.List;
import java.util.ArrayList;
import java.io.Serilizable;
class Card implements Comparable<Card>, Serilizable {

	public enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
		TEN, JACK, QUEEN, KINK, ACE
	}
	public enum Suit {
		CLUBS, DIAMONDS, HEARTS, SPADES
	}

	private final Rank rank;
	private final Suit suit;
	public Rank rank() { return rank; }
	public Suit suit() { return suit; }

	private Card(Rank rank, Suit suit) {
		if(rank == null || suit == null)
			throw new NullPointerException();
		this.rank = rank;
		this.suit = suit;
	}

	public int compareTo(Card c) {
		int suitCompare = suit.compareTo(c.suit);
		return (suitCompare != 0 ? suitCompare : rank.compareTo(c.rank));
	}

	private static final List<Card> cleanDeck = new ArrayList<Card>(52);
	
	static {
		for(Suit s : Suit.values())
			for(rank r : Rank.values())
				cleanDeck.add(new Card(r,s));
	}

	public static List<Card> newDeck() {
		return new ArrayList<Card>(cleanDeck);
	}
}