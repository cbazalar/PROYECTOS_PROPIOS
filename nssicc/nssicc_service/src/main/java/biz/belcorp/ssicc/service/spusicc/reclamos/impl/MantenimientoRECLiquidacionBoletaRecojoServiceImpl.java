/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECLiquidacionBoletaRecojoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECLiquidacionBoletaRecojoService;

/**
 * @author peextdoliva
 *
 */
@Service("spusicc.mantenimientoRECLiquidacionBoletaRecojoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECLiquidacionBoletaRecojoServiceImpl extends BaseService implements MantenimientoRECLiquidacionBoletaRecojoService {
	
	@Resource(name="spusicc.mantenimientoRECLiquidacionBoletaRecojoDAO")
	MantenimientoRECLiquidacionBoletaRecojoDAO mantenimientoRECLiquidacionBoletaRecojoDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
	InterfazSiCCDAO interfazSiCCDAO; 

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getEstados()
	 */
	public List getEstados(){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getEstados();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getCabecerasBoletasReclamo(java.util.Map)
	 */
	public List getCabecerasBoletasReclamo(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getCabecerasBoletasReclamo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getDetallesBoletasReclamo(java.util.Map)
	 */
	public List getDetallesBoletasReclamo(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getDetallesBoletasReclamo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getTotalBR(java.util.Map)
	 */
	public String getTotalBR(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getTotalBR(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getPendientesBR(java.util.Map)
	 */
	public String getPendientesBR(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getPendientesBR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getAprobadasBR(java.util.Map)
	 */
	public String getAprobadasBR(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getAprobadasBR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getRechazadasBR(java.util.Map)
	 */
	public String getRechazadasBR(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getRechazadasBR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getTotalCargosBR(java.util.Map)
	 */
	public String getTotalCargosBR(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getTotalCargosBR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#getTotalAbonosBR(java.util.Map)
	 */
	public String getTotalAbonosBR(Map criteria){
		return mantenimientoRECLiquidacionBoletaRecojoDAO.getTotalAbonosBR(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#aprobarReclamoDigitados(java.util.Map)
	 */
	public void aprobarReclamoDigitados(Map params,String[] ids){
		for (int i = 0; i < ids.length; i++) {
			params.put("codigoBoletaRecojo", ids[i]);
			mantenimientoRECLiquidacionBoletaRecojoDAO.aprobarReclamoDigitados(params);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#updateRechazarReclamoDigitados(java.lang.String)
	 */
	public void updateRechazarReclamoDigitados(Map params){
		
		String[] ids = (String [])params.get("ids"); 
		for (int i = 0; i < ids.length; i++) {
			Map criteria = new HashMap();
			criteria.put("codigoBoletaRecojo", ids[i]);
			criteria.put("codigoUsuario", MapUtils.getString(params, "codigoUsuario"));
			mantenimientoRECLiquidacionBoletaRecojoDAO.updateRechazarReclamoDigitados(criteria);
		}	
	}
	
	public String getNumLoteSTO(Map criteria){
		return interfazSiCCDAO.getNumLoteSTO(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECLiquidacionBoletaRecojoService#updateRecalcularBoletaRecojo(java.util.Map, java.lang.String[])
	 */
	public void updateRecalcularBoletaRecojo(Map criteria, String[] ids) {
		log.info("Entro a MantenimientoRECLiquidacionBoletaRecojoServiceImpl - updateRecalcularBoletaRecojo");
		
		for (int i = 0; i < ids.length; i++) {
			criteria.put("codigoBoletaRecojo", ids[i]);
			mantenimientoRECLiquidacionBoletaRecojoDAO.updateRecalcularBoletaRecojo(criteria);
		}
		
		log.info("Salio a MantenimientoRECLiquidacionBoletaRecojoServiceImpl - updateRecalcularBoletaRecojo");
	}


	
}
