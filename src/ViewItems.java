import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.*;

public class ViewItems extends JFrame implements ActionListener {
    JFrame f;
    JButton b1;

    DefaultTableModel model = new DefaultTableModel(
            new String[] { "ItemID", "Name", "Description", "Starting Bid", "End Time", "Current Highest Bid","Your Highest Bid" }, 0);
    Container cnt = this.getContentPane();

    ViewItems(String UserID) throws SQLException {
        ConnectionClass c1 = new ConnectionClass();
        String q1 = "Select item.ItemID as Item_ID,item.Name as Item_Name,item.Description,item.Starting_Bid as Starting_Bid,item.End_Time as End_Time,b.highest as Current_Hisghest_Bid,b.your as Your_Highest_Bid from item left JOIN (select c.ItemID as ItemID, c.highest as highest, d.your as your from (select bid.ItemID as ItemID,max(bid.amount) as highest from bid group by bid.ItemID) c left join (select bid.ItemID as ItemID, max(bid.amount) as your from bid where bid.UserID = '"+ UserID +"' group by bid.ItemID ) d on c.ItemID = d.ItemID) b on item.ItemID = b.ItemID where item.end_time > now() order by item.ItemID;";
        ResultSet rs = c1.stm.executeQuery(q1);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        Vector<String> columnNames = new Vector<String>();

        columnNames.add("Item ID");
        columnNames.add("Item Name");
        columnNames.add("Description");
        columnNames.add("Starting Bid");
        columnNames.add("End Time");
        columnNames.add("Current Highest Bid");
        columnNames.add("Your Highest Bid");

        Vector data = new Vector();
        Vector row = new Vector();
        while (rs.next()) {
            row = new Vector();
            for (int i = 1; i <= columnsNumber ; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        JFrame frame = new JFrame("Available Items");
        frame.setBounds(850, 0, 1050, 400);
        JScrollPane jsp = new JScrollPane(new JTable(data, columnNames));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(jsp, BorderLayout.CENTER);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent ee) {
    }
}
