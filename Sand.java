public class Sand extends Terrain
{
    @Override
    public double waterCost()
    {
        return 1.5;
    }

    @Override
    public double staminaCost()
    {
        return 1.5;
    }

    @Override
    public String toString()
    {
        return "S";
    }
}