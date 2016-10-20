/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextrdelosreyes
 * @deprecated
 */
public interface ProcesaInterfaceService extends Service {

	/**
	 * 
	 * @param pais
	 */
	public void executeEliminarInformacionPrivilege(Pais pais);

	/**
	 * 
	 * @param interfaz
	 * @param criteria
	 * @param usuario
	 * @return
	 */
	public String[] procesaArchivoEntradaUnitario(Interfaz interfaz,
			Map criteria, Usuario usuario);

	/**
	 * 
	 * @param paquete
	 * @param criteria
	 * @param codigoInterfazEmpaquetada
	 * @param usuario
	 * @return
	 */
	public String[] procesaArchivoEntradaPaquete(Interfaz paquete,
			Map criteria, String[] codigoInterfazEmpaquetada, Usuario usuario);

	/**
	 * 
	 * @param pais
	 * @param interfaz
	 * @param usuario
	 * @return
	 */
	public String[] procesaArchivoSalidaUnitario(Pais pais, Interfaz interfaz,
			Usuario usuario);

	/**
	 * 
	 * @param paquete
	 * @param criteria
	 * @param codigoInterfazEmpaquetada
	 * @param usuario
	 * @return
	 */
	public String[] procesaArchivoSalidaPaquete(Interfaz paquete, Map criteria,
			String[] codigoInterfazEmpaquetada, Usuario usuario);
	
	/**
	 * 
	 * @param criteria
	 * @param usuario
	 * @param interfaz
	 * @throws Exception
	 */
	public void cargarMovimientosBancarios(ArrayList registros,
			Usuario usuario, Interfaz interfaz, Map params) throws Exception;

	/**
	 * 
	 * @param criteria
	 * @param usuario
	 * @throws Exception
	 */
	public void cargarConsolidadosOtrosCanales(Map criteria, Usuario usuario)
			throws Exception;

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getControlFacturacion(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getSubgerencias(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getRegiones(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getZonas(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getConsultoras(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getProductos(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getStickers(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getFichas(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getTarjetas(String codigoPais);

	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @deprecated
	 */
	public List getPremios(String codigoPais);

	/**
	 * Carga la cabecera del consolidado de archivos de texto de OCS.
	 * 
	 * @param params
	 * @param usuario
	 * @param interfaz
	 * 
	 * @author Lennon Shimokawa
	 */
	public void cargarConsolidadoOCSCabecera(Map params, Usuario usuario,
			Interfaz interfaz);

	/**
	 * Carga el detalle del consolidado de archivos de texto de OCS.
	 * 
	 * @param params
	 * @param usuario
	 * @param interfaz
	 * 
	 * @author Lennon Shimokawa
	 */
	public void cargarConsolidadoOCSDetalle(Map params, Usuario usuario,
			Interfaz interfaz);

	/**
	 * 
	 * @param criteria
	 * @param usuario
	 * @param interfaz
	 * @throws Exception
	 */
	public void cargarComisionCodigoPlanilla(ArrayList registros,
			Usuario usuario, Interfaz interfaz, Map params) throws Exception;

	
}			
