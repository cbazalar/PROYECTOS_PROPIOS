package biz.belcorp.ssicc.service.spusicc.mav.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVAsignacionReemplazoGerenteDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mav.ProcesoMAVAsignacionReemplazoGerenteService;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.procesoMAVAsignacionReemplazoGerenteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAVAsignacionReemplazoGerenteServiceImpl extends BaseService implements
	ProcesoMAVAsignacionReemplazoGerenteService {
	
	@Resource(name="spusicc.procesoMAVAsignacionReemplazoGerenteDAO")
	private ProcesoMAVAsignacionReemplazoGerenteDAO procesoMAVAsignacionReemplazoGerenteDAO;
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getGerentesRegiones(java.util.Map)
	 */
	public List getGerentesRegiones(Map params) {
		return procesoMAVAsignacionReemplazoGerenteDAO.getGerentesRegiones(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#executeAsignacionGerenteRegion(java.util.Map)
	 */
	public void executeAsignacionGerenteRegion(Map params) {
		procesoMAVAsignacionReemplazoGerenteDAO.executeAsignacionGerenteRegion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#existeCodigoCliente(java.util.Map)
	 */
	public boolean existeCodigoCliente(Map params) {
		String codigoCliente = mantenimientoMAEClienteDAO.getExisteCodigoCliente(params);
		
		if(codigoCliente != null)
			return true;
		else
			return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getGerentesZonas(java.util.Map)
	 */
	public List getGerentesZonas(Map params) {
		return procesoMAVAsignacionReemplazoGerenteDAO.getGerentesZonas(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#executeAsignacionGerenteZona(java.util.Map)
	 */
	public void executeAsignacionGerenteZona(Map params) {
		procesoMAVAsignacionReemplazoGerenteDAO.executeAsignacionGerenteZona(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getRemisionesMaterialPromocional(java.util.Map)
	 */
	public List getRemisionesMaterialPromocional(Map params) {
		if(params.get("codigoPeriodo")!=null || params.get("codigoCliente")!=null) {
			Map respuesta = procesoMAVAsignacionReemplazoGerenteDAO.getDatosRemisionesMaterialPromocional(params);
			
			if(respuesta != null) {
				if(respuesta.get("oidCliente")!=null) {
					params.put("oidCliente", respuesta.get("oidCliente"));
				} else {
					if(params.get("codigoCliente")!=null)
						params.put("oidCliente", "-1");
				}
				
				if(respuesta.get("oidPeriodo")!=null) {
					params.put("oidPeriodo", respuesta.get("oidPeriodo"));
				} else {
					if(params.get("codigoPeriodo")!=null)
						params.put("oidPeriodo", "-1");
				}
			}
		}
		
		return procesoMAVAsignacionReemplazoGerenteDAO.getRemisionesMaterialPromocional(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getAbastecimientoMaterial(java.util.Map)
	 */
	public List getAbastecimientoMaterial(Map params) {
		if(params.get("codigoPeriodo")!=null) {
			Map respuesta = procesoMAVAsignacionReemplazoGerenteDAO.getDatosRemisionesMaterialPromocional(params);
			
			if(respuesta != null) {
				if(respuesta.get("oidPeriodo")!=null) {
					params.put("oidPeriodo", respuesta.get("oidPeriodo"));
				} else {
					if(params.get("codigoPeriodo")!=null)
						params.put("oidPeriodo", "-1");
				}
			}
		}
		
		return procesoMAVAsignacionReemplazoGerenteDAO.getAbastecimientoMaterial(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getListaActividades(java.util.Map)
	 */
	public List getListaActividades(Map params) {
		return procesoMAVAsignacionReemplazoGerenteDAO.getListaActividades(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getHistoricoMaterialPromocional(java.util.Map)
	 */
	public List getHistoricoMaterialPromocional(Map params) {
		Map respuesta = procesoMAVAsignacionReemplazoGerenteDAO.getDatosHistoricoMaterialPromocional(params);
			
		if(respuesta != null) {
			if(respuesta.get("oidCliente")!=null) {
				params.put("oidCliente", respuesta.get("oidCliente"));
			} else {
				params.put("oidCliente", "-1");
			}
			
			if(respuesta.get("oidPeriodo")!=null) {
				params.put("oidPeriodo", respuesta.get("oidPeriodo"));
			} else {
				params.put("oidPeriodo", "-1");
			}
		}
		
		return procesoMAVAsignacionReemplazoGerenteDAO.getHistoricoMaterialPromocional(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getCabeceraArmadoGeneral(java.util.Map)
	 */
	public List getCabeceraArmadoGeneral(Map params) {
		if(params.get("codigoPeriodo")!=null) { 
			Map respuesta = procesoMAVAsignacionReemplazoGerenteDAO.getDatosRemisionesMaterialPromocional(params);
			
			if(respuesta != null) {
				if(respuesta.get("oidPeriodo")!=null) {
					params.put("oidPeriodo", respuesta.get("oidPeriodo"));
				} else {
					if(params.get("codigoPeriodo")!=null)
						params.put("oidPeriodo", "-1");
				}
			}
		}
		
		return procesoMAVAsignacionReemplazoGerenteDAO.getCabeceraArmadoGeneral(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getDetalleArmadoGeneral(java.util.Map)
	 */
	public List getDetalleArmadoGeneral(Map params) {
		return procesoMAVAsignacionReemplazoGerenteDAO.getDetalleArmadoGeneral(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#getPlanillaEntregaMaterial(java.util.Map)
	 */
	public List getPlanillaEntregaMaterial(Map params) {
		if(params.get("codigoPeriodo")!=null) { 
			Map respuesta = procesoMAVAsignacionReemplazoGerenteDAO.getDatosRemisionesMaterialPromocional(params);
			
			if(respuesta != null) {
				if(respuesta.get("oidPeriodo")!=null) {
					params.put("oidPeriodo", respuesta.get("oidPeriodo"));
				} else {
					if(params.get("codigoPeriodo")!=null)
						params.put("oidPeriodo", "-1");
				}
			}
		}
		
		return procesoMAVAsignacionReemplazoGerenteDAO.getPlanillaEntregaMaterial(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.service.ProcesoMAVAsignacionReemplazoGerenteService#executeAsignacionNumeroCajas(java.util.Map)
	 */
	public void executeAsignacionNumeroCajas(Map params) {
		 procesoMAVAsignacionReemplazoGerenteDAO.executeAsignacionNumeroCajas(params);
	}
	
}
