package gr3

import javax.swing.JPanel
import java.awt.Color
import java.awt.Graphics
import java.awt.BasicStroke

class Spindle extends JPanel {
    final static b = new BasicStroke(3.0f)
    public void paint(Graphics g) {
      super.paint(g)
      g.setStroke b
      g.setColor Color.RED
      int h = this.height / 2
      int w = this.width
      g.drawLine(0, h, w - 1, h);
    }
}
