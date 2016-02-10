package ziggy.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Main program.
 */
@SuppressWarnings("serial")
public class Program extends JFrame implements  Runnable, Constants {

  /**
   * Game instance.
   */
  private final Game game;

  
  /**
   * Auxillary image handle for double-buffering.
   */
  private final BufferedImage bufImg;
  
  /**
   * Auxiiliary graphics handle for double-buffering.
   */
  private final Graphics bufGraphics;
  
  /**
   * Constructor.
   * @throws IOException if an I/O error occurs
   */
  public Program() throws IOException  {
    // Call superclass constructor.
    super("Ziggy lost in space");
    this.game = new Game();
    bufImg = new BufferedImage(AREA_LENGTH, AREA_LENGTH + 50, BufferedImage.TYPE_INT_RGB);
    bufGraphics = bufImg.getGraphics();
    JPanel panel = new JPanel() {
      @Override
      public void paintComponent(Graphics g) {
        // super.paintComponent(g);
        bufGraphics.clearRect(0,0, AREA_LENGTH, AREA_LENGTH + 50);
        game.draw(bufGraphics);
        g.drawImage(bufImg, 0, 0, this);
      }
    };
    panel.setSize(AREA_LENGTH, AREA_LENGTH);
    add(panel, BorderLayout.CENTER);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(AREA_LENGTH, AREA_LENGTH+50));
    setResizable(false);
    addKeyListener(game);
  }

  /**
   * Implementation of Runnable interface.
   */
  @Override
  public final void run() {
    setVisible(true);
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        try { 
          Thread.sleep(REFRESH_PERIOD); 
        } 
        catch(InterruptedException e) { 

        }
        game.step();
        
        repaint(); 
        Toolkit.getDefaultToolkit().sync();
        SwingUtilities.invokeLater(this);
      }
    });
  }

  /**
   * Main program.
   * @param args One argument required: the level of the game to load
   * @throws IOException if an I/O error occurs 
   */
  public static void main(String[] args) throws IOException {
    Program p = new Program();
    SwingUtilities.invokeLater(p);
  }  
  
}