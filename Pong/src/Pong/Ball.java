package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {

	private double x,y;
	private	double dx,dy;
	private double speed;
	private int width, height;
	private int angle;
	
	
	public Ball(double x, double y){
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		this.speed = 1.3;
		this.angle = new Random().nextInt(359);
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void update(){
		
		if(x  <= 0){
			dx*=-1;
		}else if( x + width >= Game.WIDTH){
			dx*=-1;
		}	
	
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle boundsEnemy  = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(boundsPlayer)){
			dy*=-1;
		}else if(bounds.intersects(boundsEnemy)){
			dy*=-1;
		}
		
		
		if(y > Game.HEIGHT){
			
			if(Game.enemy.pontos == 5) {
				System.out.println("Enemy venceu!!");
				Game.windonw.dispose();
				Game.isRunning = false;
				return;
			}
			
			System.out.println("ponto enemy!!");
			Game.enemy.pontos++;
			System.out.println(Game.enemy.pontos);
			//Game.isRunning = false; 
			new Game(Game.player.pontos, Game.enemy.pontos);
			return;
		}else if(y < 0){
			
			if(Game.player.pontos == 5) {
				System.out.println("player venceu!!");
				Game.isRunning = false;
				return;
			}
			
			System.out.println("ponto player!!");
			Game.player.pontos++;
			System.out.println(Game.player.pontos);
			//Game.isRunning = false;
			new Game(Game.player.pontos, Game.enemy.pontos);
			return;
		}
		
		x += dx*speed; 
		y += dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, width, height);
		
		
	}
	
	public double getX() {
		return this.x;
	}
}
