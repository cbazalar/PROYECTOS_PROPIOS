/*
 * Created on 19/04/2005 04:47:56 PM biz.belcorp.ssicc.dao.ibatis.MenuDAOiBatis
 */
package biz.belcorp.ssicc.dao.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.MenuDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.DescripcionMenu;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Menu;
import biz.belcorp.ssicc.dao.model.MenuOpciones;
import biz.belcorp.ssicc.dao.model.ParametroMenu;
import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MenuDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Repository("menuDAO")
public class MenuDAOiBatis extends BaseDAOiBatis implements MenuDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#getMenues(biz.belcorp.ssicc.model.Menu)
	 */
	public List getMenues(Menu menu) {
		List menues = getSqlMapClientTemplate().queryForList(
				"MenuSQL.getMenues", menu);
		return menues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#getMenuesByCriteria(java.util.Map)
	 */
	public List getMenuesByCriteria(Map criteria) {
		List menues = getSqlMapClientTemplate().queryForList(
				"MenuSQL.getMenuesByCriteria", criteria);
		return menues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#getMenuesByUsuario(java.lang.String,java.lang.String,java.lang.String)
	 */
	public List getMenuesByUsuario(String codigoPais, String username, String codigoIdioma, int nroSession) {
		Map map = new HashMap();
		map.put("codigoPais", codigoPais);
		map.put("username", username);
		map.put("codigoIdioma", codigoIdioma);

		// Obtenemos la relacion de menues asignados al usuario
		List menues = getSqlMapClientTemplate().queryForList("MenuSQL.getMenuesByUsuario", map);

		// Obtenemos los parametros de cada menu
		if (menues != null) {
			long numeroMenu = 1;
			for (int i = 0; i < menues.size(); i++) {
				Menu menu = (Menu) menues.get(i);
				List parametros = new ArrayList();
				/*RECUPERACION DE PARAMETROS DE MENU*/
				if (menu.getNumeroParametros()>0){
					parametros = getSqlMapClientTemplate().queryForList( "ParametroMenuSQL.getParametrosByCodigoMenu", menu.getCodigo());
				}
				/* ADICIONANDO CODIGO DE MENU */
				numeroMenu = parametros.size() + 1;
				ParametroMenu paramMenuCodigo = new ParametroMenu();
				paramMenuCodigo.setCodigoMenu(menu.getCodigo());
				paramMenuCodigo.setNombre(Constants.MENU_NOMBRE_CODIGO_MENU);
				paramMenuCodigo.setValor(menu.getCodigo());
				paramMenuCodigo.setNumero(numeroMenu);
				paramMenuCodigo.setEstado(Constants.ACTIVO);
				parametros.add(paramMenuCodigo);
				
				
				/* ADICIONANDO OCULTAR MENNU */
				numeroMenu = numeroMenu + 1;
				ParametroMenu paramIndicadorOcultar = new ParametroMenu();
				paramIndicadorOcultar.setCodigoMenu(menu.getCodigo());
				paramIndicadorOcultar.setNombre(Constants.INDICADOR_OCULTAR_MENU);
				paramIndicadorOcultar.setValor(menu.getIndicadorOcultarMenu());
				paramIndicadorOcultar.setNumero(numeroMenu);
				paramIndicadorOcultar.setEstado(Constants.ACTIVO);
				parametros.add(paramIndicadorOcultar);
				
				
				/* ADICIONANDO NRO DE SESSION */
				numeroMenu = numeroMenu + 1;
				Integer valorSession = new Integer(nroSession);
				String svalorSession = valorSession.toString().trim();
				ParametroMenu paramNroSession = new ParametroMenu();
				paramNroSession.setCodigoMenu(menu.getCodigo());
				paramNroSession.setNombre("nroSession");
				paramNroSession.setValor(svalorSession);
				paramNroSession.setNumero(numeroMenu);
				paramNroSession.setEstado(Constants.ACTIVO);
				parametros.add(paramNroSession);
				
				/* Adicionando paramteros al menu */
				menu.setParametros(parametros);
				
				/* INI NUEVO ACTUALIZACION FRAMEWORK JSF */
				String paginaJSF = new String(); //CAMBIAR LUEGO POR EL CAMPO A CREAR
				String paginaDefecto ="/paginaEnConstruccion.xhtml";
				menu.setParametrosXml("");
				menu.setParametrosVariables("");
				menu.setParametrosValores("");
				if (parametros != null && menu.getPagina() != null) {
					
					String paginaXML = menu.getPaginaXml();
					if (StringUtils.isNotBlank(paginaXML))
						paginaJSF = paginaXML;
					else
						paginaJSF = paginaDefecto;
					String parametro = "";
					String parametroVariables = "";
					String parametroValores = "";
					int ultimo = parametros.size() - 1;
					if (ultimo < 0) ultimo = 0;
					for (int x = 0; x < parametros.size(); x++) {
						ParametroMenu parametroMenu = (ParametroMenu) parametros.get(x);
						if(x==0){
							parametro =  parametro + "?" + parametroMenu.getNombre() + "=" + parametroMenu.getValor() ;	
							parametroVariables = parametroVariables + parametroMenu.getNombre();
							parametroValores = parametroValores + parametroMenu.getValor();
						}else{
							parametro =  parametro + "&" + parametroMenu.getNombre() + "=" + parametroMenu.getValor() ;
							parametroVariables = parametroVariables + ";" + parametroMenu.getNombre();
							parametroValores = parametroValores +  ";" + parametroMenu.getValor();
						}
						
					}
					menu.setParametrosXml(parametro);
					parametro = paginaJSF + parametro;
					menu.setUrlJSF(parametro);
					menu.setParametrosVariables(parametroVariables);
					menu.setParametrosValores(parametroValores);
				}
				
				if (StringUtils.isBlank(menu.getPagina())) {
					paginaJSF = paginaDefecto;
					menu.setUrlJSF(paginaJSF);
				}
				menu.setPaginaXml(paginaJSF);
				menu.setNroSession(svalorSession);
				/* FIN NUEVO ACTUALIZACION FRAMEWORK JSF */

			}
		}
		return menues;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.seguridad.MenuDAO#getMenuJSF(java.lang.String, java.lang.String)
	 */
	public Menu getMenuJSF(String codigo, String codigoIdioma) {
		// Ya que necesitamos enviar el valor del idioma, ademas del pk
		// es que usamos un map con dichos objetos
		Map map = new HashMap();
		map.put("codigoIdioma", codigoIdioma);
		map.put("codigo", codigo);

		Menu menu = (Menu) getSqlMapClientTemplate().queryForObject(
				"MenuSQL.getMenu", map);
		if (menu == null) {
			throw new ObjectRetrievalFailureException(Menu.class, codigo);
		}

		// Obtenemos los parametros del menu
		List parametros = getSqlMapClientTemplate().queryForList(
				"ParametroMenuSQL.getParametrosByCodigoMenu", menu.getCodigo());
		menu.setParametros(parametros);
		
		/* adicionando como parametro el codigo del Menu */
		long numeroMenu = 1;
		if (parametros != null)
			numeroMenu = parametros.size() + 1;
		else
			numeroMenu = 1;
		ParametroMenu paramMenuCodigo = new ParametroMenu();
		paramMenuCodigo.setCodigoMenu(menu.getCodigo());
		paramMenuCodigo.setNombre(Constants.MENU_NOMBRE_CODIGO_MENU);
		paramMenuCodigo.setValor(menu.getCodigo());
		paramMenuCodigo.setNumero(numeroMenu);
		paramMenuCodigo.setEstado(Constants.ACTIVO);
		parametros.add(paramMenuCodigo);
		
		/* Adicionando paramteros al menu */
		menu.setParametros(parametros);
		
		/* INI NUEVO ACTUALIZACION FRAMEWORK JSF */
		if (parametros != null && menu.getPagina() != null) {
			
			String paginaJSF = new String(); //CAMBIAR LUEGO POR EL CAMPO A CREAR
			String paginaXML = menu.getPaginaXml();
			if (StringUtils.isNotBlank(paginaXML))
				paginaJSF = paginaXML;
			String parametro = "";
			int ultimo = parametros.size() - 1;
			if (ultimo < 0) ultimo = 0;
			for (int x = 0; x < parametros.size(); x++) {
				ParametroMenu parametroMenu = (ParametroMenu) parametros.get(x);
				parametro =  parametro + "?" + parametroMenu.getNombre() + "=" + parametroMenu.getValor() ;
			}
			parametro = paginaJSF + parametro;
			menu.setUrlJSF(parametro);
		}
		/* FIN NUEVO ACTUALIZACION FRAMEWORK JSF */
		return menu;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#getMenu(java.lang.String,
	 *      java.lang.String)
	 */
	public Menu getMenu(String codigo, String codigoIdioma) {
		// Ya que necesitamos enviar el valor del idioma, ademas del pk
		// es que usamos un map con dichos objetos
		Map map = new HashMap();
		map.put("codigoIdioma", codigoIdioma);
		map.put("codigo", codigo);

		Menu menu = (Menu) getSqlMapClientTemplate().queryForObject(
				"MenuSQL.getMenu", map);
		if (menu == null) {
			throw new ObjectRetrievalFailureException(Menu.class, codigo);
		}

		// Obtenemos los parametros del menu
		List parametros = getSqlMapClientTemplate().queryForList(
				"ParametroMenuSQL.getParametrosByCodigoMenu", menu.getCodigo());
		menu.setParametros(parametros);
		
		return menu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#insertMenu(biz.belcorp.ssicc.model.Menu,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMenu(Menu menu, Usuario usuario) {
		if (log.isDebugEnabled()) {
			log.debug(menu);
		}
		// Creamos el map donde enviaremos los parametros usados para calcular
		// el valor del codigo
		DescripcionMenu des = new DescripcionMenu();
		des.setCodigoMenu(menu.getCodigo());
        des.setPais_cod_pais(menu.getCodPais());
		
		
		Map params = new HashMap();
		params.put("codigoPadre", menu.getCodigoPadre());
		params.put("nivel", menu.getNivel());
		String codigo = getNextPK(params);

		// Asignamos el valor al menu e insertamos la informacion en la bd
		menu.setCodigo(codigo);
		getSqlMapClientTemplate().update("MenuSQL.insertMenu", menu);

		// Insertamos las descripciones del menu
		List idiomas = getSqlMapClientTemplate().queryForList(
				"IdiomaSQL.getIdiomas", null);
		if (idiomas != null) {
			for (int i = 0; i < idiomas.size(); i++) {
				Idioma idioma = (Idioma) idiomas.get(i);
				DescripcionMenu d = new DescripcionMenu();
				d.setCodigoMenu(menu.getCodigo());
				d.setCodigoIdioma(idioma.getCodigo());
				d.setDescripcion(menu.getDescripcion());
				d.setInformacionAyuda(menu.getInformacionAyuda());
				getSqlMapClientTemplate().update(
						"MenuSQL.insertDescripcionMenu", d);
			}
		}

		// Eliminamos los parametros de las opciones del Menu y los insertamos
		// nuevamente
		/*getSqlMapClientTemplate().update("MenuSQL.removeMenuOpcionesHijo",
				des);
		getSqlMapClientTemplate().update("MenuSQL.removeMenuOpcionesPadre",
				des);
  */
		// Insertamos Nuevamente los Botones del Menu.
		MenuOpciones menuOpciones = null;
		for (int i = 0; i < menu.getBotonesAsignados().length; i++) {
			menuOpciones = new MenuOpciones();
			menuOpciones.setCodigoMenu(codigo);
			menuOpciones.setCodigoOpciones(menu.getBotonesAsignados()[i]);
			getSqlMapClientTemplate().update("MenuSQL.insertMenuOpciones",
					menuOpciones);
		}

		// Eliminamos los parametros del menu y los insertamos nuevamente
		getSqlMapClientTemplate().update(
				"ParametroMenuSQL.removeParametrosByCodigoMenu",
				menu.getCodigo());

		List parametros = menu.getParametros();
		if (parametros != null) {
			for (int i = 0; i < parametros.size(); i++) {
				ParametroMenu parametro = (ParametroMenu) parametros.get(i);
				parametro.setCodigoMenu(menu.getCodigo());
				parametro.setNumero(i + 1);
				parametro.setEstado(Constants.ESTADO_ACTIVO);
				getSqlMapClientTemplate().update(
						"ParametroMenuSQL.insertParametroMenu", parametro);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#updateMenu(biz.belcorp.ssicc.model.Menu,
	 *      biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMenu(Menu menu, Usuario usuario) {
		log.debug(menu);
		getSqlMapClientTemplate().update("MenuSQL.updateMenu", menu);

		// Actualizamos la descripcion del menu
		DescripcionMenu d = new DescripcionMenu();
		d.setCodigoMenu(menu.getCodigo());
		d.setCodigoIdioma(usuario.getIdioma().getCodigo());
		d.setDescripcion(menu.getDescripcion());
		d.setInformacionAyuda(menu.getInformacionAyuda());
        d.setPais_cod_pais(menu.getCodPais());
		// Actualizamos la descripcion y obtenemos el numero de filas
		// actualizadas
		int rows = getSqlMapClientTemplate().update(
				"MenuSQL.updateDescripcionMenu", d);

		// si no se actualizó ninguna descripcion, la insertamos
		if (rows == 0) {
			getSqlMapClientTemplate()
					.update("MenuSQL.insertDescripcionMenu", d);
		}

		// Eliminamos los parametros del menu y los insertamos nuevamente
		getSqlMapClientTemplate().update(
				"ParametroMenuSQL.removeParametrosByCodigoMenu",
				menu.getCodigo());

	
		
		// Eliminamos los parametros de las opciones del Menu y los insertamos
		// nuevamente
	
		HashMap menuOpciones = null;
			for (int i = 0; i < menu.getBotonesNoAsignados().length; i++) {
				menuOpciones = new HashMap();
				menuOpciones.put("codigoMenu", menu.getCodigo());
				menuOpciones.put("pais_cod_pais", menu.getCodPais());
				menuOpciones.put("codigoOpciones", menu.getBotonesNoAsignados()[i]);
				getSqlMapClientTemplate().update("MenuSQL.executeNroEliminaHijoPadreOpcion",
						menuOpciones);
			}			
		// actualizamos Nuevamente los Botones del Menu.
		HashMap menuOpcionesUpd = null;
			for (int i = 0; i < menu.getBotonesAsignados().length; i++) {
				menuOpcionesUpd = new HashMap();
				menuOpcionesUpd.put("codigoMenu", menu.getCodigo());
				menuOpcionesUpd.put("codigoOpciones", menu.getBotonesAsignados()[i]);
				getSqlMapClientTemplate().update("MenuSQL.executeActualizaOpciones",
						menuOpcionesUpd);
			}			
		
		
		

		List parametros = menu.getParametros();
		if (parametros != null) {
			for (int i = 0; i < parametros.size(); i++) {
				ParametroMenu parametro = (ParametroMenu) parametros.get(i);
				parametro.setCodigoMenu(menu.getCodigo());
				parametro.setNumero(i + 1);
				parametro.setEstado(Constants.ESTADO_ACTIVO);
				getSqlMapClientTemplate().update(
						"ParametroMenuSQL.insertParametroMenu", parametro);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#removeMenu(java.lang.String)
	 */
	public void removeMenu(String codigo) {
		getSqlMapClientTemplate().update("MenuSQL.removeMenu", codigo);
	}

	/**
	 * Para cuando se agregue un nuevo Menu, permite calcular el sgte.
	 * correlativo.
	 * 
	 * @param params
	 *            En este caso no es usado.
	 * @return Cadena conteniendo el valor del sgte. correlativo
	 */
	public String getNextPK(Map params) {
		String codigo = null;
		codigo = (String) getSqlMapClientTemplate().queryForObject(
				"MenuSQL.getNextPK", params);
		return codigo;
	}

	public List getMenuesByCriteriaRol(Map criteria) {
		List menues = getSqlMapClientTemplate().queryForList(
				"MenuSQL.getMenuesByCriteriaRol", criteria);
		return menues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.dao.MenuDAO#getInformacionMenu(java.util.Map)
	 */
	public String getInformacionMenu(Map params) {
		String codigo = null;
		codigo = (String) getSqlMapClientTemplate().queryForObject(
				"ParametroMenuSQL.getInformacionMenu", params);
		return codigo;
	}

	public List getMenuOpciones(Map params) {

		List menuOpciones = getSqlMapClientTemplate().queryForList(
				"MenuSQL.getMenuOpciones", params);
		return menuOpciones;
	}

	public void insertMenuOpciones(MenuOpciones menuOpciones) {
		getSqlMapClientTemplate().update("MenuSQL.insertMenuOpciones",
				menuOpciones);
	}

	public void updateMenuOpciones(MenuOpciones menuOpciones) {
		getSqlMapClientTemplate().update("MenuSQL.updateMenuOpciones",
				menuOpciones);
	}

	public void deleteMenuOpciones(String codigoMenu) {
		getSqlMapClientTemplate().update("MenuSQL.removeMenuOpciones",
				codigoMenu);
	}

	public List getMenuOpcionesAsignadas(String codigoMenu) {
		List menuOpcionesAsignadas = getSqlMapClientTemplate().queryForList(
				"MenuSQL.getMenuOpcionesAsignados", codigoMenu);
		return menuOpcionesAsignadas;

	}

	public List getMenuOpcionesFaltantes(String codigoMenu) {
		List menuOpcionesFaltantes = getSqlMapClientTemplate().queryForList(
				"MenuSQL.getMenuOpcionesFaltantes", codigoMenu);
		return menuOpcionesFaltantes;

	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.MenuDAO#getMenuesByCriteriaRolNSSiCC(java.util.Map)
	 */
	public List getMenuesByCriteriaRolNSSiCC(Map criteria) {
		List menues = getSqlMapClientTemplate().queryForList(
				"MenuSQL.getMenuesByCriteriaRolNSSiCC", criteria);
		return menues;
	}

}
