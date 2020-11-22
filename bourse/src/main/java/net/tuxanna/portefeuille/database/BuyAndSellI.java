package net.tuxanna.portefeuille.database;

import java.util.Date;
import java.util.List;

public interface BuyAndSellI
{
	boolean loadValidSellOrders(List<ShareOrderI> listOrders, Date date);
	boolean loadValidBuyOrders(List<ShareOrderI> list, Date date);
	boolean updateSellOrder(ShareOrderI sellOrder);
	boolean updateBuyOrder(ShareOrderI buyOrder);
}
