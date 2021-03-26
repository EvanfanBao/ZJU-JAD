import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/* DrawPanel.class */
/* Description: event handling and draw geometry obejcts */
class DrawPanel extends JPanel {
    private static final long serialVersionUID = 1;
    private ShapeContainer shapeContainer;      
    private MyShape shapeChosen;
    private Color colorChosen = Color.BLACK; // Default color
    private String toolType = "SELECT";      // Default toolType

    private int pressedX, currentX, pressedY, currentY;  // Mouse position
    public DrawPanel(ShapeContainer shapeContainer) {
        this.shapeContainer = shapeContainer;
        // Key event handling for sizeUp(), sizeDown(), strokeUp(), strokeDown(), remove()
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if(shapeChosen == null) {
                    return;
                }
                boolean shapeChanged;
                switch (e.getKeyCode()) {
                case KeyEvent.VK_MINUS:
                case KeyEvent.VK_D:
                    shapeChanged = shapeChosen.sizeDown();
                    break;
                case KeyEvent.VK_EQUALS:
                case KeyEvent.VK_U:
                    shapeChanged = shapeChosen.sizeUp();
                    break;
                case KeyEvent.VK_OPEN_BRACKET:
                case KeyEvent.VK_S:
                    shapeChanged = shapeChosen.strokeDown();
                    break;
                case KeyEvent.VK_CLOSE_BRACKET:
                case KeyEvent.VK_W:
                    shapeChanged = shapeChosen.strokeUp();
                    break;
                case KeyEvent.VK_BACK_SPACE:
                    shapeContainer.remove(shapeChosen);
                    shapeChosen = null;
                    shapeChanged = true;
                    break;
                default:
                    return;
                }
                if(shapeChanged) {
                    repaint();
                }
            }
        });   
        // Mouse event handling for SELECT, ELLIPSE, LINE, RECTANGLE, TEXT
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(e.getButton() != MouseEvent.BUTTON1) {
                    return ;
                }
                String toolType = getToolType();
                if(toolType == "SELECT") {
                    shapeChosen = getShape(e.getX(), e.getY());
                    System.out.println("in choosen");
                    if(shapeChosen == null) {
                        return;
                    }
                    else System.out.println("shape chosen");
                    pressedX = currentX = e.getX();
                    pressedY = currentY = e.getY();
                }
                else {
                    pressedX = currentX = e.getX();
                    pressedY = currentY = e.getY();
                    switch(toolType) {
                    case "ELLIPSE":
                        shapeChosen = new MyEllipse(pressedX, pressedY, colorChosen);
                        break;
                    case "LINE":
                        shapeChosen = new MyLine(pressedX, pressedY, colorChosen);
                        break;
                    case "RECTANGLE":
                        shapeChosen = new MyRectangle(pressedX, pressedY, colorChosen);
                        break;
                    case "TEXT":
                        String text = JOptionPane.showInputDialog("Please input text: ");
                        if(text != null && !text.isEmpty()) {
                            shapeChosen = new MyText(text, pressedX, pressedY, colorChosen);
                            break;
                        }
                    default:
                        return;
                    }
                    shapeContainer.add(shapeChosen);
                    repaint();
                }
            }

            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        });
        // Mouse motion event handling for shapeDragged(), move()
        addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) { 
                String toolType = getToolType();
                if (toolType == "SELECT") {
                    if(shapeChosen == null) {
                        return;
                    }
                    currentX = e.getX();
                    currentY = e.getY();
                    shapeChosen.move(currentX - pressedX, currentY - pressedY);
                    pressedX = currentX;
                    pressedY = currentY;
                } 
                else {
                    switch (toolType) {
                    case "ELLIPSE":
                    case "LINE":
                    case "RECTANGLE":
                        currentX = e.getX();
                        currentY = e.getY();
                        shapeChosen.shapeDragged(pressedX, pressedY, currentX, currentY);
                        break;
                    default:
                        return;
                    }
                }
                repaint();     
            }
            public void mouseMoved(MouseEvent e) {
                requestFocus();
            }
        });
    }
    // paint function() override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        shapeContainer.drawShapes(g2);
    }
    
    // gets & sets
    MyShape getShape(int x, int y) {
        return shapeContainer.getShape(x, y);
    }
    public void setToolType(String toolType) {
        this.toolType = toolType;
    }
    public String getToolType() {
        return toolType;
    }
    public void setColor(Color color) {
        this.colorChosen = color;
        this.shapeChosen.setColor(color);
        repaint();
    }
    // readFile & saveFile
    public void readFile() {
        shapeContainer.readFile();
        repaint();
    }
    public void saveFile() {
        shapeContainer.saveFile();
    }
    // exit system
    public void exit() {
        System.exit(0);
    }
}