package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBRecuperacionCarteraFFVVDAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBRecuperacionCarteraFFVVDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:esanchez@sigcomt.com">Eduardo Snchez</a>
 */
@Repository("spusicc.procesoCOBRecuperacionCarteraFFVVDAO")
public class ProcesoCOBRecuperacionCarteraFFVVDAOiBatis extends BaseDAOiBatis implements ProcesoCOBRecuperacionCarteraFFVVDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBRecuperacionCarteraFFVVDAO#getListadoCorreosGerenteZona()
	 */
	public List getListadoCorreosGerenteZona() {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getListadoCorreosGerenteZona", null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBRecuperacionCarteraFFVVDAO#getDatosCuerpoMensajeCorreoCarteraFFVV(java.util.Map)
	 */
	public List getDatosCuerpoMensajeCorreoCarteraFFVV(Map criteria) {
        List procesos = getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getDatosCuerpoMensajeCorreoCarteraFFVV", criteria);
        return procesos;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBRecuperacionCarteraFFVVDAO#getParamEmailGerenteRegion(java.util.Map)
	 */
	public String getParamEmailGerenteRegion(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getParamEmailGerenteRegion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBRecuperacionCarteraFFVVDAO#getParametroGenericoSistema(java.util.Map)
	 */
	public String getParametroGenericoSistema(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("sisicc.ProcesosCOBSQL.getParametroGenericoSistema", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBRecuperacionCarteraFFVVDAO#getDatosCuerpoMensajeCorreoCarteraFFVVGR(java.util.Map)
	 */
	public List getDatosCuerpoMensajeCorreoCarteraFFVVGR(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getDatosCuerpoMensajeCorreoCarteraFFVVGR", criteria);
	}		
}