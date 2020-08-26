//this is a subclass of enemy
public class Grizzo extends Enemy {
    //class constructor
    //sets object at a position and initializes
    public Grizzo(int x, int y) {
        super(x, y);
        
        init();
    }
     //initializer: load image, get its dimensions
    private void init() {
 
        loadImage("grizzo.gif");
        getImageDimensions();
    }
 
   

}