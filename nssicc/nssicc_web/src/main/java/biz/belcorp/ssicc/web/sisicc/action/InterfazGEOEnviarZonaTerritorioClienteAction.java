package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.ClienteUAGenerarService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazGEOEnviarZonaTerritorioClienteForm;

@ManagedBean
@SessionScoped
public class InterfazGEOEnviarZonaTerritorioClienteAction extends BaseInterfazAbstractAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3888177872134010209L;

	private Long numeroClientesGenerar; 
	
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception 
	{
		InterfazGEOEnviarZonaTerritorioClienteForm formInterfaz = new InterfazGEOEnviarZonaTerritorioClienteForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}

		ClienteUAGenerarService svc = (ClienteUAGenerarService) getBean("sisicc.clienteUAGenerarService");
		this.numeroClientesGenerar = svc.getNumeroClientesUAGenerar();
		
	}

	public Long getNumeroClientesGenerar() {
		return numeroClientesGenerar;
	}

	public void setNumeroClientesGenerar(Long numeroClientesGenerar) {
		this.numeroClientesGenerar = numeroClientesGenerar;
	}

}
