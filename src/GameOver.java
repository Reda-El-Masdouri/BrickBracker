import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameOver {
	int score1;
	int GAME_WIDTH;
	int GAME_HEIGHT;
	int BALL_DIAMETER;
	int PADDLE_HEIGHT;
	int PADDLE_WIDTH;
	
	GameOver(int score1, int GAME_WIDTH, int GAME_HEIGHT){
		this.score1 = score1;
		this.GAME_WIDTH = GAME_WIDTH;
		this.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Consolas",Font.BOLD,25));
		
		FontMetrics metrics1 = g.getFontMetrics(g.getFont()); // aligning up text in the center of the screen
		g.drawString("Game Over", (GAME_WIDTH - metrics1.stringWidth("Game Over"))/2, GAME_HEIGHT/2);
		
		
		g.setFont(new Font("Consolas",Font.BOLD,20));
		FontMetrics metrics2 = g.getFontMetrics(g.getFont()); // aligning up text in the center of the screen
		g.drawString("Scores: "+score1, (GAME_WIDTH - metrics2.stringWidth("Scores: "))/2, (int)(GAME_HEIGHT*0.65));
		
		g.setFont(new Font("Consolas",Font.BOLD,15));
		//g.drawString("Press enter to restart", (int)(GAME_WIDTH * 0.5), (int)(GAME_HEIGHT*0.75));
		FontMetrics metrics3 = g.getFontMetrics(g.getFont()); // aligning up text in the center of the screen
		g.drawString("Press enter to restart", (GAME_WIDTH - metrics3.stringWidth("Press enter to restart"))/2, (int)(GAME_HEIGHT*0.75));
		
	}
}
