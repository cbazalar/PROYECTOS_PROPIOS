/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMBonosDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.Bonos;
import biz.belcorp.ssicc.dao.spusicc.comision.model.DetalleBonos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMBonosService;


/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoCOMBonosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMBonosServiceImpl extends BaseService 
    implements MantenimientoCOMBonosService {
	
	@Resource(name="spusicc.mantenimientoCOMBonosDAO")
	MantenimientoCOMBonosDAO mantenimientoCOMBonosDAO;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMBonosService#getBonosEjecutivas(java.util.Map)
	 */
	public List getBonosEjecutivas(Map params) {		
		return mantenimientoCOMBonosDAO.getBonosEjecutivas(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMBonosService#getListDetalleBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.DetalleBonos)
	 */
	public List getListDetalleBonos(DetalleBonos detalleBonos) {
		return mantenimientoCOMBonosDAO.getListDetalleBonos(detalleBonos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMBonosService#getSiguienteCodigoConcepto(biz.belcorp.ssicc.spusicc.comision.dao.model.Bonos)
	 */
	public String getSiguienteCodigoConcepto(Bonos bono) {
		return mantenimientoCOMBonosDAO.getSiguienteCodigoConcepto(bono);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMBonosService#insertBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.Bonos)
	 */
	public void insertBonos(Bonos bono) {
		mantenimientoCOMBonosDAO.insertBonos(bono);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMBonosService#insertDetalleBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.DetalleBonos)
	 */
	public void insertDetalleBonos(DetalleBonos detalleBonos) {
	  try{	
		mantenimientoCOMBonosDAO.insertDetalleBonos(detalleBonos);
	  }catch(Exception e){
		  //llave duplicada se encuentra eliminado
		  detalleBonos.setIndicadorActivo(Constants.NUMERO_UNO);
		  mantenimientoCOMBonosDAO.updateDetalleBonos(detalleBonos);
	  }
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMBonosService#updateBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.Bonos)
	 */
	public void updateBonos(Bonos bono) {
		mantenimientoCOMBonosDAO.updateBonos(bono);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMBonosService#updateDetalleBonos(biz.belcorp.ssicc.spusicc.comision.dao.model.DetalleBonos)
	 */
	public void updateDetalleBonos(DetalleBonos detalleBonos) {
		mantenimientoCOMBonosDAO.updateDetalleBonos(detalleBonos);		
	}

}