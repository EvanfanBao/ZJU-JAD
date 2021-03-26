/* Main.java */
/* Description: create GUI, let Frame to handle */
import java.awt.*;

public class Main {
    private static void createGUI() {
        MyFrame frame = new MyFrame("myFrame");
        frame.setSize(new Dimension(500,500));  // Set the frame size
    }
    public static void main(String[] args){
        createGUI();
    }  
}
