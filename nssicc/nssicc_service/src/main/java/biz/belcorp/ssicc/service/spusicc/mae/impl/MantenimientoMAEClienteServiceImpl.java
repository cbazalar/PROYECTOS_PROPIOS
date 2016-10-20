package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacionOperadora;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteConcursoPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteEncuesta;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteMarca;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteObservacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePreferenciaComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePrimerContacto;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteReferencias;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteTipoLogro;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteVinculo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionFlete;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionSobreFlete;
import biz.belcorp.ssicc.dao.spusicc.mae.model.HistoricoClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.SegmentoGrupoLove;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;

/**
 * Service que executa las metodos de Ingreso de Clientes
 *  
 * <p>
 * <a href="MantenimientoMAEClienteServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */ 
@Service("spusicc.mantenimientoMAEClienteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoMAEClienteServiceImpl extends BaseService implements MantenimientoMAEClienteService {
	
	@Resource(name="spusicc.mantenimientoMAEClienteDAO")	
	MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO;
	
	public LabelValue[] getPeriodosVigentesByPaisMarcaCanal(Map criteria) {
		LabelValue[] result = null;

		List listaPeriodos = mantenimientoMAEClienteDAO.getPeriodosVigentesByPaisMarcaCanal(criteria);

		try {
			if (listaPeriodos != null && listaPeriodos.size() > 0) {

				result = new LabelValue[listaPeriodos.size()];

				for (int i = 0; i < listaPeriodos.size(); i++) {
					Base periodo = (Base) listaPeriodos.get(i);
					// Construimos la descripcion

					LabelValue lv = new LabelValue(periodo.getDescripcion(), periodo.getCodigo());
					result[i] = lv;
				}
			} else {
				// Creamos una primera opción vacía
				result = new LabelValue[1];
				result[0] = new LabelValue("", "");
			}
		} catch (DataAccessException ignore) {
			log.warn(ignore.getMessage());
		}
		
		return result;
	}
	
	public List getSubTiposClienteInsertar(Map criteria) {
		return mantenimientoMAEClienteDAO.getSubTiposClienteInsertar(criteria);
	}

	public String getTipoClasificacionDefault(String codigoTipoCliente, String codigoSubTipoCliente) {
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente", codigoTipoCliente);
		criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
		
		return mantenimientoMAEClienteDAO.getTipoClasificacionDefault(criteria);
	}
	
	public String getClasificacionDefault(String codigoTipoCliente, String codigoSubTipoCliente) {
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente", codigoTipoCliente);
		criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
		
		return mantenimientoMAEClienteDAO.getClasificacionDefault(criteria);
	}	
	
    public String getOidPais(Map criteria) {
        return mantenimientoMAEClienteDAO.getOidPais(criteria);
    }	

    public String getLongitudCodigoCliente(Map criteria) {
        return mantenimientoMAEClienteDAO.getLongitudCodigoCliente(criteria);
    }		

    public String getLongitudCodigoClien(Map criteria) {
        return mantenimientoMAEClienteDAO.getLongitudCodigoClien(criteria);
    }		

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getLongitudNumeroDocIdentidad(java.util.Map)
     */
    public String getLongitudNumeroDocIdentidad(Map criteria) {
        return mantenimientoMAEClienteDAO.getLongitudNumeroDocIdentidad(criteria);
    }     
    
	public List getEstadosCiviles(Map criteria){
		return mantenimientoMAEClienteDAO.getEstadosCiviles(criteria);
	}

	public List getTratamientos(Map criteria){
		return mantenimientoMAEClienteDAO.getTratamientos(criteria);	
	}
	
	public List getNivelEstudios(Map criteria) {
		return mantenimientoMAEClienteDAO.getNivelEstudios(criteria);	
	}
	
	public List getNacionalidades(Map criteria) {
		return mantenimientoMAEClienteDAO.getNacionalidades(criteria);	
	}
	
	public List getTiposDocumentoIdentidad(Map criteria){
		return mantenimientoMAEClienteDAO.getTiposDocumentoIdentidad(criteria);	
	}

	public String getTipoDocumentoObligatorio(String oidPais) {
        return mantenimientoMAEClienteDAO.getTipoDocumentoObligatorio(oidPais);
    }

	public String getTipoDocumentoDuplaCyzone(String oidPais) {
        return mantenimientoMAEClienteDAO.getTipoDocumentoDuplaCyzone(oidPais);
    }
	
	public List getSexos(Map criteria) {
		return mantenimientoMAEClienteDAO.getSexos(criteria);	
	}	

	public List getTiposVias(Map criteria) {
		return mantenimientoMAEClienteDAO.getTiposVias(criteria);	
	}	
	
	public List getNivelesGeograficos(Map criteria) {
		return mantenimientoMAEClienteDAO.getNivelesGeograficos(criteria);	
	}	

	public List getTiposClasificaciones(Map criteria) {
		return mantenimientoMAEClienteDAO.getTiposClasificaciones(criteria);	
	}	
	
	/*
	 * Validamos el tipo y numero de documento de identidad ingresado por el usuario
	 * 
	 * Si existe, verificamos si tienes cuentas castigadas en CCC
	 * Si no existe, verificamos si el pais esta en Modulo 10, para realizar la verificacion del numero de documento de identidad
	 * 															en base al algoritmo del modulo 10 
	 * 
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#validarDocumentoIdentidad(java.util.Map)
	 */
	public String validarDocumentoIdentidad(Map criteria) {
		String respuesta = null;
		List documentos = mantenimientoMAEClienteDAO.getDocumentosCliente(criteria);
		
		/* INI SA PER-SiCC-2012-0265 */
		String svalidarconDocumentos = (String)criteria.get("validarSinDocumentos");
		boolean validarconDocumentos = true;
		if(svalidarconDocumentos != null)
			validarconDocumentos = false;
		/* FIN SA PER-SiCC-2012-0265 */
		
		//se encontro un cliente para el tipo y numero de documento ingresado
		if(validarconDocumentos && documentos.size() > 0) {
			Base base = (Base)documentos.get(0);
			String oidCliente = base.getCodigo();
			String codigoCliente = base.getDescripcion();
			String valor = " ";
			
			String saldo = consultarCuentasCastigadasCliente((String)criteria.get("oidPais"), oidCliente);
			if(Double.parseDouble(saldo) > 0) {
				valor = saldo;
			}
			
			respuesta = valor + "-" + oidCliente + "-" + codigoCliente;
		} else {
			String codigoPais = (String)criteria.get("codigoPais");
			String numeroDocumento = (String)criteria.get("numeroDocumentoIdentidad");
			/* INI SA PER-SiCC-2012-0265 */
			String tipoDocumento = (String)criteria.get("tipoDocumentoIdentidad");
			String tipoDocumento2 = (String)criteria.get("tipoDocumentoIdentidad2");
			String numeroDocumento2 = (String)criteria.get("numeroDocumentoIdentidad2");
			/* FIN SA PER-SiCC-2012-0265 */
			
			//si el WEBSERVICE envia esto con valor, no se valida los documentos de identidad
			String noValidarDocumentoWS = (String)criteria.get("noValidarDocumentoWS");
			if(noValidarDocumentoWS != null) return null;

			String valorModulo = mantenimientoMAEClienteDAO.getValorModuloxPaisTipoValidacion(codigoPais, Constants.MAE_VALID_DOCIDENT);
			log.debug("Valor Modulo Identidad : " + valorModulo);
			
			//INI PER-SiCC-2015-0314
			if(StringUtils.equals(codigoPais.trim(), Constants.PAIS_BOE) ){
				
				if(log.isDebugEnabled()){
					log.debug("Pais validacion : " + Constants.PAIS_BOE);
				}
								
				criteria.put("tipoDocumento", Constants.MAE_TIPO_DOCUMENTO_CI);
				
				if(tipoDocumento!=null && tipoDocumento.trim().length()>0){
					
					if(log.isDebugEnabled()){
						log.debug("tipoDocumento : " + tipoDocumento);
					}
					
					if(StringUtils.equals(tipoDocumento.trim(), Constants.MAE_TIPO_DOCUMENTO_CI)){					
						String oidTipoDocumentoCI = mantenimientoMAEClienteDAO.getOidTipoDocumento1(criteria);
						
						if(log.isDebugEnabled()){
							log.debug("oidTipoDocumentoCI : " + oidTipoDocumentoCI);
						}
						
						if(oidTipoDocumentoCI!=null){
							
							if(log.isDebugEnabled()){
								log.debug("numeroDocumento : " + numeroDocumento);
							}
							
							if(numeroDocumento!=null && numeroDocumento.trim().length()>0){
								respuesta= mantenimientoMAEClienteDAO.validarNumeroCarnetIdentidad(numeroDocumento);
							}						
						}
					}
				}
				if(tipoDocumento2!=null && tipoDocumento2.trim().length()>0){
					if(StringUtils.equals(tipoDocumento2.trim(), Constants.MAE_TIPO_DOCUMENTO_CI)){					
						String oidTipoDocumentoCI = mantenimientoMAEClienteDAO.getOidTipoDocumento1(criteria);
						
						if(oidTipoDocumentoCI!=null){
							if(numeroDocumento2!=null && numeroDocumento2.trim().length()>0){
								
								if(log.isDebugEnabled()){
									log.debug("numeroDocumento2 : " + numeroDocumento2);
								}
								
								respuesta= mantenimientoMAEClienteDAO.validarNumeroCarnetIdentidad(numeroDocumento2);
							}						
						}
					}
				}
				
			}
		
			//FIN PER-SiCC-2015-0314
			
			if(valorModulo != null) {
				
				/* INI SA PER-SiCC-2012-0265 */
				criteria.put("tipoDocumento", Constants.MAE_TIPO_DOCUMENTO_RUT);
				String oidTipoDocumentoRUT = mantenimientoMAEClienteDAO.getOidTipoDocumento(criteria);
				/* FIN SA PER-SiCC-2012-0265 */
				
				if(valorModulo.equals(Constants.MAE_MODULO_10)) {
					boolean esTipoDocumentoModulo10 = mantenimientoMAEClienteDAO.esTipoDocumentoModulo10(criteria);
					
					if (esTipoDocumentoModulo10) {
						boolean validaOK = validarDocumentoModulo10(numeroDocumento);
						
						if (!validaOK)
							respuesta ="Modulo10";
					}	
				}
				
				/* INI SA PER-SiCC-2012-0265 */
				if(valorModulo.equals(Constants.MAE_MODULO_11V) && tipoDocumento.equals(oidTipoDocumentoRUT)) {
					boolean validaOK = mantenimientoMAEClienteDAO.validarNumeroDocumentoMod11V(numeroDocumento);
						
					if (!validaOK)
						respuesta ="Modulo11V";
				}
				
				if(tipoDocumento2 != null && !tipoDocumento2.equals("")) {
					if(valorModulo.equals(Constants.MAE_MODULO_11V) && tipoDocumento2.equals(oidTipoDocumentoRUT)) {
						boolean validaOK = mantenimientoMAEClienteDAO.validarNumeroDocumentoMod11V(numeroDocumento2);
							
						if (!validaOK)
							respuesta ="Modulo11V";
					}	
				}
				/* FIN SA PER-SiCC-2012-0265 */
			}
			
		}
		
		return respuesta;
	}
	
	/*
	 * Permite la consulta de las cuentas castigadas de un determinado cliente 
	 */
	private String consultarCuentasCastigadasCliente(String oidPais, String oidCliente) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("procesoCCC", Constants.CCC_PROCESO_CONSULTA_DEUDA_APROVISIONADA);
		criteria.put("subprocesoCCC", Constants.CCC_SUBPROCESO_CONSULTA_DEUDA_APROVISIONADA);
		
		List marcasTipoEntrada = mantenimientoMAEClienteDAO.getMarcasTipoAbonoEntrada(criteria);

		criteria.put("oidCliente", oidCliente);
		int numfilas = marcasTipoEntrada.size();
		
		if(numfilas > 0) {
			List listMarcasEntradas = new ArrayList();
			
			for(int i=0; i < numfilas; i++){
				Base baseEntrada = (Base)marcasTipoEntrada.get(i);
				listMarcasEntradas.add(baseEntrada.getCodigo());
			}
			
			criteria.put("listMarcasEntradas", listMarcasEntradas);
		}
		

		String total = mantenimientoMAEClienteDAO.getCuentasCastigadasCliente(criteria);
		return total;
	}
	
	private boolean validarDocumentoModulo10(String numeroDocumento) {
		boolean validaOK = false;
		int ultimoCaracter = Integer.parseInt(numeroDocumento.substring(numeroDocumento.length()-1));
		
		log.debug("numero documento :" + numeroDocumento);
		log.debug("ultimo Caracter :" + ultimoCaracter);
		int digitoVerificador = 0;
		
		try {
			digitoVerificador = Integer.parseInt(mantenimientoMAEClienteDAO.getValidarNumeroDocumentoMod10(numeroDocumento));
		} catch(Exception ex) {
			digitoVerificador = -1;
		}	
		
		log.debug("digitoVerificador :" + digitoVerificador);
		
		if(ultimoCaracter == digitoVerificador)
			validaOK = true;
		
		return validaOK;
	}
	
	public boolean isCodigoClienteAutomatico(Map criteria) {
		String generacionAutomatica = mantenimientoMAEClienteDAO.getCodigoClienteAutomatico(criteria);
		boolean esAutomatico = false;
		
		log.debug("Forma de Generación del Codigo de Cliente (M/A): " + generacionAutomatica);
		if(generacionAutomatica.equalsIgnoreCase(Constants.MAE_CODIGO_CLIENTE_GENERACION_AUTOMATICA))
			esAutomatico = true;
		
		return esAutomatico;
	}
	
	public Base getNuevoCodigoCliente(Map criteria) throws Exception {
		Base baseCodigoCliente = new Base();
        String digitoControl = null;
        String codigoCliente = null;

        String codigoPais = (String)criteria.get("codigoPais");
        String numCaracteres = (String)criteria.get("longitudCodigoCliente");
        String valorModulo = mantenimientoMAEClienteDAO.getValorModuloxPaisTipoValidacion(codigoPais, Constants.MAE_VALID_CODCLIE);
        
        if(valorModulo == null)
        	throw new Exception("No Se encontro Modulo de Generación de Codigo de Cliente para el pais");
        
        if(valorModulo.equals(Constants.MAE_MODULO_11)) {
            while (digitoControl == null) {
                codigoCliente = getUltimoNumeroSolicitud(criteria);
                log.debug("En el bucle, codigo de cliente: " + codigoCliente);
                
                digitoControl = generarDigitoControlModulo11(codigoCliente);
                log.debug("En el bucle, digito de control: " + digitoControl);
            }

            log.debug("este es el digito de control: " + digitoControl);

            StringBuffer cadenaCodigoCliente = new StringBuffer("" + codigoCliente);

            //Le agregamos ceros a la izquierda hasta completar el numCaracteres exigido 
            int cantCeros = Integer.parseInt(numCaracteres) - cadenaCodigoCliente.length();
            for (int i = 0; i < (cantCeros - 1); i++) {
                cadenaCodigoCliente.insert(0, "0");
            }

            //Se agrega el digito de control al final del codigo completado con ceros
            log.debug("codigo de cliente sin digito: " + cadenaCodigoCliente);
            cadenaCodigoCliente.append(digitoControl);
            log.debug("codigo de cliente con digito: " + cadenaCodigoCliente);
            
            codigoCliente = cadenaCodigoCliente.toString();
            
        } else {
    		codigoCliente = getUltimoNumeroSolicitud(criteria);
            StringBuffer cadenaCodigoCliente = new StringBuffer("" + codigoCliente);

            //Le agregamos ceros a la izquierda hasta completar el numCaracteres exigido 
            int cantCeros = Integer.parseInt(numCaracteres) - cadenaCodigoCliente.length();
            for (int i = 0; i < (cantCeros); i++) {
                cadenaCodigoCliente.insert(0, "0");
            }
            codigoCliente = cadenaCodigoCliente.toString();
            
        	if (valorModulo.equals(Constants.MAE_MODULO_00)) {
        		digitoControl = "";
        	}
        	else if (valorModulo.equals(Constants.MAE_MODULO_02)) { 
        		digitoControl = generarDigitoControlModulo02(StringUtils.right(codigoCliente,7));
        	}
        	else if (valorModulo.equals(Constants.MAE_MODULO_03)) {
        		digitoControl = generarDigitoControlModulo03(StringUtils.right(codigoCliente,7));
        	}
        	else if (valorModulo.equals(Constants.MAE_MODULO_PR)) {
        		digitoControl = mantenimientoMAEClienteDAO.getDigitoControlModuloPTR(codigoCliente);
        	}
        	else {
        		throw new Exception("No Existe Algoritmo de generacion de Codigo de Cliente para Modulo: " + valorModulo);
        	}
        }
        
        baseCodigoCliente.setCodigo(codigoCliente);	//almacena el codigo de cliente
        baseCodigoCliente.setDescripcion(digitoControl);	//almacena el digito de control
        
		return baseCodigoCliente;
	}

	private String getUltimoNumeroSolicitud(Map criteria) {
		/* INI SA PER-SiCC-2012-0535 */
		String codigoPais = (String)criteria.get("codigoPais");
		String ultimoNumero = mantenimientoMAEClienteDAO.getSecuenciaCodigoCliente(codigoPais);
		/* FIN SA PER-SiCC-2012-0535 */
		
		return ultimoNumero;
	}
	
    private String generarDigitoControlModulo11(String codigoCliente) throws Exception {
        HashMap hmPesos = new HashMap();

        List pesos = mantenimientoMAEClienteDAO.getPesosModulo11();
        Iterator it = pesos.iterator();

        while (it.hasNext()) {
        	Base basePeso = (Base)it.next();
        	
            hmPesos.put(new Long(basePeso.getCodigo()), new Long(basePeso.getDescripcion()));
        }
        
        if (hmPesos.size() != Constants.MAE_CLIENTE_NRO_POSICIONES_CON_PESO) {
            throw new Exception("Numero de Posiciones Incorrecto en BD");
        }        
        
        //Ya tengo los pesos de las posiciones, debo convertir el nro.
        long tamanio = codigoCliente.length();

        StringBuffer valorFinal = new StringBuffer(codigoCliente);

        if (tamanio < Constants.MAE_CLIENTE_NRO_POSICIONES_CON_PESO) {
            for (int i = 0; i < (Constants.MAE_CLIENTE_NRO_POSICIONES_CON_PESO - tamanio); i++) {
                valorFinal.insert(0, "0");
            }
        }

        long suma = 0;
        long digitoActual;
        long pesoActual;
        long resto;
        char[] digitos = valorFinal.toString().toCharArray();
        long digitoControl;
        String resultado;

        for (int i = 1; i < (Constants.MAE_CLIENTE_NRO_POSICIONES_CON_PESO + 1); i++) {
            digitoActual = Long.parseLong(String.valueOf((digitos[i - 1])));
            pesoActual = ((Long) hmPesos.get(new Long(i))).longValue();

            suma = suma + (digitoActual * pesoActual);
        }

        resto = suma % Constants.MAE_CLIENTE_NRO_BASE;
        log.debug("RESTO: " + resto); 
        
        if (resto == 0) {
            digitoControl = 0;
            resultado = "0";
        } else {
            if (resto == 1) {
                resultado = null;
            } else {
                digitoControl = Constants.MAE_CLIENTE_NRO_BASE - resto;
                resultado = "" + digitoControl;
            }
        }
        
        return resultado;
    }

    public List validarZonaTerritorio(Map criteria) {
    	List erroresValidacion = new ArrayList();
    	String hiperconsulta = (String)criteria.get("hiperconsulta");
    	
    	//Validamos si la Zona Destino se encuentra Inactiva
		if(hiperconsulta != null) {
			boolean esZonaInActiva = mantenimientoMAEClienteDAO.esZonaInactiva(criteria);
			if(esZonaInActiva){
				erroresValidacion.add("mantenimientoMAEClienteForm.msg.ZonaInactivaNoPermiteCambio");
			}
		}
		
    	//Validamos la zona ingresada
		String zona = mantenimientoMAEClienteDAO.getExisteZona(criteria);
		if (zona == null) {
			erroresValidacion.add("mantenimientoMAEClienteForm.msg.ZonaNoExiste");
		}
		
    	//Validamos el territorio ingresado
		String territorio = mantenimientoMAEClienteDAO.getExisteTerritorio(criteria);
		if (territorio == null) {
			erroresValidacion.add("mantenimientoMAEClienteForm.msg.TerritorioNoExiste");
		} else {
			//Validamos si el territorio pertenece a la zona ingresada
			criteria.put("oidZona", zona);
			criteria.put("oidTerritorio", territorio);
			String territorioAdministrativo = mantenimientoMAEClienteDAO.getExisteTerritorioEnZona(criteria);

			if (territorioAdministrativo == null) 
				erroresValidacion.add("mantenimientoMAEClienteForm.msg.TerritorioNoExisteEnZona");
			else {
				criteria.put("oidTerritorioAdministrativo", territorioAdministrativo);
				
				//obtenemos la unidad geografica relacionado al territorio
				Map mapUbigeo = mantenimientoMAEClienteDAO.getCodigoUbigeo(territorio);
				
				erroresValidacion.add("ok");
				erroresValidacion.add(mapUbigeo);
			}	
		}
			
    	return erroresValidacion;
    }    
    
    public List validarDatosCliente(Map criteria) {
    	List erroresValidacion = new ArrayList();
    	
    	String mostrarUnidadAdministrativa = (String)criteria.get("mostrarUnidadAdministrativa");
    	String esDuplaCyzone = (String)criteria.get("esDuplaCyzone");
    	String codigoUbigeo = (String)criteria.get("codigoUbigeo");
    	String confirmacionTerritorio = (String)criteria.get("confirmacionTerritorio");
    	String codigoZonaInicial = (String)criteria.get("codigoZonaInicial");
    	String codigoZona = (String)criteria.get("codigoZona");
    	String codigoEstatus = (String)criteria.get("codigoEstatus");
    	String hiperconsulta = (String)criteria.get("hiperconsulta");
		
    	if("true".equals(mostrarUnidadAdministrativa)) {
			
			//Validamos si la Zona Destino se encuentra Inactiva
    		if(hiperconsulta != null) {
				boolean esZonaInActiva = mantenimientoMAEClienteDAO.esZonaInactiva(criteria);
				if(esZonaInActiva){
					erroresValidacion.add("mantenimientoMAEClienteForm.msg.ZonaInactivaNoPermiteCambio");
				}
    		}	
			
	    	//Validamos la zona ingresada
			String zona = mantenimientoMAEClienteDAO.getExisteZona(criteria);
			if (zona == null) {
				erroresValidacion.add("mantenimientoMAEClienteForm.msg.ZonaNoExiste");
			}
			else {
		    	//Validamos si la zona corresponde a uno tipo oficina, verificar que tenga el
				//el subtipo: Consultora/Oficina
				boolean esZonaOficina = mantenimientoMAEClienteDAO.esZonaOficina(criteria);
				List listSubTipo = (List)criteria.get("listSubTipo");
				
				if (esZonaOficina) {
					if(!existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA, 
								Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA, listSubTipo))
						erroresValidacion.add("mantenimientoMAEClienteForm.msg.zonaOficinaNoCorresponde");
				} else {
					if(existeSubTipoCliente(Constants.MAE_TIPO_CLIENTE_CONSULTORA, 
							Constants.MAE_SUBTIPO_CLIENTE_CONSULTORA_OFICINA, listSubTipo))
					erroresValidacion.add("mantenimientoMAEClienteForm.msg.zonaOficinaNoCorresponde2");
					
				}
			}
			
	    	//Validamos el territorio ingresado
			String territorio = mantenimientoMAEClienteDAO.getExisteTerritorio(criteria);
			if (territorio == null) {
				erroresValidacion.add("mantenimientoMAEClienteForm.msg.TerritorioNoExiste");
			} else {
				//Validamos si el territorio pertenece a la zona ingresada
				criteria.put("oidZona", zona);
				criteria.put("oidTerritorio", territorio);
				String territorioAdministrativo = mantenimientoMAEClienteDAO.getExisteTerritorioEnZona(criteria);

				if (territorioAdministrativo == null) 
					erroresValidacion.add("mantenimientoMAEClienteForm.msg.TerritorioNoExisteEnZona");
				else {
					criteria.put("oidTerritorioAdministrativo", territorioAdministrativo);
					
					if(!"ok".equals(confirmacionTerritorio)) {
						//obtenemos la unidad geografica relacionado al territorio
						Map mapUbigeo = mantenimientoMAEClienteDAO.getCodigoUbigeo(territorio);
						
						String codigoUbigeoTerritorio = (String)mapUbigeo.get("ubigeo1");
						if ((String)mapUbigeo.get("ubigeo2") != null)
							codigoUbigeoTerritorio = codigoUbigeoTerritorio + (String)mapUbigeo.get("ubigeo2");
						if ((String)mapUbigeo.get("ubigeo3") != null)
							codigoUbigeoTerritorio = codigoUbigeoTerritorio + (String)mapUbigeo.get("ubigeo3");
						
						//Verificamos que el ubigeo del territorio este dentro del ubigeo del distrito de la direccion principal
						if(!codigoUbigeo.substring(0,18).equals(codigoUbigeoTerritorio)) {
							erroresValidacion.add("mantenimientoMAEClienteForm.msg.TerritorioNoCorrespondeDistrito");
						}
					}	
				}	
					
			}
		}
		
		//validamos si el codigoCliente existe en BD
		String codigoCliente = (String)criteria.get("codigoCliente");
		
		if(codigoCliente!=null && !codigoCliente.equals("")) {
			String codCliente = mantenimientoMAEClienteDAO.getExisteCodigoCliente(criteria);
			if (codCliente != null) {
				erroresValidacion.add("mantenimientoMAEClienteForm.msg.CodigoClienteExiste");
			}
		} 
		
		/* INI JJ PER-SiCC-2012-0329 */
		String codigoCUB = (String)criteria.get("codigoCUB");
		String codigoCUBAnterior = (String)criteria.get("codigoCUBAnterior");
		if((codigoCUBAnterior != null && !codigoCUBAnterior.equals(codigoCUB)) || (codigoCUBAnterior == null && codigoCUB != null && StringUtils.isNotBlank(codigoCUB))){
			String codCUB = mantenimientoMAEClienteDAO.getExisteCodigoCUB(criteria);
			if (codCUB != null) {
				erroresValidacion.add("mantenimientoMAEClienteForm.msg.CodigoCUBExiste");
			}
		}
		
		/* FIN JJ PER-SiCC-2012-0329 */
		//Validamos que la Madre Dupla tenga tipo Cliente: Consultora
		if("true".equals(esDuplaCyzone)) {
			List listClienteSubTipo = mantenimientoMAEClienteDAO.getClienteSubTipo(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
												criteria.get("oidConsultoraVinculo").toString());
			
			if(listClienteSubTipo.size() == 0) {
				erroresValidacion.add("mantenimientoMAEClienteForm.msg.ClienteVinculoNoConsultora");
			}
		}		
		
    	return erroresValidacion;
    }    

	public String getNumeroDocumentoPrincipal(String oidCliente) {
		return mantenimientoMAEClienteDAO.getNumeroDocumentoPrincipal(oidCliente);
	}
    
	public Long getOidMarca(String codigoMarca) {
		return mantenimientoMAEClienteDAO.getOidMarca(codigoMarca);
	}

	public Long getOidCanal(String codigoCanal) {
		return mantenimientoMAEClienteDAO.getOidCanal(codigoCanal);
	}

	public Long getOidFormaPagoPais(String oidPais) {
		return mantenimientoMAEClienteDAO.getOidFormaPagoPais(oidPais);	
	}
	
	public Long getOidFormaPagoSubTipoCliente(String oidPais, String oidSubTipoCliente) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		
		return mantenimientoMAEClienteDAO.getOidFormaPagoSubTipoCliente(criteria);
	}
	
	public String getValorConfiCampoSubTipoCliente(String oidPais, String oidSubTipoCliente, String oidCampoConfiguracion) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("oidCampoConfiguracion", oidCampoConfiguracion);
		
		return mantenimientoMAEClienteDAO.getValorConfiCampoSubTipoCliente(criteria);
	}
	
	public Long getOidTipoDireccion(String codigoTipoDireccion) {
		return mantenimientoMAEClienteDAO.getOidTipoDireccion(codigoTipoDireccion);
	}
	
	public Long getOidTipoComunicacion(String codigoTipoComunicacion) {
		return mantenimientoMAEClienteDAO.getOidTipoComunicacion(codigoTipoComunicacion);
	}
	
	public Long getOidTipoVinculo(String oidPais, String codigoTipoVinculo) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("codigoTipoVinculo", codigoTipoVinculo);
		
		return mantenimientoMAEClienteDAO.getOidTipoVinculo(criteria);
	}	
	
	public String getCriterioBusqueda1(String oidPais) {
		return mantenimientoMAEClienteDAO.getCriterioBusqueda1(oidPais);
	}

	public String getCriterioBusqueda2(String oidPais) {
		return mantenimientoMAEClienteDAO.getCriterioBusqueda2(oidPais);
	}    
    
    public void insertCliente(Cliente cliente) throws Exception {
    	//Obtenemos OID de la Secuencia para la tabla de Clientes
    	Long oidCliente = mantenimientoMAEClienteDAO.getSecuenciaNextValueCliente();
    	Long oidClienteHistoricoStatus = null;
    	Date now = new Date(System.currentTimeMillis());
    	boolean esDuplaCyzone = false;
    	
    	//insertamos datos del Cliente
    	cliente.setOid(oidCliente);
    	cliente.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    	mantenimientoMAEClienteDAO.insertCliente(cliente);
    	
    	//insertamos datos adicionales del cliente
    	if(cliente.getClienteAdicional() != null) {
    		ClienteAdicional clienteAdicional = cliente.getClienteAdicional();
    		clienteAdicional.setOidCliente(oidCliente);
    		clienteAdicional.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    		
    		String oidNivelRiesgo = null;
    		//si tiene unidad administrativa, se recupera el nivel de riesgo asociado
    		if(cliente.getClienteUnidadAdministrativa() != null) {
    			Map mapUnidad = new HashMap();
    			mapUnidad.put("oidTerritorioAdministrativo", 
    							cliente.getClienteUnidadAdministrativa().getOidTerritorioAdministrativo());
    			
    			oidNivelRiesgo = mantenimientoMAEClienteDAO.getNivelRiesgo(mapUnidad);
    			clienteAdicional.setOidNivelRiesgo(new Long(oidNivelRiesgo));
    		}
    		
    		mantenimientoMAEClienteDAO.insertClienteAdicional(cliente.getClienteAdicional());
    	}

    	//insertamos historico status del cliente
    	if(cliente.getClienteHistoricoEstatus() != null) {
    		ClienteHistoricoEstatus clienteHistoricoEstatus = cliente.getClienteHistoricoEstatus();
    		clienteHistoricoEstatus.setOidCliente(oidCliente);
    		
    		oidClienteHistoricoStatus = mantenimientoMAEClienteDAO.getSecuenciaNextValueHistoricoStatus();
    		
    		clienteHistoricoEstatus.setOid(oidClienteHistoricoStatus);
    		clienteHistoricoEstatus.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    		
    		mantenimientoMAEClienteDAO.insertClienteHistoricoEstatus(clienteHistoricoEstatus);
    	}
    	
    	//insertamos datos de los subtipos del cliente
    	if(cliente.getListClienteSubTipo() != null &&  cliente.getListClienteSubTipo().size()>0) {
    		List listClienteSubTipo = cliente.getListClienteSubTipo();
    		
    		Iterator it = listClienteSubTipo.iterator();
    		while(it.hasNext()) {
    			ClienteSubTipo clienteSubTipo = (ClienteSubTipo)it.next();
    			clienteSubTipo.setOidCliente(oidCliente);
    			clienteSubTipo.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    			
        		//Obtenemos OID de la Secuencia para la tabla de Cliente SubTipo
            	Long oidClienteSubTipo = mantenimientoMAEClienteDAO.getSecuenciaNextValueSubTipo();
            	clienteSubTipo.setOid(oidClienteSubTipo);
            	
        		mantenimientoMAEClienteDAO.insertClienteSubTipo(clienteSubTipo);
        		
        		if((clienteSubTipo.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)) &&
        		   (clienteSubTipo.getCodigoSubTipoCliente().equals(Constants.MAE_SUBTIPO_HIJADUPLA_HIJADUPLA))) {
        			esDuplaCyzone = true;
        		}
        		
        		//insertamos datos de las clasificaciones del cliente, relacionados a un determinado subtipo
        		if(clienteSubTipo.getListClienteClasificacion() != null &&  clienteSubTipo.getListClienteClasificacion().size()>0) {
            		List listClienteClasificacion = clienteSubTipo.getListClienteClasificacion();
            		
            		Iterator it2 = listClienteClasificacion.iterator();
            		while(it2.hasNext()) {
            			ClienteClasificacion clienteClasificacion = (ClienteClasificacion)it2.next();
            			clienteClasificacion.setOidClienteSubTipo(oidClienteSubTipo);
            			clienteClasificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
            			
                		//Obtenemos OID de la Secuencia para la tabla de Cliente Clasificacion
                    	Long oidClienteClasificacion = mantenimientoMAEClienteDAO.getSecuenciaNextValueClasificacion();
                    	clienteClasificacion.setOid(oidClienteClasificacion);
                    	
                		mantenimientoMAEClienteDAO.insertClienteClasificacion(clienteClasificacion);
                		
                		//insertamos datos en historico clasificacion
                		if(oidClienteHistoricoStatus != null) {
	                		HistoricoClasificacion historicoClasificacion = new HistoricoClasificacion();
	                		historicoClasificacion.setOidClienteHistoricoEstatus(oidClienteHistoricoStatus);
	                		historicoClasificacion.setOidClasificacion(clienteClasificacion.getOidClasificacion());
	                		historicoClasificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
	                		//mantenimientoMAEClienteDAO.insertHistoricoClasificacion(historicoClasificacion);
                		}
            		}		
        		}
        		
            	//insertamos datos primer contacto del cliente, solo para el subtipo cliente principal
            	if(cliente.getClientePrimerContacto() != null && (clienteSubTipo.getIndicadorPrincipal().intValue()==1)) {
            		ClientePrimerContacto clientePrimerContacto = cliente.getClientePrimerContacto();
            		clientePrimerContacto.setOidCliente(oidCliente);
            		clientePrimerContacto.setOidClienteSubTipo(oidClienteSubTipo);
            		clientePrimerContacto.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
            		mantenimientoMAEClienteDAO.insertClientePrimerContacto(clientePrimerContacto);
            	}
        		
    		}
    	}

    	//insertamos datos de identificacion del cliente
    	if(cliente.getListClienteIdentificacion() != null &&  cliente.getListClienteIdentificacion().size()>0) {
    		List listClienteIdentificacion = cliente.getListClienteIdentificacion();
    		
    		Iterator itIden = listClienteIdentificacion.iterator();
    		while(itIden.hasNext()) {
    			ClienteIdentificacion clienteIdentificacion = (ClienteIdentificacion)itIden.next();
        		clienteIdentificacion.setOidCliente(oidCliente);
        		clienteIdentificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
        		
        		if(esDuplaCyzone) {
        			//lo actualizamos con el codigo de cliente
        			clienteIdentificacion.setNumeroDocumento(cliente.getCodigo());
        		}
        		
        		mantenimientoMAEClienteDAO.insertClienteIdentificacion(clienteIdentificacion);
    		}
    	}

    	//insertamos las direcciones del cliente
    	if(cliente.getListClienteDireccion() != null &&  cliente.getListClienteDireccion().size()>0) {
    		List listClienteDireccion = cliente.getListClienteDireccion();
    		
    		/* INI SA PER-SiCC-2013-0265 */
    		Long oidTipoDireccionVac = getOidTipoDireccion(Constants.MAE_TIPO_DIRECCION_VACACIONES);
    		boolean ingresoDireccionVacaciones = false;
    		
    		Iterator it = listClienteDireccion.iterator();
    		while(it.hasNext()) {
    			ClienteDireccion clienteDireccion = (ClienteDireccion)it.next();
    			clienteDireccion.setOidCliente(oidCliente);
    			clienteDireccion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    			mantenimientoMAEClienteDAO.insertClienteDireccion(clienteDireccion);
        		
        		if(oidTipoDireccionVac.longValue() == clienteDireccion.getOidTipoDireccion().longValue())
        			ingresoDireccionVacaciones = true;
    		}
    		
    		if(ingresoDireccionVacaciones) {
	    		/* INI SA PER-SiCC-2012-0365 */
	    		Map params = new HashMap();
	    		params.put("codigoPais", cliente.getCodigoPais());
	    		params.put("oidCliente", cliente.getOid().toString());
	    		params.put("codigoUsuario", cliente.getCodigoUsuario());
	    		mantenimientoMAEClienteDAO.executeActualizacionClasificacionVacaciones(params);
	    		/* FIN SA PER-SiCC-2012-0365 */
    		}	
    		/* FIN SA PER-SiCC-2013-0265 */
    	}
    	
    	//insertamos la unidad administrativa del cliente
    	if(cliente.getClienteUnidadAdministrativa() != null) {
    		ClienteUnidadAdministrativa clienteUnidadAdministrativa = cliente.getClienteUnidadAdministrativa();
    		clienteUnidadAdministrativa.setOidCliente(oidCliente);
    		clienteUnidadAdministrativa.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    		
    		mantenimientoMAEClienteDAO.insertClienteUnidadAdministrativa(clienteUnidadAdministrativa);
    	}
    
    	//insertamos los tipos de comunicaciones con el cliente (telefono/celular/mail)
    	if(cliente.getListClienteComunicacion() != null &&  cliente.getListClienteComunicacion().size()>0) {
    		List listClienteComunicacion = cliente.getListClienteComunicacion();
    		
    		Iterator it = listClienteComunicacion.iterator();
    		while(it.hasNext()) {
    			ClienteComunicacion clienteComunicacion = (ClienteComunicacion)it.next();
    			clienteComunicacion.setOidCliente(oidCliente);
    			clienteComunicacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    			
        		mantenimientoMAEClienteDAO.insertClienteComunicacion(clienteComunicacion);
    		}
    	}

    	//insertamos los vinculos del cliente
    	if(cliente.getListClienteVinculo() != null &&  cliente.getListClienteVinculo().size()>0) {
    		List listClienteVinculo = cliente.getListClienteVinculo();
    		
    		Iterator it = listClienteVinculo.iterator();
    		while(it.hasNext()) {
    			ClienteVinculo clienteVinculo = (ClienteVinculo)it.next();
    			clienteVinculo.setOidCliente(oidCliente);
   
    			if(clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_DUPLACYZONE)) {
    				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    				String fechaActual = sdf.format(new Date(System.currentTimeMillis()));    				
    				
    				String oidClienteVinculo = mantenimientoMAEClienteDAO.getClienteVinculoDuplaCyzone(Constants.MAE_TIPO_VINCULO_DUPLACYZONE,
    										clienteVinculo.getOidClienteVinculante().toString(), fechaActual);
    				
    				// Validar si existe un vinculo vigente con el cliente Vinculante
    				if(oidClienteVinculo != null) {
    					//Actualizamos el Vinculo Actual, en si el campo Fecha Hasta = fecha Sistema-1
    					mantenimientoMAEClienteDAO.updateFechaHastaClienteVinculo(oidClienteVinculo);
    					
    					//Desactivamos a la hija Dupla Anterior relacionada a la Madre Dupla a Vincular
    					mantenimientoMAEClienteDAO.updateIndicadorActivoClienteVinculo(oidClienteVinculo);
    				}
    				else {
    					 //Insertamos Nueva Clasificacion Dupla Cyzone
    					List listClienteSubTipo = mantenimientoMAEClienteDAO.getClienteSubTipo(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
    												clienteVinculo.getOidClienteVinculante().toString());
    					
    					if(listClienteSubTipo.size() > 0) {
    						Base base = (Base)listClienteSubTipo.get(0);
    						String oidClienteSubTipo = base.getCodigo();
    						String oidSubTipoCliente = base.getDescripcion();
    						
    						String oidTipoClasificacion = mantenimientoMAEClienteDAO.getOidTipoClasificacion(oidSubTipoCliente, 
    														Constants.MAE_TIPO_CLASIFICACION_DUPLACYZONE);

    						String oidClasificacion = mantenimientoMAEClienteDAO.getOidClasificacion(oidTipoClasificacion, 
															Constants.MAE_CLASIFICACION_DUPLACYZONE);
    						
    						String oidClienteClasificacion = mantenimientoMAEClienteDAO.getOidClienteClasificacion(
    														oidClienteSubTipo, oidTipoClasificacion);
    						
    						//Se valida que no existe una clasificacion de DuplaCyzone para el cliente Vinculante
    						if(oidClienteClasificacion == null){
    							ClienteClasificacion clienteClasificacion = new ClienteClasificacion();
    							clienteClasificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    							
    	                		//Obtenemos OID de la Secuencia para la tabla de Cliente Clasificacion
    	                    	Long oidClienteClasificacionAux = mantenimientoMAEClienteDAO.getSecuenciaNextValueClasificacion();
    	                    	clienteClasificacion.setOid(oidClienteClasificacionAux);
    							
    							clienteClasificacion.setOidClienteSubTipo(new Long(oidClienteSubTipo));
    							clienteClasificacion.setOidTipoClasificacion(new Long(oidTipoClasificacion));
    							clienteClasificacion.setOidClasificacion(new Long(oidClasificacion));
    							clienteClasificacion.setIndicadorPrincipal(new Integer(0));
    							try {
    								clienteClasificacion.setFechaClasificacion(sdf.parse(fechaActual));
    							} catch(Exception ex) {}
    							clienteClasificacion.setOidPeriodo(cliente.getOidPeriodoIngreso()); 
    							mantenimientoMAEClienteDAO.insertClienteClasificacion(clienteClasificacion);
    						}
    					}
    				}

    				//Borramos los mensajes de Bienvenida y Rechazo DuplaCyzone para la Madre Dupla
    				Map eliminacion = new HashMap();
    				eliminacion.put("oidCliente",clienteVinculo.getOidClienteVinculante());
    				eliminacion.put("codMsgBienvenidaDuplaCyzone", Constants.MAE_COD_MENSAJE_BIENVENIDA_DUPLACYZONE);
    				eliminacion.put("codMsgRechazoDuplaCyzone", Constants.MAE_COD_MENSAJE_RECHAZO_DUPLACYZONE);
    				mantenimientoMAEClienteDAO.deleteMensajesDuplaCyzone(eliminacion);
    				
    				//Insertamos Mensaje de Bienvenida Dupla para la Madre Dupla
    		    	Map criteria = new HashMap();
    		    	criteria.put("codigoMensaje", Constants.MAE_COD_MENSAJE_BIENVENIDA_DUPLACYZONE);
    		    	criteria.put("oidCliente", clienteVinculo.getOidClienteVinculante());
    		    	criteria.put("oidModuloOrigen", Constants.MAE_CLIENTE_MODULO_MAE);
    		   	
    		    	mantenimientoMAEClienteDAO.insertBuzonMensajes(criteria);    				
    			}
    			
    			//creamos el vinculo    			
    			clienteVinculo.setFechaActualizacion(now);
    			clienteVinculo.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    			
        		mantenimientoMAEClienteDAO.insertClienteVinculo(clienteVinculo);
    		}
    	}
    	
    	//insertamos la marca del cliente
    	if(cliente.getClienteMarca() != null) {
    		ClienteMarca clienteMarca = cliente.getClienteMarca();
    		clienteMarca.setOidCliente(oidCliente);
    		
    		mantenimientoMAEClienteDAO.insertClienteMarca(clienteMarca);
    	}

    	//insertamos las observaciones del cliente
    	if(cliente.getListClienteObservacion() != null &&  cliente.getListClienteObservacion().size()>0) {
    		List listClienteObservacion = cliente.getListClienteObservacion();
    		
    		Iterator it = listClienteObservacion.iterator();
    		while(it.hasNext()) {
    			ClienteObservacion clienteObservacion = (ClienteObservacion)it.next();
    			clienteObservacion.setOidCliente(oidCliente);
        		
        		mantenimientoMAEClienteDAO.insertClienteObservacion(clienteObservacion);
    		}
    	}

    	//insertamos los concursos/premios del cliente
    	if(cliente.getListClienteConcursoPremio() != null &&  cliente.getListClienteConcursoPremio().size()>0) {
    		List listClienteConcursoPremio = cliente.getListClienteConcursoPremio();
    		
    		Iterator itConcursoPremio = listClienteConcursoPremio.iterator();
    		while(itConcursoPremio.hasNext()) {
    			ClienteConcursoPremio concursoPremio = (ClienteConcursoPremio)itConcursoPremio.next();
    			
    			Map criteria = new HashMap();
    			criteria.put("oidConcurso", concursoPremio.getOidConcurso().toString());
    			criteria.put("codigoClienteRecomendante", concursoPremio.getCodigoClienteRecomendante());
    			
    			String oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
    			
    			if(oidRecomendante == null) {
    				mantenimientoMAEClienteDAO.insertRecomendante(concursoPremio);
    				oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
    			}
    			concursoPremio.setOidClienteRecomendante(new Long(oidRecomendante));
    			
    			concursoPremio.setCodigoClienteRecomendado(cliente.getCodigo());
        		mantenimientoMAEClienteDAO.insertRecomendado(concursoPremio);
    		}
    	}
    	//insertamos las referencias del cliente
    	ClienteReferencias clienteReferencias =cliente.getClienteReferencias();
    	clienteReferencias.setCodigoCliente(cliente.getCodigo());
    	clienteReferencias.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
    	
    	insertClienteReferencias(clienteReferencias);
    
    	//insertamos las consultoras avaladas
		insertClienteAvales(cliente);
    			
    	//insertamos en buzon de mensajes, mensaje de Bienvenida a la Nueva Consultora
    	String nombreCompleto = cliente.getNombre1() + " " + cliente.getNombre2() + " " + cliente.getApellido1() + " " + cliente.getApellido2();    	
    	Map criteria = new HashMap();
    	criteria.put("codigoMensaje", Constants.MAE_COD_MENSAJE_BIENVENIDA);
    	criteria.put("oidCliente", oidCliente);
    	criteria.put("oidModuloOrigen", Constants.MAE_CLIENTE_MODULO_MAE);
    	criteria.put("datoVariable02", nombreCompleto);
   	
    	mantenimientoMAEClienteDAO.insertBuzonMensajes(criteria);
    	
    }
    
	public void insertClienteReferencias(ClienteReferencias clienteReferencias) {
		if(StringUtils.isNotEmpty(clienteReferencias.getCodigoTipoVinculoRefFamiliar()))
			mantenimientoMAEClienteDAO.insertClienteReferenciaFamiliar(clienteReferencias);
		
		if(StringUtils.isNotEmpty(clienteReferencias.getCodigoTipoVinculoRefNoFamiliar()))
			mantenimientoMAEClienteDAO.insertClienteReferenciaNoFamiliar(clienteReferencias);
		
		if(StringUtils.isNotEmpty(clienteReferencias.getCodigoTipoVinculoAval())){
			setUbigeoAval(clienteReferencias);
			mantenimientoMAEClienteDAO.insertClienteReferenciaAval(clienteReferencias);
		}
	}

	public void setMantenimientoMAEClienteDAO(MantenimientoMAEClienteDAO mantenimientoMAEClienteDAO) {
		this.mantenimientoMAEClienteDAO = mantenimientoMAEClienteDAO;
	}

	public List getClientesByCriteria(Map criteria){			
		return mantenimientoMAEClienteDAO.getClientesByCriteria(criteria);
	}	
	
	public List getDireccionesClientesByCriteria(Map criteria){			
		return mantenimientoMAEClienteDAO.getDireccionesClientesByCriteria(criteria);
	}
	
	public String getEdadMinima(Map criteria){
		return mantenimientoMAEClienteDAO.getEdadMinima(criteria);
	}
	
	public String getEdadMaxima(Map criteria){
		return mantenimientoMAEClienteDAO.getEdadMaxima(criteria);
	}
	
	public String getExisteMensajeBuzon(Map criteria){
		return mantenimientoMAEClienteDAO.getExisteMensajeBuzon(criteria);
	}
	
	public void insertBuzonMensajes(Map criteria){
		mantenimientoMAEClienteDAO.insertBuzonMensajes(criteria);
	}
	
	public List getConcursos(Map criteria){
		return mantenimientoMAEClienteDAO.getConcursos(criteria);
	}
	
	public List getPremios(Map criteria){
		return mantenimientoMAEClienteDAO.getPremios(criteria);
	}
	
	public void insertRecomendante(Map criteria){
		mantenimientoMAEClienteDAO.insertRecomendante(null);
	}
	
	public String getOidRecomendante(Map criteria){
		return mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
	}
	
	public void insertRecomendado(Map criteria){
		mantenimientoMAEClienteDAO.insertRecomendado(null);
	}
	
	public String getOidRecomendado(Map criteria){
		return mantenimientoMAEClienteDAO.getOidRecomendado(criteria);
	}

	public Long getOidClienteTipoSubTipoMigracion(String oidPais) {
		return mantenimientoMAEClienteDAO.getOidClienteTipoSubTipoMigracion(oidPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getTiposDireccion(java.util.Map)
	 */
	public List getTiposDireccion(Map criteria) {
		return mantenimientoMAEClienteDAO.getTiposDireccion(criteria);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getNivelesRiesgo(Map criteria){
		return mantenimientoMAEClienteDAO.getNivelesRiesgo(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Map getNivelRiesgoCliente(Map criteria) {
		return mantenimientoMAEClienteDAO.getNivelRiesgoCliente(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public String getFechaUltimaNivelRiesgoCliente(Map criteria) {
		String fechaUltima = mantenimientoMAEClienteDAO.getFechaUltimaCreacionNivelRiesgoCliente(criteria);
		
		if(fechaUltima == null) {
			fechaUltima = mantenimientoMAEClienteDAO.getFechaUltimaActualizacionClienteAdicional(criteria);
		}
		
		return fechaUltima;
	}	

	/**
	 * @param criteria
	 */
	public void insertNivelRiesgoCliente(Map criteria) {
		mantenimientoMAEClienteDAO.insertClienteNivelRiesgo(criteria);
		
		mantenimientoMAEClienteDAO.updateNivelRiesgoClienteAdicional(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getZonasByPaisMarcaCanal(java.util.Map)
	 */
	public List getZonasByPaisMarcaCanal(Map criteria) {
		return mantenimientoMAEClienteDAO.getZonasByPaisMarcaCanal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getTerritoriosByPaisMarcaCanalZona(java.util.Map)
	 */
	public List getTerritoriosByPaisMarcaCanalZona(Map criteria) {
		return mantenimientoMAEClienteDAO.getTerritoriosByPaisMarcaCanalZona(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListClientesByCriteria(java.util.Map)
	 */
	public List getListClientesByCriteria(Map criteria) {
		return mantenimientoMAEClienteDAO.getListClientesByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getDatosBasicosCliente(java.lang.String, java.lang.String)
	 */
	public Cliente getDatosBasicosCliente(String codigoPais,
			String codigoCliente) {
		
		Cliente cliente = mantenimientoMAEClienteDAO.getDatosBasicosCliente(codigoPais, codigoCliente);
		cliente.setOidSubTipoAval(mantenimientoMAEClienteDAO.getClientePotencialAval(codigoCliente));
		
		return cliente;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getDatosAdicionalesCliente(java.lang.String)
	 */
	public ClienteAdicional getDatosAdicionalesCliente(String oidCliente) {
		ClienteAdicional clienteAdicional = mantenimientoMAEClienteDAO.getDatosAdicionalesCliente(oidCliente);
		clienteAdicional.setIndImprDocuAnt(clienteAdicional.getIndImprDocu());
		clienteAdicional.setUsuCargAnt(clienteAdicional.getUsuCarg());
		return clienteAdicional;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getPrimerContactoCliente(java.lang.String)
	 */
	public ClientePrimerContacto getPrimerContactoCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getPrimerContactoCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListClasificacionCliente(java.lang.String)
	 */
	public List getListClasificacionCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListClasificacionCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListIdentificacionCliente(java.lang.String)
	 */
	public List getListIdentificacionCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListIdentificacionCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListTipoSubtipoCliente(java.lang.String)
	 */
	public List getListTipoSubtipoCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListTipoSubtipoCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getComunicacionCliente(java.lang.String)
	 */
	public List getListComunicacionCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListComunicacionCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListDireccionCliente(java.lang.String)
	 */
	public List getListDireccionCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListDireccionCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListObservacionCliente(java.lang.String)
	 */
	public List getListObservacionCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListObservacionCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListVinculoCliente(java.lang.String)
	 */
	public List getListVinculoCliente(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListVinculoCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getUnidadAdministrativaCliente(java.lang.String)
	 */
	public ClienteUnidadAdministrativa getUnidadAdministrativaCliente(
			String oidCliente) {
		return mantenimientoMAEClienteDAO.getUnidadAdministrativaCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateCliente(biz.belcorp.ssicc.spusicc.mae.model.Cliente)
	 */
	public void updateCliente(Cliente cliente) throws Exception{
		//actualizamos datos basicos del cliente
		log.debug("oid client " + cliente.getOid() + " Cod Cliente " + cliente.getCodigo());
		updateDatosBasicosCliente(cliente);
		
		//actualizando  datos adiconales
		ClienteAdicional clienteAdicional = cliente.getClienteAdicional();
		clienteAdicional.setCodigoUsuario(cliente.getCodigoUsuario());
		updateDatosAdicionalesCliente(clienteAdicional);
		
		//actualizamos la identificacion del cliente
		updateIdentificacionCliente(cliente);
		//actulizamos tipo subtipos y/o clasificaciones
		updateSubTipoCliente(cliente, null);
		//actualizamos direccion
		updateDireccionCliente(cliente);
		//actulaizamos las comunicaciones
		updateComunicacionesCliente(cliente);
		
		//actualizamos unidad administrativa
		updateUnidadAdministrativaCliente(cliente);
		
		//actualizamos los vinculos del cliente
		updateVinculosCliente(cliente);
		//actualizampos las observaciones
		updateObservacionesCliente(cliente);
		//actualizamos referencias del cliente
		updateReferenciasCliente(cliente);
		
		//actualizamos concursos y premios del cliente
		updateConcursoPremiosCliente(cliente);
		
    	//insertamos las consultoras avaladas
		insertClienteAvales(cliente);
		
		//insertamos los cambios realizados
		if(cliente.getClienteHistoricoDatos() != null) {
			updateHistoricoDatosCliente(cliente.getClienteHistoricoDatos());
		}	
	}

	/**
	 * insertamos los concursos/premios del cliente
	 * 
	 * @param cliente
	 */
	private void updateConcursoPremiosCliente(Cliente cliente) {
    	if(cliente.getListClienteConcursoPremio() != null &&  cliente.getListClienteConcursoPremio().size()>0) {
    		List listClienteConcursoPremio = cliente.getListClienteConcursoPremio();
    		
    		Iterator itConcursoPremio = listClienteConcursoPremio.iterator();
    		while(itConcursoPremio.hasNext()) {
    			ClienteConcursoPremio concursoPremio = (ClienteConcursoPremio)itConcursoPremio.next();
    			
    			Map criteria = new HashMap();
    			criteria.put("oidConcurso", concursoPremio.getOidConcurso().toString());
    			criteria.put("codigoClienteRecomendante", concursoPremio.getCodigoClienteRecomendante());
    			
    			String oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
    			
    			if(oidRecomendante == null) {
    				mantenimientoMAEClienteDAO.insertRecomendante(concursoPremio);
    				oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
    			}
    			concursoPremio.setOidClienteRecomendante(new Long(oidRecomendante));
    			
    			//recuperamos el oid Cliente Recomendando de Incentivos			
    			criteria.put("oidClienteRecomendante", oidRecomendante);
    			criteria.put("codigoClienteRecomendado", concursoPremio.getCodigoClienteRecomendado());
    			String oidRecomendado = mantenimientoMAEClienteDAO.getOidRecomendado(criteria);
    			
    			if(oidRecomendado == null)
    				mantenimientoMAEClienteDAO.insertRecomendado(concursoPremio);
        		
        		//si no es registrada ni retirada, se inserta sus registros correspondientes en tablas de incentivos
        		ClienteAdicional clienteAdicional = cliente.getClienteAdicional();
        		if(clienteAdicional.getCodigoStatus()!=null) {
        			if(!((clienteAdicional.getCodigoStatus().equals(Constants.MAE_ESTADO_REGISTRADA)) ||
       					(clienteAdicional.getCodigoStatus().equals(Constants.MAE_ESTADO_RETIRADA)))) {
        				
        				Map criteriaAux = new HashMap();
        				criteriaAux.put("oidPais", cliente.getOidPais().toString());
        				criteriaAux.put("codClienteRcdo", concursoPremio.getCodigoClienteRecomendado());
        				criteriaAux.put("codClienteRcte", concursoPremio.getCodigoClienteRecomendante());
        				criteriaAux.put("oidConcurso", concursoPremio.getOidConcurso().toString());
        			
        				mantenimientoMAEClienteDAO.executeRehacerTablasIncentivos(criteriaAux);
        			}
        		}	
    		}
    	}
	}
	
	/**
	 * Actualiza las referencias del cliente
	 * @param cliente
	 */
	private void updateReferenciasCliente(Cliente cliente) {
		ClienteReferencias clienteReferencias =  cliente.getClienteReferencias();
		mantenimientoMAEClienteDAO.deleteReferencias(clienteReferencias);
		clienteReferencias.setCodigoUsuario(cliente.getCodigoUsuario());
		clienteReferencias.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
		
		insertClienteReferencias(clienteReferencias);
//		//actualizamos rferencia familiar
//		mantenimientoMAEClienteDAO.updateReferenciasFamiliar(clienteReferencias);
//		//actualizamos referencia no familiar
//		mantenimientoMAEClienteDAO.updateReferenciasNoFamiliar(clienteReferencias);
//		//actualizamos refrencia aval		
//		//obteniendo los ubigeos del aval
//		setUbigeoAval(clienteReferencias);
//		mantenimientoMAEClienteDAO.updateReferenciasAval(clienteReferencias);
	}

	/**
	 * @param clienteReferencias
	 */
	private void setUbigeoAval(ClienteReferencias clienteReferencias) {
		String codigo =null;
		if(StringUtils.isNotEmpty(clienteReferencias.getCodigoDistritoAval())){
			codigo =clienteReferencias.getCodigoDistritoAval();
			//clienteReferencias.setEstado(codigo.substring(0,6));//dpto
			//clienteReferencias.setMunicipio(codigo.substring(6,12));//provinci
			clienteReferencias.setParroquia(codigo.substring(12,18));//distrito
		}		
		if(StringUtils.isNotEmpty(clienteReferencias.getCodigoProvinciaAval())){
			codigo =clienteReferencias.getCodigoProvinciaAval();
			//clienteReferencias.setEstado(codigo.substring(0,6));//dpto
			clienteReferencias.setMunicipio(codigo.substring(6,12));//provinci			
		}		
		if(StringUtils.isNotEmpty(clienteReferencias.getCodigoDepartamentoAval())){
			codigo =clienteReferencias.getCodigoDepartamentoAval();
			clienteReferencias.setEstado(codigo.substring(0,6));//dpto			
		}
	}

	private void updateObservacionesCliente(Cliente cliente) {
		List listClienteObservacion = cliente.getListClienteObservacion();
		Iterator it = listClienteObservacion.iterator();
		while(it.hasNext()) {
			ClienteObservacion clienteObservacion = (ClienteObservacion)it.next();
			clienteObservacion.setCodigoUsuario(cliente.getCodigoUsuario());
    		updateObservacionCliente(clienteObservacion);
		}		
	}

	private void updateVinculosCliente(Cliente cliente) {
		List lisSubtipo = cliente.getListClienteSubTipo();
		Iterator it = lisSubtipo.iterator();
		
		boolean isConsultora=true;
		boolean isHijaDupla=false;
		while(it.hasNext()) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo)it.next();
			if(clienteSubTipo.getCodigoTipoCliente().equals(Constants.MAE_TIPO_CLIENTE_HIJADUPLA)){
				  isHijaDupla= true;
				  isConsultora=false;
				  break;
			}
		}
		
	 if(isConsultora){	
		List listClienteVinculo = cliente.getListClienteVinculo();
		it = listClienteVinculo.iterator();
		while(it.hasNext()) {
			ClienteVinculo clienteVinculo = (ClienteVinculo)it.next();
			clienteVinculo.setCodigoUsuario(cliente.getCodigoUsuario());
			
			Date now = new Date(System.currentTimeMillis());
			
			//INI MEX-SiCC-2015-0005
			//5.	Si la cliente tiene estatus Retirada y Activo y no existe información en el campo Cod Consultora 
			//que Recomienda, no debe realizar acción alguna, si encuentra lleno el código mantener la funcionalidad 
			//que actualmente realiza el sistema.
			if(cliente.getClienteAdicional().getCodigoStatus().equals(Constants.MAE_ESTADO_RETIRADA)){
				if(cliente.getClienteAdicional().getIndicadorActivo().intValue() == 1) {
					if(clienteVinculo.getCodigoClienteVinculante()!=null && clienteVinculo.getCodigoClienteVinculante().trim().length()>0){
						
						if(clienteVinculo.getOid()==null){
							clienteVinculo.setFechaActualizacion(now);
							clienteVinculo.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
							
				    		mantenimientoMAEClienteDAO.insertClienteVinculo(clienteVinculo);
						} else {
							clienteVinculo.setFechaActualizacion(now);
							updateVinculoCliente(clienteVinculo);
						}	
					}
				}
			}else{
				if(clienteVinculo.getCodigoClienteVinculante()!=null && clienteVinculo.getCodigoClienteVinculante().trim().length()>0){
					if(clienteVinculo.getOid()==null){
						clienteVinculo.setFechaActualizacion(now);
						clienteVinculo.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
						
			    		mantenimientoMAEClienteDAO.insertClienteVinculo(clienteVinculo);
					} else {
						clienteVinculo.setFechaActualizacion(now);
						updateVinculoCliente(clienteVinculo);
					}	
				}
			}
			//FIN MEX-SiCC-2015-0005
			
			
			//verificamos si tenemos que eliminar los concursos y premios del recomendante anterior
	    	if((clienteVinculo.getOidClienteVinculanteAnterior()!=null) && 
			   ((clienteVinculo.getOidClienteVinculante().longValue() != clienteVinculo.getOidClienteVinculanteAnterior().longValue()) ||
				 (cliente.getClienteAdicional().getCodigoStatus().equals(Constants.MAE_ESTADO_RETIRADA))) ) {
	    		
	    		Map mapCriteriaConcurso = new HashMap();
	    		mapCriteriaConcurso.put("oidClienteRecomendado", cliente.getOid().toString());
	    		mapCriteriaConcurso.put("oidClienteRecomendante", clienteVinculo.getOidClienteVinculanteAnterior().toString());
	    		
	    		//recuperamos los Oids Recomendantes anteriormente almacenados para el recomendante anterior
	    		List lstOidsRecomendante = mantenimientoMAEClienteDAO.getOidsRecomendante(mapCriteriaConcurso);
	    		
	    		//eliminamos los Oids Recomendados anteriormente almacenados para el recomendante anterior
	    		mantenimientoMAEClienteDAO.executeDeleteRecomendados(mapCriteriaConcurso);
	    		
	    		//verificamos si no existe relacion de los Oids Recomendantes con Oids Recomendados en BD,
	    		//si es el caso, se elimina el Oid Recomendante
	    		Iterator itOidsRecomendante = lstOidsRecomendante.iterator();
	    		while(itOidsRecomendante.hasNext()){
	    			String oidRecomendante = (String)itOidsRecomendante.next();
	    			String totalRecomendados = mantenimientoMAEClienteDAO.getTotalRecomendados(oidRecomendante);
	    			
	    			if(totalRecomendados.equals("0")) {
	    				mantenimientoMAEClienteDAO.executeDeleteRecomendante(oidRecomendante);
	    			}
	    		}
	    		
	    	}
		}
	 }
	 
	 if(isHijaDupla){	
 		List listClienteVinculo = cliente.getListClienteVinculo();		
 		it = listClienteVinculo.iterator();
		while(it.hasNext()) {
			ClienteVinculo clienteVinculo = (ClienteVinculo)it.next();
			
			if(clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_LIDER_RECOMENDADA)) {
				clienteVinculo.setCodigoUsuario(cliente.getCodigoUsuario());
				
				Date now = new Date(System.currentTimeMillis());
				
				if(clienteVinculo.getOid()==null){
					clienteVinculo.setFechaActualizacion(now);
					clienteVinculo.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
		    		mantenimientoMAEClienteDAO.insertClienteVinculo(clienteVinculo);
				} else {
					clienteVinculo.setFechaActualizacion(now);
					updateVinculoCliente(clienteVinculo);
				}
				
				continue;
			}
			
			if(clienteVinculo.getOid()==null){

			if(clienteVinculo.getCodigoTipoVinculo().equals(Constants.MAE_TIPO_VINCULO_DUPLACYZONE)) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String fechaActual = sdf.format(new Date(System.currentTimeMillis()));    								
				String oidClienteVinculo = mantenimientoMAEClienteDAO.getClienteVinculoDuplaCyzone(Constants.MAE_TIPO_VINCULO_DUPLACYZONE,
										clienteVinculo.getOidClienteVinculante().toString(), fechaActual);				
				// Validar si existe un vinculo vigente con el cliente Vinculante
				if(oidClienteVinculo != null) {
					//Actualizamos el Vinculo Actual, en si el campo Fecha Hasta = fecha Sistema-1
					mantenimientoMAEClienteDAO.updateFechaHastaClienteVinculo(oidClienteVinculo);
				}
				else {
					 //Insertamos Nueva Clasificacion Dupla Cyzone
					List listClienteSubTipo = mantenimientoMAEClienteDAO.getClienteSubTipo(Constants.MAE_TIPO_CLIENTE_CONSULTORA,
												clienteVinculo.getOidClienteVinculante().toString());					
					if(listClienteSubTipo.size() > 0) {
						Base base = (Base)listClienteSubTipo.get(0);
						String oidClienteSubTipo = base.getCodigo();
						String oidSubTipoCliente = base.getDescripcion();
						
						String oidTipoClasificacion = mantenimientoMAEClienteDAO.getOidTipoClasificacion(oidSubTipoCliente, 
														Constants.MAE_TIPO_CLASIFICACION_DUPLACYZONE);

						String oidClasificacion = mantenimientoMAEClienteDAO.getOidClasificacion(oidTipoClasificacion, 
														Constants.MAE_CLASIFICACION_DUPLACYZONE);
						
						String oidClienteClasificacion = mantenimientoMAEClienteDAO.getOidClienteClasificacion(
														oidClienteSubTipo, oidTipoClasificacion);
						
						//Se valida que no existe una clasificacion de DuplaCyzone para el cliente Vinculante
						if(oidClienteClasificacion == null){
							ClienteClasificacion clienteClasificacion = new ClienteClasificacion();
							
	                		//Obtenemos OID de la Secuencia para la tabla de Cliente Clasificacion
	                    	Long oidClienteClasificacionAux = mantenimientoMAEClienteDAO.getSecuenciaNextValueClasificacion();
	                    	clienteClasificacion.setOid(oidClienteClasificacionAux);
							
							clienteClasificacion.setOidClienteSubTipo(new Long(oidClienteSubTipo));
							clienteClasificacion.setOidTipoClasificacion(new Long(oidTipoClasificacion));
							clienteClasificacion.setOidClasificacion(new Long(oidClasificacion));
							clienteClasificacion.setIndicadorPrincipal(new Integer(0));
							try {
								clienteClasificacion.setFechaClasificacion(sdf.parse(fechaActual));
							} catch(Exception ex) {}
							clienteClasificacion.setOidPeriodo(cliente.getOidPeriodoIngreso()); 
							clienteClasificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
							
							mantenimientoMAEClienteDAO.insertClienteClasificacion(clienteClasificacion);
						}
					}
				}

				//Borramos los mensajes de Bienvenida y Rechazo DuplaCyzone para la Madre Dupla
				Map eliminacion = new HashMap();
				eliminacion.put("oidCliente",clienteVinculo.getOidClienteVinculante());
				eliminacion.put("codMsgBienvenidaDuplaCyzone", Constants.MAE_COD_MENSAJE_BIENVENIDA_DUPLACYZONE);
				eliminacion.put("codMsgRechazoDuplaCyzone", Constants.MAE_COD_MENSAJE_RECHAZO_DUPLACYZONE);
				mantenimientoMAEClienteDAO.deleteMensajesDuplaCyzone(eliminacion);
				
				//Insertamos Mensaje de Bienvenida Dupla para la Madre Dupla
		    	Map criteria = new HashMap();
		    	criteria.put("codigoMensaje", Constants.MAE_COD_MENSAJE_BIENVENIDA_DUPLACYZONE);
		    	criteria.put("oidCliente", clienteVinculo.getOidClienteVinculante());
		    	criteria.put("oidModuloOrigen", Constants.MAE_CLIENTE_MODULO_MAE);
		   	
		    	mantenimientoMAEClienteDAO.insertBuzonMensajes(criteria);    				
			}

			//creamos el vinculo    		
			Date now = new Date(System.currentTimeMillis());
			clienteVinculo.setFechaActualizacion(now);
			clienteVinculo.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
			
    		mantenimientoMAEClienteDAO.insertClienteVinculo(clienteVinculo);
		 }//FIN DEL IF OID=NULL
		}//FIN DEL WHILE  
		 
	 }
	 
	}



	private void updateComunicacionesCliente(Cliente cliente) {
		List listClienteComunicacion = cliente.getListClienteComunicacion();		
		Iterator it = listClienteComunicacion.iterator();
		while(it.hasNext()) {
			ClienteComunicacion clienteComunicacion = (ClienteComunicacion)it.next();
			clienteComunicacion.setCodigoUsuario(cliente.getCodigoUsuario());
			clienteComunicacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
			
			if(clienteComunicacion.isEliminar()){
			  deleteComunicacionCliente(clienteComunicacion);	
			}else{
				if(clienteComunicacion.getOid()==null)
					mantenimientoMAEClienteDAO.insertClienteComunicacion(clienteComunicacion);
				else	
					updateComunicacionCliente(clienteComunicacion);
			}
		}
	}

	/**
	 * @param clienteComunicacion
	 */
	private void deleteComunicacionCliente(
			ClienteComunicacion clienteComunicacion) {
		mantenimientoMAEClienteDAO.deleteComunicacionCliente(clienteComunicacion);
		
	}

	/**
	 * @param cliente
	 */
	private void updateDireccionCliente(Cliente cliente) {
		List listClienteDireccion = cliente.getListClienteDireccion();		
		Iterator it = listClienteDireccion.iterator();
		while(it.hasNext()) {
			ClienteDireccion clienteDireccion = (ClienteDireccion)it.next();
			clienteDireccion.setCodigoUsuario(cliente.getCodigoUsuario());
			
			if(clienteDireccion.isEliminar())
				deleteDireccionCliente(clienteDireccion);
			else {
				if(clienteDireccion.getOid() != null )
					updateDireccionCliente(clienteDireccion);
				else {
					clienteDireccion.setOidCliente(cliente.getOid());
					clienteDireccion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
					
	        		mantenimientoMAEClienteDAO.insertClienteDireccion(clienteDireccion);
				}
			}	
			
			/* INI SA PER-SiCC-2012-0365 */
			if((clienteDireccion.isEliminar() && clienteDireccion.getCodigoTipoDireccion().equals(Constants.MAE_TIPO_DIRECCION_VACACIONES)) ||
				((clienteDireccion.getOid()!=null) && clienteDireccion.isTienePeriodosModificados() && 
						clienteDireccion.getCodigoTipoDireccion().equals(Constants.MAE_TIPO_DIRECCION_VACACIONES)) ) {
				Map paramsAux = new HashMap();
				paramsAux.put("codigoPais", cliente.getCodigoPais());
				paramsAux.put("oidCliente", cliente.getOid().toString());
				mantenimientoMAEClienteDAO.executeEliminarClasificacionVacaciones(paramsAux);
			}	
			/* FIN SA PER-SiCC-2012-0365 */
		}
		
		/* INI SA PER-SiCC-2012-0365 */
		Map params = new HashMap();
		params.put("codigoPais", cliente.getCodigoPais());
		params.put("oidCliente", cliente.getOid().toString());
		params.put("codigoUsuario", cliente.getCodigoUsuario());
		mantenimientoMAEClienteDAO.executeActualizacionClasificacionVacaciones(params);
		/* FIN SA PER-SiCC-2012-0365 */
	}

	/**
	 * @param cliente
	 */
	private void updateSubTipoCliente(Cliente cliente, List primerContactoOid ) {
		List listSubTipo = cliente.getListClienteSubTipo();
		Iterator it = listSubTipo.iterator();
		while(it.hasNext()){
			ClienteSubTipo clienteSubTipo	= (ClienteSubTipo)it.next();
			clienteSubTipo.setCodigoUsuario(cliente.getCodigoUsuario());			
			
			if(clienteSubTipo.getOid()!= null){
				setClasificacionesCliente(clienteSubTipo);//elimina o actualiza las clasificciones asociadas al subtipo previo indicador
				if(clienteSubTipo.isEliminar()){					
					if(clienteSubTipo.getIndicadorPrincipal().intValue() == 1) {
						ClientePrimerContacto clientePrimerContacto = cliente.getClientePrimerContacto();
						if(clientePrimerContacto != null) {
							clientePrimerContacto.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
							
							clientePrimerContacto.setOidClienteSubTipo(mantenimientoMAEClienteDAO.getOidClienteTipoSubTipoMigracion(
																		cliente.getOidPais().toString()));
							updatePrimerContactoCliente(clientePrimerContacto);
						}
					}
					
					//se elimina el subtipo
					deletePrimerContactoHijaDupla(clienteSubTipo);
					deleteTipoSubtipoCliente(clienteSubTipo);
					
				}else{ 
					//se actualiza el subtipo
				    updateTipoSubtipoCliente(clienteSubTipo);
				}
			}else{
				//insertamos	
        		//Obtenemos OID de la Secuencia para la tabla de Cliente SubTipo
            	Long oidClienteSubTipo = mantenimientoMAEClienteDAO.getSecuenciaNextValueSubTipo();
            	clienteSubTipo.setOid(oidClienteSubTipo);				
            	clienteSubTipo.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
            	
				mantenimientoMAEClienteDAO.insertClienteSubTipo(clienteSubTipo);
				//insertamos datos de las clasificaciones del cliente, relacionados a un determinado subtipo
				insertClasificacionesCliente(cliente,clienteSubTipo,oidClienteSubTipo);		
			}
			
			//actualizando primer contacto, para el clienteSubTipo que no es ELIMINADO y es PRINCIPAL
			if(!clienteSubTipo.isEliminar() && clienteSubTipo.getIndicadorPrincipal().intValue() == 1) {
				ClientePrimerContacto clientePrimerContacto = cliente.getClientePrimerContacto();
				if(clientePrimerContacto != null) {
					clientePrimerContacto.setCodigoUsuario(clienteSubTipo.getCodigoUsuario());
					clientePrimerContacto.setOidClienteSubTipo(clienteSubTipo.getOid());
					clientePrimerContacto.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
					updatePrimerContactoCliente(clientePrimerContacto);
				}	
				
				if(primerContactoOid!=null){
					for(int i =0;i<primerContactoOid.size();i++){
						ClientePrimerContacto clientePrimerContacto2= mantenimientoMAEClienteDAO.getPrimerContactoOid((Long)primerContactoOid.get(i));
						clientePrimerContacto2.setCodigoUsuario(clienteSubTipo.getCodigoUsuario());
						clientePrimerContacto2.setOidClienteSubTipo(clienteSubTipo.getOid());
						clientePrimerContacto2.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
						updatePrimerContactoCliente(clientePrimerContacto2);
			}	
		}		
			}	
			
		
		}		
		
		//si cliente es potencial/aval se le retira su subtipo y clasificaciones
		if(cliente.getOidSubTipoAval()!=null) {
			mantenimientoMAEClienteDAO.deleteClasificaciones(cliente.getOidSubTipoAval());
			mantenimientoMAEClienteDAO.deleteSubTipoCliente(cliente.getOidSubTipoAval());
		}
	}

	/**
	 * @param clienteSubTipo
	 */
	private void setClasificacionesCliente(ClienteSubTipo clienteSubTipo) {
		List listClienteClasificacion = clienteSubTipo.getListClienteClasificacion();    		
		Iterator it = listClienteClasificacion.iterator();		
		while(it.hasNext()) {
			ClienteClasificacion clienteClasificacion = (ClienteClasificacion)it.next();
			clienteClasificacion.setCodigoUsuario(clienteSubTipo.getCodigoUsuario());
			
			if(clienteClasificacion.isEliminar()){
				deleteClasificacionCliente(clienteClasificacion);
			}else{
				if(clienteClasificacion.getOid() != null)
					updateClasificacionCliente(clienteClasificacion);
				else {
					
					//Obtenemos OID de la Secuencia para la tabla de Cliente Clasificacion
					Long oidClienteClasificacion = mantenimientoMAEClienteDAO.getSecuenciaNextValueClasificacion();
					clienteClasificacion.setOid(oidClienteClasificacion);
					
					clienteClasificacion.setOidClienteSubTipo(clienteSubTipo.getOid());
					mantenimientoMAEClienteDAO.insertClienteClasificacion(clienteClasificacion);
				}
					
			}
				
			
		}		
	}

	/**
	 * @param cliente
	 * @param clienteSubTipo
	 * @param oidClienteSubTipo
	 */
	private void insertClasificacionesCliente(Cliente cliente,
			ClienteSubTipo clienteSubTipo, Long oidClienteSubTipo) {
		List listClienteClasificacion = clienteSubTipo.getListClienteClasificacion();    		
		Iterator it = listClienteClasificacion.iterator();

		Long oidClienteHistoricoStatus=null;
		//insertamos historico status del cliente
		ClienteHistoricoEstatus clienteHistoricoEstatusUlt = mantenimientoMAEClienteDAO.getUltimoHistoricoEstatus(cliente.getOid().toString());
		
		if(clienteHistoricoEstatusUlt != null) {
			oidClienteHistoricoStatus = clienteHistoricoEstatusUlt.getOid();
		} else {
			if(cliente.getClienteHistoricoEstatus() != null) {
				ClienteHistoricoEstatus clienteHistoricoEstatus = cliente.getClienteHistoricoEstatus();
				clienteHistoricoEstatus.setOidCliente(cliente.getOid());
				oidClienteHistoricoStatus = mantenimientoMAEClienteDAO.getSecuenciaNextValueHistoricoStatus();
				clienteHistoricoEstatus.setOid(oidClienteHistoricoStatus);
				clienteHistoricoEstatus.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
				mantenimientoMAEClienteDAO.insertClienteHistoricoEstatus(clienteHistoricoEstatus);
			}            		
		}
		
		while(it.hasNext()) {
			ClienteClasificacion clienteClasificacion = (ClienteClasificacion)it.next();
			clienteClasificacion.setOidClienteSubTipo(oidClienteSubTipo);
			clienteClasificacion.setCodigoUsuario(clienteSubTipo.getCodigoUsuario());
			clienteClasificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
			
			//Obtenemos OID de la Secuencia para la tabla de Cliente Clasificacion
			Long oidClienteClasificacion = mantenimientoMAEClienteDAO.getSecuenciaNextValueClasificacion();
			clienteClasificacion.setOid(oidClienteClasificacion);
			
			mantenimientoMAEClienteDAO.insertClienteClasificacion(clienteClasificacion);
			
			//insertamos datos en historico clasificacion
			if(oidClienteHistoricoStatus != null) {
				HistoricoClasificacion historicoClasificacion = new HistoricoClasificacion();
				historicoClasificacion.setOidClienteHistoricoEstatus(oidClienteHistoricoStatus);
				historicoClasificacion.setOidClasificacion(clienteClasificacion.getOidClasificacion());
				historicoClasificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
				/*
				try {
					mantenimientoMAEClienteDAO.insertHistoricoClasificacion(historicoClasificacion);
				} catch(Exception ex) {
					log.info("insertHistoricoClasificacion : ya existia el historico clasificacion");
				}	
				*/
			}
		}
	}

	/**
	 * @param cliente
	 */
	private void updateIdentificacionCliente(Cliente cliente) {
		List listIdentificacionClient = cliente.getListClienteIdentificacion();
		Iterator it = listIdentificacionClient.iterator();
		while(it.hasNext()){
			ClienteIdentificacion clienteIdentificacion	= (ClienteIdentificacion)it.next();
			clienteIdentificacion.setCodigoUsuario(cliente.getCodigoUsuario());

			if(clienteIdentificacion.getOid()!= null){
				if(clienteIdentificacion.isEliminar())//elimina
					deleteIdentificacionCliente(clienteIdentificacion);	
				else//update
					updateIdentificacionCliente(clienteIdentificacion);
			}else{
				//insertamos				
				clienteIdentificacion.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
				mantenimientoMAEClienteDAO.insertClienteIdentificacion(clienteIdentificacion);
			}
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateDatosAdicionalesCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteAdicional)
	 */
	public void updateDatosAdicionalesCliente(ClienteAdicional clienteAdicional) {
		mantenimientoMAEClienteDAO.updateDatosAdicionalesCliente(clienteAdicional);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateDatosBasicosCliente(biz.belcorp.ssicc.spusicc.mae.model.Cliente)
	 */
	public void updateDatosBasicosCliente(Cliente cliente) throws Exception {
		//validamos digito de control, si esta en nulo, actualizamos
		if((cliente.getDigitoControl()==null) || (cliente.getDigitoControl().length()==0)) {
	        String valorModulo = mantenimientoMAEClienteDAO.getValorModuloxPaisTipoValidacion(cliente.getCodigoPais(), Constants.MAE_VALID_CODCLIE);
	        String digitoControl = "";
	        
	        if(valorModulo == null)
	        	throw new Exception("No Se encontro Modulo de Generación de Codigo de Cliente para el pais");

	        if(valorModulo.equals(Constants.MAE_MODULO_00)) {
	        	digitoControl = null;
	        } else if(valorModulo.equals(Constants.MAE_MODULO_11)) {
	        	digitoControl = StringUtils.right(cliente.getCodigo(), 1);
	        } else if (valorModulo.equals(Constants.MAE_MODULO_02)) { 
	        	digitoControl = generarDigitoControlModulo02(StringUtils.right(cliente.getCodigo(), 7));
	        }
	        else if (valorModulo.equals(Constants.MAE_MODULO_03)) {
	        	digitoControl = generarDigitoControlModulo03(StringUtils.right(cliente.getCodigo(), 7));
	        }
        	else {
        		throw new Exception("No Existe Algoritmo de generacion de Codigo de Cliente para Modulo: " + valorModulo);
        	}
	        
	        cliente.setDigitoControl(digitoControl);
		}
		
		mantenimientoMAEClienteDAO.updateDatosBasicosCliente(cliente);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updatePrimerContactoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClientePrimerContacto)
	 */
	public void updatePrimerContactoCliente(
			ClientePrimerContacto clientePrimerContacto) {
		if(clientePrimerContacto.getOid()!=null)
			mantenimientoMAEClienteDAO.updatePrimerContactoCliente(clientePrimerContacto);
		else
			mantenimientoMAEClienteDAO.insertClientePrimerContacto(clientePrimerContacto);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#deleteClasificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteClasificacion)
	 */
	public void deleteClasificacionCliente(
			ClienteClasificacion clienteClasificacion) {
		mantenimientoMAEClienteDAO.deleteClasificacionCliente(clienteClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#deleteIdentificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteIdentificacion)
	 */
	public void deleteIdentificacionCliente(
			ClienteIdentificacion clienteIdentificacion) {
		 mantenimientoMAEClienteDAO.deleteIdentificacionCliente(clienteIdentificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#deleteTipoSubtipoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteSubTipo)
	 */
	public void deleteTipoSubtipoCliente(ClienteSubTipo clienteSubTipo) {
		 mantenimientoMAEClienteDAO.deleteTipoSubtipoCliente(clienteSubTipo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateClasificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteClasificacion)
	 */
	public void updateClasificacionCliente(
			ClienteClasificacion clienteClasificacion) {
		 mantenimientoMAEClienteDAO.updateClasificacionCliente(clienteClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateIdentificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteIdentificacion)
	 */
	public void updateIdentificacionCliente(
			ClienteIdentificacion clienteIdentificacion) {
		  mantenimientoMAEClienteDAO.updateIdentificacionCliente(clienteIdentificacion);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateTipoSubtipoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteSubTipo)
	 */
	public void updateTipoSubtipoCliente(ClienteSubTipo clienteSubTipo) {
		mantenimientoMAEClienteDAO.updateTipoSubtipoCliente(clienteSubTipo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#deleteDireccionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteDireccion)
	 */
	public void deleteDireccionCliente(ClienteDireccion clienteDireccion) {
		mantenimientoMAEClienteDAO.deleteDireccionCliente(clienteDireccion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacion)
	 */
	public void updateComunicacionCliente(
			ClienteComunicacion clienteComunicacion) {
		mantenimientoMAEClienteDAO.updateComunicacionCliente(clienteComunicacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateDireccionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteDireccion)
	 */
	public void updateDireccionCliente(ClienteDireccion clienteDireccion) {
		mantenimientoMAEClienteDAO.updateDireccionCliente(clienteDireccion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateObservacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteObservacion)
	 */
	public void updateObservacionCliente(ClienteObservacion clienteObservacion) {
		mantenimientoMAEClienteDAO.updateObservacionCliente(clienteObservacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateUnidadAdministrativaCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteUnidadAdministrativa)
	 */
	public void updateUnidadAdministrativaCliente(Cliente cliente) throws Exception {
		ClienteUnidadAdministrativa clienteUnidadAdministrativaOld = cliente.getClienteUnidadAdministrativa();
		ClienteUnidadAdministrativa clienteUnidadAdministrativaNew = cliente.getClienteUnidadAdministrativaNew();
		ClienteUnidadAdministrativa clienteUnidadAdministrativaOldAux = clienteUnidadAdministrativaOld;
		
		//VERIFICAMOS SI HUBO CAMBIO DE UNIDAD ADMINISTRATIVA
		if(clienteUnidadAdministrativaNew != null) {
			clienteUnidadAdministrativaNew.setCodigoUsuario(cliente.getCodigoUsuario());
			clienteUnidadAdministrativaNew.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
			
			if(clienteUnidadAdministrativaOld != null) {
				clienteUnidadAdministrativaOld.setCodigoUsuario(cliente.getCodigoUsuario());
				
				if(clienteUnidadAdministrativaOld.isEsPeriodoInicioMayorIgualPeriodoVigente()) {
					//VERIFICAMOS SI HA REALIZADO PEDIDO EN LA CAMPANA VIGENTE
				Map criteriaAux = new HashMap();
				String[] periodoVig = new String [1];
				periodoVig[0] = clienteUnidadAdministrativaOld.getPeriodoInicio().toString();
				criteriaAux.put("oidCliente", cliente.getOid().toString());
				criteriaAux.put("listPeriodos", periodoVig); 
		 		boolean pasoPedido = esClienteHaFacturadoPeriodos(criteriaAux);
				
					Map criteriaPeriodo = new HashMap();
					criteriaPeriodo.put("oidPeriodo", clienteUnidadAdministrativaOld.getPeriodoInicio().toString());
					Base basePeriodoFin =  mantenimientoMAEClienteDAO.getPeriodoAnterior(criteriaPeriodo);
					
					if(!pasoPedido) {
						//borramos la unidad administrativa vigente
						mantenimientoMAEClienteDAO.deleteUnidadAdministrativaPeriodoInicio(cliente.getOid().toString(), 
													clienteUnidadAdministrativaOld.getPeriodoInicio().toString());
						
						//recuperamos la unidad administrativa anterior
						clienteUnidadAdministrativaOld = mantenimientoMAEClienteDAO.getUnidadAdministrativaClientexPeriodoFin(
														cliente.getOid().toString(), basePeriodoFin.getCodigo());
					}	
					
					if(clienteUnidadAdministrativaOld != null) {
						clienteUnidadAdministrativaOld.setPeriodoFin(cliente.getClienteUnidadAdministrativa().getPeriodoFin());
						clienteUnidadAdministrativaOld.setIndicadorActivo(cliente.getClienteUnidadAdministrativa().getIndicadorActivo());
					}	

					if(clienteUnidadAdministrativaOld!=null && (clienteUnidadAdministrativaOld.getOidTerritorioAdministrativo().longValue()==
																clienteUnidadAdministrativaNew.getOidTerritorioAdministrativo().longValue())) {
						//actualizamos la anterior como la unidad administrativa vigente
						clienteUnidadAdministrativaOld.setPeriodoFin(null);
						clienteUnidadAdministrativaOld.setIndicadorActivo(new Integer(1));
					
						clienteUnidadAdministrativaOld.setIndicadorCambio("1");
						mantenimientoMAEClienteDAO.updateUnidadAdministrativaCliente(clienteUnidadAdministrativaOld);
						
					} else {
						if(clienteUnidadAdministrativaOld!=null)
							mantenimientoMAEClienteDAO.updateUnidadAdministrativaCliente(clienteUnidadAdministrativaOld);
						
						mantenimientoMAEClienteDAO.deleteUnidadAdministrativaPeriodoInicio(cliente.getOid().toString(), 
								clienteUnidadAdministrativaNew.getPeriodoInicio().toString());
						
						//Creamos una nueva unidad administrativa
						clienteUnidadAdministrativaNew.setIndicadorCambio("1");
						mantenimientoMAEClienteDAO.insertClienteUnidadAdministrativa(clienteUnidadAdministrativaNew);
					}
				}
				else {
					mantenimientoMAEClienteDAO.updateUnidadAdministrativaCliente(clienteUnidadAdministrativaOld);
					
					//Creamos una nueva unidad administrativa
					clienteUnidadAdministrativaNew.setIndicadorCambio("1");
					mantenimientoMAEClienteDAO.insertClienteUnidadAdministrativa(clienteUnidadAdministrativaNew);
				}
				
			} else {
				//Creamos una nueva unidad administrativa
				mantenimientoMAEClienteDAO.insertClienteUnidadAdministrativa(clienteUnidadAdministrativaNew);
			}
			
			//Se actualiza la unidad administrativa en todas las solicitudes no facturadas
			Map criteriaPedido = new HashMap();
			criteriaPedido.put("oidCliente", cliente.getOid().toString());
			criteriaPedido.put("oidTerritorioAdministrativo", clienteUnidadAdministrativaNew.getOidTerritorioAdministrativo().toString());
			mantenimientoMAEClienteDAO.updatePedidosConNuevaUnidadAdministrativa(criteriaPedido);
			
			//Verificamos si se requiere actualizar la entidad Historico Estatus del Cliente
			String oidPeriodoInicio = clienteUnidadAdministrativaNew.getPeriodoInicio().toString();
			
			Map criteriaPeriodo = new HashMap();
			criteriaPeriodo.put("oidPeriodo", oidPeriodoInicio);
			Base basePeriodoFin =  mantenimientoMAEClienteDAO.getPeriodoAnterior(criteriaPeriodo);
			String oidPeriodoFin = basePeriodoFin.getCodigo();
			
			if(clienteUnidadAdministrativaOld!=null) {
				actualizaStatusCliente(cliente, oidPeriodoInicio, oidPeriodoFin, clienteUnidadAdministrativaOldAux.getCodigoZona(),
										clienteUnidadAdministrativaNew.getCodigoZona());
			}
			
			if(clienteUnidadAdministrativaNew.isRequiereGenerarEstatus()) {
				Map criteria = new HashMap();
				criteria.put("codigoPais", cliente.getCodigoPais());
				criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
				criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

				//RECUPERAMOS LOS PERIODOS VIGENTES
				LabelValue[] periodos = getPeriodosVigentesByPaisMarcaCanal(criteria);
				
				Map params = new HashMap();
				params.put("codigoPais", cliente.getCodigoPais());
				params.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
				params.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
				params.put("codigoPeriodo", periodos[0].getLabel());
				params.put("codigoRegion", "");
				params.put("codigoCliente", cliente.getCodigo());
				params.put("codigoUsuario", cliente.getCodigoUsuario());
				
				mantenimientoMAEClienteDAO.executeCalcularEstatusCliente(params);
			}	
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateVinculoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteVinculo)
	 */
	public void updateVinculoCliente(ClienteVinculo clienteVinculo) {
		mantenimientoMAEClienteDAO.updateVinculoCliente(clienteVinculo);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getPeriodosByPaisMarcaCanal(Map criteria) {
		return mantenimientoMAEClienteDAO.getPeriodosByPaisMarcaCanal(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public boolean esTipoDocumentoSiglaRUC(Map criteria) {
		return mantenimientoMAEClienteDAO.esTipoDocumentoSiglaRUC(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public boolean esClienteHaFacturado(Map criteria) {
		return mantenimientoMAEClienteDAO.esClienteHaFacturado(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public boolean esRegionCerradaxZona(Map criteria) {
		return mantenimientoMAEClienteDAO.esRegionCerradaxZona(criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public Base getSiguientePeriodo(Map criteria) {
		return mantenimientoMAEClienteDAO.getSiguientePeriodo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#esPaisCalculaPeriodoIngreso(java.util.Map)
	 */
	public boolean esPaisCalculaPeriodoIngreso(String codigoPais) {
		return mantenimientoMAEClienteDAO.esPaisCalculaPeriodoIngreso(codigoPais);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public String getFechaInicioPeriodo(Map criteria) {
		return mantenimientoMAEClienteDAO.getFechaInicioPeriodo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateClienteRecomendado(java.util.List)
	 */
	public void updateClienteRecomendado(List listClienteConcursoPremio) {
    	//Actualizamos los premios del cliente recomendado
    		
		Iterator itConcursoPremio = listClienteConcursoPremio.iterator();
		while(itConcursoPremio.hasNext()) {
			ClienteConcursoPremio concursoPremio = (ClienteConcursoPremio)itConcursoPremio.next();
			Map criteria = new HashMap();
			
			//recuperamos el oid Cliente Recomendante de Incentivos
			criteria.put("oidConcurso", concursoPremio.getOidConcurso().toString());
			criteria.put("codigoClienteRecomendante", concursoPremio.getCodigoClienteRecomendante());
			String oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
			
			//recuperamos el oid Cliente Recomendando de Incentivos			
			criteria.put("oidClienteRecomendante", oidRecomendante);
			criteria.put("codigoClienteRecomendado", concursoPremio.getCodigoClienteRecomendado());
			String oidRecomendado = mantenimientoMAEClienteDAO.getOidRecomendado(criteria);
			
			concursoPremio.setOidClienteRecomendado(new Long(oidRecomendado));
			
    		mantenimientoMAEClienteDAO.updateRecomendado(concursoPremio);
		}
	
	}	

   /**
     * @param codigoCliente
     * @return
     * @throws Exception
     */
    private String generarDigitoControlModulo02(String codigoCliente) throws Exception {
    	int base = Constants.MAE_CLIENTE_MODULO_02_BASE;
    	int divisor = Constants.MAE_CLIENTE_MODULO_02_DIVISOR;
    	int resta = Constants.MAE_CLIENTE_MODULO_02_RESTA;
    	String factor = Constants.MAE_CLIENTE_MODULO_02_FACTOR;
    	
    	String codigoClienteAux = StringUtils.right(codigoCliente, 7);
    	int resultado = base;
    	int j = 0;
    	
    	for(int i=1; i<=7; i++) {
    		int posicion = Integer.parseInt(codigoClienteAux.substring(i-1,i));
    		int peso = Integer.parseInt(factor.substring(j, j+2));
    		
    		resultado = resultado + posicion * peso;
    		
    		j = j + 2;
    	}
    	
    	int residuo = resultado % divisor;
    	residuo = resta - residuo;
    	
    	String digitoControl = String.valueOf(residuo);
    	if(digitoControl.length() == 1 ) {
    		digitoControl = "0" + digitoControl;
    	}

        log.debug("DigitoControl: " + digitoControl); 
        
        return digitoControl;
    }

    /**
     * @param codigoCliente
     * @return
     * @throws Exception
     */
    private String generarDigitoControlModulo03(String codigoCliente) throws Exception {
    	int base = Constants.MAE_CLIENTE_MODULO_03_BASE1;
    	int constante = Constants.MAE_CLIENTE_MODULO_03_BASE2;
    	String factor = Constants.MAE_CLIENTE_MODULO_03_FACTOR;
    	
    	//obtenemos los 6 ultimos caracteres del codigo Cliente
    	String codigoClienteAux = StringUtils.right(codigoCliente, 6);
    	
    	//sumamos con la constante 448172
    	int resultado = constante  + Integer.parseInt(codigoClienteAux);
    	
    	//obtenemos nuevamente los 6 ultimos caracteres de la suma anterior
    	codigoClienteAux = StringUtils.right(String.valueOf(resultado), 6);
    	
    	//recorremos cada caracter multiplicando por un factor
    	resultado = 0;
    	for(int i=1; i<=6; i++) {
    		int posicion = Integer.parseInt(codigoClienteAux.substring(i-1,i));
    		int peso = Integer.parseInt(factor.substring(i-1, i));
    		int valor = posicion * peso;
    		
    		if(posicion * peso >= 10) {
    			resultado = resultado + 1 + (valor - 10);
    			
    		} else {
    			resultado = resultado + valor;
    		}
    	}
    	
    	int sumatoria = resultado + base;
    	int residuo = sumatoria % 10;
    	
    	
    	
    	String digitoControl = Constants.MAE_CLIENTE_MODULO_03_PREFIJO;
	
    	if(residuo == 0) {
    		digitoControl = digitoControl + String.valueOf(residuo);
    	} else {
    		digitoControl = digitoControl + String.valueOf(10 - residuo);
    	}
    		
        log.debug("DigitoControl: " + digitoControl); 
        
        return digitoControl;
    }

    /**
     * @param codigoPais
     * @param tipoValidacion
     * @return
     */
    public String getValorModuloxPaisTipoValidacion(String codigoPais, String tipoValidacion) {
    	return mantenimientoMAEClienteDAO.getValorModuloxPaisTipoValidacion(codigoPais, tipoValidacion);    	
    }
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getTipoVinculo()
	 */
	public List getTipoVinculo() {
		return mantenimientoMAEClienteDAO.getTipoVinculo();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListClienteReferencia(java.util.Map)
	 */
	public List getListClienteReferencia(Map map) {
		return mantenimientoMAEClienteDAO.getListClienteReferencia(map);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public boolean esZonaCerrada(Map criteria) {
		return mantenimientoMAEClienteDAO.esZonaCerrada(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidTipoVia(java.lang.String)
	 */
	public Long getOidTipoVia(String codigoTipoVia) {
		return mantenimientoMAEClienteDAO.getOidTipoVia(codigoTipoVia);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#esClienteHaFacturadoPeriodos(java.util.Map)
	 */
	public boolean esClienteHaFacturadoPeriodos(Map criteria) {
		return mantenimientoMAEClienteDAO.esClienteHaFacturadoPeriodos(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getUltimoPeriodoFacturado(java.lang.String)
	 */
	public String getUltimoPeriodoFacturado(String oidCliente) {
		return mantenimientoMAEClienteDAO.getUltimoPeriodoFacturado(oidCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getPeriodoAnterior(java.util.Map)
	 */
	public Base getPeriodoAnterior(Map criteria) {
		return mantenimientoMAEClienteDAO.getPeriodoAnterior(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#esPeriodoCerrado(java.util.Map)
	 */
	public boolean esPeriodoCerrado(Map criteria) {
		return mantenimientoMAEClienteDAO.esPeriodoCerrado(criteria);
	}
	
    /**
     * Verifica si se requiere actualizar entidad Historico Estatus del Cliente
     * 
     * @param cliente
     * @param oidPeriodoInicio
     * @param oidPeriodoFin
     * @param codigoZonaOrigen
     * @param codigoZonaDestino
     * @throws Exception
     */
    private void actualizaStatusCliente(Cliente cliente, String oidPeriodoInicio, String oidPeriodoFin, 
    									String codigoZonaOrigen, String codigoZonaDestino) throws Exception {
        log.debug("actualizaStatusCliente(): Entrada");

        boolean indPeriodoVigente = false;
        boolean indCierreRegionOrigen = false;
        boolean indCierreRegionDestino = false;

        //obtenemos los periodos vigentes
        Map criteria = new HashMap();
        criteria.put("codigoPais", cliente.getCodigoPais());
        criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
        criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
        criteria.put("oidPeriodoFin", oidPeriodoFin);
    	criteria.put("oidPeriodo", oidPeriodoInicio);
    	
        LabelValue[] periodos = getPeriodosVigentesByPaisMarcaCanal(criteria);        
        
        //Verificamos si periodoInicio es periodoVigente
        for(int i=0; i< periodos.length; i++) {
            if(periodos[i].getValue().equals(oidPeriodoInicio)) {
                 indPeriodoVigente = true;
                 break;
            }
        }        

        log.debug("Indicador Periodo Vigente: " + indPeriodoVigente);
        
        //Verificamos Si Region Origen ha cerrado y Region Destino ha cerrado
        if(indPeriodoVigente) {
        	criteria.put("codigoZona", codigoZonaOrigen);
            indCierreRegionOrigen = mantenimientoMAEClienteDAO.esRegionCerradaxZona(criteria);
            
            criteria.put("codigoZona", codigoZonaDestino);
            indCierreRegionDestino = mantenimientoMAEClienteDAO.esRegionCerradaxZona(criteria);
            
            log.debug("indCierreRegionOrigen(codigo Zona: " + codigoZonaOrigen + "): " + indCierreRegionOrigen);
            log.debug("indCierreRegionDestino(codigo Zona: " + codigoZonaDestino + "): " + indCierreRegionDestino);
        }

        if(indPeriodoVigente && indCierreRegionOrigen && !indCierreRegionDestino) {
        	log.debug("Se valida si se actualiza Estatus del Cliente");
            Long oidDatosAdi = null;
            Long estatus = null;
            Long oidHistoEstatus = null;
            Long oidHistoEstatusActual = null;
            Integer numCampSinPedi;
      
            //Recuperamos el oid y Numero Campaña sin Pedidos para el cliente
            oidDatosAdi = cliente.getClienteAdicional().getOid();
            numCampSinPedi = cliente.getClienteAdicional().getNumeroCampanasSinPedido();
            log.debug("Numero de Campanias sin Pedido: " + numCampSinPedi);                
      
            if(numCampSinPedi.intValue() > 0) {
            	log.debug("Se Evalua si se realiza rollback de Historico de Estatus");                
                criteria.put("oidCliente", cliente.getOid().toString());
                
                //Recuperamos el oid y Status del penultimo Historico de Status, con periodofin = this.PeriodoFin
            	ClienteHistoricoEstatus clienteEstatus = mantenimientoMAEClienteDAO.getHistoricoEstatusPeriodoFin(criteria);
            	if(clienteEstatus != null) {
            		oidHistoEstatus = clienteEstatus.getOid();
            		estatus = clienteEstatus.getOidEstatus();
            	} else {
            		log.debug(" No se encontro Historico Status para el Cliente y Periodo Fin");
            	}	
            	
                //si encuentra estatus anterior, se hace rollback de lo que se realizo 
                //en cierre de region para region Destino
                if(oidHistoEstatus != null) {
                	log.debug("Se encontro Historico Anterior Status");                

                    //Recuperamos el oid y Status del penultimo Historico de Status, con periodofin = this.PeriodoFin
                	clienteEstatus = mantenimientoMAEClienteDAO.getHistoricoEstatusPeriodoInicio(criteria);
                	if(clienteEstatus == null)
                		log.debug(" No se encontro Historico Actual para el Cliente y Periodo Fin");
                	else
                		oidHistoEstatusActual = clienteEstatus.getOid();

                    try {                    
                        //Elimina Historico Clasificacion para el Historico Status actual
                    	mantenimientoMAEClienteDAO.deleteHistoricoClasificacion(oidHistoEstatusActual.toString());
                    	
                        //Elimina Historico Status con PeriodoInicio = this.periodoInicio
                    	mantenimientoMAEClienteDAO.deleteHistoricoEstatus(oidHistoEstatusActual.toString());
                        
                        //Recupera Status Anterior y Actualiza Pedido Periodo Fin = Null
                        criteria.put("oidEstatus", estatus.toString());
                        criteria.put("oidHistoricoEstatus", oidHistoEstatus.toString());
                    	mantenimientoMAEClienteDAO.updateHistoricoEstatus(criteria);
                  
                        //Actualiza Estatus Anterior y resta 1 a Nro Periodos sin Pedido
                        numCampSinPedi = new Integer (numCampSinPedi.intValue() - 1);
                        criteria.put("numCampSinPedidos", numCampSinPedi.toString());
                        criteria.put("oidDatosAdicionales", oidDatosAdi.toString());
                        mantenimientoMAEClienteDAO.updateDatosAdicionalesEstatus(criteria);

                        log.debug("actualizaStatusCliente: Hecho");                           
                        
                    } catch (Exception e) {
                        log.error("Error al actualizar Status del cliente :" + e.getMessage());
                        throw e;
                    }
                        
                }    
            }    
        }

        log.debug("actualizaStatusCliente(): Salida");
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#executeValidacionDeudoraConsultoraAval(java.util.Map)
	 */
	public void executeValidacionDeudoraConsultoraAval(Map criteria) {
		mantenimientoMAEClienteDAO.executeValidacionDeudoraConsultoraAval(criteria);
		List listDeudoras = getListConsultorasDeudorasAval(criteria);
		criteria.put("listDeudoras",listDeudoras);				
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getListConsultorasDeudorasAval(java.util.Map)
	 */
	public List getListConsultorasDeudorasAval(Map criteria) {
		return mantenimientoMAEClienteDAO.getListConsultorasDeudorasAval(criteria);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getSizePedidosEnviados(java.util.Map)
	 */
	public Integer getSizePedidosEnviados(Map criteria) {
		return mantenimientoMAEClienteDAO.getSizePedidosEnviados(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateClientePeriodo(java.util.Map)
	 */
	public void updateClientePeriodo(Map params) throws Exception{
		List listClienteConcursoPremio = (List)params.get("listClienteConcursoPremio");
		
		//actualizamos las entidades de primer contacto, datos adicionales,
		//historico de estatus, unidad administrativa del cliente, y borramos
		//sus premios recomendados
		mantenimientoMAEClienteDAO.executeRedifinirPeriodoIngreso(params);
		
		//insertamos los premios para la consultora
		Iterator itConcursoPremio = listClienteConcursoPremio.iterator();
		while(itConcursoPremio.hasNext()) {
			ClienteConcursoPremio concursoPremio = (ClienteConcursoPremio)itConcursoPremio.next();
			
			Map criteria = new HashMap();
			criteria.put("oidConcurso", concursoPremio.getOidConcurso().toString());
			criteria.put("codigoClienteRecomendante", concursoPremio.getCodigoClienteRecomendante());
			
			String oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
			
			if(oidRecomendante == null) {
				mantenimientoMAEClienteDAO.insertRecomendante(concursoPremio);
				oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
			}
			concursoPremio.setOidClienteRecomendante(new Long(oidRecomendante));
			
    		mantenimientoMAEClienteDAO.insertRecomendado(concursoPremio);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getZonaCliente(java.lang.String, java.lang.String)
	 */
	public String getZonaCliente(String codigoPais, String codigoCliente) {
		String codigoZona = "";
		
		Cliente cliente = mantenimientoMAEClienteDAO.getDatosBasicosCliente(codigoPais, codigoCliente);
		
		ClienteUnidadAdministrativa unidadAdministrativa = mantenimientoMAEClienteDAO.
										getUnidadAdministrativaCliente(cliente.getOid().toString());
		
		if(unidadAdministrativa != null) {
			codigoZona = unidadAdministrativa.getCodigoZona();
		}
		
		return codigoZona;	
	}

	private void insertClienteAvales(Cliente cliente) {
    	if(cliente.getListClienteAval() != null &&  cliente.getListClienteAval().size()>0) {
    		List listClienteAval = cliente.getListClienteAval();
    		
    		Iterator itAval = listClienteAval.iterator();
    		while(itAval.hasNext()) {
    			Map mapAval = (Map)itAval.next();
    			
    			mapAval.put("codigoClienteAval", mapAval.get("codigoConsultora"));
    			mapAval.put("codigoCliente", cliente.getCodigo());
    			mapAval.put("codigoUsuario", cliente.getCodigoUsuario());
    			
    			mantenimientoMAEClienteDAO.insertClienteAval(mapAval);
    		}
    	}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getSegundoTipoDocumento(java.lang.String)
	 */
	public String getSegundoTipoDocumento(String oidPais) {
        return mantenimientoMAEClienteDAO.getSegundoTipoDocumento(oidPais);
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#verificarDocumentoIdentidad(java.util.Map)
	 */
	public boolean verificarDocumentoIdentidad(Map criteria) {
		boolean encontrado = false;
		List documentos = mantenimientoMAEClienteDAO.getDocumentosCliente(criteria);
		
		//se encontro un cliente para el tipo y numero de documento ingresado
		if(documentos.size() > 0) {
			encontrado = true;
		}
		
		return encontrado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExistePreferenciaComunicacion(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion)
	 */
	public ClientePreferenciaComunicacion getExistePreferenciaComunicacion(
			ClientePreferenciaComunicacion preferenciaComun) {
		return mantenimientoMAEClienteDAO.getExistePreferenciaComunicacion(preferenciaComun);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertPreferencia(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPreferencia(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		mantenimientoMAEClienteDAO.insertPreferencia(preferenciaComun, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updatePreferencia(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePreferencia(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		mantenimientoMAEClienteDAO.updatePreferencia(preferenciaComun, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExistePreferenciaClienteComunicacion(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion)
	 */
	public Integer getExistePreferenciaClienteComunicacion(
			ClientePreferenciaComunicacion preferenciaComun) {
		return mantenimientoMAEClienteDAO.getExistePreferenciaClienteComunicacion(preferenciaComun);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertPreferenciaCliente(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPreferenciaCliente(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		mantenimientoMAEClienteDAO.insertPreferenciaCliente(preferenciaComun, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updatePreferenciaCliente(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePreferenciaCliente(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		mantenimientoMAEClienteDAO.updatePreferenciaCliente(preferenciaComun, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora)
	 */
	public Integer getExisteComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora) {
		return mantenimientoMAEClienteDAO.getExisteComunicacionCliente(comunicacionOperadora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteTipoOperadora(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora)
	 */
	public ClienteComunicacionOperadora getExisteTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora) {
		return mantenimientoMAEClienteDAO.getExisteTipoOperadora(comunicacionOperadora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.insertComunicacionCliente(comunicacionOperadora, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertTipoOperadora(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.insertTipoOperadora(comunicacionOperadora, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.updateComunicacionCliente(comunicacionOperadora, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateTipoOperadora(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.updateTipoOperadora(comunicacionOperadora, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteTipoLogro(biz.belcorp.ssicc.spusicc.mae.model.ClienteTipoLogro)
	 */
	public ClienteTipoLogro getExisteTipoLogro(ClienteTipoLogro clientTipoLogro) {
		return mantenimientoMAEClienteDAO.getExisteTipoLogro(clientTipoLogro);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertTipoLogro(biz.belcorp.ssicc.spusicc.mae.model.ClienteTipoLogro, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoLogro(ClienteTipoLogro clientTipoLogro,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.insertTipoLogro(clientTipoLogro, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateTipoLogro(biz.belcorp.ssicc.spusicc.mae.model.ClienteTipoLogro, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoLogro(ClienteTipoLogro clientTipoLogro,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.updateTipoLogro(clientTipoLogro, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove)
	 */
	public SegmentoGrupoLove getExisteGrupoLove(
			SegmentoGrupoLove segmentoGrupoLove) {
		return mantenimientoMAEClienteDAO.getExisteGrupoLove(segmentoGrupoLove);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteSegmentoGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove)
	 */
	public SegmentoGrupoLove getExisteSegmentoGrupoLove(
			SegmentoGrupoLove segmentoGrupoLove) {
		return mantenimientoMAEClienteDAO.getExisteSegmentoGrupoLove(segmentoGrupoLove);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.insertGrupoLove(segmentoGrupoLove, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertSegmentoGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSegmentoGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.insertSegmentoGrupoLove(segmentoGrupoLove, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.updateGrupoLove(segmentoGrupoLove, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateSegmentoGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateSegmentoGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.updateSegmentoGrupoLove(segmentoGrupoLove, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteClienteEncuesta(biz.belcorp.ssicc.spusicc.mae.model.ClienteEncuesta)
	 */
	public Integer getExisteClienteEncuesta(ClienteEncuesta clienteEncuesta) {
		return mantenimientoMAEClienteDAO.getExisteClienteEncuesta(clienteEncuesta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertClienteEncuesta(biz.belcorp.ssicc.spusicc.mae.model.ClienteEncuesta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertClienteEncuesta(ClienteEncuesta clienteEncuesta,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.insertClienteEncuesta(clienteEncuesta, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateClienteEncuesta(biz.belcorp.ssicc.spusicc.mae.model.ClienteEncuesta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateClienteEncuesta(ClienteEncuesta clienteEncuesta,
			Usuario usuario) {
		mantenimientoMAEClienteDAO.updateClienteEncuesta(clienteEncuesta, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getCaracteresxModuloValidacion(java.lang.String, java.lang.String)
	 */
	public String getCaracteresxModuloValidacion(String codigoModuloValidacion, String indicador) {
		StringBuffer cadenaCaracteres = new StringBuffer("");
		
		Map criteria = new HashMap();
		criteria.put("codigoModuloValidacion", codigoModuloValidacion);
		criteria.put("indicador", indicador);
		
		List listCaracteres = mantenimientoMAEClienteDAO.getCaracteresxModuloValidacion(criteria);
		Iterator it = listCaracteres.iterator();
		while(it.hasNext()) {
			Base base = (Base)it.next();
			char c = base.getCodigo().charAt(0);
			int c_ascii = (int)c;
			cadenaCaracteres.append(String.valueOf(c_ascii));
			cadenaCaracteres.append("__");
		}
		
		return cadenaCaracteres.toString();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateClientePeriodoRetiradas(java.util.Map)
	 */
	public void updateClientePeriodoRetiradas(Map params) throws Exception{
		List listClienteConcursoPremio = (List)params.get("listClienteConcursoPremio");
		
		//actualizamos las entidades de unidad administrativa del cliente, y borramos
		//sus premios recomendados
		mantenimientoMAEClienteDAO.executeRedifinirPeriodoIngresoRetiradas(params);
		
		//insertamos los premios para la consultora
		Iterator itConcursoPremio = listClienteConcursoPremio.iterator();
		while(itConcursoPremio.hasNext()) {
			ClienteConcursoPremio concursoPremio = (ClienteConcursoPremio)itConcursoPremio.next();
			
			Map criteria = new HashMap();
			criteria.put("oidConcurso", concursoPremio.getOidConcurso().toString());
			criteria.put("codigoClienteRecomendante", concursoPremio.getCodigoClienteRecomendante());
			
			String oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
			
			if(oidRecomendante == null) {
				mantenimientoMAEClienteDAO.insertRecomendante(concursoPremio);
				oidRecomendante = mantenimientoMAEClienteDAO.getOidRecomendante(criteria);
			}
			concursoPremio.setOidClienteRecomendante(new Long(oidRecomendante));
			
    		mantenimientoMAEClienteDAO.insertRecomendado(concursoPremio);
		}
	}
	
	/**
	 * Se valida si ya existe un elemento con TipoCliente/SubTipo Cliente
	 * 
	 * @param codigoTipoCliente
	 * @param codigoSubTipoCliente
	 * @param detalList
	 * @return
	 */
	private boolean existeSubTipoCliente(String codigoTipoCliente, String codigoSubTipoCliente, List detalList) {
		Iterator it= detalList.iterator();
	
		while(it.hasNext()){
			ClienteSubTipo ccd = (ClienteSubTipo)it.next();
			
			if((ccd.getCodigoTipoCliente().equals(codigoTipoCliente)) && (ccd.getCodigoSubTipoCliente().equals(codigoSubTipoCliente))) {
				 return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getValidacionVigenciaRecomendacion(java.util.Map)
	 */
	public String getValidacionVigenciaRecomendacion(Map criteria) {
		return mantenimientoMAEClienteDAO.getValidacionVigenciaRecomendacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getPeriodoRecomendacion(java.lang.String, java.lang.String)
	 */
	public String getPeriodoRecomendacion(String oidPais, String oidCliente) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("oidCliente", oidCliente);
		
		return mantenimientoMAEClienteDAO.getPeriodoRecomendacion(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getTipoCutis(java.util.Map)
	 */
	public List getTipoCutis(Map criteria) {
		return mantenimientoMAEClienteDAO.getTipoCutis(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOtrasMarcas(java.util.Map)
	 */
	public List getOtrasMarcas(Map criteria) {
		return mantenimientoMAEClienteDAO.getOtrasMarcas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getTipoPersona(java.util.Map)
	 */
	public List getTipoPersona(Map criteria) {
		return mantenimientoMAEClienteDAO.getTipoPersona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOrigenIngreso(java.util.Map)
	 */
	public List getOrigenIngreso(Map criteria) {
		return mantenimientoMAEClienteDAO.getOrigenIngreso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getValidarOCRConsultora(java.util.Map)
	 */
	public Map getValidarOCRConsultora(Map map) {
		return mantenimientoMAEClienteDAO.getValidarOCRConsultora(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateInformacionCliente(java.util.Map)
	 */
	public void updateInformacionCliente(Map params) {
		String oidCliente = (String)params.get("oidCliente");
		String oidPais = (String)params.get("oidPais");
		String oidEstatus = (String)params.get("oidEstatus");
				
		Long oidPeriodo = (Long)params.get("oidPeriodo");
		boolean tieneSubTipoClienteConsultora  = Boolean.parseBoolean((String)params.get("tieneSubTipoClienteConsultora"));

		//Insertamos un registro en Historico de Nivel de Riesgo
		mantenimientoMAEClienteDAO.insertClienteNivelRiesgo(params);

		//Ingresamos estatus inicial del cliente, si es que no tiene Estatus y se ha agregado un Tipo Cliente: Consultor(a)
		if(oidEstatus == null && tieneSubTipoClienteConsultora) {
			ClienteHistoricoEstatus clienteHistoricoEstatus = new ClienteHistoricoEstatus();
			clienteHistoricoEstatus.setOidCliente(new Long(oidCliente));
			clienteHistoricoEstatus.setOidEstatus(new Long(1));
			clienteHistoricoEstatus.setOidPeriodo(oidPeriodo);
			clienteHistoricoEstatus.setOidPeriodoFin(null);

    		Long oidClienteHistoricoStatus = mantenimientoMAEClienteDAO.getSecuenciaNextValueHistoricoStatus();
    		clienteHistoricoEstatus.setOid(oidClienteHistoricoStatus);
    		mantenimientoMAEClienteDAO.insertClienteHistoricoEstatus(clienteHistoricoEstatus);
    		params.put("oidEstatusConsultoraNueva", "oidEstatusConsultoraNueva");
		}

		//Actualizamos el Nivel de Riesgo y indicador de Actividad en la Entidad de Datos Adicionalesl del Cliente
		mantenimientoMAEClienteDAO.updateNivelRiesgoClienteAdicional(params);

		//Actualizamos el tipoSubtipo y clasificaciones del Cliente
		List listClienteSubTipo = (List)params.get("listClienteSubTipo");
		Cliente cliente = new Cliente();
		cliente.setOid(new Long(oidCliente));
		cliente.setListClienteSubTipo(listClienteSubTipo);
		
		boolean eliminarSubTipo = true;
		for(int i=0; i<listClienteSubTipo.size(); i++) {
			ClienteSubTipo clienteSubTipo = (ClienteSubTipo)listClienteSubTipo.get(i);
			if(clienteSubTipo.isEliminar())
				eliminarSubTipo = true;
		}
		
		//Si se va a eliminar un subTipoCliente, se actualiza el SubTipo de Primer Contacto
		List primerContactoOid = new ArrayList();
		if(eliminarSubTipo) {
		ClienteSubTipo clienteSubTipo = (ClienteSubTipo)listClienteSubTipo.get(0);
				List listPrimerContacto=mantenimientoMAEClienteDAO.getListPrimerContacto(clienteSubTipo.getOid());
					if(listPrimerContacto!=null){
						for(int i =0;i<listPrimerContacto.size();i++){
							ClientePrimerContacto clientePrimerContacto= (ClientePrimerContacto)listPrimerContacto.get(i);
							clientePrimerContacto.setOidClienteSubTipo(mantenimientoMAEClienteDAO.getOidClienteTipoSubTipoMigracion(oidPais));
							clientePrimerContacto.getAuditInfo().setCreatedBy(cliente.getCodigoUsuario());
							primerContactoOid.add(clientePrimerContacto.getOid());
							String fechaContacto = DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,clientePrimerContacto.getFechaContacto());
							String fechaSiguienteContacto = DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT,clientePrimerContacto.getFechaSiguienteContacto());

							clientePrimerContacto.setsFechaContacto(fechaContacto);
							clientePrimerContacto.setsFechaSiguienteContacto(fechaSiguienteContacto);
							updatePrimerContactoCliente(clientePrimerContacto);
						}
					}					
		}		
		
		Long longOidPais = Long.parseLong(oidPais);
		cliente.setOidPais(longOidPais);
		//Actualizamos el tipoSubtipo
		updateSubTipoCliente(cliente, primerContactoOid);

		mantenimientoMAEClienteDAO.executeActualizarPrimerContacto(oidCliente);

	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getClientesBloqueoDesbloqueoByCriteria(java.util.Map)
	 */
	public List getClientesBloqueoDesbloqueoByCriteria(Map criteria) {
		return mantenimientoMAEClienteDAO.getClientesBloqueoDesbloqueoByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getAccesosTiposBloqueoByUsuario(java.lang.String)
	 */
	public List getAccesosTiposBloqueoByUsuario(String usuario) {
		return mantenimientoMAEClienteDAO.getAccesosTiposBloqueoByUsuario(usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getAccesosTiposDesbloqueoByUsuario(java.lang.String)
	 */
	public List getAccesosTiposDesbloqueoByUsuario(String usuario) {
		return mantenimientoMAEClienteDAO.getAccesosTiposDesbloqueoByUsuario(usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getBloqueosClienteByTipoBloqueo(java.lang.Long, java.lang.String)
	 */
	public List getBloqueosClienteByTipoBloqueo(Long oidCliente,
			String tipoBloqueo) {
		return mantenimientoMAEClienteDAO.getBloqueosClienteByTipoBloqueo(oidCliente, tipoBloqueo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertBloqueoCliente(java.util.Map)
	 */
	public void insertBloqueoCliente(Map params) {
		mantenimientoMAEClienteDAO.insertBloqueoCliente(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getAccesosTiposDesbloqueoByUsuario(java.lang.String, java.lang.String)
	 */
	public List getAccesosTiposDesbloqueoByUsuario(String codigoUsuario,
			String oidTipoBloqueo) {
		return mantenimientoMAEClienteDAO.getAccesosTiposDesbloqueoByUsuario(codigoUsuario, oidTipoBloqueo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getBloqueoCliente(java.lang.String)
	 */
	public Map getBloqueoCliente(String oidBloqueo) {
		return mantenimientoMAEClienteDAO.getBloqueoCliente(oidBloqueo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateBloqueoCliente(java.util.Map)
	 */
	public void updateBloqueoCliente(Map params) {
		mantenimientoMAEClienteDAO.updateBloqueoCliente(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getLogBloqueosCliente(java.lang.Long)
	 */
	public List getLogBloqueosCliente(Long oidCliente) {
		return mantenimientoMAEClienteDAO.getLogBloqueosCliente(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getLogDesbloqueosCliente(java.lang.Long)
	 */
	public List getLogDesbloqueosCliente(Long oidCliente) {
		return mantenimientoMAEClienteDAO.getLogDesbloqueosCliente(oidCliente);
	}
	
	/* INI SA PER-SiCC-2012-0580 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#validaPedidosEnProceso(java.util.Map)
	 */
	public boolean validaPedidosEnProceso(Map criteria) {
		return mantenimientoMAEClienteDAO.validaPedidosEnProceso(criteria);
	}
	/* FIN SA PER-SiCC-2012-0580 */

	/* INI SA PER-SiCC-2012-0365 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getPeriodoInicioVacaciones(java.util.Map)
	 */
	public String getPeriodoInicioVacaciones(Map criteria) {
		return mantenimientoMAEClienteDAO.getPeriodoInicioVacaciones(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getPeriodoFinVacaciones(java.util.Map)
	 */
	public String getPeriodoFinVacaciones(Map criteria) {
		return mantenimientoMAEClienteDAO.getPeriodoFinVacaciones(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#tienePedidoFacturado(java.util.Map)
	 */
	public boolean tienePedidoFacturado(Map criteria) {
		return mantenimientoMAEClienteDAO.tienePedidoFacturado(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#tienePedidoEnProcesoFacturacion(java.util.Map)
	 */
	public boolean tienePedidoEnProcesoFacturacion(Map criteria) {
		return mantenimientoMAEClienteDAO.tienePedidoEnProcesoFacturacion(criteria);
	}
	/* FIN SA PER-SiCC-2012-0365 */

	/* INI SA PER-SiCC-2012-0367 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertSolicitudCreditoRechazado(java.util.Map)
	 */
	public void insertSolicitudCreditoRechazado(Map params) {
		mantenimientoMAEClienteDAO.insertSolicitudCreditoRechazado(params);
		mantenimientoMAEClienteDAO.insertDocumentoDigitacion(params);
		mantenimientoMAEClienteDAO.insertDetalleExcepcion(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#executeGenerarSolicitudCreditoRechazada(java.util.Map)
	 */
	public void executeGenerarSolicitudCreditoRechazada(Map params) {
		mantenimientoMAEClienteDAO.executeGenerarSolicitudCreditoRechazada(params);
	}
	/* FIN SA PER-SiCC-2012-0367 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getObtieneTipoValidacion(java.util.Map)
	 */
	public boolean getObtieneTipoValidacion(Map params) {
		// TODO Auto-generated method stub
		return mantenimientoMAEClienteDAO.getObtieneTipoValidacion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getDatosCliente(java.util.Map)
	 */
	public Map getDatosCliente(Map criteria){
		return mantenimientoMAEClienteDAO.getDatosCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getMotivosBaja(java.util.Map)
	 */
	public List getMotivosBaja(Map criteria){
		return mantenimientoMAEClienteDAO.getMotivosBaja(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#executeBajaManualEmpresarias(java.util.Map)
	 */
	public void executeBajaManualEmpresarias(Map params) {
		mantenimientoMAEClienteDAO.executeBajaManualEmpresarias(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getDatosEmprendedoraCliente(java.util.Map)
	 */
	public Map getDatosEmprendedoraCliente(Map criteria){
		return mantenimientoMAEClienteDAO.getDatosEmprendedoraCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getZonaPeriodoCerrada(java.util.Map)
	 */
	public String getZonaPeriodoCerrada(Map map) {
		return mantenimientoMAEClienteDAO.getZonaPeriodoCerrada(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#executeReasignacionManualEmpresarias(java.util.Map)
	 */
	public void executeReasignacionManualEmpresarias(Map params){
		mantenimientoMAEClienteDAO.executeReasignacionManualEmpresarias(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#saveActualizacionDatosPortal(java.util.Map)
	 */
	public void saveActualizacionDatosPortal(Map map) {
		mantenimientoMAEClienteDAO.saveActualizacionDatosPortal(map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#deleteReporteClienteTemporal(java.lang.String)
	 */
	public void deleteReporteClienteTemporal(String codigoUsuario) {
		mantenimientoMAEClienteDAO.deleteReporteClienteTemporal(codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertReporteClienteTemporal(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertReporteClienteTemporal(List resultado, Usuario usuario) {
		mantenimientoMAEClienteDAO.insertReporteClienteTemporal(resultado, usuario);
	}

	public boolean esRegionCerradaxSeccion(Map criteria) {
		return mantenimientoMAEClienteDAO.esRegionCerradaxSeccion(criteria);
	}
	
	public String getCodigoTipoDocLegal(String tipoDocumentoIdentidad){
		return mantenimientoMAEClienteDAO.getCodigoTipoDocLegal(tipoDocumentoIdentidad);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidEstadoCivil(java.lang.String)
	 */
	public Long getOidEstadoCivil(String codigoEstadoCivil) {
		return mantenimientoMAEClienteDAO.getOidEstadoCivil(codigoEstadoCivil);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidGradoInstruccion(java.lang.String)
	 */
	public Long getOidGradoInstruccion(String codigoGradoInstruccion) {
		return mantenimientoMAEClienteDAO.getOidGradoInstruccion(codigoGradoInstruccion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidNacionalidad(java.lang.String)
	 */
	public Long getOidNacionalidad(String codigoNacionalidad) {
		return mantenimientoMAEClienteDAO.getOidNacionalidad(codigoNacionalidad);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidTratamiento(java.lang.String)
	 */
	public Long getOidTratamiento(String codigoTratamiento) {
		return mantenimientoMAEClienteDAO.getOidTratamiento(codigoTratamiento);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidTipoCliente(java.lang.String)
	 */
	public Long getOidTipoCliente(String codigoTipoCliente) {
		return mantenimientoMAEClienteDAO.getOidTipoCliente(codigoTipoCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidSubTipoCliente(java.lang.String, java.lang.String)
	 */
	public Long getOidSubTipoCliente(String oidTipoCliente, String codigoSubTipoCliente) {
		return mantenimientoMAEClienteDAO.getOidSubTipoCliente(oidTipoCliente, codigoSubTipoCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getOidTipoDocumento(java.lang.String, java.lang.String)
	 */
	public String getOidTipoDocumento(String oidPais, String tipoDocumento) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("tipoDocumento", tipoDocumento);
		return mantenimientoMAEClienteDAO.getOidTipoDocumento(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteZona(java.lang.String, java.lang.String)
	 */
	public String getExisteZona(String oidPais, String codigoZona) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("codigoZona", codigoZona);
		return mantenimientoMAEClienteDAO.getExisteZona(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteTerritorio(java.lang.String, java.lang.String)
	 */
	public String getExisteTerritorio(String oidPais, String codigoTerritorio) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("codigoTerritorio", codigoTerritorio);
		return mantenimientoMAEClienteDAO.getExisteTerritorio(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getLongitudTipoDocumento(java.lang.String, java.lang.String)
	 */
	public String getLongitudTipoDocumento(String oidPais, String oidTipoDocumento) {
		return mantenimientoMAEClienteDAO.getLongitudTipoDocumento(oidPais, oidTipoDocumento);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getCodigoPeriodoByOidPeriodo(java.lang.String)
	 */
	public String getCodigoPeriodoByOidPeriodo(String oidPeriodo) {
		return mantenimientoMAEClienteDAO.getCodigoPeriodoByOidPeriodo(oidPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteClienteCUB(java.lang.String)
	 */
	public String getExisteClienteCUB(String codigoCUB) {
		return mantenimientoMAEClienteDAO.getExisteClienteCUB(codigoCUB);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteClienteCUB(java.lang.String,java.lang.String)
	 */
	public String getValorByPaisAndTipo(String codigoPais, String tipoValidacion) {

		return mantenimientoMAEClienteDAO.getValorByPaisAndTipo(codigoPais, tipoValidacion);
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateDireccionClienteHiperConsulta(biz.belcorp.ssicc.spusicc.mae.model.Cliente)
	 */
	public void updateDireccionClienteHiperConsulta(Cliente cliente) throws Exception {
		//actualizamos direccion
		updateDireccionCliente(cliente);
				
		//actualizamos unidad administrativa
		updateUnidadAdministrativaCliente(cliente);		

		//Actualizamos datos de la Ejecutiva
		if(cliente.isEsEjecutiva()) {
			Map params = new HashMap();
			params.put("codigoPais", cliente.getCodigoPais());
			params.put("oidCliente", cliente.getOid().toString());
			
			mantenimientoMAEClienteDAO.executeActualizacionEjecutiva(params);
		}
		
		mantenimientoMAEClienteDAO.executeInsercionHistoricoDatos(cliente.getClienteHistoricoDatos());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateHistoricoDatosCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteHistoricoDatos)
	 */
	public void updateHistoricoDatosCliente(ClienteHistoricoDatos clienteHistoricoDatos) throws Exception {
		if(StringUtils.isEmpty(clienteHistoricoDatos.getCodigoCliente()))
			throw new Exception("Tiene que ingresar Codigo Cliente");
		
		if(StringUtils.isEmpty(clienteHistoricoDatos.getCodigoPais()))
			throw new Exception("Tiene que ingresar Codigo Pais");
		
		if(StringUtils.isEmpty(clienteHistoricoDatos.getCodigoUsuario()))
			throw new Exception("Tiene que ingresar Codigo Usuario");
		
		if(StringUtils.isEmpty(clienteHistoricoDatos.getIndicadorOrigen()))
			throw new Exception("Tiene que ingresar Indicador Origen");
		
		mantenimientoMAEClienteDAO.executeInsercionHistoricoDatos(clienteHistoricoDatos);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getEsEjecutiva(java.lang.String)
	 */
	public boolean esEjecutiva(String oidCliente) {
		return mantenimientoMAEClienteDAO.esEjecutiva(oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getCodigoRegionySeccion(java.lang.String, java.lang.String)
	 */
	public Map getCodigoRegionySeccion(String codigoZona, String codigoTerritorio) {
		return  mantenimientoMAEClienteDAO.getCodigoRegionySeccion(codigoZona, codigoTerritorio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteCliente(java.util.Map)
	 */
	public String getExisteCliente(Map criteria) {
		return mantenimientoMAEClienteDAO.getExisteCliente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#obtenerDatosCambioUA(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public Map obtenerDatosCambioUA(String codigoPais, String codigoMarca, String codigoCanal, String oidCliente, 
									String codigoZonaOrigen, String codigoZonaDestino) {
		log.debug("obtenerDatosCambioUA: oidCliente :"+ oidCliente+ ", codigoZonaOrigen :" + codigoZonaOrigen
							+ " codigoZonaDestino: " + codigoZonaDestino);
		Map resultados = new HashMap();

		//datos del cliente
		Base basePeriodoRegionZonaSiguiente = null;

		String oidPeriodo = "";
		String codigoPeriodo = "";
		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoMarca", codigoMarca);
		criteria.put("codigoCanal", codigoCanal);

		//RECUPERAMOS LOS PERIODOS VIGENTES
		LabelValue[] periodos = getPeriodosVigentesByPaisMarcaCanal(criteria);

		boolean regionZonaOrigenCerro = true;
		boolean regionZonaDestinoCerro = true;
		String mostrarPedidoExtemporaneo = Constants.NO;
		String mostrarMensajeCambioPeriodoVigente = Constants.NO;
		String indicadorPasoPedido = Constants.NO;
		String requiereGenerarEstatus = Constants.NO;

		//VERIFICAMOS SI HUBO CIERRE PARA ZONA Y REGION ORIGEN
		for(int i=0; i < periodos.length; i++) {
			LabelValue periodo = periodos[i];

			criteria.put("codigoZona", codigoZonaOrigen);
			criteria.put("codigoPeriodo", periodo.getLabel());
			boolean esRegionCerrada = esRegionCerradaxZona(criteria);

			if(!esRegionCerrada) {
				boolean esZonaCerrada = esZonaCerrada(criteria);

				if(!esZonaCerrada) {
					log.debug("(Periodo sin cierre de region/zona ORIGEN) :" + periodo.getLabel());
					oidPeriodo = periodo.getValue();
					regionZonaOrigenCerro = false;
					break;
				}	
			} 	
		}

		//VERIFICAMOS SI HUBO CIERRE PARA ZONA Y REGION DESTINO
		for(int i=0; i < periodos.length; i++) {
			LabelValue periodo = periodos[i];

			criteria.put("codigoZona", codigoZonaDestino);
			criteria.put("codigoPeriodo", periodo.getLabel());
			boolean esRegionCerrada = esRegionCerradaxZona(criteria);

			if(!esRegionCerrada) {
				boolean esZonaCerrada = esZonaCerrada(criteria);

				if(!esZonaCerrada) {
					basePeriodoRegionZonaSiguiente = new Base();
					basePeriodoRegionZonaSiguiente.setCodigo(periodo.getValue());
					basePeriodoRegionZonaSiguiente.setDescripcion(periodo.getLabel());
	
					log.debug("(Periodo sin cierre de region/zona DESTINO) :" + periodo.getLabel());
					oidPeriodo = periodo.getValue();
					regionZonaDestinoCerro = false;
					break;
				}	
			} 
		}

		log.debug("regionZonaOrigenCerro: " + regionZonaOrigenCerro);
		log.debug("regionZonaDestinoCerro: " + regionZonaDestinoCerro);

		//VERIFICAMOS SI LA CONSULTORA PASO PEDIDOS EN PERIODOS VIGENTES
		String[] periodoVig = new String [periodos.length];
		for(int i=0;i<periodos.length;i++) {
			periodoVig[i] = periodos[i].getValue();
		}
		criteria.put("oidCliente", oidCliente);
		criteria.put("listPeriodos", periodoVig); 
		boolean pasoPedido = esClienteHaFacturadoPeriodos(criteria);
		log.debug("consultoraPasoPedido PeriodosVigentes: " + pasoPedido);
		
		if(pasoPedido)
			indicadorPasoPedido = Constants.SI;


		if(!regionZonaOrigenCerro) { //REGION / ZONA ORIGEN NO CERRO
			log.debug("REGION / ZONA ORIGEN NO CERRO");
			
			if(!regionZonaDestinoCerro) { //REGION / ZONA DESTINO NO CERRO
	
				if(!pasoPedido) { //NO PASO PEDIDO
					log.debug("REGION / ZONA DESTINO NO CERRO, NO PASO PEDIDO");
		
					//Periodo Inicio es el menor de los vigentes.
					oidPeriodo = periodos[0].getValue();
					codigoPeriodo = periodos[0].getLabel();
		
				} else { //PASO PEDIDO
					log.debug("REGION / ZONA DESTINO NO CERRO, PASO PEDIDO");
		
					Base basePeriodoFacturadoSiguiente = null;
					String ultimoPeriodoPedido = getUltimoPeriodoFacturado(oidCliente);
					log.debug("Oid Ultimo Periodo Facturado : " + ultimoPeriodoPedido);
	
					if(ultimoPeriodoPedido != null) {
						criteria.remove("codigoPeriodo");
						criteria.put("oidPeriodo", ultimoPeriodoPedido);
						basePeriodoFacturadoSiguiente = getSiguientePeriodo(criteria);
					}
	
					//Periodo Inicio es el periodo siguiente al ultimo que la consultora Paso Pedido
					oidPeriodo = basePeriodoFacturadoSiguiente.getCodigo();
					codigoPeriodo = basePeriodoFacturadoSiguiente.getDescripcion();
					
					mostrarMensajeCambioPeriodoVigente = Constants.SI;
				}
	
			} else { //REGION / ZONA DESTINO CERRO
	
				if(!pasoPedido) { //NO PASO PEDIDO
					log.debug("REGION / ZONA DESTINO CERRO, NO PASO PEDIDO");
		
					//Periodo Inicio es el periodo siguiente al ultimo cierre
					basePeriodoRegionZonaSiguiente = getSiguientePeriodo(criteria);
					
					oidPeriodo = basePeriodoRegionZonaSiguiente.getCodigo();
					codigoPeriodo = basePeriodoRegionZonaSiguiente.getDescripcion();
					
					mostrarPedidoExtemporaneo = Constants.SI;
		
				} else { //PASO PEDIDO
					log.debug("REGION / ZONA DESTINO CERRO, PASO PEDIDO");
		
					Base basePeriodoFacturadoSiguiente = null;
					String ultimoPeriodoPedido = getUltimoPeriodoFacturado(oidCliente);
					log.debug("Oid Ultimo Periodo Facturado : " + ultimoPeriodoPedido);
	
					if(ultimoPeriodoPedido != null) {
						criteria.remove("codigoPeriodo");
						criteria.put("oidPeriodo", ultimoPeriodoPedido);
						basePeriodoFacturadoSiguiente = getSiguientePeriodo(criteria);
					}
	
					//Periodo Inicio es el periodo siguiente al ultimo que la consultora Paso Pedido
					oidPeriodo = basePeriodoFacturadoSiguiente.getCodigo();
					codigoPeriodo = basePeriodoFacturadoSiguiente.getDescripcion();
					
					mostrarMensajeCambioPeriodoVigente = Constants.SI;
	
				}
			} 
			
		} else { //REGION / ZONA ORIGEN CERRO
			
			log.debug("REGION / ZONA ORIGEN CERRO");
			
			if(!regionZonaDestinoCerro) { //REGION / ZONA DESTINO NO CERRO
	
				if(!pasoPedido) { //NO PASO PEDIDO
					log.debug("REGION / ZONA DESTINO NO CERRO, NO PASO PEDIDO");
		
					//Periodo Inicio es el menor de los vigentes.
					oidPeriodo = periodos[0].getValue();
					codigoPeriodo = periodos[0].getLabel();
		
				} else { //PASO PEDIDO
					log.debug("REGION / ZONA DESTINO NO CERRO, PASO PEDIDO");
		
					Base basePeriodoFacturadoSiguiente = null;
					String ultimoPeriodoPedido = getUltimoPeriodoFacturado(oidCliente);
					log.debug("Oid Ultimo Periodo Facturado : " + ultimoPeriodoPedido);
	
					if(ultimoPeriodoPedido != null) {
						criteria.remove("codigoPeriodo");
						criteria.put("oidPeriodo", ultimoPeriodoPedido);
						basePeriodoFacturadoSiguiente = getSiguientePeriodo(criteria);
					}
	
					//Periodo Inicio es el periodo siguiente al ultimo que la consultora Paso Pedido
					oidPeriodo = basePeriodoFacturadoSiguiente.getCodigo();
					codigoPeriodo = basePeriodoFacturadoSiguiente.getDescripcion();
					
					mostrarMensajeCambioPeriodoVigente = Constants.SI;
				}
	
			} else { //REGION / ZONA DESTINO CERRO
	
				if(!pasoPedido) { //NO PASO PEDIDO
					log.debug("REGION / ZONA DESTINO CERRO, NO PASO PEDIDO");
		
					basePeriodoRegionZonaSiguiente = getSiguientePeriodo(criteria);
					
					//Periodo Inicio es el periodo siguiente al ultimo cierre
					oidPeriodo = basePeriodoRegionZonaSiguiente.getCodigo();
					codigoPeriodo = basePeriodoRegionZonaSiguiente.getDescripcion();
					
					mostrarPedidoExtemporaneo = Constants.SI;
		
				} else { //PASO PEDIDO
					log.debug("REGION / ZONA DESTINO CERRO, PASO PEDIDO");
		
					Base basePeriodoFacturadoSiguiente = null;
					String ultimoPeriodoPedido = getUltimoPeriodoFacturado(oidCliente);
					log.debug("Oid Ultimo Periodo Facturado : " + ultimoPeriodoPedido);
	
					if(ultimoPeriodoPedido != null) {
						criteria.remove("codigoPeriodo");
						criteria.put("oidPeriodo", ultimoPeriodoPedido);
						basePeriodoFacturadoSiguiente = getSiguientePeriodo(criteria);
					}
	
					//Periodo Inicio es el periodo siguiente al ultimo que la consultora Paso Pedido
					oidPeriodo = basePeriodoFacturadoSiguiente.getCodigo();
					codigoPeriodo = basePeriodoFacturadoSiguiente.getDescripcion();
					
					mostrarMensajeCambioPeriodoVigente = Constants.SI;
	
				}
			}
			
		}

		if(!regionZonaOrigenCerro && regionZonaDestinoCerro && !pasoPedido) { 
			boolean esPeriodoVigente = false;
			
			for(int i=0;i<periodoVig.length;i++) {
				if(periodoVig[i].equalsIgnoreCase(oidPeriodo)) {
					esPeriodoVigente = true;
					break;
				}	
			}
			
			if(esPeriodoVigente)
				requiereGenerarEstatus = Constants.SI;
		}	
		
		resultados.put("oidPeriodo", oidPeriodo);
		resultados.put("codigoPeriodo", codigoPeriodo);
		resultados.put("mostrarPedidoExtemporaneo", mostrarPedidoExtemporaneo);
		resultados.put("mostrarMensajeCambioPeriodoVigente", mostrarMensajeCambioPeriodoVigente);
		resultados.put("indicadorPasoPedido", indicadorPasoPedido);
		resultados.put("requiereGenerarEstatus", requiereGenerarEstatus);
		
		return resultados;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getAccesosTiposAreaByUsuario(java.lang.String)
	 */
	public List getAccesosTiposAreaByPais(String pais) {
		return mantenimientoMAEClienteDAO.getAccesosTiposAreaByPais(pais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#existePedidosRedifinirVigenciaUA(java.util.Map)
	 */
	public boolean existePedidosRedifinirVigenciaUA(Map criteria) {
		return mantenimientoMAEClienteDAO.existePedidosRedifinirVigenciaUA(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#executeRedifinirVigenciaUA(java.util.Map)
	 */
	public void executeRedifinirVigenciaUA(Map params) {
		mantenimientoMAEClienteDAO.executeRedifinirVigenciaUA(params);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getObtenerBuscarConsultora(java.util.Map)
	 */
	public List getObtenerBuscarConsultora(Map criteria) {
		return mantenimientoMAEClienteDAO.getObtenerBuscarConsultora(criteria);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getObtenerDireccionesConsultora(java.util.Map)
	 */
	public List getObtenerDireccionesConsultora(Map criteria) {
		return mantenimientoMAEClienteDAO.getObtenerDireccionesConsultora(criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getDocumentosConsultora(java.util.Map)
	 */
	public List getDocumentosConsultora(Map docParmas) {
		return mantenimientoMAEClienteDAO.getDocumentosConsultora(docParmas);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteLider(java.util.Map)
	 */
	public Integer getExisteLider(Map criteria) {
		return this.mantenimientoMAEClienteDAO.getExisteLider(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertLider(java.util.Map)
	 */
	public void insertLider(Map criteria) {
		 this.mantenimientoMAEClienteDAO.insertLider(criteria);
		 return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExistePagoLider(java.util.Map)
	 */
	public Integer getExistePagoLider(Map criteria) {
		return this.mantenimientoMAEClienteDAO.getExistePagoLider(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updatePagoLiderContabilizarPago(java.util.Map)
	 */
	public void updatePagoLiderContabilizarPago(Map criteria) {
		this.mantenimientoMAEClienteDAO.updatePagoLiderContabilizarPago(criteria);
		return;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#updatePagoLiderContabilizarPago(java.util.Map)
	 */
	public void updatePagoLiderContabilizarPagoSinMonto(Map criteria) {
		this.mantenimientoMAEClienteDAO.updatePagoLiderContabilizarPagoSinMonto(criteria);
		return;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getPeriodosRetiradas(java.lang.String)
	 */
	public String getPeriodosRetiradas(String codigoPais) {
		return mantenimientoMAEClienteDAO.getPeriodosRetiradas(codigoPais);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updatePeriodosRetiradas(java.util.Map)
	 */
	public void updatePeriodosRetiradas(Map params) {
		mantenimientoMAEClienteDAO.updatePeriodosRetiradas(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExcencionesFletesByCriteria(java.util.Map)
	 */
	public List getExcencionesFletesByCriteria(Map params) {
		return mantenimientoMAEClienteDAO.getExcencionesFletesByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExcencionFlete(java.lang.String)
	 */
	public ExcencionFlete getExcencionFlete(String id) {
		return mantenimientoMAEClienteDAO.getExcencionFlete(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertExcencionFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcencionFlete(ExcencionFlete excencionFlete, Usuario usuario) {
		mantenimientoMAEClienteDAO.insertExcencionFlete(excencionFlete, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateExcencionFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateExcencionFlete(ExcencionFlete excencionFlete, Usuario usuario) {
		mantenimientoMAEClienteDAO.updateExcencionFlete(excencionFlete, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExcencionesSobreFletesByCriteria(java.util.Map)
	 */
	public List getExcencionesSobreFletesByCriteria(Map params) {
		return mantenimientoMAEClienteDAO.getExcencionesSobreFletesByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExcencionSobreFlete(java.lang.String)
	 */
	public ExcencionSobreFlete getExcencionSobreFlete(String id) {
		return mantenimientoMAEClienteDAO.getExcencionSobreFlete(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#insertExcencionSobreFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionSobreFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcencionSobreFlete(
			ExcencionSobreFlete excencionSobreFlete, Usuario usuario) {
		mantenimientoMAEClienteDAO.insertExcencionSobreFlete(excencionSobreFlete, usuario);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updateExcencionSobreFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionSobreFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateExcencionSobreFlete(
			ExcencionSobreFlete excencionSobreFlete, Usuario usuario) {
		mantenimientoMAEClienteDAO.updateExcencionSobreFlete(excencionSobreFlete, usuario);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#validaClientePedido(java.util.Map)
	 */
	public boolean validaClientePedido(Map params){
		
		if(!mantenimientoMAEClienteDAO.getBloqueoClientePreFacturacion(params).equals(Constants.NUMERO_CERO)){
			if(!mantenimientoMAEClienteDAO.getClientePedido(params).equals(Constants.NUMERO_CERO)){
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#validaIngresoTelefono(java.util.Map)
	 */
	public boolean validaIngresoTelefono(Map criteria) {
		// TODO Auto-generated method stub
		return StringUtils.equalsIgnoreCase(mantenimientoMAEClienteDAO.getValidaIngresoTelefono(criteria), Constants.NUMERO_CERO);
	}
	

	public void deletePrimerContactoHijaDupla(ClienteSubTipo clienteSubTipo) {
		mantenimientoMAEClienteDAO.deletePrimerContactoHijaDupla(clienteSubTipo);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getValidaDocumentoIdentidad(java.util.Map)
	 */
	public String getValidaDocumentoIdentidad(Map criteria) {
		return mantenimientoMAEClienteDAO.getValidaDocumentoIdentidad(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#getExisteCodigoProveedor(java.util.Map)
	 */
	public String getExisteCodigoProveedor(Map criteria) {
		return mantenimientoMAEClienteDAO.getExisteCodigoProveedor(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.service.MantenimientoMAEClienteService#updatePagoLiderRegistarLider(java.util.Map)
	 */
	public void updatePagoLiderRegistarLider(Map criteria) {
		mantenimientoMAEClienteDAO.updatePagoLiderRegistarLider(criteria);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getSubTiposDocumentoIdentidad(java.util.Map)
	 */
	public List getSubTiposDocumentoIdentidad(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoMAEClienteDAO.getSubTiposDocumentoIdentidad(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getMostrarSubTipoDocumentoIdentidad(java.util.Map)
	 */
	public boolean getMostrarSubTipoDocumentoIdentidad(Map criteria) {
		boolean flag = true;
		
		if(StringUtils.equalsIgnoreCase(mantenimientoMAEClienteDAO.getMostrarSubTipoDocumentoIdentidad(criteria), Constants.NUMERO_CERO))
		{	
			flag = false;
		}
		return flag;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#updateDireccionCargaMasiva(biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente)
	 */
	public void updateDireccionCargaMasiva(Cliente cliente) throws Exception {
		//actualizamos direccion
		updateDireccionCliente(cliente);
				
		//actualizamos unidad administrativa
		updateUnidadAdministrativaCliente(cliente);		
		
		//actualizamos en auditoria de cambios de MAE
		mantenimientoMAEClienteDAO.executeInsercionHistoricoDatos(cliente.getClienteHistoricoDatos());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getEntidadBancoMaeList(java.util.Map)
	 */

	public List getEntidadBancoMaeList(Map criteria) {
		return mantenimientoMAEClienteDAO.getEntidadBancoMaeList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getMostrarBancoCuentaCorriente(java.util.Map)
	 */
	
	public String getMostrarBancoCuentaCorriente(Map criteria) {
		return mantenimientoMAEClienteDAO.getMostrarBancoCuentaCorriente(criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#updateClienteLider(java.util.Map)
	 */
	public void updateClienteLider(Map criteria) {
		mantenimientoMAEClienteDAO.updateClienteLider(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getCodigosTerritorialCorresponde(java.util.Map)
	 */
	@Override
	public List getCodigosTerritorialCorresponde(Map criteria) {
		return mantenimientoMAEClienteDAO.getCodigosTerritorialCorresponde(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getBancos(java.util.Map)
	 */
	@Override
	public List getBancos(Map criteria) {
		return mantenimientoMAEClienteDAO.getBancos(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getTiposCuenta(java.util.Map)
	 */
	@Override
	public List getTiposCuenta(Map criteria) {
		return mantenimientoMAEClienteDAO.getTiposCuenta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getMostrarTipoCuentaCuentaCorriente(java.util.Map)
	 */
	@Override
	public String getMostrarTipoCuentaCuentaCorriente(Map criteria) {
		return mantenimientoMAEClienteDAO.getMostrarTipoCuentaCuentaCorriente(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService#getListDireccionClienteCamposAdicionales(java.lang.String)
	 */
	public List getListDireccionClienteCamposAdicionales(String oidCliente) {
		return mantenimientoMAEClienteDAO.getListDireccionClienteCamposAdicionales(oidCliente);
	}
}
