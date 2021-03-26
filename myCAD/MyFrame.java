
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* MyFrame.java */
/* Description: the frame */
public class MyFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private ShapeContainer shapeContainer = new ShapeContainer();
    private DrawPanel drawPanel = new DrawPanel(shapeContainer);
    private MyMenuBar menuBar = new MyMenuBar(drawPanel);
    private IconPanel iconPanel = new IconPanel(drawPanel);
    public MyFrame(String name) {
        super(name);
        setLayout(new BorderLayout()); 		// Set BorderLayout 
        setTitle("Bao Yifan's MiniCAD"); 	// Bao Yifan's MiniCAD
        addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (true) {
					int choice = JOptionPane.showConfirmDialog(null, "File has been modifided, save file?","Save file?", JOptionPane.YES_NO_CANCEL_OPTION);
					switch (choice) {
					case JOptionPane.YES_OPTION:
						drawPanel.saveFile();
						break;
					case JOptionPane.NO_OPTION:
						break;
					case JOptionPane.CANCEL_OPTION:
					case JOptionPane.CLOSED_OPTION:
						return;
					}
				}
				System.exit(0);
			}
		});
        setJMenuBar(menuBar);
        add(iconPanel,BorderLayout.EAST);
        add(drawPanel,BorderLayout.CENTER);
        setVisible(true);
    }

}
