CREATE OR REPLACE PACKAGE "IMP_PKG_PROCE_COMPA" AS

w_Filas NUMBER := 1000;
ls_sqlerrm   VARCHAR2(150);
NOTA_CREDITO CONSTANT VARCHAR(3) :='NC';
NOTA_DEBITO  CONSTANT VARCHAR(3) :='ND';

CODIGO_CANAL VARCHAR2(10) := 'VD';
CODIGO_MARCA VARCHAR2(10) := 'T';

-- Nros de Paquetes Documentarios usados segun configuracion
NRO_PAQUETE_NORMAL CONSTANT NUMBER := 1;
NRO_PAQUETE_BOLETA_DESPACHO CONSTANT NUMBER := 2;
NRO_PAQUETE_BOLETA_RECOJO CONSTANT NUMBER := 3;


-- VARIABLES GLOBALES
gv_codPais                      VARCHAR2(3);
gv_user                         VARCHAR2(50);

-- Variables usadas para imprimir el inicio y fin de cada paquete
gv_tagInicio                    VARCHAR2(100);
gv_tagFin                       VARCHAR2(100);

-- Variables usadas para la compaginacion de la boleta de despacho
gv_tagBoletaDespachoApertura    VARCHAR2(100);
gv_tagBoletaDespachoCierre      VARCHAR2(100);
gv_tagCabeceraBoletaApertura    VARCHAR2(100);

-- Variables usadas para la inclusion del telefono en la boleta de despacho
gv_tagTelefono                  VARCHAR2(100);
gv_indicadorTelefono            VARCHAR2(100);
gv_textoTelefono                VARCHAR2(100);

-- Variables usadas para la inclusion del tag de saludo por cumplea?os
gv_indicadorCumpleanos          VARCHAR2(100);
gv_tagCumpleanos                VARCHAR2(100);
gv_tagCumpleanosApertura        VARCHAR2(100);
gv_tagCumpleanosCierre          VARCHAR2(100);

-- Variables usadas para la compaginacion de las cartas
gv_tagCartaApertura             VARCHAR2(100);
gv_tagCartaCierre               VARCHAR2(100);
gv_longitudTagCartaCierre       VARCHAR2(100);

-- Variables usadas para la compaginacion del Ultimas Noticias
gv_tagUltimasNoticiasApertura   VARCHAR2(100);
gv_tagUltimasNoticiasCierre     VARCHAR2(100);
gv_longitudTagUNCierre          VARCHAR2(100);

-- Variables usadas para la inclusion del puntaje de lideres
gv_tagPuntajeLideres            VARCHAR2(100);

-- Variables usadas para la compaginacion de la hoja de picado
gv_tagHojaPicadoApertura        VARCHAR2(100);
gv_tagHojaPicadoCierre          VARCHAR2(100);

-- Variables usadas para la compaginacion de la OCS
gv_tagOCSApertura               VARCHAR2(100);
gv_tagOCSCierre                 VARCHAR2(100);
gv_indicadorDigitoCtrlOCS       VARCHAR2(100);

-- Variables usadas para la compaginacion del cupon
gv_tagCuponVacio                VARCHAR2(100);
gv_tagFormatoCuponApertura      VARCHAR2(100);
gv_tagFormatoCuponCierre        VARCHAR2(100);
gv_tagBloqueCuponCierre         VARCHAR2(100);

-- Variables usadas para la identificacion de pedidos de servicio
gv_indicadorPedidoServicio      VARCHAR2(100);
gv_tagInicioPedidoServicio      VARCHAR2(100);

/* Declaracion de procedures */

/**************************************************************************
Descripcion         : Carga la informacion del archivo XML del paquete documentario
                      generado por el sistema SiCC en la tabla IMP_PAQUE_DOCUM_SICC.
                      Se crea un registro por cada paquete documentario de una
                      consultora.
Fecha Creacion      : 05/04/2006
Fecha Modificacion  : 04/07/2006
                      p_nombreArchivo : Nombre del archivo XML a cargar.
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_CARGA_XML_SICC;

PROCEDURE IMP_PR_CARGA_XML_SICC(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2);

/**************************************************************************
Descripcion         : Elimina bloques de tags XML reemplazando el bloque por
                      un comentario vacio.
Fecha Creacion      : 25/02/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_BLOQUE_PAQUE;

/**************************************************************************
Descripcion         : Carga la informacion del archivo XML del paquete documentario
                      generado por el sistema SiCC en la tabla IMP_PAQUE_DOCUM_CUPON.
                      Se crea un registro por cada paquete documentario de una
                      consultora.
Fecha Creacion      : 28/06/2006
Fecha Modificacion  : 04/07/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_CARGA_XML_CUPON;

PROCEDURE IMP_PR_CARGA_XML_CUPON(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2);

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
       Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 20/02/2006
Fecha Modificacion  : 06/09/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : 05/11/2008 Se modifico el procedimiento para que invoque
                      a la compaginacion dinamica o estatica (forma antigua, sin
                      uso de tabla de configuracion para el orden de los formatos).
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQUE_DOCUM;
/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
       Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 20/02/2006
Fecha Modificacion  : 06/09/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : 05/11/2008 Se modifico el procedimiento para que invoque
                      a la compaginacion dinamica o estatica (forma antigua, sin
                      uso de tabla de configuracion para el orden de los formatos).
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_ZONA(p_oidzona NUMBER, psCodPeri VARCHAR2, psFechaFacturacion VARCHAR2);
/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
       Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 20/02/2006
Fecha Modificacion  : 06/09/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : 05/11/2008 Se modifico el procedimiento para que invoque
                      a la compaginacion dinamica o estatica (forma antigua, sin
                      uso de tabla de configuracion para el orden de los formatos).
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_FINAL2(p_valnumesoli NUMBER, p_codclie varchar2);
/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
       Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 20/02/2006
Fecha Modificacion  : 06/09/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : 05/11/2008 Se modifico el procedimiento para que invoque
                      a la compaginacion dinamica o estatica (forma antigua, sin
                      uso de tabla de configuracion para el orden de los formatos).
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_FINAL(p_valnumesoli NUMBER, p_codclie varchar2);
/**************************************************************************
Descripcion         : Genera el archivo de nota de credito
Fecha Creacion      : 10/11/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_nombreArchivo         : Nombre del archivo de texto.
    p_directorio            : Ruta de generacion del archivo.
***************************************************************************/
PROCEDURE IMP_PR_GENER_ARCHI_PAQDOC_FIN(p_codigoPais VARCHAR2,
                                         p_nombreArchivo VARCHAR2,
                                         p_directorio VARCHAR2);
/**************************************************************************
Descripcion         : Genera el archivo de nota de credito
Fecha Creacion      : 10/11/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_nombreArchivo         : Nombre del archivo de texto.
    p_directorio            : Ruta de generacion del archivo.
***************************************************************************/
PROCEDURE IMP_PR_GENER_ARCHI_PAQDOC_FIN2(p_codigoPais VARCHAR2,
                                         p_nombreArchivo VARCHAR2,
                                         p_directorio VARCHAR2);

/**************************************************************************
Descripcion         : Se encarga de evaluar el contenido de los tags registrados
                      en la tabla IMP_ETIQU_XML y en caso de contener puros tags
       vacios, le agrega el atributo vacio="1"
Fecha Creacion      : 20/11/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_DOCUM_VACIO;

/**************************************************************************
Descripcion         : Busca los caracteres especiales registrados en la tabla
                      IMP_CARAC_REEMP y los reemplaza por su caracter de reemplazo
       registrado tambien en esta tabla.
Fecha Creacion      : 10/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_REEMP_CARAC_ESPEC;
/**************************************************************************
Descripcion         : Busca los caracteres especiales registrados en la tabla
                      IMP_CARAC_REEMP y los reemplaza por su caracter de reemplazo
       registrado tambien en esta tabla.
Fecha Creacion      : 10/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_REEMP_CARAC_ESPEC2;
/**************************************************************************
Descripcion         : Busca los caracteres especiales registrados en la tabla
                      IMP_CARAC_REEMP y los reemplaza por su caracter de reemplazo
       registrado tambien en esta tabla.
Fecha Creacion      : 10/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_REEMP_CARAC_ESPEC_BR;

/**************************************************************************
Descripcion         : Envia la informacion del paquete documentario almacenado
                      en la tabla IMP_PAQUE_DOCU a N archivos planos, la
                      cantidad de archivos a generarse es obtenida como un
                      parametro del store procedure.
Fecha Creacion      : 20/02/2006
Fecha Modificacion  : 21/09/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_totalArchivos IN NUMBER);

/* Se elimina para evitar conflictos con el cambio al store con el mismo nombre,
   a raiz de la salida de PA LB (CHR - 23/07/2008)
PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2, p_totalArchivos IN NUMBER);
*/


/**************************************************************************
Descripcion         : Envia la informacion del paquete documentario almacenado
                      en la tabla IMP_PAQUE_DOCU a un archivo plano. El nombre
                      y ruta de destino del archivo son pasados como parametros.
Fecha Creacion      : 23/07/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_nombreArchivo IN VARCHAR2,
                                     p_directorio IN VARCHAR2,
                                     p_numeroDocumento NUMBER := 1);

/**************************************************************************
Descripcion         : Envia la informacion del paquete documentario almacenado
                      en la tabla IMP_PAQUE_DOCU a N archivos planos. El nombre
                      y ruta de destino del archivo son pasados como parametros.
Fecha Creacion      : 01/07/2009
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_nombreArchivo IN VARCHAR2,
                                     p_directorio IN VARCHAR2,
                                     p_numeroDocumento NUMBER := 1,
                                     p_totalArchivos NUMBER := 1);

/**************************************************************************
Descripcion         : Carga la informacion del archivo XML del paquete documentario
                      generado por el sistema SiCC en la tabla IMP_PAQUE_DOCUM_SICC.
                      Se crea un registro por cada paquete documentario de una
                      consultora.
Fecha Creacion      : 20/02/2006
Fecha Modificacion  : 04/07/2006
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_PROCE_COMPA(p_totalArchivos IN NUMBER);

/**************************************************************************
Descripcion         : Carga un archivo conteniendo la informacion de una Nota
                      de Credito en la tabla IMP_NOTA_CREDI, asi como su Numero
       de Documento Interno, con el objetivo de unificar todas
       las notas de credito registradas en esta tabla en un solo
       archivo.
Fecha Creacion      : 17/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_CARGA_NOTA_CREDI(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2, p_numeroSerie IN NUMBER);

/**************************************************************************
Descripcion         : Carga un archivo conteniendo la informacion de una Nota
                      de Credito en la tabla IMP_NOTA_CREDI, asi como su Numero
       de Documento Interno, con el objetivo de unificar todas
       las notas de credito registradas en esta tabla en un solo
       archivo.
Fecha Creacion      : 28/03/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_CARGA_NOTA_CREDI(p_nombreArchivo IN VARCHAR2,
                                  p_directorio IN VARCHAR2,
          p_numeroSerie IN NUMBER,
          p_codigoPeriodo IN VARCHAR2,
          p_fechaFacturacion IN VARCHAR2);

/**************************************************************************
Descripcion         : Genera el archivo unificado de Notas de Credito, ordenando
                      los archivos en base al Numero de Documento Interno.
Fecha Creacion      : 17/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_NOTA_CREDI_TO_FILE(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2, p_numeroSerie IN NUMBER);

/**************************************************************************
Descripcion         : Genera el archivo unificado de Notas de Credito, ordenando
                      los archivos en base al Numero de Documento Interno.
Fecha Creacion      : 17/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_NOTA_CREDI_TO_FILE(p_nombreArchivo IN VARCHAR2,
                                    p_directorio IN VARCHAR2,
         p_numeroSerie IN NUMBER,
         p_codigoPeriodo IN VARCHAR2,
         p_fechaFacturacion IN VARCHAR2);

/**************************************************************************
Descripcion         : Genera el archivo unificado de Notas de Credito, ordenando
                      los archivos en base al Numero de Documento Interno.
Fecha Creacion      : 17/01/2007
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_PROCE_DETAL_FACTU(p_codigoPais IN VARCHAR2,
                                   p_codigoPeriodo IN VARCHAR2,
                                   p_fechaFacturacion IN VARCHAR2,
                                   p_codigoMarca IN VARCHAR2,
                                   p_codigoCanal IN VARCHAR2,
                                   p_tipoSolicitud IN VARCHAR2);
/**************************************************************************
Descripcion         : Genera el archivo xml para el detalle de pedido, ordenando
                      los archivos en base al Numero de Documento Interno.
Fecha Creacion      : 25/01/2007
Autor               : Jhenifer Rosas Limaylla
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/

FUNCTION IMP_FN_CALCU_PAGO_POSTE(psOidSoliPosi IN NUMBER, lsCodigoPeriodoSiguiente IN VARCHAR2)
RETURN NUMBER;
/**************************************************************************
Descripcion         : Esta funcion devuelve el pago de saldo posterior recibiendo
                      como parametro el oid_cabec de pedido
Fecha Creacion      : 25/01/2007
Autor               : Jhenifer Rosas Limaylla
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
FUNCTION IMP_FN_CALCU_TOTAL_PAGO_POSTE(psOidSoliCabecPedido IN NUMBER, lsCodigoPeriodoSiguiente IN VARCHAR2, lsTipo IN VARCHAR2)
RETURN NUMBER;

FUNCTION IMP_FN_TOTA_SERV(psOidSoliCabecPedido IN NUMBER, lsCodigoPeriodoSiguiente IN VARCHAR2, lsTipo IN VARCHAR2)
RETURN NUMBER;

PROCEDURE IMP_PR_GENER_DETAL_FACTU;


/***************************************************************************
Descripcion       : Procedimiemto Generar Mensaje de Puntaje Obtenido XML

Fecha Creacion    : 07/03/2008 12:56:20 a.m.
Autor             : Leonardo Lizana
***************************************************************************/
PROCEDURE IMP_PR_GENER_MENSA_PUNTA_OBTEN(psCodClie VARCHAR2,
                                         psXML     VARCHAR2);


/***************************************************************************
Descripcion       : Funcion que retorna el numero de documento legal en base
                    al numero de solicitud de referencia para una nota de credito.

Fecha Creacion    : 28/03/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_NUMER_DOCUM_LEGAL (p_numeroSolicitudRef IN NUMBER,
                                   p_oidTipoDocuFact    IN NUMBER )
RETURN NUMBER;

/***************************************************************************
Descripcion       : Procedimiemto Generar Mensaje de Puntaje Obtenido XML

Fecha Creacion    : 13/03/2008 05:56:20 p.m.
Autor             : Leonardo Lizana
***************************************************************************/
PROCEDURE IMP_PR_GENER_XML_BOLET_RECOJ;


/***************************************************************************
Descripcion       :  Procedimiemto Calcular Documentos Laser

Fecha Creacion    : 01/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_FACTU(psCodPais VARCHAR2,
                                   psCodMarca VARCHAR2,
                                   psCodCanal VARCHAR2,
                                   psCodPeriodo VARCHAR2,
                                   psFechaFacturacion VARCHAR2);


/***************************************************************************
Descripcion       : Funcion que retorna valor de tasa de impuesto
parametro         : psIndicadorImpu  indicador de puesto OUT

Fecha Creacion    : 01/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
FUNCTION IMP_FN_OBTIE_TASA_IMPUE (psIndicadorImpu OUT VARCHAR2)
                                    RETURN NUMBER;

/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Factura

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_FACTU;

/***************************************************************************
Descripcion       : Procedimiemto Calcular Documentos Laser Guia

Fecha Creacion    : 15/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_GUIA(psCodPais VARCHAR2,
                                   psCodMarca VARCHAR2,
                                   psCodCanal VARCHAR2,
                                   psCodPeriodo VARCHAR2,
                                   psFechaFacturacion VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Guia

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_GUIA;

/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Cuenta Corriente

Fecha Creacion    : 22/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_CTCTE;

/***************************************************************************
Descripcion       : Procedimiento eliminar tablas de documento laser comun

Fecha Creacion    : 22/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_TABLA_DOCUM_LASER;


/***************************************************************************
Descripcion       : Procedimiemto Calcular Documentos Laser Nota Credito

Fecha Creacion    : 15/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_NOCRE(psCodPais VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Nota Credito

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_NOCRE;

/***************************************************************************
Descripcion       : Procedimiemto Calcular Documentos Laser Nota Debito

Fecha Creacion    : 15/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_NODEB(psCodPais VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Nota Debito

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_NODEB;


/***************************************************************************
Descripcion       : Funcion usada para validar si una consultora cumple a?os
                    en el mes del proceso, retornando 0 si no cumple la condicion
                    y 1 si la cumple.

Fecha Creacion    : 04/10/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_VALID_CUMPL (p_codigoCliente IN VARCHAR) RETURN NUMBER;
/***************************************************************************
Descripcion       : Funcion usada para validar si una consultora cumple a?os
                    en el mes del proceso, retornando 0 si no cumple la condicion
                    y 1 si la cumple.

Fecha Creacion    : 04/10/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_VALID_VALE (p_oidSoli IN NUMBER) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Funcion que obtiene una cadena con el valor del cumplea?os
                    de un cliente, en el formato pasado como parametro.

Fecha Creacion    : 30/03/2009
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_FECHA_CUMPL (p_codigoCliente IN VARCHAR,
                             p_patron IN VARCHAR2 := 'DD-MONTH',
                             p_nlsDateLang IN VARCHAR2 := 'NLS_DATE_LANGUAGE = SPANISH') RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de la boleta de despacho
                    que va a ser compaginado..

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_BOLET_DESPA (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las boletas de recojo
                    que van a ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_BOREC (p_codigoCliente IN VARCHAR2,
                                 p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las cartas
                    que van a ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_CARTA (p_codigoCliente IN VARCHAR2,
                                 p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del formato de Ultimas
                    Noticias que va a ser compaginado.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_ULTIM_NOTIC (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del detalle de factura
                    que va a ser compaginado.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_DETAL_FACTU (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las hojas de picado
                    que van a ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_HOJA_PICAD (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de orden de compra simplificada
                    que va a ser compaginada.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_OCS (p_codigoCliente IN VARCHAR2,
                               p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del formato de Ultimas
                    Noticias Privilege que va a ser compaginado.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_PRIVI(p_codigoCliente IN VARCHAR2,
                                p_numeroSolicitud IN NUMBER) RETURN CLOB;


/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del cupon que va a ser
                    compaginado..

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_CUPON (p_codigoCliente IN VARCHAR2,
                                 p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las facturas laser
                    ha ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_LASER_FACTU (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de la guia de premios
                    qe va a ser compaginada.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_LASER_GUIA (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las notas de credito
                    del cliente que van a ser incluidas en el compaginado.
                    Se toman en cuenta las notas de credito que aun no han
                    sido impresas (IND_IMPR = 0)

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_NOTA_CREDI (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las notas de debito
                    del cliente que van a ser incluidas en el compaginado.
                    Se toman en cuenta las notas de debito que aun no han
                    sido impresas (IND_IMPR = 0)

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_NOTA_DEBIT (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER) RETURN CLOB;

/***************************************************************************
Descripcion       : Funcion usada para obtener la sentencia SQL dinamica usada
                    para la compaginacion, la cual toma la parametria especificada
                    en la tabla IMP_FORMU_PAQUE_DOCUM, recibe como parametro el
                    numero de documento el cual se compara con el valor de la
                    columna NUM_PADO de la tabla anterior, asi como el orden
                    especificado en la tabla VAL_ORDE_SECU.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_SQL (p_numeroPaquete NUMBER)RETURN VARCHAR2;

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, y los
                      otros formatos (detalle factura, boleta recojo, etc).
                      El orden de compaginacion se define a traves de la tabla
                      IMP_FORMU_PAQUE_DOCUM.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQUE_DOCUM_DINAM;

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, y los
                      otros formatos (detalle factura, boleta recojo, etc).
                      Este procedimiento contiene la antigua logica de compaginacion,
                      es decir, sin hacer uso de la tabla de configuracion.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQUE_DOCUM_ESTAT;

/**************************************************************************
Descripcion         : Actualiza el indicador de impresion de las notas de
                      credito que se hayan compaginado en el paquete documentario.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_ACTUA_NOTA_CREDI_IMPRE;

/**************************************************************************
Descripcion         : Actualiza el indicador de impresion de las notas de
                      debito que se hayan compaginado en el paquete documentario.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_ACTUA_NOTA_DEBIT_IMPRE;

/***************************************************************************
Descripcion       : Funcion usada para obtener el valor del saldo actual de
                    una consultora en base al parametro de numero de filas
                    ingresado
Fecha Creacion    : 20/01/2009
Autor             : Dennys Oliva Iriarte
***************************************************************************/
FUNCTION IMP_FN_CALCU_SALDO_INICI (psOidCliente NUMBER,
                                   psParametro NUMBER,
                                   psFechaLimite VARCHAR2)RETURN NUMBER;

/***************************************************************************
Descripcion       : Procedimiento que actualiza el numero de documento legal
                    a partir del numero de documento interno, para un determinado
                    tipo de documento y una fecha de facturacion determinada.
                    Asimismo actualiza el numero de documento legal en el
                    registro unico de venta.
Fecha Creacion    : 29/01/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_ACTUA_NUMER_DOCUM_LEGAL (pnOidPeriodo NUMBER,
                                          psFechaFacturacion VARCHAR2,
                                          psCodigoTipoDocumento  VARCHAR2);

/***************************************************************************
Descripcion       : Funcion que obtiene el saldo a favor que tenia una
                    consultora al momento de pasar su pedido.
Fecha Creacion    : 03/02/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
FUNCTION IMP_FN_CALCU_SALDO_FAVOR(pnOidConsolidado NUMBER) RETURN NUMBER;

/***************************************************************************
Descripcion       : Procedimiento que actualiza el numero de documento legal
                    a partir del numero de documento interno, para las notas
                    de credito laser generadas.
                    Asimismo actualiza el numero de documento legal en el
                    registro unico de venta.
Fecha Creacion    : 02/03/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE  IMP_PR_ACTUA_NUMER_DOCUM_NOTCR;

/***************************************************************************
Descripcion       : Procedimiento que actualiza el numero de documento legal
                    a partir del numero de documento interno, para las notas
                    de debito laser generadas.
                    Asimismo actualiza el numero de documento legal en el
                    registro unico de venta.
Fecha Creacion    : 02/03/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE  IMP_PR_ACTUA_NUMER_DOCUM_NOTDE;

/***************************************************************************
Descripcion       : Procedimiento que carga el XML de la hoja de picado.
Fecha Creacion    : 11/05/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_CARGA_XML_HOJA_PICAD(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2);

/***************************************************************************
Descripcion       : Procedimiento que limpia las tablas en las cuales se cargan
                    los archivos del paquete documentario generado por SiCC, el
                    archivo de cupones y el de la hoja de picado. Es proceso se
                    ejecuta como paso inicial de la compaginacion.
Fecha Creacion    : 25/05/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_PAQUE_DOCUM;

/***************************************************************************
Descripcion       : Procedimiento que reemplaza la direccion de la boleta de
                    despacho para aquellas consultoras que tienen una direccion
                    direccion de entrega configurada.
Fecha Creacion    : 08/06/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_DIREC_BOLET_DESPA;

/***************************************************************************
Descripcion       : Procedimiento que reemplaza datos en la OCS para fusion.
Fecha Creacion    : 15/03/2012
Autor             : Jorge Yepez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_DATOS_OCS(ps_codpais IN VARCHAR2);
/***************************************************************************
Descripcion       : Procedimiento que reemplaza datos en la OCS para fusion.
Fecha Creacion    : 15/03/2012
Autor             : Jorge Yepez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_DNI_OCS(ps_codpais IN VARCHAR2);
/***************************************************************************
Descripcion       : Procedimiento que reemplaza datos en la OCS para fusion.
Fecha Creacion    : 15/03/2012
Autor             : Jorge Yepez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_OCS;
/***************************************************************************
Descripcion       : Procedimiento que reemplaza datos en la OCS para fusion.
Fecha Creacion    : 15/03/2012
Autor             : Jorge Yepez
***************************************************************************/
PROCEDURE IMP_PR_INCL_FLEX;

/***************************************************************************
Descripcion       : Funcion que obtiene el tag a colocar en la boleta de
                    despacho dependiendo de la clasificacion de la consultora.
Fecha Creacion    : 24/11/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
FUNCTION IMP_FN_ETIQU_CLASI_BOLET_DESPA(p_codigoCliente IN VARCHAR2) RETURN VARCHAR2;

/***************************************************************************
Descripcion       : Proceso que carga el paquete documentario directamente de
                    la tabla de SiCC y ya no desde el archivo.
Fecha Creacion    : 29/01/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_CARGA_PAQUE_DOCUM_SICC(p_codigoPais VARCHAR2,
                                        p_codigoPeriodo VARCHAR2,
                                        p_fechaFacturacion VARCHAR2);

/***************************************************************************
Descripcion       : Proceso que carga los cupones directamente de
                    la tabla de SiCC y ya no desde el archivo.
Fecha Creacion    : 01/02/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_CARGA_PAQUE_DOCUM_CUPON(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/***************************************************************************
Descripcion       : Proceso que genera los cupones de las consultoras que
                    han pasado pedido en un periodo y fecha particular.
Fecha Creacion    : 31/03/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_GENER_PAQUE_DOCUM_CUPON(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2);

/***************************************************************************
Descripcion       : Funcion que obtiene el tag a colocar en la boleta de
                    despacho dependiendo del estado de la consultora.
Fecha Creacion    : 27/05/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
FUNCTION IMP_FN_ETIQU_ESTAT_BOLET_DESPA(p_codigoCliente IN VARCHAR2) RETURN VARCHAR2;

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
                      Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
                      (Usando el CLOB en Memoria)
Fecha Creacion      : 03/06/2014
Autor               : Sergio Apaza
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_FINAL3(p_valnumesoli NUMBER, p_codclie varchar2);

/***************************************************************************
Descripcion       : Procedimiemto que implementa la logica en JAVA
Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
Fecha Creacion    : 17/09/2014
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
PROCEDURE IMP_PR_GENER_XML_BOREC_ORA(psCodigoPais IN VARCHAR2,
                                   psCodigoPeriodo IN VARCHAR2,
                                   psCodigoMarca IN VARCHAR2,
                                   psCodigoCanal IN VARCHAR2);

/***************************************************************************
Descripcion       : Envia la informacion para SMS de la boleta del PRIMER recojo
Fecha Creacion    : 21/11/2014
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
  PROCEDURE IMP_PR_GENER_CSV_PRIME_REC
  ( pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2
  );

/***************************************************************************
Descripcion       : Envia la informacion para SMS de la boleta del SEGUNDO recojo
Fecha Creacion    : 24/11/2014
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
PROCEDURE IMP_PR_GENER_CSV_SEGUN_REC
  ( pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2
  );

/***************************************************************************
Descripcion       : Envia la informacion para SMS de la boleta del SEGUNDO recojo
                    NO EXITOSO
Fecha Creacion    : 24/11/2014
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
PROCEDURE IMP_PR_GENER_CSV_SEGNE_REC
  ( pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera archivo de texto de Interfaz de GENERAR BAN2 DOMINICANA
  Fecha Creacion    : 01/06/2015
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/

PROCEDURE IMP_PR_GENER_BAN_DOMIN
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2
);


END;
/
CREATE OR REPLACE PACKAGE BODY "IMP_PKG_PROCE_COMPA" AS

PROCEDURE IMP_PR_CARGA_XML_SICC IS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;
l_directorio            VARCHAR2(100);
l_nombreArchivo         VARCHAR2(90);
l_extArchivo            VARCHAR2(10);
l_nombreCompletoArchivo VARCHAR2(100);

-- Contador usado para el correlativo
l_Correlative NUMBER := 1;

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;
l_Offset        NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);
l_codCliente   VARCHAR2(20);
l_numeroSolicitud NUMBER(10);

BEGIN
    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SICC
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_SICC';

    -- Tambien truncamos la tabla de cupon IMP_PAQUE_DOCUM_CUPON
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

    -- Obtenemos la informacion del directorio y nombre del archivo
    -- XML conteniendo el paquete documentario del SiCC
    SELECT DIR_ENSA, NOM_ARCH, EXT_ARCH
    INTO l_directorio, l_nombreArchivo, l_extArchivo
    FROM IMP_ARCHI_IMPRE
    WHERE COD_ARIM = 'SIC';

    l_nombreCompletoArchivo := l_nombreArchivo || '.' || l_extArchivo;

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_SICC_DIR AS ''' || l_directorio || '''';

    -- Inicializamos el BFILE locator para lectura
    l_FileLocator := BFILENAME('XML_SICC_DIR', l_nombreCompletoArchivo);
    DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

    -- Usamos un CLOB temporal para cargar la informacion del archivo
    DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

    -- Cargamos todo el archivo en un character LOB Temporal.
    DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                              l_FileLocator,
                              DBMS_LOB.GETLENGTH(l_FileLocator),
                              src_offset,
                              dst_offset,
                              charset_id,
                              lang_ctx,
                              warning);

    -- Cerramos el archivo
    DBMS_LOB.FILECLOSE(l_FileLocator);

    -- Obtenemos el tama?o del CLOB cargado
    l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

    -- LOOP
    WHILE l_CLOBLength > 0 AND l_OffsetFinal != 0
    LOOP

        -- Una vez que tenemos todo el archivo en una CLOB temporal
        -- podemos usar las funciones del paquete DBMS_LOB para segmentarlo
        DBMS_APPLICATION_INFO.SET_CLIENT_INFO('LINEA NRO ' || TO_CHAR(l_Correlative));

        l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                            '<pd>',
                                            l_OffsetInicial);

        l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                          '</pd>',
                                          l_OffsetInicial);

        IF l_OffsetInicial != 0 AND l_OffsetFinal != 0 THEN

            INSERT INTO IMP_PAQUE_DOCUM_SICC (COR_PDSI, XML_CONS)
            VALUES(l_Correlative, EMPTY_CLOB())
            RETURNING XML_CONS INTO l_CLOBDest;

            -- Copiamos la porcion del CLOB original al parcial
            DBMS_LOB.COPY(l_CLOBDest,
                            l_CLOBSrc,
                            l_OffsetFinal - l_OffsetInicial + 5,
                            1,
                            l_OffsetInicial);

            l_OffsetInicial := l_OffsetFinal + 4;

            -- Obtenemos el codigo del cliente del XML
            l_codCliente := DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</ccon>') - (INSTR(l_CLOBDest, '<ccon>') + 6), INSTR(l_CLOBDest, '<ccon>') + 6);
            l_numeroSolicitud := TO_NUMBER(DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</nbd>') - (INSTR(l_CLOBDest, '<nbd>') + 5), INSTR(l_CLOBDest, '<nbd>') + 5));

            -- Actualizamos el valor del codigo del cliente
            UPDATE IMP_PAQUE_DOCUM_SICC A
            SET A.COD_CLIE = l_codCliente,
                A.VAL_NUME_SOLI = l_numeroSolicitud
            WHERE A.COR_PDSI=l_Correlative;

        END IF;

        -- Incrementamos el valor del correlativo
        l_Correlative := l_Correlative + 1;

    END LOOP;-- END LOOP

    -- Liberamos los rescursos usados por el CLOB temporal
    DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

    COMMIT;

    EXCEPTION
    WHEN UTL_FILE.INTERNAL_ERROR THEN
        l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILEHANDLE THEN
        l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_MODE THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || l_nombreCompletoArchivo;
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILENAME THEN
        l_mensajeError:='EL ARCHIVO ' || l_nombreCompletoArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_OPERATION THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_PATH THEN
        l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN OTHERS THEN
            -- Close the cursor and file, and reraise.
            DBMS_LOB.FILECLOSE(l_FileLocator);
            DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
         RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_XML_SICC: '||substr(sqlerrm,1,250));

END IMP_PR_CARGA_XML_SICC;


PROCEDURE IMP_PR_CARGA_XML_SICC(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2) IS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;

-- Contador usado para el correlativo
l_Correlative NUMBER := 1;

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;
l_Offset        NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;
l_codCliente VARCHAR2(20);
l_numeroSolicitud NUMBER(10);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

BEGIN
    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SICC
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_SICC';

    -- Tambien truncamos la tabla de cupon IMP_PAQUE_DOCUM_CUPON
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_SICC_DIR AS ''' || p_directorio || '''';

    -- Inicializamos el BFILE locator para lectura
    l_FileLocator := BFILENAME('XML_SICC_DIR', p_nombreArchivo);
    DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

    -- Usamos un CLOB temporal para cargar la informacion del archivo
    DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

    -- Cargamos todo el archivo en un character LOB Temporal.
    DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                                 l_FileLocator,
                                 DBMS_LOB.GETLENGTH(l_FileLocator),
                                 src_offset,
                                 dst_offset,
                                 charset_id,
                                 lang_ctx,
                                 warning);

    -- Cerramos el archivo
    DBMS_LOB.FILECLOSE(l_FileLocator);

    -- Obtenemos el tama?o del CLOB cargado
    l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

    -- LOOP
    WHILE l_CLOBLength > 0 AND l_OffsetFinal != 0
    LOOP

        -- Una vez que tenemos todo el archivo en una CLOB temporal
        -- podemos usar las funciones del paquete DBMS_LOB para segmentarlo
        DBMS_APPLICATION_INFO.SET_CLIENT_INFO('LINEA NRO ' || TO_CHAR(l_Correlative));

        l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                            '<pd>',
                                            l_OffsetInicial);

        l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                          '</pd>',
                                          l_OffsetInicial);

        IF l_OffsetInicial != 0 AND l_OffsetFinal != 0 THEN

            INSERT INTO IMP_PAQUE_DOCUM_SICC (COR_PDSI, XML_CONS)
            VALUES(l_Correlative, EMPTY_CLOB())
            RETURNING XML_CONS INTO l_CLOBDest;

            -- Copiamos la porcion del CLOB original al parcial
            DBMS_LOB.COPY(l_CLOBDest,
                          l_CLOBSrc,
                          l_OffsetFinal - l_OffsetInicial + 5,
                          1,
                          l_OffsetInicial);

            l_OffsetInicial := l_OffsetFinal + 4;

            -- Obtenemos el codigo del cliente del XML
            l_codCliente := DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</ccon>') - (INSTR(l_CLOBDest, '<ccon>') + 6), INSTR(l_CLOBDest, '<ccon>') + 6);
            l_numeroSolicitud := TO_NUMBER(DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</nbd>') - (INSTR(l_CLOBDest, '<nbd>') + 5), INSTR(l_CLOBDest, '<nbd>') + 5));

            -- Actualizamos el valor del codigo del cliente
            UPDATE IMP_PAQUE_DOCUM_SICC A
            SET A.COD_CLIE = l_codCliente,
                A.VAL_NUME_SOLI = l_numeroSolicitud
            WHERE A.COR_PDSI = l_Correlative;

        END IF;

        -- Incrementamos el valor del correlativo
        l_Correlative := l_Correlative + 1;

    END LOOP;-- END LOOP

    -- Liberamos los rescursos usados por el CLOB temporal
    DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

    -- Actualizamos el indicador de pedido de servicio
    UPDATE IMP_PAQUE_DOCUM_SICC A
    SET IND_PEDI_SERV = 'S'
    WHERE NOT EXISTS (
        SELECT NULL
        FROM PED_SOLIC_CABEC CON,
             PED_SOLIC_CABEC PSC,
             MAE_CLIEN MC
        WHERE MC.OID_CLIE = CON.CLIE_OID_CLIE
        AND CON.OID_SOLI_CABE = PSC.SOCA_OID_SOLI_CABE
        AND PSC.IND_OC = 1
        AND MC.COD_CLIE = A.COD_CLIE
        AND CON.VAL_NUME_SOLI = A.VAL_NUME_SOLI
    );

    COMMIT;

    EXCEPTION
    WHEN UTL_FILE.INTERNAL_ERROR THEN
        l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILEHANDLE THEN
        l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_MODE THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || p_nombreArchivo;
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILENAME THEN
        l_mensajeError:='EL ARCHIVO ' || p_nombreArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_OPERATION THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_PATH THEN
        l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN OTHERS THEN
            -- Close the cursor and file, and reraise.
            DBMS_LOB.FILECLOSE(l_FileLocator);
            DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
         RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_XML_SICC: '||substr(sqlerrm,1,250));

END IMP_PR_CARGA_XML_SICC;


PROCEDURE IMP_PR_ELIMI_BLOQUE_PAQUE IS

-- Variables a utilizar
l_CLOB              CLOB;
l_correlativo       NUMBER;
l_etiqueta          IMP_ETIQU_ELIMI.VAL_ETIQ%TYPE;
l_etiquetaApertura  VARCHAR2(100);
l_etiquetaCierre    VARCHAR2(100);
l_etiquetaReemplazo VARCHAR2(100);
l_longitudApertura  NUMBER;
l_longitudCierre    NUMBER;
l_subcontenido      CLOB;

CURSOR c_paquetes IS
SELECT COR_PADO, NUM_PADO, XML_CONS
FROM IMP_PAQUE_DOCUM
ORDER BY NUM_PADO, COR_PADO;

r_paquetes c_paquetes%ROWTYPE;

CURSOR c_etiquetas IS
SELECT VAL_ETIQ
FROM IMP_ETIQU_ELIMI
WHERE EST_ETEL = '1'
AND TIP_ELIM = 'T'
ORDER BY ORD_ELIM;

CURSOR c_etiquetasZonas IS
SELECT A.VAL_ETIQ, B.COD_ZONA
FROM IMP_ETIQU_ELIMI A, IMP_ETIQU_ELIMI_ZONA B
WHERE A.COR_ETEL = B.ETEL_COR_ETEL
AND A.EST_ETEL = '1'
AND A.TIP_ELIM = 'Z'
ORDER BY ORD_ELIM;

r_etiquetasZonas c_etiquetasZonas%ROWTYPE;

CURSOR c_paquetesZonas(p_codigoZona VARCHAR2) IS
SELECT COR_PADO, NUM_PADO, XML_CONS
FROM IMP_PAQUE_DOCUM
WHERE DBMS_LOB.SUBSTR(XML_CONS, INSTR(XML_CONS, '</czon>') - (INSTR(XML_CONS, '<czon>') + 6), INSTR(XML_CONS, '<czon>') + 6) = p_codigoZona
ORDER BY NUM_PADO, COR_PADO;

r_paquetesZonas c_paquetesZonas%ROWTYPE;

BEGIN

/* Iteramos sobre cada una de las lineas del paquete documentario */
OPEN c_paquetes;
LOOP
FETCH c_paquetes INTO r_paquetes;
EXIT WHEN c_paquetes%NOTFOUND;
  /* Iteramos sobre cada una de las etiquetas que estan en la tabla
     IMP_ETIQU_XML y obtenemos los bloques XML que encierran. */
  OPEN c_etiquetas;
  LOOP
  FETCH c_etiquetas INTO l_etiqueta;
  EXIT WHEN c_etiquetas%NOTFOUND;
    IF(INSTR(r_paquetes.XML_CONS, l_etiqueta) != 0) THEN
        l_etiquetaApertura := '<' || l_etiqueta || '>';
        l_etiquetaCierre := '</' || l_etiqueta || '>';
        l_longitudApertura := LENGTH(l_etiquetaApertura);
        l_longitudCierre := LENGTH(l_etiquetaCierre);

        -- Reemplazamos por una cadena de comentario XML
        l_etiquetaReemplazo := '<!-- -->';

        l_subcontenido := DBMS_LOB.SUBSTR(r_paquetes.XML_CONS, INSTR(r_paquetes.XML_CONS, l_etiquetaCierre, -1) - (INSTR(r_paquetes.XML_CONS, l_etiquetaApertura)) + l_longitudCierre, INSTR(r_paquetes.XML_CONS, l_etiquetaApertura));

        SELECT XML_CONS INTO l_CLOB
        FROM IMP_PAQUE_DOCUM
        WHERE COR_PADO = r_paquetes.COR_PADO
        AND   NUM_PADO = r_paquetes.NUM_PADO
        FOR UPDATE;
        IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(r_paquetes.XML_CONS, l_subcontenido, l_etiquetaReemplazo);
    END IF;
  END LOOP;
  CLOSE c_etiquetas;
  COMMIT;
END LOOP;
CLOSE c_paquetes;

/* Iteramos sobre las zonas a considerar para eliminar los bloques del paq. doc. */
OPEN c_etiquetasZonas;
LOOP
FETCH c_etiquetasZonas INTO r_etiquetasZonas;
EXIT WHEN c_etiquetasZonas%NOTFOUND;
    l_etiquetaApertura := '<' || r_etiquetasZonas.VAL_ETIQ || '>';
    l_etiquetaCierre := '</' || r_etiquetasZonas.VAL_ETIQ || '>';
    l_longitudApertura := LENGTH(l_etiquetaApertura);
    l_longitudCierre := LENGTH(l_etiquetaCierre);

    -- Reemplazamos por una cadena de comentario XML
    l_etiquetaReemplazo := '<!-- -->';

    /* Iteramos sobre los paquetes que pertenecen a la zona */
    OPEN c_paquetesZonas(r_etiquetasZonas.COD_ZONA);
    LOOP
    FETCH c_paquetesZonas INTO r_paquetesZonas;
        EXIT WHEN c_paquetesZonas%NOTFOUND;
        IF(INSTR(r_paquetesZonas.XML_CONS, r_etiquetasZonas.VAL_ETIQ) != 0) THEN
            l_subcontenido := DBMS_LOB.SUBSTR(r_paquetesZonas.XML_CONS, INSTR(r_paquetesZonas.XML_CONS, l_etiquetaCierre, -1) - (INSTR(r_paquetesZonas.XML_CONS, l_etiquetaApertura)) + l_longitudCierre, INSTR(r_paquetesZonas.XML_CONS, l_etiquetaApertura));

            SELECT XML_CONS INTO l_CLOB
            FROM IMP_PAQUE_DOCUM
            WHERE COR_PADO = r_paquetesZonas.COR_PADO
            AND   NUM_PADO = r_paquetesZonas.NUM_PADO
            FOR UPDATE;
            IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(r_paquetesZonas.XML_CONS, l_subcontenido, l_etiquetaReemplazo);
            COMMIT;
        END IF;
    END LOOP;
    CLOSE c_paquetesZonas;
END LOOP;
CLOSE c_etiquetasZonas;

END IMP_PR_ELIMI_BLOQUE_PAQUE;


PROCEDURE IMP_PR_CARGA_XML_CUPON IS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;
l_directorio            VARCHAR2(100);
l_nombreArchivo         VARCHAR2(90);
l_extArchivo            VARCHAR2(10);
l_nombreCompletoArchivo VARCHAR2(100);

-- Contador usado para el correlativo
l_Correlative NUMBER := 1;

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;
l_Offset        NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);
l_codCliente VARCHAR2(20);

BEGIN
  -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_CUPON
  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

  -- Obtenemos la informacion del directorio y nombre del archivo
  -- XML conteniendo el paquete documentario del SiCC
  SELECT DIR_ENSA, NOM_ARCH, EXT_ARCH
  INTO l_directorio, l_nombreArchivo, l_extArchivo
  FROM IMP_ARCHI_IMPRE
  WHERE COD_ARIM = 'CUP';

  l_nombreCompletoArchivo := l_nombreArchivo || '.' || l_extArchivo;

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_CUPON_DIR AS ''' || l_directorio || '''';

  -- Inicializamos el BFILE locator para lectura
  l_FileLocator := BFILENAME('XML_CUPON_DIR', l_nombreCompletoArchivo);
  DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

  -- Usamos un CLOB temporal para cargar la informacion del archivo
  DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

  -- Cargamos todo el archivo en un character LOB Temporal.
  DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                               l_FileLocator,
                               DBMS_LOB.GETLENGTH(l_FileLocator),
                                src_offset,
                                dst_offset,
                                charset_id,
                                lang_ctx,
                                warning);

  -- Cerramos el archivo
  DBMS_LOB.FILECLOSE(l_FileLocator);

  -- Obtenemos el tama?o del CLOB cargado
  l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

  -- LOOP
  WHILE l_CLOBLength > 0 AND l_OffsetFinal != 0
  LOOP

    -- Una vez que tenemos todo el archivo en una CLOB temporal
    -- podemos usar las funciones del paquete DBMS_LOB para segmentarlo

    --DBMS_OUTPUT.PUT_LINE('LINEA NRO ' || TO_CHAR(l_Correlative));
    DBMS_APPLICATION_INFO.SET_CLIENT_INFO('LINEA NRO ' || TO_CHAR(l_Correlative));
    --DBMS_OUTPUT.PUT_LINE('OBTENIENDO EL OFFSET...');

    l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                      '<pd>',
                                l_OffsetInicial);

    l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                    '</pd>',
                              l_OffsetInicial);

    IF l_OffsetInicial != 0 AND l_OffsetFinal != 0 THEN

      --DBMS_OUTPUT.PUT_LINE('Insertando ' || TO_CHAR(l_OffsetFinal - l_OffsetInicial + 5) || ' caracteres desde la posicion ' || TO_CHAR(l_OffsetInicial));

      INSERT INTO IMP_PAQUE_DOCUM_CUPON (COR_PDCU, XML_CONS)
      VALUES(l_Correlative, EMPTY_CLOB())
      RETURNING XML_CONS INTO l_CLOBDest;

      -- Copiamos la porcion del CLOB original al parcial
      DBMS_LOB.COPY(l_CLOBDest,
                    l_CLOBSrc,
                    l_OffsetFinal - l_OffsetInicial - 4,
                    1,
                    l_OffsetInicial + 4); -- Aumentamos en 4 para que no tomem el tag <pd>

      l_OffsetInicial := l_OffsetFinal + 4;

   -- Obtenemos el codigo del cliente del XML
      l_codCliente := DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</ccon>') - (INSTR(l_CLOBDest, '<ccon>') + 6), INSTR(l_CLOBDest, '<ccon>') + 6);

   -- Actualizamos el valor del codigo del cliente
      UPDATE IMP_PAQUE_DOCUM_CUPON A
   SET A.COD_CLIE = l_codCliente
      WHERE A.COR_PDCU = l_Correlative;

    END IF;

    -- Incrementamos el valor del correlativo
    l_Correlative := l_Correlative + 1;

  END LOOP;-- END LOOP

  -- Liberamos los rescursos usados por el CLOB temporal
  DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

  COMMIT;

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || l_nombreCompletoArchivo;
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILENAME THEN
     l_mensajeError:='EL ARCHIVO ' || l_nombreCompletoArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
         -- Close the cursor and file, and reraise.
         DBMS_LOB.FILECLOSE(l_FileLocator);
         DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_XML_CUPON: '||substr(sqlerrm,1,250));

END IMP_PR_CARGA_XML_CUPON;

PROCEDURE IMP_PR_CARGA_XML_CUPON(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2) IS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;

-- Contador usado para el correlativo
l_Correlative NUMBER := 1;

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;
l_Offset        NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);
l_codCliente   VARCHAR2(20);

BEGIN
  -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_CUPON
  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_CUPON_DIR AS ''' || p_directorio || '''';

  -- Inicializamos el BFILE locator para lectura
  l_FileLocator := BFILENAME('XML_CUPON_DIR', p_nombreArchivo);
  DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

  -- Usamos un CLOB temporal para cargar la informacion del archivo
  DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

  -- Cargamos todo el archivo en un character LOB Temporal.
  DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                            l_FileLocator,
                            DBMS_LOB.GETLENGTH(l_FileLocator),
       src_offset,
       dst_offset,
       charset_id,
       lang_ctx,
       warning);

  -- Cerramos el archivo
  DBMS_LOB.FILECLOSE(l_FileLocator);

  -- Obtenemos el tama?o del CLOB cargado
  l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

  -- LOOP
  WHILE l_CLOBLength > 0 AND l_OffsetFinal != 0
  LOOP

    -- Una vez que tenemos todo el archivo en una CLOB temporal
    -- podemos usar las funciones del paquete DBMS_LOB para segmentarlo

    DBMS_APPLICATION_INFO.SET_CLIENT_INFO('LINEA NRO ' || TO_CHAR(l_Correlative));

    l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                      '<pd>',
                                l_OffsetInicial);

    l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                    '</pd>',
                              l_OffsetInicial);

    IF l_OffsetInicial != 0 AND l_OffsetFinal != 0 THEN

      INSERT INTO IMP_PAQUE_DOCUM_CUPON (COR_PDCU, XML_CONS)
      VALUES(l_Correlative, EMPTY_CLOB())
      RETURNING XML_CONS INTO l_CLOBDest;

      -- Copiamos la porcion del CLOB original al parcial
      DBMS_LOB.COPY(l_CLOBDest,
                    l_CLOBSrc,
                    l_OffsetFinal - l_OffsetInicial - 4,
                    1,
                    l_OffsetInicial + 4); -- Aumentamos en 4 para que no tomem el tag <pd>

      l_OffsetInicial := l_OffsetFinal + 4;

   -- Obtenemos el codigo del cliente del XML
      l_codCliente := DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</ccon>') - (INSTR(l_CLOBDest, '<ccon>') + 6), INSTR(l_CLOBDest, '<ccon>') + 6);

   -- Actualizamos el valor del codigo del cliente
      UPDATE IMP_PAQUE_DOCUM_CUPON A
   SET A.COD_CLIE = l_codCliente
      WHERE A.COR_PDCU = l_Correlative;

    END IF;

    -- Incrementamos el valor del correlativo
    l_Correlative := l_Correlative + 1;

  END LOOP;-- END LOOP

  -- Liberamos los rescursos usados por el CLOB temporal
  DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

  COMMIT;

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || p_nombreArchivo;
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILENAME THEN
     l_mensajeError:='EL ARCHIVO ' || p_nombreArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
         -- Close the cursor and file, and reraise.
         DBMS_LOB.FILECLOSE(l_FileLocator);
         DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_XML_CUPON: '||substr(sqlerrm,1,250));

END IMP_PR_CARGA_XML_CUPON;

PROCEDURE IMP_PR_COMPA_PAQUE_DOCUM_ESTAT IS

-- Variables usadas para la compaginacion
lv_tagCuponVacio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCuponVacio');
lv_tagInicio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicio');
lv_tagBoletaDespacho VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagBoletaDespacho');
lv_tagPuntajeLideres VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagPuntajeLideres');
lv_tagFin VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFin');

-- Variables usadas para la inclusion del telefono en la boleta de despacho
lv_tagTelefono VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagTelefono');
lv_indicadorTelefono VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorTelefono');
lv_textoTelefono VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','textoTelefono');

-- Variables usadas para la inclusion del tag de saludo por cumplea?os
lv_indicadorCumpleanos VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorCumpleanos');
lv_tagCumpleanos VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanos');
lv_tagCumpleanosApertura VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosApertura');
lv_tagCumpleanosCierre   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosCierre');

-- Division de Paquete Documentario
lv_indBoletaIndependiente    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorBoletaIndependiente');
lv_tagBoletaDespachoApertura VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagBoletaDespachoApertura');
lv_tagBoletaDespachoCierre   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagBoletaDespachoCierre');

lv_indicadorPedidoChequear VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorPedidoChequear');
lv_tagPedidoChequearApertura VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagPedidoChequearApertura');
lv_tagPedidoChequearCierre   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagPedidoChequearCierre');

lv_tagCabeceraBoletaApertura VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCabeceraBoletaDepachoApertura');
lv_tagHojaPicadoApertura     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagHojaPicadoApertura');
lv_tagHojaPicadoCierre       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagHojaPicadoCierre');
lv_tagFormatoCuponCierre     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFormatoCuponCierre');
lv_indBorecIndependiente     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorBoletaRecojoIndependiente');

-- Variables usadas para la compaginacion de documentos laser y detalle cta cte
lv_tagBloqueCuponCierre VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagBloqueCuponCierre');

-- Variables usadas para la identificacion de pedidos de servicio
lv_indicadorPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorPedidoServicio');
lv_tagInicioPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');

-- Variables usadas para la identificacion de pedidos de servicio
lv_indicadorEntregaVale   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorEntregaVale');
lv_tagInicioEntregaVale   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioEntregaVale');

BEGIN


  /*
  Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM
  */
  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM';

  -- Dependiendo del parametro de generacion de la boleta de despacho en forma independiente
  -- hacemos 1 o 2 compaginaciones y posiblemente una tercera para las boletas de recojo
  IF lv_indBoletaIndependiente = 'S' THEN

      /***********************************************************************/
      /************************ Dos compaginaciones **************************/
      /***********************************************************************/

      /***********************************************************************/
      /* I) COMPAGINACION DEL PAQUETE DOCUMENTARIO SIN LA BOLETA DE DESPACHO */
      /***********************************************************************/
      INSERT INTO IMP_PAQUE_DOCUM
      SELECT
      ROWNUM,
      XML_CONS,
      COD_CLIE,
      NUM_PADO,
      VAL_NUME_SOLI FROM (
      SELECT
      -- Correlativo de la tabla IMP_PAQUE_DOCUM_SICC
      S.COR_PDSI,
      -- Concatenamos las diferentes porciones del XML
      -- 0) Tag de inicio
      CASE WHEN lv_indicadorPedidoServicio IS NOT NULL
            AND lv_tagInicioPedidoServicio IS NOT NULL
            AND lv_indicadorPedidoServicio = 'S' THEN
          DECODE(S.IND_PEDI_SERV, 'S', lv_tagInicioPedidoServicio, lv_tagInicio)
      ELSE
          lv_tagInicio
      END ||

      -- 1) Lo que se encuentre entre el tag de inicio <pd> y el tag de boleta de despacho de inicio
      SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagInicio) + DBMS_LOB.GETLENGTH(lv_tagInicio), INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) - (INSTR(S.XML_CONS, lv_tagInicio) + DBMS_LOB.GETLENGTH(lv_tagInicio))) ||

      -- 2) Detalle de Factura
      D.XML_DETA_FACT ||

      -- 3) Cupon de Pago
      NVL(C.XML_CONS, TO_CLOB(lv_tagCuponVacio)) ||

      -- 4) XML de Puntaje Lideres, siempre que se encuentre el tag configurado
      CASE WHEN lv_tagPuntajeLideres IS NOT NULL
            AND INSTR(S.XML_CONS, lv_tagPuntajeLideres) <> 0 THEN
           -- En caso este definido el tag de Hoja de Picado y si ademas se encuentra contenido en el paquete
           -- documentario extraemos el bloque hasta antes de la Hoja de Picado, en caso contrario, extraemos
           -- el bloque hasta antes del tag de finalizacion del paquete documentario </pd>
           CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
                 AND lv_tagHojaPicadoCierre   IS NOT NULL
                 AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
               SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre))) ||
               O.XML_MENS_PUNT ||
               SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres), INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) - (INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres)))
           ELSE
               SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre))) ||
               O.XML_MENS_PUNT ||
               SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres), INSTR(S.XML_CONS, lv_tagFin) - (INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres)))
           END
      ELSE
           -- En caso este definido el tag de Hoja de Picado y si ademas se encuentra contenido en el paquete
           -- documentario extraemos el bloque hasta antes de la Hoja de Picado, en caso contrario, extraemos
           -- el bloque hasta antes del tag de finalizacion del paquete documentario </pd>
           CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
                 AND lv_tagHojaPicadoCierre   IS NOT NULL
                 AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
               -- 4.5) XML desde el final del tag </frmbd> hasta antes de la Hoja de Picado <ptl>
               SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre)))
           ELSE
               -- 4.5) XML desde el final del tag </frmbd> hasta antes del tag </pd> (SiCC)
               SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagFin) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre)))
           END
      END ||

      -- 5) XML de Ultimas Noticias Privilege
      P.XML_CONS ||

      -- 6) XML Hoja de Picado
      CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
            AND lv_tagHojaPicadoCierre   IS NOT NULL
            AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
          SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagHojaPicadoApertura), INSTR(S.XML_CONS, lv_tagHojaPicadoCierre, -1) + DBMS_LOB.GETLENGTH(lv_tagHojaPicadoCierre) - (INSTR(S.XML_CONS, lv_tagHojaPicadoApertura)))
      ELSE
          TO_CLOB('')
      END ||

      -- 7) Tag de cierre del paquete documentario </pd>
      lv_tagFin XML_CONS,
      S.COD_CLIE,
      NRO_PAQUETE_NORMAL NUM_PADO, -- NUM_PADO (1 para la compaginacion normal)
      S.VAL_NUME_SOLI
      FROM IMP_PAQUE_DOCUM_SICC S,
           IMP_PAQUE_DOCUM_PRIVI P,
           IMP_PAQUE_DOCUM_CUPON C,
           IMP_PAQUE_DOCUM_DETAL_FACTU D,
           IMP_PAQUE_DOCUM_PUNTA_OBTEN O
      WHERE S.COD_CLIE = P.COD_CONS (+)
        AND S.COD_CLIE = D.COD_CONS (+)
        AND S.VAL_NUME_SOLI = D.VAL_NUME_SOLI (+)
        AND S.COD_CLIE = C.COD_CLIE (+)
        AND S.COD_CLIE = O.COD_CLIE (+)
      ORDER BY S.COR_PDSI);

      /*******************************************************/
      /* II) COMPAGINACION DE LA BOLETA DE DESPACHO Y RECOJO */
      /*******************************************************/
      INSERT INTO IMP_PAQUE_DOCUM
      SELECT
      ROWNUM,
      XML_CONS,
      COD_CLIE,
      NUM_PADO,
      VAL_NUME_SOLI FROM (
      SELECT
      -- Correlativo de la tabla IMP_PAQUE_DOCUM_SICC
      S.COR_PDSI,

      -- Concatenamos las diferentes porciones del XML
      -- 0) Tag de inicio
      CASE WHEN lv_indicadorPedidoServicio IS NOT NULL
            AND lv_tagInicioPedidoServicio IS NOT NULL
            AND lv_indicadorPedidoServicio = 'S' THEN
          DECODE(S.IND_PEDI_SERV, 'S', lv_tagInicioPedidoServicio, lv_tagInicio)
      ELSE
          lv_tagInicio
      END ||

      -- 1) Boleta de Despacho (con la inclusion del numero de telefono y/o tag de saludo de cumplea?os segun sea el caso)
      CASE WHEN (lv_tagTelefono IS NOT NULL)
            AND (lv_indicadorTelefono = 'S')
            AND (GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF') IS NOT NULL OR
                 GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM') IS NOT NULL
            ) THEN
          -- Tag de cumplea?os
          CASE WHEN (lv_tagCumpleanos IS NOT NULL)
                AND (lv_indicadorCumpleanos = 'S')
                AND (IMP_FN_VALID_CUMPL(S.COD_CLIE) != 0) THEN
              -- Telefono y cumplea?os
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura))) ||
              lv_tagCumpleanosApertura || IMP_FN_FECHA_CUMPL(S.COD_CLIE) || lv_tagCumpleanosCierre ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura), INSTR(S.XML_CONS, lv_tagTelefono) - (INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura))) ||
              lv_textoTelefono || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF')) || '/' || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM')) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagTelefono), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagTelefono)))
          ELSE
              -- Solo telefono
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagTelefono) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura))) ||
              lv_textoTelefono || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF')) || '/' || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM')) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagTelefono), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagTelefono)))
          END
      ELSE
          CASE WHEN (lv_tagCumpleanos IS NOT NULL)
                AND (lv_indicadorCumpleanos = 'S')
                AND (IMP_FN_VALID_CUMPL(S.COD_CLIE) != 0) THEN
              -- Solo tag de cumplea?os
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura))) ||
              lv_tagCumpleanosApertura || IMP_FN_FECHA_CUMPL(S.COD_CLIE) || lv_tagCumpleanosCierre ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura)  + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura)))
          ELSE
              -- Sin telefono y sin cumplea?os
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura)))
          END
      END ||

      -- 2) Boleta de Recojo (Siempre y cuando no este configurado como boleta recojo independiente)
      CASE WHEN lv_indBorecIndependiente = 'S' THEN
          TO_CLOB('')
      ELSE
          B.XML_CONS
      END ||

      -- 3) Tag de cierre del paquete documentario
      lv_tagFin XML_CONS,
      S.COD_CLIE,
      NRO_PAQUETE_BOLETA_DESPACHO NUM_PADO, -- NUM_PADO (2 para el paquete de boleta de despacho/recojo)
      S.VAL_NUME_SOLI
      FROM MAE_CLIEN M,
           IMP_PAQUE_DOCUM_SICC S,
           IMP_PAQUE_DOCUM_BOREC B
      WHERE M.COD_CLIE = S.COD_CLIE
        AND S.COD_CLIE = B.COD_CONS (+)
      ORDER BY S.COR_PDSI);

    -- Procesamos la informacion de aquellas que no han pasado pedido o de todas
    -- las boletas de recojo si esta configurado para que se generen en un archivo
    -- independiente
    IF lv_indBorecIndependiente = 'S' THEN
        -- Colocamos las todas boletas de recojo al final con otro numero de
        -- paquete documentario y ordenados en base a la secuenciacion de territorios
        INSERT INTO IMP_PAQUE_DOCUM
        SELECT ROWNUM,
               XML_CONS,
               COD_CONS,
               NUM_PADO,
               NULL -- Numero de Solicitud
        FROM (
        SELECT
            -- Concatenamos las diferentes porciones de XML
            -- 0) Tag de inicio
            lv_tagInicio ||

            -- 1) Boleta de Recojo
            P.XML_CONS ||

            -- 2) Tag de Fin
            lv_tagFin XML_CONS,

            P.COD_CONS,
            NRO_PAQUETE_BOLETA_RECOJO NUM_PADO -- (3 para el paquete de boleta de recojo)
        FROM IMP_PAQUE_DOCUM_BOREC P,
             MAE_CLIEN B,
             MAE_CLIEN_UNIDA_ADMIN C,
             ZON_TERRI_ADMIN D,
             APP_RUTAS_TERRI E,
             APP_RUTAS_TRANS F
        WHERE P.COD_CONS = B.COD_CLIE
          AND B.OID_CLIE = C.CLIE_OID_CLIE
          AND C.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
          AND D.TERR_OID_TERR = E.TERR_OID_TERR
          AND E.RUTR_OID_RUTA_TRAN = F.OID_RUTA_TRAN
          AND C.IND_ACTI = 1
          AND C.PERD_OID_PERI_FIN IS NULL
        ORDER BY F.NUM_SECU,
              E.NUM_SECU,
              P.COD_CONS
        );
    ELSE
        -- A?adimos las boletas de recojo de aquellas que no han pasado pedido
        INSERT INTO IMP_PAQUE_DOCUM
        SELECT
            -- Correlativo
            (SELECT NVL(MAX(P.COR_PADO), 0) FROM IMP_PAQUE_DOCUM P) + ROWNUM COR_PADO,
            -- Concatenamos las diferentes porciones de XML
            -- 0) Tag de inicio
            lv_tagInicio ||

            -- 1) Boleta de Recojo
            B.XML_CONS ||

            -- 2) Tag de Fin
            lv_tagFin XML_CONS,

            B.COD_CONS,
            NRO_PAQUETE_BOLETA_DESPACHO, -- NUM_PADO (2 para el paquete de boleta de despacho/recojo)
            NULL -- Numero de Solicitud
        FROM IMP_PAQUE_DOCUM_BOREC B
        WHERE COD_CONS NOT IN (SELECT X.COD_CLIE FROM IMP_PAQUE_DOCUM X);
    END IF;

  ELSE
      /*********************************************************************/
      /********************** Una sola compaginacion ***********************/
      /*********************************************************************/
      INSERT INTO IMP_PAQUE_DOCUM
      SELECT
      ROWNUM,
      XML_CONS,
      COD_CLIE,
      NUM_PADO,
      VAL_NUME_SOLI FROM (
      SELECT
      -- Correlativo de la tabla IMP_PAQUE_DOCUM_SICC
      S.COR_PDSI,
      -- Concatenamos las diferentes porciones de XML
      -- 0) Tag de inicio
      CASE WHEN lv_indicadorPedidoServicio IS NOT NULL
            AND lv_tagInicioPedidoServicio IS NOT NULL
            AND lv_indicadorPedidoServicio = 'S' THEN
          DECODE(S.IND_PEDI_SERV, 'S', lv_tagInicioPedidoServicio, lv_tagInicio)
      ELSE
          lv_tagInicio
      END ||

      -- 1) Boleta de Despacho
      -- Dependiendo de los valores de la parametria se incluira el telefono
      -- antes del tag configurado con el valor tagTelefono y si ademas el valor
      -- del parametro indicadorTelefono es 'S'
      CASE WHEN (lv_tagTelefono IS NOT NULL)
            AND (lv_indicadorTelefono = 'S')
            AND (GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF') IS NOT NULL OR
                 GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM') IS NOT NULL
            ) THEN
          -- Tag de cumplea?os
          CASE WHEN (lv_tagCumpleanos IS NOT NULL)
                AND (lv_indicadorCumpleanos = 'S')
                AND (IMP_FN_VALID_CUMPL(S.COD_CLIE) != 0) THEN
              -- Telefono y cumplea?os
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              CASE WHEN lv_indicadorEntregaVale IS NOT NULL
                AND lv_tagInicioEntregaVale IS NOT NULL
                AND lv_indicadorEntregaVale = 'S'
                AND (IMP_FN_VALID_VALE(S.VAL_NUME_SOLI) != 0) THEN
                          lv_tagInicioEntregaVale
              else
                  ''
              end ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura))) ||
              lv_tagCumpleanosApertura || IMP_FN_FECHA_CUMPL(S.COD_CLIE) || lv_tagCumpleanosCierre ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura), INSTR(S.XML_CONS, lv_tagTelefono) - (INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura))) ||
              lv_textoTelefono || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF')) || '/' || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM')) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagTelefono), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagTelefono)))
          ELSE
              -- Solo telefono
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              CASE WHEN lv_indicadorEntregaVale IS NOT NULL
                AND lv_tagInicioEntregaVale IS NOT NULL
                AND lv_indicadorEntregaVale = 'S'
                AND (IMP_FN_VALID_VALE(S.VAL_NUME_SOLI) != 0) THEN
                          lv_tagInicioEntregaVale
              else
                  ''
              end ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagTelefono) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura))) ||
              lv_textoTelefono || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF')) || '/' || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM')) ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagTelefono), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagTelefono)))
          END
      ELSE
          CASE WHEN (lv_tagCumpleanos IS NOT NULL)
                AND (lv_indicadorCumpleanos = 'S')
                AND (IMP_FN_VALID_CUMPL(S.COD_CLIE) != 0) THEN
              -- Solo tag de cumplea?os
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              CASE WHEN lv_indicadorEntregaVale IS NOT NULL
                AND lv_tagInicioEntregaVale IS NOT NULL
                AND lv_indicadorEntregaVale = 'S'
                AND (IMP_FN_VALID_VALE(S.VAL_NUME_SOLI) != 0) THEN
                          lv_tagInicioEntregaVale
              else
                  ''
              end ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura))) ||
              lv_tagCumpleanosApertura || IMP_FN_FECHA_CUMPL(S.COD_CLIE) || lv_tagCumpleanosCierre ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagCabeceraBoletaApertura)  + DBMS_LOB.GETLENGTH(lv_tagCabeceraBoletaApertura)))
          ELSE
              -- Sin telefono y sin cumplea?os
              lv_tagBoletaDespachoApertura ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
              IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
              CASE WHEN lv_indicadorEntregaVale IS NOT NULL
                AND lv_tagInicioEntregaVale IS NOT NULL
                AND lv_indicadorEntregaVale = 'S'
                AND (IMP_FN_VALID_VALE(S.VAL_NUME_SOLI) != 0) THEN
                          lv_tagInicioEntregaVale
              else
                  ''
              end ||
              SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoApertura)))
          END
      END ||

      -- 2) Boleta de Recojo
      CASE WHEN lv_indBorecIndependiente = 'S' THEN
          TO_CLOB('')
      ELSE
          B.XML_CONS
      END ||

      -- 3) Detalle de Factura
      D.XML_DETA_FACT ||

      -- 4) XML de Cupon de Pago
      NVL(C.XML_CONS, TO_CLOB(lv_tagCuponVacio)) ||

      -- 5) XML de Puntaje Lideres, siempre que se encuentre el tag configurado
      --    evaluamos primero si existe cupon de pago para saber si extraemos la
      --    informacion desde el final de la boleta o desde el final del cupon
      CASE WHEN lv_tagFormatoCuponCierre IS NOT NULL
            AND INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) <> 0 THEN
          CASE WHEN lv_tagPuntajeLideres IS NOT NULL
                AND INSTR(S.XML_CONS, lv_tagPuntajeLideres) <> 0 THEN
               -- En caso este definido el tag de Hoja de Picado y si ademas se encuentra contenido en el paquete
               -- documentario extraemos el bloque hasta antes de la Hoja de Picado, en caso contrario, extraemos
               -- el bloque hasta antes del tag de finalizacion del paquete documentario </pd>
               CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
                     AND lv_tagHojaPicadoCierre   IS NOT NULL
                     AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre), INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres) - (INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre))) ||
                   O.XML_MENS_PUNT ||
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres), INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) - (INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres)))
               ELSE
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre), INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres) - (INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre))) ||
                   O.XML_MENS_PUNT ||
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres), INSTR(S.XML_CONS, lv_tagFin) - (INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres)))
               END
          ELSE
               -- En caso este definido el tag de Hoja de Picado y si ademas se encuentra contenido en el paquete
               -- documentario extraemos el bloque hasta antes de la Hoja de Picado, en caso contrario, extraemos
               -- el bloque hasta antes del tag de finalizacion del paquete documentario </pd>
               CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
                     AND lv_tagHojaPicadoCierre   IS NOT NULL
                     AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
                   -- 5.5) XML desde el final del tag </frmbd> hasta antes de la Hoja de Picado <ptl>
                   SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre), INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) - (INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre)))
               ELSE
                   -- 5.5) XML desde el final del tag </frmbd> hasta antes del tag </pd> (SiCC)
                   SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre), INSTR(S.XML_CONS, lv_tagFin) - (INSTR(S.XML_CONS, lv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(lv_tagFormatoCuponCierre)))
               END
          END
      ELSE
          CASE WHEN lv_tagPuntajeLideres IS NOT NULL
                AND INSTR(S.XML_CONS, lv_tagPuntajeLideres) <> 0 THEN
               -- En caso este definido el tag de Hoja de Picado y si ademas se encuentra contenido en el paquete
               -- documentario extraemos el bloque hasta antes de la Hoja de Picado, en caso contrario, extraemos
               -- el bloque hasta antes del tag de finalizacion del paquete documentario </pd>
               CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
                     AND lv_tagHojaPicadoCierre   IS NOT NULL
                     AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre))) ||
                   O.XML_MENS_PUNT ||
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres), INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) - (INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres)))
               ELSE
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre))) ||
                   O.XML_MENS_PUNT ||
                   SUBSTR(S.XML_CONS,INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres), INSTR(S.XML_CONS, lv_tagFin) - (INSTR(S.XML_CONS, lv_tagPuntajeLideres) + DBMS_LOB.GETLENGTH(lv_tagPuntajeLideres)))
               END
          ELSE
               -- En caso este definido el tag de Hoja de Picado y si ademas se encuentra contenido en el paquete
               -- documentario extraemos el bloque hasta antes de la Hoja de Picado, en caso contrario, extraemos
               -- el bloque hasta antes del tag de finalizacion del paquete documentario </pd>
               CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
                     AND lv_tagHojaPicadoCierre   IS NOT NULL
                     AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
                   -- 5.5) XML desde el final del tag </frmbd> hasta antes de la Hoja de Picado <ptl>
                   SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre)))
               ELSE
                   -- 5.5) XML desde el final del tag </frmbd> hasta antes del tag </pd> (SiCC)
                   SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre), INSTR(S.XML_CONS, lv_tagFin) - (INSTR(S.XML_CONS, lv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(lv_tagBoletaDespachoCierre)))
               END
          END
      END ||

      -- 6) XML de Ultimas Noticias Privilege
      P.XML_CONS ||

      -- 7) XML Hoja de Picado
      CASE WHEN lv_tagHojaPicadoApertura IS NOT NULL
            AND lv_tagHojaPicadoCierre   IS NOT NULL
            AND INSTR(S.XML_CONS, lv_tagHojaPicadoApertura) <> 0 THEN
          SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagHojaPicadoApertura), INSTR(S.XML_CONS, lv_tagHojaPicadoCierre, -1) + DBMS_LOB.GETLENGTH(lv_tagHojaPicadoCierre) - (INSTR(S.XML_CONS, lv_tagHojaPicadoApertura)))
      ELSE
          TO_CLOB('')
      END ||

      -- 8) Tag de cierre del paquete documentario </pd>
      lv_tagFin XML_CONS,
      S.COD_CLIE,
      NRO_PAQUETE_NORMAL NUM_PADO, -- NUM_PADO (1 para la compaginacion normal)
      S.VAL_NUME_SOLI
      FROM MAE_CLIEN M,
           IMP_PAQUE_DOCUM_SICC S,
           IMP_PAQUE_DOCUM_PRIVI P,
           IMP_PAQUE_DOCUM_CUPON C,
           IMP_PAQUE_DOCUM_DETAL_FACTU D,
           IMP_PAQUE_DOCUM_PUNTA_OBTEN O,
           IMP_PAQUE_DOCUM_BOREC B
      WHERE M.COD_CLIE = S.COD_CLIE
        AND S.COD_CLIE = P.COD_CONS (+)
        AND S.COD_CLIE = D.COD_CONS (+)
        AND S.VAL_NUME_SOLI = D.VAL_NUME_SOLI (+)
        AND S.COD_CLIE = C.COD_CLIE (+)
        AND S.COD_CLIE = O.COD_CLIE (+)
        AND S.COD_CLIE = B.COD_CONS (+)
      ORDER BY S.COR_PDSI);

    -- Procesamos la informacion de aquellas que no han pasado pedido o de todas
    -- las boletas de recojo si esta configurado para que se generen en un archivo
    -- independiente
    IF lv_indBorecIndependiente = 'S' THEN
        -- Colocamos las todas boletas de recojo al final con otro numero de
        -- paquete documentario y ordenados en base a la secuenciacion de territorios
        INSERT INTO IMP_PAQUE_DOCUM
        SELECT ROWNUM,
               XML_CONS,
               COD_CONS,
               NUM_PADO,
               NULL -- Numero de Solicitud
        FROM (
        SELECT
            -- Concatenamos las diferentes porciones de XML
            -- 0) Tag de inicio
            lv_tagInicio ||

            -- 1) Boleta de Recojo
            P.XML_CONS ||

            -- 2) Tag de Fin
            lv_tagFin XML_CONS,

            P.COD_CONS,
            NRO_PAQUETE_BOLETA_RECOJO NUM_PADO -- (3 para el paquete de boleta de recojo)
        FROM IMP_PAQUE_DOCUM_BOREC P,
             MAE_CLIEN B,
             MAE_CLIEN_UNIDA_ADMIN C,
             ZON_TERRI_ADMIN D,
             APP_RUTAS_TERRI E,
             APP_RUTAS_TRANS F
        WHERE P.COD_CONS = B.COD_CLIE
          AND B.OID_CLIE = C.CLIE_OID_CLIE
          AND C.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
          AND D.TERR_OID_TERR = E.TERR_OID_TERR
          AND E.RUTR_OID_RUTA_TRAN = F.OID_RUTA_TRAN
          AND C.IND_ACTI = 1
          AND C.PERD_OID_PERI_FIN IS NULL
        ORDER BY F.NUM_SECU,
              E.NUM_SECU,
              P.COD_CONS
        );
    ELSE
        -- A?adimos las boletas de recojo de aquellas que no han pasado pedido
        INSERT INTO IMP_PAQUE_DOCUM
        SELECT
            -- Correlativo
            (SELECT NVL(MAX(P.COR_PADO), 0) FROM IMP_PAQUE_DOCUM P) + ROWNUM COR_PADO,
            -- Concatenamos las diferentes porciones de XML
            -- 0) Tag de inicio
            lv_tagInicio ||

            -- 1) Boleta de Recojo
            B.XML_CONS ||

            -- 2) Tag de Fin
            lv_tagFin XML_CONS,

            B.COD_CONS,
            NRO_PAQUETE_NORMAL, -- NUM_PADO (1 para la compaginacion normal)
            NULL -- Numero de Solicitud
        FROM IMP_PAQUE_DOCUM_BOREC B
        WHERE COD_CONS NOT IN (SELECT X.COD_CLIE FROM IMP_PAQUE_DOCUM X);
    END IF;

  END IF;

if lv_indicadorPedidoChequear is not null and lv_indicadorPedidoChequear='S' then
update imp_paque_docum s set s.xml_cons=
              SUBSTR(S.XML_CONS, 0, INSTR(S.XML_CONS, lv_tagPedidoChequearApertura) + DBMS_LOB.GETLENGTH(lv_tagPedidoChequearApertura)-1)
              || IMP_FN_VALID_VALE(s.val_nume_soli)
              || SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, lv_tagPedidoChequearCierre) , DBMS_LOB.GETLENGTH(S.XML_CONS)) ;
end if;




  COMMIT;

  EXCEPTION
  WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_COMPA_PAQUE_DOCUM_ESTAT: '||substr(sqlerrm,1,250));
END IMP_PR_COMPA_PAQUE_DOCUM_ESTAT;

PROCEDURE IMP_PR_ELIMI_DOCUM_VACIO IS

-- Variables a utilizar
l_CLOB              CLOB;
l_correlativo       NUMBER;
l_etiqueta          IMP_ETIQU_XML.VAL_ETIQ%TYPE;
l_etiquetaApertura  VARCHAR2(100);
l_etiquetaCierre    VARCHAR2(100);
l_etiquetaReemplazo VARCHAR2(100);
l_longitudApertura  NUMBER;
l_longitudCierre    NUMBER;
l_subcontenido      CLOB;
cont NUMBER := 0;

CURSOR c_paquetes IS
SELECT COR_PADO, NUM_PADO, XML_CONS
FROM IMP_PAQUE_DOCUM
ORDER BY NUM_PADO, COR_PADO;

r_paquetes c_paquetes%ROWTYPE;

CURSOR c_etiquetas IS
SELECT VAL_ETIQ
FROM IMP_ETIQU_XML
WHERE EST_ETIQ = '1'
ORDER BY ORD_ETIQ;

BEGIN

/* Iteramos sobre cada una de las lineas del paquete documentario */
OPEN c_paquetes;
LOOP
FETCH c_paquetes INTO r_paquetes;
EXIT WHEN c_paquetes%NOTFOUND;

  /* Iteramos sobre cada una de las etiquetas que estan en la tabla
     IMP_ETIQU_XML y obtenemos los bloques XML que encierran. */
  cont := cont + 1;
  OPEN c_etiquetas;
  LOOP
  FETCH c_etiquetas INTO l_etiqueta;
  EXIT WHEN c_etiquetas%NOTFOUND;
      IF(INSTR(r_paquetes.XML_CONS, l_etiqueta) != 0) THEN
        l_etiquetaApertura := '<' || l_etiqueta || '>';
        l_etiquetaCierre := '</' || l_etiqueta || '>';
        l_longitudApertura := LENGTH(l_etiquetaApertura);
        l_longitudCierre := LENGTH(l_etiquetaCierre);
        /*
        IF (cont > 500) then
        DBMS_OUTPUT.PUT_LINE(to_char(cont) || ') PI : ' || to_char(INSTR(r_paquetes.XML_CONS, l_etiquetaApertura) + l_longitudApertura));
        DBMS_OUTPUT.PUT_LINE(to_char(cont) || ') LS : ' || to_char(INSTR(r_paquetes.XML_CONS, l_etiquetaCierre) - (INSTR(r_paquetes.XML_CONS, l_etiquetaApertura) + l_longitudApertura)));
        end if;
        */
        l_subcontenido := DBMS_LOB.SUBSTR(r_paquetes.XML_CONS, INSTR(r_paquetes.XML_CONS, l_etiquetaCierre) - (INSTR(r_paquetes.XML_CONS, l_etiquetaApertura) + l_longitudApertura), INSTR(r_paquetes.XML_CONS, l_etiquetaApertura) + l_longitudApertura);

        /* Evaluamos el contenido de la subcadena */
        /* Si a la cadena luego de eliminar los blancos, le encontramos alguna subcadena
           de la forma >#< donde # es cualquier cantidad de caracteres no blancos ni < o >,
           entonces no hacemos nada con ella, en caso contrario le colocamos el atributo
           vacio="1" */
        IF(REGEXP_INSTR(REPLACE(l_subcontenido, ' ', ''), '>([^[:space:]<>])+</') != 0) THEN
          /* No hacemos nada */
          l_etiquetaReemplazo :=  NULL;
        ELSE
          l_etiquetaReemplazo := '<' || l_etiqueta || ' vacio="1">';
          SELECT XML_CONS
          INTO l_CLOB
          FROM IMP_PAQUE_DOCUM
          WHERE COR_PADO = r_paquetes.COR_PADO
          AND   NUM_PADO = r_paquetes.NUM_PADO
          FOR UPDATE;
          IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(r_paquetes.XML_CONS, l_etiquetaApertura, l_etiquetaReemplazo);
        END IF;
      END IF;
  END LOOP;
  CLOSE c_etiquetas;
  COMMIT;
END LOOP;
CLOSE c_paquetes;

END IMP_PR_ELIMI_DOCUM_VACIO;


PROCEDURE IMP_PR_REEMP_CARAC_ESPEC IS

-- Variables a utilizar
l_CLOB              CLOB;
l_correlativo       NUMBER;

CURSOR c_paquetes IS
SELECT COR_PADO, NUM_PADO, XML_CONS
FROM IMP_PAQUE_DOCUM;

r_paquetes c_paquetes%ROWTYPE;

CURSOR c_caracteres IS
SELECT VAL_CARA, VAL_REEM
FROM IMP_CARAC_REEMP
WHERE EST_CARE = '1'
ORDER BY ORD_REEM;

r_caracteres c_caracteres%ROWTYPE;

l_pos    NUMBER := 1;
l_startPos   NUMBER := 1;
l_longitud   NUMBER;

BEGIN

/* Iteramos sobre cada una de las lineas del paquete documentario */
OPEN c_paquetes;
LOOP
FETCH c_paquetes INTO r_paquetes;
EXIT WHEN c_paquetes%NOTFOUND;


  /* Iteramos sobre cada uno de los caracteres especiales a reemplazar */
  OPEN c_caracteres;
  LOOP
  FETCH c_caracteres INTO r_caracteres;
  EXIT WHEN c_caracteres%NOTFOUND;
      /*  */
      l_startPos := 1;
      l_longitud := LENGTH(r_caracteres.VAL_REEM);
      l_pos := INSTR(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, l_startPos);
      WHILE(l_pos != 0) LOOP
          SELECT XML_CONS
          INTO l_CLOB
          FROM IMP_PAQUE_DOCUM
          WHERE COR_PADO = r_paquetes.COR_PADO
          AND   NUM_PADO = r_paquetes.NUM_PADO
          FOR UPDATE;
          IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, r_caracteres.VAL_REEM, l_startPos);
          l_startPos := l_pos + l_longitud;
          l_pos := INSTR(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, l_startPos);
      END LOOP;
  END LOOP;
  CLOSE c_caracteres;
  COMMIT;
END LOOP;
CLOSE c_paquetes;

END IMP_PR_REEMP_CARAC_ESPEC;

PROCEDURE IMP_PR_REEMP_CARAC_ESPEC2 IS

-- Variables a utilizar
l_CLOB              CLOB;
l_correlativo       NUMBER;

CURSOR c_paquetes IS
SELECT COR_PADO, NUM_PADO, XML_CONS
FROM IMP_PAQUE_DOCUM_FINAL;

r_paquetes c_paquetes%ROWTYPE;

CURSOR c_caracteres IS
SELECT VAL_CARA, VAL_REEM
FROM IMP_CARAC_REEMP
WHERE EST_CARE = '1'
ORDER BY ORD_REEM;

r_caracteres c_caracteres%ROWTYPE;

l_pos    NUMBER := 1;
l_startPos   NUMBER := 1;
l_longitud   NUMBER;

BEGIN

/* Iteramos sobre cada una de las lineas del paquete documentario */
OPEN c_paquetes;
LOOP
FETCH c_paquetes INTO r_paquetes;
EXIT WHEN c_paquetes%NOTFOUND;


  /* Iteramos sobre cada uno de los caracteres especiales a reemplazar */
  OPEN c_caracteres;
  LOOP
  FETCH c_caracteres INTO r_caracteres;
  EXIT WHEN c_caracteres%NOTFOUND;
      /*  */
      l_startPos := 1;
      l_longitud := LENGTH(r_caracteres.VAL_REEM);
      l_pos := INSTR(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, l_startPos);
      WHILE(l_pos != 0) LOOP
          SELECT XML_CONS
          INTO l_CLOB
          FROM IMP_PAQUE_DOCUM_FINAL
          WHERE COR_PADO = r_paquetes.COR_PADO
          AND   NUM_PADO = r_paquetes.NUM_PADO
          FOR UPDATE;
          IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, r_caracteres.VAL_REEM, l_startPos);
          l_startPos := l_pos + l_longitud;
          l_pos := INSTR(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, l_startPos);
      END LOOP;
  END LOOP;
  CLOSE c_caracteres;
  COMMIT;
END LOOP;
CLOSE c_paquetes;

END IMP_PR_REEMP_CARAC_ESPEC2;

PROCEDURE IMP_PR_REEMP_CARAC_ESPEC_BR IS

-- Variables a utilizar
l_CLOB              CLOB;
l_correlativo       NUMBER;

CURSOR c_paquetes IS
SELECT COD_CONS, XML_CONS
FROM IMP_PAQUE_DOCUM_BOREC;

r_paquetes c_paquetes%ROWTYPE;

CURSOR c_caracteres IS
SELECT VAL_CARA, VAL_REEM
FROM IMP_CARAC_REEMP
WHERE EST_CARE = '1'
ORDER BY ORD_REEM;

r_caracteres c_caracteres%ROWTYPE;

l_pos    NUMBER := 1;
l_startPos   NUMBER := 1;
l_longitud   NUMBER;

BEGIN

/* Iteramos sobre cada una de las lineas del paquete documentario */
OPEN c_paquetes;
LOOP
FETCH c_paquetes INTO r_paquetes;
EXIT WHEN c_paquetes%NOTFOUND;


  /* Iteramos sobre cada uno de los caracteres especiales a reemplazar */
  OPEN c_caracteres;
  LOOP
  FETCH c_caracteres INTO r_caracteres;
  EXIT WHEN c_caracteres%NOTFOUND;
      /*  */
      l_startPos := 1;
      l_longitud := LENGTH(r_caracteres.VAL_REEM);
      l_pos := INSTR(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, l_startPos);
      WHILE(l_pos != 0) LOOP
          SELECT XML_CONS
          INTO l_CLOB
          FROM IMP_PAQUE_DOCUM_BOREC
          WHERE COD_CONS = r_paquetes.COD_CONS
--          AND   NUM_PADO = r_paquetes.NUM_PADO
          FOR UPDATE;
          IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, r_caracteres.VAL_REEM, l_startPos);
          l_startPos := l_pos + l_longitud;
          l_pos := INSTR(r_paquetes.XML_CONS, r_caracteres.VAL_CARA, l_startPos);
      END LOOP;
  END LOOP;
  CLOSE c_caracteres;
  COMMIT;
END LOOP;
CLOSE c_paquetes;

END IMP_PR_REEMP_CARAC_ESPEC_BR;

PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_totalArchivos IN NUMBER) IS
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_numeroArchivo  INTEGER;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);
v_Clob           VARCHAR2(4000);
t_Clob           CLOB;
l_directorio     VARCHAR2(100);
l_nombreArchivo    VARCHAR2(50);
l_extensionArchivo VARCHAR2(10);
l_nombreCompletoArchivo VARCHAR2(100);
l_filaInicio     NUMBER := 0;
l_filaFin        NUMBER := 0;
l_totalFilas     NUMBER;
l_subtotalFilas  NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

CURSOR c1(v_filaInicio NUMBER, v_filaFin NUMBER) IS
SELECT
XML_CONS
FROM IMP_PAQUE_DOCUM
WHERE COR_PADO > v_filaInicio
AND COR_PADO <= v_filaFin
ORDER BY COR_PADO;

BEGIN

  -- Obtenemos la informacion del directorio y nombre del archivo
  -- XML conteniendo el paquete documentario del SiCC
  SELECT DIR_ENSA, NOM_ARCH, EXT_ARCH
  INTO l_directorio, l_nombreArchivo, l_extensionArchivo
  FROM IMP_ARCHI_IMPRE
  WHERE COD_ARIM = 'OUT';

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_OUT_DIR AS ''' || l_directorio || '''';

  -- Iteramos la creacion del archivo dependiendo de la cantidad
  -- de archivos a generar la cual es pasado como parametro
  SELECT MAX(COR_PADO) INTO l_totalFilas FROM IMP_PAQUE_DOCUM;

  -- Inicializamos las variables
  l_numeroArchivo := 0;
  l_subtotalFilas := ROUND(l_totalFilas / p_totalArchivos);
  l_filaInicio := 0;
  l_filaFin := l_subtotalFilas;

  -- Iteramos por la cantidad de archivos a generar
  FOR i IN 1..p_totalArchivos LOOP

      -- Abrimos el cursor y el archivo a crear
      l_numeroArchivo := l_numeroArchivo + 1;
      OPEN C1(l_filaInicio, l_filaFin);
   l_nombreCompletoArchivo := l_nombreArchivo || '.' || TO_CHAR(l_numeroArchivo) || '.' || l_extensionArchivo;
      l_output := UTL_FILE.fopen ('XML_OUT_DIR', l_nombreCompletoArchivo, 'wb', 32760);

      -- Escribimos la cabecera y el tag inicial de la raiz del XML
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<?xml version="1.0" encoding="iso-8859-1"?>'), TRUE);
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<spoolpd>'), TRUE);
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);

      -- Iteramos sobre el cursor
      LOOP
        FETCH C1 INTO t_clob;
        EXIT WHEN C1%NOTFOUND;
        l_length := DBMS_LOB.GETLENGTH(T_CLOB);
        --DBMS_OUTPUT.PUT_LINE(l_length);
        position := 1;
        l_offset := 1;
        l_amt := 4000;

        -- Escribimos los bloques en el archivo
        WHILE (l_offset <= l_length) LOOP
          IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
            --dbms_output.put_line ('l_amt: ' || l_amt);
            --dbms_output.put_line ('l_offset: ' || l_offset);
            dbms_lob.read (t_clob, l_amt, l_offset, x);
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
            l_offset := l_offset + l_amt;
            position := position + 4000;
            x := null;
        END LOOP;

        -- Escribimos el cambio de linea en el archivo
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
      END LOOP;

      -- Escribimos el tag de cierre del xml
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('</spoolpd>'), TRUE);

      -- Cerramos el archivo
      UTL_FILE.fclose (l_output);

      -- Cerramos el cursor
      CLOSE C1;

      -- Reasignamos los valores de inicio y fin de filas
      l_filaInicio := l_filaFin;
      l_filaFin := l_filaFin + l_subtotalFilas;

  END LOOP;

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.WRITE_ERROR THEN
        l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PAQUE_DOCUM_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_PAQUE_DOCUM_TO_FILE;

/* Eliminado por el cambio en PA LB (CHR - 23/07/2008)
PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2, p_totalArchivos IN NUMBER IS
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_numeroArchivo  INTEGER;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);
v_Clob           VARCHAR2(4000);
t_Clob           CLOB;
l_filaInicio     NUMBER := 0;
l_filaFin        NUMBER := 0;
l_totalFilas     NUMBER;
l_subtotalFilas  NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

CURSOR c1(v_filaInicio NUMBER, v_filaFin NUMBER) IS
SELECT
XML_CONS
FROM IMP_PAQUE_DOCUM
WHERE COR_PADO > v_filaInicio
AND COR_PADO <= v_filaFin
ORDER BY COR_PADO;

BEGIN

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_OUT_DIR AS ''' || p_directorio || '''';

  -- Iteramos la creacion del archivo dependiendo de la cantidad
  -- de archivos a generar la cual es pasado como parametro
  SELECT MAX(COR_PADO) INTO l_totalFilas FROM IMP_PAQUE_DOCUM;

  -- Inicializamos las variables
  l_numeroArchivo := 0;
  l_subtotalFilas := ROUND(l_totalFilas / p_totalArchivos);
  l_filaInicio := 0;
  l_filaFin := l_subtotalFilas;

  -- Iteramos por la cantidad de archivos a generar
  FOR i IN 1..p_totalArchivos LOOP

      -- Abrimos el cursor y el archivo a crear
      l_numeroArchivo := l_numeroArchivo + 1;
      OPEN C1(l_filaInicio, l_filaFin);
      l_output := UTL_FILE.fopen ('XML_OUT_DIR', p_nombreArchivo || '.' || TO_CHAR(l_numeroArchivo), 'wb', 32760);

      -- Escribimos la cabecera y el tag inicial de la raiz del XML
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<?xml version="1.0" encoding="iso-8859-1"?>'), TRUE);
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<spoolpd>'), TRUE);
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);

      -- Iteramos sobre el cursor
      LOOP
        FETCH C1 INTO t_clob;
        EXIT WHEN C1%NOTFOUND;
        l_length := DBMS_LOB.GETLENGTH(T_CLOB);
        position := 1;
        l_offset := 1;
        l_amt := 4000;

        -- Escribimos los bloques en el archivo
        WHILE (l_offset <= l_length) LOOP
          IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
            dbms_lob.read (t_clob, l_amt, l_offset, x);
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
            l_offset := l_offset + l_amt;
            position := position + 4000;
            x := null;
        END LOOP;

        -- Escribimos el cambio de linea en el archivo
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
      END LOOP;

      -- Escribimos el tag de cierre del xml
      UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('</spoolpd>'), TRUE);

      -- Cerramos el archivo
      UTL_FILE.fclose (l_output);

      -- Cerramos el cursor
      CLOSE C1;

      -- Reasignamos los valores de inicio y fin de filas
      l_filaInicio := l_filaFin;
      l_filaFin := l_filaFin + l_subtotalFilas;

  END LOOP;

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.WRITE_ERROR THEN
        l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PAQUE_DOCUM_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_PAQUE_DOCUM_TO_FILE;
*/

/**************************************************************************
Descripcion         : Envia la informacion del paquete documentario almacenado
                      en la tabla IMP_PAQUE_DOCU a un archivo plano. El nombre
                      y ruta de destino del archivo son pasados como parametros.
Fecha Creacion      : 23/07/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_nombreArchivo IN VARCHAR2,
                                     p_directorio IN VARCHAR2,
                                     p_numeroDocumento NUMBER := 1) IS
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);
v_Clob           VARCHAR2(4000);
t_Clob           CLOB;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);
lv_ordenamientoRutasTerritorio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorOrdenamientoRutasTerritorio');

CURSOR c1 IS
SELECT   P.XML_CONS
    FROM IMP_PAQUE_DOCUM P
   WHERE P.NUM_PADO = p_numeroDocumento
ORDER BY P.NUM_PADO,
         P.COR_PADO;

CURSOR c2 IS
SELECT   P.XML_CONS
    FROM IMP_PAQUE_DOCUM P,
         MAE_CLIEN B,
         MAE_CLIEN_UNIDA_ADMIN C,
         ZON_TERRI_ADMIN D,
         APP_RUTAS_TERRI E,
         APP_RUTAS_TRANS F
   WHERE P.COD_CLIE = B.COD_CLIE
     AND B.OID_CLIE = C.CLIE_OID_CLIE
     AND C.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
     AND D.TERR_OID_TERR = E.TERR_OID_TERR
     AND E.RUTR_OID_RUTA_TRAN = F.OID_RUTA_TRAN
     AND C.IND_ACTI = 1
     AND C.PERD_OID_PERI_FIN IS NULL
     AND P.NUM_PADO = p_numeroDocumento
ORDER BY F.NUM_SECU,
         E.NUM_SECU,
         P.COD_CLIE;

BEGIN

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_OUT_DIR AS ''' || p_directorio || '''';
    -- Abrimos el cursor y el archivo a crear
    l_output := UTL_FILE.fopen ('XML_OUT_DIR', p_nombreArchivo, 'wb', 32760);

    -- Escribimos la cabecera y el tag inicial de la raiz del XML
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<?xml version="1.0" encoding="iso-8859-1"?>'), TRUE);
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<spoolpd>'), TRUE);
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);

    IF lv_ordenamientoRutasTerritorio = 'S' THEN
        -- Ordenamiento en base a APP_RUTAS_TERRI
        OPEN C2;
        -- Iteramos sobre el cursor
        LOOP
          FETCH C2 INTO t_clob;
          EXIT WHEN C2%NOTFOUND;
          l_length := DBMS_LOB.GETLENGTH(T_CLOB);
          position := 1;
          l_offset := 1;
          l_amt := 4000;

          -- Escribimos los bloques en el archivo
          WHILE (l_offset <= l_length) LOOP
            IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
              dbms_lob.read (t_clob, l_amt, l_offset, x);
              UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
              l_offset := l_offset + l_amt;
              position := position + 4000;
              x := null;
          END LOOP;

          -- Escribimos el cambio de linea en el archivo
          UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        END LOOP;

        -- Cerramos el cursor
        CLOSE C2;
    ELSE
        -- Ordenamiento tomando el orden del paqdoc original
        OPEN C1;
        -- Iteramos sobre el cursor
        LOOP
          FETCH C1 INTO t_clob;
          EXIT WHEN C1%NOTFOUND;
          l_length := DBMS_LOB.GETLENGTH(T_CLOB);
          position := 1;
          l_offset := 1;
          l_amt := 4000;

          -- Escribimos los bloques en el archivo
          WHILE (l_offset <= l_length) LOOP
            IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
              dbms_lob.read (t_clob, l_amt, l_offset, x);
              UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
              l_offset := l_offset + l_amt;
              position := position + 4000;
              x := null;
          END LOOP;

          -- Escribimos el cambio de linea en el archivo
          UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        END LOOP;

        -- Cerramos el cursor
        CLOSE C1;
    END IF;

    -- Escribimos el tag de cierre del xml
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('</spoolpd>'), TRUE);

    -- Cerramos el archivo
    UTL_FILE.fclose (l_output);

    EXCEPTION
    WHEN UTL_FILE.INTERNAL_ERROR THEN
        l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILEHANDLE THEN
        l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_MODE THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.WRITE_ERROR THEN
           l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
           RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_OPERATION THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_PATH THEN
        l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PAQUE_DOCUM_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_PAQUE_DOCUM_TO_FILE;

/**************************************************************************
Descripcion         : Envia la informacion del paquete documentario almacenado
                      en la tabla IMP_PAQUE_DOCU a N archivos planos. El nombre
                      y ruta de destino del archivo son pasados como parametros.
Fecha Creacion      : 01/07/2009
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Inclusion del comentario
***************************************************************************/
PROCEDURE IMP_PR_PAQUE_DOCUM_TO_FILE(p_nombreArchivo IN VARCHAR2,
                                     p_directorio IN VARCHAR2,
                                     p_numeroDocumento NUMBER := 1,
                                     p_totalArchivos NUMBER := 1) IS
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);
v_Clob           VARCHAR2(4000);
t_Clob           CLOB;
l_numeroArchivo  INTEGER;
l_filaInicio     NUMBER := 0;
l_filaFin        NUMBER := 0;
l_totalFilas     NUMBER;
l_subtotalFilas  NUMBER;
l_nombre         VARCHAR2(100);
l_extension      VARCHAR2(100);
l_separador      VARCHAR2(1) := '.';
l_nombreCompleto VARCHAR2(100);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

CURSOR c1(filaInicio NUMBER, filaFin NUMBER) IS
SELECT   P.XML_CONS
    FROM IMP_PAQUE_DOCUM P
   WHERE P.NUM_PADO = p_numeroDocumento
     AND P.COR_PADO > filaInicio
     AND P.COR_PADO <= filaFin
ORDER BY P.NUM_PADO,
         P.COR_PADO;

BEGIN

    -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
    EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_OUT_DIR AS ''' || p_directorio || '''';

    -- Separamos el nombre del archivo
    IF (INSTR(p_nombreArchivo, l_separador)) <> 0 THEN
        l_nombre := SUBSTR(p_nombreArchivo, 1, INSTR(p_nombreArchivo, l_separador, -1) - 1);
        l_extension := SUBSTR(p_nombreArchivo, INSTR(p_nombreArchivo, l_separador, -1) + 1, LENGTH(p_nombreArchivo));
    END IF;

    -- Iteramos la creacion del archivo dependiendo de la cantidad
    -- de archivos a generar la cual es pasado como parametro
    SELECT MAX(COR_PADO)
    INTO l_totalFilas
    FROM IMP_PAQUE_DOCUM
    WHERE NUM_PADO = p_numeroDocumento;

    -- Inicializamos las variables
    l_numeroArchivo := 0;
    l_subtotalFilas := ROUND(l_totalFilas / p_totalArchivos);
    l_filaInicio := 0;
    l_filaFin := l_subtotalFilas;

    -- Iteramos por la cantidad de archivos a generar
    FOR i IN 1..p_totalArchivos LOOP

        -- Abrimos el cursor y el archivo a crear
        l_numeroArchivo := l_numeroArchivo + 1;

        -- Creamos el nombre del archivo
        IF p_totalArchivos > 1 THEN
            l_nombreCompleto := l_nombre || '_' || TO_CHAR(l_numeroArchivo) || l_separador || l_extension;
            l_output := UTL_FILE.fopen ('XML_OUT_DIR', l_nombreCompleto, 'wb', 32760);
        ELSE
            l_output := UTL_FILE.fopen ('XML_OUT_DIR', p_nombreArchivo, 'wb', 32760);
        END IF;

        -- Escribimos la cabecera y el tag inicial de la raiz del XML
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<?xml version="1.0" encoding="iso-8859-1"?>'), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('<spoolpd>'), TRUE);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);

        OPEN c1(l_filaInicio, l_filaFin);
        -- Iteramos sobre el cursor
        LOOP
            FETCH c1 INTO t_clob;
            EXIT WHEN C1%NOTFOUND;
            l_length := DBMS_LOB.GETLENGTH(t_clob);
            position := 1;
            l_offset := 1;
            l_amt := 4000;

            -- Escribimos los bloques en el archivo
            WHILE (l_offset <= l_length) LOOP
              IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
                dbms_lob.read (t_clob, l_amt, l_offset, x);
                UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
                l_offset := l_offset + l_amt;
                position := position + 4000;
                x := null;
            END LOOP;

            -- Escribimos el cambio de linea en el archivo
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(10)), TRUE);
        END LOOP;

        -- Escribimos el tag de cierre del xml
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw('</spoolpd>'), TRUE);

        -- Cerramos el archivo
        UTL_FILE.fclose (l_output);

        -- Cerramos el cursor
        CLOSE c1;

        -- Reasignamos los valores de inicio y fin de filas
        l_filaInicio := l_filaFin;
        l_filaFin := l_filaFin + l_subtotalFilas;

    END LOOP;

    EXCEPTION
    WHEN UTL_FILE.INTERNAL_ERROR THEN
        l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILEHANDLE THEN
        l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_MODE THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.WRITE_ERROR THEN
           l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
           RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_OPERATION THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_PATH THEN
        l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN OTHERS THEN
         RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PAQUE_DOCUM_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_PAQUE_DOCUM_TO_FILE;


PROCEDURE IMP_PR_PROCE_COMPA(p_totalArchivos IN NUMBER) IS
BEGIN

  -- Invocamos al store encargado de cargar la data del archivo
  -- conteniendo el paquete documentario del SiCC en la tabla
  -- IMP_PAQUE_DOCUM_SICC
  IMP_PR_CARGA_XML_SICC;

  -- Invocamos al store encargado de cargar la data del archivo
  -- conteniendo el Cupon de Pago en la tabla IMP_PAQUE_DOCUM_CUPON
  IMP_PR_CARGA_XML_CUPON;

  -- Invocamos al store encargado de hacer el "merge" entre la
  -- tabla conteniendo el xml de SiCC, la tabla conteniendo el
  -- XML del paquete documentario de Privilege y la tabla conteniendo
  -- el XML del Cupon de Pago
  IMP_PR_COMPA_PAQUE_DOCUM;

  -- Finalmente volcamos el contenido de la tabla resultante
  IMP_PR_PAQUE_DOCUM_TO_FILE(p_totalArchivos);

  EXCEPTION
    WHEN OTHERS THEN
       RAISE;
END IMP_PR_PROCE_COMPA;

PROCEDURE IMP_PR_CARGA_NOTA_CREDI
(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2, p_numeroSerie IN NUMBER) AS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;

l_textoCodigoInterno    VARCHAR2(15) := 'CODIGO INTERNO:';
l_saltoPagina           CHAR(1) := CHR(12);
l_numDocumentoInterno   NUMBER;

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

BEGIN
  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY NOTA_CRED_DIR AS ''' || p_directorio || '''';

  -- Inicializamos el BFILE locator para lectura
  l_FileLocator := BFILENAME('NOTA_CRED_DIR', p_nombreArchivo);
  DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

  -- Usamos un CLOB temporal para cargar la informacion del archivo
  DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

  -- Cargamos todo el archivo en un character LOB Temporal.
  DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                            l_FileLocator,
                            DBMS_LOB.GETLENGTH(l_FileLocator),
                            src_offset,
                            dst_offset,
                            charset_id,
                            lang_ctx,
                            warning);

  -- Cerramos el archivo
  DBMS_LOB.FILECLOSE(l_FileLocator);

  -- Obtenemos el tama?o del CLOB cargado
  l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

  -- Una vez que tenemos todo el archivo en una CLOB temporal
  -- podemos usar las funciones del paquete DBMS_LOB para obtener
  -- la informacion del numero de documento interno

  -- Buscamos el numero de documento interno
  --DBMS_OUTPUT.PUT_LINE(DBMS_LOB.SUBSTR(l_CLOBSrc, 15, DBMS_LOB.INSTR(l_CLOBSrc, l_textoCodigoInterno, 1) + LENGTH(l_textoCodigoInterno)));
  l_numDocumentoInterno := TO_NUMBER(TRIM(DBMS_LOB.SUBSTR(l_CLOBSrc, 15, DBMS_LOB.INSTR(l_CLOBSrc, l_textoCodigoInterno, 1) + LENGTH(l_textoCodigoInterno))));

  l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                    l_saltoPagina,
                                    1);

  l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                  l_saltoPagina,
          --l_OffsetInicial + 1
                                  l_CLOBLength - 2); -- Restamos 2 para tomar el ultimo salto de pagina o el salto de pagina doble

  IF l_OffsetFinal = 0 THEN
      l_OffsetFinal := l_CLOBLength;
  END IF;

  INSERT INTO IMP_NOTA_CREDI (NUM_SERI, NUM_DOCU_INTE, TEX_NOTA_CRED)
  VALUES(p_numeroSerie, l_numDocumentoInterno, EMPTY_CLOB())
  RETURNING TEX_NOTA_CRED INTO l_CLOBDest;

  -- Copiamos la porcion del CLOB original al parcial
  DBMS_LOB.COPY(l_CLOBDest,
                l_CLOBSrc,
                l_OffsetFinal - l_OffsetInicial,
                1,
                l_OffsetInicial);

  -- Liberamos los rescursos usados por el CLOB temporal
  DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

  COMMIT;

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || p_nombreArchivo;
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILENAME THEN
     l_mensajeError:='EL ARCHIVO ' || p_nombreArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
         -- Close the cursor and file, and reraise.
         DBMS_LOB.FILECLOSE(l_FileLocator);
         DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_XML_SICC: '||substr(sqlerrm,1,250));
END IMP_PR_CARGA_NOTA_CREDI;


PROCEDURE IMP_PR_CARGA_NOTA_CREDI
(p_nombreArchivo IN VARCHAR2,
 p_directorio IN VARCHAR2,
 p_numeroSerie IN NUMBER,
 p_codigoPeriodo IN VARCHAR2,
 p_fechaFacturacion IN VARCHAR2) AS
-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;

l_correlativo               NUMBER;
l_contador                  NUMBER := 0;
l_textoCodigoInterno        VARCHAR2(50);
l_inicioArchivo             VARCHAR2(50);
l_finArchivo                VARCHAR2(50);
l_longitudCodigoInterno     VARCHAR2(50);
l_saltoPagina               CHAR(1) := CHR(12);
l_cambioLinea               CHAR(1) := CHR(10);
l_numDocumentoInterno       NUMBER;
l_codigoCliente             VARCHAR2(15);
l_textoNumeroSolicitud      VARCHAR2(50) := 'N.PEDIDO: ';
l_numeroSolicitud           NUMBER(10);
l_numeroSolicitudRef        NUMBER(10);
l_textoNumeroSolicitudRef   VARCHAR2(50) := 'Bol Despacho Ref : ';
l_numeroDocumentoLegal      NUMBER(10);
l_textoFacturaRef           VARCHAR2(50) := 'Factura Referencia:';
l_longitudNumeroSolicitud   NUMBER := 9;

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

BEGIN
  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY NOTA_CRED_DIR AS ''' || p_directorio || '''';

  -- Inicializamos el BFILE locator para lectura
  l_FileLocator := BFILENAME('NOTA_CRED_DIR', p_nombreArchivo);
  DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

  -- Usamos un CLOB temporal para cargar la informacion del archivo
  DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

  -- Cargamos todo el archivo en un character LOB Temporal.
  DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                            l_FileLocator,
                            DBMS_LOB.GETLENGTH(l_FileLocator),
                            src_offset,
                            dst_offset,
                            charset_id,
                            lang_ctx,
                            warning);

  -- Cerramos el archivo
  DBMS_LOB.FILECLOSE(l_FileLocator);

  -- Obtenemos el tama?o del CLOB cargado
  l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

  -- Buscamos el numero de documento interno
  -- Obtenemos las cadenas de inicio y fin del archivo, asi como los que nos permiten
  -- extraer el codigo interno de la nota de credito
  l_inicioArchivo := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'inicioArchivo');
  l_finArchivo    := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'finArchivo');
  l_textoCodigoInterno    := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'textoCodigoInterno');
  l_longitudCodigoInterno := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'longitudCodigoInterno');

  -- Obtenemos el offset inicial
  l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                    l_inicioArchivo,
                                    1);

  -- LOOP
  WHILE l_CLOBLength > 0 AND l_OffsetFinal != 0
  LOOP

  l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                  l_saltoPagina,
                                  l_OffsetInicial);

  IF l_OffsetFinal = 0 THEN
      l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                      l_finArchivo,
                                      l_OffsetInicial);
  END IF;

  IF (l_OffsetFinal = 0) OR (l_OffsetInicial = l_OffsetFinal) THEN
      EXIT;
  END IF;

  l_codigoCliente := NULL;
  l_numDocumentoInterno := 0;
  l_numeroSolicitud := NULL;
  l_numeroSolicitudRef := NULL;
  l_numeroDocumentoLegal := NULL;

  -- Obtenemos el valor del correlativo
  SELECT IMP_SEQ_NOTA_CREDI.NEXTVAL
  INTO l_correlativo
  FROM DUAL;

  INSERT INTO IMP_NOTA_CREDI (
  COR_NOTA_CRED,
  COD_CLIE,
  NUM_SERI,
  NUM_DOCU_INTE,
  VAL_NUME_SOLI,
  VAL_NUME_SOLI_REFE,
  VAL_NUME_DOCU_LEGA,
  TEX_NOTA_CRED
  )
  VALUES(
  l_correlativo,
  l_codigoCliente,
  p_numeroSerie,
  l_numDocumentoInterno,
  l_numeroSolicitud,
  l_numeroSolicitudRef,
  l_numeroDocumentoLegal,
  EMPTY_CLOB()
  )
  RETURNING TEX_NOTA_CRED INTO l_CLOBDest;

  IF l_contador = 0 THEN
  -- Copiamos la porcion del CLOB original al parcial
  DBMS_LOB.COPY(l_CLOBDest,
                l_CLOBSrc,
                l_OffsetFinal - (l_OffsetInicial + LENGTH(l_inicioArchivo)),
                1,
                (l_OffsetInicial + LENGTH(l_inicioArchivo)));
  ELSE
  -- Copiamos la porcion del CLOB original al parcial
  DBMS_LOB.COPY(l_CLOBDest,
                l_CLOBSrc,
                l_OffsetFinal - (l_OffsetInicial + LENGTH(l_saltoPagina)),
                1,
                (l_OffsetInicial + LENGTH(l_saltoPagina)));
  END IF;

  -- Una vez que tenemos todo el archivo en una CLOB temporal
  -- podemos usar las funciones del paquete DBMS_LOB para obtener
  -- la informacion del numero de documento interno
  l_numDocumentoInterno := TO_NUMBER(TRIM(DBMS_LOB.SUBSTR(l_CLOBDest, TO_NUMBER(l_longitudCodigoInterno), DBMS_LOB.INSTR(l_CLOBDest, l_textoCodigoInterno, 1) + LENGTH(l_textoCodigoInterno))));
  l_codigoCliente := TRIM(DBMS_LOB.SUBSTR(l_CLOBDest, 10, DBMS_LOB.INSTR(l_CLOBDest, '-', 1) - 35 ));
  l_numeroSolicitud := TO_NUMBER(TRIM(DBMS_LOB.SUBSTR(l_CLOBDest, TO_NUMBER(l_longitudNumeroSolicitud), DBMS_LOB.INSTR(l_CLOBDest, l_textoNumeroSolicitud, 1) + LENGTH(l_textoNumeroSolicitud))));

  -- Obtenemos la solicitud de referencia
  BEGIN
      l_numeroSolicitudRef := TO_NUMBER(TRIM(DBMS_LOB.SUBSTR(l_CLOBDest, TO_NUMBER(l_longitudNumeroSolicitud), DBMS_LOB.INSTR(l_CLOBDest, l_textoNumeroSolicitudRef, 1) + LENGTH(l_textoNumeroSolicitudRef))));
  EXCEPTION
  WHEN OTHERS THEN
      l_numeroSolicitudRef := NULL;
  END;

  l_numeroDocumentoLegal := IMP_FN_NUMER_DOCUM_LEGAL(l_numeroSolicitud, 32);

  UPDATE IMP_NOTA_CREDI X
  SET X.NUM_DOCU_INTE = NVL(l_numDocumentoInterno, 0),
      X.VAL_NUME_SOLI = l_numeroSolicitud,
   X.COD_CLIE = NVL(l_codigoCliente, '0'),
   X.VAL_NUME_DOCU_LEGA = l_numeroDocumentoLegal,
   X.VAL_NUME_SOLI_REFE = l_numeroSolicitudRef
  WHERE X.COR_NOTA_CRED = l_correlativo;


  -- Reemplazamos los valores de la nota de credito
  IF l_numeroSolicitudRef IS NOT NULL AND l_numeroDocumentoLegal IS NOT NULL THEN
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOBDest, l_numeroSolicitudRef, l_numeroDocumentoLegal, 1);
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOBDest, l_textoNumeroSolicitudRef, l_textoFacturaRef, 1);
  END IF;

  l_OffsetInicial := l_OffsetFinal + 1;
  l_contador := l_contador + 1;

  END LOOP;-- END LOOP

  -- Liberamos los rescursos usados por el CLOB temporal
  DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || p_nombreArchivo;
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILENAME THEN
     l_mensajeError:='EL ARCHIVO ' || p_nombreArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
         -- Close the cursor and file, and reraise.
         DBMS_LOB.FILECLOSE(l_FileLocator);
         DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_NOTA_CREDI: '||substr(sqlerrm,1,250));
END IMP_PR_CARGA_NOTA_CREDI;


PROCEDURE IMP_PR_NOTA_CREDI_TO_FILE
(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2, p_numeroSerie IN NUMBER) AS
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);
v_Clob           VARCHAR2(4000);
t_Clob           CLOB;

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

CURSOR C_NOTA_CRED IS
SELECT
TEX_NOTA_CRED
FROM IMP_NOTA_CREDI
WHERE NUM_SERI = p_numeroSerie
ORDER BY NUM_SERI, NUM_DOCU_INTE;

BEGIN

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY NOTA_CRED_OUT_DIR AS ''' || p_directorio || '''';

  l_output := UTL_FILE.fopen ('NOTA_CRED_OUT_DIR', p_nombreArchivo, 'wb', 32760);

  -- Escribimos los caracteres de inicio de impresio
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(27) || CHR(48) || CHR(27) || CHR(80)), TRUE);

  -- Iteramos sobre el cursor
  OPEN C_NOTA_CRED;
  LOOP
    FETCH C_NOTA_CRED INTO t_clob;
    EXIT WHEN C_NOTA_CRED%NOTFOUND;
    l_length := DBMS_LOB.GETLENGTH(T_CLOB);
    position := 1;
    l_offset := 1;
    l_amt := 4000;

    -- Escribimos los bloques en el archivo
    WHILE (l_offset < l_length) LOOP
        IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
        dbms_lob.read (t_clob, l_amt, l_offset, x);
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
        l_offset := l_offset + l_amt;
        position := position + 4000;
        x := null;
    END LOOP;

  END LOOP;

  -- Cerramos el cursor
  CLOSE C_NOTA_CRED;

  -- Escribimos el ultimo salto de pagina
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(CHR(12)), TRUE);

  -- Cerramos el archivo
  UTL_FILE.fclose (l_output);

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.WRITE_ERROR THEN
        l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PAQUE_DOCUM_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_NOTA_CREDI_TO_FILE;


PROCEDURE IMP_PR_NOTA_CREDI_TO_FILE
(p_nombreArchivo IN VARCHAR2,
 p_directorio IN VARCHAR2,
 p_numeroSerie IN NUMBER,
 p_codigoPeriodo IN VARCHAR2,
 p_fechaFacturacion IN VARCHAR2) AS

l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
position         INTEGER := 1;
l_length         NUMBER :=0 ;
x                VARCHAR2(32000);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError   VARCHAR2(500);
l_inicioArchivo  VARCHAR2(50);
l_finArchivo     VARCHAR2(50);
l_saltoPagina    VARCHAR2(50);
l_flagImprimeFacturados VARCHAR2(50);

CURSOR c_nota_cred IS
SELECT
COR_NOTA_CRED,
TEX_NOTA_CRED
FROM IMP_NOTA_CREDI
WHERE NUM_SERI = p_numeroSerie
AND IND_IMPR = 'N'
ORDER BY NUM_SERI, NUM_DOCU_INTE;

r_nota_cred c_nota_cred%ROWTYPE;

CURSOR C_NOTA_CRED_FACT IS
SELECT COR_NOTA_CRED,
       TEX_NOTA_CRED,
       CON.OID_SOLI_CABE,
       CON.VAL_NUME_SOLI,
       PED_SOLIC_CABEC_SECUE.NUM_SECU_FACT_DIAR,
       MC.OID_CLIE,
       MC.COD_CLIE,
    ZON_ZONA.COD_ZONA
  FROM IMP_NOTA_CREDI,
       PED_SOLIC_CABEC CON,
       PED_SOLIC_CABEC_SECUE,
       PED_TIPO_SOLIC_PAIS PTSP,
       PED_TIPO_SOLIC PTS,
       MAE_CLIEN MC,
    MAE_CLIEN_UNIDA_ADMIN,
    ZON_TERRI_ADMIN,
    ZON_TERRI,
    ZON_SECCI,
    ZON_ZONA,
    ZON_REGIO
 WHERE PTSP.TSOL_OID_TIPO_SOLI = PTS.OID_TIPO_SOLI
   AND OID_TIPO_SOLI_PAIS IN (SELECT TSPA_OID_TIPO_SOLI_PAIS
                                FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS)
   AND CON.TSPA_OID_TIPO_SOLI_PAIS = PTSP.OID_TIPO_SOLI_PAIS
   AND CON.PERD_OID_PERI = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(p_codigoPeriodo)
   AND CON.CLIE_OID_CLIE = MC.OID_CLIE
   AND TO_CHAR(CON.FEC_FACT, 'DD/MM/YYYY') = p_fechaFacturacion
   AND CON.NUM_UNID_ATEN_TOTA > 0
   AND PED_SOLIC_CABEC_SECUE.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
   AND MAE_CLIEN_UNIDA_ADMIN.CLIE_OID_CLIE = MC.OID_CLIE
   AND MAE_CLIEN_UNIDA_ADMIN.ZTAD_OID_TERR_ADMI = ZON_TERRI_ADMIN.OID_TERR_ADMI
   AND ZON_TERRI_ADMIN.TERR_OID_TERR = ZON_TERRI.OID_TERR
   AND ZON_TERRI_ADMIN.ZSCC_OID_SECC = ZON_SECCI.OID_SECC
   AND ZON_SECCI.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
   AND ZON_ZONA.ZORG_OID_REGI = ZON_REGIO.OID_REGI
   AND MAE_CLIEN_UNIDA_ADMIN.IND_ACTI = 1
   AND IMP_NOTA_CREDI.COD_CLIE = MC.COD_CLIE
   AND IMP_NOTA_CREDI.NUM_SERI = p_numeroSerie
   AND IMP_NOTA_CREDI.IND_IMPR = 'N'
ORDER BY COD_ZONA, NUM_SECU_FACT_DIAR, IMP_NOTA_CREDI.NUM_DOCU_INTE;

r_nota_cred_fact c_nota_cred_fact%ROWTYPE;

BEGIN

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY NOTA_CRED_OUT_DIR AS ''' || p_directorio || '''';

  l_output := UTL_FILE.fopen ('NOTA_CRED_OUT_DIR', p_nombreArchivo, 'wb', 32760);

  -- Obtenemos las cadenas de inicio y fin del archivo, y el salto de pagina
  l_inicioArchivo := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'inicioArchivoConsolidado');
  l_finArchivo    := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'finArchivoConsolidado');
  l_saltoPagina   := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'saltoPagina');
  l_flagImprimeFacturados := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('NOT', 'flagImprimeFacturados');

  -- Escribimos los caracteres de inicio de impresion
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_inicioArchivo), TRUE);

  IF l_flagImprimeFacturados IS NOT NULL AND l_flagImprimeFacturados = 'S' THEN
  -- Imprimimos solo los facturados en la fecha y periodo correspondiente

   -- Iteramos sobre el cursor
   OPEN c_nota_cred_fact;
   LOOP
     FETCH c_nota_cred_fact INTO r_nota_cred_fact;
     EXIT WHEN c_nota_cred_fact%NOTFOUND;
     l_length := DBMS_LOB.GETLENGTH(r_nota_cred_fact.TEX_NOTA_CRED);
     position := 1;
     l_offset := 1;
     l_amt := 4000;

     -- Escribimos los bloques en el archivo
     WHILE (l_offset < l_length) LOOP
         IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
         dbms_lob.read (r_nota_cred_fact.TEX_NOTA_CRED, l_amt, l_offset, x);
         UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
         l_offset := l_offset + l_amt;
         position := position + 4000;
         x := null;
     END LOOP;

     -- Escribimos el salto de pagina
     UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_saltoPagina), TRUE);

     -- Actualizamos el indicador de impreso
     UPDATE IMP_NOTA_CREDI
  SET IND_IMPR = 'S'
  WHERE COR_NOTA_CRED = r_nota_cred_fact.COR_NOTA_CRED;

   END LOOP;

   -- Cerramos el cursor
   CLOSE c_nota_cred_fact;

    ELSE

   -- Iteramos sobre el cursor
   OPEN c_nota_cred;
   LOOP
     FETCH c_nota_cred INTO r_nota_cred;
     EXIT WHEN c_nota_cred%NOTFOUND;
     l_length := DBMS_LOB.GETLENGTH(r_nota_cred.TEX_NOTA_CRED);
     position := 1;
     l_offset := 1;
     l_amt := 4000;

     -- Escribimos los bloques en el archivo
     WHILE (l_offset < l_length) LOOP
         IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
         dbms_lob.read (r_nota_cred.TEX_NOTA_CRED, l_amt, l_offset, x);
         UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
         l_offset := l_offset + l_amt;
         position := position + 4000;
         x := null;
     END LOOP;

     -- Escribimos el salto de pagina
     UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_saltoPagina), TRUE);

     -- Actualizamos el indicador de impreso
     UPDATE IMP_NOTA_CREDI
  SET IND_IMPR = 'S'
  WHERE COR_NOTA_CRED = r_nota_cred.COR_NOTA_CRED;

   END LOOP;

   -- Cerramos el cursor
   CLOSE C_NOTA_CRED;

  END IF;

  -- Escribimos los caracteres de fin de archivo
  UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_finArchivo), TRUE);

  -- Cerramos el archivo
  UTL_FILE.fclose (l_output);

  EXCEPTION
 WHEN UTL_FILE.INTERNAL_ERROR THEN
     l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_FILEHANDLE THEN
     l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_MODE THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.WRITE_ERROR THEN
        l_mensajeError:='ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_OPERATION THEN
     l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN UTL_FILE.INVALID_PATH THEN
     l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
     RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

 WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_PAQUE_DOCUM_TO_FILE: '||substr(sqlerrm,1,250));

END IMP_PR_NOTA_CREDI_TO_FILE;


/***************************PROCEDIMIENTO QUE LLENA TABLA CABECERA Y DETALLE DE PEDIDO *******************/
/*********************************************************************************************************/

PROCEDURE IMP_PR_PROCE_DETAL_FACTU(p_codigoPais IN VARCHAR2,
                                   p_codigoPeriodo IN VARCHAR2,
                                   p_fechaFacturacion IN VARCHAR2,
                                   p_codigoMarca IN VARCHAR2,
                                   p_codigoCanal IN VARCHAR2,
                                   p_tipoSolicitud IN VARCHAR2)IS

id_solicitud NUMBER;
cod_regi   ZON_REGIO.COD_REGI%TYPE;
cod_terri  ZON_TERRI.COD_TERR%TYPE;
desc_regi  ZON_REGIO.DES_REGI%TYPE;
cod_zona   ZON_ZONA.COD_ZONA%TYPE;
desc_zona  ZON_ZONA.DES_ZONA%TYPE;
cod_secc   ZON_SECCI.COD_SECC%TYPE;
totalFacturaAPagar IMP_DETAL_FACTU_DETAL.Val_Impo_Con_Desc%TYPE;

SUB_TOT_SOLIC          NUMBER;
SUB_TOT_ATND           NUMBER;
SUB_TOT_TOT_CATALOGO   NUMBER;
SUB_TOT_DTO            NUMBER;
SUB_TOT_TOT_CON_DTO    NUMBER;
SUB_TOT_PAGO_POST      NUMBER;
TOT_GRATIS             NUMBER;
TOT_PAGO_POSTE         NUMBER;

totalFactura Number;
totalConDto Number;
dsto_tota_Pie Number;
fechaVencimiento DATE;
lsCodigoPeriodo VARCHAR2(6);
lnIdPais      NUMBER;
lnIdMarca     NUMBER;
lnIdCanal     NUMBER;
saldo_anterior NUMBER;
saldo_a_favor  NUMBER;
pago_posterior NUMBER;
cargoServicios NUMBER;
indicadorImpuestosGratis VARCHAR2(1) := 'N';

l_oidPeriodo                NUMBER;
l_indicadorEnvioLarissa     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_numeroLoteFacturacion     NUMBER;

CURSOR c_consultoras(oidPeriodo NUMBER,
                     indicadorEnvioLarissa VARCHAR2,
                     numeroLoteFacturacion NUMBER) IS
SELECT CON.VAL_NUME_SOLI AS NUM_SOLIC,
       CON.OID_SOLI_CABE AS OID_SOLI_CABE,
       CON.FEC_FACT AS FECHA_FACTURACION,
       BPAIS.DES_PAIS AS DESC_PAIS,
       MC.OID_CLIE AS OID_CLIE,
       MC.COD_CLIE AS COD_CONSULTORA,
       MC.VAL_NOM1 AS NOMBRE_CONSULTORA1,
       MC.VAL_NOM2 AS NOMBRE_CONSULTORA2,
       MC.VAL_APE1 AS APE_CONSULTORA1,
       MC.VAL_APE2 AS APE_CONSULTORA2,
       p_codigoPeriodo AS COD_PERIODO,
       BPAIS.COD_PAIS AS COD_PAIS,
       MAE_CLIEN_IDENT.NUM_DOCU_IDEN AS NUM_DOCU_IDEN,
       CON.VAL_IMPO_FLET_TOTA_LOCA AS FLETE,
       CON.VAL_IMPO_DESC_1_TOTA_LOCA AS DTO1,
       CON.VAL_IMPO_REDO_LOCA AS IMPO_REDO_LOCA,
       PED_SOLIC_CABEC_SECUE.NUM_SECU_FACT_DIAR
FROM PED_SOLIC_CABEC CON,
     PED_TIPO_SOLIC_PAIS PTSP,
     PED_TIPO_SOLIC PTS,
     MAE_CLIEN MC,
     BAS_PAIS BPAIS,
     SEG_PAIS SPAIS,
     MAE_CLIEN_IDENT,
     PED_SOLIC_CABEC_SECUE
WHERE BPAIS.COD_PAIS = p_codigoPais
AND CON.PERD_OID_PERI = oidPeriodo
AND CON.FEC_FACT = TO_DATE(p_fechaFacturacion, 'dd/mm/yyyy')
AND PTSP.TSOL_OID_TIPO_SOLI = PTS.OID_TIPO_SOLI
AND OID_TIPO_SOLI_PAIS IN (SELECT TSPA_OID_TIPO_SOLI_PAIS FROM INT_LAR_TIPO_SOLICI_PEDIDO_DIS)
AND CON.TSPA_OID_TIPO_SOLI_PAIS = PTSP.OID_TIPO_SOLI_PAIS
AND CON.IND_INTE_LARI_GENE = indicadorEnvioLarissa
AND (numeroLoteFacturacion IS NULL OR CON.NUM_LOTE_FACT = numeroLoteFacturacion)
AND CON.CLIE_OID_CLIE = MC.OID_CLIE
AND BPAIS.COD_PAIS = SPAIS.COD_PAIS
AND MAE_CLIEN_IDENT.VAL_IDEN_DOCU_PRIN = 1
AND MAE_CLIEN_IDENT.CLIE_OID_CLIE= MC.OID_CLIE
AND CON.FEC_FACT IS NOT NULL
AND CON.NUM_UNID_ATEN_TOTA > 0
AND PTS.COD_TIPO_SOLI IN (SELECT COD_TIPO_SOLI_EQUI
                            FROM IMP_EQUIV_TIPO_SOLIC
                           WHERE COD_TIPO_SOLI = p_tipoSolicitud)
AND PED_SOLIC_CABEC_SECUE.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
ORDER BY CON.VAL_NUME_SOLI;

consultora  c_consultoras%ROWTYPE;

BEGIN

    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_DETAL_FACTU_DETAL';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_DETAL_FACTU_CABEC';

    pago_posterior:=0;

    lnIdPais   := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigoPais);
    lnIdMarca  := gen_pkg_gener.gen_fn_devuelve_id_marca(p_codigoMarca);
    lnIdCanal  := gen_pkg_gener.gen_fn_devuelve_id_canal(p_codigoCanal);
    l_oidPeriodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(p_codigoPeriodo, lnIdMarca, lnIdCanal);

    -- Obtenemos el valor del periodo siguiente
    lsCodigoPeriodo:= per_pkg_repor_perce.per_fn_obtie_perio(p_codigoPeriodo,
                                                             lnIdPais,
                                                             lnIdMarca,
                                                             lnIdCanal,
                                                             1);
    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  cons.ind_inte_lari_gene = l_indicadorEnvioLarissa
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = lnIdPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := null;
        END;

    END IF;

    OPEN c_consultoras(l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
        FETCH c_consultoras INTO consultora;
        EXIT WHEN c_consultoras%NOTFOUND;

        id_solicitud:= consultora.Oid_Soli_Cabe;

        --insertamos los detalle de las facturas ya procesadas
        INSERT INTO IMP_DETAL_FACTU_DETAL (
        soca_oid_soli_cabe,
        soca_oid_soli_posi,
        val_codi_vent,
        des_prod,
        num_unid_dema,
        num_unid_aten,
        val_prec_cata_unit,
        val_prec_cata_tota,
        val_porc_desc,
        val_impo_desc_tota,
        val_impo_con_desc,
        val_impo_pago_post,
        des_obse,
        ind_agru,
        val_prec_grat)
        SELECT SOCA_OID_SOLI_CABE AS ID_SOLI_CABE,
        OID_SOLI_POSI AS ID_SOLIC_POSI,
        COD_VENTA AS COD_PRODUCTO,
        PRODUCTO AS NOMBRE_PRODUCTO,
        SOLIC AS NUMERO_SOLICITUDES,
        ATND AS NUMERO_SOLIC_ATENDIDAS,
        PRECIO_UNIT_CAT,
        TOTAL_CATALOGO,
        PORC_DTO,
        DESCUENTO,
        TOT_CON_DTO,
        CASE
           WHEN FORMA_PAGO IS NULL
              THEN pago_posterior
           ELSE IMP_FN_CALCU_PAGO_POSTE (OID_SOLI_POSI, lsCodigoPeriodo)
        END AS PAGO_POSTERIOR,
        OBSERVACIONES,
        AGRUPACION,
        PREC_GRATIS
        FROM (
            SELECT PED.SOCA_OID_SOLI_CABE,
                   PED_SOLIC_POSIC.OID_SOLI_POSI,
                    CASE
                       WHEN PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0
                          THEN 2 -- 'MAV'
                       WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL)
                          THEN 2 -- Premios (los agrupamos con los MAV)
                       WHEN PED_SOLIC_POSIC.VAL_PORC_DESC IS NULL OR (PED_SOLIC_POSIC.VAL_PORC_DESC IS NOT NULL AND PED_SOLIC_POSIC.VAL_PORC_DESC = 0)
                          THEN 0 -- 'PRODUCTOS SIN DESCUENTO'
                       WHEN PED_SOLIC_POSIC.VAL_PORC_DESC IS NOT NULL AND PED_SOLIC_POSIC.VAL_PORC_DESC > 0
                          THEN 1 -- 'PRODUCTOS CON DESCUENTO'
                    END AS AGRUPACION,
                    CASE
                       WHEN PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0
                          THEN 0
                       ELSE 8
                    END IND_GRATIS,
                    NVL(PED_SOLIC_POSIC.VAL_CODI_VENT, LPAD('0', 4 - LENGTH(PED_SOLIC_POSIC.VAL_CODI_VENT_FICT), '0') || PED_SOLIC_POSIC.VAL_CODI_VENT_FICT) AS COD_VENTA,
                    (SELECT VAL_I18N FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU' AND IDIO_OID_IDIO = 1 AND VAL_OID = PED_SOLIC_POSIC.PROD_OID_PROD) PRODUCTO,
                    PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL AS SOLIC,
                    PED_SOLIC_POSIC.NUM_UNID_ATEN AS ATND,
                    CASE
                       WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL)
                          THEN 0
                       WHEN PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0
                          THEN PED_SOLIC_POSIC.VAL_PREC_CONT_UNIT_LOCA
                       ELSE PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA
                    END AS PRECIO_UNIT_CAT,
                    CASE
                       WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL)
                          THEN 0
                       WHEN PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0
                          THEN PED_SOLIC_POSIC.VAL_PREC_CONT_UNIT_LOCA * PED_SOLIC_POSIC.NUM_UNID_ATEN
                       ELSE PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA * PED_SOLIC_POSIC.NUM_UNID_ATEN
                    END AS TOTAL_CATALOGO,
                    CASE
                       WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL OR PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0)
                          THEN 100
                       WHEN PED_SOLIC_POSIC.VAL_PORC_DESC IS NULL
                          THEN 0
                       ELSE PED_SOLIC_POSIC.VAL_PORC_DESC
                    END AS PORC_DTO,
                    CASE
                       WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL OR PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0)
                          THEN CASE
                                  WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL)
                                     THEN 0
                                  WHEN PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0
                                     THEN PED_SOLIC_POSIC.VAL_PREC_CONT_UNIT_LOCA * PED_SOLIC_POSIC.NUM_UNID_ATEN
                                  ELSE PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA * PED_SOLIC_POSIC.NUM_UNID_ATEN
                               END
                       ELSE PED_SOLIC_POSIC.VAL_IMPO_DESC_TOTA_LOCA
                    END AS DESCUENTO,
                    CASE
                       WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL OR PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0)
                          THEN 0
                       ELSE PED_SOLIC_POSIC.VAL_PREC_FACT_TOTA_LOCA
                    END AS TOT_CON_DTO,
                    CASE
                       WHEN NVL(PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL, 0) - NVL(PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) > 0
                          THEN 'Agotado'
                       WHEN NVL(PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) = 0
                            AND PED_SOLIC_POSIC.IND_LIMI_VENT = 1 AND (PTO.COD_TIPO_OFER = '21' OR PTO.COD_TIPO_OFER = '23')
                           THEN 'Falt.Liq.'
                       WHEN NVL(PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) = 0
                            AND PED_SOLIC_POSIC.IND_LIMI_VENT = 1
                          THEN 'Falt.Anunc.'
                       WHEN NVL(PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) = 0
                            AND PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI = 2 AND PED_SOLIC_POSIC.STPO_OID_SUBT_POSI = 21
                          THEN 'Anul. Mto Max'
                       WHEN NVL(PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) = 0
                            AND PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI = 2
                          THEN 'Vta Exclusiva'
                       WHEN NVL(PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL, 0) = 0 AND NVL(PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) = 0
                          THEN 'No Aplica'
                       WHEN (PED.COPA_OID_PARA_GENE IS NOT NULL OR PED.ICTP_OID_TIPO_PROG IS NOT NULL)
                          THEN 'Premio'
                       WHEN CLASE.COD_CLAS_SOLI = 'R1'
                          THEN 'Aten. REC'
                       WHEN PED_TIPO_POSIC.COD_TIPO_POSI IS NOT NULL AND PED_TIPO_POSIC.COD_TIPO_POSI = 'RE'
                          THEN 'Recuperacion'
                       WHEN PED_TIPO_POSIC.COD_TIPO_POSI IS NOT NULL AND PED_TIPO_POSIC.COD_TIPO_POSI = 'DA'
                          THEN 'Alternativo'
                       WHEN EXISTS (
                             SELECT NULL
                             FROM PRE_MATRI_FACTU PMF,
                                  PRE_MATRI_REEMP PMR
                             WHERE PMF.OID_MATR_FACT = PMR.MAFA_OID_COD_REEM
                             AND PMF.OFDE_OID_DETA_OFER = PED_SOLIC_POSIC.OFDE_OID_DETA_OFER
                             )
                          THEN 'Reemplazo'
                       WHEN PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA = 0
                          THEN 'Gratis'
                       ELSE ''
                    END AS OBSERVACIONES,
                    PED_SOLIC_POSIC.FOPA_OID_FORM_PAGO AS FORMA_PAGO,
                    CASE
                       WHEN PED.ICTP_OID_TIPO_PROG IS NOT NULL
                          THEN 0
                       ELSE PED_SOLIC_POSIC.VAL_PREC_CONT_TOTA_LOCA
                    END AS PREC_GRATIS,
                    CASE
                       WHEN PED.ICTP_OID_TIPO_PROG IS NOT NULL
                          THEN PED_SOLIC_POSIC.VAL_PREC_CONT_TOTA_LOCA
                       ELSE 0
                    END AS PREC_PREMIO,
                    PED.COPA_OID_PARA_GENE
               FROM PED_SOLIC_CABEC PED,
                    PED_SOLIC_CABEC CONS,
                    PED_SOLIC_POSIC,
                    PRE_OFERT_DETAL POD,
                    PRE_TIPO_OFERT PTO,
                    PED_TIPO_POSIC,
                    PED_TIPO_SOLIC_PAIS PTSP,
                    PED_TIPO_SOLIC PTS,
                    PED_CLASE_SOLIC CLASE
               WHERE PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE = PED.OID_SOLI_CABE
                 AND PED.TSPA_OID_TIPO_SOLI_PAIS = PTSP.OID_TIPO_SOLI_PAIS
                 AND PTSP.TSOL_OID_TIPO_SOLI = PTS.OID_TIPO_SOLI
                 AND PED.SOCA_OID_SOLI_CABE = CONS.OID_SOLI_CABE
                 AND PED.SOCA_OID_SOLI_CABE = id_solicitud
                 AND PED_SOLIC_POSIC.TPOS_OID_TIPO_POSI = PED_TIPO_POSIC.OID_TIPO_POSI (+)
                 AND PED_SOLIC_POSIC.OFDE_OID_DETA_OFER = POD.OID_DETA_OFER (+)
                 AND POD.TOFE_OID_TIPO_OFER = PTO.OID_TIPO_OFER (+)
                 AND PTS.CLSO_OID_CLAS_SOLI = CLASE.OID_CLAS_SOLI
                 AND PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI <> 2 -- Filtramos los anulados
               ORDER BY AGRUPACION DESC, PED_SOLIC_POSIC.VAL_CODI_VENT
        );

        COMMIT;
        SUB_TOT_SOLIC:=0;
        SUB_TOT_ATND:=0;
        SUB_TOT_TOT_CATALOGO:=0;
        SUB_TOT_DTO:=0;
        SUB_TOT_TOT_CON_DTO:=0;
        saldo_anterior:=0;
        SUB_TOT_PAGO_POST:=0;
        TOT_GRATIS:=0;

        --extraemos totalizados del detalle
        SELECT SUM(IMP_DETAL_FACTU_DETAL.NUM_UNID_DEMA),
               SUM(IMP_DETAL_FACTU_DETAL.NUM_UNID_ATEN),
               SUM(IMP_DETAL_FACTU_DETAL.VAL_PREC_CATA_TOTA),
               SUM(IMP_DETAL_FACTU_DETAL.VAL_IMPO_DESC_TOTA),
               SUM(IMP_DETAL_FACTU_DETAL.VAL_IMPO_CON_DESC),
               SUM(IMP_DETAL_FACTU_DETAL.VAL_PREC_GRAT),
               SUM(IMP_DETAL_FACTU_DETAL.VAL_IMPO_PAGO_POST)
               INTO SUB_TOT_SOLIC,
                    SUB_TOT_ATND,
                    SUB_TOT_TOT_CATALOGO,
                    SUB_TOT_DTO, -- Total Dsctos
                    SUB_TOT_TOT_CON_DTO,
                    TOT_GRATIS,
                    TOT_PAGO_POSTE
        FROM IMP_DETAL_FACTU_DETAL
        WHERE IMP_DETAL_FACTU_DETAL.SOCA_OID_SOLI_CABE = id_solicitud;

        --extraemos territorio,zona,region para la cabecera
        cod_terri  := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(consultora.Oid_Clie, 'COD_TERR');
        cod_regi   := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(consultora.Oid_Clie, 'COD_REGI');
        desc_regi  := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(consultora.Oid_Clie, 'DES_REGI');
        cod_zona   := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(consultora.Oid_Clie, 'COD_ZONA');
        desc_zona  := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(consultora.Oid_Clie, 'DES_ZONA');
        cod_secc   := GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(consultora.Oid_Clie, 'COD_SECC');

        --calculamos el monto toatal para pagos_posteriores
        SUB_TOT_PAGO_POST:= IMP_FN_CALCU_TOTAL_PAGO_POSTE(id_solicitud,lsCodigoPeriodo,'P');

        -- calculamos saldo campa?a anterior
        fechaVencimiento := GEN_PKG_GENER.GEN_FN_RECUP_FECHA_ACTIV(consultora.cod_pais,
                                                                   p_codigoMarca,
                                                                   p_codigoCanal,
                                                                   cod_zona,
                                                                   lsCodigoPeriodo,
                                                                   'CV');

        -- Obtenemos el saldo a favor
        saldo_a_favor      := IMP_FN_CALCU_SALDO_FAVOR(id_solicitud);

        --saldo_anterior     := GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_VENCI(consultora.Oid_Clie,fechaVencimiento);
        saldo_anterior     := IMP_FN_CALCU_TOTAL_PAGO_POSTE(id_solicitud,lsCodigoPeriodo,'A');

        -- Restamos al saldo anterior el saldo a favor
        saldo_anterior     := saldo_anterior - saldo_a_favor;

        -- Incluimos el redondeo dentro del descuento total
        dsto_tota_Pie      := SUB_TOT_DTO - consultora.impo_redo_loca;

        totalConDto        := SUB_TOT_TOT_CATALOGO - dsto_tota_Pie;

        cargoServicios     := IMP_FN_TOTA_SERV(id_solicitud,lsCodigoPeriodo,'A');
        IF (cargoServicios IS NULL) THEN
             cargoServicios := 0;
        END IF;

        totalFactura       := totalConDto + consultora.flete + cargoServicios;
        totalFacturaAPagar := totalFactura + saldo_anterior - SUB_TOT_PAGO_POST;

        --insertamos cabecera
        INSERT INTO IMP_DETAL_FACTU_CABEC(
        VAL_NUME_SOLI,
        OID_SOLIC_CABE,
        FEC_FACT,
        DES_PAIS,
        COD_CLIE,
        VAL_NOM1,
        VAL_NOM2,
        VAL_APE1,
        VAL_APE2,
        COD_TERR,
        COD_PERI,
        COD_PAIS,
        NUM_DOCU_IDEN,
        COD_REGI,
        COD_ZONA,
        DES_REGI,
        DES_ZONA,
        VAL_PREC_CATA_TOTA,
        VAL_IMPO_DESC_TOTA,
        VAL_IMPO_CON_DESC,
        NUM_UNID_DEMA_TOTA,
        NUM_UNID_ATEN_TOTA,
        VAL_IMPO_FLET,
        VAL_IMPO_FACT_TOTA,
        VAL_IMPO_PAGO_POST,
        VAL_IMPO_TOTA_PAGA,
        VAL_SALD_PERI_ANTE,
        VAL_IMPO_DESC_TOTA_PIE,
        VAL_IMPO_CON_DESC_PIE,
        VAL_IMPO_SERV,
        VAL_IMPO_PAGO_POST_PIE)
        VALUES(
        consultora.num_solic,
        consultora.oid_soli_cabe,
        consultora.fecha_facturacion,
        consultora.desc_pais,
        consultora.cod_consultora,
        consultora.nombre_consultora1,
        consultora.nombre_consultora2,
        consultora.ape_consultora1,
        consultora.ape_consultora2,
        cod_zona||'-'||cod_secc||'-'||cod_terri||'-'||consultora.num_secu_fact_diar,
        consultora.cod_periodo,
        consultora.cod_pais,
        consultora.num_docu_iden,
        cod_regi,
        cod_zona,
        desc_regi,
        desc_zona,
        SUB_TOT_TOT_CATALOGO,
        dsto_tota_Pie, -- SUB_TOT_DTO, se reemplaza por el valor que incluye el redondeo
        totalConDto, -- SUB_TOT_TOT_CON_DTO, se reemplaza por el valor que incluye el redondeo
        SUB_TOT_SOLIC,
        SUB_TOT_ATND,
        consultora.flete,
        totalFactura,
        TOT_PAGO_POSTE,
        totalFacturaAPagar,
        saldo_anterior,
        dsto_tota_Pie,
        totalConDto,
        cargoServicios, -- Percepciones
        SUB_TOT_PAGO_POST);

        COMMIT;

    END LOOP;
    CLOSE c_consultoras;

    -- Actualizamos las estadisticas
    gv_user := USER;
    DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => gv_user, TABNAME => 'IMP_DETAL_FACTU_CABEC', CASCADE => TRUE );
    DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => gv_user, TABNAME => 'IMP_DETAL_FACTU_DETAL', CASCADE => TRUE );

END IMP_PR_PROCE_DETAL_FACTU;

/***********************************************************************/
FUNCTION IMP_FN_CALCU_TOTAL_PAGO_POSTE(psOidSoliCabecPedido IN NUMBER, lsCodigoPeriodoSiguiente IN VARCHAR2, lsTipo IN VARCHAR2) RETURN NUMBER
IS

total_pago NUMBER;

 CURSOR c_consultoras IS
   select PAGO, importe
   from (
      select PAGO, total_pedido, sum(imp_pend) as importe
      from (
        select FEC_VENC,
               case
                 when cuota_pedido = 'SI' and pago = 'PENDIENTE' then 0
                 when cuota_percep = 'SI' and pago = 'PENDIENTE' then 0
                 else IMP_PEND
               end IMP_PEND,
               cuota_pedido,
               cuota_percep,
               fec_vencto_real,
               PAGO,
               total_pedido,
               sum(IMP_PEND) over(Partition by PAGO) as sub_tot
         from (select FEC_VENC,
                      IMP_PEND,
                      cuota_pedido,
                      cuota_percep,
                      total_pedido,
                      fec_vencto_real,
                      case
                        when FEC_VENC > fec_vencto_real then 'POSTERIOR'
                        when FEC_VENC <= fec_vencto_real then 'PENDIENTE'
                      end PAGO
                from (select CCC_MOVIM_CUENT_CORRI.PERD_OID_PERI,
                             FEC_VENC,
                             ped.PERD_OID_PERI,
                             case
                               when CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE = OID_SOLI_CABE then 'SI'
                                else 'NO'
                             end cuota_pedido,
                             case
                                when exists
                                  (select OID_SOLI_CABE
                                         from ped_solic_cabec     percep,
                                              ped_tipo_solic_pais ptsp,
                                              ped_tipo_solic      pts
                                        where percep.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
                                          and ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
                                          and pts.COD_TIPO_SOLI = 'CP01'
                                          and percep.clie_oid_clie = ped.CLIE_OID_CLIE
                                          and percep.perd_oid_peri = ped.perd_oid_peri
                                          and percep.OID_SOLI_CABE = CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE) then 'SI'
                                  else 'NO'
                             end cuota_percep,
                             CCC_MOVIM_CUENT_CORRI.IMP_PEND,
                             fec_fact,
                             total_pedido,
                             nvl((select cc.FEC_INIC
                                  from cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                                  where cc.PERD_OID_PERI = cp.oid_peri
                                       and cp.peri_oid_peri = spc.oid_peri
                                       and spc.cod_peri = lsCodigoPeriodoSiguiente
                                       and cc.ZZON_OID_ZONA = ped.zzon_oid_zona
                                       and cc.CACT_OID_ACTI = act.OID_ACTI
                                       and act.COD_ACTI = 'CV'),fec_fact) as fec_vencto_real
                           from CCC_MOVIM_CUENT_CORRI,
                           (select fec_fact,
                                   CLIE_OID_CLIE,
                                   FEC_INIC,
                                   FEC_FINA,
                                   ZZON_OID_ZONA,
                                   perd_oid_peri,
                                   ped_solic_cabec.OID_SOLI_CABE,
                                   VAL_TOTA_PAGA_LOCA as total_pedido
                            from ped_solic_cabec, cra_perio
                            where ped_solic_cabec.OID_SOLI_CABE = psOidSoliCabecPedido
                                  and oid_peri = perd_oid_peri) ped
                      WHERE CCC_MOVIM_CUENT_CORRI.CLIE_OID_CLIE = ped.CLIE_OID_CLIE
                             AND CCC_MOVIM_CUENT_CORRI.IMP_PEND > 0
                             AND NOT EXISTS (
                                SELECT NULL
                                FROM
                                   ccc_proce a,
                                   ccc_subpr b
                                WHERE a.oid_proc = b.ccpr_oid_proc
                                AND a.cod_proc = 'CCC007'
                                AND b.cod_subp = 7
                                AND b.oid_subp = ccc_movim_cuent_corri.subp_oid_subp_crea)
                      order by FEC_VENC

                            )))
         group by PAGO, total_pedido);

   consultora_ob c_consultoras%ROWTYPE;

BEGIN
    total_pago:=0;
    OPEN c_consultoras;
    LOOP
      FETCH c_consultoras INTO consultora_ob;
         IF (lsTipo = 'P') THEN
           if (consultora_ob.pago = 'POSTERIOR') then
             total_pago:= ABS(consultora_ob.importe);
           end if;
         END IF;
         IF (lsTipo = 'A') THEN
           if (consultora_ob.pago = 'PENDIENTE') then
             total_pago:= ABS(consultora_ob.importe);
           end if;
         END IF;

      EXIT WHEN c_consultoras%NOTFOUND;

    END LOOP;

        RETURN total_pago;

    CLOSE c_consultoras;

END IMP_FN_CALCU_TOTAL_PAGO_POSTE;

/***********************************************************************/
FUNCTION IMP_FN_CALCU_PAGO_POSTE(psOidSoliPosi IN NUMBER, lsCodigoPeriodoSiguiente IN VARCHAR2) RETURN NUMBER
IS

total_pago_Posterior NUMBER;

  cursor c_pago_posterior is
    select
     COD_FORM_PAGO,
     PAGO_POSTERIOR,
     sum(cuota) as importe
    from
     (
      select
       COD_FORM_PAGO,
       NUM_POSI_DETA,
       VAL_PORC_PAGO,
       COD_INDI_DIAS,
       NUM_DIAS,
       cuota,
       FEC_INIC,
       FEC_FINA,
       COD_ACTI,
       fec_fact,
       fec_vencimiento,
       fec_vencto_real,
       PAGO_POSTERIOR,
       sum(cuota) over (Partition by COD_FORM_PAGO, PAGO_POSTERIOR) as sub_tot
      from
       (
        select
         COD_FORM_PAGO,
         NUM_POSI_DETA,
         VAL_PORC_PAGO,
         COD_INDI_DIAS,
         NUM_DIAS,
         cuota,
         FEC_INIC,
         FEC_FINA,
         COD_ACTI,
         fec_fact,
         fec_vencimiento,
         fec_vencto_real,
         case
          when fec_vencimiento > fec_vencto_real then 'SI'
          else 'NO'
         end PAGO_POSTERIOR
        from
         (
          select
           COD_FORM_PAGO,
           NUM_POSI_DETA,
           VAL_PORC_PAGO,
           COD_INDI_DIAS,
           NUM_DIAS,
           (VAL_PREC_FACT_TOTA_LOCA * VAL_PORC_PAGO )/100 as cuota,
           FEC_INIC,
           FEC_FINA,
           COD_ACTI,
           fec_fact,
           case
            when COD_INDI_DIAS = 'N' then fec_fact + NUM_DIAS
            when COD_INDI_DIAS = 'A' then nvl((select FEC_INIC from cra_crono cc where cc.PERD_OID_PERI = pos.perd_oid_peri and cc.ZZON_OID_ZONA = pos.ZZON_OID_ZONA and cc.CACT_OID_ACTI = BEL_FORMA_PAGO_DETAL.CACT_OID_ACTI),fec_fact)
            --when COD_INDI_DIAS = 'D' then FEC_FINA - FEC_INIC + 1
            when COD_INDI_DIAS = 'F' then nvl(FEC_FINA, fec_fact)
           end fec_vencimiento,
           nvl((select cc.FEC_INIC
                                  from cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act
                                  where cc.PERD_OID_PERI = cp.oid_peri
                                       and cp.peri_oid_peri = spc.oid_peri
                                       and spc.cod_peri = lsCodigoPeriodoSiguiente
                                       and cc.ZZON_OID_ZONA = pos.zzon_oid_zona
                                       and cc.CACT_OID_ACTI = act.OID_ACTI
                                       and act.COD_ACTI = 'CV'),fec_fact) as fec_vencto_real
          from
           BEL_FORMA_PAGO,
           BEL_FORMA_PAGO_DETAL,
           CRA_ACTIV,
           (
            select
             OID_SOLI_POSI,
             ped_solic_posic.FOPA_OID_FORM_PAGO,
             fec_fact,
             FEC_INIC,
             FEC_FINA,
             ZZON_OID_ZONA,
             perd_oid_peri,
             ped_solic_posic.VAL_PREC_FACT_TOTA_LOCA
            from
             ped_solic_cabec,
             ped_solic_posic,
             cra_perio
            where
             oid_soli_posi = psOidSoliPosi--parametro
             and PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE = ped_solic_cabec.OID_SOLI_CABE
             and oid_peri = perd_oid_peri
           ) pos
          where
           BEL_FORMA_PAGO.OID_FORM_PAGO = BEL_FORMA_PAGO_DETAL.FOPA_OID_FORM_PAGO
           and BEL_FORMA_PAGO_DETAL.CACT_OID_ACTI = CRA_ACTIV.OID_ACTI(+)
           and BEL_FORMA_PAGO.OID_FORM_PAGO = pos.FOPA_OID_FORM_PAGO
          order by
           COD_FORM_PAGO,
           NUM_POSI_DETA
         )
       )
     )
    group by
      COD_FORM_PAGO,
     PAGO_POSTERIOR;

    pago_posterior c_pago_posterior%ROWTYPE;

BEGIN

    total_pago_Posterior:=0;
    OPEN c_pago_posterior;
    LOOP
      FETCH c_pago_posterior INTO pago_posterior;
         IF (pago_posterior.pago_posterior = 'SI') THEN
           total_pago_Posterior:= ABS(pago_posterior.Importe);
         END IF;
      EXIT WHEN c_pago_posterior%NOTFOUND;

    END LOOP;

    RETURN total_pago_Posterior;

    CLOSE c_pago_posterior;

END IMP_FN_CALCU_PAGO_POSTE;

/***********************************************************************/
FUNCTION IMP_FN_TOTA_SERV(psOidSoliCabecPedido IN NUMBER, lsCodigoPeriodoSiguiente IN VARCHAR2, lsTipo IN VARCHAR2) RETURN NUMBER
IS

total_pago NUMBER;

 CURSOR c_consultoras IS
    select
     'PENDIENTE' as PAGO,
     sum(CCC_MOVIM_CUENT_CORRI.IMP_PEND) as importe
    from
     CCC_MOVIM_CUENT_CORRI,
     (
      select
       fec_fact,
       CLIE_OID_CLIE,
       FEC_INIC,
       FEC_FINA,
       ZZON_OID_ZONA,
       perd_oid_peri,
       ped_solic_cabec.OID_SOLI_CABE ,
       VAL_TOTA_PAGA_LOCA as total_pedido
      from
       ped_solic_cabec,
       cra_perio
      where
       ped_solic_cabec.OID_SOLI_CABE = psOidSoliCabecPedido
       and oid_peri = perd_oid_peri
     ) ped
    where
     CCC_MOVIM_CUENT_CORRI.CLIE_OID_CLIE = ped.CLIE_OID_CLIE
     and CCC_MOVIM_CUENT_CORRI.IMP_PEND > 0
     and CCC_MOVIM_CUENT_CORRI.SOCA_OID_SOLI_CABE in (
      select
       OID_SOLI_CABE
      from
       ped_solic_cabec percep,
       ped_tipo_solic_pais ptsp,
       ped_tipo_solic pts
      where
       percep.TSPA_OID_TIPO_SOLI_PAIS = ptsp.OID_TIPO_SOLI_PAIS
       and ptsp.TSOL_OID_TIPO_SOLI = pts.OID_TIPO_SOLI
       and pts.COD_TIPO_SOLI = 'CP01'
       and percep.clie_oid_clie = ped.CLIE_OID_CLIE
       and percep.perd_oid_peri = ped.perd_oid_peri
     )
    order by
     FEC_VENC;

   consultora_ob c_consultoras%ROWTYPE;

BEGIN
    total_pago:=0;
    OPEN c_consultoras;
    LOOP
      FETCH c_consultoras INTO consultora_ob;
         IF (lsTipo = 'P') THEN
           if (consultora_ob.pago = 'POSTERIOR') then
             total_pago:= ABS(consultora_ob.importe);
           end if;
         END IF;
         IF (lsTipo = 'A') THEN
           if (consultora_ob.pago = 'PENDIENTE') then
             total_pago:= ABS(consultora_ob.importe);
           end if;
         END IF;

      EXIT WHEN c_consultoras%NOTFOUND;

    END LOOP;

    RETURN total_pago;

    CLOSE c_consultoras;

END IMP_FN_TOTA_SERV;


/** Este proceso sacara cada parte de xml y lo coclocara como
    concatenacion en un solo campo **/
PROCEDURE IMP_PR_GENER_DETAL_FACTU IS

CURSOR c_consultorasPedidos IS
    SELECT P.COD_CONS,
           P.VAL_NUME_SOLI,
           P.XML_DETA_FACT,
           P.ORD_XML
    FROM  IMP_TMP_DETAL_FACTU P
    ORDER BY P.COD_CONS, P.VAL_NUME_SOLI, P.ORD_XML;

consult             c_consultorasPedidos%ROWTYPE;
cod_cons_ante       VARCHAR2(20) := ' ';
val_nume_soli_ante  NUMBER(10) := 0;
l_correlativo       NUMBER := 1;
l_CLOBDest          CLOB;

BEGIN
    OPEN c_consultorasPedidos;
    LOOP
        FETCH c_consultorasPedidos INTO consult;
        EXIT WHEN c_consultorasPedidos%NOTFOUND;

        IF (consult.COD_CONS <> cod_cons_ante OR consult.VAL_NUME_SOLI <> val_nume_soli_ante) THEN
            -- Insertamos un nuevo registro y la primera parte del XML
            INSERT INTO IMP_PAQUE_DOCUM_DETAL_FACTU (
                   COR_PADO_DEFA,
                   COD_CONS,
                   VAL_NUME_SOLI,
                   XML_DETA_FACT)
            VALUES (
                   l_correlativo,
                   consult.COD_CONS,
                   consult.VAL_NUME_SOLI,
                   EMPTY_CLOB())
            RETURNING XML_DETA_FACT INTO l_CLOBDest;

            DBMS_LOB.writeappend(l_CLOBDest, LENGTH(consult.XML_DETA_FACT), consult.XML_DETA_FACT);
            l_correlativo := l_correlativo + 1;
        ELSE
            -- Agregamos la siguiente parte del XML
            DBMS_LOB.writeappend(l_CLOBDest, LENGTH(consult.XML_DETA_FACT), consult.XML_DETA_FACT);
        END IF;

        cod_cons_ante := consult.COD_CONS;
        val_nume_soli_ante := consult.VAL_NUME_SOLI;
    END LOOP;

    CLOSE c_consultorasPedidos;

END IMP_PR_GENER_DETAL_FACTU;



/***************************************************************************
Descripcion       : Procedimiemto Generar Mensaje de Puntaje Obtenido XML

Fecha Creacion    : 07/03/2008 12:56:20 a.m.
Autor             : Leonardo Lizana
***************************************************************************/
PROCEDURE IMP_PR_GENER_MENSA_PUNTA_OBTEN(psCodClie    VARCHAR2,
                                         psXML VARCHAR2
)IS

lb_xmlCLOB              CLOB;

BEGIN
          --EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_PUNTA_OBTEN';
          INSERT INTO IMP_PAQUE_DOCUM_PUNTA_OBTEN
                      (COD_CLIE,
                       XML_MENS_PUNT)
          VALUES      (psCodClie,
                       EMPTY_CLOB())
          RETURNING XML_MENS_PUNT INTO lb_xmlCLOB;
          DBMS_LOB.writeappend(lb_xmlCLOB, LENGTH(psXML), psXML);

   COMMIT;
END IMP_PR_GENER_MENSA_PUNTA_OBTEN;

/***************************************************************************
Descripcion       : Funcion que retorna el parametro de impresion de un
                    determinado proceso de impresion en base al nombre del
     parametro, retorna NULL en caso no encuentre el valor.

Fecha Creacion    : 07/03/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_OBTIE_PARAM_IMPRE(psCodigoProceso VARCHAR2,
                                  psNombreParametro VARCHAR2)
RETURN VARCHAR2 IS
lsValorParametro VARCHAR2(100);
BEGIN
    SELECT A.VAL_PARA_PRIM
    INTO lsValorParametro
    FROM IMP_PARAM_PROCE_IMPRE A
    WHERE A.PRIM_COD_PROC = psCodigoProceso
    AND A.NOM_PARA_PRIM = psNombreParametro
    AND A.EST_PARA_PRIM = '1';

    RETURN lsValorParametro;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
    RETURN NULL;
END;

/***************************************************************************
Descripcion       : Funcion que retorna el numero de documento legal en base
                    al numero de solicitud de referencia para una nota de credito.

Fecha Creacion    : 28/03/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_NUMER_DOCUM_LEGAL (p_numeroSolicitudRef IN NUMBER,
                                   p_oidTipoDocuFact    IN NUMBER)
RETURN NUMBER
IS

l_numeroDocumentoLegal NUMBER;

BEGIN

SELECT DISTINCT NUM_DOCU_LEGA_REFE
INTO  l_numeroDocumentoLegal
FROM (
SELECT   FAC_DOCUM_CONTA_CABEC.OID_CABE,
         FAC_TIPO_DOCUM.DES_TIPO_DOCU,
         FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE,
         FAC_DOCUM_CONTA_CABEC.NUM_DOCU_LEGA,
         CONS.VAL_NUME_SOLI AS BOLETA_DESPACHO,
         (SELECT COD_PERI
            FROM PED_SOLIC_CABEC REFE,
                 CRA_PERIO,
                 SEG_PERIO_CORPO
           WHERE REFE.OID_SOLI_CABE = CONS.SOCA_OID_DOCU_REFE
             AND REFE.PERD_OID_PERI = CRA_PERIO.OID_PERI
             AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI) AS CMP_REF,
         (SELECT REFE.VAL_NUME_SOLI
            FROM PED_SOLIC_CABEC REFE
           WHERE REFE.OID_SOLI_CABE = CONS.SOCA_OID_DOCU_REFE) AS BOL_REF,
         (SELECT MIN (NUM_DOCU_CONT_INTE)
            FROM FAC_DOCUM_CONTA_CABEC DR
           WHERE DR.SOCA_OID_SOLI_CABE = CONS.SOCA_OID_DOCU_REFE AND DR.TIDO_OID_TIPO_DOCU = 1) AS NUM_DOCU_INTE_REFE,
         (SELECT MIN (NUM_DOCU_LEGA)
            FROM FAC_DOCUM_CONTA_CABEC DR
           WHERE DR.SOCA_OID_SOLI_CABE = CONS.SOCA_OID_DOCU_REFE AND DR.TIDO_OID_TIPO_DOCU = 1) AS NUM_DOCU_LEGA_REFE
    FROM PED_SOLIC_CABEC CONS,
         FAC_DOCUM_CONTA_CABEC,
         FAC_TIPO_DOCUM
   WHERE FAC_TIPO_DOCUM.OID_TIPO_DOCU = FAC_DOCUM_CONTA_CABEC.TIDO_OID_TIPO_DOCU
     AND FAC_DOCUM_CONTA_CABEC.SOCA_OID_SOLI_CABE = CONS.OID_SOLI_CABE
     AND CONS.FEC_FACT IS NOT NULL
     AND CONS.VAL_NUME_SOLI = p_numeroSolicitudRef
     AND FAC_TIPO_DOCUM.OID_TIPO_DOCU = p_oidTipoDocuFact
  AND ROWNUM = 1
);

RETURN l_numeroDocumentoLegal;

EXCEPTION
    WHEN NO_DATA_FOUND THEN RETURN NULL;

END IMP_FN_NUMER_DOCUM_LEGAL;

/***************************************************************************
Descripcion       : Procedimiemto Generar Mensaje de Puntaje Obtenido XML

Fecha Creacion    : 13/03/2008 05:56:20 p.m.
Autor             : Leonardo Lizana
***************************************************************************/
PROCEDURE IMP_PR_GENER_XML_BOLET_RECOJ  IS


CURSOR c_BoletaRecojo IS


     SELECT COD_CONS
        , VAL_CORR
        , XML_BOLE_RECO
        , COD_CABE_BORE
     FROM INT_BOLET_RECOJ_XML
 ORDER BY COD_CONS,COD_CABE_BORE, VAL_CORR;

boletaRecojo  c_BoletaRecojo%ROWTYPE;
aux_codCons varchar2(15):='-';
cod_cons varchar2(15);
aux_codCabe  varchar2(15):='-';
cod_cabe varchar2(15);
lv_xmlCLOB CLOB;

    lscodpais      bas_Ctrl_Fact.Cod_Pais%TYPE;
    lsFecProc      bas_Ctrl_Fact.Fec_Proc%TYPE;
    lsCodPeri      bas_Ctrl_Fact.Cod_Peri%TYPE;
    lsTipoSol      ped_solic_cabec.tspa_oid_tipo_soli_pais%TYPE;
    lsTipoSolExp   ped_solic_cabec.tspa_oid_tipo_soli_pais%TYPE;
    lsValSol       ped_solic_cabec.val_nume_soli%TYPE;
    lsValSolExp    ped_solic_cabec.val_nume_soli%TYPE;
    lsIndicador    INT_REC_CABEC_BOREC.IND_ENVI_CON_PED1%TYPE;

    lsactuacronobr    VARCHAR2(1);

BEGIN

    --- Fecha que se esta facturando
    select fec_proc, bcf.cod_peri, bcf.cod_pais
      into lsFecProc, lsCodPeri, lscodpais
    from bas_Ctrl_Fact bcf
    where BCF.STA_CAMP = '0' and BCF.IND_CAMP_ACT = '1';

    --- Tipo de Solicitud de consolidado
    select tsp.oid_tipo_soli_pais into lsTipoSol
    from ped_tipo_solic_pais tsp, ped_tipo_solic ts
    where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
    and TS.COD_TIPO_SOLI = 'C1';

    --- Tipo de Solicitud de consolidado
    select tsp.oid_tipo_soli_pais into lsTipoSolExp
    from ped_tipo_solic_pais tsp, ped_tipo_solic ts
    where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
    and TS.COD_TIPO_SOLI = 'C8';

    lsactuacronobr := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_ACTUA_CRONO_BR');

    --Se inicializa la variabla CLOB en Memoria
    DBMS_LOB.CREATETEMPORARY(lv_xmlCLOB, TRUE);

   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_DOCUM_BOREC');
   OPEN c_BoletaRecojo;
   LOOP
     FETCH c_BoletaRecojo INTO boletaRecojo;
       cod_cons:= boletaRecojo.COD_CONS ;
       cod_cabe:= boletaRecojo.COD_CABE_BORE;
       EXIT WHEN c_BoletaRecojo%NOTFOUND;
       IF (cod_cabe <> aux_codCabe) THEN

       IF(cod_cons <> aux_codCons and aux_codCons<>'-') THEN
         INSERT INTO IMP_PAQUE_DOCUM_BOREC
                     (COD_CONS
                     , XML_CONS)
         VALUES
               (aux_codCons
               ,lv_xmlCLOB);

         --Se libera la variabla CLOB de Memoria
         DBMS_LOB.FREETEMPORARY(lv_xmlCLOB);

         --Se inicializa la variabla CLOB en Memoria
         DBMS_LOB.CREATETEMPORARY(lv_xmlCLOB, TRUE);

         /*INSERT INTO IMP_PAQUE_DOCUM_BOREC
                     (COD_CONS
                     , XML_CONS)
         VALUES
               (cod_cons
               ,EMPTY_CLOB())
         RETURNING XML_CONS INTO lv_xmlCLOB;*/

       END IF;

      ---- Obtiene como fue enviada la BR
      select decode((select count(*) from ped_solic_Cabec psc
                    where PSC.CLIE_OID_CLIE = B.CLIE_OID_CLIE
                    and PSC.TSPA_OID_TIPO_SOLI_PAIS = lsTipoSol
                    and PSC.FEC_FACT = lsFecProc
          ),0,'0','1') indicador into lsIndicador
      from  INT_REC_CABEC_BOREC b
      where B.COD_CABE_BORE = cod_cabe ;

      --- Numero de pedido del consolidado del pedido
      select max(psc.val_nume_soli) into lsValSol
      from ped_solic_Cabec psc,  INT_REC_CABEC_BOREC b
      where PSC.CLIE_OID_CLIE = B.CLIE_OID_CLIE
      and PSC.TSPA_OID_TIPO_SOLI_PAIS = lsTipoSol
      and PSC.FEC_FACT = lsFecProc
      and B.COD_CABE_BORE = cod_cabe;

      --- Numero de pedido del consolidado del express
      select max(psc.val_nume_soli) into lsValSolExp
      from ped_solic_Cabec psc,  INT_REC_CABEC_BOREC b
      where PSC.CLIE_OID_CLIE = B.CLIE_OID_CLIE
      and PSC.TSPA_OID_TIPO_SOLI_PAIS = lsTipoSolExp
      and PSC.FEC_FACT = lsFecProc
      and B.COD_CABE_BORE = cod_cabe;

      --- Toma el pedido con que se envio la boleta
      if lsValSol is null then
         lsValSol := lsValSolExp;
      end if;

        UPDATE INT_REC_CABEC_BOREC A
         SET A.IND_ENVI_XERO = DECODE(A.IND_ENVI_XERO,0,1,NULL,1,A.IND_ENVI_XERO),
             A.Ind_Envi_Xer2 = DECODE(A.IND_ENVI_XERO,1,1,A.Ind_Envi_Xer2),
             a.ind_envi_con_ped1 = decode(a.num_reco,1,lsIndicador,null),
             a.ind_envi_con_ped2 = decode(a.num_reco,2,lsIndicador,null),
             a.VAL_NUME_SOLI_PED1 = decode(a.num_reco,1,lsValSol,null),
             a.VAL_NUME_SOLI_PED2 = decode(a.num_reco,2,lsValSol,null),
             a.COD_PERI_DESP1 = decode(a.num_reco,1,lsCodPeri,null),
             a.COD_PERI_DESP2 = decode(a.num_reco,2,lsCodPeri,null)
         WHERE A.COD_CABE_BORE =boletaRecojo.COD_CABE_BORE;

         UPDATE INT_REC_LINEA_BOREC B
         SET B.IND_ENVI_XERO = DECODE(B.IND_ENVI_XERO,0,1,NULL,1,B.IND_ENVI_XERO),
             B.Ind_Envi_Xer2 = DECODE(B.IND_ENVI_XERO,1,1,B.Ind_Envi_Xer2)
         WHERE B.COD_CABE_BORE = boletaRecojo.COD_CABE_BORE;

       END IF;

       DBMS_LOB.writeappend(lv_xmlCLOB, LENGTH(boletaRecojo.XML_BOLE_RECO), boletaRecojo.XML_BOLE_RECO);

       aux_codCons:= cod_cons;
       aux_codCabe:= cod_cabe;

   END LOOP;
   CLOSE c_BoletaRecojo;

   IF(aux_codCons<>'-') THEN
     INSERT INTO IMP_PAQUE_DOCUM_BOREC
                 (COD_CONS
                 , XML_CONS)
           VALUES
                 (aux_codCons
                 ,lv_xmlCLOB);
   END IF;

   --Se libera la variabla CLOB de Memoria
   DBMS_LOB.FREETEMPORARY(lv_xmlCLOB);

   --Se actualiza las boletas de recojo generadas con motivo 7 Chile

   if lsactuacronobr = '4' then

      update int_Rec_cabec_borec
      set num_reco = 2
      where cod_cabe_bore in(
      select cod_cabe_bore from  int_Rec_cabec_borec c
      where c.esbo_oid_esta_bor1 = 3 and c.esbo_oid_esta_bor2 = 6
        and c.num_reco = 1 and c.more_oid_motn_reco_bore = 7
      );
   end if;

END IMP_PR_GENER_XML_BOLET_RECOJ;


/***************************************************************************
Descripcion       : Procedimiemto Calcular Documentos Laser

Fecha Creacion    : 01/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_FACTU(psCodPais VARCHAR2,
                                         psCodMarca VARCHAR2,
                                         psCodCanal VARCHAR2,
                                         psCodPeriodo VARCHAR2,
                                         psFechaFacturacion VARCHAR2) IS

CURSOR c_cabecera IS
    SELECT *
    FROM IMP_PAQUE_GENER_DOCUM_LASER;

    TYPE ARRAY_TYPE IS TABLE OF IMP_PAQUE_GENER_DOCUM_LASER%ROWTYPE;
    l_data ARRAY_TYPE;

    lnIdPeriodo         PED_SOLIC_CABEC.Perd_Oid_Peri%TYPE;
    l_indicadorImpu     PED_TASA_IMPUE.VAL_INDI_IMPU%TYPE;
    l_tasaImpu          PED_TASA_IMPUE.VAL_TASA_IMPU%TYPE;
    lnIdMarca           NUMBER;
    lnIdCanal           NUMBER;


    l_sumaNumUnidAten              NUMBER(12,2);
    l_sumaPrecContTotaLoca         NUMBER(12,2);
    l_sumaPrecSinImpuTotaFact      NUMBER(12,2);
    l_sumaPrecNetoTotaFact         NUMBER(12,2);

    l_decuentos                    NUMBER(12,2);
    l_baseImponible                NUMBER(12,2);
    l_impVenta                     NUMBER(12,2);
    l_total                        VARCHAR2(250);
    l_indicadorGenerarFacturaLaser VARCHAR(50);
    l_indicadorActuDocuLega        VARCHAR(50);
    l_codigoTipoDocumentoFactura   VARCHAR2(3);
    l_textoMoneda                  VARCHAR2(100);

BEGIN

   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_GENER_DOCUM_LASER');
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_TMP_GENER_DOCUM_LASER');

   l_indicadorGenerarFacturaLaser := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarFacturaLaser');
   l_indicadorActuDocuLega := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorActualizarDocumentoLegal');

   IF l_indicadorGenerarFacturaLaser IS NOT NULL AND l_indicadorGenerarFacturaLaser = 'S' THEN

     lnIdMarca       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
     lnIdCanal       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
     lnIdPeriodo     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,
                                                                    lnIdMarca,
                                                                    lnIdCanal);

     l_tasaImpu      := IMP_FN_OBTIE_TASA_IMPUE(l_indicadorImpu);
     l_codigoTipoDocumentoFactura := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'codigoTipoDocumentoFactura');
     l_textoMoneda := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'textoMoneda');

     -- Dependiendo del parametro, actualizamos el numero de documento legal en las facturas
     IF l_indicadorActuDocuLega IS NOT NULL AND l_indicadorActuDocuLega = 'S' THEN
         IMP_PKG_PROCE_COMPA.IMP_PR_ACTUA_NUMER_DOCUM_LEGAL(lnIdPeriodo, psFechaFacturacion, l_codigoTipoDocumentoFactura);
     END IF;

     INSERT INTO IMP_PAQUE_GENER_DOCUM_LASER
     SELECT CON.VAL_NUME_SOLI,
            DOC_CONT.OID_CABE,
            NVL(DOC_CONT.NUM_DOCU_LEGA, DOC_CONT.NUM_DOCU_CONT_INTE) NUM_DOCU_LEGA,
            BAS_PAIS.COD_PAIS,
            BAS_PAIS.DES_PAIS,
            CON.FEC_FACT FEC_EMIS,
            DOC_CONT.NUM_DOCU_CONT_INTE,
            MC.OID_CLIE,
            MC.COD_CLIE,
            psCodPeriodo COD_PERI,
            ZON_REGIO.COD_REGI,
            ZON_ZONA.COD_ZONA,
            ZON_TERRI.COD_TERR,
            DOC_CONT.VAL_APE1 || ' ' || DOC_CONT.VAL_APE2 || ', ' ||
            DOC_CONT.VAL_NOM1 || ' ' || DOC_CONT.VAL_NOM2 NOM_COMP,
            DOC_CONT.VAL_DIRE_COMP,
            DOC_CONT.VAL_NUME_IDEN_FISC,
            DOC_CONT.IMP_DESC_TOTA_LOCA,
            DOC_CONT.IMP_IMPU_TOTA_LOCA,
            DOC_CONT.IMP_FLET_TOTA_LOCA,
            DOC_CONT.VAL_TOTA_PAGA_LOCA,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL

       FROM FAC_DOCUM_CONTA_CABEC DOC_CONT,
            FAC_TIPO_DOCUM,
            PED_SOLIC_CABEC CON,
            MAE_CLIEN MC,
            BAS_PAIS,
            SEG_PAIS,
            ZON_TERRI,
            ZON_ZONA,
            ZON_REGIO
      WHERE DOC_CONT.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
        AND DOC_CONT.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU
        AND CON.CLIE_OID_CLIE = MC.OID_CLIE
        AND FAC_TIPO_DOCUM.COD_TIPO_DOCU = l_codigoTipoDocumentoFactura
        AND DOC_CONT.TERR_OID_TERR = ZON_TERRI.OID_TERR
        AND DOC_CONT.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
        AND DOC_CONT.ZORG_OID_REGI = ZON_REGIO.OID_REGI
        AND BAS_PAIS.COD_PAIS = SEG_PAIS.COD_PAIS
        AND SEG_PAIS.OID_PAIS = DOC_CONT.PAIS_OID_PAIS
        AND TO_CHAR(CON.FEC_FACT, 'DD/MM/YYYY') = psFechaFacturacion
        AND CON.PERD_OID_PERI = lnIdPeriodo
        AND SEG_PAIS.COD_PAIS = psCodPais;

      OPEN c_Cabecera;
          LOOP
          FETCH c_Cabecera BULK COLLECT INTO l_data LIMIT w_Filas;
          IF l_data.COUNT > 0 THEN
             FOR i IN l_data.FIRST .. l_data.LAST LOOP
                 SELECT SUM(X.NUM_UNID_ATEN),
                        SUM(X.VAL_PREC_CONT_TOTA_LOCA),
                        SUM(DECODE(X.VAL_PREC_CATA_TOTA_LOCA,
                                   0,
                                   X.VAL_PREC_CONT_TOTA_LOCA,
                                   ROUND(X.VAL_PREC_CATA_TOTA_LOCA /
                                         (1 + l_tasaImpu / 100),
                                         2))) VAL_PREC_SIN_IMPU_TOTA_FACT,
                        SUM(DECODE(X.VAL_PREC_CATA_UNIT_LOCA,
                                   0,
                                   X.VAL_PREC_CONT_TOTA_LOCA,
                                   X.VAL_PREC_NETO_TOTA_LOCA)) VAL_PREC_NETO_TOTA_FACT
                   INTO l_sumaNumUnidAten,
                        l_sumaPrecContTotaLoca,
                        l_sumaPrecSinImpuTotaFact,
                        l_sumaPrecNetoTotaFact

                   FROM FAC_DOCUM_CONTA_LINEA X,
                        (SELECT VAL_I18N, VAL_OID
                           FROM GEN_I18N_SICC
                          WHERE ATTR_ENTI = 'MAE_PRODU') Y,
                          PED_SOLIC_POSIC Z,
                          PRE_OFERT_DETAL P
                  WHERE X.PROD_OID_PROD = Y.VAL_OID
                    AND X.DCCA_OID_CABE = l_data(i).OID_CABE
                    AND X.NUM_UNID_ATEN > 0
                    AND  X.SOPO_OID_SOLI_POSI = Z.OID_SOLI_POSI
                    AND  Z.OFDE_OID_DETA_OFER = P.OID_DETA_OFER
                    AND  NOT EXISTS (SELECT NULL
                                     FROM FAC_TIPO_OFERT_EXCLU O
                                     WHERE O.TOFE_OID_TIPO_OFER = P.TOFE_OID_TIPO_OFER);

               l_decuentos     := l_sumaPrecSinImpuTotaFact - l_sumaPrecNetoTotaFact;
               l_baseImponible := l_sumaPrecNetoTotaFact + l_sumaPrecContTotaLoca;
               l_impVenta      := ROUND((l_sumaPrecNetoTotaFact * l_tasaImpu/100),2);
               l_total         := l_baseImponible + l_impVenta + l_data(i).IMP_FLET_TOTA_LOCA-l_sumaPrecContTotaLoca;

               UPDATE IMP_PAQUE_GENER_DOCUM_LASER IPGDL
               SET    IPGDL.SUM_NUM_UNID_ATEN  = l_sumaNumUnidAten,
                      IPGDL.SUM_PCON_TOTA_LOCA = l_sumaPrecContTotaLoca,
                      IPGDL.SUM_SIMP_TOTA_FACT = l_sumaPrecSinImpuTotaFact,
                      IPGDL.SUM_PNET_TOTA_FACT = l_sumaPrecNetoTotaFact,
                      IPGDL.VAL_TASA_IMPU      = l_tasaImpu,
                      IPGDL.IND_TASA_IMPU      = l_indicadorImpu,
                      IPGDL.VAL_DESC           = l_decuentos,
                      IPGDL.VAL_BASE_IMPO      = l_baseImponible,
                      IPGDL.VAL_IMPO_VENT      = l_impVenta,
                      IPGDL.VAL_TOTA           = l_total,
                      IPGDL.SUM_TOTA_LETR      = UPPER(GEN_FN_NUME_TO_TEXT(TRUNC(ABS(l_total)))) || ' y ' || TO_CHAR(ABS((l_total - TRUNC(l_total))) * 100) || '/100 ' || l_textoMoneda
               WHERE IPGDL.OID_CABE = l_data(i).OID_CABE;

             END LOOP;
          END IF;
          EXIT WHEN c_Cabecera%NOTFOUND;
          END LOOP;
     CLOSE c_Cabecera;
 END IF;

END IMP_PR_CALCU_DOCUM_LASER_FACTU;


/***************************************************************************
Descripcion       : Funcion que retorna valor de tasa de impuesto
parametro         : psIndicadorImpu  indicador de puesto tambien OUT                               retorno

Fecha Creacion    : 01/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
FUNCTION IMP_FN_OBTIE_TASA_IMPUE (psIndicadorImpu OUT VARCHAR2)
                                      RETURN NUMBER IS

      l_tasaImpu      PED_TASA_IMPUE.VAL_TASA_IMPU%TYPE;

BEGIN
       SELECT PTI.VAL_INDI_IMPU,
              PTI.VAL_TASA_IMPU
         INTO  psIndicadorImpu, l_tasaImpu
         FROM PED_IMPUE_GENER PIG,
              PED_TASA_IMPUE PTI,
              SEG_SUBAC SSA
        WHERE PIG.TAIM_OID_TASA_IMPU = PTI.OID_TASA_IMPU
          AND PIG.SBAC_OID_SBAC = SSA.OID_SBAC
          AND SSA.COD_SBAC = '000'; -- Facturacion

       RETURN l_tasaImpu;

EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_FN_OBTIE_TASA_IMPUE: ');
    RETURN NULL;
END IMP_FN_OBTIE_TASA_IMPUE;

/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Factura

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_FACTU IS

CURSOR c_documentoLaserFactura IS
      SELECT t.*
        FROM IMP_TMP_GENER_DOCUM_LASER T
       ORDER BY 2, 1, 3;

 l_fila c_documentoLaserFactura%ROWTYPE;
 l_facturaXML              CLOB;
 l_codClie              VARCHAR2(15);
 auxCodClie              VARCHAR2(15);

BEGIN
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_DOCUM_LASER_FACTU');
   auxCodClie:=NULL;
   OPEN c_documentoLaserFactura;
   LOOP
     FETCH c_documentoLaserFactura INTO l_fila;
     EXIT WHEN c_documentoLaserFactura%NOTFOUND;
       l_codClie:= l_fila.COD_CLIE;
       IF (l_codClie <> auxCodClie OR auxCodClie IS NULL) THEN
          INSERT INTO IMP_PAQUE_DOCUM_LASER_FACTU (
                      COD_CLIE,
                      XML_LASE_FACT)
          VALUES (
                 l_codClie,
                 EMPTY_CLOB())
          RETURNING XML_LASE_FACT INTO l_facturaXML;
          DBMS_LOB.writeappend(l_facturaXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;

      IF (l_codClie = auxCodClie ) then
            DBMS_LOB.writeappend(l_facturaXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;
      auxCodClie:= l_codClie;
   END LOOP;
   CLOSE c_documentoLaserFactura;

EXCEPTION
  WHEN OTHERS THEN
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_GENER_DOCUM_LASER_FACTU: '||ls_sqlerrm);
END IMP_PR_GENER_DOCUM_LASER_FACTU;


/***************************************************************************
Descripcion       : Procedimiemto Calcular Documentos Laser Guia

Fecha Creacion    : 15/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_GUIA(psCodPais VARCHAR2,
                                   psCodMarca VARCHAR2,
                                   psCodCanal VARCHAR2,
                                   psCodPeriodo VARCHAR2,
                                   psFechaFacturacion VARCHAR2) IS

    CURSOR c_Cabecera IS
     SELECT *
      FROM IMP_PAQUE_GENER_DOCUM_LASER;

    TYPE ARRAY_TYPE IS TABLE OF IMP_PAQUE_GENER_DOCUM_LASER%ROWTYPE;
    l_data ARRAY_TYPE;


    lnIdPeriodo         PED_SOLIC_CABEC.Perd_Oid_Peri%TYPE;

    lnIdMarca           NUMBER;
    lnIdCanal           NUMBER;

    l_sumaNumUnidAten            NUMBER(12,2);
    l_indicadorGenerarGuiaLaser  VARCHAR2(50);
    l_codigoTipoDocumentoGuia    VARCHAR2(3);


BEGIN

   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_GENER_DOCUM_LASER');
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_TMP_GENER_DOCUM_LASER');


   l_indicadorGenerarGuiaLaser := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarGuiaLaser');
   IF l_indicadorGenerarGuiaLaser IS NOT NULL AND l_indicadorGenerarGuiaLaser = 'S' THEN
      lnIdMarca       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_MARCA(psCodMarca);
      lnIdCanal       := gen_pkg_gener.GEN_FN_DEVUELVE_ID_CANAL(psCodCanal);
      lnIdPeriodo     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodPeriodo,
                                                                    lnIdMarca,
                                                                    lnIdCanal);

      l_codigoTipoDocumentoGuia := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'codigoTipoDocumentoGuia');

   INSERT INTO IMP_PAQUE_GENER_DOCUM_LASER
     SELECT   CON.VAL_NUME_SOLI,
         DOC_CONT.OID_CABE,
         NVL(DOC_CONT.NUM_DOCU_LEGA, DOC_CONT.NUM_DOCU_CONT_INTE) NUM_DOCU_LEGA,
         BAS_PAIS.COD_PAIS,
         BAS_PAIS.DES_PAIS,
         CON.FEC_FACT FEC_EMIS,
         DOC_CONT.NUM_DOCU_CONT_INTE,
         MC.OID_CLIE,
         MC.COD_CLIE,
         psCodPeriodo COD_PERI,
         ZON_REGIO.COD_REGI,
         ZON_ZONA.COD_ZONA,
         ZON_TERRI.COD_TERR,
         DOC_CONT.VAL_APE1 || ' ' || DOC_CONT.VAL_APE2 || ', ' || DOC_CONT.VAL_NOM1 || ' ' || DOC_CONT.VAL_NOM2 NOM_COMP,
         DOC_CONT.VAL_DIRE_COMP,
         DOC_CONT.VAL_NUME_IDEN_FISC,
         DOC_CONT.IMP_DESC_TOTA_LOCA,
         DOC_CONT.IMP_IMPU_TOTA_LOCA,
         DOC_CONT.IMP_FLET_TOTA_LOCA,
         DOC_CONT.VAL_TOTA_PAGA_LOCA,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL,
         NULL
    FROM FAC_DOCUM_CONTA_CABEC DOC_CONT,
         FAC_TIPO_DOCUM,
         PED_SOLIC_CABEC CON,
         MAE_CLIEN MC,
         BAS_PAIS,
         SEG_PAIS,
         ZON_TERRI,
         ZON_ZONA,
         ZON_REGIO
   WHERE DOC_CONT.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
     AND DOC_CONT.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU
     AND CON.CLIE_OID_CLIE = MC.OID_CLIE
     AND FAC_TIPO_DOCUM.COD_TIPO_DOCU = l_codigoTipoDocumentoGuia
     AND DOC_CONT.TERR_OID_TERR = ZON_TERRI.OID_TERR
     AND DOC_CONT.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
     AND DOC_CONT.ZORG_OID_REGI = ZON_REGIO.OID_REGI
     AND BAS_PAIS.COD_PAIS = SEG_PAIS.COD_PAIS
     AND SEG_PAIS.OID_PAIS = DOC_CONT.PAIS_OID_PAIS
     AND TO_CHAR (CON.FEC_FACT, 'dd/MM/yyyy') = psFechaFacturacion
     AND CON.PERD_OID_PERI = lnIdPeriodo
     AND SEG_PAIS.COD_PAIS = psCodPais;

      OPEN c_Cabecera;
          LOOP
          FETCH c_Cabecera BULK COLLECT INTO l_data LIMIT w_Filas;
          IF l_data.COUNT > 0 THEN
             FOR i IN l_data.FIRST .. l_data.LAST LOOP
                 SELECT SUM(X.NUM_UNID_ATEN)
                   INTO l_sumaNumUnidAten
                   FROM FAC_DOCUM_CONTA_LINEA X,
                        (SELECT VAL_I18N, VAL_OID
                           FROM GEN_I18N_SICC
                          WHERE ATTR_ENTI = 'MAE_PRODU') Y
                  WHERE X.PROD_OID_PROD = Y.VAL_OID
                    AND X.DCCA_OID_CABE = l_data(i).OID_CABE
                    AND X.NUM_UNID_ATEN > 0;

               UPDATE IMP_PAQUE_GENER_DOCUM_LASER IPGDL
               SET    IPGDL.SUM_NUM_UNID_ATEN  = l_sumaNumUnidAten
               WHERE IPGDL.OID_CABE = l_data(i).OID_CABE;

             END LOOP;
          END IF;
          EXIT WHEN c_Cabecera%NOTFOUND;
          END LOOP;
     CLOSE c_Cabecera;
   END IF;

END IMP_PR_CALCU_DOCUM_LASER_GUIA;

/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Guia

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_GUIA IS

CURSOR c_documentoLaserGuia IS
      SELECT t.*
        FROM IMP_TMP_GENER_DOCUM_LASER T
       ORDER BY 2, 1, 3;

 l_fila         c_documentoLaserGuia%ROWTYPE;
 l_guiaXML      CLOB;
 l_codClie      VARCHAR2(15);
 auxCodClie     VARCHAR2(15);

BEGIN
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_DOCUM_LASER_GUIA');
   auxCodClie:=NULL;
   OPEN c_documentoLaserGuia;
   LOOP
     FETCH c_documentoLaserGuia INTO l_fila;
     EXIT WHEN c_documentoLaserGuia%NOTFOUND;
       l_codClie:= l_fila.COD_CLIE;
       IF (l_codClie <> auxCodClie OR auxCodClie IS NULL) THEN
          INSERT INTO IMP_PAQUE_DOCUM_LASER_GUIA (
                      COD_CLIE,
                      XML_LASE_GUIA)
          VALUES (
                 l_codClie,
                 EMPTY_CLOB())
          RETURNING XML_LASE_GUIA INTO l_guiaXML;
          DBMS_LOB.writeappend(l_guiaXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;

      IF (l_codClie = auxCodClie ) then
            DBMS_LOB.writeappend(l_guiaXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;
      auxCodClie:= l_codClie;
   END LOOP;
   CLOSE c_documentoLaserGuia;

EXCEPTION
  WHEN OTHERS THEN
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_GENER_DOCUM_LASER_GUIA: '||ls_sqlerrm);
END IMP_PR_GENER_DOCUM_LASER_GUIA;


/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Cuenta Corriente

Fecha Creacion    : 22/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_CTCTE IS

CURSOR c_documentoLaserCtaCte IS
      SELECT t.*
        FROM IMP_TMP_GENER_DOCUM_LASER T
       ORDER BY 2, 1, 3;

 l_fila         c_documentoLaserCtaCte%ROWTYPE;
 l_ctaCteXML    CLOB;
 l_codClie      VARCHAR2(15);
 auxCodClie     VARCHAR2(15);

BEGIN
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_DOCUM_LASER_CTACT');
   auxCodClie:=NULL;
   OPEN c_documentoLaserCtaCte;
   LOOP
     FETCH c_documentoLaserCtaCte INTO l_fila;
     EXIT WHEN c_documentoLaserCtaCte%NOTFOUND;
       l_codClie:= l_fila.COD_CLIE;
       IF (l_codClie <> auxCodClie OR auxCodClie IS NULL) THEN
          INSERT INTO IMP_PAQUE_DOCUM_LASER_CTACT (
                      COD_CLIE,
                      XML_CUEN_CORR)
          VALUES (
                 l_codClie,
                 EMPTY_CLOB())
          RETURNING XML_CUEN_CORR INTO l_ctaCteXML;
          DBMS_LOB.writeappend(l_ctaCteXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;

      IF (l_codClie = auxCodClie ) then
            DBMS_LOB.writeappend(l_ctaCteXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;
      auxCodClie:= l_codClie;
   END LOOP;
   CLOSE c_documentoLaserCtaCte;

EXCEPTION
  WHEN OTHERS THEN
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_GENER_DOCUM_LASER_CTCTE: '||ls_sqlerrm);
END IMP_PR_GENER_DOCUM_LASER_CTCTE;

/***************************************************************************
Descripcion       : Procedimiento eliminar tablas de documento laser comun

Fecha Creacion    : 22/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_TABLA_DOCUM_LASER IS
BEGIN
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_GENER_DOCUM_LASER');
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_TMP_GENER_DOCUM_LASER');

EXCEPTION
  WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_GENER_DOCUM_LASER_CTCTE: ');
END IMP_PR_ELIMI_TABLA_DOCUM_LASER;




/***************************************************************************
Descripcion       : Procedimiemto Calcular Documentos Laser Nota Credito

Fecha Creacion    : 15/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_NOCRE(psCodPais VARCHAR2) IS

    CURSOR c_Cabecera IS
     SELECT *
      FROM IMP_PAQUE_GENER_DOCUM_LASER;

    TYPE ARRAY_TYPE IS TABLE OF IMP_PAQUE_GENER_DOCUM_LASER%ROWTYPE;
    l_data ARRAY_TYPE;


    l_indicadorImpu     PED_TASA_IMPUE.VAL_INDI_IMPU%TYPE;
    l_tasaImpu          PED_TASA_IMPUE.VAL_TASA_IMPU%TYPE;


    l_sumaNumUnidAten              NUMBER(12,2);
    l_sumaPrecContTotaLoca         NUMBER(12,2);
    l_sumaPrecSinImpuTotaFact      NUMBER(12,2);
    l_sumaPrecNetoTotaFact         NUMBER(12,2);

    l_decuentos                    NUMBER(12,2);
    l_baseImponible                NUMBER(12,2);
    l_impVenta                     NUMBER(12,2);
    l_total                        VARCHAR2(250);
    l_indicadorGenerarNtaCtoLaser  VARCHAR2(50);
    l_textoMoneda                  VARCHAR2(100);


BEGIN

   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_GENER_DOCUM_LASER');
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_TMP_GENER_DOCUM_LASER');
   gv_codPais := psCodPais;


   l_indicadorGenerarNtaCtoLaser := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarNotaCreditoLaser');
   l_textoMoneda := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'textoMoneda');


   IF l_indicadorGenerarNtaCtoLaser IS NOT NULL AND l_indicadorGenerarNtaCtoLaser = 'S' THEN
     l_tasaImpu      := IMP_FN_OBTIE_TASA_IMPUE(l_indicadorImpu);
     FOR c_notaCreditio IN (SELECT icdc.*
                      FROM IMP_CONTR_DOCUM_CONTA icdc
                      WHERE icdc.cod_pais = psCodPais
                            AND icdc.Cod_Tipo_Docu = NOTA_CREDITO) LOOP

     INSERT INTO IMP_PAQUE_GENER_DOCUM_LASER
     SELECT CON.VAL_NUME_SOLI,
            DOC_CONT.OID_CABE,
            NVL(DOC_CONT.NUM_DOCU_LEGA, DOC_CONT.NUM_DOCU_CONT_INTE) NUM_DOCU_LEGA,
            BAS_PAIS.COD_PAIS,
            BAS_PAIS.DES_PAIS,
            DOC_CONT.FEC_FACT,
            DOC_CONT.NUM_DOCU_CONT_INTE,
            MC.OID_CLIE,
            MC.COD_CLIE,
            SEG_PERIO_CORPO.COD_PERI,
            ZON_REGIO.COD_REGI,
            ZON_ZONA.COD_ZONA,
            ZON_TERRI.COD_TERR,
            DOC_CONT.VAL_APE1 || ' ' || DOC_CONT.VAL_APE2 || ', ' ||            DOC_CONT.VAL_NOM1 || ' ' || DOC_CONT.VAL_NOM2 NOM_COMP,
            DOC_CONT.VAL_DIRE_COMP,
            DOC_CONT.VAL_NUME_IDEN_FISC,
            DOC_CONT.IMP_DESC_TOTA_LOCA,
            DOC_CONT.IMP_IMPU_TOTA_LOCA,
            DOC_CONT.IMP_FLET_TOTA_LOCA,
            DOC_CONT.VAL_TOTA_PAGA_LOCA,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            SEG_PERIO_CORPO_REFE.COD_PERI COD_PERI_REFE,
            SOL_REFE.VAL_NUME_SOLI VAL_NUME_SOLI_REFE,
            FAC_TIPO_DOCUM.COD_TIPO_DOCU COD_TIPO_DOCU,
            DOC_CONT.VAL_SERI_DOCU_LEGA

       FROM FAC_DOCUM_CONTA_CABEC DOC_CONT,
            FAC_TIPO_DOCUM,
            PED_SOLIC_CABEC CON,
            PED_SOLIC_CABEC SOL_REFE,
            CRA_PERIO,
            SEG_PERIO_CORPO,
            CRA_PERIO CRA_PERIO_REFE,
            SEG_PERIO_CORPO SEG_PERIO_CORPO_REFE,
            MAE_CLIEN MC,
            BAS_PAIS,
            SEG_PAIS,
            ZON_TERRI,
            ZON_ZONA,
            ZON_REGIO
      WHERE DOC_CONT.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
        AND DOC_CONT.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU
        AND CON.SOCA_OID_DOCU_REFE = SOL_REFE.OID_SOLI_CABE(+)
        AND DOC_CONT.PERD_OID_PERI = CRA_PERIO.OID_PERI
        AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
        AND SOL_REFE.PERD_OID_PERI = CRA_PERIO_REFE.OID_PERI(+)
        AND CRA_PERIO_REFE.PERI_OID_PERI = SEG_PERIO_CORPO_REFE.OID_PERI(+)
        AND CON.CLIE_OID_CLIE = MC.OID_CLIE
        AND FAC_TIPO_DOCUM.COD_TIPO_DOCU = c_notaCreditio.Cod_Tipo_Docu_Cont
        AND DOC_CONT.TERR_OID_TERR = ZON_TERRI.OID_TERR
        AND DOC_CONT.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
        AND DOC_CONT.ZORG_OID_REGI = ZON_REGIO.OID_REGI
        AND SEG_PAIS.OID_PAIS = DOC_CONT.PAIS_OID_PAIS
        AND BAS_PAIS.COD_PAIS = SEG_PAIS.COD_PAIS
        AND BAS_PAIS.COD_PAIS = psCodPais
        AND DOC_CONT.NUM_DOCU_CONT_INTE IS NOT NULL
        AND ((DOC_CONT.IND_IMPR = 1) OR
            (DOC_CONT.IND_IMPR = 0 AND
            DOC_CONT.VAL_PREC_CATA_TOTA_LOCA < 0))
        AND DOC_CONT.OID_CABE > c_notaCreditio.Val_Ulti_Oid_Cabe;
    END LOOP;

            OPEN c_Cabecera;
                LOOP
                FETCH c_Cabecera BULK COLLECT INTO l_data LIMIT w_Filas;
                IF l_data.COUNT > 0 THEN
                   FOR i IN l_data.FIRST .. l_data.LAST LOOP
                       SELECT SUM(X.NUM_UNID_ATEN),
                        SUM(X.VAL_PREC_CONT_TOTA_LOCA),
                        SUM(DECODE(X.VAL_PREC_CATA_TOTA_LOCA,
                                   0,
                                   X.VAL_PREC_CONT_TOTA_LOCA,
                                   ROUND(X.VAL_PREC_CATA_TOTA_LOCA /
                                         (1 + l_tasaImpu / 100),
                                         2))) VAL_PREC_SIN_IMPU_TOTA_FACT,
                        SUM(DECODE(X.VAL_PREC_CATA_UNIT_LOCA,
                                   0,
                                   X.VAL_PREC_CONT_TOTA_LOCA,
                                   VAL_PREC_NETO_TOTA_LOCA)) VAL_PREC_NETO_TOTA_FACT
                       INTO l_sumaNumUnidAten,
                            l_sumaPrecContTotaLoca,
                            l_sumaPrecSinImpuTotaFact,
                            l_sumaPrecNetoTotaFact

                       FROM FAC_DOCUM_CONTA_LINEA X,
                            (SELECT VAL_I18N, VAL_OID
                               FROM GEN_I18N_SICC
                              WHERE ATTR_ENTI = 'MAE_PRODU') Y
                      WHERE X.PROD_OID_PROD = Y.VAL_OID
                        AND X.DCCA_OID_CABE = l_data(i).OID_CABE
                        AND X.NUM_UNID_ATEN <> 0;

                       l_decuentos     := l_sumaPrecSinImpuTotaFact - l_sumaPrecNetoTotaFact;
                       l_baseImponible := l_sumaPrecNetoTotaFact + l_sumaPrecContTotaLoca;
                       l_impVenta      := ROUND((l_sumaPrecNetoTotaFact * l_tasaImpu/100),2);
                       l_total  := l_baseImponible + l_impVenta + l_data(i).IMP_FLET_TOTA_LOCA-l_sumaPrecContTotaLoca;

                       UPDATE IMP_PAQUE_GENER_DOCUM_LASER IPGDL
                       SET    IPGDL.SUM_NUM_UNID_ATEN  = l_sumaNumUnidAten,
                              IPGDL.SUM_PCON_TOTA_LOCA = l_sumaPrecContTotaLoca,
                              IPGDL.SUM_SIMP_TOTA_FACT = l_sumaPrecSinImpuTotaFact,
                              IPGDL.SUM_PNET_TOTA_FACT = l_sumaPrecNetoTotaFact,
                              IPGDL.VAL_TASA_IMPU      = l_tasaImpu,
                              IPGDL.IND_TASA_IMPU      = l_indicadorImpu,
                              IPGDL.VAL_DESC           = l_decuentos,
                              IPGDL.VAL_BASE_IMPO      = l_baseImponible,
                              IPGDL.VAL_IMPO_VENT      = l_impVenta,
                              IPGDL.VAL_TOTA           = l_total,
                              IPGDL.SUM_TOTA_LETR      = UPPER(GEN_FN_NUME_TO_TEXT(TRUNC(ABS(l_total)))) || ' y ' || TO_CHAR(ABS((l_total - TRUNC(l_total))) * 100) || '/100 ' || l_textoMoneda
                       WHERE IPGDL.OID_CABE = l_data(i).OID_CABE;

                   END LOOP;
                END IF;
                EXIT WHEN c_Cabecera%NOTFOUND;
               END LOOP;
           CLOSE c_Cabecera;
   END IF;

 EXCEPTION
  WHEN OTHERS THEN
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_CALCU_DOCUM_LASER_NOCRE: '||ls_sqlerrm);
END IMP_PR_CALCU_DOCUM_LASER_NOCRE;


/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Nota Credito

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_NOCRE IS

CURSOR c_documentoLaserNotaCredito IS
      SELECT t.*
        FROM IMP_TMP_GENER_DOCUM_LASER T
       ORDER BY 2, 1, 3;

 l_fila             c_documentoLaserNotaCredito%ROWTYPE;
 l_notaCreditoXML   CLOB;
 l_oidCabe          NUMBER(12);
 auxOideCabe        NUMBER(12);
 l_maxOidCabe       NUMBER(12);
 l_procesar         BOOLEAN := FALSE;
 l_indicadorActuDocuLega VARCHAR2(50);

BEGIN

   -- Validamos el parametro de actualizacion de numeros de documento legal
   l_indicadorActuDocuLega := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorActualizarDocumentoLegal');

   -- EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_NOTA_CREDI_LASER'); Comentado para que no borre las notas (CHR 27/02/2009)

   auxOideCabe:=NULL;

   OPEN c_documentoLaserNotaCredito;
   LOOP
     FETCH c_documentoLaserNotaCredito INTO l_fila;
     EXIT WHEN c_documentoLaserNotaCredito%NOTFOUND;
       l_oidCabe:= l_fila.oid_cabe;
       IF (l_oidCabe <> auxOideCabe OR auxOideCabe IS NULL) THEN
           INSERT INTO IMP_NOTA_CREDI_LASER
              (COR_NOTA_CRED,
               COD_PAIS,
               OID_CLIE,
               COD_CLIE,
               OID_CABE,
               VAL_SERI_DOCU_LEGA,
               NUM_DOCU_CONT_INTE,
               COD_TIPO_DOCU,
               VAL_NUME_SOLI,
               COD_PERI,
               VAL_NUME_SOLI_REFE,
               COD_PERI_REFE,
               NUM_DOCU_LEGA,
               XML_NOTA_CRED,
               FEC_EMIS)
            VALUES
              (imp_seq_nota_credi_XML.Nextval,
               l_fila.COD_PAIS,
               l_fila.OID_CLIE,
               l_fila.COD_CLIE,
               l_oidCabe,
               l_fila.VAL_SERI_DOCU_LEGA,
               l_fila.NUM_DOCU_CONT_INTE,
               l_fila.COD_TIPO_DOCU,
               l_fila.VAL_NUME_SOLI,
               l_fila.COD_PERI,
               l_fila.VAL_NUME_SOLI_REFE,
               l_fila.COD_PERI_REFE,
               l_fila.NUM_DOCU_LEGA,
               EMPTY_CLOB(),
               l_fila.fec_emis)
          RETURNING XML_NOTA_CRED INTO l_notaCreditoXML;
          DBMS_LOB.writeappend(l_notaCreditoXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;

      IF (l_oidCabe = auxOideCabe ) then
            DBMS_LOB.writeappend(l_notaCreditoXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;
      auxOideCabe:= l_oidCabe;
      l_procesar:=TRUE;
   END LOOP;

  CLOSE c_documentoLaserNotaCredito;

  IF(l_procesar)THEN
     FOR c_notaCreditio IN (SELECT icdc.*
                            FROM IMP_CONTR_DOCUM_CONTA icdc
                            WHERE icdc.cod_pais = gv_codPais
                                  AND icdc.Cod_Tipo_Docu = NOTA_CREDITO) LOOP

       SELECT MAX(IMP_NOTA_CREDI_LASER.OID_CABE)
         INTO l_maxOidCabe
         FROM IMP_NOTA_CREDI_LASER
        WHERE IMP_NOTA_CREDI_LASER.COD_PAIS = gv_codPais
          AND IMP_NOTA_CREDI_LASER.COD_TIPO_DOCU = c_notaCreditio.Cod_Tipo_Docu_Cont;

       UPDATE IMP_CONTR_DOCUM_CONTA
          SET VAL_ANTE_OID_CABE = VAL_ULTI_OID_CABE,
              VAL_ULTI_OID_CABE = l_maxOidCabe
        WHERE IMP_CONTR_DOCUM_CONTA.COD_PAIS = gv_codPais
          AND IMP_CONTR_DOCUM_CONTA.COD_TIPO_DOCU_CONT = c_notaCreditio.Cod_Tipo_Docu_Cont;

      END LOOP;

       UPDATE IMP_CONTR_DOCUM_CONTA
          SET VAL_ULTI_OID_CABE = (SELECT MAX(IMP_CONTR_DOCUM_CONTA.VAL_ULTI_OID_CABE)
                                     FROM IMP_CONTR_DOCUM_CONTA
                                    WHERE IMP_CONTR_DOCUM_CONTA.COD_PAIS = gv_codPais
                                      AND IMP_CONTR_DOCUM_CONTA.COD_TIPO_DOCU = NOTA_CREDITO)
        WHERE IMP_CONTR_DOCUM_CONTA.COD_PAIS = gv_codPais
          AND IMP_CONTR_DOCUM_CONTA.COD_TIPO_DOCU = NOTA_CREDITO;


  END IF;

  -- Dependiendo del parametro, actualizamos el numero de documento legal en las notas de credito
  IF l_indicadorActuDocuLega IS NOT NULL AND l_indicadorActuDocuLega = 'S' THEN
      IMP_PKG_PROCE_COMPA.IMP_PR_ACTUA_NUMER_DOCUM_NOTCR;
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_GENER_DOCUM_LASER_NOCRE: '||ls_sqlerrm);
END IMP_PR_GENER_DOCUM_LASER_NOCRE;




/***************************************************************************
Descripcion       : Procedimiemto Calcular Documentos Laser Nota Debito

Fecha Creacion    : 15/10/2008 05:56:20 p.m.
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_CALCU_DOCUM_LASER_NODEB(psCodPais VARCHAR2) IS


     CURSOR c_Cabecera IS
     SELECT *
      FROM IMP_PAQUE_GENER_DOCUM_LASER;

    TYPE ARRAY_TYPE IS TABLE OF IMP_PAQUE_GENER_DOCUM_LASER%ROWTYPE;
    l_data ARRAY_TYPE;


    l_indicadorImpu     PED_TASA_IMPUE.VAL_INDI_IMPU%TYPE;
    l_tasaImpu          PED_TASA_IMPUE.VAL_TASA_IMPU%TYPE;

    l_sumaNumUnidAten              NUMBER(12,2);
    l_sumaPrecContTotaLoca         NUMBER(12,2);
    l_sumaPrecSinImpuTotaFact      NUMBER(12,2);
    l_sumaPrecNetoTotaFact         NUMBER(12,2);

    l_decuentos                    NUMBER(12,2);
    l_baseImponible                NUMBER(12,2);
    l_impVenta                     NUMBER(12,2);
    l_total                        VARCHAR2(250);
    l_indicadorGenerarNtaDtoLaser  VARCHAR2(50);
    l_textoMoneda                  VARCHAR2(100);



BEGIN

   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_PAQUE_GENER_DOCUM_LASER');
   EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_TMP_GENER_DOCUM_LASER');
   gv_codPais := psCodPais;

   l_indicadorGenerarNtaDtoLaser := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarNotaDebitoLaser');
   l_textoMoneda := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'textoMoneda');

   IF l_indicadorGenerarNtaDtoLaser IS NOT NULL AND l_indicadorGenerarNtaDtoLaser = 'S' THEN
   l_tasaImpu      := IMP_FN_OBTIE_TASA_IMPUE(l_indicadorImpu);
   FOR c_notaDebito IN (SELECT icdc.*
                      FROM IMP_CONTR_DOCUM_CONTA icdc
                      WHERE icdc.cod_pais = psCodPais
                            AND icdc.Cod_Tipo_Docu = NOTA_DEBITO) LOOP

     INSERT INTO IMP_PAQUE_GENER_DOCUM_LASER
     SELECT CON.VAL_NUME_SOLI,
            DOC_CONT.OID_CABE,
            NVL(DOC_CONT.NUM_DOCU_LEGA, DOC_CONT.NUM_DOCU_CONT_INTE) NUM_DOCU_LEGA,
            BAS_PAIS.COD_PAIS,
            BAS_PAIS.DES_PAIS,
            DOC_CONT.FEC_FACT,
            DOC_CONT.NUM_DOCU_CONT_INTE,
            MC.OID_CLIE,
            MC.COD_CLIE,
            SEG_PERIO_CORPO.COD_PERI,
            ZON_REGIO.COD_REGI,
            ZON_ZONA.COD_ZONA,
            ZON_TERRI.COD_TERR,
            DOC_CONT.VAL_APE1 || ' ' || DOC_CONT.VAL_APE2 || ', ' ||            DOC_CONT.VAL_NOM1 || ' ' || DOC_CONT.VAL_NOM2 NOM_COMP,
            DOC_CONT.VAL_DIRE_COMP,
            DOC_CONT.VAL_NUME_IDEN_FISC,
            DOC_CONT.IMP_DESC_TOTA_LOCA,
            DOC_CONT.IMP_IMPU_TOTA_LOCA,
            DOC_CONT.IMP_FLET_TOTA_LOCA,
            DOC_CONT.VAL_TOTA_PAGA_LOCA,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            NULL,
            SEG_PERIO_CORPO_REFE.COD_PERI COD_PERI_REFE,
            SOL_REFE.VAL_NUME_SOLI VAL_NUME_SOLI_REFE,
            FAC_TIPO_DOCUM.COD_TIPO_DOCU COD_TIPO_DOCU,
            DOC_CONT.VAL_SERI_DOCU_LEGA

       FROM FAC_DOCUM_CONTA_CABEC DOC_CONT,
            FAC_TIPO_DOCUM,
            PED_SOLIC_CABEC CON,
            PED_SOLIC_CABEC SOL_REFE,
            CRA_PERIO,
            SEG_PERIO_CORPO,
            CRA_PERIO CRA_PERIO_REFE,
            SEG_PERIO_CORPO SEG_PERIO_CORPO_REFE,
            MAE_CLIEN MC,
            BAS_PAIS,
            SEG_PAIS,
            ZON_TERRI,
            ZON_ZONA,
            ZON_REGIO
      WHERE DOC_CONT.SOCA_OID_SOLI_CABE = CON.OID_SOLI_CABE
        AND DOC_CONT.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU
        AND CON.SOCA_OID_DOCU_REFE = SOL_REFE.OID_SOLI_CABE(+)
        AND DOC_CONT.PERD_OID_PERI = CRA_PERIO.OID_PERI
        AND CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI
        AND SOL_REFE.PERD_OID_PERI = CRA_PERIO_REFE.OID_PERI(+)
        AND CRA_PERIO_REFE.PERI_OID_PERI = SEG_PERIO_CORPO_REFE.OID_PERI(+)
        AND CON.CLIE_OID_CLIE = MC.OID_CLIE
        AND FAC_TIPO_DOCUM.COD_TIPO_DOCU = c_notaDebito.Cod_Tipo_Docu_Cont
        AND DOC_CONT.TERR_OID_TERR = ZON_TERRI.OID_TERR
        AND DOC_CONT.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA
        AND DOC_CONT.ZORG_OID_REGI = ZON_REGIO.OID_REGI
        AND SEG_PAIS.OID_PAIS = DOC_CONT.PAIS_OID_PAIS
        AND BAS_PAIS.COD_PAIS = SEG_PAIS.COD_PAIS
        AND BAS_PAIS.COD_PAIS = psCodPais
        AND DOC_CONT.NUM_DOCU_CONT_INTE IS NOT NULL
        AND ((DOC_CONT.IND_IMPR = 1) OR
            (DOC_CONT.IND_IMPR = 0 AND
            DOC_CONT.VAL_PREC_CATA_TOTA_LOCA < 0))
        AND DOC_CONT.OID_CABE > c_notaDebito.Val_Ulti_Oid_Cabe;

   END LOOP;

            OPEN c_Cabecera;
                LOOP
                FETCH c_Cabecera BULK COLLECT INTO l_data LIMIT w_Filas;
                IF l_data.COUNT > 0 THEN
                   FOR i IN l_data.FIRST .. l_data.LAST LOOP
                       SELECT SUM(X.NUM_UNID_ATEN),
                        SUM(X.VAL_PREC_CONT_TOTA_LOCA),
                        SUM(DECODE(X.VAL_PREC_CATA_TOTA_LOCA,
                                   0,
                                   X.VAL_PREC_CONT_TOTA_LOCA,
                                   ROUND(X.VAL_PREC_CATA_TOTA_LOCA /
                                         (1 + l_tasaImpu / 100),
                                         2))) VAL_PREC_SIN_IMPU_TOTA_FACT,
                        SUM(DECODE(X.VAL_PREC_CATA_UNIT_LOCA,
                                   0,
                                   X.VAL_PREC_CONT_TOTA_LOCA,
                                   VAL_PREC_NETO_TOTA_LOCA)) VAL_PREC_NETO_TOTA_FACT
                       INTO l_sumaNumUnidAten,
                            l_sumaPrecContTotaLoca,
                            l_sumaPrecSinImpuTotaFact,
                            l_sumaPrecNetoTotaFact

                       FROM FAC_DOCUM_CONTA_LINEA X,
                            (SELECT VAL_I18N, VAL_OID
                               FROM GEN_I18N_SICC
                              WHERE ATTR_ENTI = 'MAE_PRODU') Y
                      WHERE X.PROD_OID_PROD = Y.VAL_OID
                        AND X.DCCA_OID_CABE = l_data(i).OID_CABE
                        AND X.NUM_UNID_ATEN <> 0;

                       l_decuentos     := l_sumaPrecSinImpuTotaFact - l_sumaPrecNetoTotaFact;
                       l_baseImponible := l_sumaPrecNetoTotaFact + l_sumaPrecContTotaLoca;
                       l_impVenta      := ROUND((l_sumaPrecNetoTotaFact * l_tasaImpu/100),2);
                       l_total  := l_baseImponible + l_impVenta + l_data(i).IMP_FLET_TOTA_LOCA;

                       UPDATE IMP_PAQUE_GENER_DOCUM_LASER IPGDL
                       SET    IPGDL.SUM_NUM_UNID_ATEN  = l_sumaNumUnidAten,
                              IPGDL.SUM_PCON_TOTA_LOCA = l_sumaPrecContTotaLoca,
                              IPGDL.SUM_SIMP_TOTA_FACT = l_sumaPrecSinImpuTotaFact,
                              IPGDL.SUM_PNET_TOTA_FACT = l_sumaPrecNetoTotaFact,
                              IPGDL.VAL_TASA_IMPU      = l_tasaImpu,
                              IPGDL.IND_TASA_IMPU      = l_indicadorImpu,
                              IPGDL.VAL_DESC           = l_decuentos,
                              IPGDL.VAL_BASE_IMPO      = l_baseImponible,
                              IPGDL.VAL_IMPO_VENT      = l_impVenta,
                              IPGDL.VAL_TOTA           = l_total,
                              IPGDL.SUM_TOTA_LETR      = UPPER(GEN_FN_NUME_TO_TEXT(TRUNC(ABS(l_total)))) || ' y ' || TO_CHAR(ABS((l_total - TRUNC(l_total))) * 100) || '/100 ' || l_textoMoneda
                       WHERE IPGDL.OID_CABE = l_data(i).OID_CABE;

                   END LOOP;
                END IF;
                EXIT WHEN c_Cabecera%NOTFOUND;
               END LOOP;
           CLOSE c_Cabecera;

   END IF;
 EXCEPTION
  WHEN OTHERS THEN
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_CALCU_DOCUM_LASER_NODEB: '||ls_sqlerrm);
END IMP_PR_CALCU_DOCUM_LASER_NODEB;



/***************************************************************************
Descripcion       : Procedimiento Generar Documento Laser Nota Debito

Fecha Creacion    : 07/10/2008
Autor             : Leonardo Lizana Chauca
***************************************************************************/
PROCEDURE IMP_PR_GENER_DOCUM_LASER_NODEB IS

CURSOR c_documentoLaserNotaDebito IS
      SELECT t.*
        FROM IMP_TMP_GENER_DOCUM_LASER T
       ORDER BY 2, 1, 3;

 l_fila             c_documentoLaserNotaDebito%ROWTYPE;
 l_notaDebitoXML   CLOB;
 l_oidCabe          NUMBER(12);
 auxOideCabe        NUMBER(12);
 l_maxOidCabe       NUMBER(12);
 l_procesar         BOOLEAN :=FALSE;
 l_indicadorActuDocuLega VARCHAR2(50);

BEGIN

   -- Validamos el parametro de actualizacion de numeros de documento legal
   l_indicadorActuDocuLega := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorActualizarDocumentoLegal');

   -- EXECUTE IMMEDIATE(' TRUNCATE TABLE IMP_NOTA_DEBIT_LASER'); Comentado para que no borre las notas (CHR 27/02/2009)

   auxOideCabe:=NULL;

   OPEN c_documentoLaserNotaDebito;
   LOOP
     FETCH c_documentoLaserNotaDebito INTO l_fila;
     EXIT WHEN c_documentoLaserNotaDebito%NOTFOUND;
       l_oidCabe:= l_fila.oid_cabe;
       IF (l_oidCabe <> auxOideCabe OR auxOideCabe IS NULL) THEN
           INSERT INTO IMP_NOTA_DEBIT_LASER
              (COR_NOTA_DEBI,
               COD_PAIS,
               OID_CLIE,
               COD_CLIE,
               OID_CABE,
               VAL_SERI_DOCU_LEGA,
               NUM_DOCU_CONT_INTE,
               COD_TIPO_DOCU,
               VAL_NUME_SOLI,
               COD_PERI,
               VAL_NUME_SOLI_REFE,
               COD_PERI_REFE,
               NUM_DOCU_LEGA,
               XML_NOTA_DEBI,
               FEC_EMIS
               )
            VALUES
              (imp_seq_nota_debit_XML.Nextval,
               l_fila.COD_PAIS,
               l_fila.OID_CLIE,
               l_fila.COD_CLIE,
               l_oidCabe,
               l_fila.VAL_SERI_DOCU_LEGA,
               l_fila.NUM_DOCU_CONT_INTE,
               l_fila.COD_TIPO_DOCU,
               l_fila.VAL_NUME_SOLI,
               l_fila.COD_PERI,
               l_fila.VAL_NUME_SOLI_REFE,
               l_fila.COD_PERI_REFE,
               l_fila.NUM_DOCU_LEGA,
               EMPTY_CLOB(),
               l_fila.fec_emis
              )
          RETURNING XML_NOTA_DEBI INTO l_notaDebitoXML;
          DBMS_LOB.writeappend(l_notaDebitoXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;

      IF (l_oidCabe = auxOideCabe ) then
            DBMS_LOB.writeappend(l_notaDebitoXML, LENGTH(l_fila.XML_PAGI), l_fila.XML_PAGI);
      END IF;
      auxOideCabe:= l_oidCabe;
      l_procesar:=TRUE;
   END LOOP;
   CLOSE c_documentoLaserNotaDebito;
   IF(l_procesar)THEN
        FOR c_notaDebito IN (SELECT icdc.*
                          FROM IMP_CONTR_DOCUM_CONTA icdc
                          WHERE icdc.cod_pais = gv_codPais
                                AND icdc.Cod_Tipo_Docu = NOTA_DEBITO) LOOP

         SELECT MAX(IMP_NOTA_DEBIT_LASER.OID_CABE)
           INTO l_maxOidCabe
           FROM IMP_NOTA_DEBIT_LASER
          WHERE IMP_NOTA_DEBIT_LASER.COD_PAIS = gv_codPais
            AND IMP_NOTA_DEBIT_LASER.COD_TIPO_DOCU = c_notaDebito.Cod_Tipo_Docu_Cont;


         UPDATE IMP_CONTR_DOCUM_CONTA
            SET VAL_ANTE_OID_CABE = VAL_ULTI_OID_CABE,
                VAL_ULTI_OID_CABE = l_maxOidCabe
          WHERE IMP_CONTR_DOCUM_CONTA.COD_PAIS = gv_codPais
            AND IMP_CONTR_DOCUM_CONTA.COD_TIPO_DOCU_CONT = c_notaDebito.Cod_Tipo_Docu_Cont;


          END LOOP;

          UPDATE IMP_CONTR_DOCUM_CONTA
          SET VAL_ULTI_OID_CABE = (SELECT MAX(IMP_CONTR_DOCUM_CONTA.VAL_ULTI_OID_CABE)
                                     FROM IMP_CONTR_DOCUM_CONTA
                                    WHERE IMP_CONTR_DOCUM_CONTA.COD_PAIS = gv_codPais
                                      AND IMP_CONTR_DOCUM_CONTA.COD_TIPO_DOCU = NOTA_CREDITO)
        WHERE IMP_CONTR_DOCUM_CONTA.COD_PAIS = gv_codPais
          AND IMP_CONTR_DOCUM_CONTA.COD_TIPO_DOCU = NOTA_CREDITO;
   END IF;

   -- Dependiendo del parametro, actualizamos el numero de documento legal en las notas de debido
   IF l_indicadorActuDocuLega IS NOT NULL AND l_indicadorActuDocuLega = 'S' THEN
       IMP_PKG_PROCE_COMPA.IMP_PR_ACTUA_NUMER_DOCUM_NOTDE;
   END IF;

EXCEPTION
  WHEN OTHERS THEN
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123,
                            'ERROR IMP_PR_GENER_DOCUM_LASER_NODEB: '||ls_sqlerrm);
END IMP_PR_GENER_DOCUM_LASER_NODEB;

/***************************************************************************
Descripcion       : Funcion usada para validar si una consultora cumple a?os
                    en el mes del proceso, retornando 0 si no cumple la condicion
                    y 1 si la cumple.

Fecha Creacion    : 27/10/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_VALID_CUMPL (p_codigoCliente IN VARCHAR)
RETURN NUMBER
IS

l_result NUMBER;

BEGIN

    SELECT COUNT(DISTINCT A.COD_CLIE)
    INTO l_result
    FROM MAE_CLIEN A, MAE_CLIEN_DATOS_ADICI B
    WHERE A.OID_CLIE = B.CLIE_OID_CLIE
    AND TO_CHAR(B.FEC_NACI, 'MM') = TO_CHAR(SYSDATE, 'MM')
    AND A.COD_CLIE = p_codigoCliente;

    RETURN l_result;

END IMP_FN_VALID_CUMPL;

/***************************************************************************
Descripcion       : Funcion usada para validar si una consultora tiene en su
                    pedido un codigo sap en particular.

Fecha Creacion    : 24/11/2010
Autor             : Jorge Yepez
***************************************************************************/
FUNCTION IMP_FN_VALID_VALE (p_oidSoli IN NUMBER)
RETURN VARCHAR2
IS

lv_tagPedidoNOChequear VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagPedidoNOChequear');
lv_tagPedidoChequear   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagPedidoChequear');

l_result VARCHAR2(100):=lv_tagPedidoNOChequear;
l_temp NUMBER:=0;

BEGIN

    SELECT count(1)
    into l_temp
    from dual
    where     exists (select *
    FROM ped_pedid_chequ A
    WHERE A.Val_Nume_Soli = p_oidSoli
    and a.cod_peri=(select cod_peri from bas_ctrl_fact where ind_camp_act=1 and sta_camp=0)
    )
    ;

    if l_temp=0 then
       l_result:=lv_tagPedidoNOChequear;
    else
       l_result:=lv_tagPedidoChequear;
    end if;

    RETURN l_result;

END IMP_FN_VALID_VALE;


/***************************************************************************
Descripcion       : Funcion que obtiene una cadena con el valor del cumplea?os
                    de un cliente, en el formato pasado como parametro.

Fecha Creacion    : 30/03/2009
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_FECHA_CUMPL (p_codigoCliente IN VARCHAR,
                             p_patron IN VARCHAR2 := 'DD-MONTH',
                             p_nlsDateLang IN VARCHAR2 := 'NLS_DATE_LANGUAGE = SPANISH')
RETURN VARCHAR2 IS

l_result VARCHAR2(100) := '';

BEGIN

    /*  Comentado hasta hacer la prueba con Xerox
    SELECT TRIM(TO_CHAR(B.FEC_NACI, p_patron, p_nlsDateLang))
    INTO l_result
    FROM MAE_CLIEN A, MAE_CLIEN_DATOS_ADICI B
    WHERE A.OID_CLIE = B.CLIE_OID_CLIE
    AND A.COD_CLIE = p_codigoCliente
    AND ROWNUM = 1;
    */

    RETURN l_result;

END IMP_FN_FECHA_CUMPL;


/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de la boleta de despacho
                    que va a ser compaginado..

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_BOLET_DESPA (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob  CLOB;
l_count NUMBER := 0;

CURSOR c_paqueteOriginal IS
SELECT
-- Dependiendo de los valores de la parametria se incluira el telefono
-- antes del tag configurado con el valor tagTelefono y si ademas el valor
-- del parametro indicadorTelefono es 'S'
CASE WHEN (gv_tagTelefono IS NOT NULL)
      AND (gv_indicadorTelefono = 'S')
      AND (GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF') IS NOT NULL OR
           GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM') IS NOT NULL
      ) THEN
    -- Tag de cumplea?os
    CASE WHEN (gv_tagCumpleanos IS NOT NULL)
          AND (gv_indicadorCumpleanos = 'S')
          AND (IMP_FN_VALID_CUMPL(S.COD_CLIE) != 0) THEN
        -- Telefono y cumplea?os
        gv_tagBoletaDespachoApertura ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, gv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(gv_tagCabeceraBoletaApertura) - (INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura))) ||
        gv_tagCumpleanosApertura || IMP_FN_FECHA_CUMPL(S.COD_CLIE) || gv_tagCumpleanosCierre ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(gv_tagCabeceraBoletaApertura), INSTR(S.XML_CONS, gv_tagTelefono, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura)) - (INSTR(S.XML_CONS, gv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(gv_tagCabeceraBoletaApertura))) ||
        gv_textoTelefono || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF')) || '/' || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM')) ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagTelefono, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura)), INSTR(S.XML_CONS, gv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, gv_tagTelefono, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura))))
    ELSE
        -- Solo telefono
        gv_tagBoletaDespachoApertura ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, gv_tagTelefono, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura)) - (INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura))) ||
        gv_textoTelefono || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TF')) || '/' || TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(M.OID_CLIE, 'TM')) ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagTelefono, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura)), INSTR(S.XML_CONS, gv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, gv_tagTelefono, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura))))
    END
ELSE
    CASE WHEN (gv_tagCumpleanos IS NOT NULL)
          AND (gv_indicadorCumpleanos = 'S')
          AND (IMP_FN_VALID_CUMPL(S.COD_CLIE) != 0) THEN
        -- Solo tag de cumplea?os
        gv_tagBoletaDespachoApertura ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, gv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(gv_tagCabeceraBoletaApertura) - (INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura))) ||
        gv_tagCumpleanosApertura || IMP_FN_FECHA_CUMPL(S.COD_CLIE) || gv_tagCumpleanosCierre ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagCabeceraBoletaApertura) + DBMS_LOB.GETLENGTH(gv_tagCabeceraBoletaApertura), INSTR(S.XML_CONS, gv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, gv_tagCabeceraBoletaApertura)  + DBMS_LOB.GETLENGTH(gv_tagCabeceraBoletaApertura)))
    ELSE
        -- Sin telefono y sin cumplea?os
        gv_tagBoletaDespachoApertura ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_CLASI_BOLET_DESPA(S.COD_CLIE) ||
        IMP_PKG_PROCE_COMPA.IMP_FN_ETIQU_ESTAT_BOLET_DESPA(S.COD_CLIE) ||
        SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura), INSTR(S.XML_CONS, gv_tagBoletaDespachoCierre) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoCierre) - (INSTR(S.XML_CONS, gv_tagBoletaDespachoApertura) + DBMS_LOB.GETLENGTH(gv_tagBoletaDespachoApertura)))
    END
END AS XML_BOLE_DESP
FROM IMP_PAQUE_DOCUM_SICC S,
     MAE_CLIEN M
WHERE S.COD_CLIE = M.COD_CLIE (+)
  AND S.COD_CLIE = p_codigoCliente
  AND S.VAL_NUME_SOLI = p_numeroSolicitud;

BEGIN

    FOR r_paqueteOriginal IN c_paqueteOriginal LOOP
        l_clob := r_paqueteOriginal.XML_BOLE_DESP;
    END LOOP;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
    l_clob := TO_CLOB('');
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_BOLET_DESPA (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));

END IMP_FN_COMPA_XML_BOLET_DESPA;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las boletas de recojo
                    que van a ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_BOREC (p_codigoCliente IN VARCHAR2,
                                 p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob     CLOB;
l_count    NUMBER := 0;
l_minNumeSoli NUMBER(10);

BEGIN

    BEGIN
        -- Primero validamos si existen boletas para el cliente
        SELECT COUNT(COD_CONS)
        INTO l_count
        FROM IMP_PAQUE_DOCUM_BOREC
        WHERE COD_CONS = p_codigoCliente;

        -- En caso hayan BR las concatenamos
        IF l_count > 0 THEN

            -- Obtenemos el minimo valor del numero de solicitud
            -- De esta manera solo compaginamos las boletas de recojo
            -- con el primer paquete documentario en caso haya mas de
            -- uno para una misma consultora
            SELECT MIN(VAL_NUME_SOLI)
            INTO l_minNumeSoli
            FROM IMP_PAQUE_DOCUM_SICC
            WHERE COD_CLIE = p_codigoCliente;

            -- Si ambos valores coinciden entonces enviamos la BR
            IF p_numeroSolicitud = l_minNumeSoli THEN
                SELECT XML_CONS
                INTO l_clob
                FROM IMP_PAQUE_DOCUM_BOREC
                WHERE COD_CONS = p_codigoCliente;
            END IF;
        END IF;

        IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
            l_clob := TO_CLOB('');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_BOREC (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));
    END;

    RETURN l_clob;

END IMP_FN_COMPA_XML_BOREC;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las cartas
                    que van a ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_CARTA (p_codigoCliente IN VARCHAR2,
                                 p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob  CLOB;
l_count NUMBER := 0;

-- Variables usadas para la compaginacion de las cartas
ln_longitudTagCartaCierre NUMBER := 0;

-- Variables usadas para calcular los indices de la subcadena
l_posicionInicial NUMBER := 0;
l_posicionFinal   NUMBER := 0;
l_posicionTemp    NUMBER := 0;

BEGIN

    -- Solamente se va a poder extraer la informacion si la parametria es correcta
    IF  gv_tagCartaApertura IS NOT NULL
    AND gv_tagCartaCierre IS NOT NULL
    AND gv_longitudTagCartaCierre IS NOT NULL THEN

        -- Convertimos el parametro al valor numerico
        ln_longitudTagCartaCierre := TO_NUMBER(gv_longitudTagCartaCierre);

        -- Primero identificamos las posiciones de las cuales substraer el contenido
        -- de las Cartas, para ello hacemos uso de las expresions regulares
        SELECT REGEXP_INSTR(XML_CONS, gv_tagCartaApertura)
        INTO l_posicionInicial
        FROM IMP_PAQUE_DOCUM_SICC
        WHERE COD_CLIE = p_codigoCliente
        AND VAL_NUME_SOLI = p_numeroSolicitud;

        -- Si no encontramos el tag de inicio entonces no existe bloque Cartas
        -- por tanto retornamos una cadena vacia
        IF l_posicionInicial = 0 THEN
            l_clob := TO_CLOB('');
        ELSE
            -- Obtenemos el maximo valor de la posicion final
            FOR l_count IN 1..20 LOOP
                SELECT REGEXP_INSTR(XML_CONS, gv_tagCartaCierre, 1, l_count)
                INTO l_posicionTemp
                FROM IMP_PAQUE_DOCUM_SICC
                WHERE COD_CLIE = p_codigoCliente
                AND VAL_NUME_SOLI = p_numeroSolicitud;

                -- Evaluamos el valor obtenido
                IF l_posicionTemp != 0 THEN
                    l_posicionFinal := l_posicionTemp;
                ELSE
                    EXIT;
                END IF;
            END LOOP;
        END IF;

        IF l_posicionInicial != 0 AND l_posicionFinal != 0 THEN
            BEGIN
              SELECT SUBSTR(S.XML_CONS,l_posicionInicial, (l_posicionFinal + ln_longitudTagCartaCierre) - l_posicionInicial)
              INTO l_clob
              FROM IMP_PAQUE_DOCUM_SICC S
              WHERE S.COD_CLIE = p_codigoCliente
              AND VAL_NUME_SOLI = p_numeroSolicitud;
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                l_clob := TO_CLOB('');
                WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_CARTA (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));
            END;
        ELSE
            l_clob := TO_CLOB('');
        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_CARTA (REVISAR PARAMETRIA)' ||substr(sqlerrm,1,250));
    END IF;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

END IMP_FN_COMPA_XML_CARTA;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del formato de Ultimas
                    Noticias que va a ser compaginado.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_ULTIM_NOTIC (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob  CLOB;
l_count NUMBER := 0;

-- Variables usadas para la compaginacion del Ultimas Noticias
ln_longitudTagUNCierre        NUMBER := 0;

-- Variables usadas para calcular los indices de la subcadena
l_posicionInicial NUMBER := 0;
l_posicionFinal   NUMBER := 0;
l_posicionTemp    NUMBER := 0;

-- Variables usadas para la inclusion del puntaje de lideres
l_posicionPuntajeLideres NUMBER := 0;
l_countMensajeLideres    NUMBER := 0;

BEGIN

    -- Solamente se va a poder extraer la informacion si la parametria es correcta
    IF  gv_tagUltimasNoticiasApertura IS NOT NULL
    AND gv_tagUltimasNoticiasCierre IS NOT NULL
    AND gv_longitudTagUNCierre IS NOT NULL THEN

        ln_longitudTagUNCierre := TO_NUMBER(gv_longitudTagUNCierre);

        -- Primero identificamos las posiciones de las cuales substraer el contenido
        -- de las Ultimas Noticias, para ello hacemos uso de las expresions regulares
        SELECT REGEXP_INSTR(XML_CONS, gv_tagUltimasNoticiasApertura)
        INTO l_posicionInicial
        FROM IMP_PAQUE_DOCUM_SICC
        WHERE COD_CLIE = p_codigoCliente
        AND VAL_NUME_SOLI = p_numeroSolicitud;

        -- Si no encontramos el tag de inicio entonces no existe bloque de Ultimas
        -- Noticias por tanto retornamos una cadena vacia
        IF l_posicionInicial = 0 THEN
            l_clob := TO_CLOB('');
        ELSE
            -- Obtenemos el maximo valor de la posicion final
            FOR l_count IN 1..18 LOOP
                SELECT REGEXP_INSTR(XML_CONS, gv_tagUltimasNoticiasCierre, 1, l_count)
                INTO l_posicionTemp
                FROM IMP_PAQUE_DOCUM_SICC
                WHERE COD_CLIE = p_codigoCliente
                AND VAL_NUME_SOLI = p_numeroSolicitud;

                -- Evaluamos el valor obtenido
                IF l_posicionTemp != 0 THEN
                    l_posicionFinal := l_posicionTemp;
                ELSE
                    EXIT;
                END IF;
            END LOOP;
        END IF;

        IF l_posicionInicial != 0 AND l_posicionFinal != 0 THEN
            BEGIN
                -- Validamos el mensaje de lideres
                SELECT COUNT(COD_CLIE)
                INTO l_countMensajeLideres
                FROM IMP_PAQUE_DOCUM_PUNTA_OBTEN
                WHERE COD_CLIE = p_codigoCliente;

                -- Si existe algun mensaje para el cliente
                IF l_countMensajeLideres > 0 THEN

                    -- Validamos si existe el texto a continuacion del cual
                    -- se va a colocar el mensaje
                    SELECT INSTR(S.XML_CONS, gv_tagPuntajeLideres)
                    INTO l_posicionPuntajeLideres
                    FROM IMP_PAQUE_DOCUM_SICC S
                    WHERE S.COD_CLIE = p_codigoCliente
                    AND S.VAL_NUME_SOLI = p_numeroSolicitud;

                    IF l_posicionPuntajeLideres <> 0
                       AND l_posicionPuntajeLideres > l_posicionInicial
                       AND l_posicionPuntajeLideres < l_posicionFinal THEN
                           SELECT
                           SUBSTR(S.XML_CONS, l_posicionInicial, l_posicionPuntajeLideres + DBMS_LOB.GETLENGTH(gv_tagPuntajeLideres) - l_posicionInicial) ||
                           O.XML_MENS_PUNT ||
                           SUBSTR(S.XML_CONS, l_posicionPuntajeLideres + DBMS_LOB.GETLENGTH(gv_tagPuntajeLideres), (l_posicionFinal + ln_longitudTagUNCierre) - (l_posicionPuntajeLideres + DBMS_LOB.GETLENGTH(gv_tagPuntajeLideres)))
                           INTO l_clob
                           FROM IMP_PAQUE_DOCUM_SICC S,
                                IMP_PAQUE_DOCUM_PUNTA_OBTEN O
                           WHERE S.COD_CLIE = O.COD_CLIE
                           AND S.COD_CLIE = p_codigoCliente
                           AND S.VAL_NUME_SOLI = p_numeroSolicitud;
                    ELSE
                        -- Si no existe simplemente retornamos el bloque de Ultimas Noticias
                        SELECT SUBSTR(S.XML_CONS,l_posicionInicial, (l_posicionFinal  + ln_longitudTagUNCierre) - l_posicionInicial)
                        INTO l_clob
                        FROM IMP_PAQUE_DOCUM_SICC S
                        WHERE S.COD_CLIE = p_codigoCliente
                        AND S.VAL_NUME_SOLI = p_numeroSolicitud;
                    END IF;
                ELSE
                    -- Retornamos el bloque de Ultimas Noticias
                    SELECT SUBSTR(S.XML_CONS,l_posicionInicial, (l_posicionFinal  + ln_longitudTagUNCierre) - l_posicionInicial)
                    INTO l_clob
                    FROM IMP_PAQUE_DOCUM_SICC S
                    WHERE S.COD_CLIE = p_codigoCliente
                    AND S.VAL_NUME_SOLI = p_numeroSolicitud;
                END IF;

            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                l_clob := TO_CLOB('');
                WHEN OTHERS THEN
                RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_ULTIM_NOTIC (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));
            END;
        ELSE
            l_clob := TO_CLOB('');
        END IF;
    ELSE
        RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_ULTIM_NOTIC (REVISAR PARAMETRIA)' ||substr(sqlerrm,1,250));
    END IF;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

END IMP_FN_COMPA_XML_ULTIM_NOTIC;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del detalle de factura
                    que va a ser compaginado.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_DETAL_FACTU (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob     CLOB;
l_count    NUMBER := 0;

CURSOR c_paqueteOriginal IS
SELECT XML_DETA_FACT
FROM IMP_PAQUE_DOCUM_DETAL_FACTU
WHERE COD_CONS = p_codigoCliente
AND VAL_NUME_SOLI = p_numeroSolicitud;

BEGIN

    FOR r_paqueteOriginal IN c_paqueteOriginal LOOP
        l_clob := r_paqueteOriginal.XML_DETA_FACT;
    END LOOP;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

    EXCEPTION
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_DETAL_FACTU (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));

END IMP_FN_COMPA_XML_DETAL_FACTU;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las hojas de picado
                    que van a ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_HOJA_PICAD (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob  CLOB;
l_count NUMBER := 0;

CURSOR c_paqueteOriginal IS
SELECT
CASE WHEN gv_tagHojaPicadoApertura IS NOT NULL
      AND gv_tagHojaPicadoCierre   IS NOT NULL
      AND INSTR(S.XML_CONS, gv_tagHojaPicadoApertura) <> 0 THEN
    SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagHojaPicadoApertura), INSTR(S.XML_CONS, gv_tagHojaPicadoCierre, -1) + DBMS_LOB.GETLENGTH(gv_tagHojaPicadoCierre) - (INSTR(S.XML_CONS, gv_tagHojaPicadoApertura)))
ELSE
    TO_CLOB('')
END XML_PADO_HOPI
FROM IMP_PAQUE_DOCUM_SICC S
WHERE S.COD_CLIE = p_codigoCliente
AND VAL_NUME_SOLI = p_numeroSolicitud;

CURSOR c_hojaPicado IS
SELECT H.XML_PADO_HOPI
INTO l_clob
FROM IMP_PAQUE_DOCUM_HOJA_PICAD H
WHERE H.COD_CLIE = p_codigoCliente
AND H.VAL_NUME_SOLI = p_numeroSolicitud;


BEGIN

    -- Primero buscamos la hoja de picado en la tabla del paquete documentario original
    FOR r_paqueteOriginal IN c_paqueteOriginal LOOP
        l_clob := r_paqueteOriginal.XML_PADO_HOPI;
    END LOOP;

    -- En caso no se encuentran resultados buscamos en la tabla de hoja de picado (Venezuela)
    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        FOR r_hojaPicado IN c_hojaPicado LOOP
            l_clob := r_hojaPicado.XML_PADO_HOPI;
        END LOOP;
    END IF;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

    EXCEPTION
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_HOJA_PICAD (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));

END IMP_FN_COMPA_XML_HOJA_PICAD;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de orden de compra simplificada
                    que va a ser compaginada.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_OCS (p_codigoCliente IN VARCHAR2,
                               p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob  CLOB;
l_count NUMBER := 0;

CURSOR c_paqueteOriginal IS
SELECT
CASE WHEN gv_tagOCSApertura IS NOT NULL
      AND gv_tagOCSCierre   IS NOT NULL
      AND INSTR(S.XML_CONS, gv_tagOCSApertura) <> 0 THEN
    SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagOCSApertura), INSTR(S.XML_CONS, gv_tagOCSCierre, -1) + DBMS_LOB.GETLENGTH(gv_tagOCSCierre) - (INSTR(S.XML_CONS, gv_tagOCSApertura)))
ELSE
    TO_CLOB('')
END XML_OCS
FROM IMP_PAQUE_DOCUM_SICC S
WHERE S.COD_CLIE = p_codigoCliente
AND S.VAL_NUME_SOLI = p_numeroSolicitud;

CURSOR c_paqueteDigitoCtrl IS
SELECT
CASE WHEN gv_tagOCSApertura IS NOT NULL
      AND gv_tagOCSCierre   IS NOT NULL
      AND INSTR(S.XML_CONS, gv_tagOCSApertura) <> 0 THEN
    SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagOCSApertura), INSTR(S.XML_CONS, '</ccon>', INSTR(S.XML_CONS, gv_tagOCSApertura)) - (INSTR(S.XML_CONS, gv_tagOCSApertura)))
    || C.COD_DIGI_CTRL ||
    SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, '</ccon>', INSTR(S.XML_CONS, gv_tagOCSApertura)), INSTR(S.XML_CONS, gv_tagOCSCierre, -1) + DBMS_LOB.GETLENGTH(gv_tagOCSCierre) - INSTR(S.XML_CONS, '</ccon>', INSTR(S.XML_CONS, gv_tagOCSApertura)))
ELSE
    TO_CLOB('')
END XML_OCS
FROM IMP_PAQUE_DOCUM_SICC S,
     MAE_CLIEN C
WHERE S.COD_CLIE = C.COD_CLIE
AND S.COD_CLIE = p_codigoCliente
AND S.VAL_NUME_SOLI = p_numeroSolicitud;

BEGIN

    IF gv_indicadorDigitoCtrlOCS IS NOT NULL AND gv_indicadorDigitoCtrlOCS = 'S' THEN
        FOR r_paqueteDigitoCtrl IN c_paqueteDigitoCtrl LOOP
            l_clob := r_paqueteDigitoCtrl.XML_OCS;
        END LOOP;
    ELSE
        FOR r_paqueteOriginal IN c_paqueteOriginal LOOP
            l_clob := r_paqueteOriginal.XML_OCS;
        END LOOP;
    END IF;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

    EXCEPTION
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_OCS (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));

END IMP_FN_COMPA_XML_OCS;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del formato de Ultimas
                    Noticias Privilege que va a ser compaginado.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_PRIVI (p_codigoCliente IN VARCHAR2,
                                 p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob     CLOB;
l_count    NUMBER := 0;

CURSOR c_paquetePrivilege IS
SELECT XML_CONS
FROM IMP_PAQUE_DOCUM_PRIVI
WHERE COD_CONS = p_codigoCliente;

BEGIN

    FOR r_paquetePrivilege IN c_paquetePrivilege LOOP
        l_clob := r_paquetePrivilege.XML_CONS;
    END LOOP;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

    EXCEPTION
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_PRIVI (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));

END IMP_FN_COMPA_XML_PRIVI;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML del cupon que va a ser
                    compaginado..

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_CUPON (p_codigoCliente IN VARCHAR2,
                                 p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob  CLOB;
l_count NUMBER := 0;

CURSOR c_paqueteOriginal IS
SELECT
CASE WHEN gv_tagFormatoCuponApertura IS NOT NULL
      AND gv_tagFormatoCuponCierre   IS NOT NULL
      AND INSTR(S.XML_CONS, gv_tagFormatoCuponApertura) <> 0 THEN
    SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagFormatoCuponApertura), INSTR(S.XML_CONS, gv_tagBloqueCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagBloqueCuponCierre) - (INSTR(S.XML_CONS, gv_tagFormatoCuponApertura))) ||
    L.XML_CUEN_CORR ||
    SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagBloqueCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagBloqueCuponCierre), INSTR(S.XML_CONS, gv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagFormatoCuponCierre) - (INSTR(S.XML_CONS, gv_tagBloqueCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagBloqueCuponCierre)))
ELSE
    TO_CLOB('')
END AS XML_CUPO
FROM IMP_PAQUE_DOCUM_SICC S, IMP_PAQUE_DOCUM_LASER_CTACT L
WHERE S.COD_CLIE = L.COD_CLIE (+)
AND INSTR(S.XML_CONS, gv_tagFormatoCuponApertura) != 0
AND S.COD_CLIE = p_codigoCliente
AND S.VAL_NUME_SOLI = p_numeroSolicitud;


CURSOR c_paqueteCupones IS
SELECT
SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagFormatoCuponApertura), INSTR(S.XML_CONS, gv_tagBloqueCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagBloqueCuponCierre) - (INSTR(S.XML_CONS, gv_tagFormatoCuponApertura))) ||
L.XML_CUEN_CORR ||
SUBSTR(S.XML_CONS, INSTR(S.XML_CONS, gv_tagBloqueCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagBloqueCuponCierre), INSTR(S.XML_CONS, gv_tagFormatoCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagFormatoCuponCierre) - (INSTR(S.XML_CONS, gv_tagBloqueCuponCierre) + DBMS_LOB.GETLENGTH(gv_tagBloqueCuponCierre))) AS XML_CUPO
FROM IMP_PAQUE_DOCUM_CUPON S, IMP_PAQUE_DOCUM_LASER_CTACT L
WHERE S.COD_CLIE = L.COD_CLIE (+)
AND S.COD_CLIE = p_codigoCliente
AND ROWNUM = 1;

BEGIN

    -- Primero buscamos el cupon en la tabla de cupones usada en Peru Esika o
    -- cuando se genera el cupon al momento de realizar la compaginacion
    FOR r_paqueteCupones IN c_paqueteCupones LOOP
        l_clob := r_paqueteCupones.XML_CUPO;
    END LOOP;

    -- En caso no se encuentre buscamos el cupon en la tabla del paquete documentario original
    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        FOR r_paqueteOriginal IN c_paqueteOriginal LOOP
            l_clob := r_paqueteOriginal.XML_CUPO;
        END LOOP;
    END IF;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB(gv_tagCuponVacio);
    END IF;

    RETURN l_clob;

    EXCEPTION
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_CUPON (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));

END IMP_FN_COMPA_XML_CUPON;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las facturas laser
                    ha ser compaginadas.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_LASER_FACTU (p_codigoCliente IN VARCHAR2,
                                       p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob     CLOB;
l_count    NUMBER := 0;
l_minNumeSoli NUMBER(10);

BEGIN

    BEGIN
        -- Primero validamos si existen factura para el cliente
        SELECT COUNT(COD_CLIE)
        INTO l_count
        FROM IMP_PAQUE_DOCUM_LASER_FACTU
        WHERE COD_CLIE = p_codigoCliente;

        -- En caso hayan notas las concatenamos
        IF l_count > 0 THEN

            -- Obtenemos el minimo valor del numero de solicitud
            -- De esta manera solo compaginamos las facturas
            -- con el primer paquete documentario en caso haya mas de
            -- uno para una misma consultora
            SELECT MIN(VAL_NUME_SOLI)
            INTO l_minNumeSoli
            FROM IMP_PAQUE_DOCUM_SICC
            WHERE COD_CLIE = p_codigoCliente;

            -- Si ambos valores coinciden entonces enviamos las facturas
            IF p_numeroSolicitud = l_minNumeSoli THEN
                SELECT XML_LASE_FACT
                INTO l_clob
                FROM IMP_PAQUE_DOCUM_LASER_FACTU
                WHERE COD_CLIE = p_codigoCliente;
            END IF;
        END IF;

        IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
            l_clob := TO_CLOB('');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_LASER_FACTU (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));
    END;

    RETURN l_clob;

END IMP_FN_COMPA_XML_LASER_FACTU;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de la guia de premios
                    qe va a ser compaginada.

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_LASER_GUIA (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob     CLOB;
l_count    NUMBER := 0;
l_minNumeSoli NUMBER(10);

BEGIN

    BEGIN
        -- Primero validamos si existen guias para el cliente
        SELECT COUNT(COD_CLIE)
        INTO l_count
        FROM IMP_PAQUE_DOCUM_LASER_GUIA
        WHERE COD_CLIE = p_codigoCliente;

        -- En caso hayan guias las concatenamos
        IF l_count > 0 THEN

            -- Obtenemos el minimo valor del numero de solicitud
            -- De esta manera solo compaginamos las guias
            -- con el primer paquete documentario en caso haya mas de
            -- uno para una misma consultora
            SELECT MIN(VAL_NUME_SOLI)
            INTO l_minNumeSoli
            FROM IMP_PAQUE_DOCUM_SICC
            WHERE COD_CLIE = p_codigoCliente;

            -- Si ambos valores coinciden entonces enviamos las guias
            IF p_numeroSolicitud = l_minNumeSoli THEN
                SELECT XML_LASE_GUIA
                INTO l_clob
                FROM IMP_PAQUE_DOCUM_LASER_GUIA
                WHERE COD_CLIE = p_codigoCliente;
            END IF;
        END IF;

        IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
            l_clob := TO_CLOB('');
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_COMPA_XML_LASER_GUIA (: '|| p_codigoCliente || '-' || p_numeroSolicitud || ')' ||substr(sqlerrm,1,250));
    END;

    RETURN l_clob;

END IMP_FN_COMPA_XML_LASER_GUIA;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las notas de credito
                    del cliente que van a ser incluidas en el compaginado.
                    Se toman en cuenta las notas de credito que aun no han
                    sido impresas (IND_IMPR = 0)

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_NOTA_CREDI (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob     CLOB;
l_count    NUMBER := 0;
l_rowcount NUMBER := 0;
l_minNumeSoli NUMBER(10);

-- Cursor para la extraccion de las notas de credito
CURSOR c_notas IS
SELECT N.COD_CLIE,
       N.XML_NOTA_CRED
FROM IMP_NOTA_CREDI_LASER N
WHERE N.COD_CLIE = p_codigoCliente
AND N.IND_IMPR = 'N'
ORDER BY N.VAL_SERI_DOCU_LEGA, N.NUM_DOCU_CONT_INTE;

r_notas c_notas%ROWTYPE;

BEGIN

    -- Primero validamos si existe notas para el cliente
    SELECT COUNT(N.COD_CLIE)
    INTO l_count
    FROM IMP_NOTA_CREDI_LASER N
    WHERE N.COD_CLIE = p_codigoCliente
    AND N.IND_IMPR = 'N';

    -- En caso hayan notas las concatenamos
    IF l_count > 0 THEN

        -- Obtenemos el minimo valor del numero de solicitud
        -- De esta manera solo compaginamos las notas de credito
        -- con el primer paquete documentario en caso haya mas de
        -- uno para una misma consultora
        SELECT MIN(VAL_NUME_SOLI)
        INTO l_minNumeSoli
        FROM IMP_PAQUE_DOCUM_SICC
        WHERE COD_CLIE = p_codigoCliente;

        -- Si ambos valores coinciden entonces enviamos las NC
        IF p_numeroSolicitud = l_minNumeSoli THEN
            OPEN c_notas;
            LOOP
            FETCH c_notas INTO r_notas;
            EXIT WHEN c_notas%NOTFOUND;

              IF l_rowcount = 0 THEN
                  l_clob := r_notas.XML_NOTA_CRED;
              ELSE
                  l_clob := l_clob || r_notas.XML_NOTA_CRED;
              END IF;

              l_rowcount := l_rowcount + 1;

            END LOOP;
            CLOSE c_notas;
        END IF;
    END IF;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

END IMP_FN_COMPA_XML_NOTA_CREDI;

/***************************************************************************
Descripcion       : Funcion usada para obtener el XML de las notas de debito
                    del cliente que van a ser incluidas en el compaginado.
                    Se toman en cuenta las notas de debito que aun no han
                    sido impresas (IND_IMPR = 0)

Fecha Creacion    : 04/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_NOTA_DEBIT (p_codigoCliente IN VARCHAR2,
                                      p_numeroSolicitud IN NUMBER)
RETURN CLOB
IS

l_clob     CLOB;
l_count    NUMBER := 0;
l_rowcount NUMBER := 0;
l_minNumeSoli NUMBER(10);

-- Cursor para la extraccion de las notas de debito
CURSOR c_notas IS
SELECT N.COD_CLIE,
       N.XML_NOTA_DEBI
FROM IMP_NOTA_DEBIT_LASER N
WHERE N.COD_CLIE = p_codigoCliente
AND N.IND_IMPR = 'N'
ORDER BY N.VAL_SERI_DOCU_LEGA, N.NUM_DOCU_CONT_INTE;

r_notas c_notas%ROWTYPE;

BEGIN

    -- Primero validamos si existe notas para el cliente
    SELECT COUNT(N.COD_CLIE)
    INTO l_count
    FROM IMP_NOTA_DEBIT_LASER N
    WHERE N.COD_CLIE = p_codigoCliente
    AND N.IND_IMPR = 'N';

    -- En caso hayan notas las concatenamos
    IF l_count > 0 THEN

        -- Obtenemos el minimo valor del numero de solicitud
        -- De esta manera solo compaginamos las notas de debito
        -- con el primer paquete documentario en caso haya mas de
        -- uno para una misma consultora
        SELECT MIN(VAL_NUME_SOLI)
        INTO l_minNumeSoli
        FROM IMP_PAQUE_DOCUM_SICC
        WHERE COD_CLIE = p_codigoCliente;

        -- Si ambos valores coinciden entonces enviamos las ND
        IF p_numeroSolicitud = l_minNumeSoli THEN
            OPEN c_notas;
            LOOP
            FETCH c_notas INTO r_notas;
            EXIT WHEN c_notas%NOTFOUND;

              IF l_rowcount = 0 THEN
                  l_clob := r_notas.XML_NOTA_DEBI;
              ELSE
                  l_clob := l_clob || r_notas.XML_NOTA_DEBI;
              END IF;

              l_rowcount := l_rowcount + 1;

            END LOOP;
            CLOSE c_notas;
        END IF;
    END IF;

    IF l_clob IS NULL OR DBMS_LOB.GETLENGTH(l_clob) = 0  THEN
        l_clob := TO_CLOB('');
    END IF;

    RETURN l_clob;

END IMP_FN_COMPA_XML_NOTA_DEBIT;

/***************************************************************************
Descripcion       : Funcion usada para obtener la sentencia SQL dinamica usada
                    para la compaginacion, la cual toma la parametria especificada
                    en la tabla IMP_FORMU_PAQUE_DOCUM, recibe como parametro el
                    numero de documento el cual se compara con el valor de la
                    columna NUM_PADO de la tabla anterior, asi como el orden
                    especificado en la tabla VAL_ORDE_SECU.

Fecha Creacion    : 05/11/2008
Autor             : Carlos Hurtado
***************************************************************************/
FUNCTION IMP_FN_COMPA_XML_SQL (p_numeroPaquete NUMBER)
RETURN VARCHAR2
IS

l_result VARCHAR2(4000);
l_count  NUMBER := 0;

-- Cursor para la extraccion de las funciones
CURSOR c_func IS
SELECT VAL_FUNC_XML
FROM IMP_FORMU_PAQUE_DOCUM
WHERE NUM_PADO = p_numeroPaquete
  AND EST_FORM_PADO = 1
ORDER BY NUM_PADO,
         NUM_ORDE_SECU;

r_func c_func%ROWTYPE;

BEGIN

    SELECT COUNT(COD_FORM_PADO)
    INTO l_count
    FROM IMP_FORMU_PAQUE_DOCUM
    WHERE NUM_PADO = p_numeroPaquete
    AND EST_FORM_PADO = 1;

    IF l_count = 0 THEN
        l_result := NULL;
    ELSE
        l_result := l_result || ' INSERT INTO IMP_PAQUE_DOCUM ';
        l_result := l_result || ' SELECT COR_PDSI, ';

        -- Determinamos si usamos el tag de inicio regular o
        -- el tag que indica si un pedido es de servicio
        IF  gv_indicadorPedidoServicio IS NOT NULL
        AND gv_tagInicioPedidoServicio IS NOT NULL
        AND gv_indicadorPedidoServicio = 'S' THEN
            l_result := l_result || ' DECODE(IND_PEDI_SERV, ''S'', ''' || gv_tagInicioPedidoServicio || ''', ''' || gv_tagInicio || ''') || ';
        ELSE
            l_result := l_result || ' ''' || gv_tagInicio || ''' || ';
        END IF;

        OPEN c_func;
        LOOP
        FETCH c_func INTO r_func;
        EXIT WHEN c_func%NOTFOUND;
            -- Concatenamos los valores de las funciones en base a la
            -- tabla de parametria la cual contiene las funciones que
            -- extraen la informacion de los bloques XML
            l_result := ' ' || l_result || r_func.VAL_FUNC_XML || '(COD_CLIE, VAL_NUME_SOLI) || ';
        END LOOP;
        CLOSE c_func;

        l_result := l_result || ' ''' || gv_tagFin || ''', ';
        l_result := l_result || ' COD_CLIE, ';
        l_result := l_result || p_numeroPaquete || ', ';
        l_result := l_result || ' VAL_NUME_SOLI ';
        l_result := l_result || ' FROM IMP_PAQUE_DOCUM_SICC ';
        l_result := l_result || ' ORDER BY COR_PDSI ';
    END IF;

    RETURN l_result;

END IMP_FN_COMPA_XML_SQL;

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, y los
                      otros formatos (detalle factura, boleta recojo, etc).
                      El orden de compaginacion se define a traves de la tabla
                      IMP_FORMU_PAQUE_DOCUM.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQUE_DOCUM_DINAM IS

-- Variables usadas para la compaginacion
l_query      VARCHAR2(4000);

-- Division de Paquete Documentario
lv_indBoletaIndependiente    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorBoletaIndependiente');
lv_indBorecIndependiente     VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorBoletaRecojoIndependiente');

BEGIN

  -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM
  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM';

  -- Dependiendo del parametro de generacion de la boleta de despacho en forma independiente
  -- hacemos 1 o 2 compaginaciones y posiblemente una tercera para las boletas de recojo
  IF lv_indBoletaIndependiente = 'S' THEN

      /***********************************************************************/
      /************************ Dos compaginaciones **************************/
      /***********************************************************************/

      /***********************************************************************/
      /* I) COMPAGINACION DEL PAQUETE DOCUMENTARIO SIN LA BOLETA DE DESPACHO */
      /***********************************************************************/
      l_query := NULL;
      l_query := IMP_FN_COMPA_XML_SQL(NRO_PAQUETE_NORMAL);
      IF l_query IS NOT NULL THEN
          EXECUTE IMMEDIATE l_query;
      END IF;

      /*******************************************************/
      /* II) COMPAGINACION DE LA BOLETA DE DESPACHO Y RECOJO */
      /*******************************************************/
      l_query := NULL;
      l_query := IMP_FN_COMPA_XML_SQL (NRO_PAQUETE_BOLETA_DESPACHO);
      IF l_query IS NOT NULL THEN
          EXECUTE IMMEDIATE l_query;
      END IF;

      -- Procesamos la informacion de aquellas que no han pasado pedido o de todas
      -- las boletas de recojo si esta configurado para que se generen en un archivo
      -- independiente
      IF lv_indBorecIndependiente = 'S' THEN
          -- Colocamos las todas boletas de recojo al final con otro numero de
          -- paquete documentario y ordenados en base a la secuenciacion de territorios
          INSERT INTO IMP_PAQUE_DOCUM
          SELECT ROWNUM,
                 XML_CONS,
                 COD_CONS,
                 NUM_PADO,
                 NULL -- Numero de Solicitud
          FROM (
          SELECT
              -- Concatenamos las diferentes porciones de XML
              -- 0) Tag de inicio
              gv_tagInicio ||

              -- 1) Boleta de Recojo
              P.XML_CONS ||

              -- 2) Tag de Fin
              gv_tagFin XML_CONS,

              P.COD_CONS,
              NRO_PAQUETE_BOLETA_RECOJO NUM_PADO --(3 para el paquete de boleta de recojo)
          FROM IMP_PAQUE_DOCUM_BOREC P,
               MAE_CLIEN B,
               MAE_CLIEN_UNIDA_ADMIN C,
               ZON_TERRI_ADMIN D,
               APP_RUTAS_TERRI E,
               APP_RUTAS_TRANS F
          WHERE P.COD_CONS = B.COD_CLIE
            AND B.OID_CLIE = C.CLIE_OID_CLIE
            AND C.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
            AND D.TERR_OID_TERR = E.TERR_OID_TERR
            AND E.RUTR_OID_RUTA_TRAN = F.OID_RUTA_TRAN
            AND C.IND_ACTI = 1
            AND C.PERD_OID_PERI_FIN IS NULL
          ORDER BY F.NUM_SECU,
                E.NUM_SECU,
                P.COD_CONS
          );
      ELSE
          -- A?adimos las boletas de recojo de aquellas que no han pasado pedido
          INSERT INTO IMP_PAQUE_DOCUM
          SELECT
          -- Correlativo
          (SELECT NVL(MAX(P.COR_PADO), 0) FROM IMP_PAQUE_DOCUM P) + ROWNUM COR_PADO,
          -- Concatenamos las diferentes porciones de XML
          -- 0) Tag de inicio
          gv_tagInicio ||

          -- 1) Boleta de Recojo
          B.XML_CONS ||

          -- 2) Tag de Fin
          gv_tagFin XML_CONS,

          B.COD_CONS,
          NRO_PAQUETE_BOLETA_DESPACHO, -- NUM_PADO (2 para el paquete de boleta de despacho/recojo)
          NULL -- Numero de Solicitud
          FROM IMP_PAQUE_DOCUM_BOREC B
          WHERE COD_CONS NOT IN (SELECT X.COD_CLIE FROM IMP_PAQUE_DOCUM X);
      END IF;

  ELSE
      /*********************************************************************/
      /********************** Una sola compaginacion ***********************/
      /*********************************************************************/
      l_query := NULL;
      l_query := IMP_FN_COMPA_XML_SQL(NRO_PAQUETE_NORMAL);
      IF l_query IS NOT NULL THEN
          EXECUTE IMMEDIATE l_query;
      END IF;

      -- Procesamos la informacion de aquellas que no han pasado pedido o de todas
      -- las boletas de recojo si esta configurado para que se generen en un archivo
      -- independiente
      IF lv_indBorecIndependiente = 'S' THEN
          -- Colocamos las todas boletas de recojo al final con otro numero de
          -- paquete documentario y ordenados en base a la secuenciacion de territorios
          INSERT INTO IMP_PAQUE_DOCUM
          SELECT ROWNUM,
                 XML_CONS,
                 COD_CONS,
                 NUM_PADO,
                 NULL -- Numero de Solicitud
          FROM (
          SELECT
              -- Concatenamos las diferentes porciones de XML
              -- 0) Tag de inicio
              gv_tagInicio ||

              -- 1) Boleta de Recojo
              P.XML_CONS ||

              -- 2) Tag de Fin
              gv_tagFin XML_CONS,

              P.COD_CONS,
              NRO_PAQUETE_BOLETA_RECOJO NUM_PADO -- (3 para el paquete de boleta de recojo)
          FROM IMP_PAQUE_DOCUM_BOREC P,
               MAE_CLIEN B,
               MAE_CLIEN_UNIDA_ADMIN C,
               ZON_TERRI_ADMIN D,
               APP_RUTAS_TERRI E,
               APP_RUTAS_TRANS F
          WHERE P.COD_CONS = B.COD_CLIE
            AND B.OID_CLIE = C.CLIE_OID_CLIE
            AND C.ZTAD_OID_TERR_ADMI = D.OID_TERR_ADMI
            AND D.TERR_OID_TERR = E.TERR_OID_TERR
            AND E.RUTR_OID_RUTA_TRAN = F.OID_RUTA_TRAN
            AND C.IND_ACTI = 1
            AND C.PERD_OID_PERI_FIN IS NULL
          ORDER BY F.NUM_SECU,
                E.NUM_SECU,
                P.COD_CONS
          );
      ELSE
          -- A?adimos las boletas de recojo de aquellas que no han pasado pedido
          INSERT INTO IMP_PAQUE_DOCUM
          SELECT
          -- Correlativo
          (SELECT NVL(MAX(P.COR_PADO), 0) FROM IMP_PAQUE_DOCUM P) + ROWNUM COR_PADO,
          -- Concatenamos las diferentes porciones de XML
          -- 0) Tag de inicio
          gv_tagInicio ||

          -- 1) Boleta de Recojo
          B.XML_CONS ||

          -- 2) Tag de Fin
          gv_tagFin XML_CONS,

          B.COD_CONS,
          NRO_PAQUETE_NORMAL, -- NUM_PADO (1 para la compaginacion normal)
          NULL -- Numero de Solicitud
          FROM IMP_PAQUE_DOCUM_BOREC B
          WHERE COD_CONS NOT IN (SELECT X.COD_CLIE FROM IMP_PAQUE_DOCUM X);
      END IF;

  END IF;

  COMMIT;

  EXCEPTION
  WHEN OTHERS THEN
      RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_COMPA_PAQUE_DOCUM_DINAM: '||substr(sqlerrm,1,250));
END IMP_PR_COMPA_PAQUE_DOCUM_DINAM;

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
                      Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQUE_DOCUM IS

-- Variables usadas para la compaginacion
lv_compaginacionDinamica VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorCompaginacionDinamica');

-- Variable usada para eliminar los detalles erroneos
lv_eliminacionDetalles VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorEliminacionDetallesErroneos');

-- Variable usada para el reemplazo de la direccion de la boleta de despacho
lv_reemplazoDireccion VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoDireccionDespacho');

lv_reemplazoOCS VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCS');

lv_reemplazoOCSFusion VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCSFusion'),'N');

lv_reemplazoOCSDni VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCSDni'),'N');

lv_codpais VARCHAR2(10);

BEGIN

    /* Actualizamos los valores de las variables globales */
    -- Variables usadas para imprimir el inicio y fin de cada paquete
    gv_tagInicio                    := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicio');
    gv_tagFin                       := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFin');

    -- Variables usadas para la compaginacion de la boleta de despacho
    gv_tagBoletaDespachoApertura    := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagBoletaDespachoApertura');
    gv_tagBoletaDespachoCierre      := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagBoletaDespachoCierre');
    gv_tagCabeceraBoletaApertura    := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCabeceraBoletaDepachoApertura');

    -- Variables usadas para la inclusion del telefono en la boleta de despacho
    gv_tagTelefono                  := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagTelefono');
    gv_indicadorTelefono            := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorTelefono');
    gv_textoTelefono                := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','textoTelefono');

    -- Variables usadas para la inclusion del tag de saludo por cumplea?os
    gv_indicadorCumpleanos          := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorCumpleanos');
    gv_tagCumpleanos                := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanos');
    gv_tagCumpleanosApertura        := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosApertura');
    gv_tagCumpleanosCierre          := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCumpleanosCierre');

    -- Variables usadas para la compaginacion de las cartas
    gv_tagCartaApertura             := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCartaApertura');
    gv_tagCartaCierre               := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCartaCierre');
    gv_longitudTagCartaCierre       := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','longitudTagCartaCierre');

    -- Variables usadas para la compaginacion del Ultimas Noticias
    gv_tagUltimasNoticiasApertura   := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagUltimasNoticiasApertura');
    gv_tagUltimasNoticiasCierre     := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagUltimasNoticiasCierre');
    gv_longitudTagUNCierre          := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','longitudTagUNCierre');

    -- Variables usadas para la inclusion del puntaje de lideres
    gv_tagPuntajeLideres            := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagPuntajeLideres');

    -- Variables usadas para la compaginacion de la hoja de picado
    gv_tagHojaPicadoApertura        := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagHojaPicadoApertura');
    gv_tagHojaPicadoCierre          := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagHojaPicadoCierre');

    -- Variables usadas para la compaginacion de la OCS
    gv_tagOCSApertura               := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagOCSApertura');
    gv_tagOCSCierre                 := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagOCSCierre');
    gv_indicadorDigitoCtrlOCS       := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorDigitoCtrlOCS');

    -- Variables usadas para la compaginacion del cupon
    gv_tagCuponVacio                := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagCuponVacio');
    gv_tagFormatoCuponApertura      := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFormatoCuponApertura');
    gv_tagFormatoCuponCierre        := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFormatoCuponCierre');
    gv_tagBloqueCuponCierre         := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagBloqueCuponCierre');

    -- Variables usadas para la identificacion de pedidos de servicio
    gv_indicadorPedidoServicio      := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorPedidoServicio');
    gv_tagInicioPedidoServicio      := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');

    -- Dependiendo del parametro invocamos a la logica de compaginacion antigua
    -- o aquella cuyo orden se configura a traves de la tabla IMP_FORMU_PAQUE_DOCUM
    IF lv_compaginacionDinamica = 'S' THEN
        IMP_PR_COMPA_PAQUE_DOCUM_DINAM;
    ELSE
        IMP_PR_COMPA_PAQUE_DOCUM_ESTAT;
    END IF;

    COMMIT;

    /*
    Eliminamos los bloques del paquete documentario que estan en la
    tabla IMP_ETIQU_ELIMI
    */
    IMP_PR_ELIMI_BLOQUE_PAQUE;

    /* Invocamos al store encargado de colocar el atributo vacio="1" */
    IMP_PR_ELIMI_DOCUM_VACIO;

    /* Invocamos al store encargado de reemplazar los caracteres especiales */
    IMP_PR_REEMP_CARAC_ESPEC;

    /* Eliminacion de detalles con error */
    IF lv_eliminacionDetalles = 'S' THEN
        DELETE FROM IMP_PAQUE_DOCUM
        WHERE INSTR(XML_CONS, '$consultora.VAL_IMPO_TOTA_PAGA') != 0
        AND NUM_PADO = NRO_PAQUETE_NORMAL;
    END IF;

    /* Reemplazo de la direccion de despacho */
    IF lv_reemplazoDireccion = 'S' THEN
        IMP_PR_REEMP_DIREC_BOLET_DESPA;
    END IF;

    IF lv_reemplazoOCS = 'S' THEN
        IMP_PR_REEMP_OCS;
        IMP_PR_INCL_FLEX;
    END IF;


    IF lv_reemplazoOCSFusion = 'S' THEN

       select distinct cod_pais into lv_codpais from bas_ctrl_fact where rownum=1;
       IMP_PR_REEMP_DATOS_OCS(lv_codpais);

    END IF;

    IF lv_reemplazoOCSDni = 'S' THEN

       select distinct cod_pais into lv_codpais from bas_ctrl_fact where rownum=1;
       IMP_PR_REEMP_DNI_OCS(lv_codpais);

    END IF;



END IMP_PR_COMPA_PAQUE_DOCUM;

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
                      Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_ZONA(p_oidzona NUMBER, psCodPeri VARCHAR2, psFechaFacturacion VARCHAR2) IS


-- Variable usada para eliminar los detalles erroneos
lv_eliminacionDetalles VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorEliminacionDetallesErroneos');


CURSOR c_cons(p_oidperi NUMBER, p_fecfact VARCHAR2, numeroLoteFacturacion NUMBER, indicadorEnvioLarissa NUMBER) IS
select a.val_nume_soli, b.cod_clie
from ped_solic_cabec a, mae_clien b
where a.perd_oid_peri=p_oidperi
and a.clie_oid_clie=b.oid_clie
and a.fec_fact=to_date(p_fecfact, 'dd/mm/yyyy')
and a.num_unid_aten_tota>0
and a.ind_ts_no_conso=0
and a.zzon_oid_zona=p_oidzona
AND a.IND_INTE_LARI_GENE = indicadorEnvioLarissa
AND (numeroLoteFacturacion IS NULL OR a.NUM_LOTE_FACT = numeroLoteFacturacion)
and exists (
      select null
      from int_lar_tipo_solici_pedido_dis l
      where l.tspa_oid_tipo_soli_pais = a.tspa_oid_tipo_soli_pais
)
;

r_cons c_cons%ROWTYPE;

    lv_codpais   varchar2(20);
    --w_filas        NUMBER(12);
    ln_oidperi   NUMBER;



    l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
    l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
    l_tipocompa                     VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorTipoCompa'),'1');
    l_numeroLoteFacturacion         NUMBER;

begin



    ln_oidperi:=gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(psCodPeri);


    SELECT z.cod_pais   into lv_codpais
                                   FROM cra_perio       x,
                                        seg_perio_corpo y,
                                        bas_ctrl_fact   z
                                  WHERE x.peri_oid_peri = y.oid_peri
                                    AND y.cod_peri = z.cod_peri
                                    AND z.ind_camp_act = 1
                                    AND z.sta_camp = 0;


    IF (l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN
        BEGIN
          SELECT MAX(con.num_lote_fact)
          INTO l_numeroLoteFacturacion
          FROM ped_solic_cabec con,
               int_lar_tipo_solici_pedido_dis tspd
         WHERE con.perd_oid_peri = ln_oidperi
           AND con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
           AND con.ind_inte_lari_gene = l_indicadorEnvioLarissa
           AND con.ind_ts_no_conso = 0
           AND (con.ind_pedi_prue = 0 OR con.ind_pedi_prue IS NULL)
           AND con.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais;
           --AND con.pais_oid_pais = l_oidPais;
        EXCEPTION
        WHEN OTHERS THEN
            l_numeroLoteFacturacion := NULL;
        END;
    END IF;


    OPEN c_cons(ln_oidperi,psFechaFacturacion,l_numeroLoteFacturacion,l_indicadorEnvioLarissa);
    LOOP
    FETCH c_cons INTO r_cons;
    EXIT WHEN c_cons%NOTFOUND;

          if l_tipocompa='1' then

          IMP_PR_COMPA_PAQDO_FINAL(r_cons.val_nume_soli, r_cons.cod_clie);
          else
             --IMP_PR_COMPA_PAQDO_FINAL2(r_cons.val_nume_soli, r_cons.cod_clie);
             IMP_PR_COMPA_PAQDO_FINAL3(r_cons.val_nume_soli, r_cons.cod_clie);
          end if;


    END LOOP;
    CLOSE c_cons;


    /*
    Eliminamos los bloques del paquete documentario que estan en la
    tabla IMP_ETIQU_ELIMI
    */
    --IMP_PR_ELIMI_BLOQUE_PAQUE;

    /* Invocamos al store encargado de colocar el atributo vacio="1" */
    --IMP_PR_ELIMI_DOCUM_VACIO;

    /* Invocamos al store encargado de reemplazar los caracteres especiales */
    --IMP_PR_REEMP_CARAC_ESPEC;

    /* Eliminacion de detalles con error */
    IF lv_eliminacionDetalles = 'S' THEN
        DELETE FROM IMP_PAQUE_DOCUM_FINAL
        WHERE INSTR(XML_CONS, '$consultora.VAL_IMPO_TOTA_PAGA') != 0
        AND NUM_PADO = NRO_PAQUETE_NORMAL;
    END IF;

END IMP_PR_COMPA_PAQDO_ZONA;
/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
                      Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_FINAL2(p_valnumesoli NUMBER, p_codclie varchar2) IS

-- Variable usada para eliminar los detalles erroneos
lv_eliminacionDetalles VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorEliminacionDetallesErroneos');
l_clob                 CLOB;
l_clob_temp            CLOB;
l_clob_temp2           CLOB;


CURSOR c_pado IS
select cod_pado from IMP_COMPA_PAQUE_DOCUM
;

r_pado c_pado%ROWTYPE;


CURSOR c_form(p_codpado varchar2) IS
select a.cod_form from IMP_FORMU_PAQUE_DOCU2 a
where a.cod_pado=p_codpado and a.est_form=1
order by a.num_orde_secu
;

r_form c_form%ROWTYPE;

/*CURSOR c_borec IS
select a.xml_cons from IMP_PAQUE_DOCUM_BOREC a where a.cod_cons
not in (select b.cod_clie from imp_paque_docum_final b)
;

r_borec c_borec%ROWTYPE;

*/

    ln_oidperi    NUMBER(10);
--    ln_borec      NUMBER(1):=0;
    lv_fecfact   varchar2(20);

    lv_codpais   varchar2(20);
    --w_filas        NUMBER(12);

ped_serv NUMBER := 0;
ped_expr NUMBER := 0;
ped_flex NUMBER := 0;
lv_indicadorPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorPedidoServicio');
lv_indicadorNMP              VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorNMP');
lv_tagInicioPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');
lv_tagInicioPedidoServicio2   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio2');
lv_tagInicioPedidoServicio3   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio3');
lv_tagInicio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicio');
lv_tagFin VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFin');


begin



    OPEN c_pado;
    LOOP
    FETCH c_pado INTO r_pado;
    EXIT WHEN c_pado%NOTFOUND;


            INSERT INTO IMP_PAQUE_DOCUM_FINAL(
            COR_PADO,
            COD_CLIE,
            NUM_PADO,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            p_valnumesoli,
            p_codclie,
            r_pado.cod_pado,
            p_valnumesoli,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            if lv_indicadorPedidoServicio='1' then

            select count(1)
            into ped_serv
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and
            (
            exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and ind_oc=1)
            or (
                   exists (select 1
                          from ped_solic_cabec x, ped_tipo_solic_pais y, ped_tipo_solic z, gen_i18n_sicc_comun gen
                          where x.tspa_oid_tipo_soli_pais=y.oid_tipo_soli_pais
                          and y.tsol_oid_tipo_soli=z.oid_tipo_soli
                          and z.oid_tipo_soli=gen.val_oid
                          and gen.attr_enti='PED_TIPO_SOLIC'
                          and gen.val_i18n like '%NMP%'
                          and x.soca_oid_soli_cabe=a.oid_soli_cabe
                          )
                   and lv_indicadorNMP='1'
               )
            )
            ;

            select count(1)
            into ped_expr
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%XPRES%'
            );


            select count(1)
            into ped_flex
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%CARGO POR USO%'
            and (select count(1) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe)=1
            );

            end if;

            if ped_serv=0 and lv_indicadorPedidoServicio='1' then
              if ped_expr=0 and ped_flex=0 then
                l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio;
                 else
                  if ped_expr>0 then
                          l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio2;
                  else
                         l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio3;
                 end if;
              end if;
            else
                l_clob_temp:=l_clob_temp || lv_tagInicio;
            end if;



            OPEN c_form(r_pado.cod_pado);
            LOOP
            FETCH c_form INTO r_form;
            EXIT WHEN c_form%NOTFOUND;

begin
                  if r_form.cod_form='BD' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_bolet_despa x
                     where x.val_nume_soli=p_valnumesoli;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);


                  end if;
                  if r_form.cod_form='OC' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_ocs x
                     where x.val_nume_soli=p_valnumesoli;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='UN' then
                     select x.xml_unot into l_clob_temp2 from imp_paque_docum_unot x
                     where x.val_nume_soli=p_valnumesoli;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='DF' then
                     select x.xml_deta_fact into l_clob_temp2 from imp_paque_docum_detal_factu x
                     where x.val_nume_soli=p_valnumesoli;
                    /*if LENGTH(l_clob_temp)>30000 then
                     DBMS_LOB.writeappend(l_clob, 30000, substr(l_clob_temp,1,30000));
                     DBMS_LOB.writeappend(l_clob, length(substr(l_clob_temp,30001)), substr(l_clob_temp,30001));
                    else
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     end if;*/

                     l_clob_temp:=l_clob_temp || l_clob_temp2;


                  end if;
                  if r_form.cod_form='CP' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || '<frmecc>' || l_clob_temp2 || '</frmecc>';

                     --DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     --DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='CT' then
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || '<frmecc>' || l_clob_temp2 ;

                     --DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                     select x.xml_cuen_corr into l_clob_temp2 from imp_paque_docum_laser_ctact x
                     where x.cod_clie=p_codclie;

                     l_clob_temp:=l_clob_temp || l_clob_temp2 || '</frmecc>' ;

                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     --DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='BR' then
                    -- ln_borec:=1;
                     select x.xml_cons into l_clob_temp2 from imp_paque_docum_borec x
                     where x.cod_cons=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  if r_form.cod_form='FA' then
                    -- ln_borec:=1;
                     select x.xml_lase_fact into l_clob_temp2 from IMP_PAQUE_DOCUM_LASER_FACTU x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  if r_form.cod_form='FX' then
                    -- ln_borec:=1;
                     begin
                     select x.xml_cons into l_clob_temp2 from flx_paque_docum x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     exception when others then
                       null;
                     end;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  if r_form.cod_form='PP' then
                    -- ln_borec:=1;
                     select x.xml_prpu into l_clob_temp2 from IMP_PAQUE_DOCUM_PROGR_PUNTO x
                     where x.cod_clie=p_codclie and rownum=1;

                     l_clob_temp:=l_clob_temp || l_clob_temp2;
                     --DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;




                  exception when others then
                     continue;
                  end;

            END LOOP;
            CLOSE c_form;
            --DBMS_LOB.writeappend(l_clob, LENGTH('</pd>'), '</pd>');

                  if LENGTH(l_clob_temp)>30000 then
                      while LENGTH(l_clob_temp)>30000
                      loop
                       DBMS_LOB.writeappend(l_clob, 30000, substr(l_clob_temp,1,30000));
                       l_clob_temp:=substr(l_clob_temp,30001);
                       end loop;
                       l_clob_temp:=l_clob_temp || '</pd>';
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  else
                       l_clob_temp:=l_clob_temp || '</pd>';
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                   end if;

            l_clob_temp:='';


    END LOOP;
    CLOSE c_pado;


    commit;


END IMP_PR_COMPA_PAQDO_FINAL2;
/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
                      Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final (Alfa|Final)
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_FINAL(p_valnumesoli NUMBER, p_codclie varchar2) IS


-- Variable usada para eliminar los detalles erroneos
lv_eliminacionDetalles VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorEliminacionDetallesErroneos');
l_clob                 CLOB;
l_clob_temp            CLOB;

CURSOR c_pado IS
select cod_pado from IMP_COMPA_PAQUE_DOCUM
;

r_pado c_pado%ROWTYPE;


CURSOR c_form(p_codpado varchar2) IS
select a.cod_form from IMP_FORMU_PAQUE_DOCU2 a
where a.cod_pado=p_codpado and a.est_form=1
order by a.num_orde_secu
;

r_form c_form%ROWTYPE;

/*CURSOR c_borec IS
select a.xml_cons from IMP_PAQUE_DOCUM_BOREC a where a.cod_cons
not in (select b.cod_clie from imp_paque_docum_final b)
;

r_borec c_borec%ROWTYPE;

*/

    ln_oidperi    NUMBER(10);
--    ln_borec      NUMBER(1):=0;
    lv_fecfact   varchar2(20);

    lv_codpais   varchar2(20);
    --w_filas        NUMBER(12);

ped_serv NUMBER := 0;
ped_expr NUMBER := 0;
lv_indicadorNMP              VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorNMP');
lv_indicadorPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorPedidoServicio');
lv_tagInicioPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');
lv_tagInicioPedidoServicio2   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio2');
lv_tagInicio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicio');
lv_tagFin VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFin');


begin



    OPEN c_pado;
    LOOP
    FETCH c_pado INTO r_pado;
    EXIT WHEN c_pado%NOTFOUND;


            INSERT INTO IMP_PAQUE_DOCUM_FINAL(
            COR_PADO,
            COD_CLIE,
            NUM_PADO,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            p_valnumesoli,
            p_codclie,
            r_pado.cod_pado,
            p_valnumesoli,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;

            if lv_indicadorPedidoServicio='1' then

            select count(1)
            into ped_serv
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and
            (
            exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and ind_oc=1)
            or (
                   exists (select 1
                          from ped_solic_cabec x, ped_tipo_solic_pais y, ped_tipo_solic z, gen_i18n_sicc_comun gen
                          where x.tspa_oid_tipo_soli_pais=y.oid_tipo_soli_pais
                          and y.tsol_oid_tipo_soli=z.oid_tipo_soli
                          and z.oid_tipo_soli=gen.val_oid
                          and gen.attr_enti='PED_TIPO_SOLIC'
                          and gen.val_i18n like '%NMP%'
                          and x.soca_oid_soli_cabe=a.oid_soli_cabe
                          )
                   and lv_indicadorNMP='1'
               )
            )
            ;

            select count(1)
            into ped_expr
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%XPRES%'
            );

            end if;

            if ped_serv=0 and lv_indicadorPedidoServicio='1' then
              if ped_expr=0 then
                DBMS_LOB.writeappend(l_clob, LENGTH(lv_tagInicioPedidoServicio), lv_tagInicioPedidoServicio);
            else
                DBMS_LOB.writeappend(l_clob, LENGTH(lv_tagInicioPedidoServicio2), lv_tagInicioPedidoServicio2);
              end if;
            else
                DBMS_LOB.writeappend(l_clob, LENGTH(lv_tagInicio), lv_tagInicio);
            end if;

            OPEN c_form(r_pado.cod_pado);
            LOOP
            FETCH c_form INTO r_form;
            EXIT WHEN c_form%NOTFOUND;

                  begin
                  if r_form.cod_form='BD' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_bolet_despa x
                     where x.val_nume_soli=p_valnumesoli;

                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);


                  end if;
                  if r_form.cod_form='OC' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_ocs x
                     where x.val_nume_soli=p_valnumesoli;

                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='UN' then
                     select x.xml_unot into l_clob_temp from imp_paque_docum_unot x
                     where x.val_nume_soli=p_valnumesoli;

                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='DF' then
                     select x.xml_deta_fact into l_clob_temp from imp_paque_docum_detal_factu x
                     where x.val_nume_soli=p_valnumesoli;
                    if LENGTH(l_clob_temp)>30000 then
                     DBMS_LOB.writeappend(l_clob, 30000, substr(l_clob_temp,1,30000));
                     DBMS_LOB.writeappend(l_clob, length(substr(l_clob_temp,30001)), substr(l_clob_temp,30001));
                    else
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  end if;
                  if r_form.cod_form='CP' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie;

                     DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='CT' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie;

                     DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                     select x.xml_cuen_corr into l_clob_temp from imp_paque_docum_laser_ctact x
                     where x.cod_clie=p_codclie;
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='BR' then
                    -- ln_borec:=1;
                     select x.xml_cons into l_clob_temp from imp_paque_docum_borec x
                     where x.cod_cons=p_codclie and rownum=1;

                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  exception when others then
                     continue;
                  end;

            END LOOP;
            CLOSE c_form;
            DBMS_LOB.writeappend(l_clob, LENGTH('</pd>'), '</pd>');

    END LOOP;
    CLOSE c_pado;

/*    if ln_borec=1 then

    OPEN c_borec;
    LOOP
    FETCH c_borec INTO r_borec;
    EXIT WHEN c_borec%NOTFOUND;


            INSERT INTO IMP_PAQUE_DOCUM_FINAL(
            COR_PADO,
            COD_CLIE,
            NUM_PADO,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            p_codclie,
            p_codclie,
            p_codclie,
            NULL,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;


                DBMS_LOB.writeappend(l_clob, LENGTH(lv_tagInicio), lv_tagInicio);

                DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), r_borec.xml_cons);

                DBMS_LOB.writeappend(l_clob, LENGTH('</pd>'), '</pd>');

    END LOOP;
    CLOSE c_borec;

    end if;*/

    commit;

END IMP_PR_COMPA_PAQDO_FINAL;

/**************************************************************************
Descripcion         : Genera el archivo de nota de credito
Fecha Creacion      : 10/11/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_nombreArchivo         : Nombre del archivo de texto.
    p_directorio            : Ruta de generacion del archivo.
***************************************************************************/
PROCEDURE IMP_PR_GENER_ARCHI_PAQDOC_FIN(p_codigoPais VARCHAR2,
                                         p_nombreArchivo VARCHAR2,
                                         p_directorio VARCHAR2) IS

lv_tagInicioPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');


CURSOR c_paquete IS

select  x.xml_cons  xml_paqu_docu
from IMP_PAQUE_DOCUM_FINAL x, ped_solic_cabec y, ped_solic_cabec_secue z
where x.val_nume_soli=y.val_nume_soli and y.oid_soli_cabe=z.soca_oid_soli_cabe
order by z.val_secu_ruta_terr asc, x.cod_clie ;

r_paquete c_paquete%ROWTYPE;


CURSOR c_borec IS
select lv_tagInicioPedidoServicio || a.xml_cons || '</pd>' xml_paqu_docu from IMP_PAQUE_DOCUM_BOREC a where a.cod_cons
not in (select b.cod_clie from imp_paque_docum_final b);

r_borec c_borec%ROWTYPE;


-- Variables para la escritura del archivo
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
l_length         NUMBER := 0 ;
x                VARCHAR2(32000);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError              VARCHAR2(500);
l_inicioArchivo             VARCHAR2(500);
l_finArchivo                VARCHAR2(500);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);



BEGIN
     IMP_PR_REEMP_CARAC_ESPEC2;
     IMP_PR_REEMP_CARAC_ESPEC_BR;

    -- Creamos la referencia al archivo
    l_output := UTL_FILE.fopen (p_directorio, p_nombreArchivo, 'wb', 32760);

    -- Creamos las cadenas de inicio y fin del archivo
    l_inicioArchivo := '<?xml version="1.0" encoding="iso-8859-1"?>' || l_cambioLineaRetornoCarro;
    l_inicioArchivo := l_inicioArchivo || '<spoolpd>' || l_cambioLineaRetornoCarro;
    l_finArchivo    := '</spoolpd>';

    -- Escribimos los caracteres de inicio de archivo
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_inicioArchivo), TRUE);

    -- Iteramos sobre el cursor
    OPEN c_paquete;
    LOOP
        FETCH c_paquete INTO r_paquete;
        EXIT WHEN c_paquete%NOTFOUND;
        l_length := DBMS_LOB.GETLENGTH(r_paquete.xml_paqu_docu);
        l_offset := 1;
        l_amt := 4000;

        -- Escribimos los bloques en el archivo
        WHILE (l_offset <= l_length) LOOP
            IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
            dbms_lob.read (r_paquete.xml_paqu_docu, l_amt, l_offset, x);
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
            l_offset := l_offset + l_amt;
            x := null;
        END LOOP;

        -- Escribimos el salto de linea
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_cambioLineaRetornoCarro), TRUE);

    END LOOP;

    -- Cerramos el cursor
    CLOSE c_paquete;



    -- Iteramos sobre el cursor
    OPEN c_borec;
    LOOP
        FETCH c_borec INTO r_borec;
        EXIT WHEN c_borec%NOTFOUND;
        l_length := DBMS_LOB.GETLENGTH(r_borec.xml_paqu_docu);
        l_offset := 1;
        l_amt := 4000;

        -- Escribimos los bloques en el archivo
        WHILE (l_offset <= l_length) LOOP
            IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
            dbms_lob.read (r_borec.xml_paqu_docu, l_amt, l_offset, x);
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
            l_offset := l_offset + l_amt;
            x := null;
        END LOOP;

        -- Escribimos el salto de linea
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_cambioLineaRetornoCarro), TRUE);

    END LOOP;

    -- Cerramos el cursor
    CLOSE c_borec;







    -- Escribimos los caracteres de fin de archivo
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_finArchivo), TRUE);

    -- Cerramos el archivo
    UTL_FILE.fclose (l_output);

EXCEPTION
WHEN UTL_FILE.INTERNAL_ERROR THEN
    l_mensajeError:= 'ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_FILEHANDLE THEN
    l_mensajeError:= 'EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_MODE THEN
    l_mensajeError:= 'MODO INVALIDO AL ABRIR EL ARCHIVO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.WRITE_ERROR THEN
       l_mensajeError:= 'ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
       RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_OPERATION THEN
    l_mensajeError:= 'MODO INVALIDO AL ABRIR EL ARCHIVO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_PATH THEN
    l_mensajeError:= 'ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_ARCHI_FACTU_LASER: '||substr(sqlerrm,1,250));

END IMP_PR_GENER_ARCHI_PAQDOC_FIN;

/**************************************************************************
Descripcion         : Genera el archivo de nota de credito
Fecha Creacion      : 10/11/2011
Autor               : Sergio Buchelli
Parametros Entrada  :
    p_codigoPais            : Codigo del pais.
    p_nombreArchivo         : Nombre del archivo de texto.
    p_directorio            : Ruta de generacion del archivo.
***************************************************************************/
PROCEDURE IMP_PR_GENER_ARCHI_PAQDOC_FIN2(p_codigoPais VARCHAR2,
                                         p_nombreArchivo VARCHAR2,
                                         p_directorio VARCHAR2) IS

lv_tagInicioPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');


CURSOR c_archivo IS

select  xx.cod_pado, xx.nom_archi, xx.nom_dire
from IMP_COMPA_PAQUE_DOCUM xx
order by xx.cod_pado ;

r_archivo c_archivo%ROWTYPE;



CURSOR c_paquete(p_pado VARCHAR) IS

select  x.xml_cons  xml_paqu_docu
from IMP_PAQUE_DOCUM_FINAL x, ped_solic_cabec y, ped_solic_cabec_secue z
where x.val_nume_soli=y.val_nume_soli and y.oid_soli_cabe=z.soca_oid_soli_cabe
and x.num_pado=p_pado
order by z.val_secu_ruta_terr asc, x.cod_clie ;

r_paquete c_paquete%ROWTYPE;


CURSOR c_borec IS
select lv_tagInicioPedidoServicio || a.xml_cons || '</pd>' xml_paqu_docu from IMP_PAQUE_DOCUM_BOREC a where a.cod_cons
not in (select b.cod_clie from imp_paque_docum_final b);

r_borec c_borec%ROWTYPE;


-- Variables para la escritura del archivo
l_output         UTL_FILE.file_type;
l_amt            NUMBER DEFAULT 4000;
l_offset         NUMBER DEFAULT 1;
l_length         NUMBER := 0 ;
x                VARCHAR2(32000);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError              VARCHAR2(500);
l_inicioArchivo             VARCHAR2(500);
l_finArchivo                VARCHAR2(500);
l_cambioLineaRetornoCarro   VARCHAR2(2) := CHR(13) || CHR(10);

lv_borraPaqdocPrem VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indElimPDServPrem'),'N');

lv_borraPaqdocPrem2 VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indElimPDServPrem2'),'N');


lv_tipoSoliConsFlex VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tipoSoliConsFlex');

lv_reemplazoOCS VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorReemplazoOCS');

BEGIN
     IMP_PR_REEMP_CARAC_ESPEC2;
     IMP_PR_REEMP_CARAC_ESPEC_BR;


    IF lv_reemplazoOCS = 'S' THEN
        IMP_PR_REEMP_OCS;
    END IF;


    OPEN c_archivo;
    LOOP
        FETCH c_archivo INTO r_archivo;
        EXIT WHEN c_archivo%NOTFOUND;


     if lv_borraPaqdocPrem='S' and r_archivo.cod_pado='1' then
       delete from imp_paque_docum_final x1 where x1.num_pado=1
       and cod_clie in
        (
        select cod_cons from imp_paque_docum_detal_factu
           where instr(xml_deta_fact,'<catalogo>')=0
            and cod_cons = cod_clie
       )
       ;
     end if;


     if lv_borraPaqdocPrem2='S' and r_archivo.cod_pado='1' then
       delete from imp_paque_docum_final x1 where x1.num_pado=1
       and exists (select 1 from ped_solic_cabec where modu_oid_modu=13 and soca_oid_soli_cabe=(select oid_soli_cabe from ped_solic_cabec where val_nume_soli=x1.val_nume_soli))
       and not exists (select 1 from ped_solic_cabec where modu_oid_modu<>13 and soca_oid_soli_cabe=(select oid_soli_cabe from ped_solic_cabec where val_nume_soli=x1.val_nume_soli))
       ;
     end if;

     if lv_tipoSoliConsFlex is not null and r_archivo.cod_pado='1' then
            delete from imp_paque_docum_final a where a.cod_clie in
            (
              select cod_clie from
              (
                select cod_clie, count(1)
                from imp_paque_docum_final
                where num_pado=1
                group by cod_clie
                having count(1)>1
              )
            )
            and a.num_pado=1
            and exists (select 1 from ped_solic_cabec
                        where tspa_oid_tipo_soli_pais=lv_tipoSoliConsFlex
                        and val_nume_soli=a.val_nume_soli
                        );
     end if;
    -- Creamos la referencia al archivo
    l_output := UTL_FILE.fopen (r_archivo.nom_dire, r_archivo.nom_archi, 'wb', 32760);

    -- Creamos las cadenas de inicio y fin del archivo
    l_inicioArchivo := '<?xml version="1.0" encoding="iso-8859-1"?>' || l_cambioLineaRetornoCarro;
    l_inicioArchivo := l_inicioArchivo || '<spoolpd>' || l_cambioLineaRetornoCarro;
    l_finArchivo    := '</spoolpd>';

    -- Escribimos los caracteres de inicio de archivo
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_inicioArchivo), TRUE);

    -- Iteramos sobre el cursor



    OPEN c_paquete(r_archivo.cod_pado);
    LOOP
        FETCH c_paquete INTO r_paquete;
        EXIT WHEN c_paquete%NOTFOUND;
        l_length := DBMS_LOB.GETLENGTH(r_paquete.xml_paqu_docu);
        l_offset := 1;
        l_amt := 4000;

        -- Escribimos los bloques en el archivo
        WHILE (l_offset <= l_length) LOOP
            IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
            dbms_lob.read (r_paquete.xml_paqu_docu, l_amt, l_offset, x);
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
            l_offset := l_offset + l_amt;
            x := null;
        END LOOP;

        -- Escribimos el salto de linea
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_cambioLineaRetornoCarro), TRUE);

    END LOOP;

    -- Cerramos el cursor
    CLOSE c_paquete;



    if r_archivo.cod_pado='2' then

    -- Iteramos sobre el cursor
    OPEN c_borec;
    LOOP
        FETCH c_borec INTO r_borec;
        EXIT WHEN c_borec%NOTFOUND;
        l_length := DBMS_LOB.GETLENGTH(r_borec.xml_paqu_docu);
        l_offset := 1;
        l_amt := 4000;

        -- Escribimos los bloques en el archivo
        WHILE (l_offset <= l_length) LOOP
            IF (l_amt > (l_length - l_offset)) THEN l_amt := l_length - l_offset + 1; END IF;
            dbms_lob.read (r_borec.xml_paqu_docu, l_amt, l_offset, x);
            UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(x), TRUE);
            l_offset := l_offset + l_amt;
            x := null;
        END LOOP;

        -- Escribimos el salto de linea
        UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_cambioLineaRetornoCarro), TRUE);

    END LOOP;

    -- Cerramos el cursor
    CLOSE c_borec;

    end if;


    -- Escribimos los caracteres de fin de archivo
    UTL_FILE.PUT_raw(l_output, utl_raw.cast_to_raw(l_finArchivo), TRUE);

    -- Cerramos el archivo
    UTL_FILE.fclose (l_output);


    END LOOP;




    -- Cerramos el cursor
    CLOSE c_archivo;




EXCEPTION
WHEN UTL_FILE.INTERNAL_ERROR THEN
    l_mensajeError:= 'ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_FILEHANDLE THEN
    l_mensajeError:= 'EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_MODE THEN
    l_mensajeError:= 'MODO INVALIDO AL ABRIR EL ARCHIVO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.WRITE_ERROR THEN
       l_mensajeError:= 'ERROR AL ESCRIBIR EN EL ARCHIVO O NO HAY ESPACIO EN DISCO';
       RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_OPERATION THEN
    l_mensajeError:= 'MODO INVALIDO AL ABRIR EL ARCHIVO';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN UTL_FILE.INVALID_PATH THEN
    l_mensajeError:= 'ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
    RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

WHEN OTHERS THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_ARCHI_FACTU_LASER: '||substr(sqlerrm,1,250));

END IMP_PR_GENER_ARCHI_PAQDOC_FIN2;

/**************************************************************************
Descripcion         : Actualiza el indicador de impresion de las notas de
                      credito que se hayan compaginado en el paquete documentario.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_ACTUA_NOTA_CREDI_IMPRE IS

l_indicadorGenerarNtaCtoLaser VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarNotaCreditoLaser');

BEGIN
    IF l_indicadorGenerarNtaCtoLaser = 'S' THEN
        UPDATE IMP_NOTA_CREDI_LASER X
        SET X.IND_IMPR = 'S',
            X.FEC_IMPR = SYSDATE
        WHERE X.IND_IMPR = 'N'
        AND EXISTS (
            SELECT NULL
            FROM IMP_PAQUE_DOCUM Y
            WHERE X.COD_CLIE = Y.COD_CLIE
        );
    END IF;
END IMP_PR_ACTUA_NOTA_CREDI_IMPRE;

/**************************************************************************
Descripcion         : Actualiza el indicador de impresion de las notas de
                      debito que se hayan compaginado en el paquete documentario.
Fecha Creacion      : 05/11/2008
Autor               : Carlos Hurtado Ramirez - cahurtado@belcorp.biz
Version             : Final
Cambios Importantes : Ninguno
***************************************************************************/
PROCEDURE IMP_PR_ACTUA_NOTA_DEBIT_IMPRE IS

l_indicadorGenerarNtaDtoLaser VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorGenerarNotaDebitoLaser');

BEGIN
    IF l_indicadorGenerarNtaDtoLaser = 'S' THEN
        UPDATE IMP_NOTA_DEBIT_LASER X
        SET X.IND_IMPR = 'S',
            X.FEC_IMPR = SYSDATE
        WHERE X.IND_IMPR = 'N'
        AND EXISTS (
            SELECT NULL
            FROM IMP_PAQUE_DOCUM Y
            WHERE X.COD_CLIE = Y.COD_CLIE
        );
    END IF;
END IMP_PR_ACTUA_NOTA_DEBIT_IMPRE;

/***************************************************************************
Descripcion      : Funcion usada para obtener el valor del saldo actual de
                             una consultora en base al parametro de numero de filas
                             ingresado
Fecha Creacion   : 20/01/2009
Autor            : Dennys Oliva Iriarte
***************************************************************************/
FUNCTION IMP_FN_CALCU_SALDO_INICI (psOidCliente NUMBER,
                                   psParametro NUMBER,
                                   psFechaLimite VARCHAR2)
RETURN NUMBER IS
   lsSaldoInicial NUMBER := 0;
BEGIN
    select nvl(sum(IMP_MOVI),0)
      INTO lsSaldoInicial
        from (SELECT FEC_EMIS,
                     CAM_REFE,
                     TIP_MOVI,
                     NUM_DOCU,
                     FEC_VENC,
                     FEC_PAGO,
                     decode(IMP_CARG,' ',0,to_number(IMP_CARG,'9999999990.00')),
                     decode(IMP_ABON,' ',0,to_number(IMP_ABON,'9999999990.00')),
                     to_number(IMP_MOVI,'9999999990.00') IMP_MOVI,
                     RNUM
                FROM (SELECT FEC_EMIS,
                             CAM_REFE,
                             TIP_MOVI,
                             NUM_DOCU,
                             FEC_VENC,
                             FEC_PAGO,
                             decode(IMP_CARG, ' ', ' ', to_char(IMP_CARG, '9999999990.00')) IMP_CARG,
                             decode(IMP_ABON, ' ', ' ', to_char(IMP_ABON, '9999999990.00')) IMP_ABON,
                             decode(IMP_MOVI, ' ', ' ', to_char(IMP_MOVI, '9999999990.00')) IMP_MOVI,
                             ROWNUM RNUM
                        FROM (SELECT FEC_EMIS,
                                     nvl(to_char(CAM_REFE), ' ') CAM_REFE,
                                     TIP_MOVI,
                                     NUM_DOCU,
                                     nvl(to_char(FEC_VENC, 'dd/MM/yyyy'), ' ') FEC_VENC,
                                     nvl(to_char(FEC_PAGO, 'dd/MM/yyyy'), ' ') FEC_PAGO,
                                     nvl(to_char(IMP_CARG), ' ') IMP_CARG,
                                     nvl(to_char(IMP_ABON), ' ') IMP_ABON,
                                     DECODE(IMP_CARG, NULL, IMP_ABON, -1 * IMP_CARG) IMP_MOVI
                                FROM (SELECT MCC.FEC_DOCU FEC_EMIS,
                                             SPC.COD_PERI CAM_REFE,
                                             CASE
                                               WHEN MCC.TCAB_OID_TCAB_CREA = 2001 THEN
                                                CASE
                                               WHEN EXISTS (SELECT OID_SOLI_CABE
                                                       FROM PED_SOLIC_CABEC     P,
                                                            PED_TIPO_SOLIC_PAIS TP,
                                                            PED_TIPO_SOLIC      T
                                                      WHERE P.SOCA_OID_SOLI_CABE =
                                                            MCC.SOCA_OID_SOLI_CABE
                                                        AND P.TSPA_OID_TIPO_SOLI_PAIS =
                                                            TP.OID_TIPO_SOLI_PAIS
                                                        AND TP.TSOL_OID_TIPO_SOLI =
                                                            T.OID_TIPO_SOLI
                                                        AND P.IND_OC = 1
                                                        AND T.IND_SOLI_NEGA = 0) THEN
                                                'Pedido'
                                               ELSE
                                                CASE
                                               WHEN MCC.IMP_MOVI > 0 THEN
                                                'Atencion de Servicio'
                                               ELSE
                                                NVL((SELECT 'C' || PERIOCORPO.COD_PERI || ' ' ||
                                                           OPERA.VAL_DESC_LARG || ' NRO.' ||
                                                           CABECRECLA.NUM_RECL AS DESCRIP
                                                      FROM PED_SOLIC_CABEC CONS,
                                                           PED_SOLIC_CABEC SOLICRECLA,
                                                           PED_SOLIC_CABEC SOLICORIGEN,
                                                           REC_SOLIC_OPERA SOLICOPERA,
                                                           REC_OPERA_RECLA OPERARECLA,
                                                           REC_CABEC_RECLA CABECRECLA,
                                                           REC_TIPOS_OPERA TIPOSOPERA,
                                                           REC_OPERA       OPERA,
                                                           CRA_PERIO       PERIO,
                                                           SEG_PERIO_CORPO PERIOCORPO
                                                     WHERE CONS.OID_SOLI_CABE =
                                                           MCC.SOCA_OID_SOLI_CABE
                                                       AND CONS.OID_SOLI_CABE =
                                                           SOLICRECLA.SOCA_OID_SOLI_CABE
                                                       AND SOLICRECLA.SOCA_OID_DOCU_REFE IS NOT NULL
                                                       AND SOLICRECLA.OID_SOLI_CABE =
                                                           SOLICOPERA.SOCA_OID_SOLI_CABE
                                                       AND SOLICOPERA.OPRE_OID_OPER_RECL =
                                                           OPERARECLA.OID_OPER_RECL
                                                       AND OPERARECLA.CARE_OID_CABE_RECL =
                                                           CABECRECLA.OID_CABE_RECL
                                                       AND OPERARECLA.TIOP_OID_TIPO_OPER =
                                                           TIPOSOPERA.OID_TIPO_OPER
                                                       AND TIPOSOPERA.ROPE_OID_OPER =
                                                           OPERA.OID_OPER
                                                       AND SOLICRECLA.SOCA_OID_DOCU_REFE =
                                                           SOLICORIGEN.OID_SOLI_CABE
                                                       AND SOLICORIGEN.PERD_OID_PERI =
                                                           PERIO.OID_PERI
                                                       AND PERIO.PERI_OID_PERI =
                                                           PERIOCORPO.OID_PERI
                                                       AND ROWNUM = 1),
                                                    'CDR')
                                             END END ELSE GEN.VAL_I18N END TIP_MOVI,
                                             MCC.SOCA_OID_SOLI_CABE SOCA_OID_SOLI_CABE,
                                             MCC.NUM_IDEN_CUOT NUM_DOCU,
                                             MCC.FEC_VENC FEC_VENC,
                                             NULL FEC_PAGO,
                                             CASE
                                               WHEN (MCC.IMP_MOVI > 0) THEN
                                                MCC.IMP_MOVI
                                               ELSE
                                                NULL
                                             END IMP_CARG,
                                             CASE
                                               WHEN (MCC.IMP_MOVI > 0) THEN
                                                NULL
                                               ELSE
                                                ABS(MCC.IMP_MOVI)
                                             END IMP_ABON
                                        FROM CCC_MOVIM_CUENT_CORRI MCC,
                                             CCC_SUBPR             CS,
                                             CCC_TIPO_ABONO_SUBPR  TAS,
                                             CCC_TIPO_CARGO_ABONO  TCA,
                                             GEN_I18N_SICC_PAIS    GEN,
                                             CRA_PERIO             CP,
                                             SEG_PERIO_CORPO       SPC
                                       WHERE MCC.SUBP_OID_SUBP_CREA = CS.OID_SUBP
                                         AND CS.OID_SUBP = TAS.SUBP_OID_SUBP
                                         AND TAS.TCAB_OID_TCAB = TCA.OID_TIPO_CARG_ABON
                                         AND GEN.ATTR_ENTI LIKE 'CCC_TIPO_CARGO_ABONO'
                                         AND GEN.VAL_OID = TCA.OID_TIPO_CARG_ABON
                                         AND MCC.PERD_OID_PERI = CP.OID_PERI
                                         AND CP.PERI_OID_PERI = SPC.OID_PERI
                                         AND MCC.CLIE_OID_CLIE = psOidCliente-- parametro
                                         --AND ROWNUM = 1
                                      UNION ALL
                                      SELECT MB.FEC_PROC      FEC_EMIS,
                                             NULL             OID_PERI,
                                             CCB.DES_CC       TIP_MOVI,
                                             MB.OID_MOVI_BANC SOCA_OID_SOLI_CABE,
                                             MB.NUM_LOTE      NUM_DOCU,
                                             NULL             FEC_VENC,
                                             MB.FEC_PAGO      FEC_PAGO,
                                             NULL             IMP_CARG,
                                             MB.IMP_PAGO      IMP_ABON
                                        FROM CCC_MOVIM_BANCA MB, CCC_CUENT_CORRI_BANCA CCB
                                       WHERE MB.CCBA_OID_CC_BANC = CCB.OID_CUEN_CORR_BANC
                                         AND MB.COD_IDEN_PROC = 'P'
                                         AND MB.CLIE_OID_CLIE = psOidCliente) -- parametro
                               ORDER BY 1 DESC) AX
                         WHERE
                             FEC_EMIS <= TO_DATE(psFechaLimite,'dd/MM/YYYY') -- parametro
                       ))

             where rnum > psParametro; --parametro

       lsSaldoInicial:= lsSaldoInicial*(-1);

    RETURN lsSaldoInicial;
END IMP_FN_CALCU_SALDO_INICI;

/***************************************************************************
Descripcion       : Procedimiento que actualiza el numero de documento legal
                    a partir del numero de documento interno, para un determinado
                    tipo de documento y una fecha de facturacion determinada.
                    Asimismo actualiza el numero de documento legal en el
                    registro unico de venta.
Fecha Creacion    : 29/01/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_ACTUA_NUMER_DOCUM_LEGAL (pnOidPeriodo NUMBER,
                                          psFechaFacturacion VARCHAR2,
                                          psCodigoTipoDocumento  VARCHAR2) IS

CURSOR cUpdateNumeros IS
SELECT DCC.OID_CABE,
       RUV.OID_REGI,
       DCC.NUM_DOCU_CONT_INTE
FROM FAC_DOCUM_CONTA_CABEC DCC,
     FAC_TIPO_DOCUM FTD,
     FAC_REGIS_UNICO_VENTA RUV,
     PED_SOLIC_CABEC CON
WHERE CON.OID_SOLI_CABE = DCC.SOCA_OID_SOLI_CABE
  AND DCC.OID_CABE = RUV.DCCA_OID_CABE
  AND DCC.TIDO_OID_TIPO_DOCU = FTD.OID_TIPO_DOCU
  AND FTD.COD_TIPO_DOCU = psCodigoTipoDocumento
  AND DCC.NUM_DOCU_LEGA IS NULL
  AND DCC.NUM_DOCU_CONT_INTE IS NOT NULL
  AND CON.PERD_OID_PERI = pnOidPeriodo
  AND CON.FEC_FACT = TO_DATE(psFechaFacturacion, 'DD/MM/YYYY');

TYPE t_oidCabe         IS TABLE OF FAC_DOCUM_CONTA_CABEC.OID_CABE%TYPE;
TYPE t_oidRegi         IS TABLE OF FAC_REGIS_UNICO_VENTA.OID_REGI%TYPE;
TYPE t_numDocuContInte IS TABLE OF FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE;

v_oidCabe          t_oidCabe;
v_oidRegi          t_oidRegi;
v_numDocuContInte  t_numDocuContInte;

n_rows NUMBER:=1000;

BEGIN

    -- Abrimos el cursor
    OPEN cUpdateNumeros;
    LOOP
        FETCH cUpdateNumeros BULK COLLECT INTO
              v_oidCabe,
              v_oidRegi,
              v_numDocuContInte
              LIMIT n_rows;
        EXIT WHEN v_oidCabe.COUNT = 0;

        FORALL i IN 1..v_oidCabe.COUNT
        UPDATE FAC_DOCUM_CONTA_CABEC X
        SET    X.NUM_DOCU_LEGA = v_numDocuContInte(i)
        WHERE  X.OID_CABE = v_oidCabe(i);

        FORALL j IN 1..v_oidRegi.COUNT
        UPDATE FAC_REGIS_UNICO_VENTA Y
        SET    Y.VAL_NUME_DOCU_LEGA = v_numDocuContInte(j)
        WHERE  Y.OID_REGI = v_oidRegi(j);

    END LOOP;
    CLOSE cUpdateNumeros;

END IMP_PR_ACTUA_NUMER_DOCUM_LEGAL;

/***************************************************************************
Descripcion       : Funcion que obtiene el saldo a favor que tenia una
                    consultora al momento de pasar su pedido.
Fecha Creacion    : 03/02/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
FUNCTION IMP_FN_CALCU_SALDO_FAVOR(pnOidConsolidado NUMBER) RETURN NUMBER IS

lnSaldoFavor NUMBER := 0;

BEGIN
    SELECT NVL(SUM(IMP_PAGA), 0)
    INTO lnSaldoFavor
    FROM CCC_MOVIM_CUENT_CORRI CCC
    WHERE CCC.SOCA_OID_SOLI_CABE = pnOidConsolidado;

    RETURN lnSaldoFavor;

END IMP_FN_CALCU_SALDO_FAVOR;


/***************************************************************************
Descripcion       : Procedimiento que actualiza el numero de documento legal
                    a partir del numero de documento interno, para las notas
                    de credito laser generadas.
                    Asimismo actualiza el numero de documento legal en el
                    registro unico de venta.
Fecha Creacion    : 02/03/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE  IMP_PR_ACTUA_NUMER_DOCUM_NOTCR IS

CURSOR cUpdateNumeros IS
SELECT DCC.OID_CABE,
       RUV.OID_REGI,
       DCC.NUM_DOCU_CONT_INTE
FROM FAC_DOCUM_CONTA_CABEC DCC,
     FAC_REGIS_UNICO_VENTA RUV,
     IMP_NOTA_CREDI_LASER NCL
WHERE DCC.OID_CABE = RUV.DCCA_OID_CABE
  AND DCC.NUM_DOCU_LEGA IS NULL
  AND DCC.NUM_DOCU_CONT_INTE IS NOT NULL
  AND NCL.OID_CABE = DCC.OID_CABE;

TYPE t_oidCabe         IS TABLE OF FAC_DOCUM_CONTA_CABEC.OID_CABE%TYPE;
TYPE t_oidRegi         IS TABLE OF FAC_REGIS_UNICO_VENTA.OID_REGI%TYPE;
TYPE t_numDocuContInte IS TABLE OF FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE;

v_oidCabe          t_oidCabe;
v_oidRegi          t_oidRegi;
v_numDocuContInte  t_numDocuContInte;

n_rows NUMBER:=1000;

BEGIN

    -- Abrimos el cursor
    OPEN cUpdateNumeros;
    LOOP
        FETCH cUpdateNumeros BULK COLLECT INTO
              v_oidCabe,
              v_oidRegi,
              v_numDocuContInte
              LIMIT n_rows;
        EXIT WHEN v_oidCabe.COUNT = 0;

        FORALL i IN 1..v_oidCabe.COUNT
        UPDATE FAC_DOCUM_CONTA_CABEC X
        SET    X.NUM_DOCU_LEGA = v_numDocuContInte(i)
        WHERE  X.OID_CABE = v_oidCabe(i);

        FORALL j IN 1..v_oidRegi.COUNT
        UPDATE FAC_REGIS_UNICO_VENTA Y
        SET    Y.VAL_NUME_DOCU_LEGA = v_numDocuContInte(j)
        WHERE  Y.OID_REGI = v_oidRegi(j);

    END LOOP;
    CLOSE cUpdateNumeros;

END IMP_PR_ACTUA_NUMER_DOCUM_NOTCR;

/***************************************************************************
Descripcion       : Procedimiento que actualiza el numero de documento legal
                    a partir del numero de documento interno, para las notas
                    de debito laser generadas.
                    Asimismo actualiza el numero de documento legal en el
                    registro unico de venta.
Fecha Creacion    : 02/03/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE  IMP_PR_ACTUA_NUMER_DOCUM_NOTDE IS

CURSOR cUpdateNumeros IS
SELECT DCC.OID_CABE,
       RUV.OID_REGI,
       DCC.NUM_DOCU_CONT_INTE
FROM FAC_DOCUM_CONTA_CABEC DCC,
     FAC_REGIS_UNICO_VENTA RUV,
     IMP_NOTA_DEBIT_LASER NDL
WHERE DCC.OID_CABE = RUV.DCCA_OID_CABE
  AND DCC.NUM_DOCU_LEGA IS NULL
  AND DCC.NUM_DOCU_CONT_INTE IS NOT NULL
  AND NDL.OID_CABE = DCC.OID_CABE;

TYPE t_oidCabe         IS TABLE OF FAC_DOCUM_CONTA_CABEC.OID_CABE%TYPE;
TYPE t_oidRegi         IS TABLE OF FAC_REGIS_UNICO_VENTA.OID_REGI%TYPE;
TYPE t_numDocuContInte IS TABLE OF FAC_DOCUM_CONTA_CABEC.NUM_DOCU_CONT_INTE%TYPE;

v_oidCabe          t_oidCabe;
v_oidRegi          t_oidRegi;
v_numDocuContInte  t_numDocuContInte;

n_rows NUMBER:=1000;

BEGIN

    -- Abrimos el cursor
    OPEN cUpdateNumeros;
    LOOP
        FETCH cUpdateNumeros BULK COLLECT INTO
              v_oidCabe,
              v_oidRegi,
              v_numDocuContInte
              LIMIT n_rows;
        EXIT WHEN v_oidCabe.COUNT = 0;

        FORALL i IN 1..v_oidCabe.COUNT
        UPDATE FAC_DOCUM_CONTA_CABEC X
        SET    X.NUM_DOCU_LEGA = v_numDocuContInte(i)
        WHERE  X.OID_CABE = v_oidCabe(i);

        FORALL j IN 1..v_oidRegi.COUNT
        UPDATE FAC_REGIS_UNICO_VENTA Y
        SET    Y.VAL_NUME_DOCU_LEGA = v_numDocuContInte(j)
        WHERE  Y.OID_REGI = v_oidRegi(j);

    END LOOP;
    CLOSE cUpdateNumeros;

END IMP_PR_ACTUA_NUMER_DOCUM_NOTDE;

/***************************************************************************
Descripcion       : Procedimiento que carga el XML de la hoja de picado.
Fecha Creacion    : 11/05/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_CARGA_XML_HOJA_PICAD(p_nombreArchivo IN VARCHAR2, p_directorio IN VARCHAR2) IS

-- Variables usadas para la carga del archivo
l_FileLocator           BFILE;
l_CLOBSrc               CLOB := EMPTY_CLOB();
l_CLOBDest              CLOB;
l_CLOBLength            NUMBER;

-- Contador usado para el correlativo
l_Correlative NUMBER := 1;

-- Contadores usados para determinar el offset
l_OffsetInicial NUMBER := 1;
l_OffsetFinal   NUMBER := 1;
l_Offset        NUMBER := 1;

-- Valores usados para la carga del CLOB desde el archivo
src_offset  NUMBER := 1;
dst_offset  NUMBER := 1;
charset_id  NUMBER := DBMS_LOB.default_csid;
lang_ctx    NUMBER := DBMS_LOB.default_lang_ctx;
warning     NUMBER;
l_codCliente VARCHAR2(20);
l_numeroSolicitud NUMBER(10);

-- Variable a contener el mensaje de la excepcion a lanzar
l_mensajeError VARCHAR2(500);

BEGIN
  -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SICC
  EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_HOJA_PICAD';

  -- Creamos el objeto DIRECTORY para utilizarlo en las funciones
  EXECUTE IMMEDIATE 'CREATE OR REPLACE DIRECTORY XML_SICC_DIR AS ''' || p_directorio || '''';

  -- Inicializamos el BFILE locator para lectura
  -- Inicializamos el BFILE locator para lectura
  l_FileLocator := BFILENAME('XML_SICC_DIR', p_nombreArchivo);
  DBMS_LOB.FILEOPEN(l_FileLocator, DBMS_LOB.FILE_READONLY);

  -- Usamos un CLOB temporal para cargar la informacion del archivo
  DBMS_LOB.CREATETEMPORARY(l_CLOBSrc, TRUE);

  -- Cargamos todo el archivo en un character LOB Temporal.
  DBMS_LOB.LOADCLOBFROMFILE(l_CLOBSrc,
                               l_FileLocator,
                               DBMS_LOB.GETLENGTH(l_FileLocator),
                               src_offset,
                               dst_offset,
                               charset_id,
                               lang_ctx,
                               warning);

  -- Cerramos el archivo
  DBMS_LOB.FILECLOSE(l_FileLocator);

  -- Obtenemos el tama?o del CLOB cargado
  l_CLOBLength := DBMS_LOB.GETLENGTH(l_CLOBSrc);

    -- LOOP
    WHILE l_CLOBLength > 0 AND l_OffsetFinal != 0
    LOOP

        -- Una vez que tenemos todo el archivo en una CLOB temporal
        -- podemos usar las funciones del paquete DBMS_LOB para segmentarlo
        l_OffsetInicial := DBMS_LOB.INSTR(l_CLOBSrc,
                                          '<picking>',
                                          l_OffsetInicial);

        l_OffsetFinal := DBMS_LOB.INSTR(l_CLOBSrc,
                                        '</picking>',
                                        l_OffsetInicial);

        IF l_OffsetInicial != 0 AND l_OffsetFinal != 0 THEN

            INSERT INTO IMP_PAQUE_DOCUM_HOJA_PICAD (COR_PADO_HOPI, XML_PADO_HOPI)
            VALUES(l_Correlative, EMPTY_CLOB())
            RETURNING XML_PADO_HOPI INTO l_CLOBDest;

            -- Copiamos la porcion del CLOB original al parcial
            DBMS_LOB.COPY(l_CLOBDest,
                            l_CLOBSrc,
                            l_OffsetFinal - l_OffsetInicial + 10,
                            1,
                            l_OffsetInicial);

            l_OffsetInicial := l_OffsetFinal + 9;

            -- Obtenemos el codigo del cliente del XML
            l_codCliente := DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</codconsultora>') - (INSTR(l_CLOBDest, '<codconsultora>') + 15), INSTR(l_CLOBDest, '<codconsultora>') + 15);
            l_numeroSolicitud := TO_NUMBER(DBMS_LOB.SUBSTR(l_CLOBDest, INSTR(l_CLOBDest, '</numpedido>') - (INSTR(l_CLOBDest, '<numpedido>') + 11), INSTR(l_CLOBDest, '<numpedido>') + 11));

            -- Actualizamos el valor del codigo del cliente
            UPDATE IMP_PAQUE_DOCUM_HOJA_PICAD A
            SET A.COD_CLIE = l_codCliente,
                A.VAL_NUME_SOLI = l_numeroSolicitud
            WHERE A.COR_PADO_HOPI = l_Correlative;

        END IF;

        -- Incrementamos el valor del correlativo
        l_Correlative := l_Correlative + 1;

    END LOOP;-- END LOOP

    -- Liberamos los rescursos usados por el CLOB temporal
    DBMS_LOB.FREETEMPORARY(l_CLOBSrc);

    COMMIT;

    EXCEPTION
    WHEN UTL_FILE.INTERNAL_ERROR THEN
        l_mensajeError:='ERROR INTERNO DEL MANEJADOR DE ARCHIVOS';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILEHANDLE THEN
        l_mensajeError:='EL ARCHIVO NO ESTA ABIERTO O NO ES VALIDO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_MODE THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO ' || p_nombreArchivo;
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_FILENAME THEN
        l_mensajeError:='EL ARCHIVO ' || p_nombreArchivo || ' NO EXISTE EN LA RUTA ESPECIFICADA.';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_OPERATION THEN
        l_mensajeError:='MODO INVALIDO AL ABRIR EL ARCHIVO';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN UTL_FILE.INVALID_PATH THEN
        l_mensajeError:='ERROR EN LA RUTA DEL ARCHIVO, ARCHIVO NO ES ACCESIBLE';
        RAISE_APPLICATION_ERROR(-20123, l_mensajeError);

    WHEN OTHERS THEN
            -- Close the cursor and file, and reraise.
            DBMS_LOB.FILECLOSE(l_FileLocator);
            DBMS_LOB.FREETEMPORARY(l_CLOBSrc);
         RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_CARGA_XML_HOJA_PICAD: '||substr(sqlerrm,1,250));

END IMP_PR_CARGA_XML_HOJA_PICAD;

/***************************************************************************
Descripcion       : Procedimiento que limpia las tablas en las cuales se cargan
                    los archivos del paquete documentario generado por SiCC, el
                    archivo de cupones y el de la hoja de picado. Es proceso se
                    ejecuta como paso inicial de la compaginacion.
Fecha Creacion    : 25/05/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_ELIMI_PAQUE_DOCUM IS
BEGIN
    -- Truncamos las 3 tablas que se cargan a partir de archivos generados
    -- por procesos de SiCC y/o SSiCC (paquete documentario, cupones y hoja de picado).
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_SICC';
   -- EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_HOJA_PICAD';

END IMP_PR_ELIMI_PAQUE_DOCUM;

/***************************************************************************
Descripcion       : Procedimiento que reemplaza la direccion de la boleta de
                    despacho para aquellas consultoras que tienen una direccion
                    direccion de entrega configurada.
Fecha Creacion    : 08/06/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_DIREC_BOLET_DESPA IS

CURSOR c_paquete IS
SELECT MC.COD_CLIE,
       IPD.COR_PADO,
       IPD.NUM_PADO,
       INSTR(IPD.XML_CONS, '<frmbd>') POS_INIC,
       '<dir1>'
       || STV.DES_ABRV_TIPO_VIA
       || ' '
       || MCD.VAL_NOMB_VIA
       || ' '
       || MCD.NUM_PPAL
       || ' '
       || MCD.VAL_OBSE
       || '</dir1>' NEW_DIR1,
       '<dir2>'
       || (SELECT DES_GEOG
             FROM ZON_VALOR_ESTRU_GEOPO DIR
            WHERE ORDE_1 = (SELECT SUBSTR (COD_UNID_GEOG, 1, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_2 = (SELECT SUBSTR (COD_UNID_GEOG, 7, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_3 = (SELECT SUBSTR (COD_UNID_GEOG, 13, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_4 = (SELECT SUBSTR (COD_UNID_GEOG, 19, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE))
       || '/'
       || (SELECT DES_GEOG
             FROM ZON_VALOR_ESTRU_GEOPO DIR
            WHERE ORDE_1 = (SELECT SUBSTR (COD_UNID_GEOG, 1, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_2 = (SELECT SUBSTR (COD_UNID_GEOG, 7, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_3 = (SELECT SUBSTR (COD_UNID_GEOG, 13, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_4 IS NULL)
       || '/'
       || (SELECT DES_GEOG
             FROM ZON_VALOR_ESTRU_GEOPO DIR
            WHERE ORDE_1 = (SELECT SUBSTR (COD_UNID_GEOG, 1, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_2 = (SELECT SUBSTR (COD_UNID_GEOG, 7, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_3 IS NULL
              AND ORDE_4 IS NULL)
       || '/'
       || (SELECT DES_GEOG
             FROM ZON_VALOR_ESTRU_GEOPO DIR
            WHERE ORDE_1 = (SELECT SUBSTR (COD_UNID_GEOG, 1, 6)
                              FROM MAE_CLIEN_DIREC
                             WHERE OID_CLIE_DIRE = MCD.OID_CLIE_DIRE)
              AND ORDE_2 IS NULL
              AND ORDE_3 IS NULL
              AND ORDE_4 IS NULL)
       || decode(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorTelefono'),'S',IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','textoTelefono') || GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE, 'TF'),'') || '/'
       || decode(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorTelefono'),'S',GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE, 'TM'),'') ||
       '</dir2>' NEW_DIR2,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<dir1>', INSTR(IPD.XML_CONS, '<frmbd>')), INSTR(IPD.XML_CONS, '</dir1>', INSTR(IPD.XML_CONS, '<frmbd>')) + DBMS_LOB.GETLENGTH('</dir1>') - (INSTR(IPD.XML_CONS, '<dir1>', INSTR(IPD.XML_CONS, '<frmbd>'))))) OLD_DIR1,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<dir2>', INSTR(IPD.XML_CONS, '<frmbd>')), INSTR(IPD.XML_CONS, '</dir2>', INSTR(IPD.XML_CONS, '<frmbd>')) + DBMS_LOB.GETLENGTH('</dir2>') - (INSTR(IPD.XML_CONS, '<dir2>', INSTR(IPD.XML_CONS, '<frmbd>'))))) OLD_DIR2
FROM MAE_CLIEN MC,
     MAE_CLIEN_DIREC MCD,
     MAE_TIPO_DIREC MTD,
     SEG_TIPO_VIA STV,
     IMP_PAQUE_DOCUM IPD
WHERE MC.OID_CLIE = MCD.CLIE_OID_CLIE
AND MCD.TIDC_OID_TIPO_DIRE = MTD.OID_TIPO_DIRE
AND MCD.TIVI_OID_TIPO_VIA = STV.OID_TIPO_VIA
AND MTD.COD_TIPO_DIRE = '07' -- Direccion de entrega
AND MCD.IND_ELIM = 0
AND IPD.COD_CLIE = MC.COD_CLIE
AND DBMS_LOB.INSTR(IPD.XML_CONS, '<frmbd>') != 0;

r_paquete c_paquete%ROWTYPE;

-- Variables usadas para el reemplazo
l_CLOB              CLOB;

BEGIN

    -- Hacemos el reemplazo de la direccion 1
    OPEN c_paquete;
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_DIR1, r_paquete.NEW_DIR1, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;

    -- Hacemos el reemplazo de la direccion 2
    OPEN c_paquete;
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_DIR2, r_paquete.NEW_DIR2, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;

END IMP_PR_REEMP_DIREC_BOLET_DESPA;

/***************************************************************************
Descripcion       : Procedimiento que reemplaza la direccion de la boleta de
                    despacho para aquellas consultoras que tienen una direccion
                    direccion de entrega configurada.
Fecha Creacion    : 08/06/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_DATOS_OCS(ps_codpais IN VARCHAR2) IS

lsmarca  VARCHAR2(10):= sto_pkg_gener.sto_fn_obten_param_ocr(ps_codpais,
                                                            'STO_MARCA_ORIG');

lsperi  VARCHAR2(10);

CURSOR c_paquete(marca varchar2, peri varchar2) IS
SELECT IPD.COD_CLIE,
       IPD.COR_PADO,
       IPD.NUM_PADO,
       INSTR(IPD.XML_CONS, '<frmocs>') POS_INIC,
       '<dir1>Tu GZ:' || (select x.nom_gere_dest || ' : ' || x.num_tele_dest from FUS_TMP_HOMOL_GEREN_ZONA x where --x.cod_pais_orig=marca
                   --and
                   x.cod_regi_dest=fdm.cod_regi_homo
                   and
                   x.cod_zona_dest=fdm.cod_zona_homo
                   and rownum=1
                   )
       || '</dir1>' NEW_DIR1,
       '<dir2>Tu Lider:' || (select x.nom_lide_dest || ' : ' || x.num_tele_dest from FUS_TMP_HOMOL_LIDER x where --x.cod_pais_orig=marca
                   --and
                   x.cod_regi_dest=fdm.cod_regi_homo
                   and
                   x.cod_zona_dest=fdm.cod_zona_homo
                   and x.cod_secc_dest=fdm.cod_secc_homo
                   and rownum=1
                   )
       || '</dir2>' NEW_DIR2,
       '<ccon>' || fdm.cod_clie_dest || fdm.cod_digi_ctrl_dest || '</ccon>' NEW_COD,
       '<creg>' || fdm.cod_regi_dest || '</creg>' NEW_REG,
       '<czon>' || fdm.cod_zona_dest || '</czon>' NEW_ZON,
       '<csec>' || fdm.cod_secc_dest || '</csec>' NEW_SEC,
       '<cter>' || fdm.cod_terr_dest || '</cter>' NEW_TER,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<dir1>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</dir1>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</dir1>') - (INSTR(IPD.XML_CONS, '<dir1>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_DIR1,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<dir2>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</dir2>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</dir2>') - (INSTR(IPD.XML_CONS, '<dir2>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_DIR2,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<ccon>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</ccon>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</ccon>') - (INSTR(IPD.XML_CONS, '<ccon>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_COD,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<creg>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</creg>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</creg>') - (INSTR(IPD.XML_CONS, '<creg>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_REG,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<czon>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</czon>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</czon>') - (INSTR(IPD.XML_CONS, '<czon>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_ZON,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<csec>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</csec>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</csec>') - (INSTR(IPD.XML_CONS, '<csec>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_SEC,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<cter>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</cter>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</cter>') - (INSTR(IPD.XML_CONS, '<cter>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_TER
FROM IMP_PAQUE_DOCUM IPD, fus_tmp_datos_migra fdm
WHERE IPD.COD_CLIE = fdm.COD_CLIE_ORIG
and fdm.cod_pais_orig=marca
and fdm.cod_camp=peri
AND DBMS_LOB.INSTR(IPD.XML_CONS, '<frmocs>') != 0;

r_paquete c_paquete%ROWTYPE;

-- Variables usadas para el reemplazo
l_CLOB              CLOB;


BEGIN

    select cod_peri into lsperi from bas_ctrl_fact z where z.ind_camp_act=1 and z.sta_camp=0;

    -- Hacemos el reemplazo de la direccion 1 por codigo de nueva gz
    OPEN c_paquete(lsmarca, lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_DIR1, r_paquete.NEW_DIR1, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;

    -- Hacemos el reemplazo de la direccion 2 por codigo de la nueva lider
    OPEN c_paquete(lsmarca, lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_DIR2, r_paquete.NEW_DIR2, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;


    -- Hacemos el reemplazo del codigo de Region
    OPEN c_paquete(lsmarca, lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_REG, r_paquete.NEW_REG, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;

    -- Hacemos el reemplazo del codigo de Zona
    OPEN c_paquete(lsmarca, lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_ZON, r_paquete.NEW_ZON, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;

    -- Hacemos el reemplazo del codigo de Seccion
    OPEN c_paquete(lsmarca, lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_SEC, r_paquete.NEW_SEC, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;

    -- Hacemos el reemplazo del codigo de Territorio
    OPEN c_paquete(lsmarca, lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_TER, r_paquete.NEW_TER, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;


    -- Hacemos el reemplazo del codigo de cliente
    OPEN c_paquete(lsmarca, lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_COD, r_paquete.NEW_COD, r_paquete.POS_INIC);
    END LOOP;



    CLOSE c_paquete;
    COMMIT;

END IMP_PR_REEMP_DATOS_OCS;

/***************************************************************************
Descripcion       : Procedimiento que reemplaza la direccion de la boleta de
                    despacho para aquellas consultoras que tienen una direccion
                    direccion de entrega configurada.
Fecha Creacion    : 08/06/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_DNI_OCS(ps_codpais IN VARCHAR2) IS



CURSOR c_paquete IS
SELECT IPD.COD_CLIE,
       IPD.COR_PADO,
       IPD.NUM_PADO,
       INSTR(IPD.XML_CONS, '<frmocs>') POS_INIC,
       '<ccon>' || mci.num_docu_iden || '</ccon>' NEW_COD,
       TRIM(SUBSTR(IPD.XML_CONS, INSTR(IPD.XML_CONS, '<ccon>', INSTR(IPD.XML_CONS, '<frmocs>')), INSTR(IPD.XML_CONS, '</ccon>', INSTR(IPD.XML_CONS, '<frmocs>')) + DBMS_LOB.GETLENGTH('</ccon>') - (INSTR(IPD.XML_CONS, '<ccon>', INSTR(IPD.XML_CONS, '<frmocs>'))))) OLD_COD
FROM IMP_PAQUE_DOCUM IPD, mae_clien mc, mae_clien_ident mci
WHERE IPD.COD_CLIE = mc.COD_CLIE
and mc.oid_clie=mci.clie_oid_clie
and mci.val_iden_docu_prin=1
AND DBMS_LOB.INSTR(IPD.XML_CONS, '<frmocs>') != 0;

r_paquete c_paquete%ROWTYPE;

-- Variables usadas para el reemplazo
l_CLOB              CLOB;


BEGIN


    -- Hacemos el reemplazo de la direccion 1 por codigo de nueva gz
    OPEN c_paquete;
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_COD, r_paquete.NEW_COD, r_paquete.POS_INIC);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;


END IMP_PR_REEMP_DNI_OCS;
/***************************************************************************
Descripcion       : Procedimiento que reemplaza la direccion de la boleta de
                    despacho para aquellas consultoras que tienen una direccion
                    direccion de entrega configurada.
Fecha Creacion    : 08/06/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_REEMP_OCS IS




lsperi  VARCHAR2(10);

CURSOR c_paquete(peri varchar2) IS
SELECT IPD.COD_CLIE,
       IPD.COR_PADO,
       IPD.NUM_PADO,
       substr(xml_cons,instr(xml_cons,'<frmocs>'),instr(xml_cons,'</frmocs>')-instr(xml_cons,'<frmocs>')+9) OLD_OCS,
       '<frmocs2>' || substr(xml_cons, instr(xml_cons,'<frmocs>')+8,instr(xml_cons,'</frmocs>')-instr(xml_cons,'<frmocs>')-17) || '<lin1>' || nvl(fch.val_pedi_base,0) || '</lin1>' ||  '<lin2>' || nvl(fch.val_line_cred,0)
       || '</lin2></blqcon></frmocs2>' NEW_OCS
FROM IMP_PAQUE_DOCUM_FINAL IPD, flx_consu_habil_flexi fch, flx_consu_linea_credi flx
WHERE IPD.COD_CLIE = fch.cod_clie
and IPD.COD_CLIE = flx.cod_clie(+)
and fch.cod_peri_comu=peri
and fch.ind_habi=1
and fch.ind_acti=1
AND DBMS_LOB.INSTR(IPD.XML_CONS, '<frmocs>') != 0;

r_paquete c_paquete%ROWTYPE;

-- Variables usadas para el reemplazo
l_CLOB              CLOB;


BEGIN

    select cod_peri into lsperi from bas_ctrl_fact z where z.ind_camp_act=1 and z.sta_camp=0;

    -- Hacemos el reemplazo de la direccion 1 por codigo de nueva gz
    OPEN c_paquete(lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM Imp_Paque_Docum_Final
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_OCS, r_paquete.new_ocs, 1);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;
exception when others then
          null;

END IMP_PR_REEMP_OCS;

/***************************************************************************
Descripcion       : Procedimiento que reemplaza la direccion de la boleta de
                    despacho para aquellas consultoras que tienen una direccion
                    direccion de entrega configurada.
Fecha Creacion    : 08/06/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_INCL_FLEX IS




lsperi  VARCHAR2(10);

CURSOR c_paquete(peri varchar2) IS
SELECT IPD.COD_CLIE,
       IPD.COR_PADO,
       IPD.NUM_PADO,
       substr(xml_cons,instr(xml_cons,'<frmecc>'),instr(xml_cons,'</frmecc>')-instr(xml_cons,'<frmecc>')+9) OLD_OCS,
       '<frmecc>' || substr(xml_cons, instr(xml_cons,'<frmecc>')+8,instr(xml_cons,'</frmecc>')-instr(xml_cons,'<frmecc>')-8) || '</frmecc>'
       || (select fl.xml_cons from FLX_PAQUE_DOCUM fl where fl.cod_clie=ipd.cod_clie and rownum=1)
       NEW_OCS
FROM IMP_PAQUE_DOCUM IPD--, flx_consu_habil_flexi fch
where DBMS_LOB.INSTR(IPD.XML_CONS, '<frmecc>') != 0;

r_paquete c_paquete%ROWTYPE;

-- Variables usadas para el reemplazo
l_CLOB              CLOB;


BEGIN

    select cod_peri into lsperi from bas_ctrl_fact z where z.ind_camp_act=1 and z.sta_camp=0;

    -- Hacemos el reemplazo de la direccion 1 por codigo de nueva gz
    OPEN c_paquete(lsperi);
    LOOP
    FETCH c_paquete INTO r_paquete;
    EXIT WHEN c_paquete%NOTFOUND;
      SELECT XML_CONS INTO l_CLOB
      FROM IMP_PAQUE_DOCUM
      WHERE COR_PADO = r_paquete.COR_PADO
      AND NUM_PADO = r_paquete.NUM_PADO FOR UPDATE;
      IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_CLOB, r_paquete.OLD_OCS, r_paquete.new_ocs, 1);
    END LOOP;
    CLOSE c_paquete;
    COMMIT;


END IMP_PR_INCL_FLEX;

/***************************************************************************
Descripcion       : Funcion que obtiene el tag a colocar en la boleta de
                    despacho dependiendo de la clasificacion de la consultora.
Fecha Creacion    : 24/11/2009
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
FUNCTION IMP_FN_ETIQU_CLASI_BOLET_DESPA(p_codigoCliente IN VARCHAR2) RETURN VARCHAR2 IS

l_result VARCHAR2(100) := '';

CURSOR c_etiquetas IS
SELECT IEBD.VAL_ETIQ_BOLE_DESP
FROM MAE_CLIEN_TIPO_SUBTI MCTS,
     MAE_CLIEN_CLASI MCC,
     MAE_CLIEN MC,
     IMP_ETIQU_CLASI IEC,
     IMP_ETIQU_BOLET_DESPA IEBD
WHERE MCC.CTSU_OID_CLIE_TIPO_SUBT = MCTS.OID_CLIE_TIPO_SUBT
AND IEC.CLAS_OID_CLAS = MCC.CLAS_OID_CLAS
AND IEC.TCCL_OID_TIPO_CLASI = MCC.TCCL_OID_TIPO_CLASI
AND IEC.ETBD_OID_ETIQ_BOLE_DESP = IEBD.OID_ETIQ_BOLE_DESP
AND IEBD.EST_ETIQ_BOLE_DESP = 1
AND MCTS.CLIE_OID_CLIE = MC.OID_CLIE
AND MC.COD_CLIE = p_codigoCliente;

BEGIN

    FOR r_etiquetas IN c_etiquetas LOOP
        l_result := r_etiquetas.VAL_ETIQ_BOLE_DESP;
    END LOOP;

    RETURN l_result;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
    l_result := '';
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_ETIQU_CLASI_BOLET_DESPA (: '|| p_codigoCliente ||')' ||substr(sqlerrm,1,250));

END;

/***************************************************************************
Descripcion       : Proceso que carga el paquete documentario directamente de
                    la tabla de SiCC y ya no desde el archivo.
Fecha Creacion    : 29/01/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_CARGA_PAQUE_DOCUM_SICC(p_codigoPais VARCHAR2,
                                        p_codigoPeriodo VARCHAR2,
                                        p_fechaFacturacion VARCHAR2) IS

-- Variables para la conversion de blob a clob
l_blob BLOB;
l_clob CLOB := 'x';
l_tempClob CLOB := EMPTY_CLOB;
l_dest_offsset INTEGER := 1;
l_src_offsset INTEGER := 1;
l_lang_context INTEGER := dbms_lob.default_lang_ctx;
l_warning INTEGER;

-- Cursor que recupera los paquetes documentarios
CURSOR c_paquetes(oidPeriodo NUMBER,
                  codigoImpresora VARCHAR2,
                  indicadorEnvioLarissa VARCHAR2,
                  numeroLoteFacturacion NUMBER) IS

select paquetes.oid_docu_impr,
       paquetes.val_buff,
       paquetes.cod_clie,
       paquetes.val_nume_soli,
       paquetes.num_secu_orig,
       '<nsec>' || pedidos.num_secu_fact_diar || '</nsec>' num_secu_actu,
       pedidos.num_secu_zona_ruta,
       pedidos.num_secu_fact_diar
from
    (
    select gdi.oid_docu_impr,
           gdi.val_buff,
           utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff, dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('</nbd>')) - (dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<nbd>')) + 5), dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<nbd>')) + 5)) val_nume_soli,
           utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff, dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('</ccon>')) - (dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<ccon>')) + 6), dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<ccon>')) + 6)) cod_clie,
           utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff, (dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('</nsec>')) + 7) - dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<nsec>')), dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<nsec>')))) num_secu_orig
    from gen_colas gc,
         gen_docum_impri gdi,
         fac_impre_virtu fiv
    where gc.oid_cola = gdi.gcol_oid_cola
      and gc.imvi_oid_impr = fiv.oid_impr
      and fiv.cod_impr = codigoImpresora
      and exists
      (
        select null
        from fac_secue_docum_inter sdi,
             ped_solic_cabec a
        where sdi.soca_oid_soli_cabe = a.oid_soli_cabe
          and a.perd_oid_peri = oidPeriodo
          and nvl(a.ind_inte_lari_gene, 0) = indicadorEnvioLarissa
          and a.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
          and (numeroLoteFacturacion is null or a.num_lote_fact = numeroLoteFacturacion)
          and a.num_unid_aten_tota > 0
          and sdi.gspo_oid_spoo = gc.gspo_oid_spool
      )
    order by 1
    ) paquetes,
    (
    select con.val_nume_soli,
           sec.num_secu_zona_ruta,
           sec.num_secu_fact_diar,
           sec.soca_oid_soli_cabe,
           sec.val_secu_ruta_terr
    from ped_solic_cabec_secue sec,
         ped_solic_cabec con
    where sec.soca_oid_soli_cabe = con.oid_soli_cabe
    and con.perd_oid_peri = oidPeriodo
    and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
    and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
    and nvl(con.ind_inte_lari_gene, 0) = indicadorEnvioLarissa
    and exists (
        select null
        from int_lar_tipo_solici_pedido_dis l
        where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
    and con.num_unid_aten_tota > 0
    order by  sec.val_secu_ruta_terr asc, con.clie_oid_clie
    ) pedidos
where paquetes.val_nume_soli (+) = pedidos.val_nume_soli
order by  pedidos.num_secu_zona_ruta,
          pedidos.num_secu_fact_diar,
          paquetes.cod_clie;

r_paquetes c_paquetes%ROWTYPE;

-- Variables locales
l_oidPais         NUMBER;
l_oidPeriodo      NUMBER;
l_oidCanal        NUMBER;
l_oidMarca        NUMBER;
l_correlativo     NUMBER := 1;
l_codigoImpresora       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'codigoImpresoraPaqdocLaser');
l_indicadorEnvioLarissa VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote  VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_numeroLoteFacturacion     NUMBER;
l_tagInicio                 VARCHAR2(10) := '<pd>';
l_tagFin                    VARCHAR2(10) := '</pd>';

BEGIN

    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_SICC
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_SICC';

    -- Obtenemos el OID del periodo
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);

    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;
        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;
        END;

    END IF;

    OPEN c_paquetes(l_oidPeriodo, l_codigoImpresora, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_paquetes INTO r_paquetes;
    EXIT WHEN c_paquetes%NOTFOUND;
        BEGIN

            -- Obtenemos la referencia al blob
            l_blob := r_paquetes.val_buff;

            -- Validamos que el blob sea diferente de null
            IF l_blob IS NOT NULL AND dbms_lob.getlength(l_blob) > 0 THEN

                INSERT INTO IMP_PAQUE_DOCUM_SICC
                VALUES (
                l_correlativo,
                EMPTY_CLOB(),
                r_paquetes.cod_clie,
                r_paquetes.val_nume_soli,
                'N')
                RETURNING XML_CONS INTO l_clob;

                l_dest_offsset := 1;
                l_src_offsset  := 1;

                -- Creamos el temporal
                dbms_lob.createtemporary(l_tempClob, TRUE);
                dbms_lob.open(l_tempClob, dbms_lob.lob_readwrite);

                dbms_lob.converttoclob
                ( dest_lob => l_tempClob
                , src_blob => l_blob
                , amount => dbms_lob.lobmaxsize
                , dest_offset => l_dest_offsset
                , src_offset => l_src_offsset
                , blob_csid => dbms_lob.default_csid
                , lang_context => l_lang_context
                , warning => l_warning
                );

                -- Agregamos el tag de fin de paquete documentario
                -- al CLOB temporal para usarlo despues
                dbms_lob.writeappend
                ( lob_loc => l_tempClob
                , amount => length(l_tagFin)
                , buffer => l_tagFin
                );

                -- Agregamos el tag de inicio de paquete documentario
                dbms_lob.append
                ( l_clob,
                  to_clob(l_tagInicio)
                );

                -- Agregamos el resto del paquete documentario
                dbms_lob.append
                ( l_clob,
                  l_tempClob
                );

                -- Liberamos el clob temporal
                dbms_lob.close(l_tempClob);

                l_correlativo := l_correlativo + 1;

                -- Si el numero de secuencia del paquete almacenado en base de
                -- datos es diferente al valor de la tabla PED_SOLIC_CABEC_SECUE
                -- hacemos la actualizacion correspondiente en la tabla de SSiCC
                IF r_paquetes.num_secu_orig != r_paquetes.num_secu_actu THEN
                    IMP_PKG_PROCE_GENER.IMP_PR_LOB_REPLACE(l_clob, r_paquetes.num_secu_orig, r_paquetes.num_secu_actu);
                END IF;

                COMMIT;

            END IF;

        END;
    END LOOP;
    CLOSE c_paquetes;

    -- Actualizamos el indicador de pedido de servicio
    update imp_paque_docum_sicc a
    set ind_pedi_serv = 'S'
    where not exists (
        select null
        from ped_solic_cabec con,
             ped_solic_cabec psc,
             mae_clien mc
        where mc.oid_clie = con.clie_oid_clie
        and con.oid_soli_cabe = psc.soca_oid_soli_cabe
        and psc.ind_oc = 1
        and mc.cod_clie = a.cod_clie
        and con.val_nume_soli = a.val_nume_soli
    );

    COMMIT;

END;

/***************************************************************************
Descripcion       : Proceso que carga los cupones directamente de
                    la tabla de SiCC y ya no desde el archivo.
Fecha Creacion    : 01/02/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_CARGA_PAQUE_DOCUM_CUPON(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2) IS
-- Variables para la conversion de blob a clob
l_blob BLOB;
l_clob CLOB := 'x';
l_dest_offsset INTEGER := 1;
l_src_offsset INTEGER := 1;
l_lang_context INTEGER := dbms_lob.default_lang_ctx;
l_warning INTEGER;

-- Cursor que recupera los cupones
CURSOR c_cupones(codigoImpresora VARCHAR2) IS
select oid_docu_impr,
       val_buff,
       cod_clie,
       fec_emis,
       cod_peri
from (
select gdi.oid_docu_impr,
       gdi.val_buff,
       utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff, dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('</fec>')) - (dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<fec>')) + 5), dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<fec>')) + 5)) fec_emis,
       utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff, dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('</ccon>')) - (dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<ccon>')) + 6), dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<ccon>')) + 6)) cod_clie,
       utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff, dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('</fcam>')) - (dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<fcam>')) + 6), dbms_lob.instr(gdi.val_buff, utl_raw.cast_to_raw('<fcam>')) + 6)) cod_peri
from gen_colas gc,
     gen_docum_impri gdi,
     fac_impre_virtu fiv,
     gen_spool gs
where gc.oid_cola = gdi.gcol_oid_cola
  and gc.imvi_oid_impr = fiv.oid_impr
  and gc.gspo_oid_spool = gs.oid_spoo
  and fiv.cod_impr = codigoImpresora
  and not exists (
    select null
    from fac_secue_docum_inter sdi
    where sdi.gspo_oid_spoo = gc.gspo_oid_spool
  )
  and gs.fec_aper >= to_date(p_fechaFacturacion, 'dd/mm/yyyy')
)
where fec_emis = p_fechaFacturacion
and substr(cod_peri, 4, 4) || substr(cod_peri, 1, 2) = p_codigoPeriodo
order by cod_clie;

r_cupones c_cupones%ROWTYPE;

-- Variables locales
l_correlativo     NUMBER := 1;
l_codigoImpresora VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'codigoImpresoraPaqdocLaser');

BEGIN

    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_CUPON
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

    OPEN c_cupones(l_codigoImpresora);
    LOOP
    FETCH c_cupones INTO r_cupones;
    EXIT WHEN c_cupones%NOTFOUND;
        BEGIN
            l_blob := r_cupones.val_buff;

            INSERT INTO IMP_PAQUE_DOCUM_CUPON
            VALUES (
            l_correlativo,
            EMPTY_CLOB(),
            r_cupones.cod_clie)
            RETURNING XML_CONS INTO l_clob;

            l_dest_offsset := 1;
            l_src_offsset  := 1;

            dbms_lob.converttoclob
            ( dest_lob => l_clob
            , src_blob => l_blob
            , amount => dbms_lob.lobmaxsize
            , dest_offset => l_dest_offsset
            , src_offset => l_src_offsset
            , blob_csid => dbms_lob.default_csid
            , lang_context => l_lang_context
            , warning => l_warning
            );

            l_correlativo := l_correlativo + 1;

        END;
    END LOOP;
    CLOSE c_cupones;

END;

/***************************************************************************
Descripcion       : Proceso que genera los cupones de las consultoras que
                    han pasado pedido en un periodo y fecha particular.
Fecha Creacion    : 31/03/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
PROCEDURE IMP_PR_GENER_PAQUE_DOCUM_CUPON(p_codigoPais VARCHAR2,
                                         p_codigoPeriodo VARCHAR2,
                                         p_fechaFacturacion VARCHAR2) IS

CURSOR c_cupon(oidPais NUMBER,
               oidPeriodo NUMBER,
               indicadorEnvioLarissa VARCHAR2,
               numeroLoteFacturacion NUMBER) IS
select mc.oid_clie,
       mc.cod_digi_ctrl,
       mc.cod_clie,
       mc.val_ape1 || ' ' || mc.val_ape2 || ', ' || mc.val_nom1 || ' ' || mc.val_nom2 nom_clie,
       stv.des_abrv_tipo_via  || ' ' || mcd.val_nomb_via || ' ' || mcd.num_ppal val_dir1,
       trim('/' from
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 4) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 3) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 2) || '/' ||
       GEN_PKG_GENER.GEN_FN_DESCR_ESTRU_GEOPO(oidPais, mc.oid_clie, 1)) val_dir2,
       zon.oid_zona,
       zon.cod_zona,
       ter.cod_terr
from mae_clien mc,
     mae_clien_direc mcd,
     seg_tipo_via stv,
     ped_solic_cabec con,
     zon_zona zon,
     zon_terri ter
where mc.oid_clie = mcd.clie_oid_clie
  and mcd.tivi_oid_tipo_via = stv.oid_tipo_via (+)
  and mcd.ind_elim = 0
  and mcd.ind_dire_ppal = 1
  and mc.oid_clie = con.clie_oid_clie
  and con.zzon_oid_zona = zon.oid_zona
  and con.terr_oid_terr = ter.oid_terr
  and con.perd_oid_peri = oidPeriodo
  and con.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
  and con.ind_inte_lari_gene = indicadorEnvioLarissa
  and (numeroLoteFacturacion is null or con.num_lote_fact = numeroLoteFacturacion)
  and exists (
      select null
      from int_lar_tipo_solici_pedido_dis l
      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais
  );

r_cupon c_cupon%ROWTYPE;

-- Variables locales
l_oidPais                       NUMBER;
l_oidPeriodo                    NUMBER;
l_oidPeriodoSgte                NUMBER;
l_codPeriodoSgte                VARCHAR2(6);
l_oidCanal                      NUMBER;
l_oidMarca                      NUMBER;
l_oidActividad                  NUMBER;
l_fechaVencimiento              DATE;
l_codigoPeriodo                 VARCHAR2(25);
l_saldoCupon                    NUMBER(12,2) := 0.00;
l_correlativo                   NUMBER := 1;
l_indicadorEnvioLarissa         VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioLarissa');
l_indicadorEnvioUltimoLote      VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indicadorEnvioUltimoLote');
l_codigoActividadVencimiento    VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'codigoActividadVencimiento');
l_numeroLoteFacturacion         NUMBER;
l_clob                          CLOB;
l_textoActual                   VARCHAR2(1000) := '';
l_digiver                       VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'digiVer');

BEGIN

    -- Obtenemos los OIDs necesarios
    l_oidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(p_codigoPais);
    l_oidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(CODIGO_CANAL);
    l_oidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(CODIGO_MARCA);
    l_oidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(p_codigoPeriodo, l_oidMarca, l_oidCanal);
    l_codigoPeriodo := SUBSTR(p_codigoPeriodo, 5, 2) || '/' || SUBSTR(p_codigoPeriodo, 1, 4);

    IF(l_indicadorEnvioUltimoLote = '1' OR l_indicadorEnvioUltimoLote = 'S') THEN

        BEGIN
            select max(cons.num_lote_fact)
            into l_numeroLoteFacturacion
            from ped_solic_cabec cons,
                 int_lar_tipo_solici_pedido_dis tspd
            where cons.perd_oid_peri = l_oidPeriodo
             and  cons.fec_fact = to_date(p_fechaFacturacion, 'dd/mm/yyyy')
             and  cons.ind_ts_no_conso = 0
             and  (cons.ind_pedi_prue = 0 or cons.ind_pedi_prue is null)
             and  cons.tspa_oid_tipo_soli_pais = tspd.tspa_oid_tipo_soli_pais
             and  cons.pais_oid_pais = l_oidPais;
        EXCEPTION
          WHEN OTHERS THEN
              l_numeroLoteFacturacion := null;
        END;

    END IF;

    -- Obtenemos el OID del periodo siguiente
    l_codPeriodoSgte := GEN_FN_CALCU_PERIO(p_codigoPeriodo, 1);
    l_oidPeriodoSgte := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(l_codPeriodoSgte, l_oidMarca, l_oidCanal);

    -- Obtenemos el OID de la actividad
    SELECT ACT.OID_ACTI
    INTO l_oidActividad
    FROM CRA_ACTIV ACT
    WHERE ACT.PAIS_OID_PAIS = l_oidPais
    AND ACT.MARC_OID_MARC = l_oidMarca
    AND ACT.CANA_OID_CANA = l_oidCanal
    AND ACT.COD_ACTI = l_codigoActividadVencimiento; -- Cupon de Vencimiento

    -- Elimina todos las filas de la Tabla IMP_PAQUE_DOCUM_CUPON
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_PAQUE_DOCUM_CUPON';

    OPEN c_cupon(l_oidPais, l_oidPeriodo, l_indicadorEnvioLarissa, l_numeroLoteFacturacion);
    LOOP
    FETCH c_cupon INTO r_cupon;
    EXIT WHEN c_cupon%NOTFOUND;
        BEGIN

            INSERT INTO IMP_PAQUE_DOCUM_CUPON
            VALUES (
            l_correlativo,
            EMPTY_CLOB(),
            r_cupon.cod_clie)
            RETURNING XML_CONS INTO l_clob;

            -- Obtenemos la fecha de vencimiento de la zona del cliente
            BEGIN
                SELECT CRO.FEC_INIC
                INTO l_fechaVencimiento
                FROM CRA_CRONO CRO
                WHERE CRO.ZZON_OID_ZONA = r_cupon.oid_zona
                AND CRO.PERD_OID_PERI = l_oidPeriodoSgte
                AND CRO.CACT_OID_ACTI = l_oidActividad;
            EXCEPTION
            WHEN NO_DATA_FOUND THEN
                l_fechaVencimiento := TRUNC(SYSDATE);
            END;

            -- Obtenemos el valor del saldo para el cliente
            --l_saldoCupon := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_VENCI(r_cupon.oid_clie, l_fechaVencimiento);
            l_saldoCupon := CCC_PKG_GENER.CCC_FN_OBTIE_SALDO_CAMPA_ANTER(r_cupon.oid_clie,l_codPeriodoSgte);

            if l_saldoCupon<0 then
              l_saldoCupon:=0;
            end if;

            -- Inicio Cupon
            l_textoActual := '<frmecc>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Inicio cabecera
            l_textoActual := '<cab>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Codigo Cliente
            l_textoActual := '<ccon>' || r_cupon.cod_clie || '</ccon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Codigo Verificador
            if nvl(l_digiver,'N')='S' then
            l_textoActual := '<dver>' || r_cupon.cod_digi_ctrl || '</dver>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);
            end if;

            -- Nombre Cliente
            l_textoActual := '<ncon>' || r_cupon.nom_clie || '</ncon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Zona
            l_textoActual := '<czon>' || r_cupon.cod_zona || '</czon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Periodo
            l_textoActual := '<fcam>' || l_codigoPeriodo || '</fcam>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Territorio
            l_textoActual := '<cter>' || r_cupon.cod_terr || '</cter>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Direccion 1
            l_textoActual := '<dir1>' || r_cupon.val_dir1 || '</dir1>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Direccion 2
            l_textoActual := '<dir2>' || r_cupon.val_dir2 || '</dir2>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fecha
            l_textoActual := '<fec>' || p_fechaFacturacion || '</fec>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fin Cabecera
            l_textoActual := '</cab>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Inicio Bloque Cupon
            l_textoActual := '<blqcp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Codigo Cliente
            l_textoActual := '<ccon>' || r_cupon.cod_clie || '</ccon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Nombre Cliente
            l_textoActual := '<ncon>' || r_cupon.nom_clie || '</ncon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Zona
            l_textoActual := '<czon>' || r_cupon.cod_zona || '</czon>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Territorio
            l_textoActual := '<cter>' || r_cupon.cod_terr || '</cter>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Saldo
            l_textoActual := '<saldo>' || l_saldoCupon || '</saldo>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fecha Facturacion
            l_textoActual := '<fec>' || p_fechaFacturacion || '</fec>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fecha Vencimiento
            l_textoActual := '<fecven>' || to_char(l_fechaVencimiento, 'dd/mm/yyyy') || '</fecven>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fin Bloque Cupon
            l_textoActual := '</blqcp>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Detalle Vacio
            l_textoActual := '<detalle><txt><t/><tr/><tr/><tr/></txt><txt><t/><tr/><tr/><tr/></txt></detalle>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            -- Fin Cupon
            l_textoActual := '</frmecc>';
            DBMS_LOB.writeappend(l_clob, LENGTH(l_textoActual), l_textoActual);

            l_correlativo := l_correlativo + 1;

        END;
    END LOOP;
    CLOSE c_cupon;

END;

/***************************************************************************
Descripcion       : Funcion que obtiene el tag a colocar en la boleta de
                    despacho dependiendo del estado de la consultora.
Fecha Creacion    : 27/05/2010
Autor             : Carlos Hurtado Ramirez
***************************************************************************/
FUNCTION IMP_FN_ETIQU_ESTAT_BOLET_DESPA(p_codigoCliente IN VARCHAR2) RETURN VARCHAR2 IS

l_result VARCHAR2(100) := '';

CURSOR c_etiquetas IS
SELECT IEBD.VAL_ETIQ_BOLE_DESP
FROM MAE_CLIEN MC,
     MAE_CLIEN_DATOS_ADICI MCDA,
     IMP_ETIQU_ESTAT_CLIEN IEEC,
     IMP_ETIQU_BOLET_DESPA IEBD
WHERE IEEC.ESTA_OID_ESTA_CLIE = MCDA.ESTA_OID_ESTA_CLIE
AND IEEC.ETBD_OID_ETIQ_BOLE_DESP = IEBD.OID_ETIQ_BOLE_DESP
AND IEBD.EST_ETIQ_BOLE_DESP = 1
AND MCDA.CLIE_OID_CLIE = MC.OID_CLIE
AND MC.COD_CLIE = p_codigoCliente;

BEGIN

    FOR r_etiquetas IN c_etiquetas LOOP
        l_result := r_etiquetas.VAL_ETIQ_BOLE_DESP;
    END LOOP;

    RETURN l_result;

    EXCEPTION
    WHEN NO_DATA_FOUND THEN
    l_result := '';
    WHEN OTHERS THEN
    RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_FN_ETIQU_ESTAT_BOLET_DESPA (: '|| p_codigoCliente ||')' ||substr(sqlerrm,1,250));

END;

/**************************************************************************
Descripcion         : Realiza el 'merge' entre el archivo XML del SiCC, el archivo
                      XML de Cupon de Pago y los bloques XML de las Ultimas Noticias
                      Privilege, el resultado final va a la tabla IMP_PAQUE_DOCUM.
                      (Usando el CLOB en Memoria)
Fecha Creacion      : 03/06/2014
Autor               : Sergio Apaza
***************************************************************************/
PROCEDURE IMP_PR_COMPA_PAQDO_FINAL3(p_valnumesoli NUMBER, p_codclie varchar2) IS

-- Variable usada para eliminar los detalles erroneos
lv_eliminacionDetalles VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorEliminacionDetallesErroneos');
l_clob                 CLOB;
l_clob_temp            CLOB;
--l_clob_temp2           CLOB;


CURSOR c_pado IS
select cod_pado from IMP_COMPA_PAQUE_DOCUM
;

r_pado c_pado%ROWTYPE;


CURSOR c_form(p_codpado varchar2) IS
select a.cod_form from IMP_FORMU_PAQUE_DOCU2 a
where a.cod_pado=p_codpado and a.est_form=1
order by a.num_orde_secu
;

r_form c_form%ROWTYPE;

/*CURSOR c_borec IS
select a.xml_cons from IMP_PAQUE_DOCUM_BOREC a where a.cod_cons
not in (select b.cod_clie from imp_paque_docum_final b)
;

r_borec c_borec%ROWTYPE;

*/

    ln_oidperi    NUMBER(10);
--    ln_borec      NUMBER(1):=0;
    lv_fecfact   varchar2(20);

    lv_codpais   varchar2(20);
    --w_filas        NUMBER(12);

ped_serv NUMBER := 0;
ped_expr NUMBER := 0;
lv_indicadorPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorPedidoServicio');
lv_indicadorNMP              VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorNMP');
lv_tagInicioPedidoServicio   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio');
lv_tagInicioPedidoServicio2   VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicioPedidoServicio2');
lv_tagInicio VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagInicio');
lv_tagFin VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','tagFin');


begin



    OPEN c_pado;
    LOOP
    FETCH c_pado INTO r_pado;
    EXIT WHEN c_pado%NOTFOUND;

            --Se inicializa la variabla CLOB en Memoria
            DBMS_LOB.CREATETEMPORARY(l_clob, TRUE);

            /*INSERT INTO IMP_PAQUE_DOCUM_FINAL(
            COR_PADO,
            COD_CLIE,
            NUM_PADO,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            p_valnumesoli,
            p_codclie,
            r_pado.cod_pado,
            p_valnumesoli,
            EMPTY_CLOB())
            RETURNING XML_CONS INTO l_clob;*/

            if lv_indicadorPedidoServicio='1' then

            select count(1)
            into ped_serv
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and
            (
            exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and ind_oc=1)
            or (
                   exists (select 1
                          from ped_solic_cabec x, ped_tipo_solic_pais y, ped_tipo_solic z, gen_i18n_sicc_comun gen
                          where x.tspa_oid_tipo_soli_pais=y.oid_tipo_soli_pais
                          and y.tsol_oid_tipo_soli=z.oid_tipo_soli
                          and z.oid_tipo_soli=gen.val_oid
                          and gen.attr_enti='PED_TIPO_SOLIC'
                          and gen.val_i18n like '%NMP%'
                          and x.soca_oid_soli_cabe=a.oid_soli_cabe
                          )
                   and lv_indicadorNMP='1'
               )
            )
            ;

            select count(1)
            into ped_expr
            from ped_solic_cabec a
            where a.val_nume_soli=p_valnumesoli
            and a.clie_oid_clie=(select oid_clie from mae_clien where cod_clie=p_codclie)
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%XPRES%'
            );

            end if;

            if ped_serv=0 and lv_indicadorPedidoServicio='1' then
              if ped_expr=0 then
                --l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio;
                DBMS_LOB.writeappend(l_clob, LENGTH(lv_tagInicioPedidoServicio), lv_tagInicioPedidoServicio);
              else
                --l_clob_temp:=l_clob_temp || lv_tagInicioPedidoServicio2;
                DBMS_LOB.writeappend(l_clob, LENGTH(lv_tagInicioPedidoServicio2), lv_tagInicioPedidoServicio2);
              end if;
            else
                --l_clob_temp:=l_clob_temp || lv_tagInicio;
                DBMS_LOB.writeappend(l_clob, LENGTH(lv_tagInicio), lv_tagInicio);
            end if;

            OPEN c_form(r_pado.cod_pado);
            LOOP
            FETCH c_form INTO r_form;
            EXIT WHEN c_form%NOTFOUND;

begin
                  if r_form.cod_form='BD' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_bolet_despa x
                     where x.val_nume_soli=p_valnumesoli;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2;
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);


                  end if;
                  if r_form.cod_form='OC' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_ocs x
                     where x.val_nume_soli=p_valnumesoli;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2;
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='UN' then
                     select x.xml_unot into l_clob_temp from imp_paque_docum_unot x
                     where x.val_nume_soli=p_valnumesoli;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2;
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                  end if;
                  if r_form.cod_form='DF' then
                     select x.xml_deta_fact into l_clob_temp from imp_paque_docum_detal_factu x
                     where x.val_nume_soli=p_valnumesoli;

                     if LENGTH(l_clob_temp)>30000 then
                      while LENGTH(l_clob_temp)>30000
                      loop
                         DBMS_LOB.writeappend(l_clob, 30000, substr(l_clob_temp,1,30000));
                         l_clob_temp:=substr(l_clob_temp,30001);
                       end loop;
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  else
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                   end if;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2;


                  end if;
                  if r_form.cod_form='CP' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie and rownum=1;

                     --l_clob_temp:=l_clob_temp || '<frmecc>' || l_clob_temp2 || '</frmecc>';

                     DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='CT' then
                     select x.xml_cons into l_clob_temp from imp_paque_docum_cupon x
                     where x.cod_clie=p_codclie and rownum=1;

                     --l_clob_temp:=l_clob_temp || '<frmecc>' || l_clob_temp2 ;

                     DBMS_LOB.writeappend(l_clob, LENGTH('<frmecc>'), '<frmecc>');
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);

                     select x.xml_cuen_corr into l_clob_temp from imp_paque_docum_laser_ctact x
                     where x.cod_clie=p_codclie;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2 || '</frmecc>' ;

                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     DBMS_LOB.writeappend(l_clob, LENGTH('</frmecc>'), '</frmecc>');
                  end if;
                  if r_form.cod_form='BR' then
                    -- ln_borec:=1;
                     select x.xml_cons into l_clob_temp from imp_paque_docum_borec x
                     where x.cod_cons=p_codclie and rownum=1;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2;
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  if r_form.cod_form='FA' then
                    -- ln_borec:=1;
                     select x.xml_lase_fact into l_clob_temp from IMP_PAQUE_DOCUM_LASER_FACTU x
                     where x.cod_clie=p_codclie and rownum=1;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2;
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  end if;
                  if r_form.cod_form='FX' then
                    -- ln_borec:=1;
                     begin
                     select x.xml_cons into l_clob_temp from flx_paque_docum x
                     where x.cod_clie=p_codclie and rownum=1;

                     --l_clob_temp:=l_clob_temp || l_clob_temp2;
                     DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                     exception when others then
                       null;
                     end;
                  end if;




                  exception when others then
                     continue;
                  end;

            END LOOP;
            CLOSE c_form;
            DBMS_LOB.writeappend(l_clob, LENGTH('</pd>'), '</pd>');

                  /*if LENGTH(l_clob_temp)>30000 then
                      while LENGTH(l_clob_temp)>30000
                      loop
                       DBMS_LOB.writeappend(l_clob, 30000, substr(l_clob_temp,1,30000));
                       l_clob_temp:=substr(l_clob_temp,30001);
                       end loop;
                       l_clob_temp:=l_clob_temp || '</pd>';
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                  else
                       l_clob_temp:=l_clob_temp || '</pd>';
                       DBMS_LOB.writeappend(l_clob, LENGTH(l_clob_temp), l_clob_temp);
                   end if;

            l_clob_temp:='';*/

          --Se inserta el CLOB en memoria a BD
          INSERT INTO IMP_PAQUE_DOCUM_FINAL(
            COR_PADO,
            COD_CLIE,
            NUM_PADO,
            VAL_NUME_SOLI,
            XML_CONS
            )
            VALUES (
            p_valnumesoli,
            p_codclie,
            r_pado.cod_pado,
            p_valnumesoli,
            l_clob);

          --Se libera la variabla CLOB de Memoria
          DBMS_LOB.FREETEMPORARY(l_CLOB);
    END LOOP;
    CLOSE c_pado;


    commit;


END IMP_PR_COMPA_PAQDO_FINAL3;

/***************************************************************************
Descripcion       : Procedimiemto que implementa la logica en JAVA
Parametros        : Codigo de pais, codigo de periodo, codigo de marca, codigo de canal
Fecha Creacion    : 17/09/2014
Autor             : Gonzalo Javier Huertas Agurto
       06/01/205    Se agrega logica para Chile
***************************************************************************/
PROCEDURE IMP_PR_GENER_XML_BOREC_ORA(psCodigoPais IN VARCHAR2,
                                   psCodigoPeriodo IN VARCHAR2,
                                   psCodigoMarca IN VARCHAR2,
                                   psCodigoCanal IN VARCHAR2)
is
    ln_sqlcode                        NUMBER(10);
    ls_sqlerrm                        VARCHAR2(2500);

cursor c_boleta_recojo_cabecera is
SELECT DISTINCT CB.COD_REGI, CB.COD_ZONA, CB.COD_CABE_BORE, CB.COD_CLIE,
       CB.Num_Reco, '1' FLAG_IMP
  FROM INT_REC_LINEA_BOREC LB,
       INT_REC_CABEC_BOREC CB,
       REC_CABEC_RECLA     RC,
       CRA_PERIO           CRA,
       SEG_PERIO_CORPO     SP,
       INT_REC_CIERR_BOREC CIE
 WHERE CB.COD_PAIS = psCodigoPais
   AND LB.COD_PAIS = psCodigoPais
   AND CB.ESBO_OID_ESTA_BOR1 =
       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais, 'I')
   AND CB.ESBO_OID_ESTA_BOR2 IS NULL
   AND CB.NUM_RECO = 1
   AND CB.IND_REGR_YOBE = 0
   AND LB.IND_REGR_YOBE = 0
   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
   AND CRA.PERI_OID_PERI = SP.OID_PERI
   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
   AND CB.IND_ENVI_YOBE = 1
   AND LB.IND_ENVI_YOBE = 1
   AND CB.IND_ENVI_XERO = 0
   AND LB.IND_ENVI_XERO = 0
   AND CB.NUM_TOTA_UNID_RECL > 0
   AND cie.cod_regi =
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
   AND cie.cod_zona =
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
   AND cie.cod_pais = cb.cod_pais
   AND (select fec_proc
          from bas_ctrl_fact
         where sta_camp = 0
           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
   AND (select fec_proc
          from bas_ctrl_fact
         where sta_camp = 0
           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
   AND cie.cod_peri = psCodigoPeriodo
UNION
SELECT DISTINCT CB.COD_REGI, CB.COD_ZONA, CB.COD_CABE_BORE, CB.COD_CLIE,
       CB.Num_Reco, '1' FLAG_IMP
  FROM INT_REC_LINEA_BOREC LB,
       INT_REC_CABEC_BOREC CB,
       REC_CABEC_RECLA     RC,
       CRA_PERIO           CRA,
       SEG_PERIO_CORPO     SP,
       PED_SOLIC_CABEC     CON,
       INT_REC_CIERR_BOREC CIE
 WHERE CB.COD_PAIS = psCodigoPais
   AND LB.COD_PAIS = psCodigoPais
   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
   AND CB.ESBO_OID_ESTA_BOR2 =
       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais, 'NX')
   AND CB.NUM_RECO = 2
   AND CB.ESBO_OID_ESTA_BOR1 =
       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais, 'GE')
   AND CB.IND_ENVI_YOBE = 1
   AND CB.IND_REGR_YOBE = 1
   AND LB.IND_ENVI_YOBE = 1
   AND LB.IND_REGR_YOBE = 1
   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
   AND CRA.PERI_OID_PERI = SP.OID_PERI
   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
   AND CON.CLIE_OID_CLIE = CB.CLIE_OID_CLIE
   AND CON.PERD_OID_PERI =
       GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
   AND CON.GRPR_OID_GRUP_PROC =
       INT_PKG_RECLA.GEN_FN_DEVUE_OID_GRUPO_PROCE('GP5')
   AND CON.SOCA_OID_SOLI_CABE IS NULL
   AND CON.TSPA_OID_TIPO_SOLI_PAIS IN
       (INT_PKG_RECLA.GEN_FN_DEVUE_OID_TIPO_SOLPA('C1'))
   AND (select count(*) from ped_solic_Cabec x 
        where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE 
        and x.TSPA_OID_TIPO_SOLI_PAIS <>  
         (select tsp.oid_tipo_soli_pais
          from ped_tipo_solic_pais tsp, ped_tipo_solic ts
          where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
          and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0    --- que no sea cargo de uso flexipago     
   AND cie.cod_regi =
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
   AND cie.cod_zona =
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
   AND cie.cod_pais = cb.cod_pais
   AND (select fec_proc
          from bas_ctrl_fact
         where sta_camp = 0
           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
   AND (select fec_proc
          from bas_ctrl_fact
         where sta_camp = 0
           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
   AND cie.cod_peri = psCodigoPeriodo
   AND CB.IND_ENVI_YOB2 = 1
   AND LB.IND_ENVI_YOB2 = 1
   AND CB.IND_ENVI_XER2 IS NULL
   AND LB.IND_ENVI_XER2 IS NULL
   AND CB.NUM_TOTA_UNID_RECL > 0
UNION
SELECT DISTINCT CB.COD_REGI, CB.COD_ZONA, CB.COD_CABE_BORE, CB.COD_CLIE,
       CB.Num_Reco, '1' FLAG_IMP
  FROM INT_REC_LINEA_BOREC LB,
       INT_REC_CABEC_BOREC CB,
       MAE_CLIEN           MC,
       REC_CABEC_RECLA     RC,
       CRA_PERIO           CRA,
       SEG_PERIO_CORPO     SP,
       INT_REC_CIERR_BOREC CIE
 WHERE CB.COD_PAIS = psCodigoPais
   AND LB.COD_PAIS = psCodigoPais
   AND CB.ESBO_OID_ESTA_BOR2 =
       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais, 'NX')
   AND CB.NUM_RECO = 2
   AND CB.ESBO_OID_ESTA_BOR1 =
       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais, 'GE')
   AND CB.IND_ENVI_YOBE = 1
   AND CB.IND_REGR_YOBE = 1
   AND LB.IND_ENVI_YOBE = 1
   AND LB.IND_REGR_YOBE = 1
   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
   AND MC.OID_CLIE = CB.CLIE_OID_CLIE
   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
   AND CRA.PERI_OID_PERI = SP.OID_PERI
   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
   AND CB.CLIE_OID_CLIE NOT IN
       (SELECT CLIE_OID_CLIE
          FROM PED_SOLIC_CABEC con1
         WHERE con1.GRPR_OID_GRUP_PROC =
               INT_PKG_RECLA.GEN_FN_DEVUE_OID_GRUPO_PROCE('GP5')
           AND con1.PERD_OID_PERI =
               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
           AND con1.SOCA_OID_SOLI_CABE IS NULL
           AND con1.TSPA_OID_TIPO_SOLI_PAIS IN (INT_PKG_RECLA.GEN_FN_DEVUE_OID_TIPO_SOLPA('C1'))
           AND (select count(*) from ped_solic_Cabec x 
                where X.SOCA_OID_SOLI_CABE = con1.OID_SOLI_CABE 
                and x.TSPA_OID_TIPO_SOLI_PAIS <>  
                 (select tsp.oid_tipo_soli_pais
                  from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                  where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                  and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago                                    
               )
   AND (GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'OID_ZONA') IN
       (SELECT ZZON_OID_ZONA
           FROM FAC_CONTR_CIERR CI
          WHERE CI.PERD_OID_PERI =
                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
            AND CI.TCIE_OID_TIPO_CIER = 2) OR
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'OID_REGI') IN
       (SELECT ZORG_OID_REGI
           FROM fac_contr_cierr ci
          WHERE ci.perd_oid_peri =
                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
            AND ci.tcie_oid_tipo_cier = 1))
   AND CB.IND_ENVI_YOB2 = 1
   AND LB.IND_ENVI_YOB2 = 1
   AND CB.IND_ENVI_XER2 IS NULL
   AND LB.IND_ENVI_XER2 IS NULL
   AND cie.cod_regi =
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_REGI')
   AND cie.cod_zona =
       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE, 'COD_ZONA')
   AND cie.cod_pais = cb.cod_pais
   AND (select fec_proc
          from bas_ctrl_fact
         where sta_camp = 0
           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
   AND (select fec_proc
          from bas_ctrl_fact
         where sta_camp = 0
           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
   AND cie.cod_peri = psCodigoPeriodo
      /*in (
           select cod_peri
           from bas_ctrl_fact
           where sta_camp = 0
           and ind_camp_act = 1
      )*/
   AND CB.NUM_TOTA_UNID_RECL > 0
 ORDER BY COD_CLIE, NUM_RECO;
--- ORDER BY COD_REGI, COD_ZONA, COD_CLIE, NUM_RECO;

TYPE boletaRecojoCabeceraRec IS RECORD(
   COD_REGI          varchar2(2),
   COD_ZONA          varchar2(4),
   COD_CABE_BORE     number(12),
   COD_CLIE          varchar2(15),
   NUM_RECO          number(4),
   FLAG_IMP          varchar2(11)
);

TYPE boletaRecojoCabeceraRecCab IS TABLE OF boletaRecojoCabeceraRec;
boletaRecojoCabeceraRecord boletaRecojoCabeceraRecCab;

 sizePaginacion              number;
 actualDireccionBR           number;
 totalPaginas                number;

 datoEnvioBR                 varchar2(10);

 rowhasta                    number;
 rowdesde                    number;
 codigoCabeceraBoletaRecojo  NUMBER(12);
 existenPaginas              boolean;
 existeCabecera              boolean;
 pagina                      number;

 boletaRecojoLineaTamanio    number;

  vl_val_nume_bore            NUMBER(10);
  vl_num_reco                 VARCHAR2(10);
  vl_cod_zona                 VARCHAR2(4);
  vl_cod_secc                 VARCHAR2(1);
  vl_cod_terr                 NUMBER(8);
  vl_cod_peri_proc            VARCHAR2(6);
  vl_val_nume_bole_desp       NUMBER(10);
  vl_fec_ingr                 DATE;
  vl_nombre                   varchar2(200);
  vl_cod_clie                 VARCHAR2(30);
  vl_telefono                 varchar2(100);
  vl_dir_br1                  varchar2(200);
  vl_des_urba                 varchar2(200);
  vl_des_dist                 varchar2(200);
  vl_des_prov                 varchar2(200);
  vl_des_dpto                 varchar2(200);
  vl_dir_br2                  varchar2(200);
  vl_num_tota_unid_recl       NUMBER(4);

TYPE boletaRecojoLineaRec IS RECORD(
  val_nume_bore           NUMBER(10),
  cod_clie		            varchar2(15),
  fec_ingr		            date,
  num_reco                VARCHAR2(10),
  num_tota_unid_recl      NUMBER(4),
  cod_regi		            varchar2(2),
  cod_zona                VARCHAR2(4),
  cod_terr                NUMBER(8),
  cod_secc                VARCHAR2(1),
  val_nume_bole_desp      NUMBER(10),
  cod_peri_proc           VARCHAR2(6),
  cod_prod		            varchar2(20),
  cod_anti		            varchar2(20),
  num_unid_recl		        number(4),
  des_oper		            varchar2(24),
  num_secu		            number(4),
  cod_peri		            varchar2(6),
  nombre                  varchar2(200),
  direccion		            varchar2(400),
  telefono                varchar2(100),
  des_urba                varchar2(200),
  des_dist                varchar2(200),
  des_prov                varchar2(200),
  des_dpto                varchar2(200),
  CODIGOUNICOVENTA	      number(10),
  des_cort		            varchar2(500),
  dir_br1                 varchar2(200),
  dir_br2                 varchar2(200),
  rnum			              number(10)
  );

TYPE boletaRecojoLineaRecCab IS TABLE OF boletaRecojoLineaRec;
boletaRecojoLineaRecord boletaRecojoLineaRecCab;

TYPE boletaRecojoLineaLongRec IS RECORD(
     tamanio_cursor           NUMBER(8)
);

TYPE boletaRecojoLineaLongRecCab IS TABLE OF boletaRecojoLineaLongRec;
boletaRecojoLineaLongRecord boletaRecojoLineaLongRecCab;

  archivoXML                  varchar2(4000);
  archivoXML1                 varchar2(1000);
  archivoXML2                 varchar2(3000);


 cursor c_boleta_recojo_linea_long is
 SELECT count(1) as tamanio_cursor
  FROM (SELECT A.*, ROWNUM RNUM
          FROM (SELECT CB.VAL_NUME_BORE,
                       MC.COD_CLIE,
                       DECODE(CB.NUM_RECO,
                              1,
                              CB.FEC_INGR,
                              2,
                              CB.FEC_ING2,
                              null) FEC_INGR,
                       DECODE(CB.NUM_RECO, 1, 'PRIMER ', 2, 'SEGUNDO ', '-') NUM_RECO,
                       CB.NUM_TOTA_UNID_RECL,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_REGI') as COD_REGI,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_ZONA') as COD_ZONA,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_TERR') as COD_TERR,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_SECC') as COD_SECC,
                       NVL(CB.VAL_NUME_BOLE_DESP, '0') VAL_NUME_BOLE_DESP,
                       CB.COD_PERI_PROC,
                       LB.COD_PROD,
                       LB.COD_ANTI,
                       LB.NUM_UNID_RECL - LB.NUM_UNID_ELIM NUM_UNID_RECL,
                       (SELECT (CASE val_param
                                 WHEN 'S' THEN
                                  LB.DES_OPER || ' (' || LB.cod_moti_devo || ')'
                                 ELSE
                                  LB.DES_OPER
                               END)
                          FROM sto_param_gener_occrr
                         WHERE cod_para = 'STO_IMP_MOT_BR') DES_OPER,
                       LB.NUM_SECU,
                       SP.COD_PERI,
                       TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_APE1) || ' ' ||
                       TRIM(MC.VAL_APE2) AS NOMBRE,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DIR_CLIE'),
                           '-') DIRECCION,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CB.CLIE_OID_CLIE,
                                                                  'TF'),
                           '-') AS TELEFONO,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_URBA'),
                           '-') AS DES_URBA,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DIST'),
                           '-') AS DES_DIST,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_PROV'),
                           '-') AS DES_PROV,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DPTO'),
                           '-') AS DES_DPTO,
                       DECODE(datoEnvioBR,
                              'C',
                              NVL(DECODE(LB.COPA_OID_PARA_GRAL,
                                         NULL,
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_MFACT(LB.TOFE_OID_TIPO_OFER,
                                                                                    LB.MAFA_OID_MATR_FACT,
                                                                                    LB.PROD_OID_PROD),
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_FICTI(LB.LOPA_OID_LOTE_PREM_ARTI,
                                                                                    LB.PANP_OID_PARA_NIVE_PREM,
                                                                                    LB.COPA_OID_PARA_GRAL,
                                                                                    LB.PROD_OID_PROD)),
                                  '-'),
                              RC.NUM_RECL) AS CODIGOUNICOVENTA,
                       /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT,*/
                       NVL(IMP_PKG_PROCE_LASER.imp_fn_desc_produ_br(psCodigoPais,LB.PROD_OID_PROD),'-') DES_CORT,
                       imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                   MC.OID_CLIE),
                                                                     '') AS DIR_BR1,
                       imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                    MC.OID_CLIE),
                                                                      '') AS DIR_BR2
                  FROM INT_REC_LINEA_BOREC LB,
                       INT_REC_CABEC_BOREC CB,
                       MAE_CLIEN           MC,
                       REC_CABEC_RECLA     RC,
                       CRA_PERIO           CRA,
                       SEG_PERIO_CORPO     SP,
                       INT_REC_CIERR_BOREC CIE
                 WHERE CB.COD_PAIS = psCodigoPais
                   AND LB.COD_PAIS = CB.COD_PAIS
                   AND CB.ESBO_OID_ESTA_BOR1 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'I')
                   AND CB.ESBO_OID_ESTA_BOR2 IS NULL
                   AND CB.NUM_RECO = 1
                   AND CB.IND_REGR_YOBE = 0
                   AND LB.IND_REGR_YOBE = 0
                   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
                   AND MC.OID_CLIE = CB.CLIE_OID_CLIE
                   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
                   AND CRA.PERI_OID_PERI = SP.OID_PERI
                   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
                   AND CB.IND_ENVI_YOBE = 1
                   AND LB.IND_ENVI_YOBE = 1
                   AND CB.IND_ENVI_XERO = 0
                   AND LB.IND_ENVI_XERO = 0
                   AND CB.COD_CABE_BORE = codigoCabeceraBoletaRecojo
                   AND cie.cod_regi =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_REGI')
                   AND cie.cod_zona =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_ZONA')
                   AND cie.cod_pais = cb.cod_pais
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
                   AND cie.cod_peri = psCodigoPeriodo
                UNION
                SELECT CB.VAL_NUME_BORE,
                       MC.COD_CLIE,
                       DECODE(CB.NUM_RECO,
                              1,
                              CB.FEC_INGR,
                              2,
                              CB.FEC_ING2,
                              null) FEC_INGR,
                       DECODE(CB.NUM_RECO, 1, 'PRIMER ', 2, 'SEGUNDO ', '-') NUM_RECO,
                       (CB.NUM_TOTA_UNID_RECL -
                       (SELECT SUM(LL.NUM_UNID_ELIM)
                           FROM INT_REC_LINEA_BOREC LL
                          WHERE LL.COD_CABE_BORE = CB.COD_CABE_BORE)) NUM_TOTA_UNID_RECL,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_REGI') as COD_REGI,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_ZONA') as COD_ZONA,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_TERR') as COD_TERR,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_SECC') as COD_SECC,
                       NVL(CB.VAL_NUME_BOLE_DESP, '0') VAL_NUME_BOLE_DESP,
                       CB.COD_PERI_PROC,
                       LB.COD_PROD,
                       LB.COD_ANTI,
                       LB.NUM_UNID_RECL - LB.NUM_UNID_ELIM NUM_UNID_RECL,
                       (SELECT (CASE val_param
                                 WHEN 'S' THEN
                                  LB.DES_OPER || ' (' || LB.cod_moti_devo || ')'
                                 ELSE
                                  LB.DES_OPER
                               END)
                          FROM sto_param_gener_occrr
                         WHERE cod_para = 'STO_IMP_MOT_BR') DES_OPER,
                       LB.NUM_SECU,
                       SP.COD_PERI,
                       TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_APE1) || ' ' ||
                       TRIM(MC.VAL_APE2) AS NOMBRE,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DIR_CLIE'),
                           '-') DIRECCION,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CB.CLIE_OID_CLIE,
                                                                  'TF'),
                           '-') AS TELEFONO,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_URBA'),
                           '-') AS DES_URBA,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DIST'),
                           '-') AS DES_DIST,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_PROV'),
                           '-') AS DES_PROV,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DPTO'),
                           '-') AS DES_DPTO,
                       DECODE(datoEnvioBR,
                              'C',
                              NVL(DECODE(LB.COPA_OID_PARA_GRAL,
                                         NULL,
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_MFACT(LB.TOFE_OID_TIPO_OFER,
                                                                                    LB.MAFA_OID_MATR_FACT,
                                                                                    LB.PROD_OID_PROD),
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_FICTI(LB.LOPA_OID_LOTE_PREM_ARTI,
                                                                                    LB.PANP_OID_PARA_NIVE_PREM,
                                                                                    LB.COPA_OID_PARA_GRAL,
                                                                                    LB.PROD_OID_PROD)),
                                  '-'),
                              RC.NUM_RECL) AS CODIGOUNICOVENTA,
                       /* NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT, */
                       NVL(IMP_PKG_PROCE_LASER.imp_fn_desc_produ_br(psCodigoPais,LB.PROD_OID_PROD),'-') DES_CORT,
                       imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                   MC.OID_CLIE),
                                                                     '') AS DIR_BR1,
                       imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                    MC.OID_CLIE),
                                                                      '') AS DIR_BR2
                  FROM INT_REC_LINEA_BOREC LB,
                       INT_REC_CABEC_BOREC CB,
                       MAE_CLIEN           MC,
                       REC_CABEC_RECLA     RC,
                       CRA_PERIO           CRA,
                       SEG_PERIO_CORPO     SP,
                       PED_SOLIC_CABEC     CON,
                       INT_REC_CIERR_BOREC CIE
                 WHERE CB.COD_PAIS = psCodigoPais
                   AND LB.COD_PAIS = CB.COD_PAIS
                   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
                   AND CB.ESBO_OID_ESTA_BOR2 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'NX')
                   AND CB.NUM_RECO = 2
                   AND CB.ESBO_OID_ESTA_BOR1 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'GE')
                   AND CB.IND_ENVI_YOBE = 1
                   AND CB.IND_REGR_YOBE = 1
                   AND LB.IND_ENVI_YOBE = 1
                   AND LB.IND_REGR_YOBE = 1
                   AND MC.OID_CLIE = CB.CLIE_OID_CLIE
                   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
                   AND CRA.PERI_OID_PERI = SP.OID_PERI
                   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
                   AND CON.CLIE_OID_CLIE = CB.CLIE_OID_CLIE
                   AND CON.PERD_OID_PERI =
                       GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                   AND CON.GRPR_OID_GRUP_PROC =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_GRUPO_PROCE('GP5')
                   AND CON.SOCA_OID_SOLI_CABE IS NULL
                   AND CON.TSPA_OID_TIPO_SOLI_PAIS IN
                       (INT_PKG_RECLA.GEN_FN_DEVUE_OID_TIPO_SOLPA('C1'))
                   AND (select count(*) from ped_solic_Cabec x 
                        where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE 
                        and x.TSPA_OID_TIPO_SOLI_PAIS <>  
                         (select tsp.oid_tipo_soli_pais
                          from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                          where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                          and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago                        
                   AND cie.cod_regi =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_REGI')
                   AND cie.cod_zona =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_ZONA')
                   AND cie.cod_pais = cb.cod_pais
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
                   AND cie.cod_peri = psCodigoPeriodo
                   AND CB.IND_ENVI_YOB2 = 1
                   AND LB.IND_ENVI_YOB2 = 1
                   AND CB.IND_ENVI_XER2 IS NULL
                   AND LB.IND_ENVI_XER2 IS NULL
                   AND CB.COD_CABE_BORE = codigoCabeceraBoletaRecojo
                UNION
                SELECT CB.VAL_NUME_BORE,
                       MC.COD_CLIE,
                       DECODE(CB.NUM_RECO,
                              1,
                              CB.FEC_INGR,
                              2,
                              CB.FEC_ING2,
                              null) FEC_INGR,
                       DECODE(CB.NUM_RECO, 1, 'PRIMER ', 2, 'SEGUNDO ', '-') NUM_RECO,
                       (CB.NUM_TOTA_UNID_RECL -
                       (SELECT SUM(LL.NUM_UNID_ELIM)
                           FROM INT_REC_LINEA_BOREC LL
                          WHERE LL.COD_CABE_BORE = CB.COD_CABE_BORE)) NUM_TOTA_UNID_RECL,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_REGI') as COD_REGI,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_ZONA') as COD_ZONA,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_TERR') as COD_TERR,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_SECC') as COD_SECC,
                       NVL(CB.VAL_NUME_BOLE_DESP, '0') VAL_NUME_BOLE_DESP,
                       CB.COD_PERI_PROC,
                       LB.COD_PROD,
                       LB.COD_ANTI,
                       LB.NUM_UNID_RECL - LB.NUM_UNID_ELIM NUM_UNID_RECL,
                       (SELECT (CASE val_param
                                 WHEN 'S' THEN
                                  LB.DES_OPER || ' (' || LB.cod_moti_devo || ')'
                                 ELSE
                                  LB.DES_OPER
                               END)
                          FROM sto_param_gener_occrr
                         WHERE cod_para = 'STO_IMP_MOT_BR') DES_OPER,
                       LB.NUM_SECU,
                       SP.COD_PERI,
                       TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_APE1) || ' ' ||
                       TRIM(MC.VAL_APE2) AS NOMBRE,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DIR_CLIE'),
                           '-') DIRECCION,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CB.CLIE_OID_CLIE,
                                                                  'TF'),
                           '-') AS TELEFONO,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_URBA'),
                           '-') AS DES_URBA,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DIST'),
                           '-') AS DES_DIST,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_PROV'),
                           '-') AS DES_PROV,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DPTO'),
                           '-') AS DES_DPTO,
                       DECODE(datoEnvioBR,
                              'C',
                              NVL(DECODE(LB.COPA_OID_PARA_GRAL,
                                         NULL,
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_MFACT(LB.TOFE_OID_TIPO_OFER,
                                                                                    LB.MAFA_OID_MATR_FACT,
                                                                                    LB.PROD_OID_PROD),
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_FICTI(LB.LOPA_OID_LOTE_PREM_ARTI,
                                                                                    LB.PANP_OID_PARA_NIVE_PREM,
                                                                                    LB.COPA_OID_PARA_GRAL,
                                                                                    LB.PROD_OID_PROD)),
                                  '-'),
                              RC.NUM_RECL) AS CODIGOUNICOVENTA,
                       /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT,*/
                       NVL(IMP_PKG_PROCE_LASER.imp_fn_desc_produ_br(psCodigoPais,LB.PROD_OID_PROD),'-') DES_CORT,
                       imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                   MC.OID_CLIE),
                                                                     '') AS DIR_BR1,
                       imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                    MC.OID_CLIE),
                                                                      '') AS DIR_BR2
                  FROM INT_REC_LINEA_BOREC LB,
                       INT_REC_CABEC_BOREC CB,
                       MAE_CLIEN           MC,
                       REC_CABEC_RECLA     RC,
                       CRA_PERIO           CRA,
                       SEG_PERIO_CORPO     SP,
                       INT_REC_CIERR_BOREC CIE
                 WHERE CB.COD_PAIS = psCodigoPais
                   AND LB.COD_PAIS = CB.COD_PAIS
                   AND CB.ESBO_OID_ESTA_BOR2 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'NX')
                   AND CB.NUM_RECO = 2
                   AND CB.ESBO_OID_ESTA_BOR1 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'GE')
                   AND CB.IND_ENVI_YOBE = 1
                   AND CB.IND_REGR_YOBE = 1
                   AND LB.IND_ENVI_YOBE = 1
                   AND LB.IND_REGR_YOBE = 1
                   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
                   AND MC.OID_CLIE = CB.CLIE_OID_CLIE
                   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
                   AND CRA.PERI_OID_PERI = SP.OID_PERI
                   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
                   AND CB.CLIE_OID_CLIE NOT IN
                       (SELECT CLIE_OID_CLIE
                          FROM PED_SOLIC_CABEC con1
                         WHERE con1.GRPR_OID_GRUP_PROC =
                               INT_PKG_RECLA.GEN_FN_DEVUE_OID_GRUPO_PROCE('GP5')
                           AND con1.PERD_OID_PERI =
                               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                           AND con1.SOCA_OID_SOLI_CABE IS NULL
                           AND con1.TSPA_OID_TIPO_SOLI_PAIS IN (INT_PKG_RECLA.GEN_FN_DEVUE_OID_TIPO_SOLPA('C1'))
                           AND (select count(*) from ped_solic_Cabec x 
                                where X.SOCA_OID_SOLI_CABE = con1.OID_SOLI_CABE 
                                and x.TSPA_OID_TIPO_SOLI_PAIS <>  
                                 (select tsp.oid_tipo_soli_pais
                                  from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                  where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                  and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago                                                    
                               )
                   AND (GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                         'OID_ZONA') IN
                       (SELECT ZZON_OID_ZONA
                           FROM FAC_CONTR_CIERR CI
                          WHERE CI.PERD_OID_PERI =
                                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                            AND CI.TCIE_OID_TIPO_CIER = 2) OR
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                         'OID_REGI') IN
                       (SELECT ZORG_OID_REGI
                           FROM fac_contr_cierr ci
                          WHERE ci.perd_oid_peri =
                                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                            AND ci.tcie_oid_tipo_cier = 1))
                   AND CB.IND_ENVI_YOB2 = 1
                   AND LB.IND_ENVI_YOB2 = 1
                   AND CB.IND_ENVI_XER2 IS NULL
                   AND LB.IND_ENVI_XER2 IS NULL
                   AND cie.cod_regi =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_REGI')
                   AND cie.cod_zona =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_ZONA')
                   AND cie.cod_pais = cb.cod_pais
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
                   AND cie.cod_peri = psCodigoPeriodo
                   AND CB.COD_CABE_BORE = codigoCabeceraBoletaRecojo) A
         WHERE A.NUM_UNID_RECL > 0
           AND A.NUM_TOTA_UNID_RECL > 0
           and ( rowHasta is null or ROWNUM <= rowHasta)

        )
 WHERE
 ( rowDesde is null or RNUM >= rowDesde);


 cursor c_boleta_recojo_linea is
 SELECT *
  FROM (SELECT A.*, ROWNUM RNUM
          FROM (SELECT CB.VAL_NUME_BORE,
                       MC.COD_CLIE,
                       DECODE(CB.NUM_RECO,
                              1,
                              CB.FEC_INGR,
                              2,
                              CB.FEC_ING2,
                              null) FEC_INGR,
                       DECODE(CB.NUM_RECO, 1, 'PRIMER ', 2, 'SEGUNDO ', '-') NUM_RECO,
                       CB.NUM_TOTA_UNID_RECL,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_REGI') as COD_REGI,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_ZONA') as COD_ZONA,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_TERR') as COD_TERR,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_SECC') as COD_SECC,
                       NVL(CB.VAL_NUME_BOLE_DESP, '0') VAL_NUME_BOLE_DESP,
                       CB.COD_PERI_PROC,
                       LB.COD_PROD,
                       LB.COD_ANTI,
                       LB.NUM_UNID_RECL - LB.NUM_UNID_ELIM NUM_UNID_RECL,
                       (SELECT (CASE val_param
                                 WHEN 'S' THEN
                                  LB.DES_OPER || ' (' || LB.cod_moti_devo || ')'
                                 ELSE
                                  LB.DES_OPER
                               END)
                          FROM sto_param_gener_occrr
                         WHERE cod_para = 'STO_IMP_MOT_BR') DES_OPER,
                       LB.NUM_SECU,
                       SP.COD_PERI,
                       TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_APE1) || ' ' ||
                       TRIM(MC.VAL_APE2) AS NOMBRE,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DIR_CLIE'),
                           '-') DIRECCION,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CB.CLIE_OID_CLIE,
                                                                  'TF'),
                           '-') AS TELEFONO,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_URBA'),
                           '-') AS DES_URBA,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DIST'),
                           '-') AS DES_DIST,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_PROV'),
                           '-') AS DES_PROV,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DPTO'),
                           '-') AS DES_DPTO,
                       DECODE(datoEnvioBR,
                              'C',
                              NVL(DECODE(LB.COPA_OID_PARA_GRAL,
                                         NULL,
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_MFACT(LB.TOFE_OID_TIPO_OFER,
                                                                                    LB.MAFA_OID_MATR_FACT,
                                                                                    LB.PROD_OID_PROD),
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_FICTI(LB.LOPA_OID_LOTE_PREM_ARTI,
                                                                                    LB.PANP_OID_PARA_NIVE_PREM,
                                                                                    LB.COPA_OID_PARA_GRAL,
                                                                                    LB.PROD_OID_PROD)),
                                  '-'),
                              RC.NUM_RECL) AS CODIGOUNICOVENTA,
                       /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT, */
                       NVL(IMP_PKG_PROCE_LASER.imp_fn_desc_produ_br(psCodigoPais,LB.PROD_OID_PROD),'-') DES_CORT,                           
                       imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                   MC.OID_CLIE),
                                                                     '') AS DIR_BR1,
                       imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                    MC.OID_CLIE),
                                                                      '') AS DIR_BR2
                  FROM INT_REC_LINEA_BOREC LB,
                       INT_REC_CABEC_BOREC CB,
                       MAE_CLIEN           MC,
                       REC_CABEC_RECLA     RC,
                       CRA_PERIO           CRA,
                       SEG_PERIO_CORPO     SP,
                       INT_REC_CIERR_BOREC CIE
                 WHERE CB.COD_PAIS = psCodigoPais
                   AND LB.COD_PAIS = CB.COD_PAIS
                   AND CB.ESBO_OID_ESTA_BOR1 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'I')
                   AND CB.ESBO_OID_ESTA_BOR2 IS NULL
                   AND CB.NUM_RECO = 1
                   AND CB.IND_REGR_YOBE = 0
                   AND LB.IND_REGR_YOBE = 0
                   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
                   AND MC.OID_CLIE = CB.CLIE_OID_CLIE
                   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
                   AND CRA.PERI_OID_PERI = SP.OID_PERI
                   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
                   AND CB.IND_ENVI_YOBE = 1
                   AND LB.IND_ENVI_YOBE = 1
                   AND CB.IND_ENVI_XERO = 0
                   AND LB.IND_ENVI_XERO = 0
                   AND CB.COD_CABE_BORE = codigoCabeceraBoletaRecojo
                   AND cie.cod_regi =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_REGI')
                   AND cie.cod_zona =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_ZONA')
                   AND cie.cod_pais = cb.cod_pais
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
                   AND cie.cod_peri = psCodigoPeriodo
                UNION
                SELECT CB.VAL_NUME_BORE,
                       MC.COD_CLIE,
                       DECODE(CB.NUM_RECO,
                              1,
                              CB.FEC_INGR,
                              2,
                              CB.FEC_ING2,
                              null) FEC_INGR,
                       DECODE(CB.NUM_RECO, 1, 'PRIMER ', 2, 'SEGUNDO ', '-') NUM_RECO,
                       (CB.NUM_TOTA_UNID_RECL -
                       (SELECT SUM(LL.NUM_UNID_ELIM)
                           FROM INT_REC_LINEA_BOREC LL
                          WHERE LL.COD_CABE_BORE = CB.COD_CABE_BORE)) NUM_TOTA_UNID_RECL,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_REGI') as COD_REGI,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_ZONA') as COD_ZONA,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_TERR') as COD_TERR,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_SECC') as COD_SECC,
                       NVL(CB.VAL_NUME_BOLE_DESP, '0') VAL_NUME_BOLE_DESP,
                       CB.COD_PERI_PROC,
                       LB.COD_PROD,
                       LB.COD_ANTI,
                       LB.NUM_UNID_RECL - LB.NUM_UNID_ELIM NUM_UNID_RECL,
                       (SELECT (CASE val_param
                                 WHEN 'S' THEN
                                  LB.DES_OPER || ' (' || LB.cod_moti_devo || ')'
                                 ELSE
                                  LB.DES_OPER
                               END)
                          FROM sto_param_gener_occrr
                         WHERE cod_para = 'STO_IMP_MOT_BR') DES_OPER,
                       LB.NUM_SECU,
                       SP.COD_PERI,
                       TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_APE1) || ' ' ||
                       TRIM(MC.VAL_APE2) AS NOMBRE,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DIR_CLIE'),
                           '-') DIRECCION,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CB.CLIE_OID_CLIE,
                                                                  'TF'),
                           '-') AS TELEFONO,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_URBA'),
                           '-') AS DES_URBA,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DIST'),
                           '-') AS DES_DIST,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_PROV'),
                           '-') AS DES_PROV,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DPTO'),
                           '-') AS DES_DPTO,
                       DECODE(datoEnvioBR,
                              'C',
                              NVL(DECODE(LB.COPA_OID_PARA_GRAL,
                                         NULL,
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_MFACT(LB.TOFE_OID_TIPO_OFER,
                                                                                    LB.MAFA_OID_MATR_FACT,
                                                                                    LB.PROD_OID_PROD),
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_FICTI(LB.LOPA_OID_LOTE_PREM_ARTI,
                                                                                    LB.PANP_OID_PARA_NIVE_PREM,
                                                                                    LB.COPA_OID_PARA_GRAL,
                                                                                    LB.PROD_OID_PROD)),
                                  '-'),
                              RC.NUM_RECL) AS CODIGOUNICOVENTA,
                       /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT,*/
                       NVL(IMP_PKG_PROCE_LASER.imp_fn_desc_produ_br(psCodigoPais,LB.PROD_OID_PROD),'-') DES_CORT,                           
                       imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                   MC.OID_CLIE),
                                                                     '') AS DIR_BR1,
                       imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                    MC.OID_CLIE),
                                                                      '') AS DIR_BR2
                  FROM INT_REC_LINEA_BOREC LB,
                       INT_REC_CABEC_BOREC CB,
                       MAE_CLIEN           MC,
                       REC_CABEC_RECLA     RC,
                       CRA_PERIO           CRA,
                       SEG_PERIO_CORPO     SP,
                       PED_SOLIC_CABEC     CON,
                       INT_REC_CIERR_BOREC CIE
                 WHERE CB.COD_PAIS = psCodigoPais
                   AND LB.COD_PAIS = CB.COD_PAIS
                   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
                   AND CB.ESBO_OID_ESTA_BOR2 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'NX')
                   AND CB.NUM_RECO = 2
                   AND CB.ESBO_OID_ESTA_BOR1 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'GE')
                   AND CB.IND_ENVI_YOBE = 1
                   AND CB.IND_REGR_YOBE = 1
                   AND LB.IND_ENVI_YOBE = 1
                   AND LB.IND_REGR_YOBE = 1
                   AND MC.OID_CLIE = CB.CLIE_OID_CLIE
                   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
                   AND CRA.PERI_OID_PERI = SP.OID_PERI
                   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
                   AND CON.CLIE_OID_CLIE = CB.CLIE_OID_CLIE
                   AND CON.PERD_OID_PERI =
                       GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                   AND CON.GRPR_OID_GRUP_PROC =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_GRUPO_PROCE('GP5')
                   AND CON.SOCA_OID_SOLI_CABE IS NULL
                   AND CON.TSPA_OID_TIPO_SOLI_PAIS IN
                       (INT_PKG_RECLA.GEN_FN_DEVUE_OID_TIPO_SOLPA('C1'))
                   AND (select count(*) from ped_solic_Cabec x 
                        where X.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE 
                        and x.TSPA_OID_TIPO_SOLI_PAIS <>  
                         (select tsp.oid_tipo_soli_pais
                          from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                          where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                          and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago                        
                   AND cie.cod_regi =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_REGI')
                   AND cie.cod_zona =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_ZONA')
                   AND cie.cod_pais = cb.cod_pais
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
                   AND cie.cod_peri = psCodigoPeriodo
                   AND CB.IND_ENVI_YOB2 = 1
                   AND LB.IND_ENVI_YOB2 = 1
                   AND CB.IND_ENVI_XER2 IS NULL
                   AND LB.IND_ENVI_XER2 IS NULL
                   AND CB.COD_CABE_BORE = codigoCabeceraBoletaRecojo
                UNION
                SELECT CB.VAL_NUME_BORE,
                       MC.COD_CLIE,
                       DECODE(CB.NUM_RECO,
                              1,
                              CB.FEC_INGR,
                              2,
                              CB.FEC_ING2,
                              null) FEC_INGR,
                       DECODE(CB.NUM_RECO, 1, 'PRIMER ', 2, 'SEGUNDO ', '-') NUM_RECO,
                       (CB.NUM_TOTA_UNID_RECL -
                       (SELECT SUM(LL.NUM_UNID_ELIM)
                           FROM INT_REC_LINEA_BOREC LL
                          WHERE LL.COD_CABE_BORE = CB.COD_CABE_BORE)) NUM_TOTA_UNID_RECL,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_REGI') as COD_REGI,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_ZONA') as COD_ZONA,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_TERR') as COD_TERR,
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                        'COD_SECC') as COD_SECC,
                       NVL(CB.VAL_NUME_BOLE_DESP, '0') VAL_NUME_BOLE_DESP,
                       CB.COD_PERI_PROC,
                       LB.COD_PROD,
                       LB.COD_ANTI,
                       LB.NUM_UNID_RECL - LB.NUM_UNID_ELIM NUM_UNID_RECL,
                       (SELECT (CASE val_param
                                 WHEN 'S' THEN
                                  LB.DES_OPER || ' (' || LB.cod_moti_devo || ')'
                                 ELSE
                                  LB.DES_OPER
                               END)
                          FROM sto_param_gener_occrr
                         WHERE cod_para = 'STO_IMP_MOT_BR') DES_OPER,
                       LB.NUM_SECU,
                       SP.COD_PERI,
                       TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_APE1) || ' ' ||
                       TRIM(MC.VAL_APE2) AS NOMBRE,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DIR_CLIE'),
                           '-') DIRECCION,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(CB.CLIE_OID_CLIE,
                                                                  'TF'),
                           '-') AS TELEFONO,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_URBA'),
                           '-') AS DES_URBA,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DIST'),
                           '-') AS DES_DIST,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_PROV'),
                           '-') AS DES_PROV,
                       NVL(GEN_PKG_GENER.GEN_FN_CLIEN_DATOS_OID(CB.CLIE_OID_CLIE,
                                                                'DES_DPTO'),
                           '-') AS DES_DPTO,
                       DECODE(datoEnvioBR,
                              'C',
                              NVL(DECODE(LB.COPA_OID_PARA_GRAL,
                                         NULL,
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_MFACT(LB.TOFE_OID_TIPO_OFER,
                                                                                    LB.MAFA_OID_MATR_FACT,
                                                                                    LB.PROD_OID_PROD),
                                         INT_PKG_RECLA.GEN_FN_DEVUE_COD_VENTA_FICTI(LB.LOPA_OID_LOTE_PREM_ARTI,
                                                                                    LB.PANP_OID_PARA_NIVE_PREM,
                                                                                    LB.COPA_OID_PARA_GRAL,
                                                                                    LB.PROD_OID_PROD)),
                                  '-'),
                              RC.NUM_RECL) AS CODIGOUNICOVENTA,
                       /*NVL(GEN_PKG_GENER.GEN_FN_DEVUELVE_DESCRIPCION(LB.PROD_OID_PROD,
                                                                     'MAE_PRODU',
                                                                     'es'),
                           '-') DES_CORT, */
                       NVL(IMP_PKG_PROCE_LASER.imp_fn_desc_produ_br(psCodigoPais,LB.PROD_OID_PROD),'-') DES_CORT,
                       imp_pkg_proce_laser.imp_fn_obtiene_text_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                   MC.OID_CLIE),
                                                                     '') AS DIR_BR1,
                       imp_pkg_proce_laser.imp_fn_obtiene_text2_direc(imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(psCodigoPais,
                                                                                                                    MC.OID_CLIE),
                                                                      '') AS DIR_BR2
                  FROM INT_REC_LINEA_BOREC LB,
                       INT_REC_CABEC_BOREC CB,
                       MAE_CLIEN           MC,
                       REC_CABEC_RECLA     RC,
                       CRA_PERIO           CRA,
                       SEG_PERIO_CORPO     SP,
                       INT_REC_CIERR_BOREC CIE
                 WHERE CB.COD_PAIS = psCodigoPais
                   AND LB.COD_PAIS = CB.COD_PAIS
                   AND CB.ESBO_OID_ESTA_BOR2 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'NX')
                   AND CB.NUM_RECO = 2
                   AND CB.ESBO_OID_ESTA_BOR1 =
                       INT_PKG_RECLA.GEN_FN_DEVUE_OID_ESTAD_BOREC(psCodigoPais,
                                                                  'GE')
                   AND CB.IND_ENVI_YOBE = 1
                   AND CB.IND_REGR_YOBE = 1
                   AND LB.IND_ENVI_YOBE = 1
                   AND LB.IND_REGR_YOBE = 1
                   AND CB.COD_CABE_BORE = LB.COD_CABE_BORE
                   AND MC.OID_CLIE = CB.CLIE_OID_CLIE
                   AND RC.OID_CABE_RECL = LB.CARE_OID_CABE_RECL
                   AND CRA.PERI_OID_PERI = SP.OID_PERI
                   AND CRA.OID_PERI = RC.PERD_OID_PERI_DOCU_REFE
                   AND CB.CLIE_OID_CLIE NOT IN
                       (SELECT CLIE_OID_CLIE
                          FROM PED_SOLIC_CABEC con1
                         WHERE con1.GRPR_OID_GRUP_PROC =
                               INT_PKG_RECLA.GEN_FN_DEVUE_OID_GRUPO_PROCE('GP5')
                           AND con1.PERD_OID_PERI =
                               GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                          GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                           AND con1.SOCA_OID_SOLI_CABE IS NULL
                           AND con1.TSPA_OID_TIPO_SOLI_PAIS IN(INT_PKG_RECLA.GEN_FN_DEVUE_OID_TIPO_SOLPA('C1'))
                           AND (select count(*) from ped_solic_Cabec x 
                                where X.SOCA_OID_SOLI_CABE = con1.OID_SOLI_CABE 
                                and x.TSPA_OID_TIPO_SOLI_PAIS <>  
                                 (select tsp.oid_tipo_soli_pais
                                  from ped_tipo_solic_pais tsp, ped_tipo_solic ts
                                  where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                                  and TS.COD_TIPO_SOLI = 'SCUF' ) )  <> 0   --- que no sea cargo de uso flexipago                             
                               )
                   AND (GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                         'OID_ZONA') IN
                       (SELECT ZZON_OID_ZONA
                           FROM FAC_CONTR_CIERR CI
                          WHERE CI.PERD_OID_PERI =
                                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                            AND CI.TCIE_OID_TIPO_CIER = 2) OR
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,
                                                         'OID_REGI') IN
                       (SELECT ZORG_OID_REGI
                           FROM fac_contr_cierr ci
                          WHERE ci.perd_oid_peri =
                                GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                                           GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                            AND ci.tcie_oid_tipo_cier = 1))
                   AND CB.IND_ENVI_YOB2 = 1
                   AND LB.IND_ENVI_YOB2 = 1
                   AND CB.IND_ENVI_XER2 IS NULL
                   AND LB.IND_ENVI_XER2 IS NULL
                   AND cie.cod_regi =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_REGI')
                   AND cie.cod_zona =
                       GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(CB.COD_CLIE,
                                                        'COD_ZONA')
                   AND cie.cod_pais = cb.cod_pais
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) >= trunc(cie.fec_cierr)
                   AND (select fec_proc
                          from bas_ctrl_fact
                         where sta_camp = 0
                           and ind_camp_act = 1) <= trunc(cie.fec_cie2)
                   AND cie.cod_peri = psCodigoPeriodo
                   AND CB.COD_CABE_BORE = codigoCabeceraBoletaRecojo) A
         WHERE A.NUM_UNID_RECL > 0
           AND A.NUM_TOTA_UNID_RECL > 0
           and ( rowHasta is null or ROWNUM <= rowHasta)

        )
 WHERE
 ( rowDesde is null or RNUM >= rowDesde);

    lsindgenebrUA     VARCHAR2(1);
    lsindgenebrOK     VARCHAR2(1);

    lncuentaUA        NUMBER := 0;
    lncuentacierrezonaregion NUMBER := 0;
    lnoidtipocierra        NUMBER;
    lnoidtipocierrar       NUMBER;

    lsactuacronobr    VARCHAR2(1);
    lsflagimpr        VARCHAR2(1);

    lb_clienteold         NUMBER(12) := NULL;
    lb_numrecoold         NUMBER(4);

    

 BEGIN

     EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_BOLET_RECOJ_XML';
--obtenemos los datos necesarios
     SELECT TO_NUMBER(VAL_PARA_PRIM)
         INTO sizePaginacion
         FROM IMP_PARAM_PROCE_IMPRE
        WHERE PRIM_COD_PROC = 'LAS'
          AND NOM_PARA_PRIM = 'numeroDetallesBoletaRecojo'
          AND EST_PARA_PRIM = '1';

        SELECT TO_NUMBER(VAL_PARAM)
          INTO actualDireccionBR
          FROM STO_PARAM_GENER_OCCRR
         WHERE COD_PAIS = psCodigoPais
           AND COD_PARA = 'STO_ACTUA_DIREC_BR';

     SELECT VAL_PARAM
       into datoEnvioBR
       FROM STO_PARAM_GENER_OCCRR A
      WHERE A.COD_PAIS = psCodigoPais
        AND A.COD_PARA = 'STO_DATO_ENVIA_BR';

      if datoEnvioBR = '' then
        datoEnvioBR := 'C';
      end if;

    lsactuacronobr := sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_ACTUA_CRONO_BR');
    lsindgenebrUA  := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'STO_IND_GENE_BR_UA'),'0');

    --- Si no se procesa las UA, todos los registros se dan como OK
    if lsindgenebrUA = '0' then
       lsindgenebrOK := '1';
    else
       lsindgenebrOK := '0';
    end if;

  lnoidtipocierra     := 2;
  lnoidtipocierrar    := 1;

--este es el primer cursor, el de mayor jerarquia, en el codigo java linea 2006
  OPEN c_boleta_recojo_cabecera;
  LOOP

  FETCH c_boleta_recojo_cabecera BULK COLLECT INTO  boletaRecojoCabeceraRecord LIMIT W_FILAS;
   IF boletaRecojoCabeceraRecord.COUNT > 0 THEN

      -- si verifica la generacion de BR para Chile
      if lsactuacronobr = '4' then

        FOR j IN boletaRecojoCabeceraRecord.FIRST .. boletaRecojoCabeceraRecord.LAST LOOP

            IF (lb_clienteold IS NULL OR
                lb_clienteold <> boletaRecojoCabeceraRecord(j).cod_clie) THEN

                ---- valida si zona o region esta en tabla de santiago
                select count(*) into lncuentaUA from INT_REC_GENE_BOREC
                where cod_pais = psCodigoPais
                and (cod_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(boletaRecojoCabeceraRecord(j).cod_clie, 'COD_REGI')  or
                     cod_zona = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(boletaRecojoCabeceraRecord(j).cod_clie, 'COD_ZONA'))
                and ind_reg = '1';

                --- Verifica el cierre de region a fin de generar las BR
                /*SELECT COUNT(*) INTO lncuentacierrezonaregion
                  FROM fac_contr_cierr ci
                 WHERE ci.perd_oid_peri = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo,
                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca),
                                                  GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal))
                   AND (
                        ---(ci.tcie_oid_tipo_cier = lnoidtipocierra AND
                        --- ci.zzon_oid_zona = boletarecojorecord(i).oid_zon) OR
                       (ci.tcie_oid_tipo_cier = lnoidtipocierrar AND
                       ci.zorg_oid_regi = GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(boletaRecojoCabeceraRecord(j).cod_clie, 'OID_REGI'))); */


                 SELECT COUNT(*) INTO lncuentacierrezonaregion 
                  FROM fac_progr_cierr x, bas_ctrl_fact y
                 WHERE X.FEC_CIER = Y.FEC_PROC and y.sta_camp = 0 and y.ind_camp_act = 1
                   and x.tip_cier = 'R' AND x.est_regi = '1' AND x.est_cier in ( 'A','P') 
                   and x.cod_regi = boletaRecojoCabeceraRecord(j).cod_regi
                   and X.CAM_PROC = psCodigoPeriodo;

                --- si la consultora es de santiago
                if lncuentaUA > 0 then
                   -- si tiene primer recojo se imprime todas las del cliente
                   if boletaRecojoCabeceraRecord(j).num_reco = 1 then
                      lsflagimpr := '1';
                   else
                      -- si no tiene primer recojo y es cierre de region se imprime
                      if lncuentacierrezonaregion > 0 then
                         lsflagimpr := '1';
                      else
                         lsflagimpr := '0';
                      end if;
                   end if;
                else
                      -- si no es de santiago y es cierre de region se imprime
                      if lncuentacierrezonaregion > 0 then
                         lsflagimpr := '1';
                      else
                         lsflagimpr := '0';
                      end if;
                end if;
                lb_clienteold := boletaRecojoCabeceraRecord(j).cod_clie;
            end if;
            boletaRecojoCabeceraRecord(j).flag_imp   := lsflagimpr;
        END LOOP;

      end if;

      FOR i IN boletaRecojoCabeceraRecord.FIRST .. boletaRecojoCabeceraRecord.LAST LOOP

         if boletaRecojoCabeceraRecord(i).flag_imp = '1' then

      --dentro del recorrido del cursor cada elemento es "boletaRecojoClientes"
      rowdesde:= null;
      rowhasta:= null;

      codigoCabeceraBoletaRecojo:= boletaRecojoCabeceraRecord(i).COD_CABE_BORE;
            --segundo cursor, mas interno "boletaRecojoLineaList"
      FOR v_linea IN c_boleta_recojo_linea_long LOOP
            boletaRecojoLineaTamanio:= v_linea.tamanio_cursor;
      END LOOP;

      if MOD(boletaRecojoLineaTamanio, sizePaginacion)!= 0 then
        totalPaginas:=boletaRecojoLineaTamanio/ sizePaginacion+1;
      else
        totalPaginas:=boletaRecojoLineaTamanio/ sizePaginacion;
      end if;

     select floor (totalPaginas)
      into totalPaginas
      from dual;

      existenPaginas:=true;
	    existeCabecera:=false;

	    rowDesde:= 1;
	    rowHasta:=sizePaginacion;
	    pagina:=1;

      WHILE (existenPaginas) LOOP
            --se vuelve a llamar al segundo cursor, el mas interno, pero con los datos
            --actualizados, se llama con cada iteracion actualizando los datos

            OPEN c_boleta_recojo_linea_long;
              LOOP

              FETCH c_boleta_recojo_linea_long BULK COLLECT INTO  boletaRecojoLineaLongRecord LIMIT W_FILAS;
               IF boletaRecojoLineaLongRecord.COUNT > 0 THEN
                  FOR k IN boletaRecojoLineaLongRecord.FIRST .. boletaRecojoLineaLongRecord.LAST LOOP
                       boletaRecojoLineaTamanio:= boletaRecojoLineaLongRecord(k).tamanio_cursor;
                  END LOOP;
               END IF;
               EXIT WHEN c_boleta_recojo_linea_long%NOTFOUND;
                END LOOP;
             CLOSE c_boleta_recojo_linea_long;

              OPEN c_boleta_recojo_linea;
              LOOP

              FETCH c_boleta_recojo_linea BULK COLLECT INTO  boletaRecojoLineaRecord LIMIT W_FILAS;
               IF boletaRecojoLineaRecord.COUNT > 0 THEN
                  FOR j IN boletaRecojoLineaRecord.FIRST .. boletaRecojoLineaRecord.LAST LOOP


                  if not existeCabecera then
                      vl_val_nume_bore:=boletaRecojoLineaRecord(j).val_nume_bore;
                      vl_num_reco:=boletaRecojoLineaRecord(j).num_reco;
                      vl_cod_zona:=boletaRecojoLineaRecord(j).cod_zona;
                      vl_cod_secc:=boletaRecojoLineaRecord(j).cod_secc;
                      vl_cod_terr:=boletaRecojoLineaRecord(j).cod_terr;
                      vl_cod_peri_proc:=boletaRecojoLineaRecord(j).cod_peri_proc;
                      vl_val_nume_bole_desp:=boletaRecojoLineaRecord(j).val_nume_bole_desp;
                      vl_fec_ingr:=boletaRecojoLineaRecord(j).fec_ingr;
                      vl_nombre:=boletaRecojoLineaRecord(j).nombre;
                      vl_cod_clie:=boletaRecojoLineaRecord(j).cod_clie;
                      vl_telefono:=boletaRecojoLineaRecord(j).telefono;
                      vl_dir_br1:=boletaRecojoLineaRecord(j).dir_br1;
                      vl_des_dist:=boletaRecojoLineaRecord(j).des_dist;
                      vl_des_prov:=boletaRecojoLineaRecord(j).des_prov;
                      vl_des_dpto:=boletaRecojoLineaRecord(j).des_dpto;
                      vl_des_urba:=boletaRecojoLineaRecord(j).des_urba;
                      vl_dir_br2:=boletaRecojoLineaRecord(j).dir_br2;
                      vl_num_tota_unid_recl:=boletaRecojoLineaRecord(j).num_tota_unid_recl;
                      existeCabecera:=true;
                  end if;

                  if boletaRecojoLineaTamanio>0 then
                      if datoEnvioBR = 'C' then
                         archivoXML2:=archivoXML2||'<txt>'||trim(to_char(boletaRecojoLineaRecord(j).codigounicoventa,'00000'))||'<t />'||boletaRecojoLineaRecord(j).des_cort||'<tr />'||to_char(boletaRecojoLineaRecord(j).num_unid_recl)||'<t />'||boletaRecojoLineaRecord(j).des_oper||'<tc />'||boletaRecojoLineaRecord(j).cod_peri||'<t />'||boletaRecojoLineaRecord(j).cod_prod||'</txt>';
                      else   
                         archivoXML2:=archivoXML2||'<txt>'||trim(to_char(boletaRecojoLineaRecord(j).codigounicoventa))||'<t />'||boletaRecojoLineaRecord(j).des_cort||'<tr />'||to_char(boletaRecojoLineaRecord(j).num_unid_recl)||'<t />'||boletaRecojoLineaRecord(j).des_oper||'<tc />'||boletaRecojoLineaRecord(j).cod_peri||'<t />'||boletaRecojoLineaRecord(j).cod_prod||'</txt>';
                      end if;                    
                  end if;

                 END LOOP;
          END IF;
           EXIT WHEN c_boleta_recojo_linea%NOTFOUND;
        END LOOP;
        CLOSE c_boleta_recojo_linea;

                    if boletaRecojoLineaTamanio= 0 then
                    existenPaginas:=false;
                    else
                    if actualDireccionBR=1 then
                        --archivoBR
                            archivoXML1:='<frmbrec><blqdata><numbrec>BR '||to_char(vl_val_nume_bore)||'</numbrec><numrecojo>'||vl_num_reco||'</numrecojo><zona>'||vl_cod_zona||'</zona><territorio>'||vl_cod_secc||to_char(vl_cod_terr)||'</territorio><campana>'||vl_cod_peri_proc||'</campana><factura>'||to_char(vl_val_nume_bole_desp)||'</factura><femision>'||to_char(trunc(vl_fec_ingr),'YYYY-MM-DD HH24:MI:SS.')||'0'||'</femision><nombre>'||vl_nombre||'</nombre><codconsultora>'||vl_cod_clie||'</codconsultora><telefono>'||vl_telefono||'</telefono><direccion1>'||vl_dir_br1||'</direccion1><direccion2>'||vl_des_urba||'</direccion2><direccion3>'||vl_dir_br2||'</direccion3><totalunidades>'||to_char(vl_num_tota_unid_recl)||'</totalunidades><cpg>'||to_char(pagina)||'</cpg><tpg>'||to_char(totalPaginas)||'</tpg></blqdata><detalle>';
                            archivoXML:=archivoXML1||archivoXML2||'</detalle></frmbrec>';
                     else
                            archivoXML1:='<frmbrec><blqdata><numbrec>BR '||to_char(vl_val_nume_bore)||'</numbrec><numrecojo>'||vl_num_reco||'</numrecojo><zona>'||vl_cod_zona||'</zona><seccion>'||vl_cod_secc||'</seccion><territorio>'||to_char(vl_cod_terr)||'</territorio><campana>'||vl_cod_peri_proc||'</campana><factura>'||to_char(vl_val_nume_bole_desp)||'</factura><femision>'||to_char(trunc(vl_fec_ingr),'YYYY-MM-DD HH24:MI:SS.')||'0'||'</femision><nombre>'||vl_nombre||'</nombre><codconsultora>'||vl_cod_clie||'</codconsultora><telefono>'||vl_telefono||'</telefono><direccion1>'||vl_dir_br1||'</direccion1><direccion2>'||vl_des_urba||'</direccion2><direccion3>'||vl_des_dist||'/'||vl_des_prov||'/'||vl_des_dpto||'</direccion3><totalunidades>'||to_char(vl_num_tota_unid_recl)||'</totalunidades><cpg>'||to_char(pagina)||'</cpg><tpg>'||to_char(totalPaginas)||'</tpg></blqdata><detalle>';
                            archivoXML:=archivoXML1||archivoXML2||'</detalle></frmbrec>';
                     end if;

                     INSERT INTO INT_BOLET_RECOJ_XML
                        ( COD_CONS
                        , VAL_CORR
                        , XML_BOLE_RECO
                        , COD_CABE_BORE)
                      VALUES
                        ( boletaRecojoCabeceraRecord(i).cod_clie
                        , pagina
                        , archivoXML
                        , codigoCabeceraBoletaRecojo);
                     pagina:=pagina+1;
                     rowDesde:=rowDesde+sizePaginacion;
                     rowHasta:=rowHasta+sizePaginacion;
                     archivoXML:='';
                     archivoXML1:='';
                     archivoXML2:='';
                      end if;
      END LOOP;

          END IF;

      END LOOP;
    END IF;

    EXIT WHEN c_boleta_recojo_cabecera%NOTFOUND;
  END LOOP;
 CLOSE c_boleta_recojo_cabecera;

    IMP_PKG_PROCE_COMPA.IMP_PR_GENER_XML_BOLET_RECOJ();

    EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_XML_BOREC_ORA: '||ls_sqlerrm);

END IMP_PR_GENER_XML_BOREC_ORA;

/***************************************************************************
Descripcion       : Envia la informacion para SMS de la boleta del PRIMER recojo
Fecha Creacion    : 21/11/2014
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
  PROCEDURE IMP_PR_GENER_CSV_PRIME_REC
  ( pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2)
IS
   ln_sqlcode                        NUMBER(10);
   ls_sqlerrm                        VARCHAR2(2500);
   CURSOR c_interfaz IS
    select 'BELCORP. Hola '|| NVL(c.val_nom1, c.val_nom2) ||
           ' se ha generado boleta PRIMER recojo de tu reclamo atendido por favor hacer entrega de los productos al transportador.' AS nombre,
           SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(b.CLIE_OID_CLIE,
                                                               'TM'),
                  1,
                  10) AS celular,
                  c.cod_clie
      from (select distinct rab.cod_Cabe_bore from INT_BOLET_RECOJ_XML rab) a,
           INT_REC_CABEC_BOREC b,
           mae_clien c,
           seg_pais s
     where A.cod_Cabe_bore = B.COD_CABE_BORE
       and B.CLIE_OID_CLIE = C.OID_CLIE
       and b.num_reco = 1
       and c.pais_oid_pais = s.oid_pais
       and b.cod_pais = s.cod_pais
       and s.cod_pais = psCodigoPais;

   TYPE interfazRec IS RECORD
    (
       nombre      varchar2(200),
       celular     varchar2(50),
       codclie     varchar(50)
    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

   lsVersion    FLX_PARAM.VAL_PARA%TYPE;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz;
        LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                    gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
                    lbabrirutlfile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).celular                ||';'||
                                      interfazRecord(x).nombre                 ||';'||
                                      interfazRecord(x).codclie;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_CSV_PRIME_REC: '||ls_sqlerrm);

END IMP_PR_GENER_CSV_PRIME_REC;

/***************************************************************************
Descripcion       : Envia la informacion para SMS de la boleta del SEGUNDO recojo
Fecha Creacion    : 21/11/2014
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
  PROCEDURE IMP_PR_GENER_CSV_SEGUN_REC
  ( pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2)
IS
   ln_sqlcode                        NUMBER(10);
   ls_sqlerrm                        VARCHAR2(2500);
   CURSOR c_interfaz IS
    select 'BELCORP. Hola '|| NVL(c.val_nom1, c.val_nom2) ||
           ' se ha generado boleta SEGUNDO recojo de tu reclamo atendido por favor hacer entrega de los productos al transportador.' AS nombre,
           SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(b.CLIE_OID_CLIE,
                                                               'TM'),
                  1,
                  10) AS celular,
                  c.cod_clie
      from (select distinct rab.cod_Cabe_bore from INT_BOLET_RECOJ_XML rab) a,
           INT_REC_CABEC_BOREC b,
           mae_clien c,
           seg_pais s
     where A.cod_Cabe_bore = B.COD_CABE_BORE
       and B.CLIE_OID_CLIE = C.OID_CLIE
       and b.num_reco = 2
       and c.pais_oid_pais = s.oid_pais
       and b.cod_pais = s.cod_pais
       and s.cod_pais = psCodigoPais;

   TYPE interfazRec IS RECORD
    (
       nombre      varchar2(200),
       celular     varchar2(50),
       codclie     varchar2(50)
    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

   lsVersion    FLX_PARAM.VAL_PARA%TYPE;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz;
        LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                    gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
                    lbabrirutlfile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).celular                ||';'||
                                      interfazRecord(x).nombre                 ||';'||
                                      interfazRecord(x).codclie;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_CSV_SEGUN_REC: '||ls_sqlerrm);

END IMP_PR_GENER_CSV_SEGUN_REC;

/***************************************************************************
Descripcion       : Envia la informacion para SMS de la boleta del SEGUNDO recojo
                    NO EXITOSO
Fecha Creacion    : 21/11/2014
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/
  PROCEDURE IMP_PR_GENER_CSV_SEGNE_REC
  ( pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2)
IS
   ln_sqlcode                        NUMBER(10);
   ls_sqlerrm                        VARCHAR2(2500);
   CURSOR c_interfaz IS
    select 'BELCORP. Hola '|| NVL(c.val_nom1, c.val_nom2) ||
           ' se ha generado cobro a tu cuenta por postventa atendido  cuyo SEGUNDO recojo no fue exitoso.' AS nombre,
           SUBSTR(FIN_PKG_GENER.FIN_FN_OBTIE_NUMER_TELEF_CLIEN(b.CLIE_OID_CLIE,
                                                               'TM'),
                  1,
                  10) AS celular,
                  c.cod_clie
      from (select distinct rab.cod_Cabe_bore
                from REC_AUDIT_BOREC  rab
                  where rab.ind_envi_sms = '1') a,
           INT_REC_CABEC_BOREC b,
           mae_clien c,
           seg_pais s
     where A.cod_Cabe_bore = B.COD_CABE_BORE
       and B.CLIE_OID_CLIE = C.OID_CLIE
       and c.pais_oid_pais = s.oid_pais
       and b.cod_pais = s.cod_pais
       and s.cod_pais = psCodigoPais;

   TYPE interfazRec IS RECORD
    (
       nombre      varchar2(200),
       celular     varchar2(50),
       codclie     varchar2(50)
    );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

   lsVersion    FLX_PARAM.VAL_PARA%TYPE;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    OPEN c_interfaz;
        LOOP
             FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                    gen_pkg_inter_archi.gen_pr_inici_inter(psCodigoPais, pscodigosistema, pscodigointerfaz, psnombrearchivo,
                                                  lsdirtempo, lsnombrearchivo, v_handle);
                    lbabrirutlfile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                          lsLinea :=  interfazRecord(x).celular                ||';'||
                                      interfazRecord(x).nombre                 ||';'||
                                      interfazRecord(x).codclie;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

        update REC_AUDIT_BOREC
           set FEC_ENVI_SMS = sysdate, IND_ENVI_SMS = '2'
         where IND_ENVI_SMS = '1';


    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
         GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR IMP_PR_GENER_CSV_SEGNE_REC: '||ls_sqlerrm);

END IMP_PR_GENER_CSV_SEGNE_REC;

/***************************************************************************
Descripcion       : Genera archivo de texto de Interfaz de GENERAR BAN2 DOMINICANA
Fecha Creacion    : 01/06/2015
Autor             : Gonzalo Javier Huertas Agurto
***************************************************************************/

PROCEDURE IMP_PR_GENER_BAN_DOMIN
(
  pscodigopais     VARCHAR2,
  pscodigosistema  VARCHAR2,
  pscodigointerfaz VARCHAR2,
  psnombrearchivo  VARCHAR2,
  psnumerolote     VARCHAR2,
  pscodigoperiodo  VARCHAR2,
  pscodigomarca    VARCHAR2,
  pscodigocanal    VARCHAR2
) IS

   ln_sqlcode                        NUMBER(10);
   gc_cod_modu                       CONSTANT CHAR(3):='CCC';
     -- Cabecera
  lc_tipo_regi_cabe                CHAR(2):='01';
  lc_nom_empr                      CHAR(32):= RPAD('TRANSBEL',32,' ');
  lc_cant_regi                     CHAR(15);
  lc_mont_regi                     CHAR(15);
  lc_val_fech_hora                 CHAR(19):= RPAD(TO_CHAR(SYSDATE,'YYYY-MM-DD hh:mm:ss'),19,' ');
  lc_fill_cabe                     CHAR(67):= RPAD(' ',67,' ');
  lc_line_cabe                     CHAR(189);

  -- Detalle
  lc_tipo_regi_deta                CHAR(2):='02';
  lv_cod_clie                      CHAR(35);
  lv_imp_bala                      CHAR(14);
  lc_imp_itbs_impu                 CHAR(14):='00000000000.00';
  lc_imp_otro_impu                 CHAR(14):='00000000000.00';
  lc_val_fech_venc                 CHAR(19):=RPAD(TO_CHAR(SYSDATE + 21,'YYYY-MM-DD hh:mm:ss'),19,' ');
  lv_nom_clie                      CHAR(25);
  lv_num_docu_iden                 CHAR(11);
  lv_line_deta                     CHAR(134);

  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_handle                        utl_file.file_type;
  lv_dir_ensa_mail                 fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch_mail                 fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_desc_erro                     VARCHAR2(4000);
  lv_cant_regi                     NUMBER(9):=0;
  lv_mont_tota                     NUMBER(15,2):=0;
  lv_cod_inte_sald_redo            fin_proce_modul.cod_inte%TYPE :='SALRD';

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE;

  lv_cod_erro                      VARCHAR2(4000);
  lv_des_erro                      VARCHAR2(4000);
   
   CURSOR c_deta
   IS
    SELECT
     s.cod_clie,
     s.nom_clie,
     s.num_docu_iden,
     s.imp_deud_tota
    FROM ccc_int_gener_saldo_repdo s;
    
  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo      bas_inter.dir_temp%TYPE;
  w_filas         NUMBER := 1000;
  v_handle        utl_file.file_type;

  lslinea         VARCHAR2(1000);

  lsnombrearchivo VARCHAR2(50);

BEGIN

  /* Procedimiento inicial para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
          psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

  /* Generando Archivo de Texto (Detalle) */
   DELETE FROM ccc_int_gener_saldo_repdo;
    
   INSERT INTO ccc_int_gener_saldo_repdo
     SELECT mc.cod_clie,
            mc.val_ape1 || '/' || mc.val_ape2 || '/' || mc.val_nom1 nom_clie,
            mci.num_docu_iden,
            NVL(mc.sal_deud_ante, 0)
       FROM mae_clien mc, mae_clien_ident mci
      WHERE mc.oid_clie = mci.clie_oid_clie
        AND mci.val_iden_docu_prin = 1
        AND mc.sal_deud_ante > 0;

    SELECT COUNT(*), SUM(imp_deud_tota)
      INTO lv_cant_regi, lv_mont_tota
      FROM ccc_int_gener_saldo_repdo;
      
      lc_cant_regi := SUBSTR(LPAD(lv_cant_regi,15,'0'),1,15);
      lc_mont_regi := SUBSTR(LPAD(TRIM(TO_CHAR(lv_mont_tota,'000000000000.00')),15,'0'),1,15);

  lc_line_cabe := lc_tipo_regi_cabe ||
                  lc_nom_empr ||
                  lc_cant_regi ||
                  lc_mont_regi ||
                  lc_val_fech_hora ||
                  lc_fill_cabe ||
                  lc_line_cabe;

  utl_file.put_line(v_handle,lc_line_cabe);     
  
    FOR v_deta in c_deta LOOP

   lv_cod_clie :=  SUBSTR(RPAD(v_deta.cod_clie,35,' '),1,35);
   lv_nom_clie :=  SUBSTR(RPAD(v_deta.nom_clie,25,' '),1,25);
   lv_imp_bala :=  SUBSTR(LPAD(TRIM(TO_CHAR(v_deta.imp_deud_tota,'00000000000.00')),14,'0'),1,14);
   lv_num_docu_iden := SUBSTR(LPAD(v_deta.num_docu_iden,11,'0'),1,11);

   lv_line_deta := lc_tipo_regi_deta ||
                  lv_cod_clie ||
                  lv_imp_bala ||
                  lc_imp_itbs_impu ||
                  lc_imp_otro_impu ||
                  lc_val_fech_venc ||
                  lv_nom_clie ||
                  lv_num_docu_iden;

   utl_file.put_line(v_handle,lv_line_deta);

  END LOOP;

  utl_file.fclose(v_handle);
  
  /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
  RETURN;
EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 1000);
    raise_application_error(-20123,
                            'ERROR IMP_PR_GENER_BAN_DOMIN: ' ||
                            ls_sqlerrm);
END IMP_PR_GENER_BAN_DOMIN;


END;
/
