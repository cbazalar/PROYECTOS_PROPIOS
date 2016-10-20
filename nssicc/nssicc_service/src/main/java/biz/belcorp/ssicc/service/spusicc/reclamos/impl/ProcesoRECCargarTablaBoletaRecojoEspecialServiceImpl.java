package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECCargarTablaBoletaRecojoEspecialDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECCargarTablaBoletaRecojoEspecialService;

@Service("spusicc.procesoRECCargarTablaBoletaRecojoEspecialService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRECCargarTablaBoletaRecojoEspecialServiceImpl extends BaseService implements ProcesoRECCargarTablaBoletaRecojoEspecialService {
	
	@Resource(name="spusicc.procesoRECCargarTablaBoletaRecojoEspecialDAO")
	ProcesoRECCargarTablaBoletaRecojoEspecialDAO procesoRECCargarTablaBoletaRecojoEspecialDAO; 
									   		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCargarTablaBoletaRecojoEspecialService#executeProcesoRECCargarTablaBoletaRecojoEspecial(java.util.Map)
	 */
	public void executeProcesoRECCargarTablaBoletaRecojoEspecial(Map params) {
		procesoRECCargarTablaBoletaRecojoEspecialDAO.executeProcesoRECCargarTablaBoletaRecojoEspecial(params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCargarTablaBoletaRecojoEspecialService#executeVerificacionTablaBoletaRecojoEspecial(java.util.Map)
	 */
	public List executeVerificacionTablaBoletaRecojoEspecial(Map params){
		return procesoRECCargarTablaBoletaRecojoEspecialDAO.executeVerificacionTablaBoletaRecojoEspecial(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCargarTablaBoletaRecojoEspecialService#getDescripcionProducto(java.util.Map)
	 */
	public String getDescripcionProducto(Map params){
		return procesoRECCargarTablaBoletaRecojoEspecialDAO.getDescripcionProducto(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECCargarTablaBoletaRecojoEspecialService#executeProcesoRECCargarTablaBoletaRecojoEspecial(java.util.List, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public void executeProcesoRECCargarTablaBoletaRecojoEspecial(List detalles, String periodo, String idioma, String usuario, String pais) {
		
		Map criteriaInserta = new  HashMap();
		for (Iterator iterator = detalles.iterator(); iterator.hasNext();) {
			
			Base base = (Base) iterator.next();
			
			criteriaInserta.put("codigoVenta",base.getCodigo());
			criteriaInserta.put("periodo",periodo);
			criteriaInserta.put("codigoPais",pais);
			criteriaInserta.put("codigoIso", idioma);
			criteriaInserta.put("val_usuario", usuario);
			

			this.insertTablaCodigosVenta(criteriaInserta);
}
		
		
		Map criteriaEjecuta = new  HashMap();
		
		criteriaEjecuta.put("periodo",periodo);
		criteriaEjecuta.put("codigoPais",pais);
		criteriaEjecuta.put("codigoIso", idioma);
		criteriaEjecuta.put("val_usuario", usuario);
		
		procesoRECCargarTablaBoletaRecojoEspecialDAO.executeProcesoRECCargarTablaBoletaRecojoEspecial(criteriaEjecuta);
	}
	
	public void insertTablaCodigosVenta(Map params){
		procesoRECCargarTablaBoletaRecojoEspecialDAO.insertTablaCodigosVenta( params);
	}
}
