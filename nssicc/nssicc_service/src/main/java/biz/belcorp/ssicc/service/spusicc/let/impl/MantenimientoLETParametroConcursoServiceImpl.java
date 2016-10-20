package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETParametroConcursoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETParametroConcursoService;

/**
 * Clase de la implementacin de la capa BO (Bussiness Object)
 * 
 * <p>
 * <a href="MantenimientoLETParametroConcursoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Service("spusicc.mantenimientoLETParametroConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLETParametroConcursoServiceImpl extends BaseService implements MantenimientoLETParametroConcursoService {

	
	@Resource(name="spusicc.mantenimientoLETParametroConcursoDAO")
	private MantenimientoLETParametroConcursoDAO mantenimientoLETParametroConcursoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getParametroConcursoList(java.util.Map)
	 */
	public List getParametroConcursoList(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getParametroConcursoList(java.util.Map)");
		List lista = mantenimientoLETParametroConcursoDAO.getParametroConcursoList(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getParametroConcursoList(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getNivelCampaniaList()
	 */
	public List getNivelCampaniaList() {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getNivelCampaniaList()");
		List lista = mantenimientoLETParametroConcursoDAO.getNivelCampaniaList();
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getNivelCampaniaList() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getNivelConcursoList()
	 */
	public List getNivelConcursoList() {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getNivelConcursoList()");
		List lista = mantenimientoLETParametroConcursoDAO.getNivelConcursoList();
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getNivelConcursoList() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getRangoPedidoList()
	 */
	public List getRangoPedidoList() {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getRangoPedidoList()");
		List lista = mantenimientoLETParametroConcursoDAO.getRangoPedidoList();
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getRangoPedidoList() - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#insertParametroConcurso(java.util.Map)
	 */
	public String insertParametroConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - insertParametroConcursoLider(java.util.Map)");
		
		//-- insertParametroConcurso --------------------------------

		//-- Anexar codigo generado
		String codigoConcurso = mantenimientoLETParametroConcursoDAO.getCodigoConcurso(criteria);
		criteria.put("codigoConcurso", codigoConcurso);
		
		//-- Logica
		mantenimientoLETParametroConcursoDAO.insertParametroConcurso(criteria);
		
		//-- insertNivelCampania ---------------------------------
		
		String incrementoPedido[] = (String[])criteria.get("incrementoPedido");
		String metaDosPedido[] = (String[])criteria.get("metaDosPedido");
		String puntajeRanking[] = (String[])criteria.get("puntajeRanking");
		String tipoVariable[] = (String[])criteria.get("tipoVariable");
		
		Map criteria_incremento = null;
		
		//-- nivel 0
		criteria_incremento = new HashMap();
		criteria_incremento.put("codigoPais", criteria.get("codigoPais").toString());
		criteria_incremento.put("codigoConcurso", criteria.get("codigoConcurso").toString());
		criteria_incremento.put("numeroNivel", 0);
		criteria_incremento.put("incrementoPedido", 0);
		criteria_incremento.put("meta", 0);
		criteria_incremento.put("puntajeRanking", 0);
		criteria_incremento.put("tipoVariable", "A");
		criteria_incremento.put("usuario", criteria.get("usuario").toString());
		mantenimientoLETParametroConcursoDAO.insertNivelCampania(criteria_incremento);
		
		//-- niveles
		for (int i = 0; i < incrementoPedido.length; i++) {
			System.out.println("insertNivelCampania["+i+"]:"+incrementoPedido[i]);
			criteria_incremento = new HashMap();
			criteria_incremento.put("codigoPais", criteria.get("codigoPais").toString());
			criteria_incremento.put("codigoConcurso", codigoConcurso);
			criteria_incremento.put("numeroNivel", (i+1));
			criteria_incremento.put("incrementoPedido", Integer.valueOf(incrementoPedido[i]).intValue() );
			criteria_incremento.put("meta", Integer.valueOf(metaDosPedido[i]).intValue() );
			criteria_incremento.put("puntajeRanking", Integer.valueOf(puntajeRanking[i]).intValue() );
			criteria_incremento.put("tipoVariable", tipoVariable[i]);
			criteria_incremento.put("usuario", criteria.get("usuario").toString());
			mantenimientoLETParametroConcursoDAO.insertNivelCampania(criteria_incremento);
		}
		/* INI JJ PER-SiCC-2012-0201 */
		//-- nivel 99
		criteria_incremento = new HashMap();
		criteria_incremento.put("codigoPais", criteria.get("codigoPais").toString());
		criteria_incremento.put("codigoConcurso", criteria.get("codigoConcurso").toString());
		criteria_incremento.put("numeroNivel", 99);
		criteria_incremento.put("incrementoPedido", 0);
		criteria_incremento.put("meta", 0);
		criteria_incremento.put("puntajeRanking", 0);
		criteria_incremento.put("tipoVariable", "A");
		criteria_incremento.put("usuario", criteria.get("usuario").toString());
		mantenimientoLETParametroConcursoDAO.insertNivelCampania(criteria_incremento);
		/* FIN JJ PER-SiCC-2012-0201 */
		//-- insertRangoPedido --------------------------------------

		String rangoPedidoPre[] = (String[])criteria.get("rangoPedidoPre");
		Map criteria_rango = null;

		//-- nivel 0
		criteria_rango = new HashMap();
		criteria_rango.put("codigoPais", criteria.get("codigoPais").toString());
		criteria_rango.put("codigoConcurso", criteria.get("codigoConcurso").toString());
		criteria_rango.put("numeroRango", 0);
		criteria_rango.put("cantidadPedido", 0);
		criteria_rango.put("usuario", criteria.get("usuario").toString());
		mantenimientoLETParametroConcursoDAO.insertRangoPedido(criteria_rango);

		//-- niveles 
		for (int i = 0; i < rangoPedidoPre.length; i++) {
			System.out.println("rangoPedidoPre["+i+"]:"+rangoPedidoPre[i]);
			criteria_rango = new HashMap();
			criteria_rango.put("codigoPais", criteria.get("codigoPais").toString());
			criteria_rango.put("codigoConcurso", codigoConcurso);
			criteria_rango.put("numeroRango", (i+1));
			criteria_rango.put("cantidadPedido", Integer.valueOf(rangoPedidoPre[i]) );
			criteria_rango.put("usuario", criteria.get("usuario").toString());
			mantenimientoLETParametroConcursoDAO.insertRangoPedido(criteria_rango);
		}
		
		//-- insertNivelConcurso ------------------------------------

		String metaPedidoCon[] = (String[])criteria.get("metaPedidoCon");
		Map criteria_metaPed = null;
		
		//-- nivel 0
		criteria_metaPed = new HashMap();
		criteria_metaPed.put("codigoPais", criteria.get("codigoPais").toString());
		criteria_metaPed.put("codigoConcurso", criteria.get("codigoConcurso").toString());
		criteria_metaPed.put("nivelConcurso", 0);
		criteria_metaPed.put("porcentajeIncremento", 0);
		criteria_metaPed.put("usuario", criteria.get("usuario").toString());
		mantenimientoLETParametroConcursoDAO.insertNivelConcurso(criteria_metaPed);

		//-- niveles
		for (int i = 0; i < metaPedidoCon.length; i++) {
			System.out.println("metaPedidoCon["+i+"]:"+metaPedidoCon[i]);
			criteria_metaPed = new HashMap();
			criteria_metaPed.put("codigoPais", criteria.get("codigoPais").toString());
			criteria_metaPed.put("codigoConcurso", codigoConcurso);
			criteria_metaPed.put("nivelConcurso", (i+1));
			criteria_metaPed.put("porcentajeIncremento", Float.valueOf(metaPedidoCon[i]) );
			criteria_metaPed.put("usuario", criteria.get("usuario").toString());
			mantenimientoLETParametroConcursoDAO.insertNivelConcurso(criteria_metaPed);
		}
		
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - insertParametroConcursoLider(java.util.Map) - Concurso:" + codigoConcurso);
		return codigoConcurso;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#updateParametroConcurso(java.util.Map)
	 */
	public void updateParametroConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - updateParametroConcurso(java.util.Map)");
		
		int validaNivelCampania = Integer.valueOf(criteria.get("validaNivelCampania").toString());
		int validaNivelConcurso = Integer.valueOf(criteria.get("validaNivelConcurso").toString());
		int validaRangoPedido = Integer.valueOf(criteria.get("validaRangoPedido").toString());
		
		//-- updateParametroConcurso --------------------------------------------------------------
		
		mantenimientoLETParametroConcursoDAO.updateParametroConcurso(criteria);

		//-- Eliminar datos de pestaas -----------------------------------------------------------
		
		if(validaRangoPedido == 0)
			mantenimientoLETParametroConcursoDAO.deleteRangoPedido(criteria);
		if(validaNivelConcurso == 0)
			mantenimientoLETParametroConcursoDAO.deleteNivelConcurso(criteria);		
		
		//-- Insertar datos pestaas --------------------------------------------------------------
		
		//-- insertNivelCampania ---------------------------------
		if(validaNivelCampania == 0){
			
			String incrementoPedido[] = (String[])criteria.get("incrementoPedido");
			String metaDosPedido[] = (String[])criteria.get("metaDosPedido");
			String puntajeRanking[] = (String[])criteria.get("puntajeRanking");			
			String tipoVariable[] = (String[])criteria.get("tipoVariable");
			
			Map criteria_incremento = null;
			/* INI JJ PER-SiCC-2012-0201 */
			//-- niveles
			for (int i = 0; i < incrementoPedido.length; i++) {
				System.out.println("insertNivelCampania["+i+"]:"+incrementoPedido[i]);
				criteria_incremento = new HashMap();
				criteria_incremento.put("codigoPais", criteria.get("codigoPais").toString());
				criteria_incremento.put("codigoConcurso", criteria.get("codigoConcurso").toString());
				criteria_incremento.put("numeroNivel", (i+1));
				criteria_incremento.put("incrementoPedido", Integer.valueOf(incrementoPedido[i]).intValue() );
				criteria_incremento.put("meta", Integer.valueOf(metaDosPedido[i]).intValue() );
				criteria_incremento.put("puntajeRanking", Integer.valueOf(puntajeRanking[i]).intValue() );
				criteria_incremento.put("tipoVariable", tipoVariable[i]);
				criteria_incremento.put("usuario", criteria.get("usuario").toString());
				mantenimientoLETParametroConcursoDAO.updateNivelCampania(criteria_incremento);
			}
			/* FIN JJ PER-SiCC-2012-0201 */
		}
		
		//-- insertRangoPedido --------------------------------------
		if(validaRangoPedido == 0){
			
			String rangoPedidoPre[] = (String[])criteria.get("rangoPedidoPre");
			Map criteria_rango = null;
			
			//-- nivel 0
			criteria_rango = new HashMap();
			criteria_rango.put("codigoPais", criteria.get("codigoPais").toString());
			criteria_rango.put("codigoConcurso", criteria.get("codigoConcurso").toString());
			criteria_rango.put("numeroRango", 0);
			criteria_rango.put("cantidadPedido", 0);
			criteria_rango.put("usuario", criteria.get("usuario").toString());
			mantenimientoLETParametroConcursoDAO.insertRangoPedido(criteria_rango);
	
			//-- niveles 
			for (int i = 0; i < rangoPedidoPre.length; i++) {
				System.out.println("rangoPedidoPre["+i+"]:"+rangoPedidoPre[i]);
				criteria_rango = new HashMap();
				criteria_rango.put("codigoPais", criteria.get("codigoPais").toString());
				criteria_rango.put("codigoConcurso", criteria.get("codigoConcurso").toString());
				criteria_rango.put("numeroRango", (i+1));
				criteria_rango.put("cantidadPedido", Integer.valueOf(rangoPedidoPre[i]) );
				criteria_rango.put("usuario", criteria.get("usuario").toString());
				mantenimientoLETParametroConcursoDAO.insertRangoPedido(criteria_rango);
			}

		}
		
		//-- insertNivelConcurso ------------------------------------
		if(validaNivelConcurso == 0){
			
			String metaPedidoCon[] = (String[])criteria.get("metaPedidoCon");
			Map criteria_metaPed = null;
			
			//-- nivel 0
			criteria_metaPed = new HashMap();
			criteria_metaPed.put("codigoPais", criteria.get("codigoPais").toString());
			criteria_metaPed.put("codigoConcurso", criteria.get("codigoConcurso").toString());
			criteria_metaPed.put("nivelConcurso", 0);
			criteria_metaPed.put("porcentajeIncremento", 0);
			criteria_metaPed.put("usuario", criteria.get("usuario").toString());
			mantenimientoLETParametroConcursoDAO.insertNivelConcurso(criteria_metaPed);
	
			//-- niveles
			for (int i = 0; i < metaPedidoCon.length; i++) {
				System.out.println("metaPedidoCon["+i+"]:"+metaPedidoCon[i]);
				criteria_metaPed = new HashMap();
				criteria_metaPed.put("codigoPais", criteria.get("codigoPais").toString());
				criteria_metaPed.put("codigoConcurso", criteria.get("codigoConcurso").toString());
				criteria_metaPed.put("nivelConcurso", (i+1));
				criteria_metaPed.put("porcentajeIncremento", Float.valueOf(metaPedidoCon[i]) );
				criteria_metaPed.put("usuario", criteria.get("usuario").toString());
				mantenimientoLETParametroConcursoDAO.insertNivelConcurso(criteria_metaPed);
			}
			
		}

		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - updateParametroConcurso(java.util.Map)");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getNivelCampaniaConsult(java.util.Map)
	 */
	public List getNivelCampaniaConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getNivelCampaniaConsult(java.util.Map)");
		List lista = mantenimientoLETParametroConcursoDAO.getNivelCampaniaConsult(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getNivelCampaniaConsult(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getNivelConcursoConsult(java.util.Map)
	 */
	public List getNivelConcursoConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getNivelConcursoConsult(java.util.Map)");
		List lista = mantenimientoLETParametroConcursoDAO.getNivelConcursoConsult(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getNivelConcursoConsult(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getParametroConcursoConsult(java.util.Map)
	 */
	public List getParametroConcursoConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getParametroConcursoConsult(java.util.Map)");
		List lista = mantenimientoLETParametroConcursoDAO.getParametroConcursoConsult(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getParametroConcursoConsult(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getRangoPedidoConsult(java.util.Map)
	 */
	public List getRangoPedidoConsult(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getRangoPedidoConsult(java.util.Map)");
		List lista = mantenimientoLETParametroConcursoDAO.getRangoPedidoConsult(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getRangoPedidoConsult(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getValidaNivelCampaEditable(java.util.Map)
	 */
	public String getValidaNivelCampaEditable(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getValidaNivelCampaEditable(java.util.Map)");
		String resultado = mantenimientoLETParametroConcursoDAO.getValidaNivelCampaEditable(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getValidaNivelCampaEditable(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getValidaNivelConcuEditable(java.util.Map)
	 */
	public String getValidaNivelConcuEditable(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getValidaNivelConcuEditable(java.util.Map)");
		String resultado = mantenimientoLETParametroConcursoDAO.getValidaNivelConcuEditable(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getValidaNivelConcuEditable(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getValidaPeriodoEditable(java.util.Map)
	 */
	public String getValidaPeriodoEditable(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getValidaPeriodoEditable(java.util.Map)");
		String resultado = mantenimientoLETParametroConcursoDAO.getValidaPeriodoEditable(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getValidaPeriodoEditable(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getValidaRangoPedidoEditable(java.util.Map)
	 */
	public String getValidaRangoPedidoEditable(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - getValidaRangoPedidoEditable(java.util.Map)");
		String resultado = mantenimientoLETParametroConcursoDAO.getValidaRangoPedidoEditable(criteria);
		log.info("Salio a MantenimientoLETParametroConcursoServiceImpl - getValidaRangoPedidoEditable(java.util.Map) - Resultado:"+resultado);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#deleteParametroConcurso(java.util.Map)
	 */
	public void deleteParametroConcurso(Map criteria) {
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - deleteParametroConcurso(java.util.Map)");
		mantenimientoLETParametroConcursoDAO.getEliminaParametroConcursoLider(criteria);
		mantenimientoLETParametroConcursoDAO.getEliminaParametroNivelCampania(criteria);
		mantenimientoLETParametroConcursoDAO.getEliminaParametroRangoPedido(criteria);
		mantenimientoLETParametroConcursoDAO.getEliminaParametroNivelConcurso(criteria);
		log.info("Entro a MantenimientoLETParametroConcursoServiceImpl - deleteParametroConcurso(java.util.Map)");
	}

	/**
	 * @return the mantenimientoLETParametroConcursoDAO
	 */
	public MantenimientoLETParametroConcursoDAO getMantenimientoLETParametroConcursoDAO() {
		return mantenimientoLETParametroConcursoDAO;
	}

	/**
	 * @param mantenimientoLETParametroConcursoDAO the mantenimientoLETParametroConcursoDAO to set
	 */
	public void setMantenimientoLETParametroConcursoDAO(
			MantenimientoLETParametroConcursoDAO mantenimientoLETParametroConcursoDAO) {
		this.mantenimientoLETParametroConcursoDAO = mantenimientoLETParametroConcursoDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getTipoVariablesList()
	 */
	public List getTipoVariablesList() {
		return mantenimientoLETParametroConcursoDAO.getTipoVariablesList();
	}
	
	/* INI JJ PER-SiCC-2012-0201 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETParametroConcursoService#getDescripcionNivelesCampaniaList()
	 */
	public List getDescripcionNivelesCampaniaList() {
		return mantenimientoLETParametroConcursoDAO.getDescripcionNivelesCampaniaList();
	}
	/* FIN JJ PER-SiCC-2012-0201 */	
}