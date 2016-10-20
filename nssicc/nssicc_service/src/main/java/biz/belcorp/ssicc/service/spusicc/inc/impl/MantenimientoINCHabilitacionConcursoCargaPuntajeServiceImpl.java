package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ProcesarINCConfiguracionConcursoCuponElectronico;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.CargaPuntaje;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;

/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoINCHabilitacionConcursoCargaPuntajeServiceImpl extends BaseService 
implements MantenimientoINCHabilitacionConcursoCargaPuntajeService {
	
	@Resource(name="spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeDAO")
	private MantenimientoINCHabilitacionConcursoCargaPuntajeDAO mantenimientoINCpuntajeDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListParametrosConcursosActivos()
	 */
	public List getListParametrosConcursosActivos() {
		return mantenimientoINCpuntajeDAO.getListParametrosConcursosActivos();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#insertConcursoHabilitado(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConcursoHabilitado(Map criteria) {
		 //lista de oid seleccionado para insertar los que ya no estan 
		List listaConcursoSeleccionado = (List) criteria.get("listaConcurso");
		String login = (String)criteria.get("login");
		//obteniendo lista de concursos ya habilitados 
		List listConcursosHabilitado = getListConcursoHabilitado();
		
		if(listConcursosHabilitado.size()==0){
			insertCargaPuntaje(listaConcursoSeleccionado,login);
			return ;
		}	
		
		setConcursoSeleccionados(listaConcursoSeleccionado,listConcursosHabilitado,login);
	}

	/**
	 * Compara la lista para saber si el consurso debe seer elimina, registrado o no hacer nada con el 
	 * @param listaConcursoSeleccionado
	 * @param listConcursosHabilitado
	 */
	private void setConcursoSeleccionados(List listaConcursoSeleccionado,
			List listConcursosHabilitado,String login) {
		int sizeConcursoSeleccionado = listaConcursoSeleccionado.size();
		int sizeConcursoHabilitado = listConcursosHabilitado.size();
		
		for(int i=0;i<sizeConcursoHabilitado;i++){
			Base base = (Base)listConcursosHabilitado.get(i);
			eliminaConcursoHabilitados(base.getCodigo(),listaConcursoSeleccionado);			
		}	
		
		for(int i=0;i<sizeConcursoSeleccionado;i++){
			String oidConcurso = (String)listaConcursoSeleccionado.get(i);
			insertaConcursoSeleccionado(oidConcurso,listConcursosHabilitado,login);			
		}
		
	}

	private void insertaConcursoSeleccionado(String oidConcurso,
			List listConcursosHabilitado,String codigoUsuario) {
		boolean valor=false;
		Iterator it = listConcursosHabilitado.iterator();
		while(it.hasNext()){
			Base b= (Base)it.next();
			if(oidConcurso.equals(b.getCodigo())){
				valor=true;
				break;
			}
		}
		if(!valor){
			CargaPuntaje cargaPuntaje = new CargaPuntaje();
			cargaPuntaje.setOid(oidConcurso);
			cargaPuntaje.setCodigoUsuario(codigoUsuario);
			insertCargaPuntaje(cargaPuntaje);
		}
		
	}

	private void eliminaConcursoHabilitados(String oidConcurso,
			List listaConcursoSeleccionado) {
		boolean valor=false;
		Iterator it = listaConcursoSeleccionado.iterator();
		while(it.hasNext()){
			String oid= (String)it.next();
			if(oidConcurso.equals(oid)){
				valor=true;
				break;
			}
		}
		if(!valor){
			deleteCargaPuntaje(oidConcurso);
		}
	}

	/**
	 * Sobrecarga del metodo insercion de carga de puntaje
	 * @param oidConcurso
	 * @param login
	 */
	private void insertCargaPuntaje(String oidConcurso, String login) {
		CargaPuntaje cargaPuntaje = new CargaPuntaje();
		cargaPuntaje.setOid(oidConcurso);
		cargaPuntaje.setCodigoUsuario(login);
		insertCargaPuntaje(cargaPuntaje);
		
	}

	/**
	 * elimina la carga de puntaje
	 * @param oidConcurso
	 */
	private void deleteCargaPuntaje(String oidConcurso) {
		mantenimientoINCpuntajeDAO.deleteCargaPuntaje(oidConcurso);
		
	}

	/**
	 * Inserta la carga de Puntaje
	 * @param cargaPuntaje
	 */
	private void insertCargaPuntaje(CargaPuntaje cargaPuntaje) {
		mantenimientoINCpuntajeDAO.insertCargaPuntaje(cargaPuntaje);
	}

	/**
	 * se encaga de hacer el rgsitro de habilitacion de los consursos
	 * @param list
	 * @param login
	 */
	private void insertCargaPuntaje(List listConcursoSeleccionados,String login ) {
		mantenimientoINCpuntajeDAO.insertCargaPuntaje(listConcursoSeleccionados,login);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListConcursoHabilitado()
	 */
	public List getListConcursoHabilitado() {
		return mantenimientoINCpuntajeDAO.getListConcursoHabilitado();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListMotivoConcursos()
	 */
	public List getListMotivoConcursos() {
		return mantenimientoINCpuntajeDAO.getListMotivoConcursos();
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#deleteConcursoHabilitado()
	 */
	public void deleteConcursoHabilitado() {
		mantenimientoINCpuntajeDAO.deleteConcursoHabilitado();		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListConcursosActivosObs(java.lang.String)
	 */
	public List getListConcursosActivosObs(String valObservacion) {
		return mantenimientoINCpuntajeDAO.getListConcursosActivosObs(valObservacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListConcursosVigentesCerrados()
	 */
	public List getListConcursosVigentesCerrados() {
		return mantenimientoINCpuntajeDAO.getListConcursosVigentesCerrados();
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListConcursosVigentesCerradosGanadoras()
	 */
	public List getListConcursosVigentesCerradosGanadoras() {
		return mantenimientoINCpuntajeDAO.getListConcursosVigentesCerradosGanadoras();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListConcursoCyzo()
	 */
	public List getListConcursoCyzo() {
		return mantenimientoINCpuntajeDAO.getListConcursoCyzo();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListConcursoReconocido()
	 */
	public List getListConcursoReconocido() {
		return mantenimientoINCpuntajeDAO.getListConcursoReconocido();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getIndicadorProdExig(java.lang.Integer)
	 */
	public int getIndicadorProdExig(Integer oidConcurso) {
		return mantenimientoINCpuntajeDAO.getIndicadorProdExig(oidConcurso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getListaConcursosCEActivosByConcursoPeriodo(java.util.Map)
	 */
	public List getListaConcursosCEActivosByConcursoPeriodo(Map criteria) {
		return mantenimientoINCpuntajeDAO.getListaConcursosCEActivosByConcursoPeriodo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#getDatosConcursoCuponElectronico(java.util.Map)
	 */
	public Map getDatosConcursoCuponElectronico(Map params) {
		return mantenimientoINCpuntajeDAO.getDatosConcursoCuponElectronico(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECAtencionesMasivasService#getValidarCodigoConsultora(java.util.Map)
	 */
	public String getValidarExisteConcursoCuponElectronico(Map criteria) {
		return mantenimientoINCpuntajeDAO.getValidarExisteConcursoCuponElectronico(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#insertMantenimientoINCConfiguracionConcursoCE(biz.belcorp.ssicc.model.ProcesarINCConfiguracionConcursoCuponElectronico)
	 */
	public String insertMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico) {

		// validacion Si consultora Existe
		Map criteria = new HashMap();
		
		criteria.put("codigoPais", procesarINCConfiguracionConcursoCuponElectronico.getCodigoPais());
		criteria.put("codigoConcurso", procesarINCConfiguracionConcursoCuponElectronico.getCodigoConcurso());
		
		String existe = this.getValidarExisteConcursoCuponElectronico(criteria);
		
		log.debug("Existe: " + existe);
		
		if (StringUtils.equals(existe, Constants.NUMERO_CERO)) {
			
			// Concurso no existe en la tabla
			String cadenaDescripion = procesarINCConfiguracionConcursoCuponElectronico.getDescripcionConcurso().toString();
			String[] parte = cadenaDescripion.split(" - ");
						
			String numeroConcurso = parte[0];
			String descripcionConcurso = parte[1];
			
			procesarINCConfiguracionConcursoCuponElectronico.setNumeroConcurso(numeroConcurso);
			procesarINCConfiguracionConcursoCuponElectronico.setDescripcionConcurso(descripcionConcurso);
			procesarINCConfiguracionConcursoCuponElectronico.setIndicadorActivo(Constants.FLAG_DEFAULT);
			procesarINCConfiguracionConcursoCuponElectronico.setEstado(Constants.FLAG_DEFAULT);
			
			mantenimientoINCpuntajeDAO.insertMantenimientoINCConfiguracionConcursoCE(procesarINCConfiguracionConcursoCuponElectronico);
		}
		
		return existe;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCHabilitacionConcursoCargaPuntajeService#updateMantenimientoINCConfiguracionConcursoCE(biz.belcorp.ssicc.model.ProcesarINCConfiguracionConcursoCuponElectronico)
	 */
	public void updateMantenimientoINCConfiguracionConcursoCE(ProcesarINCConfiguracionConcursoCuponElectronico procesarINCConfiguracionConcursoCuponElectronico) {
		mantenimientoINCpuntajeDAO.updateMantenimientoINCConfiguracionConcursoCE(procesarINCConfiguracionConcursoCuponElectronico);		
	}
		
}