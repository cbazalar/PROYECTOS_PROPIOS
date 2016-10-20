create or replace and compile java source named calendario as
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Calendario {


  public static int getNumeroSemana(int anno, int mes, int dia) throws Exception {
      try {
  				Calendar xmas = new GregorianCalendar(anno, mes, dia);
				  xmas.setFirstDayOfWeek(2);
    			int diaAnno = xmas.get(Calendar.WEEK_OF_YEAR);
    			return diaAnno;
      }
      catch(Exception e) {
          e.printStackTrace();
          throw e;
      }
   }
}
/

