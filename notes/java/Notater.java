import java.util.*;
import java.awt.Graphics;
import java.math.BigDecimal;

class Notater {
	//Init
	public static void main(String[]args) {
		System.out.println(
			//Testing
		);
		JavaEight.optionalObject();
	}

}

class Tricks {

	//	Multi-stage ternary operator
	public static Integer multiStageTernary(int a){
		Integer foo = a < 10 ? 10
					: a < 15 ? 15
					: a < 20 ? 20
					: 25;
		return foo;
	}

	//	String.format()
	public static String stringFormat() {
		int score = 86;
		int max = 120;
		double percent = score*100/max;
		return	String.format("We scored %d out of %d which is %.2f%%",score, max, percent);
	}

	//	Double-curly braces for simple object instantiation
	public static void curlyObjectInit() {
		new HashMap<String, String>() {{
			put("1", "a");
			put("2", "b");
		}};
	}

	//Inner Class
	class Inner {
		String hello() {
			return "HELLO";
		}
	}

	//Named loops
	public static void namedLoops() {
		foo:
		for(int i=0; i<10; i++) 
			for(int j=0; j<10; j++)
				if(i+j==18)
					break foo;
					// continue foo;
	}

	
	/*		The Void Object
	*	This Object can be used when you extend an abstract method
	*	with using generics while you dont want to return anything.
	*/
	abstract class Action<T> {
		abstract T execute();
	}
	class VoidAction extends Action<Void> {
		Void execute() {
			return (null);
		}
	}

	// May also be used to testing return-type with reflection.
	// (This will behave exactly ass void.class, but is cleaner in my opinion)
	public void someMethod() {}
	public void testReflection(){
		if(getClass().getMethod("someMethod").getReturnType() == Void.TYPE){
			//Figure shit out.
		}
	}




	//Static methods can also be generic
	public static <T> T test(T t){
		return t;
	}

	//Variable lenght parameters
	public static void variableLenghtParams(String...strings) {}

	//Define, init and call all at once
	public static void defInitCall() {
		new Object() {
			void hi(String in){
				System.out.println(in);
			}
		}.hi("Hi");
	}

	//Complex for loops
	public static void complexLoops() {
		//For loops can have more than one variable, condition and action
		for(int i=0, j=100; i<j && j>0; i++, j-=3) {}
		//Also can be empty
		int var = 0;
		for(;var<10; var++) {}
	}

	// Anti aliasing on paint method for smoother graphics
	public void paint(Graphics g) {
//		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//...
	}


}



class JavaEight {

	// For each on lists
	public static void foreachLambda() {
		List<String> list = Arrays.asList("alpha", "beta", "delta");
		list.forEach(System.out::println);
	}

	// 	Functional programming with Filter Map Reduce
	// 		[Sum 10% discount prices of prices > 20]
	public static String filterMapReduce() {
		final List<BigDecimal> priceList = Arrays.asList(
                new BigDecimal( "10" ), new BigDecimal( "30" ),
                new BigDecimal( "17" ), new BigDecimal( "20" ),
                new BigDecimal( "15" ), new BigDecimal( "18" ),
                new BigDecimal( "45" ), new BigDecimal( "12" )
        );
        final BigDecimal sumOfDiscountedPrices =
        		priceList.stream()
        		.filter( price -> price.compareTo(BigDecimal.valueOf(20))>0)
        		.map( price -> price.multiply( BigDecimal.valueOf(0.9)))
        		.reduce( BigDecimal.ZERO, BigDecimal::add);
        return sumOfDiscountedPrices.toString();
	}

	// Optional object to help with NullPointerExceptions
	public static void optionalObject() {
		String var = null;
		Optional<String> name = Optional.ofNullable(var);

		//Methods
		boolean isSet = name.isPresent();
		String orElseGet = name.orElseGet( () -> "nothing");
		String map = name.map( s -> "Hey "+s+"!").orElse("Hey Stranger!");

		System.out.println(map);

		//Does not need to be initialized.
		System.out.println(Optional.ofNullable(null).orElseGet( () -> "test"));
	}
}

class BestPractice {

	//	Using StringBuilder instead of "+"
	//		+ [Better performance]
	//		- [Not safe for multiple threads]
	public static String stringBuilder() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dette går ");
		builder.append("raskere");

		return builder.toString();
	}

	//	String value call
	// 		+ ["Null safe"]
	public static void stringValueCall() {
		String test = null;

		// kaster NullpointerException
		test.toString();

		// Ikke NullpointerException
		String.valueOf(test);
	}


	// Methods that are not to be overridden should be set to final
	public static final void nothing() {};

	// Always return empty Collections and Arrays instead of null
	private String name = null;
	public String getName() {
		return (null==name ? "" : name);
	}

	// Use JComponent instead of JPanel as base for a new graphical component.

}
