package net.tuxanna.portefeuille.database;

import org.jooq.Condition;

import net.tuxanna.database.jooq.public_.tables.Portfolio;

public class ConditionPortfolioSharesMatchShareId implements ConditionPortfolioI
{
	private Integer shareId;
	
	public ConditionPortfolioSharesMatchShareId(Integer shareIdToSearchFor)
	{
		shareId=shareIdToSearchFor;
	}
	
	@Override
	public Condition getCondition()
	{
		return Portfolio.PORTFOLIO.IDSHARE.eq(shareId);

	}

}
