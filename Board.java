import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Board extends JPanel implements ActionListener{
    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final int DOTSIZE = 10;
    private final int ALLDOT = WIDTH*HEIGHT;
    private final int RANDOMPOS =49;
    private final int DELAY = 100;
    private final int x[] = new int[ALLDOT];
    private final int y[] = new int[ALLDOT];
    private int dots;
    private int foodLoc_x;
    private int foodLoc_y;
    private boolean movingLeft = false;
    private boolean movingRight = true;
    private boolean movingUp = false;
    private boolean movingDown = false; 
    private boolean inGame = true;
    private Timer timer;
    private Image dot; 
    private Image food;
    private Image snakeHead; 
    
    public Board () {
        addKeyListener(new Adapter());
        setBackground(Color.black);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        loadImages();
        startGame();
    }
    
    private void loadImages(){
        ImageIcon dotPic = new ImageIcon("dot.png");
        ImageIcon foodPic = new ImageIcon("food.png");
        ImageIcon headPic = new ImageIcon("head.png");
        
        dot = dotPic.getImage();
        food = foodPic.getImage();
        snakeHead = headPic.getImage();
    }
    
    private void startGame(){
        dots = 5;
        
        for (int i=0; i<dots;i++){
            x[i] = 50-i*10;
            y[i] = 50;
        }
        
        newFoodLoc();
        
        timer = new Timer(DELAY,this);
        timer.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    private void draw(Graphics g){
        if (inGame == true){
            g.drawImage(food, foodLoc_x, foodLoc_y, this);
            for (int i=0; i<dots; i++){
                if (i==0){
                    g.drawImage(snakeHead, x[i], y[i], this);
                }else{
                    g.drawImage(dot, x[i], y[i], this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }else{
            gameOver(g);
        }
    }
    
    private void gameOver(Graphics g){
        String text = "Game Over";
        Font font = new Font("Times", Font.BOLD, 20);
        FontMetrics meter = getFontMetrics(font);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(text, (WIDTH - meter.stringWidth(text)) /2, HEIGHT/2);
    }
    
    private void foodEaten(){
        if((x[0]==foodLoc_x) && (y[0] == foodLoc_y)){
            dots= dots+2;
            newFoodLoc();
        }
    }
    
    private void move(){
        for (int i = dots; i>0 ; i--){
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        
        if (movingLeft == true){
            x[0] = x[0] - DOTSIZE;
        }
        
        if (movingRight == true){
            x[0] = x[0] + DOTSIZE;
        }
        
        if (movingUp == true){
            y[0] = y[0] - DOTSIZE; 
        }
        
        if (movingDown == true){
            y[0] = y[0] + DOTSIZE; 
        }
    }
    
    private void checkLose(){
        for (int i=dots;i>0;i--){
            if ( i>6 && x[0]==x[i] && y[0]==y[i] ) {
                inGame = false;
            }
        }
        
        if (y[0] >= HEIGHT || y[0] < 0 || x[0] >= WIDTH || x[0]<0){
            inGame = false;
            timer.stop();
        }
    }
    
    private void newFoodLoc(){
        int tempX = (int) (Math.random() * RANDOMPOS);
        int tempY = (int) (Math.random() * RANDOMPOS);
        foodLoc_x = tempX * DOTSIZE; 
        foodLoc_y = tempY * DOTSIZE; 
    }
    
    public void actionPerformed(ActionEvent e){
        if (inGame == true){
            foodEaten();
            checkLose();
            move();
        }
        
        repaint();
    }
    
    private class Adapter extends KeyAdapter{
        public void keyPressed (KeyEvent e){
            int key = e.getKeyCode();
            if (key==KeyEvent.VK_LEFT && movingRight != true){
                 movingLeft = true;
                 movingUp = false;
                 movingDown = false;
            }
            if (key==KeyEvent.VK_RIGHT && movingLeft != true){
                 movingRight = true;
                 movingUp = false;
                 movingDown = false;
            }
            if (key==KeyEvent.VK_UP && movingDown != true){
                 movingRight = false;
                 movingLeft = false;
                 movingUp = true;
            }
            if (key==KeyEvent.VK_DOWN && movingUp != true){
                 movingRight = false;
                 movingLeft = false;
                 movingDown = true;
            }
        }
    }
}