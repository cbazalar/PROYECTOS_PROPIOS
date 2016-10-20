/*
 * Created on 18/03/2005 01:11:51 PM
 *
 * org.sistema.framework.dao.ibatis.BaseDAOiBatis
 */
package org.sistema.framework.dao.ibatis;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.sistema.framework.dao.Auditor;
import org.sistema.framework.dao.Constants;
import org.sistema.framework.dao.DAO;
import org.sistema.framework.dao.seguridad.model.Idioma;
import org.sistema.framework.dao.seguridad.model.Usuario;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 */
@Repository("dao")
public class BaseDAOiBatis extends SqlMapClientDaoSupport implements DAO {
    
    protected final Log log = LogFactory.getLog(getClass());
    
    protected MessageSource messageSource;
    
    /**
	 * @param messageSource the messageSource to set
	 */
    @Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}


	/* (non-Javadoc)
	 * @see org.sistema.framework.service.framework.Service#getKeyMessage(java.lang.String)
	 */
	public String getKeyMessage(String keyMensaje) {
		String mensaje = new String();
		try {
			mensaje = this.messageSource.getMessage(keyMensaje, null,
					new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}

	
	/* (non-Javadoc)
	 * @see org.sistema.framework.service.framework.Service#getKeyMessage(java.lang.String, org.sistema.framework.dao.model.Usuario)
	 */
	public String getKeyMessage(String keyMensaje, Usuario usuario) {		
		String mensaje = new String();
		try {
			mensaje = this.messageSource.getMessage(keyMensaje, null,
					this.getLocale(usuario));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
	
	/* (non-Javadoc)
	 * @see org.sistema.framework.service.framework.Service#getLocale(org.sistema.framework.dao.model.Usuario)
	 */
	public Locale getLocale(Usuario usuario) {
		
		if (usuario != null) {
			Idioma idioma = usuario.getIdioma();
			Locale locale = new Locale(idioma.getCodigoISO());
			if (locale == null)
				locale = new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
			return locale;
		}	
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}    
    
    
    @Autowired
    public void setSqlMapClnt(SqlMapClient sqlMapClient){
       this.setSqlMapClient(sqlMapClient);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sistema.framework.dao.DAO#getObjects(java.lang.Class)
     */
    public List getObjects(Class clazz) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sistema.framework.dao.DAO#getObject(java.lang.Class,
     *      java.io.Serializable)
     */
    public Object getObject(Class clazz, Serializable id) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sistema.framework.dao.DAO#insertObject(java.lang.Object)
     */
    public void insertObject(Object o) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sistema.framework.dao.DAO#updateObject(java.lang.Object)
     */
    public void updateObject(Object o) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sistema.framework.dao.DAO#removeObject(java.lang.Class,
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
}


