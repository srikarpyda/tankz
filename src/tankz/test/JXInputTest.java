package tankz.test;

import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.directinput.DirectInputDevice;
import de.hardcode.jxinput.event.JXInputEventManager;

public class JXInputTest{

	
	
	public static void main(String[] args){
		
		System.load("C:\\Windows\\jxinput.dll");
		JXInputEventManager.setTriggerIntervall( 50 );
		for(int i = 0; i < JXInputManager.getNumberOfDevices(); i++){
			if(JXInputManager.getJXInputDevice(i).getName().equals("Controller (XBOX 360 For Windows)")){
				DirectInputDevice xbox = new DirectInputDevice(i);
				for(int j = 0; j < 2; j++){
					if(xbox.getAxis(j) != null){
						new JXInputAxisListener(xbox.getAxis(j));
					}
				}
				new JXInputButtonListener(xbox.getButton(0));
				
			}
		}
		while(true){
			
		}
	}

}
