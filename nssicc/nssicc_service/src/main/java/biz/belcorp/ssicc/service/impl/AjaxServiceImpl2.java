package biz.belcorp.ssicc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.MantenimientoEDUCursoCapacitacionDAO;
import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.ProcesoEDUCalificacionAptasDAO;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.AjaxService2;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * Implementacion de AjaxService.
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("ajaxService2")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class AjaxServiceImpl2 extends BaseService implements AjaxService2 {

	@Resource(name="edu.mantenimientoEDUCursoCapacitacionDAO")
	private MantenimientoEDUCursoCapacitacionDAO mantenimientoEDUCursoCapacitacionDAO;
	
	@Resource(name="edu.procesoEDUCalificacionAptasDAO")
	private ProcesoEDUCalificacionAptasDAO procesoEDUCalificacionAptasDAO;
	
	@Resource(name="edu.mantenimientoEDUGenericoDAO")
	private MantenimientoEDUGenericoDAO parametroEDUGenericoDAO;
	
	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;

     /*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.service.AjaxService#getRegionesEDUByPaisEmpresa(java.lang.String,
	 *      java.lang.String)
	 */
	public LabelValue[] getRegionesEDUByPaisEmpresa(String codigoPais, String codigoEmpresa) {
		LabelValue[] listaRegiones = null;
		try {
			RegionCursoCapacitacion region = new RegionCursoCapacitacion();
			region.setCodigoPais(codigoPais);
			region.setCodigoEmpresa(codigoEmpresa);
			List lista = mantenimientoEDUCursoCapacitacionDAO.getRegion(region);
			if (lista!= null && lista.size() > 0) {
				listaRegiones = new LabelValue[lista.size()];
				for (int i = 0; i < lista.size(); i++) {
					RegionCursoCapacitacion regionCursoCapacitacion = (RegionCursoCapacitacion) lista.get(i);
					LabelValue lv = new LabelValue(regionCursoCapacitacion.getCodigoRegion()+"-"+regionCursoCapacitacion.getDescripcionRegion(), regionCursoCapacitacion.getCodigoRegion());
					listaRegiones[i] = lv;
				}
			} else {
				// Creamos una primera opcin vaca
				listaRegiones = new LabelValue[1];
				listaRegiones[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return listaRegiones;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.service.AjaxService#getCampannaSgteEDUByPaisEmpresa
	 * (java.lang.String, java.lang.String)
	 */
	public String getCampannaSgteEDUByPaisEmpresa(String codigoPais,
			String codigoEmpresa) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = procesoEDUCalificacionAptasDAO
				.getCampannaActualProceso(criteria);
		if (codigoPeriodo != null) {
			criteria.put("campannaProceso", codigoPeriodo);
			criteria.put("numeroCampanna", new Integer("1"));
			String campannaSgte = parametroEDUGenericoDAO
					.getDevuelveCampanna(criteria);
			return campannaSgte;
		}
		return "";
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService2#getRegionesByPaisMarcaCanal(java.lang.String, java.lang.String, java.lang.String)
	 */
	public LabelValue[] getRegionesByPaisMarcaCanal(String codigoPais,
			String codigoMarca, String codigoCanal) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoMarca)
				&& StringUtils.isNotBlank(codigoCanal)) {
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoMarca", codigoMarca);
			criteria.put("codigoCanal", codigoCanal);

			try {
				List regiones = interfazSiCCDAO
						.getRegionesByPaisMarcaCanal(criteria);
				if (regiones != null && regiones.size() > 0) {
					result = new LabelValue[regiones.size()];

					// Creamos una primera opción vacía
					// result[0] = new LabelValue("", "");

					for (int i = 0; i < regiones.size(); i++) {
						Base region = (Base) regiones.get(i);
						// Construimos la descripcion

						LabelValue lv = new LabelValue(region.getDescripcion(),
								region.getCodigo());
						result[i] = lv;
					}
				} else {
					// Creamos una primera opción vacía
					result = new LabelValue[1];
					result[0] = new LabelValue("", "");
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.AjaxService2#getZonasMultipleByPaisMarcaCanalRegion(java.lang.String, java.lang.String, java.lang.String, java.lang.String[], java.lang.String)
	 */
	public LabelValue[] getZonasMultipleByPaisMarcaCanalRegion(
			final String codigoPais, final String codigoMarca,
			final String condigoCanal, final String[] codigoRegiones,// final
																		// ArrayList
																		// codigoRegiones,
			String condicionTodos) {
		LabelValue[] result = null;

		if (StringUtils.isNotBlank(codigoPais)
				&& StringUtils.isNotBlank(codigoPais)) {
			Map criteria = new HashMap();

			criteria.put("codigoPais", codigoPais);
			if (StringUtils.isNotBlank(codigoMarca)
					&& StringUtils.isNotBlank(codigoMarca)) {
				criteria.put("codigoMarca", codigoMarca);
			} else {
				return result;
			}
			if (StringUtils.isNotBlank(condigoCanal)
					&& StringUtils.isNotBlank(condigoCanal)) {
				criteria.put("codigoCanal", condigoCanal);
			} else {
				return result;
			}
			/*
			 * if (codigoRegiones != null && codigoRegiones.length > 0) {
			 * ArrayList list = new ArrayList(codigoRegiones.length); for (int
			 * i=0; i <codigoRegiones.length;i++)
			 * list.add(i,codigoRegiones[i].getLabel());
			 * criteria.put("codigoRegion", list); }
			 */
			criteria.put("codigoRegion", codigoRegiones);
			try {
				result = new LabelValue[1];
				result[0] = new LabelValue("Todos", "");

				List zonasList = interfazSiCCDAO.getLista(
						"getZonasMultipleByPaisMarcaCanalRegion", criteria);
				if (zonasList != null && zonasList.size() > 0) {

					if (StringUtils.equals("T", condicionTodos)) {
						result = new LabelValue[zonasList.size() + 1];
						result[0] = new LabelValue("Todos", "");
						for (int i = 0; i < zonasList.size(); i++) {
							Base periodo = (Base) zonasList.get(i);
							// Construimos la descripcion
							LabelValue lv = new LabelValue(
									periodo.getDescripcion(),
									periodo.getCodigo());
							result[i + 1] = lv;
						}
					} else {
						result = new LabelValue[zonasList.size()];
						for (int i = 0; i < zonasList.size(); i++) {
							Base concurso = (Base) zonasList.get(i);
							LabelValue lv = new LabelValue(
									concurso.getDescripcion(),
									concurso.getCodigo());
							result[i] = lv;
						}
					}
				}
			} catch (DataAccessException ignore) {
				log.warn(ignore.getMessage());
			}
		}
		return result;

		//
	}
	
}
