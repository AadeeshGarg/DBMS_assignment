
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;

public class UserSignUp extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3, l4;
    JTextField t1, t2, t3;
    JPasswordField p1;
    JButton b1, b2;

    UserSignUp() {
        f = new JFrame("Create Student Account");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("UserID");
        l1.setBounds(40, 20, 1000, 30);
        f.add(l1);

        l2 = new JLabel("Username");
        l2.setBounds(40, 70, 1000, 30);
        f.add(l2);

        l3 = new JLabel("Password");
        l3.setBounds(40, 120, 1000, 30);
        f.add(l3);

        l4 = new JLabel("Email");
        l4.setBounds(40, 170,1000,30);
        f.add(l4);

        t1 = new JTextField();
        t1.setBounds(150, 20, 150, 30);
        f.add(t1);

        t2 = new JTextField();
        t2.setBounds(150, 70, 150, 30);
        f.add(t2);

        t3 = new JTextField();
        t3.setBounds(150, 170, 150, 30);
        f.add(t3);

        p1 = new JPasswordField();
        p1.setBounds(150, 120, 150, 30);
        f.add(p1);

        b1 = new JButton("Back");
        b1.setBounds(40, 240, 120, 30);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Sign Up");
        b2.setBounds(200, 240, 120, 30);
        b2.addActionListener(this);
        f.add(b2);

        f.getContentPane();
        f.setVisible(true);
        f.setSize(400, 340);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b2) {
            String id = t1.getText();
            String name = t2.getText();
            String password = p1.getText();
            String email = t3.getText();

            try {
                if (id.equals("") || name.equals("") || password.equals("") || email.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please fill all the required fields");
                } else {
                    ConnectionClass c1 = new ConnectionClass();
                    String q1 = "insert into user values('" + id + "','" + name + "','" + email + "','" + password +"')";
                    int aa = c1.stm.executeUpdate(q1);

                    if (aa == 1) {
                        JOptionPane.showMessageDialog(null, "Account Created Successfully");
                        f.dispose();
                        new UserLogin();
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid details");
                        this.f.setVisible(false);
                        this.f.setVisible(true);
                    }

                }
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "UserID alredy exists");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b1) {
            f.dispose();
            new UserLogin();
        }
    }
}