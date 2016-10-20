/*
 * Created on 18/03/2005 01:11:51 PM
 *
 * biz.belcorp.ssicc.dao.ibatis.BaseDAOiBatis
 */
package biz.belcorp.ssicc.dao.framework.ibatis;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.Auditor;
import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramirez </a>
 */
@Repository("dao")
public class BaseDAOiBatis extends SqlMapClientDaoSupport implements DAO, Auditor {
    
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
	 * @see biz.belcorp.ssicc.service.framework.Service#getKeyMessage(java.lang.String)
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
	 * @see biz.belcorp.ssicc.service.framework.Service#getKeyMessage(java.lang.String, biz.belcorp.ssicc.dao.model.Usuario)
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
	 * @see biz.belcorp.ssicc.service.framework.Service#getLocale(biz.belcorp.ssicc.dao.model.Usuario)
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
}


