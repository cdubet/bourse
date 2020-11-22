package net.tuxanna.portefeuille.database;

import java.util.List;

public interface ShareI
{
	ShareDB loadShare(String shareName);
	boolean loadShares(List<ShareDB> listShares);
	boolean storeShares(List<ShareDB> listShares);
}
