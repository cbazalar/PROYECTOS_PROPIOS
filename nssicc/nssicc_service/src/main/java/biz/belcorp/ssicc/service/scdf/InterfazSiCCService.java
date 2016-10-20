/*
 * Created on 09/11/2005 06:07:07 PM
 * biz.belcorp.ssicc.scdf.service.InterfazSiCCService
 */
package biz.belcorp.ssicc.service.scdf;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSiCCService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface InterfazSiCCService {

    /**
     * A partir de las tablas del SiCC genera informacion para las tablas del
     * SSiCC, para ello invoca al
     * 
     * @param codigoPais
     *            Codigo del Pais
     * @param codigoPeriodo
     *            Codigo del Periodo a Procesar
     * @param usuario 
     *            Login del usuario que ejecuta el proceso
     * @return
     */
    public int executeCargaSiCC(String codigoPais, String codigoPeriodo, String usuario);

    /**
     * Actualiza el numero de boleta de despacho y la fecha de facturacin de
     * los pedidos que estan en GP5 para un determinado pas.
     * 
     * @param codigoPais
     *            Codigo del pais a procesar
     */
    public void executeCargaNumeroBoletasDespacho(String codigoPais);
    
    /**
     * Ejecuta la actualizacion de productos
     * @param codigoPais Codigo del pais a procesar
     * @param usuario Login del usuario ejecutor
     */
    public void executeCargaProductos(String codigoPais, String usuario);
    
	/**
	 * Obtiene el Periodo que esta corriendo actualmente
	 * @param codigoPais
	 * @param codigoCanal
	 * @return
	 */
	public String getPeriodoDefaultByPaisCanal(String codigoPais,
			String codigoCanal);
}
