import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StockObject extends ElementObject {
    private JPanel panel1;
    private JButton buyButton;
    private JButton sellButton;
    private JLabel stockName;
    private JLabel symbol;
    private JLabel marketVal;
    private JLabel shares;
    private JLabel avgCost;
    private JLabel totalReturn;
    private JLabel nameTitle;
    private JLabel symbolTitle;
    private JLabel marketValTitle;
    private JLabel sharesTitle;
    private JLabel avgCostTitle;
    private JLabel totalReturnTitle;
    private JPanel innerPanel;
    private ATM atm;
    private Stock stock;
    private ViewStockScreen owner;

    public StockObject(ATM atm, ViewStockScreen owner, Stock stock) {
        $$$setupUI$$$();
        this.atm = atm;
        this.stock = stock;
        this.owner = owner;

        stockName.setText(stock.getName());
        symbol.setText(stock.getSymbol());
        marketVal.setText(stock.getMarketVal().toString());

        boldJlabel(nameTitle);
        boldJlabel(symbolTitle);
        boldJlabel(marketValTitle);
        boldJlabel(sharesTitle);
        boldJlabel(avgCostTitle);
        boldJlabel(totalReturnTitle);

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buyEvent();
            }
        });

        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellEvent();
            }
        });
    }

    //passing through this constructor indicates the customer owns this stock
    public StockObject(ATM atm, ViewStockScreen owner, StockPosition stockPosition) {
        this(atm, owner, stockPosition.getStock());

        updateStockPosition(stockPosition);
    }

    public void buyEvent() {
        new StockActionDialog(atm, this, true);
    }

    public void sellEvent() {
        new StockActionDialog(atm, this, false);
    }

    public void updateStockPosition(StockPosition stockPosition) {

        sellButton.setEnabled(true);
        shares.setText(Integer.toString(stockPosition.getShares()));
        avgCost.setText(stockPosition.getAvgCost().toPlainString());
        totalReturn.setText(stockPosition.getTotalReturn().toPlainString());
    }

    public void update() {

        Customer customer = atm.getLoggedInCustomer();
        owner.update();

        if (customer != null) {

            SecuritiesAccount securitiesAccount = customer.getSecuritiesAccount();

            StockPosition stockPosition = securitiesAccount.getStockPostition(stock.getSymbol());

            if (stockPosition != null) {

                updateStockPosition(stockPosition);
            } else {

                sellButton.setEnabled(false);
                shares.setText("0");
                avgCost.setText("-1");
                totalReturn.setText("-1");
            }
        }
    }

    public Stock getStock() {
        return stock;
    }

    private void boldJlabel(JLabel jLabel) {

        jLabel.setText("<html><B>" + jLabel.getText() + "</B></html>");
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(10, 10, 10, 10), -1, -1));
        panel1.setMaximumSize(new Dimension(2147483647, 120));
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayoutManager(3, 7, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(innerPanel, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buyButton = new JButton();
        buyButton.setText("Buy");
        innerPanel.add(buyButton, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sellButton = new JButton();
        sellButton.setEnabled(false);
        sellButton.setText("Sell");
        innerPanel.add(sellButton, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        sharesTitle = new JLabel();
        sharesTitle.setText("Owned Shares");
        innerPanel.add(sharesTitle, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        shares = new JLabel();
        shares.setText("0");
        innerPanel.add(shares, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        avgCostTitle = new JLabel();
        avgCostTitle.setText("Avg Cost");
        innerPanel.add(avgCostTitle, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        avgCost = new JLabel();
        avgCost.setText("-1");
        innerPanel.add(avgCost, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalReturnTitle = new JLabel();
        totalReturnTitle.setText("Total return");
        innerPanel.add(totalReturnTitle, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        totalReturn = new JLabel();
        totalReturn.setText("-1");
        innerPanel.add(totalReturn, new GridConstraints(2, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        symbolTitle = new JLabel();
        symbolTitle.setText("Symbol");
        innerPanel.add(symbolTitle, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        symbol = new JLabel();
        symbol.setText("Label");
        innerPanel.add(symbol, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        marketValTitle = new JLabel();
        marketValTitle.setText("Market $");
        innerPanel.add(marketValTitle, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        marketVal = new JLabel();
        marketVal.setText("Label");
        innerPanel.add(marketVal, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        stockName = new JLabel();
        stockName.setText("Label");
        innerPanel.add(stockName, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameTitle = new JLabel();
        nameTitle.setText("Name");
        innerPanel.add(nameTitle, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
