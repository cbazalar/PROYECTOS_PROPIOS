CREATE OR REPLACE PACKAGE "INT_PKG_PERCE" IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;
/***************************************************************************
Descripcion       : Genera Interfase de Solicitudes Monetarias Cabecera
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_PER_SOLIC_MONET_CABEC
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psNumeroLote VARCHAR2,
   psTipoOrigen VARCHAR2,
   psCodigoAccesoFisico VARCHAR2,
   psTipoDespacho VARCHAR2);
/***************************************************************************
Descripcion       : Genera Interfase de Solicitudes Monetarias Posicion
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_PER_SOLIC_MONET_POSIC
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psNumeroLote VARCHAR2,
   psTipoOrigen VARCHAR2);
   /**************************************************************************************
Descripcion       : Genera Interfaz de Envio de PDT  - SUNAT (PER-5)
Fecha Creacion    : 19/06/2013
Autor: Jean Pierre Jimenez
****************************************************************************************/
  PROCEDURE INT_PR_PER_ARCHI_PDT(psCodigoPais     VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2,
   psFechaDesde         VARCHAR2,
   psFechaHasta          VARCHAR2);
   
   /**************************************************************************************
Descripcion       : Genera Interfaz de Envio de PDT  - SUNAT (PER-5)
Fecha Creacion    : 19/06/2013
Autor: Jean Pierre Jimenez
****************************************************************************************/
  PROCEDURE INT_PR_PER_ARCHI_PDT2(psCodigoPais     VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2,
   psFechaDesde         VARCHAR2,
   psFechaHasta          VARCHAR2);   

    /**************************************************************************************
    Descripcion       : Genera Interfaz de Envio de REsumen de percepciones para sunat (PER-7)
    Fecha Creacion    : 21/01/2016
    Autor: Ivan Tocto Jaimes
    ****************************************************************************************/
  PROCEDURE INT_PR_PER_ENVIO_RESUM_PERCE(
    psCodigoPais           VARCHAR2,
    psCodigoSistema        VARCHAR2,
    psCodigoInterfaz       VARCHAR2,
    psNombreArchivo        VARCHAR2,
    psCodigoUsuario        VARCHAR2,
    psNumeroLote           VARCHAR2,
    psFechaDesde           VARCHAR2 );   
   
END INT_PKG_PERCE;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_PERCE" IS
/***************************************************************************
Descripcion       : Genera Interfase de Solicitudes Monetarias Cabecera
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_PER_SOLIC_MONET_CABEC
  (psCodigoPais VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2,
   psNumeroLote VARCHAR2,
   psTipoOrigen VARCHAR2,
   psCodigoAccesoFisico VARCHAR2,
   psTipoDespacho VARCHAR2)
IS
   CURSOR c_interfaz IS
   SELECT DISTINCT
      PAIS_COD_PAIS,
      COD_PERI,
      COD_CLIE,
      0 AS NUM_CLIE,
      COD_TIPO_SOLI,
      COD_SBAC,
      psCodigoAccesoFisico AS COD_ACCE,
      psTipoDespacho AS TIP_DESP,
      FEC_FACT,
      ' ' AS STA_PROC,
      COR_AGRU,
      COD_CANA_REFE,
      COD_ACCE_REFE,
      COD_SBAC_REFE,
      NUM_DOCU_REFE
  FROM PER_SOLIC_MONET A
  WHERE
   PAIS_COD_PAIS = psCodigoPais AND
   COD_TIPO_SOLI NOT IN ('SC11','SC12','SP26') AND
   NUM_LOTE = psNumeroLote AND
   ((psTipoOrigen = '01'  AND TIOR_TIPO_ORIG_DATO IN ('06','01')) OR
    (psTipoOrigen = '02'  AND TIOR_TIPO_ORIG_DATO IN ('06','02')) OR
    (psTipoOrigen = '03'  AND TIOR_TIPO_ORIG_DATO IN ('06','03')) OR
    (psTipoOrigen = '04'  AND TIOR_TIPO_ORIG_DATO IN ('06','04')) OR
    (psTipoOrigen = '08'  AND TIOR_TIPO_ORIG_DATO IN ('06','08')) OR
    (psTipoOrigen <> '01' AND psTipoOrigen <> '02' AND
     psTipoOrigen <> '03' AND psTipoOrigen <> '04' AND
     psTipoOrigen <> '08' AND TIOR_TIPO_ORIG_DATO = psTipoOrigen) )
  GROUP BY
     PAIS_COD_PAIS, COR_AGRU, NUM_DOCU_REFE,
     COD_PERI, COD_CLIE, COD_TIPO_SOLI, COD_SBAC, FEC_FACT,
     COD_CANA_REFE, COD_ACCE_REFE, COD_SBAC_REFE ;

  CURSOR c_interfazGene IS
  SELECT DISTINCT
      PAIS_COD_PAIS,
      COD_PERI,
      COD_CLIE,
      0 AS NUM_CLIE,
      COD_TIPO_SOLI,
      COD_SBAC,
      psCodigoAccesoFisico AS COD_ACCE,
      psTipoDespacho AS TIP_DESP,
      FEC_FACT,
      ' ' AS STA_PROC,
      COR_AGRU,
      COD_CANA_REFE,
      COD_ACCE_REFE,
      COD_SBAC_REFE,
      NUM_DOCU_REFE
  FROM PER_SOLIC_MONET A
  WHERE
     PAIS_COD_PAIS = psCodigoPais AND
     NUM_LOTE_GENE = psNumeroLote AND
     COD_TIPO_SOLI NOT IN ('SC11','SC12','SP26')
  GROUP BY
     PAIS_COD_PAIS, COR_AGRU, NUM_DOCU_REFE,
     COD_PERI, COD_CLIE, COD_TIPO_SOLI, COD_SBAC, FEC_FACT,
     COD_CANA_REFE, COD_ACCE_REFE, COD_SBAC_REFE;

   TYPE interfazRec IS RECORD
   (
   codigoPais                   PER_SOLIC_MONET.PAIS_COD_PAIS%TYPE,
   codigoPeriodo                PER_SOLIC_MONET.COD_PERI%TYPE,
   codigoCliente                PER_SOLIC_MONET.COD_CLIE%TYPE,
   numeroClientes               NUMBER,
   tipoSolicitud                PER_SOLIC_MONET.COD_TIPO_SOLI%TYPE,
   codigoSubacceso              PER_SOLIC_MONET.COD_SBAC%TYPE,
   codigoAcceso                 PER_SOLIC_MONET.COD_ACCE%TYPE,
   tipoDespacho                 CHAR(2),
   fechaFacturacion             PER_SOLIC_MONET.FEC_FACT%TYPE,
   status                       CHAR(1),
   correlativo                  PER_SOLIC_MONET.COR_CUEN_CORR_DOLE%TYPE,
   codigoCanalReferencia        PER_SOLIC_MONET.COD_CANA_REFE%TYPE,
   codigoAccesoReferencia       PER_SOLIC_MONET.COD_ACCE_REFE%TYPE,
   codigoSubaccesoReferencia    PER_SOLIC_MONET.COD_SBAC_REFE%TYPE,
   numeroDocumentoReferencia    PER_SOLIC_MONET.NUM_DOCU_REFE%TYPE
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
    IF psTipoOrigen IS NOT NULL THEN
        OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                              interfazRecord(x).codigoPeriodo                ||';'||
                              interfazRecord(x).codigoCliente                 ||';'||
                              interfazRecord(x).numeroClientes                ||';'||
                              interfazRecord(x).tipoSolicitud                 ||';'||
                              interfazRecord(x).codigoSubacceso               ||';'||
                              interfazRecord(x).codigoAcceso                  ||';'||
                              interfazRecord(x).tipoDespacho                  ||';'||
                              to_char(interfazRecord(x).fechaFacturacion,'YYYYMMDD')  ||';'||
                              interfazRecord(x).status                        ||';'||
                              interfazRecord(x).correlativo                   ||';'||
                              interfazRecord(x).codigoCanalReferencia         ||';'||
                              interfazRecord(x).codigoAccesoReferencia        ||';'||
                              interfazRecord(x).codigoSubaccesoReferencia     ||';'||
                              interfazRecord(x).numeroDocumentoReferencia ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;
    ELSE
        OPEN c_interfazGene;
        LOOP
           FETCH c_interfazGene BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                              interfazRecord(x).codigoPeriodo                ||';'||
                              interfazRecord(x).codigoCliente                 ||';'||
                              interfazRecord(x).numeroClientes                ||';'||
                              interfazRecord(x).tipoSolicitud                 ||';'||
                              interfazRecord(x).codigoSubacceso               ||';'||
                              interfazRecord(x).codigoAcceso                  ||';'||
                              interfazRecord(x).tipoDespacho                  ||';'||
                              to_char(interfazRecord(x).fechaFacturacion,'YYYYMMDD')  ||';'||
                              interfazRecord(x).status                        ||';'||
                              interfazRecord(x).correlativo                   ||';'||
                              interfazRecord(x).codigoCanalReferencia         ||';'||
                              interfazRecord(x).codigoAccesoReferencia        ||';'||
                              interfazRecord(x).codigoSubaccesoReferencia     ||';'||
                              interfazRecord(x).numeroDocumentoReferencia ;
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
              END LOOP;
           END IF;
           EXIT WHEN c_interfazGene%NOTFOUND;
        END LOOP;
        CLOSE c_interfazGene;
    END IF;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);

    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PER_SOLIC_MONET_CABEC: '||ls_sqlerrm);
END INT_PR_PER_SOLIC_MONET_CABEC;
/***************************************************************************
Descripcion       : Genera Interfase de Solicitudes Monetarias Posicion
Fecha Creacion    : 14/11/2006
Autor             : Carlos Bazalar
***************************************************************************/
PROCEDURE INT_PR_PER_SOLIC_MONET_POSIC
  (psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2,
   psNumeroLote     VARCHAR2,
   psTipoOrigen     VARCHAR2)
IS
   CURSOR c_interfaz IS
    SELECT
      PAIS_COD_PAIS,
      COD_PERI,
      COD_CLIE,
      TIP_POSI,
      NULL AS COD_VENT,
      VAL_CANT ,
      ' ' AS STA_PROC,
      '9999999999' AS COD_PROD,
      SUM(VAL_IMPO) AS VAL_IMPO,
      COD_FORM_PAGO,
      COR_AGRU
    FROM PER_SOLIC_MONET
    WHERE
     PAIS_COD_PAIS = psCodigoPais AND
     NUM_LOTE = psNumeroLote AND
     COD_TIPO_SOLI NOT IN ('SC11','SC12','SP26') AND
    ((psTipoOrigen = '01'  AND TIOR_TIPO_ORIG_DATO IN ('06','01')) OR
     (psTipoOrigen = '02'  AND TIOR_TIPO_ORIG_DATO IN ('06','02')) OR
     (psTipoOrigen = '03'  AND TIOR_TIPO_ORIG_DATO IN ('06','03')) OR
     (psTipoOrigen = '04'  AND TIOR_TIPO_ORIG_DATO IN ('06','04')) OR
     (psTipoOrigen = '08'  AND TIOR_TIPO_ORIG_DATO IN ('06','08')) OR
     (psTipoOrigen <> '01' AND psTipoOrigen <> '02' AND
      psTipoOrigen <> '03' AND psTipoOrigen <> '04' AND
      psTipoOrigen <> '08' AND TIOR_TIPO_ORIG_DATO = psTipoOrigen) )
    GROUP BY
       PAIS_COD_PAIS, COR_AGRU, COD_FORM_PAGO,
       COD_PERI, COD_CLIE, TIP_POSI, VAL_CANT ;

   CURSOR c_interfazGene IS
    SELECT
      PAIS_COD_PAIS,
      COD_PERI,
      COD_CLIE,
      TIP_POSI,
      NULL AS COD_VENT,
      VAL_CANT ,
      ' ' AS STA_PROC,
      '9999999999' AS COD_PROD,
      VAL_IMPO,
      COD_FORM_PAGO,
      COR_AGRU
    FROM PER_SOLIC_MONET
    WHERE
     PAIS_COD_PAIS = psCodigoPais AND
     NUM_LOTE_GENE = psNumeroLote AND
     COD_TIPO_SOLI NOT IN ('SC11','SC12','SP26')
    GROUP BY
       PAIS_COD_PAIS, COR_AGRU, COD_FORM_PAGO,
       COD_PERI, COD_CLIE, TIP_POSI, VAL_CANT;

   TYPE interfazRec IS RECORD
   (
   codigoPais                   PER_SOLIC_MONET.PAIS_COD_PAIS%TYPE,
   codigoPeriodo                PER_SOLIC_MONET.COD_PERI%TYPE,
   codigoCliente                PER_SOLIC_MONET.COD_CLIE%TYPE,
   tipoPosicion                 PER_SOLIC_MONET.TIP_POSI%TYPE,
   codigoVenta                  VARCHAR2(6),
   cantidad                     PER_SOLIC_MONET.VAL_CANT%TYPE,
   status                       CHAR(1),
   codigoProducto               VARCHAR2(10),
   importePercepcion            PER_SOLIC_MONET.VAL_IMPO%TYPE,
   formaPago                    PER_SOLIC_MONET.COD_FORM_PAGO%TYPE,
   correlativo                  PER_SOLIC_MONET.COR_SOLI_MONE%TYPE
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
    IF psTipoOrigen IS NOT NULL THEN
        OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoPais           ||';'||
                              interfazRecord(x).codigoPeriodo        ||';'||
                              interfazRecord(x).codigoCliente        ||';'||
                              interfazRecord(x).tipoPosicion         ||';'||
                              interfazRecord(x).codigoVenta          ||';'||
                              interfazRecord(x).cantidad             ||';'||
                              interfazRecord(x).status               ||';'||
                              interfazRecord(x).codigoProducto       ||';'||
                              to_char(interfazRecord(x).importePercepcion,'999999999.99')    ||';'||
                              interfazRecord(x).formaPago            ||';'||
                              to_char(interfazRecord(x).correlativo,'9999999999');
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
              END LOOP;
           END IF;
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
        CLOSE c_interfaz;
    ELSE
        OPEN c_interfazGene;
        LOOP
           FETCH c_interfazGene BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  lsLinea :=  interfazRecord(x).codigoPais           ||';'||
                              interfazRecord(x).codigoPeriodo        ||';'||
                              interfazRecord(x).codigoCliente        ||';'||
                              interfazRecord(x).tipoPosicion         ||';'||
                              interfazRecord(x).codigoVenta          ||';'||
                              interfazRecord(x).cantidad             ||';'||
                              interfazRecord(x).status               ||';'||
                              interfazRecord(x).codigoProducto       ||';'||
                              to_char(interfazRecord(x).importePercepcion,'999999999.99')    ||';'||
                              interfazRecord(x).formaPago            ||';'||
                              to_char(interfazRecord(x).correlativo,'9999999999');
                  UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
              END LOOP;
           END IF;
           EXIT WHEN c_interfazGene%NOTFOUND;
        END LOOP;
        CLOSE c_interfazGene;
    END IF;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_PER_SOLIC_MONET_POSIC: '||ls_sqlerrm);
END INT_PR_PER_SOLIC_MONET_POSIC;

/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de PDT  - SUNAT (PER-5)
Fecha Creacion    : 19/06/2013
Autor: Jean Pierre Jimenez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
****************************************************************************************/
  PROCEDURE INT_PR_PER_ARCHI_PDT(psCodigoPais     VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2,
   psFechaDesde         VARCHAR2,
                                 psFechaHasta     VARCHAR2) IS
   CURSOR c_interfaz IS
              SELECT  (SELECT cod_homo
                  FROM per_tipo_docum_ident_legal b
                 WHERE b.pais_cod_pais = psCodigoPais
                   AND cod_clas = 'DI'
                   AND cod_ahom = a.tip_doid) tip_doid,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'NUM_DOID',
                                                                num_doid) num_doid,
               per_pkg_proce_perce.per_fn_devue_pdt_razon_social(psCodigoPais,
                                                                 'TC',
                                                                 a.tip_dole,
                                                                 a.cod_clie) AS raz_soci,
               per_pkg_proce_perce.per_fn_devue_pdt_nombres(psCodigoPais,
                                                            'TC',
                                                            a.tip_dole,
                                                            a.cod_clie,
                                                            'PATERNO',
                                                            a.ape_pate) AS ape_pate,
               per_pkg_proce_perce.per_fn_devue_pdt_nombres(psCodigoPais,
                                                            'TC',
                                                            a.tip_dole,
                                                            a.cod_clie,
                                                            'MATERNO',
                                                            a.ape_mate) AS ape_mate,
               per_pkg_proce_perce.per_fn_devue_pdt_nombres(psCodigoPais,
                                                            'TC',
                                                            a.tip_dole,
                                                            a.cod_clie,
                                                            'NOMBRE',
                                                            a.val_nomb) AS val_nomb,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'SER_COPE',
                                                                ser_cope) ser_cope,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'NUM_COPE',
                                                                num_cope) num_cope,
             to_char(fec_cope, 'DD/MM/YYYY') AS fec_cope,
               per_pkg_proce_perce.per_fn_devue_indi_derec_crefi(psCodigoPais,
                                                                 'TC',
                                                                 a.tip_dole) AS ind_dcfi,
               '0' AS ven_mate_dest,
               per_pkg_proce_perce.per_fn_devue_indi_clien_lista(psCodigoPais,
                                                                 a.tip_doid,
                                                                 a.num_doid) AS ind_clli,
               per_pkg_proce_perce.per_fn_devue_pdt_monto_pago(a.cod_clie,
                                                               a.ser_cope,
                                                               a.num_cope,
                                                               psFechaDesde,
                                                               psFechaHasta) AS mon_perc,
               (SELECT cod_homo
                  FROM per_tipo_docum_ident_legal
                 WHERE pais_cod_pais = psCodigoPais
                   AND cod_clas = 'TC'
                   AND cod_ahom = a.tip_dole) tip_copa,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'SER_DOLE',
                                                                ser_dole) ser_dole,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'NUM_DOLE',
                                                                num_dole) num_dole,
             to_char(MAX(fec_dole), 'DD/MM/YYYY') AS fec_dole,
               per_pkg_proce_perce.per_fn_devue_pdt_monto_total(a.cod_clie,
                                                                a.ser_dole,
                                                                a.num_dole,
                                                                psFechaDesde,
                                                                psFechaHasta) AS mon_pago
                  FROM per_gtt_perce_conso a
                GROUP BY tip_clie,
                          cod_clie,
                          ser_cope,
                          num_cope,
                          fec_cope,
                          tip_doid,
                          num_doid,
                          tip_dole,
                          ser_dole,
                          num_dole,
                          a.ape_pate,
                          a.ape_mate,
                          a.val_nomb;

    TYPE interfazRec IS RECORD(
        TIP_DOID       PER_TIPO_DOCUM_IDENT_LEGAL.COD_HOMO%TYPE,
        NUM_DOID    PER_PERCE_CONSO.NUM_DOID%TYPE,
        RAZ_SOCI       VARCHAR2(20),
        APE_PATE       PER_PERCE_CONSO.APE_PATE%TYPE,
        APE_MATE      PER_PERCE_CONSO.APE_MATE%TYPE,
        VAL_NOMB       PER_PERCE_CONSO.VAL_NOMB%TYPE,
        SER_COPE      PER_PERCE_CONSO.SER_COPE%TYPE,
        NUM_COPE    PER_PERCE_CONSO.NUM_COPE%TYPE,
        FEC_COPE      VARCHAR2(10),
        IND_DCFI         VARCHAR2(1),
        VEN_MATE_DEST VARCHAR2(1),
        IND_CLLI        VARCHAR2(1),
        MON_PERC    PER_PERCE_CONSO.MON_PERC%TYPE,
        TIP_COPA       PER_TIPO_DOCUM_IDENT_LEGAL.COD_HOMO%TYPE,
        SER_DOLE      PER_PERCE_CONSO.SER_DOLE%TYPE,
        NUM_DOLE    PER_PERCE_CONSO.NUM_DOLE%TYPE,
        FEC_DOLE      VARCHAR2(10),
      MON_PAGO      PER_PERCE_CONSO.MON_PAGO%TYPE);

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;

    INSERT INTO PER_GTT_PERCE_CONSO
    SELECT *
  FROM PER_PERCE_CONSO a
       WHERE fec_cope >= to_date(psFechaDesde, 'DD/MM/YYYY')
         AND fec_cope < to_date(psFechaHasta, 'DD/MM/YYYY') + 1
                     AND mon_pago != 0
 AND mon_perc != 0
 AND (fla_canc IS NULL OR fla_canc != '1')
 AND (fla_canc IS NULL OR fla_canc != 'A');

    OPEN c_interfaz;
        LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                               psCodigosistema,
                                               psCodigoInterfaz,
                                               psNombreArchivo,
                                               lsDirTempo,
                                               lsNombreArchivo,
                                               V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          IF (interfazRecord(x).num_doid IS NULL OR interfazRecord(x).num_doid = '00000000') THEN
               interfazRecord(x).num_doid := '99999999';
          END IF;

                          lsLinea :=  interfazRecord(x).tip_doid     ||';'||
                     interfazRecord(x).num_doid     || ';' ||
                                      interfazRecord(x).raz_soci        ||';'||
                                      interfazRecord(x).ape_pate        ||';'||
                                      interfazRecord(x).ape_mate          ||';'||
                                      interfazRecord(x).val_nomb   ||';'||
                     interfazRecord(x).Ser_cope     || ';' ||
                                      interfazRecord(x).num_cope        ||';'||
                                      interfazRecord(x).fec_cope          ||';'||
                                      interfazRecord(x).ind_dcfi   ||';'||
                                      interfazRecord(x).ven_mate_dest        ||';'||
                                      interfazRecord(x).ind_clli        ||';'||
                                      interfazRecord(x).mon_perc          ||';'||
                                      interfazRecord(x).tip_copa   ||';'||
                                      interfazRecord(x).ser_dole        ||';'||
                                      interfazRecord(x).num_dole        ||';'||
                                      interfazRecord(x).fec_dole          ||';'||
                     interfazRecord(x).mon_pago     || ';' ||
                     '' ;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR',
                                             lsDirTempo,
                                             psNombreArchivo,
                                             lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INT_PR_PER_ARCHI_PDT: ' || ls_sqlerrm);

END INT_PR_PER_ARCHI_PDT;


/**************************************************************************************
Descripcion       : Genera Interfaz de Envio de PDT  - SUNAT (PER-5)
Fecha Creacion    : 19/06/2013
Autor: Jean Pierre Jimenez
Parametros:
 psCodigoPais : Codigo de Pais
 psCodigoSistema : Codigo de Empresa
 psCodigoInterfaz : Codigo de Interfaz
 psNombreArchivo : Nombre Arcchivo
****************************************************************************************/
  PROCEDURE INT_PR_PER_ARCHI_PDT2(psCodigoPais     VARCHAR2,
   psCodigoSistema        VARCHAR2,
   psCodigoInterfaz       VARCHAR2,
   psNombreArchivo        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   psnumerolote           VARCHAR2,
   psFechaDesde         VARCHAR2,
                                 psFechaHasta     VARCHAR2) IS
   CURSOR c_interfaz IS
              SELECT  (SELECT cod_homo
                  FROM per_tipo_docum_ident_legal b
                 WHERE b.pais_cod_pais = psCodigoPais
                   AND cod_clas = 'DI'
                   AND cod_ahom = a.tip_doid) tip_doid,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'NUM_DOID',
                                                                num_doid) num_doid,
               per_pkg_proce_perce.per_fn_devue_pdt_razon_social(psCodigoPais,
                                                                 'TC',
                                                                 a.tip_dole,
                                                                 a.cod_clie) AS raz_soci,
               per_pkg_proce_perce.per_fn_devue_pdt_nombres(psCodigoPais,
                                                            'TC',
                                                            a.tip_dole,
                                                            a.cod_clie,
                                                            'PATERNO',
                                                            a.ape_pate) AS ape_pate,
               per_pkg_proce_perce.per_fn_devue_pdt_nombres(psCodigoPais,
                                                            'TC',
                                                            a.tip_dole,
                                                            a.cod_clie,
                                                            'MATERNO',
                                                            a.ape_mate) AS ape_mate,
               per_pkg_proce_perce.per_fn_devue_pdt_nombres(psCodigoPais,
                                                            'TC',
                                                            a.tip_dole,
                                                            a.cod_clie,
                                                            'NOMBRE',
                                                            a.val_nomb) AS val_nomb,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'SER_COPE',
                                                                ser_cope) ser_cope,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'NUM_COPE',
                                                                num_cope) num_cope,
             to_char(fec_cope, 'DD/MM/YYYY') AS fec_cope,
               per_pkg_proce_perce.per_fn_devue_indi_derec_crefi(psCodigoPais,
                                                                 'TC',
                                                                 a.tip_dole) AS ind_dcfi,
               '0' AS ven_mate_dest,
               per_pkg_proce_perce.per_fn_devue_indi_clien_lista(psCodigoPais,
                                                                 a.tip_doid,
                                                                 a.num_doid) AS ind_clli,
               per_pkg_proce_perce.per_fn_devue_pdt_monto_pago2(a.cod_clie,
                                                               a.ser_cope,
                                                               a.num_cope,
                                                               psFechaDesde,
                                                               psFechaHasta) AS mon_perc,
               (SELECT cod_homo
                  FROM per_tipo_docum_ident_legal
                 WHERE pais_cod_pais = psCodigoPais
                   AND cod_clas = 'TC'
                   AND cod_ahom = a.tip_dole) tip_copa,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'SER_DOLE',
                                                                ser_dole) ser_dole,
               per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                                'PER',
                                                                'NUM_DOLE',
                                                                num_dole) num_dole,
             to_char(MAX(fec_dole), 'DD/MM/YYYY') AS fec_dole,
               per_pkg_proce_perce.per_fn_devue_pdt_monto_total2(a.cod_clie,
                                                                a.ser_dole,
                                                                a.num_dole,
                                                                psFechaDesde,
                                                                psFechaHasta) AS mon_pago
                  FROM PER_TMP_PERCE_CONSO a
                  WHERE mon_pago != 0
                    AND mon_perc != 0
                    AND (fla_canc IS NULL OR fla_canc != '1')
                    AND (fla_canc IS NULL OR fla_canc != 'A')              
                GROUP BY tip_clie,
                          cod_clie,
                          ser_cope,
                          num_cope,
                          fec_cope,
                          tip_doid,
                          num_doid,
                          tip_dole,
                          ser_dole,
                          num_dole,
                          a.ape_pate,
                          a.ape_mate,
                          a.val_nomb;

    TYPE interfazRec IS RECORD(
        TIP_DOID       PER_TIPO_DOCUM_IDENT_LEGAL.COD_HOMO%TYPE,
        NUM_DOID    PER_PERCE_CONSO.NUM_DOID%TYPE,
        RAZ_SOCI       VARCHAR2(20),
        APE_PATE       PER_PERCE_CONSO.APE_PATE%TYPE,
        APE_MATE      PER_PERCE_CONSO.APE_MATE%TYPE,
        VAL_NOMB       PER_PERCE_CONSO.VAL_NOMB%TYPE,
        SER_COPE      PER_PERCE_CONSO.SER_COPE%TYPE,
        NUM_COPE    PER_PERCE_CONSO.NUM_COPE%TYPE,
        FEC_COPE      VARCHAR2(10),
        IND_DCFI         VARCHAR2(1),
        VEN_MATE_DEST VARCHAR2(1),
        IND_CLLI        VARCHAR2(1),
        MON_PERC    PER_PERCE_CONSO.MON_PERC%TYPE,
        TIP_COPA       PER_TIPO_DOCUM_IDENT_LEGAL.COD_HOMO%TYPE,
        SER_DOLE      PER_PERCE_CONSO.SER_DOLE%TYPE,
        NUM_DOLE    PER_PERCE_CONSO.NUM_DOLE%TYPE,
        FEC_DOLE      VARCHAR2(10),
      MON_PAGO      PER_PERCE_CONSO.MON_PAGO%TYPE);

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    
    EXECUTE IMMEDIATE 'TRUNCATE TABLE PER_TMP_PERCE_CONSO';

    INSERT INTO PER_TMP_PERCE_CONSO
    SELECT PAIS_COD_PAIS,
            COR_PECO,
            COD_SOCI,
            COD_CANA,
            COD_ACCE,
            COD_SBAC,
            TIP_CLIE,
            COD_CLIE,
            SER_COPE,
            NUM_COPE,
            SEC_COPE,
            TIP_DOID,
            NUM_DOID,
            TIP_DOLE,
            FEC_DOLE,
            SER_DOLE,
            NUM_DOLE,
            MON_PAGO,
            MON_PERC,
            POR_PERC,
            MON_TODL,
            FEC_COPE,
            FLA_PROC,
            FLA_CANC,
            USU_DIGI,
            FEC_DIGI,
            USU_MODI,
            FEC_MODI,
            EST_PECO,
            APE_PATE,
            APE_MATE,
            VAL_NOMB
      FROM PER_PERCE_CONSO a
     WHERE fec_cope >= to_date(psFechaDesde, 'DD/MM/YYYY')
       AND fec_cope < to_date(psFechaHasta, 'DD/MM/YYYY') + 1;
                     

    OPEN c_interfaz;
        LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
        GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                               psCodigosistema,
                                               psCodigoInterfaz,
                                               psNombreArchivo,
                                               lsDirTempo,
                                               lsNombreArchivo,
                                               V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                 IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
          IF (interfazRecord(x).num_doid IS NULL OR interfazRecord(x).num_doid = '00000000') THEN
               interfazRecord(x).num_doid := '99999999';
          END IF;

                          lsLinea :=  interfazRecord(x).tip_doid     ||';'||
                     interfazRecord(x).num_doid     || ';' ||
                                      interfazRecord(x).raz_soci        ||';'||
                                      interfazRecord(x).ape_pate        ||';'||
                                      interfazRecord(x).ape_mate          ||';'||
                                      interfazRecord(x).val_nomb   ||';'||
                     interfazRecord(x).Ser_cope     || ';' ||
                                      interfazRecord(x).num_cope        ||';'||
                                      interfazRecord(x).fec_cope          ||';'||
                                      interfazRecord(x).ind_dcfi   ||';'||
                                      interfazRecord(x).ven_mate_dest        ||';'||
                                      interfazRecord(x).ind_clli        ||';'||
                                      interfazRecord(x).mon_perc          ||';'||
                                      interfazRecord(x).tip_copa   ||';'||
                                      interfazRecord(x).ser_dole        ||';'||
                                      interfazRecord(x).num_dole        ||';'||
                                      interfazRecord(x).fec_dole          ||';'||
                     interfazRecord(x).mon_pago     || ';' ||
                     '' ;

                       UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
          utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
      GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR',
                                             lsDirTempo,
                                             psNombreArchivo,
                                             lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INT_PR_PER_ARCHI_PDT2: ' || ls_sqlerrm);

END INT_PR_PER_ARCHI_PDT2;

    /**************************************************************************************
    Descripcion       : Genera Interfaz de Envio de REsumen de percepciones para sunat (PER-7)
    Fecha Creacion    : 21/01/2016
    Autor: Ivan Tocto Jaimes
    ****************************************************************************************/
  PROCEDURE INT_PR_PER_ENVIO_RESUM_PERCE(
    psCodigoPais           VARCHAR2,
    psCodigoSistema        VARCHAR2,
    psCodigoInterfaz       VARCHAR2,
    psNombreArchivo        VARCHAR2,
    psCodigoUsuario        VARCHAR2,
    psNumeroLote           VARCHAR2,
    psFechaDesde           VARCHAR2 ) IS
       
   CURSOR c_interfaz IS   
       SELECT '1' con_inte, --1 Conx Internet.
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'SER_COPE',
                                                            p.ser_cope) ser_cope, --2 serie comprobante
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'NUM_COPE',
                                                            p.num_cope) num_cope, --3 numero comprobante
           to_char(p.fec_cope, 'DD/MM/YYYY') FEC_COPE, --4 fecha emision comprobante,             
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'NUM_DOID',
                                                            p.num_doid) num_docu, --5 documento identidad
           (SELECT cod_homo
              FROM per_tipo_docum_ident_legal b
             WHERE b.pais_cod_pais = psCodigoPais
               AND cod_clas = 'DI'
               AND cod_ahom = p.tip_doid) tip_docu, --6 tipo documento identidad                                                                               
           (SELECT UPPER(mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' ||
                         mc.val_nom2)
              FROM MAE_CLIEN mc
             where mc.cod_clie = p.cod_cons) val_nomb, --7 nombre                                                                                 
           '01' tip_perc, --8 Percepciones venta interna
           p.por_perc , --9 tasa de percepcion
           do.imp_perc_perc mon_perc, --10 importe total percibido
           to_char(nvl(do.imp_tota_docu, 0) + nvl(do.imp_perc_perc, 0)) mon_todl, --11  importe total cobrado
           (SELECT cod_homo
              FROM per_tipo_docum_ident_legal
             WHERE pais_cod_pais = psCodigoPais
               AND cod_clas = 'TC'
               AND cod_ahom = p.tip_dole) tip_docu_rela, --12 tipo de documento relacionado
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'SER_DOLE',
                                                            p.ser_dole) num_seri_docu_rela, --13 serie de documento relacionado
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'NUM_DOLE',
                                                            p.num_dole) num_dole, --14 num_dole
           to_char(p.fec_dole, 'DD/MM/YYYY') fec_emis_docu_rela, --15 fecha de emision doc relacionado
           do.imp_tota_docu mon_pago, --16 importe total doc relacionado
           'PEN' tip_mone, --17 tipo de moneda
           to_char(pa.fec_pago, 'DD/MM/YYYY') FEC_COBR, --18 fecha de cobro
           p.cor_pevd, --19 Numero de cobro oid
           to_char(nvl(pa.mon_pago, 0) - nvl(pa.mon_perc, 0)) MON_COBR_SPER, --20 Importe de cobro sin percepcion 
           'PEN' tip_mone_imco, --21 Moneda del importe de cobro
           do.imp_perc_perc MON_PERC_PERC, --22 Importe percibido de la percepcion
           to_char(p.fec_cope, 'DD/MM/YYYY') fec_perc, --23 Fecha de percepción
           pa.mon_perc MON_TOTA_COBR, --24 Monto total a cobrar
           null mon_extr_retc, --25 Moneda extranjera referencia tipo de cambio
           null tip_camb_apli, --26 Tipo de cambio aplicado
           null fec_tip_camb_apli --27 Fecha de tipo de cambio aplicado
      FROM per_regis_pagos       pa,
           per_cuent_corri_docle do,
           PER_PERCE_VENTA_DIREC p
      where pa.num_soco = do.num_soli_cons
       and p.REPA_COR_REPA = pa.COR_REPA
       and p.num_soco = pa.num_soco      
       and trunc(p.fec_cope) = to_date(psFechaDesde, 'dd/mm/yyyy')
      union
      SELECT '1' con_inte, --1 Conx Internet.
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'SER_COPE',
                                                            con.ser_cope) ser_cope, --2 serie comprobante
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'NUM_COPE',
                                                            con.num_cope) num_cope, --3 numero comprobante
           to_char(con.fec_cope, 'DD/MM/YYYY') FEC_COPE, --4 fecha emision comprobante,             
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'NUM_DOID',
                                                            con.num_doid) num_docu, --5 documento identidad
           (SELECT cod_homo
              FROM per_tipo_docum_ident_legal b
             WHERE b.pais_cod_pais = psCodigoPais
               AND cod_clas = 'DI'
               AND cod_ahom = con.tip_doid) tip_docu, --6 tipo documento identidad                                                                                
           (SELECT UPPER(mc.val_ape1 || ' ' || mc.val_ape2 || ' ' || mc.val_nom1 || ' ' ||
                         mc.val_nom2)
              FROM MAE_CLIEN mc
             where mc.cod_clie = con.cod_clie) val_nomb, --7 nombre                                                                                 
           '01' tip_perc, --8 Percepciones venta interna
           con.por_perc, --9 tasa de percepcion
           con.mon_perc, --10 monto total percibido,
           to_char(con.mon_pago) mon_todl, --11  importe total cobrado
           (SELECT cod_homo
              FROM per_tipo_docum_ident_legal
             WHERE pais_cod_pais = psCodigoPais
               AND cod_clas = 'TC'
               AND cod_ahom = con.tip_dole) tip_docu_rela, --12 tipo de documento relacionado
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'SER_DOLE',
                                                            con.ser_dole) num_seri_docu_rela, --13 serie de documento relacionado
           per_pkg_proce_perce.per_fn_devue_pdt_campo_reemp(psCodigoPais,
                                                            'PER',
                                                            'NUM_DOLE',
                                                            con.num_dole) num_dole, --14 num_dole
           to_char(con.fec_dole, 'DD/MM/YYYY') fec_emis_docu_rela, --15 fecha de emision doc relacionado
           con.mon_todl mon_pago, --16 importe total doc relacionado
           'PEN' tip_mone, --17 tipo de moneda
           to_char(con.fec_dole, 'DD/MM/YYYY') FEC_COBR, --18 fecha de cobro
           con.cor_peco cor_pevd, --19 Numero de cobro oid
           to_char(con.mon_todl) MON_COBR_SPER, --20 Importe de cobro sin percepcion
           'PEN' tip_mone_imco, --21 Moneda del importe de cobro
           con.mon_perc MON_PERC_PERC, --22 Importe percibido de la percepcion
           to_char(con.fec_cope, 'DD/MM/YYYY') fec_perc, --23 Fecha de percepción
           con.mon_perc MON_TOTA_COBR, --24 Monto total a cobrar
           null mon_extr_retc, --25 Moneda extranjera referencia tipo de cambio
           null tip_camb_apli, --26 Tipo de cambio aplicado
           null fec_tip_camb_apli --27 Fecha de tipo de cambio aplicado
      from PER_PERCE_CONSO con
      where con.cod_cana <> 'VD'
       and trunc(con.fec_cope) = to_date(psFechaDesde, 'dd/mm/yyyy');

    TYPE interfazRec IS RECORD(
        CON_INTE    VARCHAR2(1),
        SER_COPE    PER_PERCE_VENTA_DIREC.SER_COPE%TYPE,
        NUM_COPE    VARCHAR2(15),
        FEC_COPE    VARCHAR2(10),
        NUM_DOCU    VARCHAR2(15),
        TIP_DOCU    VARCHAR2(3),
        VAL_NOMB    VARCHAR2(100),
        TIP_PERC    VARCHAR2(2),
        POR_PERC    PER_PERCE_VENTA_DIREC.POR_PERC%TYPE,
        MON_PERC    PER_PERCE_VENTA_DIREC.MON_PERC%TYPE,
        MON_TODL    VARCHAR2(15),
        TIP_DOCU_RELA    VARCHAR2(3),
        NUM_SERI_DOCU_RELA    VARCHAR2(20),
        NUM_DOLE    VARCHAR2(20),
        FEC_EMIS_DOCU_RELA    VARCHAR2(10),
        MON_PAGO    PER_PERCE_VENTA_DIREC.MON_PAGO%TYPE,
        TIP_MONE    VARCHAR2(3),
        FEC_COBR    VARCHAR2(10),
        COR_PEVD    PER_PERCE_VENTA_DIREC.COR_PEVD%TYPE,
        MON_COBR_SPER    VARCHAR2(15),
        TIP_MONE_IMCO    VARCHAR2(3),
        MON_PERC_PERC    PER_PERCE_VENTA_DIREC.MON_PERC%TYPE,
        FEC_PERC    VARCHAR2(10),
        MON_TOTA_COBR    PER_PERCE_VENTA_DIREC.MON_PAGO%TYPE,
        MON_EXTR_RETC    PER_PERCE_VENTA_DIREC.MON_PAGO%TYPE,
        TIP_CAMB_APLI    PER_PERCE_VENTA_DIREC.MON_PAGO%TYPE,
        FEC_TIP_CAMB_APLI    VARCHAR2(10));

   TYPE interfazRecTab  IS TABLE OF interfazRec ;
   interfazRecord interfazRecTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;

   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);

   lbAbrirUtlFile      BOOLEAN;

BEGIN

    /* Generando Archivo de Texto (Detalle) */
    lbAbrirUtlFile := TRUE;
    
    OPEN c_interfaz;
    LOOP
        FETCH c_interfaz BULK COLLECT
        INTO interfazRecord LIMIT W_FILAS;
                 /* Procedimiento inicial para generar interfaz */
                 IF lbAbrirUtlFile THEN
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais,
                                                           psCodigosistema,
                                                           psCodigoInterfaz,
                                                           psNombreArchivo,
                                                           lsDirTempo,
                                                           lsNombreArchivo,
                                                           V_HANDLE);
                    lbAbrirUtlFile := FALSE;
                 END IF;

                IF interfazRecord.COUNT > 0 THEN
                    FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                    
                        lsLinea :=  interfazRecord(x).CON_INTE|| ';' ||
                                        interfazRecord(x).SER_COPE|| ';' ||
                                        interfazRecord(x).NUM_COPE|| ';' ||
                                        interfazRecord(x).FEC_COPE|| ';' ||
                                        interfazRecord(x).NUM_DOCU|| ';' ||
                                        interfazRecord(x).TIP_DOCU|| ';' ||
                                        interfazRecord(x).VAL_NOMB|| ';' ||
                                        interfazRecord(x).TIP_PERC|| ';' ||
                                        interfazRecord(x).POR_PERC|| ';' ||
                                        interfazRecord(x).MON_PERC|| ';' ||
                                        interfazRecord(x).MON_TODL|| ';' ||
                                        interfazRecord(x).TIP_DOCU_RELA|| ';' ||
                                        interfazRecord(x).NUM_SERI_DOCU_RELA|| ';' ||
                                        interfazRecord(x).NUM_DOLE|| ';' ||
                                        interfazRecord(x).FEC_EMIS_DOCU_RELA|| ';' ||
                                        interfazRecord(x).MON_PAGO|| ';' ||
                                        interfazRecord(x).TIP_MONE|| ';' ||
                                        interfazRecord(x).FEC_COBR|| ';' ||
                                        interfazRecord(x).COR_PEVD|| ';' ||
                                        interfazRecord(x).MON_COBR_SPER|| ';' ||
                                        interfazRecord(x).TIP_MONE_IMCO|| ';' ||
                                        interfazRecord(x).MON_PERC_PERC|| ';' ||
                                        interfazRecord(x).FEC_PERC|| ';' ||
                                        interfazRecord(x).MON_TOTA_COBR|| ';' ||
                                        interfazRecord(x).MON_EXTR_RETC|| ';' ||
                                        interfazRecord(x).TIP_CAMB_APLI|| ';' ||
                                        interfazRecord(x).FEC_TIP_CAMB_APLI;
                    
                        UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                    END LOOP;
                 END IF;
             EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;

    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
         /* Procedimiento final para generar interfaz */
        GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR',
                                             lsDirTempo,
                                             psNombreArchivo,
                                             lsNombreArchivo);
    END IF;

    RETURN;

  EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123,
                              'ERROR INT_PR_PER_ENVIO_RESUM_PERCE: ' || ls_sqlerrm);

END INT_PR_PER_ENVIO_RESUM_PERCE;

END INT_PKG_PERCE;
/
