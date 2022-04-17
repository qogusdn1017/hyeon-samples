# Minigame Code Sample

A sample code structure example for creating minigames.

This branch's code is inspired from [monun](https://github.com/monun/)'s [paper-sample-complex](https://github.com/monun/paper-sample-complex). 

## Environment

**Paper + NMS / Bungee Integrated Development Environment**

- AllProjects

    - [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines/) dependencies (OPTIONAL)

---

- Paper
    
    **Paper project includes [paperweight](https://github.com/PaperMC/paperweight/) for NMS development environement.**

    You can remove the subproject named "dongle" if you do not need paperweight.
    
    <br>

    Basic Libraries:

    - [Paper 1.18.2](https://papermc.io/downloads) dependencies
    - [Tap](https://github.com/monun/tap) dependencies
    - [Kommand](https://github.com/monun/kommand/) dependencies
    - [InvFX](https://github.com/monun/invfx/) depenedencies (OPTIONAL)
    - [Heartbeat Coroutines](https://github.com/monun/heartbeat-coroutines/) dependencies (OPTIONAL)

---

- Bungee
    - [Bungeecord](https://spigotmc.org/wiki/bungeecord) dependencies

---

- [Server Script](https://github.com/monun/server-script) / Using [aroxu's GoLang version](https://github.com/aroxu/server-script/)

### This Project Requires:

- [JDK 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html) (Corretto JDK is Recommended.)
- libarchive-tools package (on Linux|Shell, for [./server-script.sh](./server-script.sh))
- Something that kills your time, like Solitaire, because paperweight takes some time to confgiured at first.

### Special Thanks:

- [monun](https://github.com/monun) - For creating wonderful libraries within Kotlin; helping developers implement functions simpler.
- [JetBrains](https://jetbrains.com/) - Using IntelliJ IDEA & Kotlin makes my life easier; helps to make development efficient using the kotlinx series
