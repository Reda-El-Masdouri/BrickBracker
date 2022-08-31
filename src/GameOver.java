import java.awt.Color;
import java.awt.Font;
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
		g.drawString("Game Over", (int)(GAME_WIDTH * 0.5), GAME_HEIGHT/2);
		g.setFont(new Font("Consolas",Font.BOLD,20));
		g.drawString("Scores: "+String.valueOf(score1), (int)(GAME_WIDTH * 0.5), (int)(GAME_HEIGHT*0.65));
		
		g.setFont(new Font("Consolas",Font.BOLD,15));
		g.drawString("Press enter to restart ", (int)(GAME_WIDTH * 0.5), (int)(GAME_HEIGHT*0.75));
		
	}
}
