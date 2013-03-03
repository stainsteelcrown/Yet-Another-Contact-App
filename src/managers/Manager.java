/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managers;
import services.*;
/**
 *
 * @author Andrew
 */
public abstract class Manager {
    private Factory factory = new Factory();
    protected IService getService(String name) throws ServiceLoadException
    {
        return factory.getService(name);
    }
}
