
public class Calculate
{
	private double[] array;
	private double n = 0.0;
	
	public Calculate() 
	{
		
		
	}
	
	public void calculate(double[] list)
	{
		this.array = list;
		int i;
		for(i = 0; i < array.length; i++)
		{
			n = n + this.array[i];
		}
		
		n = n / i;
		
	}
	
	@Override
	public String toString()
	{
		String str = "";
		
		str = str + n;
		
		return str;
		
	}
}
