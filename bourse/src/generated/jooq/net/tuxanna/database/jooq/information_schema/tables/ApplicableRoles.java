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
 * one row for each role granted to an authorization
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ApplicableRoles extends TableImpl<Record> {

    private static final long serialVersionUID = 474770562;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.APPLICABLE_ROLES</code>
     */
    public static final ApplicableRoles APPLICABLE_ROLES = new ApplicableRoles();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.APPLICABLE_ROLES.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.APPLICABLE_ROLES.ROLE_NAME</code>.
     */
    public final TableField<Record, String> ROLE_NAME = createField(DSL.name("ROLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.APPLICABLE_ROLES.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.APPLICABLE_ROLES</code> table reference
     */
    public ApplicableRoles() {
        this(DSL.name("APPLICABLE_ROLES"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.APPLICABLE_ROLES</code> table reference
     */
    public ApplicableRoles(String alias) {
        this(DSL.name(alias), APPLICABLE_ROLES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.APPLICABLE_ROLES</code> table reference
     */
    public ApplicableRoles(Name alias) {
        this(alias, APPLICABLE_ROLES);
    }

    private ApplicableRoles(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private ApplicableRoles(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each role granted to an authorization"), TableOptions.table());
    }

    public <O extends Record> ApplicableRoles(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, APPLICABLE_ROLES);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public ApplicableRoles as(String alias) {
        return new ApplicableRoles(DSL.name(alias), this);
    }

    @Override
    public ApplicableRoles as(Name alias) {
        return new ApplicableRoles(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public ApplicableRoles rename(String name) {
        return new ApplicableRoles(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ApplicableRoles rename(Name name) {
        return new ApplicableRoles(name, null);
    }
}