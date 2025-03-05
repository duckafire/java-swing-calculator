import java.util.List;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Color;

public class Keyboard extends JPanel{
	private List<Key> buttons;

	Keyboard(Viewer viewerReference){
		super(new GridLayout(5, 4));

		// "\u232B" icon of the delete key
		String[] text = {
			"C", "^", "%", "/",
			"7", "8", "9", "*",
			"4", "5", "6", "-",
			"1", "2", "3", "+",
			".", "0", "\u232B", "=",
		};

		this.buttons = new ArrayList<Key>();

		Key newItem;
		KeyIndexes id;

		for(byte i = 0; i < 20; i++){
			if(     i == 0)  id = KeyIndexes.CLEAR_ALL;
			else if(i == 16) id = KeyIndexes.FLOAT_POINT;
			else if(i == 18) id = KeyIndexes.DELETE;
			else if(i == 19) id = KeyIndexes.RESULT;
			else if((i > 0 && i < 4) || (i < 16 && (i + 1) % 4 == 0)) id = KeyIndexes.OPERATOR;
			else id = KeyIndexes.NUMBER;
			
			newItem = new Key(id, text[i], viewerReference);
			this.buttons.add(newItem);
			this.add((JButton)newItem);
		}
	}

	public void updateButtonsFontSize(){
		for(Key item : this.buttons)
			item.updateButtonFontSize();
	}
}
