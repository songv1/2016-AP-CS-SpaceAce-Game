/*
used this as reference: http://zetcode.com/tutorials/javagamestutorial/movingsprites/

Purpose of this program: this program is a game incorporating polar coordinate functions
for the movement of enemy sprites. We learned polar coordinate functions in our precalculus
class, so we think it is relevant to Choate's precaculus students. 
*/
//import these to make program work
import java.awt.EventQueue;
import javax.swing.JFrame;

//put this class in the command line.
//it will run the game in a window
public class CollisionEx extends JFrame {

    //class constructor
    public CollisionEx() {

        initUI();
    }

    //initializes a window for the game to run on
    private void initUI() {

        //adding game content
        add(new Board());

        //cannot resize
        setResizable(false);
        pack();

        //title of window
        setTitle("Collision");

        setLocationRelativeTo(null);

        //press X to exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //main driver
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CollisionEx ex = new CollisionEx();
                ex.setVisible(true);
            }
        });
    }
}