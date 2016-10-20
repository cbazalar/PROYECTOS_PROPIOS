/*
 * Created on 21/03/2005 02:50:58 PM biz.belcorp.ssicc.dao.ibatis.PaisDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PaisDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("paisDAO")
public class PaisDAOiBatis extends BaseDAOiBatis implements PaisDAO {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.PaisDAO#getPaises(biz.belcorp.ssicc.model.Pais)
     */
    public List getPaises(Pais pais) {
        List paises = getSqlMapClientTemplate().queryForList("PaisSQL.getPaises", pais);
        return paises;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.PaisDAO#getPaisesByCriteria(java.util.Map)
     */
    public List getPaisesByCriteria(Map criteria) {
        List paises = getSqlMapClientTemplate().queryForList("PaisSQL.getPaisesByCriteria", criteria);
        return paises;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.PaisDAO#getPais(java.lang.String)
     */
    public Pais getPais(String codigo) {
        Pais pais = (Pais) getSqlMapClientTemplate().queryForObject("PaisSQL.getPais", codigo);
        if (pais == null) {
            throw new ObjectRetrievalFailureException(Pais.class, codigo);
        }
        return pais;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.PaisDAO#getPaisByCodigoISO(java.lang.String)
     */
    public Pais getPaisByCodigoISO(String codigo) {
        Pais pais = (Pais) getSqlMapClientTemplate().queryForObject("PaisSQL.getPaisByCodigoISO", codigo);
        if (pais == null) {
            throw new ObjectRetrievalFailureException(Pais.class, codigo);
        }
        return pais;
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.PaisDAO#insertPais(biz.belcorp.ssicc.model.Pais,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void insertPais(Pais pais, Usuario usuario) {
        // Insertamos la informacion
        getSqlMapClientTemplate().update("PaisSQL.insertPais", pais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.PaisDAO#updatePais(biz.belcorp.ssicc.model.Pais,
     *      biz.belcorp.ssicc.model.Usuario)
     */
    public void updatePais(Pais pais, Usuario usuario) {
        getSqlMapClientTemplate().update("PaisSQL.updatePais", pais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.dao.PaisDAO#removePais(java.lang.String)
     */
    public void removePais(String codigo) {
        getSqlMapClientTemplate().update("PaisSQL.removePais", codigo);
    }

   
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PaisDAO#getParametrosSegmentacionByPais(java.lang.String)
     */
    public List getParametrosSegmentacionByPais(String codigoPais) {
		List parametros = getSqlMapClientTemplate().queryForList("PaisSQL.getParametrosSegmentacionByPais", codigoPais);
        return parametros;
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.dao.PaisDAO#insertParametrosSegmentacion(java.util.Map)
     */
    public void insertParametrosSegmentacion(Map criteria) {
    	getSqlMapClientTemplate().insert("PaisSQL.insertParametrosSegmentacion", criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.PaisDAO#updateParametrosSegmentacion(java.util.Map)
	 */
	public void updateParametrosSegmentacion(Map criteria) {
		getSqlMapClientTemplate().update("PaisSQL.updateParametrosSegmentacion", criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.PaisDAO#getPaisByContextRoot(java.lang.String)
	 */
	public Pais getPaisByContextRoot(String contextRoot) {
		
		Map criteria = new HashMap();
		criteria.put("contextRoot", contextRoot);
		
		Pais pais = (Pais) getSqlMapClientTemplate().queryForObject("PaisSQL.getPaisesByCriteria", criteria);
//        if (pais == null) {
//            throw new ObjectRetrievalFailureException(Pais.class, codigo);
//        }
        return pais;
	}
	
}