/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.dao.spusicc.zon.ProcesoZONCargarTerritorioUnidadGeograficaDAO;
import biz.belcorp.ssicc.service.spusicc.zon.ProcesoZONCargarTerritorioUnidadGeograficaService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProcesoZONCargarTerritorioUnidadGeograficaServiceImpl.java"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 * 
 */
@Service("spusicc.procesoZONCargarTerritorioUnidadGeograficaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoZONCargarTerritorioUnidadGeograficaServiceImpl extends BaseService implements ProcesoZONCargarTerritorioUnidadGeograficaService {

	@Resource(name="spusicc.procesoZONCargarTerritorioUnidadGeograficaDAO")
	private ProcesoZONCargarTerritorioUnidadGeograficaDAO procesoZONCargarTerritorioUnidadGeograficaDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONCargarTerritorioUnidadGeograficaService#obtenerPathUpload(java.lang.String)
	 */
	public String obtenerPathUpload(String codigoPais) {
		return procesoZONCargarTerritorioUnidadGeograficaDAO.obtenerPathUpload(codigoPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONCargarTerritorioUnidadGeograficaService#cargarArchivoCSV(java.util.Map)
	 */
	public int cargarArchivoCSV(Map criteria)  throws Exception {
        String directorioTemporal = (String)criteria.get("directorioTemporal");
        String nombreArchivo = (String)criteria.get("nombreArchivo");
        Usuario usuario = (Usuario)criteria.get("usuario");
        
        procesoZONCargarTerritorioUnidadGeograficaDAO.deleteCargarTerritorioUnidadGeografica(usuario.getLogin());
        
        int fila = 0;
        Map params = new HashMap();
        params.put("codigoUsuario", usuario.getLogin());
        
        BufferedReader br = null;
        try {
        	 
			String linea;
			File file = new File(directorioTemporal, nombreArchivo);
			br = new BufferedReader(new FileReader(file));
 
			while ((linea = br.readLine()) != null) {
				String tokens[] = StringUtils.splitPreserveAllTokens(linea, ",");
				fila++;
								
				if(fila == 1)
					continue;
					
	            String codigoTerritorio = tokens[0];
	            String codigoGeografia= tokens[1];
	            
	            params.put("codigoTerritorio", StringUtils.trim(codigoTerritorio));
	            params.put("codigoGeografia", StringUtils.trim(codigoGeografia));
	            params.put("numeroFila", Integer.valueOf(fila));
	            
	            procesoZONCargarTerritorioUnidadGeograficaDAO.insertCargarTerritorioUnidadGeografica(params);
				
			}
			
			if(fila > 0)
				fila = fila - 1;
 
		} 
        catch (IOException e) 
        {
			log.error(e);
		} 
        finally {
			try 
			{
				if (br != null)
					br.close();
			} 
			catch (IOException ex) 
			{
				log.error(ex);
			}
		}	
                
        return fila;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONCargarTerritorioUnidadGeograficaService#executeValidarCargaTerritorioUnidadGeografica(java.lang.String)
	 */
	public List executeValidarCargaTerritorioUnidadGeografica(String codigoUsuario) {

		procesoZONCargarTerritorioUnidadGeograficaDAO.executeValidarCargaTerritorioUnidadGeografica(codigoUsuario);
		List lista = procesoZONCargarTerritorioUnidadGeograficaDAO.getCargarTerritorioUnidadGeografica(codigoUsuario);
				
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.ProcesoZONCargarTerritorioUnidadGeograficaService#executeProcesarCargaTerritorioUnidadGeografica(java.lang.String)
	 */
	public void executeProcesarCargaTerritorioUnidadGeografica(String codigoUsuario) {
		procesoZONCargarTerritorioUnidadGeograficaDAO.executeProcesarCargaTerritorioUnidadGeografica(codigoUsuario);
	}

}
