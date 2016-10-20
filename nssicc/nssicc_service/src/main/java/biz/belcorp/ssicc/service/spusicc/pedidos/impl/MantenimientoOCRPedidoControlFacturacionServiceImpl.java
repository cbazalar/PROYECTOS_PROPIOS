package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.LabelPedidosConsoDetalValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoAnuladoFacturado;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProcesoOCRActualizarUnidadesMaximas;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ProductoAgregacion;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.SolicitudConsolidadoCabecera;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.MailParams;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.service.util.FileUtil;

@Service("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoOCRPedidoControlFacturacionServiceImpl extends BaseService implements MantenimientoOCRPedidoControlFacturacionService {

	@Resource(name="spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionDAO")
	MantenimientoOCRPedidoControlFacturacionDAO mantenimientoOCRPedidoControlFacturacionDAO;

	@Resource(name="spusicc.procesoSTOEjecucionValidacionesService")
	ProcesoSTOEjecucionValidacionesService procesoSTOEjecucionValidacionesService;

	@Resource(name="genericoDAO")
	private GenericoDAO genericoDAO;
		
	@Resource(name="sisicc.mailReporteFacturacionAdicionalService")
	private BaseMailService mailService;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getControlFacturacionByCriteria(java.util.Map)
	 */
	public List getControlFacturacionByCriteria(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getControlFacturacionByCriteria(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getProductoAgregacionByCriteria(java.util.Map)
	 */
	public List getProductoAgregacionByCriteria(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getProductoAgregacionByCriteria(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getSolicitudCabeceraByCriteria(java.util.Map)
	 */
	public List getDeudaPedidosByCriteria(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getDeudaPedidosByCriteria(criteria);
	}

	public List getDeudaPedidosMasivaByCriteria(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getDeudaPedidosMasivaByCriteria(criteria);
	}

	public List getPedidosFacturadosAnuladosByCriteria(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getPedidosFacturadosAnuladosByCriteria(criteria);
	}

	public List getBloqueoPedidosMasivoByCriteria(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getBloqueoPedidosMasivoByCriteria(criteria);
	}

	public void deleteTemporalDeudaMasiva() {
		mantenimientoOCRPedidoControlFacturacionDAO.deleteTemporalDeudaMasiva();
	}

	public void deleteTemporalBloqueoMasivo() {
		mantenimientoOCRPedidoControlFacturacionDAO
				.deleteTemporalBloqueoMasivo();
	}

	public void insertTemporalDeudaMasiva(Map criteria) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.insertTemporalDeudaMasiva(criteria);
	}

	public void insertTemporalBloqueoMasivo(Map criteria) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.insertTemporalBloqueoMasivo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #grabarPedidoFacturadoAnulado(java.util.Map)
	 */
	public void grabarPedidoFacturadoAnulado(Map criteria) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.grabarPedidoFacturadoAnulado(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getBolsaPedidosByCriteria(java.util.Map)
	 */
	public List getOCREnviaOCSList(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getOCREnviaOCSList(criteria);
	}

	public List getOCRConsultorasInactivasList(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getOCRConsultorasInactivasList(criteria);
	}

	public List getBolsaPedidosByCriteria(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getBolsaPedidosByCriteria(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getSolicitudCabeceraById(java.util.Map)
	 */
	public SolicitudConsolidadoCabecera getDeudaPedidosById(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getDeudaPedidosById(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getBolsaPedidosById(java.util.Map)
	 */
	public SolicitudConsolidadoCabecera getBolsaPedidosById(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getBolsaPedidosById(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getProductoAgregacionById(java.util.Map)
	 */
	public ProductoAgregacion getProductoAgregacionById(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getProductoAgregacionById(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getOfertaDetalleById(java.util.Map)
	 */
	public ProductoAgregacion getOfertaDetalleById(Map criteria) {
		return this.mantenimientoOCRPedidoControlFacturacionDAO
				.getOfertaDetalleById(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getOfertaDetalleByPeriodoCodigoVenta(java.util.Map)
	 */
	public BigDecimal getOfertaDetalleByPeriodoCodigoVenta(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getOfertaDetalleByPeriodoCodigoVenta(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getMatrizFacturacionByPeriodo(java.util.Map)
	 */
	public BigDecimal getMatrizFacturacionByPeriodo(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getMatrizFacturacionByPeriodo(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getDetOfertaIndicaDigitableById(java.util.Map)
	 */
	public BigDecimal getDetOfertaIndicaDigitableById(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getDetOfertaIndicaDigitableById(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getDetOfertaPrecioCatalogoById(java.util.Map)
	 */
	public BigDecimal getDetOfertaPrecioCatalogoById(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getDetOfertaPrecioCatalogoById(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getDetOfertaEstrategiaIndividualById(java.util.Map)
	 */
	public BigDecimal getDetOfertaEstrategiaIndividualById(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getDetOfertaEstrategiaIndividualById(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getCodVentaAgregacById(java.util.Map)
	 */
	public BigDecimal getCodVentaAgregacById(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getCodVentaAgregacById(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executeActualizaPrioridad(java.util.Map)
	 */
	public void executeActualizaPrioridad(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeActualizaPrioridad(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executeProcesoOCRProcesosEspecialesOCS(java.util.Map)
	 */
	public void executeProcesoOCRProcesosEspecialesOCS(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeProcesoOCRProcesosEspecialesOCS(params);
	}

	public void executeProcesoOCRActualizaGP2(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeProcesoOCRActualizaGP2(params);
	}

	public void actualizaOCSHistoricoCabecera(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.actualizaOCSHistoricoCabecera(params);
	}

	public void actualizaOCSHistoricoDetalle(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.actualizaOCSHistoricoDetalle(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getControlFacturacionById(java.util.Map)
	 */

	public PedidoControlFacturacion getControlFacturacionById(Map criteria) {

		return this.mantenimientoOCRPedidoControlFacturacionDAO.getControlFacturacionById(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #insertControlFacturacion(biz
	 * .belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertControlFacturacion(
			PedidoControlFacturacion controlFacturacion, Usuario usuario) {
		// Se verifiaca si esta prendido el indicador de crear campana como
		// activa
		String indActiva = "0";
		Map criteria = new HashMap();
		criteria.put("codigoPais", controlFacturacion.getCodigoPais());
		criteria.put("codigoSistema", "OCR");

		indActiva = mantenimientoOCRPedidoControlFacturacionDAO
				.getIndicadorActivaNuevaCampana(criteria);

		if (Constants.NUMERO_CERO.equals(indActiva)) {
			controlFacturacion.setCampaniaActiva(Constants.NUMERO_UNO);
		} else {
			// Se valida si existe campana activa, sino existe se crea la nueva
			// campana como activa
			String indCampActiva = "0";
			indCampActiva = mantenimientoOCRPedidoControlFacturacionDAO
					.getExistePeriodoActivo(criteria);

			if (Constants.NUMERO_CERO.equals(indCampActiva)) {
				controlFacturacion.setCampaniaActiva(Constants.NUMERO_UNO);
			}// caso contraria se crea como cerrada
			else {
				controlFacturacion.setCampaniaActiva(Constants.NUMERO_CERO);
				controlFacturacion.setEstadoCampanha(Constants.NUMERO_UNO);
			}
		}

		mantenimientoOCRPedidoControlFacturacionDAO.insertControlFacturacion(
				controlFacturacion, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #insertProductoPedidoMinimo
	 * (biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProductoPedidoMinimo(
			ProductoAgregacion productoAgregacion, Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.insertProductoPedidoMinimo(
				productoAgregacion, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #updateControlFacturacion(biz
	 * .belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateControlFacturacion(
			PedidoControlFacturacion controlFacturacion, Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.updateControlFacturacion(
				controlFacturacion, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #updateDeuda(biz.belcorp.ssicc
	 * .spusicc.pedidos.model.SolicitudConsolidadoCabecera,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateDeuda(SolicitudConsolidadoCabecera cabecera,
			Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.updateDeuda(cabecera,
				usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #updateBolsaPedidosBloqueoIndividual
	 * (biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateBolsaPedidosBloqueoIndividual(
			SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.updateBolsaPedidosBloqueoIndividual(cabecera, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #updateDeudaGeneral(biz.belcorp
	 * .ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateDeudaGeneral(SolicitudConsolidadoCabecera cabecera,
			Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.updateDeudaGeneral(
				cabecera, usuario);
	}

	public void updatePedidoAnuladoFacturado(
			PedidoAnuladoFacturado anuladoFacturado, Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.updatePedidoAnuladoFacturado(anuladoFacturado, usuario);
	}

	public void updatePedidosAnuladoConsultora(
			PedidoAnuladoFacturado anuladoFacturado, Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.updatePedidosAnuladoConsultora(anuladoFacturado, usuario);
	}

	public void executeProcesoOCRCargarPedidosAnulados(Map params,
			Usuario usuario) throws Exception {

		mantenimientoOCRPedidoControlFacturacionDAO
				.deleteCargaPedidosAnulados();
		mantenimientoOCRPedidoControlFacturacionDAO
				.insertPedidoMasivoFacturadoAnulado(params, usuario);
		mantenimientoOCRPedidoControlFacturacionDAO
				.updateCargaPedidosMasivoAnulados(params);
	}

	public void updatePedidosAnulaciones(
			PedidoAnuladoFacturado anuladoFacturado, Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.updatePedidosAnulaciones(
				anuladoFacturado, usuario);
	}

	public void updateDeudaGeneralMasiva(SolicitudConsolidadoCabecera cabecera,
			Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.updateDeudaGeneralMasiva(
				cabecera, usuario);
	}

	public void updateBloqueoGeneral(SolicitudConsolidadoCabecera cabecera,
			Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.updateBloqueoGeneral(
				cabecera, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #updateBolsaPedidosBloqueo(
	 * biz.belcorp.ssicc.spusicc.pedidos.model.SolicitudConsolidadoCabecera,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateBolsaPedidosBloqueo(
			SolicitudConsolidadoCabecera cabecera, Usuario usuario) {
		mantenimientoOCRPedidoControlFacturacionDAO.updateBolsaPedidosBloqueo(
				cabecera, usuario);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #deleteControlFacturacion(biz
	 * .belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
	 */
	public void deleteControlFacturacion(
			PedidoControlFacturacion controlFacturacion) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.deleteControlFacturacion(controlFacturacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #cerrarCampanaControlFacturacion
	 * (biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
	 */
	public void cerrarCampanaControlFacturacion(
			PedidoControlFacturacion controlFacturacion) throws Exception {

		/* SE CIERRA LA CAMPAA */
		mantenimientoOCRPedidoControlFacturacionDAO
				.cerrarCampanaControlFacturacion(controlFacturacion);
		Map map = BeanUtils.describe(controlFacturacion);

		/*
		 * PASANDO A HISTORICOS INFORMACION DE PEDIDOS
		 * INT_SOLIC_CONSO_CABEC-INT_SOLIC_CONSO_DETAL
		 */
		mantenimientoOCRPedidoControlFacturacionDAO.executeOCSHistorico(map);

		/* PASANDO A HISTORICOS INFORMACION DE STO */
		Map params = new HashMap();

		params.put("codigoPais", controlFacturacion.getCodigoPais());
		params.put("numLote", "");
		params.put("fechaInicio", "");
		params.put("fechaFin", "");
		params.put("codigoTipo", Constants.STO_TIPO_DOCUMENTO_OCC);
		params.put("codigoPeriodo", controlFacturacion.getCodigoPeriodo());

		TipoDocumentoDigitado tipoDocumentoDigitado = (TipoDocumentoDigitado) procesoSTOEjecucionValidacionesService
				.getTipoDocumentoDigitado(Constants.STO_TIPO_DOCUMENTO_OCC);

		procesoSTOEjecucionValidacionesService.executeAlmacenamientoHistorico(
				tipoDocumentoDigitado.getExeProcHist(), params);

	}

	public void executeActualizaNumeroLote(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeActualizaNumeroLote(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #desactivarProductoPedidoMinimo
	 * (biz.belcorp.ssicc.spusicc.pedidos.model.ProductoAgregacion)
	 */
	public void desactivarProductoPedidoMinimo(
			ProductoAgregacion productoAgregacion) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.desactivarProductoPedidoMinimo(productoAgregacion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getPedidosSiguienteCampanha(java.util.Map)
	 */
	public String getPedidosSiguienteCampanha(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getPedidosSiguienteCampanha(map);
	}

	public String getPedidosNSiguienteCampanha(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getPedidosNSiguienteCampanha(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executePedido(java.lang.String, java.util.Map)
	 */
	public void executePedido(String method, Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO.executePedido(method,
				params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getNumLotes(java.lang.String)
	 */
	public String getNumLotes(Map params) {
		return mantenimientoOCRPedidoControlFacturacionDAO.getNumLotes(params);
	}

	public String getNumeroLote(Map params) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getNumeroLote(params);
	}

	public String getNumeroUnidades(Map params) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getNumeroUnidades(params);
	}

	public LabelPedidosConsoDetalValue getDetalleById(Map params) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getDetalleById(params);
	}

	public void updateUnidades(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO.updateUnidades(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #updateUnidadesTotal(biz.belcorp
	 * .ssicc.spusicc.pedidos.model.ProcesoOCRActualizarUnidadesMaximas)
	 */
	public boolean updateUnidadesTotal(ProcesoOCRActualizarUnidadesMaximas bean) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.updateUnidadesTotal(bean);
	}

	public void executeProcesoRECCargaTablaBoletaRecojoEspecial(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeProcesoRECCargaTablaBoletaRecojoEspecial(params);
	}

	public List getCampanhasActivasByCriteria(Map criteria) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getCampanhasActivasByCriteria(criteria);
	}

	public List getCampanhasActivasById(Map criteria) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getCampanhasActivasById(criteria);
	}

	public List getOCRDetalladoAptasList(Map criteria) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getOCRDetalladoAptasList(criteria);
	}

	public List getOCRPedidosDuplicadosList(Map criteria) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getOCRPedidosDuplicadosList(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executeProcesoMontoMinimoMaximo(java.util.Map)
	 */
	public void executeProcesoMontoMinimoMaximo(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeProcesoMontoMinimoMaximo(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executeProcesoRevertirCambiosGP1STO(java.util.Map)
	 */
	public void executeProcesoRevertirCambiosGP1STO(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeProcesoRevertirCambiosGP1STO(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getMaximoUnidades(java.util.Map)
	 */
	public String getMaximoUnidades(Map params) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getMaximoUnidades(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getLongitudMaximoUnidades(java.util.Map)
	 */
	public String getLongitudMaximoUnidades(Map params) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getLongitudMaximoUnidades(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executeActualizarContrarioIndicadores(java.util.Map)
	 */
	public void executeActualizarContrarioIndicadores(Map criteria) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeActualizarContrarioIndicadores(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #anularPedidoFacturado(java.util.Map)
	 */
	public void anularPedidoFacturado(Map map) {
		mantenimientoOCRPedidoControlFacturacionDAO.anularPedidoFacturado(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getPedidosByCriteria(java.util.Map)
	 */
	public List getPedidosByCriteria(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getPedidosByCriteria(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #eliminarPedidoDigitado(java.util.Map)
	 */
	public void eliminarPedidoDigitado(Map criteria) {
		String[] lista = (String[]) criteria.get("lista");

		String id = "";
		for (int i = 0; i < lista.length; i++) {
			id = lista[i];

			Map datos = new HashMap();
			datos.put("codigoPais", StringUtils.split(id, "-")[0]);
			datos.put("codigoPeriodo", StringUtils.split(id, "-")[1]);
			datos.put("codigoCliente", StringUtils.split(id, "-")[2]);
			datos.put("numeroLote", StringUtils.split(id, "-")[3]);
			datos.put("codigoUsuario", criteria.get("codigoUsuario"));

			// eliminar pedidos
			mantenimientoOCRPedidoControlFacturacionDAO
					.eliminarPedidoDigitado(datos);

			// grabar en historico de eliminados
			mantenimientoOCRPedidoControlFacturacionDAO
					.grabarEliminacionPedidoDigitado(datos);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getPedidosAEliminarByCriteria(java.util.Map)
	 */
	public List getPedidosAEliminarByCriteria(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getPedidosAEliminarByCriteria(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #anularPedidoFacturadoSTO(java.util.Map)
	 */
	public void anularPedidoFacturadoSTO(Map map) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.anularPedidoFacturadoSTO(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #deleteOCSClasificaciones(java.util.Map)
	 */
	public void deleteOCSClasificaciones(Map map) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.deleteOCSClasificaciones(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getListaCodigoPlantilla(java.util.Map)
	 */
	public List getListaCodigoPlantilla(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getListaCodigoPlantilla(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getListaPlantillas(java.util.Map)
	 */
	public List getListaPlantillas(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getListaPlantillas(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #insertPlantillas(java.util.Map)
	 */
	public void insertPlantillas(Map map) {
		mantenimientoOCRPedidoControlFacturacionDAO.insertPlantillas(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executeRegeneraPlantillas(java.util.Map)
	 */
	public void executeRegeneraPlantillas(Map map) {
		mantenimientoOCRPedidoControlFacturacionDAO
				.executeRegeneraPlantillas(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getListaSolicitudes(java.util.Map)
	 */
	public List getListaSolicitudes(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getListaSolicitudes(map);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #executeOCSHistorico(java.util.Map)
	 */
	public void executeOCSHistorico(Map params) {
		mantenimientoOCRPedidoControlFacturacionDAO.executeOCSHistorico(params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService#saveHistoricoPedidos()
	 */
	public void saveHistoricoPedidos() {
		mantenimientoOCRPedidoControlFacturacionDAO.saveHistoricoPedidos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #updateLevantarRetornarDeuda(java.util.Map,
	 * biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateLevantarRetornarDeuda(Map map, String[] items) {
		for (int i = 0; i < items.length; i++) {
			String id = items[i];
			map.put("codigoPais", StringUtils.split(id, "|")[0]);
			map.put("codigoCliente", StringUtils.split(id, "|")[1]);
			map.put("codigoPeriodo", StringUtils.split(id, "|")[2]);
			map.put("fechaSolicitud", StringUtils.split(id, "|")[3]);
			map.put("indErrAdmCart", StringUtils.split(id, "|")[4]);
			map.put("numeroLote", StringUtils.split(id, "|")[5]);
			mantenimientoOCRPedidoControlFacturacionDAO
					.updateLevantarRetornarDeuda(map);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #validarPedidoMayorGP1(java.util.Map)
	 */
	public String validarPedidoMayorGP1(Map params) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.validarPedidoMayorGP1(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #cerrarCampanaControlFacturacion
	 * (biz.belcorp.ssicc.spusicc.pedidos.model.PedidoControlFacturacion)
	 */
	public void cerrarPeriodo(Map map) throws Exception {

		log.debug("Cerrando Periodo");
		
		//Pase a Historicos Sin Gestion
		List listaDocuHistoSinGestionar = procesoSTOEjecucionValidacionesService.getTiposDocumentosHistoricoSTOSinGestion(map);

		log.info("listaDocuHistoSinGestionar " + listaDocuHistoSinGestionar.size());

		for (int i = 0; i < listaDocuHistoSinGestionar.size(); i++) {

			Map documentoMap = (Map) listaDocuHistoSinGestionar.get(i);

			List periodos = procesoSTOEjecucionValidacionesService.getListaPeriodosHistoricosSinGestion(documentoMap);

			for (int j = 0; j < periodos.size(); j++) {
				Map criteria = (Map) periodos.get(j);
				String codigoPeriodo = (String) criteria.get("codigoPeriodo");
				documentoMap.put("codigoPeriodo", codigoPeriodo);
				documentoMap.put("numeroProceso", ""+j);
				log.debug("codigoPeriodo " + codigoPeriodo);
				procesoSTOEjecucionValidacionesService.executeHistoricoSinGestionar(documentoMap);
			}
		}

		// Se graba en historicos
		List listaDocuHisto = procesoSTOEjecucionValidacionesService.getTiposDocumentosHistoricoSTOAut(map);

		log.info("listaDocuHisto " + listaDocuHisto.size());

		for (int i = 0; i < listaDocuHisto.size(); i++) {

			Map documentoMap = (Map) listaDocuHisto.get(i);

			List periodos = mantenimientoOCRPedidoControlFacturacionDAO.getListaPeriodosHistoricos(documentoMap);

			documentoMap.put("numLote", "");
			documentoMap.put("fechaInicio", "");
			documentoMap.put("fechaFin", "");
			String procesoHistorico = (String) documentoMap.get("procesoHistorico");

			for (int j = 0; j < periodos.size(); j++) {
				Map criteria = (Map) periodos.get(j);
				String codigoPeriodo = (String) criteria.get("codigoPeriodo");
				log.debug("codigoPeriodo " + codigoPeriodo);
				documentoMap.put("codigoPeriodo", codigoPeriodo);
				procesoSTOEjecucionValidacionesService.executeAlmacenamientoHistorico(procesoHistorico,documentoMap);
			}
		}
		log.info("listaDocuHisto " + listaDocuHisto.size());
		/*Se Cierra la Campaa*/
                //mantenimientoOCRPedidoControlFacturacionDAO.cerrarPeriodo(map);
		
		//Facturacion Adicional
		String codigoPais = MapUtils.getString(map, "codigoPais", "");
		String codigoSistema = Constants.STO_CODIGO_SISTEMA;
		
		String ejecutarProceso = genericoDAO.getParametroPais(codigoPais, codigoSistema, Constants.STO_CODIGO_PARAMETRO_FAD_EJECUCION);
		
		if(StringUtils.equals(ejecutarProceso, Constants.SI))
		{
			//Ejecuta el proceso y genera la data para enviar el archivo
			map.put("directorioReporte", null);
			mantenimientoOCRPedidoControlFacturacionDAO.executeFacturacionAdicional(map);
			
			String directorioReporte = MapUtils.getString(map, "directorioReporte");
			
			if(StringUtils.isNotBlank(directorioReporte))
			{
				String nombreArchivo = "FAD_" + MapUtils.getString(map, "codigoPeriodo") + ".csv";
				
				//Verificamos si el archivo existe
				String rutaFuente = FileUtil.formatDirectory(directorioReporte) + nombreArchivo;
				
				File fileFuente = new File(rutaFuente);
				
				if(fileFuente.exists())
				{
					
					String directorioDestino = genericoDAO.getParametroPais(codigoPais, codigoSistema, Constants.STO_CODIGO_PARAMETRO_FAD_RUTA);
					String rutaDestino = FileUtil.formatDirectory(directorioDestino) + nombreArchivo;
					File fileDestino = new File(rutaDestino);
									
					//Copiamos el reporte al Directorio que debe de quedar el archivo
					FileUtils.copyFile(fileFuente, fileDestino);
					//
					
					//Envio por Correo
					String emailOrigen = genericoDAO.getParametroPais(codigoPais, codigoSistema, Constants.STO_CODIGO_PARAMETRO_FAD_EMAIL_ORIGEN);
					String emailDestino = genericoDAO.getParametroPais(codigoPais, codigoSistema, Constants.STO_CODIGO_PARAMETRO_FAD_EMAIL_DESTINO);
					String emailAsunto = genericoDAO.getParametroPais(codigoPais, codigoSistema, Constants.STO_CODIGO_PARAMETRO_FAD_ASUNTO_EMAIL_DESTINO);
					
					map.put("correoOrigen", emailOrigen);					
					map.put("correosDestinos", emailDestino);
					map.put("correoCC","");
					map.put("subject", emailAsunto);
					map.put("nombreArchivoReporte", nombreArchivo);
					map.put("fileAttachment", fileFuente);
					
					MailParams mailParams = new MailParams();
					mailParams.setUsuario((Usuario)MapUtils.getObject(map, "usuario"));
					mailParams.setPais((Pais)MapUtils.getObject(map, "pais"));
					map.put("parameterMap", new HashMap());
					
					mailParams.setQueryParams(map);
					
					
					mailService.enviarMail(mailParams);
				}
				else
				{
					throw new Exception("El archivo no fue generado");
				}
				
			}
		}
		mantenimientoOCRPedidoControlFacturacionDAO.cerrarPeriodo(map);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.
	 * MantenimientoOCRPedidoControlFacturacionService
	 * #getConsultoraCabeceraSimpleByCriteria(java.util.Map)
	 */
	public List getConsultoraCabeceraSimpleByCriteria(Map map) {
		return mantenimientoOCRPedidoControlFacturacionDAO
				.getConsultoraCabeceraSimpleByCriteria(map);
	}
}