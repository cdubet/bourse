/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.public_.tables;


import java.util.Arrays;
import java.util.List;

import net.tuxanna.database.jooq.public_.Keys;
import net.tuxanna.database.jooq.public_.Public;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Portfolio extends TableImpl<Record> {

    private static final long serialVersionUID = 585079122;

    /**
     * The reference instance of <code>PUBLIC.PORTFOLIO</code>
     */
    public static final Portfolio PORTFOLIO = new Portfolio();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>PUBLIC.PORTFOLIO.IDPORTFOLIO</code>.
     */
    public final TableField<Record, Integer> IDPORTFOLIO = createField(DSL.name("IDPORTFOLIO"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.IDSHARE</code>.
     */
    public final TableField<Record, Integer> IDSHARE = createField(DSL.name("IDSHARE"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.IDACCOUNT</code>.
     */
    public final TableField<Record, Integer> IDACCOUNT = createField(DSL.name("IDACCOUNT"), org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.QTE</code>.
     */
    public final TableField<Record, Double> QTE = createField(DSL.name("QTE"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.UNITPRICE</code>.
     */
    public final TableField<Record, Double> UNITPRICE = createField(DSL.name("UNITPRICE"), org.jooq.impl.SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.USEFORSUMMARY</code>.
     */
    public final TableField<Record, Integer> USEFORSUMMARY = createField(DSL.name("USEFORSUMMARY"), org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.PORTFOLIO</code> table reference
     */
    public Portfolio() {
        this(DSL.name("PORTFOLIO"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.PORTFOLIO</code> table reference
     */
    public Portfolio(String alias) {
        this(DSL.name(alias), PORTFOLIO);
    }

    /**
     * Create an aliased <code>PUBLIC.PORTFOLIO</code> table reference
     */
    public Portfolio(Name alias) {
        this(alias, PORTFOLIO);
    }

    private Portfolio(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Portfolio(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Portfolio(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, PORTFOLIO);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<Record, Integer> getIdentity() {
        return Keys.IDENTITY_PORTFOLIO;
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYS_PK_10121;
    }

    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.SYS_PK_10121);
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Record, ?>>asList(Keys.SYS_FK_10123, Keys.SYS_FK_10124);
    }

    public Shares shares() {
        return new Shares(this, Keys.SYS_FK_10123);
    }

    public Account account() {
        return new Account(this, Keys.SYS_FK_10124);
    }

    @Override
    public Portfolio as(String alias) {
        return new Portfolio(DSL.name(alias), this);
    }

    @Override
    public Portfolio as(Name alias) {
        return new Portfolio(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Portfolio rename(String name) {
        return new Portfolio(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Portfolio rename(Name name) {
        return new Portfolio(name, null);
    }
}