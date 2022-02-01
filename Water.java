public class Water extends Terrain
{
    @Override
    public double waterCost()
    {
        return -10;
    }

    @Override
    public String toString()
    {
        return "W";
    }
}
