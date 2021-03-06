/*
 * This file is generated by jOOQ.
 */
package net.tuxanna.database.jooq.system_lobs;


import net.tuxanna.database.jooq.system_lobs.routines.AllocBlocks;
import net.tuxanna.database.jooq.system_lobs.routines.AllocSingleBlock;
import net.tuxanna.database.jooq.system_lobs.routines.ConvertBlock;
import net.tuxanna.database.jooq.system_lobs.routines.CreateEmptyBlock;
import net.tuxanna.database.jooq.system_lobs.routines.DeleteBlocks;
import net.tuxanna.database.jooq.system_lobs.routines.DeleteLob;
import net.tuxanna.database.jooq.system_lobs.routines.DeleteUnused;
import net.tuxanna.database.jooq.system_lobs.routines.DeleteUnusedLobs;
import net.tuxanna.database.jooq.system_lobs.routines.DivideBlock;
import net.tuxanna.database.jooq.system_lobs.routines.MergeEmptyBlocks;

import org.jooq.Configuration;


/**
 * Convenience access to all stored procedures and functions in SYSTEM_LOBS.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Routines {

    /**
     * Call <code>SYSTEM_LOBS.ALLOC_BLOCKS</code>
     */
    public static void allocBlocks(
          Configuration configuration
        , Integer bCount
        , Integer bOffset
        , Long lId
    ) {
        AllocBlocks p = new AllocBlocks();
        p.setBCount(bCount);
        p.setBOffset(bOffset);
        p.setLId(lId);

        p.execute(configuration);
    }

    /**
     * Call <code>SYSTEM_LOBS.ALLOC_SINGLE_BLOCK</code>
     */
    public static void allocSingleBlock(
          Configuration configuration
        , Integer bCount
        , Integer bOffset
        , Long lId
    ) {
        AllocSingleBlock p = new AllocSingleBlock();
        p.setBCount(bCount);
        p.setBOffset(bOffset);
        p.setLId(lId);

        p.execute(configuration);
    }

    /**
     * Call <code>SYSTEM_LOBS.CONVERT_BLOCK</code>
     */
    public static void convertBlock(
          Configuration configuration
        , Integer bAddr
        , Integer bCount
        , Integer bOffset
        , Long lId
    ) {
        ConvertBlock p = new ConvertBlock();
        p.setBAddr(bAddr);
        p.setBCount(bCount);
        p.setBOffset(bOffset);
        p.setLId(lId);

        p.execute(configuration);
    }

    /**
     * Call <code>SYSTEM_LOBS.CREATE_EMPTY_BLOCK</code>
     */
    public static Integer createEmptyBlock(
          Configuration configuration
        , Integer bAddr
        , Integer bCount
    ) {
        CreateEmptyBlock p = new CreateEmptyBlock();
        p.setBAddr(bAddr);
        p.setBCount(bCount);

        p.execute(configuration);
        return p.getBAddr();
    }

    /**
     * Call <code>SYSTEM_LOBS.DELETE_BLOCKS</code>
     */
    public static void deleteBlocks(
          Configuration configuration
        , Long lId
        , Integer bOffset
        , Integer bLimit
        , Long txId
    ) {
        DeleteBlocks p = new DeleteBlocks();
        p.setLId(lId);
        p.setBOffset(bOffset);
        p.setBLimit(bLimit);
        p.setTxId(txId);

        p.execute(configuration);
    }

    /**
     * Call <code>SYSTEM_LOBS.DELETE_LOB</code>
     */
    public static void deleteLob(
          Configuration configuration
        , Long lId
        , Long txId
    ) {
        DeleteLob p = new DeleteLob();
        p.setLId(lId);
        p.setTxId(txId);

        p.execute(configuration);
    }

    /**
     * Call <code>SYSTEM_LOBS.DELETE_UNUSED</code>
     */
    public static void deleteUnused(
          Configuration configuration
        , Long[] lIds
    ) {
        DeleteUnused p = new DeleteUnused();
        p.setLIds(lIds);

        p.execute(configuration);
    }

    /**
     * Call <code>SYSTEM_LOBS.DELETE_UNUSED_LOBS</code>
     */
    public static Integer deleteUnusedLobs(
          Configuration configuration
        , Long limitId
    ) {
        DeleteUnusedLobs p = new DeleteUnusedLobs();
        p.setLimitId(limitId);

        p.execute(configuration);
        return p.getTotalCount();
    }

    /**
     * Call <code>SYSTEM_LOBS.DIVIDE_BLOCK</code>
     */
    public static void divideBlock(
          Configuration configuration
        , Integer bOffset
        , Long lId
    ) {
        DivideBlock p = new DivideBlock();
        p.setBOffset(bOffset);
        p.setLId(lId);

        p.execute(configuration);
    }

    /**
     * Call <code>SYSTEM_LOBS.MERGE_EMPTY_BLOCKS</code>
     */
    public static void mergeEmptyBlocks(
          Configuration configuration
    ) {
        MergeEmptyBlocks p = new MergeEmptyBlocks();

        p.execute(configuration);
    }
}
