/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.information_schema.tables;


import net.tuxanna.database.jooq.information_schema.InformationSchema;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * storage space used by the indices of each accessible table defined within
 * this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemIndexstats extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of
     * <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS</code>
     */
    public static final SystemIndexstats SYSTEM_INDEXSTATS = new SystemIndexstats();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.TABLE_TYPE</code>.
     */
    public final TableField<Record, String> TABLE_TYPE = createField(DSL.name("TABLE_TYPE"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.INDEX_NAME</code>.
     */
    public final TableField<Record, String> INDEX_NAME = createField(DSL.name("INDEX_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.ORDINAL_POSITION</code>.
     */
    public final TableField<Record, Long> ORDINAL_POSITION = createField(DSL.name("ORDINAL_POSITION"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.CARDINALITY</code>.
     */
    public final TableField<Record, Long> CARDINALITY = createField(DSL.name("CARDINALITY"), SQLDataType.BIGINT, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.ALLOCATED_ROWS</code>.
     */
    public final TableField<Record, Long> ALLOCATED_ROWS = createField(DSL.name("ALLOCATED_ROWS"), SQLDataType.BIGINT, this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.ALLOCATED_SPACE</code>.
     */
    public final TableField<Record, Long> ALLOCATED_SPACE = createField(DSL.name("ALLOCATED_SPACE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.SPACE_ID</code>.
     */
    public final TableField<Record, Long> SPACE_ID = createField(DSL.name("SPACE_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS.BASE_SPACE</code>.
     */
    public final TableField<Record, Long> BASE_SPACE = createField(DSL.name("BASE_SPACE"), SQLDataType.BIGINT, this, "");

    private SystemIndexstats(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemIndexstats(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("storage space used by the indices of each accessible table defined within this database"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS</code> table
     * reference
     */
    public SystemIndexstats(String alias) {
        this(DSL.name(alias), SYSTEM_INDEXSTATS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS</code> table
     * reference
     */
    public SystemIndexstats(Name alias) {
        this(alias, SYSTEM_INDEXSTATS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_INDEXSTATS</code> table
     * reference
     */
    public SystemIndexstats() {
        this(DSL.name("SYSTEM_INDEXSTATS"), null);
    }

    public <O extends Record> SystemIndexstats(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_INDEXSTATS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemIndexstats as(String alias) {
        return new SystemIndexstats(DSL.name(alias), this);
    }

    @Override
    public SystemIndexstats as(Name alias) {
        return new SystemIndexstats(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemIndexstats rename(String name) {
        return new SystemIndexstats(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemIndexstats rename(Name name) {
        return new SystemIndexstats(name, null);
    }
}
