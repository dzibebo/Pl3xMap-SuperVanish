# Pl3xMap-SuperVanish

A lightweight, high-performance Minecraft plugin that hides players vanished with **SuperVanish** or **PremiumVanish** from **Pl3xMap**.

## Features

- **Instant updates**: Automatically hides players on Pl3xMap when vanished and restores visibility upon un-vanishing.
- **Zero bloat**: No telemetry, no background update checkers, no external network requests.
- **Modern platform support**: Fully compatible with Spigot, Paper, Purpur, Gale, Leaf, and Folia on Java 17, 21, and modern GraalVM.

## Requirements

- Minecraft Server: **1.19.4+** (Paper / Purpur / Leaf / Folia recommended)
- [Pl3xMap](https://modrinth.com/plugin/pl3xmap)
- [SuperVanish](https://www.spigotmc.org/resources/supervanish-invisible-invisible.1331/) or [PremiumVanish](https://www.spigotmc.org/resources/premiumvanish-stay-hidden-bungee-support.14404/)

## Building

```bash
./gradlew build
```
The compiled jar will be located in `build/libs/`.

## License

This project is licensed under the [MIT License](LICENSE).
