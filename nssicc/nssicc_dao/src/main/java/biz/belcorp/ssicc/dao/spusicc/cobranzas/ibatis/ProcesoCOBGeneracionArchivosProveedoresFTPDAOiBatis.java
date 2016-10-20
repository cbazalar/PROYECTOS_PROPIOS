package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBGeneracionArchivosProveedoresFTPDAO;

/**
 * <p>
 * <a href="ProcesoCOBGeneracionArchivosProveedoresFTPDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Repository("spusicc.procesoCOBGeneracionArchivosProveedoresFTPDAO")
public class ProcesoCOBGeneracionArchivosProveedoresFTPDAOiBatis extends BaseDAOiBatis implements ProcesoCOBGeneracionArchivosProveedoresFTPDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBGeneracionArchivosProveedoresFTPDAO#obtenerProveedoresEtapas()
	 */
	public List obtenerProveedoresEtapas() {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.obtenerProveedoresEtapas", null);
	}
}