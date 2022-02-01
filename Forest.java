public class Forest extends Terrain
{
    @Override
    public double foodCost()
    {
        return -10;
    }

    @Override
    public double staminaCost()
    {
        return 2;
    }

    @Override
    public String toString()
    {
        return "F";
    }
}
