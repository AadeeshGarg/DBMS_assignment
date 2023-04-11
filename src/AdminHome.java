import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class AdminHome extends JFrame implements ActionListener {
    JFrame f;
    JButton b1, b2, b3, b4;

    AdminHome() {
        f = new JFrame("Admin Home");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        b1 = new JButton("View All Items");
        b1.setBounds(30, 50, 160, 120);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Add New Item");
        b2.setBounds(200, 50, 160, 120);
        b2.addActionListener(this);
        f.add(b2);

        b3 = new JButton("Log Out");
        b3.setBounds(200, 180, 160, 120);
        b3.addActionListener(this);
        f.add(b3);

        b4 = new JButton("Auction History");
        b4.setBounds(30, 180, 160, 120);
        b4.addActionListener(this);
        f.add(b4);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(390, 350);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                new ViewAllItems();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            f.dispose();
            new AddNewItem();
        } else if (ae.getSource() == b4) {
            try {
                new AuctionHistory();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (ae.getSource() == b3) {
            f.dispose();
            new AdminLogin();
        }
    }
}

