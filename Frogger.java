import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.Messaging.SyncScopeHelper;

public class Frogger extends JPanel implements KeyListener {
	public static BufferedImage carImg;
	public static BufferedImage froggyImg;
	public static BufferedImage carImg2;
	public static BufferedImage roadImg;
	public static BufferedImage grassImg;
	boolean roads=false;
	Font font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
	Graphics g;
	int counter = 0;
	int gameState = 0;// 0 start 1 game 2 game over 3 game win
	JFrame p = new JFrame();
	Random r = new Random();
	int FrogSize = 50;
	int FroggyX = 400;
	int FroggyY = 900;
	boolean isAlive = true;
	ArrayList<Car> Cars = new ArrayList<Car>();
	ArrayList<Rectangle> CarBoxes=new ArrayList<Rectangle>();
	boolean gameStart = false;
	Rectangle FrogBox;
	Rectangle CarBox;
	Rectangle CarBox2;
	public Frogger() {
		try {
			carImg2 = ImageIO.read(this.getClass().getResourceAsStream("car2.jpeg"));
			carImg = ImageIO.read(this.getClass().getResourceAsStream("car.png"));
			froggyImg = ImageIO.read(this.getClass().getResourceAsStream("frog.png"));
			roadImg = ImageIO.read(this.getClass().getResourceAsStream("road.jpg"));
			grassImg = ImageIO.read(this.getClass().getResourceAsStream("grass.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setup();
		FrogBox=new Rectangle(FroggyX,FroggyY,50,50);
		for (int p = 0; p < 17; p++) {
			Car newCar = new Car(0,50+(50 * p));
			Car newCar2 = new Car(750/2,50+(50 * p));
			newCar.setCarSpeed();
			newCar.setDirection();
			newCar2.setCarSpeed(newCar.getCarSpeed());
			newCar2.setDirection(newCar.getDirection());
			Cars.add(newCar);
			Cars.add(newCar2);
			CarBox=new Rectangle((int)newCar.getCarX(),newCar.getCarY(),50,50);
			CarBox2=new Rectangle((int)newCar2.getCarX(),newCar2.getCarY(),50,50);
			CarBoxes.add(CarBox);
			CarBoxes.add(CarBox2);
			if(newCar.getDirection()==-1) {
				newCar.setCarX(750);
				CarBox.setBounds(750, newCar.getCarY(), 50, 50);
				newCar2.setCarX(750/2);
				CarBox2.setBounds(750, newCar2.getCarY(), 50, 50);
			}
			
		}

	}

	public void paintComponent(Graphics G) {
		
		// super.paintComponent(G);
		if (gameState == 0) {
			G.setColor(Color.GREEN);
			G.fillRect(0, 0, 800, 1100);
			G.setColor(Color.BLACK);
			G.setFont(font);
			G.drawString("Frogger", 100, 100);
			G.drawString("press space to start", 100, 200);
		}
		if (gameState == 1) {
			counter++;
			G.setColor(Color.BLACK);
			G.fillRect(0, 0, 800, 1000);
			//G.setColor(Color.GREEN);
			G.drawImage(grassImg, 0, 0, 800, 50, null);
			G.drawImage(grassImg, 0, 50+(17*50), 800, 50, null);
				for(int o=0;o<17;o++){
					G.drawImage(roadImg, 0, 50+(o*50), 800, 50, null);
				}
			G.drawImage(froggyImg, FroggyX, FroggyY, 50, 50, null);
			//G.fillRect(FroggyX, FroggyY, FrogSize, FrogSize);
			if(FroggyY<0){
				gameState=3;
			}
			for (int x = 0; x < Cars.size(); x++) {
				G.setColor(Color.BLACK);
				if(Cars.get(x).getDirection()==-1){
					G.drawImage(carImg, (int)Cars.get(x).getCarX(),Cars.get(x).getCarY(), 50, 50, null);
				}
				if(Cars.get(x).getDirection()==1){
					G.drawImage(carImg2, (int)Cars.get(x).getCarX(),Cars.get(x).getCarY(), 50, 50, null);
				}
				//G.fillRect(Cars.get(x).getCarX(), Cars.get(x).getCarY(), 50, 30);
			}
			if (counter % 10 == 0) {
				for (int v = 0; v < Cars.size(); v++) {
					if(Cars.get(v).getDirection()==1) {
						Cars.get(v).setCarX(Cars.get(v).getCarX() + (Cars.get(v).getCarSpeed()+15)/15);
					}
					if(Cars.get(v).getDirection()==-1) {
						Cars.get(v).setCarX(Cars.get(v).getCarX() - (Cars.get(v).getCarSpeed()+15)/15);
					}
					
					if(Cars.get(v).getCarX()>=800 && Cars.get(v).getDirection()==1) {
						Cars.get(v).setCarX(0);
					}
					if(Cars.get(v).getCarX()<=-50 && Cars.get(v).getDirection()==-1) {
						Cars.get(v).setCarX(750);
					}
					for(int i=0;i<CarBoxes.size();i++){
						FrogBox.setBounds(FroggyX,FroggyY,50,50);
						CarBoxes.get(i).setBounds((int)Cars.get(i).getCarX(),Cars.get(i).getCarY() , 50, 30);
						if(CarBoxes.get(i).intersects(FrogBox)){
							gameState=2;
							repaint();
						}
					}
				}
			}
			repaint();
		}
		if(gameState==2) {
			G.setColor(Color.RED);
			G.fillRect(0, 0, 800, 1000);
			G.setColor(Color.BLACK);
			G.setFont(font);
			G.drawString("Game Over", 100, 100);
			G.drawString("press space to go to start", 100, 200);
		}
		if(gameState==3){
			G.setColor(Color.GREEN);
			G.fillRect(0, 0, 800, 1000);
			G.setColor(Color.BLACK);
			G.setFont(font);
			G.drawString("You Win!", 100, 100);
			G.drawString("press space to go to start", 100, 200);
		}
	}
	void setup() {
		p.setVisible(true);
		p.add(this);
		p.setSize(800, 1000);
		p.addKeyListener(this);
		p.setResizable(false);
		p.setTitle("Frogger");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (gameState == 0) {
			if (e.getKeyCode() == 32) {
				gameState = 1;
				gameStart = true;
				repaint();
			}

		} else if (gameState == 1) {
			if (e.getKeyCode() == 87) {
				FroggyY = FroggyY - 50;
			} else if (e.getKeyCode() == 65) {
				if (FroggyX > 0) {
					FroggyX = FroggyX - 50;
				}
			} else if (e.getKeyCode() == 83) {
				if (FroggyY < 950) {
					FroggyY = FroggyY + 50;
				}
			} else if (e.getKeyCode() == 68) {
				if (FroggyX < 750) {
					FroggyX = FroggyX + 50;
				}
			}
			repaint();
		} else if (gameState == 2) {
			if (e.getKeyCode() == 32) {
				gameStart=false;
				gameState = 0;
				this.setVisible(false);
				new Frogger();
				repaint();
			}
		}
		else if (gameState == 3) {
			if (e.getKeyCode() == 32) {
				gameStart=false;
				gameState = 0;
				this.setVisible(false);
				new Frogger();
				repaint();
			}
		}
	}
}
