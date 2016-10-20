package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCargaPedidosObjetivosExcelDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.ProcesoLETCargaPedidosObjetivosExcelService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author Jesse James Rios Franco
 *
 */
@Service("spusicc.procesoLETCargaPedidosObjetivosExcelService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLETCargaPedidosObjetivosExcelServiceImpl extends BaseService implements ProcesoLETCargaPedidosObjetivosExcelService{
	
	@Resource(name="spusicc.procesoLETCargaPedidosObjetivosExcelDAO")
	private ProcesoLETCargaPedidosObjetivosExcelDAO procesoLETCargaPedidosObjetivosExcelDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#loadfileExcel(java.util.Map, java.util.List, java.util.List, java.lang.Boolean)
	 */
	public boolean loadfileExcel(Map criteria, List pedidosObjetivosList,List errorPedidosObjetivosListList, Boolean flagErrorGlobal) 
	throws Exception{
		
		boolean flagError = false;
		boolean flagErrorGeneral = false;
		String  directorioTemporal = (String)criteria.get("directorioTemporal");
		String  nombreArchivo = (String)criteria.get("nombreArchivo");
		String  codigoPais = (String)criteria.get("codigoPais");
		String  codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String  codigoUsuario = (String)criteria.get("login");
		String[] regionList = (String[])criteria.get("regionList");
		String codigoConcurso = (String)criteria.get("codigoConcurso");
		String campanaFinConcurso = (String)criteria.get("campanaFinConcurso");
		String indicadorValidaLiderSeccion = (String)criteria.get("indicadorValidaLiderSeccion");
			
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		//nos pasamos a la segunda fila, ya que el primero se encuentra la cabecera
		excelUtil.next();
		
		int fila = 1;
		
		Map pedidosObjetivos = null;
		String mensajeError = null;
		
		while(excelUtil.hasNext()){
			flagError = false;
			Map mapRow = excelUtil.next();
			mensajeError = "";
			
			pedidosObjetivos = new HashMap();
			
			fila = fila + 1;
			
			//try {
				
				log.debug("Carga Pedidos Objetivos mapRow--- "+fila+" - "+mapRow.toString());
				
				pedidosObjetivos.put("fila", fila);
				pedidosObjetivos.put("codigoPais", ((String)mapRow.get("0")).toUpperCase().trim());
				pedidosObjetivos.put("codigoPeriodo", Integer.valueOf((((Long)new DecimalFormat("000000").parse((String)mapRow.get("1"))).intValue())));
				pedidosObjetivos.put("codigoMarca", ((String)mapRow.get("2")).toUpperCase().trim());
				pedidosObjetivos.put("codigoRegion", ((String)mapRow.get("3")).toUpperCase().trim());
				pedidosObjetivos.put("codigoZona", ((String)mapRow.get("4")).toUpperCase().trim());
				pedidosObjetivos.put("codigoSeccion", ((String)mapRow.get("5")).toUpperCase().trim());
				pedidosObjetivos.put("numeroPeridosObjetivos", Integer.valueOf(((Long)new DecimalFormat("0000").parse((String)mapRow.get("6"))).intValue()));
				pedidosObjetivos.put("codigoConcurso", codigoConcurso);
				pedidosObjetivos.put("codigoUsuario", codigoUsuario);
				
				if(!((String)pedidosObjetivos.get("codigoPais")).equals(codigoPais)){
					mensajeError = getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorPaisNoExiste");
					flagError = true;
					flagErrorGeneral = true;
				}
				
				if(((Integer) pedidosObjetivos.get("codigoPeriodo") > Integer.valueOf(campanaFinConcurso))){
					mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorPeriodoDiferenteProceso");
					flagError = true;
					flagErrorGeneral = true;
				}
				
				if(((Integer) pedidosObjetivos.get("codigoPeriodo") < Integer.valueOf(codigoPeriodo))){
					mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorPeriodoMenorAlProcesado");
					flagError = true;
					flagErrorGeneral = true;
				}
				
				boolean isEncontro = false;
				
				for(int i=0;i<regionList.length;i++){
					if(((String)pedidosObjetivos.get("codigoRegion")).equals(regionList[i])){
						isEncontro = true;
						break;
					}
				}
				
				if(!isEncontro){
					mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorRegionProcesadaDiferenteProceso");
					flagError = true;
					flagErrorGeneral = true;
				}
				
				criteria.put("codigoZona", pedidosObjetivos.get("codigoZona"));
				criteria.put("codigoRegion", pedidosObjetivos.get("codigoRegion"));
				criteria.put("codigoSeccion", pedidosObjetivos.get("codigoSeccion"));
				
				if(this.getExisteZona(criteria).intValue() == 0){
					mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorZonaNoExisteNoActiva");
					flagError = true;
					flagErrorGeneral = true;
				}
					
				if(indicadorValidaLiderSeccion.equals("1")){
					if(this.getSeccioNueva(criteria).intValue() == 0 && this.getZonaNuevaRezonificada(criteria).intValue() == 0){
						mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorSeccionNoNuevaRezonificada");
						flagError = true;
						flagErrorGeneral = true;
					}
				}else{
					if(this.getExisteSeccionByZona(criteria).intValue() == 0){
						mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorSeccionNoExisteNoActiva");
						flagError = true;
						flagErrorGeneral = true;
					}
				}
				
				/*if(this.getExisteZona(criteria).intValue() > 0){
					
					if(this.getExisteSeccionByZona(criteria).intValue() > 0){
						if(this.getZonaNuevaRezonificada(criteria).intValue() == 0 && this.getSeccioNueva(criteria).intValue() == 0){
						mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorSeccionNoNuevaRezonificada");
						flagError = true;
						flagErrorGeneral = true;
					}
					}else{
						mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorSeccionNoExisteNoActiva");
						flagError = true;
						flagErrorGeneral = true;
					}
					
				}else{
					mensajeError = mensajeError+getKeyMessage("procesoLETCargaPedidosObjetivosExcelForm.mensajeErrorZonaNoExisteNoActiva");
					flagError = true;
					flagErrorGeneral = true;
				}*/
				
				if(flagError){
					pedidosObjetivos.put("descripcionError",mensajeError);
					errorPedidosObjetivosListList.add(pedidosObjetivos);
				}else
					pedidosObjetivosList.add(pedidosObjetivos);
				
			/*} catch (Exception e) {
				e.printStackTrace();
				flagError = true;
				flagErrorGlobal = new Boolean(true);
				break;
			}*/
		}
		
		flagError = flagErrorGeneral;
		
		return flagError;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#getExisteSeccionByZona(java.util.Map)
	 */
	public Integer getExisteSeccionByZona(Map criteria) {
		return procesoLETCargaPedidosObjetivosExcelDAO.getExisteSeccionByZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#getExisteZona(java.util.Map)
	 */
	public Integer getExisteZona(Map criteria) {
		return procesoLETCargaPedidosObjetivosExcelDAO.getExisteZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#executeCargaPedidosObjetivos(java.util.List)
	 */
	public void executeCargaPedidosObjetivos(List pedidosObjetivosList) {
		
		List pedidosObjetivosAll = procesoLETCargaPedidosObjetivosExcelDAO.getPedidosObjetivosAll();
		boolean isEncontro = false;
		Map temp;
		Map pedidosObjetivos;
		
		Map params;
		
		for(int i=0;i<pedidosObjetivosList.size();i++){
			temp = (Map)pedidosObjetivosList.get(i);
			isEncontro = false;
			for(int j=0;j<pedidosObjetivosAll.size();j++){
				pedidosObjetivos = (Map)pedidosObjetivosAll.get(j);
				if(((String)temp.get("codigoPais")).equals((String)pedidosObjetivos.get("codigoPais")) &&
				   (String.valueOf((Integer)temp.get("codigoPeriodo"))).equals((String)pedidosObjetivos.get("campanaProceso")) &&
				   ((String)temp.get("codigoConcurso")).equals((String)pedidosObjetivos.get("codigoConcurso")) &&
				   ((String)temp.get("codigoRegion")).equals((String)pedidosObjetivos.get("codigoRegion")) &&
				   ((String)temp.get("codigoZona")).equals((String)pedidosObjetivos.get("codigoZona")) &&
				   ((String)temp.get("codigoSeccion")).equals((String)pedidosObjetivos.get("codigoSeccion"))
				  ){
					isEncontro = true;
				}
			}
			if(isEncontro){
				procesoLETCargaPedidosObjetivosExcelDAO.updatePedidoObjetivo(temp);
			}else{
				procesoLETCargaPedidosObjetivosExcelDAO.insertPedidoObjetivo(temp);
			}
			
			params = new HashMap();
			
			params.put("codigoPais",(String)temp.get("codigoPais"));
			params.put("codigoPeriodo",String.valueOf((Integer)temp.get("codigoPeriodo")));
			params.put("codigoConcurso",(String)temp.get("codigoConcurso"));
			params.put("codigoRegion", (String)temp.get("codigoRegion"));
			params.put("codigoZona", (String)temp.get("codigoZona"));
			params.put("codigoSeccion", (String)temp.get("codigoSeccion"));
			params.put("numeroPeridosObjetivos", String.valueOf(((Integer)temp.get("numeroPeridosObjetivos")).intValue()));
			params.put("codigoUsuario", (String)temp.get("codigoUsuario"));
			
			procesoLETCargaPedidosObjetivosExcelDAO.executeCalculoMetasLideresCampana(params);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#getCodigoConcursoByPeriodo(java.util.Map)
	 */
	public String getCodigoConcursoByPeriodo(Map criteria) {
		return procesoLETCargaPedidosObjetivosExcelDAO.getCodigoConcursoByPeriodo(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#getZonaNuevaRezonificada(java.util.Map)
	 */
	public Integer getZonaNuevaRezonificada(Map criteria) {
		return procesoLETCargaPedidosObjetivosExcelDAO.getZonaNuevaRezonificada(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#getSeccioNueva(java.util.Map)
	 */
	public Integer getSeccioNueva(Map criteria) {
		return procesoLETCargaPedidosObjetivosExcelDAO.getSeccioNueva(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.ProcesoLETCargaPedidosObjetivosExcelService#getCampanaFinConcursoByCodigoConcurso(java.lang.String)
	 */
	public String getCampanaFinConcursoByCodigoConcurso(String codigoConcurso) {
		return procesoLETCargaPedidosObjetivosExcelDAO.getCampanaFinConcursoByCodigoConcurso(codigoConcurso);
	}
}