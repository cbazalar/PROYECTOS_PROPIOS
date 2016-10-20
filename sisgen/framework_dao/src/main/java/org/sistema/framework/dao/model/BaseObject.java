package org.sistema.framework.dao.model;


/**
 * @author pecbazalar
 *
 */
public abstract class BaseObject {
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public abstract String toString();

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public abstract boolean equals(Object o);

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public abstract int hashCode();
}

