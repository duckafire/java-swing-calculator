package calculator.presentation;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

@SuppressWarnings("serial")
public class Key extends JButton{
	private boolean firstFontSizeUpdate = true;
	private KeyIndex id;

	public Key(String text, KeyIndex _id){
		super(text);
		this.setBackground(Color.GRAY);
		this.id = _id;
	}

	public KeyIndex getId(){
		return this.id;
	}

	public void updateButtonFontSize(){
		Font updatedFont;

		if(this.firstFontSizeUpdate){
			this.firstFontSizeUpdate = false;
			updatedFont = this.getFont().deriveFont((float)26);

		}else{
			Dimension dim = this.getSize();

			int width  = (int)(dim.width  * 0.25);
			int height = (int)(dim.height * 0.25);

			float newSize = (float)((width < height) ? width : height);
			updatedFont = this.getFont().deriveFont(newSize);
		}

		this.setFont(updatedFont);
		this.revalidate();
		this.repaint();
	}
}
