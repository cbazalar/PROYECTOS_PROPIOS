/**
 * 
 */
package biz.belcorp.ssicc.dao.spisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;

/** 
* 
* <p>
* <a href="InterfazIMPGeneracionDocumentosNumeroInternoDAO.java.html"> <i>View Source</i> </a>
* </p>
* 
* @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
* 
*/
public interface InterfazIMPGeneracionDocumentosNumeroInternoDAO extends DAO {

	/**
	 * Obtenemos los tipos de proceso sobre documentos electrnicos
	 * @author sguerra
	 * @return
	 */
	public List getTipoProcesoDocElectronico();

	/**
	 * Obtenemos el da anterior a la fecha de facturacin
	 * @param criteria
	 * @return
	 */
	public PedidoControlFacturacion getControlFacturacionById(Map criteria);
	
	/**
	 * Obtiene (SYSDATE - 3) si DAY = LUNES (1), caso contrario devuelve SYSDATE 
	 * @return
	 */
	public String getDesdeFechaFacturacion(Map criteria);

	/**
	 * Limpia tabla temporal que contiene cdigos de documentos electrnicos
	 * @author sguerra
	 */
	public void deleteDocumentosElectonicos();

	/**
	 * Inserta los codigo de documentos electrnicos que vienen en el archivo de texto
	 * @author sguerra
	 * @param codigoDocumento
	 */
	public void insertDocumentosElectonicos(String codigoDocumento);

	/**
	 * Obtiene la lista de interfaces asociadas al tipo de documento electronico
	 * @author sguerra
	 * @param tipoProceso
	 * @return
	 */
	public List getInterfacesDocElectronico(String tipoProceso);

	/**
	 * Inserta los nmeros de documentos internos a tabla temporal de documentos
	 * @author sguerra
	 * @param criteria
	 */
	public void insertDocumentosInternos(Map criteria);

	/**
	 * Obtiene lista de tipo de documentos cargadosen temporal IMP_TMP_FELEC_CABEC  
	 * @author sguerra
	 * @return
	 */
	public List getTipoDocumentos(Map criteria);

}
