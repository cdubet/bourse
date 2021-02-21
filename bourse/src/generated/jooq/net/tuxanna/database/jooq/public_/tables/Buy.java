/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.public_.tables;


import java.time.LocalDateTime;
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
public class Buy extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>PUBLIC.BUY</code>
     */
    public static final Buy BUY = new Buy();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>PUBLIC.BUY.IDBUY</code>.
     */
    public final TableField<Record, Integer> IDBUY = createField(DSL.name("IDBUY"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.BUY.IDSHARE</code>.
     */
    public final TableField<Record, Integer> IDSHARE = createField(DSL.name("IDSHARE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.BUY.IDACCOUNT</code>.
     */
    public final TableField<Record, Integer> IDACCOUNT = createField(DSL.name("IDACCOUNT"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>PUBLIC.BUY.QTE</code>.
     */
    public final TableField<Record, Double> QTE = createField(DSL.name("QTE"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.BUY.UNITPRICEREQUESTED</code>.
     */
    public final TableField<Record, Double> UNITPRICEREQUESTED = createField(DSL.name("UNITPRICEREQUESTED"), SQLDataType.DOUBLE.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.BUY.UNITPRICEBOUGHT</code>.
     */
    public final TableField<Record, Double> UNITPRICEBOUGHT = createField(DSL.name("UNITPRICEBOUGHT"), SQLDataType.DOUBLE, this, "");

    /**
     * The column <code>PUBLIC.BUY.STATE</code>.
     */
    public final TableField<Record, Integer> STATE = createField(DSL.name("STATE"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.BUY.DATEEXPIRATION</code>.
     */
    public final TableField<Record, LocalDateTime> DATEEXPIRATION = createField(DSL.name("DATEEXPIRATION"), SQLDataType.LOCALDATETIME(6).nullable(false), this, "");

    private Buy(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Buy(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>PUBLIC.BUY</code> table reference
     */
    public Buy(String alias) {
        this(DSL.name(alias), BUY);
    }

    /**
     * Create an aliased <code>PUBLIC.BUY</code> table reference
     */
    public Buy(Name alias) {
        this(alias, BUY);
    }

    /**
     * Create a <code>PUBLIC.BUY</code> table reference
     */
    public Buy() {
        this(DSL.name("BUY"), null);
    }

    public <O extends Record> Buy(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, BUY);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<Record, Integer> getIdentity() {
        return (Identity<Record, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYS_PK_10151;
    }

    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.SYS_PK_10151);
    }

    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Record, ?>>asList(Keys.SYS_FK_10156, Keys.SYS_FK_10157);
    }

    private transient Shares _shares;
    private transient Account _account;

    public Shares shares() {
        if (_shares == null)
            _shares = new Shares(this, Keys.SYS_FK_10156);

        return _shares;
    }

    public Account account() {
        if (_account == null)
            _account = new Account(this, Keys.SYS_FK_10157);

        return _account;
    }

    @Override
    public Buy as(String alias) {
        return new Buy(DSL.name(alias), this);
    }

    @Override
    public Buy as(Name alias) {
        return new Buy(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Buy rename(String name) {
        return new Buy(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Buy rename(Name name) {
        return new Buy(name, null);
    }
}
