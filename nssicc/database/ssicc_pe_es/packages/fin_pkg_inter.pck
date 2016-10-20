CREATE OR REPLACE PACKAGE FIN_PKG_INTER IS

 -- Declaracion de Tipos
 TYPE t_tab_camp IS TABLE OF VARCHAR2(500)  INDEX BY BINARY_INTEGER;

 gv_des_log                               VARCHAR2(2500);
 ln_sqlcode                               NUMBER(10);
 ls_sqlerrm                               VARCHAR2(150);

 PROCEDURE FIN_PR_LISTA_ARCHI_DIREC(
  p_dir_ensa                       IN   VARCHAR2);
  
 PROCEDURE FIN_PR_OBTIE_NUMER_LOTE(
  p_num_lote                       OUT   fin_inter_ejecu.num_lote%TYPE);

 PROCEDURE FIN_PR_REGIS_INTER_ENTRA_EJECU(
  p_cod_modu                       IN   fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN   fin_inter_ejecu.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE,
  p_nom_arch                       IN   fin_inter_ejecu.nom_arch_proc%TYPE,
  p_cod_usu                        IN   seg_usuar.use_usua%TYPE,
  p_num_mult_lote                  IN   fin_inter_ejecu.num_mult_lote%TYPE);

 PROCEDURE FIN_PR_REGIS_INTER_SALID_EJECU(
  p_cod_modu                       IN       fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN       fin_inter_ejecu.cod_inte%TYPE,
  p_cod_usua                       IN       seg_usuar.use_usua%TYPE,
  p_num_lote                       IN       fin_inter_ejecu.num_lote%TYPE,
  p_dir_ensa                       IN OUT   fin_param_inter_cabec.dir_ensa%TYPE,
  p_nom_arch                       IN OUT   VARCHAR2,
  p_handle                         OUT      utl_file.file_type);

 PROCEDURE FIN_PR_ACTUA_INTER_EJECU(
  p_cod_modu                       IN   fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN   fin_inter_ejecu.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE,
  p_des_log                        IN   fin_inter_ejecu.des_log%TYPE);

   /**************************************************************************
     Descripcion       : Procesar Interface de Entrada
     Fecha Creacion    : 27/10/2010
     Parametros Entrada :
             Codigo Modulo
             Codigo Interface
            Codigo Usuario
    Autor : Jorge Florencio
   ***************************************************************************/
 PROCEDURE FIN_PR_PROCE_INTER_ENTRA(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_nom_arch_proc                  IN   fin_inter_ejecu.nom_arch_proc%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote_multi                 IN   fin_inter_ejecu.num_lote%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE,
  p_cod_erro                       OUT  VARCHAR2);

   /**************************************************************************
     Descripcion       : Procesar Interface Salida
     Fecha Creacion    : 27/10/2010
     Parametros Entrada :
             Codigo Modulo
             Codigo Interface
            Codigo Usuario
    Autor : Jorge Florencio
   ***************************************************************************/
   PROCEDURE FIN_PR_PROCE_INTER_SALID(
      p_cod_modu                   IN       fin_param_inter_cabec.cod_modu%TYPE,
      p_cod_inte                     IN       fin_param_inter_cabec.cod_inte%TYPE,
      p_num_lote                    IN        fin_inter_ejecu.num_lote%TYPE,
      p_nom_arch                   IN       fin_param_inter_cabec.nom_arch%TYPE,
      p_val_bind_vari              IN      VARCHAR2,
      p_cod_usua                     IN       seg_usuar.use_usua%TYPE,
      p_val_serv_ftp              IN       fin_param_inter_cabec.ser_ftp%TYPE DEFAULT NULL,
      p_val_puer_ftp               IN      fin_param_inter_cabec.pue_ftp%TYPE DEFAULT NULL,
      p_val_usua_ftp              IN     fin_param_inter_cabec.usu_ftp%TYPE DEFAULT NULL,
      p_val_pass_ftp              IN    fin_param_inter_cabec.pas_ftp%TYPE DEFAULT NULL,
      p_val_dire_ftp               IN    fin_param_inter_cabec.dir_ftp%TYPE DEFAULT NULL);

 PROCEDURE FIN_PR_FINAL_INTER_SALID_EJECU(
  p_cod_modu                       IN       fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN       fin_inter_ejecu.cod_inte%TYPE,
  p_num_lote                       IN       fin_inter_ejecu.num_lote%TYPE,
  p_cant_regi_proc                 IN       fin_inter_ejecu.reg_proc%TYPE,
  p_cod_esta_inte                  IN       fin_inter_ejecu.cod_esta_inte%TYPE,
  p_des_erro                       IN       fin_inter_ejecu.des_erro%TYPE DEFAULT  '');

   /**************************************************************************
     Descripcion       :  Procedimiento generar el Text File configurado  en la  Interface
     Fecha Creacion    : 27/10/2010
     Parametros Entrada :
             Codigo Modulo
             Codigo Interface
             Lista de Bind Variables
             Nombre del Archivo a Generar
     Autor : Jorge Florencio
   ***************************************************************************/
   PROCEDURE FIN_PR_EXPOR_INTER_TO_TEXT(
      p_cod_modu              IN       fin_param_inter_cabec.cod_modu%TYPE,
      p_cod_inte                 IN       fin_param_inter_cabec.cod_inte%TYPE,
      p_val_bind_vari         IN        VARCHAR2,
      p_filename                  IN       VARCHAR2,
      p_val_cant_line           OUT   NUMBER);

   /*************************************************************************
      Descripcion       :  Procedimiento para Exportar una  Tabla a un archivo CSV
      Fecha Creacion    : 27/10/2010
      Parametros Entrada :
             Nombre de Tabla
             Directorio
             Nombre de Archivo
     Autor : Jorge Florencio
   ****************************************************************** ******/
   PROCEDURE FIN_PR_EXPOR_TABLE_TO_CSV(
      p_tname                      IN       VARCHAR2,
      p_dir                           IN       VARCHAR2,
      p_filename                  IN       VARCHAR2 );

   /**************************************************************************
     Descripcion       :  Procedimiento para Exportar una Sentencia SQL a un archivo CSV
     Fecha Creacion    : 27/10/2010
     Parametros Entrada :
             Nombre de Tabla
             Directorio
             Nombre de Archivo
     Autor : Jorge Florencio
   ***************************************************************************/
   PROCEDURE FIN_PR_EXPOR_SQL_TO_CSV(
      p_query                       IN       VARCHAR2,
      p_dir                           IN       VARCHAR2,
      p_filename                  IN       VARCHAR2 );

   /**************************************************************************
     Descripcion       :  Funcion que formatea un valor en base
                                  a los parametros de entrada.
     Fecha Creacion    : 27/10/2010
     Parametros Entrada :
             Valor del Campo a Formatear
             Tipo de Campo
             Longitud del Campo
             Formato del Campo
             Indicador de Relleno
             Valor del Relleno
     Parametros Salida :
             Valor del Campo Formateado
     Autor : Jorge Florencio
   ***************************************************************************/
 FUNCTION FIN_FN_FORMA_CAMPO(
  p_val_camp                       IN   VARCHAR2,
  p_tip_camp                       IN   NUMBER,
  p_lon_camp                       IN   NUMBER,
  p_for_camp                       IN   VARCHAR2,
  p_can_deci                       IN   NUMBER,
  p_ind_rell                       IN   VARCHAR2,
  p_val_rell                       IN   VARCHAR2 DEFAULT ' ')
 RETURN VARCHAR2;

      /**************************************************************************
     Descripcion       :  Funcion que realiza el SPLIT de una cadena
                                  en base a un separador
     Fecha Creacion    : 27/10/2010
     Parametros Entrada :
             Cadena de Texto
             Numero de Elemento
             Separador
     Parametros Salida :
           Cadena obtenido del SPLIT
     Autor : Jorge Florencio
   ***************************************************************************/
 FUNCTION FIN_FN_SPLIT_STRING(
  p_string                         IN       VARCHAR2,
  p_element                        IN       INTEGER,
  p_separator                      IN       VARCHAR2)
 RETURN VARCHAR2;

 PROCEDURE FIN_PR_GENER_ARCHI(
  p_cod_modu                       IN       fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN       fin_param_inter_detal.cod_inte%TYPE,
  p_num_lote                       IN       VARCHAR2,
  p_dir_ensa                       IN OUT   fin_param_inter_cabec.dir_ensa%TYPE,
  p_nom_arch                       IN OUT   VARCHAR2,
  p_handle                         OUT      utl_file.file_type);

 PROCEDURE FIN_PR_CARGA_DATOS_FIJOS(
  p_cod_modu                       IN       fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN       fin_param_inter_detal.cod_inte%TYPE,
  p_tab_camp                       IN OUT   t_tab_camp);

 PROCEDURE FIN_PR_CARGA_FUNCI_SINPA(
  p_cod_modu                       IN   fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_detal.cod_inte%TYPE,
  p_tab_camp                       IN OUT t_tab_camp);

 PROCEDURE FIN_PR_GENER_LINEA_INTER(
  p_handle                         IN       utl_file.file_type,
  p_tab_camp                       IN       t_tab_camp,
  p_val_sepa                       IN       fin_param_inter_cabec.val_sepa%TYPE,
  p_val_deli                       IN       fin_param_inter_cabec.val_deli%TYPE);


 PROCEDURE FIN_PR_GENER_INTER_ENTRA_MAGIC(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE,
  p_cod_erro                       OUT  VARCHAR2);

 PROCEDURE FIN_PR_GENER_INTER_SALID(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_val_bind_vari                  IN   VARCHAR2,
  p_handle                         IN   utl_file.file_type,
  p_val_cant_line                  OUT  NUMBER,
  p_dir_ensa                       IN   fin_param_inter_cabec.dir_ensa%TYPE DEFAULT NULL,    
  p_nom_arch                       IN   VARCHAR2,
  p_val_serv_ftp                   IN   fin_param_inter_cabec.ser_ftp%TYPE DEFAULT NULL,
  p_val_puer_ftp                   IN   fin_param_inter_cabec.pue_ftp%TYPE DEFAULT NULL,
  p_val_usua_ftp                   IN   fin_param_inter_cabec.usu_ftp%TYPE DEFAULT NULL,
  p_val_pass_ftp                   IN   fin_param_inter_cabec.pas_ftp%TYPE DEFAULT NULL,
  p_val_dire_ftp                   IN   fin_param_inter_cabec.dir_ftp%TYPE DEFAULT NULL);

 PROCEDURE FIN_PR_GENER_INTER_SALID_MAGIC(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE, 
  p_cod_usua                       IN   VARCHAR2,
  p_dir_ensa                       IN   fin_param_inter_cabec.dir_ensa%TYPE DEFAULT NULL,
  p_nom_arch                       IN   fin_inter_ejecu.nom_arch_proc%TYPE DEFAULT NULL,
  p_val_serv_ftp                   IN   fin_param_inter_cabec.ser_ftp%TYPE DEFAULT NULL,
  p_val_puer_ftp                   IN   fin_param_inter_cabec.pue_ftp%TYPE DEFAULT NULL,
  p_val_usua_ftp                   IN   fin_param_inter_cabec.usu_ftp%TYPE DEFAULT NULL,
  p_val_pass_ftp                   IN   fin_param_inter_cabec.pas_ftp%TYPE DEFAULT NULL,
  p_val_dire_ftp                   IN   fin_param_inter_cabec.dir_ftp%TYPE DEFAULT NULL);
  
 PROCEDURE FIN_PR_ENVIA_EMAIL(
  p_from                           IN       VARCHAR2,
  p_recipient                      IN       VARCHAR2,
  p_subject                        IN       VARCHAR2);

 PROCEDURE FIN_PR_ENVIA_EMAIL_ATTAC_CLOB(
  p_val_mens_from                  IN   VARCHAR2,
  p_val_mens_to                    IN   VARCHAR2,
  p_val_subj                       IN   VARCHAR2,
  p_val_text_mens                  IN   VARCHAR2,
  p_file_atta                      IN   CLOB,
  p_file_name                      IN   VARCHAR2);

 PROCEDURE SENDMAIL_ATT(
 MSG_FROM     VARCHAR2,
 MSG_TO       VARCHAR2,
 MSG_SUBJECT  VARCHAR2,
 MSG_TEXT     VARCHAR2,
 MSG_ATT      CLOB,
 ATT_FILENAME VARCHAR2);

END FIN_PKG_INTER;
/
CREATE OR REPLACE PACKAGE BODY FIN_PKG_INTER IS

 e_no_exis_arch                    EXCEPTION;
 gv_reco_trac                      FIN_PKG_GENER.error_rt;


 PROCEDURE FIN_PR_LISTA_DIREC ( directory IN VARCHAR2 )
 AS language JAVA name 'DirList.getList( java.lang.String )';

  /**************************************************************************
   Descripcion       : Guarda en la tabla fin_tempo_lista_direc los nombres
                       de los archivos ubicados en el directo de entrada o salida.
   Fecha Creacion    : 27/10/2010
   Parametros Entrada :
    p_dir_ensa : Directorio de entrada y salida.
    p_nom_arch : Prefijo de los archivos.
   Autor : Jorge Florencio
 ***************************************************************************/
 PROCEDURE FIN_PR_LISTA_ARCHI_DIREC(
  p_dir_ensa                       IN   VARCHAR2,
  p_nom_arch                       IN   VARCHAR2)
 IS
  lv_sql                           VARCHAR2(4000);
 BEGIN

  --Listar los archivos
  DELETE FROM imp_tmp_archi_direc;
  DELETE FROM fin_tempo_lista_direc;

  FIN_PR_LISTA_DIREC(p_dir_ensa);

  lv_sql := 'INSERT INTO fin_tempo_lista_direc ' ||
                         'SELECT DISTINCT nom_arch ' ||
                         'FROM imp_tmp_archi_direc ' ||
                         'WHERE nom_arch LIKE ''' || p_nom_arch || '%''';

  EXECUTE IMMEDIATE lv_sql;

 END FIN_PR_LISTA_ARCHI_DIREC;

  /**************************************************************************
   Descripcion       : Guarda en la tabla fin_tempo_lista_direc los nombres
                       de los archivos ubicados en el directo de entrada o salida.
   Fecha Creacion    : 27/10/2010
   Parametros Entrada :
    p_dir_ensa : Directorio de entrada y salida.
    p_nom_arch : Prefijo de los archivos.
   Autor : Jorge Florencio
 ***************************************************************************/
 PROCEDURE FIN_PR_LISTA_ARCHI_DIREC(
  p_dir_ensa                       IN   VARCHAR2)
 IS
  lv_sql                           VARCHAR2(4000);
 BEGIN

  --Listar los archivos
  DELETE FROM imp_tmp_archi_direc;
  DELETE FROM fin_tempo_lista_direc;

  FIN_PR_LISTA_DIREC(p_dir_ensa);

  lv_sql := 'INSERT INTO fin_tempo_lista_direc ' ||
                         'SELECT DISTINCT nom_arch ' ||
                         'FROM imp_tmp_archi_direc ';

  EXECUTE IMMEDIATE lv_sql;

 END FIN_PR_LISTA_ARCHI_DIREC;
 
 PROCEDURE FIN_PR_OBTIE_NUMER_LOTE(
  p_num_lote                       OUT   fin_inter_ejecu.num_lote%TYPE)
 IS

  lv_cont                          fin_numer_lote.cont%TYPE;
  lv_val_cade_fech                 fin_numer_lote.val_cade_fech%TYPE;
  lv_num_secu                      VARCHAR2(5);

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  lv_val_cade_fech:=to_char(trunc(SYSDATE),'YYYYMMDD');

  SELECT cnl.cont
  INTO lv_cont
  FROM fin_numer_lote cnl
  WHERE cnl.val_cade_fech=lv_val_cade_fech;

  UPDATE fin_numer_lote cnl
  SET cnl.cont = lv_cont + 1
  WHERE cnl.val_cade_fech = lv_val_cade_fech;
  
  lv_num_secu := TRIM(TO_CHAR(lv_cont,'0009'));

  p_num_lote := TO_NUMBER(concat(lv_val_cade_fech,lv_num_secu));

  COMMIT;

  EXCEPTION

   WHEN no_data_found THEN

    lv_cont:=001;
    lv_num_secu:= TRIM(TO_CHAR(lv_cont,'0009'));

    INSERT INTO fin_numer_lote VALUES (to_char(trunc(SYSDATE),'YYYYMMDD'),lv_cont+1);

    p_num_lote := concat(lv_val_cade_fech,lv_num_secu);

    COMMIT;

 END FIN_PR_OBTIE_NUMER_LOTE;

 FUNCTION FIN_FN_FORMA_CAMPO_INTER_ENTRA(
  p_val_camp                       IN   VARCHAR2,
  p_tip_camp                       IN   NUMBER,
  p_lon_camp                       IN   NUMBER,
  p_for_camp                       IN   VARCHAR2,
  p_can_deci                       IN   NUMBER,
  p_ind_rell                       IN   VARCHAR2,
  p_val_rell                       IN   VARCHAR2 DEFAULT ' ')
 RETURN VARCHAR2
 IS

  lv_camp_form                      VARCHAR2(500);
  lv_camp_fech                      DATE;
  lv_val_rell                       VARCHAR2(1);

 BEGIN

  lv_val_rell :=  NVL(p_val_rell,' ');

  IF p_val_camp is NOT  NULL THEN

   CASE

    WHEN p_tip_camp= 1 THEN

     IF p_ind_rell='D' THEN

      SELECT LPAD(SUBSTR(NVL(p_val_camp,lv_val_rell),1,p_lon_camp),p_lon_camp,lv_val_rell)
      INTO lv_camp_form
      FROM dual;

     ELSE

      SELECT SUBSTR(RPAD(NVL(p_val_camp,lv_val_rell),p_lon_camp,lv_val_rell),1,p_lon_camp)
      INTO lv_camp_form
      FROM dual;

     END IF;

    WHEN p_tip_camp = 2 THEN

      --dbms_output.put_line('Numero');
      --dbms_output.put_line(p_val_camp);

      SELECT to_number(p_val_camp)/POWER(10,p_can_deci)
      INTO lv_camp_form
      FROM dual;

    WHEN p_tip_camp = 3 THEN

      --dbms_output.put_line('Fecha');
      --dbms_output.put_line('Valor Campo ' || p_val_camp);
     --dbms_output.put_line('Formato Campo ' || p_for_camp);

      SELECT TO_DATE(p_val_camp,p_for_camp)
      INTO lv_camp_fech
      FROM dual;

     -- dbms_output.put_line('Valor Campo fecha ' || lv_camp_fech);

      SELECT to_date(lv_camp_fech,'DD/MM/YYYY')
      INTO lv_camp_form
      FROM DUAL;

      --dbms_output.put_line('Campo Formateado ' || lv_camp_form);

   ELSE

    NULL;

   END CASE;

  END IF;

 RETURN TRIM(lv_camp_form);

/*
   EXCEPTION

    WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);
           RAISE_application_error(-20123,
                              'ERROR CCC_FN_FORMA_CAMPO:  ' || p_cod_modu || '- ' || p_cod_inte ||
                         ls_sqlerrm);
*/

 END FIN_FN_FORMA_CAMPO_INTER_ENTRA;

 PROCEDURE FIN_PR_REGIS_INTER_ENTRA_EJECU(
  p_cod_modu                       IN   fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN   fin_inter_ejecu.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE,
  p_nom_arch                       IN   fin_inter_ejecu.nom_arch_proc%TYPE,
  p_cod_usu                        IN   seg_usuar.use_usua%TYPE,
  p_num_mult_lote                  IN   fin_inter_ejecu.num_mult_lote%TYPE)
 IS

      lv_reg_fin_inter_ejecu             fin_inter_ejecu%ROWTYPE;
      PRAGMA AUTONOMOUS_TRANSACTION;

   BEGIN

      lv_reg_fin_inter_ejecu.cod_modu:=p_cod_modu;
      lv_reg_fin_inter_ejecu.cod_inte:=p_cod_inte;

      SELECT pc.des_inte
      INTO lv_reg_fin_inter_ejecu.des_inte
      FROM fin_param_inter_cabec pc
      WHERE pc.cod_modu = p_cod_modu
      AND pc.cod_inte = p_cod_inte;

      lv_reg_fin_inter_ejecu.num_lote := p_num_lote;
      lv_reg_fin_inter_ejecu.nom_arch_proc := p_nom_arch;
      lv_reg_fin_inter_ejecu.ind_ejec := 'S';
      lv_reg_fin_inter_ejecu.reg_proc := 0;
      lv_reg_fin_inter_ejecu.reg_erro := 0;
      lv_reg_fin_inter_ejecu.fec_inic_proc := SYSDATE;
      lv_reg_fin_inter_ejecu.fec_fina_proc := NULL;
      lv_reg_fin_inter_ejecu.cod_esta_inte := 1;
      lv_reg_fin_inter_ejecu.des_log := NULL;
      lv_reg_fin_inter_ejecu.usu_proc := p_cod_usu;
      lv_reg_fin_inter_ejecu.num_mult_lote := p_num_mult_lote;

      INSERT INTO fin_inter_ejecu VALUES lv_reg_fin_inter_ejecu;

      COMMIT;

  END  FIN_PR_REGIS_INTER_ENTRA_EJECU;

 PROCEDURE FIN_PR_REGIS_INTER_SALID_EJECU(
  p_cod_modu                       IN       fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN       fin_inter_ejecu.cod_inte%TYPE,
  p_cod_usua                       IN       seg_usuar.use_usua%TYPE,
  p_num_lote                       IN       fin_inter_ejecu.num_lote%TYPE,
  p_dir_ensa                       IN OUT   fin_param_inter_cabec.dir_ensa%TYPE,
  p_nom_arch                       IN OUT   VARCHAR2,
  p_handle                         OUT      utl_file.file_type)
 IS

  lv_reg_fin_inter_ejecu             fin_inter_ejecu%ROWTYPE;

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  lv_reg_fin_inter_ejecu.cod_modu:=p_cod_modu;
  lv_reg_fin_inter_ejecu.cod_inte:=p_cod_inte;
    
  FIN_PR_GENER_ARCHI(p_cod_modu,p_cod_inte,p_num_lote,p_dir_ensa,p_nom_arch,p_handle);

  SELECT pc.des_inte
  INTO lv_reg_fin_inter_ejecu.des_inte
  FROM fin_param_inter_cabec pc
  WHERE pc.cod_modu = p_cod_modu
    AND pc.cod_inte = p_cod_inte;

  lv_reg_fin_inter_ejecu.num_lote := p_num_lote;
  lv_reg_fin_inter_ejecu.nom_arch_proc := p_nom_arch;
  lv_reg_fin_inter_ejecu.ind_ejec:='S';
  lv_reg_fin_inter_ejecu.reg_proc := 0;
  lv_reg_fin_inter_ejecu.reg_erro := 0;
  lv_reg_fin_inter_ejecu.fec_inic_proc:=SYSDATE;
  lv_reg_fin_inter_ejecu.fec_fina_proc:=NULL;
  lv_reg_fin_inter_ejecu.cod_esta_inte := 1;
  lv_reg_fin_inter_ejecu.des_log:=NULL;
  lv_reg_fin_inter_ejecu.usu_proc:=p_cod_usua;

  INSERT INTO fin_inter_ejecu VALUES lv_reg_fin_inter_ejecu;

  COMMIT;

 END FIN_PR_REGIS_INTER_SALID_EJECU;

   PROCEDURE FIN_PR_ACTUA_INTER_EJECU(
      p_cod_modu                   IN          fin_inter_ejecu.cod_modu%TYPE,
      p_cod_inte                     IN          fin_inter_ejecu.cod_inte%TYPE,
      p_num_lote                     IN          fin_inter_ejecu.num_lote%TYPE,
      p_des_log                       IN          fin_inter_ejecu.des_log%TYPE)
   IS

      PRAGMA AUTONOMOUS_TRANSACTION;

   BEGIN

      UPDATE fin_inter_ejecu f
      SET f.des_log= f.des_log || CHR(13) || to_char(SYSDATE,'HH:MI:SS') || ' : '|| p_des_log
      WHERE f.cod_modu = p_cod_modu
      AND f.cod_inte = p_cod_inte
      AND f.num_lote = p_num_lote;

      COMMIT;

   END  FIN_PR_ACTUA_INTER_EJECU;

   PROCEDURE FIN_PR_ACTUA_INTER_REGIS_PROCE(
      p_cod_modu                   IN          fin_inter_ejecu.cod_modu%TYPE,
      p_cod_inte                     IN          fin_inter_ejecu.cod_inte%TYPE,
      p_num_lote                     IN          fin_inter_ejecu.num_lote%TYPE,
      p_num_regi_proc            IN          fin_inter_ejecu.reg_proc%TYPE)
   IS

      PRAGMA AUTONOMOUS_TRANSACTION;

   BEGIN

      UPDATE fin_inter_ejecu f
      SET f.reg_proc= p_num_regi_proc
      WHERE f.cod_modu = p_cod_modu
      AND f.cod_inte = p_cod_inte
      AND f.num_lote = p_num_lote;

      COMMIT;

   END  FIN_PR_ACTUA_INTER_REGIS_PROCE;

 PROCEDURE FIN_PR_FINAL_INTER_ENTRA_EJECU(
  p_cod_modu                       IN   fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN   fin_inter_ejecu.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE,
  p_nom_arch_proc                  IN   fin_inter_ejecu.nom_arch_proc%TYPE,
  p_cod_esta_inte                  IN   fin_inter_ejecu.cod_esta_inte%TYPE,
  p_des_erro                       IN   fin_inter_ejecu.des_erro%TYPE DEFAULT  '')
 IS

  lv_ser_ftp                       fin_param_inter_cabec.ser_ftp%TYPE;
  lv_pue_ftp                       fin_param_inter_cabec.pue_ftp%TYPE;
  lv_usu_ftp                       fin_param_inter_cabec.usu_ftp%TYPE;
  lv_pas_ftp                       fin_param_inter_cabec.pas_ftp%TYPE;
  lv_dir_ftp                       fin_param_inter_cabec.dir_ftp%TYPE;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_dir_hist                      fin_param_inter_cabec.dir_hist%TYPE;
  lv_tabla_ensa                    fin_param_inter_cabec.nom_tabla_ensa%TYPE;
  lv_tabla_hist                    fin_param_inter_cabec.nom_tabla_hist%TYPE;
  lv_des_log                       VARCHAR2(2500);
  lv_sql                           VARCHAR2(200);

  PRAGMA AUTONOMOUS_TRANSACTION;

 BEGIN

  IF p_cod_esta_inte = '2' THEN

   SELECT pc.ser_ftp, pc.pue_ftp, pc.usu_ftp, pc.pas_ftp, pc.dir_ftp,
            pc.dir_ensa, pc.dir_hist, pc.nom_tabla_ensa , pc.nom_tabla_hist
   INTO
    lv_ser_ftp,lv_pue_ftp,lv_usu_ftp, lv_pas_ftp, lv_dir_ftp,
    lv_dir_ensa, lv_dir_hist, lv_tabla_ensa , lv_tabla_hist
   FROM fin_param_inter_cabec pc
   WHERE pc.cod_modu = p_cod_modu
     AND pc.cod_inte = p_cod_inte;

   lv_des_log:= 'Copiando el archivo ' || p_nom_arch_proc || ' a el directorio ' || lv_dir_hist;
   FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
   UTL_FILE.FCOPY(lv_dir_ensa,p_nom_arch_proc,lv_dir_hist,p_nom_arch_proc);

   lv_des_log:= 'Eliminando el archivo ' || p_nom_arch_proc || ' del directorio de entrada ' || lv_dir_ensa;
   FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
   UTL_FILE.fremove(lv_dir_ensa,p_nom_arch_proc);
   --GEN_PKG_FTP.ftp_pr_delet_ascii(lv_ser_ftp,lv_pue_ftp,lv_usu_ftp,lv_pas_ftp,lv_dir_ftp,p_nom_arch_proc);

   lv_des_log:= 'Copiando la informacion al Historico de la entidad de entrada';
   FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
   lv_sql:= ' INSERT INTO ' || lv_tabla_hist || ' SELECT * FROM ' || lv_tabla_ensa;
   EXECUTE IMMEDIATE lv_sql;

   lv_des_log:= 'La interfaz se ejecuto correctamente';
   FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);

  END IF;

  UPDATE fin_inter_ejecu p
  SET
   p.fec_fina_proc = SYSDATE,
   p.ind_ejec = 'N',
   p.cod_esta_inte = p_cod_esta_inte,
   p.des_erro = p_des_erro
  WHERE p.cod_modu = p_cod_modu
    AND p.cod_inte = p_cod_inte
    AND p.num_lote = p_num_lote;

  COMMIT;

 END  FIN_PR_FINAL_INTER_ENTRA_EJECU;

 PROCEDURE FIN_PR_FINAL_INTER_SALID_EJECU(
  p_cod_modu                       IN       fin_inter_ejecu.cod_modu%TYPE,
  p_cod_inte                       IN       fin_inter_ejecu.cod_inte%TYPE,
  p_num_lote                       IN       fin_inter_ejecu.num_lote%TYPE,
  p_cant_regi_proc                 IN       fin_inter_ejecu.reg_proc%TYPE,
  p_cod_esta_inte                  IN       fin_inter_ejecu.cod_esta_inte%TYPE,
  p_des_erro                       IN       fin_inter_ejecu.des_erro%TYPE DEFAULT  '')
 IS

  PRAGMA AUTONOMOUS_TRANSACTION;

  BEGIN

      UPDATE fin_inter_ejecu p
      SET
         p.fec_fina_proc = SYSDATE,
         p.ind_ejec = 'N',
         p.reg_proc = NVL(p_cant_regi_proc,0),
         p.cod_esta_inte = p_cod_esta_inte,
         p.des_erro = p_des_erro
       WHERE p.cod_modu = p_cod_modu
       AND p.cod_inte = p_cod_inte
       AND p.num_lote = p_num_lote;

       COMMIT;

   END  FIN_PR_FINAL_INTER_SALID_EJECU;

 PROCEDURE FIN_PR_CARGA_ARCHI_TO_TABLE(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE,
  p_table                          IN   VARCHAR2,
  p_dir                            IN   VARCHAR2,
  p_filename                       IN   VARCHAR2,
  p_reg_proc                       OUT  NUMBER)
 IS

  l_input                          utl_file.file_type;
  l_theCursor                      INTEGER DEFAULT dbms_sql.open_cursor;
  l_buffer                         VARCHAR2(4000);
  l_lastLine                       VARCHAR2(4000);
  l_status                         INTEGER;
  l_lonCamp                        INTEGER;
  lv_canDeci                       INTEGER;
  l_colCnt                         NUMBER DEFAULT 0;
  l_cnt                            NUMBER DEFAULT 0;
  l_regProc                        NUMBER DEFAULT 0;
  l_sep                            CHAR(1) DEFAULT NULL;
  l_errmsg                         VARCHAR2(4000);
  lv_valo_camp                     VARCHAR2(4000);
  lv_tipo_camp                     fin_param_inter_detal.val_tipo_camp%TYPE;
  lv_posi_inic                     NUMBER(12);
  lv_form_camp                     fin_param_inter_detal.val_form_camp%TYPE;
  lv_ind_rell                      fin_param_inter_detal.ind_rell%TYPE;
  lv_val_rell                      fin_param_inter_detal.val_rell%TYPE;

 BEGIN

  l_input := utl_file.fopen(p_dir, p_filename, 'r' );

  l_buffer := 'INSERT INTO ' || p_table || ' VALUES ( ';

  SELECT COUNT(1)
  INTO l_colCnt
  FROM fin_param_inter_detal d
  WHERE d.cod_modu = p_cod_modu
    AND d.cod_inte = p_cod_inte;

  -- Generar las Bind Variables
  FOR i IN 1 .. l_colCnt  LOOP
   l_buffer := l_buffer || l_sep || ':b'||i;
   l_sep    := ',';
  END LOOP;

  l_buffer := l_buffer || ')';

  dbms_sql.parse(  l_theCursor, l_buffer, dbms_sql.native );

  LOOP

   BEGIN
    utl_file.get_line( l_input, l_lastLine );
   EXCEPTION
    WHEN NO_DATA_FOUND then
     EXIT;
   END;

  l_regProc := l_regProc + 1;

  l_buffer := CONCAT(TO_CHAR(p_num_lote),l_lastLine);

  --dbms_output.put_line('Inicio Linea ' || l_regProc);
  --dbms_output.put_line(l_buffer);

  -- Obteniendo el valor de cada Bind Variable --
  lv_posi_inic := 1;

  FOR i in 1 .. l_colCnt LOOP

   SELECT d.num_long_camp, d.can_deci, d.val_tipo_camp, d.val_form_camp, d.ind_rell, d.val_rell
   INTO l_lonCamp , lv_canDeci , lv_tipo_camp, lv_form_camp, lv_ind_rell,lv_val_rell
   FROM fin_param_inter_detal d
   WHERE d.cod_modu = p_cod_modu
     AND d.cod_inte = p_cod_inte
     AND d.num_orde = i;

   --dbms_output.put_line(l_buffer);

    lv_valo_camp := FIN_FN_FORMA_CAMPO_INTER_ENTRA(
       substr(l_buffer, lv_posi_inic, l_lonCamp),
       lv_tipo_camp,
       l_lonCamp,
       lv_form_camp,
       lv_canDeci,
       lv_ind_rell,
       lv_val_rell);

    dbms_sql.bind_variable(l_theCursor, ':b'||i,lv_valo_camp);

    --dbms_output.put_line('Valor '  || i || ' ' || lv_valo_camp);
    --dbms_output.put_line(lv_valo_camp);

   lv_posi_inic := lv_posi_inic + l_lonCamp;

  END LOOP;

  BEGIN

   l_status := dbms_sql.execute(l_theCursor);
   l_cnt := l_cnt + 1;

--        exception
  --          when others then
    --            l_errmsg := sqlerrm;
      --          insert into badlog ( errm, data )
        --        values ( l_errmsg, l_lastLine );
  END;

 END LOOP;

    p_reg_proc := l_regProc;
    dbms_sql.close_cursor(l_theCursor);
    utl_file.fclose( l_input );

 END FIN_PR_CARGA_ARCHI_TO_TABLE;

 PROCEDURE FIN_PR_PROCE_INTER_ENTRA(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_nom_arch_proc                  IN   fin_inter_ejecu.nom_arch_proc%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote_multi                 IN   fin_inter_ejecu.num_lote%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

  l_input_write                    utl_file.file_type;
  lv_tip_inte                      fin_param_inter_cabec.tip_inte%TYPE;
  lv_dir_hist                      fin_param_inter_cabec.dir_hist%TYPE;
  lv_nom_tabl_ensa                 fin_param_inter_cabec.nom_tabla_ensa%TYPE;
  lv_nom_tabl_hist                 fin_param_inter_cabec.nom_tabla_hist%TYPE;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_val_sql                           VARCHAR2(200);
  lv_des_log                       VARCHAR2(4000);
  lv_nom_arch                      fin_param_inter_cabec.nom_arch%TYPE;
  lv_reg_proc                      NUMBER DEFAULT 0;
  lv_cant_arch                     NUMBER DEFAULT 0;

  e_arch_no_read_only              EXCEPTION;

 BEGIN

  FIN_PR_OBTIE_NUMER_LOTE(p_num_lote);

  FIN_PR_REGIS_INTER_ENTRA_EJECU(p_cod_modu,p_cod_inte,p_num_lote,p_nom_arch_proc,p_cod_usua,p_num_lote_multi);

  SELECT pc.tip_inte,pc.dir_ensa,pc.dir_hist,pc.nom_arch, pc.nom_tabla_ensa, pc.nom_tabla_hist
  INTO
   lv_tip_inte,lv_dir_ensa, lv_dir_hist,lv_nom_arch, lv_nom_tabl_ensa,lv_nom_tabl_hist
  FROM fin_param_inter_cabec pc
  WHERE pc.cod_modu = p_cod_modu
    AND pc.cod_inte = p_cod_inte;

  lv_des_log := 'Directorio de Entrada ' || lv_dir_ensa;
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);

  lv_des_log := 'Nombre de Archivo Procesado ' || p_nom_arch_proc;
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);

  lv_des_log:= 'Numero de Lote ' || p_num_lote;
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);

  --BEGIN
  --l_input_write := utl_file.fopen(lv_dir_ensa, p_nom_arch_proc, 'W' );
   --utl_file.fclose( l_input_write );
--  EXCEPTION
  -- WHEN OTHERS THEN
    --RAISE e_arch_no_read_only;
--  END;

  lv_des_log:= 'Cargando Archivo de Texto ' || p_nom_arch_proc || ' a la Tabla de Entrada ' || lv_nom_tabl_ensa;
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
  FIN_PR_CARGA_ARCHI_TO_TABLE(p_cod_modu,p_cod_inte,p_num_lote,lv_nom_tabl_ensa,lv_dir_ensa,p_nom_arch_proc,lv_reg_proc);

  FIN_PR_ACTUA_INTER_REGIS_PROCE(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_reg_proc);

  /*
  lv_des_log:= 'Copiando la informacion al Historico de la entidad de entrada';
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);

  IF lv_nom_tabl_hist IS NOT NULL THEN
   lv_val_sql := ' INSERT INTO ' || lv_nom_tabl_hist || ' SELECT * FROM ' || lv_nom_tabl_ensa;
   FIN_PKG_GENER.FIN_PR_EXECU_DINAM_SQL(lv_val_sql);
  END IF;

  lv_des_log:= 'Copiando archivo de texto al historico';
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
  UTL_FILE.FCOPY(lv_dir_ensa,p_nom_arch_proc,lv_dir_hist,p_nom_arch_proc);

  lv_des_log:= 'Eliminado el archivo de texto del directorio de entrada';
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
  UTL_FILE.fremove(lv_dir_ensa,p_nom_arch_proc);  
  */

  lv_des_log:= 'El archivo ha sido procesado con exito';
  FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);

  FIN_PR_FINAL_INTER_ENTRA_EJECU(p_cod_modu, p_cod_inte, p_num_lote,p_nom_arch_proc,2,NULL);

  EXCEPTION

   WHEN e_no_exis_arch THEN
    RAISE_application_error(-20123,
     'ERROR FIN_PR_PROCE_INTER_ENTRA: No existen archivos para procesar');

   WHEN e_arch_no_read_only THEN
    RAISE_application_error(-20123,
     'ERROR FIN_PR_PROCE_INTER_ENTRA: El Archivo se encuentra en Modo Escritura');

   WHEN OTHERS THEN

    FIN_PR_FINAL_INTER_ENTRA_EJECU(p_cod_modu, p_cod_inte, p_num_lote,NULL,9,dbms_utility.format_error_stack());

    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
    raise_application_error (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FIN_PR_PROCE_INTER_ENTRA;

 PROCEDURE FIN_PR_PROCE_INTER_SALID(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE,
  p_nom_arch                       IN   fin_param_inter_cabec.nom_arch%TYPE,
  p_val_bind_vari                  IN   VARCHAR2,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_val_serv_ftp                   IN   fin_param_inter_cabec.ser_ftp%TYPE DEFAULT NULL,
  p_val_puer_ftp                   IN   fin_param_inter_cabec.pue_ftp%TYPE DEFAULT NULL,
  p_val_usua_ftp                   IN   fin_param_inter_cabec.usu_ftp%TYPE DEFAULT NULL,
  p_val_pass_ftp                   IN   fin_param_inter_cabec.pas_ftp%TYPE DEFAULT NULL,
  p_val_dire_ftp                   IN   fin_param_inter_cabec.dir_ftp%TYPE DEFAULT NULL)
   IS

      lv_dir_ensa                 fin_param_inter_cabec.dir_ensa%TYPE;
      lv_dir_hist                  fin_param_inter_cabec.dir_hist%TYPE;
      lv_nom_arch                fin_param_inter_cabec.nom_arch%TYPE;
      lv_nom_tabla_ensa      fin_param_inter_cabec.nom_tabla_ensa%TYPE;
      lv_nom_tabla_hist       fin_param_inter_cabec.nom_tabla_hist%TYPE;
      lv_des_exar                bas_exten_archi.des_exar%TYPE;
      lv_ser_ftp                  fin_param_inter_cabec.ser_ftp%TYPE;
      lv_pue_ftp                  fin_param_inter_cabec.pue_ftp%TYPE;
      lv_usu_ftp                  fin_param_inter_cabec.usu_ftp%TYPE;
      lv_pas_ftp                  fin_param_inter_cabec.pas_ftp%TYPE;
      lv_dir_ftp                   fin_param_inter_cabec.dir_ftp%TYPE;
      lv_ind_envi_ftp_fijo  fin_param_inter_cabec.ind_envi_ftp_fijo%TYPE;
      lv_ind_envi_ftp_vari  fin_param_inter_cabec.ind_envi_ftp_vari%TYPE;
      lv_file_name              VARCHAR2(250);
      lv_cant_line                   NUMBER;
      lv_sql                            VARCHAR2(250);

   BEGIN

      SELECT
         c.dir_ensa,
         c.dir_hist,
         c.nom_arch,
         bea.des_exar,
         c.nom_tabla_ensa,
         c.nom_tabla_hist,
         c.ser_ftp,
         c.pue_ftp,
         c.usu_ftp,
         c.pas_ftp,
         c.dir_ftp,
         c.ind_envi_ftp_fijo,
         c.ind_envi_ftp_vari
      INTO
         lv_dir_ensa,
         lv_dir_hist,
         lv_nom_arch,
         lv_des_exar,
         lv_nom_tabla_ensa,
         lv_nom_tabla_hist,
         lv_ser_ftp,
         lv_pue_ftp,
         lv_usu_ftp,
         lv_pas_ftp,
         lv_dir_ftp,
         lv_ind_envi_ftp_fijo,
         lv_ind_envi_ftp_vari
      FROM
         fin_param_inter_cabec c,
         bas_exten_archi bea
     WHERE c.cod_modu = p_cod_modu
     AND c.cod_inte = p_cod_inte
     AND c.cod_exar = bea.cod_exar;

      IF p_nom_arch IS NOT NULL THEN
         lv_nom_arch := p_nom_arch;
      END IF;

      lv_file_name := lv_nom_arch || '.' || lv_des_exar;

      --Generar Archivo en el Directorio de Salida  --
      gv_des_log:= 'Generando el archivo : ' || lv_file_name || ' en : ' || lv_dir_ensa;
      FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);

      FIN_PKG_INTER.FIN_PR_EXPOR_INTER_TO_TEXT(p_cod_modu, p_cod_inte, p_val_bind_vari ,lv_file_name,lv_cant_line);

      gv_des_log:= 'Fin de la generacion del archivo con ' || lv_cant_line || ' registros.';
      FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);

      --Copiando a la Entidad Historica de Salida --
      gv_des_log:= 'Copiando la informacion a la  entidad historica de salida';
      FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);
      lv_sql:= ' INSERT INTO ' || lv_nom_tabla_hist || ' SELECT * FROM ' || lv_nom_tabla_ensa;
      EXECUTE IMMEDIATE lv_sql;

      -- Copiando el archivo al Historico de Salida
      gv_des_log:= 'Copiando el archivo ' || lv_file_name  || ' al historico : ' || lv_dir_hist;
      FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);
      UTL_FILE.FCOPY(lv_dir_ensa,lv_file_name,lv_dir_hist,lv_file_name);

      /*
      lv_des_log:= 'Eliminado archivo del directorio de entrada';
      FIN_PR_ACTUA_INTER_EJEC(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
      GEN_PKG_FTP.ftp_pr_delet_ascii(lv_ser_ftp,lv_pue_ftp,lv_usu_ftp,lv_pas_ftp,lv_dir_ftp,p_nom_arch_proc);
      */

      -- Zipear el archivo --
      --GEN_PKG_INTER_ARCHI.GEN_PR_COMPR_ZIP('/ssicc/temp/pe/es',lv_nom_arch);

     -- Envio a un Servidor FTP --
     IF lv_ind_envi_ftp_fijo = 1 THEN

        gv_des_log:= 'Enviando el archivo al FTP fijo ' || lv_ser_ftp;

        FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);
        GEN_PKG_FTP.FTP_PR_ENVIO_ASCCI(lv_ser_ftp,lv_pue_ftp,lv_usu_ftp,lv_pas_ftp,lv_dir_ensa,lv_file_name,p_val_dire_ftp,lv_file_name);

        gv_des_log:= 'Archivo enviado exitosamente';
        FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);
   END IF;

     IF lv_ind_envi_ftp_vari = 1 THEN

        gv_des_log:= 'Enviando el archivo al FTP variable ';
        FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);
        GEN_PKG_FTP.FTP_PR_ENVIO_ASCCI(p_val_serv_ftp,p_val_puer_ftp,p_val_usua_ftp,p_val_pass_ftp,lv_dir_ensa,lv_file_name,p_val_dire_ftp,lv_file_name);

        gv_des_log:= 'Se envio el archivo : ' || lv_file_name || ' al Servidor : ' || p_val_serv_ftp || ' Directorio : ' || p_val_dire_ftp;
        FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);

   END IF;

   IF (lv_ind_envi_ftp_fijo = 1 ) OR (lv_ind_envi_ftp_vari = 1) THEN

        gv_des_log:= 'Eliminado archivo del directorio de entrada ';
        FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);

        GEN_PKG_FTP.FTP_PR_DELET_ASCII(lv_ser_ftp,lv_pue_ftp,lv_usu_ftp,lv_pas_ftp,lv_dir_ftp,lv_file_name);
   END IF;

   /*
   EXCEPTION
      WHEN OTHERS THEN
          NULL;
     */
   END FIN_PR_PROCE_INTER_SALID;

 PROCEDURE FIN_PR_EXPOR_INTER_TO_TEXT(
  p_cod_modu              IN       fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                 IN       fin_param_inter_cabec.cod_inte%TYPE,
  p_val_bind_vari         IN        VARCHAR2,
  p_filename                  IN       VARCHAR2,
  p_val_cant_line           OUT   NUMBER)
 IS

  l_output              utl_file.file_type;
  l_theCursor         INTEGER DEFAULT dbms_sql.open_cursor;
  l_columnValue     VARCHAR2(4000);
  l_status               INTEGER;
  l_lon_camp          NUMBER;
  l_colCnt               NUMBER := 0;
  l_descTbl            dbms_sql.desc_tab;

  lv_tip_camp                      fin_param_inter_detal.val_tipo_camp%TYPE;
  lv_lon_camp                      fin_param_inter_detal.num_long_camp%TYPE;
  lv_for_camp                      fin_param_inter_detal.val_camp%TYPE;
  lv_can_deci                      fin_param_inter_detal.can_deci%TYPE;
  lv_ind_rell                      fin_param_inter_detal.ind_rell%TYPE;
  lv_val_rell                      fin_param_inter_detal.val_rell%TYPE;

  lv_nom_tabla_ensa                fin_param_inter_cabec.nom_tabla_ensa%TYPE;
  lv_val_sql                       fin_param_inter_cabec.val_sql%TYPE;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_dir_hist                      fin_param_inter_cabec.dir_hist%TYPE;
  lv_num_bind_vari                 fin_param_inter_cabec.num_bind_vari%TYPE;

 BEGIN

  p_val_cant_line:= 0;

  SELECT
   c.val_sql,
   c.dir_ensa,
   c.num_bind_vari,
   c.nom_tabla_ensa
  INTO
   lv_val_sql,
   lv_dir_ensa,
   lv_num_bind_vari,
   lv_nom_tabla_ensa
  FROM
   fin_param_inter_cabec c
  WHERE c.cod_modu = p_cod_modu
    AND c.cod_inte = p_cod_inte;

  IF lv_val_sql IS NULL THEN
   lv_val_sql:= 'SELECT * FROM ' || lv_nom_tabla_ensa;
  END IF;

  l_output := utl_file.fopen( lv_dir_ensa, p_filename, 'w' );

  --EXECUTE IMMEDIATE 'alter session set nls_date_format=''dd/mm/yyyy'' ';

  dbms_sql.parse(  l_theCursor,  lv_val_sql, dbms_sql.native );

  dbms_sql.describe_columns( l_theCursor, l_colCnt, l_descTbl );

  IF lv_num_bind_vari > 0 THEN

   FOR i IN 1 .. lv_num_bind_vari LOOP
    DBMS_SQL.BIND_VARIABLE(l_theCursor, ':' || i ,FIN_FN_SPLIT_STRING(p_val_bind_vari,i,';'));
   END LOOP;

  END IF;


  FOR i IN 1 .. l_colCnt LOOP
   --utl_file.put( l_output, l_descTbl(i).col_name);
   dbms_sql.define_column( l_theCursor, i, l_columnValue, 4000 );
  END LOOP;

  --utl_file.new_line( l_output);

  l_status := dbms_sql.execute(l_theCursor);

  WHILE ( dbms_sql.fetch_rows(l_theCursor) > 0 ) LOOP

   FOR i IN 1 .. l_colCnt LOOP

    SELECT d.val_tipo_camp,d.num_long_camp, d.val_form_camp, d.can_deci,d.ind_rell, d.val_rell
    INTO lv_tip_camp,lv_lon_camp,lv_for_camp, lv_can_deci,lv_ind_rell, lv_val_rell
    FROM fin_param_inter_detal d
    WHERE  d.cod_modu = p_cod_modu
      AND d.cod_inte = p_cod_inte
      AND d.num_orde = i;

    dbms_sql.column_value(l_theCursor, i, l_columnValue );

    l_columnValue := FIN_FN_FORMA_CAMPO(l_columnValue,lv_tip_camp,lv_lon_camp, lv_for_camp,lv_can_deci,lv_ind_rell,lv_val_rell);

    /*
    IF p_val_cant_line = 15 THEN
     dbms_output.put_line(l_columnValue);
    END IF;
    */

    utl_file.put( l_output, l_columnValue );

   END LOOP;

   utl_file.new_line( l_output );

   p_val_cant_line := p_val_cant_line + 1;

  END LOOP;

  dbms_sql.close_cursor(l_theCursor);
  utl_file.fclose( l_output );

  EXECUTE IMMEDIATE 'alter session set nls_date_format=''dd/mm/yyyy'' ';
/*
   EXCEPTION
     WHEN OTHERS THEN
         EXECUTE IMMEDIATE 'ALTER SESSION SET nls_date_format=''dd-MON-yy'' ';
      RAISE;
*/

 END FIN_PR_EXPOR_INTER_TO_TEXT;

   PROCEDURE FIN_PR_EXPOR_TABLE_TO_CSV(
      p_tname                      IN       VARCHAR2,
      p_dir                           IN       VARCHAR2,
      p_filename                  IN       VARCHAR2 )
   IS

      l_output                     utl_file.file_type;
      l_theCursor               INTEGER DEFAULT dbms_sql.open_cursor;
      l_columnValue            VARCHAR2(4000);
      l_status                      INTEGER;
      l_query                       VARCHAR2(1000)
      DEFAULT                   'select * from ' || p_tname;
      l_colCnt                      NUMBER := 0;
      l_separator                 VARCHAR2(1);
      l_descTbl                   dbms_sql.desc_tab;

   BEGIN

      l_output := utl_file.fopen( p_dir, p_filename, 'w' );

      EXECUTE IMMEDIATE 'alter session set nls_date_format=''dd-mon-yyyy hh24:mi:ss'' ';

      dbms_sql.parse(  l_theCursor,  l_query, dbms_sql.native );
      dbms_sql.describe_columns( l_theCursor, l_colCnt, l_descTbl );

      FOR i IN 1 .. l_colCnt LOOP
         utl_file.put( l_output, l_separator || '"' || l_descTbl(i).col_name || '"' );
         dbms_sql.define_column( l_theCursor, i, l_columnValue, 4000 );
         l_separator := ',';
      END LOOP;

      utl_file.new_line( l_output );

      l_status := dbms_sql.execute(l_theCursor);

      WHILE ( dbms_sql.fetch_rows(l_theCursor) > 0 ) LOOP
         l_separator := '';
         FOR i IN 1 .. l_colCnt LOOP
            dbms_sql.column_value( l_theCursor, i, l_columnValue );
            utl_file.put( l_output, l_separator || l_columnValue );
            l_separator := ',';
         END LOOP;

         utl_file.new_line( l_output );

      END LOOP;

      dbms_sql.close_cursor(l_theCursor);
      utl_file.fclose( l_output );

      EXECUTE IMMEDIATE 'alter session set nls_date_format=''dd-MON-yy'' ';


   EXCEPTION
     WHEN OTHERS THEN
         EXECUTE IMMEDIATE 'ALTER SESSION SET nls_date_format=''dd-MON-yy'' ';
      RAISE;

   END FIN_PR_EXPOR_TABLE_TO_CSV;

   PROCEDURE FIN_PR_EXPOR_SQL_TO_CSV(
      p_query                       IN       VARCHAR2,
      p_dir                           IN       VARCHAR2,
      p_filename                  IN       VARCHAR2 )
   IS

      l_output              utl_file.file_type;
      l_theCursor         INTEGER DEFAULT dbms_sql.open_cursor;
      l_columnValue     VARCHAR2(4000);
      l_status               INTEGER;
      l_colCnt               number := 0;
      l_separator         varchar2(1);
      l_descTbl            dbms_sql.desc_tab;

   BEGIN

      l_output := utl_file.fopen( p_dir, p_filename, 'w' );

      EXECUTE IMMEDIATE 'alter session set nls_date_format=''dd-mon-yyyy hh24:mi:ss'' ';

      dbms_sql.parse(  l_theCursor,  p_query, dbms_sql.native );
      dbms_sql.describe_columns( l_theCursor, l_colCnt, l_descTbl );

      FOR i IN 1 .. l_colCnt LOOP
         utl_file.put( l_output, l_separator || '"' || l_descTbl(i).col_name || '"' );
         dbms_sql.define_column( l_theCursor, i, l_columnValue, 4000 );
         l_separator := ',';
      END LOOP;

      utl_file.new_line( l_output );

      l_status := dbms_sql.execute(l_theCursor);

      WHILE ( dbms_sql.fetch_rows(l_theCursor) > 0 ) LOOP
         l_separator := '';
         FOR i IN 1 .. l_colCnt LOOP
            dbms_sql.column_value( l_theCursor, i, l_columnValue );
            utl_file.put( l_output, l_separator || l_columnValue );
            l_separator := ',';
         END LOOP;

         utl_file.new_line( l_output );

      END LOOP;

      dbms_sql.close_cursor(l_theCursor);
      utl_file.fclose( l_output );

      EXECUTE IMMEDIATE 'alter session set nls_date_format=''dd-mm-yy'' ';


   EXCEPTION
     WHEN OTHERS THEN
         EXECUTE IMMEDIATE 'ALTER SESSION SET nls_date_format=''dd-MON-yy'' ';
      RAISE;

   END FIN_PR_EXPOR_SQL_TO_CSV;

 FUNCTION FIN_FN_FORMA_CAMPO(
  p_val_camp                       IN   VARCHAR2,
  p_tip_camp                       IN   NUMBER,
  p_lon_camp                       IN   NUMBER,
  p_for_camp                       IN   VARCHAR2,
  p_can_deci                       IN   NUMBER,
  p_ind_rell                       IN   VARCHAR2,
  p_val_rell                       IN   VARCHAR2 DEFAULT ' ')
 RETURN VARCHAR2
 IS

  lv_camp_form                      VARCHAR2(500);
  lv_camp_fech                      DATE;
  lv_val_rell                       VARCHAR2(1);

 BEGIN

  lv_val_rell :=  NVL(p_val_rell,' ');

  IF TRIM(p_val_camp) IS NOT  NULL THEN

   CASE

    WHEN p_tip_camp= 1 THEN

     IF p_ind_rell='D' THEN

      SELECT LPAD(SUBSTR(NVL(p_val_camp,lv_val_rell),1,p_lon_camp),p_lon_camp,lv_val_rell)
      INTO lv_camp_form
      FROM dual;

     ELSE

      SELECT SUBSTR(RPAD(NVL(p_val_camp,lv_val_rell),p_lon_camp,lv_val_rell),1,p_lon_camp)
      INTO lv_camp_form
      FROM dual;

     END IF;

    WHEN p_tip_camp = 2 THEN

     IF p_can_deci IS NULL THEN

      SELECT TRIM(REPLACE(REPLACE(TO_CHAR(TO_NUMBER(p_val_camp),p_for_camp),'.',''),',',''))
      INTO lv_camp_form
      FROM dual;

     ELSE

      SELECT TRIM(REPLACE(TO_CHAR(TO_NUMBER(p_val_camp),p_for_camp),',','.'))
      INTO lv_camp_form
      FROM dual;

     END IF;

    WHEN p_tip_camp = 3 THEN

     --dbms_output.put_line(p_val_camp);
      -- Validacion que el parametro tiene un formato de fecha correcta --
      lv_camp_fech := p_val_camp;

      SELECT TO_CHAR(lv_camp_fech,p_for_camp)
      INTO lv_camp_form
      FROM dual;

    ELSE

      SELECT RPAD(' ',p_lon_camp,' ')
      INTO lv_camp_form
      FROM DUAL;

    END CASE;


   ELSE

    SELECT RPAD(' ',p_lon_camp,' ')
    INTO lv_camp_form
    FROM DUAL;

   END IF;

 RETURN  lv_camp_form;

/*
   EXCEPTION

    WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);
           RAISE_application_error(-20123,
                              'ERROR CCC_FN_FORMA_CAMPO:  ' || p_cod_modu || '- ' || p_cod_inte ||
                               ls_sqlerrm);
*/
   END FIN_FN_FORMA_CAMPO;

   FUNCTION FIN_FN_SPLIT_STRING
      (p_string                    IN       VARCHAR2,
        p_element                  IN       INTEGER,
        p_separator               IN       VARCHAR2)
   RETURN      VARCHAR2
   AS
      v_string     VARCHAR2(4000);
   BEGIN
      v_string := p_string || p_separator;

      FOR i IN 1 .. p_element - 1 LOOP
         v_string := SUBSTR(v_string,INSTR(v_string,p_separator)+1);
      END LOOP;
   RETURN SUBSTR(v_string,1,INSTR(v_string,p_separator)-1);

   END FIN_FN_SPLIT_STRING;

 PROCEDURE FIN_PR_GENER_ARCHI(
  p_cod_modu                       IN       fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN       fin_param_inter_detal.cod_inte%TYPE,
  p_num_lote                       IN       VARCHAR2,
  p_dir_ensa                       IN OUT   fin_param_inter_cabec.dir_ensa%TYPE,
  p_nom_arch                       IN OUT   VARCHAR2,
  p_handle                         OUT      utl_file.file_type)
 IS

  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_nom_arch                      VARCHAR2(50);

 BEGIN

  IF p_dir_ensa IS NULL THEN
  
   SELECT param.dir_ensa
   INTO lv_dir_ensa
   FROM fin_param_inter_cabec param
   WHERE param.cod_modu = p_cod_modu
     AND param.cod_inte = p_cod_inte;
   
   p_dir_ensa := lv_dir_ensa;
   
  ELSE
    
   lv_dir_ensa := p_dir_ensa; 
       
  END IF;
    
  IF p_nom_arch IS NULL THEN 
    
   SELECT param.nom_arch ||  p_num_lote ||'.TXT'
   INTO lv_nom_arch
   FROM fin_param_inter_cabec param
   WHERE param.cod_modu = p_cod_modu
     AND param.cod_inte = p_cod_inte;
   
   p_nom_arch := lv_nom_arch;
    
  ELSE
  
   lv_nom_arch := p_nom_arch;
     
  END IF;
    
  p_handle:= utl_file.fopen(lv_dir_ensa,lv_nom_arch,'W');
      
 END FIN_PR_GENER_ARCHI;

 FUNCTION FIN_FN_FORMA_CAMPO_CARAC(
  p_cod_modu                       IN   fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_detal.cod_inte%TYPE,
  p_val_camp                       IN   VARCHAR2,
  p_lon_camp                       IN   NUMBER,
  p_ind_rell                       IN   VARCHAR2,
  p_val_rell                       IN   VARCHAR2 DEFAULT ' ')
 RETURN VARCHAR2
 IS

  lv_camp_form VARCHAR2(500);

 BEGIN

  IF p_ind_rell='D' THEN

   SELECT LPAD(SUBSTR(NVL(p_val_camp,p_val_rell),1,p_lon_camp),p_lon_camp,p_val_rell)
   INTO lv_camp_form
   FROM dual;

  ELSE

   SELECT SUBSTR(RPAD(NVL(p_val_camp,p_val_rell),p_lon_camp,p_val_rell),1,p_lon_camp)
   INTO lv_camp_form
   FROM dual;

  END IF;

  RETURN  lv_camp_form;

 END FIN_FN_FORMA_CAMPO_CARAC;

 FUNCTION FIN_FN_FORMA_CAMPO_NUMER(
  p_cod_modu                       IN   fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_detal.cod_inte%TYPE,
  p_num_orde                       IN   NUMBER,
  p_imp                            IN   NUMBER)
 RETURN VARCHAR2
 IS

  lv_for_camp                      fin_param_inter_detal.val_form_camp%TYPE;
  lv_lon_camp                      fin_param_inter_detal.num_long_camp%TYPE;
  lv_ind_rell                      fin_param_inter_detal.ind_rell%TYPE;
  lv_val_rell                      fin_param_inter_detal.val_rell%TYPE;
  lv_camp_form                     VARCHAR2(255);

 BEGIN

  SELECT d.val_form_camp , d.num_long_camp,d.val_rell, d.ind_rell
  INTO lv_for_camp, lv_lon_camp,lv_val_rell, lv_ind_rell
  FROM fin_param_inter_detal d
  WHERE d.cod_modu = p_cod_modu
    AND d.cod_inte = p_cod_inte
    AND d.num_orde=p_num_orde;

  IF lv_ind_rell='D' THEN

   SELECT RPAD(SUBSTR(TRIM(TO_CHAR(p_imp,lv_for_camp)),1,lv_lon_camp),lv_lon_camp,lv_val_rell)
   INTO lv_camp_form
   FROM dual;

  ELSE

   SELECT LPAD(SUBSTR(TRIM(TO_CHAR(p_imp,lv_for_camp)),1,lv_lon_camp),lv_lon_camp,lv_val_rell)
   INTO lv_camp_form
   FROM dual;

  END IF;

  RETURN  lv_camp_form;

 END FIN_FN_FORMA_CAMPO_NUMER;



 FUNCTION FIN_FN_FORMA_CAMPO_FECHA(
  p_cod_modu                       IN   fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_detal.cod_inte%TYPE,
  p_num_orde                       IN   NUMBER,
  p_fecha                          IN   DATE)
 RETURN VARCHAR2
 IS

  lv_form_camp                 fin_param_inter_detal.val_form_camp%TYPE;
  lv_long_camp                 fin_param_inter_detal.num_long_camp%TYPE;
  lv_val_rell                      fin_param_inter_detal.val_rell%TYPE;
  lv_camp_form                     VARCHAR2(255);

 BEGIN

  IF TRIM(p_fecha) IS NOT NULL THEN

  SELECT d.val_form_camp , d.num_long_camp,d.val_rell
  INTO lv_form_camp, lv_long_camp,lv_val_rell
  FROM fin_param_inter_detal d
  WHERE d.cod_modu = p_cod_modu
    AND d.cod_inte = p_cod_inte
    AND d.num_orde = p_num_orde;

  SELECT RPAD(SUBSTR(TO_CHAR(p_fecha,lv_form_camp),1,lv_long_camp),lv_long_camp,lv_val_rell)
  INTO lv_camp_form
  FROM dual;

  ELSE

   SELECT RPAD(' ',lv_long_camp,' ')
   INTO lv_camp_form
   FROM DUAL;

  END IF;

  RETURN  lv_camp_form;

 END FIN_FN_FORMA_CAMPO_FECHA;

 PROCEDURE FIN_PR_CARGA_DATOS_FIJOS(
  p_cod_modu                       IN       fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN       fin_param_inter_detal.cod_inte%TYPE,
  p_tab_camp                       IN OUT   t_tab_camp)
 IS

 CURSOR c_inter(
  p_cod_modu         fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte         fin_param_inter_detal.cod_inte%TYPE)
 IS

  SELECT
   det.num_orde,
   det.val_camp,
   det.num_long_camp,
   det.ind_rell,
   det.val_rell,
   det.val_tipo_camp
  FROM fin_param_inter_detal det
  WHERE det.cod_modu = p_cod_modu
    AND det.cod_inte = p_cod_inte
    AND det.val_tipo_camp = 1
    AND det.ind_acti = 1
    AND det.ind_gene_manu = 0;

  lv_val_rell                       VARCHAR2(1);

 BEGIN

  FOR v_inter IN c_inter(p_cod_modu,p_cod_inte) LOOP

   lv_val_rell :=  NVL(v_inter.val_rell,' ');

   p_tab_camp(v_inter.num_orde):=FIN_FN_FORMA_CAMPO_CARAC(p_cod_modu,p_cod_inte,v_inter.val_camp,v_inter.num_long_camp,v_inter.ind_rell,lv_val_rell);

  END LOOP;

 END FIN_PR_CARGA_DATOS_FIJOS;

 PROCEDURE FIN_PR_CARGA_FUNCI_SINPA(
  p_cod_modu                       IN   fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_detal.cod_inte%TYPE,
  p_tab_camp                       IN OUT t_tab_camp)
 IS

 CURSOR c_inter(
  p_cod_modu                       fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       fin_param_inter_detal.cod_inte%TYPE)
 IS
  SELECT
   det.num_orde,
   det.val_tipo_camp,
   det.val_form_camp,
   det.val_camp,
   det.num_long_camp,
   det.ind_rell,
   det.val_rell,
   det.val_func_camp
  FROM fin_param_inter_detal det
  WHERE det.cod_modu = p_cod_modu
    AND det.cod_inte = p_cod_inte
    AND det.val_func_camp IS NOT NULL
    AND det.ind_para = 0
    AND det.ind_acti = 1
    AND det.ind_para = 0
    AND det.ind_gene_manu = 0;

  lv_sql               VARCHAR2(500);
  lv_camp_inic         VARCHAR2(500);
  lv_camp_fina         VARCHAR2(500);

 BEGIN

  FOR v_inter IN c_inter(p_cod_modu,p_cod_inte) LOOP

   lv_sql:='SELECT TO_CHAR(' || v_inter.val_func_camp ||  ',''' || v_inter.val_form_camp || ''')'  || ' FROM DUAL';

   EXECUTE IMMEDIATE lv_sql INTO lv_camp_inic;

   IF v_inter.ind_rell='D' THEN

    SELECT LPAD(SUBSTR(NVL(lv_camp_inic,v_inter.val_rell),1,v_inter.num_long_camp),v_inter.num_long_camp,v_inter.val_rell)
    INTO lv_camp_fina
    FROM dual;

   ELSE

    SELECT SUBSTR(RPAD(NVL(lv_camp_inic,v_inter.val_rell),v_inter.num_long_camp,v_inter.val_rell),1,v_inter.num_long_camp)
    INTO lv_camp_fina
    FROM dual;

   END IF;

   p_tab_camp(v_inter.num_orde):=lv_camp_fina;

  END LOOP;

 END FIN_PR_CARGA_FUNCI_SINPA;

 PROCEDURE FIN_PR_CARGA_FUNCI_CONPA(
  p_cod_modu                       IN   fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_detal.cod_inte%TYPE,
  p_tab_camp                       IN OUT t_tab_camp,
  p_oid_clie                       IN    mae_clien.oid_clie%TYPE)
 IS

 CURSOR c_inter(
  p_cod_modu         fin_param_inter_detal.cod_modu%TYPE,
  p_cod_inte         fin_param_inter_detal.cod_inte%TYPE)
 IS
  SELECT *
  FROM fin_param_inter_detal param
  WHERE param.cod_modu = p_cod_modu
    AND param.cod_inte = p_cod_inte
    AND param.val_func_camp IS NOT NULL
    AND param.ind_acti = 1
    AND param.ind_para = 1
    AND param.ind_gene_manu = 0;

  lv_sql VARCHAR2(500);

 BEGIN

  FOR v_inter IN  c_inter(p_cod_modu,p_cod_inte) LOOP

   --lv_sql:='SELECT RPAD(SUBSTR(' || v_inter.fun_camp || '(' || p_oid_clie || '),1,' || v_inter.lon_camp || '),' || v_inter.lon_camp || ',''' || v_inter.val_rell || ''') FROM DUAL';
   lv_sql:='SELECT FIN_PKG_INTER.FIN_FN_FORMA_CAMPO(''' || p_cod_modu  || ''',''' || p_cod_inte || ''',' || v_inter.val_camp || '(' || p_oid_clie || '),' || v_inter.num_long_camp || ',''' || v_inter.ind_rell || ''',''' || v_inter.val_rell || ''') FROM DUAL';
   EXECUTE IMMEDIATE lv_sql INTO p_tab_camp(v_inter.num_orde);

  END LOOP;

 END FIN_PR_CARGA_FUNCI_CONPA;

 PROCEDURE FIN_PR_GENER_LINEA_INTER(
  p_handle                         IN   utl_file.file_type,
  p_tab_camp                       IN   t_tab_camp,
  p_val_sepa                       IN   fin_param_inter_cabec.val_sepa%TYPE,
  p_val_deli                       IN   fin_param_inter_cabec.val_deli%TYPE)
 IS

  lv_line_sali   VARCHAR2(4000);

 BEGIN

  FOR x IN 1 .. p_tab_camp.COUNT LOOP

   IF p_tab_camp.EXISTS(x) THEN

    IF p_val_sepa IS NOT NULL THEN

     IF x > 1 THEN

      lv_line_sali:= lv_line_sali || CHR(p_val_sepa) || trim(to_char(p_tab_camp(x)));

     ELSE
      lv_line_sali:= trim(to_char(p_tab_camp(x)));
     END IF;

    ELSE
     lv_line_sali:= lv_line_sali || to_char(p_tab_camp(x));
    END IF;

   END IF;

  END LOOP;

  IF p_val_deli IS NOT NULL THEN
   lv_line_sali := lv_line_sali || chr(p_val_deli);
  END IF;

  utl_file.put_line(p_handle,lv_line_sali);  

 END FIN_PR_GENER_LINEA_INTER;

 PROCEDURE FIN_PR_GENER_INTER_ENTRA_MAGIC(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_cod_usua                       IN   seg_usuar.use_usua%TYPE,
  p_num_lote                       OUT  fin_inter_ejecu.num_lote%TYPE,
  p_cod_erro                       OUT  VARCHAR2)
 IS

  lv_nom_arch                      fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_num_lote_inte                 fin_inter_ejecu.num_lote%TYPE;
  lv_num_lote                      fin_inter_ejecu.num_lote%TYPE;
  lv_tip_inte                      fin_param_inter_cabec.tip_inte%TYPE;
  lv_dir_hist                      fin_param_inter_cabec.dir_hist%TYPE;
  lv_nom_tabl_ensa                 fin_param_inter_cabec.nom_tabla_ensa%TYPE;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_mult_lote                     fin_inter_ejecu.num_mult_lote%TYPE;
  lv_val_sql                       VARCHAR2(200);
  lv_des_log                       VARCHAR2(4000);
  lv_cod_erro                      VARCHAR2(4000);
  lv_des_erro                      VARCHAR2(4000);
  lv_reg_proc                      NUMBER DEFAULT 0;
  lv_cant_arch                     NUMBER DEFAULT 0;
  lv_cant_regi_proc                NUMBER(12) DEFAULT 0;
  lv_cant_inte_ejec                NUMBER DEFAULT 0;

  e_exis_inte_ejec                 EXCEPTION;

 CURSOR c_list_arch
 IS
 SELECT nom_arch
 FROM fin_tempo_lista_direc
 ORDER BY nom_arch ASC;

 BEGIN

  -- Buscar archivos en la interface de entrada --
  SELECT COUNT(*)
  INTO lv_cant_inte_ejec
  FROM fin_inter_ejecu f
  WHERE f.cod_modu = p_cod_modu
    AND f.cod_inte = p_cod_inte
    AND f.fec_fina_proc IS NULL;
    
  IF lv_cant_inte_ejec > 0 THEN
   RAISE e_exis_inte_ejec;
  END IF;

  FIN_PR_OBTIE_NUMER_LOTE(lv_mult_lote);

  p_num_lote := lv_mult_lote;

  SELECT pc.tip_inte,pc.dir_ensa,pc.dir_hist,pc.nom_arch, pc.nom_tabla_ensa
  INTO
   lv_tip_inte,lv_dir_ensa, lv_dir_hist,lv_nom_arch, lv_nom_tabl_ensa
  FROM fin_param_inter_cabec pc
  WHERE pc.cod_modu = p_cod_modu
    AND pc.cod_inte = p_cod_inte;

  lv_val_sql := ' DELETE FROM ' || lv_nom_tabl_ensa;
  FIN_PKG_GENER.FIN_PR_EXECU_DINAM_SQL(lv_val_sql);

  FIN_PR_LISTA_ARCHI_DIREC(lv_dir_ensa,lv_nom_arch);

  SELECT COUNT(*)
  INTO lv_cant_arch
  FROM fin_tempo_lista_direc;

  IF lv_cant_arch = 0 THEN
   RAISE e_no_exis_arch;
  END IF;

  --FIN_PKG_GENER.FIN_PR_TRUNC_TABLE(lv_nom_tabl_ensa);

  FOR v_list_arch IN c_list_arch LOOP

   FIN_PR_PROCE_INTER_ENTRA(p_cod_modu,p_cod_inte,v_list_arch.nom_arch,p_cod_usua,lv_mult_lote,lv_num_lote,lv_cod_erro );
   COMMIT;
  END LOOP;

 EXCEPTION

  WHEN e_no_exis_arch THEN
   ROLLBACK; 
   p_cod_erro := 'procesoFINGeneracionInterfaceEntrada.erro.no.exis.arch';
   
  WHEN e_exis_inte_ejec THEN
   ROLLBACK; 
   p_cod_erro := 'procesoFINGeneracionInterfaceEntrada.erro.exis.inte.ejec';

  WHEN OTHERS THEN
   gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
   raise_application_error (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );

 END FIN_PR_GENER_INTER_ENTRA_MAGIC;

 PROCEDURE FIN_PR_GENER_INTER_SALID(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_val_bind_vari                  IN   VARCHAR2,
  p_handle                         IN   utl_file.file_type,
  p_val_cant_line                  OUT  NUMBER,
  p_dir_ensa                       IN   fin_param_inter_cabec.dir_ensa%TYPE DEFAULT NULL,    
  p_nom_arch                       IN   VARCHAR2,
  p_val_serv_ftp                   IN   fin_param_inter_cabec.ser_ftp%TYPE DEFAULT NULL,
  p_val_puer_ftp                   IN   fin_param_inter_cabec.pue_ftp%TYPE DEFAULT NULL,
  p_val_usua_ftp                   IN   fin_param_inter_cabec.usu_ftp%TYPE DEFAULT NULL,
  p_val_pass_ftp                   IN   fin_param_inter_cabec.pas_ftp%TYPE DEFAULT NULL,
  p_val_dire_ftp                   IN   fin_param_inter_cabec.dir_ftp%TYPE DEFAULT NULL)
 IS

  l_descTbl                        dbms_sql.desc_tab;
  lv_nom_tabla_ensa                fin_param_inter_cabec.nom_tabla_ensa%TYPE;
  lv_nom_tabla_hist                fin_param_inter_cabec.nom_tabla_hist%TYPE;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE;
  lv_dir_hist                      fin_param_inter_cabec.dir_hist%TYPE;
  lv_num_bind_vari                 fin_param_inter_cabec.num_bind_vari%TYPE;
  lv_val_sepa                      fin_param_inter_cabec.val_sepa%TYPE;
  lv_val_deli                      fin_param_inter_cabec.val_deli%TYPE;
  lv_val_sql                       fin_param_inter_cabec.val_sql%TYPE;
  lv_ind_comp                      fin_param_inter_cabec.ind_comp%TYPE;
  lv_dire_ensa_copi                fin_param_inter_cabec.dir_ensa%TYPE;
  lv_file_name                     fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_nom_arch_zip                  fin_inter_ejecu.nom_arch_proc%TYPE;
  lv_handle                        utl_file.file_type;
  lv_tab_camp                      fin_pkg_inter.t_tab_camp;

  l_theCursor                      INTEGER DEFAULT dbms_sql.open_cursor;
  l_columnValue                    VARCHAR2(4000);
  l_status                         INTEGER;

  l_colCnt                         NUMBER := 0;
  i                                NUMBER(12);
  lv_cant                          NUMBER(12);

  CURSOR c_camp_manu
  IS
   SELECT
    num_orde,
    val_tipo_camp,
    num_long_camp,
    val_form_camp,
    can_deci,
    ind_rell,
    val_rell
   FROM
    (SELECT d.num_orde,d.val_tipo_camp,d.num_long_camp, d.val_form_camp,d.can_deci,d.ind_rell, d.val_rell
     FROM fin_param_inter_detal d
     WHERE d.cod_modu = p_cod_modu
       AND d.cod_inte = p_cod_inte
       AND d.ind_gene_manu = 1
       ORDER BY 1 ASC);

 BEGIN
  
 lv_file_name := p_nom_arch;
 
  p_val_cant_line:= 0;

  SELECT
   c.val_sql,
   c.dir_ensa,
   c.dir_hist,
   c.num_bind_vari,
   c.nom_tabla_ensa,
   c.nom_tabla_hist,
   c.val_sepa,
   c.val_deli,
   c.ind_comp,
   c.val_dire_ensa_copi
  INTO
   lv_val_sql,
   lv_dir_ensa,
   lv_dir_hist,
   lv_num_bind_vari,
   lv_nom_tabla_ensa,
   lv_nom_tabla_hist,
   lv_val_sepa,
   lv_val_deli,
   lv_ind_comp,
   lv_dire_ensa_copi
  FROM
   fin_param_inter_cabec c
  WHERE c.cod_modu = p_cod_modu
    AND c.cod_inte = p_cod_inte;
  
  IF p_dir_ensa IS NOT NULL THEN
    
   lv_dir_ensa := p_dir_ensa;
      
  END IF;
    
  IF lv_val_sql IS NULL THEN
   lv_val_sql:= 'SELECT * FROM ' || lv_nom_tabla_ensa;
  END IF;

  --dbms_output.put_line(lv_val_sql);

  dbms_sql.parse(  l_theCursor,  lv_val_sql, dbms_sql.native );

  dbms_sql.describe_columns( l_theCursor, l_colCnt, l_descTbl );

  IF lv_num_bind_vari > 0 THEN

   FOR i IN 1 .. lv_num_bind_vari LOOP
    DBMS_SQL.BIND_VARIABLE(l_theCursor, ':' || i ,FIN_FN_SPLIT_STRING(p_val_bind_vari,i,';'));
   END LOOP;

  END IF;


  FOR i IN 1 .. l_colCnt LOOP

   dbms_sql.define_column( l_theCursor, i, l_columnValue, 4000 );

  END LOOP;

  l_status := dbms_sql.execute(l_theCursor);

  -- Recorriendo el Cursor
  WHILE ( dbms_sql.fetch_rows(l_theCursor) > 0 ) LOOP

   i := 0;

   FOR v_camp_manu IN c_camp_manu LOOP

    i:= i + 1;

    --dbms_output.put_line(i || ' ' || l_columnValue);

    dbms_sql.column_value(l_theCursor, i, l_columnValue );

    l_columnValue := FIN_FN_FORMA_CAMPO(l_columnValue,v_camp_manu.val_tipo_camp,v_camp_manu.num_long_camp,v_camp_manu.val_form_camp, v_camp_manu.can_deci,v_camp_manu.ind_rell,v_camp_manu.val_rell);

    --dbms_output.put_line(i || ' ' || l_columnValue);

    lv_tab_camp(v_camp_manu.num_orde):= l_columnValue;

   END LOOP;

   SELECT COUNT(*)
   INTO lv_cant
   FROM fin_param_inter_detal det
   WHERE det.cod_modu = p_cod_modu
     AND det.cod_inte = p_cod_inte
     AND det.val_tipo_camp = 1
     AND det.ind_acti = 1
     AND det.ind_gene_manu = 0;

   IF lv_cant > 0 THEN
    FIN_PKG_INTER.FIN_PR_CARGA_DATOS_FIJOS(p_cod_modu,p_cod_inte,lv_tab_camp);
   END IF;

   FIN_PKG_INTER.FIN_PR_GENER_LINEA_INTER(p_handle,lv_tab_camp,lv_val_sepa,lv_val_deli);


   p_val_cant_line := p_val_cant_line + 1;

  END LOOP;

  lv_handle:=p_handle;

  dbms_sql.close_cursor(l_theCursor);
  utl_file.fclose(lv_handle);

  --lv_des_log:= 'Copiando la informacion al Historico de la entidad de entrada';
  --FIN_PR_ACTUA_INTER_EJEC(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
  
  --dbms_output.put_line('Tabla Historica ' || lv_nom_tabla_hist);
  
  IF lv_nom_tabla_hist IS NOT NULL THEN
   lv_val_sql := ' INSERT INTO ' || lv_nom_tabla_hist || ' SELECT * FROM ' || lv_nom_tabla_ensa;
   FIN_PKG_GENER.FIN_PR_EXECU_DINAM_SQL(lv_val_sql);
  END IF;

  lv_val_sql := ' DELETE FROM ' || lv_nom_tabla_ensa;
  FIN_PKG_GENER.FIN_PR_EXECU_DINAM_SQL(lv_val_sql);

  --lv_des_log:= 'Copiando archivo al historico';
  --FIN_PR_ACTUA_INTER_EJEC(p_cod_modu ,p_cod_inte ,p_num_lote ,lv_des_log);
      
  UTL_FILE.FCOPY(lv_dir_ensa,p_nom_arch,lv_dir_hist,p_nom_arch);
  
  -- Compression --
  IF lv_ind_comp=1 THEN
    --utl_file.frename(lv_dir_ensa,p_nom_arch,lv_dir_ensa,p_nom_arch || '.TMP');
    --GEN_PKG_INTER_ARCHI.GEN_PR_COMPR_ZIP(lv_dir_ensa,p_nom_arch);     
   FIN_PKG_ZIP.FIN_PR_ZIP_ARCHI(lv_dir_ensa,p_nom_arch);
   
   IF lv_dire_ensa_copi IS NOT NULL THEN
 
    lv_nom_arch_zip := SUBSTR(p_nom_arch,1,LENGTH(p_nom_arch)-3) || 'ZIP';            
    UTL_FILE.FCOPY(lv_dir_ensa,lv_nom_arch_zip,lv_dire_ensa_copi,lv_nom_arch_zip); 
    
   END IF;
   
  END IF;
  --
  
  --Envio FTP --
  IF p_val_serv_ftp is NOT NULL THEN
   gv_des_log:= 'Enviando el archivo al FTP variable ';
   --FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,lv_num_lote ,gv_des_log);
      
   GEN_PKG_FTP.FTP_PR_ENVIO_ASCCI(p_val_serv_ftp,p_val_puer_ftp,p_val_usua_ftp,p_val_pass_ftp,lv_dir_ensa,lv_file_name,p_val_dire_ftp,lv_file_name);

   gv_des_log:= 'Se envio el archivo : ' || lv_file_name || ' al Servidor : ' || p_val_serv_ftp || ' Directorio : ' || p_val_dire_ftp;
   --FIN_PR_ACTUA_INTER_EJECU(p_cod_modu ,p_cod_inte ,p_num_lote ,gv_des_log);
  END IF;
        
   --EXECUTE IMMEDIATE 'alter session set nls_date_format=''dd/mm/yyyy'' ';

 END FIN_PR_GENER_INTER_SALID;

 PROCEDURE FIN_PR_GENER_INTER_SALID_MAGIC(
  p_cod_modu                       IN   fin_param_inter_cabec.cod_modu%TYPE,
  p_cod_inte                       IN   fin_param_inter_cabec.cod_inte%TYPE,
  p_num_lote                       IN   fin_inter_ejecu.num_lote%TYPE, 
  p_cod_usua                       IN   VARCHAR2,
  p_dir_ensa                       IN   fin_param_inter_cabec.dir_ensa%TYPE DEFAULT NULL,
  p_nom_arch                       IN   fin_inter_ejecu.nom_arch_proc%TYPE DEFAULT NULL,
  p_val_serv_ftp                   IN   fin_param_inter_cabec.ser_ftp%TYPE DEFAULT NULL,
  p_val_puer_ftp                   IN   fin_param_inter_cabec.pue_ftp%TYPE DEFAULT NULL,
  p_val_usua_ftp                   IN   fin_param_inter_cabec.usu_ftp%TYPE DEFAULT NULL,
  p_val_pass_ftp                   IN   fin_param_inter_cabec.pas_ftp%TYPE DEFAULT NULL,
  p_val_dire_ftp                   IN   fin_param_inter_cabec.dir_ftp%TYPE DEFAULT NULL)
 IS

  lv_handle                        utl_file.file_type;
  lv_nom_arch                      fin_inter_ejecu.nom_arch_proc%TYPE := p_nom_arch;
  lv_dir_ensa                      fin_param_inter_cabec.dir_ensa%TYPE:= p_dir_ensa;
  lv_cant_regi_proc                NUMBER(12);
  lv_des_erro                      VARCHAR2(4000);

 BEGIN

   FIN_PR_REGIS_INTER_SALID_EJECU(p_cod_modu,p_cod_inte,p_cod_usua,p_num_lote,lv_dir_ensa,lv_nom_arch,lv_handle);
   FIN_PR_GENER_INTER_SALID(p_cod_modu,p_cod_inte,0,lv_handle,lv_cant_regi_proc,lv_dir_ensa,lv_nom_arch,p_val_serv_ftp,p_val_puer_ftp,p_val_usua_ftp,p_val_pass_ftp,p_val_dire_ftp);
   FIN_PR_FINAL_INTER_SALID_EJECU(p_cod_modu,p_cod_inte,p_num_lote,lv_cant_regi_proc,2);

/*
 EXCEPTION
  WHEN OTHERS THEN
    gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;
    gv_reco_trac := FIN_PKG_GENER.FIN_FN_OBTIE_LINEA_ERROR(DBMS_UTILITY.format_error_backtrace);
    raise_application_error (-20000,
                             ' *** Error ' || SQLERRM  ||
                             ' *** encontrado en la linea ' || gv_reco_trac.line_number ||
                             ' en el programa ' ||
                             gv_reco_trac.program_owner || '.' || gv_reco_trac.program_name );
*/
 END FIN_PR_GENER_INTER_SALID_MAGIC;


 PROCEDURE FIN_PR_ENVIA_EMAIL(
  p_from                           IN VARCHAR2,
  p_recipient                      IN VARCHAR2,
  p_subject                        IN VARCHAR2)
 IS

  lv_Mail_Host                     VARCHAR2(30) := 'penot09';
  lv_Mail_Conn                     utl_smtp.Connection;
  lv_crlf                          VARCHAR2(2)  := chr(13)||chr(10);

 BEGIN

  lv_Mail_Conn := utl_smtp.Open_Connection(lv_mail_host, 25);
  utl_smtp.Helo(lv_mail_conn, lv_mail_host);
  utl_smtp.Mail(lv_mail_conn, p_From);
  utl_smtp.Rcpt(lv_mail_conn, p_Recipient);
  utl_smtp.Data(lv_mail_Conn,
   'Date: '   || to_char(sysdate, 'Dy, DD Mon YYYY hh24:mi:ss') || lv_crlf ||
   'From: '   || p_From || lv_crlf ||
   'Subject: '|| p_Subject || lv_crlf ||
   'To: '     || p_Recipient || lv_crlf ||
  lv_crlf ||
   'some message text'|| lv_crlf ||  -- Message body
   'more message text'|| lv_crlf
  );
  utl_smtp.Quit(lv_mail_conn);

 EXCEPTION

  WHEN utl_smtp.Transient_Error OR utl_smtp.Permanent_Error then
   raise_application_error(-20000, 'Unable to send mail: '||sqlerrm);

 END FIN_PR_ENVIA_EMAIL;

 PROCEDURE FIN_PR_ENVIA_EMAIL_ATTAC_CLOB(
  p_val_mens_from                  IN   VARCHAR2,
  p_val_mens_to                    IN   VARCHAR2,
  p_val_subj                       IN   VARCHAR2,
  p_val_text_mens                  IN   VARCHAR2,
  p_file_atta                      IN   CLOB,
  p_file_name                      IN   VARCHAR2)
 IS


  lv_mailhost                      VARCHAR2(50) := 'penot09';
  lv_port                          NUMBER(2) := 25;
  lv_helo                          VARCHAR2(50) := 'localhost';

  lv_conn                          UTL_TCP.CONNECTION;
  lv_rc                            INTEGER;
  lv_crlf                          VARCHAR2(2) := CHR(13) || CHR(10);
  lv_val_mesg                      VARCHAR2(32767);

  lv_buffer_size                   CONSTANT PLS_INTEGER := 4000;
  lv_buffer_next                   PLS_INTEGER := 4000;
  lv_body_buffer                   VARCHAR2(8000 CHAR);

 BEGIN

  lv_conn := UTL_TCP.OPEN_CONNECTION(lv_mailhost, lv_port); -- open the SMTP port

  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'HELO ' || lv_helo);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'MAIL FROM: ' || p_val_mens_from); ----- MAIL BOX SENDING THE EMAIL
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'RCPT TO: ' || p_val_mens_to); ----- MAIL BOX RECIEVING THE EMAIL
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'DATA'); ----- EMAIL MESSAGE BODY START
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Date: ' || TO_CHAR(SYSDATE, 'dd Mon yy hh24:mi:ss'));
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'From: ' || p_val_mens_from);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'MIME-Version: 1.0');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'To: ' || p_val_mens_to);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Subject: ' || p_val_subj);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Type: multipart/mixed;'); ----- INDICATES THAT THE BODY CONSISTS OF MORE THAN ONE PART
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, ' boundary="-----SECBOUND"'); ----- SEPERATOR USED TO SEPERATE THE BODY PARTS
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn); ----- DO NOT REMOVE THIS BLANK LINE - PART OF MIME STANDARD
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, '-------SECBOUND');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Type: text/html'); ----- 1ST BODY PART. EMAIL TEXT MESSAGE
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Transfer-Encoding: 7bit');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, p_val_text_mens); ----- TEXT OF EMAIL MESSAGE
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, '-------SECBOUND');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Type: text/plain;'); ----- 2ND BODY PART.
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, ' name="' || p_file_name || '"');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Transfer_Encoding: 7bit');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Disposition: attachment;'); ----- INDICATES THAT THIS IS AN ATTACHMENT
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, ' filename="' || p_file_name || '"'); ----- SUGGESTED FILE NAME FOR ATTACHMENT
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn);

 --CLOB
  FOR i IN 0 .. FLOOR(DBMS_LOB.GETLENGTH(p_file_atta) / lv_buffer_size) LOOP

   DBMS_LOB.READ(p_file_atta,lv_buffer_next,
    i * lv_buffer_size + 1,
    lv_body_buffer);

   lv_rc := UTL_TCP.WRITE_TEXT(lv_conn, lv_body_buffer);

  END LOOP;

  lv_rc := UTL_TCP.WRITE_LINE(lv_conn);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, '.'); ----- EMAIL MESSAGE BODY END
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'QUIT'); ----- ENDS EMAIL TRANSACTION

  UTL_TCP.CLOSE_CONNECTION(lv_conn); ----- CLOSE SMTP PORT CONNECTION

 EXCEPTION
  WHEN OTHERS THEN
   RAISE;
 END FIN_PR_ENVIA_EMAIL_ATTAC_CLOB;

 PROCEDURE SENDMAIL_ATT(
 MSG_FROM     VARCHAR2,
 MSG_TO       VARCHAR2,
 MSG_SUBJECT  VARCHAR2,
 MSG_TEXT     VARCHAR2,
 MSG_ATT      CLOB,
 ATT_FILENAME VARCHAR2)

 IS

 lv_mailhost                       VARCHAR2(50) := 'penot09';
 lv_port                           NUMBER(2) := 25;
 lv_helo                           VARCHAR2(50) := 'localhost';

 lv_conn                           UTL_TCP.CONNECTION;
 lv_rc                             INTEGER;
 CRLF                              VARCHAR2(2) := CHR(13) || CHR(10);
 MESG                              VARCHAR2(32767);

 V_BUFFER_SIZE                     CONSTANT PLS_INTEGER := 4000;
 V_BUFFER_NEXT                     PLS_INTEGER := 4000;
 V_BODY_BUFFER                     VARCHAR2(8000 CHAR);

 BEGIN

  lv_conn := UTL_TCP.OPEN_CONNECTION(lv_mailhost, lv_port); -- open the SMTP port
  --DBMS_OUTPUT.PUT_LINE(UTL_TCP.GET_LINE(lv_conn, TRUE));
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'HELO ' || lv_helo);
  --DBMS_OUTPUT.PUT_LINE(UTL_TCP.GET_LINE(lv_conn, TRUE));
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'MAIL FROM: ' || MSG_FROM); ----- MAIL BOX SENDING THE EMAIL
  --DBMS_OUTPUT.PUT_LINE(UTL_TCP.GET_LINE(lv_conn, TRUE));
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'RCPT TO: ' || MSG_TO); ----- MAIL BOX RECIEVING THE EMAIL
  --DBMS_OUTPUT.PUT_LINE(UTL_TCP.GET_LINE(lv_conn, TRUE));
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'DATA'); ----- EMAIL MESSAGE BODY START
  --DBMS_OUTPUT.PUT_LINE(UTL_TCP.GET_LINE(lv_conn, TRUE));
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn,
  'Date: ' ||
  TO_CHAR(SYSDATE, 'dd Mon yy hh24:mi:ss'));
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'From: ' || MSG_FROM);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'MIME-Version: 1.0');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'To: ' || MSG_TO);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Subject: ' || MSG_SUBJECT);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Type: multipart/mixed;'); ----- INDICATES THAT THE BODY CONSISTS OF MORE THAN ONE PART
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, ' boundary="-----SECBOUND"'); ----- SEPERATOR USED TO SEPERATE THE BODY PARTS
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn); ----- DO NOT REMOVE THIS BLANK LINE - PART OF MIME STANDARD
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, '-------SECBOUND');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Type: text/html'); ----- 1ST BODY PART. EMAIL TEXT MESSAGE
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Transfer-Encoding: 7bit');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, MSG_TEXT); ----- TEXT OF EMAIL MESSAGE
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn);
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, '-------SECBOUND');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Type: text/plain;'); ----- 2ND BODY PART.
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, ' name="' || ATT_FILENAME || '"');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Transfer_Encoding: 7bit');
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'Content-Disposition: attachment;'); ----- INDICATES THAT THIS IS AN ATTACHMENT
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn, ' filename="' || ATT_FILENAME || '"'); ----- SUGGESTED FILE NAME FOR ATTACHMENT
  lv_rc := UTL_TCP.WRITE_LINE(lv_conn);

  --CLOB
  FOR I IN 0 .. FLOOR(DBMS_LOB.GETLENGTH(MSG_ATT) / V_BUFFER_SIZE) LOOP
   DBMS_LOB.READ(MSG_ATT,
    V_BUFFER_NEXT,
    I * V_BUFFER_SIZE + 1,
    V_BODY_BUFFER);

   lv_rc := UTL_TCP.WRITE_TEXT(lv_conn, V_BODY_BUFFER);

 END LOOP;

 lv_rc := UTL_TCP.WRITE_LINE(lv_conn);
 lv_rc := UTL_TCP.WRITE_LINE(lv_conn, '.'); ----- EMAIL MESSAGE BODY END
 --DBMS_OUTPUT.PUT_LINE(UTL_TCP.GET_LINE(lv_conn, TRUE));
 lv_rc := UTL_TCP.WRITE_LINE(lv_conn, 'QUIT'); ----- ENDS EMAIL TRANSACTION
 --DBMS_OUTPUT.PUT_LINE(UTL_TCP.GET_LINE(lv_conn, TRUE));
 UTL_TCP.CLOSE_CONNECTION(lv_conn); ----- CLOSE SMTP PORT CONNECTION

 EXCEPTION
 WHEN OTHERS THEN
  RAISE;
 
 END SENDMAIL_ATT;

---------------------------

END FIN_PKG_INTER;
/
