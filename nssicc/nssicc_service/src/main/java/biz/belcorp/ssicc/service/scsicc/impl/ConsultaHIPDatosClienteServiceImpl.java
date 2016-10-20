package biz.belcorp.ssicc.service.scsicc.impl;

import java.util.ArrayList;
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
import biz.belcorp.ssicc.dao.scsicc.ConsultaHIPDatosClienteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService;

/**
 * Implementacion de consultas del modulo de HiperConsulta
 * 
 * @author <a href="">Sergio Apaza</a>
 * 
 */
@Service("scsicc.consultaHIPDatosClienteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ConsultaHIPDatosClienteServiceImpl extends BaseService implements
		ConsultaHIPDatosClienteService {

	@Resource(name = "scsicc.consultaHIPDatosClienteDAO")
	private ConsultaHIPDatosClienteDAO consultaHIPDatosClienteDAO;

	@Resource(name = "spusicc.mantenimientoMAEClienteDAO")
	private MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosGenerales(java.util.Map)
	 */
	public Map getDatosGenerales(Map criteria) {
		return consultaHIPDatosClienteDAO.getDatosGenerales(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getMotivoBloqueo(java.util.Map)
	 */
	public String getMotivoBloqueo(Map criteria) {
		return consultaHIPDatosClienteDAO.getMotivoBloqueo(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDireccionDomicilio(java.util.Map)
	 */
	public Map getDireccionDomicilio(Map criteria) {
		return consultaHIPDatosClienteDAO.getDireccionDomicilio(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDireccionDespacho(java.util.Map)
	 */
	public Map getDireccionDespacho(Map criteria) {
		return consultaHIPDatosClienteDAO.getDireccionDespacho(criteria);	
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getUltimoPedido(java.util.Map)
	 */
	public Map getUltimoPedido(Map criteria) {
		return consultaHIPDatosClienteDAO.getUltimoPedido(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getSaldoUnico(java.util.Map)
	 */
	public String getSaldoUnico(Map criteria) {
		return consultaHIPDatosClienteDAO.getSaldoUnico(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getSaldoCampana(java.util.Map)
	 */
	public String getSaldoCampana(Map criteria) {
		return consultaHIPDatosClienteDAO.getSaldoCampana(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getUnidadesAdministrativas(java.util.Map)
	 */
	public List getUnidadesAdministrativas (Map criteria) {
		return consultaHIPDatosClienteDAO.getUnidadesAdministrativas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getTipificacionCliente(java.util.Map)
	 */
	public List getTipificacionCliente (Map criteria) {
		return consultaHIPDatosClienteDAO.getTipificacionCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getOpcionesPermitidas(java.util.Map)
	 */
	public List getOpcionesPermitidas(Map criteria){
		return consultaHIPDatosClienteDAO.getOpcionesPermitidas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#validarOpcionSecundaria(java.util.Map)
	 */
	public boolean validarOpcionSecundaria(Map criteria) {
		return consultaHIPDatosClienteDAO.validarOpcionSecundaria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getCabeceraReclamos(java.util.Map)
	 */
	public List getCabeceraReclamos (Map criteria) {
		return consultaHIPDatosClienteDAO.getCabeceraReclamos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDetalleReclamo(java.util.Map)
	 */
	public List getDetalleReclamo (Map criteria) {
		return consultaHIPDatosClienteDAO.getDetalleReclamo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getBoletasRecojo(java.util.Map)
	 */
	public List getBoletasRecojo (Map criteria) {
		return consultaHIPDatosClienteDAO.getBoletasRecojo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getClientesByCriteria(java.util.Map)
	 */
	public List getClientesByCriteria (Map criteria) {
		List lista = null;
				
		String codigoConexionExterna = MapUtils.getString(criteria, "codigoConexionExterna", "");
		
		if(StringUtils.equals(codigoConexionExterna, Constants.CONEXION_EXTERNA_FOX))
			lista = consultaHIPDatosClienteDAO.getClientesByCriteriaFOX(criteria);
		else			
			lista = consultaHIPDatosClienteDAO.getClientesByCriteria(criteria);
		
		return lista;
	}
	
	public List getClientesByCriteria2(Map criteria) {
		List lista = null;

		String codigoConexionExterna = MapUtils.getString(criteria,
				"codigoConexionExterna", "");
		lista = consultaHIPDatosClienteDAO.getClientesByCriteria(criteria);

		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getTiposDocumento(java.util.Map)
	 */
	public List getTiposDocumento (Map criteria) {
		return consultaHIPDatosClienteDAO.getTiposDocumento(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getConcursos(java.util.Map)
	 */
	public List getConcursos(Map criteria) {
		return consultaHIPDatosClienteDAO.getConcursos(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPuntajeDetalleConcurso(java.util.Map)
	 */
	public List getPuntajeDetalleConcurso(Map criteria) {
		return consultaHIPDatosClienteDAO.getPuntajeDetalleConcurso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPuntajeResumenConcurso(java.util.Map)
	 */
	public List getPuntajeResumenConcurso(Map criteria) {
		return consultaHIPDatosClienteDAO.getPuntajeResumenConcurso(criteria);
	}	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDuplaAsociada(java.util.Map)
	 */
	public Base getDuplaAsociada(Map criteria) {
		List listDuplaAsociada = consultaHIPDatosClienteDAO.getDuplaAsociada(criteria);
		Base base;
		
		if((listDuplaAsociada != null) && (listDuplaAsociada.size()>0)) {
			base = (Base)listDuplaAsociada.get(0);
		} else {
			base = new Base();
			base.setCodigo("-");
			base.setDescripcion("-");
		}
		
		return base;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPremiosByNivel(java.util.Map)
	 */
	public List getPremiosByNivel(Map criteria) {
		return consultaHIPDatosClienteDAO.getPremiosByNivel(criteria);
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getBolsaFaltantes(java.util.Map)
	 */
	public List getBolsaFaltantes(Map criteria) {
		return consultaHIPDatosClienteDAO.getBolsaFaltantes(criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getSaldoPagar(java.util.Map)
	 */
	public String getSaldoPagar(Map criteria) {
		return consultaHIPDatosClienteDAO.getSaldoPagar(criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#executaCronogramaActividadesList(java.util.Map)
	 */
	public List executaCronogramaActividadesList(Map criteria) {
		return consultaHIPDatosClienteDAO.executaCronogramaActividadesList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getGerenteZonaList(java.util.Map)
	 */
	public List getGerenteZonaList(Map criteria) {
		return consultaHIPDatosClienteDAO.getGerenteZonaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getGerenteRegionList(java.util.Map)
	 */
	public List getGerenteRegionList(Map criteria) {
		return consultaHIPDatosClienteDAO.getGerenteRegionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#executaEnviosPreferencialesList(java.util.Map)
	 */
	public List executaEnviosPreferencialesList(Map criteria) {
		return consultaHIPDatosClienteDAO.executaEnviosPreferencialesList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#executaSuscribirEnviosPreferenciales(java.util.Map)
	 */
	public void executaSuscribirEnviosPreferenciales(Map criteria){
		consultaHIPDatosClienteDAO.executaSuscribirEnviosPreferenciales(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#executaFinSuscribirEnviosPreferenciales(java.util.Map)
	 */
	public void executaFinSuscribirEnviosPreferenciales(Map criteria){
		consultaHIPDatosClienteDAO.executaFinSuscribirEnviosPreferenciales(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getResponsableSeccionList(java.util.Map)
	 */
	public List getResponsableSeccionList(Map criteria) {
		return consultaHIPDatosClienteDAO.getResponsableSeccionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getHistoriaBloqueoList(java.util.Map)
	 */
	public List getHistoriaBloqueoList(Map criteria) {
		return consultaHIPDatosClienteDAO.getHistoriaBloqueoList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getEduConsultorasList(java.util.Map)
	 */
	public List getEduConsultorasList(Map criteria) {
		return consultaHIPDatosClienteDAO.getEduConsultorasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getEduCursosList(java.util.Map)
	 */
	public List getEduCursosList(Map criteria) {
		return consultaHIPDatosClienteDAO.getEduCursosList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getTipoCertificacionList(java.util.Map)
	 */
	public List getTipoCertificacionList(Map criteria) {
		return consultaHIPDatosClienteDAO.getTipoCertificacionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPremiosClienteList(java.util.Map)
	 */
	public List getPremiosClienteList(Map criteria) {
		return consultaHIPDatosClienteDAO.getPremiosClienteList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getFecMesAnno(java.util.Map)
	 */
	public String getFecMesAnno(Map paramsOidCli){
		return consultaHIPDatosClienteDAO.getFecMesAnno(paramsOidCli);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#execGeneraCertificacion(java.util.Map)
	 */
	public void execGeneraCertificacion(Map criteria){
		consultaHIPDatosClienteDAO.execGeneraCertificacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getOidMsj(java.util.Map)
	 */
	public String getOidMsj(Map paramsTemp) {
		return consultaHIPDatosClienteDAO.getOidMsj(paramsTemp);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public String getPromedioVentas(Map criteria) {
		return consultaHIPDatosClienteDAO.getPromedioVentas(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getProductosDespachadosAutomaticamente(java.util.Map)
	 */
	public List getProductosDespachadosAutomaticamente(Map criteria) {
		return consultaHIPDatosClienteDAO.getProductosDespachadosAutomaticamente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getProductosSolicitadosConsultoras(java.util.Map)
	 */
	public List getProductosSolicitadosConsultoras(Map criteria) {
		return consultaHIPDatosClienteDAO.getProductosSolicitadosConsultoras(criteria);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map getDuplaActual(Map criteria) {
		return consultaHIPDatosClienteDAO.getDuplaActual(criteria);
	}
	
	/**
	 * @param criteria
	 */
	public void execGenerarSolicitudDuplaCyzone(Map criteria) {
		consultaHIPDatosClienteDAO.insertDocumentoDigitacion(criteria);
		consultaHIPDatosClienteDAO.insertSolicitudDuplaCyzone(criteria);
	}

	/**
	 * @param criteria
	 */
	public void execGenerarSolicitudActualizacionDatos(Map criteria) {
		consultaHIPDatosClienteDAO.insertDocumentoDigitacion(criteria);
		consultaHIPDatosClienteDAO.insertSolicitudActualizacionDatos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPedidosConsultora(java.util.Map)
	 */
	public List getPedidosConsultora(Map criteria){
		return this.consultaHIPDatosClienteDAO.getPedidosConsultora(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDetallePedido(java.util.Map)
	 */
	public List getDetallePedido(Map criteria){
		return this.consultaHIPDatosClienteDAO.getDetallePedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getConsultaCdrRechazados(java.util.Map)
	 */
	public List getConsultaCdrRechazados(Map criteria) {
		return consultaHIPDatosClienteDAO.getConsultaCdrRechazados(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDetalleCdrRechazados(java.util.Map)
	 */
	public List getDetalleCdrRechazados(Map criteria) {
		return consultaHIPDatosClienteDAO.getDetalleCdrRechazados(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListaTipoPremios(java.util.Map)
	 */
	public List getListaTipoPremios(Map criteria) {
		return consultaHIPDatosClienteDAO.getListaTipoPremios(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getImpedidaPasarCdr(java.util.Map)
	 */
	public String getImpedidaPasarCdr(Map criterios) {
		return consultaHIPDatosClienteDAO.getImpedidaPasarCdr(criterios);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getImpedidaPasarPedido(java.util.Map)
	 */
	public String getImpedidaPasarPedido(Map criterios) {
		return consultaHIPDatosClienteDAO.getImpedidaPasarPedido(criterios);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getVinculosReferencias(Map criteria) {
		return consultaHIPDatosClienteDAO.getVinculosReferencias(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPromedioVentasxCampañas(java.util.Map)
	 */
	public String getPromedioVentasxCampanhas(Map criteria) {
		return consultaHIPDatosClienteDAO.getPromedioVentasxCampanhas(criteria);
	} 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getMediosComunicacion(java.util.Map)
	 */
	public List getMediosComunicacion (Map criteria) {
		return consultaHIPDatosClienteDAO.getMediosComunicacion(criteria);
	} 

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getLiderSeccionList(java.util.Map)
	 */
	public List getLiderSeccionList(Map criteria) {
		return consultaHIPDatosClienteDAO.getLiderSeccionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getClasificacionLove(java.util.Map)
	 */
	public String getClasificacionLove(Map criteria) {
		return consultaHIPDatosClienteDAO.getClasificacionLove(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPremiosAtendidosFaltantes(java.util.Map)
	 */
	public List getPremiosAtendidosFaltantes(Map criteria) {
		return consultaHIPDatosClienteDAO.getPremiosAtendidosFaltantes(criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getRecomendaciones(java.util.Map)
	 */
	public List getRecomendaciones(Map criteria) {
		return consultaHIPDatosClienteDAO.getRecomendaciones(criteria);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getTiposVinculos(java.util.Map)
	 */
	public List getTiposVinculos(Map criteria) {
		return consultaHIPDatosClienteDAO.getTiposVinculos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getHistoricoVinculos(java.util.Map)
	 */
	public List getHistoricoVinculos(Map criteria) {
		return consultaHIPDatosClienteDAO.getHistoricoVinculos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getFechaCastigada(java.util.Map)
	 */
	public String getFechaCastigada(Map criterios) {
		return consultaHIPDatosClienteDAO.getFechaCastigada(criterios);
	}

	public String getLimiteCredito(Map criteria){
		return consultaHIPDatosClienteDAO.getLimiteCredito(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getSaldoVencidos(java.util.Map)
	 */
	public Map getSaldoVencidos(Map criteria) {
		return consultaHIPDatosClienteDAO.getSaldoVencidos(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosFamiliaProtegida(java.util.Map)
	 */
	public Map getDatosFamiliaProtegida(Map criteria) {
		List listCoberturas = consultaHIPDatosClienteDAO.getFechasCoberturaPoliza(criteria);
		Map datos = new HashMap();
		
		datos.put("estadoSolicitudPoliza", consultaHIPDatosClienteDAO.getEstadoSolicitudPoliza(criteria));
		
		if((listCoberturas != null) && (listCoberturas.size()>0)) {
			Base base = (Base)listCoberturas.get(0);
			datos.put("fechaInicioCobertua", base.getCodigo());
			datos.put("fechaFinCobertura", base.getDescripcion());
		} else {
			datos.put("fechaInicioCobertua", "");
			datos.put("fechaFinCobertura", "");
		}
		
		return datos;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getSolicitudesPoliza(java.util.Map)
	 */
	public List getSolicitudesPoliza(Map criteria) {
		return consultaHIPDatosClienteDAO.getSolicitudesPoliza(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getHistoricoCargosPoliza(java.util.Map)
	 */
	public List getHistoricoCargosPoliza(Map criteria) {
		return consultaHIPDatosClienteDAO.getHistoricoCargosPoliza(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getObtenerMontoPlanSuperacion(java.util.Map)
	 */
	public String getObtenerMontoPlanSuperacion(Map criteria) {
		return consultaHIPDatosClienteDAO.getObtenerMontoPlanSuperacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#validarImagenesEscaneoSC(java.lang.String)
	 */
	public boolean validarImagenesEscaneoSC(String codigoCliente) {
		return consultaHIPDatosClienteDAO.validarImagenesEscaneoSC(codigoCliente);
	}

	/* INI SA PER-SiCC-2012-0385 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosCodigoVentaPrincipal(java.util.Map)
	 */
	public Map getDatosCodigoVentaPrincipal(Map criteria) {
		return consultaHIPDatosClienteDAO.getDatosCodigoVentaPrincipal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListCodigoVentaRelacionados(java.util.Map)
	 */
	public List getListCodigoVentaRelacionados(Map criteria) {
		return consultaHIPDatosClienteDAO.getListCodigoVentaRelacionados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListVentaExclusiva(java.util.Map)
	 */
	public List getListVentaExclusiva(Map criteria) {
		return consultaHIPDatosClienteDAO.getListVentaExclusiva(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListLimiteVenta(java.util.Map)
	 */
	public List getListLimiteVenta(Map criteria) {
		return consultaHIPDatosClienteDAO.getListLimiteVenta(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListControlStock(java.util.Map)
	 */
	public List getListControlStock(Map criteria) {
		return consultaHIPDatosClienteDAO.getListControlStock(criteria);
	}
	/* FIN SA PER-SiCC-2012-0385 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getTipoConsultora(java.util.Map)
	 */
	public String getTipoConsultora(Map criteria) {
		return consultaHIPDatosClienteDAO.getTipoConsultora(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListCoberturaCentroAcopio(java.util.Map)
	 */
	public List getListCoberturaCentroAcopio(Map criteria) {
		return consultaHIPDatosClienteDAO.getListCoberturaCentroAcopio(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getAdicionalesCabeceraReclamo(java.util.Map)
	 */
	public List getAdicionalesCabeceraReclamo(Map criteria) {
		return consultaHIPDatosClienteDAO.getAdicionalesCabeceraReclamo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosCabeceraDetallePedidoSolicitado(java.lang.String)
	 */
	public List getDatosCabeceraDetallePedidoSolicitado(String secuencia) {
		return consultaHIPDatosClienteDAO.getDatosCabeceraDetallePedidoSolicitado(secuencia);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosPoliza(java.util.Map)
	 */
	public Map getDatosPoliza(Map criteria) {
		Map resultado = consultaHIPDatosClienteDAO.getDatosPoliza(criteria);
		List listCoberturas = consultaHIPDatosClienteDAO.getFechasCoberturaPoliza(criteria);
		
		if((listCoberturas != null) && (listCoberturas.size()>0)) {
			Base base = (Base)listCoberturas.get(0);
			
			if(resultado==null)
				resultado = new HashMap();
			
			resultado.put("fechaInicioCobertura", base.getCodigo());
			resultado.put("fechaFinCobertura", base.getDescripcion());
		} else {
			if(resultado!=null) {
				resultado.put("fechaInicioCobertura", "");
				resultado.put("fechaFinCobertura", "");
			}	
		}
		
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getHistorialCobrosSeguro(java.util.Map)
	 */
	public List getHistorialCobrosSeguro(Map criteria) {
		return consultaHIPDatosClienteDAO.getHistorialCobrosSeguro(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosMetas(java.util.Map)
	 */
	public Map getDatosMetas(Map criteria) {
		return consultaHIPDatosClienteDAO.getDatosMetas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getFacturacionAdicionalList(java.lang.String)
	 */
	public List getFacturacionAdicionalList(String codigoCliente) {
		return consultaHIPDatosClienteDAO.getFacturacionAdicionalList(codigoCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListPeriodoRetail(java.util.Map)
	 */
	public List getListPeriodoRetail(Map criteria) {
		return consultaHIPDatosClienteDAO.getListPeriodoRetail(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListCabeceraRetail(java.util.Map)
	 */
	public List getListCabeceraRetail(Map criteria) {
		return consultaHIPDatosClienteDAO.getListCabeceraRetail(criteria);
	}
	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListDetalleRetail(java.util.Map)
	 */
	public List getListDetalleRetail(Map criteria) {
		return consultaHIPDatosClienteDAO.getListDetalleRetail(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#executeActualizarDatosCliente(java.util.Map)
	 */
	public void executeActualizarDatosCliente(Map params) {
		consultaHIPDatosClienteDAO.executeActualizarDatosCliente(params);
		
		ClienteHistoricoDatos clienteHistoricoDatos = (ClienteHistoricoDatos)params.get("clienteHistoricoDatos");
		mantenimientoMAEClienteDAO.executeInsercionHistoricoDatos(clienteHistoricoDatos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosEjecutiva(java.util.Map)
	 */
	public Map getDatosEjecutiva(Map criteria) {
		Map resultado = consultaHIPDatosClienteDAO.getDatosEjecutiva(criteria);
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDetalleEvaluacionEjecutiva(java.util.Map)
	 */
	public List getDetalleEvaluacionEjecutiva(Map criteria) {
		return consultaHIPDatosClienteDAO.getDetalleEvaluacionEjecutiva(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getFaseActivaPrograma(java.util.Map)
	 */
	public String getFaseActivaPrograma(Map criteria) {
		return consultaHIPDatosClienteDAO.getFaseActivaPrograma(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getEtapasEjecutivas(java.util.Map)
	 */
	public List getEtapasEjecutivas(Map criteria) {
		return consultaHIPDatosClienteDAO.getEtapasEjecutivas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getBloqueoActivo(java.lang.String)
	 */
	public String getBloqueoActivo(String codigoCliente) {
		return consultaHIPDatosClienteDAO.getBloqueoActivo(codigoCliente);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.getSaldoCupon3(java.util.Map)
	 */
	public Double getSaldoCupon3(Map criteria) {
		return consultaHIPDatosClienteDAO.getSaldoCupon3(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getOidPeriodo(java.util.Map)
	 */
	public Integer getOidPeriodo(Map criteria){
		return consultaHIPDatosClienteDAO.getOidPeriodo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getOidCliente(java.util.Map)
	 */
	public Integer getOidCliente(Map criteria){
		return consultaHIPDatosClienteDAO.getOidCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getMontoMinimo(java.util.Map)
	 */
	public Double getMontoMinimo(Map criteria){
		return consultaHIPDatosClienteDAO.getMontoMinimo(criteria);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDesPeriodoByCodigoPeriodoX(java.util.Map)
	 */
	public String getDesPeriodoByCodigoPeriodoX(Map criteria) {
		return consultaHIPDatosClienteDAO.getDesPeriodoByCodigoPeriodoX(criteria);
	}
	
	public Map getAplicaMontoMinimo(Map criteria) {
		return consultaHIPDatosClienteDAO.getAplicaMontoMinimo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPromedioVentasxNumeroPedidos(java.util.Map)
	 */
	public String getPromedioVentasxNumeroPedidos(Map criteria) {
		return consultaHIPDatosClienteDAO.getPromedioVentasxNumeroPedidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getConsultaDetallePedidoSolicitado(java.util.Map)
	 */
	public List getConsultaDetallePedidoSolicitado(Map criteria) {
		return consultaHIPDatosClienteDAO.getConsultaDetallePedidoSolicitado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getCabeceraConsultoraCastigada(java.util.Map)
	 */
	public List getCabeceraConsultoraCastigada(Map criteria) {
		return consultaHIPDatosClienteDAO.getCabeceraConsultoraCastigada(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDetalleConsultoraCastigada(java.util.Map)
	 */
	public List getDetalleConsultoraCastigada(Map criteria) {
		return consultaHIPDatosClienteDAO.getDetalleConsultoraCastigada(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getIndicadorBasparampais(java.util.Map)
	 */
	public String getIndicadorBasparampais(Map criteria){
		return consultaHIPDatosClienteDAO.getIndicadorBasparampais(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getEtapaDeCobro(java.util.Map)
	 */
	public String getEtapaDeCobro(Map criteria){
		return consultaHIPDatosClienteDAO.getEtapaDeCobro(criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getCobrador(java.util.Map)
	 */
	public String getCobrador(Map criteria){
		return consultaHIPDatosClienteDAO.getCobrador(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getCuentaCorrienteConsultoraCastigada(java.util.Map)
	 */
	public List getCuentaCorrienteConsultoraCastigada(Map criteria) {
		return consultaHIPDatosClienteDAO.getCuentaCorrienteConsultoraCastigada(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getSaldoACampana(java.util.Map)
	 */
	public String getSaldoACampana(Map criteria) {
		return consultaHIPDatosClienteDAO.getSaldoACampana(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPedidoBaseLineaCredito(java.util.Map)
	 */
	public Map getPedidoBaseLineaCredito(Map params) {
		Map result = new HashMap();
		
		List lista = consultaHIPDatosClienteDAO.getPedidoBaseLineaCredito(params);
		List listaCabecera = new ArrayList();
		List listaDetalle = new ArrayList();
		
		listaDetalle.add(new HashMap());
		listaDetalle.add(new HashMap());
		
		if(lista != null)
		{
			for(int i=0; i<lista.size(); i++)
			{
				Map data = (Map)lista.get(i);

				String codigoPeriodo = MapUtils.getString(data, "codigoPeriodo", "");
				double pedidoBase =  MapUtils.getDouble(data, "pedidoBase", new Double(0)).doubleValue();
				double lineaCredito =  MapUtils.getDouble(data, "lineaCredito", new Double(0)).doubleValue();
				
				Map pb = (HashMap)listaDetalle.get(0);
				pb.put("descripcion", "Pedido Base");
				pb.put(codigoPeriodo, pedidoBase);
				
				Map lc = (HashMap)listaDetalle.get(1);
				lc.put("descripcion", "Linea de Credito");
				lc.put(codigoPeriodo, lineaCredito);
				
				Base base = new Base();
				base.setCodigo(codigoPeriodo);
				base.setDescripcion(codigoPeriodo);
				listaCabecera.add(base);
			}
		}
		
		result.put("listaCabecera", listaCabecera);
		result.put("listaDetalle", listaDetalle);
		
		return result;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getListDetallePuntosConcursoRetail(java.util.Map)
	 */
	public List getListDetallePuntosConcursoRetail(Map criteria) {
		return consultaHIPDatosClienteDAO.getListDetallePuntosConcursoRetail(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDetalleRecuperacionAnulacion(java.util.Map)
	 */
	public List getDetalleRecuperacionAnulacion(Map criteria) {
		return consultaHIPDatosClienteDAO.getDetalleRecuperacionAnulacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#insertRecuperacionAnulacionAudit(java.util.Map)
	 */
	public void insertRecuperacionAnulacionAudit(Map criteria) {
		consultaHIPDatosClienteDAO.insertRecuperacionAnulacionAudit(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#deleteRecuperacionAnulacion(java.lang.String)
	 */
	public void deleteRecuperacionAnulacion(String oidSoliPosi) {
		consultaHIPDatosClienteDAO.deleteRecuperacionAnulacion(oidSoliPosi);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getExisteIntegridadCabecera(java.lang.String)
	 */
	public String getExisteIntegridadCabecera(String oidSoliCabe) {
		return consultaHIPDatosClienteDAO.getExisteIntegridadCabecera(oidSoliCabe);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#deleteRecuperacionAnulacionCabecera(java.lang.String)
	 */
	public void deleteRecuperacionAnulacionCabecera(String oidSoliCabe) {
		consultaHIPDatosClienteDAO.deleteRecuperacionAnulacionCabecera(oidSoliCabe);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPremiosRechazados(java.util.Map)
	 */
	public List getPremiosRechazados(Map criteria) {
		return consultaHIPDatosClienteDAO.getPremiosRechazados(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getEtapasCobro(java.util.Map)
	 */
	public List getEtapasCobro(Map criteria) {
		return consultaHIPDatosClienteDAO.getEtapasCobro(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDeshabilitarZonaTerritorio(java.util.Map)
	 */
	public String getDeshabilitarZonaTerritorio(Map criteria) {
		return consultaHIPDatosClienteDAO.getDeshabilitarZonaTerritorio(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPremiosElegidos(java.util.Map)
	 */
	public List getPremiosElegidos(Map criteria) {
		return consultaHIPDatosClienteDAO.getPremiosElegidos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getCodigoProgramaLET(java.lang.String)
	 */
	public String getCodigoProgramaLET(String campanyaProceso) {
		return consultaHIPDatosClienteDAO.getCodigoProgramaLET(campanyaProceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getDatosSociaEmpresaria(java.util.Map)
	 */
	public Map getDatosSociaEmpresaria(Map criteria) {
		return consultaHIPDatosClienteDAO.getDatosSociaEmpresaria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getHistorialSociaEmpresaria(java.util.Map)
	 */
	public List getHistorialSociaEmpresaria(Map criteria) {
		return consultaHIPDatosClienteDAO.getHistorialSociaEmpresaria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getResultadosSociaEmpresaria(java.util.Map)
	 */
	public List getResultadosSociaEmpresaria(Map criteria) {
		return consultaHIPDatosClienteDAO.getResultadosSociaEmpresaria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getEstatusCliente(java.util.Map)
	 */
	public String getEstatusCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getEstatusCliente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getFechaCampanaActivacionCliente(java.util.Map)
	 */
	public Map getFechaCampanaActivacionCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getFechaCampanaActivacionCliente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getFechaCancelacionCliente(java.util.Map)
	 */
	public String getFechaCancelacionCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getFechaCancelacionCliente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getFechaComunicacionCliente(java.util.Map)
	 */
	public String getFechaComunicacionCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getFechaComunicacionCliente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getMotivoCancelacionCliente(java.util.Map)
	 */
	public String getMotivoCancelacionCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getMotivoCancelacionCliente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPeriodoComunicacionCliente(java.util.Map)
	 */
	public String getPeriodoComunicacionCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getPeriodoComunicacionCliente(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getPeriodoNumeroPedido(java.util.Map)
	 */
	public Map getPeriodoNumeroPedido(Map criteria) {
		return consultaHIPDatosClienteDAO.getPeriodoNumeroPedido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getSituacionFlexipago(java.lang.String, java.lang.String)
	 */
	public String getSituacionFlexipago(String codigoPais, String codigoCliente, String campanhaProceso) {
		return consultaHIPDatosClienteDAO.getSituacionFlexipago(codigoPais, codigoCliente, campanhaProceso);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.scsicc.service.ConsultaHIPDatosClienteService#getOidSubtipoCliente(java.util.Map)
	 */
	public String getOidSubtipoCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getOidSubtipoCliente(criteria);
	}
	
	public String getObtieAbonoPendi(Map criteria) {
		return consultaHIPDatosClienteDAO.getObtieAbonoPendi(criteria);
	}

	
	public String getTipoPersonaxOidCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getTipoPersonaxOidCliente(criteria);
	}


	public String getOrigenIngresosxOidCliente(Map criteria) {
		return consultaHIPDatosClienteDAO.getOrigenIngresosxOidCliente(criteria);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.scsicc.ConsultaHIPDatosClienteService#getOrigenxCodCliente(java.util.Map)
	 */
	public String getOrigenxCodCliente(Map criteria) {
		String origen=consultaHIPDatosClienteDAO.getOrigenxCodCliente(criteria);
		String resultado="";
		if(StringUtils.isNotBlank(origen)){
			if(StringUtils.equals(origen, "CZ"))
				resultado = "Pantalla de Zonificación";
			if(StringUtils.equals(origen, "CM"))
				resultado = "Pantalla MAE";		
			if(StringUtils.equals(origen, "CH"))
				resultado = "Pantalla Hiperconsulta";		
			if(StringUtils.equals(origen, "CC"))
				resultado = "Comercial Carga";		
		}
		return resultado;
		
	}
	

}