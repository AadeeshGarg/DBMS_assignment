import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class PlaceBid extends JFrame implements ActionListener {
    JFrame f;
    JLabel l1, l2, l3;
    JComboBox c1;
    JTextField t1;
    JButton b1, b2, b3;
    String UserId;

    PlaceBid(String UserId) {
        this.UserId = UserId;
        f = new JFrame("Place a Bid");
        f.setBackground(Color.white);
        f.setLayout(null);

        l1 = new JLabel("Amount -");
        l1.setBounds(40, 70, 1000, 30);
        f.add(l1);

        t1 = new JTextField();
        t1.setBounds(200, 70, 150, 30);
        f.add(t1);

        l3 = new JLabel("Select Item -");
        l3.setBounds(40, 40, 1000, 30);
        f.add(l3);
        
        b1 = new JButton("Place Bid");
        b1.setBounds(60, 150, 100, 40);
        b1.addActionListener(this);
        f.add(b1);

        b2 = new JButton("Back");
        b2.setBounds(135, 200, 100, 40);
        b2.addActionListener(this);
        f.add(b2);

        b3 = new JButton("View Items");
        b3.setBounds(210, 150, 100, 40);
        b3.addActionListener(this);
        f.add(b3);

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT ItemID FROM Item where item.end_time > NOW() order by itemid;";
            ResultSet rs = obj.stm.executeQuery(query);
            ArrayList<String> eve = new ArrayList<String>();
            while (rs.next()) {
                eve.add(rs.getString("ItemID"));
            }
            Object[] ev = eve.toArray();
            c1 = new JComboBox<Object>(ev);
            c1.setBounds(200, 40, 150, 30);
            c1.addActionListener(this);
            f.add(c1);
        } catch (Exception e) {
            System.out.println(e);
        }

        f.getContentPane();
        f.setVisible(true);
        f.setSize(390, 310);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            f.dispose();
            String ItemID = (String) c1.getSelectedItem();
            Integer Amount = -1;
            try{
                Amount = Integer.parseInt(t1.getText());
                ConnectionClass c = new ConnectionClass();
                ResultSet rs1 = c.stm.executeQuery("select if('"+ ItemID +"' not in (Select bid.ItemID from bid),(select item.Starting_bid from item where item.itemid = '"+ItemID+"'),(select max(bid.amount) from bid where bid.ItemID = '"+ItemID+"')) as Highest;");
                rs1.next();
                Integer amt = Integer.parseInt(rs1.getString("Highest"));
                if(amt >= Amount){
                    throw new ArithmeticException();
                }
                else{
                    int aa = c.stm.executeUpdate("insert into bid(UserID,ItemID,Amount) values('"+UserId+"','"+ItemID+"',"+Amount+");");
                    if(aa == 1){
                        JOptionPane.showMessageDialog(null, "Bid Placed Successfully");
                        f.dispose();
                        new PlaceBid(UserId);
                    }
                    else{
                        throw new Exception();
                    }
                }
            }catch (ArithmeticException e) {
                JOptionPane.showMessageDialog(null, " Enter Amount greater than Current Highest");
                new PlaceBid(UserId);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Please enter Valid Details");
                new PlaceBid(UserId);
            }
            

        } else if (ae.getSource() == b2) {
            f.dispose();
            new UserHome(UserId);
        }
        else if (ae.getSource() == b3){
            try {
                new ViewItems(UserId);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
