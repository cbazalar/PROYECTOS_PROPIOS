CREATE OR REPLACE PROCEDURE "TMP_PR_CAMBIOS_OID" (
  pi_accion in varchar2   -- I -> generar informe de cambios
                          -- C -> realizar cambios (y generar tambien informe)
) as

  k_accion_informe       constant varchar2(1) := 'I';
  k_accion_cambios       constant varchar2(1) := 'C';
  TYPE t_cursor_dinamico IS REF CURSOR;
  c_cursor_dinamico      t_cursor_dinamico;
  w_orden_plantilla      varchar2(4000);
  w_orden                varchar2(4000);
  w_campo_oid            varchar2(30);
  w_campo_oid_hijo       varchar2(30);
  w_campo_fk             varchar2(30);
  w_oid_hijo             number(12);

  -- cantidad a sumar a cada oid que se va a modificar
--  k_desplazamiento       constant number := 1000;

  -- tablas a cambiar
  cursor c_tablas is
    select distinct t1.table_name
                   ,t1.constraint_name
    from user_constraints     t1
        ,tmp_registros_online t2
    where t1.table_name = t2.tabla
      and t1.constraint_type = 'P'
      and ind_tratamiento = 1
    order by t1.table_name;

  -- registros a cambiar de una tabla
  cursor c_registros( pi_tabla  in  varchar2 ) is
    select oid, oid_nuevo
    from tmp_registros_online
    where tabla = pi_tabla
      and ind_tratamiento = 1
    order by oid;

  -- constraints que dependen de la pk a modificar
  cursor c_constraints( pi_tabla  varchar2 ) is
    select table_name, constraint_name
    from user_constraints
    where constraint_type = 'R'
      and r_constraint_name in ( select constraint_name
                                 from user_constraints
                                 where constraint_type = 'P'
                                   and table_name = pi_tabla )
    order by table_name, constraint_name;

  ----
  -- deshabilitar fks afectadas
  ----
  procedure pr_disable is
  begin
    for reg_tabla in c_tablas loop
      for reg_constraint in c_constraints(reg_tabla.table_name) loop
        execute immediate 'alter table '
          || reg_constraint.table_name || ' modify constraint ' || reg_constraint.constraint_name || ' disable';
      end loop;
    end loop;
  end pr_disable;

  ----
  -- habilitar fks afectadas
  ----
  procedure pr_enable is
  begin
    for reg_tabla in c_tablas loop
      for reg_constraint in c_constraints(reg_tabla.table_name) loop
        execute immediate 'alter table '
          || reg_constraint.table_name || ' modify constraint ' || reg_constraint.constraint_name || ' enable';
      end loop;
    end loop;
  end pr_enable;

begin
  -- validar los parametros
  if pi_accion is null or pi_accion not in (k_accion_informe,k_accion_cambios) then
    dbms_output.put_line( 'Error: el parametro de entrada debe ser I (informe) o C (cambios)' );
    return;
  end if;

  if pi_accion = k_accion_informe then -- informe
    delete tmp_registros_a_cambiar;
  else
    -- llamada recursiva para generar primero el informe
    tmp_pr_cambios_oid( k_accion_informe );
    pr_disable;
  end if;

  for reg_tabla in c_tablas loop

    -- averiguar el campo oid
    select column_name
    into w_campo_oid
    from user_cons_columns
    where constraint_name = reg_tabla.constraint_name
      and table_name = reg_tabla.table_name;

    for reg_registro in c_registros(reg_tabla.table_name) loop
        if pi_accion = k_accion_informe then
          -- cambio del propio registro (la pk, el oid)

--DBMS_OUTPUT.PUT_LINE( reg_tabla.table_name || '-' || TO_CHAR(reg_registro.oid) || '-' || w_campo_oid );

          -- coger el siguiente valor de la secuencia si no se ha definido un valor fijo
          if reg_registro.oid_nuevo is null then
            execute immediate 'select '
              || substr(reg_tabla.table_name,1,4)
              || substr(reg_tabla.constraint_name,1,4)
              || '_SEQ.nextval from dual'
            into reg_registro.oid_nuevo;

            -- actualizar para dejar constancia del oid nuevo que toma
            -- para cuando sea k_accion_cambios tendra todo relleno
            update tmp_registros_online
            set oid_nuevo = reg_registro.oid_nuevo
            where oid   = reg_registro.oid
              and tabla = reg_tabla.table_name;
          end if;

          insert into tmp_registros_a_cambiar(
             tabla
            ,oid
            ,campo
            ,valor_antiguo
            ,valor_nuevo )
          values(
             reg_tabla.table_name
            ,reg_registro.oid
            ,w_campo_oid
            ,reg_registro.oid
            ,reg_registro.oid_nuevo
          );

        else

          -- update del registro padre
          execute immediate 'update '
            || reg_tabla.table_name
            || ' set '
            || w_campo_oid
            || ' = '
            || to_char(reg_registro.oid_nuevo)
            || ' where '
            || w_campo_oid
            || ' = '
            || to_char(reg_registro.oid);

          -- si la tabla tiene atributos traducibles, actualizar tambien sus oids
          update gen_i18n_sicc
          set val_oid = reg_registro.oid_nuevo
          where val_oid = reg_registro.oid
            and attr_enti = reg_tabla.table_name;

        end if;

      for reg_constraint in c_constraints(reg_tabla.table_name) loop
        select column_name
        into w_campo_oid_hijo
        from user_cons_columns
        where constraint_name = ( select constraint_name
                                  from user_constraints
                                  where table_name = reg_constraint.table_name
                                    and constraint_type = 'P' )
          and table_name = reg_constraint.table_name;

        -- averiguar el campo FK
        select column_name
        into w_campo_fk
        from user_cons_columns
        where constraint_name = reg_constraint.constraint_name
          and table_name = reg_constraint.table_name;

        -- orden del cursor dinamico
        w_orden_plantilla := 'select '
          || w_campo_oid_hijo
          || ' oid from '
          || reg_constraint.table_name
          || ' where '
          || w_campo_fk
          || ' = # order by '
          || w_campo_oid_hijo;


        -- cambio de cada registro que apunte al primero
        -- montar un cursor dinamico para ver los registros hijo concretos a modificar
        w_orden := replace( w_orden_plantilla, '#', to_char(reg_registro.oid) );

        open c_cursor_dinamico for w_orden;
        loop
          fetch c_cursor_dinamico into w_oid_hijo;
          exit when c_cursor_dinamico%notfound;
            if pi_accion = k_accion_informe then
              -- cambio de un registro hijo
              insert into tmp_registros_a_cambiar(
                 tabla
                ,oid
                ,campo
                ,valor_antiguo
                ,valor_nuevo )
              values(
                 reg_constraint.table_name
                ,w_oid_hijo
                ,w_campo_fk
                ,reg_registro.oid
                ,reg_registro.oid_nuevo
              );
            else
              -- update del registro hijo
              execute immediate 'update '
                || reg_constraint.table_name
                || ' set '
                || w_campo_fk
                || ' = '
                || to_char(reg_registro.oid_nuevo)
                || ' where '
                || w_campo_oid_hijo
                || ' = '
                || to_char(w_oid_hijo);
            end if;
        end loop;
        close c_cursor_dinamico;

      end loop;  -- c_registros

    end loop;  -- c_constraints

  end loop;  -- c_tablas

  if pi_accion = k_accion_cambios then
    pr_enable;
  end if;

  commit;

end tmp_pr_cambios_oid;
/

