import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ElementsScreen extends BaseScreen {
    private JButton createBtn;
    private JPanel elementsPanel;
    private JPanel mainPanel;
    private SingleArgMethod<Void, ElementsScreen> func;
    private SingleArgMethod<IUIElement, ElementsScreen> updateFunc;
    private List<IUIElement> elements;

    public ElementsScreen() {
        $$$setupUI$$$();

        initialize();
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

    public ElementsScreen(List<IUIElement> elements, SingleArgMethod<Void, ElementsScreen> func, String buttonText, SingleArgMethod<IUIElement, ElementsScreen> updateFunc) {
        this();
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

    public void update() {
        addUIElement(updateFunc.apply(this));
    }

    public void remove(Object object) {
        for (IUIElement e : elements) {
            if (e.equals(object)) {
                elementsPanel.remove(e.$$$getRootComponent$$$());
                elementsPanel.revalidate();
                elementsPanel.repaint();
                return;
            }
        }
    }

    private void addUIElement(IUIElement element) {
        elementsPanel.add(element.$$$getRootComponent$$$());
        elementsPanel.revalidate();
        elementsPanel.repaint();
    }

    public void refreshUIElements(List<IUIElement> elements) {
        elementsPanel.removeAll();

        for (IUIElement element : elements) {
            element.setOwner(this);
            element.$$$getRootComponent$$$().setAlignmentX(Component.CENTER_ALIGNMENT);
            elementsPanel.add(element.$$$getRootComponent$$$());
        }
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
        mainPanel.setLayout(new GridLayoutManager(2, 5, new Insets(10, 10, 10, 10), -1, -1));
        mainPanel.setMinimumSize(new Dimension(500, 500));
        createBtn = new JButton();
        createBtn.setText("Create New");
        mainPanel.add(createBtn, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        mainPanel.add(scrollPane1, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        elementsPanel = new JPanel();
        elementsPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        scrollPane1.setViewportView(elementsPanel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
