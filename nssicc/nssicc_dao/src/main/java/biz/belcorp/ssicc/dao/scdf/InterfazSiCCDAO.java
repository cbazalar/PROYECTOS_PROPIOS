package biz.belcorp.ssicc.dao.scdf;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

public interface InterfazSiCCDAO extends DAO {

    /**
     * Ejecuta el procedimiento almacenado PRI_PR_CARGA_SICC. Lo que realiza es
     * pasar informacion de las Tablas del SiCC hacia SSiCC, haciendo algunas
     * conversiones.
     * 
     * @param codigoPais
     *            Es el parametro que le pasa al Pais
     * @return 0
     */
    public int executeCargaSiCC(String codigoPais, String codigoPeriodo, String usuario);

    /**
     * Realiza la actualizacion de la Tabla PRI_PEDID referente a los campos de
     * Facturacion NUM_FACT (numero de factura) FEC_FACT (fecha de facturacion)
     * 
     * @param codigoPais
     *            Codigo del Pais del Usuario invocador
     */
    public void executeCargaNumeroBoletasDespacho(String codigoPais);
    
    /**
     * Ejecuta la carga de los productos que pertenecen al programa de fidelizacion Privilege.
     * @param codigoPais Codigo del Pais del Usuario invocador
     * @param usuario Login del usuario ejecutor
     */
    public void executeCargaProductos(String codigoPais, String usuario);
    
    /**
	 * Obtiene el periodo vigente de un determinado país y canal
	 * 
	 * @return Lista de objetos Base, poblados.
	 */
	public String getPeriodoDefaultByPaisCanal(Map params);

}
