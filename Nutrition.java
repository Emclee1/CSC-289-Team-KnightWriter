public class Nutrition
{
  private final int servingSize, servings, calories, fat, sodium;
	
  public static class Builder {
    // required
    private int servingSize, servings;

    // optional, initialized to default values
    private int calories = 0;
    private int fat = 0;
    private int sodium = 0;
 		
    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    public Builder calories(int val) {
      calories = val;
      return this;
    }
		
    public Builder fat(int val) {
      fat = val;
      return this;
    }
		
    public Builder sodium(int val) {
      sodium = val;
      return this;
    }
		
    public Nutrition build() {
      return new Nutrition(this);
    }	
  }

  public Nutrition(Builder builder) {
    this.servingSize  = builder.servingSize;
    this.servings     = builder.servings;
    this.calories     = builder.calories;
    this.fat          = builder.fat;
    this.sodium       = builder.sodium;
  }

  public int getCalories() {
    return calories;
  }
  public int getFat() {
    return fat;
  }
  public int getSodium() {
    return sodium;
  }
}