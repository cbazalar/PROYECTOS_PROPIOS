package biz.belcorp.ssicc.dao.soa.ibatis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.soa.ProspectoDAO;

@Repository("soa.prospectoDAO")
public class ProspectoDAOiBatis extends BaseDAOiBatis implements ProspectoDAO {

	public int getLengthDocumento(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"soa.ProspectoSQL.getLengthDocumento", criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.soa.dao.ProspectoDAO#getValidacionCrediticiaUsuario
	 * (java.util.Map)
	 */
	public List getValidacionCrediticiaUsuario(Map criteria) {
		final List listProspecto = new ArrayList();
		List<Map> list = null;
		
		criteria.put("encontroSELECTPrincipal", Constants.SI);
		list = getSqlMapClientTemplate().queryForList(
				"soa.ProspectoSQL.getValidacionCrediticiaUsuario", criteria);
		log.debug("Lista Prospecto ES-LB: " + list.size());
		if (list == null || list.size() == 0) {
			list = getSqlMapClientTemplate().queryForList("soa.ProspectoSQL.getValidacionCrediticiaUsuarioSecundario", criteria);
			log.debug("Lista Secundaria Prospecto ES-LB: " + list.size());
			criteria.put("encontroSELECTPrincipal", Constants.NO);
		}

		String codigoCliente = null;
		String apellido1 = null;
		String apellido2 = null;
		String nombre1 = null;
		String nombre2 = null;
		String codigoRegion = null;
		String codigoZona = null;
		String codigoSeccion = null;
		String deudaBelcorp = null;
		String deudaBelcorpFinal = null;
		String deudaTerceros = null;
		String deudaCastigada = null;
		String deudaCastigadaFinal = null;
		String estadoCliente = null;
		String simboloMoneda = null;
		String descripcionMoneda = null;

		if (list.size() > 1) {

			int index = 0;
			Iterator it = list.iterator();

			while (it.hasNext()) {
				Map obj = (Map) it.next();
				if (index == 0) {
					codigoCliente = (String) obj.get("codigoCliente");
					apellido1 = (String) obj.get("apellido1");
					apellido2 = (String) obj.get("apellido2");
					nombre1 = (String) obj.get("nombre1");
					nombre2 = (String) obj.get("nombre2");
					codigoRegion = (String) obj.get("codigoRegion");
					codigoZona = (String) obj.get("codigoZona");
					codigoSeccion = (String) obj.get("codigoSeccion");
					deudaBelcorp = String.valueOf(obj.get("deudaBelcorp"));
					log.debug("Deuda Belcorp  Actual: " + deudaBelcorp);
					deudaTerceros = String.valueOf(obj.get("deudaTerceros"));
					deudaCastigada = String.valueOf(obj.get("deudaCastigada"));
					estadoCliente = (String) obj.get("estadoCliente");
					simboloMoneda = (String) obj.get("simboloMoneda");
					descripcionMoneda = (String) obj.get("descripcionMoneda");
				}

				if (index == 1) {
					BigDecimal operacion = new BigDecimal(deudaBelcorp);
					operacion = operacion.add(new BigDecimal(String.valueOf(obj.get("deudaBelcorp"))));
					deudaBelcorpFinal = String.valueOf(operacion);
					log.debug("Deuda Belcorp  Histrica: " + String.valueOf(obj.get("deudaBelcorp")));
					log.debug("Deuda Belcorp  Acumulada: " + deudaBelcorpFinal);

					if (deudaBelcorp.equals(deudaCastigada)) {
						deudaCastigadaFinal = String.valueOf(obj.get("deudaCastigada"));
					} else {
						BigDecimal operacionCastigada = new BigDecimal(deudaCastigada);
						operacionCastigada = operacionCastigada.add(new BigDecimal(String.valueOf(obj.get("deudaCastigada"))));
						deudaCastigadaFinal = String.valueOf(operacionCastigada);
						log.debug("Deuda Castigada  Histrica: " + String.valueOf(obj.get("deudaCastigada")));
						log.debug("Deuda Castigada  Acumulada: " + deudaCastigadaFinal);
					}

				}

				index++;
			}

			Map<String, String> obj = new HashMap();

			obj.put("codigoCliente", codigoCliente);
			obj.put("apellido1", apellido1);
			obj.put("apellido2", apellido2);
			obj.put("nombre1", nombre1);
			obj.put("nombre2", nombre2);
			obj.put("codigoRegion", codigoRegion);
			obj.put("codigoZona", codigoZona);
			obj.put("codigoSeccion", codigoSeccion);
			obj.put("deudaBelcorp", deudaBelcorpFinal);
			obj.put("deudaCastigada", deudaCastigada);
			obj.put("deudaCastigadaTmp", deudaCastigadaFinal);
			obj.put("deudaTerceros", deudaTerceros);
			obj.put("estadoCliente", estadoCliente);
			obj.put("simboloMoneda", simboloMoneda);
			obj.put("descripcionMoneda", descripcionMoneda);
			listProspecto.add(obj);

			log.debug("Lista Consolidad Prospecto size: " + listProspecto.size());
			return listProspecto;
			
		} else {
			if (list.size() == 1) {
				Iterator it = list.iterator();

				while (it.hasNext()) {
					Map obj = (Map) it.next();
					codigoCliente = (String) obj.get("codigoCliente");
					apellido1 = (String) obj.get("apellido1");
					apellido2 = (String) obj.get("apellido2");
					nombre1 = (String) obj.get("nombre1");
					nombre2 = (String) obj.get("nombre2");
					codigoRegion = (String) obj.get("codigoRegion");
					codigoZona = (String) obj.get("codigoZona");
					codigoSeccion = (String) obj.get("codigoSeccion");
					deudaBelcorp = String.valueOf(obj.get("deudaBelcorp"));
					deudaTerceros = String.valueOf(obj.get("deudaTerceros"));
					deudaCastigada = String.valueOf(obj.get("deudaCastigada"));
					deudaCastigadaFinal = String.valueOf(obj.get("deudaCastigadaTmp"));
					estadoCliente = (String) obj.get("estadoCliente");
					simboloMoneda = (String) obj.get("simboloMoneda");
					descripcionMoneda = (String) obj.get("descripcionMoneda");
				}

				Map<String, String> obj = new HashMap();

				obj.put("codigoCliente", codigoCliente);
				obj.put("apellido1", apellido1);
				obj.put("apellido2", apellido2);
				obj.put("nombre1", nombre1);
				obj.put("nombre2", nombre2);
				obj.put("codigoRegion", codigoRegion);
				obj.put("codigoZona", codigoZona);
				obj.put("codigoSeccion", codigoSeccion);
				obj.put("deudaBelcorp", deudaBelcorp);
				obj.put("deudaCastigada", deudaCastigada);
				if (deudaBelcorp.equals(deudaCastigada)) {
					obj.put("deudaCastigadaTmp", deudaCastigadaFinal);
				} else {
					obj.put("deudaCastigadaTmp", deudaCastigada);
				}
				obj.put("deudaTerceros", deudaTerceros);
				obj.put("estadoCliente", estadoCliente);
				obj.put("simboloMoneda", simboloMoneda);
				obj.put("descripcionMoneda", descripcionMoneda);
				listProspecto.add(obj);

				log.debug("Lista Prospecto size: " + listProspecto.size());
				return listProspecto;
			} else {
				log.debug("Lista Prospecto size: " + list.size());
				return list;
			}
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.soa.dao.ProspectoDAO#getMotivosBloqueoXConsultora(java.util.Map)
	 */
	public List getMotivosBloqueoXConsultora(Map criteria) {
		List<Map> list = null;
		return list = getSqlMapClientTemplate().queryForList(
				"soa.ProspectoSQL.getMotivosBloqueoXConsultora", criteria);
	}

}
