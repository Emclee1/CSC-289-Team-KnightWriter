package kitt;

public class Calculate
{
	private double perServing = 0.0;
	
	public Calculate() {	}
	
	public Calculate( double tot, String totUnit, double serv, String servUnit ) 
	{
		if( totUnit.equals( "cup" ) )
		{
			switch( servUnit )
			{
				case "cup":
					calculate( tot, serv );
					break;
				case "lb":
					calculate( tot, poundsToCup( serv ) );
					break;
				case "Tbs":
					calculate( tot, tableToCup( serv ) );
					break;
				case "tsp":
					calculate( tot, teaToCup( serv ) );
					break;
				case "oz":
					calculate( tot, ozToCup( serv ) );
					break;
				default:
					System.out.printf( "Problem 1; totunit: %s  %f   servUnit: %s  %f%n", totUnit, tot, servUnit, serv );
			}
		}
		else if( totUnit.equals( "lb" ) )
		{
			switch( servUnit )
			{
				case "cup":
					calculate( tot, cupsToPound( serv ) );
					break;
				case "oz":
					calculate( tot, ozToPounds( serv ) );
					break;
				case "g":
					calculate( tot, gramsToPounds( serv ) );
					break;
				case "lb":
					calculate( tot, serv );
					break;
				default:
					System.out.printf( "Problem 2; totunit: %s  %f   servUnit: %s  %f%n", totUnit, tot, servUnit, serv );
			}
		}
		else if( totUnit.equals( "oz" ) )
		{
			switch( servUnit )
			{
				case "cup":
					calculate( tot, cupsToOz( serv ) );
					break;
				case "lb":
					calculate( tot, poundsToOz( serv ) );
					break;
				case "g":
					calculate( tot, gramsToOz( serv ) );
				case "oz":
					calculate( tot, serv );
					break;
				case "Tbs":
					calculate( tot, tableToOz( serv ) );
					break;
				case "tsp":
					calculate( tot, teaToOz( serv ) );
					break;
				default:
					System.out.printf( "Problem 3; totunit: %s  %f   servUnit: %s  %f%n", totUnit, tot, servUnit, serv );
			}
		}
		else if( totUnit.equals( "Tbs" ) )
		{
			switch( servUnit )
			{
				case "cup":
					calculate( tot, cupsToTable( serv ) );
					break;
				case "oz":
					calculate( tot, ozToTable( serv ) );
					break;
				case "Tbs":
					calculate( tot, serv );
					break;
				case "tsp":
					calculate( tot, teaToTable( serv ) );
					break;
				default:
					System.out.printf( "Problem 4; totunit: %s  %f   servUnit: %s  %f%n", totUnit, tot, servUnit, serv );
			}
		}
		else if( totUnit.equals( "tsp" ) )
		{
			switch( servUnit )
			{
				case "cup":
					calculate( tot, cupsToTea( serv ) );
					break;
				case "oz":
					calculate( tot, ozToTea( serv ) );
					break;
				case "Tbs":
					calculate( tot, tableToTea( serv ) );
					break;
				case "tsp":
					calculate( tot, serv );
					break;
				default:
					System.out.printf( "Problem 5; totunit: %s  %f   servUnit: %s  %f%n", totUnit, tot, servUnit, serv );
			}
		}
		else if( totUnit.equals( "count" ) || totUnit.equals( "unit" ) )
		{
			calculate( tot, serv );
		}
	}

	public void calculate(double item, double size)
	{
		perServing = item / size;
	}
	
	public double cupsToTable(double cups)
	{
		double tbsp = 0.0;
		
		tbsp = cups * 16;
		
		return tbsp;
	}
	
	public double cupsToTea(double cups)
	{
		double tsp = 0.0;
		
		tsp = cups * 16;
		tsp = tsp * 3;
		
		return tsp;
	}
	
	public double cupsToOz(double cups)
	{
		double oz = 0.0;
		
		oz = cups * 8;
		
		return oz;
	}
	
	public double cupsToPound(double cups)
	{
		double lb = 0.0;
		
		lb = cups / 2;
		
		return lb;
	}
	
	public double teaToCup(double tsp)
	{
		double cups = 0.0;
		
		cups = tsp / 3;
		
		cups = tsp / 16;
		
		return cups;
	}
	
	public double teaToTable(double tsp)
	{
		double tbsp = 0.0;
		
		tbsp = tsp / 3;
		
		return tbsp;
	}
	
	private double teaToOz(double tsp)
	{
		double oz = 0.0;
		
		oz = tsp / 6;
		
		return oz;
	}

	private double tableToOz(double tbsp) 
	{
		double oz = 0.0;
		
		oz = tbsp / 2;
		
		return oz;
	}
	
	public double tableToTea(double tbsp)
	{
		double tsp = 0.0;
		
		tsp = tbsp * 3;
		
		return tsp;
	}
	
	public double tableToCup(double tbsp)
	{
		double cups = 0.0;
		
		cups = tbsp / 16;
		
		return cups;
	}
	
	public double poundsToOz(double lbs)
	{
		double oz = 0.0;
		
		oz = lbs * 16;
		
		return oz;
	}
	
	public double poundsToGrams(double lbs)
	{
		double g = 0.0;
		
		g = lbs * 453.592;
		
		return g;
	}
	
	public double poundsToCup(double lbs)
	{
		double cups = 0.0;
		
		cups = lbs * 2;
		
		return cups;
	}
	
	public double ozToPounds(double oz)
	{
		double lbs = 0.0;
		
		lbs = oz / 16;
		
		return lbs;
	}
	
	public double ozToGrams(double oz)
	{
		double g = 0.0;
		
		g = oz * 28.3495;
		
		return g;
	}
	
	public double ozToCup(double oz)
	{
		double cups = 0.0;
		
		cups = oz / 8;
		
		return cups;
	}
	
	public double ozToTable(double oz)
	{
		double table = 0.0;
		
		table = oz * 2;
		
		return table;
	}
	
	private double ozToTea(double oz)
	{
		double tsp = 0.0;
		
		tsp = oz * 6;
		
		return tsp;
	}
	
	public double gramsToPounds(double g)
	{
		double lbs = 0.0;
		
		lbs = g / 453.592;
		
		return lbs;
	}
	
	public double gramsToCups(double g)
	{
		double cups = 0.0;
		
		cups = g / 226.8;
		
		return cups;
	}
	
	public double gramsToOz(double g)
	{
		double oz = 0.0;
		
		oz = g / 28.3495;
		
		return oz;
	}
	
	@Override
	public String toString()
	{
		String str = "";
		
		str = str + perServing;
		
		return str;
	}
	
	public double getServing()
	{
		return perServing;
	}
}
