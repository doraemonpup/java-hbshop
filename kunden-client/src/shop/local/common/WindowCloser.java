package shop.local.common;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import shop.local.interfaces.EShopInterface;


public class WindowCloser extends WindowAdapter {

	private EShopInterface sf = null;
	
	public WindowCloser(EShopInterface esi) {
		this.sf = esi;
	}
	
	@Override
	public void windowClosing(WindowEvent we) {
		try {
			sf.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Frame f = (Frame) we.getSource();
		// Alternative: Frame f = (Frame) we.getWindow();

		f.setVisible(false);
		f.dispose();
	}
}
