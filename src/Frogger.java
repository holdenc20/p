import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frogger extends JPanel implements KeyListener{
	Font font=new Font(Font.SANS_SERIF, Font.BOLD,20);
	Graphics g;
	int gameState=0;//0 start 1 game 2 game over
	JFrame p=new JFrame();
	public Frogger(){
		setup();
		
	}
	public void paintComponent(Graphics G){
		//super.paintComponent(G);
		if(gameState==0){
			System.out.println("0");
			G.setColor(Color.GREEN);
			G.fillRect(0, 0, 800,1600);
			G.setColor(Color.BLACK);
			G.drawString("Frogger", 100, 100);
			G.setFont(font);
			G.drawString("press space to start",100,200);
		}
		if(gameState==1){
			System.out.println("1");
			G.setColor(Color.GRAY);
			G.fillRect(0, 0, 800, 1600);
		}
	}
	

	void setup(){
	 p.setVisible(true);
	 p.add(this);
	 p.setSize(800,1600);
	 p.addKeyListener(this);
	}
	@Override
	public void keyTyped(KeyEvent e) {

	}
	@Override
	public void keyPressed(KeyEvent e) {

	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(gameState==0){
			if(e.getKeyCode()==32){
				gameState=1;
				System.out.println(gameState);
		}
			
		}
		else if(gameState==1){
			
		}
		else if(gameState==2){
			
		}
	}
}
