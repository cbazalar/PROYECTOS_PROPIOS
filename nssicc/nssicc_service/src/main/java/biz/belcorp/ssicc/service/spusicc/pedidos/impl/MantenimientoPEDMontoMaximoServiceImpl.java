package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoPEDMontoMaximoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CarAsignCodigConfi;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.CarParamCarte;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoPEDMontoMaximoService;

/**
 * @author cdavila
 * 
 */
@Service("spusicc.mantenimientoPEDMontoMaximoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEDMontoMaximoServiceImpl extends BaseService implements MantenimientoPEDMontoMaximoService {

	@Resource(name="spusicc.mantenimientoPEDMontoMaximoDAO")
	MantenimientoPEDMontoMaximoDAO mantenimientoPEDMontoMaximoDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #getNivelRiesgo()
	 */
	public List getNivelRiesgo() {
		return mantenimientoPEDMontoMaximoDAO.getNivelRiesgo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #getCountExisteMontoMaximo()
	 */
	public String getCountExisteMontoMaximo(Map criteria) {
		/*List<String> listAfterRegion = (List<String>) criteria.get("listRegion");
		List<String> listRegion = new ArrayList<String>();
		criteria.remove("listRegion");
		for (String region : listAfterRegion) {
			criteria.put("codigoRegion", region);
			String oidRegion = mantenimientoPEDMontoMaximoDAO.getOidRegionByCodRegion(criteria);
			listRegion.add(oidRegion);
		}
		criteria.put("listRegion", listRegion);

		List<String> listAfterZona = (List<String>) criteria.get("listZona");
		List<String> listZona = new ArrayList<String>();
		criteria.remove("listZona");
		for (String zona : listAfterZona) {
			criteria.put("codigoZona", zona);
			String oidZona = mantenimientoPEDMontoMaximoDAO.getOidZonaByCodZona(criteria);
			listZona.add(oidZona);
		}
		criteria.put("listZona", listZona);

		List<String> listAfterSeccion = (List<String>) criteria.get("listSeccion");
		List<String> listSeccion = new ArrayList<String>();
		criteria.remove("listSeccion");
		for (String seccion : listAfterSeccion) {
			String[] compuesto = seccion.split("-");
			listSeccion.add(compuesto[2].trim());
		}
		criteria.put("listSeccion", listSeccion);*/

		return mantenimientoPEDMontoMaximoDAO.getCountExisteMontoMaximo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #insertCarParamCarte(java.util.Map)
	 */
	public Map insertCarParamCarte(Map criteria) {
		String oidPais = mantenimientoPEDMontoMaximoDAO.getOidPais(criteria);
		criteria.put("oidPais", oidPais);

		// agrega tipo de operacion 'I' (insertar)
		criteria.put("tipoOperacion", Constants.TIPO_OPERACION_INSERTAR);
		// agrega momento de registro D ( despues)
		criteria.put("momentoRegistro", Constants.MOMENTO_REGISTRO_DESPUES);

		criteria.put("indMontoMaximo", Constants.NUMERO_CERO);

		Long oidCarpPaca = mantenimientoPEDMontoMaximoDAO.getIdSgteCarParamCarte();
		criteria.put("oidCarpPaca", oidCarpPaca);
		mantenimientoPEDMontoMaximoDAO.insertCarParamCarte(criteria);

		Long oidCarpPacaAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarParamCarteAudit();
		criteria.put("oidCarpPacaAudit", oidCarpPacaAudit);
		mantenimientoPEDMontoMaximoDAO.insertCarParamCarteAudit(criteria);

		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #insertCarAsignCodigConfi(java.util.Map)
	 */
	public void insertCarAsignCodigConfi(Map criteria) {

		List<String> listRegion = (List<String>) criteria.get("listRegion");
		List<String> listZona = (List<String>) criteria.get("listZona");
		List<String> listSeccion = (List<String>) criteria.get("listSeccion");

		if (!listRegion.isEmpty() && !listZona.isEmpty()
				&& !listSeccion.isEmpty()) {
			for (String seccion : listSeccion) {
				String[] compuesto = seccion.split("-");
				criteria.put("oidRegion", Long.parseLong(compuesto[0].trim()));
				criteria.put("oidZona", Long.parseLong(compuesto[1].trim()));
				criteria.put("oidSeccion", Long.parseLong(compuesto[2].trim()));
				insertAfterCarAsignCodigConfi(criteria);
			}

		} else if (!listRegion.isEmpty() && !listZona.isEmpty()) {
			for (String zona : listZona) {
				criteria.put("codigoZona", zona);
				String oidZona = mantenimientoPEDMontoMaximoDAO.getOidZonaByCodZona(criteria);
				criteria.put("oidZona", Long.parseLong(oidZona));
				String oidRegion = mantenimientoPEDMontoMaximoDAO.getOidRegionByCodZona(criteria);
				criteria.put("oidRegion", Long.parseLong(oidRegion));
				criteria.put("oidSeccion", null);
				insertAfterCarAsignCodigConfi(criteria);
			}

		} else if (!listRegion.isEmpty()) {
			for (String region : listRegion) {
				criteria.put("codigoRegion", region);
				String oidRegion = mantenimientoPEDMontoMaximoDAO.getOidRegionByCodRegion(criteria);
				criteria.put("oidRegion", Long.parseLong(oidRegion));
				criteria.put("oidZona", null);
				criteria.put("oidSeccion", null);
				insertAfterCarAsignCodigConfi(criteria);
			}
		} else {
			criteria.put("oidRegion", null);
			criteria.put("oidZona", null);
			criteria.put("oidSeccion", null);
			insertAfterCarAsignCodigConfi(criteria);
		}

	}

	private void insertAfterCarAsignCodigConfi(Map criteria) {
		Long oidCarAscc = mantenimientoPEDMontoMaximoDAO.getIdSgteCarAsignCodigConfi();
		criteria.put("oidCarAscc", oidCarAscc);
		mantenimientoPEDMontoMaximoDAO.insertCarAsignCodigConfi(criteria);

		Long oidCarAsccAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarAsignCodigConfiAudit();
		criteria.put("oidCarAsccAudit", oidCarAsccAudit);
		mantenimientoPEDMontoMaximoDAO.insertCarAsignCodigConfiAudit(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #getMontoMaximoList(java.util.Map)
	 */
	public List getMontoMaximoList(Map criteria) {
		/*List<String> listAfterRegion = (List<String>) criteria.get("listRegion");
		List<String> listRegion = new ArrayList<String>();
		criteria.remove("listRegion");
		for (String region : listAfterRegion) {
			criteria.put("codigoRegion", region);
			String oidRegion = mantenimientoPEDMontoMaximoDAO.getOidRegionByCodRegion(criteria);
			listRegion.add(oidRegion);
		}
		criteria.put("listRegion", listRegion);

		List<String> listAfterZona = (List<String>) criteria.get("listZona");
		List<String> listZona = new ArrayList<String>();
		criteria.remove("listZona");
		for (String zona : listAfterZona) {
			criteria.put("codigoZona", zona);
			String oidZona = mantenimientoPEDMontoMaximoDAO.getOidZonaByCodZona(criteria);
			listZona.add(zona);
		}
		criteria.put("listZona", listZona);

		List<String> listAfterSeccion = (List<String>) criteria.get("listSeccion");
		List<String> listSeccion = new ArrayList<String>();
		criteria.remove("listSeccion");
		for (String seccion : listAfterSeccion) {
			String[] compuesto = seccion.split("-");
			listSeccion.add(compuesto[2].trim());
		}
		criteria.put("listSeccion", listSeccion);
		String oidPais = mantenimientoPEDMontoMaximoDAO.getOidPais(criteria);
		criteria.put("oidPais", oidPais);*/
		
		return mantenimientoPEDMontoMaximoDAO.getMontoMaximoList(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #deleteCarParamCarte(java.util.Map)
	 */
	public void deleteCarParamCarte(Map criteria) {
		// agrega tipo de operacion 'E' (eliminar)
		criteria.put("tipoOperacion", Constants.TIPO_OPERACION_ELIMINAR);
		// agrega momento de registro 'A' ( antes)
		criteria.put("momentoRegistro", Constants.MOMENTO_REGISTRO_ANTES);
		// busca elementos para insertar ANTES en getIdSgteCarParamCarteAudit
		List<CarParamCarte> listCarParamCarte = mantenimientoPEDMontoMaximoDAO.getCarParamCarte(criteria);
		if (!listCarParamCarte.isEmpty()) {
			for (CarParamCarte carParamCarte : listCarParamCarte) {
				criteria.put("oidCarpPaca", carParamCarte.getOidParaCart());
				criteria.put("oidPais", carParamCarte.getPaisOidPais());
				criteria.put("codigoNivelRiesgo", carParamCarte.getNiriOidNiveRies());
				criteria.put("montoMaximo", carParamCarte.getValMontMaxiPerm());

				// inserta en getIdSgteCarParamCarteAudit
				Long oidCarpPacaAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarParamCarteAudit();
				criteria.put("oidCarpPacaAudit", oidCarpPacaAudit);
				mantenimientoPEDMontoMaximoDAO.insertCarParamCarteAudit(criteria);
			}

			// elimina CarParamCarte
			mantenimientoPEDMontoMaximoDAO.deleteCarParamCarte(criteria);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #deleteCarAsignCodigConfi(java.util.Map)
	 */
	public void deleteCarAsignCodigConfi(Map criteria) {
		// agrega tipo de operacion 'E' (eliminar)
		criteria.put("tipoOperacion", Constants.TIPO_OPERACION_ELIMINAR);
		// agrega momento de registro 'A' ( antes)
		criteria.put("momentoRegistro", Constants.MOMENTO_REGISTRO_ANTES);
		// busca elementos para insertar ANTES en getIdSgteCarParamCarteAudit
		List<CarAsignCodigConfi> listCarAsignCodigConfi = mantenimientoPEDMontoMaximoDAO.getCarAsignCodigConfi(criteria);
		if (!listCarAsignCodigConfi.isEmpty()) {
			for (CarAsignCodigConfi carAsignCodigConfi : listCarAsignCodigConfi) {
				criteria.put("codigoNivelRiesgo", carAsignCodigConfi.getNiriOidNiveRies());
				criteria.put("oidCarAscc", carAsignCodigConfi.getOidAsigCodiConf());
				criteria.put("oidRegion", carAsignCodigConfi.getZorgOidRegi());
				criteria.put("oidZona", carAsignCodigConfi.getZzonOidZona());
				criteria.put("oidSeccion", carAsignCodigConfi.getZsccOidSecc());
				criteria.put("oidCarpPaca", carAsignCodigConfi.getPacaOidParaCart());
				// inserta en getIdSgteCarAsignCodigConfiAudit
				Long oidCarAsccAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarAsignCodigConfiAudit();
				criteria.put("oidCarAsccAudit", oidCarAsccAudit);
				mantenimientoPEDMontoMaximoDAO.insertCarAsignCodigConfiAudit(criteria);
			}
			// elimina CarAsignCodigConfi
			mantenimientoPEDMontoMaximoDAO.deleteCarAsignCodigConfi(criteria);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService
	 * #updateIndMontMaxi(java.util.Map)
	 */
	public void updateIndMontMaxi(Map criteria) {
		if(StringUtils.equals(MapUtils.getString(criteria, "accion"), "A"))
			criteria.put("tipoOperacion", Constants.TIPO_OPERACION_ACTIVAR);
		else
			criteria.put("tipoOperacion", Constants.TIPO_OPERACION_DESACTIVAR);
		
		// agrega momento de registro 'A' ( antes)
		criteria.put("momentoRegistro", Constants.MOMENTO_REGISTRO_ANTES);

		// busca elementos para insertar ANTES en getIdSgteCarParamCarteAudit
		List<CarParamCarte> listCarParamCarte = mantenimientoPEDMontoMaximoDAO.getCarParamCarte(criteria);
		if (!listCarParamCarte.isEmpty()) {
			CarParamCarte paramCarte = listCarParamCarte.get(0);
			for (CarParamCarte carParamCarte : listCarParamCarte) {
				criteria.put("oidCarpPaca", carParamCarte.getOidParaCart());
				criteria.put("oidPais", carParamCarte.getPaisOidPais());
				criteria.put("codigoNivelRiesgo", carParamCarte.getNiriOidNiveRies());
				criteria.put("montoMaximo", carParamCarte.getValMontMaxiPerm());
				criteria.put("indMontoMaximo", carParamCarte.getIndMontMaxi());
				// inserta en getIdSgteCarParamCarteAudit
				Long oidCarpPacaAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarParamCarteAudit();
				criteria.put("oidCarpPacaAudit", oidCarpPacaAudit);
				mantenimientoPEDMontoMaximoDAO.insertCarParamCarteAudit(criteria);
			}

			/*if (paramCarte.getIndMontMaxi().equals(Constants.NUMERO_CERO)) {
				criteria.put("newIndMontoMaximo", Constants.NUMERO_UNO);
			} else {
				criteria.put("newIndMontoMaximo", Constants.NUMERO_CERO);
			}*/
			
			if (StringUtils.equals(MapUtils.getString(criteria, "accion"), "A")) {
				criteria.put("newIndMontoMaximo", Constants.NUMERO_UNO);
			} else {
				criteria.put("newIndMontoMaximo", Constants.NUMERO_CERO);
			}

			// modificar el IndMontMaxi
			mantenimientoPEDMontoMaximoDAO.updateIndMontMaxi(criteria);

			Map criteriaAfter = new HashMap();
			
			if(StringUtils.equals(MapUtils.getString(criteria, "accion"), "A"))
				criteriaAfter.put("tipoOperacion", Constants.TIPO_OPERACION_ACTIVAR);
			else
				criteriaAfter.put("tipoOperacion", Constants.TIPO_OPERACION_DESACTIVAR);
			
			// agrega momento de registro 'D' (despues)
			criteriaAfter.put("momentoRegistro", Constants.MOMENTO_REGISTRO_DESPUES);

			String newIndMontoMaximo = (String) criteria.get("newIndMontoMaximo");
			criteriaAfter.put("indMontoMaximo", newIndMontoMaximo);
			String usuario = (String) criteria.get("usuario");
			criteriaAfter.put("usuario", usuario);
			for (CarParamCarte carParamCarte : listCarParamCarte) {
				criteriaAfter.put("oidCarpPaca", carParamCarte.getOidParaCart());
				criteriaAfter.put("oidPais", carParamCarte.getPaisOidPais());
				criteriaAfter.put("codigoNivelRiesgo", carParamCarte.getNiriOidNiveRies());
				criteriaAfter.put("montoMaximo", carParamCarte.getValMontMaxiPerm());
				// inserta en getIdSgteCarParamCarteAudit
				Long oidCarpPacaAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarParamCarteAudit();
				criteriaAfter.put("oidCarpPacaAudit", oidCarpPacaAudit);
				mantenimientoPEDMontoMaximoDAO.insertCarParamCarteAudit(criteriaAfter);
			}

		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.MantenimientoPEDMontoMaximoService#updateRegMontMaxi(java.util.Map)
	 */
	public void updateRegMontMaxi(Map criteria) {
		criteria.put("tipoOperacion", Constants.TIPO_OPERACION_MODIFICAR);
		
		// agrega momento de registro 'A' (antes)
		criteria.put("momentoRegistro", Constants.MOMENTO_REGISTRO_ANTES);

		// busca elementos para insertar ANTES en getIdSgteCarParamCarteAudit
		List<CarParamCarte> listCarParamCarte = mantenimientoPEDMontoMaximoDAO.getCarParamCarte(criteria);
		if (!listCarParamCarte.isEmpty()) {
			CarParamCarte paramCarte = listCarParamCarte.get(0);
			for (CarParamCarte carParamCarte : listCarParamCarte) {
				criteria.put("oidCarpPaca", carParamCarte.getOidParaCart());
				criteria.put("oidPais", carParamCarte.getPaisOidPais());
				criteria.put("codigoNivelRiesgo", carParamCarte.getNiriOidNiveRies());
				criteria.put("montoMaximo", carParamCarte.getValMontMaxiPerm());
				criteria.put("indMontoMaximo", carParamCarte.getIndMontMaxi());
				// inserta en getIdSgteCarParamCarteAudit
				Long oidCarpPacaAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarParamCarteAudit();
				criteria.put("oidCarpPacaAudit", oidCarpPacaAudit);
				mantenimientoPEDMontoMaximoDAO.insertCarParamCarteAudit(criteria);
			}
			
			// modificar el Registro
			mantenimientoPEDMontoMaximoDAO.updateRegMontMaxi(criteria);

			/*Map criteriaAfter = new HashMap();
			criteriaAfter.put("momentoRegistro", Constants.MOMENTO_REGISTRO_DESPUES);

			String newIndMontoMaximo = (String) criteria.get("newIndMontoMaximo");
			criteriaAfter.put("indMontoMaximo", newIndMontoMaximo);
			String usuario = (String) criteria.get("usuario");
			criteriaAfter.put("usuario", usuario);
			for (CarParamCarte carParamCarte : listCarParamCarte) {
				criteriaAfter.put("oidCarpPaca", carParamCarte.getOidParaCart());
				criteriaAfter.put("oidPais", carParamCarte.getPaisOidPais());
				criteriaAfter.put("codigoNivelRiesgo", carParamCarte.getNiriOidNiveRies());
				criteriaAfter.put("montoMaximo", carParamCarte.getValMontMaxiPerm());
				// inserta en getIdSgteCarParamCarteAudit
				Long oidCarpPacaAudit = mantenimientoPEDMontoMaximoDAO.getIdSgteCarParamCarteAudit();
				criteriaAfter.put("oidCarpPacaAudit", oidCarpPacaAudit);
				mantenimientoPEDMontoMaximoDAO.insertCarParamCarteAudit(criteriaAfter);
			}*/
		}
	}

	public List getCarParamCarte(Map criteria) {
		return mantenimientoPEDMontoMaximoDAO.getCarParamCarte(criteria);
	}

	public List getCodigoRegionUA(Map criteria) {
		return mantenimientoPEDMontoMaximoDAO.getCodigoRegionUA(criteria);
	}

	public List getCodigoZonaUA(Map criteria) {
		return mantenimientoPEDMontoMaximoDAO.getCodigoZonaUA(criteria);
	}

	public List getCodigoSeccionUA(Map criteria) {
		return mantenimientoPEDMontoMaximoDAO.getCodigoSeccionUA(criteria);
	}
}
