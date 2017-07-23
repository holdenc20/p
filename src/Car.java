import java.util.Random;

public class Car {
	Random r=new Random();
	int CarY;
	double CarX;
	double CarSpeed;
	int direction;

	public double getCarSpeed() {
		return CarSpeed;
	}
	public void setDitection() {
		int rand=r.nextInt(2);
		if(rand==1) {
			direction=1;
		}
		if(rand==0) {
			direction=-1;
		}
	}
	public int getDirection() {
		return direction;
		
	}
	public void setCarSpeed() {
		CarSpeed = r.nextInt(30);
	}

	public Car(int CarX, int CarY) {
		this.CarY = CarY;
		this.CarX = CarX;
		//this.CarSpeed=CarSpeed;
	}

	public void setCarY(int CarY) {
		this.CarY = CarY;
	}

	public void setCarX(double CarX) {
		this.CarX = CarX;
	}

	public int getCarY() {
		return CarY;
	}

	public double getCarX() {
		return CarX;
	}
}
