package package_g;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Lorentz extends JFrame implements Runnable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    int frameNumber = -1;
    int delay;
    Thread animatorThread;
    boolean frozen = false;
    
   
    
    public void startAnimation() {
            if (animatorThread == null) {
                animatorThread = new Thread(this);
            }
            animatorThread.start();
        }
  
    
    public void run() {
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        long startTime = System.currentTimeMillis();
        while (Thread.currentThread() == animatorThread) {
            frameNumber++;
            repaint();
            try {
                startTime += delay;
                Thread.sleep(Math.max(0, 
                                      startTime-System.currentTimeMillis()));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
    
    public void paint(Graphics g) {
	g.setColor(Color.RED);
        g.fillRect(20, 150, 240, 15);
        g.setColor(Color.BLUE);
            
        g.fillRect(frameNumber, 185, 240, 30);
	
        g.drawString("Frame " + frameNumber, 5, 50);
    } 
    
    public static void main(String[] args) {
	
	AnimatorApplication animator = null;
        int fps = 10;

        // Get frames per second from the command line argument
        if (args.length > 0) {
            try {
                fps = Integer.parseInt(args[0]);
            } catch (Exception e) {}
        }
        animator = new AnimatorApplication(fps, "Animator");
        animator.setSize(337, 337);
        animator.show();
        animator.startAnimation();
	
       /* SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }
        }
        
        );*/
    }

    /*
    private static void createAndShowGUI() {
        System.out.println("Created GUI on EDT? "+
        SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Swing Paint Demo");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }

}

class MyPanel extends JPanel {

   
    private static final long serialVersionUID = 1L;
    private int squareX = 50;
    private int squareY = 50;
    private int squareW = 20;
    private int squareH = 20;
    
    int x = -240;

    public MyPanel() {

        setBorder(BorderFactory.createLineBorder(Color.RED));
        setBackground(Color.BLACK);
    }
    
    private void moveSquare(int x, int y) {
        int OFFSET = 1;
        if ((squareX!=x) || (squareY!=y)) {
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
            squareX=x;
            squareY=y;
            repaint(squareX,squareY,squareW+OFFSET,squareH+OFFSET);
        } 
    }
    

    public Dimension getPreferredSize() {
        return new Dimension(337,337);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        for(int i=2; i>1; i++){
        g.setColor(Color.RED);
        g.fillRect(20, 150, 240, 15);
        g.setColor(Color.BLUE);
        
            System.out.println(x + "");
            if (x > 337) {
        	x = -240;
            }
            x=x+1;
            try {
		Thread.sleep(50);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
            
        g.fillRect(x, 185, 240, 30);
       }
        
        
    }  */
}