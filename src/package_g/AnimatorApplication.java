package package_g;

import java.awt.*;

/* 
 * Based on Arthur van Hoff's animation examples, this application
 * can serve as a template for all animation applications.
 */
public class AnimatorApplication extends Frame implements Runnable {
    int frameNumber = -1;
    int delay;
    Thread animatorThread;
    boolean frozen = false;

    AnimatorApplication(int fps, String windowTitle) {
        super(windowTitle);
        delay = (fps > 0) ? (1000 / fps) : 100;
    }

    public void startAnimation() {
        //Create and start the animating thread.
        if (frozen) {
            //Do nothing.  The user has requested that we
            //stop changing the image.
        } else {
            //Start animating!
            if (animatorThread == null) {
                animatorThread = new Thread(this);
            }
            animatorThread.start();
        }
    }

    public void stopAnimation() {
        //Stop the animating thread.
        animatorThread = null;
    }

    public boolean mouseDown(Event e, int x, int y) {
        if (frozen) {
            frozen = false;
            startAnimation();
        } else {
            frozen = true;
            stopAnimation();
        }
        return true;
    }


    public boolean handleEvent(Event e) {
        switch (e.id) {
          case Event.WINDOW_ICONIFY:
            stopAnimation();
            break;
          case Event.WINDOW_DEICONIFY:
            startAnimation();
            break;
          case Event.WINDOW_DESTROY:
            System.exit(0);
            break;
        }  
        return super.handleEvent(e);
    }

    public void run() {
        //Just to be nice, lower this thread's priority
        //so it can't interfere with other processing going on.
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        //Remember the starting time
        long startTime = System.currentTimeMillis();

        //This is the animation loop.
        while (Thread.currentThread() == animatorThread) {
            //Advance the animation frame.
            frameNumber++;

            //Display it.
            repaint();

            //Delay depending on how far we are behind.
            try {
                startTime += delay;
                Thread.sleep(Math.max(0, 
                                      startTime-System.currentTimeMillis()));
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    //Draw the current frame of animation.
    public void paint(Graphics g) {
        g.drawString("Frame " + frameNumber, 5, 50);
    }

    public static void main(String args[]) {
        AnimatorApplication animator = null;
        int fps = 10;

        // Get frames per second from the command line argument
        if (args.length > 0) {
            try {
                fps = Integer.parseInt(args[0]);
            } catch (Exception e) {}
        }
        animator = new AnimatorApplication(fps, "Animator");
        animator.resize(200, 60);
        animator.show();
        animator.startAnimation();
    }
}