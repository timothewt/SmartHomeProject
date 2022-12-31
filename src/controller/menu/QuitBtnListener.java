package controller.menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class QuitBtnListener implements ActionListener {

    public QuitBtnListener() {}

    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

}
