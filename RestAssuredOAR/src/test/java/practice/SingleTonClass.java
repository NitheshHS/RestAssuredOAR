package practice;

public class SingleTonClass {
	//declare constructor as private
	//create one static getter method
	private static SingleTonClass ref;
	private SingleTonClass() {
		System.out.println("one copy");
	}
	static int count=1;
	public static SingleTonClass getInstance() {
		while(count<=3) {
		ref=new SingleTonClass();
		count++;
		
		}
		return ref;
	} 

}
