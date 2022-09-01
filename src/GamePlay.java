import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GamePlay extends JPanel implements Runnable, KeyListener, ActionListener{
	
	
	private MapGenerator table;
	
	static final int GAME_WIDTH = 700;
	static final int GAME_HEIGHT = 600;
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 10;
	static final int PADDLE_HEIGHT = 100;
	static int TOTAL_BRICKES = 21;
	
	Thread gameThread;
	Image image;
	WinGame win = new WinGame(GAME_WIDTH, GAME_HEIGHT);
	Graphics graphics;
	Random random;
	Paddle paddle;
	Ball ball;
	Score score = new Score(GAME_WIDTH, GAME_HEIGHT);
	GameOver gameOver;
	GamePlay(){
		newPaddle();
		newBall();
		
		this.setFocusable(true);// if we press a key it going have focus
		this.addKeyListener(new AL());
		this.setPreferredSize(SCREEN_SIZE); // a Dimension -> SCREEN_SIZE
		
		newTable();
		
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void newBall() {
		random = new Random();
		int randomPosYBall = random.nextInt((int)(GAME_HEIGHT-BALL_DIAMETER)/2, GAME_HEIGHT-BALL_DIAMETER); // for the middle ->(GAME_HEIGHT-BALL_DIAMETER)/2
		ball = new Ball((GAME_WIDTH-BALL_DIAMETER)/2,randomPosYBall,BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddle() {
		paddle = new Paddle((GAME_WIDTH/2)-(PADDLE_HEIGHT/2), GAME_HEIGHT-PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_WIDTH);
		
	}
	public void newTable() {
		table = new MapGenerator(3,7);
	}
	public void paint(Graphics g) {
		image = createImage(getWidth(), getHeight()); 
		graphics = image.getGraphics();
		draw(graphics);// draw all the components
		g.drawImage(image, 0, 0, this);
	}
	public void draw(Graphics g) {
		paddle.draw(g);
		ball.draw(g);
		table.draw((Graphics2D)g);
		score.draw(g);
		// bounce ball of bottom window edges:
		if(ball.y >= (GAME_HEIGHT-BALL_DIAMETER)) {
			gameOver = new GameOver(score.player,GAME_WIDTH, GAME_HEIGHT);
			ball.setYDirection(0);
			ball.setXDirection(0);
			
			gameOver.draw(g);
		}
		if(TOTAL_BRICKES == 0) {
			ball.setYDirection(0);
			ball.setXDirection(0);
			win.draw(g);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void move() {
		paddle.move();
		ball.move();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfThicks = 60.0;
		double ns = 1000000000/amountOfThicks; // nano-second
		double delta = 0;
		while(true) {
			long now =System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
		
	}
	private void checkCollision() {
		// bounce ball off top, left and right window edges:
		if(ball.y <= 0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.x <= 0) {
			ball.setXDirection(-ball.xVelocity);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			ball.setXDirection(-ball.xVelocity);
		}
		
		// bounces ball of paddle: we use the intersects method of Rectangle class
		if(ball.intersects(paddle)) {
			ball.yVelocity++;// optional for more difficulty
			ball.setYDirection(-ball.yVelocity);
		}
		// stop the paddle between the right and the left window edges:
		if(paddle.x <= 0) {
			paddle.x = 0;
		}
		if(paddle.x > (GAME_WIDTH - PADDLE_HEIGHT)) {
			paddle.x = GAME_WIDTH - PADDLE_HEIGHT;
		}
		// bounces ball of bricks:
			// each brick will be a rectangle to use the intersects method:
		A: for(int i =0; i< table.map.length; i++) {
			for(int j = 0; j< table.map[0].length; j++) {
				if(table.map[i][j]>0) {
					int brickX = j*table.brickWidth + 80;
					int brickY = i*table.brickHeight +50;
					int brickWidth = table.brickWidth;
					int brickHeight = table.brickHeight;
					Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
					Rectangle brickRectangle = rect;
					if(ball.intersects(brickRectangle)) {
						table.setBrickValue(0, i, j);
						TOTAL_BRICKES--;
						score.player += 5;
						
						if(brickRectangle.x + brickRectangle.width <= ball.x+1) {
							//System.out.println("1");
							ball.setXDirection(-ball.xVelocity);
						}
						else if(ball.x+BALL_DIAMETER-1 <= brickRectangle.x) {
							//System.out.println("2");
							ball.setXDirection(-ball.xVelocity);
						}
						/*else if(brickRectangle.y + brickRectangle.height >= ball.y){
							System.out.println("3");
							ball.setYDirection(-ball.yVelocity);
						}*/
						else {
							//System.out.println("4");
							ball.setYDirection(-ball.yVelocity);
						}
						break A;
					}
					
				}
				
			}
		}
	}
	
	// inner class for action listener
		public class AL extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				paddle.keyPressed(e);
				
				if(ball.xVelocity == 0 && ball.yVelocity == 0) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						newBall();
						newPaddle();
						newTable();
						score.player = 0;
						TOTAL_BRICKES = 21;
					}
				}
			}
			public void keyReleased(KeyEvent e) {
				paddle.keyReleased(e);
				
			}
		}
}
