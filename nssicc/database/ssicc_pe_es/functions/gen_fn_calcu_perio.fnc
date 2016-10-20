CREATE OR REPLACE FUNCTION "GEN_FN_CALCU_PERIO"
(
  p_periodo      IN VARCHAR2,
  p_num_periodos IN NUMBER
) RETURN VARCHAR2 IS
/**************************************************************
Descripcion        : Calcula el Periodo de acuerdo al numero
                     de peridos a sumar o restar, el cual es pasado
					 como parametro, si el valor del segundo
					 parametro es positivo, se suman en caso contrario
					 se restan al periodo pasado como primer
					 parametro.
Fecha Creacion     : 02/04/2007
Parametros         : vchPERIODO : Periodo
                     numPERIODOS: Numero Periodos a Sumar/Restar
Autor              : Carlos Hurtado Ramirez
Version            : Final
***************************************************************/

BEGIN

  RETURN gen_pkg_gener.gen_fn_perio_nsigu(NULL, p_periodo, p_num_periodos);

END gen_fn_calcu_perio;
/
