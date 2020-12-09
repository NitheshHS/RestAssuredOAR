package practice;

public class Sample {
	public static void main(String[] args) {
		
		SingleTonClass ref=SingleTonClass.getInstance();
		System.out.println(ref);
		SingleTonClass ref1=SingleTonClass.getInstance();
		System.out.println(ref1);
		SingleTonClass ref2=SingleTonClass.getInstance();
		System.out.println(ref2);
		SingleTonClass ref3=SingleTonClass.getInstance();
		System.out.println(ref3);
	}

}
