import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.util.*;

public class ViewBiddingHistory extends JFrame implements ActionListener {
    JFrame f;
    JButton b1;

    DefaultTableModel model = new DefaultTableModel(
            new String[] {"Bid ID","Item ID","Amount","Status"}, 0);
    Container cnt = this.getContentPane();

    ViewBiddingHistory(String UserID) throws SQLException {
        ConnectionClass c1 = new ConnectionClass();
        String q1 = "Select bid.BidID,bid.ItemID,bid.Amount,if((Select item.End_Time from item where item.ItemID = bid.Itemid) < NOW(),if(bid.amount < (select max(b.amount) from bid b where b.ItemID = bid.ItemID),'Declined','Accepted'),if(bid.amount < (select max(b.amount) from bid b where b.ItemID = bid.ItemID),'Declined','Winning')) as status from bid where bid.UserID='"+ UserID +"' group by bid.BidID;";
        ResultSet rs = c1.stm.executeQuery(q1);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();

        Vector<String> columnNames = new Vector<String>();

        columnNames.add("Bid ID");
        columnNames.add("Item ID");
        columnNames.add("Amount");
        columnNames.add("Status");

        Vector data = new Vector();
        Vector row = new Vector();
        while (rs.next()) {
            row = new Vector();
            for (int i = 1; i <= columnsNumber ; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        JFrame frame = new JFrame("Your Bids");
        frame.setBounds(400, 0, 700, 400);
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
