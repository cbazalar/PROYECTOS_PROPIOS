package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECAtencionesMasivasDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.ProcesoRECActivacionReclamosGratisDAO;
import biz.belcorp.ssicc.service.spusicc.reclamos.ProcesoRECActivacionReclamosGratisService;

/**
 * @author Aurelio Oviedo
 *
 */
@Service("spusicc.procesoRECActivacionReclamosGratisService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoRECActivacionReclamosGratisServiceImpl extends BaseService implements ProcesoRECActivacionReclamosGratisService {
	
	@Resource(name="spusicc.procesoRECActivacionReclamosGratisDAO")
	ProcesoRECActivacionReclamosGratisDAO procesoRECActivacionReclamosGratisDAO;
	
	@Resource(name="spusicc.mantenimientoRECAtencionesMasivasDAO")
	MantenimientoRECAtencionesMasivasDAO mantenimientoRECAtencionesMasivasDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECActivacionReclamosGratisService#getActivacionReclamosGratisList(java.util.Map)
	 */
	public List getActivacionReclamosGratisList(Map params) {
		return procesoRECActivacionReclamosGratisDAO.getActivacionReclamosGratisList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECActivacionReclamosGratisService#executeProcesoActivacionReclamosGratis(java.util.Map)
	 */
	public void executeProcesoActivacionReclamosGratis(Map params) {
		procesoRECActivacionReclamosGratisDAO.executeProcesoActivacionReclamosGratis(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECActivacionReclamosGratisService#getDataMemoriaNByCriteria(java.util.Map)
	 */
	public List getDataMemoriaNByCriteria(Map params) {
		return procesoRECActivacionReclamosGratisDAO.getDataMemoriaNByCriteria(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.ProcesoRECActivacionReclamosGratisService#getDataMemoriaSByCriteria(java.util.Map)
	 */
	public List getDataMemoriaSByCriteria(Map params) {
		return procesoRECActivacionReclamosGratisDAO.getDataMemoriaSByCriteria(params);
	}
	
	public Map executarProcesoRECActivacionReclamosGratisAsignar(Map params){
		//-	Se debe de limpiar la tabla GTT REC_GTT_DETAL_INGRE_ATEN_MAS 
		mantenimientoRECAtencionesMasivasDAO.eliminarAtencionesMasivas();
		
		//-	Se debe de obtener un numero de lote que se va a procesar ver
		String numeroLote = mantenimientoRECAtencionesMasivasDAO.getNumeroLoteAtencionesMasivas();
		Map criteria = new HashMap();
		criteria.put("numeroLote", numeroLote);
		
		
		String dataMemoria = (String)params.get("dataMemoria");
		List recActivacionReclamosGratisList = (List)params.get("recActivacionReclamosGratisList");

		//-	Por cada registro de la grilla se deber� de agregar en la tabla REC_GTT_DETAL_INGRE_ATEN_MAS  los valores		
		for(int i=0;i<recActivacionReclamosGratisList.size();i++){
			
			criteria.put("codigoConsultora", MapUtils.getString((Map)recActivacionReclamosGratisList.get(i), "codigoCliente"));
			criteria.put("codigoVenta", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[0]);
			criteria.put("precio", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[2]);
			criteria.put("precioContable", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[4]);
			criteria.put("codigoSAP", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[3]);
			criteria.put("descripcionProducto", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[1]);
			criteria.put("idTipoOferta", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[5]);
			criteria.put("idProducto", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[6]);
			criteria.put("idMatrizFacturacion", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[7]);
			criteria.put("idOperacionReclamo", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[8]);
			criteria.put("idSolicitud", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[9]);
			criteria.put("idParametroNivel", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[10]);
			criteria.put("idLoteArticulo", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[11]);
			criteria.put("idTipoConcurso", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[12]);
			criteria.put("idDetalleOferta", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[13]);
			criteria.put("idFormaPago", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[14]);
			criteria.put("cantidad", MapUtils.getString((Map)recActivacionReclamosGratisList.get(i), "cantidadNoAtendida"));
			criteria.put("error", null);
			
			mantenimientoRECAtencionesMasivasDAO.insertAtencionesMasivasConsultoraVenta(criteria);
		}
		
		//-	Y ejecutar el siguiente SP
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("numeroLote", numeroLote);
		criteriaOperacion.put("tipoProducto", Constants.REC_TIPO_PRODUCTO);
		criteriaOperacion.put("tipoAtencion", MapUtils.getString(params, "tipoAtencion"));
		criteriaOperacion.put("codigoCliente", null);
		criteriaOperacion.put("campanhaProceso", MapUtils.getString(params, "campanaDespacho"));
		criteriaOperacion.put("campanhaReferencia", MapUtils.getString(params, "campanaFaltante"));
		criteriaOperacion.put("codigoPais", MapUtils.getString(params, "codigoPais"));
		criteriaOperacion.put("codigoUsuario", MapUtils.getString(params, "codigoUsuario"));
		criteriaOperacion.put("tipoOperacion", Constants.REC_TIPO_OPERACION);
		
		mantenimientoRECAtencionesMasivasDAO.procesarIngresoAtencionesMasivas(criteriaOperacion);
		
		//-	Luego que termina el proceso se deber� de actualizar los registros de la tabla REC_ACTIV_RECLA_GRATI, 
		//para esto se debera de procesar todos los registros de la grilla y actualizar con el OID_ACTI_RECL_GRAT, los datos a actualizar son:		
		
		for(int i=0;i<recActivacionReclamosGratisList.size();i++){
			criteria.put("codigoUsuario", MapUtils.getString(params, "codigoUsuario"));
			criteria.put("campanhaProceso", MapUtils.getString(params, "campanaDespacho"));
			criteria.put("codigoSAP", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[3]);
			criteria.put("idDetalleOferta", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[13]);
			criteria.put("codigoVenta", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[0]);
			criteria.put("precio", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[2]);
			criteria.put("precioContable", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[4]);
			criteria.put("descripcionProducto", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[1]);
			criteria.put("idTipoOferta", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[5]);
			criteria.put("idMatrizFacturacion", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[7]);
			criteria.put("idFormaPago", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[14]);
			criteria.put("idSolicitud", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[9]);
			criteria.put("idParametroNivel", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[10]);
			criteria.put("idLoteArticulo", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[11]);
			criteria.put("idTipoConcurso", StringUtils.splitPreserveAllTokens(dataMemoria, "|")[12]);
			criteria.put("oid", MapUtils.getString((Map)recActivacionReclamosGratisList.get(i), "oid"));
			
			procesoRECActivacionReclamosGratisDAO.updateActivacionReclamosGratisAsignar(criteria);
		}
		return criteria;
	}
	
}