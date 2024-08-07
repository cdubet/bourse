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
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Portfolio extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

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
    public final TableField<Record, Integer> IDPORTFOLIO = createField(DSL.name("IDPORTFOLIO"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.IDSHARE</code>.
     */
    public final TableField<Record, Integer> IDSHARE = createField(DSL.name("IDSHARE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.IDACCOUNT</code>.
     */
    public final TableField<Record, Integer> IDACCOUNT = createField(DSL.name("IDACCOUNT"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.QTE</code>.
     */
    public final TableField<Record, Double> QTE = createField(DSL.name("QTE"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.UNITPRICE</code>.
     */
    public final TableField<Record, Double> UNITPRICE = createField(DSL.name("UNITPRICE"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.PORTFOLIO.USEFORSUMMARY</code>.
     */
    public final TableField<Record, Integer> USEFORSUMMARY = createField(DSL.name("USEFORSUMMARY"), SQLDataType.INTEGER.nullable(false), this, "");

    private Portfolio(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Portfolio(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
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

    /**
     * Create a <code>PUBLIC.PORTFOLIO</code> table reference
     */
    public Portfolio() {
        this(DSL.name("PORTFOLIO"), null);
    }

    public <O extends Record> Portfolio(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, PORTFOLIO);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<Record, Integer> getIdentity() {
        return (Identity<Record, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYS_PK_10122;
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.asList(Keys.SYS_FK_10124, Keys.SYS_FK_10125);
    }

    private transient Shares _shares;
    private transient Account _account;

    /**
     * Get the implicit join path to the <code>PUBLIC.SHARES</code> table.
     */
    public Shares shares() {
        if (_shares == null)
            _shares = new Shares(this, Keys.SYS_FK_10124);

        return _shares;
    }

    /**
     * Get the implicit join path to the <code>PUBLIC.ACCOUNT</code> table.
     */
    public Account account() {
        if (_account == null)
            _account = new Account(this, Keys.SYS_FK_10125);

        return _account;
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
