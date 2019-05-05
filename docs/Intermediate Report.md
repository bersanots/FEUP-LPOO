# LPOO_TG Project Name

In this platform game you can help our hero escape the castle by pulling the lever or collecting a key that opens the gates throughout three different levels in which you will encounter all kinds of foes, from always watchful guards to reckless ogres.

This project was developed by Bernardo Santos (up201504711@fe.up.pt) and Filipe Almeida (up201708999@fe.up.pt) for LPOO 2018/19.

## Implemented Features

So far, the developed code isn't focused on the final game, but on it's components. Several classes were created to represent each element of the game and the way they interact with each other.

The elements created to this point are:

* The Hero: the main focus of the game. It's controlled by the user and used to navigate through the map. Initially has 3 lives and every time it loses one, has a short invulnerability time.
* Walls: used to create the main frame of the map. Each wall is created separately, which allows to not only create the 4 outer walls but also to give more depth to the map.
* Monsters: not very smart characters but still a threat. Only move from one side to another not taking in consideration the position of the hero and if this two meet in the same position, one life is taken away.
* Wizards: move in a similar way to monsters but the threat they pose is their spells. One is shot every time there is a clear way to hit the hero, although after one is fired there's minimum time that has to pass after next one.
* Items: extra lives and keys spread throughout the map.
* The Gate: the final objective of the hero. Behaves just like a wall unless the hero has collected all the keys which allows the gate to be opened.

Screenshots:
![Screenshots 1](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_212/blob/master/docs/Screenshots/screenshot1.png)
![Screenshots 2](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_212/blob/master/docs/Screenshots/screenshot2.png)
![Screenshots 3](https://github.com/FEUP-LPOO/projecto-lpoo-2019-lpoo_212/blob/master/docs/Screenshots/screenshot3.png)

## Planned Features

Although not yed decided exactly what, we´re still looking to add new elements to the game. This could be new characters that have different interactions or by creating traps. There is still the possibility to give the hero new abilities or powers, but it's yet to be decided.

The most important part that is still left to be created is the actual game. All the classes are developed in a way that allows the final process to simply be assembling all the pieces. Despite that, this part should also be done carefully because it's going to determine the game's playability and difficulty.

Another feature to be implemented are all the menus as well as a way to access different levels that is still to be decided.

To the point of this report, the game is being developed using lanterna and letters to represent the different objects of the game, but it shouldn't be very hard to use another graphical interface, since all the functions that take care of the visual part are being separated from the other methods.

Despite the fact that we are trying to have good coding practices from the start, it's very likely that we'll find some SOLID violations or Code Smells along the way. We've decided to go over this after the game is fully working although acknowledging that it is one of the most important parts.

Note: The screen that appears if the hero gets to the gate that says "GAME WON" is only for testing purposes and will be completely removed.

## Design

### Classes
#### Game

Used to create and store all the components of the game. The entire purpose of this class is to allow communication between all the object that are part of the game. Contains the game loop which is programmed to run at approximately 30 frames per second.

#### Position

Represents a position on the board. Stores the x and y values.

#### Character

Abstract class that represents a moving character. Stores it's position and provides functions to move it. Also implements abstract methods to draw itself amd to check fo collisions with walls.

#### Hero (extends Character)

Represents the hero. Adds to the class Character information about the lives and keys it has and the functions to alter these values.

#### Monster (extends Character)

Represents one monster. Adds to the class Character information about his speed and the direction of his movement. It also has methods to determine his next movement, since it is programed to travel in one direction until it hits a wall and then turn back.

#### Wizard (extends Character)

Represents one wizard. Adds to the class Character information about his speed and the direction of his movement. It also has a spell that is set to null if one is not being fired. For a spell to be fired, the hero has to share one of the coordinates with the wizard and there needs to be a clear path in that direction (no walls in between). Implements methods to determine his next movement, since it is programed to travel in one direction until it hits a wall and then turn back, and to determine when to fire a spell.

#### Spell (extends Character)

Represents one Spell. Adds to the class Character information about his speed and the direction of his movement. It also has methods to determine his next movement, since it is programed to travel in one direction until it hits a wall and then disappear.

#### Wall

Abstract class that represents a wall. Stores four integers that delimit a rectangle. This rectangle determines the space that the wall takes. It has methods to draw itself, test for collision given a position and a direction and check if there is a straight clear path between two positions.

#### Gate (extends  Wall)

Represents one gate. Adds to the class wall the number of key a hero needs to have to go through it. Implements a method to test if the gate can be opened and overrides the draw method to be represented with a different letter.

#### Item

Represents an item to be picked up by the hero. Stores its position and a boolean value that determines if it was picked up or not. It has two abstract methods: one to draw itself and one to determine the modification it gives to the hero.

#### Life (extends Item)

Represents an extra life the hero can get.

#### Key (extends Item)

Represents a Key the hero can get.
