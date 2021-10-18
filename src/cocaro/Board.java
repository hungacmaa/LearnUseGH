/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocaro;

/**
 *
 * @author Admin
 */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.*;
import javax.imageio.*;
import javax.sound.sampled.*;
public class Board extends JPanel{
    private static final int N = 3;
    private static final int M = 3;
    
    private Image imgX;
    private Image imgO;
    
    private Cell[][] matrix = new Cell[N][M];
    
    private String currentPlayer = Cell.O_VALUE;
    
    public Board(String currentPlayer){
        this();
        this.currentPlayer = currentPlayer;
    }
    public Board() {
        this.initMatrix();
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                
                
                
                // tinh toan xem x, y roi vao o nao trong board, sau do ve hinh x, o tuy y
                for(int i=0; i<N; ++i){
                    for(int j=0; j<M; ++j){
                        Cell cell = matrix[i][j];
                        int xStart = cell.getX();                        
                        int yStart = cell.getY();
                        int xEnd = xStart+cell.getW();
                        int yEnd = yStart+cell.getH();
                        
                        if(x>=xStart && x<xEnd && y>yStart && y<yEnd){
                            if(cell.getValue().equals(Cell.EMPTY_VALUE)){
                                soundClick();
                                cell.setValue(currentPlayer);
                                currentPlayer = currentPlayer.equals(Cell.O_VALUE) ? Cell.X_VALUE : Cell.O_VALUE;
                                repaint();
                            }
                            
                        }
                    }
                }
            }
            
        });
        
        try{
            imgX = ImageIO.read(getClass().getResource("x.png"));
            imgO = ImageIO.read(getClass().getResource("o.png"));
        } catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private synchronized void soundClick(){
        
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("Click.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
        }
    }
    
    private void initMatrix(){
        //tao ma tran
        for(int i=0; i<N; ++i){
            for(int j=0; j<M; ++j){
                Cell c = new Cell();
                matrix[i][j] = c;
            }
        }
    }
    
    @Override
    public void paint(Graphics g) {
        int w = getWidth()/N, h = getHeight()/M;
        Graphics2D graphic2d = (Graphics2D) g;
        
        int k = 0;
        for(int i=0; i<N; ++i){
            for(int j=0; j<M; ++j){
                int x = j*w;
                int y = i*h;
                
                // Cap nhat ma tran
                Cell cell = matrix[i][j];
                cell.setX(x);
                cell.setY(y);
                cell.setH(h);
                cell.setW(w);
                
                Color color = k%2==0 ? Color.WHITE : Color.LIGHT_GRAY;
                graphic2d.setColor(color);
                graphic2d.fillRect(x, y, w, h);
                
                if(cell.getValue().equals(Cell.X_VALUE)){
                    Image img = imgX;
                    graphic2d.drawImage(img, x, y, w, h, this);
                }
                else if(cell.getValue().equals(Cell.O_VALUE)){
                    Image img = imgO;
                    graphic2d.drawImage(img, x, y, w, h, this);
                }
                
                ++k;
            }
        }
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
}
