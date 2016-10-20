package biz.belcorp.ssicc.service.sisicc.framework.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.InterfazOCRDAO;
import biz.belcorp.ssicc.dao.sisicc.InterfazSiCCDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Historico;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDDAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.AccionTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitadoPK;
import biz.belcorp.ssicc.service.HistoricoService;
import biz.belcorp.ssicc.service.ParametroInterfazService;
import biz.belcorp.ssicc.service.ocr.gen.GenericoOCRComercialFacadeService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BasePaqueteInterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.ProcesoSTOExecutionService;


/**
 * Interface que deben implementar las Interfaces Paquetes SiCC.
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
public abstract class SSiCC_Desfasado_BasePaqueteInterfazServiceImpl  implements SSiCC_Desfasado_BasePaqueteInterfazService {
   
	private static final String TIPO_DOCUMENTO_PARAM = "tipoDocumento";
	private static final String INDICADOR_ARCHIVO_CONTROL = "indicadorArchivoControl";
	
	protected final Log log = LogFactory.getLog(getClass());
	protected SSiCC_Desfasado_InterfazExecutionService interfazExecutionService; 
	protected ParametroInterfazService parametroInterfazService ;
	protected ProcesoSTOExecutionService procesoSTOExecutionService;
	
	protected GenericoOCRComercialFacadeService genericoOCRComercialFacadeService;
	protected InterfazOCRDAO interfazOCRDAO;
	protected MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService;
	protected InterfazSiCCDAO interfazSiCCDAO;
	protected ProcesoPEDDAO procesoPEDDAO;
	
	private HistoricoService historicoService;
	
	/**
	 * metodo a llamar antes de ejecutar a la interfaz
	 * @param params
	 */
	protected  abstract void beforeExecutePaqueteInterfaz(Map params) ;
	
	/**
	 * metodo que ejecuta la interfaz
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected  abstract SSiCC_Desfasado_InterfazExecutionResult executePaqueteInterfaz(Map params) throws Exception ;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BasePaqueteInterfazService#afterExecutePaqueteInterfaz(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.beans.SSiCC_Desfasado_InterfazExecutionResult)
	 */
	public void afterExecutePaqueteInterfaz(Map params, SSiCC_Desfasado_InterfazExecutionResult result) throws Exception{
	   log.debug("Entering 'super afterExecuteInterfaz' method ");
	   
	   String tipoDocumento= (String)params.get("paramTipoDocumento");
	   String indValidacionSTO = (String)params.get("indValidacionSTO");
	   String indicadorArchivoControl = (String)params.get("paramIndicadorArchivoControl");
	   log.debug("====>Ingreso a execute Validacion Automatica<==== ::: "+indValidacionSTO);
	   log.debug("====>tipoDocumento<==== ::: "+tipoDocumento);
	   log.debug("====>indicadorArchivoControl<==== ::: "+indicadorArchivoControl);
	   
	   if(result.ejecucionCompletada()) {
		   log.debug("====>result.isEjecutarSTO<==== ::: " + result.isEjecutarSTO());
		   if (result.isEjecutarSTO() && StringUtils.isNotEmpty(tipoDocumento) &&  (StringUtils.isEmpty(indValidacionSTO) ||  Constants.SI.equals(indValidacionSTO))){
			   String codigoPais = (String)params.get("codigoPais");        
			   AccionTipoDocumento accionTipoDocumento = new AccionTipoDocumento(codigoPais,tipoDocumento,Constants.STO_ACCI_VALI_AUTO);    			
			   procesoSTOExecutionService.execute(accionTipoDocumento,params, null);
		   }
	   }	   
	   
	   if(Constants.UNO.equals(indicadorArchivoControl)) {
		   log.debug("Se Valida Archivo de Control para las interfaces Unitarias");
		   
		   String codigoErrorControl= validarInterfazByControl(params, result.getInterfaz().getCodigo());	
		   if(!codigoErrorControl.equals(Constants.ARCHIVO_NO_EXISTE_EN_CONTROL)){
			   log.debug("Se actualiza Archivo de Control para las interfaces Unitarias Cargadas, como si fueran no Cargadas");		
			   updateEstadoCargadasArchivoControl(params, result.getInterfaz().getCodigo());
		  }
	   }
	   
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.sisicc.framework.SSiCC_Desfasado_BasePaqueteInterfazService#executeInterfaz(java.util.Map)
	 */
	public SSiCC_Desfasado_InterfazExecutionResult executeInterfaz(Map params) throws Exception{
		beforePrepareParams(params);
		SSiCC_Desfasado_InterfazExecutionResult result=null;
		beforeExecutePaqueteInterfaz(params);
		
		result = interfazExecutionService.executeInterfaz(params);
		Historico historicoEjecucion = result.getHistoricoEjecucion();
		Usuario usuario = (Usuario) params.get("usuario");
		
		try {
			afterExecutePaqueteInterfaz(params,result);
			historicoService.updateHistorico(historicoEjecucion, usuario);
			
		} catch(Exception ex) {
			log.error(ex.getMessage());
			
			historicoEjecucion.setFlagError(Constants.SI);
			historicoEjecucion.setDescripcionError(ex.getMessage());
			historicoEjecucion.setEstadoProceso(Constants.ESTADO_PROCESO_INTERFAZ_ERROR_DESCONOCIDO);
			
			historicoService.updateHistorico(historicoEjecucion, usuario);
			
			throw ex;
		}
		
		return result; 
	}

	/**
	 * su funcion principal es obtnerer los parametros del paquete
	 * y obtener el numero d elote STO si es tiene como parametro el 
	 * tipo de doucumento
	 * @param params
	 */
	protected void beforePrepareParams(Map params) {
		log.debug("inicio beforePrepareParams PAQUETE");
		String codigoPais= (String)params.get("codigoPais");
		String codigoSistema= (String)params.get("codigoSistema");
		String codigo= (String)params.get("codigoInterfaz");
		InterfazPK interfazPK = new InterfazPK(codigoPais, codigoSistema, codigo);
		List<ParametroInterfaz> list = parametroInterfazService.getParametrosByPKInterfaz(interfazPK);
		for(ParametroInterfaz p : list){
			params.put(p.getNombre(), p.getValor());			
			//
			if(TIPO_DOCUMENTO_PARAM.equals(p.getNombre())){
				log.debug("tipo documento  "+p.getValor());
				String numLoteSTO = interfazExecutionService.
												getStoService().
													updateLoteSTO(new TipoDocumentoDigitadoPK(codigoPais,p.getValor()));
				log.info("numLoteSTO : "+ numLoteSTO);		
				params.put("numLoteSTO",numLoteSTO);
				params.put("paramTipoDocumento",p.getValor());				
			}
			if(INDICADOR_ARCHIVO_CONTROL.equals(p.getNombre())){
				log.debug("indicador Archivo Control  "+p.getValor());
				params.put("paramIndicadorArchivoControl",p.getValor());				
			}
						
		}
		
		log.debug("fin beforePrepareParams PAQUETE");
	}

	/**
	 * Valida el interfaz de paquete conta la tabla de control, si tiene alguna interfaz unitaria x cargar
	 * @param params
	 * @param codigo
	 * @return
	 */
	private String validarInterfazByControl(Map params, String codigo) {
		Map criteria = new HashMap();
		Long oidControl=null;
		criteria.put("codigoPais", params.get("codigoPais"));
		criteria.put("codigoSistema", params.get("codigoSistema"));		
		criteria.put("idProcesoBatch", params.get("idProcesoBatch"));
		criteria.put("codigoPaquete", codigo);
		
		try{
		  log.debug("validando archivo control para interfaz paquete");	
		  oidControl = interfazExecutionService.getInterfazService().getOidArchivoControl(criteria);//si existe oidcontrol es not null
		}catch(Exception e){
		  //hay mas de un lote que no se ha cargado ponemos a O para pasar la validacion de continuar
			oidControl =new Long(0);
		}
		log.debug("oidControl "+oidControl);
		if(oidControl != null) return "-1";				
		return "-2";
		
	}
	
	/**
	 * actualiza en el archivo de control, a las interfaces que se marcaron como cargadas se marcaran
	 * como no cargadas
	 * 
	 * @param params
	 * @param codigo
	 * @return
	 */
	private void updateEstadoCargadasArchivoControl(Map params, String codigo) {
		Map criteria = new HashMap();
		Long oidControl=null;
		criteria.put("codigoPais", params.get("codigoPais"));
		criteria.put("codigoSistema", params.get("codigoSistema"));		
		criteria.put("idProcesoBatch", params.get("idProcesoBatch"));
		criteria.put("codigoPaquete", codigo);
		criteria.put("codigoUsuario", params.get("codigoUsuario"));
		
  	    log.debug("actualizando archivo control para interfaz paquete");
  	    interfazExecutionService.getInterfazService().updateEstadoCargadasArchivoControl(criteria);
	}
	
	

	/**
	 * @return the interfazExecutionService
	 */
	public SSiCC_Desfasado_InterfazExecutionService getInterfazExecutionService() {
		return interfazExecutionService;
	}

	/**
	 * @param interfazExecutionService the interfazExecutionService to set
	 */
	public void setInterfazExecutionService(
			SSiCC_Desfasado_InterfazExecutionService interfazExecutionService) {
		this.interfazExecutionService = interfazExecutionService;
	}

	/**
	 * @return the parametroInterfazService
	 */
	public ParametroInterfazService getParametroInterfazService() {
		return parametroInterfazService;
	}

	/**
	 * @param parametroInterfazService the parametroInterfazService to set
	 */
	public void setParametroInterfazService(
			ParametroInterfazService parametroInterfazService) {
		this.parametroInterfazService = parametroInterfazService;
	}

	/**
	 * @return the procesoSTOExecutionService
	 */
	public ProcesoSTOExecutionService getProcesoSTOExecutionService() {
		return procesoSTOExecutionService;
	}

	/**
	 * @param procesoSTOExecutionService the procesoSTOExecutionService to set
	 */
	public void setProcesoSTOExecutionService(
			ProcesoSTOExecutionService procesoSTOExecutionService) {
		this.procesoSTOExecutionService = procesoSTOExecutionService;
	}

	/**
	 * @return the genericoOCRComercialFacadeService
	 */
	public GenericoOCRComercialFacadeService getGenericoOCRComercialFacadeService() {
		return genericoOCRComercialFacadeService;
	}

	/**
	 * @param genericoOCRComercialFacadeService the genericoOCRComercialFacadeService to set
	 */
	public void setGenericoOCRComercialFacadeService(
			GenericoOCRComercialFacadeService genericoOCRComercialFacadeService) {
		this.genericoOCRComercialFacadeService = genericoOCRComercialFacadeService;
	}

	/**
	 * @return the interfazOCRDAO
	 */
	public InterfazOCRDAO getInterfazOCRDAO() {
		return interfazOCRDAO;
	}

	/**
	 * @param interfazOCRDAO the interfazOCRDAO to set
	 */
	public void setInterfazOCRDAO(InterfazOCRDAO interfazOCRDAO) {
		this.interfazOCRDAO = interfazOCRDAO;
	}

	/**
	 * @return the mantenimientoOCRPedidoControlFacturacionService
	 */
	public MantenimientoOCRPedidoControlFacturacionService getMantenimientoOCRPedidoControlFacturacionService() {
		return mantenimientoOCRPedidoControlFacturacionService;
	}

	/**
	 * @param mantenimientoOCRPedidoControlFacturacionService the mantenimientoOCRPedidoControlFacturacionService to set
	 */
	public void setMantenimientoOCRPedidoControlFacturacionService(
			MantenimientoOCRPedidoControlFacturacionService mantenimientoOCRPedidoControlFacturacionService) {
		this.mantenimientoOCRPedidoControlFacturacionService = mantenimientoOCRPedidoControlFacturacionService;
	}

	/**
	 * @return the interfazSiCCDAO
	 */
	public InterfazSiCCDAO getInterfazSiCCDAO() {
		return interfazSiCCDAO;
	}

	/**
	 * @param interfazSiCCDAO the interfazSiCCDAO to set
	 */
	public void setInterfazSiCCDAO(InterfazSiCCDAO interfazSiCCDAO) {
		this.interfazSiCCDAO = interfazSiCCDAO;
	}

	/**
	 * @return the procesoPEDDAO
	 */
	public ProcesoPEDDAO getProcesoPEDDAO() {
		return procesoPEDDAO;
	}

	/**
	 * @param procesoPEDDAO the procesoPEDDAO to set
	 */
	public void setProcesoPEDDAO(ProcesoPEDDAO procesoPEDDAO) {
		this.procesoPEDDAO = procesoPEDDAO;
	}

	/**
	 * @return the historicoService
	 */
	public HistoricoService getHistoricoService() {
		return historicoService;
	}

	/**
	 * @param historicoService the historicoService to set
	 */
	public void setHistoricoService(HistoricoService historicoService) {
		this.historicoService = historicoService;
	}
		
}