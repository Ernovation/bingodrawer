# Bingo Drawer

A simple, accessible bingo number drawer for Android. Set a highest number, draw without repeats, and see drawn numbers highlighted on a clean grid. A large overlay briefly shows the last drawn number for easy readability.

## Features
- Set a maximum number and apply it; draw unique numbers until exhausted.
- Big overlay for the last drawn number, auto-sizes to fit the grid area.
- Grid highlights drawn numbers; undrawn numbers stay visible.
- Reset with confirmation; input field hides after applying and reappears on reset.
- State is persisted across app restarts (max, draws, last number, input text).
- Light/dark theme friendly; keyboard hides when input hides.

## Getting started
```bash
./gradlew :app:assembleDebug
```
Install the resulting APK from `app/build/outputs/apk/debug/` on your device or emulator.

## Usage
1) Enter the highest number to include and tap **Apply**.
2) Tap **Draw** to get a unique number; the overlay flashes the result and the grid highlights it.
3) Tap **Reset** (confirm) to clear the session and set a new range.

## Accessibility
- Large, high-contrast overlay with auto-sizing text.
- Clear grid highlighting for drawn numbers.
- Theme-aware input and labels.

## Assets
- Launcher icon: `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Play Store text: `PLAY_STORE_DESCRIPTION.md`

## Notes
- Works offline; no sign-in required.
- Designed for phones and tablets.
