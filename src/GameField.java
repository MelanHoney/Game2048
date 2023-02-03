import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

public class GameField extends JPanel {
    private int tileX;
    private int tileY;
    private Timer timer;
    private Image tile_2;
    private Image tile_4;
    private Image tile_8;
    private Image tile_16;
    private Image tile_32;
    private Image tile_64;
    private Image tile_128;
    private Image tile_256;
    private Image tile_512;
    private Image tile_1024;
    private Image tile_2048;
    private int size = 320;
    private int tile_size = 80;
    private int all_tiles = 16;
    private int[][] field;
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    public GameField() {
        setBackground(Color.white);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
    }

    public void initGame(){
        field  = new int[4][4];
        createTile();
    }

    public void createTile(){
        do {
            tileX = new Random().nextInt(4);
            tileY = new Random().nextInt(4);
        } while (field[tileX][tileY] != 0);
        double coefForFour = new Random().nextInt(100)*0.2;
        double coefForTwo = new Random().nextInt(100);
        if (coefForTwo > coefForFour) {
            field[tileX][tileY] = 2;
        } else {
            field[tileX][tileY] = 4;
        }
    }

    public void loadImages(){
        ImageIcon ii2 = new ImageIcon("2.png");
        tile_2 = ii2.getImage();
        ImageIcon ii4 = new ImageIcon("4.png");
        tile_4 = ii4.getImage();
        ImageIcon ii8 = new ImageIcon("8.png");
        tile_8 = ii8.getImage();
        ImageIcon ii16 = new ImageIcon("16.png");
        tile_16 = ii16.getImage();
        ImageIcon ii32 = new ImageIcon("32.png");
        tile_32 = ii32.getImage();
        ImageIcon ii64 = new ImageIcon("64.png");
        tile_64 = ii64.getImage();
        ImageIcon ii128 = new ImageIcon("128.png");
        tile_128 = ii128.getImage();
        ImageIcon ii256 = new ImageIcon("256.png");
        tile_256 = ii256.getImage();
        ImageIcon ii512 = new ImageIcon("512.png");
        tile_512 = ii512.getImage();
        ImageIcon ii1024 = new ImageIcon("1024.png");
        tile_1024 = ii1024.getImage();
        ImageIcon ii2048 = new ImageIcon("2048.png");
        tile_2048 = ii2048.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(inGame){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    switch (field[i][j]){
                        case 0:
                            break;
                        case 2:
                            g.drawImage(tile_2, i*80, j*80, this);
                            break;
                        case 4:
                            g.drawImage(tile_4, i*80, j*80, this);
                            break;
                        case 8:
                            g.drawImage(tile_8, i*80, j*80, this);
                            break;
                        case 16:
                            g.drawImage(tile_16, i*80, j*80, this);
                            break;
                        case 32:
                            g.drawImage(tile_32, i*80, j*80, this);
                            break;
                        case 64:
                            g.drawImage(tile_64, i*80, j*80, this);
                            break;
                        case 128:
                            g.drawImage(tile_128, i*80, j*80, this);
                            break;
                        case 256:
                            g.drawImage(tile_256, i*80, j*80, this);
                            break;
                        case 512:
                            g.drawImage(tile_512, i*80, j*80, this);
                            break;
                        case 1024:
                            g.drawImage(tile_1024, i*80, j*80, this);
                            break;
                        case 2048:
                            g.drawImage(tile_2048, i*80, j*80, this);
                            break;
                    }
                }
            }
        } else {
            String str = "Game Over";
            //Font f = new Font("Arial",14,Font.BOLD);
            g.setColor(Color.black);
            //g.setFont(f);
            g.drawString(str,125,size/2);
        }
    }

    public void isFull(){
        inGame = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(field[i][j] == 0) {
                    inGame = true;
                }
            }
        }
    }

    public void move(){
        if(left){
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field[j][i] != 0) {
                        if (field[j-1][i] == field[j][i]) {
                            field[j][i] = 0;
                            field[j-1][i] *= 2;
                        } else {
                            for (int k = j; k > 0; k--) {
                                if (field[k-1][i] == 0) {
                                    field[k-1][i] = field[k][i];
                                    field[k][i] = 0;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(right){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[j][i] != 0) {
                        if (field[j+1][i] == field[j][i]) {
                            field[j][i] = 0;
                            field[j+1][i] *= 2;
                        } else {
                            for (int k = j; k < 3; k++) {
                                if (field[k+1][i] == 0) {
                                    field[k+1][i] = field[k][i];
                                    field[k][i] = 0;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(up){
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j < 4; j++) {
                    if (field[i][j] != 0) {
                        if (field[i][j-1] == field[i][j]) {
                            field[i][j] = 0;
                            field[i][j-1] *= 2;
                        } else {
                            for (int k = j; k > 0; k--) {
                                if (field[i][k-1] == 0) {
                                    field[i][k-1] = field[i][k];
                                    field[i][k] = 0;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        if(down){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] != 0) {
                        if (field[i][j+1] == field[i][j]) {
                            field[i][j] = 0;
                            field[i][j+1] *= 2;
                        } else {
                            for (int k = j; k < 3; k++) {
                                if (field[i][k+1] == 0) {
                                    field[i][k+1] = field[i][k];
                                    field[i][k] = 0;
                                } else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    class FieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (inGame) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if(key == KeyEvent.VK_LEFT) {
                    left = true;
                    right = false;
                    up = false;
                    down = false;
                }
                if(key == KeyEvent.VK_RIGHT) {
                    left = false;
                    right = true;
                    up = false;
                    down = false;
                }

                if(key == KeyEvent.VK_UP) {
                    left = false;
                    right = false;
                    up = true;
                    down = false;
                }
                if(key == KeyEvent.VK_DOWN) {
                    left = false;
                    right = false;
                    up = false;
                    down = true;
                }
                move();
                createTile();
                isFull();
            }
            repaint();
        }
    }
}
