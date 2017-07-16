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
	public static BufferedImage frogImg;
	public static BufferedImage carImg2;


	Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	Graphics g;
	int counter = 0;
	int gameState = 0;// 0 start 1 game 2 game over
	JFrame p = new JFrame();
	Random r = new Random();
	int FrogSize = 50;
	int FrogX = 400;
	int FrogY = 900;
	boolean isAlive = true;
	ArrayList<Car> Cars = new ArrayList<Car>();
	ArrayList<Rectangle> CarBoxes=new ArrayList<Rectangle>();
	boolean gameStart = false;
	Rectangle FrogBox;
	Rectangle CarBox;
	public Frogger() {
		try {
			carImg2 = ImageIO.read(this.getClass().getResourceAsStream("car2.jpeg"));
			carImg = ImageIO.read(this.getClass().getResourceAsStream("car.png"));
			frogImg = ImageIO.read(this.getClass().getResourceAsStream("frog.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setup();
		FrogBox=new Rectangle(FrogX,FrogY,50,50);
		for (int p = 0; p < 25; p++) {
			Car newCar = new Car(0, 35 * p);
			newCar.setCarSpeed();
			newCar.setDitection();
			Cars.add(newCar);
			CarBox=new Rectangle(newCar.getCarX(),newCar.getCarY(),50,30);
			CarBoxes.add(CarBox);
			if(newCar.getDirection()==-1) {
				newCar.setCarX(750);
				CarBox.setBounds(750, newCar.getCarY(), 50, 30);
			}
			
		}

	}

	public void paintComponent(Graphics G) {
		// super.paintComponent(G);
		if (gameState == 0) {
			G.setColor(Color.GREEN);
			G.fillRect(0, 0, 800, 1000);
			G.setColor(Color.BLACK);
			G.setFont(font);
			G.drawString("Frogger", 100, 100);
			G.drawString("press space to start", 100, 200);
		}
		if (gameState == 1) {
			counter++;
			G.setColor(Color.GRAY);
			G.fillRect(0, 0, 800, 1000);
			G.setColor(Color.GREEN);
			G.drawImage(frogImg, FrogX, FrogY, 50, 50, null);
			//G.fillRect(FrogX, FrogY, FrogSize, FrogSize);
			for (int x = 0; x < Cars.size(); x++) {
				G.setColor(Color.BLACK);
				if(Cars.get(x).getDirection()==-1){
					G.drawImage(carImg, Cars.get(x).getCarX(),Cars.get(x).getCarY(), 50, 30, null);
				}
				if(Cars.get(x).getDirection()==1){
					G.drawImage(carImg2, Cars.get(x).getCarX(),Cars.get(x).getCarY(), 50, 30, null);
				}
				//G.fillRect(Cars.get(x).getCarX(), Cars.get(x).getCarY(), 50, 30);
			}
			if (counter % 100 == 0) {
				for (int v = 0; v < Cars.size(); v++) {
					if(Cars.get(v).getDirection()==1) {
						Cars.get(v).setCarX(Cars.get(v).getCarX() + Cars.get(v).getCarSpeed());
					}
					if(Cars.get(v).getDirection()==-1) {
						Cars.get(v).setCarX(Cars.get(v).getCarX() - (Cars.get(v).getCarSpeed()));
					}
					
					if(Cars.get(v).getCarX()>=800 && Cars.get(v).getDirection()==1) {
						Cars.get(v).setCarX(0);
					}
					if(Cars.get(v).getCarX()<=-50 && Cars.get(v).getDirection()==-1) {
						Cars.get(v).setCarX(750);
					}
					for(int i=0;i<CarBoxes.size();i++){
						FrogBox.setBounds(FrogX,FrogY,50,50);
						CarBoxes.get(i).setBounds(Cars.get(i).getCarX(),Cars.get(i).getCarY() , 50, 30);
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
		if (gameState == 0) {
			if (e.getKeyCode() == 32) {
				gameState = 1;
				gameStart = true;
				repaint();
			}

		} else if (gameState == 1) {
			if (e.getKeyCode() == 87) {
				FrogY = FrogY - 50;
			} else if (e.getKeyCode() == 65) {
				if (FrogX > 0) {
					FrogX = FrogX - 50;
				}
			} else if (e.getKeyCode() == 83) {
				if (FrogY < 950) {
					FrogY = FrogY + 50;
				}
			} else if (e.getKeyCode() == 68) {
				if (FrogX < 750) {
					FrogX = FrogX + 50;
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
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
