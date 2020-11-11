package net.tuxanna.portefeuille.util;

import java.util.List;

import net.tuxanna.portefeuille.business_logic.util.ProblemNotification;

public interface PortfolioNotifierI
{
	boolean notifyUser(List<ReportI> message);
	boolean notifyErrors(List<ProblemNotification> err);
}