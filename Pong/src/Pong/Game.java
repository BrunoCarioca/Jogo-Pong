package Pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	private static final long serialVersionUID = 1L;
	/*Configurações Janela*/
	public static final int WIDTH  = 160;
	public static final int HEIGHT = 120;
	private final int SCALE  = 2;
	public static JFrame windonw;
	/*Configurando loop*/
	private Thread thread;
	public static boolean isRunning;
	/*Desenhando tela*/
	private BufferedImage layer;
	/*Players*/
	public static Player player;
	public static Enemy  enemy;
	public static Ball   ball;
	public int pontosPlayer;
	public int pontosEnemy;
	
	public Game(int pontosPlayer, int pontosEnemy){
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.addKeyListener(this);
		this.pontosPlayer = pontosPlayer;
		this.pontosEnemy = pontosEnemy;
		player = new Player(WIDTH/2 - 20, HEIGHT - 5, this.pontosPlayer);
		enemy  = new Enemy(WIDTH/2 - 20, 0, this.pontosEnemy);
		ball = new Ball(WIDTH/2 - 2, HEIGHT/2 - 2);
		
		
	}
	
	public static void main(String[] args) {
		Game game = new Game(0,0);
		windonw = new JFrame("Pong");
		windonw.add(game);
		windonw.setResizable(false);
		windonw.pack();
		windonw.setLocationRelativeTo(null);
		windonw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windonw.setVisible(true);
		game.start();
	}
	

	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();	
	}
	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		player.update();
		enemy.update();
		ball.update();
	}
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		
		g = bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
		
	}
	
	
	public void run() {
		//Controlando loop
		long 	lastTime = System.nanoTime();
		double	amountOfticks = 60.0;
		double 	ns = 1000000000/amountOfticks;
		double 	delta = 0;
		//Verificando frames
		//int 	frames = 0;
		//double	timer =	System.currentTimeMillis();
		
		while(isRunning){
			requestFocus();
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			if(delta >= 1){
				update();
				render();
				//frames++;
				delta--;
			}
		
			/*if(System.currentTimeMillis() - timer >= 1000){
				//System.out.println("FPS: "+frames);
				frames = 0;
				timer+=1000;
			}*/
		}
		stop();
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = true;
		}
		
	}

	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			player.left = false;
		}
		
	}

	
	
	
}
