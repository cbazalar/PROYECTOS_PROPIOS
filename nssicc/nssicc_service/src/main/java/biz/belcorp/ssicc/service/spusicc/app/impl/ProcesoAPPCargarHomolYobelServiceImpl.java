package biz.belcorp.ssicc.service.spusicc.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.app.ProcesoAPPCargarHomolYobelDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPCargarHomolYobelService;

/**  
 * <p>
 * <a href="ProcesoAPPCargarHomolYobelServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */

/**
 * @author peextdoliva
 *
 */
@Service("spusicc.procesoAPPCargarHomolYobelService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoAPPCargarHomolYobelServiceImpl extends BaseService implements ProcesoAPPCargarHomolYobelService {

	@Resource(name="spusicc.procesoAPPCargarHomolYobelDAO")
	private ProcesoAPPCargarHomolYobelDAO procesoAPPCargarHomolYobelDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPCargarHomolYobelService#executeSecuenciarZonasTerritorios()
	 */
	public void executeSecuenciarZonasTerritorios(){
		procesoAPPCargarHomolYobelDAO.executeSecuenciarZonasTerritorios();
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPCargarHomolYobelService#insertaHomologadoYobel(java.util.List)
	 */
	public List insertaHomologadoYobel(List lineas,Usuario usuario){
		String mensajeError = "";
		List returnErroresList = new ArrayList();
		
		procesoAPPCargarHomolYobelDAO.deleteTemporalHomologadoYobel();
		for (int i = 0; i < lineas.size(); i++) {
			Map params = (Map)lineas.get(i);
			procesoAPPCargarHomolYobelDAO.insertaTemporalHomologadoYobel(params);
		}
		
		List zonasList = procesoAPPCargarHomolYobelDAO.getSecuenciasZonasList();
		List zonasHomologadasList = procesoAPPCargarHomolYobelDAO.getSecuenciasZonasTempoHomologadasList();

		List erroresList = new ArrayList();

		for (int i = 0; i < zonasHomologadasList.size(); i++) {
			Map homologado = (Map)zonasHomologadasList.get(i);
			boolean flag = true;
			for (int j = 0; j < zonasList.size(); j++) {
				Map zona = (Map)zonasList.get(j);
				if(homologado.get("codigoZona").toString().equals(zona.get("codigoZona").toString())){
					flag = false;
					if(!homologado.get("cantidad").toString().equals(zona.get("cantidad").toString())){
						erroresList.add(homologado.get("codigoZona").toString());
					}
					break;
				}
			}
			if(flag)
				erroresList.add(homologado.get("codigoZona").toString());
		}
		
		if(erroresList.size() != 0){
			String error = getMessageResource(usuario, "territotios.zonas.no.coinciden.resecuenciar") + " ";
			for (int i = 0; i < erroresList.size(); i++) {
				error += erroresList.get(i).toString() + " "; 				
			}
			error += getMessageResource(usuario, "territorios.zonas");
			returnErroresList.add(error);
		}
		
		Map criteria = new HashMap();
		procesoAPPCargarHomolYobelDAO.validarCargaHomologacion(criteria);
		
		String listaErrores = (String) criteria.get("listaErrores"); 
		String mensajeInicioError = (String) criteria.get("mensajeInicioError");
		String mensajeFinalError = (String) criteria.get("mensajeFinalError");
		
		if(listaErrores!=null){
			String[] mensajesInicio = mensajeInicioError.split(",");
			String[] mensajesFin = mensajeFinalError.split(",");
			String[] errores =  listaErrores.split(";");
			for(int i=0; i<errores.length; i++)
				returnErroresList.add(getMessageResource(usuario, mensajesInicio[i]) +
			      errores[i] + getMessageResource(usuario, mensajesFin[i]));
			return returnErroresList;
		}
		
		if(returnErroresList.size() == 0){
			for (int i = 0; i < lineas.size(); i++) {
				Map params = (Map)lineas.get(i);
				procesoAPPCargarHomolYobelDAO.insertaHomologadoYobel(params);
			}
			String codUsuario = usuario.getLogin();
			executeSecuenciarZonasTerritorios(codUsuario);
		}
		return returnErroresList;
	}


	/**
	 * Ejecuta la carga de homologados Yobel
	 * @param codUsuario
	 */
	public void executeSecuenciarZonasTerritorios(String codUsuario){
		Map criteria = new HashMap();
		criteria.put("usuario", codUsuario);

		procesoAPPCargarHomolYobelDAO.executeSecuenciarZonasTerritorios(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPCargarHomolYobelService#deleteTablaHomologacion()
	 */
	public void deleteTablaHomologacion(){
		procesoAPPCargarHomolYobelDAO.deleteTablaHomologacion();
	}
	
	/**
	 * Obtiene message resource
	 * @param usuario
	 * @param messageResource
	 * @return
	 */
	public String getMessageResource(Usuario usuario, String messageResource) {
		return (this.messageSource).getMessage(messageResource, null, getLocale(usuario));
	}
}

