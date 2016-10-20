/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

/**
 * @author Sigcomt
 *
 */
public interface InterfazXRXDAO {
	public void executeInterfazXRXEnviarBoletaVentaElectronica(Map params);
	public void executeInterfazXRXEnviarNotaCreditoElectronica(Map params);
	
	public void updateInterfazXRXRecepcionarBoletaVentaElectronica(Map params);
	public void updateInterfazXRXRecepcionarNotaCreditoElectronica(Map params);
	public void executeNotaCreditoAHistorico(Map params);
	public void executeBoletaVentaAHistorico(Map params);
	
	public int getCantidadBoletasVenta(String nombreArchivoSinExtension);
	public int getCantidadNotasCredito(String nombreArchivoSinExtension);
}
