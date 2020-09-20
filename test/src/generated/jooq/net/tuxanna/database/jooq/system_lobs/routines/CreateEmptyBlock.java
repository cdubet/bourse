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
public class CreateEmptyBlock extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = -1308374378;

    /**
     * The parameter <code>SYSTEM_LOBS.CREATE_EMPTY_BLOCK.B_ADDR</code>.
     */
    public static final Parameter<Integer> B_ADDR = Internal.createParameter("B_ADDR", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>SYSTEM_LOBS.CREATE_EMPTY_BLOCK.B_COUNT</code>.
     */
    public static final Parameter<Integer> B_COUNT = Internal.createParameter("B_COUNT", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public CreateEmptyBlock() {
        super("CREATE_EMPTY_BLOCK", SystemLobs.SYSTEM_LOBS);

        addInOutParameter(B_ADDR);
        addInParameter(B_COUNT);
    }

    /**
     * Set the <code>B_ADDR</code> parameter IN value to the routine
     */
    public void setBAddr(Integer value) {
        setValue(B_ADDR, value);
    }

    /**
     * Set the <code>B_COUNT</code> parameter IN value to the routine
     */
    public void setBCount(Integer value) {
        setValue(B_COUNT, value);
    }

    /**
     * Get the <code>B_ADDR</code> parameter OUT value from the routine
     */
    public Integer getBAddr() {
        return get(B_ADDR);
    }
}