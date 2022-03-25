import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Scanner;
public class discountCalc extends JFrame {
    private JTextField shoppingAmt;
    private JRadioButton visa;
    private JRadioButton masterCard;
    private JRadioButton rupay;
    private JTextField discount1;
    private JTextField netPrice1;
    private JButton clearAllButton;
    private JButton calculateNetPriceButton;
    private JButton exitButton;
    private JPanel shoppingMall;

    public discountCalc() {
        setTitle("Discount Form");
        setSize(450,350);
        setContentPane(shoppingMall);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
//        ButtonGroup b1=new ButtonGroup();
//        b1.add(visa);
//        b1.add(masterCard);
//        b1.add(rupay);

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
                if(visa.isSelected()) {
                    System.out.println("Visa");
                    if (amount < 5000) {
                        amount12=Float.toString(amount);
                        discount = 0.1f * amount;
                        discount1.setText(Float.toString(discount));
                        discount12=Float.toString(discount);
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                        netPrice15=Float.toString(netPrice);
                        cardType = "Visa";
                        addToDatabase(amount12,cardType,discount12,netPrice15);

                    }
                    if (amount >= 5000) {
                        discount = 0.2f * amount;
                        discount1.setText(Float.toString(discount));
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                    }
                }
                if(masterCard.isSelected()){
                    System.out.println("mastercard");
                    if (amount < 10000) {
                        discount = 0.15f * amount;
                        discount1.setText(Float.toString(discount));
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                    }
                    if (amount >= 10000) {
                        discount = 0.25f * amount;
                        discount1.setText(Float.toString(discount));
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                    }
                }
                if(rupay.isSelected()){
                    System.out.println("Rupay");
                    if (amount < 8000) {
                        discount = 0.12f * amount;
                        discount1.setText(Float.toString(discount));
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                    }
                    if (amount >= 8000) {
                        discount = 0.15f * amount;
                        discount1.setText(Float.toString(discount));
                        netPrice = amount-discount;
                        netPrice1.setText(Float.toString(netPrice));
                    }
                }


            }
        });
    }

    private void addToDatabase(String amount,String CardType,String discount,String netPrice){
        final String Db_URL="jdbc:mysql://localhost:3306/discount";
        final String USERNAME="root";
        final String PASSWORD="06yuvraj@SINGH";
        Scanner k = new Scanner(System.in);
        System.out.println("enter amount");
        String amount1 = amount;
        System.out.println("enter discount");
        String discount1 = discount;

        System.out.println("enter cardType");
        String cardType = CardType;

        System.out.println("enter netprice");
        String netprice = netPrice;

        // Inserting data using SQL query
        String sql = "insert into amountDetails values('" + amount1
                + "'," + discount1 + ",'" + cardType + "','"+ netprice +"')";
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
