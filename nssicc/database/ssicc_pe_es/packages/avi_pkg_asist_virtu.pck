CREATE OR REPLACE PACKAGE AVI_PKG_ASIST_VIRTU IS

  /* Declaracion de Variables */
  LN_SQLCODE NUMBER(10);
  LS_SQLERRM VARCHAR2(150);
  W_FILAS             NUMBER := 5000 ;

  PROCEDURE AVI_PR_CARGA_FACTU(ps_Cod_Peri       IN VARCHAR2,
                               ps_Cod_Peri_Cruce IN VARCHAR2,
                               ps_Cod_Pais       IN VARCHAR2,
                               ps_Cod_Marca      IN VARCHAR2,
                               ps_Cod_Canal      IN VARCHAR2);

  PROCEDURE AVI_PR_CARGA_FACTU_CABEC(ps_Cod_Peri       IN VARCHAR2,
                                     ps_Cod_Peri_Cruce IN VARCHAR2,
                                     ps_Cod_Pais       IN VARCHAR2,
                                     ps_Cod_Marca      IN VARCHAR2,
                                     ps_Cod_Canal      IN VARCHAR2);

  PROCEDURE AVI_PR_CARGA_FACTU_DETAL(ps_Cod_Peri       IN VARCHAR2,
                                     ps_Cod_Peri_Cruce IN VARCHAR2,
                                     ps_Cod_Pais       IN VARCHAR2,
                                     ps_Cod_Marca      IN VARCHAR2,
                                     ps_Cod_Canal      IN VARCHAR2);

  PROCEDURE AVI_PR_INTER_CONSU_PUNTA (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2,
                                      psCodigoPeriodo         VARCHAR2,
                                      psCodigoPeriodoCruce    VARCHAR2,
                                      psEnviarNovedades       VARCHAR2);

  PROCEDURE AVI_PR_INTER_CONSU_DECDR (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2,
                                      psCodigoPeriodo         VARCHAR2,
                                      psCodigoPeriodoCruce    VARCHAR2);

  FUNCTION AVI_FN_VALID_REGIO_CERRA(pnOidPeriodo        NUMBER,
                                    pnOidRegion         NUMBER)
    RETURN NUMBER;

  PROCEDURE AVI_PR_INTER_SALDO_CONSU (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2,
                                      psCodigoPeriodo         VARCHAR2,
                                      psCodigoPeriodoCruce    VARCHAR2,
                                      psEnviarNovedades       VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfase Consultoras Bloqueadas
  Fecha Creacion    : 16/08/2010
  Autor             : Carlos Diaz Valverde
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoSistema  : Codigo de Sistema
              psCodigoInterfaz : Codigo de Interfaz
              psNombreArchivo  : Nombre de Archivo a generarse en el servidor
  ***************************************************************************/
  PROCEDURE AVI_PR_INTER_CONSU_BLOQU (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      psCodigoInterfaz        VARCHAR2,
                                      psNombreArchivo         VARCHAR2);

  /***************************************************************************
  Descripcion       : Genera Interfase Tipos de Bloqueos
  Fecha Creacion    : 16/08/2010
  Autor             : Carlos Diaz Valverde
  Parametros        :
              psCodigoPais     : Codigo de Pais
              psCodigoSistema  : Codigo de Sistema
              psCodigoInterfaz : Codigo de Interfaz
              psNombreArchivo  : Nombre de Archivo a generarse en el servidor
  ***************************************************************************/
  PROCEDURE AVI_PR_INTER_TIPOS_BLOQU (psCodigoPais            VARCHAR2,
                                      psCodigoSistema         VARCHAR2,
                                      Pscodigointerfaz        Varchar2,
                                      psNombreArchivo         VARCHAR2);

END AVI_PKG_ASIST_VIRTU;
/

CREATE OR REPLACE PACKAGE BODY AVI_PKG_ASIST_VIRTU IS

PROCEDURE AVI_PR_CARGA_FACTU(ps_Cod_Peri       IN VARCHAR2,
                             ps_Cod_Peri_Cruce IN VARCHAR2,
                             ps_Cod_Pais       IN VARCHAR2,
                             ps_Cod_Marca      IN VARCHAR2,
                             ps_Cod_Canal      IN VARCHAR2) IS
BEGIN
  -- Ejecutamos el detalle ya que el numero de rechazados
  -- enviado por la cabecera lo ha de tomar de esta tabla
  AVI_PR_CARGA_FACTU_DETAL(ps_Cod_Peri,
                           ps_Cod_Peri_Cruce,
                           ps_Cod_Pais,
                           ps_Cod_Marca,
                           ps_Cod_Canal);

  -- Ejecutamos el calculo de estadisticos de la cabecera
  AVI_PR_CARGA_FACTU_CABEC(ps_Cod_Peri,
                           ps_Cod_Peri_Cruce,
                           ps_Cod_Pais,
                           ps_Cod_Marca,
                           ps_Cod_Canal);
END AVI_PR_CARGA_FACTU;


/**************************************************************************
Descripcion       : Carga la informacion de perido seleccionado y perido de cruce en la tabla
                    temporal INT_AVI_FACTU_CABEC_TEMPO que servira para la obtencion de la informacion a enviar
                    de Facturacion - Cabecera para la interfaz.
Fecha Creacion    : 30/07/2008
Parametros Entrada:
  ps_cod_peri     :  Codigo de periodo
  ps_cod_peri_cruce   :  Codigo de periodo de cruce
  ps_cod_pais     :  Codigo de pais
  ps_cod_marca    :  Codigo de marca
  ps_cod_canal    :  Codigo de canal

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE AVI_PR_CARGA_FACTU_CABEC(ps_Cod_Peri       IN VARCHAR2,
                                   ps_Cod_Peri_Cruce IN VARCHAR2,
                                   ps_Cod_Pais       IN VARCHAR2,
                                   ps_Cod_Marca      IN VARCHAR2,
                                   ps_Cod_Canal      IN VARCHAR2)
IS
  CURSOR C_PEDI_RECH IS
    SELECT INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS,
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI,
           INT_AVI_FACTU_DETAL_TEMPO.COD_REGI,
           INT_AVI_FACTU_DETAL_TEMPO.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           COUNT(1) NUM_PEDI_RECH
      FROM INT_AVI_FACTU_DETAL_TEMPO, ZON_REGIO, ZON_ZONA
     WHERE ((INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS = ps_Cod_Pais) AND
           (INT_AVI_FACTU_DETAL_TEMPO.COD_REGI = ZON_REGIO.COD_REGI) AND
           (INT_AVI_FACTU_DETAL_TEMPO.COD_ZONA = ZON_ZONA.COD_ZONA) AND
           (INT_AVI_FACTU_DETAL_TEMPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS,
              INT_AVI_FACTU_DETAL_TEMPO.COD_REGI,
              INT_AVI_FACTU_DETAL_TEMPO.COD_ZONA,
              INT_AVI_FACTU_DETAL_TEMPO.COD_PERI,
              ZON_REGIO.OID_REGI,
              ZON_ZONA.OID_ZONA;

  R_PEDI_RECH C_PEDI_RECH%ROWTYPE;

  CURSOR C_ESTI IS
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           INT_FUENT_VENTA_PREVI_SAP.NUM_PEDI AS NUM_PEDI_ESTI,
           INT_FUENT_VENTA_PREVI_SAP.NUM_ACTI_FINA AS NUM_ESTI_ACTI_FINA
      FROM SEG_PAIS,
           SEG_PERIO_CORPO,
           SEG_MARCA,
           SEG_CANAL,
           ZON_ZONA,
           ZON_REGIO,
           CRA_PERIO,
           INT_FUENT_VENTA_PREVI_SAP
     WHERE ((SEG_PAIS.OID_PAIS = ZON_ZONA.PAIS_OID_PAIS) AND
           (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI) AND
           (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (ZON_ZONA.OID_ZONA = INT_FUENT_VENTA_PREVI_SAP.ZZON_OID_ZONA) AND
           (ZON_REGIO.OID_REGI = INT_FUENT_VENTA_PREVI_SAP.ZORG_OID_REGI) AND
           (CRA_PERIO.OID_PERI = INT_FUENT_VENTA_PREVI_SAP.PERD_OID_PERI) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)));

  CURSOR C_ACTI_FINA IS
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           SUM(INT_FUENT_VENTAS_REAL.NUM_ACTI_FINA) AS NUM_ACTI_FINA
      FROM SEG_PAIS,
           SEG_PERIO_CORPO,
           SEG_MARCA,
           SEG_CANAL,
           ZON_ZONA,
           ZON_REGIO,
           CRA_PERIO,
           INT_FUENT_VENTAS_REAL
     WHERE ((SEG_PAIS.OID_PAIS = ZON_ZONA.PAIS_OID_PAIS) AND
           (ZON_REGIO.OID_REGI = ZON_ZONA.ZORG_OID_REGI) AND
           (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (ZON_ZONA.OID_ZONA = INT_FUENT_VENTAS_REAL.ZZON_OID_ZONA) AND
           (ZON_REGIO.OID_REGI = INT_FUENT_VENTAS_REAL.ZORG_OID_REGI) AND
           (CRA_PERIO.OID_PERI = INT_FUENT_VENTAS_REAL.PERD_OID_PERI) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA;

  CURSOR C_MONTO_FACTU IS
    SELECT  SEG_PAIS.COD_PAIS,
            SEG_PERIO_CORPO.COD_PERI,
            ZON_REGIO.COD_REGI,
            ZON_ZONA.COD_ZONA,
            ZON_REGIO.OID_REGI,
            ZON_ZONA.OID_ZONA,
            SUM(PED_SOLIC_CABEC.VAL_PREC_NETO_TOTA_LOCA) - SUM(PED_SOLIC_CABEC.VAL_IMPO_FLET_SIN_IMPU_TOTA) VAL_MONT_FACT
    FROM CRA_PERIO,
         PED_SOLIC_CABEC,
         PED_TIPO_SOLIC_PAIS,
         SEG_PERIO_CORPO,
         SEG_PAIS,
         SEG_MARCA,
         SEG_CANAL,
         PED_TIPO_SOLIC,
         ZON_ZONA,
         ZON_REGIO
    WHERE (    (CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI)
      AND (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS = PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS)
      AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
      AND (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS)
      AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
      AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
      AND (PED_TIPO_SOLIC.OID_TIPO_SOLI = PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI)
      AND (PED_TIPO_SOLIC.COD_TIPO_SOLI IN (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS))
      AND ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5))
      AND (PED_SOLIC_CABEC.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA)
      AND (ZON_ZONA.ZORG_OID_REGI = ZON_REGIO.OID_REGI)
      AND (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5)
      AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 0)
      AND (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL)
      AND (SEG_PAIS.COD_PAIS = ps_Cod_Pais)
      AND (SEG_MARCA.COD_MARC = ps_Cod_Marca)
      AND (SEG_CANAL.COD_CANA = ps_Cod_Canal)
      AND (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY SEG_PAIS.COD_PAIS,
              ZON_REGIO.COD_REGI,
              ZON_ZONA.COD_ZONA,
              SEG_PERIO_CORPO.COD_PERI,
              ZON_REGIO.OID_REGI,
              ZON_ZONA.OID_ZONA;

  R_ESTI           C_ESTI%ROWTYPE;
  R_ACTI_FINA      C_ACTI_FINA%ROWTYPE;
  R_MONTO_FACTU    C_MONTO_FACTU%ROWTYPE;

  EXISTE NUMBER := 0;
  l_user    VARCHAR2(20);
BEGIN

  -- PRIMERO LIMPIAR TODA LA TABLA TEMPORAL
  EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_AVI_FACTU_CABEC_TEMPO';
  --DELETE FROM INT_AVI_FACTU_CABEC_TEMPO;

  -- INSERTAMOS LAS ZONAS CON LOS VALORES DE LOS PEDIDOS FACTURADOS, Y LOS PRIMEROS PEDIDOS
  INSERT INTO INT_AVI_FACTU_CABEC_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_REGI,
     COD_ZONA,
     OID_REGI,
     OID_ZONA,
     NUM_PEDI_FACT,
     NUM_PRIM_PEDI,
     VAL_MONT_FACT)
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           ZON_REGIO.COD_REGI,
           ZON_ZONA.COD_ZONA,
           ZON_REGIO.OID_REGI,
           ZON_ZONA.OID_ZONA,
           COUNT(DISTINCT PED_SOLIC_CABEC.CLIE_OID_CLIE) AS NROPEDIDOS,
           SUM(CASE WHEN ((MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 1)
                		   OR (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 2 AND (FC2.TOTAL > 0))
                 		   OR (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 8 AND (FC2.TOTAL > 0))
                		   OR (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 7 AND (MAE_CLIEN_DATOS_ADICI.IND_ACTI = 1))
          					) THEN 1
				       ELSE 0 END) AS NUM_PRIM_PEDI,
           0 AS VAL_MONT_FACT
      FROM CRA_PERIO,
           PED_SOLIC_CABEC,
           PED_TIPO_SOLIC_PAIS,
           SEG_PERIO_CORPO,
           SEG_PAIS,
           SEG_MARCA,
           SEG_CANAL,
           PED_TIPO_SOLIC,
           ZON_ZONA,
           ZON_REGIO,
    			 MAE_CLIEN,
    			 MAE_CLIEN_DATOS_ADICI,
           (SELECT CC.OID_PERI, RR.OID_REGI, AVI_PKG_ASIST_VIRTU.AVI_FN_VALID_REGIO_CERRA(CC.OID_PERI,RR.OID_REGI) TOTAL
              FROM CRA_PERIO CC, ZON_REGIO RR, SEG_PERIO_CORPO SS
             WHERE CC.PERI_OID_PERI = SS.OID_PERI
               AND (SS.COD_PERI = ps_Cod_Peri OR
                    (ps_Cod_Peri_Cruce IS NOT NULL AND SS.COD_PERI = ps_Cod_Peri_Cruce))) FC2
     WHERE ((CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI) AND
           (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
           PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS) AND
        	 (MAE_CLIEN.OID_CLIE = PED_SOLIC_CABEC.CLIE_OID_CLIE) AND
        	 (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (PED_TIPO_SOLIC.OID_TIPO_SOLI =
           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI) AND
           (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
           (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS)) AND
           ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR
           (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5)) AND
           (PED_SOLIC_CABEC.ZZON_OID_ZONA = ZON_ZONA.OID_ZONA) AND
           (ZON_ZONA.ZORG_OID_REGI = ZON_REGIO.OID_REGI) AND
           (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5) AND
           (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1) AND
           (PED_SOLIC_CABEC.IND_OC = 1) AND
           (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL) AND
           (FC2.OID_REGI = ZON_REGIO.OID_REGI) AND
           (FC2.OID_PERI = CRA_PERIO.OID_PERI) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce)))
     GROUP BY SEG_PAIS.COD_PAIS,
              ZON_REGIO.COD_REGI,
              ZON_ZONA.COD_ZONA,
              SEG_PERIO_CORPO.COD_PERI,
              ZON_REGIO.OID_REGI,
              ZON_ZONA.OID_ZONA;

  l_user := USER;
  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'INT_AVI_FACTU_CABEC_TEMPO', CASCADE => TRUE );

  -- ACTUALIZAMOS LAS CANTIDADES DE PEDIDOS RECHAZADOS EN CASO SEA NECESARIO
  OPEN C_PEDI_RECH;
  LOOP
    FETCH C_PEDI_RECH
      INTO R_PEDI_RECH;
    EXIT WHEN C_PEDI_RECH%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_PEDI_RECH.COD_PAIS
         AND COD_PERI = R_PEDI_RECH.COD_PERI
         AND COD_REGI = R_PEDI_RECH.COD_REGI
         AND COD_ZONA = R_PEDI_RECH.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET NUM_PEDI_RECH = R_PEDI_RECH.NUM_PEDI_RECH
         WHERE COD_PAIS = R_PEDI_RECH.COD_PAIS
           AND COD_PERI = R_PEDI_RECH.COD_PERI
           AND COD_REGI = R_PEDI_RECH.COD_REGI
           AND COD_ZONA = R_PEDI_RECH.COD_ZONA;
      ELSE
        INSERT INTO INT_AVI_FACTU_CABEC_TEMPO
          (COD_PAIS,
           COD_PERI,
           COD_REGI,
           COD_ZONA,
           OID_REGI,
           OID_ZONA,
           NUM_PEDI_RECH)
        VALUES
          (R_PEDI_RECH.COD_PAIS,
           R_PEDI_RECH.COD_PERI,
           R_PEDI_RECH.COD_REGI,
           R_PEDI_RECH.COD_ZONA,
           R_PEDI_RECH.OID_REGI,
           R_PEDI_RECH.OID_ZONA,
           R_PEDI_RECH.NUM_PEDI_RECH);
      END IF;
    END;
  END LOOP;
  CLOSE C_PEDI_RECH;

  -- ACTUALIZAMOS LOS NUMERO DE ESTIMADOS DE ACTIVAS FINALES, NUMERO DE PEDIDOS ESTIMADOS
  OPEN C_ESTI;
  LOOP
    FETCH C_ESTI
      INTO R_ESTI;
    EXIT WHEN C_ESTI%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_ESTI.COD_PAIS
         AND COD_PERI = R_ESTI.COD_PERI
         AND COD_REGI = R_ESTI.COD_REGI
         AND COD_ZONA = R_ESTI.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET NUM_PEDI_ESTI = R_ESTI.NUM_PEDI_ESTI,
               NUM_ESTI_ACTI_FINA = R_ESTI.NUM_ESTI_ACTI_FINA
         WHERE COD_PAIS = R_ESTI.COD_PAIS
           AND COD_PERI = R_ESTI.COD_PERI
           AND COD_REGI = R_ESTI.COD_REGI
           AND COD_ZONA = R_ESTI.COD_ZONA;
      END IF;
    END;
  END LOOP;
  CLOSE C_ESTI;

  -- ACTUALIZAMOS LOS NUMERO DE ACTIVAS FINALES
  OPEN C_ACTI_FINA;
  LOOP
    FETCH C_ACTI_FINA
      INTO R_ACTI_FINA;
    EXIT WHEN C_ACTI_FINA%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_ACTI_FINA.COD_PAIS
         AND COD_PERI = R_ACTI_FINA.COD_PERI
         AND COD_REGI = R_ACTI_FINA.COD_REGI
         AND COD_ZONA = R_ACTI_FINA.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET NUM_ACTI_FINA      = R_ACTI_FINA.NUM_ACTI_FINA
         WHERE COD_PAIS = R_ACTI_FINA.COD_PAIS
           AND COD_PERI = R_ACTI_FINA.COD_PERI
           AND COD_REGI = R_ACTI_FINA.COD_REGI
           AND COD_ZONA = R_ACTI_FINA.COD_ZONA;
      END IF;
    END;
  END LOOP;
  CLOSE C_ACTI_FINA;

  -- ACTUALIZAMOS LOS MONTOS FACTURADOS
  OPEN C_MONTO_FACTU;
  LOOP
    FETCH C_MONTO_FACTU
      INTO R_MONTO_FACTU;
    EXIT WHEN C_MONTO_FACTU%NOTFOUND;
    BEGIN
      SELECT COUNT(1)
        INTO EXISTE
        FROM INT_AVI_FACTU_CABEC_TEMPO
       WHERE COD_PAIS = R_MONTO_FACTU.COD_PAIS
         AND COD_PERI = R_MONTO_FACTU.COD_PERI
         AND COD_REGI = R_MONTO_FACTU.COD_REGI
         AND COD_ZONA = R_MONTO_FACTU.COD_ZONA;

      IF (EXISTE = 1) THEN
        UPDATE INT_AVI_FACTU_CABEC_TEMPO
           SET VAL_MONT_FACT = R_MONTO_FACTU.VAL_MONT_FACT
         WHERE COD_PAIS = R_MONTO_FACTU.COD_PAIS
           AND COD_PERI = R_MONTO_FACTU.COD_PERI
           AND COD_REGI = R_MONTO_FACTU.COD_REGI
           AND COD_ZONA = R_MONTO_FACTU.COD_ZONA;
      END IF;
    END;
  END LOOP;
  CLOSE C_MONTO_FACTU;

  -- CALCULAMOS EL PORCENTAJE DE PEDIDOS FACTURADOS, LOS PEDIDOS TOTALES POR FACTURAR,
  -- PORCENTAJE DE ACTIVIDAD FINAL, MONTO PROMEDIO PEDIDOS FACTURADOS
  UPDATE INT_AVI_FACTU_CABEC_TEMPO
     SET VAL_PORC_PEDI_FACT = DECODE(NUM_PEDI_ESTI, 0, 0, ROUND(NUM_PEDI_FACT / NUM_PEDI_ESTI * 100)),
         NUM_PEDI_TOTA = NUM_PEDI_FACT + NUM_PEDI_RECH,
         VAL_PORC_ACTI_FINA = DECODE(NUM_ESTI_ACTI_FINA, 0, 0, ROUND(NUM_ACTI_FINA / NUM_ESTI_ACTI_FINA * 100)),
         VAL_PROM_PEDI_FACT = DECODE(NUM_ACTI_FINA, 0, 0, ROUND(VAL_MONT_FACT / NUM_ACTI_FINA, 2));

END AVI_PR_CARGA_FACTU_CABEC;

/**************************************************************************
Descripcion       : Carga la informacion de perido seleccionado y perido de cruce en la tabla
                    temporal INT_AVI_FACTU_DETAL_TEMPO que servira para la obtencion de la informacion a enviar
                    de Facturacion - Detalle para la interfaz.
Fecha Creacion    : 30/07/2008
Parametros Entrada:
  ps_cod_peri     :  Codigo de periodo
  ps_cod_peri_cruce   :  Codigo de periodo de cruce
  ps_cod_pais     :  Codigo de pais
  ps_cod_marca    :  Codigo de marca
  ps_cod_canal    :  Codigo de canal

Autor             : Sergio Apaza
***************************************************************************/
PROCEDURE AVI_PR_CARGA_FACTU_DETAL(ps_Cod_Peri       IN VARCHAR2,
                                   ps_Cod_Peri_Cruce IN VARCHAR2,
                                   ps_Cod_Pais       IN VARCHAR2,
                                   ps_Cod_Marca      IN VARCHAR2,
                                   ps_Cod_Canal      IN VARCHAR2) IS

  CURSOR C_FACTURADOS IS
    SELECT INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS,
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI,
           INT_AVI_FACTU_DETAL_TEMPO.COD_CLIE
      FROM CRA_PERIO,
           PED_SOLIC_CABEC,
           PED_TIPO_SOLIC_PAIS,
           SEG_PERIO_CORPO,
           SEG_PAIS,
           SEG_MARCA,
           SEG_CANAL,
           PED_TIPO_SOLIC,
           INT_AVI_FACTU_DETAL_TEMPO
     WHERE ((CRA_PERIO.OID_PERI = PED_SOLIC_CABEC.PERD_OID_PERI) AND
           (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
           PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS) AND
           (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI) AND
           (SEG_PAIS.OID_PAIS = PED_SOLIC_CABEC.PAIS_OID_PAIS) AND
           (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC) AND
           (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA) AND
           (PED_TIPO_SOLIC.OID_TIPO_SOLI =
           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI) AND
           (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
           (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS)) AND
           ((PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 1) OR
           (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 5)) AND
           (PED_SOLIC_CABEC.GRPR_OID_GRUP_PROC = 5) AND
           (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1) AND
           (PED_SOLIC_CABEC.IND_OC = 1) AND
           (PED_SOLIC_CABEC.FEC_FACT IS NOT NULL) AND
           (SEG_PAIS.COD_PAIS = ps_Cod_Pais) AND
           (SEG_MARCA.COD_MARC = ps_Cod_Marca) AND
           (SEG_CANAL.COD_CANA = ps_Cod_Canal) AND
           (SEG_PAIS.COD_PAIS = INT_AVI_FACTU_DETAL_TEMPO.COD_PAIS) AND
           (SEG_PERIO_CORPO.COD_PERI =
           INT_AVI_FACTU_DETAL_TEMPO.COD_PERI) AND
           (PED_SOLIC_CABEC.CLIE_OID_CLIE =
           INT_AVI_FACTU_DETAL_TEMPO.OID_CLIE));

  R_FACTURADOS C_FACTURADOS%ROWTYPE;

  FEC_MAX_FACT DATE;
  l_user  VARCHAR2(20);
BEGIN

  -- primero limpiar toda la tabla
  EXECUTE IMMEDIATE 'TRUNCATE TABLE INT_AVI_FACTU_DETAL_TEMPO';

  -- Insertamos los detalles considerando tambien los activos
  -- para los casos en que un pedido fue inicialmente rechazado
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     OID_CLIE,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT SEG_PAIS.COD_PAIS,
           SEG_PERIO_CORPO.COD_PERI,
           MAE_CLIEN.OID_CLIE,
           MAE_CLIEN.COD_CLIE,
           (SELECT r.COD_REGI FROM ZON_REGIO r, ZON_ZONA z
             WHERE r.OID_REGI = z.ZORG_OID_REGI
               AND z.OID_ZONA =  PED_SOLIC_CABEC.ZZON_OID_ZONA),
           (SELECT COD_ZONA FROM ZON_ZONA WHERE OID_ZONA = PED_SOLIC_CABEC.ZZON_OID_ZONA),
           (EVI_PKG_EJECU_VIRTU.EVI_FN_CALCU_VALOR_SALDO_DEUD2(MAE_CLIEN.COD_CLIE)) AS SALDO_CONSULTORA,
           (CASE
             WHEN ((MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 1 OR
                  MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE = 2) AND
                  PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3) THEN
              ('M')
             ELSE
              CASE
             WHEN (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3) THEN
              ('D')
             ELSE
              ('N')
           END END),
           PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI,
           PED_SOLIC_CABEC.FEC_PROG_FACT
      FROM PED_SOLIC_CABEC,
           PED_TIPO_SOLIC_PAIS,
           MAE_CLIEN,
           MAE_CLIEN_DATOS_ADICI,
           PED_TIPO_SOLIC,
           SEG_CANAL,
           SEG_MARCA,
           SEG_PAIS,
           SEG_PERIO_CORPO,
           CRA_PERIO
     WHERE (PED_SOLIC_CABEC.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
       AND (PED_TIPO_SOLIC_PAIS.OID_TIPO_SOLI_PAIS =
           PED_SOLIC_CABEC.TSPA_OID_TIPO_SOLI_PAIS)
       AND (PED_TIPO_SOLIC.OID_TIPO_SOLI =
           PED_TIPO_SOLIC_PAIS.TSOL_OID_TIPO_SOLI)
       AND (PED_TIPO_SOLIC.COD_TIPO_SOLI IN
           (SELECT COD_TIPO_SOLI FROM INT_EVI_TIPO_SOLIC_PAIS))
       AND (PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 2 OR
           PED_SOLIC_CABEC.ESSO_OID_ESTA_SOLI = 3)
       AND (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
       AND (PED_SOLIC_CABEC.PERD_OID_PERI = CRA_PERIO.OID_PERI)
       AND (SEG_CANAL.OID_CANA = CRA_PERIO.CANA_OID_CANA)
       AND (SEG_MARCA.OID_MARC = CRA_PERIO.MARC_OID_MARC)
       AND (SEG_PAIS.OID_PAIS = CRA_PERIO.PAIS_OID_PAIS)
       AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
       AND (PED_SOLIC_CABEC.IND_TS_NO_CONSO = 1)
       AND (MAE_CLIEN_DATOS_ADICI.IND_ACTI = 1) -- Se envian solamente las consultoras activas
       AND (SEG_PAIS.COD_PAIS = ps_Cod_Pais)
       AND (SEG_MARCA.COD_MARC = ps_Cod_Marca)
       AND (SEG_CANAL.COD_CANA = ps_Cod_Canal)
       AND (SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri OR
           (ps_Cod_Peri_Cruce IS NOT NULL AND
           SEG_PERIO_CORPO.COD_PERI = ps_Cod_Peri_Cruce));

  -- Incluimos los registros rechazados por deuda de la tabla INT_SOLIC_CONSO_CABEC
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT A.COD_PAIS,
           A.COD_PERI,
           A.COD_CLIE,
           A.COD_REGI,
           A.COD_ZONA,
           A.VAL_SALD_DEUD,
    		   'D' AS RECHAZO,
           3, -- Usamos el OID de los rechazados por deuda
           A.FEC_SOLI
      FROM INT_SOLIC_CONSO_CABEC A
     WHERE (A.IND_ERRO_DEUD = '2')
   	   AND (A.IND_ERROR_SGPE = '0')
       AND (A.IND_ERRO_REMP = '0')
       AND (A.IND_ADMI_CART = '0')
       AND (A.IND_OCS_PROC = '0')
       AND (A.IND_CONT_ACT = '0') -- Se envian solo las activas
       AND (A.FEC_SOLI < SYSDATE)
       AND NOT EXISTS
     (SELECT B.COD_PERI
              FROM INT_AVI_FACTU_DETAL_TEMPO B
             WHERE B.COD_PAIS = A.COD_PAIS
               AND B.COD_CLIE = A.COD_CLIE
               AND B.COD_PERI = A.COD_PERI)
       AND (A.COD_PERI = ps_Cod_Peri OR (ps_Cod_Peri_Cruce IS NOT NULL AND
           A.COD_PERI = ps_Cod_Peri_Cruce));

  -- Eliminamos a las consultoras que tiene una solicitud con status valido y que esten en
  -- el periodo correspondiente
  l_user := USER;
  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => l_user, TABNAME => 'INT_AVI_FACTU_DETAL_TEMPO', CASCADE => TRUE );

  OPEN C_FACTURADOS;
  LOOP
    FETCH C_FACTURADOS
      INTO R_FACTURADOS;
    EXIT WHEN C_FACTURADOS%NOTFOUND;
    BEGIN
      DELETE FROM INT_AVI_FACTU_DETAL_TEMPO
       WHERE COD_PAIS = R_FACTURADOS.COD_PAIS
         AND COD_PERI = R_FACTURADOS.COD_PERI
         AND COD_CLIE = R_FACTURADOS.COD_CLIE;
    END;

  END LOOP;
  CLOSE C_FACTURADOS;

  -- Obtenemos la fecha maxima de facturacion
  SELECT MAX(FEC_PROG_FACT)
    INTO FEC_MAX_FACT
    FROM INT_AVI_FACTU_DETAL_TEMPO
   WHERE COD_PAIS = ps_Cod_Pais
     AND COD_PERI = ps_Cod_Peri;

  -- Eliminamos aquellos detalles que no corresponden a la fecha
  -- maxima de facturacion programada
  IF FEC_MAX_FACT IS NOT NULL THEN
    DELETE FROM INT_AVI_FACTU_DETAL_TEMPO
     WHERE FEC_PROG_FACT <> FEC_MAX_FACT
       AND COD_PAIS = ps_Cod_Pais
       AND COD_PERI = ps_Cod_Peri
       AND COD_MOTI_RECH <> 'D';
  END IF;

  -- Hacemos lo mismo en caso haya periodo de cruce
  IF ps_Cod_Peri_Cruce IS NOT NULL THEN
    FEC_MAX_FACT := NULL;
    -- Obtenemos la fecha maxima de facturacion
    SELECT MAX(FEC_PROG_FACT)
      INTO FEC_MAX_FACT
      FROM INT_AVI_FACTU_DETAL_TEMPO
     WHERE COD_PAIS = ps_Cod_Pais
       AND COD_PERI = ps_Cod_Peri_Cruce;

    -- Eliminamos aquellos detalles que no corresponden a la fecha
    -- maxima de facturacion programada
    IF FEC_MAX_FACT IS NOT NULL THEN
      DELETE FROM INT_AVI_FACTU_DETAL_TEMPO
       WHERE FEC_PROG_FACT <> FEC_MAX_FACT
         AND COD_PAIS = ps_Cod_Pais
         AND COD_PERI = ps_Cod_Peri_Cruce
         AND COD_MOTI_RECH <> 'D';
    END IF;
  END IF;

  -- Incluimos los registros rechazados por MontoMinimo y Monto Maximo
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT A.COD_PAIS,
           A.COD_PERI,
           A.COD_CLIE,
           A.COD_REGI,
           A.COD_ZONA,
           A.VAL_SALD_DEUD,
    		   (CASE WHEN (A.IND_ERRO_MTMA = '1') THEN 'M'
    		         WHEN (A.IND_ERRO_MTMI = '1') THEN 'N'
    		    END) AS RECHAZO,
           3, -- Usamos el OID de los rechazados por deuda
           A.FEC_SOLI
      FROM INT_SOLIC_CONSO_CABEC A
     WHERE ((A.IND_ERRO_MTMI = '1') OR
			     (A.IND_ERRO_MTMA = '1'))
   	   AND (A.IND_ERROR_SGPE = '0')
       AND (A.IND_ERRO_REMP = '0')
       AND (A.IND_ADMI_CART = '0')
       AND (A.IND_OCS_PROC = '0')
       AND (A.IND_CONT_ACT = '0') -- Se envian solo las activas
       AND (A.FEC_SOLI < SYSDATE)
       AND NOT EXISTS
     (SELECT B.COD_PERI
              FROM INT_AVI_FACTU_DETAL_TEMPO B
             WHERE B.COD_PAIS = A.COD_PAIS
               AND B.COD_CLIE = A.COD_CLIE
               AND B.COD_PERI = A.COD_PERI)
       AND (A.COD_PERI = ps_Cod_Peri OR (ps_Cod_Peri_Cruce IS NOT NULL AND
           A.COD_PERI = ps_Cod_Peri_Cruce));

   -- Incluimos los registros rechazados por Otros Motivos
  INSERT INTO INT_AVI_FACTU_DETAL_TEMPO
    (COD_PAIS,
     COD_PERI,
     COD_CLIE,
     COD_REGI,
     COD_ZONA,
     VAL_SALD,
     COD_MOTI_RECH,
     OID_ESTA_SOLI,
     FEC_PROG_FACT)
    SELECT A.COD_PAIS,
           A.COD_PERI,
           A.COD_CLIE,
           A.COD_REGI,
           A.COD_ZONA,
           A.VAL_SALD_DEUD,
    		   'O' AS RECHAZO,
           3, -- Usamos el OID de los rechazados por deuda
           A.FEC_SOLI
      FROM INT_SOLIC_CONSO_CABEC A
     WHERE (A.IND_ERRO_DEUD <> '2')
       AND (A.IND_ERRO_MTMI <> '1')
       AND (A.IND_ERRO_MTMA <> '1')
       AND (A.IND_PROC_GP2 <> 1)
       AND (A.FEC_SOLI < SYSDATE)
       AND NOT EXISTS
     (SELECT B.COD_PERI
              FROM INT_AVI_FACTU_DETAL_TEMPO B
             WHERE B.COD_PAIS = A.COD_PAIS
               AND B.COD_CLIE = A.COD_CLIE
               AND B.COD_PERI = A.COD_PERI)
       AND (A.COD_PERI = ps_Cod_Peri OR (ps_Cod_Peri_Cruce IS NOT NULL AND
           A.COD_PERI = ps_Cod_Peri_Cruce));

END AVI_PR_CARGA_FACTU_DETAL;

/**************************************************************************
Descripcion       : Carga la informacion de los puntajes de consultoras
                    requeridos por la interface Asistente Virtual
Fecha Creacion    : 05/08/2008
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoCruce  :  Codigo de periodo cruce
  psEnviarNovedades     : Enviar Novedades (S, N: Completo)

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE AVI_PR_INTER_CONSU_PUNTA (psCodigoPais            VARCHAR2,
                                    psCodigoSistema         VARCHAR2,
                                    psCodigoInterfaz        VARCHAR2,
                                    psNombreArchivo         VARCHAR2,
                                    psCodigoPeriodo         VARCHAR2,
                                    psCodigoPeriodoCruce    VARCHAR2,
                                    psEnviarNovedades       VARCHAR2)
IS
  CURSOR c_interfaz IS
    SELECT COD_PAIS,
           COD_CLIE,
           COD_PERI_INIC,
           COD_PERI_FINA,
           NUM_CONC,
           DECODE(COD_TIPO_PUNT,'V','V-Ventas','C','C-Constancia','P','P-Pedido'),
           COD_ESTA_CONC,
           NUM_PUNT
    FROM   AVI_HISTO_CONSU_PUNTA
    WHERE  COD_PAIS = psCodigoPais
      AND  IND_ENVI = '1'
     ORDER BY COD_CLIE,
            COD_PERI_INIC,
            COD_PERI_FINA,
            NUM_CONC,
            COD_ESTA_CONC,
            COD_TIPO_PUNT;

  CURSOR c_interfazPuntaje(oidPais NUMBER, periodo VARCHAR2) IS
    SELECT psCodigoPais,
           CLIEN.COD_CLIE,
           PERD.COD_PERI,
           PERH.COD_PERI,
           CPG.NUM_CONC,
           CPG.TIPO,
           DECODE (IVC.SITUACION,  'V','P', DECODE (IVF.CLIE_OID_CLIE, NULL, 'N', 'G') ),
           SUM (CCP.NUM_PUNT)
    FROM (SELECT IVD.NUM_CONC,
                 IVD.COPA_OID_PARA_GRAL,
                 DECODE(IVD.VICO_OID_VIGE_CONC, 1, 'V', 'T') SITUACION
            FROM INC_VERSI_CONCU IVD
           WHERE IVD.VICO_OID_VIGE_CONC IN (1, 6)) IVC,
         (SELECT CPG1.OID_PARA_GRAL,
                 CPG1.NUM_CONC,
                 CPG1.PERD_OID_PERI_HAST,
                 CPG1.PERD_OID_PERI_DESD,
                 'V' TIPO
            FROM INC_CONCU_PARAM_GENER CPG1
           WHERE CPG1.COIV_OID_CONC_IVR = 1
             AND CPG1.PAIS_OID_PAIS = oidPais --OIDPAIS
          UNION
          SELECT CPG1.OID_PARA_GRAL,
                 CPG1.NUM_CONC,
                 CPG1.PERD_OID_PERI_HAST,
                 CPG1.PERD_OID_PERI_DESD,
                 DECODE(CPG1.COIV_OID_CONC_IVR, 2, 'C', 'P') TIPO
            FROM INC_CONCU_PARAM_GENER CPG1
           WHERE (CPG1.COIV_OID_CONC_IVR = 2 OR
                 CPG1.COIV_OID_CONC_IVR = 3)
             AND CPG1.PAIS_OID_PAIS = oidPais /*OIDPAIS*/
          ) CPG,
         INC_CUENT_CORRI_PUNTO CCP,
         MAE_CLIEN CLIEN,
         (SELECT GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL
            FROM INC_PARAM_GENER_PREMI PGP,
                 INC_PARAM_NIVEL_PREMI PNP,
                 INC_GANAD             GAN
           WHERE GAN.PANP_OID_PARA_NIVE_PREM = PNP.OID_PARA_NIVE_PREM
             AND PNP.PAGP_OID_PARA_GENE_PREM = PGP.OID_PARA_GENE_PREM
           GROUP BY GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL) IVF,
         (SELECT C.OID_PERI,
                 A.COD_PERI,
                 (SELECT COUNT(1)
                    FROM SEG_PERIO_CORPO B
                   WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
            FROM SEG_PERIO_CORPO A, CRA_PERIO C
           WHERE A.OID_PERI = C.PERI_OID_PERI
           ORDER BY A.COD_PERI) PERD,
         (SELECT C.OID_PERI,
                 A.COD_PERI,
                 (SELECT COUNT(1)
                    FROM SEG_PERIO_CORPO B
                   WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
            FROM SEG_PERIO_CORPO A, CRA_PERIO C
           WHERE A.OID_PERI = C.PERI_OID_PERI
           ORDER BY A.COD_PERI) PERH
   WHERE IVC.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
     AND CPG.OID_PARA_GRAL = CCP.COPA_OID_PARA_GRAL
     AND CCP.COPA_OID_PARA_GRAL = IVF.COPA_OID_PARA_GRAL(+)
     AND CCP.CLIE_OID_CLIE = CLIEN.OID_CLIE
     AND CCP.CLIE_OID_CLIE = IVF.CLIE_OID_CLIE(+)
     AND NOT (CCP.TMOV_OID_TIPO_MOVI = 2 AND CCP.VAL_DESC = 'Entrega de Premio')
     AND CCP.TMOV_OID_TIPO_MOVI <> 3
     AND CPG.PERD_OID_PERI_DESD = PERD.OID_PERI
     AND CPG.PERD_OID_PERI_HAST = PERH.OID_PERI
     AND (   (    IVC.SITUACION = 'T'
              AND ((SELECT COUNT (1)
                      FROM SEG_PERIO_CORPO B
                     WHERE B.COD_PERI <= periodo) - PERH.CONTA BETWEEN 1 AND 5)
             )
          OR (    IVC.SITUACION = 'V'
              /*AND PERH.CONTA >= (SELECT COUNT (*)
                                   FROM SEG_PERIO_CORPO B
                                  WHERE B.COD_PERI <= periodo)*/
              AND PERD.CONTA <= (SELECT COUNT (*)
                                   FROM SEG_PERIO_CORPO B
                                  WHERE B.COD_PERI <= periodo)
             )
         )
   GROUP BY CLIEN.COD_CLIE,
            CCP.CLIE_OID_CLIE,
            IVF.CLIE_OID_CLIE,
            PERH.COD_PERI,
            PERD.COD_PERI,
            CPG.NUM_CONC,
            CPG.OID_PARA_GRAL,
            IVC.SITUACION,
            CPG.TIPO;

  TYPE interfazRec IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    codigoCliente     MAE_CLIEN.COD_CLIE%TYPE,
    periodoInicio     SEG_PERIO_CORPO.COD_PERI%TYPE,
    periodoFin        SEG_PERIO_CORPO.COD_PERI%TYPE,
    codigoConcurso    INC_CONCU_PARAM_GENER.NUM_CONC%TYPE,
    tipoPuntaje       VARCHAR2(15),
    estado            VARCHAR2(1),
    puntos            NUMBER(11)
  );

  rRegistro           AVI_HISTO_CONSU_PUNTA%ROWTYPE;
  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);

  /* Variables de parametros */
  lnIdPais            NUMBER;
  lsPeriodo           VARCHAR2(6);
  lbAbrirUtlFile      BOOLEAN;
BEGIN

  /* obteniendo id Pais */
  lnIdPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);-- id del pais consultante

  /*Obteniendo el periodo a Evaluar*/
  IF (psCodigoPeriodoCruce IS NOT NULL) THEN
    lsPeriodo := psCodigoPeriodoCruce;
  ELSE
    lsPeriodo := psCodigoPeriodo;
  END IF;

  --ENVIAR COMPLETO
  IF(psEnviarNovedades = 'N') THEN
    EXECUTE IMMEDIATE 'TRUNCATE TABLE AVI_HISTO_CONSU_PUNTA';

    INSERT INTO AVI_HISTO_CONSU_PUNTA
      (COD_PAIS,
       COD_CLIE,
       COD_PERI_INIC,
       COD_PERI_FINA,
       NUM_CONC,
       COD_TIPO_PUNT,
       COD_ESTA_CONC,
       NUM_PUNT,
       IND_ENVI,
       FEC_ULTI_ENVI)
      SELECT psCodigoPais,
             CLIEN.COD_CLIE,
             PERD.COD_PERI,
             PERH.COD_PERI,
             CPG.NUM_CONC,
             CPG.TIPO,
             DECODE (IVC.SITUACION,  'V','P', DECODE (IVF.CLIE_OID_CLIE, NULL, 'N', 'G') ),
             SUM (CCP.NUM_PUNT),
             '1',
             TRUNC(SYSDATE)
      FROM (SELECT IVD.NUM_CONC,
                   IVD.COPA_OID_PARA_GRAL,
                   DECODE(IVD.VICO_OID_VIGE_CONC, 1, 'V', 'T') SITUACION
              FROM INC_VERSI_CONCU IVD
             WHERE IVD.VICO_OID_VIGE_CONC IN (1, 6)) IVC,
           (SELECT CPG1.OID_PARA_GRAL,
                   CPG1.NUM_CONC,
                   CPG1.PERD_OID_PERI_HAST,
                   CPG1.PERD_OID_PERI_DESD,
                   'V' TIPO
              FROM INC_CONCU_PARAM_GENER CPG1
             WHERE CPG1.COIV_OID_CONC_IVR = 1
               AND CPG1.PAIS_OID_PAIS = lnIdPais --OIDPAIS
            UNION
            SELECT CPG1.OID_PARA_GRAL,
                   CPG1.NUM_CONC,
                   CPG1.PERD_OID_PERI_HAST,
                   CPG1.PERD_OID_PERI_DESD,
                   DECODE(CPG1.COIV_OID_CONC_IVR, 2, 'C', 'P') TIPO
              FROM INC_CONCU_PARAM_GENER CPG1
             WHERE (CPG1.COIV_OID_CONC_IVR = 2 OR
                   CPG1.COIV_OID_CONC_IVR = 3)
               AND CPG1.PAIS_OID_PAIS = lnIdPais /*OIDPAIS*/
            ) CPG,
           INC_CUENT_CORRI_PUNTO CCP,
           MAE_CLIEN CLIEN,
           (SELECT GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL
              FROM INC_PARAM_GENER_PREMI PGP,
                   INC_PARAM_NIVEL_PREMI PNP,
                   INC_GANAD             GAN
             WHERE GAN.PANP_OID_PARA_NIVE_PREM = PNP.OID_PARA_NIVE_PREM
               AND PNP.PAGP_OID_PARA_GENE_PREM = PGP.OID_PARA_GENE_PREM
             GROUP BY GAN.CLIE_OID_CLIE, PGP.COPA_OID_PARA_GRAL) IVF,
           (SELECT C.OID_PERI,
                   A.COD_PERI,
                   (SELECT COUNT(1)
                      FROM SEG_PERIO_CORPO B
                     WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
              FROM SEG_PERIO_CORPO A, CRA_PERIO C
             WHERE A.OID_PERI = C.PERI_OID_PERI
             ORDER BY A.COD_PERI) PERD,
           (SELECT C.OID_PERI,
                   A.COD_PERI,
                   (SELECT COUNT(1)
                      FROM SEG_PERIO_CORPO B
                     WHERE B.COD_PERI <= A.COD_PERI) AS CONTA
              FROM SEG_PERIO_CORPO A, CRA_PERIO C
             WHERE A.OID_PERI = C.PERI_OID_PERI
             ORDER BY A.COD_PERI) PERH
     WHERE IVC.COPA_OID_PARA_GRAL = CPG.OID_PARA_GRAL
       AND CPG.OID_PARA_GRAL = CCP.COPA_OID_PARA_GRAL
       AND CCP.COPA_OID_PARA_GRAL = IVF.COPA_OID_PARA_GRAL(+)
       AND CCP.CLIE_OID_CLIE = CLIEN.OID_CLIE
       AND CCP.CLIE_OID_CLIE = IVF.CLIE_OID_CLIE(+)
       AND NOT (CCP.TMOV_OID_TIPO_MOVI = 2 AND CCP.VAL_DESC = 'Entrega de Premio')
       AND CCP.TMOV_OID_TIPO_MOVI <> 3
       AND CPG.PERD_OID_PERI_DESD = PERD.OID_PERI
       AND CPG.PERD_OID_PERI_HAST = PERH.OID_PERI
       AND (   (    IVC.SITUACION = 'T'
                AND ((SELECT COUNT (1)
                        FROM SEG_PERIO_CORPO B
                       WHERE B.COD_PERI <= lsPeriodo) - PERH.CONTA BETWEEN 1 AND 5)
               )
            OR (    IVC.SITUACION = 'V'
                /*AND PERH.CONTA >= (SELECT COUNT (*)
                                     FROM SEG_PERIO_CORPO B
                                    WHERE B.COD_PERI <= lsPeriodo)*/
                AND PERD.CONTA <= (SELECT COUNT (1)
                                     FROM SEG_PERIO_CORPO B
                                    WHERE B.COD_PERI <= lsPeriodo)
               )
           )
     GROUP BY CLIEN.COD_CLIE,
              CCP.CLIE_OID_CLIE,
              IVF.CLIE_OID_CLIE,
              PERH.COD_PERI,
              PERD.COD_PERI,
              CPG.NUM_CONC,
              CPG.OID_PARA_GRAL,
              IVC.SITUACION,
              CPG.TIPO;
  ELSE
    --SOLO EN CASO DE ENVIAR NOVEDADES
    UPDATE AVI_HISTO_CONSU_PUNTA
       SET IND_ENVI = '0';

    OPEN c_interfazPuntaje(lnIdPais, lsPeriodo);
    LOOP
       FETCH c_interfazPuntaje BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
         IF interfazRecord.COUNT > 0 THEN
            FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP

              BEGIN
                SELECT *
                 INTO  rRegistro
                 FROM  AVI_HISTO_CONSU_PUNTA
                WHERE COD_PAIS = interfazRecord(x).codigoPais
                  AND COD_CLIE = interfazRecord(x).codigoCliente
                  AND NUM_CONC = interfazRecord(x).codigoConcurso;

                IF((rRegistro.COD_PERI_INIC <> interfazRecord(x).periodoInicio) OR
                   (rRegistro.COD_PERI_FINA <> interfazRecord(x).periodoFin) OR
                   (rRegistro.COD_TIPO_PUNT <> interfazRecord(x).tipoPuntaje) OR
                   (rRegistro.COD_ESTA_CONC <> interfazRecord(x).estado) OR
                   (rRegistro.NUM_PUNT <> interfazRecord(x).puntos)) THEN

                  UPDATE AVI_HISTO_CONSU_PUNTA
                     SET IND_ENVI = '1',
                         FEC_ULTI_ENVI = TRUNC(SYSDATE),
                         COD_PERI_INIC = interfazRecord(x).periodoInicio,
                         COD_PERI_FINA = interfazRecord(x).periodoFin,
                         COD_TIPO_PUNT = interfazRecord(x).tipoPuntaje,
                         COD_ESTA_CONC = interfazRecord(x).estado,
                         NUM_PUNT = interfazRecord(x).puntos
                   WHERE COD_PAIS = rRegistro.COD_PAIS
                     AND COD_CLIE = rRegistro.COD_CLIE
                     AND NUM_CONC = rRegistro.NUM_CONC;
                END IF;

              EXCEPTION
               WHEN OTHERS THEN
                  INSERT INTO AVI_HISTO_CONSU_PUNTA
                    (COD_PAIS,
                     COD_CLIE,
                     NUM_CONC,
                     COD_PERI_INIC,
                     COD_PERI_FINA,
                     COD_TIPO_PUNT,
                     COD_ESTA_CONC,
                     NUM_PUNT,
                     IND_ENVI,
                     FEC_ULTI_ENVI)
                  VALUES
                     (interfazRecord(x).codigoPais,
                      interfazRecord(x).codigoCliente,
                      interfazRecord(x).codigoConcurso,
                      interfazRecord(x).periodoInicio,
                      interfazRecord(x).periodoFin,
                      interfazRecord(x).tipoPuntaje,
                      interfazRecord(x).estado,
                      interfazRecord(x).puntos,
                      '1',
                      TRUNC(SYSDATE));
              END;

            END LOOP;
         END IF;

       EXIT WHEN c_interfazPuntaje%NOTFOUND;
    END LOOP;
  END IF;

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
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).codigoCliente         ||';'||
                        interfazRecord(x).periodoInicio         ||';'||
                        interfazRecord(x).periodoFin            ||';'||
                        interfazRecord(x).codigoConcurso        ||';'||
                        interfazRecord(x).tipoPuntaje           ||';'||
                        interfazRecord(x).estado                ||';'||
                        interfazRecord(x).puntos;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

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
    RAISE_APPLICATION_ERROR(-20123, 'ERROR AVI_PR_INTER_CONSU_PUNTA: '||ls_sqlerrm);

END AVI_PR_INTER_CONSU_PUNTA;

/**************************************************************************
Descripcion       : Carga la informacion de los Detalles CDR de las consultoras
                    requeridos por la interface Asistente Virtual
Fecha Creacion    : 07/08/2008
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoCruce  :  Codigo de periodo cruce

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE AVI_PR_INTER_CONSU_DECDR (psCodigoPais            VARCHAR2,
                                    psCodigoSistema         VARCHAR2,
                                    psCodigoInterfaz        VARCHAR2,
                                    psNombreArchivo         VARCHAR2,
                                    psCodigoPeriodo         VARCHAR2,
                                    psCodigoPeriodoCruce    VARCHAR2)
IS
  CURSOR c_interfaz IS
     SELECT
          psCodigoPais AS CODIGO_PAIS,
          MAE_CLIEN.COD_CLIE AS CODIGO_CLIENTE,
          REC_CABEC_RECLA.NUM_RECL AS NUMERO_RECLAMO,
    			COD_VENTA.VAL_CODI_VENT AS CODIGO_VENTA,
    			(case WHEN REC_TIPO_MOVIM.COD_TIPO_MOVI = 'E' THEN REC_LINEA_OPERA_RECLA.IMP_CARG
    			      WHEN REC_TIPO_MOVIM.COD_TIPO_MOVI = 'D' THEN REC_LINEA_OPERA_RECLA.IMP_ABON
    			      ELSE 0
    		     end) AS MONTO,
          DECODE(REC_OPERA.COD_OPER,'CM','C','TM','Q','DN','D','DE','D','FM','F','FA','F','TP','P') AS CODIGO_OPERACION,
    			(CASE WHEN (REC_CABEC_RECLA.ESRE_OID_ESTA_RECL = 5) THEN 'R'
    			      WHEN (REC_TIPO_MOVIM.COD_TIPO_MOVI = 'E') THEN 'E'
    				  WHEN (REC_TIPO_MOVIM.COD_TIPO_MOVI = 'D') THEN 'B'
    			 END) AS ESTADO
    FROM REC_CABEC_RECLA,
         MAE_CLIEN,
         MAE_CLIEN_DATOS_ADICI,
         REC_OPERA_RECLA,
         REC_LINEA_OPERA_RECLA,
      	 REC_TIPO_MOVIM,
         SEG_PERIO_CORPO,
         CRA_PERIO,
         REC_OPERA,
         REC_TIPOS_OPERA,
         (SELECT Z2.PROD_OID_PROD, Z2.VAL_CODI_VENT,Z1.OID_MATR_FACT, Z2.TOFE_OID_TIPO_OFER
           FROM PRE_MATRI_FACTU Z1, PRE_OFERT_DETAL Z2, PRE_MATRI_FACTU_CABEC Z3
           WHERE z3.OID_CABE = Z1.MFCA_OID_CABE
           AND Z1.OFDE_OID_DETA_OFER = Z2.OID_DETA_OFER
          ) COD_VENTA
  	   WHERE  (REC_CABEC_RECLA.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
         AND (MAE_CLIEN_DATOS_ADICI.CLIE_OID_CLIE = MAE_CLIEN.OID_CLIE)
         AND (REC_CABEC_RECLA.OID_CABE_RECL = REC_OPERA_RECLA.CARE_OID_CABE_RECL)
         AND (REC_TIPOS_OPERA.OID_TIPO_OPER = REC_OPERA_RECLA.TIOP_OID_TIPO_OPER)
         AND (REC_TIPOS_OPERA.ROPE_OID_OPER = REC_OPERA.OID_OPER)
         AND (REC_LINEA_OPERA_RECLA.OPRE_OID_OPER_RECL = REC_OPERA_RECLA.OID_OPER_RECL)
         AND (REC_CABEC_RECLA.PERD_OID_PERI_RECL = CRA_PERIO.OID_PERI)
         AND (SEG_PERIO_CORPO.OID_PERI = CRA_PERIO.PERI_OID_PERI)
         AND (REC_CABEC_RECLA.COD_USUA_INGR != 'CALYPSO')
         AND (REC_CABEC_RECLA.COD_USUA_INGR != 'BELCENTER')
         AND (REC_OPERA.COD_OPER IN ('CM','TM','TP','DN','DE','FM','FA'))
    	   AND (REC_TIPO_MOVIM.OID_TIPO_MOVI = REC_LINEA_OPERA_RECLA.TIMO_OID_TIPO_MOVI)
         AND (MAE_CLIEN_DATOS_ADICI.ESTA_OID_ESTA_CLIE != 7)
  	     AND REC_LINEA_OPERA_RECLA.PROD_OID_PROD = COD_VENTA.PROD_OID_PROD (+)
     	   AND COD_VENTA.TOFE_OID_TIPO_OFER (+) = REC_LINEA_OPERA_RECLA.TOFE_OID_TIPO_OFER
    	   AND COD_VENTA.OID_MATR_FACT(+)  = REC_LINEA_OPERA_RECLA.MAFA_OID_MATR_FACT
         AND SEG_PERIO_CORPO.COD_PERI >= psCodigoPeriodo;

  TYPE interfazRec IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    codigoCliente     MAE_CLIEN.COD_CLIE%TYPE,
    guiaCDR           REC_CABEC_RECLA.NUM_RECL%TYPE,
    codigoVenta       VARCHAR2(6),
    monto             VARCHAR2(11),
    codigoOperacion   VARCHAR2(1),
    estado            VARCHAR2(1)
  );

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
     FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
     /* Procedimiento inicial para generar interfaz */
     IF lbAbrirUtlFile THEN
         GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
            psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
         lbAbrirUtlFile := FALSE;
     END IF;
     IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
            lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                        interfazRecord(x).codigoCliente         ||';'||
                        interfazRecord(x).guiaCDR               ||';'||
                        interfazRecord(x).codigoVenta           ||';'||
                        interfazRecord(x).monto                 ||';'||
                        interfazRecord(x).codigoOperacion       ||';'||
                        interfazRecord(x).estado;

            UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
        END LOOP;
     END IF;
     EXIT WHEN c_interfaz%NOTFOUND;
  END LOOP;
  utl_file.fclose(V_HANDLE);

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
    RAISE_APPLICATION_ERROR(-20123, 'ERROR AVI_PR_INTER_CONSU_DECDR: '||ls_sqlerrm);

END AVI_PR_INTER_CONSU_DECDR;


/**************************************************************************
Descripcion        : Verifica si una region esta cerrado para un determinado
                     periodo
Fecha Creacion     : 26/11/2008
Parametros Entrada :
           pnOidPeriodo : Oid Periodo
           pnOidRegion : Oid Region

Autor              : Sergio Apaza
***************************************************************************/
FUNCTION AVI_FN_VALID_REGIO_CERRA(pnOidPeriodo        NUMBER,
                                  pnOidRegion         NUMBER)
RETURN NUMBER IS
  lnTotal NUMBER;
BEGIN
  /* Obteniendo id de periodo */
  SELECT COUNT(OID_CTRL)
    INTO lnTotal
    FROM FAC_CONTR_CIERR CIE
   WHERE PERD_OID_PERI = pnOidPeriodo
     AND TCIE_OID_TIPO_CIER = 1
     AND ZORG_OID_REGI = pnOidRegion;

  RETURN lnTotal;

END AVI_FN_VALID_REGIO_CERRA;

/**************************************************************************
Descripcion       : Envia los Saldos de la Consultora
Fecha Creacion    : 08/04/2009
Parametros Entrada:
  psCodigoPais     :  Codigo de pais
  psCodigoSistema  :  Codigo de Sistema
  psCodigoInterfaz :  Codigo de Interfaz
  psNombreArchivo  :  nombreArchivo
  psCodigoPeriodo  :  Codigo de periodo
  psCodigoPeriodoCruce  :  Codigo de periodo cruce
  psEnviarNovedades     : Enviar Novedades (S, N: Completo)

Autor             : Sergio Apaza

***************************************************************************/
PROCEDURE AVI_PR_INTER_SALDO_CONSU (psCodigoPais            VARCHAR2,
                                    psCodigoSistema         VARCHAR2,
                                    psCodigoInterfaz        VARCHAR2,
                                    psNombreArchivo         VARCHAR2,
                                    psCodigoPeriodo         VARCHAR2,
                                    psCodigoPeriodoCruce    VARCHAR2,
                                    psEnviarNovedades       VARCHAR2)
IS
  CURSOR c_interfazCompleto(oidPais NUMBER) IS
    SELECT psCodigoPais,
           COD_CLIE,
           COD_ZONA,
           TO_CHAR(GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_VENCI(OID_CLIE, fechaVencimiento)) SALDO
    FROM
    (  SELECT distinct cl.OID_CLIE,
             cl.COD_CLIE,
             re.COD_REGI,
             zo.COD_ZONA,
             GEN_PKG_GENER.GEN_FN_OBTIE_FECHA_VENCI(psCodigoPais,'T','VD',psCodigoPeriodo,
    														 re.COD_REGI,
    														 zo.COD_ZONA,
    														 cl.OID_CLIE) fechaVencimiento
      FROM MAE_CLIEN cl,
           MAE_CLIEN_DATOS_ADICI ad,
           MAE_CLIEN_TIPO_SUBTI ct,
           MAE_TIPO_CLIEN tc,
           (SELECT * FROM MAE_ESTAT_CLIEN WHERE Cod_Esta_Clie <> '07') ec ,
           MAE_CLIEN_UNIDA_ADMIN cu,
           ZON_TERRI_ADMIN zta,
           ZON_SECCI se,
           ZON_ZONA zo,
           ZON_REGIO re
     WHERE cl.OID_CLIE = ct.CLIE_OID_CLIE
       AND cl.OID_CLIE = ad.CLIE_OID_CLIE
       AND cl.OID_CLIE = cu.CLIE_OID_CLIE
       AND cu.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
       AND zta.ZSCC_OID_SECC = se.OID_SECC
       AND se.ZZON_OID_ZONA = zo.OID_ZONA
       AND zo.ZORG_OID_REGI = re.OID_REGI
       AND ct.TICL_OID_TIPO_CLIE = tc.OID_TIPO_CLIE
       AND ad.ESTA_OID_ESTA_CLIE = ec.OID_ESTA_CLIE
       AND tc.COD_TIPO_CLIE = '02'
       AND cu.IND_ACTI = '1'
       AND cl.PAIS_OID_PAIS = oidPais
    )
    order by cod_clie;

  CURSOR c_interfazNovedades(oidPais NUMBER, fecha DATE) IS
    SELECT psCodigoPais,
           COD_CLIE,
           COD_ZONA,
           GEN_PKG_GENER.GEN_FN_CALCU_VALOR_SALDO_VENCI(oid_clie,
            GEN_PKG_GENER.GEN_FN_OBTIE_FECHA_VENCI(psCodigoPais,'T','VD',psCodigoPeriodo,
    														 COD_REGI,
    														 COD_ZONA,
    														 OID_CLIE)) saldo
    FROM
    (  SELECT distinct cl.OID_CLIE,
             cl.COD_CLIE,
             re.COD_REGI,
             zo.COD_ZONA
      FROM MAE_CLIEN cl,
           MAE_CLIEN_DATOS_ADICI ad,
           MAE_CLIEN_TIPO_SUBTI ct,
           MAE_TIPO_CLIEN tc,
           (select * from MAE_ESTAT_CLIEN where Cod_Esta_Clie <> '07') ec ,
           MAE_CLIEN_UNIDA_ADMIN cu,
           CCC_MOVIM_CUENT_CORRI cc,
           ZON_TERRI_ADMIN zta,
           ZON_SECCI se,
           ZON_ZONA zo,
           ZON_REGIO re
     WHERE cl.OID_CLIE = ct.CLIE_OID_CLIE
       AND cl.OID_CLIE = ad.CLIE_OID_CLIE
       AND cl.OID_CLIE = cu.CLIE_OID_CLIE
       AND cu.ZTAD_OID_TERR_ADMI = zta.OID_TERR_ADMI
       AND zta.ZSCC_OID_SECC = se.OID_SECC
       AND se.ZZON_OID_ZONA = zo.OID_ZONA
       AND zo.ZORG_OID_REGI = re.OID_REGI
       AND ct.TICL_OID_TIPO_CLIE = tc.OID_TIPO_CLIE
       AND ad.ESTA_OID_ESTA_CLIE = ec.OID_ESTA_CLIE
       AND cc.CLIE_OID_CLIE = cl.OID_CLIE
       AND tc.COD_TIPO_CLIE = '02'
       AND cu.IND_ACTI = '1'
       AND cl.PAIS_OID_PAIS = oidPais
       AND (cl.FEC_ULTI_ACTU >= fecha
        OR ad.FEC_ULTI_ACTU >= fecha
        OR cu.FEC_ULTI_ACTU >= fecha
        OR cc.FEC_ULTI_ACTU >= fecha
        )
    ) clientes
    ORDER BY COD_CLIE;


  TYPE interfazRec IS RECORD
  (
    codigoPais        SEG_PAIS.COD_PAIS%TYPE,
    codigoCliente     MAE_CLIEN.COD_CLIE%TYPE,
    codigoZona        ZON_ZONA.COD_ZONA%TYPE,
    saldo             VARCHAR2(15)
  );

  TYPE interfazRecTab  IS TABLE OF interfazRec ;
  interfazRecord interfazRecTab;

  /* Variables usadas para la generacion del archivo de texto */
  lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
  v_handle            UTL_FILE.FILE_TYPE;
  lsLinea             VARCHAR2(1000);
  lsNombreArchivo     VARCHAR2(50);
  lbAbrirUtlFile      BOOLEAN;
  lnIdPais            NUMBER;
  ldUltimaFecha       DATE;
BEGIN

  /* obteniendo id Pais */
  lnIdPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);-- id del pais consultante

  /* Si se va a enviar Novedades obtenemos la ultima fecha de corrida de la interfaz AVI-9*/
  IF(psEnviarNovedades = 'S') THEN
    BEGIN
      SELECT MAX(trunc(FEC_FPRO))
        INTO ldUltimaFecha
        FROM BAS_HISTO_LOTES
       WHERE PAIS_COD_PAIS = psCodigoPais
         AND SIST_COD_SIST = psCodigoSistema
         AND INTE_COD_INTE = psCodigoInterfaz
         AND IND_LOER = 'N';
    EXCEPTION
      WHEN OTHERS THEN
        ldUltimaFecha := NULL;
    END;
  END IF;

  /* Generando Archivo de Texto (Detalle) */
  lbAbrirUtlFile := TRUE;

  IF((psEnviarNovedades = 'N') OR ((psEnviarNovedades = 'S') AND (ldUltimaFecha IS NULL))) THEN
    OPEN c_interfazCompleto(lnIdPais);
    LOOP
       FETCH c_interfazCompleto BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       /* Procedimiento inicial para generar interfaz */
       IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
       END IF;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                          interfazRecord(x).codigoCliente         ||';'||
                          interfazRecord(x).codigoZona            ||';'||
                          interfazRecord(x).saldo;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfazCompleto%NOTFOUND;
    END LOOP;

  ELSE

    OPEN c_interfazNovedades(lnIdPais, ldUltimaFecha);
    LOOP
       FETCH c_interfazNovedades BULK COLLECT INTO interfazRecord LIMIT W_FILAS;
       /* Procedimiento inicial para generar interfaz */
       IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
           lbAbrirUtlFile := FALSE;
       END IF;
       IF interfazRecord.COUNT > 0 THEN
          FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
              lsLinea :=  interfazRecord(x).codigoPais            ||';'||
                          interfazRecord(x).codigoCliente         ||';'||
                          interfazRecord(x).codigoZona            ||';'||
                          interfazRecord(x).saldo;

              UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
          END LOOP;
       END IF;
       EXIT WHEN c_interfazNovedades%NOTFOUND;
    END LOOP;

  END IF;

  utl_file.fclose(V_HANDLE);

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
    RAISE_APPLICATION_ERROR(-20123, 'ERROR AVI_PR_INTER_SALDO_CONSU: '||ls_sqlerrm);

END AVI_PR_INTER_SALDO_CONSU;


/***************************************************************************
Descripcion       : Genera Interfase Consultoras Bloqueadas
Fecha Creacion    : 16/08/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
***************************************************************************/
PROCEDURE AVI_PR_INTER_CONSU_BLOQU
(  psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2
)

IS
   Cursor C_Interfaz Is
      Select    Cl.Oid_Clie,
                Cl.Cod_Clie,
                psCodigoPais as cod_pais,
                null as cod_consu,
                (
                  Nvl(Trim(Val_Nom1), ' ') || ' ' ||
                  Nvl(Trim(Val_Ape1), ' ') || ' ' ||
                  Nvl(Trim(Val_Ape2), ' ')
                ) As Nom_Consu,
                null as Cod_Bloq,
                '1' as ind_activ
      From      MAE_CLIEN CL,
                Mae_Clien_Tipo_Subti Ct,
                Mae_Clien_Datos_Adici Cad
      Where     Cl.Oid_Clie = Ct.Clie_Oid_Clie
        And     Cl.Oid_Clie = Cad.Clie_Oid_Clie
        And     Ct.Ticl_Oid_Tipo_Clie = 2
        And     Ct.Ind_Ppal = 1
        And     Cad.Ind_Acti = 1
        And     (select count(1) from Mae_Clien_Bloqu where clie_oid_clie=cl.oid_clie and fec_desb is null) > 0;

   Type Interfaz Is Record   (
      oidCliente              NUMBER,
      codigoCliente           Varchar2(15),
      codigoPais              Varchar2(3),
      codigoConsultora        Varchar2(15),
      nombreConsultora        Varchar2(100),
      codigoBloqueo           Varchar2(2),
      indicaActivacion        VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;
   v_validaIntefaz     NUMBER;

   /* Variables usadas para los subquerys */
   v_indicadorCodCon   VARCHAR2(1);
   v_numeroDocIde      VARCHAR2(30);
   v_codigoConsultora  VARCHAR2(30);
   v_codigoBloqueo     VARCHAR2(2);

BEGIN

    /* Flag para abrir utl_file */
    lbAbrirUtlFile := TRUE;

    /* Validar si es necesario generar esta interfaz para el país que está consultando */
    select    count(*)
      into    v_validaIntefaz
    from      Int_Param_Gener
    where     Pais_Cod_Pais=psCodigoPais
      And     Cod_Inte=psCodigoInterfaz
      and     ind_acti_inte=1;

    if v_validaIntefaz>0 then

      -- capturar indicador de cod. consultora
      BEGIN
        Select    Ind_Docu_Iden
          into    v_indicadorCodCon
        From      Int_Param_Gener
        Where     Pais_Cod_Pais=psCodigoPais
        And       Cod_Inte=psCodigoInterfaz;
      EXCEPTION
    	  WHEN NO_DATA_FOUND THEN
          v_indicadorCodCon:=NULL;
      END;

      /* Obteniendo lista */
      OPEN c_interfaz;
      LOOP
         FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

         /* Procedimiento inicial para generar interfaz */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

         /* Formateo filas */
         IF interfazRecord.COUNT > 0 THEN
            For X In Interfazrecord.First .. Interfazrecord.Last Loop

                -- capturar numero de doc. identidad
                BEGIN
                  Select    Num_Docu_Iden
                    into    v_numeroDocIde
                  From      Mae_Clien_Ident
                  Where     Clie_Oid_Clie=interfazRecord(x).oidCliente
                    and     val_iden_docu_prin = 1;
                EXCEPTION
              	  WHEN NO_DATA_FOUND THEN
                    v_numeroDocIde:=NULL;
                END;

                -- capturar codigo consultora
                IF v_indicadorCodCon = 0 THEN
                  v_codigoConsultora := interfazRecord(x).codigoCliente;
                ELSE
                  v_codigoConsultora := v_numeroDocIde;
                END IF;

                -- capturar codigo bloqueo
                BEGIN
                  Select    distinct cod_tipo_bloq
                    into    v_codigoBloqueo
                  From      Mae_Tipo_Bloqu
                  Where     Oid_Tipo_Bloq In (
                              Select    Tibq_Oid_Tipo_Bloq
                              From      Mae_Clien_Bloqu
                              Where     Clie_Oid_Clie = interfazRecord(x).oidCliente
                            )
                    and     num_nive_grav_bloq in (
                              Select    min(num_nive_grav_bloq)
                              From      Mae_Tipo_Bloqu
                              Where     Oid_Tipo_Bloq In (
                                          Select    Distinct Tibq_Oid_Tipo_Bloq
                                          From      Mae_Clien_Bloqu
                                          Where     Clie_Oid_Clie = interfazRecord(x).oidCliente
                                        )
                            );
                EXCEPTION
              	  WHEN NO_DATA_FOUND THEN
                    v_codigoBloqueo:=NULL;
                END;

                lsLinea :=  interfazRecord(x).codigoPais         ||';'||
                            v_codigoConsultora                   ||';'||
                            interfazRecord(x).nombreConsultora   ||';'||
                            v_codigoBloqueo                      ||';'||
                            interfazRecord(x).indicaActivacion   ;

                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

            END LOOP;
         END IF;
         EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz;

    else

        /* generar utl_file vacio */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

    end if;

    /* Cerrar utl_file */
    utl_file.fclose(V_HANDLE);
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR AVI_PR_INTER_CONSU_BLOQU: '||ls_sqlerrm);

END AVI_PR_INTER_CONSU_BLOQU;

/***************************************************************************
Descripcion       : Genera Interfase Tipos de Bloqueos
Fecha Creacion    : 16/08/2010
Autor             : Carlos Diaz Valverde
Parametros        :
            psCodigoPais     : Codigo de Pais
            psCodigoSistema  : Codigo de Sistema
            psCodigoInterfaz : Codigo de Interfaz
            psNombreArchivo  : Nombre de Archivo a generarse en el servidor
***************************************************************************/
PROCEDURE AVI_PR_INTER_TIPOS_BLOQU
(  psCodigoPais               VARCHAR2,
   psCodigoSistema            VARCHAR2,
   psCodigoInterfaz           VARCHAR2,
   psNombreArchivo            VARCHAR2
)

IS
   Cursor C_Interfaz Is
      Select    psCodigoPais,
                Bl.Cod_Tipo_Bloq,
                Gen.Val_I18n as des_bloq,
                '1' As Ind_Activ
      from      mae_tipo_bloqu bl,
                Gen_I18n_Sicc_Comun Gen
      Where     Bl.Oid_Tipo_Bloq = Gen.Val_Oid
        And     Gen.Attr_Enti='MAE_TIPO_BLOQU';

   Type Interfaz Is Record   (
      codigoPais              Varchar2(3),
      codigoBloqueo           Varchar2(2),
      descripcionBloqueo      Varchar2(100),
      indicaActivacion        VARCHAR2(1)
   );

   TYPE interfazTab  IS TABLE OF interfaz ;
   interfazRecord interfazTab;

   /* Variables usadas para la generacion del archivo de texto */
   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
   lsLinea             VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;
   v_validaIntefaz     NUMBER;

BEGIN

    /* Flag para abrir utl_file */
    lbAbrirUtlFile := TRUE;

    /* Validar si es necesario generar esta interfaz para el país que está consultando */
    select    count(*)
      into    v_validaIntefaz
    from      Int_Param_Gener
    where     Pais_Cod_Pais=psCodigoPais
      And     Cod_Inte=psCodigoInterfaz
      and     ind_acti_inte=1;

    if v_validaIntefaz>0 then

      /* Obteniendo lista */
      OPEN c_interfaz;
      LOOP
         FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

         /* Procedimiento inicial para generar interfaz */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

         /* Formateo filas */
         IF interfazRecord.COUNT > 0 THEN
            For X In Interfazrecord.First .. Interfazrecord.Last Loop
            lsLinea :=  interfazRecord(x).codigoPais         ||';'||
                        interfazrecord(X).codigoBloqueo      ||';'||
                        interfazRecord(x).descripcionBloqueo ||';'||
                        interfazRecord(x).indicaActivacion   ;
                UTL_FILE.PUT_LINE (V_HANDLE, lslinea );
            END LOOP;
         END IF;
         EXIT WHEN c_interfaz%NOTFOUND;
      END LOOP;
      CLOSE c_interfaz;

    else

        /* generar utl_file vacio */
         IF lbAbrirUtlFile THEN
           GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
              psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
             lbAbrirUtlFile := FALSE;
         END IF;

    end if;

    /* Cerrar utl_file */
    utl_file.fclose(V_HANDLE);
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
     RAISE_APPLICATION_ERROR(-20123, 'ERROR AVI_PR_INTER_TIPOS_BLOQU: '||ls_sqlerrm);

END AVI_PR_INTER_TIPOS_BLOQU;


END AVI_PKG_ASIST_VIRTU;
/

