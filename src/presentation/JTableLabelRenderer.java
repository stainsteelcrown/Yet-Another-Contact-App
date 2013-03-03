/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

/**
 *
 * @author Andrew
 */
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Andrew
 */
class JTableLabelRenderer implements TableCellRenderer {
  private TableCellRenderer __defaultRenderer;

  public JTableLabelRenderer(TableCellRenderer renderer) {
    __defaultRenderer = renderer;
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
						 boolean isSelected,
						 boolean hasFocus,
						 int row, int column)
  {
    if(value instanceof Component)
      return (Component)value;
    return __defaultRenderer.getTableCellRendererComponent(
	   table, value, isSelected, hasFocus, row, column);
  }
}
