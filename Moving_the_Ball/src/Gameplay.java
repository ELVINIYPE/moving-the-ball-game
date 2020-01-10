//package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

//import com.sun.glass.ui.Timer;
//import com.sun.prism.Graphics;
//import com.sun.prism.paint.Color;

public class Gameplay  extends JPanel implements KeyListener, ActionListener  {
	private boolean play = false;
	private int score = 0;
	private int totalBricks =28;
	private  Timer timer;
	private int delay = 5;
	private int playerX = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	
	public Gameplay() {
		map = new MapGenerator(4,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
		
		
	}
	
	
	public void paint(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		map.draw((Graphics2D)g);
		
		g.setColor(Color.yellow);
		g.fillRect(1,1,3,592);
		g.fillRect(1,1,692,3);
		g.fillRect(691,0,3,592);
		
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD,25));
		g.drawString(""+score, 590, 30);
		
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20,20);
		
		
		if(totalBricks==0) {
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD,20));
			g.drawString("yoyo congrats, SCORE: "+score, 190, 300);
		}
		if((ballposY > 570) && (totalBricks!=0)){
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD,20));
			g.drawString("GAME OVER, SCORE: "+score, 190, 300);
			g.setFont(new Font("serif", Font.BOLD,20));
			g.drawString("Press ENTER to RESTART ", 190, 400);
			
		}
		
		
		
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		timer.start();
		if(play) {
			if(new Rectangle(ballposX,ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			
			A : for(int i =0; i<map.map.length; i++) {
					for(int j = 0; j<map.map[0].length; j++){
						if(map.map[i][j] > 0) {
							int brickX = j* map.brickWidth + 80;
							int brickY = i* map.brickHeight + 50;
							int brickWidth = map.brickWidth;
							int brickHeight = map.brickHeight;
							
							Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
							Rectangle ballRect = new Rectangle(ballposX, ballposY, 20,20);
							Rectangle brickRect = rect;
							
							if(ballRect.intersects(brickRect)) {
								map.setBrickValue(0,i,j);
								totalBricks--;
								score +=5;
								
								
								if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
									ballXdir = -ballXdir;
							} else {
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

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
				} else {
					moveRight();
				}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10	) {
				playerX = 10;
				} else {
					moveLeft();
				}
			
		}
		
		
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3,7);
				repaint();
				
			}
		}
		
		
	}


	private void moveLeft() {
		// TODO Auto-generated method stub
		play = true;
		playerX-=40;
		
		
	}


	private void moveRight() {
		// TODO Auto-generated method stub
		play = true;
		playerX+=40;
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

	
	

}
