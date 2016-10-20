package biz.belcorp.ssicc.web.spncd.action;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DualListModel;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spncd.model.DescuentoCupon;
import biz.belcorp.ssicc.dao.spncd.model.ProgramaCupon;
import biz.belcorp.ssicc.dao.spncd.model.UnidadAdministrativaCupon;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProgramaNuevCuponesForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProgramaNuevCuponesNivelForm;
import biz.belcorp.ssicc.web.spncd.form.MantenimientoCUPProgramaNuevCuponesSearchForm;

@SessionScoped
@ManagedBean
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MantenimientoCUPProgramaNuevCuponesSearchAction extends
		BaseMantenimientoSearchAbstractAction {

	private static final long serialVersionUID = -3711517029766392907L;
	private String codigoPais;
	private List cupProgramasList;
	private List cuponesProgDescuentosList;
	private List siccMarcaList;
	private List siccCanalList;
	private List cupTipoPedidoList;
	private List nvsUnidadAdministrativaList;
	private String siccRegionSeleccionadaList;
	private List cupNivelList;
	private List nvsDescuentosList;
	private boolean indicadorRegaloPedido;
	private boolean indicadorPremioElectivo;
	private boolean indicadorConstanciaCupon;
	private boolean indicadorConstanciaPremio;
	private boolean indicadorCupon;
	private boolean indicadorConstanciaPremioElectivo;
	private boolean indicadorPremioIncentivo;
	private boolean indicadorPremioWeb;
	private boolean indicadorPedidoMixto;
	private boolean indicadorTodasUA;
	private boolean habilitar;
	private boolean ocultarInsertarRegion;
	private boolean ocultarInsertarZona;
	private boolean indicadorCuponReutilizable;
	private String CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA = Constants.CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA;
	private String CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES = Constants.CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES;
	private String listaZonas;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private DataTableModel comDetalleTableModelRegion;
	private DataTableModel comDetalleTableModelDescuento;
	private Object beanRegistroDetalleRegion;
	private Object[] beanRegistroDetalleDescuento;
	ProgramaCupon data;
	String indConstanciaCupon;
	String indCupon;
	String indConstanciaPremio;
	String indConstanciaPremioElectivo;
	String indProgramaObligatorio;
	String indGeneraMensaje;
	String indPremioElectivo;
	String indRegaloPedido;
	String indPremioIncentivo;
	Boolean modificaIndicadores = false;
	String periodoActual;
	String periodoFinal;
	List ListaRegioneIngresadas;
	@ManagedProperty(value = "#{mantenimientoCUPProgramaNuevCuponesNivelAction}")
	private MantenimientoCUPProgramaNuevCuponesNivelAction action;

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getSalirForward()
	 */
	@Override
	protected String getSalirForward() {
		return "mantenimientoCUPProgramaNuevCuponesList";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#getPaginaMantenimiento()
	 */
	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoCUPProgramaNuevCuponesForm";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveFormBusqueda()
	 */
	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoCUPProgramaNuevCuponesSearchForm form = new MantenimientoCUPProgramaNuevCuponesSearchForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setFindAttributes()
	 */
	@Override
	protected List setFindAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		MantenimientoCUPProgramaNuevCuponesSearchForm cuponesForm = (MantenimientoCUPProgramaNuevCuponesSearchForm) this.formBusqueda;

		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		List list = service
				.getProgramasDsctoByCriteria(getCriteria(cuponesForm));
		this.cuponesProgDescuentosList = list;
		return list;
	}

	/**
	 * @param form
	 * @return
	 * @throws Exception
	 */
	private Map getCriteria(MantenimientoCUPProgramaNuevCuponesSearchForm form)
			throws Exception {
		Map criteria = BeanUtils.describe(form);
		criteria.put("estado", Constants.ESTADO_ACTIVO);
		criteria.put("codigoPais", codigoPais);

		if (StringUtils.isNotBlank(form.getCodigoPrograma())) {
			criteria.put("codigoPrograma", form.getCodigoPrograma());
		}

		return criteria;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setDeleteAttributes()
	 */
	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		ProgramaCupon data = (ProgramaCupon) this.beanRegistroSeleccionado;
		service.deleteProgramaDscto(data);
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveAttributes()
	 */
	@Override
	protected boolean setSaveAttributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'save' method");
		}

		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		MantenimientoCUPProgramaNuevCuponesForm cuponesForm = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		boolean bGrabar = true;

		if (validationSuccessful(cuponesForm)) {
			validarCheckBox(cuponesForm);
			/*
			 * if(StringUtils.equals(cuponesForm.getNumeroPedidos(), "0") ||
			 * StringUtils.equals(cuponesForm.getNumeroPedidos(), "0")){
			 * cuponesForm.setIndicadorPedidoMixto(Constants.NUMERO_CERO); }
			 */

			if (cuponesForm.getIndicadorPremioWeb().equals(
					Constants.NUMERO_CERO)) {
				cuponesForm.setNumeroPedidos("");
				cuponesForm.setTipoPedido("");
				cuponesForm.setIndicadorPedidoMixto("");
			} else {
				if (indicadorPremioWeb) {
					String numPedidos = cuponesForm.getNumeroPedidos();
					String numNiveles = cuponesForm.getNumeroNiveles();
					if (StringUtils.isNotBlank(numPedidos)) {
						if (Integer.parseInt(numPedidos) == 0
								|| Integer.parseInt(numPedidos) > Integer
										.parseInt(numNiveles)) {
							bGrabar = false;
							throw new Exception(
									"Numero de Pedidos debe estar en el rango de 1 y "
											+ numNiveles);
						}
					} else {
						bGrabar = false;
						throw new Exception(
								"Numero de Pedidos debe estar en el rango de 1 y "
										+ numNiveles);
					}
				}
			}

			// /////////////////////////////////////////////////////////////////////////////////
			// Valida que para ser obligatorio, debe ser electivo
			if (!StringUtils.equals(cuponesForm.getIndicadorPremioElectivo(), Constants.NUMERO_DOS) && StringUtils.equals(cuponesForm.getIndicadorProgramaObligatorio(), Constants.NUMERO_UNO)) {
				bGrabar = false;
				throw new Exception(
						this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.programa.obligatorio"));
			}

			// Valida que para ser obligatorio, debe ser despacho de cupones
			// desde el 1er pedido
			if (!StringUtils.equals(cuponesForm.getIndicadorCupon(), Constants.NUMERO_UNO) && StringUtils.equals(cuponesForm.getIndicadorProgramaObligatorio(), Constants.NUMERO_UNO)) {
				bGrabar = false;
				throw new Exception(
						this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.programa.primer.pedido"));
			}

			if (cuponesForm.getIndicadorVigencia().equals("P")) {
				if (StringUtils.isBlank(cuponesForm.getNumVigencia())) {
					bGrabar = false;
					throw new Exception("'Vigencia' es un campo requerido.");

				} else {
					if (Integer.parseInt(cuponesForm.getNumVigencia()) <= 0) {
						bGrabar = false;
						throw new Exception(
								"'Vigencia' debe de ser mayor a cero.");
					}
				}
			}

			// Se valida si no esta marcado el check de Todas UAS se haya
			// ingresado al menos una UA
			if (!indicadorTodasUA) {
				if (nvsUnidadAdministrativaList.size() == 0) {
					bGrabar = false;
					throw new Exception(
							this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.notRegistros"));
				}
			}
			
			/* INI JR PER-SiCC-2012-0202 */
			//Se valida si esta el indicador RxP marcado que se haya ingresado monto minimo
			if(this.indicadorRegaloPedido == true){				
				if(StringUtils.isBlank(cuponesForm.getMontoMinimo())){
					this.addError("Error: ", this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.montoMinimo"));
					return false; 
				}
				
				if(Integer.parseInt(cuponesForm.getMontoMinimo()) < 0){
					this.addError("Error: ", this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.montoMinimo.cero"));
					return false; 
				}			
			}
			/* FIN JR PER-SiCC-2012-0202  */

//			Validacion periodos y cupones
			String periodoDesde = cuponesForm.getCampanhaInicial();
			String periodoHasta = cuponesForm.getCampanhaFinal();
			String codigoVentCupIni = cuponesForm.getCodigoVentCupIni();
			String codigoVentCupFin = cuponesForm.getCodigoVentCupFin();
			String cuponInicial = cuponesForm.getCuponInicial();
			String cuponFinal = cuponesForm.getCuponFinal();
			String periodoFinal = cuponesForm.getPeriodoFinal();
			
			Integer a = Integer.parseInt(periodoDesde);
			Integer b = Integer.parseInt(periodoHasta);
			Integer c = Integer.parseInt(codigoVentCupIni);
			Integer d = Integer.parseInt(codigoVentCupFin);
			
			Integer e1 = null;
			if(StringUtils.isNotBlank(periodoFinal)){
				e1 = Integer.parseInt(periodoFinal);
			}
			
			Integer f = null;
			if(StringUtils.isNotBlank(cuponInicial)){
				f = Integer.parseInt(cuponInicial);
			}
			
			Integer g = null;
			if(StringUtils.isNotBlank(cuponFinal)){
				g = Integer.parseInt(cuponFinal);
			}
			
			if ( a > b ){
           		this.addError("Error: ", this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.periodo.inicial.mayor.final"));
            	return false;
            }

            if ( c > d ){
            	this.addError("Error: ", this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.cupon.inicial.mayor.final"));
            	return false;
            }

            if (e1 != null){

	            if ( b < e1 ) {
	            	this.addError("Error: ",this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.periodo.final.mayor", new Object[]{periodoFinal}));
	           		periodoHasta = periodoFinal;
	            	return false;
	            }

            }

            if (f != null){

	            if ( f < c ){
	            	this.addError("Error: ",this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.cupon.inicial.menor", new Object[]{cuponInicial}));
	           		codigoVentCupIni = cuponInicial;
	            	return false;
	            }
            }

            if (g != null){

            	if ( d < g ){
            		this.addError("Error: ",this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.cupon.final.mayor", new Object[]{cuponFinal}));
	           		codigoVentCupFin = cuponFinal;
	            	return false;
	            }
            }
			
			
			
			String indicadorTodasUA = cuponesForm.getIndicadorTodasUA();

			if (log.isDebugEnabled())
				log.debug("indicadorTodasUA: " + indicadorTodasUA);

			// ////////////////////////////////////////////////

			ProgramaCupon cupon = obtenerCupon(cuponesForm);
			cupon.setCodigoUsuario(usuario.getLogin());

			log.debug(this.periodoActual);

			try {
				if (!cuponesForm.isNewRecord()) {
					log.debug("update bean " + cupon);

					if (this.modificaIndicadores) {

						cupon.setIndicadorConstanciaCupon(cuponesForm
								.getIndicadorConstanciaCupon());
						cupon.setIndicadorCupon(cuponesForm.getIndicadorCupon());
						cupon.setIndicadorConstanciaPremio(cuponesForm
								.getIndicadorConstanciaPremio());
						cupon.setIndicadorConstanciaPremioElectivo(cuponesForm
								.getIndicadorConstanciaPremioElectivo());
						cupon.setIndicadorPremioElectivo(cuponesForm
								.getIndicadorPremioElectivo());
						cupon.setIndicadorProgramaObligatorio(cuponesForm
								.getIndicadorProgramaObligatorio());
						cupon.setIndicadorGeneraMensaje(cuponesForm
								.getIndicadorGeneraMensaje());
						cupon.setIndicadorCuponReutilizable(cuponesForm.getIndicadorCuponReutilizable());
					}

					// Se verifica si existen consultoras asociados al programa
					Map criteria = new HashMap();
					criteria.put("codigoPrograma", cupon.getCodigoPrograma());
					criteria.put("usuario", usuario.getLogin());
					criteria.put("estadoEliminado", Constants.ESTADO_INACTIVO); // Permanente

					String verificaConsultora = service
							.verificaConsultoraPrograma(criteria);

					if (Constants.NUMERO_CERO.equals(verificaConsultora)) {
						criteria.put("codigoPais", cupon.getCodigoPais());
						criteria.put("periodoInicio",
								cupon.getCampanhaInicial());
						criteria.put("periodoFin", cupon.getCampanhaFinal());
						criteria.put("periodoActual", this.periodoActual);

						String existeCruce = "";
						// si esta marcado el indicador de Todas UAS se
						// verificaq no exista cruce con otro programa
						if (Constants.NUMERO_UNO.equals(cupon
								.getIndicadorTodasUA())) {

							existeCruce = service.verificaCruce(criteria);

							if (Constants.NUMERO_CERO.equals(existeCruce)) {
								service.updateProgramaDscto(cupon, usuario);
								service.updateUnidadAdministrativa(criteria);
								this.actualizaListaGrillaDescuento(cuponesForm,
										cupon.getListDescuentos());
								service.insertDescuentos(cupon);
							} else {
								bGrabar = false;
								throw new Exception(
										this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.cruce.programa"));

							}
						}// Si no esta marcado
						else {

							if (validaCruceUAS(cupon, service)) {
								service.updateProgramaDscto(cupon, usuario);
								service.updateUnidadAdministrativa(criteria);
								// //////////////////////
								service.insertUnidadAdministrativa(cupon);
								//

								this.actualizaListaGrillaDescuento(cuponesForm,
										cupon.getListDescuentos());
								service.insertDescuentos(cupon);
							} else {
								bGrabar = false;
								throw new Exception(
										this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.cruce.programa"));

							}
						}

					} else {
						criteria.put("codigoPais", codigoPais);
						criteria.put("campanaFin", cupon.getCampanhaFinal());
						criteria.put("usuario", usuario.getLogin());
						service.updateProgramaDscto2(criteria);

						this.actualizaListaGrillaDescuento(cuponesForm,
								cupon.getListDescuentos());
						service.insertDescuentos(cupon);
					}

				} else {
					Map criteria = new HashMap();
					criteria.put("codigoPais", codigoPais);
					criteria.put("periodoInicio", cupon.getCampanhaInicial());
					criteria.put("periodoFin", cupon.getCampanhaFinal());
					criteria.put("periodoActual", this.periodoActual);
					criteria.put("codigoPrograma", cupon.getCodigoPrograma());

					// si esta marcado el indicador de Todas UAS se verificaq no
					// exista cruce con otro programa
					if (Constants.NUMERO_UNO
							.equals(cupon.getIndicadorTodasUA())) {

						// String existeCruce = service.verificaCruce(criteria);
						String existeCruce = Constants.NUMERO_CERO;

						if (Constants.NUMERO_CERO.equals(existeCruce)) {
							// Se valida que no exista cruce x UAS
							if (validaCruceUAS(cupon, service)) {
								service.insertProgramaDscto(cupon, usuario);
								this.actualizaListaGrillaDescuento(cuponesForm,
										cupon.getListDescuentos());
								service.insertDescuentos(cupon);
							} else {
								bGrabar = false;
								throw new Exception(
										this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.cruce.programa"));

							}

						} else {
							bGrabar = false;
							throw new Exception(
									this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.cruce.programa"));

						}
					}// Si no esta marcado
					else {
						// Se valida que no exista cruce x UAS
						if (validaCruceUAS(cupon, service)) {
							service.insertProgramaDscto(cupon, usuario);
							this.actualizaListaGrillaDescuento(cuponesForm,
									cupon.getListDescuentos());
							service.insertDescuentos(cupon);
						} else {
							bGrabar = false;
							throw new Exception(
									this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.cruce.programa"));

						}
					}
				}
			} catch (Exception e) {
				this.addError("Error", obtieneMensajeErrorException(e));
			}

		}

		return bGrabar;
	}

	public void validarCheckBox(MantenimientoCUPProgramaNuevCuponesForm f) {

		if (indicadorCupon)
			f.setIndicadorCupon(Constants.NRO_UNO);
		else
			f.setIndicadorCupon(Constants.NRO_CERO);

		if (indicadorPremioElectivo)
			f.setIndicadorPremioElectivo(Constants.NRO_DOS);
		else
			f.setIndicadorPremioElectivo(Constants.NRO_CERO);
		if (indicadorTodasUA)
			f.setIndicadorTodasUA(Constants.NRO_UNO);
		else
			f.setIndicadorTodasUA(Constants.NRO_CERO);

		if (indicadorPremioWeb)
			f.setIndicadorPremioWeb(Constants.NRO_UNO);
		else
			f.setIndicadorPremioWeb(Constants.NRO_CERO);

		if (indicadorConstanciaCupon)
			f.setIndicadorConstanciaCupon(Constants.NRO_UNO);
		else
			f.setIndicadorConstanciaCupon(Constants.NRO_CERO);
		if (indicadorConstanciaPremio)
			f.setIndicadorConstanciaPremio(Constants.NRO_UNO);
		else
			f.setIndicadorConstanciaPremio(Constants.NRO_CERO);

		if (indicadorConstanciaPremioElectivo)
			f.setIndicadorConstanciaPremioElectivo(Constants.NRO_UNO);
		else
			f.setIndicadorConstanciaPremioElectivo(Constants.NRO_CERO);

		if (indicadorRegaloPedido)
			f.setIndicadorRegaloPedido(Constants.NRO_UNO);
		else
			f.setIndicadorRegaloPedido(Constants.NRO_CERO);

		if (indicadorPremioIncentivo)
			f.setIndicadorPremioIncentivo(Constants.NRO_UNO);
		else
			f.setIndicadorPremioIncentivo(Constants.NRO_CERO);
		
		if (indicadorPedidoMixto)
			f.setIndicadorPedidoMixto(Constants.NRO_UNO);
		else
			f.setIndicadorPedidoMixto(Constants.NRO_CERO);
		
		if(indicadorCuponReutilizable)
			f.setIndicadorCuponReutilizable(Constants.NRO_UNO);
		else
			f.setIndicadorCuponReutilizable(Constants.NRO_CERO);
	}

	public void validarCheckBox2(MantenimientoCUPProgramaNuevCuponesForm f) {

		if (f.getIndicadorCupon().equals(Constants.NRO_UNO))
			indicadorCupon = true;
		else
			indicadorCupon = false;

		if (f.getIndicadorPremioElectivo().equals(Constants.NRO_DOS))
			indicadorPremioElectivo = true;
		else
			indicadorPremioElectivo = false;

		if (nvsUnidadAdministrativaList.size() == 0)
			indicadorTodasUA = true;
		else
			indicadorTodasUA = false;
		if(StringUtils.isBlank(f.getIndicadorPremioWeb())){
			f.setIndicadorPremioWeb("0");
		}
		if (f.getIndicadorPremioWeb().equals(Constants.NRO_UNO))
			indicadorPremioWeb = true;
		else
			indicadorPremioWeb = false;

		if (f.getIndicadorConstanciaCupon().equals(Constants.NRO_UNO))
			indicadorConstanciaCupon = true;
		else
			indicadorConstanciaCupon = false;

		if (f.getIndicadorConstanciaPremio().equals(Constants.NRO_UNO))
			indicadorConstanciaPremio = true;
		else
			indicadorConstanciaPremio = false;
		
		if(StringUtils.isBlank(f.getIndicadorConstanciaPremioElectivo())){
			f.setIndicadorConstanciaPremioElectivo("0");
		}
		if (f.getIndicadorConstanciaPremioElectivo().equals(Constants.NRO_UNO))
			indicadorConstanciaPremioElectivo = true;
		else
			indicadorConstanciaPremioElectivo = false;
		
		if(StringUtils.isBlank(f.getIndicadorRegaloPedido())){
			f.setIndicadorRegaloPedido("0");
		}
		if (f.getIndicadorRegaloPedido().equals(Constants.NRO_UNO))
			indicadorRegaloPedido = true;
		else
			indicadorRegaloPedido = false;
		
		if(StringUtils.isBlank(f.getIndicadorPremioIncentivo())){
			f.setIndicadorPremioIncentivo("0");
		}
		if (f.getIndicadorPremioIncentivo().equals(Constants.NRO_UNO))
			indicadorPremioIncentivo = true;
		else
			indicadorPremioIncentivo = false;
		if(StringUtils.isBlank(f.getIndicadorPedidoMixto())){
			f.setIndicadorPedidoMixto("0");
		}
		if (f.getIndicadorPedidoMixto().equals(Constants.NRO_UNO))
			indicadorPedidoMixto = true;
		else
			indicadorPedidoMixto = false;
		
		if (f.getIndicadorCuponReutilizable().equals(Constants.NRO_UNO))
			indicadorCuponReutilizable = true;
		else
			indicadorCuponReutilizable = false;
	}

	/**
	 * Verifica si existen cruces de campañas por UAS
	 * 
	 * @param cupon
	 * @return
	 */
	private boolean validaCruceUAS(ProgramaCupon cupon,
			MantenimientoCUPProgDsctoService service) {
		String respuesta = "0";
		String periodoInicial = cupon.getCampanhaInicial();
		String periodoFinal = cupon.getCampanhaFinal();
		String region = "";
		String zona = "";
		List listConcursoAmbitoGeografico = cupon
				.getListUnidadesAdministrativas();

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", cupon.getCodigoPrograma());
		criteria.put("periodoInicial", periodoInicial);
		criteria.put("periodoFinal", periodoFinal);
		criteria.put("periodoActual", this.periodoActual);

		if (listConcursoAmbitoGeografico.size() == 0) {
			criteria.put("periodoInicio", periodoInicial);
			criteria.put("periodoFin", periodoFinal);
			criteria.put("periodoActual", this.periodoActual);

			String existeCruce = service.verificaCruce(criteria);

			if (!Constants.NUMERO_CERO.equals(existeCruce)) {
				return false;
			}
		}

		for (int i = 0; i < listConcursoAmbitoGeografico.size(); i++) {
			UnidadAdministrativaCupon unidadAdministrativa = (UnidadAdministrativaCupon) listConcursoAmbitoGeografico
					.get(i);
			region = unidadAdministrativa.getCodigoRegion();
			zona = unidadAdministrativa.getCodigoZona();

			criteria.put("region", region);
			criteria.put("zona", zona);

			service.verificaCruceProgramaUA(criteria);

			respuesta = (String) criteria.get("resultado");

			if (Constants.NUMERO_UNO.equals(respuesta)) {
				break;
			}
		}

		if (Constants.NUMERO_UNO.equals(respuesta)) {
			return false;
		}

		return true;
	}

	/**
	 * Obtiene le objeto Programa cupon
	 * 
	 * @param f
	 * @return
	 */
	private ProgramaCupon obtenerCupon(MantenimientoCUPProgramaNuevCuponesForm f) {
		ProgramaCupon cupon = new ProgramaCupon();

		cupon.setId(f.getId());
		cupon.setCodigoPais(f.getCodigoPais());
		cupon.setCodigoPrograma(f.getCodigoPrograma());
		cupon.setCampanhaInicial(f.getCampanhaInicial());
		cupon.setCampanhaFinal(f.getCampanhaFinal());
		cupon.setCodigoVentCupIni(f.getCodigoVentCupIni());
		cupon.setCodigoVentCupFin(f.getCodigoVentCupFin());
		cupon.setEstadoProg(f.getEstadoProg());
		cupon.setNumVigencia(f.getNumVigencia());
		cupon.setCodigoMarca(f.getCodigoMarca());
		cupon.setCodigoCanal(f.getCodigoCanal());
		cupon.setIndicadorConstanciaCupon(f.getIndicadorConstanciaCupon());
		cupon.setIndicadorConstanciaPremio(f.getIndicadorConstanciaPremio());
		cupon.setIndicadorCupon(f.getIndicadorCupon());
		cupon.setIndicadorConstanciaPremioElectivo(f
				.getIndicadorConstanciaPremioElectivo());
		cupon.setIndicadorProgramaObligatorio(f
				.getIndicadorProgramaObligatorio());
		cupon.setIndicadorPremioElectivo(f.getIndicadorPremioElectivo());
		cupon.setIndicadorGeneraMensaje(f.getIndicadorGeneraMensaje());
		cupon.setIndicadorRegaloPedido(f.getIndicadorRegaloPedido());
		cupon.setMontoMinimo(f.getMontoMinimo());
		cupon.setIndicadorTodasUA(f.getIndicadorTodasUA());
		cupon.setIndicadorPremioIncentivo(f.getIndicadorPremioIncentivo());
		cupon.setNumeroPedidos(f.getNumeroPedidos());
		cupon.setTipoPedido(f.getTipoPedido());
		cupon.setIndicadorPedidoMixto(f.getIndicadorPedidoMixto());
		cupon.setIndicadorPremioWeb(f.getIndicadorPremioWeb());
		cupon.setIndicadorCuponReutilizable(f.getIndicadorCuponReutilizable());

		// Unidades Administrativas
		List listUnidadesAdministrativas = this.nvsUnidadAdministrativaList;
		cupon.setListUnidadesAdministrativas(listUnidadesAdministrativas);
		cupon.setIndActualizarUAS(f.isIndActualizarUAS());

		cupon.setIndicadorVigencia(f.getIndicadorVigencia());

		// Descuentos
		List listDescuentos = this.nvsDescuentosList;
		// this.actualizaListaGrillaDescuento(request, f, listDescuentos);
		cupon.setListDescuentos(listDescuentos);
		cupon.setIndActualizarDES(f.isIndActualizarDES());

		/*
		 * ParametroDescuentoForm[] p1 = f.getParametrosGrupo01();
		 * 
		 * if(p1!=null){ for(int i=0; i<p1.length; i++) { DescuentoCupon
		 * descuento = new DescuentoCupon();
		 * descuento.setCodigoPais(cupon.getCodigoPais());
		 * descuento.setCodigoPrograma(cupon.getCodigoPrograma());
		 * descuento.setCodigoVenta(p1[i].getCodigoVenta());
		 * descuento.setMontoDescuento(p1[i].getMontoDescuento());
		 * descuento.setMontoVentaExigencia(p1[i].getMontoVentaExigencia());
		 * descuento.setNivel(p1[i].getNivel());
		 * descuento.setNivelExigencia(p1[i].getNivelExigencia());
		 * descuento.setUsuario(cupon.getCodigoUsuario());
		 * 
		 * if(p1[i].getEstadoRegistro()==null){
		 * descuento.setEstadoRegistro(Constants.ESTADO_ACTIVO); }else {
		 * descuento.setEstadoRegistro(p1[i].getEstadoRegistro()); }
		 * 
		 * descuentos.add(descuento); } cupon.setListDescuentos(descuentos);
		 * cupon.setIndActualizarDES(f.isIndActualizarDES()); }
		 */

		if (StringUtils.equals(f.getIndicadorVigencia(),
				Constants.CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES))
			cupon.setNumVigencia(null);

		return cupon;
	}

	/**
	 * @param request
	 * @param form
	 * @return
	 */
	private boolean validationSuccessful(
			MantenimientoCUPProgramaNuevCuponesForm form) {
		// if you really like using the validation framework stuff, you can just
		// call ActionErrors errors = form.validate( mapping, request ); in this
		// method
		// and check for errors being empty, if not save them and you're done.
		// I end up finding the validation framework a bit annoying to work
		// with, so I do it
		// old-Skool way. Inevitably in a more complex app you end up having to
		// perform
		// more complex validation than the validation framework provides, so I
		// just assume
		// keep it all here in one place, versus having some handled by xml
		// configuration and
		// some hardcoded.
		boolean isOk = true;

		if ((form.getCodigoVentCupIni() == null)
				|| (form.getCodigoVentCupIni().trim().length() == 0)) {
			// errors.add("codigoVentCupIni", new
			// ActionMessage("errors.required",
			// "CodigoVentCupIni"));
		} else {
			try {
				Integer.parseInt(form.getCodigoVentCupIni());
			} catch (NumberFormatException e) {
				// errors.add("codigoVentCupIni", new ActionMessage(
				// "errors.number", "CodigoVentCupIni"));
			}
			MantenimientoCUPProgramaNuevCuponesForm cuponesForm = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
			MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
			Map criteria = new HashMap();
			try {
				criteria = BeanUtils.describe(cuponesForm);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (isUpdate(cuponesForm)) {
				/*
				 * String codigoCuponInicial =
				 * service.getCodigoCuponInicial(criteria); String
				 * codigoCuponFinal = service.getCodigoCuponFinal(criteria);
				 * String codigoPeriodoInicial=
				 * service.getCodigoPeriodoInicial(criteria); String
				 * codigoPeriodoFinal= service.getCodigoPeriodoFinal(criteria);
				 * 
				 * if (!StringUtils.isEmpty(codigoPeriodoInicial) &&
				 * !StringUtils
				 * .equals(codigoPeriodoInicial,cuponesForm.getCampanhaInicial
				 * ())) errors.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage( "errors.programa.periodo.menor.ingresado"));
				 * if
				 * (cuponesForm.getCampanhaInicial().compareTo(codigoPeriodoInicial
				 * )> 0 ) errors.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage( "errors.programa.periodo.menor")); if
				 * (cuponesForm
				 * .getCampanhaFinal().compareTo(codigoPeriodoFinal)< 0 )
				 * errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				 * "errors.programa.periodo.mayor")); if
				 * (cuponesForm.getCodigoVentCupIni
				 * ().compareTo(codigoCuponInicial)> 0 )
				 * errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				 * "errors.programa.cupon.menor")); if
				 * (cuponesForm.getCodigoVentCupFin
				 * ().compareTo(codigoCuponFinal)< 0 )
				 * errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				 * "errors.programa.cupon.mayor"));
				 */
			}
		}

		// if (!errors.isEmpty()) {
		// saveErrors(request, errors);
		// isOk = false;
		// }

		return isOk;
	}

	/**
	 * @param request
	 * @param cuponesForm
	 * @return
	 */
	private boolean isUpdate(MantenimientoCUPProgramaNuevCuponesForm cuponesForm) {
		boolean updateFlag = true;

		// String id = request.getParameter("id");
		String id = cuponesForm.getId();

		if (StringUtils.isBlank(id)) {
			updateFlag = false;
		}

		return updateFlag;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		MantenimientoCUPProgramaNuevCuponesForm f = new MantenimientoCUPProgramaNuevCuponesForm();
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		
		this.ocultarInsertarRegion = false;
		this.ocultarInsertarZona = false;
		this.siccRegionList = null;
		this.siccZonaList = null;
		
		if (this.accion.equals(this.ACCION_NUEVO)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'add' method");
			}

			/** ********************************************************* */
			/* Obteniendo los codigo de Marca y Canal */
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			// Carga de los combos Marca, Canal
			this.siccMarcaList = reporteService.getMarcas();
			this.siccCanalList = reporteService.getCanalesByCodigoISO(usuario
					.getIdioma().getCodigoISO());
			this.cupTipoPedidoList = service.getTipoPedidoCUP();
			f.setTipoPedido("WE");

			Map criteria2 = new HashMap();
			String numeroNiveles = service.getNumeroNiveles(criteria2);
			f.setNumeroNiveles(numeroNiveles);

			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			/** ********************************************************* */

			f.setCodigoPrograma(service.getNextCodigoPrograma(codigoPais));
			f.setIndicadorGeneraMensaje(Constants.NUMERO_CERO);
			f.setIndicadorProgramaObligatorio(Constants.NUMERO_UNO);
			setIndicadorPedidoMixto(true);
			setIndicadorTodasUA(true);
			f.setPeriodoInicialEditable(true);
			f.setIndicadorEditable(false);
			f.setCampanhaInicial(null);
			f.setCampanhaFinal(null);
			f.setCuponInicial(null);
			f.setCuponFinal(null);
			f.setCodigoVentCupIni(null);
			f.setCodigoVentCupFin(null);
			f.setNumVigencia(null);
			f.setMontoMinimo(null);
			setIndicadorRegaloPedido(false);
			f.setZonaList(null);
			f.setIndicadorVigencia(Constants.CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA);
			f.setNivelDescuentos(null);
			f.setNivelDescuentos(null);
			f.setCampanhaInicioDescuentos(null);
			f.setCampanhaFinDescuentos(null);
			f.setMontoDescuentos(null);
			f.setMontoVentaExigidoDescuentos(null);
			f.setCodigoVentaDescuentos(null);

			f.setSelectedItemsDES(null);

			String[] valorVacio = {};
			f.setListaGrillaDescuentoCampo01(valorVacio);
			f.setListaGrillaDescuentoCampo02(valorVacio);
			f.setListaGrillaDescuentoCampo03(valorVacio);
			f.setListaGrillaDescuentoCampo04(valorVacio);
			f.setListaGrillaDescuentoCampo05(valorVacio);
			f.setListaGrillaDescuentoCampo06(valorVacio);

			this.modificaIndicadores = false;
			f.setIndActualizarUAS(false);
			f.setIndActualizarDES(false);
			f.setIndicadorCampoEditable(false);
			f.setIndicadorBorraUA(false);
			f.setIndicadorBorraDES(false);
			f.setIndRegionTodos(false);
			f.setIndTodaRegion(false);
			f.setTabSeleccion(Constants.NVS_TAB_UAS);

			// request.getSession().setAttribute(Constants.SICC_REGION_SELECCIONADA_LIST,
			// "");

			if (!f.isIndActualizarUAS()) {
				// request.getSession().setAttribute(Constants.SICC_REGION_LIST,new
				// ArrayList());
				// request.getSession().setAttribute(Constants.SICC_ZONA_LIST,new
				// ArrayList());
				this.nvsUnidadAdministrativaList = new ArrayList();

				// Obteniendo el periodo actual
				Map criteriaPeriodo = new HashMap();
				criteriaPeriodo.put("codigoPais", codigoPais);
				criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
				criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

				MantenimientoOCRPedidoControlFacturacionService service2 = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
				PedidoControlFacturacion controlFacturacion = service2
						.getControlFacturacionById(criteriaPeriodo);

				f.setPeriodoActual(controlFacturacion.getCodigoPeriodo());
				this.periodoActual = controlFacturacion.getCodigoPeriodo();
				// this.siccRegionList = ajax.getloadRegionesDisponibles(
				// codigoPais, periodoActual, f.getCampanhaInicial(),
				// f.getCampanhaFinal(), "", f.getCodigoPrograma());

			}

			if (!f.isIndActualizarDES()) {
				Map criteria = new HashMap();
				criteria.put("codigoPais", codigoPais);
				this.nvsDescuentosList = new ArrayList();
				this.cupNivelList = service.getNivelbyPais(criteria);
			}

			ListaRegioneIngresadas = new ArrayList();

			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
			criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

			MantenimientoOCRPedidoControlFacturacionService serviceCtrl = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = serviceCtrl.getControlFacturacionById(criteria);

			// Carga de PeriodoProceso y Fecha Facturacion
			f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());

			GenericoService genericoService = (GenericoService) getBean("genericoService");
			ParametroPais paramPais = new ParametroPais();

			paramPais.setCodigoPais(codigoPais);
			// paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
			paramPais.setNombreParametro("indCUVOblig");
			paramPais.setValorParametro(Constants.NUMERO_UNO);
			paramPais.setIndicadorActivo(Constants.NUMERO_UNO);

			List lstParametros = genericoService.getParametrosPais(paramPais);

			if (lstParametros != null && lstParametros.size() > 0) 
				f.setValidacionCodigoVenta(true);
			else
				f.setValidacionCodigoVenta(false);			
			
			indicadorPremioElectivo=false;
			indicadorRegaloPedido=false;
			indicadorConstanciaCupon=false;
			indicadorConstanciaPremio=false;
			indicadorCupon=false;
			indicadorConstanciaPremioElectivo=false;		
			indicadorCuponReutilizable=false;
			indicadorPremioIncentivo=false;
			indicadorPremioElectivo=false;
			indicadorPedidoMixto=false;
			indicadorPremioWeb=false;
			
			this.nvsUnidadAdministrativaList = new ArrayList();
			this.comDetalleTableModelRegion = new DataTableModel(this.nvsUnidadAdministrativaList);

		} else if (this.accion.equals(this.ACCION_MODIFICAR)) {
			if (log.isDebugEnabled()) {
				log.debug("Entering 'edit' method");
			}
			f.setNewRecord(false);
			ProgramaCupon data = (ProgramaCupon) this.beanRegistroSeleccionado;
			Map criteria = new HashMap();
			criteria.put("codigoPais", codigoPais);
			criteria.put("codigoPrograma", data.getCodigoPrograma());

			/* Reestablecemos por si se hayan intentado eliminar uas */
			// criteria.put("usuario", getUsuario(request).getLogin());
			// criteria.put("estadoEliminado", Constants.ESTADO_ACTIVO);
			// criteria.put("estadoEliminadoTemporal",
			// Constants.ESTADO_ELIMINADO_TEMPORAL);

			// service.updateUnidadAdministrativa(criteria);
			/**/

			ProgramaCupon cupon = service.getProgramaDsctoById(criteria);
			BeanUtils.copyProperties(f, cupon);
			criteria = BeanUtils.describe(f);

			f.setTipoPedido("WE");
			f.setIndicadorPedidoMixto(Constants.NUMERO_UNO);

			String codigoCuponInicial = service.getCodigoCuponInicial(criteria);
			String codigoCuponFinal = service.getCodigoCuponFinal(criteria);
			String codigoPeriodoInicial = service.getCodigoPeriodoInicial(criteria);
			String codigoPeriodoFinal = service.getCodigoPeriodoFinal(criteria);
			f.setCuponFinal(codigoCuponFinal);
			f.setCuponInicial(codigoCuponInicial);
			f.setPeriodoFinal(codigoPeriodoFinal);
			f.setPeriodoInicialEditable(StringUtils.isEmpty(codigoPeriodoInicial));
			f.setEditable(StringUtils.equals(cupon.getEstadoProg(), "S"));
			f.setTabSeleccion(Constants.NVS_TAB_UAS);

			this.indConstanciaCupon = cupon.getIndicadorConstanciaCupon();
			this.indCupon = cupon.getIndicadorCupon();
			this.indConstanciaPremio = cupon.getIndicadorConstanciaPremio();
			this.indConstanciaPremioElectivo = cupon.getIndicadorConstanciaPremioElectivo();
			this.indProgramaObligatorio = cupon.getIndicadorProgramaObligatorio();
			this.indGeneraMensaje = cupon.getIndicadorGeneraMensaje();
			this.indPremioElectivo = cupon.getIndicadorPremioElectivo();
			this.indRegaloPedido = cupon.getIndicadorRegaloPedido();
			this.indPremioIncentivo = cupon.getIndicadorPremioIncentivo();
			this.periodoFinal = cupon.getCampanhaFinal();

			/** ********************************************************* */
			/* Obteniendo los codigo de Marca y Canal */
			ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
			// Carga de los combos Marca, Canal
			this.siccMarcaList = reporteService.getMarcas();
			this.siccCanalList = reporteService.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
			this.cupTipoPedidoList = service.getTipoPedidoCUP();

			Map criteria2 = new HashMap();
			String numeroNiveles = service.getNumeroNiveles(criteria2);
			f.setNumeroNiveles(numeroNiveles);

			f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
			f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
			/** ********************************************************* */

			// Obteniendo el periodo actual
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("codigoPais", codigoPais);
			criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
			criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

			MantenimientoOCRPedidoControlFacturacionService service2 = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
			PedidoControlFacturacion controlFacturacion = service2.getControlFacturacionById(criteriaPeriodo);

			f.setPeriodoActual(controlFacturacion.getCodigoPeriodo());
			f.setPeriodoProceso(controlFacturacion.getCodigoPeriodo());

			this.periodoActual = controlFacturacion.getCodigoPeriodo();
			this.siccRegionList = null;
			this.siccRegionSeleccionadaList = "";
			this.siccZonaList = null;
			this.nvsUnidadAdministrativaList = new ArrayList();
			this.cupNivelList = new ArrayList();

			// Obteniendo la lista de UAS del Programa seleccionado
			List listUnidadesAdministrativas = cupon.getListUnidadesAdministrativas();
			this.nvsUnidadAdministrativaList = listUnidadesAdministrativas;
			this.comDetalleTableModelRegion = new DataTableModel(this.nvsUnidadAdministrativaList);
			// obtenemos la lista de DES del programa seleccionado
			
			
			List descuentoList = cupon.getListDescuentos();			
			this.nvsDescuentosList = descuentoList;
			this.comDetalleTableModelDescuento = new DataTableModel(this.nvsDescuentosList);

			f.setSelectedItemsDES(null);
			this.cupNivelList = service.getNivelbyPais(criteria);

			f.setIndicadorEditable(true);
			f.setIndicadorBorraUA(false);
			f.setIndicadorBorraDES(false);
			f.setIndRegionTodos(false);
			f.setIndTodaRegion(false);

			// Se verifica si existen consultoras asociados al programa
			criteria.put("codigoPrograma", cupon.getCodigoPrograma());
			String verificaConsultora = service.verificaConsultoraPrograma(criteria);
			if (Constants.NUMERO_CERO.equals(verificaConsultora)) {
				f.setIndicadorCampoEditable(false);	
				this.indicadorTodasUA = false;
			} else {
				f.setIndicadorCampoEditable(true);
				this.indicadorTodasUA = true;
			}

			this.modificaIndicadores = true;
			this.ListaRegioneIngresadas = new ArrayList();

			GenericoService genericoService = (GenericoService) getBean("genericoService");
			ParametroPais paramPais = new ParametroPais();

			paramPais.setCodigoPais(codigoPais);
			// paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
			paramPais.setNombreParametro("indCUVOblig");
			paramPais.setValorParametro(Constants.NUMERO_UNO);
			paramPais.setIndicadorActivo(Constants.NUMERO_UNO);

			List lstParametros = genericoService.getParametrosPais(paramPais);

			if (lstParametros != null && lstParametros.size() > 0) 
				f.setValidacionCodigoVenta(true);
			else 
				f.setValidacionCodigoVenta(false);
			
			// siccRegionList se mostrara dependiendo de su campañaFinal e
			// Inicial
			validarCheckBox2(f);			
			if (!indicadorTodasUA && !f.isIndicadorCampoEditable()) {
				this.siccRegionList = ajax.getloadRegionesDisponibles(
						codigoPais, periodoActual, f.getCampanhaInicial(),
						f.getCampanhaFinal(), "", f.getCodigoPrograma());
				this.ocultarInsertarRegion = true;
				this.ocultarInsertarZona = true;
			}
			//this.indicadorTodasUA = true;
		}
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}
		this.data  = new ProgramaCupon();
		MantenimientoCUPProgramaNuevCuponesSearchForm cuponesForm = (MantenimientoCUPProgramaNuevCuponesSearchForm) this.formBusqueda;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Map criteria = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		codigoPais = pais.getCodigo();
		criteria.put("codigoPais", codigoPais);
		this.cupProgramasList = service.getProgramasDescuentosbyPais(criteria);

		String programaActivo = service.getProgramaActivo(codigoPais);
		cuponesForm.setCodigoPrograma(programaActivo);
		this.mostrarBotonConsultar = false;
		this.salirGrabarPantallaPadre=true;
		LabelValue[] limpia = {};
		this.siccZonaList = limpia;
		this.action.setNoAsignados(new ArrayList());
		this.action.setAsignados(new ArrayList());
		this.cupNivelList = new ArrayList();
		this.indicadorPedidoMixto=false;

	}

	/**
	 * Método para elegir mediante un checkbox
	 *	si es Todas las regiones ó Personalizarlo
	 * @param val
	 */
	public void mostrarRegiones(ValueChangeEvent val) {
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		String valor = val.getNewValue().toString();
		if (valor.equals("false")) {
			this.siccRegionList = ajax.getloadRegionesDisponibles(codigoPais,
					periodoActual, f.getCampanhaInicial(),
					f.getCampanhaFinal(), "", f.getCodigoPrograma());
			f.setRegionList(null);
			this.ocultarInsertarRegion = true;
			this.ocultarInsertarZona = true;
		} else {
			// SI ES TRUE SE DEBE LIMPIAR EL COMBO REGIONES, COMBO ZONAS Y
			// LIMPIARSE LA LISTA
			setSiccRegionList(null);
			setSiccZonaList(null);
			this.nvsUnidadAdministrativaList.clear();
			this.ocultarInsertarRegion = false;
			this.ocultarInsertarZona = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.
	 * BaseMantenimientoSearchAbstractAction
	 * #setValidarConfirmar(java.lang.String)
	 */
	public String setValidarConfirmar(String accion) {
		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		if (StringUtils.isBlank(f.getCampanhaInicial()) && !indicadorTodasUA) {
			return "Debe ingresar la Campaña de Inicio";
		} else if (StringUtils.isBlank(f.getCampanhaFinal())
				&& !indicadorTodasUA) {
			return "Debe ingresar la Campaña Final";
		} else {

			if (accion.equals("INSERTAR_REGION")) {
				// ############### VALIDAR CAMPOS REQUERIDOS
				// #######################

				if (f.getRegionList() == null || f.getRegionList().length == 0)
					return this
							.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.notRegistros.seleccionado");

			} else if (accion.equals("INSERTAR_ZONA")) {
				if (f.getZonaList() == null || f.getZonaList().length == 0)
					return this
							.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.notRegistros.seleccionado");

			} else if (accion.equals("ELIMINAR_REGION")) {
				if (beanRegistroDetalleRegion == null)
					return "No hay Registros";
			} else if (accion.equals("INSERTAR_DESCUENTO")) {
				if (StringUtils.isBlank(f.getCampanhaInicial())) {
					return "Debe ingresar la Campaña de Inicio";
				} else if (StringUtils.isBlank(f.getCampanhaFinal())) {
					return "Debe ingresar la Campaña Final";
				} else if (StringUtils.isBlank(f.getNivelDescuentos())) {
					return this
							.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.nivel.blanco");
				} else if (StringUtils.isBlank(f.getCampanhaInicioDescuentos())) {
					return this
							.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campanhaInicio.blanco");
				} else if (StringUtils.isBlank(f
						.getMontoVentaExigidoDescuentos())) {
					return this
							.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.montoVenta.blanco");
				} else if (StringUtils.isBlank(f.getMontoDescuentos())) {
					return this
							.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.montoDescuento.blanco");
				}
			} else if (accion.equals("ELIMINAR_DESCUENTO")) {

			} else if (accion.equals("NIVELES")) {

			}
		}

		return null;
	}

	public void insertaRegion(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertaRegion' method");
		}

		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		List detalList = this.nvsUnidadAdministrativaList;

		String codigoPrograma = f.getCodigoPrograma();
		String periodoActual = this.periodoActual;
		String periodoInicial = f.getCampanhaInicial();
		String periodoFinal = f.getCampanhaFinal();
		ArrayList listaZona = new ArrayList();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();

		List listTemp = new ArrayList();
		// obtenemos las regiones ingresadas
		if (f.getRegionList() != null) {

			// Obtenemos las regiones seleccionadas
			String regionesSeleccionadas = getRegionesSeleccionadas(detalList);
			this.siccRegionSeleccionadaList = regionesSeleccionadas;
			//

			// Obtenemos las disponibles excluyendo las seleccionadas
			LabelValue[] lv = aSvc.getloadRegionesDisponibles(codigoPais,
					periodoActual, periodoInicial, periodoFinal,
					regionesSeleccionadas, codigoPrograma);
			//

			LabelValue[] listaNuevaRegDip = new LabelValue[] {};

			for (int i = 0; i < f.getRegionList().length; i++) {
				String region = f.getRegionList()[i];
				listaZona.add(region);

				// Si no eligio la opcion todos
				if (!"Todos".equals(region)) {
					criteria.put("oidRegion", region);
					String datoRegion = service
							.devuelveDescripcionRegion(criteria);
					String[] datosRegion = datoRegion.split("__");

					String codigoRegion = datosRegion[0];
					String descripcionRegion = datosRegion[1];

					UnidadAdministrativaCupon unidad = new UnidadAdministrativaCupon();
					unidad.setCodigoPais(codigoPais);
					unidad.setCodigoPrograma(codigoPrograma);
					unidad.setOidRegion(new Long(region));
					unidad.setCodigoRegion(codigoRegion);
					unidad.setDescripcionRegion(descripcionRegion);
					unidad.setDescripcionPeriodo(f.getCampanhaInicial());
					unidad.setOidZona(null);
					unidad.setDescripcionZona(null);
					unidad.setUsuario(usuario.getLogin());

					detalList.add(unidad);

					f.setIndRegionTodos(false);
					f.setIndTodaRegion(true);

					listaNuevaRegDip = eliminarRegionExistente(lv, region);
					lv = listaNuevaRegDip;

				}
				// Si eligio la opcion todos
				else {
					f.setIndRegionTodos(true);
					f.setIndTodaRegion(false);

					for (int j = 1; j < lv.length; j++) {
						Base base = new Base();
						base.setCodigo(lv[j].getValue());
						base.setDescripcion(lv[j].getLabel());

						UnidadAdministrativaCupon unidad = new UnidadAdministrativaCupon();
						unidad.setCodigoPais(codigoPais);
						unidad.setCodigoPrograma(codigoPrograma);
						unidad.setOidRegion(new Long(base.getCodigo()));
						unidad.setDescripcionRegion(base.getDescripcion());
						unidad.setDescripcionPeriodo(f.getCampanhaInicial());
						unidad.setOidZona(null);
						unidad.setDescripcionZona(null);
						unidad.setUsuario(usuario.getLogin());

						criteria.put("oidRegion", base.getCodigo());
						String datoRegion = service
								.devuelveDescripcionRegion(criteria);
						String[] datosRegion = datoRegion.split("__");
						String codigoRegion = datosRegion[0];

						unidad.setCodigoRegion(codigoRegion);

						if (!existeUnidadAdministrativa(detalList, unidad))
							detalList.add(unidad);
					}
					break;
				}

			}

			// Nueva Lista de Regiones Disponibles
			this.siccRegionList = listaNuevaRegDip;

			ArrayList regiones = new ArrayList();
			for (LabelValue object : listaNuevaRegDip) {
				regiones.add(object.getValue());
			}

			// Si en la lista sólo hay una opción que diga "Todos" la Lista será
			// nula
			if (regiones.size() == 1
					&& regiones.get(0).toString().contains("Todos")) {
				siccRegionList = null;
			}
			this.siccZonaList = aSvc.getZonasDisponiblesRegion(codigoPais,
					periodoActual, regiones, listaZonas,
					f.getCampanhaInicial(), f.getCampanhaFinal());
			if (listaNuevaRegDip.length == 0)
				siccZonaList = null;
		}

		this.nvsUnidadAdministrativaList = detalList;
		this.comDetalleTableModelRegion = new DataTableModel(
				this.nvsUnidadAdministrativaList);
		f.setZonaList(null);
		f.setRegionList(null);

	}

	public void insertaZona(ActionEvent event) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertaZona' method");
		}

		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");

		List detalList = this.nvsUnidadAdministrativaList;
		log.debug("formulario  :  " + f);

		String periodoActual = this.periodoActual;
		String periodoInicial = f.getCampanhaInicial();
		String periodoFinal = f.getCampanhaFinal();
		String codigoPrograma = f.getCodigoPrograma();
		ArrayList listaZona = new ArrayList();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		Map criteria = new HashMap();

		// Obtenemos las disponibles excluyendo las seleccionadas
		LabelValue[] lv = new LabelValue[] {};
		String zona = "";
		String region = "";

		List listUnidadAdministrativa = new ArrayList();
		// obtenemos las zonas ingresadas
		if (f.getZonaList() != null) {

			for (int i = 0; i < f.getZonaList().length; i++) {
				zona = f.getZonaList()[i];
				criteria.put("oidZona", zona);
				log.debug("mi zona: " + zona);
				// Si no eligio la opcion todos
				if (!"Todos".equals(zona)) {

					String datoZona = service.devuelveDescripcionZona(criteria);
					String[] datosZona = datoZona.split("__");
					Long oidRegion = new Long(datosZona[0]);
					String codigoZona = datosZona[1];
					String descripcionZona = datosZona[2];

					criteria.put("oidRegion", oidRegion);
					String datoRegion = service
							.devuelveDescripcionRegion(criteria);
					String[] datosRegion = datoRegion.split("__");

					String codigoRegion = datosRegion[0];
					String descripcionRegion = datosRegion[1];

					UnidadAdministrativaCupon unidad = new UnidadAdministrativaCupon();
					unidad.setCodigoPais(codigoPais);
					unidad.setCodigoPrograma(codigoPrograma);
					unidad.setOidRegion(oidRegion);
					unidad.setCodigoRegion(codigoRegion);
					unidad.setDescripcionRegion(descripcionRegion);
					unidad.setDescripcionPeriodo(f.getCampanhaInicial());
					unidad.setOidZona(new Long(zona));
					unidad.setCodigoZona(codigoZona);
					unidad.setDescripcionZona(descripcionZona);
					unidad.setUsuario(usuario.getLogin());

					detalList.add(unidad);
				}// Si eligio la opcion todos
				else {
					// obtenemos las regiones ingresadas
					if (f.getRegionList() != null) {
						for (int j = 0; j < f.getRegionList().length; j++) {
							region = f.getRegionList()[j];
							listaZona.add(region);

							if (!"Todos".equals(region)) {
								criteria.put("oidRegion", region);
								String datoRegion = service
										.devuelveDescripcionRegion(criteria);
								String[] datosRegion = datoRegion.split("__");

								String codigoRegion = datosRegion[0];
								String descripcionRegion = datosRegion[1];
								String zonasTemp = "";

								lv = aSvc.getZonasDisponiblesRegion(codigoPais,
										periodoActual, listaZona, zonasTemp,
										periodoInicial, periodoFinal);

								for (int k = 1; k < lv.length; k++) {
									if (!"Todos".equals(lv[k].getValue())) {
										Base base = new Base();
										base.setCodigo(lv[k].getValue());
										base.setDescripcion(lv[k].getLabel());

										UnidadAdministrativaCupon unidad = new UnidadAdministrativaCupon();
										unidad.setCodigoPais(codigoPais);
										unidad.setCodigoPrograma(codigoPrograma);
										unidad.setOidRegion(new Long(region));
										unidad.setCodigoRegion(codigoRegion);
										unidad.setDescripcionRegion(descripcionRegion);
										unidad.setDescripcionPeriodo(f
												.getCampanhaInicial());
										unidad.setOidZona(new Long(base
												.getCodigo()));
										unidad.setDescripcionZona(base
												.getDescripcion());
										unidad.setUsuario(usuario.getLogin());

										criteria.put("oidZona",
												base.getCodigo());
										String datoZona = service
												.devuelveDescripcionZona(criteria);
										String[] datosZona = datoZona
												.split("__");
										String codigoZona = datosZona[1];

										unidad.setCodigoZona(codigoZona);

										if (!existeUnidadAdministrativa(
												detalList, unidad))
											detalList.add(unidad);
									}
								}
							}

							f.setIndRegionTodos(false);
							f.setIndTodaRegion(true);

							// request.getSession()
							// .setAttribute(Constants.SICC_REGION_LIST,
							// new ArrayList());
							List listaNuevaRegDip = new ArrayList();
							String regionesSeleccionadas = getRegionesSeleccionadas(detalList);
							this.siccRegionSeleccionadaList = regionesSeleccionadas;

							lv = aSvc.getloadRegionesDisponibles(codigoPais,
									periodoActual, periodoInicial,
									periodoFinal, regionesSeleccionadas,
									codigoPrograma);

							for (int m = 0; m < lv.length; m++) {
								if (!region.equals(lv[m].getValue())) {
									listaNuevaRegDip.add(lv[m]);
								}
							}

							ListaRegioneIngresadas.add(region);
							for (int k = 0; k < ListaRegioneIngresadas.size(); k++) {
								String oidReg = (String) ListaRegioneIngresadas
										.get(k);

								for (int l = 0; l < listaNuevaRegDip.size(); l++) {
									LabelValue base = (LabelValue) listaNuevaRegDip
											.get(l);

									if (base.getValue().equals(oidReg)) {
										listaNuevaRegDip.remove(l);
										break;
									}
								}
							}

							// this.siccRegionList=listaNuevaRegDip;

						}
					}
					break;
				}
			}
		}
		/***********/
		// TODO

		criteria.put("oidRegion", listaZona);
		criteria.put("codigoPeriodo", periodoActual);
		criteria.put("periodoInicial", periodoInicial);
		criteria.put("periodoFinal", periodoFinal);
		List lista = service.getZonasDisponiblesRegion(criteria);
		log.debug("details Lista: " + detalList.size());

		List oidRegionList = new ArrayList();
		if (!"Todos".equals(zona)) {
			for (int i = 0; i < f.getRegionList().length; i++) {
				region = f.getRegionList()[i];
				criteria.put("oidRegion", region);
				String datoRegion = service.devuelveDescripcionRegion(criteria);
				String[] datosRegion = datoRegion.split("__");

				oidRegionList.add(region);
				criteria.put("oidRegion", oidRegionList);

				String codigoRegion = datosRegion[0];
				String descripcionRegion = datosRegion[1];

				for (int j = 0; j < detalList.size(); j++) {

					UnidadAdministrativaCupon unidad = new UnidadAdministrativaCupon();
					unidad = (UnidadAdministrativaCupon) detalList.get(j);
					if (unidad.getOidRegion().intValue() == Integer
							.parseInt(region)) {
						listUnidadAdministrativa.add(unidad);
					}
				}
			}

			int valor = listUnidadAdministrativa.size();
			int cantTotal = service.getCantZonasxRegion(criteria);
			if (valor == cantTotal) {

				for (int j = 0; j < f.getRegionList().length; j++) {
					region = f.getRegionList()[j];

					f.setIndRegionTodos(false);
					f.setIndTodaRegion(true);

					// request.getSession().setAttribute(
					// Constants.SICC_REGION_LIST, new ArrayList());
					List listaNuevaRegDip = new ArrayList();
					// String
					String regionesSeleccionadas = getRegionesSeleccionadas(detalList);
					this.siccRegionSeleccionadaList = regionesSeleccionadas;

					lv = aSvc.getloadRegionesDisponibles(codigoPais,
							periodoActual, periodoInicial, periodoFinal,
							regionesSeleccionadas, codigoPrograma);

					for (int m = 0; m < lv.length; m++) {
						if (!region.equals(lv[m].getValue())) {
							listaNuevaRegDip.add(lv[m]);
						}
					}

					ListaRegioneIngresadas.add(region);
					for (int k = 0; k < ListaRegioneIngresadas.size(); k++) {
						String oidReg = (String) ListaRegioneIngresadas.get(k);

						for (int l = 0; l < listaNuevaRegDip.size(); l++) {
							LabelValue base = (LabelValue) listaNuevaRegDip
									.get(l);

							if (base.getValue().equals(oidReg)) {
								listaNuevaRegDip.remove(l);
								break;
							}
						}
					}

				}

			}
		}

		// log.debug("mi lista regiones JSP: "+ listaZonas.size());
		/*********/
		ArrayList regiones = new ArrayList();
		for (String object : f.getRegionList()) {
			regiones.add(object);
		}
		listaZonas = getZonasSeleccionadas(f.getZonaList());
		this.siccZonaList = aSvc.getZonasDisponiblesRegion(codigoPais,
				periodoActual, regiones, listaZonas, f.getCampanhaInicial(),
				f.getCampanhaFinal());

		this.siccRegionSeleccionadaList = getRegionesSeleccionadasByZona(detalList);
		if (f.getZonaList().length == 1 && f.getZonaList()[0].equals("Todos")) {
			this.siccZonaList = null;
			this.siccRegionList = aSvc.getloadRegionesDisponibles(codigoPais,
					periodoActual, periodoInicial, periodoFinal,
					this.siccRegionSeleccionadaList, codigoPrograma);
		}

		if (siccRegionList.length == 2
				&& siccRegionList[0].getValue().equals("Todos"))
			siccRegionList = null;
		// if (regiones.size() == 1 && siccZonaList == null) {
		// siccRegionList = null;
		// }

		this.nvsUnidadAdministrativaList = detalList;
		this.comDetalleTableModelRegion = new DataTableModel(
				this.nvsUnidadAdministrativaList);
		f.setIndActualizarUAS(true);
		f.setTabSeleccion(Constants.NVS_TAB_UAS);
		f.setZonaList(null);
		f.setRegionList(null);

	}

	public void borraUas(ActionEvent event) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'borraUas' method");
		}
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");

		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		List detalList = this.nvsUnidadAdministrativaList;
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		criteria.put("usuario", usuario.getLogin());

		// Temporal, se reestablece si no se guarda
		criteria.put("estadoEliminado", Constants.ESTADO_ELIMINADO_TEMPORAL);

		UnidadAdministrativaCupon data = (UnidadAdministrativaCupon) this.beanRegistroDetalleRegion;

		criteria.put("oidRegion", data.getOidRegion());
		criteria.put("oidZona", data.getOidZona());
		service.updateUnidadAdministrativa(criteria);
		detalList = eliminarUas(data, detalList);

		f.setIndActualizarUAS(true);
		f.setIndicadorBorraUA(true);
		f.setIndRegionTodos(false);
		f.setIndTodaRegion(false);
		f.setTabSeleccion(Constants.NVS_TAB_UAS);

		String regionesSeleccionadas = getRegionesSeleccionadas(detalList);
		String[] zonasSeleccionadas = getZonasSeleccionadas(detalList);
		listaZonas = getZonasSeleccionadas(zonasSeleccionadas);
		this.siccRegionSeleccionadaList = regionesSeleccionadas;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		this.siccRegionList = ajax.getloadRegionesDisponibles(codigoPais,
				periodoActual, f.getCampanhaInicial(), f.getCampanhaFinal(),
				regionesSeleccionadas, f.getCodigoPrograma());

		ArrayList regiones = new ArrayList();
		// for (String object : f.getRegionList()) {
		// regiones.add(object);
		// }

		this.siccZonaList = ajax.getZonasDisponiblesRegion(codigoPais,
				periodoActual, regiones, listaZonas, f.getCampanhaInicial(),
				f.getCampanhaFinal());
		this.nvsUnidadAdministrativaList = detalList;
		this.comDetalleTableModelRegion = new DataTableModel(
				this.nvsUnidadAdministrativaList);
		f.setZonaList(null);
		f.setRegionList(null);

	}

	private List eliminarUas(UnidadAdministrativaCupon detalle, List detalList) {
		ArrayList<UnidadAdministrativaCupon> nombreArrayList = new ArrayList<UnidadAdministrativaCupon>();
		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;

		Iterator it = detalList.iterator();
		String descripcionRegion = detalle.getDescripcionRegion();

		while (it.hasNext()) {
			UnidadAdministrativaCupon data = (UnidadAdministrativaCupon) it
					.next();
			String region = data.getDescripcionRegion();

			// ELIMINA CUANDO ES SOLO REGION
			if (StringUtils.isBlank(data.getDescripcionZona())) {
				if (!descripcionRegion.equals(region))
					nombreArrayList.add(data);

			} else {
				// ELIMINA CUANDO ES REGION Y ZONA
				if (descripcionRegion.equals(region)
						&& detalle.getDescripcionZona().equals(
								data.getDescripcionZona())) {

				} else
					nombreArrayList.add(data);
			}

		}
		return nombreArrayList;
	}

	private List eliminarDes(DescuentoCupon detalle, List detalList) {
		ArrayList<DescuentoCupon> nombreArrayList = new ArrayList<DescuentoCupon>();
		Iterator it = detalList.iterator();
		if (StringUtils.isBlank(detalle.getCampanhaFin()))
			detalle.setCampanhaFin("");
		if (StringUtils.isBlank(detalle.getCodigoVenta()))
			detalle.setCodigoVenta("");
		String nivel = detalle.getNivel();
		String campanhaInicio = detalle.getCampanhaInicio();
		String campanhaFin = detalle.getCampanhaFin();
		String montoVentaExigencia = detalle.getMontoVentaExigencia();
		String montoDescuento = detalle.getMontoDescuento();
		String codigoVenta = detalle.getCodigoVenta();

		while (it.hasNext()) {
			DescuentoCupon des = (DescuentoCupon) it.next();
			String _nivel = des.getNivel();
			String _campanhaInicio = des.getCampanhaInicio();
			String _campanhaFin = des.getCampanhaFin();
			String _montoVentaExigencia = des.getMontoVentaExigencia();
			String _montoDescuento = des.getMontoDescuento();
			String _codigoVenta = des.getCodigoVenta();

			if (!nivel.equals(_nivel)
					|| !campanhaInicio.equals(_campanhaInicio)
					|| !campanhaFin.equals(_campanhaFin)
					|| !montoVentaExigencia.equals(_montoVentaExigencia)
					|| !montoDescuento.equals(_montoDescuento)
					|| !codigoVenta.equals(_codigoVenta)) {
				nombreArrayList.add(des);
			}
		}
		return nombreArrayList;
	}

	/**
	 * Obtiene el listado de regiones seleccionadas
	 * 
	 * @param detalList
	 * @return
	 */
	private String getRegionesSeleccionadas(List detalList) {
		StringBuilder regiones = new StringBuilder();

		if (detalList != null && detalList.size() > 0) {
			for (int i = 0; i < detalList.size(); i++) {
				UnidadAdministrativaCupon unidad = (UnidadAdministrativaCupon) detalList
						.get(i);

				if (unidad.getOidZona() == null)
					regiones.append(unidad.getOidRegion().toString()).append(
							"|");
			}
		}

		return regiones.toString();
	}

	private String getCodigosRegionesSeleccionadas(List detalList) {
		String regiones = "";

		if (detalList != null && detalList.size() > 0) {
			for (int i = 0; i < detalList.size(); i++) {
				UnidadAdministrativaCupon unidad = (UnidadAdministrativaCupon) detalList
						.get(i);
				regiones = regiones + unidad.getOidRegion() + ";";

			}
		}

		return regiones;
	}

	private String[] getZonasSeleccionadas(List detalList) {
		String[] zonas = new String[detalList.size()];

		if (detalList != null && detalList.size() > 0) {
			for (int i = 0; i < detalList.size(); i++) {
				UnidadAdministrativaCupon unidad = (UnidadAdministrativaCupon) detalList
						.get(i);
				if (StringUtils.isNotBlank(unidad.getDescripcionZona()))
					zonas[i] = unidad.getOidZona().toString();
			}
		}

		return zonas;
	}

	private String getRegionesSeleccionadasByZona(List detalList) {
		StringBuilder regiones = new StringBuilder();

		if (detalList != null && detalList.size() > 0) {
			for (int i = 0; i < detalList.size(); i++) {
				UnidadAdministrativaCupon unidad = (UnidadAdministrativaCupon) detalList
						.get(i);

				regiones.append(unidad.getOidRegion().toString()).append("|");
			}
		}

		return regiones.toString();
	}

	private String getZonasSeleccionadas(String[] listaZona) {
		String listZonas = "";
		for (int a = 0; a < listaZona.length; a++) {

			listZonas = listZonas + "_" + listaZona[a];

		}
		return listZonas;
	}

	private LabelValue[] eliminarRegionExistente(LabelValue[] lv, String region) {
		List ret = new ArrayList();

		if (lv != null && lv.length > 0) {
			for (int i = 0; i < lv.length; i++) {
				if (!StringUtils.equals(lv[i].getValue(), region)) {
					ret.add(lv[i]);
				}
			}
		}

		return (LabelValue[]) ret.toArray(new LabelValue[ret.size()]);
	}

	private boolean existeUnidadAdministrativa(List detalList,
			UnidadAdministrativaCupon unidad) {
		boolean ret = false;

		if (detalList != null && detalList.size() > 0) {
			for (int i = 0; i < detalList.size(); i++) {
				UnidadAdministrativaCupon ua = (UnidadAdministrativaCupon) detalList
						.get(i);

				if (ua.getOidRegion().doubleValue() == unidad.getOidRegion()
						.doubleValue() && ua.getOidZona() == null) {
					ret = true;
					break;
				} else if (ua.getOidRegion().doubleValue() == unidad
						.getOidRegion().doubleValue()
						&& ua.getOidZona().doubleValue() == unidad.getOidZona()
								.doubleValue()) {
					ret = true;
					break;
				}
			}
		}
		return ret;
	}

	public void loadCampoRegaloPedidos(ValueChangeEvent val) {
		try {
			String valor = (String) val.getNewValue();
			MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
			if (valor.equals("true")) {
				f.setMontoMinimo(null);
				setIndicadorRegaloPedido(true);
			} else {
				setIndicadorRegaloPedido(false);
				f.setMontoMinimo(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	public void loadCampoPremioWeb(ValueChangeEvent val) {
		try {
			this.indicadorPedidoMixto=false;
			Boolean valor = (Boolean) val.getNewValue();
			if (valor.equals(true)) {
				setIndicadorPremioWeb(true);
				this.indicadorPedidoMixto=true;
			} else {
				setIndicadorPremioWeb(false);
				this.indicadorPedidoMixto=false;

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
		
	}

	// Método que Carga la Lista de Regiones que depende de Campaña Inicial
	public void loadRegionesByCampaniaInicial(String valor) {
		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String campaniaInicio = valor;

		// indicadorTodasUA es un booleano si se va a ocultar ó mostrar las
		// regiones
		if (!indicadorTodasUA) {
			this.siccRegionList = ajax.getloadRegionesDisponibles(codigoPais,
					periodoActual, campaniaInicio, f.getCampanhaFinal(), "",
					f.getCodigoPrograma());
			f.setRegionList(null);
		}
		f.setCampanhaInicial(campaniaInicio);
		setSiccZonaList(null);
		this.nvsUnidadAdministrativaList.clear();
	}

	// Método que Carga la Lista de Regiones que depende de Campaña Final
	public void loadRegionesByCampaniaFin(String valor) {
		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String campaniaFinal = valor;
		// indicadorTodasUA es un booleano si se va a ocultar ó mostrar las
		// regiones
		if (!indicadorTodasUA) {
			this.siccRegionList = ajax.getloadRegionesDisponibles(codigoPais,
					periodoActual, f.getCampanhaInicial(), campaniaFinal, "",
					f.getCodigoPrograma());
			f.setRegionList(null);
		}
		f.setCampanhaFinal(campaniaFinal);
		setSiccZonaList(null);
		this.nvsUnidadAdministrativaList.clear();
	}

	public void loadZonasDisponibles(ValueChangeEvent val) 
	{
		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		AjaxService ajax = (AjaxService) getBean("ajaxService");
		String[] regiones = (String[]) val.getNewValue();
		int estado = 0;
		
		if (listaZonas == null)
			listaZonas = "";
		
		if(regiones.length > 0){
			for (int i = 0; i < regiones.length; i++) {
				String aux = regiones[i];
				if(aux.equalsIgnoreCase("Todos")){
					estado = 1;
					this.ocultarInsertarZona = false;
					break;
				}
			}
		}
		
		if(estado == 0)
		{
			this.siccZonaList = ajax.getZonasDisponiblesRegion(codigoPais, periodoActual, new ArrayList(Arrays.asList(regiones)),
					listaZonas, f.getCampanhaInicial(), f.getCampanhaFinal());
			this.ocultarInsertarZona = true;
		
		}else
			this.siccZonaList = new LabelValue[]{};
		
		ocultarInsertarRegion = true; 

		for (int i = 0; i < regiones.length; i++) {
			if (getCodigosRegionesSeleccionadas(nvsUnidadAdministrativaList)
					.contains(regiones[i])) {
				ocultarInsertarRegion = true;
			} 
		}
	}

	public void insertaDescuento(ActionEvent event) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertaDescuento' method");
		}

		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		MantenimientoCUPProgDsctoService service = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		List detalList = this.nvsDescuentosList;

		try {
			this.actualizaListaGrillaDescuento(f, detalList);

			String codigoPrograma = f.getCodigoPrograma();
			String nivel = f.getNivelDescuentos();
			String campanhaInicio = f.getCampanhaInicioDescuentos();
			String campanhaFin = f.getCampanhaFinDescuentos();
			boolean indicadorDescuentoExiste = false;
			boolean indicadorDescuentoCampanhaFinNulo = false;

			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

			if (detalList != null) {
				for (int i = 0; i < detalList.size(); i++) {
					DescuentoCupon descuento = (DescuentoCupon) detalList
							.get(i);
					if (StringUtils.equals(descuento.getNivel(), nivel)
							&& StringUtils.equals(
									descuento.getCampanhaInicio(),
									campanhaInicio)) {
						indicadorDescuentoExiste = true;
					}

					if (StringUtils.equals(descuento.getNivel(), nivel)
							&& StringUtils.isBlank(descuento.getCampanhaFin())
							&& StringUtils.isBlank(campanhaFin)) {
						indicadorDescuentoCampanhaFinNulo = true;
					}
				}
			}

			if (!indicadorDescuentoExiste) {
				if (!indicadorDescuentoCampanhaFinNulo) {
					DescuentoCupon oc = new DescuentoCupon();

					oc.setCodigoPais(f.getCodigoPais());
					oc.setCodigoPrograma(f.getCodigoPrograma());
					oc.setCodigoVenta(f.getCodigoVentaDescuentos());
					oc.setMontoDescuento(f.getMontoDescuentos());
					oc.setMontoVentaExigencia(f
							.getMontoVentaExigidoDescuentos());
					oc.setNivel(f.getNivelDescuentos());
					oc.setCampanhaInicio(f.getCampanhaInicioDescuentos());
					oc.setCampanhaFin(f.getCampanhaFinDescuentos());
					oc.setUsuario(usuario.getLogin());

					MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

					Map criteria = new HashMap();
					criteria.put("codigoPais", f.getCodigoPais());
					criteria.put("codigoPrograma", f.getCodigoPrograma());
					criteria.put("codigoPeriodo", f.getPeriodoActual());
					criteria.put("codigoVenta", f.getCodigoVentaDescuentos());
					criteria.put("codigoNivel", nivel);

					if (StringUtils.isNotEmpty(f.getCodigoVentaDescuentos())) {
						BigDecimal oidOferta = facturacionService
								.getOfertaDetalleByPeriodoCodigoVenta(criteria);
						if (oidOferta.intValue() <= 0) {
							this.addError(
									"Error",
									this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.cuv"));
						} else {
							detalList.add(oc);
						}
					} else
						detalList.add(oc);
					this.nvsDescuentosList = detalList;

				} else {
					this.addError(
							"Error",
							this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campanhaFin.nulo"));
				}
			} else {
				this.addError(
						"Error",
						this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.codigoNivel.existe"));
			}
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
			// "errors.detail", error));
			// saveErrors(request, messages);
		}
		/*********/

		f.setNivelDescuentos(f.getNivelDescuentos());
		f.setCampanhaInicioDescuentos(f.getCampanhaInicioDescuentos());
		f.setCampanhaFinDescuentos(f.getCampanhaFinDescuentos());
		f.setMontoDescuentos(f.getMontoDescuentos());
		f.setMontoVentaExigidoDescuentos(f.getMontoVentaExigidoDescuentos());
		f.setCodigoVentaDescuentos(f.getCodigoVentaDescuentos());

		this.nvsDescuentosList = detalList;
		this.comDetalleTableModelDescuento = new DataTableModel(this.nvsDescuentosList);
	}

	public void borraDes(ActionEvent event) throws Exception {

		if (log.isDebugEnabled()) {
			log.debug("Entering 'borraDes' method");
		}
		int tamanio = this.beanRegistroDetalleDescuento.length;
		MantenimientoCUPProgramaNuevCuponesForm f = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		// ParametroDescuentoForm []parametrosGrupo01 =
		// f.getParametrosGrupo01();

		List detalList = this.nvsDescuentosList;

		try {
			this.actualizaListaGrillaDescuento(f, detalList);

			List listaRetorno = new ArrayList();

			if (tamanio > 0) {
				for (int i = 0; i < tamanio; i++) {
					DescuentoCupon data = (DescuentoCupon) this.beanRegistroDetalleDescuento[i];
					detalList = eliminarDes(data, detalList);

				}
			}

			this.nvsDescuentosList = detalList;
			this.comDetalleTableModelDescuento = new DataTableModel(
					this.nvsDescuentosList);

		} catch (Exception e) {
			// String error = e.getMessage();
			// if (StringUtils.isBlank(error))
			// error = e.getLocalizedMessage();
			// messages.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(
			// "errors.detail", error));
			// saveErrors(request, messages);
		}

		/*
		 * for(int i=0; i<detalList.size(); i++) { for(int j=0; j<arrDES.length;
		 * j++) { if(StringUtils.equals(arrDES[j],String.valueOf(i))) {
		 * ((DescuentoCupon
		 * )detalList.get(i)).setEstadoRegistro(Constants.ESTADO_INACTIVO); } }
		 * }
		 * request.getSession().setAttribute(Constants.NVS_DESCUENTOS_LIST,detalList
		 * ); /* for(int i=0; i<parametrosGrupo01.length; i++) { for(int j=0;
		 * j<arrDES.length; j++) {
		 * if(StringUtils.equals(arrDES[j],String.valueOf(i))) {
		 * parametrosGrupo01[i].setEstadoRegistro(Constants.ESTADO_INACTIVO); }
		 * } } f.setParametrosGrupo01(parametrosGrupo01);
		 */

		f.setIndActualizarDES(true);
		f.setIndicadorBorraDES(true);
		f.setTabSeleccion(Constants.NVS_TAB_DES);

	}

	private void actualizaListaGrillaDescuento(
			MantenimientoCUPProgramaNuevCuponesForm f, List list)
			throws Exception {
		if (list == null)
			return;

		String[] listNivel = new String[list.size()];
		String[] listCampIni = new String[list.size()];
		String[] listCamFin = new String[list.size()];
		String[] listMontoExi = new String[list.size()];
		String[] listMontoDes = new String[list.size()];
		String[] listCodigoVen = new String[list.size()];
		for (int k = 0; k < list.size(); k++) {
			DescuentoCupon oc = (DescuentoCupon) list.get(k);
			listNivel[k] = oc.getNivel();
			listCampIni[k] = oc.getCampanhaInicio();
			listCamFin[k] = oc.getCampanhaFin();
			listMontoExi[k] = oc.getMontoVentaExigencia();
			listMontoDes[k] = oc.getMontoDescuento();
			listCodigoVen[k] = oc.getCodigoVenta();

		}
		f.setListaGrillaDescuentoCampo01(listNivel);
		f.setListaGrillaDescuentoCampo02(listCampIni);
		f.setListaGrillaDescuentoCampo03(listCamFin);
		f.setListaGrillaDescuentoCampo04(listMontoExi);
		f.setListaGrillaDescuentoCampo05(listMontoDes);
		f.setListaGrillaDescuentoCampo06(listCodigoVen);
		for (int k = 0; k < list.size(); k++) {
			boolean flagCampanhaInicio = false;
			boolean flagCampanhaFin = false;
			DescuentoCupon oc = (DescuentoCupon) list.get(k);
			String nivel = f.getListaGrillaDescuentoCampo01()[k];
			String campanhaInicio = f.getListaGrillaDescuentoCampo02()[k];
			String campanhaFin = f.getListaGrillaDescuentoCampo03()[k];
			String montoVentaExigencia = f.getListaGrillaDescuentoCampo04()[k];
			String montoDescuento = f.getListaGrillaDescuentoCampo05()[k];
			String codigoVenta = f.getListaGrillaDescuentoCampo06()[k];

			oc.setCodigoPais(codigoPais);
			oc.setCodigoPrograma(f.getCodigoPrograma());
			oc.setCodigoVenta(codigoVenta);
			oc.setMontoDescuento(montoDescuento);
			oc.setMontoVentaExigencia(montoVentaExigencia);
			oc.setNivel(nivel);
			oc.setCampanhaInicio(campanhaInicio);
			oc.setCampanhaFin(campanhaFin);

			MantenimientoOCRPedidoControlFacturacionService facturacionService = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");

			ReporteService reportService = (ReporteService) getBean("scsicc.reporteService");

			Map criteria = new HashMap();
			Map criteria1 = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("codigoPrograma", f.getCodigoPrograma());
			criteria.put("codigoPeriodo", f.getPeriodoActual());
			criteria.put("codigoVenta", codigoVenta);
			criteria.put("codigoNivel", nivel);

			BigDecimal oidOferta = new BigDecimal(0);
			if (StringUtils.isNotEmpty(codigoVenta))
				oidOferta = facturacionService
						.getOfertaDetalleByPeriodoCodigoVenta(criteria);

			String oidPeriodo = "";
			ReporteService reporteService = (ReporteService) this
					.getBean("scsicc.reporteService");

			if (StringUtils.isNotEmpty(codigoVenta)
					&& (oidOferta.intValue() <= 0)) {
				f.setSelectedItemsDES(null);
				this.addError(
						"Error",
						this.getResourceMessage("mantenimientoCUPDespachoRegaloPedidoForm.error.cuv"));
			} else {
				if (StringUtils.isBlank(campanhaInicio)
						|| StringUtils.isBlank(montoVentaExigencia)
						|| StringUtils.isBlank(montoDescuento)) {
					f.setSelectedItemsDES(null);
					this.addError(
							"Error",
							this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campos.blanco"));
				} else {
					if (Double.parseDouble(montoVentaExigencia) <= Double
							.parseDouble(Constants.NUMERO_CERO)
							|| Double.parseDouble(montoDescuento) <= Double
									.parseDouble(Constants.NUMERO_CERO)) {
						f.setSelectedItemsDES(null);
						this.addError(
								"Error",
								this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campos.cero"));
					} else {
						try {
							criteria1.put("codigoPeriodo", campanhaInicio);
							oidPeriodo = reporteService.getOidString(
									"getOidPeriodoByCodigoPeriodo", criteria);
						} catch (Exception ex) {
							flagCampanhaInicio = true;
						}
						try {
							criteria1.put("codigoPeriodo", campanhaFin);
							oidPeriodo = reporteService.getOidString(
									"getOidPeriodoByCodigoPeriodo", criteria);
						} catch (Exception ex) {
							flagCampanhaFin = true;
						}
						if (flagCampanhaInicio) {
							f.setSelectedItemsDES(null);
							this.addError(
									"Error",
									this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campanhaInicioMenor"));
						}

						if (flagCampanhaFin) {
							f.setSelectedItemsDES(null);
							this.addError(
									"Error",
									this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campanhaInicioMenor"));
						}
						if (StringUtils.isNotBlank(campanhaFin)) {
							if (Integer.parseInt(campanhaFin) < Integer
									.parseInt(campanhaInicio)) {
								f.setSelectedItemsDES(null);
								this.addError(
										"Error",
										this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campanhaInicioMenor"));
							}
						}
					}
				}
			}

			for (int i = k + 1; i < list.size(); i++) {
				DescuentoCupon descuento = (DescuentoCupon) list.get(i);
				if (StringUtils.equals(descuento.getNivel(), nivel)
						&& StringUtils.equals(descuento.getCampanhaInicio(),
								campanhaInicio)) {
					f.setSelectedItemsDES(null);
					addError(
							"Error",
							this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.codigoNivel.existe"));
				}

				if (StringUtils.equals(descuento.getNivel(), nivel)
						&& StringUtils.isBlank(descuento.getCampanhaFin())
						&& StringUtils.isBlank(campanhaFin)) {
					f.setSelectedItemsDES(null);
					throw new Exception(
							this.getResourceMessage("mantenimientoCUPProgramaNuevCuponesForm.error.campanhaFin.nulo"));
				}
			}
		}
	}

	public void redireccionarPagina(ActionEvent event) throws Exception {
		MantenimientoCUPProgramaNuevCuponesNivelForm f = (MantenimientoCUPProgramaNuevCuponesNivelForm) this.action.getFormMantenimiento();
		this.data  = new ProgramaCupon();
		if (this.beanRegistroSeleccionado == null)
			this.addError("Error", "Seleccione un elemento de la lista");
		else {
			this.data = (ProgramaCupon) this.beanRegistroSeleccionado;
			BeanUtils.copyProperties(f, data);
			this.action.setFormMantenimiento(f);
			this.action.setData(data);
			this.action.inicializando();
			this.redireccionarPagina("mantenimientoCUPProgramaNuevCuponesNivelForm");
		}

	}

	public void salirPagina(ActionEvent event) throws IOException {
		this.redireccionarPagina("mantenimientoCUPProgramaNuevCuponesList");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoAbstractAction
	 * #devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoCUPProgramaNuevCuponesForm form = (MantenimientoCUPProgramaNuevCuponesForm) this.formMantenimiento;
		boolean isNew = form.isNewRecord();
		if (isNew) {
			return "mantenimientoCUPProgramaNuevCuponesForm.add.success";
		} else {
			return "mantenimientoCUPProgramaNuevCuponesForm.updated";
		}
	}

	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
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
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the cupTipoPedidoList
	 */
	public List getCupTipoPedidoList() {
		return cupTipoPedidoList;
	}

	/**
	 * @param cupTipoPedidoList
	 *            the cupTipoPedidoList to set
	 */
	public void setCupTipoPedidoList(List cupTipoPedidoList) {
		this.cupTipoPedidoList = cupTipoPedidoList;
	}

	/**
	 * @return the indConstanciaCupon
	 */
	public String getIndConstanciaCupon() {
		return indConstanciaCupon;
	}

	/**
	 * @param indConstanciaCupon
	 *            the indConstanciaCupon to set
	 */
	public void setIndConstanciaCupon(String indConstanciaCupon) {
		this.indConstanciaCupon = indConstanciaCupon;
	}

	/**
	 * @return the indCupon
	 */
	public String getIndCupon() {
		return indCupon;
	}

	/**
	 * @param indCupon
	 *            the indCupon to set
	 */
	public void setIndCupon(String indCupon) {
		this.indCupon = indCupon;
	}

	/**
	 * @return the indConstanciaPremio
	 */
	public String getIndConstanciaPremio() {
		return indConstanciaPremio;
	}

	/**
	 * @param indConstanciaPremio
	 *            the indConstanciaPremio to set
	 */
	public void setIndConstanciaPremio(String indConstanciaPremio) {
		this.indConstanciaPremio = indConstanciaPremio;
	}

	/**
	 * @return the indConstanciaPremioElectivo
	 */
	public String getIndConstanciaPremioElectivo() {
		return indConstanciaPremioElectivo;
	}

	/**
	 * @param indConstanciaPremioElectivo
	 *            the indConstanciaPremioElectivo to set
	 */
	public void setIndConstanciaPremioElectivo(
			String indConstanciaPremioElectivo) {
		this.indConstanciaPremioElectivo = indConstanciaPremioElectivo;
	}

	/**
	 * @return the indProgramaObligatorio
	 */
	public String getIndProgramaObligatorio() {
		return indProgramaObligatorio;
	}

	/**
	 * @param indProgramaObligatorio
	 *            the indProgramaObligatorio to set
	 */
	public void setIndProgramaObligatorio(String indProgramaObligatorio) {
		this.indProgramaObligatorio = indProgramaObligatorio;
	}

	/**
	 * @return the indGeneraMensaje
	 */
	public String getIndGeneraMensaje() {
		return indGeneraMensaje;
	}

	/**
	 * @param indGeneraMensaje
	 *            the indGeneraMensaje to set
	 */
	public void setIndGeneraMensaje(String indGeneraMensaje) {
		this.indGeneraMensaje = indGeneraMensaje;
	}

	/**
	 * @return the indPremioElectivo
	 */
	public String getIndPremioElectivo() {
		return indPremioElectivo;
	}

	/**
	 * @param indPremioElectivo
	 *            the indPremioElectivo to set
	 */
	public void setIndPremioElectivo(String indPremioElectivo) {
		this.indPremioElectivo = indPremioElectivo;
	}

	/**
	 * @return the indRegaloPedido
	 */
	public String getIndRegaloPedido() {
		return indRegaloPedido;
	}

	/**
	 * @param indRegaloPedido
	 *            the indRegaloPedido to set
	 */
	public void setIndRegaloPedido(String indRegaloPedido) {
		this.indRegaloPedido = indRegaloPedido;
	}

	/**
	 * @return the indPremioIncentivo
	 */
	public String getIndPremioIncentivo() {
		return indPremioIncentivo;
	}

	/**
	 * @param indPremioIncentivo
	 *            the indPremioIncentivo to set
	 */
	public void setIndPremioIncentivo(String indPremioIncentivo) {
		this.indPremioIncentivo = indPremioIncentivo;
	}

	/**
	 * @return the modificaIndicadores
	 */
	public Boolean getModificaIndicadores() {
		return modificaIndicadores;
	}

	/**
	 * @param modificaIndicadores
	 *            the modificaIndicadores to set
	 */
	public void setModificaIndicadores(Boolean modificaIndicadores) {
		this.modificaIndicadores = modificaIndicadores;
	}

	/**
	 * @return the periodoActual
	 */
	public String getPeriodoActual() {
		return periodoActual;
	}

	/**
	 * @param periodoActual
	 *            the periodoActual to set
	 */
	public void setPeriodoActual(String periodoActual) {
		this.periodoActual = periodoActual;
	}

	/**
	 * @return the periodoFinal
	 */
	public String getPeriodoFinal() {
		return periodoFinal;
	}

	/**
	 * @param periodoFinal
	 *            the periodoFinal to set
	 */
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	/**
	 * @return the listaRegioneIngresadas
	 */
	public List getListaRegioneIngresadas() {
		return ListaRegioneIngresadas;
	}

	/**
	 * @param listaRegioneIngresadas
	 *            the listaRegioneIngresadas to set
	 */
	public void setListaRegioneIngresadas(List listaRegioneIngresadas) {
		ListaRegioneIngresadas = listaRegioneIngresadas;
	}

	/**
	 * @return the indicadorRegaloPedido
	 */
	public boolean isIndicadorRegaloPedido() {
		return indicadorRegaloPedido;
	}

	/**
	 * @param indicadorRegaloPedido
	 *            the indicadorRegaloPedido to set
	 */
	public void setIndicadorRegaloPedido(boolean indicadorRegaloPedido) {
		this.indicadorRegaloPedido = indicadorRegaloPedido;
	}

	/**
	 * @return the indicadorPremioElectivo
	 */
	public boolean isIndicadorPremioElectivo() {
		return indicadorPremioElectivo;
	}

	/**
	 * @param indicadorPremioElectivo
	 *            the indicadorPremioElectivo to set
	 */
	public void setIndicadorPremioElectivo(boolean indicadorPremioElectivo) {
		this.indicadorPremioElectivo = indicadorPremioElectivo;
	}

	/**
	 * @return the indicadorConstanciaCupon
	 */
	public boolean isIndicadorConstanciaCupon() {
		return indicadorConstanciaCupon;
	}

	/**
	 * @param indicadorConstanciaCupon
	 *            the indicadorConstanciaCupon to set
	 */
	public void setIndicadorConstanciaCupon(boolean indicadorConstanciaCupon) {
		this.indicadorConstanciaCupon = indicadorConstanciaCupon;
	}

	/**
	 * @return the indicadorConstanciaPremio
	 */
	public boolean isIndicadorConstanciaPremio() {
		return indicadorConstanciaPremio;
	}

	/**
	 * @param indicadorConstanciaPremio
	 *            the indicadorConstanciaPremio to set
	 */
	public void setIndicadorConstanciaPremio(boolean indicadorConstanciaPremio) {
		this.indicadorConstanciaPremio = indicadorConstanciaPremio;
	}

	/**
	 * @return the indicadorCupon
	 */
	public boolean isIndicadorCupon() {
		return indicadorCupon;
	}

	/**
	 * @param indicadorCupon
	 *            the indicadorCupon to set
	 */
	public void setIndicadorCupon(boolean indicadorCupon) {
		this.indicadorCupon = indicadorCupon;
	}

	/**
	 * @return the indicadorConstanciaPremioElectivo
	 */
	public boolean isIndicadorConstanciaPremioElectivo() {
		return indicadorConstanciaPremioElectivo;
	}

	/**
	 * @param indicadorConstanciaPremioElectivo
	 *            the indicadorConstanciaPremioElectivo to set
	 */
	public void setIndicadorConstanciaPremioElectivo(
			boolean indicadorConstanciaPremioElectivo) {
		this.indicadorConstanciaPremioElectivo = indicadorConstanciaPremioElectivo;
	}

	/**
	 * @return the indicadorPremioIncentivo
	 */
	public boolean isIndicadorPremioIncentivo() {
		return indicadorPremioIncentivo;
	}

	/**
	 * @param indicadorPremioIncentivo
	 *            the indicadorPremioIncentivo to set
	 */
	public void setIndicadorPremioIncentivo(boolean indicadorPremioIncentivo) {
		this.indicadorPremioIncentivo = indicadorPremioIncentivo;
	}

	/**
	 * @return the indicadorPremioWeb
	 */
	public boolean isIndicadorPremioWeb() {
		return indicadorPremioWeb;
	}

	/**
	 * @param indicadorPremioWeb
	 *            the indicadorPremioWeb to set
	 */
	public void setIndicadorPremioWeb(boolean indicadorPremioWeb) {
		this.indicadorPremioWeb = indicadorPremioWeb;
	}

	/**
	 * @return the cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA
	 */
	public String getCUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA() {
		return CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA;
	}

	/**
	 * @param cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA
	 *            the cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA to set
	 */
	public void setCUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA(
			String cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA) {
		CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA = cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_PROGRAMA;
	}

	/**
	 * @return the cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES
	 */
	public String getCUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES() {
		return CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES;
	}

	/**
	 * @param cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES
	 *            the cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES to set
	 */
	public void setCUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES(
			String cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES) {
		CUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES = cUP_PROGRAMA_NUEVAS_VIGENCIA_POR_NIVELES;
	}

	/**
	 * @return the indicadorPedidoMixto
	 */
	public boolean isIndicadorPedidoMixto() {
		return indicadorPedidoMixto;
	}

	/**
	 * @param indicadorPedidoMixto
	 *            the indicadorPedidoMixto to set
	 */
	public void setIndicadorPedidoMixto(boolean indicadorPedidoMixto) {
		this.indicadorPedidoMixto = indicadorPedidoMixto;
	}

	/**
	 * @return the indicadorTodasUA
	 */
	public boolean isIndicadorTodasUA() {
		return indicadorTodasUA;
	}

	/**
	 * @param indicadorTodasUA
	 *            the indicadorTodasUA to set
	 */
	public void setIndicadorTodasUA(boolean indicadorTodasUA) {
		this.indicadorTodasUA = indicadorTodasUA;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the nvsUnidadAdministrativaList
	 */
	public List getNvsUnidadAdministrativaList() {
		return nvsUnidadAdministrativaList;
	}

	/**
	 * @param nvsUnidadAdministrativaList
	 *            the nvsUnidadAdministrativaList to set
	 */
	public void setNvsUnidadAdministrativaList(List nvsUnidadAdministrativaList) {
		this.nvsUnidadAdministrativaList = nvsUnidadAdministrativaList;
	}

	/**
	 * @return the siccRegionSeleccionadaList
	 */
	public String getSiccRegionSeleccionadaList() {
		return siccRegionSeleccionadaList;
	}

	/**
	 * @param siccRegionSeleccionadaList
	 *            the siccRegionSeleccionadaList to set
	 */
	public void setSiccRegionSeleccionadaList(String siccRegionSeleccionadaList) {
		this.siccRegionSeleccionadaList = siccRegionSeleccionadaList;
	}

	/**
	 * @return the comDetalleTableModelRegion
	 */
	public DataTableModel getComDetalleTableModelRegion() {
		return comDetalleTableModelRegion;
	}

	/**
	 * @param comDetalleTableModelRegion
	 *            the comDetalleTableModelRegion to set
	 */
	public void setComDetalleTableModelRegion(
			DataTableModel comDetalleTableModelRegion) {
		this.comDetalleTableModelRegion = comDetalleTableModelRegion;
	}

	/**
	 * @return the beanRegistroDetalleRegion
	 */
	public Object getBeanRegistroDetalleRegion() {
		return beanRegistroDetalleRegion;
	}

	/**
	 * @param beanRegistroDetalleRegion
	 *            the beanRegistroDetalleRegion to set
	 */
	public void setBeanRegistroDetalleRegion(Object beanRegistroDetalleRegion) {
		this.beanRegistroDetalleRegion = beanRegistroDetalleRegion;
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
	 * @return the comDetalleTableModelDescuento
	 */
	public DataTableModel getComDetalleTableModelDescuento() {
		return comDetalleTableModelDescuento;
	}

	/**
	 * @param comDetalleTableModelDescuento
	 *            the comDetalleTableModelDescuento to set
	 */
	public void setComDetalleTableModelDescuento(
			DataTableModel comDetalleTableModelDescuento) {
		this.comDetalleTableModelDescuento = comDetalleTableModelDescuento;
	}

	/**
	 * @return the beanRegistroDetalleDescuento
	 */
	public Object[] getBeanRegistroDetalleDescuento() {
		return beanRegistroDetalleDescuento;
	}

	/**
	 * @param beanRegistroDetalleDescuento
	 *            the beanRegistroDetalleDescuento to set
	 */
	public void setBeanRegistroDetalleDescuento(
			Object[] beanRegistroDetalleDescuento) {
		this.beanRegistroDetalleDescuento = beanRegistroDetalleDescuento;
	}

	/**
	 * @return the nvsDescuentosList
	 */
	public List getNvsDescuentosList() {
		return nvsDescuentosList;
	}

	/**
	 * @param nvsDescuentosList
	 *            the nvsDescuentosList to set
	 */
	public void setNvsDescuentosList(List nvsDescuentosList) {
		this.nvsDescuentosList = nvsDescuentosList;
	}

	/**
	 * @return the habilitar
	 */
	public boolean isHabilitar() {
		return habilitar;
	}

	/**
	 * @param habilitar
	 *            the habilitar to set
	 */
	public void setHabilitar(boolean habilitar) {
		this.habilitar = habilitar;
	}

	/**
	 * @return the listaZonas
	 */
	public String getListaZonas() {
		return listaZonas;
	}

	/**
	 * @param listaZonas
	 *            the listaZonas to set
	 */
	public void setListaZonas(String listaZonas) {
		this.listaZonas = listaZonas;
	}

	/**
	 * @return the ocultarInsertarRegion
	 */
	public boolean isOcultarInsertarRegion() {
		return ocultarInsertarRegion;
	}

	/**
	 * @param ocultarInsertarRegion
	 *            the ocultarInsertarRegion to set
	 */
	public void setOcultarInsertarRegion(boolean ocultarInsertarRegion) {
		this.ocultarInsertarRegion = ocultarInsertarRegion;
	}

	/**
	 * @return the action
	 */
	public MantenimientoCUPProgramaNuevCuponesNivelAction getAction() {
		return action;
	}

	/**
	 * @param action
	 *            the action to set
	 */
	public void setAction(MantenimientoCUPProgramaNuevCuponesNivelAction action) {
		this.action = action;
	}

	/**
	 * @return the data
	 */
	public ProgramaCupon getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(ProgramaCupon data) {
		this.data = data;
	}

	public boolean isIndicadorCuponReutilizable() {
		return indicadorCuponReutilizable;
	}

	public void setIndicadorCuponReutilizable(boolean indicadorCuponReutilizable) {
		this.indicadorCuponReutilizable = indicadorCuponReutilizable;
	}

	public boolean isOcultarInsertarZona() {
		return ocultarInsertarZona;
	}

	public void setOcultarInsertarZona(boolean ocultarInsertarZona) {
		this.ocultarInsertarZona = ocultarInsertarZona;
	}
	
	/**
	 * @return the cuponesProgDescuentosList
	 */
	public List getCuponesProgDescuentosList() {
		return cuponesProgDescuentosList;
	}

	/**
	 * @param cuponesProgDescuentosList
	 *            the cuponesProgDescuentosList to set
	 */
	public void setCuponesProgDescuentosList(List cuponesProgDescuentosList) {
		this.cuponesProgDescuentosList = cuponesProgDescuentosList;
	}
	
	

}
