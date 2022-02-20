package de.linkl.Main;

import de.linkl.Tools.TextBox;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class Window extends JFrame implements MouseListener{

    Dimension dimension;
    BufferedImage frameIcon;

    protected JButton start;
    protected JButton exit;

    protected TextBox titleBox;
    protected TextBox startBox;
    protected TextBox exitBox;

    public Window(int width, int height, String title) {


        dimension = new Dimension(width, height);
        try {
            frameIcon = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/de/linkl/Graphics/frameIcon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);

        this.setIconImage(frameIcon);

        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setLocationRelativeTo(null);

        Border border = new LineBorder(Color.RED);
        Border border2 = new BevelBorder(BevelBorder.LOWERED);
        Border border3 = new EtchedBorder(EtchedBorder.RAISED);

        start = new JButton("Start");
        start.setBounds(540, 200, 200, 50);
        start.addMouseListener(this);
        start.setMargin(new Insets(0, 0, 0, 0));
        start.setBackground(new Color(181, 181, 181));
        start.setBorder(border3);
        this.add(start);

        exit = new JButton("Exit");
        exit.setBounds(540, 300, 200, 50);
        exit.addMouseListener(this);
        exit.setMargin(new Insets(0, 0, 0, 0));
        exit.setBackground(new Color(181, 181, 181));
        exit.setBorder(border3);
        this.add(exit);

        titleBox = new TextBox(Game.width/2-220, 100, "java game");

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == start) {
            start.setVisible(false);
            exit.setVisible(false);
            Game.inMenu = false;
        }
        if (e.getSource() == exit) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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


}
