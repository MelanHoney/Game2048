import javax.swing.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("2048");
        setSize(335, 358);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(400, 400);
        add(new GameField());
        setVisible(true);
    }

    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
    }
}