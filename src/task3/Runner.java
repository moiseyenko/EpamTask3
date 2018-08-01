package task3;

public class Runner {
	public static void main(String[] args) {

		Parking park = new Parking(3);

		new Car("one", park, 6000, 5000);
		new Car("two", park, 6000, 5000);
		new Car("three", park, 6000, 5000);
		new Car("four", park, 500, 5000);
		new Car("five", park, 500, 5000);
		new Car("six", park, 500, 5000);

	}
}
