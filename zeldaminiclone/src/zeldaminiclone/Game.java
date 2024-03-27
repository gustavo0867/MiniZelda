package zeldaminiclone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

//criando janela do meu jogo
public class Game extends Canvas implements Runnable, KeyListener  {
	//criando tamanho da janela do nosso jogo
	public static int WIDHT = 480, HEIGHT = 480;
	public Player player;
	
	public World world;
	
	
	
	public Game() {
		//quero adicionar eventos de teclado, e os metodos ja estao criados
		this.addKeyListener(this);
		//CRIANDO NOVA DIMENSAO
		this.setPreferredSize(new Dimension(WIDHT,HEIGHT));
		new Spritesheet();
		
		player = new Player(32,32);
		world = new World();
		
	}
	//responsavel pela logica do jogo
	public void tick() {
		player.tick();
		
	}
	
	//renderizar graficos
public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDHT, HEIGHT);
		
		player.render(g);
		world.render(g);
		
		
		bs.show();
		
	}
	
	//metodo principal do nosso jogo
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		//conteudo do jogo sera exibido na janela 
		frame.add(game);
		//titulo 
		frame.setTitle("Mini Zelda");
		
		//caluclar o tamanho da janela automaticamente
		frame.pack();
		//deixando a janela centralizada
		frame.setLocationRelativeTo(null);
		//qunaod a janela fechar o processo java parar tbm
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		
		
		new Thread(game).start();
		
	}
	
	
	@Override
	public void run() {
		
		while(true) {
			//System.out.println("Chamando game looping!");
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		
	}
	
	

}






