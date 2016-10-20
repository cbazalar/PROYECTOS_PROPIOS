package biz.belcorp.ssicc.web.spncd.action;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spncd.model.EquivalenciaMatriz;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPEquivalenciaMatrizForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPEquivalenciaMatrizSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MantenimientoCUPEquivalenciaMatrizSearchAction extends	BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -2834186698131222653L;

	private List cupProgramasList;
	private List cupNivelList;
	private List allCupNotSelect;
	private List allCupSelect;
	private List cupEquivalenciaMatrizList;
	private String mostrarBotonValidar = "false";
	private String mostrarCheckBokx = "true";
	private Boolean indicadorDefaultLove;
	private Boolean indicadorEquivalenciaLove;
	private boolean indicadorKit;

	/**
	 * @param evt
	 * @throws Exception
	 */
	public void valida(ActionEvent evt) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'valida' method");
		}
		try {
			MantenimientoCUPEquivalenciaMatrizForm equivalenciaMatrizForm = (MantenimientoCUPEquivalenciaMatrizForm) this.formMantenimiento;
			if (validationSuccessful(equivalenciaMatrizForm)) {
				equivalenciaMatrizForm.setCodigoVentaValido(true);
				equivalenciaMatrizForm.setMostrarCodigoVentas(false);
				setMostrarCheckBokx("false");

				AjaxService ajax = (AjaxService) getBean("ajaxService");
				equivalenciaMatrizForm.setCodigoProducto(ajax
						.getCodigoProductoByCodigoVenta(
								equivalenciaMatrizForm.getCodigoPeriodo(),
								equivalenciaMatrizForm.getCodigoVenta()));
				equivalenciaMatrizForm.setDescripcionProducto(ajax
						.getDescripcionProductoByCodigoVenta(
								equivalenciaMatrizForm.getCodigoPeriodo(),
								equivalenciaMatrizForm.getCodigoVenta()));
				equivalenciaMatrizForm.setValorUnitario(ajax
						.getValorUnitarioByCodigoVenta(
								equivalenciaMatrizForm.getCodigoPeriodo(),
								equivalenciaMatrizForm.getCodigoVenta()));
			} else {
				equivalenciaMatrizForm.setMostrarCodigoVentas(true);
				equivalenciaMatrizForm.setCodigoVentaValido(false);
				return;
			}
			this.mostrarBotonSave = true;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/**
	 * @param evt
	 * @throws Exception
	 */
	public void validarHomologacion(ActionEvent evt)  {
		try {
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			MantenimientoCUPEquivalenciaMatrizSearchForm equivalenciaMatrizForm = (MantenimientoCUPEquivalenciaMatrizSearchForm) this.formBusqueda;
			String data = null;
			String mensaje = null;
			String codigoPeriodo = equivalenciaMatrizForm.getCodigoPeriodo();
			if (codigoPeriodo != "") {
				data = ajax.getCuponesNoHomologados(
						equivalenciaMatrizForm.getCodigoPais(),
						equivalenciaMatrizForm.getCodigoPrograma(),
						equivalenciaMatrizForm.getCodigoPeriodo());
				if (data != "") {
					mensaje = this
							.getResourceMessage("mantenimientoCUPEquivalenciaMatriz.faltanHomologar");
					this.addError("Error:", mensaje + data);
					return;
				} else {
					mensaje = this
							.getResourceMessage("mantenimientoCUPEquivalenciaMatriz.correctoHomologar");
					this.addInfo("Informacion:", mensaje);
					return;
				}
			} else {
				mensaje = this
						.getResourceMessage("mantenimientoCUPEquivalenciaMatriz.seleccionarPeriodo");
				this.addError("Error:", mensaje);
				return;
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	/**
	 * @param id
	 * @return
	 */
	private Map getCriteria(String id) {
		Map criteria = new HashMap();
		criteria.put("codigoPais", StringUtils.split(id, "-")[0]);
		criteria.put("codigoPeriodo", StringUtils.split(id, "-")[1]);
		criteria.put("codigoPrograma", StringUtils.split(id, "-")[2]);
		criteria.put("codigoNivel", StringUtils.split(id, "-")[3]);
		criteria.put("codigoCupon", StringUtils.split(id, "-")[4]);
		// criteria.put("indicadorLove", (StringUtils.split(id,
		// "-")[5]).trim());
		// criteria.put("indicadorLoveDefault", (StringUtils.split(id,
		// "-")[6]).trim());
		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCUPEquivalenciaMatrizForm cuponesForm = (MantenimientoCUPEquivalenciaMatrizForm) this.formMantenimiento;
		boolean updateFlag = true;
		String id = cuponesForm.getId();
		if (StringUtils.isBlank(id)) {
			updateFlag = false;
		}
		return "mantenimientoCUPEquivalenciaMatrizForm.add.success";
	}

	/**
	 * @param request
	 * @param cuponesForm
	 * @return
	 */
	private boolean validationSuccessful(MantenimientoCUPEquivalenciaMatrizForm cuponesForm) {
		boolean isOk = true;		
		Map map = new HashMap();
		try {
			map = BeanUtils.describe(cuponesForm);
		} catch (IllegalAccessException e1) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e1));
			return false;
		} catch (InvocationTargetException e1) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e1));
			return false;
		} catch (NoSuchMethodException e1) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e1));
			return false;
		}
		
		MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		// Valida que exista en la matriz de precios
		BigDecimal oidOferta = facturacionService.getOfertaDetalleByPeriodoCodigoVenta(map);
		if (oidOferta.intValue() <= 0) {			
			this.addError("Error: ", this.getResourceMessage("errors.codigo.venta.matriz"));
			return false;
		} else {
			try {
				Integer.parseInt(cuponesForm.getCodigoVenta());
			} catch (NumberFormatException e) {				
				this.addError("Error: ", this.getResourceMessage("errors.invalid.id", new Object[]{cuponesForm.getCodigoVenta()}));
				return false;
			}
		}

		EquivalenciaMatriz cupon = new EquivalenciaMatriz();
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		try {
			BeanUtils.copyProperties(cupon, cuponesForm);
		} catch (IllegalAccessException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return false;
		} catch (InvocationTargetException e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return false;
		}

		int total = Integer.parseInt(service.validaPrioridad(cupon).trim());

		if (isUpdate(cuponesForm)){
			if (total > 1){				
				this.addError("Error: ", this.getResourceMessage("errors.codigo.venta.prioridad"));
				return false;
			}		
		}else {
			if (total > 0){					
				this.addError("Error: ", this.getResourceMessage("errors.codigo.venta.prioridad"));
				return false;
			}
		}

		// Validar la asignaci�n de un c�digo de venta a m�s de un cup�n para un
		// mismo periodo y nivel
		Map criteria = new HashMap();
		criteria.put("codigoPais", cupon.getCodigoPais());
		criteria.put("codigoPeriodo", cupon.getCodigoPeriodo());
		criteria.put("codigoPrograma", cupon.getCodigoPrograma());
		criteria.put("codigoNivel", cupon.getCodigoNivel());
		criteria.put("codigoVenta", cupon.getCodigoVenta());
		criteria.put("codigoCupon", cupon.getCodigoCupon());
		EquivalenciaMatriz equivalencia = new EquivalenciaMatriz();
		equivalencia = service.getEquivalenciaMatrizByIdLove(criteria);

		if (equivalencia != null){
			this.addError("Error : ", this.getResourceMessage("errors.codigo.venta.asignacion"));
			return false;
			}

		// Valido que no haya otro codigo de venta igual para el mismo periodo
		if (!service.getCodigoVentaPeriodo(criteria).equals(Constants.NUMERO_CERO)) {
				this.addError("Error : ", this.getResourceMessage("errors.codigo.venta.existe.periodo"));
				return false;
		}
		
		/* INI Validar unidades máximas y mostrar el valor correctos en los premios electivos */
		Integer intValorUnidadesMaximas = Integer.parseInt(cupon.getValorUnidadesMaximas());
		if(intValorUnidadesMaximas.intValue()<=0){			
			this.addError("Error : ", this.getResourceMessage("errors.valorUnidadesMaximas"));
			return false;
		}
		 /* FIN Validar unidades máximas y mostrar el valor correctos en los premios electivos */
				

		return isOk;
	}

	/**
	 * @param cuponesForm
	 * @return
	 */
	private EquivalenciaMatriz getCuponBeanList(
			MantenimientoCUPEquivalenciaMatrizSearchForm cuponesForm) {
		EquivalenciaMatriz sistemabusqueda = (EquivalenciaMatriz) this.beanRegistroSeleccionado;
		String id = sistemabusqueda.getId();
		EquivalenciaMatriz cupon = new EquivalenciaMatriz();
		cupon.setCodigoPais(StringUtils.split(id, "-")[0]);
		cupon.setCodigoPeriodo(StringUtils.split(id, "-")[1]);
		cupon.setCodigoPrograma(StringUtils.split(id, "-")[2]);
		cupon.setCodigoNivel(StringUtils.split(id, "-")[3]);
		cupon.setCodigoCupon(StringUtils.split(id, "-")[4]);
		// cupon.setIndicadorLove(StringUtils.split(id, "-")[5]);
		// cupon.setIndicadorLoveDefault(StringUtils.split(id, "-")[6]);
		return cupon;
	}

	/**
	 * @param request
	 * @param despachoProductoForm
	 * @return
	 */
	private boolean isUpdate(
			MantenimientoCUPEquivalenciaMatrizForm despachoProductoForm) {
		boolean updateFlag = true;
		String id = despachoProductoForm.getId();
		if (StringUtils.isBlank(id)) {
			updateFlag = false;
		}
		return updateFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPEquivalenciaMatrizList";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCUPEquivalenciaMatrizForm";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCUPEquivalenciaMatrizSearchForm form = new MantenimientoCUPEquivalenciaMatrizSearchForm();
		return form;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	private Map getCriteria(MantenimientoCUPEquivalenciaMatrizSearchForm form) {
		try {
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			MantenimientoCUPEquivalenciaMatrizSearchForm programaPeriodoForm = (MantenimientoCUPEquivalenciaMatrizSearchForm) this.formBusqueda;
			Map criteria = BeanUtils.describe(form);
			criteria.put("codigoPais", pais.getCodigo());

			if (StringUtils.isNotBlank(programaPeriodoForm.getCodigoPeriodo())) {
				criteria.put("codigoPeriodo",
						programaPeriodoForm.getCodigoPeriodo());
			}
			return criteria;
		} catch (Exception e) {
			return null;
		}		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		log.debug("delete");
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		MantenimientoCUPEquivalenciaMatrizSearchForm form = (MantenimientoCUPEquivalenciaMatrizSearchForm) this.formBusqueda;
		service.deleteEquivalenciaMatriz(getCuponBeanList(form));

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

		MantenimientoCUPEquivalenciaMatrizForm cuponesForm = (MantenimientoCUPEquivalenciaMatrizForm) this.formMantenimiento;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		if (validationSuccessful(cuponesForm)) {
			EquivalenciaMatriz cupon = new EquivalenciaMatriz();
			BeanUtils.copyProperties(cupon, cuponesForm);
			if (this.indicadorEquivalenciaLove) {
				cupon.setIndicadorLove("1");
			} else {
				cupon.setIndicadorLove(null);

			}
			if (this.indicadorDefaultLove) {
				cupon.setIndicadorLoveDefault("1");
			} else {
				cupon.setIndicadorLoveDefault(null);
			}
			cupon.setCodigoUsuario(usuario.getLogin());

			/* INI SA PER-SiCC--2012-0467 */
			cupon.setIndicadorKit(null);
			if (cuponesForm.isMostrarIndicadorKit()) {
				if (this.indicadorKit) {
					cupon.setIndicadorKit("1");
				}
			}
			
			boolean updateFlag = true;
			// String id = request.getParameter("id");
			String id = cuponesForm.getId();
			if (StringUtils.isBlank(id)) {
				updateFlag = false;
			}
			
			//Encapsula el almacenamiento de equivalencia
			//en las tablas que corresponda
			service.almacenarEquivalencia(usuario, cupon, updateFlag);
			this.find();			
			return true;
		} else {
			return false;
		}
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

		EquivalenciaMatriz sistemabusqueda = (EquivalenciaMatriz) this.beanRegistroSeleccionado;
		this.mostrarBotonSave = false;
		this.indicadorEquivalenciaLove = false;
		this.indicadorDefaultLove = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());

		MantenimientoCUPEquivalenciaMatrizForm equivalenciaMatrizForm = new MantenimientoCUPEquivalenciaMatrizForm();

		equivalenciaMatrizForm.setMostrarCodigoVentas(true);

		/* INI SA PER-SiCC--2012-0467 */
		equivalenciaMatrizForm.setMostrarIndicadorKit(false);
		/* FIN SA PER-SiCC--2012-0467 */

		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id = sistemabusqueda.getId();
			equivalenciaMatrizForm.setCodigoPrograma(sistemabusqueda.getCodigoPrograma());
			EquivalenciaMatriz equivalenciaMatriz = service.getEquivalenciaMatrizById(getCriteria(id));
			BeanUtils.copyProperties(equivalenciaMatrizForm, equivalenciaMatriz);
			
			if (StringUtils.equals(sistemabusqueda.getIndicadorLove(), "1")) {
				this.indicadorEquivalenciaLove = true;
				equivalenciaMatrizForm.setIndicadorEquivalenciaLove("1");
			} else {
				this.indicadorEquivalenciaLove = false;
				equivalenciaMatrizForm.setIndicadorEquivalenciaLove("0");
			}			
				
			if (StringUtils.equals(sistemabusqueda.getIndicadorLoveDefault(), "1")) {
				this.indicadorDefaultLove = true;
				equivalenciaMatrizForm.setIndicadorDefaultLove("1");
			} else {
				this.indicadorDefaultLove = false;
				equivalenciaMatrizForm.setIndicadorDefaultLove("0");
			}		
			
			if (StringUtils.equals(sistemabusqueda.getIndicadorKit(), "1")) {
				this.indicadorKit = true;
				equivalenciaMatrizForm.setIndicadorKit("1");
			} else {
				this.indicadorDefaultLove = false;
				equivalenciaMatrizForm.setIndicadorKit("0");
			}		

			/* INI SA PER-SiCC--2012-0467 */
			if (Constants.NUMERO_UNO.equals(equivalenciaMatrizForm.getIndicadorKit())) {
				equivalenciaMatrizForm.setMostrarIndicadorKit(true);
			} else {
				if (equivalenciaMatrizForm.getCodigoNivel().equals("01")) {
					criteria.put("codigoPrograma",
							equivalenciaMatrizForm.getCodigoPrograma());
					criteria.put("codigoCupon",
							equivalenciaMatrizForm.getCodigoCupon());

					/* INI SA PER-SiCC-2012-0854 */
					criteria.put("codigoPeriodo",
							equivalenciaMatrizForm.getCodigoPeriodo());
					/* FIN SA PER-SiCC-2012-0854 */

					if (service.verificaProgramaPrimerPedido(criteria)) {
						// Se comenta esta validacion para permitir marcar como
						// kit mas de un cupon.
						// if(!service.existenCuponesConIndicadorKit(criteria))

						equivalenciaMatrizForm.setMostrarIndicadorKit(true);
					}
				}
			}
			this.listaBusqueda = this.setFindAttributes();
			this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
			/* FIN SA PER-SiCC--2012-0467 */

			return equivalenciaMatrizForm;
		} else
			return equivalenciaMatrizForm;
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
			log.debug("Entering 'edit' method");
		}
		this.mostrarBotonBuscar = true;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = true;
		this.mostrarBotonNuevo = false;
		this.indicadorEquivalenciaLove = false;
		this.indicadorDefaultLove = false;

		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		this.cupProgramasList = service.getProgramasDescuentosbyPais(criteria);
		this.cupNivelList = service.getNivelbyPais(criteria);
		String programaActivo = service.getProgramaActivo(pais.getCodigo());
		MantenimientoCUPEquivalenciaMatrizSearchForm equivalenciaMatrizForm = (MantenimientoCUPEquivalenciaMatrizSearchForm) this.formBusqueda;
		criteria.put("codigoPrograma", programaActivo);
		String codigoPeriodo = service.getPeriodoDefaultByPrograma(criteria);
		if (StringUtils.isEmpty(codigoPeriodo))
			codigoPeriodo = service.getCodigoPeriodoInicial(criteria);
		equivalenciaMatrizForm.setCodigoPeriodo(codigoPeriodo);
		equivalenciaMatrizForm.setCodigoPrograma(programaActivo);

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
	public List getAllCupNotSelect() {
		return allCupNotSelect;
	}

	/**
	 * @param allCupNotSelect
	 *            the allCupNotSelect to set
	 */
	public void setAllCupNotSelect(List allCupNotSelect) {
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

	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		this.setMostrarBotonValidar("true");
		MantenimientoCUPEquivalenciaMatrizSearchForm form = (MantenimientoCUPEquivalenciaMatrizSearchForm) this.formBusqueda;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		List list = service.getEquivalenciaMatrizByCriteria(getCriteria(form));
		
		return list;
	}
 
	/**
	 * @return the cupEquivalenciaMatrizList
	 */
	public List getCupEquivalenciaMatrizList() {
		return cupEquivalenciaMatrizList;
	}

	/**
	 * @param cupEquivalenciaMatrizList
	 *            the cupEquivalenciaMatrizList to set
	 */
	public void setCupEquivalenciaMatrizList(List cupEquivalenciaMatrizList) {
		this.cupEquivalenciaMatrizList = cupEquivalenciaMatrizList;
	}

	/**
	 * @return the mostrarBotonValidar
	 */
	public String getMostrarBotonValidar() {
		return mostrarBotonValidar;
	}

	/**
	 * @param mostrarBotonValidar
	 *            the mostrarBotonValidar to set
	 */
	public void setMostrarBotonValidar(String mostrarBotonValidar) {
		this.mostrarBotonValidar = mostrarBotonValidar;
	}

	/**
	 * @return the mostrarCheckBokx
	 */
	public String getMostrarCheckBokx() {
		return mostrarCheckBokx;
	}

	/**
	 * @param mostrarCheckBokx
	 *            the mostrarCheckBokx to set
	 */
	public void setMostrarCheckBokx(String mostrarCheckBokx) {
		this.mostrarCheckBokx = mostrarCheckBokx;
	}

	/**
	 * @return the indicadorDefaultLove
	 */
	public Boolean getIndicadorDefaultLove() {
		return indicadorDefaultLove;
	}

	/**
	 * @param indicadorDefaultLove
	 *            the indicadorDefaultLove to set
	 */
	public void setIndicadorDefaultLove(Boolean indicadorDefaultLove) {
		this.indicadorDefaultLove = indicadorDefaultLove;
	}

	/**
	 * @return the indicadorEquivalenciaLove
	 */
	public Boolean getIndicadorEquivalenciaLove() {
		return indicadorEquivalenciaLove;
	}

	/**
	 * @param indicadorEquivalenciaLove
	 *            the indicadorEquivalenciaLove to set
	 */
	public void setIndicadorEquivalenciaLove(Boolean indicadorEquivalenciaLove) {
		this.indicadorEquivalenciaLove = indicadorEquivalenciaLove;
	}

	public boolean isIndicadorKit() {
		return indicadorKit;
	}

	public void setIndicadorKit(boolean indicadorKit) {
		this.indicadorKit = indicadorKit;
	}
	
	
}