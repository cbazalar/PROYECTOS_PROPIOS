package biz.belcorp.ssicc.dao.spusicc.cobranzas;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoCOBRecuperacionCarteraFFVVDAO"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:esanchez@sigcomt.com">Eduardo Snchez</a>
 */
public interface ProcesoCOBRecuperacionCarteraFFVVDAO extends DAO{

	/**
	 * Obtine los correos de gerentes de zona
	 * @param params
	 * @return
	 */
    public List getListadoCorreosGerenteZona();
    
    /**
     * Obtiene una relacion de los datos del cuerpo del mensaje para los correos a travs de un objeto tipo map.
     * 
     * @param criteria
     * @return Lista de objetos
     */
    public List getDatosCuerpoMensajeCorreoCarteraFFVV(Map criteria);
    
	/**
	 * Obtine el correo del gerente de regin de Bas_Param_Pais
	 * @param criteria
     * @return Correo Electronico de Gerente de Regin por Defecto
	 */
    public String getParamEmailGerenteRegion(Map criteria);
    
	/**
	 * Devuelve el valor del parametro
	 * @param criteria
	 * @return
	 */
	public String getParametroGenericoSistema(Map criteria);

	/**
	 * Obtiene el listado del cuerpo del mensaje para la GR
	 * @param criteria
	 * @return
	 */
	public List getDatosCuerpoMensajeCorreoCarteraFFVVGR(Map criteria);
    
}