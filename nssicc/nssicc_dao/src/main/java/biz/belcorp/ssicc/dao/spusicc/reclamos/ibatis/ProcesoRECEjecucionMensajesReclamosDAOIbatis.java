/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECEjecucionMensajesReclamosDAO;


/**
 * @author peextjcairampoma
 *
 */
@Repository("spusicc.procesoRECEjecucionMensajesReclamosDAO")
public class ProcesoRECEjecucionMensajesReclamosDAOIbatis extends BaseDAOiBatis implements ProcesoRECEjecucionMensajesReclamosDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.ProcesoRECEjecucionMensajesReclamosDAO#executeRECEjecucionMensajesReclamos(java.util.Map)
	 */
	public void executeRECEjecucionMensajesReclamos(Map params){
		List listaMensajes = getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getRECEjecucionMensajesReclamos", null);
		for (int i = 0; i < listaMensajes.size(); i++) {
			Map	map = (HashMap)listaMensajes.get(i);
			Object codigoMensaje= map.get("codigoMensaje");			
			Object ejecucion= map.get("ejecucion");
	        
			params.put("codigoMensaje", codigoMensaje);
			params.put("ejecucion", ejecucion);
			
			if (ejecucion!=null){
				String etiquetaEjecucion = ejecucion.toString().trim();
				if (!StringUtils.isEmpty(etiquetaEjecucion)) 
					getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL."+etiquetaEjecucion ,params);
			}
		}
	}
}
