package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEModificarMaterialesVistaDAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.MaterialesVista;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEModificarMaterialesVistaService;
import biz.belcorp.ssicc.service.util.ExcelUtil;
/**
 *  
 * <p>
 * <a href="MantenimientoAPEModificarMaterialesVistaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEModificarMaterialesVistaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEModificarMaterialesVistaServiceImpl extends BaseService implements MantenimientoAPEModificarMaterialesVistaService{
	
	@Resource(name="spusicc.mantenimientoAPEModificarMaterialesVistaDAO")
	private MantenimientoAPEModificarMaterialesVistaDAO mantenimientoAPEModificarMaterialesVistaDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getTipoDispensacionList(java.util.Map)
	 */
	public List getTipoDispensacionList(Map criteria) {
		return mantenimientoAPEModificarMaterialesVistaDAO.getTipoDispensacionList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getMaterialesVistaList(java.util.Map)
	 */
	public List getMaterialesVistaList(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getMaterialesVistaList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getMaterialesVistaObject(java.util.Map)
	 */
	public MaterialesVista getMaterialesVistaObject(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getMaterialesVistaObject(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getCodTipoCajaProductoList(java.util.Map)
	 */
	public List getCodTipoCajaProductoList(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getCodTipoCajaProductoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#updateMaterialesVista(java.util.Map)
	 */
	public void updateMaterialesVista(Map criteria){
		mantenimientoAPEModificarMaterialesVistaDAO.updateMaterialesVista(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getOidTipoDispensacionByCodigo(java.util.Map)
	 */
	public String getOidTipoDispensacionByCodigo(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoDispensacionByCodigo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getOidTipoAnaquelbyCodigo(java.util.Map)
	 */
	public String getOidTipoAnaquelbyCodigo(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoAnaquelbyCodigo(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getTipoAnaquelProductoList(java.util.Map)
	 */
	public List getTipoAnaquelProductoList(Map criteria) {
		return mantenimientoAPEModificarMaterialesVistaDAO.getTipoAnaquelProductoList(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#insertTipoAnaquelProducto(java.util.Map)
	 */
	public void insertTipoAnaquelProducto(String OidSap, String NumeroPrioridad, String OidTipoAnaquel, List listAnaqueles){
		
		boolean flagEncontro = false;
		String sNumPrio = "";
		Integer NumPrio = Integer.parseInt(NumeroPrioridad);
		String sCodTipoAnaquel = "";
		Map criteria = new HashMap();
		
		mantenimientoAPEModificarMaterialesVistaDAO.deleteTipoAnaquelbyOidProducto(OidSap);
		
		criteria.put("oidSAP", OidSap);
		criteria.put("numPrioridad", NumeroPrioridad);
		criteria.put("oidTipoAnaquel", OidTipoAnaquel);
		mantenimientoAPEModificarMaterialesVistaDAO.insertTipoAnaquelProducto(criteria);
		
		for (int i=0;i<listAnaqueles.size(); i++){
		    criteria = (Map)listAnaqueles.get(i);
			
			sNumPrio = (String)criteria.get("numPrioridad");
			sCodTipoAnaquel = (String)criteria.get("codTipoAnaquel");
			criteria.put("oidSAP", OidSap);
			criteria.put("codigoTipoAnaquel", sCodTipoAnaquel);
			criteria.put("oidTipoAnaquel", mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoAnaquelbyCodigo(criteria));
			
			if (sNumPrio.equals(NumeroPrioridad)){
				flagEncontro = true;
			}
			
			if (flagEncontro){
				NumPrio = NumPrio + 10;
				sNumPrio = NumPrio.toString();
			}
				
			criteria.put("numPrioridad", sNumPrio);	
				
			mantenimientoAPEModificarMaterialesVistaDAO.insertTipoAnaquelProducto(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#deleteTipoAnaquelProducto(java.util.Map)
	 */
	public void deleteTipoAnaquelProducto(Map criteria){
		mantenimientoAPEModificarMaterialesVistaDAO.deleteTipoAnaquelProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#updateTipoAnaquelProducto(java.util.Map)
	 */
	public void updateTipoAnaquelProducto( List listAnaqueles){
		
		Map criteria = new HashMap();
		
		String sNumPrio = "";
		Integer NumPrio = 0;
		
		for (int i=0;i<listAnaqueles.size(); i++){
		    criteria = (Map)listAnaqueles.get(i);
		    
			NumPrio = NumPrio + 10;
			sNumPrio = NumPrio.toString();
			criteria.put("numPrioridad", sNumPrio);
			
			mantenimientoAPEModificarMaterialesVistaDAO.updateTipoAnaquelProducto(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getExisteTipoAnaquelProducto(java.util.Map)
	 */
	public String getExisteTipoAnaquelProducto(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getExisteTipoAnaquelProducto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#insertTipoAnaquelProducto(java.lang.String, java.util.List)
	 */
	public void insertTipoAnaquelProducto(String OidSap, List listAnaqueles){
		
		Map criteria = new HashMap();
		String sCodTipoAnaquel = "";
		String sNumPrio = "";
		Integer NumPrio = 0;
		
		mantenimientoAPEModificarMaterialesVistaDAO.deleteTipoAnaquelbyOidProducto(OidSap);
		
		for (int i=0;i<listAnaqueles.size(); i++){
		    criteria = (Map)listAnaqueles.get(i);
		    sCodTipoAnaquel = (String)criteria.get("codTipoAnaquel");
			criteria.put("oidSAP", OidSap);
			criteria.put("codigoTipoAnaquel", sCodTipoAnaquel);
			criteria.put("oidTipoAnaquel", mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoAnaquelbyCodigo(criteria));
		    
			NumPrio = NumPrio + 10;
			sNumPrio = NumPrio.toString();
			criteria.put("numPrioridad", sNumPrio);
			
			mantenimientoAPEModificarMaterialesVistaDAO.insertTipoAnaquelProducto(criteria);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getMaxNumeroPrioridad(java.util.Map)
	 */
	public int getMaxNumeroPrioridad(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getMaxNumeroPrioridad(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#executeCargaArchivoExcel(java.util.Map)
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String oidPais = (String)criteria.get("oidPais");
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		List list = null;
		List listaError = null;
		
		int numfila=0;
		String codigoProducto = "";
		String oidProducto = "";
		BigDecimal codSAP = new BigDecimal(0);
		
		Map map = new HashMap();
		map.put("oidPais", oidPais);

		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			++numfila;

			if(list==null) list = new ArrayList();

			if (numfila > 1){
				if(StringUtils.isNotEmpty((String)mapRow.get("0"))){
					codigoProducto = (String)mapRow.get("0");
					codSAP = new BigDecimal(codigoProducto);
					map.put("codSap", codSAP.longValue());
					mapRow.put("0", codSAP.longValue());
					oidProducto = mantenimientoAPEModificarMaterialesVistaDAO.getOidProductoAPE(map);
					map.put("oidSAP", oidProducto);
					
					mapRow.put("oidPais", oidPais);
					mapRow.put("oidSAP", oidProducto);
					
					list.add(mapRow);
				}
			}
		}
		
		listaError = actualizarProductos(list);
		
		excelUtil.cerrar();
		
		return listaError;
	}
	
	/**
	 * Actualiza el maestro de Productos de APE
	 * @param list
	 * @return
	 */
	private List actualizarProductos(List list){
		Iterator listIterator = list.iterator();
		String sValError = "";
		long codigoProducto = 0;
		List listaError = null;
		
		while (listIterator.hasNext()) {
			Map dataInsert = (Map) listIterator.next();
			log.debug("dataInsert " + dataInsert);
			
			//Se llama al procedure para realizar las validaciones
			Map resultadoMap = obtieneMapProductosCarga(dataInsert);
			mantenimientoAPEModificarMaterialesVistaDAO.validaProductos(resultadoMap);
			sValError = (String) resultadoMap.get("valError");
			log.debug("sValError " + sValError);
			
			if (sValError.equals("0")){
				//Si esta todo OK se realiza la asignacion del producto al anaquel
				resultadoMap.put("oidTipoAnaquel", mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoAnaquelbyCodigo(resultadoMap));
				resultadoMap.put("oidCajaMaestra", mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoCajaProducto(resultadoMap));
				resultadoMap.put("oidTipoDispensacion", mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoDispensacionByCodigo(resultadoMap));
				mantenimientoAPEModificarMaterialesVistaDAO.updateMaterialesVista(resultadoMap);
				//Se actuliza el numero de Prioridad del Tipo de Anaquel 
				mantenimientoAPEModificarMaterialesVistaDAO.executeActulizaNumeroPrioridad(resultadoMap);
			}
			else{
				if(listaError==null) listaError = new ArrayList();
				
				Map mapError = new HashMap();
				codigoProducto =  (Long)dataInsert.get("0");
				mapError.put("codigoProducto", codigoProducto);
				mapError.put("descripcionError", sValError);
				
				listaError.add(mapError);
			}
		}
		
		return listaError;
	}
	
	/**
	 * Obtien el Map para actualizar
	 * @param criteria
	 */
	public Map obtieneMapProductosCarga ( Map criteria ){
		Map params = new HashMap();
		
		String oidPais =(String)criteria.get("oidPais");
		String oidSAP = (String)criteria.get("oidSAP");
		Long codSAP = (Long)criteria.get("0");
		String codigoTipoDispensacion = (String) criteria.get("1");
		String codCajaMaestra = (String)criteria.get("2");
		String codTipoAnaquel = (String)criteria.get("3");
		String uniCajaMaestra = (String)criteria.get("4");
		String numHoraInventario = (String)criteria.get("5");
		String holguraCubicaje = (String)criteria.get("6");
		String numAsigMaxChanel = (String)criteria.get("7");
		String screenQTY = (String)criteria.get("8");
		String pickSpeed = (String)criteria.get("9");
		String packSize = (String)criteria.get("10");
		String largo = (String)criteria.get("11");
		String ancho = (String)criteria.get("12");
		String alto = (String)criteria.get("13");
		String lane95 = (String)criteria.get("14");
		String lane60 = (String)criteria.get("15");
		String uniCajaGrande = (String)criteria.get("16");
		String uniCajaPequena = (String)criteria.get("17");
		
		params.put("oidPais", oidPais);
		params.put("oidSAP", oidSAP);
		params.put("codSAP", codSAP.toString());
		params.put("codigoTipoDispensacion", 
				codigoTipoDispensacion.substring(0,codigoTipoDispensacion.indexOf(".")!=-1?codigoTipoDispensacion.indexOf("."):codigoTipoDispensacion.length()));
		//params.put("oidTipoDispensacion", mantenimientoAPEModificarMaterialesVistaDAO.getOidTipoDispensacionByCodigo(params));
		params.put("codigoTipoCaja", 
				codCajaMaestra.substring(0,codCajaMaestra.indexOf(".")!=-1?codCajaMaestra.indexOf("."):codCajaMaestra.length()));
		params.put("codigoTipoAnaquel", codTipoAnaquel );
		params.put("uniCajaMaestra", 
				uniCajaMaestra.substring(0,uniCajaMaestra.indexOf(".")!=-1?uniCajaMaestra.indexOf("."):uniCajaMaestra.length()));
		params.put("numHoraInventario", 
				numHoraInventario.substring(0,numHoraInventario.indexOf(".")!=-1?numHoraInventario.indexOf("."):numHoraInventario.length()));
		params.put("holguraCubicaje", 
				holguraCubicaje.substring(0,holguraCubicaje.indexOf(".")!=-1?holguraCubicaje.indexOf("."):holguraCubicaje.length()));
		params.put("numAsigMaxChanel", 
				numAsigMaxChanel.substring(0,numAsigMaxChanel.indexOf(".")!=-1?numAsigMaxChanel.indexOf("."):numAsigMaxChanel.length()));
		params.put("screenQTY", 
				screenQTY.substring(0,screenQTY.indexOf(".")!=-1?screenQTY.indexOf("."):screenQTY.length()));
		params.put("pickSpeed", 
				pickSpeed.substring(0,pickSpeed.indexOf(".")!=-1?pickSpeed.indexOf("."):pickSpeed.length()));
		params.put("packSize", 
				packSize.substring(0,packSize.indexOf(".")!=-1?packSize.indexOf("."):packSize.length()));
		params.put("largo", 
				largo.substring(0,largo.indexOf(".")!=-1?largo.indexOf("."):largo.length()));
		params.put("ancho", 
				ancho.substring(0,ancho.indexOf(".")!=-1?ancho.indexOf("."):ancho.length()));
		params.put("alto", 
				alto.substring(0,alto.indexOf(".")!=-1?alto.indexOf("."):alto.length()));
		params.put("lane95", 
				lane95.substring(0,lane95.indexOf(".")!=-1?lane95.indexOf("."):lane95.length()));
		params.put("lane60", 
				lane60.substring(0,lane60.indexOf(".")!=-1?lane60.indexOf("."):lane60.length()));
		params.put("uniCajaGrande", 
				uniCajaGrande.substring(0,uniCajaGrande.indexOf(".")!=-1?uniCajaGrande.indexOf("."):uniCajaGrande.length()));
		params.put("uniCajaPequena", 
				uniCajaPequena.substring(0,uniCajaPequena.indexOf(".")!=-1?uniCajaPequena.indexOf("."):uniCajaPequena.length()));
		
		return params;
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEModificarMaterialesVistaService#getDesUnidadMedidaAPE(java.util.Map)
	 */
	public String getDesUnidadMedidaAPE(Map criteria){
		return mantenimientoAPEModificarMaterialesVistaDAO.getDesUnidadMedidaAPE(criteria);
	}
}