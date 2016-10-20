/*
 * Created on 18/03/2005 01:11:51 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.BaseDAOiBatis
 */
package biz.belcorp.ssicc.dao.jdbc;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;

import biz.belcorp.ssicc.dao.framework.Auditor;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseDAOJdbc.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
public class BaseDAOJdbc extends JdbcDaoSupport implements DAO,
        Auditor {
   
    protected final Log log = LogFactory.getLog(getClass());

	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.DAO#getObjects(java.lang.Class)
     */
    public List getObjects(Class clazz) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.DAO#getObject(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getObject(Class clazz, Serializable id) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.DAO#insertObject(java.lang.Object)
     */
    public void insertObject(Object o) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.DAO#updateObject(java.lang.Object)
     */
    public void updateObject(Object o) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.DAO#removeObject(java.lang.Class,
     *      java.io.Serializable)
     */
    public void removeObject(Class clazz, Serializable id) {
        throw new UnsupportedOperationException();
    }

    protected DataFieldMaxValueIncrementer incrementer;

    public DataFieldMaxValueIncrementer getIncrementer() {
        return incrementer;
    }

     public void setIncrementer(DataFieldMaxValueIncrementer incrementer) {
        this.incrementer = incrementer;
    }

	@Override
	public String getKeyMessage(String keyMensaje) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getKeyMessage(String keyMensaje, Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Locale getLocale(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}


}
