package biz.belcorp.ssicc.service.spusicc.ruv.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ruv.MantenimientoRUVRegistroVentasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService;

/**
 * @author peextdoliva
 */
@Service("spusicc.mantenimientoRUVRegistroVentasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRUVRegistroVentasServiceImpl extends BaseService implements MantenimientoRUVRegistroVentasService{
	
	@Resource(name="spusicc.mantenimientoRUVRegistroVentasDAO")
	private MantenimientoRUVRegistroVentasDAO mantenimientoRUVRegistroVentasDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getListRegistroVentas(java.util.Map)
	 */
	public List getListRegistroVentas(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getListRegistroVentas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getTasasImpuesto(java.util.Map)
	 */
	public List getTasasImpuesto(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getTasasImpuesto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getCanales(java.util.Map)
	 */
	public List getCanales(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getCanales(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getAccesos(java.util.Map)
	 */
	public List getAccesos(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getAccesos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getSubAccesos(java.util.Map)
	 */
	public List getSubAccesos(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getSubAccesos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getTiposDocumentoLegal()
	 */
	public List getTiposDocumentoLegal() {
		return mantenimientoRUVRegistroVentasDAO.getTiposDocumentoLegal();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getSociedades(java.util.Map)
	 */
	public List getSociedades(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getSociedades(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getTiposDocumento()
	 */
	public List getTiposDocumento() {
		return mantenimientoRUVRegistroVentasDAO.getTiposDocumento();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getFormatoFechaNumerico(java.lang.String)
	 */
	public Map getFormatoFechaNumerico(String codigoPais) {
		return mantenimientoRUVRegistroVentasDAO.getFormatoFechaNumerico(codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getTotalRegistroVentas(java.util.Map)
	 */
	public String getTotalRegistroVentas(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getTotalRegistroVentas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getTotalRegistroRangoVentas(java.util.Map)
	 */
	public String getTotalRegistroRangoVentas(Map criteria) {
		return mantenimientoRUVRegistroVentasDAO.getTotalRegistroRangoVentas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#getRegistroVenta(java.lang.String)
	 */
	public Map getRegistroVenta(String oid) {
		return mantenimientoRUVRegistroVentasDAO.getRegistroVenta(oid);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#insertRegistroVenta(java.util.Map)
	 */
	public void insertRegistroVenta(Map params) {
		String numeroDocumentoLegal = (String)params.get("numeroDocumentoLegal");
		String numeroDocumentoLegalFinal = (String)params.get("numeroDocumentoLegalFinal");
		String numDocControlLegal = (String)params.get("numDocControlLegal");
		
		if(StringUtils.isNotEmpty(numeroDocumentoLegalFinal)) {
		
			int docLegalInc = Integer.valueOf(numeroDocumentoLegal).intValue();
        	int docLegalFin = Integer.valueOf(numeroDocumentoLegalFinal).intValue();
        	
        	int docCtrolInc=0;
        	if(StringUtils.isNotEmpty(numDocControlLegal)) 
                docCtrolInc =  Integer.valueOf(numDocControlLegal).intValue();
            
        	while(docLegalInc <= docLegalFin) {
        		params.put("numeroDocumentoLegal", String.valueOf(docLegalInc));
        		
        		if(StringUtils.isNotEmpty(numDocControlLegal)) {
        			params.put("numDocControlLegal", String.valueOf(docCtrolInc));
                    docCtrolInc++;
                }
        		
        		docLegalInc++;
        		
        		mantenimientoRUVRegistroVentasDAO.insertRegistroVenta(params);
        	}
 
        	
		} else 
			mantenimientoRUVRegistroVentasDAO.insertRegistroVenta(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#updateRegistroVenta(java.util.Map)
	 */
	public void updateRegistroVenta(Map params) {
		mantenimientoRUVRegistroVentasDAO.updateRegistroVenta(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.ruv.MantenimientoRUVRegistroVentasService#deleteRegistroVenta(java.util.Map)
	 */
	public void deleteRegistroVenta(Map params) {
		mantenimientoRUVRegistroVentasDAO.deleteRegistroVenta(params);
	}
	
}
