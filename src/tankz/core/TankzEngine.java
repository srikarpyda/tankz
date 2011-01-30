package tankz.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import tankz.ui.TankzGameUI;

public class TankzEngine extends Thread implements KeyListener{
	
	public static TankzGameUI ui;
	public static TankzGrid grid;
	public static Vector<ActiveObject> active;
	private boolean isRunning = true;
	private boolean isPaused = false;
	
	public TankzEngine() {
		super("GameEngine");
		grid = new TankzGrid(9);
		this.setupGrid();
		active = new Vector<ActiveObject>();
		active.add(new Tank(16*8, 16*8));
		ui = new TankzGameUI();
		ui.addKeyListener(this);
	}
	
	public TankzEngine(File file){
		//TODO generate code for this
	}
	
	public void run() {
		while(isRunning){
			while(!isPaused){
				boolean repaint = true;
				if(repaint) {
					ui.repaintGame();
					repaint = false;
				}else {
					repaint = true;
				}
				
				iterateLogic();
				try {
					sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void stopGame(){
		this.isRunning = false;
	}
	
	public boolean togglePaused(){
		this.isPaused = !isPaused;
		return this.isPaused;
	}
	
	private void iterateLogic() {
		for(ActiveObject object : active){
			object.action();
		}
	}
	
	private void setupGrid(){
		TankzTileState state = TankzTileState.BLOCKED;
		for(int x = 1; x < grid.getGridSize(); x+=2){
			for(int y = 1; y < grid.getGridSize(); y+=2){
				grid.setState(x, y, state);
			}
		}
		grid.setState(0, 0, TankzTileState.TANK_START);
		grid.setState(0, 8, TankzTileState.TANK_START);
		grid.setState(8, 0, TankzTileState.TANK_START);
		grid.setState(8, 8, TankzTileState.TANK_START);
		grid.setState(4, 4, TankzTileState.POWERUP);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_UP){
			active.get(0).setDirection(Direction.NORTH);
			active.get(0).setVelocity((float) 0.5);
		}else if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
			active.get(0).setDirection(Direction.WEST);
			active.get(0).setVelocity((float) 0.5);
		}else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
			active.get(0).setDirection(Direction.EAST);
			active.get(0).setVelocity((float) 0.5);
		}else if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
			active.get(0).setDirection(Direction.SOUTH);
			active.get(0).setVelocity((float) 0.5);
		}else if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
			System.out.println("Space Pressed");
		}else if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.out.println("Escape Pressed");
		}else{
			System.out.println("Undefined Key");
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_UP){
			active.get(0).setDirection(Direction.NORTH);
			active.get(0).setVelocity(0);
		}else if(arg0.getKeyCode()==KeyEvent.VK_LEFT){
			active.get(0).setDirection(Direction.WEST);
			active.get(0).setVelocity(0);
		}else if(arg0.getKeyCode()==KeyEvent.VK_RIGHT){
			active.get(0).setDirection(Direction.EAST);
			active.get(0).setVelocity(0);
		}else if(arg0.getKeyCode()==KeyEvent.VK_DOWN){
			active.get(0).setDirection(Direction.SOUTH);
			active.get(0).setVelocity(0);
		}else if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
			
		}else{
			System.out.println("Undefined Key");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
