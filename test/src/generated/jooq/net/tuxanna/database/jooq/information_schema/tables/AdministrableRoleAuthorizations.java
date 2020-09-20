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
 * one row for each role that can be granted
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AdministrableRoleAuthorizations extends TableImpl<Record> {

    private static final long serialVersionUID = 917124635;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.ADMINISTRABLE_ROLE_AUTHORIZATIONS</code>
     */
    public static final AdministrableRoleAuthorizations ADMINISTRABLE_ROLE_AUTHORIZATIONS = new AdministrableRoleAuthorizations();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.ADMINISTRABLE_ROLE_AUTHORIZATIONS.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ADMINISTRABLE_ROLE_AUTHORIZATIONS.ROLE_NAME</code>.
     */
    public final TableField<Record, String> ROLE_NAME = createField(DSL.name("ROLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ADMINISTRABLE_ROLE_AUTHORIZATIONS.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.ADMINISTRABLE_ROLE_AUTHORIZATIONS</code> table reference
     */
    public AdministrableRoleAuthorizations() {
        this(DSL.name("ADMINISTRABLE_ROLE_AUTHORIZATIONS"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ADMINISTRABLE_ROLE_AUTHORIZATIONS</code> table reference
     */
    public AdministrableRoleAuthorizations(String alias) {
        this(DSL.name(alias), ADMINISTRABLE_ROLE_AUTHORIZATIONS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ADMINISTRABLE_ROLE_AUTHORIZATIONS</code> table reference
     */
    public AdministrableRoleAuthorizations(Name alias) {
        this(alias, ADMINISTRABLE_ROLE_AUTHORIZATIONS);
    }

    private AdministrableRoleAuthorizations(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private AdministrableRoleAuthorizations(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each role that can be granted"), TableOptions.table());
    }

    public <O extends Record> AdministrableRoleAuthorizations(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, ADMINISTRABLE_ROLE_AUTHORIZATIONS);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public AdministrableRoleAuthorizations as(String alias) {
        return new AdministrableRoleAuthorizations(DSL.name(alias), this);
    }

    @Override
    public AdministrableRoleAuthorizations as(Name alias) {
        return new AdministrableRoleAuthorizations(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AdministrableRoleAuthorizations rename(String name) {
        return new AdministrableRoleAuthorizations(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AdministrableRoleAuthorizations rename(Name name) {
        return new AdministrableRoleAuthorizations(name, null);
    }
}