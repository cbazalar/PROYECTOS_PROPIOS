package org.sistema.framework.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.sistema.framework.dao.seguridad.model.Usuario;

/**
 * Data Access Object (DAO) interface. This is an interface used to tag our DAO
 * classes and to provide common methods to all DAOs.
 * 
 * <p>
 * <a href="DAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a>
 */
public interface DAO {

    /**
     * Generic method used to get all objects of a particular type. This is the
     * same as lookup up all rows in a table.
     * 
     * @param clazz
     *            the type of objects (a.k.a. while table) to get data from
     * @return List of populated objects
     */
    public List getObjects(Class clazz);

    /**
     * Generic method to get an object based on class and identifier. An
     * ObjectRetrievalFailureException Runtime Exception is thrown if nothing is
     * found.
     * 
     * @param clazz
     *            model class to lookup
     * @param id
     *            the identifier (primary key) of the class
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    public Object getObject(Class clazz, Serializable id);

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param o
     *            the object to save
     */
    public void insertObject(Object o);

    /**
     * Generic method to save an object - handles both update and insert.
     * 
     * @param o
     *            the object to save
     */
    public void updateObject(Object o);

    /**
     * Generic method to delete an object based on class and id
     * 
     * @param clazz
     *            model class to lookup
     * @param id
     *            the identifier (primary key) of the class
     */
    public void removeObject(Class clazz, Serializable id);
    
    

    
    /**
   	 * Obtiene el valor basado en el key enviado como parametro.
   	 * Dicho valor se obtiene del archivo properties
   	 * @param keyMensaje
   	 * @return
   	 */
    public String getKeyMessage(String keyMensaje);
    
    /**
   	 * Obtiene el valor basado en el key enviado como parametro.
   	 * Dicho valor se obtiene del archivo properties
   	 * @param keyMensaje
   	 * @return
   	 */
    public String getKeyMessage(String keyMensaje, Usuario usuario);
    
    /**
	 * Metodo para obtener el Locate del usuario ingresado como parametro 
	 * @param usuario
	 * @return
	 */
    public Locale getLocale(Usuario usuario);
    

}

