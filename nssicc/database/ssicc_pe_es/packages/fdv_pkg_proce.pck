CREATE OR REPLACE PACKAGE fdv_pkg_proce
IS
   -- Author  : Ivan Tocto Jaimes
   -- Created : 29/09/2011 11:04:24 a.m.
   -- Purpose : Funciones y Procedimientos almacenados para los procesos de
   --           Fuente de Ventas

    /* Declaracion de Records */
    type record_zone is record (
        cod_zona fdv_base_vaexo.bvx_zona%TYPE);

    type record_varchar is record (
        description VARCHAR2(50));

    type record_grupo is record (
        var1 FDV_PROI_CLUST.var1%TYPE,
        var2 FDV_PROI_CLUST.var2%TYPE,
        var3 FDV_PROI_CLUST.var3%TYPE,
        tot1 number,
        tot2 number,
        tot3 number);

    /* Declaracion de Tipos */
    type collection_zone is table of record_zone;
    type collection_varchar is table of record_varchar;
    type collection_grupos is table of record_grupo;
    type array_group is varray(13) of VARCHAR2(20);

    type split_tbl IS table of varchar2(4000);

    /* Declaracion de Variables */
    li_tbl_existe INTEGER;
    ls_sqlerrm    VARCHAR2(250);

    -- Declaracion de constantes
    lc_par_per_sub VARCHAR2(3) := '001'; -- Percentil Sub-Penetrado
    lc_par_fac_sub VARCHAR2(3) := '002'; -- Factor Sub-Penetrado
    lc_par_pes_his VARCHAR2(3) := '003'; -- Peso Histórico
    lc_par_per_cap VARCHAR2(3) := '004'; -- Percentil Capitalización
    lc_par_per_act VARCHAR2(3) := '005'; -- Percentil Actividad
    lc_par_per_pup VARCHAR2(3) := '006'; -- Percentil PUP
    lc_par_per_ppu VARCHAR2(3) := '007'; -- Percentil PPU
    lc_par_peh_vnt VARCHAR2(3) := '008'; -- Peso Histórico de Ventas
    lc_par_pev_pcr VARCHAR2(3) := '009'; -- Peso de Ventas Po
    lc_par_ajc_max VARCHAR2(3) := '010'; -- Ajuste de Colas Máximo
    lc_par_ajc_min VARCHAR2(3) := '011'; -- Ajuste de Colas Minimo

    li_nro_deci INTEGER := 10;  -- Numero de Decimales
    --

    /*********** definicion de los codigos en el array_group *******************
    1 = 'Muy Pequeño',
    2 = 'Pequeño',
    3 = 'Mediano',
    4 = 'Grande',
    5 = 'Muy Grande'
    ***************************************************************************/

    /* Declaracion de Arrays */
    v_array_group_2 array_group := array_group('3','4');
    v_array_group_3 array_group := array_group('2','3','4');
    v_array_group_4 array_group := array_group('2','3','4','5');
    v_array_group_5 array_group := array_group('1','2','3','4','5');

   /**************************************************************************
   Descripcion       : Inicializa la tabla de consolidados por zonas
   Fecha Creacion    : 29/09/2011
   Parametros Entrada:
       p_cod_proceso   : Codigo del proceso
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE fdv_pr_inici_conso (
      p_cod_proceso    VARCHAR2
   );

   /***************************************************************************
   Descripcion       : Proceso que se encarga de efectuar los calculos para la
                       generacion de la propuesta de clusterizacion
   Fecha Creacion    : 05/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodpais       : Codigo del pais,
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
   PROCEDURE FDV_PR_GENER_PROPU_CLUST (
        pcodpais               VARCHAR2,
        pcodproceso            VARCHAR2,
        pmensajeerror    OUT   VARCHAR2
   );

   /***************************************************************************
   Descripcion       : Proceso que se encarga de procesar las variables exogeneas
                        Marca las que no se van a considerar en el proceso
   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodpais       : Codigo del pais,
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
   PROCEDURE FDV_PR_VARIA_EXOGE (
        pcodpais               VARCHAR2,
        pcodproceso            VARCHAR2,
        pmensajeerror    OUT   VARCHAR2
   );

   /***************************************************************************
   Descripcion       : Proceso que nos permite calcular los percentiles en base
                       al número de grupos de la población que ha seleccionado
                       el usuario, estos datos servirán para el cálculo del
                       grupo poblacional
   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
   PROCEDURE FDV_PR_CALCU_GRUPO_POBLA (
        pcodproceso            VARCHAR2,
        pmensajeerror    OUT   VARCHAR2
   );

   /***************************************************************************
   Descripcion       : Proceso que nos permite calcular la penetración de
                       cada zona a partir de la Base de Datos Zonas.
   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
   PROCEDURE FDV_PR_CALCU_PENTR (
        pcodproceso            VARCHAR2,
        pmensajeerror    out   varchar2
   );

   /***************************************************************************
   Descripcion       : Proceso que nos permite organizar las variables en orden
                       jerárquico por cada zona a partir de la Base de
                       Datos Zonas.
   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
   PROCEDURE FDV_PR_JERAR_VARIA (
        pcodproceso            VARCHAR2,
        pmensajeerror    OUT   VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Realiza la distribucion de metas
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
        p_cod_pais      : Codigo del pais,
       p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_DIST_METAS (
      p_cod_pais        VARCHAR2,
      p_cod_proceso     VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Inicializa la tabla base con los datos para el calculo
                        del proceso de distribucion de metas
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_pais      : Codigo del pais,
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_INI_PROC_DIST_METAS (
      p_cod_pais        VARCHAR2,
      p_cod_proceso     VARCHAR2,
      pmensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Inicializa la tabla base con los datos para el calculo
                        de secciones, del proceso de distribucion de metas
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_INI_PROC_DIST_META_SECC (
      p_cod_proceso     VARCHAR2,
      pmensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Calcula el promedio de secciones em base a las
                        modificaciones que hizo el usuario
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_PROM_SECC (
      p_cod_proceso     VARCHAR2,
      pmensajeerror OUT VARCHAR2
   );


   /**************************************************************************
   Descripcion       : Calcula los benchmarks y los campos calculados
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             p_fal_cerrar  : Numero de campañas que faltan para cerrar el periodo
             p_aacc_inicio : Campaña de inicio en formato AAAACC -- INICIO DE PERIODO
             p_aacc_fin_perio : Campaña de fin-corte en formato AAAACC -- FIN DE PERIODO
             p_aacc_fin_corte : Campaña de fin-corte en formato AAAACC -- CAMPAÑA DE CORTE
             p_aacc_ini_ant : Campaña de inicio en formato AAAACC del periodo del año anterior
             p_aacc_ini_ant_men1 : Campaña de inicio MENOS 1 en formato AAAACC del periodo del año anterior
             p_aacc_fin_ant : Campaña de fin en formato AAAACC del periodo del año anterior
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_BNMK_CMCA (
      p_cod_proceso     VARCHAR2,
      p_fal_cerrar  OUT NUMBER,
      p_aacc_inicio OUT VARCHAR2,
      p_aacc_fin_perio OUT VARCHAR2,
      p_aacc_fin_corte OUT VARCHAR2,
      p_aacc_ini_ant OUT VARCHAR2,
      p_aacc_ini_ant_men1 OUT VARCHAR2,
      p_aacc_fin_ant OUT VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Calcula la venta potencial e indices relativos
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
             p_aacc_ini_ant : Campaña de inicio en formato AAAACC del periodo del año anterior
             p_aacc_fin_ant : Campaña de fin en formato AAAACC del periodo del año anterior
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_VEPO_INRE (
      p_cod_proceso  VARCHAR2,
      p_aacc_ini_ant VARCHAR2,
      p_aacc_fin_ant VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Realiza el proceso de calculo de decante
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
             p_aacc_ini_ant : Campaña de inicio en formato AAAACC del periodo del año anterior
             p_aacc_fin_ant : Campaña de fin en formato AAAACC del periodo del año anterior
             p_meta_vndi : Meta de venta a distribuir
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_DECA (
      p_cod_proceso  VARCHAR2,
      p_aacc_ini_ant VARCHAR2,
      p_aacc_fin_ant VARCHAR2,
      p_meta_vndi NUMBER,
      p_mensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Realiza el proceso de ajuste, de colas y de metas por campaña
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
             p_fal_cerrar : Numero de campañas que faltan para cerrar el periodo
             p_aacc_inicio : Campaña de inicio en formato AAAACC -- INICIO DE PERIODO
             p_aacc_fin_perio : Campaña de fin-corte en formato AAAACC -- FIN DE PERIODO
             p_aacc_fin_corte : Campaña de fin-corte en formato AAAACC -- CAMPAÑA DE CORTE
             p_meta_vndi : Meta de venta a distribuir,
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_AJUS_COLA_MECA (
      p_cod_proceso  VARCHAR2,
      p_fal_cerrar  NUMBER,
      p_aacc_inicio VARCHAR2,
      p_aacc_fin_perio VARCHAR2,
      p_aacc_fin_corte VARCHAR2,
      p_meta_vndi NUMBER,
      p_mensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Limpia / inicializa los campos calculados en el proceso
                        de distribucion de metas
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_LIMP_CCAL (
      p_cod_proceso  VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   );

   /**************************************************************************
   Descripcion       : Realiza el proceso de ajuste de metas por campaña
                        En base a los datos que el Director de Ventas a cargado
   Fecha Creacion    : 05/01/2012
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_AJUS_MCDV (
      p_cod_proceso  VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   );

    /************************************************************************
     Descripcion : Funcion que convierte Cadena a Lista
     Creado     : Carlos Bazalar
     Fecha      : 19/10/2010
       Parametros:
         p_list  Cadena que sera convertida a Lista
         p_del   Delimitador a usar, por defecto es ,
    ************************************************************************/
    FUNCTION FDV_FN_SPLIT
    (
        p_list varchar2,
        p_del varchar2 := ','
    )
    RETURN split_tbl;



   /**************************************************************************
   Descripcion       : Proceso que se encarga de efectuar los calculos para la
                       generacion de la propuesta de clusterizacion desde
                       archivo
   Fecha Creacion    : 19/06/2012
   Autor             : Carlos Soberon
   Parametros Entrada:
             pcodpais       : Codigo del pais,
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
    PROCEDURE FDV_PR_GENER_PROPU_CLUST_ARCH (
        pcodpais               VARCHAR2,
        pcodproceso            VARCHAR2,
        pmensajeerror    OUT   VARCHAR2
    );



END fdv_pkg_proce;
/
CREATE OR REPLACE PACKAGE BODY fdv_pkg_proce
IS
   /**************************************************************************
   Descripcion       : Inicializa la tabla de consolidados por zonas
   Fecha Creacion    : 29/09/2011
   Parametros Entrada:
       p_cod_proceso   : Codigo del proceso
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
    PROCEDURE FDV_PR_INICI_CONSO (
        p_cod_proceso    VARCHAR2
    ) IS
    BEGIN

        -- Eliminamos los datos anteriores
        DELETE FROM FDV_ZONA_CONSO WHERE PROC_COD_PROC = p_cod_proceso;

        -- Inicializamos la tabla con los valores por defecto
        -- Solo se consideran las zonas que aparecen en la campaña de corte.

        INSERT INTO FDV_ZONA_CONSO (PROC_COD_PROC, COD_ZONA, FLA_CACO, FLA_ZOCO, FLA_ZOFI, FLA_ZONC)
        SELECT DISTINCT p_cod_proceso, BZO_ZONA,'N','N','N','N'
        FROM FDV_BASE_ZONA
        WHERE PROC_COD_PROC = p_cod_proceso;

    END FDV_PR_INICI_CONSO;

   /**************************************************************************
   Descripcion       : Proceso que se encarga de efectuar los calculos para la
                       generacion de la propuesta de clusterizacion
   Fecha Creacion    : 05/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodpais       : Codigo del pais,
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
    PROCEDURE FDV_PR_GENER_PROPU_CLUST (
        pcodpais          VARCHAR2,
        pcodproceso       VARCHAR2,
        pmensajeerror OUT VARCHAR2
    ) IS

    state_proc fdv_proce.sta_proc%TYPE;

    BEGIN


      -- Solo consideramos las zonas que estan en la campaña de corte
      DELETE FROM FDV_ZONA_CONSO
      WHERE PROC_COD_PROC = pcodproceso
      AND COD_ZONA NOT IN(
        SELECT BZO_ZONA
        FROM FDV_BASE_ZONA
        WHERE PROC_COD_PROC = pcodproceso
        AND BZO_ANYO||BZO_CAMP IN(SELECT ANY_PROC||CAM_PROC FROM FDV_PROCE WHERE PROC_COD_PROC = pcodproceso)
      );
      -- --

      FDV_PR_VARIA_EXOGE(pcodpais, pcodproceso, pmensajeerror);

      if(pmensajeerror = '' or pmensajeerror is null) then

        fdv_pr_calcu_grupo_pobla(pcodproceso, pmensajeerror);

        if(pmensajeerror = '' or pmensajeerror is null) then

          FDV_PR_CALCU_PENTR(pcodproceso, pmensajeerror);

          if(pmensajeerror = '' or pmensajeerror is null) then

            FDV_PR_JERAR_VARIA(pcodproceso, pmensajeerror);

            if(pmensajeerror = '' or pmensajeerror is null) then

              -- Verificamos el estado actual del proceso
              select sta_proc into state_proc from fdv_proce
              where cod_proc = pcodproceso;

              if(state_proc = '2') then
                -- Una vez finalizado los calculos actualizamos el proceso al
                -- estado "En proceso de clusterizacion" = 3
                update fdv_proce set sta_proc = '3'
                where cod_proc = pcodproceso;
              end if;

            end if;
          end if;
        end if;
      end if;

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    pmensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_GENER_PROPU_CLUST: '
    || ls_sqlerrm
    );

    END FDV_PR_GENER_PROPU_CLUST;

   /**************************************************************************
   Descripcion       : Proceso que se encarga de calcular la capitalizacion de
                       las zonas a considerar
   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodpais       : Codigo del pais,
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
    PROCEDURE FDV_PR_VARIA_EXOGE (
        pcodpais          VARCHAR2,
        pcodproceso       VARCHAR2,
        pmensajeerror OUT VARCHAR2
    ) IS

    v_query     VARCHAR2(500);
    v_condition VARCHAR2(500);

    v_nse  fdv_proce.fla_nse%TYPE;
    v_pobl fdv_proce.fla_pobl%TYPE;
    v_rlur fdv_proce.fla_rlur%TYPE;
    v_var1 fdv_proce.fla_var1%type;
    v_var2 fdv_proce.fla_var2%TYPE;

    v_record_zone collection_zone;

    BEGIN

      v_query     := '';
      v_condition := '';
      v_nse  := '';
      v_pobl := '';
      v_rlur := '';
      v_var1 := '';
      v_var2 := '';

      -- variables exogenas que el usuario selecciono
      select fla_nse, fla_pobl, fla_rlur, fla_var1, fla_var2
      into v_nse, v_pobl, v_rlur, v_var1, v_var2
      from fdv_proce where cod_proc = pcodproceso;

      -- Verificando que tengan datos en las variables exogenas seleccionadas.
      -- Obteniendo todas las zonas que no tienen datos en algunas de las
      -- Variables Exogenas

      if(v_nse = 'S') then
        v_condition := v_condition||' or bvx_nse is null';
      end if;

      if(v_pobl = 'S') then
        v_condition := v_condition||' or bvx_pobl is null or bvx_pobl = 0';
      end if;

      if(v_rlur = 'S') then
        v_condition := v_condition||' or bvx_rlur is null';
      end if;

      if(v_var1 = 'S') then
        v_condition := v_condition||' or bvx_var1 is null';
      end if;

      if(v_var2 = 'S') then
        v_condition := v_condition||' or bvx_var2 is null';
      end if;

      v_condition := substr(v_condition, 4);

      v_query := v_query||' select cod_zona';
      v_query := v_query||' from fdv_zona_conso';
      v_query := v_query||' where fla_zoco = '||''''||'S'||''''||' and proc_cod_proc = '||pcodproceso;
      v_query := v_query||' minus';
      v_query := v_query||' select bvx_zona';
      v_query := v_query||' from fdv_base_vaexo';
      v_query := v_query||' where proc_cod_proc = '||pcodproceso;

      v_query := v_query||' union ';

      v_query := v_query||' select';
      v_query := v_query||' bvx_zona from fdv_base_vaexo';
      v_query := v_query||' where proc_cod_proc = '||pcodproceso||' and bvx_zona in (';
      v_query := v_query||' select distinct cod_zona from fdv_zona_conso';
      v_query := v_query||' where fla_zoco = '||''''||'S'||''''||' and proc_cod_proc = '||pcodproceso;
      v_query := v_query||' ) and (';
      v_query := v_query||v_condition;
      v_query := v_query||' )';

      execute immediate v_query bulk collect into v_record_zone;

      if(v_record_zone.count != 0) then
        for i in v_record_zone.first .. v_record_zone.last loop

          -- Actualizando la zona como zona que no es a considerar debido
          -- a que no cuenta con datos en todas las variables seleccionadas
          update fdv_zona_conso
            set fla_zoco = 'N'
          where proc_cod_proc = pcodproceso
          and cod_zona = v_record_zone(i).cod_zona;

        end loop;
      end if;

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    pmensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_VARIA_EXOGE: '
    || ls_sqlerrm
    );

    END FDV_PR_VARIA_EXOGE;

   /***************************************************************************
   Descripcion       : Proceso que nos permite calcular los percentiles en base
                       al número de grupos de la población que ha seleccionado
                       el usuario, estos datos servirán para el cálculo del
                       grupo poblacional
   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
    PROCEDURE FDV_PR_CALCU_GRUPO_POBLA (
        pcodproceso       VARCHAR2,
        pmensajeerror OUT VARCHAR2
    ) IS

    v_query VARCHAR2(800);
    num_grpo fdv_proce.nro_grpo%TYPE;
    percent_ini NUMBER;
    percent_fin NUMBER;
    v_grupo_fin VARCHAR2(1);

    BEGIN

      num_grpo := 0;
      v_query := '';

      -- Obtenemos el numero de grupos que selecciono el usuario
      select nro_grpo into num_grpo from fdv_proce
      where cod_proc = pcodproceso;

      -- Solo se debe proceder si el usuario selecciono un numero de grupos
      if(num_grpo > 0) then

        -- Limpiamos nuestra tabla temporal filtrando por el codigo del proceso
        delete from fdv_PROI_perce where proc_cod_proc = pcodproceso;

        -- Iteramos el numero de grupo seleccionado para hallar y almacenar en una tabla temporal
        -- los percentiles calculados y tambien la descripcion del grupo poblacional
        for i in 1 .. num_grpo loop

          v_query := '';
          v_query := v_query||' insert into fdv_PROI_perce(proc_cod_proc, val_ini, val_fin, des_grpo)';
          v_query := v_query||' select '||''''||pcodproceso||''''||',';
          v_query := v_query||' percentile_cont(('||to_char(i-1)||')/'||to_char(num_grpo)||') within group (order by bvx_pobl) val_ini,';
          v_query := v_query||' percentile_cont('||to_char(i)||'/'||to_char(num_grpo)||') within group (order by bvx_pobl) val_fin,';

          if(num_grpo = 2) then
            v_query := v_query||''''||v_array_group_2(i)||'''';
            v_grupo_fin := v_array_group_2(i);
          elsif(num_grpo = 3) then
            v_query := v_query||''''||v_array_group_3(i)||'''';
            v_grupo_fin := v_array_group_3(i);
          elsif(num_grpo = 4) then
            v_query := v_query||''''||v_array_group_4(i)||'''';
            v_grupo_fin := v_array_group_4(i);
          elsif(num_grpo = 5) then
            v_query := v_query||''''||v_array_group_5(i)||'''';
            v_grupo_fin := v_array_group_5(i);
          end if;

          -- Usamos las zonas que se marcaron como confiables y las no confiables, descartando las oficinas
          v_query := v_query||' from (';
          v_query := v_query||'     select nvl(bvx_pobl, 0) bvx_pobl ';
          v_query := v_query||'     from fdv_base_vaexo ';
          v_query := v_query||'     where proc_cod_proc = '||pcodproceso;
          v_query := v_query||'     and bvx_zona in( ';
          v_query := v_query||'         select cod_zona from fdv_zona_conso where proc_cod_proc = '||pcodproceso||' and fla_zoco = ''S'' )';
          v_query := v_query||' ) ';

          execute immediate v_query;

        end loop ;

        -- Actualizamos el campo "Grupo Poblacional" (GRP_POBL) de la tabla
        -- FDV_BASE_VAEXO tomando como referencia al campo BVX_POBL y basandonos
        -- en nuestra tabla temporal FDV_PROI_PERCE

        update fdv_base_vaexo set grp_pobl = (
          select des_grpo from fdv_PROI_perce where proc_cod_proc = pcodproceso
          and val_fin != 0 and (
          (val_ini <= bvx_pobl and val_fin >  bvx_pobl and cast(des_grpo as number) != v_grupo_fin) or
          (val_ini <= bvx_pobl and val_fin >= bvx_pobl and cast(des_grpo as number)  = v_grupo_fin)
          )
        )
        where proc_cod_proc = pcodproceso;

        -- Actualizando la tabla FDV_ZONA_CONSO con los datos
        -- de la tabla FDV_BASE_VAEXO

        for bean in (
            select bvx_zona, bvx_nse, bvx_rlur,
            bvx_pobl, bvx_var1, bvx_var2, grp_pobl
            from fdv_base_vaexo where proc_cod_proc = pcodproceso
        ) loop

          update fdv_zona_conso
          set var_nse  = bean.bvx_nse,
              var_rlur = bean.bvx_rlur,
              var_pobl = bean.bvx_pobl,
              var_var1 = bean.bvx_var1,
              var_var2 = bean.bvx_var2,
              var_grpo = bean.grp_pobl
          where cod_zona = bean.bvx_zona
          and proc_cod_proc = pcodproceso;

        end loop;
      end if;

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    pmensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_CALCU_GRUPO_POBLA: '
    || ls_sqlerrm
    );

    END FDV_PR_CALCU_GRUPO_POBLA;

   /***************************************************************************
   Descripcion       : Proceso que nos permite calcular la penetración de
                       cada zona a partir de la Base de Datos Zonas.
   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
    PROCEDURE FDV_PR_CALCU_PENTR (
        pcodproceso       VARCHAR2,
        pmensajeerror OUT VARCHAR2
    ) IS

    anio fdv_proce.any_proc%TYPE;
    campanha fdv_proce.cam_proc%TYPE;

    BEGIN

      -- campaña y anio que el usuario selecciono
      select any_proc, cam_proc into anio, campanha
      from fdv_proce where cod_proc = pcodproceso;

      -- Recorremos la lista de zonas y la penetracion calculada para luego
      -- actualizar el campo VAL_PNTR de la tabla FDV_ZONA_CONSO
      for bean in (
        select bzo_zona as zona, bzo_acti_real/(bvx_pobl/1000) as pntr
        from fdv_base_zona bz, fdv_base_vaexo bv
        where bz.proc_cod_proc = bv.proc_cod_proc
        and bz.bzo_zona = bv.bvx_zona
        and bz.proc_cod_proc = pcodproceso
        and bz.bzo_camp = campanha
        and bz.bzo_anyo = anio
        and bvx_pobl > 0
      ) loop

        update fdv_zona_conso
          set val_pntr = bean.pntr
        where cod_zona = bean.zona
        and proc_cod_proc = pcodproceso;

      end loop;

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    pmensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_CALCU_PENTR: '
    || ls_sqlerrm
    );

    END FDV_PR_CALCU_PENTR;


   /***************************************************************************
   Descripcion       : Proceso que nos permite organizar las variables en orden
                       jerárquico por cada zona a partir de la Base de
                       Datos Zonas.

                       Solo se jerarquiza 3 variables

   Fecha Creacion    : 06/10/2011
   Autor             : Frank Ayala
   Parametros Entrada:
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
    PROCEDURE FDV_PR_JERAR_VARIA (
        pcodproceso       VARCHAR2,
        pmensajeerror OUT VARCHAR2
    ) IS

        v_nse  fdv_proce.fla_nse%TYPE := '';
        v_pobl fdv_proce.fla_pobl%TYPE := '';
        v_rlur fdv_proce.fla_rlur%TYPE := '';

        v_ord_vacl           VARCHAR2(100) := '';
        v_orden_var          VARCHAR2(100) := '';

        v_query_a            VARCHAR2(2000) := '';
        v_query_b            VARCHAR2(2000) := '';

        n_min_zona   NUMBER := 0;
        n_cont_vari  NUMBER := 0;
        n_nro_clust_actual number := 1;
        n_nro_max_grupo number := 8;
        n_nro_ante_zonas number := 0;
        n_nro_sigte_zonas number := 0;
        n_nro_total_grupo number := 0;
        n_nro_total_regist number := 0;
        n_indic_inicio number := 1;
        v_flag_proc varchar2(1):= 'S';
        n_nro_zonas_singr number := 0;

        v_record_varchar collection_varchar;
        v_record_cl_asig collection_grupos;

        listaCampos  split_tbl;
        listaOrdenCampo1  split_tbl;
        listaOrdenCampo2  split_tbl;
        listaOrdenCampo3  split_tbl;

    BEGIN

        -- variables exogenas que el usuario selecciono
        select fla_nse, fla_pobl, fla_rlur
        into v_nse, v_pobl, v_rlur
        from fdv_proce where cod_proc = pcodproceso;

        -- obteniendo el tamaño mínimo de grupo de zonas
        select val_para into n_min_zona from fdv_proce_param
        where para_cod_para = '012' and proc_cod_proc = pcodproceso;

        -- Determinamos la desviacion estandar por cada una de las variables que
        -- el usuario selecciono para luego agruparlas y ordenarlas de mayor a menor

        if(v_nse = 'S') then
            v_query_a := v_query_a||' union all';
            v_query_a := v_query_a||' select '||''''||'VAR_NSE'||''''||' as desc_variable, stddev(slct.prom_pntr) as desv_pntr from(';
            v_query_a := v_query_a||'       select var_nse, avg(val_pntr) as prom_pntr';
            v_query_a := v_query_a||'       from fdv_zona_conso where proc_cod_proc = '||pcodproceso;
            v_query_a := v_query_a||'       and fla_zoco = '||''''||'S'||''''||' group by var_nse) slct';
        end if;

        if(v_pobl = 'S') then
            v_query_a := v_query_a||' union all';
            v_query_a := v_query_a||' select '||''''||'VAR_GRPO'||''''||' as desc_variable, stddev(slct.prom_pntr) as desv_pntr from(';
            v_query_a := v_query_a||'       select var_grpo, avg(val_pntr) as prom_pntr';
            v_query_a := v_query_a||'       from fdv_zona_conso where proc_cod_proc = '||pcodproceso;
            v_query_a := v_query_a||'       and fla_zoco = '||''''||'S'||''''||' group by var_grpo) slct';
        end if;

        if(v_rlur = 'S') then
            v_query_a := v_query_a||' union all';
            v_query_a := v_query_a||' select '||''''||'VAR_RLUR'||''''||' as desc_variable, stddev(slct.prom_pntr) as desv_pntr from(';
            v_query_a := v_query_a||'       select var_rlur, avg(val_pntr) as prom_pntr';
            v_query_a := v_query_a||'       from fdv_zona_conso where proc_cod_proc = '||pcodproceso;
            v_query_a := v_query_a||'       and fla_zoco = '||''''||'S'||''''||' group by var_rlur) slct';
        end if;

        if(v_query_a is not null) then
            v_query_a := substr(v_query_a, 11);
            v_query_b := v_query_b||' select slct2.desc_variable from(';
            v_query_b := v_query_b||v_query_a;
            v_query_b := v_query_b||' ) slct2 order by slct2.desv_pntr desc';

            execute immediate v_query_b bulk collect into v_record_varchar;

            if(v_record_varchar.count != 0) then

                -- Acumulamos las variables en el orden generado, concatenando sus
                -- descripciones de cada una
                for i in v_record_varchar.first .. v_record_varchar.last loop

                    v_ord_vacl  := v_ord_vacl||', '||v_record_varchar(i).description;
                    n_cont_vari := n_cont_vari + 1; -- contamos las variables seleccionadas

                    if(v_record_varchar(i).description = 'VAR_GRPO') then
                        v_orden_var := v_orden_var||', VAR_GRPO desc';  --WARN Solo debe de haber un espacio entre el nombre del campo y el orden
                    elsif(v_record_varchar(i).description = 'VAR_NSE') then
                        v_orden_var := v_orden_var||', VAR_NSE asc';    --WARN Solo debe de haber un espacio entre el nombre del campo y el orden
                    elsif(v_record_varchar(i).description = 'VAR_RLUR') then
                        v_orden_var := v_orden_var||', VAR_RLUR desc';  --WARN Solo debe de haber un espacio entre el nombre del campo y el orden
                    end if;

                end loop;

                -- Spliteando la cadena formada, ya que tal como se forma existe una
                -- ', ' demas en la parte delantera
                v_ord_vacl := substr(v_ord_vacl, 2);
                v_orden_var := substr(v_orden_var, 2);

                -- Actualizando el campo ORD_VACL de la tabla FDV_PROCE con el orden
                -- generado de las variables exogenas seleccionadas
                update fdv_proce set ord_vacl = v_ord_vacl
                where cod_proc = pcodproceso;

                -- Obteniendo los datos ordenados para evaluarlos y asignar el cluster
                -- Movemos la data a una tabla temporal para procesarla

                -- Limpiamos la tabla temporal
                delete from FDV_PROI_CLUST where proc_cod_proc = pcodproceso;

                -- Inicializamos las columnas de la tabla de consolidados
                update fdv_zona_conso
                set clu_asig_sist = null, clu_asig_pais = null
                where proc_cod_proc = pcodproceso
                and fla_zoco = 'S';

                v_query_a := '';

                if n_cont_vari = 1 then
                    v_query_a := 'INSERT INTO FDV_PROI_CLUST(PROC_COD_PROC, COD_ZONA, VAR1) ';
                elsif n_cont_vari = 2 then
                    v_query_a := 'INSERT INTO FDV_PROI_CLUST(PROC_COD_PROC, COD_ZONA, VAR1, VAR2) ';
                elsif n_cont_vari = 3 then
                    v_query_a := 'INSERT INTO FDV_PROI_CLUST(PROC_COD_PROC, COD_ZONA, VAR1, VAR2, VAR3) ';
                end if;

                v_query_a := v_query_a||' select proc_cod_proc, cod_zona, '||v_ord_vacl;
                v_query_a := v_query_a||' from fdv_zona_conso where proc_cod_proc = '||pcodproceso;
                v_query_a := v_query_a||' and fla_zoco = '||''''||'S'||''''||' order by '||v_orden_var;

                execute immediate v_query_a;

                -- Dependiendo del numero de variables construimos un query
                --------------------------------------------------------------------------------------

                -- Inicializamos los clusters
                update fdv_PROI_clust
                set clus = null
                where proc_cod_proc = pcodproceso;

                -- Inicializamos la lista de campos
                listaCampos := fdv_fn_split(trim(v_orden_var));

                -- Casos por cada numero de variables
                if n_cont_vari = 1 then

                    listaOrdenCampo1 := fdv_fn_split(trim(listaCampos(1)), ' ');

                    v_query_a := ' ';
                    v_query_a := v_query_a||' select var1, '''' var2, '''' var3, count(*) tot1, 0 tot2, 0 tot3 ';
                    v_query_a := v_query_a||' from fdv_PROI_clust ';
                    v_query_a := v_query_a||' where proc_cod_proc = '|| pcodproceso;
                    v_query_a := v_query_a||' group by var1 ';
                    v_query_a := v_query_a||' order by var1 '||listaOrdenCampo1(2);

                    execute immediate v_query_a bulk collect into v_record_cl_asig;

                    n_nro_total_regist := v_record_cl_asig.count;

                    for i in v_record_cl_asig.first .. v_record_cl_asig.last loop

                        if(i = 1) then
                            -- siempre se establece el valor en el primero
                            update fdv_PROI_clust
                            set clus = n_nro_clust_actual
                            where proc_cod_proc = pcodproceso
                            and var1 = v_record_cl_asig(i).var1;

                            n_nro_clust_actual := n_nro_clust_actual + 1;
                        else

                           -- verificamos la cantidad del anterior
                            select count(*)
                            into n_nro_ante_zonas
                            from fdv_PROI_clust
                            where proc_cod_proc = pcodproceso
                            and clus = n_nro_clust_actual - 1;

                            if(v_record_cl_asig(i).tot1 >= n_nro_max_grupo) then

                                if n_nro_ante_zonas < n_nro_max_grupo then
                                    --si el anterior es menor que el maximo, se agrupa con el actual
                                    update fdv_PROI_clust
                                    set clus = n_nro_clust_actual - 1
                                    where proc_cod_proc = pcodproceso
                                    and var1 = v_record_cl_asig(i).var1;
                                else
                                    -- se inicia un nuevo grupo
                                    update fdv_PROI_clust
                                    set clus = n_nro_clust_actual
                                    where proc_cod_proc = pcodproceso
                                    and var1 = v_record_cl_asig(i).var1;

                                    n_nro_clust_actual := n_nro_clust_actual + 1;

                                end if;

                            elsif(v_record_cl_asig(i).tot1 < n_nro_max_grupo) then

                                -- verificamos la cantidad del siguiente grupo, solo si no se ha llegado al fnal
                                if(i<n_nro_total_regist) then
                                    -- evaluar el grupo anterior y el siguiente

                                    n_nro_sigte_zonas := v_record_cl_asig(i+1).tot1;

                                    if(n_nro_ante_zonas < n_nro_sigte_zonas) then
                                        --agrupar en el nivel anterior
                                        update fdv_PROI_clust
                                        set clus = n_nro_clust_actual - 1
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1;
                                    else
                                        --agrupar en el siguiente nivel
                                        update fdv_PROI_clust
                                        set clus = n_nro_clust_actual
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1;

                                        n_nro_clust_actual := n_nro_clust_actual + 1;
                                    end if;

                                else
                                    -- es el ultimo registro, es menor que 8 se agrupa al anterior
                                        --agrupar en el nivel anterior
                                        update fdv_PROI_clust
                                        set clus = n_nro_clust_actual - 1
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1;
                                end if;


                            end if;

                        end if;
                    end loop;
                elsif n_cont_vari = 2 then

                    listaOrdenCampo1 := fdv_fn_split(trim(listaCampos(1)), ' ');
                    listaOrdenCampo2 := fdv_fn_split(trim(listaCampos(2)), ' ');

                    -- agrupamos y ordenamos los datos a procesar
                    v_query_a := ' ';

                    v_query_a := v_query_a||' select ';
                    v_query_a := v_query_a||' v1.var1, v2.var2, '''' var3, v1.tot1, v2.tot2, 0 tot3 ';
                    v_query_a := v_query_a||' from ( ';
                    v_query_a := v_query_a||'       select var1, count(*) tot1 ';
                    v_query_a := v_query_a||'       from fdv_PROI_clust ';
                    v_query_a := v_query_a||'       where proc_cod_proc = '||pcodproceso;
                    v_query_a := v_query_a||'       group by var1 ';
                    v_query_a := v_query_a||'       order by var1) v1, ( ';
                    v_query_a := v_query_a||'       select var1, var2, count(*) tot2 ';
                    v_query_a := v_query_a||'       from fdv_PROI_clust ';
                    v_query_a := v_query_a||'       where proc_cod_proc = '||pcodproceso;
                    v_query_a := v_query_a||'       group by var1, var2 ';
                    v_query_a := v_query_a||'       order by var1, var2) v2 ';
                    v_query_a := v_query_a||' where v1.var1 = v2.var1 ';
                    v_query_a := v_query_a||' order by v1.var1 '||listaOrdenCampo1(2)||', v2.var2 '||listaOrdenCampo2(2);

                    execute immediate v_query_a bulk collect into v_record_cl_asig;

                    n_nro_total_regist := v_record_cl_asig.count;
                    n_indic_inicio := 1;

                    --verificamos si el primer grupo del nivel superiror tiene menos del minimo
                    if(v_record_cl_asig(1).tot1 < n_nro_max_grupo) then
                        --agrupamos a todos en uno solo
                        update fdv_PROI_clust
                        set clus = 0 --marcamos con cero para actualizarlo al final con el nro inicial 1
                        where proc_cod_proc = pcodproceso
                        and var1 = v_record_cl_asig(1).var1;

                        -- calculamos el inicio del loop
                        select count(distinct var2) + 1
                        into n_indic_inicio
                        from fdv_PROI_clust
                        where proc_cod_proc = pcodproceso
                        and var1 = v_record_cl_asig(1).var1;

                    end if;

                    for i in n_indic_inicio .. v_record_cl_asig.last loop
                        n_nro_total_grupo := n_nro_total_grupo + v_record_cl_asig(i).tot2;

                        if(i = n_indic_inicio) then
                            -- siempre se establece el valor en el primero
                            update fdv_PROI_clust
                            set clus = n_nro_clust_actual
                            where proc_cod_proc = pcodproceso
                            and var1 = v_record_cl_asig(i).var1
                            and var2 = v_record_cl_asig(i).var2;

                            n_nro_clust_actual := n_nro_clust_actual + 1;
                        else
                           -- verificamos la cantidad del anterior
                            select count(*)
                            into n_nro_ante_zonas
                            from fdv_PROI_clust
                            where proc_cod_proc = pcodproceso
                            and clus = n_nro_clust_actual - 1;

                            -- verificamos si el nivel mas superior ya cerro
                            if n_nro_total_grupo = v_record_cl_asig(i).tot1 then
                                n_nro_total_grupo := 0;
                                --cierra en esta etapa, por lo tanto se agrupa el anterior al actual

                                if v_record_cl_asig(i).tot2 < n_nro_max_grupo then
                                    --si el anterior es menor que el maximo, se agrupa con el actual
                                    update fdv_PROI_clust
                                    set clus = n_nro_clust_actual - 1
                                    where proc_cod_proc = pcodproceso
                                    and var1 = v_record_cl_asig(i).var1
                                    and var2 = v_record_cl_asig(i).var2;
                                else
                                    -- se inicia un nuevo grupo si el anterior es menor que el maximo

                                    if n_nro_ante_zonas < n_nro_max_grupo then
                                        -- en caso contrario se agrupa al actual
                                        update fdv_PROI_clust
                                        set clus = n_nro_clust_actual - 1
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1
                                        and var2 = v_record_cl_asig(i).var2;

                                    else
                                    -- en caso contrario se agrupa al anterior
                                        update fdv_PROI_clust
                                        set clus = n_nro_clust_actual
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1
                                        and var2 = v_record_cl_asig(i).var2;

                                        n_nro_clust_actual := n_nro_clust_actual + 1;
                                    end if;
                                end if;

                            else
                                -- aun no cierra el nivel, se inicia un nuevo grupo, solo si
                                -- el anterior >= maximo

                                if n_nro_ante_zonas >= n_nro_max_grupo then
                                    update fdv_PROI_clust
                                    set clus = n_nro_clust_actual
                                    where proc_cod_proc = pcodproceso
                                    and var1 = v_record_cl_asig(i).var1
                                    and var2 = v_record_cl_asig(i).var2;

                                    n_nro_clust_actual := n_nro_clust_actual + 1;
                                else
                                    --se agrupa en el anterior
                                    update fdv_PROI_clust
                                    set clus = n_nro_clust_actual - 1
                                    where proc_cod_proc = pcodproceso
                                    and var1 = v_record_cl_asig(i).var1
                                    and var2 = v_record_cl_asig(i).var2;
                                end if;
                            end if;


                        end if;

                    end loop;

                    -- si la cantidad del ultimo nivel es < 7, se ajusta la agrupacion y se une al
                    -- anterior
                    if v_record_cl_asig(n_nro_total_regist).tot1 < n_nro_max_grupo then
                        update fdv_PROI_clust
                        set clus = (select max(clus)-1 from fdv_PROI_clust)
                        where proc_cod_proc = pcodproceso
                        and var1 = v_record_cl_asig(n_nro_total_regist).var1;
                    end if;

                    --actualizamos los valores que se marcaron anteriormente
                    update fdv_PROI_clust
                    set clus = 1
                    where proc_cod_proc = pcodproceso
                    and clus = 0;

                elsif n_cont_vari = 3 then

                    listaOrdenCampo1 := fdv_fn_split(trim(listaCampos(1)), ' ');
                    listaOrdenCampo2 := fdv_fn_split(trim(listaCampos(2)), ' ');
                    listaOrdenCampo3 := fdv_fn_split(trim(listaCampos(3)), ' ');

                    -- agrupamos y ordenamos los datos a procesar
                    v_query_a := ' ';

                    v_query_a := v_query_a||' select ';
                    v_query_a := v_query_a||' v1.var1, v2.var2, v3.var3, v1.tot1, v2.tot2, v3.tot3 ';
                    v_query_a := v_query_a||' from ( ';
                    v_query_a := v_query_a||'       select var1, count(*) tot1 ';
                    v_query_a := v_query_a||'       from fdv_PROI_clust ';
                    v_query_a := v_query_a||'       where proc_cod_proc = '||pcodproceso;
                    v_query_a := v_query_a||'       group by var1 ';
                    v_query_a := v_query_a||'       order by var1) v1, ( ';
                    v_query_a := v_query_a||'       select var1, var2, count(*) tot2 ';
                    v_query_a := v_query_a||'       from fdv_PROI_clust ';
                    v_query_a := v_query_a||'       where proc_cod_proc = '||pcodproceso;
                    v_query_a := v_query_a||'       group by var1, var2 ';
                    v_query_a := v_query_a||'       order by var1, var2) v2, ( ';
                    v_query_a := v_query_a||'       select var1, var2, var3, count(*) tot3 ';
                    v_query_a := v_query_a||'       from fdv_PROI_clust ';
                    v_query_a := v_query_a||'       where proc_cod_proc = '||pcodproceso;
                    v_query_a := v_query_a||'       group by var1, var2, var3 ';
                    v_query_a := v_query_a||'       order by var1, var2, var3 ) v3 ';
                    v_query_a := v_query_a||' where v1.var1 = v2.var1 ';
                    v_query_a := v_query_a||'       and v2.var1 = v3.var1 ';
                    v_query_a := v_query_a||'       and v2.var2 = v3.var2 ';
                    v_query_a := v_query_a||'       order by v1.var1 '||listaOrdenCampo1(2)||', v2.var2 '||listaOrdenCampo2(2)||', v3.var3 '||listaOrdenCampo3(2);

                    execute immediate v_query_a bulk collect into v_record_cl_asig;

                    n_nro_total_regist := v_record_cl_asig.count;
                    v_flag_proc := 'S';

                    for i in n_indic_inicio .. v_record_cl_asig.last loop

                        if v_record_cl_asig(i).tot2 >= n_nro_max_grupo then

                            n_nro_total_grupo := n_nro_total_grupo + v_record_cl_asig(i).tot3;

                            if(v_flag_proc = 'S') then
                                -- siempre se establece el valor en el primero
                                update fdv_PROI_clust
                                set clus = n_nro_clust_actual
                                where proc_cod_proc = pcodproceso
                                and var1 = v_record_cl_asig(i).var1
                                and var2 = v_record_cl_asig(i).var2
                                and var3 = v_record_cl_asig(i).var3;

                                n_nro_clust_actual := n_nro_clust_actual + 1;

                                v_flag_proc := 'N';

                                if n_nro_total_grupo = v_record_cl_asig(i).tot2 then
                                    n_nro_total_grupo := 0;
                                end if;
                            else
                               -- verificamos la cantidad del anterior
                                select count(*)
                                into n_nro_ante_zonas
                                from fdv_PROI_clust
                                where proc_cod_proc = pcodproceso
                                and clus = n_nro_clust_actual - 1;

                                -- verificamos si el nivel intermedio ya cerro
                                if n_nro_total_grupo = v_record_cl_asig(i).tot2 then
                                    n_nro_total_grupo := 0;
                                    --cierra en esta etapa, por lo tanto se agrupa el anterior al actual

                                    if v_record_cl_asig(i).tot3 < n_nro_max_grupo then
                                        --si el anterior es menor que el maximo, se agrupa con el actual

                                        update fdv_PROI_clust
                                        set clus = n_nro_clust_actual - 1
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1
                                        and var2 = v_record_cl_asig(i).var2
                                        and var3 = v_record_cl_asig(i).var3;
                                    else
                                        -- se inicia un nuevo grupo si el anterior es menor que el maximo
                                        if n_nro_ante_zonas < n_nro_max_grupo then
                                            -- en caso contrario se agrupa al actual
                                            update fdv_PROI_clust
                                            set clus = n_nro_clust_actual - 1
                                            where proc_cod_proc = pcodproceso
                                            and var1 = v_record_cl_asig(i).var1
                                            and var2 = v_record_cl_asig(i).var2
                                            and var3 = v_record_cl_asig(i).var3;

                                        else
                                            -- en caso contrario se agrupa al anterior
                                            update fdv_PROI_clust
                                            set clus = n_nro_clust_actual
                                            where proc_cod_proc = pcodproceso
                                            and var1 = v_record_cl_asig(i).var1
                                            and var2 = v_record_cl_asig(i).var2
                                            and var3 = v_record_cl_asig(i).var3;

                                            n_nro_clust_actual := n_nro_clust_actual + 1;
                                        end if;
                                    end if;

                                    --verificamos si hay un grupo anterior esperando ser asignado
                                    --y actualizamos con el cluster del primer grupo

                                    update fdv_PROI_clust
                                    set clus = (
                                        select min(clus) from fdv_PROI_clust
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1
                                        and var2 = v_record_cl_asig(i).var2
                                    )
                                    where proc_cod_proc = pcodproceso
                                    and clus = 0;

                                else
                                    -- estamos en el nivel intermedio

                                    -- verificar si esta empeszando un nuevo grupo de nivel
                                    -- superior

                                    if(v_record_cl_asig(i).var1 = v_record_cl_asig(i-1).var1) then
                                        -- estamos en el mismo nivel

                                        -- aun no cierra el nivel, se inicia un nuevo grupo, solo si
                                        -- el anterior > maximo

                                        if n_nro_ante_zonas >= n_nro_max_grupo then
                                            update fdv_PROI_clust
                                            set clus = n_nro_clust_actual
                                            where proc_cod_proc = pcodproceso
                                            and var1 = v_record_cl_asig(i).var1
                                            and var2 = v_record_cl_asig(i).var2
                                            and var3 = v_record_cl_asig(i).var3;

                                            n_nro_clust_actual := n_nro_clust_actual + 1;
                                        else
                                            --se agrupa en el anterior
                                            update fdv_PROI_clust
                                            set clus = n_nro_clust_actual - 1
                                            where proc_cod_proc = pcodproceso
                                            and var1 = v_record_cl_asig(i).var1
                                            and var2 = v_record_cl_asig(i).var2
                                            and var3 = v_record_cl_asig(i).var3;
                                        end if;

                                    else
                                        -- es un nuevo nivel

                                        update fdv_PROI_clust
                                        set clus = n_nro_clust_actual
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i).var1
                                        and var2 = v_record_cl_asig(i).var2
                                        and var3 = v_record_cl_asig(i).var3;

                                        n_nro_clust_actual := n_nro_clust_actual + 1;

                                    end if;

                                end if;


                            end if;

                        else

                            n_nro_total_grupo := n_nro_total_grupo + v_record_cl_asig(i).tot3;

                            -- inicializmaos el campo clust a cero,

                            update fdv_PROI_clust
                            set clus = 0
                            where proc_cod_proc = pcodproceso
                            and var1 = v_record_cl_asig(i).var1
                            and var2 = v_record_cl_asig(i).var2
                            and var3 = v_record_cl_asig(i).var3;

                            --se cierra el nivel superior o intermedio
                            if(i < n_nro_total_regist and v_record_cl_asig(i).var1 != v_record_cl_asig(i+1).var1) then
                                -- verificar si hay 8 o mas si es asi, agrupamos en un nuevo

                                select count(*)
                                into n_nro_zonas_singr
                                from fdv_PROI_clust
                                where proc_cod_proc = pcodproceso
                                and var1 = v_record_cl_asig(i).var1
                                and clus = 0;

                                --si el valor actual es menor que 8, se agrega al nro de sueltos
                                if n_nro_zonas_singr >= n_nro_max_grupo then
                                    update fdv_PROI_clust
                                    set clus = n_nro_clust_actual
                                    where proc_cod_proc = pcodproceso
                                    and var1 = v_record_cl_asig(i).var1
                                    and clus = 0;

                                    n_nro_clust_actual := n_nro_clust_actual + 1;
                                else
                                    -- se debe de agrupar al nivel anterior
                                    update fdv_PROI_clust
                                    set clus = (
                                        select max(clus) from fdv_PROI_clust
                                        where proc_cod_proc = pcodproceso
                                        and var1 = v_record_cl_asig(i-1).var1
                                    )
                                    where proc_cod_proc = pcodproceso
                                    and clus = 0;

                                end if;

                            end if;

                            if (n_nro_total_grupo = v_record_cl_asig(i).tot2) then
                                n_nro_total_grupo := 0;
                            end if;

                        end if;


                    end loop;

                    -- si hubieran grupos que faltaron clusterizar se agrupan al ultimo

                    update fdv_PROI_clust
                    set clus = (
                        select max(clus) from fdv_PROI_clust
                        where proc_cod_proc = pcodproceso
                    )
                    where proc_cod_proc = pcodproceso
                    and clus = 0;

                end if;


                -- Actualizamos los cluster calculados a la tabla de consolidados

                update fdv_zona_conso tco
                set
                    tco.clu_asig_sist = (select lpad(tmp.clus, 4, '0')
                                            from fdv_PROI_clust tmp
                                            where tmp.proc_cod_proc = tco.proc_cod_proc and tmp.cod_zona = tco.cod_zona),
                    tco.clu_asig_pais = (select lpad(tmp.clus, 4, '0')
                                            from fdv_PROI_clust tmp
                                            where tmp.proc_cod_proc = tco.proc_cod_proc and tmp.cod_zona = tco.cod_zona)
                where proc_cod_proc = pcodproceso
                and fla_zoco = 'S'
                and exists(select 1
                                from fdv_PROI_clust tmp
                                where tmp.proc_cod_proc = tco.proc_cod_proc and tmp.cod_zona = tco.cod_zona);
                --------------------------------------------------------------------------------------
            end if;
        end if;

    EXCEPTION
    WHEN OTHERS
    THEN
        ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
        pmensajeerror := ls_sqlerrm;
        raise_application_error (-20123,
        'FDV_PR_JERAR_VARIA: '
        || ls_sqlerrm
    );

    END FDV_PR_JERAR_VARIA;


   /**************************************************************************
   Descripcion       : Calcula el promedio de secciones em base a las
                        modificaciones que hizo el usuario
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_PROM_SECC (
      p_cod_proceso     VARCHAR2,
      pmensajeerror OUT VARCHAR2
   ) IS
   BEGIN

        --- INI-0001 ---
        -- Calculamos el promedio de las secciones del periodo que se desea distribuir
        -- tomando como referencia las secciones de las zonas que el usuario ha
        -- modificado y las que nó

        DELETE FROM FDV_PROI_PROME_SECCI WHERE PROC_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_PROME_SECCI(PROC_COD_PROC, COD_ZONA, PRO_SECC)
        SELECT
        PROC_COD_PROC,
        COD_ZONA,
        (CASE
            WHEN SUBSTR(CAM_CASE, 5) <= 6 THEN
                (CASE
                    WHEN NRO_SECO = 0 THEN NRO_SCCO
                    ELSE (NRO_SECO * (6 - SUBSTR(CAM_CASE, 5) + 1) + NRO_SCCO * (SUBSTR(CAM_CASE, 5) - 1))/6 END)
            WHEN CAM_CASE > 6 AND CAM_CASE <= 12 THEN
                (CASE
                    WHEN NRO_SECO = 0 THEN NRO_SCCO
                    ELSE (NRO_SECO * (12 - SUBSTR(CAM_CASE, 5) + 1) + NRO_SCCO * (SUBSTR(CAM_CASE, 5) - 6 - 1))/6 END)
            ELSE
                (CASE
                    WHEN NRO_SECO = 0 THEN NRO_SCCO
                    ELSE (NRO_SECO * (18 - SUBSTR(CAM_CASE, 5) + 1) + NRO_SCCO * (SUBSTR(CAM_CASE, 5) - 12 - 1))/6 END)
            END) PRO_SECC
        FROM FDV_ZONA_CONSO WHERE PROC_COD_PROC = p_cod_proceso AND FLA_ZOCO_PODE = 'S';
        -- --

        --Actualizamos el valor calculado en la tabla de consolidados
        UPDATE FDV_ZONA_CONSO ZC
        SET (ZC.PRO_SECC) =
            (SELECT TP.PRO_SECC
             FROM FDV_PROI_PROME_SECCI TP WHERE  TP.PROC_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA)
        WHERE ZC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_PROI_PROME_SECCI TP WHERE  TP.PROC_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA);
        -- --
        --- FIN-0001 ---

    EXCEPTION
    WHEN OTHERS
    THEN
        ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
        pmensajeerror := ls_sqlerrm;
        raise_application_error (-20123,
        'FDV_PR_PROC_CALC_PROM_SECC: '
        || ls_sqlerrm
    );
   END FDV_PR_PROC_CALC_PROM_SECC;


   /**************************************************************************
   Descripcion       : Calcula los benchmarks y los campos calculados
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             p_fal_cerrar  : Numero de campañas que faltan para cerrar el periodo
             p_aacc_inicio : Campaña de inicio en formato AAAACC -- INICIO DE PERIODO
             p_aacc_fin_perio : Campaña de fin-corte en formato AAAACC -- FIN DE PERIODO
             p_aacc_fin_corte : Campaña de fin-corte en formato AAAACC -- CAMPAÑA DE CORTE
             p_aacc_ini_ant : Campaña de inicio en formato AAAACC del periodo del año anterior
             p_aacc_ini_ant_men1 : Campaña de inicio MENOS 1 en formato AAAACC del periodo del año anterior
             p_aacc_fin_ant : Campaña de fin en formato AAAACC del periodo del año anterior
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_BNMK_CMCA (
      p_cod_proceso     VARCHAR2,
      p_fal_cerrar  OUT NUMBER,
      p_aacc_inicio OUT VARCHAR2,
      p_aacc_fin_perio OUT VARCHAR2,
      p_aacc_fin_corte OUT VARCHAR2,
      p_aacc_ini_ant OUT VARCHAR2,
      p_aacc_ini_ant_men1 OUT VARCHAR2,
      p_aacc_fin_ant OUT VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   ) IS
        v_per_sub NUMBER(12,4); -- Percentil Sub-Penetrado
        v_fac_sub NUMBER(12,4); -- Factor Sub-Penetrado
        v_pes_his NUMBER(12,4); -- Peso Histórico
        v_per_cap NUMBER(12,4); -- Percentil Capitalización
        v_per_act NUMBER(12,4); -- Percentil Actividad
        v_per_pup NUMBER(12,4); -- Percentil PUP
        v_per_ppu NUMBER(12,4); -- Percentil PPU

        v_cct_numero NUMBER;        -- Campaña de corte en formato entero

        v_anyo_corte VARCHAR2(4);   -- Año de corte en formato AAAA
        v_peri_proc VARCHAR2(1);    -- Periodo de proceso
        v_peri_anpr VARCHAR2(4);    -- Año del Periodo de proceso

        v_pct_numero NUMBER;        -- Año del periodo de corte en formato entero
        v_cam_corte VARCHAR2(2);    -- Campaña de corte en formato CC
        v_fal_cerrar NUMBER;        -- Numero de campañas que faltan para cerrar el periodo

        v_aacc_inicio VARCHAR2(6);  -- Campaña de inicio en formato AAAACC -- INICIO DE PERIODO

        v_aacc_fin_corte VARCHAR2(6);     -- Campaña de fin-corte en formato AAAACC -- CAMPAÑA DE CORTE
        v_aacc_fin_perio VARCHAR2(6);     -- Campaña de fin-corte en formato AAAACC -- FIN DE PERIODO

        v_aacc_ini_ant VARCHAR2(6);  -- Campaña de inicio en formato AAAACC del periodo del año anterior
        v_aacc_ini_ant_men1 VARCHAR2(6);  -- Campaña de inicio MENOS 1 en formato AAAACC del periodo del año anterior
        v_aacc_fin_ant VARCHAR2(6);  -- Campaña de fin en formato AAAACC del periodo del año anterior

   BEGIN

        -- Obtenemos los datos necesarios del proceso
        SELECT ANY_PROC, CAM_PROC, PER_PROC, APE_PROC
        INTO v_anyo_corte, v_cam_corte, v_peri_proc, v_peri_anpr
        FROM FDV_PROCE
        WHERE COD_PROC = p_cod_proceso;
        -- --

        --- INI-0002 ---
        -- Calculamos los Benchmarks - Por cada una de las ZONAS CAPS
        -- obtenemos los valores de los parametros a usar

        -- Percentil Subpenetrado
        SELECT VAL_PARA INTO v_per_sub FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_per_sub;

        -- Percentil Capitalizacion
        SELECT VAL_PARA INTO v_per_cap FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_per_cap;

        -- Percentil Actividad
        SELECT VAL_PARA INTO v_per_act FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_per_act;

        -- Percentil PUP
        SELECT VAL_PARA INTO v_per_pup FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_per_pup;

        -- Percentil PPU
        SELECT VAL_PARA INTO v_per_ppu FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_per_ppu;

        --
        DELETE FROM FDV_PROI_BMARK WHERE proc_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_BMARK(proc_COD_PROC, COD_ZOCA, BMK_PNTR, BMK_CAPI, BMK_ACTI, BMK_PUP, BMK_PPU)
        SELECT
        p_cod_proceso,
        ZON_CAPS,
        PERCENTILE_CONT(v_per_sub) WITHIN GROUP (ORDER BY VAL_PNTR)  BMK_PNTR,
        PERCENTILE_CONT(v_per_cap) WITHIN GROUP (ORDER BY PRO_CARE_NISE)  BMK_CAPI,
        PERCENTILE_CONT(v_per_act) WITHIN GROUP (ORDER BY PRO_ACRE)  BMK_ACTI,
        PERCENTILE_CONT(v_per_pup) WITHIN GROUP (ORDER BY PRO_PUPR)  BMK_PUP,
        PERCENTILE_CONT(v_per_ppu) WITHIN GROUP (ORDER BY PRO_PPUR)  BMK_PPU
        FROM (
            SELECT
            ZON_CAPS,
            VAL_PNTR, PRO_CARE_NISE, PRO_ACRE, PRO_PUPR, PRO_PPUR
            FROM FDV_ZONA_CONSO
            WHERE PROC_COD_PROC = p_cod_proceso
            AND FLA_ZOCO_PODE = 'S')
        GROUP BY ZON_CAPS;

        -- Actualizamos los valores calculados en la tabla de consolidados

        -- Primero actuializamos los que fueron asignados por el sistema
        UPDATE FDV_ZONA_CONSO ZC
                SET (ZC.BMK_PNTR, ZC.BMK_CAPI, ZC.BMK_ACTI, ZC.BMK_PUP, ZC.BMK_PPU) =
                    (SELECT TP.BMK_PNTR, TP.BMK_CAPI, TP.BMK_ACTI, TP.BMK_PUP, TP.BMK_PPU
                     FROM FDV_PROI_BMARK TP
                     WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZOCA = ZC.ZON_CAPS)
                WHERE ZC.PROC_COD_PROC = p_cod_proceso
                AND EXISTS (SELECT 1 FROM FDV_PROI_BMARK TP WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZOCA = ZC.ZON_CAPS);
        --- FIN-0002 ---

        --- INI-0003 ---
        -- Actualizaciones de campos calculados
        -- obtenemos los valores de los parametros a usar

        -- Factor Subpenetrado
        SELECT VAL_PARA INTO v_fac_sub FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_fac_sub;

        -- Peso Historico
        SELECT VAL_PARA INTO v_pes_his FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_pes_his;


        -- 1 - Actualizamos el campo calculado, Factor Penetracion
        UPDATE FDV_ZONA_CONSO
        SET CCA_FACT_PNTR = (CASE WHEN VAL_PNTR < BMK_PNTR THEN v_fac_sub ELSE 1 END)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';
        --

        -- 2 - Actualizamos el campo calculado, Capitalización Objetivo
        UPDATE FDV_ZONA_CONSO
        SET CCA_CAPI_OBJT = (CASE WHEN PRO_CARE_NISE > BMK_CAPI THEN PRO_CARE_NISE ELSE BMK_CAPI END) *
                            PRO_SECC * CCA_FACT_PNTR *
                            (1 - v_pes_his)  + (PRO_CARE_NISE * PRO_SECC * v_pes_his)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';
        --

        -- 3 - Actualizamos el campo calculado, Capitalización del Periodo
        UPDATE FDV_ZONA_CONSO
        SET CCA_CAPI_PERI = 6 * CCA_CAPI_OBJT
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';
        --

        -- Calculamos los valores iniciales para el campo calculado Activas iniciales

        -- Campañas de inicio, fin y numero de campañas que faltan para cerrar el periodo
        -- al que pertenece la campaña de corte
        -- Periodo del año anterior del periodo actual, campaña inicio y fin del periodo del año anterior
        v_cct_numero := to_number(v_cam_corte);
        if v_cct_numero < 6 then
            v_fal_cerrar := 6 - v_cct_numero;
            v_aacc_inicio := v_anyo_corte||'01';
            v_aacc_fin_perio := v_anyo_corte||'06';
        elsif v_cct_numero > 6 and v_cct_numero < 12 then
            v_fal_cerrar := 12 - v_cct_numero;
            v_aacc_inicio := v_anyo_corte||'07';
            v_aacc_fin_perio := v_anyo_corte||'12';
        elsif v_cct_numero > 12 and v_cct_numero < 18 then
            v_fal_cerrar := 18 - v_cct_numero;
            v_aacc_inicio := v_anyo_corte||'13';
            v_aacc_fin_perio := v_anyo_corte||'18';
        end if;

        v_aacc_fin_corte := v_anyo_corte||v_cam_corte;

        -- Campaña de inicio y fin del mismo periodo en el que se quiere distribuir la meta, pero del año anterior
        v_pct_numero := to_number(v_peri_anpr);

        if v_peri_proc = '1' then
            v_aacc_ini_ant := TO_CHAR((v_pct_numero-1))||'01';
            v_aacc_ini_ant_men1 := TO_CHAR((v_pct_numero-2))||'18';
            v_aacc_fin_ant := TO_CHAR((v_pct_numero-1))||'06';
        elsif v_peri_proc = '2' then
            v_aacc_ini_ant := TO_CHAR((v_pct_numero-1))||'07';
            v_aacc_ini_ant_men1 := TO_CHAR((v_pct_numero-1))||'06';
            v_aacc_fin_ant := TO_CHAR((v_pct_numero-1))||'12';
        elsif v_peri_proc = '3' then
            v_aacc_ini_ant := TO_CHAR((v_pct_numero-1))||'13';
            v_aacc_ini_ant_men1 := TO_CHAR((v_pct_numero-1))||'12';
            v_aacc_fin_ant := TO_CHAR((v_pct_numero-1))||'18';
        end if;
        -- --

        -- Calculamos el promedio de capitalizacion de las campañas pasadas del periodo al que pertenece la campaña de corte
        DELETE FROM FDV_PROI_PROME_CAPIT WHERE proc_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_PROME_CAPIT(proc_COD_PROC, COD_ZONA, PRO_CAPI, ACT_FINA, FLA_PROC)
        SELECT
        p_cod_proceso,
        BZ.BZO_ZONA,
        AVG(BZ.BZO_CAPI_REAL) PRO_CAPI,
        0,
        'N'
        FROM FDV_BASE_ZONA BZ
        WHERE BZ.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA)
        AND BZ.BZO_ANYO||BZ.BZO_CAMP >= v_aacc_inicio
        AND BZ.BZO_ANYO||BZ.BZO_CAMP <= v_aacc_fin_corte
        GROUP BY BZ.BZO_ZONA;

        -- Calculamos las activas finales de la campaña de corte
        INSERT INTO FDV_PROI_PROME_CAPIT(proc_COD_PROC, COD_ZONA, PRO_CAPI, ACT_FINA, FLA_PROC)
        SELECT
        p_cod_proceso,
        BZ.BZO_ZONA,
        0,
        BZ.BZO_ACTI_REAL,
        'N'
        FROM FDV_BASE_ZONA BZ
        WHERE BZ.PROC_COD_PROC = p_cod_proceso
        -- Calculamos las activas Finales de todas las zonas, Las que son confiables y las que no son confiables
        AND BZ.BZO_ANYO||BZO_CAMP = v_aacc_fin_corte
        ORDER BY BZ.BZO_ZONA;

        --Agrupamos los datos calculados en la tabla temporal e insertamos los valores en la misma tabla
        INSERT INTO FDV_PROI_PROME_CAPIT(proc_COD_PROC, COD_ZONA, PRO_CAPI, ACT_FINA, FLA_PROC)
        SELECT p_cod_proceso, COD_ZONA, SUM(PRO_CAPI) PRO_CAPI, SUM(ACT_FINA) ACT_FINA, 'S'
        FROM FDV_PROI_PROME_CAPIT
        WHERE proc_COD_PROC = p_cod_proceso
        GROUP BY COD_ZONA;

        -- 4 - Actualizamos el campo calculado, Activas Iniciales
        UPDATE FDV_ZONA_CONSO ZC
        SET ZC.CCA_ACTI_INIC =
            (
                SELECT (TP.ACT_FINA + (v_fal_cerrar * TP.PRO_CAPI))
                FROM FDV_PROI_PROME_CAPIT TP
                WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC
                AND TP.COD_ZONA = ZC.COD_ZONA
                AND TP.FLA_PROC = 'S'
             )
        WHERE ZC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_PROI_PROME_CAPIT TP WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA AND TP.FLA_PROC = 'S');
        --

        -- 5 - Actualizamos el campo calculado, Activas Finales
        -- Para las zonas confiables, La capitalizacion objetivo esta calculada, para las demás las calculamos posteriormente
        -- En base a la capitalizacion objetivo de las confiables
        UPDATE FDV_ZONA_CONSO
        SET CCA_ACTI_FINA = CCA_ACTI_INIC + CCA_CAPI_PERI
        WHERE FLA_ZOCO_PODE = 'S'
        AND PROC_COD_PROC = p_cod_proceso;
        --

        -- Calculamos las activas del año anterior, en funcion a la data del periodo del año anterior
        DELETE FROM FDV_PROI_PROME_AANTE WHERE proc_COD_PROC = p_cod_proceso;

        -- Calculamos las activas PROMEDIO del periodo actual del año anterior
        INSERT INTO FDV_PROI_PROME_AANTE(proc_COD_PROC, COD_ZONA, ACT_PROM, ACT_INIC, ACT_FINA, FLA_PROC)
        SELECT
        p_cod_proceso,
        BZ.BZO_ZONA,
        AVG(BZ.BZO_ACTI_REAL),
        0,
        0,
        'N'
        FROM FDV_BASE_ZONA BZ
        WHERE BZ.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'S')
        AND BZ.BZO_ANYO||BZO_CAMP >= v_aacc_ini_ant
        AND BZ.BZO_ANYO||BZO_CAMP <= v_aacc_fin_ant
        GROUP BY BZ.BZO_ZONA;

        -- Calculamos las activas INICIALES del periodo actual del año anterior
        -- En este caso se CONSIDERA LA CAMPAÑA ANTERIOR AL PERIODO DE INICIO
        INSERT INTO FDV_PROI_PROME_AANTE(proc_COD_PROC, COD_ZONA, ACT_PROM, ACT_INIC, ACT_FINA, FLA_PROC)
        SELECT
        p_cod_proceso,
        BZ.BZO_ZONA,
        0,
        AVG(BZ.BZO_ACTI_REAL),
        0,
        'N'
        FROM FDV_BASE_ZONA BZ
        WHERE BZ.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'S')
        AND BZ.BZO_ANYO||BZO_CAMP = v_aacc_ini_ant_men1
        GROUP BY BZ.BZO_ZONA;

        -- Calculamos las activas FINALES del periodo actual del año anterior
        INSERT INTO FDV_PROI_PROME_AANTE(proc_COD_PROC, COD_ZONA, ACT_PROM, ACT_INIC, ACT_FINA, FLA_PROC)
        SELECT
        p_cod_proceso,
        BZ.BZO_ZONA,
        0,
        0,
        AVG(BZ.BZO_ACTI_REAL),
        'N'
        FROM FDV_BASE_ZONA BZ
        WHERE BZ.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'S')
        AND BZ.BZO_ANYO||BZO_CAMP = v_aacc_fin_ant
        GROUP BY BZ.BZO_ZONA;

        -- Agrupamos los datos calculados anteriormente
        INSERT INTO FDV_PROI_PROME_AANTE(proc_COD_PROC, COD_ZONA, ACT_PROM, ACT_INIC, ACT_FINA, FLA_PROC)
        SELECT p_cod_proceso, COD_ZONA, SUM(ACT_PROM), SUM(ACT_INIC), SUM(ACT_FINA), 'S'
        FROM FDV_PROI_PROME_AANTE
        WHERE proc_COD_PROC = p_cod_proceso
        GROUP BY COD_ZONA;

        -- Actualizamos los campos calculados
        -- 6 - Activas Promedio AA
        -- 7 - Activas Iniciales AA
        -- 8 - Activas Finales AA
        UPDATE FDV_ZONA_CONSO ZC
        SET (ZC.CCA_ACTI_PRAA, ZC.CCA_ACTI_INAA, ZC.CCA_ACTI_FIAA) =
            (
                SELECT TP.ACT_PROM, TP.ACT_INIC, TP.ACT_FINA
                FROM FDV_PROI_PROME_AANTE TP
                WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC
                AND TP.COD_ZONA = ZC.COD_ZONA
                AND TP.FLA_PROC = 'S'
             )
        WHERE ZC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_PROI_PROME_AANTE TP WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA AND TP.FLA_PROC = 'S');

------------------------<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<
COMMIT;

        -- Actualizamos el campo calculado
        -- 9 - FACTOR1 = (2 * ApromAA)/(AinicAA + AfinAA)
        UPDATE FDV_ZONA_CONSO
        SET CCA_FAC1 = (2 * CCA_ACTI_PRAA) / (CCA_ACTI_INAA + CCA_ACTI_FIAA)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';


        -- Actualizamos el campo calculado,
        -- 10 - Activas Promedio ---- ((M + O)/2) * FACTOR1
        UPDATE FDV_ZONA_CONSO
        SET CCA_ACTI_PROM = ((CCA_ACTI_INIC + CCA_ACTI_FINA)/2) * CCA_FAC1
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';

        -- Actualizamos el campo calculado
        -- 11 - Actividad
        UPDATE FDV_ZONA_CONSO
        SET CCA_ACVD = (CASE WHEN PRO_ACRE < BMK_ACTI THEN BMK_ACTI ELSE PRO_ACRE END)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';

        -- Actualizamos el campo calculado
        -- 12 - PUP
        UPDATE FDV_ZONA_CONSO
        SET CCA_PUP = (CASE WHEN PRO_PUPR < BMK_PUP THEN BMK_PUP ELSE PRO_PUPR END)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';

        -- Actualizamos el campo calculado
        -- 13 - PPU
        UPDATE FDV_ZONA_CONSO
        SET CCA_PPU = (CASE WHEN PRO_PPUR < BMK_PPU THEN BMK_PPU ELSE PRO_PPUR END)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';

        --- FIN-0003 ---

      p_fal_cerrar := v_fal_cerrar;
      p_aacc_inicio := v_aacc_inicio;
      p_aacc_fin_perio := v_aacc_fin_perio;
      p_aacc_fin_corte := v_aacc_fin_corte;
      p_aacc_ini_ant := v_aacc_ini_ant;
      p_aacc_ini_ant_men1 := v_aacc_ini_ant_men1;
      p_aacc_fin_ant := v_aacc_fin_ant;

    EXCEPTION
    WHEN OTHERS
    THEN
        ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
        p_mensajeerror := ls_sqlerrm;
        raise_application_error (-20123,
        'FDV_PR_PROC_CALC_BNMK_CMCA: '
        || ls_sqlerrm
    );
   END FDV_PR_PROC_CALC_BNMK_CMCA;

   /**************************************************************************
   Descripcion       : Calcula la venta potencial e indices relativos
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_VEPO_INRE (
      p_cod_proceso     VARCHAR2,
      p_aacc_ini_ant VARCHAR2,
      p_aacc_fin_ant VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   ) IS

    v_suma_vent_pote NUMBER(38,10);  -- Suma Venta Potencial

   BEGIN

        --- INI-0004 --- Calculo de Venta Potencial

        -- Escenario Año Anterior
        -- Calculamos las sumas de las campañas del periodo actual, pero del año anterior
        DELETE FROM FDV_PROI_POTEN_EAANT WHERE proc_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_POTEN_EAANT(proc_COD_PROC, COD_ZONA, PEDI_REAL, PMNP_REAL, VENT_REAL)
        SELECT
        p_cod_proceso,
        BZ.BZO_ZONA,
        AVG(BZ.BZO_PEDI_REAL) PEDI_REAL,
        AVG(BZ.BZO_PMNP_REAL) PMNP_REAL,
        AVG(BZ.BZO_VENT_REAL) VENT_REAL
        FROM FDV_BASE_ZONA BZ
        WHERE BZ.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'S')
        AND BZ.BZO_ANYO||BZO_CAMP >= p_aacc_ini_ant
        AND BZ.BZO_ANYO||BZO_CAMP <= p_aacc_fin_ant
        GROUP BY BZ.BZO_ZONA;

        -- Actualizamos a la tabla de consolidados los valores calculados anteriormente
        UPDATE FDV_ZONA_CONSO ZC
        SET (ZC.POT_EAAN_PEDI, ZC.POT_EAAN_PMNP, ZC.POT_EAAN_VENT) =
            (
                SELECT TP.PEDI_REAL, TP.PMNP_REAL, TP.VENT_REAL
                FROM FDV_PROI_POTEN_EAANT TP
                WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC
                AND TP.COD_ZONA = ZC.COD_ZONA
             )
        WHERE ZC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1 FROM FDV_PROI_POTEN_EAANT TP WHERE TP.proc_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA);

        -- Escenario Potencial - Zonas Confiables

        -- Calculamos el numero de pedidos potenciales
        -- X = ActivasPromedio x 6 x Actividad
        UPDATE FDV_ZONA_CONSO
        SET POT_PEDI_POTE = CCA_ACTI_PROM * 6 * CCA_ACVD
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';

        -- Calculamos el PMNP potencial
        -- Y = PUP x PPU
        UPDATE FDV_ZONA_CONSO
        SET POT_PMNP_POTE = CCA_PUP * CCA_PPU
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';

        -- Calculamos El FACTOR2
        -- FACTOR2 = VentaREAA/(PedidosREAA*PMNPREAA)
        UPDATE FDV_ZONA_CONSO
        SET POT_FAC2 = POT_EAAN_VENT/(POT_EAAN_PEDI * POT_EAAN_PMNP)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';

        -- Calculamos la Venta Potencial
        -- VP = X * Y * FACTOR2
        -- FACTOR2 = VentaREAA/(PedidosREAA*PMNPREAA)
        UPDATE FDV_ZONA_CONSO
        SET POT_VENT_POTE = POT_PEDI_POTE * POT_PMNP_POTE * POT_FAC2
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_PODE = 'S';
        -- --

        -- Escenario Potencial - Zonas No Confiables
        -- Los procesos anteriores de Activas Iniciales ya calcularon este valor para todas las zonas
        -- Para este caso en particular solo falta calcular las Activas Finales

        -- Calculamos un promedio por zonas CAPS de las variables
        -- Capitalizacion Objetivo
        -- Actividad
        -- PUP
        -- PPU
        -- FACTOR1
        -- FACTOR2
        DELETE FROM FDV_PROI_POTEN_PROMR WHERE proc_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_POTEN_PROMR(proc_COD_PROC, COD_ZOCA, PRO_CAOB, PRO_ACVD, PRO_PUP, PRO_PPU, PRO_FAC1, PRO_FAC2)
        SELECT
        p_cod_proceso,
        TP.ZON_CAPS,
        AVG(TP.CCA_CAPI_OBJT) CAPI_OBJT,
        AVG(TP.CCA_ACVD) ACVD,
        AVG(TP.CCA_PUP) PUP,
        AVG(TP.CCA_PPU) PPU,
        AVG(TP.CCA_FAC1) FAC1,
        AVG(TP.POT_FAC2) FAC2
        FROM (
            SELECT
            ZON_CAPS,
            CCA_CAPI_OBJT,
            CCA_ACVD,
            CCA_PUP,
            CCA_PPU,
            CCA_FAC1,
            POT_FAC2
            FROM FDV_ZONA_CONSO
            WHERE PROC_COD_PROC = p_cod_proceso
            AND FLA_ZOCO_PODE = 'S'
            ) TP
        GROUP BY TP.ZON_CAPS;

        -- Calculamos las

        -- Actualizamos los indicadores calculados en la tabla de consolidados
        UPDATE FDV_ZONA_CONSO ZC
        SET (CCA_CAPI_OBJT, CCA_ACVD, CCA_PUP, CCA_PPU, CCA_FAC1, POT_FAC2) =
                (
                SELECT TP.PRO_CAOB, TP.PRO_ACVD, TP.PRO_PUP, TP.PRO_PPU, TP.PRO_FAC1, TP.PRO_FAC2
                FROM FDV_PROI_POTEN_PROMR TP
                WHERE  ZC.PROC_COD_PROC = TP.proc_COD_PROC
                AND ZC.ZON_CAPS = TP.COD_ZOCA
                )
        WHERE ZC.PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND ZC.FLA_ZOCO_PODE = 'N'
        AND EXISTS (SELECT 1 FROM FDV_PROI_POTEN_PROMR TP WHERE  ZC.PROC_COD_PROC = TP.PROC_COD_PROC AND ZC.ZON_CAPS = TP.COD_ZOCA);

        -- Calculamos y actualizamos las activas finales para las zonas que no son confiables
        UPDATE FDV_ZONA_CONSO
        SET CCA_ACTI_FINA = CCA_ACTI_INIC + (CCA_CAPI_OBJT * 6)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_PODE = 'N';

        -- Calculamos y actualizamos las activas promedio para las zonas no confiables
        UPDATE FDV_ZONA_CONSO
        SET CCA_ACTI_PROM = ((CCA_ACTI_INIC + CCA_ACTI_FINA)/2) * CCA_FAC1
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_PODE = 'N';

        -- Calculamos y actualizamos LOS PEDIDOS POTENCIALES para las zonas no confiables
        UPDATE FDV_ZONA_CONSO
        SET POT_PEDI_POTE = CCA_ACTI_PROM * 6 * CCA_ACVD
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_PODE = 'N';

        -- Calculamos y actualizamos El PMNP para las zonas no confiables
        UPDATE FDV_ZONA_CONSO
        SET POT_PMNP_POTE = CCA_PPU * CCA_PUP
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_PODE = 'N';

        -- Calculamos y actualizamos La Venta Potencial para las zonas no confiables
        UPDATE FDV_ZONA_CONSO
        SET POT_VENT_POTE = POT_PEDI_POTE * POT_PMNP_POTE * POT_FAC2
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_PODE = 'N';
        -- --
        --- FIN-0004 ---

        --- INI-0005---
        -- Calculo de Indices relativos

        --Sumamos toda la venta potencial de todas las zonas
        SELECT SUM(POT_VENT_POTE)
        INTO v_suma_vent_pote
        FROM FDV_ZONA_CONSO
        WHERE PROC_COD_PROC = p_cod_proceso;

        -- Actualizamos el valor en la columna
        UPDATE FDV_ZONA_CONSO
        SET POT_INDI_RELA = POT_VENT_POTE/v_suma_vent_pote
        WHERE PROC_COD_PROC = p_cod_proceso;

        --- FIN-0005 ---

    EXCEPTION
    WHEN OTHERS
    THEN
        ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
        p_mensajeerror := ls_sqlerrm;
        raise_application_error (-20123,
        'FDV_PR_PROC_CALC_VEPO_INRE: '
        || ls_sqlerrm
    );
   END FDV_PR_PROC_CALC_VEPO_INRE;


   /**************************************************************************
   Descripcion       : Realiza el proceso de calculo de decante
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
             p_aacc_ini_ant : Campaña de inicio en formato AAAACC del periodo del año anterior
             p_aacc_fin_ant : Campaña de fin en formato AAAACC del periodo del año anterior
             p_meta_vndi : Meta de venta a distribuir
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_CALC_DECA (
      p_cod_proceso  VARCHAR2,
      p_aacc_ini_ant VARCHAR2,
      p_aacc_fin_ant VARCHAR2,
      p_meta_vndi NUMBER,
      p_mensajeerror OUT VARCHAR2
   ) IS

        v_camp_inic NUMBER; -- Campaña de inicio del perido del año anterior en el que se quiere distribuir la meta
        v_codi_para NUMBER; -- Código del parametro inicial Cx+1, en formato numerico y los demas son secuenciales

        v_peh_vnt NUMBER(12,4); -- Peso Histórico de Ventas
        v_pev_pcr NUMBER(12,4); -- Peso de Ventas Po

   BEGIN

        --- INI DECANTE ----

        -- Insertamos para todas las zonas el rango de campañas

        DELETE FROM FDV_PROI_DECAN_CALCU_BASE WHERE proc_COD_PROC = p_cod_proceso;

        v_camp_inic := to_number(substr(p_aacc_ini_ant, 5));
        v_codi_para := 13;

        FOR v_conta IN 1..6
        LOOP
            -- zonas confiables
            INSERT INTO FDV_PROI_DECAN_CALCU_BASE(proc_COD_PROC, COD_ZONA, CAMPANYA)
            SELECT
            DISTINCT p_cod_proceso, BZ.BZO_ZONA, substr(v_camp_inic + 100, 2)
            FROM FDV_BASE_ZONA BZ
            WHERE BZ.PROC_COD_PROC = p_cod_proceso
            AND BZ.BZO_ANYO||BZO_CAMP >= p_aacc_ini_ant
            AND BZ.BZO_ANYO||BZO_CAMP <= p_aacc_fin_ant
            AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'S');
            --

            -- zonas NO confiables
            INSERT INTO FDV_PROI_DECAN_CALCU_BASE(proc_COD_PROC, COD_ZONA, CAMPANYA)
            SELECT
            DISTINCT p_cod_proceso, BZ.BZO_ZONA, substr(v_camp_inic + 100, 2)
            FROM FDV_BASE_ZONA BZ
            WHERE BZ.PROC_COD_PROC = p_cod_proceso
            AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'N' AND ZC.ZON_CAPS IS NOT NULL);
            --

            UPDATE FDV_PROI_DECAN_CALCU_BASE
            SET PRO_REAL_PROJ = (SELECT VAL_PARA FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso
                                AND PARA_COD_PARA = substr(v_codi_para + 1000, 2))
            WHERE proc_COD_PROC = p_cod_proceso
            AND CAMPANYA = substr(v_camp_inic + 100, 2);

            v_camp_inic := v_camp_inic + 1;
            v_codi_para := v_codi_para + 1;

        END LOOP;
        -- --

        -- Insertamos los datos de ventas calculados en una temporal
        -- Calculando los porcentajes de ventas
        DELETE FROM FDV_PROI_DECAN_CALCU WHERE proc_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_DECAN_CALCU(proc_COD_PROC, COD_ZONA, CAMPANYA, PCT_VENT)
        SELECT
        p_cod_proceso COD_PROC,
        T.COD_ZONA,
        T.CAMPANYA,
        RATIO_TO_REPORT(T.VENTA) OVER (PARTITION BY T.COD_ZONA) PCT_VENT
        FROM(
                SELECT
                BZ.BZO_ZONA COD_ZONA,
                BZ.BZO_CAMP CAMPANYA,
                ROUND(SUM(BZ.BZO_VENT_REAL), li_nro_deci) VENTA
                FROM FDV_BASE_ZONA BZ
                WHERE BZ.PROC_COD_PROC = p_cod_proceso
                AND BZ.BZO_ANYO||BZO_CAMP >= p_aacc_ini_ant
                AND BZ.BZO_ANYO||BZO_CAMP <= p_aacc_fin_ant
                AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'S')
                GROUP BY BZ.BZO_ZONA, BZO_CAMP
                ORDER BY BZ.BZO_ZONA, BZO_CAMP) T;
        -- --

        -- Actualizamos los indices de ventas historicas
        UPDATE FDV_PROI_DECAN_CALCU_BASE TP1
        SET TP1.PCT_VENT = (
            SELECT TP2.PCT_VENT
            FROM FDV_PROI_DECAN_CALCU TP2
            WHERE TP2.PROC_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA
            AND TP2.CAMPANYA = TP1.CAMPANYA)
        WHERE TP1.proc_COD_PROC = p_cod_proceso
        AND EXISTS(SELECT 1 FROM FDV_PROI_DECAN_CALCU TP2
            WHERE TP2.proc_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA
            AND TP2.CAMPANYA = TP1.CAMPANYA);
        -- --

        -- Actualizamos el peso de venta por campaña a nivel de zona - T
        -- Para aquellas zonas que se excluyen del cálculo, únicamente se les asigna la  Distribución % por Campaña (Profit Real Proy.)
        -- Peso Historico de Ventas
        SELECT VAL_PARA INTO v_peh_vnt FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_peh_vnt;

        -- Peso de Ventas P0
        SELECT VAL_PARA INTO v_pev_pcr FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_pev_pcr;

        UPDATE FDV_PROI_DECAN_CALCU_BASE
        SET PCT_VENT_CAZO = (CASE WHEN PCT_VENT = 0 THEN PRO_REAL_PROJ ELSE PCT_VENT*v_peh_vnt + PRO_REAL_PROJ*v_pev_pcr END)
        WHERE proc_COD_PROC = p_cod_proceso;
        -- --

        -- Calculamos la meta de venta del periodo DEC_META_VENT_AJPEONDERADO- S
        UPDATE FDV_ZONA_CONSO
        SET DEC_META_PERI = p_meta_vndi * POT_INDI_RELA
        WHERE PROC_COD_PROC = p_cod_proceso;
        -- --

        -- Calculamos la Meta de Venta o Potencial Por Campaña (META POR CAMPAÑA), a nivel de zona I = S x T
        UPDATE FDV_PROI_DECAN_CALCU_BASE TP
        SET TP.VEN_POTE = PCT_VENT_CAZO * (SELECT ZC.DEC_META_PERI
                                FROM FDV_ZONA_CONSO ZC
                                WHERE ZC.PROC_COD_PROC = TP.proc_COD_PROC AND ZC.COD_ZONA = TP.COD_ZONA)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND EXISTS(SELECT 1
                                FROM FDV_ZONA_CONSO ZC
                                WHERE ZC.PROC_COD_PROC = TP.proc_COD_PROC AND ZC.COD_ZONA = TP.COD_ZONA);
        -- --

        -- Calculamos el Indice Relativo por zona y campaña (INDICE RELATIVO AJUSTADO POR CAMPAÑA) - R
        DELETE FROM FDV_PROI_DECAN_INDR WHERE proc_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_DECAN_INDR(proc_COD_PROC, COD_ZONA, CAMPANYA, INDR)
        SELECT
        p_cod_proceso, T1.COD_ZONA, T1.CAMPANYA, (VEN_POTE/SUM_VEN_POTE) INDR
        FROM (
                SELECT COD_ZONA, CAMPANYA, VEN_POTE
                FROM FDV_PROI_DECAN_CALCU_BASE
                WHERE proc_COD_PROC = p_cod_proceso) T1,
             (
                SELECT CAMPANYA, SUM(VEN_POTE) SUM_VEN_POTE
                FROM FDV_PROI_DECAN_CALCU_BASE
                WHERE proc_COD_PROC = p_cod_proceso
                GROUP BY CAMPANYA) T2
        WHERE T1.CAMPANYA = T2.CAMPANYA;


        UPDATE FDV_PROI_DECAN_CALCU_BASE T1
        SET T1.VEN_POTE_INDR = (SELECT T2.INDR
                            FROM FDV_PROI_DECAN_INDR T2
                            WHERE T2.proc_COD_PROC = T1.proc_COD_PROC
                            AND T2.COD_ZONA = T1.COD_ZONA
                            AND T2.CAMPANYA = T1.CAMPANYA)
        WHERE T1.proc_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1
                            FROM FDV_PROI_DECAN_INDR T2
                            WHERE T2.proc_COD_PROC = T1.proc_COD_PROC
                            AND T2.COD_ZONA = T1.COD_ZONA
                            AND T2.CAMPANYA = T1.CAMPANYA);
        -- --

        -- Calculamos el Decante de la Meta de Venta (META AJUSTADA POR CAMPAÑA)
        -- Es el valor que ingresa al ajuste de colas
        UPDATE FDV_PROI_DECAN_CALCU_BASE
        SET DEC_META_VENT = VEN_POTE_INDR * p_meta_vndi * PRO_REAL_PROJ
        WHERE proc_COD_PROC = p_cod_proceso;
        -- --

        --Calculamos la Meta de Venta Ajustada al Periodo  L
        -- Venta Propuesta por la herramienta
        UPDATE FDV_ZONA_CONSO ZC
        SET ZC.DEC_META_VENT_AJPE = (
            SELECT SUM(TP.DEC_META_VENT)
            FROM FDV_PROI_DECAN_CALCU_BASE TP
            WHERE TP.PROC_COD_PROC = ZC.PROC_COD_PROC
            AND TP.COD_ZONA = ZC.COD_ZONA)
        WHERE ZC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1
            FROM FDV_PROI_DECAN_CALCU_BASE TP
            WHERE TP.PROC_COD_PROC = ZC.PROC_COD_PROC
            AND TP.COD_ZONA = ZC.COD_ZONA);
        -- --

        --- FIN Decante ---

    EXCEPTION
    WHEN OTHERS
    THEN
        ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
        p_mensajeerror := ls_sqlerrm;
        raise_application_error (-20123,
        'FDV_PR_PROC_CALC_DECA: '
        || ls_sqlerrm
    );
   END FDV_PR_PROC_CALC_DECA;


   /**************************************************************************
   Descripcion       : Realiza el proceso de ajuste, de colas y de metas por campaña
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_AJUS_COLA_MECA (
      p_cod_proceso  VARCHAR2,
      p_fal_cerrar  NUMBER,
      p_aacc_inicio VARCHAR2,
      p_aacc_fin_perio VARCHAR2,
      p_aacc_fin_corte VARCHAR2,
      p_meta_vndi NUMBER,
      p_mensajeerror OUT VARCHAR2
   ) IS

        v_nro_camp_vrea     NUMBER; --Numero de campañas con datos de venta real del periodo actual

        v_ajc_max NUMBER(12,4); -- Ajuste de Colas Máximo
        v_ajc_min NUMBER(12,4); -- Ajuste de Colas Minimo

        v_suma_l NUMBER(38,10);
        v_suma_f NUMBER(38,10);

   BEGIN

        --- INI Ajuste de Colas Por Zonas CAPS ---

        --Calculamos el nro de campañas con datos de venta real
        v_nro_camp_vrea := 6 - p_fal_cerrar;
        --

        -- Identificamos a las zonas que no tienen TODOS los datos de VENTA
        -- en TODAS las campañas DEL PERIODO ANTERIOR AL QUE SE QUIERE DISTRIBUIR LA META, PUEDEN SER 4 o 5
        -- Quitamos  a las zonas que no cumplen con estos criterios
        UPDATE FDV_ZONA_CONSO
        SET FLA_ZOCO_AJCO = 'N'
        WHERE PROC_COD_PROC = p_cod_proceso
        AND COD_ZONA IN(
            SELECT BZO_ZONA
            FROM (
                SELECT
                BZO_ZONA, BZO_CAMP,
                (CASE WHEN SUM(BZO_VENT_REAL) > 0 THEN 0 ELSE 1 END) VAL
                FROM FDV_BASE_ZONA BZ
                WHERE PROC_COD_PROC = p_cod_proceso
                AND BZO_ANYO||BZO_CAMP >= p_aacc_inicio
                AND BZO_ANYO||BZO_CAMP <= p_aacc_fin_corte
                AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.ZON_CAPS IS NOT NULL)
                GROUP BY BZO_ZONA, BZO_CAMP
                ORDER BY BZO_ZONA, BZO_CAMP
                )
            GROUP BY BZO_ZONA
            HAVING SUM(VAL) = 0 AND COUNT(DISTINCT BZO_CAMP) != v_nro_camp_vrea --ESTE VALOR PUEDE SER 4 o 5
        );
        --

        -- Insertamos todas las zonas a considerar, en la tabla temporal
        DELETE FROM FDV_PROI_AJUS_COLA WHERE PROC_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_AJUS_COLA(PROC_COD_PROC, COD_ZONA)
        SELECT PROC_COD_PROC, COD_ZONA
        FROM FDV_ZONA_CONSO
        WHERE PROC_COD_PROC = p_cod_proceso
        AND FLA_ZOCO_AJCO = 'S';

        -- Calculo del FACTOR 3, en base a las primeras 4/5 campañas
        DELETE FROM FDV_PROI_AJCO_FAC3 WHERE PROC_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_AJCO_FAC3 (PROC_COD_PROC, COD_ZONA, FAC3)
        SELECT
        DISTINCT
        p_cod_proceso,
        BNR_ZONA,
        AVG(CUMP) OVER (PARTITION BY BNR_ZONA) FACT3
        FROM(
            SELECT BNR_ZONA, BNR_CAMP, ROUND(ROUND(SUM(BNR_VENR), li_nro_deci)/ROUND(SUM(BNR_VFDV), li_nro_deci), li_nro_deci) CUMP
            FROM FDV_BASE_DNRVE BZ
            WHERE PROC_COD_PROC = p_cod_proceso
            AND BNR_ANYO||BNR_CAMP >= p_aacc_inicio
            AND BNR_ANYO||BNR_CAMP <= p_aacc_fin_corte
            AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BNR_ZONA AND ZC.ZON_CAPS IS NOT NULL )
            GROUP BY BNR_ZONA, BNR_ANYO, BNR_CAMP
            ORDER BY BNR_ZONA, BNR_ANYO, BNR_CAMP);

        -- Actualizamos el valor calculado
        UPDATE FDV_PROI_AJUS_COLA TP1
        SET TP1.FAC3 = (
            SELECT TP2.FAC3
            FROM FDV_PROI_AJCO_FAC3 TP2
            WHERE TP2.PROC_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA)
        WHERE TP1.PROC_COD_PROC = p_cod_proceso
        AND EXISTS(SELECT 1 FROM FDV_PROI_AJCO_FAC3 TP2
            WHERE TP2.PROC_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA);
        -- --

        -- Calculo de la fuente de ventas de los periodos que faltan concluir
        DELETE FROM FDV_PROI_AJCO_FDVE WHERE PROC_COD_PROC = p_cod_proceso;

        INSERT INTO FDV_PROI_AJCO_FDVE (PROC_COD_PROC, COD_ZONA, VENT_FDVE)
            SELECT
            PROC_COD_PROC,
            BNR_ZONA,
            SUM(BNR_VFDV) FDVXY
            FROM FDV_BASE_DNRVE BZ
            WHERE PROC_COD_PROC = p_cod_proceso
            AND BNR_ANYO||BNR_CAMP > p_aacc_fin_corte
            AND BNR_ANYO||BNR_CAMP <= p_aacc_fin_perio
            AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BNR_ZONA AND ZC.ZON_CAPS IS NOT NULL)
            GROUP BY PROC_COD_PROC, BNR_ZONA;

        -- Actualizamos el valor calculado
        UPDATE FDV_PROI_AJUS_COLA TP1
        SET TP1.VENT_FDVE = (
            SELECT TP2.VENT_FDVE
            FROM FDV_PROI_AJCO_FDVE TP2
            WHERE TP2.PROC_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA)
        WHERE TP1.PROC_COD_PROC = p_cod_proceso
        AND EXISTS(SELECT 1 FROM FDV_PROI_AJCO_FDVE TP2
            WHERE TP2.PROC_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA);

        -- --

        -- Calculo de LA VENTA REAL DE CX-5 CX-2 - Teniendo como base la Base De Datos Zonas
        -- Solo se considera las zonas que tienen datos en todas las campáñas
            DELETE FROM FDV_PROI_AJCO_VERE WHERE PROC_COD_PROC = p_cod_proceso;

            INSERT INTO FDV_PROI_AJCO_VERE (PROC_COD_PROC, COD_ZONA, VENT_REAL)
            SELECT
            p_cod_proceso,
            BZO_ZONA,
            SUM(BZO_VENT_REAL)
            FROM FDV_BASE_ZONA BZ
            WHERE PROC_COD_PROC = p_cod_proceso
            AND BZO_ANYO||BZO_CAMP >= p_aacc_inicio
            AND BZO_ANYO||BZO_CAMP <= p_aacc_fin_corte
            AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = BZ.PROC_COD_PROC AND ZC.COD_ZONA = BZ.BZO_ZONA AND ZC.ZON_CAPS IS NOT NULL AND ZC.FLA_ZOCO_AJCO = 'S')
            GROUP BY BZO_ZONA
            ORDER BY BZO_ZONA;

        -- Actualizamos el valor calculado
        UPDATE FDV_PROI_AJUS_COLA TP1
        SET TP1.VENT_REAL = (
            SELECT TP2.VENT_REAL
            FROM FDV_PROI_AJCO_VERE TP2
            WHERE TP2.PROC_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA)
        WHERE TP1.PROC_COD_PROC = p_cod_proceso
        AND EXISTS(SELECT 1 FROM FDV_PROI_AJCO_VERE TP2
            WHERE TP2.PROC_COD_PROC = TP1.PROC_COD_PROC
            AND TP2.COD_ZONA = TP1.COD_ZONA);

        -- --

        -- Calculamos H = Venta Real del Periodo Anterior, al que se quiere distribuir la meta
        UPDATE FDV_ZONA_CONSO ZC
        SET ZC.DEC_META_AJCO_VALH = (
            SELECT  TP.VENT_REAL  + ROUND(TP.FAC3 * TP.VENT_FDVE, li_nro_deci)
            FROM FDV_PROI_AJUS_COLA TP
            WHERE TP.PROC_COD_PROC = ZC.PROC_COD_PROC
            AND TP.COD_ZONA = ZC.COD_ZONA)
        WHERE ZC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS (SELECT 1
            FROM FDV_PROI_AJUS_COLA TP
            WHERE TP.PROC_COD_PROC = ZC.PROC_COD_PROC
            AND TP.COD_ZONA = ZC.COD_ZONA);
        -- --

        -- Calculamos el Crecimiento DE VENTAS = F = (L/H) - 1,
        UPDATE FDV_ZONA_CONSO
        SET DEC_META_AJCO_CZCA = (DEC_META_VENT_AJPE/DEC_META_AJCO_VALH) -1
        WHERE PROC_COD_PROC = p_cod_proceso
        AND DEC_META_AJCO_VALH > 0;
        --

        -- Calculamos el crecimiento CAPS
        -- (suma(L)/suma(H)) - 1
        UPDATE FDV_ZONA_CONSO CA
        SET CA.DEC_META_CREC_CAPS = (
            SELECT
            (SUM(DE.DEC_META_VENT_AJPE)/ SUM(DE.DEC_META_AJCO_VALH)) - 1
            FROM FDV_ZONA_CONSO DE
            WHERE DE.PROC_COD_PROC = CA.PROC_COD_PROC
            AND DE.ZON_CAPS = CA.ZON_CAPS
            AND DE.ZON_CAPS IS NOT NULL
            AND FLA_ZOCO_AJCO = 'S'
            GROUP BY ZON_CAPS
        )
        WHERE CA.proc_COD_PROC = p_cod_proceso
        AND EXISTS(
            SELECT
            1
            FROM FDV_ZONA_CONSO DE
            WHERE DE.PROC_COD_PROC = CA.PROC_COD_PROC
            AND DE.ZON_CAPS = CA.ZON_CAPS
            AND DE.ZON_CAPS IS NOT NULL
            AND FLA_ZOCO_AJCO = 'S'
        );
        --

        -- Calculamos los crecimientos Maximo y minimos
        -- Ajuste de Colas Máximo
        SELECT VAL_PARA INTO v_ajc_max FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_ajc_max;

        -- Ajuste de Colas Minimo
        SELECT VAL_PARA INTO v_ajc_min FROM FDV_PROCE_PARAM WHERE PROC_COD_PROC = p_cod_proceso AND PARA_COD_PARA = lc_par_ajc_min;

        UPDATE FDV_ZONA_CONSO
        SET
        DEC_META_CREC_MAXI = (CASE WHEN DEC_META_CREC_CAPS > 0 THEN DEC_META_CREC_CAPS * (1 + v_ajc_max) ELSE DEC_META_CREC_CAPS * (1 - v_ajc_max) END),
        DEC_META_CREC_MINI = (CASE WHEN DEC_META_CREC_CAPS > 0 THEN DEC_META_CREC_CAPS * (1 - v_ajc_min) ELSE DEC_META_CREC_CAPS * (1 + v_ajc_min) END)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND DEC_META_AJCO_VALH > 0;
        -- --

        -- Ajustamos las Colas

        --Calculamos los ajustes de crecimiento maximo y minimo
        -- F = DEC_META_AJCO_CZCA
        -- MAX = DEC_META_CREC_MAXI
        -- MIN = DEC_META_CREC_MINI
        -- L = DEC_META_VENT_AJPE
        -- H = DEC_META_AJCO_VALH

        -- DEC_META_AJUS_CRMX = SI(F > MAX, - (L - H*(1 + MAX)), 0) / 1000
        -- DEC_META_AJUS_CRMN = SI(F < MIN, (H*(1 + MIN) - L), 0) / 1000

        UPDATE FDV_ZONA_CONSO
        SET
            DEC_META_AJUS_CRMX = (CASE WHEN DEC_META_AJCO_CZCA > DEC_META_CREC_MAXI THEN -(DEC_META_VENT_AJPE - (DEC_META_AJCO_VALH * (1 + DEC_META_CREC_MAXI))) ELSE 0 END)/1000,
            DEC_META_AJUS_CRMN = (CASE WHEN DEC_META_AJCO_CZCA < DEC_META_CREC_MINI THEN (DEC_META_AJCO_VALH * (1 + DEC_META_CREC_MINI) - DEC_META_VENT_AJPE) ELSE 0 END)/1000
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_AJCO = 'S';
        -- --

        -- Calculamos los ajustes de ventas en base a los campos calculados en el paso anterior
        -- A - Ajuste de Venta
        UPDATE FDV_ZONA_CONSO
        SET DEC_META_AJUS_VENT = (CASE  WHEN DEC_META_AJUS_CRMX != 0
                                        THEN DEC_META_AJUS_CRMX
                                        ELSE
                                            (CASE WHEN DEC_META_AJUS_CRMN != 0 THEN DEC_META_AJUS_CRMN
                                            ELSE 0 END)
                                        END)
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_AJCO = 'S';
        -- --

        -- Calculamos la distribucion de venta propuesta por la herramienta ajustando los
        -- valores con los valores calculados en el paso anterior
        -- F = L + A

        UPDATE FDV_ZONA_CONSO
        SET DEC_META_PROP_AJUS = DEC_META_VENT_AJPE + 1000*DEC_META_AJUS_VENT
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL;
        -- --

        -- Verificamos que la suma total de la meta propuesta por la herramienta = L
        -- sea igual que la suma de la meta ajustada = F

        SELECT SUM(DEC_META_VENT_AJPE) SL, SUM(DEC_META_PROP_AJUS) SF
        INTO v_suma_l, v_suma_f
        FROM FDV_ZONA_CONSO
        WHERE PROC_COD_PROC = p_cod_proceso
        AND ZON_CAPS IS NOT NULL
        AND FLA_ZOCO_AJCO = 'S';
        --

        -- Si los valores anteriores son distintos, entonces se reparte la diferencia
        -- entre las zonas que no se les aplicó el ajuste ( los que tienen CERO en DEC_META_AJUS_VENT)
        -- Calculamos la venta a quitar

        if v_suma_l != v_suma_f then
            -- Actualizamos el valor calculado en el campo DEC_META_AJUS_VENT, en las zonas
            -- que no se hizo el ajuste (DEC_META_AJUS_VENT = 0)
            -- El proceso es por zonas CAPS

            for oBean in(
                SELECT DISTINCT ZON_CAPS
                FROM FDV_ZONA_CONSO
                WHERE PROC_COD_PROC = p_cod_proceso
                AND ZON_CAPS IS NOT NULL
            ) loop

                for bean in(
                    SELECT
                    T.COD_ZONA,
                    ROUND(-1*(RATIO_TO_REPORT(T.DIF_VENT) OVER ()), li_nro_deci) *
                    (
                        SELECT
                        ROUND(SUM(DEC_META_AJUS_VENT), li_nro_deci)
                        FROM FDV_ZONA_CONSO
                        WHERE PROC_COD_PROC = p_cod_proceso
                        AND ZON_CAPS = oBean.ZON_CAPS
                        AND FLA_ZOCO_AJCO = 'S'
                    ) QTAR_VENT
                    FROM(
                        SELECT
                        COD_ZONA,
                        ROUND(DEC_META_VENT_AJPE - DEC_META_AJCO_VALH, li_nro_deci) DIF_VENT
                        FROM FDV_ZONA_CONSO
                        WHERE PROC_COD_PROC = p_cod_proceso
                        AND ZON_CAPS = oBean.ZON_CAPS
                        AND FLA_ZOCO_AJCO = 'S'
                        AND DEC_META_AJUS_VENT = 0) T
                ) loop

                    UPDATE FDV_ZONA_CONSO
                    SET DEC_META_AJUS_VENT = bean.QTAR_VENT,
                    FLA_ZOCO_AJVE = 'S' -- Zonas a las que se esta ajustando la venta
                    WHERE PROC_COD_PROC = p_cod_proceso
                    AND COD_ZONA = bean.COD_ZONA;

                end loop;

            end loop;
            -- --

            -- --
            -- RE Calculamos la distribucion de venta propuesta por la herramienta ajustando los
            -- valores con los valores calculados en el paso anterior
            -- F = L + A

            UPDATE FDV_ZONA_CONSO
            SET DEC_META_PROP_AJUS = DEC_META_VENT_AJPE + 1000*DEC_META_AJUS_VENT
            WHERE PROC_COD_PROC = p_cod_proceso
            AND ZON_CAPS IS NOT NULL
            AND FLA_ZOCO_AJCO = 'S';
            -- --

            -- Hasta este punto la SUMA debe de ser IGUALES Para SL = SF
            SELECT SUM(DEC_META_VENT_AJPE) SL, SUM(DEC_META_PROP_AJUS) SF
            INTO v_suma_l, v_suma_f
            FROM FDV_ZONA_CONSO
            WHERE PROC_COD_PROC = p_cod_proceso
            AND ZON_CAPS IS NOT NULL
            AND FLA_ZOCO_AJCO = 'S';

            if v_suma_l != v_suma_f then
                -- Error de Calculo, se debe de establecer un limite de error
                dbms_output.put_line ('Diferencia en el ajuste - 1: ' ||to_char(v_suma_l - v_suma_f));
            end if;
        end if;

        -- Calculamos el FACTOR4 = F/L y lo multiplicamos por la distribucion de metas por campaña
        -- que ya se ha calculado anteriormente en la tabla FDV_PROI_DECAN_CALCU_BASE
        -- campo DEC_META_VENT
        -- F = DEC_META_PROP_AJUS
        -- L = DEC_META_VENT_AJPE
        -- FACTOR4 = F/L
        -- Q = DEC_META_VENT
        -- G = Q * FACTOR4
        -- G debe de ser igual a la meta de venta del periodo L para las zonas no consideradas
        -- para el ajuste; y debe de ser igual a F para las zonas que se ajustaron

        UPDATE FDV_PROI_DECAN_CALCU_BASE DC
        SET DC.DEC_PRDI_META_CCOL = DEC_META_VENT * (
                SELECT ROUND(ZC.DEC_META_PROP_AJUS/ZC.DEC_META_VENT_AJPE, li_nro_deci) -- FACTOR4
                FROM FDV_ZONA_CONSO ZC
                WHERE ZC.PROC_COD_PROC = DC.PROC_COD_PROC
                AND ZC.COD_ZONA = DC.COD_ZONA
                AND ZC.DEC_META_VENT_AJPE > 0
                )
        WHERE DC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS(
                SELECT 1
                FROM FDV_ZONA_CONSO ZC
                WHERE ZC.PROC_COD_PROC = DC.PROC_COD_PROC
                AND ZC.COD_ZONA = DC.COD_ZONA
                AND ZC.DEC_META_VENT_AJPE > 0
        );
        -- --

        -- Para el caso de las zonas a las que no se les ajusto la venta
        -- Y las que no entraron al ajuste de colas
        -- la venta es la misma que la herramienta ¿propuso
        UPDATE FDV_PROI_DECAN_CALCU_BASE DC
        SET DC.DEC_PRDI_META_CCOL = DEC_META_VENT
        WHERE DC.PROC_COD_PROC = p_cod_proceso
        AND DC.COD_ZONA IN (
                -- ZONAS A LAS QUE NO SE LES AJUSTO LA VENTA
                SELECT COD_ZONA
                FROM FDV_ZONA_CONSO
                WHERE PROC_COD_PROC = p_cod_proceso
                AND ZON_CAPS IN(
                    --CAPS A LAS QUE NO SE LES AJUSTO LA VENTA
                    SELECT ZON_CAPS
                    FROM FDV_ZONA_CONSO ZC
                    WHERE ZC.PROC_COD_PROC = p_cod_proceso
                    AND ZON_CAPS IS NOT NULL
                    MINUS
                    SELECT ZON_CAPS
                    FROM FDV_ZONA_CONSO ZC
                    WHERE ZC.PROC_COD_PROC = p_cod_proceso
                    AND ZC.FLA_ZOCO_AJCO = 'S'
                    AND ZC.FLA_ZOCO_AJVE = 'S'
                    AND ZON_CAPS IS NOT NULL)
                 UNION
                 --ZONAS QUE NO ENTRARON AL AJUSTE DE COLAS POR NO TENER DATA
                 --HISTORICA
                 SELECT COD_ZONA
                 FROM FDV_ZONA_CONSO ZC
                 WHERE ZC.PROC_COD_PROC = p_cod_proceso
                 AND ZC.FLA_ZOCO_AJCO = 'N'
                 AND ZON_CAPS IS NOT NULL
        );
        --

        -- Validamos que la meta a distribuir y el valor ajustado sean iguales
        SELECT SUM(DEC_PRDI_META_CCOL)
        INTO v_suma_l
        FROM FDV_PROI_DECAN_CALCU_BASE
        WHERE PROC_COD_PROC = p_cod_proceso;

        if v_suma_l != p_meta_vndi then
            -- Error de Calculo, se debe de establecer un limite de error
            dbms_output.put_line ('Diferencia en el ajuste - 2: ' ||to_char(v_suma_l - p_meta_vndi));
        end if;
        --- FIN Ajuste de Colas ---


        -- Ajuste de Meta Por Campaña
        -- Calculamos el monto por campaña, segun la distribucion de pesos
        -- ingresados por el usuario, y restamos el valor de G con el anterior
        -- para obtener la diferencia campaña a campaña
        for bean in (
            SELECT T1.CAMPANYA, (T2.S2 - T1.S1) DIFF
            FROM (
                SELECT CAMPANYA, SUM(DEC_PRDI_META_CCOL) S1
                FROM FDV_PROI_DECAN_CALCU_BASE
                WHERE PROC_COD_PROC = p_cod_proceso
                GROUP BY CAMPANYA) T1,
            (SELECT DISTINCT CAMPANYA, PRO_REAL_PROJ * p_meta_vndi S2
            FROM FDV_PROI_DECAN_CALCU_BASE
            WHERE PROC_COD_PROC = p_cod_proceso) T2
            WHERE T1.CAMPANYA = T2.CAMPANYA
        ) loop

            UPDATE FDV_PROI_DECAN_CALCU_BASE
            SET DEC_DIFF_CAMP = bean.diff
            WHERE PROC_COD_PROC = p_cod_proceso
            AND CAMPANYA = bean.campanya;

        end loop;
        -- --

        -- Distribuimos el excedente o sobrante anterior por campaña
        -- G = DEC_PRDI_META_CCOL
        -- R = VEN_POTE_INDR
        -- W = G + R * DEC_DIFF_CAMP

        UPDATE FDV_PROI_DECAN_CALCU_BASE
        SET DEC_MECA_FINA = DEC_PRDI_META_CCOL + ROUND(VEN_POTE_INDR * DEC_DIFF_CAMP, li_nro_deci)
        WHERE PROC_COD_PROC = p_cod_proceso;
        --- FIN Ajuste de Metas por campaña ---

    EXCEPTION
    WHEN OTHERS
    THEN
        ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
        p_mensajeerror := ls_sqlerrm;
        raise_application_error (-20123,
        'FDV_PR_PROC_AJUS_COLA_MECA: '
        || ls_sqlerrm
    );
   END FDV_PR_PROC_AJUS_COLA_MECA;

   /**************************************************************************
   Descripcion       : Limpia / inicializa los campos calculados en el proceso
                        de distribucion de metas
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_LIMP_CCAL (
      p_cod_proceso  VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   ) IS
   BEGIN

        -- Limpiamos los campos a usar para todo el proceso
        UPDATE FDV_ZONA_CONSO
        SET
        PRO_SECC = 0,
        BMK_PNTR = 0,
        BMK_CAPI = 0,
        BMK_ACTI = 0,
        BMK_PUP = 0,
        BMK_PPU = 0,
        CCA_FACT_PNTR = 0,
        CCA_CAPI_OBJT = 0,
        CCA_CAPI_PERI = 0,
        CCA_ACTI_INIC = 0,
        CCA_ACTI_FINA = 0,
        CCA_ACTI_PRAA = 0,
        CCA_ACTI_INAA = 0,
        CCA_ACTI_FIAA = 0,
        CCA_ACTI_PROM = 0,
        CCA_ACVD = 0,
        CCA_PUP = 0,
        CCA_PPU = 0,
        CCA_FAC1 = 0,
        POT_EAAN_PEDI = 0,
        POT_EAAN_PMNP = 0,
        POT_EAAN_VENT = 0,
        POT_PEDI_POTE = 0,
        POT_PMNP_POTE = 0,
        POT_VENT_POTE = 0,
        POT_FAC2 = 0,
        POT_INDI_RELA = 0,
        DEC_META_PERI = 0,
        DEC_META_VENT_AJPE = 0,
        DEC_META_AJCO_VALH = 0,
        DEC_META_AJCO_CZCA = 0,
        DEC_META_CREC_MAXI = 0,
        DEC_META_CREC_MINI = 0,
        DEC_META_AJUS_CRMX = 0,
        DEC_META_AJUS_CRMN = 0,
        DEC_META_AJUS_VENT = 0,
        DEC_META_PROP_AJUS = 0,
        DEC_META_CREC_CAPS = 0,
        VAL_META_CAX1 = 0,
        VAL_META_CAX2 = 0,
        VAL_META_CAX3 = 0,
        VAL_META_CAX4 = 0,
        VAL_META_CAX5 = 0,
        VAL_META_CAX6 = 0,
        FLA_ZOCO_AJCO = 'S',
        FLA_ZOCO_AJVE = 'N'
        WHERE PROC_COD_PROC = p_cod_proceso;
        --

    EXCEPTION
    WHEN OTHERS
    THEN
        ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
        p_mensajeerror := ls_sqlerrm;
        raise_application_error (-20123,
        'FDV_PR_PROC_LIMP_CCAL: '
        || ls_sqlerrm
    );
   END FDV_PR_PROC_LIMP_CCAL;


   /**************************************************************************
   Descripcion       : Realiza la distribucion de metas
   Fecha Creacion    : 30/09/2011
   Parametros Entrada:
              p_cod_pais      : Codigo del pais,
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_DIST_METAS (
        p_cod_pais        VARCHAR2,
        p_cod_proceso     VARCHAR2,
        p_mensajeerror OUT VARCHAR2

   ) IS
        v_fal_cerrar NUMBER;            -- Numero de campañas que faltan para cerrar el periodo

        v_aacc_inicio VARCHAR2(6);      -- Campaña de inicio en formato AAAACC -- INICIO DE PERIODO
        v_aacc_fin_perio VARCHAR2(6);   -- Campaña de fin-corte en formato AAAACC -- FIN DE PERIODO
        v_aacc_fin_corte VARCHAR2(6);   -- Campaña de fin-corte en formato AAAACC -- CAMPAÑA DE CORTE

        v_aacc_ini_ant VARCHAR2(6);     -- Campaña de inicio en formato AAAACC del periodo del año anterior
        v_aacc_ini_ant_men1 VARCHAR2(6);-- Campaña de inicio MENOS 1 en formato AAAACC del periodo del año anterior
        v_aacc_fin_ant VARCHAR2(6);     -- Campaña de fin en formato AAAACC del periodo del año anterior

        v_meta_vndi NUMBER(30,2);       -- Meta de Venta a Distribuir

        v_record_varchar collection_varchar;

   BEGIN

        -- Inicializamos los valores
        FDV_PR_INI_PROC_DIST_METAS (p_cod_pais, p_cod_proceso, p_mensajeerror);
        -- --
        if(p_mensajeerror = '' or p_mensajeerror is null) then

            -- Limpiamos los campos de la tabla de consolidados
            -- que se van a usar en el proceso
            FDV_PR_PROC_LIMP_CCAL(p_cod_proceso, p_mensajeerror);

            if(p_mensajeerror = '' or p_mensajeerror is null) then

                -- Calculamos el promedio de las secciones en base a lo que el usuario ha ingresado
                FDV_PR_PROC_CALC_PROM_SECC (p_cod_proceso, p_mensajeerror);

                if(p_mensajeerror = '' or p_mensajeerror is null) then

                    -- Calculamos los benchmarks y ños campos calculados
                    FDV_PR_PROC_CALC_BNMK_CMCA (p_cod_proceso, v_fal_cerrar, v_aacc_inicio, v_aacc_fin_perio, v_aacc_fin_corte, v_aacc_ini_ant, v_aacc_ini_ant_men1, v_aacc_fin_ant, p_mensajeerror);

                    if(p_mensajeerror = '' or p_mensajeerror is null) then

                        -- Calculamos la venta potencial y los indices relativos
                        FDV_PR_PROC_CALC_VEPO_INRE (p_cod_proceso, v_aacc_ini_ant, v_aacc_fin_ant, p_mensajeerror);

                        if(p_mensajeerror = '' or p_mensajeerror is null) then

                            -- Obtenemos los datos necesarios del proceso
                            SELECT VAL_MEVD
                            INTO v_meta_vndi
                            FROM FDV_PROCE
                            WHERE COD_PROC = p_cod_proceso;
                            -- --

                            -- Calculamos el decante
                            FDV_PR_PROC_CALC_DECA(p_cod_proceso, v_aacc_ini_ant, v_aacc_fin_ant, v_meta_vndi, p_mensajeerror);

                            if(p_mensajeerror = '' or p_mensajeerror is null) then

                                -- Realizamos el ajuste de colas y el ajuste de meta por campaña
                                FDV_PR_PROC_AJUS_COLA_MECA(p_cod_proceso, v_fal_cerrar, v_aacc_inicio, v_aacc_fin_perio, v_aacc_fin_corte, v_meta_vndi, p_mensajeerror);

                                if(p_mensajeerror = '' or p_mensajeerror is null) then

                                    -- Movemos los datos calculados a la tabla de consolidados
                                    SELECT DISTINCT CAMPANYA
                                    BULK COLLECT INTO v_record_varchar
                                    FROM FDV_PROI_DECAN_CALCU_BASE
                                    WHERE PROC_COD_PROC = p_cod_proceso
                                    ORDER BY CAMPANYA;

                                    if(v_record_varchar.count = 6) then

                                        UPDATE FDV_ZONA_CONSO T1
                                        SET
                                        T1.VAL_META_CAX1 = (
                                            SELECT T2.DEC_MECA_FINA
                                            FROM FDV_PROI_DECAN_CALCU_BASE T2
                                            WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                                            AND T2.COD_ZONA = T1.COD_ZONA
                                            AND T2.CAMPANYA = v_record_varchar(1).description
                                        ),
                                        T1.VAL_META_CAX2 = (
                                            SELECT T2.DEC_MECA_FINA
                                            FROM FDV_PROI_DECAN_CALCU_BASE T2
                                            WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                                            AND T2.COD_ZONA = T1.COD_ZONA
                                            AND T2.CAMPANYA = v_record_varchar(2).description
                                        ),
                                        T1.VAL_META_CAX3 = (
                                            SELECT T2.DEC_MECA_FINA
                                            FROM FDV_PROI_DECAN_CALCU_BASE T2
                                            WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                                            AND T2.COD_ZONA = T1.COD_ZONA
                                            AND T2.CAMPANYA = v_record_varchar(3).description
                                        ),
                                        T1.VAL_META_CAX4 = (
                                            SELECT T2.DEC_MECA_FINA
                                            FROM FDV_PROI_DECAN_CALCU_BASE T2
                                            WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                                            AND T2.COD_ZONA = T1.COD_ZONA
                                            AND T2.CAMPANYA = v_record_varchar(4).description
                                        ),
                                        T1.VAL_META_CAX5 = (
                                            SELECT T2.DEC_MECA_FINA
                                            FROM FDV_PROI_DECAN_CALCU_BASE T2
                                            WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                                            AND T2.COD_ZONA = T1.COD_ZONA
                                            AND T2.CAMPANYA = v_record_varchar(5).description
                                        ),
                                        T1.VAL_META_CAX6 = (
                                            SELECT T2.DEC_MECA_FINA
                                            FROM FDV_PROI_DECAN_CALCU_BASE T2
                                            WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                                            AND T2.COD_ZONA = T1.COD_ZONA
                                            AND T2.CAMPANYA = v_record_varchar(6).description
                                        )
                                        WHERE T1.PROC_COD_PROC = p_cod_proceso
                                        AND EXISTS (
                                            SELECT 1
                                            FROM FDV_PROI_DECAN_CALCU_BASE T2
                                            WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                                            AND T2.COD_ZONA = T1.COD_ZONA
                                        );
                                        -- --

                                        --Actualizamos la meta ajustada del periodo
                                        -- Actualizamos la meta ajustada por el DV
                                        UPDATE FDV_ZONA_CONSO
                                        SET META_VENT_AJPE = VAL_META_CAX1+VAL_META_CAX2+VAL_META_CAX3+VAL_META_CAX4+VAL_META_CAX5+VAL_META_CAX6,
                                            META_VENT_AJDV = VAL_META_CAX1+VAL_META_CAX2+VAL_META_CAX3+VAL_META_CAX4+VAL_META_CAX5+VAL_META_CAX6
                                        WHERE PROC_COD_PROC = p_cod_proceso;

                                    end if;

                                end if;

                            end if;

                        end if;

                    end if;

                end if;

            end if;

        end if;


    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    p_mensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_PROC_DIST_METAS: '
    || ls_sqlerrm
    );

   END FDV_PR_PROC_DIST_METAS;

   /**************************************************************************
     Descripcion       : Inicializa la tabla base con los datos para el calculo
                          de secciones, del proceso de distribucion de metas
     Fecha Creacion    : 30/09/2011
     Parametros Entrada:
             p_cod_pais      : Codigo del pais,
     Parametros Salida :
             pmensajeerror   : Mensaje de Error
     Autor             : Ivan Tocto Jaimes
    **************************************************************************/
    PROCEDURE FDV_PR_INI_PROC_DIST_META_SECC (
        p_cod_proceso     VARCHAR2,
        pmensajeerror OUT VARCHAR2
    )
    is
        -- suma de numero de secciones
        n_sum_scco NUMBER;

        -- campaña de corte
        v_cam_corte VARCHAR2(6);

    BEGIN

        -- Inicializando las variables
        n_sum_scco := 0;

        select sum(nro_scco) into n_sum_scco from fdv_zona_conso
        where proc_cod_proc = p_cod_proceso;

        -- Solo si los datos de las secciones aun no han sido calculadas,
        -- inicializamos los numeros de secciones de las zonas
        if(n_sum_scco = 0) then

          -- limpiamos los datos de las tablas temporales correspondientes al proceso
          DELETE FROM FDV_PROI_POTEN_SECCI WHERE PROC_COD_PROC = p_cod_proceso;
          -- --

          -- Obtenemos los datos necesarios del proceso
          SELECT ANY_PROC||CAM_PROC INTO v_cam_corte FROM FDV_PROCE WHERE COD_PROC = p_cod_proceso;
          -- --

          -- Actualizamos las zonas caps en base a lo que el usuario selecciono y lo que el sistema calculo
          UPDATE FDV_ZONA_CONSO
          SET ZON_CAPS = NVL(TRIM(CLU_ASIG_PAIS), CLU_ASIG_SIST)
          WHERE PROC_COD_PROC = p_cod_proceso;
          --

          --Calculamos el numero de secciones de la campaña de corte
          INSERT INTO FDV_PROI_POTEN_SECCI(PROC_COD_PROC, COD_ZONA, NRO_SCCO)
          SELECT
          p_cod_proceso,
          SE.BSE_ZONA,
          COUNT(SE.BSE_ZONA)
          FROM FDV_BASE_SECCI SE
          WHERE SE.PROC_COD_PROC = p_cod_proceso
          AND SE.BSE_ANYO||SE.BSE_CAMP = v_cam_corte
          GROUP BY BSE_ZONA;
          -- --

          --Actualizamos la tabla de consolidados con el valor alculado
          UPDATE FDV_ZONA_CONSO ZC
          SET (ZC.NRO_SCCO) =
              (SELECT TP.NRO_SCCO
               FROM FDV_PROI_POTEN_SECCI TP WHERE  TP.PROC_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA)
          WHERE ZC.PROC_COD_PROC = p_cod_proceso
          AND EXISTS (SELECT 1 FROM FDV_PROI_POTEN_SECCI TP WHERE  TP.PROC_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA);
          -- --

        end if;

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    pmensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_INI_PROC_DIST_META_SECC: '
    || ls_sqlerrm
    );

    END FDV_PR_INI_PROC_DIST_META_SECC;

   /**************************************************************************
     Descripcion       : Inicializa la tabla base con los datos para el calculo
                          del proceso de distribucion de metas
     Fecha Creacion    : 30/09/2011
     Parametros Entrada:
             p_cod_pais      : Codigo del pais,
             p_cod_proceso   : Codigo del proceso
     Parametros Salida :
             pmensajeerror   : Mensaje de Error
     Autor             : Ivan Tocto Jaimes
    **************************************************************************/
    PROCEDURE FDV_PR_INI_PROC_DIST_METAS (
        p_cod_pais        VARCHAR2,
        p_cod_proceso     VARCHAR2,
        pmensajeerror OUT VARCHAR2
    )
    is
        -- campaña de corte
        v_cam_corte VARCHAR2(6);

        -- Campaña anterior, 18 campañas atraz de la anterior
        v_cam_a18 VARCHAR2(6);

        lnidpais  seg_pais.oid_pais%TYPE;
        lnidcanal seg_canal.oid_cana%TYPE;
        lnidmarca seg_marca.oid_marc%TYPE;

    BEGIN

          -- Obteniendo oids
          lnidpais  := gen_pkg_gener.gen_fn_devuelve_id_pais(p_cod_pais);
          lnidcanal := gen_pkg_gener.gen_fn_devuelve_id_canal('VD');
          lnidmarca := gen_pkg_gener.gen_fn_devuelve_id_marca('T');

          -- limpiamos los datos de las tablas temporales correspondientes al proceso
          DELETE FROM FDV_PROI_POTEN_ZONA WHERE PROC_COD_PROC = p_cod_proceso;
          -- --

          -- Obtenemos los datos necesarios del proceso
          SELECT ANY_PROC||CAM_PROC INTO v_cam_corte FROM FDV_PROCE WHERE COD_PROC = p_cod_proceso;
          -- --

          -- Obtenemos la campaña luego de calcular 18 campañas, considerando la
          -- actual, campaña de corte
          -- Se usa 17 ya que la funcion no considera la campaña que recibe como parámetro
          v_cam_a18 := PER_PKG_REPOR_PERCE.PER_FN_OBTIE_PERIO(
          v_cam_corte, lnidpais, lnidmarca, lnidcanal, -17);

          -- volvemos a marcar las zonas
          UPDATE FDV_ZONA_CONSO
          SET FLA_ZOCO_PODE = 'N'
          WHERE PROC_COD_PROC = p_cod_proceso;
          --

          -- Marcamos las zonas que se van a considerar en Potencial y Decante
          UPDATE FDV_ZONA_CONSO
          SET FLA_ZOCO_PODE = 'S'
          WHERE PROC_COD_PROC = p_cod_proceso
          AND COD_ZONA IN(
              SELECT
              B.BZO_ZONA
              FROM FDV_BASE_ZONA B
              WHERE B.PROC_COD_PROC = p_cod_proceso
              AND B.BZO_ANYO||B.BZO_CAMP >= v_cam_a18
              AND B.BZO_ANYO||B.BZO_CAMP <= v_cam_corte
              AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO C
                            WHERE C.PROC_COD_PROC=B.PROC_COD_PROC
                            AND C.COD_ZONA = B.BZO_ZONA
                            AND C.FLA_ZONC_VAVE = 'N' -- Excluir zonas con data no confiable
                            AND C.FLA_ZOFI = 'N' -- Excluir zonas oficinas
                            )
              AND EXISTS (SELECT 1 FROM FDV_BASE_VAEXO V
                            WHERE V.PROC_COD_PROC=B.PROC_COD_PROC
                            AND V.BVX_ZONA = B.BZO_ZONA
                            AND V.BVX_POBL > 0 --Excluir zonas que no tienen datos en la variable poblacion
              )
              GROUP BY B.BZO_ZONA
              HAVING COUNT(DISTINCT BZO_ANYO||BZO_CAMP) = 18 -- Solo Zonas con data historica en 18 campañas
          );
          -- --

          -- Obteniendo promedio de capitalizacion y numero de secciones por
          -- zonas que son a considerar
          for bean in(
            select bse_zona, avg(bse_capi) as promedio, count(bse_secc) as conteo
            from fdv_base_secci where proc_cod_proc = p_cod_proceso
            and bse_zona in (
              select distinct cod_zona from fdv_zona_conso
              where fla_zoco_pode = 'S' and proc_cod_proc = p_cod_proceso
            )
            and bse_anyo||bse_camp >= v_cam_a18
            and bse_anyo||bse_camp <= v_cam_corte
            group by bse_zona
          ) loop

            -- Actualizando el promedio capitalizacion y numero de secciones
            -- por las zonas a considerar
            -- Tambien la capitalizacion a nivel de seccion que va ser la misma
            update fdv_zona_conso
              set pro_capi = bean.promedio,
                  nro_secc = bean.conteo,
                  pro_care_nise = bean.promedio
            where proc_cod_proc = p_cod_proceso
            and cod_zona = bean.bse_zona;

          end loop;
          -- --

          -- Obtenemos los datos de las zonas CAPS que tienen datos en las 18 ultimas campañas
          -- tomando como campaña de referencia la campaña de corte cx-2 inclusive
          -- CAPS exluyen zonas oficinas, zonas con data no confiable
          INSERT INTO FDV_PROI_POTEN_ZONA (PROC_COD_PROC, COD_ZONA, PRO_ACRE, PRO_PUPR, PRO_PPUR, PRO_CARE)
          SELECT
          p_cod_proceso,
          B.BZO_ZONA,
          AVG(B.BZO_ACDA_REAL) PRO_ACDA_REAL,
          AVG(B.BZO_PUP_REAL) PRO_PUP_REAL,
          AVG(B.BZO_PPU_REAL) PRO_PPU_REAL,
          AVG(B.BZO_CAPI_REAL) PRO_CAPI_REAL
          FROM FDV_BASE_ZONA B
          WHERE B.PROC_COD_PROC = p_cod_proceso
          AND B.BZO_ANYO||B.BZO_CAMP >= v_cam_a18
          AND B.BZO_ANYO||B.BZO_CAMP <= v_cam_corte
          AND EXISTS (SELECT 1 FROM FDV_ZONA_CONSO ZC WHERE ZC.PROC_COD_PROC = B.PROC_COD_PROC AND ZC.COD_ZONA = B.BZO_ZONA AND ZC.FLA_ZOCO_PODE = 'S')
          GROUP BY B.BZO_ZONA;
          -- --

          --Actualizamos los valores en la tabla de consolidados, todos menos la capitalizacion
          UPDATE FDV_ZONA_CONSO ZC
          SET (ZC.PRO_ACRE, ZC.PRO_PUPR, ZC.PRO_PPUR, ZC.PRO_CARE) =
              (SELECT TP.PRO_ACRE, TP.PRO_PUPR, TP.PRO_PPUR, TP.PRO_CARE
               FROM FDV_PROI_POTEN_ZONA TP WHERE TP.PROC_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA)
          WHERE ZC.PROC_COD_PROC = p_cod_proceso
          AND EXISTS (SELECT 1 FROM FDV_PROI_POTEN_ZONA TP WHERE TP.PROC_COD_PROC = ZC.PROC_COD_PROC AND TP.COD_ZONA = ZC.COD_ZONA);
          -- --

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    pmensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_INI_PROC_DIST_METAS: '
    || ls_sqlerrm
    );

    END FDV_PR_INI_PROC_DIST_METAS;


   /**************************************************************************
   Descripcion       : Realiza el proceso de ajuste de metas por campaña
                        En base a los datos que el Director de Ventas a cargado
   Fecha Creacion    : 05/01/2012
   Parametros Entrada:
             p_cod_proceso   : Codigo del proceso
   Parametros Salida :
             pmensajeerror   : Mensaje de Error
   Autor             : Ivan Tocto Jaimes
   ***************************************************************************/
   PROCEDURE FDV_PR_PROC_AJUS_MCDV (
      p_cod_proceso  VARCHAR2,
      p_mensajeerror OUT VARCHAR2
   ) IS

        v_suma_l NUMBER(38,10);
        v_meta_vndi NUMBER(30,2);       -- Meta de Venta a Distribuir
        v_record_varchar collection_varchar;

   BEGIN

        -- Obtenemos los datos necesarios del proceso
        SELECT VAL_MEVD
        INTO v_meta_vndi
        FROM FDV_PROCE
        WHERE COD_PROC = p_cod_proceso;
        -- --

        -- Cálculo de la propuesta de distribución de Meta por campaña a nivel de zona (considerando el ajuste del DV)
        -- FACTOR5 = P/Z
        -- P = META_VENT_AJDV
        -- Z = META_VENT_AJPE
        -- W = DEC_MECA_FINA

        -- Y = DEC_MECA_FINA * FACTOR5

        UPDATE FDV_PROI_DECAN_CALCU_BASE DC
        SET DC.DEC_PRDI_META_AJDV = DEC_MECA_FINA * (
                SELECT ROUND(ZC.META_VENT_AJDV/ZC.META_VENT_AJPE, li_nro_deci) -- FACTOR5
                FROM FDV_ZONA_CONSO ZC
                WHERE ZC.PROC_COD_PROC = DC.PROC_COD_PROC
                AND ZC.COD_ZONA = DC.COD_ZONA
                AND ZC.META_VENT_AJPE > 0
                )
        WHERE DC.PROC_COD_PROC = p_cod_proceso
        AND EXISTS(
                SELECT 1
                FROM FDV_ZONA_CONSO ZC
                WHERE ZC.PROC_COD_PROC = DC.PROC_COD_PROC
                AND ZC.COD_ZONA = DC.COD_ZONA
                AND ZC.META_VENT_AJPE > 0
        );
        -- --

        -- Validamos que la meta a distribuir y el valor ajustado sean iguales
        SELECT SUM(DEC_PRDI_META_AJDV)
        INTO v_suma_l
        FROM FDV_PROI_DECAN_CALCU_BASE
        WHERE PROC_COD_PROC = p_cod_proceso;

        if v_suma_l != v_meta_vndi then
            -- Error de Calculo, se debe de establecer un limite de error
            dbms_output.put_line ('Diferencia en el ajuste - 3: ' ||to_char(v_suma_l - v_meta_vndi));
        end if;
        -- --

        -- Ajuste de Meta Por Campaña
        -- Calculamos el monto por campaña, segun la distribucion de pesos
        -- ingresados por el usuario, y restamos el valor de Y con el anterior
        -- para obtener la diferencia campaña a campaña
        for bean in (
            SELECT T1.CAMPANYA, (T2.S2 - T1.S1) DIFF
            FROM (
                SELECT CAMPANYA, SUM(DEC_PRDI_META_AJDV) S1
                FROM FDV_PROI_DECAN_CALCU_BASE
                WHERE PROC_COD_PROC = p_cod_proceso
                GROUP BY CAMPANYA) T1,
            (SELECT DISTINCT CAMPANYA, PRO_REAL_PROJ * v_meta_vndi S2
            FROM FDV_PROI_DECAN_CALCU_BASE
            WHERE PROC_COD_PROC = p_cod_proceso) T2
            WHERE T1.CAMPANYA = T2.CAMPANYA
        ) loop

            UPDATE FDV_PROI_DECAN_CALCU_BASE
            SET DEC_DIFF_CAMP_AJDV = bean.diff
            WHERE PROC_COD_PROC = p_cod_proceso
            AND CAMPANYA = bean.campanya;

        end loop;
        --

        -- Distribuimos el excedente o sobrante anterior por campaña
        -- Y = DEC_PRDI_META_AJDV
        -- R = VEN_POTE_INDR
        -- B = Y + R * DEC_DIFF_CAMP_AJDV

        UPDATE FDV_PROI_DECAN_CALCU_BASE
        SET DEC_MECA_FINA_AJDV = DEC_PRDI_META_AJDV + ROUND(VEN_POTE_INDR * DEC_DIFF_CAMP_AJDV, li_nro_deci)
        WHERE PROC_COD_PROC = p_cod_proceso;
        --- FIN Ajuste de Metas por campaña DV---

        -- Movemos los datos calculados a la tabla de consolidados
        SELECT DISTINCT CAMPANYA
        BULK COLLECT INTO v_record_varchar
        FROM FDV_PROI_DECAN_CALCU_BASE
        WHERE PROC_COD_PROC = p_cod_proceso
        ORDER BY CAMPANYA;

        if(v_record_varchar.count = 6) then

            UPDATE FDV_ZONA_CONSO T1
            SET
            T1.VAL_META_AJDV_CAX1 = (
                SELECT T2.DEC_MECA_FINA_AJDV
                FROM FDV_PROI_DECAN_CALCU_BASE T2
                WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                AND T2.COD_ZONA = T1.COD_ZONA
                AND T2.CAMPANYA = v_record_varchar(1).description
            ),
            T1.VAL_META_AJDV_CAX2 = (
                SELECT T2.DEC_MECA_FINA_AJDV
                FROM FDV_PROI_DECAN_CALCU_BASE T2
                WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                AND T2.COD_ZONA = T1.COD_ZONA
                AND T2.CAMPANYA = v_record_varchar(2).description
            ),
            T1.VAL_META_AJDV_CAX3 = (
                SELECT T2.DEC_MECA_FINA_AJDV
                FROM FDV_PROI_DECAN_CALCU_BASE T2
                WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                AND T2.COD_ZONA = T1.COD_ZONA
                AND T2.CAMPANYA = v_record_varchar(3).description
            ),
            T1.VAL_META_AJDV_CAX4 = (
                SELECT T2.DEC_MECA_FINA_AJDV
                FROM FDV_PROI_DECAN_CALCU_BASE T2
                WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                AND T2.COD_ZONA = T1.COD_ZONA
                AND T2.CAMPANYA = v_record_varchar(4).description
            ),
            T1.VAL_META_AJDV_CAX5 = (
                SELECT T2.DEC_MECA_FINA_AJDV
                FROM FDV_PROI_DECAN_CALCU_BASE T2
                WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                AND T2.COD_ZONA = T1.COD_ZONA
                AND T2.CAMPANYA = v_record_varchar(5).description
            ),
            T1.VAL_META_AJDV_CAX6 = (
                SELECT T2.DEC_MECA_FINA_AJDV
                FROM FDV_PROI_DECAN_CALCU_BASE T2
                WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                AND T2.COD_ZONA = T1.COD_ZONA
                AND T2.CAMPANYA = v_record_varchar(6).description
            )
            WHERE T1.PROC_COD_PROC = p_cod_proceso
            AND EXISTS (
                SELECT 1
                FROM FDV_PROI_DECAN_CALCU_BASE T2
                WHERE T2.PROC_COD_PROC = T1.PROC_COD_PROC
                AND T2.COD_ZONA = T1.COD_ZONA
            );
            -- --
        end if;

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    p_mensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_PROC_AJUS_MCDV: '
    || ls_sqlerrm
    );

   END FDV_PR_PROC_AJUS_MCDV;


    /************************************************************************
     Descripcion : Funcion que convierte Cadena a Lista
     Creado     : Carlos Bazalar
     Fecha      : 19/10/2010
       Parametros:
         p_list  Cadena que sera convertida a Lista
         p_del   Delimitador a usar, por defecto es ,
    ************************************************************************/
    FUNCTION FDV_FN_SPLIT
    (
        p_list varchar2,
        p_del varchar2 := ','
    )
    RETURN split_tbl
    IS
        l_idx      pls_integer;
        indice     NUMBER(3);
        l_list     varchar2(4000) := TRIM(p_list);
        l_value    varchar2(4000);
        ln_i       NUMBER;
        ln_j       NUMBER;
        listaSplit split_tbl;
    BEGIN
        listaSplit := split_tbl();
        ln_i := listaSplit.FIRST;
        ln_j := listaSplit.LAST;
        indice := 0;
        loop
            l_idx := instr(l_list,p_del);
            indice:= indice + 1;
            listaSplit.EXTEND(1);
            ln_i := listaSplit.FIRST;
            ln_j := listaSplit.LAST;
            if l_idx > 0 then
                l_value:= substr(l_list,1,l_idx-1);
                l_list := substr(l_list,l_idx+length(p_del));
                listaSplit(indice):= TRIM(l_value);
            else
                l_value := l_list;
                listaSplit(indice):= TRIM(l_value);
                exit;
            end if;
        end loop;
        ln_i := listaSplit.FIRST;
        ln_j := listaSplit.LAST;
        RETURN listaSplit;
    end FDV_FN_SPLIT;


   /**************************************************************************
   Descripcion       : Proceso que se encarga de efectuar los calculos para la
                       generacion de la propuesta de clusterizacion desde
                       archivo
   Fecha Creacion    : 19/06/2012
   Autor             : Carlos Soberon
   Parametros Entrada:
             pcodpais       : Codigo del pais,
             pcodproceso    : Codigo del proceso
   Parametros Salida :
             pmensajeerror  : Mensaje de Error
   ***************************************************************************/
  PROCEDURE FDV_PR_GENER_PROPU_CLUST_ARCH (
        pcodpais          VARCHAR2,
        pcodproceso       VARCHAR2,
        pmensajeerror OUT VARCHAR2
    ) IS

    state_proc fdv_proce.sta_proc%TYPE;

    BEGIN

        -- Solo consideramos las zonas que estan en la campaña de corte
        DELETE FROM FDV_ZONA_CONSO
        WHERE PROC_COD_PROC = pcodproceso
        AND COD_ZONA NOT IN(
            SELECT BZO_ZONA
            FROM FDV_BASE_ZONA
            WHERE PROC_COD_PROC = pcodproceso
            AND BZO_ANYO||BZO_CAMP IN(SELECT ANY_PROC||CAM_PROC FROM FDV_PROCE WHERE PROC_COD_PROC = pcodproceso)
          );
        -- --

        for bean in (
            select bvx_zona, bvx_nse, bvx_rlur, bvx_pobl, bvx_var1,
                       bvx_var2, grp_pobl, grp_pobl_usua , clu_usua
            from fdv_base_vaexo where proc_cod_proc = pcodproceso
        ) loop

            update fdv_zona_conso
            set var_nse  = bean.bvx_nse,
                  var_rlur = bean.bvx_rlur,
                  var_pobl = bean.bvx_pobl,
                  var_var1 = bean.bvx_var1,
                  var_var2 = bean.bvx_var2,
                  var_grpo = bean.grp_pobl_usua,
                  clu_asig_sist = bean.clu_usua,
                  clu_asig_pais = bean.clu_usua,
                  fla_zoco = decode(bean.clu_usua, null, 'N', 'S')
              where cod_zona = bean.bvx_zona
              and proc_cod_proc = pcodproceso;

        end loop;

        FDV_PR_CALCU_PENTR(pcodproceso, pmensajeerror);

        if(pmensajeerror = '' or pmensajeerror is null) then

            -- Verificamos el estado actual del proceso
            select sta_proc into state_proc from fdv_proce
            where cod_proc = pcodproceso;

            if(state_proc = '2') then
                -- Una vez finalizado los calculos actualizamos el proceso al
                -- estado "En proceso de clusterizacion" = 3
                update fdv_proce
                set sta_proc = '3',
                ord_vacl = 'VAR_GRPO,VAR_NSE,VAR_RLUR'
                where cod_proc = pcodproceso;
            end if;

        end if;

    EXCEPTION
    WHEN OTHERS
    THEN
    ls_sqlerrm := SUBSTR (SQLERRM, 1, 250);
    pmensajeerror := ls_sqlerrm;
    raise_application_error (-20123,
    'FDV_PR_GENER_PROPU_CLUST: '
    || ls_sqlerrm
    );

    END FDV_PR_GENER_PROPU_CLUST_ARCH;



END fdv_pkg_proce;
/
