import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;

/* IconPanel.java */
/* Description: the Icon panel in the right, including button event handling */
public class IconPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private final String[] buttonTypes = {"line", "ellipse", "rectangle","text", "cursor"}; // The 5 button types
    private final int BUTTONNUM = buttonTypes.length;
    private DrawPanel drawPanel;
    private JButton[] buttons = new JButton[BUTTONNUM];
    private ImageIcon[] icons = new ImageIcon[BUTTONNUM];
    private final String PATH = "./images/";        // The image path
    // ActionListener for different buttons, set differnt tool for draw panel
    private ActionListener buttonListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
            case "line":
                setTool("LINE");
                break;
            case "ellipse":
                setTool("ELLIPSE");
                break;
            case "rectangle":
                setTool("RECTANGLE");
                break;
            case "text":
                setTool("TEXT");
                break;
            case "cursor":
                setTool("SELECT");
            default:
                break;
            }
        }
    };
    // Constructor
    public IconPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
        this.setLayout(new GridLayout(BUTTONNUM + 1,0));   // GridLayout is used
        Dimension size = new Dimension(50, 50);
        // Set buttons
        for(int i = 0; i < BUTTONNUM; i++) {
            buttons[i] = new JButton();
            buttons[i].setActionCommand(buttonTypes[i]);
            buttons[i].setPreferredSize(size);
            buttons[i].addActionListener(buttonListener);
            icons[i] = new ImageIcon(PATH+buttonTypes[i]+".png");
            icons[i].setImage(icons[i].getImage().getScaledInstance(size.width, size.height, Image.SCALE_DEFAULT));
            buttons[i].setIcon(icons[i]);
            add(buttons[i]);
        }
        add(new ColorPanel());
    }
    public void setTool(String toolType) {
        drawPanel.setToolType(toolType);
    }
    /* ColorPanel class */
    /* Description: used to change color of selected object and objects followd */
    private class ColorPanel extends JPanel{
        private static final long serialVersionUID = 1L;
        private final Color[] colorArray = {
            Color.BLACK, Color.RED, Color.YELLOW, Color.PINK,
            Color.GREEN, Color.CYAN, Color.GRAY, Color.BLUE, Color.ORANGE
        };
        private final int COLORNUM = colorArray.length;
        private colorButton[] colorButtons = new colorButton[COLORNUM]; 
        ColorPanel() {
            setPreferredSize(new Dimension(100,100));
            int row = (int)Math.sqrt(COLORNUM);  //这里就是可开方的形式
            setLayout(new GridLayout(row,row)); 
            // Set color buttons
            for(int i = 0; i < COLORNUM; i++) {
                colorButtons[i] = new colorButton(colorArray[i]);
                colorButtons[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Color color = ((JButton) e.getSource()).getBackground(); 
                        drawPanel.setColor(color);  
                    }
                });
                add(colorButtons[i]);
            }
        }
        private class colorButton extends JButton {
            private static final long serialVersionUID = 1L;
            colorButton(Color color) {
            setBackground(color);
            setOpaque(true);
            setBorderPainted(false);
            setVisible(true);
            }
        }   
    }
}