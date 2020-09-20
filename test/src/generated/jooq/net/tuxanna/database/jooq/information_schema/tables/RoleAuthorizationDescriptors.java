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
 * one row for each role granted directly to a grantee
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleAuthorizationDescriptors extends TableImpl<Record> {

    private static final long serialVersionUID = 345526662;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS</code>
     */
    public static final RoleAuthorizationDescriptors ROLE_AUTHORIZATION_DESCRIPTORS = new RoleAuthorizationDescriptors();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS.ROLE_NAME</code>.
     */
    public final TableField<Record, String> ROLE_NAME = createField(DSL.name("ROLE_NAME"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS.GRANTEE</code>.
     */
    public final TableField<Record, String> GRANTEE = createField(DSL.name("GRANTEE"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS.GRANTOR</code>.
     */
    public final TableField<Record, String> GRANTOR = createField(DSL.name("GRANTOR"), org.jooq.impl.SQLDataType.VARCHAR(128), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS.IS_GRANTABLE</code>.
     */
    public final TableField<Record, String> IS_GRANTABLE = createField(DSL.name("IS_GRANTABLE"), org.jooq.impl.SQLDataType.VARCHAR(3), this, "");

    /**
     * Create a <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS</code> table reference
     */
    public RoleAuthorizationDescriptors() {
        this(DSL.name("ROLE_AUTHORIZATION_DESCRIPTORS"), null);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS</code> table reference
     */
    public RoleAuthorizationDescriptors(String alias) {
        this(DSL.name(alias), ROLE_AUTHORIZATION_DESCRIPTORS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.ROLE_AUTHORIZATION_DESCRIPTORS</code> table reference
     */
    public RoleAuthorizationDescriptors(Name alias) {
        this(alias, ROLE_AUTHORIZATION_DESCRIPTORS);
    }

    private RoleAuthorizationDescriptors(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private RoleAuthorizationDescriptors(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each role granted directly to a grantee"), TableOptions.table());
    }

    public <O extends Record> RoleAuthorizationDescriptors(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, ROLE_AUTHORIZATION_DESCRIPTORS);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public RoleAuthorizationDescriptors as(String alias) {
        return new RoleAuthorizationDescriptors(DSL.name(alias), this);
    }

    @Override
    public RoleAuthorizationDescriptors as(Name alias) {
        return new RoleAuthorizationDescriptors(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RoleAuthorizationDescriptors rename(String name) {
        return new RoleAuthorizationDescriptors(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RoleAuthorizationDescriptors rename(Name name) {
        return new RoleAuthorizationDescriptors(name, null);
    }
}