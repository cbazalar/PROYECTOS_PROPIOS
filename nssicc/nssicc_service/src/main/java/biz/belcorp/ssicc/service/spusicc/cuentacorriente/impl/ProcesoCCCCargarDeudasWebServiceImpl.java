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
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ProcesoCCCCargarDeudasWebDAO;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.gen.GenericoCCCComercialDAO;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarDeudasWebService;


/**
 * Service para le mdulo de Cuenta Corriente
 *  
 * <p>
 * <a href="ProcesoCCCCargarDeudasWebServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">JFA</a>
 * 
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCCargarDeudasWebServiceImpl extends BaseService implements ProcesoCCCCargarDeudasWebService {

	private ProcesoCCCCargarDeudasWebDAO procesoCCCCargarDeudasWebDAO;
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
	public void executeCargarDeudasWeb(Map params) throws Exception {
		
		String codigoPais= (String)params.get("codigoPais");		
		ConexionOCRWrapper conexion = getDevuelveConexionComercial(codigoPais);
		log.debug("conexion comercial "+conexion.getHost() + " usu: " +conexion.getUsuario());
		
		Map criteria = new HashMap();
		procesoCCCCargarDeudasWebDAO.executeGeneraNovedadesDeudasWeb(criteria);
		
		List listInsertDeudasWeb =  procesoCCCCargarDeudasWebDAO.getListInsertDeudasWeb(params);
		log.debug("Lista Insert Deudas Web "+listInsertDeudasWeb.size());
		
		Iterator it1 = listInsertDeudasWeb.iterator();
		int cont1 = 0;
		//boolean hayInsercion=false;
		while(it1.hasNext()){
			Map map = (Map)it1.next();
			
			log.debug("Insercion : " + cont1);
			
			params.put("codigoConsultora", map.get("codigoConsultora"));
			params.put("nombreConsultora", map.get("nombreConsultora"));
			params.put("montoDeuda", map.get("montoDeuda"));			
			
			deleteDeudasWeb(conexion,params);
		insertDeudasWeb(conexion,params);
			
			cont1 = cont1 +1;
		}
		
		List listUpdateDeudasWeb =  procesoCCCCargarDeudasWebDAO.getListUpdateDeudasWeb(params);	
		log.debug("Lista Update Deudas Web "+listUpdateDeudasWeb.size());
		
		Iterator it2 = listUpdateDeudasWeb.iterator();
		int cont2 = 0;
		//boolean hayInsercion=false;
		while(it2.hasNext()){
			Map map = (Map)it2.next();
			
			log.debug("Actualizacion : " + cont2);
			
			params.put("codigoConsultora", map.get("codigoConsultora"));
			params.put("nombreConsultora", map.get("nombreConsultora"));
			params.put("montoDeuda", map.get("montoDeuda"));			
			
		updateDeudasWeb(conexion,params);	
			
			cont2 = cont2 +1;
		}
				
		
		
	}
					
	private void deleteDeudasWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		genericoImpl = (GenericoCCCComercialDAO) getGenericoImplementations(conexionOCRWrapper, params);
		genericoImpl.deleteDeudasWeb(conexionOCRWrapper, params);
		
	}

	public void insertDeudasWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		genericoImpl = (GenericoCCCComercialDAO) getGenericoImplementations(conexionOCRWrapper, params);
		genericoImpl.insertDeudaWeb(conexionOCRWrapper, params);
	}
	
	public void updateDeudasWeb(ConexionOCRWrapper conexionOCRWrapper, Map params) throws Exception{
		genericoImpl = (GenericoCCCComercialDAO) getGenericoImplementations(conexionOCRWrapper, params);
		genericoImpl.updateDeudaWeb(conexionOCRWrapper, params);
	}
	
	public ProcesoCCCCargarDeudasWebDAO getProcesoCCCCargarDeudasWebDAO() {
		return procesoCCCCargarDeudasWebDAO;
	}

	public void setProcesoCCCCargarDeudasWebDAO(
			ProcesoCCCCargarDeudasWebDAO procesoCCCCargarDeudasWebDAO) {
		this.procesoCCCCargarDeudasWebDAO = procesoCCCCargarDeudasWebDAO;
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
