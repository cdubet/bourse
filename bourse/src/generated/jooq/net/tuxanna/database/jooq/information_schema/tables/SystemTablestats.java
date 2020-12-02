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
 * cardinality and storage space used by tables and table spaces
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemTablestats extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS</code>
     */
    public static final SystemTablestats SYSTEM_TABLESTATS = new SystemTablestats();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.TABLE_CATALOG</code>.
     */
    public final TableField<Record, String> TABLE_CATALOG = createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.TABLE_SCHEMA</code>.
     */
    public final TableField<Record, String> TABLE_SCHEMA = createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.TABLE_NAME</code>.
     */
    public final TableField<Record, String> TABLE_NAME = createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.TABLE_TYPE</code>.
     */
    public final TableField<Record, String> TABLE_TYPE = createField(DSL.name("TABLE_TYPE"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.CARDINALITY</code>.
     */
    public final TableField<Record, Long> CARDINALITY = createField(DSL.name("CARDINALITY"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.SPACE_ID</code>.
     */
    public final TableField<Record, Long> SPACE_ID = createField(DSL.name("SPACE_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.USED_SPACE</code>.
     */
    public final TableField<Record, Long> USED_SPACE = createField(DSL.name("USED_SPACE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.ALLOCATED_SPACE</code>.
     */
    public final TableField<Record, Long> ALLOCATED_SPACE = createField(DSL.name("ALLOCATED_SPACE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS.USED_MEMORY</code>.
     */
    public final TableField<Record, Long> USED_MEMORY = createField(DSL.name("USED_MEMORY"), SQLDataType.BIGINT, this, "");

    private SystemTablestats(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemTablestats(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("cardinality and storage space used by tables and table spaces"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS</code> table reference
     */
    public SystemTablestats(String alias) {
        this(DSL.name(alias), SYSTEM_TABLESTATS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS</code> table reference
     */
    public SystemTablestats(Name alias) {
        this(alias, SYSTEM_TABLESTATS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_TABLESTATS</code> table reference
     */
    public SystemTablestats() {
        this(DSL.name("SYSTEM_TABLESTATS"), null);
    }

    public <O extends Record> SystemTablestats(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_TABLESTATS);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemTablestats as(String alias) {
        return new SystemTablestats(DSL.name(alias), this);
    }

    @Override
    public SystemTablestats as(Name alias) {
        return new SystemTablestats(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemTablestats rename(String name) {
        return new SystemTablestats(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemTablestats rename(Name name) {
        return new SystemTablestats(name, null);
    }
}
