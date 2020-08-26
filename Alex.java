//this is a subclass of enemy
public class Alex extends Enemy {
    //class constructor
    //sets object at a position and initializes
    public Alex(int x, int y) {
        super(x, y);

        init();
    }

    //initializer: load image, get its dimensions
    private void init() {

        loadImage("alexander.png");
        getImageDimensions();
    }


}