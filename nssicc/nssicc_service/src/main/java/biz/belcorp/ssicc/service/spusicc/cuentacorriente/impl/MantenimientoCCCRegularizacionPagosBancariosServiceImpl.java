package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCRegularizacionPagosBancariosDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model.RegularizacionPagoBancario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCRegularizacionPagosBancariosService;



/**
 * Service que controla el Mantenimiento de la Regularizacion de Pagos Bancarios
 *  
 * <p>
 * <a href="MantenimientoCCCRegularizacionPagosBancariosServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz"></a>
 * 
 */
@Service("spusicc.mantenimientoCCCRegularizacionPagosBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCRegularizacionPagosBancariosServiceImpl extends BaseService implements MantenimientoCCCRegularizacionPagosBancariosService {

	
	@Resource(name = "spusicc.mantenimientoCCCRegularizacionPagosBancariosDAO")
	private MantenimientoCCCRegularizacionPagosBancariosDAO mantenimientoCCCRegularizacionPagosBancariosDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#getNumeroLote(java.util.Map)
	 */
	public void getNumeroLote(Map criteria){
		this.mantenimientoCCCRegularizacionPagosBancariosDAO.getNumeroLote(criteria); 
	};
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.MantenimientoCCCRegularizacionPagosBancariosService#getCronogramaCarteraList(java.util.Map)
	 */
	public List  getPagosBancariosPorRegularizarList(Map criteria) {
		return mantenimientoCCCRegularizacionPagosBancariosDAO.getPagosBancariosPorRegularizarList(criteria);
	}
 
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#getRegularizacionPagoBancarioById(biz.belcorp.ssicc.spusicc.cuentacorriente.model.RegularizacionPagoBancario)
	 */
	public RegularizacionPagoBancario getRegularizacionPagoBancarioById(RegularizacionPagoBancario regularizacionPagoBancario){
		return mantenimientoCCCRegularizacionPagosBancariosDAO.getRegularizacionPagoBancarioById(regularizacionPagoBancario);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#updatePagoBancarioPorRegularizar(java.util.Map)
	 */
	public void updatePagoBancarioPorRegularizar(Map criteria){
		this.mantenimientoCCCRegularizacionPagosBancariosDAO.updatePagoBancarioPorRegularizar(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#updateListPagoBancarioPorRegularizar(java.util.List)
	 */
	public void updateListPagoBancarioPorRegularizar(List params){
		
		Map criteria = new HashMap();
		int cont = 0;
		for (int i = 0; i < params.size(); i++) {
			criteria = (Map)params.get(i);
			log.debug("Actualizo "+criteria.get("oidMovimientoBancario")+" con el valor "+criteria.get("codigoConsultora")+ " con Consecutivo " + criteria.get("consecutivoTransaccion"));
			
			List listaDetalle = (List)criteria.get("listaDetalle");
			if(listaDetalle !=null && listaDetalle.size()>0){
				Map criteriaDetalle = new HashMap();
				for (int j = 0; j < listaDetalle.size(); j++) {	
					criteriaDetalle = (Map)listaDetalle.get(j);
					criteriaDetalle.put("numeroLoteBancario", MapUtils.getString(criteria, "numeroLoteBancario"));
					criteriaDetalle.put("oidMovimientoBancario", MapUtils.getString(criteria, "oidMovimientoBancario"));
					cont++;
					criteriaDetalle.put("numeroConsecutivoTransaccion", cont+"");
					this.mantenimientoCCCRegularizacionPagosBancariosDAO.updatePagoBancarioPorRegularizar(criteriaDetalle);
					
				}
			}else{
				cont++;
				String importePagoDetalle = (String)criteria.get("importePagoDetalle");
				if (StringUtils.isBlank(importePagoDetalle))
					criteria.put("importePagoDetalle", "0");
				criteria.put("numeroConsecutivoTransaccion", cont+"");
				this.mantenimientoCCCRegularizacionPagosBancariosDAO.updatePagoBancarioPorRegularizar(criteria);
				
			}
		}
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#deleteListPagoBancarioPorRegularizar(java.util.List)
	 */
	public void deleteListPagoBancarioPorRegularizar(List params){
		Map datos = new HashMap();
		for (int i = 0; i < params.size(); i++) {
			datos = (Map)params.get(i);
			log.debug("Eliminando "+datos.get("oidMovimientoBancario"));
			this.mantenimientoCCCRegularizacionPagosBancariosDAO.deletePagoBancarioPorRegularizar(datos);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#archivarListPagoBancarioPorRegularizar(java.util.List)
	 */
	public void archivarListPagoBancarioPorRegularizar(List params){
		Map datos = new HashMap();
		for (int i = 0; i < params.size(); i++) {
			datos = (Map)params.get(i);
			log.debug("Archivando "+datos.get("oidMovimientoBancario"));
			this.mantenimientoCCCRegularizacionPagosBancariosDAO.archivarPagoBancarioPorRegularizar(datos);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#executeRegistrarLoteBancario(java.util.Map)
	 */
	public void executeRegistrarLoteBancario (Map criteria) {
		this.mantenimientoCCCRegularizacionPagosBancariosDAO.executeRegistrarLoteBancario(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCRegularizacionPagosBancariosService#updateListCuponesTramite(java.util.List)
	 */
	public void updateListCuponesTramite (List params){
		Map criteria = new HashMap();
		for (int i = 0; i < params.size(); i++) {
			criteria = (Map)params.get(i);			
			this.mantenimientoCCCRegularizacionPagosBancariosDAO.updateListCuponesTramite(criteria);
		}
	}


	@Override
	public Map getPagosBancariosPorRegularizarDividirPagoByFilter(Map criteria) {
		return mantenimientoCCCRegularizacionPagosBancariosDAO.getPagosBancariosPorRegularizarDividirPagoByFilter(criteria);
	}
	
}
