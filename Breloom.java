//this is a subclass of enemy
public class Breloom extends Enemy {
  
    //class constructor
    //sets object at a position and initializes
    public Breloom(int x, int y) {
        super(x, y);
        
        init();
    }
     //initializer: load image, get its dimensions
    private void init() {
 
        loadImage("breloom.png");
        getImageDimensions();
    }
 
   

}