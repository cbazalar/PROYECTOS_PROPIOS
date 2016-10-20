package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOFacturacionAdicionalDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOFacturacionAdicionalService;

/**
 * @author Jose Luis Rodriguez
 */
@Service("spusicc.mantenimientoSTOFacturacionAdicionalService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOFacturacionAdicionalServiceImpl extends BaseService implements MantenimientoSTOFacturacionAdicionalService{
	
	@Resource(name="spusicc.mantenimientoSTOFacturacionAdicionalDAO")
	private MantenimientoSTOFacturacionAdicionalDAO mantenimientoSTOFacturacionAdicionalDAO;


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOFacturacionAdicionalService#getFacturaAdicionalList(java.util.Map)
	 */
	public List getFacturaAdicionalList(Map criteria) {
		return mantenimientoSTOFacturacionAdicionalDAO.getFacturaAdicionalList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOFacturacionAdicionalService#deleteFacturaAdicional(java.util.Map)
	 */
	public void deleteFacturaAdicional(Map parametros) {
		
		String[] selectedItems = (String[])parametros.get("idSeleccionados");
		String usuario = (String)parametros.get("usuario");
		
		Map map = new HashMap();
		map.put("codigoPais", parametros.get("codPais").toString());
		
		String val = mantenimientoSTOFacturacionAdicionalDAO.getValidFacturaAdicional(map);
	
		Map criteria = new HashMap();
		
		for (int i = 0; i < selectedItems.length; i++) {
			String registro = selectedItems[i];
			String[] valores = registro.split("\\|");
			
			criteria.put("oidFactAdicional", valores[0]);
			criteria.put("usuario", usuario);
			
			if(valores.length <= 1){
				criteria.put("codigoCliente", "");
			}else{
				criteria.put("codigoCliente", valores[1]);
			}
			
			if (StringUtils.equalsIgnoreCase(val, Constants.SI)) {
				mantenimientoSTOFacturacionAdicionalDAO.deleteFacturaAdicionalDetalle(criteria);
			mantenimientoSTOFacturacionAdicionalDAO.deleteFacturaAdicional(criteria);
			}else{
			    mantenimientoSTOFacturacionAdicionalDAO.deleteFacturaAdicional(criteria);
			}
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOFacturacionAdicionalService#insertFacturaAdicional(java.util.Map)
	 */
	public void insertFacturaAdicional(Map criteria) {
		List l = new ArrayList();
		l = (List)criteria.get("clienteList");
		Map map = new HashMap();
		map.put("codigoPais", criteria.get("codigoPais"));
		String val = mantenimientoSTOFacturacionAdicionalDAO.getValidFacturaAdicional(map);
		if(l.size() == 0){
			if (!StringUtils.equalsIgnoreCase(val, Constants.SI)) {
			mantenimientoSTOFacturacionAdicionalDAO.insertFacturaAdicional(criteria);
			}else{
				mantenimientoSTOFacturacionAdicionalDAO.insertFacturaAdicional(criteria);
				String oidUltimoFacturaAdicional = mantenimientoSTOFacturacionAdicionalDAO.getOidUltimoFacturaAdicionalCabecera();
				criteria.put("oidFactAdicCabecera", oidUltimoFacturaAdicional);
				
				mantenimientoSTOFacturacionAdicionalDAO.executeInsertFADDetalle(criteria);
			}
		}
		else{
			for (int i = 0; i < l.size(); i++) {
				criteria.put("oidCliente", l.get(i));
				if (!val.toUpperCase().equals(Constants.SI)) {
				    mantenimientoSTOFacturacionAdicionalDAO.insertFacturaAdicional(criteria);
				}else{
					//criteria.put("oidClienteList", l.get(i));
				mantenimientoSTOFacturacionAdicionalDAO.insertFacturaAdicional(criteria);
					String oidUltimoFacturaAdicional = mantenimientoSTOFacturacionAdicionalDAO.getOidUltimoFacturaAdicionalCabecera();
					criteria.put("oidFactAdicCabecera", oidUltimoFacturaAdicional);
					
					mantenimientoSTOFacturacionAdicionalDAO.executeInsertFADDetalle(criteria);
			    }
			}
		}
	}	
}