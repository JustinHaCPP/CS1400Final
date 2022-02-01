public class ComplexBrain extends Brain
{
    private char pastMove;

    public ComplexBrain(int x, int y, Player player, Terrain[][] map)
    {
        setX(x);
        setY(y);
        setPlayer(player);
        setMap(map);
    }

    @Override
    public String move()
    {
        double[] northSupplies = {0,0}, eastSupplies = {0,0}, southSupplies = {0,0};


        // Calculate North
        if (getY()-1 > 0)
        {
            northSupplies = calculateSupplies(getMap()[getY() - 1][getX()]);
        }
        else
        {
            northSupplies[0] = 0;
            northSupplies[1] = 0;
        }

        // Calculate East
        if (getX()+1 < 49)
        {
            eastSupplies = calculateSupplies(getMap()[getY()][getX() + 1]);
        }
        else
        {
            eastSupplies[0] = 0;
            eastSupplies[1] = 0;
        }

        // Calculate South
        if (getY()+1 < 9)
        {
            southSupplies = calculateSupplies(getMap()[getY() + 1][getX()]);
        }
        else
        {
            southSupplies[0] = 0;
            southSupplies[1] = 0;
        }

        // Compare supplies
        if (eastSupplies[0] == 1 && eastSupplies[1] >= northSupplies[1] && eastSupplies[1] >= southSupplies[1])
        {
            setX(getX() + 1);
            pastMove = 'E';
            return "E";
        }
        else if (northSupplies[0] == 1 && pastMove != 'S' && northSupplies[1] >= eastSupplies[1] && northSupplies[1] >= southSupplies[1])
        {
            setY(getY() - 1);
            pastMove = 'N';
            return "N";
        }
        else if (southSupplies[0] == 1 && pastMove != 'N' && southSupplies[1] >= northSupplies[1] && southSupplies[1] >= eastSupplies[1])
        {
            setY(getY() + 1);
            pastMove = 'S';
            return "S";
        }
        else
        {
            setX(getX() + 1);
            pastMove = 'E';
            return "E";
        }

    }

    public double[] calculateSupplies(Terrain terrain)
    {
        double[] array = new double[2];
        array[0] = 1;

        double food = terrain.foodCost();
        double foodSupply = getPlayer().getFoodSupply();
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

        double water = terrain.waterCost();
        double waterSupply = getPlayer().getWaterSupply();
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

        double stamina = terrain.staminaCost();
        double staminaSupply = getPlayer().getStaminaSupply();
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
        array[1] += staminaSupply * getPlayer().waterFactor();

        return array;
    }
}
