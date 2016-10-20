/**
 * 
 */
package biz.belcorp.ssicc.dao.spisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spisicc.InterfazIMPGeneracionDocumentosNumeroInternoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;

/** 
* 
* <p>
* <a href="InterfazIMPGeneracionDocumentosNumeroInternoDAOIbatis.java.html"> <i>View Source</i> </a>
* </p>
* 
* @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
* 
*/
@Repository("spisicc.interfazIMPGeneracionDocumentosNumeroInternoDAO")    
public class InterfazIMPGeneracionDocumentosNumeroInternoDAOIbatis extends BaseDAOiBatis implements
		InterfazIMPGeneracionDocumentosNumeroInternoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#getTipoProcesoDocElectronico()
	 */
	public List getTipoProcesoDocElectronico() {
		return getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getTipoProcesoDocElectronico");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#getControlFacturacionById(java.util.Map)
	 */
	public PedidoControlFacturacion getControlFacturacionById(Map criteria) {
		return (PedidoControlFacturacion)getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getControlFacturacionByCriteria", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#getDesdeFechaFacturacion()
	 */
	public String getDesdeFechaFacturacion(Map criteria) {
		return (String)getSqlMapClientTemplate().queryForObject("ProcesoImpresionSQL.getDesdeFechaFacturacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#deleteDocumentosElectonicos()
	 */
	public void deleteDocumentosElectonicos() {
		getSqlMapClientTemplate().delete("ProcesoImpresionSQL.deleteDocumentosElectonicos");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#insertDocumentosElectonicos(java.lang.String)
	 */
	public void insertDocumentosElectonicos(String codigoDocumento) {
		getSqlMapClientTemplate().insert("ProcesoImpresionSQL.insertDocumentosElectonicos", codigoDocumento);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#getInterfacesDocElectronico(java.lang.String)
	 */
	public List getInterfacesDocElectronico(String tipoProceso) {
		return (List)getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getInterfacesDocElectronico", tipoProceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#insertDocumentosInternos(java.util.Map)
	 */
	public void insertDocumentosInternos(Map criteria) {
		getSqlMapClientTemplate().update("ProcesoImpresionSQL.executeInsertaDocumentosInternos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.dao.InterfazIMPGeneracionDocumentosNumeroInternoDAO#getTipoDocumentos(java.util.Map)
	 */
	public List getTipoDocumentos(Map criteria) {
		return (List)getSqlMapClientTemplate().queryForList("ProcesoImpresionSQL.getTipoDocumentos", criteria);
	}
	
}
