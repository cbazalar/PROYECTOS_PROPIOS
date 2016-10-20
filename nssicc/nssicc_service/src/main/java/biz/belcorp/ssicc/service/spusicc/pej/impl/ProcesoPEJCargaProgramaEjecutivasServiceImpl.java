package biz.belcorp.ssicc.service.spusicc.pej.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pej.ProcesoPEJCargaProgramaEjecutivasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pej.ProcesoPEJCargaProgramaEjecutivasService;
import biz.belcorp.ssicc.service.util.ExcelUtil;



/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
@Service("spusicc.procesoPEJCargaProgramaEjecutivasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEJCargaProgramaEjecutivasServiceImpl extends BaseService implements ProcesoPEJCargaProgramaEjecutivasService {
	
	@Resource(name="spusicc.procesoPEJCargaProgramaEjecutivasDAO")
	private ProcesoPEJCargaProgramaEjecutivasDAO procesoPEJCargaProgramaEjecutivasDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ProcesoPEJCargaProgramaEjecutivasService#getTipoCarga()
	 */
	public List getTipoCarga() {
		return procesoPEJCargaProgramaEjecutivasDAO.getTipoCarga();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ProcesoPEJCargaProgramaEjecutivasService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {
		String directorioTemporal = (String)criteria.get("directorioTemporal");
		String nombreArchivo = (String)criteria.get("nombreArchivo");
		String tipoCarga = (String)criteria.get("tipoCarga");
		String campanyaReferencia = (String)criteria.get("campanyaReferencia");
		String codigoUsuario = ((Usuario)criteria.get("usuario")).getLogin();
		String prefijo = (String)criteria.get("prefijo");

		//recupera el numero de carga
		String numeroCarga = getNumeroCarga();
		
		procesoPEJCargaProgramaEjecutivasDAO.deleteCargaProgramaEjecutivas(codigoUsuario);
		
		//Abrimos el archivo Excel para su procesamiento		
		ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
		//nos colocamos en la primera hora del documento Excel
		excelUtil.initSheet(0);
		excelUtil.next();
		
		int fila=0;
		int existen=0;
		int noExisten=0;
		Map params = new HashMap();
		
		while(excelUtil.hasNext()) {
			Map mapRow = excelUtil.next();
			String prefijoExcel = (String)mapRow.get("0");
			if(StringUtils.equals(prefijoExcel, prefijo)){
				fila +=1;
				String codigoCliente = (StringUtils.isBlank((String)mapRow.get("1"))?"":StringUtils.remove((String)mapRow.get("1"), ".0"));
				String campInicio = (StringUtils.isBlank((String)mapRow.get("2"))?"":StringUtils.remove((String)mapRow.get("2"), ".0"));
				String campFinal = (StringUtils.isBlank((String)mapRow.get("3"))?"":StringUtils.remove((String)mapRow.get("3"), ".0"));
				String codigoNivel = (StringUtils.isBlank((String)mapRow.get("4"))?"":(StringUtils.remove((String)mapRow.get("4"), ".0").length()==1?"0"+StringUtils.remove((String)mapRow.get("4"), ".0"):StringUtils.remove((String)mapRow.get("4"), ".0")));
				String codigoFase = (StringUtils.isBlank((String)mapRow.get("5"))?"":(StringUtils.remove((String)mapRow.get("5"), ".0").length()==1?"0"+StringUtils.remove((String)mapRow.get("5"), ".0"):StringUtils.remove((String)mapRow.get("5"), ".0")));
				String metaPedido = (StringUtils.isBlank((String)mapRow.get("6"))?"":StringUtils.remove((String)mapRow.get("6"), ".0"));
				String metaIngreso = (StringUtils.isBlank((String)mapRow.get("7"))?"":StringUtils.remove((String)mapRow.get("7"), ".0"));
				String metaRecaudo = (StringUtils.isBlank((String)mapRow.get("8"))?"":StringUtils.remove((String)mapRow.get("8"), ".0"));
				String montoReclamo = (StringUtils.isBlank((String)mapRow.get("9"))?"":StringUtils.remove((String)mapRow.get("9"), ".0"));
				String tipoAbono = (StringUtils.isBlank((String)mapRow.get("10"))?"":(StringUtils.remove((String)mapRow.get("10"), ".0").length()==1?"0"+StringUtils.remove((String)mapRow.get("10"), ".0"):StringUtils.remove((String)mapRow.get("10"), ".0")));
				String tipoOperacion = (StringUtils.isBlank((String)mapRow.get("11"))?"":StringUtils.remove((String)mapRow.get("11"), ".0"));
				
			    params.put("numeroCarga", Integer.valueOf(numeroCarga));
			    params.put("codigoCliente", StringUtils.trim(codigoCliente));
			    params.put("campanyaReferencia", campanyaReferencia);
			    params.put("tipoCarga", tipoCarga);
			    params.put("campInicio", StringUtils.trim(campInicio));
			    params.put("campFinal", StringUtils.trim(campFinal));
			    params.put("codigoNivel", StringUtils.trim(codigoNivel));
			    params.put("metaRecaudo", (StringUtils.isBlank(metaRecaudo)?null:Double.valueOf(metaRecaudo)));
			    params.put("metaIngreso", (StringUtils.isBlank(metaIngreso)?null:Double.valueOf(metaIngreso)));
			    params.put("metaPedido", (StringUtils.isBlank(metaPedido)?null:Double.valueOf(metaPedido)));
			    params.put("montoReclamo", (StringUtils.isBlank(montoReclamo)?null:Double.valueOf(montoReclamo)));
			    params.put("codigoFase", StringUtils.trim(codigoFase));
				params.put("tipoAbono", StringUtils.trim(tipoAbono));
				params.put("tipoOperacion", StringUtils.trim(tipoOperacion));
				params.put("numeroFila", Integer.valueOf(fila));
				params.put("codigoUsuario", codigoUsuario);
			    
				procesoPEJCargaProgramaEjecutivasDAO.insertCargaProgramaEjecutivas(params);
			}
		}
		excelUtil.cerrar();
		
		Map resultado = new HashMap();
		resultado.put("numeroCarga", numeroCarga);
		resultado.put("totalRegistros", String.valueOf(fila));
		
		return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ProcesoPEJCargaProgramaEjecutivasService#executeValidarCargaProgramaEjecutivas(java.util.Map)
	 */
	public List executeValidarCargaProgramaEjecutivas(Map params) {
		procesoPEJCargaProgramaEjecutivasDAO.executeValidarCargaProgramaEjecutivas(params);
		
		List resultados = procesoPEJCargaProgramaEjecutivasDAO.getCargaProgramaEjecutivasList(params);
		
		return resultados;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.ProcesoPEJCargaProgramaEjecutivasService#executeActualizarCargaProgramaEjecutivas(java.util.Map)
	 */
	public void executeActualizarCargaProgramaEjecutivas(Map params) {
		procesoPEJCargaProgramaEjecutivasDAO.executeActualizarCargaProgramaEjecutivas(params);
	}

	private String getNumeroCarga() {
		return procesoPEJCargaProgramaEjecutivasDAO.getNumeroCarga();
	}
}