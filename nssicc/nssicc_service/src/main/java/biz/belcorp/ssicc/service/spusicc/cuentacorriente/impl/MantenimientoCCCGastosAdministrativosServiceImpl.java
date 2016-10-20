package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCGastosAdministrativosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCGastosAdministrativosService;

/**
* @author <a href="mailto:jflorencio@belcorp.biz">Jorge FLorencio</a>
 *
 */
@Service("spusicc.mantenimientoCCCGastosAdministrativosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCGastosAdministrativosServiceImpl extends BaseService implements MantenimientoCCCGastosAdministrativosService {
	
	@Resource(name = "spusicc.mantenimientoCCCGastosAdministrativosDAO")
	MantenimientoCCCGastosAdministrativosDAO mantenimientoCCCGastosAdministrativosDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCGastosAdministrativosService#getGastosAdministrativosList(java.util.Map)
	 */
	public List getGastosAdministrativosList(Map criteria) {
		return mantenimientoCCCGastosAdministrativosDAO.getGastosAdministrativosList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCGastosAdministrativosService#insertCCCGastosAdministrativos(java.util.Map)
	 */
	public int insertCCCGastosAdministrativos(Map criteria) {
		List l = new ArrayList();
		l = (List)criteria.get("clienteList");
		int cont = 0;
		String codigoRegion = (String)criteria.get("codigoRegion");
/*
		if(Constants.FORMATEAR_TODOS.equals(codigoRegion)){
			List listRegiones = (List)criteria.get("listaRegionesSTO");
			Iterator it = listRegiones.iterator();
			while(it.hasNext()){
				Base baseRegion = (Base)it.next();
				criteria.put("codigoRegion", baseRegion.getCodigo());				
				mantenimientoCCCGastosAdministrativosDAO.insertCCCGastosAdministrativos(criteria);
				cont++ ;				
			}			
		}else{*/			
			if(l.size() == 0){
				mantenimientoCCCGastosAdministrativosDAO.insertCCCGastosAdministrativos(criteria);
				cont = 1;
			}
			else{
				for (int i = 0; i < l.size(); i++) {
					criteria.put("oidCliente", l.get(i));
					mantenimientoCCCGastosAdministrativosDAO.insertCCCGastosAdministrativos(criteria);
					cont++;
				}
			}
		//}
		
		return cont;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.MantenimientoCCCGastosAdministrativosService#deleteGastosAdministrativos(java.util.Map)
	 */
	public void deleteLogicoGastosAdministrativos(Map parametros) {
		
		String[] selectedItems = (String[])parametros.get("idSeleccionados");
		String usuario = (String)parametros.get("usuario");
		
		Map criteria = new HashMap();
		
		for (int i = 0; i < selectedItems.length; i++) {
			
			criteria.put("oid", Integer.valueOf(selectedItems[i]));
			criteria.put("usuario", usuario);
			
			mantenimientoCCCGastosAdministrativosDAO.deleteLogicoGastosAdministrativos(criteria);
		}
	}
	

}