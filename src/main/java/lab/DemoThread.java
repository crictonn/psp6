package lab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
public class DemoThread extends JFrame{
    private BufferedImage buffCanon, buffCannonball, buffTarget, buffExplosion;
    private Random randomTarget;
    private int cannonBallXPosition = 175; //Сделать 200, чтобы была красиво появлялся из пушки

    static JButton shootButton = new JButton("Shoot!");
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
        content.add(new CannonBall(), BorderLayout.CENTER);
        content.add(shootButton, BorderLayout.SOUTH);

        shootButton.addActionListener(new ShotPerformed());


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
            graphics2D.drawImage(buffCanon,0,300,100,100,this);
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

    public class CannonBall extends JPanel{
        public CannonBall(){
            setPreferredSize(new Dimension(500, 500));//Вот здесь оно как будто не нужно, из-за того, что центер автоматически выравнивается по EAST и WEST
            try{
                buffCannonball = ImageIO.read(new File("C://Users/devic/IdeaProjects/psp6/src/main/resources/cannonball.png"));
            } catch (IOException e){
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        cannonBallXPosition--;
                        repaint();
                        try {
                            Thread.sleep(1);
                        } catch (Exception e){
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
            graphics2D.drawImage(buffCannonball,cannonBallXPosition,315,30,30,this);
        }
   }

    public class ShotPerformed implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }



    public static void main(String[] args) {
        new DemoThread().setVisible(true);
    }
}
