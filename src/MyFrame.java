import javax.swing.*;

public class MyFrame extends JFrame {

    MyFrame(){
        JButton button = new JButton();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1000,1000);
        this.setVisible(true);
        this.add(button);
    }
}
