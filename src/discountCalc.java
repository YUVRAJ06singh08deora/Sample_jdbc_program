import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;
public class discountCalc extends JFrame {
    private JTextField shoppingAmt;
    private JRadioButton cash;
    private JRadioButton cheque;
    private JRadioButton creditCard;
    private JTextField discount1;
    private JTextField netPrice1;
    private JButton clearAllButton;
    private JButton calculateNetPriceButton;
    private JButton exitButton;
    private JPanel shoppingMall;
    private JTable table1;
    private void createTablefromDatabase(String amount12,String cardType,String discount12,String netPrice15){
        Object[][] data={
                {amount12,cardType,discount12,netPrice15}
        };
        table1.setModel(new DefaultTableModel(
                data,
                new String[]{"Shopping Amount","Card Type","Discount","NetPrice"}
        ));
    }
    public discountCalc() {
        setTitle("Discount Form");
        setSize(450,350);
        setContentPane(shoppingMall);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingAmt.setText("");
                discount1.setText("");
                netPrice1.setText("");
            }
        });
        calculateNetPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                calculateNetPrice();

            }

            private void calculateNetPrice() {

                String shopingTxt = shoppingAmt.getText();
                int amount= Integer.parseInt(shopingTxt);
                float discount;
                float netPrice;
                String amount12;
                String netPrice15;
                String discount12;
                String cardType;

                if(cash.isSelected()) {

                    if (amount > 10000) {
                        amount12=Float.toString(amount);
                        discount = 0.2f * amount;
                        discount1.setText(Float.toString(discount));
                        discount12=Float.toString(discount);
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                        netPrice15=Float.toString(netPrice);
                        cardType = "Cash";
                        addToDatabase(amount12,cardType,discount12,netPrice15);
                        createTablefromDatabase(amount12,cardType,discount12,netPrice15);
                    }

                }
                if(cheque.isSelected()){

                    if (amount > 15000) {
                        amount12=Float.toString(amount);
                        discount = 0.1f * amount;
                        discount1.setText(Float.toString(discount));
                        discount12=Float.toString(discount);
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                        netPrice15=Float.toString(netPrice);
                        cardType = "Cheque";
                        addToDatabase(amount12,cardType,discount12,netPrice15);
                        createTablefromDatabase(amount12,cardType,discount12,netPrice15);
                    }

                }
                if(creditCard.isSelected()){

                    if (amount > 10000) {
                        amount12=Float.toString(amount);
                        discount = 0.1f * amount;
                        discount1.setText(Float.toString(discount));
                        discount12=Float.toString(discount);
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                        netPrice15=Float.toString(netPrice);
                        cardType = "creditCard";
                        addToDatabase(amount12,cardType,discount12,netPrice15);
                        createTablefromDatabase(amount12,cardType,discount12,netPrice15);
                    }

                }


            }
        });
    }

    private void addToDatabase(String amount,String CardType,String discount,String netPrice){
        final String Db_URL="jdbc:mysql://localhost:3306/discount";
        final String USERNAME="root";
        final String PASSWORD="06yuvraj@SINGH";


        String amount1 = amount;

        String discount1 = discount;


        String cardType = CardType;


        String netprice = netPrice;

        // Inserting data using SQL query
        String sql = "insert into amountDetails values('" + amount1
                + "','" + cardType + "','" + discount1 + "','"+ netprice +"')";
//
//        // Connection class object
//        Connection con = null;

        // Try block to check for exceptions
        try {
            Connection conn = DriverManager.getConnection(Db_URL, USERNAME, PASSWORD);
            Statement stat = conn.createStatement();

            int m = stat.executeUpdate(sql);
            if (m == 1)
                System.out.println(
                        "inserted successfully : " + sql);
            else
                System.out.println("insertion failed");
        }
        catch (Exception ex) {
            // Display message when exceptions occurs
            System.err.println(ex);
        }
    }

    public static void main(String[] args) {
  discountCalc c=new discountCalc();
    }
}
