/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.system_lobs.routines;


import net.tuxanna.database.jooq.system_lobs.SystemLobs;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class DivideBlock extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = -1957511372;

    /**
     * The parameter <code>SYSTEM_LOBS.DIVIDE_BLOCK.B_OFFSET</code>.
     */
    public static final Parameter<Integer> B_OFFSET = Internal.createParameter("B_OFFSET", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>SYSTEM_LOBS.DIVIDE_BLOCK.L_ID</code>.
     */
    public static final Parameter<Long> L_ID = Internal.createParameter("L_ID", org.jooq.impl.SQLDataType.BIGINT, false, false);

    /**
     * Create a new routine call instance
     */
    public DivideBlock() {
        super("DIVIDE_BLOCK", SystemLobs.SYSTEM_LOBS);

        addInParameter(B_OFFSET);
        addInParameter(L_ID);
    }

    /**
     * Set the <code>B_OFFSET</code> parameter IN value to the routine
     */
    public void setBOffset(Integer value) {
        setValue(B_OFFSET, value);
    }

    /**
     * Set the <code>L_ID</code> parameter IN value to the routine
     */
    public void setLId(Long value) {
        setValue(L_ID, value);
    }
}