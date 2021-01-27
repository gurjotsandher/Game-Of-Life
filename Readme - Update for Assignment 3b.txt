
Changes as per the assignment:

- Implemented hexagonal fields

- Implemented procreation for all animals

- By default, the setting "Reset random generator before placing lifeforms" is now enabled.

- Plants / Grass:
  - Default spawn rate changed to 40%
  - Changed minimum amount of neighbors required for seeding to 2.
  - Changed minimum amount of free neighboring cells required for seeding to 3.
  
- Herbivores:
  - Color changed to Yellow
  - Default spawn rate hanged to 20%
  - Procreate if there are:
    - At least 1 other Herbivore neighbor
	- At least 2 free neighboring cells
	- At least 2 neighboring cells with food (plants)
	
- Carnivores implemented:
  - Color: Red
  - Default spawn rate set to 50%
  - Eats herbivores and omnivores
  - Must eat within 5 moves
  - Procreate if there are:
    - At least 1 other Carnivore neighbor
	- At least 3 free neighboring cells
	- At least 2 neighboring cells with food (herbivores or omnivores)

- Omnivores implemented: 
  - Color: Blue
  - Default spawn rate set to 55%
  - Eats herbivores, carnivores and plants
  - Must eat within 5 moves
  - Procreate if there are:
    - At least 1 other Omnivore neighbor
	- At least 3 free neighboring cells
	- At least 1 neighboring cells with food (herbivores, omnivores and plants)

- Generating lifeforms changed as required for the assignment:

  - It used to be defined like this, multiple lifeforms were possible per Cell:
  
	if(RandomGenerator.nextNumber(99) >= 100 - Settings.getSpawnRateGrass())
	{
						 
		this.addLifeformToWorld(world, column, row, new Grass());
		
	}
	
	if(RandomGenerator.nextNumber(99) >= 100 - Settings.getSpawnRateHerbivore())
	{

		this.addLifeformToWorld(world, column, row, new Herbivore());
		
	}
	
  - The assignment 3b now specifies that a different approach for spawning 
    lifeforms should be applied that allows only one Lifeform per cell:
  
    - Since my spawn rates are configurable at runtime through the Settings, 
	  I need to sort them to be able to check the lowest probability first.
	  There is a private inner class SpawnRate in the Game.java to do the sorting.
	    
		ArrayList<SpawnRate> spawnRates = new ArrayList<SpawnRate>();

        spawnRates.add(new SpawnRate(Settings.getSpawnRateCarnivore(), new Carnivore()));
        spawnRates.add(new SpawnRate(Settings.getSpawnRateGrass(), new Grass()));
        spawnRates.add(new SpawnRate(Settings.getSpawnRateHerbivore(), new Herbivore()));
        spawnRates.add(new SpawnRate(Settings.getSpawnRateOmnivore(), new Omnivore()));
        
        SpawnRate sorter = new SpawnRate();
        sorter.sortBySpawnRate(spawnRates);
		
	- The spawn rates are then respected this way:
	
        for(int row = 0; row < Settings.getWorldNumberRows(); ++row)
        {

            columnLoop: for(int column = 0; column < Settings.getWorldNumberColumns(); ++column)
            {
                
                int randomNumber = RandomGenerator.nextNumber(99);
                
                for(int i = 0; i < spawnRates.size(); ++i)
                {
                    
                    SpawnRate spawnRate = spawnRates.get(i);
                    
                    if(randomNumber >= 100 - spawnRate.getRate())
                    {
                                         
                        try 
                        {
                            
                            this.addLifeformToWorld(world, column, row, (Lifeform) spawnRate.getLifeform().getClass().newInstance());
                            
                        } 
                        catch (InstantiationException | IllegalAccessException e) 
                        {
                            
                            e.printStackTrace();
                            
                        }
                        
                        continue columnLoop;
                        
                    }
                    
                }

            } 
            
        }
	

  - Settings:
  
    - Implemented configurable spawn rates & requirements for procreation for the new and existing lifeforms.
	
	- Introduced a setting "Debugging" tools, disabled by default. Provides helpful functionality when activated, e.g.:
	
	  - Displays the column + row numbers on cells.
	  - Certain debugging outputs in the console.



Toggle between hexagonal & square fields:
- The programs starts with hexagnal fields by default
- To change to square fields, lick the "Settings" button and uncheck "Hexagonal fields" on the bottom, then click "Apply & restart".
  - You may have to manually resize the window
  
  