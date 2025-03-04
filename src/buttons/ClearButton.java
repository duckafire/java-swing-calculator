import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClearButton extends AdaptableButtonFont{
	ClearButton(String text, Color color, JTextField _viewer){
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
		this.viewer.setText("");
	}
}
