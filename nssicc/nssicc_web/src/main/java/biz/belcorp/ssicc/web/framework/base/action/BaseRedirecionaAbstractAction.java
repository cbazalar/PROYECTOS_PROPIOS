package biz.belcorp.ssicc.web.framework.base.action;




public abstract class BaseRedirecionaAbstractAction extends MBaseSistemaAbstractJSF{

	private static final long serialVersionUID = 1L;
	
	protected abstract String setRedireccionarAtributes() throws Exception;

	/**
	 * Metodo que se encarga de la navegacion indicada de acuerdo al valor del parametro establecido
	 * @return String, valor que representa el destino de la pagina 					
	 */
	public String redireccionar() {
		String redireccionar = null;
		if (log.isWarnEnabled()) { 
			log.warn("Entering 'add' method redireccionar");
		}
		
		
		try{			
			redireccionar = setRedireccionarAtributes();
			return  redireccionar;
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));			
			return null;
		}
	}

	/**
	 * Metodo de Inicializacion Personalizada de la Clase ManageBeans, cuando se ingresa a la Opción
	 * @throws Exception
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarCabeceraPantalla = false;
	}

}
