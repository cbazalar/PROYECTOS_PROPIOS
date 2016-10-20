CREATE OR REPLACE PACKAGE pq_plani AS
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  k_prdto_borrado           CONSTANT gen_prdto.cod_proc%TYPE := 'BORRADO_PERIODICO';
  k_prdto_borrado_num_dias  CONSTANT gen_prdto_param.cod_para%TYPE := 'NUM_DIAS_BORRADO';
  k_prdto_borrado_crit_borr CONSTANT gen_prdto_param.cod_para%TYPE := 'CAMPO_CRITERIO_BORRADO';

  -------ENCABEZADO-------

  ------------------------------------------------------------------------------
  -- Procedimiento de validacion de planificacion de una tarea
  ------------------------------------------------------------------------------
  FUNCTION fn_valid_planif
  (
    pi_cod_prdto gen_prdto.cod_proc%TYPE,
    pi_reg_prdto gen_prdto%ROWTYPE
  ) RETURN VARCHAR2;

  ------------------------------------------------------------------------------
  -- Procedimiento de calculo de planificacion
  ------------------------------------------------------------------------------
  PROCEDURE pr_calculo_planif
  (
    pi_reg_proc        gen_prdto%ROWTYPE,
    po_primera_fecha   OUT DATE,
    po_siguiente_fecha OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento de borrado de datos antiguos
  ------------------------------------------------------------------------------
  PROCEDURE pr_pdto_borrado_datos;

  PROCEDURE executor(str_par IN VARCHAR2);

  FUNCTION fn_planif_semanal
  (
    pi_fecha        IN DATE,
    pi_ind_dia_sem1 NUMBER,
    pi_ind_dia_sem2 NUMBER,
    pi_ind_dia_sem3 NUMBER,
    pi_ind_dia_sem4 NUMBER,
    pi_ind_dia_sem5 NUMBER,
    pi_ind_dia_sem6 NUMBER,
    pi_ind_dia_sem7 NUMBER
  ) RETURN DATE;

  ------------------------------------------------------------------------------
  -- Procedimiento DTO_OBTENER_DESCUENTOS
  ------------------------------------------------------------------------------

  PROCEDURE dto_obtener_descuentos
  (
    idperi        IN NUMBER,
    idmarca       IN NUMBER,
    idcanal       IN NUMBER,
    idacces       IN NUMBER,
    idsubacces    IN NUMBER,
    idzona        IN NUMBER,
    idpais        IN NUMBER,
    idregion      IN NUMBER,
    idsubgerencia IN NUMBER,
    idcliente     IN NUMBER,
    salida_text   OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_DATOS_MENSAJES
  ------------------------------------------------------------------------------

  PROCEDURE ped_obtener_datos_mensajes
  (
    idsoliccabecera IN NUMBER,
    idpais          IN NUMBER,
    idsubacceso     IN NUMBER,
    idacceso        IN NUMBER,
    idcanal         IN NUMBER,
    codigoventa     IN VARCHAR2,
    salida_text     OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_DAT_MEN_PREM
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_dat_men_prem
  (
    idsoliccabecera IN NUMBER,
    idpais          IN NUMBER,
    idsubacceso     IN NUMBER,
    idacceso        IN NUMBER,
    idcanal         IN NUMBER,
    idproducto      IN VARCHAR2,
    salida_text     OUT VARCHAR2

  );

  ------------------------------------------------------------------------------
  -- Procedimiento MAV_OBTENER_DETALLES_MAV
  ------------------------------------------------------------------------------

  PROCEDURE mav_obtener_detalles_mav
  (
    idperi      IN NUMBER,
    idcliente   IN NUMBER,
    idestado    IN NUMBER,
    salida_text OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_LIMITE_VENTA
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_limite_venta
  (
    idperi              IN NUMBER,
    idcliente           IN NUMBER,
    idcabecerasolicitud IN NUMBER,
    idestado            IN NUMBER,
    ididioma            IN NUMBER,
    salida_text         OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_INDICADORES_FLETE
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_indicadores_flete
  (
    cliente      IN NUMBER,
    oidsolicitud IN NUMBER,
    salida_text  OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_POSIC_RECUPERACION
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_posic_recuperacion
  (
    oidsolicitud IN NUMBER,
    recuperacion IN NUMBER,
    salida_text  OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_POSIC_SOLICITUD
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_posic_solicitud
  (
    oidsolicitud IN NUMBER,
    salida_text  OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_PREC_POSIC_MATRIZ
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_prec_posic_matriz
  (
    oidsolicitud            IN NUMBER,
    estado_posicion_anulado IN NUMBER,
    salida_text             OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_VAL_MONTO_MINIMO
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_val_monto_minimo
  (
    oidsolicitud IN NUMBER,
    salida_text  OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_REALIZAR_CALCULO
  ------------------------------------------------------------------------------
  PROCEDURE ped_realizar_calculo
  (
    oidsolicitud            IN NUMBER,
    estado_posicion_anulado IN NUMBER,
    numerodecimales         IN NUMBER,
    salida_text             OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_ACTUA_CABEC_ACUM
  ------------------------------------------------------------------------------
  PROCEDURE ped_actua_cabec_acum
  (
    fecha_ini  IN VARCHAR2,
    fecha_fin  IN VARCHAR2,
    periodo    IN VARCHAR2,
    periodosig IN VARCHAR2,
    salida     OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento Pre_Actua_Buzon_Mensa
  ------------------------------------------------------------------------------
  PROCEDURE pre_actua_buzon_mensa
  (
    param_valores_insert IN VARCHAR2,
    param_oid_pais       IN NUMBER,
    param_oid_clie       IN NUMBER,
    param_oid_cabec      IN NUMBER,
    param_salida         OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento Pre_Gene_Esta_Cv
  ------------------------------------------------------------------------------
  PROCEDURE pre_gene_esta_cv
  (
    oidsolicabe  IN NUMBER,
    periodo      IN NUMBER,
    tiposolipais IN NUMBER,
    cliente      IN NUMBER
  );

  ------------------------------------------------------------------------------
  -- Procedimiento Ped_Cargar_Acum3
  ------------------------------------------------------------------------------
  PROCEDURE ped_cargar_acum3
  (
    oidcabe IN NUMBER,
    tsp     IN NUMBER,
    clien   IN NUMBER,
    peri    IN NUMBER
  );

  ------------------------------------------------------------------------------
  -- Procedimiento Ped_Carga_Acumu_Clien_Peri
  ------------------------------------------------------------------------------
  PROCEDURE ped_carga_acumu_clien_peri
  (
    oidcabe IN NUMBER,
    tsp     IN NUMBER,
    clien   IN NUMBER,
    peri    IN NUMBER,
    salida  OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento FN_CU_CALC_IND_INCOBRABLE
  ------------------------------------------------------------------------------
  FUNCTION fn_cu_calc_ind_incobrable
  (
    idpais_par  IN VARCHAR2,
    idmarca_par IN VARCHAR2,
    idcanal_par IN VARCHAR2,
    idclien_par IN VARCHAR2
  ) RETURN NUMBER;

  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION FN_FECHA_DOCUMENTO
  ------------------------------------------------------------------------------
  FUNCTION fn_fecha_documento
  (
    idpais_par  IN VARCHAR2,
    idmarca_par IN VARCHAR2,
    idcanal_par IN VARCHAR2,
    idclien_par IN VARCHAR2
  ) RETURN DATE;

  ------------------------------------------------------------------------------
  -- Procedimiento IMP_PR_PROCE_DETAL_FACTU_PERF
  ------------------------------------------------------------------------------
  PROCEDURE imp_pr_proce_detal_factu_perf
  (
    p_oidsolicitud  IN NUMBER,
    p_codigopais    IN VARCHAR2,
    p_codigoperiodo IN VARCHAR2,
    salida          OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento OCR_PR_COMP_MONT_MINIMO
  ------------------------------------------------------------------------------
  PROCEDURE ocr_pr_comp_mont_minimo
  (
    p_oidpais       IN NUMBER,
    p_codigousuario IN VARCHAR2,
    salida          OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_ACTUA_RESUM_PEDID
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_actua_resum_pedid
  (
    p_oidpais    IN NUMBER,
    p_oidmarca   IN NUMBER,
    p_oidcanal   IN NUMBER,
    p_oidperiodo IN NUMBER,
    salida       OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_ACTUA_PEDID_INCEN
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_actua_pedid_incen
  (
    p_oidpais    IN NUMBER,
    p_oidmarca   IN NUMBER,
    p_oidcanal   IN NUMBER,
    p_oidperiodo IN NUMBER,
    salida       OUT VARCHAR2
  );
  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_elimi_produ_alter_falta(p_oidsolicabe NUMBER);
  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_gener_produ_alter_falta(p_oidsolicabe NUMBER);
  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_inser_produ_alter_falta
  (
    p_oid_prod_alte_falt ped_produ_alter_falta.oid_prod_alte_falt%TYPE,
    p_soca_oid_soli_cabe ped_produ_alter_falta.soca_oid_soli_cabe%TYPE,
    p_cod_vent_orig      ped_produ_alter_falta.cod_vent_orig%TYPE,
    p_cod_vent_alte      ped_produ_alter_falta.cod_vent_alte%TYPE,
    p_des_alte           ped_produ_alter_falta.des_alte%TYPE,
    p_imp_prec_cata      ped_produ_alter_falta.imp_prec_cata%TYPE,
    p_des_cata           ped_produ_alter_falta.des_cata%TYPE
  );

  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_inser_detal_prol
  (
    p_oid_deta_prol           ped_detal_prol.oid_deta_prol%TYPE,
    p_soca_oid_soli_cabe      ped_detal_prol.soca_oid_soli_cabe%TYPE,
    p_val_codi_vent           ped_detal_prol.val_codi_vent%TYPE,
    p_num_unid_dema           ped_detal_prol.num_unid_dema%TYPE,
    p_num_unid_dema_real      ped_detal_prol.num_unid_dema_real%TYPE,
    p_num_unid_comp           ped_detal_prol.num_unid_comp%TYPE,
    p_val_prec_cata_unit_loca ped_detal_prol.val_prec_cata_unit_loca%TYPE,
    p_val_porc_desc           ped_detal_prol.val_porc_desc%TYPE,
    p_val_impo_desc_unit_loca ped_detal_prol.val_impo_desc_unit_loca%TYPE,
    p_val_prec_fact_unit_loca ped_detal_prol.val_prec_fact_unit_loca%TYPE,
    p_val_obse                ped_detal_prol.val_obse%TYPE,
    p_cod_vent_orig           ped_detal_prol.cod_vent_orig%TYPE
  );

  ------------------------------------------------------------------------------
  -- Procedimiento OCR_PR_PROCE_ESPEC
  ------------------------------------------------------------------------------
  PROCEDURE ocr_pr_proce_espec
  (
    p_oidpais       IN NUMBER,
    p_oidsolicitud  IN NUMBER,
    p_codigousuario IN VARCHAR2,
    salida          OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION PED_FN_OBTEN_GASTO_ADMIN
  ------------------------------------------------------------------------------
  FUNCTION ped_fn_obten_gasto_admin(idconsolidado IN NUMBER) RETURN NUMBER;

  ------------------------------------------------------------------------------
  -- Procedimiento FAC_PR_GASTOS_ADMI_SICC
  ------------------------------------------------------------------------------
  PROCEDURE fac_pr_gastos_admi_sicc(p_oidpais IN NUMBER);

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_GENER_SOLIC_BOLSA_FALTA
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_gener_solic_bolsa_falta
  (
    p_oidpais    IN NUMBER,
    p_oidmarca   IN NUMBER,
    p_oidcanal   IN NUMBER,
    p_oidperiodo IN NUMBER,
    p_oidzona    IN NUMBER,
    p_usuario    IN VARCHAR2,
    salida       OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_PROGR_NUEVA_REGAL
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_progr_nueva_regal
  (
    p_oidpais     NUMBER,
    p_oidperiodo  NUMBER,
    p_oidconcurso NUMBER,
    p_oidcliente  NUMBER,
    p_resultado   OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_MONTO_MAXIM
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_monto_maxim
  ( p_oidsolicitud  IN NUMBER,
    p_usuario       IN VARCHAR2,
    salida          OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_MONTO_MINIM
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_monto_minim
  ( p_oidsolicitud  IN NUMBER,
    p_usuario       IN VARCHAR2,
    salida          OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_OFERT_ESPEC
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_ofert_espec
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION INC_FN_OBTEN_PUNTA_RETAI
  ------------------------------------------------------------------------------
  FUNCTION inc_fn_obten_punta_retai
  ( p_oidpais       IN NUMBER,
    p_oidcliente    IN NUMBER,
    p_oidperiodo    IN NUMBER
  ) RETURN NUMBER;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_MONTO_MINIM_INCEN
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_monto_minim_incen
  ( p_oidsolicitud  IN NUMBER,
    p_usuario       IN VARCHAR2,
    salida          OUT VARCHAR2
  );

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_OFERT_ESPEC2
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_ofert_espec2
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  );

------------------------------------------------------------------------------
  -- Procedimiento PED_PR_ASIGNAR_CUV
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_asignar_CUV
  ( p_oidcabecera   IN NUMBER,
    p_oidcatalogo   IN NUMBER,
    salida          OUT VARCHAR2
  );
  
  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_OFERT_ESPEC2
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_cuadr_revis
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  );  

  /***************************************************************************
  Descripcion       : Calcula Segmentacion de los Procesos GP2 y GP4 de SICC
  Fecha Creacion    : 26/09/2014
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE gen_pr_calcu_segme_sicc
  (psProcesoMonitor           VARCHAR2,
   psGrupoProceso             VARCHAR2,
   pnTotalPedidos             NUMBER,
   salida                     OUT VARCHAR2);  

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_CUADR_NX
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_cuadr_nx
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  );   

  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION INC_FN_VALID_CAMPA_ANTER
  ------------------------------------------------------------------------------
  FUNCTION inc_fn_valid_campa_anter
  ( p_oidpais       IN NUMBER,
    p_oidcliente    IN NUMBER,
    p_oidperiodo    IN NUMBER
  ) RETURN NUMBER;
  
  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_ELIM_FALT_PROL_INDI
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_elim_falt_prol_indi
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  );
  
    ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_ELIM_FALT_PROL_CFIJA
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_elim_falt_prol_cfija
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  );
    
END pq_plani;
/
CREATE OR REPLACE PACKAGE BODY pq_plani AS
  ------------------------------------------------------------------------------
  -- Depuracion, mensajes por pantalla. Hace un dbms_output dividido por varias
  -- lineas cuando se exceden 255 caracteres
  ------------------------------------------------------------------------------
  PROCEDURE pr_mostrar(pi_texto IN VARCHAR2) IS
  BEGIN
    IF nvl(length(pi_texto),
           0) < 256 THEN
      dbms_output.put_line(pi_texto);
    ELSE
      -- recursivo. Mostramos los 255 primeros caracteres y volvemos a empezar
      -- con el resto
      dbms_output.put_line(substr(pi_texto,
                                  1,
                                  255));
      pr_mostrar(substr(pi_texto,
                        256,
                        length(pi_texto)));
    END IF;
  END pr_mostrar;
  ------------------------------------------------------------------------------
  -- Procedimiento de Cabecera de log de ejecucion
  -- Devuelve el oid del registro generado
  ------------------------------------------------------------------------------
  FUNCTION fn_log_ejecucion_cabec(pi_cod_proceso gen_prdto.cod_proc%TYPE) RETURN NUMBER IS
    w_oid NUMBER(12);
  BEGIN
    INSERT INTO gen_prdto_log_cabec
      (oid_log_proc,
       prdt_oid_proc,
       fec_ejec)
    VALUES
      (gen_prol_seq.nextval,
       (SELECT oid_proc FROM gen_prdto WHERE cod_proc = pi_cod_proceso),
       SYSDATE)
    RETURNING oid_log_proc INTO w_oid;
    RETURN w_oid;
  END fn_log_ejecucion_cabec;
  ------------------------------------------------------------------------------
  -- Procedimiento de Linea de log de ejecucion
  -- Devuelve el oid del registro generado
  ------------------------------------------------------------------------------
  PROCEDURE pr_log_ejecucion_linea
  (
    pi_cabecera   gen_prdto_log_cabec.oid_log_proc%TYPE,
    pio_num_linea IN OUT gen_prdto_log_detal.num_line%TYPE,
    pi_texto      VARCHAR2
  ) IS
  BEGIN
    pio_num_linea := pio_num_linea + 1;
    INSERT INTO gen_prdto_log_detal
      (oid_log_deta,
       prol_oid_log_proc,
       num_line,
       val_log)
    VALUES
      (gen_prld_seq.nextval,
       pi_cabecera,
       pio_num_linea,
       substr(pi_texto,
              1,
              100));
  END pr_log_ejecucion_linea;
  ------------------------------------------------------------------------------
  -- Procedimiento de validacion de planificacion de una tarea
  -- Podria haber validaciones concretas para algunos procedimientos, en
  -- principio las definidas son todas genericas
  ------------------------------------------------------------------------------
  FUNCTION fn_valid_planif
  (
    pi_cod_prdto gen_prdto.cod_proc%TYPE,
    pi_reg_prdto gen_prdto%ROWTYPE
  ) RETURN VARCHAR2 IS
  BEGIN
    IF pi_reg_prdto.ind_plan_unic NOT IN (0,
                                          1) THEN
      RETURN 'ind_plan_unic debe contener 0 o 1';
    END IF;
    IF pi_reg_prdto.ind_plan_sema NOT IN (0,
                                          1) THEN
      RETURN 'ind_plan_sema debe contener 0 o 1';
    END IF;
    IF pi_reg_prdto.ind_plan_mens NOT IN (0,
                                          1) THEN
      RETURN 'ind_plan_mens debe contener 0 o 1';
    END IF;
    IF (pi_reg_prdto.ind_plan_unic = 1 AND pi_reg_prdto.ind_plan_sema = 1) OR
       (pi_reg_prdto.ind_plan_unic = 1 AND pi_reg_prdto.ind_plan_mens = 1) OR
       (pi_reg_prdto.ind_plan_sema = 1 AND pi_reg_prdto.ind_plan_mens = 1) THEN
      RETURN 'Mas de una planificacion esta activa';
    END IF;
    IF pi_reg_prdto.fec_hora_prim_ejec IS NULL AND
       NOT (pi_reg_prdto.ind_plan_unic = 0 AND pi_reg_prdto.ind_plan_sema = 0 AND
        pi_reg_prdto.ind_plan_mens = 0) THEN
      --  planificacion no desactivada
      RETURN 'Error: hay que indicar el dia de la primera ejecucion en FEC_HORA_PRIM_EJEC';
    END IF;
    IF pi_reg_prdto.ind_plan_sema = 1 THEN
      IF pi_reg_prdto.ind_dia_sem1 = 0 AND pi_reg_prdto.ind_dia_sem2 = 0 AND
         pi_reg_prdto.ind_dia_sem3 = 0 AND pi_reg_prdto.ind_dia_sem4 = 0 AND
         pi_reg_prdto.ind_dia_sem5 = 0 AND pi_reg_prdto.ind_dia_sem6 = 0 AND
         pi_reg_prdto.ind_dia_sem7 = 0 THEN
        RETURN 'Error: debe activarse algun dia de la semana en programaciones semanales';
      END IF;
      IF pi_reg_prdto.ind_dia_sem1 NOT IN (1,
                                           0) OR
         pi_reg_prdto.ind_dia_sem2 NOT IN (1,
                                           0) OR
         pi_reg_prdto.ind_dia_sem3 NOT IN (1,
                                           0) OR
         pi_reg_prdto.ind_dia_sem4 NOT IN (1,
                                           0) OR
         pi_reg_prdto.ind_dia_sem5 NOT IN (1,
                                           0) OR
         pi_reg_prdto.ind_dia_sem6 NOT IN (1,
                                           0) OR
         pi_reg_prdto.ind_dia_sem7 NOT IN (1,
                                           0) THEN
        RETURN 'Error: ind_dia_semX debe ser 0 o 1';
      END IF;
    END IF;
    RETURN NULL;
  END fn_valid_planif;
  ------------------------------------------------------------------------------
  -- Funcion que devuelve el siguiente dia
  ------------------------------------------------------------------------------
  FUNCTION fn_planif_semanal
  (
    pi_fecha        IN DATE,
    pi_ind_dia_sem1 NUMBER,
    pi_ind_dia_sem2 NUMBER,
    pi_ind_dia_sem3 NUMBER,
    pi_ind_dia_sem4 NUMBER,
    pi_ind_dia_sem5 NUMBER,
    pi_ind_dia_sem6 NUMBER,
    pi_ind_dia_sem7 NUMBER
  ) RETURN DATE IS
    w_fecha_siguiente      VARCHAR2(1000);
    w_fecha_siguiente_date DATE;
    w_fecha                VARCHAR2(100) := 'to_date( ''' || to_char(pi_fecha,
                                                                     'yyyy-mm-dd') ||
                                            ''', ''yyyy-mm-dd'')';
  BEGIN
    w_fecha_siguiente := 'SELECT to_date( to_char( least( ';
    IF pi_ind_dia_sem1 = 1 THEN
      w_fecha_siguiente := w_fecha_siguiente || ' next_day( ' || w_fecha || ', ''LUNES'' ),';
    END IF;
    IF pi_ind_dia_sem2 = 1 THEN
      w_fecha_siguiente := w_fecha_siguiente || ' next_day( ' || w_fecha || ', ''MARTES'' ),';
    END IF;
    IF pi_ind_dia_sem3 = 1 THEN
      w_fecha_siguiente := w_fecha_siguiente || ' next_day( ' || w_fecha || ', ''MIERCOLES'' ),';
    END IF;
    IF pi_ind_dia_sem4 = 1 THEN
      w_fecha_siguiente := w_fecha_siguiente || ' next_day( ' || w_fecha || ', ''JUEVES'' ),';
    END IF;
    IF pi_ind_dia_sem5 = 1 THEN
      w_fecha_siguiente := w_fecha_siguiente || ' next_day( ' || w_fecha || ', ''VIERNES'' ),';
    END IF;
    IF pi_ind_dia_sem6 = 1 THEN
      w_fecha_siguiente := w_fecha_siguiente || ' next_day( ' || w_fecha || ', ''SABADO'' ),';
    END IF;
    IF pi_ind_dia_sem7 = 1 THEN
      w_fecha_siguiente := w_fecha_siguiente || ' next_day( ' || w_fecha || ', ''DOMINGO'' ),';
    END IF;
    -- quitamos la coma que sobra
    w_fecha_siguiente := substr(w_fecha_siguiente,
                                1,
                                length(w_fecha_siguiente) - 1);
    w_fecha_siguiente := w_fecha_siguiente || ' ), ''yyyy-mm-dd '' ) || ''' ||
                         to_char(pi_fecha,
                                 'hh24:mi:ss') || ''', ''yyyy-mm-dd hh24:mi:ss'' ) FROM DUAL';
    --pr_mostrar( w_fecha_siguiente );
    EXECUTE IMMEDIATE w_fecha_siguiente
      INTO w_fecha_siguiente_date;
    RETURN w_fecha_siguiente_date;
  END fn_planif_semanal;
  ------------------------------------------------------------------------------
  -- Procedimiento de calculo de planificacion
  ------------------------------------------------------------------------------
  PROCEDURE pr_calculo_planif
  (
    pi_reg_proc        gen_prdto%ROWTYPE,
    po_primera_fecha   OUT DATE,
    po_siguiente_fecha OUT VARCHAR2
  ) IS
    w_meses               PLS_INTEGER;
    w_fecha_inicio        DATE;
    w_fecha_siguiente     VARCHAR2(2001);
    w_primera_fecha_texto VARCHAR2(200) := 'to_date( ''' ||
                                           to_char(pi_reg_proc.fec_hora_prim_ejec,
                                                   'yyyy-mm-dd hh24:mi:ss') ||
                                           ''', ''yyyy-mm-dd hh24:mi:ss  '')';
  BEGIN
    ----
    -- Una vez
    ---
    IF pi_reg_proc.ind_plan_unic = 1 THEN
      po_primera_fecha   := pi_reg_proc.fec_hora_prim_ejec;
      po_siguiente_fecha := NULL;
      ----
      -- Semanal
      ---
    ELSIF pi_reg_proc.ind_plan_sema = 1 THEN
      IF pi_reg_proc.fec_hora_prim_ejec < SYSDATE THEN
        po_primera_fecha := fn_planif_semanal(SYSDATE - 1,
                                              pi_reg_proc.ind_dia_sem1,
                                              pi_reg_proc.ind_dia_sem2,
                                              pi_reg_proc.ind_dia_sem3,
                                              pi_reg_proc.ind_dia_sem4,
                                              pi_reg_proc.ind_dia_sem5,
                                              pi_reg_proc.ind_dia_sem6,
                                              pi_reg_proc.ind_dia_sem7);
      ELSE
        po_primera_fecha := fn_planif_semanal(pi_reg_proc.fec_hora_prim_ejec,
                                              pi_reg_proc.ind_dia_sem1,
                                              pi_reg_proc.ind_dia_sem2,
                                              pi_reg_proc.ind_dia_sem3,
                                              pi_reg_proc.ind_dia_sem4,
                                              pi_reg_proc.ind_dia_sem5,
                                              pi_reg_proc.ind_dia_sem6,
                                              pi_reg_proc.ind_dia_sem7);
      END IF;
      po_siguiente_fecha := 'pq_plani.fn_planif_semanal(' || 'sysdate, ' ||
                            to_char(pi_reg_proc.ind_dia_sem1) || ',' ||
                            to_char(pi_reg_proc.ind_dia_sem2) || ',' ||
                            to_char(pi_reg_proc.ind_dia_sem3) || ',' ||
                            to_char(pi_reg_proc.ind_dia_sem4) || ',' ||
                            to_char(pi_reg_proc.ind_dia_sem5) || ',' ||
                            to_char(pi_reg_proc.ind_dia_sem6) || ',' ||
                            to_char(pi_reg_proc.ind_dia_sem7) || ')';
      ----
      -- Mensual
      ---
    ELSIF pi_reg_proc.ind_plan_mens = 1 THEN
      IF pi_reg_proc.fec_hora_prim_ejec < SYSDATE THEN
        w_fecha_inicio := pi_reg_proc.fec_hora_prim_ejec;
        w_meses        := 1;
        WHILE w_fecha_inicio < SYSDATE
        LOOP
          w_fecha_inicio := add_months(pi_reg_proc.fec_hora_prim_ejec,
                                       w_meses);
          w_meses        := w_meses + 1;
        END LOOP;
        po_primera_fecha := w_fecha_inicio;
      ELSE
        po_primera_fecha := pi_reg_proc.fec_hora_prim_ejec;
      END IF;
      po_siguiente_fecha := 'add_months( ' || w_primera_fecha_texto ||
                            ', round( months_between( sysdate, ' || w_primera_fecha_texto ||
                            ') ) + 1 )';
    ELSE
      -- no hay planificacion
      po_primera_fecha   := NULL;
      po_siguiente_fecha := NULL;
    END IF;
  END pr_calculo_planif;
  ------------------------------------------------------------------------------
  -- Procedimiento de borrado de datos antiguos
  ------------------------------------------------------------------------------
  PROCEDURE pr_pdto_borrado_datos IS
    w_sysdate CONSTANT DATE := SYSDATE;
    w_instruccion    VARCHAR2(4000);
    w_cabecera_log   gen_prdto_log_cabec.oid_log_proc%TYPE;
    w_num_lineas_log gen_prdto_log_detal.num_line%TYPE := 0;
    w_reg_tratados   NUMBER(5);
    CURSOR c_tablas IS
      SELECT cod_obje tabla,
             t3.val_para campo,
             to_number(t5.val_para) num_dias
        FROM gen_prdto             t0,
             gen_prdto_objet       t1,
             gen_prdto_param       t2,
             gen_prdto_param_valor t3,
             gen_prdto_param       t4,
             gen_prdto_param_valor t5
       WHERE t0.cod_proc = k_prdto_borrado
         AND t1.prdt_oid_proc = t0.oid_proc
         AND t2.prdt_oid_proc = t0.oid_proc
         AND t4.prdt_oid_proc = t0.oid_proc
         AND t2.cod_para = k_prdto_borrado_crit_borr
         AND t4.cod_para = k_prdto_borrado_num_dias
         AND t3.obje_oid_obje_proc = t1.oid_obje_proc
         AND t5.obje_oid_obje_proc = t1.oid_obje_proc
         AND t3.prpa_oid_para = t2.oid_para
         AND t5.prpa_oid_para = t4.oid_para
       ORDER BY t1.num_orde_proc;
  BEGIN
    w_cabecera_log := fn_log_ejecucion_cabec(k_prdto_borrado);
    FOR reg IN c_tablas
    LOOP
      BEGIN
        w_instruccion := 'delete ' || reg.tabla || ' where ' || reg.campo || ' < to_date(''' ||
                         to_char(w_sysdate,
                                 'yyyy-mm-dd hh24:mi:ss') || ''', ''yyyy-mm-dd hh24:mi:ss'') - ' ||
                         to_char(reg.num_dias);
        pr_mostrar(w_instruccion);
        EXECUTE IMMEDIATE w_instruccion;
        pr_log_ejecucion_linea(w_cabecera_log,
                               w_num_lineas_log,
                               'Tabla ' || reg.tabla || ': borrados ' || to_char(SQL%ROWCOUNT) ||
                               ' registros');
      EXCEPTION
        WHEN OTHERS THEN
          pr_log_ejecucion_linea(w_cabecera_log,
                                 w_num_lineas_log,
                                 'Error borrando tabla ' || reg.tabla || ': ' || SQLERRM);
      END;
    END LOOP;
  END pr_pdto_borrado_datos;

  PROCEDURE executor(str_par IN VARCHAR2) AS
    linea_actual VARCHAR2(32767);
    linea_resto  VARCHAR2(32767);

  BEGIN

    linea_actual := substr(str_par,
                           1,
                           (instrb(str_par,
                                   ';') - 1));
    IF length(substr(str_par,
                     (instrb(str_par,
                             ';')),
                     length(str_par))) = 1 THEN
      EXECUTE IMMEDIATE linea_actual;
    ELSE
      linea_resto := substr(str_par,
                            (instrb(str_par,
                                    ';') + 1),
                            length(str_par));
      LOOP

        EXECUTE IMMEDIATE linea_actual;

        linea_actual := substr(linea_resto,
                               1,
                               (instrb(linea_resto,
                                       ';') - 1));
        IF length(substr(linea_resto,
                         (instrb(linea_resto,
                                 ';')),
                         length(linea_resto))) = 1 THEN
          EXECUTE IMMEDIATE linea_actual;
          EXIT;
        ELSE
          linea_resto := substr(linea_resto,
                                (instrb(linea_resto,
                                        ';') + 1),
                                length(linea_resto));
        END IF;

      END LOOP;
    END IF;
  END executor;
  ------------------------------------------------------------------------------
  -- Procedimiento DTO_OBTENER_DESCUENTOS
  ------------------------------------------------------------------------------
  PROCEDURE dto_obtener_descuentos
  (
    idperi        IN NUMBER,
    idmarca       IN NUMBER,
    idcanal       IN NUMBER,
    idacces       IN NUMBER,
    idsubacces    IN NUMBER,
    idzona        IN NUMBER,
    idpais        IN NUMBER,
    idregion      IN NUMBER,
    idsubgerencia IN NUMBER,
    idcliente     IN NUMBER,
    salida_text   OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT DISTINCT oid_desc,
                      dto_descu.num_desc_corr
        FROM dto_descu,
             cra_perio perio_ini,
             cra_perio perio_fin,
             cra_perio perio_solicitud,
             dto_alcan_dto_admin,
             dto_descu_acces,
             dto_descu_subac,
             v_mae_tipif_clien,
             dto_alcan_dto_clasi_clien dtoclasi,
             (SELECT s.dcto_oid_desc        oiddesc,
                     rel.ticl_oid_tipo_clie tipo,
                     s.sbti_oid_subt_clie   subtipo
                FROM dto_descu_subti_clien s,
                     mae_subti_clien       rel
               WHERE rel.oid_subt_clie = s.sbti_oid_subt_clie
              UNION
              SELECT t.dcto_oid_desc,
                     t.ticl_oid_tipo_clie,
                     NULL
                FROM dto_descu_tipo_clien t
               WHERE t.ticl_oid_tipo_clie NOT IN
                     (SELECT DISTINCT rel.ticl_oid_tipo_clie
                        FROM dto_descu_subti_clien s,
                             mae_subti_clien       rel
                       WHERE rel.oid_subt_clie = s.sbti_oid_subt_clie
                         AND s.dcto_oid_desc = t.dcto_oid_desc)) tipo_subtipo_descuento
       WHERE dto_descu.ind_acti = 1
         AND dto_descu.pais_oid_pais = idpais
         AND dto_descu.marc_oid_marc = idmarca
         AND dto_descu.cana_oid_cana = idcanal
         AND perio_solicitud.oid_peri = idperi
         AND perio_ini.oid_peri = dto_descu.perd_oid_peri
         AND perio_ini.fec_inic <= perio_solicitud.fec_inic
         AND perio_fin.oid_peri(+) = dto_descu.perd_oid_peri_limi_fin
         AND ((perio_fin.fec_fina >= perio_solicitud.fec_fina) OR perio_fin.fec_fina IS NULL)
         AND dto_descu.oid_desc = dto_alcan_dto_admin.dcto_oid_desc(+)
         AND (dto_descu.ind_naci = 1 OR (dto_alcan_dto_admin.zzon_oid_zona = idzona OR
             dto_alcan_dto_admin.zzon_oid_zona IS NULL) AND
             (dto_alcan_dto_admin.zorg_oid_regi = idregion OR
             dto_alcan_dto_admin.zorg_oid_regi IS NULL) AND
             (dto_alcan_dto_admin.zsgv_oid_subg_vent = idsubgerencia))
         AND dto_descu.oid_desc = dto_descu_acces.dcto_oid_desc(+)
         AND (dto_descu_acces.acce_oid_acce IS NULL OR dto_descu_acces.acce_oid_acce = idacces)
         AND dto_descu.oid_desc = dto_descu_subac.dcto_oid_desc(+)
         AND (dto_descu_subac.sbac_oid_sbac IS NULL OR dto_descu_subac.sbac_oid_sbac = idsubacces)
         AND v_mae_tipif_clien.clie_oid_clie = idcliente
         AND dto_descu.oid_desc = tipo_subtipo_descuento.oiddesc(+)
         AND (((SELECT dadc.clie_oid_clie
                  FROM dto_alcan_dto_clien dadc
                 WHERE dadc.dcto_oid_desc = oid_desc
                   AND dadc.clie_oid_clie = idcliente) IS NOT NULL AND
             tipo_subtipo_descuento.oiddesc IS NULL) OR tipo_subtipo_descuento.oiddesc IS NOT NULL)
         AND (tipo_subtipo_descuento.tipo = v_mae_tipif_clien.ticl_oid_tipo_clie OR
             tipo_subtipo_descuento.tipo IS NULL)
         AND (tipo_subtipo_descuento.subtipo = v_mae_tipif_clien.sbti_oid_subt_clie OR
             tipo_subtipo_descuento.subtipo IS NULL)
         AND dto_descu.oid_desc = dtoclasi.dcto_oid_desc(+)
         AND (dtoclasi.clas_oid_clas IS NULL OR
             dtoclasi.clas_oid_clas = v_mae_tipif_clien.clas_oid_clas)
         AND (dtoclasi.tccl_oid_tipo_clas IS NULL OR
             dtoclasi.tccl_oid_tipo_clas = v_mae_tipif_clien.tccl_oid_tipo_clasi)
       ORDER BY num_desc_corr;

    contador INTEGER := 0;
    campo1   NUMBER;
    campo2   NUMBER;
  BEGIN
    salida_text := '|';

    OPEN salida_cur;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || '|';
    END LOOP;

    CLOSE salida_cur;
  END dto_obtener_descuentos;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_DATOS_MENSAJES
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_datos_mensajes
  (
    idsoliccabecera IN NUMBER,
    idpais          IN NUMBER,
    idsubacceso     IN NUMBER,
    idacceso        IN NUMBER,
    idcanal         IN NUMBER,
    codigoventa     IN VARCHAR2,
    salida_text     OUT VARCHAR2
  ) IS
    var_query VARCHAR2(5000);

    TYPE my_curs_type IS REF CURSOR;
    salida_cur my_curs_type;

    campo1  NUMBER;
    campo2  NUMBER;
    campo3  NUMBER;
    campo4  NUMBER;
    campo5  VARCHAR(2);
    campo6  VARCHAR(2);
    campo7  VARCHAR(1);
    campo8  VARCHAR(1);
    campo9  VARCHAR(5);
    campo10 VARCHAR(4000);
    campo11 VARCHAR(18);
    campo12 NUMBER;

  BEGIN
    salida_text := '|';

    var_query := 'SELECT ped_solic_cabec.val_nume_soli, rec_cabec_recla.num_aten,' ||
                 ' rec_opera_recla.num_secu_oper,' ||
                 ' rec_linea_opera_recla.num_unid_recl, rec_opera.cod_oper,' ||
                 ' rec_motiv_recha_desbl.cod_rech_desb,' ||
                 ' rec_estad_opera.cod_esta_oper, rec_preci.cod_prec,' || ' msg_mensa.cod_mens,' ||
                 ' pq_apl_aux.valor_gen_i18n_sicc (1,' || ' rec_opera.oid_oper,' || '''REC_OPERA''' ||
                 ' ) descrip, ' || ' pre_ofert_detal.val_codi_vent,' ||
                 ' rec_tipos_opera.val_info_ebel_noti' || ' FROM ped_solic_cabec,' ||
                 ' rec_solic_opera,' || ' seg_subac,' || ' seg_acces,' || ' seg_canal,' ||
                 ' rec_cabec_recla,' || ' rec_opera_recla,' || ' rec_linea_opera_recla,' ||
                 ' pre_matri_factu,' || ' pre_ofert_detal,' || ' rec_tipos_opera,' ||
                 '  rec_opera,' || '  msg_mensa,' || '  rec_preci,' || '  rec_motiv_recha_desbl,' ||
                 '  rec_estad_opera' || '  WHERE ped_solic_cabec.oid_soli_cabe = ' ||
                 idsoliccabecera ||
                 ' AND rec_solic_opera.opre_oid_oper_recl = rec_opera_recla.oid_oper_recl' ||
                 ' AND ped_solic_cabec.oid_soli_cabe = rec_solic_opera.soca_oid_soli_cabe' ||
                 ' AND ped_solic_cabec.pais_oid_pais = ' || idpais ||
                 ' AND ped_solic_cabec.sbac_oid_sbac = ' || idsubacceso ||
                 ' AND ped_solic_cabec.sbac_oid_sbac = seg_subac.oid_sbac' ||
                 ' AND seg_subac.acce_oid_acce = seg_acces.oid_acce' || ' AND seg_acces.oid_acce =' ||
                 idacceso || ' AND seg_acces.cana_oid_cana = ' || idcanal ||
                 ' AND seg_acces.cana_oid_cana = seg_canal.oid_cana' ||
                 ' AND rec_cabec_recla.oid_cabe_recl = rec_opera_recla.care_oid_cabe_recl' ||
                 ' AND rec_solic_opera.tspa_oid_tipo_soli_pais = rec_linea_opera_recla.tspa_oid_tipo_soli_pais' ||
                 ' AND rec_opera_recla.oid_oper_recl = rec_linea_opera_recla.opre_oid_oper_recl' ||
                 ' AND rec_linea_opera_recla.mafa_oid_matr_fact = pre_matri_factu.oid_matr_fact' ||
                 ' AND pre_matri_factu.ofde_oid_deta_ofer = pre_ofert_detal.oid_deta_ofer' ||
                 ' AND pre_ofert_detal.val_codi_vent IN (' || codigoventa || ')' ||
                 ' AND rec_opera_recla.tiop_oid_tipo_oper = rec_tipos_opera.oid_tipo_oper' ||
                 ' AND rec_tipos_opera.rope_oid_oper = rec_opera.oid_oper' ||
                 ' AND rec_opera.mens_oid_mens = msg_mensa.oid_mens' ||
                 ' AND rec_opera.peci_oid_peci = rec_preci.oid_prec' ||
                 ' AND rec_opera_recla.mrdb_oid_moti_rech_desb = rec_motiv_recha_desbl.oid_moti_rech_desb(+)' ||
                 ' AND rec_opera_recla.esop_oid_esta_oper = rec_estad_opera.oid_esta_oper';

    OPEN salida_cur FOR var_query;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7,
             campo8,
             campo9,
             campo10,
             campo11,
             campo12;

      EXIT WHEN salida_cur%NOTFOUND;

      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || ',' ||
                     nvl(to_char(campo8),
                         'null') || ',' || nvl(to_char(campo9),
                                               'null') || ',' ||
                     nvl(to_char(campo10),
                         'null') || ',' || nvl(to_char(campo11),
                                               'null') || ',' ||
                     nvl(to_char(campo12),
                         'null') || '|';
    END LOOP;

    CLOSE salida_cur;
  END ped_obtener_datos_mensajes;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_DAT_MEN_PREM
  ------------------------------------------------------------------------------

  PROCEDURE ped_obtener_dat_men_prem
  (
    idsoliccabecera IN NUMBER,
    idpais          IN NUMBER,
    idsubacceso     IN NUMBER,
    idacceso        IN NUMBER,
    idcanal         IN NUMBER,
    idproducto      IN VARCHAR2,
    salida_text     OUT VARCHAR2
  ) IS
    var_query VARCHAR2(5000);

    TYPE my_curs_type IS REF CURSOR;
    salida_cur my_curs_type;

    campo1  NUMBER;
    campo2  NUMBER;
    campo3  NUMBER;
    campo4  NUMBER;
    campo5  VARCHAR(2);
    campo6  VARCHAR(2);
    campo7  VARCHAR(1);
    campo8  VARCHAR(1);
    campo9  VARCHAR(5);
    campo10 VARCHAR(255);
    campo11 VARCHAR(18);
    campo12 NUMBER;

  BEGIN
    salida_text := '|';

    var_query := 'SELECT ped_solic_cabec.val_nume_soli, rec_cabec_recla.num_aten,' ||
                 ' rec_opera_recla.num_secu_oper,' ||
                 ' rec_linea_opera_recla.num_unid_recl, rec_opera.cod_oper,' ||
                 ' rec_motiv_recha_desbl.cod_rech_desb,' ||
                 ' rec_estad_opera.cod_esta_oper, rec_preci.cod_prec,' || ' msg_mensa.cod_mens,' ||
                 ' pq_apl_aux.valor_gen_i18n_sicc (1,' || ' rec_opera.oid_oper,' || '''REC_OPERA''' ||
                 ' ) descrip, ' || ' rec_linea_opera_recla.prod_oid_prod,' ||
                 ' rec_tipos_opera.val_info_ebel_noti' || ' FROM ped_solic_cabec,' ||
                 ' rec_solic_opera,' || ' seg_subac,' || ' seg_acces,' || ' seg_canal,' ||
                 ' rec_cabec_recla,' || ' rec_opera_recla,' || ' rec_linea_opera_recla,' ||
                 ' rec_tipos_opera,' || ' rec_opera,' || ' msg_mensa,' || ' rec_preci,' ||
                 ' rec_motiv_recha_desbl,' || ' rec_estad_opera' ||
                 ' WHERE ped_solic_cabec.oid_soli_cabe = ' || idsoliccabecera ||
                 ' AND rec_solic_opera.opre_oid_oper_recl = rec_opera_recla.oid_oper_recl' ||
                 ' AND ped_solic_cabec.oid_soli_cabe = rec_solic_opera.soca_oid_soli_cabe' ||
                 ' AND ped_solic_cabec.pais_oid_pais = ' || idpais ||
                 ' AND ped_solic_cabec.sbac_oid_sbac = ' || idsubacceso ||
                 ' AND ped_solic_cabec.sbac_oid_sbac = seg_subac.oid_sbac' ||
                 ' AND seg_subac.acce_oid_acce = seg_acces.oid_acce' || ' AND seg_acces.oid_acce =' ||
                 idacceso || ' AND seg_acces.cana_oid_cana = ' || idcanal ||
                 ' AND seg_acces.cana_oid_cana = seg_canal.oid_cana' ||
                 ' AND rec_cabec_recla.oid_cabe_recl = rec_opera_recla.care_oid_cabe_recl' ||
                 ' AND rec_solic_opera.tspa_oid_tipo_soli_pais = rec_linea_opera_recla.tspa_oid_tipo_soli_pais' ||
                 ' AND rec_opera_recla.oid_oper_recl = rec_linea_opera_recla.opre_oid_oper_recl' ||
                 ' AND rec_opera_recla.tiop_oid_tipo_oper = rec_tipos_opera.oid_tipo_oper' ||
                 ' AND rec_tipos_opera.rope_oid_oper = rec_opera.oid_oper' ||
                 ' AND rec_opera.mens_oid_mens = msg_mensa.oid_mens' ||
                 ' AND rec_opera.peci_oid_peci = rec_preci.oid_prec' ||
                 ' AND rec_linea_opera_recla.prod_oid_prod IN (' || idproducto || ')' ||
                 ' AND rec_opera_recla.mrdb_oid_moti_rech_desb = rec_motiv_recha_desbl.oid_moti_rech_desb(+)' ||
                 ' AND rec_opera_recla.esop_oid_esta_oper = rec_estad_opera.oid_esta_oper';

    OPEN salida_cur FOR var_query;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7,
             campo8,
             campo9,
             campo10,
             campo11,
             campo12;

      EXIT WHEN salida_cur%NOTFOUND;

      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || ',' ||
                     nvl(to_char(campo8),
                         'null') || ',' || nvl(to_char(campo9),
                                               'null') || ',' ||
                     nvl(to_char(campo10),
                         'null') || ',' || nvl(to_char(campo11),
                                               'null') || ',' ||
                     nvl(to_char(campo12),
                         'null') || '|';
    END LOOP;

    CLOSE salida_cur;
  END ped_obtener_dat_men_prem;

  ------------------------------------------------------------------------------
  -- Procedimiento MAV_OBTENER_DETALLES_MAV
  ------------------------------------------------------------------------------

  PROCEDURE mav_obtener_detalles_mav
  (
    idperi      IN NUMBER,
    idcliente   IN NUMBER,
    idestado    IN NUMBER,
    salida_text OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT DISTINCT acti_oid_acti,
                      val_base_esti_dest,
                      val_camp_eval,
                      ocat_oid_cata,
                      civi_oid_cicl_vida,
                      mav_detal_mav.clas_oid_clas,
                      val_crit,
                      mcur_oid_curs,
                      val_edad_desd,
                      val_edad_hast,
                      ind_envi_mens,
                      aest_oid_esta_acti,
                      tepr_oid_tipo_esta_proc,
                      val_fact_corr,
                      fcob_oid_form_cobr,
                      licl_oid_list_clie,
                      mapr_oid_marc_prod,
                      mens_oid_mens,
                      val_mont,
                      nego_oid_nego,
                      num_aniv,
                      val_obse,
                      oid_deta_mav,
                      perd_oid_peri,
                      perd_oid_peri_curs,
                      mav_detal_mav.perd_oid_peri_fina,
                      perd_oid_peri_fina_mont,
                      perd_oid_peri_inic_mont,
                      val_prec,
                      val_prec_cont,
                      val_prec_stnd,
                      prdn_oid_proc,
                      prod_oid_prod,
                      scas_oid_subc,
                      tccl_oid_tipo_clas,
                      mav_detal_mav.ticl_oid_tipo_clie,
                      tofe_oid_tipo_ofer,
                      num_unid_esti,
                      num_unid_clie,
                      num_unid_tota,
                      num_unid_tota_esti,
                      uneg_oid_unid_nego,
                      atde_oid_acti_tipo_desp,
                      cras_oid_crit_asig,
                      pentrada.cana_oid_cana,
                      pentrada.marc_oid_marc,
                      pentrada.pais_oid_pais,
                      pentrada.val_nomb_peri,
                      (SELECT sm.des_marc
                         FROM seg_marca sm
                        WHERE pentrada.marc_oid_marc = sm.oid_marc) AS des_marc,
                      pq_apl_aux.valor_gen_i18n_sicc(1,
                                                     pentrada.cana_oid_cana,
                                                     'SEG_CANAL') des_canal,
                      pq_apl_aux.valor_gen_i18n_sicc(1,
                                                     mav_detal_mav.acti_oid_acti,
                                                     'MAV_ACTIV') des_activ,
                      pq_apl_aux.valor_gen_i18n_sicc(1,
                                                     mav_detal_mav.ticl_oid_tipo_clie,
                                                     'MAE_TIPO_CLIEN') des_tipo_cliente,
                      fopa_oid_form_pago
        FROM mav_detal_mav,
             mav_subcr_asign           msa,
             cra_perio                 pentrada,
             v_mae_tipif_clien,
             cra_perio                 pini,
             cra_perio                 pfin,
             mav_subti_clien_detal,
             mae_clien_unida_admin,
             zon_terri_admin,
             zon_secci,
             zon_zona,
             zon_regio,
             mav_detal_mav_unida_admin
       WHERE mav_detal_mav.scas_oid_subc = msa.oid_subc
         AND mav_detal_mav.perd_oid_peri = pentrada.oid_peri
         AND mav_detal_mav.ticl_oid_tipo_clie = v_mae_tipif_clien.ticl_oid_tipo_clie
         AND v_mae_tipif_clien.clie_oid_clie = idcliente
         AND v_mae_tipif_clien.clas_oid_clas =
             nvl(mav_detal_mav.clas_oid_clas,
                 v_mae_tipif_clien.clas_oid_clas)
         AND v_mae_tipif_clien.tccl_oid_tipo_clasi =
             nvl(mav_detal_mav.tccl_oid_tipo_clas,
                 v_mae_tipif_clien.tccl_oid_tipo_clasi)
         AND v_mae_tipif_clien.sbti_oid_subt_clie =
             nvl(mav_subti_clien_detal.sbti_oid_subt_clie,
                 v_mae_tipif_clien.sbti_oid_subt_clie)
         AND mav_detal_mav.oid_deta_mav = mav_subti_clien_detal.denv_oid_deta_mav(+)
         AND mav_detal_mav.perd_oid_peri = pini.oid_peri
         AND mav_detal_mav.perd_oid_peri_fina = pfin.oid_peri(+)
         AND pentrada.oid_peri = idperi
         AND ((mav_detal_mav.perd_oid_peri_fina IS NULL AND
             mav_detal_mav.perd_oid_peri = pentrada.oid_peri) OR
             (mav_detal_mav.perd_oid_peri_fina IS NOT NULL AND pini.fec_inic <= pentrada.fec_inic AND
             pentrada.fec_fina <= pfin.fec_fina))
         AND mav_detal_mav.tepr_oid_tipo_esta_proc = idestado
         AND mav_detal_mav.oid_deta_mav NOT IN
             (SELECT denv_oid_deta_mav FROM mav_envio WHERE clie_oid_clie = idcliente)
         AND v_mae_tipif_clien.clie_oid_clie = mae_clien_unida_admin.clie_oid_clie
         AND zon_terri_admin.oid_terr_admi = mae_clien_unida_admin.ztad_oid_terr_admi
         AND zon_terri_admin.zscc_oid_secc = zon_secci.oid_secc
         AND zon_zona.oid_zona = zon_secci.zzon_oid_zona
         AND zon_zona.zorg_oid_regi = zon_regio.oid_regi
         AND mae_clien_unida_admin.perd_oid_peri_fin IS NULL
         AND zon_zona.oid_zona = nvl(mav_detal_mav_unida_admin.zzon_oid_zona,
                                     zon_zona.oid_zona)
         AND zon_regio.oid_regi = nvl(mav_detal_mav_unida_admin.zorg_oid_regi,
                                      zon_regio.oid_regi)
         AND mav_detal_mav_unida_admin.denv_oid_deta_mav(+) = mav_detal_mav.oid_deta_mav
         AND (mav_detal_mav.aest_oid_esta_acti IS NULL OR
             (SELECT COUNT(*)
                 FROM mae_clien_datos_adici,
                      mae_estat_clien,
                      mav_estad_mav_mae,
                      mav_estad_mav,
                      mav_activ_estad
                WHERE mae_clien_datos_adici.clie_oid_clie = v_mae_tipif_clien.clie_oid_clie
                  AND mae_clien_datos_adici.esta_oid_esta_clie = mae_estat_clien.oid_esta_clie
                  AND mae_estat_clien.oid_esta_clie = mav_estad_mav_mae.esta_oid_esta_clie
                  AND mav_estad_mav_mae.emav_oid_esta_mav = mav_estad_mav.oid_esta_mav
                  AND mav_estad_mav.oid_esta_mav = mav_activ_estad.emav_oid_esta_mav
                  AND mav_activ_estad.oid_esta_acti = mav_detal_mav.aest_oid_esta_acti) > 0);

    campo1  NUMBER;
    campo2  NUMBER;
    campo3  NUMBER;
    campo4  NUMBER;
    campo5  NUMBER;
    campo6  NUMBER;
    campo7  VARCHAR(20);
    campo8  NUMBER;
    campo9  NUMBER;
    campo10 NUMBER;
    campo11 NUMBER;
    campo12 NUMBER;
    campo13 NUMBER;
    campo14 NUMBER;
    campo15 NUMBER;
    campo16 NUMBER;
    campo17 NUMBER;
    campo18 NUMBER;
    campo19 NUMBER;
    campo20 NUMBER;
    campo21 NUMBER;
    campo22 VARCHAR(100);
    campo23 NUMBER;
    campo24 NUMBER;
    campo25 NUMBER;
    campo26 NUMBER;
    campo27 NUMBER;
    campo28 NUMBER;
    campo29 NUMBER;
    campo30 NUMBER;
    campo31 NUMBER;
    campo32 NUMBER;
    campo33 NUMBER;
    campo34 NUMBER;
    campo35 NUMBER;
    campo36 NUMBER;
    campo37 NUMBER;
    campo38 NUMBER;
    campo39 NUMBER;
    campo40 NUMBER;
    campo41 NUMBER;
    campo42 NUMBER;
    campo43 NUMBER;
    campo44 NUMBER;
    campo45 NUMBER;
    campo46 NUMBER;
    campo47 NUMBER;
    campo48 VARCHAR(100);
    campo49 VARCHAR(100);
    campo50 VARCHAR(100);
    campo51 VARCHAR(100);
    campo52 VARCHAR(100);
    campo53 NUMBER;

  BEGIN

    salida_text := '|';

    OPEN salida_cur;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7,
             campo8,
             campo9,
             campo10,
             campo11,
             campo12,
             campo13,
             campo14,
             campo15,
             campo16,
             campo17,
             campo18,
             campo19,
             campo20,
             campo21,
             campo22,
             campo23,
             campo24,
             campo25,
             campo26,
             campo27,
             campo28,
             campo29,
             campo30,
             campo31,
             campo32,
             campo33,
             campo34,
             campo35,
             campo36,
             campo37,
             campo38,
             campo39,
             campo40,
             campo41,
             campo42,
             campo43,
             campo44,
             campo45,
             campo46,
             campo47,
             campo48,
             campo49,
             campo50,
             campo51,
             campo52,
             campo53;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || ',' ||
                     nvl(to_char(campo8),
                         'null') || ',' || nvl(to_char(campo9),
                                               'null') || ',' ||
                     nvl(to_char(campo10),
                         'null') || ',' || nvl(to_char(campo11),
                                               'null') || ',' ||
                     nvl(to_char(campo12),
                         'null') || ',' || nvl(to_char(campo13),
                                               'null') || ',' ||
                     nvl(to_char(campo14),
                         'null') || ',' || nvl(to_char(campo15),
                                               'null') || ',' ||
                     nvl(to_char(campo16),
                         'null') || ',' || nvl(to_char(campo17),
                                               'null') || ',' ||
                     nvl(to_char(campo18),
                         'null') || ',' || nvl(to_char(campo19),
                                               'null') || ',' ||
                     nvl(to_char(campo20),
                         'null') || ',' || nvl(to_char(campo21),
                                               'null') || ',' ||
                     nvl(to_char(campo22),
                         'null') || ',' || nvl(to_char(campo23),
                                               'null') || ',' ||
                     nvl(to_char(campo24),
                         'null') || ',' || nvl(to_char(campo25),
                                               'null') || ',' ||
                     nvl(to_char(campo26),
                         'null') || ',' || nvl(to_char(campo27),
                                               'null') || ',' ||
                     nvl(to_char(campo28),
                         'null') || ',' || nvl(to_char(campo29),
                                               'null') || ',' ||
                     nvl(to_char(campo30),
                         'null') || ',' || nvl(to_char(campo31),
                                               'null') || ',' ||
                     nvl(to_char(campo32),
                         'null') || ',' || nvl(to_char(campo33),
                                               'null') || ',' ||
                     nvl(to_char(campo34),
                         'null') || ',' || nvl(to_char(campo35),
                                               'null') || ',' ||
                     nvl(to_char(campo36),
                         'null') || ',' || nvl(to_char(campo37),
                                               'null') || ',' ||
                     nvl(to_char(campo38),
                         'null') || ',' || nvl(to_char(campo39),
                                               'null') || ',' ||
                     nvl(to_char(campo40),
                         'null') || ',' || nvl(to_char(campo41),
                                               'null') || ',' ||
                     nvl(to_char(campo42),
                         'null') || ',' || nvl(to_char(campo43),
                                               'null') || ',' ||
                     nvl(to_char(campo44),
                         'null') || ',' || nvl(to_char(campo45),
                                               'null') || ',' ||
                     nvl(to_char(campo46),
                         'null') || ',' || nvl(to_char(campo47),
                                               'null') || ',' ||
                     nvl(to_char(campo48),
                         'null') || ',' || nvl(to_char(campo49),
                                               'null') || ',' ||
                     nvl(to_char(campo50),
                         'null') || ',' || nvl(to_char(campo51),
                                               'null') || ',' ||
                     nvl(to_char(campo52),
                         'null') || ',' || nvl(to_char(campo53),
                                               'null') || '|';
    END LOOP;

    CLOSE salida_cur;

  END;
  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_LIMITE_VENTA
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_limite_venta
  (
    idperi              IN NUMBER,
    idcliente           IN NUMBER,
    idcabecerasolicitud IN NUMBER,
    idestado            IN NUMBER,
    ididioma            IN NUMBER,
    salida_text         OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT po.oid_soli_posi,
             po.num_unid_dema,
             po.num_unid_por_aten,
             cv.val_codi_vent,
             cl.cod_clie,
             po.ofde_oid_deta_ofer,
             MAX(val_limi_ctrl_vent) val_limi_ctrl_vent,
             pq_apl_aux.valor_gen_i18n_sicc(ididioma,
                                            po.prod_oid_prod,
                                            'MAE_PRODU')
        FROM ped_solic_posic po,
             ped_solic_cabec ca,
             zon_zona zo,
             mae_clien cl,
             pre_ofert_detal cv,
             (SELECT *
                FROM ped_gesti_stock   st,
                     v_mae_tipif_clien tp
               WHERE ind_ulti_noti = 1
                 AND st.perd_oid_peri = idperi
                 AND val_limi_ctrl_vent IS NOT NULL
                 AND tp.clie_oid_clie = idcliente
                 AND (st.ticl_oid_tipo_clie is null or st.ticl_oid_tipo_clie = tp.ticl_oid_tipo_clie)
                 AND (st.sbti_oid_subt_clie IS NULL OR st.sbti_oid_subt_clie = tp.sbti_oid_subt_clie)
                 AND (st.tccl_oid_tipo_clas IS NULL OR
                     st.tccl_oid_tipo_clas = tp.tccl_oid_tipo_clasi)
                 AND (st.clas_oid_clas IS NULL OR st.clas_oid_clas = tp.clas_oid_clas)) st
       WHERE po.soca_oid_soli_cabe = idcabecerasolicitud
         AND po.soca_oid_soli_cabe = ca.oid_soli_cabe
         AND ca.zzon_oid_zona = zo.oid_zona
         AND cl.oid_clie = ca.clie_oid_clie
         AND po.espo_oid_esta_posi <> idestado
         AND cv.oid_deta_ofer = po.ofde_oid_deta_ofer
         AND st.ofde_oid_deta_ofer = po.ofde_oid_deta_ofer
         AND (st.zorg_oid_regi IS NULL OR st.zorg_oid_regi = zo.zorg_oid_regi)
         AND (st.zzon_oid_zona IS NULL OR st.zzon_oid_zona = ca.zzon_oid_zona)
         AND po.ind_limi_vent IS NULL
       GROUP BY po.oid_soli_posi,
                po.num_unid_dema,
                po.num_unid_por_aten,
                cv.val_codi_vent,
                cl.cod_clie,
                po.ofde_oid_deta_ofer,
                pq_apl_aux.valor_gen_i18n_sicc(ididioma,
                                               po.prod_oid_prod,
                                               'MAE_PRODU');
    campo1 NUMBER;
    campo2 NUMBER;
    campo3 NUMBER;
    campo4 VARCHAR(18);
    campo5 VARCHAR(15);
    campo6 NUMBER;
    campo7 NUMBER;
    campo8 VARCHAR(100);
  BEGIN
    salida_text := '|';
    OPEN salida_cur;
    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7,
             campo8;
      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || ',' ||
                     nvl(to_char(campo8),
                         'null') || '|';
    END LOOP;
    CLOSE salida_cur;
  END;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_INDICADORES_FLETE
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_indicadores_flete
  (
    cliente      IN NUMBER,
    oidsolicitud IN NUMBER,
    salida_text  OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT ca.oid_soli_cabe,
             ca.ind_exen_flet,
             ts.ind_suje_flet,
             MAX(exp.mae_ind_exen_flet) mae_ind_exen_flet
        FROM ped_tipo_solic_pais ts,
             ped_solic_cabec ca,
             (SELECT ex.pais_oid_pais,
                     ex.ind_exen_flet mae_ind_exen_flet
                FROM mae_exenc_flete   ex,
                     v_mae_tipif_clien tp
               WHERE tp.clie_oid_clie = cliente
                 AND ex.ticl_oid_tipo_clie = tp.ticl_oid_tipo_clie
                 AND (ex.sbti_oid_subt_clie IS NULL OR ex.sbti_oid_subt_clie = tp.sbti_oid_subt_clie)
                 AND (ex.tccl_oid_tipo_clas IS NULL OR
                     ex.tccl_oid_tipo_clas = tp.tccl_oid_tipo_clasi)
                 AND (ex.clas_oid_clas IS NULL OR ex.clas_oid_clas = tp.clas_oid_clas)) exp
       WHERE ca.oid_soli_cabe = oidsolicitud
         AND ca.tspa_oid_tipo_soli_pais = ts.oid_tipo_soli_pais
         AND exp.pais_oid_pais(+) = ca.pais_oid_pais
       GROUP BY ca.oid_soli_cabe,
                ca.ind_exen_flet,
                ts.ind_suje_flet;

    campo1 NUMBER;
    campo2 NUMBER;
    campo3 NUMBER;
    campo4 NUMBER;

  BEGIN
    salida_text := '|';
    OPEN salida_cur;
    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || '|';
    END LOOP;

    CLOSE salida_cur;

  END ped_obtener_indicadores_flete;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_POSIC_RECUPERACION
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_posic_recuperacion
  (
    oidsolicitud IN NUMBER,
    recuperacion IN NUMBER,
    salida_text  OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT b.oid_soli_posi      AS oidposicion,
             h.ofde_oid_deta_ofer AS detalleofertaanterior
        FROM ped_solic_posic b,
             pre_ofert_detal c,
             pre_ofert       d,
             pre_estra       e,
             pre_matri_factu f,
             pre_matri_recup g,
             pre_matri_factu h,
             pre_tipo_produ  t
       WHERE b.soca_oid_soli_cabe = oidsolicitud
         AND c.oid_deta_ofer = b.ofde_oid_deta_ofer
         AND d.oid_ofer = c.ofer_oid_ofer
         AND e.oid_estr = d.coes_oid_estr
         AND t.cod_tipo_prod = recuperacion
         AND t.oid_tipo_prod = e.tipr_oid_tipo_prod
         AND f.ofde_oid_deta_ofer = b.ofde_oid_deta_ofer
         AND f.mfca_oid_cabe = d.mfca_oid_cabe
         AND g.mafa_oid_cod_ppal = f.oid_matr_fact
         AND h.oid_matr_fact = g.mafa_oid_cod_recu;

    campo1 NUMBER;
    campo2 NUMBER;

  BEGIN
    salida_text := '|';
    OPEN salida_cur;
    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2;
      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || '|';
    END LOOP;

    CLOSE salida_cur;

  END ped_obtener_posic_recuperacion;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_POSIC_SOLICITUD
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_posic_solicitud
  (
    oidsolicitud IN NUMBER,
    salida_text  OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT pos.oid_soli_posi,
             pos.val_codi_vent,
             pos.num_unid_dema,
             pos.num_unid_aten,
             pos.num_unid_por_aten,
             pos.num_unid_dema_real,
             pos.ofde_oid_deta_ofer,
             pos.tpos_oid_tipo_posi,
             pos.stpo_oid_subt_posi,
             det.ofer_oid_ofer,
             ofer.num_ofer,
             grup.num_grup,
             est.ties_oid_tipo_estr,
             indcuadtip.indc_oid_indi_cuad,
             grup.cod_fact_cuad,
             grup.ind_cndt,
             det.num_posi_rank,
             det.num_pagi_cata,
             pos.val_prec_cata_unit_loca,
             pos.cod_posi,
             det.val_fact_repe,
             det.prod_oid_prod,
             prod.lipr_oid_line_prod,
             det.ocat_oid_catal
        FROM ped_solic_posic            pos,
             pre_ofert_detal            det,
             pre_ofert                  ofer,
             pre_grupo_ofert            grup,
             pre_estra                  est,
             pre_indic_cuadr_tipo_estra indcuadtip,
             mae_produ                  prod
       WHERE soca_oid_soli_cabe = oidsolicitud
         AND pos.ofde_oid_deta_ofer = det.oid_deta_ofer
         AND det.ofer_oid_ofer = ofer.oid_ofer
         AND det.gofe_oid_grup_ofer = grup.oid_grup_ofer(+)
         AND ofer.coes_oid_estr = est.oid_estr
         AND grup.cues_oid_ind_cuad_tipo_estr = indcuadtip.oid_ind_cuad_tipo_estr(+)
         AND prod.oid_prod = det.prod_oid_prod;

    campo1  NUMBER;
    campo2  VARCHAR(18);
    campo3  NUMBER;
    campo4  NUMBER;
    campo5  NUMBER;
    campo6  NUMBER;
    campo7  NUMBER;
    campo8  NUMBER;
    campo9  NUMBER;
    campo10 NUMBER;
    campo11 NUMBER;
    campo12 NUMBER;
    campo13 NUMBER;
    campo14 NUMBER;
    campo15 NUMBER;
    campo16 NUMBER;
    campo17 NUMBER;
    campo18 NUMBER;
    campo19 NUMBER;
    campo20 NUMBER;
    campo21 NUMBER;
    campo22 NUMBER;
    campo23 NUMBER;
    campo24 NUMBER;

  BEGIN
    salida_text := '|';
    OPEN salida_cur;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7,
             campo8,
             campo9,
             campo10,
             campo11,
             campo12,
             campo13,
             campo14,
             campo15,
             campo16,
             campo17,
             campo18,
             campo19,
             campo20,
             campo21,
             campo22,
             campo23,
             campo24;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || ',' ||
                     nvl(to_char(campo8),
                         'null') || ',' || nvl(to_char(campo9),
                                               'null') || ',' ||
                     nvl(to_char(campo10),
                         'null') || ',' || nvl(to_char(campo11),
                                               'null') || ',' ||
                     nvl(to_char(campo12),
                         'null') || ',' || nvl(to_char(campo13),
                                               'null') || ',' ||
                     nvl(to_char(campo14),
                         'null') || ',' || nvl(to_char(campo15),
                                               'null') || ',' ||
                     nvl(to_char(campo16),
                         'null') || ',' || nvl(to_char(campo17),
                                               'null') || ',' ||
                     nvl(to_char(campo18),
                         'null') || ',' || nvl(to_char(campo19),
                                               'null') || ',' ||
                     nvl(to_char(campo20),
                         'null') || ',' || nvl(to_char(campo21),
                                               'null') || ',' ||
                     nvl(to_char(campo22),
                         'null') || ',' || nvl(to_char(campo23),
                                               'null') || ',' ||
                     nvl(to_char(campo24),
                         'null') || '|';
    END LOOP;

    CLOSE salida_cur;

  END ped_obtener_posic_solicitud;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_PREC_POSIC_MATRIZ
  ------------------------------------------------------------------------------
  PROCEDURE ped_obtener_prec_posic_matriz
  (
    oidsolicitud            IN NUMBER,
    estado_posicion_anulado IN NUMBER,
    salida_text             OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT p.oid_soli_posi,
             o.oid_ofer,
             d.imp_prec_cata,
             d.imp_prec_posi,
             o.oid_ofer oferta,
             COUNT(ve.oid_vent_excl) venta_excl,
             d.val_fact_repe
        FROM ped_solic_posic p,
             pre_ofert       o,
             pre_ofert_detal d,
             pre_venta_exclu ve
       WHERE p.soca_oid_soli_cabe = oidsolicitud
         AND p.espo_oid_esta_posi <> estado_posicion_anulado
         AND ((p.val_prec_cata_unit_loca = 0) OR (p.val_prec_cata_unit_loca IS NULL))
         AND ((p.val_prec_cont_unit_loca = 0) OR (p.val_prec_cont_unit_loca IS NULL))
         AND p.val_codi_vent IS NOT NULL
         AND p.ofde_oid_deta_ofer = d.oid_deta_ofer
         AND d.ofer_oid_ofer = o.oid_ofer
         AND ve.ofer_oid_ofer(+) = o.oid_ofer
       GROUP BY p.oid_soli_posi,
                oid_deta_ofer,
                o.oid_ofer,
                d.imp_prec_cata,
                d.imp_prec_posi,
                d.val_fact_repe;

    campo1 NUMBER;
    campo2 NUMBER;
    campo3 NUMBER;
    campo4 NUMBER;
    campo5 NUMBER;
    campo6 NUMBER;
    campo7 NUMBER;

  BEGIN
    salida_text := '|';
    OPEN salida_cur;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || '|';
    END LOOP;

    CLOSE salida_cur;

  END ped_obtener_prec_posic_matriz;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_OBTENER_VAL_MONTO_MINIMO
  ------------------------------------------------------------------------------

  PROCEDURE ped_obtener_val_monto_minimo
  (
    oidsolicitud IN NUMBER,
    salida_text  OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT mm.oid_mont_mini,
             mm.val_niv1,
             mm.val_niv2,
             mm.val_niv3,
             mm.val_reca,
             mm.val_mont_mini_nomi,
             decode(nvl((SELECT ind_excl_mont_mini FROM seg_pais WHERE oid_pais = ca.pais_oid_pais),
                        0),
                    0,
                    ca.val_prec_cata_tota_loca,
                    (SELECT SUM(val_prec_cata_tota_loca)
                       FROM ped_solic_posic,
                            mae_produ,
                            pre_ofert_detal
                      WHERE soca_oid_soli_cabe = ca.oid_soli_cabe
                        AND ped_solic_posic.prod_oid_prod = oid_prod
                        AND ofde_oid_deta_ofer = oid_deta_ofer
                        AND NOT EXISTS
                      (SELECT *
                               FROM ped_exclu_monto_minim
                              WHERE oid_tipo_ofer = pre_ofert_detal.tofe_oid_tipo_ofer
                                AND ca.perd_oid_peri >= oid_camp_inic
                                AND ca.perd_oid_peri <= nvl(oid_camp_fina,
                                                            ca.perd_oid_peri))
                        AND NOT EXISTS
                      (SELECT *
                               FROM ped_exclu_monto_minim
                              WHERE oid_marc = mae_produ.mapr_oid_marc_prod
                                AND ca.perd_oid_peri >= oid_camp_inic
                                AND ca.perd_oid_peri <= nvl(oid_camp_fina,
                                                            ca.perd_oid_peri)))) val_prec_cata_tota_loca
        FROM ped_monto_minim   mm,
             ped_solic_cabec   ca,
             v_mae_tipif_clien tp,
             zon_zona          zon
       WHERE ca.oid_soli_cabe = oidsolicitud
         AND ca.zzon_oid_zona = zon.oid_zona
         AND mm.tspa_oid_tipo_soli_pais = ca.tspa_oid_tipo_soli_pais
         AND mm.ticl_oid_tipo_clie = tp.ticl_oid_tipo_clie
         AND ca.clie_oid_clie = tp.clie_oid_clie
         AND (mm.sbti_oid_subt_clie IS NULL OR mm.sbti_oid_subt_clie = tp.sbti_oid_subt_clie)
         AND (mm.tccl_oid_tipo_clas IS NULL OR mm.tccl_oid_tipo_clas = tp.tccl_oid_tipo_clasi)
         AND (mm.clas_oid_clas IS NULL OR mm.clas_oid_clas = tp.clas_oid_clas)
         AND (mm.zzon_oid_zona IS NULL OR mm.zzon_oid_zona = zon.oid_zona)
         AND (mm.zorg_oid_regi IS NULL OR mm.zorg_oid_regi = zon.zorg_oid_regi);

    campo1 NUMBER;
    campo2 NUMBER;
    campo3 NUMBER;
    campo4 NUMBER;
    campo5 NUMBER;
    campo6 NUMBER;
    campo7 NUMBER;
  BEGIN
    salida_text := '|';

    OPEN salida_cur;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || '|';
    END LOOP;

    CLOSE salida_cur;
  END ped_obtener_val_monto_minimo;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_REALIZAR_CALCULO
  ------------------------------------------------------------------------------
  PROCEDURE ped_realizar_calculo
  (
    oidsolicitud            IN NUMBER,
    estado_posicion_anulado IN NUMBER,
    numerodecimales         IN NUMBER,
    salida_text             OUT VARCHAR2
  ) IS
    CURSOR salida_cur IS
      SELECT trunc((SUM(val_prec_cata_unit_loca * num_unid_por_aten)),
                   numerodecimales) precio_catalogo,
             trunc((SUM(val_prec_cata_unit_loca * num_unid_dema_real)),
                   numerodecimales) precio_demanda_real,
             trunc((SUM(val_prec_cont_unit_loca * num_unid_por_aten)),
                   numerodecimales) precio_contable,
             SUM(num_unid_por_aten) unidades_atender,
             SUM(num_unid_dema_real) unidades_demanda_real,
             pts.ind_anul,
             pts.ind_devo,
             psc.perd_oid_peri,
             psc.clie_oid_clie,
             psc.ind_oc,
             trunc((SUM(val_impo_desc_unit_loca * num_unid_por_aten)),
                   numerodecimales) descuento
        FROM ped_solic_posic     psp,
             ped_solic_cabec     psc,
             ped_tipo_solic_pais ptsp,
             ped_tipo_solic      pts
       WHERE psc.oid_soli_cabe = oidsolicitud
         AND psp.soca_oid_soli_cabe = psc.oid_soli_cabe
         AND psp.espo_oid_esta_posi != estado_posicion_anulado
         AND tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
         AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
       GROUP BY pts.ind_anul,
                pts.ind_devo,
                psc.perd_oid_peri,
                psc.clie_oid_clie,
                psc.ind_oc;

    campo1  NUMBER;
    campo2  NUMBER;
    campo3  NUMBER;
    campo4  NUMBER;
    campo5  NUMBER;
    campo6  NUMBER;
    campo7  NUMBER;
    campo8  NUMBER;
    campo9  NUMBER;
    campo10 NUMBER;
    campo11 NUMBER;

  BEGIN
    salida_text := '|';
    OPEN salida_cur;

    LOOP
      FETCH salida_cur
        INTO campo1,
             campo2,
             campo3,
             campo4,
             campo5,
             campo6,
             campo7,
             campo8,
             campo9,
             campo10,
             campo11;

      EXIT WHEN salida_cur%NOTFOUND;
      salida_text := salida_text || nvl(to_char(campo1),
                                        'null') || ',' ||
                     nvl(to_char(campo2),
                         'null') || ',' || nvl(to_char(campo3),
                                               'null') || ',' ||
                     nvl(to_char(campo4),
                         'null') || ',' || nvl(to_char(campo5),
                                               'null') || ',' ||
                     nvl(to_char(campo6),
                         'null') || ',' || nvl(to_char(campo7),
                                               'null') || ',' ||
                     nvl(to_char(campo8),
                         'null') || ',' || nvl(to_char(campo9),
                                               'null') || ',' ||
                     nvl(to_char(campo10),
                         'null') || ',' || nvl(to_char(campo11),
                                               'null') || '|';
    END LOOP;
    CLOSE salida_cur;

  END ped_realizar_calculo;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_ACTUA_CABEC_ACUM
  ------------------------------------------------------------------------------
  PROCEDURE ped_actua_cabec_acum
  (
    fecha_ini  IN VARCHAR2,
    fecha_fin  IN VARCHAR2,
    periodo    IN VARCHAR2,
    periodosig IN VARCHAR2,
    salida     OUT VARCHAR2
  ) IS

    var_aux_num VARCHAR2(8000);
    aux         VARCHAR2(200);
    aux2        VARCHAR2(200);
    auxperiodo  VARCHAR2(100);

    TYPE my_curs_type IS REF CURSOR;

    cur           my_curs_type;
    fecha         DATE;
    zona          NUMBER;
    tipo_sol      NUMBER;
    subacceso     NUMBER;
    gp1           NUMBER;
    gp2           NUMBER;
    gp3           NUMBER;
    gp4           NUMBER;
    gp5           NUMBER;
    linea_del     VARCHAR2(200);
    linea_ins     VARCHAR2(5000);
    dia           VARCHAR2(2);
    mes           VARCHAR2(2);
    año           VARCHAR2(4);
    fecha_ini_nro NUMBER;
    fecha_fin_nro NUMBER;

  BEGIN
    IF fecha_ini IS NULL OR fecha_ini = 'NULL' THEN
      aux  := '';
      aux2 := '';
    ELSE
      dia           := substr(fecha_ini,
                              1,
                              2);
      mes           := substr(fecha_ini,
                              4,
                              2);
      año           := substr(fecha_ini,
                              7,
                              4);
      fecha_ini_nro := to_number(año || mes || dia);
      aux           := ' AND PSC.FEC_PROG_FACT_COMP >= ' || fecha_ini_nro;
      aux2          := ' AND FEC_PROC >= TO_DATE(''' || fecha_ini || '''' || ',''dd/MM/yyyy'')';

      IF fecha_fin IS NULL OR fecha_fin = 'NULL' THEN
        aux := aux || ' AND PSC.FEC_PROG_FACT_COMP <= ' || fecha_ini_nro;

        aux2 := aux2 || ' AND FEC_PROC <= TO_DATE(''' || fecha_ini || '''' || ',''dd/MM/yyyy'')';
      ELSE
        dia           := substr(fecha_fin,
                                1,
                                2);
        mes           := substr(fecha_fin,
                                4,
                                2);
        año           := substr(fecha_fin,
                                7,
                                4);
        fecha_fin_nro := to_number(año || mes || dia);

        aux := aux || ' AND PSC.FEC_PROG_FACT_COMP <= ' || fecha_fin_nro;

        aux2 := aux2 || ' AND FEC_PROC <= TO_DATE(''' || fecha_fin || '''' || ',''dd/MM/yyyy'')';
      END IF;
    END IF;

    IF (periodosig IS NULL) THEN
      auxperiodo := '= ' || periodo;
    ELSE
      auxperiodo := 'IN (' || periodo || ',' || periodosig || ')';
    END IF;

    var_aux_num := 'SELECT TO_DATE(FEC_PROG_FACT),  ZZON_OID_ZONA,  TSPA_OID_TIPO_SOLI_PAIS,  SBAC_OID_SBAC,' ||
                   ' NVL(MAX(GP1),0) GP1, NVL(MAX(GP2),0) GP2, NVL(MAX(GP3),0) GP3, NVL(MAX(GP4),0) GP4,' ||
                   ' NVL(MAX(GP5),0) GP5 FROM(SELECT PSC.FEC_PROG_FACT,PSC.ZZON_OID_ZONA,PSC.TSPA_OID_TIPO_SOLI_PAIS, ' ||
                   ' PSC.SBAC_OID_SBAC, CASE WHEN (PSC.GRPR_OID_GRUP_PROC = 1) THEN SUM(1) END GP1, ' ||
                   ' CASE WHEN (PSC.GRPR_OID_GRUP_PROC = 2) THEN SUM(1) END GP2, ' ||
                   ' CASE WHEN (PSC.GRPR_OID_GRUP_PROC = 3) THEN SUM(1) END GP3, ' ||
                   ' CASE WHEN (PSC.GRPR_OID_GRUP_PROC = 4) THEN SUM(1) END GP4, ' ||
                   ' CASE WHEN (PSC.GRPR_OID_GRUP_PROC = 5) THEN SUM(1) END GP5 ' ||
                   ' FROM PED_SOLIC_CABEC PSC WHERE PSC.PERD_OID_PERI ' || auxperiodo || aux ||
                   ' GROUP BY PSC.FEC_PROG_FACT, PSC.ZZON_OID_ZONA, PSC.TSPA_OID_TIPO_SOLI_PAIS, ' ||
                   ' PSC.SBAC_OID_SBAC, PSC.GRPR_OID_GRUP_PROC) GROUP BY TO_DATE(FEC_PROG_FACT),ZZON_OID_ZONA, ' ||
                   ' TSPA_OID_TIPO_SOLI_PAIS, SBAC_OID_SBAC ';
    linea_del   := 'DELETE PED_SOLIC_CABEC_ACUM WHERE 1 = 1 ' || aux2;

    EXECUTE IMMEDIATE linea_del;

    OPEN cur FOR var_aux_num;

    LOOP
      FETCH cur
        INTO fecha,
             zona,
             tipo_sol,
             subacceso,
             gp1,
             gp2,
             gp3,
             gp4,
             gp5;

      EXIT WHEN cur%NOTFOUND;
      linea_ins := 'INSERT INTO PED_SOLIC_CABEC_ACUM (OID_ACUM,TSPA_OID_TIPO_SOLI_PAIS,ZZON_OID_ZONA,SBAC_OID_SBAC,FEC_PROC,VAL_ACUM_GP1,VAL_ACUM_GP2,VAL_ACUM_GP3,VAL_ACUM_GP4,VAL_ACUM_GP5) VALUES (PED_SCAA_SEQ.NEXTVAL,' ||
                   tipo_sol || ',' || zona || ',' || subacceso || ',''' || fecha || ''',' || gp1 || ',' || gp2 || ',' || gp3 || ',' || gp4 || ',' || gp5 || ')';

      EXECUTE IMMEDIATE linea_ins;

      linea_ins := '';
    END LOOP;

    CLOSE cur;
  END;

  ------------------------------------------------------------------------------
  -- Procedimiento Pre_Actua_Buzon_Mensa
  ------------------------------------------------------------------------------
  PROCEDURE pre_actua_buzon_mensa
  (
    param_valores_insert IN VARCHAR2,
    param_oid_pais       IN NUMBER,
    param_oid_clie       IN NUMBER,
    param_oid_cabec      IN NUMBER,
    param_salida         OUT VARCHAR2
  ) IS
    varoidmensaje        NUMBER;
    varposicionsiguiente NUMBER;
    varvalor             VARCHAR2(500);
    varrestrovalores     VARCHAR2(5000);
    vartabla             VARCHAR2(5000) := 'INSERT INTO MSG_BUZON_MENSA ' ||
                                           ' (OID_BUZO_MENS  , CLIE_OID_CLIE  ,MENS_OID_MENS  ,MODU_OID_MODU_ORIG  , NUM_SECU,' ||
                                           '  DATO_VARI_01  , DATO_VARI_03  , DATO_VARI_04  , DATO_VARI_05  , DATO_VARI_06 , DATO_vARI_07  , IND_LIST_CONS,  IND_ACTI ,  FEC_GRAB)  VALUES ( ';

    varnumerosecu NUMBER;
    varseq        NUMBER;
  BEGIN

    -- OBTENGO OID MENSAJE
    SELECT oid_mens
      INTO varoidmensaje
      FROM msg_mensa
     WHERE pais_oid_pais = param_oid_pais
       AND cod_mens = 'PRE02';
    BEGIN
      -- OBTENGO NUMERO DE SECUENCIA
      SELECT b.num_secu
        INTO varnumerosecu
        FROM msg_buzon_mensa b,
             msg_mensa       a
       WHERE b.mens_oid_mens = a.oid_mens
         AND b.mens_oid_mens = varoidmensaje
         AND b.clie_oid_clie = param_oid_clie
         AND a.pais_oid_pais = param_oid_pais
         AND b.num_secu = (SELECT MAX(c.num_secu)
                             FROM msg_buzon_mensa c,
                                  msg_mensa       d
                            WHERE c.mens_oid_mens = d.oid_mens
                              AND c.mens_oid_mens = varoidmensaje
                              AND c.clie_oid_clie = param_oid_clie
                              AND d.pais_oid_pais = param_oid_pais)
         FOR UPDATE;

    EXCEPTION
      WHEN no_data_found THEN
        varnumerosecu := 0;
    END;
    varrestrovalores := param_valores_insert;
    LOOP
      varposicionsiguiente := instr(varrestrovalores,
                                    ';');
      IF varposicionsiguiente <> 0 THEN
        BEGIN
          varnumerosecu    := varnumerosecu + 1;
          varvalor         := substr(varrestrovalores,
                                     1,
                                     varposicionsiguiente - 1);
          varrestrovalores := substr(varrestrovalores,
                                     varposicionsiguiente + 1,
                                     length(varrestrovalores));
          SELECT msg_bume_seq.nextval INTO varseq FROM dual;
          EXECUTE IMMEDIATE vartabla || varseq || ',' || param_oid_clie || ',' || varoidmensaje ||
                            ' , 5 ,  ' || varnumerosecu || ',' || varvalor || ')';
          EXECUTE IMMEDIATE 'INSERT INTO PED_SOLIC_MENSA  (OID_SOLI_MENS,  SOCA_OID_SOLI_CABE , MENS_OID_MENS  , VAL_BUZO_MENS)' ||
                            'VALUES ( PED_SOME_SEQ.nextval , ' || param_oid_cabec || ', ' ||
                            varoidmensaje || ',' || varseq || ')';

        END;
      ELSE
        EXIT;
      END IF;
    END LOOP;
  END;

  ------------------------------------------------------------------------------
  -- Procedimiento Pre_Gene_Esta_Cv
  ------------------------------------------------------------------------------
  PROCEDURE pre_gene_esta_cv
  (
    oidsolicabe  IN NUMBER,
    periodo      IN NUMBER,
    tiposolipais IN NUMBER,
    cliente      IN NUMBER
  ) IS
    CURSOR c_solicposi
    (
      oidcabec NUMBER,
      peri     NUMBER
    ) IS
      SELECT mf.oid_matr_fact,
             sp.val_codi_vent,
             nvl(sp.num_unid_aten,
                 0) num_unid_aten,
             nvl(sp.num_unid_por_aten,
                 0) num_unid_por_aten,
             nvl(sp.val_prec_cata_tota_loca,
                 0) val_prec_cata_tota_loca,
             nvl(od.num_unid_esti,
                 0) num_unid_esti,
             nvl(od.imp_prec_cata,
                 0) imp_prec_cata,
             nvl(sp.val_prec_cata_unit_loca,
                 0) val_prec_cata_unit_loca
        FROM ped_solic_posic       sp,
             pre_ofert_detal       od,
             pre_matri_factu       mf,
             pre_matri_factu_cabec mfc
       WHERE sp.soca_oid_soli_cabe = oidcabec
         AND od.oid_deta_ofer = sp.ofde_oid_deta_ofer
         AND mf.ofde_oid_deta_ofer = od.oid_deta_ofer
         AND mfc.oid_cabe = mf.mfca_oid_cabe
         AND mfc.perd_oid_peri = peri;

    r_estadist    pre_matri_estad_cv_clien%ROWTYPE;
    indanul       NUMBER;
    inddevo       NUMBER;
    montoanul     NUMBER := 0;
    unidadesanul  NUMBER := 0;
    montodevol    NUMBER := 0;
    unidadesdevol NUMBER := 0;
  BEGIN

    SELECT ts.ind_anul,
           ts.ind_devo
      INTO indanul,
           inddevo
      FROM ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts
     WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
       AND tsp.oid_tipo_soli_pais = tiposolipais;

    FOR r_solicposi IN c_solicposi(oidsolicabe,
                                   periodo)
    LOOP
      BEGIN
        SELECT *
          INTO r_estadist
          FROM pre_matri_estad_cv_clien estadi
         WHERE estadi.mafa_oid_matr_fact = r_solicposi.oid_matr_fact
           AND estadi.clie_oid_clie = cliente;

        montoanul     := r_estadist.imp_mont_anul;
        unidadesanul  := r_estadist.num_unid_anul;
        montodevol    := r_estadist.imp_mont_devu;
        unidadesdevol := r_estadist.num_unid_devu;

        IF indanul = 1 THEN
          montoanul    := r_solicposi.val_prec_cata_tota_loca + montoanul;
          unidadesanul := r_solicposi.num_unid_aten + unidadesanul;
        END IF;

        IF inddevo = 1 THEN
          montodevol    := montodevol + r_solicposi.val_prec_cata_tota_loca;
          unidadesdevol := unidadesdevol + r_solicposi.num_unid_aten;
        END IF;

        UPDATE pre_matri_estad_cv_clien
           SET num_unid_fact  = r_solicposi.num_unid_aten + num_unid_fact,
               imp_mont_fact  = r_solicposi.val_prec_cata_tota_loca + imp_mont_fact,
               num_unid_falta =
               (r_solicposi.num_unid_por_aten - r_solicposi.num_unid_aten) + num_unid_falta,
               imp_mont_falt =
               ((r_solicposi.num_unid_por_aten - r_solicposi.num_unid_aten) *
               r_solicposi.val_prec_cata_unit_loca) + imp_mont_falt,
               num_unid_devu  = unidadesdevol,
               imp_mont_devu  = montodevol,
               num_unid_anul  = unidadesanul,
               imp_mont_anul  = montoanul
         WHERE oid_matr_esta_cv_clien = r_estadist.oid_matr_esta_cv_clien;
      EXCEPTION
        WHEN no_data_found THEN
          IF indanul = 1 THEN
            montoanul    := r_solicposi.val_prec_cata_tota_loca;
            unidadesanul := r_solicposi.num_unid_aten;
          END IF;

          IF inddevo = 1 THEN
            montodevol    := r_solicposi.val_prec_cata_tota_loca;
            unidadesdevol := r_solicposi.num_unid_aten;
          END IF;

          INSERT INTO pre_matri_estad_cv_clien
            (oid_matr_esta_cv_clien,
             mafa_oid_matr_fact,
             clie_oid_clie,
             num_unid_esti,
             imp_mont_esti,
             num_unid_fact,
             imp_mont_fact,
             num_unid_falta,
             imp_mont_falt,
             num_unid_devu,
             imp_mont_devu,
             num_unid_anul,
             imp_mont_anul)
          VALUES
            (pre_mfcv_seq.nextval,
             r_solicposi.oid_matr_fact,
             cliente,
             r_solicposi.num_unid_esti,
             r_solicposi.imp_prec_cata,
             r_solicposi.num_unid_aten,
             r_solicposi.val_prec_cata_tota_loca,
             r_solicposi.num_unid_por_aten - r_solicposi.num_unid_aten,
             (r_solicposi.num_unid_por_aten - r_solicposi.num_unid_aten) *
             r_solicposi.val_prec_cata_unit_loca,
             unidadesdevol,
             montodevol,
             unidadesanul,
             montoanul);
      END;
    END LOOP;
  END pre_gene_esta_cv;

  ------------------------------------------------------------------------------
  -- Procedimiento Ped_Cargar_Acum3
  ------------------------------------------------------------------------------
  PROCEDURE ped_cargar_acum3
  (
    oidcabe IN NUMBER,
    tsp     IN NUMBER,
    clien   IN NUMBER,
    peri    IN NUMBER
  ) IS

    reg_acum    ped_solic_cabec_acum3%ROWTYPE;
    indanul     NUMBER;
    inddevo     NUMBER;
    cantidad    NUMBER := 0;
    cantpedanul NUMBER := 0;
    cantpeddevo NUMBER := 0;
    unidades    NUMBER := 0;
    monto       NUMBER := 0;
    montoanul   NUMBER := 0;
    montodevo   NUMBER := 0;
    error_acceso EXCEPTION;
  BEGIN
    SELECT ts.ind_anul,
           ts.ind_devo
      INTO indanul,
           inddevo
      FROM ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts
     WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
       AND tsp.oid_tipo_soli_pais = tsp;

    SELECT nvl(SUM(sp.num_unid_aten),
               0),
           nvl(SUM(sp.val_prec_cata_tota_loca),
               0)
      INTO unidades,
           monto
      FROM ped_solic_posic sp
     WHERE sp.soca_oid_soli_cabe = oidcabe
       AND sp.espo_oid_esta_posi <> 2;

    SELECT *
      INTO reg_acum
      FROM ped_solic_cabec_acum3 sacu
     WHERE sacu.clie_oid_clie = clien
       AND sacu.perd_oid_peri = peri;

    IF indanul = 1 THEN
      cantpedanul := reg_acum.val_cant_pedi_anul + 1;
      montoanul   := reg_acum.val_sum_mont_tota_anul + monto;
    ELSE
      cantpedanul := reg_acum.val_cant_pedi_anul;
      montoanul   := reg_acum.val_sum_mont_tota_anul;
    END IF;

    IF inddevo = 1 THEN
      cantpeddevo := reg_acum.val_cant_pedi_devo + 1;
      montodevo   := reg_acum.val_sum_mont_tota_devo + monto;
    ELSE
      cantpeddevo := reg_acum.val_cant_pedi_devo;
      montodevo   := reg_acum.val_sum_mont_tota_devo;
    END IF;

    UPDATE ped_solic_cabec_acum3
       SET val_cant_pedi          = reg_acum.val_cant_pedi + 1,
           val_sum_unid           = reg_acum.val_sum_unid + unidades,
           val_cant_pedi_devo     = cantpeddevo,
           val_cant_pedi_anul     = cantpedanul,
           val_sum_mont_tota      = reg_acum.val_sum_mont_tota + monto,
           val_sum_mont_tota_devo = montodevo,
           val_sum_mont_tota_anul = montoanul
     WHERE oid_soca_acu3 = reg_acum.oid_soca_acu3;
  EXCEPTION
    WHEN no_data_found THEN
      IF indanul = 1 THEN
        cantpedanul := 1;
        montoanul   := monto;
      END IF;

      IF inddevo = 1 THEN
        cantpeddevo := 1;
        montodevo   := monto;
      END IF;

      INSERT INTO ped_solic_cabec_acum3
        (oid_soca_acu3,
         clie_oid_clie,
         perd_oid_peri,
         val_cant_pedi,
         val_sum_unid,
         val_cant_pedi_devo,
         val_cant_pedi_anul,
         val_sum_mont_tota,
         val_sum_mont_tota_devo,
         val_sum_mont_tota_anul)
      VALUES
        (ped_sca3_seq.nextval,
         clien,
         peri,
         1,
         unidades,
         cantpeddevo,
         cantpedanul,
         monto,
         montodevo,
         montoanul);

  END ped_cargar_acum3;

  ------------------------------------------------------------------------------
  -- Procedimiento Ped_Carga_Acumu_Clien_Peri
  ------------------------------------------------------------------------------
  PROCEDURE ped_carga_acumu_clien_peri
  (
    oidcabe IN NUMBER,
    tsp     IN NUMBER,
    clien   IN NUMBER,
    peri    IN NUMBER,
    salida  OUT VARCHAR2
  ) IS
  BEGIN
    ped_cargar_acum3(oidcabe,
                     tsp,
                     clien,
                     peri);
    pre_gene_esta_cv(oidcabe,
                     peri,
                     tsp,
                     clien);
    UPDATE ped_solic_cabec_acum3
       SET val_sum_tota_paga_loca = nvl(val_sum_tota_paga_loca,
                                        0) + (SELECT nvl(psc.val_tota_paga_loca,
                                                         0)
                                                FROM ped_solic_cabec psc
                                               WHERE psc.oid_soli_cabe = oidcabe)
     WHERE clie_oid_clie = clien
       AND perd_oid_peri = peri;
  END ped_carga_acumu_clien_peri;

  ------------------------------------------------------------------------------
  -- Procedimiento FN_CU_CALC_IND_INCOBRABLE
  ------------------------------------------------------------------------------
  FUNCTION fn_cu_calc_ind_incobrable
  (
    idpais_par  IN VARCHAR2,
    idmarca_par IN VARCHAR2,
    idcanal_par IN VARCHAR2,
    idclien_par IN VARCHAR2
  ) RETURN NUMBER IS
    indicador NUMBER;
  BEGIN
    SELECT CASE
             WHEN (COUNT(*) >= 1) THEN
              1
             ELSE
              0
           END
      INTO indicador
      FROM ccc_movim_cuent_corri m,
           cra_perio             cp
     WHERE m.clie_oid_clie = idclien_par
       AND m.imp_pend <> 0
       AND cp.cana_oid_cana = idcanal_par
       AND cp.pais_oid_pais = idpais_par
       AND m.perd_oid_peri = cp.oid_peri
       AND m.marc_oid_marc = idmarca_par
       AND m.masi_oid_marc_situ IN (SELECT u.oid_marc_situ
                                      FROM ccc_marca_situa      u,
                                           ccc_marca_tipo_abono m,
                                           ccc_tipo_abono_subpr t,
                                           ccc_subpr            s,
                                           ccc_proce            p
                                     WHERE u.oid_marc_situ = m.masi_oid_marc_sali
                                       AND m.tasp_oid_tipo_abon_subp = t.oid_tipo_abon_subp
                                       AND t.subp_oid_subp = s.oid_subp
                                       AND s.ccpr_oid_proc = p.oid_proc
                                       AND s.cod_subp = 1
                                       AND p.cod_proc = 'CON002'
                                       AND u.pais_oid_pais = idpais_par
                                       AND m.ind_entr_sali LIKE 'E');
    RETURN indicador;

  END fn_cu_calc_ind_incobrable;

  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION FN_FECHA_DOCUMENTO
  ------------------------------------------------------------------------------
  FUNCTION fn_fecha_documento
  (
    idpais_par  IN VARCHAR2,
    idmarca_par IN VARCHAR2,
    idcanal_par IN VARCHAR2,
    idclien_par IN VARCHAR2
  ) RETURN DATE IS
    fecha_doc DATE;
  BEGIN
    SELECT MIN(m.fec_docu)
      INTO fecha_doc
      FROM ccc_movim_cuent_corri m,
           cra_perio             cp
     WHERE m.clie_oid_clie = idclien_par
       AND m.imp_pend <> 0
       AND cp.cana_oid_cana = idcanal_par
       AND cp.pais_oid_pais = idpais_par
       AND m.perd_oid_peri = cp.oid_peri
       AND m.marc_oid_marc = idmarca_par
       AND m.masi_oid_marc_situ IN (SELECT u.oid_marc_situ
                                      FROM ccc_marca_situa      u,
                                           ccc_marca_tipo_abono m,
                                           ccc_tipo_abono_subpr t,
                                           ccc_subpr            s,
                                           ccc_proce            p
                                     WHERE u.oid_marc_situ = m.masi_oid_marc_sali
                                       AND m.tasp_oid_tipo_abon_subp = t.oid_tipo_abon_subp
                                       AND t.subp_oid_subp = s.oid_subp
                                       AND s.ccpr_oid_proc = p.oid_proc
                                       AND s.cod_subp = 1
                                       AND p.cod_proc = 'CON001'
                                       AND u.pais_oid_pais = idpais_par
                                       AND m.ind_entr_sali LIKE 'E');
    RETURN fecha_doc;

  END fn_fecha_documento;

  ------------------------------------------------------------------------------
  -- Procedimiento IMP_PR_PROCE_DETAL_FACTU_PERF
  ------------------------------------------------------------------------------
  PROCEDURE imp_pr_proce_detal_factu_perf
  (
    p_oidsolicitud  IN NUMBER,
    p_codigopais    IN VARCHAR2,
    p_codigoperiodo IN VARCHAR2,
    salida          OUT VARCHAR2
  ) IS
  BEGIN
    --Invocamos al proceso de Impresion Laser
    imp_pkg_proce_laser.imp_pr_proce_detal_factu_perf(p_oidsolicitud,
                                                      p_codigopais,
                                                      p_codigoperiodo);

  END imp_pr_proce_detal_factu_perf;

  ------------------------------------------------------------------------------
  -- Procedimiento OCR_PR_COMP_MONT_MINIMO
  ------------------------------------------------------------------------------
  PROCEDURE ocr_pr_comp_mont_minimo
  (
    p_oidpais       IN NUMBER,
    p_codigousuario IN VARCHAR2,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
    lsfechaproceso  VARCHAR2(10);
  BEGIN
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;

    SELECT cod_peri,
           to_char(fec_proc,
                   'dd/MM/yyyy')
      INTO lscodigoperiodo,
           lsfechaproceso
      FROM bas_ctrl_fact
     WHERE cod_pais = lscodigopais
       AND sta_camp = 0
       AND ind_camp_act = 1;

    --Invocamos al proceso de Monto Minimo
    ocr_solic_pedidos.ocr_pr_comp_mont_minimo(lscodigopais,
                                              lscodigoperiodo,
                                              lsfechaproceso,
                                              p_codigousuario);

  END ocr_pr_comp_mont_minimo;

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_ACTUA_RESUM_PEDID
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_actua_resum_pedid
  (
    p_oidpais    IN NUMBER,
    p_oidmarca   IN NUMBER,
    p_oidcanal   IN NUMBER,
    p_oidperiodo IN NUMBER,
    salida       OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
    lscodigomarca   seg_marca.cod_marc%TYPE;
    lscodigocanal   seg_canal.cod_cana%TYPE;
    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN
    --Obtenemos el CodigoPais, CodigoMarca y Codigo Canal
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;
    SELECT cod_marc INTO lscodigomarca FROM seg_marca WHERE oid_marc = p_oidmarca;
    SELECT cod_cana INTO lscodigocanal FROM seg_canal WHERE oid_cana = p_oidcanal;

    --Obtenemos el Codigo de Periodo
    SELECT cod_peri
      INTO lscodigoperiodo
      FROM seg_perio_corpo cor,
           cra_perio       cra
     WHERE cor.oid_peri = cra.peri_oid_peri
       AND cra.oid_peri = p_oidperiodo;

    --Invocamos al proceso de Incentivos
    inc_pkg_proce_incen.inc_pr_actua_resum_pedid(lscodigopais,
                                                 lscodigomarca,
                                                 lscodigocanal,
                                                 lscodigoperiodo);

  END inc_pr_actua_resum_pedid;

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_ACTUA_PEDID_INCEN
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_actua_pedid_incen
  (
    p_oidpais    IN NUMBER,
    p_oidmarca   IN NUMBER,
    p_oidcanal   IN NUMBER,
    p_oidperiodo IN NUMBER,
    salida       OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
    lscodigomarca   seg_marca.cod_marc%TYPE;
    lscodigocanal   seg_canal.cod_cana%TYPE;
    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN
    --Obtenemos el CodigoPais, CodigoMarca y Codigo Canal
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;
    SELECT cod_marc INTO lscodigomarca FROM seg_marca WHERE oid_marc = p_oidmarca;
    SELECT cod_cana INTO lscodigocanal FROM seg_canal WHERE oid_cana = p_oidcanal;

    --Obtenemos el Codigo de Periodo
    SELECT cod_peri
      INTO lscodigoperiodo
      FROM seg_perio_corpo cor,
           cra_perio       cra
     WHERE cor.oid_peri = cra.peri_oid_peri
       AND cra.oid_peri = p_oidperiodo;

    --Invocamos al proceso de Incentivos
    inc_pkg_proce_incen.inc_pr_actua_pedid_incen(lscodigopais,
                                                 lscodigomarca,
                                                 lscodigocanal,
                                                 lscodigoperiodo);

  END inc_pr_actua_pedid_incen;
  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_elimi_produ_alter_falta(p_oidsolicabe NUMBER) IS

    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN

    DELETE ped_produ_alter_falta WHERE soca_oid_soli_cabe = p_oidsolicabe;
    DELETE ped_detal_prol WHERE soca_oid_soli_cabe = p_oidsolicabe;
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR PED_PR_ELIMI_PRODU_ALTER_FALTA: ' || ls_sqlerrm);

  END ped_pr_elimi_produ_alter_falta;
  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_gener_produ_alter_falta(p_oidsolicabe NUMBER) IS

    CURSOR c_prodaltefalt IS
      SELECT ped_praf_seq.nextval,
             cab.oid_soli_cabe,
             pos.val_codi_vent,
             pod.val_codi_vent,
             gen.val_i18n,
             pod.imp_prec_cata,
             cat.des_cata
        FROM ped_solic_cabec       cab,
             ped_solic_posic       pos,
             pre_matri_factu       pmf,
             pre_matri_codig_alter pmca,
             pre_matri_factu       pmf2,
             pre_ofert_detal       pod,
             bel_stock             bs,
             gen_i18n_sicc_pais    gen,
             pre_catal             cat
       WHERE pmf.ofde_oid_deta_ofer = pos.ofde_oid_deta_ofer
         AND cab.oid_soli_cabe = pos.soca_oid_soli_cabe
         AND cab.oid_soli_cabe = p_oidsolicabe
         AND pmf.oid_matr_fact = pmca.mafa_oid_cod_ppal
         AND pmca.mafa_oid_cod_alte = pmf2.oid_matr_fact
         AND pmf2.ofde_oid_deta_ofer = pod.oid_deta_ofer
         AND pod.prod_oid_prod = bs.prod_oid_prod
         AND bs.esme_oid_esta_merc = 2001
         AND bs.val_sald >= pos.num_unid_por_aten
         AND bs.almc_oid_alma = cab.almc_oid_alma
         AND pod.ocat_oid_catal = cat.oid_cata
         AND pod.prod_oid_prod = gen.val_oid
         AND gen.attr_enti = 'MAE_PRODU';

    TYPE t_oid_prod_alte_falt IS TABLE OF ped_produ_alter_falta.oid_prod_alte_falt%TYPE;
    TYPE t_cod_vent_alte IS TABLE OF ped_produ_alter_falta.cod_vent_alte%TYPE;
    TYPE t_des_alte IS TABLE OF ped_produ_alter_falta.des_alte%TYPE;
    TYPE t_imp_prec_cata IS TABLE OF ped_produ_alter_falta.imp_prec_cata%TYPE;
    TYPE t_des_cata IS TABLE OF ped_produ_alter_falta.des_cata%TYPE;

    v_oid_prod_alte_falt t_oid_prod_alte_falt;
    v_cod_vent_alte      t_cod_vent_alte;
    v_des_alte           t_des_alte;
    v_imp_prec_cata      t_imp_prec_cata;
    v_des_cata           t_des_cata;

    -- Cambios en las descripciones de las observacion por PROL
    -- RCR PER-SiCC-2012-0618
    -- Carlos Sanchez C. 05/07/2012

    CURSOR c_detaprol IS
      SELECT ped_dpro_seq.nextval,
             psc.oid_soli_cabe,
             nvl(nvl(psp.val_codi_vent,
                     lpad('0',
                          4 - length(psp.val_codi_vent_fict),
                          '0') || psp.val_codi_vent_fict),
                 '00000') AS val_codi_vent,
             CASE
               WHEN stpo_oid_subt_posi = 1 OR stpo_oid_subt_posi = 7 THEN
                psp.num_unid_dema
               ELSE
                0
             END num_unid_dema,
             psp.num_unid_dema_real,
             psp.num_unid_compr,
             psp.val_prec_cata_unit_loca,
             nvl(psp.val_porc_desc,
                 0) val_porc_desc,
             round(psp.val_impo_desc_unit_loca * num_unid_compr,
                   2),
             round(num_unid_compr * (psp.val_prec_cata_unit_loca - psp.val_impo_desc_unit_loca),
                   2) prec_fact_unit_loca,
             CASE
               WHEN (nvl(psp.num_unid_dema_real,
                         0) - nvl(psp.num_unid_compr,
                                   0) > 0) AND
                    (psc.copa_oid_para_gene IS NOT NULL OR psc.ictp_oid_tipo_prog IS NOT NULL) THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 1
                       AND ind_acti = 1),
                    'Premio Agotado')
               WHEN (SELECT COUNT(1)
                       FROM ped_reser_stock r
                      WHERE r.soca_oid_soli_cabe = psc.oid_soli_cabe
                        AND r.val_codi_vent = psp.val_codi_vent
                        AND r.num_unid_rese > 0) = 0 THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 2
                       AND ind_acti = 1),
                    'Agotado')
               WHEN nvl(psp.num_unid_dema_real,
                        0) = 0 AND nvl(psp.num_unid_compr,
                                       0) = 0 AND psp.ind_limi_vent = 1 AND
                    (pto.cod_tipo_ofer = '21' OR pto.cod_tipo_ofer = '23') THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 3
                       AND ind_acti = 1),
                    'Falt.Liq.')
               WHEN nvl(psp.num_unid_dema_real,
                        0) = 0 AND nvl(psp.num_unid_compr,
                                       0) = 0 AND psp.ind_limi_vent = 1 THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 4
                       AND ind_acti = 1),
                    'Faltante Anunciado')
               WHEN nvl(psp.num_unid_dema_real,
                        0) = 0 AND nvl(psp.num_unid_compr,
                                       0) = 0 AND psp.espo_oid_esta_posi = 2 AND
                    psp.stpo_oid_subt_posi = 21 THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 5
                       AND ind_acti = 1),
                    'Eliminado Monto Maximo')
               WHEN nvl(psp.num_unid_dema_real,
                        0) = 0 AND nvl(psp.num_unid_compr,
                                       0) = 0 AND psp.espo_oid_esta_posi = 2 THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 6
                       AND ind_acti = 1),
                    'Venta Exclusiva')
               WHEN pst.cod_subt_posi IS NOT NULL AND pst.cod_subt_posi = 'RD' THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 7
                       AND ind_acti = 1),
                    'Agotado')
               WHEN nvl(psp.num_unid_dema_real,
                        0) = 0 AND nvl(psp.num_unid_compr,
                                       0) = 0 THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 8
                       AND ind_acti = 1),
                    'No Aplica')
               WHEN (psc.copa_oid_para_gene IS NOT NULL OR psc.ictp_oid_tipo_prog IS NOT NULL) THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 9
                       AND ind_acti = 1),
                    'Premio')
               WHEN psc.modu_oid_modu = '15' THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 10
                       AND ind_acti = 1),
                    'At.Rec')
               WHEN ptp.cod_tipo_posi IS NOT NULL AND ptp.cod_tipo_posi = 'RE' THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 11
                       AND ind_acti = 1),
                    'Recuperacion')
               WHEN ptp.cod_tipo_posi IS NOT NULL AND ptp.cod_tipo_posi = 'DA' THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 12
                       AND ind_acti = 1),
                    'Reemplazo')
               WHEN pst.cod_subt_posi IS NOT NULL AND pst.cod_subt_posi = 'RZ' THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 13
                       AND ind_acti = 1),
                    'Reemp.' ||
                    (SELECT DISTINCT pof.val_codi_vent
                       FROM pre_matri_factu pmf,
                            pre_matri_reemp pmr,
                            pre_matri_factu pmf2,
                            pre_ofert_detal pof
                      WHERE pmf.oid_matr_fact = pmr.mafa_oid_cod_reem
                        AND pmf.ofde_oid_deta_ofer = psp.ofde_oid_deta_ofer
                        AND pmr.mafa_oid_cod_ppal = pmf2.oid_matr_fact
                        AND pmf2.ofde_oid_deta_ofer = pof.oid_deta_ofer
                        AND EXISTS (SELECT 1
                               FROM ped_solic_posic
                              WHERE soca_oid_soli_cabe = psc.oid_soli_cabe
                                AND ofde_oid_deta_ofer = psp.ofde_oid_deta_ofer)
                        AND rownum = 1))
               WHEN psp.val_prec_cata_unit_loca = 0 THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 14
                       AND ind_acti = 1),
                    'Gratis')
               WHEN ts.cod_tipo_soli = 'IPLC' THEN
                nvl((SELECT des_tipo_obse
                      FROM ped_tipo_obser_prol
                     WHERE oid_tipo_obse = 15
                       AND ind_acti = 1),
                    'Premio LET')
               ELSE
                ''
             END AS val_obse,
             (SELECT cod_vent_orig
                FROM ped_produ_alter pa
               WHERE pa.clie_oid_clie = psc.clie_oid_clie
                 AND pa.perd_oid_peri = psc.perd_oid_peri
                 AND pa.cod_vent_alte = nvl(nvl(psp.val_codi_vent,
                                                lpad('0',
                                                     4 - length(psp.val_codi_vent_fict),
                                                     '0') || psp.val_codi_vent_fict),
                                            '00000')) cod_vent_orig
        FROM ped_solic_cabec     psc,
             ped_solic_posic     psp,
             pre_ofert_detal     pod,
             pre_tipo_ofert      pto,
             ped_tipo_solic_pais tsp,
             ped_tipo_solic      ts,
             ped_tipo_posic      ptp,
             ped_subti_posic     pst
       WHERE psc.oid_soli_cabe = psp.soca_oid_soli_cabe
         AND psc.tspa_oid_tipo_soli_pais = tsp.oid_tipo_soli_pais
         AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
         AND psp.tpos_oid_tipo_posi = ptp.oid_tipo_posi(+)
         AND psp.stpo_oid_subt_posi = pst.oid_subt_posi(+)
         AND psp.ofde_oid_deta_ofer = pod.oid_deta_ofer(+)
         AND pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer(+)
         AND psp.espo_oid_esta_posi != 2
         AND psc.oid_soli_cabe = p_oidsolicabe;

    TYPE t_oid_deta_prol IS TABLE OF ped_detal_prol.oid_deta_prol%TYPE;
    TYPE t_soca_oid_soli_cabe IS TABLE OF ped_detal_prol.soca_oid_soli_cabe%TYPE;
    TYPE t_val_codi_vent IS TABLE OF ped_detal_prol.val_codi_vent%TYPE;
    TYPE t_num_unid_dema IS TABLE OF ped_detal_prol.num_unid_dema%TYPE;
    TYPE t_num_unid_dema_real IS TABLE OF ped_detal_prol.num_unid_dema_real%TYPE;
    TYPE t_num_unid_comp IS TABLE OF ped_detal_prol.num_unid_comp%TYPE;
    TYPE t_val_prec_cata_unit_loca IS TABLE OF ped_detal_prol.val_prec_cata_unit_loca%TYPE;
    TYPE t_val_porc_desc IS TABLE OF ped_detal_prol.val_porc_desc%TYPE;
    TYPE t_val_impo_desc_unit_loca IS TABLE OF ped_detal_prol.val_impo_desc_unit_loca%TYPE;
    TYPE t_val_prec_fact_unit_loca IS TABLE OF ped_detal_prol.val_prec_fact_unit_loca%TYPE;
    TYPE t_val_obse IS TABLE OF ped_detal_prol.val_obse%TYPE;
    TYPE t_cod_vent_orig IS TABLE OF ped_detal_prol.cod_vent_orig%TYPE;

    v_oid_deta_prol           t_oid_deta_prol;
    v_soca_oid_soli_cabe      t_soca_oid_soli_cabe;
    v_val_codi_vent           t_val_codi_vent;
    v_num_unid_dema           t_num_unid_dema;
    v_num_unid_dema_real      t_num_unid_dema_real;
    v_num_unid_comp           t_num_unid_comp;
    v_val_prec_cata_unit_loca t_val_prec_cata_unit_loca;
    v_val_porc_desc           t_val_porc_desc;
    v_val_impo_desc_unit_loca t_val_impo_desc_unit_loca;
    v_val_prec_fact_unit_loca t_val_prec_fact_unit_loca;
    v_val_obse                t_val_obse;
    v_cod_vent_orig           t_cod_vent_orig;
    w_filas                   NUMBER := 5000; -- Numero de filas a procesar cada vez

    i BINARY_INTEGER := 0;

  BEGIN

    OPEN c_prodaltefalt;
    LOOP
      FETCH c_prodaltefalt BULK COLLECT
        INTO v_oid_prod_alte_falt,
             v_soca_oid_soli_cabe,
             v_cod_vent_orig,
             v_cod_vent_alte,
             v_des_alte,
             v_imp_prec_cata,
             v_des_cata LIMIT w_filas;

      IF v_oid_prod_alte_falt.count > 0 THEN
        FOR i IN v_oid_prod_alte_falt.first .. v_oid_prod_alte_falt.last
        LOOP

          ped_pr_inser_produ_alter_falta(v_oid_prod_alte_falt(i),
                                         v_soca_oid_soli_cabe(i),
                                         v_cod_vent_orig(i),
                                         v_cod_vent_alte(i),
                                         v_des_alte(i),
                                         v_imp_prec_cata(i),
                                         v_des_cata(i));
        END LOOP;
      END IF;
      EXIT WHEN c_prodaltefalt%NOTFOUND;

    END LOOP;
    CLOSE c_prodaltefalt;

    OPEN c_detaprol;
    LOOP
      FETCH c_detaprol BULK COLLECT
        INTO v_oid_deta_prol,
             v_soca_oid_soli_cabe,
             v_val_codi_vent,
             v_num_unid_dema,
             v_num_unid_dema_real,
             v_num_unid_comp,
             v_val_prec_cata_unit_loca,
             v_val_porc_desc,
             v_val_impo_desc_unit_loca,
             v_val_prec_fact_unit_loca,
             v_val_obse,
             v_cod_vent_orig LIMIT w_filas;

      IF v_oid_deta_prol.count > 0 THEN
        FOR i IN v_oid_deta_prol.first .. v_oid_deta_prol.last
        LOOP

          ped_pr_inser_detal_prol(v_oid_deta_prol(i),
                                  v_soca_oid_soli_cabe(i),
                                  v_val_codi_vent(i),
                                  v_num_unid_dema(i),
                                  v_num_unid_dema_real(i),
                                  v_num_unid_comp(i),
                                  v_val_prec_cata_unit_loca(i),
                                  v_val_porc_desc(i),
                                  v_val_impo_desc_unit_loca(i),
                                  v_val_prec_fact_unit_loca(i),
                                  v_val_obse(i),
                                  v_cod_vent_orig(i));
        END LOOP;
      END IF;
      EXIT WHEN c_detaprol%NOTFOUND;

    END LOOP;
    CLOSE c_detaprol;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR PED_PR_GENER_PRODU_ALTER_FALTA: ' || ls_sqlerrm);

  END ped_pr_gener_produ_alter_falta;
  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_inser_produ_alter_falta
  (
    p_oid_prod_alte_falt ped_produ_alter_falta.oid_prod_alte_falt%TYPE,
    p_soca_oid_soli_cabe ped_produ_alter_falta.soca_oid_soli_cabe%TYPE,
    p_cod_vent_orig      ped_produ_alter_falta.cod_vent_orig%TYPE,
    p_cod_vent_alte      ped_produ_alter_falta.cod_vent_alte%TYPE,
    p_des_alte           ped_produ_alter_falta.des_alte%TYPE,
    p_imp_prec_cata      ped_produ_alter_falta.imp_prec_cata%TYPE,
    p_des_cata           ped_produ_alter_falta.des_cata%TYPE
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    INSERT INTO ped_produ_alter_falta
      (oid_prod_alte_falt,
       soca_oid_soli_cabe,
       cod_vent_orig,
       cod_vent_alte,
       des_alte,
       imp_prec_cata,
       des_cata)
    VALUES
      (p_oid_prod_alte_falt,
       p_soca_oid_soli_cabe,
       p_cod_vent_orig,
       p_cod_vent_alte,
       p_des_alte,
       p_imp_prec_cata,
       p_des_cata);

    COMMIT;
  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR PED_PR_INSER_PRODU_ALTER_FALTA: ' || ls_sqlerrm);

  END ped_pr_inser_produ_alter_falta;

  ------------------------------------------------------------------------------
  -- Procedimiento Para generar Productos Alternativos
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_inser_detal_prol
  (
    p_oid_deta_prol           ped_detal_prol.oid_deta_prol%TYPE,
    p_soca_oid_soli_cabe      ped_detal_prol.soca_oid_soli_cabe%TYPE,
    p_val_codi_vent           ped_detal_prol.val_codi_vent%TYPE,
    p_num_unid_dema           ped_detal_prol.num_unid_dema%TYPE,
    p_num_unid_dema_real      ped_detal_prol.num_unid_dema_real%TYPE,
    p_num_unid_comp           ped_detal_prol.num_unid_comp%TYPE,
    p_val_prec_cata_unit_loca ped_detal_prol.val_prec_cata_unit_loca%TYPE,
    p_val_porc_desc           ped_detal_prol.val_porc_desc%TYPE,
    p_val_impo_desc_unit_loca ped_detal_prol.val_impo_desc_unit_loca%TYPE,
    p_val_prec_fact_unit_loca ped_detal_prol.val_prec_fact_unit_loca%TYPE,
    p_val_obse                ped_detal_prol.val_obse%TYPE,
    p_cod_vent_orig           ped_detal_prol.cod_vent_orig%TYPE
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN

    INSERT INTO ped_detal_prol
      (oid_deta_prol,
       soca_oid_soli_cabe,
       val_codi_vent,
       num_unid_dema,
       num_unid_dema_real,
       num_unid_comp,
       val_prec_cata_unit_loca,
       val_porc_desc,
       val_impo_desc_unit_loca,
       val_prec_fact_unit_loca,
       val_obse,
       cod_vent_orig)
    VALUES
      (p_oid_deta_prol,
       p_soca_oid_soli_cabe,
       p_val_codi_vent,
       p_num_unid_dema,
       p_num_unid_dema_real,
       p_num_unid_comp,
       p_val_prec_cata_unit_loca,
       p_val_porc_desc,
       p_val_impo_desc_unit_loca,
       p_val_prec_fact_unit_loca,
       p_val_obse,
       p_cod_vent_orig);

    COMMIT;
  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR PED_PR_INSER_DETAL_PROL: ' || ls_sqlerrm);

  END ped_pr_inser_detal_prol;

  ------------------------------------------------------------------------------
  -- Procedimiento OCR_PR_PROCE_ESPEC
  ------------------------------------------------------------------------------
  PROCEDURE ocr_pr_proce_espec
  (
    p_oidpais       IN NUMBER,
    p_oidsolicitud  IN NUMBER,
    p_codigousuario IN VARCHAR2,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais seg_pais.cod_pais%TYPE;
  BEGIN
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;
    --Invocamos al proceso de Monto Minimo
    ocr_solic_pedidos.ocr_pr_proce_espec(lscodigopais,
                                         p_oidsolicitud,
                                         p_codigousuario);

  END ocr_pr_proce_espec;

  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION PED_FN_OBTEN_GASTO_ADMIN
  ------------------------------------------------------------------------------
  FUNCTION ped_fn_obten_gasto_admin(idconsolidado IN NUMBER) RETURN NUMBER IS
    lngastoadmin ped_solic_cabec.val_tota_gast_admi%TYPE := 0;

    lv_codpais VARCHAR2(10);

    lv_indiejec VARCHAR2(10);

  BEGIN

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    lv_indiejec := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lv_codpais,
                                                            'STO_GASTO_ADMIN'),
                       'N');

    IF lv_indiejec <> 'S' THEN
      RETURN 0;
    END IF;

    SELECT SUM(nvl(psc.val_tota_gast_admi,
                   0))
      INTO lngastoadmin
      FROM ped_solic_cabec     psc,
           ped_tipo_solic_pais ptp,
           ped_tipo_solic      pts
     WHERE psc.soca_oid_soli_cabe = idconsolidado
       AND psc.tspa_oid_tipo_soli_pais = ptp.oid_tipo_soli_pais
       AND ptp.tsol_oid_tipo_soli = pts.oid_tipo_soli
       AND (pts.ind_soli_nega = 0 OR pts.ind_anul = 1);

    RETURN nvl(lngastoadmin,
               0);
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END ped_fn_obten_gasto_admin;

  ------------------------------------------------------------------------------
  -- Procedimiento FAC_PR_GASTOS_ADMI_SICC
  ------------------------------------------------------------------------------
  PROCEDURE fac_pr_gastos_admi_sicc(p_oidpais IN NUMBER) IS
  BEGIN
    --Invocamos al proceso de Impresion Laser
    fac_pkg_proc.fac_pr_gastos_admi_sicc;

  END fac_pr_gastos_admi_sicc;

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_GENER_SOLIC_BOLSA_FALTA
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_gener_solic_bolsa_falta
  (
    p_oidpais    IN NUMBER,
    p_oidmarca   IN NUMBER,
    p_oidcanal   IN NUMBER,
    p_oidperiodo IN NUMBER,
    p_oidzona    IN NUMBER,
    p_usuario    IN VARCHAR2,
    salida       OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
    lscodigomarca   seg_marca.cod_marc%TYPE;
    lscodigocanal   seg_canal.cod_cana%TYPE;
    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
    lscodigozona    zon_zona.cod_zona%TYPE;
  BEGIN
    --Obtenemos el CodigoPais, CodigoMarca y Codigo Canal
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;
    SELECT cod_marc INTO lscodigomarca FROM seg_marca WHERE oid_marc = p_oidmarca;
    SELECT cod_cana INTO lscodigocanal FROM seg_canal WHERE oid_cana = p_oidcanal;

    --Obtenemos el Codigo de Periodo
    SELECT cod_peri
      INTO lscodigoperiodo
      FROM seg_perio_corpo cor,
           cra_perio       cra
     WHERE cor.oid_peri = cra.peri_oid_peri
       AND cra.oid_peri = p_oidperiodo;

    --Obtenemos el Codigo de Periodo
    SELECT cod_zona INTO lscodigozona FROM zon_zona WHERE oid_zona = p_oidzona;

    --Invocamos al proceso de Incentivos
    inc_pkg_proce_incen.inc_pr_gener_solic_bolsa_falta(lscodigopais,
                                                       lscodigomarca,
                                                       lscodigocanal,
                                                       lscodigoperiodo,
                                                       lscodigozona,
                                                       p_usuario);

  END inc_pr_gener_solic_bolsa_falta;

  ------------------------------------------------------------------------------
  -- Procedimiento INC_PR_PROGR_NUEVA_REGAL
  ------------------------------------------------------------------------------
  PROCEDURE inc_pr_progr_nueva_regal
  (
    p_oidpais     NUMBER,
    p_oidperiodo  NUMBER,
    p_oidconcurso NUMBER,
    p_oidcliente  NUMBER,
    p_resultado   OUT VARCHAR2
  ) IS
    lsresultado    VARCHAR2(1);
    lnpuntajetotal NUMBER(7);

    lnoidcausa         inc_causa_desca.oid_caus_desc%TYPE;
    lscodclasificacion inc_clasi_concu.cod_clas_conc%TYPE;
    ldfechaproceso     bas_ctrl_fact.fec_proc%TYPE;
    lnoidsecuencia     inc_cuent_corri_punto.oid_cuen_corr_punt%TYPE;

  BEGIN

    lsresultado := '0';

    BEGIN
      SELECT cla.cod_clas_conc
        INTO lscodclasificacion
        FROM inc_concu_param_gener gen,
             inc_clasi_concu       cla
       WHERE gen.ccon_oid_clas_conc = cla.oid_clas_conc
         AND gen.oid_para_gral = p_oidconcurso;
    EXCEPTION
      WHEN OTHERS THEN
        lscodclasificacion := NULL;
    END;

    IF ((lscodclasificacion = 'X') OR (lscodclasificacion = 'K')) THEN
      --Obtenemos la Fecha de Proceso
      SELECT fec_proc
        INTO ldfechaproceso
        FROM bas_ctrl_fact bas,
             seg_pais      pai
       WHERE bas.cod_pais = pai.cod_pais
         AND pai.oid_pais = p_oidpais
         AND bas.sta_camp = '0'
         AND bas.ind_camp_act = '1';

      SELECT oid_caus_desc INTO lnoidcausa FROM inc_causa_desca WHERE cod_caus = 'PN';

      --Sumariza todos los puntajes del concurso
      SELECT nvl(SUM(num_punt),
                 0)
        INTO lnpuntajetotal
        FROM inc_cuent_corri_punto
       WHERE clie_oid_clie = p_oidcliente
         AND copa_oid_para_gral = p_oidconcurso;

      INSERT INTO inc_desca
        (oid_desc,
         fec_desc,
         clie_oid_clie,
         copa_oid_para_gral,
         perd_oid_peri,
         cade_oid_caus_desc)
      VALUES
        (inc_cesc_seq.nextval,
         ldfechaproceso,
         p_oidcliente,
         p_oidconcurso,
         p_oidperiodo,
         lnoidcausa);

      --Crear un nuevo registro en la tabla de Cuenta corriente:
      lnoidsecuencia := inc_cucp_seq.nextval;

      --Insertamos el puntaje en la Entidad Cuenta Corriente Puntos
      INSERT INTO inc_cuent_corri_punto
        (oid_cuen_corr_punt,
         num_movi,
         num_punt,
         num_punt_exig,
         fec_movi,
         copa_oid_para_gral,
         clie_oid_clie,
         perd_oid_peri,
         tmov_oid_tipo_movi,
         fec_ulti_actu,
         val_desc)
      VALUES
        (lnoidsecuencia,
         lnoidsecuencia,
         (-1) * lnpuntajetotal,
         0,
         ldfechaproceso,
         p_oidconcurso,
         p_oidcliente,
         p_oidperiodo,
         2,
         SYSDATE,
         'Cargo Puntaje por atención Programa Nuevas');

      lsresultado := '1';

    END IF;

    p_resultado := lsresultado;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR INC_PR_PROGR_NUEVA_REGAL: ' || ls_sqlerrm);

  END inc_pr_progr_nueva_regal;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_MONTO_MAXIM
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_monto_maxim
  ( p_oidsolicitud  IN NUMBER,
    p_usuario       IN VARCHAR2,
    salida          OUT VARCHAR2
  ) IS
  BEGIN
    --Invocamos al proceso de Monto Maximo
    PED_PKG_CUADR_OFERT.ped_pr_monto_maxim(p_oidsolicitud,
                                           'ADMIN');

  END ped_pr_monto_maxim;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_MONTO_MINIM
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_monto_minim
  ( p_oidsolicitud  IN NUMBER,
    p_usuario       IN VARCHAR2,
    salida          OUT VARCHAR2
  ) IS
  BEGIN
    --Invocamos al proceso de Monto Minimo
    PED_PKG_CUADR_OFERT.ped_pr_monto_minim(p_oidsolicitud,
                                           'ADMIN');

  END ped_pr_monto_minim;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_OFERT_ESPEC
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_ofert_espec
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
  BEGIN
    --Obtenemos el CodigoPais
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;

    --Invocamos al proceso de Ofertas Especiales
    PED_PKG_CUADR_OFERT.ped_pr_ofert_espec(p_oidsolicitud,
                                           lscodigopais);

  END ped_pr_ofert_espec;

  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION INC_FN_OBTEN_PUNTA_RETAI
  ------------------------------------------------------------------------------
  FUNCTION inc_fn_obten_punta_retai
  ( p_oidpais       IN NUMBER,
    p_oidcliente    IN NUMBER,
    p_oidperiodo    IN NUMBER
  ) RETURN NUMBER IS

    lscodpais          seg_pais.cod_pais%TYPE;
    lscodigoperiodo    seg_perio_corpo.cod_peri%TYPE;
    lscodigoperiodoant seg_perio_corpo.cod_peri%TYPE;

    lnoidmarca         seg_marca.oid_marc%TYPE;
    lnoidcanal         seg_canal.oid_cana%TYPE;
    lnSuma             NUMBER;
  BEGIN
    --Obtenemos el codigo Pais
    SELECT COD_PAIS INTO lscodpais FROM SEG_PAIS WHERE OID_PAIS = p_oidpais;

    --Obtenemos el Codigo de Periodo
    SELECT cod_peri
      INTO lscodigoperiodo
      FROM seg_perio_corpo cor,
           cra_perio       cra
     WHERE cor.oid_peri = cra.peri_oid_peri
       AND cra.oid_peri = p_oidperiodo;

    lnoidmarca   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_MARCA('T');
    lnoidcanal   := GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_CANAL('VD');

    --Obtenemos el codigo periodo Anterior
    lscodigoperiodoant := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(lscodigoperiodo,p_oidpais,lnoidmarca,lnoidcanal,-1);

    SELECT NVL(SUM(pun.NUM_PUNT),0)
      INTO lnSuma
      FROM INC_PUNTO_RETAI pun, MAE_CLIEN cli
     WHERE pun.COD_PAIS = lscodpais
       AND pun.CAM_INIC = lscodigoperiodoant
       AND pun.COD_CONS = cli.Cod_Clie
       AND cli.OID_CLIE = p_oidcliente
       AND pun.IND_PUNT_MONT_RECO = '1'
       AND pun.EST_REGI = '1';


    RETURN lnSuma;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END inc_fn_obten_punta_retai;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_MONTO_MINIM_INCEN
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_monto_minim_incen
  ( p_oidsolicitud  IN NUMBER,
    p_usuario       IN VARCHAR2,
    salida          OUT VARCHAR2
  ) IS
  BEGIN
    --Invocamos al proceso de Monto Minimo Incentivos
    PED_PKG_CUADR_OFERT.ped_pr_monto_minim(p_oidsolicitud,
                                           'ADMIN');

  END ped_pr_monto_minim_incen;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_OFERT_ESPEC2
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_ofert_espec2
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
  BEGIN
    --Obtenemos el CodigoPais
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;

    --Invocamos al proceso de Ofertas Especiales 2
    PED_PKG_CUADR_OFERT.ped_pr_ofert_espec2(p_oidsolicitud,
                                            lscodigopais);

  END ped_pr_ofert_espec2;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_ASIGNAR_CUV
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_asignar_CUV
  ( p_oidcabecera   IN NUMBER,
    p_oidcatalogo   IN NUMBER,
    salida          OUT VARCHAR2
  ) IS
    lnOidPeriodo    CRA_PERIO.OID_PERI%TYPE;
  BEGIN
    
    --Obtenemos el Oid Periodo                 
    SELECT PERD_OID_PERI
      INTO lnOidPeriodo
      FROM PRE_MATRI_FACTU_CABEC
     WHERE OID_CABE = p_oidcabecera;
    
    --Invocamos al proceso de Asignar CUV
    ped_pkg_cuadr_ofert.ped_pr_asignar_CUV(lnOidPeriodo,
                                           p_oidcatalogo);

  END ped_pr_asignar_CUV;  

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_OFERT_ESPEC2
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_cuadr_revis
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
  BEGIN
    --Obtenemos el CodigoPais
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;

    --Invocamos al proceso de Ofertas Especiales 2
    PED_PKG_CUADR_OFERT.ped_pr_cuadr_revis(p_oidsolicitud,
                                            lscodigopais);

  END ped_pr_cuadr_revis;
  
  /***************************************************************************
  Descripcion       : Calcula Segmentacion de los Procesos GP2 y GP4 de SICC
  Fecha Creacion    : 26/09/2014
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE gen_pr_calcu_segme_sicc
  (psProcesoMonitor           VARCHAR2,
   psGrupoProceso             VARCHAR2,
   pnTotalPedidos             NUMBER,
   salida                     OUT VARCHAR2)
  IS PRAGMA AUTONOMOUS_TRANSACTION;

    lsTipoSegmentacion          BAS_PARAM_SEGME_DINAM.IND_SEGM%TYPE;
    
    lnNumeroServidores          NUMBER;
    lnNumeroColas               NUMBER;
    
    lnHilosEnProceso            NUMBER;
    lnTotalSegmentos            NUMBER;
    lnPedidosxHilo              NUMBER;
    lnTamanioSegmento           NUMBER;
    lnTamanioSubSegmento        NUMBER;
    lnPivoteSubSegmento         NUMBER;
    
  BEGIN

    BEGIN
      SELECT IND_SEGM
        INTO lsTipoSegmentacion
        FROM BAS_PARAM_SEGME_DINAM;
    EXCEPTION
      WHEN OTHERS THEN
        lsTipoSegmentacion := '0';
    END;
    
    IF(lsTipoSegmentacion = '1'AND psGrupoProceso='GP2') THEN --Tipo: Manual
      INSERT INTO BAS_MONIT_SEGME
        (COD_PROC,
         VAL_GRUP_PROC,
         NUM_TAMA_SEGM,
         NUM_MAXI_CANT_SEGM,
         NUM_TAMA_SUB_SEGM,
         NUM_TAMA_SEGM_INIC,
         IND_SEGM,
         FEC_CREA)
      SELECT psProcesoMonitor,
             psGrupoProceso,
             NUM_TAMA_SEGM_GP2,
             NUM_MAXI_CANT_SEGM_GP2,
             NUM_TAMA_SUB_SEGM_GP2,
             NUM_COLA_PROC_GP2 * NUM_SERV_BATC,
             IND_SEGM,
             SYSDATE
        FROM BAS_PARAM_SEGME_DINAM;   
    END IF;        

    IF(lsTipoSegmentacion = '1'AND psGrupoProceso='GP4') THEN --Tipo: Manual
      INSERT INTO BAS_MONIT_SEGME
        (COD_PROC,
         VAL_GRUP_PROC,
         NUM_TAMA_SEGM,
         NUM_MAXI_CANT_SEGM,
         NUM_TAMA_SUB_SEGM,
         NUM_TAMA_SEGM_INIC,
         IND_SEGM,
         FEC_CREA)
      SELECT psProcesoMonitor,
             psGrupoProceso,
             NUM_TAMA_SEGM_GP4,
             NUM_MAXI_CANT_SEGM_GP4,
             NUM_TAMA_SUB_SEGM_GP4,
             NUM_COLA_PROC_GP4 * NUM_SERV_BATC,
             IND_SEGM,
             SYSDATE
        FROM BAS_PARAM_SEGME_DINAM;   
    END IF;
    
    IF(lsTipoSegmentacion = '2' AND psGrupoProceso='GP2') THEN --Tipo: Automatico
      SELECT NUM_SERV_BATC,
             NUM_COLA_PROC_GP2,
             NUM_MAXI_PIVO_GP2
        INTO lnNumeroServidores,
             lnNumeroColas,
             lnPivoteSubSegmento
        FROM BAS_PARAM_SEGME_DINAM;     
      
      lnTotalSegmentos := lnNumeroServidores;
      lnHilosEnProceso := lnNumeroColas -1;
      lnPedidosxHilo := lnPivoteSubSegmento;
             
      IF(pnTotalPedidos > (lnNumeroServidores * lnHilosEnProceso * lnPedidosxHilo)) THEN
        lnTamanioSegmento := lnHilosEnProceso * lnPedidosxHilo;
        lnTamanioSubSegmento := lnPedidosxHilo;
      ELSE
        lnPedidosxHilo := CEIL(pnTotalPedidos / (lnHilosEnProceso * lnNumeroServidores));
        lnTamanioSegmento := lnHilosEnProceso * lnPedidosxHilo;
        lnTamanioSubSegmento := lnPedidosxHilo;
      END IF;
      
      INSERT INTO BAS_MONIT_SEGME
        (COD_PROC,
         VAL_GRUP_PROC,
         NUM_TAMA_SEGM,
         NUM_MAXI_CANT_SEGM,
         NUM_TAMA_SUB_SEGM,
         NUM_TAMA_SEGM_INIC,
         IND_SEGM,
         FEC_CREA)
      VALUES
        (psProcesoMonitor,
         psGrupoProceso,
         lnTamanioSegmento,
         lnTotalSegmentos,
         lnTamanioSubSegmento,
         lnNumeroServidores * lnNumeroColas,
         lsTipoSegmentacion,
         SYSDATE);   
    END IF; 
    
    IF(lsTipoSegmentacion = '2' AND psGrupoProceso='GP4') THEN --Tipo: Automatico
      SELECT NUM_SERV_BATC,
             NUM_COLA_PROC_GP4,
             NUM_MAXI_PIVO_GP4
        INTO lnNumeroServidores,
             lnNumeroColas,
             lnPivoteSubSegmento
        FROM BAS_PARAM_SEGME_DINAM;     
      
      lnTotalSegmentos := lnNumeroServidores;
      lnHilosEnProceso := lnNumeroColas -1;
      lnPedidosxHilo := lnPivoteSubSegmento;
             
      IF(pnTotalPedidos > (lnNumeroServidores * lnHilosEnProceso * lnPedidosxHilo)) THEN
        lnTamanioSegmento := lnHilosEnProceso * lnPedidosxHilo;
        lnTamanioSubSegmento := lnPedidosxHilo;
      ELSE
        lnPedidosxHilo := CEIL(pnTotalPedidos / (lnHilosEnProceso * lnNumeroServidores));
        lnTamanioSegmento := lnHilosEnProceso * lnPedidosxHilo;
        lnTamanioSubSegmento := lnPedidosxHilo;
      END IF;
      
      INSERT INTO BAS_MONIT_SEGME
        (COD_PROC,
         VAL_GRUP_PROC,
         NUM_TAMA_SEGM,
         NUM_MAXI_CANT_SEGM,
         NUM_TAMA_SUB_SEGM,
         NUM_TAMA_SEGM_INIC,
         IND_SEGM,
         FEC_CREA)
      VALUES
        (psProcesoMonitor,
         psGrupoProceso,
         lnTamanioSegmento,
         lnTotalSegmentos,
         lnTamanioSubSegmento,
         lnNumeroServidores * lnNumeroColas,
         lsTipoSegmentacion,
         SYSDATE);   
    END IF; 
    
    COMMIT;        
      
  EXCEPTION
  WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,250);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR gen_pr_calcu_segme_sicc: ('|| ln_sqlcode || ')' || ls_sqlerrm);
  END gen_pr_calcu_segme_sicc;

  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_CUADR_NX
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_cuadr_nx
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
  BEGIN
    --Obtenemos el CodigoPais
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;

    --Invocamos al proceso de Cuadre de Ofertas
    PED_PKG_CUADR_OFERT.ped_pr_cuadr_nx(p_oidsolicitud,
                                            lscodigopais);

  END ped_pr_cuadr_nx;
  
  ------------------------------------------------------------------------------
  -- Procedimiento FUNCTION INC_FN_VALID_CAMPA_ANTER
  ------------------------------------------------------------------------------
  FUNCTION inc_fn_valid_campa_anter
  ( p_oidpais       IN NUMBER,
    p_oidcliente    IN NUMBER,
    p_oidperiodo    IN NUMBER
  ) RETURN NUMBER IS

    lnoidperiodoant    NUMBER;
    lnOcurrencias      NUMBER;
  BEGIN
    
    --Validamos el Estatus si es Nueva o Reincorporada
    SELECT COUNT(1) 
     INTO lnOCurrencias 
     FROM MAE_CLIEN_HISTO_ESTAT
     WHERE PERD_OID_PERI_PERI_FIN is NULL
      AND ( ((PERD_OID_PERI < p_oidperiodo) AND (ESTA_OID_ESTA_CLIE IN (1, 7)) ) OR
            ((PERD_OID_PERI >= p_oidperiodo) AND (ESTA_OID_ESTA_CLIE IN (1, 7, 2, 8)) ) 
           )
      AND CLIE_OID_CLIE = p_oidcliente;
      
    IF(lnOcurrencias > 0) THEN
      RETURN 1;
    END IF;  
    
    --Recuperamos el periodo anterior
    SELECT oid_peri
      INTO lnoidperiodoant
      FROM (SELECT   cray.oid_peri 
                FROM SEG_PERIO_CORPO corx,
                      CRA_PERIO crax,
                      SEG_PERIO_CORPO cory,
                      CRA_PERIO cray
               WHERE corx.oid_peri = crax.peri_oid_peri
                 AND cory.oid_peri = cray.peri_oid_peri
                 AND crax.oid_peri = p_oidperiodo
                 AND cory.cod_peri < corx.cod_peri
            ORDER BY cory.cod_peri DESC) 
      WHERE rownum <= 1; 

    --Verificamos si tiene pedido en Campaña Anterior
    SELECT COUNT(1)
      INTO lnOcurrencias
      FROM PED_SOLIC_CABEC PSC,
           PED_SOLIC_CABEC CAB,
           PED_TIPO_SOLIC_PAIS TSP,
           PED_TIPO_SOLIC TSO
     WHERE PSC.Perd_Oid_Peri = lnoidperiodoant
       AND PSC.CLIE_OID_CLIE = p_oidcliente
       AND PSC.TSPA_OID_TIPO_SOLI_PAIS = TSP.OID_TIPO_SOLI_PAIS
       AND TSP.TSOL_OID_TIPO_SOLI = TSO.OID_TIPO_SOLI
       AND PSC.SOCA_OID_SOLI_CABE = CAB.OID_SOLI_CABE(+)
       AND NVL(CAB.ESSO_OID_ESTA_SOLI,0) <> 4
       AND TSO.COD_TIPO_SOLI = 'SOC';

    IF(lnOcurrencias > 0) THEN
      RETURN 1;
    ELSE
      RETURN 0;
    END IF;

  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
  END inc_fn_valid_campa_anter;
  
  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_ELIM_FALT_PROL_INDI
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_elim_falt_prol_indi
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
  BEGIN
    --Obtenemos el CodigoPais
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;

    --Invocamos al proceso de Eliminar Productos Faltantes PROL Individuales
    ped_pkg_cuadr_ofert.ped_pr_elim_falt_prol_indi(p_oidsolicitud,
                                                   lscodigopais);

  END ped_pr_elim_falt_prol_indi;  
  
  ------------------------------------------------------------------------------
  -- Procedimiento PED_PR_ELIM_FALT_PROL_CFIJA
  ------------------------------------------------------------------------------
  PROCEDURE ped_pr_elim_falt_prol_cfija
  ( p_oidsolicitud  IN NUMBER,
    p_oidpais       IN NUMBER,
    salida          OUT VARCHAR2
  ) IS
    lscodigopais    seg_pais.cod_pais%TYPE;
  BEGIN
    --Obtenemos el CodigoPais
    SELECT cod_pais INTO lscodigopais FROM seg_pais WHERE oid_pais = p_oidpais;

    --Invocamos al proceso de Eliminar Productos Faltantes PROL Individuales
    ped_pkg_cuadr_ofert.ped_pr_elim_falt_prol_cfija(p_oidsolicitud,
                                                    lscodigopais);

  END ped_pr_elim_falt_prol_cfija;
    
  
END pq_plani;
/
