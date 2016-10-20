package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPREMatrizCargaEstimadosDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPREMatrizCargaEstimadosService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 *
 */
@Service("spusicc.procesoPREMatrizCargaEstimadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPREMatrizCargaEstimadosServiceImpl extends BaseService implements ProcesoPREMatrizCargaEstimadosService{

    @Resource(name="spusicc.procesoPREMatrizCargaEstimadosDAO")
	private ProcesoPREMatrizCargaEstimadosDAO procesoPREMatrizCargaEstimadosDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPREMatrizCargaEstimadosService#cargarArchivoExcel(java.util.Map)
	 */
	public Map cargarArchivoExcel(Map criteria) throws Exception {
        String directorioTemporal = (String)criteria.get("directorioTemporal");
        String nombreArchivo = (String)criteria.get("nombreArchivo");
        Usuario usuario = (Usuario)criteria.get("usuario");
        
        procesoPREMatrizCargaEstimadosDAO.deleteCargaEstimados(usuario.getLogin());
        
        ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
        excelUtil.initSheet(0);
        
        int fila = 0;
        Map params = new HashMap();
        params.put("codigoUsuario", usuario.getLogin());
        
        while(excelUtil.hasNext()){
            Map mapRow = excelUtil.next();
            fila++;
            
            String campanhia = StringUtils.remove((String)mapRow.get("0"), ".0");
            String codigoSAP = StringUtils.remove((String)mapRow.get("1"), ".0");
            String tipoOferta = StringUtils.remove((String)mapRow.get("2"), ".0");
            String cicloVida = StringUtils.remove((String)mapRow.get("3"), ".0");
            String unidadesEstimadas = StringUtils.remove((String)mapRow.get("4"), ".0");
            String ventaNetaEstimada = (String)mapRow.get("5");
            String precioCatalogo = (String)mapRow.get("6");
            String precioPosicinamiento = (String)mapRow.get("7");
            String catalogo = StringUtils.remove((String)mapRow.get("8"), ".0");
            String numeroPagina = StringUtils.remove((String)mapRow.get("9"), ".0");
            String posicion = StringUtils.remove((String)mapRow.get("10"), ".0");
            String estrategia = StringUtils.remove((String)mapRow.get("11"), ".0");
            String costeEstandar = (String)mapRow.get("12");
            
            
            try
            {
            	if(StringUtils.isNotBlank(codigoSAP));
            	BigDecimal cs = new BigDecimal(codigoSAP);
            	            	
            	codigoSAP = String.valueOf(cs.longValue());
            }
            catch(Exception ex){}
            
            
            params.put("campanhia", StringUtils.trim(campanhia));
            params.put("codigoSAP", StringUtils.trim(codigoSAP));
            params.put("tipoOferta", StringUtils.trim(tipoOferta));
            params.put("cicloVida", StringUtils.trim(cicloVida));
            params.put("unidadesEstimadas", StringUtils.trim(unidadesEstimadas));
            params.put("ventaNetaEstimada", StringUtils.trim(ventaNetaEstimada));
            params.put("precioCatalogo", StringUtils.trim(precioCatalogo));
            params.put("precioPosicinamiento", StringUtils.trim(precioPosicinamiento));
            params.put("catalogo", StringUtils.trim(catalogo));
            params.put("numeroPagina", StringUtils.trim(numeroPagina));
            params.put("posicion", StringUtils.trim(posicion));
            params.put("estrategia", StringUtils.trim(estrategia));
            params.put("costeEstandar", StringUtils.trim(costeEstandar));
            params.put("numeroFila", Integer.valueOf(fila));
            
            procesoPREMatrizCargaEstimadosDAO.insertCargaEstimados(params);
        }
        excelUtil.cerrar();
        
        Map resultado = new HashMap();
        resultado.put("totalRegistros", String.valueOf(fila));
        return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPREMatrizCargaEstimadosService#executeValidarCargaEstimados(java.util.Map)
	 */
	public List executeValidarCargaEstimados(Map params) {
        procesoPREMatrizCargaEstimadosDAO.executeValidarCargaEstimados(params);
        List resultados = procesoPREMatrizCargaEstimadosDAO.getCargaEstimadosList(params);
        return resultados;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPREMatrizCargaEstimadosService#executeActualizarCargaEstimados(java.util.Map)
	 */
	public void executeActualizarCargaEstimados(Map params) {
        procesoPREMatrizCargaEstimadosDAO.executeActualizarCargaEstimados(params);
		
	}
    
}
