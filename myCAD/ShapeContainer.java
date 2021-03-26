
import java.awt.Graphics2D;
import java.util.ArrayList;

import java.awt.*;
import javax.swing.JFileChooser;
import java.io.*;

/* ShapeContainer.java */
/* Description: hold shapes and paint */
public class ShapeContainer {
    private ArrayList<MyShape> shapeList = new ArrayList<MyShape>();    // Container for MyShape
    public void drawShapes(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);  //抗锯齿
        for(MyShape shape : shapeList) {
            shape.Draw(g);
        }
    }
    public void add(MyShape shape) {
        if(shape != null && shape.getShapeCato() != ShapeCate.UNDEFINED) {
            shapeList.add(shape);
        }
    }
    public void remove(MyShape shape) {
        if(shape != null && shape.getShapeCato() != ShapeCate.UNDEFINED) {
            shapeList.remove(shape);
        }
    }
    public void clear() {
        shapeList.clear();
    }
    public int getNumber() {
        return shapeList.size();
    }
    public MyShape getShape(int x, int y) {
        for(MyShape shape : shapeList) {
            if(shape instanceof MyLine) {
                if(((MyLine)shape).nearLine(x, y))
                    return shape;
            } ///  先检查直线 再检查其他的
            else if(shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }
    // read objects from file to container
    public void readFile() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(null);
        File f = fc.getSelectedFile();
        try {
            FileInputStream file = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(file);
            Integer shapeNum = (Integer)in.readObject();
            shapeList.clear();
            for(int i=0;i<shapeNum;i++){
                MyShape tmp=(MyShape)in.readObject();
                if(tmp!=null){
                    if(tmp instanceof MyLine){
                        shapeList.add((MyLine)tmp);
                    }
                    else if(tmp instanceof MyRectangle){
                        shapeList.add((MyRectangle)tmp);
                    }
                    else if(tmp instanceof MyEllipse){
                        shapeList.add((MyEllipse)tmp);
                    }
                    else if(tmp instanceof MyText){
                        shapeList.add((MyText)tmp);
                    }
                    else{
                        System.out.println("ERROR: File is not parsable");
                    }
                }
            }
            in.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // write objects from container to file
    public void saveFile() {
        JFileChooser fc = new JFileChooser();
        fc.showSaveDialog(null);
        File f=fc.getSelectedFile();
        FileOutputStream file;
        try{
            file = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(shapeList.size());
            for(MyShape s : shapeList){
                out.writeObject(s);
            }
            out.close();
            file.close();
        }catch(IOException e){
            e.printStackTrace();
        }    
    } 
}
