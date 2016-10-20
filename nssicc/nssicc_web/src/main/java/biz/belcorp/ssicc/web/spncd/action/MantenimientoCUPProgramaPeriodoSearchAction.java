package biz.belcorp.ssicc.web.spncd.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.MenuOpciones;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaPeriodo;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProgramaPeriodoForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProgramaPeriodoSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoCUPProgramaPeriodoSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private List cupProgramasList;
	private List cupProgramaPeriodoList;
	private List cupNivelList;
	private LabelValue[] allCupNotSelect;
	private LabelValue[] allCupYesSelect;
	private List allCupSelect;
	private DualListModel<Base> listaCupones;
	private Boolean desahabilitar;
	private static final long serialVersionUID = 6067613029962581853L;

	/**
	 * @param programaPeriodoForm
	 * @return
	 * @throws Exception
	 */
	private Map getCriteria(
			MantenimientoCUPProgramaPeriodoSearchForm programaPeriodoForm) {
		try {
			MantenimientoCUPProgramaPeriodoSearchForm form = (MantenimientoCUPProgramaPeriodoSearchForm) this.formBusqueda;
			Map criteria = BeanUtils.describe(form);
			criteria.put("codigoPais", form.getCodigoPais());
			// criteria.put("estadoProgramaPeriodo", Constants.ESTADO_ACTIVO);
			if (StringUtils.isNotBlank(form.getCodigoPeriodo())) {
				criteria.put("codigoPeriodo", form.getCodigoPeriodo());
			}
			return criteria;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPProgramaPeriodoList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCUPProgramaPeriodoForm";
	}

	/**
	 * @param val
	 */
	public void loadCuponesPrograma(ValueChangeEvent val) {
		try {
			String valor = val.getNewValue().toString();
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			MantenimientoCUPProgramaPeriodoForm form = (MantenimientoCUPProgramaPeriodoForm) this.formMantenimiento;
			String codigoPais = form.getCodigoPais();
			String codigoPrograma = valor;
			String nivel = form.getNivel();
			String codigoPeriodo = form.getCodigoPeriodo();
			this.allCupNotSelect = aSvc.getCuponesFaltantesPeriodoByNivel(
					codigoPais, codigoPrograma, nivel, codigoPeriodo, "");
			this.allCupYesSelect = aSvc.getCuponesPeriodoByNivel(codigoPais,
					codigoPrograma, nivel, codigoPeriodo, "");
			if (this.allCupYesSelect != null) {
				String mensaje = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.programa");
				String mensaje1 = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.periodo");
				String mensaje2 = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.modificar");

				String mensajeFinal = mensaje + ' ' + form.getCodigoPrograma()
						+ ' ' + mensaje1 + ' ' + form.getCodigoPeriodo() + ' '
						+ mensaje2;
				this.addWarn("Info : ", mensajeFinal);
			}
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

			Map criteria = new HashMap();
			criteria.put("codigoPais", form.getCodigoPais());
			criteria.put("codigoPrograma",codigoPrograma);
			String indicador = service.getIndicadorCuponReutilizable(criteria);
			
			if (indicador != null
					&& StringUtils.equalsIgnoreCase(indicador,
							Constants.NUMERO_UNO)) {
				form.setValorUnidad(Constants.NUMERO_UNO);
				form.setIndicadorCuponReutilizable(Constants.NUMERO_UNO);
			} else 
				form.setIndicadorCuponReutilizable(Constants.NUMERO_CERO);
			
			
			ArrayList select = new ArrayList<Base>();
			ArrayList notSelect = new ArrayList<Base>();
			
			if(this.allCupNotSelect != null){
				for (int i = 0; i < this.allCupNotSelect.length; i++) {
					Base b = new Base();
					b.setCodigo(this.allCupNotSelect[i].getValue());
					b.setDescripcion(this.allCupNotSelect[i].getLabel());
					select.add(b);
				}
			}
			
			if(this.allCupYesSelect != null){
				for (int i = 0; i < this.allCupYesSelect.length; i++) {
					Base b = new Base();
					b.setCodigo(this.allCupYesSelect[i].getValue());
					b.setDescripcion(this.allCupYesSelect[i].getLabel());
					notSelect.add(b);
				}
			}

			this.listaCupones = new DualListModel<Base>(select, notSelect);
			
			
//			String condicion=aSvc.getIndicadorCuponReutilizable(form.getCodigoPais(),codigoPrograma);
//			
//			if(StringUtils.equals(condicion, "1"))
//				form.setIndicadorCuponReutilizable(condicion);
//			else
//				form.setIndicadorCuponReutilizable("0");
//			
			String data=aSvc.getValorNivel(form.getCodigoPais(),codigoPrograma, form.getNivel(),form.getCodigoPeriodo());
			
			if(StringUtils.equals(form.getIndicadorCuponReutilizable(), "1"))
				form.setValorUnidad("1");
			else{
				if(StringUtils.isNotBlank(data))
					form.setValorUnidad(data);
				else
					form.setValorUnidad("");
			}
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}
	
	/**
	 * @param val
	 */
	public void loadCuponesPeriodo(String periodo) {
		try {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			MantenimientoCUPProgramaPeriodoForm form = (MantenimientoCUPProgramaPeriodoForm) this.formMantenimiento;
			form.setCodigoPeriodo(periodo);
			String codigoPais = form.getCodigoPais();
			String codigoPrograma = form.getCodigoPrograma();
			String nivel = form.getNivel();
			String codigoPeriodo = periodo;
			this.allCupNotSelect = aSvc.getCuponesFaltantesPeriodoByNivel(
					codigoPais, codigoPrograma, nivel, codigoPeriodo, "");
			this.allCupYesSelect = aSvc.getCuponesPeriodoByNivel(codigoPais,
					codigoPrograma, nivel, codigoPeriodo, "");
			if (this.allCupYesSelect != null) {
				String mensaje = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.programa");
				String mensaje1 = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.periodo");
				String mensaje2 = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.modificar");

				String mensajeFinal = mensaje + ' ' + form.getCodigoPrograma()
						+ ' ' + mensaje1 + ' ' + form.getCodigoPeriodo() + ' '
						+ mensaje2;
				this.addWarn("Info : ", mensajeFinal);
			}
//			validacion del periodo
			String data = aSvc.getProgramaPeriodoById(codigoPais, codigoPrograma,codigoPeriodo);

			if (!data.equals("0")) {
				String mensaje = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.programa");
				String mensaje1 = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.periodo");
				String mensaje2 = this
						.getResourceMessage("mensaje.mantenimientoCUPProgramaPeriodoForm.modificar");

				String mensajeFinal = mensaje + ' ' + form.getCodigoPrograma()
						+ ' ' + mensaje1 + ' ' + form.getCodigoPeriodo() + ' '
						+ mensaje2;
				this.addWarn("Info : ", mensajeFinal);
			}
			
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

			Map criteria = new HashMap();
			criteria.put("codigoPais", form.getCodigoPais());
			criteria.put("codigoPrograma",
					form.getCodigoPrograma());
			String indicador = service
					.getIndicadorCuponReutilizable(criteria);
			
			if (indicador != null
					&& StringUtils.equalsIgnoreCase(indicador,
							Constants.NUMERO_UNO)) {
				form.setValorUnidad(Constants.NUMERO_UNO);
				form
						.setIndicadorCuponReutilizable(Constants.NUMERO_UNO);
			} else {
				form
						.setIndicadorCuponReutilizable(Constants.NUMERO_CERO);
			}
			
			ArrayList select = new ArrayList<Base>();
			ArrayList notSelect = new ArrayList<Base>();
			
			if(this.allCupNotSelect != null){
				for (int i = 0; i < this.allCupNotSelect.length; i++) {
					Base b = new Base();
					b.setCodigo(this.allCupNotSelect[i].getValue());
					b.setDescripcion(this.allCupNotSelect[i].getLabel());
					select.add(b);
				}
			}
			
			if(this.allCupYesSelect != null){
				for (int i = 0; i < this.allCupYesSelect.length; i++) {
					Base b = new Base();
					b.setCodigo(this.allCupYesSelect[i].getValue());
					b.setDescripcion(this.allCupYesSelect[i].getLabel());
					notSelect.add(b);
				}
			}
			this.listaCupones = new DualListModel<Base>(select, notSelect);
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * ajax.getCuponesFaltantesPeriodoByNivel( pais.value, codigoPrograma.value,
	 * nivel.value,periodo.value, '', loadCuponesPeriodoFaltantesCallback);
	 * 
	 * @param val
	 */
	public void loadCuponesNivel(ValueChangeEvent val) {
		try {
			MantenimientoCUPProgramaPeriodoForm form = (MantenimientoCUPProgramaPeriodoForm) this.formMantenimiento;
			ArrayList select = new ArrayList<Base>();
			ArrayList notSelect = new ArrayList<Base>();
			
			String valor = val.getNewValue().toString();
			if(!StringUtils.equals(valor, "S")){
			
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String codigoPais = form.getCodigoPais();
			String codigoPrograma = form.getCodigoPrograma();
			String nivel = valor;
			form.setValorUnidad("");
			form.setIndicadorCuponReutilizable("");
			String codigoPeriodo = form.getCodigoPeriodo();
			this.allCupNotSelect = aSvc.getCuponesFaltantesPeriodoByNivel(
					codigoPais, codigoPrograma, nivel, codigoPeriodo, "");
			this.allCupYesSelect = aSvc.getCuponesPeriodoByNivel(codigoPais,
					codigoPrograma, nivel, codigoPeriodo, "");

			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

			Map criteria = new HashMap();
			criteria.put("codigoPais", form.getCodigoPais());
			criteria.put("codigoPrograma",
					form.getCodigoPrograma());
			String indicador = service
					.getIndicadorCuponReutilizable(criteria);
			
			if (indicador != null
					&& StringUtils.equalsIgnoreCase(indicador,
							Constants.NUMERO_UNO)) {
				form.setValorUnidad(Constants.NUMERO_UNO);
				form
						.setIndicadorCuponReutilizable(Constants.NUMERO_UNO);
			} else {
				form
						.setIndicadorCuponReutilizable(Constants.NUMERO_CERO);
			}

			
			
			if(this.allCupNotSelect != null){
				for (int i = 0; i < this.allCupNotSelect.length; i++) {
					Base b = new Base();
					b.setCodigo(this.allCupNotSelect[i].getValue());
					b.setDescripcion(this.allCupNotSelect[i].getLabel());
					select.add(b);
				}
			}
			
			if(this.allCupYesSelect != null){
				for (int i = 0; i < this.allCupYesSelect.length; i++) {
					Base b = new Base();
					b.setCodigo(this.allCupYesSelect[i].getValue());
					b.setDescripcion(this.allCupYesSelect[i].getLabel());
					notSelect.add(b);
				}
			}
			this.listaCupones = new DualListModel<Base>(select, notSelect);
			
			//ValorUnidad
			
			String condicion=aSvc.getIndicadorCuponReutilizable(form.getCodigoPais(),form.getCodigoPrograma());
			
			if(StringUtils.equals(condicion, "1"))
				form.setIndicadorCuponReutilizable(condicion);
			else
				form.setIndicadorCuponReutilizable("0");
			
			String data=aSvc.getValorNivel(form.getCodigoPais(),form.getCodigoPrograma(),valor,form.getCodigoPeriodo());
			
			if(StringUtils.equals(form.getIndicadorCuponReutilizable(), "1"))
				form.setValorUnidad("1");
			else{
				if(StringUtils.isNotBlank(data))
					form.setValorUnidad(data);
				else
					form.setValorUnidad("");
			}
			
			//Valor
			
			String nivelValor=aSvc.getValorNivelPremiosElectivos(form.getCodigoPais(),form.getCodigoPrograma(), valor,form.getCodigoPeriodo());
			
			if(StringUtils.isNotBlank(nivelValor))
				form.setValorUnidadPremioElectivo(nivelValor);
			else
				form.setValorUnidadPremioElectivo("");
			
			}else{
				if(StringUtils.equals(form.getIndicadorCuponReutilizable(), "1"))
					form.setValorUnidad("1");
				else
					form.setValorUnidad("");
				form.setValorUnidadPremioElectivo("");				
				this.listaCupones = new DualListModel<Base>(select, notSelect);
			}
			
			
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCUPProgramaPeriodoSearchForm form = new MantenimientoCUPProgramaPeriodoSearchForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		MantenimientoCUPProgramaPeriodoSearchForm form = new MantenimientoCUPProgramaPeriodoSearchForm();
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		List list = service.getProgramasPeriodoByCriteria(getCriteria(form));
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		ProgramaPeriodo form = (ProgramaPeriodo) this.beanRegistroSeleccionado;
		service.deletePeriodoPrograma(form);
		String mensaje = this
				.getResourceMessage("mantenimientoCUPProgramaPeriodoForm.delete.success");
		this.addInfo("Info: ", mensaje);
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		MantenimientoCUPProgramaPeriodoForm cuponesForm = (MantenimientoCUPProgramaPeriodoForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ProgramaPeriodo cupon = new ProgramaPeriodo();
		
//		Setea cuponesAsignados de la listaCupones
		if (this.listaCupones.getTarget() != null && this.listaCupones.getTarget().size() > 0){
			String[] cuponesAsignados = new String[this.listaCupones.getTarget().size()];
			for(int i= 0; i < this.listaCupones.getTarget().size(); i++ ){
				Base cupones = (Base)this.listaCupones.getTarget().get(i);
				cuponesAsignados[i] = cupones.getCodigo();
				cuponesForm.setCuponesAsignados(cuponesAsignados);
			}
		}else{
			cuponesForm.setCuponesAsignados(new String[]{});
		}
		
//		Setea cuponesNoAsignados de la listaCupones
		if(this.listaCupones.getSource() != null && this.listaCupones.getSource().size() > 0){
			String[] cuponesNoAsignados = new String[this.listaCupones.getSource().size()];
			for(int i= 0; i < this.listaCupones.getSource().size(); i++ ){
				Base cupones = (Base)this.listaCupones.getSource().get(i);
				cuponesNoAsignados[i] = cupones.getCodigo();
			}
			cuponesForm.setCuponesNoAsignados(cuponesNoAsignados);
		}else{
			cuponesForm.setCuponesNoAsignados(new String[]{});
		}
		
		BeanUtils.copyProperties(cupon, cuponesForm);
		log.debug("insert bean " + cupon);
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			service.updateProgramaPeriodo(cupon, usuario);
		} else
			service.insertProgramaPeriodo(cupon, usuario);

		return true;
	}
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		return "mantenimientoCUPProgramaPeriodoForm.add.success";
	}

	/**
	 * @param programaPeriodoForm
	 * @return
	 */
	private boolean validationSuccessful(
			MantenimientoCUPProgramaPeriodoForm programaPeriodoForm) {
		boolean isOk = true;
		String mensaje = null;
		if (this.accion.equals(this.ACCION_NUEVO))
			isOk = true;
		else {
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			String programaActivo = service
					.getProgramaActivo(programaPeriodoForm.getCodigoPais());
			if (StringUtils.isEmpty(programaActivo)) {
				mensaje = this.getResourceMessage("errors.programa.activo");
				isOk = false;
				addError("Error: ", mensaje);
				return false;
			} else {
				/*
				 * AjaxService aSvc = (AjaxService) getBean("ajaxService");
				 * String nextPeriodo =
				 * aSvc.getNextPeriodoByCupProgPerio(getPais(
				 * request.getSession()).getCodigo(), programaActivo); if
				 * (StringUtils.isEmpty(nextPeriodo)) {
				 * errors.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage("errors.programa.periodo")); isOk = false; }
				 * else { isOk = true;
				 * 
				 * }
				 */
			}
		}
		return isOk;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setObtenerRegistroAttributes()
	 */
	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'edit' method");
		}
		MantenimientoCUPProgramaPeriodoForm programaPeriodoForm = new MantenimientoCUPProgramaPeriodoForm();

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		programaPeriodoForm.setCodigoPais(pais.getCodigo());
		this.desahabilitar = true;
		if (validationSuccessful(programaPeriodoForm)) {
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			Map criteria = new HashMap();
			criteria.put("codigoPais", pais.getCodigo());
			this.cupProgramasList = service
					.getProgramasDescuentosbyPais(criteria);
			this.cupNivelList = service.getNivelbyPais(criteria);
			programaPeriodoForm.setValorUnidad("");
			programaPeriodoForm.setIndicadorCuponReutilizable("");
			if (!this.accion.equals(this.ACCION_NUEVO)) {
				this.desahabilitar = false;
				ProgramaPeriodo form = (ProgramaPeriodo) this.beanRegistroSeleccionado;
				programaPeriodoForm.setNewRecord(true);
				// String id = form.get
				Map mapPeriodoById = new HashMap();
				mapPeriodoById.put("codigoPais", pais.getCodigo());
				mapPeriodoById.put("codigoPeriodo", form.getCodigoPeriodo());
				mapPeriodoById.put("codigoPrograma", form.getCodigoPrograma());
				ProgramaPeriodo programaPeriodo = service
						.getProgramaPeriodoById(mapPeriodoById);
				BeanUtils.copyProperties(programaPeriodoForm, programaPeriodo);
				criteria.put("codigoPrograma",
						programaPeriodoForm.getCodigoPrograma());

				String indicador = service
						.getIndicadorCuponReutilizable(criteria);
				
				if (indicador != null
						&& StringUtils.equalsIgnoreCase(indicador,Constants.NUMERO_UNO)) {
					programaPeriodoForm.setValorUnidad(Constants.NUMERO_UNO);
					programaPeriodoForm.setIndicadorCuponReutilizable(Constants.NUMERO_UNO);
				} else 
					programaPeriodoForm.setIndicadorCuponReutilizable(Constants.NUMERO_CERO);
				

				programaPeriodoForm.setEditable(StringUtils.equals(programaPeriodo.getEstadoProgramaPeriodo(), "1"));
				this.allCupSelect = new ArrayList();
				ArrayList notSelect = new ArrayList();
				this.listaCupones = new DualListModel<Base>(notSelect,notSelect);
				programaPeriodoForm.setNivel("");
				programaPeriodoForm.setNewRecord(false);

			} else {
				String programaActivo = service.getProgramaActivo(pais
						.getCodigo());
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				String nextPeriodo = aSvc.getNextPeriodoByCupProgPerio(
						pais.getCodigo(), programaActivo);
				programaPeriodoForm.setCodigoPeriodo(nextPeriodo);
				programaPeriodoForm.setCodigoPrograma(programaActivo);
				Base data0 = (Base)this.cupNivelList.get(0);
				programaPeriodoForm.setNivel(data0.getCodigo());
				criteria.put("codigoPrograma",
						programaPeriodoForm.getCodigoPrograma());

				String indicador = service
						.getIndicadorCuponReutilizable(criteria);

				if (indicador != null
						&& StringUtils.equalsIgnoreCase(indicador,
								Constants.NUMERO_UNO)) {
					programaPeriodoForm.setValorUnidad(Constants.NUMERO_UNO);
					programaPeriodoForm
							.setIndicadorCuponReutilizable(Constants.NUMERO_UNO);
				} else 
					programaPeriodoForm.setIndicadorCuponReutilizable(Constants.NUMERO_CERO);				
			
				this.allCupSelect = new ArrayList();
				ArrayList notSelect = new ArrayList();
				this.listaCupones = new DualListModel<Base>(notSelect,	notSelect);
				programaPeriodoForm.setNewRecord(true);

			}
		} else {
			return null;
		}
		return programaPeriodoForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.mostrarBotonConsultar = false;
		this.desahabilitar = false;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.cupProgramasList = service.getProgramasDescuentosbyPais(criteria);
		String programaActivo = service.getProgramaActivo(pais.getCodigo());
		MantenimientoCUPProgramaPeriodoSearchForm programaPeriodoForm = (MantenimientoCUPProgramaPeriodoSearchForm) this.formBusqueda;
		criteria.put("codigoPrograma", programaActivo);
		String codigoPeriodo = service.getPeriodoDefaultByPrograma(criteria);
		if (StringUtils.isEmpty(codigoPeriodo))
			codigoPeriodo = service.getCodigoPeriodoInicial(criteria);
		programaPeriodoForm.setCodigoPeriodo(codigoPeriodo);
		programaPeriodoForm.setCodigoPrograma(programaActivo);

	}
	
	@Override
	public String setValidarMantenimiento(){
		MantenimientoCUPProgramaPeriodoForm f = (MantenimientoCUPProgramaPeriodoForm) this.formMantenimiento;
		if(StringUtils.isBlank(f.getNivel()) || StringUtils.equals(f.getNivel(), "S"))
			return "Nivel: Campo Requerido";
		return "";
	}

	/**
	 * @return the cupProgramasList
	 */
	public List getCupProgramasList() {
		return cupProgramasList;
	}

	/**
	 * @param cupProgramasList
	 *            the cupProgramasList to set
	 */
	public void setCupProgramasList(List cupProgramasList) {
		this.cupProgramasList = cupProgramasList;
	}

	/**
	 * @return the cupProgramaPeriodoList
	 */
	public List getCupProgramaPeriodoList() {
		return cupProgramaPeriodoList;
	}

	/**
	 * @param cupProgramaPeriodoList
	 *            the cupProgramaPeriodoList to set
	 */
	public void setCupProgramaPeriodoList(List cupProgramaPeriodoList) {
		this.cupProgramaPeriodoList = cupProgramaPeriodoList;
	}

	/**
	 * @return the cupNivelList
	 */
	public List getCupNivelList() {
		return cupNivelList;
	}

	/**
	 * @param cupNivelList
	 *            the cupNivelList to set
	 */
	public void setCupNivelList(List cupNivelList) {
		this.cupNivelList = cupNivelList;
	}

	/**
	 * @return the allCupNotSelect
	 */
	public LabelValue[] getAllCupNotSelect() {
		return allCupNotSelect;
	}

	/**
	 * @param allCupNotSelect
	 *            the allCupNotSelect to set
	 */
	public void setAllCupNotSelect(LabelValue[] allCupNotSelect) {
		this.allCupNotSelect = allCupNotSelect;
	}

	/**
	 * @return the allCupSelect
	 */
	public List getAllCupSelect() {
		return allCupSelect;
	}

	/**
	 * @param allCupSelect
	 *            the allCupSelect to set
	 */
	public void setAllCupSelect(List allCupSelect) {
		this.allCupSelect = allCupSelect;
	}

	/**
	 * @return the listaCupones
	 */
	public DualListModel<Base> getListaCupones() {
		return listaCupones;
	}

	/**
	 * @param listaCupones
	 *            the listaCupones to set
	 */
	public void setListaCupones(DualListModel<Base> listaCupones) {
		this.listaCupones = listaCupones;
	}

	/**
	 * @return the desahabilitar
	 */
	public Boolean getDesahabilitar() {
		return desahabilitar;
	}

	/**
	 * @param desahabilitar
	 *            the desahabilitar to set
	 */
	public void setDesahabilitar(Boolean desahabilitar) {
		this.desahabilitar = desahabilitar;
	}

}