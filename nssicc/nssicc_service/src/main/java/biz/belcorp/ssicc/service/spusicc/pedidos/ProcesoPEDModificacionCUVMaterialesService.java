package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.model.LabelValueCUV;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 */

public interface ProcesoPEDModificacionCUVMaterialesService extends Service{
	
	/**
	 * devuelve los datos del producto entre ellos el CUV por codigo SAP para luego ser modificado
	 * @param codigoSAP
	 * @return
	 */
	public LabelValueCUV getDatosCUVByCodigoSAP(String codigoSAP);

	/**
	 * actualiza el codigo de venta unico de un producto
	 * @param params
	 */
	public void updateCodVentaByOidProducto(Map params);

	/**
	 * inserta los datos de los cambios en hechos en la tabla de auditoria
	 * @param params
	 */
	public void saveAuditoria(Map params);
}