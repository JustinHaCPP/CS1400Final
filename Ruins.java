public class Ruins extends Terrain
{
    @Override
    public double foodCost()
    {
        return -10;
    }

    @Override
    public double waterCost()
    {
        return -10;
    }

    @Override
    public String toString()
    {
        return "R";
    }
}
