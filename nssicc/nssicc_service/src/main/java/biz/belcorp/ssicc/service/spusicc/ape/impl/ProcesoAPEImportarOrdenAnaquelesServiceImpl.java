package biz.belcorp.ssicc.service.spusicc.ape.impl;

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
import biz.belcorp.ssicc.dao.spusicc.ape.ProcesoAPEImportarOrdenAnaquelesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.ProcesoAPEImportarOrdenAnaquelesService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * Service que ejecutara los metodos de importacin de orden de Anaqueles
 *  
 * <p>
 * <a href="ProcesoAPEImportarOrdenAnaquelesServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoAPEImportarOrdenAnaquelesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoAPEImportarOrdenAnaquelesServiceImpl extends BaseService implements ProcesoAPEImportarOrdenAnaquelesService {

	@Resource(name="spusicc.procesoAPEImportarOrdenAnaquelesDAO")
	private ProcesoAPEImportarOrdenAnaquelesDAO procesoAPEImportarOrdenAnaquelesDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEImportarOrdenAnaquelesService#getOrdenAnaquel(java.util.Map)
	 */
	public Map getOrdenAnaquel(Map criteria) {
		return procesoAPEImportarOrdenAnaquelesDAO.getOrdenAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEImportarOrdenAnaquelesService#getSubLineaOrdenAnaquel(java.util.Map)
	 */
	public List getSubLineaOrdenAnaquel(Map criteria) {
		return procesoAPEImportarOrdenAnaquelesDAO.getSubLineaOrdenAnaquel(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEImportarOrdenAnaquelesService#cargarArchivoExcel(java.util.Map)
	 */
	public List cargarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String oidSubLineaArmado = (String)criteria.get("oidSubLineaArmado");
		String oidMapaCD = (String)criteria.get("oidMapaCD");
		String oidOrdenAnaquel = (String)criteria.get("oidOrdenAnaquel");
		String ordenado = (String)criteria.get("ordenado");
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		
		String codigoAnaquel = null;
		String numeroOrden = null;
		List lstAnaqueles = new ArrayList();
		int fila = 0;
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			fila = fila + 1;
			
			//Recuperamos el codigo de Anaquel y numero Orden
			codigoAnaquel = (String)mapRow.get("0");
			numeroOrden = (String)mapRow.get("1");

			int varIntDato = numeroOrden.indexOf(".0");
			if (varIntDato >= 0 && (varIntDato+2 == numeroOrden.length())){
				numeroOrden = (numeroOrden).substring(0, varIntDato);
			}
			
			//Lo almacenamos en una lista temporal
			Map mapFila = new HashMap();
			mapFila.put("numeroAnaquel", codigoAnaquel);
			mapFila.put("numeroOrden", numeroOrden);
			mapFila.put("indicadorExpandido", "");
			mapFila.put("anaquelDestino", "");
			mapFila.put("observaciones", "");
			
			lstAnaqueles.add(mapFila);
		}
		excelUtil.cerrar();

		//1) Validamos que la misma Cantidad de Datos del Excel sea igual al de filas a insertar/actualizar en BD
		Map criteriaAux = new HashMap();
		criteriaAux.put("oidMapaCD", oidMapaCD);
		criteriaAux.put("oidSubLineaArmado", oidSubLineaArmado);
		criteriaAux.put("oidOrdenAnaquel", oidOrdenAnaquel);
		
		List lstAnaquelesBD = new ArrayList();
		if(ordenado.equals(Constants.STO_TIPO_GESTIONABLE_NO))
			lstAnaquelesBD = procesoAPEImportarOrdenAnaquelesDAO.getDetalleMapaCDBySubLinea(criteriaAux);
		else
			lstAnaquelesBD = procesoAPEImportarOrdenAnaquelesDAO.getDetalleOrdenAnaquelBySubLinea(criteriaAux);
		
		String resultado = "0";
		if(lstAnaqueles.size() != lstAnaquelesBD.size()) {
			resultado = "1";
		} else {
			
			Map mapAnaquelesBD = new HashMap();
			Iterator it = lstAnaquelesBD.iterator();
			while(it.hasNext()) {
				Map mapBD = (Map)it.next();
				mapAnaquelesBD.put((String)mapBD.get("numeroAnaquel"), mapBD);
			}
			
			it = lstAnaqueles.iterator();
			while(it.hasNext()) {
				Map mapExcel = (Map)it.next();
				String numeroAnaquel = (String)mapExcel.get("numeroAnaquel");
				
				if(mapAnaquelesBD.get(numeroAnaquel)!=null) {
					Map mapBD = (Map)mapAnaquelesBD.get(numeroAnaquel);
					mapExcel.put("indicadorExpandido", (String)mapBD.get("indicadorExpansion"));
					mapExcel.put("anaquelDestino", (String)mapBD.get("anaquelDestino"));
				}
			}	
		}

		criteria.put("resultado", resultado);
		
		return lstAnaqueles;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEImportarOrdenAnaquelesService#executeImportarOrdenAnaqueles(java.util.Map)
	 */
	public void executeImportarOrdenAnaqueles(Map criteria) throws Exception {
		String oidMapaCD = (String)criteria.get("oidMapaCD");
		String oidOrdenAnaquel = (String)criteria.get("oidOrdenAnaquel");
		String ordenado = (String)criteria.get("ordenado");
		String oidSubLineaArmado = (String)criteria.get("oidSubLineaArmado");
		List lstAnaqueles = (List)criteria.get("listaAnaqueles");
		
		List lstAnaquelesBD = new ArrayList();
		Map criteriaAux = new HashMap();
		criteriaAux.put("oidMapaCD", oidMapaCD);
		criteriaAux.put("oidSubLineaArmado", oidSubLineaArmado);
		criteriaAux.put("oidOrdenAnaquel", oidOrdenAnaquel);
		
		if(ordenado.equals(Constants.STO_TIPO_GESTIONABLE_NO))
			lstAnaquelesBD = procesoAPEImportarOrdenAnaquelesDAO.getDetalleMapaCDBySubLinea(criteriaAux);
		else
			lstAnaquelesBD = procesoAPEImportarOrdenAnaquelesDAO.getDetalleOrdenAnaquelBySubLinea(criteriaAux);
		
		boolean valida = validacionAnaqueles(lstAnaqueles, lstAnaquelesBD);
		
		//Si la validacion es correcta, se procede a la grabacion de los datos
		if(valida) {
			if(ordenado.equals(Constants.STO_TIPO_GESTIONABLE_NO))
				insertOrdenAnaquelDetalle(oidOrdenAnaquel, lstAnaqueles);
			else
				updateOrdenAnaquelDetalle(oidOrdenAnaquel, lstAnaqueles);
		}

		criteria.put("resultado", valida==true?"0":"1");
	}
	
	/**
	 * @param lstAnaqueles
	 * @param lstAnaquelesBD
	 * @return
	 */
	private boolean validacionAnaqueles(List lstAnaqueles, List lstAnaquelesBD) {
		boolean valida = true;
		
		//cargamos la lista recuperada de BD		
		Map mapAnaquelesBD = new HashMap();
		Iterator it = lstAnaquelesBD.iterator();
		while(it.hasNext()) {
			Map mapBD = (Map)it.next();
			mapAnaquelesBD.put((String)mapBD.get("numeroAnaquel"), mapBD);
		}

		Map mapAnaquel = new HashMap();
		Map mapOrden = new HashMap();
		
		it = lstAnaqueles.iterator();
		while(it.hasNext()) {
			Map mapExcel = (Map)it.next();
			String numeroAnaquel = (String)mapExcel.get("numeroAnaquel");
			String numeroOrden = (String)mapExcel.get("numeroOrden");
			String validacion="";
			
			//2) Que no se repita codigo anaquel recibido del Excel
			if (mapAnaquel.get(numeroAnaquel)!=null)  {
				validacion=validacion + "2,";
			} else {
				mapAnaquel.put(numeroAnaquel, numeroAnaquel);
			}
			
			//3) Que numero de Orden sea numerico
			try {
				Long.valueOf(numeroOrden);
			} catch(Exception exception) {
				validacion=validacion + "3,";
			}
			
			//4) Que no se repita numeroOrden recibido del Excel
			if (mapOrden.get(numeroOrden)!=null)  {
				validacion=validacion + "4,";
			} else {
				mapOrden.put(numeroOrden, numeroOrden);
			}
			
			//5) Que el codigo de anaquel recibido sea igual al de la SubLinea y Orden Anaquel Seleccionado
			if (mapAnaquelesBD.get(numeroAnaquel)==null)  {
				validacion=validacion + "5,";
			} else {
				Map mapBD = (Map)mapAnaquelesBD.get(numeroAnaquel);
				mapExcel.put("oidDetalleOrden", mapBD.get("oidDetalleOrden"));
				mapExcel.put("oidDetalleMapaCD", mapBD.get("oidDetalleMapaCD"));
			}
			
			if (validacion.length() > 0) {
				valida = false;
			}
			
			mapExcel.put("observaciones", validacion);
		}
		
		return valida;
	}
	
	/**
	 * @param oidOrdenAnaquel
	 * @param lstAnaqueles
	 */
	private void insertOrdenAnaquelDetalle(String oidOrdenAnaquel, List lstAnaqueles) {
		Iterator it = lstAnaqueles.iterator();
		
		while(it.hasNext()) {
			Map mapRegistro = (Map)it.next();
			
			mapRegistro.put("oidOrdenAnaquel", oidOrdenAnaquel);
			procesoAPEImportarOrdenAnaquelesDAO.insertDetalleOrdenAnaquel(mapRegistro);
		}	
	}

	/**
	 * @param oidOrdenAnaquel
	 * @param lstAnaqueles
	 */
	private void updateOrdenAnaquelDetalle(String oidOrdenAnaquel, List lstAnaqueles) {
		Iterator it = lstAnaqueles.iterator();
		
		while(it.hasNext()) {
			Map mapRegistro = (Map)it.next();
			procesoAPEImportarOrdenAnaquelesDAO.updateDetalleOrdenAnaquel(mapRegistro);
		}	
	}

	/**
	 * @param procesoAPEImportarOrdenAnaquelesDAO The procesoAPEImportarOrdenAnaquelesDAO to set.
	 */
	public void setProcesoAPEImportarOrdenAnaquelesDAO(
			ProcesoAPEImportarOrdenAnaquelesDAO procesoAPEImportarOrdenAnaquelesDAO) {
		this.procesoAPEImportarOrdenAnaquelesDAO = procesoAPEImportarOrdenAnaquelesDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEImportarOrdenAnaquelesService#getTipoSolConsolidado(java.util.Map)
	 */
	public List getTipoSolConsolidado(Map params) {
		return this.procesoAPEImportarOrdenAnaquelesDAO.getTipoSolConsolidado(params);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.ape.service.ProcesoAPEImportarOrdenAnaquelesService#actualizarIndicador(java.util.Map)
     */
    public void actualizarIndicador(Map params){
    	procesoAPEImportarOrdenAnaquelesDAO.actualizarIndicador(params);
    }
	
}

