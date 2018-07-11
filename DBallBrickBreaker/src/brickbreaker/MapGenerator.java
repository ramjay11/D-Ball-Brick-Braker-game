package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator
{
  public int[][] map;
  public int brickWidth;
  public int brickHeight;
  
  public MapGenerator(int row, int col)
  {
    this.map = new int[row][col];
    for (int i = 0; i < this.map.length; i++) {
      for (int j = 0; j < this.map[0].length; j++) {
        this.map[i][j] = 1;
      }
    }
    this.brickWidth = (540 / col);
    this.brickHeight = (150 / row);
  }
  
  public void draw(Graphics2D g)
  {
    for (int i = 0; i < this.map.length; i++) {
      for (int j = 0; j < this.map[0].length; j++) {
        if (this.map[i][j] > 0)
        {
          g.setColor(Color.white);
          g.fillRect(j * this.brickWidth + 80, i * this.brickHeight + 50, this.brickWidth, this.brickHeight);
          
          g.setStroke(new BasicStroke(3.0F));
          g.setColor(Color.black);
          g.drawRect(j * this.brickWidth + 80, i * this.brickHeight + 50, this.brickWidth, this.brickHeight);
        }
      }
    }
  }
  
  public void setBrickValue(int value, int row, int col)
  {
    this.map[row][col] = value;
  }
}
