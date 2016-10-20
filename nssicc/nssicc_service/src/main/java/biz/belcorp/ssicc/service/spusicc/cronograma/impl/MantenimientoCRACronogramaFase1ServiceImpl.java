package biz.belcorp.ssicc.service.spusicc.cronograma.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cronograma.MantenimientoCRACronogramaFase1DAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRACronogramaFase1Service;

/**
 * @author <a href="mailto:dooliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Service("spusicc.cronograma.mantenimientoCRACronogramaFase1Service")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCRACronogramaFase1ServiceImpl extends BaseService implements MantenimientoCRACronogramaFase1Service {
	
	@Resource(name="spusicc.cronograma.mantenimientoCRACronogramaFase1DAO")
	MantenimientoCRACronogramaFase1DAO mantenimientoCRACronogramaFase1DAO;
		

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRAMatrizDiasService#getCargaMatrizDias(java.util.Map)
	 */
	public List generarCronogramaFase1(Map criteria){
		mantenimientoCRACronogramaFase1DAO.getGenerarCronogramaFase1(criteria);
		
		//return mantenimientoCRACronogramaFase1DAO.getPintaCronogramaFase1();
		return getCargaCronogramaFase1(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#updateCronogramaFase1(java.util.Map)
	 */
	public void updateCronogramaFase1(Map criteria){
		mantenimientoCRACronogramaFase1DAO.updateCronogramaFase1(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#getDatosCronoFase1(java.util.Map)
	 */
	public List getDatosCronoFase1(Map criteria){
		return mantenimientoCRACronogramaFase1DAO.getDatosCronoFase1(criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#getZonasCronograma(java.util.Map)
	 */
	public List getZonasCronograma(Map criteria){
		return mantenimientoCRACronogramaFase1DAO.getZonasCronograma(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#getCargaCronogramaFase1(java.util.Map)
	 */
	public List getCargaCronogramaFase1(Map criteria){		
					
       mantenimientoCRACronogramaFase1DAO.getCargaCronogramaFase1(criteria);
		
	   return mantenimientoCRACronogramaFase1DAO.getPintaCronogramaFase1();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#getCargaCronogramaFase2(java.util.Map)
	 */
	public List getCargaCronogramaFase2(Map criteria){
		mantenimientoCRACronogramaFase1DAO.deleteTemporalFase2();
		
		List zonaList = (List)criteria.get("zonaList");
		if (zonaList != null){
			
		  if (zonaList.size() > 0 )	
			for (int i=0; i<zonaList.size();i++){
				criteria.put("oidZona", zonaList.get(i));
				mantenimientoCRACronogramaFase1DAO.getCargaCronogramaFase2(criteria);
			}
		  else{
			  criteria.put("oidZona", "");
			  mantenimientoCRACronogramaFase1DAO.getCargaCronogramaFase2(criteria);
		  }
			  
		}
		return mantenimientoCRACronogramaFase1DAO.getPintaCronogramaFase2();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#getDatosCronoFase2(java.util.Map)
	 */
	public List getDatosCronoFase2(Map criteria){
		return mantenimientoCRACronogramaFase1DAO.getDatosCronoFase2(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#updateCronogramaFase2(java.util.Map)
	 */
	public void updateCronogramaFase2(Map criteria){
		mantenimientoCRACronogramaFase1DAO.deleteTemporalFase2();
		mantenimientoCRACronogramaFase1DAO.updateCronogramaFase2(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#generarCronogramaFase2(java.util.Map)
	 */
	public List generarCronogramaFase2(Map criteria){
		mantenimientoCRACronogramaFase1DAO.deleteTemporalFase2();
		
		List zonaList = (List)criteria.get("zonaList");
		if (zonaList != null){
			
		  if (zonaList.size() > 0)	
			for (int i=0; i<zonaList.size();i++){
				criteria.put("oidZona", zonaList.get(i));
				mantenimientoCRACronogramaFase1DAO.generarCronogramaFase2(criteria);
			}
		  else{
			  criteria.put("oidZona", "");
			  mantenimientoCRACronogramaFase1DAO.generarCronogramaFase2(criteria);
		  }
		}
		return mantenimientoCRACronogramaFase1DAO.getPintaCronogramaFase2();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#deleteCronogramaFase2(java.util.Map)
	 */
	public void deleteCronogramaFase2(Map criteria){
		//mantenimientoCRACronogramaFase1DAO.deleteTemporalFase2();
		
		List zonaList = (List)criteria.get("zonaList");
		if (zonaList != null){
			
		  if (zonaList.size() > 0)	
			for (int i=0; i<zonaList.size();i++){
				criteria.put("oidZona", zonaList.get(i));
				mantenimientoCRACronogramaFase1DAO.deleteCronogramaFase2(criteria);
			}
		  else{
			  criteria.put("oidZona", "");
			  mantenimientoCRACronogramaFase1DAO.deleteCronogramaFase2(criteria);
		  }
		}
		//return mantenimientoCRACronogramaFase1DAO.getPintaCronogramaFase2();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cronograma.service.MantenimientoCRACronogramaFase1Service#generarCronogramaPorZonaFase2(java.util.Map)
	 */
	public void copiaCronogramaPorZonaFase2(Map criteria){
		
		List zonaRegenerarList = (List)criteria.get("zonaRegenerarList");
		String zonaReferencia = (String)criteria.get("zonaReferencia");
		List actividadList = (List)criteria.get("actividades");
		
		if (zonaRegenerarList != null){
			
		  if (zonaRegenerarList.size() > 0)	
			for (int i=0; i<zonaRegenerarList.size();i++){
				
		      if ( !zonaRegenerarList.get(i).equals(zonaReferencia))	{	
				criteria.put("oidZona", zonaRegenerarList.get(i));
				//mantenimientoCRACronogramaFase1DAO.deleteCronogramaFase2(criteria);
				    
				    for (int j=0; j<actividadList.size();j++){
				    	criteria.put("oidActividad", actividadList.get(j));
				    	mantenimientoCRACronogramaFase1DAO.copiaCronogramaPorZonaFase2(criteria);
				    }
		      }
			}
		}

	}

}