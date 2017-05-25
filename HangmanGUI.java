import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.BorderLayout;
    
public class HangmanGUI{
    private Display display;
    private ArrayList<JButton> alphabetButtonsList = new ArrayList<JButton>();
    private JButton nextGameButton;
    private JButton giveUpButton;
    
    private String displayMessage;
    private String[] wordList = {"Banana","Apple"};
    private String unknownWord;
    private String guesses; //contains letters which user has guessed
    private boolean gameOver;
    private int badGuesses; //all incorrect guesses (Number of times)
    
    public HangmanGUI(){ //all buttons, displays, etc created here
        ButtonHandler buttonHandler = new ButtonHandler();
        display = new Display();
        JPanel bottomPanel = new JPanel();
        setLayout(new BorderLayout(3,3));
        add(display,BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        nextGameButton = new JButton("Next Word");
        giveUpButton = new JButton("Give Up");
        JButton quitButton = new JButton("Quit");
        
        nextGameButton.addActionListener(buttonHandler);
        giveUpButton.addActionListener(buttonHandler);
        quitButton.addActionListener(buttonHandler);
        
        bottom.add(nextGameButton);
        bottom.add(giveUpButton);
        bottom.add(quitButton);
        
        startGame();
    }
    
    private class Display extends JPanel{
        Display(){ 
            setPreferredSize(new Dimension (500,500)); //same as snake
            setBackground(new Color(250,230,180)); //change to adjust color
            setFont(new Font("Arial",Font.BOLD,20));
        }
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            ((Graphics2D)g).setStroke(new BasicStroke(3));
            if (displayMessage!=null){
                g.setColor(Color.YELLOW); //color of string
                g.drawString(message,30,40); //alter to fit screen
            }
        }
    }
    
    private class ButtonHandler implements ActionListener{
        public void actionPerformed(ActionEvent event){
            JButton clickedButton = (JButton)event.getSource();
            String command = event.getActionCommand();
            if(command.equals("Quit")){
                System.exit(0);
            }else{
                display = "The command is " + command;
            }
            display.repaint(); //redraw to show message
        }
    }
    
    private void startGame(){
        gameOver = false;
        guesses = "";
        badGuesses = 0;
        nextButton.setEnabled(false); //only true when word is finished
        giveUpButton.setEnabled(true);
        
        for(int i=0;i<alphabetButtons.size();i++){
            alphabetButtons.get(i).setEnabled(true);
        }
        
        int index = (int)(Math.random()*wordlist.length); //generates index of word array
        word = wordList[index];
        word = word.toUpperCase();
        message = "The word has" + word.length() + "letters.";
    }
    
    private boolean wordIsComplete(){
        for(int i=0; i<word.length();i++){
            char c = word.charAt(i);
            if (guesses.indexOf(c) == -1){
                return false;
            }
            return true;
        }
    }
}