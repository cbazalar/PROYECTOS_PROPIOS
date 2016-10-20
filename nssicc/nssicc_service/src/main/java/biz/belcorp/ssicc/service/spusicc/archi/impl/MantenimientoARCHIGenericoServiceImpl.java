package biz.belcorp.ssicc.service.spusicc.archi.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.archi.MantenimientoARCHIGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.archi.model.EntidadBorradoPeriodicoAntFecha;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.archi.MantenimientoARCHIGenericoService;

@Service("spusicc.mantenimientoARCHIGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoARCHIGenericoServiceImpl extends BaseService 
implements MantenimientoARCHIGenericoService{
	
	@Resource(name="spusicc.mantenimientoARCHIGenericoDAO")
	MantenimientoARCHIGenericoDAO mantenimientoARCHIGenericoDAO;

	
	public List getListaTipoModulo(Map params) {
		return this.mantenimientoARCHIGenericoDAO.getListaTipoModulo(params);
	}

	
	public List getEntidadPeriodicoAntiguoFechaList(Map params) {
		return this.mantenimientoARCHIGenericoDAO.getEntidadPeriodicoAntiguoFechaList(params);
	}

	
	public EntidadBorradoPeriodicoAntFecha getEntidadPeriodicoAntiguoFecha(Map params) {
		return this.mantenimientoARCHIGenericoDAO.getEntidadPeriodicoAntiguoFecha(params);
	}

	
	public void insertEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean, Usuario usuario) {
		this.mantenimientoARCHIGenericoDAO.insertEntidadBorraPeriFecha(bean, usuario);
		
	}


	public void updateEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean, Usuario usuario) {
		this.mantenimientoARCHIGenericoDAO.updateEntidadBorraPeriFecha(bean, usuario);
		
	}

	
	public void deleteEntidadBorraPeriFecha(EntidadBorradoPeriodicoAntFecha bean, Usuario usuario) {
		this.mantenimientoARCHIGenericoDAO.deleteEntidadBorraPeriFecha(bean, usuario);
		
	}
	
	

}
