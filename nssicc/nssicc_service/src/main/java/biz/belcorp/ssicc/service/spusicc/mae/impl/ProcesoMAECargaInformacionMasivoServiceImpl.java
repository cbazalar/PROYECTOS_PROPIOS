package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaInformacionMasivoDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.DireccionTelefonoMasivo;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECargaInformacionMasivoService;

@Service("spusicc.procesoMAECargaInformacionMasivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public  class ProcesoMAECargaInformacionMasivoServiceImpl extends BaseService implements
ProcesoMAECargaInformacionMasivoService {

@Resource(name="spusicc.procesoMAECargaInformacionMasivoDAO")
private ProcesoMAECargaInformacionMasivoDAO procesoMAECargaInformacionMasivoDAO;



	public String validarCliente(Map params){
		
		return procesoMAECargaInformacionMasivoDAO.validarCliente(params);
	}
	
	public String validarDireccion(Map params){
		
		return procesoMAECargaInformacionMasivoDAO.validarDireccion(params);
	}
	
	

   
   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.ProcesoMAECargaInformacionMasivoService#executeProcesarRegistros(java.util.Map)
	 */
	public void executeProcesarRegistros(Map criteria) {
		
		//Si el indicador es 0 - Registra
		//Si el indicador es 1 - Actualiza
		
		List listaRegistrosValidos = (List)criteria.get("listaRegistrosValidos");
		
		int indicadorConstancia = 0;
		int numeroVigenciaPrograma = 0;
		
		//Capturar el Indicador de Constancia y Numero de Vigencia Programa
		String resultado = "";
		int contador = 0;
		
		String codigoPeriodo = MapUtils.getString(criteria, "codigoPeriodo");
		String tipoCarga = MapUtils.getString(criteria, "tipoCarga");
		String usuario = MapUtils.getString(criteria, "usuarioLogin");
		Map param = new HashMap();
		
		for (int i = 0; i < listaRegistrosValidos.size(); i++) {
			DireccionTelefonoMasivo bean = new DireccionTelefonoMasivo();
			bean = (DireccionTelefonoMasivo)listaRegistrosValidos.get(i);
			contador = -1;
			param.put("codigoPeriodo", codigoPeriodo);
			param.put("codigoConsultora", bean.getCodigoConsultora());
			param.put("usuario", usuario);
			
			int res;
			int insert;
			if(tipoCarga.compareTo(Constants.NUMERO_UNO)==0){
				if (StringUtils.isNotBlank(bean.getDireccionDomicilio()) || StringUtils.isNotBlank(bean.getReferenciaDomicilio())) {
					param.put("direccion", bean.getDireccionDomicilio());
					param.put("referenciaDireccion", bean.getReferenciaDomicilio());
					param.put("tipoDireccion", "0"+Constants.NUMERO_UNO);
					res=procesoMAECargaInformacionMasivoDAO.executeActualizarDirecciones(param);
					String verificarRegistro="";
					// verificarRegistro verificar si existe el registro
					
					if(verificarRegistro!=null){
						// actualizar
					}else{
						// insertar
					}					
				}	
					
				if (StringUtils.isNotBlank(bean.getDireccionEntrega()) || StringUtils.isNotBlank(bean.getReferenciaEntrega())) {

					param.put("direccion", bean.getDireccionEntrega());
					param.put("referenciaDireccion", bean.getReferenciaEntrega());
					
					param.put("tipoDireccion", "0"+Constants.NUMERO_SIETE);
					
					procesoMAECargaInformacionMasivoDAO.executeInsertActualizarDirecciones(param);
					res=1;
						
				}
				if (StringUtils.isNotBlank(bean.getDireccionVacaciones()) || StringUtils.isNotBlank(bean.getReferenciaVacaciones())) {
					param.put("direccion", bean.getDireccionVacaciones());
					param.put("referenciaDireccion", bean.getReferenciaVacaciones());
					param.put("tipoDireccion", "0"+Constants.NUMERO_OCHO);
					res=procesoMAECargaInformacionMasivoDAO.executeActualizarDirecciones(param);					
					String verificarRegistro="";
					// verificarRegistro verificar si existe el registro
					if(verificarRegistro!=null){
						// actualizar
					}else{
						// insertar
					}		
				}
			}
			if(tipoCarga.compareTo(Constants.NUMERO_DOS)==0){
				if (StringUtils.isNotBlank(bean.getTelefonoCasa())) {
					param.put("tipo", Constants.MAE_TIPO_COMUNICACION_TELEFONO_CASA);
					param.put("valor", bean.getTelefonoCasa());
					param.put("diaComunicacion","L");					
					res=procesoMAECargaInformacionMasivoDAO.executeActualizarTelefonos(param);		
					String verificarRegistro="";
					// verificarRegistro verificar si existe el registro
					if(res==0){
						insert=procesoMAECargaInformacionMasivoDAO.insertClienteComunicacion(param);
						// actualizar
					}else{
						// insertar
					}
				}
				if ( StringUtils.isNotBlank(bean.getTelefonoCelular())) {
					param.put("tipo", Constants.MAE_TIPO_COMUNICACION_TELEFONO_MOVIL);
					param.put("valor", bean.getTelefonoCelular());
					param.put("diaComunicacion","L");	
					res=procesoMAECargaInformacionMasivoDAO.executeActualizarTelefonos(param);	
					String verificarRegistro="";
					// verificarRegistro verificar si existe el registro
					if(res==0){
						insert=procesoMAECargaInformacionMasivoDAO.insertClienteComunicacion(param);
						// actualizar
					}else{
						// insertar
					}
				}
				if (StringUtils.isNotBlank(bean.getTelefonoTrabajo())) {
					param.put("tipo", Constants.MAE_TIPO_COMUNICACION_TELEFONO_TRABAJO);
					param.put("valor", bean.getTelefonoTrabajo());
					param.put("diaComunicacion","");	
					res=procesoMAECargaInformacionMasivoDAO.executeActualizarTelefonos(param);	
					String verificarRegistro="";
					// verificarRegistro verificar si existe el registro
					if(res==0){
						insert=procesoMAECargaInformacionMasivoDAO.insertClienteComunicacion(param);
						// actualizar
					}else{
						// insertar
					}	
				}
				if (StringUtils.isNotBlank(bean.getEmail())) {
					param.put("tipo", Constants.MAE_TIPO_COMUNICACION_MAIL);
					param.put("valor", bean.getEmail());
					param.put("diaComunicacion","");	
					res=procesoMAECargaInformacionMasivoDAO.executeActualizarTelefonos(param);	
					String verificarRegistro="";
					// verificarRegistro verificar si existe el registro
					if(res==0){
						insert=procesoMAECargaInformacionMasivoDAO.insertClienteComunicacion(param);
						// actualizar
					}else{
						// insertar
					}
				}
			}
		
				}
			}
		
	
}
