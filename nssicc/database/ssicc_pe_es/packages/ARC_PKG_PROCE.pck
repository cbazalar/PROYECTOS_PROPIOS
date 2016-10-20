CREATE OR REPLACE PACKAGE ARC_PKG_PROCE is

 PROCEDURE ARC_PR_CCC_ARCHI_CLIEN(
  p_oid_clie                   IN   mae_clien.oid_clie%TYPE,
  p_num_dias_anti              IN   NUMBER DEFAULT NULL);

 PROCEDURE ARC_PR_CCC_ARCHI_RETIR_SINDE;
 
 PROCEDURE ARC_PR_PROCE_BORRA_PERIO;
  
 PROCEDURE ARC_PR_BORRA_PERIO_FECHA_ANTIG;

 PROCEDURE ARC_PR_BORRA_PERIO_CAMPA_ANTIG;
   
 PROCEDURE ARC_PR_CCC_CARGA_RETIR_SINDE(
  p_num_proc                     IN   NUMBER);
 
 PROCEDURE ARC_PR_CCC_PROCE_ARCHI_MASIV(
  p_oid_proc                     IN    NUMBER);
 
 PROCEDURE FIN_PR_REGIS_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                      IN   VARCHAR2,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_nom_tabl                     IN   VARCHAR2,
  p_cod_proc_deta                OUT  VARCHAR2,
  p_cod_erro                     OUT  VARCHAR2);

  PROCEDURE FIN_PR_ACTUA_PROCE_DETAL(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_des_log                        IN   fin_proce_ejecu.des_log%TYPE);

 PROCEDURE FIN_PR_FINAL_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_esta_fin                 IN   fin_proce_ejecu.cod_esta_proc%TYPE,
  p_num_rows                     IN   fin_proce_ejecu_detal.num_rows%TYPE);
       
END ARC_PKG_PROCE;
/
CREATE OR REPLACE PACKAGE BODY ARC_PKG_PROCE IS

 -- Declaracion de Variables --
 gv_des_log                        VARCHAR2(2500);

 gc_cod_modu                       CONSTANT fin_proce_ejecu.cod_modu%TYPE:='ARC';
 gc_cod_proc_arch_clie             CONSTANT NUMBER(4):=1310;
 gc_cod_proc_borr_peri             CONSTANT NUMBER(4):=1314;
 
 /* Declaracion de variables */
 ln_sqlcode                            NUMBER(10);
 ls_sqlerrm                            VARCHAR2(1500);
 
 -- Excepciones --
 gv_reco_trac                    FIN_PKG_GENER.error_rt; 
  
 PROCEDURE ARC_PR_CCC_ARCHI_CLIEN(
  p_oid_clie                   IN   mae_clien.oid_clie%TYPE,
  p_num_dias_anti              IN   NUMBER DEFAULT NULL)
 IS
 
  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE; 
   
 BEGIN

  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_proc_arch_clie;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Archivado Consultora : ' || p_oid_clie;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  
  
  
  
  IF p_num_dias_anti IS NULL THEN
  
   INSERT INTO ccc_movim_cuent_corri_archi
    SELECT 
     mcc.oid_movi_cc,
     lv_id_proc_ejec
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.clie_oid_clie = p_oid_clie; 

   gv_des_log:='   Insertando en CCC_MOVIM_CUENT_CORRI_ARCHI : ' || SQL%ROWCOUNT;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
         
   INSERT INTO ccc_movim_banca_archi
    SELECT 
     mb.oid_movi_banc,
     lv_id_proc_ejec
    FROM ccc_movim_banca mb
    WHERE mb.clie_oid_clie = p_oid_clie; 
   
   gv_des_log:='   Insertando en CCC_MOVIM_BANCA_ARCHI : ' || SQL%ROWCOUNT;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   
  ELSE
    
   INSERT INTO ccc_movim_cuent_corri_archi
    SELECT 
     mcc.oid_movi_cc,
     lv_id_proc_ejec
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.clie_oid_clie = p_oid_clie
      AND TRUNC(SYSDATE) - mcc.fec_docu >= p_num_dias_anti; 
   
   gv_des_log:='   Insertando en CCC_MOVIM_CUENT_CORRI_ARCHI : ' || SQL%ROWCOUNT;
   FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
     
   INSERT INTO ccc_movim_banca_archi
    SELECT 
     mb.oid_movi_banc,
     lv_id_proc_ejec
    FROM ccc_movim_banca mb
    WHERE mb.clie_oid_clie = p_oid_clie
      AND TRUNC(SYSDATE) - mb.fec_proc >= p_num_dias_anti;  
  
    gv_des_log:='   Insertando en CCC_MOVIM_BANCA_ARCHI : ' || SQL%ROWCOUNT;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
     
  END IF;
  
  --------------------------------------------------
  INSERT INTO ccc_detal_cargo_histo hdet
   SELECT det.*
   FROM ccc_detal_cargo_abono_direc det
   WHERE EXISTS (
     SELECT NULL
     FROM ccc_movim_cuent_corri_archi md   
     WHERE md.oid_movi_cc = det.mvcc_oid_movi_cc);
    
  gv_des_log:='   Insertando en CCC_DETAL_CARGO_HISTO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   
  DELETE FROM ccc_detal_cargo_abono_direc det
   WHERE EXISTS (
     SELECT NULL
     FROM ccc_movim_cuent_corri_archi md   
     WHERE md.oid_movi_cc = det.mvcc_oid_movi_cc);
  
  gv_des_log:='   Borrando en CCC_DETAL_CARGO_HISTO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
    
  ---------------------------------------------------        
  INSERT INTO ccc_histo_movim_histo hhis
   SELECT his.*
   FROM 
    ccc_histo_movim_cc his,
    ccc_movim_cuent_corri_archi md    
   WHERE his.mvcc_oid_movi_cc = md.oid_movi_cc;    
  
  gv_des_log:='   Insertando en CCC_HISTO_MOVIM_HISTO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  DELETE FROM ccc_histo_movim_cc his
  WHERE EXISTS (
   SELECT NULL
   FROM ccc_movim_cuent_corri_archi md   
   WHERE md.oid_movi_cc = his.mvcc_oid_movi_cc);
  
  gv_des_log:='   Borrando CCC_HISTO_MOVIM_CC : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  ---------------------------------------------------        
  INSERT INTO ccc_aplic_abono_cargo_histo
   SELECT cad.*
   FROM 
    ccc_aplic_abono_cargo cad,
    ccc_movim_cuent_corri_archi md    
   WHERE cad.mvcc_oid_movi_carg = md.oid_movi_cc;    
  
  gv_des_log:='   Insertando en CCC_APLIC_ABONO_CARGO_HISTO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  DELETE FROM ccc_aplic_abono_cargo cad
  WHERE EXISTS (
   SELECT NULL
   FROM ccc_movim_cuent_corri_archi md   
   WHERE md.oid_movi_cc = cad.mvcc_oid_movi_carg);
   
  gv_des_log:='   Borrando en CCC_APLIC_ABONO_CARGO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);    

  ---------------------------------------------------    
  INSERT INTO ccc_movim_cuent_histo
   SELECT mcc.*
   FROM 
    ccc_movim_cuent_corri mcc,
    ccc_movim_cuent_corri_archi md
   WHERE mcc.oid_movi_cc = md.oid_movi_cc;
  
  gv_des_log:='   Insertando en CCC_MOVIM_CUENT_HISTO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
   
  DELETE FROM ccc_movim_cuent_corri mcc
  WHERE EXISTS (
   SELECT NULL
   FROM ccc_movim_cuent_corri_archi md   
   WHERE md.oid_movi_cc = mcc.oid_movi_cc);
  
  gv_des_log:='   Borrando en CCC_MOVIM_CUENT_CORRI : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  ---------------------------------------------------
  INSERT INTO ccc_detal_cargo_histo hdet
   SELECT det.*
   FROM ccc_detal_cargo_abono_direc det
   WHERE EXISTS (
      SELECT NULL
      FROM ccc_movim_banca_archi md   
      WHERE md.oid_movi_banc = det.cmba_oid_movi_banc);
  
  gv_des_log:='   Insertando en CCC_DETAL_CARGO_HISTO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  DELETE FROM ccc_detal_cargo_abono_direc det
   WHERE EXISTS (
     SELECT NULL
     FROM ccc_movim_banca_archi md   
     WHERE md.oid_movi_banc = det.cmba_oid_movi_banc);
  
  gv_des_log:='   Borrando en CCC_DETAL_CARGO_ABONO_DIREC : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  ------------------------------------------------------                
  INSERT INTO ccc_movim_banca_histo hban
   SELECT ban.*
   FROM 
    ccc_movim_banca ban,
    ccc_movim_banca_archi md    
   WHERE ban.oid_movi_banc = md.oid_movi_banc;
  
  gv_des_log:='   Insertando en CCC_MOVIM_BANCA_HISTO : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

      
  DELETE FROM ccc_movim_banca mb
  WHERE EXISTS (
   SELECT NULL
   FROM ccc_movim_banca_archi md   
   WHERE md.oid_movi_banc = mb.oid_movi_banc);
   
  gv_des_log:='   Borrando en CCC_MOVIM_BANCA : ' || SQL%ROWCOUNT;
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
    
  -------------------------------------------------------
 
  DELETE FROM ccc_movim_cuent_corri_archi WHERE oid_proc = lv_id_proc_ejec;
  DELETE FROM ccc_movim_banca_archi WHERE oid_proc = lv_id_proc_ejec;
  
  gv_des_log:='Fin Archivado Consultora';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);

  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

EXCEPTION

 WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );
      
 END ARC_PR_CCC_ARCHI_CLIEN;
 
 PROCEDURE ARC_PR_CCC_ARCHI_RETIR_SINDE
 IS
 
 CURSOR c_reti
 IS
 SELECT 
  mc.cod_clie,
  mc.oid_clie
 FROM
  mae_clien mc,
  mae_clien_datos_adici mcda
 WHERE mc.sal_deud_ante = 0
   AND mc.oid_clie = mcda.clie_oid_clie
   AND mcda.esta_oid_esta_clie = 7
   AND NOT EXISTS (
            SELECT NULL
            FROM ccc_consu_casti_cabec c
            WHERE c.oid_clie = mc.oid_clie)
   AND NOT EXISTS (
            SELECT NULL
            FROM ccc_clien_casti c
            WHERE c.oid_clie = mc.oid_clie)
   AND EXISTS (
            SELECT NULL
            FROM ccc_movim_cuent_corri mcc
            WHERE mcc.clie_oid_clie = mc.oid_clie)
   AND NOT EXISTS (
            SELECT NULL
            FROM ccc_movim_banca mb
            WHERE mb.fec_proc > TRUNC(SYSDATE - 180)
              AND mb.clie_oid_clie = mc.oid_clie)
   AND NOT EXISTS (
            SELECT NULL
            FROM ccc_movim_cuent_corri a
            WHERE a.fec_docu > TRUNC(SYSDATE - 180)
              AND a.clie_oid_clie = mc.oid_clie);

  lv_cont                        NUMBER(12):=0;
  lv_cant_clie                   NUMBER(12):=0;
  
 BEGIN
   
  lv_cant_clie := NVL(CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CantDepuRetiSinD'),2);
  
  FOR v_reti IN c_reti LOOP
    
   ARC_PR_CCC_ARCHI_CLIEN(v_reti.oid_clie);
   
   COMMIT;
   
   lv_cont := lv_cont + 1;
    
   IF lv_cont > lv_cant_clie THEN
    EXIT;  
   END IF;
   
  END LOOP;
  
 END ARC_PR_CCC_ARCHI_RETIR_SINDE;
 
 PROCEDURE ARC_PR_PROCE_BORRA_PERIO
 IS
 BEGIN

  ARC_PR_BORRA_PERIO_FECHA_ANTIG;
  ARC_PR_BORRA_PERIO_CAMPA_ANTIG;
       
 END ARC_PR_PROCE_BORRA_PERIO;
 
 PROCEDURE ARC_PR_BORRA_PERIO_FECHA_ANTIG
 IS
 CURSOR c_enti_depu IS
  SELECT r.val_nomb_tabl,
         r.val_camp_fech,
         r.val_nume_dias
  FROM arc_entid_borra_perio_fecha r
  WHERE r.ind_acti =  1 ;

  CURSOR c_Constraints( p_cNombre_Tabla VARCHAR2 ) IS
  SELECT Table_Name, Constraint_Name, Column_Name
  FROM   all_cons_columns AC
  WHERE  AC.Table_Name = p_cNombre_Tabla ;
 
  lv_sql                         VARCHAR2(4000);
  
  lv_cod_erro                      VARCHAR2(4000);

  lv_id_proc_ejec                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_id_proc_deta                  fin_proce_ejecu.cod_proc_ejec%TYPE;
  lv_log_cod_proc                  fin_proce_ejecu.cod_proc%TYPE;
  lv_log_user                      fin_proce_ejecu.usu_proc%TYPE; 
  l_rows_affected                  NUMBER(12);
  
 BEGIN
 
  lv_log_user     := USER;
  lv_log_cod_proc := gc_cod_proc_borr_peri;

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,lv_cod_erro);

  gv_des_log:='Inicio Borrando Periodico por Fecha Antiguedad ';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  
  FOR v_enti_depu IN c_enti_depu LOOP
   
    /*
    FOR J IN c_Constraints( v_enti_depu.val_nomb_tabl ) LOOP
      lv_sql := ' DELETE FROM ' || J.Table_Name || ' M WHERE M.' || J.Column_Name || ' = ' || J.Column_Name ;    
      --dbms_output.put_line(lv_sql);       
   --+++++++++      EXECUTE IMMEDIATE lv_sql;      
    END LOOP ;   
    */
    
    gv_des_log:=' *** Borrando Entidad ' || v_enti_depu.val_nomb_tabl;
    FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
            
    lv_sql := NULL ; 
    lv_sql := 'DELETE FROM ' || v_enti_depu.val_nomb_tabl || 
              ' WHERE TRUNC(SYSDATE) - ' || v_enti_depu.val_camp_fech || ' > ' || v_enti_depu.val_nume_dias; 
    
    FIN_PR_REGIS_PROCE_DETAL(gc_cod_modu,lv_log_cod_proc,lv_log_user,lv_id_proc_ejec,v_enti_depu.val_nomb_tabl,lv_id_proc_deta,lv_cod_erro);
                                           
    EXECUTE IMMEDIATE lv_sql ;
    
    l_rows_affected := SQL%ROWCOUNT;
    
    dbms_output.put_line(l_rows_affected);
    
    FIN_PR_FINAL_PROCE_DETAL(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,lv_id_proc_deta,'2',l_rows_affected);        
                
    COMMIT ;
    
  END LOOP ;

  gv_des_log:='Fin del Proceso';
  FIN_PKG_GENER.FIN_PR_ACTUA_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,gv_des_log);
  FIN_PKG_GENER.FIN_PR_FINAL_PROCE(gc_cod_modu,lv_log_cod_proc,lv_id_proc_ejec,'2');

 EXCEPTION

  WHEN OTHERS THEN
   gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   RAISE_APPLICATION_ERROR (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );
                                   
 END ARC_PR_BORRA_PERIO_FECHA_ANTIG;
 
 PROCEDURE ARC_PR_BORRA_PERIO_CAMPA_ANTIG
 IS
 CURSOR c_enti_depu
 IS
  SELECT 
   r.val_nomb_tabl,
   r.val_camp_peri,
   r.val_nume_anti   
  FROM arc_entid_borra_perio_campa r
  WHERE r.ind_acti = 1;
    
  CURSOR c_Constraints( p_cNombre_Tabla VARCHAR2 ) IS
  SELECT Table_Name, Constraint_Name, Column_Name
  FROM   all_cons_columns AC
  WHERE  AC.Table_Name = p_cNombre_Tabla ;
 
  lv_sql                         VARCHAR2(4000);
   
 BEGIN
 
  FOR v_enti_depu IN c_enti_depu LOOP
    
    FOR J IN c_Constraints( v_enti_depu.val_nomb_tabl ) LOOP
      lv_sql := ' DELETE FROM ' || J.Table_Name || 'M WHERE M.' || J.Column_Name || ' = ' || J.Column_Name ;    
      --dbms_output.put_line(lv_sql);
       
--+++++++++      EXECUTE IMMEDIATE lv_sql;
      
    END LOOP ;
      
    lv_sql := NULL ; 
    lv_sql := 'DELETE FROM ' || v_enti_depu.val_nomb_tabl || 
              ' WHERE TRUNC(SYSDATE) - ' || v_enti_depu.val_camp_peri || ' > ' || v_enti_depu.val_nume_anti; 
       
    dbms_output.put_line(lv_sql) ;
       
--------+++    EXECUTE IMMEDIATE lv_sql ;
    
  END LOOP ;
      
 END ARC_PR_BORRA_PERIO_CAMPA_ANTIG;
 
 /*
 PROCEDURE ARC_PR_ENTID_FECHA_ANTIG_ARCHI 
 AS
   
 CURSOR c_enti_depu
 IS
  SELECT 
   r.val_nomb_tabl,
   r.val_camp_fech,
   r.val_nume_dias,
   r.val_nomb_tabl_hist
  FROM arc_entid_fecha_antig r
  WHERE r.val_nomb_tabl_hist is NOT NULL
    AND r.ind_acti = 1;
 
  lv_sql                         VARCHAR2(4000);
   
 BEGIN
 
  FOR v_enti_depu IN c_enti_depu LOOP
  
   lv_sql := 'INSERT INTO ' || v_enti_depu.val_nomb_tabl_hist || 
             ' SELECT * FROM ' || v_enti_depu.val_nomb_tabl || 
             ' WHERE TRUNC(SYSDATE) - ' || v_enti_depu.val_camp_fech || ' > ' || v_enti_depu.val_nume_dias;
   
   dbms_output.put_line(lv_sql);
    
   EXECUTE IMMEDIATE lv_sql;
                   
   lv_sql := 'DELETE FROM ' || v_enti_depu.val_nomb_tabl || 
             ' WHERE TRUNC(SYSDATE) - ' || v_enti_depu.val_camp_fech || ' > ' || v_enti_depu.val_nume_dias; 
   
   dbms_output.put_line(lv_sql);
   
   EXECUTE IMMEDIATE lv_sql; 
     
  END LOOP;
 
 END ARC_PR_ENTID_FECHA_ANTIG_ARCHI;
 */
 
 PROCEDURE ARC_PR_CCC_CARGA_RETIR_SINDE(
  p_num_proc                     IN   NUMBER)
 IS
 
  lv_cant_regi                   NUMBER(12);
  lv_cant_proc                   NUMBER(12);
 
 BEGIN
  
  DELETE FROM arc_proce_masiv_clien;
   
  INSERT INTO arc_proce_masiv_clien 
   SELECT 
    mc.oid_clie,
    NULL
   FROM
    mae_clien mc,
    mae_clien_datos_adici mcda
   WHERE mc.sal_deud_ante = 0
     AND mc.oid_clie = mcda.clie_oid_clie
     AND mcda.esta_oid_esta_clie = 7
     AND NOT EXISTS (
            SELECT NULL
            FROM ccc_consu_casti_cabec c
            WHERE c.oid_clie = mc.oid_clie)
     AND NOT EXISTS (
            SELECT NULL
            FROM ccc_clien_casti c
            WHERE c.oid_clie = mc.oid_clie)
     AND EXISTS (
            SELECT NULL
            FROM ccc_movim_cuent_corri mcc
            WHERE mcc.clie_oid_clie = mc.oid_clie)
     AND NOT EXISTS (
            SELECT NULL
            FROM ccc_movim_banca mb
            WHERE mb.fec_proc > TRUNC(SYSDATE - 180)
              AND mb.clie_oid_clie = mc.oid_clie)
     AND NOT EXISTS (
            SELECT NULL
            FROM ccc_movim_cuent_corri a
            WHERE a.fec_docu > TRUNC(SYSDATE - 180)
              AND a.clie_oid_clie = mc.oid_clie);
 
  SELECT COUNT(*)
  INTO lv_cant_regi
  FROM arc_proce_masiv_clien;             
  
  lv_cant_proc :=  ROUND(lv_cant_regi/ p_num_proc,0);
  
  FOR i IN 1 .. p_num_proc LOOP
  
   IF i < p_num_proc THEN
   
    UPDATE arc_proce_masiv_clien
    SET oid_proc = i
    WHERE oid_proc IS NULL
      AND ROWNUM <= lv_cant_proc;
   
   ELSE
     
    UPDATE arc_proce_masiv_clien
    SET oid_proc = i
    WHERE oid_proc IS NULL;
    
   END IF;
     
  END LOOP;
  
 END ARC_PR_CCC_CARGA_RETIR_SINDE;
 
 PROCEDURE ARC_PR_CCC_PROCE_ARCHI_MASIV(
  p_oid_proc                     IN    NUMBER)
 IS
 
 CURSOR c_reti_sind
 IS
  SELECT 
   c.oid_clie
  FROM arc_proce_masiv_clien c
  WHERE c.oid_proc = p_oid_proc;
 
  lv_cont                        NUMBER(12); 
  
 BEGIN
 
  FOR v_reti_sind in c_reti_sind LOOP
   
   lv_cont := 1;
   
   WHILE lv_cont = 1
     
   LOOP 
   
    SELECT COUNT(*)
    INTO lv_cont
    FROM fin_proce_ejecu f
    WHERE f.cod_modu = 'CCC'
      AND f.cod_proc IN ('01','05','REPW','16')
      AND f.ind_ejec = 'S';
   
    IF lv_cont = 0 THEN
     ARC_PR_CCC_ARCHI_CLIEN(v_reti_sind.oid_clie);  
    ELSE
     DBMS_LOCK.SLEEP(300);
    END IF; 
   
   END LOOP;
      
   COMMIT;
   
  END LOOP;
      
 END ARC_PR_CCC_PROCE_ARCHI_MASIV;
 
 PROCEDURE FIN_PR_REGIS_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_usu                      IN   VARCHAR2,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_nom_tabl                     IN   VARCHAR2,
  p_cod_proc_deta                OUT  VARCHAR2,
  p_cod_erro                     OUT  VARCHAR2)
 IS

  lv_reg_fin_proce_ejecu  fin_proce_ejecu_detal%ROWTYPE;

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  lv_reg_fin_proce_ejecu.cod_modu:= p_cod_modu;
  lv_reg_fin_proce_ejecu.cod_proc:=p_cod_proc;

  SELECT fpm.des_proc
  INTO lv_reg_fin_proce_ejecu.des_proc
  FROM fin_proce_modul fpm
  WHERE fpm.cod_modu=p_cod_modu
  AND fpm.cod_proc=p_cod_proc;

  lv_reg_fin_proce_ejecu.cod_proc_ejec := p_cod_proc_ejec;   
  
  SELECT (1+ABS(MOD(dbms_random.random,1000000)))
  INTO lv_reg_fin_proce_ejecu.cod_proc_ejec_deta
  FROM dual;
  
  p_cod_proc_deta := lv_reg_fin_proce_ejecu.cod_proc_ejec_deta;
  
  lv_reg_fin_proce_ejecu.fec_inic_proc := SYSDATE;    
  lv_reg_fin_proce_ejecu.val_nomb_tabl := p_nom_tabl;  
  lv_reg_fin_proce_ejecu.des_log := NULL;

  INSERT INTO fin_proce_ejecu_detal VALUES lv_reg_fin_proce_ejecu;

  COMMIT;

 EXCEPTION

  WHEN OTHERS THEN
   p_cod_erro:='errors.finanzas.proceso.no.registrado';

 END FIN_PR_REGIS_PROCE_DETAL;
 
 PROCEDURE FIN_PR_ACTUA_PROCE_DETAL(
  p_cod_modu                       IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                       IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                  IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_des_log                        IN   fin_proce_ejecu.des_log%TYPE)

 IS

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  UPDATE fin_proce_ejecu_detal p
  SET p.des_log= p.des_log || CHR(13) || to_char(SYSDATE,'HH:MI:SS') || ' : '|| p_des_log
  WHERE p.cod_modu = p_cod_modu
    AND p.cod_proc = p_cod_proc
    AND p.cod_proc_ejec = p_cod_proc_ejec
    AND p.cod_proc_ejec_deta = p_cod_proc_deta;

  COMMIT;

 END FIN_PR_ACTUA_PROCE_DETAL;
 
 PROCEDURE FIN_PR_FINAL_PROCE_DETAL(
  p_cod_modu                     IN   fin_modul.cod_modu%TYPE,
  p_cod_proc                     IN   fin_proce_modul.cod_proc%TYPE,
  p_cod_proc_ejec                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_proc_deta                IN   fin_proce_ejecu.cod_proc_ejec%TYPE,
  p_cod_esta_fin                 IN   fin_proce_ejecu.cod_esta_proc%TYPE,
  p_num_rows                     IN   fin_proce_ejecu_detal.num_rows%TYPE)
 IS

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  UPDATE fin_proce_ejecu_detal p
  SET
   p.fec_fina_proc= SYSDATE,
   p.num_rows = p_num_rows      
  WHERE p.cod_modu = p_cod_modu
    AND p.cod_proc = p_cod_proc
    AND p.cod_proc_ejec = p_cod_proc_ejec
    AND p.cod_proc_ejec_deta = p_cod_proc_deta;

  COMMIT;

 END FIN_PR_FINAL_PROCE_DETAL;
 
END ARC_PKG_PROCE;
/
