import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/* MyMenuBar.java */
/* Description: the menubar */
public class MyMenuBar extends JMenuBar {
    private static final long serialVersionUID = 1L;
    
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem openFile = new JMenuItem("Open File");
    private JMenuItem saveFile = new JMenuItem("Save File");
    private JMenuItem closeWindow = new JMenuItem("Close");

	private JMenu aboutMenu = new JMenu("About");
	private JMenuItem help = new JMenuItem("Help");
	private JMenuItem about = new JMenuItem("About");
    {
        fileMenu.addSeparator();
        fileMenu.add(openFile);
        fileMenu.addSeparator();
        fileMenu.add(saveFile);
        fileMenu.addSeparator();
        fileMenu.add(closeWindow);
        aboutMenu.add(about);
        aboutMenu.addSeparator();
        aboutMenu.add(help);
        add(fileMenu);
        add(aboutMenu);
    } 
    public MyMenuBar(DrawPanel drawPanel) {  
        openFile.addActionListener(e -> drawPanel.readFile());
        saveFile.addActionListener(e -> drawPanel.saveFile());
        closeWindow.addActionListener(e -> drawPanel.exit());
        
        about.addActionListener(new ActionListener( ) {
            public void actionPerformed(ActionEvent e) {
                String message = "Application: miniCAD\n" + "Author: Bao Yifan\n" + "SID: 3180103499";
                JOptionPane.showMessageDialog(null,message, "About", JOptionPane.PLAIN_MESSAGE);                    
            }
        });
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = "SizeUp: +\n"
                    + "SizeDown: -\n" 
                    + "StrokeUp: ]\n"
                    + "StrokeDown: [\n"
                    + "Delete Instance: BackSpace";
                JOptionPane.showMessageDialog(null, message, "Help", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
    
}
