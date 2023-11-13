package lab;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
public class DemoThread extends JFrame{
    private BufferedImage buffCanon, buffCannonball, buffTarget, buffExplosion;
    private Random randomTarget;
    public DemoThread(){
        setTitle("I LOVE GUI IN JAVA OMG");
        setSize(new Dimension(500, 500));
        setLocationRelativeTo(null);
        setVisible(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel content = new JPanel(new BorderLayout());
        setContentPane(content);

        content.add(new Canon(), BorderLayout.WEST);
    }
    private class Canon extends JPanel{
        public Canon(){
            setPreferredSize(new Dimension(500,500));
            try{
                buffCanon = ImageIO.read(new File("C://Users/devic/IdeaProjects/psp6/src/main/resources/canon.png"));
            } catch (IOException e){
                e.printStackTrace();
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                        repaint();
                }
            }).start();
        }

        @Override
        protected void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.drawImage(buffCanon,350,300,100,100,this);
        }
    }

    public static void main(String[] args) {
        new DemoThread().setVisible(true);
    }
}
