CREATE OR REPLACE PACKAGE NVS_PKG_PROGR_NUEVA IS
  --Declaracion de Tipos
  TYPE TIPOCURSOR IS  REF CURSOR;
  --Declaracion de Variables
  ln_sqlcode   NUMBER(10);
  ls_sqlerrm   VARCHAR2(150);
  W_FILAS      NUMBER:=1000;


/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar Clasificación de 1er Nivel Principal,
                    a las Consultoras que Registraron Duplas en la Campaña y que No tienen
                    el Tipo de Clasificación: Programa Duplas.
Fecha Creacion    : 16/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_REGIS_PROGR_DUPLA (
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2);
/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de 2do Nivel a todas
                    las consultoras con Clasificación de 1er Nivel y que han solicitado Kit
                    de Dupla en la campaña de proceso.
Fecha Creacion    : 22/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_1ER_2DO_NIV (
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2);

/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de SIN NIVEL a Todas las Consultoras con Clasificación
                    1er Nivel y que no han solicitado KIT de Duplas en las últimas 3 campañas.
Fecha Creacion    : 25/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_1ER_SIN_NIV(
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2);

/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de 3er Nivel a todas
                    las consultoras que tengan las clasificación de 2do nivel.
Fecha Creacion    : 25/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_2DO_3ER_NIV (
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2);

/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de SIN NIVEL a todas
                    las consultoras que tiene la clasificación de 3er Nivel.
Fecha Creacion    : 25/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_3ER_SIN_NIV (
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2);

  /**************************************************************************
  Descripcion       : Registrar a las consultoras nuevas que cumplen con las 
                      condiciones para acceder a un descuento
  Fecha Creacion    : 16/07/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de marca
    psCodigoCanal    :  Codigo de canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario

  Autor             : Gonzalo Huertas

  ***************************************************************************/
  PROCEDURE NVS_PR_EVALU_DCTO_NUEVA
    (psCodigoPais               VARCHAR2,
     psCodigoMarca              VARCHAR2,
     psCodigoCanal              VARCHAR2,
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2);

END NVS_PKG_PROGR_NUEVA;
/
CREATE OR REPLACE PACKAGE BODY NVS_PKG_PROGR_NUEVA IS
/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar Clasificación de 1er Nivel Principal, 
                    a las Consultoras que Registraron Duplas en la Campaña y que No tienen
                    el Tipo de Clasificación: Programa Duplas.
Fecha Creacion    : 16/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoPais
                    psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_REGIS_PROGR_DUPLA(
  psCodigoCampana varchar2,
  psUsuario VARCHAR2)
IS

BEGIN

  INSERT INTO mae_clien_clasi
   ( OID_CLIE_CLAS,
     CTSU_OID_CLIE_TIPO_SUBT,
     PERD_OID_PERI,
     TCCL_OID_TIPO_CLASI,
     CLAS_OID_CLAS,
     FEC_CLAS,
     IND_PPAL,
     FEC_ULTI_ACTU,
     USU_MODI
    )
    SELECT MAE_CLCL_SEQ.NEXTVAL,
           mcts.oid_clie_tipo_subt,
           per_act.oid_peri,
           clas_1er_Niv.Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
           clas_1er_Niv.Oid_Clas,--clasificacion 1er Nivel
           hij.fec_ingr,
           '1',
           SYSDATE,
           psUsuario--usuario del sistema
      FROM mae_clien mad,
           mae_clien_tipo_subti mcts,
           mae_clien_vincu mcv,
           mae_clien hij,
           (SELECT p.oid_peri,p.fec_inic,p.fec_fina
              FROM cra_perio p,
                   seg_perio_corpo pc
             WHERE p.peri_oid_peri = pc.oid_peri
               AND pc.cod_peri     = psCodigoCampana
           ) per_act,
           (SELECT tcc.oid_tipo_clas,
                   tcc.sbti_oid_subt_clie,
                   mc.oid_clas
              FROM mae_tipo_clasi_clien tcc,
                   mae_clasi mc
             WHERE tcc.cod_tipo_clas = '12' AND
                   tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                   mc.cod_clas       = '01'
           ) clas_1er_Niv
     WHERE mad.oid_clie            = mcts.clie_oid_clie AND
           mcts.ticl_oid_tipo_clie = 2 AND
           mad.oid_clie            = mcv.clie_oid_clie_vnte AND
           hij.oid_clie            = mcv.clie_oid_clie_vndo AND
           mcv.tivc_oid_tipo_vinc  = 1 AND
           mcv.fec_hast IS NULL AND
           hij.fec_ingr BETWEEN per_act.fec_inic AND per_act.fec_fina AND
           NOT EXISTS ( SELECT NULL
                          FROM mae_clien_clasi mcc,
                               mae_tipo_clasi_clien tcc
                         WHERE mcc.ctsu_oid_clie_tipo_subt = mcts.oid_clie_tipo_subt AND
                               tcc.sbti_oid_subt_clie      = mcts.sbti_oid_subt_clie AND
                               mcc.tccl_oid_tipo_clasi     = tcc.oid_tipo_clas AND
                               tcc.cod_tipo_clas       = '12' --tipo clasificacion Programa Dupla KIT
                       ) AND
           mcts.sbti_oid_subt_clie = clas_1er_Niv.Sbti_Oid_Subt_Clie;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);

    RAISE_APPLICATION_ERROR(-20123, 'ERROR NVS_PR_REGIS_PROGR_DUPLA: '||ls_sqlerrm);

END NVS_PR_REGIS_PROGR_DUPLA;
/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de 2do Nivel a todas
                    las consultoras con Clasificación de 1er Nivel y que han solicitado Kit
                    de Dupla en la campaña de proceso.
Fecha Creacion    : 22/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_1ER_2DO_NIV (
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2)
IS

   CURSOR c_clientes(pslistaCUVDuplaKit bas_param_pais.val_para%TYPE) IS
    SELECT mcts.oid_clie_tipo_subt,
           per_act.oid_peri,
           clas_2do_Niv.Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
           clas_2do_Niv.Oid_Clas --clasificacion 1er Nivel
      FROM mae_clien_tipo_subti mcts,
           mae_clien_clasi mcc,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '01'
            ) clas_1er_Niv,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '02'
            ) clas_2do_Niv,
             (SELECT p.oid_peri,p.fec_inic,p.fec_fina
                  FROM cra_perio p,
                       seg_perio_corpo pc
                 WHERE p.peri_oid_peri = pc.oid_peri
                   AND pc.cod_peri     = psCodigoCampana
               ) per_act
     WHERE mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt AND
           mcts.ticl_oid_tipo_clie = clas_1er_Niv.Ticl_Oid_Tipo_Clie AND
           mcts.sbti_oid_subt_clie = clas_1er_Niv.Sbti_Oid_Subt_Clie AND
           mcc.tccl_oid_tipo_clasi = clas_1er_Niv.Oid_Tipo_Clas AND
           mcc.clas_oid_clas       = clas_1er_Niv.Oid_Clas AND
           NOT EXISTS (       SELECT sc.ticl_oid_tipo_clie,
                                     tcc.sbti_oid_subt_clie,
                                     tcc.oid_tipo_clas,
                                     mc.oid_clas
                                FROM mae_clien_tipo_subti cts,
                                     mae_clien_clasi cc,
                                     mae_subti_clien sc,
                                     mae_tipo_clasi_clien tcc,
                                     mae_clasi mc
                               WHERE cts.clie_oid_clie         = mcts.clie_oid_clie AND
                                     cc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt AND
                                     sc.Ticl_Oid_Tipo_Clie     = cts.ticl_oid_tipo_clie AND
                                     tcc.Sbti_Oid_Subt_Clie    = cts.sbti_oid_subt_clie AND
                                     sc.oid_subt_clie          = tcc.sbti_oid_subt_clie AND
                                     tcc.oid_tipo_clas         = cc.tccl_oid_tipo_clasi AND
                                     tcc.cod_tipo_clas         = '12' AND
                                     mc.oid_clas               = cc.clas_oid_clas AND
                                     mc.cod_clas               = '02'
                      ) AND
           EXISTS (   SELECT NULL
                        FROM ped_solic_cabec psc,
                             ped_tipo_solic ts,
                             ped_tipo_solic_pais tsp,
                             ped_solic_posic psp
                       WHERE psc.perd_oid_peri = per_act.oid_peri AND
                             ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli AND
                             ts.cod_tipo_soli = 'SOC' AND
                             psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais AND
                             psc.clie_oid_clie           = mcts.clie_oid_clie AND
                             psc.fec_fact IS NOT NULL AND
                             psp.soca_oid_soli_cabe = psc.oid_soli_cabe AND
                             psp.val_codi_vent IN (pslistaCUVDuplaKit)
                   );

  --se define un tipo de dato tipo Tabla de Registros de la interfaz
  TYPE clieClasRecTab  IS TABLE OF c_clientes%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  clieclasRecord clieClasRecTab;
  --lista de CUV KIT Programa Dupla
  lsListaCUVDuplaKit bas_param_pais.val_para%TYPE;

BEGIN

--obtenemos indicador de bloqueo de reingreso por pais
  BEGIN
       SELECT bpp.val_para
         INTO lsListaCUVDuplaKit
         FROM bas_param_pais bpp
        WHERE bpp.cod_pais = pscodigopais
          AND bpp.cod_sist = 'NVS'
          AND bpp.nom_para = 'listaCUVDuplaKit'
          AND bpp.ind_acti = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lsListaCUVDuplaKit := '00000';
  END;


  -- Abrimos el cursor principal
  OPEN c_clientes(lsListaCUVDuplaKit);
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO clieclasRecord LIMIT w_filas;

        IF  clieclasRecord.COUNT > 0 THEN

          FOR x IN clieclasRecord.FIRST..clieclasRecord.LAST LOOP
            --Eliminamos los niveles anteriores del programa Dupla Kit
            DELETE FROM mae_clien_clasi mcc
                  WHERE mcc.ctsu_oid_clie_tipo_subt = clieclasRecord(x).oid_clie_tipo_subt AND
                        mcc.tccl_oid_tipo_clasi     = clieclasRecord(x).Oid_Tipo_Clas;

            --insertamos la clasificación.
            INSERT INTO mae_clien_clasi
            ( OID_CLIE_CLAS,
              CTSU_OID_CLIE_TIPO_SUBT,
              PERD_OID_PERI,
              TCCL_OID_TIPO_CLASI,
              CLAS_OID_CLAS,
              FEC_CLAS,
              IND_PPAL,
              FEC_ULTI_ACTU,
              USU_MODI
                )
            VALUES
            ( MAE_CLCL_SEQ.NEXTVAL,
              clieclasRecord(x).oid_clie_tipo_subt,
              clieclasRecord(x).oid_peri,
              clieclasRecord(x).Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
              clieclasRecord(x).Oid_Clas,--clasificacion 1er Nivel
              trunc(SYSDATE),
              '1',
              SYSDATE,
              psUsuario--usuario del sistema
             );

          END LOOP;

        END IF;

      EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);

    RAISE_APPLICATION_ERROR(-20123, 'ERROR NVS_PR_ACTUA_1ER_2DO_NIV: '||ls_sqlerrm);

END NVS_PR_ACTUA_1ER_2DO_NIV;

/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de SIN NIVEL a Todas
                    las Consultoras con Clasificación 1er Nivel y que no han solicitado KIT
                    de Duplas en las últimas 3 campañas..
Fecha Creacion    : 25/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_1ER_SIN_NIV(
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2)
IS

   CURSOR c_clientes(psOidPeriInicKit cra_perio.oid_peri%TYPE) IS
    SELECT mcts.oid_clie_tipo_subt,
           per_act.oid_peri,
           clas_Sin_Niv.Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
           clas_Sin_Niv.Oid_Clas --clasificacion 1er Nivel
      FROM mae_clien_tipo_subti mcts,
           mae_clien_clasi mcc,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '01'
            ) clas_1er_Niv,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '04'
            ) clas_Sin_Niv,
             (SELECT p.oid_peri,p.fec_inic,p.fec_fina
                  FROM cra_perio p,
                       seg_perio_corpo pc
                 WHERE p.peri_oid_peri = pc.oid_peri
                   AND pc.cod_peri     = psCodigoCampana
               ) per_act
     WHERE mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt AND
           mcts.ticl_oid_tipo_clie = clas_1er_Niv.Ticl_Oid_Tipo_Clie AND
           mcts.sbti_oid_subt_clie = clas_1er_Niv.Sbti_Oid_Subt_Clie AND
           mcc.tccl_oid_tipo_clasi = clas_1er_Niv.Oid_Tipo_Clas AND
           mcc.clas_oid_clas       = clas_1er_Niv.Oid_Clas AND
           mcc.ind_ppal            = '1' AND
           mcc.perd_oid_peri       < per_act.oid_peri AND
           mcc.perd_oid_peri       =  psOidPeriInicKit;

  --se define un tipo de dato tipo Tabla de Registros de la interfaz
  TYPE clieClasRecTab  IS TABLE OF c_clientes%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  clieclasRecord clieClasRecTab;
  --variable campaña de inicio al programa kit
  lnOidPeriodo cra_perio.oid_peri%TYPE;

BEGIN
  --obtenemos la campaña limite de pedido KIT
  BEGIN
       SELECT gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(gen_pkg_gener.gen_fn_perio_nsigu(psCodigoPais,psCodigoCampana,-2))
         INTO lnOidPeriodo
         FROM dual;
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN lnOidPeriodo := 0 ;
  END;

  -- Abrimos el cursor principal
  OPEN c_clientes(lnOidPeriodo);
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO clieclasRecord LIMIT w_filas;

        IF  clieclasRecord.COUNT > 0 THEN

          FOR x IN clieclasRecord.FIRST..clieclasRecord.LAST LOOP
            --Eliminamos los niveles de programa dupla kit anteriores
            DELETE FROM mae_clien_clasi mcc
                  WHERE mcc.ctsu_oid_clie_tipo_subt = clieclasRecord(x).oid_clie_tipo_subt AND
                        mcc.tccl_oid_tipo_clasi     = clieclasRecord(x).Oid_Tipo_Clas;

            --insertamos la clasificación.
            INSERT INTO mae_clien_clasi
            ( OID_CLIE_CLAS,
              CTSU_OID_CLIE_TIPO_SUBT,
              PERD_OID_PERI,
              TCCL_OID_TIPO_CLASI,
              CLAS_OID_CLAS,
              FEC_CLAS,
              IND_PPAL,
              FEC_ULTI_ACTU,
              USU_MODI
                )
            VALUES
            ( MAE_CLCL_SEQ.NEXTVAL,
              clieclasRecord(x).oid_clie_tipo_subt,
              clieclasRecord(x).oid_peri,
              clieclasRecord(x).Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
              clieclasRecord(x).Oid_Clas,--clasificacion Sin Nivel
              trunc(SYSDATE),
              '1',
              SYSDATE,
              psUsuario--usuario del sistema
             );

          END LOOP;

        END IF;

      EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);

    RAISE_APPLICATION_ERROR(-20123, 'ERROR NVS_PR_ACTUA_1ER_SIN_NIV: '||ls_sqlerrm);

END NVS_PR_ACTUA_1ER_SIN_NIV;

/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de 3er Nivel a todas
                    las consultoras que tengan las clasificación de 2do nivel.
Fecha Creacion    : 25/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_2DO_3ER_NIV (
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2)
IS

   CURSOR c_clientes IS
    SELECT mcts.oid_clie_tipo_subt,
           per_act.oid_peri,
           clas_3er_Niv.Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
           clas_3er_Niv.Oid_Clas --clasificacion 1er Nivel
      FROM mae_clien_tipo_subti mcts,
           mae_clien_clasi mcc,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '02'
            ) clas_2do_Niv,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '03'
            ) clas_3er_Niv,
             (SELECT p.oid_peri,p.fec_inic,p.fec_fina
                  FROM cra_perio p,
                       seg_perio_corpo pc
                 WHERE p.peri_oid_peri = pc.oid_peri
                   AND pc.cod_peri     = psCodigoCampana
               ) per_act
     WHERE mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt AND
           mcts.ticl_oid_tipo_clie = clas_2do_Niv.Ticl_Oid_Tipo_Clie AND
           mcts.sbti_oid_subt_clie = clas_2do_Niv.Sbti_Oid_Subt_Clie AND
           mcc.tccl_oid_tipo_clasi = clas_2do_Niv.Oid_Tipo_Clas AND
           mcc.clas_oid_clas       = clas_2do_Niv.Oid_Clas AND
           mcc.perd_oid_peri       < per_act.oid_peri AND 
           mcc.ind_ppal            = '1';

  --se define un tipo de dato tipo Tabla de Registros de la interfaz
  TYPE clieClasRecTab  IS TABLE OF c_clientes%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  clieclasRecord clieClasRecTab;
  --variable campaña de inicio al programa kit
  lnOidPeriodo cra_perio.oid_peri%TYPE;

BEGIN


  -- Abrimos el cursor principal
  OPEN c_clientes;
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO clieclasRecord LIMIT w_filas;

        IF  clieclasRecord.COUNT > 0 THEN

          FOR x IN clieclasRecord.FIRST..clieclasRecord.LAST LOOP
            --Eliminamos los niveles de programa dupla kit anteriores
            DELETE FROM mae_clien_clasi mcc
                  WHERE mcc.ctsu_oid_clie_tipo_subt = clieclasRecord(x).oid_clie_tipo_subt AND
                        mcc.tccl_oid_tipo_clasi     = clieclasRecord(x).Oid_Tipo_Clas;

            --insertamos la clasificación.
            INSERT INTO mae_clien_clasi
            ( OID_CLIE_CLAS,
              CTSU_OID_CLIE_TIPO_SUBT,
              PERD_OID_PERI,
              TCCL_OID_TIPO_CLASI,
              CLAS_OID_CLAS,
              FEC_CLAS,
              IND_PPAL,
              FEC_ULTI_ACTU,
              USU_MODI
                )
            VALUES
            ( MAE_CLCL_SEQ.NEXTVAL,
              clieclasRecord(x).oid_clie_tipo_subt,
              clieclasRecord(x).oid_peri,
              clieclasRecord(x).Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
              clieclasRecord(x).Oid_Clas,--clasificacion Sin Nivel
              trunc(SYSDATE),
              '1',
              SYSDATE,
              psUsuario--usuario del sistema
             );

          END LOOP;

        END IF;

      EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);

    RAISE_APPLICATION_ERROR(-20123, 'ERROR NVS_PR_ACTUA_2DO_3ER_NIV: '||ls_sqlerrm);

END NVS_PR_ACTUA_2DO_3ER_NIV;
/****************************************************************************************
Descripcion       : Proceso que se encarga de Asignar la Clasificación de SIN NIVEL a todas
                    las consultoras que tiene la clasificación de 3er Nivel.
Fecha Creacion    : 25/01/2013
Autor             : CSVD - FFVV
Parametros        : psCodigoCampana
                    psUsuario
*****************************************************************************************/
PROCEDURE NVS_PR_ACTUA_3ER_SIN_NIV (
  psCodigoPais VARCHAR2,
  psCodigoCampana VARCHAR2,
  psUsuario VARCHAR2)
IS

   CURSOR c_clientes IS
    SELECT mcts.oid_clie_tipo_subt,
           per_act.oid_peri,
           clas_Sin_Niv.Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
           clas_Sin_Niv.Oid_Clas --clasificacion 1er Nivel
      FROM mae_clien_tipo_subti mcts,
           mae_clien_clasi mcc,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '03'
            ) clas_3er_Niv,
           (  SELECT sc.ticl_oid_tipo_clie,
                     tcc.sbti_oid_subt_clie,
                     tcc.oid_tipo_clas,
                     mc.oid_clas
                FROM mae_subti_clien sc,
                     mae_tipo_clasi_clien tcc,
                     mae_clasi mc
               WHERE sc.oid_subt_clie  = tcc.sbti_oid_subt_clie AND
                     tcc.cod_tipo_clas = '12' AND
                     tcc.oid_tipo_clas = mc.tccl_oid_tipo_clas AND
                     mc.cod_clas       = '04'
            ) clas_Sin_Niv,
             (SELECT p.oid_peri,p.fec_inic,p.fec_fina
                  FROM cra_perio p,
                       seg_perio_corpo pc
                 WHERE p.peri_oid_peri = pc.oid_peri
                   AND pc.cod_peri     = psCodigoCampana
               ) per_act
     WHERE mcts.oid_clie_tipo_subt = mcc.ctsu_oid_clie_tipo_subt AND
           mcts.ticl_oid_tipo_clie = clas_3er_Niv.Ticl_Oid_Tipo_Clie AND
           mcts.sbti_oid_subt_clie = clas_3er_Niv.Sbti_Oid_Subt_Clie AND
           mcc.tccl_oid_tipo_clasi = clas_3er_Niv.Oid_Tipo_Clas AND
           mcc.clas_oid_clas       = clas_3er_Niv.Oid_Clas AND
           mcc.perd_oid_peri       < per_act.oid_peri AND
           mcc.ind_ppal            = '1';

  --se define un tipo de dato tipo Tabla de Registros de la interfaz
  TYPE clieClasRecTab  IS TABLE OF c_clientes%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  clieclasRecord clieClasRecTab;
  --variable campaña de inicio al programa kit
  lnOidPeriodo cra_perio.oid_peri%TYPE;

BEGIN


  -- Abrimos el cursor principal
  OPEN c_clientes;
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO clieclasRecord LIMIT w_filas;

        IF  clieclasRecord.COUNT > 0 THEN

          FOR x IN clieclasRecord.FIRST..clieclasRecord.LAST LOOP
            --Eliminamos los niveles de programa dupla kit anteriores
            DELETE FROM mae_clien_clasi mcc
                  WHERE mcc.ctsu_oid_clie_tipo_subt = clieclasRecord(x).oid_clie_tipo_subt AND
                        mcc.tccl_oid_tipo_clasi     = clieclasRecord(x).Oid_Tipo_Clas;

            --insertamos la clasificación.
            INSERT INTO mae_clien_clasi
            ( OID_CLIE_CLAS,
              CTSU_OID_CLIE_TIPO_SUBT,
              PERD_OID_PERI,
              TCCL_OID_TIPO_CLASI,
              CLAS_OID_CLAS,
              FEC_CLAS,
              IND_PPAL,
              FEC_ULTI_ACTU,
              USU_MODI
                )
            VALUES
            ( MAE_CLCL_SEQ.NEXTVAL,
              clieclasRecord(x).oid_clie_tipo_subt,
              clieclasRecord(x).oid_peri,
              clieclasRecord(x).Oid_Tipo_Clas,--tipo clasificacion Programa Duplas
              clieclasRecord(x).Oid_Clas,--clasificacion Sin Nivel
              trunc(SYSDATE),
              '1',
              SYSDATE,
              psUsuario--usuario del sistema
             );

          END LOOP;

        END IF;

      EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);

    RAISE_APPLICATION_ERROR(-20123, 'ERROR NVS_PR_ACTUA_3ER_SIN_NIV: '||ls_sqlerrm);

END NVS_PR_ACTUA_3ER_SIN_NIV;

/**************************************************************************
  Descripcion       : Registrar a las consultoras nuevas que cumplen con las 
                      condiciones para acceder a un descuento
  Fecha Creacion    : 16/07/2014
  Parametros Entrada:
    psCodigoPais     :  Codigo de pais
    psCodigoMarca    :  Codigo de marca
    psCodigoCanal    :  Codigo de canal
    psCodigoPeriodo  :  Codigo de periodo
    psCodigoUsuario  :  Codigo de Usuario

  Autor             : Gonzalo Huertas

  ***************************************************************************/
PROCEDURE NVS_PR_EVALU_DCTO_NUEVA (
     psCodigoPais               VARCHAR2,
     psCodigoMarca              VARCHAR2,
     psCodigoCanal              VARCHAR2,
     psCodigoPeriodo            VARCHAR2,
     psCodigoUsuario            VARCHAR2)
IS

    lnoidperiodo      NUMBER;
    lnoidpais         NUMBER;
    lnoidcanal        NUMBER;
    lnoidmarca        NUMBER;
    variab            varchar2(10);
    
    --variables a calcular
    lncodigoprograma  varchar2(3);
    lncodigozona      varchar2(4);
    lncodigoregion    varchar2(2);
    lnmontoventaexig  number(12,2);
    lnmontodescuento  number(12,2);
    lnindinex         varchar2(1);
    lnnroinex         number(5);
    lnnroorde         number(3);
    lnnrouadm         number(5):=0;
    vsCodVent         VARCHAR2(5):='';
    vnCantVenta       number(1):=1;
    vnPosic           NUMBER(10);
    vnOidDetalleOferta NUMBER(12);
    vnImpPrecPosi      NUMBER(12,2);
    vnOidProducto      NUMBER(12);
    vnSubTipoPosi      NUMBER(12);   
    lsIndCuv          VARCHAR2(5); 
    

   CURSOR c_clientes IS
    WITH TEMP AS (
                SELECT clie.cod_clie,
                       clie.oid_clie,
                       clhe.esta_oid_esta_clie,
                       SUM(CASE WHEN peri2.cod_peri=GEN_FN_CALCU_PERIO (psCodigoPeriodo, -3) THEN 1 ELSE 0 END) ind_pedi_camp_1,
                       SUM(CASE WHEN peri2.cod_peri=GEN_FN_CALCU_PERIO (psCodigoPeriodo, -2) THEN 1 ELSE 0 END) ind_pedi_camp_2,
                       SUM(CASE WHEN peri2.cod_peri=GEN_FN_CALCU_PERIO (psCodigoPeriodo, -1) THEN 1 ELSE 0 END) ind_pedi_camp_3,
                       SUM(CASE WHEN peri2.cod_peri=GEN_FN_CALCU_PERIO (psCodigoPeriodo, -0) THEN 1 ELSE 0 END) ind_pedi_camp_4,
                       SUM(CASE WHEN peri2.cod_peri>=peri.cod_peri THEN 1 ELSE 0 END) val_nume_peds,
                       SUM(CASE WHEN peri2.cod_peri=psCodigoPeriodo THEN sca2.val_mont_tota ELSE 0 END) val_mont_tota,
                       peri.cod_peri val_camp_prim_pedi,
                       perd.pais_oid_pais,
                       perd.marc_oid_marc,
                       perd.cana_oid_cana,
                       pedi.oid_soli_cabe
                  FROM mae_clien_histo_estat clhe,
                       mae_clien clie,
                       cra_perio perd,
                       seg_perio_corpo peri,
                       (
                          SELECT soca.clie_oid_clie, soca.oid_soli_cabe, cons.val_nume_soli, cons.val_tota_paga_loca, cons.esso_oid_esta_soli
                            FROM ped_solic_cabec soca,
                                 ped_solic_cabec cons,
                                 ped_tipo_solic_pais tspa,
                                 ped_tipo_solic tsol
                           WHERE 1=1
                             AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe(+)
                             AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
                             AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
                             AND soca.perd_oid_peri = lnOidPeriodo
                             AND soca.grpr_oid_grup_proc = 3 -- Esto debe ser 3 ( para evaluar pedidos en gp3 )
                             AND tsol.cod_tipo_soli = 'SOC' 
                       ) pedi,
                       ped_solic_cabec_acum2 sca2,
                       cra_perio perd2,
                       seg_perio_corpo peri2
                 WHERE clhe.clie_oid_clie = sca2.clie_oid_clie(+)
                   AND sca2.perd_oid_peri = perd2.oid_peri(+)
                   AND perd2.peri_oid_peri = peri2.oid_peri(+)
                   AND clhe.clie_oid_clie = pedi.clie_oid_clie
                   AND clhe.clie_oid_clie = clie.oid_clie
                   AND clhe.perd_oid_peri = perd.oid_peri
                   AND perd.peri_oid_peri = peri.oid_peri
                   AND clhe.esta_oid_esta_clie IN (2,8)
                   AND peri.cod_peri >= GEN_FN_CALCU_PERIO (psCodigoPeriodo, -3)
                   AND peri2.cod_peri BETWEEN GEN_FN_CALCU_PERIO (psCodigoPeriodo, -3) AND psCodigoPeriodo
                 GROUP BY clie.cod_clie,
                          clie.oid_clie,
                          clhe.esta_oid_esta_clie,
                          peri.cod_peri,
                          perd.pais_oid_pais,
                          perd.marc_oid_marc,
                          perd.cana_oid_cana,
                          pedi.oid_soli_cabe
             )
SELECT cod_clie,
       oid_clie,
       esta_oid_esta_clie,
       ind_pedi_camp_1,
       ind_pedi_camp_2,
       ind_pedi_camp_3,
       ind_pedi_camp_4,
       val_nume_peds,
       oid_soli_cabe,
       CASE WHEN VEN_PKG_REPOR.VEN_FN_DEVUE_NUME_CAMPA(psCodPeriodoIni => val_camp_prim_pedi,
                                                       psCodPeriodoFin => psCodigoPeriodo,
                                                       pnIdPais => lnoidpais,
                                                       pnIdMarca => lnOidMarca,
                                                       pnIdCanal => lnOidCanal) = val_nume_peds THEN 1 ELSE 0 END ind_cons_pedi,
       val_mont_tota,
       val_camp_prim_pedi,
       cn.cod_prog as cod_prog,
       pd.mon_vent_exig as mon_vent_exig,
       pd.mon_desc as mon_desc,
       pd.nive_cod_nive as cod_nivel
       
  FROM temp,
       CUP_CONSU_NUEVA cn,
       CUP_PROG_NUEVA_CUPON pn,
       NVS_PARAM_DESCU pd
 where cn.cod_cons = cod_clie
   and cn.camp_ini_ccc <=  psCodigoPeriodo
   and (cn.camp_fin_ccc >=  psCodigoPeriodo or cn.camp_fin_ccc is null)
   and cn.cod_pais = psCodigoPais
   and pn.cod_pais = cn.cod_pais
   and cn.cod_prog = pn.cod_prog
   and pn.ind_esta <> 'C'
   and pd.pais_cod_pais = cn.cod_pais
   and pd.prog_cod_prog = cn.cod_prog
   and pd.nive_cod_nive = '0' || TO_CHAR(val_nume_peds)
   and pd.cam_inic_vige <= psCodigoPeriodo
   and (pd.cam_fina_vige >= psCodigoPeriodo 
    or pd.cam_fina_vige is null)
   and cod_clie not in (select ncd.cod_Clie from NVS_CLIEN_DESCU ncd
                 where ncd.cam_proc = psCodigoPeriodo 
                   and ncd.cam_anul is null
                   and ncd.est_desc = 'P'
                   and ncd.est_regi = 1); 

   CURSOR c_desc_anul (lnoidperiodo varchar2) IS 
    SELECT DISTINCT clie.oid_clie oidCliente,
           clie.cod_clie codCliente ,
           ncd.PROG_COD_PROG codProg ,
           ncd.num_orde numOrden
      FROM ped_solic_cabec x,
           ped_solic_cabec soca,
           ped_tipo_solic_pais tsp,
           ped_tipo_solic ts,
           mae_clien clie,
           cra_perio p,
           NVS_CLIEN_DESCU ncd
    WHERE x.soca_oid_docu_refe      = soca.oid_soli_cabe
      AND soca.clie_oid_clie        = clie.oid_clie
      AND x.perd_oid_peri           = lnoidperiodo
      AND soca.perd_oid_peri        = lnoidperiodo
      AND x.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
      AND tsp.tsol_oid_tipo_soli    = ts.oid_tipo_soli
      AND ts.cod_tipo_soli          IN ('SDAA','SDAN')
      AND x.fec_fact                BETWEEN p.fec_inic AND p.fec_fina
      AND ncd.cod_clie              = clie.cod_clie
      AND ncd.cam_proc              = psCodigoPeriodo   
      AND ncd.est_desc              = 'P'
      AND 0 = (
         SELECT COUNT(*)
          FROM ped_solic_cabec pdx,
               ped_solic_cabec sc2
         WHERE pdx.soca_oid_soli_cabe = sc2.oid_soli_cabe
           AND pdx.tspa_oid_tipo_soli_pais = 2075 --SOC
           AND sc2.tspa_oid_tipo_soli_pais = 2043 --C1
           AND sc2.esso_oid_esta_soli  <> 4
           AND sc2.clie_oid_clie = clie.oid_clie
           AND sc2.perd_oid_peri = lnoidperiodo
           AND sc2.fec_fact IS NOT NULL
         ); 

  --se define un tipo de dato tipo Tabla de Registros de la interfaz
  TYPE clieClasRecTab  IS TABLE OF c_clientes%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  clieclasRecord clieClasRecTab;

  --se define un tipo de dato tipo Tabla de Registros de la interfaz
  TYPE clieAnulRecTab  IS TABLE OF c_desc_anul%ROWTYPE;
  --se define una variable de tipo de dato de tabla personalizada con el registro de cada linea de la interfaz
  clieanulRecord clieAnulRecTab;

BEGIN
  
  -- Obtenemos los OIDs de los datos necesarios para el proceso
    lnoidpais    := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais);
    lnoidmarca   := gen_pkg_gener.gen_fn_devuelve_id_marca(psCodigoMarca);
    lnoidcanal   := gen_pkg_gener.gen_fn_devuelve_id_canal(psCodigoCanal);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(psCodigoPeriodo,
                                                               lnoidmarca,
                                                               lnoidcanal);

    SELECT OID_SUBT_POSI 
    INTO vnSubTipoPosi
    FROM   PED_SUBTI_POSIC
    WHERE  COD_SUBT_POSI = 'PN';
  
  -- Abrimos el cursor clientes
  OPEN c_desc_anul(lnoidperiodo);
    LOOP
      FETCH c_desc_anul BULK COLLECT
        INTO clieanulRecord LIMIT w_filas;

        IF  clieanulRecord.COUNT > 0 THEN

          FOR x IN clieanulRecord.FIRST..clieanulRecord.LAST LOOP

          UPDATE NVS_CLIEN_DESCU C
          SET EST_DESC = 'A',
              CAM_ANUL = psCodigoPeriodo,
              USU_MODI = psCodigoUsuario,
              FEC_MODI = sysdate
        WHERE PAIS_COD_PAIS = psCodigoPais
          AND PROG_COD_PROG =  clieanulRecord(x).codProg 
          AND COD_CLIE =  clieanulRecord(x).codCliente
          AND CAM_PROC = psCodigoPeriodo
          AND NUM_ORDE = clieanulRecord(x).numOrden;

          END LOOP;

        END IF;

      EXIT WHEN c_desc_anul%NOTFOUND;
    END LOOP;

  -- Abrimos el cursor clientes
  OPEN c_clientes;
    LOOP
      FETCH c_clientes BULK COLLECT
        INTO clieclasRecord LIMIT w_filas;

        IF  clieclasRecord.COUNT > 0 THEN

          FOR x IN clieclasRecord.FIRST..clieclasRecord.LAST LOOP
            
          IF (clieclasRecord(x).ind_cons_pedi = 1) THEN  
           --obtenemos la zona y la region
            SELECT nvl(zon_zona.cod_zona,''),
                   nvl(zon_regio.cod_regi,'')
              INTO lncodigozona,
                   lncodigoregion
              FROM mae_clien,
                   mae_clien_unida_admin,
                   zon_terri_admin,
                   zon_secci,
                   zon_zona,
                   zon_regio
             WHERE ((zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi) AND
                   (zon_secci.oid_secc = zon_terri_admin.zscc_oid_secc) AND
                   (zon_zona.oid_zona = zon_secci.zzon_oid_zona) AND
                   (zon_regio.oid_regi = zon_zona.zorg_oid_regi) AND
                   (mae_clien.oid_clie = mae_clien_unida_admin.clie_oid_clie) AND
                   (mae_clien_unida_admin.ind_acti = '1') AND (mae_clien.oid_clie = clieclasRecord(x).oid_clie) AND
                   (rownum = 1));
                   
              select count(1)
                into lnnrouadm
                from NVS_PARAM_DESCU_UNADM
               where pais_cod_pais = psCodigoPais
                 and prog_cod_prog = clieclasRecord(x).cod_prog; 
                 
              select count(1)
                into lnnroinex
                from NVS_PARAM_DESCU_UNADM
               where pais_cod_pais = psCodigoPais
                 and prog_cod_prog = clieclasRecord(x).cod_prog
                 and cod_regi = lncodigoregion
                 and (cod_zona is null 
                  or cod_zona = lncodigozona)
                 and est_regi <>9 ;
              
             
              if (lnnroinex = 0) then
                lnindinex:= '3';
              else   
              --obtenemos el indice
              select ind_excl
                  into lnindinex
                from NVS_PARAM_DESCU_UNADM
               where pais_cod_pais = psCodigoPais
                   and prog_cod_prog = clieclasRecord(x).cod_prog
                 and cod_regi = lncodigoregion
                   and (cod_zona is null 
                    or cod_zona      = lncodigozona)
                   and est_regi <>9;
              end if;
                 
               --insertamos indice lnindeexcl(el valor debe ser igual a "1") y el lnmontoventaexig<=val_mont_tota
               if ((trim(lnindinex)<>'1' or  lnnrouadm = 0) and clieclasRecord(x).mon_vent_exig <= clieclasRecord(x).val_mont_tota) then
                
                   SELECT NVL(max(NUM_ORDE),1) 
                   INTO lnnroorde
                   FROM NVS_CLIEN_DESCU
                   WHERE PAIS_COD_PAIS =  psCodigoPais
                   AND   PROG_COD_PROG =  clieclasRecord(x).cod_prog
                   AND   COD_CLIE      =  clieclasRecord(x).cod_clie
                   AND   CAM_PROC      =  psCodigoPeriodo;
               
                   IF (lnnroorde = 0 ) THEN
                      lnnroorde:= lnnroorde + 1;  
                   END IF;
          
                   INSERT INTO NVS_CLIEN_DESCU
                   (PAIS_COD_PAIS,
                    PROG_COD_PROG,
                    COD_CLIE,
                    CAM_PROC,
                    NUM_ORDE,
                    CAM_ASIG,
                    MON_DESC,
                    NIVE_COD_NIVE,
                    EST_DESC,
                    USU_CREA,
                    FEC_CREA,
                    EST_REGI)
                    values
                    (psCodigoPais,
                    clieclasRecord(x).cod_prog,
                    clieclasRecord(x).cod_clie,
                    psCodigoPeriodo,
                    lnnroorde,
                    GEN_PKG_GENER.gen_fn_perio_nsigu(psCodigoPais, psCodigoPeriodo, '1'),
                    clieclasRecord(x).mon_desc,
                    clieclasRecord(x).cod_nivel,
                    'P',
                    psCodigoUsuario,
                    sysdate,
                    '1');  
                
                BEGIN    
                   SELECT BPS.VAL_PARA
                     INTO lsIndCuv
                     FROM BAS_PARAM_PAIS bps
                    WHERE bps.COD_PAIS = psCodigoPais
                      AND bps.NOM_PARA = 'indCUVOblig';
                 EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                          lsIndCuv := '0';
                END;
                
               IF (lsIndCuv = '1'  ) THEN     
                
                BEGIN    
                    SELECT pd.COD_VENT 
                    INTO vsCodVent
                    FROM NVS_PARAM_DESCU pd
                    WHERE pd.prog_cod_prog = clieclasRecord(x).cod_prog
                    AND pd.est_regi <> '9'
                    AND pd.nive_cod_nive = clieclasRecord(x).cod_nivel
                    AND pd.cam_inic_vige <= psCodigoPeriodo
                    AND (pd.cam_fina_vige >= psCodigoPeriodo
                     OR pd.cam_fina_vige is null );
                 EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                          vsCodVent := NULL;
                END;
                
                BEGIN
                 SELECT MAX(NVL(pi.cod_posi,0))
                   INTO vnPosic 
                   FROM ped_solic_posic pi
                  WHERE pi.soca_oid_soli_Cabe = clieclasRecord(x).oid_soli_cabe   ;
                  EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                          vnPosic := 0;
                END;

                BEGIN
                  SELECT ofedet.oid_deta_ofer,
                         ofedet.imp_prec_posi,
                         prod.oid_prod
                    INTO vnOidDetalleOferta,
                         vnImpPrecPosi,
                         vnOidProducto
                    FROM pre_ofert ofe,
                         pre_ofert_detal ofedet,
                         pre_matri_factu mf,
                         pre_matri_factu_cabec mfc,
                         mae_produ prod
                   WHERE mfc.perd_oid_peri     = lnoidperiodo
                     AND mf.mfca_oid_cabe      = mfc.oid_cabe
                     AND ofe.mfca_oid_cabe     = mfc.oid_cabe
                     AND ofe.oid_ofer          = ofedet.ofer_oid_ofer
                     AND ofedet.oid_deta_ofer  = mf.ofde_oid_deta_ofer
                     AND ofedet.val_codi_vent  = vsCodVent
                     AND ofedet.prod_oid_prod  = prod.oid_prod;
                    
                 EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                         vnOidDetalleOferta :=0;
                         vnImpPrecPosi := 0;
                         vnOidProducto:= 0;
                   END;      
                
                  vnPosic := vnPosic  +1;
                  

                  
                         INSERT INTO PED_SOLIC_POSIC (
                                OID_SOLI_POSI,
                                COD_POSI,
                                NUM_UNID_DEMA,
                                NUM_UNID_POR_ATEN,
                                NUM_UNID_COMPR,
                                NUM_UNID_DEMA_REAL,
                                VAL_TASA_IMPU,
                                SOCA_OID_SOLI_CABE,
                                TPOS_OID_TIPO_POSI,
                                PROD_OID_PROD,
                                VAL_PREC_CATA_UNIT_LOCA,
                                VAL_PREC_CONT_UNIT_LOCA,
                                VAL_PREC_CATA_UNIT_DOCU,
                                VAL_PREC_CONTA_UNIT_DOCU,
                                ESPO_OID_ESTA_POSI,
                                STPO_OID_SUBT_POSI,
                                VAL_CODI_VENT,
                                OFDE_OID_DETA_OFER)
                        VALUES (PED_SOPO_SEQ.NEXTVAL,
                                vnPosic,
                                vnCantVenta,
                                vnCantVenta,
                                vnCantVenta,
                                vnCantVenta,
                                0,
                                clieclasRecord(x).oid_soli_cabe, --vnOidSolicCabec,
                                9,
                                vnOidProducto, 
                                0,
                                vnImpPrecPosi, 
                                0,
                                vnImpPrecPosi, 
                                4,
                                vnSubTipoPosi,
                                vsCodVent,
                                vnOidDetalleOferta );
                                
                     END IF ; 
               END IF;
            END IF;
          END LOOP;
        END IF;
      EXIT WHEN c_clientes%NOTFOUND;
    END LOOP;

  EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,1000);

    RAISE_APPLICATION_ERROR(-20123, 'ERROR NVS_PR_EVALU_DCTO_NUEVA: '||ls_sqlerrm);

END NVS_PR_EVALU_DCTO_NUEVA;
END NVS_PKG_PROGR_NUEVA;
/
