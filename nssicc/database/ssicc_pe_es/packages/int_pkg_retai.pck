CREATE OR REPLACE PACKAGE "INT_PKG_RETAI" IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;


/* Declaracion de procedures */
/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar MAtriz Facturacion RET
  Fecha Creacion     : 28/11/2006
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_RET_MATRI_CAMPA (
   psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoAcceso VARCHAR2,
   psCodigoSubAcceso VARCHAR2,
   psPeriodoInicio VARCHAR2,
   psPeriodoFin VARCHAR2
);
/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Matriz para Puntajes Calypso
  Fecha Creacion     : 28/11/2006
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_RET_MATRI_PUNTA_CALYP (
   psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoAcceso VARCHAR2,
   psCodigoSubAcceso VARCHAR2,
   psPeriodoInicio VARCHAR2,
   psPeriodoFin VARCHAR2
);

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Factura
                       Venta Directa
  Fecha Creacion     : 27/02/2007
  Autor              : Carlos Bazalar
 ***************************************************************************/
PROCEDURE INT_PR_RET_FACTU_VENTA_DIREC(
   psCodigoPais       VARCHAR2,
   psCodigosistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psOrigenFactura    VARCHAR2
);

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Complemento Factura Venta Directa
  Fecha Creacion     : 27/02/2007
  Autor              : Carlos Bazalar
 ***************************************************************************/
PROCEDURE INT_PR_RET_COMPL_FACTU_VTADI(
   psCodigoPais       VARCHAR2,
   psCodigosistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2
);

/**************************************************************************
Descripcion        : Devuelve Indicador Descuento Especifico del Producto
Fecha Creacion     : 01/03/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION INT_FN_RET_VALOR_ESPEC_DSCTO(
    psCodigoPeriodo            VARCHAR2,
    psCodigoTipoCliente        VARCHAR2,
    psCodigoSubtipoCliente     VARCHAR2,
    psCodigoTipoOferta         VARCHAR2,
    psCodigoNegocio            VARCHAR2,
    psCodigoUnidadNegocio      VARCHAR2,
    psCodigoMarcaProducto      VARCHAR2,
    pnComisionable             NUMBER
)
RETURN VARCHAR2;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Informacion
                       Venta
  Fecha Creacion     : 10/05/2010
  Autor              : Jose Luis Rodriguez
 ***************************************************************************/
PROCEDURE INT_PR_RET_INFOR_VENTA(
   psCodigoPais       VARCHAR2,
   psCodigosistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2
);

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Detalle de Facturas de Venta Directa
  Fecha Creacion     : 06/09/2010
  Autor              : Dennys Oliva Iriarte
 ***************************************************************************/
PROCEDURE INT_PR_RET_DETAL_FACTU_VTADI(psCodigoPais       VARCHAR2,
                                       psCodigosistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2);
/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Detalle de Facturas de Venta Directa para Chile
  Fecha Creacion     : 06/09/2010
  Autor              : Dennys Oliva Iriarte
 ***************************************************************************/
PROCEDURE INT_PR_RET_DETAL_FACTU_VD_CH(psCodigoPais       VARCHAR2,
                                       psCodigosistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2);

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Detalle de Facturas de Venta Directa
  Fecha Creacion     : 06/09/2010
  Autor              : Dennys Oliva Iriarte
 ***************************************************************************/
PROCEDURE INT_PR_RET_DETAL_FACTU_VTADIXX(psCodigoPais       VARCHAR2,
                                       psCodigosistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2);
/*************************************************************************************
Descripcion       : Interfaz que env�a los cierres de Zona, Region y Campa�a (RET-15)
Fecha Creacion    : 07/01/2014
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoMarca    : Codigo Marca
 psCodigoCanal    : Codgio Canal
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
 psCodigoPeriodo  : A�o Campa�a
 pdFechaFacturacion: Fecha Facturaci�n

Autor: CSVD - FFVV
*************************************************************************************/
PROCEDURE INT_PR_RET_ENVIO_CIERR
(
   psCodigoPais       VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2
);

/***************************************************************************
Descripcion : Interfaz que envia informacion de clientes (RET-16)
Fecha Creacion : 19/08/2015
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion: Fecha Facturacion
 psUsuarioSistema : Usuario Sistema
 psTipoProceso : Tipo Proceso
 psCodigoConsultora : Codigo de Consultora
 psNumeroDocumento : Numero Documento
***************************************************************************/
PROCEDURE INT_PR_RET_ENVIO_CLIEN
  (psCodigoPais      VARCHAR2,
   psCodigoSistema   VARCHAR2,
   psCodigoInterfaz  VARCHAR2,
   psNombreArchivo   VARCHAR2,
   psCodigoMarca     VARCHAR2,
   psCodigoCanal     VARCHAR2,
   psCodigoPeriodo   VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psUsuarioSistema   VARCHAR2,
   psTipoProceso	  VARCHAR2,
   psCodigoConsultora VARCHAR2,
   psNumeroDocumento  VARCHAR2
  );



END INT_PKG_RETAI;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_RETAI" IS

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar MAtriz Facturacion RET
  Fecha Creacion     : 28/11/2006
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_RET_MATRI_CAMPA(
   psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoAcceso VARCHAR2,
   psCodigoSubAcceso VARCHAR2,
   psPeriodoInicio VARCHAR2,
   psPeriodoFin VARCHAR2
)

IS
   CURSOR c_interfaz IS

      SELECT SEG_PAIS.COD_PAIS,
             SEG_PERIO_CORPO.COD_PERI,
             PRE_OFERT_DETAL.VAL_CODI_VENT,
             MAE_PRODU.COD_SAP,
             MAE_PRODU.DES_CORT,
    GEN_I18N_SICC_PAIS.VAL_I18N,
             PRE_TIPO_OFERT.COD_TIPO_OFER,
    ROUND(PRE_OFERT_DETAL.IMP_PREC_CATA / PRE_OFERT_DETAL.VAL_FACT_REPE, 2) AS IMP_PREC_PROD,
             PRE_TIPO_OFERT.IND_COMI,
             PED_TASA_IMPUE.VAL_TASA_IMPU,
             SEG_MARCA_PRODU.COD_MARC_PROD,
             MAE_NEGOC.COD_NEGO,
             MAE_UNIDA_NEGOC.COD_UNID_NEGO,
             GEN_PKG_GENER.GEN_FN_DEVUE_DSCTO_GENER(
                  CRA_PERIO.OID_PERI,
                  PRE_OFERT_DETAL.VAL_CODI_VENT) AS VALDES
  FROM PRE_OFERT_DETAL,
    PRE_TIPO_OFERT,
    SEG_PAIS,
    SEG_MARCA,
    SEG_CANAL,
    SEG_ACCES,
    SEG_SUBAC,
    CRA_PERIO,
    SEG_PERIO_CORPO,
    SEG_TIPO_PERIO,
    MAE_PRODU,
    MAE_NEGOC,
    MAE_UNIDA_NEGOC,
    SEG_MARCA_PRODU,
    PED_IMPUE_GENER,
    PED_TASA_IMPUE,
    PRE_MATRI_FACTU,
    PRE_MATRI_FACTU_CABEC,
 GEN_I18N_SICC_PAIS
   WHERE (    (MAE_PRODU.OID_PROD = PRE_OFERT_DETAL.PROD_OID_PROD)
          AND (MAE_NEGOC.OID_NEGO = MAE_PRODU.NEGO_OID_NEGO)
          AND (MAE_UNIDA_NEGOC.OID_UNID_NEGO = MAE_PRODU.UNEG_OID_UNID_NEGO)
          AND (PED_TASA_IMPUE.OID_TASA_IMPU = PED_IMPUE_GENER.TAIM_OID_TASA_IMPU)
          AND (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS)
          AND (SEG_PAIS.OID_PAIS = PED_IMPUE_GENER.PAIS_OID_PAIS)
          AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
          AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
          AND (SEG_CANAL.OID_CANA = SEG_ACCES.CANA_OID_CANA)
          AND (SEG_ACCES.OID_ACCE = SEG_SUBAC.ACCE_OID_ACCE)
          AND (SEG_SUBAC.OID_SBAC = PED_IMPUE_GENER.SBAC_OID_SBAC)
          AND (PRE_TIPO_OFERT.OID_TIPO_OFER = PRE_OFERT_DETAL.TOFE_OID_TIPO_OFER)
          AND (SEG_MARCA_PRODU.OID_MARC_PROD = MAE_PRODU.MAPR_OID_MARC_PROD)
          AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
          AND (PRE_OFERT_DETAL.OID_DETA_OFER = PRE_MATRI_FACTU.OFDE_OID_DETA_OFER)
          AND (CRA_PERIO.OID_PERI = PRE_MATRI_FACTU_CABEC.PERD_OID_PERI)
          AND (PRE_MATRI_FACTU_CABEC.OID_CABE = PRE_MATRI_FACTU.MFCA_OID_CABE)
          AND (SEG_TIPO_PERIO.OID_TIPO_PERI = SEG_CANAL.TIPE_OID_TIPO_PERI)
          AND (SEG_TIPO_PERIO.OID_TIPO_PERI = SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI)
          AND (SEG_CANAL.COD_CANA = psCodigoCanal)
          AND (SEG_MARCA.COD_MARC = psCodigoMarca)
          AND (SEG_ACCES.COD_ACCE = psCodigoAcceso)
          AND (SEG_SUBAC.COD_SBAC = psCodigoSubAcceso)
          AND (SEG_PAIS.COD_PAIS = psCodigoPais)
          AND (SEG_PERIO_CORPO.COD_PERI >= psPeriodoInicio)
          AND (SEG_PERIO_CORPO.COD_PERI <= psPeriodoFin)
          AND (PRE_OFERT_DETAL.VAL_CODI_VENT IS NOT NULL)
    AND (GEN_I18N_SICC_PAIS.ATTR_ENTI = 'MAE_PRODU')
          AND (GEN_I18N_SICC_PAIS.VAL_OID = MAE_PRODU.OID_PROD)
         -- AND (PRE_OFERT_DETAL.IND_DIGI = 1)
         )
   ORDER BY SEG_PERIO_CORPO.COD_PERI ASC,
         MAE_PRODU.COD_SAP ASC,
         PRE_OFERT_DETAL.VAL_CODI_VENT ASC;

   TYPE interfazRec IS RECORD
   (
        codigoPais                           SEG_PAIS.COD_PAIS%TYPE,
        codigoPeriodo                        SEG_PERIO_CORPO.COD_PERI%TYPE,
        codigoVenta                          PRE_OFERT_DETAL.VAL_CODI_VENT%TYPE,
        codigoProducto                       MAE_PRODU.COD_SAP%TYPE,
        descripcionProducto                  MAE_PRODU.DES_CORT%TYPE,
  descripcion                          GEN_I18N_SICC_PAIS.VAL_I18N%TYPE,
        codigoTipoOferta                     PRE_TIPO_OFERT.COD_TIPO_OFER%TYPE,
        precioCatalogo                       PRE_OFERT_DETAL.IMP_PREC_CATA%TYPE,
        flagComisionable                     PRE_TIPO_OFERT.IND_COMI%TYPE,
        tasaImpuestos                        PED_TASA_IMPUE.VAL_TASA_IMPU%TYPE,
        marcaProducto                        SEG_MARCA_PRODU.COD_MARC_PROD%TYPE,
        codigoNegocio                        MAE_NEGOC.COD_NEGO%TYPE,
        codigoUnidadNegocio                  MAE_UNIDA_NEGOC.COD_UNID_NEGO%TYPE,
        valorDescuentoEspecifico             VARCHAR2(3)
   );

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=
                 interfazRecord(x).codigoPais                     ||';'||
                 interfazRecord(x).codigoProducto                 ||';'||
                 interfazRecord(x).codigoPeriodo                  ||';'||
                 interfazRecord(x).codigoVenta                    ||';'||
                 interfazRecord(x).descripcion                    ||';'||
                 interfazRecord(x).codigoTipoOferta               ||';'||
                 interfazRecord(x).precioCatalogo                 ||';'||
                 interfazRecord(x).tasaImpuestos                  ||';'||
                 interfazRecord(x).valorDescuentoEspecifico     ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    UTL_FILE.FCLOSE(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_MATRI_CAMPA: '||ls_sqlerrm);

END INT_PR_RET_MATRI_CAMPA;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Matriz para Puntajes Calypso
  Fecha Creacion     : 28/11/2006
  Autor              : Marco Silva
 ***************************************************************************/
PROCEDURE INT_PR_RET_MATRI_PUNTA_CALYP(
   psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoAcceso VARCHAR2,
   psCodigoSubAcceso VARCHAR2,
   psPeriodoInicio VARCHAR2,
   psPeriodoFin VARCHAR2
)
IS
   CURSOR c_interfaz IS
   SELECT SEG_PERIO_CORPO.COD_PERI,
          PRE_OFERT_DETAL.VAL_CODI_VENT,
          PRE_TIPO_OFERT.COD_TIPO_OFER,
          ROUND(PRE_OFERT_DETAL.IMP_PREC_CATA / PRE_OFERT_DETAL.VAL_FACT_REPE, 2) AS IMP_PREC_PROD
  FROM PRE_OFERT_DETAL,
    PRE_TIPO_OFERT,
    SEG_PAIS,
    SEG_MARCA,
    SEG_CANAL,
    SEG_ACCES,
    SEG_SUBAC,
    CRA_PERIO,
    SEG_PERIO_CORPO,
    SEG_TIPO_PERIO,
    MAE_PRODU,
    MAE_NEGOC,
    MAE_UNIDA_NEGOC,
    SEG_MARCA_PRODU,
    PED_IMPUE_GENER,
    PED_TASA_IMPUE,
    PRE_MATRI_FACTU,
    PRE_MATRI_FACTU_CABEC,
    GEN_I18N_SICC_PAIS
   WHERE (    (MAE_PRODU.OID_PROD = PRE_OFERT_DETAL.PROD_OID_PROD)
          AND (MAE_NEGOC.OID_NEGO = MAE_PRODU.NEGO_OID_NEGO)
          AND (MAE_UNIDA_NEGOC.OID_UNID_NEGO = MAE_PRODU.UNEG_OID_UNID_NEGO)
          AND (PED_TASA_IMPUE.OID_TASA_IMPU = PED_IMPUE_GENER.TAIM_OID_TASA_IMPU)
          AND (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS)
          AND (SEG_PAIS.OID_PAIS = PED_IMPUE_GENER.PAIS_OID_PAIS)
          AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
          AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
          AND (SEG_CANAL.OID_CANA = SEG_ACCES.CANA_OID_CANA)
          AND (SEG_ACCES.OID_ACCE = SEG_SUBAC.ACCE_OID_ACCE)
          AND (SEG_SUBAC.OID_SBAC = PED_IMPUE_GENER.SBAC_OID_SBAC)
          AND (PRE_TIPO_OFERT.OID_TIPO_OFER = PRE_OFERT_DETAL.TOFE_OID_TIPO_OFER)
          AND (SEG_MARCA_PRODU.OID_MARC_PROD = MAE_PRODU.MAPR_OID_MARC_PROD)
          AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
          AND (PRE_OFERT_DETAL.OID_DETA_OFER = PRE_MATRI_FACTU.OFDE_OID_DETA_OFER)
          AND (CRA_PERIO.OID_PERI = PRE_MATRI_FACTU_CABEC.PERD_OID_PERI)
          AND (PRE_MATRI_FACTU_CABEC.OID_CABE = PRE_MATRI_FACTU.MFCA_OID_CABE)
          AND (SEG_TIPO_PERIO.OID_TIPO_PERI = SEG_CANAL.TIPE_OID_TIPO_PERI)
          AND (SEG_TIPO_PERIO.OID_TIPO_PERI = SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI)
          AND (SEG_CANAL.COD_CANA = psCodigoCanal)
          AND (SEG_MARCA.COD_MARC = psCodigoMarca)
          AND (SEG_ACCES.COD_ACCE = psCodigoAcceso)
          AND (SEG_SUBAC.COD_SBAC = psCodigoSubAcceso)
          AND (SEG_PAIS.COD_PAIS = psCodigoPais)
          AND (SEG_PERIO_CORPO.COD_PERI >= psPeriodoInicio)
          AND (SEG_PERIO_CORPO.COD_PERI <= psPeriodoFin)
          AND (PRE_OFERT_DETAL.VAL_CODI_VENT IS NOT NULL)
          AND (GEN_I18N_SICC_PAIS.ATTR_ENTI = 'MAE_PRODU')
          AND (GEN_I18N_SICC_PAIS.VAL_OID = MAE_PRODU.OID_PROD)
         )
   ORDER BY SEG_PERIO_CORPO.COD_PERI ASC,
         MAE_PRODU.COD_SAP ASC,
         PRE_OFERT_DETAL.VAL_CODI_VENT ASC;
   TYPE interfazRec IS RECORD
   (
        codigoPeriodo                        SEG_PERIO_CORPO.COD_PERI%TYPE,
        codigoVenta                          PRE_OFERT_DETAL.VAL_CODI_VENT%TYPE,
        codigoTipoOferta                     PRE_TIPO_OFERT.COD_TIPO_OFER%TYPE,
        precioCatalogo                       PRE_OFERT_DETAL.IMP_PREC_CATA%TYPE
   );
   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;
  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */
    OPEN c_interfaz;
    LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=
                 interfazRecord(x).codigoPeriodo                  ||';'||
                 interfazRecord(x).codigoVenta                    ||';'||
                 interfazRecord(x).codigoTipoOferta               ||';'||
                 interfazRecord(x).precioCatalogo                 ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
    UTL_FILE.FCLOSE(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_MATRI_PUNTA_CALYP: '||ls_sqlerrm);
END INT_PR_RET_MATRI_PUNTA_CALYP;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Factura
                       Venta Directa
  Fecha Creacion     : 27/02/2007
  Autor              : Carlos Bazalar
 ***************************************************************************/
PROCEDURE INT_PR_RET_FACTU_VENTA_DIREC(
   psCodigoPais       VARCHAR2,
   psCodigosistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psOrigenFactura    VARCHAR2
)

IS
  CURSOR c_interfaz IS
      SELECT   CABECERA.CODPAIS AS CODPAIS,
         PED_SOLIC_CABEC.VAL_NUME_SOLI AS NUMFAC,
         CABECERA.CODVEN AS CODVEN,
         CABECERA.CODPRO AS CODPRO,
         CABECERA.CODCLI AS CODCLI,
         CABECERA.FECFAC AS FECFAC,
         CABECERA.CODMAR AS CODMAR,
         AVG (CABECERA.UNIATE) AS UNIATE,
         AVG (CABECERA.VALTOT) AS VALTOT,
         CABECERA.ORGFAC AS ORGFAC,
         CABECERA.UBIGEO AS UBIGEO,
         CABECERA.CAMFAC AS CAMFAC
    FROM (SELECT NVL (SEG_PAIS.COD_PAIS, ' ') AS CODPAIS,
                 NVL (PED_SOLIC_CABEC.VAL_NUME_SOLI, 0) AS NUMFAC,
                 NVL (PED_SOLIC_POSIC.VAL_CODI_VENT, ' ') AS CODVEN,
                 NVL (MAE_PRODU.COD_SAP, ' ') AS CODPRO,
                 NVL (MAE_CLIEN.COD_CLIE, ' ') AS CODCLI,
                 PED_SOLIC_CABEC.FEC_FACT AS FECFAC,
                 NVL (SEG_MARCA_PRODU.COD_MARC_PROD, ' ') AS CODMAR,
                 NVL (PED_SOLIC_POSIC.NUM_UNID_ATEN, 0) AS UNIATE,
                 DECODE (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA * PED_SOLIC_POSIC.NUM_UNID_ATEN,
                         0, 0,
                         NVL (PED_SOLIC_POSIC.VAL_PREC_FACT_UNIT_LOCA * PED_SOLIC_POSIC.NUM_UNID_ATEN, 0)
                        ) AS VALTOT,
                 '99' AS ORGFAC,
                 NVL (ZON_VALOR_ESTRU_GEOPO.ORDE_1, ' ') || NVL (ZON_VALOR_ESTRU_GEOPO.ORDE_2, ' ') AS UBIGEO,
                 NVL (SEG_PERIO_CORPO.COD_PERI, ' ') AS CAMFAC,
                 PED_SOLIC_CABEC.OID_SOLI_CABE,
                 PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE
            FROM PED_SOLIC_CABEC,
                 SEG_PAIS,
                 SEG_MARCA,
                 SEG_CANAL,
                 CRA_PERIO,
                 SEG_PERIO_CORPO,
                 SEG_TIPO_PERIO,
                 PED_ESTAD_SOLIC,
                 FAC_TIPO_DOCUM,
                 FAC_TIPO_DOCUM_LEGAL,
                 PED_TIPO_SOLIC_PAIS,
                 PED_TIPO_SOLIC,
                 PED_SOLIC_POSIC,
                 PED_ESTAD_POSIC,
                 MAE_PRODU,
                 SEG_MARCA_PRODU,
                 MAE_CLIEN,
                 ZON_VALOR_ESTRU_GEOPO
           WHERE (    (SEG_PAIS.COD_PAIS = PSCODIGOPAIS)
                  AND (SEG_MARCA.COD_MARC = PSCODIGOMARCA)
                  AND (SEG_CANAL.COD_CANA = PSCODIGOCANAL)
                  AND (SEG_PERIO_CORPO.COD_PERI = PSCODIGOPERIODO)
                  AND (PED_SOLIC_CABEC.FEC_FACT = TO_DATE (PSFECHAFACTURACION, 'DD/MM/YYYY'))
                  AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
                  AND ((COD_TIPO_SOLI LIKE 'SE%') OR (COD_TIPO_SOLI = 'SOC'))
      AND (PED_SOLIC_POSIC.VAL_CODI_VENT IS NOT NULL)
                  AND (PED_SOLIC_CABEC.IND_PEDI_PRUE = 0)
                  AND (PED_TIPO_SOLIC.IND_DEVO = 0)
                  AND (PED_TIPO_SOLIC.IND_ANUL = 0)
                  AND (PED_ESTAD_SOLIC.COD_ESTA_SOLI <> 'AN')
                  AND (PED_ESTAD_POSIC.COD_ESTA_POSI <> 'AN')
                  AND (FAC_TIPO_DOCUM_LEGAL.IND_ACTI_DOCU = 1)
                  AND (PED_SOLIC_CABEC.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
                  AND (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
                  AND (CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC)
                  AND (CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA)
                  AND (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
                  AND (SEG_CANAL.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                  AND (SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                  AND (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI)
                  AND (PED_SOLIC_CABEC.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU)
                  AND (FAC_TIPO_DOCUM.COD_TIPO_DOCU = FAC_TIPO_DOCUM_LEGAL.COD_TIPO_DOLE)
                  AND (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS)
                  AND (PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI)
                  AND (PED_SOLIC_CABEC.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE)
                  AND (PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI = PED_ESTAD_POSIC.OID_ESTA_POSI)
                  AND (PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD)
                  AND (MAE_PRODU.MAPR_OID_MARC_PROD = SEG_MARCA_PRODU.OID_MARC_PROD)
                  AND (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
                  AND (PED_SOLIC_CABEC.VEPO_OID_VALO_ESTR_GEOP = ZON_VALOR_ESTRU_GEOPO.OID_VALO_ESTR_GEOP)
                 )) CABECERA,
         PED_SOLIC_CABEC
   WHERE CABECERA.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
GROUP BY CABECERA.CODPAIS,
         PED_SOLIC_CABEC.VAL_NUME_SOLI,
         CABECERA.CODVEN,
         CABECERA.CODPRO,
         CABECERA.CODCLI,
         CABECERA.FECFAC,
         CABECERA.CODMAR,
         CABECERA.ORGFAC,
         CABECERA.UBIGEO,
         CABECERA.CAMFAC
ORDER BY CODPAIS,
         CODCLI,
         NUMFAC,
         CODVEN;

  TYPE interfazRec IS RECORD
  (
      codigoPais  SEG_PAIS.COD_PAIS%TYPE,
      valNumeSoli PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
      codigoVen   PED_SOLIC_POSIC.VAL_CODI_VENT%TYPE,
      codigoPro   MAE_PRODU.COD_SAP%TYPE,
      codigoCli   MAE_CLIEN.COD_CLIE%TYPE,
      fechaFact   PED_SOLIC_CABEC.FEC_FACT%TYPE,
      codigoMarca SEG_MARCA_PRODU.COD_MARC_PROD%TYPE,
      unidadAten  PED_SOLIC_POSIC.NUM_UNID_ATEN%TYPE,
      valTotal    PED_SOLIC_POSIC.VAL_PREC_FACT_TOTA_LOCA%TYPE,
      orgFac      varchar2(100),
      ubigeo      varchar2(200),
      camFac      SEG_PERIO_CORPO.COD_PERI%TYPE
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

BEGIN
  /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

  /* Generando Archivo de Texto (Detalle) */
  OPEN c_interfaz;
  LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=
                 interfazRecord(x).codigoPais                          ||';'||
                 to_char(interfazRecord(x).valNumeSoli,'9999999990')   ||';'||
                 interfazRecord(x).codigoCli                           ||';'||
                 to_char(interfazRecord(x).fechaFact,'YYYYMMDD')       ||';'||
                 interfazRecord(x).codigoPro                           ||';'||
                 interfazRecord(x).codigoVen                           ||';'||
                 interfazRecord(x).codigoMarca                         ||';'||
                 to_char(interfazRecord(x).unidadAten,'99990')         ||';'||
                 to_char(interfazRecord(x).valTotal,'99999990.99')     ||';'||
                 interfazRecord(x).orgFac                              ||';'||
                 interfazRecord(x).ubigeo                              ||';'||
                 interfazRecord(x).camFac     ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;
  UTL_FILE.FCLOSE(V_HANDLE);

  /* Procedimiento final para generar interfaz */
  GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

  RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_FACTU_VENTA_DIREC: '||ls_sqlerrm);
END INT_PR_RET_FACTU_VENTA_DIREC;



/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Complemento Factura Venta Directa
  Fecha Creacion     : 27/02/2007
  Autor              : Carlos Bazalar
 ***************************************************************************/
PROCEDURE INT_PR_RET_COMPL_FACTU_VTADI(
   psCodigoPais       VARCHAR2,
   psCodigosistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2
)

IS
  CURSOR c_interfaz IS
        SELECT   CABECERA.CODPAIS AS CODPAIS,
         PED_SOLIC_CABEC.VAL_NUME_SOLI AS NUMFAC,
         CABECERA.CODVEN AS CODVEN,
         CABECERA.CODPRO AS CODPRO,
         CABECERA.CODCLI AS CODCLI,
         AVG (CABECERA.VVCU) AS VVCU,
         AVG (CABECERA.VVCUD) AS VVCUD,
         AVG (CABECERA.VVCD) AS VVCD,
         AVG (CABECERA.TOTIMP) AS TOTIMP,
         AVG (CABECERA.FLETE) AS FLETE,
         CABECERA.TIPDOC AS TIPDOC
    FROM (SELECT NVL (SEG_PAIS.COD_PAIS, ' ') AS CODPAIS,
                 NVL (PED_SOLIC_CABEC.VAL_NUME_SOLI, 0) AS NUMFAC,
                 NVL (PED_SOLIC_POSIC.VAL_CODI_VENT, ' ') AS CODVEN,
                 NVL (MAE_PRODU.COD_SAP, ' ') AS CODPRO,
                 NVL (MAE_CLIEN.COD_CLIE, ' ') AS CODCLI,
                 -- Se envia el mismo valor tanto para B y F (CHR 08/08/2008)
                 NVL (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0) AS VVCU,
                 -- Se invirtio el valor enviandose el (precio catalogo - dscto - imp = VAL_PREC_FACT_UNIT_LOCA)
                 -- para boletas y (precio catalogo - dscto - imp = VAL_PREC_NETO_UNIT_LOCA) para facturas (CHR 08/08/2008)
                 (CASE FAC_TIPO_DOCUM_LEGAL.IND_TIPO_DOCU
                     WHEN 'F'
                        THEN DECODE (NVL (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0),
                                     0, 0,
                                     NVL (PED_SOLIC_POSIC.VAL_PREC_NETO_UNIT_LOCA, 0)
                                    )
                     WHEN 'B'
                        THEN DECODE (NVL (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0),
                                     0, 0,
                                     NVL (PED_SOLIC_POSIC.VAL_PREC_FACT_UNIT_LOCA, 0)
                                    )
                  END
                 ) AS VVCUD,
                 -- Se invirtio el valor enviandose el (precio catalogo - dscto - imp = VAL_PREC_FACT_TOTA_LOCA)
                 -- para boletas y (precio catalogo - dscto - imp = VAL_PREC_NETO_TOTA_LOCA) para facturas (CHR 08/08/2008)
                 (CASE FAC_TIPO_DOCUM_LEGAL.IND_TIPO_DOCU
                     WHEN 'F'
                        THEN DECODE (NVL (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0),
                                     0, 0,
                                     NVL (PED_SOLIC_POSIC.VAL_PREC_NETO_TOTA_LOCA, 0)
                                    )
                     WHEN 'B'
                        THEN DECODE (NVL (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0),
                                     0, 0,
                                     NVL (PED_SOLIC_POSIC.VAL_PREC_FACT_TOTA_LOCA, 0)
                                    )
                  END
                 ) AS VVCD,
                 (CASE FAC_TIPO_DOCUM_LEGAL.IND_TIPO_DOCU
                     WHEN 'B'
                        THEN 0
                     WHEN 'F'
                        THEN DECODE (NVL (PED_SOLIC_POSIC.VAL_PREC_CATA_UNIT_LOCA, 0),
                                     0, 0,
                                     NVL (PED_SOLIC_POSIC.VAL_IMPO_IMPU_TOTA_LOCA, 0)
                                    )
                  END
                 ) AS TOTIMP,
                 0 AS FLETE,
                 NVL (FAC_TIPO_DOCUM_LEGAL.IND_TIPO_DOCU, ' ') AS TIPDOC,
                 PED_SOLIC_CABEC.OID_SOLI_CABE,
                 PED_SOLIC_CABEC.SOCA_OID_SOLI_CABE
            FROM PED_SOLIC_CABEC,
                 SEG_PAIS,
                 SEG_MARCA,
                 SEG_CANAL,
                 CRA_PERIO,
                 SEG_PERIO_CORPO,
                 SEG_TIPO_PERIO,
                 PED_ESTAD_SOLIC,
                 FAC_TIPO_DOCUM,
                 FAC_TIPO_DOCUM_LEGAL,
                 PED_TIPO_SOLIC,
                 PED_TIPO_SOLIC_PAIS,
                 PED_SOLIC_POSIC,
                 PED_ESTAD_POSIC,
                 MAE_PRODU,
                 SEG_MARCA_PRODU,
                 MAE_CLIEN
           WHERE (    (SEG_PAIS.COD_PAIS = PSCODIGOPAIS)
                  AND (SEG_MARCA.COD_MARC = PSCODIGOMARCA)
                  AND (SEG_CANAL.COD_CANA = PSCODIGOCANAL)
                  AND (SEG_PERIO_CORPO.COD_PERI = PSCODIGOPERIODO)
                  AND (PED_SOLIC_CABEC.FEC_FACT = TO_DATE (PSFECHAFACTURACION, 'DD/MM/YYYY'))
                  AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
                  AND ((COD_TIPO_SOLI LIKE 'SE%') OR (COD_TIPO_SOLI = 'SOC'))
      AND (PED_SOLIC_POSIC.VAL_CODI_VENT IS NOT NULL)
                  AND (PED_SOLIC_CABEC.IND_PEDI_PRUE = 0)
                  AND (PED_ESTAD_SOLIC.COD_ESTA_SOLI <> 'AN')
                  AND (PED_ESTAD_POSIC.COD_ESTA_POSI <> 'AN')
                  AND (PED_TIPO_SOLIC.IND_DEVO = 0)
                  AND (PED_TIPO_SOLIC.IND_ANUL = 0)
                  AND (FAC_TIPO_DOCUM_LEGAL.IND_ACTI_DOCU = 1)
                  AND (PED_SOLIC_CABEC.PAIS_OID_PAIS = SEG_PAIS.OID_PAIS)
                  AND (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
                  AND (CRA_PERIO.MARC_OID_MARC = SEG_MARCA.OID_MARC)
                  AND (CRA_PERIO.CANA_OID_CANA = SEG_CANAL.OID_CANA)
                  AND (CRA_PERIO.PERI_OID_PERI = SEG_PERIO_CORPO.OID_PERI)
                  AND (SEG_CANAL.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                  AND (SEG_PERIO_CORPO.TIPE_OID_TIPO_PERI = SEG_TIPO_PERIO.OID_TIPO_PERI)
                  AND (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = PED_ESTAD_SOLIC.OID_ESTA_SOLI)
                  AND (PED_SOLIC_CABEC.TIDO_OID_TIPO_DOCU = FAC_TIPO_DOCUM.OID_TIPO_DOCU)
                  AND (FAC_TIPO_DOCUM.COD_TIPO_DOCU = FAC_TIPO_DOCUM_LEGAL.COD_TIPO_DOLE)
                  AND (PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS = PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS)
                  AND (PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI = PED_TIPO_SOLIC.OID_TIPO_SOLI)
                  AND (PED_SOLIC_CABEC.OID_SOLI_CABE = PED_SOLIC_POSIC.SOCA_OID_SOLI_CABE)
                  AND (PED_SOLIC_POSIC.ESPO_OID_ESTA_POSI = PED_ESTAD_POSIC.OID_ESTA_POSI)
                  AND (PED_SOLIC_POSIC.PROD_OID_PROD = MAE_PRODU.OID_PROD)
                  AND (MAE_PRODU.MAPR_OID_MARC_PROD = SEG_MARCA_PRODU.OID_MARC_PROD)
                  AND (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
                 )) CABECERA,
         PED_SOLIC_CABEC
   WHERE CABECERA.SOCA_OID_SOLI_CABE = PED_SOLIC_CABEC.OID_SOLI_CABE
GROUP BY CABECERA.CODPAIS,
         PED_SOLIC_CABEC.VAL_NUME_SOLI,
         CABECERA.CODVEN,
         CABECERA.CODPRO,
         CABECERA.CODCLI,
         CABECERA.TIPDOC
ORDER BY CODPAIS,
         CODCLI,
         NUMFAC;

  TYPE interfazRec IS RECORD
  (
      codigoPais   SEG_PAIS.COD_PAIS%TYPE,
      valNumeSoli  PED_SOLIC_CABEC.VAL_NUME_SOLI%TYPE,
      codigoVen    PED_SOLIC_POSIC.VAL_CODI_VENT%TYPE,
      codigoPro    MAE_PRODU.COD_SAP%TYPE,
      codigoCli    MAE_CLIEN.COD_CLIE%TYPE,
      vvcu         NUMBER,
      vvcud        NUMBER,
      vvcd         NUMBER,
      totalImporte NUMBER,
      flete        NUMBER,
      tipoDocum    FAC_TIPO_DOCUM_LEGAL.IND_TIPO_DOCU%TYPE
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000;
  v_handle            UTL_FILE.FILE_TYPE;
  W_DESC              VARCHAR2(200);
  lsLinea             VARCHAR2(1000);
  lsLineaCabecera     VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

BEGIN
  /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

  /* Generando Archivo de Texto (Detalle) */
  OPEN c_interfaz;
  LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=
                 interfazRecord(x).codigoPais                          ||';'||
                 to_char(interfazRecord(x).valNumeSoli,'9999999990')   ||';'||
                 interfazRecord(x).codigoVen                           ||';'||
                 interfazRecord(x).codigoPro                           ||';'||
                 interfazRecord(x).codigoCli                           ||';'||
                 to_char(interfazRecord(x).vvcu,'99999990.99')         ||';'||
                 to_char(interfazRecord(x).vvcud,'99999990.99')        ||';'||
                 to_char(interfazRecord(x).vvcd,'99999990.99')         ||';'||
                 to_char(interfazRecord(x).totalImporte,'99999990.99') ||';'||
                 to_char(interfazRecord(x).flete,'99999990.99')        ||';'||
                 interfazRecord(x).tipoDocum     ;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;
  UTL_FILE.FCLOSE(V_HANDLE);

  /* Procedimiento final para generar interfaz */
  GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

  RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_COMPL_FACTU_VTADI: '||ls_sqlerrm);
END INT_PR_RET_COMPL_FACTU_VTADI;

/**************************************************************************
Descripcion        : Devuelve Indicador Descuento Especifico del Producto
Fecha Creacion     : 01/03/2007
Autor              : Carlos Bazalar
***************************************************************************/
FUNCTION INT_FN_RET_VALOR_ESPEC_DSCTO(
    psCodigoPeriodo            VARCHAR2,
    psCodigoTipoCliente        VARCHAR2,
    psCodigoSubtipoCliente     VARCHAR2,
    psCodigoTipoOferta         VARCHAR2,
    psCodigoNegocio            VARCHAR2,
    psCodigoUnidadNegocio      VARCHAR2,
    psCodigoMarcaProducto      VARCHAR2,
    pnComisionable             NUMBER
)
RETURN VARCHAR2
IS
  regRegistro    GEN_PKG_GENER.tRegObtenerDsctoVarios;
BEGIN
  regRegistro := GEN_PKG_GENER.GEN_FN_DEVUE_DSCTO_VARIO(
    psCodigoPeriodo           ,
    psCodigoTipoCliente       ,
    psCodigoSubtipoCliente    ,
    psCodigoTipoOferta        ,
    psCodigoNegocio           ,
    psCodigoUnidadNegocio     ,
    psCodigoMarcaProducto     ,
    pnComisionable            );
  RETURN regRegistro.valorEspeciDscto;

END INT_FN_RET_VALOR_ESPEC_DSCTO;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar Informacion
                       Venta
  Fecha Creacion     : 10/05/2010
  Autor              : Jose Luis Rodriguez
 ***************************************************************************/
PROCEDURE INT_PR_RET_INFOR_VENTA(
   psCodigoPais       VARCHAR2,
   psCodigosistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2
)
IS
  CURSOR c_interfaz (vnIdPais NUMBER,vnIdPeriodo NUMBER) IS
    SELECT A.CodPais AS COD_PAIS,
           A.CodZona AS COD_ZONA,
           A.CodSAP  AS COD_SAP,
           A.CodMarca AS COD_MARCA,
           A.CodNegocio AS COD_NEGOCIO,
           gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(A.OidPeriodo),
           A.CodTipoOferta AS COD_TIPO_OFERTA,
           TO_CHAR(A.FecFacturacion, 'YYYYMMDD') AS FEC_FACTURACION,
           SUM(A.UnidadesAtendidas) AS NUM_UNID_ATEN,
           SUM(A.UnidadesFaltantes) AS NUM_UNID_FALT,
           0 AS NUM_UNID_PROY,
           SUM(A.VentaNeta) AS VAL_VENT_NETA,
           SUM(A.VentaNetaFaltante) AS VAL_VENT_NETA_FALT
    FROM ( SELECT sp.cod_pais  CodPais,
                  zon.cod_zona CodZona,
                  mp.cod_sap   CodSAP,
                  smp.cod_marc_prod CodMarca,
                  mn.cod_nego  CodNegocio,
                  psc.perd_oid_peri OidPeriodo,
                  pto.cod_tipo_ofer CodTipoOferta,
                  psc.fec_fact FecFacturacion,
                  psp.num_unid_aten UnidadesAtendidas,
                  psp.num_unid_dema_real - psp.num_unid_aten UnidadesFaltantes,
                  decode(psp.val_prec_cata_unit_loca, 0, 0,psp.val_prec_neto_unit_loca) * psp.num_unid_aten VentaNeta,
                  decode(psp.val_prec_cata_unit_loca, 0, 0, psp.val_prec_neto_unit_loca)*(psp.num_unid_dema_real - psp.num_unid_aten) VentaNetaFaltante
             FROM ped_solic_cabec psc,
                  ped_solic_posic psp,
                  mae_produ mp,
                  mae_negoc mn,
                  pre_ofert_detal pod,
                  pre_tipo_ofert pto,
                  zon_zona zon,
                  seg_marca_produ smp,
                  seg_pais sp
            WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
              AND psc.pais_oid_pais = sp.oid_pais
              AND psc.zzon_oid_zona = zon.oid_zona
              AND psp.prod_oid_prod = mp.oid_prod
              AND mn.oid_nego = mp.nego_oid_nego
              AND smp.oid_marc_prod = mp.mapr_oid_marc_prod
              AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer
              AND pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer
              AND psp.num_unid_aten >= 0
              AND psp.espo_oid_esta_posi != 2
              AND psc.ind_oc = 1
              AND psp.num_unid_dema_real - psp.num_unid_aten > 0 -- solo unidades faltantes
              AND psc.perd_oid_peri = vnIdPeriodo
              AND psc.fec_fact = to_date(psFechaFacturacion, 'DD/MM/YYYY')
              AND psc.pais_oid_pais = vnIdPais ) A
  GROUP BY A.CodPais, A.CodZona, A.CodSAP, A.CodMarca,A.CodNegocio, A.OidPeriodo, A.CodTipoOferta, A.FecFacturacion
  ORDER BY A.CodZona, A.CodSAP, A.CodTipoOferta;

  TYPE interfazRec IS RECORD
  (
      codigoPais             SEG_PAIS.COD_PAIS%TYPE,
      codigoZona             ZON_ZONA.COD_ZONA%TYPE,
      codigoSap              MAE_PRODU.COD_SAP%TYPE,
      codigoMarca            SEG_MARCA_PRODU.COD_MARC_PROD%TYPE,
      codigoNegocio          MAE_NEGOC.COD_NEGO%TYPE,
      codPeriodo             SEG_PERIO_CORPO.COD_PERI%TYPE,
      codigoTipoOferta       PRE_TIPO_OFERT.COD_TIPO_OFER%TYPE,
      fechaFacturacion       VARCHAR2(8),
      numUnidadesAtendidas   PED_SOLIC_POSIC.NUM_UNID_ATEN%TYPE,
      numUnidadesFaltantes   PED_SOLIC_POSIC.NUM_UNID_ATEN%TYPE,
      numUnidadesProyectadas NUMBER,
      valVentaNeta           PED_SOLIC_POSIC.VAL_PREC_NETO_UNIT_LOCA%TYPE,
      valVentaNetaFaltante   PED_SOLIC_POSIC.VAL_PREC_NETO_UNIT_LOCA%TYPE
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);

  lnIdPais      NUMBER;
  lnIdPeriodo   NUMBER;

BEGIN

  lbAbrirUtlFile := TRUE;
  lnIdPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnIdPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

  /* Procedimiento inicial para generar interfaz */
  IF lbAbrirUtlFile THEN
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                                           psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
    lbAbrirUtlFile := FALSE;
  END IF;

  OPEN c_interfaz(lnIdPais, lnIdPeriodo);
  LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=
                 interfazRecord(x).codigoPais                          ||';'||
                 interfazRecord(x).codigoZona                          ||';'||
                 interfazRecord(x).codigoSap                           ||';'||
                 interfazRecord(x).codigoMarca                         ||';'||
                 interfazRecord(x).codigoNegocio                       ||';'||
                 interfazRecord(x).codPeriodo                          ||';'||
                 interfazRecord(x).codigoTipoOferta                    ||';'||
                 interfazRecord(x).fechaFacturacion                    ||';'||
                 interfazRecord(x).numUnidadesAtendidas                ||';'||
                 interfazRecord(x).numUnidadesFaltantes                ||';'||
                 interfazRecord(x).numUnidadesProyectadas              ||';'||
                 interfazRecord(x).valVentaNeta                        ||';'||
                 interfazRecord(x).valVentaNetaFaltante;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_INFOR_VENTA: '||ls_sqlerrm);
END INT_PR_RET_INFOR_VENTA;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Detalle de Facturas de Venta Directa
  Fecha Creacion     : 06/09/2010
  Autor              : Dennys Oliva Iriarte
 ***************************************************************************/
PROCEDURE INT_PR_RET_DETAL_FACTU_VTADI(psCodigoPais       VARCHAR2,
                                       psCodigosistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2)

IS
  CURSOR c_interfaz(v_indretfe VARCHAR2,v_indretfe2 VARCHAR2) IS
      select sp.cod_pais,
             decode(v_indretfe,'S',cab.val_seri_docu_lega || '-','') || cab.num_docu_cont_inte,
             decode(v_indretfe2, 'S', (select num_docu_iden from mae_clien_ident x where x.VAL_IDEN_DOCU_PRIN=1 and x.clie_oid_clie=mc.oid_clie and rownum=1), mc.cod_clie) cod_clie,
             to_char(cab.fec_fact, 'yyyymmdd') fec_fact,
             mp.cod_sap,
             psp.val_codi_vent,
             decode(v_indretfe,'S',mcdi.ind_impr_docu,smp.cod_marc_prod) cod_marc_prod,
             det.num_unid_aten,
             decode(det.val_prec_cata_unit_loca, 0,0,det.val_prec_fact_unit_loca * det.num_unid_aten) as val_tota,
             decode(tdl.ind_tipo_docu, 'B', 0, 1) ind_tipo_docu,
             decode(v_indretfe,'S',con.val_nume_soli,smp.cod_marc_prod) as ubigeo,
             spc.cod_peri
        from fac_docum_conta_cabec cab,
             fac_docum_conta_linea det,
             fac_tipo_docum        ftd,
             mae_produ             mp,
             ped_solic_cabec       con,
             ped_solic_posic       psp,
             mae_clien             mc,
             mae_clien_datos_adici mcdi,
             seg_pais              sp,
             seg_marca_produ       smp,
             fac_tipo_docum_legal  tdl,
             cra_perio             cp,
             seg_perio_corpo       spc,
             zon_valor_estru_geopo veg
       where cab.soca_oid_soli_cabe = con.oid_soli_cabe
         and con.clie_oid_clie = mc.oid_clie
         and mcdi.clie_oid_clie = mc.oid_clie
         and con.pais_oid_pais = sp.oid_pais
         and cab.oid_cabe = det.dcca_oid_cabe
         and det.prod_oid_prod = mp.oid_prod
         and mp.mapr_oid_marc_prod = smp.oid_marc_prod
         and det.sopo_oid_soli_posi = psp.oid_soli_posi
         and con.perd_oid_peri = cp.oid_peri
         and cp.peri_oid_peri = spc.oid_peri
         and spc.cod_peri = psCodigoPeriodo
         and det.num_unid_aten > 0
         and psp.val_codi_vent is not null -- no se consideran los premios
         and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
         and exists (select null
                       from int_lar_tipo_solici_pedido_dis l
                      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
         and cab.tido_oid_tipo_docu = ftd.oid_tipo_docu
         and ftd.cod_tipo_docu = tdl.cod_tipo_dole
         and tdl.ind_acti_docu = 1
         and con.vepo_oid_valo_estr_geop = veg.oid_valo_estr_geop
       order by cab.tido_oid_tipo_docu, cab.num_docu_cont_inte;

  TYPE interfazRec IS RECORD (
      cod_pais               seg_pais.cod_pais%type,
      num_docu_cont_inte     varchar2(20),
      cod_clie               mae_clien.cod_clie%type,
      fec_fact				       varchar2(8),--fac_docum_conta_cabec.fec_fact%type,
      cod_sap                mae_produ.cod_sap%type,
      val_codi_vent			     ped_solic_posic.val_codi_vent%type,
      cod_marc_prod          varchar2(12),
      num_unid_aten          fac_docum_conta_linea.num_unid_aten%type,
      val_tota               fac_docum_conta_linea.val_prec_cata_unit_loca%type,
      ind_tipo_docu			     fac_tipo_docum_legal.ind_tipo_docu%type,
      cod_ubig               varchar2(12),
      cod_peri               seg_perio_corpo.cod_peri%type
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

  lsindretfe VARCHAR2(10):= nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'IND_RET_FE'),'N');
  lsindretfe2 VARCHAR2(10):= nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'IND_RET_FE2'),'N');
  lsindretfe3 VARCHAR2(10):= nvl(sto_pkg_gener.sto_fn_obten_param_ocr(psCodigoPais,'IND_RET_FE3'),'N');


BEGIN

    if lsindretfe3='S' then
      INT_PR_RET_DETAL_FACTU_VD_CH(psCodigoPais,psCodigosistema,
                                       psCodigoInterfaz,
                                       psNombreArchivo,
                                       psCodigoPeriodo,
                                       psFechaFacturacion);
      return;
    end if;


  /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);



  /* Generando Archivo de Texto (Detalle) */
  OPEN c_interfaz(lsindretfe,lsindretfe2);
  LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea := interfazRecord(x).cod_pais                         ||';'||
                         interfazRecord(x).num_docu_cont_inte               ||';'||
                         interfazRecord(x).cod_clie                         ||';'||
                         interfazRecord(x).fec_fact			                    ||';'||
                         interfazRecord(x).cod_sap                          ||';'||
                         interfazRecord(x).val_codi_vent		                ||';'||
                         interfazRecord(x).cod_marc_prod                    ||';'||
                         to_char(interfazRecord(x).num_unid_aten,'99990')   ||';'||
                         to_char(interfazRecord(x).val_tota,'999999990.99') ||';'||
                         interfazRecord(x).ind_tipo_docu		                ||';'||
                         interfazRecord(x).cod_ubig                         ||';'||
                         interfazRecord(x).cod_peri;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;
  UTL_FILE.FCLOSE(V_HANDLE);

  /* Procedimiento final para generar interfaz */
  GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

  RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_DETAL_FACTU_VTADI: '||ls_sqlerrm);
END INT_PR_RET_DETAL_FACTU_VTADI;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Detalle de Facturas de Venta Directa para Chile
  Fecha Creacion     : 06/09/2010
  Autor              : Dennys Oliva Iriarte
 ***************************************************************************/
PROCEDURE INT_PR_RET_DETAL_FACTU_VD_CH(psCodigoPais       VARCHAR2,
                                       psCodigosistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2)

IS
  CURSOR c_interfaz IS
select     cod_pais,
           num_docu_cont_inte,
            cod_clie,
              fec_fact,
             cod_sap,
             val_codi_vent,
             cod_marc_prod,
             num_unid_aten,
             val_tota,
             ind_tipo_docu,
             ubigeo,
             cod_peri
 from
(
      select sp.cod_pais,
             x.val_foli num_docu_cont_inte,
             (select num_docu_iden from mae_clien_ident x where x.VAL_IDEN_DOCU_PRIN=1 and x.clie_oid_clie=mc.oid_clie and rownum=1) cod_clie,
             to_char(cab.fec_fact, 'yyyymmdd') fec_fact,
             mp.cod_sap,
             psp.val_codi_vent,
             smp.cod_marc_prod,
             1 num_unid_aten,
             decode(det.val_prec_cata_unit_loca, 0,0,det.val_prec_cata_unit_loca) as val_tota,
             decode(tdl.ind_tipo_docu, 'B', 0, 1) ind_tipo_docu,
             con.val_nume_soli as ubigeo,
             spc.cod_peri
        from fac_docum_conta_cabec cab,
             fac_docum_conta_linea det,
             fac_tipo_docum        ftd,
             mae_produ             mp,
             ped_solic_cabec       con,
             ped_solic_posic       psp,
             mae_clien             mc,
             mae_clien_datos_adici mcdi,
             seg_pais              sp,
             seg_marca_produ       smp,
             fac_tipo_docum_legal  tdl,
             cra_perio             cp,
             seg_perio_corpo       spc,
             zon_valor_estru_geopo veg,
             PED_BOLET_ELECT_histo x
       where cab.soca_oid_soli_cabe = con.oid_soli_cabe
         and con.clie_oid_clie = mc.oid_clie
         and mcdi.clie_oid_clie = mc.oid_clie
         and con.pais_oid_pais = sp.oid_pais
         and cab.oid_cabe = det.dcca_oid_cabe
         and det.prod_oid_prod = mp.oid_prod
         and mp.mapr_oid_marc_prod = smp.oid_marc_prod
         and det.sopo_oid_soli_posi = psp.oid_soli_posi
         and con.perd_oid_peri = cp.oid_peri
         and cp.peri_oid_peri = spc.oid_peri
         and spc.cod_peri = psCodigoPeriodo
         and x.val_nume_soli=con.val_nume_soli
         and x.val_codi_vent=psp.val_codi_vent
         and det.num_unid_aten > 0
         and psp.val_codi_vent is not null -- no se consideran los premios
         and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
         and exists (select null
                       from int_lar_tipo_solici_pedido_dis l
                      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
         and cab.tido_oid_tipo_docu = ftd.oid_tipo_docu
         and ftd.cod_tipo_docu = tdl.cod_tipo_dole
         and tdl.ind_acti_docu = 1
         and con.vepo_oid_valo_estr_geop = veg.oid_valo_estr_geop
         union all
      select sp.cod_pais,
             NULL num_docu_cont_inte,
             (select num_docu_iden from mae_clien_ident x where x.VAL_IDEN_DOCU_PRIN=1 and x.clie_oid_clie=mc.oid_clie and rownum=1) cod_clie,
             to_char(cab.fec_fact, 'yyyymmdd') fec_fact,
             mp.cod_sap,
             psp.val_codi_vent,
             smp.cod_marc_prod,
             det.num_unid_aten,
             decode(det.val_prec_cata_unit_loca, 0,0,det.val_prec_cata_unit_loca) as val_tota,
             decode(tdl.ind_tipo_docu, 'B', 0, 1) ind_tipo_docu,
             con.val_nume_soli as ubigeo,
             spc.cod_peri
        from fac_docum_conta_cabec cab,
             fac_docum_conta_linea det,
             fac_tipo_docum        ftd,
             mae_produ             mp,
             ped_solic_cabec       con,
             ped_solic_posic       psp,
             mae_clien             mc,
             mae_clien_datos_adici mcdi,
             seg_pais              sp,
             seg_marca_produ       smp,
             fac_tipo_docum_legal  tdl,
             cra_perio             cp,
             seg_perio_corpo       spc,
             zon_valor_estru_geopo veg
       where cab.soca_oid_soli_cabe = con.oid_soli_cabe
         and con.clie_oid_clie = mc.oid_clie
         and mcdi.clie_oid_clie = mc.oid_clie
         and con.pais_oid_pais = sp.oid_pais
         and cab.oid_cabe = det.dcca_oid_cabe
         and det.prod_oid_prod = mp.oid_prod
         and mp.mapr_oid_marc_prod = smp.oid_marc_prod
         and det.sopo_oid_soli_posi = psp.oid_soli_posi
         and con.perd_oid_peri = cp.oid_peri
         and cp.peri_oid_peri = spc.oid_peri
         and spc.cod_peri = psCodigoPeriodo
         and det.num_unid_aten > 0
         and psp.val_codi_vent is not null -- no se consideran los premios
         and con.fec_fact = to_date(psFechaFacturacion, 'dd/mm/yyyy')
         and psp.val_prec_cata_unit_loca=0
         and not exists (select 1 from PED_BOLET_ELECT_histo where val_nume_soli=con.val_nume_soli and val_codi_vent=psp.val_codi_vent)
         and exists (select null
                       from int_lar_tipo_solici_pedido_dis l
                      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
         and cab.tido_oid_tipo_docu = ftd.oid_tipo_docu
         and ftd.cod_tipo_docu = tdl.cod_tipo_dole
         and tdl.ind_acti_docu = 1
         and con.vepo_oid_valo_estr_geop = veg.oid_valo_estr_geop
         )
         order by ubigeo,VAL_CODI_VENT, num_docu_cont_inte
         ;

  TYPE interfazRec IS RECORD (
      cod_pais               seg_pais.cod_pais%type,
      num_docu_cont_inte     varchar2(20),
      cod_clie               mae_clien.cod_clie%type,
      fec_fact				       varchar2(8),--fac_docum_conta_cabec.fec_fact%type,
      cod_sap                mae_produ.cod_sap%type,
      val_codi_vent			     ped_solic_posic.val_codi_vent%type,
      cod_marc_prod          varchar2(12),
      num_unid_aten          fac_docum_conta_linea.num_unid_aten%type,
      val_tota               fac_docum_conta_linea.val_prec_cata_unit_loca%type,
      ind_tipo_docu			     fac_tipo_docum_legal.ind_tipo_docu%type,
      cod_ubig               varchar2(12),
      cod_peri               seg_perio_corpo.cod_peri%type
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);



BEGIN
  /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

  /* Generando Archivo de Texto (Detalle) */
  OPEN c_interfaz();
  LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea := interfazRecord(x).cod_pais                         ||';'||
                         interfazRecord(x).num_docu_cont_inte               ||';'||
                         interfazRecord(x).cod_clie                         ||';'||
                         interfazRecord(x).fec_fact			                    ||';'||
                         interfazRecord(x).cod_sap                          ||';'||
                         interfazRecord(x).val_codi_vent		                ||';'||
                         interfazRecord(x).cod_marc_prod                    ||';'||
                         to_char(interfazRecord(x).num_unid_aten,'99990')   ||';'||
                         to_char(interfazRecord(x).val_tota,'999999990.99') ||';'||
                         interfazRecord(x).ind_tipo_docu		                ||';'||
                         interfazRecord(x).cod_ubig                         ||';'||
                         interfazRecord(x).cod_peri;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;
  UTL_FILE.FCLOSE(V_HANDLE);

  /* Procedimiento final para generar interfaz */
  GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

  RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_DETAL_FACTU_VTADI: '||ls_sqlerrm);
END INT_PR_RET_DETAL_FACTU_VD_CH;

/**************************************************************************
  Descripcion        : Genera el archivo para la Interfaz Enviar
                       Detalle de Facturas de Venta Directa
  Fecha Creacion     : 06/09/2010
  Autor              : Dennys Oliva Iriarte
 ***************************************************************************/
PROCEDURE INT_PR_RET_DETAL_FACTU_VTADIXX(psCodigoPais       VARCHAR2,
                                       psCodigosistema    VARCHAR2,
                                       psCodigoInterfaz   VARCHAR2,
                                       psNombreArchivo    VARCHAR2,
                                       psCodigoPeriodo    VARCHAR2,
                                       psFechaFacturacion VARCHAR2)

IS


  CURSOR c_interfaz(v_indretfe VARCHAR2,v_indretfe2 VARCHAR2) IS
           select sp.cod_pais,
             decode(v_indretfe,'S',cab.val_seri_docu_lega || '-','') || cab.num_docu_cont_inte,
             decode(v_indretfe2, 'S', (select num_docu_iden from mae_clien_ident x where x.VAL_IDEN_DOCU_PRIN=1 and x.clie_oid_clie=mc.oid_clie and rownum=1), mc.cod_clie) cod_clie,
             to_char(cab.fec_fact, 'yyyymmdd') fec_fact,
             mp.cod_sap,
             psp.val_codi_vent,
             decode(v_indretfe,'S',mcdi.ind_impr_docu,smp.cod_marc_prod) cod_marc_prod,
             det.num_unid_aten,
             decode(det.val_prec_cata_unit_loca, 0,0,det.val_prec_fact_unit_loca * det.num_unid_aten) as val_tota,
             decode(tdl.ind_tipo_docu, 'B', 0, 1) ind_tipo_docu,
             decode(v_indretfe,'S',con.val_nume_soli,smp.cod_marc_prod) as ubigeo,
             spc.cod_peri,
       (  SELECT SUM(nvl(lin.num_unid_recl,0))
          FROM rec_linea_opera_recla       lin
         WHERE lin.timo_oid_tipo_movi = 2
           AND lin.sopo_oid_soli_posi = psp.oid_soli_posi) uni_recl      ,
        (        SELECT nvl(SUM(nvl(det2.can_vent_devu,0)),0)
                  FROM int_solic_conso_poven_detal det2,
                       sto_docum_digit             dig
                 WHERE det2.cod_pais = psCodigoPais
                   AND det2.cod_clie = mc.cod_clie
                   AND det2.oid_soli_posi_devu = psp.oid_soli_posi
                   AND dig.sec_nume_docu = det2.sec_nume_docu
                   AND dig.ind_envi = 0
                   AND dig.ind_rech = 0       ) unid_pend,
             con.val_nume_soli,
             con.val_tota_paga_loca,
      ( select nvl(sum(f.VAL_TOTA_PAGA_LOCA),0) MontoDevolucion
         from ped_solic_cabec a,
              rec_cabec_recla b,
              rec_opera_recla c,
              rec_solic_opera d,
              ped_solic_cabec e,
              ped_solic_cabec f,
              ped_tipo_solic_pais tsp,
              ped_tipo_solic ts
        where a.VAL_NUME_SOLI = con.val_nume_soli
        and a.OID_SOLI_CABE = b.SOCA_OID_SOLI_CABE
        and b.oid_cabe_recl = c.CARE_OID_CABE_RECL
        and c.OID_OPER_RECL  = d.opre_oid_oper_recl
        and tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
        and oid_tipo_soli_pais = d.TSPA_OID_TIPO_SOLI_PAIS
        and cod_tipo_soli in ('SDDN')
        and d.SOCA_OID_SOLI_CABE = e.OID_SOLI_CABE
        and e.SOCA_OID_SOLI_CABE = f.OID_SOLI_CABE          ) montodev   ,
        REC_PKG_PROCE.REC_FN_PORCE_MONTO_DEVOL(mc.oid_clie,psCodigoPais,psCodigoPeriodo) pordev
        from fac_docum_conta_cabec cab,
             fac_docum_conta_linea det,
             fac_tipo_docum        ftd,
             mae_produ             mp,
             ped_solic_cabec       con,
             ped_solic_posic       psp,
             mae_clien             mc,
             mae_clien_datos_adici mcdi,
             seg_pais              sp,
             seg_marca_produ       smp,
             fac_tipo_docum_legal  tdl,
             cra_perio             cp,
             seg_perio_corpo       spc,
             zon_valor_estru_geopo veg
       where cab.soca_oid_soli_cabe = con.oid_soli_cabe
         and con.clie_oid_clie = mc.oid_clie
         and mcdi.clie_oid_clie = mc.oid_clie
         and con.pais_oid_pais = sp.oid_pais
         and cab.oid_cabe = det.dcca_oid_cabe
         and det.prod_oid_prod = mp.oid_prod
         and mp.mapr_oid_marc_prod = smp.oid_marc_prod
         and det.sopo_oid_soli_posi = psp.oid_soli_posi
         and con.perd_oid_peri = cp.oid_peri
         and cp.peri_oid_peri = spc.oid_peri
         and det.num_unid_aten > 0
         and psp.val_codi_vent is not null -- no se consideran los premios
         and con.val_nume_soli = '1507845293'  ----nro doc
         --and decode(v_indretfe,'S',cab.val_seri_docu_lega,'B065') = 'B065'
         --and cab.num_docu_cont_inte = '5834627'
         and exists (select null
                       from int_lar_tipo_solici_pedido_dis l
                      where l.tspa_oid_tipo_soli_pais = con.tspa_oid_tipo_soli_pais)
         and cab.tido_oid_tipo_docu = ftd.oid_tipo_docu
         and ftd.cod_tipo_docu = tdl.cod_tipo_dole
         and tdl.ind_acti_docu = 1
         and con.vepo_oid_valo_estr_geop = veg.oid_valo_estr_geop
       ;


  TYPE interfazRec IS RECORD (
      cod_pais               seg_pais.cod_pais%type,
      num_docu_cont_inte     varchar2(20),
      cod_clie               mae_clien.cod_clie%type,
      fec_fact				       varchar2(8),--fac_docum_conta_cabec.fec_fact%type,
      cod_sap                mae_produ.cod_sap%type,
      val_codi_vent			     ped_solic_posic.val_codi_vent%type,
      cod_marc_prod          varchar2(12),
      num_unid_aten          fac_docum_conta_linea.num_unid_aten%type,
      val_tota               fac_docum_conta_linea.val_prec_cata_unit_loca%type,
      ind_tipo_docu			     fac_tipo_docum_legal.ind_tipo_docu%type,
      cod_ubig               varchar2(12),
      cod_peri               seg_perio_corpo.cod_peri%type,
      NUM_UNID_RECLA       VARCHAR2(20),
      NUM_UNID_OTROS       VARCHAR2(20),
      NROPEDIDO         VARCHAR2(20),
      MONTO_PEDIDO       VARCHAR2(20),
      MONTO_DEVUELTO       VARCHAR2(20),
      PORCENTAJE_DEVOL     VARCHAR2(20)
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

  lsCodigoPais  bas_ctrl_fact.cod_pais%type;

  lsindretfe2 VARCHAR2(10);

  lsindretfe VARCHAR2(10);




BEGIN

  select cod_pais into lsCodigoPais from bas_ctrl_fact where rownum=1;

  lsindretfe2 := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lsCodigoPais,'IND_RET_FE2'),'N');
  lsindretfe := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lsCodigoPais,'IND_RET_FE'),'N');



  /* Generando Archivo de Texto (Detalle) */
  OPEN c_interfaz(lsindretfe,lsindretfe2);
  LOOP
       FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea := interfazRecord(x).cod_pais                         ||';'||
                         interfazRecord(x).num_docu_cont_inte               ||';'||
                         interfazRecord(x).cod_clie                         ||';'||
                         interfazRecord(x).fec_fact			                    ||';'||
                         interfazRecord(x).cod_sap                          ||';'||
                         interfazRecord(x).val_codi_vent		                ||';'||
                         interfazRecord(x).cod_marc_prod                    ||';'||
                         to_char(interfazRecord(x).num_unid_aten,'99990')   ||';'||
                         to_char(interfazRecord(x).val_tota,'999999990.99') ||';'||
                         interfazRecord(x).ind_tipo_docu		                ||';'||
                         interfazRecord(x).cod_ubig                         ||';'||
                         interfazRecord(x).cod_peri;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
       END IF;
       EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  CLOSE c_interfaz;

  RETURN;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_DETAL_FACTU_VTADIXX: '||ls_sqlerrm);
END INT_PR_RET_DETAL_FACTU_VTADIXX;


/*************************************************************************************
Descripcion       : Interfaz que env�a los cierres de Zona, Region y Campa�a (RET-15)
Fecha Creacion    : 07/01/2014
Fecha Modificacion: 11/09/2014
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoMarca    : Codigo Marca
 psCodigoCanal    : Codgio Canal
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
 psCodigoPeriodo  : A�o Campa�a
 pdFechaFacturacion: Fecha Facturaci�n

Autor: CSVD - FFVV
*************************************************************************************/
PROCEDURE INT_PR_RET_ENVIO_CIERR
(
   psCodigoPais       VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2
)
IS
   CURSOR c_cierre IS

     SELECT cod_pais,
            cam_proc,
            fec_cier,
            tip_cier,
            cod_regi,
            cod_zona,
            cod_camp
       FROM fac_progr_cierr
      WHERE cam_proc = psCodigoPeriodo
        AND est_cier = 'P'
      ORDER BY fec_cier, tip_cier DESC;

   TYPE tRecCierre IS RECORD
     (
       codigoPais         fac_progr_cierr.cod_pais%TYPE,
       campanaProceso     fac_progr_cierr.cam_proc%TYPE,
       fechaCierre        fac_progr_cierr.fec_cier%TYPE,
       tipoCierre         fac_progr_cierr.tip_cier%TYPE,
       codigoRegion       fac_progr_cierr.cod_regi%TYPE,
       codigoZona         fac_progr_cierr.cod_zona%TYPE,
       codigoCampana      fac_progr_cierr.cod_camp%TYPE
     );

   TYPE tTabtRecCierre  IS TABLE OF tRecCierre ;
   interfazRecord tTabtRecCierre;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_hANDle            UTL_FILE.FILE_TYPE;
   lbAbrirUtlFile      BOOLEAN;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

BEGIN

    lbAbrirUtlFile := TRUE;

    OPEN c_cierre;
    LOOP
       FETCH c_cierre BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

    IF lbAbrirUtlFile THEN
     GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigoSistema, psCodigoInterfaz,
                                            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
     lbAbrirUtlFile := FALSE;
    END IF;

    IF interfazRecord.COUNT > 0 THEN
      FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

        lsLinea := interfazRecord(x).codigoPais        ||';'||
                   interfazRecord(x).campanaProceso    ||';'||
                   TO_CHAR(interfazRecord(x).fechaCierre,'YYYYMMDD') ||';'||
                   interfazRecord(x).tipoCierre        ||';'||
                   interfazRecord(x).codigoRegion      ||';'||
                   interfazRecord(x).codigoZona        ||';'||
                   interfazRecord(x).codigoCampana;

        UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
      END LOOP;
    END IF;

       EXIT WHEN c_cierre%NOTFOUND;
    END LOOP;
    CLOSE c_cierre;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_ENVIO_CIERR: '||ls_sqlerrm);
END INT_PR_RET_ENVIO_CIERR;


/***************************************************************************
Descripcion : Interfaz que envia informacion de clientes (RET-16)
Fecha Creacion : 19/08/2015
Autor : CSVD - FFVV
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Sistema
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
 psCodigoMarca  : Codigo Marca
 psCodigoCanal  : Codigo Canal
 psCodigoPeriodo : Codigo Periodo
 psFechaFacturacion: Fecha Facturacion
 psUsuarioSistema : Usuario Sistema
 psTipoProceso : Tipo Proceso
 psCodigoConsultora : Codigo de Consultora
 psNumeroDocumento : Numero Documento
***************************************************************************/
PROCEDURE INT_PR_RET_ENVIO_CLIEN
  (psCodigoPais       VARCHAR2,
   psCodigoSistema    VARCHAR2,
   psCodigoInterfaz   VARCHAR2,
   psNombreArchivo    VARCHAR2,
   psCodigoMarca      VARCHAR2,
   psCodigoCanal      VARCHAR2,
   psCodigoPeriodo    VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psUsuarioSistema   VARCHAR2,
   psTipoProceso	  VARCHAR2,
   psCodigoConsultora VARCHAR2,
   psNumeroDocumento  VARCHAR2)
IS

  -- RC: Declaramos variable local tipo proceso
  lsTipoProceso          VARCHAR2(2);

  CURSOR c_novedades (vnIdPais NUMBER, fechaActualizacionInterfaz DATE, vdFechaProceso DATE, 
                      vnMontoMaximoDeuda NUMBER, vnNumDiasPedido NUMBER, vsOidTipoClasificacion VARCHAR2, 
                      vsOidClasificacion VARCHAR2, lsOidClie number) IS

    SELECT psCodigoPais codigoPais,
           clie.cod_clie codigoCliente,
           NVL(docu.num_docu_iden,'') numeroDocumentoIdentidad,
           clie.val_ape1 apellidoPaterno,
           clie.val_ape2 apellidoMaterno,
           clie.val_nom1 || ' '|| clie.val_nom2 nombres,
           'CO' tipo,
           TRIM(SUBSTR( TRIM(dire.des_abrv_tipo_via) || ' '|| TRIM(dire.val_nomb_via) || ' ' || TRIM (num_ppal) || ' ' || TRIM (val_inte) || ' ' || TRIM (val_obse),0,100)) AS direccion,
           '111' barrio,
           NULL ubicGeografica,
           dire.val_cod_post codigoPostal,
           clie.cod_sexo sexo,
           to_char(clda.fec_naci,'yyyymmdd') AS fechaNacimiento,
           NULL tipoCutis,
           TO_NUMBER(NVL(mec.cod_esta_civi, '06')) estadoCivil, -- 06 - Otros
           SUBSTR(TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(clie.oid_clie, 'TF')),0,50) telefonoFijo,
           NULL tipoCliente,
           INT_PKG_REUTI.INT_FN_REU_EVALU_ESTAT_CONSU(clie.oid_clie, ec.cod_esta_clie, uadm.cod_zona, vnMontoMaximoDeuda) estadoCliente, --00:Activa, 01:Inactiva, 02:Morosa, 03:Bloqueo
           NULL porcentajeDescuento,
           uadm.cod_regi codigoRegion,
           uadm.cod_zona codigoZona,
           uadm.cod_secc codigoSeccion,
           uadm.cod_terr codigoTerritorio,
           nsep.cod_nsep NSE,
           NULL campanaUltimoPedido,
           NULL programaFidel,
           NULL puntaje,
           SUBSTR(TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(clie.oid_clie, 'ML')),0,50) correo,
           ec.cod_esta_clie estatusCliente,
           CASE
              WHEN (
                    SELECT COUNT(1)
                      FROM mae_clien_tipo_subti cts ,
                           mae_clien_clasi cc,
                           mae_clien mc
                     WHERE cts.oid_clie_tipo_subt = cc.ctsu_oid_clie_tipo_subt
                       AND mc.oid_clie = cts.clie_oid_clie
                       AND cts.ticl_oid_tipo_clie = 2
                       AND cts.sbti_oid_subt_clie = 1
                       AND TO_CHAR(cc.tccl_oid_tipo_clasi) IN (vsOidTipoClasificacion)
                       AND INSTR(vsOidClasificacion,cc.clas_oid_clas) > 0
                       AND mc.oid_clie = clie.oid_clie
                   ) > 0 THEN 1 ELSE 0
           END indicadorTop
      FROM (
            SELECT oid_clie AS clie_oid_clie FROM mae_clien clie WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_datos_adici WHERE fec_ulti_actu > ( fechaActualizacionInterfaz  )
            UNION
            SELECT clie_oid_clie FROM mae_clien_unida_admin WHERE ind_acti = 1 AND fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_prime_conta WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_direc WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_comun WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie_vndo FROM mae_clien_vincu WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie_vnte FROM mae_clien_vincu WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_bloqu WHERE fec_bloq >= TRUNC( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_bloqu WHERE fec_desb >= TRUNC( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM ccc_movim_cuent_corri WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie_oid_clie FROM mae_clien_tipo_subti WHERE fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT x.clie_oid_clie FROM mae_clien_tipo_subti x, mae_clien_clasi y
            WHERE x.oid_clie_tipo_subt = y.ctsu_oid_clie_tipo_subt AND y.fec_ulti_actu > ( fechaActualizacionInterfaz )
            UNION
            SELECT clie.oid_clie
              FROM ped_solic_cabec soca,
                   ped_solic_cabec cons,
                   ped_tipo_solic_pais tspa,
                   ped_tipo_solic tsol,
                   mae_clien clie
             WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe
               AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
               AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
               AND soca.clie_oid_clie = clie.oid_clie
               AND soca.fec_fact IS NOT NULL
               AND soca.grpr_oid_grup_proc = 5
               AND tsol.cod_tipo_soli = 'SOC'
               AND soca.fec_fact = vdFechaProceso
           ) data,
           mae_clien clie,
           mae_clien_datos_adici clda,
           mae_tipo_nivel_socec_perso nsep,
           mae_estad_civil mec,
           mae_estat_clien ec,
           (
            SELECT a.clie_oid_clie,
                   a.oid_clie_dire,
                   c.cod_tipo_via,
                   c.des_abrv_tipo_via,
                   a.val_nomb_via,
                   a.num_ppal,
                   a.val_inte,
                   a.val_obse,
                   a.val_barr,
                   a.val_cod_post,
                   t.cod_terr
              FROM mae_clien_direc a,
                   mae_tipo_direc b,
                   seg_tipo_via c,
                   mae_clien d,
                   zon_terri t
             WHERE d.oid_clie = a.clie_oid_clie
               AND b.oid_tipo_dire = a.tidc_oid_tipo_dire
               AND c.oid_tipo_via = a.tivi_oid_tipo_via
               AND a.terr_oid_terr = t.oid_terr (+)
               AND a.ind_elim = 0
               AND a.ind_dire_ppal  = 1
           ) dire,
           (
            SELECT cuad.clie_oid_clie, zorg.oid_regi, zzon.oid_zona, zscc.oid_secc , terr.oid_terr,
                   cuad.ind_acti, zorg.cod_regi, zzon.cod_zona, zscc.cod_secc, terr.cod_terr,
                   lide.val_ape1, lide.val_ape2, lide.val_nom1, lide.val_nom2
              FROM mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_terri terr,
                   zon_zona zzon,
                   zon_regio zorg,
                   mae_clien clie,
                   mae_clien lide
             WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND cuad.clie_oid_clie = clie.oid_clie
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND ztad.terr_oid_terr = terr.oid_terr
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zscc.clie_oid_clie = lide.oid_clie(+)
               AND zzon.zorg_oid_regi = zorg.oid_regi
               -- Condicion adicional --
               AND cuad.ind_acti = 1
           ) uadm,
           (
            SELECT clid.clie_oid_clie, tdoc.oid_tipo_docu, tdoc.cod_tipo_docu, clid.num_docu_iden
              FROM mae_clien_ident clid,
                   mae_tipo_docum tdoc
             WHERE clid.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
               AND clid.val_iden_docu_prin = 1
           ) docu
     WHERE data.clie_oid_clie = clie.oid_clie
       AND data.clie_oid_clie = clda.clie_oid_clie
       AND data.clie_oid_clie = dire.clie_oid_clie(+)
       AND data.clie_oid_clie = uadm.clie_oid_clie(+)
       AND data.clie_oid_clie = docu.clie_oid_clie(+)
       AND clda.nsep_oid_nsep = nsep.oid_nsep(+)
       AND clda.escv_oid_esta_civi = mec.oid_esta_civi(+)
       AND clda.esta_oid_esta_clie = ec.oid_esta_clie
       AND psTipoProceso = 'I'    ---- Solo funciona cuando se invoca por Interface
   UNION ALL
     SELECT psCodigoPais codigoPais,
           clie.cod_clie codigoCliente,
           NVL(docu.num_docu_iden,'') numeroDocumentoIdentidad,
           clie.val_ape1 apellidoPaterno,
           clie.val_ape2 apellidoMaterno,
           clie.val_nom1 || ' '|| clie.val_nom2 nombres,
           'CO' tipo,
           TRIM(SUBSTR( TRIM(dire.des_abrv_tipo_via) || ' '|| TRIM(dire.val_nomb_via) || ' ' || TRIM (num_ppal) || ' ' || TRIM (val_inte) || ' ' || TRIM (val_obse),0,100)) AS direccion,
           '111' barrio,
           NULL ubicGeografica,
           dire.val_cod_post codigoPostal,
           clie.cod_sexo sexo,
           to_char(clda.fec_naci,'yyyymmdd') AS fechaNacimiento,
           NULL tipoCutis,
           TO_NUMBER(NVL(mec.cod_esta_civi, '06')) estadoCivil, -- 06 - Otros
           SUBSTR(TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(clie.oid_clie, 'TF')),0,50) telefonoFijo,
           NULL tipoCliente,
           INT_PKG_REUTI.INT_FN_REU_EVALU_ESTAT_CONSU(clie.oid_clie, ec.cod_esta_clie, uadm.cod_zona, vnMontoMaximoDeuda) estadoCliente, --00:Activa, 01:Inactiva, 02:Morosa, 03:Bloqueo
           NULL porcentajeDescuento,
           uadm.cod_regi codigoRegion,
           uadm.cod_zona codigoZona,
           uadm.cod_secc codigoSeccion,
           uadm.cod_terr codigoTerritorio,
           nsep.cod_nsep NSE,
           NULL campanaUltimoPedido,
           NULL programaFidel,
           NULL puntaje,
           SUBSTR(TRIM(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(clie.oid_clie, 'ML')),0,50) correo,
           ec.cod_esta_clie estatusCliente,
           CASE
              WHEN (
                    SELECT COUNT(1)
                      FROM mae_clien_tipo_subti cts ,
                           mae_clien_clasi cc,
                           mae_clien mc
                     WHERE cts.oid_clie_tipo_subt = cc.ctsu_oid_clie_tipo_subt
                       AND mc.oid_clie = cts.clie_oid_clie
                       AND cts.ticl_oid_tipo_clie = 2
                       AND cts.sbti_oid_subt_clie = 1
                       AND TO_CHAR(cc.tccl_oid_tipo_clasi) IN (vsOidTipoClasificacion)
                       AND INSTR(vsOidClasificacion,cc.clas_oid_clas) > 0
                       AND mc.oid_clie = clie.oid_clie
                       and mc.oid_clie = lsOidClie
                   ) > 0 THEN 1 ELSE 0
           END indicadorTop
      FROM
           mae_clien clie,
           mae_clien_datos_adici clda,
           mae_tipo_nivel_socec_perso nsep,
           mae_estad_civil mec,
           mae_estat_clien ec,
           (
            SELECT a.clie_oid_clie,
                   a.oid_clie_dire,
                   c.cod_tipo_via,
                   c.des_abrv_tipo_via,
                   a.val_nomb_via,
                   a.num_ppal,
                   a.val_inte,
                   a.val_obse,
                   a.val_barr,
                   a.val_cod_post,
                   t.cod_terr
              FROM mae_clien_direc a,
                   mae_tipo_direc b,
                   seg_tipo_via c,
                   mae_clien d,
                   zon_terri t
             WHERE d.oid_clie = a.clie_oid_clie
               AND b.oid_tipo_dire = a.tidc_oid_tipo_dire
               AND c.oid_tipo_via = a.tivi_oid_tipo_via
               AND a.terr_oid_terr = t.oid_terr (+)
               AND a.ind_elim = 0
               AND a.ind_dire_ppal  = 1
               and d.oid_clie = lsOidClie
           ) dire,
           (
            SELECT cuad.clie_oid_clie, zorg.oid_regi, zzon.oid_zona, zscc.oid_secc , terr.oid_terr,
                   cuad.ind_acti, zorg.cod_regi, zzon.cod_zona, zscc.cod_secc, terr.cod_terr,
                   lide.val_ape1, lide.val_ape2, lide.val_nom1, lide.val_nom2
              FROM mae_clien_unida_admin cuad,
                   zon_terri_admin ztad,
                   zon_secci zscc,
                   zon_terri terr,
                   zon_zona zzon,
                   zon_regio zorg,
                   mae_clien clie,
                   mae_clien lide
             WHERE cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
               AND cuad.clie_oid_clie = clie.oid_clie
               AND ztad.zscc_oid_secc = zscc.oid_secc
               AND ztad.terr_oid_terr = terr.oid_terr
               AND zscc.zzon_oid_zona = zzon.oid_zona
               AND zscc.clie_oid_clie = lide.oid_clie(+)
               AND zzon.zorg_oid_regi = zorg.oid_regi
               -- Condicion adicional --
               AND cuad.ind_acti = 1
               and clie.oid_clie = lsOidClie
           ) uadm,
           (
            SELECT clid.clie_oid_clie, tdoc.oid_tipo_docu, tdoc.cod_tipo_docu, clid.num_docu_iden
              FROM mae_clien_ident clid,
                   mae_tipo_docum tdoc
             WHERE clid.tdoc_oid_tipo_docu = tdoc.oid_tipo_docu
               AND clid.val_iden_docu_prin = 1
               and clid.clie_oid_clie = lsOidClie
           ) docu
     WHERE 1 = 1
       AND clie.oid_clie = clda.clie_oid_clie
       AND clie.oid_clie = dire.clie_oid_clie(+)
       AND clie.oid_clie = uadm.clie_oid_clie(+)
       AND clie.oid_clie = docu.clie_oid_clie(+)
       AND clda.nsep_oid_nsep = nsep.oid_nsep(+)
       AND clda.escv_oid_esta_civi = mec.oid_esta_civi(+)
       AND clda.esta_oid_esta_clie = ec.oid_esta_clie
       AND clie.oid_clie = lsOidClie 
       AND psTipoProceso = 'W'; ---- Solo funciona cuando se invoca por WebService


   TYPE novedadesTipo IS RECORD
   (
        codigoPais                          VARCHAR2(3),
        codigoCliente                       mae_clien.cod_clie%TYPE,
        numeroDocumentoIdentidad            mae_clien_ident.num_docu_iden%TYPE,
        apellidoPaterno                     mae_clien.val_ape1%TYPE,
        apellidoMaterno                     mae_clien.val_ape2%TYPE,
        nombres                             VARCHAR2(50),
        tipo                                VARCHAR2(2),
        direccion                           VARCHAR2(200),
        barrio                              VARCHAR2(50),
        ubicGeografica                      VARCHAR2(50),
        codigoPostal                        mae_clien_direc.val_cod_post%TYPE,
        sexo                                VARCHAR2(1),
        fechaNacimiento                     VARCHAR2(8),
        tipoCutis                           VARCHAR2(1),
        estadoCivil                         mae_estad_civil.cod_esta_civi%TYPE,
        telefonoFijo                        mae_clien_comun.val_text_comu%TYPE,
        tipoCliente                         VARCHAR2(2),
        estadoCliente                       VARCHAR2(2),
        porcentajeDescuento                 VARCHAR2(5),
        codigoRegion                        zon_regio.cod_regi%TYPE,
        codigoZona                          zon_zona.cod_zona%TYPE,
        codigoSeccion                       zon_secci.cod_secc%TYPE,
        codigoTerritorio                    zon_terri.cod_terr%TYPE,
        NSE                                 mae_tipo_nivel_socec_perso.cod_nsep%TYPE,
        campanaUltimoPedido                 VARCHAR2(6),
        programaFidel        	              VARCHAR2(2),
        puntaje                             VARCHAR2(2),
        correo                              mae_clien_comun.val_text_comu%TYPE,
        estatusCliente                      mae_estat_clien.cod_esta_clie%TYPE,
        indicadorTop                        VARCHAR2(1)
   );

  TYPE novedadesTab IS TABLE OF novedadesTipo;
   novedadesRecord novedadesTab;



  CURSOR c_tipocliente (vsCodigoTipoCliente VARCHAR2, lsOidClie number)IS

    SELECT clie.cod_clie codigoCliente,
           GEN_PKG_GENER.GEN_FN_CLIEN_LIDER(clie.oid_clie, vsCodigoTipoCliente) tipoCliente
      FROM int_ret_clien_noved a,
           mae_clien clie
     WHERE a.cod_clie = clie.cod_clie
       and ( psTipoProceso = 'I' or
            ( psTipoProceso= 'W' and clie.oid_clie = lsOidClie ) );

   TYPE tipoclienteTipo IS RECORD
   (
        codigoCliente                       mae_clien.cod_clie%TYPE,
        tipoCliente                         VARCHAR2(2)
   );

   TYPE tipoclienteTab IS TABLE OF tipoclienteTipo;
   tipoclienteRecord tipoclienteTab;


  CURSOR c_pedido ( lsOidClie number )IS

   WITH pedido AS
   (
    SELECT clie.cod_clie codigoCliente,
           (
            SELECT MAX(spc.cod_peri)
              FROM ped_solic_cabec_acum2 sca2,
                   cra_perio cp,
                   seg_perio_corpo spc
             WHERE sca2.perd_oid_peri = cp.oid_peri
              AND cp.peri_oid_peri = spc.oid_peri
              AND sca2.clie_oid_clie = clie.oid_clie
           ) campanaUltimoPedido,
           clie.oid_clie
      FROM int_ret_clien_noved a,
           mae_clien clie
     WHERE a.cod_clie = clie.cod_clie
       and ( psTipoProceso = 'I' or
            ( psTipoProceso= 'W' and clie.oid_clie = lsOidClie ) )
   ) SELECT pedido.codigoCliente codigoCliente,
            pedido.campanaUltimoPedido campanaUltimoPedido,
            mce.fec_fact_ulti fechaFacturacion
       FROM pedido,
           mae_clien_estat mce
      WHERE pedido.oid_clie = mce.oid_clie   ;



   TYPE pedidoTipo IS RECORD
   (
        codigoCliente                       mae_clien.cod_clie%TYPE,
        campanaUltimoPedido                 VARCHAR2(6),
        fechaFacturacion                    DATE
   );

   TYPE pedidoTab IS TABLE OF pedidoTipo;
   pedidoRecord pedidoTab;

           /*(
            SELECT MAX(NVL(val_porc_desc,0)) val_porc_desc
                  FROM ped_solic_cabec     oc,
                       ped_solic_cabec     conso,
                       ped_solic_posic,
                       mae_produ,
                       ped_tipo_solic_pais ptsp,
                       ped_tipo_solic      pts
                 WHERE oc.ind_ts_no_conso = 1
                   AND oc.ind_oc = 1
                   AND oc.oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe
                   AND ped_solic_posic.prod_oid_prod = mae_produ.oid_prod
                   AND oc.fec_fact IS NOT NULL
                   AND espo_oid_esta_posi <> 2
                   AND mae_produ.mapr_oid_marc_prod <> 1
                   AND ped_solic_posic.tpos_oid_tipo_posi = 1
                   AND oc.perd_oid_peri >= 2537
                   AND oc.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                   AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                   AND pts.ind_anul <> 1
                   AND pts.ind_devo <> 1
                   AND oc.soca_oid_soli_cabe = conso.oid_soli_cabe(+)
                   AND nvl(conso.esso_oid_esta_soli, 1) <> 4
                   AND oc.clie_oid_clie = clie.oid_clie
                   AND oc.perd_oid_peri IN ( SELECT MAX(perd_oid_peri) FROM PED_SOLIC_CABEC_ACUM2 WHERE clie_oid_clie = oc.clie_oid_clie )
            ) descuento,*/


  CURSOR c_descuento (vsValorPorcentajeDcto VARCHAR2, lsOidClie number)IS

    SELECT rcn.cod_clie codigoCliente,
           GEN_PKG_INTER_ARCHI.GEN_FN_OBTIE_PORC_DCTO(
                   (select oid_clie from mae_clien x where x.cod_clie = rcn.cod_clie), 
                   vsValorPorcentajeDcto) porcentajeDescuento,
           rcn.cod_esta_clie estatusCliente,
           rcn.cam_ulti_pedi campanaUltimoPedido,
           rcn.fec_fact fechaFacturacion
      FROM int_ret_clien_noved rcn
     WHERE psTipoProceso = 'I'  
    UNION ALL  
    SELECT rcn.cod_clie codigoCliente,
           GEN_PKG_INTER_ARCHI.GEN_FN_OBTIE_PORC_DCTO(
                   (select oid_clie from mae_clien x where x.cod_clie = rcn.cod_clie), 
                   vsValorPorcentajeDcto) porcentajeDescuento,
           rcn.cod_esta_clie estatusCliente,
           rcn.cam_ulti_pedi campanaUltimoPedido,
           rcn.fec_fact fechaFacturacion
      FROM int_ret_clien_noved rcn
     WHERE  psTipoProceso= 'W' 
     and rcn.cod_clie = (select cod_clie from mae_clien x where x.oid_clie = lsOidClie)  ;

   TYPE descuentoTipo IS RECORD
   (
        codigoCliente                       mae_clien.cod_clie%TYPE,
        porcentajeDescuento                 VARCHAR2(5),
        estatusCliente                      mae_estat_clien.cod_esta_clie%TYPE,
        campanaUltimoPedido                 VARCHAR2(6),
        fechaFacturacion                    DATE
   );

   TYPE descuentoTab IS TABLE OF descuentoTipo;
   descuentoRecord descuentoTab;


  CURSOR c_interfaz (lsOidClie number) IS

    SELECT a.pais_cod_pais codigoPais,
           a.cod_clie codigoCliente,
           a.num_docu_iden numeroDocumentoIdentidad,
           a.ape_pate_clie apellidoPaterno,
           a.ape_mate_clie apellidoMaterno,
           a.nom_clie nombres,
           a.val_tipo tipo,
           a.val_dire direccion,
           a.val_barr barrio,
           a.ubi_geog ubicGeografica,
           a.cod_post codigoPostal,
           a.cod_sexo sexo,
           a.fec_naci fechaNacimiento,
           a.tip_cuti tipoCutis,
           a.est_civi estadoCivil,
           a.tel_fijo telefonoFijo,
           a.tip_clie tipoCliente,
           a.est_clie estadoCliente,
           a.por_desc porcentajeDescuento,
           a.cod_regi codigoRegion,
           a.cod_zona codigoZona,
           a.cod_secc codigoSeccion,
           a.cod_terr codigoTerritorio,
           a.niv_soci_econ NSE,
           a.cam_ulti_pedi campanaUltimoPedido,
           a.pro_fide programaFidel,
           a.val_punt puntaje,
           a.cor_elec correo,
           a.fec_fact fechaFacturacion,
           a.cod_esta_clie estatusCliente,
           a.ind_top indicadorTop
      FROM int_ret_clien_noved a
      where psTipoProceso = 'I' 
    union all      
    SELECT a.pais_cod_pais codigoPais,
           a.cod_clie codigoCliente,
           a.num_docu_iden numeroDocumentoIdentidad,
           a.ape_pate_clie apellidoPaterno,
           a.ape_mate_clie apellidoMaterno,
           a.nom_clie nombres,
           a.val_tipo tipo,
           a.val_dire direccion,
           a.val_barr barrio,
           a.ubi_geog ubicGeografica,
           a.cod_post codigoPostal,
           a.cod_sexo sexo,
           a.fec_naci fechaNacimiento,
           a.tip_cuti tipoCutis,
           a.est_civi estadoCivil,
           a.tel_fijo telefonoFijo,
           a.tip_clie tipoCliente,
           a.est_clie estadoCliente,
           a.por_desc porcentajeDescuento,
           a.cod_regi codigoRegion,
           a.cod_zona codigoZona,
           a.cod_secc codigoSeccion,
           a.cod_terr codigoTerritorio,
           a.niv_soci_econ NSE,
           a.cam_ulti_pedi campanaUltimoPedido,
           a.pro_fide programaFidel,
           a.val_punt puntaje,
           a.cor_elec correo,
           a.fec_fact fechaFacturacion,
           a.cod_esta_clie estatusCliente,
           a.ind_top indicadorTop
      FROM int_ret_clien_noved a
      where psTipoProceso= 'W' 
      and a.cod_clie = (select cod_clie from mae_clien x where x.oid_clie = lsOidClie) ;
            

   TYPE interfazTipo IS RECORD
   (
        codigoPais                          VARCHAR2(3),
        codigoCliente                       mae_clien.cod_clie%TYPE,
        numeroDocumentoIdentidad            mae_clien_ident.num_docu_iden%TYPE,
        apellidoPaterno                     mae_clien.val_ape1%TYPE,
        apellidoMaterno                     mae_clien.val_ape2%TYPE,
        nombres                             VARCHAR2(50),
        tipo                                VARCHAR2(2),
        direccion                           VARCHAR2(200),
        barrio                              VARCHAR2(50),
        ubicGeografica                      VARCHAR2(50),
        codigoPostal                        mae_clien_direc.val_cod_post%TYPE,
        sexo                                VARCHAR2(1),
        fechaNacimiento                     VARCHAR2(8),
        tipoCutis                           VARCHAR2(1),
        estadoCivil                         mae_estad_civil.cod_esta_civi%TYPE,
        telefonoFijo                        mae_clien_comun.val_text_comu%TYPE,
        tipoCliente                         VARCHAR2(2),
        estadoCliente                       VARCHAR2(2),
        porcentajeDescuento                 VARCHAR2(5),
        codigoRegion                        zon_regio.cod_regi%TYPE,
        codigoZona                          zon_zona.cod_zona%TYPE,
        codigoSeccion                       zon_secci.cod_secc%TYPE,
        codigoTerritorio                    zon_terri.cod_terr%TYPE,
        NSE                                 mae_tipo_nivel_socec_perso.cod_nsep%TYPE,
        campanaUltimoPedido                 VARCHAR2(6),
        programaFidel        	              VARCHAR2(2),
        puntaje                             VARCHAR2(2),
        correo                              mae_clien_comun.val_text_comu%TYPE,
        fechaFacturacion                    DATE,
        estatusCliente                      mae_estat_clien.cod_esta_clie%TYPE,
        indicadorTop                        VARCHAR2(1)
   );

  TYPE interfazTab IS TABLE OF interfazTipo;
   interfazRecord interfazTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo                  BAS_INTER.DIR_TEMP%TYPE;
   v_handle                    UTL_FILE.FILE_TYPE;
   lsLinea                     VARCHAR2(1000);
   lbAbrirUtlFile              BOOLEAN;
   lsNombreArchivo             VARCHAR2(50);

   pdFechaUltimaActualizacion  DATE;
   pdFechaProceso              DATE;
   pdFechaFacturacion          DATE;
   lnIdPais                    NUMBER;
   lsPorcentajeDescuento       VARCHAR2(5);
   lsValorPorcentajeDcto       VARCHAR2(5);
   lsValorPorcentajeDctoPEG    VARCHAR2(5);
   lsValorPorcentajeDctoEGR    VARCHAR2(5);
   lsValorPorcentajeDctoMin    VARCHAR2(5);
   lnMontoMaximoDeuda          NUMBER;
   lsCodigoTipoCliente         VARCHAR2(2);
   lnNumDiasPedido             NUMBER;
   lsCampanaUltimoPedido       VARCHAR2(6);
   lsEstatusCliente            VARCHAR2(2);
   lsUltimaCampanaCierre       VARCHAR2(6);
   lsOidTipoClasificacion      VARCHAR2(50);
   lsOidClasificacion          VARCHAR2(50);
   lsIndPiRetai                VARCHAR2(50);

   lsOidClie                   mae_clien.oid_clie%TYPE;
   lsNumeroDocumento           mae_clien_ident.num_docu_iden%TYPE;
   lsCodigoConsultora          mae_clien.cod_clie%TYPE;
   
  lnMontoVentaRetail          ret_venta_cabec.val_mont_bapl_dcto%TYPE;
  lnMontoBaseDcto             PED_SOLIC_CABEC.VAL_MONT_BAPL_DCTO%TYPE;   
   --lsCodigoRegion              VARCHAR2(2);

   /* Declaracion de variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(1500);
   W_FILAS      NUMBER:=1000;

BEGIN
  dbms_output.put_line (to_char(sysdate,'dd/MM/yyyy hh:mi:ss'));
  lnIdPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  pdFechaProceso := TO_DATE(psFechaFacturacion,'DD/MM/YYYY');
    
  if psTipoProceso= 'W'  then
    if psCodigoConsultora is null then
        BEGIN
            SELECT mae.oid_clie, mae.cod_clie   --- clid.num_docu_iden
              into lsOidClie, lsCodigoConsultora
              FROM mae_clien_ident clid,
                   mae_clien mae
             WHERE mae.oid_clie = clid.clie_oid_clie
               ---AND clid.val_iden_docu_prin = 1          
               and clid.num_docu_iden = psNumeroDocumento 
            and rownum = 1;
            
            lsNumeroDocumento := null;
        EXCEPTION
          WHEN no_data_found THEN
               lsOidClie := 0;
        end;
    else
      
     lsNumeroDocumento := null;
     lsCodigoConsultora := psCodigoConsultora;
      BEGIN
        select mae.oid_clie
          into lsOidClie
          from mae_clien mae
          where mae.cod_clie = psCodigoConsultora
          and rownum = 1;
      EXCEPTION
        WHEN no_data_found THEN
             lsOidClie := 0;
      end;
        
    end if;

        
  end if;
  
  lsTipoProceso := psTipoProceso;

  SELECT MAX(FEC_IPRO)
  INTO pdFechaUltimaActualizacion
  FROM BAS_HISTO_LOTES
  WHERE PAIS_COD_PAIS = psCodigoPais
     AND SIST_COD_SIST= psCodigosistema
     AND INTE_COD_INTE = psCodigoInterfaz
     AND IND_LOER ='N'
     AND FEC_FPRO IS NOT NULL;

   SELECT val_pain
    INTO lnMontoMaximoDeuda
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'montoMaximoDeuda';

  SELECT val_pain
    INTO lsCodigoTipoCliente
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'codigoTipoCliente';

  SELECT val_pain
    INTO lnNumDiasPedido
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'numDiasPedido';

  SELECT val_pain
    INTO lsValorPorcentajeDcto
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'valorPorcentajeDcto';

  SELECT val_pain
    INTO lsValorPorcentajeDctoPEG
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'valorPorcentajeDctoPEG';

  SELECT val_pain
    INTO lsValorPorcentajeDctoEGR
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'valorPorcentajeDctoEGR';

  SELECT val_pain
    INTO lsValorPorcentajeDctoMin
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'valorPorcentajeDctoMin';

  SELECT val_pain
    INTO lsOidTipoClasificacion
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'oidTipoClasificacion';

  SELECT val_pain
    INTO lsOidClasificacion
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'oidClasificacion';

/*
  SELECT val_pain
    INTO lsCodigoRegion
    FROM bas_param_inter
   WHERE pais_cod_pais = psCodigoPais
     AND sist_cod_sist = psCodigoSistema
     AND inte_cod_inte = psCodigoInterfaz
     AND nom_pain = 'codigoRegion'; */

 -- RC Validamos si tipoProceso = 'I': Interfaz
 IF lsTipoProceso = 'I' THEN
   /* Eliminando informacion de tabla temporal */
   DELETE int_ret_clien_noved;
 ELSE	-- valida cuando tipoProceso = 'W': WebService
   DELETE int_ret_clien_noved
     where ( pais_cod_pais = psCodigoPais and cod_clie = lsCodigoConsultora);
 END IF; -- fin RC

 /* Reemplaza a la resta AND soca.fec_fact = vdFechaProceso - vnNumDiasPedido */
 pdFechaProceso := gen_pkg_gener.gen_fn_fecha_dias_habiles(pdFechaProceso,lnNumDiasPedido);



     /* Insertando Novedades en tabla temporal */
     OPEN c_novedades(lnIdPais, pdFechaUltimaActualizacion, pdFechaProceso, lnMontoMaximoDeuda, lnNumDiasPedido, lsOidTipoClasificacion, lsOidClasificacion,lsOidClie);
      LOOP
       FETCH c_novedades BULK COLLECT INTO novedadesRecord LIMIT W_FILAS;

        IF novedadesRecord.COUNT > 0 THEN
         FOR x IN novedadesRecord.FIRST .. novedadesRecord.LAST LOOP

            INSERT INTO int_ret_clien_noved
            (
             pais_cod_pais, cod_clie, num_docu_iden,
             ape_pate_clie, ape_mate_clie, nom_clie,
             val_tipo, val_dire, val_barr,
             ubi_geog, cod_post, cod_sexo,
             fec_naci, tip_cuti, est_civi,
             tel_fijo, tip_clie, est_clie,
             por_desc, cod_regi, cod_zona,
             cod_secc, cod_terr, niv_soci_econ,
             cam_ulti_pedi, pro_fide, val_punt,
             cor_elec, cod_esta_clie, ind_top,
             usu_crea, fec_crea
            )
            VALUES
            (
             novedadesRecord(x).codigoPais,
             novedadesRecord(x).codigoCliente,
             novedadesRecord(x).numeroDocumentoIdentidad,
             novedadesRecord(x).apellidoPaterno,
             novedadesRecord(x).apellidoMaterno,
             novedadesRecord(x).nombres,
             novedadesRecord(x).tipo,
             novedadesRecord(x).direccion,
             novedadesRecord(x).barrio,
             novedadesRecord(x).ubicGeografica,
             novedadesRecord(x).codigoPostal,
             novedadesRecord(x).sexo,
             novedadesRecord(x).fechaNacimiento,
             novedadesRecord(x).tipoCutis,
             novedadesRecord(x).estadoCivil,
             novedadesRecord(x).telefonoFijo,
             novedadesRecord(x).tipoCliente,
             novedadesRecord(x).estadoCliente,
             novedadesRecord(x).porcentajeDescuento,
             novedadesRecord(x).codigoRegion,
             novedadesRecord(x).codigoZona,
             novedadesRecord(x).codigoSeccion,
             novedadesRecord(x).codigoTerritorio,
             novedadesRecord(x).NSE,
             novedadesRecord(x).campanaUltimoPedido,
             novedadesRecord(x).programaFidel,
             novedadesRecord(x).puntaje,
             novedadesRecord(x).correo,
             novedadesRecord(x).estatusCliente,
             novedadesRecord(x).indicadorTop,
             psUsuarioSistema,
             SYSDATE
            );

         END LOOP;
        END IF;
       EXIT WHEN c_novedades%NOTFOUND;
      END LOOP;
     CLOSE c_novedades;


 /* Actualizamos Tipo Cliente en tabla temporal */
 OPEN c_tipocliente(lsCodigoTipoCliente,lsOidClie);
  LOOP
   FETCH c_tipocliente BULK COLLECT INTO tipoclienteRecord LIMIT W_FILAS;

    IF tipoclienteRecord.COUNT > 0 THEN
     FOR x IN tipoclienteRecord.FIRST .. tipoclienteRecord.LAST LOOP

        UPDATE int_ret_clien_noved rcn
           SET rcn.tip_clie = tipoclienteRecord(x).tipoCliente,
               rcn.usu_modi = psUsuarioSistema,
               rcn.fec_modi = SYSDATE
         WHERE rcn.pais_cod_pais = psCodigoPais
           AND rcn.cod_clie = tipoclienteRecord(x).codigoCliente;

     END LOOP;
    END IF;
   EXIT WHEN c_tipocliente%NOTFOUND;
  END LOOP;
 CLOSE c_tipocliente;


 /* Actualizamos Campaña Ultimo Pedido en tabla temporal */
 OPEN c_pedido(lsOidClie);
  LOOP
   FETCH c_pedido BULK COLLECT INTO pedidoRecord LIMIT W_FILAS;

    IF pedidoRecord.COUNT > 0 THEN
     FOR x IN pedidoRecord.FIRST .. pedidoRecord.LAST LOOP

        UPDATE int_ret_clien_noved rcn
           SET rcn.cam_ulti_pedi = pedidoRecord(x).campanaUltimoPedido,
               rcn.usu_modi = psUsuarioSistema,
               rcn.fec_modi = SYSDATE
         WHERE rcn.pais_cod_pais = psCodigoPais
           AND rcn.cod_clie = pedidoRecord(x).codigoCliente;

        UPDATE int_ret_clien_noved rcn
           SET rcn.fec_fact = pedidoRecord(x).fechaFacturacion,
               rcn.usu_modi = psUsuarioSistema,
               rcn.fec_modi = SYSDATE
         WHERE rcn.pais_cod_pais = psCodigoPais
           AND rcn.cod_clie = pedidoRecord(x).codigoCliente;

     END LOOP;
    END IF;
   EXIT WHEN c_pedido%NOTFOUND;
  END LOOP;
 CLOSE c_pedido;


 /* Actualizamos Porcentaje Descuento en tabla temporal */
 OPEN c_descuento(lsValorPorcentajeDcto,lsOidClie);
  LOOP
   FETCH c_descuento BULK COLLECT INTO descuentoRecord LIMIT W_FILAS;

    IF descuentoRecord.COUNT > 0 THEN
     FOR x IN descuentoRecord.FIRST .. descuentoRecord.LAST LOOP

        lsPorcentajeDescuento := descuentoRecord(x).porcentajeDescuento;

        IF descuentoRecord(x).estatusCliente = '05' OR descuentoRecord(x).estatusCliente = '07' THEN
          lsPorcentajeDescuento := lsValorPorcentajeDctoEGR;
        END IF;

        IF descuentoRecord(x).porcentajeDescuento < lsValorPorcentajeDctoMin THEN
          lsPorcentajeDescuento := lsValorPorcentajeDctoMin;
        END IF;

        IF descuentoRecord(x).estatusCliente = '01' AND (descuentoRecord(x).fechaFacturacion IS NULL OR descuentoRecord(x).campanaUltimoPedido IS NULL) THEN
           lsPorcentajeDescuento := 0;
        END IF;

        UPDATE int_ret_clien_noved rcn
           SET rcn.por_desc = lsPorcentajeDescuento,
               rcn.usu_modi = psUsuarioSistema,
               rcn.fec_modi = SYSDATE
         WHERE rcn.pais_cod_pais = psCodigoPais
           AND rcn.cod_clie = descuentoRecord(x).codigoCliente;

     END LOOP;
    END IF;
   EXIT WHEN c_descuento%NOTFOUND;
  END LOOP;
 CLOSE c_descuento;


 -- Generando Archivo de Texto (Detalle) --
 lbAbrirUtlFile := TRUE;
 OPEN c_interfaz(lsOidClie);
  LOOP
   FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
    -- Procedimiento inicial para generar interfaz --
    -- RC: Si tipo proceso = 'I'
	  IF lsTipoProceso = 'I' THEN
      IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
             psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
      END IF;
    END IF; -- fin RC

    IF interfazRecord.COUNT > 0 THEN
     FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

        lsCampanaUltimoPedido := interfazRecord(x).campanaUltimoPedido;
        lsPorcentajeDescuento := interfazRecord(x).porcentajeDescuento;
        lsUltimaCampanaCierre := '0';


        SELECT NVL(MIN(TRIM(ZON_REGIO.IND_PILO_RETA)),'0')
           INTO lsIndPiRetai
           FROM ZON_REGIO
          WHERE COD_REGI = interfazRecord(x).codigoRegion;

        IF(lsIndPiRetai='1') THEN
        --IF interfazRecord(x).codigoRegion = lsCodigoRegion THEN

           SELECT MAX(fpc.cam_proc)
             INTO lsUltimaCampanaCierre
             FROM fac_progr_cierr fpc
            WHERE fpc.tip_cier = 'R'
              AND fpc.est_cier = 'P'
              AND fpc.cod_regi = interfazRecord(x).codigoRegion;


             IF interfazRecord(x).fechaFacturacion <= pdFechaProceso THEN

              lsCampanaUltimoPedido := GEN_FN_CALCU_PERIO(interfazRecord(x).campanaUltimoPedido, 1);

              IF interfazRecord(x).indicadorTop = '0' THEN
                 lsPorcentajeDescuento := lsValorPorcentajeDcto;
              END IF;

              IF interfazRecord(x).estatusCliente = '04' THEN
                 lsCampanaUltimoPedido := GEN_FN_CALCU_PERIO(lsUltimaCampanaCierre, 1);
                 lsPorcentajeDescuento := lsValorPorcentajeDctoPEG;
              ELSIF interfazRecord(x).estatusCliente = '05' OR interfazRecord(x).estatusCliente = '07' THEN
                 lsCampanaUltimoPedido := GEN_FN_CALCU_PERIO(lsUltimaCampanaCierre, 1);
                 lsPorcentajeDescuento := lsValorPorcentajeDctoEGR;
              ELSE
                 IF interfazRecord(x).porcentajeDescuento < lsValorPorcentajeDctoMin THEN
                    lsPorcentajeDescuento := lsValorPorcentajeDctoMin;
                 END IF;
              END IF;
           ELSE
              IF interfazRecord(x).fechaFacturacion IS NULL OR interfazRecord(x).campanaUltimoPedido IS NULL THEN
                 lsCampanaUltimoPedido := GEN_FN_CALCU_PERIO(lsUltimaCampanaCierre, 1);
                 lsPorcentajeDescuento := lsValorPorcentajeDctoEGR;
              END IF;
           END IF;
           --- calcula total venta directa y retail
           lnMontoVentaRetail:=0;
           lnMontoBaseDcto := 0;
           if lsCampanaUltimoPedido is not null and psTipoProceso= 'W' then

               BEGIN
                   SELECT SUM(nvl(rc.val_mont_bapl_dcto,0))
                   INTO lnMontoVentaRetail
                   FROM ret_venta_cabec rc
                   WHERE rc.val_cuen_consu = interfazRecord(x).codigoCliente
                         AND rc.cam_reta = lsCampanaUltimoPedido
                         AND rc.cod_pais = psCodigoPais;
                   EXCEPTION
                     WHEN OTHERS THEN
                        lnMontoVentaRetail:=0;
               END;

              BEGIN
                SELECT SUM(PSC.VAL_MONT_BAPL_DCTO)
                  INTO lnMontoBaseDcto
                  FROM PED_SOLIC_CABEC PSC,
                       PED_SOLIC_CABEC CAB,
                       PED_TIPO_SOLIC_PAIS TSP,
                       PED_TIPO_SOLIC TSO
                 WHERE PSC.Perd_Oid_Peri = gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lsCampanaUltimoPedido)
                   AND PSC.CLIE_OID_CLIE = gen_pkg_gener.gen_fn_devuelve_id_cliente(interfazRecord(x).codigoCliente)
                   AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
                   AND TSP.TSOL_OID_TIPO_SOLI = TSO.OID_TIPO_SOLI
                   AND PSC.SOCA_OID_SOLI_CABE = CAB.OID_SOLI_CABE(+)
                   AND NVL(CAB.ESSO_OID_ESTA_SOLI,0) <> 4
                   AND TSO.COD_TIPO_SOLI = 'SOC';
              EXCEPTION
                WHEN NO_DATA_FOUND THEN
                  lnMontoBaseDcto := 0;
              END; 

           end if;

           UPDATE int_ret_clien_noved rcn
              SET rcn.cam_ulti_pedi = lsCampanaUltimoPedido,
                  rcn.por_desc = lsPorcentajeDescuento,
                  rcn.usu_modi = psUsuarioSistema,
                  rcn.tot_vta_dir = lnMontoBaseDcto,
                  rcn.tot_vta_ret = lnMontoVentaRetail,
                  rcn.fec_modi = SYSDATE
            WHERE rcn.pais_cod_pais = psCodigoPais
              AND rcn.cod_clie = interfazRecord(x).codigoCliente;

        END IF;
     -- RC: Validamos si tipo de proceso es Interfaz
	   IF lsTipoProceso = 'I' THEN
       
       lsLinea := interfazRecord(x).codigoPais                       ||';'||
                  interfazRecord(x).codigoCliente                    ||';'||
                  interfazRecord(x).numeroDocumentoIdentidad         ||';'||
                  interfazRecord(x).apellidoPaterno                  ||';'||
                  interfazRecord(x).apellidoMaterno                  ||';'||
                  interfazRecord(x).nombres                          ||';'||
                  interfazRecord(x).tipo                             ||';'||
                  interfazRecord(x).direccion                        ||';'||
                  interfazRecord(x).barrio                           ||';'||
                  interfazRecord(x).ubicGeografica                   ||';'||
                  interfazRecord(x).codigoPostal                     ||';'||
                  interfazRecord(x).sexo                             ||';'||
                  interfazRecord(x).fechaNacimiento                  ||';'||
                  interfazRecord(x).tipoCutis                        ||';'||
                  interfazRecord(x).estadoCivil                      ||';'||
                  interfazRecord(x).telefonoFijo                     ||';'||
                  interfazRecord(x).tipoCliente                      ||';'||
                  interfazRecord(x).estadoCliente                    ||';'||
                  lsPorcentajeDescuento                              ||';'||
                  interfazRecord(x).codigoRegion                     ||';'||
                  interfazRecord(x).codigoZona                       ||';'||
                  interfazRecord(x).codigoSeccion                    ||';'||
                  interfazRecord(x).codigoTerritorio                 ||';'||
                  interfazRecord(x).NSE                              ||';'||
                  lsCampanaUltimoPedido                              ||';'||
                  interfazRecord(x).programaFidel                    ||';'||
                  interfazRecord(x).puntaje                          ||';'||
                  interfazRecord(x).correo                           ;

         UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
     END IF; -- fin RC
     END LOOP;
    END IF;
   EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
 CLOSE c_interfaz;

 -- RC: Validamos si tipo de proceso es Interfaz
 IF lsTipoProceso = 'I' THEN
   IF NOT lbAbrirUtlFile THEN
      utl_file.fclose(V_HANDLE);

      -- Procedimiento final para generar interfaz --
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
   END IF;
 END IF; -- fin RC

RETURN;
EXCEPTION
 WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,1000);
 RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_RET_ENVIO_CLIEN: '||ls_sqlerrm);

END INT_PR_RET_ENVIO_CLIEN;


END INT_PKG_RETAI;
/
