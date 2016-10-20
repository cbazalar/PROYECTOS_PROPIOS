/*
 * Created on 07/05/2007 10:59:05 AM biz.belcorp.ssicc.sisicc.web.ws.InterfazWebService
 */
package biz.belcorp.ssicc.service.spusicc.yob.ws;

import java.rmi.RemoteException;

import biz.belcorp.ssicc.service.sisicc.ws.beans.InterfazResultado;



/**
 * <p>
 * <a href="InterfazWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva </a>
 */
public interface InterfazYOBWebService {
 
    /**
     * Trazabilidad de Lotes: Generaci�n de Lotes Matriciales de Impresi�n
     * @param codigoPais
     * @param usuario
     * @param nombreArchivo
     *  (El nombre del archivo a enviar ser� con el sgte formato: YOB-1_AAAAMMDDNNNN.TXT)
     * @return
     */
    public String[] cargaLotexProd(String codigoPais,String usuario,String nombreArchivo);
    
    /**
     * Carga de Lotes en Trazabilidad: Carga el archivo de Lotes enviado por YOBEL
     * @param codigoPais
     * @param codigoInterfaz
     * @param codigoPeriodo
     * @param fechaFacturacion
     * @param usuario
     * @return
     * @throws RemoteException
     */
    public InterfazResultado executeYOBCargaLotesTrazabilidadWeb(String codigoPais,
    		String codigoInterfaz,
    		String usuario) throws RemoteException;
     
}
