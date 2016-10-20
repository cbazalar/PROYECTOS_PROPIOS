CREATE OR REPLACE PACKAGE "PQ_APL_CRA" as

  ------------------------------------------------------------------------------
  -- Crea un calendario en la tabla CRA_FERIA (Feriados)
  ------------------------------------------------------------------------------
  procedure pr_crear_calendario(
     pi_num_anio      number
    ,pi_oid_pais      number
    ,pi_oid_marca     number
    ,pi_oid_canal     number
    ,pi_grupos_zonas  varchar2 
    ,pi_sabados       number
    ,pi_domingos      number 
  );

  ------------------------------------------------------------------------------
  -- Modifica un calendario en la tabla CRA_FERIA (Feriados)
  ------------------------------------------------------------------------------
  procedure pr_modificar_calendario(
     pi_num_anio       number
    ,pi_grupos_zonas   varchar2 
    ,pi_actividades    varchar2
    ,pi_festivos       varchar2
    ,pi_no_laborables  varchar2
  );

end pq_apl_cra;
/

CREATE OR REPLACE PACKAGE BODY "PQ_APL_CRA" as

  type t_lista_oids is
    table of number
    index by binary_integer;

  type t_lista_fechas is
    table of date
    index by binary_integer;

  -- caracter separador de elementos en listas de variables de cadenas de texto
  k_separador       constant varchar2(1) := ',';

  -- formato de las fechas pasadas en cadenas de texto
  k_formato_fechas  constant varchar2(10) := 'DD/MM/YYYY';


  ----------------------------------------------------------------------------
  -- Inicializa una variable de tabla pl con los valores separados con comas
  -- en una variable alfanumerica (devuelve tabla de OIDs)
  ----------------------------------------------------------------------------
  procedure pr_inic_lista_oids(
     pi_lista  in  varchar2
    ,po_lista  out t_lista_oids
  ) is
    w_num_param  number(3) := 0;
    w_lista_char varchar2(2000);
    w_lista_tab  t_lista_oids;
    w_pos_separ  number(3);
  begin
    w_lista_char := pi_lista;
    if w_lista_char is null then
      -- nada que hacer
      return;
    end if;

    loop
      w_pos_separ := instr(w_lista_char, k_separador);
      w_num_param := w_num_param + 1;

      if w_pos_separ = 0 then
        w_lista_tab(w_num_param) := w_lista_char;
        po_lista := w_lista_tab;
        exit;
      else
        w_lista_tab(w_num_param) := substr(w_lista_char, 1, w_pos_separ - 1 );
        w_lista_char := substr(w_lista_char, w_pos_separ+1, length(w_lista_char) );
      end if;

    end loop;

  exception
    when others then
      dbms_output.put_line( substr('<pr_inic_lista_oids> Error: ' || sqlerrm , 1 , 250 ));
      raise;
  end pr_inic_lista_oids;


  ----------------------------------------------------------------------------
  -- Inicializa una variable de tabla pl con los valores separados con comas
  -- en una variable alfanumerica (devuelve tabla de fechas)
  ----------------------------------------------------------------------------
  procedure pr_inic_lista_fechas(
     pi_lista  in  varchar2
    ,po_lista  out t_lista_fechas
  ) is
    w_num_param  number(3) := 0;
    w_lista_char varchar2(2000);
    w_lista_tab  t_lista_fechas;
    w_pos_separ  number(3);
  begin
    w_lista_char := pi_lista;
    if w_lista_char is null then
      -- nada que hacer
      return;
    end if;

    loop
      w_pos_separ := instr(w_lista_char, k_separador);
      w_num_param := w_num_param + 1;

      if w_pos_separ = 0 then
        w_lista_tab(w_num_param) := to_date(ltrim(rtrim(w_lista_char)),k_formato_fechas);
        po_lista := w_lista_tab;
        exit;
      else
        w_lista_tab(w_num_param) := to_date(ltrim(rtrim(substr(w_lista_char, 1, w_pos_separ - 1 ))),k_formato_fechas);
        w_lista_char := substr(w_lista_char, w_pos_separ+1, length(w_lista_char) );
      end if;

    end loop;

  exception
    when others then
      dbms_output.put_line( substr('<pr_inic_lista_fechas> Error: ' || sqlerrm , 1 , 250 ));
      raise;
  end pr_inic_lista_fechas;


  ----------------------------------------------------------------------------
  -- Depuracion: Muestra por pantalla una lista de oids
  ----------------------------------------------------------------------------
  procedure pr_mostrar_lista_oids( pi_lista  t_lista_oids ) is
  begin
    for i in nvl(pi_lista.first,0) .. nvl(pi_lista.last,-1) loop
      dbms_output.put_line( to_char(i) || ': ' || to_char(pi_lista(i)) );
    end loop;
  end pr_mostrar_lista_oids;


  ----------------------------------------------------------------------------
  -- Depuracion: Muestra por pantalla una lista de fechas
  ----------------------------------------------------------------------------
  procedure pr_mostrar_lista_fechas( pi_lista  t_lista_fechas ) is
  begin
    for i in nvl(pi_lista.first,0) .. nvl(pi_lista.last,-1) loop
      dbms_output.put_line( to_char(i) || ': ' || to_char(pi_lista(i),k_formato_fechas) );
    end loop;
  end pr_mostrar_lista_fechas;


  ------------------------------------------------------------------------------
  -- Crea un calendario en la tabla CRA_FERIA (Feriados)
  ------------------------------------------------------------------------------
  procedure pr_crear_calendario(
     pi_num_anio      number
    ,pi_oid_pais      number
    ,pi_oid_marca     number
    ,pi_oid_canal     number
    ,pi_grupos_zonas  varchar2
    ,pi_sabados       number
    ,pi_domingos      number ) is

    w_lista_zonas     t_lista_oids;
    w_lista_festivos  t_lista_fechas;


    ----------------------------------------------------------------------------
    -- Inicializa una variable de tabla pl de fechas con los sabados o domingos
    -- de un anio
    ----------------------------------------------------------------------------
    procedure pr_inic_lista_festivos(
       pi_anio   in  number
      ,pi_dias   in  varchar2  -- S sabados, D domingos
      ,po_lista  in out t_lista_fechas
    ) is
      w_fecha       date := to_date( to_char(pi_anio) || '0101', 'yyyymmdd' );
      w_dia_semana  varchar2(50);
      w_num_dias    number;
    begin
      w_num_dias := nvl(po_lista.last,0)+1;
      w_dia_semana := rtrim(to_char( w_fecha, 'DAY', 'NLS_DATE_LANGUAGE=''SPANISH''' ));

      -- sabados
      if pi_dias = 'S' then
        if w_dia_semana = 'LUNES' then
          w_fecha := w_fecha + 5;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'MARTES' then
          w_fecha := w_fecha + 4;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana like 'MI_RCOLES' then -- acento
          w_fecha := w_fecha + 3;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'JUEVES' then
          w_fecha := w_fecha + 2;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'VIERNES' then
          w_fecha := w_fecha + 1;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana like 'S_BADO' then -- acento
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'DOMINGO' then
          w_fecha := w_fecha + 6;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        end if;

      -- domingos
      elsif pi_dias = 'D' then
        if w_dia_semana = 'LUNES' then
          w_fecha := w_fecha + 6;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'MARTES' then
          w_fecha := w_fecha + 5;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana like 'MI_RCOLES' then -- acento
          w_fecha := w_fecha + 4;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'JUEVES' then
          w_fecha := w_fecha + 3;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'VIERNES' then
          w_fecha := w_fecha + 2;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana like 'S_BADO' then -- acento
          w_fecha := w_fecha + 1;
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        elsif w_dia_semana = 'DOMINGO' then
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        end if;
      end if;

      loop
        -- siguiente fecha
        w_fecha := w_fecha + 7;
        if to_number(to_char(w_fecha,'YYYY')) = pi_anio then
          po_lista(w_num_dias) := w_fecha;
          w_num_dias := w_num_dias + 1;
        else
          return;
        end if;

      end loop;

    exception
      when others then
        dbms_output.put_line( substr('<pr_inic_lista_festivos> Error: ' || sqlerrm , 1 , 250 ));
    end pr_inic_lista_festivos;

  begin -- pr_crear_calendario
    -- cargar los grupos de zonas
    pr_inic_lista_oids( rtrim(ltrim(pi_grupos_zonas)), w_lista_zonas );

    -- validar lista de zonas
    if w_lista_zonas.first is null then
      dbms_output.put_line( 'Error: el parametro de zonas es nulo' );
      return;
    end if;

    -- validar anio
    if pi_num_anio not between 1900 and 2200 then
      dbms_output.put_line( 'Error: el parametro de anio no esta incluido entre 1900 y 2200' );
      return;
    end if;

    -- cargar sabados
    if pi_sabados = 1 then
      pr_inic_lista_festivos( pi_num_anio, 'S', w_lista_festivos );
    end if;

    -- cargar domingos
    if pi_domingos = 1 then
      pr_inic_lista_festivos( pi_num_anio, 'D', w_lista_festivos );
    end if;

    -- Insertar sabados y domingos
    for w_zona in nvl(w_lista_zonas.first,0) .. nvl(w_lista_zonas.last,-1) loop
      for w_festivo in nvl(w_lista_festivos.first,0) .. nvl(w_lista_festivos.last,-1) loop
        -- insert select de las actividades para el pais, marca y canal indicados
        insert into cra_feria(
           oid_feri
          ,cgzo_oid_cabe_grup_zona
          ,cact_oid_acti
          ,num_anio
          ,fec_feri
          ,ind_fest
          ,ind_no_labo )
        select cra_feri_seq.nextval
              ,w_lista_zonas(w_zona)
              ,oid_acti
              ,pi_num_anio
              ,w_lista_festivos(w_festivo)
              ,0
              ,1
        from cra_activ
        where pais_oid_pais = pi_oid_pais
          and marc_oid_marc = pi_oid_marca
          and cana_oid_cana = pi_oid_canal;
      end loop;
    end loop;

  exception
    when others then
      dbms_output.put_line( substr('<pr_crear_calendario> Error: ' || sqlerrm , 1 , 250 ));
  end pr_crear_calendario;


  ------------------------------------------------------------------------------
  -- Modifica un calendario en la tabla CRA_FERIA (Feriados)
  ------------------------------------------------------------------------------
  procedure pr_modificar_calendario(
     pi_num_anio       number
    ,pi_grupos_zonas   varchar2
    ,pi_actividades    varchar2
    ,pi_festivos       varchar2
    ,pi_no_laborables  varchar2
  ) is

    w_lista_zonas          t_lista_oids;
    w_lista_actividades    t_lista_oids;
    w_lista_festivos       t_lista_fechas;
    w_lista_no_laborables  t_lista_fechas;

  begin
    pr_inic_lista_oids( pi_grupos_zonas, w_lista_zonas );
    pr_inic_lista_oids( pi_actividades, w_lista_actividades );
    pr_inic_lista_fechas( pi_festivos, w_lista_festivos );
    pr_inic_lista_fechas( pi_no_laborables, w_lista_no_laborables );

    -- validar lista de zonas
    if w_lista_zonas.first is null then
      dbms_output.put_line( 'Error: el parametro de zonas es nulo' );
      return;
    end if;

    -- validar lista de actividades
    if w_lista_actividades.first is null then
      dbms_output.put_line( 'Error: el parametro de actividades es nulo' );
      return;
    end if;

    -- Las listas de festivos y laborables no deberian ser las dos nulas
    if w_lista_festivos.first is null and w_lista_no_laborables.first is null then
      dbms_output.put_line( 'Error: los parametros de festivos y de no laborables son nulos' );
      return;
    end if;

    -- validar anio
    if pi_num_anio not between 1900 and 2200 then
      dbms_output.put_line( 'Error: el parametro de anio no esta incluido entre 1900 y 2200' );
      return;
    end if;

    for w_zona in w_lista_zonas.first .. w_lista_zonas.last loop

      for w_actividad in w_lista_actividades.first .. w_lista_actividades.last loop

        -- borrado de todos los feriados y no laborables para esta zona, actividad y anio
        delete cra_feria
        where cgzo_oid_cabe_grup_zona = w_lista_zonas(w_zona)
          and cact_oid_acti = w_lista_actividades(w_actividad)
          and num_anio = pi_num_anio;

        if w_lista_festivos.first is not null then
          -- insercion de festivos
          for w_festivo in w_lista_festivos.first .. w_lista_festivos.last loop
            insert into cra_feria (
               oid_feri
              ,cgzo_oid_cabe_grup_zona
              ,cact_oid_acti
              ,num_anio
              ,fec_feri
              ,ind_fest
              ,ind_no_labo )
            values (
               cra_feri_seq.nextval
              ,w_lista_zonas(w_zona)
              ,w_lista_actividades(w_actividad)
              ,pi_num_anio
              ,w_lista_festivos(w_festivo)
              ,1
              ,0
            );

          end loop;
        end if;

        if w_lista_no_laborables.first is not null then
          -- borrado e insercion de no laborables
          for w_no_laborable in w_lista_no_laborables.first .. w_lista_no_laborables.last loop

            insert into cra_feria (
               oid_feri
              ,cgzo_oid_cabe_grup_zona
              ,cact_oid_acti
              ,num_anio
              ,fec_feri
              ,ind_fest
              ,ind_no_labo )
            values (
               cra_feri_seq.nextval
              ,w_lista_zonas(w_zona)
              ,w_lista_actividades(w_actividad)
              ,pi_num_anio
              ,w_lista_no_laborables(w_no_laborable)
              ,0
              ,1
            );

          end loop;
        end if;
      end loop;  -- actividades
    end loop;  -- zonas

  exception
    when others then
      dbms_output.put_line( substr('<pr_modificar_calendario> Error: ' || sqlerrm , 1 , 250 ));
  end pr_modificar_calendario;

end pq_apl_cra;
/

