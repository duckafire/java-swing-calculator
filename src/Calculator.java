import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Calculator extends JFrame{
	private final int defaultWindowWidth = 500;
	private final int defaultWindowHeight = 700;

	private JPanel mainPanel;
	private Display display;
	private Keyboard keyboard;

	public Calculator(){
		super("Java swing calculator");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.defaultWindowWidth, this.defaultWindowHeight);
		this.setMinimumSize(new Dimension(220, 230));

		this.mainPanel = new JPanel(new BorderLayout(4, 4));

		this.display  = new Display(this.defaultWindowWidth, this.defaultWindowHeight);
		this.keyboard = new Keyboard(this.display.getViewer());

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				display.updateSize(getSize());
				keyboard.updateButtonsFontSize();
			}
		});

		this.mainPanel.add(this.display,  BorderLayout.NORTH);
		this.mainPanel.add(this.keyboard, BorderLayout.CENTER);
		this.add(this.mainPanel);
		this.setVisible(true);
	}
}
