<img width="128" height="128" src="https://i.imgur.com/MFgAUdY.png" alt="icon_square">

# Xposed-Disable-FLAG_SECURE

Xposed Module to Disable FLAG_SECURE, enabling screenshots and recording in apps that normally wouldn't allow it.

## [Downloads](https://github.com/VarunS2002/Xposed-Disable-FLAG_SECURE/releases/)

>[![APK: v1.0.0](https://img.shields.io/badge/APK-v1.0.0-brightgreen)](https://github.com/VarunS2002/Xposed-Disable-FLAG_SECURE/releases/Xposed-Disable-FLAG_SECURE_1.0.0.apk)
![Build: passing](https://img.shields.io/badge/build-passing-brightgreen)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

## Requirements:

- Android 8.0+ (Oreo/SDK 26)

- Xposed implementation installed properly like [EdXposed](https://github.com/ElderDrivers/EdXposed/) and [LSPosed](https://github.com/LSPosed/LSPosed/)

- Untested but may work on Rootless Xposed implementations like [Tai Chi](https://github.com/taichi-framework/TaiChi/)

## Usage:

- EdXposed:
  - Normal Mode:
    - Enable the module and reboot
  - White List / Activation Scope Mode:
    - Enable the module
    - Select `Android System`
    - Select the target app in which you want to enable screenshots
    - Reboot
  - Black List Mode:
    - Enable the module
    - Do not select `Android System`
    - Do not select the target app in which you want to enable screenshots
    - Reboot
- LSPosed:
  - Enable the module
  - Select `System Framework`
  - Select the target app in which you want to enable screenshots
  - Reboot

## Note:

- This app does not prevent apps from detecting that you've taken a screenshot or recorded it like Snap or Instagram.
  This only enables it in apps that prohibit screenshots and recording. So don't screenshot your girlfriend's nudes.
  (You probably don't have one since you're reading this anyway)


- Xposed Implementations Tested on:
  - [EdXposed](https://github.com/ElderDrivers/EdXposed/)
  - [LSPosed](https://github.com/LSPosed/LSPosed/)


- Apps Tested on:
  - Amazon Prime Video (Screenshots and Screen Recording of Media)
  - Telegram (Secret Chat & Disappearing Media)
  - Reddit (Anonymous Browsing Mode)
  - Google Chrome (Incognito Mode)
  - Brave Browser (Incognito Mode)


- This app is a fork of the existing apps but with better compatibility:
  - https://github.com/veeti/DisableFlagSecure/
  - https://github.com/LSPosed/DisableFlagSecure/
