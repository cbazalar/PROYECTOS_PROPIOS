CREATE OR REPLACE PACKAGE PEJ_PKG_PROCE IS

   W_FILAS NUMBER := 5000;

   SIN_NIVEL CONSTANT VARCHAR2(2) := 'SN';

   EJECUTIVA_JUNIOR CONSTANT VARCHAR2(2) := 'EJ';

   EJECUTIVA_SENIOR CONSTANT VARCHAR2(2) := 'ES';

   EJECUTIVA_MASTER CONSTANT VARCHAR2(2) := 'EM';

   ASPIRANTE CONSTANT VARCHAR2(2)        := 'AS';

    /*********************************************************************************
    Descripcion       : Realiza el proceso de carga de tarejta paycard en Cta Cte.
    Fecha Creacion    : 13/01/2011
    Autor             : Jesse Rios
    *********************************************************************************/
    PROCEDURE PEJ_PR_PROCE_CARGA_TARJE_PAYCA(psCodigoPais    VARCHAR2,
                                             psCodigoMarca   VARCHAR2,
                                             psCodigoCanal   VARCHAR2,
                                             psCodigoPeriodo VARCHAR2,
                                             psUsuario       VARCHAR2);



/***************************************************************************
Descripcion       : Valida Carga de Programa Ejecutivas
Fecha Creacion    : 01/03/2013
Autor             : Aurelio Oviedo
	Fecha Modificación : 29/06/2013
	Autor              : Sebastian Guerra
***************************************************************************/
      PROCEDURE PEJ_PR_VALID_CARGA_PROGR(psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   pnCampanya                 VARCHAR2,
	   pnCodigoPrograma           VARCHAR2,
	   pnTipoCarga                VARCHAR2);

/***************************************************************************
Descripcion       : Actualiza Carga de Programa Ejecutivas
Fecha Creacion    : 01/03/2013
Autor             : Aurelio Oviedo
	Fecha Modificación : 29/06/2013
	Autor              : Sebastian Guerra
***************************************************************************/
      PROCEDURE PEJ_PR_ACTUA_CARGA_PROGR(psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   pnCampanya                 VARCHAR2,
   pnCodigoPrograma           VARCHAR2,
	   pnTipoCarga                VARCHAR2,
   pnCodigoUsuario            VARCHAR2);


    /***************************************************************************
     Descripcion       : Calcula avance de gestión
     Fecha Creacion    : 07/11/2013
     Autor             : Carlos Mori
     Fecha Modificación: -
     Autor             : -
    ***************************************************************************/
      PROCEDURE PEJ_PR_CALCU_AVANC_GESTI(psCodigoPais         VARCHAR2,
                                         psCodigoMarca        VARCHAR2,
                                         psCodigoCanal        VARCHAR2,
                                         psCodigoPeriodo      VARCHAR2,
                                         psFechaFacturacion   VARCHAR2);

    /***************************************************************************
     Descripcion       : Calcula comisión por ejecutiva
     Fecha Creacion    : 13/11/2013
     Autor             : Carlos Mori
     Fecha Modificación: -
     Autor             : -
    ***************************************************************************/
      PROCEDURE PEJ_PR_CALCU_COMIS_EJECU(psCodigoPais         VARCHAR2,
                                         psCodigoMarca        VARCHAR2,
                                         psCodigoCanal        VARCHAR2,
                                         psCodigoPeriodo      VARCHAR2,
                                         psFechaFacturacion   VARCHAR2);
END PEJ_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY PEJ_PKG_PROCE IS
   /* Declaracion de Variables */
   ln_sqlcode        NUMBER(10);
   ls_sqlerrm        VARCHAR2(1000);

    /*********************************************************************************
    Descripcion       : Realiza el proceso de carga de tarejta paycard en Cta Cte.
    Fecha Creacion    : 13/01/2011
    Autor             : Jesse Rios
   *********************************************************************************/
    PROCEDURE PEJ_PR_PROCE_CARGA_TARJE_PAYCA(psCodigoPais    VARCHAR2,
                                             psCodigoMarca   VARCHAR2,
                                             psCodigoCanal   VARCHAR2,
                                             psCodigoPeriodo VARCHAR2,
                                             psUsuario       VARCHAR2)IS

        CURSOR C_COMISION_CABECERA IS
        SELECT COD_EJEC,IMP_MONT_PAYC
        FROM PEJ_COMIS_CABEC
        WHERE COD_PAIS = psCodigoPais
        AND   COD_PERI = psCodigoPeriodo
        AND   IND_ENVI = 0
        AND   IND_COMI_RECU = 1;

        TYPE comisionCabeceraRec IS RECORD(COD_EJEC PEJ_COMIS_CABEC.COD_EJEC%TYPE,IMP_MONT_PAYC PEJ_COMIS_CABEC.IMP_MONT_PAYC%TYPE);
        TYPE comisionCabeceraTab IS TABLE OF comisionCabeceraRec;
        comisionCabeceraRecord comisionCabeceraTab;

        vnOidSubProcesoCargoDirecto  PEJ_PARAM_GENER.COD_SUB_PROC_CARG_DIRE%TYPE;
        vnOidSubProcesoAbonoDirecto  PEJ_PARAM_GENER.COD_SUB_PROC_ABON_DIRE%TYPE;
        vnFila NUMBER := 1;
        vnSecuencialFecha NUMBER;

   BEGIN

        SELECT COD_SUB_PROC_CARG_DIRE,COD_SUB_PROC_ABON_DIRE
        INTO vnOidSubProcesoCargoDirecto,vnOidSubProcesoAbonoDirecto
        FROM PEJ_PARAM_GENER
        WHERE COD_PAIS = psCodigoPais;

        EXECUTE IMMEDIATE 'TRUNCATE TABLE CCC_CARGA_CARGO_ABONO_MASIV';

        OPEN C_COMISION_CABECERA;

        LOOP
            FETCH C_COMISION_CABECERA BULK COLLECT INTO comisionCabeceraRecord LIMIT W_FILAS;
            IF comisionCabeceraRecord.COUNT > 9 THEN
              FOR i IN comisionCabeceraRecord.FIRST .. comisionCabeceraRecord.LAST LOOP
                  INSERT INTO CCC_CARGA_CARGO_ABONO_MASIV
                  (NUM_LOTE,
                   VAL_FILA,
                   COD_CLIE,
                   IMP_MOVI,
                   IMP_MOVI_VALI,
                   COD_USUA,
                   FEC_PROC)
                  VALUES(
                   vnFila,
                   vnFila,
                   comisionCabeceraRecord(i).COD_EJEC,
                   comisionCabeceraRecord(i).IMP_MONT_PAYC,
                   comisionCabeceraRecord(i).IMP_MONT_PAYC,
                   psUsuario,
                   sysdate);

                   vnFila := vnFila + 1;
              END LOOP;
            END IF;
        END LOOP;

        CLOSE C_COMISION_CABECERA;

        SELECT NVL(MAX(VAL_SECU),0) + 1
        INTO vnSecuencialFecha
        FROM pej_carga_secue
        WHERE cod_pais = psCodigoPais
        AND FEC_CARG = trunc(sysdate);

        --falta definir con doris el codigo de sociedad
        --CCC_PKG_PROCE.CCC_PR_PROCE_CARGO_ABONO_MASIV(psCodigoPais,null,psCodigoPeriodo,vnOidSubProcesoCargoDirecto,TO_CHAR(TRUNC(sysdate)),vnSecuencialFecha,psUsuario);

        INSERT INTO PEJ_CARGA_SECUE(
          COD_PAIS,
          FEC_CARG,
          VAL_SECU
        )VALUES(
          psCodigoPais,
          TRUNC(SYSDATE),
          vnSecuencialFecha
        );

        vnSecuencialFecha := vnSecuencialFecha +1;

        --CCC_PKG_PROCE.CCC_PR_PROCE_CARGO_ABONO_MASIV(psCodigoPais,null,psCodigoPeriodo,vnOidSubProcesoAbonoDirecto,TO_CHAR(TRUNC(sysdate)),vnSecuencialFecha,psUsuario);

        INSERT INTO PEJ_CARGA_SECUE(
          COD_PAIS,
          FEC_CARG,
          VAL_SECU
        )VALUES(
          psCodigoPais,
          TRUNC(SYSDATE),
          vnSecuencialFecha
        );

   EXCEPTION
   WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR PEJ_PR_PROCE_CARGA_TARJE_PAYCA: '||ls_sqlerrm);
   END PEJ_PR_PROCE_CARGA_TARJE_PAYCA;



/***************************************************************************
Descripcion       : Valida Carga de Programa Ejecutivas
Fecha Creacion    : 01/03/2013
Autor             : Aurelio Oviedo
	Fecha Modificación : 29/06/2013
	Autor              : Sebastian Guerra
***************************************************************************/
      PROCEDURE PEJ_PR_VALID_CARGA_PROGR(psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   pnCampanya                 VARCHAR2,
	   pnCodigoPrograma           VARCHAR2,
	   pnTipoCarga                VARCHAR2)
IS

  CURSOR c_programas IS
	    SELECT NUM_REGI_CARG,	CLIE_COD_CLIE,	COD_CAMP_REFE,	COD_LIST,	CAM_INIC,	CAM_FINA,	COD_NIVE,	COD_FASE,	VAL_META_PEDI,	
	           VAL_META_INGR,	VAL_META_RECA,	VAL_MONT_RECL, COD_ABON,	VAL_TIPO_OPER,	COD_MOTI_RECH,	NUM_FILA,	EST_PROC
      FROM EJE_CARGA_MASIV_EJECU
	     WHERE NUM_REGI_CARG = pnNumeroCarga
	       AND COD_LIST = pnTipoCarga
	       AND EST_PROC = 0
	       AND COD_CAMP_REFE = pnCampanya;

  TYPE interfazProgramas IS RECORD
  (
	    numeroRegistrosCarga          EJE_CARGA_MASIV_EJECU.NUM_REGI_CARG%TYPE,
	    codigoCliente                 EJE_CARGA_MASIV_EJECU.CLIE_COD_CLIE%TYPE,
	    campanyaReferencia            EJE_CARGA_MASIV_EJECU.COD_CAMP_REFE%TYPE,
	    codigoLista                   EJE_CARGA_MASIV_EJECU.COD_LIST%TYPE,
	    campanyaInicio                EJE_CARGA_MASIV_EJECU.CAM_INIC%TYPE,
	    campanyaFinal                 EJE_CARGA_MASIV_EJECU.CAM_FINA%TYPE,
	    codigoNivel                   EJE_CARGA_MASIV_EJECU.COD_NIVE%TYPE,
	    codigoFase                    EJE_CARGA_MASIV_EJECU.COD_FASE%TYPE,
	    lnValorMetaPedido             EJE_CARGA_MASIV_EJECU.VAL_META_PEDI%TYPE,
	    lnValorMetaIngreso            EJE_CARGA_MASIV_EJECU.VAL_META_INGR%TYPE,
	    lnValorMetaRecaudado          EJE_CARGA_MASIV_EJECU.VAL_META_RECA%TYPE,
	    lnValorMontoReclamo           EJE_CARGA_MASIV_EJECU.VAL_MONT_RECL%TYPE,
	    codigoTipoAbono               EJE_CARGA_MASIV_EJECU.COD_ABON%TYPE,
	    valorTipoOperacion            EJE_CARGA_MASIV_EJECU.VAL_TIPO_OPER%TYPE,
	    codigoMotivoRechazo           EJE_CARGA_MASIV_EJECU.COD_MOTI_RECH%TYPE,
    numeroFila         EJE_CARGA_MASIV_EJECU.NUM_FILA%TYPE,
	    estadoProceso                 EJE_CARGA_MASIV_EJECU.EST_PROC%TYPE
  );

  TYPE interfazProgramasTab  IS TABLE OF interfazProgramas;
  interfazRecordN interfazProgramasTab;

  lnOidPais                  SEG_PAIS.OID_PAIS%TYPE;
  lsCodigoCliente            EJE_CARGA_MASIV_EJECU.CLIE_COD_CLIE%TYPE;
  lsCodigoMotivo             EJE_MOTIV_RECHA_CARGA.COD_MOTI_RECH%TYPE;
	  lnCampanyaInicio           EJE_CARGA_MASIV_EJECU.CAM_INIC%TYPE;
	  lnCampanyaFinal            EJE_CARGA_MASIV_EJECU.CAM_FINA%TYPE;
	  lnCodigoLista              EJE_CARGA_MASIV_EJECU.COD_LIST%TYPE;
	  lsCodigoNivel              EJE_CARGA_MASIV_EJECU.COD_NIVE%TYPE;
	  lsCodigoFase               EJE_CARGA_MASIV_EJECU.COD_FASE%TYPE;
	  lnNumeroFila               EJE_CARGA_MASIV_EJECU.NUM_FILA%TYPE;
	  lnCodigoTipoAbono          EJE_CARGA_MASIV_EJECU.COD_ABON%TYPE;
	  lnValorMontoReclamo        EJE_CARGA_MASIV_EJECU.VAL_MONT_RECL%TYPE;
	  lnEstadoProceso            NUMBER(1);
  lnCantidad                 NUMBER(2);
	  lnTraslape                 NUMBER(2);
	  lnExiste                   NUMBER(2);

BEGIN

  --Recuperamos el oid Pais
  lnOidPais := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodigoPais);

	  lnEstadoProceso := 2;
	  lsCodigoMotivo := NULL;
	
 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_programas;
  LOOP
    FETCH c_programas BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
	        lsCodigoCliente      :=  interfazRecordN(x).codigoCliente;
	        lnCampanyaInicio     :=  interfazRecordN(x).campanyaInicio;
	        lnCampanyaFinal      :=  interfazRecordN(x).campanyaFinal;
	        lnCodigoLista        :=  interfazRecordN(x).codigoLista;
	        lsCodigoNivel        :=  interfazRecordN(x).codigoNivel;
	        lsCodigoFase         :=  interfazRecordN(x).codigoFase;
	        lnCodigoTipoAbono    :=  interfazRecordN(x).codigoTipoAbono;
        lnNumeroFila :=  interfazRecordN(x).numeroFila;

	        CASE lnCodigoLista
	          WHEN '01' THEN
        --(1), Validamos si existe el Codigo de Cliente
	            SELECT COUNT(1)
	              INTO lnExiste
            FROM MAE_CLIEN
           WHERE PAIS_OID_PAIS = lnOidPais
             AND COD_CLIE = lsCodigoCliente;

	            IF lnExiste = 0 THEN              
	              SELECT COD_MOTI_RECH
	                INTO lsCodigoMotivo
	                FROM EJE_MOTIV_RECHA_CARGA
	               WHERE COD_MOTI_RECH = '01'
	                 AND IND_ACTI = 1
	                 AND EST_REGI = 1;
	                 
	              lnEstadoProceso :=1;
	            ELSE
	              --(2), Validamos si la campañia inicial existe
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM SEG_PERIO_CORPO
	               WHERE COD_PERI = lnCampanyaInicio;
	              
	              IF lnExiste = 0 THEN
	                SELECT COD_MOTI_RECH
	                  INTO lsCodigoMotivo
	                  FROM EJE_MOTIV_RECHA_CARGA
	                 WHERE COD_MOTI_RECH = '02'
	                   AND IND_ACTI = 1
	                   AND EST_REGI = 1;

	                lnEstadoProceso :=1;
        ELSE
	                --(3), Validamos si la campañia final existe
                SELECT COUNT(1)
	                  INTO lnExiste
	                  FROM SEG_PERIO_CORPO
	                 WHERE COD_PERI = lnCampanyaFinal;
                
	                IF lnExiste = 0 THEN
	                  SELECT COD_MOTI_RECH
	                    INTO lsCodigoMotivo
	                    FROM EJE_MOTIV_RECHA_CARGA
	                   WHERE COD_MOTI_RECH = '02'
	                     AND IND_ACTI = 1
	                     AND EST_REGI = 1;

	                  lnEstadoProceso :=1;
        ELSE
	                  --(4), Validamos que la campañia inicial y final no se traslapen
                SELECT COUNT(1)
	                    INTO lnCantidad
	                    FROM EJE_CLIEN_EXCLU
	                   WHERE PAIS_COD_PAIS = psCodigoPais
	                     AND COD_PROG_EJEC = pnCodigoPrograma
	                     AND CLIE_COD_CLIE = lsCodigoCliente
	                     AND IND_ACTI = 1;
                   
	                  IF lnCantidad > 0 THEN
	                    SELECT sum(CASE WHEN ((lnCampanyaInicio >= COD_CAMP_INIC) AND (lnCampanyaInicio <= COD_CAMP_FINA)) OR 
	                                         ((lnCampanyaFinal >= COD_CAMP_INIC) AND (lnCampanyaFinal <= COD_CAMP_FINA)) OR 
	                                         ((lnCampanyaInicio < COD_CAMP_INIC) AND (lnCampanyaFinal > COD_CAMP_FINA))
	                                    THEN 1 ELSE 0 END)
	                      INTO lnTraslape
	                      FROM EJE_CLIEN_EXCLU
	                     WHERE PAIS_COD_PAIS = psCodigoPais
	                       AND COD_PROG_EJEC = pnCodigoPrograma
	                       AND CLIE_COD_CLIE = lsCodigoCliente
	                       AND IND_ACTI = 1;
                
	                    IF lnTraslape IS NOT NULL AND lnTraslape > 0 THEN
	                      SELECT COD_MOTI_RECH
	                        INTO lsCodigoMotivo
	                        FROM EJE_MOTIV_RECHA_CARGA
	                       WHERE COD_MOTI_RECH = '02'
	                         AND IND_ACTI = 1
	                         AND EST_REGI = 1;

	                      lnEstadoProceso :=1;
	                    END IF;
                          ELSE
                            SELECT COUNT(1)
                              INTO lnExiste
                              FROM EJE_CARGA_MASIV_EJECU
                             WHERE VAL_TIPO_OPER = 'E'
                               AND CAM_INIC IS NULL
                                OR CAM_FINA IS NULL;
                            
                            IF lnExiste > 0 THEN
                              SELECT COD_MOTI_RECH
                                INTO lsCodigoMotivo
                                FROM EJE_MOTIV_RECHA_CARGA
                               WHERE COD_MOTI_RECH = '02'
                                 AND IND_ACTI = 1
                                 AND EST_REGI = 1;

                              lnEstadoProceso :=1;
                            END IF;
	                  END IF;   
                END IF;
	              END IF;
                    END IF;
                
	          WHEN '02' THEN
	            --(1), Validamos si existe el Codigo de Cliente
	            SELECT COUNT(1)
	              INTO lnExiste
	              FROM MAE_CLIEN
	             WHERE PAIS_OID_PAIS = lnOidPais
	               AND COD_CLIE = lsCodigoCliente;

	            IF lnExiste = 0 THEN
	              SELECT COD_MOTI_RECH
	                INTO lsCodigoMotivo
	                FROM EJE_MOTIV_RECHA_CARGA
	               WHERE COD_MOTI_RECH = '01'
	                 AND IND_ACTI = 1
	                 AND EST_REGI = 1;

	              lnEstadoProceso :=1;
	            ELSE
	              --(2), Validamos si existe un Nivel Ejecutiva
	              SELECT COUNT(1) 
	                INTO lnExiste
	                FROM EJE_NIVEL_PROGR
	               WHERE pais_cod_pais  = psCodigoPais
	                 AND cod_prog_ejec  = pnCodigoPrograma
	                 AND cod_nive = lsCodigoNivel;

	              IF lnExiste = 0 THEN
	                SELECT COD_MOTI_RECH
	                  INTO lsCodigoMotivo
	                  FROM EJE_MOTIV_RECHA_CARGA
	                 WHERE COD_MOTI_RECH = '03'
	                   AND IND_ACTI = 1
	                   AND EST_REGI = 1;

	                lnEstadoProceso :=1;
	              ELSE
	                --(3), Validamos si existe un Codigo Fase
                    SELECT COUNT(1)
	                  INTO lnExiste
	                  FROM EJE_FASES_PROGR
	                 WHERE pais_cod_pais  = psCodigoPais
	                   AND cod_prog_ejec  = pnCodigoPrograma
	                   AND cod_fase  = lsCodigoFase;

	                IF lnExiste = 0 THEN
	                  SELECT COD_MOTI_RECH
	                    INTO lsCodigoMotivo
	                    FROM EJE_MOTIV_RECHA_CARGA
	                   WHERE COD_MOTI_RECH = '04'
	                     AND IND_ACTI = 1
	                     AND EST_REGI = 1;

	                  lnEstadoProceso :=1;
                    ELSE
	                  --(4), Validamos si existe el codigo de cliente es una Ejecutiva
                    SELECT COUNT(1)
	                    INTO lnExiste
	                    FROM ZON_HISTO_GEREN
	                   WHERE gere = lsCodigoCliente
	                     AND perd_oid_peri_hast IS NULL
	                     AND cod_secc IS NOT NULL;
	                  
	                  IF lnExiste = 0 THEN
	                    SELECT COD_MOTI_RECH
	                      INTO lsCodigoMotivo
	                      FROM EJE_MOTIV_RECHA_CARGA
	                     WHERE COD_MOTI_RECH = '09'
	                       AND IND_ACTI = 1
	                       AND EST_REGI = 1;

	                    lnEstadoProceso :=1;
                    ELSE
                            --(5), Validamos la existencia en la entidad nivel campaña
                        SELECT COUNT(1)
                              INTO lnExiste
                              FROM EJE_NIVEL_CAMPA
                             WHERE PAIS_COD_PAIS = psCodigoPais
                               AND COD_PROG_EJEC = pnCodigoPrograma
                               AND CLIE_COD_CLIE = lsCodigoCliente
                               AND COD_FASE = lsCodigoFase;
                            
                            IF lnExiste > 0 THEN
                              SELECT COD_MOTI_RECH
                                INTO lsCodigoMotivo
                                FROM EJE_MOTIV_RECHA_CARGA
                               WHERE COD_MOTI_RECH = '10'
                                 AND IND_ACTI = 1
                                 AND EST_REGI = 1;

                              lnEstadoProceso :=1;
                            END IF;
	                  END IF;
                        END IF;
                    END IF;
                END IF;

	          WHEN '03' THEN
	            --(1), Validamos si existe el Codigo de Cliente
                SELECT COUNT(1)
	              INTO lnExiste
	              FROM MAE_CLIEN
	             WHERE PAIS_OID_PAIS = lnOidPais
	               AND COD_CLIE = lsCodigoCliente;

	            IF lnExiste = 0 THEN
	              SELECT COD_MOTI_RECH
	                INTO lsCodigoMotivo
	                FROM EJE_MOTIV_RECHA_CARGA
	               WHERE COD_MOTI_RECH = '01'
	                 AND IND_ACTI = 1
	                 AND EST_REGI = 1;

	              lnEstadoProceso :=1;
	            ELSE
	              --(2), Validamos si existe el codigo de cliente es una Ejecutiva
                SELECT COUNT(1)
	                INTO lnExiste
	                FROM ZON_HISTO_GEREN
	               WHERE gere = lsCodigoCliente
	                 AND perd_oid_peri_hast IS NULL
	                 AND cod_secc IS NOT NULL;
                 
	              IF lnExiste = 0 THEN
	                SELECT COD_MOTI_RECH
	                  INTO lsCodigoMotivo
	                  FROM EJE_MOTIV_RECHA_CARGA
	                 WHERE COD_MOTI_RECH = '09'
                   AND IND_ACTI = 1
	                   AND EST_REGI = 1;

	                lnEstadoProceso :=1;
                ELSE
                        --(3), Validamos la existencia en la entidad nivel campaña
                    SELECT COUNT(1)
                          INTO lnExiste
                          FROM EJE_NIVEL_CAMPA
                         WHERE PAIS_COD_PAIS = psCodigoPais
                           AND COD_PROG_EJEC = pnCodigoPrograma
                           AND CLIE_COD_CLIE = lsCodigoCliente
                           AND COD_FASE = lsCodigoFase;

                        IF lnExiste > 0 THEN
                          SELECT COD_MOTI_RECH
                            INTO lsCodigoMotivo
                            FROM EJE_MOTIV_RECHA_CARGA
                           WHERE COD_MOTI_RECH = '10'
                             AND IND_ACTI = 1
                             AND EST_REGI = 1;
                              
                          lnEstadoProceso :=1;
                    END IF;
                END IF;
                END IF;
            
	          WHEN '04' THEN
                    --(1), Validamos si existe el codigo de cliente en el Maestro de Clientes
                SELECT COUNT(1)
	              INTO lnExiste
	              FROM MAE_CLIEN
	             WHERE PAIS_OID_PAIS = lnOidPais
	               AND COD_CLIE = lsCodigoCliente;
	               
	            IF lnExiste = 0 THEN
	              SELECT COD_MOTI_RECH
	                INTO lsCodigoMotivo
	                FROM EJE_MOTIV_RECHA_CARGA
	               WHERE COD_MOTI_RECH = '01'
                   AND IND_ACTI = 1
	                 AND EST_REGI = 1;
                
	              lnEstadoProceso :=1;
                ELSE
	              --(2), Validamos si existe el codigo de cliente es una Ejecutiva
                    SELECT COUNT(1)
	                INTO lnExiste
	                FROM ZON_HISTO_GEREN
	               WHERE gere = lsCodigoCliente
	                 AND perd_oid_peri_hast IS NULL
	                 AND cod_secc IS NOT NULL;
                     
	              IF lnExiste = 0 THEN
	                SELECT COD_MOTI_RECH
	                  INTO lsCodigoMotivo
	                  FROM EJE_MOTIV_RECHA_CARGA
	                 WHERE COD_MOTI_RECH = '09'
	                   AND IND_ACTI = 1
	                   AND EST_REGI = 1;
            
	                lnEstadoProceso :=1;
	              ELSE
                        --(3), Validamos si existe en entidad Tipo de Abono
                SELECT COUNT(1)
	                  INTO lnExiste
	                  FROM EJE_TIPO_ABONO
	                 WHERE ind_acti = 1
	                   AND pais_cod_pais = psCodigoPais
	                   AND cod_prog_ejec = pnCodigoPrograma
	                   AND cod_abon = lnCodigoTipoAbono;
	                   
	                IF lnExiste = 0 THEN
	                  SELECT COD_MOTI_RECH
	                    INTO lsCodigoMotivo
	                    FROM EJE_MOTIV_RECHA_CARGA
	                   WHERE COD_MOTI_RECH = '08'
                   AND IND_ACTI = 1
	                     AND EST_REGI = 1;

	                  lnEstadoProceso :=1;
                        ELSE
                          --(4), Validamos la existencia en la entidad nivel campaña
                SELECT COUNT(1)
                            INTO lnExiste
                            FROM EJE_NIVEL_CAMPA
                           WHERE PAIS_COD_PAIS = psCodigoPais
                             AND COD_PROG_EJEC = pnCodigoPrograma
                             AND CLIE_COD_CLIE = lsCodigoCliente
                             AND COD_FASE = lsCodigoFase;
                            
                          IF lnExiste > 0 THEN
                            SELECT COD_MOTI_RECH
                              INTO lsCodigoMotivo
                              FROM EJE_MOTIV_RECHA_CARGA
                             WHERE COD_MOTI_RECH = '10'
                   AND IND_ACTI = 1
                               AND EST_REGI = 1;

                            lnEstadoProceso :=1;
                          END IF;
                END IF;
            END IF;
        END IF;
	        END CASE;

	        UPDATE EJE_CARGA_MASIV_EJECU
	           SET COD_MOTI_RECH = lsCodigoMotivo,
	               EST_PROC =  lnEstadoProceso
             WHERE NUM_REGI_CARG = pnNumeroCarga
               AND NUM_FILA= lnNumeroFila;

        END LOOP;
     END IF;
     EXIT WHEN c_programas%NOTFOUND;
  END LOOP;
  CLOSE c_programas;

EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR PEJ_PR_VALID_CARGA_PROGR: ('|| ln_sqlcode || ')' || ls_sqlerrm);
END PEJ_PR_VALID_CARGA_PROGR;

/***************************************************************************
Descripcion       : Actualiza Carga de Programa Ejecutivas
Fecha Creacion    : 01/03/2013
Autor             : Aurelio Oviedo
     Fecha Modificación : 29/06/2013
     Autor              : Sebastian Guerra
***************************************************************************/
      PROCEDURE PEJ_PR_ACTUA_CARGA_PROGR(psCodigoPais               VARCHAR2,
   pnNumeroCarga              NUMBER,
   pnCampanya                 VARCHAR2,
   pnCodigoPrograma           VARCHAR2,
	   pnTipoCarga                VARCHAR2,
   pnCodigoUsuario            VARCHAR2)
IS

  CURSOR c_programas IS
	    SELECT NUM_REGI_CARG,	CLIE_COD_CLIE,	COD_CAMP_REFE,	COD_LIST,	CAM_INIC,	CAM_FINA,	COD_NIVE,	COD_FASE,	VAL_META_PEDI,	
	           VAL_META_INGR,	VAL_META_RECA, VAL_MONT_RECL, COD_ABON,	VAL_TIPO_OPER,	COD_MOTI_RECH,	NUM_FILA,	EST_PROC 
	      FROM EJE_CARGA_MASIV_EJECU
     WHERE NUM_REGI_CARG = pnNumeroCarga
	       AND COD_CAMP_REFE = pnCampanya
	       AND EST_PROC = 2
	       AND COD_LIST = pnTipoCarga
	       AND VAL_TIPO_OPER IN('E','N','A');

  TYPE interfazProgramas IS RECORD
  (
	    numeroRegistroCarga           EJE_CARGA_MASIV_EJECU.NUM_REGI_CARG%TYPE,
	    codigoCliente                 EJE_CARGA_MASIV_EJECU.CLIE_COD_CLIE%TYPE,
	    campanyaReferencia            EJE_CARGA_MASIV_EJECU.COD_CAMP_REFE%TYPE,
	    codigoLista                   EJE_CARGA_MASIV_EJECU.COD_LIST%TYPE,
	    campanyaInicio                EJE_CARGA_MASIV_EJECU.CAM_INIC%TYPE,
	    campanyaFinal                 EJE_CARGA_MASIV_EJECU.CAM_FINA%TYPE,
	    codigoNivel                   EJE_CARGA_MASIV_EJECU.COD_NIVE%TYPE,
	    codigoFase                    EJE_CARGA_MASIV_EJECU.COD_FASE%TYPE,
	    valorMetaPedido               EJE_CARGA_MASIV_EJECU.VAL_META_PEDI%TYPE,
	    valorMetaIngreso              EJE_CARGA_MASIV_EJECU.VAL_META_INGR%TYPE,
	    valorMetaRechazo              EJE_CARGA_MASIV_EJECU.VAL_META_RECA%TYPE,
	    valorMontoReclamo             EJE_CARGA_MASIV_EJECU.VAL_MONT_RECL%TYPE,
	    codigoTipoAbono               EJE_CARGA_MASIV_EJECU.COD_ABON%TYPE,
	    valorTipoOperacion            EJE_CARGA_MASIV_EJECU.VAL_TIPO_OPER%TYPE,
	    codigoMotivoRechazo           EJE_CARGA_MASIV_EJECU.COD_MOTI_RECH%TYPE,
    numeroFila         EJE_CARGA_MASIV_EJECU.NUM_FILA%TYPE,
	    estadoProceso                 EJE_CARGA_MASIV_EJECU.EST_PROC%TYPE
  );

  TYPE interfazProgramasTab  IS TABLE OF interfazProgramas;
  interfazRecordN interfazProgramasTab;

	  lsCodigoCliente       EJE_CARGA_MASIV_EJECU.CLIE_COD_CLIE%TYPE;
	  lnValorTipoOperacion  EJE_CARGA_MASIV_EJECU.VAL_TIPO_OPER%TYPE;
	  lnCampanyaInicio      EJE_CARGA_MASIV_EJECU.CAM_INIC%TYPE;
	  lnCampanyaFinal       EJE_CARGA_MASIV_EJECU.CAM_FINA%TYPE;
	  lnCodigoLista         EJE_CARGA_MASIV_EJECU.COD_LIST%TYPE;
	  lsCodigoFase          EJE_CARGA_MASIV_EJECU.COD_FASE%TYPE;
	  lsCodigoNivel         EJE_CARGA_MASIV_EJECU.COD_NIVE%TYPE;
	  lsCodigoTipoAbono     EJE_CARGA_MASIV_EJECU.COD_ABON%TYPE;
	  lnValorMetaPedido     EJE_CARGA_MASIV_EJECU.VAL_META_PEDI%TYPE;
	  lnValorMetaIngreso    EJE_CARGA_MASIV_EJECU.VAL_META_INGR%TYPE;
	  lnValorMetaRechazo    EJE_CARGA_MASIV_EJECU.VAL_META_RECA%TYPE;
	  lnValorMontoReclamo   EJE_CARGA_MASIV_EJECU.VAL_MONT_RECL%TYPE;
	  lnNumeroPedidoMinimo  EJE_NIVEL_PROGR.NUM_PEDI_MINI%TYPE;
  lnNumeroFila               EJE_CARGA_MASIV_EJECU.NUM_FILA%TYPE;
	  lnExiste              NUMBER(2);
	  lnCodigoSeccion       VARCHAR2(5);

BEGIN

 --(1) PROCESAMOS A LOS CLIENTES
  OPEN c_programas;
  LOOP
    FETCH c_programas BULK COLLECT INTO interfazRecordN LIMIT W_FILAS;
    IF interfazRecordN.COUNT > 0 THEN

      FOR x IN interfazRecordN.FIRST .. interfazRecordN.LAST LOOP
	        lsCodigoCliente        :=  interfazRecordN(x).codigoCliente;
	        lnValorTipoOperacion   :=  interfazRecordN(x).valorTipoOperacion;
	        lnCampanyaInicio       :=  interfazRecordN(x).campanyaInicio;
	        lnCampanyaFinal        :=  interfazRecordN(x).campanyaFinal;
	        lnCodigoLista          :=  interfazRecordN(x).codigoLista;
	        lsCodigoFase           :=  interfazRecordN(x).codigoFase;
	        lsCodigoNivel          :=  interfazRecordN(x).codigoNivel;
	        lsCodigoTipoAbono      :=  interfazRecordN(x).codigoTipoAbono;
	        lnValorMetaPedido      :=  interfazRecordN(x).valorMetaPedido;
	        lnValorMontoReclamo    :=  interfazRecordN(x).valorMontoReclamo;
        lnNumeroFila :=  interfazRecordN(x).numeroFila;


	        CASE lnCodigoLista
	          WHEN '01' THEN
	            IF lnValorTipoOperacion ='E' THEN
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_CLIEN_EXCLU
	               WHERE PAIS_COD_PAIS = psCodigoPais
	                 AND COD_PROG_EJEC = pnCodigoPrograma
	                 AND CLIE_COD_CLIE = lsCodigoCliente
	                 AND IND_ACTI = 1;
	                 
	              IF lnExiste > 0 THEN
	                UPDATE EJE_CLIEN_EXCLU
	                  SET COD_CAMP_FINA = pnCampanya,
	                      IND_ACTI      = 0,
	                      usu_modi      = pnCodigoUsuario,
	                      fec_modi      = SYSDATE
	                WHERE PAIS_COD_PAIS = psCodigoPais
	                  AND COD_PROG_EJEC = pnCodigoPrograma
	                  AND CLIE_COD_CLIE = lsCodigoCliente
	                  AND IND_ACTI = 1;
	              END IF;
	            END IF;
	
	            IF lnValorTipoOperacion ='N' THEN
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_CLIEN_EXCLU
	               WHERE PAIS_COD_PAIS = psCodigoPais
	                 AND COD_PROG_EJEC = pnCodigoPrograma
	                 AND CLIE_COD_CLIE = lsCodigoCliente
	                 AND IND_ACTI = 1;

	              IF lnExiste = 0 THEN
	                INSERT INTO EJE_CLIEN_EXCLU
	                  (PAIS_COD_PAIS,
	                   COD_PROG_EJEC,
	                   CLIE_COD_CLIE,
	                   COD_CAMP_INIC,
	                   COD_CAMP_FINA,
	                   IND_ACTI,
	                   USU_CREA,
	                   FEC_CREA)
            VALUES
	                  (psCodigoPais,
	                   pnCodigoPrograma,
	                   lsCodigoCliente,
	                   lnCampanyaInicio,
	                   lnCampanyaFinal,
	                   1,
	                   pnCodigoUsuario,
	                   SYSDATE);
	              END IF;
	            END IF;
        
	          WHEN '02' THEN
	            IF lnValorTipoOperacion ='E' THEN
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_NIVEL_CAMPA
	               WHERE PAIS_COD_PAIS = psCodigoPais
	                 AND COD_PROG_EJEC = pnCodigoPrograma
	                 AND CLIE_COD_CLIE = lsCodigoCliente
	                 AND cod_fase = lsCodigoFase;
                
	              IF lnExiste > 0 THEN
	                SELECT COUNT(1)
	                  INTO lnExiste
                        FROM eje_evalu_secci_campa evse,
                             eje_fases_progr fapr
                       WHERE evse.pais_cod_pais = psCodigoPais
                         AND evse.cod_prog_ejec = pnCodigoPrograma
                         AND evse.clie_cod_clie = lsCodigoCliente
                         --
                         AND evse.pais_cod_pais = fapr.pais_cod_pais
                         AND evse.cod_prog_ejec = fapr.cod_prog_ejec
                         AND evse.cam_proc BETWEEN fapr.cod_camp_inic AND fapr.cod_camp_fina
                           ;
                      /*SELECT COUNT(1)
                        INTO lnExiste
	                  FROM EJE_EVALU_SECCI_CAMPA
	                 WHERE pais_cod_pais = psCodigoPais
	                   AND cod_prog_ejec = pnCodigoPrograma
	                   AND clie_cod_clie = lsCodigoCliente
                         AND cod_fase = lsCodigoFase;*/

	                IF lnExiste = 0 THEN
	                  DELETE FROM EJE_NIVEL_CAMPA
	                   WHERE PAIS_COD_PAIS = psCodigoPais
	                     AND COD_PROG_EJEC = pnCodigoPrograma
	                     AND CLIE_COD_CLIE = lsCodigoCliente
	                     AND cod_fase = lsCodigoFase;
	                END IF;  
	              END IF;
	            END IF;

	            IF lnValorTipoOperacion ='N' THEN
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_NIVEL_CAMPA
	               WHERE PAIS_COD_PAIS = psCodigoPais
	                 AND COD_PROG_EJEC = pnCodigoPrograma
	                 AND CLIE_COD_CLIE = lsCodigoCliente
	                 AND cod_fase = lsCodigoFase;

	              IF lnExiste = 0 THEN
	                INSERT INTO EJE_NIVEL_CAMPA
	                  (PAIS_COD_PAIS,
	                   COD_PROG_EJEC,
	                   CLIE_COD_CLIE,
	                   COD_FASE,
	                   COD_NIVE,
	                   ind_acti, 
	                   USU_CREA,
	                   FEC_CREA)
	                VALUES
	                  (psCodigoPais,
	                   pnCodigoPrograma,
	                   lsCodigoCliente,
	                   lsCodigoFase,
	                   lsCodigoNivel,
	                   1,
	                   pnCodigoUsuario,
	                   SYSDATE);
	              END IF;
	            END IF;

	            IF lnValorTipoOperacion ='A' THEN
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_NIVEL_CAMPA
	               WHERE PAIS_COD_PAIS = psCodigoPais
	                 AND COD_PROG_EJEC = pnCodigoPrograma
	                 AND CLIE_COD_CLIE = lsCodigoCliente
	                 AND cod_fase = lsCodigoFase;

	              IF lnExiste > 0 THEN
	                UPDATE EJE_NIVEL_CAMPA
	                   SET cod_nive = lsCodigoNivel,
	                       ind_acti = 1,
	                       usu_modi = pnCodigoUsuario,
	                       fec_actu = SYSDATE
	                 WHERE PAIS_COD_PAIS = psCodigoPais
	                   AND COD_PROG_EJEC = pnCodigoPrograma
	                   AND CLIE_COD_CLIE = lsCodigoCliente
	                   AND cod_fase = lsCodigoFase;
	              END IF;
	            END IF;

	          WHEN '03' THEN
	            IF lnValorTipoOperacion ='E' THEN
	              BEGIN
	                SELECT cod_zona || cod_secc 
	                  INTO lnCodigoSeccion
	                  FROM ZON_HISTO_GEREN
	                 WHERE gere = lsCodigoCliente
	                   AND perd_oid_peri_hast IS NULL
	                   AND cod_secc IS NOT NULL;
	              EXCEPTION
	                WHEN NO_DATA_FOUND THEN 
	                  lnCodigoSeccion := NULL;
	              END;

	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_METAS_SECCI
	               WHERE pais_cod_pais = psCodigoPais
	                 AND cod_prog_ejec = pnCodigoPrograma
	                 AND cod_secc = lnCodigoSeccion
	                 AND cam_proc = pnCampanya
	                 AND clie_cod_clie = lsCodigoCliente;

	              IF lnExiste > 0 THEN
	                SELECT COUNT(1)
	                  INTO lnExiste
	                  FROM EJE_EVALU_SECCI_CAMPA
	                 WHERE pais_cod_pais = psCodigoPais
	                   AND cod_prog_ejec = pnCodigoPrograma
	                   AND cod_secc = lnCodigoSeccion
	                   AND cam_proc = pnCampanya
	                   AND clie_cod_clie = lsCodigoCliente;

	                SELECT COUNT(1)
	                  INTO lnExiste
	                  FROM EJE_DETAL_MOVIM_SECCI
	                 WHERE pais_cod_pais = psCodigoPais
	                   AND cod_prog_ejec = pnCodigoPrograma
	                   AND cod_secc = lnCodigoSeccion
	                   AND cam_proc = pnCampanya
	                   AND clie_cod_clie = lsCodigoCliente;

	                SELECT COUNT(1)
	                  INTO lnExiste
	                  FROM EJE_LIQUI_CAMPA
	                 WHERE pais_cod_pais = psCodigoPais
	                   AND cod_prog_ejec = pnCodigoPrograma
	                   AND cam_proc = pnCampanya;

	                IF lnExiste = 0 THEN
	                  DELETE FROM EJE_METAS_SECCI
	                   WHERE pais_cod_pais = psCodigoPais
	                     AND cod_prog_ejec = pnCodigoPrograma
	                     AND cod_secc = lnCodigoSeccion
	                     AND cam_proc = pnCampanya
	                     AND clie_cod_clie = lsCodigoCliente;
	                END IF;
            END IF;
            END IF;

	            IF lnValorTipoOperacion ='N' THEN
	              BEGIN
	                SELECT cod_zona || cod_secc 
	                  INTO lnCodigoSeccion
	                  FROM ZON_HISTO_GEREN
	                 WHERE gere = lsCodigoCliente
	                   AND perd_oid_peri_hast IS NULL
	                   AND cod_secc IS NOT NULL;
	              EXCEPTION
	                WHEN NO_DATA_FOUND THEN 
	                  lnCodigoSeccion := NULL;
	              END;
	              
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_METAS_SECCI
	               WHERE pais_cod_pais = psCodigoPais
	                 AND cod_prog_ejec = pnCodigoPrograma
	                 AND cod_secc = lnCodigoSeccion
	                 AND cam_proc = pnCampanya
	                 AND clie_cod_clie = lsCodigoCliente;
                               
	              IF lnExiste = 0 THEN
	                BEGIN
	                  SELECT B.NUM_PEDI_MINI
	                    INTO lnNumeroPedidoMinimo
	                    FROM EJE_NIVEL_CAMPA A, EJE_NIVEL_PROGR B
	                   WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
	                     AND A.COD_PROG_EJEC = B.COD_PROG_EJEC
	                     AND A.COD_NIVE = B.COD_NIVE
	                     AND A.IND_ACTI = 1
	                     AND A.pais_cod_pais = psCodigoPais
	                     AND A.cod_prog_ejec = pnCodigoPrograma
	                     AND A.clie_cod_clie = lsCodigoCliente
	                     AND A.cod_fase = lsCodigoFase;
	                EXCEPTION
	                 WHEN NO_DATA_FOUND THEN
	                   lnNumeroPedidoMinimo :=NULL;
	                END;

	                INSERT INTO EJE_METAS_SECCI
	                  (PAIS_COD_PAIS,
	                   COD_PROG_EJEC,
	                   COD_SECC,
	                   CAM_PROC,
	                   CLIE_COD_CLIE,
	                   VAL_META_PEDI,
	                   VAL_META_INGR,
	                   VAL_META_RECA,
	                   NUM_ACTI_FINA,
	                   NUM_MINI_PEDI,
	                   NUM_CLIE_EXCL,
	                   USU_CREA,
	                   FEC_CREA)
	                VALUES
	                  (psCodigoPais,
	                   pnCodigoPrograma,
	                   lnCodigoSeccion,
	                   pnCampanya,
	                   lsCodigoCliente,
	                   lnValorMetaPedido,
	                   lnValorMetaIngreso,
	                   lnValorMetaRechazo,
	                   0,
	                   lnNumeroPedidoMinimo,
	                   0,
	                   pnCodigoUsuario,
	                   SYSDATE);
	              END IF;
	            END IF;

	            IF lnValorTipoOperacion ='A' THEN
	              BEGIN
	                SELECT cod_zona || cod_secc 
	                  INTO lnCodigoSeccion
	                  FROM ZON_HISTO_GEREN
	                 WHERE gere = lsCodigoCliente
	                   AND perd_oid_peri_hast IS NULL
	                   AND cod_secc IS NOT NULL;
	              EXCEPTION
	                WHEN NO_DATA_FOUND THEN 
	                  lnCodigoSeccion := NULL;
	              END;

	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_METAS_SECCI
	               WHERE pais_cod_pais = psCodigoPais
	                 AND cod_prog_ejec = pnCodigoPrograma
	                 AND cod_secc = lnCodigoSeccion
	                 AND cam_proc = pnCampanya
	                 AND clie_cod_clie = lsCodigoCliente;

	              IF lnExiste > 0 THEN
	                BEGIN
	                  SELECT B.NUM_PEDI_MINI
	                    INTO lnNumeroPedidoMinimo
	                    FROM EJE_NIVEL_CAMPA A, EJE_NIVEL_PROGR B
	                   WHERE A.PAIS_COD_PAIS = B.PAIS_COD_PAIS
	                     AND A.COD_PROG_EJEC = B.COD_PROG_EJEC
	                     AND A.COD_NIVE = B.COD_NIVE
	                     AND A.IND_ACTI = 1
	                     AND A.pais_cod_pais = psCodigoPais
	                     AND A.cod_prog_ejec = pnCodigoPrograma
	                     AND A.clie_cod_clie = lsCodigoCliente
	                     AND A.cod_fase = lsCodigoFase;
	                EXCEPTION
	                 WHEN NO_DATA_FOUND THEN
	                   lnNumeroPedidoMinimo :=NULL;
	                END;

	                UPDATE EJE_METAS_SECCI
	                   SET VAL_META_PEDI = lnValorMetaPedido,
	                       VAL_META_INGR = lnValorMetaIngreso,
	                       VAL_META_RECA = lnValorMetaRechazo,
	                       NUM_ACTI_FINA = 0,
	                       NUM_MINI_PEDI = lnNumeroPedidoMinimo,
	                       NUM_CLIE_EXCL = 0,
	                       USU_MODI      = pnCodigoUsuario,
	                       FEC_MODI      = SYSDATE
	                 WHERE PAIS_COD_PAIS = psCodigoPais
	                   AND COD_PROG_EJEC = pnCodigoPrograma
	                   AND COD_SECC = lnCodigoSeccion
	                   AND CAM_PROC = pnCampanya
	                   AND CLIE_COD_CLIE = lsCodigoCliente;
	              END IF;
	            END IF;

	          WHEN '04' THEN
	            IF lnValorTipoOperacion ='E' THEN
	              SELECT COUNT(1) 
	                INTO lnExiste
	                FROM EJE_LIQUI_CAMPA
	               WHERE pais_cod_pais  = psCodigoPais
	                 AND cod_prog_ejec  = pnCodigoPrograma
	                 AND cod_abon = lsCodigoTipoAbono
	                 AND clie_cod_clie = lsCodigoCliente
	                 AND cam_proc = pnCampanya
	                 AND ind_proc = 0
	                 AND ind_orig_proc = 'X';

	              IF lnExiste > 0 THEN
	                DELETE FROM EJE_LIQUI_CAMPA
	                 WHERE pais_cod_pais = psCodigoPais
	                   AND cod_prog_ejec = pnCodigoPrograma
	                   AND cod_abon = lsCodigoTipoAbono
	                   AND clie_cod_clie = lsCodigoCliente
	                   AND cam_proc = pnCampanya
	                   AND ind_proc = 0
	                   AND ind_orig_proc = 'X';
	              END IF;
	            END IF;

	            IF lnValorTipoOperacion ='N' THEN
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_LIQUI_CAMPA
	               WHERE pais_cod_pais = psCodigoPais
	                 AND cod_prog_ejec = pnCodigoPrograma
	                 AND cod_abon = lsCodigoTipoAbono
	                 AND clie_cod_clie = lsCodigoCliente
	                 AND cam_proc = pnCampanya;

	              IF lnExiste = 0 THEN                
	                INSERT INTO EJE_LIQUI_CAMPA
	                  ( PAIS_COD_PAIS,
	                    COD_PROG_EJEC,
	                    COD_ABON,
	                    clie_cod_clie,
	                    CAM_PROC,
	                    VAL_MONT_LIQU,
	                    IND_PROC,
	                    IND_ORIG_PROC,
	                    USU_CREA,
	                    FEC_CREA)
            VALUES
	                  (psCodigoPais,
	                   pnCodigoPrograma,
	                   lsCodigoTipoAbono,
	                   lsCodigoCliente,
	                   pnCampanya,
	                   lnValorMontoReclamo,
	                   0,
	                   'X',
	                   pnCodigoUsuario,
	                   SYSDATE);
	              END IF;
        END IF;

	            IF lnValorTipoOperacion ='A' THEN
	              SELECT COUNT(1)
	                INTO lnExiste
	                FROM EJE_LIQUI_CAMPA
	               WHERE pais_cod_pais = psCodigoPais
	                 AND cod_prog_ejec = pnCodigoPrograma
	                 AND cod_abon = lsCodigoTipoAbono
	                 AND clie_cod_clie = lsCodigoCliente
	                 AND cam_proc = pnCampanya
	                 AND ind_proc = 0
	                 AND ind_orig_proc = 'X';

	              IF lnExiste > 0 THEN
	               UPDATE EJE_LIQUI_CAMPA
	                  SET VAL_MONT_LIQU = lnValorMontoReclamo,
	                      USU_MODI      = pnCodigoUsuario,
	                      FEC_MODI      = SYSDATE
	                WHERE pais_cod_pais = psCodigoPais
	                  AND cod_prog_ejec = pnCodigoPrograma
	                  AND cod_abon = lsCodigoTipoAbono
	                  AND clie_cod_clie = lsCodigoCliente
	                  AND cam_proc = pnCampanya
	                  AND ind_proc = 0
	                  AND ind_orig_proc = 'X';
	              END IF;
	            END IF;
	        END CASE;
        END LOOP;
     END IF;
     EXIT WHEN c_programas%NOTFOUND;
  END LOOP;
  CLOSE c_programas;

	  --Actualiza la fila procesada
	  UPDATE EJE_CARGA_MASIV_EJECU
	     SET EST_PROC = 3
	   WHERE NUM_REGI_CARG = pnNumeroCarga;
	     
EXCEPTION
WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR(-20123, 'ERROR PEJ_PR_ACTUA_CARGA_PROGR: ('|| lsCodigoCliente || ' - ' || ln_sqlcode || ')' || ls_sqlerrm);
END PEJ_PR_ACTUA_CARGA_PROGR;


/***************************************************************************
 Descripcion       : Calcula avance de gestión
 Fecha Creacion    : 07/11/2013
 Autor             : Carlos Mori
 Fecha Modificación: -
 Autor             : -
***************************************************************************/
PROCEDURE PEJ_PR_CALCU_AVANC_GESTI(psCodigoPais         VARCHAR2,
                                   psCodigoMarca        VARCHAR2,
                                   psCodigoCanal        VARCHAR2,
                                   psCodigoPeriodo      VARCHAR2,
                                   psFechaFacturacion   VARCHAR2)
IS

CURSOR C_EJE_SECCI ( vnOidPeriodo NUMBER,
                     vsCodigoPrograma VARCHAR2,
                     vsCodigoFase VARCHAR2,
                     vnDiasRango NUMBER
                   ) IS
WITH temp AS
(
SELECT ejec.cod_regi,
       ejec.cod_zona,
       ejec.cod_secc,
       ejec.gere,
       (
        SELECT nica.cod_nive
          FROM eje_nivel_campa nica
         WHERE nica.pais_cod_pais = psCodigoPais
           AND nica.cod_prog_ejec = vsCodigoPrograma
           AND nica.clie_cod_clie = ejec.gere
           AND nica.cod_fase = vsCodigoFase
       ) cod_nive,
       CASE WHEN sca2.clie_oid_clie IS NOT NULL THEN 1 ELSE 0 END ind_paso_pedi,
       CASE WHEN cier.cod_regi IS NOT NULL THEN 1 ELSE 0 END ind_cier,
       CASE WHEN cier.cod_regi IS NOT NULL THEN cier.fec_cier + vnDiasRango ELSE TO_DATE( psFechaFacturacion, 'DD/MM/YYYY') END fec_limi_reca,
       (
         SELECT mese.val_meta_pedi
           FROM eje_metas_secci mese
          WHERE mese.pais_cod_pais = psCodigoPais
            AND mese.cod_prog_ejec = vsCodigoPrograma
            AND mese.cod_secc = ejec.cod_zona || ejec.cod_secc
            AND mese.clie_cod_clie = ejec.gere
            AND mese.cam_proc = psCodigoPeriodo
       ) val_nume_pedi_esti,
       pedi.val_nume_pedi_real,
       (
         SELECT mese.val_meta_ingr
           FROM eje_metas_secci mese
          WHERE mese.pais_cod_pais = psCodigoPais
            AND mese.cod_prog_ejec = vsCodigoPrograma
            AND mese.cod_secc = ejec.cod_zona || ejec.cod_secc
            AND mese.clie_cod_clie = ejec.gere
            AND mese.cam_proc = psCodigoPeriodo
       ) val_nume_ingr_esti,
       (
        SELECT COUNT(*) val_nume_ingr_secc
          FROM mae_clien_vincu cvin,
               mae_tipo_vincu tivc,
               mae_clien_prime_conta cprc,
               mae_clien vnte,
               mae_clien vndo,
               cra_perio perd,
               seg_perio_corpo peri,
               mae_clien_unida_admin cuad,
               zon_terri_admin ztad,
               zon_secci zscc,
               zon_zona zzon,
               zon_regio zorg,
               ped_solic_cabec_acum2 sca2
         WHERE cvin.tivc_oid_tipo_vinc = tivc.oid_tipo_vinc
           AND cvin.clie_oid_clie_vnte = vnte.oid_clie
           AND cvin.clie_oid_clie_vndo = vndo.oid_clie
           AND vndo.oid_clie = cprc.clie_oid_clie
           AND vndo.oid_clie = sca2.clie_oid_clie
           AND perd.peri_oid_peri = peri.oid_peri
           AND vndo.oid_clie = cuad.clie_oid_clie
           AND cuad.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND ztad.zscc_oid_secc = zscc.oid_secc
           AND zscc.zzon_oid_zona = zzon.oid_zona
           --
           AND vnOidPeriodo BETWEEN cuad.perd_oid_peri_ini AND NVL(cuad.perd_oid_peri_fin,vnOidPeriodo)
           --AND cuad.ind_acti = 1
           AND tivc.ind_reco = 1
           AND sca2.perd_oid_peri = vnOidPeriodo
           AND vnte.cod_clie = ejec.gere
           AND cprc.perd_oid_peri = vnOidPeriodo
           AND peri.cod_peri = psCodigoPeriodo
           AND zzon.cod_zona = ejec.cod_zona
           AND zorg.cod_regi = ejec.cod_regi
         GROUP BY zorg.cod_regi,
                  zzon.cod_zona
       ) val_nume_ingr_real,
       (
         SELECT mese.val_meta_reca
           FROM eje_metas_secci mese
          WHERE mese.pais_cod_pais = psCodigoPais
            AND mese.cod_prog_ejec = vsCodigoPrograma
            AND mese.cod_secc = ejec.cod_zona || ejec.cod_secc
            AND mese.clie_cod_clie = ejec.gere
            AND mese.cam_proc = psCodigoPeriodo
       ) val_porc_reca_esti,
       pedi.val_mont_vent_cata,
       NVL(eval.ind_proc,0) ind_proc
  FROM (
         SELECT gere.gere, gere.cod_regi, gere.cod_zona, gere.cod_secc, clie.oid_clie
           FROM zon_histo_geren gere,
                mae_clien clie
          WHERE gere.gere = clie.cod_clie
            AND gere.cod_regi IS NOT NULL
            AND gere.cod_zona IS NOT NULL
            AND gere.cod_secc IS NOT NULL
            AND vnOidPeriodo BETWEEN gere.perd_oid_peri_desd AND NVL( gere.perd_oid_peri_hast, vnOidPeriodo )
       ) ejec,
       ped_solic_cabec_acum2 sca2,
       (
        SELECT zorg.cod_regi, TRUNC( coci.fec_cier ) fec_cier
          FROM fac_contr_cierr coci,
               own_comun.fac_tipos_cierr tcie,
               zon_regio zorg
         WHERE coci.tcie_oid_tipo_cier = tcie.oid_tipo_cier
           AND coci.zorg_oid_regi = zorg.oid_regi
           AND coci.perd_oid_peri = vnOidPeriodo
           AND tcie.oid_tipo_cier =1
         GROUP BY zorg.cod_regi, TRUNC( coci.fec_cier )
       ) cier,
       (
        SELECT zorg.cod_regi,
               zzon.cod_zona,
               zscc.cod_secc,
               COUNT(*) val_nume_orde,
               COUNT( DISTINCT soca.clie_oid_clie ) val_nume_pedi_real,
               SUM( soca.val_prec_cata_tota_loca ) val_mont_vent_cata
          FROM ped_solic_cabec soca,
               ped_solic_cabec cons,
               ped_tipo_solic_pais tspa,
               ped_tipo_solic tsol,
               mae_clien clie,
               zon_terri_admin ztad,
               zon_secci zscc,
               zon_zona zzon,
               zon_regio zorg
         WHERE clie.oid_clie = soca.clie_oid_clie
           AND soca.soca_oid_soli_cabe = cons.oid_soli_cabe
           AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
           AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
           AND soca.ztad_oid_terr_admi = ztad.oid_terr_admi
           AND ztad.zscc_oid_secc = zscc.oid_secc
           AND zscc.zzon_oid_zona = zzon.oid_zona
           AND zzon.zorg_oid_regi = zorg.oid_regi
           --
           AND soca.perd_oid_peri = vnOidPeriodo
           AND soca.ind_oc = 1
           AND soca.grpr_oid_grup_proc = 5
           AND tsol.cod_tipo_soli = 'SOC'
           AND cons.esso_oid_esta_soli != 4
           AND clie.cod_clie NOT IN (
                                      SELECT clex.clie_cod_clie
                                        FROM eje_clien_exclu clex
                                       WHERE clex.pais_cod_pais = psCodigoPais
                                         AND clex.cod_prog_ejec = vsCodigoPrograma
                                         AND clex.ind_acti = 1
                                    )
         GROUP BY zorg.cod_regi,
                  zzon.cod_zona,
                  zscc.cod_secc
       ) pedi,
       (
        SELECT evse.cod_secc, evse.clie_cod_clie, evse.ind_proc
          FROM eje_evalu_secci_campa evse
         WHERE evse.pais_cod_pais = psCodigoPais
           AND evse.cod_prog_ejec = vsCodigoPrograma
           AND evse.cam_proc = psCodigoPeriodo
       ) eval
 WHERE ejec.cod_regi = cier.cod_regi(+)
   AND ejec.cod_regi = pedi.cod_regi(+)
   AND ejec.cod_zona = pedi.cod_zona(+)
   AND ejec.cod_secc = pedi.cod_secc(+)
   AND ejec.oid_clie = sca2.clie_oid_clie(+)
   AND sca2.perd_oid_peri(+) = vnOidPeriodo
   AND ejec.cod_zona || ejec.cod_secc = eval.cod_secc(+)
   AND ejec.gere = eval.clie_cod_clie(+)
   AND EXISTS (
               SELECT NULL
                 FROM eje_metas_secci mese
                WHERE mese.pais_cod_pais = psCodigoPais
                  AND mese.cod_prog_ejec = vsCodigoPrograma
                  AND mese.cod_secc = ejec.cod_zona || ejec.cod_secc
                  AND mese.clie_cod_clie = ejec.gere
                  AND mese.cam_proc = psCodigoPeriodo
              )
)
SELECT *
  FROM temp
     ;

TYPE EjeSecciRecTab IS TABLE OF c_eje_secci%ROWTYPE INDEX BY BINARY_INTEGER;
EjeSecciRecord EjeSecciRecTab;

CURSOR C_EJE_RECAUDO (
                      vnOidPeriodoAnte NUMBER,
                      vsCodigoPrograma VARCHAR2,
                      vsCodigoRegion VARCHAR2,
                      vsCodigoZona VARCHAR2,
                      vsCodigoSeccion VARCHAR2,
                      vsFechaLimite VARCHAR2
                     ) IS
WITH temp AS
(
SELECT zorg.cod_regi,
       zzon.cod_zona,
       zscc.cod_secc,
       clie.cod_clie,
       cons.oid_soli_cabe,
       cons.val_tota_paga_loca,
       cons.esso_oid_esta_soli,
       mvcc.subp_oid_subp_crea,
       mvcc.subp_oid_subp_ulti,
       mvcc.fec_ulti_movi,
       mvcc.val_ulti_nume_hist,
       NVL(mvcc.imp_movi,0) imp_movi,
       NVL(mvcc.imp_pago,0) imp_pago,
       NVL(mvcc.imp_pend,0) imp_pend,
       CASE
         WHEN mvcc.subp_oid_subp_ulti = 2003 AND mvcc.fec_ulti_movi <= TO_DATE(vsFechaLimite,'DD/MM/YYYY') THEN NVL(mvcc.imp_pago,0) ELSE 0
       END imp_pago_real,
       NVL((
            SELECT SUM ( hmcc.imp_pago )
              FROM ccc_histo_movim_cc hmcc
             WHERE hmcc.mvcc_oid_movi_cc = mvcc.oid_movi_cc
               AND hmcc.subp_oid_subp = 2003
               AND hmcc.fec_movi <= TO_DATE(vsFechaLimite,'DD/MM/YYYY')
           ),0) imp_pago_hist,
       CASE
         WHEN mvcc.subp_oid_subp_ulti = 2004 AND mvcc.fec_ulti_movi <= TO_DATE(psFechaFacturacion,'DD/MM/YYYY') THEN NVL(mvcc.imp_pago,0) ELSE 0
       END imp_aten_real,
       NVL((
            SELECT SUM ( hmcc.imp_pago )
              FROM ccc_histo_movim_cc hmcc
             WHERE hmcc.mvcc_oid_movi_cc = mvcc.oid_movi_cc
               AND hmcc.subp_oid_subp = 2004
               AND hmcc.fec_movi <= TO_DATE(psFechaFacturacion,'DD/MM/YYYY')
           ),0) imp_aten_hist,
       (( cons.val_impo_flet_tota_loca + cons.val_impo_impu_tota_loca ) / NVL(mvcc.imp_movi,1)) val_porc_desc
  FROM ped_solic_cabec soca,
       ped_solic_cabec cons,
       mae_clien clie,
       ped_tipo_solic_pais tspa,
       ped_tipo_solic tsol,
       zon_terri_admin ztad,
       zon_secci zscc,
       zon_zona zzon,
       zon_regio zorg,
       ccc_movim_cuent_corri mvcc
 WHERE soca.soca_oid_soli_cabe = cons.oid_soli_cabe(+)
   AND soca.clie_oid_clie = clie.oid_clie
   AND soca.tspa_oid_tipo_soli_pais = tspa.oid_tipo_soli_pais
   AND tspa.tsol_oid_tipo_soli = tsol.oid_tipo_soli
   AND soca.ztad_oid_terr_admi = ztad.oid_terr_admi
   AND ztad.zscc_oid_secc = zscc.oid_secc
   AND zscc.zzon_oid_zona = zzon.oid_zona
   AND zzon.zorg_oid_regi = zorg.oid_regi
   AND cons.oid_soli_cabe = mvcc.soca_oid_soli_cabe
   --
   AND soca.perd_oid_peri = vnOidPeriodoAnte
   AND tsol.cod_tipo_soli = 'SOC'
   AND soca.ind_oc = 1
   AND cons.esso_oid_esta_soli != 4
   AND zzon.cod_zona = vsCodigoZona
   AND zscc.cod_secc = vsCodigoSeccion
   AND mvcc.subp_oid_subp_crea = 2001
   AND clie.cod_clie NOT IN (
                              SELECT clex.clie_cod_clie
                                FROM eje_clien_exclu clex
                               WHERE clex.pais_cod_pais = psCodigoPais
                                 AND clex.cod_prog_ejec = vsCodigoPrograma
                                 AND clex.ind_acti = 1
                                 AND psCodigoPeriodo BETWEEN clex.cod_camp_inic AND clex.cod_camp_fina
                            )
)
SELECT cod_regi,
       cod_zona,
       cod_secc,
       SUM ( ROUND( imp_movi - (imp_movi * val_porc_desc),0) ) val_tota_carg,
       SUM ( ROUND( imp_pago_real - (imp_pago_real * val_porc_desc) ,0) ) val_tota_cobr,
       SUM ( ROUND( imp_pago_hist - (imp_pago_hist * val_porc_desc) ,0) ) val_tota_cobr_hist,
       SUM ( ROUND( imp_aten_real - (imp_aten_real * val_porc_desc) ,0) ) val_tota_aten,
       SUM ( ROUND( imp_aten_hist - (imp_aten_hist * val_porc_desc) ,0) ) val_tota_aten_hist
  FROM temp
 GROUP BY cod_regi, cod_zona, cod_secc
     ;

TYPE RecSecciRecTab IS TABLE OF c_eje_recaudo%ROWTYPE INDEX BY BINARY_INTEGER;
RecSecciRecord RecSecciRecTab;

aRegEva EJE_EVALU_SECCI_CAMPA%ROWTYPE;

-- Variables de control
lnFilas      NUMBER(12):= 50;
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);

-- Variables de trabajo
lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;
lnOidPeriodoAnterior CRA_PERIO.OID_PERI%TYPE;
ldFechaFinalReca CRA_PERIO.FEC_FINA%TYPE;
lnDiasRango NUMBER(12) := 0 ;
lnPorceNoCumple NUMBER(12) := 0;

lsCodigoPrograma EJE_PROGR.COD_PROG_EJEC%TYPE;
lsNivelEjecutiva EJE_NIVEL_CAMPA.COD_NIVE%TYPE;
lsCodigoFase EJE_FASES_PROGR.COD_FASE%TYPE;
lnOk NUMBER(1):=1;

BEGIN
    /*
      Se obtiene los Oids de la campaña actual y campaña anterior
    */
    
    lnOidPeriodo         := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodo );
    lnOidPeriodoAnterior := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( FIN_PKG_GENER.FIN_FN_CALCU_PERIO_NANTE( psCodigoPeriodo, 1 ) );

    /*
      Se obtiene el código del programa y fase activas en la campaña
    */
    
    BEGIN
       SELECT prog.cod_prog_ejec,
              (
                SELECT fapr.cod_fase
                  FROM eje_fases_progr fapr
                 WHERE fapr.pais_cod_pais = psCodigoPais
                   AND fapr.cod_prog_ejec = prog.cod_prog_ejec
                   AND psCodigoPeriodo BETWEEN fapr.cod_camp_inic AND fapr.cod_camp_fina
              ) cod_fase
         INTO lsCodigoPrograma,
              lsCodigoFase
          FROM eje_progr prog
         WHERE prog.ind_acti = 1
             ;
    EXCEPTION WHEN OTHERS THEN
       lnOk := 0;
    END;

    /*
      Se obtiene el número de días rango para el computo de los recaudos
    */
    
    BEGIN
       SELECT TO_NUMBER(epap.val_para)
         INTO lnDiasRango
          FROM eje_param_progr epap,
               eje_param parp
         WHERE epap.cod_para_prog = parp.cod_para_prog
           AND parp.cod_para_prog = '05'
             ;
    EXCEPTION WHEN OTHERS THEN
       lnDiasRango := 1;
    END;
    
    /*
      Se obtiene el porcentaje fijo para incumplimiento de metas.
      - Esto debe regularizarse agregando campos al constraints de las tablas relacionadas.
    */
    
    BEGIN
       SELECT TO_NUMBER(epap.val_para)
         INTO lnPorceNoCumple
          FROM eje_param_progr epap,
               eje_param parp
         WHERE epap.cod_para_prog = parp.cod_para_prog
           AND parp.cod_para_prog = '06'
             ;
    EXCEPTION WHEN OTHERS THEN
       lnPorceNoCumple := 0.5;
    END;

    /*
      Si existe un programa activo en la campaña, se realizan los cálculos correspondientes
      a los valores de la sección: Ingresos en la campaña, número de pedidos en la campaña,
      recaudos de la campaña anterior, monto acumulado de venta en la campaña.
    */
    
    IF lnOk = 1 THEN
        /*
          Se leen todas las ejecutivas activas al cierre de región o e campaña
        */
        OPEN C_EJE_SECCI( lnOidPeriodo, lsCodigoPrograma, lsCodigoFase, lnDiasRango );
        
        LOOP
            FETCH C_EJE_SECCI BULK COLLECT INTO EjeSecciRecord LIMIT lnFilas;
            
            IF EjeSecciRecord.COUNT > 0 THEN
               FOR a IN EjeSecciRecord.FIRST .. EjeSecciRecord.LAST LOOP

                   /*
                     Inicializar array del registro
                   */
                   
                   SELECT psCodigoPais,
                          lsCodigoPrograma,
                          EjeSecciRecord(a).cod_zona || EjeSecciRecord(a).cod_secc,
                          EjeSecciRecord(a).gere,
                          psCodigoPeriodo,
                          --
                          NVL(EjeSecciRecord(a).val_nume_pedi_real,0),
                          NVL(EjeSecciRecord(a).val_nume_ingr_real,0),
                          0,0,
                          NVL(EjeSecciRecord(a).val_mont_vent_cata,0),
                          0,0,0,0,0,0,
                          NVL(EjeSecciRecord(a).ind_paso_pedi,0),
                          0,0,0,
                          EjeSecciRecord(a).cod_nive,
                          NULL,0,
                          'COCMORI',
                          1,
                          NULL,
                          SYSDATE,
                          NULL
                     INTO aRegEva.pais_cod_pais,
                          aRegEva.cod_prog_ejec,
                          aRegEva.cod_secc,
                          aRegEva.clie_cod_clie,
                          aRegEva.cam_proc,
                          --
                          aRegEva.val_real_pedi,
                          aRegEva.val_real_ingr,
                          aRegEva.val_mont_reca,
                          aRegEva.val_porc_reca,
                          aRegEva.val_mont_vent_cata,
                          aRegEva.val_indi_pedi,
                          aRegEva.val_indi_ingr,
                          aRegEva.val_indi_reca,
                          aRegEva.ind_cump_vari_pedi,
                          aRegEva.ind_cump_vari_ingr,
                          aRegEva.ind_cump_vari_reca,
                          aRegEva.ind_fact_ejec,
                          aRegEva.val_mont_comi,
                          aRegEva.val_mont_comi_neto,
                          aRegEva.num_docu_equi,
                          aRegEva.cod_nive,
                          aRegEva.cod_rang,
                          aRegEva.val_porc_comi,
                          aRegEva.usu_crea,
                          aRegEva.ind_proc,
                          aRegEva.usu_modi,
                          aRegEva.fec_crea,
                          aRegEva.fec_modi
                     FROM DUAL;
                   
                   /*
                     Calcular porcentajes e indicadores de cumplimiento
                   */
                   
                   -- Porcentaje e indicador de cumplimiento de pedidos
                   IF NVL( EjeSecciRecord(a).val_nume_pedi_esti,0 ) > 0 THEN
                      aRegEva.val_indi_pedi := ROUND(( aRegEva.val_real_pedi / NVL( EjeSecciRecord(a).val_nume_pedi_esti,0 ) )* 100 ,2);
                      -- Formatear el indice de pedidos calculado
                      SELECT CASE
                                WHEN NVL( aRegEva.val_indi_pedi,0 ) - TRUNC( NVL( aRegEva.val_indi_pedi,0 ),0 ) = 0    THEN NVL( aRegEva.val_indi_pedi,0 )
                                WHEN NVL( aRegEva.val_indi_pedi,0 ) - TRUNC( NVL( aRegEva.val_indi_pedi,0 ),0 ) > 0.5  THEN TRUNC( NVL( aRegEva.val_indi_pedi,0 ),0 ) + 1
                                WHEN NVL( aRegEva.val_indi_pedi,0 ) - TRUNC( NVL( aRegEva.val_indi_pedi,0 ),0 ) <= 0.5 THEN TRUNC( NVL( aRegEva.val_indi_pedi,0 ),0 ) + 0.5
                             END
                        INTO aRegEva.val_indi_pedi
                        FROM DUAL;
                   END IF;
                   -- Indicador de cumplimiento de pedido
                   SELECT CASE 
                             WHEN aRegEva.val_real_pedi >= NVL(EjeSecciRecord(a).val_nume_pedi_esti,0) THEN 1
                             ELSE 0
                          END
                     INTO aRegEva.ind_cump_vari_pedi
                     FROM DUAL;

                   -- Porcentaje e indicador de cumplimiento de ingresos
                   IF NVL( EjeSecciRecord(a).val_nume_ingr_esti,0 ) > 0 THEN
                      aRegEva.val_indi_ingr := ROUND(( aRegEva.val_real_ingr / NVL(EjeSecciRecord(a).val_nume_ingr_esti,0) )* 100 ,2);
                   END IF;
                   -- Indicador de cumplimiento de ingresos
                   SELECT CASE 
                             WHEN aRegEva.val_real_ingr >= NVL(EjeSecciRecord(a).val_nume_ingr_esti,0) THEN 1
                             ELSE 0
                          END
                     INTO aRegEva.ind_cump_vari_ingr
                     FROM DUAL;
                   
                   /*
                     CALCULAR RECAUDO
                   */
                   
                   OPEN C_EJE_RECAUDO( 
                                      lnOidPeriodoAnterior,
                                      lsCodigoPrograma,
                                      EjeSecciRecord(a).cod_regi,
                                      EjeSecciRecord(a).cod_zona,
                                      EjeSecciRecord(a).cod_secc,
                                      TO_CHAR(EjeSecciRecord(a).fec_limi_reca,'DD/MM/YYYY')
                                     );
                   LOOP
                      FETCH C_EJE_RECAUDO BULK COLLECT INTO RecSecciRecord LIMIT lnFilas;
                      
                      IF RecSecciRecord.COUNT > 0 THEN
                         FOR b IN RecSecciRecord.FIRST .. RecSecciRecord.LAST LOOP
                             aRegEva.val_mont_reca := NVL(RecSecciRecord(b).val_tota_cobr,0) +
                                                      NVL(RecSecciRecord(b).val_tota_cobr_hist,0)
                                                      ;
                             
                             -- Porcentaje e indicador de cumplimiento de recaudo
                             IF NVL(RecSecciRecord(b).val_tota_carg,0) > 0 THEN
                                aRegEva.val_indi_reca := ROUND(( aRegEva.val_mont_reca / ( NVL(RecSecciRecord(b).val_tota_carg,0) -
                                                                                           NVL(RecSecciRecord(b).val_tota_aten,0) -
                                                                                           NVL(RecSecciRecord(b).val_tota_aten_hist,0)
                                                                                           ))* 100 ,2);
                                -- Formatear el indice de recaudos calculado
                                SELECT CASE
                                          WHEN NVL( aRegEva.val_indi_reca,0 ) - TRUNC( NVL( aRegEva.val_indi_reca,0 ),0 ) = 0    THEN NVL( aRegEva.val_indi_reca,0 )
                                          WHEN NVL( aRegEva.val_indi_reca,0 ) - TRUNC( NVL( aRegEva.val_indi_reca,0 ),0 ) >= 0.5 THEN TRUNC( NVL( aRegEva.val_indi_reca,0 ),0 ) + 1
                                          WHEN NVL( aRegEva.val_indi_reca,0 ) - TRUNC( NVL( aRegEva.val_indi_reca,0 ),0 ) < 0.5  THEN TRUNC( NVL( aRegEva.val_indi_reca,0 ),0 )
                                       END
                                  INTO aRegEva.val_indi_reca
                                  FROM DUAL;
                             END IF;
                             -- Indicador de cumplimiento de recaudo
                             SELECT CASE 
                                       WHEN aRegEva.val_indi_reca >= EjeSecciRecord(a).val_porc_reca_esti THEN 1
                                       ELSE 0
                                    END
                               INTO aRegEva.ind_cump_vari_reca
                               FROM DUAL;
                             aRegEva.val_porc_reca := aRegEva.val_indi_reca;
                         END LOOP;
                      END IF;
                      EXIT WHEN C_EJE_RECAUDO%NOTFOUND;
                   END LOOP;
                   CLOSE C_EJE_RECAUDO;
                   
                   /*
                     Calcular rango de comisión que le corresponde a la ejecutiva
                   */
                   
                   BEGIN
                       SELECT rapr.cod_rang
                         INTO aRegEva.cod_rang
                         FROM eje_rango_progr rapr
                        WHERE rapr.ind_acti = 1
                          AND aRegEva.val_indi_pedi BETWEEN rapr.val_inic_rang AND rapr.val_fina_rang
                          AND rapr.pais_cod_pais = psCodigoPais
                          AND rapr.cod_prog_ejec = lsCodigoPrograma
                            ;
                   EXCEPTION WHEN OTHERS THEN
                       aRegEva.cod_rang := NULL;
                   END;
                   
                   /*
                     Guardar datos de la evaluación de la ejecutiva
                   */
                   BEGIN
                       INSERT INTO eje_evalu_secci_campa evse
                       VALUES (
                               aRegEva.pais_cod_pais,
                               aRegEva.cod_prog_ejec,
                               aRegEva.cod_secc,
                               aRegEva.clie_cod_clie,
                               aRegEva.cam_proc,
                               aRegEva.val_real_pedi,
                               aRegEva.val_real_ingr,
                               aRegEva.val_mont_reca,
                               aRegEva.val_porc_reca,
                               aRegEva.val_mont_vent_cata,
                               aRegEva.val_indi_pedi,
                               aRegEva.val_indi_ingr,
                               aRegEva.val_indi_reca,
                               aRegEva.ind_cump_vari_pedi,
                               aRegEva.ind_cump_vari_ingr,
                               aRegEva.ind_cump_vari_reca,
                               aRegEva.ind_fact_ejec,
                               aRegEva.val_mont_comi,
                               aRegEva.val_mont_comi_neto,
                               aRegEva.num_docu_equi,
                               aRegEva.cod_nive,
                               aRegEva.cod_rang,
                               aRegEva.val_porc_comi,
                               aRegEva.usu_crea,
                               aRegEva.ind_proc,
                               aRegEva.usu_modi,
                               aRegEva.fec_crea,
                               aRegEva.fec_modi
                              )
                       ;
                   EXCEPTION WHEN dup_val_on_index THEN
                       UPDATE eje_evalu_secci_campa evse
                          SET val_real_pedi = aRegEva.val_real_pedi,
                              val_real_ingr = aRegEva.val_real_ingr,
                              val_mont_reca = aRegEva.val_mont_reca,
                              val_porc_reca = aRegEva.val_porc_reca,
                              val_mont_vent_cata = aRegEva.val_mont_vent_cata,
                              val_indi_pedi = aRegEva.val_indi_pedi,
                              val_indi_ingr = aRegEva.val_indi_ingr,
                              val_indi_reca = aRegEva.val_indi_reca,
                              ind_cump_vari_pedi = aRegEva.ind_cump_vari_pedi,
                              ind_cump_vari_ingr = aRegEva.ind_cump_vari_ingr,
                              ind_cump_vari_reca = aRegEva.ind_cump_vari_reca,
                              ind_fact_ejec = aRegEva.ind_fact_ejec,
                              val_mont_comi = aRegEva.val_mont_comi,
                              val_mont_comi_neto = aRegEva.val_mont_comi_neto,
                              num_docu_equi = aRegEva.num_docu_equi,
                              cod_nive = aRegEva.cod_nive,
                              cod_rang = aRegEva.cod_rang,
                              val_porc_comi = aRegEva.val_porc_comi,
                              ind_proc = aRegEva.ind_proc,
                              usu_modi = 'COCMORI',
                              fec_modi = SYSDATE
                        WHERE pais_cod_pais = aRegEva.pais_cod_pais
                          AND cod_prog_ejec = aRegEva.cod_prog_ejec
                          AND cod_secc = aRegEva.cod_secc
                          AND clie_cod_clie = aRegEva.clie_cod_clie
                          AND cam_proc = aRegEva.cam_proc;
                    END;
               END LOOP;
            END IF;
            EXIT WHEN C_EJE_SECCI%NOTFOUND;
        END LOOP;
        CLOSE C_EJE_SECCI;

        PEJ_PKG_PROCE.PEJ_PR_CALCU_COMIS_EJECU( pscodigopais,
                                                pscodigomarca,
                                                pscodigocanal,
                                                pscodigoperiodo,
                                                psfechafacturacion );
    END IF;
EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
     ls_sqlerrm := substr(sqlerrm,1,250);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PEJ_PR_CALCU_AVANC_GESTI: '||ls_sqlerrm);
END PEJ_PR_CALCU_AVANC_GESTI;

/***************************************************************************
 Descripcion       : Calcula comisión por ejecutiva
 Fecha Creacion    : 13/11/2013
 Autor             : Carlos Mori
 Fecha Modificación: -
 Autor             : -
***************************************************************************/
PROCEDURE PEJ_PR_CALCU_COMIS_EJECU(psCodigoPais        VARCHAR2,
                                   psCodigoMarca       VARCHAR2,
                                   psCodigoCanal       VARCHAR2,
                                   psCodigoPeriodo     VARCHAR2,
                                   psFechaFacturacion  VARCHAR2)
IS
CURSOR C_EJE_SECCI ( vsCodigoPrograma VARCHAR2,
                     vsCodigoFase     VARCHAR2,
                     vnOidPeriodo     NUMBER
                   )
IS
SELECT evse.*,
       nira.val_porc_comi val_porc_comi_real,
       (
         SELECT TO_NUMBER(epap.val_para) / 10
            FROM eje_param_progr epap,
                 eje_param parp
           WHERE epap.cod_para_prog = parp.cod_para_prog
             AND parp.cod_para_prog = '06'
       ) val_porc_comi_mini
  FROM (
         SELECT gere.gere, gere.cod_regi, gere.cod_zona, gere.cod_secc, clie.oid_clie
           FROM zon_histo_geren gere,
                mae_clien clie
          WHERE gere.gere = clie.cod_clie
            AND gere.cod_regi IS NOT NULL
            AND gere.cod_zona IS NOT NULL
            AND gere.cod_secc IS NOT NULL
            AND vnOidPeriodo BETWEEN gere.perd_oid_peri_desd AND NVL( gere.perd_oid_peri_hast, vnOidPeriodo )
       ) ejec,
       (
        SELECT zorg.cod_regi, TRUNC( coci.fec_cier ) fec_cier
          FROM fac_contr_cierr coci,
               own_comun.fac_tipos_cierr tcie,
               zon_regio zorg
         WHERE coci.tcie_oid_tipo_cier = tcie.oid_tipo_cier
           AND coci.zorg_oid_regi = zorg.oid_regi
           AND coci.perd_oid_peri = vnOidPeriodo
           AND tcie.oid_tipo_cier =1
         GROUP BY zorg.cod_regi, TRUNC( coci.fec_cier )
       ) cier,
       eje_evalu_secci_campa evse,
       eje_nivel_rango_progr nira
 WHERE ejec.gere = evse.clie_cod_clie
   AND ejec.cod_zona || ejec.cod_secc = evse.cod_secc
   AND ejec.cod_regi = cier.cod_regi
   AND evse.pais_cod_pais = nira.pais_cod_pais(+)
   AND evse.cod_prog_ejec = nira.cod_prog_ejec(+)
   AND evse.cod_nive = nira.cod_nive(+)
   AND evse.cod_rang = nira.cod_rang(+)
   --
   AND evse.pais_cod_pais = psCodigoPais
   AND evse.cod_prog_ejec = vsCodigoPrograma
   AND evse.cam_proc = psCodigoPeriodo
   AND evse.ind_proc IN (0,1)
   AND nira.cod_fase = vsCodigoFase
   AND nira.cod_grup_comi = '01'
   AND TO_DATE(psFechaFacturacion,'DD/MM/YYYY') >= (to_date(cier.fec_cier,'DD/MM/YYYY') + 1) -- poner el valor de dias de gracia
         ;

TYPE EjeSecciRecTab IS TABLE OF c_eje_secci%ROWTYPE INDEX BY BINARY_INTEGER;
EjeSecciRecord EjeSecciRecTab;

CURSOR curDataCanal (vnPeriodo NUMBER, vnPeriodoSig NUMBER, vnIndCruce NUMBER, vnIndCier NUMBER) IS
    SELECT geca.clave,
           CASE
              WHEN vnIndCier = 0 THEN
                   CASE WHEN NVL(cocc.ind_rece_cc,0)   +
                             NVL(cocc.ind_rece_dd,0)   +
                             NVL(cocc.ind_rece_digi,0) +
                             NVL(cocc.ind_rece_ivr,0)  +
                             NVL(cocc.ind_rece_mens,0) +
                             NVL(cocc.ind_rece_ocr,0)  +
                             NVL(cocc.ind_rece_onli,0) +
                             NVL(cocc.ind_rece_web,0) > 1 THEN 'MIX'
             ELSE
               CASE
                               WHEN NVL(cocc.ind_rece_cc,0)   = 1 THEN 'CC'
                               WHEN NVL(cocc.ind_rece_dd,0)   = 1 THEN 'DD'
                               WHEN NVL(cocc.ind_rece_digi,0) = 1 THEN 'SC'
                               WHEN NVL(cocc.ind_rece_ivr,0)  = 1 THEN 'IVR'
                               WHEN NVL(cocc.ind_rece_mens,0) = 1 THEN 'SMS'
                               WHEN NVL(cocc.ind_rece_ocr,0)  = 1 THEN 'OCR'
                               WHEN NVL(cocc.ind_rece_onli,0) = 1 THEN 'OL'
                               WHEN NVL(cocc.ind_rece_web,0)  = 1 THEN 'WEB'
                 ELSE 'ND'
               END
                   END
              ELSE
                   CASE WHEN NVL(hicc.ind_rece_cc,0)   +
                             NVL(hicc.ind_rece_dd,0)   +
                             NVL(hicc.ind_rece_digi,0) +
                             NVL(hicc.ind_rece_ivr,0)  +
                             NVL(hicc.ind_rece_mens,0) +
                             NVL(hicc.ind_rece_ocr,0)  +
                             NVL(hicc.ind_rece_onli,0) +
                             NVL(hicc.ind_rece_web,0) > 1 THEN 'MIX'
                    ELSE
                             CASE
                               WHEN NVL(hicc.ind_rece_cc,0)   = 1 THEN 'CC'
                               WHEN NVL(hicc.ind_rece_dd,0)   = 1 THEN 'DD'
                               WHEN NVL(hicc.ind_rece_digi,0) = 1 THEN 'SC'
                               WHEN NVL(hicc.ind_rece_ivr,0)  = 1 THEN 'IVR'
                               WHEN NVL(hicc.ind_rece_mens,0) = 1 THEN 'SMS'
                               WHEN NVL(hicc.ind_rece_ocr,0)  = 1 THEN 'OCR'
                               WHEN NVL(hicc.ind_rece_onli,0) = 1 THEN 'OL'
                               WHEN NVL(hicc.ind_rece_web,0)  = 1 THEN 'WEB'
                               ELSE 'ND'
                             END
                   END
           END AS CanalIngreso
      FROM (SELECT CASE
                      WHEN sgeca.oid_soli_cabe_refe IS NOT NULL THEN
                           sgeca.oid_soli_cabe_refe
                      ELSE sgeca.oid_soli_cabe
                   END oid_soli_cabe,
                   ROWID Clave
              FROM int_tmp_gener_solic_cabec sgeca) geca,
           int_solic_conso_cabec cocc,
           (SELECT a.soca_oid_soli_cabe_refe,
                   a.perd_oid_peri,
                   a.ind_rece_ocr,
                   a.ind_rece_web,
                   a.ind_rece_dd,
                   a.ind_rece_digi,
                   a.ind_rece_cc,
                   a.ind_rece_mens,
                   a.ind_rece_onli,
                   a.ind_rece_ivr
              FROM ped_histo_solic_conso_cabec a
             WHERE a.cod_pais = psCodigoPais
               AND (a.perd_oid_peri = vnperiodo OR
                   (vnindcruce = 0 AND a.perd_oid_peri = vnPeriodoSig))
               AND a.soca_oid_soli_cabe_refe IS NOT NULL
               AND a.ind_erro_rech = 0) hicc
     WHERE geca.oid_soli_cabe = cocc.soca_oid_soli_cabe_refe(+)
       AND geca.oid_soli_cabe = hicc.soca_oid_soli_cabe_refe(+)
         ;

TYPE cTabDataCanal IS TABLE OF curDataCanal%ROWTYPE INDEX BY BINARY_INTEGER;
cCanal cTabDataCanal;

-- Variables de control
lnFilas      NUMBER(12):= 5000;
ln_sqlcode   NUMBER(10);
ls_sqlerrm   VARCHAR2(1500);

-- Variables de trabajo
lnOidPeriodo CRA_PERIO.OID_PERI%TYPE;

lsCodigoPrograma EJE_PROGR.COD_PROG_EJEC%TYPE;
lsCodigoFase EJE_FASES_PROGR.COD_FASE%TYPE;
lnOk NUMBER(1):=1;

BEGIN

-- Obtener Oid de la campaña actual y campaña anterior

    lnOidPeriodo := FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO( psCodigoPeriodo );

-- Obtener el código del programa y fase activas en la campaña
    
    BEGIN
       SELECT prog.cod_prog_ejec,
              (
                SELECT fapr.cod_fase
                  FROM eje_fases_progr fapr
                 WHERE fapr.pais_cod_pais = psCodigoPais
                   AND fapr.cod_prog_ejec = prog.cod_prog_ejec
                   AND psCodigoPeriodo BETWEEN fapr.cod_camp_inic AND fapr.cod_camp_fina
              ) cod_fase
         INTO lsCodigoPrograma,
              lsCodigoFase
          FROM eje_progr prog
         WHERE prog.ind_acti = 1
             ;
    EXCEPTION WHEN OTHERS THEN
       lnOk := 0;
END;

-- Se calcula el porcentaje de comisión y el monto de la comisión que le corresponde a la ejecutiva
    
    IF lnOk = 1 THEN
        /*
          Se leen todas las ejecutivas activas al cierre de región o e campaña
        */
        OPEN C_EJE_SECCI( lsCodigoPrograma, lsCodigoFase, lnOidPeriodo );
        
        LOOP
            FETCH C_EJE_SECCI BULK COLLECT INTO EjeSecciRecord LIMIT lnFilas;
            
            IF EjeSecciRecord.COUNT > 0 THEN
               FOR a IN EjeSecciRecord.FIRST .. EjeSecciRecord.LAST LOOP
                   /* Calcula monto comisión que le corresponde a la ejecutiva */
                   IF EjeSecciRecord(a).ind_fact_ejec = 1 THEN
                      IF EjeSecciRecord(a).ind_cump_vari_reca = 1 THEN
                         EjeSecciRecord(a).val_porc_comi := EjeSecciRecord(a).val_porc_comi_real;
                         EjeSecciRecord(a).val_mont_comi := ROUND( EjeSecciRecord(a).val_mont_reca * (EjeSecciRecord(a).val_porc_comi_real/100),0);
                      ELSE
                         EjeSecciRecord(a).val_porc_comi := EjeSecciRecord(a).val_porc_comi_mini;
                         EjeSecciRecord(a).val_mont_comi := ROUND(EjeSecciRecord(a).val_mont_reca * (EjeSecciRecord(a).val_porc_comi_mini/100),0);
                      END IF;
                   ELSE
                         EjeSecciRecord(a).val_porc_comi := 0;
                         EjeSecciRecord(a).val_mont_comi := 0;
                   END IF;
                   /* Guarda valor del porcentaje y monto de comisión calculado para la ejecutiva */
                   UPDATE eje_evalu_secci_campa evse
                      SET evse.val_porc_comi = EjeSecciRecord(a).val_porc_comi,
                          evse.val_mont_comi = EjeSecciRecord(a).val_mont_comi,
                          evse.ind_proc = 2
                    WHERE evse.pais_cod_pais = EjeSecciRecord(a).pais_cod_pais
                      AND evse.cod_prog_ejec = EjeSecciRecord(a).cod_prog_ejec
                      AND evse.cod_secc = EjeSecciRecord(a).cod_secc
                      AND evse.clie_cod_clie = EjeSecciRecord(a).clie_cod_clie
                      AND evse.cam_proc = EjeSecciRecord(a).cam_proc
                        ;
               END LOOP;
            END IF;
            EXIT WHEN C_EJE_SECCI%NOTFOUND;
        END LOOP;
        CLOSE C_EJE_SECCI;
    END IF;
EXCEPTION WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(sqlerrm,1,250);
    RAISE_APPLICATION_ERROR( -20123, 'ERROR PEJ_PR_CALCU_COMIS_EJECU: ' || ls_sqlerrm );
END PEJ_PR_CALCU_COMIS_EJECU;

END PEJ_PKG_PROCE;
/
