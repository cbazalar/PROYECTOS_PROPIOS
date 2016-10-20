CREATE OR REPLACE PACKAGE INT_PKG_MAE is

   w_filas                      NUMBER := 5000;
   ln_sqlcode                 NUMBER(10);
   ls_sqlerrm                 VARCHAR2(1000);


/***************************************************************************
Descripcion       : Genera la interface de consultoras bloqueadas/desbloqueadas
Fecha Creacion    : 20/02/2014
Autor             : Juan Gutiérrez
***************************************************************************/
    PROCEDURE MAE_PR_ENVIO_CONSU_BLOQ_DESB(
        psCodigoPais                 VARCHAR2,
   psCodigoSistema          VARCHAR2,
   psCodigoInterfaz         VARCHAR2,
   psNombreArchivo          VARCHAR2,
   psFechaInicio            VARCHAR2,
        psFechaFin                    VARCHAR2,
        psNumeroLote               VARCHAR2);

/***************************************************************************
    Descripcion           : Inserta un registro en la tabla int_histo_lotes
    Fecha Creacion    : 17/03/2014
    Autor                   : Sebastian Guerra
***************************************************************************/
    PROCEDURE INT_PR_INSER_HISTO_LOTES(
        psOidPais                       NUMBER,
        psCodInterfaz               VARCHAR2,
        psNumeroLote               VARCHAR2,
        psContadorReg             NUMBER,
        pdFechaInicio                DATE
  );

END INT_PKG_MAE;
/
CREATE OR REPLACE PACKAGE BODY INT_PKG_MAE IS

/***************************************************************************
Descripcion       : Genera la interface de consultoras bloqueadas/desbloqueadas
Fecha Creacion    : 20/02/2014
Autor             : Juan Gutiérrez
***************************************************************************/

    PROCEDURE MAE_PR_ENVIO_CONSU_BLOQ_DESB(
        psCodigoPais                 VARCHAR2,
   psCodigoSistema          VARCHAR2,
   psCodigoInterfaz         VARCHAR2,
   psNombreArchivo          VARCHAR2,
   psFechaInicio            VARCHAR2,
        psFechaFin                    VARCHAR2,
        psNumeroLote               VARCHAR2)
 IS

        CURSOR c_interfaz (lsFecIni VARCHAR2 , lsFecFin VARCHAR2) IS
    SELECT 
           'I'                                                    ind_accion,
               RPAD(TRIM (mi.num_docu_iden), 10, ' ') num_iden,
               RPAD(TRIM (m.val_ape1)|| ' ' ||TRIM (m.val_ape2)|| ', ' ||TRIM (m.val_nom1)|| ' ' ||TRIM (m.val_nom2), 60, ' ') nombre,
               LPAD (m.cod_clie, 10, '0') cod_clie, 
               RPAD(TRIM (gen.val_i18n), 40, ' ') motivo
      FROM mae_clien                m,
           mae_clien_bloqu          b,
           mae_clien_unida_admin    ua,
           mae_clien_ident          mi,
           gen_i18n_sicc_comun      gen,
           mae_tipo_bloqu           mtb
     WHERE m.oid_clie            = b.clie_oid_clie
       AND ua.clie_oid_clie      = m.oid_clie
       AND ua.ind_acti           = 1
       AND m.oid_clie            = mi.clie_oid_clie
       AND mi.val_iden_docu_prin = 1
       AND gen.attr_enti LIKE 'MAE_TIPO_BLOQU'
       AND gen.idio_oid_idio = 1
       AND b.tibq_oid_tipo_bloq=mtb.oid_tipo_bloq
       AND mtb.oid_tipo_bloq=gen.val_oid
                AND b.fec_desb IS NULL
                AND TO_CHAR(b.fec_bloq, 'DD/MM/YYYY') BETWEEN lsFecIni AND lsFecFin
  UNION
     SELECT
           'E'                                                      ind_accion,
              RPAD(TRIM (mi1.num_docu_iden), 10, ' ') num_iden,
              RPAD(TRIM (m1.val_ape1)|| ' ' || TRIM (m1.val_ape2)|| ', ' || TRIM (m1.val_nom1)|| ' ' || TRIM (m1.val_nom2), 60, ' ') nombre,
              LPAD (m1.cod_clie, 10, '0') cod_clie,
              SUBSTR('                                        ', 1, 40) motivo
      FROM mae_clien                m1,
           mae_clien_bloqu          b1,
           mae_clien_unida_admin    ua1,
           mae_clien_ident          mi1,
           gen_i18n_sicc_comun      gen1,
           mae_tipo_bloqu           mtb1
     WHERE m1.oid_clie            = b1.clie_oid_clie
       AND ua1.clie_oid_clie      = m1.oid_clie
       AND ua1.ind_acti           = 1
       AND m1.oid_clie            = mi1.clie_oid_clie
       AND mi1.val_iden_docu_prin = 1
       AND gen1.attr_enti LIKE 'MAE_TIPO_BLOQU'
       AND gen1.idio_oid_idio = 1
       AND b1.tibq_oid_tipo_bloq=mtb1.oid_tipo_bloq
       AND mtb1.oid_tipo_bloq=gen1.val_oid
                 AND b1.fec_desb IS NOT NULL
                 AND TO_CHAR(b1.fec_desb, 'DD/MM/YYYY') BETWEEN lsFecIni AND lsFecFin
        ORDER BY 4, 1 ASC;

        TYPE interfazRec IS RECORD(
            ind_accion         CHAR,
            num_iden           VARCHAR2(10),
            nombre              VARCHAR2(100),
            cod_clie           VARCHAR2(15),
            motivo             VARCHAR2(40)
      );

   TYPE interfazRecTab  IS TABLE OF interfazRec;
   interfazRecord interfazRecTab;

   lsDirTempo          BAS_INTER.DIR_TEMP%TYPE;
   v_handle            UTL_FILE.FILE_TYPE;
       lsLinea                       VARCHAR2(1000);
   lsNombreArchivo     VARCHAR2(50);
   lbAbrirUtlFile      BOOLEAN;
   lsNumDocEmpresa     VARCHAR2(10);
       lnContadorReg          NUMBER;
       lnOidPais                    NUMBER;
       ldFecInicioEjec           DATE;

BEGIN

        lnOidPais := gen_pkg_gener.gen_fn_devuelve_id_pais(psCodigoPais); -- id del pais consultante
    lbAbrirUtlFile:= TRUE;
        lnContadorReg := 0;
        ldFecInicioEjec := SYSDATE;

        -- Si existe el registro para ese lote, se elimina
        DELETE FROM int_histo_lotes
              WHERE pais_oid_pais = lnOidPais
                AND cod_inte = psCodigoInterfaz
                AND num_lote = psNumeroLote;

        -- Obtenemos el numero de documento de la empresa
        BEGIN
          SELECT RPAD(val_pain, 10, ' ')
            INTO lsNumDocEmpresa
            FROM bas_param_inter
           WHERE pais_cod_pais = psCodigoPais
    AND sist_cod_sist =  psCodigoSistema  
             AND inte_cod_inte = psCodigoInterfaz
             AND nom_pain = 'numDocEmpresa';
        EXCEPTION
        WHEN NO_DATA_FOUND  THEN
             lsNumDocEmpresa := '          ';
        END;

        OPEN c_interfaz( psFechaInicio, psFechaFin );
        LOOP
                FETCH c_interfaz BULK COLLECT INTO interfazRecord LIMIT w_filas;

             IF lbAbrirUtlFile THEN
                    GEN_PKG_INTER_ARCHI.GEN_PR_INICI_INTER(psCodigoPais, psCodigoSistema, psCodigoInterfaz, psNombreArchivo, lsDirTempo, lsNombreArchivo, v_handle);
               lbAbrirUtlFile := FALSE;
             END IF;
 
               IF interfazRecord.COUNT > 0 THEN
                  FOR x IN interfazRecord.FIRST .. interfazRecord.LAST LOOP
                        lsLinea :=   SUBSTR(interfazRecord(x).ind_accion, 1, 1) 
                                     || SUBSTR(interfazRecord(x).num_iden, 1, 10) 
                                     || SUBSTR(lsNumDocEmpresa, 1, 10) 
                                     || SUBSTR(interfazRecord(x).nombre, 1, 60) 
                                     || SUBSTR(interfazRecord(x).cod_clie, 1, 10) 
                                     || '                    ' --20  POS
                                     || SUBSTR(interfazRecord(x).motivo, 1, 40);
                        UTL_FILE.PUT_LINE (v_handle, lslinea );
                        lnContadorReg := lnContadorReg + 1;
                  END LOOP;
               END IF;
                
           EXIT WHEN c_interfaz%NOTFOUND;
        END LOOP;
    CLOSE c_interfaz;

    IF NOT lbAbrirUtlFile THEN
           UTL_FILE.FCLOSE(v_handle);
           GEN_PKG_INTER_ARCHI.GEN_PR_FINAL_INTER('SICC_DIR', lsDirTempo, psNombreArchivo, lsNombreArchivo);
           INT_PR_INSER_HISTO_LOTES(lnOidPais, psCodigoInterfaz, psNumeroLote, lnContadorReg, ldFecInicioEjec);
    END IF;

EXCEPTION
WHEN OTHERS THEN
     ln_sqlcode := SQLCODE;
         ls_sqlerrm := SUBSTR(SQLERRM, 1, 1000);
     RAISE_APPLICATION_ERROR(-20123, 'ERROR MAE_PR_ENVIO_CONSU_BLOQ_DESB : '||ls_sqlerrm);
    END MAE_PR_ENVIO_CONSU_BLOQ_DESB;

/***************************************************************************
    Descripcion           : Inserta un registro en la tabla int_histo_lotes
    Fecha Creacion    : 17/03/2014
    Autor                   : Sebastian Guerra
***************************************************************************/
    PROCEDURE INT_PR_INSER_HISTO_LOTES(
        psOidPais                       NUMBER,
        psCodInterfaz               VARCHAR2,
        psNumeroLote               VARCHAR2,
        psContadorReg             NUMBER,
        pdFechaInicio                DATE
    )
    IS
    
    BEGIN
        INSERT INTO int_histo_lotes
                    (cod_inte, 
                      num_lote, 
                      val_desc_lote, 
                      fec_inic_proc, 
                      fec_fin_proc, 
                      ind_log_erro, 
                      num_regi_proc, 
                      num_regi_erro,
                      pais_oid_pais
                    )
             VALUES (psCodInterfaz, 
                            psNumeroLote, 
                            psCodInterfaz, 
                            pdFechaInicio,
                            SYSDATE, 
                            0, 
                            psContadorReg, 
                            0,
                            psOidPais
                    );
    END INT_PR_INSER_HISTO_LOTES;

END INT_PKG_MAE ;
/
