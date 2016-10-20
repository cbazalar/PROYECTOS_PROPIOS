CREATE OR REPLACE PACKAGE "MAE_PKG_ESTAT" IS
  /* Declaracion de variables */
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=3000;

  /* Estatus de Consultora */
  ESTADO_REGISTRADA CONSTANT VARCHAR2(2) := '01';
  ESTADO_NUEVA CONSTANT VARCHAR2(2) := '02';
  ESTADO_NORMAL CONSTANT VARCHAR2(2) := '03';
  ESTADO_EGRESANTE CONSTANT VARCHAR2(2) := '04'; --POSIBLE EGRESO--
  ESTADO_EGRESADA CONSTANT VARCHAR2(2) := '05';
  ESTADO_REINGRESO CONSTANT VARCHAR2(2) := '06';
  ESTADO_REACTIVADA CONSTANT VARCHAR2(2) := '08';
  ESTADO_RETIRADA CONSTANT VARCHAR2(2) := '07';

  NUMERO_CAMPANAS_RETIRADA CONSTANT NUMBER := 18;

  PROCESO_FACTURACION CONSTANT VARCHAR2(1) := 'F';
  PROCESO_CIERRE_REGION CONSTANT VARCHAR2(1) := 'R';
  PROCESO_CIERRE_CAMPANA CONSTANT VARCHAR2(1) := 'C';


  PROCEDURE MAE_PR_CARGA_INICI_ESTAT
    (psCodigoPais      VARCHAR2);

  PROCEDURE MAE_PR_INGRE_NUEVA_CONSU
    (psCodigoPais      VARCHAR2);

  PROCEDURE MAE_PR_PROCE_FACTU_DIARI
    (psCodigoPais              VARCHAR2,
     psCodigoPeriodo           VARCHAR2,
     psFechaFacturacion        VARCHAR2
    );

  PROCEDURE MAE_PR_PROCE_ANULA_FACTU
    (psCodigoPais              VARCHAR2,
     psCodigoPeriodo           VARCHAR2,
     psTipoProceso             VARCHAR2,
     psFechaFacturacion        VARCHAR2,
     pnTotalErrores       OUT  NUMBER);

  PROCEDURE MAE_PR_PROCE_CIERR_REGIO
    (psCodigoPais              VARCHAR2,
     psCodigoPeriodo           VARCHAR2,
     psCodigoRegion            VARCHAR2,
     psFechaFacturacion        VARCHAR2,
     psCodigoUsuario           VARCHAR2
    );

  PROCEDURE MAE_PR_PROCE_CIERR_CAMPA
    (psCodigoPais              VARCHAR2,
     psCodigoPeriodo           VARCHAR2
    );

  PROCEDURE MAE_PR_ACTUA_HISTO_ESTAT
  (pnOidCliente           NUMBER,
   pnOidPeriodo           NUMBER,
   pnOidPeriodoAnterior   NUMBER,
   pnOidEstatus           NUMBER,
   psCodigoPais           VARCHAR2,
   psTipoProceso          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psFechaFacturacion     VARCHAR2,
   psCodigoCliente        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   pbSinErrores       OUT BOOLEAN);
   


END MAE_PKG_ESTAT;
/
CREATE OR REPLACE PACKAGE BODY "MAE_PKG_ESTAT" IS

/***************************************************************************
Descripcion       : Carga Inicial Calificacion Estatus
Fecha Creacion    : 21/10/2008
Fecha Modificacion: 07/05/2015
Autor Modificacion: CSVD-FFVV
***************************************************************************/
PROCEDURE MAE_PR_CARGA_INICI_ESTAT
  (psCodigoPais      VARCHAR2)
IS
  lsCodigoPeriodoCerrado     VARCHAR2(6);
  lsCodigoPeriodoAnterior    VARCHAR2(6);

  lsCodigoMarca              VARCHAR2(2);
  lsCodigoCanal              VARCHAR2(2);

  lnOidPais                NUMBER(12);
  lnOidMarca               NUMBER(12);
  lnOidCanal               NUMBER(12);

  lnOidPeriodoCerradoAnt   NUMBER(12);
  lnHistoSinFinRepetidos   NUMBER(12);
  lnTotalRegistros         NUMBER(12);

  CURSOR c_consultorasRetiradas IS
    SELECT est.PAIS_COD_PAIS, est.COD_CLIE, adi.NUM_CAMP_SIN_PEDI
      FROM MAE_CLIEN_ESTAT_ACTUA est, MAE_CLIEN_DATOS_ADICI adi
     WHERE est.CAM_ULTI_PEDI IS NULL
       AND est.COD_ESTA_ACTU = '07'
       AND est.CLIE_OID_CLIE = adi.CLIE_OID_CLIE;

    TYPE interfazConsultorasRet IS RECORD
    (
     codPais                MAE_CLIEN_ESTAT_ACTUA.PAIS_COD_PAIS%TYPE,
     codCliente             MAE_CLIEN_ESTAT_ACTUA.COD_CLIE%TYPE,
     numCampanaSinPedido    MAE_CLIEN_DATOS_ADICI.NUM_CAMP_SIN_PEDI%TYPE
    );

    TYPE interfazConsultorasRetTab  IS TABLE OF interfazConsultorasRet;
    interfazRecordRet     interfazConsultorasRetTab;


  CURSOR c_consultorasEgresadas IS
    SELECT est.pais_cod_pais,
           est.cod_clie,
           adi.num_camp_sin_pedi
      FROM mae_clien_estat_actua est,
           mae_clien_datos_adici adi
     WHERE est.cod_esta_actu = '05'
       AND est.clie_oid_clie = adi.clie_oid_clie;

    TYPE interfazConsultorasEgre IS RECORD
    (
     codPais                mae_clien_estat_actua.pais_cod_pais%TYPE,
     codcliente             mae_clien_estat_actua.cod_clie%TYPE,
     numcampanasinpedido    mae_clien_datos_adici.num_camp_sin_pedi%TYPE
    );

    TYPE interfazConsultorasEgreTab  IS TABLE OF interfazConsultorasEgre;
    interfazRecordEgre     interfazConsultorasEgreTab;

--Inicio cambio para optimizar la consulta PER-SiCC-2015-0231
/*CURSOR c_universoConsultorasRetiradas(p_codigoPeriodoCerrado VARCHAR2,
                                      p_codigoPeriodoAnterior VARCHAR2,
                                      p_oidPais NUMBER) IS
  SELECT  psCodigoPais, cli.OID_CLIE, cli.COD_CLIE,
         (SELECT MAX(cor.COD_PERI)
            FROM PED_SOLIC_CABEC_ACUM2 acu, CRA_PERIO cra, SEG_PERIO_CORPO cor
           WHERE acu.CLIE_OID_CLIE = cli.OID_CLIE
             AND acu.PERD_OID_PERI = cra.OID_PERI
             AND cra.PERI_OID_PERI = cor.OID_PERI) PERI_ULTI_PEDI,
       p_codigoPeriodoCerrado, esc.COD_ESTA_CLIE,
       p_codigoPeriodoAnterior, tmec.COD_ESTA_CLIE esta_anterior,  'ADMIN', SYSDATE
    FROM MAE_CLIEN cli, MAE_CLIEN_DATOS_ADICI adi, MAE_ESTAT_CLIEN esc,
         TMP_MAE_ESTA_CLIE tmec, TMP_MAE_CLIE_TISU tmct
   WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
     AND adi.ESTA_OID_ESTA_CLIE = esc.OID_ESTA_CLIE
     AND tmct.CLIE_OID_CLIE = cli.OID_CLIE
     AND cli.PAIS_OID_PAIS = p_oidPais
     AND tmec.CLIE_OID_CLIE = cli.OID_CLIE;

  TYPE interfazUniversoConsultorasRet IS RECORD (
   codPais                MAE_CLIEN_ESTAT_ACTUA.PAIS_COD_PAIS%TYPE,
   oidCliente             MAE_CLIEN.OID_CLIE%TYPE,
   codCliente             MAE_CLIEN.COD_CLIE%TYPE,
   ultPeriodo             SEG_PERIO_CORPO.COD_PERI%TYPE,
   perCerrado             SEG_PERIO_CORPO.COD_PERI%TYPE,
   codEstaClie            MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
   perAnterior            SEG_PERIO_CORPO.COD_PERI%TYPE,
   estaAnterior           MAE_ESTAT_CLIEN.COD_ESTA_CLIE%TYPE,
   usuRegistro            MAE_CLIEN_ESTAT_ACTUA.USU_CREA%TYPE,
   fecRegistro            MAE_CLIEN_ESTAT_ACTUA.FEC_CREA%TYPE
  );

  TYPE interfazUnivConsultorasRetTab  IS TABLE OF interfazUniversoConsultorasRet;
  interfazRecordUniversoRet     interfazUnivConsultorasRetTab;
*/
--Fin cambio para optimizar la consulta PER-SiCC-2015-0231

BEGIN

  --RECUPERAMOS EL OID PAIS, MARCA Y CANAL
  lsCodigoMarca := 'T';
  lsCodigoCanal := 'VD';
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA(lsCodigoMarca);
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL(lsCodigoCanal);

  SELECT COUNT(*)
  INTO   lnHistoSinFinRepetidos
  FROM  (SELECT cli.OID_CLIE, COUNT(*)
           FROM MAE_CLIEN_HISTO_ESTAT his, MAE_CLIEN cli,
                (SELECT DISTINCT CTS.CLIE_OID_CLIE
            		  FROM  MAE_CLIEN_TIPO_SUBTI CTS,
            		        (SELECT SUB.OID_SUBT_CLIE
            	 		       FROM MAE_PARAM_ESTAT_TISUB ING, MAE_TIPO_CLIEN TIP, MAE_SUBTI_CLIEN SUB
              				  WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
              				    AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
              				    AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
              				    AND ING.PAIS_COD_PAIS = psCodigoPais
                          AND ING.IND_ACTI = '1'
                          AND ING.EST_REGI = '1') ETC
                 WHERE CTS.SBTI_OID_SUBT_CLIE = ETC.OID_SUBT_CLIE) ets
           WHERE cli.OID_CLIE = ets.CLIE_OID_CLIE
             AND cli.OID_CLIE = his.CLIE_OID_CLIE
             AND cli.PAIS_OID_PAIS = lnOidPais
             AND his.PERD_OID_PERI_PERI_FIN IS NULL
           GROUP BY cli.OID_CLIE
          HAVING COUNT(*)>1);

  IF(lnHistoSinFinRepetidos > 0) THEN
    RAISE_APPLICATION_ERROR(-20123, 'HAY CLIENTES QUE TIENEN 2 o MAS REGISTROS (HISTORICO ESTATUS) CON PERIODO FIN NUL0, DEBE CORREGIRLO.');
  END IF;

  --UBICAMOS EL ULTIMO PERIODO CERRADO
 SELECT MAX(CAM_PROC)
    INTO lsCodigoPeriodoCerrado
    FROM FAC_PROGR_CIERR
  WHERE TIP_CIER='C'
      AND EST_CIER='P'
      AND EST_REGI=1;

  --EN CASO QUE NO ENCONTREMOS PERIODO CERRADO, SE LANZARA UNA EXCEPCION
  IF(lsCodigoPeriodoCerrado IS NULL) THEN
    RAISE_APPLICATION_ERROR(-20123, 'NO SE PUDO RECUPERAR EL ULTIMO PERIODO CERRADO');
  END IF;

  lsCodigoPeriodoAnterior := GEN_FN_PERIO_ATRAS(lsCodigoPeriodoCerrado, 1);
  lnOidPeriodoCerradoAnt := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO(lsCodigoPeriodoAnterior, lnOidMarca, lnOidCanal, TRUE);

  --BORRAMOS LOS VALORES DE LA TABLA MAE_CLIEN_ESTAT_ACTUA PARA EL PAIS SELECCIONADO
  EXECUTE IMMEDIATE 'TRUNCATE TABLE MAE_CLIEN_ESTAT_ACTUA';

  --BORRAMOS LOS VALORES DE LA TABLA MAE_TMP_ESTAT_ERROR PARA EL PAIS SELECCIONADO
  EXECUTE IMMEDIATE 'TRUNCATE TABLE MAE_TMP_ESTAT_ERROR';

  --SI NO ENCONTRAMOS EL PERIODO ANTERIOR, SERA GUARDADO COMO NULO
  IF(lnOidPeriodoCerradoAnt = -1) THEN
    lsCodigoPeriodoAnterior := NULL;
  END IF;

  --Inicio cambio para optimizar la consulta PER-SiCC-2015-0231
  EXECUTE IMMEDIATE 'ALTER SESSION ENABLE PARALLEL DML';
  EXECUTE IMMEDIATE 'TRUNCATE TABLE TMP_MAE_ESTA_CLIE';

  INSERT  INTO TMP_MAE_ESTA_CLIE
  SELECT his.CLIE_OID_CLIE,est.COD_ESTA_CLIE
           FROM  MAE_CLIEN_HISTO_ESTAT his,
                 MAE_ESTAT_CLIEN est,
                 (SELECT c1.OID_PERI, s1.COD_PERI
                     FROM CRA_PERIO c1, SEG_PERIO_CORPO s1
                   WHERE c1.PERI_OID_PERI = s1.OID_PERI) ri,
                 (SELECT c2.OID_PERI, s2.COD_PERI
                    FROM CRA_PERIO c2, SEG_PERIO_CORPO s2
                   WHERE c2.PERI_OID_PERI = s2.OID_PERI) rf
           WHERE --his.CLIE_OID_CLIE = cli.OID_CLIE AND
              his.ESTA_OID_ESTA_CLIE = est.OID_ESTA_CLIE
             AND his.PERD_OID_PERI = ri.OID_PERI
             AND his.PERD_OID_PERI_PERI_FIN = rf.OID_PERI (+)
             AND ri.COD_PERI <= lsCodigoPeriodoAnterior
             AND ((his.PERD_OID_PERI_PERI_FIN IS NULL)  OR
                  (lsCodigoPeriodoAnterior <= rf.COD_PERI));

  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'TMP_MAE_ESTA_CLIE', CASCADE => TRUE );

  EXECUTE IMMEDIATE 'TRUNCATE TABLE TMP_MAE_CLIE_TISU';

  INSERT  INTO TMP_MAE_CLIE_TISU
  SELECT DISTINCT CTS.CLIE_OID_CLIE
                FROM  MAE_CLIEN_TIPO_SUBTI CTS,
                  MAE_PARAM_ESTAT_TISUB ING,
                  MAE_TIPO_CLIEN TIP,
                  MAE_SUBTI_CLIEN SUB
            WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
                       AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
                       AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
                       AND ING.PAIS_COD_PAIS = psCodigoPais
              AND ING.IND_ACTI = '1'
              AND ING.EST_REGI = '1'
              AND CTS.SBTI_OID_SUBT_CLIE = SUB.OID_SUBT_CLIE;

  DBMS_STATS.GATHER_TABLE_STATS( OWNNAME => USER, TABNAME => 'TMP_MAE_CLIE_TISU', CASCADE => TRUE );

 /* OPEN c_universoConsultorasRetiradas(lsCodigoPeriodoCerrado, lsCodigoPeriodoAnterior, lnOidPais);
  LOOP
    FETCH c_universoConsultorasRetiradas BULK COLLECT INTO interfazRecordUniversoRet LIMIT W_FILAS;
    IF interfazRecordUniversoRet.COUNT > 0 THEN
       FOR x IN interfazRecordUniversoRet.FIRST .. interfazRecordUniversoRet.LAST LOOP

  INSERT INTO MAE_CLIEN_ESTAT_ACTUA
    (PAIS_COD_PAIS, CLIE_OID_CLIE, COD_CLIE,
     CAM_ULTI_PEDI, CAM_EVAL_ACTU, COD_ESTA_ACTU,
     CAM_EVAL_ANTE, COD_ESTA_ANTE, USU_CREA, FEC_CREA)
              VALUES
                 (interfazRecordUniversoRet(x).codPais, interfazRecordUniversoRet(x).oidCliente, interfazRecordUniversoRet(x).codCliente,
                  interfazRecordUniversoRet(x).ultPeriodo, interfazRecordUniversoRet(x).perCerrado, interfazRecordUniversoRet(x).codEstaClie,
                  interfazRecordUniversoRet(x).perAnterior, interfazRecordUniversoRet(x).estaAnterior, interfazRecordUniversoRet(x).usuRegistro, interfazRecordUniversoRet(x).fecRegistro);

       END LOOP;
    END IF;
    EXIT WHEN c_universoConsultorasRetiradas%NOTFOUND;
  END LOOP;
  CLOSE c_universoConsultorasRetiradas;
*/

  INSERT /*+ PARALLEL(MAE_CLIEN_ESTAT_ACTUA,4) */ INTO mae_clien_estat_actua
    (pais_cod_pais, clie_oid_clie, cod_clie,
     cam_ulti_pedi, cam_eval_actu, cod_esta_actu,
     cam_eval_ante, cod_esta_ante, usu_crea, fec_crea)
  SELECT /*+ PARALLEL(cli, 4) INDEX(tmec ESCL_IDX1) INDEX(tmct CLTI_IDX1) */
         psCodigoPais,
         cli.oid_clie,
         cli.cod_clie,
         CASE
             WHEN esc.cod_esta_clie <> '01' THEN
               (
                SELECT MAX(cor.cod_peri)
                  FROM ped_solic_cabec_acum2 acu,
                       cra_perio cra,
                       seg_perio_corpo cor
                 WHERE acu.clie_oid_clie = cli.oid_clie
                   AND acu.perd_oid_peri = cra.oid_peri
                   AND cra.peri_oid_peri = cor.oid_peri
               )
             ELSE ''
         END AS peri_ulti_pedi,
         lsCodigoPeriodoCerrado,
         esc.cod_esta_clie,
         lsCodigoPeriodoAnterior,
         CASE
             WHEN ESC.COD_ESTA_CLIE <> '01' THEN
               (
                SELECT tmec.cod_esta_clie
                  FROM tmp_mae_esta_clie tmec
                 WHERE tmec.clie_oid_clie = cli.oid_clie
               )
             ELSE ''
         END AS esta_anterior,
         'ADMIN',
         SYSDATE
     FROM mae_clien cli,
          mae_clien_datos_adici adi,
          mae_estat_clien esc,
          tmp_mae_clie_tisu tmct
    WHERE cli.oid_clie = adi.clie_oid_clie
      AND adi.esta_oid_esta_clie = esc.oid_esta_clie
      AND tmct.clie_oid_clie = cli.oid_clie
      AND cli.pais_oid_pais = lnOidPais;


  /*INSERT INTO MAE_CLIEN_ESTAT_ACTUA
    (PAIS_COD_PAIS, CLIE_OID_CLIE, COD_CLIE,
     CAM_ULTI_PEDI, CAM_EVAL_ACTU, COD_ESTA_ACTU,
     CAM_EVAL_ANTE, COD_ESTA_ANTE, USU_CREA, FEC_CREA)
  SELECT psCodigoPais, cli.OID_CLIE, cli.COD_CLIE,
         (SELECT MAX(cor.COD_PERI)
            FROM PED_SOLIC_CABEC_ACUM2 acu, CRA_PERIO cra, SEG_PERIO_CORPO cor
           WHERE acu.CLIE_OID_CLIE = cli.OID_CLIE
             AND acu.PERD_OID_PERI = cra.OID_PERI
             AND cra.PERI_OID_PERI = cor.OID_PERI) PERI_ULTI_PEDI,
       lsCodigoPeriodoCerrado, esc.COD_ESTA_CLIE,
  	   lsCodigoPeriodoAnterior, (SELECT est.COD_ESTA_CLIE
                                 FROM  MAE_CLIEN_HISTO_ESTAT his,
                                       MAE_ESTAT_CLIEN est,
                                       (SELECT c1.OID_PERI, s1.COD_PERI
                                 	    	  FROM CRA_PERIO c1, SEG_PERIO_CORPO s1
                                    		 WHERE c1.PERI_OID_PERI = s1.OID_PERI) ri,
                                       (SELECT c2.OID_PERI, s2.COD_PERI
                                  	  	  FROM CRA_PERIO c2, SEG_PERIO_CORPO s2
                                    		 WHERE c2.PERI_OID_PERI = s2.OID_PERI) rf
                                 WHERE his.CLIE_OID_CLIE = cli.OID_CLIE
                                   AND his.ESTA_OID_ESTA_CLIE = est.OID_ESTA_CLIE
                                   AND his.PERD_OID_PERI = ri.OID_PERI
                                   AND his.PERD_OID_PERI_PERI_FIN = rf.OID_PERI (+)
                                   AND ri.COD_PERI <= lsCodigoPeriodoAnterior
                                   AND ((his.PERD_OID_PERI_PERI_FIN IS NULL)  OR
                                        (lsCodigoPeriodoAnterior <= rf.COD_PERI)) ) esta_anterior,  'ADMIN', SYSDATE
    FROM MAE_CLIEN cli, MAE_CLIEN_DATOS_ADICI adi, MAE_ESTAT_CLIEN esc,
         (SELECT DISTINCT CTS.CLIE_OID_CLIE
    		  FROM  MAE_CLIEN_TIPO_SUBTI CTS,
                MAE_PARAM_ESTAT_TISUB ING,
                MAE_TIPO_CLIEN TIP,
                MAE_SUBTI_CLIEN SUB
          WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
 				    AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
 				    AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
 				    AND ING.PAIS_COD_PAIS = psCodigoPais
            AND ING.IND_ACTI = '1'
            AND ING.EST_REGI = '1'
            AND CTS.SBTI_OID_SUBT_CLIE = SUB.OID_SUBT_CLIE) ets
   WHERE cli.OID_CLIE = adi.CLIE_OID_CLIE
     AND adi.ESTA_OID_ESTA_CLIE = esc.OID_ESTA_CLIE
     AND ets.CLIE_OID_CLIE = cli.OID_CLIE
     AND cli.PAIS_OID_PAIS = lnOidPais;*/
  --Fin cambio para optimizar la consulta PER-SiCC-2015-0231

  -- PROCESAMOS LAS CONSULTORAS QUE TIENEN ULTIMO PEDIDO EN NULO Y SON RETIRADAS
  OPEN c_consultorasRetiradas;
  LOOP
    FETCH c_consultorasRetiradas BULK COLLECT INTO interfazRecordRet LIMIT W_FILAS;
    IF interfazRecordRet.COUNT > 0 THEN

      FOR x IN interfazRecordRet.FIRST .. interfazRecordRet.LAST LOOP

        IF(interfazRecordRet(x).numCampanaSinPedido >= NUMERO_CAMPANAS_RETIRADA) THEN
          UPDATE MAE_CLIEN_ESTAT_ACTUA
             SET CAM_ULTI_PEDI = GEN_FN_CALCU_PERIO(lsCodigoPeriodoCerrado, interfazRecordRet(x).numCampanaSinPedido*(-1))
           WHERE COD_CLIE = interfazRecordRet(x).codCliente
             AND PAIS_COD_PAIS = psCodigoPais;

        ELSE
          UPDATE MAE_CLIEN_ESTAT_ACTUA
             SET CAM_ULTI_PEDI = GEN_FN_CALCU_PERIO(lsCodigoPeriodoCerrado, NUMERO_CAMPANAS_RETIRADA*(-1))
           WHERE COD_CLIE = interfazRecordRet(x).codCliente
             AND PAIS_COD_PAIS = psCodigoPais;

        END IF;

      END LOOP;
    END IF;
    EXIT WHEN c_consultorasRetiradas%NOTFOUND;
  END LOOP;
  CLOSE c_consultorasRetiradas;


  -- Procesamos consultoras que tienen ULTIMO PEDIDO EN NULO y son EGRESADAS
  OPEN c_consultorasEgresadas;
  LOOP
    FETCH c_consultorasEgresadas BULK COLLECT INTO interfazRecordEgre LIMIT W_FILAS;
    IF interfazRecordEgre.COUNT > 0 THEN

      FOR x IN interfazRecordEgre.FIRST .. interfazRecordEgre.LAST LOOP

        UPDATE MAE_CLIEN_ESTAT_ACTUA
           SET CAM_ULTI_PEDI = GEN_FN_CALCU_PERIO(lsCodigoPeriodoCerrado, interfazRecordEgre(x).numCampanaSinPedido*(-1))
         WHERE COD_CLIE = interfazRecordEgre(x).codCliente
           AND PAIS_COD_PAIS = psCodigoPais;

      END LOOP;
    END IF;
    EXIT WHEN c_consultorasEgresadas%NOTFOUND;
  END LOOP;
  CLOSE c_consultorasEgresadas;

  --Igualamos el campo PenultimoPedido = Ultimo Pedido
  UPDATE MAE_CLIEN_ESTAT_ACTUA
     SET CAM_PENU_PEDI = CAM_ULTI_PEDI
     where COD_ESTA_ACTU <> '01';

  --Si hay consultoras que no tienen Campana de Ultimo Pedido, se registra un error
  SELECT COUNT(1)
    INTO lnTotalRegistros
    FROM MAE_CLIEN_ESTAT_ACTUA est
   WHERE est.CAM_ULTI_PEDI IS NULL
     AND est.COD_ESTA_ACTU != '07';

  IF(lnTotalRegistros > 0) THEN
    INSERT INTO MAE_TMP_ESTAT_ERROR
      (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
       TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
    VALUES
      (psCodigoPais, NULL, lsCodigoPeriodoCerrado, NULL,
       PROCESO_CIERRE_CAMPANA, '4', '0', SYSDATE);
  END IF;


EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_CARGA_INICI_ESTAT: (' || ls_sqlerrm);
END MAE_PR_CARGA_INICI_ESTAT;

/***************************************************************************
Descripcion       : Ingreso de Nuevas Consultoras
Fecha Creacion    : 07/10/2008
Fecha Modificacion: 07/05/2015
Autor Modificacion: CSVD - FFVV
***************************************************************************/
PROCEDURE MAE_PR_INGRE_NUEVA_CONSU
  (psCodigoPais      VARCHAR2)
IS
  lnOidPais          NUMBER;
  lnOidHistoricoSig  NUMBER;
  lnOidAdiciSig      NUMBER;

BEGIN
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

  -- Insertamos Nuevas Consultoras en tabla MAE_CLIEN_ESTAT_ACTUA --
  INSERT INTO mae_clien_estat_actua
    (pais_cod_pais, clie_oid_clie, cod_clie,
     cod_esta_actu, usu_crea, fec_crea)
  SELECT psCodigoPais, cli.oid_clie, cli.cod_clie,
         est.cod_esta_clie, 'ADMIN-F_PDN', SYSDATE
    FROM mae_clien cli,
         mae_clien_datos_adici adi,
         mae_estat_clien est,
         (
          SELECT DISTINCT cts.clie_oid_clie
        		FROM mae_clien_tipo_subti cts,
        		     (
                  SELECT sub.oid_subt_clie
        	 		      FROM mae_param_estat_tisub ing,
                         mae_tipo_clien tip,
                         mae_subti_clien sub
        				   WHERE ing.cod_tipo_clie = tip.cod_tipo_clie
        				     AND sub.ticl_oid_tipo_clie = tip.oid_tipo_clie
        				     AND sub.cod_subt_clie = ing.cod_subt_clie
        				     AND ing.pais_cod_pais = psCodigoPais
                     AND ing.ind_acti = '1'
                     AND ing.est_regi = '1'
                 ) etc
           WHERE cts.sbti_oid_subt_clie = etc.oid_subt_clie
         ) ets
   WHERE cli.oid_clie = adi.clie_oid_clie
     AND ets.clie_oid_clie = cli.oid_clie
     AND adi.esta_oid_esta_clie = est.oid_esta_clie
     AND est.cod_esta_clie in ('01','05','07') ---se añade 07 para los casos de Carga Lote Bancario - Incobrables
     AND cli.pais_oid_pais = lnOidPais
     AND NOT EXISTS (
                     SELECT *
              	       FROM mae_clien_estat_actua con
              	      WHERE con.pais_cod_pais = psCodigoPais
              	        AND con.cod_clie = cli.cod_clie
                    );

  -- Obtenemos el maximo OID de tabla MAE_CLIEN_ESTAT_ACTUA_ADICI --
  SELECT MAX(oid_clie_dato_adic) + 1 INTO lnOidAdiciSig FROM mae_clien_estat_actua_adici;

  -- Insertamos Nuevas Consultoras en tabla MAE_CLIEN_ESTAT_ACTUA_ADICI --
  INSERT INTO mae_clien_estat_actua_adici
    (oid_clie_dato_adic, clie_oid_clie, num_camp_sin_pedi, ind_acti,
     esta_oid_esta_clie, usu_crea, fec_crea
    )
  SELECT lnOidAdiciSig + ROWNUM oid_clie_dato_adic,
         cli.oid_clie,
         adi.num_camp_sin_pedi,
         adi.ind_acti,
         adi.esta_oid_esta_clie,
         'ADMIN-F_PDN',
         SYSDATE
    FROM mae_clien cli,
         mae_clien_datos_adici adi,
         mae_estat_clien est,
         (
          SELECT DISTINCT cts.clie_oid_clie
        		FROM mae_clien_tipo_subti cts,
        		     (
                  SELECT sub.oid_subt_clie
        	 		      FROM mae_param_estat_tisub ing,
                         mae_tipo_clien tip,
                         mae_subti_clien sub
        				   WHERE ing.cod_tipo_clie = tip.cod_tipo_clie
        				     AND sub.ticl_oid_tipo_clie = tip.oid_tipo_clie
        				     AND sub.cod_subt_clie = ing.cod_subt_clie
        				     AND ing.pais_cod_pais = psCodigoPais
                     AND ing.ind_acti = '1'
                     AND ing.est_regi = '1'
                 ) etc
           WHERE cts.sbti_oid_subt_clie = etc.oid_subt_clie
          ) ets
  WHERE cli.oid_clie = adi.clie_oid_clie
    AND ets.clie_oid_clie = cli.oid_clie
    AND adi.esta_oid_esta_clie = est.oid_esta_clie
     AND est.cod_esta_clie in ( '01','07') ---se añade 07 para los casos de Carga Lote Bancario - Incobrables
    AND cli.pais_oid_pais = lnOidPais
    AND NOT EXISTS (
                    SELECT *
                        FROM mae_clien_estat_actua_adici cad
                       WHERE cad.clie_oid_clie = cli.oid_clie
                   );


  -- Obtenemos el maximo OID de tabla MAE_CLIEN_ESTAT_ACTUA_HISTO --
  SELECT MAX(oid_hist_esta) + 1 INTO lnOidHistoricoSig FROM mae_clien_estat_actua_histo;

  -- Insertamos Nuevas Conusltoras en MAE_CLIEN_ESTAT_ACTUA_HISTO --
  INSERT INTO mae_clien_estat_actua_histo
   (
    oid_hist_esta, clie_oid_clie, perd_oid_peri, perd_oid_peri_peri_fin, esta_oid_esta_clie,
    usu_crea, fec_crea
   )
   SELECT lnOidHistoricoSig + ROWNUM oid_hist_esta,
          cli.oid_clie,
          his.perd_oid_peri,
          his.perd_oid_peri_peri_fin,
          his.esta_oid_esta_clie,
          'ADMIN-F_PDN', SYSDATE
     FROM mae_clien cli,
          mae_clien_histo_estat his,
          (
           SELECT DISTINCT cts.clie_oid_clie
             FROM mae_clien_tipo_subti cts,
                  (
                   SELECT sub.oid_subt_clie
                     FROM mae_param_estat_tisub ing,
                          mae_tipo_clien tip,
                          mae_subti_clien sub
                     WHERE ing.cod_tipo_clie = tip.cod_tipo_clie
                       AND sub.ticl_oid_tipo_clie = tip.oid_tipo_clie
                       AND sub.cod_subt_clie = ing.cod_subt_clie
                       AND ing.pais_cod_pais = psCodigoPais
                       AND ing.ind_acti = '1'
                       AND ing.est_regi = '1'
                  ) etc
            WHERE cts.sbti_oid_subt_clie = etc.oid_subt_clie
          ) ets
    WHERE cli.oid_clie = his.clie_oid_clie
      AND ets.clie_oid_clie = cli.oid_clie
      AND cli.pais_oid_pais = lnOidPais
      AND NOT EXISTS (
                      SELECT *
                        FROM mae_clien_estat_actua_histo che
                       WHERE che.clie_oid_clie = cli.oid_clie
                   );
END;

/***********************************************************************************
Descripcion       : Proceso de Facturacion Diaria para Evaluacion de Estatus
                    de las Consultoras que han pasado pedido, asi como de las
                    consultoras que no han pasado pedido de las regiones facturadas.
Fecha Creacion    : 09/10/2008
Fecha Modificacion: 24/09/2015
Autor Modificacion: CSVD - FFVV
************************************************************************************/
PROCEDURE MAE_PR_PROCE_FACTU_DIARI
  (psCodigoPais              VARCHAR2,
   psCodigoPeriodo           VARCHAR2,
   psFechaFacturacion        VARCHAR2)
IS
-- Variables
pnTotalErrores NUMBER;
lsindReverEstatusPorAnul bas_param_pais.val_para%TYPE;

CURSOR c_consultorasConPedido(oidPais NUMBER, oidPeriodo NUMBER, fechaFacturacion VARCHAR2) IS
  SELECT psc.CLIE_OID_CLIE, cli.COD_CLIE, psc.ZZON_OID_ZONA, zr.oid_regi
   FROM PED_SOLIC_CABEC psc,
        PED_TIPO_SOLIC_PAIS tsp,
        PED_TIPO_SOLIC ts,
        MAE_CLIEN cli,
        (SELECT DISTINCT CTS.CLIE_OID_CLIE
        		  FROM  MAE_CLIEN_TIPO_SUBTI CTS,
        		        (SELECT SUB.OID_SUBT_CLIE
        	 		       FROM MAE_PARAM_ESTAT_TISUB ING, MAE_TIPO_CLIEN TIP, MAE_SUBTI_CLIEN SUB
        				  WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
        				    AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
        				    AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
        				    AND ING.PAIS_COD_PAIS = psCodigoPais
                    AND ING.IND_ACTI = '1'
                    AND ING.EST_REGI = '1') ETC
                  WHERE CTS.SBTI_OID_SUBT_CLIE = ETC.OID_SUBT_CLIE) ets,
        ZON_ZONA zz,
        ZON_REGIO zr
    WHERE psc.IND_OC = 1
    AND psc.PAIS_OID_PAIS = oidPais
    AND psc.PERD_OID_PERI = oidPeriodo
    AND TO_CHAR(psc.FEC_FACT,'DD/MM/YYYY') = fechaFacturacion
    AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
    AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
    AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
    AND cli.OID_CLIE = psc.CLIE_OID_CLIE
    AND ets.clie_oid_clie = cli.oid_clie
    AND ts.IND_DEVO  = 0
    AND psc.MODU_OID_MODU <> 15
    AND ts.IND_ANUL  = 0
    AND psc.IND_TS_NO_CONSO = 1
    AND psc.ZZON_OID_ZONA = zz.oid_zona
    AND zz.zorg_oid_regi = zr.oid_regi;

CURSOR c_consultorasSinPedido(oidPais NUMBER, oidPeriodo NUMBER, oidPeriodoAnterior NUMBER) IS
  SELECT uni.CLIE_OID_CLIE, cli.COD_CLIE
    FROM MAE_CLIEN cli,
         MAE_CLIEN_UNIDA_ADMIN uni,
         ZON_TERRI_ADMIN ter,
         ZON_SECCI sec,
         ZON_ZONA zon,
         MAE_TMP_ESTAT_ZONAS tmp,
         MAE_CLIEN_ESTAT_ACTUA con,
         (
          SELECT DISTINCT CTS.CLIE_OID_CLIE
        		FROM MAE_CLIEN_TIPO_SUBTI CTS,
        		     (SELECT SUB.OID_SUBT_CLIE
        	 		      FROM MAE_PARAM_ESTAT_TISUB ING,
                         MAE_TIPO_CLIEN TIP,
                         MAE_SUBTI_CLIEN SUB
        				   WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
        				     AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
        				     AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
        				     AND ING.PAIS_COD_PAIS = psCodigoPais
                     AND ING.IND_ACTI = '1'
                     AND ING.EST_REGI = '1'
                 ) ETC
           WHERE CTS.SBTI_OID_SUBT_CLIE = ETC.OID_SUBT_CLIE
         ) ets
   WHERE cli.OID_CLIE = uni.CLIE_OID_CLIE
     AND cli.OID_CLIE = con.CLIE_OID_CLIE
     AND ets.CLIE_OID_CLIE = cli.OID_CLIE
     AND uni.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
     AND ter.ZSCC_OID_SECC = sec.OID_SECC
     AND sec.ZZON_OID_ZONA = zon.OID_ZONA
     AND cli.PAIS_OID_PAIS = oidPais
     --AND uni.IND_ACTI = 1
     AND oidPeriodo BETWEEN uni.perd_oid_peri_ini AND NVL(uni.perd_oid_peri_fin,oidPeriodo)
     AND zon.OID_ZONA = tmp.OID_ZONA
  	 AND (con.CAM_EVAL_ACTU IS NULL OR con.CAM_EVAL_ACTU < psCodigoPeriodo)
     
     UNION

     SELECT cli.oid_clie oidcliente, cli.cod_clie codigoCliente
                                 FROM mae_clien_unida_admin adm,
                                      mae_clien cli,
                                      zon_terri_admin tadm,
                                      zon_terri terr,
                                      zon_secci secc,
                                      mae_clien_datos_adici adi,
                                      cra_perio per_ini,
                                      (select cod_clie
                                      from ZON_HISTO_TERRI_CONSU
                                      where cod_peri= psCodigoPeriodo) hcon,
                                      zon_zona zon_zon,
                                      mae_clien_estat_actua actua
                                WHERE     adm.clie_oid_clie = cli.oid_clie
                                      AND adm.ztad_oid_terr_admi = tadm.oid_terr_admi
                                      AND tadm.zscc_oid_secc = secc.oid_secc
                                      AND secc.ind_acti = 1
                                      AND secc.ind_borr = 0
                                      AND adi.clie_oid_clie = cli.oid_clie
                                      AND adi.ind_acti = 1
                                      AND tadm.terr_oid_terr = terr.oid_terr
                                      AND terr.ind_borr = 0
                                      AND secc.zzon_oid_zona = zon_zon.oid_zona
                                      AND zon_zon.ind_acti = 1
                                      AND zon_zon.ind_borr = 0
                                      AND adm.ind_acti = 1
                                      AND cli.cod_clie = hcon.cod_clie
                                      AND actua.clie_oid_clie=cli.oid_clie 
                                      AND actua.pais_cod_pais = psCodigoPais
                                      AND actua.cam_eval_actu<>psCodigoPeriodo   
                                     AND cli.oid_clie not in
                                       (     SELECT soc.clie_oid_clie
                                             FROM  PED_SOLIC_CABEC   soc
                                             WHERE  soc.clie_oid_clie = cli.oid_clie
                                             AND soc.ind_ts_no_conso = 1
                                             AND soc.ind_oc = 1
                                             AND soc.ind_pedi_prue <> 1
                                             AND soc.modu_oid_modu <> 15
                                             AND soc.perd_oid_peri = oidPeriodo
                                             AND soc.fec_fact = to_date(psFechaFacturacion, 'dd/MM/yyyy')  
                                       )
                             GROUP BY cli.oid_clie,
                                      adi.esta_oid_esta_clie, 
                                      adi.num_camp_sin_pedi,
                                      cli.cod_clie,
                                      terr.cod_terr
                                      
      UNION
                                                                     
      SELECT cli.oid_clie oidcliente, cli.cod_clie codigoCliente
                                 FROM mae_clien_unida_admin adm,
                                      mae_clien cli,
                                      zon_terri_admin tadm,
                                      zon_terri terr,
                                      zon_secci secc,
                                      mae_clien_datos_adici adi,
                                      cra_perio per_ini,
                                       (select cod_terr
                                      from ZON_HISTO_VALID_UNADM
                                      where cod_peri= psCodigoPeriodo) hcua,
                                      zon_zona zon_zon,
                                      mae_clien_estat_actua actua
                                WHERE     adm.clie_oid_clie = cli.oid_clie
                                      AND adm.ztad_oid_terr_admi = tadm.oid_terr_admi
                                      AND tadm.zscc_oid_secc = secc.oid_secc
                                      AND secc.ind_acti = 1
                                      AND secc.ind_borr = 0
                                      AND adi.clie_oid_clie = cli.oid_clie
                                      AND adi.ind_acti = 1
                                      AND tadm.terr_oid_terr = terr.oid_terr
                                      AND terr.ind_borr = 0
                                      AND secc.zzon_oid_zona = zon_zon.oid_zona
                                      AND zon_zon.ind_acti = 1
                                      AND zon_zon.ind_borr = 0
                                      AND adm.ind_acti = 1
                                      AND terr.cod_terr = hcua.cod_terr
                                      AND actua.clie_oid_clie=cli.oid_clie
                                      AND actua.cam_eval_actu<>psCodigoPeriodo
                                      AND actua.pais_cod_pais = psCodigoPais
                                      AND cli.oid_clie not in
                                       (     SELECT soc.clie_oid_clie
                                             FROM  PED_SOLIC_CABEC   soc
                                             WHERE  soc.clie_oid_clie = cli.oid_clie
                                             AND soc.ind_ts_no_conso = 1
                                             AND soc.ind_oc = 1
                                             AND soc.ind_pedi_prue <> 1
                                             AND soc.modu_oid_modu <> 15
                                             AND soc.perd_oid_peri = oidPeriodo
                                             AND soc.fec_fact =
                                                     to_date(psFechaFacturacion, 'dd/MM/yyyy') 
                                       )
                             GROUP BY cli.oid_clie,
                                      adi.esta_oid_esta_clie,
                                      adi.num_camp_sin_pedi,
                                      cli.cod_clie,
                                      terr.cod_terr,
                                      actua.cam_eval_actu
                                      
        UNION
                                                                     
        SELECT ext.clie_oid_Clie oidcliente, ext.cod_clie codigoCliente
          FROM mae_clien_unida_admin adm, 
               Mae_Clien_Estat_Actua ext
         WHERE adm.perd_oid_peri_fin =oidPeriodoAnterior  
           AND adm.ZTAD_OID_TERR_ADMI IN (SELECT DISTINCT adm.ztad_oid_terr_admi
                                              FROM Mae_Clien_Estat_Actua act,
                                                   mae_clien_unida_admin adm
                                             WHERE act.cam_eval_actu = psCodigoPeriodo --Periodo Actual
                                               AND act.clie_oid_clie = adm.clie_oid_clie
                                               AND adm.perd_oid_peri_fin IS NULL)       
           AND adm.clie_oid_clie = ext.clie_oid_clie
           AND ext.cam_eval_actu <> psCodigoPeriodo;
        
                                     

  TYPE interfazConsultorasConPed IS RECORD
  (
   oidCliente             PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE,
   codCliente             MAE_CLIEN.COD_CLIE%TYPE,
   oidZona                PED_SOLIC_CABEC.ZZON_OID_ZONA%TYPE,
   oidRegi                ZON_REGIO.OID_REGI%TYPE
  );

  TYPE interfazConsultorasConPedTab  IS TABLE OF interfazConsultorasConPed;
  interfazRecordCP     interfazConsultorasConPedTab;

  TYPE interfazConsultorasSinPed IS RECORD
  (
   oidCliente             PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE,
   codCliente             MAE_CLIEN.COD_CLIE%TYPE
  );

  TYPE interfazConsultorasSinPedTab  IS TABLE OF interfazConsultorasSinPed;
  interfazRecordSP     interfazConsultorasSinPedTab;

  lnOidPais              NUMBER;
  lnOidMarca             NUMBER;
  lnOidCanal             NUMBER;
  lnOidPeriodo           NUMBER;
  lnOidPeriodoAnterior   NUMBER;

  lsCodCampanaAnterior     VARCHAR2(6);
  lsCodCampanaEvaluacion   VARCHAR2(6);
  lsCodCampanaUltPedido    VARCHAR2(6);
  lnOidEstatusNuevo        NUMBER;

  lsCodEstatusActual       VARCHAR2(2);
  lsCodEstatusAnterior     VARCHAR2(2);
  lsCodEstatusNuevo        VARCHAR2(2);

  lnOidZona                NUMBER(12);
  lnOidRegi                NUMBER(12);
  lnNumPeriodosRetiro      NUMBER;
  lnCantidadPeriodos       NUMBER;
  lbSinErroresHistoEstatus BOOLEAN;

  lnTotalErrores           NUMBER;
  lnTotalErroresAnulacion  NUMBER;

BEGIN

BEGIN
       SELECT bpp.val_para
         INTO lsindReverEstatusPorAnul
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'indReverEstatusPorAnula'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsindReverEstatusPorAnul := '0';
  END;

  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
  lnTotalErrores := 0;

  --CALCULAMOS LA CAMPAÑA ANTERIOR A LA CAMPAÑA DE PROCESO
  lsCodCampanaAnterior:= GEN_FN_PERIO_ATRAS(psCodigoPeriodo, 1);
  lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsCodCampanaAnterior);

  -- PRIMERO LIMPIAR TODA LA TABLA TEMPORAL DE ZONAS PROCESADAS
  EXECUTE IMMEDIATE 'TRUNCATE TABLE MAE_TMP_ESTAT_ZONAS';

  -- LIMPIAR TODA LA TABLA TEMPORAL DE REGIONES PROCESADAS
  EXECUTE IMMEDIATE 'TRUNCATE TABLE MAE_TMP_ESTAT_REGIO';

  --(1) PROCESAMOS LAS NUEVOS INGRESOS DE CONSULTORAS
  MAE_PR_INGRE_NUEVA_CONSU(psCodigoPais);

  --(2) PROCESAMOS LAS FACTURAS ANULADAS
  IF lsindReverEstatusPorAnul = '1' THEN
  MAE_PR_PROCE_ANULA_FACTU(psCodigoPais, psCodigoPeriodo, PROCESO_FACTURACION, psFechaFacturacion, lnTotalErroresAnulacion);
  lnTotalErrores := lnTotalErrores + lnTotalErroresAnulacion;
  END IF;
  --(3) PROCESAMOS LAS CONSULTORAS CON PEDIDO
  OPEN c_consultorasConPedido(lnOidPais, lnOidPeriodo, psFechaFacturacion);
  LOOP
    FETCH c_consultorasConPedido BULK COLLECT INTO interfazRecordCP LIMIT W_FILAS;
    IF interfazRecordCP.COUNT > 0 THEN

      FOR x IN interfazRecordCP.FIRST .. interfazRecordCP.LAST LOOP
        lsCodCampanaEvaluacion := '';
        lbSinErroresHistoEstatus := TRUE;

        --RECUPERAMOS LA CONSULTORA, DE LA ENTIDAD DE 'ESTATUS CONSULTORA'
        BEGIN
          SELECT CAM_EVAL_ACTU, COD_ESTA_ACTU, COD_ESTA_ANTE, CAM_ULTI_PEDI
            INTO lsCodCampanaEvaluacion, lsCodEstatusActual, lsCodEstatusAnterior, lsCodCampanaUltPedido
            FROM MAE_CLIEN_ESTAT_ACTUA
           WHERE COD_CLIE = interfazRecordCP(x).codCliente
             AND PAIS_COD_PAIS = psCodigoPais;

          lsCodEstatusNuevo := lsCodEstatusActual;
        EXCEPTION
          WHEN OTHERS THEN
            lsCodEstatusNuevo := '--';
            lnTotalErrores := lnTotalErrores + 1;

            --INSERTAMOS UN ERROR; SI NO ENCONTRAMOS A LA CONSULTORA
            INSERT INTO MAE_TMP_ESTAT_ERROR
              (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
               TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
            VALUES
              (psCodigoPais, interfazRecordCP(x).codCliente, psCodigoPeriodo, psFechaFacturacion,
               PROCESO_FACTURACION, '1', '0', SYSDATE);
        END;

        IF (lsCodEstatusNuevo <> '--') THEN
          --CAMPAÑA DE EVALUACION < CAMPAÑA DE FACTURACION
          IF((lsCodCampanaEvaluacion IS NULL) OR (lsCodCampanaEvaluacion < psCodigoPeriodo)) THEN
            IF (lsCodEstatusActual = ESTADO_REGISTRADA) THEN
              lsCodEstatusNuevo := ESTADO_NUEVA;
            ELSIF (lsCodEstatusActual = ESTADO_NUEVA) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF (lsCodEstatusActual = ESTADO_NORMAL) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusActual = ESTADO_EGRESANTE) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusActual = ESTADO_EGRESADA) THEN
              lsCodEstatusNuevo := ESTADO_REINGRESO;
            ELSIF(lsCodEstatusActual = ESTADO_REINGRESO) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusActual = ESTADO_REACTIVADA) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusActual = ESTADO_RETIRADA) THEN
              lsCodEstatusNuevo := ESTADO_REACTIVADA;
            END IF;

            --Recuperamos el Oid estatus
            SELECT OID_ESTA_CLIE
            INTO   lnOidEstatusNuevo
            FROM   MAE_ESTAT_CLIEN
            WHERE  COD_ESTA_CLIE = lsCodEstatusNuevo;

            IF (lsCodEstatusActual <> lsCodEstatusNuevo) THEN
              -- Actualizamos la Entidad MAE_HISTO_ESTAT, SI HA HABIDO CAMBIO DE ESTATUS
              MAE_PR_ACTUA_HISTO_ESTAT(interfazRecordCP(x).oidCliente, lnOidPeriodo, lnOidPeriodoAnterior, lnOidEstatusNuevo,
                psCodigoPais, PROCESO_FACTURACION, psCodigoPeriodo, psFechaFacturacion, interfazRecordCP(x).codCliente, 'ADMIN-F_PD1',lbSinErroresHistoEstatus);
            END IF;

            IF(lbSinErroresHistoEstatus) THEN
              --Actualizamos la Entidad 'Estatus de Consultora'
              UPDATE MAE_CLIEN_ESTAT_ACTUA
                 SET CAM_EVAL_ANTE = CAM_EVAL_ACTU,
                     COD_ESTA_ANTE = COD_ESTA_ACTU,
                     CAM_ULTI_PEDI = psCodigoPeriodo,
                     CAM_PENU_PEDI = lsCodCampanaUltPedido,
                     CAM_EVAL_ACTU = psCodigoPeriodo,
                     COD_ESTA_ACTU = lsCodEstatusNuevo,
                     USU_MODI = 'ADMIN-F_PD1',
                     FEC_MODI = SYSDATE
               WHERE COD_CLIE = interfazRecordCP(x).codCliente
                 AND PAIS_COD_PAIS = psCodigoPais;

              -- Actualizamos la Entidad MAE_CLIEN_DATOS_ADICI (Se creo nueva tabla MAE_CLIEN_ESTAT_ACTUA_ADICI)
              UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                 SET NUM_CAMP_SIN_PEDI = 0,
                     ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                     USU_MODI = 'ADMIN-F_PD1',
                     FEC_MODI = SYSDATE
               WHERE CLIE_OID_CLIE = interfazRecordCP(x).oidCliente;
            ELSE
              lnTotalErrores := lnTotalErrores + 1;
            END IF;
          END IF;

          --CAMPAÑA DE EVALUACION = CAMPAÑA DE FACTURACION, SE TRATA DE UN PEDIDO EXTEMPORANEO
          IF(lsCodCampanaEvaluacion = psCodigoPeriodo) THEN
            IF (lsCodEstatusAnterior = ESTADO_REGISTRADA) THEN
              lsCodEstatusNuevo := ESTADO_NUEVA;
            ELSIF (lsCodEstatusAnterior = ESTADO_NUEVA) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF (lsCodEstatusAnterior = ESTADO_NORMAL) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusAnterior = ESTADO_EGRESANTE) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusAnterior = ESTADO_EGRESADA) THEN
              lsCodEstatusNuevo := ESTADO_REINGRESO;
            ELSIF(lsCodEstatusAnterior = ESTADO_REINGRESO) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusAnterior = ESTADO_REACTIVADA) THEN
              lsCodEstatusNuevo := ESTADO_NORMAL;
            ELSIF(lsCodEstatusAnterior = ESTADO_RETIRADA) THEN
              lsCodEstatusNuevo := ESTADO_REACTIVADA;
            END IF;

            --Recuperamos el Oid estatus
            SELECT OID_ESTA_CLIE
            INTO   lnOidEstatusNuevo
            FROM   MAE_ESTAT_CLIEN
            WHERE  COD_ESTA_CLIE = lsCodEstatusNuevo;

            IF (lsCodEstatusActual <> lsCodEstatusNuevo) THEN
              -- Actualizamos la Entidad MAE_HISTO_ESTAT, SI HA HABIDO CAMBIO DE ESTATUS
              MAE_PR_ACTUA_HISTO_ESTAT(interfazRecordCP(x).oidCliente, lnOidPeriodo, lnOidPeriodoAnterior, lnOidEstatusNuevo,
                psCodigoPais, PROCESO_FACTURACION, psCodigoPeriodo, psFechaFacturacion, interfazRecordCP(x).codCliente, 'ADMIN-F_PD2', lbSinErroresHistoEstatus);
            END IF;

            IF(lbSinErroresHistoEstatus) THEN
              --Actualizamos la Entidad 'Estatus de Consultora'
              UPDATE MAE_CLIEN_ESTAT_ACTUA
                 SET CAM_ULTI_PEDI = psCodigoPeriodo,
                     CAM_PENU_PEDI = lsCodCampanaUltPedido,
                     COD_ESTA_ACTU = lsCodEstatusNuevo,
                     USU_MODI = 'ADMIN-F_PD2',
                     FEC_MODI = SYSDATE
               WHERE COD_CLIE = interfazRecordCP(x).codCliente
                 AND PAIS_COD_PAIS = psCodigoPais;

              -- Actualizamos la Entidad MAE_CLIEN_DATOS_ADICI (Se creo nueva tabla MAE_CLIEN_ESTAT_ACTUA_ADICI)
              UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                 SET NUM_CAMP_SIN_PEDI = 0,
                     ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                     USU_MODI = 'ADMIN-F_PD2',
                     FEC_MODI = SYSDATE
               WHERE CLIE_OID_CLIE = interfazRecordCP(x).oidCliente;
            ELSE
              lnTotalErrores := lnTotalErrores + 1;
            END IF;
          END IF;

        END IF;

        --Actualizamos nuestro tabla de Zonas Procesadas
        BEGIN
          SELECT OID_ZONA
          INTO  lnOidZona
          FROM  MAE_TMP_ESTAT_ZONAS
          WHERE OID_ZONA = interfazRecordCP(x).oidZona;
        EXCEPTION
          WHEN OTHERS THEN
            INSERT INTO MAE_TMP_ESTAT_ZONAS VALUES(interfazRecordCP(x).oidZona);
        END;

        --Actualizamos nuestro tabla de Regiones Procesadas
        BEGIN
          SELECT OID_REGI
          INTO  lnOidRegi
          FROM  MAE_TMP_ESTAT_REGIO
          WHERE OID_REGI = interfazRecordCP(x).oidRegi;
        EXCEPTION
          WHEN OTHERS THEN
            INSERT INTO MAE_TMP_ESTAT_REGIO VALUES(interfazRecordCP(x).oidRegi);
        END;

      END LOOP;
    END IF;
    EXIT WHEN c_consultorasConPedido%NOTFOUND;
  END LOOP;
  CLOSE c_consultorasConPedido;

  --Recuperamos Nro Campañas de Periodos de Retiro configurado para el Pais
  SELECT NUM_PERI_RETI
    INTO lnNumPeriodosRetiro
    FROM SEG_PAIS
   WHERE OID_PAIS = lnOidPais;

  -- Incluir regiones con consultoras pendientes de evaluación
  INSERT INTO mae_tmp_estat_regio
  SELECT zorg.oid_regi
    FROM mae_clien_estat_actua x,
         mae_clien_tipo_subti ctsu,
         mae_clien_unida_admin cuad,
         zon_terri_admin ztad,
         zon_secci zscc,
         zon_zona zzon,
         zon_regio zorg
   WHERE 1=1
     AND x.cam_eval_actu < psCodigoPeriodo
     AND x.clie_oid_clie = ctsu.clie_oid_clie
     AND x.clie_oid_clie = cuad.clie_oid_clie
     AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
     AND ztad.zscc_oid_secc = zscc.oid_secc
     AND zscc.zzon_oid_zona = zzon.oid_zona
     AND zzon.zorg_oid_regi = zorg.oid_regi
     --
     AND ctsu.ticl_oid_tipo_clie = 2
     AND ctsu.sbti_oid_subt_clie IN (1,21)
     --
     AND lnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,lnOidPeriodo)
     --
     AND EXISTS (
                 SELECT pcie.cod_regi
                   FROM fac_progr_cierr pcie
                  WHERE pcie.tip_cier = 'R'
                    AND pcie.cam_proc = psCodigoPeriodo
                    AND pcie.est_cier = 'P'
                    AND pcie.cod_regi = zorg.cod_regi
                )
     AND zorg.ind_cier = 1
     AND zorg.oid_regi NOT IN (
                               SELECT oid_regi
                                 FROM mae_tmp_estat_regio
                              )
   GROUP BY zorg.oid_regi;


  -- Incluir regiones con consultoras pendientes de evaluación
  INSERT INTO mae_tmp_estat_zonas
  SELECT zzon.oid_zona
    FROM mae_clien_estat_actua x,
         mae_clien_tipo_subti ctsu,
         mae_clien_unida_admin cuad,
         zon_terri_admin ztad,
         zon_secci zscc,
         zon_zona zzon
   WHERE 1=1
     AND x.cam_eval_actu < psCodigoPeriodo
     AND x.clie_oid_clie = ctsu.clie_oid_clie
     AND x.clie_oid_clie = cuad.clie_oid_clie
     AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
     AND ztad.zscc_oid_secc = zscc.oid_secc
     AND zscc.zzon_oid_zona = zzon.oid_zona
     --
     AND ctsu.ticl_oid_tipo_clie = 2
     AND ctsu.sbti_oid_subt_clie IN (1,21)
     --
     AND lnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,lnOidPeriodo)
     --
     AND EXISTS (
                 SELECT pcie.cod_regi
                   FROM fac_progr_cierr pcie
                  WHERE pcie.tip_cier = 'Z'
                    AND pcie.cam_proc = psCodigoPeriodo
                    AND pcie.est_cier = 'P'
                    AND pcie.cod_zona = zzon.cod_zona
                )
     AND zzon.ind_cier = 1
     AND zzon.oid_zona NOT IN (
                               SELECT oid_zona
                                 FROM mae_tmp_estat_zonas
                              )
   GROUP BY zzon.oid_zona;


  --(4) PROCESAMOS LAS CONSULTORAS SIN PEDIDO
  OPEN c_consultorasSinPedido(lnOidPais, lnOidPeriodo, lnOidPeriodoAnterior);
  LOOP
    FETCH c_consultorasSinPedido BULK COLLECT INTO interfazRecordSP LIMIT W_FILAS;
    IF interfazRecordSP.COUNT > 0 THEN

      FOR x IN interfazRecordSP.FIRST .. interfazRecordSP.LAST LOOP
        lsCodCampanaEvaluacion := '';
        lbSinErroresHistoEstatus := TRUE;

        --RECUPERAMOS LA CONSULTORA, DE LA ENTIDAD DE 'ESTATUS CONSULTORA'
        BEGIN
          SELECT CAM_EVAL_ACTU, COD_ESTA_ACTU, CAM_ULTI_PEDI
            INTO lsCodCampanaEvaluacion, lsCodEstatusActual, lsCodCampanaUltPedido
            FROM MAE_CLIEN_ESTAT_ACTUA
           WHERE COD_CLIE = interfazRecordSP(x).codCliente
             AND PAIS_COD_PAIS = psCodigoPais;

          lsCodEstatusNuevo := lsCodEstatusActual;
        EXCEPTION
          WHEN OTHERS THEN
            lsCodEstatusNuevo := '--';
            lnTotalErrores := lnTotalErrores + 1;

            --INSERTAMOS UN ERROR; SI NO ENCONTRAMOS A LA CONSULTORA
            INSERT INTO MAE_TMP_ESTAT_ERROR
              (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
               TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
            VALUES
              (psCodigoPais, interfazRecordSP(x).codCliente, psCodigoPeriodo, psFechaFacturacion,
               PROCESO_FACTURACION, '1', '0', SYSDATE);
        END;

        IF (lsCodEstatusNuevo <> '--') THEN
          IF ((lsCodCampanaEvaluacion IS NULL) OR (lsCodCampanaEvaluacion < psCodigoPeriodo)) THEN
            IF ((lsCodCampanaEvaluacion IS NOT NULL) AND (lsCodCampanaEvaluacion < lsCodCampanaAnterior)) THEN
              lnTotalErrores := lnTotalErrores + 1;

              --INSERTAMOS UN ERROR; SI LA ULTIMA EVALUACION DE ESTATUS ANTERIOR EXCEDE EN UNA CAMPAÑA DE FACTURACION
              INSERT INTO MAE_TMP_ESTAT_ERROR
                (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
                 TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
              VALUES
                (psCodigoPais, interfazRecordSP(x).codCliente, psCodigoPeriodo, psFechaFacturacion,
                 PROCESO_FACTURACION, '2', '0', SYSDATE);
            ELSE
              --EVALUAMOS EL ESTATUS DE LA CONSULTORA
              IF (lsCodEstatusActual = ESTADO_REGISTRADA) THEN
                lsCodEstatusNuevo := ESTADO_REGISTRADA;
              ELSIF (lsCodEstatusActual = ESTADO_NUEVA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF (lsCodEstatusActual = ESTADO_NORMAL) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_EGRESANTE) THEN
                lsCodEstatusNuevo := ESTADO_EGRESADA;
              ELSIF(lsCodEstatusActual = ESTADO_EGRESADA) THEN
                lnCantidadPeriodos := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodCampanaUltPedido,
                                               psCodigoPeriodo, lnOidPais, lnOidMarca, lnOidCanal) - 1;

                IF(lnCantidadPeriodos >= lnNumPeriodosRetiro) THEN
                  lsCodEstatusNuevo := ESTADO_RETIRADA;
                ELSE
                  lsCodEstatusNuevo := ESTADO_EGRESADA;
                END IF;
              ELSIF(lsCodEstatusActual = ESTADO_REINGRESO) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_REACTIVADA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_RETIRADA) THEN
                lsCodEstatusNuevo := ESTADO_RETIRADA;
              END IF;

              --Recuperamos el Oid estatus
              SELECT OID_ESTA_CLIE
              INTO   lnOidEstatusNuevo
              FROM   MAE_ESTAT_CLIEN
              WHERE  COD_ESTA_CLIE = lsCodEstatusNuevo;

              /* se comenta para que no actualice el historico_estatus
              IF (lsCodEstatusActual <> lsCodEstatusNuevo) THEN
                --ACTUALIZAMOS LA ENTIDAD MAE_HISTO_ESTAT, SI HA HABIDO CAMBIO DE ESTATUS
                MAE_PR_ACTUA_HISTO_ESTAT(interfazRecordSP(x).oidCliente, lnOidPeriodo, lnOidPeriodoAnterior, lnOidEstatusNuevo,
                  psCodigoPais, PROCESO_FACTURACION, psCodigoPeriodo, psFechaFacturacion, interfazRecordSP(x).codCliente, lbSinErroresHistoEstatus);
              END IF;
              */

              IF(lbSinErroresHistoEstatus) THEN
                --Actualizamos la Entidad 'Estatus de Consultora'
                UPDATE MAE_CLIEN_ESTAT_ACTUA
                   SET CAM_EVAL_ANTE = CAM_EVAL_ACTU,
                       COD_ESTA_ANTE = COD_ESTA_ACTU,
                       CAM_EVAL_ACTU = psCodigoPeriodo,
                       COD_ESTA_ACTU = lsCodEstatusNuevo,
                       USU_MODI = 'ADMIN-F_SP',
                       FEC_MODI = SYSDATE
                 WHERE COD_CLIE = interfazRecordSP(x).codCliente
                   AND PAIS_COD_PAIS = psCodigoPais;

                --ACTUALIZAMOS LA ENTIDAD MAE_CLIEN_DATOS_ADICI (se creo nueva tabla MAE_CLIEN_ESTAT_ACTUA_ADICI)
                /*se comenta oara que no actualice datos_adici
                IF(lsCodEstatusActual = ESTADO_EGRESADA AND lsCodEstatusnuevo = ESTADO_RETIRADA) THEN
                  UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                     SET NUM_CAMP_SIN_PEDI = NVL(NUM_CAMP_SIN_PEDI,0) + 1,
                         ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                         IND_ACTI = 0,
                         USU_MODI = 'ADMIN-F_SP',
                         FEC_MODI = SYSDATE
                   WHERE CLIE_OID_CLIE = interfazRecordSP(x).oidCliente;
                ELSE
                  UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                     SET NUM_CAMP_SIN_PEDI = NVL(NUM_CAMP_SIN_PEDI,0) + 1,
                         ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                         USU_MODI = 'ADMIN-F_SP',
                         FEC_MODI = SYSDATE
                   WHERE CLIE_OID_CLIE = interfazRecordSP(x).oidCliente;
                END IF;
                */

              ELSE
                lnTotalErrores := lnTotalErrores + 1;
              END IF;

            END IF;
          END IF;
        END IF;

      END LOOP;

    END IF;
    EXIT WHEN c_consultorasSinPedido%NOTFOUND;
  END LOOP;
  CLOSE c_consultorasSinPedido;

  pnTotalErrores := lnTotalErrores;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_PROCE_FACTU_DIARI: (' || ls_sqlerrm);
END MAE_PR_PROCE_FACTU_DIARI;

/***************************************************************************
Descripcion       : Proceso de Anulacion de Facturas para Evaluacion de Estatus
                    de las Consultoras que han anulado su pedido.
Fecha Creacion    : 27/10/2008
Fecha Modificacion: 23/04/2015
Autor             : CSVD-FFVV
***************************************************************************/
PROCEDURE MAE_PR_PROCE_ANULA_FACTU
  (psCodigoPais              VARCHAR2,
   psCodigoPeriodo           VARCHAR2,
   psTipoProceso             VARCHAR2,
   psFechaFacturacion        VARCHAR2,
   pnTotalErrores       OUT  NUMBER)
IS
CURSOR c_FacturasAnuladas(oidPais NUMBER, oidPeriodo NUMBER) IS
  SELECT psc.CLIE_OID_CLIE, cli.COD_CLIE
   FROM PED_SOLIC_CABEC psc,
        PED_TIPO_SOLIC_PAIS tsp,
        PED_TIPO_SOLIC ts,
        MAE_CLIEN cli,
        (SELECT DISTINCT CTS.CLIE_OID_CLIE
        		  FROM  MAE_CLIEN_TIPO_SUBTI CTS,
        		        (SELECT SUB.OID_SUBT_CLIE
        	 		       FROM MAE_PARAM_ESTAT_TISUB ING, MAE_TIPO_CLIEN TIP, MAE_SUBTI_CLIEN SUB
        				  WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
        				    AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
        				    AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
        				    AND ING.PAIS_COD_PAIS = psCodigoPais
                    AND ING.IND_ACTI = '1'
                    AND ING.EST_REGI = '1') ETC
                  WHERE CTS.SBTI_OID_SUBT_CLIE = ETC.OID_SUBT_CLIE) ets,
		(SELECT * FROM PED_SOLIC_CABEC WHERE PAIS_OID_PAIS = oidPais AND PERD_OID_PERI = oidPeriodo) con
    WHERE psc.PAIS_OID_PAIS = oidPais
    AND psc.PERD_OID_PERI = oidPeriodo
    AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
    AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
    AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
    AND cli.OID_CLIE = psc.CLIE_OID_CLIE
    AND ets.CLIE_OID_CLIE = cli.OID_CLIE
    AND psc.IND_TS_NO_CONSO = 0
    AND ts.IND_ANUL = 1
    AND con.OID_SOLI_CABE = psc.SOCA_OID_DOCU_REFE;

  TYPE interfazFacturasAnuladas IS RECORD
  (
   oidCliente             PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE,
   codCliente             MAE_CLIEN.COD_CLIE%TYPE
  );

  TYPE interfazFacturasAnuladasTab  IS TABLE OF interfazFacturasAnuladas;
  interfazRecordFA     interfazFacturasAnuladasTab;

  lnOidPais              NUMBER;
  lnOidMarca             NUMBER;
  lnOidCanal             NUMBER;
  lnOidPeriodo           NUMBER;
  lnOidPeriodoAnterior   NUMBER;

  lsCodCampanaAnterior     VARCHAR2(6);
  lsCodCampanaEvaluacion   VARCHAR2(6);
  lsCodCampanaUltPedido    VARCHAR2(6);
  lsCodCampanaPenuPedido   VARCHAR2(6);
  lnOidEstatusNuevo        NUMBER;

  lsCodEstatusActual       VARCHAR2(2);
  lsCodEstatusAnterior     VARCHAR2(2);
  lsCodEstatusNuevo        VARCHAR2(2);

  lnNumPeriodosRetiro         NUMBER;
  lnCantidadPeriodos          NUMBER;
  lbSinErroresHistoEstatus    BOOLEAN;
  lnTotalErrores              NUMBER;
  lnTotalOrdenes              NUMBER;
BEGIN
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
  lnTotalErrores := 0;

  --CALCULAMOS LA CAMPAÑA ANTERIOR A LA CAMPAÑA DE PROCESO
  lsCodCampanaAnterior:= GEN_FN_PERIO_ATRAS(psCodigoPeriodo, 1);
  lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsCodCampanaAnterior);

  --Recuperamos Nro Campañas de Periodos de Retiro configurado para el Pais
  SELECT NUM_PERI_RETI
    INTO lnNumPeriodosRetiro
    FROM SEG_PAIS
   WHERE OID_PAIS = lnOidPais;

  --PROCESAMOS LAS FACTURAS ANULADAS
  OPEN c_FacturasAnuladas(lnOidPais, lnOidPeriodo);
  LOOP
    FETCH c_FacturasAnuladas BULK COLLECT INTO interfazRecordFA LIMIT W_FILAS;
    IF interfazRecordFA.COUNT > 0 THEN

      FOR x IN interfazRecordFA.FIRST .. interfazRecordFA.LAST LOOP
        lsCodCampanaEvaluacion := '';
        lbSinErroresHistoEstatus := TRUE;

        --RECUPERAMOS LA CONSULTORA, DE LA ENTIDAD DE 'ESTATUS CONSULTORA'
        BEGIN
          SELECT CAM_EVAL_ACTU, COD_ESTA_ANTE, COD_ESTA_ACTU, CAM_ULTI_PEDI, CAM_PENU_PEDI
            INTO lsCodCampanaEvaluacion, lsCodEstatusAnterior, lsCodEstatusActual, lsCodCampanaUltPedido, lsCodCampanaPenuPedido
            FROM MAE_CLIEN_ESTAT_ACTUA
           WHERE COD_CLIE = interfazRecordFA(x).codCliente
             AND PAIS_COD_PAIS = psCodigoPais;

          lsCodEstatusNuevo := lsCodEstatusAnterior;
        EXCEPTION
          WHEN OTHERS THEN
            lsCodEstatusNuevo := '--';
            lnTotalErrores := lnTotalErrores + 1;

            --INSERTAMOS UN ERROR; SI NO ENCONTRAMOS A LA CONSULTORA
            INSERT INTO MAE_TMP_ESTAT_ERROR
              (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
               TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
            VALUES
              (psCodigoPais, interfazRecordFA(x).codCliente, psCodigoPeriodo, psFechaFacturacion,
               psTipoProceso, '1', '0', SYSDATE);
        END;

        SELECT COUNT(1)
          INTO lnTotalOrdenes
          FROM PED_SOLIC_CABEC psc,
               PED_TIPO_SOLIC_PAIS tsp,
               PED_TIPO_SOLIC ts,
               (SELECT *
                  FROM PED_SOLIC_CABEC
                 WHERE PAIS_OID_PAIS = lnOidPais
                   AND CLIE_OID_CLIE = interfazRecordFA(x).oidCliente) con
         WHERE psc.IND_OC = 1
           AND psc.PAIS_OID_PAIS = lnOidPais
           AND ((psc.IND_PEDI_PRUE is null) OR (psc.IND_PEDI_PRUE = 0))
           AND psc.TSPA_OID_TIPO_SOLI_PAIS = tsp.OID_TIPO_SOLI_PAIS
           AND tsp.TSOL_OID_TIPO_SOLI = ts.OID_TIPO_SOLI
           AND ts.IND_DEVO = 0
           AND psc.MODU_OID_MODU <> 15
           AND ts.IND_ANUL = 0
           AND psc.IND_TS_NO_CONSO = 1
           AND psc.CLIE_OID_CLIE = interfazRecordFA(x).oidCliente
           AND psc.SOCA_OID_SOLI_CABE = con.OID_SOLI_CABE
           AND con.ESSO_OID_ESTA_SOLI <> 4
           AND PSC.PERD_OID_PERI = lnOidPeriodo;

        IF(lnTotalOrdenes = 0) THEN

          IF (lsCodEstatusNuevo <> '--') THEN
            IF ((lsCodCampanaEvaluacion = psCodigoPeriodo) AND (lsCodCampanaUltPedido = psCodigoPeriodo)) THEN
              --EVALUAMOS EL ESTATUS DE LA CONSULTORA
              IF (lsCodEstatusAnterior = ESTADO_REGISTRADA) THEN
                lsCodEstatusNuevo := ESTADO_REGISTRADA;
              ELSIF (lsCodEstatusAnterior = ESTADO_NUEVA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF (lsCodEstatusAnterior = ESTADO_NORMAL) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusAnterior = ESTADO_EGRESANTE) THEN
                lsCodEstatusNuevo := ESTADO_EGRESADA;
              ELSIF(lsCodEstatusAnterior = ESTADO_EGRESADA) THEN
                lnCantidadPeriodos := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodCampanaPenuPedido,
                                               psCodigoPeriodo, lnOidPais, lnOidMarca, lnOidCanal) - 1;

                IF(lnCantidadPeriodos >= lnNumPeriodosRetiro) THEN
                  lsCodEstatusNuevo := ESTADO_RETIRADA;
                ELSE
                  lsCodEstatusNuevo := ESTADO_EGRESADA;
                END IF;
              ELSIF(lsCodEstatusAnterior = ESTADO_REINGRESO) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusAnterior = ESTADO_REACTIVADA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusAnterior = ESTADO_RETIRADA) THEN
                lsCodEstatusNuevo := ESTADO_RETIRADA;
              END IF;

              --Recuperamos el Oid estatus
              SELECT OID_ESTA_CLIE
              INTO   lnOidEstatusNuevo
              FROM   MAE_ESTAT_CLIEN
              WHERE  COD_ESTA_CLIE = lsCodEstatusNuevo;

              IF (lsCodEstatusActual <> lsCodEstatusNuevo) THEN
                --ACTUALIZAMOS LA ENTIDAD MAE_HISTO_ESTAT, SI HA HABIDO CAMBIO DE ESTATUS
                MAE_PR_ACTUA_HISTO_ESTAT(interfazRecordFA(x).oidCliente, lnOidPeriodo, lnOidPeriodoAnterior, lnOidEstatusNuevo,
                  psCodigoPais, psTipoProceso, psCodigoPeriodo, psFechaFacturacion, interfazRecordFA(x).codCliente, 'ADMIN-F_AF', lbSinErroresHistoEstatus);
              END IF;

              IF(lbSinErroresHistoEstatus) THEN
                --obtenemos la cantidad de Periodos que hay entre la campaña de Ultimo Pedido
                --y Campaña Proceso, que seran actualizados en el campo Campaña sin Pedido
                lnCantidadPeriodos := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodCampanaPenuPedido,
                                               psCodigoPeriodo, lnOidPais, lnOidMarca, lnOidCanal) - 1;

                --Actualizamos la Entidad 'Estatus de Consultora'
                UPDATE MAE_CLIEN_ESTAT_ACTUA
                   SET CAM_ULTI_PEDI = lsCodCampanaPenuPedido,
                       COD_ESTA_ACTU = lsCodEstatusNuevo,
                       USU_MODI = 'ADMIN-F_AF',
                       FEC_MODI = SYSDATE
                 WHERE COD_CLIE = interfazRecordFA(x).codCliente
                   AND PAIS_COD_PAIS = psCodigoPais;

                --ACTUALIZAMOS LA ENTIDAD MAE_CLIEN_DATOS_ADICI (Se creo nueva tabla MAE_CLIEN_ESTAT_ACTUA_ADICI)
                UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                   SET NUM_CAMP_SIN_PEDI = lnCantidadPeriodos,
                       ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                       USU_MODI = 'ADMIN-F_AF',
                       FEC_MODI = SYSDATE
                 WHERE CLIE_OID_CLIE = interfazRecordFA(x).oidCliente;
              ELSE
                lnTotalErrores := lnTotalErrores + 1;
              END IF;

            END IF;
          END IF;

        END IF;

      END LOOP;

    END IF;
    EXIT WHEN c_FacturasAnuladas%NOTFOUND;
  END LOOP;
  CLOSE c_FacturasAnuladas;

  pnTotalErrores := lnTotalErrores;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_PROCE_ANULA_FACTU: (' || ls_sqlerrm);
END MAE_PR_PROCE_ANULA_FACTU;

/***************************************************************************
Descripcion       : Proceso de Facturacion al cierre de Region para Evaluacion
                    de Estatus de las Consultoras que no han pasado pedido de
                    las regiones pasadas como parametro
Fecha Creacion    : 31/10/2008
Fecha Modificacion: 23/04/2015
Autor             : CSVD-FFVV
***************************************************************************/
PROCEDURE MAE_PR_PROCE_CIERR_REGIO
  (psCodigoPais              VARCHAR2,
   psCodigoPeriodo           VARCHAR2,
   psCodigoRegion            VARCHAR2,
   psFechaFacturacion        VARCHAR2,
   psCodigoUsuario           VARCHAR2)
IS
-- Variables
pnTotalErrores NUMBER;
--Se toma a todas las consultoras que no han pasado pedido en la ultima campaña
CURSOR c_consultoras(oidPais NUMBER) IS
  SELECT uni.CLIE_OID_CLIE, cli.COD_CLIE
    FROM MAE_CLIEN cli,MAE_CLIEN_UNIDA_ADMIN uni,
         ZON_TERRI_ADMIN ter, ZON_SECCI sec, ZON_ZONA zon,
         ZON_REGIO reg, MAE_CLIEN_ESTAT_ACTUA con,
        (SELECT DISTINCT CTS.CLIE_OID_CLIE
        		  FROM  MAE_CLIEN_TIPO_SUBTI CTS,
        		        (SELECT SUB.OID_SUBT_CLIE
        	 		       FROM MAE_PARAM_ESTAT_TISUB ING, MAE_TIPO_CLIEN TIP, MAE_SUBTI_CLIEN SUB
        				  WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
        				    AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
        				    AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
        				    AND ING.PAIS_COD_PAIS = psCodigoPais
                    AND ING.IND_ACTI = '1'
                    AND ING.EST_REGI = '1') ETC
                  WHERE CTS.SBTI_OID_SUBT_CLIE = ETC.OID_SUBT_CLIE) ets
   WHERE cli.OID_CLIE = uni.CLIE_OID_CLIE
     AND cli.OID_CLIE = con.CLIE_OID_CLIE
     AND ets.CLIE_OID_CLIE = cli.OID_CLIE
     AND uni.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
     AND ter.ZSCC_OID_SECC = sec.OID_SECC
     AND sec.ZZON_OID_ZONA = zon.OID_ZONA
     AND cli.PAIS_OID_PAIS = oidPais
     AND uni.IND_ACTI = 1
     AND zon.zorg_oid_regi = reg.oid_regi
     --AND INSTR(',' || psRegiones || ',', ',' || TO_CHAR(OID_REGI) || ',') > 0
     AND reg.cod_regi = psCodigoRegion
  	 AND (con.CAM_ULTI_PEDI IS NULL OR con.CAM_ULTI_PEDI < psCodigoPeriodo);


  TYPE interfazConsultoras IS RECORD
  (
   oidCliente             PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE,
   codCliente             MAE_CLIEN.COD_CLIE%TYPE
  );

  TYPE interfazConsultorasTab  IS TABLE OF interfazConsultoras;
  interfazRecordC        interfazConsultorasTab;

  lnOidPais              NUMBER;
  lnOidMarca             NUMBER;
  lnOidCanal             NUMBER;
  lnOidPeriodo           NUMBER;
  lnOidPeriodoAnterior   NUMBER;

  lsCodCampanaAnterior     VARCHAR2(6);
  lsCodCampanaEvaluacion   VARCHAR2(6);
  lsCodCampanaUltPedido    VARCHAR2(6);
  lnOidEstatusNuevo        NUMBER;

  lsCodEstatusActual       VARCHAR2(2);
  lsCodEstatusNuevo        VARCHAR2(2);
  lsCodEstatusAnterior     VARCHAR2(2);

  lnNumPeriodosRetiro      NUMBER;
  lnNumPeriodosEgreso      NUMBER;
  lnCantidadPeriodos       NUMBER;
  lbSinErroresHistoEstatus BOOLEAN;

  lnTotalErrores           NUMBER;
  lnNumCampSinPedi         NUMBER;
  lnIndActividad           NUMBER;
  
BEGIN
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
  lnTotalErrores := 0;

  --CALCULAMOS LA CAMPAÑA ANTERIOR A LA CAMPAÑA DE PROCESO
  lsCodCampanaAnterior:= GEN_FN_PERIO_ATRAS(psCodigoPeriodo, 1);
  lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsCodCampanaAnterior);

  --Recuperamos Nro Campañas de Periodos de Retiro configurado para el Pais
  SELECT NUM_PERI_RETI
    INTO lnNumPeriodosRetiro
    FROM SEG_PAIS
   WHERE OID_PAIS = lnOidPais;

  --Recuperamos Nro Campañas de Periodos de Egreso configurado para el Pais
  SELECT NUM_PERI_EGRE
    INTO lnNumPeriodosEgreso
    FROM SEG_PAIS
   WHERE OID_PAIS = lnOidPais;

  --PROCESAMOS LAS CONSULTORAS SIN PEDIDO
  OPEN c_consultoras(lnOidPais);
  LOOP
    FETCH c_consultoras BULK COLLECT INTO interfazRecordC LIMIT W_FILAS;
    IF interfazRecordC.COUNT > 0 THEN

      FOR x IN interfazRecordC.FIRST .. interfazRecordC.LAST LOOP
        lsCodCampanaEvaluacion := '';
        lbSinErroresHistoEstatus := TRUE;

        --RECUPERAMOS LA CONSULTORA, DE LA ENTIDAD DE 'ESTATUS CONSULTORA'
        BEGIN
          SELECT CAM_EVAL_ACTU, COD_ESTA_ACTU, CAM_ULTI_PEDI, COD_ESTA_ANTE
            INTO lsCodCampanaEvaluacion, lsCodEstatusActual, lsCodCampanaUltPedido, lsCodEstatusAnterior
            FROM MAE_CLIEN_ESTAT_ACTUA
           WHERE COD_CLIE = interfazRecordC(x).codCliente
             AND PAIS_COD_PAIS = psCodigoPais;

          lsCodEstatusNuevo := lsCodEstatusActual;
        EXCEPTION
          WHEN OTHERS THEN
            lsCodEstatusNuevo := '--';
            lnTotalErrores := lnTotalErrores + 1;

            --INSERTAMOS UN ERROR; SI NO ENCONTRAMOS A LA CONSULTORA
            INSERT INTO MAE_TMP_ESTAT_ERROR
              (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
               TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
            VALUES
              (psCodigoPais, interfazRecordC(x).codCliente, psCodigoPeriodo, NULL,
               PROCESO_CIERRE_REGION, '1', '0', SYSDATE);
        END;

        IF (lsCodEstatusNuevo <> '--') THEN

--          IF ((lsCodCampanaEvaluacion IS NULL) OR (lsCodCampanaEvaluacion < psCodigoPeriodo)) THEN
            IF ((lsCodCampanaEvaluacion IS NOT NULL) AND (lsCodCampanaEvaluacion < lsCodCampanaAnterior)) THEN
              lnTotalErrores := lnTotalErrores + 1;

              --INSERTAMOS UN ERROR; SI LA ULTIMA EVALUACION DE ESTATUS ANTERIOR EXCEDE EN UNA CAMPAÑA DE FACTURACION
              INSERT INTO MAE_TMP_ESTAT_ERROR
                (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
                 TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
              VALUES
                (psCodigoPais, interfazRecordC(x).codCliente, psCodigoPeriodo, NULL,
                 PROCESO_CIERRE_REGION, '2', '0', SYSDATE);
            ELSE

              lsCodEstatusNuevo := lsCodEstatusActual; -----se añade
              lsCodEstatusActual := lsCodEstatusAnterior;

               /*
              --EVALUAMOS EL ESTATUS DE LA CONSULTORA
              IF (lsCodEstatusActual = ESTADO_REGISTRADA) THEN
                lsCodEstatusNuevo := ESTADO_REGISTRADA;
              ELSIF (lsCodEstatusActual = ESTADO_NUEVA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF (lsCodEstatusActual = ESTADO_NORMAL) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_EGRESANTE) THEN
                lnCantidadPeriodos := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodCampanaUltPedido,
                                               psCodigoPeriodo, lnOidPais, lnOidMarca, lnOidCanal) - 1;

                IF(lnCantidadPeriodos > lnNumPeriodosEgreso) THEN
                  lsCodEstatusNuevo := ESTADO_EGRESADA;
                ELSE
                  lsCodEstatusNuevo := ESTADO_EGRESANTE;
                END IF;

              ELSIF(lsCodEstatusActual = ESTADO_EGRESADA) THEN
                lnCantidadPeriodos := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodCampanaUltPedido,
                                               psCodigoPeriodo, lnOidPais, lnOidMarca, lnOidCanal) - 1;

                IF(lnCantidadPeriodos >= lnNumPeriodosRetiro) THEN
                  lsCodEstatusNuevo := ESTADO_RETIRADA;
                ELSE
                  lsCodEstatusNuevo := ESTADO_EGRESADA;
                END IF;
              ELSIF(lsCodEstatusActual = ESTADO_REINGRESO) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_REACTIVADA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_RETIRADA) THEN
                lsCodEstatusNuevo := ESTADO_RETIRADA;
              END IF;
*/
              --Recuperamos el Oid estatus
              SELECT OID_ESTA_CLIE
              INTO   lnOidEstatusNuevo
              FROM   MAE_ESTAT_CLIEN
              WHERE  COD_ESTA_CLIE = lsCodEstatusNuevo;

              IF (lsCodEstatusActual <> lsCodEstatusNuevo) THEN
                --ACTUALIZAMOS LA ENTIDAD MAE_HISTO_ESTAT, SI HA HABIDO CAMBIO DE ESTATUS
                MAE_PR_ACTUA_HISTO_ESTAT(interfazRecordC(x).oidCliente, lnOidPeriodo, lnOidPeriodoAnterior, lnOidEstatusNuevo,
                  psCodigoPais, PROCESO_CIERRE_REGION, psCodigoPeriodo, NULL, interfazRecordC(x).codCliente, 'ADMIN-CR', lbSinErroresHistoEstatus);
              END IF;

              IF(lbSinErroresHistoEstatus) THEN
                --Actualizamos la Entidad 'Estatus de Consultora'
                UPDATE MAE_CLIEN_ESTAT_ACTUA
                   SET --CAM_EVAL_ANTE = CAM_EVAL_ACTU,
                       --COD_ESTA_ANTE = COD_ESTA_ACTU,
                       --CAM_EVAL_ACTU = psCodigoPeriodo,
                       --COD_ESTA_ACTU = lsCodEstatusNuevo,
                       USU_MODI = 'ADMIN-CR',
                       FEC_MODI = SYSDATE
                 WHERE COD_CLIE = interfazRecordC(x).codCliente
                   AND PAIS_COD_PAIS = psCodigoPais;

                --ACTUALIZAMOS LA ENTIDAD MAE_CLIEN_DATOS_ADICI
                IF(lsCodEstatusActual = ESTADO_EGRESADA AND lsCodEstatusnuevo = ESTADO_RETIRADA) THEN
                  UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                     SET NUM_CAMP_SIN_PEDI = NVL(NUM_CAMP_SIN_PEDI,0) + 1,
                         ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                         IND_ACTI = 0,
                         USU_MODI = 'ADMIN-CR',
                         FEC_MODI = SYSDATE
                   WHERE CLIE_OID_CLIE = interfazRecordC(x).oidCliente;
                ELSE
                  IF(lsCodEstatusnuevo = ESTADO_REGISTRADA) THEN
                    SELECT NVL(NUM_CAMP_SIN_PEDI,0) + 1
                      INTO lnNumCampSinPedi
                      FROM MAE_CLIEN_ESTAT_ACTUA_ADICI
                     WHERE CLIE_OID_CLIE = interfazRecordC(x).oidCliente;  
                   
                    IF(lnNumCampSinPedi >= 18) THEN
                      lnIndActividad := 0;
                    ELSE
                      lnIndActividad := 1;
                    END IF;
                  
                    UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                       SET NUM_CAMP_SIN_PEDI = lnNumCampSinPedi,
                           IND_ACTI = lnIndActividad,
                           ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                           USU_MODI = 'ADMIN-CR',
                           FEC_MODI = SYSDATE
                     WHERE CLIE_OID_CLIE = interfazRecordC(x).oidCliente;
                  ELSE
                    UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                       SET NUM_CAMP_SIN_PEDI = NVL(NUM_CAMP_SIN_PEDI,0) + 1,
                           ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                           USU_MODI = 'ADMIN-CR',
                           FEC_MODI = SYSDATE
                     WHERE CLIE_OID_CLIE = interfazRecordC(x).oidCliente;    
                  END IF;                      
                END IF;
              ELSE
                lnTotalErrores := lnTotalErrores + 1;
              END IF;

            END IF;
          END IF;
--        END IF;

      END LOOP;

      --Se añade registro por cierre de region en fac_contr_cierr (MAELanzarActualizacionEstatus), se debe descomentar cuando pase a producción
      /*
      INSERT INTO FAC_CONTR_CIERR
              (oid_ctrl,
               pais_oid_pais,
               val_secu_cier,
               fec_cier,
               val_proc_ejec,
               val_resu_proc,
               perd_oid_peri,
               zzon_oid_zona,
               tcie_oid_tipo_cier,
               zorg_oid_regi,
               fec_ulti_actu
              )
            VALUES
              (FAC_COCI_SEQ.NEXTVAL,
               lnOidPais,
               FAC_COCI_SEQ.CURRVAL,
               TO_DATE(psFechaFacturacion, 'dd/MM/yyyy'),
               'Asignar_estatus_en_lotes',
               'OK',
               psCodigoPeriodo,
               NULL,
               1,
               psCodigoRegion,
               SYSDATE);

      ---Actualiza el FAC_PROGR_CIERR
          UPDATE FAC_PROGR_CIERR
             SET EST_CIER = 'P',
                 USU_MODI = psCodigoUsuario, --CODIGO USUARIO
                 FEC_MODI = SYSDATE
           WHERE EST_CIER = 'A'
             AND COD_REGI = psCodigoRegion
             AND TIP_CIER = 'R'
             AND CAM_PROC = psCodigoPeriodo;
    */
    END IF;
    EXIT WHEN c_consultoras%NOTFOUND;
  END LOOP;
  CLOSE c_consultoras;

  pnTotalErrores := lnTotalErrores;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_PROCE_CIERR_REGIO: (' || ls_sqlerrm);
END MAE_PR_PROCE_CIERR_REGIO;

/***************************************************************************
Descripcion       : Proceso de Facturacion al cierre de Campaña para Evaluacion
                    de Estatus de las Consultoras que no han pasado pedido
Fecha Creacion    : 04/11/2008
Fecha Modificacion: 23/04/2015
Autor             : CSVD-FFVV
***************************************************************************/
PROCEDURE MAE_PR_PROCE_CIERR_CAMPA
  (psCodigoPais              VARCHAR2,
   psCodigoPeriodo           VARCHAR2)

IS
-- Variables
pnTotalErrores NUMBER;
lsindReverEstatusPorAnul bas_param_pais.val_para%TYPE;

CURSOR c_consultoras(oidPais NUMBER) IS
  SELECT uni.CLIE_OID_CLIE, cli.COD_CLIE
    FROM MAE_CLIEN cli,MAE_CLIEN_UNIDA_ADMIN uni,
         ZON_TERRI_ADMIN ter, ZON_SECCI sec, ZON_ZONA zon,
         ZON_REGIO reg, MAE_CLIEN_ESTAT_ACTUA con,
        (SELECT DISTINCT CTS.CLIE_OID_CLIE
        		  FROM  MAE_CLIEN_TIPO_SUBTI CTS,
        		        (SELECT SUB.OID_SUBT_CLIE
        	 		       FROM MAE_PARAM_ESTAT_TISUB ING, MAE_TIPO_CLIEN TIP, MAE_SUBTI_CLIEN SUB
        				  WHERE ING.COD_TIPO_CLIE = TIP.COD_TIPO_CLIE
        				    AND SUB.TICL_OID_TIPO_CLIE = TIP.OID_TIPO_CLIE
        				    AND SUB.COD_SUBT_CLIE = ING.COD_SUBT_CLIE
        				    AND ING.PAIS_COD_PAIS = psCodigoPais
                    AND ING.IND_ACTI = '1'
                    AND ING.EST_REGI = '1') ETC
                  WHERE CTS.SBTI_OID_SUBT_CLIE = ETC.OID_SUBT_CLIE) ets
   WHERE cli.OID_CLIE = uni.CLIE_OID_CLIE
     AND cli.OID_CLIE = con.CLIE_OID_CLIE
     AND ets.CLIE_OID_CLIE = cli.OID_CLIE
     AND uni.ZTAD_OID_TERR_ADMI = ter.OID_TERR_ADMI
     AND ter.ZSCC_OID_SECC = sec.OID_SECC
     AND sec.ZZON_OID_ZONA = zon.OID_ZONA
     AND cli.PAIS_OID_PAIS = oidPais
     AND uni.IND_ACTI = 1
     AND zon.zorg_oid_regi = reg.oid_regi
     AND reg.pais_oid_pais = oidPais
  	 --AND (con.CAM_ULTI_PEDI IS NULL OR con.CAM_ULTI_PEDI < psCodigoPeriodo);
     AND (con.CAM_EVAL_ACTU IS NULL OR con.CAM_EVAL_ACTU < psCodigoPeriodo); -- se cambia esta linea

  TYPE interfazConsultoras IS RECORD
  (
   oidCliente             PED_SOLIC_CABEC.CLIE_OID_CLIE%TYPE,
   codCliente             MAE_CLIEN.COD_CLIE%TYPE
  );

  TYPE interfazConsultorasTab  IS TABLE OF interfazConsultoras;
  interfazRecordC        interfazConsultorasTab;

  lnOidPais              NUMBER;
  lnOidMarca             NUMBER;
  lnOidCanal             NUMBER;
  lnOidPeriodo           NUMBER;
  lnOidPeriodoAnterior   NUMBER;

  lsCodCampanaAnterior     VARCHAR2(6);
  lsCodCampanaEvaluacion   VARCHAR2(6);
  lsCodCampanaUltPedido    VARCHAR2(6);
  lnOidEstatusNuevo        NUMBER;

  lsCodEstatusActual       VARCHAR2(2);
  lsCodEstatusNuevo        VARCHAR2(2);
  lsCodEstatusAnterior     VARCHAR2(2);

  lnNumPeriodosRetiro      NUMBER;
  lnNumPeriodosEgreso      NUMBER;
  lnCantidadPeriodos       NUMBER;
  lbSinErroresHistoEstatus BOOLEAN;

  lnTotalErrores           NUMBER;
  lnTotalErroresAnulacion  NUMBER;
BEGIN
  BEGIN
       SELECT bpp.val_para
         INTO lsindReverEstatusPorAnul
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'MAE'
          AND bpp.nom_para = 'indReverEstatusPorAnula'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsindReverEstatusPorAnul := '0';
  END;

  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);
  lnOidMarca := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
  lnOidCanal := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');
  lnOidPeriodo := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);
  lnTotalErrores := 0;

  --CALCULAMOS LA CAMPAÑA ANTERIOR A LA CAMPAÑA DE PROCESO
  lsCodCampanaAnterior:= GEN_FN_PERIO_ATRAS(psCodigoPeriodo, 1);
  lnOidPeriodoAnterior := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(lsCodCampanaAnterior);

  --Recuperamos Nro Campañas de Periodos de Retiro configurado para el Pais
  SELECT NUM_PERI_RETI
    INTO lnNumPeriodosRetiro
    FROM SEG_PAIS
   WHERE OID_PAIS = lnOidPais;

  --Recuperamos Nro Campañas de Periodos de Egreso configurado para el Pais
  SELECT NUM_PERI_EGRE
    INTO lnNumPeriodosEgreso
    FROM SEG_PAIS
   WHERE OID_PAIS = lnOidPais;

  --PROCESAMOS LAS CONSULTORAS SIN PEDIDO
  OPEN c_consultoras(lnOidPais);
  LOOP
    FETCH c_consultoras BULK COLLECT INTO interfazRecordC LIMIT W_FILAS;
    IF interfazRecordC.COUNT > 0 THEN

      FOR x IN interfazRecordC.FIRST .. interfazRecordC.LAST LOOP
        lsCodCampanaEvaluacion := '';
        lbSinErroresHistoEstatus := TRUE;

        --RECUPERAMOS LA CONSULTORA, DE LA ENTIDAD DE 'ESTATUS CONSULTORA'
        BEGIN
          SELECT CAM_EVAL_ACTU, COD_ESTA_ACTU, CAM_ULTI_PEDI, COD_ESTA_ANTE
            INTO lsCodCampanaEvaluacion, lsCodEstatusActual, lsCodCampanaUltPedido, lsCodEstatusAnterior
            FROM MAE_CLIEN_ESTAT_ACTUA
           WHERE COD_CLIE = interfazRecordC(x).codCliente
             AND PAIS_COD_PAIS = psCodigoPais;

          lsCodEstatusNuevo := lsCodEstatusActual;
        EXCEPTION
          WHEN OTHERS THEN
            lsCodEstatusNuevo := '--';
            lnTotalErrores := lnTotalErrores + 1;

            --INSERTAMOS UN ERROR; SI NO ENCONTRAMOS A LA CONSULTORA
            INSERT INTO MAE_TMP_ESTAT_ERROR
              (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
               TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
            VALUES
              (psCodigoPais, interfazRecordC(x).codCliente, psCodigoPeriodo, NULL,
               PROCESO_CIERRE_CAMPANA, '1', '0', SYSDATE);
        END;

        IF (lsCodEstatusNuevo <> '--') THEN
          IF ((lsCodCampanaEvaluacion IS NULL) OR (lsCodCampanaEvaluacion < psCodigoPeriodo)) THEN
            IF ((lsCodCampanaEvaluacion IS NOT NULL) AND (lsCodCampanaEvaluacion < lsCodCampanaAnterior)) THEN
              lnTotalErrores := lnTotalErrores + 1;

              --INSERTAMOS UN ERROR; SI LA ULTIMA EVALUACION DE ESTATUS ANTERIOR EXCEDE EN UNA CAMPAÑA DE FACTURACION
              INSERT INTO MAE_TMP_ESTAT_ERROR
                (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
                 TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
              VALUES
                (psCodigoPais, interfazRecordC(x).codCliente, psCodigoPeriodo, NULL,
                 PROCESO_CIERRE_CAMPANA, '2', '0', SYSDATE);
            ELSE
                lsCodEstatusNuevo := lsCodEstatusActual; -----se añade
                lsCodEstatusActual := lsCodEstatusAnterior;

              --EVALUAMOS EL ESTATUS DE LA CONSULTORA
              IF (lsCodEstatusActual = ESTADO_REGISTRADA) THEN
                lsCodEstatusNuevo := ESTADO_REGISTRADA;
              ELSIF (lsCodEstatusActual = ESTADO_NUEVA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF (lsCodEstatusActual = ESTADO_NORMAL) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_EGRESANTE) THEN
                lsCodEstatusNuevo := ESTADO_EGRESADA;  
              ELSIF(lsCodEstatusActual = ESTADO_EGRESANTE) THEN
                lsCodEstatusNuevo := ESTADO_EGRESADA;
              ELSIF(lsCodEstatusActual = ESTADO_EGRESADA) THEN
                lnCantidadPeriodos := VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(lsCodCampanaUltPedido,
                                               psCodigoPeriodo, lnOidPais, lnOidMarca, lnOidCanal) - 1;

                IF(lnCantidadPeriodos >= lnNumPeriodosRetiro) THEN
                  lsCodEstatusNuevo := ESTADO_RETIRADA;
                ELSE
                  lsCodEstatusNuevo := ESTADO_EGRESADA;
                END IF;
              ELSIF(lsCodEstatusActual = ESTADO_REINGRESO) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_REACTIVADA) THEN
                lsCodEstatusNuevo := ESTADO_EGRESANTE;
              ELSIF(lsCodEstatusActual = ESTADO_RETIRADA) THEN
                lsCodEstatusNuevo := ESTADO_RETIRADA;
              END IF;
              
              --Recuperamos el Oid estatus
              SELECT OID_ESTA_CLIE
              INTO   lnOidEstatusNuevo
              FROM   MAE_ESTAT_CLIEN
              WHERE  COD_ESTA_CLIE = lsCodEstatusNuevo;

              IF (lsCodEstatusActual <> lsCodEstatusNuevo) THEN
                --ACTUALIZAMOS LA ENTIDAD MAE_HISTO_ESTAT, SI HA HABIDO CAMBIO DE ESTATUS
                MAE_PR_ACTUA_HISTO_ESTAT(interfazRecordC(x).oidCliente, lnOidPeriodo, lnOidPeriodoAnterior, lnOidEstatusNuevo,
                  psCodigoPais, PROCESO_CIERRE_CAMPANA, psCodigoPeriodo, NULL, interfazRecordC(x).codCliente, 'ADMIN-CC', lbSinErroresHistoEstatus);
              END IF;

              IF(lbSinErroresHistoEstatus) THEN
                --Actualizamos la Entidad 'Estatus de Consultora'
                UPDATE MAE_CLIEN_ESTAT_ACTUA
                   SET CAM_EVAL_ANTE = CAM_EVAL_ACTU,
                       COD_ESTA_ANTE = COD_ESTA_ACTU,
                       CAM_EVAL_ACTU = psCodigoPeriodo,
                       COD_ESTA_ACTU = lsCodEstatusNuevo,
                       USU_MODI = 'ADMIN-CC',
                       FEC_MODI = SYSDATE
                 WHERE COD_CLIE = interfazRecordC(x).codCliente
                   AND PAIS_COD_PAIS = psCodigoPais;

                --ACTUALIZAMOS LA ENTIDAD MAE_CLIEN_DATOS_ADICI
                IF(lsCodEstatusActual = ESTADO_EGRESADA AND lsCodEstatusnuevo = ESTADO_RETIRADA) THEN
                  UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                     SET NUM_CAMP_SIN_PEDI = NVL(NUM_CAMP_SIN_PEDI,0) + 1,
                         ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                         IND_ACTI = 0,
                         USU_MODI = 'ADMIN-CC',
                         FEC_MODI = SYSDATE
                   WHERE CLIE_OID_CLIE = interfazRecordC(x).oidCliente;
                ELSE
                  UPDATE MAE_CLIEN_ESTAT_ACTUA_ADICI
                     SET NUM_CAMP_SIN_PEDI = NVL(NUM_CAMP_SIN_PEDI,0) + 1,
                         ESTA_OID_ESTA_CLIE = lnOidEstatusNuevo,
                         USU_MODI = 'ADMIN-CC',
                         FEC_MODI = SYSDATE
                   WHERE CLIE_OID_CLIE = interfazRecordC(x).oidCliente;
                END IF;
              ELSE
                lnTotalErrores := lnTotalErrores + 1;
              END IF;

            END IF;
          END IF;
        END IF;

      END LOOP;

    END IF;
    EXIT WHEN c_consultoras%NOTFOUND;
  END LOOP;
  CLOSE c_consultoras;

  --PARA GARANTIZAR QUE TODAS LAS ANULACIONES DE LA CAMPAÑA, LO EJECUTAMOS AL FINAL DEL CIERRE DE CAMPAÑA
  IF lsindReverEstatusPorAnul = '1' THEN
  MAE_PR_PROCE_ANULA_FACTU(psCodigoPais, psCodigoPeriodo, PROCESO_CIERRE_CAMPANA, NULL, lnTotalErroresAnulacion);
  lnTotalErrores := lnTotalErrores + lnTotalErroresAnulacion;
  END IF;

  pnTotalErrores := lnTotalErrores;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_PROCE_CIERR_CAMPA: (' || ls_sqlerrm);
END MAE_PR_PROCE_CIERR_CAMPA;

/***************************************************************************
Descripcion       : Proceso de Facturacion Diaria para Evaluacion de Estatus
                    de las Consultoras que han pasado pedido, asi como de las
                    consultoras que no han pasado pedido de las zonas facturadas.
Fecha Creacion    : 09/10/2008
Fecha Modificacion: 23/04/2015
Autor Modificacion: CSVD-FFVV
***************************************************************************/
PROCEDURE MAE_PR_ACTUA_HISTO_ESTAT
  (pnOidCliente           NUMBER,
   pnOidPeriodo           NUMBER,
   pnOidPeriodoAnterior   NUMBER,
   pnOidEstatus           NUMBER,
   psCodigoPais           VARCHAR2,
   psTipoProceso          VARCHAR2,
   psCodigoPeriodo        VARCHAR2,
   psFechaFacturacion     VARCHAR2,
   psCodigoCliente        VARCHAR2,
   psCodigoUsuario        VARCHAR2,
   pbSinErrores      OUT  BOOLEAN)
IS
  lnOidHistorico         NUMBER;
  lnOidHistoricoAnte     NUMBER; -- Nuevo
  lnOidEstatusAnterior   NUMBER; -- Nuevo
  lnOidPeriodoInicio     NUMBER;
  lnOidHistoricoSig      NUMBER;
  lnOidEstatusActual     NUMBER; --Nuevo (2)
BEGIN
  pbSinErrores := FALSE;

  SELECT OID_HIST_ESTA, PERD_OID_PERI, ESTA_OID_ESTA_CLIE
   INTO  lnOidHistorico, lnOidPeriodoInicio, lnOidEstatusActual
   FROM  MAE_CLIEN_ESTAT_ACTUA_HISTO
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND PERD_OID_PERI_PERI_FIN IS NULL;

  BEGIN
  SELECT OID_HIST_ESTA, ESTA_OID_ESTA_CLIE
    INTO lnOidHistoricoAnte, lnOidEstatusAnterior
    FROM MAE_CLIEN_ESTAT_ACTUA_HISTO
   WHERE CLIE_OID_CLIE = pnOidCliente
     AND pnOidPeriodoAnterior BETWEEN PERD_OID_PERI AND NVL(PERD_OID_PERI_PERI_FIN,pnOidPeriodoAnterior); -- Nuevo
  EXCEPTION
    WHEN OTHERS THEN
      lnOidEstatusAnterior := NULL;
  END;

  IF (lnOidPeriodoInicio = pnOidPeriodo) THEN
     IF pnOidEstatus = lnOidEstatusAnterior THEN -- Nuevo
        DELETE FROM MAE_CLIEN_ESTAT_ACTUA_HISTO
         WHERE OID_HIST_ESTA = lnOidHistorico;
         --
        UPDATE MAE_CLIEN_ESTAT_ACTUA_HISTO
           SET PERD_OID_PERI_PERI_FIN = NULL,
           USU_MODI = psCodigoUsuario,
           FEC_MODI = SYSDATE
         WHERE OID_HIST_ESTA = lnOidHistoricoAnte;
     ELSE
    UPDATE MAE_CLIEN_ESTAT_ACTUA_HISTO
       SET ESTA_OID_ESTA_CLIE = pnOidEstatus,
           USU_MODI = psCodigoUsuario,
       FEC_MODI = SYSDATE
     WHERE OID_HIST_ESTA = lnOidHistorico;
    END IF;
  ELSE
    --Se añade IF por el cambio de actualizacion de adic e histo al cierre para las sin pedido
  	IF (pnOidEstatus <> lnOidEstatusActual) THEN
    -- Obtenemos el maximo OID --
    SELECT MAX(OID_HIST_ESTA) + 1 INTO lnOidHistoricoSig FROM MAE_CLIEN_ESTAT_ACTUA_HISTO;

    --Insertamos un nuevo historico Estatus
    INSERT INTO MAE_CLIEN_ESTAT_ACTUA_HISTO
      (OID_HIST_ESTA, PERD_OID_PERI, CLIE_OID_CLIE,  PERD_OID_PERI_PERI_FIN, ESTA_OID_ESTA_CLIE, USU_CREA, FEC_CREA)
    VALUES
        (lnOidHistoricoSig, pnOidPeriodo, pnOidCliente, NULL, pnOidEstatus, psCodigoUsuario, SYSDATE);

    --Actualizamos el periodo fin
    UPDATE MAE_CLIEN_ESTAT_ACTUA_HISTO
       SET PERD_OID_PERI_PERI_FIN = pnOidPeriodoAnterior,
         USU_MODI = psCodigoUsuario,
         FEC_MODI = SYSDATE
       WHERE OID_HIST_ESTA = lnOidHistorico;
    ELSE
       UPDATE MAE_CLIEN_ESTAT_ACTUA_HISTO
           SET USU_MODI = psCodigoUsuario,
       FEC_MODI = SYSDATE
     WHERE OID_HIST_ESTA = lnOidHistorico;
  END IF;

  END IF;

  pbSinErrores := TRUE;
EXCEPTION
  WHEN OTHERS THEN
    --INSERTAMOS UN ERROR; SI NO ENCONTRAMOS A LA CONSULTORA
    INSERT INTO MAE_TMP_ESTAT_ERROR
      (COD_PAIS, COD_CLIE, CAM_PROC, FEC_FACT,
       TIP_PROC, COD_ERRO, FLG_ATEN, FEC_PROC)
    VALUES
      (psCodigoPais, psCodigoCliente, psCodigoPeriodo, psFechaFacturacion,
       psTipoProceso, '3', '0', SYSDATE);
END MAE_PR_ACTUA_HISTO_ESTAT;



END MAE_PKG_ESTAT;
/
