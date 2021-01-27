package gsand.game;

import java.util.UUID;

import javafx.scene.Group;

/**
 * Abstract class for all of the game's lifeforms
 * 
 * @author gsand
 * @version 1.0
 */
public abstract class Lifeform 
{

    public final static String LIFEFORM_CARNIVORE = "Carnivore";
    public final static String LIFEFORM_GRASS = "Grass";
    public final static String LIFEFORM_HERBIVORE = "Herbivore";
    public final static String LIFEFORM_OMNIVORE = "Omnivore";

    public final static int LIFEFORM_CARNIVORE_SPAWN_RATE = 50;
    public final static int LIFEFORM_GRASS_SPAWN_RATE = 40;
    public final static int LIFEFORM_HERBIVORE_SPAWN_RATE = 20;
    public final static int LIFEFORM_OMNIVORE_SPAWN_RATE = 55;
    
    private String name;
    private Cell cell;
    
    private UUID identifier;

    protected int requiredAmountOfFreeSurroundingCellsForProcreation = 1;
    protected int requiredMinimumAmountOfSurroundingPartnersForProcreation = 1;
    protected int requiredMinimumAmountOfSurroundingFoodForProcreation = 0;

    protected Class<?>[] food = {};
    
    protected int allowedNumberOfTurnsWithoutFood;
    protected int countTurnsWithoutFood = 0;
            
    /**
     * Initializes a new lifeform.
     * A unique identifier for the new lifeform will be generated automatically.
     * 
     * @param Color color
     * @param String imgPath
     */
    public Lifeform(String name)
    {

        this.name = name;
        this.identifier = UUID.randomUUID();
        
    }

    /**
     * Retrieves the number of turns this animal has passed without having found food.
     * 
     * @return int
     */
    protected int getTurnsWithoutFood()
    {
        
        return this.countTurnsWithoutFood;
        
    }

    /**
     * Consumes the food on the specified cell.
     * The food on the cell will be removed from the game.
     * 
     * @param Cell cell
     */
    protected boolean consumeFoodOnCell(Cell cell)
    {
        
        for(int i = 0; i < this.food.length; ++i)
        {

            if(cell.hasLifeform(this.food[i]))
            {
                
                Lifeform food = cell.getLifeform(this.food[i]);
                
                if(App.getGameObject().removeLifeformFromWorld(food))
                {

                    this.countTurnsWithoutFood = 0;
                    
                    return true;
                    
                }
                
            }
            
        }
        
        return false;
        
    }
        
    /**
     * Retrieve this lifeform's unique identifier.
     * 
     * @return UUID
     */
    public UUID getIdentifier()
    {
        
        return this.identifier;
        
    }
    
    /**
     * Compares two Lifeform object's identifiers and checks if they're the same.
     * 
     * @param Lifeform lifeform
     * @return boolean
     */
    public boolean hasSameIdentifier(Lifeform lifeform)
    {
        
        if(this.getIdentifier().equals(lifeform.getIdentifier()))
        {
            
            return true;
            
        }
        
        return false;
        
    }
        
    /**
     * Retrieves the name of the lifeform.
     * 
     * @return String
     */
    public String getName()
    {
        
        return this.name;
        
    }

    /**
     * Sets this lifeform's cell.
     * 
     * @param Cell cell
     * @return void
     */
    public void setCell(Cell cell)
    {
        
        this.cell = cell;
        
    }

    /**
     * Sets this lifeform's cell.
     * Alias of setCell(Cell cell).
     * 
     * @param Cell cell
     * @return void
     */
    public void Cell(Cell cell)
    {
        
        this.setCell(cell);
        
    }

    /**
     * Retrieves this lifeform's cell.
     * 
     * @return Cell
     */
    public Cell getCell() 
    {
                
        return this.cell;
        
    }
        
    /**
     * Checks if there is at least one free cell for the lifeform of the specified type
     * within the scope of this lifeform's current cell.
     * 
     * @param Class<?> type
     * @return boolean
     */
    protected boolean hasFreeCellInScopeForType(Class<?> type)
    {

        Cell thisCell = this.getCell();
        
        if(thisCell == null)
        {
            
            return false;
            
        }
        
        Cell[] surroundingCells = this.getCell().getSurroundingCells();
        
        for(int i = 0; i < surroundingCells.length; ++i)
        {
            
            Cell currentCell = surroundingCells[i];

            if(currentCell != null && currentCell.hasSpaceForLifeformType(type))
            {
                
                return true;
                
            }
            
        }
        
        return false;
        
    }
    
    /**
     * Retrieves all lifeforms of the same type as this lifeform
     * from the cells directly surrounding this lifeform's cell.
     * 
     * @return Lifeform[]
     */
    protected Lifeform[] getSurroundingPartners()
    {
        
        Cell[] surroundingCells = this.getCell().getSurroundingCells();
        Lifeform[] unsortedPartners = new Lifeform[surroundingCells.length];
        
        int countSurroundingPartners = 0;
        
        for(int i = 0; i < surroundingCells.length; ++i)
        {
            
            Cell surroundingCell = surroundingCells[i];
            
            if(surroundingCell != null && surroundingCell.hasLifeform(this.getClass()))
            {
                
                unsortedPartners[countSurroundingPartners] = surroundingCell.getLifeform(this.getClass());
                
                ++countSurroundingPartners;
                
            }
            
        }
        
        Lifeform[] partners = new Lifeform[countSurroundingPartners];
        
        for(int i = 0; i < countSurroundingPartners; ++i)
        {
            
            partners[i] = unsortedPartners[i];
            
        }
        
        return partners;
        
    }
    
    /**
     * Retrieves all lifeforms of the specified food types for this lifeform
     * from the cells directly surrounding this lifeform's cell.
     * 
     * @return Lifeform[]
     */
    protected Lifeform[] getSurroundingFood()
    {
        
        Cell[] surroundingCells = this.getCell().getSurroundingCells();
        Lifeform[] unsortedFood = new Lifeform[surroundingCells.length * (this.food.length > 0 ? this.food.length : 1)];
        
        int countSurroundingFood = 0;
        
        for(int i = 0; i < surroundingCells.length; ++i)
        {
            
            Cell surroundingCell = surroundingCells[i];
            
            for(int j = 0; j < this.food.length; ++j)
            {

                if(surroundingCell != null && surroundingCell.hasLifeform(this.food[j]))
                {
                    
                    unsortedFood[countSurroundingFood] = surroundingCell.getLifeform(this.food[j]);
                    
                    ++countSurroundingFood;
                    
                }
                
            }
            
        }
        
        Lifeform[] food = new Lifeform[countSurroundingFood];
        
        for(int i = 0; i < countSurroundingFood; ++i)
        {
            
            food[i] = unsortedFood[i];
            
        }
        
        return food;
        
    }
    
    /**
     * Retrieves all surrounding cells that are free, 
     * meaning no lifeform of the same type is on it yet.
     * 
     * @return Cell[]
     */
    protected Cell[] getFreeSurroundingCells()
    {
        
        Cell[] cells = this.getCell().getSurroundingCells();
        Cell[] unsortedFreeCells = new Cell[cells.length];
        
        int countUnsorted = 0;
        
        for(int i = 0; i < cells.length; ++i)
        {
            
            if(cells[i] != null)
            {
                
                if(!cells[i].hasLifeform(this.getClass()))
                {
                    
                    unsortedFreeCells[countUnsorted] = cells[i];
                    
                    ++countUnsorted;
                    
                }
                
            }
            
        }

        Cell[] sortedFreeCells = new Cell[countUnsorted];
        
        for(int i = 0; i < countUnsorted; ++i)
        {
            
            sortedFreeCells[i] = unsortedFreeCells[i];
            
        }
        
        return sortedFreeCells;
        
    }

    
    /**
     * Makes this lifeform procreate.
     * Will spread the grass depending on the configured values coming from the Settings object.
     * 
     * @return void
     */
    protected void procreate()
    {
        
        Cell[] freeSurroundingCells = this.getFreeSurroundingCells();
        Lifeform[] sourroundingPartners = this.getSurroundingPartners();
        Lifeform[] sourroundingFood = this.getSurroundingFood();
        
        if(freeSurroundingCells.length > 0 && freeSurroundingCells.length >= requiredAmountOfFreeSurroundingCellsForProcreation)
        {
            
            if(sourroundingFood.length >= requiredMinimumAmountOfSurroundingFoodForProcreation)
            {
                
                if(sourroundingPartners.length >= requiredMinimumAmountOfSurroundingPartnersForProcreation)
                {
                    
                    // Pick a random surrounding cell to spawn a new lifeform.
                    
                    int randomCellIndex = freeSurroundingCells.length > 0 ? RandomGenerator.nextNumber(freeSurroundingCells.length) : 0;
                
                    Cell randomCell = freeSurroundingCells[randomCellIndex];
                    
                    try 
                    {
                        
                        App.getGameObject().addLifeformToWorld(randomCell, this.getClass().newInstance());
                        
                    } 
                    catch(Exception e) 
                    {
                        
                        e.printStackTrace();
                        
                    }
                        
                }
                
            }
            
        }
        
    }
    
    /**
     * Retrieves this lifeform's image.
     * 
     * @return Group
     */
    public abstract Group getImage();
    
    /**
     * Moves this lifeform.
     * 
     * @return void
     */
    public abstract void move();
    
    /**
     * Makes this lifeform die.
     * 
     * @return void
     */
    protected abstract void die();
        
    /**
     * Makes this lifeform nourish / eat food, if applicable.
     * 
     * @return void
     */
    public abstract void nourish();
        
}
