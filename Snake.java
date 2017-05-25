import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame{
    public Snake () {
        add(new Board());
        //setRezsizable(false);
        pack();
        setTitle("SNAKE by OSCAR SO");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main (String[] args){
        EventQueue.invokeLater(new Runnable (){
            public void run(){
                JFrame k= new Snake();
                k.setVisible(true);
            }
        });
    }
}