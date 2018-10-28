package BlockBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.*;


public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseMotionListener{
	
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 32;
	
	private Timer timer;
	private int delay = 12;
	
	private int playerX = 310;
	
	private int ballposX;
	private int ballposY;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	public Gameplay() {
		ballposX = (int)(Math.random()*300) + 300;
		ballposY = (int)(Math.random()*300) + 250;
		map = new MapGenerator(4, 8);
		addKeyListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//bricks
		map.draw((Graphics2D)g);
		
//		//borders
//		g.setColor(Color.yellow);
//		g.fillRect(0, 0, 3, 592);
//		g.fillRect(0, 0, 692, 3);
//		g.fillRect(691, 0, 3, 592);
//		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		//the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);
		
		if(totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("YOU WON!!!!", 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 260, 350);
		}
		
		if(ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Score: "+score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(play) {
			if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))){
				ballYdir = -ballYdir;
			}
			
			//make the bricks in different colors and in different points value. 
			
			
			A: for(int i = 0; i < map.map.length; i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.brickwidth + 80;
						int brickY = i * map.brickheight + 50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
						Rectangle brickRect = rect;
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							updateScore(i);
							if(delay > 1)
							timer.setDelay(delay--);
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}
							else {
								ballYdir = -ballYdir;
							}
							
							break A;
						}
					}
				}
			}
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			}
		}
		repaint();
		
	}
	
	public void updateScore(int i) {
		switch(i) {
			case 0: score+=20;
					break;
			case 1: score+=15;
					break;
			case 2: score+=10;
					break;
			case 3: score+=5;
					break;
		}
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;				
				ballposX = (int)(Math.random()*300) + 300;
				ballposY = (int)(Math.random()*300) + 250;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 32;
				map = new MapGenerator(4, 8);
				delay = 12;
				timer.setDelay(delay);
				
				repaint();
			}
		}
	}
	
	public void startNewGame() {
		play = true;				
		ballposX = (int)(Math.random()*300) + 300;
		ballposY = (int)(Math.random()*300) + 250;
		ballXdir = -1;
		ballYdir = -2;
		playerX = 310;
		score = 0;
		totalBricks = 32;
		map = new MapGenerator(4, 8);
		delay = 12;
		timer.setDelay(delay);
		
		repaint();
	}
 
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(e.getX() >= 600) {
			playerX = 600;
		}
		else if(e.getX() < 10) {
			playerX = 10;
		}
		else {
			playerX = e.getX();	
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {	}

}
