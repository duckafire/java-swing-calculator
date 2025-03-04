import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NumberButton extends AdaptableButtonFont{
	NumberButton(String text, Color color, JTextField _viewer){
		super(text, color, _viewer);

		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				action(e);
			}
		});
	}

	@Override
	protected void action(ActionEvent e){
		char text = e.getActionCommand().charAt(0);

		if(text < '0' || text > '9' || (text == '0' && this.viewer.getText().isEmpty()))
			return;

		String newText = this.viewer.getText() + text;
		this.viewer.setText(newText);
	}
}
