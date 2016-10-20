/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.ocr.model.ConexionOCRWrapper;
import biz.belcorp.ssicc.dao.sisicc.InterfazRECDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.GenericoRECAnulacionesNMPSFacadeService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECGestionIngresoAnulacionNmpsSearchService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author peextjcairampoma
 * 
 */
@Service("spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECGestionIngresoAnulacionNmpsSearchServiceImpl extends BaseService implements MantenimientoRECGestionIngresoAnulacionNmpsSearchService{

	@Resource(name="spusicc.mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO")
	MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO;
	
	@Resource(name="sisicc.interfazRECDAO")
	InterfazRECDAO interfazRECDAO;
	
	@Resource(name="spusicc.genericoRECAnulacionesNMPSFacadeService")
	GenericoRECAnulacionesNMPSFacadeService genericoRECAnulacionesNMPSFacadeService;

	// ------------------------------------------NEW
	// ---------------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.
	 * MantenimientoRECGestionIngresoAnulacionNmpsSearchService
	 * #getConsultoraPedidoList(java.util.Map)
	 */
	public List getConsultoraPedidoList(Map params)
	{
		return mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.getConsultoraPedidoList(params);
	}

	/**
	 * @return the mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO
	 */
	public MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO getMantenimientoRECGestionIngresoAnulacionNmpsSearchDAO()
	{
		return mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO;
	}

	/**
	 * @param mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO
	 *            the mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO to
	 *            set
	 */
	public void setMantenimientoRECGestionIngresoAnulacionNmpsSearchDAO(MantenimientoRECGestionIngresoAnulacionNmpsSearchDAO mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO)
	{
		this.mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO = mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.
	 * MantenimientoRECGestionIngresoAnulacionNmpsSearchService
	 * #getCampanaList(java.util.Map)
	 */
	public List getCampanaList(Map params)
	{
		return mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.getCampanaList(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.
	 * MantenimientoRECGestionIngresoAnulacionNmpsSearchService
	 * #executeProcesoIngresoAnulacionNmps(java.util.Map)
	 */
	public void executeProcesoIngresoAnulacionNmps(Map params)
	{
		mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.executeProcesoIngresoAnulacionNmps(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.
	 * MantenimientoRECGestionIngresoAnulacionNmpsSearchService
	 * #executeProcesoGenerarArchivoIngresoAnulacionNmps(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void executeProcesoGenerarArchivoIngresoAnulacionNmps(Map params)
	{
		mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.executeProcesoGenerarArchivoIngresoAnulacionNmps(params);
		
		//se obtiene la lista de los valores de trama y pedido
		List tramaPedidoList= mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.getValTramaPedidoList(params);
		if(tramaPedidoList!=null && tramaPedidoList.size()>0){
			try {
				ConexionOCRWrapper conexion = interfazRECDAO.getDevuelveConexionOCR(params);
				
				if(MapUtils.getString(params, "estadoConexion")!=null && StringUtils.equals(MapUtils.getString(params, "estadoConexion"), Constants.OK_MESSAGE))
				{
				
					Connection cn = genericoRECAnulacionesNMPSFacadeService.obtenerConexion(conexion, params);
					
					for (int i=0;i<tramaPedidoList.size();i++){
						
							//obtenemos valPedido y valTrama
							Map map= (HashMap)tramaPedidoList.get(i);
							String valTrama = MapUtils.getString(map, "valTrama");
							params.put("valTrama", valTrama);
							String valPedido = MapUtils.getString(map, "valPedido");
							params.put("valPedido", valPedido);
							//se realiza la conexion y se ejecuta el procedimiento almacenado de la otra base de datos
							genericoRECAnulacionesNMPSFacadeService.excuteSpWCSAPE(conexion, cn, params);
							//se realiza el update del valor de trama y pedido
							mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.updateValTramaPedido(params);
						
					}
					genericoRECAnulacionesNMPSFacadeService.cerrarConexion(conexion, cn, params);
				}
			} catch (Exception e) {
				log.error("Error en executeProcesoGenerarArchivoIngresoAnulacionNmps " + e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.
	 * MantenimientoRECGestionIngresoAnulacionNmpsSearchService
	 * #getMotivosDevolucion(java.util.Map)
	 */
	public List getMotivosDevolucion(Map params)
	{
		return mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.getMotivosDevolucion(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.
	 * MantenimientoRECGestionIngresoAnulacionNmpsSearchService
	 * #getTiposOperacionIngresoAnulaciones()
	 */
	public List getTiposOperacionIngresoAnulaciones(Map params)
	{
		return mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.getTiposOperacionIngresoAnulaciones(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.
	 * MantenimientoRECGestionIngresoAnulacionNmpsSearchService
	 * #executeCargaArchivoExcel(java.util.Map)
	 */
	public List executeCargaArchivoExcel(Map criteria) throws Exception
	{

		String directorioTemporal = (String) criteria.get("directorioTemporal");
		String nombreArchivo = (String) criteria.get("nombreArchivo");
		String CadenaFecha = "";

		try
		{
			Date FechaActual = new Date();
			SimpleDateFormat Formato = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
			CadenaFecha = Formato.format(FechaActual);

		}
		catch (Exception e)
		{
			log.debug("Error en fecha:" + e.getMessage());
		}

		// Abrimos el archivo Excel para su procesamiento
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		// nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);

		int numfila = 0;
		int indice = 0;

		List listaAnular = new ArrayList();

		while (excelUtil.hasNext())
		{
			Map mapRow = excelUtil.next();
			Map params = new HashMap();

			if (numfila > 0)
			{
				if (StringUtils.isNotEmpty((String) mapRow.get("0")))
				{
					params.put("cod_usua", criteria.get("cod_usua"));
					params.put("fec_ingr", CadenaFecha);
					params.put("cod_pais", criteria.get("cod_pais"));
					params.put("cod_est_proc", criteria.get("cod_est_proc"));
					params.put("cod_error", criteria.get("cod_error"));
					
					params.put("num_doc", (String) mapRow.get("0"));
					params.put("cod_clie", (String) mapRow.get("1"));
					// String nmp =(String)mapRow.get("2");
					String numero = (String) mapRow.get("2");	
					if (StringUtils.isBlank(numero)) {
						numero = (String) mapRow.get("0");	
					}
					else {
						try{
							numero = numero.substring(0,numero.indexOf("."));
						}
						catch(Exception e) {
							numero = (String) mapRow.get("2");
						}
					}	
					try{
						
						Long nmp = Long.parseLong(numero);
						params.put("num_nmp",numero);
					
					}catch(Exception e){
						params.put("num_nmp", "");
					}
					
					
					if(!StringUtils.equalsIgnoreCase(numero, Constants.NUMERO_CERO)){
							params.put("num_recl", numero);
					}else{
							params.put("num_recl", (String) mapRow.get(""));
					}
					
						
//					if (nmp > 0)
//						params.put("num_nmp", (String) mapRow.get("2"));
//					else
//						params.put("num_nmp", "0");

					params.put("observaciones", (String) mapRow.get("3"));
					// log.debug("num_doc--->:"+(String)mapRow.get("0"));
					// log.debug("cod_clie-->:"+(String)mapRow.get("1"));

					if (listaAnular.size() > 0)
					{

						Map objeto = null;
						indice = 0;

						for (int j = 0; j < listaAnular.size(); j++)
						{
							objeto = (Map) listaAnular.get(j);

							// log.debug("Num Doc A--->:"+objeto.get("num_doc"));
							// log.debug("Num Doc B--->:"+params.get("num_doc"));
							// log.debug("Cod Clie A--->:"+objeto.get("cod_clie"));
							// log.debug("Cod Clie B--->:"+params.get("cod_clie"));

							if (objeto.get("num_doc").equals(params.get("num_doc")) && objeto.get("cod_clie").equals(params.get("cod_clie")))
							{
								indice = j;
								break;
							}

						}

						// log.debug("Indice -->:"+indice);

						if (indice == 0)
						{
							listaAnular.add(params);
						}

					}
					else
					{
						listaAnular.add(params);
					}

				}
			}
			++numfila;
		}

		excelUtil.cerrar();

		return listaAnular;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECGestionIngresoAnulacionNmpsSearchService#getNumeroLote()
	 */
	public String getNumeroLote() {
		return mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.getNumeroLote();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECGestionIngresoAnulacionNmpsSearchService#getIndicadorNotaMercaderiaPerdida(java.util.Map)
	 */
	public String getIndicadorNotaMercaderiaPerdida(Map params){
		return mantenimientoRECGestionIngresoAnulacionNmpsSearchDAO.getIndicadorNotaMercaderiaPerdida(params);
	}
}
