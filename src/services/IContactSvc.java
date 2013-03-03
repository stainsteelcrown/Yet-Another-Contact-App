/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import domain.*;
import java.util.List;

/**
 *
 * @author Andrew
 */
public interface IContactSvc extends IService {

    public final String NAME = "IContactSvc";

    public Contact store (Contact c);
    public List retrieve (Contact r);
    public List retrieve (String search);
    public Contact update (Contact update);
    public Contact delete (Contact d);

}
