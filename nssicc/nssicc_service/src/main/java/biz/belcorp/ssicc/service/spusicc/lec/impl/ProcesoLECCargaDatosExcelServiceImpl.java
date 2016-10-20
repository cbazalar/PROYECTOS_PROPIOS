package biz.belcorp.ssicc.service.spusicc.lec.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.lec.ProcesoLECCargaDatosExcelDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

@Service("spusicc.procesoLECCargaDatosExcelService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoLECCargaDatosExcelServiceImpl extends BaseService implements ProcesoLECCargaDatosExcelService{
	
	private static final String ACTUALIZACION_ESTATUS_REENVÍO_TARJETA = "18";
	
	@Resource(name="spusicc.procesoLECCargaDatosExcelDAO")
	private ProcesoLECCargaDatosExcelDAO procesoLECCargaDatosExcelDAO; 
	
	public List getTipoCarga(Map map){
		return procesoLECCargaDatosExcelDAO.getTipoCarga(map);
	}


	
	public List getPrograma(Map map){		
		return procesoLECCargaDatosExcelDAO.getPrograma(map);
	}
	
	private String getNumeroCarga() {
		return procesoLECCargaDatosExcelDAO.getNumeroCarga();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {
		Map resultado = new HashMap();
	 	String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String tipoCarga = (String)criteria.get("tipoCarga");
		String codigoPeriodo = (String)criteria.get("codigoPeriodo");
		String codigoPrograma = (String)criteria.get("codigoPrograma");
		String codigoUsuario = ((Usuario)criteria.get("usuario")).getLogin();
		String prefijo = (String)criteria.get("prefijo");
		String codigoPais= (String)criteria.get("codigoPais");

		try{
		
			//Abrimos el archivo Excel para su procesamiento		
			ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
			excelUtil.setManejarConBigDecimal(true);
	
			//nos colocamos en la primera hora del documento Excel
			excelUtil.initSheet(0);		
			excelUtil.next();
			
			//recupera el numero de carga
			String numeroCarga = getNumeroCarga();
					
			int totalInsertados=0;
			int fila=0;
			int existen=0;
			int noExisten=0;
			int insert=0;
			Map params = new HashMap();		
			while(excelUtil.hasNext()) {
				Map mapRow = excelUtil.next();
				fila +=1;
				if (ACTUALIZACION_ESTATUS_REENVÍO_TARJETA.equals(tipoCarga)) {
					String ValorDato1 = (StringUtils.isBlank((String)mapRow.get("0"))?"":StringUtils.remove((String)mapRow.get("0"), ".0"));
					ValorDato1 = StringUtils.trim(ValorDato1);
					if(ValorDato1.compareTo("")==0 )	{
							
					}
					else {
						params.put("ValorDato1", ValorDato1);	
						if(ValorDato1.compareTo("")==0)					params.put("ValorDato1", null);
						params.put("numeroCarga", Integer.valueOf(numeroCarga));			    
					    params.put("codigoPeriodo", codigoPeriodo);			    
					    params.put("tipoCarga", tipoCarga);
					    params.put("codigoPrograma", codigoPrograma);
					    params.put("numeroFila", Integer.valueOf(fila));
						params.put("codigoUsuario", codigoUsuario);
						params.put("codigoPais", codigoPais);
						
						insert=procesoLECCargaDatosExcelDAO.insertCargaDatos(params);
						if(insert==1)
							totalInsertados++;
						insert=0;					
					}
				}
				else {
					String ValorDato1 = (StringUtils.isBlank((String)mapRow.get("0"))?"":StringUtils.remove((String)mapRow.get("0"), ".0"));
					String ValorDato2 = (StringUtils.isBlank((String)mapRow.get("1"))?"":StringUtils.remove((String)mapRow.get("1"), ".0"));
					String ValorDato3 = (StringUtils.isBlank((String)mapRow.get("2"))?"":StringUtils.remove((String)mapRow.get("2"), ".0"));
					String ValorDato4 = (StringUtils.isBlank((String)mapRow.get("3"))?"":StringUtils.remove((String)mapRow.get("3"), ".0"));
					String ValorDato5 = (StringUtils.isBlank((String)mapRow.get("4"))?"":StringUtils.remove((String)mapRow.get("4"), ".0"));
					String ValorDato6 = (StringUtils.isBlank((String)mapRow.get("5"))?"":StringUtils.remove((String)mapRow.get("5"), ".0"));
					String ValorDato7 = (StringUtils.isBlank((String)mapRow.get("6"))?"":StringUtils.remove((String)mapRow.get("6"), ".0"));
					String ValorDato8 = (StringUtils.isBlank((String)mapRow.get("7"))?"":StringUtils.remove((String)mapRow.get("7"), ".0"));
					String ValorDato9 = (StringUtils.isBlank((String)mapRow.get("8"))?"":StringUtils.remove((String)mapRow.get("8"), ".0"));
					String ValorDato10 = (StringUtils.isBlank((String)mapRow.get("9"))?"":StringUtils.remove((String)mapRow.get("9"), ".0"));
					ValorDato1 = StringUtils.trim(ValorDato1);
					ValorDato2 = StringUtils.trim(ValorDato2);
					ValorDato3 = StringUtils.trim(ValorDato3);
					ValorDato4 = StringUtils.trim(ValorDato4);
					ValorDato5 = StringUtils.trim(ValorDato5);
					ValorDato6 = StringUtils.trim(ValorDato6);
					ValorDato7 = StringUtils.trim(ValorDato7);
					ValorDato8 = StringUtils.trim(ValorDato8);
					ValorDato9 = StringUtils.trim(ValorDato9);
					ValorDato10 = StringUtils.trim(ValorDato10);				
					if(ValorDato1.compareTo("")==0 && ValorDato2.compareTo("")==0 && ValorDato3.compareTo("")==0 
						&& ValorDato4.compareTo("")==0 && ValorDato5.compareTo("")==0
						&& ValorDato6.compareTo("")==0 && ValorDato7.compareTo("")==0 && ValorDato8.compareTo("")==0 
						&& ValorDato9.compareTo("")==0 && ValorDato10.compareTo("")==0)	{
						
					}
					else {				
						params.put("ValorDato1", ValorDato1);				
						params.put("ValorDato2", ValorDato2);
						params.put("ValorDato3", ValorDato3);
						params.put("ValorDato4", ValorDato4);
						params.put("ValorDato5", ValorDato5);
						params.put("ValorDato6", ValorDato6);
						params.put("ValorDato7", ValorDato7);
						params.put("ValorDato8", ValorDato8);
						params.put("ValorDato9", ValorDato9);
						params.put("ValorDato10", ValorDato10);
					
						if(ValorDato1.compareTo("")==0)					params.put("ValorDato1", null);
						if(ValorDato2.compareTo("")==0)					params.put("ValorDato2", null);
						if(ValorDato3.compareTo("")==0)					params.put("ValorDato3", null);
						if(ValorDato4.compareTo("")==0)					params.put("ValorDato4", null);
						if(ValorDato5.compareTo("")==0)					params.put("ValorDato5", null);
						if(ValorDato6.compareTo("")==0)					params.put("ValorDato6", null);
						if(ValorDato7.compareTo("")==0)					params.put("ValorDato7", null);
						if(ValorDato8.compareTo("")==0)					params.put("ValorDato8", null);
						if(ValorDato9.compareTo("")==0)					params.put("ValorDato9", null);
						if(ValorDato10.compareTo("")==0)				params.put("ValorDato10", null);				
						
						params.put("numeroCarga", Integer.valueOf(numeroCarga));			    
					    params.put("codigoPeriodo", codigoPeriodo);			    
					    params.put("tipoCarga", tipoCarga);
					    params.put("codigoPrograma", codigoPrograma);
					    params.put("numeroFila", Integer.valueOf(fila));
						params.put("codigoUsuario", codigoUsuario);
						params.put("codigoPais", codigoPais);
						
						insert=procesoLECCargaDatosExcelDAO.insertCargaDatos(params);
						if(insert==1)
							totalInsertados++;
						insert=0;					
					}
				}
			}
			
			
			excelUtil.cerrar();		
			resultado.put("numeroCarga", numeroCarga);
			resultado.put("totalRegistros", String.valueOf(totalInsertados));		
			resultado.put("totalRegistrosInsertados", String.valueOf(totalInsertados));
		
	    }
		catch(Exception ex) {
			log.debug("No se pudo procesar el archivo "+ex.getMessage());
			throw new Exception("No se Proceso Correctamente el Archivo");
		}
	    return resultado;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService#executeValidarCargaDatos(java.util.Map)
	 */
	public List executeValidarCargaDatos(Map params) {
		String val=procesoLECCargaDatosExcelDAO.executeValidarCargaDatos(params);
		List resultados= null;
		if(val==null || val.compareTo("0")==0){
		resultados = procesoLECCargaDatosExcelDAO.getCargaDatosList(params);
		}
		if(resultados==null)
		resultados= new ArrayList();
		return resultados;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.lec.ProcesoLECCargaDatosExcelService#executeActualizarCargaDatos(java.util.Map)
	 */
	public String executeActualizarCargaDatos(Map params) {	
		String res="1";
		try{
			res=procesoLECCargaDatosExcelDAO.executeActualizarCargaDatos(params);
			if(res.compareTo("0")==0){
				String mensajeError = Constants.MESSAGE_LEC_NO_REGISTRO_CORRECTO;
				throw new Exception(mensajeError);
			}
	    }catch(Exception e){
		e.printStackTrace();
	 	log.debug("error ");
	}
		
		return res;
	  
	}
	public List getListaIntermedia(Map map){		
		return procesoLECCargaDatosExcelDAO.getListaIntermedia(map);
	}


	public String getCampanhaInicialPrograma(Map params) {
		return procesoLECCargaDatosExcelDAO.getCampanhaInicialPrograma(params);
	}



	public boolean getNumeroRegistrosResultadosLet(Map params) {
		return procesoLECCargaDatosExcelDAO.getNumeroRegistrosResultadosLet(params);
	}

}
