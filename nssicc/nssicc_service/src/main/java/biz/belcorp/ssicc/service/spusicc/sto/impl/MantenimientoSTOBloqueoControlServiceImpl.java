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
import biz.belcorp.ssicc.dao.spusicc.sto.MantenimientoSTOBloqueoControlDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOBloqueoControlService;

/**
 * @author peextjrios
 */

@Service("spusicc.mantenimientoSTOBloqueoControlService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSTOBloqueoControlServiceImpl extends BaseService implements MantenimientoSTOBloqueoControlService{
	
	@Resource(name="spusicc.mantenimientoSTOBloqueoControlDAO")
	private MantenimientoSTOBloqueoControlDAO mantenimientoSTOBloqueoControlDAO;	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOBloqueoControlService#getBloqueoControlList(java.util.Map)
	 */
	public List getBloqueoControlList(Map criteria) {
		return mantenimientoSTOBloqueoControlDAO.getBloqueoControlList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOBloqueoControlService#insertSTOBloqueoControl(java.util.Map)
	 */
	public int insertSTOBloqueoControl(Map criteria) {
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
				mantenimientoSTOBloqueoControlDAO.insertSTOBloqueoControl(criteria);
				cont++ ;				
			}			
		}else{			
			if(l.size() == 0){
				mantenimientoSTOBloqueoControlDAO.insertSTOBloqueoControl(criteria);
				cont = 1;
			}
			else{
				for (int i = 0; i < l.size(); i++) {
					criteria.put("oidCliente", l.get(i));
					mantenimientoSTOBloqueoControlDAO.insertSTOBloqueoControl(criteria);
					cont++;
				}
			}
		}
		
		return cont;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOBloqueoControlService#deleteBloqueoControl(java.lang.String[])
	 */
	public void deleteBloqueoControl(Map parametros) {
		
		String[] selectedItems = (String[])parametros.get("idSeleccionados");
		String usuario = (String)parametros.get("usuario");
		
		Map criteria = new HashMap();
		
		for (int i = 0; i < selectedItems.length; i++) {
			
			criteria.put("codigoBloqueo", Integer.valueOf(selectedItems[i]));
			criteria.put("usuario", usuario);
			
			mantenimientoSTOBloqueoControlDAO.updateEstadoInactivoBloqueoControl(criteria);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.service.MantenimientoSTOBloqueoControlService#getParametroGenericoSistema(java.util.Map)
	 */
	public String getParametroGenericoSistema(Map criteria) {		
		return mantenimientoSTOBloqueoControlDAO.getParametroGenericoSistema(criteria);
	}
}