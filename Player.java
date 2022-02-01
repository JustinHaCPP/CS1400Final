public class Player
{
    private double foodSupply, waterSupply, staminaSupply;

    // Getters
    public double getFoodSupply() {return foodSupply;}
    public double getWaterSupply() {return waterSupply;}
    public double getStaminaSupply() {return staminaSupply;}
    // Setters
    public void setFoodSupply(double foodSupply) {this.foodSupply = foodSupply;}
    public void setWaterSupply(double waterSupply) {this.waterSupply = waterSupply;}
    public void setStaminaSupply(double staminaSupply) {this.staminaSupply = staminaSupply;}
    // three methods
    public double foodFactor() {return 1.0;}
    public double waterFactor() {return 1.0;}
    public double staminaFactor() {return 1.0;}
    // enter method
    public boolean enter(Terrain terrain)
    {
        double food = terrain.foodCost();
        if (food > 0.0)
        {
            food *= foodFactor();
        }
        foodSupply -= food;
        if (foodSupply > 20.0)
        {
            foodSupply = 20.0;
        }
        if (foodSupply < 0.0)
        {
            return false; // The player has 'died'
        }

        double water = terrain.waterCost();
        if (water > 0.0)
        {
            water *= waterFactor();
        }
        waterSupply -= water;
        if (waterSupply > 20.0)
        {
            waterSupply = 20.0;
        }
        if (waterSupply < 0.0)
        {
            return false; // The player has 'died'
        }

        double stamina = terrain.staminaCost();
        if (stamina > 0.0)
        {
            stamina *= staminaFactor();
        }
        staminaSupply -= stamina;
        if (staminaSupply > 20.0)
        {
            staminaSupply = 20.0;
        }
        if (staminaSupply < 0.0)
        {
            return false; // The player has 'died'
        }

        return true;
    }
}
