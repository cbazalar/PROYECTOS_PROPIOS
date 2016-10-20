package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONCallesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONCallesService;

@Service("spusicc.mantenimientoZONCallesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoZONCallesServiceImpl extends BaseService implements MantenimientoZONCallesService {

	@Resource(name="spusicc.mantenimientoZONCallesDAO")
	private MantenimientoZONCallesDAO mantenimientoZONCallesDAO;

	public List getDireccionesClientesList(Map criteria) {
		return mantenimientoZONCallesDAO.getDireccionesClientesList(criteria);
	}

	public void insertZonCalle(Map criteria) {
		Long idSgteZonCalle = mantenimientoZONCallesDAO.getIdSgteZonCalle();
		criteria.put("oidCalle", idSgteZonCalle);

		Long oidValoEstrGeop = mantenimientoZONCallesDAO.getOidValoEstrGeop(criteria);
		criteria.put("oidValoEstrGeop", oidValoEstrGeop);

		mantenimientoZONCallesDAO.insertZonCalle(criteria);

	}

	public Map getZonCalle(Long oidCalle) {
		return mantenimientoZONCallesDAO.getZonCalle(oidCalle);
	}
	
	public void updateZonCalle(Map criteria) {
		Long oidValoEstrGeop = mantenimientoZONCallesDAO.getOidValoEstrGeop(criteria);
		criteria.put("oidValoEstrGeop", oidValoEstrGeop);
		
		mantenimientoZONCallesDAO.updateZonCalle(criteria);
		
		
	    HashMap criteria2 = new HashMap();
	    criteria2.put("descripcionVia", MapUtils.getString(criteria, "nombreViaB"));
	    criteria2.put("correVia", MapUtils.getString(criteria, "oidCalle"));		
	    criteria2.put("tipoVia", MapUtils.getString(criteria, "tipoVia"));
	    String cUg = MapUtils.getString(criteria, "nivel1")
	    		     .concat(MapUtils.getString(criteria, "nivel2"))
	    		     .concat(MapUtils.getString(criteria, "nivel3"))
	    		     .concat(MapUtils.getString(criteria, "nivel4"))
	    		     .concat(MapUtils.getString(criteria, "nivel5"))
	    		     .concat(MapUtils.getString(criteria, "nivel6"));
	    criteria2.put("codigoUnidadGeo", cUg);
		
		mantenimientoZONCallesDAO.updateDireccionCliente(criteria2);
	}
	
	public void deleteZonCalle(Map criteria) {
		mantenimientoZONCallesDAO.deleteZonCalle(criteria);
	}
	
	public String getValidaConsultoraCalle(Map criteria){
	   return mantenimientoZONCallesDAO.getValidaConsultoraCalle(criteria);
	}
}
