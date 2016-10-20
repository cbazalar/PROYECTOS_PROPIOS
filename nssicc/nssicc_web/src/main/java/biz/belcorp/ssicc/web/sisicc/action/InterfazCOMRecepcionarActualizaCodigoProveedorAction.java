package biz.belcorp.ssicc.web.sisicc.action;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazCOMRecepcionarActualizaCodigoProveedorForm;


/**
 * The Class InterfazCOMRecepcionarActualizaCodigoProveedorAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 23/12/2014
 */
@ManagedBean
@SessionScoped
public class InterfazCOMRecepcionarActualizaCodigoProveedorAction extends BaseInterfazAbstractAction {

	private static final long serialVersionUID = -6383686942588515401L;
	
	/** FIXME Para borrar porque se migro al nuevo esquema de Interfaces.
	 * 	efernandezo
	 */ 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction#devuelveFormInterfaz()
	 */
	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		InterfazCOMRecepcionarActualizaCodigoProveedorForm form = new InterfazCOMRecepcionarActualizaCodigoProveedorForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		
	}

	/** FIXME Para borrar porque se migro al nuevo esquema de Interfaces.
	 * 	efernandezo
	 */ 
	/*
	 
	protected ActionForward cancelled(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("mainMenu");
	}

	public ActionForward upload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'upload' method");
		}

		ActionMessages messages = new ActionMessages();
		Usuario usuario = getUsuario(request.getSession());
		
		InterfazPERRecepcionarConsolidadosOtrosCanalesForm f = (InterfazPERRecepcionarConsolidadosOtrosCanalesForm) form;
		InterfazService service = (InterfazService) getBean("sisicc.interfazService");
		ProcesaInterfaceService procesaService = (ProcesaInterfaceService) getBean("sisicc.procesaInterfaceService");
		
		InterfazPK pk = new InterfazPK(f.getCodigoPais(), f.getCodigoSistema(),	f.getCodigoInterfaz());
		Interfaz interfaz = service.getInterfaz(pk);	
		Map criteria = BeanUtils.describe(f);
		String numeroLote = "";
				
		String[] resultado = procesaService.procesaArchivoEntradaUnitario(
				interfaz, criteria, usuario);

		numeroLote = resultado[0];
		String mensajes = resultado[1];
		String mensajesError = resultado[2];
		if(StringUtils.isNotEmpty(mensajes))
		{	messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(mensajes));
			saveMessages(request, messages);
		}
		else
		{	messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(mensajesError));
			saveErrors(request, messages);
		}

		f.setNumeroLote(numeroLote);
		return mapping.findForward("list");
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		InterfazService service = (InterfazService) getBean("sisicc.interfazService");
		InterfazPERRecepcionarConsolidadosOtrosCanalesForm f = (InterfazPERRecepcionarConsolidadosOtrosCanalesForm) form;

		String codInterfaz = (String) request.getParameter("codigoInterfaz");
		String codSistema = codInterfaz.substring(0, codInterfaz.indexOf('-'))
				.trim();

		//ActionMessages messages = new ActionMessages();

		// Usuario usuario = getUsuario(request.getSession());
		Pais pais = getPais(request.getSession());
		Interfaz interfaz = new Interfaz();

		InterfazPK interfazpk = new InterfazPK();
		interfazpk.setCodigo(codInterfaz);
		interfazpk.setCodigoPais(pais.getCodigo());
		interfazpk.setCodigoSistema(codSistema);

		f.setCodigoPais(pais.getCodigo());
		f.setCodigoSistema(codSistema);
		f.setCodigoInterfaz(codInterfaz);

		interfaz = service.getInterfaz(interfazpk);

		f.setDescripcion(interfaz.getDescripcion());

		log.debug("NULO? " + interfaz.getCodigo() + " "
				+ interfaz.getCodigoPais() + " " + interfaz.getCodigoSistema());

		ParametroInterfazService parametroService = (ParametroInterfazService) getBean("sisicc.parametroInterfazService");
		List lista = parametroService.getParametrosByPKInterfaz(interfazpk);
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		ParametroInterfaz parametroInterfaz = new ParametroInterfaz();

		for (int j = 0; j < lista.size(); j++) {
			parametroInterfaz = (ParametroInterfaz) lista.get(j);
			criteria.put(parametroInterfaz.getNombre(), parametroInterfaz
					.getValor());
		}

		request.getSession().removeAttribute(Constants.CARGA_PRIVILEGE_LIST);
		return mapping.findForward("list");
	}

	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// Removemos el form de su respectivo scope
		removeFormBean(mapping, request);

		// Removemos la lista de resultados
		request.getSession().removeAttribute(Constants.CARGA_PRIVILEGE_LIST);
		return mapping.findForward("list");
	}

	public ActionForward refresh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'refresh' method");
		}
		// Regresamos a la pagina de resultados
		return mapping.findForward("list");
	}*/
}
