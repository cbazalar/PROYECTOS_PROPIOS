/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoPEDCargaMasivaFletesDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaMasivaFletesService;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("spusicc.procesoPEDCargaMasivaFletesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDCargaMasivaFletesServiceImpl extends BaseService implements ProcesoPEDCargaMasivaFletesService {

	@Resource(name="spusicc.procesoPEDCargaMasivaFletesDAO")
	private ProcesoPEDCargaMasivaFletesDAO procesoPEDCargaMasivaFletesDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaMasivaFletesService#cargarArchivoExcelCSV(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public Map cargarArchivoExcelCSV(List lineas, Usuario usuario) {
		String codUsuario = usuario.getLogin();
		procesoPEDCargaMasivaFletesDAO.deleteTemporalCargaMasivaFletes(codUsuario);
		for (int i = 0; i < lineas.size(); i++) {
			Map params = (Map)lineas.get(i);
			params.put("numeroFila", Integer.valueOf(i+1));
			params.put("codigoUsuario", codUsuario);
			procesoPEDCargaMasivaFletesDAO.insertaTemporalCargaMasivaFletes(params);
		}
		
        Map resultado = new HashMap();
        resultado.put("totalRegistros", String.valueOf(lineas.size()));
        return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaMasivaFletesService#executeValidarCargaMasivaFlete(java.util.Map)
	 */
	public List executeValidarCargaMasivaFlete(Map params) {
		procesoPEDCargaMasivaFletesDAO.executeValidarCargaMasivaFlete(params);
        List resultados = procesoPEDCargaMasivaFletesDAO.getCargaMasivaFleteList(params);
        return resultados;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.service.ProcesoPEDCargaMasivaFletesService#executeActualizarCargaMasivaFlete(java.util.Map)
	 */
	public void executeActualizarCargaMasivaFlete(Map params) {
		procesoPEDCargaMasivaFletesDAO.deleteDetalleConfiguracionFlete();
		procesoPEDCargaMasivaFletesDAO.deleteCabeceraConfiguracionFlete();
		procesoPEDCargaMasivaFletesDAO.executeActualizarConfiguracionFlete(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaMasivaFletesService#cargarArchivoCSVMontoMinimo(java.util.List, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public Map cargarArchivoCSVMontoMinimo(List lineas, Usuario usuario) {
		String codUsuario = usuario.getLogin();
		procesoPEDCargaMasivaFletesDAO.deleteTemporalCargaMasivaMontoMinimo(codUsuario);		
		for (int i = 0; i < lineas.size(); i++) {
			Map params = (Map)lineas.get(i);
			params.put("numeroFila", Integer.valueOf(i+1));
			params.put("codigoUsuario", codUsuario);
			procesoPEDCargaMasivaFletesDAO.insertaTemporalCargaMasivaMontoMinimo(params);
		}
		
        Map resultado = new HashMap();
        resultado.put("totalRegistros", String.valueOf(lineas.size()));
        return resultado;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDCargaMasivaFletesService#executeValidarCargaMasivaMontoMinimo(java.util.Map)
	 */
	public List executeValidarCargaMasivaMontoMinimo(Map params) {		
		procesoPEDCargaMasivaFletesDAO.executeValidarCargaMasivaMontoMinimo(params);
        List resultados = procesoPEDCargaMasivaFletesDAO.getCargaMasivaMontoMinimoList(params);
        return resultados;
	}
	
	public void executeActualizarCargaMasivaMontoMinimo(Map params) {		
		procesoPEDCargaMasivaFletesDAO.executeActualizarCargaMasivaMontoMinimo(params);
	}

	
}
