/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.comision.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scdf.ZonaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Zona;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionCalculo;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionClasificacion;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisionEscalonada;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosComisiones;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.DatosRegionesSubGerencia;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.ZonaDemandaAnticipada;
import biz.belcorp.ssicc.dao.spusicc.comision.MantenimientoCOMComisionDAO;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionCabecera;
import biz.belcorp.ssicc.dao.spusicc.comision.model.CalificacionComisionDetalle;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMComisionService;



/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a> 
 *
 */

@Service("spusicc.mantenimientoCOMComisionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCOMComisionServiceImpl extends BaseService implements MantenimientoCOMComisionService {
	
	@Resource(name="spusicc.mantenimientoCOMComisionDAO")
	MantenimientoCOMComisionDAO mantenimientoCOMComisionDAO;
	
	@Resource(name="scdf.zonaDAO")
	ZonaDAO zonaDAO;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getComisionesList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * retorna la lista de comisiones
	 */
	public List getComisionesList(CalificacionComisionCabecera calificacionComisionCabecera){
		return mantenimientoCOMComisionDAO.getComisionesList(calificacionComisionCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getNivelList(java.lang.String)
	 * retorna la lista de niveles
	 */
	public List getNivelList(String codigo) {
		return mantenimientoCOMComisionDAO.getNivelList(codigo);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#deleteComisionCabeceraAndChild(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * Borra la cabecera de la comisin y todo lo que pertenece a dicha comisin
	 */
	public void deleteComisionCabeceraAndChild(
			CalificacionComisionCabecera calificacionComisionCabecera) {
		
			mantenimientoCOMComisionDAO.deleteComisionDetalle(calificacionComisionCabecera);
			mantenimientoCOMComisionDAO.deleteComisionCabecera(calificacionComisionCabecera);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getComisionDetalleList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera)
	 * retorna la lista de detalle de comisin
	 */
	public List getComisionDetalleList(CalificacionComisionCabecera calificacionComisionCabecera) {
		return mantenimientoCOMComisionDAO.getComisionDetalleList(calificacionComisionCabecera);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#updateComisionCabeceraAndDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera, java.util.List)
	 * actualiza cabecera y detalle de la comisin
	 */
	public void updateComisionCabeceraAndDetalle(CalificacionComisionCabecera updateComisionCabecera,	List cabeceraList) {
		mantenimientoCOMComisionDAO.updateComisionCabeceraAndDetalle(updateComisionCabecera);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#insertComisionCabeceraAndDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionCabecera, java.util.List)
	 * Agrega cabecera y detalle de la comisin
	 */
	public void insertComisionCabeceraAndDetalle(CalificacionComisionCabecera calificacionComisionCabecera,	List detalList) {
		
		String codigoComision = mantenimientoCOMComisionDAO.getSecuenciaNextValue();
		calificacionComisionCabecera.setCodigoComision(codigoComision);
		mantenimientoCOMComisionDAO.insertComisionCabecera(calificacionComisionCabecera);
		if(detalList !=null)
			for (Iterator iterator = detalList.iterator(); iterator.hasNext();) {
				CalificacionComisionDetalle calificacionComisionDetalle = (CalificacionComisionDetalle) iterator.next();
				calificacionComisionDetalle.setCodigoComision(codigoComision);			
				mantenimientoCOMComisionDAO.insertComisionDetalle(calificacionComisionDetalle);
				
			}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#insertComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Agrega detalle de la comisin
	 */
	public void insertComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMComisionDAO.insertComisionDetalle(calificacionComisionDetalle);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#updateComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Actualiza Detalle de la comisin
	 */
	public void updateComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMComisionDAO.updateComisionDetalle(calificacionComisionDetalle);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#deleteComisionDetalle(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * Borra detalle de la comisin
	 */
	public void deleteComisionDetalle(CalificacionComisionDetalle calificacionComisionDetalle) {
		mantenimientoCOMComisionDAO.deleteComisionDetalle(calificacionComisionDetalle);

	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getComisionDetalleList(biz.belcorp.ssicc.spusicc.comision.dao.model.CalificacionComisionDetalle)
	 * retorna la lista de detalle de comisin
	 */
	public List getComisionDetalleList(CalificacionComisionDetalle calificacionComisionDetalle) {
		return mantenimientoCOMComisionDAO.getComisionDetalleList(calificacionComisionDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getZonasDemandaAnticipadaDetalle(java.util.Map)
	 */
	public List getZonasDemandaAnticipadaDetalle(Map criteria) {
		return mantenimientoCOMComisionDAO.getZonasDemandaAnticipadaDetalle(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#insertZonasDemandaAnticipada(java.util.Map)
	 */
	public void insertZonasDemandaAnticipada(Map criteria) {
		mantenimientoCOMComisionDAO.insertZonasDemandaAnticipada(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#updateZonasDemandaAnticipada(java.util.Map)
	 */
	public void updateZonasDemandaAnticipada(Map criteria) {
		mantenimientoCOMComisionDAO.updateZonasDemandaAnticipada(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#deleteZonasDemandaAnticipada(java.util.Map)
	 */
	public void deleteZonasDemandaAnticipada(Map criteria) {
		mantenimientoCOMComisionDAO.deleteZonasDemandaAnticipada(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getZonaDemandaAnticipada(java.util.Map)
	 */
	public ZonaDemandaAnticipada getZonaDemandaAnticipada(Map criteria) {
		return mantenimientoCOMComisionDAO.getZonaDemandaAnticipada(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getValidarZonasDemanta(java.util.Map)
	 */
	public int getValidarZonasDemanda(Map criteria) {
		return mantenimientoCOMComisionDAO.getValidarZonasDemanda(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getValidaExisteZona(java.util.Map)
	 */
	public int getValidaExisteZona(Map criteria) {
		return mantenimientoCOMComisionDAO.getValidaExisteZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getComisionesRecuperacionDetalle(java.util.Map)
	 */
	public List getComisionesRecuperacionDetalle(Map criteria) {
		return mantenimientoCOMComisionDAO.getComisionesRecuperacionDetalle(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getCodigoComisionSiguiente()
	 */
	public String getCodigoComisionSiguiente() {
		return mantenimientoCOMComisionDAO.getCodigoComisionSiguiente();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#insertComisiones(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisiones, java.util.List, java.util.List, java.util.List)
	 */
	public void insertComisiones(DatosComisiones datosComisiones,
			List comisionRegionesList, List comisionDatosComisionList,
			List comisionComisionEscalonadaList,List comisionClasificacion, Map criteria) {
		log.debug("servicio de insertar comisiones");
		String codigoComision = (String)criteria.get("codigoComision");
		String codigoPais = (String) criteria.get("codigoPais");
		String codigoMarca = (String) criteria.get("codigoMarca");
		String codigoCanal = (String) criteria.get("codigoCanal");
		String indicadorComisionEscalonada= (String)criteria.get("indicadorComisionEscalonada");
		
		boolean indicadorInsertarDatosComision = false;
		
		if (log.isDebugEnabled()) {
			log.debug(criteria);
		}
		
		//insertar en COM_COMI
		mantenimientoCOMComisionDAO.insertComisiones(datosComisiones);
		
		criteria.put("codigoComision",codigoComision);
		int oidComision = mantenimientoCOMComisionDAO.getOidComisiones(criteria);
		log.debug("oid de Comision: "+oidComision);
		criteria.put("oidComision",oidComision);
		
		
		if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04)) {
			//insertar Clasificacion
			if (comisionClasificacion != null && comisionClasificacion.size() > 0) {
				for (int i = 0; i < comisionClasificacion.size(); i++) {
					DatosComisionClasificacion a = (DatosComisionClasificacion)comisionClasificacion.get(i);
					a.setCodigoUsuario(datosComisiones.getNombreUsuario());
					a.setCodigoComision(datosComisiones.getCodigoComision());
					a.setEstado(Constants.ACTIVO);
					mantenimientoCOMComisionDAO.insertClasificacion(a);
				}
			}
		}

		int orden = 0;
		log.debug("Antes de entrar al bucle de Comision Regiones 'comisionRegionesList'");
		if (comisionRegionesList != null && comisionRegionesList.size() > 0) {
			log.debug("'comisionRegionesList' es diferente de Null");
			for (int i = 0; i < comisionRegionesList.size(); i++) {
				DatosRegionesSubGerencia dr = new DatosRegionesSubGerencia();
				dr = (DatosRegionesSubGerencia)comisionRegionesList.get(i);
				orden = i +1;
				dr.setOidComision(oidComision);
				dr.setCodigoCanal(codigoCanal);
				dr.setCodigoPais(codigoPais);
				dr.setCodigoMarca(codigoMarca);
				dr.setOrden(orden);
				mantenimientoCOMComisionDAO.insertDetalleComisiones(dr);
			}
		}
		
		mantenimientoCOMComisionDAO.insertComisionCobranza(criteria);
		
		int oidCobraza = mantenimientoCOMComisionDAO.getOidCobranzaComisiones(criteria);
		
		log.debug("Codigo de cobranza es: " + oidCobraza);
		
		
		//	Datos Recuperacin
		if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_05) || 
				StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_07) || 
				(StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) && StringUtils.equals(indicadorComisionEscalonada, "0"))){
			log.debug("mi codigo escalonada es " + indicadorComisionEscalonada);
			//Cuando es 0 "cero"
			if (comisionDatosComisionList.size() > 0 && comisionDatosComisionList != null) {
				for (int i = 0; i < comisionDatosComisionList.size(); i++) {
					DatosComisionCalculo b  =new DatosComisionCalculo();
					b = (DatosComisionCalculo)comisionDatosComisionList.get(i);
					b.setOidCobranza(oidCobraza);
					mantenimientoCOMComisionDAO.insertComisionCalculada(b);
				}
			}
			indicadorInsertarDatosComision = true;
		}
		
		if (!indicadorInsertarDatosComision) {
			//	Comisin Escalonada
			if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_01) ||
					StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) ||
					StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_03) ||
					StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04) ||
					(StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) && 
							StringUtils.equals(indicadorComisionEscalonada, "1"))) {
				
				log.debug("mi codigo escalonada es " + indicadorComisionEscalonada);
				//Cuando es 1 "uno"
				if (comisionComisionEscalonadaList.size() > 0 && comisionComisionEscalonadaList != null) {
					for (int i = 0; i < comisionComisionEscalonadaList.size(); i++) {
						DatosComisionEscalonada b = new DatosComisionEscalonada();
						b = (DatosComisionEscalonada)comisionComisionEscalonadaList.get(i);
						b.setCodigoCobranza(oidCobraza);
						
						if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_01) ||
								StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02)) {
							b.setNroDiasRecuperacion(Integer.parseInt(datosComisiones.getNroDiasRecuperacion()));
							b.setNroTramo(i+1);
							mantenimientoCOMComisionDAO.insertComisionEscalonada(b);
						
						}else if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_03)) {
							
							b.setValPorcRecuperacionFinal(b.getValPorcFinal());
							b.setValPorcRecuperacionInicial(b.getValPorcInicial());
							
							b.setNroTramo(i+1);
							mantenimientoCOMComisionDAO.insertComisionEscalonada(b);
							
						}else if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04)) {
							b.setValPorcRecuperacionFinal(b.getValPorcFinalRecuperacion());
							b.setValPorcRecuperacionInicial(b.getValPorcInicialRecuperacion());
							b.setNroDiasRecuperacion(Integer.parseInt(datosComisiones.getNroDiasRecuperacion()));
							b.setNroTramo(i+1);
							mantenimientoCOMComisionDAO.insertComisionEscalonada(b);
						}
					}
				}
			}
		}
		
		log.debug("termine mi servicio de registro existoso");
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getDatosComisiones(java.util.Map)
	 */
	public DatosComisiones getDatosComisionRecuperacion(Map criteria) {
		return mantenimientoCOMComisionDAO.getDatosComisionRecuperacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#updateComisiones(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisiones, java.util.List, java.util.List, java.util.List, java.util.Map)
	 */
	public void updateComisiones(DatosComisiones datosComisiones,
			List comisionRegionesList, List comisionDatosComisionList,
			List comisionComisionEscalonadaList, List comisionClasificacion, Map criteria) {
		
		log.debug("servicio de insertar comisiones");
		boolean indicadorInsertarDatosComision = false;
		
		String codigoPais = (String) criteria.get("codigoPais");
		String codigoMarca = (String) criteria.get("codigoMarca");
		String codigoCanal = (String) criteria.get("codigoCanal");
		String indicadorComisionEscalonada= (String) criteria.get("indicadorComisionEscalonada");
		criteria.put("codigoComision", datosComisiones.getCodigoComision());
		criteria.put("oidComision", datosComisiones.getOidComision());
		
		List tempDatosComisionesZonasSubGerencia = getDatosComisionesZonasSubGerencia(criteria);
		List tempDatosComisiones = getDatosComisiones(criteria);
		List tempDatosComisionEscalonada = getComisionEscalonada(criteria);
		List tempDatosComisionClasificacion = getClasificacionByCodigoComisionList(criteria);
		int cantCobranza = mantenimientoCOMComisionDAO.getCantCobranza(criteria);
		int oidCobrazaEliminar = mantenimientoCOMComisionDAO.getOidCobranzaComisiones(criteria);
		
		criteria.put("oidCobranza", oidCobrazaEliminar);
		mantenimientoCOMComisionDAO.updateComisiones(datosComisiones);
		
		if (tempDatosComisionEscalonada != null) 
			mantenimientoCOMComisionDAO.deleteDatosEscalonada(criteria);
		
		if (tempDatosComisiones != null) 
			mantenimientoCOMComisionDAO.deleteDatosComisiones(criteria);
		
		if (cantCobranza != 0) 
			mantenimientoCOMComisionDAO.deleteDatosCobranza(criteria);
		
		if (tempDatosComisionesZonasSubGerencia != null)
			mantenimientoCOMComisionDAO.deleteZonasSubGerencia(criteria);
		
		if(tempDatosComisionClasificacion != null)
			mantenimientoCOMComisionDAO.deleteClasificacion(criteria);
			
		if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04)) {
			//insertar Clasificacion
			if (comisionClasificacion != null && comisionClasificacion.size() > 0) {
				for (int i = 0; i < comisionClasificacion.size(); i++) {
					DatosComisionClasificacion a = (DatosComisionClasificacion)comisionClasificacion.get(i);
					a.setCodigoUsuario(datosComisiones.getNombreUsuario());
					a.setCodigoComision(datosComisiones.getCodigoComision());
					a.setEstado(Constants.ACTIVO);
					mantenimientoCOMComisionDAO.insertClasificacion(a);
				}
			}
		}
		int orden = 0;
		if (comisionRegionesList.size()!=0) {
			for (int i = 0; i < comisionRegionesList.size(); i++) {
				DatosRegionesSubGerencia dr = new DatosRegionesSubGerencia();
				dr = (DatosRegionesSubGerencia)comisionRegionesList.get(i);
				orden = i +1;
				dr.setOidComision(datosComisiones.getOidComision());
				dr.setCodigoCanal(codigoCanal);
				dr.setCodigoPais(codigoPais);
				dr.setCodigoMarca(codigoMarca);
				dr.setOrden(orden);
				mantenimientoCOMComisionDAO.insertDetalleComisiones(dr);
			}
		}
		
		mantenimientoCOMComisionDAO.insertComisionCobranza(criteria);
		
		int oidCobraza = mantenimientoCOMComisionDAO.getOidCobranzaComisiones(criteria);
		
		log.debug("Codigo de cobranza es: " + oidCobraza);
		
		
		//	Datos Recuperacin
		if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_05) || 
				StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_07) || 
				(StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) && StringUtils.equals(indicadorComisionEscalonada, "0"))){
			log.debug("mi codigo escalonada es "+ indicadorComisionEscalonada);
			if (comisionDatosComisionList.size() !=0) {
				for (int i = 0; i < comisionDatosComisionList.size(); i++) {
					DatosComisionCalculo b  =new DatosComisionCalculo();
					b = (DatosComisionCalculo)comisionDatosComisionList.get(i);
					b.setOidCobranza(oidCobraza);
					mantenimientoCOMComisionDAO.insertComisionCalculada(b);
				}
			}
			indicadorInsertarDatosComision = true;
		}
		
		
		if (!indicadorInsertarDatosComision) {
			//	Comisin Escalonada
			if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_01) ||
					StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) ||
					StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_03) ||
					StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04) ||
					(StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02) && 
							StringUtils.equals(indicadorComisionEscalonada, "1"))) {
				
				if (comisionComisionEscalonadaList!= null && comisionComisionEscalonadaList.size() >0) {
					log.debug("mi codigo escalonada es " + indicadorComisionEscalonada);
					//Cuando es 1 "uno"
					if (comisionComisionEscalonadaList.size() != 0) {
						for (int i = 0; i < comisionComisionEscalonadaList.size(); i++) {
							DatosComisionEscalonada b = new DatosComisionEscalonada();
							b = (DatosComisionEscalonada)comisionComisionEscalonadaList.get(i);
							b.setCodigoCobranza(oidCobraza);
							
							if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_01) ||
									StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_02)) {
								b.setNroDiasRecuperacion(Integer.parseInt(datosComisiones.getNroDiasRecuperacion()));
								b.setNroTramo(i+1);
								mantenimientoCOMComisionDAO.insertComisionEscalonada(b);
							
							}else if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_03)) {
								
								b.setValPorcRecuperacionFinal(b.getValPorcFinal());
								b.setValPorcRecuperacionInicial(b.getValPorcInicial());
								
								b.setNroTramo(i+1);
								mantenimientoCOMComisionDAO.insertComisionEscalonada(b);
								
							}else if (StringUtils.equals(datosComisiones.getCodigoBaseComision(), Constants.CODIGO_BASE_COMISION_04)) {
								b.setValPorcRecuperacionFinal(b.getValPorcFinalRecuperacion());
								b.setValPorcRecuperacionInicial(b.getValPorcInicialRecuperacion());
								b.setNroDiasRecuperacion(Integer.parseInt(datosComisiones.getNroDiasRecuperacion()));
								b.setNroTramo(i+1);
								mantenimientoCOMComisionDAO.insertComisionEscalonada(b);
							}
						}
					}
				}
			}
		}
		
		
		log.debug("fin de mi metodo");
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getDatosComisionesZonasSubGerencia(java.util.Map)
	 */
	public List getDatosComisionesZonasSubGerencia(Map criteria) {
		return mantenimientoCOMComisionDAO.getDatosComisionesZonasSubGerencia(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getRegionesSubGerencia(java.util.Map)
	 */
	public List getDatosComisiones(Map criteria) {
		return mantenimientoCOMComisionDAO.getDatosComisiones(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getComisionEscalonada(java.util.Map)
	 */
	public List getComisionEscalonada(Map criteria) {
		return mantenimientoCOMComisionDAO.getComisionEscalonada(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#deleteComisionRecuperacion(java.util.Map)
	 */
	public int deleteComisionRecuperacion(Map criteria) {
		int resultado = 0;
		int codigoResultado = 0;
		DatosComisiones comision = mantenimientoCOMComisionDAO.getDatosComisionRecuperacion(criteria);
		
		criteria.put("codigoComision", comision.getCodigoComision());
		
		if (StringUtils.equals(comision.getCodigoTipoComisionista(), "01")) {
			log.debug("Validando que no existan registros en la entidad Comisiones caluladas por Regin correspondientes al cdigo de comisin del registro seleccionado");
			resultado = mantenimientoCOMComisionDAO.getCantComisionesCalculadasXRegion(criteria);
			if (resultado != 0) 
				codigoResultado = 1;
		}else if (StringUtils.equals(comision.getCodigoTipoComisionista(), "02")) {
			log.debug("Validando que no existan registros en la entidad Comisiones caluladas por Zona correspondientes al cdigo de comisin del registro seleccionado.");
			resultado = mantenimientoCOMComisionDAO.getCantComisionesCalculadasxZona(criteria);
			if (resultado != 0)
				codigoResultado = 2;
		}else if(StringUtils.equals(comision.getCodigoTipoComisionista(), "03")){
			log.debug("Validando que no existan registros en la entidad Comisiones caluladas  correspondientes al cdigo de comisin del registro seleccionado");
			resultado= mantenimientoCOMComisionDAO.getCantComisionesCalculadas(criteria);
			if (resultado != 0)
				codigoResultado = 3;
		}
		
		if (resultado == 0) {
			int oidCobraza = mantenimientoCOMComisionDAO.getOidCobranzaComisiones(criteria);
			criteria.put("oidCobranza", oidCobraza);
			
			mantenimientoCOMComisionDAO.deleteDatosComisiones(criteria);
			mantenimientoCOMComisionDAO.deleteDatosEscalonada(criteria);

			mantenimientoCOMComisionDAO.deleteDatosCobranza(criteria);
			mantenimientoCOMComisionDAO.deleteZonasSubGerencia(criteria);
			mantenimientoCOMComisionDAO.deleteComisionRecuperacion(criteria);
			mantenimientoCOMComisionDAO.deleteClasificacion(criteria);
		}
		
			
		return codigoResultado;
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getTipoComiList(java.util.Map)
	 */
	public List getTipoComiList(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoCOMComisionDAO.getTipoComiList(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getComDatosAdam(java.util.Map)
	 */
	public List getComDatosAdam(Map criteria) {
		return mantenimientoCOMComisionDAO.getComDatosAdam(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getComArchivoNominaList(java.util.Map)
	 */
	public List getComArchivoNominaList(Map criteria) {
		return mantenimientoCOMComisionDAO.getComArchivoNominaList(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getTipoClasificacion()
	 */
	public List getTipoClasificacionList() {
		return mantenimientoCOMComisionDAO.getTipoClasificacionList();
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getClasificacionComisionList(java.util.Map)
	 */
	public List getClasificacionComisionList(Map criteria) {
		return mantenimientoCOMComisionDAO.getClasificacionComisionList(criteria);
	}
	
	public List getClasificacionByCodigoComisionList (Map criteria){
		return mantenimientoCOMComisionDAO.getClasificacionByCodigoComisionList(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#deleteClasificacion(java.util.Map)
	 */
	public void deleteClasificacion(Map criteria) {
		mantenimientoCOMComisionDAO.deleteClasificacion(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#insertClasificacion(biz.belcorp.ssicc.spusicc.cobranzas.model.DatosComisionClasificacion)
	 */
	public void insertClasificacion(
			DatosComisionClasificacion datosComisionClasificacion) {
		mantenimientoCOMComisionDAO.insertClasificacion(datosComisionClasificacion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.service.MantenimientoCOMComisionService#getZona(biz.belcorp.ssicc.scdf.model.Zona)
	 */
	public Zona getZona(Zona zona) {
		return zonaDAO.getZona(zona);
	}

	public List getListaMostrarGerentesRetiradas(Map criteria){
		return mantenimientoCOMComisionDAO.getListaMostrarGerentesRetiradas(criteria);
	}


	
	/**
	 * @param zonaDAO the zonaDAO to set
	 */
	public void setZonaDAO(ZonaDAO zonaDAO) {
		this.zonaDAO = zonaDAO;
	}
	
	
	
}