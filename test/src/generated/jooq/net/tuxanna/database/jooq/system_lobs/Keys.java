/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.system_lobs;


import net.tuxanna.database.jooq.system_lobs.tables.Blocks;
import net.tuxanna.database.jooq.system_lobs.tables.LobIds;
import net.tuxanna.database.jooq.system_lobs.tables.Lobs;
import net.tuxanna.database.jooq.system_lobs.tables.Parts;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>SYSTEM_LOBS</code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<Record> BLOCKS_PK = UniqueKeys0.BLOCKS_PK;
    public static final UniqueKey<Record> LOB_IDS_PK = UniqueKeys0.LOB_IDS_PK;
    public static final UniqueKey<Record> LOBS_UQ1 = UniqueKeys0.LOBS_UQ1;
    public static final UniqueKey<Record> LOBS_PK = UniqueKeys0.LOBS_PK;
    public static final UniqueKey<Record> PARTS_PK = UniqueKeys0.PARTS_PK;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class UniqueKeys0 {
        public static final UniqueKey<Record> BLOCKS_PK = Internal.createUniqueKey(Blocks.BLOCKS, "BLOCKS_PK", new TableField[] { Blocks.BLOCKS.BLOCK_ADDR }, true);
        public static final UniqueKey<Record> LOB_IDS_PK = Internal.createUniqueKey(LobIds.LOB_IDS, "LOB_IDS_PK", new TableField[] { LobIds.LOB_IDS.LOB_ID }, true);
        public static final UniqueKey<Record> LOBS_UQ1 = Internal.createUniqueKey(Lobs.LOBS, "LOBS_UQ1", new TableField[] { Lobs.LOBS.BLOCK_ADDR }, true);
        public static final UniqueKey<Record> LOBS_PK = Internal.createUniqueKey(Lobs.LOBS, "LOBS_PK", new TableField[] { Lobs.LOBS.LOB_ID, Lobs.LOBS.BLOCK_OFFSET }, true);
        public static final UniqueKey<Record> PARTS_PK = Internal.createUniqueKey(Parts.PARTS, "PARTS_PK", new TableField[] { Parts.PARTS.LOB_ID, Parts.PARTS.BLOCK_OFFSET }, true);
    }
}
