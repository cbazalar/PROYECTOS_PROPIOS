CREATE OR REPLACE PROCEDURE "PER_PR_CARGA_PERCE_VTADI"
 (
  p_codigoPais        IN VARCHAR2,
  p_fechaInicial      IN VARCHAR2,
  p_fechaFinal        IN VARCHAR2,
  p_fechaCorte        IN VARCHAR2,
  p_usuDigi           IN VARCHAR2,
  p_cantidadRegistros OUT VARCHAR2
 ) IS

 -- Variables usadas para la ejecucion del PL/SQL Dinamico.
 ln_sqlcode   NUMBER(10);
 ls_sqlerrm   VARCHAR2(150);
 W_FILAS      NUMBER:=1000;
 
 -- Cursor son la informacion de Registro de Pagos a procesar --
 CURSOR curRegPagos IS
   SELECT A.*
     FROM PER_REGIS_PAGOS A
    WHERE A.PAIS_COD_PAIS = p_codigoPais
      AND A.STA_REPE = '2'
   	  AND A.FEC_PAGO <= TO_DATE(p_fechaFinal, 'DD/MM/YYYY')
      AND A.FEC_PROC < TO_DATE(p_fechaCorte, 'DD/MM/YYYY') + 1
   	  AND A.IND_REPE = 'S'
  	  AND A.FEC_DOLE <= TO_DATE(p_fechaFinal, 'DD/MM/YYYY')
      AND A.MON_TODL > 0.0
    ORDER BY A.COD_CONS, 
             A.TIP_DOLE, 
             A.NUM_SOCO, 
             A.EJE_DINT,
             A.NUM_DINT, 
             A.NUM_CUOT, 
             A.SEC_ABON ;
 
 TYPE typeTableRegisPagos IS TABLE OF PER_REGIS_PAGOS%ROWTYPE;
 regRegistroPago PER_REGIS_PAGOS%ROWTYPE;
 tablaRegistroPago typeTableRegisPagos;
 
 lsValSeriDocu               FAC_DOCUM_CONTA_CABEC.VAL_SERI_DOCU_LEGA%TYPE;
 lnNumDocuLega               FAC_DOCUM_CONTA_CABEC.NUM_DOCU_LEGA%TYPE;
 
 correlDia                   NUMBER;
 cantAnual                   NUMBER;
 correlativo                 NUMBER;
 numeroComprobante           NUMBER;
 flagSunat                   NUMBER;
 lnIdSoci                    NUMBER;
 lnIdSbac                    NUMBER;
 lb_procesar                 BOOLEAN;
 lnCorreDetalle              NUMBER;
 lnNumeCompro                NUMBER;
 lb_inserto_venta_directa    BOOLEAN;
 
 regAutorSunatCoper          PER_AUTOR_SUNAT_COPER%ROWTYPE;
 lsCodClien                  PER_REGIS_PAGOS.COD_CONS%TYPE;
 lsTipoDoc                   PER_REGIS_PAGOS.TIP_DOLE%TYPE;
 lsTipoDocId                 PER_REGIS_PAGOS.TIP_DOID%TYPE;
 lsNumDocId                  PER_REGIS_PAGOS.NUM_DOID%TYPE;
 lsNumDocIdTemp              PER_REGIS_PAGOS.NUM_DOID%TYPE;
 lnNumeComproIni             PER_CONTR_DIARI_COPER.NUM_COIN%TYPE;
 lnNumeComproFin             PER_CONTR_DIARI_COPER.NUM_COFI%TYPE;
 errorAnno                   EXCEPTION;
 lnpaginaError               NUMBER;
 lnRegistroError             NUMBER;
 lsFallo                     VARCHAR2(10);
 lnTam                       NUMBER;

BEGIN

  DBMS_OUTPUT.put_line ('Al Inicio del SP'||p_codigoPais||','||p_fechaInicial||','||p_fechaFinal||','||p_fechaCorte||','||p_usuDigi);
  p_cantidadRegistros := 0;

  /* Obtenemos el siguiente correlativo por dia segun el Pais.*/
  correlDia := 1;
  
  BEGIN
   SELECT NVL(MAX(COR_CODI+1),1) 
     INTO correlDia 
     FROM PER_CONTR_DIARI_COPER
    WHERE PAIS_COD_PAIS = p_codigoPais;
  EXCEPTION
  WHEN no_data_found THEN
      correlDia := 1;
  END ;
 
  /* Insertamos en el Control Diario el registro correspondiente al siguiente correlativo. */
  INSERT INTO PER_CONTR_DIARI_COPER
    (PAIS_COD_PAIS, COR_CODI, FEC_PROC, NUM_COIN, NUM_COFI, USU_DIGI, FEC_DIGI, EST_CODI)
  VALUES 
    (p_codigoPais, correlDia, TO_DATE(p_fechaFinal, 'DD/MM/YYYY'), 0, 0, p_usuDigi, SYSDATE, '1');
  
  /*Obtenemos la cantidad de registros referentes al presente año,
  que nos servira para obtener el numero de comprobante*/
  BEGIN
   SELECT COUNT(1) 
     INTO cantAnual 
     FROM PER_CONTR_ANUAL_COPER
    WHERE ANO_CONT = SUBSTR(p_fechaFinal,7,11);
  EXCEPTION
  WHEN no_data_found THEN
      cantAnual := 0;
  END;
 
 
  IF cantAnual > 0 THEN
    /*	Obtenemos el numero de comprobante del año en curso.*/
    SELECT NUM_COPE 
      INTO numeroComprobante 
      FROM PER_CONTR_ANUAL_COPER
     WHERE ANO_CONT = SUBSTR(p_fechaFinal,7,11);
  ELSE
    /* Obtenemos el numero de comprobante del año anterior */
    BEGIN
    	SELECT NUM_COPE 
        INTO numeroComprobante 
        FROM PER_CONTR_ANUAL_COPER
     WHERE ANO_CONT = TO_NUMBER(SUBSTR(p_fechaFinal,7,11))-1;
    EXCEPTION
    WHEN no_data_found THEN
         RAISE_APPLICATION_ERROR(-20123, 'No existe registro correspondiente al A?o '|| to_char(TO_NUMBER(SUBSTR(p_fechaFinal,7,11)) - 1));
    END ;
  
    BEGIN
    	SELECT NVL(MAX(COR_COAN+1),1) 
        INTO correlativo 
        FROM PER_CONTR_ANUAL_COPER;
    EXCEPTION
    WHEN no_data_found THEN
        correlativo := 1;
    END;
 
    INSERT INTO PER_CONTR_ANUAL_COPER
      (COR_COAN, ANO_CONT, NUM_COPE, USU_DIGI, FEC_DIGI, EST_COAN)
    VALUES 
      (correlativo, TO_CHAR(TO_NUMBER(SUBSTR(p_fechaFinal,7,11)+1)), numeroComprobante, p_usuDigi, SYSDATE, '1');
	
  END IF;
 
  /* Revisamos que el numero de comprobante se encuentre dentro del rango dado por SUNAT
  Si es 0 no se encuentra dentro del rango, caso contrario si.*/
  BEGIN
    SELECT COUNT(1)
      INTO flagSunat
      FROM PER_AUTOR_SUNAT_COPER
     WHERE --COD_AUTO = 'A02' AND
           COD_VIGE = '4'
       AND NUM_INAU < numeroComprobante
       AND NUM_FIAU > numeroComprobante ;
  EXCEPTION
  WHEN no_data_found THEN
       flagSunat := 0;
  END;
 
  IF flagSunat > 0 THEN
    /* Realizamos un volcado de la informacion encontrada en la tabla de Autorizacion de SUNAT a un BULK COLLECT */
    SELECT *
      INTO regAutorSunatCoper
      FROM PER_AUTOR_SUNAT_COPER
     WHERE --COD_AUTO = 'A02' AND
           COD_VIGE = '4'
           AND NUM_INAU < numeroComprobante
           AND NUM_FIAU > numeroComprobante ;
 
    /* Obteniendo informacion */
    lb_inserto_venta_directa := FALSE;
    lnpaginaError := 0;
    lsCodClien := 'xx';
    lsTipoDoc := 'yy';
    lnCorreDetalle := 0;
    lnNumeCompro := numeroComprobante + 1;
    lnNumeComproIni:= lnNumeCompro;
    lsFallo := 'NO';
     
    -- Abrimos cursor con informacion de Registro de Pagos --
    OPEN curRegPagos;
      LOOP
        FETCH curRegPagos BULK COLLECT INTO tablaRegistroPago LIMIT W_FILAS;
      IF tablaRegistroPago.COUNT > 0 THEN
          lnpaginaError := lnpaginaError + 1;
        
        FOR x IN tablaRegistroPago.FIRST .. tablaRegistroPago.LAST LOOP
        
          regRegistroPago := tablaRegistroPago(x);
          lb_procesar := TRUE;
          lnRegistroError := x;

          SELECT A.OID_SOCI
            INTO lnIdSoci
            FROM SEG_SOCIE A
           WHERE A.COD_SOCI = regRegistroPago.Cod_Soci;

          SELECT A.OID_SBAC
            INTO lnIdSbac
            FROM SEG_SUBAC A
           WHERE A.COD_SBAC = regRegistroPago.Cod_Sbac;

          BEGIN
            SELECT D.VAL_SERI_DOCU_LEGA,
                   D.NUM_DOCU_LEGA
              INTO lsValSeriDocu,
                   lnNumDocuLega
              FROM SEG_PAIS A,
                   SEG_SUBAC B,
                   SEG_SOCIE C,
                   FAC_DOCUM_CONTA_CABEC D,
                   FAC_TIPO_DOCUM E
             WHERE A.COD_PAIS = p_codigoPais
               AND (A.OID_PAIS = C.PAIS_OID_PAIS)
               AND (A.OID_PAIS = D.PAIS_OID_PAIS)
               AND (B.OID_SBAC = D.SBAC_OID_SBAC)
               AND (C.OID_SOCI = D.SOCI_OID_SOCI)
               AND (E.OID_TIPO_DOCU = D.TIDO_OID_TIPO_DOCU )
               AND C.COD_SOCI = regRegistroPago.Cod_Soci
               AND B.COD_SBAC = regRegistroPago.Cod_Sbac
               AND E.COD_TIPO_DOCU = regRegistroPago.Tip_Dole
               AND D.SBAC_OID_SBAC = lnIdSbac
               AND D.SOCI_OID_SOCI = lnIdSoci
               AND to_number(D.VAL_EJER_DOCU_INTE) = to_number(regRegistroPago.Eje_Dint)
               AND D.NUM_DOCU_CONT_INTE = regRegistroPago.Num_Dint ;
          EXCEPTION
            WHEN no_data_found THEN
              lb_procesar := FALSE;
          END;

          /* Verificando que Serie y Numero de Documento Legal no sean nulos */
          IF lb_procesar THEN
             IF lsValSeriDocu IS NULL OR lnNumDocuLega IS NULL THEN
                lb_procesar := FALSE;
             END IF;
          END IF;

          IF lb_procesar THEN

            /* verificando el nro de documento */
            IF regRegistroPago.Tip_Doid = '01' THEN
               lsNumDocIdTemp := regRegistroPago.Num_Doid;
               IF lsNumDocIdTemp IS NOT NULL THEN
                  lsNumDocIdTemp := TRIM(lsNumDocIdTemp);
                  lnTam := length(lsNumDocIdTemp);
                  IF lnTam > 8 THEN
                     lsNumDocIdTemp := substr(lsNumDocIdTemp, lnTam - 7);
                  ELSIF lnTam < 8 THEN
                     lsNumDocIdTemp := lpad(lsNumDocIdTemp,8,'0');
                  END IF;
               END IF;
               regRegistroPago.Num_Doid := lsNumDocIdTemp;
            END IF;

            /* Obteniendo Correlativo y Numero de comprobante */
            IF regRegistroPago.Cod_Cons = lsCodClien AND
               regRegistroPago.Tip_Dole = lsTipoDoc AND
               regRegistroPago.Tip_Doid = lsTipoDocId AND
               regRegistroPago.Num_Doid = lsNumDocId THEN
               lnCorreDetalle := lnCorreDetalle + 1;
               IF lnCorreDetalle = 21 THEN
                  lnCorreDetalle := 1;
                  lnNumeCompro := lnNumeCompro + 1;
               END IF;
            ELSE
              lnCorreDetalle := 1;
              lnNumeCompro := lnNumeCompro + 1;
              lsCodClien := regRegistroPago.Cod_Cons;
              lsTipoDoc := regRegistroPago.Tip_Dole;
              lsTipoDocId := regRegistroPago.Tip_Doid;
              lsNumDocId := regRegistroPago.Num_Doid;

              /* verificando el nro de documento */
              /* lsNumDocId := TRIM(lsNumDocId);
              IF lsTipoDocId = '01' THEN
              IF lsNumDocId IS NOT NULL THEN
              lnTam := length(lsNumDocId);
              IF lnTam > 8 THEN
              lsNumDocId := substr(lsNumDocId, lnTam - 7);
              ELSIF lnTam < 8 THEN
              lsNumDocId := lpad(lsNumDocId,8,'0');
              END IF;
              END IF;
              END IF;
              regRegistroPago.Num_Doid := lsNumDocId;*/
            END IF;
 
            lnNumeComproFin := lnNumeCompro;
            lb_inserto_venta_directa := TRUE;

            /* Insertando en Percepcion Venta Directa */
            BEGIN
              INSERT INTO PER_PERCE_VENTA_DIREC 
              (
                PAIS_COD_PAIS,
                COD_SOCI, COD_CANA,
                COD_ACCE, COD_SBAC,
                FEC_COPE,
                SER_COPE,
                NUM_COPE, SEC_COPE,
                COD_AUSU, TIP_DOLE,
                FEC_DOLE, SER_DOLE,
                NUM_DOLE, NUM_SOCO,
                TIP_ABON, NUM_ABON,
                COD_CONS, TIP_DOID,
                NUM_DOID, MON_PAGO,
                MON_PERC, POR_PERC,
                MON_TODL, USU_DIGI,
                FEC_DIGI, USU_MODI,
                FEC_MODI, EST_PEVD,
                REPA_COR_REPA
              )
              VALUES 
              (
                p_codigoPais,
                regRegistroPago.Cod_Soci, regRegistroPago.Cod_Cana,
                regRegistroPago.Cod_Acce, regRegistroPago.Cod_Sbac,
                TO_DATE(p_fechaFinal, 'DD/MM/YYYY'),
                regAutorSunatCoper.ser_docu,
                lnNumeCompro, lnCorreDetalle,
                regAutorSunatCoper.Cod_Auto, regRegistroPago.Tip_Dole,
                regRegistroPago.Fec_Dole, lsValSeriDocu,
                lnNumDocuLega, regRegistroPago.Num_Soco,
                regRegistroPago.Tip_Abon, regRegistroPago.Num_Abon,
                regRegistroPago.Cod_Cons, regRegistroPago.Tip_Doid,
                regRegistroPago.Num_Doid, regRegistroPago.Mon_Pago,
                regRegistroPago.Mon_Perc, regRegistroPago.Por_Perc,
                regRegistroPago.Mon_Todl, p_usuDigi,
                SYSDATE, p_usuDigi,
                SYSDATE, '2',
                regRegistroPago.Cor_Repa
              );

              UPDATE PER_REGIS_PAGOS A
                 SET A.STA_REPE = '4',
                     A.USU_MODI = p_usuDigi,
                     A.FEC_MODI = SYSDATE
               WHERE A.PAIS_COD_PAIS = regRegistroPago.Pais_Cod_Pais 
                 AND A.COR_REPA = regRegistroPago.Cor_Repa;

              p_cantidadRegistros := p_cantidadRegistros + 1;

            EXCEPTION
              WHEN OTHERS THEN
                   lsFallo := 'SI';
                   UPDATE PER_REGIS_PAGOS A
                      SET A.STA_REPE = 'X',
                          A.USU_MODI = p_usuDigi,
                          A.FEC_MODI = SYSDATE
                    WHERE A.PAIS_COD_PAIS = regRegistroPago.Pais_Cod_Pais 
                      AND A.COR_REPA = regRegistroPago.Cor_Repa;
            END ;
          END IF;
          -- COMMIT;
        END LOOP;
      END IF;
      EXIT WHEN curRegPagos%NOTFOUND;
      END LOOP;
    CLOSE curRegPagos;
    
    /* Actualizando Numero de Comprobante Inicial y Final en control Diario y
    Control Anual*/
    IF lb_inserto_venta_directa THEN
       UPDATE PER_CONTR_DIARI_COPER A
          SET A.NUM_COIN = lnNumeComproIni,
              A.NUM_COFI = lnNumeComproFin,
              A.USU_MODI = p_usuDigi,
              A.FEC_MODI = SYSDATE
        WHERE A.PAIS_COD_PAIS = p_codigoPais
          AND A.COR_CODI = correlDia;
          
       IF cantAnual > 0 THEN
          UPDATE PER_CONTR_ANUAL_COPER A
             SET A.NUM_COPE = lnNumeComproFin,
                 A.USU_MODI = p_usuDigi,
                 A.FEC_MODI = SYSDATE
           WHERE ANO_CONT = SUBSTR(p_fechaFinal,7,11);
       ELSE
          UPDATE PER_CONTR_ANUAL_COPER A
             SET A.NUM_COPE = lnNumeComproFin,
                 A.USU_MODI = p_usuDigi,
                 A.FEC_MODI = SYSDATE
           WHERE A.COR_COAN = correlativo;
       END IF;
    END IF;
   
    /* Insertamos en la tabla de Consolidado la informacion de la Venta Directa */
    INSERT INTO PER_PERCE_CONSO
    (
    PAIS_COD_PAIS,
    COD_SOCI, COD_CANA,
    COD_ACCE, COD_SBAC,
    TIP_CLIE, COD_CLIE,
    SER_COPE, NUM_COPE,
    SEC_COPE, TIP_DOID,
    NUM_DOID, TIP_DOLE,
    FEC_DOLE, SER_DOLE,
    NUM_DOLE, MON_PAGO, MON_PERC, POR_PERC,
    MON_TODL, FEC_COPE, FLA_PROC, FLA_CANC,
    USU_DIGI, FEC_DIGI, USU_MODI, FEC_MODI, EST_PECO,
    PEVD_COR_PEVD
    )
    SELECT A.PAIS_COD_PAIS,
           A.COD_SOCI, A.COD_CANA,
           A.COD_ACCE, A.COD_SBAC,
           (
            SELECT D.COD_TIPO_CLIE
              FROM MAE_CLIEN_TIPO_SUBTI C,
                   MAE_TIPO_CLIEN D
             WHERE (B.OID_CLIE = C.CLIE_OID_CLIE)
               AND (D.OID_TIPO_CLIE = C.TICL_OID_TIPO_CLIE)
               AND ROWNUM = 1 
           ) AS COD_TIPO_CLIEN,
           A.COD_CONS,
           A.SER_COPE, A.NUM_COPE,
           A.SEC_COPE, A.TIP_DOID,
           A.NUM_DOID, A.TIP_DOLE,
           A.FEC_DOLE, A.SER_DOLE,
           A.NUM_DOLE, A.MON_PAGO, A.MON_PERC, A.POR_PERC,
           A.MON_TODL, A.FEC_COPE, NULL, NULL,
           A.USU_DIGI, A.FEC_DIGI, A.USU_MODI, A.FEC_MODI, '1',
           A.COR_PEVD
      FROM PER_PERCE_VENTA_DIREC A,
           MAE_CLIEN B,
           SEG_PAIS E
     WHERE A.PAIS_COD_PAIS = p_codigoPais
       AND (A.PAIS_COD_PAIS = E.COD_PAIS)
       AND A.FEC_COPE >= TO_DATE(p_fechaInicial, 'DD/MM/YYYY')
       AND A.FEC_COPE < TO_DATE(p_fechaFinal, 'DD/MM/YYYY') + 1
       AND (A.COD_CONS = B.COD_CLIE)
       AND (E.OID_PAIS = B.PAIS_OID_PAIS)
       AND (A.EST_PEVD = '2')
     ORDER BY A.COD_CONS, A.SER_COPE, A.NUM_COPE, TO_NUMBER(A.SEC_COPE) ;
  END IF;
 
  dbms_output.put_line(lsFallo);

  --Actualizamos Estado de registros procesados
  UPDATE PER_PERCE_VENTA_DIREC A
     SET A.EST_PEVD = '1',
         A.USU_MODI = p_usuDigi,
         A.FEC_MODI = SYSDATE
   WHERE A.FEC_COPE >= TO_DATE(p_fechaInicial, 'DD/MM/YYYY')
     AND A.FEC_COPE < TO_DATE(p_fechaFinal, 'DD/MM/YYYY') + 1
     AND A.EST_PEVD = '2';
  --COMMIT;
  
EXCEPTION
WHEN OTHERS THEN
  ln_sqlcode := SQLCODE;
  ls_sqlerrm := substr(sqlerrm,1,250);
  IF ln_sqlcode < 0 THEN
     RAISE_APPLICATION_ERROR(-20123, 'ERROR PER_PR_CARGA_PERCE_VTADI: Pagina: '||lnpaginaError||' Registro: '||lnRegistroError||' '||ls_sqlerrm);
  END IF;
END ;
 
/
