package biz.belcorp.ssicc.web.spusicc.let.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.primefaces.component.tabview.TabView;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;

import biz.belcorp.ssicc.dao.spusicc.let.model.Premios;
import biz.belcorp.ssicc.dao.spusicc.let.model.ProgramaCorporativo;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoNivel;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoRetencion;
import biz.belcorp.ssicc.dao.spusicc.let.model.Tramos;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETProgramaCorporativoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.let.form.MantenimientoLETProgramaCorporativoForm;
import biz.belcorp.ssicc.web.spusicc.let.form.MantenimientoLETProgramaCorporativoPremiosForm;
import biz.belcorp.ssicc.web.spusicc.let.form.MantenimientoLETProgramaCorporativoRangoNivelForm;
import biz.belcorp.ssicc.web.spusicc.let.form.MantenimientoLETProgramaCorporativoRangoRetencionForm;
import biz.belcorp.ssicc.web.spusicc.let.form.MantenimientoLETProgramaCorporativoSearchForm;
import biz.belcorp.ssicc.web.spusicc.let.form.MantenimientoLETProgramaCorporativoTramosForm;

@ManagedBean
@SessionScoped
public class MantenimientoLETProgramaCorporativoAction extends BaseMantenimientoSearchAbstractAction{

	
	private static final long serialVersionUID = -5060954078773173257L;
	
	private List letTipoNivelExitoList;
	private List letProgramaCorporativoRangoNivelList;
	private List letProgramaCorporativoRangoRetencionList;
	private List letProgramaCorporativoTramosList;
	private List letProgramaCorporativoPremiosList;
	private List busquedaLetProgramaCorporativoPremiosList;
	
	private DataTableModel letProgramaCorporativoRangoNivelDataModel;
	private Object beanletProgramaCorporativoRangoNivel;	
	private DataTableModel letProgramaCorporativoRangoRetencionDataModel;
	private Object beanletProgramaCorporativoRangoRetencion;
	private DataTableModel letProgramaCorporativoTramosDataModel;
	private Object beanletProgramaCorporativoTramos;			
	private DataTableModel letProgramaCorporativoPremiosDataModel;
	private Object beanletProgramaCorporativoPremios;
	
	private String indicadorActivoRangoNivel;
	private String indicadorActivoRangoRetencion;
	private String indicadorActivoTramos;
	private String indicadorActivoPremios;
	private String indexRangoNivel;
	private String estadoInactivo=Constants.ESTADO_INACTIVO;
	private String indicadorHabilitaTramos;
	private boolean consultar;
	private boolean indExigenciaPedidoWeb;
	private boolean indGananciaLiderNueva;
	private boolean indicadorPremio;
	private boolean indRetencion22;
	private boolean indRetencion33;
	private boolean indRetencion44;
	private boolean mostrarCampana;
	
	private MantenimientoLETProgramaCorporativoRangoNivelForm rangoNivelForm;
	private MantenimientoLETProgramaCorporativoRangoRetencionForm rangoRetencionForm;
	private MantenimientoLETProgramaCorporativoTramosForm tramosForm;
	private MantenimientoLETProgramaCorporativoPremiosForm premiosForm;
	
	private TabView tabShow;
	

	@Override
	protected String getSalirForward() {		
		return "mantenimientoLETProgramaCorporativoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoLETProgramaCorporativoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoLETProgramaCorporativoSearchForm searchForm = new MantenimientoLETProgramaCorporativoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {		
		MantenimientoLETProgramaCorporativoSearchForm f = (MantenimientoLETProgramaCorporativoSearchForm)this.formBusqueda;	
		MantenimientoLETProgramaCorporativoService service = (MantenimientoLETProgramaCorporativoService) getBean("spusicc.mantenimientoLETProgramaCorporativoService");
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", f.getCodigoPrograma());
		criteria.put("descripcionPrograma", f.getDescripcionPrograma());
		
		List programaCorporativoList = service.getProgramaCorporativoList(criteria);		
		return programaCorporativoList;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		String id = sistemabusqueda.get("codigoPrograma").toString();
		
		
		if(id != null){
			try {
				MantenimientoLETProgramaCorporativoService service = (MantenimientoLETProgramaCorporativoService) getBean("spusicc.mantenimientoLETProgramaCorporativoService");
				
				Map criteria = new HashMap();
				criteria.put("codigoPais", pais.getCodigo());
				criteria.put("codigoPrograma",id);
				criteria.put("usuario", usuario.getLogin());
				
				int indExisteObjPedido = Integer.parseInt(service.getExisteObjetivosPedidosPorPrograma(criteria));
				
				if(indExisteObjPedido == 0){
					service.deleteProgramaCorporativo(criteria);										
				}else					
					throw new Exception(this.getResourceMessage("mantenimientoLETProgramaCorporativoForm.existe.objetivoPedido"));				
			} catch (Exception e) {
				String error = e.getMessage();				
				if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
					this.addError("Error:", error);
				
			}
			return true;
			
		}
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoLETProgramaCorporativoForm f = (MantenimientoLETProgramaCorporativoForm)this.formMantenimiento;
		MantenimientoLETProgramaCorporativoService service = (MantenimientoLETProgramaCorporativoService) getBean("spusicc.mantenimientoLETProgramaCorporativoService");
		
		String indicadorActivoRangoNivel = this.indicadorActivoRangoNivel;
		String indicadorActivoRangoRetencion =this.indicadorActivoRangoRetencion;
		String indicadorActivoTramos = this.indicadorActivoTramos;
		String indicadorActivoPremios = this.indicadorActivoPremios;
		
		f.setIndicadorActivoRangoNivel(indicadorActivoRangoNivel);
		f.setIndicadorActivoRangoRetencion(indicadorActivoRangoRetencion);
		f.setIndicadorActivoTramos(indicadorActivoTramos);
		f.setIndicadorActivoPremios(indicadorActivoPremios);
		if(this.indExigenciaPedidoWeb)
			f.setIndExigenciaPedidoWeb(Constants.NUMERO_UNO);
		else
			f.setIndExigenciaPedidoWeb(Constants.NUMERO_CERO);
		if(this.indGananciaLiderNueva)
			f.setIndGananciaLiderNueva(Constants.NUMERO_UNO);
		else
			f.setIndGananciaLiderNueva(Constants.NUMERO_CERO);
				
		ProgramaCorporativo programaCorporativo = new ProgramaCorporativo();
		BeanUtils.copyProperties(programaCorporativo, f);
		
		List listRangoNivel = this.letProgramaCorporativoRangoNivelList;
		if (listRangoNivel != null && listRangoNivel.size() > 0) {
            List parametros = new ArrayList();
            for (int i = 0; i < listRangoNivel.size(); i++) {
                RangoNivel rangoNivel = new RangoNivel();
                BeanUtils.copyProperties(rangoNivel, listRangoNivel.get(i));
                parametros.add(rangoNivel);
            }
            programaCorporativo.setRangoNivelList(parametros);
        }
		
		List listRangoRetencion = this.letProgramaCorporativoRangoRetencionList;
		if (listRangoRetencion != null && listRangoRetencion.size() > 0) {
            List parametros = new ArrayList();
            for (int i = 0; i < listRangoRetencion.size(); i++) {
                RangoRetencion rangoRetencion = new RangoRetencion();
                BeanUtils.copyProperties(rangoRetencion, listRangoRetencion.get(i));
                parametros.add(rangoRetencion);
            }
            programaCorporativo.setRangoRetencionList(parametros);
        }
		
		List listTramos = this.letProgramaCorporativoTramosList;
		if (listTramos != null && listTramos.size() > 0) {
            List parametros = new ArrayList();
            for (int i = 0; i < listTramos.size(); i++) {
                Tramos tramos = new Tramos();
                BeanUtils.copyProperties(tramos, listTramos.get(i));
                parametros.add(tramos);
            }
            programaCorporativo.setTramosList(parametros);
        }
		
		List listPremios= this.letProgramaCorporativoPremiosList;
		if (listPremios != null && listPremios.size() > 0) {
            List parametros = new ArrayList();
            for (int i = 0; i < listPremios.size(); i++) {
                Premios premios = new Premios();
                BeanUtils.copyProperties(premios, listPremios.get(i));
                parametros.add(premios);
            }
            programaCorporativo.setPremiosList(parametros);
        }
		
		try {
			if ((listRangoNivel == null || listRangoNivel.size() <= 0) ||
				(listRangoRetencion == null || listRangoRetencion.size() <= 0) ||
				(listTramos == null || listTramos.size() <= 0)) {				
				throw new Exception(this.getResourceMessage("mantenimientoLETProgramaCorporativoForm.noExistenDatos.grillas"));
			}
			
			Map criteria = new HashMap();
			criteria.put("codigoPrograma", "");
			
			if(StringUtils.isBlank(f.getPeriodoFin())){
				int indExisteProgramaCampFinNulo = Integer.parseInt(service.getExisteProgramaCampanaFinNulo(criteria));
				
				if(indExisteProgramaCampFinNulo > 0){
					criteria.put("codigoPrograma", f.getCodigoPrograma());					
					indExisteProgramaCampFinNulo = Integer.parseInt(service.getExisteProgramaCampanaFinNulo(criteria));					
					if(indExisteProgramaCampFinNulo == 0)						
						throw new Exception(this.getResourceMessage("mantenimientoLETProgramaCorporativoForm.existePrograma.campanaFinNulo"));
								
				}
			}
			
			if (f.isNewRecord()) { //INSERTAR
				int maxCampanaFinPrograma = Integer.parseInt(service.getMaximaCampanaFinPrograma());				
				if(Integer.parseInt(f.getPeriodoInicio()) > maxCampanaFinPrograma)
					service.insertProgramaCorporativo(programaCorporativo, usuario);					
				else				
					throw new Exception(this.getResourceMessage("mantenimientoLETProgramaCorporativoForm.campanaInicio.menor.maxCampanaFin"));
				
			}else { //MODIFICAR
				service.updateProgramaCorporativo(programaCorporativo, usuario);				
			}
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("ERROR:", error);					
			return false;
		}
		
		Map criteria = new HashMap();
		criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoPrograma", "");
		criteria.put("descripcionPrograma", "");
		
		List programaCorporativoList = service.getProgramaCorporativoList(criteria);
		
		//session.setAttribute(Constants.LET_PROGRAMA_CORPORATIVO_LIST, programaCorporativoList);
		this.letProgramaCorporativoRangoNivelList=null;
		this.letProgramaCorporativoRangoRetencionList=null;
		this.letProgramaCorporativoTramosList=null;
		this.letProgramaCorporativoPremiosList=null;		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {		
		Map sistemabusqueda = (Map) this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoLETProgramaCorporativoForm f = new MantenimientoLETProgramaCorporativoForm();		
		
		MantenimientoLETProgramaCorporativoService service = (MantenimientoLETProgramaCorporativoService) getBean("spusicc.mantenimientoLETProgramaCorporativoService");
		this.letTipoNivelExitoList=service.getTipoNivelExito();
		
		String codigoPrograma = service.getNextCodigoProgramaCorporativo();
		f.setCodigoPrograma(codigoPrograma);
		f.setTabSeleccion(Constants.LET_TAB_PROGRAMA_CORPORATIVO_RANGO_NIVEL);
		f.setCampanyaPremioBuscar(""); 
		f.setCodigoPais(pais.getCodigo());
		
		this.mostrarBotonSalir=false;		
        this.consultar=false;
        this.indExigenciaPedidoWeb=false;
        this.indGananciaLiderNueva=false;
        this.indicadorActivoRangoNivel=Constants.NUMERO_CERO;
        this.indicadorActivoRangoRetencion=Constants.NUMERO_CERO;
        this.indicadorActivoTramos=Constants.NUMERO_CERO;
        this.indicadorActivoPremios=Constants.NUMERO_CERO;
        
        this.letProgramaCorporativoRangoNivelDataModel=new DataTableModel();				
		this.letProgramaCorporativoRangoRetencionDataModel=new DataTableModel();
		this.letProgramaCorporativoTramosDataModel=new DataTableModel();
		this.letProgramaCorporativoPremiosDataModel=new DataTableModel();
      		
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			String id = sistemabusqueda.get("codigoPrograma").toString();
			String codigoPais = pais.getCodigo();			
			if (id != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + id + " "+ codigoPais);
				}				   	       
				this.obtenerRegistro(id, f);
				f.setNewRecord(false);					
				this.letProgramaCorporativoRangoNivelDataModel=new DataTableModel(this.letProgramaCorporativoRangoNivelList);				
				this.letProgramaCorporativoRangoRetencionDataModel=new DataTableModel(this.letProgramaCorporativoRangoRetencionList);
				this.letProgramaCorporativoTramosDataModel=new DataTableModel(this.letProgramaCorporativoTramosList);
				this.letProgramaCorporativoPremiosDataModel=new DataTableModel(this.letProgramaCorporativoPremiosList);				
			}
			if(this.accion.equals(this.ACCION_CONSULTAR)){
				this.consultar=true;	
				this.mostrarBotonSave=false;
			}		
		}
		return f;		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoLETProgramaCorporativoSearchForm f = (MantenimientoLETProgramaCorporativoSearchForm)this.formBusqueda;			
		
		f.setCodigoPrograma("");
		f.setDescripcionPrograma("");
		f.setCodigoPais(pais.getCodigo());
	
		
	}
	
	/**
	 * Obtener todo la informacion de una Programa Corporativo	
	 */
	private void obtenerRegistro(String id, MantenimientoLETProgramaCorporativoForm f) throws Exception {
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();		
		//MantenimientoLETProgramaCorporativoForm f = (MantenimientoLETProgramaCorporativoForm)this.formMantenimiento;
		MantenimientoLETProgramaCorporativoService service = (MantenimientoLETProgramaCorporativoService) getBean("spusicc.mantenimientoLETProgramaCorporativoService");		
		
		Map criteria = BeanUtils.describe(f);
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPrograma", id);
		List listProgramaCorporativo = service.getProgramaCorporativoList(criteria);
		
		if (listProgramaCorporativo != null && listProgramaCorporativo.size() > 0) {
            for (int i = 0; i < listProgramaCorporativo.size(); i++) {
                BeanUtils.copyProperties(f, listProgramaCorporativo.get(i));
            }
        }
		
		List listRangoNivel  = service.getRangoNivelLET(criteria);
		List listRangoNivel2 = new ArrayList();
		if (listRangoNivel != null && listRangoNivel.size() > 0) {
            for (int i = 0; i < listRangoNivel.size(); i++) {
            	RangoNivel rangoNivel = new RangoNivel();
                BeanUtils.copyProperties(rangoNivel, listRangoNivel.get(i));
                rangoNivel.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
                listRangoNivel2.add(rangoNivel);
            }
        }
		
		List listRangoRetencion  = service.getRangoRetencionLET(criteria);
		List listRangoRetencion2 = new ArrayList();
		if (listRangoRetencion != null && listRangoRetencion.size() > 0) {
            for (int i = 0; i < listRangoRetencion.size(); i++) {
            	RangoRetencion rangoRetencion = new RangoRetencion();
                BeanUtils.copyProperties(rangoRetencion, listRangoRetencion.get(i));
                rangoRetencion.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
                listRangoRetencion2.add(rangoRetencion);
            }
        }
		
		List listTramos  = service.getTramosLET(criteria);
		List listTramos2 = new ArrayList();
		if (listTramos != null && listTramos.size() > 0) {
            for (int i = 0; i < listTramos.size(); i++) {
            	Tramos tramos = new Tramos();
                BeanUtils.copyProperties(tramos, listTramos.get(i));
                tramos.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
                listTramos2.add(tramos);
            }
        }
		
		List listPremios  = service.getPremiosLET(criteria);
		List listPremios2 = new ArrayList();
		if (listPremios != null && listPremios.size() > 0) {
            for (int i = 0; i < listPremios.size(); i++) {
            	Premios premios = new Premios();
                BeanUtils.copyProperties(premios, listPremios.get(i));
                premios.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
                listPremios2.add(premios);
            }
        }
		this.letProgramaCorporativoRangoNivelList=listRangoNivel2;
		this.letProgramaCorporativoRangoRetencionList=listRangoRetencion2;
		this.letProgramaCorporativoTramosList=listTramos2;
		this.letProgramaCorporativoPremiosList=listPremios2;   
		if(StringUtils.equals(f.getIndExigenciaPedidoWeb(), "1"))
			this.indExigenciaPedidoWeb=true;
		else
			this.indExigenciaPedidoWeb=false;
		if(StringUtils.equals(f.getIndGananciaLiderNueva(), "1"))
			this.indGananciaLiderNueva=true;
		else
			this.indGananciaLiderNueva=false;
	}
	
	
	//RANGO NIVEL
	public void nuevaVistaMantenerRangoNivel(ActionEvent event){
		this.beanletProgramaCorporativoRangoNivel=null;
		this.vistaMantenerRangoNivel(event);
	}
	
	public void vistaMantenerRangoNivel(ActionEvent event){
		try {			
			MantenimientoLETProgramaCorporativoRangoNivelForm f=  new MantenimientoLETProgramaCorporativoRangoNivelForm();
						
			if (this.beanletProgramaCorporativoRangoNivel!=null) {
							
				RangoNivel rangoNivel = (RangoNivel)this.beanletProgramaCorporativoRangoNivel;
					
				f.setCodigoNivel(rangoNivel.getCodigoNivel());
				f.setPedidosIniciales(rangoNivel.getPedidosIniciales());
				f.setPedidosFinales(rangoNivel.getPedidosFinales());
				f.setTolerancia(rangoNivel.getTolerancia());
				f.setPercentil(rangoNivel.getPercentil());
				f.setGananciaCumplimiento(rangoNivel.getGananciaCumplimiento());
				f.setGananciaSobrecumplimiento(rangoNivel.getGananciaSobrecumplimiento());
				f.setEstado(rangoNivel.getEstado());
					
				if(StringUtils.isBlank(rangoNivel.getCorrelativo()))
					f.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
				else
					f.setCorrelativo(rangoNivel.getCorrelativo());						
			}			
			this.rangoNivelForm=f;
			this.mostrarBotonSave=false;
			this.mostrarBotonSalir=false;
			this.tabShow.setActiveIndex(0);
			this.redireccionarPagina("mantenimientoLETProgramaCorporativoTabRangoNivelForm");			
		} catch (Exception e) {			
			e.printStackTrace();
			String error = e.getMessage();
			this.tabShow.setActiveIndex(0);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", error);			
		}
	}
	
	public void guardarMantenerRangoNivel(ActionEvent event) {
		try {
			MantenimientoLETProgramaCorporativoRangoNivelForm f = (MantenimientoLETProgramaCorporativoRangoNivelForm) this.rangoNivelForm;
			
			List list =this.letProgramaCorporativoRangoNivelList;
			List listNivel =this.letTipoNivelExitoList;	
			
			if(list == null){
				list = new ArrayList();
			}
			
			String index =  this.indexRangoNivel;
			index = (index == null || StringUtils.isBlank(index)) ? "0" : index; 
			
			if(this.isCodigoNivelValida(f.getCodigoNivel(), list, f.getCorrelativo())){
				if(this.isPedidosRangoNivelValida(f.getPedidosIniciales(), f.getPedidosFinales(), list, f.getCorrelativo())){
					RangoNivel rangoNivel = new RangoNivel();
					rangoNivel.setCodigoNivel(f.getCodigoNivel());
					rangoNivel.setPedidosIniciales(f.getPedidosIniciales());
					rangoNivel.setPedidosFinales(f.getPedidosFinales());
					rangoNivel.setTolerancia(f.getTolerancia());
					rangoNivel.setPercentil(f.getPercentil());
					rangoNivel.setGananciaCumplimiento(f.getGananciaCumplimiento());
					rangoNivel.setGananciaSobrecumplimiento(f.getGananciaSobrecumplimiento());
					rangoNivel.setEstado(Constants.ESTADO_ACTIVO);
					
					int j=0;
					for(j = 0; j <= listNivel.size(); j ++){
						Base nivel =(Base)listNivel.get(j);
						if(StringUtils.equals(nivel.getCodigo(), rangoNivel.getCodigoNivel())){
							rangoNivel.setDescripcionNivel(nivel.getDescripcion());
							break;
						}
					}
					
					if(StringUtils.isBlank(f.getCorrelativo())){
						rangoNivel.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
						list.add(rangoNivel);
					}else{
						if(this.validarUpdateRangoNivelCorrecta(list, Integer.parseInt(index), f.getPedidosIniciales(), f.getPedidosFinales())){
							rangoNivel.setCorrelativo(f.getCorrelativo());
							actualizarRangoNivel(list, rangoNivel, f.getCorrelativo());
						}else{
							throw new Exception(this.getResourceMessage("mantenimientoLETProgramaCorporativoRangoNivelForm.existe.pedidos.traslape")); 					
						}
					}
					
					f.setCorrelativo(null);									
					
					this.letProgramaCorporativoRangoNivelList=list;
					this.letProgramaCorporativoRangoNivelDataModel=new DataTableModel(this.letProgramaCorporativoRangoNivelList);				
					this.tabShow.setActiveIndex(0);				
					this.indicadorActivoRangoNivel=Constants.NUMERO_UNO;
					this.addInfo("Info:", this.getResourceMessage("mantenimientoLETProgramaCorporativoRangoNivelForm.datos.insert"));
				}else{
					throw new Exception(this.getResourceMessage("mantenimientoLETProgramaCorporativoRangoNivelForm.traslape.rangosSiguientes"));					
				}
			}else{
				throw new Exception(this.getResourceMessage("mantenimientoLETProgramaCorporativoRangoNivelForm.existe.rangoNivel"));			
			}
			
		} catch (Exception e) {			
			String error = e.getMessage();
			this.tabShow.setActiveIndex(0);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", error);			
		}
	}
	
	public void eliminarRangoNivel(ActionEvent actionEvent){		
		try {			
			RangoNivel benRangoNivel=(RangoNivel)this.beanletProgramaCorporativoRangoNivel;
			int id=-1;
			List list = this.letProgramaCorporativoRangoNivelList;
			for(int i=0;i<list.size();i++){					
				RangoNivel nrango= new RangoNivel();
				nrango=(RangoNivel)list.get(i);
					if(nrango.equals(benRangoNivel)){
						id=i;
						break;
					}
			}			
			list.remove(id);
			this.letProgramaCorporativoRangoNivelList=list;
			this.indicadorActivoRangoNivel=Constants.NUMERO_UNO;
			this.letProgramaCorporativoRangoNivelDataModel=new DataTableModel(this.letProgramaCorporativoRangoNivelList);	
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", error);
		}
	}
	
	
	//Salir Pantalla Form 
	public void salir(ActionEvent actionEvent) {
		try {
			this.mostrarBotonSalir=false;
			this.mostrarBotonSave=true;
			this.redireccionarPagina("mantenimientoLETProgramaCorporativoForm");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	public void salirPadre(ActionEvent actionEvent) {
		try {			
			this.redireccionarPagina("mantenimientoLETProgramaCorporativoList");
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}

	}
	
	private boolean isCodigoNivelValida(String codigoNuevo, List list, String correlativoRangoNivel){
		//Verificamos en la grilla
		boolean existe = false;
		
		for(int i=0; i<list.size(); i++){
			RangoNivel reg = (RangoNivel)list.get(i);
			
			if(StringUtils.isBlank(correlativoRangoNivel)){
				//registrando uno nuevo
				if(StringUtils.equals(codigoNuevo, reg.getCodigoNivel())){
					existe = true;
					break;
				}
			}else{
				//Editando
				if((StringUtils.equals(codigoNuevo, reg.getCodigoNivel()) && StringUtils.equals(correlativoRangoNivel, reg.getCorrelativo()))){
					existe = false;
					break;
				}else{
					existe = true;
				}
			}
		}
		
		return !existe;
	}
	
	private boolean isPedidosRangoNivelValida(String pedidoInicial, String pedidoFinal, List list, String correlativoRangoNivel) {
		
		//Verificamos en la grilla
		Iterator it = list.iterator();
		it = list.iterator();
		 
	    while(it.hasNext()){
	    	RangoNivel rangoNivelAux = (RangoNivel)it.next();
		  
			String pedidoInicialAux = rangoNivelAux.getPedidosIniciales();
			String pedidoFinalAux = rangoNivelAux.getPedidosFinales();
			String correlativoRangoNivelAux = rangoNivelAux.getCorrelativo();
			
			//si son distintos se valida traslape los valores	
			if(
				    (((Integer.parseInt(pedidoInicial) >= Integer.parseInt(pedidoInicialAux)) &&  
				    		(Integer.parseInt(pedidoInicial) <= Integer.parseInt(pedidoFinalAux)))
					 ||
					 ((Integer.parseInt(pedidoFinal) >= Integer.parseInt(pedidoInicialAux)) &&  
					         (Integer.parseInt(pedidoFinal) <= Integer.parseInt(pedidoFinalAux)))
					 ||
						 ( (Integer.parseInt(pedidoInicial) < Integer.parseInt(pedidoInicialAux)) &&
								 (Integer.parseInt(pedidoInicial) > Integer.parseInt(pedidoFinalAux)))
					 )		 
				   ){
				
				if(StringUtils.isNotEmpty(correlativoRangoNivel) && StringUtils.isNotEmpty(correlativoRangoNivelAux)
				    && StringUtils.equals(correlativoRangoNivel, correlativoRangoNivelAux)) {
					continue;
				}
			  return false;
			}
		}
	    
		return true;
	}

	private boolean validarUpdateRangoNivelCorrecta (List listaRangoNivel, int index, String valPedidoInicial, String valPedidoFinal){
		int pedidoAnterior = 0;
		int pedidoPosterior = 0;
		boolean resultado = false;
	
		//Ingreso por primera vez
		if (listaRangoNivel.size() == 0 || listaRangoNivel == null) {
			return true;
		}

		//Valida que el Pedido Inicial sea mayor al Pedido Final
		if (listaRangoNivel.size() -2 >= 0) {
			if (index - 2 >= 0) {
				RangoNivel rna = (RangoNivel)listaRangoNivel.get(index -2);
				pedidoAnterior = Integer.parseInt(rna.getPedidosFinales());
			}
		}
	
		if (listaRangoNivel.size()>= index + 1) {
			RangoNivel rnp = (RangoNivel) listaRangoNivel.get(index);
			pedidoPosterior = Integer.parseInt(rnp.getPedidosIniciales());
		}
	
		log.debug("el tramo elegido es: "+ index);
	
		int pedidoInicial = Integer.parseInt(valPedidoInicial);
		int pedidoFinal = Integer.parseInt(valPedidoFinal);
	
	
		if(pedidoAnterior == 0 && pedidoPosterior == 0)
			return true;
	
		if (!resultado) {
			// No existe un campo anterior
			if (pedidoPosterior == 0) {
				log.debug("No existe un campo anterior");
				if (pedidoAnterior < pedidoInicial){
				return true;
			}
			}else {
				if (pedidoAnterior > pedidoInicial ||  pedidoFinal > pedidoPosterior) {
					log.debug("El Pedido Inicial y Final no se encuentra en el rango del Pedido Anterior y al Pedido posterior");
					resultado =  false;
				}else {
					return true;
				}
			}
		}

		return resultado;
	}
	
	private void actualizarRangoNivel(List lista, RangoNivel newRangoNivel, String codigo) {
		for(int i = 0; i < lista.size(); i ++) {
			RangoNivel rn = (RangoNivel)lista.get(i);
			if(StringUtils.equals(rn.getCorrelativo(), codigo)) {
				lista.set(i, newRangoNivel);
				break;
			}
		}
	}
	
	//RANGO RETENCION	
	public void nuevoVistaMantenerRangoRetencion(ActionEvent actionEvent){
		this.beanletProgramaCorporativoRangoRetencion=null;
		this.vistaMantenerRangoRetencion(actionEvent);
	}
	
	public void vistaMantenerRangoRetencion(ActionEvent actionEvent){
		try {
			MantenimientoLETProgramaCorporativoRangoRetencionForm f = new MantenimientoLETProgramaCorporativoRangoRetencionForm();
			this.indicadorPremio=false;						
			List list = this.letProgramaCorporativoRangoRetencionList;			
			
			if (this.beanletProgramaCorporativoRangoRetencion!=null) {
			
					RangoRetencion rangoRetencion = (RangoRetencion)this.beanletProgramaCorporativoRangoRetencion;

					f.setCodigoRetencion(rangoRetencion.getCodigoRetencion());
					f.setIngresosIniciales(rangoRetencion.getIngresosIniciales());
					f.setIngresosFinales(rangoRetencion.getIngresosFinales());
					f.setGanancia22(rangoRetencion.getGanancia22());
					f.setGanancia33(rangoRetencion.getGanancia33());
					f.setGanancia44(rangoRetencion.getGanancia44());
					f.setRetencion22(rangoRetencion.getRetencion22());
					f.setRetencion33(rangoRetencion.getRetencion33());
					f.setRetencion44(rangoRetencion.getRetencion44());
					f.setIndicadorPremio(rangoRetencion.getIndicadorPremio());
					f.setEstado(rangoRetencion.getEstado());
					
					if(StringUtils.isBlank(rangoRetencion.getCorrelativo()))
						f.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
					else
						f.setCorrelativo(rangoRetencion.getCorrelativo());
					if(StringUtils.equals(f.getIndicadorPremio(), "1"))
						this.indicadorPremio=true;
					else
						this.indicadorPremio=false;
					
				
			}else{
				if(list == null || list.size() == 0){
					f.setCodigoRetencion("01");
				}else{
					RangoRetencion rangoRetencion = (RangoRetencion)list.get(list.size() -1);
					
					int nextCodigoRetencion = Integer.parseInt(rangoRetencion.getCodigoRetencion()) + 1;
					String codigoRetencion = (nextCodigoRetencion > 9) ? ""+nextCodigoRetencion : "0"+nextCodigoRetencion;
					
					f.setCodigoRetencion(codigoRetencion);
				}
			}
			
			this.rangoRetencionForm=f;
			this.mostrarBotonSave=false;
			this.mostrarBotonSalir=false;
			this.tabShow.setActiveIndex(1);
			this.redireccionarPagina("mantenimientoLETProgramaCorporativoTabRangoRetencionForm");	
		} catch (Exception e) {
			e.printStackTrace();
			String error = e.getMessage();
			this.tabShow.setActiveIndex(1);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}
	}
	
	public void guardarMantenerRangoRetencion(ActionEvent actionEvent){
		try {
			MantenimientoLETProgramaCorporativoRangoRetencionForm f = (MantenimientoLETProgramaCorporativoRangoRetencionForm) this.rangoRetencionForm;
			//Validaciones
			if(Integer.parseInt(f.getIngresosIniciales())<=0)
				throw new Exception("Número Ingresos Iniciales debe ser mayor a 0");
			if(Integer.parseInt(f.getIngresosFinales())<=0)
				throw new Exception("Número Ingresos Finales debe ser mayor a 0");
			if(Integer.parseInt(f.getIngresosFinales())<Integer.parseInt(f.getIngresosIniciales()))
				throw new Exception("Número Ingresos Finales debe ser mayor o igual a Número Ingresos Iniciales");
			
			if(Float.parseFloat(f.getRetencion22())<=0)
				throw new Exception("Retención 2/2 debe ser mayor a 0");
			if(Float.parseFloat(f.getRetencion33())<=0)
				throw new Exception("Retención 3/3 debe ser mayor a 0");
			if(Float.parseFloat(f.getRetencion44())<=0)
				throw new Exception("Retención 4/4 debe ser mayor a 0");
        	
			if(this.indicadorPremio)
				f.setIndicadorPremio("1");
			else
				f.setIndicadorPremio("0");
			List list = this.letProgramaCorporativoRangoRetencionList;
			
			if(list == null){
				list = new ArrayList();
			}
			
			if(this.isIngresosRangoRetencionValida(f.getIngresosIniciales(), f.getIngresosFinales(), list, f.getCorrelativo())){
				RangoRetencion rangoRetencion = new RangoRetencion();
				rangoRetencion.setCodigoRetencion(f.getCodigoRetencion());
				rangoRetencion.setIngresosIniciales(f.getIngresosIniciales());
				rangoRetencion.setIngresosFinales(f.getIngresosFinales());
				rangoRetencion.setGanancia22(f.getGanancia22());
				rangoRetencion.setGanancia33(f.getGanancia33());
				rangoRetencion.setGanancia44(f.getGanancia44());
				rangoRetencion.setRetencion22(f.getRetencion22());
				rangoRetencion.setRetencion33(f.getRetencion33());
				rangoRetencion.setRetencion44(f.getRetencion44());
				rangoRetencion.setIndicadorPremio(f.getIndicadorPremio());
				rangoRetencion.setEstado(Constants.ESTADO_ACTIVO);
				
				if(StringUtils.isBlank(f.getCorrelativo())){
					rangoRetencion.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
					list.add(rangoRetencion);
				}else{
					rangoRetencion.setCorrelativo(f.getCorrelativo());
					actualizarRangoRetencion(list, rangoRetencion, f.getCorrelativo());
				}
				
				f.setCorrelativo(null);
				
				this.addInfo("Info:", this.getResourceMessage("mantenimientoLETProgramaCorporativoRangoRetencionForm.datos.insert"));			
				
				//session.setAttribute(Constants.LET_MANT_RANGO_RETENCION_CERRAR, Constants.NUMERO_UNO);
				this.letProgramaCorporativoRangoRetencionList=list;
				this.letProgramaCorporativoRangoRetencionDataModel=new DataTableModel(this.letProgramaCorporativoRangoRetencionList);	
				this.indicadorActivoRangoRetencion=Constants.NUMERO_UNO;					
				this.tabShow.setActiveIndex(1);
				
			}else{				
				this.addError("Error: ", this.getResourceMessage("mantenimientoLETProgramaCorporativoRangoRetencionForm.existe.ingresos.traslape"));
			}
		} catch (Exception e) {
			String error = e.getMessage();
			this.tabShow.setActiveIndex(1);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}			
	}
	
	

	public void eliminarRangoRetencion(ActionEvent actionEvent){
		try {			
			RangoRetencion benRango=(RangoRetencion)this.beanletProgramaCorporativoRangoRetencion;
			int id=-1;
			List list = this.letProgramaCorporativoRangoRetencionList;
			for(int i=0;i<list.size();i++){					
				RangoRetencion nrango= new RangoRetencion();
				nrango=(RangoRetencion)list.get(i);
					if(nrango.equals(benRango)){
						id=i;
						break;
					}
			}					
			list.remove(id);
			this.letProgramaCorporativoRangoRetencionList=list;
			this.indicadorActivoRangoRetencion=Constants.NUMERO_UNO;			
			this.letProgramaCorporativoRangoRetencionDataModel=new DataTableModel(this.letProgramaCorporativoRangoRetencionList);			
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
			this.addError("Error: ", error);
		}	
	}
	
	private void actualizarRangoRetencion(List lista, RangoRetencion newRangoRetencion, String codigo) {
		for(int i = 0; i < lista.size(); i ++) {
			RangoRetencion rr = (RangoRetencion)lista.get(i);
			if(StringUtils.equals(rr.getCorrelativo(), codigo)) {
				lista.set(i, newRangoRetencion);
				break;
			}
		}
	}
	
	private boolean isIngresosRangoRetencionValida(String ingresoInicial, String ingresoFinal, List list, String correlativoRangoRetencion) {
		
		//Verificamos en la grilla
		Iterator it = list.iterator();
		it = list.iterator();
		 
	    while(it.hasNext()){
	    	RangoRetencion rangoRetencionAux = (RangoRetencion)it.next();
		  
			String ingresoInicialAux = rangoRetencionAux.getIngresosIniciales();
			String ingresoFinalAux = rangoRetencionAux.getIngresosFinales();
			String correlativoRangoRetencionAux = rangoRetencionAux.getCorrelativo();
			
			//si son distintos se valida traslape los valores	
			if(
				    (((Integer.parseInt(ingresoInicial) >= Integer.parseInt(ingresoInicialAux)) &&  
				    		(Integer.parseInt(ingresoInicial) <= Integer.parseInt(ingresoFinalAux)))
					 ||
					 ((Integer.parseInt(ingresoFinal) >= Integer.parseInt(ingresoInicialAux)) &&  
					         (Integer.parseInt(ingresoFinal) <= Integer.parseInt(ingresoFinalAux)))
					 ||
						 ( (Integer.parseInt(ingresoInicial) < Integer.parseInt(ingresoInicialAux)) &&
								 (Integer.parseInt(ingresoInicial) > Integer.parseInt(ingresoFinalAux)))
					 )		 
				   ){
				
				if(StringUtils.isNotEmpty(correlativoRangoRetencion) && StringUtils.isNotEmpty(correlativoRangoRetencionAux)
				    && StringUtils.equals(correlativoRangoRetencion, correlativoRangoRetencionAux)) {
					continue;
				}
			  return false;
			}
		}
	    
		return true;
	}
	
	//TRAMOS	
	public void nuevoVistaMantenerTramos(ActionEvent actionEvent){	
		this.beanletProgramaCorporativoTramos=null;
		this.indRetencion22=false;
		this.indRetencion33=false;
		this.indRetencion44=false;
		this.mostrarCampana=false;
		this.vistaMantenerTramos(actionEvent);	
	}
	
	public void vistaMantenerTramos(ActionEvent actionEvent){
		try {
			MantenimientoLETProgramaCorporativoTramosForm f = new MantenimientoLETProgramaCorporativoTramosForm();	
			MantenimientoLETProgramaCorporativoForm primerForm = (MantenimientoLETProgramaCorporativoForm)this.formMantenimiento;		
			
			this.tabShow.setActiveIndex(2);			
			String periodoInicio = primerForm .getPeriodoInicio();
			String periodoFin = primerForm .getPeriodoFin();
			if(StringUtils.isBlank(periodoInicio)){
				this.setMensajeAlertaDefault("Ingrese por lo menos Campaña Inicio para poder registrar Tramos");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}				
			if(StringUtils.isNotBlank(periodoFin)){
				int codperini = Integer.parseInt(periodoInicio);
				int codperfin = Integer.parseInt(periodoFin);
				if(codperfin<codperini){
					this.setMensajeAlertaDefault("Campaña Fin debe ser mayor o igual a la Campaña Inicio");
					String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;
				}					
			}
			
			this.indicadorHabilitaTramos=Constants.NUMERO_CERO;			
			List list =this.letProgramaCorporativoTramosList;
			
			if (this.beanletProgramaCorporativoTramos!=null) {				
					Tramos tramos = (Tramos)this.beanletProgramaCorporativoTramos;
					f.setCodigoTramo(tramos.getCodigoTramo());
					f.setPeriodoInicioTramo(tramos.getPeriodoInicioTramo());
					f.setPeriodoFinTramo(tramos.getPeriodoFinTramo());
					f.setRetencion22Tramo(tramos.getRetencion22Tramo());
					f.setRetencion33Tramo(tramos.getRetencion33Tramo());
					f.setRetencion44Tramo(tramos.getRetencion44Tramo());
					f.setEvaluacionNivelExito(tramos.getEvaluacionNivelExito());
					f.setNumeroCampanasCambiarNivel(tramos.getNumeroCampanasCambiarNivel());
					f.setEstado(tramos.getEstado());
					this.indicadorHabilitaTramos=obtenerIndicador(tramos.getPeriodoInicioTramo(),tramos.getPeriodoFinTramo());					
					if(StringUtils.isBlank(tramos.getCorrelativo())){
						f.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
					}else{
						f.setCorrelativo(tramos.getCorrelativo());
					}
					
					if(StringUtils.equals(f.getRetencion22Tramo(), Constants.NUMERO_UNO))
						this.indRetencion22=true;
					else
						this.indRetencion22=false;					
					if(StringUtils.equals(f.getRetencion33Tramo(), Constants.NUMERO_UNO))
						this.indRetencion33=true;
					else
						this.indRetencion33=false;					
					if(StringUtils.equals(f.getRetencion44Tramo(), Constants.NUMERO_UNO))
						this.indRetencion44=true;
					else
						this.indRetencion44=false;
					if(StringUtils.equals(f.getEvaluacionNivelExito(), "2"))
						this.mostrarCampana=true;
					else
						this.mostrarCampana=false;
				
			}else{
				if(list == null || list.size() == 0){
					f.setCodigoTramo("01");
				}else{
					String codigoTramo = "";
					int nextCodigoTramo = 0;
					
					int codigoTramoMenor = 0;
					int codigoTramoMayor = 0;
					
					for (int i = 0; i < list.size(); i++) {
						Tramos tramos = (Tramos)list.get(i);
						codigoTramoMenor = Integer.parseInt(tramos.getCodigoTramo());
						
						if(codigoTramoMayor == 0){
							codigoTramoMayor = codigoTramoMenor;
						}else{
							if(codigoTramoMenor < codigoTramoMayor){
								nextCodigoTramo = codigoTramoMayor;
							}else if(codigoTramoMenor > codigoTramoMayor){
								codigoTramoMayor = codigoTramoMenor;
								nextCodigoTramo = codigoTramoMayor;
							}
						}
					}
					
					nextCodigoTramo = nextCodigoTramo + 1;
					codigoTramo = (nextCodigoTramo > 9) ? ""+nextCodigoTramo : "0"+nextCodigoTramo;										
					f.setCodigoTramo(codigoTramo);
				}
			}
			
			this.tramosForm=f;
			this.mostrarBotonSave=false;
			this.mostrarBotonSalir=false;			
			this.redireccionarPagina("mantenimientoLETProgramaCorporativoTabTramosForm");			
		} catch (Exception e) {
			e.printStackTrace();
			String error = e.getMessage();
			this.tabShow.setActiveIndex(2);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}
	}
	
	public void guardarMantenerTramos(ActionEvent actionEvent){
		try {			
			MantenimientoLETProgramaCorporativoTramosForm f = (MantenimientoLETProgramaCorporativoTramosForm)this.tramosForm;			
			MantenimientoLETProgramaCorporativoForm principalForm = (MantenimientoLETProgramaCorporativoForm)this.formMantenimiento;
			
			//Validaciones
			this.tabShow.setActiveIndex(2);
			int inicioTramo = Integer.parseInt(f.getPeriodoInicioTramo());
			int finTramo = Integer.parseInt(f.getPeriodoFinTramo());
			if(finTramo<inicioTramo){
				this.setMensajeAlertaDefault("Campaña Fin debe ser mayor o igual a la Campaña Inicio");
				String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
				this.getRequestContext().execute(ventanaConfirmar);
				return;
			}			
					
			if(StringUtils.equals(f.getEvaluacionNivelExito(), "2")){
				if(StringUtils.isNotBlank(f.getNumeroCampanasCambiarNivel())){
					int numCampanas=Integer.parseInt(f.getNumeroCampanasCambiarNivel());
					if(numCampanas<=0 || numCampanas>(finTramo-inicioTramo+1)){	
						f.setNumeroCampanasCambiarNivel("");
						this.setMensajeAlertaDefault("El número debe ser MAYOR a 0 y MENOR o IGUAL a la diferencia de campañas");
						String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
						this.getRequestContext().execute(ventanaConfirmar);
						return;												
					}
				}else{
					this.setMensajeAlertaDefault("Ingrese un valor");
					String ventanaConfirmar = "PF('principalFormAlert_alertDialog').show()";
					this.getRequestContext().execute(ventanaConfirmar);
					return;	
				}								
			}
			
			if(StringUtils.equals(f.getEvaluacionNivelExito(), "1"))
				f.setNumeroCampanasCambiarNivel("");			
			
			String periodoInicioPrograma = principalForm.getPeriodoInicio();
			String periodoFinPrograma = principalForm.getPeriodoFin();
			
			
			if(this.indRetencion22)
				f.setRetencion22Tramo(Constants.NUMERO_UNO);
			else
				f.setRetencion22Tramo(Constants.NUMERO_CERO);
			if(this.indRetencion33)
				f.setRetencion33Tramo(Constants.NUMERO_UNO);
			else
				f.setRetencion33Tramo(Constants.NUMERO_CERO);
			if(this.indRetencion44)
				f.setRetencion44Tramo(Constants.NUMERO_UNO);
			else
				f.setRetencion44Tramo(Constants.NUMERO_CERO);

			List list = this.letProgramaCorporativoTramosList;
			
			if(list == null){
				list = new ArrayList();
			}
			
			if(StringUtils.equals(periodoFinPrograma, "") || StringUtils.equals(periodoFinPrograma, null))
				periodoFinPrograma = "999999";
			
			if(
					((Integer.parseInt(f.getPeriodoInicioTramo()) >= Integer.parseInt(periodoInicioPrograma)) &&
					(Integer.parseInt(f.getPeriodoInicioTramo()) <= Integer.parseInt(periodoFinPrograma)) &&
					(Integer.parseInt(f.getPeriodoFinTramo()) >= Integer.parseInt(periodoInicioPrograma)) &&
					(Integer.parseInt(f.getPeriodoFinTramo()) <= Integer.parseInt(periodoFinPrograma)))
				||
					((Integer.parseInt(f.getPeriodoFinTramo()) >= Integer.parseInt(periodoInicioPrograma)) &&
					(Integer.parseInt(f.getPeriodoFinTramo()) <= Integer.parseInt(periodoFinPrograma)))
				||
					((Integer.parseInt(f.getPeriodoInicioTramo()) < Integer.parseInt(periodoInicioPrograma)) &&
					(Integer.parseInt(f.getPeriodoInicioTramo()) > Integer.parseInt(periodoFinPrograma)))			){
				
				if(this.isCampanasTramosValida(f.getPeriodoInicioTramo(), f.getPeriodoFinTramo(), list, f.getCorrelativo())){
					Tramos tramos = new Tramos();
					tramos.setCodigoTramo(f.getCodigoTramo());
					tramos.setPeriodoInicioTramo(f.getPeriodoInicioTramo());
					tramos.setPeriodoFinTramo(f.getPeriodoFinTramo());
					tramos.setRetencion22Tramo(f.getRetencion22Tramo());
					tramos.setRetencion33Tramo(f.getRetencion33Tramo());
					tramos.setRetencion44Tramo(f.getRetencion44Tramo());
					tramos.setNumeroCampanasCambiarNivel(f.getNumeroCampanasCambiarNivel());
					tramos.setEvaluacionNivelExito(f.getEvaluacionNivelExito());
					tramos.setEstado(Constants.ESTADO_ACTIVO);
					
					if(StringUtils.isBlank(f.getCorrelativo())){
						tramos.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
						list.add(tramos);
					}else{
						tramos.setCorrelativo(f.getCorrelativo());
						actualizarTramos(list, tramos, f.getCorrelativo());
					}
					
					f.setCorrelativo(null);					
								
					this.letProgramaCorporativoTramosList=list;									
					this.indicadorActivoTramos=Constants.NUMERO_UNO;						
					this.letProgramaCorporativoTramosDataModel=new DataTableModel(this.letProgramaCorporativoTramosList);					
					this.addInfo("Info:", this.getResourceMessage("mantenimientoLETProgramaCorporativoTramosForm.datos.insert"));
				}else					
					this.addError("Error:", this.getResourceMessage("mantenimientoLETProgramaCorporativoTramosForm.existe.campanas.traslape"));				
			}else				
				this.addError("Error:", this.getResourceMessage("mantenimientoLETProgramaCorporativoTramosForm.campanas.nodentro.programa"));			
		
		} catch (Exception e) {
			String error = e.getMessage();
			this.tabShow.setActiveIndex(2);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}
	}
	
	public void eliminarTramos(ActionEvent actionEvent){
		try {
			Tramos beanTramos=(Tramos)this.beanletProgramaCorporativoTramos;
			int id=-1;
			List list = this.letProgramaCorporativoTramosList;
			for(int i=0;i<list.size();i++){
				Tramos nTramo=new Tramos();
				nTramo=(Tramos)list.get(i);					
				if(nTramo.equals(beanTramos)){
					id=i;
					break;
				}
			}
			beanTramos.setEstado(Constants.ESTADO_INACTIVO);
			list.set(id, beanTramos);
			this.letProgramaCorporativoTramosList=list;
			this.indicadorActivoTramos=Constants.NUMERO_UNO;	
			this.letProgramaCorporativoTramosDataModel=new DataTableModel(this.letProgramaCorporativoTramosList);			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
			
		}
	}
	
	private void actualizarTramos(List lista, Tramos newTramos, String codigo) {
		for(int i = 0; i < lista.size(); i ++) {
			Tramos t = (Tramos)lista.get(i);
			if(StringUtils.equals(t.getCorrelativo(), codigo)) {
				lista.set(i, newTramos);
				break;
			}
		}
	}
	
	private boolean isCampanasTramosValida(String campanaInicial, String campanaFinal, List list, String correlativoTramos) {
		
		//Verificamos en la grilla
		Iterator it = list.iterator();
		it = list.iterator();
		 
	    while(it.hasNext()){
	    	Tramos tramosAux = (Tramos)it.next();
		  
			String campanaInicialAux = tramosAux.getPeriodoInicioTramo();
			String campanaFinalAux = tramosAux.getPeriodoFinTramo();
			String correlativoTramosAux = tramosAux.getCorrelativo();
			
			//si son distintos se valida traslape los valores
			if(!StringUtils.equals(tramosAux.getEstado(), Constants.ESTADO_INACTIVO)){	
				if(
					    (((Integer.parseInt(campanaInicial) >= Integer.parseInt(campanaInicialAux)) &&  
					    		(Integer.parseInt(campanaInicial) <= Integer.parseInt(campanaFinalAux)))
						 ||
						 ((Integer.parseInt(campanaFinal) >= Integer.parseInt(campanaInicialAux)) &&  
						         (Integer.parseInt(campanaFinal) <= Integer.parseInt(campanaFinalAux)))
						 ||
							 ( (Integer.parseInt(campanaInicial) < Integer.parseInt(campanaInicialAux)) &&
									 (Integer.parseInt(campanaInicial) > Integer.parseInt(campanaFinalAux)))
						 )		 
					   ){
					
					if(StringUtils.isNotEmpty(correlativoTramos) && StringUtils.isNotEmpty(correlativoTramosAux)
					    && StringUtils.equals(correlativoTramos, correlativoTramosAux)) {
						continue;
					}
				  return false;
				}
	    	}
		}	    
		return true;
	}
	
	public String obtenerIndicador(String periodoInicio, String periodoFin){
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		MantenimientoLETProgramaCorporativoService service = (MantenimientoLETProgramaCorporativoService) getBean("spusicc.mantenimientoLETProgramaCorporativoService");
		String periodo = periodoInicio;
		String indicador = "0";
		String result = "";
		Map criteria = new HashMap();
		criteria.put("campanias", Constants.PERIODO_SIGUIENTE);
		criteria.put("codigoPeriodo", periodoFin);
		String periodoFinSiguiente = reporteService.getOidString("getDesPeriodoByCodigoPeriodoX", criteria);
		while(!StringUtils.equals(periodo, periodoFinSiguiente)){
			criteria.put("codigoPeriodo", periodo);
			result= service.getNivelesCalculadosByCampanha(criteria);
			if(StringUtils.isNotBlank(result) && Integer.parseInt(result)>0){
				indicador = "1";
				break;
			}
			periodo = reporteService.getOidString("getDesPeriodoByCodigoPeriodoX", criteria);
		}
		return indicador;
	}
	
	public void loadCampana(ValueChangeEvent val){		
		this.mostrarCampana=false;
		String valor=val.getNewValue().toString();
		if(StringUtils.equals(valor, "2"))
			this.mostrarCampana=true;
		
	}
	
	//PREMIOS
	
	public void nuevaVistaMantenerPremios(ActionEvent actionEvent){
		this.beanletProgramaCorporativoPremios=null;
		this.vistaMantenerPremios(actionEvent);
	}
	
	public void vistaMantenerPremios(ActionEvent actionEvent){
		try {
			MantenimientoLETProgramaCorporativoPremiosForm f = new MantenimientoLETProgramaCorporativoPremiosForm() ;
			
			if (this.beanletProgramaCorporativoPremios!=null) {
				
				List list =this.letProgramaCorporativoPremiosList;
				Premios premios = (Premios)this.beanletProgramaCorporativoPremios;

				f.setCampanyaPremio(premios.getCampanyaPremio());
				f.setCodigoNivelPremio(premios.getCodigoNivelPremio());
				f.setVariablePremio(premios.getVariablePremio());
				f.setCodigoPremio(premios.getCodigoPremio());
				f.setCodigoPremioAnterior(premios.getCodigoPremio());
				f.setDescripcionPremio(premios.getDescripcionPremio());
				f.setPrecioPremio(premios.getPrecioPremio());
				f.setEstado(premios.getEstado());
					
				if(StringUtils.isBlank(premios.getCorrelativo()))
					f.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
				else
					f.setCorrelativo(premios.getCorrelativo());	
				
			}
			this.premiosForm=f;
			this.mostrarBotonSave=false;
			this.mostrarBotonSalir=false;
			this.tabShow.setActiveIndex(3);
			this.redireccionarPagina("mantenimientoLETProgramaCorporativoTabPremiosForm");		
		} catch (Exception e) {
			e.printStackTrace();
			String error = e.getMessage();
			this.tabShow.setActiveIndex(3);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}
	}
	
	public void guardarMantenerPremios(ActionEvent actionEvent){
		try {
			MantenimientoLETProgramaCorporativoPremiosForm f = (MantenimientoLETProgramaCorporativoPremiosForm) this.premiosForm;
			
			List list = this.letProgramaCorporativoPremiosList;
			List listNivel = this.letTipoNivelExitoList;
			
			if(list == null){
				list = new ArrayList();
			}
			
			Premios premios = new Premios();
			premios.setCampanyaPremio(f.getCampanyaPremio());
			premios.setCodigoNivelPremio(f.getCodigoNivelPremio());
			premios.setVariablePremio(f.getVariablePremio());
			premios.setCodigoPremio(f.getCodigoPremio());
			premios.setCodigoPremioAnterior(f.getCodigoPremioAnterior());
			premios.setDescripcionPremio(f.getDescripcionPremio());
			premios.setPrecioPremio(f.getPrecioPremio());
			premios.setEstado(Constants.ESTADO_ACTIVO);
			
			int j=0;
			for(j = 0; j <= listNivel.size(); j ++){
				Base nivel =(Base)listNivel.get(j);
				if(StringUtils.equals(nivel.getCodigo(), premios.getCodigoNivelPremio())){
					premios.setDescripcionNivelPremio(nivel.getDescripcion());
					break;
				}
			}
			
			if(StringUtils.isBlank(f.getCorrelativo())){
				premios.setCorrelativo(String.valueOf(RandomUtils.nextLong()));
				list.add(premios);
			}else{
				premios.setCorrelativo(f.getCorrelativo());
				actualizarPremios(list, premios, f.getCorrelativo());
			}
			
			f.setCorrelativo(null);	
			
			this.addInfo("Info:", this.getResourceMessage("mantenimientoLETProgramaCorporativoPremiosForm.datos.insert"));		
			
			this.letProgramaCorporativoPremiosList=list;
			this.indicadorActivoPremios=Constants.NUMERO_UNO;
			this.tabShow.setActiveIndex(3);
			this.letProgramaCorporativoPremiosDataModel=new DataTableModel(this.letProgramaCorporativoPremiosList);	
			//session.removeAttribute("busquedaLetProgramaCorporativoPremiosList");
		
		} catch (Exception e) {
			String error = e.getMessage();
			this.tabShow.setActiveIndex(3);
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}
	}
	
	private void actualizarPremios(List lista, Premios newPremios, String codigo) {
		for(int i = 0; i < lista.size(); i ++) {
			Premios p = (Premios)lista.get(i);
			if(StringUtils.equals(p.getCorrelativo(), codigo)) {
				lista.set(i, newPremios);
				break;
			}
		}
	}
	
	public void eliminarPremios(ActionEvent actionEvent){
		try {			
			Premios beanPremio = (Premios) this.beanletProgramaCorporativoPremios;
			List list = this.letProgramaCorporativoPremiosList;
			int id=-1;
			
			for(int i=0;i<list.size();i++){
				Premios nPremio=new Premios();
				nPremio=(Premios)list.get(i);					
				if(nPremio.equals(beanPremio)){
					id=i;
					break;
				}
			}			
			beanPremio.setEstado(Constants.ESTADO_INACTIVO);
			list.set(id, beanPremio);
			this.letProgramaCorporativoPremiosList=list;
			this.indicadorActivoPremios=Constants.NUMERO_UNO;			
			this.letProgramaCorporativoPremiosDataModel=new DataTableModel(this.letProgramaCorporativoPremiosList);				
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}
	}
	
	public void buscarPremios(ActionEvent actionEvent){
		try {
			
			MantenimientoLETProgramaCorporativoForm f = (MantenimientoLETProgramaCorporativoForm)this.formMantenimiento;	
					
			List letPremiosList = this.letProgramaCorporativoPremiosList;
			List nuevoLetPremiosList = new ArrayList();
			
			if(letPremiosList != null && letPremiosList.size() > 0){
				for (int i=0; i < letPremiosList.size(); i++) {
					Premios premios = new Premios();
	                BeanUtils.copyProperties(premios, letPremiosList.get(i));

	                if(StringUtils.equals(premios.getCampanyaPremio(), f.getCampanyaPremioBuscar())){
	                	if(premios.getEstado() != Constants.ESTADO_INACTIVO){
	                		nuevoLetPremiosList.add(premios);
	                	}
	                }
				}
			}			
			this.busquedaLetProgramaCorporativoPremiosList=nuevoLetPremiosList;	
			this.tabShow.setActiveIndex(3);			
		} catch (Exception e) {
			String error = e.getMessage();
			this.tabShow.setActiveIndex(3);
			this.addError("Error: ", error);
		}
	}
	
	public void buscarCUV(){
		try {
			MantenimientoLETProgramaCorporativoPremiosForm f = (MantenimientoLETProgramaCorporativoPremiosForm) this.premiosForm;
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			if(StringUtils.isBlank(f.getCampanyaPremio())){
				f.setCodigoPremio("");				
				throw new Exception("Ingrese una Campaña para buscar el Premio");
				
			}else{
				String valorCodigo=this.rellenarCeros(f.getCodigoPremio(),5);
				f.setCodigoPremio(valorCodigo);
				String data=ajax.buscarCUV(f.getCodigoPremio(), f.getCampanyaPremio());
				if(StringUtils.isNotBlank(data))
					f.setDescripcionPremio(data);
				else{
					f.setDescripcionPremio("");
					throw new Exception("Código Premio no existe en la matriz para la Campaña ingresada");					
				}
			}		
			
		} catch (Exception e) {
			String error = e.getMessage();
			if (StringUtils.isBlank(error)) error = e.getLocalizedMessage();
				this.addError("Error: ", error);
		}
		
	}
	
	public String rellenarCeros(String cad, int cantidad){
		int n=cantidad-cad.length();
		String relleno="";
		if(n>0){			
			for( int i= 0; i < n; i++) {
			      relleno = relleno + "0";
			  } 
			relleno=relleno + cad;
			return relleno;
		}else
			return cad;
		
	}	
	
	public String setValidarConfirmar(String accion) {	
		if(accion.equals("MODIFICAR_RANGONIVEL") ){
			if(this.beanletProgramaCorporativoRangoNivel==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("ELIMINAR_RANGONIVEL") ){
			if(this.beanletProgramaCorporativoRangoNivel==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("MODIFICAR_RANGORETENCION") ){
			if(this.beanletProgramaCorporativoRangoRetencion==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("ELIMINAR_RANGORETENCION") ){
			if(this.beanletProgramaCorporativoRangoRetencion==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("MODIFICAR_TRAMOS") ){
			if(this.beanletProgramaCorporativoTramos==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("ELIMINAR_TRAMOS") ){
			if(this.beanletProgramaCorporativoTramos==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("MODIFICAR_PREMIOS") ){
			if(this.beanletProgramaCorporativoPremios==null)
				return this.getResourceMessage("errors.select.item");
		}
		if(accion.equals("ELIMINAR_PREMIOS") ){
			if(this.beanletProgramaCorporativoPremios==null)
				return this.getResourceMessage("errors.select.item");
		}
		return null;
	}
	
	public String setValidarMantenimiento(){		
		MantenimientoLETProgramaCorporativoForm f = (MantenimientoLETProgramaCorporativoForm)this.formMantenimiento;		
		String periodoInicio = f.getPeriodoInicio();
		String periodoFin = f.getPeriodoFin();
		if(StringUtils.isBlank(periodoInicio))
			return "Ingrese por lo menos Campaña Inicio para poder registrar Tramos";
		if(StringUtils.isNotBlank(periodoFin)){
			int codperini = Integer.parseInt(periodoInicio);
			int codperfin = Integer.parseInt(periodoFin);
			if(codperfin<codperini)
				return "Campaña Fin debe ser mayor o igual a la Campaña Inicio";	
		}	
		return "";
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoLETProgramaCorporativoForm f = (MantenimientoLETProgramaCorporativoForm)this.formMantenimiento;
		boolean isNew = f.isNewRecord();
		if (isNew) {
			return "mantenimientoLETProgramaCorporativoForm.added";
		} else {
			return "mantenimientoLETProgramaCorporativoForm.updated";
		}
	}

	public List getLetTipoNivelExitoList() {
		return letTipoNivelExitoList;
	}

	public void setLetTipoNivelExitoList(List letTipoNivelExitoList) {
		this.letTipoNivelExitoList = letTipoNivelExitoList;
	}

	public String getIndicadorActivoRangoNivel() {
		return indicadorActivoRangoNivel;
	}

	public void setIndicadorActivoRangoNivel(String indicadorActivoRangoNivel) {
		this.indicadorActivoRangoNivel = indicadorActivoRangoNivel;
	}

	public String getIndicadorActivoRangoRetencion() {
		return indicadorActivoRangoRetencion;
	}

	public void setIndicadorActivoRangoRetencion(
			String indicadorActivoRangoRetencion) {
		this.indicadorActivoRangoRetencion = indicadorActivoRangoRetencion;
	}

	public String getIndicadorActivoTramos() {
		return indicadorActivoTramos;
	}

	public void setIndicadorActivoTramos(String indicadorActivoTramos) {
		this.indicadorActivoTramos = indicadorActivoTramos;
	}

	public String getIndicadorActivoPremios() {
		return indicadorActivoPremios;
	}

	public void setIndicadorActivoPremios(String indicadorActivoPremios) {
		this.indicadorActivoPremios = indicadorActivoPremios;
	}

	public boolean isConsultar() {
		return consultar;
	}

	public void setConsultar(boolean consultar) {
		this.consultar = consultar;
	}

	public boolean isIndExigenciaPedidoWeb() {
		return indExigenciaPedidoWeb;
	}

	public void setIndExigenciaPedidoWeb(boolean indExigenciaPedidoWeb) {
		this.indExigenciaPedidoWeb = indExigenciaPedidoWeb;
	}

	public boolean isIndGananciaLiderNueva() {
		return indGananciaLiderNueva;
	}

	public void setIndGananciaLiderNueva(boolean indGananciaLiderNueva) {
		this.indGananciaLiderNueva = indGananciaLiderNueva;
	}

	public List getLetProgramaCorporativoRangoNivelList() {
		return letProgramaCorporativoRangoNivelList;
	}

	public void setLetProgramaCorporativoRangoNivelList(
			List letProgramaCorporativoRangoNivelList) {
		this.letProgramaCorporativoRangoNivelList = letProgramaCorporativoRangoNivelList;
	}

	public List getLetProgramaCorporativoRangoRetencionList() {
		return letProgramaCorporativoRangoRetencionList;
	}

	public void setLetProgramaCorporativoRangoRetencionList(
			List letProgramaCorporativoRangoRetencionList) {
		this.letProgramaCorporativoRangoRetencionList = letProgramaCorporativoRangoRetencionList;
	}

	public List getLetProgramaCorporativoTramosList() {
		return letProgramaCorporativoTramosList;
	}

	public void setLetProgramaCorporativoTramosList(
			List letProgramaCorporativoTramosList) {
		this.letProgramaCorporativoTramosList = letProgramaCorporativoTramosList;
	}

	public List getLetProgramaCorporativoPremiosList() {
		return letProgramaCorporativoPremiosList;
	}

	public void setLetProgramaCorporativoPremiosList(
			List letProgramaCorporativoPremiosList) {
		this.letProgramaCorporativoPremiosList = letProgramaCorporativoPremiosList;
	}

	public DataTableModel getLetProgramaCorporativoRangoNivelDataModel() {
		return letProgramaCorporativoRangoNivelDataModel;
	}

	public void setLetProgramaCorporativoRangoNivelDataModel(
			DataTableModel letProgramaCorporativoRangoNivelDataModel) {
		this.letProgramaCorporativoRangoNivelDataModel = letProgramaCorporativoRangoNivelDataModel;
	}

	public Object getBeanletProgramaCorporativoRangoNivel() {
		return beanletProgramaCorporativoRangoNivel;
	}

	public void setBeanletProgramaCorporativoRangoNivel(
			Object beanletProgramaCorporativoRangoNivel) {
		this.beanletProgramaCorporativoRangoNivel = beanletProgramaCorporativoRangoNivel;
	}

	public DataTableModel getLetProgramaCorporativoTramosDataModel() {
		return letProgramaCorporativoTramosDataModel;
	}

	public void setLetProgramaCorporativoTramosDataModel(
			DataTableModel letProgramaCorporativoTramosDataModel) {
		this.letProgramaCorporativoTramosDataModel = letProgramaCorporativoTramosDataModel;
	}

	public Object getBeanletProgramaCorporativoTramos() {
		return beanletProgramaCorporativoTramos;
	}

	public void setBeanletProgramaCorporativoTramos(
			Object beanletProgramaCorporativoTramos) {
		this.beanletProgramaCorporativoTramos = beanletProgramaCorporativoTramos;
	}

	public DataTableModel getLetProgramaCorporativoRangoRetencionDataModel() {
		return letProgramaCorporativoRangoRetencionDataModel;
	}

	public void setLetProgramaCorporativoRangoRetencionDataModel(
			DataTableModel letProgramaCorporativoRangoRetencionDataModel) {
		this.letProgramaCorporativoRangoRetencionDataModel = letProgramaCorporativoRangoRetencionDataModel;
	}

	public Object getBeanletProgramaCorporativoRangoRetencion() {
		return beanletProgramaCorporativoRangoRetencion;
	}

	public void setBeanletProgramaCorporativoRangoRetencion(
			Object beanletProgramaCorporativoRangoRetencion) {
		this.beanletProgramaCorporativoRangoRetencion = beanletProgramaCorporativoRangoRetencion;
	}

	public DataTableModel getLetProgramaCorporativoPremiosDataModel() {
		return letProgramaCorporativoPremiosDataModel;
	}

	public void setLetProgramaCorporativoPremiosDataModel(
			DataTableModel letProgramaCorporativoPremiosDataModel) {
		this.letProgramaCorporativoPremiosDataModel = letProgramaCorporativoPremiosDataModel;
	}

	public Object getBeanletProgramaCorporativoPremios() {
		return beanletProgramaCorporativoPremios;
	}

	public void setBeanletProgramaCorporativoPremios(
			Object beanletProgramaCorporativoPremios) {
		this.beanletProgramaCorporativoPremios = beanletProgramaCorporativoPremios;
	}

	public String getIndexRangoNivel() {
		return indexRangoNivel;
	}

	public void setIndexRangoNivel(String indexRangoNivel) {
		this.indexRangoNivel = indexRangoNivel;
	}

	public String getEstadoInactivo() {
		return estadoInactivo;
	}

	public void setEstadoInactivo(String estadoInactivo) {
		this.estadoInactivo = estadoInactivo;
	}

	public MantenimientoLETProgramaCorporativoRangoNivelForm getRangoNivelForm() {
		return rangoNivelForm;
	}

	public void setRangoNivelForm(
			MantenimientoLETProgramaCorporativoRangoNivelForm rangoNivelForm) {
		this.rangoNivelForm = rangoNivelForm;
	}

	public boolean isIndicadorPremio() {
		return indicadorPremio;
	}

	public void setIndicadorPremio(boolean indicadorPremio) {
		this.indicadorPremio = indicadorPremio;
	}

	public MantenimientoLETProgramaCorporativoRangoRetencionForm getRangoRetencionForm() {
		return rangoRetencionForm;
	}

	public void setRangoRetencionForm(
			MantenimientoLETProgramaCorporativoRangoRetencionForm rangoRetencionForm) {
		this.rangoRetencionForm = rangoRetencionForm;
	}

	public TabView getTabShow() {
		return tabShow;
	}

	public void setTabShow(TabView tabShow) {
		this.tabShow = tabShow;
	}

	public String getIndicadorHabilitaTramos() {
		return indicadorHabilitaTramos;
	}

	public void setIndicadorHabilitaTramos(String indicadorHabilitaTramos) {
		this.indicadorHabilitaTramos = indicadorHabilitaTramos;
	}

	public boolean isIndRetencion22() {
		return indRetencion22;
	}

	public void setIndRetencion22(boolean indRetencion22) {
		this.indRetencion22 = indRetencion22;
	}

	public boolean isIndRetencion33() {
		return indRetencion33;
	}

	public void setIndRetencion33(boolean indRetencion33) {
		this.indRetencion33 = indRetencion33;
	}

	public boolean isIndRetencion44() {
		return indRetencion44;
	}

	public void setIndRetencion44(boolean indRetencion44) {
		this.indRetencion44 = indRetencion44;
	}

	public MantenimientoLETProgramaCorporativoTramosForm getTramosForm() {
		return tramosForm;
	}

	public void setTramosForm(
			MantenimientoLETProgramaCorporativoTramosForm tramosForm) {
		this.tramosForm = tramosForm;
	}

	public boolean isMostrarCampana() {
		return mostrarCampana;
	}

	public void setMostrarCampana(boolean mostrarCampana) {
		this.mostrarCampana = mostrarCampana;
	}

	public MantenimientoLETProgramaCorporativoPremiosForm getPremiosForm() {
		return premiosForm;
	}

	public void setPremiosForm(
			MantenimientoLETProgramaCorporativoPremiosForm premiosForm) {
		this.premiosForm = premiosForm;
	}

	public List getBusquedaLetProgramaCorporativoPremiosList() {
		return busquedaLetProgramaCorporativoPremiosList;
	}

	public void setBusquedaLetProgramaCorporativoPremiosList(
			List busquedaLetProgramaCorporativoPremiosList) {
		this.busquedaLetProgramaCorporativoPremiosList = busquedaLetProgramaCorporativoPremiosList;
	}	

}
