# Hordes

Hordes is a small Bukkit plugin which increases observable amount of monsters
without actually spawning more of them. It's very lightweight and it will not
have any impact on the overall performance.

## How does it work?

First let's get some things clear. First, in Minecraft mobs spawn in every dark
place: in caves, under trees, at night etc. Second, Bukkit has an option to 
limit amount of mobs spawned per chunk. By default there can be 70 monsters,
you can increase/decrease that number and it will have an impact on performance.
Third, the player cannot be in two places at the same time. I think we can agree
with that. So why do mobs in caves fill this limit up when the player is on the
surface? They cannot threaten him, and he cannot kill them.

Hordes removes all the mobs that are far away from the player and blocks
spawning new ones in locations out of reach of the player. This means that
Bukkit will spawn monsters only near the player, on the same vertical level.
While the amount of mobs is intact, they are placed only near the player.

If you don't want your players to fight with greater amount of mobs you can
decrease the limit in _bukkit.yml_ file. It will make your server run faster
(less mobs to handle - better performance) while maintaining the amount of mobs
near the players.

## Configuration

Each world in which you want to enable the plugin needs one section in the
configuration, named as the name of the world. `height` option is responsible
for the vertical distance from the player beyond which monsters will despawn.
If you want to limit horizontal distance, there's an option for that in
_spigot.yml_ config file, it's called `mob-spawn-range`. Use ctrl+f to find
it. `mobs` is a list of types of mobs handled by this plugin taken from
[here](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/EntityType.html).
If you don't want some mob to be despawned simply remove it from this list.
`multi` option is health multiplier. It will increase health of naturally
spawned mobs that many times. It can has a floating point, but cannot be
negative.

## Commands

Hordes has only one command, "/hordesreload", which reloads the configuration.
You need _hordes.reload_ command to use it, which is default for ops.

## Licensing, source code, compiling

Hordes is licensed under GPLv3, which means it's a free software (as in "free
speech", not "free beer"). It's distributed from
[SpigotMC.org](https://www.spigotmc.org/resources/hordes.12879/) for 2€.
The source code can be found on [GitHub](https://github.com/Co0sh/Hordes). To
compile it you need JDK 1.7 and Maven 3 installed on your system. Issue
`mvn install` command inside the root directory. The JAR file should appear
in _target_ folder.
