package net.tuxanna.portefeuille.businessLogic.mobileAverage;

public class MobileAverageExponential
{

	//https://www.trading-attitude.com/moyenne-mobile-trading-simple-efficace
	// MME(t)= (α × Prix actuel) + ((1-α) × MME(t-1))
	// où α représente le degré de décroissance du poids de chaque valeur.

	// Il est compris entre 0 et 1 et dépend du nombre de période qu’on utilise. Ainsi :
	// α = 2/(N+1)
	// oùN est le nombre de période qu’on utilise pour calculer la constante de lissage α.
	public MobileAverageExponential()
	{
		// TODO Auto-generated constructor stub
	}

}
