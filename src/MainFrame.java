import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/*
 * Created by JFormDesigner on Wed Apr 27 10:25:26 ICT 2022
 */



/**
 * @author Quoc
 */
public class MainFrame extends JFrame {
    public MainFrame() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Quoc
        panel4 = new JPanel();
        idTextField = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        nameTextField = new JTextField();
        birthdayTextField = new JTextField();
        label4 = new JLabel();
        label5 = new JLabel();
        emailTextField = new JTextField();
        addBtn = new JButton();
        clearBtn = new JButton();
        label6 = new JLabel();
        phoneNumberTextField = new JTextField();
        label7 = new JLabel();
        saveRadioButton = new JRadioButton();
        deleteBtn = new JButton();
        deleteRadioButton = new JRadioButton();
        updateBtn = new JButton();
        updateRadioButton = new JRadioButton();
        sortARadioBtn = new JRadioButton();
        sortDRadioBtn = new JRadioButton();
        searchIdTextField = new JTextField();
        label8 = new JLabel();
        searchNameTextField = new JTextField();
        label9 = new JLabel();
        searchBtn = new JButton();
        maleRadioButton = new JRadioButton();
        femaleRadioButton = new JRadioButton();
        panel1 = new JPanel();
        scrollPane1 = new JScrollPane();
        tblStudent = new JTable();

        //======== this ========
        var contentPane = getContentPane();

        //======== panel4 ========
        {
            panel4.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder (
            0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border .TitledBorder
            . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt. Color .
            red ) ,panel4. getBorder () ) ); panel4. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void propertyChange (java .
            beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( ) ;} } );

            //---- label1 ----
            label1.setText("ID:");

            //---- label2 ----
            label2.setText("Name:");

            //---- label4 ----
            label4.setText("Birthday(mm/dd/yy):");

            //---- label5 ----
            label5.setText("Email(a@example.com):");

            //---- addBtn ----
            addBtn.setText("Save");

            //---- clearBtn ----
            clearBtn.setText("Clear");

            //---- label6 ----
            label6.setText("Phone Number(10 digits):");

            //---- label7 ----
            label7.setText("Gender:");

            //---- saveRadioButton ----
            saveRadioButton.setText("Add Function");

            //---- deleteBtn ----
            deleteBtn.setText("Delete");

            //---- deleteRadioButton ----
            deleteRadioButton.setText("Delete Function");

            //---- updateBtn ----
            updateBtn.setText("Update");

            //---- updateRadioButton ----
            updateRadioButton.setText("Update Function");

            //---- sortARadioBtn ----
            sortARadioBtn.setText("Sorting Asc");

            //---- sortDRadioBtn ----
            sortDRadioBtn.setText("Sorting Desc");

            //---- label8 ----
            label8.setText("Search ID:");

            //---- label9 ----
            label9.setText("Search Name:");

            //---- searchBtn ----
            searchBtn.setText("Search");

            //---- maleRadioButton ----
            maleRadioButton.setText("Male");

            //---- femaleRadioButton ----
            femaleRadioButton.setText("Female");

            GroupLayout panel4Layout = new GroupLayout(panel4);
            panel4.setLayout(panel4Layout);
            panel4Layout.setHorizontalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel4Layout.createParallelGroup()
                            .addComponent(label2)
                            .addComponent(label1)
                            .addComponent(label8)
                            .addComponent(label9)
                            .addComponent(label7))
                        .addGroup(panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nameTextField, GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                        .addComponent(idTextField))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel4Layout.createParallelGroup()
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addGroup(panel4Layout.createParallelGroup()
                                                .addComponent(label5)
                                                .addComponent(label4))
                                            .addGap(22, 22, 22)
                                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
                                                .addComponent(birthdayTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(label6)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(phoneNumberTextField, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))))
                                .addGroup(GroupLayout.Alignment.LEADING, panel4Layout.createSequentialGroup()
                                    .addGap(11, 11, 11)
                                    .addComponent(searchIdTextField, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.LEADING, panel4Layout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(panel4Layout.createParallelGroup()
                                        .addComponent(searchNameTextField, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(panel4Layout.createSequentialGroup()
                                            .addComponent(searchBtn)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(sortARadioBtn)
                                            .addGap(14, 14, 14)))))
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(maleRadioButton)
                                .addGap(18, 18, 18)
                                .addComponent(femaleRadioButton)))
                        .addGap(15, 15, 15)
                        .addGroup(panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGroup(panel4Layout.createParallelGroup()
                                    .addComponent(addBtn)
                                    .addComponent(deleteBtn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panel4Layout.createParallelGroup()
                                    .addComponent(saveRadioButton)
                                    .addComponent(deleteRadioButton)))
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                    .addComponent(clearBtn)
                                    .addComponent(updateBtn))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(updateRadioButton))
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(sortDRadioBtn)))
                        .addContainerGap(43, Short.MAX_VALUE))
            );
            panel4Layout.setVerticalGroup(
                panel4Layout.createParallelGroup()
                    .addGroup(panel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label4)
                            .addComponent(addBtn)
                            .addComponent(birthdayTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(idTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1)
                            .addComponent(saveRadioButton))
                        .addGap(7, 7, 7)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label5)
                            .addComponent(emailTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2)
                            .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteRadioButton)
                            .addComponent(deleteBtn))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel4Layout.createParallelGroup()
                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label6)
                                .addComponent(phoneNumberTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(updateBtn)
                                .addComponent(updateRadioButton))
                            .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(label7)
                                .addComponent(maleRadioButton)
                                .addComponent(femaleRadioButton)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(sortARadioBtn)
                                .addComponent(sortDRadioBtn))
                            .addGroup(panel4Layout.createSequentialGroup()
                                .addComponent(clearBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label8)
                                    .addComponent(searchIdTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                    .addComponent(label9)
                                    .addComponent(searchNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchBtn)))
                        .addContainerGap(12, Short.MAX_VALUE))
            );
        }

        //======== panel1 ========
        {

            //======== scrollPane1 ========
            {
                scrollPane1.setViewportView(tblStudent);
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 905, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPane1, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                        .addContainerGap())
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Quoc
    private JPanel panel4;
    private JTextField idTextField;
    private JLabel label1;
    private JLabel label2;
    private JTextField nameTextField;
    private JTextField birthdayTextField;
    private JLabel label4;
    private JLabel label5;
    private JTextField emailTextField;
    private JButton addBtn;
    private JButton clearBtn;
    private JLabel label6;
    private JTextField phoneNumberTextField;
    private JLabel label7;
    private JRadioButton saveRadioButton;
    private JButton deleteBtn;
    private JRadioButton deleteRadioButton;
    private JButton updateBtn;
    private JRadioButton updateRadioButton;
    private JRadioButton sortARadioBtn;
    private JRadioButton sortDRadioBtn;
    private JTextField searchIdTextField;
    private JLabel label8;
    private JTextField searchNameTextField;
    private JLabel label9;
    private JButton searchBtn;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JPanel panel1;
    private JScrollPane scrollPane1;
    private JTable tblStudent;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
