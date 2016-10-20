package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ConsultaCCCGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCRecepcionarPagosWebDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.gen.GenericoCCCComercialDAO;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCRecepcionarPagosWebService;


/**
 * Service para le mdulo de Cuenta Corriente
 *  
 * <p>
 * <a href="ProcesoCCCRecepcionarPagosWebServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCRecepcionarPagosWebServiceImpl extends BaseService implements ProcesoCCCRecepcionarPagosWebService {


	private ProcesoCCCRecepcionarPagosWebDAO procesoCCCRecepcionarPagosWebDAO;
	
	private ConsultaCCCGenericoDAO consultaCCCGenericoDAO;
	
	private GenericoService genericoService;	
	
    private Map genericoImplementations;
	protected GenericoCCCComercialDAO genericoImpl;
	
	protected transient final Log log = LogFactory.getLog(getClass());
	
		
		
	/**
	 * Retorna la conexion a comercial
	 * @param codigoPais
	 * @return
	 */
	private ConexionOCRWrapper getDevuelveConexionComercial(String codigoPais) {
		
		
		ConexionOCRWrapper conexion = new ConexionOCRWrapper();
		conexion.setCodigoPais(codigoPais);		
		
		log.debug("Obteniendo los datos de la conexion");
		
		//try {
			String host = consultaCCCGenericoDAO.getParametroPaisbyCodigo(Constants.CCC_HOST_PAGOS_LINEA);
			log.debug(host);
			
			String usuario = consultaCCCGenericoDAO.getParametroPaisbyCodigo(Constants.CCC_USER_PAGOS_LINEA);
			log.debug(usuario);
			
			String password = consultaCCCGenericoDAO.getParametroPaisbyCodigo(Constants.CCC_PWD_PAGOS_LINEA);
			log.debug(password);
			
			String tipoConexion = consultaCCCGenericoDAO.getParametroPaisbyCodigo(Constants.CCC_TIPO_CONEXION_PAGOS_LINEA);
			log.debug(tipoConexion);
			
			conexion.setHost(host);
			conexion.setUsuario(usuario);
			conexion.setPassword(password);				
			conexion.setTipoConexionExterna(tipoConexion);
			
			log.debug("Conexion OK");
			
		//	
		//} catch (Exception e) {
		//	conexion.setHost("jdbc:jtds:sqlserver://PENTSPEIS:1433;DatabaseName=SIP");
		//	conexion.setUsuario("IPMPAR");
		//	conexion.setPassword("Belcorp2011");
		//	conexion.setTipoConexionExterna("SQL");
		//}
														
		return conexion;
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.ProcesoSTOEjecucionValidacionesService#executeRecepcionCuponPago(java.util.Map)
	 */
	public void executeRecepcionarPagosWeb(Map params) throws Exception {
		
		String codigoPais= (String)params.get("codigoPais");
		//obteniendo lista SQL SERVER
		ConexionOCRWrapper conexion = getDevuelveConexionComercial(codigoPais);
		log.debug("conexion comercial "+conexion.getHost() + " usu: " +conexion.getUsuario());
		
		//Obtiene el numero de lote
		Map criteria = new HashMap();
		consultaCCCGenericoDAO.getNumeroLote(criteria);
		params.put("numeroLote", criteria.get("numeroLote"));	
		
		//Obtener el ultimo OID_PAGO Cargado
		Integer ultimoOidPago = procesoCCCRecepcionarPagosWebDAO.getUltimoOidPagoWeb(params);
		
		params.put("ultimoOidPago", ultimoOidPago);	
		
		String tabla = consultaCCCGenericoDAO.getParametroPaisbyCodigo(Constants.CCC_TABLA_PAGOS_LINEA);
		params.put("tabla", tabla);	


		log.debug(params.toString());
		List listPagosWeb = getListPagosWeb(conexion, params);
		log.debug("Lista Pagos Web "+listPagosWeb.size());
		Iterator it = listPagosWeb.iterator();
		boolean hayInsercion=false;
		while(it.hasNext()){
			Map map = (Map)it.next();
						
			String fecha = (String) map.get("fechaPago");//yyyy-mm-dd hh:mi:ss
						
			params.put("oidPago", map.get("oidPago"));			
			params.put("tipoOperacion", map.get("tipoOperacion"));						
			params.put("codigoConsultora", map.get("codigoConsultora"));									
			params.put("fechaPago",fecha.substring(8,10)+"/"+fecha.substring(5, 7)+"/"+fecha.substring(0,4) + " " + fecha.substring(11,13) + ":" + fecha.substring(14,16) + ":" + fecha.substring(17,19) );						
									
			String montoPagoOriginal = map.get("montoPago")!=null?String.valueOf(map.get("montoPago")):"0";
			//log.debug(montoPagoOriginal);
			Integer longitudMonto = montoPagoOriginal.length();
			
			String montoPagoDecimal = montoPagoOriginal.substring(longitudMonto-2,longitudMonto);
			//log.debug(montoPagoDecimal);
			
			String montoPagoEntero = montoPagoOriginal.substring(0,longitudMonto-2);
			//log.debug(montoPagoEntero);
			
			Double montoPago = Double.valueOf(montoPagoEntero + "." + montoPagoDecimal);  						
			//log.debug(montoPago);
						
			params.put("montoPago", montoPago);											
			params.put("codigoBanco", map.get("codigoBanco"));							
			params.put("codigoCanal", map.get("codigoCanal"));
			params.put("codigoOperacion", map.get("codigoOperacion"));						
			params.put("codigoTrace", map.get("codigoTrace"));					
			
			procesoCCCRecepcionarPagosWebDAO.insertPagoWeb(params);
			
		}
		//
		params.remove("rutCliente");
		params.remove("valorPagado");
		params.remove("fechaRegistro");
		params.remove("numeroDocumentoIdentidad");
		
		procesoCCCRecepcionarPagosWebDAO.executeCargaPagosBancoLinea(params);
		
		procesoCCCRecepcionarPagosWebDAO.updateUltimoOidPagoWeb(params);
				
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.ocr.service.gen.GenericoOCRComercialFacadeService#getListProcesoCargaCupon(biz.belcorp.ssicc.ocr.dao.model.ConexionOCRWrapper, java.util.Map)
	 */
	public List getListPagosWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		genericoImpl = (GenericoCCCComercialDAO) getGenericoImplementations(conexionOCRWrapper, params);
		return genericoImpl.getListPagosWeb(conexionOCRWrapper, params);
	}
				
	public ProcesoCCCRecepcionarPagosWebDAO getProcesoCCCRecepcionarPagosWebDAO() {
		return procesoCCCRecepcionarPagosWebDAO;
	}

	public void setProcesoCCCRecepcionarPagosWebDAO(
			ProcesoCCCRecepcionarPagosWebDAO procesoCCCRecepcionarPagosWebDAO) {
		this.procesoCCCRecepcionarPagosWebDAO = procesoCCCRecepcionarPagosWebDAO;
	}
	
	public ConsultaCCCGenericoDAO getConsultaCCCGenericoDAO() {
		return consultaCCCGenericoDAO;
	}

	public void setConsultaCCCGenericoDAO(
			ConsultaCCCGenericoDAO consultaCCCGenericoDAO) {
		this.consultaCCCGenericoDAO = consultaCCCGenericoDAO;
	}
	
	/**
	 * @return the genericoService
	 */
	public GenericoService getGenericoService() {
		return genericoService;
	}	

	/**
	 * @param genericoService the genericoService to set
	 */
	public void setGenericoService(GenericoService genericoService) {
		this.genericoService = genericoService;
	}

	
	/**
	 * @param codigo
	 * @param params
	 * @return
	 */
	public GenericoCCCComercialDAO getGenericoImplementations(ConexionOCRWrapper conexionOCRWrapper, Map params) {
		log.debug("ini getGenericoImplementations");
		
		/* Colocando el pais */
		if(params==null) params = new HashMap();
		params.remove("codigoPais");
		params.put("codigoPais", conexionOCRWrapper.getCodigoPais());
		
		/* obteniendo DAO */
		genericoImpl=(GenericoCCCComercialDAO)genericoImplementations.get(conexionOCRWrapper.getTipoConexionExterna());
		return genericoImpl;
	}
	
	
	public void setGenericoImplementations(Map genericoImplementations) {
		this.genericoImplementations = genericoImplementations;
	}

	
	public Map getGenericoImplementations() {
		return genericoImplementations;
	}

	public GenericoCCCComercialDAO getGenericoImpl() {
		return genericoImpl;
	}

	public void setGenericoImpl(GenericoCCCComercialDAO genericoImpl) {
		this.genericoImpl = genericoImpl;
	}

}
