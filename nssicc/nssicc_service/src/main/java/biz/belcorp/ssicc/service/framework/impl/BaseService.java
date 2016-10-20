package biz.belcorp.ssicc.service.framework.impl;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Idioma;
import biz.belcorp.ssicc.dao.model.Usuario;



/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * 
 * <p>
 * <a href="BaseService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a>
 */

@Service("baseService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class BaseService implements biz.belcorp.ssicc.service.framework.Service  {
	
    protected final Log log = LogFactory.getLog(getClass());
        
    @Resource(name="transactionManager")
    protected DataSourceTransactionManager transactionManager;
    
    
    @Resource(name="messageSource")
	protected MessageSource messageSource;
    


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.framework.Service#getKeyMessage(java.lang.String)
	 */
	public String getKeyMessage(String keyMensaje) {
		String mensaje = new String();
		if (StringUtils.isBlank(keyMensaje)) return "";
		try {
			mensaje = this.messageSource.getMessage(keyMensaje, null,
					new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
	
	/**
	 * @param keyMensaje
	 * @param args
	 * @return
	 */
	public String getKeyMessage(String keyMensaje, Object args[]) {
		String mensaje = new String();
		if (StringUtils.isBlank(keyMensaje)) return "";
		try {
			mensaje = this.messageSource.getMessage(keyMensaje, args,
					new Locale(Constants.EDU_IDIOMA_DEFAULT_ES));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.framework.Service#getKeyMessage(java.lang.String, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public String getKeyMessage(String keyMensaje, Usuario usuario) {		
		String mensaje = new String();
		if (StringUtils.isBlank(keyMensaje)) return "";
		try {
			mensaje = this.messageSource.getMessage(keyMensaje, null,
					this.getLocale(usuario));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mensaje;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.framework.Service#getLocale(biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public Locale getLocale(Usuario usuario) {
		
		if (usuario != null) {
			Idioma idioma = usuario.getIdioma();
			Locale locale = new Locale(idioma.getCodigoISO());
			if (locale == null)
				locale = new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
			return locale;
		}	
		return new Locale(Constants.EDU_IDIOMA_DEFAULT_ES);
	}    
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.framework.Service#obtieneMensajeErrorException(java.lang.Exception)
	 */
	public String obtieneMensajeErrorException(Exception e) {
		String retorno = null;
		if (StringUtils.isNotBlank(e.getMessage()))
			return e.getMessage();
		if (StringUtils.isNotBlank(e.getLocalizedMessage()))
			return e.getLocalizedMessage();
		retorno = e.toString();
		return retorno;
	}
	
	/**
     * Metodo que permite obtener el usuario por defecto
     * @return Usuario, objeto de respuesta
     */
    protected Usuario obtenerUsuarioByDefault(String login){
    	Idioma idioma = new Idioma();
        idioma.setCodigo("01");
        idioma.setCodigoISO("ES");
        idioma.setCodigoSiCC(Constants.EDU_IDIOMA_DEFAULT_ES);
        idioma.setEstado("1");
        
        Usuario usuario = new Usuario();
        usuario.setCodigoIdioma("01");
        usuario.setCodigoPais("");
        usuario.setLogin(login);
        usuario.setCodigo("");
        usuario.setIdioma(idioma);
    	return usuario;
    }
    
    /**
	 * Prepara los parametros a pasar al Service para la ejecucion. Remueve
	 * parametros innecesarios generados en la capa web, y setea los parametros
	 * necesarios para la capa service. Este metodo puede ser sobreescrito en
	 * caso se requieran mas parametros.
	 * @param params
	 * @return
	 * @throws Exception
	 */
	protected Map prepareParamsBeforeExecute(Map params) throws Exception {
		// Paso todos los parametros al map
		// Remuevo los parametros que no son necesarios del Validator
		params.remove("resultValueMap");
		params.remove("validatorResults");
		params.remove("servletWrapper");
		params.remove("multipartRequestHandler");
		params.remove("class");
		params.remove("page");
		return params;
	}
    
}
