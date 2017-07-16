import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.omg.Messaging.SyncScopeHelper;

public class Frogger extends JPanel implements KeyListener {
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
	boolean gameStart = false;

	public Frogger() {
		setup();
		for (int p = 0; p < 25; p++) {
			Car newCar = new Car(0, 35 * p);
			newCar.setCarSpeed();
			newCar.setDitection();
			Cars.add(newCar);
			if(newCar.getDirection()==-1) {
				newCar.setCarX(750);
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
			G.fillRect(FrogX, FrogY, FrogSize, FrogSize);
			for (int x = 0; x < Cars.size(); x++) {
				G.setColor(Color.BLACK);
				G.fillRect(Cars.get(x).getCarX(), Cars.get(x).getCarY(), 50, 30);
			}
			if (counter % 100 == 0) {
				for (int v = 0; v < Cars.size(); v++) {
					if(Cars.get(v).getDirection()==1) {
						Cars.get(v).setCarX(Cars.get(v).getCarX() + Cars.get(v).getCarSpeed()+4);
					}
					if(Cars.get(v).getDirection()==-1) {
						Cars.get(v).setCarX(Cars.get(v).getCarX() - (Cars.get(v).getCarSpeed()+4));
					}
					
					if(Cars.get(v).getCarX()>=800 && Cars.get(v).getDirection()==1) {
						Cars.get(v).setCarX(0);
					}
					if(Cars.get(v).getCarX()<=-50 && Cars.get(v).getDirection()==-1) {
						Cars.get(v).setCarX(750);
					}
				}
			}
			repaint();
		}
		if(gameState==2) {
			
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

		}
	}
}
