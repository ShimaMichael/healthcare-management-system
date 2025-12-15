import javax.swing.*;
import java.awt.*;

public class ReferralPanel extends AbstractListPanel {

    private JButton btnProcessNext;

    public ReferralPanel() {
        super("Referrals");

        btnProcessNext = new JButton("Process Next Referral");

        // add to existing button bar
        JPanel bottom = (JPanel) getComponent(1); // BorderLayout.SOUTH panel
        bottom.add(btnProcessNext);
    }

    public JButton getBtnProcessNext() { return btnProcessNext; }
}
