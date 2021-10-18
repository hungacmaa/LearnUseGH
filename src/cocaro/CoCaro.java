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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
public class CoCaro {
    private static int sec = 0;
    private static Timer timer = new Timer();
    private static JLabel lblTime = new JLabel("00:00");
    private static Board board;
    public static void main(String[] args) {
        int w = 500, h = 500;

        board = new Board();
        
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        
        board.setPreferredSize(new Dimension(w, h));
        
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);
        bottomPanel.setBackground(Color.DARK_GRAY);
        
        JButton btnStart = new JButton("Start");
        
        
        
        btnStart.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
            
        });
        
        lblTime.setForeground(Color.WHITE);
        
        bottomPanel.add(lblTime);
        bottomPanel.add(btnStart);
        
        jPanel.add(board);
        jPanel.add(bottomPanel);
        
        JFrame jFrame = new JFrame("Game co caro 9 o");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // dung khi thoat
        jFrame.setResizable(false); // ko cho phep thay doi size
        jFrame.add(jPanel);
        jFrame.pack();
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = ((int)dimension.getWidth()/2 - (jFrame.getWidth()/2));
        int y = ((int)dimension.getHeight()/2 - (jFrame.getHeight()/2));
//        jFrame.setLocation(x, y);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
    
    private static void startGame(){
        // Hỏi ai đi trước
        int choice = JOptionPane.showConfirmDialog(null, "người chơi O đi trước ?", "chọn người đi trước", JOptionPane.YES_NO_OPTION);
        String currentPlayer = (choice == 0) ? Cell.O_VALUE : Cell.X_VALUE;
        board.setCurrentPlayer(currentPlayer);
        
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sec ++;
                String time = String.format("%02d", sec/60)+" : "+String.format("%02d", sec%60);
                lblTime.setText(time);
            }
        }, 1000, 1000);
    }
}
