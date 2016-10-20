CREATE OR REPLACE PACKAGE "INT_PKG_COMIS" IS

/* Declaracion de variables */
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(150);
W_FILAS      NUMBER:=1000;

/***************************************************************************
Descripcion       : Genera Interfase de Adam
Fecha Creacion    : 14/05/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_COM_ADAM
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoComision           VARCHAR2,
   psFechaProcesoInicio       VARCHAR2,
   psFechaProcesoFin          VARCHAR2,
   codigoPeriodoProceso1      VARCHAR2,
   codigoPeriodoProceso2      VARCHAR2,
   indicador                  VARCHAR2)
;
/***************************************************************************
Descripcion       : Genera Interfase Carga Masiva Ejecutiva
Fecha Creacion    : 04/08/2008
Autor             : Efrain Fernandez
Parametros        :
            pscodigopais       : Codigo Pais
            pscodigosistema    : Codigo Sistema
            pscodigointerfaz   : Codigo Intefaz
            psnombrearchivo    : Nombre Archivo
            psnumerolote       : Numero de Lote
            pscodigomarca      : Codigo Marca
            pscodigocanal      : Codigo Canal
            pstipoComisionista : Tipo Comisionista
            psanioInicial      : Annio Inicial
            pscodigoTramo      : Codigo Tramo
***************************************************************************/
PROCEDURE INT_PR_COM_CARGA_MASIV_EJECU
(
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    psnumerolote        VARCHAR2,
    pscodigomarca        VARCHAR2,
    pscodigocanal        VARCHAR2,
    pstipoComisionista VARCHAR2,
    psanioInicial VARCHAR2,
    pscodigoTramo VARCHAR2
    )
;
/***************************************************************************
Descripcion       : Genera Interfase Carga Masiva Ejecutiva Nueva
Fecha Creacion    : 04/08/2008
Autor             : Efrain Fernandez
Parametros        :
            pscodigopais       : Codigo Pais
            pscodigosistema    : Codigo Sistema
            pscodigointerfaz   : Codigo Intefaz
            psnombrearchivo    : Nombre Archivo
            pscodigoPeriodo    : Annio Inicial
            pstipoComisionista : Tipo Comisionista
***************************************************************************/
PROCEDURE INT_PR_COM_ARCHI_EJECU_NUEVA
(
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    pstipoComisionista VARCHAR2
    )
;
/***************************************************************************
Descripcion       : Genera Interfase Pago Comision Ejecutiva
Fecha Creacion    : 04/08/2008
Autor             : Efrain Fernandez
Parametros        :
            pscodigopais       : Codigo Pais
            pscodigosistema    : Codigo Sistema
            pscodigointerfaz   : Codigo Intefaz
            psnombrearchivo    : Nombre Archivo
            pstipoComisionista : Tipo Comisionista
            pscodigoPeriodo    : Annio Inicial
            psCodigoComision   : Codigo Comision
***************************************************************************/
PROCEDURE INT_PR_COM_PAGOS_COMIS_EJECU
(
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    pstipoComisionista   VARCHAR2,
    pscodigoPeriodo      VARCHAR2,
    psCodigoComision     VARCHAR2,
    psComisionIngresos     VARCHAR2
    );
/***************************************************************************
Descripcion       : Genera Nueva Interfase de Adam
Fecha Creacion    : 02/12/2009
Autor             : Paolo Lopez
***************************************************************************/
PROCEDURE INT_PR_COM_ADAM_MODIF
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoComision           VARCHAR2,
   psFechaProcesoInicio       VARCHAR2,
   psFechaProcesoFin          VARCHAR2,
   codigoPeriodoProceso1      VARCHAR2,
   codigoPeriodoProceso2      VARCHAR2,
   indicador                  VARCHAR2)
;
END INT_PKG_COMIS;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_COMIS" IS

/***************************************************************************
Descripcion       : Genera Interfase de Adam
Fecha Creacion    : 14/05/2007
Autor             : Marco Agurto
***************************************************************************/
PROCEDURE INT_PR_COM_ADAM
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoComision           VARCHAR2,
   psFechaProcesoInicio       VARCHAR2,
   psFechaProcesoFin          VARCHAR2,
   codigoPeriodoProceso1      VARCHAR2,
   codigoPeriodoProceso2      VARCHAR2,
   indicador                  VARCHAR2)
IS
   CURSOR c_interfaz IS
   SELECT '  ',
         (SELECT B.COD_EMPL FROM   MAE_CLIEN C, MAE_CLIEN_DATOS_ADICI B
          WHERE  C.OID_CLIE = B.CLIE_OID_CLIE
                 AND C.COD_CLIE = A.COD_LIDE_ZONA
                 AND B.IND_ACTI = 1
                 AND B.COD_EMPL IS NOT NULL) AS COD_EMPL ,
          substr(A.NOM_LIDE_ZONA,1,50),
          LPAD(trim(to_char( (nvl(A.IMP_COMI_TRA1,0)+ nvl(A.IMP_COMI_TRA2,0))*100,'99999999999999999')), 18, '0')
    FROM COM_COMIS_PERIO_CALCU_ZONA A
    WHERE   A.COD_COMI =    psCodigoComision
         AND (A.PERI_COD_PERI = codigoPeriodoProceso1 OR
              A.PERI_COD_PERI = DECODE(codigoPeriodoProceso1,NULL,codigoPeriodoProceso1, '',codigoPeriodoProceso1, codigoPeriodoProceso2)
              )
       ORDER BY 2;

   CURSOR c_interfaz1  IS
   SELECT '  ',
       (SELECT B.COD_EMPL  FROM   MAE_CLIEN C,  MAE_CLIEN_DATOS_ADICI B
        WHERE  C.OID_CLIE = B.CLIE_OID_CLIE
               AND C.COD_CLIE = A.COD_LIDE_ZONA
               AND B.IND_ACTI = 1
               AND B.COD_EMPL IS NOT NULL) AS COD_EMPL ,
       substr (A.NOM_LIDE_ZONA, 1,50),
       LPAD(trim(to_char( (A.IMP_COMI)*100,'99999999999999999')), 18, '0')
      FROM COM_COMIS_PERIO_CALCU_COMER A
      WHERE A.COD_COMI = psCodigoComision
      AND A.FEC_INIC >= to_date (psFechaProcesoInicio,'dd/mm/yyyy')
      AND A.FEC_FINA <= to_date (psFechaProcesoFin,'dd/mm/yyyy')
      ORDER BY 2 ;

   ls_oidPeriodoDesde         seg_perio_corpo.oid_peri%TYPE;
   ls_oidPeriodoHasta         seg_perio_corpo.oid_peri%TYPE;
   ls_oidPais                 seg_pais.oid_pais%TYPE;
   ls_oidMarca                seg_marca.oid_marc%TYPE;
   ls_oidCanal                seg_canal.oid_cana%TYPE;
   TYPE interfazCom IS RECORD
   (
    codigoVacio               VARCHAR2(2),
    codigoPlanilla            mae_clien_datos_adici.cod_empl%TYPE,
    nombreCliente             VARCHAR2(50),
    montoComision             VARCHAR2(18)
   );
   TYPE interfazComTab  IS TABLE OF interfazCom ;
   interfazRecord interfazComTab;
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

    IF indicador ='PERIODO' THEN
      OPEN c_interfaz;
      LOOP
         FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoVacio                    ||';'||
                        interfazRecord(x).codigoPlanilla                 ||';'||
                        interfazRecord(x).nombreCliente                 ||';'||
                        interfazRecord(x).montoComision ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END LOOP;
         END IF;
         EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz;
    ELSE
      OPEN c_interfaz1;
      LOOP
         FETCH c_interfaz1 BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                lsLinea :=  interfazRecord(x).codigoVacio                    ||';'||
                            interfazRecord(x).codigoPlanilla                 ||';'||
                            interfazRecord(x).nombreCliente                 ||';'||
                            interfazRecord(x).montoComision ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END LOOP;
         END IF;
         EXIT WHEN c_interfaz1%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz1;
    END IF;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_COM_ADAM: '||ls_sqlerrm);
END INT_PR_COM_ADAM;

PROCEDURE INT_PR_COM_CARGA_MASIV_EJECU
  (
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    psnumerolote        VARCHAR2,
    pscodigomarca        VARCHAR2,
    pscodigocanal        VARCHAR2,
    pstipoComisionista VARCHAR2,
    psanioInicial VARCHAR2,
    pscodigoTramo VARCHAR2
  )
  IS
   i integer :=0;
  /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   ln_id_pais          NUMBER;
   W_FILAS             NUMBER := 1000 ;

   /*valores para el txt*/
   lvcodSociedad          VARCHAR2(4);
   lvindViaPago           VARCHAR2(1);
   lvcodBanco             VARCHAR2(5);
   lvvalClaveBanco        VARCHAR2(4);
   lvcodTipoCuenta        VARCHAR2(1);
   lvcodMoneda            VARCHAR2(3);

   /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;

  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

  -- Declaracion de Estructuras
      CURSOR C_CARGA is
        SELECT TEMP.ejecutiva, TEMP.rango, TEMP.proveedor1, TEMP.proveedor2, TEMP.ruc, TEMP.codCuentaBancaria, TEMP.DIRECCION, TEMP.DISTRITO, TEMP.CODIGOREGION, TEMP.NOMBREREGION,
                TEMP.nomTelefono,
                TEMP.nomFax,
                TEMP.nomCorreoElectronico,
                TEMP.tipoRegimen
        FROM (
                SELECT C.COD_EJEC as ejecutiva,
                       C.COD_RANG as rango,
                       SUBSTR(replace(MC.VAL_APE1,'Ñ','N') ||' '|| replace(MC.VAL_APE2,'Ñ','N') ||' '|| replace(MC.VAL_NOM1,'Ñ','N') ||' '|| replace(MC.VAL_NOM2,'Ñ','N'),1,40) AS proveedor1,
                       SUBSTR(replace(MC.VAL_APE1,'Ñ','N') ||','|| replace(MC.VAL_APE2,'Ñ','N') ||','|| replace(MC.VAL_NOM1,'Ñ','N') ||' '|| replace(MC.VAL_NOM2,'Ñ','N'),1,40) AS proveedor2,
                       ML.VAL_RUC AS ruc,
                       ML.COD_CCCI AS codCuentaBancaria,
                SUBSTR( replace(TRIM(MD.VAL_NOMB_VIA),'Ñ','N')||' '||replace(TRIM(MD.NUM_PPAL),'Ñ','N')||' '||replace(TRIM(MD.VAL_OBSE),'Ñ','N') ,1,60) AS DIRECCION,
                ( SELECT ZG.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO ZG,
                     MAE_CLIEN_DIREC MDX
                    WHERE ZG.ORDE_1 = SUBSTR(MDX.COD_UNID_GEOG,1,6)
                          AND ZG.ORDE_2 = SUBSTR(MDX.COD_UNID_GEOG,7,6)
                          AND ZG.ORDE_3 = SUBSTR(MDX.COD_UNID_GEOG,13,6)
                          AND ZG.ORDE_4 IS null
                          AND MDX.IND_DIRE_PPAL = 1
                          AND MDX.IND_ELIM = 0
                          AND MDX.CLIE_OID_CLIE = MD.CLIE_OID_CLIE  ) AS DISTRITO,
                  NVL( (
                  SELECT CQ.COD_REGI
                  FROM COM_EQUIV_REGIO_SAP CQ,
                       MAE_CLIEN_DIREC MDX
                  WHERE CQ.PAIS_COD_PAIS = pscodigopais
                        AND CQ.COD_ORDE = SUBSTR(MD.COD_UNID_GEOG,1,6)
                        AND MDX.CLIE_OID_CLIE = MD.CLIE_OID_CLIE
                        AND MDX.IND_DIRE_PPAL = 1
                        AND MDX.IND_ELIM = 0
                        ),'') AS CODIGOREGION,
                  NVL( (
                  SELECT CQ.DES_DEPA
                  FROM COM_EQUIV_REGIO_SAP CQ,
                       MAE_CLIEN_DIREC MDX
                  WHERE CQ.PAIS_COD_PAIS = pscodigopais
                        AND CQ.COD_ORDE = SUBSTR(MD.COD_UNID_GEOG,1,6)
                        AND MDX.CLIE_OID_CLIE = MD.CLIE_OID_CLIE
                        AND MDX.IND_DIRE_PPAL = 1
                        AND MDX.IND_ELIM = 0
                        ),'') AS NOMBREREGION,
                SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE,'TF'),1,20) AS nomTelefono,
                SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE,'FX'),1,20) AS nomFax,
                SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE,'ML'),1,55) AS nomCorreoElectronico,
                DECODE(ML.TRGI_COD_RGIM, '00', '', (SELECT R.DES_RGIM FROM COM_TIPO_REGIM R WHERE R.COD_RGIM = ML.TRGI_COD_RGIM)) tipoRegimen
                FROM  COM_HISTO_VARIA_EJETR_CABEC C,
                      MAE_CLIEN_LIDER ML,
                      MAE_CLIEN MC,
                      MAE_CLIEN_DIREC MD
                WHERE C.COD_EJEC = ML.COD_CLID
                      AND C.COD_PAIS = ML.PAIS_COD_PAIS
                      AND C.COD_EJEC = MC.COD_CLIE
                      AND MC.OID_CLIE = MD.CLIE_OID_CLIE
                      --AND ML.VAL_RAZO_SOCI IS NOT NULL
                      AND ML.VAL_RUC IS NOT NULL
                      AND ML.NUM_LIAH IS NOT NULL
                      AND C.COD_PAIS = pscodigopais
                      AND C.COD_MARC = pscodigomarca
                      AND C.COD_CANA = pscodigocanal
                      AND C.COD_TIPO_COMI = pstipoComisionista
                      AND C.NUM_ANIO_INIC = psanioInicial
                      AND C.COD_RANG = pscodigoTramo
                      AND C.COD_NIVE IN ('EJ','ES','EM','AS')
                      AND MC.PAIS_OID_PAIS = ln_id_pais
                      AND MD.IND_DIRE_PPAL = 1
                      AND MD.IND_ELIM = 0
        ) TEMP
              WHERE TEMP.DIRECCION IS NOT NULL
              AND TEMP.CODIGOREGION IS NOT NULL
              AND TEMP.NOMBREREGION IS NOT NULL
              AND TEMP.TIPOREGIMEN IS NOT NULL
              AND TEMP.ruc IS NOT NULL
              AND TEMP.codCuentaBancaria IS NOT NULL;

        TYPE interfazCom IS RECORD
         (
           cod_ejec          		varchar2(15),
           cod_rango         		varchar2(2),
           proveedor         		varchar2(40),
           proveedor2        		varchar2(40),
           ruc               		varchar2(12),
           codCuentaBancaria 		varchar2(20),
           nomDireccion      		varchar2(60),
           nomDistrito       		varchar2(40),
           codRegion         		varchar2(2),
           nomRegion         		varchar2(30),
           nomTelefono       		varchar2(20),
           nomFax            		varchar2(20),
           nomCorreoElectronico varchar2(55),
           tipoRegimen          varchar2(11)
         );

         TYPE interfazComTab  IS TABLE OF interfazCom ;
         interfazRecord interfazComTab;
   lbAbrirUtlFile  BOOLEAN;
BEGIN
     ln_id_pais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

            BEGIN
              SELECT DE.COD_SOCI, DE.IND_VIA_PAGO, DE.COD_BANC, DE.VAL_CLAV_BANC, DE.COD_TIPO_CUEN, DE.COD_MONE
              INTO   lvcodSociedad, lvindViaPago, lvcodBanco, lvvalClaveBanco, lvcodTipoCuenta, lvcodMoneda
              FROM COM_DATOS_FIJOS_EJECU DE
              WHERE DE.PAIS_COD_PAIS = pscodigopais
                    AND DE.COD_TIPO_COMI = pstipoComisionista;
               EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                  lvcodSociedad:='';
                  lvindViaPago:='';
                  lvcodBanco:='';
                  lvvalClaveBanco:='';
                  lvcodTipoCuenta:='';
                  lvcodMoneda:='';
             END;

      lbAbrirUtlFile := TRUE;
      OPEN C_CARGA;
      LOOP
         FETCH C_CARGA BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         /* Procedimiento inicial para generar interfaz */
          IF lbAbrirUtlFile THEN
             GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
          END IF;

         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            I:=I+1;

                 lsLinea :=
                             lvcodSociedad                          ||';'||  --Sociedad
                             interfazRecord(x).proveedor            ||';'||  --Nombre Proveedor1
                             interfazRecord(x).nomRegion            ||';'||  --Nombre dela Region
                             interfazRecord(x).proveedor2            ||';'||  --Nombre Proveedor2
                             interfazRecord(x).proveedor            ||';'||  --Busqueda
                             interfazRecord(x).nomDireccion         ||';'||  --Direccion
                             interfazRecord(x).nomDistrito          ||';'||  --Distrito
                             interfazRecord(x).ruc                  ||';'||  --RUC
                             interfazRecord(x).tipoRegimen          ||';'||  --TipoRegimen
                             ''                                     ||';'||  --CODIGOPOSTAL
                             interfazRecord(x).nomRegion            ||';'||  --Nombre_region
                             interfazRecord(x).codRegion            ||';'||  --codigo_region
                             pscodigopais                           ||';'||  --PAIS
                             lvindViaPago                           ||';'||  --via_pago
                             lvcodBanco                             ||';'||  --banc Propio
                             lvvalClaveBanco                        ||';'||  --ls_val_clav_banc
                             interfazRecord(x).codCuentaBancaria    ||';'||  --Cuenta Bancaria
                             interfazRecord(x).proveedor            ||';'||  --Titular de Cuenta
                             lvcodTipoCuenta                        ||';'||  --Tipo_cuenta
                             lvcodMoneda                            ||';'||  --Moneda
                             interfazRecord(x).nomTelefono          ||';'||  --ls_telefono
                             interfazRecord(x).nomFax               ||';'||  --ls_fax
                             interfazRecord(x).nomCorreoElectronico;         --ls_correo

                        --    DBMS_OUTPUT.PUT_LINE('lsLinea: '||lsLinea);
            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            END LOOP;
          END IF;
         EXIT WHEN C_CARGA%NOTFOUND;
      END LOOP;
      CLOSE C_CARGA;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(v_handle);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr( ln_sqlcode||'-'||sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_COM_CARGA_MASIV_EJECU: '||ls_sqlerrm);
END INT_PR_COM_CARGA_MASIV_EJECU;

PROCEDURE INT_PR_COM_ARCHI_EJECU_NUEVA
  (
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    pstipoComisionista VARCHAR2
  )
  IS
   i integer :=0;
  /* Declaracion de Variables */
   ln_sqlcode   NUMBER(10);
   ls_sqlerrm   VARCHAR2(150);
   ln_id_pais          NUMBER;
   W_FILAS             NUMBER := 1000 ;

   /*valores para el txt*/
   lvcodSociedad          VARCHAR2(4);
   lvindViaPago           VARCHAR2(1);
   lvcodBanco             VARCHAR2(5);
   lvvalClaveBanco        VARCHAR2(4);
   lvcodTipoCuenta        VARCHAR2(1);
   lvcodMoneda            VARCHAR2(3);

   /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;

  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

   TYPE t_tab_tempo_clie IS TABLE OF MAE_CLIEN_LIDER%ROWTYPE INDEX BY BINARY_INTEGER;
   v_tab_tempo_clie t_tab_tempo_clie;

  -- Declaracion de Estructuras

      CURSOR C_CARGA( ln_id_pais NUMBER ) is
  SELECT TEMP.COD_CLID,
         TEMP.proveedor1,TEMP.proveedor2, TEMP.ruc, TEMP.codCuentaBancaria, TEMP.DIRECCION, TEMP.DISTRITO, TEMP.CODIGOREGION, TEMP.NOMBREREGION,
       TEMP.nomTelefono,
       TEMP.nomFax,
       TEMP.nomCorreoElectronico,
       TEMP.tipoRegimen
  FROM(
        SELECT ML.COD_CLID as COD_CLID,
              SUBSTR(replace(MC.VAL_APE1,'Ñ','N') ||' '|| replace(MC.VAL_APE2,'Ñ','N') ||' '|| replace(MC.VAL_NOM1,'Ñ','N') ||' '|| replace(MC.VAL_NOM2,'Ñ','N'),1,40) AS proveedor1,
              SUBSTR(replace(MC.VAL_APE1,'Ñ','N') ||','|| replace(MC.VAL_APE2,'Ñ','N') ||','|| replace(MC.VAL_NOM1,'Ñ','N') ||' '|| replace(MC.VAL_NOM2,'Ñ','N'),1,40) AS proveedor2,
              ML.VAL_RUC AS ruc,
              ML.COD_CCCI AS codCuentaBancaria,
        SUBSTR( replace(TRIM(MD.VAL_NOMB_VIA),'Ñ','N')||' '||replace(TRIM(MD.NUM_PPAL),'Ñ','N')||' '||replace(TRIM(MD.VAL_OBSE),'Ñ','N') ,1,60) AS DIRECCION,
        ( SELECT ZG.DES_GEOG FROM ZON_VALOR_ESTRU_GEOPO ZG,
             MAE_CLIEN_DIREC MDX
            WHERE ZG.ORDE_1 = SUBSTR(MDX.COD_UNID_GEOG,1,6)
                  AND ZG.ORDE_2 = SUBSTR(MDX.COD_UNID_GEOG,7,6)
                  AND ZG.ORDE_3 = SUBSTR(MDX.COD_UNID_GEOG,13,6)
                  AND ZG.ORDE_4 is null
                  AND MDX.IND_DIRE_PPAL = 1
                  AND MDX.IND_ELIM = 0
                  AND MDX.CLIE_OID_CLIE = MD.CLIE_OID_CLIE  ) AS DISTRITO,
          NVL( (
          SELECT CQ.COD_REGI
          FROM COM_EQUIV_REGIO_SAP CQ,
               MAE_CLIEN_DIREC MDX
          WHERE CQ.PAIS_COD_PAIS = pscodigopais
                AND CQ.COD_ORDE = SUBSTR(MD.COD_UNID_GEOG,1,6)
                AND MDX.CLIE_OID_CLIE = MD.CLIE_OID_CLIE
                AND MDX.IND_DIRE_PPAL = 1
                AND MDX.IND_ELIM = 0
                ),'') AS CODIGOREGION,
          NVL( (
          SELECT CQ.DES_DEPA
          FROM COM_EQUIV_REGIO_SAP CQ,
               MAE_CLIEN_DIREC MDX
          WHERE CQ.PAIS_COD_PAIS = pscodigopais
                AND CQ.COD_ORDE = SUBSTR(MD.COD_UNID_GEOG,1,6)
                AND MDX.CLIE_OID_CLIE = MD.CLIE_OID_CLIE
                AND MDX.IND_DIRE_PPAL = 1
                AND MDX.IND_ELIM = 0
              ),'') AS NOMBREREGION,
              SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE,'TF'),1,20) AS nomTelefono,
              SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE,'FX'),1,20) AS nomFax,
              SUBSTR(GEN_PKG_GENER.GEN_FN_CLIEN_TEXTO_COMUN(MC.OID_CLIE,'ML'),1,55) AS nomCorreoElectronico,
              DECODE(ML.TRGI_COD_RGIM, '00', '', (SELECT R.DES_RGIM FROM COM_TIPO_REGIM R WHERE R.COD_RGIM = ML.TRGI_COD_RGIM)) tipoRegimen
        FROM MAE_CLIEN MC,
             MAE_CLIEN_DIREC MD,
             MAE_CLIEN_LIDER ML
        WHERE MC.COD_CLIE = ML.COD_CLID
              AND MC.OID_CLIE = MD.CLIE_OID_CLIE
              AND MC.PAIS_OID_PAIS = ln_id_pais
              AND MD.IND_DIRE_PPAL = 1
              AND MD.IND_ELIM = 0
              AND ( ML.IND_ENVI = '0' or ML.IND_ENVI is null)
              AND ML.PAIS_COD_PAIS = pscodigopais
              --AND ML.PER_INGR = pscodigoPeriodo
        ) TEMP
        WHERE TEMP.proveedor1 IS NOT NULL
              AND TEMP.ruc IS NOT NULL
              AND TEMP.codCuentaBancaria IS NOT NULL
              AND TEMP.DIRECCION IS NOT NULL
              AND TEMP.CODIGOREGION IS NOT NULL
              AND TEMP.NOMBREREGION IS NOT NULL
              AND TEMP.TIPOREGIMEN IS NOT NULL;

        TYPE interfazCom IS RECORD
         ( cod_clid             varchar2(15),
           proveedor            varchar2(40),
           proveedor2            varchar2(40),
           ruc                  varchar2(12),
           codCuentaBancaria    varchar2(20),
           nomDireccion         VARCHAR2(60),
           nomDistrito          VARCHAR2(40),
           codRegion            VARCHAR2(2),
           nomRegion            VARCHAR2(30),
           nomTelefono       		varchar2(20),
           nomFax            		varchar2(20),
           nomCorreoElectronico varchar2(55),
           tipoRegimen          varchar2(11)
         );

         TYPE interfazComTab  IS TABLE OF interfazCom ;
         interfazRecord interfazComTab;

  lbAbrirUtlFile  BOOLEAN;
BEGIN

    ln_id_pais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

            BEGIN
              SELECT DE.COD_SOCI, DE.IND_VIA_PAGO, DE.COD_BANC, DE.VAL_CLAV_BANC, DE.COD_TIPO_CUEN, DE.COD_MONE
              INTO   lvcodSociedad, lvindViaPago, lvcodBanco, lvvalClaveBanco, lvcodTipoCuenta, lvcodMoneda
              FROM COM_DATOS_FIJOS_EJECU DE
              WHERE DE.PAIS_COD_PAIS = pscodigopais
                AND DE.COD_TIPO_COMI = pstipoComisionista;
               EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                  lvcodSociedad:='';
                  lvindViaPago:='';
                  lvcodBanco:='';
                  lvvalClaveBanco:='';
                  lvcodTipoCuenta:='';
                  lvcodMoneda:='';
             END;

      lbAbrirUtlFile := TRUE;
      OPEN C_CARGA(ln_id_pais);
      LOOP
         FETCH C_CARGA BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         /* Procedimiento inicial para generar interfaz */
          IF lbAbrirUtlFile THEN
             GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
          END IF;

         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                  I:=I+1;
                   v_tab_tempo_clie(x).COD_CLID := interfazRecord(x).cod_clid;

                   lsLinea :=
                         lvcodSociedad                          ||';'||  --Sociedad
                         interfazRecord(x).proveedor            ||';'||  --Nombre Proveedor1
                         interfazRecord(x).nomRegion            ||';'||  --Nombre dela Region
                         interfazRecord(x).proveedor2            ||';'||  --Nombre Proveedor2
                         interfazRecord(x).proveedor            ||';'||  --Busqueda
                         interfazRecord(x).nomDireccion         ||';'||  --Direccion
                         interfazRecord(x).nomDistrito          ||';'||  --Distrito
                         interfazRecord(x).ruc                  ||';'||  --RUC
                         interfazRecord(x).tipoRegimen          ||';'||  --tipoRegimen
                         ''                                     ||';'||  --CODIGOPOSTAL
                         interfazRecord(x).nomRegion            ||';'||  --Nombre_region
                         interfazRecord(x).codRegion            ||';'||  --codigo_region
                         pscodigopais                           ||';'||  --PAIS
                         lvindViaPago                           ||';'||  --via_pago
                         lvcodBanco                             ||';'||  --banc Propio
                         lvvalClaveBanco                        ||';'||  --ls_val_clav_banc
                         interfazRecord(x).codCuentaBancaria    ||';'||  --Cuenta Bancaria
                         interfazRecord(x).proveedor            ||';'||  --Titular de Cuenta
                         lvcodTipoCuenta                        ||';'||  --Tipo_cuenta
                         lvcodMoneda                            ||';'||  --Moneda
                         interfazRecord(x).nomTelefono          ||';'||  --ls_telefono
                         interfazRecord(x).nomFax               ||';'||  --ls_fax
                         interfazRecord(x).nomCorreoElectronico;         --ls_correo

      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            END LOOP;
          END IF;
         EXIT WHEN C_CARGA%NOTFOUND;
      END LOOP;
      CLOSE C_CARGA;

      /* Actualiza Indicador */

           FOR x IN 1..v_tab_tempo_clie.COUNT LOOP
           BEGIN
               UPDATE MAE_CLIEN_LIDER ML
                SET ML.IND_ENVI = '1'
                WHERE ML.COD_CLID = v_tab_tempo_clie(x).COD_CLID
                      AND ML.PAIS_COD_PAIS = pscodigopais;
            END;
           END LOOP;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(v_handle);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr( ln_sqlcode||'-'||sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_COM_ARCHI_EJECU_NUEVA: '||ls_sqlerrm);
END INT_PR_COM_ARCHI_EJECU_NUEVA;


PROCEDURE INT_PR_COM_PAGOS_COMIS_EJECU
(
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    pstipoComisionista   VARCHAR2,
    pscodigoPeriodo      VARCHAR2,
    psCodigoComision     VARCHAR2,
    psComisionIngresos     VARCHAR2
    )
  IS
   i integer :=0;
  /* Declaracion de Variables */
   ln_sqlcode       NUMBER(10);
   ls_sqlerrm       VARCHAR2(150);
   ln_id_pais       NUMBER;
   W_FILAS          NUMBER := 1000 ;
   lvval_tasa_impu  NUMBER(5,3);

   lverror           varchar2(40):='x';
   /*valores para el txt*/
    lvcodSociedad         COM_DATOS_FIJOS_EJECU.COD_SOCI%TYPE;
    lvmoneda              COM_DATOS_FIJOS_EJECU.COD_MONE%TYPE;
    lvclaseDocumento      COM_DATOS_FIJOS_EJECU.COD_CLAS_DOCU%TYPE;
    lvtextoCabecera       COM_DATOS_FIJOS_EJECU.VAL_TEXT_CABE%TYPE;
    lvclaveProveedor      COM_DATOS_FIJOS_EJECU.COD_CLAV_PROV%TYPE;
    lvcalculoImpuesto     COM_DATOS_FIJOS_EJECU.COD_CALC_IMPU%TYPE;
    lvclaveCtasGastos     COM_DATOS_FIJOS_EJECU.COD_CLAV_CTAS_GAST%TYPE;
    lvcuentaContable      COM_DATOS_FIJOS_EJECU.COD_CTAS_CONT%TYPE;
    lvcentroCosto         COM_DATOS_FIJOS_EJECU.COD_CENT_COST%TYPE;
    lvclavePosicion       COM_DATOS_FIJOS_EJECU.COD_CLAV_SEGU_POSI%TYPE;
    lvmarca               COM_DATOS_FIJOS_EJECU.COD_MARC%TYPE;
    lvnegocio             COM_DATOS_FIJOS_EJECU.COD_NEGO%TYPE;
    lvindicadorIgv        COM_DATOS_FIJOS_EJECU.IND_IGV%TYPE;

   /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;

  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

  -- Declaracion de Estructuras

      CURSOR C_CARGA(lvval_tasa_impu NUMBER, ln_id_pais NUMBER) is
      SELECT
     TO_CHAR(SYSDATE,'DD.MM.YYYY') AS FECHADOCUMENTO,
             'C'|| SUBSTR(CLIENTES.COD_PERI,5,2)||'-'||SUBSTR(CLIENTES.COD_PERI,1,4) AS REFERENCIA,
             psCodigoComision      AS ASIGNACION,
             SUBSTR(replace(MC.VAL_APE1,'Ñ','N') ||' '|| replace(MC.VAL_APE2,'Ñ','N') ||' '|| replace(MC.VAL_NOM1,'Ñ','N') ||' '|| replace(MC.VAL_NOM2,'Ñ','N'),1,40) AS NOMBREPROVEEDOR,
             TRIM(ML.COD_PROV)      AS CODIGOPROVEEDOR,
                    TO_CHAR(COM_PKG_REPOR.COM_FN_DEVUE_COMIS_EJCAL(pscodigopais,
                                                      psCodigoComision,
                                                      CLIENTES.COD_CLIE,
                                                      pscodigoPeriodo,
                                                      lvval_tasa_impu)+
                 COM_PKG_REPOR.COM_FN_DEVUE_COMIS_INGRE_EJECU(CLIENTES.COD_PERI,
                                                             CLIENTES.COD_CLIE,
                                                             psComisionIngresos)  *
               (1 + lvval_tasa_impu / 100) ,'999,999.99') AS IMPORTE1,
             'C'||'-'|| SUBSTR(CLIENTES.COD_PERI,5,2) AS PERIODOCOMERCIAL,
             SUBSTR(CLIENTES.COD_PERI,1,4)           AS EJERCIOCOMERCIAL,
                               TO_CHAR(COM_PKG_REPOR.COM_FN_DEVUE_COMIS_EJCAL(pscodigopais,
                                                      psCodigoComision,
                                                      CLIENTES.COD_CLIE,
                                                      pscodigoPeriodo,
                                                      lvval_tasa_impu)+
                 COM_PKG_REPOR.COM_FN_DEVUE_COMIS_INGRE_EJECU(CLIENTES.COD_PERI,
                                                             CLIENTES.COD_CLIE,
                                                             psComisionIngresos)  *
               (1 + lvval_tasa_impu / 100) ,'999,999.99') as IMPORTETOTAL,
             CASE WHEN ML.Tip_Regi = 'RUS' THEN 'Asesoria por Reclutamient'
                  ELSE 'Serv.Asesoria Empresarial'
             END AS textoCabecera,
             CASE WHEN ML.Tip_Regi = 'RUS' THEN 'I2'
                  ELSE 'I1'
             END AS indicadorIGV,
             (SELECT ORD_ESTA  FROM COM_ORDEN_ESTAD_ZONA WHERE COD_ZONA=CLIENTES.COD_ZONA) as ordenEstadistico,
             (SELECT substr(COD_NIVE,1,1)  FROM COM_COMIS_EJCAL_CABEC
             WHERE COD_EJEC = CLIENTES.COD_CLIE
             AND COD_PAIS = pscodigopais
             AND COD_TIPO_COMI = pstipoComisionista
             AND COD_CAMP = pscodigoPeriodo
             AND COD_COMI=psCodigoComision) as nivelEjecutiva,
             CLIENTES.COD_PERI AS CODIGO_PERIODO,
             CLIENTES.COD_ZONA AS CODIGO_ZONA
      FROM
            MAE_CLIEN_LIDER ML
           ,MAE_CLIEN MC
           ,(select PC.COD_LIDE_SECC COD_CLIE,PC.PERI_COD_PERI COD_PERI, PC.COD_ZONA COD_ZONA from COM_COMIS_PERIO_CALCU PC
               where PC.PERI_COD_PERI = pscodigoPeriodo
                 and PC.COD_COMI = psComisionIngresos
              union
              select CM.COD_EJEC COD_CLIE,CM.COD_CAMP COD_PERI, CM.COD_ZONA COD_ZONA from COM_COMIS_EJCAL_CABEC CM
               where CM.COD_PAIS = pscodigopais
                 AND CM.COD_TIPO_COMI = pstipoComisionista
                 AND CM.COD_CAMP = pscodigoPeriodo
                 AND CM.COD_COMI = psCodigoComision
                 AND CM.VAL_MOTO_COMI > 0) CLIENTES

      WHERE MC.COD_CLIE = ML.COD_CLID
            AND ML.PAIS_COD_PAIS = pscodigopais
            AND CLIENTES.COD_CLIE = ML.COD_CLID
            AND MC.PAIS_OID_PAIS = ln_id_pais
            AND ML.COD_PROV IS NOT NULL
            AND NOT EXISTS (
                SELECT 1
                FROM MAE_CLIEN_LIDER_BLOQU LB
                WHERE LB.COD_CLIB = CLIENTES.COD_CLIE
                 AND LB.PAIS_COD_PAIS = pscodigopais
                AND LB.PER_INIC  <= CLIENTES.COD_PERI AND
                 ( LB.PER_FINA >= CLIENTES.COD_PERI OR LB.PER_FINA IS NULL )
                 )
             AND (COM_PKG_REPOR.COM_FN_DEVUE_COMIS_EJCAL(pscodigopais,
                                                      psCodigoComision,
                                                      CLIENTES.COD_CLIE,
                                                      pscodigoPeriodo,
                                                      lvval_tasa_impu)+
                 COM_PKG_REPOR.COM_FN_DEVUE_COMIS_INGRE_EJECU(CLIENTES.COD_PERI,
                                                             CLIENTES.COD_CLIE,
                                                             psComisionIngresos)  *
               (1 + lvval_tasa_impu / 100))>0;

             TYPE interfazCom IS RECORD
             ( fecDocumento     varchar2(10),
               codReferencia    varchar2(16),
               codAsignacion    VARCHAR2(15),
               nomproveedor     varchar2(40),
               codProveedor     varchar2(10),
               valImporte       varchar2(15),
               valPeriodoComercial            VARCHAR2(4),
               valEjercicioComercial          VARCHAR2(4),
               valImporteTotal         varchar2(15),
               textoCabecera           varchar2(25),
               indicadorIGV            varchar2(2),
               ordenEstadistico        varchar2(7),
               nivelEjecutiva          varchar2(2),
               codigoPeriodo  COM_COMIS_EJCAL_CABEC.COD_CAMP%TYPE,
               codigoZona     COM_COMIS_EJCAL_CABEC.COD_ZONA%TYPE
             );

             TYPE interfazComTab  IS TABLE OF interfazCom ;
             interfazRecord interfazComTab;

   lbAbrirUtlFile  BOOLEAN;
BEGIN
     ln_id_pais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    lverror:='x1';
        BEGIN
            SELECT PT.VAL_TASA_IMPU
            into lvval_tasa_impu
             FROM PED_TASA_IMPUE PT
            WHERE PT.PAIS_OID_PAIS = ln_id_pais
            AND PT.VAL_INDI_IMPU = ( SELECT DE.COD_IMPU FROM COM_DATOS_FIJOS_EJECU DE
                                                  WHERE DE.PAIS_COD_PAIS = pscodigopais
                                                  AND DE.COD_TIPO_COMI = pstipoComisionista);
           EXCEPTION
              WHEN NO_DATA_FOUND THEN
                  RAISE_APPLICATION_ERROR(-20123, 'No encontro Valor del Impuesto (IGV) , Verificar');
        END;
    lverror:='x2';
        BEGIN
              SELECT DE.COD_SOCI AS SOCIEDAD,
                     DE.COD_MONE AS MONEDA,
                     DE.COD_CLAS_DOCU AS CLASEDOCUMENTO,
                     DE.VAL_TEXT_CABE AS TEXTOCABECERA,
                     DE.COD_CLAV_PROV AS CLAVEPROVEEDOR,
                     DE.COD_CALC_IMPU AS CALCULOIMPUESTO,
                     DE.COD_CLAV_CTAS_GAST AS CLAVECTASGASTOS,
                     DE.COD_CTAS_CONT AS CUENTACONTABLE,
                     DE.COD_CENT_COST AS CENTRODECOSTO,
                     DE.COD_CLAV_SEGU_POSI AS CLAVESEGPOSICION,
                     DE.COD_MARC           AS MARCA,
                     DE.COD_NEGO           AS NEGOCIO,
                     DE.IND_IGV           AS INDICADORIGV
              INTO   lvcodSociedad, lvmoneda, lvclaseDocumento, lvtextoCabecera,
                     lvclaveProveedor, lvcalculoImpuesto, lvclaveCtasGastos,
                     lvcuentaContable, lvcentroCosto, lvclavePosicion,
                     lvmarca, lvnegocio, lvindicadorIgv
              FROM COM_DATOS_FIJOS_EJECU DE
              WHERE DE.PAIS_COD_PAIS = pscodigopais
                AND DE.COD_TIPO_COMI = pstipoComisionista;

           EXCEPTION
              WHEN NO_DATA_FOUND THEN
                   lvcodSociedad     :='';
                   lvmoneda          :='';
                   lvclaseDocumento  :='';
                   lvclaveProveedor  :='';
                   lvcalculoImpuesto :='';
                   lvclaveCtasGastos :='';
                   lvcuentaContable  :='';
                   lvcentroCosto     :='';
                   lvclavePosicion   :='';
                   lvmarca           :='';
                   lvnegocio         :='';
           END;
    lverror:='x3';
     lbAbrirUtlFile := TRUE;
     OPEN C_CARGA(lvval_tasa_impu,ln_id_pais);
         lverror:='x4';
      LOOP
         FETCH C_CARGA BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
                  lverror:='x5';
         /* Procedimiento inicial para generar interfaz */
          IF lbAbrirUtlFile THEN
             GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
          END IF;
         IF interfazRecord.COUNT > 0 THEN
                  lverror:='x6';
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                     lverror:='x7';
            I:=I+1;
         lverror:=interfazRecord(x).valImporte;
            lsLinea :=       lvcodSociedad                          ||';'||  --Sociedad
                             interfazRecord(x).fecDocumento         ||';'||  --Fecha de Documento
                             interfazRecord(x).fecDocumento         ||';'||  --Fecha Contabilizacion
                             lvmoneda                               ||';'||  --Moneda
                             lvclaseDocumento                       ||';'||  --Clase Documento
                             interfazRecord(x).codReferencia        ||';'||  --cod Referencia
                             interfazRecord(x).codAsignacion        ||';'||  --cod Asignacion
                             interfazRecord(x).nomproveedor         ||';'||  --Nom Proveedor
                             'C' || SUBSTR(interfazRecord(x).codigoPeriodo,5,2) || '/' || SUBSTR(interfazRecord(x).codigoPeriodo,3,2) || ' ' || lvtextoCabecera ||';'||  --Texto Cabecera
                             lvclaveProveedor                       ||';'||  --Clave Proveedor
                             interfazRecord(x).codProveedor         ||';'||  --cod Proveedor
                             interfazRecord(x).valImporte           ||';'||  --valImporte 1
                             lvcalculoImpuesto                      ||';'||  --lvcalculoImpuesto
                             lvclaveCtasGastos                      ||';'||  --Clave Ctas Gastos
                             lvcuentaContable                       ||';'||  --Cuenta Contable
                             lvcentroCosto                          ||';'||  --CentroCosto
                             interfazRecord(x).valPeriodoComercial  ||';'||  --valPeriodoComercial
                             interfazRecord(x).valEjercicioComercial||';'||  --valEjercicioComercial
                             lvclavePosicion                        ||';'||  --Clave Seg Posicion
                             lvmarca                                ||';'||  --Marca
                             lvnegocio                              ||';'||  --Cod Negocio
                             interfazRecord(x).indicadorIgv         ||';'||  --Indicador Igv
                             interfazRecord(x).valImporteTotal      ||';'||  --valImporte total
                             interfazRecord(x).ordenEstadistico     ||';'||  --Orden Estadistico
                             interfazRecord(x).nivelEjecutiva       ||';'||  --Nivel Ejecutiva
                             interfazRecord(x).codigoZona;                   --Codigo Zona

      UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            END LOOP;
          END IF;
         EXIT WHEN C_CARGA%NOTFOUND;
      END LOOP;
      CLOSE C_CARGA;

    IF NOT lbAbrirUtlFile THEN
       utl_file.fclose(v_handle);

       /* Procedimiento final para generar interfaz */
       GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(lverror||'-'||ln_sqlcode||'-'||sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR NT_PR_COM_PAGOS_COMIS_EJECU: '||ls_sqlerrm);
END INT_PR_COM_PAGOS_COMIS_EJECU;
/***************************************************************************
Descripcion       : Genera Nueva Interfase de Adam
Fecha Creacion    : 02/12/2009
Autor             : Paolo Lopez Medel
***************************************************************************/
PROCEDURE INT_PR_COM_ADAM_MODIF
  (psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2,
   psNumeroLote               VARCHAR2,
   psCodigoComision           VARCHAR2,
   psFechaProcesoInicio       VARCHAR2,
   psFechaProcesoFin          VARCHAR2,
   codigoPeriodoProceso1      VARCHAR2,
   codigoPeriodoProceso2      VARCHAR2,
   indicador                  VARCHAR2)
IS
   CURSOR c_interfaz IS
   SELECT D.COD_COMP,(SELECT B.COD_EMPL FROM   MAE_CLIEN C, MAE_CLIEN_DATOS_ADICI B
          WHERE  C.OID_CLIE = B.CLIE_OID_CLIE
                 AND C.COD_CLIE = A.COD_LIDE_ZONA
                 AND B.COD_EMPL IS NOT NULL) AS COD_EMPL,
          A.NOM_LIDE_ZONA as nomEmpleado,
          (SELECT ci.num_docu_iden
           FROM MAE_CLIEN_IDENT ci
           WHERE ci.val_iden_docu_prin = 1
           AND ci.clie_oid_clie = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_LIDE_ZONA)) docu_ident,
          D.COD_INCI_RECU,' ',(NVL(A.IMP_COMI_TRA1,0)+NVL(A.IMP_COMI_TRA2,0)+NVL(A.IMP_COMI_ACTI,0)) as IMP_COMI,A.PERI_COD_PERI as COD_PERI,'        ',A.POR_RECU,' ',' ',' '
    FROM COM_COMIS_PERIO_CALCU_ZONA A,COM_DATOS_ADAM D
    WHERE
         A.COD_COMI =    psCodigoComision and D.COD_PAIS = psCodigoPais
         AND (A.PERI_COD_PERI = codigoPeriodoProceso1 OR
              A.PERI_COD_PERI = DECODE(codigoPeriodoProceso1,NULL,codigoPeriodoProceso1, '',codigoPeriodoProceso1, codigoPeriodoProceso2)
              )
       ORDER BY 2;

   CURSOR c_interfaz1  IS
   SELECT D.COD_COMP,(SELECT B.COD_EMPL  FROM   MAE_CLIEN C,  MAE_CLIEN_DATOS_ADICI B
        WHERE  C.OID_CLIE = B.CLIE_OID_CLIE
               AND C.COD_CLIE = A.COD_LIDE_ZONA
               AND B.COD_EMPL IS NOT NULL) AS COD_EMPL,
          A.NOM_LIDE_ZONA as nomEmpleado,
          (SELECT ci.num_docu_iden
           FROM MAE_CLIEN_IDENT ci
           WHERE ci.val_iden_docu_prin = 1
           AND ci.clie_oid_clie = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(A.COD_LIDE_ZONA)) docu_ident,
          D.COD_INCI_COME,' ',A.IMP_COMI,TO_CHAR(A.FEC_INIC,'DDMMYYYY'),TO_CHAR(A.FEC_FINA,'DDMMYYYY'),null,' ',' ',' '
      FROM COM_COMIS_PERIO_CALCU_COMER A,COM_DATOS_ADAM D
      WHERE A.COD_COMI = psCodigoComision AND D.COD_PAIS = psCodigoPais
      AND A.FEC_INIC >= to_date (psFechaProcesoInicio,'dd/mm/yyyy')
      AND A.FEC_FINA <= to_date (psFechaProcesoFin,'dd/mm/yyyy')
      ORDER BY 2 ;

   TYPE interfazCom IS RECORD
   (
    codigoCompania          VARCHAR2(4),
    codigoEmpleado          VARCHAR2(12),
    nomEmpleado             VARCHAR2(100),
    documentoIndentidad     VARCHAR2(30),
    codigoIncidencia        VARCHAR2(3),
    variable1             	VARCHAR2(1),
  	variable2            		VARCHAR2(18),
  	variable3             	VARCHAR2(8),
  	variable4             	VARCHAR2(8),
  	porcentajeRecupTramo1   NUMBER(12,2),
  	referencia2           	VARCHAR2(1),
  	estatus             		VARCHAR2(1),
  	errores             		VARCHAR2(1)
   );
   TYPE interfazComTab  IS TABLE OF interfazCom ;
   interfazRecord interfazComTab;
  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  W_FILAS             NUMBER := 1000 ;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
BEGIN
    /* Procedimiento inicial para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
        psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);

    /* Generando Archivo de Texto (Detalle) */

    IF indicador ='PERIODO' THEN
      OPEN c_interfaz;
      LOOP
         FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoCompania            ||';'||
                        interfazRecord(x).codigoEmpleado            ||';'||
                        interfazRecord(x).nomEmpleado               ||';'||
                        interfazRecord(x).documentoIndentidad       ||';'||
                        interfazRecord(x).codigoIncidencia          ||';'||
						            interfazRecord(x).variable1                 ||';'||
						            interfazRecord(x).variable2                 ||';'||
						            interfazRecord(x).variable3                 ||';'||
						            interfazRecord(x).variable4                 ||';'||
						            interfazRecord(x).porcentajeRecupTramo1     ||';'||
						            interfazRecord(x).referencia2               ||';'||
                        interfazRecord(x).estatus                   ||';'||
						            interfazRecord(x).errores ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END LOOP;
         END IF;
         EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz;
    ELSE
      OPEN c_interfaz1;
      LOOP
         FETCH c_interfaz1 BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                lsLinea :=  interfazRecord(x).codigoCompania            ||';'||
                            interfazRecord(x).codigoEmpleado            ||';'||
                            interfazRecord(x).nomEmpleado               ||';'||
                            interfazRecord(x).documentoIndentidad       ||';'||
                            interfazRecord(x).codigoIncidencia          ||';'||
                						interfazRecord(x).variable1                 ||';'||
                						interfazRecord(x).variable2                 ||';'||
                						interfazRecord(x).variable3                 ||';'||
                						interfazRecord(x).variable4                 ||';'||
                						interfazRecord(x).porcentajeRecupTramo1     ||';'||
                						interfazRecord(x).referencia2               ||';'||
                            interfazRecord(x).estatus                   ||';'||
						                interfazRecord(x).errores ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END LOOP;
         END IF;
         EXIT WHEN c_interfaz1%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz1;
    END IF;
    utl_file.fclose(V_HANDLE);

    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    RETURN;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_COM_ADAM: '||ls_sqlerrm);
END INT_PR_COM_ADAM_MODIF;
END INT_PKG_COMIS;
/

