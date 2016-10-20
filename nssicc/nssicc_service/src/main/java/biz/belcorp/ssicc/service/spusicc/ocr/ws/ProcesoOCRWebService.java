/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.ocr.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.spusicc.ocr.ws.beans.ProcesoOCRWebServiceResultado;


/**
 * <p>
 * <a href="ProcesoOCRWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface ProcesoOCRWebService {

	/**
	 * Validar que exista en SICC el c�digo o documento de identidad ingresado.
	 * @param codigoConsultora
	 * @param numeroDocumento
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoOCRWebServiceResultado getValidarConsultora(String codigoConsultora,
    		 String numeroDocumento) throws RemoteException;
    
	/**
	 * @param codigoConsultora
	 * @param fechaTransferencia
	 * @return
	 * @throws RemoteException
	 */
	public ProcesoOCRWebServiceResultado saveSolicitudesTransferidas(String codigoConsultora,
			String fechaTransferencia,String codigoUsuario) throws RemoteException; 
}
