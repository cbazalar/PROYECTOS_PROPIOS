/*
 * Created on 07/11/2005 02:10:03 PM
 * biz.belcorp.privilege.service.impl.InterfazPrivilegeServiceImpl
 */
package biz.belcorp.ssicc.service.scdf.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.PaisDAO;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.ArchivoDAO;
import biz.belcorp.ssicc.dao.scdf.BitacoraDAO;
import biz.belcorp.ssicc.dao.scdf.ClienteDAO;
import biz.belcorp.ssicc.dao.scdf.ColumnaDAO;
import biz.belcorp.ssicc.dao.scdf.ConsultoraDAO;
import biz.belcorp.ssicc.dao.scdf.ControlFacturacionDAO;
import biz.belcorp.ssicc.dao.scdf.CuentaClienteDAO;
import biz.belcorp.ssicc.dao.scdf.PremioDAO;
import biz.belcorp.ssicc.dao.scdf.ProductoDAO;
import biz.belcorp.ssicc.dao.scdf.RegionDAO;
import biz.belcorp.ssicc.dao.scdf.StickerDAO;
import biz.belcorp.ssicc.dao.scdf.SubgerenciaDAO;
import biz.belcorp.ssicc.dao.scdf.TarjetaDAO;
import biz.belcorp.ssicc.dao.scdf.ZonaDAO;
import biz.belcorp.ssicc.dao.scdf.model.Archivo;
import biz.belcorp.ssicc.dao.scdf.model.Bitacora;
import biz.belcorp.ssicc.dao.scdf.model.Cliente;
import biz.belcorp.ssicc.dao.scdf.model.ClientePK;
import biz.belcorp.ssicc.dao.scdf.model.Columna;
import biz.belcorp.ssicc.dao.scdf.model.ControlFacturacion;
import biz.belcorp.ssicc.dao.scdf.model.CuentaCliente;
import biz.belcorp.ssicc.dao.scdf.model.Premio;
import biz.belcorp.ssicc.dao.scdf.model.Tarjeta;
import biz.belcorp.ssicc.dao.scdf.model.TarjetaPK;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scdf.InterfazPrivilegeService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazPrivilegeServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("scdf.interfazPrivilegeService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPrivilegeServiceImpl extends BaseService implements
        InterfazPrivilegeService {

	@Resource(name="scdf.archivoDAO")
    private ArchivoDAO archivoDAO;

	@Resource(name="scdf.bitacoraDAO")
	private BitacoraDAO bitacoraDAO;

	@Resource(name="scdf.clienteDAO")
	private ClienteDAO clienteDAO;

	@Resource(name="scdf.columnaDAO")
	private ColumnaDAO columnaDAO;

	@Resource(name="scdf.consultoraDAO")
	private ConsultoraDAO consultoraDAO;

	@Resource(name="scdf.controlFacturacionDAO")
	private ControlFacturacionDAO controlFacturacionDAO;

	@Resource(name="scdf.cuentaClienteDAO")
	private CuentaClienteDAO cuentaClienteDAO;

	@Resource(name="paisDAO")
	private PaisDAO paisDAO;

	@Resource(name="scdf.premioDAO")
	private PremioDAO premioDAO;

	@Resource(name="scdf.productoDAO")
	private ProductoDAO productoDAO;

	@Resource(name="scdf.regionDAO")
	private RegionDAO regionDAO;

	@Resource(name="scdf.stickerDAO")
	private StickerDAO stickerDAO;

	@Resource(name="scdf.subgerenciaDAO")
	private SubgerenciaDAO subgerenciaDAO;

	@Resource(name="scdf.tarjetaDAO")
	private TarjetaDAO tarjetaDAO;

	@Resource(name="scdf.zonaDAO")
	private ZonaDAO zonaDAO;

	
        /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.InterfazPrivilegeService#executeEliminarInformacionPrivilege(java.lang.String)
     */
    public void executeEliminarInformacionPrivilege(String codigoPais) {
        premioDAO.removePremioByPais(codigoPais);
        tarjetaDAO.removeTarjetaByPais(codigoPais);
        cuentaClienteDAO.removeCuentaClienteByPais(codigoPais);
        clienteDAO.removeClienteByPais(codigoPais);
    }

    /*
     * GFACTOR.TXT : Para el factor del BAS_CONTR * GFICINSC.TXT : Informacin
     * para BAS_CLIEN * GTARPTOS.TXT : Informacin para BAS_CUENT_CLIEN Y
     * BAS_TARJE * GPREDESP.TXT : Informacin para BAS_PREMI *
     */
    public synchronized List executeCargarArchivosPrivilege(Usuario usuario,
            Pais pais) {
        ArrayList listaBitacoras = new ArrayList();
        listaBitacoras.add(cargarFactor(usuario, pais));
        listaBitacoras.add(cargarFichasInscripcion(usuario, pais));
        listaBitacoras.add(cargarTarjetasPuntos(usuario, pais));
        listaBitacoras.add(cargarPremiosDespacho(usuario, pais));

        return listaBitacoras;
    }

    /*
     * Generamos diversos archivos, obteniendo la informacion de las Tablas de
     * SSiCC. Luego el resultado de cada carga genera una Bitacora, la cual es
     * registrada en la BD con el Usuario que se le pasa por parametro y enviada
     * en una Lista.
     */
    public List executeDescargarArchivosPrivilege(Usuario usuario, Pais pais) {
        ArrayList listaBitacoras = new ArrayList();
        listaBitacoras.add(descargarControlFacturacion(usuario, pais));
        listaBitacoras.add(descargarSubgerencias(usuario, pais));
        listaBitacoras.add(descargarRegiones(usuario, pais));
        listaBitacoras.add(descargarZonas(usuario, pais));
        listaBitacoras.add(descargarConsultoras(usuario, pais));
        listaBitacoras.add(descargarProductos(usuario, pais));
        listaBitacoras.add(descargarStickers(usuario, pais));
        listaBitacoras.add(descargarFichasInscripcion(usuario, pais));
        listaBitacoras.add(descargarTarjetasPuntos(usuario, pais));
        listaBitacoras.add(descargarPremios(usuario, pais));
        return listaBitacoras;
    }

    private synchronized Bitacora cargarFactor(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarFactor'");
        }
        int contNumLinea = 0;
        int contRecorre = 0;
        int numLinError = 0;
        String ruta;
        String mensajeExcepcion = "";
        Archivo archivo = new Archivo();
        archivo.setCodigoPais(pais.getCodigo());
        archivo.setCodigoArchivo(Constants.INTERFAZ_DESCARGA_ARCHIVO_FACTORES);
        archivo = archivoDAO.getArchivo(archivo);
        String nombreArchivo = archivo.getNombreArchivo()
                + archivo.getExtension();
        ruta = (StringUtils.isEmpty(archivo.getRutaTemporal()) ? archivo
                .getRutaDestino() : archivo.getRutaTemporal())
                + nombreArchivo;
        Bitacora bitacora = new Bitacora();
        try {
            File file = new File(ruta);
            nombreArchivo = file.getName();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                contNumLinea++;
            }
            fr = null;
            br = null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = StringUtils.trimToNull(br.readLine())) != null) {
                ControlFacturacion controlFacturacion = new ControlFacturacion();
                contRecorre++;
                controlFacturacion.setCodigoPais(pais.getCodigo());
                controlFacturacion = (ControlFacturacion) descomponeLinea(
                        linea, controlFacturacion, archivo);
                controlFacturacionDAO.updateControlFacturacion(
                        controlFacturacion, usuario);
                controlFacturacion = null;
            }
            fr.close();
            br.close();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            numLinError = (contRecorre > 0) ? contRecorre : -1;
            mensajeExcepcion = e.getMessage();
        } finally {
            generaBitacora(bitacora, usuario, pais.getCodigo(), numLinError,
                    nombreArchivo, mensajeExcepcion, contNumLinea);
        }
        return bitacora;
    }

    private synchronized Bitacora cargarFichasInscripcion(Usuario usuario,
            Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarFichaInscripcion'");
        }
        int contNumLinea = 0;
        int contRecorre = 0;
        int numLinError = 0;
        String ruta;
        String mensajeExcepcion = "";
        Archivo archivo = new Archivo();
        archivo.setCodigoPais(pais.getCodigo());
        archivo.setCodigoArchivo(Constants.INTERFAZ_DESCARGA_ARCHIVO_FICHAS);
        archivo = archivoDAO.getArchivo(archivo);
        String nombreArchivo = archivo.getNombreArchivo()
                + archivo.getExtension();
        ruta = (StringUtils.isEmpty(archivo.getRutaTemporal()) ? archivo
                .getRutaDestino() : archivo.getRutaTemporal())
                + nombreArchivo;
        Bitacora bitacora = new Bitacora();
        try {
            File file = new File(ruta);
            nombreArchivo = file.getName();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                contNumLinea++;
            }
            fr = null;
            br = null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = StringUtils.trimToNull(br.readLine())) != null) {
                Cliente cliente = new Cliente();
                contRecorre++;
                cliente = (Cliente) descomponeLinea(linea, cliente, archivo);
                cliente.setCodigoPais(StringUtils.trim(pais.getCodigo()));
                cliente.setStatus(Constants.STATUS_CLIENTE_FICHAS);
                clienteDAO.insertCliente(cliente, usuario);
                cliente = null;
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            numLinError = (contRecorre > 0) ? contRecorre : -1;
            mensajeExcepcion = e.getMessage();
        } finally {
            generaBitacora(bitacora, usuario, pais.getCodigo(), numLinError,
                    nombreArchivo, mensajeExcepcion, contNumLinea);
        }
        return bitacora;
    }

    private synchronized Bitacora cargarTarjetasPuntos(Usuario usuario,
            Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarTarjetaPuntos'");
        }
        int contNumLinea = 0;
        int contRecorre = 0;
        int numLinError = 0;
        String ruta;
        String mensajeExcepcion = "";
        Archivo archivo = new Archivo();
        archivo.setCodigoPais(pais.getCodigo());
        archivo.setCodigoArchivo(Constants.INTERFAZ_DESCARGA_ARCHIVO_TARJETAS);
        archivo = archivoDAO.getArchivo(archivo);
        String nombreArchivo = archivo.getNombreArchivo()
                + archivo.getExtension();
        ruta = (StringUtils.isEmpty(archivo.getRutaTemporal()) ? archivo
                .getRutaDestino() : archivo.getRutaTemporal())
                + nombreArchivo;
        Bitacora bitacora = new Bitacora();
        try {
            File file = new File(ruta);
            nombreArchivo = file.getName();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                contNumLinea++;
            }
            fr = null;
            br = null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = StringUtils.trimToNull(br.readLine())) != null) {
                contRecorre++;

                CuentaCliente cuentaCliente = new CuentaCliente();
                cuentaCliente = (CuentaCliente) descomponeLinea(linea,
                        cuentaCliente, archivo);
                cuentaCliente.setCodigoPais(pais.getCodigo());
                Tarjeta tarjeta = new Tarjeta();
                tarjeta = (Tarjeta) descomponeLinea(linea, tarjeta, archivo);
                tarjeta.setCodigoPais(pais.getCodigo());
                /**
                 * Si no existe el Cliente lo insertamos y establecemos el
                 * status a T.
                 */
                Cliente cliente = new Cliente();
                cliente.setCodigoPais(pais.getCodigo());
                cliente.setCodigo(cuentaCliente.getCodigoCliente());
                List clientes = clienteDAO.getClientes(cliente);
                if (clientes.size() == 0) {
                    Cliente mapCliente = (Cliente) descomponeLinea(linea,
                            cliente, archivo);
                    cliente.setNombres(mapCliente.getNombres());
                    cliente.setDocumentoIdentidad(mapCliente
                            .getDocumentoIdentidad());
                    cliente.setCodigoConsultora(tarjeta.getCodigoConsultora());
                    cliente.setStatus(Constants.STATUS_CLIENTE_TARJETA);
                    clienteDAO.insertCliente(cliente, usuario);
                }
                CuentaCliente mapCuentaCliente = new CuentaCliente();
                mapCuentaCliente.setCodigoPais(cuentaCliente.getCodigoPais());
                mapCuentaCliente.setCodigoCliente(cuentaCliente
                        .getCodigoCliente());
                List cuentaClientes = cuentaClienteDAO
                        .getCuentaClientes(mapCuentaCliente);
                if (cuentaClientes.size() == 0) {
                    cuentaClienteDAO
                            .insertCuentaCliente(cuentaCliente, usuario);
                }
                Tarjeta mapTarjeta = new Tarjeta();
                mapTarjeta.setCodigoPais(tarjeta.getCodigoPais());
                mapTarjeta.setNumeroTarjeta(tarjeta.getNumeroTarjeta());
                List tarjetas = tarjetaDAO.getTarjetas(mapTarjeta);
                if (tarjetas.size() == 0) {
                    tarjetaDAO.insertTarjeta(tarjeta, usuario);
                }
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            numLinError = (contRecorre > 0) ? contRecorre : -1;
            mensajeExcepcion = e.getMessage();
        } finally {
            generaBitacora(bitacora, usuario, pais.getCodigo(), numLinError,
                    nombreArchivo, mensajeExcepcion, contNumLinea);
        }
        return bitacora;
    }

    private synchronized Bitacora cargarPremiosDespacho(Usuario usuario,
            Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'cargarPremioDespacho'");
        }
        int contNumLinea = 0;
        int contRecorre = 0;
        int numLinError = 0;
        String ruta;
        String mensajeExcepcion = "";
        Archivo archivo = new Archivo();
        archivo.setCodigoPais(pais.getCodigo());
        archivo.setCodigoArchivo(Constants.INTERFAZ_DESCARGA_ARCHIVO_PREMIOS);
        archivo = archivoDAO.getArchivo(archivo);
        String nombreArchivo = archivo.getNombreArchivo()
                + archivo.getExtension();
        ruta = (StringUtils.isEmpty(archivo.getRutaTemporal()) ? archivo
                .getRutaDestino() : archivo.getRutaTemporal())
                + nombreArchivo;
        Bitacora bitacora = new Bitacora();
        try {
            File file = new File(ruta);
            nombreArchivo = file.getName();
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while (br.readLine() != null) {
                contNumLinea++;
            }
            fr = null;
            br = null;
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = StringUtils.trimToNull(br.readLine())) != null) {
                Premio premio = new Premio();
                contRecorre++;
                premio = (Premio) descomponeLinea(linea, premio, archivo);
                premio.setCodigoPais(pais.getCodigo());

                // Buscamos la tarjeta para determinar si es necesario insertar
                // una nueva y su correspondiente cliente
                TarjetaPK tarjetaPK = new TarjetaPK(pais.getCodigo(), premio
                        .getNumeroTarjeta());
                Tarjeta tarjeta = new Tarjeta();

                try {
                    tarjeta = tarjetaDAO.getTarjeta(tarjetaPK);
                } catch (ObjectRetrievalFailureException orfe) {
                    // En caso ocurra la excepcion entonces la tarjeta no existe
                    tarjeta = (Tarjeta) descomponeLinea(linea, tarjeta, archivo);
                    tarjeta.setCodigoPais(pais.getCodigo());

                    // Ahora buscamos al cliente asociado al premio/tarjeta
                    ClientePK clientePK = new ClientePK(pais.getCodigo(),
                            tarjeta.getCodigoCliente());
                    Cliente cliente = new Cliente();

                    try {
                        cliente = clienteDAO.getCliente(clientePK);
                    } catch (ObjectRetrievalFailureException orfe2) {
                        // En caso ocurra la excepcion entonces el cliente no
                        // existe
                        cliente = (Cliente) descomponeLinea(linea, cliente,
                                archivo);
                        cliente.setCodigoPais(pais.getCodigo());
                        cliente.setCodigo(tarjeta.getCodigoCliente());
                        cliente.setStatus(Constants.STATUS_CLIENTE_PREMIO);

                        // Insertamos al cliente
                        clienteDAO.insertCliente(cliente, usuario);
                    }

                    // Insertamos la tarjeta
                    tarjetaDAO.insertTarjeta(tarjeta, usuario);
                }

                premioDAO.insertPremio(premio, usuario);
                premio = null;
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            numLinError = (contRecorre > 0) ? contRecorre : -1;
            mensajeExcepcion = e.getMessage();
        } finally {
            generaBitacora(bitacora, usuario, pais.getCodigo(), numLinError,
                    nombreArchivo, mensajeExcepcion, contNumLinea);
        }
        return bitacora;
    }

    private Bitacora descargarProductos(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarProductos'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_PRODUCTOS;
        List productos = productoDAO.getProductosMapByPais(pais
                .getCodigo());
        return parametrizaArchivo(codigoArchivo, productos, usuario,
                pais);
    }

    private Bitacora descargarConsultoras(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarConsultoras'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_CONSULTORAS;
        List consultoras = consultoraDAO.getConsultorasMapByPais(pais
                .getCodigo());
        return parametrizaArchivo(codigoArchivo, consultoras, usuario,
                pais);
    }

    private Bitacora descargarStickers(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarStickers'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_STICKERS;
        List stickers = stickerDAO.getHistoricoStickersMapByPais(pais
                .getCodigo());
        return parametrizaArchivo(codigoArchivo, stickers, usuario,
                pais);
    }

    private Bitacora descargarFichasInscripcion(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarFichasInscripcion'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_FICHAS;
        Cliente cliente = new Cliente();
        cliente.setCodigoPais(pais.getCodigo());
        cliente.setStatus(Constants.STATUS_CLIENTE_FICHAS);
        List clientes = clienteDAO.getClientesMap(cliente);
        return parametrizaArchivo(codigoArchivo, clientes, usuario,
                pais);
    }

    private Bitacora descargarTarjetasPuntos(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarTarjetasPuntos'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_TARJETAS;
        List tarjetas = tarjetaDAO.getTarjetasMapByPais(pais
                .getCodigo());
        return parametrizaArchivo(codigoArchivo, tarjetas, usuario,
                pais);
    }

    private Bitacora descargarPremios(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarPremios'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_PREMIOS;
        List premios = premioDAO.getPremiosMapByPais(pais.getCodigo());
        return parametrizaArchivo(codigoArchivo, premios, usuario,
                pais);
    }

    private Bitacora descargarControlFacturacion(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarControlFacturacion'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_CONTROL_FACTURACION;
        List controlesFacturacion = controlFacturacionDAO
                .getControlFacturacionMap(pais.getCodigo());
        return parametrizaArchivo(codigoArchivo, controlesFacturacion, usuario,
                pais);
    }

    private Bitacora descargarSubgerencias(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarSubgerencias'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_SUBGERENCIAS;
        List subgerencias = subgerenciaDAO.getSubgerenciaMapByPais(pais
                .getCodigo());
        return parametrizaArchivo(codigoArchivo, subgerencias, usuario,
                pais);
    }

    private Bitacora descargarRegiones(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarRegiones'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_REGIONES;
        List regiones = regionDAO.getRegionMapByPais(pais.getCodigo());
        return parametrizaArchivo(codigoArchivo, regiones, usuario,
                pais);
    }

    private Bitacora descargarZonas(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarZonas'");
        }
        String codigoArchivo = Constants.INTERFAZ_CARGA_ARCHIVO_ZONAS;
        List zonas = zonaDAO.getZonaMapByPais(pais.getCodigo());
        return parametrizaArchivo(codigoArchivo, zonas, usuario,
                pais);
    }

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.scdf.service.InterfazPrivilegeService#executeGeneracionArchivoOCR(biz.belcorp.ssicc.model.Usuario,
     *      biz.belcorp.ssicc.model.Pais)
     */
    public List executeGeneracionArchivoOCR(Usuario usuario, Pais pais) {
        return descargarArchivosOCR(usuario, pais);
    }

    private synchronized List descargarArchivosOCR(Usuario usuario, Pais pais) {
        if (log.isInfoEnabled()) {
            log.info("Dentro del metodo 'descargarArchivosOCR'");
        }
        // Obtenemos la informacin de las cabeceras y detalles de los pedidos
        // para la creacion de los archivos de OCR
        Map criteria = new HashMap();
        ArrayList listaBitacoras = new ArrayList();

        // Creamos los archivos de premios
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("indicadorPremio", Constants.INDICADOR_PREMIO_CANJE);

        List cabeceraOCRPremios = premioDAO.getPremiosCabecera(criteria);
        List detalleOCRPremios = premioDAO.getPremiosDetalle(criteria);
        
        listaBitacoras.add(parametrizaArchivo(Constants.INTERFAZ_DESCARGA_ARCHIVO_OCR_PREMIOS_CABECERA,
                cabeceraOCRPremios, usuario, pais));
        listaBitacoras.add(parametrizaArchivo(Constants.INTERFAZ_DESCARGA_ARCHIVO_OCR_PREMIOS_DETALLE,
                detalleOCRPremios, usuario, pais));

        // Creamos los archivos de oportunidades
        criteria.put("codigoPais", pais.getCodigo());
        criteria.put("indicadorPremio", Constants.INDICADOR_PREMIO_OPORTUNIDAD);

        List cabeceraOCROportunidades = premioDAO.getPremiosCabecera(criteria);
        List detalleOCROportunidades = premioDAO.getPremiosDetalle(criteria);
        listaBitacoras.add(parametrizaArchivo(
                Constants.INTERFAZ_DESCARGA_ARCHIVO_OCR_OPORTUNIDADES_CABECERA,
                cabeceraOCROportunidades, usuario, pais));
        listaBitacoras.add(parametrizaArchivo(
                Constants.INTERFAZ_DESCARGA_ARCHIVO_OCR_OPORTUNIDADES_DETALLE,
                detalleOCROportunidades, usuario, pais));

        return listaBitacoras;
    }

    /**
     * Mtodo que inserta una Bitcora en la BD de acuerdo a los resultados de
     * la Carga o Descarga de archivos. Coloca un mensaje OK, en caso todo haya
     * seguido una ejecucin normal.
     * 
     * @param bitacora
     *            Objeto Bitacora que sera poblado de acuerdo a los resultados
     *            del Procesamiento.
     * @param usuario
     *            Usuario invocador o del Sistema.
     * @param codigoPais
     *            Codigo del Pais del Usuario invocador.
     * @param numLinError
     *            numero de Linea de Error.
     * @param nombreArchivo
     *            Nombre del Archivo que se proceso.
     * @param mensajeExcepcion
     *            En caso exista algun error o fallo, aca se colocara el error y
     *            en que linea del archivo ha ocurrido.
     * @param contNumLinea
     *            Numero de Lineas que contiene el Archivo Procesado.
     */
    private void generaBitacora(Bitacora bitacora, Usuario usuario,
            String codigoPais, int numLinError, String nombreArchivo,
            String mensajeExcepcion, int contNumLinea) {
        try {
            bitacora.setCodigoPais(codigoPais);
            bitacora.setUsuarioProcesa(usuario.getLogin());
            bitacora.setNumeroLineaError((numLinError > 0 ? numLinError : 0));
            bitacora.setNombreArchivo(nombreArchivo);
            bitacora.setComentarios((numLinError != 0) ? "Excepcin ("
                    + mensajeExcepcion + ") en la lnea " + numLinError
                    : Constants.OK_MESSAGE);
            bitacora.setNumeroRegistros(contNumLinea);
            bitacora.setFechaProceso(new Timestamp(System.currentTimeMillis()));
            bitacora.setTipoProceso(Constants.TIPO_PROCESO_CARGA);
            bitacoraDAO.insertBitacora(bitacora);
        } catch (Exception e) {
            log.error("Error en generaBitacora " + e);
        }
    }

    /**
     * El presente mtodo se encarga de generar un archivo a partir de los
     * parametros dados en las tablas PRI_MAEST_ARCHI y PRI_MAEST_COLUM. Aca le
     * podemos indicar si los campos estaran separados por un caracter o tendran
     * una longitud fija, si se completaran con caracteres de relleno, el tipo
     * de dato que se maneje, etc.
     * 
     * @param codigoArchivo
     *            El codigo de Identificacion del Archivo que se encuentra en la
     *            Clase Constants.
     * @param listaSinFormato
     *            Lista de Maps donde los nombres de los campos serviran para
     *            identificar con respecto a los campos de escritura
     * @param usuario
     *            Usuario que se encuentra en el Sistema. Se usa para generar la
     *            Bitacora
     * @param pais
     *            Pais del Usuario del Sistema. Se usa para generar la Bitacora
     * @return Bitacora conteniendo los resultados de la generacion del Archivo.
     */
    private Bitacora parametrizaArchivo(String codigoArchivo,
            List listaSinFormato, Usuario usuario, Pais pais) {
        int contNumLinea = 0;
        int contRecorre = 0;
        int numLinError = 0;
        boolean ajuste = false;
        String mensajeExcepcion = "";
        String nombreArchivo = "";
        String nombreArchivoFisico = "";
        String direccion = "";
        Bitacora bitacora = new Bitacora();
        try {
            Map mapa;
            String separador = "";
            Archivo archivo = new Archivo();
            archivo.setCodigoPais(pais.getCodigo());
            archivo.setCodigoArchivo(codigoArchivo);
            archivo = archivoDAO.getArchivo(archivo);
            nombreArchivo = archivo.getNombreArchivo();
            String ruta = StringUtils.isEmpty(archivo.getRutaTemporal()) ? archivo
                    .getRutaDestino()
                    : archivo.getRutaTemporal();
            direccion = ruta + nombreArchivo + archivo.getExtension();
            if (StringUtils.equalsIgnoreCase(archivo.getIndicadorSufijo(),
                    Constants.SI)) {
                direccion = ruta
                        + nombreArchivo
                        + StringUtils.trimToEmpty(DateFormatUtils.format(
                                new Date(), archivo.getPatronSufijo()))
                        + archivo.getExtension();
            }
            if (StringUtils.equalsIgnoreCase(archivo.getIndicadorSeparador(),
                    Constants.SI)) {
                separador = archivo.getCaracterSeparador();
            }
            if (StringUtils.equalsIgnoreCase(
                    archivo.getIndicadorLongitudFija(), Constants.SI)) {
                ajuste = true;
            }
            File file = new File(direccion);
            nombreArchivoFisico = file.getName();
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            Columna columna = new Columna();
            columna.setCodigoPais(archivo.getCodigoPais());
            columna.setCodigoArchivo(archivo.getCodigoArchivo());
            List lcolumna = columnaDAO.getColumnas(columna);
            int tamc = lcolumna.size();
            Iterator il = listaSinFormato.iterator();
            contNumLinea = listaSinFormato.size();
            while (il.hasNext()) {
                contRecorre++;
                mapa = (Map) il.next();
                int ic = 0;
                StringBuffer escritura = new StringBuffer();
                while (ic < tamc) {
                    columna = (Columna) lcolumna.get(ic);
                    ic++;
                    String cadena = "";
                    Object objeto = null;
                    if (StringUtils.isEmpty(columna.getValorDefecto())) {
                        objeto = MapUtils.getObject(mapa, columna
                                .getNombreColumna());
                    } else {
                        cadena = columna.getValorDefecto();
                    }
                    Date fecha = null;
                    if (log.isDebugEnabled()) {
                        log.debug("MAEST_COLUM - " + columna.getNombreColumna()
                                + " " + objeto);
                    }
                    if (objeto != null) {
                        if (log.isDebugEnabled()) {
                            log.debug(objeto.getClass().getName());
                        }
                        if (objeto instanceof Date) {
                            fecha = (Date) objeto;
                            if (log.isDebugEnabled()) {
                                log.debug("Objeto Obtenido: " + objeto);
                            }
                            if (!StringUtils.isEmpty(columna.getPatronFecha())) {
                                cadena = DateFormatUtils.format(fecha, columna
                                        .getPatronFecha());
                                if (log.isDebugEnabled()) {
                                    log.debug("EN CASO SEA FECHA: " + cadena);
                                }
                            }
                        } else if (objeto instanceof BigDecimal) {
                            cadena = ((BigDecimal) objeto).toString();
                        } else {
                            cadena = (String) objeto;
                        }
                    }
                    if (cadena == null)
                        cadena = "";
                    if (StringUtils.equalsIgnoreCase(columna
                            .getIndicadorRelleno(), Constants.SI)
                            && ajuste) {
                        String relleno = columna.getCaracterRelleno();
                        if (StringUtils.equalsIgnoreCase(columna.getTipoDato(),
                                Constants.TIPO_DATO_NUMERICO)) {
                            int longitud = columna.getLongitudCampo()
                                    + columna.getPrecisionCampo()
                                    + ((columna.getPrecisionCampo() > 0) ? 1
                                            : 0);
                            cadena = StringUtils.leftPad(cadena, longitud,
                                    relleno);
                        } else {
                            int longitud = columna.getLongitudCampo();
                            cadena = StringUtils.rightPad(cadena, longitud,
                                    relleno);
                        }
                    }
                    if (StringUtils.equalsIgnoreCase(columna
                            .getFlagDelimitador(), Constants.SI)) {
                        String delim = StringUtils.stripToEmpty(columna
                                .getCaracterDelimitador());
                        cadena = delim + cadena + delim;
                        log.debug("VALOR DE LA CADENA: " + cadena);
                    }
                    escritura.append(cadena);
                    if (ic < tamc) {
                        escritura.append(separador);
                    }
                }
                bw.write(escritura.toString());
                bw.newLine();
            }
            bw.flush();
            fw.flush();
            bw.close();
            fw.close();
        } catch (Exception e) {
            log.error("Error en parametrizaArchivo " + e.getMessage());
            numLinError = (contRecorre > 0) ? contRecorre : -1;
            mensajeExcepcion = e.getMessage();
        } finally {
            generaBitacora(bitacora, usuario, pais.getCodigo(), numLinError,
                    nombreArchivoFisico, mensajeExcepcion, contNumLinea);
        }
        return bitacora;
    }

    /**
     * Este mtodo se encarga de leer una lnea del archivo para realizar una
     * insercion en la BD, para dicha lectura identifica con la metadata de la
     * tabla PRI_MAEST_COLUM si el archivo esta separado por caracteres o si
     * posee longitud fija.
     * 
     * @param linea
     *            Linea del archivo que se esta leyendo
     *            (BufferedReader.readLine)
     * @param objeto
     *            Objeto que se insertara y al cual se le asignaran los campos
     *            de la linea leida. Dicho objeto sera devuelto por este metodo
     *            debidamente poblado.
     * @param archivo
     *            Metadata acerca del Archivo que se esta leyendo.
     * @return Objeto debidamente poblado.
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Object descomponeLinea(String linea, Object objeto, Archivo archivo)
            throws IllegalAccessException, InvocationTargetException {
        Columna columna = new Columna();
        columna.setCodigoPais(archivo.getCodigoPais());
        columna.setCodigoArchivo(archivo.getCodigoArchivo());
        List lcolumna = columnaDAO.getColumnas(columna);
        int tamc = lcolumna.size();
        int ic = 0;
        if (archivo.getIndicadorSeparador().compareToIgnoreCase(Constants.SI) == 0) {
            String separador = archivo.getCaracterSeparador();
            String[] tokens = StringUtils.splitPreserveAllTokens(linea,
                    separador);
            while (ic < tamc) {
                columna = (Columna) lcolumna.get(ic);
                String cadena = "";
                if (log.isDebugEnabled()) {
                    log.debug("Columna N: " + (ic + 1));
                    log.debug("Columna - Nombre Clase: "
                            + columna.getNombreClase());
                    log.debug("Objeto - Nombre Paquete: "
                            + objeto.getClass().getPackage().getName());
                    log.debug("Objeto - Nombre Clase: "
                            + objeto.getClass().getName());
                }
                String nombreClase = columna.getNombreClase();
                if (nombreClase == null)
                    nombreClase = "";
                if (StringUtils.isBlank(nombreClase)
                        || objeto.getClass().getName().endsWith(nombreClase)) {
                    cadena = tokens[ic];
                    ic++;
                    if (columna.getFlagDelimitador().compareToIgnoreCase(
                            Constants.SI) == 0) {
                        String delimitador = StringUtils.stripToEmpty(columna
                                .getCaracterDelimitador());
                        cadena = StringUtils.strip(cadena, delimitador);
                    }
                    cadena = StringUtils.trimToEmpty(cadena);
                    if (log.isDebugEnabled())
                        log.debug("Valor: " + cadena + " --> Columna: "
                                + columna.getNombreColumna());
                    if (columna.getIndicadorRelleno().compareToIgnoreCase(
                            Constants.SI) == 0) {
                        String relleno = columna.getCaracterRelleno();
                        if (columna.getTipoDato().compareToIgnoreCase(
                                Constants.TIPO_DATO_NUMERICO) == 0) {
                            int longitud = columna.getLongitudCampo()
                                    + columna.getPrecisionCampo()
                                    + ((columna.getPrecisionCampo() > 0) ? 1
                                            : 0);
                            cadena = StringUtils.leftPad(cadena, longitud,
                                    relleno);
                        } else {
                            int longitud = columna.getLongitudCampo();
                            cadena = StringUtils.rightPad(cadena, longitud,
                                    relleno);
                        }
                    }
                    BeanUtils.copyProperty(objeto, columna.getNombreColumna(),
                            cadena);
                } else {
                    ic++;
                }
            }
        } else {
            int ini = 0;
            int fin = 0;
            int var = 0;
            while (ic < tamc) {
                columna = (Columna) lcolumna.get(ic);
                ic++;
                String cadena = "";
                if (log.isDebugEnabled())
                    log.debug("TIPO DE CLASE:" + columna.getNombreClase());
                String nombreClase = columna.getNombreClase();
                if (nombreClase == null)
                    nombreClase = "";
                if (StringUtils.isBlank(nombreClase)
                        || objeto.getClass().getName().endsWith(nombreClase)) {
                    if (columna.getFlagDelimitador().compareToIgnoreCase(
                            Constants.SI) == 0) {
                        var = columna.getCaracterDelimitador().length();
                    } else {
                        var = 0;
                    }
                    int longitud;
                    if (columna.getTipoDato().compareToIgnoreCase(
                            Constants.TIPO_DATO_NUMERICO) == 0) {
                        longitud = columna.getLongitudCampo()
                                + columna.getPrecisionCampo()
                                + ((columna.getPrecisionCampo() > 0) ? 1 : 0);
                    } else {
                        longitud = columna.getLongitudCampo();
                    }
                    ini = fin + var;
                    fin = ini + longitud;
                    cadena = linea.substring(ini, fin);
                    if (columna.getFlagDelimitador().compareToIgnoreCase(
                            Constants.SI) == 0) {
                        String delimitador = StringUtils.stripToEmpty(columna
                                .getCaracterDelimitador());
                        cadena = StringUtils.strip(cadena, delimitador);
                    }
                    cadena = StringUtils.trimToEmpty(cadena);
                    if (log.isDebugEnabled())
                        log.debug("CADENA " + cadena + " PARA LA COLUMNA: "
                                + columna.getNombreColumna());
                    if (columna.getIndicadorRelleno().compareToIgnoreCase(
                            Constants.SI) == 0) {
                        String relleno = columna.getCaracterRelleno();
                        if (columna.getTipoDato().compareToIgnoreCase(
                                Constants.TIPO_DATO_NUMERICO) == 0) {
                            cadena = StringUtils.leftPad(cadena, longitud,
                                    relleno);
                        } else {
                            cadena = StringUtils.rightPad(cadena, longitud,
                                    relleno);
                        }
                    }
                    BeanUtils.copyProperty(objeto, columna.getNombreColumna(),
                            cadena);
                    fin = fin + var;
                }
            }
        }
        return objeto;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.scdf.service.InterfazPrivilegeService#executeEliminarBuzonMsg(java.lang.String)
     */
    public void executeEliminarBuzonMsg(String codigoPais) {
        clienteDAO.executeEliminarBuzonMsg(codigoPais);
    }

}