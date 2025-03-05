import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Color;

public class Display extends JPanel{
	private Viewer viewer; // original

	Display(int width, int height){
		super();
		this.setPreferredSize(new Dimension(width, height));

		this.viewer = new Viewer();
		this.add((JTextField)this.viewer);
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
		this.viewer.updateViewerSize(this);
	}

	public Viewer getViewer(){
		return this.viewer;
	}
}
