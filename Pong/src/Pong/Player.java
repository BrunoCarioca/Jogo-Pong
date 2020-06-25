package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {

	public boolean right, left;
	public int x, y;
	public int width, height;
	public int pontos; 
	
	public Player(int x, int y, int pontos){
		this.x = x;
		this.y = y;
		this.width = 40;
		this.height = 5;
		this.pontos = pontos;
		
	}
	
	public void update() {
		if(right == true){
			x++;
		}else if(left == true) {
			x--;
		}
		
		if(x+width > Game.WIDTH) {
			x = Game.WIDTH - width; 
		} else if (x < 0 ) {
			x = 0;
		}
	}
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x, y, width, height);
		
	}
	
	
}
