//import these to make program work
import java.lang.Math; 
import java.util.Random; 

//class for enemies in the game, subclass of sprite
public class Enemy extends Sprite {
    
    //fields for movement functions
    private final int INITIAL_X = 1440;
    private final int INITIAL_Y = 900; 
    private float theta = 0;
    private float radius = 5; 
    private Random rand = new Random(); 

    //enemy class constructor, setting enemy at a position
    //and initializing
    public Enemy(int x, int y) {
        super(x, y);
        
        initEnemy();
    }
    
    //initializer: load image for the enemy sprite
    //and get the image's dimensions
    private void initEnemy() {
        
        loadImage("alien.gif");
        getImageDimensions();
    }
 

    //move linearly
    public void move() {
 
        if (x < 0) {
            x = INITIAL_X;
            y = INITIAL_Y/2; 
        }
        
        x-= 4; 

        int num = rand.nextInt(5); 
        int sign = rand.nextInt(2); 
        if(sign < 1){
            y += num; 
        } else {
            y-= num; 
        }
        
     
    }


    //move circularly using a trig function
    public void movePol() {
        
       if (y > 900 || y < 0) {
            y = INITIAL_Y/2; 
        }

        if(x > 1440 || x < 0){
            x = INITIAL_X;
        } 

        y += radius*Math.sin(theta);
        x += radius*Math.cos(theta); 
        theta+=0.01;  

        //print this for debugging: 
        // System.out.println(theta + " " + x + " " + y); 

    }

    //move in a rosetta using a trig function
    public void moveRose() {
        
       if (y > 900 || y < 0) {
            y = 450; 
            
        }

        if(x > 1440 || x < 0){
            x = 720;
        } 

        y += radius*Math.sin(2*theta);
        x += radius*Math.cos(2*theta); 

        theta+=0.01; 

        // System.out.println(theta + " " + x + " " + y); 

    }

    //move in a cardioid pattern using trig function
    public void moveCardioid() {
        
       if (y > 900 || y < 0) {
            y = 450; 
            
        }

        if(x > 1440 || x < 0){
            x = 720;
        } 

        y += radius*Math.sin(theta*(1-Math.cos(theta)));
        x += radius*Math.cos(theta*(1-Math.cos(theta))); 

        theta+=0.01;  
        //System.out.println(theta + " " + x + " " + y); 

    }



}