package biz.belcorp.ssicc.web.spusicc.action;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.axis.types.Year;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.hxtt.concurrent.ar;
import com.sun.xml.bind.marshaller.NioEscapeHandler;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteConcursoPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteMarca;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePrimerContacto;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteReferencias;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteVinculo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Concurso;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Premio;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.Estimado;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.MantenimientoDATEstimadosService;
import biz.belcorp.ssicc.service.spusicc.comision.MantenimientoCOMPorcentajeComisionService;
import biz.belcorp.ssicc.service.spusicc.comision.ProcesoCOMCalculoCalificacionTramoService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorp;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpservice;
import biz.belcorp.ssicc.service.spusicc.ocr.ws.axis.IConsultaBelcorpserviceLocator;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.hip.action.BusquedaConsultorasAction;
import biz.belcorp.ssicc.web.spusicc.comision.form.MantenimientoCOMCalificacionComisionForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoDATEstimadosSearchForm;
import biz.belcorp.ssicc.web.spusicc.form.MantenimientoMAEClienteForm;
import biz.belcorp.ssicc.web.spusicc.mae.action.BusquedaMAEPremiosRecomendacionAction;
import biz.belcorp.ssicc.web.spusicc.mae.form.MantenimientoMAEModificacionClienteForm;
import biz.belcorp.ssicc.web.spusicc.mav.form.ProcesoMAVAsignacionReemplazoGerenteRegionForm;
import biz.belcorp.ssicc.web.spusicc.sto.action.MantenimientoSTOSolicitudPostVentaCabeceraAction;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOControlFnePorcentajeSearchForm;

/**
 * @author Sigcomt
 * 
 */
@SessionScoped
@ManagedBean
public class MantenimientoMAERedefinirPeriodoAction extends
		BaseMantenimientoSearchAbstractAction {
	// FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	
	private String pathPremios = "";
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

	}

	public void inicializar() {

	}

	public void redefinirPeriodo() {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'redefinirPeriodo' method");
		}

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoMAEModificacionClienteForm f = (MantenimientoMAEModificacionClienteForm) formMantenimiento;

		Usuario usuario = mPantallaPrincipalBean.getCurrentUser();

		// datos del cliente IMPORTENTE
		Cliente cliente = new Cliente();// (Cliente)session.getAttribute(Constants.MAE_DTO_CLIENTE);

		Map criteriaConcurso = new HashMap();
		List listClienteConcursoPremio = new ArrayList();
		boolean mostrarPantallaPremios = false;

		try {
			criteriaConcurso.put("oidPais", f.getOidPais());
			criteriaConcurso.put("oidPeriodo", f.getOidPeriodoRedefinir());

			// grabamos los concursos y premios para el cliente nuevo
			if (f.getCodigoConsultoraRecomendante() != null
					&& !("".equals(f.getCodigoConsultoraRecomendante()))) {
				criteriaConcurso.put("oidDirigConsul",
						Constants.MAE_OID_DIRIG_CONSUL);
				criteriaConcurso.put("oidBaseCalcu",
						Constants.MAE_OID_BASE_CALCU_RECOMEN);

				String codigoZona = clienteService.getZonaCliente(
						f.getCodigoPais(), f.getCodigoConsultoraRecomendante());
				criteriaConcurso.put("codigoZona", codigoZona);
				criteriaConcurso.put("codigoCliente",
						f.getCodigoConsultoraRecomendante());
				//

				// session.setAttribute("codigoZonaConcurso", codigoZona);

				List listConcursos = clienteService
						.getConcursos(criteriaConcurso);
				Iterator itConcursos = listConcursos.iterator();

				while (itConcursos.hasNext()) {
					Concurso concurso = (Concurso) itConcursos.next();

					// si no pertenece a la zona geografica del concurso, no se
					// le asigna dicho concurso a la consultora
					if (concurso.getTotalGeografico().intValue() > 0
							&& concurso.getCodigoZona() == null)
						continue;

					criteriaConcurso.put("oidConcurso",
							concurso.getOidConcurso());
					List listPremios = clienteService
							.getPremios(criteriaConcurso);

					// Si el Concurso de Rec. No tiene Niveles Electivos, no se
					// muestra,
					// Anterior: if(concurso.getNumeroNivel().intValue() > 1)
					if (concurso.getNumeroNivelSelectivo().intValue() == 0) {
						ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
						concursoPremio.setOidModulo(new Long(
								Constants.MAE_CLIENTE_MODULO_MAE));

						concursoPremio.setCodigoClienteRecomendante(f
								.getCodigoConsultoraRecomendante());
						concursoPremio
								.setOidConcurso(concurso.getOidConcurso());

						concursoPremio.setCodigoClienteRecomendado(cliente
								.getCodigo());
						concursoPremio.setOidPeriodo(new Long(f
								.getOidPeriodoRedefinir()));

						listClienteConcursoPremio.add(concursoPremio);
					} else {

						// si el premio es de un nivel se graba
						if (listPremios.size() > 1)
							mostrarPantallaPremios = true;

						ClienteConcursoPremio concursoPremio = new ClienteConcursoPremio();
						concursoPremio.setOidModulo(new Long(
								Constants.MAE_CLIENTE_MODULO_MAE));

						concursoPremio.setCodigoClienteRecomendante(f
								.getCodigoConsultoraRecomendante());
						concursoPremio
								.setOidConcurso(concurso.getOidConcurso());

						concursoPremio.setCodigoClienteRecomendado(cliente
								.getCodigo());
						concursoPremio.setOidPeriodo(new Long(f
								.getOidPeriodoRedefinir()));

						// Seteamos a null si el indicador esta en uno
						if (StringUtils.equals(concurso.getTipoPremio(),
								Constants.ESTADO_ACTIVO)) {
							concursoPremio.setOidNivelPremio(null);
							concursoPremio.setNumeroPremio(null);
						} else {
							Premio premio = (Premio) listPremios.get(0);
							concursoPremio.setOidNivelPremio(premio
									.getOidPremio());
							concursoPremio.setNumeroPremio(premio
									.getNumeroPremio());
						}

						listClienteConcursoPremio.add(concursoPremio);
					}
				}

				// OCULTAR LA PANTALLA DE PREMIOS
				if (listConcursos != null && listConcursos.size() > 0) {
					// verificamos si el indicador TPRM_OID_TIPO_PION tipopremio
					// esta en 1
					// TODOS los registros de la lista van a tenr el mismo valor
					Concurso concurso = (Concurso) listConcursos.get(0);

					// No se muestra la pantalla de premios
					if (StringUtils.equals(concurso.getTipoPremio(),
							Constants.ESTADO_ACTIVO)) {
						f.setMostrarPantallaPremios(false);
						mostrarPantallaPremios = false;
					}
				}
				//

			}

			criteriaConcurso.put("oidCliente", cliente.getOid().toString());
			criteriaConcurso.put("listClienteConcursoPremio",
					listClienteConcursoPremio);
			criteriaConcurso.put("codigoUsuario", usuario.getLogin());

			if (f.getCodigoEstatus().equals(Constants.MAE_ESTADO_REGISTRADA))
				clienteService.updateClientePeriodo(criteriaConcurso);
			else if (f.getCodigoEstatus().equals(Constants.MAE_ESTADO_RETIRADA))
				clienteService.updateClientePeriodoRetiradas(criteriaConcurso);

			if (!mostrarPantallaPremios) {
				/*
				 * messages.add(ActionMessages.GLOBAL_MESSAGE, new
				 * ActionMessage(
				 * "mantenimientoMAEModificacionClienteForm.updated",
				 * cliente.getCodigo()));
				 */
				addInfo("Mensaje",
						this.getResourceMessage("mantenimientoMAEModificacionClienteForm.updated"));

			}

		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error))
				error = e.getLocalizedMessage();
			addInfo(this.getResourceMessage("errors.detail"),
					this.getResourceMessage("mantenimientoMAEModificacionClienteForm.updated"));

			// return mapping.findForward("redefinirPeriodo");
		}

		if (mostrarPantallaPremios) {
			this.pathPremios = f.getOidPeriodoRedefinir() + "|"
					+ f.getOidPais() + "|" + f.getCodigoCliente() + "|"
					+ f.getCodigoConsultoraRecomendante();
			// session.setAttribute("idPremiosMAEModificacion",
			// this.pathPremios);

			// return mapping.findForward("premios");
		}
		// else
		// return mapping.findForward(getViewForward());
	}

}
