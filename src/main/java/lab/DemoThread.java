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

        content.add(new Canon(), BorderLayout.EAST);
        content.add(new Target(), BorderLayout.WEST);
        content.add(new JButton("Shoot"), BorderLayout.SOUTH);


    }
    private class Canon extends JPanel{
        public Canon(){
            setPreferredSize(new Dimension(150, 400));
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
            graphics2D.drawImage(buffCanon,25,300,100,100,this);
        }
    }

    public class Target extends JPanel{
        private boolean status = false;
        public Target(){
            setPreferredSize(new Dimension(150, 400));
            try{
                buffTarget = ImageIO.read(new File("C://Users/devic/IdeaProjects/psp6/src/main/resources/target.png"));
            } catch (IOException e){
                e.printStackTrace();
            }

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        repaint();
                        randomTarget = new Random();
                        try {
                            Thread.sleep((randomTarget.nextInt(5 - 1 + 1) + 1) * 1000);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        @Override
        protected void paintComponent(Graphics graphics){
            super.paintComponent(graphics);
            Graphics2D graphics2D = (Graphics2D) graphics;
            if(status){
                graphics2D.drawImage(buffTarget,25,250,0,0,this);
                status = false;
            }else{
                graphics2D.drawImage(buffTarget,25,250,100,100,this);
                status = true;
            }
        }
    }


    public static void main(String[] args) {
        new DemoThread().setVisible(true);
    }
}
