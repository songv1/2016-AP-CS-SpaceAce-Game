//this is a subclass of enemy
public class Demon extends Alien {
    //class constructor
    //sets object at a position and initializes
    public Demon(int x, int y) {
        super(x, y);
        
        init();
    }
     //initializer: load image, get its dimensions
    private void init() {
 
        loadImage("demon.gif");
        getImageDimensions();
    }
 

}