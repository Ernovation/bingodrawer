# Bingo Drawer

En enkel och tillgänglig bingoapp. Ställ in högsta numret, dra utan upprepningar och se dragna nummer markerade i ett rent rutnät. En stor overlay visar senast dragna nummer kort för enkel läsbarhet.

## Funktioner
- Sätt ett maxnummer och använd det; dra unika nummer tills de tar slut.
- Stor overlay för senaste draget, autoanpassas för rutnätsytan.
- Rutnätet markerar dragna nummer; odragna förblir synliga.
- Återställ med bekräftelse; inmatningsfältet döljs efter användning och visas igen vid återställning.
- Tillstånd bevaras mellan omstarter (max, drag, senaste nummer, inmatad text).
- Fungerar i ljus/mörkt tema; tangentbordet döljs när fältet döljs.

## Kom igång
```bash
./gradlew :app:assembleDebug
```
Installera APK:n från `app/build/outputs/apk/debug/` på din enhet eller emulator.

## Användning
1) Ange högsta nummer och tryck **Apply**.
2) Tryck **Draw** för ett unikt nummer; overlayn blinkar resultatet och rutnätet markerar det.
3) Tryck **Reset** (bekräfta) för att rensa sessionen och sätta ett nytt intervall.

## Tillgänglighet
- Stor, högkontrast-overlay med autojusterad text.
- Tydlig markering i rutnätet för dragna nummer.
- Tema-medveten input och etiketter.

## Tillgångar
- Appikon: `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Play Store-text: `PLAY_STORE_DESCRIPTION.md`

## Notiser
- Fungerar offline; inget inlogg krävs.
- Designad för mobiler och surfplattor.
