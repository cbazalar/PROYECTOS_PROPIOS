CREATE OR REPLACE PACKAGE MAV_PKG_PROCE IS

  /* Declaracion de Variables */
  LN_SQLCODE NUMBER(10);
  LS_SQLERRM VARCHAR2(150);
  W_FILAS             NUMBER := 5000 ;

  TIPO_CONS_REST_TODO                   VARCHAR(12) := 'TODO';
  TIPO_CONS_REST_UNIDAD_ADMIN           VARCHAR(12) := 'UAS';
  TIPO_CONS_REST_TIPO_CLASIF            VARCHAR(12) := 'TCL';
  TIPO_CONS_REST_CUMPLEANHOS            VARCHAR(12) := 'CUM';
  TIPO_CONS_REST_ANIVERSARIO            VARCHAR(12) := 'ANV';
  TIPO_CONS_REST_EDAD                   VARCHAR(12) := 'EDAD';
  TIPO_CONS_REST_PED_SUP_MON            VARCHAR(12) := 'PSM';
  TIPO_CONS_REST_PED_SUP_MON_MA         VARCHAR(12) := 'PSMM';
  TIPO_CONS_REST_PED_SUP_MON_UN         VARCHAR(12) := 'PSMUN';
  TIPO_CONS_REST_PED_SUP_MON_NE         VARCHAR(12) := 'PSMN';
  TIPO_CONS_REST_PED_SUP_MON_CA         VARCHAR(12) := 'PSMC';
  TIPO_CONS_REST_PED_NSUP_MON           VARCHAR(12) := 'PNSM';
  TIPO_CONS_REST_PED_NSUP_MON_MA        VARCHAR(12) := 'PNSMM';
  TIPO_CONS_REST_PED_NSUP_MON_UN        VARCHAR(12) := 'PNSMUN';
  TIPO_CONS_REST_PED_NSUP_MON_NE        VARCHAR(12) := 'PNSMN';
  TIPO_CONS_REST_PED_NSUP_MON_CA        VARCHAR(12) := 'PNSMC';
  TIPO_CONS_REST_CON_INS_NUEV_DU        VARCHAR(12) := 'NUDP';
  TIPO_CONS_REST_LISTA_CONS             VARCHAR(12) := 'LCL';
  TIPO_CONS_REST_ESTATUS                VARCHAR(12) := 'ESCL';
  TIPO_CONS_REST_MON_FLX                VARCHAR(12) := 'MTFLX';
  TIPO_CONS_REST_DEMAN_ANTIC            VARCHAR(12) := 'DEA';


  /**************************************************************************
  Descripcion        : Recupera el Gerente de Reemplazo
  Fecha Creacion     : 03/01/2011
  Parametros Entrada :
             pnOidRegion : Oid Periodo
             psCodigoMarca : Codigo Marca
             psCodigoCanal : Codigo Canal
             psCodigoPeriodo : Codigo Periodo

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION FN_MAV_OBTEN_GEREN_REGIO(pnOidRegion        NUMBER,
                                    psCodigoMarca      VARCHAR2,
                                    psCodigoCanal      VARCHAR2,
                                    psCodigoPeriodo    VARCHAR2)
  RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Permite registrar reemplazo de Gerentes a Regiones  activa
                      y que no cuentan con Gerente asignado para la campaña, con la
                      finalidad de que no se deje  de despachar los materiales de la campaña a estas regiones.
  Fecha Creacion    : 03/01/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    psCodigoMarca   :  Codigo Marca
    psCodigoCanal     : Codigo Canal
    psCodigoPeriodo    : Codigo Periodo
    pnOidRegion    :  oid Region
    psCodigoCliente    :  codigo Cliente
    psCodigoUsuario    :  codigo Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_ASIGN_GEREN_REGIO(psCodigoPais      IN VARCHAR2,
                                     psCodigoMarca     IN VARCHAR2,
                                     psCodigoCanal     IN VARCHAR2,
                                     psCodigoPeriodo   IN VARCHAR2,
                                     pnOidRegion       IN NUMBER,
                                     psCodigoCliente   IN VARCHAR2,
                                     psCodigoUsuario   IN VARCHAR2);

  /**************************************************************************
  Descripcion        : Recupera el Gerente de Zona de Reemplazo
  Fecha Creacion     : 04/01/2011
  Parametros Entrada :
             pnOidZona : Oid Zona
             psCodigoMarca : Codigo Marca
             psCodigoCanal : Codigo Canal
             psCodigoPeriodo : Codigo Periodo

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION FN_MAV_OBTEN_GEREN_ZONA(pnOidZona          NUMBER,
                                   psCodigoMarca      VARCHAR2,
                                   psCodigoCanal      VARCHAR2,
                                   psCodigoPeriodo    VARCHAR2)
  RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Permite registrar reemplazo de Gerentes a Zonas  activas y
                      que no cuentan con Gerente asignado para la campaña, con la
                      finalidad de que no se deje  de despachar los materiales de la campaña a estas zonas.
  Fecha Creacion    : 04/01/2011
  Parametros Entrada:
    psCodigoPais     :  Codigo Pais
    psCodigoMarca   :  Codigo Marca
    psCodigoCanal     : Codigo Canal
    psCodigoPeriodo    : Codigo Periodo
    pnOidRegion    :  oid Region
    pnOidZona    :  oid Zona
    psCodigoCliente    :  codigo Cliente
    psCodigoUsuario    :  codigo Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_ASIGN_GEREN_ZONA(psCodigoPais      IN VARCHAR2,
                                    psCodigoMarca     IN VARCHAR2,
                                    psCodigoCanal     IN VARCHAR2,
                                    psCodigoPeriodo   IN VARCHAR2,
                                    pnOidRegion       IN NUMBER,
                                    pnOidZona         IN NUMBER,
                                    psCodigoCliente   IN VARCHAR2,
                                    psCodigoUsuario   IN VARCHAR2);

  /**************************************************************************
  Descripcion        : Recupera el Cargo de Gerente
  Fecha Creacion     : 04/01/2011
  Parametros Entrada :
             pnOidCliente : Oid Cliente

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION FN_MAV_OBTEN_CARGO_GEREN(pnOidCliente    NUMBER)
  RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Recupera el Gerente de Region de Reemplazo
  Fecha Creacion     : 06/01/2011
  Parametros Entrada :
             psCodigoMarca : Codigo Marca
             psCodigoCanal : Codigo Canal
             psCodigoPeriodo : Codigo Periodo
             psCodigoRegion : Codigo Region
             psCodigoZona : Codigo Zona

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION FN_MAV_OBTEN_GEREN_HISTO(psCodigoMarca      VARCHAR2,
                                    psCodigoCanal      VARCHAR2,
                                    psCodigoPeriodo    VARCHAR2,
                                    psCodigoRegion     VARCHAR2,
                                    psCodigoZona       VARCHAR2)
  RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Recupera la Region/Zona de la Gerente
  Fecha Creacion     : 06/01/2011
  Parametros Entrada :
             pnOidCliente : Oid Cliente

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION FN_MAV_OBTEN_REGIO_ZONA(pnOidCliente    NUMBER)
  RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Recupera datos del Cliente
  Fecha Creacion     : 07/01/2011
  Parametros Entrada :
             pnOidCliente : Oid Cliente
             psTipoDato : Tipo de Dato

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION FN_MAV_OBTEN_DATOS_CLIEN(pnOidCliente    NUMBER,
                                    psTipoDato      VARCHAR2)
  RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Permite registrar numero de Cajas a una solicitud de Pedido
  Fecha Creacion    : 10/01/2011
  Parametros Entrada:
    pnOidSolicitud     :  Oid Solicitud
    pnTotalCajas   :  Total de Cajas
    psCodigoUsuario    :  codigo Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_ASIGN_NUMER_CAJAS(pnOidSolicitud      IN VARCHAR2,
                                     pnNumeroCajas       IN NUMBER,
                                     psCodigoUsuario     IN VARCHAR2);


  /**************************************************************************
  Descripcion        : Recupera lista de Numeros de Caja
  Fecha Creacion     : 12/01/2011
  Parametros Entrada :
             pnTotalCajas : Total de Cajas

  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION FN_MAV_OBTEN_LISTA_CAJAS(pnTotalCajas   NUMBER)
  RETURN split_tbl PIPELINED;

  /**************************************************************************
  Descripcion       : Validacion de Carga Masiva de MAV

  Fecha Creacion    : 02/01/2013
  Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoUsuario  :  Codigo de Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_VALID_CARGA_MASIV(psCodigoPais          VARCHAR2,
                                     psCodigoUsuario       VARCHAR2);

  /**************************************************************************
  Descripcion       : Actualizacion de Carga Masiva de MAV

  Fecha Creacion    : 02/01/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoUsuario  :  Codigo de Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_ACTUA_CARGA_MASIV(psCodigoPais          VARCHAR2,
                                     psCodigoUsuario       VARCHAR2);

  /**************************************************************************
  Descripcion       : Valida que el producto exista en la Matriz de Precios

  Fecha Creacion    : 21/01/2013
  Parametros Entrada:
    pnOidPais         :  oid Pais
    pnOidAcceso       :  oid Acceso
    pnOidSubAcceso    :  oid SubAcceso
    pnOidPeriodo      :  oid Periodo
    pnOidTipoOferta   :  oid Tipo Oferta
    pnOidCicloVida    :  oid Ciclo Vida
    pnOidFormaPago    :  oid Forma Pago
    pnOidFormaCobro   :  oid Forma Cobro
    pnPrecio          :  oid Precio

  Autor             : Sergio Apaza

  ***************************************************************************/
  FUNCTION MAV_FN_VALID_PRODU(pnOidPais            NUMBER,
                              pnOidAcceso          NUMBER,
                              pnOidSubAcceso       NUMBER,
                              pnOidPeriodo         NUMBER,
                              pnOidTipoOferta      NUMBER,
                              pnOidCicloVida       NUMBER,
                              pnOidFormaPago       NUMBER,
                              pnOidFormaCobro      NUMBER,
                              psCodigoSAP          VARCHAR2,
                              pnPrecio             NUMBER)
  RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Proceso Generar Envio MAV

  Fecha Creacion    : 22/01/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de Periodo
    psCodigoActividad  :  Codigo de Actividad
    psCodigoUsuario   : Codigo Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_GENER_ENVIO(psCodigoPais          VARCHAR2,
                               psCodigoPeriodo       VARCHAR2,
                               psCodigoActividad     VARCHAR2,
                               pscodigoTipoMav       VARCHAR2,
                               psCodigoUsuario       VARCHAR2);

  /**************************************************************************
  Descripcion       : Proceso Actualizar Envio MAV

  Fecha Creacion    : 21/08/2013
  Parametros Entrada:
    psCodigoCliente           : Codigo de Cliente
    psCodigoPais              : Codigo de Pais
    psCodigoRegion            : Codigo de Region
    psCodigoZona              : Codigo de Zona
    psIndEnvi                 : Indicador de Envio
    psIndAlta                 : Indicador Alta/Baja
    psCodigoCargo             : Codigo de Cargo
    psCodigoUsuario           : Codigo de Usuario

  Autor             : Juan Altamirano
  ***************************************************************************/
  PROCEDURE MAV_PR_ACTUA_ENVIO(psCodigoCliente       VARCHAR2,
                              psCodigoPais          VARCHAR2,
                              psCodigoRegion        VARCHAR2,
                              psCodigoZona          VARCHAR2,
                              psIndEnvi             VARCHAR2,
                              psIndAlta             VARCHAR2,
                              psCodigoCargo         VARCHAR2,
                              psCodigoUsuario       VARCHAR2);

  /**************************************************************************
  Descripcion       : Proceso Validar Agregados MAV

  Fecha Creacion    : 01/02/2013
  Parametros Entrada:
  pnOidSolicitud     :  Oid Solicitud

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_VALID_AGREG(pnOidSolicitud        NUMBER);

  /**************************************************************************
  Descripcion       : Registrar Error del Proceso Generar Envio MAV

  Fecha Creacion    : 06/02/2013
  Parametros Entrada:
    pnOidCliente     :  Oid Cliente
    pnOidSolicitud   :  Oid Solicitud

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_REGIS_ERROR_AGREG(pnOidCliente          NUMBER,
                                     pnOidSolicitud        NUMBER);

  /**************************************************************************
  Descripcion       : Proceso Facturacion Gerentes MAV

  Fecha Creacion    : 08/02/2013
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoPeriodo  :  Codigo de Periodo
    psCodigoActividad  :  Codigo de Actividad
    psCodigoUsuario   : Codigo Usuario

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE MAV_PR_FACTU_GEREN(psCodigoPais          VARCHAR2,
                               psCodigoPeriodo       VARCHAR2,
                               psCodigoActividad     VARCHAR2,
                               psCodigoUsuario       VARCHAR2);


/**************************************************************************
Descripcion       : Proceso que realiza la insercion de data en tablas temporal
                    para su visualizacion en los Reportes de Atenciones MAV
                    por Fecha-Campaña Según Tipo MAV/Actividad/Tipo Oferta/Producto/Tipo de Reporte
Fecha Creacion    : 19/07/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAV_PR_REPOR_ATENC_CAMPA(
  pscodigoTipoReporte     VARCHAR2,
  pscodigoRadio           VARCHAR2,
  pscodigoTipoMav         VARCHAR2,
  psflagRangoFechas       VARCHAR2,
  pnOidPais               NUMBER,
  psCodigoPeriodo         VARCHAR2,
  pscodigoTipoOferta      VARCHAR2,
  pscodigoActividad       VARCHAR2,
  pscodigoProducto        VARCHAR2,
  psfechaInicio           VARCHAR2,
  psfechaFin              VARCHAR2,
  psCodigoTipoCargo VARCHAR2,
  psIndicadorEnvio VARCHAR2);

/**************************************************************************
Descripcion       : Proceso que realiza la actualización de los envíos del MAV en base a los movimientos del DV
Fecha Creacion    : 11/09/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAV_PR_ACTUA_ENVIO_MASIV(
    psCodigoUsuario       VARCHAR2
);

/**************************************************************************
  Descripcion       : Proceso Inserta Envio MAV Gerente

  Fecha Creacion    : 21/03/2014
  Parametros Entrada:
    psCodigoPais              : Codigo de Pais
    pnCorrelativo             : Correlativo MAV
    psCodigoRegion            : Codigo de Region
    psCodigoZona              : Codigo de Zona
    psCapacitadora            : Codigo Capacitadora
    pnUnidades                : Numero de Unidades
    psCodigoUsuario           : Codigo de Usuario

  Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAV_PR_INSER_ENVIO_GEREN(psCodigoPais         VARCHAR2,
                                   pnCorrelativo        NUMBER,
                                   psCodigoRegion       VARCHAR2,
                                   psCodigoZona         VARCHAR2,
                                   psCapacitadora       VARCHAR2,
                                   pnUnidades           NUMBER,
                                   psCodigoUsuario      VARCHAR2);

/**************************************************************************
  Descripcion       : Proceso Inserta Envio MAV Consultoras

  Fecha Creacion    : 18/11/2014
  Parametros Entrada:
    psCodigoPais              : Codigo de Pais
    pnCorrelativo             : Correlativo MAV
    psCodigoCliente            : Codigo de Cliente
    pnUnidades                : Numero de Unidades
    psCodigoUsuario           : Codigo de Usuario

  Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAV_PR_INSER_ENVIO_CONSU(psCodigoPais         VARCHAR2,
                                   pnCorrelativo        NUMBER,
                                   psCodigoCliente      VARCHAR2,
                                   pnUnidades           NUMBER,
                                   psCodigoUsuario      VARCHAR2);
                                   
/***************************************************************************
  Descripcion       : Valida la carga externas consultoras
  Fecha Creacion    : 09/02/2015
  Autor             : Fernando Ochoa
  ***************************************************************************/
PROCEDURE MAV_PR_VALIDAR_EXTER_CLIE(psCodigoUsuario VARCHAR2);

END MAV_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY MAV_PKG_PROCE IS

/**************************************************************************
Descripcion        : Recupera el Gerente de Region de Reemplazo
Fecha Creacion     : 03/01/2011
Parametros Entrada :
           pnOidRegion : Oid Periodo
           psCodigoMarca : Codigo Marca
           psCodigoCanal : Codigo Canal
           psCodigoPeriodo : Codigo Periodo

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION FN_MAV_OBTEN_GEREN_REGIO(pnOidRegion        NUMBER,
                                  psCodigoMarca      VARCHAR2,
                                  psCodigoCanal      VARCHAR2,
                                  psCodigoPeriodo    VARCHAR2)
RETURN NUMBER IS
  lnOidCliente    NUMBER;
BEGIN
  /* Obteniendo id gerente reemplazo */
  SELECT R.CLIE_OID_CLIE
    INTO lnOidCliente
    FROM MAV_REMPL_GEREN_REGIO R, SEG_MARCA M, SEG_CANAL C, SEG_PERIO_CORPO S, CRA_PERIO P
   WHERE R.ZORG_OID_REGI = pnOidRegion
     AND R.MARC_OID_MARC  = M.OID_MARC
     AND C.OID_CANA = R.CANA_OID_CANA
     AND M.COD_MARC  = psCodigoMarca
     AND C.COD_CANA = psCodigoCanal
     AND P.OID_PERI = R.PERD_OID_PERI
     AND P.PERI_OID_PERI = S.OID_PERI
     AND S.COD_PERI = psCodigoPeriodo;

  RETURN lnOidCliente;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END FN_MAV_OBTEN_GEREN_REGIO;


/**************************************************************************
Descripcion       : Permite registrar reemplazo de Gerentes a Regiones  activa
                    y que no cuentan con Gerente asignado para la campaña, con la
                    finalidad de que no se deje  de despachar los materiales de la campaña a estas regiones.
Fecha Creacion    : 03/01/2011
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  psCodigoMarca   :  Codigo Marca
  psCodigoCanal     : Codigo Canal
  psCodigoPeriodo    : Codigo Periodo
  pnOidRegion    :  oid Region
  psCodigoCliente    :  codigo Cliente
  psCodigoUsuario    :  codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_ASIGN_GEREN_REGIO(psCodigoPais      IN VARCHAR2,
                                   psCodigoMarca     IN VARCHAR2,
                                   psCodigoCanal     IN VARCHAR2,
                                   psCodigoPeriodo   IN VARCHAR2,
                                   pnOidRegion       IN NUMBER,
                                   psCodigoCliente   IN VARCHAR2,
                                   psCodigoUsuario   IN VARCHAR2)
IS
  lnOidCliente    MAV_REMPL_GEREN_REGIO.CLIE_OID_CLIE%TYPE;
  lnOcurrencias   NUMBER;

  lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;

BEGIN
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

  --Obtenemos el Oid Cliente
  SELECT OID_CLIE
    INTO lnOidCliente
    FROM MAE_CLIEN
   WHERE COD_CLIE = psCodigoCliente;

  --Obtenemos el Oid Periodo
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --VERIFICAMOS SI EXISTE YA REGISTRADO EL GERENTE DE REGION DE REEMPLAZO
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM MAV_REMPL_GEREN_REGIO
   WHERE PAIS_OID_PAIS = lnOidPais
     AND MARC_OID_MARC = lnOidMarca
     AND CANA_OID_CANA = lnOidCanal
     AND ZORG_OID_REGI = pnOidRegion
     AND PERD_OID_PERI = lnOidPeriodo;

   IF(lnOcurrencias = 0) THEN
     INSERT INTO MAV_REMPL_GEREN_REGIO
       (PAIS_OID_PAIS, MARC_OID_MARC, CANA_OID_CANA,
        ZORG_OID_REGI, PERD_OID_PERI, COD_CLIE,
        CLIE_OID_CLIE, USU_MODI, FEC_MODI)
     VALUES
       (lnOidPais, lnOidMarca, lnOidCanal,
        pnOidRegion, lnOidPeriodo, psCodigoCliente,
        lnOidCliente, psCodigoUsuario, SYSDATE);

   ELSE
     UPDATE MAV_REMPL_GEREN_REGIO
        SET COD_CLIE = psCodigoCliente,
            CLIE_OID_CLIE = lnOidCliente,
            USU_MODI = psCodigoUsuario,
            FEC_MODI = SYSDATE
      WHERE PAIS_OID_PAIS = lnOidPais
        AND MARC_OID_MARC = lnOidMarca
        AND CANA_OID_CANA = lnOidCanal
        AND ZORG_OID_REGI = pnOidRegion
        AND PERD_OID_PERI = lnOidPeriodo;

   END IF;

END MAV_PR_ASIGN_GEREN_REGIO;


/**************************************************************************
Descripcion        : Recupera el Gerente de Zona de Reemplazo
Fecha Creacion     : 04/01/2011
Parametros Entrada :
           pnOidZona : Oid Zona
           psCodigoMarca : Codigo Marca
           psCodigoCanal : Codigo Canal
           psCodigoPeriodo : Codigo Periodo

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION FN_MAV_OBTEN_GEREN_ZONA(pnOidZona          NUMBER,
                                 psCodigoMarca      VARCHAR2,
                                 psCodigoCanal      VARCHAR2,
                                 psCodigoPeriodo    VARCHAR2)
RETURN NUMBER IS
  lnOidCliente    NUMBER;
BEGIN
  /* Obteniendo id gerente reemplazo */
  SELECT Z.CLIE_OID_CLIE
    INTO lnOidCliente
    FROM MAV_REMPL_GEREN_ZONA Z, SEG_MARCA M, SEG_CANAL C, SEG_PERIO_CORPO S, CRA_PERIO P
   WHERE Z.ZZON_OID_ZONA = pnOidZona
     AND Z.MARC_OID_MARC  = M.OID_MARC
     AND C.OID_CANA = Z.CANA_OID_CANA
     AND M.COD_MARC  = psCodigoMarca
     AND C.COD_CANA = psCodigoCanal
     AND P.OID_PERI = Z.PERD_OID_PERI
     AND P.PERI_OID_PERI = S.OID_PERI
     AND S.COD_PERI = psCodigoPeriodo;

  RETURN lnOidCliente;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END FN_MAV_OBTEN_GEREN_ZONA;


/**************************************************************************
Descripcion       : Permite registrar reemplazo de Gerentes a Zonas  activas y
                    que no cuentan con Gerente asignado para la campaña, con la
                    finalidad de que no se deje  de despachar los materiales de la campaña a estas zonas.
Fecha Creacion    : 04/01/2011
Parametros Entrada:
  psCodigoPais     :  Codigo Pais
  psCodigoMarca   :  Codigo Marca
  psCodigoCanal     : Codigo Canal
  psCodigoPeriodo    : Codigo Periodo
  pnOidRegion    :  oid Region
  pnOidZona    :  oid Zona
  psCodigoCliente    :  codigo Cliente
  psCodigoUsuario    :  codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_ASIGN_GEREN_ZONA(psCodigoPais      IN VARCHAR2,
                                  psCodigoMarca     IN VARCHAR2,
                                  psCodigoCanal     IN VARCHAR2,
                                  psCodigoPeriodo   IN VARCHAR2,
                                  pnOidRegion       IN NUMBER,
                                  pnOidZona         IN NUMBER,
                                  psCodigoCliente   IN VARCHAR2,
                                  psCodigoUsuario   IN VARCHAR2)
IS
  lnOidCliente    MAV_REMPL_GEREN_ZONA.CLIE_OID_CLIE%TYPE;
  lnOcurrencias   NUMBER;

  lnOidPais       SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca      SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal      SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;

BEGIN
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(psCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(psCodigoCanal);

  --Obtenemos el Oid Cliente
  SELECT OID_CLIE
    INTO lnOidCliente
    FROM MAE_CLIEN
   WHERE COD_CLIE = psCodigoCliente;

  --Obtenemos el Oid Periodo
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --VERIFICAMOS SI EXISTE YA REGISTRADO EL GERENTE DE REGION DE REEMPLAZO
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM MAV_REMPL_GEREN_ZONA
   WHERE PAIS_OID_PAIS = lnOidPais
     AND MARC_OID_MARC = lnOidMarca
     AND CANA_OID_CANA = lnOidCanal
     AND ZORG_OID_REGI = pnOidRegion
     AND ZZON_OID_ZONA = pnOidZona
     AND PERD_OID_PERI = lnOidPeriodo;

   IF(lnOcurrencias = 0) THEN
     INSERT INTO MAV_REMPL_GEREN_ZONA
       (PAIS_OID_PAIS, MARC_OID_MARC, CANA_OID_CANA,
        ZORG_OID_REGI, ZZON_OID_ZONA, PERD_OID_PERI, COD_CLIE,
        CLIE_OID_CLIE, USU_MODI, FEC_MODI)
     VALUES
       (lnOidPais, lnOidMarca, lnOidCanal,
        pnOidRegion, pnOidZona, lnOidPeriodo, psCodigoCliente,
        lnOidCliente, psCodigoUsuario, SYSDATE);

   ELSE
     UPDATE MAV_REMPL_GEREN_ZONA
        SET COD_CLIE = psCodigoCliente,
            CLIE_OID_CLIE = lnOidCliente,
            USU_MODI = psCodigoUsuario,
            FEC_MODI = SYSDATE
      WHERE PAIS_OID_PAIS = lnOidPais
        AND MARC_OID_MARC = lnOidMarca
        AND CANA_OID_CANA = lnOidCanal
        AND ZORG_OID_REGI = pnOidRegion
        AND ZZON_OID_ZONA = pnOidZona
        AND PERD_OID_PERI = lnOidPeriodo;

   END IF;

END MAV_PR_ASIGN_GEREN_ZONA;


/**************************************************************************
Descripcion        : Recupera el Cargo de Gerente
Fecha Creacion     : 04/01/2011
Parametros Entrada :
           pnOidCliente : Oid Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION FN_MAV_OBTEN_CARGO_GEREN(pnOidCliente    NUMBER)
RETURN VARCHAR2 IS
  lnOcurrencias   NUMBER;
  lsCargo         VARCHAR2(10);
BEGIN
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM ZON_ZONA
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND IND_ACTI = 1
     AND IND_BORR = 0;

  IF(lnOcurrencias > 0) THEN
    lsCargo := 'GZ';
  ELSE
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM ZON_REGIO
     WHERE CLIE_OID_CLIE = pnOidCliente
       AND IND_ACTI = 1
       AND IND_BORR = 0;

    IF(lnOcurrencias > 0) THEN
      lsCargo := 'GR';
    ELSE
      SELECT COUNT(1)
        INTO lnOcurrencias
        FROM MAV_REMPL_GEREN_REGIO
       WHERE CLIE_OID_CLIE = pnOidCliente;

      IF(lnOcurrencias > 0) THEN
        lsCargo := 'REEMP-GR';
      ELSE
        SELECT COUNT(1)
          INTO lnOcurrencias
          FROM MAV_REMPL_GEREN_ZONA
         WHERE CLIE_OID_CLIE = pnOidCliente;

        IF(lnOcurrencias > 0) THEN
          lsCargo := 'REEMP-GZ';
        ELSE
          lsCargo := '  ';
        END IF;
      END IF;
    END IF;
  END IF;

  RETURN lsCargo;

END FN_MAV_OBTEN_CARGO_GEREN;


/**************************************************************************
Descripcion        : Recupera el Gerente de Region de Reemplazo
Fecha Creacion     : 06/01/2011
Parametros Entrada :
           psCodigoMarca : Codigo Marca
           psCodigoCanal : Codigo Canal
           psCodigoPeriodo : Codigo Periodo
           psCodigoRegion : Codigo Region
           psCodigoZona : Codigo Zona

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION FN_MAV_OBTEN_GEREN_HISTO(psCodigoMarca      VARCHAR2,
                                  psCodigoCanal      VARCHAR2,
                                  psCodigoPeriodo    VARCHAR2,
                                  psCodigoRegion     VARCHAR2,
                                  psCodigoZona       VARCHAR2)
RETURN NUMBER IS
  lnOidCliente       MAE_CLIEN.OID_CLIE%TYPE;
  lnOidGerente       MAE_CLIEN.OID_CLIE%TYPE;
  lnOidGerenteReemp  MAE_CLIEN.OID_CLIE%TYPE;

BEGIN
  IF(psCodigoZona IS NOT NULL) THEN
    SELECT Z.CLIE_OID_CLIE OID_CLIE_GERE,
	         MAV_PKG_PROCE.FN_MAV_OBTEN_GEREN_ZONA(Z.OID_ZONA, psCodigoMarca, psCodigoCanal, psCodigoPeriodo) OID_CLIE_REEM
      INTO lnOidGerente, lnOidGerenteReemp
      FROM ZON_ZONA Z, ZON_REGIO R, SEG_MARCA M, SEG_CANAL C
     WHERE Z.IND_ACTI = 1
       AND Z.MARC_OID_MARC  = M.OID_MARC
       AND C.OID_CANA = Z.CANA_OID_CANA
       AND Z.ZORG_OID_REGI = R.OID_REGI
       AND R.COD_REGI = psCodigoRegion
       AND Z.COD_ZONA = psCodigoZona
       AND M.COD_MARC  = psCodigoMarca
       AND C.COD_CANA = psCodigoCanal;

    IF(lnOidGerenteReemp IS NOT NULL) THEN
      lnOidCliente := lnOidGerenteReemp;
    ELSE
      lnOidCliente := lnOidGerente;
    END IF;
  ELSE
    SELECT R.CLIE_OID_CLIE OID_CLIE_GERE,
	         MAV_PKG_PROCE.FN_MAV_OBTEN_GEREN_REGIO(R.OID_REGI, psCodigoMarca, psCodigoCanal, psCodigoPeriodo) OID_CLIE_REEM
      INTO lnOidGerente, lnOidGerenteReemp
      FROM ZON_REGIO R, SEG_MARCA M, SEG_CANAL C
     WHERE R.IND_ACTI = 1
       AND R.MARC_OID_MARC  = M.OID_MARC
       AND C.OID_CANA = R.CANA_OID_CANA
       AND R.COD_REGI = psCodigoRegion
       AND M.COD_MARC  = psCodigoMarca
       AND C.COD_CANA = psCodigoCanal;

    IF(lnOidGerenteReemp IS NOT NULL) THEN
      lnOidCliente := lnOidGerenteReemp;
    ELSE
      lnOidCliente := lnOidGerente;
    END IF;

  END IF;

  RETURN lnOidCliente;

END FN_MAV_OBTEN_GEREN_HISTO;


/**************************************************************************
Descripcion        : Recupera la Region/Zona de la Gerente
Fecha Creacion     : 06/01/2011
Parametros Entrada :
           pnOidCliente : Oid Cliente

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION FN_MAV_OBTEN_REGIO_ZONA(pnOidCliente    NUMBER)
RETURN VARCHAR2 IS
  lsRegionZona       VARCHAR2(20);

BEGIN
  SELECT reg.COD_REGI || ' - ' || zon.cod_zona
    INTO lsRegionZona
    FROM zon_zona zon,
         zon_regio reg
   WHERE zon.clie_oid_clie = pnOidCliente
     AND zon.IND_ACTI = 1
     AND zon.IND_BORR = 0
		 AND zon.ZORG_OID_REGI = reg.OID_REGI
     AND reg.IND_ACTI = 1
     AND reg.IND_BORR= 0;

  RETURN lsRegionZona;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END FN_MAV_OBTEN_REGIO_ZONA;


/**************************************************************************
Descripcion        : Recupera datos del Cliente
Fecha Creacion     : 07/01/2011
Parametros Entrada :
           pnOidCliente : Oid Cliente
           psTipoDato : Tipo de Dato

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION FN_MAV_OBTEN_DATOS_CLIEN(pnOidCliente    NUMBER,
                                  psTipoDato      VARCHAR2)
RETURN VARCHAR2 IS
  lsDato       VARCHAR2(100);

BEGIN
  IF(psTipoDato = 'Direccion') THEN
    SELECT tv.DES_ABRV_TIPO_VIA || ' ' || mcd.val_nomb_via || ' ' || num_ppal || ' ' || mcd.val_obse
      INTO lsDato
      FROM MAE_CLIEN_DIREC mcd,
 	         SEG_TIPO_VIA tv
     WHERE mcd.CLIE_OID_CLIE = pnOidCliente
       AND mcd.IND_DIRE_PPAL = 1
       AND mcd.IND_ELIM = 0
       AND mcd.TIVI_OID_TIPO_VIA = tv.OID_TIPO_VIA;

  ELSIF (psTipoDato = 'TelefonoFijo') THEN
    SELECT mcc.VAL_TEXT_COMU
      INTO lsDato
      FROM MAE_CLIEN_COMUN mcc, MAE_TIPO_COMUN tip
     WHERE mcc.clie_oid_clie = pnOidCliente
       AND mcc.ticm_oid_tipo_comu = tip.oid_tipo_comu
       AND tip.cod_tipo_comu = 'TF';

  ELSIF (psTipoDato = 'Celular') THEN
    SELECT mcc.VAL_TEXT_COMU
      INTO lsDato
      FROM MAE_CLIEN_COMUN mcc, MAE_TIPO_COMUN tip
     WHERE mcc.clie_oid_clie = pnOidCliente
       AND mcc.ticm_oid_tipo_comu = tip.oid_tipo_comu
       AND tip.cod_tipo_comu = 'CE';

  ELSIF (psTipoDato = 'NombreCompleto') THEN
    SELECT mc.val_nom1  || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' || mc.val_ape2
      INTO lsDato
      FROM MAE_CLIEN mc
     WHERE mc.OID_CLIE = pnOidCliente;

  ELSIF (psTipoDato = 'CodigoRegionGerenteZona') THEN
    SELECT reg.COD_REGI
      INTO lsDato
      FROM zon_zona zon, zon_regio reg
     WHERE zon.clie_oid_clie = pnOidCliente
       AND zon.IND_ACTI = 1
       AND zon.IND_BORR = 0
       AND zon.ZORG_OID_REGI = reg.OID_REGI
       AND reg.IND_ACTI = 1
       AND reg.IND_BORR= 0;

  ELSIF (psTipoDato = 'RegionGerenteZona') THEN
    SELECT reg.DES_REGI
      INTO lsDato
      FROM zon_zona zon, zon_regio reg
     WHERE zon.clie_oid_clie = pnOidCliente
       AND zon.IND_ACTI = 1
       AND zon.IND_BORR = 0
       AND zon.ZORG_OID_REGI = reg.OID_REGI
       AND reg.IND_ACTI = 1
       AND reg.IND_BORR= 0;

  ELSIF (psTipoDato = 'CodigoZonaGerenteZona') THEN
    SELECT zon.cod_zona
      INTO lsDato
      FROM zon_zona zon
     WHERE zon.clie_oid_clie = pnOidCliente
       AND zon.IND_ACTI = 1
       AND zon.IND_BORR = 0;

  END IF;

  RETURN lsDato;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END FN_MAV_OBTEN_DATOS_CLIEN;


/**************************************************************************
Descripcion       : Permite registrar numero de Cajas a una solicitud de Pedido
Fecha Creacion    : 10/01/2011
Parametros Entrada:
  pnOidSolicitud     :  Oid Solicitud
  pnNumeroCajas   :  Numero de Cajas
  psCodigoUsuario    :  codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_ASIGN_NUMER_CAJAS(pnOidSolicitud      IN VARCHAR2,
                                   pnNumeroCajas       IN NUMBER,
                                   psCodigoUsuario     IN VARCHAR2)
IS
  lnOcurrencias   NUMBER;

BEGIN
  --VERIFICAMOS SI EXISTE YA REGISTRADO EL GERENTE DE REGION DE REEMPLAZO
  SELECT COUNT(1)
    INTO lnOcurrencias
    FROM MAV_PEDID_CAJA
   WHERE SOCA_OID_SOLI_CABE = pnOidSolicitud;

   IF(lnOcurrencias = 0) THEN
     INSERT INTO MAV_PEDID_CAJA
       (SOCA_OID_SOLI_CABE,
        NUM_CAJAS,
        USU_MODI,
        FEC_MODI)
     VALUES
       (pnOidSolicitud,
        pnNumeroCajas,
        psCodigoUsuario,
        SYSDATE);

   ELSE
     UPDATE MAV_PEDID_CAJA
        SET NUM_CAJAS = pnNumeroCajas,
            USU_MODI = psCodigoUsuario,
            FEC_MODI = SYSDATE
      WHERE SOCA_OID_SOLI_CABE = pnOidSolicitud;

   END IF;

END MAV_PR_ASIGN_NUMER_CAJAS;


/**************************************************************************
Descripcion        : Recupera lista de Numeros de Caja
Fecha Creacion     : 12/01/2011
Parametros Entrada :
           pnTotalCajas : Total de Cajas

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION FN_MAV_OBTEN_LISTA_CAJAS(pnTotalCajas   NUMBER)
RETURN split_tbl PIPELINED IS

  lnPosicion    NUMBER;
BEGIN

  lnPosicion := 0;

  FOR x IN 1..pnTotalCajas LOOP
    lnPosicion := lnPosicion + 1;

    PIPE ROW(TO_CHAR(lnPosicion));
  END LOOP;

END FN_MAV_OBTEN_LISTA_CAJAS;


/**************************************************************************
Descripcion       : Validacion de Carga Masiva de MAV

Fecha Creacion    : 02/01/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_VALID_CARGA_MASIV(psCodigoPais          VARCHAR2,
                                   psCodigoUsuario       VARCHAR2)
IS

  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca          SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal          SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo        CRA_PERIO.OID_PERI%TYPE;
  lsCodPeriodoFact    BAS_CTRL_FACT.COD_PERI%TYPE;

  CURSOR c_CargaMasiva IS
    SELECT *
      FROM MAV_TMP_CARGA_MASIV
     ORDER BY NUM_FILA;

  TYPE typTabCargaMasiva IS TABLE OF MAV_TMP_CARGA_MASIV%ROWTYPE;
  tabCargaMasiva         typTabCargaMasiva;

  CURSOR c_CargaMasivaCabec IS
    SELECT DISTINCT
           COD_PAIS,
           CAM_DESP,
           COD_ACTI,
           COD_TIPO_OFER,
           COD_PROD,
           IND_ENVI_MENS
      FROM MAV_TMP_CARGA_MASIV
     WHERE EST_VALI = 1
       AND COD_CONS IS NOT NULL
     ORDER BY COD_PAIS, CAM_DESP, COD_ACTI, COD_TIPO_OFER, COD_PROD, IND_ENVI_MENS;

  CURSOR c_CargaMasivaCabecRegion IS
    SELECT DISTINCT
           COD_PAIS,
           CAM_DESP,
           COD_ACTI,
           COD_TIPO_OFER,
           COD_PROD,
           IND_ENVI_MENS
      FROM MAV_TMP_CARGA_MASIV
     WHERE EST_VALI = 1
       AND COD_REGI IS NOT NULL
       AND COD_ZONA IS NULL
     ORDER BY COD_PAIS, CAM_DESP, COD_ACTI, COD_TIPO_OFER, COD_PROD, IND_ENVI_MENS;

  CURSOR c_CargaMasivaCabecZona IS
    SELECT DISTINCT
           COD_PAIS,
           CAM_DESP,
           COD_ACTI,
           COD_TIPO_OFER,
           COD_PROD,
           IND_ENVI_MENS
      FROM MAV_TMP_CARGA_MASIV
     WHERE EST_VALI = 1
       AND COD_REGI IS NOT NULL
       AND COD_ZONA IS NOT NULL
     ORDER BY COD_PAIS, CAM_DESP, COD_ACTI, COD_TIPO_OFER, COD_PROD, IND_ENVI_MENS;

  TYPE interfazCargaMasivaCabec IS RECORD
  (
   codigoPais                MAV_TMP_CARGA_MASIV.COD_PAIS%TYPE,
   campanaDespacho           MAV_TMP_CARGA_MASIV.CAM_DESP%TYPE,
   codigoActividad           MAV_TMP_CARGA_MASIV.COD_ACTI%TYPE,
   codigoTipoOferta          MAV_TMP_CARGA_MASIV.COD_TIPO_OFER%TYPE,
   codigoProducto            MAV_TMP_CARGA_MASIV.COD_PROD%TYPE,
   indEnviaMensaje           MAV_TMP_CARGA_MASIV.IND_ENVI_MENS%TYPE
  );

  TYPE interfazCabecTab  IS TABLE OF interfazCargaMasivaCabec;
  interfazRecordN interfazCabecTab;

  lbValido               BOOLEAN;
  lnOcurrencias          NUMBER;
  lsMensajeError         VARCHAR2(100);
  lnNumUnidades          NUMBER(12);

  lnNumeroFila           MAV_TMP_CARGA_MASIV.NUM_FILA%TYPE;
  lsCodigoPais           MAV_TMP_CARGA_MASIV.COD_PAIS%TYPE;
  lsCodigoPeriodo        MAV_TMP_CARGA_MASIV.CAM_DESP%TYPE;
  lsCodigoActividad      MAV_TMP_CARGA_MASIV.COD_ACTI%TYPE;
  lsCodigoTipoOferta     MAV_TMP_CARGA_MASIV.COD_TIPO_OFER%TYPE;
  lsCodigoProducto       MAV_TMP_CARGA_MASIV.COD_PROD%TYPE;
  lsIndEnviaMensaje      MAV_TMP_CARGA_MASIV.IND_ENVI_MENS%TYPE;
  lsNumUnidades          MAV_TMP_CARGA_MASIV.NUM_UNID%TYPE;
  lsCodigoConsultora     MAV_TMP_CARGA_MASIV.COD_CONS%TYPE;
  lsCodigoRegion         MAV_TMP_CARGA_MASIV.COD_REGI%TYPE;
  lsCodigoZona           MAV_TMP_CARGA_MASIV.COD_ZONA%TYPE;
BEGIN
  --Recuperamos el oid Pais,Marca,Canal
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

  --Recupermoa el periodo de Facturacion
  SELECT COD_PERI
    INTO lsCodPeriodoFact
    FROM BAS_CTRL_FACT
   WHERE COD_PAIS = psCodigoPais
     AND STA_CAMP = '0'
     AND IND_CAMP_ACT = '1';

  --(1) PROCESAMOS A LAS RECOMENDANDOS
  OPEN c_CargaMasiva;
  LOOP
    FETCH c_CargaMasiva BULK COLLECT INTO tabCargaMasiva LIMIT W_FILAS;
    IF tabCargaMasiva.COUNT > 0 THEN

      FOR x IN tabCargaMasiva.FIRST .. tabCargaMasiva.LAST LOOP
        lbValido := TRUE;
        lsMensajeError := NULL;
        lnNumeroFila := tabCargaMasiva(x).NUM_FILA;
        lsCodigoPais := tabCargaMasiva(x).COD_PAIS;
        lsCodigoPeriodo := tabCargaMasiva(x).CAM_DESP;
        lsCodigoActividad := tabCargaMasiva(x).COD_ACTI;
        lsCodigoTipoOferta := tabCargaMasiva(x).COD_TIPO_OFER;
        lsCodigoProducto := tabCargaMasiva(x).COD_PROD;
        lsIndEnviaMensaje := tabCargaMasiva(x).IND_ENVI_MENS;
        lsNumUnidades := tabCargaMasiva(x).NUM_UNID;
        lsCodigoConsultora := tabCargaMasiva(x).COD_CONS;
        lsCodigoRegion := tabCargaMasiva(x).COD_REGI;
        lsCodigoZona := tabCargaMasiva(x).COD_ZONA;

        --(0) Validamos Valores en Campos Obligatorios
        IF((lsCodigoPais IS NULL) AND (lsCodigoPeriodo IS NULL) AND
           (lsCodigoActividad IS NULL) AND (lsCodigoTipoOferta IS NULL) AND
           (lsCodigoProducto IS NULL) AND (lsIndEnviaMensaje IS NULL) AND
           (lsNumUnidades IS NULL)) THEN

          lbValido := FALSE;
          lsMensajeError := 'Campos Obligatorios no estan completos';
        END IF;

        --(1) Validamos Codigo Pais
        IF(lbValido) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM SEG_PAIS
           WHERE COD_PAIS = lsCodigoPais;

          IF(lnOcurrencias = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Codigo de Pais No Existe';
          END IF;
        END IF;

        --(2) Validamos Codigo Periodo
        IF(lbValido) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM SEG_PERIO_CORPO
           WHERE COD_PERI = lsCodigoPeriodo;

          IF(lnOcurrencias = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Campaña de Despacho No Existe';
          END IF;
        END IF;

        --(2) Validamos Codigo Periodo
        IF(lbValido) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM SEG_PERIO_CORPO
           WHERE COD_PERI = lsCodigoPeriodo;

          IF(lnOcurrencias = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Campaña de Despacho No Existe';
          END IF;

          IF(lsCodigoPeriodo < lsCodPeriodoFact) THEN
            lbValido := FALSE;
            lsMensajeError := 'Campaña de Despacho es Menor a Campaña Actual';
          END IF;

        END IF;

        --(3) Validamos Oid Actividad
        IF(lbValido) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM MAV_ACTIV
           WHERE OID_ACTI = lsCodigoActividad;

          IF(lnOcurrencias = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Actividad No Existe';
          END IF;
        END IF;

        --(4) Validamos Codigo Tipo Oferta
        IF(lbValido) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM PRE_TIPO_OFERT
           WHERE COD_TIPO_OFER = lsCodigoTipoOferta;

          IF(lnOcurrencias = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Tipo de Oferta No Existe';
          END IF;
        END IF;

        --(5) Validamos Codigo Producto
        IF(lbValido) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM MAE_PRODU
           WHERE COD_SAP = lsCodigoProducto;

          IF(lnOcurrencias = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Codigo de Producto No Existe';
          END IF;
        END IF;

        --(6) Validamos Indicador Envia Mensaje
        IF(lbValido) THEN
          IF(NOT ((lsIndEnviaMensaje = 'SI') OR (lsIndEnviaMensaje = 'NO'))) THEN
            lbValido := FALSE;
            lsMensajeError := 'Ind. Envia Mensaje tiene valor Incorrecto';
          END IF;
        END IF;

        --(7) Validamos Numero Unidades
        IF(lbValido) THEN
          BEGIN
            lnNumUnidades := TO_NUMBER(lsNumUnidades);
          EXCEPTION
            WHEN OTHERS THEN
              lbValido := FALSE;
              lsMensajeError := 'Cantidad no es Número Válido';
          END;
        END IF;

        --(7.1) Validamos Numero Unidades > 0
        IF(lbValido) THEN
          IF(lnNumUnidades = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Cantidad debe ser mayor a 0';
          END IF;
        END IF;

        --(8) Validamos Codigo Consultora
        IF(lbValido) THEN
          IF(lsCodigoConsultora IS NOT NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM MAE_CLIEN
             WHERE COD_CLIE = lsCodigoConsultora;

            IF(lnOcurrencias = 0) THEN
              lbValido := FALSE;
              lsMensajeError := 'Codigo de Consultora No Existe';
            END IF;
          END IF;
        END IF;

        --(9) Validamos Codigo Region
        IF(lbValido) THEN
          IF(lsCodigoRegion IS NOT NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM ZON_REGIO
             WHERE COD_REGI = lsCodigoRegion;

            IF(lnOcurrencias = 0) THEN
              lbValido := FALSE;
              lsMensajeError := 'Codigo de Region No Existe';
            END IF;
          END IF;
        END IF;

        --(10) Validamos Codigo Zona
        IF(lbValido) THEN
          IF(lsCodigoZona IS NOT NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM ZON_ZONA
             WHERE COD_ZONA = lsCodigoZona;

            IF(lnOcurrencias = 0) THEN
              lbValido := FALSE;
              lsMensajeError := 'Codigo de Zona No Existe';
            END IF;
          END IF;
        END IF;

        --(11) Validamos Relacion Codigo Actividad y Tipo Oferta Corresponden
        IF(lbValido) THEN
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM MAV_ACTIV act, MAV_ACTIV_TIPO_OFERT ato, PRE_TIPO_OFERT tof
           WHERE act.OID_ACTI = ato.ACTI_OID_ACTI
             AND ato.TOFE_OID_TIPO_OFER = tof.OID_TIPO_OFER
             AND act.OID_ACTI = lsCodigoActividad
             AND tof.COD_TIPO_OFER = lsCodigoTipoOferta;

          IF(lnOcurrencias = 0) THEN
            lbValido := FALSE;
            lsMensajeError := 'Relacion Actividad/Tipo Oferta No Existe';
          END IF;
        END IF;

        --(12) Validamos Relacion Region y Zona
        IF(lbValido) THEN
          IF(lsCodigoRegion IS NOT NULL AND lsCodigoZona IS NOT NULL) THEN
            SELECT COUNT(1)
              INTO lnOcurrencias
              FROM ZON_REGIO reg, ZON_ZONA zon
             WHERE reg.OID_REGI = zon.ZORG_OID_REGI
               AND reg.COD_REGI = lsCodigoRegion
               AND zon.COD_ZONA = lsCodigoZona;

            IF(lnOcurrencias = 0) THEN
              lbValido := FALSE;
              lsMensajeError := 'Relacion Region/Zona No Existe';
            END IF;
          END IF;
        END IF;


        IF(lbValido) THEN
          UPDATE MAV_TMP_CARGA_MASIV
             SET EST_VALI = 1
           WHERE NUM_FILA = tabCargaMasiva(x).NUM_FILA;
        ELSE
          UPDATE MAV_TMP_CARGA_MASIV
             SET EST_VALI = 9,
                 MEN_ERRO = lsMensajeError
           WHERE NUM_FILA = tabCargaMasiva(x).NUM_FILA;
        END IF;

      END LOOP;

    END IF;

    EXIT WHEN c_CargaMasiva%NOTFOUND;
  END LOOP;
  CLOSE c_CargaMasiva;

  -- PROCESAMOS A LAS CABECERAS MAV, VALIDANDO LAS CONSULTORAS DUPLICADAS
  OPEN c_CargaMasivaCabec;
  LOOP
    FETCH c_CargaMasivaCabec BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        FOR y IN (SELECT COD_CONS, COUNT(1), MIN(NUM_FILA) FIL_INIC
                    FROM MAV_TMP_CARGA_MASIV
                   WHERE COD_PAIS = interfazRecordN(x).codigoPais
                     AND CAM_DESP = interfazRecordN(x).campanaDespacho
                     AND COD_ACTI = interfazRecordN(x).codigoActividad
                     AND COD_TIPO_OFER = interfazRecordN(x).codigoTipoOferta
                     AND COD_PROD = interfazRecordN(x).codigoProducto
                     AND IND_ENVI_MENS = interfazRecordN(x).indEnviaMensaje
                     AND EST_VALI = 1
                     AND COD_CONS IS NOT NULL
                   GROUP BY COD_CONS
                   HAVING COUNT(1)>1) LOOP

          UPDATE MAV_TMP_CARGA_MASIV
             SET EST_VALI = 9,
                 MEN_ERRO = 'Código de Consultora duplicada'
           WHERE COD_PAIS = interfazRecordN(x).codigoPais
             AND CAM_DESP = interfazRecordN(x).campanaDespacho
             AND COD_ACTI = interfazRecordN(x).codigoActividad
             AND COD_TIPO_OFER = interfazRecordN(x).codigoTipoOferta
             AND COD_PROD = interfazRecordN(x).codigoProducto
             AND IND_ENVI_MENS = interfazRecordN(x).indEnviaMensaje
             AND EST_VALI = 1
             AND COD_CONS = y.COD_CONS
             AND NUM_FILA <> y.FIL_INIC;

        END LOOP;

      END LOOP;
    END IF;

    EXIT WHEN c_CargaMasivaCabec%NOTFOUND;
  END LOOP;
  CLOSE c_CargaMasivaCabec;

  -- PROCESAMOS A LAS CABECERAS MAV, VALIDANDO LAS REGIONES DUPLICADAS
  OPEN c_CargaMasivaCabecRegion;
  LOOP
    FETCH c_CargaMasivaCabecRegion BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        FOR y IN (SELECT COD_REGI, COUNT(1), MIN(NUM_FILA) FIL_INIC
                    FROM MAV_TMP_CARGA_MASIV
                   WHERE COD_PAIS = interfazRecordN(x).codigoPais
                     AND CAM_DESP = interfazRecordN(x).campanaDespacho
                     AND COD_ACTI = interfazRecordN(x).codigoActividad
                     AND COD_TIPO_OFER = interfazRecordN(x).codigoTipoOferta
                     AND COD_PROD = interfazRecordN(x).codigoProducto
                     AND IND_ENVI_MENS = interfazRecordN(x).indEnviaMensaje
                     AND EST_VALI = 1
                     AND COD_REGI IS NOT NULL
                     AND COD_ZONA IS NULL
                   GROUP BY COD_REGI
                   HAVING COUNT(1)>1) LOOP

          UPDATE MAV_TMP_CARGA_MASIV
             SET EST_VALI = 9,
                 MEN_ERRO = 'Código de Region duplicada'
           WHERE COD_PAIS = interfazRecordN(x).codigoPais
             AND CAM_DESP = interfazRecordN(x).campanaDespacho
             AND COD_ACTI = interfazRecordN(x).codigoActividad
             AND COD_TIPO_OFER = interfazRecordN(x).codigoTipoOferta
             AND COD_PROD = interfazRecordN(x).codigoProducto
             AND IND_ENVI_MENS = interfazRecordN(x).indEnviaMensaje
             AND EST_VALI = 1
             AND COD_REGI = y.COD_REGI
             AND COD_ZONA IS NULL
             AND NUM_FILA <> y.FIL_INIC;

        END LOOP;

      END LOOP;
    END IF;

    EXIT WHEN c_CargaMasivaCabecRegion%NOTFOUND;
  END LOOP;
  CLOSE c_CargaMasivaCabecRegion;

  -- PROCESAMOS A LAS CABECERAS MAV, VALIDANDO LAS ZONAS DUPLICADAS
  OPEN c_CargaMasivaCabecZona;
  LOOP
    FETCH c_CargaMasivaCabecZona BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

        FOR y IN (SELECT COD_REGI, COD_ZONA, COUNT(1), MIN(NUM_FILA) FIL_INIC
                    FROM MAV_TMP_CARGA_MASIV
                   WHERE COD_PAIS = interfazRecordN(x).codigoPais
                     AND CAM_DESP = interfazRecordN(x).campanaDespacho
                     AND COD_ACTI = interfazRecordN(x).codigoActividad
                     AND COD_TIPO_OFER = interfazRecordN(x).codigoTipoOferta
                     AND COD_PROD = interfazRecordN(x).codigoProducto
                     AND IND_ENVI_MENS = interfazRecordN(x).indEnviaMensaje
                     AND EST_VALI = 1
                     AND COD_REGI IS NOT NULL
                     AND COD_ZONA IS NOT NULL
                   GROUP BY COD_REGI, COD_ZONA
                   HAVING COUNT(1)>1) LOOP

          UPDATE MAV_TMP_CARGA_MASIV
             SET EST_VALI = 9,
                 MEN_ERRO = 'Código de Region/Zona duplicada'
           WHERE COD_PAIS = interfazRecordN(x).codigoPais
             AND CAM_DESP = interfazRecordN(x).campanaDespacho
             AND COD_ACTI = interfazRecordN(x).codigoActividad
             AND COD_TIPO_OFER = interfazRecordN(x).codigoTipoOferta
             AND COD_PROD = interfazRecordN(x).codigoProducto
             AND IND_ENVI_MENS = interfazRecordN(x).indEnviaMensaje
             AND EST_VALI = 1
             AND COD_REGI = y.COD_REGI
             AND COD_ZONA = y.COD_ZONA
             AND NUM_FILA <> y.FIL_INIC;

        END LOOP;

      END LOOP;
    END IF;

    EXIT WHEN c_CargaMasivaCabecZona%NOTFOUND;
  END LOOP;
  CLOSE c_CargaMasivaCabecZona;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_VALID_CARGA_MASIV: (' || ln_sqlcode || ')' || ls_sqlerrm);

END MAV_PR_VALID_CARGA_MASIV;


/**************************************************************************
Descripcion       : Actualizacion de Carga Masiva de MAV

Fecha Creacion    : 02/01/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoUsuario  :  Codigo de Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_ACTUA_CARGA_MASIV(psCodigoPais          VARCHAR2,
                                   psCodigoUsuario       VARCHAR2)
IS

  lnOidPais           SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca          SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal          SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo        CRA_PERIO.OID_PERI%TYPE;

  CURSOR c_CargaMasivaCabec IS
    SELECT DISTINCT
           COD_PAIS,
           CAM_DESP,
           COD_ACTI,
           COD_TIPO_OFER,
           COD_PROD,
           IND_ENVI_MENS
      FROM MAV_TMP_CARGA_MASIV
     WHERE EST_VALI = 1
     ORDER BY COD_PAIS, CAM_DESP, COD_ACTI, COD_TIPO_OFER, COD_PROD, IND_ENVI_MENS;

  TYPE interfazCargaMasivaCabec IS RECORD
  (
   codigoPais                MAV_TMP_CARGA_MASIV.COD_PAIS%TYPE,
   campanaDespacho           MAV_TMP_CARGA_MASIV.CAM_DESP%TYPE,
   codigoActividad           MAV_TMP_CARGA_MASIV.COD_ACTI%TYPE,
   codigoTipoOferta          MAV_TMP_CARGA_MASIV.COD_TIPO_OFER%TYPE,
   codigoProducto            MAV_TMP_CARGA_MASIV.COD_PROD%TYPE,
   indEnviaMensaje           MAV_TMP_CARGA_MASIV.IND_ENVI_MENS%TYPE
  );

  TYPE interfazCabecTab  IS TABLE OF interfazCargaMasivaCabec;
  interfazRecordN interfazCabecTab;

  CURSOR c_CargaMasivaConsultoras(codigoPais VARCHAR2, campanaDespacho VARCHAR2,
                            codigoActividad VARCHAR2, tipoOferta VARCHAR2,
                            codigoProducto VARCHAR2, indEnviaMensaje VARCHAR2) IS
    SELECT NUM_UNID,
           COD_CONS
      FROM MAV_TMP_CARGA_MASIV
     WHERE COD_PAIS = codigoPais
       AND CAM_DESP = campanaDespacho
       AND COD_ACTI = codigoActividad
       AND COD_TIPO_OFER = tipoOferta
       AND COD_PROD = codigoProducto
       AND IND_ENVI_MENS = indEnviaMensaje
       AND EST_VALI = 1
       AND COD_CONS IS NOT NULL
     ORDER BY NUM_FILA;

  TYPE interfazCargaMasivaConsultoras IS RECORD
  (
   numeroUnidades       MAV_TMP_CARGA_MASIV.NUM_UNID%TYPE,
   codigoConsultora     MAV_TMP_CARGA_MASIV.COD_CONS%TYPE
  );

  TYPE interfazDetalTab  IS TABLE OF interfazCargaMasivaConsultoras;
  interfazRecordC interfazDetalTab;

  CURSOR c_CargaMasivaRegiones(codigoPais VARCHAR2, campanaDespacho VARCHAR2,
                               codigoActividad VARCHAR2, tipoOferta VARCHAR2,
                               codigoProducto VARCHAR2, indEnviaMensaje VARCHAR2) IS
    SELECT NUM_UNID,
           COD_REGI,
           COD_ZONA
      FROM MAV_TMP_CARGA_MASIV
     WHERE COD_PAIS = codigoPais
       AND CAM_DESP = campanaDespacho
       AND COD_ACTI = codigoActividad
       AND COD_TIPO_OFER = tipoOferta
       AND COD_PROD = codigoProducto
       AND IND_ENVI_MENS = indEnviaMensaje
       AND EST_VALI = 1
       AND (COD_REGI IS NOT NULL OR COD_ZONA IS NOT NULL)
     ORDER BY NUM_FILA;

  TYPE interfazCargaMasivaRegiones IS RECORD
  (
   numeroUnidades       MAV_TMP_CARGA_MASIV.NUM_UNID%TYPE,
   codigoRegion         MAV_TMP_CARGA_MASIV.COD_REGI%TYPE,
   codigoZona           MAV_TMP_CARGA_MASIV.COD_ZONA%TYPE
  );

  TYPE interfazDetal2Tab  IS TABLE OF interfazCargaMasivaRegiones;
  interfazRecordR interfazDetal2Tab;


  lnOcurrencias          NUMBER;
  lnNumUnidades          NUMBER(12);
  lnContador             NUMBER(12);
  lbInsertado            BOOLEAN;
  lnCorrelativoCab       MAV_PARAM_CONFI.COR_PARA_CONF%TYPE;

  lnNumeroFila           MAV_TMP_CARGA_MASIV.NUM_FILA%TYPE;
  lsCodigoPais           MAV_TMP_CARGA_MASIV.COD_PAIS%TYPE;
  lsCodigoPeriodo        MAV_TMP_CARGA_MASIV.CAM_DESP%TYPE;
  lsCodigoActividad      MAV_TMP_CARGA_MASIV.COD_ACTI%TYPE;
  lsCodigoTipoOferta     MAV_TMP_CARGA_MASIV.COD_TIPO_OFER%TYPE;
  lsCodigoProducto       MAV_TMP_CARGA_MASIV.COD_PROD%TYPE;
  lsIndEnviaMensaje      MAV_TMP_CARGA_MASIV.IND_ENVI_MENS%TYPE;
  lsNumUnidades          MAV_TMP_CARGA_MASIV.NUM_UNID%TYPE;
  lsCodigoConsultora     MAV_TMP_CARGA_MASIV.COD_CONS%TYPE;
  lsCodigoRegion         MAV_TMP_CARGA_MASIV.COD_REGI%TYPE;
  lsCodigoZona           MAV_TMP_CARGA_MASIV.COD_ZONA%TYPE;

  lnOidActividad         MAV_ACTIV.OID_ACTI%TYPE;
  lnOidTipoCliente       MAV_ACTIV.TICL_OID_TIPO_CLIE%TYPE;
  lsCodigoTipoCliente    MAE_TIPO_CLIEN.COD_TIPO_CLIE%TYPE;
  lnOidFormaPago         MAV_ACTIV.FOPA_OID_FORM_PAGO%TYPE;
  lnOidFormaCobro        MAV_ACTIV.TCPA_OID_TIPO_COND_PAGO%TYPE;
  lnTipoDespacho         MAV_ACTIV_TIPO_DESPA.TDCH_OID_TIPO_DESP%TYPE;
  lnEnvioSolicitud       MAV_ACTIV_TIPO_DESPA.ENVS_OID_ENVI_SOLI%TYPE;

  lsCodigoEstado         MAV_PARAM_CONFI.COD_ESTA_MAV%TYPE;
  lnOidActividadTO       MAV_ACTIV_TIPO_OFERT.OID_TIPO_OFER_ACTI%TYPE;

BEGIN
  --Recuperamos el oid Pais,Marca,Canal
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

  --(1) PROCESAMOS A LAS CABECERAS MAV
  OPEN c_CargaMasivaCabec;
  LOOP
    FETCH c_CargaMasivaCabec BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
        lsCodigoPais := interfazRecordN(x).codigoPais;
        lsCodigoPeriodo := interfazRecordN(x).campanaDespacho;
        lsCodigoActividad := interfazRecordN(x).codigoActividad;
        lsCodigoTipoOferta := interfazRecordN(x).codigoTipoOferta;
        lsCodigoProducto := interfazRecordN(x).codigoProducto;
        lsIndEnviaMensaje := interfazRecordN(x).indEnviaMensaje;

        lsCodigoEstado := '2'; --Completado

        --Obtenemos el correlativo MAV
        SELECT nvl(MAX(COR_PARA_CONF),0)+1
          INTO lnCorrelativoCab
    		  FROM MAV_PARAM_CONFI;

        --Obtenemos datos relacionados a la actividad MAV
        SELECT act.OID_ACTI,
    		       act.TICL_OID_TIPO_CLIE,
    		       tip.COD_TIPO_CLIE,
    		       act.FOPA_OID_FORM_PAGO,
    		       act.TCPA_OID_TIPO_COND_PAGO,
    		       atd.OID_ACTI_TIPO_DESP,
    		       atd.ENVS_OID_ENVI_SOLI
          INTO lnOidActividad,
               lnOidTipoCliente,
               lsCodigoTipoCliente,
               lnOidFormaPago,
               lnOidFormaCobro,
               lnTipoDespacho,
               lnEnvioSolicitud
    		  FROM MAV_ACTIV act,
    		       MAV_ACTIV_TIPO_DESPA atd,
    		       MAE_TIPO_CLIEN tip
    		 WHERE act.OID_ACTI = atd.ACTI_OID_ACTI
    		   AND act.TICL_OID_TIPO_CLIE = tip.OID_TIPO_CLIE
    		   AND act.OID_ACTI = lsCodigoActividad
           AND ROWNUM = 1;

        --Obtenemos el oid TipoOferta Actividad
        SELECT ato.OID_TIPO_OFER_ACTI
          INTO lnOidActividadTO
          FROM PRE_TIPO_OFERT ofe, MAV_ACTIV_TIPO_OFERT ato
         WHERE ofe.OID_TIPO_OFER = ato.TOFE_OID_TIPO_OFER
           AND ato.ACTI_OID_ACTI = lnOidActividad
           AND ofe.cod_tipo_ofer = lsCodigoTipoOferta;

        INSERT INTO MAV_PARAM_CONFI
         (PAIS_COD_PAIS,
	        COR_PARA_CONF,
	        ATOF_ACTI_OID_ACTI,
 	        ATOF_OID_TIPO_OFER_ACTI,
	        PROD_OID_PROD,
	        CAM_PARA_MAV,
	        COD_ACTI,
	        COD_TIPO_OFER,
	        COD_SAP,
	        TICL_OID_TIPO_CLIE,
	        COD_TIPO_CLIE,
	        COD_ESTA_MAV,
	        IND_ORIG_MAV,
	        IND_ENVI_MENS,
	        FOR_COBR,
	        FOR_PAGO,
	        ACT_TIPO_DESP,
	        ENV_SOLI,
	        NUM_UNID_PROD,
	        VAL_PREC,
          IND_UNID_DIF,
	        IND_ACTI,
	        USU_CREA,
	        FEC_CREA,
	        EST_REGI)
    		VALUES
	       (lsCodigoPais,
	        lnCorrelativoCab,
	        lnOidActividad,
	        lnOidActividadTO,
	        (SELECT OID_PROD FROM MAE_PRODU WHERE COD_SAP = lsCodigoProducto),
	        lsCodigoPeriodo,
	        lnOidActividad,
	        lsCodigoTipoOferta,
	        lsCodigoProducto,
	        lnOidTipoCliente,
	        lsCodigoTipoCliente,
	        lsCodigoEstado,
	        'C',
	        DECODE(lsIndEnviaMensaje, 'SI', 'S', 'N'),
	        lnOidFormaCobro,
	        lnOidFormaPago,
	        lnTipoDespacho,
	        lnEnvioSolicitud,
	        NULL,
	        0,
          'N',
	        1,
	        psCodigoUsuario,
	        SYSDATE,
	        1);

        --INSERTAMOS LOS DETALLES DEL PARAMETRO MAV

        --CASO (1): Todos
        SELECT COUNT(1)
          INTO lnOcurrencias
          FROM MAV_TMP_CARGA_MASIV
         WHERE COD_PAIS = lsCodigoPais
           AND CAM_DESP = lsCodigoPeriodo
           AND COD_ACTI = lsCodigoActividad
           AND COD_TIPO_OFER = lsCodigoTipoOferta
           AND COD_PROD = lsCodigoProducto
           AND IND_ENVI_MENS = lsIndEnviaMensaje
           AND EST_VALI = 1
           AND COD_CONS IS NULL
           AND COD_REGI IS NULL
           AND COD_ZONA IS NULL;

        IF(lnOcurrencias > 0) THEN
          INSERT INTO MAV_PARAM_CORES_CABEC (
      		   PAIS_COD_PAIS,
      		   PACO_COR_PARA_CONF,
      		   CORE_COR_CONS_REST,
      		   CAM_PROC_MAV,
      		   IND_CONS_REST,
      		   NUM_REGI,
      		   IND_ACTI,
      		   USU_CREA,
      		   FEC_CREA,
      		   EST_REGI)
      		VALUES
            (lsCodigoPais,
        		 lnCorrelativoCab,
        		 1,
        		 lsCodigoPeriodo,
        		 'C',
        		 0,
        		 '1',
        		 psCodigoUsuario,
        		 SYSDATE,
        		 '1');

          INSERT INTO MAV_PARAM_CORES_DETAL(
      		   PAIS_COD_PAIS,
      		   PACO_COR_PARA_CONF,
      		   CORE_COR_CONS_REST,
      		   COR_CONS_REST_DETA,
      		   CAM_PROC_MAV,
      		   IND_CONS_REST,
      		   NUM_UNID_PROD,
      		   IND_ACTI,
      		   USU_CREA,
      		   FEC_CREA,
      		   EST_REGI)
        	VALUES
            (lsCodigoPais,
        		 lnCorrelativoCab,
        		 1,
        		 1,
        		 lsCodigoPeriodo,
        		 'C',
        		 NULL,
        		 '1',
        		 psCodigoUsuario,
        		 SYSDATE,
        		 '1');

        ELSE
          --CASO (2): Lista Cliente
          lbInsertado := FALSE;
          lnContador := 0;

          OPEN c_CargaMasivaConsultoras(lsCodigoPais, lsCodigoPeriodo, lsCodigoActividad,
                                        lsCodigoTipoOferta, lsCodigoProducto, lsIndEnviaMensaje);
          LOOP
            FETCH c_CargaMasivaConsultoras BULK COLLECT INTO interfazRecordC LIMIT W_FILAS;
            IF interfazRecordC.COUNT > 0 THEN

              FOR y IN interfazRecordC.FIRST .. interfazRecordC.LAST LOOP

                IF(NOT lbInsertado) THEN
                  INSERT INTO MAV_PARAM_CORES_CABEC (
              		   PAIS_COD_PAIS,
              		   PACO_COR_PARA_CONF,
              		   CORE_COR_CONS_REST,
              		   CAM_PROC_MAV,
              		   IND_CONS_REST,
              		   NUM_REGI,
              		   IND_ACTI,
              		   USU_CREA,
              		   FEC_CREA,
              		   EST_REGI)
              		VALUES
                    (lsCodigoPais,
                		 lnCorrelativoCab,
                		 18,
                		 lsCodigoPeriodo,
                		 'C',
                		 0,
                		 '1',
                		 psCodigoUsuario,
                		 SYSDATE,
                		 '1');

                  lbInsertado := TRUE;
                END IF;
                lnContador := lnContador + 1;

                INSERT INTO MAV_PARAM_CORES_DETAL(
            		   PAIS_COD_PAIS,
            		   PACO_COR_PARA_CONF,
            		   CORE_COR_CONS_REST,
            		   COR_CONS_REST_DETA,
            		   CAM_PROC_MAV,
            		   IND_CONS_REST,
            		   NUM_UNID_PROD,
            		   VAL_CONS_REST_1,
            		   VAL_CONS_REST_2,
            		   IND_ACTI,
            		   USU_CREA,
            		   FEC_CREA,
            		   EST_REGI)
              	VALUES
                  (lsCodigoPais,
              		 lnCorrelativoCab,
              		 18,
              		 lnContador,
              		 lsCodigoPeriodo,
              		 'C',
              		 interfazRecordC(y).numeroUnidades,
              		 lsCodigoPais,
              		 interfazRecordC(y).codigoConsultora,
              		 '1',
              		 psCodigoUsuario,
              		 SYSDATE,
              		 '1');
              END LOOP;

            END IF;

            EXIT WHEN c_CargaMasivaConsultoras%NOTFOUND;
          END LOOP;
          CLOSE c_CargaMasivaConsultoras;

          IF(lbInsertado) THEN
            UPDATE MAV_PARAM_CORES_CABEC
               SET NUM_REGI = lnContador
             WHERE PAIS_COD_PAIS = psCodigoPais
               AND PACO_COR_PARA_CONF = lnCorrelativoCab
               AND CORE_COR_CONS_REST = 18;

            UPDATE MAV_PARAM_CONFI
               SET IND_UNID_DIF = 'S'
             WHERE PAIS_COD_PAIS = psCodigoPais
               AND COR_PARA_CONF = lnCorrelativoCab;
          END IF;

          --CASO (3): Lista Region/Zona
          lbInsertado := FALSE;
          lnContador := 0;

          OPEN c_CargaMasivaRegiones(psCodigoPais, lsCodigoPeriodo, lsCodigoActividad,
                                        lsCodigoTipoOferta, lsCodigoProducto, lsIndEnviaMensaje);
          LOOP
            FETCH c_CargaMasivaRegiones BULK COLLECT INTO interfazRecordR LIMIT W_FILAS;
            IF interfazRecordR.COUNT > 0 THEN

              FOR y IN interfazRecordR.FIRST .. interfazRecordR.LAST LOOP

                IF(NOT lbInsertado) THEN
                  INSERT INTO MAV_PARAM_CORES_CABEC (
              		   PAIS_COD_PAIS,
              		   PACO_COR_PARA_CONF,
              		   CORE_COR_CONS_REST,
              		   CAM_PROC_MAV,
              		   IND_CONS_REST,
              		   NUM_REGI,
              		   IND_ACTI,
              		   USU_CREA,
              		   FEC_CREA,
              		   EST_REGI)
              		VALUES
                    (lsCodigoPais,
                		 lnCorrelativoCab,
                		 30,
                		 lsCodigoPeriodo,
                		 'C',
                		 0,
                		 '1',
                		 psCodigoUsuario,
                		 SYSDATE,
                		 '1');

                  lbInsertado := TRUE;
                END IF;
                lnContador := lnContador + 1;

                INSERT INTO MAV_PARAM_CORES_DETAL(
            		   PAIS_COD_PAIS,
            		   PACO_COR_PARA_CONF,
            		   CORE_COR_CONS_REST,
            		   COR_CONS_REST_DETA,
            		   CAM_PROC_MAV,
            		   IND_CONS_REST,
            		   NUM_UNID_PROD,
            		   VAL_CONS_REST_1,
            		   VAL_CONS_REST_2,
            		   IND_ACTI,
            		   USU_CREA,
            		   FEC_CREA,
            		   EST_REGI)
              	VALUES
                  (lsCodigoPais,
              		 lnCorrelativoCab,
              		 30,
              		 lnContador,
              		 lsCodigoPeriodo,
              		 'C',
              		 interfazRecordR(y).numeroUnidades,
              		 interfazRecordR(y).codigoRegion,
              		 interfazRecordR(y).codigoZona,
              		 '1',
              		 psCodigoUsuario,
              		 SYSDATE,
              		 '1');
              END LOOP;

            END IF;

            EXIT WHEN c_CargaMasivaRegiones%NOTFOUND;
          END LOOP;
          CLOSE c_CargaMasivaRegiones;

          IF(lbInsertado) THEN
            UPDATE MAV_PARAM_CORES_CABEC
               SET NUM_REGI = lnContador
             WHERE PAIS_COD_PAIS = lsCodigoPais
               AND PACO_COR_PARA_CONF = lnCorrelativoCab
               AND CORE_COR_CONS_REST = 30;

            UPDATE MAV_PARAM_CONFI
               SET IND_UNID_DIF = 'S'
             WHERE PAIS_COD_PAIS = psCodigoPais
               AND COR_PARA_CONF = lnCorrelativoCab;
          END IF;

        END IF;

      END LOOP;
    END IF;

    EXIT WHEN c_CargaMasivaCabec%NOTFOUND;
  END LOOP;
  CLOSE c_CargaMasivaCabec;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_ACTUA_CARGA_MASIV: (' || ln_sqlcode || ')' || ls_sqlerrm);

END MAV_PR_ACTUA_CARGA_MASIV;


/**************************************************************************
Descripcion       : Valida que el producto exista en la Matriz de Precios

Fecha Creacion    : 21/01/2013
Parametros Entrada:
  pnOidPais         :  oid Pais
  pnOidAcceso       :  oid Acceso
  pnOidSubAcceso    :  oid SubAcceso
  pnOidPeriodo      :  oid Periodo
  pnOidTipoOferta   :  oid Tipo Oferta
  pnOidCicloVida    :  oid Ciclo Vida
  pnOidFormaPago    :  oid Forma Pago
  pnOidFormaCobro   :  oid Forma Cobro
  pnPrecio          :  oid Precio

Autor             : Sergio Apaza

***************************************************************************/
FUNCTION MAV_FN_VALID_PRODU(pnOidPais            NUMBER,
                            pnOidAcceso          NUMBER,
                            pnOidSubAcceso       NUMBER,
                            pnOidPeriodo         NUMBER,
                            pnOidTipoOferta      NUMBER,
                            pnOidCicloVida       NUMBER,
                            pnOidFormaPago       NUMBER,
                            pnOidFormaCobro      NUMBER,
                            psCodigoSAP          VARCHAR2,
                            pnPrecio             NUMBER)
RETURN NUMBER IS
  lnOidMatriz            PRE_MATRI_FACTU_CABEC.OID_CABE%TYPE;
  lnOidProducto          MAE_PRODU.OID_PROD%TYPE;
  lnOidDetOfer           PRE_OFERT_DETAL.OID_DETA_OFER%TYPE;

  lnPrecioCatalogo       NUMBER;
  lnPrecioContable       NUMBER;
BEGIN
  --Se verifica que se tenga la Cabecera de Matriz
  BEGIN
    SELECT OID_CABE
      INTO lnOidMatriz
      FROM PRE_MATRI_FACTU_CABEC
     WHERE PERD_OID_PERI = pnOidPeriodo;
  EXCEPTION
    WHEN OTHERS THEN
      lnOidMatriz := NULL;
  END;

  --Se verifica que se encuentra en el Maestro de Materiales
  BEGIN
    SELECT OID_PROD
      INTO lnOidProducto
      FROM MAE_PRODU
     WHERE COD_SAP = psCodigoSAP;
  EXCEPTION
    WHEN OTHERS THEN
      lnOidProducto := NULL;
  END;

  lnOidDetOfer := NULL;

  IF((lnOidMatriz IS NOT NULL) AND (lnOidProducto IS NOT NULL)) THEN
    FOR x IN (SELECT DET.VAL_CODI_VENT,
                     DET.OID_DETA_OFER,
                     DET.FOPA_OID_FORM_PAGO,
                     DET.IMP_PREC_CATA,
                     DET.IMP_PREC_POSI
                FROM PRE_OFERT_DETAL DET,
                     PRE_OFERT OFE
               WHERE OFE.MFCA_OID_CABE = lnOidMatriz
                 /*AND OFE.ACCE_OID_ACCE = pnOidAcceso
                 AND OFE.SBAC_OID_SBAC = pnOidSubAcceso*/
                 AND OFE.OID_OFER = DET.OFER_OID_OFER
                 AND DET.PROD_OID_PROD = lnOidProducto
                 AND DET.TOFE_OID_TIPO_OFER = pnOidTipoOferta
                 AND DET.CIVI_OID_CICLO_VIDA = pnOidCicloVida) LOOP

      IF((pnOidFormaPago IS NULL AND x.FOPA_OID_FORM_PAGO IS NULL) OR (pnOidFormaPago = x.FOPA_OID_FORM_PAGO)) THEN
        IF(pnOidFormaCobro = 1) THEN --Gratis
          IF(pnPrecio = x.IMP_PREC_POSI) THEN
            RETURN x.OID_DETA_OFER;
          END IF;
        ELSE
          IF(pnPrecio = x.IMP_PREC_CATA) THEN
        RETURN x.OID_DETA_OFER;
          END IF;
        END IF;
      END IF;

    END LOOP;

    IF(pnOidFormaCobro = 1) THEN --GRATIS
      lnPrecioCatalogo := 0;
      lnPrecioContable := pnPrecio;
    ELSE
      lnPrecioCatalogo := pnPrecio;
      lnPrecioContable := 0;
    END IF;

    --Si no se encontro se solicita a Matriz de precios (caso de uso 'Insertar producto en matriz')
    lnOidDetOfer := PED_PKG_CUADR_OFERT.PED_FN_INSER_OFER_MAV(lnOidProducto,
                                                               pnOidTipoOferta,
                                                               lnPrecioCatalogo,
                                                               lnPrecioContable,
                                                               pnOidFormaPago,
                                                               pnOidPeriodo);

    IF(lnOidDetOfer = 0) THEN --Hubo un error al crear el codigo de Venta
      lnOidDetOfer := NULL;
    END IF;
  END IF;

  RETURN lnOidDetOfer;

EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;

END MAV_FN_VALID_PRODU;


/**************************************************************************
Descripcion       : Proceso Generar Envio MAV

Fecha Creacion    : 22/01/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoPeriodo  :  Codigo de Periodo
  psCodigoActividad  :  Codigo de Actividad
  psCodigoUsuario   : Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_GENER_ENVIO(psCodigoPais          VARCHAR2,
                             psCodigoPeriodo       VARCHAR2,
                             psCodigoActividad     VARCHAR2,
                             pscodigoTipoMav       VARCHAR2,
                             psCodigoUsuario       VARCHAR2)
IS

  lnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal              SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;
  lnOidAcceso             SEG_ACCES.OID_ACCE%TYPE;
  lnOidSubAcceso          SEG_SUBAC.OID_SBAC%TYPE;
  lnTipoOferta            PRE_TIPO_OFERT.OID_TIPO_OFER%TYPE;

  lnConsiListaClientes    MAV_CONSI_RESTR.COR_CONS_REST%TYPE;
  lnConsiListaRegiones    MAV_CONSI_RESTR.COR_CONS_REST%TYPE;
  lnTieneLista            NUMBER;
  lnOidMatrizFact         NUMBER;
  lnOidDetOfer            NUMBER;
  lsCodigoVenta           PRE_OFERT_DETAL.VAL_CODI_VENT%TYPE;
  leValidaProducto EXCEPTION;

  lnOcurrencias           NUMBER;
  lbDespachar             BOOLEAN;
  lnTieneFormula          NUMBER;
  lnTieneVariable         NUMBER;
  lsCampoFV               VARCHAR2(20);
  lsCampoFVP              VARCHAR2(20);
  lnNroPeriodosFVP        NUMBER;
  lsCodPeriodoFVP         VARCHAR2(20);
  lnOidPeriodoFVP         NUMBER;
  lnNumeroUnidades        NUMBER;
  lnNumeroTotales         NUMBER;

  CURSOR cursorMAV IS
    SELECT M.PAIS_COD_PAIS,
           M.COR_PARA_CONF,
           M.ATOF_OID_TIPO_OFER_ACTI,
           M.COD_SAP,
           M.COD_TIPO_CLIE,
           M.NUM_UNID_PROD,
           M.VAL_PREC,
           M.FOR_COBR,
           M.ACT_TIPO_DESP,
           M.FOR_PAGO,
           M.ATOF_ACTI_OID_ACTI,
           M.IND_UNID_DIF
      FROM MAV_PARAM_CONFI M
     WHERE M.PAIS_COD_PAIS = psCodigoPais
       AND M.CAM_PARA_MAV = psCodigoPeriodo
       AND M.COD_ESTA_MAV = 2 --2: Completado
       AND M.COD_ACTI = NVL(psCodigoActividad, M.COD_ACTI)
       AND M.TICL_OID_TIPO_CLIE = NVL(pscodigoTipoMav, M.TICL_OID_TIPO_CLIE)
       AND M.EST_REGI = '1'
       AND M.IND_ACTI = '1'
     ORDER BY 1;

BEGIN

  --Recuperamos el oid Pais,Marca,Canal, Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);

  --Recuperamos el oid Acceso
  SELECT OID_ACCE INTO lnOidAcceso FROM SEG_ACCES WHERE COD_ACCE = 'GZ';

  --Recuperamos el oid Acceso
  SELECT OID_SBAC INTO lnOidSubAcceso FROM SEG_SUBAC WHERE COD_SBAC = '000';

  --Obtenemos el oid Consideracion para Lista de Clientes
  SELECT COR_CONS_REST
    INTO lnConsiListaClientes
    FROM MAV_CONSI_RESTR
   WHERE ABR_CONS_REST = 'LCL' AND IND_CONS_REST = 'C';

  --Obtenemos el oid Consideracion para Lista de Regiones
  SELECT COR_CONS_REST
    INTO lnConsiListaRegiones
    FROM MAV_CONSI_RESTR
   WHERE ABR_CONS_REST = 'LRG' AND IND_CONS_REST = 'C';

  FOR cMAV IN cursorMAV LOOP
    --Obtenemos el Oid Tipo Oferta
    SELECT TA.TOFE_OID_TIPO_OFER
      INTO lnTipoOferta
      FROM MAV_ACTIV_TIPO_OFERT TA
     WHERE TA.OID_TIPO_OFER_ACTI = cMAV.ATOF_OID_TIPO_OFER_ACTI;

    --Recuperamos el Oid Detalle Oferta asociado al MAV, para ello invocamos
    --a la funcion de Validar Productos
    lnOidDetOfer := MAV_PKG_PROCE.MAV_FN_VALID_PRODU(lnOidPais,
                                                      lnOidAcceso,
                                                      lnOidSubAcceso,
                                                      lnOidPeriodo,
                                                      lnTipoOferta,
                                                      4,
                                                      cMAV.FOR_PAGO,
                                                      cMAV.FOR_COBR,
                                                      cMAV.COD_SAP,
                                                      cMAV.VAL_PREC);

    IF lnOidDetOfer IS NULL THEN
        RAISE leValidaProducto;
    END IF;

    IF(lnOidDetOfer IS NOT NULL) THEN
      --Obtenemos el oid Matriz Facturacion
      SELECT MF.OID_MATR_FACT,
             D.VAL_CODI_VENT
        INTO lnOidMatrizFact,
             lsCodigoVenta
        FROM PRE_OFERT_DETAL       D,
             PRE_CATAL             C,
             PRE_OFERT             O,
             PRE_MATRI_FACTU_CABEC M,
             PRE_MATRI_FACTU       MF
       WHERE D.OCAT_OID_CATAL = C.OID_CATA(+)
         AND M.PERD_OID_PERI = lnOidPeriodo
         AND M.OID_CABE = O.MFCA_OID_CABE
         AND O.OID_OFER = D.OFER_OID_OFER
         AND M.OID_CABE = O.MFCA_OID_CABE
         AND MF.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
         AND MF.MFCA_OID_CABE = M.OID_CABE
         AND D.OID_DETA_OFER = lnOidDetOfer;

      --Verificamos si tiene Lista de Clientes el detalle MAV
      SELECT COUNT(1)
        INTO lnTieneLista
        FROM MAV_PARAM_CORES_DETAL D
       WHERE D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
         AND D.PACO_COR_PARA_CONF= cMAV.COR_PARA_CONF
         AND D.CORE_COR_CONS_REST = lnConsiListaClientes
         AND D.IND_ACTI = 1
         AND D.EST_REGI = '1'
         AND ROWNUM = 1;

      IF lnTieneLista >0  THEN
        INSERT INTO MAV_ENVIO_CONFI
          (OID_ENVI,
           NUM_UNID,
           VAL_PREC,
           PAIS_COD_PAIS,--
           COR_PARA_CONF,--
           MAFA_OID_MATR_FACT,-- OBTENER EL IDETIFICADOR DE MATRIZ
           CLIE_OID_CLIE,
           ESEN_OID_ESTA_ENVI,
           FCOB_OID_FORM_COBR,
           ATDE_OID_ACTI_TIPO_DESP,
           FOPA_OID_FORM_PAGO,
           IND_ENVI,
           ACTI_OID_ACTI,
           IND_ACTI,
           USU_CREA,
           FEC_CREA,
           EST_REGI,
           TIP_CONS_DESP,
           COD_REGI,
           COD_ZONA
          )
        SELECT MAV_MENC_SEQ.NEXTVAL,
               D.NUM_UNID_PROD,
               cMAV.VAL_PREC,
               cMAV.PAIS_COD_PAIS,
               cMAV.COR_PARA_CONF,
               lnOidMatrizFact,
               C.OID_CLIE,
               1,
               cMAV.FOR_COBR,
               cMAV.ACT_TIPO_DESP,
               cMAV.FOR_PAGO,
               'P',
               cMAV.ATOF_ACTI_OID_ACTI,
               1,
               psCodigoUsuario,
               SYSDATE,
               1,
               'CO',
               GEN_PKG_GENER.gen_fn_clien_datos(C.COD_CLIE, 'COD_REGI'),--CodRegiCliente
               GEN_PKG_GENER.gen_fn_clien_datos(C.COD_CLIE, 'COD_ZONA')--CodZonaCliente
          FROM MAV_PARAM_CORES_DETAL D,
               MAE_CLIEN C--,
               --ZON_REGIO R,
               --ZON_ZONA Z
         WHERE C.COD_CLIE = D.VAL_CONS_REST_2
           AND C.PAIS_OID_PAIS = lnOidPais
           AND D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
           AND D.PACO_COR_PARA_CONF = cMAV.COR_PARA_CONF
           AND D.CORE_COR_CONS_REST = lnConsiListaClientes
           AND D.IND_ACTI = 1
           AND D.EST_REGI = '1';
        -- Se actualiza el estado del Detalle MAV
        UPDATE MAV_PARAM_CONFI M
           SET M.COD_ESTA_MAV = 4, -- GENERADO
               M.VAL_CODI_VENT = lsCodigoVenta,
               M.USU_MODI = psCodigoUsuario,
               M.FEC_MODI = SYSDATE
         WHERE M.PAIS_COD_PAIS = psCodigoPais
           AND M.COR_PARA_CONF = cMAV.COR_PARA_CONF;

      ELSIF cMAV.COD_TIPO_CLIE ='02' THEN -- Consultoras
        -- Se actualiza el estado del Detalle MAV
        UPDATE MAV_PARAM_CONFI M
           SET M.COD_ESTA_MAV = 3, -- INICIADO
               M.VAL_CODI_VENT = lsCodigoVenta,
               M.USU_MODI = psCodigoUsuario,
               M.FEC_MODI = SYSDATE
         WHERE M.PAIS_COD_PAIS = psCodigoPais
           AND M.COR_PARA_CONF = cMAV.COR_PARA_CONF;

      ELSIF cMAV.COD_TIPO_CLIE ='04' THEN -- Gerentes
        lnTieneFormula:=0;
        lnTieneVariable:=0;

        --Solo para MAV con Opcion Activar Unidad Diferenciada en NO
        IF(cMAV.IND_UNID_DIF = 'N') THEN
          --Verificamos si tiene Consideracion: Formula
          SELECT COUNT(1)
            INTO lnTieneFormula
            FROM MAV_PARAM_CORES_DETAL D
           WHERE D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
             AND D.PACO_COR_PARA_CONF= cMAV.COR_PARA_CONF
             AND D.CORE_COR_CONS_REST = 64
             AND D.IND_ACTI = 1
             AND D.EST_REGI = '1';

          --Verificamos si tiene Consideracion: Variable Venta
          SELECT COUNT(1)
            INTO lnTieneVariable
            FROM MAV_PARAM_CORES_DETAL D
           WHERE D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
             AND D.PACO_COR_PARA_CONF= cMAV.COR_PARA_CONF
             AND D.CORE_COR_CONS_REST = 65
             AND D.IND_ACTI = 1
             AND D.EST_REGI = '1';
        END IF;

        IF(lnTieneFormula > 0) THEN
          SELECT D.VAL_CONS_REST_1,
                 TO_NUMBER(D.VAL_CONS_REST_2)
            INTO lsCampoFVP,
                 lnNroPeriodosFVP
            FROM MAV_PARAM_CORES_DETAL D
           WHERE D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
             AND D.PACO_COR_PARA_CONF= cMAV.COR_PARA_CONF
             AND D.CORE_COR_CONS_REST = 64
             AND D.IND_ACTI = 1
             AND D.EST_REGI = '1';

          lsCodPeriodoFVP := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                     lnOidPais,
                                                                     lnOidMarca,
                                                                     lnOidCanal,
                                                                     lnNroPeriodosFVP);

          lnOidPeriodoFVP := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoFVP,
                                                                 lnOidMarca,
                                                                 lnOidCanal);

          lnTieneVariable := 0;

        END IF;

        IF(lnTieneVariable > 0) THEN
          SELECT D.VAL_CONS_REST_1,
                 TO_NUMBER(D.VAL_CONS_REST_2)
            INTO lsCampoFVP,
                 lnNroPeriodosFVP
            FROM MAV_PARAM_CORES_DETAL D
           WHERE D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
             AND D.PACO_COR_PARA_CONF= cMAV.COR_PARA_CONF
             AND D.CORE_COR_CONS_REST = 65
             AND D.IND_ACTI = 1
             AND D.EST_REGI = '1';

          lsCodPeriodoFVP := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(psCodigoPeriodo,
                                                                     lnOidPais,
                                                                     lnOidMarca,
                                                                     lnOidCanal,
                                                                     lnNroPeriodosFVP);

          lnOidPeriodoFVP := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodPeriodoFVP,
                                                                 lnOidMarca,
                                                                 lnOidCanal);

        END IF;

        lbDespachar := TRUE;
        IF((lnTieneFormula > 0) OR (lnTieneVariable > 0)) THEN
          --VERIFICAMOS SI HAY DATOS EN LA TABLA FUENTE VENTA PREVISTA
          SELECT COUNT(1)
            INTO lnOcurrencias
            FROM INT_FUENT_VENTA_PREVI_SAP
           WHERE PERD_OID_PERI = lnOidPeriodoFVP;

          IF(lnOcurrencias = 0) THEN
            lbDespachar := FALSE;
          ELSE
            IF(lnTieneVariable > 0) THEN
              SELECT SUM(DECODE(lsCampoFVP, 'AF', FVP.NUM_ACTI_FINA, 'IN', FVP.NUM_INGR, FVP.NUM_REIN))
                INTO lnNumeroTotales
                FROM MAV_PARAM_CORES_DETAL D,
                     ZON_REGIO R,
                     ZON_ZONA Z,
                     INT_FUENT_VENTA_PREVI_SAP FVP
               WHERE R.COD_REGI = D.VAL_CONS_REST_1
                 AND D.VAL_CONS_REST_3 IS NULL
                 AND Z.ZORG_OID_REGI = R.OID_REGI
                 AND Z.COD_ZONA = D.VAL_CONS_REST_2
                 AND D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
                 AND D.PACO_COR_PARA_CONF = cMAV.COR_PARA_CONF
                 AND D.CORE_COR_CONS_REST = lnConsiListaRegiones
                 AND D.IND_ACTI = 1
                 AND D.EST_REGI = '1'
                 AND FVP.PERD_OID_PERI = lnOidPeriodoFVP
                 AND Z.OID_ZONA = FVP.ZZON_OID_ZONA;
            END IF;
          END IF;
        END IF;

        IF(lbDespachar) THEN
          FOR y IN (SELECT x.NUM_UNID_PROD,
               x.CLIE_OID_CLIE,
                           x.TIP_CONS_DESP,
               x.COD_REGI,--CodRegiCliente
               x.COD_ZONA--CodZonaCliente
          FROM (SELECT NVL(R.CLIE_OID_CLIE,
                       (SELECT CL.OID_CLIE
                        FROM ZON_DIREC_VENTA_CABEC CA,
                            ZON_DIREC_VENTA_DETAL DE,
                            ZON_TIPO_CARGO CG,
                            MAE_CLIEN CL
                        WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                         AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                         AND CA.PAIS_COD_PAIS = psCodigoPais
                         AND CA.COD_CLIE = DE.COD_CLIE
                         AND CA.FEC_REGI = DE.DICA_FEC_REGI
                         AND CA.CAM_PROC = DE.DICA_CAM_PROC
                         AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                         AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                         AND CA.EST_REGI = '1'
                         AND DE.EST_REGI = '1'
                         AND R.COD_REGI = DE.COD_REGI
                         AND R.IND_ACTI = '1'
                         AND R.IND_BORR = '0'
                         AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                         AND CG.EST_REGI = '1'
                         AND CL.COD_CLIE = CA.COD_CLIE
                         AND CG.COD_TIPO_CARG = 'MVR'
                         AND CA.ESCA_COD_ESTA_CARG = 'A'
                         AND DE.COD_SUBG = '01'
                         )) AS CLIE_OID_CLIE,
                      DECODE(cMAV.IND_UNID_DIF, 'S', D.NUM_UNID_PROD, cMAV.NUM_UNID_PROD) NUM_UNID_PROD,
                      DECODE(R.CLIE_OID_CLIE, NULL, 'AR','GR') TIP_CONS_DESP,
                      R.COD_REGI, NULL COD_ZONA
                  FROM MAV_PARAM_CORES_DETAL D,
                       ZON_REGIO R
                 WHERE R.COD_REGI = D.VAL_CONS_REST_1
                   AND D.VAL_CONS_REST_2 IS NULL
                   AND D.VAL_CONS_REST_3 IS NULL
                   AND D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
                   AND D.PACO_COR_PARA_CONF = cMAV.COR_PARA_CONF
                   AND D.CORE_COR_CONS_REST = lnConsiListaRegiones
                   AND D.IND_ACTI = 1
                   AND D.EST_REGI = '1'
                UNION
                SELECT NVL(Z.CLIE_OID_CLIE,
                       (SELECT CL.OID_CLIE
                        FROM ZON_DIREC_VENTA_CABEC CA,
                            ZON_DIREC_VENTA_DETAL DE,
                            ZON_TIPO_CARGO CG,
                            MAE_CLIEN CL
                        WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                         AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                         AND CA.PAIS_COD_PAIS = psCodigoPais
                         AND CA.COD_CLIE = DE.COD_CLIE
                         AND CA.FEC_REGI = DE.DICA_FEC_REGI
                         AND CA.CAM_PROC = DE.DICA_CAM_PROC
                         AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                         AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                         AND CA.EST_REGI = '1'
                         AND DE.EST_REGI = '1'
                         AND R.COD_REGI = DE.COD_REGI
                         AND R.IND_ACTI = '1'
                         AND R.IND_BORR = '0'
                         AND Z.COD_ZONA = DE.COD_ZONA
                         AND Z.IND_ACTI = '1'
                         AND Z.IND_BORR = '0'
                         AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                         AND CG.EST_REGI = '1'
                         AND CL.COD_CLIE = CA.COD_CLIE
                         AND CG.COD_TIPO_CARG = 'MVZ'
                         AND CA.ESCA_COD_ESTA_CARG = 'A'
                         AND DE.COD_SUBG = '01'
                         )) AS CLIE_OID_CLIE,

                       DECODE(cMAV.IND_UNID_DIF, 'S', D.NUM_UNID_PROD, cMAV.NUM_UNID_PROD) NUM_UNID_PROD,
                       DECODE(Z.CLIE_OID_CLIE, NULL, 'AZ','GZ') TIP_CONS_DESP,
                       R.COD_REGI, Z.COD_ZONA
                  FROM MAV_PARAM_CORES_DETAL D,
                       ZON_REGIO R,
                       ZON_ZONA Z
                 WHERE R.COD_REGI = D.VAL_CONS_REST_1
                   AND D.VAL_CONS_REST_3 IS NULL
                   AND Z.ZORG_OID_REGI = R.OID_REGI
                   AND Z.COD_ZONA = D.VAL_CONS_REST_2
                   AND D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
                   AND D.PACO_COR_PARA_CONF = cMAV.COR_PARA_CONF
                   AND D.CORE_COR_CONS_REST = lnConsiListaRegiones
                   AND D.IND_ACTI = 1
                   AND D.EST_REGI = '1'
                UNION
                SELECT (SELECT CL.OID_CLIE FROM  ZON_DIREC_VENTA_CABEC CA,
                       ZON_DIREC_VENTA_DETAL DE,
                       ZON_TIPO_CARGO CG,
                       MAE_CLIEN CL
                 WHERE CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                   AND CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                   AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                   AND CA.COD_CLIE = DE.COD_CLIE
                   AND CA.FEC_REGI = DE.DICA_FEC_REGI
                   AND CA.CAM_PROC = DE.DICA_CAM_PROC
                   AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                   AND CA.EST_REGI = 1
                   AND DE.EST_REGI = 1
                   AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                   AND CG.EST_REGI = 1
                   AND CL.COD_CLIE = CA.COD_CLIE
                   AND CG.COD_TIPO_CARG = 'EE'
                   AND CA.ESCA_COD_ESTA_CARG = 'A'
                   AND DE.COD_SUBG = '01'
                   AND DE.COD_REGI = D.VAL_CONS_REST_3) CLIE_OID_CLIE,
                   DECODE(cMAV.IND_UNID_DIF, 'S', D.NUM_UNID_PROD, cMAV.NUM_UNID_PROD) NUM_UNID_PROD,
                      'CA' TIP_CONS_DESP,
                       D.VAL_CONS_REST_3 COD_REGI,
                       NULL COD_ZONA
                  FROM MAV_PARAM_CORES_DETAL D
                 WHERE D.VAL_CONS_REST_1 IS NULL
                   AND D.VAL_CONS_REST_2 IS NULL
                   AND D.PAIS_COD_PAIS = cMAV.PAIS_COD_PAIS
                   AND D.PACO_COR_PARA_CONF = cMAV.COR_PARA_CONF
                   AND D.CORE_COR_CONS_REST = lnConsiListaRegiones
                   AND D.IND_ACTI = 1
                   AND D.EST_REGI = '1'
                               )x )  LOOP

            lnNumeroUnidades := y.NUM_UNID_PROD;

            IF((lnTieneFormula > 0) AND (y.COD_ZONA IS NOT NULL)) THEN
              SELECT DECODE(lsCampoFVP, 'AF', FVP.NUM_ACTI_FINA, 'IN', FVP.NUM_INGR, FVP.NUM_REIN)
                INTO lnNumeroUnidades
                FROM INT_FUENT_VENTA_PREVI_SAP FVP,
                     ZON_ZONA ZON
               WHERE FVP.PERD_OID_PERI = lnOidPeriodoFVP
                 AND ZON.OID_ZONA = FVP.ZZON_OID_ZONA
                 AND ZON.COD_ZONA = y.COD_ZONA
                 AND ZON.IND_ACTI = '1';

              lnNumeroUnidades := lnNumeroUnidades * y.NUM_UNID_PROD;
            END IF;

            IF((lnTieneVariable > 0) AND (y.COD_ZONA IS NOT NULL)) THEN
              SELECT DECODE(lsCampoFVP, 'AF', FVP.NUM_ACTI_FINA, 'IN', FVP.NUM_INGR, FVP.NUM_REIN)
                INTO lnNumeroUnidades
                FROM INT_FUENT_VENTA_PREVI_SAP FVP,
                     ZON_ZONA ZON
               WHERE FVP.PERD_OID_PERI = lnOidPeriodoFVP
                 AND ZON.OID_ZONA = FVP.ZZON_OID_ZONA
                 AND ZON.COD_ZONA = y.COD_ZONA
                 AND ZON.IND_ACTI = '1';

              lnNumeroUnidades := TRUNC((lnNumeroUnidades * y.NUM_UNID_PROD) / lnNumeroTotales);
            END IF;

            INSERT INTO MAV_ENVIO_CONFI
              (OID_ENVI,
               NUM_UNID,
               VAL_PREC,
               PAIS_COD_PAIS,--
               COR_PARA_CONF,--
               MAFA_OID_MATR_FACT,-- OBTENER EL IDETIFICADOR DE MATRIZ
               CLIE_OID_CLIE,
               ESEN_OID_ESTA_ENVI,
               FCOB_OID_FORM_COBR,
               ATDE_OID_ACTI_TIPO_DESP,
               FOPA_OID_FORM_PAGO,
               IND_ENVI,
               ACTI_OID_ACTI,
               IND_ACTI,
               USU_CREA,
               FEC_CREA,
               EST_REGI,
               TIP_CONS_DESP,
               COD_REGI,
               COD_ZONA)
            VALUES
              (MAV_MENC_SEQ.NEXTVAL,
               lnNumeroUnidades,
               cMAV.VAL_PREC,
               cMAV.PAIS_COD_PAIS,
               cMAV.COR_PARA_CONF,
               lnOidMatrizFact,
               y.CLIE_OID_CLIE,
               1,
               cMAV.FOR_COBR,
               cMAV.ACT_TIPO_DESP,
               cMAV.FOR_PAGO,
               'P',
               cMAV.ATOF_ACTI_OID_ACTI,
               1,
               psCodigoUsuario,
               SYSDATE,
               1,
               y.TIP_CONS_DESP,
               y.COD_REGI,
               y.COD_ZONA);
          END LOOP;

        -- Se actualiza el estado del Detalle MAV
        UPDATE MAV_PARAM_CONFI M
           SET M.COD_ESTA_MAV = 4, -- GENERADO
               M.VAL_CODI_VENT = lsCodigoVenta,
               M.USU_MODI = psCodigoUsuario,
               M.FEC_MODI = SYSDATE
         WHERE M.PAIS_COD_PAIS = psCodigoPais
           AND M.COR_PARA_CONF = cMAV.COR_PARA_CONF;
        END IF;

      END IF;
    END IF;

  END LOOP;

EXCEPTION
    WHEN leValidaProducto THEN
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_GENER_ENVIO: La Función MAV_PKG_PROCE.MAV_FN_VALID_PRODU ha retornado NULL' );

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_GENER_ENVIO: ' || ls_sqlerrm);

END MAV_PR_GENER_ENVIO;

/**************************************************************************
  Descripcion       : Proceso Actualizar Envio MAV

  Fecha Creacion    : 21/08/2013
  Parametros Entrada:
    psCodigoCliente           : Codigo de Cliente
    psCodigoPais              : Codigo de Pais
    psCodigoRegion            : Codigo de Region
    psCodigoZona              : Codigo de Zona
    psIndEnvi                 : Indicador de Envio
    psIndAlta                 : Indicador Alta/Baja
    psCodigoCargo             : Codigo de Cargo
    psCodigoUsuario           : Codigo de Usuario

  Autor             : Juan Altamirano
***************************************************************************/
PROCEDURE MAV_PR_ACTUA_ENVIO(psCodigoCliente       VARCHAR2,
                             psCodigoPais          VARCHAR2,
                             psCodigoRegion        VARCHAR2,
                             psCodigoZona          VARCHAR2,
                             psIndEnvi             VARCHAR2,
                             psIndAlta             VARCHAR2,
                             psCodigoCargo         VARCHAR2,
                             psCodigoUsuario       VARCHAR2)
IS
 vsReg                   NUMBER;

BEGIN

--ENVIOS   DIRECTORIO
--GZ = GZ    region notnull, zona notnull
--GR = GR    region notnull, zona null
--AR = MVR   region notnull, zona null
--AZ = MVZ   region notnull, zona notnull
--CA = EE    region notnull, zona null



 IF (psIndAlta = 1) THEN --********************************* SI ES ALTA

      IF(psCodigoCargo = 'GR' OR psCodigoCargo = 'GZ') THEN -- es titular? GR,GZ
            IF(psCodigoCargo = 'GR') THEN --si es Gerente de Region
               SELECT COUNT(1) INTO vsReg
               FROM MAV_ENVIO_CONFI
                   WHERE PAIS_COD_PAIS = psCodigoPais
                    AND COD_REGI =  psCodigoRegion
                    AND COD_ZONA IS NULL
                    AND IND_ENVI = psIndEnvi
                    AND TIP_CONS_DESP = psCodigoCargo;

                IF(vsReg > 0) THEN --si lo encuentra
                     UPDATE MAV_ENVIO_CONFI
                       SET CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                       WHERE PAIS_COD_PAIS = psCodigoPais
                        AND COD_REGI =  psCodigoRegion
                        AND COD_ZONA IS NULL
                        AND IND_ENVI = psIndEnvi
                        AND TIP_CONS_DESP = psCodigoCargo;
                ELSE              --sino lo encuentra
                        --LO BUSCA COMO ALTERNO
                         --1. primero busco como MVR
                         select count(1) into vsReg
                           from MAV_ENVIO_CONFI
                               where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI =  psCodigoRegion
                                and COD_ZONA is null
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR
                         if(vsReg > 0) then
                             update MAV_ENVIO_CONFI
                             set CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                 TIP_CONS_DESP = 'AR',
                                      USU_MODI = psCodigoUsuario,
                                      FEC_MODI = sysdate
                              where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI =  psCodigoRegion
                                and COD_ZONA is null
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR
                         end if;

                         --2. segundo busco como MVZ
                         select count(1) into vsReg
                           from MAV_ENVIO_CONFI
                               where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI = psCodigoRegion
                                and COD_ZONA = psCodigoZona
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a Az
                         if(vsReg > 0) then
                             update MAV_ENVIO_CONFI
                             set CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                 TIP_CONS_DESP = 'AZ',
                                      USU_MODI = psCodigoUsuario,
                                      FEC_MODI = sysdate
                              where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI = psCodigoRegion
                                and COD_ZONA = psCodigoZona
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a Az
                         end if;

                END IF;

            ELSIF(psCodigoCargo = 'GZ') THEN --si es gerente de Zona
                SELECT COUNT(1) INTO vsReg
                 FROM MAV_ENVIO_CONFI
                     WHERE PAIS_COD_PAIS = psCodigoPais
                      AND COD_REGI =  psCodigoRegion
                      AND COD_ZONA = psCodigoZona
                      AND IND_ENVI = psIndEnvi
                      AND TIP_CONS_DESP = psCodigoCargo;

                IF(vsReg > 0) THEN --si lo encuentra
                     UPDATE MAV_ENVIO_CONFI
                       SET CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                        WHERE PAIS_COD_PAIS = psCodigoPais
                          AND COD_REGI =  psCodigoRegion
                          AND COD_ZONA = psCodigoZona
                          AND IND_ENVI = psIndEnvi
                          AND TIP_CONS_DESP = psCodigoCargo;

                ELSE              --sino lo encuentra

                        --LO BUSCA COMO ALTERNO
                         --1. primero busco como MVR
                         select count(1) into vsReg
                           from MAV_ENVIO_CONFI
                               where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI =  psCodigoRegion
                                and COD_ZONA is null
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR
                         if(vsReg > 0) then
                             update MAV_ENVIO_CONFI
                             set CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                 TIP_CONS_DESP = 'AR',
                                      USU_MODI = psCodigoUsuario,
                                      FEC_MODI = sysdate
                              where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI =  psCodigoRegion
                                and COD_ZONA is null
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR
                         end if;

                         --2. segundo busco como MVZ
                         select count(1) into vsReg
                           from MAV_ENVIO_CONFI
                               where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI = psCodigoRegion
                                and COD_ZONA = psCodigoZona
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a Az
                         if(vsReg > 0) then
                             update MAV_ENVIO_CONFI
                             set CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                 TIP_CONS_DESP = 'AZ',
                                      USU_MODI = psCodigoUsuario,
                                      FEC_MODI = sysdate
                              where PAIS_COD_PAIS = psCodigoPais
                                and COD_REGI = psCodigoRegion
                                and COD_ZONA = psCodigoZona
                                and IND_ENVI = psIndEnvi
                                and TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a Az
                         end if;
                END IF;

            END IF;

      ELSIF(psCodigoCargo = 'MVR' OR psCodigoCargo = 'MVZ') THEN -- es alterno? MVZ,MVR
            IF(psCodigoCargo = 'MVR') THEN
                 SELECT COUNT(1) INTO vsReg
                   FROM MAV_ENVIO_CONFI
                       WHERE PAIS_COD_PAIS = psCodigoPais
                        AND COD_REGI =  psCodigoRegion
                        AND COD_ZONA IS NULL
                        AND IND_ENVI = psIndEnvi
                        AND TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR

                IF(vsReg > 0) THEN --si lo encuentra
                     UPDATE MAV_ENVIO_CONFI
                       SET CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                         WHERE PAIS_COD_PAIS = psCodigoPais
                          AND COD_REGI =  psCodigoRegion
                          AND COD_ZONA IS NULL
                          AND IND_ENVI = psIndEnvi
                          AND TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR

                END IF;

            ELSIF(psCodigoCargo = 'MVZ') THEN
                  SELECT COUNT(1) INTO vsReg
                   FROM MAV_ENVIO_CONFI
                       WHERE PAIS_COD_PAIS = psCodigoPais
                        AND COD_REGI =  psCodigoRegion
                        AND COD_ZONA = psCodigoZona
                        AND IND_ENVI = psIndEnvi
                        AND TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a AZ

                  IF(vsReg > 0) THEN --si lo encuentra
                     UPDATE MAV_ENVIO_CONFI
                       SET CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                          WHERE PAIS_COD_PAIS = psCodigoPais
                            AND COD_REGI =  psCodigoRegion
                            AND COD_ZONA = psCodigoZona
                            AND IND_ENVI = psIndEnvi
                            AND TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a AZ
                  END IF;

            END IF;

      ELSE -- es capacitador? EE
          SELECT COUNT(1) INTO vsReg
             FROM MAV_ENVIO_CONFI
                 WHERE PAIS_COD_PAIS = psCodigoPais
                  AND COD_REGI =  psCodigoRegion
                  AND COD_ZONA IS NULL
                  AND IND_ENVI = psIndEnvi
                  AND TIP_CONS_DESP = 'CA'; --el cargo es equivalente a CA

           IF(vsReg > 0) THEN --si lo encuentra
               UPDATE MAV_ENVIO_CONFI
                 SET CLIE_OID_CLIE = psCodigoCliente, --actualiza OidCliente
                          USU_MODI = psCodigoUsuario,
                          FEC_MODI = SYSDATE
                    WHERE PAIS_COD_PAIS = psCodigoPais
                      AND COD_REGI =  psCodigoRegion
                      AND COD_ZONA IS NULL
                      AND IND_ENVI = psIndEnvi
                      AND TIP_CONS_DESP = 'CA'; --el cargo es equivalente a CA
            END IF;


      END IF;

    ELSE  --******************************** SI ES BAJA
          IF(psCodigoCargo = 'GR' OR psCodigoCargo = 'GZ') THEN -- es titular? GR,GZ
             IF(psCodigoCargo = 'GR') THEN --si es Gerente de Region
                   SELECT COUNT(1) INTO vsReg
                   FROM MAV_ENVIO_CONFI
                       WHERE PAIS_COD_PAIS = psCodigoPais
                        AND COD_REGI =  psCodigoRegion
                        AND COD_ZONA IS NULL
                        AND IND_ENVI = psIndEnvi
                        AND TIP_CONS_DESP = psCodigoCargo;

                    IF(vsReg > 0) THEN --si lo encuentra
                         UPDATE MAV_ENVIO_CONFI
                           SET CLIE_OID_CLIE = NULL, --limpia el OidCliente
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                           WHERE PAIS_COD_PAIS = psCodigoPais
                            AND COD_REGI =  psCodigoRegion
                            AND COD_ZONA IS NULL
                            AND IND_ENVI = psIndEnvi
                            AND TIP_CONS_DESP = psCodigoCargo;
                    END IF;

                ELSIF(psCodigoCargo = 'GZ') THEN --si es gerente de Zona
                    SELECT COUNT(1) INTO vsReg
                     FROM MAV_ENVIO_CONFI
                         WHERE PAIS_COD_PAIS = psCodigoPais
                          AND COD_REGI =  psCodigoRegion
                          AND COD_ZONA = psCodigoZona
                          AND IND_ENVI = psIndEnvi
                          AND TIP_CONS_DESP = psCodigoCargo;

                    IF(vsReg > 0) THEN --si lo encuentra
                         UPDATE MAV_ENVIO_CONFI
                           SET CLIE_OID_CLIE = NULL, --limpia el OidCliente
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                            WHERE PAIS_COD_PAIS = psCodigoPais
                              AND COD_REGI =  psCodigoRegion
                              AND COD_ZONA = psCodigoZona
                              AND IND_ENVI = psIndEnvi
                              AND TIP_CONS_DESP = psCodigoCargo;
                    END IF;

                END IF;

          ELSIF(psCodigoCargo = 'MVR' OR psCodigoCargo = 'MVZ') THEN -- es alterno? MVZ,MVR
                IF(psCodigoCargo = 'MVR') THEN
                     SELECT COUNT(1) INTO vsReg
                       FROM MAV_ENVIO_CONFI
                           WHERE PAIS_COD_PAIS = psCodigoPais
                            AND COD_REGI =  psCodigoRegion
                            AND COD_ZONA IS NULL
                            AND IND_ENVI = psIndEnvi
                            AND TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR

                    IF(vsReg > 0) THEN --si lo encuentra
                         UPDATE MAV_ENVIO_CONFI
                           SET CLIE_OID_CLIE = NULL, --limpia el OidCliente
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                             WHERE PAIS_COD_PAIS = psCodigoPais
                              AND COD_REGI =  psCodigoRegion
                              AND COD_ZONA IS NULL
                              AND IND_ENVI = psIndEnvi
                              AND TIP_CONS_DESP = 'AR'; --el cargo es equivalente a AR

                    END IF;

                ELSIF(psCodigoCargo = 'MVZ') THEN
                      SELECT COUNT(1) INTO vsReg
                       FROM MAV_ENVIO_CONFI
                           WHERE PAIS_COD_PAIS = psCodigoPais
                            AND COD_REGI =  psCodigoRegion
                            AND COD_ZONA = psCodigoZona
                            AND IND_ENVI = psIndEnvi
                            AND TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a AZ

                      IF(vsReg > 0) THEN --si lo encuentra
                         UPDATE MAV_ENVIO_CONFI
                           SET CLIE_OID_CLIE = NULL, --limpia el OidCliente
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                              WHERE PAIS_COD_PAIS = psCodigoPais
                                AND COD_REGI =  psCodigoRegion
                                AND COD_ZONA = psCodigoZona
                                AND IND_ENVI = psIndEnvi
                                AND TIP_CONS_DESP = 'AZ'; --el cargo es equivalente a AZ
                      END IF;

                END IF;


          ELSE -- es capacitador? EE
               SELECT COUNT(1) INTO vsReg
                 FROM MAV_ENVIO_CONFI
                     WHERE PAIS_COD_PAIS = psCodigoPais
                      AND COD_REGI =  psCodigoRegion
                      AND COD_ZONA IS NULL
                      AND IND_ENVI = psIndEnvi
                      AND TIP_CONS_DESP = 'CA'; --el cargo es equivalente a CA

               IF(vsReg > 0) THEN --si lo encuentra
                   UPDATE MAV_ENVIO_CONFI
                     SET CLIE_OID_CLIE = NULL, --limpia el OidCliente
                              USU_MODI = psCodigoUsuario,
                              FEC_MODI = SYSDATE
                        WHERE PAIS_COD_PAIS = psCodigoPais
                          AND COD_REGI =  psCodigoRegion
                          AND COD_ZONA IS NULL
                          AND IND_ENVI = psIndEnvi
                          AND TIP_CONS_DESP = 'CA'; --el cargo es equivalente a CA
                END IF;

          END IF;

    END IF; --FIN CONDICION ALTA Y BAJA


EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_ACTUA_ENVIO: ' || ls_sqlerrm);

END MAV_PR_ACTUA_ENVIO;

/**************************************************************************
Descripcion       : Proceso Validar Agregados MAV

Fecha Creacion    : 01/02/2013
Parametros Entrada:
  pnOidSolicitud     :  Oid Solicitud

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_VALID_AGREG(pnOidSolicitud        NUMBER)
IS

  lnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal              SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;

  lnOidCliente            MAE_CLIEN.OID_CLIE%TYPE;
  lsCodCliente            MAE_CLIEN.COD_CLIE%TYPE;
  lsCodPeriodo            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnTipoOferta            PRE_TIPO_OFERT.OID_TIPO_OFER%TYPE;
  lnCodigoPosicion        PED_SOLIC_POSIC.COD_POSI%TYPE;

  lnEncontroCORE          NUMBER;
  lnRegionCerrada         NUMBER;
  lnOidEstadoActu         NUMBER(12);
  lnPeriodoActual         SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnPeriodoAnt            SEG_PERIO_CORPO.COD_PERI%TYPE;
  lnOidEstado             MAE_CLIEN_HISTO_ESTAT.ESTA_OID_ESTA_CLIE%TYPE;
  lnExisteOid             NUMBER;
  lnOidPeriodoAnt         CRA_PERIO.OID_PERI%TYPE;
  lbCumple                BOOLEAN;
  lsFechaIni              VARCHAR2(8);
  lsFechaFinal            VARCHAR2(8);
  lsAnho                  VARCHAR2(4);
  lsAnhoInicial           VARCHAR2(4);
  lsAnhoFinal             VARCHAR2(4);

  lnOidMatrizFact         NUMBER;
  lnOidDetOfer            NUMBER;
  lnRangoInicio           NUMBER;
  lnRangoFin              NUMBER;
  lnUnidades              NUMBER;

  lnOidPeriodoIni         NUMBER;
  lnOidPeriodoFin         NUMBER;
  lnMonto                 NUMBER;
  lnMarca                 NUMBER;
  lnUnidadNegocio         NUMBER;
  lnNegocio               NUMBER;
  lnCatalogo              NUMBER;
  lsCodigoUsuario         VARCHAR2(20);
  lsFlagPeriodoEnRango       VARCHAR2(1);
  lsTipoVenta             MAV_PARAM_CORES_DETAL.VAL_CONS_REST_5%TYPE;

  lnMontoDesde            NUMBER;
  lnMontoHasta            NUMBER;
  lsCodPeriodoAnt         SEG_PERIO_CORPO.COD_PERI%TYPE;
  lsCodPeriodoFlex        SEG_PERIO_CORPO.COD_PERI%TYPE;

  CURSOR cursorMAV(codigoPeriodo VARCHAR2, oidCliente NUMBER) IS
    SELECT DISTINCT
           mav.PAIS_COD_PAIS,
           crcab.CAM_PROC_MAV,
           mav.COR_PARA_CONF,
           mav.NUM_UNID_PROD,
           mav.VAL_PREC,
           mav.FOR_COBR,
           mav.ACT_TIPO_DESP,
           mav.FOR_PAGO,
           mav.ATOF_ACTI_OID_ACTI,
           mav.ATOF_OID_TIPO_OFER_ACTI,
           mav.COD_SAP,
           mav.IND_UNID_DIF
      FROM MAV_PARAM_CONFI mav,
           MAV_PARAM_CORES_CABEC crcab,
           MAV_CONSI_RESTR cr
     WHERE crcab.PAIS_COD_PAIS = mav.PAIS_COD_PAIS
       AND crcab.PACO_COR_PARA_CONF = mav.COR_PARA_CONF
       AND crcab.CAM_PROC_MAV =  codigoPeriodo
       AND crcab.CORE_COR_CONS_REST = cr.COR_CONS_REST
       AND mav.EST_REGI = '1'
       AND mav.IND_ACTI = 1
       AND crcab.EST_REGI = '1'
       AND crcab.IND_ACTI = 1
       AND cr.EST_REGI = '1'
       AND cr.IND_ACTI = 1
       AND cr.ABR_CONS_REST <> 'LCL'
       AND cr.IND_DIRI = 'C'
       AND mav.COD_ESTA_MAV = 3 --INICIADO
       AND NOT EXISTS (SELECT 1
                         FROM MAV_ENVIO_CONFI mec
                        WHERE mec.Clie_Oid_Clie = oidCliente
                          AND mec.cor_para_conf = mav.cor_para_conf)
     ORDER BY mav.COR_PARA_CONF;

  CURSOR cursorConsRest(codigoPeriodo VARCHAR2, codigoPais VARCHAR2, oidParamMAV NUMBER) IS
    SELECT CAM_PROC_MAV,
           PAIS_COD_PAIS,
           PACO_COR_PARA_CONF,
           COR_CONS_REST,
           ABR_CONS_REST,
           IND_CONS_REST,
           IND_TIPO,
           IND_DIRI,
           CORE_COR_CONS_REST,
           VAL_CONS_REST_1,
           VAL_CONS_REST_2,
           VAL_CONS_REST_3,
           VAL_CONS_REST_4,
           VAL_CONS_REST_5
      FROM
          (SELECT CRDET.CAM_PROC_MAV,
                 CRDET.PAIS_COD_PAIS,
                 CRDET.PACO_COR_PARA_CONF,
                 CR.COR_CONS_REST,
                 CR.ABR_CONS_REST,
                 CR.IND_CONS_REST,
                 CR.IND_TIPO,
                 CR.IND_DIRI,
                 CRCAB.CORE_COR_CONS_REST,
                 CRDET.VAL_CONS_REST_1,
                 CRDET.VAL_CONS_REST_2,
                 CRDET.VAL_CONS_REST_3,
                 CRDET.VAL_CONS_REST_4,
                 CRDET.VAL_CONS_REST_5
            FROM MAV_PARAM_CORES_CABEC CRCAB,
                 MAV_PARAM_CORES_DETAL CRDET,
                 MAV_CONSI_RESTR CR
           WHERE CRCAB.PAIS_COD_PAIS = codigoPais
             AND CRCAB.PACO_COR_PARA_CONF = oidParamMAV
             AND CRCAB.CAM_PROC_MAV = codigoPeriodo
             AND CRDET.PAIS_COD_PAIS = CRCAB.PAIS_COD_PAIS
             AND CRDET.PACO_COR_PARA_CONF = CRCAB.PACO_COR_PARA_CONF
             AND CRDET.CORE_COR_CONS_REST = CRCAB.CORE_COR_CONS_REST
             AND CR.COR_CONS_REST = CRCAB.CORE_COR_CONS_REST
             AND CR.ABR_CONS_REST NOT IN ('UAS', 'TCL')
             AND CRCAB.EST_REGI = '1'
             AND CRCAB.IND_ACTI = 1
             AND CRDET.EST_REGI = '1'
             AND CRDET.IND_ACTI = 1

           UNION

           SELECT CRCAB.CAM_PROC_MAV,
                 CRCAB.PAIS_COD_PAIS,
                 CRCAB.PACO_COR_PARA_CONF,
                 CR.COR_CONS_REST,
                 CR.ABR_CONS_REST,
                 CR.IND_CONS_REST,
                 CR.IND_TIPO,
                 CR.IND_DIRI,
                 CRCAB.CORE_COR_CONS_REST,
                 NULL,
                 NULL,
                 NULL,
                 NULL,
                 NULL
            FROM MAV_PARAM_CORES_CABEC CRCAB,
                 MAV_CONSI_RESTR CR
           WHERE CRCAB.PAIS_COD_PAIS = codigoPais
             AND CRCAB.PACO_COR_PARA_CONF = oidParamMAV
             AND CRCAB.CAM_PROC_MAV = codigoPeriodo
             AND CR.COR_CONS_REST = CRCAB.CORE_COR_CONS_REST
             AND CR.ABR_CONS_REST IN ('UAS', 'TCL')
             AND CRCAB.EST_REGI = '1'
             AND CRCAB.IND_ACTI = 1
             AND (SELECT COUNT(1)
                    FROM MAV_PARAM_CORES_DETAL CRDET
                   WHERE CRDET.PAIS_COD_PAIS = CRCAB.PAIS_COD_PAIS
                     AND CRDET.PACO_COR_PARA_CONF = CRCAB.PACO_COR_PARA_CONF
                     AND CRDET.CORE_COR_CONS_REST = CRCAB.CORE_COR_CONS_REST
                     AND CRDET.EST_REGI = '1'
                     AND CRDET.IND_ACTI = 1) > 0
           )
     ORDER BY IND_CONS_REST DESC, COR_CONS_REST;

BEGIN

  --Recuperamos el oid Pais, oidCliente y Periodo
  SELECT PAIS_OID_PAIS,
         CLIE_OID_CLIE,
         PERD_OID_PERI
    INTO lnOidPais,
         lnOidCliente,
         lnOidPeriodo
    FROM PED_SOLIC_CABEC
   WHERE OID_SOLI_CABE = pnOidSolicitud;

  --Obtenemos el Codigo Cliente
  SELECT COD_CLIE
    INTO lsCodCliente
    FROM MAE_CLIEN
   WHERE OID_CLIE = lnOidCliente;

  --Obtenemos el Codigo Periodo
  lsCodPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_CODIG_PERIO(lnOidPeriodo);
  lsCodigoUsuario := 'ADMIN';

  --Recuperamos el OidMarca y OidCanal
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

  --Recuperamos la fecha Inicio, la fecha Fin y el año del periodo
  SELECT TO_CHAR(cra.FEC_INIC,'yyyyMMdd'),
         TO_CHAR(cra.FEC_FINA,'yyyyMMdd'),
         cor.VAL_ANIO,
         TO_CHAR(cra.FEC_INIC,'yyyy'),
         TO_CHAR(cra.FEC_FINA,'yyyy')
    INTO lsFechaIni,
         lsFechaFinal,
         lsAnho,
         lsAnhoInicial,
         lsAnhoFinal
    FROM CRA_PERIO cra,
         SEG_PERIO_CORPO cor
   WHERE cra.OID_PERI = lnOidPeriodo
     AND cra.PERI_OID_PERI = cor.OID_PERI;

  --PROCESAMOS LOS DETALLES MAV PARA EL CLIENTE QUE PASA LA SOLICITUD
  FOR cMAV IN cursorMAV(lsCodPeriodo, lnOidCliente) LOOP
    lbCumple := FALSE;

    FOR cCORE IN cursorConsRest(cMAV.CAM_PROC_MAV	, cMAV.PAIS_COD_PAIS, cMAV.COR_PARA_CONF) LOOP
      lnEncontroCORE:=0;

      CASE cCORE.abr_cons_rest
        WHEN TIPO_CONS_REST_TODO THEN
             lnEncontroCORE := 1;

        WHEN TIPO_CONS_REST_ANIVERSARIO THEN
             lnEncontroCORE := INSTR(cCORE.VAL_CONS_REST_1, '-');
             IF(lnEncontroCORE = 0) THEN
               lnRangoInicio := TO_NUMBER(cCORE.VAL_CONS_REST_1);
               lnRangoFin := TO_NUMBER(cCORE.VAL_CONS_REST_1);
             ELSE
               lnRangoInicio := TO_NUMBER(SUBSTR(cCORE.VAL_CONS_REST_1,1,lnEncontroCORE-1));
               lnRangoFin := TO_NUMBER(SUBSTR(cCORE.VAL_CONS_REST_1,lnEncontroCORE+1));
             END IF;

             SELECT COUNT(1)
               INTO lnEncontroCORE
               FROM MAE_CLIEN mc
              WHERE mc.OID_CLIE = lnOidCliente
                AND mc.FEC_INGR IS NOT NULL
                AND (
                     TO_NUMBER(lsAnho ||  TO_CHAR(mc.FEC_INGR,'MMdd')) >= TO_NUMBER(lsFechaIni)
                     AND TO_NUMBER(lsAnho ||  TO_CHAR(mc.FEC_INGR,'MMdd')) <= TO_NUMBER(lsFechaFinal)
                    )
                AND (TO_NUMBER(TO_CHAR(SYSDATE,'yyyy')) - TO_NUMBER(TO_CHAR(mc.FEC_INGR,'yyyy')))
                     BETWEEN lnRangoInicio AND lnRangoFin;

        WHEN TIPO_CONS_REST_CUMPLEANHOS THEN
             /*SELECT COUNT(1)
               INTO lnEncontroCORE
               FROM MAE_CLIEN_DATOS_ADICI mcda
              WHERE mcda.CLIE_OID_CLIE = lnOidCliente
                AND mcda.FEC_NACI IS NOT NULL
                AND (
                     TO_NUMBER(lsAnho ||  TO_CHAR(mcda.FEC_NACI,'MMdd')) >= TO_NUMBER(lsFechaIni)
                      AND TO_NUMBER(lsAnho ||  TO_CHAR(mcda.FEC_NACI,'MMdd')) <= TO_NUMBER(lsFechaFinal)
                     );*/
               IF lsAnhoInicial <> lsAnhoFinal THEN
                  SELECT COUNT(1)
                    INTO lnEncontroCORE
                    FROM MAE_CLIEN_DATOS_ADICI mcda
                   WHERE mcda.CLIE_OID_CLIE = lnOidCliente
                     AND mcda.FEC_NACI IS NOT NULL 
                     AND ((TO_NUMBER(lsAnhoInicial || TO_CHAR(mcda.FEC_NACI, 'MMdd')) >=
                         TO_NUMBER(lsFechaIni) 
                         AND TO_NUMBER(lsAnhoInicial || TO_CHAR(mcda.FEC_NACI, 'MMdd')) <=
                         TO_NUMBER(lsAnhoInicial || '1231')) OR
                         (TO_NUMBER(lsAnhoFinal || '0101') <=
                         TO_NUMBER(lsAnhoFinal || TO_CHAR(mcda.FEC_NACI, 'MMdd')) AND
                         TO_NUMBER(lsAnhoFinal || TO_CHAR(mcda.FEC_NACI, 'MMdd')) <=
                         TO_NUMBER(lsFechaFinal)
                         ));
               ELSE 
                  SELECT COUNT(1)
                    INTO lnEncontroCORE
                    FROM MAE_CLIEN_DATOS_ADICI mcda
                   WHERE mcda.FEC_NACI IS NOT NULL
                     AND mcda.CLIE_OID_CLIE = lnOidCliente 
                     AND (TO_NUMBER(lsAnhoFinal || TO_CHAR(mcda.FEC_NACI, 'MMdd')) >=
                         TO_NUMBER(lsFechaIni))
                     AND (TO_NUMBER(lsAnhoFinal || TO_CHAR(mcda.FEC_NACI, 'MMdd')) <=
                         TO_NUMBER(lsFechaFinal));
               END IF;

        WHEN TIPO_CONS_REST_EDAD THEN
             SELECT COUNT(1)
               INTO lnEncontroCORE
               FROM MAE_CLIEN_DATOS_ADICI mcda
              WHERE mcda.clie_oid_clie = lnOidCliente
                AND ((TO_DATE(lsFechaIni,'yyyyMMdd') - mcda.fec_naci)/ 365.25 )
                    BETWEEN cCORE.VAL_CONS_REST_1 AND cCORE.VAL_CONS_REST_2;

        WHEN TIPO_CONS_REST_PED_SUP_MON THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_2, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_4;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN
               SELECT SUM(a.monto)
                 INTO lnEncontroCORE
                 FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                )  monto
                         FROM ped_solic_cabec,
                              cra_perio pini,
                              cra_perio pfin,
                              cra_perio pcabecera,
                              ped_solic_posic
                        WHERE pini.oid_peri = lnOidPeriodoIni
                          AND pfin.oid_peri =  lnOidPeriodoFin
                          AND pcabecera.oid_peri = ped_solic_cabec.perd_oid_peri
                          --AND pcabecera.oid_peri = ped_solic_cabec_acum2.perd_oid_peri
                          AND pcabecera.fec_inic >= pini.fec_inic
                          AND pcabecera.fec_fina <= pfin.fec_fina
                          AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                          AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                          AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                          AND ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                          --AND ped_solic_cabec_acum2.clie_oid_clie = lnOidCliente
                          AND ped_solic_cabec.clie_oid_clie =  lnOidCliente
                          AND ped_solic_cabec.FEC_FACT IS NOT NULL
                          AND ped_solic_posic.soca_oid_soli_cabe = ped_solic_cabec.oid_soli_cabe
                          AND ped_solic_cabec.IND_OC = 1
                        UNION ALL
                          SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                     ) monto
                                     FROM ped_solic_cabec, ped_solic_posic
                                     WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                                     AND ped_solic_posic.soca_oid_soli_cabe = ped_solic_cabec.oid_soli_cabe
                                     AND lsFlagPeriodoEnRango = 'S'
                         ) a

                       HAVING SUM(a.monto) >= lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_SUP_MON_MA THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnMarca := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN

               SELECT SUM(a.monto)
                 INTO lnEncontroCORE
                 FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                  ) monto
                         FROM ped_solic_cabec,
                              cra_perio pini,
                              cra_perio pfin,
                              cra_perio pcabecera,
                              ped_solic_posic,
                              mae_produ mp
                        WHERE pini.oid_peri = lnOidPeriodoIni
                          AND pfin.oid_peri = lnOidPeriodoFin
                          AND pcabecera.oid_peri = ped_solic_cabec.perd_oid_peri
                          AND pcabecera.fec_inic >= pini.fec_inic
                          AND pcabecera.fec_fina <= pfin.fec_fina
                          AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                          AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                          AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                          AND ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                          AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                          AND mp.oid_prod = ped_solic_posic.prod_oid_prod
                          AND mp.MAPR_OID_MARC_PROD = lnMarca
                          AND ped_solic_posic.soca_oid_soli_cabe = ped_solic_cabec.oid_soli_cabe
                          AND ped_solic_cabec.FEC_FACT IS NOT NULL
                          AND ped_solic_cabec.IND_OC = 1
                        UNION ALL
                          SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                   ) monto
                            FROM ped_solic_cabec, ped_solic_posic, mae_produ
                           WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                             AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                             AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                             AND mae_produ.MAPR_OID_MARC_PROD = lnMarca
                             AND lsFlagPeriodoEnRango = 'S') a

                       HAVING SUM(a.monto) >= lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_SUP_MON_UN THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnUnidadNegocio := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN

               SELECT SUM(a.monto)
                 INTO lnEncontroCORE
                 FROM (SELECT
                       decode(lsTipoVenta, 'VD',
                           nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                   * ped_solic_posic.val_prec_cata_tota_loca
                                ),0),
                           nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                   * ped_solic_posic.val_prec_cata_tota_loca
                                ), 0)
                                   ) monto
                       FROM ped_solic_cabec,
                            ped_solic_posic,
                            mae_produ,
                            cra_perio pini,
                            cra_perio pfin,
                            cra_perio pcabecera
                       WHERE ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                         AND ped_solic_cabec.fec_fact IS NOT NULL
                         AND ped_solic_cabec.IND_OC = 1
                         AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                         AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                         AND mae_produ.uneg_oid_unid_nego =  lnUnidadNegocio
                         AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                         AND ped_solic_cabec.perd_oid_peri = pcabecera.oid_peri
                         AND pcabecera.fec_inic >= pini.fec_inic
                         AND pcabecera.fec_fina <= pfin.fec_fina
                         AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                         AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                         AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                         AND pini.oid_peri = lnOidPeriodoIni
                         AND pfin.oid_peri = lnOidPeriodoFin
                       UNION ALL
                         SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                       ) monto
                           FROM ped_solic_cabec, ped_solic_posic, mae_produ
                          WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                            AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                            AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                           AND mae_produ.uneg_oid_unid_nego = lnUnidadNegocio
                           AND lsFlagPeriodoEnRango = 'S') a

                       HAVING SUM(a.monto) >= lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_SUP_MON_NE THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnNegocio := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN

               SELECT SUM(a.monto)
                 INTO lnEncontroCORE
                 FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                    * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                    ) monto
                        FROM ped_solic_cabec,
                             ped_solic_posic,
                             mae_produ,
                             cra_perio pini,
                             cra_perio pfin,
                             cra_perio pcabecera
                        WHERE ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                          AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                          AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                          AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                          AND mae_produ.nego_oid_nego = lnNegocio
                          AND ped_solic_cabec.perd_oid_peri = pcabecera.oid_peri
                          AND pcabecera.fec_inic >= pini.fec_inic
                          AND pcabecera.fec_fina <= pfin.fec_fina
                          AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                          AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                          AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                          AND pini.oid_peri = lnOidPeriodoIni
                          AND pfin.oid_peri = lnOidPeriodoFin
                          AND ped_solic_cabec.fec_fact IS NOT NULL
                          AND ped_solic_cabec.IND_OC = 1
                        UNION ALL
                         SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                       ) monto
                           FROM ped_solic_cabec, ped_solic_posic, mae_produ
                          WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                            AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                            AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                            AND mae_produ.nego_oid_nego = lnNegocio
                            AND lsFlagPeriodoEnRango = 'S'
                         ) a
                       HAVING SUM(a.monto) >= lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_SUP_MON_CA THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnCatalogo := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN
              SELECT SUM(a.monto)
                INTO lnEncontroCORE
                FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                   ) monto
                       FROM ped_solic_cabec,
                            ped_solic_posic,
                            cra_perio pini,
                            cra_perio pfin,
                            cra_perio pcabecera,
                             pre_ofert_detal
                       WHERE ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                         AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                         AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                         AND ped_solic_cabec.fec_fact IS NOT NULL
                         AND ped_solic_cabec.IND_OC = 1
                         AND ped_solic_cabec.perd_oid_peri = pcabecera.oid_peri
                         AND pcabecera.fec_inic >= pini.fec_inic
                         AND pcabecera.fec_fina <= pfin.fec_fina
                         AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                         AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                         AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                         AND pini.oid_peri = lnOidPeriodoIni
                         AND pfin.oid_peri = lnOidPeriodoFin
                         AND ped_solic_posic.ofde_oid_deta_ofer = pre_ofert_detal.oid_deta_ofer
                         AND pre_ofert_detal.ocat_oid_catal = lnCatalogo
                       UNION ALL
                        SELECT NVL(SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                     ),0) monto
                          FROM ped_solic_cabec, ped_solic_posic, pre_ofert_detal
                         WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                           AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                           AND ped_solic_posic.ofde_oid_deta_ofer = pre_ofert_detal.oid_deta_ofer
                           AND pre_ofert_detal.ocat_oid_catal = lnCatalogo
                           AND lsFlagPeriodoEnRango = 'S'
                         ) a
                       HAVING SUM(a.monto) >= lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_NSUP_MON THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_2, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_4;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN
               SELECT SUM(NVL(a.monto,0))  --CAMBIO NVL
                 INTO lnEncontroCORE
                 FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                )  monto

                        FROM ped_solic_cabec,
                             cra_perio pini,
                             cra_perio pfin,
                             cra_perio pcabecera,
                             ped_solic_posic
                        WHERE pini.oid_peri = lnOidPeriodoIni
                          AND pfin.oid_peri =  lnOidPeriodoFin
                          --AND pcabecera.oid_peri = ped_solic_cabec_acum2.perd_oid_peri
                          AND pcabecera.oid_peri = ped_solic_cabec.perd_oid_peri
                          AND pcabecera.fec_inic >= pini.fec_inic
                          AND pcabecera.fec_fina <= pfin.fec_fina
                          AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                          AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                          AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                          AND ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                          --ND ped_solic_cabec_acum2.clie_oid_clie = lnOidCliente
                          AND ped_solic_cabec.clie_oid_clie =  lnOidCliente
                          AND ped_solic_cabec.FEC_FACT IS NOT NULL
                          AND ped_solic_posic.soca_oid_soli_cabe = ped_solic_cabec.oid_soli_cabe
                          AND ped_solic_cabec.IND_OC = 1
                        UNION ALL
                         SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                     ) monto
                           FROM ped_solic_cabec, ped_solic_posic
                          WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                            AND ped_solic_posic.soca_oid_soli_cabe = ped_solic_cabec.oid_soli_cabe
                            AND lsFlagPeriodoEnRango = 'S'
                         ) a

                       HAVING SUM(NVL(a.monto,0)) < lnMonto;  --CAMBIO NVL

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_NSUP_MON_MA THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnMarca := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN

               SELECT SUM(NVL(a.monto, 0))
                 INTO lnEncontroCORE
                 FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                  * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                  ) monto
                        FROM ped_solic_cabec,
                             cra_perio pini,
                             cra_perio pfin,
                             cra_perio pcabecera,
                             ped_solic_posic,
                             mae_produ mp
                        WHERE pini.oid_peri = lnOidPeriodoIni
                          AND pfin.oid_peri = lnOidPeriodoFin
                          AND pcabecera.oid_peri = ped_solic_cabec.perd_oid_peri
                          AND pcabecera.fec_inic >= pini.fec_inic
                          AND pcabecera.fec_fina <= pfin.fec_fina
                          AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                          AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                          AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                          AND ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                          AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                          AND mp.oid_prod = ped_solic_posic.prod_oid_prod
                          AND mp.MAPR_OID_MARC_PROD = lnMarca
                          AND ped_solic_posic.soca_oid_soli_cabe = ped_solic_cabec.oid_soli_cabe
                          AND ped_solic_cabec.FEC_FACT IS NOT NULL
                          AND ped_solic_cabec.IND_OC = 1
                        UNION ALL
                        SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                     ) monto
                          FROM ped_solic_cabec, ped_solic_posic, mae_produ
                         WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                           AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                           AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                           AND mae_produ.MAPR_OID_MARC_PROD = lnMarca
                           AND lsFlagPeriodoEnRango = 'S'
                         ) a
                       HAVING SUM(NVL(a.monto, 0)) < lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_NSUP_MON_UN THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnUnidadNegocio := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN

               SELECT SUM(NVL(a.monto, 0))
                 INTO lnEncontroCORE
                 FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                   * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                   ) monto
                         FROM ped_solic_cabec,
                              ped_solic_posic,
                              mae_produ,
                              cra_perio pini,
                              cra_perio pfin,
                              cra_perio pcabecera
                         WHERE ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                           AND ped_solic_cabec.fec_fact IS NOT NULL
                           AND ped_solic_cabec.IND_OC = 1
                           AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                           AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                           AND mae_produ.uneg_oid_unid_nego =  lnUnidadNegocio
                           AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                           AND ped_solic_cabec.perd_oid_peri = pcabecera.oid_peri
                           AND pcabecera.fec_inic >= pini.fec_inic
                           AND pcabecera.fec_fina <= pfin.fec_fina
                           AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                           AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                           AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                           AND pini.oid_peri = lnOidPeriodoIni
                           AND pfin.oid_peri = lnOidPeriodoFin
                         UNION ALL
                           SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                         ) monto
                             FROM ped_solic_cabec, ped_solic_posic, mae_produ
                            WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                              AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                              AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                              AND mae_produ.uneg_oid_unid_nego = lnUnidadNegocio
                              AND lsFlagPeriodoEnRango = 'S'
                         ) a
                       HAVING SUM(NVL(a.monto, 0)) < lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_NSUP_MON_NE THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnNegocio := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN

               SELECT SUM(NVL(a.monto, 0))
                 INTO lnEncontroCORE
                 FROM (SELECT decode(lsTipoVenta, 'VD',
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                    * ped_solic_posic.val_prec_cata_tota_loca
                                    ),0),
                               nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                       * ped_solic_posic.val_prec_cata_tota_loca
                                    ), 0)
                                    ) monto
                        FROM ped_solic_cabec,
                             ped_solic_posic,
                             mae_produ,
                             cra_perio pini,
                             cra_perio pfin,
                             cra_perio pcabecera
                        WHERE ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                          AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                          AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                          AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                          AND mae_produ.nego_oid_nego = lnNegocio
                          AND ped_solic_cabec.perd_oid_peri = pcabecera.oid_peri
                          AND pcabecera.fec_inic >= pini.fec_inic
                          AND pcabecera.fec_fina <= pfin.fec_fina
                          AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                          AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                          AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                          AND pini.oid_peri = lnOidPeriodoIni
                          AND pfin.oid_peri = lnOidPeriodoFin
                          AND ped_solic_cabec.fec_fact IS NOT NULL
                          AND ped_solic_cabec.IND_OC = 1
                        UNION ALL
                         SELECT SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                       ) monto
                           FROM ped_solic_cabec, ped_solic_posic, mae_produ
                          WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                            AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                            AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                            AND mae_produ.nego_oid_nego = lnNegocio
                            AND lsFlagPeriodoEnRango = 'S'
                          ) a
                       HAVING SUM(NVL(a.monto, 0)) < lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_PED_NSUP_MON_CA THEN
             lnMonto := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnCatalogo := TO_NUMBER(cCORE.VAL_CONS_REST_2);
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_3, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_4, lnOidMarca, lnOidCanal);
             lsTipoVenta := cCORE.VAL_CONS_REST_5;
            lsFlagPeriodoEnRango := 'N';
            IF lnOidPeriodo >= lnOidPeriodoIni AND lnOidPeriodo <= lnOidPeriodoFin THEN
                lsFlagPeriodoEnRango := 'S';
            END IF;

             BEGIN
              SELECT SUM(NVL(a.monto, 0))
                INTO lnEncontroCORE
                FROM (SELECT decode(lsTipoVenta, 'VD',
                             nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_tota_loca
                                  ),0),
                             nvl( SUM (  PED_SOLIC_POSIC.NUM_UNID_ATEN
                                     * ped_solic_posic.val_prec_cata_tota_loca
                                  ), 0)
                                     ) monto
                       FROM ped_solic_cabec,
                            ped_solic_posic,
                            cra_perio pini,
                            cra_perio pfin,
                            cra_perio pcabecera,
                            pre_ofert_detal
                       WHERE ped_solic_cabec.oid_soli_cabe != pnOidSolicitud
                         AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                         AND ped_solic_cabec.clie_oid_clie = lnOidCliente
                         AND ped_solic_cabec.fec_fact IS NOT NULL
                         AND ped_solic_cabec.IND_OC = 1
                         AND ped_solic_cabec.perd_oid_peri = pcabecera.oid_peri
                         AND pcabecera.fec_inic >= pini.fec_inic
                         AND pcabecera.fec_fina <= pfin.fec_fina
                         AND pcabecera.cana_oid_cana = pini.cana_oid_cana
                         AND pcabecera.marc_oid_marc = pini.marc_oid_marc
                         AND pcabecera.pais_oid_pais = pini.pais_oid_pais
                         AND pini.oid_peri = lnOidPeriodoIni
                         AND pfin.oid_peri = lnOidPeriodoFin
                         AND ped_solic_posic.ofde_oid_deta_ofer = pre_ofert_detal.oid_deta_ofer
                         AND pre_ofert_detal.ocat_oid_catal = lnCatalogo
                       UNION ALL
                         SELECT NVL(SUM (  ped_solic_posic.NUM_UNID_DEMA_REAL
                                     * ped_solic_posic.val_prec_cata_unit_loca
                                     ),0) monto
                           FROM ped_solic_cabec, ped_solic_posic, pre_ofert_detal
                          WHERE ped_solic_cabec.oid_soli_cabe = pnOidSolicitud
                            AND ped_solic_cabec.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                            AND ped_solic_posic.ofde_oid_deta_ofer = pre_ofert_detal.oid_deta_ofer
                            AND pre_ofert_detal.ocat_oid_catal = lnCatalogo
                            AND lsFlagPeriodoEnRango = 'S'
                         ) a
                       HAVING SUM(NVL(a.monto, 0)) < lnMonto;

                lnEncontroCORE := 1;

              EXCEPTION
                WHEN OTHERS THEN
                  lnEncontroCORE := 0;
              END;

        WHEN TIPO_CONS_REST_CON_INS_NUEV_DU THEN
             lnOidPeriodoIni := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_1, lnOidMarca, lnOidCanal);
             lnOidPeriodoFin := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(cCORE.VAL_CONS_REST_2, lnOidMarca, lnOidCanal);

             SELECT COUNT(1)
               INTO lnEncontroCORE
               FROM MAE_CLIEN_VINCU vinc,
                    MAE_TIPO_VINCU tipo,
                    CRA_PERIO pini,
                    CRA_PERIO pfin
              WHERE vinc.TIVC_OID_TIPO_VINC = tipo.OID_TIPO_VINC
                AND tipo.COD_TIPO_VINC = '01'
                AND pini.OID_PERI = lnOidPeriodoIni
                AND pfin.OID_PERI = lnOidPeriodoFin
                AND vinc.FEC_DESD >= pini.FEC_INIC
                AND vinc.FEC_DESD <= pfin.FEC_FINA
                AND vinc.CLIE_OID_CLIE_VNTE = lnOidCliente
                AND vinc.FEC_HAST IS NULL;

        WHEN TIPO_CONS_REST_TIPO_CLASIF THEN

             SELECT COUNT(1)
               INTO lnEncontroCORE
               FROM mae_clien_tipo_subti mcts,
                    mae_clien_clasi      mccl,
                    mae_tipo_clien       mt,
                    mae_subti_clien      mst,
                    mae_tipo_clasi_clien mtc,
                    mae_clasi            mc,
                    MAV_PARAM_CORES_DETAL CRDET
              WHERE mcts.clie_oid_clie = lnOidCliente
                AND mcts.oid_clie_tipo_subt = mccl.ctsu_oid_clie_tipo_subt(+)
                AND mcts.ticl_oid_tipo_clie = mt.oid_tipo_clie(+)
                AND mcts.sbti_oid_subt_clie = mst.oid_subt_clie(+)
                AND mccl.tccl_oid_tipo_clasi = mtc.oid_tipo_clas(+)
                AND mccl.clas_oid_clas = mc.oid_clas(+)
                AND mcts.sbti_oid_subt_clie = mtc.sbti_oid_subt_clie --nuevo
                AND CRDET.PAIS_COD_PAIS = cCORE.Pais_Cod_Pais
                AND CRDET.PACO_COR_PARA_CONF = cCORE.PACO_COR_PARA_CONF
                AND CRDET.CORE_COR_CONS_REST = cCORE.CORE_COR_CONS_REST
                AND CRDET.EST_REGI = '1'
                AND CRDET.IND_ACTI = 1
                AND mt.cod_tipo_clie = CRDET.VAL_CONS_REST_1
                AND (CRDET.VAL_CONS_REST_2 IS NULL OR mst.cod_subt_clie = CRDET.VAL_CONS_REST_2)
                AND (CRDET.VAL_CONS_REST_3 IS NULL OR mtc.cod_tipo_clas = CRDET.VAL_CONS_REST_3)
                AND (CRDET.VAL_CONS_REST_4 IS NULL OR mc.cod_clas = CRDET.VAL_CONS_REST_4);

           IF((lnEncontroCORE > 0) AND (cMAV.IND_UNID_DIF = 'S')) THEN
             SELECT NUM_UNID_PROD
               INTO lnUnidades
               FROM (
                     SELECT CRDET.NUM_UNID_PROD
                       FROM mae_clien_tipo_subti mcts,
                            mae_clien_clasi      mccl,
                            mae_tipo_clien       mt,
                            mae_subti_clien      mst,
                            mae_tipo_clasi_clien mtc,
                            mae_clasi            mc,
                            MAV_PARAM_CORES_DETAL CRDET
                      WHERE mcts.clie_oid_clie = lnOidCliente
                        AND mcts.oid_clie_tipo_subt = mccl.ctsu_oid_clie_tipo_subt(+)
                        AND mcts.ticl_oid_tipo_clie = mt.oid_tipo_clie(+)
                        AND mcts.sbti_oid_subt_clie = mst.oid_subt_clie(+)
                        AND mccl.tccl_oid_tipo_clasi = mtc.oid_tipo_clas(+)
                        AND mccl.clas_oid_clas = mc.oid_clas(+)
                        AND mcts.sbti_oid_subt_clie = mtc.sbti_oid_subt_clie --nuevo
                        AND CRDET.PAIS_COD_PAIS = cCORE.Pais_Cod_Pais
                        AND CRDET.PACO_COR_PARA_CONF = cCORE.PACO_COR_PARA_CONF
                        AND CRDET.CORE_COR_CONS_REST = cCORE.CORE_COR_CONS_REST
                        AND CRDET.EST_REGI = '1'
                        AND CRDET.IND_ACTI = 1
                        AND mt.cod_tipo_clie = CRDET.VAL_CONS_REST_1
                        AND (CRDET.VAL_CONS_REST_2 IS NULL OR mst.cod_subt_clie = CRDET.VAL_CONS_REST_2)
                        AND (CRDET.VAL_CONS_REST_3 IS NULL OR mtc.cod_tipo_clas = CRDET.VAL_CONS_REST_3)
                        AND (CRDET.VAL_CONS_REST_4 IS NULL OR mc.cod_clas = CRDET.VAL_CONS_REST_4)
                      ORDER BY CRDET.VAL_CONS_REST_1, CRDET.VAL_CONS_REST_2,
                               CRDET.VAL_CONS_REST_3, CRDET.VAL_CONS_REST_4)
              WHERE ROWNUM = 1;
           END IF;

          WHEN TIPO_CONS_REST_UNIDAD_ADMIN THEN

               SELECT COUNT(1)
                 INTO lnEncontroCORE
                 FROM mae_clien_unida_admin uniadm,
                      zon_terri_admin       zta,
                      zon_terri             zt,
                      zon_secci             zsec,
                      zon_zona              zzon,
                      zon_regio             zreg,
                      MAV_PARAM_CORES_DETAL CRDET
                WHERE uniadm.clie_oid_clie = lnOidCliente
                  AND uniadm.ind_acti = 1
                  AND uniadm.ztad_oid_terr_admi = zta.oid_terr_admi
                  AND zta.terr_oid_terr = zt.oid_terr(+)
                  AND zta.zscc_oid_secc = zsec.oid_secc(+)
                  AND zsec.zzon_oid_zona = zzon.oid_zona(+)
                  AND zzon.zorg_oid_regi = zreg.oid_regi(+)
                  AND CRDET.PAIS_COD_PAIS = cCORE.Pais_Cod_Pais
                  AND CRDET.PACO_COR_PARA_CONF = cCORE.PACO_COR_PARA_CONF
                  AND CRDET.CORE_COR_CONS_REST = cCORE.CORE_COR_CONS_REST
                  AND CRDET.EST_REGI = '1'
                  AND CRDET.IND_ACTI = 1
                  AND (CRDET.VAL_CONS_REST_4 IS NULL OR zt.cod_terr = CRDET.VAL_CONS_REST_4)
                  AND (CRDET.VAL_CONS_REST_3 IS NULL OR zsec.cod_secc = CRDET.VAL_CONS_REST_3)
                  AND (CRDET.VAL_CONS_REST_2 IS NULL OR zzon.cod_zona = CRDET.VAL_CONS_REST_2)
                  AND (CRDET.VAL_CONS_REST_1 IS NULL OR zreg.cod_regi = CRDET.VAL_CONS_REST_1);

           IF((lnEncontroCORE > 0) AND (cMAV.IND_UNID_DIF = 'S')) THEN
             SELECT NUM_UNID_PROD
               INTO lnUnidades
               FROM (
                     SELECT CRDET.NUM_UNID_PROD
                       FROM mae_clien_unida_admin uniadm,
                            zon_terri_admin       zta,
                            zon_terri             zt,
                            zon_secci             zsec,
                            zon_zona              zzon,
                            zon_regio             zreg,
                            MAV_PARAM_CORES_DETAL CRDET
                      WHERE uniadm.clie_oid_clie = lnOidCliente
                        AND uniadm.ind_acti = 1
                        AND uniadm.ztad_oid_terr_admi = zta.oid_terr_admi
                        AND zta.terr_oid_terr = zt.oid_terr(+)
                        AND zta.zscc_oid_secc = zsec.oid_secc(+)
                        AND zsec.zzon_oid_zona = zzon.oid_zona(+)
                        AND zzon.zorg_oid_regi = zreg.oid_regi(+)
                        AND CRDET.PAIS_COD_PAIS = cCORE.Pais_Cod_Pais
                        AND CRDET.PACO_COR_PARA_CONF = cCORE.PACO_COR_PARA_CONF
                        AND CRDET.CORE_COR_CONS_REST = cCORE.CORE_COR_CONS_REST
                        AND CRDET.EST_REGI = '1'
                        AND CRDET.IND_ACTI = 1
                        AND (CRDET.VAL_CONS_REST_4 IS NULL OR zt.cod_terr = CRDET.VAL_CONS_REST_4)
                        AND (CRDET.VAL_CONS_REST_3 IS NULL OR zsec.cod_secc = CRDET.VAL_CONS_REST_3)
                        AND (CRDET.VAL_CONS_REST_2 IS NULL OR zzon.cod_zona = CRDET.VAL_CONS_REST_2)
                        AND (CRDET.VAL_CONS_REST_1 IS NULL OR zreg.cod_regi = CRDET.VAL_CONS_REST_1)
                      ORDER BY CRDET.VAL_CONS_REST_1, CRDET.VAL_CONS_REST_2,
                               CRDET.VAL_CONS_REST_3, CRDET.VAL_CONS_REST_4)
              WHERE ROWNUM = 1;
           END IF;

          WHEN TIPO_CONS_REST_ESTATUS THEN

               --query para determinar si una region cerro
               SELECT count(1)
                INTO lnRegionCerrada
                FROM FAC_PROGR_CIERR G
              WHERE G.CAM_PROC = (SELECT COD_PERI
                                     FROM BAS_CTRL_FACT
                                    WHERE STA_CAMP = 0
                                      AND IND_CAMP_ACT = 1)--campaña actual
                 AND G.TIP_CIER = 'R'
                 AND G.EST_CIER = 'P'
                 AND G.EST_REGI = '1'
                 AND G.COD_REGI = ( SELECT ZR.COD_REGI
                                      FROM PED_SOLIC_CABEC PSC, ZON_REGIO ZR, ZON_ZONA ZZ
                                     WHERE PSC.OID_SOLI_CABE = pnOidSolicitud
                                       AND PSC.ZZON_OID_ZONA = ZZ.OID_ZONA
                                       AND ZZ.ZORG_OID_REGI = ZR.OID_REGI);

              --evaluamos lnRegionCerrada

              IF lnRegionCerrada = 0 THEN --region ya cerro

               SELECT COUNT(1)
                 INTO lnEncontroCORE
                 FROM MAE_CLIEN_DATOS_ADICI adi,
                      MAV_PARAM_CORES_DETAL CRDET
                WHERE adi.CLIE_OID_CLIE = lnOidCliente
                  AND CRDET.PAIS_COD_PAIS = cCORE.Pais_Cod_Pais
                  AND CRDET.PACO_COR_PARA_CONF = cCORE.PACO_COR_PARA_CONF
                  AND CRDET.CORE_COR_CONS_REST = cCORE.CORE_COR_CONS_REST
                  AND CRDET.EST_REGI = '1'
                  AND CRDET.IND_ACTI = 1
                  AND adi.ESTA_OID_ESTA_CLIE = TO_NUMBER(CRDET.VAL_CONS_REST_1);

               ELSE  --region no cerro

                  -- ADD 1 INICIO
                    SELECT ADI.ESTA_OID_ESTA_CLIE
                      INTO lnOidEstadoActu
                        FROM MAE_CLIEN_DATOS_ADICI ADI
                        WHERE ADI.CLIE_OID_CLIE = lnOidCliente;
                  -- ADD 1 FIN

                  SELECT CTRL.COD_PERI
                    INTO lnPeriodoActual
                    FROM BAS_CTRL_FACT CTRL
                   WHERE CTRL.COD_PAIS = cCORE.Pais_Cod_Pais
                     AND CTRL.sta_camp = '0'
                     AND CTRL.ind_camp_act = '1';

                   lnPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lnPeriodoActual,lnOidPais, lnOidMarca, lnOidCanal,-1);
                   lnOidPeriodoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lnPeriodoAnt, lnOidMarca, lnOidCanal);

                   -- ADD 2 INICIO
                   IF (lnOidEstadoActu = 1 OR lnOidEstadoActu =2) THEN
                     lnOidEstado:= 1;
                   ELSE

                   -- ADD 2 FIN

                   SELECT MCHE.ESTA_OID_ESTA_CLIE
                     INTO lnOidEstado
                     FROM MAE_CLIEN_HISTO_ESTAT MCHE
                    WHERE MCHE.PERD_OID_PERI <= lnOidPeriodoAnt
                      AND MCHE.CLIE_OID_CLIE = lnOidCliente
                      AND (MCHE.PERD_OID_PERI_PERI_FIN IS NULL OR
                          MCHE.PERD_OID_PERI_PERI_FIN >= lnOidPeriodoAnt);

                    -- ADD 3 INICIO
                   END IF;
                   -- ADD 3 FIN

                    IF lnOidEstado IS NOT NULL THEN
                      /*SELECT COUNT(1)
                      INTO lnExisteOid
                        FROM MAV_PARAM_CORES_DETAL MPCD,
                             MAE_CLIEN_DATOS_ADICI ADI
                     WHERE MPCD.PAIS_COD_PAIS = cCORE.Pais_Cod_Pais
                       AND MPCD.VAL_CONS_REST_1 = lnOidEstado
                       AND MPCD.PACO_COR_PARA_CONF = cCORE.PACO_COR_PARA_CONF
                       AND MPCD.CORE_COR_CONS_REST = cCORE.CORE_COR_CONS_REST
                       AND MPCD.EST_REGI = '1'
                         AND MPCD.IND_ACTI = 1
                         AND ADI.ESTA_OID_ESTA_CLIE = TO_NUMBER(MPCD.VAL_CONS_REST_1)
                         AND ADI.ESTA_OID_ESTA_CLIE IN ('1','2');*/

                         -- ADD 4 INICIO
                    SELECT COUNT(1)
                      INTO lnExisteOid
                      FROM MAV_PARAM_CORES_DETAL MPCD
                     WHERE MPCD.PAIS_COD_PAIS = cCORE.Pais_Cod_Pais--'PE'--
                       AND MPCD.PACO_COR_PARA_CONF = cCORE.PACO_COR_PARA_CONF--255--
                       AND MPCD.CORE_COR_CONS_REST = cCORE.CORE_COR_CONS_REST--61--
                       AND TO_NUMBER(MPCD.VAL_CONS_REST_1) = lnOidEstado--1--
                       AND MPCD.EST_REGI = '1'
                         AND MPCD.IND_ACTI = 1;
                     -- ADD 4 FIN

                    IF lnExisteOid = 0 then
                       lnEncontroCORE:=0;
                    else
                       lnEncontroCORE:=1;
                    end if;
                    END IF;

               END IF;

          WHEN TIPO_CONS_REST_MON_FLX THEN
             lnMontoDesde := TO_NUMBER(cCORE.VAL_CONS_REST_1, '9999999999.99');
             lnMontoHasta := TO_NUMBER(cCORE.VAL_CONS_REST_2, '9999999999.99');
             lsCodPeriodoFlex := cCORE.VAL_CONS_REST_3;

             --RECUPERAMOS EL PERIODO ANTERIOR
             lsCodPeriodoAnt := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lsCodPeriodo,
                                                     lnOidPais, lnOidMarca, lnOidCanal,-1);

             IF(lsCodPeriodoFlex = lsCodPeriodoAnt) THEN
               SELECT COUNT(1)
                 INTO lnEncontroCORE
                 FROM INT_SOLIC_CONSO_CABEC SOL
                WHERE SOL.VAL_IMPO_DESC_3_TOTA_LOCA >= lnMontoDesde
                  AND SOL.VAL_IMPO_DESC_3_TOTA_LOCA <= lnMontoHasta
                  AND SOL.SOCA_OID_SOLI_CABE_REFE = pnOidSolicitud ;

             ELSE
               SELECT COUNT(1)
                 INTO lnEncontroCORE
                 FROM FLX_GENER_FINAN_CONSU_FLEXI F
                WHERE F.VAL_MONT_FLEX_FINA > 0
                  AND F.VAL_MONT_FLEX_FINA >= lnMontoDesde
                  AND F.VAL_MONT_FLEX_FINA <= lnMontoHasta
                  AND F.COD_PERI = lsCodPeriodoFlex
                  AND F.COD_CLIE = lsCodCliente;

             END IF;
             
         WHEN TIPO_CONS_REST_DEMAN_ANTIC THEN
            SELECT COUNT(1)
              INTO lnEncontroCORE
              FROM INT_SOLIC_CONSO_CABEC CONS
             WHERE CONS.IND_OCS_PROC = 1
               AND CONS.IND_VALI_DEAN = 1 --indicador de DA
               AND CONS.SOCA_OID_SOLI_CABE_REFE = pnOidSolicitud; --solicitud de la consultora

         ELSE
           lnEncontroCORE := 0;
      END CASE;

      -- SI encontro RESTRICCION SALE, SI ES CONSIDERACION CONTINUA
      IF cCORE.IND_CONS_REST = 'R' AND lnEncontroCORE > 0 THEN
        lbCumple:= FALSE;
        EXIT;
      ELSIF cCORE.IND_CONS_REST = 'R' AND lnEncontroCORE = 0 THEN
        lbCumple:= TRUE;
      ELSIF cCORE.IND_CONS_REST = 'C' AND lnEncontroCORE = 0 THEN
        lbCumple:= FALSE;
        EXIT;
      ELSIF cCORE.IND_CONS_REST = 'C' AND lnEncontroCORE > 0 THEN
        lbCumple:= TRUE;
      END IF;

    END LOOP;

    IF (lbCumple = TRUE) THEN
      --Obtenemos el Oid Tipo Oferta
      SELECT TA.TOFE_OID_TIPO_OFER
        INTO lnTipoOferta
        FROM MAV_ACTIV_TIPO_OFERT TA
       WHERE TA.OID_TIPO_OFER_ACTI = cMAV.ATOF_OID_TIPO_OFER_ACTI;

      --Recuperamos el Oid Detalle Oferta asociado al MAV, para ello invocamos
      --a la funcion de Validar Productos
      lnOidDetOfer := MAV_PKG_PROCE.MAV_FN_VALID_PRODU(lnOidPais,
                                                        NULL,
                                                        NULL,
                                                        lnOidPeriodo,
                                                        lnTipoOferta,
                                                        4,
                                                        cMAV.FOR_PAGO,
                                                        cMAV.FOR_COBR,
                                                        cMAV.COD_SAP,
                                                        cMAV.VAL_PREC);

      IF(lnOidDetOfer IS NOT NULL) THEN
        --Obtenemos el oid Matriz Facturacion
        SELECT MF.OID_MATR_FACT
          INTO lnOidMatrizFact
          FROM PRE_OFERT_DETAL       D,
               PRE_CATAL             C,
               PRE_OFERT             O,
               PRE_MATRI_FACTU_CABEC M,
               PRE_MATRI_FACTU       MF
         WHERE D.OCAT_OID_CATAL = C.OID_CATA(+)
           AND M.PERD_OID_PERI = lnOidPeriodo
           AND M.OID_CABE = O.MFCA_OID_CABE
           AND O.OID_OFER = D.OFER_OID_OFER
           AND M.OID_CABE = O.MFCA_OID_CABE
           AND MF.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
           AND MF.MFCA_OID_CABE = M.OID_CABE
           AND D.OID_DETA_OFER = lnOidDetOfer;

        INSERT INTO MAV_ENVIO_CONFI
           (OID_ENVI,
            FEC_ENTR,
            NUM_UNID, --DEPENDE DE LA CORE
            VAL_PREC,
            PAIS_COD_PAIS,
            COR_PARA_CONF,
            MAFA_OID_MATR_FACT,
            CLIE_OID_CLIE,
            ESEN_OID_ESTA_ENVI, --1
            FCOB_OID_FORM_COBR,
            ATDE_OID_ACTI_TIPO_DESP,
            FOPA_OID_FORM_PAGO,
            IND_ENVI,
            ACTI_OID_ACTI,
            NUM_MENS,
            NUM_SECU,
            IND_ACTI,
            USU_CREA,
            FEC_CREA,
            EST_REGI)
        VALUES
           (MAV_MENC_SEQ.NEXTVAL,
            NULL,
            DECODE(cMAV.IND_UNID_DIF, 'S', lnUnidades, cMAV.NUM_UNID_PROD),
            cMAV.VAL_PREC,
            cMAV.PAIS_COD_PAIS,
            cMAV.COR_PARA_CONF,
            lnOidMatrizFact,
            lnOidCliente,
            1,
            cMAV.FOR_COBR,
            cMAV.ACT_TIPO_DESP,
            cMAV.FOR_PAGO,
            'P',
            cMAV.ATOF_ACTI_OID_ACTI,
            NULL,
            NULL,
            1,
            lsCodigoUsuario,
            SYSDATE,
            1);
      END IF;

    END IF;

  END LOOP;

  --Obtenemos la ultima posicion de la cabecera
  SELECT NVL(MAX(COD_POSI), 0)
    INTO lnCodigoPosicion
    FROM PED_SOLIC_POSIC
   WHERE SOCA_OID_SOLI_CABE = pnOidSolicitud;

  --INSERTAMOS LA INFORMACIN DE LOS MAV PENDIENTE EN EL DETALL DE PEDIDO DE LA CONSULTORA
  INSERT INTO PED_SOLIC_POSIC
    (OID_SOLI_POSI,
     SOCA_OID_SOLI_CABE,
     COD_POSI,
     NUM_UNID_DEMA,
     NUM_UNID_POR_ATEN,
     TPOS_OID_TIPO_POSI,
     PROD_OID_PROD,
     FOPA_OID_FORM_PAGO,
     VAL_CODI_VENT,
     ESPO_OID_ESTA_POSI,
     STPO_OID_SUBT_POSI,
     VAL_CODI_VENT_FICT,
     NUM_UNID_DEMA_REAL,
     VAL_PREC_CATA_UNIT_LOCA,
     VAL_PREC_CONT_UNIT_LOCA,
     VAL_PREC_CATA_UNIT_DOCU,
     VAL_PREC_CONTA_UNIT_DOCU,
     VAL_PORC_DESC,
     VAL_IMPO_DESC_UNIT_DOCU,
     OFDE_OID_DETA_OFER,
     SOPO_OID_SOLI_POSI,
     NUM_UNID_COMPR,
     VAL_IMPO_DESC_UNIT_LOCA,
     NUM_PAGI_CATA,
     VAL_CATA,
     ALMC_OID_ALMC)
    SELECT
         PED_SOPO_SEQ.NEXTVAL,
         pnOidSolicitud,
         lnCodigoPosicion + ROWNUM,
         0,
         E.NUM_UNID,
         3,
         D.PROD_OID_PROD,
         E.FOPA_OID_FORM_PAGO,
         D.VAL_CODI_VENT,
         4,
         6,
         NULL,
         E.NUM_UNID,
         DECODE(E.FCOB_OID_FORM_COBR, 1, 0, E.VAL_PREC),
         DECODE(E.FCOB_OID_FORM_COBR, 1, E.VAL_PREC, 0),
         DECODE(E.FCOB_OID_FORM_COBR, 1, 0, E.VAL_PREC),
         DECODE(E.FCOB_OID_FORM_COBR, 1, E.VAL_PREC, 0),
         NULL,
         NULL,
         D.OID_DETA_OFER,
         NULL,
         E.NUM_UNID,
         NULL,
         NULL,
         NULL,
         A.ALMA_OID_ALMA
    FROM MAV_ENVIO_CONFI E, PRE_MATRI_FACTU F, PRE_OFERT_DETAL D, MAV_ACTIV A,
         PRE_OFERT             O,
         PRE_MATRI_FACTU_CABEC M
   WHERE E.IND_ENVI = 'P'
     AND E.CLIE_OID_CLIE = lnOidCliente
     AND E.MAFA_OID_MATR_FACT = F.OID_MATR_FACT
     AND F.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
     AND E.ACTI_OID_ACTI = A.OID_ACTI
     AND M.PERD_OID_PERI = lnOidPeriodo
     AND M.OID_CABE = O.MFCA_OID_CABE
     AND O.OID_OFER = D.OFER_OID_OFER
     AND A.TICL_OID_TIPO_CLIE = 2;

  --SE INSERTA REGISTROS EN LA TABLA DE SOLICIDUD DE ENVIO DE MAV
  INSERT INTO MAV_SOLIC_ENVIO_CONFI
    (OID_SOLI_ENVI,
     SOCA_OID_SOLI_CABE,
     IND_GENE,
     MENV_OID_ENVI)
  SELECT MAV_SOEC_SEQ.NEXTVAL,
         pnOidSolicitud,
         1,
         E.OID_ENVI
    FROM MAV_ENVIO_CONFI E, PRE_MATRI_FACTU F, PRE_OFERT_DETAL D, MAV_ACTIV A,
         PRE_OFERT             O,
         PRE_MATRI_FACTU_CABEC M
   WHERE E.IND_ENVI = 'P'
     AND E.CLIE_OID_CLIE = lnOidCliente
     AND E.MAFA_OID_MATR_FACT = F.OID_MATR_FACT
     AND F.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
     AND E.ACTI_OID_ACTI = A.OID_ACTI
     AND M.PERD_OID_PERI = lnOidPeriodo
     AND M.OID_CABE = O.MFCA_OID_CABE
     AND O.OID_OFER = D.OFER_OID_OFER
     AND A.TICL_OID_TIPO_CLIE = 2;

  --SE ACTUALIZA EL ESTADO DE LA ENTIDAD [ENVIO MAV] a Enviado (E)
  UPDATE MAV_ENVIO_CONFI
     SET IND_ENVI = 'E',
         USU_MODI = lsCodigoUsuario,
         FEC_MODI = SYSDATE
   WHERE OID_ENVI IN (SELECT E.OID_ENVI
                        FROM MAV_ENVIO_CONFI E, PRE_MATRI_FACTU F, PRE_OFERT_DETAL D, MAV_ACTIV A,
                             PRE_OFERT             O,
                             PRE_MATRI_FACTU_CABEC M
                       WHERE E.IND_ENVI = 'P'
                         AND E.CLIE_OID_CLIE = lnOidCliente
                         AND E.MAFA_OID_MATR_FACT = F.OID_MATR_FACT
                         AND F.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
                         AND E.ACTI_OID_ACTI = A.OID_ACTI
                         AND M.PERD_OID_PERI = lnOidPeriodo
                         AND M.OID_CABE = O.MFCA_OID_CABE
                         AND O.OID_OFER = D.OFER_OID_OFER
                         AND A.TICL_OID_TIPO_CLIE = 2);

EXCEPTION
  WHEN OTHERS THEN
    MAV_PR_REGIS_ERROR_AGREG(lnOidCliente, pnOidSolicitud);

END MAV_PR_VALID_AGREG;

/**************************************************************************
Descripcion       : Registrar Error del Proceso Generar Envio MAV

Fecha Creacion    : 06/02/2013
Parametros Entrada:
  pnOidCliente     :  Oid Cliente
  pnOidSolicitud   :  Oid Solicitud

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_REGIS_ERROR_AGREG(pnOidCliente          NUMBER,
                                   pnOidSolicitud        NUMBER)
IS PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN

  INSERT INTO MAV_TMP_CLIEN_ENVIO
   (CLIE_OID_CLIE,
    OID_SOLI_CABE,
    FEC_INCI)
  VALUES
   (pnOidCliente,
    pnOidSolicitud,
    SYSDATE);

 COMMIT;

EXCEPTION
 WHEN OTHERS THEN
   NULL;
END MAV_PR_REGIS_ERROR_AGREG;


/**************************************************************************
Descripcion       : Proceso Facturacion Gerentes MAV

Fecha Creacion    : 08/02/2013
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoPeriodo  :  Codigo de Periodo
  psCodigoActividad  :  Codigo de Actividad
  psCodigoUsuario   : Codigo Usuario

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE MAV_PR_FACTU_GEREN(psCodigoPais          VARCHAR2,
                             psCodigoPeriodo       VARCHAR2,
                             psCodigoActividad     VARCHAR2,
                             psCodigoUsuario       VARCHAR2)
IS

  lnOidPais               SEG_PAIS.OID_PAIS%TYPE;
  lnOidMarca              SEG_MARCA.OID_MARC%TYPE;
  lnOidCanal              SEG_CANAL.OID_CANA%TYPE;
  lnOidPeriodo            CRA_PERIO.OID_PERI%TYPE;


  lnOidCabecera           PED_SOLIC_CABEC.OID_SOLI_CABE%TYPE;
  lnOidClieDire		        MAE_CLIEN_DIREC.OID_CLIE_DIRE%TYPE;
  lnOidTipoDocu		        MAE_CLIEN_IDENT.TDOC_OID_TIPO_DOCU%TYPE;
  lsNumeroSolicitud       PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
  lsNumeroFormato         PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE;
  ldFechaProgFact         CRA_CRONO.FEC_INIC%TYPE;


  lnOidTerritorio		      ZON_TERRI.OID_TERR%TYPE;
  lnOidZona		            ZON_ZONA.OID_ZONA%TYPE;
  --INICIO AJUSTE
  lnOidZonaCrono          ZON_ZONA.OID_ZONA%TYPE;
  --FIN AJUSTE
  lnOidUbigeo		          ZON_TERRI.VEPO_OID_VALO_ESTR_GEOP%TYPE;
  lnOidTerritorioAdm	    ZON_TERRI_ADMIN.OID_TERR_ADMI%TYPE;
  lnCodigoPosicion        PED_SOLIC_POSIC.COD_POSI%TYPE;
  lsTipoActividad         CRA_ACTIV.COD_ACTI%TYPE;
  lsfechaFactura          DATE;

  CURSOR cursorCLI( codigoPais VARCHAR2) IS
    SELECT DISTINCT E.CLIE_OID_CLIE,
           E.TIP_CONS_DESP,
           E.COD_REGI,
           E.COD_ZONA,
           G.FOPA_OID_FORM_PAGO,
           G.TSPA_OID_TIPO_SOLI_PAIS_DEST,
           H.TSOL_OID_TIPO_CONS,
           H.ALMC_OID_ALMA
      FROM MAV_ENVIO_CONFI E,
           PRE_MATRI_FACTU M,
           PRE_OFERT_DETAL D,
           PRE_OFERT O,
           PRE_MATRI_FACTU_CABEC F,
           MAV_ACTIV G,
           PED_TIPO_SOLIC_PAIS H
     WHERE E.PAIS_COD_PAIS = codigoPais
       AND E.IND_ENVI = 'P'
       AND E.IND_ACTI ='1'
       AND E.EST_REGI = '1'
       AND E.MAFA_OID_MATR_FACT = M.OID_MATR_FACT
       AND D.OID_DETA_OFER = M.OFDE_OID_DETA_OFER
       AND F.PERD_OID_PERI = lnOidPeriodo
       AND F.OID_CABE = O.MFCA_OID_CABE
       AND O.OID_OFER = D.OFER_OID_OFER
       AND E.ACTI_OID_ACTI = G.OID_ACTI
       AND H.OID_TIPO_SOLI_PAIS = G.TSPA_OID_TIPO_SOLI_PAIS_DEST
       AND E.CLIE_OID_CLIE IS NOT NULL
       AND E.COR_PARA_CONF IN (SELECT mav.COR_PARA_CONF
                                 FROM MAV_PARAM_CONFI mav,
                                      MAV_PARAM_CORES_CABEC crcab
                                WHERE crcab.PAIS_COD_PAIS = mav.PAIS_COD_PAIS
                                  AND crcab.PACO_COR_PARA_CONF = mav.COR_PARA_CONF
                                  AND crcab.CAM_PROC_MAV = psCodigoPeriodo
                                  AND mav.COD_ACTI = NVL(psCodigoActividad, mav.COD_ACTI)
                                  AND mav.EST_REGI = '1'
                                  AND mav.IND_ACTI = 1
                                  AND crcab.EST_REGI = '1'
                                  AND crcab.IND_ACTI = 1
                                  AND mav.COD_ESTA_MAV = 4
                                  AND mav.TICL_OID_TIPO_CLIE =4);--ADD2


  TYPE interfazPedidos IS RECORD(
    oidCliente         MAV_ENVIO_CONFI.CLIE_OID_CLIE%TYPE,
    tipoGerente          MAV_ENVIO_CONFI.TIP_CONS_DESP%TYPE,
    codigoRegion         MAV_ENVIO_CONFI.COD_REGI%TYPE,
    codigoZona           MAV_ENVIO_CONFI.COD_ZONA%TYPE,
    oidFormaPago         MAV_ACTIV.FOPA_OID_FORM_PAGO%TYPE,
    oidTipoSolicitud     MAV_ACTIV.TSPA_OID_TIPO_SOLI_PAIS_DEST%TYPE,
    oidTipoConsolidado   PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_CONS%TYPE,
    oidAlmacen           PED_TIPO_SOLIC_PAIS.ALMC_OID_ALMA%TYPE
  );


  TYPE interfazPedidosTab IS TABLE OF interfazPedidos;
  interfazRecordN interfazPedidosTab;
  lnOidSoci   NUMBER;

BEGIN


  --Recuperamos el oid Pais,Marca,Canal, Periodo
  lnOidPais    := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(psCodigoPeriodo, lnOidMarca, lnOidCanal);


    --PROCESAMOS A LAS BOLSAS FALTANTES
  OPEN cursorCLI(psCodigoPais);
    LOOP
      FETCH cursorCLI BULK COLLECT
        INTO interfazRecordN LIMIT W_FILAS;


        IF interfazRecordN.COUNT > 0 THEN
        FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP

          --Recuperamos datos de la direccion del Cliente
          SELECT m.OID_CLIE_DIRE
            INTO lnOidClieDire
            FROM MAE_CLIEN_DIREC m
           WHERE m.clie_oid_clie = interfazRecordN(x).oidCliente
             AND m.ind_dire_ppal = 1
             AND m.ind_elim = 0;


          --Recuperamos los datos del documento de identidad del Cliente
          SELECT m.TDOC_OID_TIPO_DOCU
            INTO lnOidTipoDocu
            FROM MAE_CLIEN_IDENT m
           WHERE m.clie_oid_clie = interfazRecordN(x).oidCliente
             AND m.val_iden_docu_prin = 1;


          --Recuperamos los datos de la unidad administrativa del Cliente
          SELECT ter.TERR_OID_TERR,
                 zon.OID_ZONA,
                 ztr.VEPO_OID_VALO_ESTR_GEOP,
                 adm.ZTAD_OID_TERR_ADMI
            INTO lnOidTerritorio,
                 lnOidZona,
                 lnOidUbigeo,
                 lnOidTerritorioAdm
            FROM MAE_CLIEN_UNIDA_ADMIN adm,
                 ZON_TERRI_ADMIN       ter,
                 ZON_SECCI             sec,
                 ZON_ZONA              zon,
                 ZON_TERRI             ztr
           WHERE adm.clie_oid_clie = interfazRecordN(x).oidCliente
             AND adm.ind_acti = 1
             AND adm.ztad_oid_terr_admi = ter.oid_terr_admi
             AND ter.zscc_oid_secc = sec.oid_secc
             AND sec.zzon_oid_zona = zon.oid_zona
             AND ztr.oid_terr = ter.terr_oid_terr
             AND ztr.pais_oid_pais = ter.pais_oid_pais;


          --Obtenemos la activad, para obtener la fecha programada de facturacion
          IF(interfazRecordN(x).tipoGerente = 'GZ') THEN
            lsTipoActividad := 'FM';
            BEGIN
            SELECT ZZ.OID_ZONA
              INTO lnOidZonaCrono
              FROM ZON_ZONA ZZ
             WHERE ZZ.CLIE_OID_CLIE = interfazRecordN(x).oidCliente--180246
               AND ZZ.COD_ZONA= interfazRecordN(x).codigoZona --add
               AND ZZ.IND_ACTI = 1
               AND ZZ.IND_BORR = 0;

              EXCEPTION
            WHEN OTHERS THEN
              lnOidZonaCrono :=  NULL;
           END;
          ELSIF(interfazRecordN(x).tipoGerente = 'GR') THEN
            lsTipoActividad := 'SR';
         BEGIN
            SELECT ZZ.OID_ZONA
              INTO lnOidZonaCrono
              FROM ZON_REGIO ZR, ZON_ZONA ZZ
             WHERE ZR.OID_REGI = ZZ.ZORG_OID_REGI
               AND ZR.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
               AND ZR.COD_REGI = interfazRecordN(x).codigoRegion  --add
               AND ZZ.IND_ACTI = 1--add
               AND ZZ.IND_BORR = 0--add
               AND ZR.IND_ACTI = 1
               AND ZR.IND_BORR = 0
               AND ROWNUM =1;

          EXCEPTION
            WHEN OTHERS THEN
              lnOidZonaCrono :=  NULL;
         END;

          ELSIF(interfazRecordN(x).tipoGerente = 'CA') THEN
            lsTipoActividad := 'SC';

         BEGIN
          SELECT zo.oid_zona
            INTO lnOidZonaCrono
            FROM ZON_DIREC_VENTA_CABEC CA,
                 ZON_DIREC_VENTA_DETAL DE,
                 ZON_TIPO_CARGO CG,
                 ZON_ZONA ZO,
                 ZON_REGIO RE
           WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
             AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
             AND CA.PAIS_COD_PAIS = psCodigoPais
             AND CA.COD_CLIE = DE.COD_CLIE
             AND CA.FEC_REGI = DE.DICA_FEC_REGI
             AND CA.CAM_PROC = DE.DICA_CAM_PROC
             AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
             AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
             AND CA.EST_REGI = '1'
             AND DE.EST_REGI = '1'
             AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
             AND CG.EST_REGI = '1'
             AND CG.COD_TIPO_CARG = 'EE'
             AND CA.ESCA_COD_ESTA_CARG = 'A'
             AND DE.COD_SUBG = '01'
             AND DE.COD_REGI = RE.COD_REGI
             AND RE.COD_REGI = interfazRecordN(x).codigoRegion  --add2 FACT MAV PE
             AND zo.zorg_oid_regi = re.oid_regi
             AND ZO.IND_ACTI=1 --ADD3
             AND ZO.IND_BORR=0 --ADD3
             AND RE.IND_ACTI=1 --ADD3
             AND RE.IND_BORR=0 --ADD3
             AND DE.COD_CLIE = (SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE = interfazRecordN(x).oidCliente)
             AND ROWNUM = 1;

            EXCEPTION
            WHEN OTHERS THEN
              lnOidZonaCrono :=  NULL;
         END;
          ELSIF(interfazRecordN(x).tipoGerente = 'AZ') THEN
          lsTipoActividad := 'FM';
          BEGIN

           SELECT zo.oid_zona
            INTO lnOidZonaCrono
            FROM ZON_DIREC_VENTA_CABEC CA,
                 ZON_DIREC_VENTA_DETAL DE,
                 ZON_TIPO_CARGO CG,
                 ZON_ZONA ZO
           WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
             AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
             AND CA.PAIS_COD_PAIS = psCodigoPais
             AND CA.COD_CLIE = DE.COD_CLIE
             AND CA.FEC_REGI = DE.DICA_FEC_REGI
             AND CA.CAM_PROC = DE.DICA_CAM_PROC
             AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
             AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
             AND CA.EST_REGI = '1'
             AND DE.EST_REGI = '1'
             AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
             AND CG.EST_REGI = '1'
             AND CG.COD_TIPO_CARG IN ('MVZ')
             AND CA.ESCA_COD_ESTA_CARG = 'A'
             AND DE.COD_SUBG = '01'
             AND DE.COD_ZONA = ZO.COD_ZONA
             AND ZO.IND_ACTI=1 --ADD2
             AND ZO.IND_BORR=0 --ADD2
           AND ZO.COD_ZONA = interfazRecordN(x).codigoZona
             AND DE.COD_CLIE = (SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE = interfazRecordN(x).oidCliente)
             AND ROWNUM = 1;

            EXCEPTION
            WHEN OTHERS THEN
              lnOidZonaCrono :=  NULL;
         END;

          ELSIF(interfazRecordN(x).tipoGerente = 'AR') THEN
          lsTipoActividad := 'SR';
          BEGIN

          SELECT zo.oid_zona
            INTO lnOidZonaCrono
            FROM ZON_DIREC_VENTA_CABEC CA,
                 ZON_DIREC_VENTA_DETAL DE,
                 ZON_TIPO_CARGO CG,
                 ZON_ZONA ZO,
                 ZON_REGIO RE
           WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
             AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
             AND CA.PAIS_COD_PAIS = psCodigoPais
             AND CA.COD_CLIE = DE.COD_CLIE
             AND CA.FEC_REGI = DE.DICA_FEC_REGI
             AND CA.CAM_PROC = DE.DICA_CAM_PROC
             AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
             AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
             AND CA.EST_REGI = '1'
             AND DE.EST_REGI = '1'
             AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
             AND CG.EST_REGI = '1'
             AND CG.COD_TIPO_CARG = 'MVR'
             AND CA.ESCA_COD_ESTA_CARG = 'A'
             AND DE.COD_SUBG = '01'
             AND DE.COD_REGI = RE.COD_REGI
           AND RE.COD_REGI = interfazRecordN(x).codigoRegion
             AND ZO.IND_ACTI=1 --ADD2
             AND ZO.IND_BORR=0 --ADD2
             AND RE.IND_ACTI=1 --ADD2
             AND RE.IND_BORR=0 --ADD2
             AND zo.zorg_oid_regi = re.oid_regi
             AND DE.COD_CLIE = (SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE = interfazRecordN(x).oidCliente)
             AND ROWNUM = 1;

            EXCEPTION
            WHEN OTHERS THEN
              lnOidZonaCrono :=  NULL;
         END;

          ELSE
            --lsTipoActividad := 'FM';
            lsTipoActividad := NULL;

          END IF;


          SELECT TRUNC(FEC_PROC)
            INTO lsfechaFactura
          FROM BAS_CTRL_FACT X WHERE X.IND_CAMP_ACT = 1 AND X.STA_CAMP = 0;


          --Obtenemos la Fecha Programada de Facturacion
          BEGIN

            SELECT cro.FEC_INIC
              INTO ldFechaProgFact
              FROM CRA_CRONO cro,
                   CRA_ACTIV act
             WHERE cro.PERD_OID_PERI = lnOidPeriodo
               AND cro.ZZON_OID_ZONA = lnOidZonaCrono --NUEVA VARIABLE
               AND cro.CACT_OID_ACTI = act.OID_ACTI
               AND act.COD_ACTI = lsTipoActividad
               AND act.PAIS_OID_PAIS = lnOidPais
               --AND TRUNC(cro.FEC_INIC) >= TRUNC(SYSDATE);
               AND TRUNC(cro.FEC_INIC) = lsfechaFactura;

          EXCEPTION
            WHEN OTHERS THEN
              --ldFechaProgFact := TRUNC(SYSDATE);
              ldFechaProgFact :=  NULL;
          END;

          IF(ldFechaProgFact IS NOT NULL --'MAV Gerente GR'
          OR FIN_PKG_GENER.FIN_FN_OBTIE_DESCR_TSOLI_PAIS(interfazRecordN(x).oidTipoSolicitud) IN ('MAV Gerente BV','MAV Gerentes Catalogos GR')
          ) THEN -- O LA ACTIVIDAD ES <> MATERIAL APOYO
          -- obtenemos el siguiente numero de Secuencia
          SELECT PED_SOCA_SEQ.NEXTVAL INTO lnOidCabecera FROM DUAL;


          --Obtenemos el Numero de Solicitud
          lsNumeroSolicitud := STO_PKG_GENER.STO_FN_RESRV_SECUE_NSOLI(pscodigopais,
                                                                      'PED001',
                                                                      '000',
                                                                      0);


          lsNumeroFormato := to_char(SYSDATE, 'YY') || lpad(lsNumeroSolicitud, 8, '0') + 1;

          BEGIN
            SELECT PSOL.SOCI_OID_SOCI
            INTO lnOidSoci
            FROM ped_tipo_solic_pais PSOL
            WHERE psol.pais_oid_pais = lnOidPais
             AND psol.oid_tipo_soli_pais = interfazRecordN(x).oidTipoSolicitud;
          EXCEPTION
          WHEN no_data_found THEN
               lnOidSoci := 2001;
          END;

          INSERT INTO PED_SOLIC_CABEC
            (ACFI_OID_ACCE_FISI,
             ALMC_OID_ALMA,
             CLDI_OID_CLIE_DIRE,
             CLIE_OID_CLIE,
             CLIE_OID_CLIE_DEST,
             CLIE_OID_CLIE_PAGA,
             CLIE_OID_CLIE_RECE_FACT,
             CLIE_OID_CONS_ASOC,
             CLSO_OID_CLAS_SOLI,
             COPA_OID_PARA_GENE,
             ESSO_OID_ESTA_SOLI,
             FEC_CRON,
             FEC_PROG_FACT,
             FOPA_OID_FORM_PAGO,
             GRPR_OID_GRUP_PROC,
             IND_OC,
             IND_PEDI_PRUE,
             IND_PERM_UNIO_SOL,
             IND_TS_NO_CONSO,
             MODU_OID_MODU,
             MONE_OID_MONE,
             NUM_CLIEN,
             NUM_DOCU_ORIG,
             NUM_PREM,
             OID_SOLI_CABE,
             OPER_OID_OPER,
             PAIS_OID_PAIS,
             PERD_OID_PERI,
             PROC_OID_PROC,
             SBAC_OID_SBAC,
             SBTI_OID_SUBT_CLIE,
             SOCA_OID_DOCU_REFE,
             SOCI_OID_SOCI,
             TDOC_OID_TIPO_DOCU,
             TERR_OID_TERR,
             TICL_OID_TIPO_CLIE,
             TIDO_OID_TIPO_DOCU,
             TIDS_OID_TIPO_DESP,
             TSPA_OID_TIPO_SOLI_PAIS,
             TSPA_OID_TIPO_SOLI_PAIS_CONS,
             VAL_GLOS_OBSE,
             VAL_NUME_SOLI,
             VAL_USUA,
             VEPO_OID_VALO_ESTR_GEOP,
             VAL_TIPO_CAMB,
             ZZON_OID_ZONA,
             ZTAD_OID_TERR_ADMI,
             ICTP_OID_TIPO_PROG)
         VALUES
            (NULL,
             interfazRecordN(x).oidAlmacen,  --(OK) OBTENER ALMACEN ACTIVO (IGUAL PA TODOS)
             lnOidClieDire,  --(OK) OBTENER OID CLIEDIRE CLIENTE
             interfazRecordN(x).oidCliente,
             interfazRecordN(x).oidCliente,
             interfazRecordN(x).oidCliente,
             interfazRecordN(x).oidCliente,
             NULL,
             5,
             NULL,
             1,
             TRUNC(SYSDATE),
             lsfechaFactura,  --(OK) OBTENER FECHA PREVISTA FACTURACION
             interfazRecordN(x).oidFormaPago,
             3,
             0,
             0,
             1,
             1,
             9,
             NULL,
             NULL,
             NULL,
             NULL,
             lnOidCabecera,
             10,
             lnOidPais,
             lnOidPeriodo,
             1,
             888,
             4,
             NULL,
             lnOidSoci,
             lnOidTipoDocu,  --(OK) OBTENER DOCUMENTO PRINCIPAL DEL CLIENTE
             lnOidTerritorio, --(OK) OBTENER TERRITORIIO DEL CLIENTE
             4,
             8,
             1,
             interfazRecordN(x).oidTipoSolicitud,  --(OK) OBTENER GENERAL DE LA ACTIVIDAD, TSPA_OID_TIPO_SOLI_PAIS_DEST
             interfazRecordN(x).oidTipoConsolidado,  -- (OK) OBTENER GENERAL DE LA ACTIVIDAD, TSPA_OID_TIPO_SOLI_PAIS_CONS
             NULL,
             lsNumeroFormato, --(OK) CALCULAR EL VALOR DEL NUMERO DE SOLICITUD
             psCodigoUsuario,
             lnOidUbigeo,  --(OK) OBTENER EL OID_VALO_ESTR_GEOP DEL CLIENTE
             1.0,
             lnOidZona,  --(OK) OBTENER EL OID_ZONA DEL CLIENTE
             lnOidTerritorioAdm,  --(OK) OBTENER EL OID_TERR_ADMI DEL CLIENTE
             NULL);

          --Obtenemos la ultima posicion de la cabecera
          SELECT NVL(MAX(COD_POSI), 0)
            INTO lnCodigoPosicion
            FROM PED_SOLIC_POSIC
           WHERE SOCA_OID_SOLI_CABE = lnOidCabecera;

          --INSERTAMOS LA INFORMACIN DE LOS MAV PENDIENTE EN EL DETALL DE PEDIDO DE LA CONSULTORA
          INSERT INTO PED_SOLIC_POSIC
            (OID_SOLI_POSI,
             SOCA_OID_SOLI_CABE,
             COD_POSI,
             NUM_UNID_DEMA,
             NUM_UNID_POR_ATEN,
             TPOS_OID_TIPO_POSI,
             PROD_OID_PROD,
             FOPA_OID_FORM_PAGO,
             VAL_CODI_VENT,
             ESPO_OID_ESTA_POSI,
             STPO_OID_SUBT_POSI,
             VAL_CODI_VENT_FICT,
             NUM_UNID_DEMA_REAL,
             VAL_PREC_CATA_UNIT_LOCA,
             VAL_PREC_CONT_UNIT_LOCA,
             VAL_PREC_CATA_UNIT_DOCU,
             VAL_PREC_CONTA_UNIT_DOCU,
             VAL_PORC_DESC,
             VAL_IMPO_DESC_UNIT_DOCU,
             OFDE_OID_DETA_OFER,
             SOPO_OID_SOLI_POSI,
             NUM_UNID_COMPR,
             VAL_IMPO_DESC_UNIT_LOCA,
             NUM_PAGI_CATA,
             VAL_CATA)
            SELECT
                 PED_SOPO_SEQ.NEXTVAL,
                 lnOidCabecera,
                 lnCodigoPosicion + ROWNUM,
             0,
                 E.NUM_UNID,
             12,
                 D.PROD_OID_PROD,
                 E.FOPA_OID_FORM_PAGO,
                 D.VAL_CODI_VENT,
             4,
             16,
             NULL,
                 E.NUM_UNID,
                 DECODE(E.FCOB_OID_FORM_COBR, 1, 0, E.VAL_PREC),
                 DECODE(E.FCOB_OID_FORM_COBR, 1, E.VAL_PREC, 0),
                 DECODE(E.FCOB_OID_FORM_COBR, 1, 0, E.VAL_PREC),
                 DECODE(E.FCOB_OID_FORM_COBR, 1, E.VAL_PREC, 0),
             NULL,
             NULL,
                 D.OID_DETA_OFER,
             NULL,
                 E.NUM_UNID,
             NULL,
             NULL,
                 NULL
            FROM MAV_ENVIO_CONFI E, PRE_MATRI_FACTU F, PRE_OFERT_DETAL D, MAV_ACTIV A,
                 PRE_OFERT             O,
                 PRE_MATRI_FACTU_CABEC M
           WHERE E.IND_ENVI = 'P'
             AND E.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
             AND E.MAFA_OID_MATR_FACT = F.OID_MATR_FACT
             AND F.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
             AND E.ACTI_OID_ACTI = A.OID_ACTI
             AND M.PERD_OID_PERI = lnOidPeriodo
             AND M.OID_CABE = O.MFCA_OID_CABE
             AND O.OID_OFER = D.OFER_OID_OFER
             AND A.TICL_OID_TIPO_CLIE = 4
             AND E.TIP_CONS_DESP = interfazRecordN(x).tipoGerente
             AND E.COD_REGI = interfazRecordN(x).codigoRegion
             AND ((E.COD_ZONA IS NULL) OR (E.COD_ZONA = interfazRecordN(x).codigoZona))
             AND A.FOPA_OID_FORM_PAGO = interfazRecordN(x).oidFormaPago
             AND A.TSPA_OID_TIPO_SOLI_PAIS_DEST = interfazRecordN(x).oidTipoSolicitud
             AND E.COR_PARA_CONF IN (SELECT mav.COR_PARA_CONF
                                       FROM MAV_PARAM_CONFI mav,
                                            MAV_PARAM_CORES_CABEC crcab
                                      WHERE crcab.PAIS_COD_PAIS = mav.PAIS_COD_PAIS
                                        AND crcab.PACO_COR_PARA_CONF = mav.COR_PARA_CONF
                                        AND crcab.CAM_PROC_MAV = psCodigoPeriodo
                                        AND mav.COD_ACTI = NVL(psCodigoActividad, mav.COD_ACTI)
                                        AND mav.EST_REGI = '1'
                                        AND mav.IND_ACTI = 1
                                        AND crcab.EST_REGI = '1'
                                        AND crcab.IND_ACTI = 1
                                        AND mav.COD_ESTA_MAV = 4);

          --SE INSERTA REGISTROS EN LA TABLA DE SOLICIDUD DE ENVIO DE MAV
          INSERT INTO MAV_SOLIC_ENVIO_CONFI
            (OID_SOLI_ENVI,
             SOCA_OID_SOLI_CABE,
             IND_GENE,
             MENV_OID_ENVI)
          SELECT MAV_SOEC_SEQ.NEXTVAL,
                 lnOidCabecera,
                 1,
                 E.OID_ENVI
            FROM MAV_ENVIO_CONFI E, PRE_MATRI_FACTU F, PRE_OFERT_DETAL D, MAV_ACTIV A,
                 PRE_OFERT             O,
                 PRE_MATRI_FACTU_CABEC M
           WHERE E.IND_ENVI = 'P'
             AND E.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
             AND E.MAFA_OID_MATR_FACT = F.OID_MATR_FACT
             AND F.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
             AND E.ACTI_OID_ACTI = A.OID_ACTI
             AND M.PERD_OID_PERI = lnOidPeriodo
             AND M.OID_CABE = O.MFCA_OID_CABE
             AND O.OID_OFER = D.OFER_OID_OFER
             AND A.TICL_OID_TIPO_CLIE = 4
             AND E.TIP_CONS_DESP = interfazRecordN(x).tipoGerente
             AND E.COD_REGI = interfazRecordN(x).codigoRegion
             AND ((E.COD_ZONA IS NULL) OR (E.COD_ZONA = interfazRecordN(x).codigoZona))
             AND A.FOPA_OID_FORM_PAGO = interfazRecordN(x).oidFormaPago
             AND A.TSPA_OID_TIPO_SOLI_PAIS_DEST = interfazRecordN(x).oidTipoSolicitud
             AND E.COR_PARA_CONF IN (SELECT mav.COR_PARA_CONF
                                       FROM MAV_PARAM_CONFI mav,
                                            MAV_PARAM_CORES_CABEC crcab
                                      WHERE crcab.PAIS_COD_PAIS = mav.PAIS_COD_PAIS
                                        AND crcab.PACO_COR_PARA_CONF = mav.COR_PARA_CONF
                                        AND crcab.CAM_PROC_MAV = psCodigoPeriodo
                                        AND mav.COD_ACTI = NVL(psCodigoActividad, mav.COD_ACTI)
                                        AND mav.EST_REGI = '1'
                                        AND mav.IND_ACTI = 1
                                        AND crcab.EST_REGI = '1'
                                        AND crcab.IND_ACTI = 1
                                        AND mav.COD_ESTA_MAV = 4);

          --SE ACTUALIZA EL ESTADO DE LA ENTIDAD [ENVIO MAV] a Enviado (E)
          UPDATE MAV_ENVIO_CONFI
             SET IND_ENVI = 'E',
                 USU_MODI = psCodigoUsuario,
                 FEC_MODI = SYSDATE
           WHERE OID_ENVI IN (SELECT E.OID_ENVI
                                FROM MAV_ENVIO_CONFI E, PRE_MATRI_FACTU F, PRE_OFERT_DETAL D, MAV_ACTIV A,
                                     PRE_OFERT             O,
                                     PRE_MATRI_FACTU_CABEC M
                               WHERE E.IND_ENVI = 'P'
                                 AND E.CLIE_OID_CLIE = interfazRecordN(x).oidCliente
                                 AND E.MAFA_OID_MATR_FACT = F.OID_MATR_FACT
                                 AND F.OFDE_OID_DETA_OFER = D.OID_DETA_OFER
                                 AND E.ACTI_OID_ACTI = A.OID_ACTI
                                 AND M.PERD_OID_PERI = lnOidPeriodo
                                 AND M.OID_CABE = O.MFCA_OID_CABE
                                 AND O.OID_OFER = D.OFER_OID_OFER
                                 AND A.TICL_OID_TIPO_CLIE = 4
                                 AND E.TIP_CONS_DESP = interfazRecordN(x).tipoGerente
                                 AND E.COD_REGI = interfazRecordN(x).codigoRegion
                                 AND ((E.COD_ZONA IS NULL) OR (E.COD_ZONA = interfazRecordN(x).codigoZona))
                                 AND A.FOPA_OID_FORM_PAGO = interfazRecordN(x).oidFormaPago
                                 AND A.TSPA_OID_TIPO_SOLI_PAIS_DEST = interfazRecordN(x).oidTipoSolicitud
                                 AND E.COR_PARA_CONF IN (SELECT mav.COR_PARA_CONF
                                                           FROM MAV_PARAM_CONFI mav,
                                                                MAV_PARAM_CORES_CABEC crcab
                                                          WHERE crcab.PAIS_COD_PAIS = mav.PAIS_COD_PAIS
                                                            AND crcab.PACO_COR_PARA_CONF = mav.COR_PARA_CONF
                                                            AND crcab.CAM_PROC_MAV = psCodigoPeriodo
                                                            AND mav.COD_ACTI = NVL(psCodigoActividad, mav.COD_ACTI)
                                                            AND mav.EST_REGI = '1'
                                                            AND mav.IND_ACTI = 1
                                                            AND crcab.EST_REGI = '1'
                                                            AND crcab.IND_ACTI = 1
                                                            AND mav.COD_ESTA_MAV = 4)
                                 );


          END IF;

        END LOOP;


      END IF;
    EXIT WHEN cursorCLI%NOTFOUND;
    END LOOP;
    CLOSE cursorCLI;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_FACTU_GEREN: ' || ls_sqlerrm);

END MAV_PR_FACTU_GEREN;

/**************************************************************************
Descripcion       : Proceso que realiza la insercion de data en tablas temporal
                    para su visualizacion en los Reportes de Atenciones MAV
                    por Fecha-Campaña Según Tipo MAV/Actividad/Tipo Oferta/Producto/Tipo de Reporte
Fecha Creacion    : 19/07/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAV_PR_REPOR_ATENC_CAMPA(
  pscodigoTipoReporte     VARCHAR2,
  pscodigoRadio           VARCHAR2,
  pscodigoTipoMav         VARCHAR2,
  psflagRangoFechas       VARCHAR2,
  pnOidPais               NUMBER,
  psCodigoPeriodo         VARCHAR2,
  pscodigoTipoOferta      VARCHAR2,
  pscodigoActividad       VARCHAR2,
  pscodigoProducto        VARCHAR2,
  psfechaInicio           VARCHAR2,
  psfechaFin              VARCHAR2,
  psCodigoTipoCargo VARCHAR2,
  psIndicadorEnvio VARCHAR2)
IS

  lnOidPeriodo NUMBER;
  lsCodigoPais VARCHAR2(3);
  lsFlagCapacitadora BAS_PARAM_PAIS.VAL_PARA%TYPE;
  lsFlagTipoCargo    BAS_PARAM_PAIS.VAL_PARA%TYPE;
  lnOidTipoSolicitud NUMBER(12);

  lsFlagRegionesZonas VARCHAR2(1);
  lsFlagCodigoSAP     VARCHAR2(1);  
BEGIN

  DELETE FROM MAV_REPOR_CAMPA_CONSU;
  DELETE FROM MAV_REPOR_CAMPA_CONSU_DETAL;

  -- Obtenemos el Codigo del pais
  SELECT COD_PAIS INTO lsCodigoPais FROM SEG_PAIS WHERE OID_PAIS = pnOidPais;

  --Obtenemos el OID del periodo
  SELECT OID_PERI INTO lnOidPeriodo FROM CRA_PERIO WHERE VAL_NOMB_PERI LIKE '%'|| pscodigoPeriodo ||'%';

  --Obtenemos el OID del tipo de solicitud para consultoras
    SELECT tsp.OID_TIPO_SOLI_PAIS
    INTO lnOidTipoSolicitud
    FROM PED_TIPO_SOLIC_PAIS tsp, PED_TIPO_SOLIC sol
    WHERE tsp.TSOL_OID_TIPO_SOLI = sol.OID_TIPO_SOLI
    AND sol.COD_TIPO_SOLI = 'SOC'
    AND tsp.PAIS_OID_PAIS = pnOidPais;

  --Obtenemos los OID de tipos de solicitud para gerentes
    INSERT INTO MAE_GTT_REZOC( VAL_CODI, VAL_TIPO)
    SELECT DISTINCT act.TSPA_OID_TIPO_SOLI_PAIS_DEST, 'S' VAL_TIPO
    FROM MAV_PARAM_CONFI mav,  MAV_PARAM_CORES_CABEC crcab,  MAV_ACTIV act, PED_TIPO_SOLIC_PAIS tsp
    WHERE crcab.PAIS_COD_PAIS = mav.PAIS_COD_PAIS
    AND crcab.PACO_COR_PARA_CONF = mav.COR_PARA_CONF
    AND crcab.CAM_PROC_MAV = pscodigoPeriodo
    AND mav.EST_REGI = '1'
    AND mav.IND_ACTI = 1
    AND crcab.EST_REGI = '1'
    AND crcab.IND_ACTI = 1
    AND mav.COD_ESTA_MAV = 4
    AND mav.COD_ACTI = act.OID_ACTI
    AND mav.ticl_oid_tipo_clie = pscodigoTipoMav
    AND tsp.OID_TIPO_SOLI_PAIS = act.TSPA_OID_TIPO_SOLI_PAIS_DEST;

    --Verificamos si existen zonas/Regiones
    SELECT DECODE(COUNT(*), 0, '0', '1')
    INTO lsFlagRegionesZonas
    FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R';

    --Verificamos si existen zonas/Regiones
    SELECT DECODE(COUNT(*), 0, '0', '1')
    INTO lsFlagCodigoSAP
    FROM MAV_REPOR_ATENC_SAP_TEMPO;    
    

  BEGIN
    SELECT VAL_PARA
    INTO  lsFlagCapacitadora
    FROM BAS_PARAM_PAIS
    WHERE COD_PAIS = lsCodigoPais
    AND COD_SIST = 'MAV'
    AND COD_PARA = '003'
    AND IND_ACTI = '1';
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
          lsFlagCapacitadora := 'N';
  END;

  BEGIN
    SELECT VAL_PARA
    INTO  lsFlagTipoCargo
    FROM BAS_PARAM_PAIS
    WHERE COD_PAIS = lsCodigoPais
    AND COD_SIST = 'MAV'
    AND COD_PARA = '004'
    AND IND_ACTI = '1';
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
          lsFlagTipoCargo := 'N';
  END;

  IF pscodigoTipoReporte = '0' THEN
     IF pscodigoRadio = '0' THEN
        IF pscodigoTipoMav = '2' THEN
           IF psflagRangoFechas = 'on' THEN  /** 1 **/
            --RESUMEN CONSULTORAS CON FECHA
              INSERT INTO MAV_REPOR_CAMPA_CONSU(
              oid_soli_posi,
              cam_para_mav,
              fec_fact,
              cod_acti,
              des_acti,
              cod_tipo_ofer,
              des_tipo_ofer,
              cod_sap,
              des_prod,
              val_codi_vent,
              num_unid_dema_real,
              num_unid_aten,
              num_unid_falt,
              cod_regi,
              cod_zona
              )
              SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                       TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_FACTURACION,
                       CONFI.COD_ACTI CODACTIVIDAD,
                       G.VAL_I18N ACTIVIDAD,
                       CONFI.COD_TIPO_OFER CODTOFERTA,
                       G1.VAL_I18N TIPOOFERTA,
                       MP.COD_SAP CODIGOSAP,
                       NP.VAL_I18N DESCPRODUCTO,
                       CONFI.Val_Codi_Vent CUV,
                       PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                       PSP.NUM_UNID_ATEN UNIATENDIDA,
                     (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                     ZR.COD_REGI,
                     ZN.COD_ZONA
                 FROM PED_SOLIC_CABEC PSC,
                      PED_SOLIC_CABEC B,
                      PED_SOLIC_POSIC PSP,
                      MAE_PRODU MP,
                       (SELECT * FROM GEN_I18N_SICC_PAIS
                         WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                        MAV_PARAM_CONFI CONFI,
                        MAV_ACTIV_TIPO_OFERT ACTIV,
                        GEN_I18N_SICC_PAIS G,
                        (SELECT * FROM GEN_I18N_SICC_COMUN
                    WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                   ZON_ZONA ZN,
                   ZON_REGIO ZR,
                MAV_ENVIO_CONFI ENVI,
                MAV_SOLIC_ENVIO_CONFI SOLEN
                WHERE 1=1
                AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                AND PSC.PERD_OID_PERI = lnOidPeriodo
                AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                  AND PSP.TPOS_OID_TIPO_POSI = 3
                AND PSC.TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicitud
                  AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                  AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                  AND B.ESSO_OID_ESTA_SOLI <> 4
                  AND PSP.ESPO_OID_ESTA_POSI <> 2
                  AND PSP.PROD_OID_PROD = MP.OID_PROD
                  AND PSP.PROD_OID_PROD = NP.VAL_OID
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                  AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                AND CONFI.COD_SAP =  MP.COD_SAP
                  AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                  AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                  AND G.ATTR_ENTI = 'MAV_ACTIV'
                  AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                  AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                  AND CONFI.COD_ESTA_MAV IN (3,4,5)
                  AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
                AND ZN.ZORG_OID_REGI = ZR.OID_REGI
                AND ((lsFlagRegionesZonas = '1' AND ZN.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
                AND TRUNC(PSC.FEC_FACT) >= to_date(psFechaInicio, 'DD/MM/YYYY')
                AND TRUNC(PSC.FEC_FACT) <= to_date(psFechaFin, 'DD/MM/YYYY');

           ELSE /** 1 **/
            --RESUMEN CONSULTORAS SIN FECHA
              INSERT INTO MAV_REPOR_CAMPA_CONSU(
              oid_soli_posi,
              cam_para_mav,
              fec_fact,
              cod_acti,
              des_acti,
              cod_tipo_ofer,
              des_tipo_ofer,
              cod_sap,
              des_prod,
              val_codi_vent,
              num_unid_dema_real,
              num_unid_aten,
              num_unid_falt,
              cod_regi,
              cod_zona
              )
              SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                       TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_FACTURACION,
                       CONFI.COD_ACTI CODACTIVIDAD,
                       G.VAL_I18N ACTIVIDAD,
                       CONFI.COD_TIPO_OFER CODTOFERTA,
                       G1.VAL_I18N TIPOOFERTA,
                       MP.COD_SAP CODIGOSAP,
                       NP.VAL_I18N DESCPRODUCTO,
                       CONFI.Val_Codi_Vent CUV,
                       PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                       PSP.NUM_UNID_ATEN UNIATENDIDA,
                     (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                     ZR.COD_REGI,
                     ZN.COD_ZONA
                 FROM PED_SOLIC_CABEC PSC,
                      PED_SOLIC_CABEC B,
                      PED_SOLIC_POSIC PSP,
                      MAE_PRODU MP,
                       (SELECT * FROM GEN_I18N_SICC_PAIS
                         WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                        MAV_PARAM_CONFI CONFI,
                        MAV_ACTIV_TIPO_OFERT ACTIV,
                        GEN_I18N_SICC_PAIS G,
                        (SELECT * FROM GEN_I18N_SICC_COMUN
                    WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                   ZON_ZONA ZN,
                   ZON_REGIO ZR,
                MAV_ENVIO_CONFI ENVI,
                MAV_SOLIC_ENVIO_CONFI SOLEN
                WHERE 1=1
                AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                AND PSC.PERD_OID_PERI = lnOidPeriodo
                AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                  AND PSP.TPOS_OID_TIPO_POSI = 3
                AND PSC.TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicitud
                  AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                  AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                  AND B.ESSO_OID_ESTA_SOLI <> 4
                  AND PSP.ESPO_OID_ESTA_POSI <> 2
                  AND PSP.PROD_OID_PROD = MP.OID_PROD
                  AND PSP.PROD_OID_PROD = NP.VAL_OID
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                  AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
                  AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                  AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                  AND G.ATTR_ENTI = 'MAV_ACTIV'
                  AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                  AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                  AND CONFI.COD_ESTA_MAV IN (3,4,5)
                  AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
                AND ZN.ZORG_OID_REGI = ZR.OID_REGI
                AND ((lsFlagRegionesZonas = '1' AND ZN.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0');
           END IF;

        ELSE
           IF psflagRangoFechas = 'on' THEN /** 2 **/
                --RESUMEN GERENTES CON FECHA

                IF lsFlagCapacitadora = 'S' THEN
                  INSERT INTO MAV_REPOR_CAMPA_CONSU(
                  oid_soli_posi,
                  cam_para_mav,
                  fec_fact,
                  cod_acti,
                  des_acti,
                  cod_tipo_ofer,
                  des_tipo_ofer,
                  cod_sap,
                  des_prod,
                  val_codi_vent,
                  num_unid_dema_real,
                  num_unid_aten,
                  num_unid_falt,
                  cod_regi,
                  cod_zona
                  )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                       TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_FACTURACION,
                       CONFI.COD_ACTI CODACTIVIDAD,
                       G.VAL_I18N ACTIVIDAD,
                       CONFI.COD_TIPO_OFER CODTOFERTA,
                       G1.VAL_I18N TIPOOFERTA,
                       MP.COD_SAP CODIGOSAP,
                       NP.VAL_I18N DESCPRODUCTO,
                       CONFI.Val_Codi_Vent CUV,
                       PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                       PSP.NUM_UNID_ATEN UNIATENDIDA,
                       (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                       ENVI.COD_REGI,
                       ENVI.COD_ZONA
                 FROM PED_SOLIC_CABEC PSC,
                   PED_SOLIC_CABEC B,
                   PED_SOLIC_POSIC PSP,
                   MAE_PRODU MP,
                   (SELECT * FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                    MAV_PARAM_CONFI CONFI,
                    MAV_ACTIV_TIPO_OFERT ACTIV,
                    GEN_I18N_SICC_PAIS G,
                    (SELECT * FROM GEN_I18N_SICC_COMUN
                      WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                    MAV_ENVIO_CONFI ENVI,
                    MAV_SOLIC_ENVIO_CONFI SOLEN
                    WHERE 1 = 1
                    AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                    AND PSC.PERD_OID_PERI = lnOidPeriodo
                    AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                    AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN (SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
                  AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                  AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                  AND B.ESSO_OID_ESTA_SOLI <> 4
                  AND PSP.ESPO_OID_ESTA_POSI <> 2
                  AND PSP.PROD_OID_PROD = MP.OID_PROD
                  AND PSP.PROD_OID_PROD = NP.VAL_OID
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                  AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
                  AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                  AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                  AND G.ATTR_ENTI = 'MAV_ACTIV'
                  AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                  AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                  AND CONFI.COD_ESTA_MAV IN (3,4,5)
                  AND (pscodigoActividad IS NULL OR ACTIV.ACTI_OID_ACTI = pscodigoActividad)
                  AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                  AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
                  AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
                  AND TRUNC(PSC.FEC_FACT) >= to_date(psFechaInicio, 'DD/MM/YYYY')
                  AND TRUNC(PSC.FEC_FACT) <= to_date(psFechaFin, 'DD/MM/YYYY');

                ELSE
              INSERT INTO MAV_REPOR_CAMPA_CONSU(
                  oid_soli_posi,
              cam_para_mav,
              fec_fact,
              cod_acti,
              des_acti,
              cod_tipo_ofer,
              des_tipo_ofer,
              cod_sap,
              des_prod,
              val_codi_vent,
              num_unid_dema_real,
              num_unid_aten,
                  num_unid_falt,
                  cod_regi,
                  cod_zona
              )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                     TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_FACTURACION,
                           CONFI.COD_ACTI CODACTIVIDAD,
                           G.VAL_I18N ACTIVIDAD,
                           CONFI.COD_TIPO_OFER CODTOFERTA,
                           G1.VAL_I18N TIPOOFERTA,
                           MP.COD_SAP CODIGOSAP,
                           NP.VAL_I18N DESCPRODUCTO,
                           CONFI.Val_Codi_Vent CUV,
                           PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                           PSP.NUM_UNID_ATEN UNIATENDIDA,
                       (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                       ZR.COD_REGI,
                       ZN.COD_ZONA
                     FROM PED_SOLIC_CABEC PSC,
                       PED_SOLIC_CABEC B,
                       PED_SOLIC_POSIC PSP,
                       MAE_PRODU MP,
                       (SELECT * FROM GEN_I18N_SICC_PAIS
                         WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                        MAV_PARAM_CONFI CONFI,
                        MAV_ACTIV_TIPO_OFERT ACTIV,
                        GEN_I18N_SICC_PAIS G,
                        (SELECT * FROM GEN_I18N_SICC_COMUN
                      WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                     ZON_ZONA ZN,
                     ZON_REGIO ZR,
                    MAV_ENVIO_CONFI ENVI,
                    MAV_SOLIC_ENVIO_CONFI SOLEN
                    WHERE 1 = 1
                    AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                    AND PSC.PERD_OID_PERI = lnOidPeriodo
                    AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                    AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN (SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
                      AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                      AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                      AND B.ESSO_OID_ESTA_SOLI <> 4
                      AND PSP.ESPO_OID_ESTA_POSI <> 2
                      AND PSP.PROD_OID_PROD = MP.OID_PROD
                      AND PSP.PROD_OID_PROD = NP.VAL_OID
                      AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                      AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                      AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
                      AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                      AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                      AND G.ATTR_ENTI = 'MAV_ACTIV'
                      AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                      AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                      AND CONFI.COD_ESTA_MAV IN (3,4,5)
                      AND (pscodigoActividad IS NULL OR ACTIV.ACTI_OID_ACTI = pscodigoActividad)
                      AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                  AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
                  AND ZN.ZORG_OID_REGI = ZR.OID_REGI
                  AND ((lsFlagRegionesZonas = '1' AND ZN.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
                  AND TRUNC(PSC.FEC_FACT) >= to_date(psFechaInicio, 'DD/MM/YYYY')
                  AND TRUNC(PSC.FEC_FACT) <= to_date(psFechaFin, 'DD/MM/YYYY');

                END IF;

           ELSE /** 2 **/
           --RESUMEN GERENTES SIN FECHA
                IF lsFlagCapacitadora = 'S' THEN
                  INSERT INTO MAV_REPOR_CAMPA_CONSU(
                  oid_soli_posi,
                  cam_para_mav,
                  fec_fact,
                  cod_acti,
                  des_acti,
                  cod_tipo_ofer,
                  des_tipo_ofer,
                  cod_sap,
                  des_prod,
                  val_codi_vent,
                  num_unid_dema_real,
                  num_unid_aten,
                  num_unid_falt,
                  cod_regi,
                  cod_zona
                  )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                       TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_FACTURACION,
                       CONFI.COD_ACTI CODACTIVIDAD,
                       G.VAL_I18N ACTIVIDAD,
                       CONFI.COD_TIPO_OFER CODTOFERTA,
                       G1.VAL_I18N TIPOOFERTA,
                       MP.COD_SAP CODIGOSAP,
                       NP.VAL_I18N DESCPRODUCTO,
                       CONFI.Val_Codi_Vent CUV,
                       PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                       PSP.NUM_UNID_ATEN UNIATENDIDA,
                       (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                       ENVI.COD_REGI,
                       ENVI.COD_ZONA
                 FROM PED_SOLIC_CABEC PSC,
                   PED_SOLIC_CABEC B,
                   PED_SOLIC_POSIC PSP,
                   MAE_PRODU MP,
                   (SELECT * FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                    MAV_PARAM_CONFI CONFI,
                    MAV_ACTIV_TIPO_OFERT ACTIV,
                    GEN_I18N_SICC_PAIS G,
                    (SELECT * FROM GEN_I18N_SICC_COMUN
                      WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                    MAV_ENVIO_CONFI ENVI,
                    MAV_SOLIC_ENVIO_CONFI SOLEN
                    WHERE 1 = 1
                    AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                    AND PSC.PERD_OID_PERI = lnOidPeriodo
                    AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                    AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
                  AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                  AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                  AND B.ESSO_OID_ESTA_SOLI <> 4
                  AND PSP.ESPO_OID_ESTA_POSI <> 2
                  AND PSP.PROD_OID_PROD = MP.OID_PROD
                  AND PSP.PROD_OID_PROD = NP.VAL_OID
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                  AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
                  AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                  AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                  AND G.ATTR_ENTI = 'MAV_ACTIV'
                  AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                  AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                  AND CONFI.COD_ESTA_MAV IN (3,4,5)
                  AND (pscodigoActividad IS NULL OR ACTIV.ACTI_OID_ACTI = pscodigoActividad)
                  AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                  AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
                  AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0');

                ELSE
              INSERT INTO MAV_REPOR_CAMPA_CONSU(
                  oid_soli_posi,
              cam_para_mav,
              fec_fact,
              cod_acti,
              des_acti,
              cod_tipo_ofer,
              des_tipo_ofer,
              cod_sap,
              des_prod,
              val_codi_vent,
              num_unid_dema_real,
              num_unid_aten,
                  num_unid_falt,
                  cod_regi,
                  cod_zona
              )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                     TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_FACTURACION,
                           CONFI.COD_ACTI CODACTIVIDAD,
                           G.VAL_I18N ACTIVIDAD,
                           CONFI.COD_TIPO_OFER CODTOFERTA,
                           G1.VAL_I18N TIPOOFERTA,
                           MP.COD_SAP CODIGOSAP,
                           NP.VAL_I18N DESCPRODUCTO,
                           CONFI.Val_Codi_Vent CUV,
                           PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                           PSP.NUM_UNID_ATEN UNIATENDIDA,
                       (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                       ZR.COD_REGI,
                       ZN.COD_ZONA
                     FROM PED_SOLIC_CABEC PSC,
                       PED_SOLIC_CABEC B,
                       PED_SOLIC_POSIC PSP,
                       MAE_PRODU MP,
                       (SELECT * FROM GEN_I18N_SICC_PAIS
                         WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                        MAV_PARAM_CONFI CONFI,
                        MAV_ACTIV_TIPO_OFERT ACTIV,
                        GEN_I18N_SICC_PAIS G,
                        (SELECT * FROM GEN_I18N_SICC_COMUN
                      WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                     ZON_ZONA ZN,
                     ZON_REGIO ZR,
                    MAV_ENVIO_CONFI ENVI,
                    MAV_SOLIC_ENVIO_CONFI SOLEN
                    WHERE 1 = 1
                    AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                    AND PSC.PERD_OID_PERI = lnOidPeriodo
                    AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                    AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
                      AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                      AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                      AND B.ESSO_OID_ESTA_SOLI <> 4
                      AND PSP.ESPO_OID_ESTA_POSI <> 2
                      AND PSP.PROD_OID_PROD = MP.OID_PROD
                      AND PSP.PROD_OID_PROD = NP.VAL_OID
                      AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                      AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                      AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
                      AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                      AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                      AND G.ATTR_ENTI = 'MAV_ACTIV'
                      AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                      AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                      AND CONFI.COD_ESTA_MAV IN (3,4,5)
                      AND (pscodigoActividad IS NULL OR ACTIV.ACTI_OID_ACTI = pscodigoActividad)
                      AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                  AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
                  AND ZN.ZORG_OID_REGI = ZR.OID_REGI
                  AND ((lsFlagRegionesZonas = '1' AND ZN.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0');

                END IF;

           END IF;
        END IF;
     ELSE
       IF pscodigoTipoMav = '2' THEN /** 3 **/

          IF psflagRangoFechas = 'on' THEN
            -- DETALLE CONSULTORAS CON FECHA
              INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                oid_soli_posi,
                cam_para_mav,
                fec_fact,
                cod_regi,
                cod_zona,
                COD_SECC,
                COD_TERR,
                cod_acti,
                des_acti,
                cod_tipo_ofer,
                des_tipo_ofer,
                cod_clie,
                des_clie,
                des_tele,
                des_movi,
                cod_sap,
                des_prod,
                val_codi_vent,
                num_unid_dema_real,
                num_unid_aten,
                NUM_UNID_POR_ATEN,
                num_unid_falt,
                VAL_PREC_UNIT,
                VAL_PORC_DESC,
                VAL_NUME_SOLI,
                VAL_ESTA,
                cod_clie_ante
              )
              SELECT DISTINCT PSP.OID_SOLI_POSI,
                              CONFI.CAM_PARA_MAV CAMPANA,
                              TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_ENVIO,
                              ZR.COD_REGI REGION,
                              ZN.COD_ZONA ZONA,
                              SECC.COD_SECC  SECCION,
                              TERR.COD_TERR TERR,
                              CONFI.COD_ACTI CODACTIVIDAD,
                              G.VAL_I18N ACTIVIDAD,
                              CONFI.COD_TIPO_OFER CODTOFERTA,
                              G1.VAL_I18N TIPOOFERTA,
                              CLI.COD_CLIE CODIGOCLIE,
                              TRIM(CLI.VAL_APE1) || ' ' || TRIM(CLI.VAL_APE2) || ' ' ||
                              TRIM(CLI.VAL_NOM1) || ' ' || TRIM(CLI.VAL_NOM2) CONSULTORA,
                              (SELECT MCC.VAL_TEXT_COMU
                                 FROM MAE_CLIEN_COMUN MCC
                                WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE
                                  AND MCC.TICM_OID_TIPO_COMU = 1) TELEFONO,
                              (SELECT MCC1.VAL_TEXT_COMU
                                 FROM MAE_CLIEN_COMUN MCC1
                                WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE
                                  AND MCC1.TICM_OID_TIPO_COMU = 6) MOVIL,
                              MP.COD_SAP CODIGOSAP,
                              NP.VAL_I18N DESCPRODUCTO,
                              CONFI.Val_Codi_Vent CUV,
                              PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                              PSP.NUM_UNID_ATEN UNIATENDIDA,
                              PSP.NUM_UNID_POR_ATEN POR_ATENDER,
                              (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                              PSP.VAL_PREC_FACT_UNIT_LOCA AS PRECIO_UNITARIO,
                     PSP.VAL_PORC_DESC AS PORC_DSCTO, 
                     PSC.VAL_NUME_SOLI AS PEDIDO,
                    (SELECT VAL_I18N FROM MAE_CLIEN_DATOS_ADICI ADI,GEN_I18N_SICC_COMUN
                     WHERE CLIE_OID_CLIE=PSC.CLIE_OID_CLIE 
                     AND ATTR_ENTI='MAE_ESTAT_CLIEN'
                     AND VAL_OID=ADI.ESTA_OID_ESTA_CLIE) AS ESTATUS,
                     (select num_docu_iden from mae_clien_ident  where val_iden_docu_prin=1 and clie_oid_clie = cli.oid_clie) documento_identidad
                FROM PED_SOLIC_CABEC PSC,
                     PED_SOLIC_CABEC B,
                     PED_SOLIC_POSIC PSP,
                     MAE_CLIEN CLI,
                     MAE_PRODU MP,
                     (SELECT * FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                     MAV_PARAM_CONFI CONFI,
                     MAV_ACTIV_TIPO_OFERT ACTIV,
                     GEN_I18N_SICC_PAIS G,
                     (SELECT * FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                     ZON_ZONA ZN,
                     ZON_REGIO ZR,
                     MAV_ENVIO_CONFI ENVI,
                     MAV_SOLIC_ENVIO_CONFI SOLEN,
                     ZON_TERRI_ADMIN ZTAD,
                     ZON_SECCI SECC,
                     ZON_TERRI TERR
               WHERE 1 = 1
                 AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                 AND PSC.PERD_OID_PERI = lnOidPeriodo
                 AND PSC.CLIE_OID_CLIE = CLI.OID_CLIE
                 AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                 AND PSP.TPOS_OID_TIPO_POSI = 3
                 AND PSC.TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicitud
                 AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                 AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                 AND B.ESSO_OID_ESTA_SOLI <> 4
                 AND PSP.ESPO_OID_ESTA_POSI <> 2
                 AND PSP.PROD_OID_PROD = MP.OID_PROD
                 AND PSP.PROD_OID_PROD = NP.VAL_OID
                 AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                 AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                 AND CONFI.COD_SAP = MP.COD_SAP
                 AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                 AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                 AND G.ATTR_ENTI = 'MAV_ACTIV'
                 AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                 AND (pscodigoTipoOferta IS NULL OR
                     ACTIV.TOFE_OID_TIPO_OFER IN
                     (SELECT A.OID_TIPO_OFER
                         FROM PRE_TIPO_OFERT A
                        WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                 AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                 AND CONFI.COD_ESTA_MAV IN (3, 4, 5)
                 AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                 AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                 AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                 AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                 AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                    --
                 AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                    --
                 AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
                 AND ZN.ZORG_OID_REGI = ZR.OID_REGI
                 AND ((lsFlagRegionesZonas = '1' AND
                     ZN.COD_ZONA IN
                     (SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR
                     lsFlagRegionesZonas = '0')
                 AND TRUNC(PSC.FEC_FACT) >= to_date(psFechaInicio, 'DD/MM/YYYY')
                 AND TRUNC(PSC.FEC_FACT) <= to_date(psFechaFin, 'DD/MM/YYYY')
                 AND PSC.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
                 AND ZTAD.ZSCC_OID_SECC=SECC.OID_SECC
                 AND ZTAD.TERR_OID_TERR=TERR.OID_TERR;

          ELSE
            -- DETALLE CONSULTORAS SIN FECHA
              /** 3 **/
             INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                oid_soli_posi,
                cam_para_mav,
                fec_fact,
                cod_regi,
                cod_zona,
                COD_SECC,
                COD_TERR,
                cod_acti,
                des_acti,
                cod_tipo_ofer,
                des_tipo_ofer,
                cod_clie,
                des_clie,
                des_tele,
                des_movi,
                cod_sap,
                des_prod,
                val_codi_vent,
                num_unid_dema_real,
                num_unid_aten,
                NUM_UNID_POR_ATEN,
                num_unid_falt,
                VAL_PREC_UNIT,
                VAL_PORC_DESC,
                VAL_NUME_SOLI,
                VAL_ESTA,
                cod_clie_ante
              )
              SELECT DISTINCT PSP.OID_SOLI_POSI,
                      CONFI.CAM_PARA_MAV CAMPANA,
                      TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_ENVIO,
                      ZR.COD_REGI REGION,
                      ZN.COD_ZONA ZONA,
                      SECC.COD_SECC  SECCION,
                      TERR.COD_TERR TERR,
                      CONFI.COD_ACTI CODACTIVIDAD,
                      G.VAL_I18N ACTIVIDAD,
                      CONFI.COD_TIPO_OFER CODTOFERTA,
                      G1.VAL_I18N TIPOOFERTA,
                      CLI.COD_CLIE CODIGOCLIE,
                      TRIM(CLI.VAL_APE1) || ' ' || TRIM(CLI.VAL_APE2) || ' ' ||
                      TRIM(CLI.VAL_NOM1) || ' ' || TRIM(CLI.VAL_NOM2) CONSULTORA,
                      (SELECT MCC.VAL_TEXT_COMU
                         FROM MAE_CLIEN_COMUN MCC
                        WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE
                          AND MCC.TICM_OID_TIPO_COMU = 1) TELEFONO,
                      (SELECT MCC1.VAL_TEXT_COMU
                         FROM MAE_CLIEN_COMUN MCC1
                        WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE
                          AND MCC1.TICM_OID_TIPO_COMU = 6) MOVIL,
                      MP.COD_SAP CODIGOSAP,
                      NP.VAL_I18N DESCPRODUCTO,
                      CONFI.Val_Codi_Vent CUV,
                      PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                      PSP.NUM_UNID_ATEN UNIATENDIDA,
                      PSP.NUM_UNID_POR_ATEN POR_ATENDER,
                      (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                      PSP.VAL_PREC_FACT_UNIT_LOCA AS PRECIO_UNITARIO,
                           PSP.VAL_PORC_DESC AS PORC_DSCTO, 
                           PSC.VAL_NUME_SOLI AS PEDIDO,
                          (SELECT VAL_I18N FROM MAE_CLIEN_DATOS_ADICI ADI,GEN_I18N_SICC_COMUN
                           WHERE CLIE_OID_CLIE=PSC.CLIE_OID_CLIE 
                           AND ATTR_ENTI='MAE_ESTAT_CLIEN'
                           AND VAL_OID=ADI.ESTA_OID_ESTA_CLIE) AS ESTATUS,
                      (select num_docu_iden from mae_clien_ident  where val_iden_docu_prin=1 and clie_oid_clie = cli.oid_clie) documento_identidad
        FROM PED_SOLIC_CABEC PSC,
             PED_SOLIC_CABEC B,
             PED_SOLIC_POSIC PSP,
             MAE_CLIEN CLI,
             MAE_PRODU MP,
             (SELECT * FROM GEN_I18N_SICC_PAIS WHERE ATTR_ENTI = 'MAE_PRODU') NP,
             MAV_PARAM_CONFI CONFI,
             MAV_ACTIV_TIPO_OFERT ACTIV,
             GEN_I18N_SICC_PAIS G,
             (SELECT * FROM GEN_I18N_SICC_COMUN WHERE ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
             ZON_ZONA ZN,
             ZON_REGIO ZR,
             MAV_ENVIO_CONFI ENVI,
             MAV_SOLIC_ENVIO_CONFI SOLEN,
             ZON_TERRI_ADMIN ZTAD,
             ZON_SECCI SECC,
             ZON_TERRI TERR
       WHERE 1 = 1
         AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
         AND PSC.PERD_OID_PERI = lnOidPeriodo
         AND PSC.CLIE_OID_CLIE = CLI.OID_CLIE
         AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
         AND PSP.TPOS_OID_TIPO_POSI = 3
         AND PSC.TSPA_OID_TIPO_SOLI_PAIS = lnOidTipoSolicitud
         AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
         AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
         AND B.ESSO_OID_ESTA_SOLI <> 4
         AND PSP.ESPO_OID_ESTA_POSI <> 2
         AND PSP.PROD_OID_PROD = MP.OID_PROD
         AND PSP.PROD_OID_PROD = NP.VAL_OID
         AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
         AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
         AND CONFI.COD_SAP = MP.COD_SAP
         AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
         AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
         AND G.ATTR_ENTI = 'MAV_ACTIV'
         AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
         AND (pscodigoTipoOferta IS NULL OR
             ACTIV.TOFE_OID_TIPO_OFER IN
             (SELECT A.OID_TIPO_OFER
                 FROM PRE_TIPO_OFERT A
                WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
         AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
         AND CONFI.COD_ESTA_MAV IN (3, 4, 5)
         AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
         AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
         AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
         AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
         AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
            --
         AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
            --
         AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
         AND ZN.ZORG_OID_REGI = ZR.OID_REGI
         AND ((lsFlagRegionesZonas = '1' AND
             ZN.COD_ZONA IN
             (SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR
             lsFlagRegionesZonas = '0')
         AND PSC.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI    
         AND ZTAD.ZSCC_OID_SECC=SECC.OID_SECC
         AND ZTAD.TERR_OID_TERR=TERR.OID_TERR;

          END IF;

       ELSE
          IF psflagRangoFechas = 'on' THEN /** 4 **/
            -- DETALLE GERENTES CON FECHA
            IF lsFlagTipoCargo = 'S' THEN
                 INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                    oid_soli_posi,
                    cam_para_mav,
                    fec_fact,
                    cod_regi,
                    cod_zona,
                    cod_acti,
                    des_acti,
                    cod_tipo_ofer,
                    des_tipo_ofer,
                    cod_clie,
                    des_clie,
                    des_tele,
                    des_movi,
                    cod_sap,
                    des_prod,
                    val_codi_vent,
                    num_unid_dema_real,
                    num_unid_aten,
                    num_unid_falt,
                    cod_clie_ante,
                    COD_TIPO_CARG,
                    COD_SECC,
                    COD_TERR,
                    NUM_UNID_POR_ATEN,
                    VAL_PREC_UNIT,
                    VAL_PORC_DESC,
                    VAL_NUME_SOLI,
                    VAL_ESTA
                  )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                       TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_ENVIO,
                       ENVI.COD_REGI,
                       ENVI.COD_ZONA,
                       CONFI.COD_ACTI CODACTIVIDAD,
                       G.VAL_I18N ACTIVIDAD,
                       CONFI.COD_TIPO_OFER CODTOFERTA,
                       G1.VAL_I18N TIPOOFERTA,
                       CLI.COD_CLIE CODIGOCLIE,
                       TRIM(CLI.VAL_APE1)  ||' ' ||  TRIM(CLI.VAL_APE2)   ||' ' ||  TRIM(CLI.VAL_NOM1)  ||' ' ||  TRIM(CLI.VAL_NOM2) CONSULTORA,
                       (SELECT MCC.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC.TICM_OID_TIPO_COMU = 1 ) TELEFONO,
                       (SELECT MCC1.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC1  WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC1.TICM_OID_TIPO_COMU = 6 ) MOVIL,
                       MP.COD_SAP CODIGOSAP,
                       NP.VAL_I18N DESCPRODUCTO,
                       CONFI.Val_Codi_Vent CUV,
                       PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                       PSP.NUM_UNID_ATEN UNIATENDIDA,
                       (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                       (select num_docu_iden from mae_clien_ident  where val_iden_docu_prin=1 and clie_oid_clie = cli.oid_clie) documento_identidad,
                       ENVI.TIP_CONS_DESP,
                       SECC.COD_SECC,
                     TERR.COD_TERR,
                     PSP.NUM_UNID_POR_ATEN,
                     PSP.VAL_PREC_FACT_UNIT_LOCA,
                     PSP.VAL_PORC_DESC, 
                     PSC.VAL_NUME_SOLI,
                     (SELECT VAL_I18N 
                        FROM MAE_CLIEN_DATOS_ADICI ADI, GEN_I18N_SICC_COMUN
                       WHERE CLIE_OID_CLIE = PSC.CLIE_OID_CLIE 
                         AND ATTR_ENTI = 'MAE_ESTAT_CLIEN'
                         AND VAL_OID = ADI.ESTA_OID_ESTA_CLIE) ESTATUS
                 FROM PED_SOLIC_CABEC PSC,
                   PED_SOLIC_CABEC B,
                   PED_SOLIC_POSIC PSP,
                   MAE_CLIEN CLI,
                   MAE_PRODU MP,
                   (SELECT * FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                    MAV_PARAM_CONFI CONFI,
                    MAV_ACTIV_TIPO_OFERT ACTIV,
                    GEN_I18N_SICC_PAIS G,
                    (SELECT * FROM GEN_I18N_SICC_COMUN
                      WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                     ZON_SECCI SECC,
                     ZON_TERRI TERR,
                     ZON_TERRI_ADMIN ZTAD,
                        MAV_ENVIO_CONFI ENVI,
                     MAV_SOLIC_ENVIO_CONFI SOLEN
                    WHERE 1 = 1
                    AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
                    AND PSC.PERD_OID_PERI = lnOidPeriodo
                    AND PSC.CLIE_OID_CLIE= CLI.OID_CLIE
                    AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                  AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
                  AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                  AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                  AND B.ESSO_OID_ESTA_SOLI <> 4
                  AND PSP.ESPO_OID_ESTA_POSI <> 2
                  AND PSP.PROD_OID_PROD = MP.OID_PROD
                  AND PSP.PROD_OID_PROD = NP.VAL_OID
                  AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                  AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
                  AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                  AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                  AND G.ATTR_ENTI = 'MAV_ACTIV'
                  AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                  AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                  AND CONFI.COD_ESTA_MAV IN (3,4,5)
                  AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                  AND PSC.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
                  AND ZTAD.ZSCC_OID_SECC = SECC.OID_SECC
                  AND ZTAD.TERR_OID_TERR = TERR.OID_TERR
                  AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
                  AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
                  AND TRUNC(PSC.FEC_FACT) >= to_date(psFechaInicio, 'DD/MM/YYYY')
                  AND TRUNC(PSC.FEC_FACT) <= to_date(psFechaFin, 'DD/MM/YYYY');
            ELSE
             INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                    oid_soli_posi,
                cam_para_mav,
                fec_fact,
                cod_regi,
                cod_zona,
                cod_acti,
                des_acti,
                cod_tipo_ofer,
                des_tipo_ofer,
                cod_clie,
                des_clie,
                des_tele,
                des_movi,
                cod_sap,
                des_prod,
                val_codi_vent,
                num_unid_dema_real,
                num_unid_aten,
                num_unid_falt,
                cod_clie_ante,
                COD_SECC,
                COD_TERR,
                NUM_UNID_POR_ATEN,
                VAL_PREC_UNIT,
                VAL_PORC_DESC,
                VAL_NUME_SOLI,
                VAL_ESTA
              )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                   TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_ENVIO,
                   ZR.COD_REGI REGION,
                   ZN.COD_ZONA ZONA,
                   CONFI.COD_ACTI CODACTIVIDAD,
                   G.VAL_I18N ACTIVIDAD,
                   CONFI.COD_TIPO_OFER CODTOFERTA,
                   G1.VAL_I18N TIPOOFERTA,
                   CLI.COD_CLIE CODIGOCLIE,
                   TRIM(CLI.VAL_APE1)  ||' ' ||  TRIM(CLI.VAL_APE2)   ||' ' ||  TRIM(CLI.VAL_NOM1)  ||' ' ||  TRIM(CLI.VAL_NOM2) CONSULTORA,
            (SELECT MCC.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC.TICM_OID_TIPO_COMU = 1 ) TELEFONO,
            (SELECT MCC1.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC1  WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC1.TICM_OID_TIPO_COMU = 6 ) MOVIL,
                   MP.COD_SAP CODIGOSAP,
                   NP.VAL_I18N DESCPRODUCTO,
                   CONFI.Val_Codi_Vent CUV,
                   PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                   PSP.NUM_UNID_ATEN UNIATENDIDA,
                   (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                   (select num_docu_iden from mae_clien_ident  where val_iden_docu_prin=1 and clie_oid_clie = cli.oid_clie) documento_identidad,
                   SECC.COD_SECC,
                     TERR.COD_TERR,
                     PSP.NUM_UNID_POR_ATEN,
                     PSP.VAL_PREC_FACT_UNIT_LOCA,
                     PSP.VAL_PORC_DESC, 
                     PSC.VAL_NUME_SOLI,
                     (SELECT VAL_I18N 
                        FROM MAE_CLIEN_DATOS_ADICI ADI, GEN_I18N_SICC_COMUN
                       WHERE CLIE_OID_CLIE = PSC.CLIE_OID_CLIE 
                         AND ATTR_ENTI = 'MAE_ESTAT_CLIEN'
                         AND VAL_OID = ADI.ESTA_OID_ESTA_CLIE) ESTATUS
             FROM PED_SOLIC_CABEC PSC,
               PED_SOLIC_CABEC B,
               PED_SOLIC_POSIC PSP,
               MAE_CLIEN CLI,
               MAE_PRODU MP,
               (SELECT * FROM GEN_I18N_SICC_PAIS
                 WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                MAV_PARAM_CONFI CONFI,
                MAV_ACTIV_TIPO_OFERT ACTIV,
                GEN_I18N_SICC_PAIS G,
                (SELECT * FROM GEN_I18N_SICC_COMUN
                  WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                 ZON_ZONA ZN,
                     ZON_REGIO ZR,
                 ZON_SECCI SECC,
                 ZON_TERRI TERR,
                 ZON_TERRI_ADMIN ZTAD,
           MAV_ENVIO_CONFI ENVI,
           MAV_SOLIC_ENVIO_CONFI SOLEN
            WHERE 1 = 1
            AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
            AND PSC.PERD_OID_PERI = lnOidPeriodo
              AND PSC.CLIE_OID_CLIE= CLI.OID_CLIE
            AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                  AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
              AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
              AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
              AND B.ESSO_OID_ESTA_SOLI <> 4
              AND PSP.ESPO_OID_ESTA_POSI <> 2
              AND PSP.PROD_OID_PROD = MP.OID_PROD
              AND PSP.PROD_OID_PROD = NP.VAL_OID
              AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
              AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
              AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
              AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
              AND G.ATTR_ENTI = 'MAV_ACTIV'
              AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
              AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
              AND CONFI.COD_ESTA_MAV IN (3,4,5)
              AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
              AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
              AND ZN.ZORG_OID_REGI = ZR.OID_REGI
              AND PSC.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
              AND ZTAD.ZSCC_OID_SECC = SECC.OID_SECC
              AND ZTAD.TERR_OID_TERR = TERR.OID_TERR
                  AND ((lsFlagRegionesZonas = '1' AND ZN.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
                  AND TRUNC(PSC.FEC_FACT) >= to_date(psFechaInicio, 'DD/MM/YYYY')
                  AND TRUNC(PSC.FEC_FACT) <= to_date(psFechaFin, 'DD/MM/YYYY');

            END IF;

          ELSE /** 4 **/
            -- DETALLE GERENTES SIN FECHA
            IF lsFlagTipoCargo = 'S' THEN
                 INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                    oid_soli_posi,
                    cam_para_mav,
                    fec_fact,
                    cod_regi,
                    cod_zona,
                    cod_acti,
                    des_acti,
                    cod_tipo_ofer,
                    des_tipo_ofer,
                    cod_clie,
                    des_clie,
                    des_tele,
                    des_movi,
                    cod_sap,
                    des_prod,
                    val_codi_vent,
                    num_unid_dema_real,
                    num_unid_aten,
                    num_unid_falt,
                    cod_clie_ante,
                    COD_TIPO_CARG,
                    COD_SECC,
                    COD_TERR,
                    NUM_UNID_POR_ATEN,
                    VAL_PREC_UNIT,
                    VAL_PORC_DESC,
                    VAL_NUME_SOLI,
                    VAL_ESTA
                  )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                       TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_ENVIO,
                       ENVI.COD_REGI,
                       ENVI.COD_ZONA,
                       CONFI.COD_ACTI CODACTIVIDAD,
                       G.VAL_I18N ACTIVIDAD,
                       CONFI.COD_TIPO_OFER CODTOFERTA,
                       G1.VAL_I18N TIPOOFERTA,
                       CLI.COD_CLIE CODIGOCLIE,
                       TRIM(CLI.VAL_APE1)  ||' ' ||  TRIM(CLI.VAL_APE2)   ||' ' ||  TRIM(CLI.VAL_NOM1)  ||' ' ||  TRIM(CLI.VAL_NOM2) CONSULTORA,
                       (SELECT MCC.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC.TICM_OID_TIPO_COMU = 1 ) TELEFONO,
                       (SELECT MCC1.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC1  WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC1.TICM_OID_TIPO_COMU = 6 ) MOVIL,
                       MP.COD_SAP CODIGOSAP,
                       NP.VAL_I18N DESCPRODUCTO,
                       CONFI.Val_Codi_Vent CUV,
                       PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                       PSP.NUM_UNID_ATEN UNIATENDIDA,
                       (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                       (select num_docu_iden from mae_clien_ident  where val_iden_docu_prin=1 and clie_oid_clie = cli.oid_clie) documento_identidad,
                       ENVI.TIP_CONS_DESP,
                       SECC.COD_SECC,
                     TERR.COD_TERR,
                     PSP.NUM_UNID_POR_ATEN,
                     PSP.VAL_PREC_FACT_UNIT_LOCA,
                     PSP.VAL_PORC_DESC, 
                     PSC.VAL_NUME_SOLI,
                     (SELECT VAL_I18N 
                        FROM MAE_CLIEN_DATOS_ADICI ADI, GEN_I18N_SICC_COMUN
                       WHERE CLIE_OID_CLIE = PSC.CLIE_OID_CLIE 
                         AND ATTR_ENTI = 'MAE_ESTAT_CLIEN'
                         AND VAL_OID = ADI.ESTA_OID_ESTA_CLIE) ESTATUS
                 FROM PED_SOLIC_CABEC PSC,
                   PED_SOLIC_CABEC B,
                   PED_SOLIC_POSIC PSP,
                   MAE_CLIEN CLI,
                   MAE_PRODU MP,
                   (SELECT * FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                    MAV_PARAM_CONFI CONFI,
                    MAV_ACTIV_TIPO_OFERT ACTIV,
                    GEN_I18N_SICC_PAIS G,
                    (SELECT * FROM GEN_I18N_SICC_COMUN
                      WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                    ZON_SECCI SECC,
                    ZON_TERRI TERR,
                    ZON_TERRI_ADMIN ZTAD,
           MAV_ENVIO_CONFI ENVI,
           MAV_SOLIC_ENVIO_CONFI SOLEN
     WHERE 1 = 1
            AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
            AND PSC.PERD_OID_PERI = lnOidPeriodo
            AND PSC.CLIE_OID_CLIE= CLI.OID_CLIE
            AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                  AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
                  AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
                  AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
                  AND B.ESSO_OID_ESTA_SOLI <> 4
                  AND PSP.ESPO_OID_ESTA_POSI <> 2
                  AND PSP.PROD_OID_PROD = MP.OID_PROD
                  AND PSP.PROD_OID_PROD = NP.VAL_OID
                  AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                  AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
                  AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
                  AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                  AND G.ATTR_ENTI = 'MAV_ACTIV'
                  AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                  AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                  AND CONFI.COD_ESTA_MAV IN (3,4,5)
                  AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
                  AND PSC.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
                  AND ZTAD.ZSCC_OID_SECC = SECC.OID_SECC
                  AND ZTAD.TERR_OID_TERR = TERR.OID_TERR
                  AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
                  AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0');
            ELSE
            INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                    oid_soli_posi,
                cam_para_mav,
                fec_fact,
                cod_regi,
                cod_zona,
                cod_acti,
                des_acti,
                cod_tipo_ofer,
                des_tipo_ofer,
                cod_clie,
                des_clie,
                des_tele,
                des_movi,
                cod_sap,
                des_prod,
                val_codi_vent,
                num_unid_dema_real,
                num_unid_aten,
                num_unid_falt,
                cod_clie_ante,
                COD_SECC,
                COD_TERR,
                NUM_UNID_POR_ATEN,
                VAL_PREC_UNIT,
                VAL_PORC_DESC,
                VAL_NUME_SOLI,
                VAL_ESTA
              )
                SELECT DISTINCT PSP.OID_SOLI_POSI, CONFI.CAM_PARA_MAV CAMPANA,
                   TO_CHAR(PSC.FEC_FACT, 'dd/mm/yyyy') FECHA_ENVIO,
                   ZR.COD_REGI REGION,
                   ZN.COD_ZONA ZONA,
                   CONFI.COD_ACTI CODACTIVIDAD,
                   G.VAL_I18N ACTIVIDAD,
                   CONFI.COD_TIPO_OFER CODTOFERTA,
                   G1.VAL_I18N TIPOOFERTA,
                   CLI.COD_CLIE CODIGOCLIE,
                   TRIM(CLI.VAL_APE1)  ||' ' ||  TRIM(CLI.VAL_APE2)   ||' ' ||  TRIM(CLI.VAL_NOM1)  ||' ' ||  TRIM(CLI.VAL_NOM2) CONSULTORA,
            (SELECT MCC.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC.TICM_OID_TIPO_COMU = 1 ) TELEFONO,
            (SELECT MCC1.VAL_TEXT_COMU FROM MAE_CLIEN_COMUN MCC1  WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE AND MCC1.TICM_OID_TIPO_COMU = 6 ) MOVIL,
                   MP.COD_SAP CODIGOSAP,
                   NP.VAL_I18N DESCPRODUCTO,
                   CONFI.Val_Codi_Vent CUV,
                   PSP.NUM_UNID_DEMA_REAL UNIDEMANDA,
                   PSP.NUM_UNID_ATEN UNIATENDIDA,
                   (PSP.NUM_UNID_DEMA_REAL - PSP.NUM_UNID_ATEN) UNIFALTANTE,
                   (select num_docu_iden from mae_clien_ident  where val_iden_docu_prin=1 and clie_oid_clie = cli.oid_clie) documento_identidad,
                   SECC.COD_SECC,
                     TERR.COD_TERR,
                     PSP.NUM_UNID_POR_ATEN,
                     PSP.VAL_PREC_FACT_UNIT_LOCA,
                     PSP.VAL_PORC_DESC, 
                     PSC.VAL_NUME_SOLI,
                     (SELECT VAL_I18N 
                        FROM MAE_CLIEN_DATOS_ADICI ADI, GEN_I18N_SICC_COMUN
                       WHERE CLIE_OID_CLIE = PSC.CLIE_OID_CLIE 
                         AND ATTR_ENTI = 'MAE_ESTAT_CLIEN'
                         AND VAL_OID = ADI.ESTA_OID_ESTA_CLIE) ESTATUS
             FROM PED_SOLIC_CABEC PSC,
               PED_SOLIC_CABEC B,
               PED_SOLIC_POSIC PSP,
               MAE_CLIEN CLI,
               MAE_PRODU MP,
               (SELECT * FROM GEN_I18N_SICC_PAIS
                 WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                MAV_PARAM_CONFI CONFI,
                MAV_ACTIV_TIPO_OFERT ACTIV,
                GEN_I18N_SICC_PAIS G,
                (SELECT * FROM GEN_I18N_SICC_COMUN
                  WHERE  ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                 ZON_ZONA ZN,
                     ZON_REGIO ZR,
                 ZON_SECCI SECC,
                 ZON_TERRI TERR,
                 ZON_TERRI_ADMIN ZTAD,
           MAV_ENVIO_CONFI ENVI,
           MAV_SOLIC_ENVIO_CONFI SOLEN
            WHERE 1 = 1
            AND PSC.OID_SOLI_CABE = PSP.SOCA_OID_SOLI_CABE
            AND PSC.PERD_OID_PERI = lnOidPeriodo
              AND PSC.CLIE_OID_CLIE= CLI.OID_CLIE
            AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                  AND PSC.TSPA_OID_TIPO_SOLI_PAIS IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'S')
              AND B.PERD_OID_PERI = PSC.PERD_OID_PERI
              AND PSC.SOCA_OID_SOLI_CABE = B.OID_SOLI_CABE
              AND B.ESSO_OID_ESTA_SOLI <> 4
              AND PSP.ESPO_OID_ESTA_POSI <> 2
              AND PSP.PROD_OID_PROD = MP.OID_PROD
              AND PSP.PROD_OID_PROD = NP.VAL_OID
              AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
              AND PSP.PROD_OID_PROD = CONFI.PROD_OID_PROD
                  AND CONFI.COD_SAP =  MP.COD_SAP
              AND CONFI.CAM_PARA_MAV = pscodigoPeriodo
              AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
              AND G.ATTR_ENTI = 'MAV_ACTIV'
              AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                  AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
              AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
              AND CONFI.COD_ESTA_MAV IN (3,4,5)
              AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                  AND PSC.OID_SOLI_CABE = SOLEN.SOCA_OID_SOLI_CABE
                  AND SOLEN.MENV_OID_ENVI = ENVI.OID_ENVI
                  AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                  AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                  --
                  AND PSP.VAL_CODI_VENT = CONFI.VAL_CODI_VENT
                  --
              AND PSC.ZZON_OID_ZONA = ZN.OID_ZONA
              AND ZN.ZORG_OID_REGI = ZR.OID_REGI
              AND PSC.ZTAD_OID_TERR_ADMI = ZTAD.OID_TERR_ADMI
              AND ZTAD.ZSCC_OID_SECC = SECC.OID_SECC
              AND ZTAD.TERR_OID_TERR = TERR.OID_TERR
                  AND ((lsFlagRegionesZonas = '1' AND ZN.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0');

            END IF;

          END IF;
       END IF;

     END IF;
  ELSIF pscodigoTipoReporte = '2' THEN
    --REPORTE DE ENVIOS
    IF psflagRangoFechas = 'on' THEN
        --REPORTE CONSIDERANDO FECHAS
        IF lsFlagCapacitadora = 'S' THEN
            --REPORTE COLOMBIA CON FECHAS
            INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                COR_PARA_CONF,
                IND_ENVI,
                CAM_PARA_MAV,
                FEC_FACT,
                COD_TIPO_CARG,
                COD_REGI,
                COD_ZONA,
                COD_ACTI,
                DES_ACTI,
                COD_TIPO_OFER,
                DES_TIPO_OFER,
                COD_CLIE,
                DES_CLIE,
                DES_TELE,
                DES_MOVI,
                COD_SAP,
                DES_PROD,
                VAL_CODI_VENT,
                NUM_UNID_DEMA_REAL
            )
            SELECT
                  CONFI.COR_PARA_CONF,
                  ENVI.IND_ENVI,
                  CONFI.CAM_PARA_MAV,
                  TO_CHAR(ENVI.FEC_CREA, 'DD/MM/YYYY') FEC_CREA,
                  ENVI.TIP_CONS_DESP,
                  ENVI.COD_REGI,
                  ENVI.COD_ZONA,
                  CONFI.COD_ACTI,
                  G.VAL_I18N DES_ACTI,
                  CONFI.COD_TIPO_OFER,
                  G1.VAL_I18N DES_TIPO_OFER,
                  CLI.COD_CLIE,
                  CLI.VAL_APE1 || ' ' || CLI.VAL_APE2 || ' ' || CLI.VAL_NOM1 || ' ' || CLI.VAL_NOM2 DES_CLIE,
                  (SELECT MCC.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC
                   WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC.TICM_OID_TIPO_COMU = 1) DES_TELE,
                  (SELECT MCC1.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC1
                   WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC1.TICM_OID_TIPO_COMU = 6) DES_MOVI,
                  MP.COD_SAP,
                  NP.VAL_I18N DES_PROD,
                  CONFI.VAL_CODI_VENT,
                  ENVI.NUM_UNID
            FROM
                   MAE_CLIEN CLI,
                   MAE_PRODU MP,
                   (SELECT *
                      FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                   MAV_PARAM_CONFI CONFI,
                   MAV_ACTIV_TIPO_OFERT ACTIV,
                   GEN_I18N_SICC_PAIS G,
                   (SELECT *
                      FROM GEN_I18N_SICC_COMUN
                     WHERE ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                   MAV_ENVIO_CONFI ENVI
             WHERE 1 = 1
                AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                AND (psIndicadorEnvio IS NULL OR ENVI.IND_ENVI = psIndicadorEnvio)
                AND CONFI.PROD_OID_PROD = MP.OID_PROD
                AND CONFI.PROD_OID_PROD = NP.VAL_OID
                AND CONFI.CAM_PARA_MAV = psCodigoPeriodo
                AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                AND G.ATTR_ENTI = 'MAV_ACTIV'
                AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                AND CONFI.COD_ESTA_MAV IN (3, 4, 5) --INICIADO/GENERADO
                AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                AND  ENVI.CLIE_OID_CLIE = CLI.OID_CLIE
                AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
                AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
                AND TRUNC(ENVI.FEC_CREA) >= to_date(psFechaInicio, 'DD/MM/YYYY')
                AND TRUNC(ENVI.FEC_CREA) <= to_date(psFechaFin, 'DD/MM/YYYY');
            -- --
        ELSE
            --REPORTE OTROS PAISES CON FECHAS
            INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                COR_PARA_CONF,
                IND_ENVI,
                CAM_PARA_MAV,
                FEC_FACT,
                COD_TIPO_CARG,
                COD_REGI,
                COD_ZONA,
                COD_ACTI,
                DES_ACTI,
                COD_TIPO_OFER,
                DES_TIPO_OFER,
                COD_CLIE,
                DES_CLIE,
                DES_TELE,
                DES_MOVI,
                COD_SAP,
                DES_PROD,
                VAL_CODI_VENT,
                NUM_UNID_DEMA_REAL
            )
            SELECT
                  CONFI.COR_PARA_CONF,
                  ENVI.IND_ENVI,
                  CONFI.CAM_PARA_MAV,
                  TO_CHAR(ENVI.FEC_CREA, 'DD/MM/YYYY') FEC_CREA,
                  ENVI.TIP_CONS_DESP,
                  ENVI.COD_REGI,
                  ENVI.COD_ZONA,
                  CONFI.COD_ACTI,
                  G.VAL_I18N DES_aCTI,
                  CONFI.COD_TIPO_OFER,
                  G1.VAL_I18N DES_TIPO_OFER,
                  CLI.COD_CLIE,
                  CLI.VAL_APE1 || ' ' || CLI.VAL_APE2 || ' ' || CLI.VAL_NOM1 || ' ' ||CLI.VAL_NOM2 DES_CLIE,
                  (SELECT MCC.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC
                   WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC.TICM_OID_TIPO_COMU = 1) DES_TELE,
                  (SELECT MCC1.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC1
                   WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC1.TICM_OID_TIPO_COMU = 6) DES_MOVI,
                  MP.COD_SAP,
                  NP.VAL_I18N DES_PROD,
                  CONFI.VAL_CODI_VENT,
                  ENVI.NUM_UNID
            FROM
                   MAE_CLIEN CLI,
                   MAE_PRODU MP,
                   (SELECT *
                      FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                   MAV_PARAM_CONFI CONFI,
                   MAV_ACTIV_TIPO_OFERT ACTIV,
                   GEN_I18N_SICC_PAIS G,
                   (SELECT *
                      FROM GEN_I18N_SICC_COMUN
                     WHERE ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                   MAV_ENVIO_CONFI ENVI
                WHERE 1 = 1
                AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
               AND (psIndicadorEnvio IS NULL OR ENVI.IND_ENVI = psIndicadorEnvio)
               AND CONFI.PROD_OID_PROD = MP.OID_PROD
               AND CONFI.PROD_OID_PROD = NP.VAL_OID
               AND CONFI.CAM_PARA_MAV = psCodigoPeriodo
               AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
               AND G.ATTR_ENTI = 'MAV_ACTIV'
               AND CONFI.ATOF_OID_TIPO_OFER_ACTI = ACTIV.OID_TIPO_OFER_ACTI
               AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
               AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
               AND CONFI.COD_ESTA_MAV IN (3, 4, 5) --INICIADO/GENERADO
               AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
               AND  ENVI.CLIE_OID_CLIE = CLI.OID_CLIE
               AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
               AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
               AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
               AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
               AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0')
               AND TRUNC(ENVI.FEC_CREA) >= to_date(psFechaInicio, 'DD/MM/YYYY')
               AND TRUNC(ENVI.FEC_CREA) <= to_date(psFechaFin, 'DD/MM/YYYY');
            -- --
        END IF;
        -- --
  ELSE
        --REPORTE SIN FECHAS
        IF lsFlagCapacitadora = 'S' THEN
            --REPORTE COLOMBIA SIN FECHAS
            INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                COR_PARA_CONF,
                IND_ENVI,
                CAM_PARA_MAV,
                FEC_FACT,
                COD_TIPO_CARG,
                COD_REGI,
                COD_ZONA,
                COD_ACTI,
                DES_ACTI,
                COD_TIPO_OFER,
                DES_TIPO_OFER,
                COD_CLIE,
                DES_CLIE,
                DES_TELE,
                DES_MOVI,
                COD_SAP,
                DES_PROD,
                VAL_CODI_VENT,
                NUM_UNID_DEMA_REAL
            )
            SELECT
                  CONFI.COR_PARA_CONF,
                  ENVI.IND_ENVI,
                  CONFI.CAM_PARA_MAV,
                  TO_CHAR(ENVI.FEC_CREA, 'DD/MM/YYYY') FEC_CREA,
                  ENVI.TIP_CONS_DESP,
                  ENVI.COD_REGI,
                  ENVI.COD_ZONA,
                  CONFI.COD_ACTI,
                  G.VAL_I18N DES_ACTI,
                  CONFI.COD_TIPO_OFER,
                  G1.VAL_I18N DES_TIPO_OFER,
                  CLI.COD_CLIE,
                  CLI.VAL_APE1 || ' ' || CLI.VAL_APE2 || ' ' || CLI.VAL_NOM1 || ' ' || CLI.VAL_NOM2 DES_CLIE,
                  (SELECT MCC.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC
                   WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC.TICM_OID_TIPO_COMU = 1) DES_TELE,
                  (SELECT MCC1.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC1
                   WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC1.TICM_OID_TIPO_COMU = 6) DES_MOVI,
                  MP.COD_SAP,
                  NP.VAL_I18N DES_PROD,
                  CONFI.VAL_CODI_VENT,
                  ENVI.NUM_UNID
            FROM
                   MAE_CLIEN CLI,
                   MAE_PRODU MP,
                   (SELECT *
                      FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                   MAV_PARAM_CONFI CONFI,
                   MAV_ACTIV_TIPO_OFERT ACTIV,
                   GEN_I18N_SICC_PAIS G,
                   (SELECT *
                      FROM GEN_I18N_SICC_COMUN
                     WHERE ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                   MAV_ENVIO_CONFI ENVI
             WHERE 1 = 1
                AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
                AND (psIndicadorEnvio IS NULL OR ENVI.IND_ENVI = psIndicadorEnvio)
                AND CONFI.PROD_OID_PROD = MP.OID_PROD
                AND CONFI.PROD_OID_PROD = NP.VAL_OID
                AND CONFI.CAM_PARA_MAV = psCodigoPeriodo
                AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
                AND G.ATTR_ENTI = 'MAV_ACTIV'
                AND CONFI.ATOF_OID_TIPO_OFER_ACTI = activ.oid_tipo_ofer_acti
                AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
                AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
                AND CONFI.COD_ESTA_MAV IN (3, 4, 5) --INICIADO/GENERADO
                AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
                AND  ENVI.CLIE_OID_CLIE = CLI.OID_CLIE
                AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
                AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
                AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
                AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
                AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0');
            -- --
        ELSE
            --REPORTE OTROS PAISES SIN FECHAS
            INSERT INTO MAV_REPOR_CAMPA_CONSU_DETAL(
                COR_PARA_CONF,
                IND_ENVI,
                CAM_PARA_MAV,
                FEC_FACT,
                COD_TIPO_CARG,
                COD_REGI,
                COD_ZONA,
                COD_ACTI,
                DES_ACTI,
                COD_TIPO_OFER,
                DES_TIPO_OFER,
                COD_CLIE,
                DES_CLIE,
                DES_TELE,
                DES_MOVI,
                COD_SAP,
                DES_PROD,
                VAL_CODI_VENT,
                NUM_UNID_DEMA_REAL
            )
            SELECT
                  CONFI.COR_PARA_CONF,
                  ENVI.IND_ENVI,
                  CONFI.CAM_PARA_MAV,
                  TO_CHAR(ENVI.FEC_CREA, 'DD/MM/YYYY') FEC_CREA,
                  ENVI.TIP_CONS_DESP,
                  ENVI.COD_REGI,
                  ENVI.COD_ZONA,
                  CONFI.COD_ACTI,
                  G.VAL_I18N DES_aCTI,
                  CONFI.COD_TIPO_OFER,
                  G1.VAL_I18N DES_TIPO_OFER,
                  CLI.COD_CLIE,
                  CLI.VAL_APE1 || ' ' || CLI.VAL_APE2 || ' ' || CLI.VAL_NOM1 || ' ' ||CLI.VAL_NOM2 DES_CLIE,
                  (SELECT MCC.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC
                   WHERE MCC.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC.TICM_OID_TIPO_COMU = 1) DES_TELE,
                  (SELECT MCC1.VAL_TEXT_COMU
                    FROM MAE_CLIEN_COMUN MCC1
                   WHERE MCC1.CLIE_OID_CLIE = CLI.OID_CLIE
                     AND MCC1.TICM_OID_TIPO_COMU = 6) DES_MOVI,
                  MP.COD_SAP,
                  NP.VAL_I18N DES_PROD,
                  CONFI.VAL_CODI_VENT,
                  ENVI.NUM_UNID
            FROM
                   MAE_CLIEN CLI,
                   MAE_PRODU MP,
                   (SELECT *
                      FROM GEN_I18N_SICC_PAIS
                     WHERE ATTR_ENTI = 'MAE_PRODU') NP,
                   MAV_PARAM_CONFI CONFI,
                   MAV_ACTIV_TIPO_OFERT ACTIV,
                   GEN_I18N_SICC_PAIS G,
                   (SELECT *
                      FROM GEN_I18N_SICC_COMUN
                     WHERE ATTR_ENTI = 'PRE_TIPO_OFERT') G1,
                   MAV_ENVIO_CONFI ENVI
                WHERE 1 = 1
                AND (psCodigoActividad IS NULL OR CONFI.COD_ACTI = psCodigoActividad)
               AND (psIndicadorEnvio IS NULL OR ENVI.IND_ENVI = psIndicadorEnvio)
               AND CONFI.PROD_OID_PROD = MP.OID_PROD
               AND CONFI.PROD_OID_PROD = NP.VAL_OID
               AND CONFI.CAM_PARA_MAV = psCodigoPeriodo
               AND G.VAL_OID = CONFI.ATOF_ACTI_OID_ACTI
               AND G.ATTR_ENTI = 'MAV_ACTIV'
               AND CONFI.ATOF_OID_TIPO_OFER_ACTI = ACTIV.OID_TIPO_OFER_ACTI
               AND (pscodigoTipoOferta IS NULL OR ACTIV.TOFE_OID_TIPO_OFER IN (SELECT A.OID_TIPO_OFER FROM PRE_TIPO_OFERT A WHERE A.COD_TIPO_OFER = pscodigoTipoOferta))
               AND ACTIV.TOFE_OID_TIPO_OFER = G1.VAL_OID
               AND CONFI.COD_ESTA_MAV IN (3, 4, 5) --INICIADO/GENERADO
               AND CONFI.TICL_OID_TIPO_CLIE = pscodigoTipoMav
               AND  ENVI.CLIE_OID_CLIE = CLI.OID_CLIE
               AND ENVI.PAIS_COD_PAIS = CONFI.PAIS_COD_PAIS
               AND ENVI.COR_PARA_CONF = CONFI.COR_PARA_CONF
               AND ((lsFlagCodigoSAP = '1' AND MP.COD_SAP IN (SELECT COD_SAP FROM MAV_REPOR_ATENC_SAP_TEMPO)) OR lsFlagCodigoSAP = '0')
               AND (psCodigoTipoCargo IS NULL OR ENVI.TIP_CONS_DESP = DECODE(psCodigoTipoCargo, 'MVR', 'AR', 'MVZ', 'AZ', 'EE', 'CA', psCodigoTipoCargo))
               AND ((lsFlagRegionesZonas = '1' AND ENVI.COD_ZONA IN(SELECT VAL_CODI FROM MAE_GTT_REZOC WHERE VAL_TIPO = 'R')) OR lsFlagRegionesZonas='0');
            -- --
        END IF;
        -- --
    END IF;

  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_REPOR_ATENC_CAMPA: ' || ls_sqlerrm);

END MAV_PR_REPOR_ATENC_CAMPA;

/**************************************************************************
Descripcion       : Proceso que realiza la actualización de los envíos del MAV en base a los movimientos del DV
Fecha Creacion    : 11/09/2013
Autor             : Ivan Tocto
***************************************************************************/
PROCEDURE MAV_PR_ACTUA_ENVIO_MASIV(
    psCodigoUsuario       VARCHAR2
) IS

    CURSOR c_envios is
    SELECT
    ME.OID_ENVI,
    ME.TIP_CONS_DESP,
    ME.COD_REGI,
    ME.COD_ZONA,
    ME.CLIE_OID_CLIE,
    NVL((SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE =ME.CLIE_OID_CLIE ), ' ') COD_CLI_ENV,
    DECODE(
        ME.TIP_CONS_DESP,
            'GR', (
                    SELECT (SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE = ZR.CLIE_OID_CLIE)
                    FROM ZON_REGIO ZR
                    WHERE ZR.IND_ACTI = 1
                    AND ZR.IND_BORR = 0
                    AND ZR.COD_REGI =  ME.COD_REGI
                    AND ROWNUM =1),
            'GZ', (
                    SELECT (SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE = ZZ.CLIE_OID_CLIE)
                    FROM ZON_ZONA ZZ
                    WHERE ZZ.IND_ACTI = 1
                    AND ZZ.IND_BORR = 0
                    AND ZZ.COD_ZONA =  ME.COD_ZONA
                    AND ROWNUM = 1),
            'AR',  (
                    SELECT CA.COD_CLIE
                    FROM ZON_DIREC_VENTA_CABEC CA,  ZON_DIREC_VENTA_DETAL DE, ZON_TIPO_CARGO CG
                    WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                    AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                    AND CA.COD_CLIE = DE.COD_CLIE
                    AND CA.FEC_REGI = DE.DICA_FEC_REGI
                    AND CA.CAM_PROC = DE.DICA_CAM_PROC
                    AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                    AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                    AND CA.EST_REGI = '1'
                    AND DE.EST_REGI = '1'
                    AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                    AND CG.EST_REGI = '1'
                    AND CG.COD_TIPO_CARG = 'MVR'
                    AND CA.ESCA_COD_ESTA_CARG = 'A'
                    AND DE.COD_SUBG = '01'
                    AND DE.COD_REGI =  ME.COD_REGI
                    AND ROWNUM = 1),
            'AZ', (
                    SELECT CA.COD_CLIE
                    FROM ZON_DIREC_VENTA_CABEC CA, ZON_DIREC_VENTA_DETAL DE, ZON_TIPO_CARGO CG
                    WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                    AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                    AND CA.COD_CLIE = DE.COD_CLIE
                    AND CA.FEC_REGI = DE.DICA_FEC_REGI
                    AND CA.CAM_PROC = DE.DICA_CAM_PROC
                    AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                    AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                    AND CA.EST_REGI = '1'
                    AND DE.EST_REGI = '1'
                    AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                    AND CG.EST_REGI = '1'
                    AND CG.COD_TIPO_CARG = 'MVZ'
                    AND CA.ESCA_COD_ESTA_CARG = 'A'
                    AND DE.COD_SUBG = '01'
                    AND DE.COD_ZONA = ME.COD_ZONA
                    AND ROWNUM = 1),
            'CA', (
                    SELECT CA.COD_CLIE
                    FROM ZON_DIREC_VENTA_CABEC CA, ZON_DIREC_VENTA_DETAL DE, ZON_TIPO_CARGO CG
                    WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                    AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                    AND CA.COD_CLIE = DE.COD_CLIE
                    AND CA.FEC_REGI = DE.DICA_FEC_REGI
                    AND CA.CAM_PROC = DE.DICA_CAM_PROC
                    AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                    AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                    AND CA.EST_REGI = '1'
                    AND DE.EST_REGI = '1'
                    AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                    AND CG.EST_REGI = '1'
                    AND CG.COD_TIPO_CARG = 'EE'
                    AND CA.ESCA_COD_ESTA_CARG = 'A'
                    AND DE.COD_SUBG = '01'
                    AND DE.COD_REGI = ME.COD_REGI
                    AND ROWNUM = 1)
         ) COD_CLIE_DV,
    DECODE(
        ME.TIP_CONS_DESP,
             'AR', 'GR-'||(
                                SELECT (SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE = ZR.CLIE_OID_CLIE)
                                FROM ZON_REGIO ZR
                                WHERE ZR.IND_ACTI = 1
                                AND ZR.IND_BORR = 0
                                AND ZR.COD_REGI =  ME.COD_REGI
                                AND ROWNUM = 1),
              'AZ', 'GZ-'||(
                                SELECT (SELECT CL.COD_CLIE FROM MAE_CLIEN CL WHERE CL.OID_CLIE = ZZ.CLIE_OID_CLIE)
                                FROM ZON_ZONA ZZ
                                WHERE ZZ.IND_ACTI = 1
                                AND ZZ.IND_BORR = 0
                                AND ZZ.COD_ZONA =  ME.COD_ZONA
                                AND ROWNUM = 1),
              'GR', 'AR-'||(
                                SELECT CA.COD_CLIE
                                FROM ZON_DIREC_VENTA_CABEC CA, ZON_DIREC_VENTA_DETAL DE, ZON_TIPO_CARGO CG
                                WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                                AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                                AND CA.COD_CLIE = DE.COD_CLIE
                                AND CA.FEC_REGI = DE.DICA_FEC_REGI
                                AND CA.CAM_PROC = DE.DICA_CAM_PROC
                                AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                                AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                                AND CA.EST_REGI = '1'
                                AND DE.EST_REGI = '1'
                                AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                                AND CG.EST_REGI = '1'
                                AND CG.COD_TIPO_CARG = 'MVR'
                                AND CA.ESCA_COD_ESTA_CARG = 'A'
                                AND DE.COD_SUBG = '01'
                                AND DE.COD_REGI =  ME.COD_REGI
                                AND ROWNUM = 1),
               'GZ', 'AZ-'||(
                                SELECT CA.COD_CLIE
                                FROM ZON_DIREC_VENTA_CABEC CA, ZON_DIREC_VENTA_DETAL DE, ZON_TIPO_CARGO CG
                                WHERE CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
                                AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
                                AND CA.COD_CLIE = DE.COD_CLIE
                                AND CA.FEC_REGI = DE.DICA_FEC_REGI
                                AND CA.CAM_PROC = DE.DICA_CAM_PROC
                                AND CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
                                AND CA.COR_DIRE_VENT = DE.DICA_COR_DIRE_VENT
                                AND CA.EST_REGI = '1'
                                AND DE.EST_REGI = '1'
                                AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
                                AND CG.EST_REGI = '1'
                                AND CG.COD_TIPO_CARG = 'MVZ'
                                AND CA.ESCA_COD_ESTA_CARG = 'A'
                                AND DE.COD_SUBG = '01'
                                AND DE.COD_ZONA = ME.COD_ZONA
                                AND ROWNUM = 1)
         ) COD_CLIE_OPUEST_DV
    FROM MAV_ENVIO_CONFI ME
    WHERE ME.IND_ENVI = 'P'
    AND ME.TIP_CONS_DESP != 'CO'
    AND ME.IND_ACTI = 1
    AND ME.EST_REGI = 1
    --CAMPAÑA ACTUAL
    AND ME.COR_PARA_CONF IN (SELECT MV.COR_PARA_CONF FROM MAV_PARAM_CONFI MV
                     WHERE MV.TICL_OID_TIPO_CLIE=4
                       AND MV.COD_ESTA_MAV = 4--ADD
                       AND MV.IND_ACTI = 1
                       AND MV.EST_REGI = 1
                       AND MV.CAM_PARA_MAV = (SELECT F.COD_PERI
                                              FROM BAS_CTRL_FACT F
                                              WHERE F.STA_CAMP = 0
                                              AND  F.IND_CAMP_ACT = 1
                                              AND ROWNUM =1)
                    )
    --
    ;

  TYPE mavEnviosRec IS RECORD
  (
   oidEnvio                         MAV_ENVIO_CONFI.OID_ENVI%TYPE,
   tipoCargo                       MAV_ENVIO_CONFI.TIP_CONS_DESP%TYPE,
   codigoRegion                  MAV_ENVIO_CONFI.COD_REGI%TYPE,
   codigoZona                     MAV_ENVIO_CONFI.COD_ZONA%TYPE,
   oidClienteEnvio                MAV_ENVIO_CONFI.CLIE_OID_CLIE%TYPE,
   codigoClienteEnvio           MAE_CLIEN.COD_CLIE%TYPE,
   codigoClienteDV               MAE_CLIEN.COD_CLIE%TYPE,
   codigoClienteOpestoDV     VARCHAR2(20)
  );

  TYPE mavEnviosTab  IS TABLE OF mavEnviosRec;
  mavEnvios mavEnviosTab;

  lsCodigoClienteAlterno   ZON_DIREC_VENTA_CABEC.COD_CLIE%TYPE;

BEGIN

    --(1) PROCESAMOS A LOS ENVIOS
    OPEN c_envios;
    LOOP
        FETCH c_envios BULK COLLECT INTO mavEnvios LIMIT W_FILAS;
            IF mavEnvios.COUNT > 0 THEN

                FOR x IN mavEnvios.FIRST .. mavEnvios.LAST LOOP

                    IF mavEnvios(x).tipoCargo = 'GR' THEN

                        IF mavEnvios(x).codigoClienteDV IS NOT NULL THEN
                            IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN
                                --ACTUALIZAR EL ENVIO CON EL CODIGO DEL DV
                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(mavEnvios(x).codigoClienteDV),
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                            END IF;
                        ELSE
                            --NO SE UBICÓ POR LA GERENTE BUSCAMOS POR LA ALTERNA
                            lsCodigoClienteAlterno := SUBSTR(mavEnvios(x).codigoClienteOpestoDV, 4);

                            IF lsCodigoClienteAlterno IS NOT NULL THEN
                                    --ACTUALIZAR EL ENVIO CON EL CODIGO DEL ALTERNO DEL DV
                                    UPDATE MAV_ENVIO_CONFI
                                    SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(lsCodigoClienteAlterno),
                                    TIP_CONS_DESP = 'AR',
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                                    WHERE OID_ENVI = mavEnvios(x).oidEnvio;
                            ELSE
                                --NO SE UBICA COMO GERENTE NI COMO ALTERNO
                                --ACTUALIZAMOS A NULL EL OID DEL CLIENTE Y CARGO ALTERNO
                                mavEnvios(x).codigoClienteDV := ' ';-- ADD
                              IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN-- ADD

                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = NULL,
                                TIP_CONS_DESP = 'AR',
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                              END IF;--ADD
                            END IF;

                        END IF;

                    ELSIF mavEnvios(x).tipoCargo = 'GZ' THEN

                        IF mavEnvios(x).codigoClienteDV IS NOT NULL THEN
                            IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN
                                --ACTUALIZAR EL ENVIO CON EL CODIGO DEL DV
                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(mavEnvios(x).codigoClienteDV),
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                            END IF;
                        ELSE
                            --NO SE UBICÓ POR LA GERENTE BUSCAMOS POR LA ALTERNA
                            lsCodigoClienteAlterno := SUBSTR(mavEnvios(x).codigoClienteOpestoDV, 4);

                            IF lsCodigoClienteAlterno IS NOT NULL THEN
                                    --ACTUALIZAR EL ENVIO CON EL CODIGO DEL ALTERNO DEL DV
                                    UPDATE MAV_ENVIO_CONFI
                                    SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(lsCodigoClienteAlterno),
                                    TIP_CONS_DESP = 'AZ',
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                                    WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                            ELSE
                                --NO SE UBICA COMO GERENTE NI COMO ALTERNO
                                --ACTUALIZAMOS A NULL EL OID DEL CLIENTE Y CARGO ALTERNO
                              mavEnvios(x).codigoClienteDV := ' ';-- ADD
                              IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN-- ADD

                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = NULL,
                                TIP_CONS_DESP = 'AZ',
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                              END IF;--ADD

                            END IF;

                        END IF;

                    ELSIF mavEnvios(x).tipoCargo = 'CA' THEN

                        IF mavEnvios(x).codigoClienteDV IS NOT NULL THEN
                            IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN
                                --ACTUALIZAR EL ENVIO CON EL CODIGO DEL DV
                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(mavEnvios(x).codigoClienteDV),
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                            END IF;
                        ELSE
                                --ACTUALIZAR EL ENVIO CON EL CODIGO NULL
                           mavEnvios(x).codigoClienteDV := ' ';-- ADD
                           IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN-- ADD

                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = NULL,
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                             END IF;--ADD

                        END IF;

                    ELSIF mavEnvios(x).tipoCargo = 'AR' THEN
                        -- PRIMERO BUSCAMOS POR LA GERENTE
                        lsCodigoClienteAlterno := SUBSTR(mavEnvios(x).codigoClienteOpestoDV, 4);
                        -----
                        IF lsCodigoClienteAlterno IS NOT NULL THEN
                                --ACTUALIZAR EL ENVIO CON EL CODIGO DE LA GERENTE
                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(lsCodigoClienteAlterno),
                                TIP_CONS_DESP = 'GR',
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                        ELSE
                            --NO SE UBICÓ COMO GERENTE, LA BUSCAMOS COMO ALTERNA (QUE ES LA DATA QUE VIENE)
                            IF mavEnvios(x).codigoClienteDV IS NOT NULL THEN
                                IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN
                                    --ACTUALIZAR EL ENVIO CON EL CODIGO DEL DV DE LA ALTERNA
                                    UPDATE MAV_ENVIO_CONFI
                                    SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(mavEnvios(x).codigoClienteDV),
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                                    WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                                END IF;
                            ELSE
                                --NO SE UBICA COMO GERENTE NI COMO ALTERNO
                                --ACTUALIZAMOS A NULL EL OID DEL CLIENTE Y CARGO ALTERNO

                            mavEnvios(x).codigoClienteDV := ' ';-- ADD
                             IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN-- ADD

                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = NULL,
                                TIP_CONS_DESP = 'AR',
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                             END IF;--ADD

                            END IF;

                        END IF;

                    ELSIF mavEnvios(x).tipoCargo = 'AZ' THEN

                        -- PRIMERO BUSCAMOS POR LA GERENTE
                        lsCodigoClienteAlterno := SUBSTR(mavEnvios(x).codigoClienteOpestoDV, 4);
                        -----
                        IF lsCodigoClienteAlterno IS NOT NULL THEN
                                --ACTUALIZAR EL ENVIO CON EL CODIGO DE LA GERENTE
                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(lsCodigoClienteAlterno),
                                TIP_CONS_DESP = 'GZ',
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                        ELSE
                            --NO SE UBICÓ COMO GERENTE, LA BUSCAMOS COMO ALTERNA (QUE ES LA DATA QUE VIENE)
                            IF mavEnvios(x).codigoClienteDV IS NOT NULL THEN
                                IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN
                                    --ACTUALIZAR EL ENVIO CON EL CODIGO DEL DV DE LA ALTERNA
                                    UPDATE MAV_ENVIO_CONFI
                                    SET CLIE_OID_CLIE = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(mavEnvios(x).codigoClienteDV),
                                    USU_MODI = psCodigoUsuario,
                                    FEC_MODI = SYSDATE
                                    WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                                END IF;
                            ELSE
                                --NO SE UBICA COMO GERENTE NI COMO ALTERNO
                                --ACTUALIZAMOS A NULL EL OID DEL CLIENTE Y CARGO ALTERNO

                            mavEnvios(x).codigoClienteDV := ' ';-- ADD
                             IF mavEnvios(x).codigoClienteDV != mavEnvios(x).codigoClienteEnvio THEN-- ADD


                                UPDATE MAV_ENVIO_CONFI
                                SET CLIE_OID_CLIE = NULL,
                                TIP_CONS_DESP = 'AZ',
                                USU_MODI = psCodigoUsuario,
                                FEC_MODI = SYSDATE
                                WHERE OID_ENVI = mavEnvios(x).oidEnvio;

                             END IF;--ADD

                            END IF;

                        END IF;

                    END IF;

                END LOOP;

            END IF;

        EXIT WHEN c_envios%NOTFOUND;

    END LOOP;
    CLOSE c_envios;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_ACTUA_ENVIO_MASIV: ' || ls_sqlerrm);

END MAV_PR_ACTUA_ENVIO_MASIV;


/**************************************************************************
  Descripcion       : Proceso Inserta Envio MAV Gerente

  Fecha Creacion    : 21/03/2014
  Parametros Entrada:
    psCodigoPais              : Codigo de Pais
    pnCorrelativo             : Correlativo MAV
    psCodigoRegion            : Codigo de Region
    psCodigoZona              : Codigo de Zona
    psCapacitadora            : Codigo Capacitadora
    pnUnidades                : Numero de Unidades
    psCodigoUsuario           : Codigo de Usuario

  Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAV_PR_INSER_ENVIO_GEREN(psCodigoPais         VARCHAR2,
                                   pnCorrelativo        NUMBER,
                                   psCodigoRegion       VARCHAR2,
                                   psCodigoZona         VARCHAR2,
                                   psCapacitadora       VARCHAR2,
                                   pnUnidades           NUMBER,
                                   psCodigoUsuario      VARCHAR2)
IS
  lsCodigoRegion         MAV_ENVIO_CONFI.COD_REGI%TYPE;
  lsCodigoZona           MAV_ENVIO_CONFI.COD_ZONA%TYPE;
  lsTipoConsultora       MAV_ENVIO_CONFI.TIP_CONS_DESP%TYPE;
  lnOidCliente           MAV_ENVIO_CONFI.CLIE_OID_CLIE%TYPE;

  lnSecuencia            NUMBER;
BEGIN

  lnSecuencia := MAV_MENC_SEQ.NEXTVAL;

  INSERT INTO MAV_ENVIO_CONFI
    (OID_ENVI,
     NUM_UNID,
     VAL_PREC,
     PAIS_COD_PAIS,--
     COR_PARA_CONF,--
     MAFA_OID_MATR_FACT,-- OBTENER EL IDETIFICADOR DE MATRIZ
     CLIE_OID_CLIE,
     ESEN_OID_ESTA_ENVI,
     FCOB_OID_FORM_COBR,
     ATDE_OID_ACTI_TIPO_DESP,
     FOPA_OID_FORM_PAGO,
     IND_ENVI,
     ACTI_OID_ACTI,
     IND_ACTI,
     USU_CREA,
     FEC_CREA,
     EST_REGI,
     TIP_CONS_DESP,
     COD_REGI,
     COD_ZONA)
  SELECT lnSecuencia,
         pnUnidades,
         CON.VAL_PREC,
         CON.PAIS_COD_PAIS,
         CON.COR_PARA_CONF,
         CON.MAFA_OID_MATR_FACT,
         NULL, --x.CLIE_OID_CLIE
         1,
         CON.FCOB_OID_FORM_COBR,
         CON.ATDE_OID_ACTI_TIPO_DESP,
         CON.FOPA_OID_FORM_PAGO,
         'P',
         CON.ACTI_OID_ACTI,
         1,
         psCodigoUsuario,
         SYSDATE,
         1,
         NULL,
         NULL,
         NULL
    FROM MAV_ENVIO_CONFI CON
   WHERE CON.COR_PARA_CONF = pnCorrelativo
     AND ROWNUM = 1;

  --BUSCAMOS AL GERENTE DE ZONA O SU ALTERNO
  IF((psCodigoRegion IS NOT NULL) AND (psCodigoZona IS NOT NULL))  THEN
    lsCodigoRegion := psCodigoRegion;
    lsCodigoZona := psCodigoZona;

    BEGIN
      SELECT CL.OID_CLIE
        INTO lnOidCliente
        FROM ZON_DIREC_VENTA_CABEC CA,
             ZON_DIREC_VENTA_DETAL DE,
             ZON_TIPO_CARGO        CG,
             MAE_CLIEN CL
       WHERE CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
         AND CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
         AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
         AND CA.COD_CLIE = DE.COD_CLIE
         AND CA.FEC_REGI = DE.DICA_FEC_REGI
         AND CA.CAM_PROC = DE.DICA_CAM_PROC
         AND CA.EST_REGI = 1
         AND DE.EST_REGI = 1
         AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
         AND CG.EST_REGI = 1
         AND CG.COD_TIPO_CARG = 'GZ'
         AND CA.ESCA_COD_ESTA_CARG = 'A'
         AND CA.COD_CLIE = CL.COD_CLIE
         AND DE.COD_SUBG = '01'
         AND DE.COD_REGI = lsCodigoRegion
         AND DE.COD_ZONA = lsCodigoZona;

      lsTipoConsultora := 'GZ';
    EXCEPTION
      WHEN OTHERS THEN
        lnOidCliente := NULL;
        lsTipoConsultora := 'AZ';
    END;

    IF(lnOidCliente IS NULL) THEN
      BEGIN
        SELECT CL.OID_CLIE
          INTO lnOidCliente
          FROM ZON_DIREC_VENTA_CABEC CA,
               ZON_DIREC_VENTA_DETAL DE,
               ZON_TIPO_CARGO        CG,
               MAE_CLIEN CL
         WHERE CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
           AND CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
           AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
           AND CA.COD_CLIE = DE.COD_CLIE
           AND CA.FEC_REGI = DE.DICA_FEC_REGI
           AND CA.CAM_PROC = DE.DICA_CAM_PROC
           AND CA.EST_REGI = 1
           AND DE.EST_REGI = 1
           AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
           AND CG.EST_REGI = 1
           AND CG.COD_TIPO_CARG = 'MVZ'
           AND CA.ESCA_COD_ESTA_CARG = 'A'
           AND CA.COD_CLIE = CL.COD_CLIE
           AND DE.COD_SUBG = '01'
           AND DE.COD_REGI = lsCodigoRegion
           AND DE.COD_ZONA = lsCodigoZona;

      EXCEPTION
        WHEN OTHERS THEN
          lnOidCliente := NULL;
      END;
    END IF;

  --BUSCAMOS AL GERENTE DE REGION O SU ALTERNO
  ELSIF(psCodigoRegion IS NOT NULL)  THEN

    lsCodigoRegion := psCodigoRegion;

    BEGIN
      SELECT CL.OID_CLIE
        INTO lnOidCliente
        FROM ZON_DIREC_VENTA_CABEC CA,
             ZON_DIREC_VENTA_DETAL DE,
             ZON_TIPO_CARGO        CG,
             MAE_CLIEN CL
       WHERE CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
         AND CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
         AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
         AND CA.COD_CLIE = DE.COD_CLIE
         AND CA.FEC_REGI = DE.DICA_FEC_REGI
         AND CA.CAM_PROC = DE.DICA_CAM_PROC
         AND CA.EST_REGI = 1
         AND DE.EST_REGI = 1
         AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
         AND CG.EST_REGI = 1
         AND CG.COD_TIPO_CARG = 'GR'
         AND CA.ESCA_COD_ESTA_CARG = 'A'
         AND CA.COD_CLIE = CL.COD_CLIE
         AND DE.COD_SUBG = '01'
         AND DE.COD_REGI = lsCodigoRegion;

      lsTipoConsultora := 'GR';
    EXCEPTION
      WHEN OTHERS THEN
        lnOidCliente := NULL;
        lsTipoConsultora := 'AR';
    END;

    IF(lnOidCliente IS NULL) THEN
      BEGIN
        SELECT CL.OID_CLIE
          INTO lnOidCliente
          FROM ZON_DIREC_VENTA_CABEC CA,
               ZON_DIREC_VENTA_DETAL DE,
               ZON_TIPO_CARGO        CG,
               MAE_CLIEN CL
         WHERE CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
           AND CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
           AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
           AND CA.COD_CLIE = DE.COD_CLIE
           AND CA.FEC_REGI = DE.DICA_FEC_REGI
           AND CA.CAM_PROC = DE.DICA_CAM_PROC
           AND CA.EST_REGI = 1
           AND DE.EST_REGI = 1
           AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
           AND CG.EST_REGI = 1
           AND CG.COD_TIPO_CARG = 'MVR'
           AND CA.ESCA_COD_ESTA_CARG = 'A'
           AND CA.COD_CLIE = CL.COD_CLIE
           AND DE.COD_SUBG = '01'
           AND DE.COD_REGI = lsCodigoRegion;

      EXCEPTION
        WHEN OTHERS THEN
          lnOidCliente := NULL;
      END;
    END IF;

  ELSE --BUSCAMOS AL GERENTE DE REGION DE LA CAPACITADORA
    lsCodigoRegion := psCapacitadora;
    lsTipoConsultora := 'CA';

    BEGIN
      SELECT CL.OID_CLIE
        INTO lnOidCliente
        FROM ZON_DIREC_VENTA_CABEC CA,
             ZON_DIREC_VENTA_DETAL DE,
             ZON_TIPO_CARGO        CG,
             MAE_CLIEN CL
       WHERE CA.PAIS_COD_PAIS = DE.PAIS_COD_PAIS
         AND CA.TIOP_COD_TIPO_OPER = DE.TIOP_COD_TIPO_OPER
         AND CA.TICA_COD_TIPO_CARG = DE.TICA_COD_TIPO_CARG
         AND CA.COD_CLIE = DE.COD_CLIE
         AND CA.FEC_REGI = DE.DICA_FEC_REGI
         AND CA.CAM_PROC = DE.DICA_CAM_PROC
         AND CA.EST_REGI = 1
         AND DE.EST_REGI = 1
         AND CG.COD_TIPO_CARG = CA.TICA_COD_TIPO_CARG
         AND CG.EST_REGI = 1
         AND CG.COD_TIPO_CARG = 'EE'
         AND CA.ESCA_COD_ESTA_CARG = 'A'
         AND CA.COD_CLIE = CL.COD_CLIE
         AND DE.COD_SUBG = '01'
         AND DE.COD_REGI = lsCodigoRegion;
    EXCEPTION
      WHEN OTHERS THEN
        lnOidCliente := NULL;
    END;
  END IF;

  UPDATE MAV_ENVIO_CONFI
     SET CLIE_OID_CLIE = lnOidCliente,
         TIP_CONS_DESP = lsTipoConsultora,
         COD_REGI = lsCodigoRegion,
         COD_ZONA = lsCodigoZona
   WHERE OID_ENVI = lnSecuencia;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_INSER_ENVIO_GEREN: ' || ls_sqlerrm);

END MAV_PR_INSER_ENVIO_GEREN;

/**************************************************************************
  Descripcion       : Proceso Inserta Envio MAV Consultoras

  Fecha Creacion    : 18/11/2014
  Parametros Entrada:
    psCodigoPais              : Codigo de Pais
    pnCorrelativo             : Correlativo MAV
    psCodigoCliente            : Codigo de Cliente
    pnUnidades                : Numero de Unidades
    psCodigoUsuario           : Codigo de Usuario

  Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE MAV_PR_INSER_ENVIO_CONSU(psCodigoPais         VARCHAR2,
                                   pnCorrelativo        NUMBER,
                                   psCodigoCliente      VARCHAR2,
                                   pnUnidades           NUMBER,
                                   psCodigoUsuario      VARCHAR2)
IS
  lnOidCliente           MAV_ENVIO_CONFI.CLIE_OID_CLIE%TYPE;

  lnSecuencia            NUMBER;
BEGIN

  lnSecuencia := MAV_MENC_SEQ.NEXTVAL;

  SELECT OID_CLIE
    INTO lnOidCliente
    FROM MAE_CLIEN
   WHERE COD_CLIE = psCodigoCliente;

  INSERT INTO MAV_ENVIO_CONFI
    (OID_ENVI,
     NUM_UNID,
     VAL_PREC,
     PAIS_COD_PAIS,--
     COR_PARA_CONF,--
     MAFA_OID_MATR_FACT,-- OBTENER EL IDETIFICADOR DE MATRIZ
     CLIE_OID_CLIE,
     ESEN_OID_ESTA_ENVI,
     FCOB_OID_FORM_COBR,
     ATDE_OID_ACTI_TIPO_DESP,
     FOPA_OID_FORM_PAGO,
     IND_ENVI,
     ACTI_OID_ACTI,
     IND_ACTI,
     USU_CREA,
     FEC_CREA,
     EST_REGI,
     TIP_CONS_DESP)
  SELECT lnSecuencia,
         pnUnidades,
         CON.VAL_PREC,
         CON.PAIS_COD_PAIS,
         CON.COR_PARA_CONF,
         CON.MAFA_OID_MATR_FACT,
         lnOidCliente,
         1,
         CON.FCOB_OID_FORM_COBR,
         CON.ATDE_OID_ACTI_TIPO_DESP,
         CON.FOPA_OID_FORM_PAGO,
         'P',
         CON.ACTI_OID_ACTI,
         1,
         psCodigoUsuario,
         SYSDATE,
         1,
         'CO'
    FROM MAV_ENVIO_CONFI CON
   WHERE CON.COR_PARA_CONF = pnCorrelativo
     AND ROWNUM = 1;

EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_INSER_ENVIO_CONSU: ' || ls_sqlerrm);

END MAV_PR_INSER_ENVIO_CONSU;

/***************************************************************************
    Descripcion       : Valida la carga externa consultoras
    Fecha Creacion    : 09/02/2015
    Autor             : Fernando Ochoa
***************************************************************************/
PROCEDURE MAV_PR_VALIDAR_EXTER_CLIE(psCodigoUsuario VARCHAR2) IS

    CURSOR C_EXTERNA_CLIE IS
    SELECT
    NUM_FILA,
    COD_PAIS,
    COD_CLI,
    NUM_UNID
    FROM MAV_TEMPO_EXTER_CONSUL
    WHERE COD_USUA = psCodigoUsuario;

    TYPE tipoClientes IS RECORD
    (
        numeroFila              MAV_TEMPO_EXTER_CONSUL.NUM_FILA%TYPE,
        codigoPais        MAV_TEMPO_EXTER_CONSUL.COD_PAIS%TYPE,
        codigoCliente         MAV_TEMPO_EXTER_CONSUL.COD_CLI%TYPE,
        numeroUnidades         MAV_TEMPO_EXTER_CONSUL.NUM_UNID%TYPE
    );

  TYPE tipoClientesTab  IS TABLE OF tipoClientes;
  tipoClientesRecordN tipoClientesTab;

  lsMensajeError            MAV_TEMPO_EXTER_CONSUL.MEN_ERRO%TYPE;
  lnNumeroFila              MAV_TEMPO_EXTER_CONSUL.NUM_FILA%TYPE;
  esNumerico BOOLEAN;
  lnNumUnidades NUMBER;
  lnOcurrencias NUMBER;
  lbValido BOOLEAN;

BEGIN


  OPEN C_EXTERNA_CLIE;
  LOOP
    FETCH C_EXTERNA_CLIE BULK COLLECT INTO tipoClientesRecordN LIMIT W_FILAS;
    IF tipoClientesRecordN.COUNT > 0 THEN
      FOR x IN tipoClientesRecordN.FIRST .. tipoClientesRecordN.LAST LOOP

        lnNumeroFila   := tipoClientesRecordN(x).numeroFila;
        lsMensajeError := '';
        esNumerico := TRUE;
        lbValido := TRUE;
        -- (1) Validar que la columna de  codigo pais no este vacio,
       IF LENGTH(tipoClientesRecordN(x).codigoPais) = 0  THEN
            lsMensajeError := lsMensajeError||'Fila no tiene valor en código de pais';
            lbValido := FALSE;
        END IF;
        -- --
        
        -- (2) Validar que la columna de  codigo consultora no este vacio,
       IF(lbValido) THEN
          IF LENGTH(tipoClientesRecordN(x).codigoCliente)= 0 THEN
            lsMensajeError := lsMensajeError||'Fila no tiene valor en código de consultora';
            lbValido := FALSE;
          END IF;
        END IF;
        -- --
        
         -- (3) Validar que la columna de  numero unidades no este vacio,
       IF(lbValido) THEN
          IF LENGTH(tipoClientesRecordN(x).numeroUnidades) = 0 THEN
            lsMensajeError := lsMensajeError||'Fila no tiene valor en cantidad';
            lbValido := FALSE;
          END IF;
        END IF;
        -- --
        
          -- (4) Validar codigo pais exista,
       IF(lbValido) THEN       
         SELECT COUNT(1)
              INTO lnOcurrencias 
         FROM   SEG_PAIS
         WHERE  COD_PAIS = tipoClientesRecordN(x).codigoPais; 
                    
          IF (lnOcurrencias = 0) THEN
            lsMensajeError := lsMensajeError||'Código de País No existe';
            lbValido := FALSE;
          END IF;
        END IF;
        -- --
        
        -- (5) Validar codigo consultora exista,
       IF(lbValido) THEN       
         SELECT COUNT(1)
              INTO lnOcurrencias 
         FROM   MAE_CLIEN
         WHERE  PAIS_OID_PAIS = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(tipoClientesRecordN(x).codigoPais) 
		            AND  COD_CLIE = tipoClientesRecordN(x).codigoCliente;
                    
          IF (lnOcurrencias = 0) THEN
            lsMensajeError := lsMensajeError||'Código de Consultora No existe';
            lbValido := FALSE;
          END IF;
        END IF;
        -- --
          --(6) Validamos Numero Unidades
        IF(lbValido) THEN
          BEGIN
            lnNumUnidades := TO_NUMBER(tipoClientesRecordN(x).numeroUnidades);
          EXCEPTION
            WHEN OTHERS THEN
              lbValido := FALSE;
              lsMensajeError := lsMensajeError||'Fila no tiene número valido en cantidad';
          END;
        END IF;

        --(7) Validamos Numero Unidades > 0
        IF(lbValido) THEN
          IF(lnNumUnidades = 0) THEN
            lbValido := FALSE;
            lsMensajeError := lsMensajeError||'Cantidad debe ser mayor a 0';
          END IF;
        END IF;
 
        
         -- (8) Validar consultora duplicada
        IF(lbValido) THEN
          SELECT MIN(NUM_FILA) NUMFILA_DUP
          INTO lnOcurrencias
          FROM MAV_TEMPO_EXTER_CONSUL
          WHERE COD_PAIS = tipoClientesRecordN(x).codigoPais 
          AND COD_CLI = tipoClientesRecordN(x).codigoCliente
          AND NUM_FILA != lnNumeroFila;

          IF lnOcurrencias IS NOT NULL THEN
              IF lnOcurrencias < lnNumeroFila THEN
               lsMensajeError := lsMensajeError||'Código de Consultora duplicada ';
              END IF;
          END IF;
        END IF;
        -- --

        IF(length(lsMensajeError) > 0) THEN           
           UPDATE MAV_TEMPO_EXTER_CONSUL
           SET EST_REGI = '0',
           MEN_ERRO = lsMensajeError
           WHERE COD_USUA = psCodigoUsuario
           AND NUM_FILA = tipoClientesRecordN(x).numeroFila;
        ELSE
           UPDATE MAV_TEMPO_EXTER_CONSUL
           SET
           EST_REGI = '1',
           IND_ACC = '3',
           DESC_CLI = (SELECT VAL_APE1||' '||VAL_APE2||', '||VAL_NOM1||' '||VAL_NOM2
                    	 FROM MAE_CLIEN M
                       WHERE  M.PAIS_OID_PAIS = GEN_PKG_GENER. GEN_FN_DEVUELVE_ID_PAIS(tipoClientesRecordN(x).codigoPais)
                       AND  M.COD_CLIE = tipoClientesRecordN(x).codigoCliente)      
           WHERE COD_USUA = psCodigoUsuario
           AND NUM_FILA = tipoClientesRecordN(x).numeroFila;
        END IF;

      END LOOP;
    END IF;
  EXIT WHEN C_EXTERNA_CLIE%NOTFOUND;
  END LOOP;
  CLOSE C_EXTERNA_CLIE;

EXCEPTION
  WHEN OTHERS THEN
       ln_sqlcode := SQLCODE;
       ls_sqlerrm := SUBSTR(SQLERRM,1,150);
       RAISE_APPLICATION_ERROR(-20123, 'ERROR MAV_PR_VALIDAR_EXTER_CLIE: '||ls_sqlerrm);
END MAV_PR_VALIDAR_EXTER_CLIE;

END MAV_PKG_PROCE;
/
