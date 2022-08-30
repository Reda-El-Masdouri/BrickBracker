import java.awt.Color;

import javax.swing.*;

public class MyFrame extends JFrame{
	
	GamePlay gamePlay;
	MyFrame(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.setSize(700, 600);
		//this.setLocationRelativeTo(null);//centering the frame
		this.setTitle("Brickout Ball");
		this.setResizable(false);
		this.setBackground(Color.black);
		
		
		gamePlay = new GamePlay();
		this.add(gamePlay);
		this.pack();
		this.setVisible(true);
		
	}
}
