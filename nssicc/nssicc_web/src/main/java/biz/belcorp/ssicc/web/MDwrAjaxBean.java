package biz.belcorp.ssicc.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.web.framework.base.action.MBaseAbstractJSF;


@ManagedBean
@SessionScoped
public class MDwrAjaxBean extends MBaseAbstractJSF {
	
	private String valorInicial;
	private AjaxService ajaxService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 4306135105438369425L;

   
   
	@PostConstruct
	public void view() {
		try {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'view' method");
			}
			this.ajaxService = (AjaxService) getBean("ajaxService");

		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}


	public String getCodigoResultadoChequeo(String numeroPedido) {
		String valor = this.ajaxService.getCodigoResultadoChequeo(numeroPedido);
		return valor;
	}
	
	
	

	/**
	 * @return the valorInicial
	 */
	public String getValorInicial() {
		return valorInicial;
	}



	/**
	 * @param valorInicial the valorInicial to set
	 */
	public void setValorInicial(String valorInicial) {
		this.valorInicial = valorInicial;
	}
	
	
	
	
}
