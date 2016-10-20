package biz.belcorp.ssicc.service.spusicc.lideres.impl;

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
import biz.belcorp.ssicc.dao.spusicc.lideres.MantenimientoLIDIncrementoVariablesVentaDAO;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.IncrementoVariableVenta;
import biz.belcorp.ssicc.dao.spusicc.lideres.model.TipoIncremento;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lideres.MantenimientoLIDIncrementoVariablesVentaService;

@Service("spusicc.mantenimientoLIDIncrementoVariablesVentaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLIDIncrementoVariablesVentaServiceImpl extends BaseService implements
		MantenimientoLIDIncrementoVariablesVentaService {
	
	@Resource(name="spusicc.mantenimientoLIDIncrementoVariablesVentaDAO")
	MantenimientoLIDIncrementoVariablesVentaDAO mantenimientoLIDIncrementoVariablesVentaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDIncrementoVariablesVentaService#getVariableVentasList(java.lang.String)
	 */
	public List getVariableVentasList(String codIdioma) {
		return mantenimientoLIDIncrementoVariablesVentaDAO.getVariableVentasList(codIdioma);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDIncrementoVariablesVentaService#getTipoIncrementoMap(java.util.List)
	 */
	public Map getTipoIncrementoMap(List tipoIncrementoList){
			Map variableVentaMap = new HashMap();
			for (Iterator iterator = tipoIncrementoList.iterator(); iterator
					.hasNext();) {
				TipoIncremento tipoIncremento = (TipoIncremento) iterator.next();
				variableVentaMap.put(tipoIncremento.getCodigo(), tipoIncremento.getDescripcion()); 
			}
		return variableVentaMap;
	}

	public List getTipoIncrementoList() {
		return mantenimientoLIDIncrementoVariablesVentaDAO.getTipoIncrementoList();
	}
	
	/**
	 * @return Returns the mantenimientoLIDIncrementoVariablesVentaDAO.
	 */
	public MantenimientoLIDIncrementoVariablesVentaDAO getMantenimientoLIDIncrementoVariablesVentaDAO() {
		return mantenimientoLIDIncrementoVariablesVentaDAO;
	}

	/**
	 * @param mantenimientoLIDIncrementoVariablesVentaDAO The mantenimientoLIDIncrementoVariablesVentaDAO to set.
	 */
	public void setMantenimientoLIDIncrementoVariablesVentaDAO(
			MantenimientoLIDIncrementoVariablesVentaDAO mantenimientoLIDIncrementoVariablesVentaDAO) {
		this.mantenimientoLIDIncrementoVariablesVentaDAO = mantenimientoLIDIncrementoVariablesVentaDAO;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDIncrementoVariablesVentaService#getIncrementoVariableVentaList(java.util.Map)
	 */
	public List getIncrementoVariableVentaList(Map criteria) {
		boolean locate = false;
		
		List incrementoVariableVentaList = mantenimientoLIDIncrementoVariablesVentaDAO.getIncrementoVariableVentaList(criteria);
		List _incrementoVariableVentaList=new ArrayList();
		String periodoProceso=(String) criteria.get("periodoProceso"); 
		String periodoMaximo=(String) criteria.get("periodoMaximo");
		int anioProceso =Integer.parseInt(periodoProceso.substring(0,4));
		int perProceso= Integer.parseInt(periodoProceso.substring(4));
		int anioMaximo=Integer.parseInt(periodoMaximo.substring(0,4));
		int perMaximo=Integer.parseInt(periodoMaximo.substring(4));
		String codProcesoAux;
		
		
		log.debug("tamao de la lista   : " + incrementoVariableVentaList.size());
		while ( anioProceso<=anioMaximo && perProceso!=perMaximo) {
			perProceso++;
			locate=false;
			log.debug("proceso 1 :  " + perProceso/Constants.NUMERO_CAMPANYAS);
			log.debug("proceso 2 :  " + perProceso%Constants.NUMERO_CAMPANYAS);
			
			if(perProceso<10){
				codProcesoAux= anioProceso+"0"+perProceso;
			}else{
				codProcesoAux= ""+anioProceso+perProceso;
			}
			log.debug("- anioProceso : " + codProcesoAux);

			if(perProceso<=Constants.NUMERO_CAMPANYAS){									
				for (Iterator iter = incrementoVariableVentaList.iterator(); iter.hasNext();) {
					IncrementoVariableVenta incrementoVariableVenta = (IncrementoVariableVenta) iter.next();
					if(incrementoVariableVenta.getCodPeriodo().equals(codProcesoAux)){
						locate=true;
						if(incrementoVariableVenta.getValorIncremento()==null){
							log.debug(" - Seteando...");
							incrementoVariableVenta.setValorIncremento((criteria.get("valorIncremento")).equals("")?""+0:(String)criteria.get("valorIncremento"));
						} 				
						_incrementoVariableVentaList.add(incrementoVariableVenta);
					}
					
				}
				if(!locate){
					log.debug("nuevos registros : ");
					IncrementoVariableVenta incrementoVariableVentaNew = new IncrementoVariableVenta();
					incrementoVariableVentaNew.setCodPeriodo(codProcesoAux);
					incrementoVariableVentaNew.setValorIncremento((criteria.get("valorIncremento")).equals("")?""+0:(String)criteria.get("valorIncremento"));
					incrementoVariableVentaNew.setTipoIncremento((String)criteria.get("tipoIncremento"));
					log.debug(" - Seteando...");
					_incrementoVariableVentaList.add(incrementoVariableVentaNew);
					
				}
			}
			if(perProceso>=Constants.NUMERO_CAMPANYAS){
				anioProceso++;
				perProceso =0;
			}
				
		}
		
		
		log.debug("tamao de la lista a retornar : " + _incrementoVariableVentaList.size());	
		return _incrementoVariableVentaList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDIncrementoVariablesVentaService#getPeriodoMaximo(java.util.Map)
	 */
	public String getPeriodoMaximo(Map criteria) {
		return mantenimientoLIDIncrementoVariablesVentaDAO.getPeriodoMaximo(criteria);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDIncrementoVariablesVentaService#updateVariableIncrementoVenta(biz.belcorp.ssicc.spusicc.lideres.model.IncrementoVariableVenta)
	 */
	public void updateVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta) {
		mantenimientoLIDIncrementoVariablesVentaDAO.updateVariableIncrementoVenta(incrementoVariableVenta);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDIncrementoVariablesVentaService#saveVariableIncrementoVenta(biz.belcorp.ssicc.spusicc.lideres.model.IncrementoVariableVenta)
	 */
	public void saveVariableIncrementoVenta(IncrementoVariableVenta incrementoVariableVenta) {
		mantenimientoLIDIncrementoVariablesVentaDAO.saveVariableIncrementoVenta(incrementoVariableVenta);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lideres.service.MantenimientoLIDIncrementoVariablesVentaService#executeGenerarActividadFinalZonasPeriodo(java.util.Map)
	 */
	public void executeGenerarActividadFinalZonasPeriodo(Map params) {
		String[] codPais = (String[]) params.get("paramCodPais");
		String[] codMarca = (String[]) params.get("paramCodMarca");
		String[] codPeriodo = (String[]) params.get("paramCodPeriodo");
		String[] codVariableVenta = (String[]) params.get("paramCodVariableVenta");
		for(int i = 0;i<codPeriodo.length;i++ ){
			params.put("codigoPais",codPais[i]);
			params.put("codigoMarca",codMarca[i]);
			params.put("codigoPeriodo",codPeriodo[i]);
			params.put("codigoVariableVenta",codVariableVenta[i]);
			log.debug("values : " + codPais[i]+" - " +codMarca[i]+" - "+codPeriodo[i]+" - "+codVariableVenta[i]);
			mantenimientoLIDIncrementoVariablesVentaDAO.executeGenerarActividadFinalZonasPeriodo(params);
		}
	}
}
