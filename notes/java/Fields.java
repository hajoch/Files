public class Fields {

	// Instance variable
	int i = 1;

	/*		Static
	**	When a field is static there exists only one of it,
	**	no matter how many instances of the class is created.
	*/
	static int s = 2;

	/*		Final
	**	A final field must be assigned within the constructor
	*/
	final int f = 3;

	/*		Transient
	**	Variables may be marked transient to indicate that they are
	**	not part of the persistent state of an object.
	**	If Fields was to be to persistent this variable would not be saved.
	*/
	transient int t;

	/*		Volatile
	**	When threads have shared variables, volatile ensure that the
	**	variable is consistent. Instead of manually enforcing a mutual exclusion
	**	lock for these variables, volatile may be used.
	**	Decalaring a field volatile JMM ensures that all threads see a
	**	consistent value for the variable.
	**
	**	NB: final variables can not be volatile
	*/
	static volatile int v = 0;

	//	An alternative would be to make the methods using the vaiable
	//	synchronized, which prevents the methods to execute concurrently
	static synchronized void increase() { v++; }
	static synchronized void reduce() { v--; }


}