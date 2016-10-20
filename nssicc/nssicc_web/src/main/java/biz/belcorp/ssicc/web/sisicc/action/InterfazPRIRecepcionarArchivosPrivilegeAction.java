package biz.belcorp.ssicc.web.sisicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.collections.MapUtils;

import biz.belcorp.ssicc.service.scdf.InterfazPrivilegeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazPRIRecepcionarArchivosPrivilegeForm;

@ManagedBean
@SessionScoped
public class InterfazPRIRecepcionarArchivosPrivilegeAction extends
        BaseInterfazAbstractAction {

	private static final long serialVersionUID = 2984036043327212266L;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazPRIRecepcionarArchivosPrivilegeForm form = new  InterfazPRIRecepcionarArchivosPrivilegeForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#beforeExecuteInterfaz(java.util.Map)
     */
    protected void beforeExecuteInterfaz(Map params) {
        // Obtenemos la referencia al service encargado de limpiar las tablas en
        // las cuales se va a cargar la informacion de los archivos.
        InterfazPrivilegeService service = (InterfazPrivilegeService)getBean("scdf.interfazPrivilegeService");
        String codigoPais = MapUtils.getString(params, "codigoPais");
        
        // Eliminamos la informacion cargada previamente
        service.executeEliminarInformacionPrivilege(codigoPais);
    }

    /*
     * protected ActionForward cancelled(ActionMapping mapping, ActionForm form,
     * HttpServletRequest request, HttpServletResponse response) throws
     * Exception { return mapping.findForward("mainMenu"); }
     * 
     * public ActionForward upload(ActionMapping mapping, ActionForm form,
     * HttpServletRequest request, HttpServletResponse response) throws
     * Exception { if (log.isDebugEnabled()) { log.debug("Entering 'upload'
     * method"); }
     * 
     * ActionMessages messages = new ActionMessages(); // Obtenemos el pais y el
     * usuario de la sesion Pais pais = getPais(request.getSession()); Usuario
     * usuario = getUsuario(request.getSession()); // HttpSession session =
     * request.getSession(true); InterfazPRIRecepcionarArchivosPrivilegeForm f =
     * (InterfazPRIRecepcionarArchivosPrivilegeForm) form; InterfazService
     * service = (InterfazService) getBean("sisicc.interfazService");
     * ProcesaInterfaceService procesaService = (ProcesaInterfaceService)
     * getBean("sisicc.procesaInterfaceService");
     * 
     * InterfazPK pk = new InterfazPK(f.getCodigoPais(), f.getCodigoSistema(),
     * f.getCodigoInterfaz()); Interfaz interfaz = service.getInterfaz(pk);
     * 
     * int tam = f.getCodigoInterfacesEmpaquetadas().length; String[]
     * empaquetadas = new String[tam];
     * 
     * for (int i = 0; i < tam; i++) { log.debug("Codigo interfaz " + i + ": " +
     * f.getCodigoInterfacesEmpaquetadas()[i]); empaquetadas[i] =
     * f.getCodigoInterfacesEmpaquetadas()[i]; }
     * 
     * Map criteria = BeanUtils.describe(f);
     * 
     * String numeroLote = "";
     * 
     * procesaService.executeEliminarInformacionPrivilege(pais);
     * 
     * String[] resultado = procesaService.procesaArchivoEntradaPaquete(
     * interfaz, criteria, empaquetadas, usuario);
     * 
     * numeroLote = resultado[0]; String mensajes = resultado[1]; String
     * mensajesError = resultado[2]; if (StringUtils.isNotEmpty(mensajes)) {
     * messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
     * mensajes)); saveMessages(request, messages); } else {
     * messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
     * mensajesError)); saveErrors(request, messages); }
     * 
     * f.setNumeroLote(numeroLote);
     * 
     * return mapping.findForward("list"); }
     * 
     * public ActionForward view(ActionMapping mapping, ActionForm form,
     * HttpServletRequest request, HttpServletResponse response) throws
     * Exception { if (log.isDebugEnabled()) { log.debug("Entering 'view'
     * method"); }
     * 
     * InterfazService service = (InterfazService)
     * getBean("sisicc.interfazService");
     * InterfazPRIRecepcionarArchivosPrivilegeForm f =
     * (InterfazPRIRecepcionarArchivosPrivilegeForm) form;
     * 
     * String codInterfaz = (String) request.getParameter("codigoInterfaz");
     * String codSistema = codInterfaz.substring(0, codInterfaz.indexOf('-'))
     * .trim();
     * 
     * ActionMessages messages = new ActionMessages(); // Usuario usuario =
     * getUsuario(request.getSession()); Pais pais =
     * getPais(request.getSession()); Interfaz interfaz = new Interfaz();
     * 
     * InterfazPK interfazpk = new InterfazPK();
     * interfazpk.setCodigo(codInterfaz);
     * interfazpk.setCodigoPais(pais.getCodigo());
     * interfazpk.setCodigoSistema(codSistema);
     * 
     * f.setCodigoPais(pais.getCodigo()); f.setCodigoSistema(codSistema);
     * f.setCodigoInterfaz(codInterfaz);
     * 
     * System.out.println(f.getCodigoPais() + " " + f.getCodigoSistema() + " " +
     * f.getCodigoInterfaz());
     * 
     * interfaz = service.getInterfaz(interfazpk);
     * 
     * f.setDescripcion(interfaz.getDescripcion());
     * 
     * List paquete = service.getComponentesInterfazPaquete(interfazpk); if
     * (paquete == null) { // Interfaces Deshabilitadas
     * messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
     * "interfazSiCC.error.interfaz.desabilitada")); saveErrors(request,
     * messages); return mapping.findForward("disabled"); } else
     * request.getSession().setAttribute(Constants.ALL_INTERFACES_PAQUETE,
     * paquete);
     * 
     * log.debug("NULO? " + interfaz.getCodigo() + " " +
     * interfaz.getCodigoPais() + " " + interfaz.getCodigoSistema());
     * 
     * ParametroInterfazService parametroService = (ParametroInterfazService)
     * getBean("sisicc.parametroInterfazService"); List lista =
     * parametroService.getParametrosByPKInterfaz(interfazpk); Map criteria =
     * new HashMap(); criteria.put("codigoPais", pais.getCodigo());
     * ParametroInterfaz parametroInterfaz = new ParametroInterfaz();
     * 
     * for (int j = 0; j < lista.size(); j++) { parametroInterfaz =
     * (ParametroInterfaz) lista.get(j);
     * criteria.put(parametroInterfaz.getNombre(), parametroInterfaz
     * .getValor()); }
     * 
     * request.getSession().removeAttribute(Constants.CARGA_PRIVILEGE_LIST);
     * return mapping.findForward("list"); }
     * 
     * 
     * protected ActionForward unspecified(ActionMapping mapping, ActionForm
     * form, HttpServletRequest request, HttpServletResponse response) throws
     * Exception { // Removemos el form de su respectivo scope
     * removeFormBean(mapping, request); // Removemos la lista de resultados
     * request.getSession().removeAttribute(Constants.CARGA_PRIVILEGE_LIST);
     * return mapping.findForward("list"); }
     * 
     * public ActionForward refresh(ActionMapping mapping, ActionForm form,
     * HttpServletRequest request, HttpServletResponse response) throws
     * Exception { if (log.isDebugEnabled()) { log.debug("Entering 'refresh'
     * method"); } // Regresamos a la pagina de resultados return
     * mapping.findForward("list"); }
     */
}
