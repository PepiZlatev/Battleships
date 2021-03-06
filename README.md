# Battleships

A localhost web game where you can you can add a Ship (Battle, Cargo or Patrol) into your collection and attack another players.

This project part of the Spring Fundamentals course at SoftUni. (May 2022)

## :gear: This is how it works:
- Anonymous users:
  * can register
  * can login in case of existing account

- Logged usersr:
  * can add ship
  * can fight another user's ship

## :hammer_and_pick: Tech Stack:
- Java
- HTML\CSS
- Spring Boot
- MySQL

## Screenshots:

- The index page is the main page of the web app. If you are not logged in, you have no access to the home and and ship-adding page.

Index |
:------:
![index](https://github.com/PepiZlatev/Battleships/blob/master/screenshots/index-page.png)

- On the left side you can see the register page and on the right the login page.

Register | Login
:--------------:| :--------------:
![register](https://github.com/PepiZlatev/Battleships/blob/master/screenshots/register.png) | ![login](https://github.com/PepiZlatev/Battleships/blob/master/screenshots/login.png)

Once you create an account or login with an existing one, you will be authomatically transfered to the home page, where you can either add a new ship into your collection, or if you have at least one, you can attack the ship of anther player.

- On the left side is the home page, where the fight happens, and the add-ship page, where you can create your own ship.

Home | Add Ship
:--------------:| :--------------:
![home](https://github.com/PepiZlatev/Battleships/blob/master/screenshots/home.png) | ![add ship](https://github.com/PepiZlatev/Battleships/blob/master/screenshots/add-ship.png)

The page, where every new registered or logged in user is redirected after the registration/login is the Home page, the image on the left. The right image is the page, where new ship is created.

- Left -> Fight preparation; Right -> Result of the fight.

Home with Ships added | After the fight happened
:--------------:| :--------------:
![preparation](https://github.com/PepiZlatev/Battleships/blob/master/screenshots/with%20ships.png) | ![result](https://github.com/PepiZlatev/Battleships/blob/master/screenshots/after%20fight.png)

After each fight the defender's ship is removed, if its initial health was less than attacker's initial power. In cases where the defender' initial health is more than the attacker's initial power, then the defenre's health is reduced, but the ship is not removed.
