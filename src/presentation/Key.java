package calculator.presentation;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Key extends JButton{
	private boolean firstFontSizeUpdate = true;

	public Key(String text){
		super(text);
		this.setBackground(Color.GRAY);

		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				action(e);
			}
		});
	}

	private void action(ActionEvent e){
		// DEBUG: it will be moved
		System.out.println(this.getText());
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
