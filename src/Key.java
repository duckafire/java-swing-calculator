import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Key extends JButton{
	private KeyIndexes id;

	private static Viewer viewer;
	private boolean firstFontSizeUpdate = true;

	Key(KeyIndexes _id, String text, Viewer viewerReference){
		super(text);
		this.id = _id;
		this.setBackground(Color.GRAY);
		this.viewer = viewerReference;

		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				action(e);
			}
		});
	}

	private void action(ActionEvent e){
		if(this.id == KeyIndexes.CLEAR_ALL)
			this.viewer.clearAll();

		else if(this.id == KeyIndexes.DELETE)
			this.viewer.decrementTextField();

		else if(this.id == KeyIndexes.RESULT)
			this.viewer.clearAll();

		else
			this.viewer.incrementTextField(e.getActionCommand(), this.id == KeyIndexes.OPERATOR);
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
