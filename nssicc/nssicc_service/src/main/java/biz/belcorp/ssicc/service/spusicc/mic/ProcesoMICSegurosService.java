package biz.belcorp.ssicc.service.spusicc.mic;

import biz.belcorp.ssicc.service.framework.Service;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
public interface ProcesoMICSegurosService  extends Service{


	/**
	 * Genera las aptas para los microseguros manera automatica
	 * @throws Exception
	 */
	void executeGeneracionAptasMicroSeguros() throws Exception;

    /**
     * Envia los pagos de microseguros 
     * @throws Exception
     */
    void executeEnvioPagoMicroSeguros() throws Exception; 
    
    /**
     * Recepciona los pagos 
     * @throws Exception
     */
    void executeRecepcionPagoMicroSeguros() throws Exception;
    
    /**
     * Envio Aseguradora
     * @throws Exception
     */
    void executeEnvioAseguradoraMicroSeguros() throws Exception;

}
