import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.*;

public class ViewAllItems extends JFrame implements ActionListener {
    JFrame f;
    JButton b1;

    DefaultTableModel model = new DefaultTableModel(
            new String[] { "ItemID", "Name", "Description", "Starting Bid" ,"Reserve Price", "End Time", "Current Highest Bid" }, 0);
    Container cnt = this.getContentPane();

    ViewAllItems() throws SQLException {
        ConnectionClass c1 = new ConnectionClass();
        String q1 = "Select item.ItemID,item.Name,item.Description,item.Starting_Bid,item.Reserve_Price,item.End_Time,b.highest from item left JOIN (select c.ItemID as ItemID, c.highest as highest from (select bid.ItemID as ItemID,max(bid.amount) as highest from bid group by bid.ItemID) c) b on item.ItemID = b.ItemID order by item.ItemID;";
        ResultSet rs = c1.stm.executeQuery(q1);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        Vector<String> columnNames = new Vector<String>();

        columnNames.add("Item ID");
        columnNames.add("Item Name");
        columnNames.add("Description");
        columnNames.add("Starting Bid");
        columnNames.add("Reserve Price");
        columnNames.add("End Time");
        columnNames.add("Highest Bid");

        Vector data = new Vector();
        Vector row = new Vector();
        while (rs.next()) {
            row = new Vector();
            for (int i = 1; i <= columnsNumber ; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        JFrame frame = new JFrame("All Items");
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
