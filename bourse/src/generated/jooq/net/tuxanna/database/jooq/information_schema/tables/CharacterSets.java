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
 * one row for each character set name
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CharacterSets extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.CHARACTER_SETS</code>
     */
    public static final CharacterSets CHARACTER_SETS = new CharacterSets();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column
     * <code>INFORMATION_SCHEMA.CHARACTER_SETS.CHARACTER_SET_CATALOG</code>.
     */
    public final TableField<Record, String> CHARACTER_SET_CATALOG = createField(DSL.name("CHARACTER_SET_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.CHARACTER_SETS.CHARACTER_SET_SCHEMA</code>.
     */
    public final TableField<Record, String> CHARACTER_SET_SCHEMA = createField(DSL.name("CHARACTER_SET_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.CHARACTER_SETS.CHARACTER_SET_NAME</code>.
     */
    public final TableField<Record, String> CHARACTER_SET_NAME = createField(DSL.name("CHARACTER_SET_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.CHARACTER_SETS.CHARACTER_REPERTOIRE</code>.
     */
    public final TableField<Record, String> CHARACTER_REPERTOIRE = createField(DSL.name("CHARACTER_REPERTOIRE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.CHARACTER_SETS.FORM_OF_USE</code>.
     */
    public final TableField<Record, String> FORM_OF_USE = createField(DSL.name("FORM_OF_USE"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.CHARACTER_SETS.DEFAULT_COLLATE_CATALOG</code>.
     */
    public final TableField<Record, String> DEFAULT_COLLATE_CATALOG = createField(DSL.name("DEFAULT_COLLATE_CATALOG"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.CHARACTER_SETS.DEFAULT_COLLATE_SCHEMA</code>.
     */
    public final TableField<Record, String> DEFAULT_COLLATE_SCHEMA = createField(DSL.name("DEFAULT_COLLATE_SCHEMA"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    /**
     * The column
     * <code>INFORMATION_SCHEMA.CHARACTER_SETS.DEFAULT_COLLATE_NAME</code>.
     */
    public final TableField<Record, String> DEFAULT_COLLATE_NAME = createField(DSL.name("DEFAULT_COLLATE_NAME"), net.tuxanna.database.jooq.information_schema.Domains.SQL_IDENTIFIER.getDataType(), this, "");

    private CharacterSets(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private CharacterSets(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("one row for each character set name"), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.CHARACTER_SETS</code> table
     * reference
     */
    public CharacterSets(String alias) {
        this(DSL.name(alias), CHARACTER_SETS);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.CHARACTER_SETS</code> table
     * reference
     */
    public CharacterSets(Name alias) {
        this(alias, CHARACTER_SETS);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.CHARACTER_SETS</code> table reference
     */
    public CharacterSets() {
        this(DSL.name("CHARACTER_SETS"), null);
    }

    public <O extends Record> CharacterSets(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, CHARACTER_SETS);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public CharacterSets as(String alias) {
        return new CharacterSets(DSL.name(alias), this);
    }

    @Override
    public CharacterSets as(Name alias) {
        return new CharacterSets(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public CharacterSets rename(String name) {
        return new CharacterSets(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public CharacterSets rename(Name name) {
        return new CharacterSets(name, null);
    }
}
