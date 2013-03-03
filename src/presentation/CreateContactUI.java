/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import domain.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import managers.*;

/**
 *
 * @author Andrew
 */
public class CreateContactUI  extends JFrame{

    CreateContactUI() {
   
        }

    private static class MyUpdatedTableModel extends AbstractTableModel {

        public MyUpdatedTableModel() {
        }
        private String[] columnNames = {"Label",
                                        "Values"};
        private Object[][] data;

        public void setData(Object[][] data){
            this.data = data;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        @Override
        public boolean isCellEditable(int row, int col) {
            if (col < 1) {
                return false;
            } else {
                return true;
            }    
        }
    }

    private JButton submitBtn = new JButton("Submit");
    private JButton cancelBtn = new JButton("Cancel");
    private JLabel contactUpdateLbl = new JLabel("Update Contact");
    private JLabel firstNameLbl = new JLabel("First Name");
    private JLabel lastNameLbl = new JLabel("Last Name");
    private JLabel middleNameLbl = new JLabel("Middle Name");
    private JLabel homeEmailLbl = new JLabel("Home Email");
    private JLabel workEmailLbl = new JLabel("Work email");
    private JLabel homePhoneLbl = new JLabel("Home Phone");
    private JLabel workPhoneLbl = new JLabel("Work Phone");
    private JLabel mobilePhoneLbl = new JLabel("Mobile Phone");
    private JLabel homeStreetAddressLbl = new JLabel("Home Street Address");
    private JLabel homeCityLbl = new JLabel("Home City");
    private JLabel homeStateLbl = new JLabel("Home State");
    private JLabel homeZipLbl = new JLabel ("Home Zip");
    private JLabel homeCountryLbl = new JLabel ("Home Country");
    private JLabel workStreetAddressLbl = new JLabel("Work Street Address");
    private JLabel workCityLbl = new JLabel("Work City");
    private JLabel workStateLbl = new JLabel("Work State");
    private JLabel workZipLbl = new JLabel("Work Zip");
    private JLabel workCountryLbl = new JLabel("Work Country");
    private JLabel otherStreetAddressLbl = new JLabel("Other Street Address");
    private JLabel otherCityLbl = new JLabel("Other City");
    private JLabel otherStateLbl = new JLabel("Other State");
    private JLabel otherZipLbl = new JLabel("Other Zip");
    private JLabel otherCountryLbl = new JLabel("Other Country");

    //private JTextField firstNameFld = new JTextField(45);

    
    public CreateContactUI(String name){
    super(name);


    Container container = getContentPane();
    GridBagLayout gridbag = new GridBagLayout();
    container.setLayout(gridbag);
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;

    c.weightx = 0.5;
    c.fill = GridBagConstraints.NONE;
    contactUpdateLbl.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    gridbag.setConstraints(contactUpdateLbl, c);
    container.add(contactUpdateLbl);

    c.weightx = 0.1;
    gridbag.setConstraints(submitBtn, c);
    container.add(submitBtn);

    c.weightx = 0.1;
    gridbag.setConstraints(cancelBtn, c);
    container.add(cancelBtn);

    GridBagConstraints d = new GridBagConstraints();

    d.weightx = 1.0;
    d.weighty = 1.0;
    d.gridwidth = 3;
    d.gridy = 1;
    d.gridx = 1;
    d.fill = GridBagConstraints.BOTH ;

    Object [][] newData = {
        {firstNameLbl , ""},
        {middleNameLbl ,""},
        {lastNameLbl ,""},
        {homeEmailLbl ,""},
        {workEmailLbl ,""},
        {homePhoneLbl ,""},
        {workPhoneLbl ,""},
        {mobilePhoneLbl ,""},
        {homeStreetAddressLbl, ""},
        {homeCityLbl, ""},
        {homeStateLbl, ""},
        {homeZipLbl, ""},
        {homeCountryLbl, ""},
        {workStreetAddressLbl, ""},
        {workCityLbl, ""},
        {workStateLbl, ""},
        {workZipLbl, ""},
        {workCountryLbl, ""},
        {otherStreetAddressLbl, ""},
        {otherCityLbl, ""},
        {otherStateLbl, ""},
        {otherZipLbl, ""},
        {otherCountryLbl, ""}
    };
    final MyUpdatedTableModel myUpdatedTableModel = new MyUpdatedTableModel();
    myUpdatedTableModel.setData(newData);

    JTable table1 = new JTable(myUpdatedTableModel);
    TableCellRenderer defaultRenderer = table1.getDefaultRenderer(JLabel.class);
    table1.setDefaultRenderer(JLabel.class, new JTableLabelRenderer(defaultRenderer));
    table1.setPreferredScrollableViewportSize(new Dimension(900,300));
    table1.setFillsViewportHeight(true);

    JScrollPane scrollPane = new JScrollPane(table1);

    gridbag.setConstraints(scrollPane, d);
    container.add(scrollPane);


    submitBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed (ActionEvent event){

                    Contact contact = new Contact();
                  
                    if(!myUpdatedTableModel.getValueAt(1, 1).toString().equals(""))
                        contact.setFirstName(myUpdatedTableModel.getValueAt(1, 1).toString());
                    
                    ContactMgr contactMgr = new ContactMgr();
                try {
                    if(contact.validate())
                        contactMgr.create(contact);
                } catch (Exception ex) {
                    
                }

                    CreateContactUI.this.dispose();
                }
            });
    cancelBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed (ActionEvent event){
                    CreateContactUI.this.dispose();
                }
            });


    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);
    }

    }
