import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteButton extends AdaptableButtonFont{
	DeleteButton(String text, Color color, JTextField _viewer){
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
		if(viewer.getText().isEmpty())
			return;

		int lastCharId = this.viewer.getText().length() - 1;
		String newText = this.viewer.getText().substring(0, lastCharId);

		this.viewer.setText(newText);
	}
}
