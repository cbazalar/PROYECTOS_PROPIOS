package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.MantenimientoCCCFacturarInteresDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.MantenimientoCCCFacturarInteresService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * Service que controla la Consulta de Telecobro
 *  
 * <p>
 * <a href="MantenimientoCCCFacturarInteresServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres Loyola</a>
 * 
 */
@Service("spusicc.mantenimientoCCCFacturarInteresService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoCCCFacturarInteresServiceImpl extends BaseService implements MantenimientoCCCFacturarInteresService {

	@Resource(name = "spusicc.mantenimientoCCCFacturarInteresDAO")
	private MantenimientoCCCFacturarInteresDAO mantenimientoCCCFacturarInteresDAO;


	public List getConsoCalcuInteMoralList(Map criteria) {
		return mantenimientoCCCFacturarInteresDAO.getConsoCalcuInteMoralList(criteria);
	}

	public boolean updateConsolidadoInterMoraCCC(Map criteria, Usuario usuario) {
		
		boolean valida=true;
		List listaConsolidado = (List)criteria.get("listaConsolidado");
        
		mantenimientoCCCFacturarInteresDAO.deleteConsolidadoInterMoraCCC(usuario.getLogin());
		 
		if(listaConsolidado!=null && listaConsolidado.size()>0){
			for (int i=0;i<listaConsolidado.size();i++){
				Map params = (Map)listaConsolidado.get(i);	
				params.put("codigoUsuario", usuario.getLogin());
				mantenimientoCCCFacturarInteresDAO.insertConsolidadoInterMoraCCC(params);	
				
			}
		}
		
		mantenimientoCCCFacturarInteresDAO.executeValidarCargaConsolidadoInterMoraCCC(usuario.getLogin());
		mantenimientoCCCFacturarInteresDAO.executeProcesarCargaFactuInterMora(usuario.getLogin());
		
		return valida;
	}

	public String obtenerPathUpload(String codigoPais) {
		return mantenimientoCCCFacturarInteresDAO.obtenerPathUpload(codigoPais);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoCCCFacturarInteresService#cargarArchivoExcel(java.util.Map)
	 */
	public int cargarArchivoExcel(Map criteria)  throws Exception {
        String directorioTemporal = (String)criteria.get("directorioTemporal");
        String nombreArchivo = (String)criteria.get("nombreArchivo");
        Usuario usuario = (Usuario)criteria.get("usuario");
        
        mantenimientoCCCFacturarInteresDAO.deleteConsolidadoInterMoraCCC(usuario.getLogin());
        
        ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
        excelUtil.initSheet(0);
                
        int fila = 0;
        Map params = new HashMap();
        params.put("codigoUsuario", usuario.getLogin());
        
        while(excelUtil.hasNext()){
            Map mapRow = excelUtil.next();
            fila++;
            
            if(fila == 1)
            	mapRow = excelUtil.next();
            
            String codigoConsultora = StringUtils.remove((String)mapRow.get("0"), ".0");
            String montoInteres= StringUtils.remove((String)mapRow.get("1"), ".0");
            
            params.put("codigoConsultora", StringUtils.trim(codigoConsultora));
            params.put("montoInteres", StringUtils.trim(montoInteres));
            params.put("numeroFila", Integer.valueOf(fila));
            
            mantenimientoCCCFacturarInteresDAO.insertConsolidadoInterMoraCCC(params);
        }
        excelUtil.cerrar();
        
        return fila;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoCCCFacturarInteresService#executeValidarCargaTerritorioUnidadGeografica(java.lang.String)
	 */
	public List executeValidarConsolidadoInterMora(String codigoUsuario) {

		mantenimientoCCCFacturarInteresDAO.executeValidarCargaConsolidadoInterMoraCCC(codigoUsuario);
		List lista = mantenimientoCCCFacturarInteresDAO.getCargarConsolidadoInterMoraCCC(codigoUsuario);
				
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoCCCFacturarInteresService#executeProcesarCargaTerritorioUnidadGeografica(java.lang.String)
	 */
	public void executeProcesarCargaConsolidadoInterMora(String codigoUsuario) {
		mantenimientoCCCFacturarInteresDAO.executeProcesarCargaFactuInterMora(codigoUsuario);
	}
	
}
