//missile class, a subclass of sprite
public class Missile extends Sprite {
    
    //fields for speed and the width of board
    private final int BOARD_WIDTH = 1440;
    private final int MISSILE_SPEED = 15;
    
    //class constructor: sets missile at a position
    //and initializes
    public Missile(int x, int y) {
        super(x, y);
 
        initMissile();
    }

    //initializes by loading an image and getting dimensions
    private void initMissile() {
         
        loadImage("missile.png");
        getImageDimensions();        
    }
    
    //moving linearly, at MISSILE_SPEED
    //if it goes off the screen, set it invisible
    public void move() {
         
        x += MISSILE_SPEED;
         
        if (x > BOARD_WIDTH)
            vis = false;
    }
}