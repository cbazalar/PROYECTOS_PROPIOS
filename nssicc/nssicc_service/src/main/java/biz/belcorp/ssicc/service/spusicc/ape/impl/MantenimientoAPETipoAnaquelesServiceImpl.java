package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPETipoAnaquelesDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TipoAnaquel;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPETipoAnaquelesService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPETipoAnaquelesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPETipoAnaquelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
 public class MantenimientoAPETipoAnaquelesServiceImpl extends BaseService implements MantenimientoAPETipoAnaquelesService{

	@Resource(name="spusicc.mantenimientoAPETipoAnaquelesDAO")
 	private MantenimientoAPETipoAnaquelesDAO mantenimientoAPETipoAnaquelesDAO;
 	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getCodigoTipoAnaquelesList(java.util.Map)
	 */
	public List getCodigoTipoAnaquelesList(Map criteria) {
		return mantenimientoAPETipoAnaquelesDAO.getCodigoTipoAnaquelesList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getTipoAnaquelesList(java.util.Map)
	 */
	public List getTipoAnaquelesList(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.getTipoAnaquelesList(criteria);
	}
 	
 	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#deleteTipoAnaquel(java.util.Map, java.lang.String[])
	 */
	public String deleteTipoAnaquel(Map criteria, String[] items) {
		
		String vNomTabla = new String();
		String error= new String();
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
			
			criteria.put("oidTipoAnaquel", StringUtils.split(id, "|")[0]);
			criteria.put("codTipoAnaquel", StringUtils.split(id, "|")[1]);
			
			//Se valida si es que el tipo de anaquel no se por default
			int validaDefault = mantenimientoAPETipoAnaquelesDAO.validaTipoAnaquelDefaultbyOid(criteria);
			
			if(validaDefault  == 0){
				mantenimientoAPETipoAnaquelesDAO.deleteTipoAnaquel(criteria);
				error = (String) criteria.get("valError");
			
				if (Constants.NUMERO_CERO.equals(error)){
					vNomTabla ="1";
				}else{
					vNomTabla = (String) criteria.get("nomTabla");
					break;
				}
			}
			else{
				vNomTabla = "2";
			}
		}
		return vNomTabla;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getTipoAnaquelObject(java.util.Map)
	 */
	public TipoAnaquel getTipoAnaquelObject(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.getTipoAnaquelObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getTipoChanelList(java.util.Map)
	 */
	public List getTipoChanelList(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.getTipoChanelList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getOidTipoChanel(java.util.Map)
	 */
	public int getOidTipoChanel(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.getOidTipoChanel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#updateTipoAnaquel(java.util.Map)
	 */
	public void updateTipoAnaquel(Map criteria){
		mantenimientoAPETipoAnaquelesDAO.updateTipoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getNextOidTipoAnaquel(java.util.Map)
	 */
	public int getNextOidTipoAnaquel(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.getNextOidTipoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getMaxCodTipoAnaquel(java.util.Map)
	 */
	public int getExisteCodTipoAnaquel(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.getExisteCodTipoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#insertTipoAnaquel(java.util.Map)
	 */
	public void insertTipoAnaquel(Map criteria){
		mantenimientoAPETipoAnaquelesDAO.insertTipoAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#insertIdiomasComunAPE(java.util.Map)
	 */
	public void insertIdiomasComunAPE(Map criteria) {
		mantenimientoAPETipoAnaquelesDAO.insertIdiomasComunAPE(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#updateIdiomasComunAPE(java.util.Map)
	 */
	public void updateIdiomasComunAPE(Map criteria) {
		mantenimientoAPETipoAnaquelesDAO.updateIdiomasComunAPE(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#validaExisteTipoAnaquelDefaultbyTipo(java.util.Map)
	 */
	public int validaExisteTipoAnaquelDefaultbyTipo(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.validaExisteTipoAnaquelDefaultbyTipo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#updateValorTipoDefault(java.util.Map)
	 */
	public void updateValorTipoDefault(Map criteria){
		mantenimientoAPETipoAnaquelesDAO.updateValorTipoDefault(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPETipoAnaquelesService#getOidTipoAnaquelbyTipoDefault(java.util.Map)
	 */
	public String getOidTipoAnaquelbyTipoDefault(Map criteria){
		return mantenimientoAPETipoAnaquelesDAO.getOidTipoAnaquelbyTipoDefault(criteria);
	}
 }