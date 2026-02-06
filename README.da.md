# Bingo Drawer

En enkel og tilgængelig bingo-app. Angiv det højeste tal, træk uden gentagelser, og se de trukne tal markeret i et rent gitter. Et stort overlay viser kort det senest trukne tal for nem aflæsning.

## Funktioner
- Sæt et maksimalt tal og anvend det; træk unikke tal, indtil de er brugt op.
- Stort overlay for det sidst trukne tal, autojusteret til gitterområdet.
- Gitteret fremhæver de trukne tal; utrukne tal forbliver synlige.
- Nulstil med bekræftelse; inputfeltet skjules efter anvendelse og vises igen ved nulstilling.
- Tilstand bevares mellem app-genstarter (maks, træk, sidste tal, indtastet tekst).
- Virker i lys/mørk tema; tastaturet skjules, når feltet skjules.

## Kom i gang
```bash
./gradlew :app:assembleDebug
```
Installer APK'en fra `app/build/outputs/apk/debug/` på din enhed eller emulator.

## Brug
1) Indtast det højeste tal og tryk **Apply**.
2) Tryk **Draw** for et unikt tal; overlayet viser resultatet, og gitteret fremhæver det.
3) Tryk **Reset** (bekræft) for at rydde sessionen og sætte et nyt interval.

## Tilgængelighed
- Stort, højkontrast-overlay med autojusteret tekst.
- Tydelig fremhævning i gitteret for trukne tal.
- Tema-bevidst input og etiketter.

## Aktiver
- App-ikon: `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Play Store-tekst: `PLAY_STORE_DESCRIPTION.md`

## Noter
- Fungerer offline; ingen login påkrævet.
- Designet til telefoner og tablets.
