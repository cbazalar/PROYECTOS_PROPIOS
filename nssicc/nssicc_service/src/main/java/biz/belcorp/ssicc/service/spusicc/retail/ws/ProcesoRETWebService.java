/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsolidadoRetailRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ProcesoRETWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentasRetaiDetalleRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentasRetailCabecRETWebService;


/**
 * <p>
 * <a href="ProcesoRETWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface ProcesoRETWebService {

 
   
	/**
	 * Permite registar las ventas Retal tanto cabecera como detalle asi como el consolidado, metodo publicado en web service
	 * @param codigoPais
	 * @param fechaDocumentos
	 * @param retailCabecera
	 * @param retailDetalle
	 * @param retailConsolidado
	 * @param codigoIsoIdioma
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoRETWebServiceResultado saveVentaRetail(String codigoPais,
    		 String fechaDocumentos,
             VentasRetailCabecRETWebService [] retailCabecera,
    	     VentasRetaiDetalleRETWebService [] retailDetalle,
    	     ConsolidadoRetailRETWebService  [] retailConsolidado,
    	     String usuario,
    		 String codigoIsoIdioma,
    		 String tipoProceso) throws RemoteException;
    

}
