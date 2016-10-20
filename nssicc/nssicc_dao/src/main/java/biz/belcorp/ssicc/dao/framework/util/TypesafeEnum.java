package biz.belcorp.ssicc.dao.framework.util;

/**
 * 
 * @author David Hinostroza Vintes
 *
 */

public class TypesafeEnum {
	
	public static final int PE_LEVEL  = 0;
    public static final int CO_LEVEL  = 1;
    public static final int AR_LEVEL  = 2;
    public static final int CR_LEVEL  = 3;
    public static final int DO_LEVEL  = 4;
    public static final int EC_LEVEL  = 5;
    public static final int GT_LEVEL  = 6;
    public static final int MX_LEVEL  = 7;
    public static final int PR_LEVEL  = 8;
    public static final int SV_LEVEL  = 9;
    public static final int VE_LEVEL  = 10;

    public static final int BO_LEVEL  = 11;
    public static final int CL_LEVEL  = 12;
    
    //COLOMBIA ESIKA
    public static final int COE_LEVEL  = 13;
    //MEXICO ESIKA
    public static final int MXE_LEVEL  = 14;
	//VENEZUELA ESIKA
    public static final int VEE_LEVEL  = 15;
    //SALVADOR
    public static final int SVE_LEVEL  = 16;    
    //GUATEMALA
    public static final int GTE_LEVEL  = 17;
    //PANAMA
    public static final int PA_LEVEL   = 18;
	
	private final int level;	
	
	/**
	 * @param level
	 */
	private TypesafeEnum(int level) 	{		
		this.level = level;		
	}	
	
	public static final TypesafeEnum PE = new TypesafeEnum(PE_LEVEL);	
	public static final TypesafeEnum CO = new TypesafeEnum(CO_LEVEL);	
	public static final TypesafeEnum AR = new TypesafeEnum(AR_LEVEL);
    public static final TypesafeEnum CR = new TypesafeEnum(CR_LEVEL);
    public static final TypesafeEnum DO = new TypesafeEnum(DO_LEVEL);
    public static final TypesafeEnum EC = new TypesafeEnum(EC_LEVEL);
    public static final TypesafeEnum GT = new TypesafeEnum(GT_LEVEL);
    public static final TypesafeEnum MX = new TypesafeEnum(MX_LEVEL);
    public static final TypesafeEnum PR = new TypesafeEnum(PR_LEVEL);
    public static final TypesafeEnum SV = new TypesafeEnum(SV_LEVEL);
    public static final TypesafeEnum VE = new TypesafeEnum(VE_LEVEL);
    public static final TypesafeEnum BO = new TypesafeEnum(BO_LEVEL);
    public static final TypesafeEnum CL = new TypesafeEnum(CL_LEVEL);
    public static final TypesafeEnum PA = new TypesafeEnum(PA_LEVEL);
    
    
    public static final TypesafeEnum COE = new TypesafeEnum(COE_LEVEL);
    public static final TypesafeEnum MXE = new TypesafeEnum(MXE_LEVEL);
    public static final TypesafeEnum VEE = new TypesafeEnum(VEE_LEVEL);
    public static final TypesafeEnum SVE = new TypesafeEnum(SVE_LEVEL);
    public static final TypesafeEnum GTE = new TypesafeEnum(GTE_LEVEL);
    
    
	/**
	 * @return
	 */
	public int toLevel()		
	{		return level;		
	}

	private static final TypesafeEnum __VOLUMES[] = {
		PE, CO, AR, CR, DO, EC, GT, MX, PR, SV, VE, BO, CL, COE, MXE, VEE, SVE, GTE, PA};
	
	
	/**
	 * @param level
	 * @return
	 */
	public static final TypesafeEnum getDataSource(int level) {
	      if(level >= PE_LEVEL && level <= PA_LEVEL)
	        return __VOLUMES[level];
	      return null;
	}

	/**
	 * @param level
	 * @return
	 */
	public static final TypesafeEnum getDataSource(String level) {
	    int leveln=-1;
	    leveln=Integer.valueOf(level).intValue();
		if(leveln >= PE_LEVEL && leveln <= PA_LEVEL)
	        return __VOLUMES[leveln];
	    return null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
	      return (level == ((TypesafeEnum)obj).level);
	}

    /**
     * @param obj
     * @return
     */
    public int compareTo(Object obj) {
	      // Assume proper type was given
	      int other = ((TypesafeEnum)obj).level;
	      if(level == other)
	        return 0;
	      if(level < other)
	        return -1;
	      return 1;
	}

	/**
	 * @return
	 */
	public int getLevel() { return level; }
	
}

