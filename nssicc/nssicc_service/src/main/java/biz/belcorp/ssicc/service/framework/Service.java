package biz.belcorp.ssicc.service.framework;

import java.util.Locale;

import biz.belcorp.ssicc.dao.model.Usuario;


public interface Service { 
    
     /**
	 * Obtiene el valor basado en el key enviado como parametro.
	 * Dicho valor se obtiene del archivo properties
	 * @param keyMensaje
	 * @return
	 */
	public String getKeyMessage(String keyMensaje);

	 /**
	 * Obtiene el valor basado en el key enviado como parametro.
	 * Dicho valor se obtiene del archivo properties
	 * @param keyMensaje
	 * @return
	 */
	public String getKeyMessage(String keyMensaje, Usuario usuario);
	
	/**
	 * Metodo para obtener el Locate del usuario ingresado como parametro 
	 * @param usuario
	 * @return
	 */
	public Locale getLocale(Usuario usuario);
	
	
	/**
	 * Setea la variable de Mensaje de Error en base a la excepcion ingresada como parametro
	 * @param e
	 */
	public String obtieneMensajeErrorException(Exception e);
    
}

