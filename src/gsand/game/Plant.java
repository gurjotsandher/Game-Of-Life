package gsand.game;

/**
 * Plant lifeform
 * 
 * @author gsand
 * @version 1.0
 */
abstract public class Plant extends Lifeform 
{

    public final static int REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_SEEDING_GRASS = 3;
    public final static int REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_SEEDING_GRASS = 2;
    
    /**
     * Initializes a new plant.
     * 
     * @param String name
     */
    public Plant(String name) 
    {
        
        super(name);
        
    }
    
    /**
     * Plants do not nourish on anything in the current version of the game.
     * 
     * @return void
     */
    public void nourish()
    {
        
        return;
        
    }
        
}
