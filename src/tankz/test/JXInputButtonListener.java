package tankz.test;

import tankz.core.TankzEngine;
import de.hardcode.jxinput.Button;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputButtonEventListener;
import de.hardcode.jxinput.event.JXInputEventManager;

public class JXInputButtonListener implements JXInputButtonEventListener {

	public JXInputButtonListener( Button button )
	{
		JXInputEventManager.addListener( this, button );
	}

	
	@Override
	public void changed(JXInputButtonEvent arg0) {
		if(arg0.getButton().getName().equals("Button 0")){
			if(arg0.getButton().getState() == true){
				TankzEngine.playerObjects.get(1).fire();
			}
		}

	}

}
