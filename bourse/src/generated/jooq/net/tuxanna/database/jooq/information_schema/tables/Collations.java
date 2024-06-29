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
import org.jooq.impl.TableImpl;


/**
 * one row for each character collation descriptor.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Collations extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.COLLATIONS</code>
     */
    public static final Collations COLLATIONS = new Collations();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.COLLATIONS.COLLATION_CATALOG</code>.
     */
    public final TableField<Record, String> COLLATION_CATALOG = createField(DSL.name("COLLATION_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLLATIONS.COLLATION_SCHEMA</code>.
     */
    public final TableField<Record, String> COLLATION_SCHEMA = createField(DSL.name("COLLATION_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLLATIONS.COLLATION_NAME</code>.
     */
    public final TableField<Record, String> COLLATION_NAME = createField(DSL.name("COLLATION_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.COLLATIONS.PAD_ATTRIBUTE</code>.
     */
    public final TableField<Record, String> PAD_ATTRIBUTE = createField(DSL.name("PAD_ATTRIBUTE"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    private Collations(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Collations(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each character collation descriptor."), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLLATIONS</code> table
     * reference
     */
    public Collations(String alias) {
        this(DSL.name(alias), COLLATIONS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.COLLATIONS</code> table
     * reference
     */
    public Collations(Name alias) {
        this(alias, COLLATIONS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.COLLATIONS</code> table reference
     */
    public Collations() {
        this(DSL.name("COLLATIONS"), null);
    }

    public <O extends Record> Collations(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, COLLATIONS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public Collations as(String alias) {
        return new Collations(DSL.name(alias), this);
    }

    @Override
    public Collations as(Name alias) {
        return new Collations(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Collations rename(String name) {
        return new Collations(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Collations rename(Name name) {
        return new Collations(name, null);
    }
}
