
import com.fastfoodstore.gui.form.staffForm.InfoBox;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HoangTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel at Mouse Position Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        InfoBox mainPanel = new InfoBox("id","123");
        mainPanel.setLayout(new BorderLayout());
        frame.add(mainPanel,BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
        frame.setSize(970, 680);
        frame.setResizable(false);

    }
}
