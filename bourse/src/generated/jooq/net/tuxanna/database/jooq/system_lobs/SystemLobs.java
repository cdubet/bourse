/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.system_lobs;


import java.util.Arrays;
import java.util.List;

import net.tuxanna.database.jooq.DefaultCatalog;
import net.tuxanna.database.jooq.system_lobs.tables.Blocks;
import net.tuxanna.database.jooq.system_lobs.tables.LobIds;
import net.tuxanna.database.jooq.system_lobs.tables.Lobs;
import net.tuxanna.database.jooq.system_lobs.tables.Parts;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemLobs extends SchemaImpl {

    private static final long serialVersionUID = 1491176187;

    /**
     * The reference instance of <code>SYSTEM_LOBS</code>
     */
    public static final SystemLobs SYSTEM_LOBS = new SystemLobs();

    /**
     * The table <code>SYSTEM_LOBS.BLOCKS</code>.
     */
    public final Blocks BLOCKS = Blocks.BLOCKS;

    /**
     * The table <code>SYSTEM_LOBS.LOB_IDS</code>.
     */
    public final LobIds LOB_IDS = LobIds.LOB_IDS;

    /**
     * The table <code>SYSTEM_LOBS.LOBS</code>.
     */
    public final Lobs LOBS = Lobs.LOBS;

    /**
     * The table <code>SYSTEM_LOBS.PARTS</code>.
     */
    public final Parts PARTS = Parts.PARTS;

    /**
     * No further instances allowed
     */
    private SystemLobs() {
        super("SYSTEM_LOBS", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.<Sequence<?>>asList(
            Sequences.LOB_ID);
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Blocks.BLOCKS,
            LobIds.LOB_IDS,
            Lobs.LOBS,
            Parts.PARTS);
    }
}