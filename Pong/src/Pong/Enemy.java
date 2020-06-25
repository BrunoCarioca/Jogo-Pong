package Pong;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy {

	public double x,y;
	public int	width, height;
	public int pontos;
	
	public Enemy(double x, double y, int pontos) {
		this.x = x;
		this.y = y;
		this.width =  40;
		this.height = 5;
		this.pontos = pontos;
		
	}
	
	public void update(){
		x+= (Game.ball.getX() - x - 10)*0.07;
	}
	
	public void render(Graphics g){
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
		
	}
	
}
