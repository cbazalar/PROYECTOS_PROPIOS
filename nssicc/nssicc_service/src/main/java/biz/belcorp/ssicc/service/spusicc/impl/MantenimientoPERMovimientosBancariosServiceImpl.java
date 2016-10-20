package biz.belcorp.ssicc.service.spusicc.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosCabecera;
import biz.belcorp.ssicc.dao.sisicc.model.MovimientosBancariosDetalle;
import biz.belcorp.ssicc.dao.spusicc.MantenimientoPERMovimientosBancariosDAO;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPERMovimientosBancariosService;

/**
 * Service que controla a los Movimientos Bancarios
 *  
 * <p>
 * <a href="MantenimientoPERMovimientosBancariosServiceimpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:rdelosreyes@belcorp.biz">Richard De los Reyes Prncipe</a>
 * 
 */
@Service("spusicc.mantenimientoPERMovimientosBancariosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPERMovimientosBancariosServiceImpl extends BaseService implements MantenimientoPERMovimientosBancariosService {

	@Resource(name="spusicc.mantenimientoPERMovimientosBancariosDAO")
	MantenimientoPERMovimientosBancariosDAO mantenimientoDAO;

	@Resource(name="sisicc.historicoService")
	private HistoricoService historicoService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#getMovimientosBancarios(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera)
	 */
	public List getMovimientosBancarios(MovimientosBancariosCabecera cabecera) {
		return mantenimientoDAO.getMovimientosBancarios(cabecera);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#getMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera)
	 */
	public List getMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera) {
		return mantenimientoDAO.getMovimientosBancariosCabecera(cabecera);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#getBeanMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera)
	 */
	public MovimientosBancariosCabecera getBeanMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera) {
		return mantenimientoDAO.getBeanMovimientosBancariosCabecera(cabecera);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#getMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle)
	 */
	public List getMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle) {
		return mantenimientoDAO.getMovimientosBancariosDetalle(detalle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#insertMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera, Usuario usuario) {
		cabecera.setCodigoTipoOrigenDatos(Constants.RECAUDO_BANCARIO_MANUAL);
		mantenimientoDAO.insertMovimientosBancariosCabecera(cabecera, usuario);
		
		/* Actualizando Historico de Lotes */
		Historico historicoEjecucion = initializeHistorico(cabecera);
		historicoEjecucion.setNumeroLote(cabecera.getNumeroLoteInterno());
		log.debug("Insertando historico=" + historicoEjecucion);
		historicoService.insertHistorico(historicoEjecucion, usuario);
		
	}

	
	/**
	 * Crea e inicializa el Historico.
	 * 
	 * @param params
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Historico inicializado
	 */
	private Historico initializeHistorico(MovimientosBancariosCabecera cabecera) {
		if (log.isDebugEnabled())
			log.debug("Entering 'initializeHistorico' method");
		Historico historico = new Historico();
		historico.setFechaInicioProceso(new Timestamp(System
				.currentTimeMillis()));
		historico.setCodigoPais(cabecera.getCodigoPais());
		historico.setCodigoSistema(Constants.CODIGO_BANCOS);
		historico.setCodigoInterfaz(Constants.INTERFAZ_RECEPCION_RECAUDO_AUTOMATICO);
		historico.setFlagError(Constants.NO);
		historico.setRegistrosProcesados(new Long(0));
		historico.setRegistrosErroneos(new Long(0));
		historico.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_OK);
		//historico.setDescripcionLote((String) params.get("descripcion"));
		//historico.setObservaciones((String) params.get("observaciones"));
		if (log.isDebugEnabled())
			log.debug("historico=" + historico);
		return historico;
	}
	
	
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#insertMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle, Usuario usuario) {
		detalle.setStatusMovimiento(Constants.MOVIMIENTO_BANCARIO_PENDIENTE);
		mantenimientoDAO.insertMovimientosBancariosDetalle(detalle, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#updateMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera, Usuario usuario) {
		mantenimientoDAO.updateMovimientosBancariosCabecera(cabecera, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#updateMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle, Usuario usuario) {
		detalle.setImportePagoAplicado(0);
		detalle.setImportePagoPendiente(0);
		detalle.setImporteRecaudoGenerado(0);
		detalle.setImportePercepcion(0);
		detalle.setStatusMovimiento(Constants.MOVIMIENTO_BANCARIO_PENDIENTE);
		mantenimientoDAO.updateMovimientosBancariosDetalle(detalle, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#removeMovimientosBancariosCabecera(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosCabecera,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeMovimientosBancariosCabecera(
			MovimientosBancariosCabecera cabecera, Usuario usuario) {
		mantenimientoDAO.removeMovimientosBancariosCabecera(cabecera, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#removeMovimientosBancariosDetalle(biz.belcorp.ssicc.sisicc.model.MovimientosBancariosDetalle,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeMovimientosBancariosDetalle(
			MovimientosBancariosDetalle detalle, Usuario usuario) {
		mantenimientoDAO.removeMovimientosBancariosDetalle(detalle, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#getNextNumeroLote()
	 */
	public String getNextNumeroLote(String codigoPais, String codigoTipoOrigenDatos) {
		return mantenimientoDAO.getNextNumeroLote(codigoPais, codigoTipoOrigenDatos);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.service.MantenimientoPERMovimientosBancariosService#convierteFormatoFecha(java.lang.String)
	 */
	public String convierteFormatoFecha(String fecha) {
		String resultado = "";
		resultado = fecha.substring(6, 10) + fecha.substring(3, 5)
				+ fecha.substring(0, 2);
		return resultado;
	}
}
