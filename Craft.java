//import these to make program work
import java.awt.event.KeyEvent;
import java.util.ArrayList;

//subclass of sprite, craft is sprite for player to control
public class Craft extends Sprite {

    //fields for change in position and missiles to shoot
    private int dx;
    private int dy;
    private ArrayList < Missile > missiles;

    //class constructor setting position and initializing
    public Craft(int x, int y) {
        super(x, y);

        initCraft();
    }

    //initializer: creates new array list for missiles,
    //loads image, and gets dimensions of that image. 
    private void initCraft() {

        missiles = new ArrayList < > ();
        loadImage("craft.gif");
        getImageDimensions();
    }

    //moves the craft around by dy units. 
    //you can only move vertically
    public void move() {

        y += dy;

        if (y < 0) {
            y = 720;
        }

        if (y > 720) {
            y = 0;
        }
    }

    //gets the missiles in the arraylist
    public ArrayList getMissiles() {
        return missiles;
    }

    //whatever happens when you press a key
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //SPACE: shoot missiles
        if (key == KeyEvent.VK_SPACE) {
            fire();
        }

        //UP: move up
        if (key == KeyEvent.VK_UP) {
            dy = -10;
        }

        //DOWN: move down 
        if (key == KeyEvent.VK_DOWN) {
            dy = 10;
        }

    }

    //shoot missiles by adding missiles in front of the craft
    public void fire() {
        missiles.add(new Missile(x + width, y + height / 2));
    }

    //when you release a key, you stop moving
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();


        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }
}