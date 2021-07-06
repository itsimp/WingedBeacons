# ![image](src/main/resources/assets/wingedbeacons/icon.png) WingedBeacons
Minecraft fabric mod that adds additional beacon functionality!

## Flight Effect
![FlightEffect](https://user-images.githubusercontent.com/34523218/124624493-eb350a00-de4a-11eb-80c4-021c0d2573c8.png)

Winged Beacons adds a new effect to the game: Flight. This effect allows for creative flight and when it expires slow falling will be applied to the player.

## Beacons Changed
![BeaconUI](https://user-images.githubusercontent.com/34523218/124624740-28010100-de4b-11eb-8ecd-69e785c55815.png)

Flight has been added to beacons as a secondary effect. It requires a max beacon level to use as well as an advancement & certain experience level as specified in the config.

## Configuration
You can configure various parts of Winged Beacons to your liking. The config currently has 3 options:
- xpLevelRequired (default: `30`) - Minimum level required to access the flight effect in a beacon
- slowFallingTime (default: `10`) - Amount of time in seconds that slow falling will be applied to the player after the flight effect expires
- advancementRequired (default: `minecraft:end/elytra`) - A certain advancement a player must have achieved in order to access the flight effect in a beacon
  - To disable set to `minecraft:adventure/root`
