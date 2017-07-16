import java.util.Random;

public class Car {
	Random r=new Random();
	int CarY;
	int CarX;
	int CarSpeed;
	int direction;

	public int getCarSpeed() {
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
		CarSpeed = r.nextInt(8);
	}

	public Car(int CarX, int CarY) {
		this.CarY = CarY;
		this.CarX = CarX;
		this.CarSpeed=CarSpeed;
	}

	public void setCarY(int CarY) {
		this.CarY = CarY;
	}

	public void setCarX(int CarX) {
		this.CarX = CarX;
	}

	public int getCarY() {
		return CarY;
	}

	public int getCarX() {
		return CarX;
	}
}
