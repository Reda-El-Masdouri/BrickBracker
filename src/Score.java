import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
	
	
	static int GAME_WIDTH;
	static int GAME_HEIGHT;
	int player; 

	Score(int GAME_WIDTH, int GAME_HEIGHT){
		Score.GAME_WIDTH = GAME_WIDTH;
		Score.GAME_HEIGHT = GAME_HEIGHT;
	}

	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Consolas",Font.BOLD,25));
		
		
		
		g.drawString(String.valueOf(player/10)+String.valueOf(player%10), (int)(GAME_WIDTH * 0.90), GAME_HEIGHT/12);
		
		// String.valueOf(player1/10)+String.valueOf(player1%10) for the 2 digit on the screen
		}
}
