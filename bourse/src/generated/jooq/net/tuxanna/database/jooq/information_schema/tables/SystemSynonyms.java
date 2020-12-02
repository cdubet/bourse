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
 * the synonyms for tables and other objects defined in this database
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemSynonyms extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS</code>
     */
    public static final SystemSynonyms SYSTEM_SYNONYMS = new SystemSynonyms();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS.SYNONYM_CATALOG</code>.
     */
    public final TableField<Record, String> SYNONYM_CATALOG = createField(DSL.name("SYNONYM_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS.SYNONYM_SCHEMA</code>.
     */
    public final TableField<Record, String> SYNONYM_SCHEMA = createField(DSL.name("SYNONYM_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS.SYNONYM_NAME</code>.
     */
    public final TableField<Record, String> SYNONYM_NAME = createField(DSL.name("SYNONYM_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS.OBJECT_CATALOG</code>.
     */
    public final TableField<Record, String> OBJECT_CATALOG = createField(DSL.name("OBJECT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS.OBJECT_SCHEMA</code>.
     */
    public final TableField<Record, String> OBJECT_SCHEMA = createField(DSL.name("OBJECT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS.OBJECT_NAME</code>.
     */
    public final TableField<Record, String> OBJECT_NAME = createField(DSL.name("OBJECT_NAME"), SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS.OBJECT_TYPE</code>.
     */
    public final TableField<Record, String> OBJECT_TYPE = createField(DSL.name("OBJECT_TYPE"), SQLDataType.VARCHAR(128), this, "");

    private SystemSynonyms(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemSynonyms(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("the synonyms for tables and other objects defined in this database"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS</code> table reference
     */
    public SystemSynonyms(String alias) {
        this(DSL.name(alias), SYSTEM_SYNONYMS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS</code> table reference
     */
    public SystemSynonyms(Name alias) {
        this(alias, SYSTEM_SYNONYMS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_SYNONYMS</code> table reference
     */
    public SystemSynonyms() {
        this(DSL.name("SYSTEM_SYNONYMS"), null);
    }

    public <O extends Record> SystemSynonyms(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_SYNONYMS);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemSynonyms as(String alias) {
        return new SystemSynonyms(DSL.name(alias), this);
    }

    @Override
    public SystemSynonyms as(Name alias) {
        return new SystemSynonyms(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSynonyms rename(String name) {
        return new SystemSynonyms(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemSynonyms rename(Name name) {
        return new SystemSynonyms(name, null);
    }
}
