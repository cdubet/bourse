/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.system_lobs.tables;


import java.util.Arrays;
import java.util.List;

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
public class Lobs extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>SYSTEM_LOBS.LOBS</code>
     */
    public static final Lobs LOBS = new Lobs();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>SYSTEM_LOBS.LOBS.BLOCK_ADDR</code>.
     */
    public final TableField<Record, Integer> BLOCK_ADDR = createField(DSL.name("BLOCK_ADDR"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>SYSTEM_LOBS.LOBS.BLOCK_COUNT</code>.
     */
    public final TableField<Record, Integer> BLOCK_COUNT = createField(DSL.name("BLOCK_COUNT"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>SYSTEM_LOBS.LOBS.BLOCK_OFFSET</code>.
     */
    public final TableField<Record, Integer> BLOCK_OFFSET = createField(DSL.name("BLOCK_OFFSET"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>SYSTEM_LOBS.LOBS.LOB_ID</code>.
     */
    public final TableField<Record, Long> LOB_ID = createField(DSL.name("LOB_ID"), SQLDataType.BIGINT, this, "");

    private Lobs(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Lobs(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>SYSTEM_LOBS.LOBS</code> table reference
     */
    public Lobs(String alias) {
        this(DSL.name(alias), LOBS);
    }

    /**
     * Create an aliased <code>SYSTEM_LOBS.LOBS</code> table reference
     */
    public Lobs(Name alias) {
        this(alias, LOBS);
    }

    /**
     * Create a <code>SYSTEM_LOBS.LOBS</code> table reference
     */
    public Lobs() {
        this(DSL.name("LOBS"), null);
    }

    public <O extends Record> Lobs(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, LOBS);
    }

    @Override
    public Schema getSchema() {
        return SystemLobs.SYSTEM_LOBS;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.LOBS_PK;
    }

    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.LOBS_UQ1, Keys.LOBS_PK);
    }

    @Override
    public Lobs as(String alias) {
        return new Lobs(DSL.name(alias), this);
    }

    @Override
    public Lobs as(Name alias) {
        return new Lobs(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Lobs rename(String name) {
        return new Lobs(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Lobs rename(Name name) {
        return new Lobs(name, null);
    }
}
