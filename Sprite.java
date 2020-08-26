//import these to make program work
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
 

//this is a class for the general sprite in the game
public class Sprite {
    
    //fields for position, dimensions, visibility, and image
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;
    
    //class constructor setting x,y positions
    //making sprite visible
    public Sprite(int x, int y) {
 
        this.x = x;
        this.y = y;
        vis = true;
    }
    
    //getting dimensions of image
    protected void getImageDimensions() {
 
        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    //loading the image for the sprite
    protected void loadImage(String imageName) {
 
        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
    }
    
    //getting the image for the sprite
    public Image getImage() {
        return image;
    }
    
    //getting the x position
    public int getX() {
        return x;
    }
    
    //getting the y position
    public int getY() {
        return y;
    }
    
    //telling whether sprite is visible
    public boolean isVisible() {
        return vis;
    }
    
    //setting sprite to visible
    public void setVisible(Boolean visible) {
        vis = visible;
    }
    
    //setting bounds for the sprite
    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}