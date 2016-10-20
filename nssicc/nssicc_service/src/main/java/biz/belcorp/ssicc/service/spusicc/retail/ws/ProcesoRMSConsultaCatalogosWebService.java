/*
 * Created on 21/01/2016 10:09:05 AM biz.belcorp.ssicc.service.spusicc.retail.ws.ProcesoRMSConsultaCatalogoWebService
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsolidadoRetailRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ConsultaCatalogosRMSWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.DescuentosRetailRETWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.ProcesoRETWebServiceResultado;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentasRetaiDetalleRETWebService;
import biz.belcorp.ssicc.service.spusicc.retail.ws.beans.VentasRetailCabecRETWebService;


/**
 * <p>
 * <a href="ProcesoRMSConsultaCatalogoWebService.java"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Richar Cruzado Vallejos </a>
 */
public interface ProcesoRMSConsultaCatalogosWebService {

 
   
	/**
	 * RCR PER-SiCC-2016-0053. Desarrollo de WebService Tabla Catalogo
	 * Permite obtener el objeto Catalogo, el cual devolverá información de la tabla catalogos.
	 * 
	 */
	public ConsultaCatalogosRMSWebServiceResultado getConsultaCatalogos()
			throws RemoteException;
	
    

}
