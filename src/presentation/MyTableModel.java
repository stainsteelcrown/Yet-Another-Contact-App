/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author andrew
 */
class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Name","Phone Number","Email","Address","Delete","id"};
    private Object [][] data;

    boolean[] columnsVisible = new boolean[6];

    public MyTableModel() {
        columnsVisible[0] = true;
        columnsVisible[1] = true;
        columnsVisible[2] = true;
        columnsVisible[3] = true;
        columnsVisible[4] = true;
        columnsVisible[5] = false;
    }

    public void setData(Object[][] data){
          this.data = data;
      }

    public int getRowCount() {
        return data.length;
    }

    public Object getValueAt(int row, int col) {
            return data[row][col];
        }

    protected int getNumber (int col) {
        int n = col;    // right number to return
        int i = 0;
        do {
            if (!(columnsVisible[i])) n++;
            i++;
        } while (i < n);
        // If we are on an invisible column,
        // we have to go one step further
        while (!(columnsVisible[n])) n++;
        return n;
    }

    public int getColumnCount() {

        return columnNames.length;
        /*int n = 0;
        for (int i = 0; i < 6; i++)
            if (columnsVisible[i]) n++;
        return n;*/
    }

    @Override
    public String getColumnName(int col){
        return columnNames[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
            return false;
        }

    @Override
      public Class getColumnClass(int c) {
          return getValueAt(0, c).getClass();
      }

    @Override
      public void setValueAt(Object value, int row, int col) {
          data[row][col] = value;
          fireTableCellUpdated(row, col);
      }
    
}
