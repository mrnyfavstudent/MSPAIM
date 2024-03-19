import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;
/**
 * Draw line by using mouse.
 *
 * @author Tracy 
 * @version 15/03/24
 */
public class Drawer
{
    // instance variables 
    private double startX, startY; // fields to remeber "pressed position 
    private Color currentColor = Color.black; // defalt colour 
    private boolean cricle = true; // change crilce to rec 
    private double finalX, finalY; // fields to remeber "pressed position 
    private boolean line = true; // line across makes a rectangle 
    private String text; 
    

    /**
     * Constructor for objects of class lineDrawer
     */
    public Drawer()
    {
       // instance variables 
       UI.setLineWidth(10);
       UI.addButton("Random Colour", this::randomColour); // another button
       UI.addButton("Colour", this::doChooseColor);
       UI.addButton("Cricle or Square", this::dochangeshape);
       UI.addButton("line to rectangle", this::dochangeline);
       UI.addTextField("write somthing", this::doText);
       UI.addButton("Quit", UI::quit);
       UI.setMouseListener(this::doMouse);
       UI.addSlider("get thicky wicky", 10, 50, 20, this::doThickie);
    }
    
    /**
     * Call back method for text  
     * allows for the user to write somthing in 
     */
    public void doText(String s) {
        // the parameter s contains what the user entered into the textfield and presses enter
        text = s;
        System.out.println(s); 
        
    }
    
     /**
     * Call back method for clour  
     * creates the width of the line thingy  
     */
    public void doThickie(double value){
        UI.setLineWidth(value);
    }
    
    /**
     * Call back method for clour  
     * creates a colour 
     */
    public void randomColour(){
        Color col = new Color((float) Math.random(), (float) Math.random(), (float) Math.random());
        UI.setColor(col);
    }
    
    /**
     * Call back the colors #freedom 
     */
    public void doChooseColor(){
        this.currentColor = JColorChooser.showDialog(null, "Color", this.currentColor);
        UI.setColor(this.currentColor);
    }
    
    /**
     * call back and changes shape between square and cricle 
     */
    public void dochangeshape(){
        if(cricle == true){
            cricle = false;
        }
        else if(cricle == false){
            cricle = true; 
        }
   
    }
    
    /**
     * call back and changes shape between square and cricle 
     */
    public void dochangeline(){
        if(line == true){
            line = false;
        }
        else if(line == false){
            line = true; 
        }
   
    }

    /**
     * Call back method for mouse 
     * draws a line 
     */
    public void doMouse(String action, double x, double y){
        if(action.equals("pressed")){
            //store the pressed position 
            this.startX = x;
            this.startY = y;
            UI.drawLine(this.startX, this.startY, x, y);
        } else if(action.equals("released")&&(line == true)){
            UI.drawLine(this.startX, this.startY, x, y);//draw line 
        }else if(action.equals("released")&&(line == false)){
            this.finalX = x;
            this.finalY = y; 
            UI.fillRect(this.startX, this.startY,this.finalX-this.startX, this.finalY-this.startY);// create a rectangle
        }else if(action.equals("clicked")&&(cricle == true)){
            UI.fillOval(x-50/2, y-50/2, 50, 50); //draw a cricle on click without the little square 
        }else if(action.equals("clicked")&&(cricle == false)){
            UI.fillRect(x-50/2, y-50/2, 50, 50); //draw a cricle on click without the little square  
        } 
    }
}