package brickbreaker;

import javax.swing.JFrame;

public class BrickMain {

	public static void main(String[] args) {
		JFrame obj = new JFrame();
		Gameplay gamePlay = new Gameplay();
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("D'Ball Brick Breaker");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(3);
		obj.add(gamePlay);

	}

}
