
// 	From Java 8
interface DefendMethod {
	
	default void message(String message) {
		message(message, null, null);
	}

	void message(String message, double d, int i);
}

//	-----------------------------------
// 		No need to implement method.
//	-----------------------------------


interface A {
	default void hello(){
		System.out.println("HELLO");
	}
}

class C implements A {
	//Does not need to implement hello();
}


//	-----------------------------------
// 		Velge hvilken defend metode hvis flere.
//	-----------------------------------

interface B {
	default void hello() {
		System.out.println("HADE");
	}
}

class MultipleHellos implements A, B {
	public MultipleHellos(){};
	public void hello() default A.hello();
}

class DefendMethods {
	public static void main(String[]args) {
		MultipleHellos mh = new MultipleHellos();
		mh.hello();
	}
}