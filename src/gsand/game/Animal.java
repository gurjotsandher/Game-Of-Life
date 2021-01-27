package gsand.game;

public abstract class Animal extends Lifeform
{
    

    public final static int REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_CARNIVORE = 3;
    public final static int REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_CARNIVORE = 1;
    public final static int REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_CARNIVORE = 2;

    public final static int REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_HERBIVORE = 2;
    public final static int REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_HERBIVORE = 1;
    public final static int REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_HERBIVORE = 2;
    
    public final static int REQUIRED_AMOUNT_OF_FREE_SURROUNDING_CELLS_FOR_PROCREATION_OMNIVORE = 3;
    public final static int REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_PARTNERS_FOR_PROCREATION_OMNIVORE = 1;
    public final static int REQUIRED_MINIMUM_AMOUNT_OF_SURROUNDING_FOOD_FOR_PROCREATION_OMNIVORE = 1;

    public final static int ALLOWED_COUNT_TURNS_WITHOUT_FOOD_CARNIVORE = 5;
    public final static int ALLOWED_COUNT_TURNS_WITHOUT_FOOD_HERBIVORE = 5;
    public final static int ALLOWED_COUNT_TURNS_WITHOUT_FOOD_OMNIVORE = 5;
    
    private Cell previousCell;

    /**
     * Initializes a new animal.
     * You may give it a name.
     * 
     * @param String name
     */
    public Animal(String name) 
    {
        
        super(name);
        
    }

    /**
     * Standard move method for all animals.
     * Can be overridden for special cases.
     * 
     * Will also check if the animal landed on its food source and increment the count of turns passed without food.
     * If so, the food source will be consumed by this.consumeFoodOnCell(Cell cell);
     * 
     * @return void
     */
    public void move()
    {
                
        if(!this.hasFreeCellInScopeForType(this.getClass()))
        {

            // This animal cannot move, all cells in the animal's scope are blocked.

            return;
            
        }
                        
        Cell[] surroundingCells = this.getCell().getSurroundingCells();
        
        int randomFieldIndex = RandomGenerator.nextNumber(surroundingCells.length - 1);
        
        Cell targetCell = surroundingCells[randomFieldIndex];
                
        if(targetCell == null || targetCell.hasLifeform(this.getClass()))
        {
            
            // Only one of each types of lifeforms is allowed on a single cell.
            
            try
            {
                
                this.move();
                
            }    
            catch(StackOverflowError e)
            {
                
            }
            
            return;
            
        }
        
        this.getCell().removeLifeform(this);
        
        this.previousCell = this.getCell();
        
        targetCell.addLifeform(this);
        
    }
    
    /**
     * Consumes th animal's food on the current cell.
     * 
     * @return void
     */
    public void nourish()
    {

        if(!this.consumeFoodOnCell(this.getCell()))
        {
            
            ++this.countTurnsWithoutFood;
            
        }
        
        this.checkIfStarved();
        
    }
    
    /**
     * Checks if the animal has starved:
     * 
     * If the animal passed the configured amount of turns without landing on a field 
     * with the configured food source, this will return true.
     * 
     * The animal will then die().
     * 
     * @return void
     */
    protected void checkIfStarved()
    {
        
        if(this.countTurnsWithoutFood > this.allowedNumberOfTurnsWithoutFood)
        {
            
            this.die();
            
        }
        
    }
    
    /**
     * Removes the animal from the game.
     * 
     * @return void
     */
    protected void die()
    {
        
        App.getGameObject().removeLifeformFromWorld(this);
                
    }
    
}
