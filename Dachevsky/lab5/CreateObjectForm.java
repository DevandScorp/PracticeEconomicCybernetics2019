import javax.swing.*;
import javax.swing.event.CaretEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class CreateObjectForm extends JFrame {

    private static final long serialVersionUID = 1L;
    Main frame;
    private JLabel sectionLabel, reportLabel, modelLabel, reportNumberLabel;
    private JTextField partTextField, sectionTextField, nameTextField, priceTextField;
    private JButton closeButton, okButton;
    private JSeparator jSeparatorFirst, jSeparatorSecond;

    CreateObjectForm(Main temp) {
        frame = temp;
        initComponents();
        myInitComponents();
        setTitle("Add object");
        setSize(300, 300);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    private void initComponents() {

        sectionLabel = new JLabel();
        reportLabel = new JLabel();
        modelLabel = new JLabel();
        reportNumberLabel = new JLabel();
        partTextField = new JTextField();
        sectionTextField = new JTextField();
        nameTextField = new JTextField();
        priceTextField = new JTextField();
        jSeparatorFirst = new JSeparator();
        jSeparatorSecond = new JSeparator();
        okButton = new JButton();
        closeButton = new JButton();

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        sectionLabel.setText("Секция:");
        reportLabel.setText("Доклад:");
        modelLabel.setText("Докладчик:");
        reportNumberLabel.setText("Номер доклада");

        partTextField.addCaretListener(evt -> partTextFieldCaretUpdate(evt));
        sectionTextField.addCaretListener(evt -> sectionTextFieldCaretUpdate(evt));
        nameTextField.addCaretListener(evt -> nameTextFieldCaretUpdate(evt));

        okButton.setText("Accept");
        okButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                okButtonMouseClicked(evt);
            }
        });
        closeButton.setText("Close");
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                closeButtonMouseClicked(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparatorFirst)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(modelLabel)
                                                        .addComponent(reportLabel)
                                                        .addComponent(sectionLabel)
                                                        .addComponent(reportNumberLabel))
                                                .addGap(28, 28, 28)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(sectionTextField, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(nameTextField, GroupLayout.Alignment.TRAILING)
                                                        .addComponent(partTextField)
                                                        .addComponent(priceTextField, GroupLayout.Alignment.TRAILING)))
                                        .addComponent(jSeparatorSecond)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(okButton)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(closeButton))
                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                        //.addComponent(loadFromFileRadioButton)
                                                                        .addGap(18, 18, 18)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED))
                                                                ))
                                                .addGap(0, 4, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparatorFirst, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(sectionLabel)
                                        .addComponent(partTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(reportLabel)
                                        .addComponent(sectionTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(modelLabel)
                                        .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(reportNumberLabel)
                                        .addComponent(priceTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSeparatorSecond, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(okButton)
                                        .addComponent(closeButton))
                                .addContainerGap())
        );

        pack();
    }


    private void partTextFieldCaretUpdate(CaretEvent evt) {
        checkOkClose();
    }

    private void sectionTextFieldCaretUpdate(CaretEvent evt) {
        checkOkClose();
    }

    private void nameTextFieldCaretUpdate(CaretEvent evt) {
        checkOkClose();
    }


    private void closeButtonMouseClicked(MouseEvent evt) {
        dispose();
    }

    private void okButtonMouseClicked(MouseEvent evt) {
        if (okButton.isEnabled()) {

            Node nb = new Node(partTextField.getText(), sectionTextField.getText(), nameTextField.getText(), priceTextField.getText().isEmpty() ? "-1" : this.priceTextField.getText());
            Main.addResult = nb;

            frame.addNewItem();
            dispose();
        }
    }


    private void checkOkClose() {
        if (!partTextField.getText().isEmpty() && !sectionTextField.getText().isEmpty() && !nameTextField.getText().isEmpty())
            okButton.setEnabled(true);

    }

    private void setKeyboardState(boolean flag) {
        partTextField.setEditable(flag);
        sectionTextField.setEditable(flag);
        sectionTextField.setEditable(flag);
        nameTextField.setEditable(flag);
        priceTextField.setEditable(flag);
        okButton.setEnabled(false);
        closeButton.setEnabled(true);
        checkOkClose();
    }

    private void setFileState(boolean flag) {
        okButton.setEnabled(false);
        closeButton.setEnabled(true);
        checkOkClose();
    }

    private void myInitComponents() {
        setKeyboardState(true);
        setFileState(false);
    }
}