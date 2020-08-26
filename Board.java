/*
        2/26/16
        Space Ace by Maya and Victoria

        INSTRUCTIONS: USE UP AND DOWN ARROW KEYS TO MOVE CHARACTER
                      PRESS SPACE BAR TO FIRE ARROWS AT ENEMIES
                      DEFEAT ALL THE ENEMIES WITHOUT DYING


        sources: http://zetcode.com/tutorials/javagamestutorial/
*/

//this is the board class. 
//the content of the game is put in here.
//import these so the program can run

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Image;
import javax.swing.ImageIcon;

//the class Board is a subclass of JPanel and implements
//the interface ActionListener (so it would respond to keyboard stuff)
public class Board extends JPanel implements ActionListener {


    private Image city; //for the background
    private Timer timer; //the timer for each move to go by
    private final int DELAY = 15; //delay for timer

    private Craft craft; //the craft you control; protagonist

    //these Array Lists hold the enemies
    private ArrayList < Alien > aliens;
    private ArrayList < Demon > demons;
    private ArrayList < Breloom > brelooms;
    private ArrayList < Grizzo > grizzos;
    private ArrayList < Togekiss > toges;
    private ArrayList < Alex > alexes;


    private boolean ingame; //determines whether you are still playing

    //dimensions of craft
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;

    //dimensions of game window
    private final int B_WIDTH = 720;
    private final int B_HEIGHT = 450;

    //image to be imported in construtor
    Image img;

    //how much health you have initially
    private int healthBar = 10000;

    //you start out with 0 points and level 1
    private int score = 0;
    private int level = 1;

    private final int[][] pos = {
        {
            2380,
            29
        },
        {
            2500,
            59
        },
        {
            1380,
            89
        },
        {
            780,
            109
        },
        {
            580,
            139
        },
        {
            680,
            239
        },
        {
            790,
            259
        },
        {
            760,
            50
        },
        {
            790,
            150
        },
        {
            980,
            209
        },
        {
            560,
            45
        },
        {
            510,
            70
        },
        {
            930,
            159
        },
        {
            590,
            80
        },
        {
            530,
            60
        },
        {
            940,
            59
        },
        {
            990,
            30
        },
        {
            920,
            200
        },
        {
            900,
            259
        },
        {
            660,
            50
        },
        {
            540,
            90
        },
        {
            810,
            220
        },
        {
            860,
            20
        },
        {
            740,
            180
        },
        {
            820,
            128
        },
        {
            490,
            170
        },
        {
            700,
            30
        }
    };

    //class constructor
    public Board() {

        initBoard();
        img = Toolkit.getDefaultToolkit().createImage("city.jpg");

    }


    //starts game
    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        ingame = true;

        setPreferredSize(new Dimension(1440, 900));

        craft = new Craft(ICRAFT_X, ICRAFT_Y);

        initAliens();
        initDemons();
        initBrelooms();
        initGrizzos();
        initToges();
        initAlexes();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    //these initializers put enemy objects in an array at a certain position
    public void initAliens() {
        aliens = new ArrayList < > ();

        for (int[] p: pos) {
            aliens.add(new Alien(p[0], p[1]));
        }

    }

    public void initDemons() {
        demons = new ArrayList < > ();

        for (int[] p: pos) {
            demons.add(new Demon(p[0], p[1]));
        }
    }

    public void initBrelooms() {
        brelooms = new ArrayList < > ();
        for (int[] p: pos) {
            brelooms.add(new Breloom(p[0], p[1]));
        }
    }

    public void initGrizzos() {
        grizzos = new ArrayList < > ();
        for (int[] p: pos) {
            grizzos.add(new Grizzo(p[0], p[1]));
        }
    }

    public void initToges() {
        toges = new ArrayList < > ();
        for (int[] p: pos) {
            toges.add(new Togekiss(p[0], p[1]));
        }
    }

    public void initAlexes() {
        alexes = new ArrayList < > ();
        for (int[] p: pos) {
            alexes.add(new Alex(p[0], p[1]));
        }
    }

    //the paint component to draw objects onto screen
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            g.drawImage(img, 0, 0, null);
            drawObjects(g);

        } else if(!ingame){
            if (healthBar == 0) {
                //if you lose, print "Game over"
                drawGameOver(g);
            } else {
                win(g);
            }
        }

        Toolkit.getDefaultToolkit().sync();
    }


    //draw objects onto the screen. 
    private void drawObjects(Graphics g) {
        //draw craft and missiles
        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY(),
                this);
        }

        ArrayList < Missile > ms = craft.getMissiles();

        for (Missile m: ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        //For each enemy drawn, only draw the next if all enemies
        //of one group are killed
        for (Alien a: aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        if (aliens.isEmpty()) {

            for (Demon d: demons) {
                if (d.isVisible()) {
                    g.drawImage(d.getImage(), d.getX(), d.getY(), this);
                    d.setVisible(true);
                }
            }
        }


        if (demons.isEmpty()) {
            for (Breloom b: brelooms) {
                if (b.isVisible()) {
                    g.drawImage(b.getImage(), b.getX(), b.getY(), this);
                }
            }
        }

        if (brelooms.isEmpty()) {
            for (Grizzo gr: grizzos) {
                if (gr.isVisible()) {
                    g.drawImage(gr.getImage(), gr.getX(), gr.getY(), this);
                }
            }
        }

        if (grizzos.isEmpty()) {
            for (Togekiss t: toges) {
                if (t.isVisible()) {
                    g.drawImage(t.getImage(), t.getX(), t.getY(), this);
                }
            }
        }

        if (toges.isEmpty()) {
            for (Alex ax: alexes) {
                if (ax.isVisible()) {
                    g.drawImage(ax.getImage(), ax.getX(), ax.getY(), this);
                }
            }
        }

        //show health, score, and level
        g.setColor(Color.WHITE);
        Font f = new Font("Courier", 10, 28);
        FontMetrics fm = getFontMetrics(f);
        g.setFont(f);
        g.drawString("Health: " + healthBar/5, 650, 45);
        g.drawString("Score: " + score, 5, 20);
        g.drawString("Level: " + level, 5, 50);
    }

    //print "game over" in large, white font
    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("DejaVu Sans - Plain", Font.BOLD, 28);
        FontMetrics fm = getFontMetrics(small);
        g.drawImage(img, 0, 0, null);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, B_WIDTH,
            B_HEIGHT);
    }

    //"print "you win" in large, white font
    private void win(Graphics g) {
        String msg = "You Win!";
        Font small = new Font("DejaVu Sans - Plain", Font.BOLD, 28);
        FontMetrics fm = getFontMetrics(small);
        g.drawImage(img, 0, 0, null);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, B_WIDTH,
            B_HEIGHT);

    }

    //update variables based on what key is pressed, 
    //and what function is called
    public void actionPerformed(ActionEvent e) {

        inGame();

        updateCraft();
        updateMissiles();

        updateAliens();
        updateDemons();
        updateBrelooms();
        updateGrizzos();
        updateToges();
        updateAlexes();

        checkCollisions();

        repaint();
    }

    //test if you are still playing the game 
    //and stop the timer. (stop action)
    private void inGame() {

        if (!ingame) {
            timer.stop();
        }
    }

    //update the craft position on screen if it is visible
    //based on the move() function in the subclass. 
    private void updateCraft() {

        if (craft.isVisible()) {
            craft.move();
        }
    }

    //update missile position on screen if it is visible.
    //else, remove the missile fom the array list. 
    private void updateMissiles() {

        ArrayList < Missile > ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    //for the following update enemy functions
    //if all the enemies are killed, set level = 2, 3, etc.
    //move the enemy and update the position based on its move function
    //in the subclass.
    //if the enemy is not visible, remove from array list
    private void updateAliens() {

        if (aliens.isEmpty()) {
            level = 2;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Alien a = aliens.get(i);
            if (a.isVisible()) {
                //a.movePolar();
                a.moveRose();
            } else {
                aliens.remove(i);
            }
        }
    }

    private void updateDemons() {

        if (demons.isEmpty()) {

            level = 3;
            return;
        }

        for (int i = 0; i < demons.size(); i++) {

            Demon d = demons.get(i);
            if (d.isVisible()) {
                //a.movePolar();
                d.movePol();
            } else {
                demons.remove(i);
            }
        }
    }

    private void updateBrelooms() {

        if (brelooms.isEmpty()) {
            level = 4;
            return;
        }

        for (int i = 0; i < brelooms.size(); i++) {

            Breloom b = brelooms.get(i);
            if (b.isVisible()) {
                //a.movePolar();
                b.moveCardioid();
            } else {
                brelooms.remove(i);
            }
        }

    }

    private void updateGrizzos() {

        if (grizzos.isEmpty()) {
            level = 5;
            return;
        }

        for (int i = 0; i < grizzos.size(); i++) {

            Grizzo gr = grizzos.get(i);
            if (gr.isVisible()) {
                //a.movePolar();
                gr.move();
            } else {
                grizzos.remove(i);
            }
        }

    }

    private void updateToges() {

        if (toges.isEmpty()) {
            level = 6;
            return;
        }

        for (int i = 0; i < toges.size(); i++) {

            Togekiss t = toges.get(i);
            if (t.isVisible()) {
                t.moveRose();
            } else {
                toges.remove(i);
            }
        }

    }

    //for this last enemy, set ingame to false.
    private void updateAlexes() {

        if (alexes.isEmpty()) {
            level = 7;
            ingame = false;
            return;
        }

        for (int i = 0; i < alexes.size(); i++) {

            Alex ax = alexes.get(i);
            if (ax.isVisible()) {
                ax.moveRose();
            } else {
                alexes.remove(i);
            }
        }

    }

    //check collisions
    public void checkCollisions() {
        //set a rectangle to check collision bounds
        Rectangle r3 = craft.getBounds();

        //for the following, check if enemy has hit the craft
        //decrease health because craft gets hurt by collision
        for (Alien alien: aliens) {
            Rectangle r2 = alien.getBounds();

            if (r3.intersects(r2)) {
                healthBar--;
                if (healthBar == 0) {
                    craft.setVisible(false);
                    alien.setVisible(false);
                    ingame = false;
                }
            }
        }

        if (aliens.isEmpty()) {
            for (Demon demon: demons) {
                Rectangle r2 = demon.getBounds();

                if (r3.intersects(r2)) {
                    healthBar--;
                    if (healthBar == 0) {
                        craft.setVisible(false);
                        demon.setVisible(false);
                        ingame = false;
                    }
                }
            }
        }

        if (demons.isEmpty()) {
            for (Breloom breloom: brelooms) {
                Rectangle r2 = breloom.getBounds();

                if (r3.intersects(r2)) {
                    healthBar--;
                    if (healthBar == 0) {
                        craft.setVisible(false);
                        breloom.setVisible(false);
                        ingame = false;
                    }
                }
            }
        }


        if (brelooms.isEmpty()) {
            for (Grizzo grizzo: grizzos) {
                Rectangle r2 = grizzo.getBounds();

                if (r3.intersects(r2)) {
                    healthBar--;
                    if (healthBar == 0) {
                        craft.setVisible(false);
                        grizzo.setVisible(false);
                        ingame = false;
                    }
                }
            }
        }

        if (grizzos.isEmpty()) {
            for (Togekiss toge: toges) {
                Rectangle r2 = toge.getBounds();

                if (r3.intersects(r2)) {
                    healthBar--;
                    if (healthBar == 0) {
                        craft.setVisible(false);
                        toge.setVisible(false);
                        ingame = false;
                    }
                }
            }
        }

        if (toges.isEmpty()) {
            for (Alex alex: alexes) {
                Rectangle r2 = alex.getBounds();

                if (r3.intersects(r2)) {
                    healthBar--;
                    if (healthBar == 0) {
                        craft.setVisible(false);
                        alex.setVisible(false);
                        ingame = false;
                    }
                }
            }
        }

        //now check if missile has hit enemy
        //if so, make the enemy disappear and add to score
        //also make the missile disappear
        ArrayList < Missile > ms = craft.getMissiles();

        for (Missile m: ms) {

            Rectangle r1 = m.getBounds();

            for (Alien alien: aliens) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    alien.setVisible(false);
                    score += 10;
                }
            }


            if (aliens.isEmpty()) {
                for (Demon demon: demons) {

                    Rectangle r2 = demon.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        demon.setVisible(false);
                        score += 20;
                    }
                }
            }

            if (demons.isEmpty()) {
                for (Breloom breloom: brelooms) {

                    Rectangle r2 = breloom.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        breloom.setVisible(false);
                        score += 30;
                    }
                }
            }

            if (brelooms.isEmpty()) {
                for (Grizzo grizzo: grizzos) {

                    Rectangle r2 = grizzo.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        grizzo.setVisible(false);
                        score += 40;
                    }
                }
            }

            if (grizzos.isEmpty()) {
                for (Togekiss toge: toges) {

                    Rectangle r2 = toge.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        toge.setVisible(false);
                        score += 50;
                    }
                }
            }

            if (toges.isEmpty()) {
                for (Alex alex: alexes) {

                    Rectangle r2 = alex.getBounds();

                    if (r1.intersects(r2)) {
                        m.setVisible(false);
                        alex.setVisible(false);
                    }

                }
            }
        }
    }


    //this is the TAdaptor class and it helps 
    //the game respond to key inputs
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}