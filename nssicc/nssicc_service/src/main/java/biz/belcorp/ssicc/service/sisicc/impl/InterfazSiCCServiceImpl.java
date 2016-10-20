/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.jasperreports.JasperReportsUtils;
import org.springframework.ui.velocity.VelocityEngineUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.sisicc.model.Cliente;
import biz.belcorp.ssicc.dao.sisicc.model.EstimadoProductos;
import biz.belcorp.ssicc.dao.sisicc.model.LibretaAhorro;
import biz.belcorp.ssicc.dao.spisicc.ProcesoImpresionDAO;
import biz.belcorp.ssicc.service.exception.ServiceException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;

/**
 * 
 * <p>
 * <a href="InterfazSiCCServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */


@Service("sisicc.interfazSiCCService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSiCCServiceImpl extends BaseService implements
		InterfazSiCCService {

	
	@Resource(name="sisicc.interfazSiCCDAO")
	private InterfazSiCCDAO interfazSiCCDAO;
	
	@Resource(name="spisicc.procesoImpresionDAO")
	private ProcesoImpresionDAO procesoImpresionDAO;
	
	private VelocityEngine velocityEngine;
	
	@Resource(name="sisicc.interfazOCRDAO")
	private InterfazOCRDAO interfazOCRDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getLista(java.lang.String, java.util.Map)
	 */
	public List getLista(String tipoLista, Map params){		
		return interfazSiCCDAO.getLista(tipoLista, params);
		
	}
	
	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getMarcas()
	 */
	public List getMarcas() {
		return this.interfazSiCCDAO.getMarcas();
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getRangosPeriodo()
	 */
	public List getRangosPeriodo() {
		return this.interfazSiCCDAO.getRangosPeriodo();
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCanalesByCodigoISO(java.lang.String)
	 */
	public List getCanalesByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getCanalesByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCanalesRolByCodigoISO(java.lang.String)
	 */
	public List getCanalesRolByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getCanalesRolByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCanalesRolByCodigoISO(java.lang.String)
	 */
	public List getEstadosRolByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getEstadosRolByCodigoISO(codigo);
	}

	
	
	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getAccesosByCodigoISO(java.lang.String)
	 */
	public List getAccesosByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getAccesosByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getAccesosByCanalByCodigoISO(java.lang.String)
	 */
	public List getAccesosByCanalByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getAccesosByCanalByCodigoISO(codigo);
	}

	public List getAccesosTodosByCanalByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getAccesosTodosByCanalByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSubaccesosByCodigoISO(java.lang.String)
	 */
	public List getSubaccesosByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getSubaccesosByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSubaccesosByCriteria(java.util.Map)
	 */
	public List getSubaccesosByCriteria(Map criteria) {
		return this.interfazSiCCDAO.getSubaccesosByCriteria(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTiposClientesByCodigoISO(java.lang.String)
	 */
	public List getTiposClientesByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getTiposClientesByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSubTiposClientesByCriteria(java.lang.String)
	 */
	public List getSubTiposClientesByCriteria(Map criteria) {
		return this.interfazSiCCDAO.getSubTiposClientesByCriteria(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getPeriodosByTipoPMC(java.lang.String)
	 */
	public List getPeriodosByTipoPMC(String tipo) {
		return this.interfazSiCCDAO.getPeriodosByTipoPMC(tipo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getActividades(java.lang.String,
	 *      java.lang.String)
	 */
	public List getActividades(String codigoIsoIdioma, String codigoMarca) {
		return this.interfazSiCCDAO
				.getActividades(codigoIsoIdioma, codigoMarca);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getActividadesByCodigoISO(java.lang.String)
	 */
	public List getActividadesByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getActividadesByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelCronogramaFacturacion(java.util.Map)
	 */
	public List getInterfazMyEbelCronogramaFacturacion(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazREUCronogramaFacturacion(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelCronogramaFacturacion(java.util.Map)
	 */
	public List getInterfazBELUbicacionesGeograficas(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazBELUbicacionesGeograficas(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazSABEnviarIncentivosConsultoras(java.util.Map)
	 */
	public List getInterfazSABEnviarIncentivosConsultoras(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazSABEnviarIncentivosConsultoras(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazBELPorcentajesReferencia(java.util.Map)
	 */
	public List getInterfazBELPorcentajesReferencia(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazBELPorcentajesReferencia(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazBELUnidadesAtendidas(java.util.Map)
	 */
	public List getInterfazBELUnidadesAtendidas(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazBELUnidadesAtendidas(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSaldoCtaCteCliente(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String)
	 */
	public Double getSaldoCtaCteCliente(String codigoPais,
			String codigoCliente, String fechaDocumentoDesde,
			String fechaDocumentoHasta, String fechaVencimientoDesde,
			String fechaVencimientoHasta) {
		return this.interfazSiCCDAO.getSaldoCtaCteCliente(codigoPais,
				codigoCliente, fechaDocumentoDesde, fechaDocumentoHasta,
				fechaVencimientoDesde, fechaVencimientoHasta);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazReutilizacionConsultoras(java.util.Map)
	 */
	public List getInterfazBELDireccionClientes(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazBELDireccionClientes(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazRetailMatrizCampanya(java.util.Map)
	 */
	public List getInterfazREUPeriodosFacturacion(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazREUPeriodosFacturacion(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazRetailMatrizCampanya(java.util.Map)
	 */
	public List getInterfazRETEnviarMatrizCampanya(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazRETEnviarMatrizCampanya(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */
	public List getInterfazMyEbelMovimientosCuentaCorriente(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazMYEMovimientosCuentaCorriente(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getPeriodosByTipo(java.lang.String)
	 */
	public List getPeriodosByTipo(String tipo) {
		return this.interfazSiCCDAO.getPeriodosByTipo(tipo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazRetailFacturasVentaDirecta(java.util.Map)
	 */
	public List getInterfazRETEnviarFacturasVentaDirecta(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazRETEnviarFacturasVentaDirecta(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazRETEnviarComplementoFacturasVentaDirecta(java.util.Map)
	 */
	public List getInterfazRETEnviarComplementoFacturasVentaDirecta(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazRETEnviarComplementoFacturasVentaDirecta(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazRetailDocumentosAnulados(java.util.Map)
	 */
	public List getInterfazReutilizacionDocumentosAnulados(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazREUDocumentosAnulados(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazOCREnviarRegiones(java.util.Map)
	 */
	public List getInterfazOCREnviarRegiones(Map params) {
		List datos = this.interfazSiCCDAO.getInterfazOCREnviarRegiones(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazOCREnviarZonas(java.util.Map)
	 */
	public List getInterfazOCREnviarZonas(Map params) {
		List datos = this.interfazSiCCDAO.getInterfazOCREnviarZonas(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazSAMEnviarInicializacionStocks(java.util.Map)
	 */
	public List getInterfazSAMEnviarInicializacionStocks(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazSAMEnviarInicializacionStocks(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazSAMInicializacionStocks(java.util.Map)
	 */
	public List getInterfazCOMRecopla(Map params) {
		List datos = this.interfazSiCCDAO.getInterfazCOMRecopla(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getAlmacenesByCodigoISO(java.lang.String)
	 */
	public List getAlmacenesByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getAlmacenesByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getAlmacenesByCodigoISO(java.lang.String)
	 */
	public List getAlmacenesByCodigoISOPais(Map params) {
		return this.interfazSiCCDAO.getAlmacenesByCodigoISOPais(params);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getEstadosMercaderiaByCodigoISO(java.lang.String)
	 */
	public List getEstadosMercaderiaByCodigoISO(String codigo) {
		return this.interfazSiCCDAO.getEstadosMercaderiaByCodigoISO(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazSABEnviarRentabilidadPorZona(java.util.Map)
	 */
	public List getInterfazSABEnviarRentabilidadPorZona(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazSABEnviarRentabilidadPorZona(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazSABEnviarFuenteVentasPrevista(java.util.Map)
	 */
	public List getInterfazSABEnviarFuenteVentasPrevista(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazSABEnviarFuenteVentasPrevista(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSociedadesByCodigoPais(java.lang.String)
	 */
	public List getSociedadesByCodigoPais(String codigo) {
		return this.interfazSiCCDAO.getSociedadesByCodigoPais(codigo);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazBELFacturasCabecera(java.util.Map)
	 */
	public List getInterfazBELFacturasCabecera(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazBELFacturasCabecera(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazBELFacturasDetalle(java.util.Map)
	 */
	public List getInterfazBELFacturasDetalle(Map params) {
		List datos = this.interfazSiCCDAO.getInterfazBELFacturasDetalle(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazReutilizacionMatrizCampanya(java.util.Map)
	 */
	public List getInterfazReutilizacionMatrizCampanya(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazReutilizacionMatrizCampanya(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getDescuentoEspecifico(int)
	 */
	public String getDescuentoEspecifico(int idCabecera) {
		return this.interfazSiCCDAO.getDescuentoEspecifico(idCabecera);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getDescuentoVarios(java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String, java.lang.String, java.lang.String,
	 *      java.lang.String)
	 */
	public String getDescuentoVarios(String codigoPeriodo,
			String codigoTipoCliente, String codigoSubtipoCliente,
			String codigoTipoOferta, String codigoNegocio,
			String codigoUnidadNegocio, String codigoMarcaProducto,
			String exclusionTipoOferta) {
		return this.interfazSiCCDAO.getDescuentoVarios(codigoPeriodo,
				codigoTipoCliente, codigoSubtipoCliente, codigoTipoOferta,
				codigoNegocio, codigoUnidadNegocio, codigoMarcaProducto,
				exclusionTipoOferta);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazGISEnviarDireccionConsultoras(java.util.Map)
	 */
	public List getInterfazGISEnviarDireccionConsultoras(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazGISEnviarDireccionConsultoras(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getClientesSICCByCriteria(java.util.Map)
	 */
	public List getClientesSICCByCriteria(Map criteria) {
		return this.interfazSiCCDAO.getClientesSICCByCriteria(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getClienteSICCByCodigo(java.lang.String)
	 */
	public Cliente getClienteSICCByCodigo(Map criteria) {
		return this.interfazSiCCDAO.getClienteSICCByCodigo(criteria);
	}

	private Map getParameters(Pais pais, Usuario usuario) throws Exception {
		Map parameters = new HashMap();

		parameters.put("LOGON_USER", usuario.getLogin());
		parameters.put("PAIS_LOGON_USER", pais.getDescripcion());

		return parameters;
	}

	private JasperReport getReport() throws Exception {
		// Cargamos el reporte
		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "clientesPendientes.jasper", getClass());
		return (JasperReport) JRLoader.loadObject(resource.getInputStream());
	}

	private JRDataSource getDataSource(List data) {
		return new JRMapCollectionDataSource(data);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getClientesPendientesActualizarUA(java.util.Map)
	 */
	public List getClientesPendientesActualizarUA(Map criteria) {
		return this.interfazSiCCDAO.getClientesPendientesActualizarUA(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getClientesPendientesActualizarUA(java.util.Map)
	 */
	public List getClientesPendientesActualizarXLSUA(Map criteria) {
		return this.interfazSiCCDAO
				.getClientesPendientesActualizarXLSUA(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public byte[] getBytesReporteClientesPendientes(Map params,
			Usuario usuario, Pais pais) {
		byte[] bytes = {};
		List clientes = getClientesPendientesActualizarUA(params);

		if (clientes.size() > 0) {
			try {
				bytes = JasperRunManager.runReportToPdf(getReport(),
						getParameters(pais, usuario), getDataSource(clientes));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}
		return bytes;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public List getReporteConsultorasRecomendadasPDF(Map criteria) {
		return this.interfazSiCCDAO
				.getReporteConsultorasRecomendadasPDF(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public List getReporteConsultorasRecomendadasXLS(Map criteria) {
		return this.interfazSiCCDAO
				.getReporteConsultorasRecomendadasXLS(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public byte[] getBytesReporteConsultorasRecomendadasPDF(Map params,
			Usuario usuario, Pais pais) {
		byte[] bytes = {};
		List consultoras = getReporteConsultorasRecomendadasPDF(params);
		log.debug("tamano lista:" + consultoras.size());
		if (consultoras.size() > 0) {
			try {
				bytes = JasperRunManager.runReportToPdf(
						getReportConsultorasPDF(),
						getParameters(pais, usuario),
						getDataSource(consultoras));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}
		return bytes;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public byte[] getBytesReporteConsultorasRecomendadasXLS(Map params,
			Usuario usuario, Pais pais) {
		byte[] bytes = {};
		List consultoras = getReporteConsultorasRecomendadasXLS(params);
		log.debug("tamano lista en excel:" + consultoras.size());

		if (consultoras.size() > 0) {
			try {

				FileOutputStream fos = null;
				File file = new File("reporteConsultorasRecomendadas.xls");

				try {
					fos = new FileOutputStream(file);
					JasperReportsUtils.renderAsXls(getReportConsultorasXLS(),
							getParameters(pais, usuario),
							getDataSource(consultoras), fos);
					fos.flush();
					fos.close();
					fos = null;
				} catch (Exception e) {
					if (fos != null) {
						try {
							fos.close();
							fos = null;
						} catch (IOException ignore) {
						}
					}
					log.error(e.getMessage(), e);
					throw new ServiceException(e.getMessage());
				}

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}
		return bytes;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	private JasperReport getReportConsultorasPDF() throws Exception {
		// Cargamos el reporte
		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "consultorasRecomendadaspdf.jasper", getClass());
		return (JasperReport) JRLoader.loadObject(resource.getInputStream());
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	private JasperReport getReportConsultorasXLS() throws Exception {
		// Cargamos el reporte
		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "consultorasRecomendadasxls.jasper", getClass());
		return (JasperReport) JRLoader.loadObject(resource.getInputStream());
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public List getPeriodosByPMC(Map criteria) {
		return this.interfazSiCCDAO.getPeriodosByPMC(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public List getPeriodosDefaultByPMC(Map criteria) {
		return this.interfazSiCCDAO.getPeriodosDefaultByPMC(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */
	public String getPeriodoDefaultByPMC(Map criteria) {
		String defecto = "";
		Base base = new Base();
		List aux = this.getPeriodosDefaultByPMC(criteria);
		if (aux.size() > 0) {
			base = (Base) aux.get(0);
			defecto = base.getCodigo();
		}
		return defecto;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public List getInterfazCOMEnviarLibretaAhorros(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazCOMEnviarLibretaAhorros(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public List getInterfazCOMEnviarLideresNuevas(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazCOMEnviarLideresNuevas(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */

	public List getInterfazCOMEnviarPagoLideres(Map params) {
		List datos = this.interfazSiCCDAO
				.getInterfazCOMEnviarPagoLideres(params);
		return datos;
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazMyEbelMovimientosCuentaCorriente(java.util.Map)
	 */
	public List setInterfazCOMRecopla(List listaAhorros) {
		List listaAhorrosErroneo = new LinkedList();
		LibretaAhorro libretaAhorro;
		int existe = 0;
		String existeStr = "";

		for (int i = 0; i < listaAhorros.size(); i++) {
			libretaAhorro = new LibretaAhorro();
			libretaAhorro = (LibretaAhorro) listaAhorros.get(i);
			existeStr = this.interfazSiCCDAO
					.getCantidadLibretaAhorrobyDNI(libretaAhorro
							.getDocumentoIdentidad());
			existe = Integer.parseInt(existeStr);
			if (existe == 0)
				listaAhorrosErroneo.add(libretaAhorro);
			else
				this.interfazSiCCDAO.updateLibretaAhorro(libretaAhorro);
		}
		return listaAhorrosErroneo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSociedadByCodigo(java.util.Map)
	 */
	public Base getSociedadByCodigo(Map criteria) {
		return interfazSiCCDAO.getSociedadByCodigo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getAlmacenByCodigo(java.util.Map)
	 */
	public Base getAlmacenByCodigo(Map criteria) {
		return interfazSiCCDAO.getAlmacenByCodigo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCanalByCodigo(java.util.Map)
	 */
	public Base getCanalByCodigo(Map criteria) {
		return interfazSiCCDAO.getCanalByCodigo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getRangoPeriodoByCodigo(java.lang.String)
	 */
	public Base getRangoPeriodoByCodigo(String periodo) {
		return interfazSiCCDAO.getRangoPeriodoByCodigo(periodo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getRegionByCodigo(java.util.Map)
	 */
	public Base getRegionByCodigo(Map criteria) {
		return interfazSiCCDAO.getRegionByCodigo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getZonaByCodigo(java.util.Map)
	 */
	public Base getZonaByCodigo(Map criteria) {
		return interfazSiCCDAO.getZonaByCodigo(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getMarcaByCodigo(java.lang.String)
	 */
	public Base getMarcaByCodigo(String codigoMarca) {
		return interfazSiCCDAO.getMarcaByCodigo(codigoMarca);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCuentasCorrientesByCodigoPais(java.lang.String)
	 */
	public List getCuentasCorrientesByCodigoPais(String codigo) {
		return this.interfazSiCCDAO.getCuentasCorrientesByCodigoPais(codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTiposTransaccionesByCodigoPais(java.lang.String)
	 */
	public List getTiposTransaccionesByCodigoPais(String codigo) {
		return this.interfazSiCCDAO.getTiposTransaccionesByCodigoPais(codigo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getHorarios()
	 */
	public List getHorarios() {
		ArrayList resultado = new ArrayList();
		Base base = new Base();
		base.setCodigo("N");
		base.setDescripcion("Normal");
		resultado.add(base);
		base = new Base();
		base.setCodigo("A");
		base.setDescripcion("Adicional");
		resultado.add(base);
		return resultado;
	}

	public List getReportePERListaGenerica(Map criteria) {
		return interfazSiCCDAO.getReportePERListaGenerica(criteria);
	}

	public List getReportePERListaControlCliente(Map criteria) {
		return interfazSiCCDAO.getReportePERListaControlCliente(criteria);
	}

	public List getReportePERLibroPercepciones(Map criteria) {
		return interfazSiCCDAO.getReportePERLibroPercepciones(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getReportePERListaCargosFacturacion(java.util.Map)
	 */
	public List getReportePERListaCargosFacturacion(Map criteria) {
		return interfazSiCCDAO.getReportePERListaCargosFacturacion(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getReportePERListaCargosDirectos(java.util.Map)
	 */
	public List getReportePERListaCargosDirectos(Map criteria) {
		return interfazSiCCDAO.getReportePERListaCargosDirectos(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getReportePERListaAbonosPorNotaCredito(java.util.Map)
	 */
	public List getReportePERListaAbonosPorNotaCredito(Map criteria) {
		return interfazSiCCDAO.getReportePERListaAbonosPorNotaCredito(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getReportePERListaAbonosDirectos(java.util.Map)
	 */
	public List getReportePERListaAbonosDirectos(Map criteria) {
		return interfazSiCCDAO.getReportePERListaAbonosDirectos(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getReportePERListaAbonosDirectosPercepciones(java.util.Map)
	 */
	public List getReportePERListaAbonosDirectosPercepciones(Map criteria) {
		return interfazSiCCDAO
				.getReportePERListaAbonosDirectosPercepciones(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getReportePERListaAbonosPorCobranza(java.util.Map)
	 */
	public List getReportePERListaAbonosPorCobranza(Map criteria) {
		return interfazSiCCDAO.getReportePERListaAbonosPorCobranza(criteria);
	}

	private JasperReport getReport(String binario) throws Exception {
		// Cargamos el reporte
		ClassPathResource resource = null;
		try {
			resource = new ClassPathResource(binario, getClass());
		}
		catch (Exception e) {
			resource = new ClassPathResource(
					Constants.JASPER_DIRECTORIO + binario, getClass());	
		}
		return (JasperReport) JRLoader.loadObject(resource.getInputStream());
	}

	private Map getParametersCuentasCorrientes(Map criteria, Pais pais,
			Usuario usuario) throws Exception {
		// Creamos los parametros a enviar
		Map parameters = new HashMap();
		parameters.put("fechaInicio", criteria.get("fechaDesde").toString());
		parameters.put("fechaFinal", criteria.get("fechaHasta").toString());
		parameters.put("descripcionPais", criteria.get("descripcionPais")
				.toString());
		parameters.put("codigoSociedad", criteria.get("codigoSociedad")
				.toString());

		// 1. CARGOS FACTURACION
		// Obtenemos la relacion de los cargos de facturacion
		List cargosFacturacion = interfazSiCCDAO
				.getReportePERListaCargosFacturacion(criteria);

		ClassPathResource cargosFacturacionResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "cargosFacturacion.jasper", getClass());
		JasperReport cargosFacturacionSubreport = (JasperReport) JRLoader
				.loadObject(cargosFacturacionResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters
				.put("cargosFacturacionSubreport", cargosFacturacionSubreport);
		parameters.put("cargosFacturacionSubreportData",
				new JRMapCollectionDataSource(cargosFacturacion));
		cargosFacturacion = null;

		// 2. CARGOS DIRECTOS
		// Obtenemos la relacion de los cargos directos
		List cargosDirectos = interfazSiCCDAO
				.getReportePERListaCargosDirectos(criteria);

		ClassPathResource cargosDirectosResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "cargosDirectos.jasper", getClass());
		JasperReport cargosDirectosSubreport = (JasperReport) JRLoader
				.loadObject(cargosDirectosResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("cargosDirectosSubreport", cargosDirectosSubreport);
		parameters.put("cargosDirectosSubreportData",
				new JRMapCollectionDataSource(cargosDirectos));

		cargosDirectos = null;

		// 3. ABONOS NOTA CREDITO
		// Obtenemos la relacion de los premios solicitados
		List abonosNotaCredito = interfazSiCCDAO
				.getReportePERListaAbonosPorNotaCredito(criteria);

		ClassPathResource abonosNotaCreditoResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO +"abonosNotaCredito.jasper", getClass());
		JasperReport abonosNotaCreditoSubreport = (JasperReport) JRLoader
				.loadObject(abonosNotaCreditoResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters
				.put("abonosNotaCreditoSubreport", abonosNotaCreditoSubreport);
		parameters.put("abonosNotaCreditoSubreportData",
				new JRMapCollectionDataSource(abonosNotaCredito));

		abonosNotaCredito = null;

		// 4. ABONOS DIRECTOS
		// Obtenemos la relacion de los abonosNotaCredito solicitados
		List abonosDirectos = interfazSiCCDAO
				.getReportePERListaAbonosDirectos(criteria);

		ClassPathResource abonosDirectosResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "abonosDirectos.jasper", getClass());
		JasperReport abonosDirectosSubreport = (JasperReport) JRLoader
				.loadObject(abonosDirectosResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("abonosDirectosSubreport", abonosDirectosSubreport);
		parameters.put("abonosDirectosSubreportData",
				new JRMapCollectionDataSource(abonosDirectos));
		abonosDirectos = null;

		// 5. ABONOS DIRECTOS PERCEPCIONES
		// Obtenemos la relacion de los abonosNotaCredito solicitados
		List abonosDirectosPercepciones = interfazSiCCDAO
				.getReportePERListaAbonosDirectosPercepciones(criteria);

		ClassPathResource abonosDirectosPercepcionesResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "abonosDirectosPercepciones.jasper", getClass());
		JasperReport abonosDirectosPercepcionesSubreport = (JasperReport) JRLoader
				.loadObject(abonosDirectosPercepcionesResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("abonosDirectosPercepcionesSubreport",
				abonosDirectosPercepcionesSubreport);
		parameters.put("abonosDirectosPercepcionesSubreportData",
				new JRMapCollectionDataSource(abonosDirectosPercepciones));
		abonosDirectosPercepciones = null;

		// 6. ABONOS COBRANZA
		// Obtenemos la relacion de los abonosNotaCredito solicitados
		List abonosCobranza = interfazSiCCDAO
				.getReportePERListaAbonosPorCobranza(criteria);

		ClassPathResource abonosCobranzaResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "abonosCobranza.jasper", getClass());
		JasperReport abonosCobranzaSubreport = (JasperReport) JRLoader
				.loadObject(abonosCobranzaResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("abonosCobranzaSubreport", abonosCobranzaSubreport);
		parameters.put("abonosCobranzaSubreportData",
				new JRMapCollectionDataSource(abonosCobranza));
		abonosCobranza = null;

		return parameters;
	}

	public List getReportePERConsolidadoCobranzasChequearDatos(Map criteria) {
		return null;
		// return
		// interfazSiCCDAO.getReportePERConsolidadoCobranzasChequearDatos(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getBytesReporteCuentasCorrientes(java.util.Map,
	 *      biz.belcorp.ssicc.model.Usuario, biz.belcorp.ssicc.model.Pais)
	 */

	public byte[] getBytesReporteCuentasCorrientes(Map criteria,
			Usuario usuario, Pais pais) {
		byte[] bytes = {};
		List cabecera = getReportePERListaGenerica(criteria);

		if (cabecera != null && cabecera.size() > 0) {
			try {
				bytes = JasperRunManager
						.runReportToPdf(getReport("/cuentasCorrientes.jasper"),
								getParametersCuentasCorrientes(criteria, pais,
										usuario), getDataSource(cabecera));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}

		}

		return bytes;
	}

	public byte[] getBytesAntiguedadDeudasMeses(Map criteria, Usuario usuario,
			Pais pais) {
		byte[] bytes = {};

		List cabecera = getReportePERAntiguedadDeudasChequearDatos(criteria);

		if (cabecera.size() > 0) {
			try {
				bytes = JasperRunManager.runReportToPdf(
						getReport("/antiguedadDeudasMeses.jasper"),
						getParametersAntiguedadDeudasMeses(criteria, pais,
								usuario), getDataSource(cabecera));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public byte[] getBytesAntiguedadDeudasPeriodos(Map criteria,
			Usuario usuario, Pais pais) {
		byte[] bytes = {};
		List cabecera = getReportePERAntiguedadDeudasChequearDatos(criteria);

		if (cabecera.size() > 0) {
			try {
				bytes = JasperRunManager.runReportToPdf(
						getReport("/antiguedadDeudasPeriodos.jasper"),
						getParametersAntiguedadDeudasPeriodos(criteria, pais,
								usuario), getDataSource(cabecera));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public List getReportePERAntiguedadDeudasMesesHaceDosAnhos(Map criteria) {
		return interfazSiCCDAO
				.getReportePERAntiguedadDeudasMesesHaceDosAnhos(criteria);
	}

	public List getReportePERAntiguedadDeudasMesesHaceUnAnho(Map criteria) {
		return interfazSiCCDAO
				.getReportePERAntiguedadDeudasMesesHaceUnAnho(criteria);
	}

	public List getReportePERAntiguedadDeudasMesesActual(Map criteria) {
		return interfazSiCCDAO
				.getReportePERAntiguedadDeudasMesesActual(criteria);
	}

	public List getReportePERAntiguedadDeudasPeriodosHaceDosAnhos(Map criteria) {
		return interfazSiCCDAO
				.getReportePERAntiguedadDeudasPeriodosHaceDosAnhos(criteria);
	}

	public List getReportePERAntiguedadDeudasPeriodosHaceUnAnho(Map criteria) {
		return interfazSiCCDAO
				.getReportePERAntiguedadDeudasPeriodosHaceUnAnho(criteria);
	}

	public List getReportePERAntiguedadDeudasPeriodosActual(Map criteria) {
		return interfazSiCCDAO
				.getReportePERAntiguedadDeudasPeriodosActual(criteria);
	}

	public List getReportePERAntiguedadDeudasChequearDatos(Map criteria) {
		return interfazSiCCDAO
				.getReportePERAntiguedadDeudasChequearDatos(criteria);
	}

	private Map getParametersAntiguedadDeudasMeses(Map criteria, Pais pais,
			Usuario usuario) throws Exception {
		// Creamos los parametros a enviar
		Map parameters = new HashMap();
		parameters.put("codigoRegion", criteria.get("codigoRegion").toString());
		parameters.put("codigoRegion", criteria.get("codigoRegion").toString());
		parameters.put("pais", pais.getDescripcion());
		parameters.put("sociedad", criteria.get("descripcionSociedad")
				.toString());
		if (criteria.get("codigoSeccion").toString().equals("")) {
			parameters.put("seccion", "TODAS");
		} else
			parameters.put("seccion", criteria.get("codigoSeccion").toString());

		if (criteria.get("codigoZona").toString().equals("")) {
			parameters.put("zona", "TODAS");
		} else
			parameters.put("zona", criteria.get("codigoZona").toString());

		parameters.put("codigoRegion", criteria.get("codigoRegion").toString());
		if (criteria.get("codigoSeccion").toString().equals("")) {
			parameters.put("seccion", "TODAS");
		} else
			parameters.put("seccion", criteria.get("codigoSeccion").toString());

		if (criteria.get("codigoZona").toString().equals("")) {
			parameters.put("zona", "TODAS");
		} else
			parameters.put("zona", criteria.get("codigoZona").toString());

		parameters.put("codigoRegion", criteria.get("codigoRegion").toString());
		if (criteria.get("codigoSeccion").toString().equals("")) {
			parameters.put("seccion", "TODAS");
		} else
			parameters.put("seccion", criteria.get("codigoSeccion").toString());

		if (criteria.get("codigoZona").toString().equals("")) {
			parameters.put("zona", "TODAS");
		} else
			parameters.put("zona", criteria.get("codigoZona").toString());

		parameters.put("codigoRegion", criteria.get("codigoRegion").toString());
		if (criteria.get("codigoSeccion").toString().equals("")) {
			parameters.put("seccion", "TODAS");
		} else
			parameters.put("seccion", criteria.get("codigoSeccion").toString());

		if (criteria.get("codigoZona").toString().equals("")) {
			parameters.put("zona", "TODAS");
		} else
			parameters.put("zona", criteria.get("codigoZona").toString());

		// 1. CARGOS FACTURACION
		// Obtenemos la relacion de los cargos de facturacion
		List meses2 = interfazSiCCDAO
				.getReportePERAntiguedadDeudasMesesHaceDosAnhos(criteria);

		ClassPathResource meses2Resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "deudasMesesHaceDosAnhos.jasper", getClass());
		JasperReport meses2Subreport = (JasperReport) JRLoader
				.loadObject(meses2Resource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("mesesSubreport2", meses2Subreport);
		parameters.put("mesesSubreportData2", new JRMapCollectionDataSource(
				meses2));

		// 2. CARGOS DIRECTOS
		// Obtenemos la relacion de los cargos directos
		List meses1 = interfazSiCCDAO
				.getReportePERAntiguedadDeudasMesesHaceUnAnho(criteria);

		ClassPathResource meses1Resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "deudasMesesHaceUnAnho.jasper", getClass());
		JasperReport meses1Subreport = (JasperReport) JRLoader
				.loadObject(meses1Resource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("mesesSubreport1", meses1Subreport);
		parameters.put("mesesSubreportData1", new JRMapCollectionDataSource(
				meses1));

		// 3. ABONOS NOTA CREDITO
		// Obtenemos la relacion de los premios solicitados
		List meses = interfazSiCCDAO
				.getReportePERAntiguedadDeudasMesesActual(criteria);
		ClassPathResource mesesResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "deudasMesesActual.jasper", getClass());
		JasperReport mesesSubreport = (JasperReport) JRLoader
				.loadObject(mesesResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("mesesSubreport", mesesSubreport);
		parameters.put("mesesSubreportData", new JRMapCollectionDataSource(
				meses));

		return parameters;
	}

	private Map getParametersAntiguedadDeudasPeriodos(Map criteria, Pais pais,
			Usuario usuario) throws Exception {
		// Creamos los parametros a enviar
		Map parameters = new HashMap();
		parameters.put("codigoRegion", criteria.get("codigoRegion").toString());
		parameters.put("codigoRegion", criteria.get("codigoRegion").toString());
		parameters.put("pais", pais.getDescripcion());
		parameters.put("sociedad", criteria.get("descripcionSociedad")
				.toString());

		if (criteria.get("codigoSeccion").toString().equals("")) {
			parameters.put("seccion", "TODAS");
		} else
			parameters.put("seccion", criteria.get("codigoSeccion").toString());

		if (criteria.get("codigoZona").toString().equals("")) {
			parameters.put("zona", "TODAS");
		} else
			parameters.put("zona", criteria.get("codigoZona").toString());

		// 1. CARGOS FACTURACION
		// Obtenemos la relacion de los cargos de facturacion
		List periodos2 = interfazSiCCDAO
				.getReportePERAntiguedadDeudasPeriodosHaceDosAnhos(criteria);

		ClassPathResource periodos2Resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "deudasPeriodosHaceDosAnhos.jasper", getClass());
		JasperReport periodos2Subreport = (JasperReport) JRLoader
				.loadObject(periodos2Resource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("periodosSubreport2", periodos2Subreport);
		parameters.put("periodosSubreportData2", new JRMapCollectionDataSource(
				periodos2));

		// 2. CARGOS DIRECTOS
		// Obtenemos la relacion de los cargos directos
		List periodos1 = interfazSiCCDAO
				.getReportePERAntiguedadDeudasPeriodosHaceUnAnho(criteria);

		ClassPathResource periodos1Resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "deudasPeriodosHaceUnAnho.jasper", getClass());
		JasperReport periodos1Subreport = (JasperReport) JRLoader
				.loadObject(periodos1Resource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("periodosSubreport1", periodos1Subreport);
		parameters.put("periodosSubreportData1", new JRMapCollectionDataSource(
				periodos1));

		// 3. ABONOS NOTA CREDITO
		// Obtenemos la relacion de los premios solicitados
		List periodos = interfazSiCCDAO
				.getReportePERAntiguedadDeudasPeriodosActual(criteria);

		ClassPathResource periodosResource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "deudasPeriodosActual.jasper", getClass());
		JasperReport periodosSubreport = (JasperReport) JRLoader
				.loadObject(periodosResource.getInputStream());

		// Guardamos la referencia del reporte y su datasource en el map
		parameters.put("periodosSubreport", periodosSubreport);
		parameters.put("periodosSubreportData", new JRMapCollectionDataSource(
				periodos));

		return parameters;
	}

	public byte[] getBytesReporteControlCliente(Map criteria, Usuario usuario,
			Pais pais) {
		byte[] bytes = {};
		List cabecera = getReportePERListaControlCliente(criteria);
		if (cabecera.size() > 0) {
			try {

				bytes = JasperRunManager.runReportToPdf(
						getReport("/controlporCliente.jasper"),
						getParametersReporteControlCliente(criteria.get(
								"fechaDesde").toString(), criteria.get(
								"fechaHasta").toString(), criteria.get(
								"descripcionPais").toString()),
						getDataSource(cabecera));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public byte[] getBytesReporteLibroPercepciones(Map criteria,
			Usuario usuario, Pais pais) {
		byte[] bytes = {};
		List cabecera = getReportePERLibroPercepciones(criteria);
		if (cabecera.size() > 0) {
			try {

				bytes = JasperRunManager.runReportToPdf(
						getReport("/libroPercepciones.jasper"),
						getParametersReporteLibroPercepciones(criteria.get(
								"fechaDesde").toString(), criteria.get(
								"fechaHasta").toString(), criteria.get(
								"descripcionPais").toString()),
						getDataSource(cabecera));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	private Map getParametersReporteControlCliente(String fechaInicio,
			String fechaFinal, String pais) throws Exception {
		Map parameters = new HashMap();
		parameters.put("fechaInicio", fechaInicio);
		parameters.put("fechaFinal", fechaFinal);
		parameters.put("pais", pais);
		return parameters;
	}

	public List getInterfazMYEPercepcionesVentaDirectaCabecera(Map params) {
		log
				.error("Entering method 'getInterfazMYEPercepcionesVentaDirectaCabecera'");
		List datos = this.interfazSiCCDAO
				.getInterfazMYEPercepcionesVentaDirectaCabecera(params);
		return datos;
	}

	public List getInterfazMYEPercepcionesVentaDirectaDetalle(Map params) {
		log
				.error("Entering method 'getInterfazMYEPercepcionesVentaDirectaDetalle'");
		List datos = this.interfazSiCCDAO
				.getInterfazMYEPercepcionesVentaDirectaDetalle(params);
		return datos;
	}

	public List getInterfazMYECodigoAutorizacionSunat(Map params) {
		log.error("Entering method 'getInterfazMYECodigoAutorizacionSunat'");
		List datos = this.interfazSiCCDAO
				.getInterfazMYECodigoAutorizacionSunat(params);
		return datos;
	}

	private Map getParametersReporteLibroPercepciones(String fechaInicio,
			String fechaFinal, String pais) throws Exception {
		Map parameters = new HashMap();
		parameters.put("fechaInicio", fechaInicio);
		parameters.put("fechaFinal", fechaFinal);
		parameters.put("pais", pais);
		return parameters;
	}

	public List getConsultorasByCriteria(Map criteria) {
		return interfazSiCCDAO.getConsultorasByCriteria(criteria);
	}

	public List getProductosByCriteria(Map criteria) {
		return interfazSiCCDAO.getProductosByCriteria(criteria);
	}

	public List getTipoDocumentosByCodigoISO(Map criteria) {
		return this.interfazSiCCDAO.getTipoDocumentosByCodigoISO(criteria);
	}

	public List getTipoComprobantesPago() {
		return this.interfazSiCCDAO.getTipoComprobantesPago();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazOCRConsolidadoOCSCabecera(java.util.Map)
	 */
	public List getInterfazOCRConsolidadoOCSCabecera(Map criteria) {
		log.debug("Entering method 'getInterfazOCRConsolidadoOCSCabecera'");
		return interfazSiCCDAO.getInterfazOCRConsolidadoOCSCabecera(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazOCRConsolidadoOCSDetalle(java.util.Map)
	 */
	public List getInterfazOCRConsolidadoOCSDetalle(Map criteria) {
		log.debug("Entering method 'getInterfazOCRConsolidadoOCSDetalle'");
		return interfazSiCCDAO.getInterfazOCRConsolidadoOCSDetalle(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getInterfazRECProductosReclamadosSubAccesos(java.util.Map)
	 */
	public List getInterfazRECProductosReclamadosSubAccesos(Map criteria) {
		log
				.debug("Entering method 'getInterfazRECProductosReclamadosSubAccesos'");
		return interfazSiCCDAO
				.getInterfazRECProductosReclamadosSubAccesos(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTiposIngresoByCodigoISO(java.util.Map)
	 */
	public List getTiposIngresoByCodigoISO(Map criteria) {
		log.debug("Entering method 'getTiposIngresoByCodigoISO'");
		return interfazSiCCDAO.getTiposIngresoByCodigoISO(criteria);
	}

	public List getCuentasCorrientesByPaisBancoSociedad(Map criteria) {
		return interfazSiCCDAO
				.getCuentasCorrientesByPaisBancoSociedad(criteria);
	}

	public List getBancosByPais(Map criteria) {
		return interfazSiCCDAO.getBancosByPais(criteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getOperacionesByCodigoPais(java.util.Map)
	 */
	public List getOperacionesByCodigoPais(Map criteria) {
		log.debug("Entering method 'getOperacionesByCodigoPais'");
		return interfazSiCCDAO.getOperacionesByCodigoPais(criteria);
	}

	public List getTiposRecepcionMICAyOCS() {
		log.debug("Entering method 'getTiposRecepcionMICAyOCS'");
		return interfazSiCCDAO.getTiposRecepcionMICAyOCS();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getRECProductosList(java.util.Map)
	 */
	public List getRECProductosList(Map criteria) {
		log.debug("Entering method 'getOperacionesByCodigoPais'");
		return interfazSiCCDAO.getRECProductosList(criteria);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getConsultaRECProductosList(java.util.Map)
	 */
	public List getConsultaRECProductosList(Map criteria) {
		log.debug("Entering method 'getOperacionesByCodigoPais'");
		return interfazSiCCDAO.getConsultaRECProductosList(criteria);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getRECProductosListxLote(java.util.Map)
	 */
	public List getRECProductosListxLote(Map criteria) {
		log.debug("Entering method 'getOperacionesByCodigoPais'");
		return interfazSiCCDAO.getRECProductosListxLote(criteria);

	}

	public List getTipoOrigenDatos() {
		return interfazSiCCDAO.getTipoOrigenDatos();
	}

	public List getTipoOrigenDatos2(Map criteria) {
		return interfazSiCCDAO.getTipoOrigenDatos2(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getMarcas()
	 */
	public List getTiposCierres() {
		return this.interfazSiCCDAO.getTiposCierres();
	}

	public List getGrupoProceso() {
		return this.interfazSiCCDAO.getGrupoProceso();
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getConcursosByPaisMarcaCanalPeriodo(java.util.Map)
	 */
	public List getConcursosByPaisMarcaCanalPeriodo(Map criteria) {
		return this.interfazSiCCDAO
				.getConcursosByPaisMarcaCanalPeriodo(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTiposClasificacionesByCriteria(java.util.Map)
	 */
	public List getTiposClasificacionesByCriteria(Map criteria) {
		return this.interfazSiCCDAO.getTiposClasificacionesByCriteria(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getClasificacionesByCriteria(java.util.Map)
	 */
	public List getClasificacionesByCriteria(Map criteria) {
		return this.interfazSiCCDAO.getClasificacionesByCriteria(criteria);
	}

	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTiposVinculosByPais(java.util.Map)
	 */
	public List getTiposVinculosByPais(String codigoPais) {
		return this.interfazSiCCDAO.getTiposVinculosByPais(codigoPais);
	}

	public List getReportePERLiquidacionCobranza(Map criteria) {
		return interfazSiCCDAO.getReportePERLiquidacionCobranza(criteria);
	}

	private Map getParametersReportePERLiquidacionCobranza(Map criteria)
			throws Exception {
		Map parameters = new HashMap();
		parameters.put("sociedad", criteria.get("descripcionSociedad")
				.toString());
		parameters.put("pais", criteria.get("descripcionPais").toString());
		parameters.put("banco", criteria.get("descripcionBanco").toString());
		parameters.put("cuentaCorriente", criteria.get(
				"descripcionCuentaCorriente").toString());
		parameters.put("fechaLiquidacion", criteria.get("fechaLiquidacion")
				.toString());
		parameters.put("comprobante", criteria.get("descripcionComprobante")
				.toString());
		log.debug(parameters.toString());
		return parameters;
	}

	public byte[] getBytesReportePERLiquidacionCobranza(Map criteria) {
		byte[] bytes = {};
		List cabecera = getReportePERLiquidacionCobranza(criteria);
		if (cabecera.size() > 0) {
			try {

				bytes = JasperRunManager.runReportToPdf(
						getReport("/liquidacionCobranza.jasper"),
						getParametersReportePERLiquidacionCobranza(criteria),
						getDataSource(cabecera));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public List getComprobantesByPaisBancoCuentaCorriente(Map criteria) {
		return interfazSiCCDAO
				.getComprobantesByPaisBancoCuentaCorriente(criteria);
	}

	private Map getParametersBaseRecuperacionCampanhasPais(Map params,
			List cabecera) throws Exception {
		Map criteria = params;

		ClassPathResource basePais = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "baseRecuperacionBasicoPais.jasper", getClass());
		JasperReport basePaisSubreport = (JasperReport) JRLoader
				.loadObject(basePais.getInputStream());
		criteria.put("paisBasicoSubreport", basePaisSubreport);
		criteria.put("paisBasicoDataSubreport", new JRMapCollectionDataSource(
				cabecera));

		ClassPathResource tipoPais = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "baseRecuperacionTiposPais.jasper", getClass());
		JasperReport tipoPaisSubreport = (JasperReport) JRLoader
				.loadObject(tipoPais.getInputStream());
		criteria.put("paisTipoSubreport", tipoPaisSubreport);
		criteria.put("paisTipoDataSubreport", new JRMapCollectionDataSource(
				cabecera));

		return criteria;
	}

	private Map getParametersBaseRecuperacionCampanhasRegion(Map params,
			List cabecera) throws Exception {
		Map criteria = params;

		ClassPathResource baseRegion = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "baseRecuperacionBasicoRegion.jasper", getClass());
		JasperReport baseRegionSubreport = (JasperReport) JRLoader
				.loadObject(baseRegion.getInputStream());
		criteria.put("regionBasicoSubreport", baseRegionSubreport);
		criteria.put("regionBasicoDataSubreport",
				new JRMapCollectionDataSource(cabecera));

		ClassPathResource tipoRegion = new ClassPathResource(
				Constants.JASPER_DIRECTORIO +"baseRecuperacionTiposRegion.jasper", getClass());
		JasperReport tipoRegionSubreport = (JasperReport) JRLoader
				.loadObject(tipoRegion.getInputStream());
		criteria.put("regionTipoSubreport", tipoRegionSubreport);
		criteria.put("regionTipoDataSubreport", new JRMapCollectionDataSource(
				cabecera));

		return criteria;
	}

	private Map getParametersBaseRecuperacionCampanhasZona(Map params,
			List cabecera) throws Exception {
		Map criteria = params;

		ClassPathResource baseZona = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "baseRecuperacionBasicoZona.jasper", getClass());
		JasperReport baseZonaSubreport = (JasperReport) JRLoader
				.loadObject(baseZona.getInputStream());
		criteria.put("zonaBasicoSubreport", baseZonaSubreport);
		criteria.put("zonaBasicoDataSubreport", new JRMapCollectionDataSource(
				cabecera));

		ClassPathResource tipoZona = new ClassPathResource(
				Constants.JASPER_DIRECTORIO + "baseRecuperacionTiposZona.jasper", getClass());
		JasperReport tipoZonaSubreport = (JasperReport) JRLoader
				.loadObject(tipoZona.getInputStream());
		criteria.put("zonaTipoSubreport", tipoZonaSubreport);
		criteria.put("zonaTipoDataSubreport", new JRMapCollectionDataSource(
				cabecera));

		return criteria;
	}

	public byte[] getBytesReportePERBaseRecuperacionCampanhasPais(Map params) {
		byte[] bytes = {};

		HashMap map = new HashMap();
		map.put("SYSDATE", new java.util.Date());
		List resultado = new ArrayList();
		resultado.add(map);
		List cabecera = getReporteBaseRecuperacionCampanhasPais(params);
		if (cabecera.size() > 0) {
			try {
				bytes = JasperRunManager.runReportToPdf(
						getReport("/recuperacionCampanhasPais.jasper"),
						getParametersBaseRecuperacionCampanhasPais(params,
								cabecera), getDataSource(resultado));
			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public byte[] getBytesReportePERBaseRecuperacionCampanhasRegion(Map params) {
		byte[] bytes = {};

		HashMap map = new HashMap();
		map.put("SYSDATE", new java.util.Date());
		List resultado = new ArrayList();
		resultado.add(map);
		List cabecera = getReporteBaseRecuperacionCampanhasRegion(params);
		if (cabecera.size() > 0) {
			try {

				bytes = JasperRunManager.runReportToPdf(
						getReport("/recuperacionCampanhasRegion.jasper"),
						getParametersBaseRecuperacionCampanhasRegion(params,
								cabecera), getDataSource(resultado));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public byte[] getBytesReportePERBaseRecuperacionCampanhasZona(Map params) {
		byte[] bytes = {};

		HashMap map = new HashMap();
		map.put("SYSDATE", new java.util.Date());
		List resultado = new ArrayList();
		resultado.add(map);
		List cabecera = getReporteBaseRecuperacionCampanhasZona(params);
		if (cabecera.size() > 0) {
			try {

				bytes = JasperRunManager.runReportToPdf(
						getReport("/recuperacionCampanhasZona.jasper"),
						getParametersBaseRecuperacionCampanhasZona(params,
								cabecera), getDataSource(resultado));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public List getReporteBaseRecuperacionCampanhasPais(Map criteria) {
		return interfazSiCCDAO
				.getReporteBaseRecuperacionCampanhasPais(criteria);
	}

	public List getReporteBaseRecuperacionCampanhasRegion(Map criteria) {
		return interfazSiCCDAO
				.getReporteBaseRecuperacionCampanhasRegion(criteria);
	}

	public List getReporteBaseRecuperacionCampanhasZona(Map criteria) {
		return interfazSiCCDAO
				.getReporteBaseRecuperacionCampanhasZona(criteria);
	}

	public List getConcursos(Map criteria) {
		return interfazSiCCDAO.getConcursos(criteria);
	}

	public List getPlantillasConcursos() {
		return interfazSiCCDAO.getPlantillasConcursos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getPeriodoDefaultByPaisCanal(java.lang.String,
	 *      java.lang.String)
	 */
	public String getPeriodoDefaultByPaisCanal(String codigoPais,
			String codigoCanal) {
		String result = "";
		try {
			Map params = new HashMap();
			params.put("codigoPais", codigoPais);
			params.put("codigoCanal", codigoCanal);

			result = (String) interfazSiCCDAO
					.getPeriodoDefaultByPaisCanal(params);
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		return result;
	}

	public List getReportePERFacturasPendientesSeccion(Map criteria) {
		return interfazSiCCDAO.getReportePERFacturasPendientesSeccion(criteria);
	}

	private Map getParametersReportePERFacturasPendientesSeccion(Map criteria)
			throws Exception {
		Map parameters = new HashMap();
		parameters.put("pais", criteria.get("descripcionPais").toString());
		parameters.put("marca", criteria.get("descripcionMarca").toString());
		parameters.put("canal", criteria.get("descripcionCanal").toString());
		parameters.put("periodo", criteria.get("codigoPeriodo").toString());
		parameters.put("region", criteria.get("descripcionRegion").toString());
		parameters.put("zona", criteria.get("descripcionZona").toString());
		parameters
				.put("seccion", criteria.get("descripcionSeccion").toString());
		parameters.put("territorio", criteria.get("descripcionTerritorio")
				.toString());
		if (log.isDebugEnabled()) {
			log.debug(parameters.toString());
			log.debug("Mostrando los Reportes Elegidos"
					+ criteria.get("presentacion"));
		}
		String[] presentacion = (String[]) criteria.get("presentacion");
		for (int i = 0; i < presentacion.length; i++) {
			switch (Integer.parseInt(presentacion[i])) {
			case 1:
				List detalles = interfazSiCCDAO
						.getReportePERFacturasPendientesSeccionDetalle(criteria);
				ClassPathResource detallesFacturas = new ClassPathResource(
						Constants.JASPER_DIRECTORIO + "facturasPendientesDetalles.jasper", getClass());
				JasperReport detallesFacturasSubreport = (JasperReport) JRLoader
						.loadObject(detallesFacturas.getInputStream());
				criteria.put("detallesFacturasSubreport",
						detallesFacturasSubreport);
				criteria.put("detallesFacturasDataSubreport",
						new JRMapCollectionDataSource(detalles));
				break;
			case 2:
				List resumen = interfazSiCCDAO
						.getReportePERFacturasPendientesSeccionResumen(criteria);
				ClassPathResource resumenFacturas = new ClassPathResource(
						Constants.JASPER_DIRECTORIO + "facturasPendientesResumen.jasper", getClass());
				JasperReport resumenFacturasSubreport = (JasperReport) JRLoader
						.loadObject(resumenFacturas.getInputStream());
				criteria.put("resumenFacturasSubreport",
						resumenFacturasSubreport);
				criteria.put("resumenFacturasDataSubreport",
						new JRMapCollectionDataSource(resumen));
				break;
			case 3:
				List pedidosZona = interfazSiCCDAO
						.getReportePERFacturasPendientesSeccionPedidosZona(criteria);
				ClassPathResource pedidosZonaFacturas = new ClassPathResource(
						Constants.JASPER_DIRECTORIO + "facturasPendientesPedidosZona.jasper", getClass());
				JasperReport pedidosZonaFacturasSubreport = (JasperReport) JRLoader
						.loadObject(pedidosZonaFacturas.getInputStream());
				criteria.put("pedidosZonaFacturasSubreport",
						pedidosZonaFacturasSubreport);
				criteria.put("pedidosZonaFacturasDataSubreport",
						new JRMapCollectionDataSource(pedidosZona));
				break;
			case 4:
				List pedidosRegion = interfazSiCCDAO
						.getReportePERFacturasPendientesSeccionPedidosRegion(criteria);
				ClassPathResource pedidosRegionFacturas = new ClassPathResource(
						Constants.JASPER_DIRECTORIO + "facturasPendientesPedidosRegion.jasper", getClass());
				JasperReport pedidosRegionFacturasSubreport = (JasperReport) JRLoader
						.loadObject(pedidosRegionFacturas.getInputStream());
				criteria.put("pedidosRegionFacturasSubreport",
						pedidosRegionFacturasSubreport);
				criteria.put("pedidosRegionFacturasDataSubreport",
						new JRMapCollectionDataSource(pedidosRegion));
				break;
			default:
				break;
			}
		}

		return parameters;
	}

	public byte[] getBytesReportePERFacturasPendientesSeccion(Map criteria) {
		byte[] bytes = {};
		List resultado = interfazSiCCDAO
				.getReportePERFacturasPendientesSeccion(criteria);
		if (resultado.size() > 0) {
			try {

				bytes = JasperRunManager
						.runReportToPdf(
								getReport("/facturasPendientesSeccion.jasper"),
								getParametersReportePERFacturasPendientesSeccion(criteria),
								getDataSource(resultado));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	public List getTipoOrigenDatosRecaudosBancarios() {
		return interfazSiCCDAO.getTipoOrigenDatosRecaudosBancarios();
	}

	private Map getParametersReporteEVIMicaRecepcionPedidosZona(Map criteria)
			throws Exception {
		Map parameters = new HashMap();
		parameters.put("pais", criteria.get("descripcionPais").toString());
		parameters.put("periodo", criteria.get("codigoPeriodo").toString());
		log.debug(parameters.toString());
		return parameters;
	}

	public List getReporteEVIMicaRecepcionPedidosZona(Map criteria) {
		return interfazSiCCDAO.getReporteEVIMicaRecepcionPedidosZona(criteria);
	}

	public byte[] getBytesReporteEVIMicaRecepcionPedidosZona(Map criteria) {
		byte[] bytes = {};
		List cabecera = getReporteEVIMicaRecepcionPedidosZona(criteria);
		if (cabecera.size() > 0) {
			try {

				bytes = JasperRunManager
						.runReportToPdf(
								getReport("/micaRecepcionPedidosZona.jasper"),
								getParametersReporteEVIMicaRecepcionPedidosZona(criteria),
								getDataSource(cabecera));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}

		return bytes;
	}

	private Map getParametersReporteEVIMicaRecepcionPedidosRegion(Map criteria)
			throws Exception {
		Map parameters = new HashMap();
		parameters.put("pais", criteria.get("descripcionPais").toString());
		parameters.put("periodo", criteria.get("codigoPeriodo").toString());
		log.debug(parameters.toString());
		return parameters;
	}

	public List getReporteEVIMicaRecepcionPedidosRegion(Map criteria) {
		return interfazSiCCDAO
				.getReporteEVIMicaRecepcionPedidosRegion(criteria);
	}

	public byte[] getBytesReporteEVIMicaRecepcionPedidosRegion(Map criteria) {
		byte[] bytes = {};
		List cabecera = getReporteEVIMicaRecepcionPedidosRegion(criteria);
		if (cabecera.size() > 0) {
			try {

				bytes = JasperRunManager
						.runReportToPdf(
								getReport("/micaRecepcionPedidosRegion.jasper"),
								getParametersReporteEVIMicaRecepcionPedidosRegion(criteria),
								getDataSource(cabecera));

			} catch (Exception e) {
				log.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
		}
		return bytes;
	}

	public List getSeccionesByPaisMarcaCanalRegionZona(Map params) {
		return null;
	}

	public List getZonaByPaisZona(Map params) {
		return interfazSiCCDAO.getZonaByPaisZona(params);
	}

	public List getComision() {
		return interfazSiCCDAO.getComision();
	}

	public String getNumeroLoteIntHistoLotes(Map params, String interFaz) {
		return interfazSiCCDAO.getNumeroLoteIntHistoLotes(params, interFaz);
	}

	public List getTiposClientesByCodigoISOOID(String codigoISO) {
		return interfazSiCCDAO.getTiposClientesByCodigoISOOID(codigoISO);
	}	

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#updateInterfazREUIndicadorTransferenciaClientes()
     */
    public void updateInterfazREUIndicadorTransferenciaClientes() {
        interfazSiCCDAO.updateInterfazREUIndicadorTransferenciaClientes();
    }
    
	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCanalesByCodigoISO(java.lang.String)
	 */
	public List getTiposSolicitudPais(String codigoISO) {
		return this.interfazSiCCDAO.getTiposSolicitudPais(codigoISO);
	}
	/*
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#truncateTablaControlIVR()
	 */
	public void deleteTablaControlIVR() {
		 interfazSiCCDAO.deleteTablaControlIVR();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#executeGenerarBoletasRecojo(java.util.Map)
	 */
	public void executeGenerarBoletasRecojo(Map params) {
		interfazSiCCDAO.executeGenerarBoletasRecojo(params);
		
	}

	public void executeGenerarXMLBoletasRecojoORA(Map params) {
		interfazSiCCDAO.executeGenerarXMLBoletasRecojoORA(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#executeGenerarXMLBoletasRecojo(java.util.Map)
	 */
	public void executeGenerarXMLBoletasRecojo(Map params) {
		
		int totalPaginas;
		interfazSiCCDAO.executeTruncateGenerarXMLBoletasRecojo();
		List boletaRecojoCabeceraList = interfazSiCCDAO.getBoletaRecojoCabeceraList(params);
		log.debug("boletaRecojoCabeceraList ");
		
		String numeroDetallesBoletaRecojo = procesoImpresionDAO.getValorParametroImpresion(Constants.PROCESO_IMPRESION_LASER,"numeroDetallesBoletaRecojo");
		log.debug("numeroDetallesBoletaRecojo " + numeroDetallesBoletaRecojo);		 
		int  sizePaginacion = Integer.parseInt(numeroDetallesBoletaRecojo);
		
		log.debug("sizePaginacion " + sizePaginacion);		
		int actualDireccionBR = interfazSiCCDAO.getActualDireccionBR(params);
		
		log.debug("actualDireccionBR " + actualDireccionBR);		
		log.debug("- Size Paginacion  : " + sizePaginacion);
		
		//Obteniendo el dato de Envio de BR
		Map criteria = new HashMap();
		criteria.put("codigoPais", (String)params.get("codigoPais"));
		criteria.put("codigoParametro", Constants.STO_DATO_ENVIA_BR);
		String datoEnvioBR = this.interfazOCRDAO.getSTOParametroGeneralOCR(criteria);
		if (StringUtils.isBlank(datoEnvioBR)) {
			datoEnvioBR = Constants.STO_DATO_ENVIA_BR_DEFAULT;
		}
		params.put("datoEnvioBR", datoEnvioBR);
		
		
        Iterator i = boletaRecojoCabeceraList.iterator();
       try{ 
	        while (i.hasNext()) {
	        	
	        	Map model = new HashMap();
	            Map boletaRecojoClientes = (Map) i.next();
	            
	            String codigoCabeceraBoletaRecojo = MapUtils.getString(boletaRecojoClientes,"codigoCabeceraBoletaRecojo");
	            params.put("rowDesde",null);
	            params.put("rowHasta",null);
	            params.put("codigoCabeceraBoletaRecojo", codigoCabeceraBoletaRecojo);
			            List boletaRecojoLineaList = interfazSiCCDAO.getBoletaRecojoLineaList(params);
	            if(boletaRecojoLineaList.size() % sizePaginacion!=0){
	    			totalPaginas=boletaRecojoLineaList.size() / sizePaginacion+1;
	    		}else{
	    			totalPaginas=boletaRecojoLineaList.size() / sizePaginacion;
	    		}
	            log.debug("- Total de paginas : " +totalPaginas);
	            
	        	boolean existenPaginas=true;
	        	boolean existeCabecera=false;

	            int rowDesde= 1;
	            int rowHasta=sizePaginacion;
	            int pagina=1;
	        	
	            model.put("totalPaginas", new Integer(totalPaginas));
	            model.put("pagina", new Integer(pagina));
	            
	            params.put("rowDesde", new Integer(rowDesde));
	            params.put("rowHasta", new Integer(rowHasta));
	            
	            log.debug("- Codigo : " + codigoCabeceraBoletaRecojo);
	            while(existenPaginas){
	            		
			            boletaRecojoLineaList = interfazSiCCDAO.getBoletaRecojoLineaList(params);
			            log.debug("- Rows recuperados : " + boletaRecojoLineaList.size());
			            if(!existeCabecera){
			            	 Iterator x = boletaRecojoLineaList.iterator();
			            	 Map boletaRecojoCabecera = (Map) x.next();
			            	 model.put("boletaRecojoCabecera", boletaRecojoCabecera);
			            	 existeCabecera=true;
			            }
			            
			            if(boletaRecojoLineaList.size()>0){
			            	model.put("boletaRecojoLineaList",boletaRecojoLineaList);
				            String archivoXML = ""; 
				            if (actualDireccionBR == 1 ) {
				            	archivoXML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"generarBoletasRecojoBR.vm", model);
							}else {
								archivoXML = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,"generarBoletasRecojo.vm", model);
							}
				            String codigoCliente = MapUtils.getString(boletaRecojoClientes,"codCliente");
				            params.put("cod_clie",codigoCliente);
				            params.put("archivoXML", archivoXML);
				            params.put("pagina", new Integer(pagina));
				            interfazSiCCDAO.saveXMLBoletasRecojo(params);
				            
				            pagina++;
				            model.put("pagina", new Integer(pagina));
				            
				            rowDesde+=sizePaginacion;
				            rowHasta+=sizePaginacion;
				            
				            params.put("rowDesde", new Integer(rowDesde));
				            params.put("rowHasta",new Integer(rowHasta));
			            }else{
			            	existenPaginas=false;
			            	
			            }
			            
	            }
	        }
	        interfazSiCCDAO.executeGenerarXMLBoletasRecojo(params);
	 
       }catch(Exception e) {
        log.error(e.getMessage());
        throw new ServiceException(e.getMessage());
       }
       
       
       
	}

	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	public String executeExportarMatrizLBEL(Map params) {
		return this.interfazSiCCDAO.executeExportarMatrizLBEL(params);	
	}

	public void executeCambioCodigoVenta(Map params) {
		this.interfazSiCCDAO.executeCambioCodigoVenta(params);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getNumLotes(java.util.Map)
	 */
	public List getNumeroLoteByFact(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getNumeroLoteByFact(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCentroDByPaisMarcaCanal(java.util.Map)
	 */
	public List getCentroDistribucionByPais(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getCentroDistribucionByPais(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCentroDByPaisMarcaCanal(java.util.Map)
	 */
	public List getTipoSol(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getTipoSol(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCentroDByPaisMarcaCanal(java.util.Map)
	 */
	public List getEstadosPedidos() {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getEstadosPedidos();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getCentroDByPaisMarcaCanal(java.util.Map)
	 */
	public List getRegion() {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getRegion();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getLinea(java.util.Map)
	 */
	public List getLinea(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getLinea(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getMapaByCentro(java.util.Map)
	 */
	public List getMapaByCentro(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getMapaByCentro(params);
	}

/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getMapaByCentro(java.util.Map)
	 */
	public List getVersionByMapa(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getVersionByMapa(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getMapaByCentro(java.util.Map)
	 */
	public List getVersionByMapaAnt(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getVersionByMapaAnt(params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getDescripcionByProducto(java.util.Map)
	 */
	public List getDescripcionByProducto(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getDescripcionByProducto(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getZonasByPais(java.util.Map)
	 */
	public List getZonasByPais(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getZonasByPais(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getAccesoByCanal(java.util.Map)
	 */
	public List getAccesoByCanal(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getAccesoByCanal(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSubaccesoByAcceso(java.util.Map)
	 */
	public List getSubaccesoByAcceso(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getSubaccesoByAcceso(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getFacturacion(java.util.Map)
	 */
	public List getFacturacion(Map params) {
		// TODO Auto-generated method stub
		return this.interfazSiCCDAO.getFacturacion(params);
	}
	
	public List getRECConsolidadoUnidadesAlmacenVirtual(Map params) {
		 
		return interfazSiCCDAO.getRECConsolidadoUnidadesAlmacenVirtual(params);
	}

	public List getSublineaxLinea(Map criteria) {
		
		return this.interfazSiCCDAO.getSublineaByLinea(criteria);
	}

	
	public List getConsolidadoTransferenciaBoletasRecojoSinAnulacion(Map criteria) {
	   return this.interfazSiCCDAO.getConsolidadoTransferenciaBoletasRecojoSinAnulacion(criteria);
	}

	
	public List getConsolidadoTransferenciaBoletasRecojoConAnulacion(Map criteria) {
		return this.interfazSiCCDAO.getConsolidadoTransferenciaBoletasRecojoConAnulacion(criteria);
	}
	
	public List getInterfazSATEnviarBoletaDespacho(Map criteria) {
		return this.interfazSiCCDAO.getInterfazSATEnviarBoletaDespachoA (criteria);
	}

	
	public void setInterfazSATRecepcionarEstimadoProductos(EstimadoProductos estimados, Usuario usuario, Map lista) {
		
		this.interfazSiCCDAO.insertSATEstimadoProductos(estimados,usuario, lista);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getListCodComision(java.lang.String)
	 */
	public List getListCodComision(String codigo) {
		return this.interfazSiCCDAO.getListCodComision(codigo);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getEstadosBoletasRecojo(java.lang.String)
	 */
	public List getEstadosBoletasRecojo(String codigoPais) {
		return this.interfazSiCCDAO.getEstadosBoletasRecojo(codigoPais);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getResultadosBoletasRecojo(java.lang.String)
	 */
	public List getResultadosBoletasRecojo(String codigoPais) {

		return this.interfazSiCCDAO.getResultadosBoletasRecojo(codigoPais);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getMotivosNoRecojoBoletasRecojo(java.lang.String)
	 */
	public List getMotivosNoRecojoBoletasRecojo(String codigoPais) {
		return this.interfazSiCCDAO.getMotivosNoRecojoBoletasRecojo(codigoPais);
	}
	
//	public void setInterfazSATRecepcionarEstimadoProductos(EstimadoProductos estimados, Usuario usuario) {
//		
//		this.interfazSiCCDAO.insertSATEstimadoProductos(estimados,usuario);
//	}

//	/* (non-Javadoc)
//	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#setInterfazSATRecepcionarEstimadoProductos(biz.belcorp.ssicc.sisicc.model.EstimadoProductos, biz.belcorp.ssicc.model.Usuario)
//	 */
//	public void setInterfazSATRecepcionarEstimadoProductos(EstimadoProductos estimados, Usuario usuario) {
//		// TODO Auto-generated method stub
//		
//	}
	

	public String  getCodigoDocumento(Map params){
		return this.interfazSiCCDAO.getCodigoDocumento(params);
	}
	
	public String  getNumLoteSTO(Map params){
		return this.interfazSiCCDAO.getNumLoteSTO(params);
	}
	
	
		public EstimadoProductos getListaEstimados(Map params){
		EstimadoProductos est = new EstimadoProductos();
		est.setProcedencia(1);
		return this.interfazSiCCDAO.getListaEstimadosProducto(params, est);
	}


		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#setInterfazSATRecepcionarEstimadoProductos(biz.belcorp.ssicc.sisicc.model.EstimadoProductos, biz.belcorp.ssicc.model.Usuario)
		 */
		public void setInterfazSATRecepcionarEstimadoProductos(
				EstimadoProductos estimados, Usuario usuario) {
			// TODO Auto-generated method stub
			
		}

		

		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getVersionByP(java.util.Map)
		 */
		public List getVersionSinP(Map params) {
			// TODO Auto-generated method stub
			return this.interfazSiCCDAO.getVersionSinP(params);
		}

	
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getProcesoBatchByInterfaz(java.util.Map)
		 */
		public List getProcesoBatchByInterfaz(Map criteria) {
			return interfazSiCCDAO.getProcesoBatchByInterfaz(criteria);
		}

		public void updateInterfazProcesoBatch(Map queryParams) {
			interfazSiCCDAO.updateInterfazProcesoBatch(queryParams);
		}
		
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTiposInterfazSMS()
		 */
		public List getTiposInterfazSMS() {
			return interfazSiCCDAO.getTiposInterfazSMS();
		}
		
		/* (non-Javadoc)
		 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getOidString(java.lang.String, java.util.Map)
		 */
		public String getOidString(String string, Map mapQueryParams){
			return interfazSiCCDAO.getOidString(string, mapQueryParams);
		}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getPeriodoFechaProcesoActual(java.util.Map)
	 */
	public List getPeriodoFechaProcesoActual(Map criteria) {
		log.info("Entro a InterfazSiCCServiceImpl - getPeriodoFechaProcesoActual(java.util.Map)");
		List lista = interfazSiCCDAO.getPeriodoFechaProcesoActual(criteria);
		log.info("Salio a InterfazSiCCServiceImpl - getPeriodoFechaProcesoActual(java.util.Map) - Resultado:"+lista.size());
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getPeriodoFechaProceso(java.util.Map)
	 */
	public List getPeriodoFechaProceso(Map criteria) {
		List lista = interfazSiCCDAO.getPeriodoFechaProceso(criteria);
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getListaProcesosIncentivos(java.util.Map)
	 */
	public List getListaProcesosIncentivos(Map criteria){
		return interfazSiCCDAO.getListaProcesosIncentivos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getListaProcesosCierreFacturacion(java.util.Map)
	 */
	public List getListaProcesosCierreFacturacion(Map criteria) {
		return interfazSiCCDAO.getListaProcesosCierreFacturacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getListaProcesosLet(java.util.Map)
	 */
	public List getListaProcesosLet(Map criteria) {
		return interfazSiCCDAO.getListaProcesosLet(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getSociedadEquivalenciaSAP(java.util.Map)
	 */
	public String getSociedadEquivalenciaSAP(Map params){
		return interfazSiCCDAO.getSociedadEquivalenciaSAP(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getListaProcesosGenCierreCampania(java.util.Map)
	 */
	public List getListaProcesosGenCierreCampania(Map criteria) {
		return interfazSiCCDAO.getListaProcesosGenCierreCampania(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getContCierreCampaByCodigoPeriodo(java.lang.String)
	 */
	public Integer getContCierreCampaByCodigoPeriodo(String codigoPeriodo) {
		return interfazSiCCDAO.getContCierreCampaByCodigoPeriodo(codigoPeriodo);
	}
	
	public List getListaAlmacen(Map criteria){
		return interfazSiCCDAO.getListaAlmacen(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTiposRecepcionXRXBoletaVenta()
	 */
	public List getTiposRecepcionXRX(String codigoInterfaz1, String codigoInterfaz2) {
		return interfazSiCCDAO.getTiposRecepcionXRX(codigoInterfaz1, codigoInterfaz2);
	}
	
	/* INI JJ  PER-SiCC-2012-0388 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getContCierreZonaByCodigoPeriodoOidZona(java.lang.String, java.lang.Integer)
	 */
	public Integer getContCierreZonaByCodigoPeriodoOidZona(String codigoPeriodo, Integer oidZona) {
		return interfazSiCCDAO.getContCierreZonaByCodigoPeriodoOidZona(codigoPeriodo,oidZona);
	}
	/* FIN JJ  PER-SiCC-2012-0388 */
	
	/* INI SA PER-SiCC-2012-1120 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getContCierreRegionByCodigoPeriodoOidRegion(java.lang.String, java.lang.Integer)
	 */
	public Integer getContCierreRegionByCodigoPeriodoOidRegion(String codigoPeriodo, Integer oidRegion) {
		return interfazSiCCDAO.getContCierreRegionByCodigoPeriodoOidRegion(codigoPeriodo, oidRegion);
	}
	/* FIN AS PER-SiCC-2012-1120 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getContRegistrosAsociadosCargaArchivosBolVent(java.util.Map)
	 */
	public Integer getContRegistrosAsociadosCargaArchivosBolVent(Map criteria) {		
		return interfazSiCCDAO.getContRegistrosAsociadosCargaArchivosBolVent(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getContRegistrosAsociadosCargaArchivosNotasCred(java.util.Map)
	 */
	public Integer getContRegistrosAsociadosCargaArchivosNotasCred(Map criteria) {		
		return interfazSiCCDAO.getContRegistrosAsociadosCargaArchivosNotasCred(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getArchivosPendientesBolVent(java.util.Map)
	 */
	public List getArchivosPendientesBolVent(Map criteria) {
		return interfazSiCCDAO.getArchivosPendientesBolVent(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getArchivosPendientesNotasCred(java.util.Map)
	 */
	public List getArchivosPendientesNotasCred(Map criteria) {
		return interfazSiCCDAO.getArchivosPendientesNotasCred(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getFechaFacturacion()
	 */
	public String getFechaFacturacion() {
		return interfazSiCCDAO.getFechaFacturacion();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getListaEstadosIncentivos()
	 */
	public List getListaEstadosIncentivos() {
		return this.interfazSiCCDAO.getListaEstadosIncentivos();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getPeriodoFechaCampanyaActivaSF(java.util.Map)
	 */
	public Map getPeriodoFechaCampanyaActivaSF(Map params) {
		return this.interfazSiCCDAO.getPeriodoFechaCampanyaActivaSF(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getListaAsientos()
	 */
	public List getListaAsientos() {
		return this.interfazSiCCDAO.getListaAsientos();
	}

	public void updateInterfazCCCCargasDeudasWeb(Map params) {
		interfazSiCCDAO.updateInterfazCCCCargasDeudasWeb(params);		
	}

	public List getListaTipoPagoLec() {
		return this.interfazSiCCDAO.getListaPagosLec();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTipoReemplazo()
	 */
	public List getTipoReemplazo() {
		return this.interfazSiCCDAO.getTipoReemplazo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getTipoDocumentosPago()
	 */
	public List getTipoDocumentosPago() {
		return this.interfazSiCCDAO.getTipoDocumentosPago();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getExisteSolicitudesCabecera()
	 */
	public String getExisteSolicitudesCabecera() {
		return this.interfazSiCCDAO.getExisteSolicitudesCabecera();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#getPeriodosDefaultByPMCF(java.util.Map)
	 */
	public List getPeriodosDefaultByPMCF(Map criteria) {
		return this.interfazSiCCDAO.getPeriodosDefaultByPMCF(criteria);
	}	

	public String getDescripcionTipoClienteByCodigoTipoClienteCodigoIdioma(
			Map criteria) {
		return this.interfazSiCCDAO.getDescripcionTipoClienteByCodigoTipoClienteCodigoIdioma(criteria);
	}

	public String getDescripcionSubTipoClienteByCriteria(Map criteria) { 
		return this.interfazSiCCDAO.getDescripcionSubTipoClienteByCriteria(criteria);
	}

	public String getDescripcionTipoClasificacionByCriteria(Map criteria) {
		return this.interfazSiCCDAO.getDescripcionTipoClasificacionByCriteria(criteria);
	}

	public String getClasificacionByCriteria(Map criteria) {
		return this.interfazSiCCDAO.getClasificacionByCriteria(criteria);
	}

	public List getTiposClientesByCodigoISO01(String codigo) {
		return this.interfazSiCCDAO.getTiposClientesByCodigoISO01(codigo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#executeEnviarSMSBoletaPrimerRecojo(java.util.Map)
	 */
	public void executeEnviarSMSBoletaPrimerRecojo(Map params) {
		interfazSiCCDAO.executeEnviarSMSBoletaPrimerRecojo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#executeEnviarSMSBoletaSegundoRecojo(java.util.Map)
	 */
	public void executeEnviarSMSBoletaSegundoRecojo(Map params) {
		interfazSiCCDAO.executeEnviarSMSBoletaSegundoRecojo(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.InterfazSiCCService#executeEnviarSMSBoletaSegundoRecojoNoExitoso(java.util.Map)
	 */
	public void executeEnviarSMSBoletaSegundoRecojoNoExitoso(Map params) {
		interfazSiCCDAO.executeEnviarSMSBoletaSegundoRecojoNoExitoso(params);
	}
	
	/* INI NSSICC */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	public void executeProcesoRECActualizaUnidadesDevueltas02(Map params) {
		interfazSiCCDAO.executeProcesoRECActualizaUnidadesDevueltas(params);				 
		interfazSiCCDAO.deleteInterfazRETProductosReclamados(params);
		
	}
	/* FIN NSSICC */
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.InterfazSiCCService#executeGenerarAtencionesInteligentes(java.util.Map)
	 */
	public void executeGenerarAtencionesInteligentes(Map params) {
		interfazSiCCDAO.executeGenerarAtencionesInteligentes(params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.InterfazSiCCService#executeEliminarAtencionesInteligentes(java.util.Map)
	 */
	public void executeEliminarAtencionesInteligentes(Map params) {
		interfazSiCCDAO.executeEliminarAtencionesInteligentes(params);
		
	}
	
}