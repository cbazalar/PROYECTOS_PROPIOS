create or replace trigger "PRDT_BIU_TR" 
before insert or update or delete on
gen_prdto
for each row
declare
  w_error            varchar2(500);
  reg_procedimiento  gen_prdto%rowtype;
  w_primera_fecha    date;
  w_siguiente_fecha  varchar2(4000);

begin

  if inserting or updating then

    -- comprobar que no se modifica el numero de job
    if :new.val_id_job <> :old.val_id_job
       or
       ( :new.val_id_job is null and :old.val_id_job is not null )
       or
       ( :new.val_id_job is not null and :old.val_id_job is null ) then

      raise_application_error( -20001, 'Error: el valor de la columna VAL_ID_JOB solo se gestiona internamente' );

    end if;

    -- si la planificacion es semanal, evitar nulos en los dias de la semana
    if :new.ind_plan_unic = 1 then
      :new.ind_dia_sem1 := nvl( :new.ind_dia_sem1, 0 );
      :new.ind_dia_sem2 := nvl( :new.ind_dia_sem2, 0 );
      :new.ind_dia_sem3 := nvl( :new.ind_dia_sem3, 0 );
      :new.ind_dia_sem4 := nvl( :new.ind_dia_sem4, 0 );
      :new.ind_dia_sem5 := nvl( :new.ind_dia_sem5, 0 );
      :new.ind_dia_sem6 := nvl( :new.ind_dia_sem6, 0 );
      :new.ind_dia_sem7 := nvl( :new.ind_dia_sem7, 0 );
    end if;

    -- cargar los valores nuevos del registro en una variable registro
    reg_procedimiento.oid_proc           := :new.oid_proc;
    reg_procedimiento.cod_proc           := :new.cod_proc;
    reg_procedimiento.des_proc           := :new.des_proc;
    reg_procedimiento.ind_plan_unic      := :new.ind_plan_unic;
    reg_procedimiento.ind_plan_sema      := :new.ind_plan_sema;
    reg_procedimiento.ind_plan_mens      := :new.ind_plan_mens;
    reg_procedimiento.fec_hora_prim_ejec := :new.fec_hora_prim_ejec;
    reg_procedimiento.ind_dia_sem1       := :new.ind_dia_sem1;
    reg_procedimiento.ind_dia_sem2       := :new.ind_dia_sem2;
    reg_procedimiento.ind_dia_sem3       := :new.ind_dia_sem3;
    reg_procedimiento.ind_dia_sem4       := :new.ind_dia_sem4;
    reg_procedimiento.ind_dia_sem5       := :new.ind_dia_sem5;
    reg_procedimiento.ind_dia_sem6       := :new.ind_dia_sem6;
    reg_procedimiento.ind_dia_sem7       := :new.ind_dia_sem7;
    reg_procedimiento.val_id_job         := :new.val_id_job;

    -- validar la planificacion. Se rechaza cualquier modificacion que
    -- implique una planificacion incoherente
    w_error := pq_plani.fn_valid_planif( :new.cod_proc, reg_procedimiento );
    if w_error is not null then
      raise_application_error( -20001, w_error );
    end if;

    -- si no hay planificacion, quitar el job si existe
    if :new.ind_plan_unic = 0
       and
 	     :new.ind_plan_sema = 0
 	     and
	     :new.ind_plan_mens = 0 then

      if :old.val_id_job is not null then
  			dbms_job.remove( :old.val_id_job );
  			:new.val_id_job := null;
	    end if;

    else -- si hay planificacion

	    -- calcular la funcion de planificacion de la tarea
		  pq_plani.pr_calculo_planif(
		     reg_procedimiento
		    ,w_primera_fecha
		    ,w_siguiente_fecha
		  );

      -- comprobar si hay que hacer summit por primera vez o bien
	    -- hay que replanificar un job que ya existe
	    if :old.val_id_job is null then

				DBMS_JOB.SUBMIT(
					 :new.val_id_job
					,:new.val_codi
					,w_primera_fecha
					,nvl( w_siguiente_fecha, 'NULL' )
					-- no_parse IN BOOLEAN DEFAULT FALSE,
					-- instance IN BINARY_INTEGER DEFAULT any_instance,
					-- force IN BOOLEAN DEFAULT FALSE
	      );

	    else -- cambiar el job existente

	      DBMS_JOB.CHANGE (
					 :new.val_id_job
	        ,:new.val_codi
					,w_primera_fecha
					,nvl( w_siguiente_fecha, 'NULL' )
					-- instance IN BINARY_INTEGER DEFAULT NULL,
					-- force IN BOOLEAN DEFAULT FALSE
	      );

	    end if;

    end if;

  elsif deleting then

    -- borrar el job si existia
    if :old.val_id_job is not null then
      dbms_job.remove( :old.val_id_job );
    end if;

  end if;

end PRDT_BIU_TR;
/

