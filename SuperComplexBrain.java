public class SuperComplexBrain extends Brain
{
    public SuperComplexBrain(int x, int y, Player player, Terrain[][] map)
    {
        setX(x);
        setY(y);
        setPlayer(player);
        setMap(map);
    }

    @Override
    public String move()
    {
        double[] eastSupplies = {0,0,0,0,0}, northEastSupplies = {0,0,0,0,0}, eastNorthSupplies = {0,0,0,0,0}, southEastSupplies = {0,0,0,0,0}, eastSouthSupplies = {0,0,0,0,0};

        // Calculate East
        if (getX()+1 < 49)
        {
            double[] temp = {0,0, getPlayer().getFoodSupply(), getPlayer().getWaterSupply(), getPlayer().getStaminaSupply()};
            eastSupplies = calculateSupplies(getMap()[getY()][getX() + 1], temp);
        }
        else
        {
            eastSupplies[0] = 0;
            eastSupplies[1] = 0;
        }
        // Calculate North then East
        if (getY()-1 > 0 && getX()+1 < 49)
        {
            double[] temp = {0,0, getPlayer().getFoodSupply(), getPlayer().getWaterSupply(), getPlayer().getStaminaSupply()};
            northEastSupplies = calculateSupplies(getMap()[getY() - 1][getX()], temp);
            northEastSupplies = calculateSupplies(getMap()[getY()-1][getX() + 1], northEastSupplies);
        }
        else
        {
            northEastSupplies[0] = 0;
            northEastSupplies[1] = 0;
        }
        // Calculate East then North
        if (getY()-1 > 0 && getX()+1 < 49)
        {
            double[] temp = {0,0, getPlayer().getFoodSupply(), getPlayer().getWaterSupply(), getPlayer().getStaminaSupply()};
            eastNorthSupplies = calculateSupplies(getMap()[getY()][getX() + 1], temp);
            eastNorthSupplies = calculateSupplies(getMap()[getY() - 1][getX()+1], eastNorthSupplies);
        }
        else
        {
            eastNorthSupplies[0] = 0;
            eastNorthSupplies[1] = 0;
        }
        // Calculate South then East
        if (getY()+1 < 9 && getX()+1 < 49)
        {
            double[] temp = {0,0, getPlayer().getFoodSupply(), getPlayer().getWaterSupply(), getPlayer().getStaminaSupply()};
            southEastSupplies = calculateSupplies(getMap()[getY() + 1][getX()], temp);
            southEastSupplies = calculateSupplies(getMap()[getY()+1][getX() + 1], southEastSupplies);
        }
        else
        {
            southEastSupplies[0] = 0;
            southEastSupplies[1] = 0;
        }
        // Calculate East then South
        if (getY()+1 < 9 && getX()+1 < 49)
        {
            double[] temp = {0,0, getPlayer().getFoodSupply(), getPlayer().getWaterSupply(), getPlayer().getStaminaSupply()};
            eastSouthSupplies = calculateSupplies(getMap()[getY()][getX() + 1], temp);
            eastSouthSupplies = calculateSupplies(getMap()[getY() + 1][getX()+1], eastSouthSupplies);
        }
        else
        {
            eastSouthSupplies[0] = 0;
            eastSouthSupplies[1] = 0;
        }

        // Compare supplies
        if (eastSupplies[0] == 1 && eastSupplies[1] >= northEastSupplies[1] && eastSupplies[1] >= eastNorthSupplies[1] && eastSupplies[1] >= southEastSupplies[1] && eastSupplies[1] >= eastSouthSupplies[1])
        {
            setX(getX() + 1);
            return "E";
        }
        else if (northEastSupplies[0] == 1 && northEastSupplies[1] >= eastSupplies[1] && northEastSupplies[1] >= eastNorthSupplies[1] && northEastSupplies[1] >= southEastSupplies[1] && northEastSupplies[1] >= eastSouthSupplies[1])
        {
            setY(getY() -1);
            setX(getX() + 1);
            return "NE";
        }
        else if (eastNorthSupplies[0] == 1 && eastNorthSupplies[1] >= eastSupplies[1] && eastNorthSupplies[1] >= northEastSupplies[1] && eastNorthSupplies[1] >= southEastSupplies[1] && eastNorthSupplies[1] >= eastSouthSupplies[1])
        {
            setY(getY() -1);
            setX(getX() + 1);
            return "EN";
        }
        else if (southEastSupplies[0] == 1 && southEastSupplies[1] >= eastSupplies[1] && southEastSupplies[1] >= northEastSupplies[1] && southEastSupplies[1] >= eastNorthSupplies[1] && southEastSupplies[1] >= eastSouthSupplies[1])
        {
            setY(getY() +1);
            setX(getX() + 1);
            return "SE";
        }
        else if (eastSouthSupplies[0] == 1 && eastSouthSupplies[1] >= eastSupplies[1] && eastSouthSupplies[1] >= northEastSupplies[1] && eastSouthSupplies[1] >= eastNorthSupplies[1] && eastSouthSupplies[1] >= southEastSupplies[1])
        {
            setY(getY() +1);
            setX(getX() + 1);
            return "ES";
        }
        else
        {
            setX(getX() + 1);
            return "E";
        }
    }

    public double[] calculateSupplies(Terrain terrain, double[] supplies)
    {
        double[] array = new double[5];
        array[0] = 1;

        double food = terrain.foodCost();
        double foodSupply = supplies[2];
        if (food > 0.0)
        {
            food *= getPlayer().foodFactor();
        }
        foodSupply -= food;
        if (foodSupply > 20.0)
        {
            foodSupply = 20.0;
        }
        if (foodSupply <= 0.0)
        {
            foodSupply = 0;
            array[0] = 0;
        }
        array[1] = foodSupply * getPlayer().foodFactor();
        array[2] = foodSupply;

        double water = terrain.waterCost();
        double waterSupply = supplies[3];
        if (water > 0.0)
        {
            water *= getPlayer().waterFactor();

        }
        waterSupply -= water;
        if (waterSupply > 20.0)
        {
            waterSupply = 20.0;
        }
        if (waterSupply <= 0.0)
        {
            waterSupply = 0;
            array[0] = 0;
        }
        array[1] += waterSupply * getPlayer().waterFactor();
        array[3] = waterSupply;

        double stamina = terrain.staminaCost();
        double staminaSupply = supplies[4];
        if (stamina > 0.0)
        {
            stamina *= getPlayer().staminaFactor();
        }
        staminaSupply -= stamina;
        if (staminaSupply > 20.0)
        {
            staminaSupply = 20.0;
        }
        if (staminaSupply <= 0.0)
        {
            staminaSupply = 0;
            array[0] = 0;
        }
        array[1] += staminaSupply * getPlayer().staminaFactor();    // Multiplies the comparison value by player factors to show which ones are more valuable.
        array[4] = staminaSupply;

        return array;
    }
}
