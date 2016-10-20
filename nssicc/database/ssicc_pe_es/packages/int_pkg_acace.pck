CREATE OR REPLACE PACKAGE INT_PKG_ACACE IS

  /* Declaracion de Variables */
  LN_SQLCODE NUMBER(10);
  LS_SQLERRM VARCHAR2(150);

  PROCEDURE ACC_PR_VALID_RECOM_PREMI(psClienteRecomendante IN VARCHAR2,
                                     psTipoDocIdentidad    IN VARCHAR2,
                                     psCodigoConcurso      IN VARCHAR2,
                                     pnNumeroVersion       IN NUMBER,
                                     pnNivel               IN NUMBER,
                                     pnNumeroPremio        IN NUMBER,
                                     psCodigoCampania      IN VARCHAR2,
                                     psResultado           OUT VARCHAR2,
                                     psMensajeResultado    OUT VARCHAR2);

  FUNCTION ACC_FN_EXIST_TIPO_DOCUM(psTipoDocIdentidad  VARCHAR2,
                                   devuelveValorNoData BOOLEAN := FALSE)
    RETURN NUMBER;

  FUNCTION ACC_FN_EXIST_CLIEN(psTipoDocIdentidad   VARCHAR2,
                              psNumeroDocIdentidad VARCHAR2,
                              devuelveValorNoData  BOOLEAN := FALSE)
    RETURN NUMBER;

  FUNCTION ACC_FN_DEVUELVE_ID_PARA_GRAL(psCodigoConcurso    VARCHAR2,
                                        pnNumeroVersion     NUMBER,
                                        devuelveValorNoData BOOLEAN := FALSE)
    RETURN NUMBER;

  FUNCTION ACC_FN_DEVUELVE_ID_NIVE_PREM(pnOidConcurso       NUMBER,
                                        pnNivel             NUMBER,
                                        pnNumeroPremio      NUMBER,
                                        devuelveValorNoData BOOLEAN := FALSE)
    RETURN NUMBER;

  PROCEDURE ACC_PR_ACTUA_CONCU_RECOM(psCodigoPais       VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psResultado        OUT VARCHAR2,
                                     psMensajeResultado OUT VARCHAR2);


/**************************************************************************
  Descripcion        : Devuelve el codigo, descripcion de la clasificaion
                       de la consutyora
  Fecha Creacion     : 24/05/2008
  Autor              : Jose Cairampoma
  ***************************************************************************/
FUNCTION acc_fn_devue_clasi_clien
(
  pscodigopais   VARCHAR2,
  pstiporetorno  VARCHAR2,
  pscodigoacceso VARCHAR2,
  pnoidcliente   NUMBER
) RETURN VARCHAR2;

/****************************************************************************
Descripcion       : Genera Interfaz de Envio de Clientes (ACC-1) Completo
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 06/03/2013
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
Autor: CSVD - FFVV 
*****************************************************************************/
PROCEDURE INT_PR_ACC_ENVIO_CLIEN
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psTipoEnvio      VARCHAR2
  );

/****************************************************************************
Descripcion       : Genera Interfaz de Envio de Clientes (ACC-1) Novedades
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 06/03/2013
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
Autor: CSVD - FFVV 
*****************************************************************************/
 PROCEDURE INT_PR_ACC_ENVIO_CLIEN_NOVED
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

/****************************************************************************
Descripcion       : Genera Interfaz de Envio de CDR's (ACC-3)
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 06/03/2013
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
 psFechaFacturacion : Fecha de Facturacion
Autor: CSVD - FFVV 
*****************************************************************************/
  PROCEDURE INT_PR_ACC_ENVIO_CDR
  (
   pscodigopais     VARCHAR2,
                                   pscodigosistema  VARCHAR2,
                                   pscodigointerfaz VARCHAR2,
                                   psnombrearchivo  VARCHAR2,
   pscodigoPeriodo  VARCHAR2,
   psFechaFacturacion   VARCHAR2
  );
  
/****************************************************************************
Descripcion       : Recepcionar Recomendates y Recomendadas(ACC-4)
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 06/03/2013
Parametros:

Autor: CSVD - FFVV 
*****************************************************************************/

PROCEDURE INT_PR_ACC_RECEP_RECTE_RECDA
  (
   pscod_pais      IN VARCHAR2,
                                          pscod_comp      IN VARCHAR2,
                                          pscod_clie_rete IN VARCHAR2,
                                          pstip_docu_iden IN VARCHAR2,
                                          psnum_docu_iden IN VARCHAR2,
                                          psnum_conc      IN VARCHAR2,
                                          psnum_vers      IN VARCHAR2,
                                          psnum_prem      IN VARCHAR2,
                                          psnum_nive      IN VARCHAR2,
                                          pscod_camp_soli IN VARCHAR2,
                                          psind_asig_prem IN VARCHAR2,
                                          pscod_clie_reco IN VARCHAR2,
                                          psfec_ingr      IN VARCHAR2,
                                          pshor_ingr      IN VARCHAR2,
                                          psusu_proc      IN VARCHAR2,
   psind_proc      IN VARCHAR2
  );

END INT_PKG_ACACE;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_ACACE IS

  /**************************************************************************
  Descripcion       : Valida si el cliente recomendante existe, si hay cliente para el tipo y
                      numero de documento de identidad, si existe el concurso, si existe la campania
                      si existe el premio
  Fecha Creacion     : 30/09/2008
  Parametros Entrada:
    psClienteRecomendante  :  Cliente Recomendante
    psTipoDocIdentidad     :  Tipo documento identidad
    psNumeroDocIdentidad   :  Numero documento identidad
    psCodigoConcurso       :  Codigo Concurso
    pnNumeroVersion     :  Version del concurso
    psCodigoCampania    :  Campaña
    psResultado         :  resultado de validacion, OK (bien), ER (en otro caso)
    psMensajeResultado  :  mensaje de los problemas encontrados

  Autor             : Sergio Apaza

  ***************************************************************************/
  PROCEDURE ACC_PR_VALID_RECOM_PREMI(psClienteRecomendante IN VARCHAR2,
                                     psTipoDocIdentidad    IN VARCHAR2,
                                     psCodigoConcurso      IN VARCHAR2,
                                     pnNumeroVersion       IN NUMBER,
                                     pnNivel               IN NUMBER,
                                     pnNumeroPremio        IN NUMBER,
                                     psCodigoCampania      IN VARCHAR2,
                                     psResultado           OUT VARCHAR2,
                                     psMensajeResultado    OUT VARCHAR2) IS

    lnIdCampana  NUMBER;
    lnIdConcurso NUMBER;
    lnIdCliente  NUMBER;
    lnIdNivel    NUMBER;

    lsMensajeResultado VARCHAR2(500);
  BEGIN
    lsMensajeResultado := 'ERRORES :';

    --Validamos que existe el cliente Recomendante
    lnIdCliente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CLIENTE(psClienteRecomendante,
                                                            true);
    IF (lnIdCliente = -1) THEN
      lsMensajeResultado := lsMensajeResultado ||
                            'Cliente Recomendante no Encontrado. ';
    END IF;

    --Validamos que existe el tipo de documento
    lnIdCliente := ACC_FN_EXIST_TIPO_DOCUM(psTipoDocIdentidad, true);
    IF (lnIdCliente = -1) THEN
      lsMensajeResultado := lsMensajeResultado ||
                            'Tipo de documento de identidad No Existe. ';
    END IF;

    --Validamos que existe el concurso y numero de version
    lnIdConcurso := ACC_FN_DEVUELVE_ID_PARA_GRAL(psCodigoConcurso,
                                                 pnNumeroVersion,
                                                 true);
    IF (lnIdConcurso = -1) THEN
      lsMensajeResultado := lsMensajeResultado ||
                            'Concurso no Encontrado. ';
    ELSE
      --Validamos que existe el nivel y premio
      lnIdNivel := ACC_FN_DEVUELVE_ID_NIVE_PREM(lnIdConcurso,
                                                pnNivel,
                                                pnNumeroPremio,
                                                true);
      IF (lnIdNivel = -1) THEN
        lsMensajeResultado := lsMensajeResultado ||
                              'Premio no Encontrado para el Nivel ingresado. ';
      END IF;
    END IF;

    --Validamos que la campaña se encuentre en la tabla seg_perio_corpo
    lnIdCampana := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoCampania,
                                                               true);
    IF (lnIdCampana = -1) THEN
      lsMensajeResultado := lsMensajeResultado || 'Campaña no Encontrada. ';
    END IF;

    IF (lsMensajeResultado = 'ERRORES :') THEN
      psResultado := 'OK';
    ELSE
      psResultado        := 'ER';
      psMensajeResultado := lsMensajeResultado;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      psResultado        := 'ER';
      psMensajeResultado := substr(SQLERRM, 1, 250);
  END;

  /**************************************************************************
  Descripcion        : Devuelve Id Tipo Documento Identidad
  Fecha Creacion     : 01/10/2008
  Parametros Entrada :
             psTipoDocIdentidad : Tipo documento de identidad
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION ACC_FN_EXIST_TIPO_DOCUM(psTipoDocIdentidad  VARCHAR2,
                                   devuelveValorNoData BOOLEAN := FALSE)
    RETURN NUMBER IS
    ln_id_oid MAE_TIPO_DOCUM.OID_TIPO_DOCU%TYPE;
  BEGIN
    /* Obteniendo id de tipo documento */
    SELECT a.oid_tipo_docu
      INTO ln_id_oid
      FROM MAE_TIPO_DOCUM a
     WHERE a.cod_tipo_docu = psTipoDocIdentidad;
    RETURN ln_id_oid;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelveValorNoData) THEN
        ls_sqlerrm := 'No se encontro Tipo Doc. Identidad: ' ||
                      psTipoDocIdentidad;
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_EXIST_TIPO_DOCUM: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_EXIST_TIPO_DOCUM: ' ||
                                ls_sqlerrm);
      END IF;
  END ACC_FN_EXIST_TIPO_DOCUM;

  /**************************************************************************
  Descripcion        : Devuelve Id Cliente
  Fecha Creacion     : 30/09/2008
  Parametros Entrada :
             psTipoDocIdentidad : Tipo documento de identidad
             psNumeroDocIdentidad : Numero documento de identidad
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION ACC_FN_EXIST_CLIEN(psTipoDocIdentidad   VARCHAR2,
                              psNumeroDocIdentidad VARCHAR2,
                              devuelveValorNoData  BOOLEAN := FALSE)
    RETURN NUMBER IS
    ln_id_oid MAE_CLIEN_IDENT.OID_CLIE_IDEN%TYPE;
  BEGIN
    /* Obteniendo id de periodo */
    SELECT a.clie_oid_clie
      INTO ln_id_oid
      FROM MAE_CLIEN_IDENT a
     WHERE a.tdoc_oid_tipo_docu = psTipoDocIdentidad
       AND a.num_docu_iden = psNumeroDocIdentidad;
    RETURN ln_id_oid;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelveValorNoData) THEN
        ls_sqlerrm := 'No se encontro ID de Cliente con Tipo Doc. Identidad: ' ||
                      psTipoDocIdentidad || ', numero Doc. Identidad: ' ||
                      psNumeroDocIdentidad;
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_EXIST_CLIEN: ' || ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_EXIST_CLIEN: ' || ls_sqlerrm);
      END IF;
  END ACC_FN_EXIST_CLIEN;


  /**************************************************************************
  Descripcion        : Devuelve Id de Periodo de TABLA INC_CONCU_PARAM_GENER
  Fecha Creacion     : 30/09/2008
  Parametros Entrada :
             psCodigoConcurso : Codigo de Concurso
             pnNumeroVersion : Numero Version
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION ACC_FN_DEVUELVE_ID_PARA_GRAL(psCodigoConcurso    VARCHAR2,
                                        pnNumeroVersion     NUMBER,
                                        devuelveValorNoData BOOLEAN := FALSE)
    RETURN NUMBER IS
    ln_id_para_gral INC_CONCU_PARAM_GENER.Oid_Para_Gral%TYPE;
  BEGIN
    /* Obteniendo id de periodo */
    SELECT a.oid_para_gral
      INTO ln_id_para_gral
      FROM INC_CONCU_PARAM_GENER a
     WHERE a.bcal_oid_base_calc = 4
       AND a.num_conc = psCodigoConcurso
       AND a.num_vers = pnNumeroVersion;
    RETURN ln_id_para_gral;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelveValorNoData) THEN
        ls_sqlerrm := 'No se encontro ID de Concurso y version respectivo con Codigo: ' ||
                      psCodigoConcurso || ', version: ' ||
                      TO_CHAR(pnNumeroVersion);
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_DEVUELVE_ID_PARA_GRAL: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_DEVUELVE_ID_PARA_GRAL: ' ||
                                ls_sqlerrm);
      END IF;
  END ACC_FN_DEVUELVE_ID_PARA_GRAL;

  /**************************************************************************
  Descripcion        : Devuelve Id Nivel Premio
  Fecha Creacion     : 01/10/2008
  Parametros Entrada :
             pnOidConcurso  : Oid Concurso
             pnNivel        : Nivel de Premiacion
             pnNumeroPremio : Numero de Premio
             devuelveValorNoData: Si no se encuentra valor por NO_DATA_FOUND:
                                  Si es true : devuelve -1
                                  Si es false: devuelve Excepcion (Por defecto)
  Autor              : Sergio Apaza
  ***************************************************************************/
  FUNCTION ACC_FN_DEVUELVE_ID_NIVE_PREM(pnOidConcurso       NUMBER,
                                        pnNivel             NUMBER,
                                        pnNumeroPremio      NUMBER,
                                        devuelveValorNoData BOOLEAN := FALSE)
    RETURN NUMBER IS
    ln_id_oid MAE_CLIEN_IDENT.OID_CLIE_IDEN%TYPE;
  BEGIN
    /* Obteniendo id nivel premio */
    SELECT pnp.oid_para_nive_prem
      INTO ln_id_oid
      FROM inc_concu_param_gener pg,
           inc_param_gener_premi pgp,
           inc_param_nivel_premi pnp,
           inc_premi_artic       pa,
           inc_lote_premi_artic  lpa
     WHERE pg.oid_para_gral = pnOidConcurso
       AND pgp.copa_oid_para_gral = pg.oid_para_gral
       AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
       AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
       AND lpa.prar_oid_prem_arti = pa.oid_prem_arti
       AND pnp.num_nive = pnNivel
       AND lpa.num_prem = pnNumeroPremio;
    RETURN ln_id_oid;
  EXCEPTION
    WHEN no_data_found THEN
      IF (NOT devuelveValorNoData) THEN
        ls_sqlerrm := 'No se encontro Premio: ' || TO_CHAR(pnNumeroPremio) ||
                      ', para el Nivel: ' || TO_CHAR(pnNivel);
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_DEVUELVE_ID_NIVE_PREM: ' ||
                                ls_sqlerrm);
      ELSE
        RETURN - 1;
      END IF;
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      IF ln_sqlcode < 0 THEN
        RAISE_APPLICATION_ERROR(-20123,
                                'ERROR ACC_FN_DEVUELVE_ID_NIVE_PREM: ' ||
                                ls_sqlerrm);
      END IF;
  END ACC_FN_DEVUELVE_ID_NIVE_PREM;


/****************************************************************************
Descripcion       : Genera Interfaz de Envio de Concursos y Premios (ACC-2)
Fecha Creacion    : 01/10/2008
Fecha Edicion     : 02/08/2011
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 psResultado  	  : Resultado (salida)
 psMensajeResultado: Mensaje Resultado (salida)
Autor:
*****************************************************************************/
  PROCEDURE ACC_PR_ACTUA_CONCU_RECOM(psCodigoPais       VARCHAR2,
                                     psCodigoPeriodo    VARCHAR2,
                                     psResultado        OUT VARCHAR2,
                                     psMensajeResultado OUT VARCHAR2) IS
    i                  integer:=0;
    varExistConsultora integer;
    W_FILAS            NUMBER := 1000;

    varCodigoPeriodoDesp VARCHAR2(6);
    varFecIngMC          date;
    varCount             number;
    varOidCliReco        number(12);
    varOidCliRete        number(12);
    varOidTipVinc        number(12);
    varIdNivelPrem       number(12);
    varCampannaVigente   number;
    varIdGral            number;
    varCountCliRec       number;
    varCountRect         number;
    varCodClieMC         varchar2(15);
    varOidCliVinc        number(12);
    varOidClieRete       number(12);
    varOidClieReteNEXT   number(12);
    varOidCliRedo        number(12);
    varPeriodoReco       VARCHAR2(6);
    varCodClie           VARCHAR2(15);

    CURSOR C_CARGA is
      SELECT IA.TIP_DOCU_IDEN,
             IA.NUM_DOCU_IDEN,
             INT_PKG_ACACE.ACC_FN_EXIST_TIPO_DOCUM(IA.TIP_DOCU_IDEN) AS OID_TIP_DOCU,
             INT_PKG_ACACE.ACC_FN_DEVUELVE_ID_PARA_GRAL(IA.NUM_CONC,
                                                        IA.NUM_VERS) AS CAMPANNA,
             IA.NUM_CONC,
             IA.NUM_VERS,
             IA.FEC_INGR,
             IA.COD_CLIE_RETE,
             IA.COD_CAMP_SOLI,
             IA.COD_CLIE_RECO,
             IA.NUM_PREM,
             IA.NUM_NIVE
        FROM INT_ACC_RCDTE_PREMI IA
       WHERE IA.IND_PROC = '0'
         AND IA.TIP_DOCU_IDEN = '01'
         AND LENGTH(IA.NUM_DOCU_IDEN) < 11
         AND IA.COD_CAMP_SOLI >= GEN_FN_PERIO_ATRAS(psCodigoPeriodo, 1)
         AND IA.COD_CAMP_SOLI <= psCodigoPeriodo;

    TYPE RecPremioRol IS RECORD(
      TIP_DOCU_IDEN varchar2(2),
      NUM_DOCU_IDEN varchar2(10),
      OID_TIP_DOCU  NUMBER,
      CAMPANNA      NUMBER,
      NUM_CONC      varchar2(6),
      NUM_VERS      number(3),
      FEC_INGR      date,
      COD_CLIE_RETE varchar2(15),
      COD_CAMP_SOLI varchar2(6),
      COD_CLIE_RECO varchar2(15),
      NUM_PREM      number(4),
      NUM_NIVE      number(4));

    TYPE recPremioRolTab IS TABLE OF RecPremioRol;
    recPremioRolcord recPremioRolTab;

  BEGIN
    varCampannaVigente := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CRA_PERIO2(psCodigoPeriodo);

    OPEN C_CARGA;
    LOOP
      FETCH C_CARGA BULK COLLECT
        INTO recPremioRolcord LIMIT W_FILAS;
      IF recPremioRolcord.COUNT > 0 THEN
        FOR x IN recPremioRolcord.FIRST .. recPremioRolcord.LAST LOOP
          I         := I + 1;
          varIdGral := INT_PKG_ACACE.ACC_FN_DEVUELVE_ID_PARA_GRAL(recPremioRolcord(x)
                                                                  .NUM_CONC,
                                                                  recPremioRolcord(x)
                                                                  .NUM_VERS);

          DBMS_OUTPUT.PUT_LINE('TIP_DOCU_IDEN: ' || recPremioRolcord(x)
                               .TIP_DOCU_IDEN);

          --Verificamos si existe cod_clie_reco  cliente recomendada
          BEGIN
            SELECT COUNT(1), MC.CLIE_OID_CLIE, M.COD_CLIE
              INTO varExistConsultora, varOidCliReco, varCodClie  --varOidCliReco varClieOidClie
              FROM MAE_CLIEN_IDENT MC, MAE_CLIEN M
             WHERE
             M.OID_CLIE = MC.CLIE_OID_CLIE
             AND MC.TDOC_OID_TIPO_DOCU = recPremioRolcord(x)
            .OID_TIP_DOCU
               AND MC.NUM_DOCU_IDEN = recPremioRolcord(x)
            .NUM_DOCU_IDEN
             group by MC.CLIE_OID_CLIE,M.COD_CLIE;
          EXCEPTION
            WHEN NO_DATA_FOUND THEN
              varExistConsultora := 0;
          END;

          IF varExistConsultora > 0 THEN
            DBMS_OUTPUT.PUT_LINE('varExistConsultora: ' ||
                                 varExistConsultora);
            ---varRecoxxx = varClieOidClie

            SELECT a.cod_peri
              into varCodigoPeriodoDesp
              FROM seg_perio_corpo a, cra_perio b, seg_canal c, seg_marca d
             WHERE b.oid_peri =
                   (SELECT IPG.PERD_OID_PERI
                      FROM INC_PARAM_GENER_PREMI IPG
                     WHERE IPG.COPA_OID_PARA_GRAL = varIdGral)
               AND a.oid_peri = b.peri_oid_peri
               and b.CANA_OID_CANA = c.OID_CANA
               and b.MARC_OID_MARC = d.OID_MARC
               and c.COD_CANA = 'VD'
               and d.COD_MARC = 'T';

            select a.cod_peri
              into varPeriodoReco
                FROM seg_perio_corpo a, cra_perio b,
                     mae_clien_prime_conta me
               WHERE me.perd_oid_peri = b.oid_peri
                     and b.peri_oid_peri = a.oid_peri
                     and me.clie_oid_clie = varOidCliReco;

            IF psCodigoPeriodo < varCodigoPeriodoDesp
            and recPremioRolcord(x).COD_CAMP_SOLI = varPeriodoReco THEN
              --varwww = varCodClieMC
              SELECT  MCX.FEC_CREA, mcx.cod_clie  -- OLD MCX.FEC_INGR
                into  varFecIngMC, varCodClieMC ---
                FROM MAE_CLIEN MCX
               WHERE MCX.OID_CLIE = varOidCliReco; --

              SELECT MCX.OID_CLIE
                into varOidCliRete
                FROM MAE_CLIEN MCX
               WHERE MCX.COD_CLIE = recPremioRolcord(x).cod_clie_rete;

              SELECT MTV.OID_TIPO_VINC
                INTO varOidTipVinc
                FROM MAE_TIPO_VINCU MTV
               WHERE MTV.IND_RECO = 1;

              IF recPremioRolcord(x).FEC_INGR <= varFecIngMC THEN
                DBMS_OUTPUT.PUT_LINE('varCodigoPeriodoDesp: ' ||
                                     varCodigoPeriodoDesp);

                -- 5A --
                BEGIN
                  SELECT count(1), MCV.OID_CLIE_VINC
                    INTO varCount, varOidCliVinc
                    FROM MAE_CLIEN_VINCU MCV
                   WHERE MCV.TIVC_OID_TIPO_VINC = varOidTipVinc
                     AND MCV.CLIE_OID_CLIE_VNDO = varOidCliReco --varClieOidClie
                   GROUP BY MCV.OID_CLIE_VINC; -- varRecoxxx --varOidCliReco;

                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    varCount := 0;
                END;

                IF varCount > 0 THEN
                 --varRecoxxx --varOidCliReco;

                  UPDATE MAE_CLIEN_VINCU MCV
                     SET MCV.CLIE_OID_CLIE_VNTE = varOidCliRete
                   WHERE MCV.OID_CLIE_VINC = varOidCliVinc;

                ELSE

                  INSERT INTO MAE_CLIEN_VINCU MCV
                    (MCV.OID_CLIE_VINC,
                     MCV.CLIE_OID_CLIE_VNDO,
                     MCV.TIVC_OID_TIPO_VINC,
                     MCV.FEC_DESD,
                     MCV.FEC_HAST,
                     MCV.IND_VINC_PPAL,
                     MCV.FEC_ULTI_ACTU,
                     clie_oid_clie_vnte
                     )
                  VALUES
                    (MAE_CVIN_SEQ.NEXTVAL,
                     varOidCliReco,
                     varOidTipVinc,
                     trunc(SYSDATE),
                     add_months(trunc(SYSDATE), 12),
                     '1',
                     SYSDATE,
                     varOidCliRete
                     );

                END IF;

                -- 5C --
                BEGIN
                  SELECT COUNT(1), ICR.OID_CLIE_RETE
                    INTO varCountRect, varOidClieRete
                    FROM INC_CLIEN_RECTE ICR
                   WHERE ICR.CLIE_OID_CLIE = varOidCliRete --no sera CLIE_OID_CLIE ???
                     AND ICR.COPA_OID_PARA_GRAL = varIdGral
                   GROUP BY ICR.OID_CLIE_RETE;
                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    varCountRect := 0;
                END;
              --CLIE_OID_CLIE, COPA_OID_PARA_GRAL
                if varCountRect = 0 then

                  select INC_CLR3_SEQ.NEXTVAL
                    into varOidClieReteNEXT
                    from dual;

                  INSERT INTO INC_CLIEN_RECTE
                    (oid_clie_rete,
                     ind_fin_vinc,
                     clie_oid_clie,
                     copa_oid_para_gral,
                     ind_eval)
                  VALUES
                    (varOidClieReteNEXT, NULL, varOidCliRete, varIdGral, null);

                  ---                       varOidClieRete := INC_CLRE_SEQ.CURRVAL;
                end if;
                -- 5B --
                varIdNivelPrem := INT_PKG_ACACE.ACC_FN_DEVUELVE_ID_NIVE_PREM(varIdGral,
                                                                             recPremioRolcord(x)
                                                                             .NUM_NIVE,
                                                                             recPremioRolcord(x)
                                                                             .NUM_PREM);

                BEGIN
                  SELECT COUNT(1), ICR.OID_CLIE_REDO
                    INTO varCountCliRec, varOidCliRedo
                    FROM INC_CLIEN_RECDO ICR
                   WHERE ICR.CLIE_OID_CLIE = varOidCliReco
                     AND ICR.PANP_OID_PARA_NIVE_PREM = varIdNivelPrem
                   GROUP BY ICR.OID_CLIE_REDO;

                EXCEPTION
                  WHEN NO_DATA_FOUND THEN
                    varCountCliRec := 0;
                END;

                IF varCountCliRec > 0 THEN

                  UPDATE INC_CLIEN_RECDO ICR
                     SET ICR.CLR3_OID_CLIE_RETE = varOidClieRete
                   WHERE ICR.OID_CLIE_REDO = varOidCliRedo;

                ELSE

                  INSERT INTO INC_CLIEN_RECDO ICR
                    (OID_CLIE_REDO,
                     clr3_oid_clie_rete,
                     clie_oid_clie,
                     perd_oid_peri,
                     panp_oid_para_nive_prem,
                     num_prem)
                  VALUES
                    (INC_CLRE_SEQ.NEXTVAL,
                     varOidClieReteNEXT, --varHHH,
                     varOidCliReco,
                     varCampannaVigente,
                     varIdNivelPrem,
                     recPremioRolcord(x).NUM_PREM);
                END IF;

                UPDATE INT_ACC_RCDTE_PREMI IA
                   SET IA.IND_PROC      = '1',
                       IA.IND_ASIG_PREM = '1',
                       ia.cod_clie_reco = varCodClie --varOidCliReco --varwww  old varCodClieMC
                 WHERE IA.TIP_DOCU_IDEN = recPremioRolcord(x)
                .TIP_DOCU_IDEN
                   AND IA.NUM_DOCU_IDEN = recPremioRolcord(x)
                .NUM_DOCU_IDEN
                   AND IA.COD_CAMP_SOLI = recPremioRolcord(x)
                .COD_CAMP_SOLI
                   AND IA.COD_CLIE_RETE = recPremioRolcord(x)
                .COD_CLIE_RETE;

                ---------------------------------------------------

              ELSIF recPremioRolcord(x).FEC_INGR > varFecIngMC THEN
                DBMS_OUTPUT.PUT_LINE('varCodigoPeriodoDesp: ' ||
                                     varCodigoPeriodoDesp);

                UPDATE INT_ACC_RCDTE_PREMI IA
                   SET IA.IND_PROC      = '1',
                       IA.IND_ASIG_PREM = '0',
                       ia.cod_clie_reco = varCodClie --varOidCliReco --varwww old varCodClieMC
                 WHERE IA.TIP_DOCU_IDEN = recPremioRolcord(x)
                .TIP_DOCU_IDEN
                   AND IA.NUM_DOCU_IDEN = recPremioRolcord(x)
                .NUM_DOCU_IDEN
                   AND IA.COD_CAMP_SOLI = recPremioRolcord(x)
                .COD_CAMP_SOLI
                   AND IA.COD_CLIE_RETE = recPremioRolcord(x)
                .COD_CLIE_RETE;

              END IF;

            ELSE

              UPDATE INT_ACC_RCDTE_PREMI IA
                 SET IA.IND_PROC      = '1',
                     IA.IND_ASIG_PREM = '0',
                     ia.cod_clie_reco = varCodClie --varCodClieMC --varwww
               WHERE IA.TIP_DOCU_IDEN = recPremioRolcord(x)
              .TIP_DOCU_IDEN
                 AND IA.NUM_DOCU_IDEN = recPremioRolcord(x)
              .NUM_DOCU_IDEN
                 AND IA.COD_CAMP_SOLI = recPremioRolcord(x)
              .COD_CAMP_SOLI
                 AND IA.COD_CLIE_RETE = recPremioRolcord(x)
              .COD_CLIE_RETE;

            END IF;
          END IF;
        END LOOP;
      END IF;
      EXIT WHEN C_CARGA%NOTFOUND;
    END LOOP;
    CLOSE C_CARGA;

  EXCEPTION
    WHEN OTHERS THEN
      psResultado        := 'ER';
      psMensajeResultado := substr(SQLERRM, 1, 250);
  END;


/****************************************************************************
  Descripcion        : Devuelve el codigo, descripcion de la clasificaion
                       de la consutyora
  Fecha Creacion     : 24/05/2008
  Autor              : Jose Cairampoma
  ***************************************************************************/
FUNCTION ACC_FN_DEVUE_CLASI_CLIEN
(
  pscodigopais   VARCHAR2,
  pstiporetorno  VARCHAR2,
  pscodigoacceso VARCHAR2,
  pnoidcliente   NUMBER
) RETURN VARCHAR2 IS

  lsretorno v_gen_i18n_sicc.val_i18n%TYPE;

BEGIN
  SELECT decode(pstiporetorno,
                'COD_CLAS',
                mcls.cod_clas,
                'DES_CLAS',
                (SELECT idio.val_i18n
                   FROM v_gen_i18n_sicc idio
                  WHERE idio.attr_enti = 'MAE_CLASI'
                    AND idio.idio_oid_idio = 1
                    AND idio.val_oid = mcls.oid_clas),
                NULL)
    INTO lsretorno
    FROM mae_clien_clasi      cc,
         mae_clien_tipo_subti cts,
         mae_clasi            mcls,
         mae_tipo_clasi_clien mtcls,
         mae_subti_clien      mst,
         mae_tipo_clien       mt,
         int_acc_corpo_param  iacc
   WHERE cc.ctsu_oid_clie_tipo_subt = cts.oid_clie_tipo_subt
     AND cts.clie_oid_clie = pnoidcliente
     AND mcls.oid_clas = cc.clas_oid_clas
     AND mtcls.oid_tipo_clas = cc.tccl_oid_tipo_clasi
     AND mst.oid_subt_clie = cts.sbti_oid_subt_clie
     AND mt.oid_tipo_clie = cts.ticl_oid_tipo_clie
     AND mtcls.cod_tipo_clas = iacc.val_dato
     AND iacc.cod_pais = pscodigopais
     AND iacc.cod_acce = pscodigoacceso;

  RETURN lsretorno;
EXCEPTION
  WHEN no_data_found THEN

    RETURN NULL;

  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);
    IF ln_sqlcode < 0 THEN
      raise_application_error(-20123,
                              'ERROR ACC_FN_DEVUE_CASIF: ' || ls_sqlerrm);
    END IF;
END ACC_FN_DEVUE_CLASI_CLIEN;


/****************************************************************************
Descripcion       : Genera Interfaz de Envio de Clientes (ACC-1) 
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 06/08/2014 
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
 psTipoEnvio      : N: Novedad, C:Completo 
Autor: CSVD - FFVV 
*****************************************************************************/
PROCEDURE INT_PR_ACC_ENVIO_CLIEN
(
   psCodigoPais     VARCHAR2,
   psCodigoSistema  VARCHAR2,
   psCodigoInterfaz VARCHAR2,
   psNombreArchivo  VARCHAR2,
   psTipoEnvio      VARCHAR2
) IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  ln_id_pais NUMBER;
   w_filas    NUMBER := 1000;

  lverror VARCHAR2(40) := 'x';
  /*valores para el txt*/
  lbabrirutlfile BOOLEAN;

  /* Variables usadas para la generacion del archivo de texto */
  lsdirtempo bas_inter.dir_temp%TYPE;
  v_handle   utl_file.file_type;

  lslinea         VARCHAR2(1000);
  lsnombrearchivo VARCHAR2(50);
   varfecproceso   VARCHAR2(10);

   -- Declaracion de Estructuras
  codpais           VARCHAR2(3);
  codcompania       VARCHAR2(2);
  codtipocliente    VARCHAR2(2);
  codcliente        VARCHAR2(15);
  apppaterno        VARCHAR2(25);
  appmaterno        VARCHAR2(25);
  prinombre         VARCHAR2(25);
  segnombre         VARCHAR2(25);
  codsubgerencia    VARCHAR2(2);
  codregion         VARCHAR2(2);
  codzona           VARCHAR2(4);
  codseccion        VARCHAR2(1);
  codterritorio     VARCHAR2(6);
  fecnacimiento     VARCHAR2(8);
  tipdocidentidad   VARCHAR2(2);
  numdocidentidad   VARCHAR2(10);
  direccion         VARCHAR2(60);
  barcliente        VARCHAR2(30);
  telefono          VARCHAR2(10);
  desvalorventa     VARCHAR2(15);
  campanaingreso    VARCHAR2(6);
  estcivil          VARCHAR2(2);
  corelectronico    VARCHAR2(60);
  estatusconsultora VARCHAR2(2);

  CURSOR c_carga
  (
    ln_id_pais NUMBER,
     varfecproceso VARCHAR2,
    vscadena   VARCHAR2,
    vsreplace  VARCHAR2
  ) IS
   
    WITH
    tempZonRegio AS
     (
      SELECT re.*
     FROM zon_regio re
     WHERE re.ind_acti = '1'
         AND re.ind_borr = '0'
     ),
     tempZonZona AS
     (
      SELECT zo.*
      FROM zon_zona zo
      WHERE zo.ind_acti = '1'
         AND zo.ind_borr = '0'
     ),
     tempZonSecc AS
     (
      SELECT se.*
      FROM zon_secci se
      WHERE se.ind_acti = '1'
         AND se.ind_borr = '0'
     )
     SELECT psCodigoPais AS codpais,
           (
            SELECT val_pain 
              FROM bas_param_inter 
              WHERE pais_cod_pais = psCodigoPais
                AND sist_cod_sist = psCodigoSistema
                AND inte_cod_inte = psCodigoInterfaz
                AND nom_pain = 'codigoCompania'
           ) AS codcompania,
           cl.cod_clie AS codcliente,
            SUBSTR(TRIM(translate(cl.val_ape1,vscadena,vsreplace)),1,25) AS apppaterno,
            SUBSTR(TRIM(translate(cl.val_ape2,vscadena,vsreplace)),1,25) AS appmaterno,
            SUBSTR(TRIM(translate(cl.val_nom1,vscadena,vsreplace)),1,25) AS prinombre,
            SUBSTR(TRIM(translate(cl.val_nom2,vscadena,vsreplace)),1,25) AS segnombre,
           (
            SELECT cod_subg_vent 
              FROM zon_sub_geren_venta 
             WHERE pais_oid_pais = ln_id_pais
           ) AS codsubgerencia,
           (
            CASE
                WHEN (
                      SELECT COUNT(1)
                        FROM tempZonRegio tmpReg
                       WHERE tmpReg.clie_oid_clie = cl.oid_clie) > 0 THEN
                        (SELECT cod_regi FROM tempZonRegio WHERE clie_oid_clie = cl.oid_clie and rownum<=1)
            ELSE
                reg.cod_regi
            END
           ) AS codregion,
           (
            CASE
                WHEN (
                      SELECT count(1)
                        FROM tempZonZona tmpZon
                       WHERE tmpZon.clie_oid_clie = cl.oid_clie) > 0 THEN
                        (SELECT cod_zona FROM tempZonZona WHERE clie_oid_clie = cl.oid_clie AND rownum<=1)
            ELSE
                zo.cod_zona
            END
           ) AS codzona,
           (
            CASE
                WHEN (
                      SELECT count(1)
                        FROM tempZonSecc tmpSec
                       WHERE tmpSec.clie_oid_clie = cl.oid_clie) > 0 THEN
                        (SELECT cod_secc FROM tempZonSecc WHERE clie_oid_clie = cl.oid_clie AND rownum<=1)
            ELSE
                se.cod_secc
            END
           ) AS codseccion,
           te.cod_terr AS codterritorio,
            TO_CHAR(cad.fec_naci,'yyyymmdd') AS fecnacimiento,
            SUBSTR(TRIM(iden.num_docu_iden),1,10) AS numdocidentidad,
           (
            SELECT mtd.cod_tipo_docu
              FROM mae_tipo_docum mtd
             WHERE mtd.oid_tipo_docu = iden.tdoc_oid_tipo_docu
                   AND mtd.pais_oid_pais = ln_id_pais
           ) AS tipdocidentidad,
            SUBSTR((cdir.val_nomb_via) || '' || (cdir.num_ppal) || '' || (cdir.val_obse),1,60) AS direccion,
            SUBSTR(TRIM(cdir.val_barr),1,30) AS barcliente,    
            (
             CASE
                WHEN (SELECT COUNT(1)
                        FROM tempZonZona tmpZon
                       WHERE tmpZon.clie_oid_clie = cl.oid_clie) > 0 THEN
                             NVL( substr(TRIM(ccom.val_text_comu),1,10),'')  --telefono celular
                ELSE
                   NVL(SUBSTR(TRIM(ccom.val_text_comu),1,10), --telefono celular
                       SUBSTR(( SELECT MAX(REPLACE(mcc.val_text_comu, ';', ''))
                                  FROM mae_clien_comun mcc
                                 WHERE mcc.ticm_oid_tipo_comu = (
                                                                 SELECT mtc.oid_tipo_comu
                                                                   FROM mae_tipo_comun mtc
                                                                  WHERE mtc.cod_tipo_comu = 'TF'
                                                                )
                                   AND mcc.clie_oid_clie = cl.oid_clie
                              ),1,10)
                      ) 
             END
            )  
            AS telefono,          
           '' AS desvalorventa,
           (
            SELECT spx.cod_peri
              FROM mae_clien_prime_conta xc,
                   mae_clien             clx,
                   cra_perio             cp,
                   seg_perio_corpo       spx
             WHERE xc.clie_oid_clie = clx.oid_clie
                   AND cp.oid_peri = xc.perd_oid_peri
                   AND spx.oid_peri = cp.peri_oid_peri
                   AND clx.oid_clie = cl.oid_clie
           ) AS campanaingreso,
           (
            SELECT mec.cod_esta_civi
              FROM mae_estad_civil mec
             WHERE mec.oid_esta_civi = cad.escv_oid_esta_civi
           ) AS estcivil,
           (
            CASE
               WHEN (SELECT COUNT(1)
                     FROM tempZonRegio tmpReg
                     WHERE tmpReg.clie_oid_clie = cl.oid_clie) > 0 THEN
              'GR'
               WHEN (SELECT COUNT(1)
                     FROM tempZonZona tmpZon
                     WHERE tmpZon.clie_oid_clie = cl.oid_clie) > 0 THEN
              'GZ'
               WHEN  (SELECT COUNT(1)
                      FROM tempZonSecc tmpSec
                      WHERE tmpSec.clie_oid_clie = cl.oid_clie) > 0 THEN
              'LD'
             ELSE
              'CL'
            END
           ) AS codtipocliente,
           (
             SELECT e.cod_esta_clie
               FROM mae_estat_clien e
              WHERE e.oid_esta_clie = cad.esta_oid_esta_clie
           ) AS estatusconsultora,
            SUBSTR(acc_fn_devue_clasi_clien(psCodigoPais,'DES_CLAS','CLAS',cl.oid_clie),1,15),
            (
             SELECT bac.val_dato_homo
               FROM mae_clien mc,
                    mae_clien_bloqu b,
                    mae_tipo_bloqu mtb,
                    int_acc_corpo_param bac
              WHERE b.clie_oid_clie = mc.oid_clie
                AND b.oid_bloq = (SELECT MAX(mcb.oid_bloq)
                                    FROM mae_clien_bloqu mcb
                                   WHERE mcb.fec_desb IS NULL
                                     AND mcb.clie_oid_clie = mc.oid_clie
                                 )
                AND b.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                AND bac.cod_pais   = psCodigoPais
                AND bac.cod_acce   = 'BLOQ'
                AND bac.val_dato   = mtb.cod_tipo_bloq
                AND mc.oid_clie    = cl.oid_clie
            ) ,
           (
            SELECT REPLACE(mcc.val_text_comu, ';', '')
           FROM mae_clien_comun mcc
              WHERE mcc.ticm_oid_tipo_comu = ( SELECT mtc.oid_tipo_comu
                   FROM mae_tipo_comun mtc
                                              WHERE mtc.cod_tipo_comu = 'ML'
                                            )
               AND mcc.clie_oid_clie = cl.oid_clie
           ) AS corelectronico,
           cl.oid_clie
      FROM mae_clien             cl,
           mae_clien_tipo_subti  ct,
           mae_clien_unida_admin cu,
           mae_clien_ident       iden,
           mae_clien_direc       cdir,
           mae_clien_datos_adici cad,
           mae_clien_comun       ccom,
           zon_terri_admin       zta,
           zon_zona              zo,
           zon_secci             se,
           zon_terri             te,
           zon_regio             reg
     WHERE cl.oid_clie = ct.clie_oid_clie
           AND ct.ticl_oid_tipo_clie = 2
           AND ct.ind_ppal = 1
           AND cl.oid_clie = cu.clie_oid_clie
           AND cu.ind_acti = 1
           AND cl.oid_clie = iden.clie_oid_clie
           AND iden.val_iden_docu_prin = 1
           AND cl.oid_clie = cdir.clie_oid_clie
           AND cdir.ind_dire_ppal = 1
           AND cdir.ind_elim = 0
           AND cl.oid_clie = cad.clie_oid_clie
           AND cl.oid_clie = ccom.clie_oid_clie(+)
        AND (ccom.ticm_oid_tipo_comu(+) = 6)
           AND cu.ztad_oid_terr_admi = zta.oid_terr_admi
           AND zta.zscc_oid_secc = se.oid_secc
           AND se.zzon_oid_zona = zo.oid_zona
           AND zo.zorg_oid_regi = reg.oid_regi
           AND te.oid_terr = zta.terr_oid_terr
           AND cl.pais_oid_pais = ln_id_pais
           AND reg.ind_acti = 1
           AND zo.ind_acti = 1
        AND se.ind_acti = 1
        AND (
            (
              psTipoEnvio = 'N' AND  -- Novedad 
              ( cl.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                ct.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                cu.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                iden.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                cdir.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                cad.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                se.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                zo.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                reg.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
                ccom.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy')
              )
            ) OR psTipoEnvio = 'C'  -- Completo 
            ) 
       UNION
       SELECT pscodigopais AS codpais,
              ( 
               SELECT val_pain
                 FROM bas_param_inter
                WHERE pais_cod_pais = pscodigopais
                  AND sist_cod_sist = pscodigosistema
                  AND inte_cod_inte = pscodigointerfaz
                  AND nom_pain = 'codigoCompania'
              ) AS codcompania,
              cl.cod_clie AS codcliente,
              SUBSTR(TRIM(translate(cl.val_ape1,vscadena,vsreplace)),1,25) AS apppaterno,
              SUBSTR(TRIM(translate(cl.val_ape2,vscadena,vsreplace)),1,25) AS appmaterno,
              SUBSTR(TRIM(translate(cl.val_nom1,vscadena,vsreplace)),1,25) AS prinombre,
              SUBSTR(TRIM(translate(cl.val_nom2,vscadena,vsreplace)),1,25) AS segnombre,
              (
               SELECT cod_subg_vent
                 FROM zon_sub_geren_venta
                WHERE pais_oid_pais = ln_id_pais
              ) AS codsubgerencia,
              '' AS codregion,
              '' AS codzona,
              '' AS codseccion,
              NULL AS codterritorio,
              '' AS fecnacimiento,
              SUBSTR(TRIM(cl.num_docu_iden),1,10) AS numdocidentidad,
              '' AS tipdocidentidad,
              '' AS direccion,
              '' AS barcliente,
              '' AS telefono,
              '' AS desvalorventa,
              '' AS campanaingreso,
              '' AS estcivil,
              'CF' AS codtipocliente,
              '' AS estatusconsultora,
              '',
              CASE WHEN cl.ind_acti = 9 THEN '' ELSE '16' END,
              '' AS corelectronico,
              NULL
         FROM ccc_consu_casti_cabec cl
        WHERE ( (
                 psTipoEnvio = 'N' AND  -- Novedad 
                 (cl.fec_modi >= to_date(varfecproceso,'dd/mm/yyyy'))
                ) OR psTipoEnvio = 'C'  -- Completo 
              )  ;

  TYPE interfazcom IS RECORD(
    codpais         seg_pais.cod_pais%TYPE,
    codcompania       bas_param_inter.val_pain%TYPE,
    codcliente      mae_clien.cod_clie%TYPE,
    apppaterno      mae_clien.val_ape1%TYPE,
    appmaterno      mae_clien.val_ape2%TYPE,
    prinombre       mae_clien.val_nom1%TYPE,
    segnombre       mae_clien.val_nom2%TYPE,
    codsubgerencia  zon_sub_geren_venta.cod_subg_vent%TYPE,
    codregion       zon_regio.cod_regi%TYPE,
    codzona         zon_zona.cod_zona%TYPE,
    codseccion      zon_secci.cod_secc%TYPE,
    codterritorio   zon_terri.cod_terr%TYPE,
    fecnacimiento   VARCHAR2(8),
    numdocidentidad mae_clien_ident.num_docu_iden%TYPE,
    tipdocidentidad VARCHAR2(2),
    direccion       VARCHAR2(60),
    barcliente      VARCHAR2(30),
    telefono        mae_clien_comun.val_text_comu%TYPE,
    desvalorventa   VARCHAR2(15),
    campanaingreso  VARCHAR2(6),
    estcivil        mae_estad_civil.cod_esta_civi%TYPE,
    codtipocliente    VARCHAR2(2),
    estatusconsultora gen_i18n_sicc_comun.val_i18n%TYPE,
    clasifcliente     VARCHAR2(15),
    categoria         VARCHAR2(3),
    corelectronico    mae_clien_comun.val_text_comu%TYPE,
     oid_clie          mae_clien.oid_clie%TYPE);

  TYPE interfazcomtab IS TABLE OF interfazcom;
  interfazrecord interfazcomtab;

  lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
  lsreplace VARCHAR2(100) := 'a        ';

  lsCodZona   zon_zona.cod_zona%TYPE;
  lsCodRegion zon_regio.cod_regi%TYPE;

BEGIN
  /* Procedimiento inicial para generar interfaz */
  lverror        := 'x1';
  ln_id_pais     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
  lbabrirutlfile := TRUE;

   SELECT TO_CHAR(MAX(fec_fpro), 'DD/MM/YYYY')
     INTO varfecproceso
     FROM bas_histo_lotes
    WHERE inte_cod_inte = psCodigoInterfaz
      AND ind_loer = 'N';

  lverror := 'x3';
   OPEN c_carga(ln_id_pais, varfecproceso, lscadena, lsreplace);
  lverror := 'x4';
  LOOP
    FETCH c_carga BULK COLLECT
      INTO interfazrecord LIMIT w_filas;

    IF lbabrirutlfile THEN
      GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(pscodigopais,
                                             pscodigosistema,
                                             pscodigointerfaz,
                                             psnombrearchivo,
                                             lsdirtempo,
                                             lsnombrearchivo,
                                             v_handle);
      lbabrirutlfile := FALSE;
    END IF;

    IF interfazrecord.count > 0 THEN
      FOR x IN interfazrecord.first .. interfazrecord.last LOOP
           lverror := interfazrecord(x).codcliente;
                IF interfazRecord(x).codTipoCliente = 'GR' THEN
                   interfazRecord(x).codZona := 0;
                   interfazRecord(x).codSeccion := 0;
                   interfazRecord(x).codTerritorio := 0;
                END IF;

                IF interfazRecord(x).codTipoCliente = 'GZ' THEN

             SELECT re.cod_regi
               INTO lsCodRegion
               FROM zon_zona zo,zon_regio re
              WHERE re.oid_regi = zo.zorg_oid_regi
                   AND zo.ind_acti = 1
                   AND zo.ind_borr = 0
                   AND re.ind_acti = 1
                AND re.ind_borr = 0
                AND zo.cod_zona = interfazRecord(x).codZona;

                   interfazRecord(x).codRegion := lsCodRegion;
                   interfazRecord(x).codSeccion := 0;
                   interfazRecord(x).codTerritorio := 0;
                END IF;

                IF interfazRecord(x).codTipoCliente = 'LD' THEN
             SELECT zo.Cod_Zona,re.Cod_Regi
               INTO lsCodZona,lsCodRegion
               FROM zon_secci se, zon_zona zo,zon_regio re
              WHERE re.oid_regi = zo.zorg_oid_regi
                AND zo.oid_zona = se.zzon_oid_zona
                   AND zo.ind_acti = 1
                   AND zo.ind_borr = 0
                   AND re.ind_acti = 1
                   AND re.ind_borr = 0
                   AND se.ind_acti = 1
                   AND se.ind_borr = 0
                AND se.clie_oid_clie = interfazRecord(x).oid_clie;

                   interfazRecord(x).codRegion := lsCodRegion;
                   interfazRecord(x).codZona := lsCodZona;
                   interfazRecord(x).codTerritorio := 0;
                END IF;

                lsLinea := interfazRecord(x).codPais               || ';' ||
                           interfazRecord(x).codCompania           || ';' ||
                           interfazRecord(x).codCliente            || ';' ||
                           interfazRecord(x).appPaterno            || ';' ||
                           interfazRecord(x).appMaterno            || ';' ||
                           interfazRecord(x).priNombre             || ';' ||
                           interfazRecord(x).segNombre             || ';' ||
                           interfazRecord(x).codSubGerencia        || ';' ||
                           interfazRecord(x).codRegion             || ';' ||
                           interfazRecord(x).codZona               || ';' ||
                           interfazRecord(x).codSeccion            || ';' ||
                           interfazRecord(x).codTerritorio         || ';' ||
                           interfazRecord(x).fecNacimiento         || ';' ||
                           interfazRecord(x).numDocIdentidad       || ';' ||
                           interfazRecord(x).tipDocIdentidad       || ';' ||
                           interfazRecord(x).direccion             || ';' ||
                           interfazRecord(x).barCliente            || ';' ||
                           interfazRecord(x).telefono              || ';' ||
                           interfazRecord(x).desValorVenta         || ';' ||
                           interfazRecord(x).CampanaIngreso        || ';' ||
                           interfazRecord(x).estCivil              || ';' ||
                           interfazRecord(x).codTipoCliente        || ';' ||
                           interfazRecord(x).estatusConsultora     || ';' ||
                           interfazRecord(x).clasifcliente         || ';' ||
                           interfazRecord(x).categoria             || ';' ||
                           interfazRecord(x).corElectronico;
        utl_file.put_line(v_handle, lslinea);
      END LOOP;
    END IF;
    EXIT WHEN c_carga%NOTFOUND;
  END LOOP;
  CLOSE c_carga;

  IF NOT lbabrirutlfile THEN
    utl_file.fclose(v_handle);
    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
  END IF;

EXCEPTION
  WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(lverror || '-' || ln_sqlcode || '-' || SQLERRM,1,250);
    raise_application_error(-20123,'ERROR INT_PR_ACC_ENVIO_CLIEN: ' || ls_sqlerrm);
END INT_PR_ACC_ENVIO_CLIEN;


/****************************************************************************
Descripcion       : Genera Interfaz de Envio de Clientes (ACC-1) Novedades
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 29/05/2014
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
Autor: CSVD - FFVV 
*****************************************************************************/
 PROCEDURE INT_PR_ACC_ENVIO_CLIEN_NOVED
 (
   pscodigopais     VARCHAR2,
   pscodigosistema  VARCHAR2,
   pscodigointerfaz VARCHAR2,
   psnombrearchivo  VARCHAR2
 ) IS

   /* Declaracion de Variables */
   ln_sqlcode NUMBER(10);
   ls_sqlerrm VARCHAR2(150);
   ln_id_pais NUMBER;
   w_filas    NUMBER := 1000;

   lverror VARCHAR2(40) := 'x';
   /*valores para el txt*/
   lbabrirutlfile BOOLEAN;

   /* Variables usadas para la generacion del archivo de texto */
   lsdirtempo bas_inter.dir_temp%TYPE;
   v_handle   utl_file.file_type;

   lslinea         VARCHAR2(1000);
   lsnombrearchivo VARCHAR2(50);
   varfecproceso   VARCHAR2(10);

   -- Declaracion de Estructuras
   codpais           VARCHAR2(3);
   codcompania       VARCHAR2(2);
   codtipocliente    VARCHAR2(2);
   codcliente        VARCHAR2(15);
   apppaterno        VARCHAR2(25);
   appmaterno        VARCHAR2(25);
   prinombre         VARCHAR2(25);
   segnombre         VARCHAR2(25);
   codsubgerencia    VARCHAR2(2);
   codregion         VARCHAR2(2);
   codzona           VARCHAR2(4);
   codseccion        VARCHAR2(1);
   codterritorio     VARCHAR2(6);
   fecnacimiento     VARCHAR2(8);
   tipdocidentidad   VARCHAR2(2);
   numdocidentidad   VARCHAR2(10);
   direccion         VARCHAR2(60);
   barcliente        VARCHAR2(30);
   telefono          VARCHAR2(10);
   desvalorventa     VARCHAR2(15);
   campanaingreso    VARCHAR2(6);
   estcivil          VARCHAR2(2);
   corelectronico    VARCHAR2(60);
   estatusconsultora VARCHAR2(2);

   CURSOR c_carga
   (
     ln_id_pais    NUMBER,
     varfecproceso VARCHAR2,
     vscadena      VARCHAR2,
     vsreplace     VARCHAR2
   ) IS
     WITH
     tempZonRegio AS
    (SELECT re.*
     FROM zon_regio re
     WHERE re.ind_acti = '1'
     AND re.ind_borr = '0'),
     tempZonZona AS
     (SELECT zo.*
      FROM zon_zona zo
      WHERE zo.ind_acti = '1'
      AND zo.ind_borr = '0'),
     tempZonSecc AS
     (SELECT se.*
      FROM zon_secci se
      WHERE se.ind_acti = '1'
      AND se.ind_borr = '0')
     SELECT pscodigopais AS codpais,
            (
             SELECT val_pain 
               FROM bas_param_inter 
              WHERE pais_cod_pais = pscodigopais 
                AND sist_cod_sist = pscodigosistema 
                AND inte_cod_inte = pscodigointerfaz
            ) AS codcompania,
            cl.cod_clie AS codcliente,
            substr(TRIM(translate(cl.val_ape1,vscadena,vsreplace)),1,25) AS apppaterno,
            substr(TRIM(translate(cl.val_ape2,vscadena,vsreplace)),1,25) AS appmaterno,
            substr(TRIM(translate(cl.val_nom1,vscadena,vsreplace)),1,25) AS prinombre,
            substr(TRIM(translate(cl.val_nom2,vscadena,vsreplace)),1,25) AS segnombre,
            (
             SELECT cod_subg_vent 
               FROM zon_sub_geren_venta 
              WHERE pais_oid_pais = ln_id_pais
            ) AS codsubgerencia,
            (
             CASE
                WHEN (
                      SELECT COUNT(1)
                        FROM tempZonRegio tmpReg
                       WHERE tmpReg.clie_oid_clie = cl.oid_clie) > 0 THEN
                        (SELECT cod_regi FROM tempZonRegio WHERE clie_oid_clie = cl.oid_clie and rownum<=1)
            ELSE
                reg.cod_regi
             END
           ) AS codregion,
           ( 
            CASE
                WHEN (
                      SELECT count(1)
                        FROM tempZonZona tmpZon
                       WHERE tmpZon.clie_oid_clie = cl.oid_clie) > 0 THEN
                        (SELECT cod_zona FROM tempZonZona WHERE clie_oid_clie = cl.oid_clie and rownum<=1)
            ELSE
                zo.cod_zona
           END
           ) AS codzona,
           (
            CASE
                WHEN (
                      SELECT count(1)
                        FROM tempZonSecc tmpSec
                       WHERE tmpSec.clie_oid_clie = cl.oid_clie) > 0 THEN
                        (SELECT cod_secc FROM tempZonSecc WHERE clie_oid_clie = cl.oid_clie  and rownum<=1)
            ELSE
                se.cod_secc
            END
           ) AS codseccion,
            te.cod_terr AS codterritorio,
           to_char(cad.fec_naci,'yyyymmdd') AS fecnacimiento,
           substr(TRIM(iden.num_docu_iden),1,10) AS numdocidentidad,
           (
            SELECT mtd.cod_tipo_docu
               FROM mae_tipo_docum mtd
              WHERE mtd.oid_tipo_docu = iden.tdoc_oid_tipo_docu
                AND mtd.pais_oid_pais = ln_id_pais
           ) AS tipdocidentidad,
           substr((cdir.val_nomb_via) || '' || (cdir.num_ppal) || '' || (cdir.val_obse),1,60) AS direccion,
           substr(TRIM(cdir.val_barr),1,30) AS barcliente,
                
           (
            CASE
             WHEN (select count(1)
                   from tempZonZona tmpZon
                   where tmpZon.clie_oid_clie = cl.oid_clie) > 0 THEN
               nvl( substr(TRIM(ccom.val_text_comu),1,10),'')  --telefono celular
             ELSE
           nvl( substr(TRIM(ccom.val_text_comu),1,10), --telefono celular
                substr(( SELECT max(REPLACE(mcc.val_text_comu, ';', ''))
                           FROM mae_clien_comun mcc
                          WHERE mcc.ticm_oid_tipo_comu = (SELECT mtc.oid_tipo_comu
                                                            FROM mae_tipo_comun mtc
                                                           WHERE mtc.cod_tipo_comu = 'TF'
                                                         )
                            AND mcc.clie_oid_clie = cl.oid_clie
                        ),1,10)
                    ) 
            END
           )  
           AS telefono,          
             
            '' AS desvalorventa,
           (
            SELECT spx.cod_peri
               FROM mae_clien_prime_conta xc,
                    mae_clien             clx,
                    cra_perio             cp,
                    seg_perio_corpo       spx
              WHERE xc.clie_oid_clie = clx.oid_clie
                    AND cp.oid_peri = xc.perd_oid_peri
                    AND spx.oid_peri = cp.peri_oid_peri
               AND clx.oid_clie = cl.oid_clie
           ) AS campanaingreso,
           (
            SELECT mec.cod_esta_civi
               FROM mae_estad_civil mec
             WHERE mec.oid_esta_civi = cad.escv_oid_esta_civi
           ) AS estcivil,
           (
            CASE
             WHEN (select count(1)
                   from tempZonRegio tmpReg
                   where tmpReg.clie_oid_clie = cl.oid_clie) > 0 THEN
              'GR'
             WHEN (select count(1)
                   from tempZonZona tmpZon
                   where tmpZon.clie_oid_clie = cl.oid_clie) > 0 THEN
              'GZ'
             WHEN  (select count(1)
                    from tempZonSecc tmpSec
                    where tmpSec.clie_oid_clie = cl.oid_clie) > 0 THEN
              'LD'
             ELSE
              'CL'
            END
           ) AS codtipocliente,
           (
            SELECT e.cod_esta_clie
               FROM mae_estat_clien e
              WHERE e.oid_esta_clie = cad.esta_oid_esta_clie
           ) AS estatusconsultora,
           substr(acc_fn_devue_clasi_clien(pscodigopais,'DES_CLAS','CLAS',cl.oid_clie),1,15),
           (   SELECT bac.val_dato_homo        
                 FROM mae_clien mc, 
                      mae_clien_bloqu b,
                      mae_tipo_bloqu mtb,
                      int_acc_corpo_param bac
                WHERE b.clie_oid_clie = mc.oid_clie 
                  AND b.oid_bloq      = ( SELECT max(mcb.oid_bloq)
                                            FROM mae_clien_bloqu mcb
                                           WHERE mcb.fec_desb             IS NULL 
                                             AND mcb.clie_oid_clie      = mc.oid_clie     
                                        ) 
                  AND b.tibq_oid_tipo_bloq = mtb.oid_tipo_bloq
                  AND bac.cod_pais   = pscodigopais
                  AND bac.cod_acce   = 'BLOQ'
                  AND bac.val_dato   = mtb.cod_tipo_bloq
                  AND mc.oid_clie    = cl.oid_clie           
           )
           ,
           (
            SELECT REPLACE(mcc.val_text_comu, ';', '')
             FROM mae_clien_comun mcc
             WHERE mcc.ticm_oid_tipo_comu =
                  (SELECT mtc.oid_tipo_comu
                     FROM mae_tipo_comun mtc
                    WHERE mtc.cod_tipo_comu = 'ML')
             AND mcc.clie_oid_clie = cl.oid_clie
           ) AS corelectronico,
             cl.oid_clie
       FROM mae_clien             cl,
            mae_clien_tipo_subti  ct,
            mae_clien_unida_admin cu,
            mae_clien_ident       iden,
            mae_clien_direc       cdir,
            mae_clien_datos_adici cad,
            mae_clien_comun       ccom,
            zon_terri_admin       zta,
            zon_zona              zo,
            zon_secci             se,
            zon_terri             te,
            zon_regio             reg
      WHERE cl.oid_clie = ct.clie_oid_clie
            AND ct.ticl_oid_tipo_clie = 2
            --AND ct.ind_ppal = 1
            AND ct.sbti_oid_subt_clie in (1,21)
            AND cl.oid_clie = cu.clie_oid_clie
            AND cu.ind_acti = 1
            AND cl.oid_clie = iden.clie_oid_clie
            AND iden.val_iden_docu_prin = 1
            AND cl.oid_clie = cdir.clie_oid_clie
            AND cdir.ind_dire_ppal = 1
            AND cdir.ind_elim = 0
            AND cl.oid_clie = cad.clie_oid_clie
            AND cl.oid_clie = ccom.clie_oid_clie(+)
            AND (ccom.ticm_oid_tipo_comu(+) = 6)
            AND cu.ztad_oid_terr_admi = zta.oid_terr_admi
            AND zta.zscc_oid_secc = se.oid_secc
            AND se.zzon_oid_zona = zo.oid_zona
            AND zo.zorg_oid_regi = reg.oid_regi
            AND te.oid_terr = zta.terr_oid_terr
            AND cl.pais_oid_pais = ln_id_pais
            AND reg.ind_acti = 1
            AND zo.ind_acti = 1
            AND se.ind_acti = 1
       AND (cl.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
            ct.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
            cu.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
            iden.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
            cdir.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
            cad.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy') OR
            ccom.fec_ulti_actu >= to_date(varfecproceso,'dd/mm/yyyy')
           )
        UNION 
       SELECT pscodigopais AS codpais,
       ( SELECT val_pain
               FROM bas_param_inter
              WHERE pais_cod_pais = pscodigopais
                AND sist_cod_sist = pscodigosistema
                AND inte_cod_inte = pscodigointerfaz
       ) AS codcompania,
       cl.cod_clie AS codcliente,
       substr(TRIM(translate(cl.val_ape1,vscadena,vsreplace)),1,25) AS apppaterno,
       substr(TRIM(translate(cl.val_ape2,vscadena,vsreplace)),1,25) AS appmaterno,
       substr(TRIM(translate(cl.val_nom1,vscadena,vsreplace)),1,25) AS prinombre,
       substr(TRIM(translate(cl.val_nom2,vscadena,vsreplace)),1,25) AS segnombre,
       (
             SELECT cod_subg_vent
               FROM zon_sub_geren_venta
              WHERE pais_oid_pais = ln_id_pais
       ) AS codsubgerencia,
       '' AS codregion,
       '' AS codzona,
       '' AS codseccion,
       NULL AS codterritorio,
       '' AS fecnacimiento,
       substr(TRIM(cl.num_docu_iden),1,10) AS numdocidentidad,
       '' AS tipdocidentidad,
       '' AS direccion,
       '' AS barcliente,
       '' AS telefono,
       '' AS desvalorventa,
       '' AS campanaingreso,
       '' AS estcivil,
       'CF' AS codtipocliente,
       '' AS estatusconsultora,
       '',
       CASE WHEN cl.ind_acti = 9 THEN '' ELSE '16' END,
       '' AS corelectronico,
       NULL
  FROM CCC_CONSU_CASTI_CABEC cl
 WHERE cl.fec_modi >= to_date(varfecproceso,'dd/mm/yyyy');

   TYPE interfazcom IS RECORD(
     codpais         seg_pais.cod_pais%TYPE,
     codcompania       bas_param_inter.val_pain%TYPE,
     codcliente      mae_clien.cod_clie%TYPE,
     apppaterno      mae_clien.val_ape1%TYPE,
     appmaterno      mae_clien.val_ape2%TYPE,
     prinombre       mae_clien.val_nom1%TYPE,
     segnombre       mae_clien.val_nom2%TYPE,
     codsubgerencia  zon_sub_geren_venta.cod_subg_vent%TYPE,
     codregion       zon_regio.cod_regi%TYPE,
     codzona         zon_zona.cod_zona%TYPE,
     codseccion      zon_secci.cod_secc%TYPE,
     codterritorio   zon_terri.cod_terr%TYPE,
     fecnacimiento   VARCHAR2(8),
     numdocidentidad mae_clien_ident.num_docu_iden%TYPE,
     tipdocidentidad VARCHAR2(2),
     direccion       VARCHAR2(60),
     barcliente      VARCHAR2(30),
     telefono        mae_clien_comun.val_text_comu%TYPE,
     desvalorventa   VARCHAR2(15),
     campanaingreso  VARCHAR2(6),
     estcivil        mae_estad_civil.cod_esta_civi%TYPE,
     codtipocliente    VARCHAR2(2),
     estatusconsultora gen_i18n_sicc_comun.val_i18n%TYPE,
     clasifcliente     VARCHAR2(15),
     categoria         VARCHAR2(3),
     corelectronico    mae_clien_comun.val_text_comu%TYPE,
     Oid_Clie          mae_clien.oid_clie%TYPE);

   TYPE interfazcomtab IS TABLE OF interfazcom;
   interfazrecord interfazcomtab;

   lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
   lsreplace VARCHAR2(100) := 'a        ';

   lsCodZona   zon_zona.cod_zona%TYPE;
   lsCodRegion zon_regio.cod_regi%TYPE;
 BEGIN
   /* Procedimiento inicial para generar interfaz */
   lverror        := 'x1';
   ln_id_pais     := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
   lbabrirutlfile := TRUE;

   SELECT to_char(MAX(fec_fpro), 'DD/MM/YYYY')
     INTO varfecproceso
     FROM bas_histo_lotes
    WHERE inte_cod_inte = pscodigointerfaz;

   lverror := 'x3';
   OPEN c_carga(ln_id_pais, varfecproceso, lscadena, lsreplace);
   lverror := 'x4';
   LOOP
     FETCH c_carga BULK COLLECT
       INTO interfazrecord LIMIT w_filas;

     IF lbabrirutlfile THEN
       GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(pscodigopais,
                                              pscodigosistema,
                                              pscodigointerfaz,
                                              psnombrearchivo,
                                              lsdirtempo,
                                              lsnombrearchivo,
                                              v_handle);
       lbabrirutlfile := FALSE;
     END IF;

     IF interfazrecord.count > 0 THEN
       FOR x IN interfazrecord.first .. interfazrecord.last LOOP
           lverror := interfazrecord(x).codcliente;
           IF interfazRecord(x).codTipoCliente = 'GR' THEN
              interfazRecord(x).codZona := 0;
              interfazRecord(x).codSeccion := 0;
              interfazRecord(x).codTerritorio := 0;
           END IF;

           IF interfazRecord(x).codTipoCliente = 'GZ' THEN

             select re.Cod_Regi
             into lsCodRegion
             from zon_zona zo,zon_regio re
             where re.oid_regi = zo.zorg_oid_regi
             AND zo.ind_acti = 1
             AND zo.ind_borr = 0
             AND re.ind_acti = 1
             and re.ind_borr = 0
             and zo.cod_zona = interfazRecord(x).codZona
             --and zo.clie_oid_clie = interfazrecord(x).oid_clie
             ;

             interfazRecord(x).codRegion := lsCodRegion;
             interfazRecord(x).codSeccion := 0;
             interfazRecord(x).codTerritorio := 0;
          END IF;

          IF interfazRecord(x).codTipoCliente = 'LD' THEN
             select zo.Cod_Zona,re.Cod_Regi
             into lsCodZona,lsCodRegion
             from zon_secci se, zon_zona zo,zon_regio re
             where re.oid_regi = zo.zorg_oid_regi
             and zo.oid_zona = se.zzon_oid_zona
             AND zo.ind_acti = 1
             AND zo.ind_borr = 0
             AND re.ind_acti = 1
             AND re.ind_borr = 0
             AND se.ind_acti = 1
             AND se.ind_borr = 0
             and se.clie_oid_clie = interfazRecord(x).Oid_Clie;

             interfazRecord(x).codRegion := lsCodRegion;
             interfazRecord(x).codZona := lsCodZona;
             interfazRecord(x).codTerritorio := 0;
          END IF;

           lsLinea :=  interfazRecord(x).codPais               || ';' ||
                       interfazRecord(x).codCompania            || ';' ||
                       interfazRecord(x).codCliente             || ';' ||
                       interfazRecord(x).appPaterno             || ';' ||
                       interfazRecord(x).appMaterno             || ';' ||
                       interfazRecord(x).priNombre              || ';' ||
                       interfazRecord(x).segNombre              || ';' ||
                       interfazRecord(x).codSubGerencia         || ';' ||
                       interfazRecord(x).codRegion              || ';' ||
                       interfazRecord(x).codZona                || ';' ||
                       interfazRecord(x).codSeccion             || ';' ||
                       interfazRecord(x).codTerritorio          || ';' ||
                       interfazRecord(x).fecNacimiento          || ';' ||
                       interfazRecord(x).numDocIdentidad        || ';' ||
                       interfazRecord(x).tipDocIdentidad        || ';' ||
                       interfazRecord(x).direccion              || ';' ||
                       interfazRecord(x).barCliente             || ';' ||
                       interfazRecord(x).telefono               || ';' ||
                       interfazRecord(x).desValorVenta          || ';' ||
                       interfazRecord(x).CampanaIngreso         || ';' ||
                       interfazRecord(x).estCivil               || ';' ||
                       interfazRecord(x).codTipoCliente         || ';' ||
                       interfazRecord(x).estatusConsultora      || ';' ||
                       interfazRecord(x).clasifcliente          || ';' ||
                       interfazRecord(x).categoria              || ';' ||
                       interfazRecord(x).corElectronico;
         utl_file.put_line(v_handle, lslinea);
       END LOOP;
     END IF;
     EXIT WHEN c_carga%NOTFOUND;
   END LOOP;
   CLOSE c_carga;

   IF NOT lbabrirutlfile THEN
     utl_file.fclose(v_handle);
     /* Procedimiento final para generar interfaz */
     GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR',
                                            lsdirtempo,
                                            psnombrearchivo,
                                            lsnombrearchivo);
   END IF;

 EXCEPTION
   WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(lverror || '-' || ln_sqlcode || '-' || SQLERRM,1,250);
     raise_application_error(-20123,'ERROR INT_PR_ACC_ENVIO_CLIEN_NOVED: ' || ls_sqlerrm);
 END INT_PR_ACC_ENVIO_CLIEN_NOVED;

/****************************************************************************
Descripcion       : Genera Interfaz de Envio de CDR's (ACC-3)
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 06/03/2013
Parametros:
 psCodigoPais     : Codigo Pais
 psCodigoPeriodo  : Año Campaña
 psCodigoSistema  : Codigo Empresa
 psCodigoInterfaz : Codigo Interfaz
 psNombreArchivo  : Nombre Arcchivo
 psFechaFacturacion : Fecha de Facturacion
 
Autor: CSVD - FFVV 
*****************************************************************************/
  PROCEDURE INT_PR_ACC_ENVIO_CDR
  (
   pscodigopais     VARCHAR2,
                                   pscodigosistema  VARCHAR2,
                                   pscodigointerfaz VARCHAR2,
                                   psnombrearchivo  VARCHAR2,
   pscodigoPeriodo  VARCHAR2,
   psFechaFacturacion   VARCHAR2
  ) IS
    i integer := 0;
    /* Declaracion de Variables */
    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);
    ln_id_pais NUMBER;
    W_FILAS    NUMBER := 1000;

    lverror varchar2(40) := 'x';
    /*valores para el txt*/
    lbAbrirUtlFile      BOOLEAN;

      codPais            VARCHAR2(3);
      codCompania        VARCHAR2(2);
      codCliente         rec_cabec_recla.CLIE_OID_CLIE%TYPE;
      numDocServicioPosv rec_cabec_recla.NUM_RECL%TYPE;
      desProducto        mae_produ.DES_CORT%TYPE;
      unidadesDevueltas  rec_linea_opera_recla.NUM_UNID_RECL%TYPE;
      codProducto        mae_produ.cod_sap%TYPE;
    opeServPosVenta    VARCHAR2(1);
      codCampana         VARCHAR2(6);
    codVenta           VARCHAR2(5);
    FechaEnvi          VARCHAR2(8);

    /* Variables usadas para la generacion del archivo de texto */
    lsDirTempo BAS_INTER.DIR_TEMP%TYPE;
    v_handle   UTL_FILE.FILE_TYPE;

    lsLinea         VARCHAR2(1000);
    lsNombreArchivo VARCHAR2(50);

    -- Declaracion de Estructuras
    indActOperCali        bas_param_inter.val_pain%TYPE;

    CURSOR C_CARGA(ln_id_pais NUMBER) is
        SELECT pscodigopais AS codpais,
               (SELECT val_pain
                FROM bas_param_inter bi
               WHERE pais_cod_pais = pscodigopais 
                 AND sist_cod_sist = pscodigosistema 
                 AND inte_cod_inte = pscodigointerfaz
                 AND bi.nom_pain   = 'codigoCompania'
                 ) AS codcompania,
               a.num_recl AS numdocservicioposv, mc.cod_clie AS codcliente,
               /*             
              CASE
                  WHEN e.cod_oper IN ('CP', 'PC')
                     THEN TO_CHAR (psp.val_codi_vent_fict)
                  ELSE psp.val_codi_vent
               END AS codventa,
               */
               nvl(to_char(psp.val_codi_vent_fict),psp.val_codi_vent) AS codventa,
               m.cod_sap AS codproducto, regexp_replace(gi.val_i18n,',',' ')	AS desproducto,
               sp.cod_peri AS codcampana, c.num_unid_recl AS unidadesdevueltas,
               TO_CHAR (SYSDATE, 'yyyymmdd') fechaenvi,
             CASE
                  WHEN e.cod_oper IN ('DN', 'DE', 'DM','DP')
                     THEN 'D'
                  WHEN e.cod_oper IN ('CM', 'MC', 'CP', 'PC','MT','PT','TM','PT')
                     THEN 'C'
                  ELSE 'O'
               END AS opeservposventa
        FROM rec_cabec_recla       a,
             rec_opera_recla       b,
             rec_linea_opera_recla c,
             rec_tipo_movim        d,
             rec_tipos_opera       f,
             rec_opera             e,
             mae_produ             m,
             mae_clien             mc,
             ped_solic_posic      psp,
               ped_solic_cabec psc,
             seg_perio_corpo sp,
             cra_perio cp,
               gen_i18n_sicc_pais gi,
             rec_solic_opera rso,
             rec_motiv_devol rmd
       WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.oid_oper_recl = c.opre_oid_oper_recl
         AND c.timo_oid_tipo_movi = d.oid_tipo_movi
         AND mc.oid_clie = a.clie_oid_clie
         AND c.prod_oid_prod = m.oid_prod
         AND b.tiop_oid_tipo_oper = f.oid_tipo_oper
         AND f.rope_oid_oper = e.oid_oper
           AND c.sopo_oid_soli_posi = psp.oid_soli_posi
           AND b.oid_oper_recl = rso.opre_oid_oper_recl
           AND e.tspa_oid_soli_pais_gene = rso.tspa_oid_tipo_soli_pais
           AND rso.soca_oid_soli_cabe = psc.oid_soli_cabe
           AND psc.fec_fact = TO_DATE (psfechafacturacion, 'DD/MM/YYYY')
            -- Parametro --
         AND d.cod_tipo_movi = 'D'
         AND c.modv_oid_moti_devo = rmd.oid_moti_devo (+)
         AND (indActOperCali is NULL AND e.cod_oper IN ('DN', 'DE', 'DM', 'CM', 'MC', 'CP', 'PC') 
          OR (indActOperCali IS NOT NULL AND rmd.ind_mot_dev = 'C'))
          -- AND a.perd_oid_peri_recl = cp.oid_peri
           AND cp.peri_oid_peri = sp.oid_peri
           AND gi.attr_enti = 'MAE_PRODU'
           AND gi.val_oid = c.prod_oid_prod
           AND sp.cod_peri = pscodigoperiodo;

    TYPE interfazCom IS RECORD(
      codPais            VARCHAR2(3),
      codCompania        bas_param_inter.val_pain%TYPE,
      numDocServicioPosv rec_cabec_recla.NUM_RECL%TYPE, -- VARCHAR2(8),
      codCliente         rec_cabec_recla.CLIE_OID_CLIE%TYPE,-- number(12), --  VARCHAR2(10),
      codVenta           VARCHAR2(5),
      codProducto        mae_produ.cod_sap%TYPE, -- VARCHAR2(9),
      desProducto        mae_produ.DES_CORT%TYPE, --  VARCHAR2(30),
      codCampana         VARCHAR2(6),
      unidadesDevueltas  rec_linea_opera_recla.NUM_UNID_RECL%TYPE,  -- VARCHAR2(3),
      FechaEnvi          VARCHAR2(8),
      opeServPosVenta    VARCHAR2(1));

    TYPE interfazComTab IS TABLE OF interfazCom;
    interfazRecord interfazComTab;


  BEGIN
    /* Procedimiento inicial para generar interfaz */
    lverror := 'x1';
    ln_id_pais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(pscodigopais);
    lbAbrirUtlFile := TRUE;

    lverror := 'x3';

      BEGIN
       SELECT bpi.val_pain
         INTO indActOperCali
         FROM bas_param_inter bpi
        WHERE bpi.pais_cod_pais = pscodigopais
          AND bpi.sist_cod_sist = 'ACC'
          AND bpi.inte_cod_inte = 'ACC-3'
          AND bpi.nom_pain = 'indActOperCali'
          AND bpi.est_pain = '1';
       EXCEPTION
         WHEN NO_DATA_FOUND
         THEN indActOperCali := NULL;
      END;

    OPEN C_CARGA(ln_id_pais);
    lverror := 'x4';
    LOOP
      FETCH C_CARGA BULK COLLECT INTO interfazRecord LIMIT W_FILAS;

           IF lbAbrirUtlFile THEN
               GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigosistema, psCodigoInterfaz,
                  psNombreArchivo, lsDirTempo, lsNombreArchivo, V_HANDLE);
               lbAbrirUtlFile := FALSE;
           END IF;

      IF interfazRecord.COUNT > 0 THEN
        FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
             I       := I + 1;
          lsLinea := interfazRecord(x).codPais               || ';' ||
                    interfazRecord(x).codCompania            || ';' ||
                    interfazRecord(x).numDocServicioPosv     || ';' ||
                    interfazRecord(x).codCliente             || ';' ||
                    interfazRecord(x).codVenta               || ';' ||
                    interfazRecord(x).codProducto            || ';' ||
                    interfazRecord(x).desProducto            || ';' ||
                    interfazRecord(x).codCampana             || ';' || 
                    interfazRecord(x).unidadesDevueltas      || ';' ||
                    interfazRecord(x).FechaEnvi              || ';' ||
                    interfazRecord(x).opeServPosVenta;

          UTL_FILE.PUT_LINE(V_HANDLE, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN C_CARGA%NOTFOUND;
    END LOOP;
    CLOSE C_CARGA;

    IF NOT lbAbrirUtlFile THEN
    utl_file.fclose(V_HANDLE);
    /* Procedimiento final para generar interfaz */
    GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(lverror || '-' || ln_sqlcode || '-' || sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123,'ERROR INT_PR_ACC_ENVIO_CDR: ' || ls_sqlerrm);
  END INT_PR_ACC_ENVIO_CDR;

/****************************************************************************
Descripcion       : Recepcionar Recomendates y Recomendadas(ACC-4)
Fecha Creacion    : 01/10/2008
Fecha Modificacion: 06/03/2013
Parametros:

Autor: CSVD - FFVV 
*****************************************************************************/

PROCEDURE INT_PR_ACC_RECEP_RECTE_RECDA
  (
   pscod_pais      IN VARCHAR2,
                                          pscod_comp      IN VARCHAR2,
                                          pscod_clie_rete IN VARCHAR2,
                                          pstip_docu_iden IN VARCHAR2,
                                          psnum_docu_iden IN VARCHAR2,
                                          psnum_conc      IN VARCHAR2,
                                          psnum_vers      IN VARCHAR2,
                                          psnum_prem      IN VARCHAR2,
                                          psnum_nive      IN VARCHAR2,
                                          pscod_camp_soli IN VARCHAR2,
                                          psind_asig_prem IN VARCHAR2,
                                          pscod_clie_reco IN VARCHAR2,
                                          psfec_ingr      IN VARCHAR2,
                                          pshor_ingr      IN VARCHAR2,
                                          psusu_proc      IN VARCHAR2,
   psind_proc      IN VARCHAR2
  ) IS

    /* Declaracion de Variables */
    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);
    lverror varchar2(40) := 'x';

    /* Declaracion de Variables Negocio */
    ln_existe NUMBER;

  BEGIN

  SELECT COUNT(1)
  INTO ln_existe
  FROM INT_ACC_RCDTE_PREMI
  WHERE TIP_DOCU_IDEN = pstip_docu_iden
        AND NUM_DOCU_IDEN = psnum_docu_iden
        AND COD_CAMP_SOLI = pscod_camp_soli ;

       IF ln_existe > 0 THEN

        UPDATE INT_ACC_RCDTE_PREMI
        SET COD_PAIS			= pscod_pais,
            COD_COMP			= pscod_comp,
            COD_CLIE_RETE	= pscod_clie_rete,
            NUM_CONC			= psnum_conc,
            NUM_VERS			= psnum_vers,
            NUM_PREM			= psnum_prem,
            NUM_NIVE			= psnum_nive,
            IND_ASIG_PREM	= psind_asig_prem,
            COD_CLIE_RECO	= pscod_clie_reco,
            FEC_INGR			= TO_DATE(psfec_ingr,'YYYYMMDD'),
            HOR_INGR			= pshor_ingr,
            USU_PROC			= psusu_proc,
            IND_PROC      = psind_proc
        WHERE TIP_DOCU_IDEN=pstip_docu_iden
              AND NUM_DOCU_IDEN=psnum_docu_iden
              AND COD_CAMP_SOLI=pscod_camp_soli;

       ELSE

       	    INSERT INTO INT_ACC_RCDTE_PREMI
    	       (
    	        COD_PAIS,
          		COD_COMP,
          		COD_CLIE_RETE,
          		TIP_DOCU_IDEN,
          		NUM_DOCU_IDEN,
          		NUM_CONC,
          		NUM_VERS,
          		NUM_PREM,
          		NUM_NIVE,
          		COD_CAMP_SOLI,
          		IND_ASIG_PREM,
          		COD_CLIE_RECO,
          		FEC_INGR,
          		HOR_INGR,
          		USU_PROC,
          		IND_PROC
          		)
          	    VALUES
          	    (
                  pscod_pais,
                  pscod_comp,
                  pscod_clie_rete,
                  pstip_docu_iden,
                  psnum_docu_iden,
                  psnum_conc,
                  psnum_vers,
                  psnum_prem,
                  psnum_nive,
                  pscod_camp_soli,
                  psind_asig_prem,
                  pscod_clie_reco,
                  TO_DATE(psfec_ingr,'YYYYMMDD'),
                  pshor_ingr,
                  psusu_proc,
                  psind_proc
          	    );

       END IF;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(lverror || '-' || ln_sqlcode || '-' || sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR INT_PR_ACC_RECEP_RECTE_RECDA: ' || ls_sqlerrm);

  END INT_PR_ACC_RECEP_RECTE_RECDA;

END INT_PKG_ACACE;
/
