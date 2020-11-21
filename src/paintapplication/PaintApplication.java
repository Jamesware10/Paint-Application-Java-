/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paintapplication;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import javax.swing.JFrame;
import javax.swing.*;

//import static javax.swing.text.html.CSS.Attribute.BACKGROUND_COLOR;
/**
 *
 * @author Curtney James
 */
public class PaintApplication extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    JPanel panel = new JPanel();
    JComboBox<String> jcbColors;

    String[] colors = {"Blue", "Red", "Green", "Yellow", "Orange", "MAgenta", "Black"};
    JPanel tipSizePanel = new JPanel();

    Graphics graphics = getGraphics();

    Color brushColor;
    public int offset;
    public int heightAndWidth;

    JButton extraSmallTip = new JButton("Extra Small");
    JButton smallTip = new JButton("Small");
    JButton mediumTip = new JButton("Medium / Default");
    JButton largeTip = new JButton("Large");
    JButton extraLargeTip = new JButton("Extra Large");
    JButton eraserTip = new JButton("Eraser");
    private JButton btnExit;

    public PaintApplication() {

        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        ImageIcon img = new ImageIcon("C:\\Users\\Curtney James\\Documents\\NetBeansProjects\\PaintApplication\\src\\img/paint.png");
        setIconImage(img.getImage());

        addMouseListener(this);
        addMouseMotionListener(this);
        setSize(1000, 800);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        //Settings panel
        add(panel, new FlowLayout());
        panel.setBackground(Color.DARK_GRAY);
        panel.setBorder(BorderFactory.createTitledBorder("Settings"));
        panel.setSize(1000, 110);
        panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        String[] colors = {"Black", "Red", "Blue", "Green", "Yellow", "Orange", "Magenta"};

        btnExit = new JButton("Exit");

        //color ComboBox selection
        jcbColors = new JComboBox<>(colors);
        panel.add(jcbColors);
        jcbColors.setSize(50, 100);
        jcbColors.setBorder(BorderFactory.createTitledBorder("Colors"));

        //Tip Size selction Panel
        tipSizePanel.setLayout(new FlowLayout());
        tipSizePanel.setSize(1980, HEIGHT);
        tipSizePanel.setBorder(BorderFactory.createTitledBorder("Tip Sizes"));
        panel.add(tipSizePanel);

        tipSizePanel.add(eraserTip);
        tipSizePanel.add(extraSmallTip);
        tipSizePanel.add(smallTip);
        tipSizePanel.add(mediumTip);
        tipSizePanel.add(largeTip);
        tipSizePanel.add(extraLargeTip);
        panel.add(btnExit);
        heightAndWidth = 10;

        //JComboBox color selection
        jcbColors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jcbColors = (JComboBox) e.getSource();

                String color = jcbColors.getSelectedItem().toString();

                switch (color) {
                    case "Red":
                        brushColor = Color.RED;
                        break;
                    case "Blue":
                        brushColor = Color.BLUE;
                        break;
                    case "Green":
                        brushColor = Color.GREEN;
                        break;
                    case "Yellow":
                        brushColor = Color.YELLOW;
                        break;
                    case "Orange":
                        brushColor = Color.ORANGE;
                        break;
                    case "Magenta":
                        brushColor = Color.MAGENTA;
                        break;
                    case "Black":
                        brushColor = Color.BLACK;
                        break;

                }
            }
        });

        eraserTip.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event
            ) {

                brushColor = getBackground();
//
//                java.awt.Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit();
//                Image image = toolkit.getImage(getClass().getResource("/eraser-icon.png"));
//                Cursor c = toolkit.createCustomCursor(image, new Point(getX(), getY()), "");
//               Image scaledImage = image.getScaledInstance(32, 32, Image.SCALE_DEFAULT);
//                setCursor(c);

                Image i = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Curtney James\\Documents\\NetBeansProjects\\PaintApplication\\src\\paintapplication/eraser-icon.png");
                Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(i, new Point(10, 10), "cursor1");
                setCursor(c);

            }
        }
        );

        extraSmallTip.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event
            ) {
                heightAndWidth = 2;
                offset = heightAndWidth;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        }
        );

        smallTip.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event
            ) {
                heightAndWidth = 5;
                offset = heightAndWidth;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        }
        );

        mediumTip.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event
            ) {
                heightAndWidth = 10;
                offset = heightAndWidth;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        }
        );

        largeTip.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event
            ) {
                heightAndWidth = 20;
                offset = heightAndWidth;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        }
        );

        extraLargeTip.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event
            ) {
                heightAndWidth = 40;
                offset = heightAndWidth;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        }
        );

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Graphics graphics = getGraphics();
        graphics.setColor(brushColor);
        graphics.fillOval((e.getX() - (offset / 2)), (e.getY() - offset / 2), heightAndWidth, heightAndWidth);

        if (e.getX() <= 150) {
            graphics.setColor(null);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics graphics = getGraphics();
        graphics.setColor(brushColor);
        graphics.fillOval((e.getX() - (offset / 2)), (e.getY() - offset / 2), heightAndWidth, heightAndWidth);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public static void main(String[] args) {
        new PaintApplication();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
