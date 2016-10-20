package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETiposCajaEmbalajeDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TiposCajaEmbalaje;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPETiposCajaEmbalajeService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPETiposCajaEmbalajeServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPETiposCajaEmbalajeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPETiposCajaEmbalajeServiceImpl extends BaseService implements MantenimientoAPETiposCajaEmbalajeService {

	@Resource(name="spusicc.mantenimientoAPETiposCajaEmbalajeDAO")
	private MantenimientoAPETiposCajaEmbalajeDAO mantenimientoAPETiposCajaEmbalajeDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposCajaEmbalajeService#getTipoCajaEmbalajeList(java.util.Map)
	 */
	public List getTipoCajaEmbalajeList(Map criteria) {
		return this.mantenimientoAPETiposCajaEmbalajeDAO.getTipoCajaEmbalajeList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposCajaEmbalajeService#insertarTipoCajaEmbalaje(java.util.Map)
	 */
	public void insertarTipoCajaEmbalaje(Map criteria) {
		this.mantenimientoAPETiposCajaEmbalajeDAO.insertarTipoCajaEmbalaje(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposCajaEmbalajeService#eliminarTipoCajaEmbalaje(java.util.Map)
	 */
	public String deleteTiposCajaEmbalaje(Map criteria, String[] items, String oidIdiomaiso) {
		
		String vNomTabla = new String();
		String error= new String();
		String codigoTipoCajaEmbal = "";
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
		
			criteria.put("oidTipoCajaEmb",id);
			codigoTipoCajaEmbal = this.mantenimientoAPETiposCajaEmbalajeDAO.getObtenerCodigoTipoCaja(criteria);
			criteria.put("codigoTipoCajaEmbal",codigoTipoCajaEmbal);
			
			this.mantenimientoAPETiposCajaEmbalajeDAO.deleteTiposCajaEmbalaje(criteria);
			error = (String) criteria.get("valError");
			
			if (Constants.NUMERO_CERO.equals(error)){
				vNomTabla ="1";
			}else{
				vNomTabla = (String) criteria.get("nomTabla");
				break;
			}
		}
		
		return vNomTabla;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposCajaEmbalajeService#updateTipoCajaEmbalaje(java.util.Map)
	 */
	public void updateTipoCajaEmbalaje(Map criteria) {
		this.mantenimientoAPETiposCajaEmbalajeDAO.updateTipoCajaEmbalaje(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposCajaEmbalajeService#getNextOidTipoCajaEmbalaje(java.util.Map)
	 */
	public String getNextOidTipoCajaEmbalaje(Map criteria) {
		return this.mantenimientoAPETiposCajaEmbalajeDAO.getNextOidTipoCajaEmbalaje(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposCajaEmbalajeService#getTiposCajaEmbalajeObject(java.util.Map)
	 */
	public TiposCajaEmbalaje getTiposCajaEmbalajeObject(Map criteria) {
		return this.mantenimientoAPETiposCajaEmbalajeDAO.getTiposCajaEmbalajeObject(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETiposCajaEmbalajeService#getObtenerCodigoTipoCaja(java.util.Map)
	 */
	public String getObtenerCodigoTipoCaja(Map criteria) {
		return this.mantenimientoAPETiposCajaEmbalajeDAO.getObtenerCodigoTipoCaja(criteria);
	}
	
}
