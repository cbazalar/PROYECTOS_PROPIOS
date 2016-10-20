package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBeneficioDeudaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBeneficioDeudaService;

/**
 * @author Jesse James Rios Franco
 */

@Service("spusicc.mantenimientoSTOBeneficioDeudaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOBeneficioDeudaServiceImpl extends BaseService implements MantenimientoSTOBeneficioDeudaService{
	
	@Resource(name="spusicc.mantenimientoSTOBeneficioDeudaDAO")
	private MantenimientoSTOBeneficioDeudaDAO mantenimientoSTOBeneficioDeudaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOBeneficioDeudaService#getBeneficioDeudaList(java.util.Map)
	 */
	public List getBeneficioDeudaList(Map criteria) {
		return mantenimientoSTOBeneficioDeudaDAO.getBeneficioDeudaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOBeneficioDeudaService#deleteBeneficioDeuda(java.util.Map)
	 */
	public void deleteBeneficioDeuda(Map parametros) {
		
		String[] selectedItems = (String[])parametros.get("idSeleccionados");
		String usuario = (String)parametros.get("usuario");
		
		Map criteria = new HashMap();
		
		for (int i = 0; i < selectedItems.length; i++) {
			
			criteria.put("oidBeneficioDeuda", Integer.valueOf(selectedItems[i]));
			criteria.put("usuario", usuario);
			
			mantenimientoSTOBeneficioDeudaDAO.deleteBeneficioDeuda(criteria);
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOBeneficioDeudaService#insertDeudaBenficio(java.util.Map)
	 */
	public int insertDeudaBenficio(Map criteria) {
		List l = new ArrayList();
		l = (List)criteria.get("clienteList");
		int cont = 0;
		String codigoRegion = (String)criteria.get("codigoRegion");

		if(Constants.FORMATEAR_TODOS.equals(codigoRegion)){
			List listRegiones = (List)criteria.get("listaRegionesSTO");
			Iterator it = listRegiones.iterator();
			while(it.hasNext()){
				Base baseRegion = (Base)it.next();
				criteria.put("codigoRegion", baseRegion.getCodigo());
				mantenimientoSTOBeneficioDeudaDAO.insertDeudaBenficio(criteria);
				cont++ ;
			}
		}else{
			if(l.size() == 0){
				mantenimientoSTOBeneficioDeudaDAO.insertDeudaBenficio(criteria);
				cont = 1;
			}
			else{
				for (int i = 0; i < l.size(); i++) {
					criteria.put("oidCliente", l.get(i));
					mantenimientoSTOBeneficioDeudaDAO.insertDeudaBenficio(criteria);
					cont++;
				}
			}
		}

		return cont;
	}
}