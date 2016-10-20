package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCPremiosElectivosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCPremiosElectivosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 * @author <a href="mailto:sbuchelli@csigcomt.com">Sergio Buchelli</a>
 *
 */
@Service("spusicc.mantenimientoINCPremiosElectivosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoINCPremiosElectivosServiceImpl extends BaseService implements
	MantenimientoINCPremiosElectivosService{

	@Resource(name="spusicc.mantenimientoINCPremiosElectivosDAO")
	MantenimientoINCPremiosElectivosDAO mantenimientoINCPremiosElectivosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#getListParametrosConcursosElectivos()
	 */
	public List getListParametrosConcursosElectivos() {
		return mantenimientoINCPremiosElectivosDAO.getListParametrosConcursosElectivos();
	}

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#executeValidacionesPremiosElectivos(java.util.Map)
	 */
	public void executeValidacionesPremiosElectivos(Map map) {
		 mantenimientoINCPremiosElectivosDAO.executeValidacionesPremiosElectivos(map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#getPremiosElectivos()
	 */
	public List getPremiosElectivos() {
		return mantenimientoINCPremiosElectivosDAO.getPremiosElectivos();
	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#insertPremioElectivo(java.util.Map)
	 */
	public void insertPremioElectivo(Map map) {
		mantenimientoINCPremiosElectivosDAO.insertPremioElectivo(map);
		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#insertPremiosElectivos(java.util.List, java.lang.String[])
	 */
	public void insertPremiosElectivos(List listPremiosElectivos, String[] items) {
	    //volviendo a insertar
        for(int i=0; i< items.length;i++){
        	log.debug("items "+ items[i]);
        	Map map=(Map)listPremiosElectivos.get(Integer.parseInt(items[i]));
        	if(i==0)
        		mantenimientoINCPremiosElectivosDAO.deletePremiosElectivos(map);//solo se borra los ind_pen=1
        	insertPremioElectivo(map);
        }		
	}

	public void insertPremiosElectivos(List listPremiosElectivos,
			String[] items, String[] unidadesItems) {
		log.debug("unidadesItems "+unidadesItems.length);
	  //volviendo a insertar	
        for(int i=0; items!=null && i< items.length;i++){
        	log.debug("items seleccionado "+ items[i]);
        	Map map=(Map)listPremiosElectivos.get(Integer.parseInt(items[i])-1);
        	//String numUnidades=String.valueOf(map.get("numUnidades"));
        	map.put("numUnidades",unidadesItems[Integer.parseInt(items[i])-1]);
        	log.debug("map " +map);
        	if(i==0) 
        	 mantenimientoINCPremiosElectivosDAO.deletePremiosElectivos(map);//solo se borra los ind_pen=1
        	String indicadorPendiente= String.valueOf(map.get("indicadorPendiente"));
        	if(Constants.NUMERO_UNO.equals(indicadorPendiente))
        		insertPremioElectivo(map);
        }			
	}

	public void insertPremiosElectivos(List listPremiosElectivos) {
		Iterator it = listPremiosElectivos.iterator();
		int i=0;
		while(it.hasNext()){
			Map map=(Map)it.next();
			log.debug("map INSERTAR " +map);
			if(i==0)
			 mantenimientoINCPremiosElectivosDAO.deletePremiosElectivos(map);//solo se borra los ind_pen=1
			String indicadorPendiente= String.valueOf(map.get("indicadorPendiente"));
			log.debug("indicador pendiente "+indicadorPendiente);
        	if(Constants.NUMERO_UNO.equals(indicadorPendiente)){
        		String indicadorValido= String.valueOf(map.get("indicadorValido"));
        		if( (indicadorValido.equals(Constants.NUMERO_UNO)) )
        			insertPremioElectivoInvalido(map);
        		else
        			insertPremioElectivo(map);
        		
        	}
        	i++;
		}
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#insertPremioElectivoInvalido(java.util.Map)
	 */
	public void insertPremioElectivoInvalido(Map map) {
		mantenimientoINCPremiosElectivosDAO.insertPremioElectivoInvalido(map);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#getListCodigoVentas(java.util.Map)
	 */
	public List<Map> getListCodigoVentas(Map<String, String> map) {
		return mantenimientoINCPremiosElectivosDAO.getListCodigoVentas(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#getLongitudCodVentaFicticio(java.lang.String)
	 */
	public String getLongitudCodVentaFicticio(String codigoPais) {
		return mantenimientoINCPremiosElectivosDAO.getLongitudCodVentaFicticio(codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#deletePremiosElectivos(java.util.Map)
	 */
	public void deletePremiosElectivos(Map map) {
		mantenimientoINCPremiosElectivosDAO.deletePremiosElectivos(map);//solo se borra los ind_pen=1
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#getListParametrosConcursosElectivosDiferido()
	 */
	public List getListParametrosConcursosElectivosDiferido() {
		return mantenimientoINCPremiosElectivosDAO.getListParametrosConcursosElectivosDiferido();
	}

	/**
	 * se encarga de parsear el valor correcto de los campos codigos que vengan con (.)
	 * @param next
	 * @return
	 */
	private Map getParseRowPremiosElectivos(Map mapRow) {
		 String codigoVenta =(String)mapRow.get("1");
		 String numeroUnidades =(String)mapRow.get("2");

		 mapRow.put("1",codigoVenta.substring(0,codigoVenta.indexOf(".")!=-1?codigoVenta.indexOf("."):codigoVenta.length()));
		 mapRow.put("2",numeroUnidades.substring(0,numeroUnidades.indexOf(".")!=-1?numeroUnidades.indexOf("."):numeroUnidades.length()));
		 
		 return mapRow;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#executeCargaPremiosElectivos(java.util.Map)
	 */
	public void executeCargaPremiosElectivos(Map params) throws Exception {
		String directorioTemporal = (String)params.get("directorioTemporal");
		String nombreArchivo = (String)params.get("nombreArchivo");
		String codigoUsuario = (String)params.get("codigoUsuario");
		
		String numeroConcurso = (String)params.get("numeroConcurso");
		
	    //Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
				
		List list = new ArrayList();
		
		//borramos la lista de registros activos para la campaa seleccionado
		mantenimientoINCPremiosElectivosDAO.deleteCargaPremiosElectivos(params);
		int fila = 1;
		
		while(excelUtil.hasNext()) { 
			Map mapRow = getParseRowPremiosElectivos(excelUtil.next());
			mapRow.put("numeroConcurso",numeroConcurso);
			mapRow.put("codigoUsuario",codigoUsuario);			
			mapRow.put("fila",fila);
			
			fila++;
			mantenimientoINCPremiosElectivosDAO.insertCargaPremiosElectivos(mapRow);			
		}
		excelUtil.cerrar();
		
		//hacemos el proceso de registro en batch
		mantenimientoINCPremiosElectivosDAO.executeCargaPremiosElectivos(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCPremiosElectivosService#deleteCargaPremiosElectivos(java.util.Map)
	 */
	public void deleteCargaPremiosElectivos(Map params) {
		mantenimientoINCPremiosElectivosDAO.deleteCargaPremiosElectivos(params);
	}
	
}
