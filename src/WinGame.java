import java.awt.*;
import java.awt.event.*;
import java.util.Random; 
import javax.swing.*;

public class WinGame {
	
	int GAME_WIDTH;
	int GAME_HEIGHT;
	
	
	WinGame(int GAME_WIDTH, int GAME_HEIGHT){
		this.GAME_WIDTH = GAME_WIDTH;
		this.GAME_HEIGHT = GAME_HEIGHT;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Consolas",Font.BOLD,55));
		FontMetrics metrics = g.getFontMetrics(g.getFont()); // aligning up text in the center of the screen
		g.drawString("You won the game", (GAME_WIDTH - metrics.stringWidth("You won the game"))/2, GAME_HEIGHT/2);
		g.setFont(new Font("Consolas",Font.BOLD,15));
		FontMetrics metrics1 = g.getFontMetrics(g.getFont()); // aligning up text in the center of the screen
		g.drawString("Press enter to restart", (GAME_WIDTH - metrics1.stringWidth("Press enter to restart"))/2, (int)(GAME_HEIGHT*0.75));
		
	}
}
