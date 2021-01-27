"Game of life" by @gsand - March 2020

Functionality:

- Click anywhere on the game world / tiles to advance one the game by one turn.

Features:

- A Herbivore is an Animal (which is a Lifeform). A Carnivore class could easily be extended from the Animal.java
- Herbivore: Yellow, move into cells randomly, eat plants found in cell if any, die if they don’t find food within 5 turns.
- Animal.java also has a member "Class<?>[] food" that is an Array of lifeform classes, e.g. "{Plant.class}" for the derived Herbivore objects. It could for example be changed to "{Carnivore.class, Herbivore.class}" when implementing carnivores.
- Animal.java has a method "consumeFoodOnCell(Cell cell)" and a configurable setting "allowedNumberOfTurnsWithoutFood" (e.g. with the value 5) that can be adjusted for each derived animal.
- Each Lifeform has an independent spawn probability. Herbivores can spawn on top of Grass, on the same cell. 
  - The spawn rates are considered for each single cell when initializing the game's lifeforms in "Game.setLifeformsOnCells(World world)".
  - Default spawn probabilities can be changed in the Lifeform.java: Lifeform.LIFEFORM_GRASS_SPAWN_RATE is at 20, Lifeform.LIFEFORM_HERBIVORE_SPAWN_RATE is at 15, or at runtime through the game's settings.
- All lifeforms have a method getImage(), derived from Liveform.java that returns a javafx.scene.Group object, which is filled depending on the lifeform. It could easily be enhanced to display images or graphics of any sort.
- All lifeforms contain the methods move() and die(), derived from Liveform.java. Grass doesn't actually move (though its seedinging is triggered in this method) or die except when it gets eaten (handled differently), but it could still be implemented for other plants that extend the Plant.java
- Cell.sortLifeforms(Lifeform[] input) will sort all lifeforms in the order they need to be drawn (e.g. an animal should be on grass, maybe later Trees on grass, Monkeys on trees, etc.)
- Every class derived from Lifeform.java will have a method "procreate()" that is called once every turn. It will check if they can procreate (Herbivores, not implemented in this version) or seed (Grass, integreaded in this version).
- Grass: Shown as green in a cell, cannot move, are placed initially in a random order, never die by themselves.
- Grass: Will be removed from the game once eaten by a Herbivore.
- Grass will seed out. The rules are:
  - In order to seed, a plant will need at least 3 empty cells directly around them. This number is defineable per "Plant.requiredAmountOfFreeSurroundingCellsForSeeding".
  - A plant needs at least 4 plants of the same type in neighboring cells in order to seed, this number is defineable per "requiredMinimumAmountOfSurroundingPartnersForSeeding".
- The amount of cells / the world size can be mofified with World.WORLD_NUMBER_ROWS and World.WORLD_NUMBER_COLUMNS
- WorldRectangular.java is extended from World.java, it could be exchanged for a "WorldHexagonal" in the "Game2D.java", to display the game with hexagonal fields.
- Flexible layout: Cell.java has a method "getSurroundingCells()" that is being used by the different lifeforms for movement and procreation. It could be adjusted depending on the shape of the world (e.g. from rectangular to hexagonal fields)
- Game2D.java is derived from Game.java, it could be exchanged for example for a 3D view, or to have multiple World objects at once in a single game.
- There is an EventListener.java that listens to certain game events and displays messages and statistics to the console: "onGameStarted", "onGameEnded" and "onAdvanceTurn".

Update, April 1st 2020:
- Introduced a "Settings" button to the simulation, on the bottom of the screen. The following parameters can now easily be adjusted from within the application:
  - World: Number of rows (Please rescale the windows manually after adjusting this value)
  - World: Number of columns (Please rescale the windows manually after adjusting this value)
  - Spawn rate: Herbivores
  - Spawn rate: Grass
  - Grass: Number of neighbors required to seed (lower -> Faster growrth)
  - Grass: Number of free surrounding cells required to seed (lower -> Faster growth)
  - Herbivores: Number of rounds they can go before starving.
  - Yes/No: Reset random generator before placing lifeforms. Default: No!