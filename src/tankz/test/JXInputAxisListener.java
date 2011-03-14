package tankz.test;

import java.awt.event.KeyEvent;

import tankz.core.TankzEngine;
import de.hardcode.jxinput.Axis;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputAxisEventListener;
import de.hardcode.jxinput.event.JXInputEventManager;

public class JXInputAxisListener implements JXInputAxisEventListener {

	public JXInputAxisListener( Axis axis )
	{
		JXInputEventManager.addListener( this, axis, 0.95 );
	}

	
	@Override
	public void changed(JXInputAxisEvent arg0) {
		if(arg0.getAxis().getName().equals("X Axis")){
			System.out.println("X Axis Changed!");
			System.out.println(arg0.getAxis().getValue());
			if(arg0.getAxis().getValue() > (float) 0.2){
				TankzEngine.playerObjects.get(1).keyPressed(KeyEvent.VK_D);
			}else if(arg0.getAxis().getValue() < (float) -0.2){
				TankzEngine.playerObjects.get(1).keyPressed(KeyEvent.VK_A);
			}
		}else if(arg0.getAxis().getName().equals("Y Axis")){
			if(arg0.getAxis().getValue() > (float) 0.2){
				TankzEngine.playerObjects.get(1).keyPressed(KeyEvent.VK_S);
			}else if(arg0.getAxis().getValue() < (float) -0.2){
				TankzEngine.playerObjects.get(1).keyPressed(KeyEvent.VK_W);
			}
		}

	}

}
