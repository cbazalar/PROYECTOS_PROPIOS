/**
 * 
 */
package biz.belcorp.ssicc.service.spisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spisicc.InterfazIMPGeneracionDocumentosNumeroInternoDAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spisicc.InterfazIMPGeneracionDocumentosNumeroInternoService;

/** 
* 
* <p>
* <a href="InterfazIMPGeneracionDocumentosNumeroInternoServiceImpl.java.html"> <i>View Source</i> </a>
* </p>
* 
* @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
* 
*/
@Service("spisicc.interfazIMPGeneracionDocumentosNumeroInternoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIMPGeneracionDocumentosNumeroInternoServiceImpl extends BaseService implements InterfazIMPGeneracionDocumentosNumeroInternoService {
	
	@Resource(name="spisicc.interfazIMPGeneracionDocumentosNumeroInternoDAO")
	InterfazIMPGeneracionDocumentosNumeroInternoDAO interfazIMPGeneracionDocumentosNumeroInternoDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.InterfazIMPGeneracionDocumentosNumeroInternoService#getTipoProcesoDocElectronico()
	 */
	public List getTipoProcesoDocElectronico() {
		return interfazIMPGeneracionDocumentosNumeroInternoDAO.getTipoProcesoDocElectronico();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.InterfazIMPGeneracionDocumentosNumeroInternoService#getControlFacturacionById(java.util.Map)
	 */
	public PedidoControlFacturacion getControlFacturacionById(Map criteria) {
		return interfazIMPGeneracionDocumentosNumeroInternoDAO.getControlFacturacionById(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.InterfazIMPGeneracionDocumentosNumeroInternoService#getDesdeFechaFacturacion()
	 */
	public String getDesdeFechaFacturacion(Map criteria){
		return interfazIMPGeneracionDocumentosNumeroInternoDAO.getDesdeFechaFacturacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.InterfazIMPGeneracionDocumentosNumeroInternoService#insertDocElectronico(java.util.List)
	 */
	public void insertDocElectronico(List listCodigos) {
		interfazIMPGeneracionDocumentosNumeroInternoDAO.deleteDocumentosElectonicos();
		
		for (int i=0;i<listCodigos.size();i++){
			String codigoDocumento = (String) listCodigos.get(i);
			interfazIMPGeneracionDocumentosNumeroInternoDAO.insertDocumentosElectonicos(codigoDocumento);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.InterfazIMPGeneracionDocumentosNumeroInternoService#getInterfacesDocElectronico(java.lang.String)
	 */
	public List getInterfacesDocElectronico(String tipoProceso) {
		return interfazIMPGeneracionDocumentosNumeroInternoDAO.getInterfacesDocElectronico(tipoProceso);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.InterfazIMPGeneracionDocumentosNumeroInternoService#insertDocInterno(java.util.Map)
	 */
	public void insertDocInterno(Map criteria) {
		interfazIMPGeneracionDocumentosNumeroInternoDAO.deleteDocumentosElectonicos();
		interfazIMPGeneracionDocumentosNumeroInternoDAO.insertDocumentosInternos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spisicc.service.InterfazIMPGeneracionDocumentosNumeroInternoService#getTipoDocumentos(java.util.Map)
	 */
	public List getTipoDocumentos(Map criteria) {
		return interfazIMPGeneracionDocumentosNumeroInternoDAO.getTipoDocumentos(criteria);
	}
	
}
