import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.*;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AddNewItem extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4, l5, l6;
    JTextField t1, t2, t3, t4, t5;
    JButton b1, b2;

    AddNewItem() {
        f = new JFrame("Add New Event");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("Name of Item -");
        l1.setBounds(40, 20, 1000, 30);
        f.add(l1);

        l2 = new JLabel("Description -");
        l2.setBounds(40, 70, 1000, 30);
        f.add(l2);

        l3 = new JLabel("Strating Bid -");
        l3.setBounds(40, 120, 1000, 30);
        f.add(l3);

        l4 = new JLabel("Reserve Price -");
        l4.setBounds(40, 170, 150, 30);
        f.add(l4);

        l5 = new JLabel("End Time -");
        l5.setBounds(40, 220, 150, 30);
        f.add(l5);

        l6 = new JLabel("YYYY-MM-DD HH:MI:SS");
        l6.setBounds(20, 250, 200, 12);
        l6.setFont(new Font("Arial", Font.PLAIN, 11));
        f.add(l6);

        t1 = new JTextField();
        t1.setBounds(150, 20, 150, 30);
        f.add(t1);

        t2 = new JTextField();
        t2.setBounds(150, 70, 150, 30);
        f.add(t2);

        t3 = new JTextField();
        t3.setBounds(150, 120, 150, 30);
        f.add(t3);

        t4 = new JTextField();
        t4.setBounds(150, 170, 150, 30);
        f.add(t4);

        t5 = new JTextField();
        t5.setBounds(150, 220, 150, 30);
        f.add(t5);

        b1 = new JButton("Back");
        b1.setBounds(40, 290, 120, 30);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Add");
        b2.setBounds(200, 290, 120, 30);
        b2.addActionListener(this);
        f.add(b2);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 380);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            String name = t1.getText();
            String desc = t2.getText();
            String s_b = t3.getText();
            String r_p = t4.getText();
            String e_t = t5.getText();
            int b = 0;

            try {
                if (name.equals("") || (desc.equals("") || Integer.parseInt(s_b) < 0)
                        || (e_t.equals("") || Integer.parseInt(r_p) < 0)) {
                    JOptionPane.showMessageDialog(null, "Invalid Entries");
                } else {
                    int sb = Integer.parseInt(s_b);
                    int rp = Integer.parseInt(r_p);

                    ConnectionClass c1 = new ConnectionClass();

                    String q1 = "insert into item(Name,Description,Starting_Bid,Reserve_Price,End_Time) values ('"+name+"','"+desc+"',"+sb+","+rp+",'"+e_t+"');";
                    int aa = c1.stm.executeUpdate(q1);

                    if (aa == 1) {
                        JOptionPane.showMessageDialog(null, "Item Added Successfully");
                        f.dispose();
                        new AdminHome();
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid details");
                        this.f.setVisible(false);
                        this.f.setVisible(true);
                    }

                }
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "Enter Valid Details, Integrity Constraints Violated");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Enter Valid Details");
                e.printStackTrace();
            }
        } else if (ae.getSource() == b1) {
            f.dispose();
            new AdminHome();
        }
    }
}
