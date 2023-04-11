import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class UserHome extends JFrame implements ActionListener{
    JFrame f;
    JButton b1, b2, b3, b4, b5;
    String UserId;

    UserHome(String UserId) {
        this.UserId = UserId;

        f = new JFrame("User Home");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        b1 = new JButton("View Items");
        b1.setBounds(30, 50, 160, 120);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Place Bid");
        b2.setBounds(200, 50, 160, 120);
        b2.addActionListener(this);
        f.add(b2);

        b3 = new JButton("Track Current Bids");
        b3.setBounds(200, 180, 160, 120);
        b3.addActionListener(this);
        f.add(b3);

        b4 = new JButton("View Bidding History");
        b4.setBounds(30, 180, 160, 120);
        b4.addActionListener(this);
        f.add(b4);

        b5 = new JButton("LogOut");
        b5.setBounds(280,20,80,20);
        b5.addActionListener(this);
        f.add(b5);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(390, 350);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                new ViewItems(UserId);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            f.dispose();
            new PlaceBid(UserId);
        } else if (ae.getSource() == b5) {
            f.dispose();
            new UserLogin();
        } else if (ae.getSource() == b4) {
            try {
                new ViewBiddingHistory(UserId);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (ae.getSource() == b3) {
            try {
                new TrackCurrentBids(UserId);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}




