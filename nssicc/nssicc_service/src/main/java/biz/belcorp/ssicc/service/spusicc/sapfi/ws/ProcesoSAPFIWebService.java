/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.sapfi.ws;

import biz.belcorp.ssicc.service.spusicc.sapfi.ws.beans.ResultadoSAPFIWebService;


/**
 * <p>
 * <a href="ProcesoSAPFIWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Carlos Bazalar La Rosa</a>
 */
public interface ProcesoSAPFIWebService {

 
	 /**
	 * Proceso que registra a una Lider
	 * @param codigoPais
	 * @param nroDocumento
	 * @param codigoProveedor
	 * @return
	 */
	public ResultadoSAPFIWebService registrarLider (
			    String codigoPais,
			    String nroDocumento,
			    String codigoProveedor,
			    String estadoSap
	     		);
	
	
	/**
	 * Proceso que Contabiliza Pago de Lideres
	 * @param codigoPais
	 * @param nroSecuencial
	 * @param codigoProveedor
	 * @param montoNeto
	 * @return
	 */
	public ResultadoSAPFIWebService contabilizarPago (
			 String codigoPais,
			 String nroSecuencial,
			 String codigoProveedor,
			 String montoNeto,
			 String estadoSAP
			);
    
  
}
