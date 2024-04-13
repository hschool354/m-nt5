package newproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

class RoundButton extends JButton {
    private static final long serialVersionUID = 1L;

    public RoundButton(String text) {
        super(text);
        setBorder(null); // Loại bỏ viền
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        // Loại bỏ vẽ viền
    }

    Shape shape;
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30);
        }
        return shape.contains(x, y);
    }
}