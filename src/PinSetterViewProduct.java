

import javax.swing.JFrame;

public class PinSetterViewProduct {
	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void show() {
		frame.show();
	}

	public void hide() {
		frame.hide();
	}
}