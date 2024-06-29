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
 * comments on tables, views, columns, sequences, triggers and routines defined
 * by users or system
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SystemComments extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS</code>
     */
    public static final SystemComments SYSTEM_COMMENTS = new SystemComments();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS.OBJECT_CATALOG</code>.
     */
    public final TableField<Record, String> OBJECT_CATALOG = createField(DSL.name("OBJECT_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS.OBJECT_SCHEMA</code>.
     */
    public final TableField<Record, String> OBJECT_SCHEMA = createField(DSL.name("OBJECT_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS.OBJECT_NAME</code>.
     */
    public final TableField<Record, String> OBJECT_NAME = createField(DSL.name("OBJECT_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS.OBJECT_TYPE</code>.
     */
    public final TableField<Record, String> OBJECT_TYPE = createField(DSL.name("OBJECT_TYPE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS.COLUMN_NAME</code>.
     */
    public final TableField<Record, String> COLUMN_NAME = createField(DSL.name("COLUMN_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS.COMMENT</code>.
     */
    public final TableField<Record, String> COMMENT = createField(DSL.name("COMMENT"), net.tuxanna.database.jooq.information_schema.Domains.CHARACTER_DATA.getDataType(), this, "");

    private SystemComments(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private SystemComments(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("comments on tables, views, columns, sequences, triggers and routines defined by users or system"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS</code> table
     * reference
     */
    public SystemComments(String alias) {
        this(DSL.name(alias), SYSTEM_COMMENTS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS</code> table
     * reference
     */
    public SystemComments(Name alias) {
        this(alias, SYSTEM_COMMENTS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.SYSTEM_COMMENTS</code> table reference
     */
    public SystemComments() {
        this(DSL.name("SYSTEM_COMMENTS"), null);
    }

    public <O extends Record> SystemComments(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSTEM_COMMENTS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public SystemComments as(String alias) {
        return new SystemComments(DSL.name(alias), this);
    }

    @Override
    public SystemComments as(Name alias) {
        return new SystemComments(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemComments rename(String name) {
        return new SystemComments(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public SystemComments rename(Name name) {
        return new SystemComments(name, null);
    }
}
