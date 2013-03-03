/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

import domain.Contact;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import managers.ContactMgr;
import services.ServiceLoadException;

/**
 *
 * @author Andrew
 */
class JTableButtonMouseListener implements MouseListener {
  private JTable __table;

  private void __forwardEventToButton(MouseEvent e) {
    TableColumnModel columnModel = __table.getColumnModel();
    int column = columnModel.getColumnIndexAtX(e.getX());
    int row    = e.getY() / __table.getRowHeight();
    Object value;
    JButton button;
    MouseEvent buttonEvent;

    if(row >= __table.getRowCount() || row < 0 ||
       column >= __table.getColumnCount() || column < 0)
      return;

    value = __table.getValueAt(row, column);

    if(!(value instanceof JButton))
      return;

    button = (JButton)value;

    buttonEvent =
      (MouseEvent)SwingUtilities.convertMouseEvent(__table, e, button);
    button.dispatchEvent(buttonEvent);
    // This is necessary so that when a button is pressed and released
    // it gets rendered properly.  Otherwise, the button may still appear
    // pressed down when it has been released.
    __table.repaint();
  }

  public JTableButtonMouseListener(JTable table) {
    __table = table;
  }

  public void mouseClicked(MouseEvent e) {
    __forwardEventToButton(e);
    TableColumnModel columnModel = __table.getColumnModel();
    Object idObject;
    GetData getdata = new GetData();
    int id;
    int row    = e.getY() / __table.getRowHeight();
    int column = columnModel.getColumnIndexAtX(e.getX());
    idObject = __table.getValueAt(row, column + 1);
    Integer idInt = (Integer) idObject;
    id = idInt.intValue();
    ContactMgr contactMgr = new ContactMgr();
    Contact contact = new Contact();
    contact.setID(id);
        try {
            contactMgr.delete(contact);
        } catch (ServiceLoadException ex) {
            Logger.getLogger(JTableButtonMouseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    String search = "null";

    Object [][] blankSearch = {
        {"","","","","",-1}
    };

     CreateContactUI1.fireData(getdata.getData(search, blankSearch));
  }

  public void mouseEntered(MouseEvent e) {
    __forwardEventToButton(e);
  }

  public void mouseExited(MouseEvent e) {
    __forwardEventToButton(e);
  }

  public void mousePressed(MouseEvent e) {
    __forwardEventToButton(e);
  }

  public void mouseReleased(MouseEvent e) {
    __forwardEventToButton(e);
  }
}
