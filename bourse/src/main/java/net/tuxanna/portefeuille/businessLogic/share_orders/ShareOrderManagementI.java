package net.tuxanna.portefeuille.businessLogic.share_orders;

import java.util.Date;
import java.util.List;


import net.tuxanna.portefeuille.database.DatabaseI;
import net.tuxanna.portefeuille.util.ReportI;

public interface ShareOrderManagementI
{

	void setDatabase(DatabaseI database);

	boolean update(Date now);

	void addResultToNotify(List<ReportI> listReportToPrint);

}