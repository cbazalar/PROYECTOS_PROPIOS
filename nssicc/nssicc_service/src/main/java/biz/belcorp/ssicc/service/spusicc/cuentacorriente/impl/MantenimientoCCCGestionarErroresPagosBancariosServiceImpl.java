package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCGestionarErroresPagosBancariosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.ErrorPagoBancario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCGestionarErroresPagosBancariosService;



/**
 * Service que controla el Mantenimiento de la Gestion de los Errores de Pagos Bancarios
 *  
 * <p>
 * <a href="MantenimientoCCCGestionarErroresPagosBancariosServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz"></a>
 * 
 */
@Service("spusicc.mantenimientoCCCGestionarErroresPagosBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCGestionarErroresPagosBancariosServiceImpl extends BaseService implements MantenimientoCCCGestionarErroresPagosBancariosService {

	@Resource(name = "spusicc.mantenimientoCCCGestionarErroresPagosBancariosDAO")
	private MantenimientoCCCGestionarErroresPagosBancariosDAO mantenimientoCCCGestionarErroresPagosBancariosDAO;

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCCCGestionarErroresPagosBancariosService#getCronogramaCarteraList(java.util.Map)
	 */
	public List  getPagosBancariosPorGestionarList(Map criteria) {
		return mantenimientoCCCGestionarErroresPagosBancariosDAO.getPagosBancariosPorGestionarList(criteria);
	}
 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCGestionarErroresPagosBancariosService#getRegularizacionPagoBancarioById(biz.belcorp.ssicc.spusicc.cuentacorriente.model.RegularizacionPagoBancario)
	 */
	public ErrorPagoBancario getErrorPagoBancarioById(String oidMovimientoBancario){
		return mantenimientoCCCGestionarErroresPagosBancariosDAO.getErrorPagoBancarioById(oidMovimientoBancario);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCGestionarErroresPagosBancariosService#updatePagoBancarioPorRegularizar(java.util.Map)
	 */
	public void gestionarErrorPagoBancario(Map criteria){
		this.mantenimientoCCCGestionarErroresPagosBancariosDAO.gestionarErrorPagoBancario(criteria);
	}
	
	public void aprobarErrorPagoBancario(String[] items){		
		Map criteria = new HashMap();
		for(int i = 0; i < items.length; i++){			
			String id = items[i];			
			criteria.put("codigoTipoOrdenTransporte", StringUtils.split(id, "|")[0]);
			criteria.put("estadoEntrega", StringUtils.split(id, "|")[1]);
			mantenimientoCCCGestionarErroresPagosBancariosDAO.aprobarErrorPagoBancario(criteria);
		}
	}
	
	public void deleteErrorPagoBancario(String[] items){
														
		Map criteria = new HashMap();
		for(int i = 0; i < items.length; i++){			
			log.debug(items.toString());
			String []split =StringUtils.split(items[i], "-");								
			criteria.put("oidMovimientoBancario", split[0]);
			log.debug("Eliminando " + criteria.get("oidMovimientoBancario"));		
			mantenimientoCCCGestionarErroresPagosBancariosDAO.deleteErrorPagoBancario(criteria);
		}
	}	
	
}
