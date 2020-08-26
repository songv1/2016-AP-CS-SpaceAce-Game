//this is a subclass of enemy
public class Alien extends Enemy {

    //class constructor
    //sets object at a position and initializes
    public Alien(int x, int y) {
        super(x, y);
        
        init();
    }
    
    //initializer: load image, get its dimensions
    private void init() {
 
        loadImage("alien.gif");
        getImageDimensions();
    }
 
}