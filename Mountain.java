public class Mountain extends Terrain
{
    @Override
    public double foodCost()
    {
        return 4;
    }

    @Override
    public double waterCost()
    {
        return 4;
    }

    @Override
    public double staminaCost()
    {
        return 4;
    }

    @Override
    public String toString()
    {
        return "M";
    }
}
