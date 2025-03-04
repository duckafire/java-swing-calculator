import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionEvent;

public abstract class AdaptableButtonFont extends JButton{
	protected static JTextField viewer; // reference

	AdaptableButtonFont(String text, Color backgroundColor, JTextField _viewer){
		super(text);
		this.setBackground(backgroundColor);
		this.viewer = _viewer;
	}

	public void updateButtonFontSize(){
		Dimension dim = this.getSize();

		int width  = (int)(dim.width  * 0.25);
		int height = (int)(dim.height * 0.25);

		float newSize = (float)((width < height) ? width : height);
		Font updatedFont = this.getFont().deriveFont(newSize);

		this.setFont(updatedFont);
		this.revalidate();
		this.repaint();
	}

	public char getViewerLastChar(){
		if(this.viewer.getText().isEmpty())
			return '\u0000';
			
		int id = this.viewer.getText().length() - 1;
		return this.viewer.getText().charAt(id);
	}

	protected abstract void action(ActionEvent e);
}
