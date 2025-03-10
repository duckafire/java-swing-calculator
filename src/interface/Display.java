import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

public class Display extends JPanel{
	private JTextField viewer;

	Display(int width, int height){
		super();
		this.setPreferredSize(new Dimension(width, height));

		this.viewer = new JTextField("");
		this.viewer.setBackground(Color.GRAY);
		this.viewer.setEditable(false);
		this.viewer.setPreferredSize(this.getPreferredSize());
		this.viewer.setHorizontalAlignment(JTextField.RIGHT);

		this.add(this.viewer);
	}

	public void updateSize(Dimension windowSize){
		this.setPreferredSize(new Dimension(windowSize.width, (int)(windowSize.height / 5)));
		this.revalidate();
		this.repaint();

		this.viewer.setPreferredSize(this.getPreferredSize());
		this.updateViewerSize();
		this.viewer.revalidate();
		this.viewer.repaint();
	}

	private void updateViewerSize(){
		float fontSize = (float)(this.getPreferredSize().height * 0.75);
		Font updatedFont = this.viewer.getFont().deriveFont(fontSize);

		this.viewer.setFont(updatedFont);
	}
}
