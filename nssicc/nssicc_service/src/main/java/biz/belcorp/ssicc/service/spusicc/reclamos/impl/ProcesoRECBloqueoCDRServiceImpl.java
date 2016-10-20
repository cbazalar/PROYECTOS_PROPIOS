/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECBloqueoCDRDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECBloqueoCDRService;

/**
 * @author Dennys Oliva Iriate
 *
 */
@Service("spusicc.procesoRECBloqueoCDRService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRECBloqueoCDRServiceImpl extends BaseService implements ProcesoRECBloqueoCDRService {
	
	@Resource(name="spusicc.procesoRECBloqueoCDRDAO")
	ProcesoRECBloqueoCDRDAO procesoRECBloqueoCDRDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECBloqueoCDRService#getBloqueosCDRByCriteria(java.util.Map)
	 */
	public List getBloqueosCDRByCriteria(Map map){
		if(!((String)map.get("codigoPeriodo")).equals("")){
			Map criteria = (Map)procesoRECBloqueoCDRDAO.getPeriodoInicialFinal(map).get(0);
			map.put("fechaInicial", criteria.get("fechaInicial"));
			map.put("fechaFinal", criteria.get("fechaFinal"));
		}
		return procesoRECBloqueoCDRDAO.getBloqueosCDRByCriteria(map);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECBloqueoCDRService#updateDesbloqueoCDR(java.lang.String[], java.lang.String)
	 */
	public void updateDesbloqueoCDR(String[] listaIDs, String usuario){
		Map map = new HashMap();
		for (int i = 0; i < listaIDs.length; i++) {        	        	
        	map.put("codigoUsuario", usuario);
        	map.put("oidBloqueo", listaIDs[i]);
        	procesoRECBloqueoCDRDAO.updateDesbloqueoCDR(map);
        }		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECBloqueoCDRService#executeProcesoRECBloqueoCDR(java.util.Map)
	 */
	public void executeProcesoRECBloqueoCDR(Map params){
		procesoRECBloqueoCDRDAO.executeProcesoRECBloqueoCDR(params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECBloqueoCDRService#executeProcesoRECEnviarCDRRecepcionados(java.util.Map)
	 */
	public void executeProcesoRECEnviarCDRRecepcionados(Map params){
		List lista = (List) params.get("listaCDR");
		String codigoUsuario = (String) params.get("codigoUsuario");
		for(int i=0; i < lista.size(); i++) {
			Map registro = (HashMap) lista.get(i); 
			registro.put("codigoUsuario", codigoUsuario);
			procesoRECBloqueoCDRDAO.executeProcesoRECEnviarCDRRecepcionados(registro);
		}
		
		
	}
}
