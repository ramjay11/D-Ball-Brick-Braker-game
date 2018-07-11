package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
  private boolean play = false;
  private int score = 0;
  private int totalBricks = 21;
  private Timer timer;
  private int delay = 8;
  private int playerX = 310;
  private int ballposX = 120;
  private int ballposY = 350;
  private int ballXdir = -1;
  private int ballYdir = -2;
  private MapGenerator map;


  
  public Gameplay()
  {
    this.map = new MapGenerator(3, 7);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    this.timer = new Timer(this.delay, this);
    this.timer.start();
  }
  
  public void paint(Graphics g)
  {
    g.setColor(Color.black);
    g.fillRect(1, 1, 692, 592);
    
    this.map.draw((Graphics2D)g);
    
    g.setColor(Color.blue);
    g.fillRect(0, 0, 3, 592);
    g.fillRect(0, 0, 692, 3);
    g.fillRect(691, 0, 3, 592);
    
    g.setColor(Color.white);
    g.setFont(new Font("serif", 1, 25));
    g.drawString(""+score, 590, 30);
    g.setColor(Color.green);
    g.fillRect(this.playerX, 550, 100, 8);
    
    g.setColor(Color.red);
    g.fillOval(this.ballposX, this.ballposY, 20, 20);
    if (this.totalBricks == 21)
    {
      g.setColor(Color.white);
      g.setFont(new Font("Matura MT Script", 1, 15));
      g.drawString("Game Created By Ramjay Romorosa ©", 10, 495);
    }
    g.setColor(Color.white);
    g.setFont(new Font("lucida handwriting", 1, 50));
    g.drawString("D' Ball Brick Breaker ", 10, 300);
    if (this.totalBricks <= 0)
    {
      this.play = false;
      this.ballXdir = 0;
      this.ballYdir = 0;
      g.setColor(Color.red);
      g.setFont(new Font("serif", 1, 30));
      g.drawString("You Won, How Surprising It Is", 170, 350);
      
      g.setFont(new Font("serif", 1, 20));
      g.drawString("Press Enter to Play Again You Piece Of Sh*t", 175, 400);
    }
    if (this.ballposY > 570)
    {
      this.play = false;
      this.ballXdir = 0;
      this.ballYdir = 0;
      g.setColor(Color.red);
      g.setFont(new Font("serif", 1, 30));
      g.drawString("Game Over. You Suck, F*cker ", 170, 350);
      
      g.setFont(new Font("serif", 1, 20));
      g.drawString("Press Enter to Play Again You Piece Of Sh*t", 173, 400);
    }
    if (this.totalBricks == 21)
    {
      g.setColor(Color.green);
      g.setFont(new Font("jokerman", 1, 20));
      g.drawString("Press Left & Right Arrow Keys To Play The Game, You Dumbass ", 10, 350);
    }
    g.dispose();
  }
  
  public void actionPerformed(ActionEvent e)
  {
    this.timer.start();
    if (this.play)
    {
      if (new Rectangle(this.ballposX, this.ballposY, 20, 20).intersects(new Rectangle(this.playerX, 550, 100, 8))) {
        this.ballYdir = (-this.ballYdir);
      }
      for (int i = 0; i < this.map.map.length; i++) {
        for (int j = 0; j < this.map.map[0].length; j++) {
          if (this.map.map[i][j] > 0)
          {
            int brickX = j * this.map.brickWidth + 80;
            int brickY = i * this.map.brickHeight + 50;
            int brickWidth = this.map.brickWidth;
            int brickHeight = this.map.brickHeight;
            
            Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
            Rectangle ballRect = new Rectangle(this.ballposX, this.ballposY, 20, 20);
            Rectangle brickRect = rect;
            if (ballRect.intersects(brickRect))
            {
              this.map.setBrickValue(0, i, j);
              this.totalBricks -= 1;
              this.score += 5;
              if ((this.ballposX + 19 <= brickRect.x) || (this.ballposX + 1 >= brickRect.x + brickRect.width))
              {
                this.ballXdir = (-this.ballXdir);
                break;
              }
              this.ballYdir = (-this.ballYdir);
              
              break;
            }
          }
        }
      }
      this.ballposX += this.ballXdir;
      this.ballposY += this.ballYdir;
      if (this.ballposX < 0) {
        this.ballXdir = (-this.ballXdir);
      }
      if (this.ballposY < 0) {
        this.ballYdir = (-this.ballYdir);
      }
      if (this.ballposX > 670) {
        this.ballXdir = (-this.ballXdir);
      }
    }
    repaint();
  }
  
  public void keyTyped(KeyEvent e) {}
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyPressed(KeyEvent e)
  {
    if (e.getKeyCode() == 39) {
      if (this.playerX >= 600) {
        this.playerX = 600;
      } else {
        moveRight();
      }
    }
    if (e.getKeyCode() == 37) {
      if (this.playerX < 10) {
        this.playerX = 10;
      } else {
        moveLeft();
      }
    }
    if ((e.getKeyCode() == 10) && 
      (!this.play))
    {
      this.play = true;
      this.ballposX = 120;
      this.ballposY = 350;
      this.ballXdir = -1;
      this.ballYdir = -2;
      this.playerX = 310;
      this.score = 0;
      this.totalBricks = 21;
      this.map = new MapGenerator(3, 7);
      
      repaint();
    }
  }
  
  public void moveRight()
  {
    this.play = true;
    this.playerX += 20;
  }
  
  public void moveLeft()
  {
    this.play = true;
    this.playerX -= 20;
  }
}
