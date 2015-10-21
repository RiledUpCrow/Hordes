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

You can edit the plugin settings in the config.yml file. `async-despawn` option 
in `global-settings` controls if the plugin should despawn mobs in an asych thread.
Leave that set to `true` unless you have problems with other plugins. `despawn-interval`
controls how often the mobs are despawned. Default is every 10 seconds. If you make 
that number lower, the mobs will be removed more frequently, but it may slightly impact
the performance. Last is `version`. Do not touch this setting!

Each world in which you want to enable the plugin needs one section in the `worlds`
branch in the configuration, named as the name of the world. `height` option is responsible
for the vertical distance from the player beyond which monsters will despawn.
If you want to limit horizontal distance, there's an option for that in
_spigot.yml_ config file, it's called `mob-spawn-range` (it's measured in whole chunks.
Use ctrl+f to find it. Option `mobs` is a list of types of mobs handled by this plugin taken from
[here](https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/entity/EntityType.html).
You don't have to make it with CAPS LOCK, you can also use spaces instead of `_`.
If you don't want some mob to be despawned simply remove it from this list.
`health` option is health multiplier. It will increase health of naturally
spawned mobs that many times. It can has a floating point, but cannot be
negative.

`ratio` option controls the chance for a mob to spawn, `0` means it won't show at all
and `1` means it will be allowed every time Minecraft tries to do so. This setting
should be used along with the `custom` branch, which lets you control individual
properties of each mob specified in `mobs` list. If you don't specify any mob here,
it will simply use settings defined for the whole world (`health` and `ratio`).
For example, if you want half of all monsters to be of type _Zombie_, you should set
global `ratio` to `0.5` and add `zombie` to `custom` branch. There you should
specify `ratio` for zombie to be set to `1`. It will mean that every time a _Zombie_ will
try to spawn it will be allowed to do so, but all other mobs will have only 50% chance to
be spawned.

This will not decrease the total amount of mobs, because Minecraft will keep spawning the mobs
until it reaches its limit. The only difference is that mobs with lower `ratio` will
spawn slower, because some spawn events will be blocked. Generally a spawn events happens
once a tick (20 times per second), so if 10 of them will be blocked instead of 8, mobs
will spawn less frequently. You probably won't notice the difference though.

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
