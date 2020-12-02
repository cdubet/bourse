/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.system_lobs;


import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * Convenience access to all sequences in SYSTEM_LOBS.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Sequences {

    /**
     * The sequence <code>SYSTEM_LOBS.LOB_ID</code>
     */
    public static final Sequence<Long> LOB_ID = Internal.createSequence("LOB_ID", SystemLobs.SYSTEM_LOBS, SQLDataType.BIGINT, null, null, null, null, false, null);
}
