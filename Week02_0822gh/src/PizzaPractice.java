class Circle {
	protected int radius;
	public Circle(int r) {radius = r;}
}

class Pizza extends Circle {

	String name;

	public Pizza(String name,int r) {
		super(r);
		this.name = name;
	}

	public void print(){
		System.out.println(name + " 피자의 반지름은 " + radius + "cm입니다"); 
		}
}

public class PizzaPractice {
	public static void main(String[] args) {
		Pizza obj = new Pizza("Pepperoni", 20);
		obj.print();

	}
	
}
