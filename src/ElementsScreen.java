import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * A common screen that can be used to display a list of IUIElements in a scrollable panel.
 * Can pass in functions to run when a button is clicked, or functions to run when an update is notified
 */
public class ElementsScreen extends BaseScreen {
    private JButton createBtn;
    private JPanel elementsPanel;
    private JPanel mainPanel;
    private JLabel CheckingFeeLbl;
    private JLabel WithdrawFeeLbl;
    private JLabel CloseOpenLabel;
    private SingleArgMethod<Void, ElementsScreen> func;
    private SingleArgMethod<IUIElement, ElementsScreen> updateFunc;
    private List<IUIElement> elements;

    public ElementsScreen(ATM atm) {
        super(atm);

        $$$setupUI$$$();

        initialize();
        CheckingFeeLbl.setText("A charge fee of $" + Constants.checkingFee + " will be applied to Checking account transactions");

        WithdrawFeeLbl.setText("A charge fee of $" + Constants.withdrawFee + " will be applied to all withdrawals.");



        elementsPanel.setLayout(new BoxLayout(elementsPanel, BoxLayout.Y_AXIS));
        ElementsScreen s = this;
        createBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (func != null) {
                    func.apply(s);
                }
            }
        });
    }

    public ElementsScreen(ATM atm, List<IUIElement> elements, SingleArgMethod<Void, ElementsScreen> func, String buttonText, SingleArgMethod<IUIElement, ElementsScreen> updateFunc) {
        this(atm);
        this.func = func;
        this.updateFunc = updateFunc;
        this.elements = elements;

        if (func == null) {
            createBtn.setVisible(false);
        } else {
            createBtn.setText(buttonText);
        }

        refreshUIElements(elements);
    }

    /**
     * Calls the update func passed in the constructor
     */
    public void update() {
        addUIElement(updateFunc.apply(this));
    }

    /**
     * Remove an object from the elements list, comparison is done by the equals method
     * @param object object to be removed
     */
    public void remove(Object object) {
        for (IUIElement e : elements) {
            if (e.equals(object)) {
                elementsPanel.remove(e.$$$getRootComponent$$$());
                elements.remove(e);
                elementsPanel.revalidate();
                elementsPanel.repaint();
                return;
            }
        }
    }

    private void addUIElement(IUIElement element) {
        element.setOwner(this);
        elements.add(element);
        elementsPanel.add(element.$$$getRootComponent$$$());
        elementsPanel.revalidate();
        elementsPanel.repaint();
    }

    /**
     * Redraws all the elements to the list.
     * @param elements element list to be drawn
     */
    public void refreshUIElements(List<IUIElement> elements) {
        elementsPanel.removeAll();

        for (IUIElement element : elements) {
            element.setOwner(this);
            element.$$$getRootComponent$$$().setAlignmentX(Component.CENTER_ALIGNMENT);
            elementsPanel.add(element.$$$getRootComponent$$$());
        }
        this.elements = elements;
    }

    private void initialize() {
        setContentPane($$$getRootComponent$$$());
        setSize(700, 700);
        centerScreen();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(3, 5, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.setMinimumSize(new Dimension(500, 500));
        createBtn = new JButton();
        createBtn.setText("Create New");
        mainPanel.add(createBtn, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(2, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        elementsPanel = new JPanel();
        elementsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(elementsPanel);
        CheckingFeeLbl = new JLabel();
        CheckingFeeLbl.setText("Label");
        mainPanel.add(CheckingFeeLbl, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        WithdrawFeeLbl = new JLabel();
        WithdrawFeeLbl.setText("Label");
        mainPanel.add(WithdrawFeeLbl, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
