package db.diary;

public class Dog {
	String name = "ello";
	private static Dog instance;
	
	private Dog() {
		
	}
	
	public static Dog getInstance() {
		if(instance == null)
		instance = new Dog();
		return instance;
	}
}
