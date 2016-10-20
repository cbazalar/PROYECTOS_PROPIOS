package biz.belcorp.ssicc.web.spusicc.inc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.NuevaConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.RangoConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.util.StringUtil;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCProgramasConstanciaForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCProgramasConstanciaNuevasForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCProgramasConstanciaRangosForm;
import biz.belcorp.ssicc.web.spusicc.inc.form.MantenimientoINCProgramasConstanciaSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes", "unchecked"})
public class MantenimientoINCProgramasConstanciaSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4597986501688073633L;
	
	private String numeroUno = Constants.NUMERO_UNO;
	private String numeroCero = Constants.NUMERO_CERO;
	private List incProgramasConstanciaProgramaPuntosRangosList;
	private List incProgramasConstanciaProgramaPuntosNuevasList;
	private MantenimientoINCProgramasConstanciaRangosForm mantenimientoINCProgramasConstanciaRangosForm;
	private MantenimientoINCProgramasConstanciaNuevasForm mantenimientoINCProgramasConstanciaNuevasForm;
	private RangoConstanciaProgramaPuntos rangosSeleccionados;
	private NuevaConstanciaProgramaPuntos nuevasSeleccionados;
//	private List rangosSeleccionados;
	private DataTableModel tablaRangosModel; 
	private DataTableModel tablaNuevasModel; 
	
	//agregue
	private Boolean campanyaMaximo;
	private Boolean mostrarCampoPuntos;

	@Override
	protected String getSalirForward() {
		return "mantenimientoINCProgramasConstanciaList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoINCProgramasConstanciaForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoINCProgramasConstanciaSearchForm formSearch = new MantenimientoINCProgramasConstanciaSearchForm();
		return formSearch;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{		
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		MantenimientoINCProgramasConstanciaSearchForm f = (MantenimientoINCProgramasConstanciaSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		
		Map params = BeanUtils.describe(f);		
		List lista = service.getProgramasConstanciaProgramaPuntosByCriteria(params);		
		
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method - MantenimientoINCProgramasConstanciaAction");
		}
		
		MantenimientoINCProgramasConstanciaForm f = (MantenimientoINCProgramasConstanciaForm) this.formMantenimiento;
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		boolean isNew = f.isNewRecord();
		
		//pasamos el valor de campanyaMaximo
		if(this.campanyaMaximo)
			f.setCampanyaMaxima("1");
		else
			f.setCampanyaMaxima("0");
		
		ConstanciaProgramaPuntos cpp = new ConstanciaProgramaPuntos();		
		BeanUtils.copyProperties(cpp, f);
		
        try {
        	//Validamos que el concurso multipunto exista
        	if(StringUtils.isNotBlank(cpp.getCodigoMultiPunto()))
        	{
        		LabelValue programa = ajaxService.getConcursoProgramaPuntos(cpp.getCodigoPais(), cpp.getCodigoMultiPunto());
        		
        		if(programa == null)
        		{
                    this.addError("Error: ", this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.concursoNoExiste"));
                    return false;
        		}
        		else
        		{
        			if(StringUtils.equals(programa.getValue(), Constants.NUMERO_CERO))
        			{
        				this.addError("Error: ", this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.noEsProgramaPuntos"));
                        return false;
        			}
        		}        		
        	}
        		
            if (isNew)
    			service.insertConstanciaProgramaPuntos(cpp, usuario);
            else
    			service.updateConstanciaProgramaPuntos(cpp, usuario);
        }
        catch (InvalidIdentifierException iie) {
            String codigo = iie.getIdentifier().toString();
            this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
            return false;
        }
        		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("setEditAttributes - MantenimientoINCProgramasConstanciaAction");
		}

		MantenimientoINCProgramasConstanciaForm f = new MantenimientoINCProgramasConstanciaForm();
		MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		this.mostrarBotonSave = true;
		
		f.setCodigo(null);
		f.setCampanyaInicio(null);
		f.setCampanyaFin(null);
		f.setCodigoMultiPunto(null);
		f.setMontoMinimo(null);
		f.setIndicadorActivo(Constants.NUMERO_UNO);
		f.setDescripcionMultiPunto(null);
		f.setFlagCampanyaProcesoMayor(false);
		f.setCodigoPais(pais.getCodigo());
		//agregue
		f.setCampanyaMaxima("1");
		this.campanyaMaximo = true;
		f.setEvaluacionPrograma("P");

		try {
			ConstanciaProgramaPuntos registroSeleccionado = (ConstanciaProgramaPuntos) this.beanRegistroSeleccionado;
			String codigo = registroSeleccionado.getCodigo();

			if (!this.accion.equals(this.ACCION_NUEVO)) 
			{
				if (StringUtils.isNotBlank(codigo)) {
					ConstanciaProgramaPuntos cpp = new ConstanciaProgramaPuntos();
					cpp.setCodigo(codigo);
					cpp.setCodigoPais(pais.getCodigo());
					ConstanciaProgramaPuntos obj = service.getConstanciaProgramaPuntos(cpp);

					BeanUtils.copyProperties(f, obj);

					if (obj != null && StringUtils.isNotBlank(obj.getCodigoMultiPunto())) {
						LabelValue programa = ajaxService.getConcursoProgramaPuntos(obj.getCodigoPais(), obj.getCodigoMultiPunto());
						if (programa != null)
							f.setDescripcionMultiPunto(programa.getLabel());
					}

					// Obtenemos la campaña activa
					Map criteriaPeriodo = new HashMap();
					MantenimientoOCRPedidoControlFacturacionService controlFactService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
					criteriaPeriodo.put("codigoPais", pais.getCodigo());
					criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
					criteriaPeriodo.put("indicadorActiva", Constants.NUMERO_UNO);

					PedidoControlFacturacion controlFacturacion = controlFactService.getControlFacturacionById(criteriaPeriodo);

					if (Integer.parseInt(controlFacturacion.getCodigoPeriodo()) > Integer.parseInt(obj.getCampanyaInicio()))
						f.setFlagCampanyaProcesoMayor(true);
					
					if(StringUtils.isNotBlank(f.getCampanyaMaxima()) && f.getCampanyaMaxima().equals("1"))
						this.campanyaMaximo = true;
					else
						this.campanyaMaximo = false;

					f.setNewRecord(false);
					
					if(this.accion.equals(this.ACCION_CONSULTAR))
						this.mostrarBotonSave = false;
					else
						this.mostrarBotonSave = true;
				}
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonEliminar = false;		
	}

	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoINCProgramasConstanciaForm f = (MantenimientoINCProgramasConstanciaForm) this.formMantenimiento;
		boolean registro = f.isNewRecord();
		if(registro)
			return "mantenimientoINCProgramasConstanciaForm.created";
		else
			return "mantenimientoINCProgramasConstanciaForm.updated";
	}
	
	public void rangos(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("rangos - MantenimientoINCProgramasConstanciaAction");
		}

		try {

			ConstanciaProgramaPuntos registroSeleccionado = (ConstanciaProgramaPuntos) this.beanRegistroSeleccionado;
			if (registroSeleccionado == null)
				this.addWarn("", this.getResourceMessage("errors.select.item"));
			else {
				this.mostrarBotonSave = false;
				MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
				MantenimientoINCProgramasConstanciaRangosForm f = new MantenimientoINCProgramasConstanciaRangosForm();
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

				f.setCodigoProgramaConstancia(registroSeleccionado.getCodigo());
				f.setCodigoPais(usuario.getCodigoPais());
//				f.setSelectedItems(null);

				Map params = BeanUtils.describe(f);
				List rangos = service.getRangoConstanciaProgramaPuntosList(params);
				
				this.incProgramasConstanciaProgramaPuntosRangosList = rangos;
				this.tablaRangosModel = new DataTableModel(this.incProgramasConstanciaProgramaPuntosRangosList);
				this.mantenimientoINCProgramasConstanciaRangosForm = f;
				
				//evaluamos campo EvaluacionPrograma
				if(StringUtils.isNotBlank(registroSeleccionado.getEvaluacionPrograma()) 
						&& registroSeleccionado.getEvaluacionPrograma().equals("P"))
				{
					this.mostrarCampoPuntos = true;
				}else
					this.mostrarCampoPuntos = false;

				this.redireccionarPagina("mantenimientoINCProgramasConstanciaRangosForm");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void agregarrango(ActionEvent event) throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("agregarrango - MantenimientoINCProgramasConstanciaAction");
		}

		try {

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			MantenimientoINCProgramasConstanciaRangosForm f = (MantenimientoINCProgramasConstanciaRangosForm) this.mantenimientoINCProgramasConstanciaRangosForm;

			List rangoList = this.incProgramasConstanciaProgramaPuntosRangosList;

			if (rangoList == null)
				rangoList = new ArrayList();

			RangoConstanciaProgramaPuntos rango = new RangoConstanciaProgramaPuntos();

			BeanUtils.copyProperties(rango, f);

			// Insertamos en BD
			service.insertRangoConstanciaProgramaPuntos(rango, usuario);
			//

			Map params = BeanUtils.describe(rango);
			params.put("rangoFinal", null);

			rangoList = service.getRangoConstanciaProgramaPuntosList(params);

			this.incProgramasConstanciaProgramaPuntosRangosList = rangoList;
			this.tablaRangosModel = new DataTableModel(this.incProgramasConstanciaProgramaPuntosRangosList);

			f.setRangoFinal("");
			f.setPuntosAbono("");
			f.setFactorMultiplicador("");

			this.addInfo("", this.getResourceMessage("mantenimientoINCProgramasConstanciaRangosForm.rango.agregado"));
		} catch (InvalidIdentifierException iie) {
			this.addError("Error: ", this.getResourceMessage("mantenimientoINCProgramasConstanciaRangosForm.error.rango.existe"));
		}catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void eliminarrango(ActionEvent event)
	{

		if(log.isDebugEnabled()){
			log.debug("eliminarrango - MantenimientoINCProgramasConstanciaAction");
		}
		
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			MantenimientoINCProgramasConstanciaRangosForm f = (MantenimientoINCProgramasConstanciaRangosForm) this.mantenimientoINCProgramasConstanciaRangosForm;
			
			if(this.rangosSeleccionados != null)
			{
				String rangoFinal = this.rangosSeleccionados.getRangoFinal();
				
				if(StringUtils.isNotBlank(rangoFinal))
				{
					if(log.isDebugEnabled())
						log.debug("rangoFinal: " + rangoFinal);
									
					RangoConstanciaProgramaPuntos rango = new RangoConstanciaProgramaPuntos();
					rango.setCodigoPais(f.getCodigoPais());
					rango.setCodigoProgramaConstancia(f.getCodigoProgramaConstancia());
					rango.setRangoFinal(rangoFinal);
					
					service.deleteRangoConstanciaProgramaPuntos(rango, usuario);
					
					//Lista
					Map params = BeanUtils.describe(rango);
					params.put("rangoFinal", null);
					
					List rangos = service.getRangoConstanciaProgramaPuntosList(params);
					this.incProgramasConstanciaProgramaPuntosRangosList = rangos;
					this.tablaRangosModel = new DataTableModel(this.incProgramasConstanciaProgramaPuntosRangosList);					
				}
			}
			
			this.addInfo("", this.getResourceMessage("mantenimientoINCProgramasConstanciaRangosForm.rango.eliminado"));		
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}			
	}
	
	public void nuevas(ActionEvent event) 
	{
		if (log.isDebugEnabled()) {
			log.debug("nuevas - MantenimientoINCProgramasConstanciaAction");
		}

		try {

			ConstanciaProgramaPuntos registroSeleccionado = (ConstanciaProgramaPuntos) this.beanRegistroSeleccionado;
			if (registroSeleccionado == null)
				this.addWarn("", this.getResourceMessage("errors.select.item"));
			else {
				this.mostrarBotonSave = false;
				MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
				MantenimientoINCProgramasConstanciaNuevasForm f = new MantenimientoINCProgramasConstanciaNuevasForm();
				Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

				f.setCodigoProgramaConstancia(registroSeleccionado.getCodigo());
				f.setCodigoPais(usuario.getCodigoPais());
//				f.setSelectedItems(null);

				Map params = BeanUtils.describe(f);
				List nuevas = service.getNuevaConstanciaProgramaPuntosList(params);
				
				this.incProgramasConstanciaProgramaPuntosNuevasList = nuevas;
				this.tablaNuevasModel = new DataTableModel(this.incProgramasConstanciaProgramaPuntosNuevasList);
				this.mantenimientoINCProgramasConstanciaNuevasForm = f;

				this.redireccionarPagina("mantenimientoINCProgramasConstanciaNuevasForm");
			}
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public void agregarNueva(ActionEvent event) throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("agregarNueva - MantenimientoINCProgramasConstanciaAction");
		}

		try {

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			MantenimientoINCProgramasConstanciaNuevasForm f = (MantenimientoINCProgramasConstanciaNuevasForm) this.mantenimientoINCProgramasConstanciaNuevasForm;

			if(Integer.parseInt(f.getPedidosExigidos())>Integer.parseInt(f.getPeriodosExigidos())) {
				this.addError("Error: ", this.getResourceMessage("mantenimientoINCProgramasConstanciaNuevasForm.msg.validarExigidas"));
				return;
			}
			
			List nuevaList = this.incProgramasConstanciaProgramaPuntosNuevasList;

			if (nuevaList == null)
				nuevaList = new ArrayList();

			NuevaConstanciaProgramaPuntos nueva = new NuevaConstanciaProgramaPuntos();

			BeanUtils.copyProperties(nueva, f);

			// Insertamos en BD
			service.insertNuevaConstanciaProgramaPuntos(nueva, usuario);
			//

			Map params = BeanUtils.describe(nueva);
			params.put("periodosExigidos", null);

			nuevaList = service.getNuevaConstanciaProgramaPuntosList(params);

			this.incProgramasConstanciaProgramaPuntosNuevasList = nuevaList;
			this.tablaNuevasModel = new DataTableModel(this.incProgramasConstanciaProgramaPuntosNuevasList);

			f.setPeriodosExigidos("");
			f.setPedidosExigidos("");
			f.setPuntosAbonar("");

			this.addInfo("", this.getResourceMessage("mantenimientoINCProgramasConstanciaNuevasForm.nueva.agregado"));
		} catch (InvalidIdentifierException iie) {
			this.addError("Error: ", this.getResourceMessage("mantenimientoINCProgramasConstanciaNuevasForm.error.nueva.existe"));
		}catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	public void eliminarNueva(ActionEvent event)
	{

		if(log.isDebugEnabled()){
			log.debug("eliminarNueva - MantenimientoINCProgramasConstanciaAction");
		}
		
		try {
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			MantenimientoINCConfiguracionConcursoService service = (MantenimientoINCConfiguracionConcursoService) getBean("spusicc.mantenimientoINCConfiguracionConcursoService");
			MantenimientoINCProgramasConstanciaNuevasForm f = (MantenimientoINCProgramasConstanciaNuevasForm) this.mantenimientoINCProgramasConstanciaNuevasForm;
			
			if(this.nuevasSeleccionados != null)
			{
				String periodosExigidos = this.nuevasSeleccionados.getPeriodosExigidos();
				
				if(StringUtils.isNotBlank(periodosExigidos))
				{
					if(log.isDebugEnabled())
						log.debug("periodosExigidos: " + periodosExigidos);
									
					NuevaConstanciaProgramaPuntos nueva = new NuevaConstanciaProgramaPuntos();
					nueva.setCodigoPais(f.getCodigoPais());
					nueva.setCodigoProgramaConstancia(f.getCodigoProgramaConstancia());
					nueva.setPeriodosExigidos(periodosExigidos);
					
					service.deleteNuevaConstanciaProgramaPuntos(nueva, usuario);
					
					//Lista
					Map params = BeanUtils.describe(nueva);
					params.put("periodosExigidos", null);
					
					List nuevas = service.getNuevaConstanciaProgramaPuntosList(params);
					this.incProgramasConstanciaProgramaPuntosNuevasList = nuevas;
					this.tablaNuevasModel = new DataTableModel(this.incProgramasConstanciaProgramaPuntosNuevasList);					
				}
			}
			
			this.addInfo("", this.getResourceMessage("mantenimientoINCProgramasConstanciaNuevasForm.nueva.eliminado"));		
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}			
	}
	
	public void cargarDescripcionProgramaConcursoPuntos()
	{
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoINCProgramasConstanciaForm f = (MantenimientoINCProgramasConstanciaForm) this.formMantenimiento;
		
		LabelValue resultado =  ajax.getConcursoProgramaPuntos(pais.getCodigo(), f.getCodigoMultiPunto());
		if(resultado != null)
		{
			//Verificamaos si es programa de puntos		
			if(resultado.getValue().equals("0"))
			{
				//No es programa de puntos
				this.addWarn("", this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.noEsProgramaPuntos"));
				f.setDescripcionMultiPunto(null);							
			}
			else
			{
				f.setDescripcionMultiPunto(resultado.getLabel());
			}	
		}else
		{
			this.addError("Error: ", this.getResourceMessage("mantenimientoINCConfiguracionConcursoForm.msg.concursoNoExiste"));
			f.setDescripcionMultiPunto(null);					
		}		
	}
	
	@Override
	public String setValidarMantenimiento() 
	{
		String mensaje = null;
		MantenimientoINCProgramasConstanciaForm f = (MantenimientoINCProgramasConstanciaForm) this.formMantenimiento;
		
		
		if(StringUtils.isNotBlank(f.getCampanyaInicio()) && StringUtils.isNotBlank(f.getCampanyaFin()))
		{
			int periodoInicio = Integer.parseInt(f.getCampanyaInicio());
			int periodoFin = Integer.parseInt(f.getCampanyaFin());
			
			if(periodoInicio > periodoFin)
				mensaje = this.getResourceMessage("mantenimientoINCProgramasConstanciaForm.error.campanyas");			
		}		
		
		return mensaje;	
	}
	
	@Override
	public String setValidarConfirmar(String accion) 
	{
		String mensaje = null;
		
		if(accion.equals("EliminarRango"))
		{
			if(this.rangosSeleccionados == null)
				mensaje= this.getResourceMessage("errors.select.item");
		}
		
		if(accion.equals("EliminarNueva"))
		{
			if(this.nuevasSeleccionados == null)
				mensaje= this.getResourceMessage("errors.select.item");
		}
		
		return mensaje;
	}
	
	public String getNumeroUno() {
		return numeroUno;
	}

	public void setNumeroUno(String numeroUno) {
		this.numeroUno = numeroUno;
	}

	public List getIncProgramasConstanciaProgramaPuntosRangosList() {
		return incProgramasConstanciaProgramaPuntosRangosList;
	}

	public void setIncProgramasConstanciaProgramaPuntosRangosList(
			List incProgramasConstanciaProgramaPuntosRangosList) {
		this.incProgramasConstanciaProgramaPuntosRangosList = incProgramasConstanciaProgramaPuntosRangosList;
	}

	public MantenimientoINCProgramasConstanciaRangosForm getMantenimientoINCProgramasConstanciaRangosForm() {
		return mantenimientoINCProgramasConstanciaRangosForm;
	}

	public void setMantenimientoINCProgramasConstanciaRangosForm(
			MantenimientoINCProgramasConstanciaRangosForm mantenimientoINCProgramasConstanciaRangosForm) {
		this.mantenimientoINCProgramasConstanciaRangosForm = mantenimientoINCProgramasConstanciaRangosForm;
	}

	public RangoConstanciaProgramaPuntos getRangosSeleccionados() {
		return rangosSeleccionados;
	}

	public void setRangosSeleccionados(RangoConstanciaProgramaPuntos rangosSeleccionados) {
		this.rangosSeleccionados = rangosSeleccionados;
	}

	public DataTableModel getTablaRangosModel() {
		return tablaRangosModel;
	}

	public void setTablaRangosModel(DataTableModel tablaRangosModel) {
		this.tablaRangosModel = tablaRangosModel;
	}

	public String getNumeroCero() {
		return numeroCero;
	}

	public void setNumeroCero(String numeroCero) {
		this.numeroCero = numeroCero;
	}

	public Boolean getCampanyaMaximo() {
		return campanyaMaximo;
	}

	public void setCampanyaMaximo(Boolean campanyaMaximo) {
		this.campanyaMaximo = campanyaMaximo;
	}

	public Boolean getMostrarCampoPuntos() {
		return mostrarCampoPuntos;
	}

	public void setMostrarCampoPuntos(Boolean mostrarCampoPuntos) {
		this.mostrarCampoPuntos = mostrarCampoPuntos;
	}

	/**
	 * @return the incProgramasConstanciaProgramaPuntosNuevasList
	 */
	public List getIncProgramasConstanciaProgramaPuntosNuevasList() {
		return incProgramasConstanciaProgramaPuntosNuevasList;
	}

	/**
	 * @param incProgramasConstanciaProgramaPuntosNuevasList the incProgramasConstanciaProgramaPuntosNuevasList to set
	 */
	public void setIncProgramasConstanciaProgramaPuntosNuevasList(
			List incProgramasConstanciaProgramaPuntosNuevasList) {
		this.incProgramasConstanciaProgramaPuntosNuevasList = incProgramasConstanciaProgramaPuntosNuevasList;
	}

	/**
	 * @return the mantenimientoINCProgramasConstanciaNuevasForm
	 */
	public MantenimientoINCProgramasConstanciaNuevasForm getMantenimientoINCProgramasConstanciaNuevasForm() {
		return mantenimientoINCProgramasConstanciaNuevasForm;
	}

	/**
	 * @param mantenimientoINCProgramasConstanciaNuevasForm the mantenimientoINCProgramasConstanciaNuevasForm to set
	 */
	public void setMantenimientoINCProgramasConstanciaNuevasForm(
			MantenimientoINCProgramasConstanciaNuevasForm mantenimientoINCProgramasConstanciaNuevasForm) {
		this.mantenimientoINCProgramasConstanciaNuevasForm = mantenimientoINCProgramasConstanciaNuevasForm;
	}

	/**
	 * @return the nuevasSeleccionados
	 */
	public NuevaConstanciaProgramaPuntos getNuevasSeleccionados() {
		return nuevasSeleccionados;
	}

	/**
	 * @param nuevasSeleccionados the nuevasSeleccionados to set
	 */
	public void setNuevasSeleccionados(
			NuevaConstanciaProgramaPuntos nuevasSeleccionados) {
		this.nuevasSeleccionados = nuevasSeleccionados;
	}

	/**
	 * @return the tablaNuevasModel
	 */
	public DataTableModel getTablaNuevasModel() {
		return tablaNuevasModel;
	}

	/**
	 * @param tablaNuevasModel the tablaNuevasModel to set
	 */
	public void setTablaNuevasModel(DataTableModel tablaNuevasModel) {
		this.tablaNuevasModel = tablaNuevasModel;
	}

//	public List getRangosSeleccionados() {
//		return rangosSeleccionados;
//	}
//
//	public void setRangosSeleccionados(List rangosSeleccionados) {
//		this.rangosSeleccionados = rangosSeleccionados;
//	}

}
