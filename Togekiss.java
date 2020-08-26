//this is a subclass of enemy
public class Togekiss extends Enemy {
        //class constructor
    //sets object at a position and initializes
    public Togekiss(int x, int y) {
        super(x, y);
        
        init();
    }
    //initializer: load image, get its dimensions 
    private void init() {
 
        loadImage("togekiss.gif");
        getImageDimensions();
    }
 
   

}