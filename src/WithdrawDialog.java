import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

/**
 * A dialog that shows up to allow users to withdraw or deposit money.
 */
public class WithdrawDialog extends JDialog {
    private JPanel contentPane;
    private JButton DepositButton;
    private JButton buttonCancel;
    private JSpinner amountSpin;
    private JButton WithdrawButton;
    private JLabel amountLabel;

    /**
     * A dialog that shows up to allow users to withdraw or deposit money.
     * @param atm     Reference to the current ATM object.
     * @param account The account the actions will be performed on.
     * @param object  A reference to the AccountObject that generated this dialog.
     */
    public WithdrawDialog(ATM atm, BankAccount account, AccountsObject object) {
        $$$setupUI$$$();
        amountSpin.setModel(new SpinnerNumberModel(0, 0, 1000000000, 0.01));
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(DepositButton);

        DepositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deposit(atm, account);
                object.repaint();
            }
        });

        WithdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(atm, account);
                object.repaint();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void withdraw(ATM atm, BankAccount account) {
        BigDecimal amount = Helper.checkSpinnnerMoneyValue(amountSpin);

        if (amount == null) {
            JOptionPane.showMessageDialog(contentPane, "Amount entered is not valid.");
            return;
        }
        if (atm.withdraw(atm.getLoggedInCustomer(), account, amount)) {
            dispose();
        } else {
            String message = "Failed to withdraw money. You don't have enough.";
            if (account.getType() == AccountType.SAVINGS && ((SavingsAccount) account).isSecurityBackingAccount()) {
                message += "This is a security backing account, you need to keep a minimum of $" + Constants.minimalSecurityAmount.toPlainString();
            }

            JOptionPane.showMessageDialog(contentPane, message);
        }
    }

    private void deposit(ATM atm, BankAccount account) {
        BigDecimal amount = Helper.checkSpinnnerMoneyValue(amountSpin);

        if (amount == null) {
            JOptionPane.showMessageDialog(contentPane, "Amount entered is not valid.");
            return;
        }
        if (atm.deposit(atm.getLoggedInCustomer(), account, amount)) {
            JOptionPane.showMessageDialog(contentPane, "Deposit " + account.getCurrency() + " " + amount.toPlainString() + " success.");
        } else {
            JOptionPane.showMessageDialog(contentPane, "Deposit action failed.");
        }
        dispose();
    }

    private void onCancel() {
        dispose();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        DepositButton = new JButton();
        DepositButton.setText("Deposit");
        panel2.add(DepositButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel2.add(buttonCancel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        WithdrawButton = new JButton();
        WithdrawButton.setText("Withdraw");
        panel2.add(WithdrawButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        amountSpin = new JSpinner();
        panel3.add(amountSpin, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        amountLabel = new JLabel();
        amountLabel.setText("Amount");
        panel3.add(amountLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
