/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

import javax.swing.*;
import java.awt.*;
import managers.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Andrew
 */
public class CreateContactUI1  extends JFrame{

    public static void main(String[] args){
        new CreateContactUI1("Yet Another Contact Application");
    }

    GetData getdata = new GetData();


    private JLabel popupLbl = new JLabel ("You have exceeded the maximum number of characters! ");
    private JLabel contactViewLbl = new JLabel ("Contact View");


    private JTextField searchFld = new JTextField(50);

    private JButton submitBtn = new JButton("Submit");
    private JButton cancelBtn = new JButton("Cancel");
    private JButton newContactBtn = new JButton("New Contact");
    private JButton newSearchBtn = new JButton("Search");
    private JButton clearSearchBtn = new JButton("Clear");
    private JButton deleteBtn = new JButton("Delete");
    

    private static JTable table;
    private Object [][] newData = {
        {"","","","",deleteBtn,-1}
    };
    private static MyTableModel myTableModel;
    

    
    public CreateContactUI1(String name){
    super(name);

    Container container = getContentPane();
    GridBagLayout gridbag = new GridBagLayout();
    container.setLayout(gridbag);
    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;

    c.weightx = 0.1;
    c.fill = GridBagConstraints.NONE;
    contactViewLbl.setBorder(BorderFactory.createEmptyBorder(0,5,0,5));
    gridbag.setConstraints(contactViewLbl, c);
    container.add(contactViewLbl);

    c.weightx = 0.1;
    gridbag.setConstraints(newContactBtn, c);
    container.add(newContactBtn);

    c.weightx = 0.1;
    gridbag.setConstraints(newSearchBtn, c);
    container.add(newSearchBtn);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 1.0;
    searchFld.setMargin(new Insets(0,2,0,2));
    gridbag.setConstraints(searchFld, c);
    container.add(searchFld);

    c.weightx = 0.1;
    gridbag.setConstraints(clearSearchBtn, c);
    container.add(clearSearchBtn);

    GridBagConstraints d = new GridBagConstraints();

    d.weightx = 1.0;
    d.weighty = 1.0;
    d.gridwidth = 5;
    d.gridy = 1;
    d.gridx = 0;
    d.fill = GridBagConstraints.BOTH ;

    myTableModel = new MyTableModel();
    myTableModel.setData(newData);

    table = new JTable(myTableModel);
    TableCellRenderer defaultRenderer = table.getDefaultRenderer(JButton.class);
    table.setDefaultRenderer(JButton.class, new JTableButtonRenderer(defaultRenderer));
    table.setPreferredScrollableViewportSize(new Dimension(900,300));
    table.addMouseListener(new JTableButtonMouseListener(table));
    table.setFillsViewportHeight(true);
    
    JScrollPane scrollPane = new JScrollPane(table);

    gridbag.setConstraints(scrollPane, d);
    container.add(scrollPane);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
    setVisible(true);

    fireData(getdata.getData("null", newData));


    newSearchBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed (ActionEvent event){
                    ContactMgr contactMgr = new ContactMgr();
                    String search = searchFld.getText();
                    if(search.equals(""))
                        search = "null";

                    Object [][] blankSearch = {
                        {"","","","",deleteBtn,-1}
                    };

            fireData(getdata.getData(search, blankSearch));

                }
    });

    
    clearSearchBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed (ActionEvent event){
                    ContactMgr contactMgr = new ContactMgr();

                    String search = "null";

                    Object [][] blankSearch = {
                        {"","","","",deleteBtn,-1}
                    };

                    fireData(getdata.getData(search, blankSearch));

                    searchFld.setText("") ;
                }
    });


    newContactBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed (ActionEvent event){
                    new CreateContactUI("Update/Create New Contact");   
                }
        });

    }

    public static void fireData(Object[][] dataUpdate){

        myTableModel.setData(dataUpdate);

        table.revalidate();
        table.repaint();

        myTableModel.fireTableDataChanged();

    }


    



    }
