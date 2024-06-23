/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.system_lobs.tables;


import net.tuxanna.database.jooq.system_lobs.Keys;
import net.tuxanna.database.jooq.system_lobs.SystemLobs;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Parts extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SYSTEM_LOBS.PARTS</code>
     */
    public static final Parts PARTS = new Parts();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>SYSTEM_LOBS.PARTS.BLOCK_COUNT</code>.
     */
    public final TableField<Record, Integer> BLOCK_COUNT = createField(DSL.name("BLOCK_COUNT"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>SYSTEM_LOBS.PARTS.BLOCK_OFFSET</code>.
     */
    public final TableField<Record, Integer> BLOCK_OFFSET = createField(DSL.name("BLOCK_OFFSET"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>SYSTEM_LOBS.PARTS.PART_OFFSET</code>.
     */
    public final TableField<Record, Long> PART_OFFSET = createField(DSL.name("PART_OFFSET"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>SYSTEM_LOBS.PARTS.PART_LENGTH</code>.
     */
    public final TableField<Record, Long> PART_LENGTH = createField(DSL.name("PART_LENGTH"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>SYSTEM_LOBS.PARTS.PART_BYTES</code>.
     */
    public final TableField<Record, Long> PART_BYTES = createField(DSL.name("PART_BYTES"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>SYSTEM_LOBS.PARTS.LOB_ID</code>.
     */
    public final TableField<Record, Long> LOB_ID = createField(DSL.name("LOB_ID"), SQLDataType.BIGINT, this, "");

    private Parts(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Parts(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>SYSTEM_LOBS.PARTS</code> table reference
     */
    public Parts(String alias) {
        this(DSL.name(alias), PARTS);
    }

    /**
     * Create an aliased <code>SYSTEM_LOBS.PARTS</code> table reference
     */
    public Parts(Name alias) {
        this(alias, PARTS);
    }

    /**
     * Create a <code>SYSTEM_LOBS.PARTS</code> table reference
     */
    public Parts() {
        this(DSL.name("PARTS"), null);
    }

    public <O extends Record> Parts(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, PARTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : SystemLobs.SYSTEM_LOBS;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.PARTS_PK;
    }

    @Override
    public Parts as(String alias) {
        return new Parts(DSL.name(alias), this);
    }

    @Override
    public Parts as(Name alias) {
        return new Parts(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Parts rename(String name) {
        return new Parts(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Parts rename(Name name) {
        return new Parts(name, null);
    }
}
