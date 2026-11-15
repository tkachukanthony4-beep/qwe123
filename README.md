# Donut Visual Client (Fabric, Minecraft 1.21.4)

Right Shift = module menu (click to toggle, ⚙ for settings). C = zoom (rebindable). Settings + enabled state
are saved to config/donutvisual.properties.

## Rendering modules (with settings)
- BlockOverlay: fill/outline on targeted block, rainbow/static, pulse, hide vanilla outline
- ColorGrade: Warm/Cool/Sunset/Toxic/Night presets or custom tint, brightness, vignette
- CustomGlint: static or rainbow enchant-glint colour (ships a greyscale glint texture so it can be tinted)
- HandAura: soft glow around the first-person hand
- HandPose: position / rotation / scale of hands + presets (Low, Tilted, Far, Compact)
- CustomFOV: 30-140, optionally keeps sprint FOV effects
- Zoom: level, smooth easing, scroll-to-adjust, cinematic camera

## Other modules
FPS, Coords, CPS, Keystrokes, Clock, Speed, Ping, Memory, WorldInfo, NetherCoords, Watermark, Compass,
ModuleList, FPSGraph, TargetDot, ArmorHUD, PotionHUD, ItemCounters, Fullbright, NoHurtCam, NoFireOverlay, ToggleSprint

## Build
JDK 21, then:  gradle wrapper --gradle-version 8.11  &&  ./gradlew build
Jar: build/libs/donutvisual-1.0.0.jar -> mods folder (needs Fabric Loader + Fabric API).

## Notes
Mixins use defaultRequire=0, so a mismatched target is skipped instead of crashing. If a feature does nothing,
check latest.log for "Mixin ... failed" and adjust that method signature for your exact version.
