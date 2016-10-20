CREATE OR REPLACE PACKAGE INT_PKG_MICRO IS
/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Interfaz que envía Microseguros para pago canales regulares
Fecha Creacion    : 11/05/2010
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoMarca   : Codigo de Marca
          psCodigoCanal   : Codigo de Canal
          psCodigoPeriodo : Codigo Periodo
          psFechaFacturacion: Fecha Facturacion
             psNumLote      :    Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_MIC_ENVIO_PAGO_CANAL
  (psCodigoPais VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);

/***************************************************************************
Descripcion       : Interfaz que envía Informacion Aseguradoras
Fecha Creacion    : 19/05/2010
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoMarca   : Codigo de Marca
          psCodigoCanal   : Codigo de Canal
          psCodigoPeriodo : Codigo Periodo
          psFechaFacturacion: Fecha Facturacion
             psNumLote      :    Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_MIC_ENVIO_ASEGU
  (psCodigoPais VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2);

/***************************************************************************
Descripcion : Funcion que devuelve  domiclio de la consultora
Fecha Creacion : 20/05/2010
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Oid Cliente

***************************************************************************/
FUNCTION MIC_FN_DEVUE_DOMIC(
 psOidClient NUMBER
)
RETURN VARCHAR2;


END INT_PKG_MICRO;
/

CREATE OR REPLACE PACKAGE BODY INT_PKG_MICRO IS

/***************************************************************************
Descripcion       : Interfaz que envía pagos microseguros
Fecha Creacion    : 11/05/2010
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoMarca   : Codigo de Marca
          psCodigoCanal   : Codigo de Canal
          psCodigoPeriodo : Codigo Periodo
          psFechaFacturacion: Fecha Facturacion
          psNumLote      : Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_MIC_ENVIO_PAGO_CANAL
  (psCodigoPais VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2)
IS
    CURSOR c_interfaz IS
            SELECT A.COD_CLIE,
                   '' COD_DIGI_CTRL,
                   MTD.COD_TIPO_DOCU,
                   MCI.NUM_DOCU_IDEN,
                   '' DIG_CONT,
                   NVL(MC.VAL_APE1,' ') VAL_APE1,
                   NVL(MC.VAL_APE2,' ') VAL_APE2,
                   NVL(MC.VAL_NOM1,' ') VAL_NOM1,
                   NVL(MC.VAL_NOM2,' ') VAL_NOM2,
                   GEN_PKG_GENER.GEN_FN_CLIEN_DATOS(MC.COD_CLIE,'COD_ZONA') COD_ZONA,
                   '' COD_TIPO_CLIE,
                   '' COD_SUBT_CLIE,
                   A.COD_MICR,
                   A.COD_COBE,
                   LTRIM(TO_CHAR(A.VAL_MONT,'999999999.99')) VAL_MONT ,
                   A.COD_GRUP
            FROM MIC_CLIEN_APTAS A,
                 MAE_CLIEN MC,
                 MAE_CLIEN_IDENT MCI,
                 MAE_TIPO_DOCUM MTD
            WHERE
                 A.IND_APTA = 1
                 AND A.IND_ERRO = 0
                 AND A.COD_CLIE = MC.COD_CLIE
                 AND MC.OID_CLIE=MCI.CLIE_OID_CLIE
                 AND MCI.TDOC_OID_TIPO_DOCU=MTD.OID_TIPO_DOCU;
                 --AND MTD.COD_TIPO_DOCU='01';

    TYPE interfazrec IS RECORD(
           codigoConsultora           mae_clien.cod_clie%TYPE,
           codigoDigitoControl        mae_clien.cod_digi_ctrl%TYPE,
           codigoTipoDocumento        mae_tipo_docum.COD_TIPO_DOCU%TYPE,
           numeroDocumentoIdentidad   mae_clien_ident.num_docu_iden%TYPE,
           digitoControlDocumento     VARCHAR2(2),
           apellidoPaterno            mae_clien.val_ape1%TYPE,
           apellidoMaterno            mae_clien.val_ape2%TYPE,
           primerNombre               mae_clien.val_nom1%TYPE,
           segundoNombre              mae_clien.val_nom2%TYPE,
           codigoZona                 zon_zona.cod_zona%TYPE,
           tipoCliente                mae_tipo_clien.COD_TIPO_CLIE%TYPE,
           subTipoCliente             mae_subti_clien.COD_SUBT_CLIE%TYPE,
           tipoClasificacion          mae_tipo_clasi_clien.COD_TIPO_CLAS%TYPE,
           clasificacion              mae_clasi.COD_CLAS%TYPE,
           montoDeuda                 VARCHAR2(18),
           codigoGrupo                MIC_CLIEN_APTAS.COD_GRUP%TYPE
     );

   TYPE interfazTab  IS TABLE OF interfazrec ;

   interfazRecord interfazTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag                       VARCHAR2(1):=' ';
BEGIN

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                  psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;

           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

                  IF(interfazRecord(x).codigoTipoDocumento='01' AND length(interfazRecord(x).numeroDocumentoIdentidad) <= 15)THEN

                    lsLinea :=interfazRecord(x).codigoConsultora         ||';'||
                              interfazRecord(x).codigoDigitoControl      ||';'||
                              interfazRecord(x).codigoTipoDocumento      ||';'||
                              interfazRecord(x).numeroDocumentoIdentidad ||';'||
                              interfazRecord(x).digitoControlDocumento   ||';'||
                              interfazRecord(x).apellidoPaterno          ||';'||
                              interfazRecord(x).apellidoMaterno          ||';'||
                              interfazRecord(x).primerNombre             ||';'||
                              interfazRecord(x).segundoNombre            ||';'||
                              interfazRecord(x).codigoZona               ||';'||
                              interfazRecord(x).tipoCliente              ||';'||
                              interfazRecord(x).subTipoCliente           ||';'||
                              interfazRecord(x).tipoClasificacion        ||';'||
                              interfazRecord(x).clasificacion            ||';'||
                              interfazRecord(x).montoDeuda;
                              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

                               UPDATE MIC_CLIEN_APTAS
                               SET  FEC_ENVI       = SYSDATE,
                                    FEC_ULTI_ACTU = SYSDATE
                               WHERE  COD_CLIE      = interfazRecord(x).codigoConsultora
                                AND    COD_MICR      = interfazRecord(x).tipoClasificacion
                                AND    COD_COBE      = interfazRecord(x).clasificacion
                                AND    COD_GRUP      = interfazRecord(x).codigoGrupo
                                AND    COD_TIPO_DOCU = interfazRecord(x).codigoTipoDocumento;

                  ELSE

                    IF(length(interfazRecord(x).numeroDocumentoIdentidad) > 15) then
                        UPDATE MIC_CLIEN_APTAS
                        SET  IND_ERRO      = 2,
                             FEC_ULTI_ACTU = SYSDATE
                        WHERE  COD_CLIE      = interfazRecord(x).codigoConsultora
                        AND    COD_MICR      = interfazRecord(x).tipoClasificacion
                        AND    COD_COBE      = interfazRecord(x).clasificacion
                        AND    COD_GRUP      = interfazRecord(x).codigoGrupo
                        AND    COD_TIPO_DOCU = interfazRecord(x).codigoTipoDocumento;
                    else
                        UPDATE MIC_CLIEN_APTAS
                        SET  IND_ERRO      = 1,
                             FEC_ULTI_ACTU = SYSDATE
                        WHERE  COD_CLIE      = interfazRecord(x).codigoConsultora
                        AND    COD_MICR      = interfazRecord(x).tipoClasificacion
                        AND    COD_COBE      = interfazRecord(x).clasificacion
                        AND    COD_GRUP      = interfazRecord(x).codigoGrupo
                        AND    COD_TIPO_DOCU = interfazRecord(x).codigoTipoDocumento;
                    end if;


                  END IF;


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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MIC_ENVIO_PAGO_CANAL: '||ls_sqlerrm);
END INT_PR_MIC_ENVIO_PAGO_CANAL;

/***************************************************************************
Descripcion       : Interfaz que envía Informacion Aseguradoras
Fecha Creacion    : 19/05/2010
Autor             : Sergio Buchelli
Parametros:
          psCodigoPais   : Codigo de Pais
          psCodigoMarca   : Codigo de Marca
          psCodigoCanal   : Codigo de Canal
          psCodigoPeriodo : Codigo Periodo
          psFechaFacturacion: Fecha Facturacion
             psNumLote      :    Numero Lote
          psCodigoSistema   : Codigo de Sistema
          psCodigoInterfaz  : Codigo Interfaz
          psNombreArchivo   : Nombre Archivo
***************************************************************************/
PROCEDURE INT_PR_MIC_ENVIO_ASEGU
  (psCodigoPais VARCHAR2,
   psCodigoMarca VARCHAR2,
   psCodigoCanal VARCHAR2,
   psCodigoPeriodo VARCHAR2,
   psFechaFacturacion VARCHAR2,
   psNumLote VARCHAR2,
   psCodigoSistema VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo VARCHAR2)
IS
    CURSOR c_interfaz IS
            SELECT DISTINCT A.COD_CLIE,
                   MC.OID_CLIE,
                   NVL(MC.VAL_APE1,' ') VAL_APE1,
                   NVL(MC.VAL_APE2,' ') VAL_APE2,
                   NVL(MC.VAL_NOM1,' ')  || ' ' ||  NVL(MC.VAL_NOM2,' ') VAL_NOM,
                   MTD.COD_TIPO_DOCU,
                   SUBSTR(MCI.NUM_DOCU_IDEN,3),
                  TO_CHAR(CDA.FEC_NACI,'yyyy-MM-dd') FEC_NACI
            FROM MIC_CLIEN_APTAS A,
                 MAE_CLIEN MC,
                 MAE_CLIEN_IDENT MCI,
                 MAE_TIPO_DOCUM MTD,
                 MAE_CLIEN_DATOS_ADICI CDA
            WHERE
                 A.IND_APTA = 1
                 AND A.IND_ERRO = 0
                 AND A.COD_CLIE = MC.COD_CLIE
                 AND MC.OID_CLIE=MCI.CLIE_OID_CLIE
                 AND MCI.TDOC_OID_TIPO_DOCU=MTD.OID_TIPO_DOCU
                 AND CDA.CLIE_OID_CLIE = MC.OID_CLIE;


    TYPE interfazrec IS RECORD(
           codigoConsultora           mae_clien.cod_clie%TYPE,
           oidConsultora              mae_clien.oid_clie%TYPE,
           apellidoPaterno            mae_clien.val_ape1%TYPE,
           apellidoMaterno            mae_clien.val_ape2%TYPE,
           nombres                    VARCHAR2(100),
           tipoDocumento              MAE_TIPO_DOCUM.COD_TIPO_DOCU%TYPE,
           numDocumento               MAE_CLIEN_IDENT.NUM_DOCU_IDEN%TYPE,
           fechaNacimiento            VARCHAR2(10)
     );

   TYPE interfazTab  IS TABLE OF interfazrec ;

   interfazRecord interfazTab;


 CURSOR c_banco(numDocumento VARCHAR2) IS
    SELECT Z.*
    FROM MIC_PAGOS Z
    WHERE Z.NUM_DOCU = numDocumento
      AND TO_CHAR(Z.FEC_PAGO,'MM') = TRIM(SUBSTR(psFechaFacturacion,4,2))--TO_CHAR(psFechaFacturacion,'MM')
    ORDER BY  VAL_PAGO DESC, FEC_PAGO ASC;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lbAbrirUtlFile      BOOLEAN;
  lsNombreArchivo     VARCHAR2(50);
  lsFlag              VARCHAR2(1):=' ';
  lsdomicilio         VARCHAR2(600);
  lsContPagos         NUMBER;
  lsmicroseguro       MIC_MICRO.COD_MICR_EXTE%TYPE;
  lscobertura         MIC_COBER.COD_COBE_EXTE%TYPE;
  lnImporteAbonado    MIC_PAGOS.VAL_PAGO%TYPE;
  lsbanco             MIC_BANCO.DES_BANC%TYPE;
  lsfechaPago         VARCHAR2(10);
  lnContPagos         NUMBER;
  lnNumCont           NUMBER:=0;
BEGIN

      /* Generando Archivo de Texto (Detalle) */
        lbAbrirUtlFile := TRUE;
        OPEN c_interfaz;
        LOOP
           FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
           /* Procedimiento inicial para generar interfaz */
           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                  psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;

           IF interfazRecord.COUNT > 0 THEN
              FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

                --x cada client
                    lsdomicilio :=MIC_FN_DEVUE_DOMIC(interfazRecord(x).oidConsultora);
                	    --Se recupera el "mes de proceso" de: la fecha de facturación que se ha solicitado por  pantalla
                        --o de la fecha del sistema si es del proceso automático;

                    SELECT count(1) INTO lnContPagos
                        FROM MIC_PAGOS Z
                        WHERE Z.NUM_DOCU = interfazRecord(x).numDocumento
                          AND TO_CHAR(Z.FEC_PAGO,'MM') = TRIM(SUBSTR(psFechaFacturacion,4,2));--TO_CHAR(psFechaFacturacion,'MM') ;

                  IF(lnContPagos > 0) THEN
                    lnNumCont:=0;
                    FOR cBanco IN c_banco(interfazRecord(x).numDocumento) LOOP
                          lnNumCont:=lnNumCont+1;
                          begin
                              SELECT X.DES_BANC
                              INTO lsbanco
                              FROM MIC_BANCO X
                              WHERE X.COD_BANC_EXTE =cBanco.COD_BANC_EXTE
                               AND X.IND_ACTI='1';
                          exception
                            when NO_DATA_FOUND then
                                 lsbanco:='';
                          end;

                          begin
                              SELECT (SELECT Z.COD_MICR_EXTE
                                      FROM MIC_MICRO Z
                                      WHERE Z.COD_MICR = X.COD_MICR) MICRO,
                                     (SELECT Z.COD_COBE_EXTE
                                      FROM MIC_COBER  Z
                                      WHERE Z.COD_COBE = X.COD_COBE) COBER
                              INTO lsmicroseguro,lscobertura
                              FROM MIC_COBER_GRUPO_CLIEN  X
                              WHERE X.VAL_PREC =cBanco.VAL_PAGO;
                          exception
                            when OTHERS then
                                 lsmicroseguro:='';
                                 lscobertura:='';

                          end;


                              lsLinea :=interfazRecord(x).apellidoPaterno   ||';'||
                              interfazRecord(x).apellidoMaterno             ||';'||
                              interfazRecord(x).nombres                     ||';'||
                              interfazRecord(x).tipoDocumento               ||';'||
                              interfazRecord(x).numDocumento                ||';'||
                              interfazRecord(x).fechaNacimiento             ||';'||
                              lsdomicilio                                   ||';'||
                              lsmicroseguro                                 ||';'||
                              lscobertura                                   ||';'||
                              cBanco.VAL_PAGO                               ||';'||
                              lsbanco                                       ||';'||
                              TO_CHAR(cBanco.FEC_PAGO,'yyyy-MM-dd')         ||';'||
                              lnNumCont;

                              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );


                    END LOOP;

                  END IF;

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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_MIC_ENVIO_ASEGU: '||ls_sqlerrm);
END INT_PR_MIC_ENVIO_ASEGU;


/***************************************************************************
Descripcion : Funcion que devuelve  domiclio de la consultora
Fecha Creacion : 20/05/2010
Autor : Sergio Buchelli
Parametros :
 psCodigoPais : Oid Cliente

***************************************************************************/
FUNCTION MIC_FN_DEVUE_DOMIC(
 psOidClient NUMBER
)
RETURN VARCHAR2
IS
lsRetorno VARCHAR2(500);
lsTipoVia    VARCHAR2(30);
lsNombreVia  VARCHAR2(100);
lsNumeroPrincipal VARCHAR2(100);
lsObservacion VARCHAR2(100);
lsDescripcion VARCHAR2(100);
BEGIN

          SELECT    DES_ABRV_TIPO_VIA,
                    VAL_NOMB_VIA,
                    NUM_PPAL,
                    VAL_OBSE,
                    nivel_1
                    || DECODE (nivel_2, NULL, '', '/' || nivel_2)
                    || DECODE (nivel_3, NULL, '', '/' || nivel_3)
                    || DECODE (nivel_4, NULL, '', '/' || nivel_4)
                    || DECODE (nivel_5, NULL, '', '/' || nivel_5)
                    || DECODE (nivel_6, NULL, '', '/' || nivel_6)
                    || DECODE (nivel_7, NULL, '', '/' || nivel_7)
                    || DECODE (nivel_8, NULL, '', '/' || nivel_8)
                    || DECODE (nivel_9, NULL, '', '/' || nivel_9) AS DESC_UNI
           INTO  lsTipoVia, lsNombreVia, lsNumeroPrincipal,lsObservacion ,lsDescripcion
           FROM (SELECT   a.OID_CLIE_DIRE OID,
                          c.DES_ABRV_TIPO_VIA,
                          a.VAL_NOMB_VIA,
                          a.NUM_PPAL,
                          a.VAL_OBSE,
                          a.VAL_BARR,
                          t.COD_TERR,
                          (SELECT des_geog
                             FROM zon_valor_estru_geopo
                            WHERE pais_oid_pais = d.pais_oid_pais
                              AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                              AND orde_2 IS NULL) AS nivel_1,
                          (SELECT des_geog
                             FROM zon_valor_estru_geopo
                            WHERE pais_oid_pais = d.pais_oid_pais
                              AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                              AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                              AND orde_3 IS NULL) AS nivel_2,
                          (SELECT des_geog
                             FROM zon_valor_estru_geopo
                            WHERE pais_oid_pais = d.pais_oid_pais
                              AND orde_1 = SUBSTR (a.cod_unid_geog, 1, 6)
                              AND orde_2 = SUBSTR (a.cod_unid_geog, 7, 6)
                              AND orde_3 = SUBSTR (a.cod_unid_geog, 13, 6)
                              AND orde_4 IS NULL) AS nivel_3,
                          CASE
                             WHEN LENGTH (a.cod_unid_geog) > 18
                                THEN (SELECT des_geog
                                        FROM zon_valor_estru_geopo
                                       WHERE pais_oid_pais =
                                                            d.pais_oid_pais
                                         AND orde_1 = SUBSTR(a.cod_unid_geog, 1, 6)
                                         AND orde_2 = SUBSTR(a.cod_unid_geog, 7, 6)
                                         AND orde_3 = SUBSTR(a.cod_unid_geog, 13, 6)
                                         AND orde_4 = SUBSTR  (a.cod_unid_geog, 19, 6)
                                         AND orde_5 IS NULL)
                             ELSE NULL
                          END AS nivel_4,
                          CASE
                             WHEN LENGTH (a.cod_unid_geog) > 24
                                THEN (SELECT des_geog
                                        FROM zon_valor_estru_geopo
                                       WHERE pais_oid_pais =
                                                            d.pais_oid_pais
                                         AND orde_1 = SUBSTR   (a.cod_unid_geog, 1, 6)
                                         AND orde_2 = SUBSTR   (a.cod_unid_geog, 7, 6)
                                         AND orde_3 = SUBSTR   (a.cod_unid_geog, 13, 6)
                                         AND orde_4 = SUBSTR   (a.cod_unid_geog, 19, 6)
                                         AND orde_5 = SUBSTR   (a.cod_unid_geog, 25, 6)
                                         AND orde_6 IS NULL)
                             ELSE NULL
                          END AS nivel_5,
                          CASE
                             WHEN LENGTH (a.cod_unid_geog) > 30
                                THEN (SELECT des_geog
                                        FROM zon_valor_estru_geopo
                                       WHERE pais_oid_pais =
                                                            d.pais_oid_pais
                                         AND orde_1 = SUBSTR   (a.cod_unid_geog, 1, 6)
                                         AND orde_2 = SUBSTR   (a.cod_unid_geog, 7, 6)
                                         AND orde_3 = SUBSTR   (a.cod_unid_geog, 13, 6)
                                         AND orde_4 = SUBSTR   (a.cod_unid_geog, 19, 6)
                                         AND orde_5 = SUBSTR   (a.cod_unid_geog, 25, 6)
                                         AND orde_6 = SUBSTR   (a.cod_unid_geog, 31, 6)
                                         AND orde_7 IS NULL)
                             ELSE NULL
                          END AS nivel_6,
                          CASE
                             WHEN LENGTH (a.cod_unid_geog) > 36
                                THEN (SELECT des_geog
                                        FROM zon_valor_estru_geopo
                                       WHERE pais_oid_pais =
                                                            d.pais_oid_pais
                                         AND orde_1 = SUBSTR   (a.cod_unid_geog, 1, 6)
                                         AND orde_2 = SUBSTR   (a.cod_unid_geog, 7, 6)
                                         AND orde_3 = SUBSTR   (a.cod_unid_geog, 13, 6)
                                         AND orde_4 = SUBSTR   (a.cod_unid_geog, 19, 6)
                                         AND orde_5 = SUBSTR   (a.cod_unid_geog, 25, 6)
                                         AND orde_6 = SUBSTR   (a.cod_unid_geog, 31, 6)
                                         AND orde_7 = SUBSTR   (a.cod_unid_geog, 37, 6)
                                         AND orde_8 IS NULL)
                             ELSE NULL
                          END AS nivel_7,
                          CASE
                             WHEN LENGTH (a.cod_unid_geog) > 42
                                THEN (SELECT des_geog
                                        FROM zon_valor_estru_geopo
                                       WHERE pais_oid_pais =
                                                            d.pais_oid_pais
                                         AND orde_1 = SUBSTR   (a.cod_unid_geog, 1, 6)
                                         AND orde_2 = SUBSTR   (a.cod_unid_geog, 7, 6)
                                         AND orde_3 = SUBSTR   (a.cod_unid_geog, 13, 6)
                                         AND orde_4 = SUBSTR   (a.cod_unid_geog, 19, 6)
                                         AND orde_5 = SUBSTR   (a.cod_unid_geog, 25, 6)
                                         AND orde_6 = SUBSTR   (a.cod_unid_geog, 31, 6)
                                         AND orde_7 = SUBSTR   (a.cod_unid_geog, 37, 6)
                                         AND orde_8 = SUBSTR   (a.cod_unid_geog, 43, 6)
                                         AND orde_9 IS NULL)
                             ELSE NULL
                          END AS nivel_8,
                          CASE
                             WHEN LENGTH (a.cod_unid_geog) > 48
                                THEN (SELECT des_geog
                                        FROM zon_valor_estru_geopo
                                       WHERE pais_oid_pais =
                                                            d.pais_oid_pais
                                         AND orde_1 = SUBSTR   (a.cod_unid_geog, 1, 6)
                                         AND orde_2 = SUBSTR   (a.cod_unid_geog, 7, 6)
                                         AND orde_3 = SUBSTR   (a.cod_unid_geog, 13, 6)
                                         AND orde_4 = SUBSTR   (a.cod_unid_geog, 19, 6)
                                         AND orde_5 = SUBSTR   (a.cod_unid_geog, 25, 6)
                                         AND orde_6 = SUBSTR   (a.cod_unid_geog, 31, 6)
                                         AND orde_7 = SUBSTR   (a.cod_unid_geog, 37, 6)
                                         AND orde_8 = SUBSTR   (a.cod_unid_geog, 43, 6)
                                         AND orde_9 = SUBSTR   (a.cod_unid_geog, 49, 6))
                             ELSE NULL
                          END AS nivel_9
                     FROM MAE_CLIEN_DIREC a,
                          MAE_TIPO_DIREC b,
                          SEG_TIPO_VIA c,
                          MAE_CLIEN d,
                          ZON_TERRI t
                    WHERE d.OID_CLIE =psOidClient
                      AND d.OID_CLIE = a.CLIE_OID_CLIE
                      AND a.IND_ELIM = 0
                      AND b.OID_TIPO_DIRE = a.TIDC_OID_TIPO_DIRE
                      AND c.OID_TIPO_VIA = a.TIVI_OID_TIPO_VIA
                      AND a.IND_DIRE_PPAL  = 1
                      AND a.TERR_OID_TERR = t.OID_TERR (+)
                 ORDER BY a.OID_CLIE_DIRE DESC)
               WHERE ROWNUM = 1;
             --lsTipoVia, lsNombreVia, lsNumeroPrincipal,lsObservacion ,lsDescripcion
          IF(lsTipoVia IS NOT NULL AND LENGTH(lsTipoVia)>0)THEN
           lsRetorno:= lsRetorno || ' ' || TRIM(lsTipoVia);
          END IF;

          IF(lsNombreVia IS NOT NULL AND LENGTH(lsNombreVia)>0)THEN
           lsRetorno:= lsRetorno || ' ' || TRIM(lsNombreVia);
          END IF;

          IF(lsNumeroPrincipal IS NOT NULL AND LENGTH(lsNumeroPrincipal)>0)THEN
           lsRetorno:= lsRetorno || ' ' || TRIM(lsNumeroPrincipal);
          END IF;

          IF(lsObservacion IS NOT NULL AND LENGTH(lsObservacion)>0)THEN
           lsRetorno:= lsRetorno || ' ' || TRIM(lsObservacion);
          END IF;

          IF(lsDescripcion IS NOT NULL AND LENGTH(lsDescripcion)>0)THEN
           lsRetorno:= lsRetorno || ' ' || TRIM(lsDescripcion);
          END IF;

          RETURN  TRIM(lsRetorno);

EXCEPTION
    WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MIC_FN_DEVUE_DOMIC: '|| psOidClient || ' error '|| ls_sqlerrm);

RETURN  lsRetorno;

END MIC_FN_DEVUE_DOMIC;

END INT_PKG_MICRO;
/

