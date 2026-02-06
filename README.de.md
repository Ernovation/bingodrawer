# Bingo Drawer

Eine einfache, barrierearme Bingo-App. Lege die höchste Zahl fest, ziehe ohne Wiederholungen und sieh die gezogenen Zahlen in einem klaren Raster hervorgehoben. Ein großes Overlay zeigt kurz die zuletzt gezogene Zahl für gute Lesbarkeit.

## Funktionen
- Maximalzahl festlegen und anwenden; einzigartige Zahlen ziehen, bis alle weg sind.
- Großes Overlay für die letzte gezogene Zahl, automatisch an die Rasterfläche angepasst.
- Raster hebt gezogene Zahlen hervor; ungezogene bleiben sichtbar.
- Zurücksetzen mit Bestätigung; Eingabefeld wird nach dem Anwenden ausgeblendet und beim Zurücksetzen wieder angezeigt.
- Zustand bleibt zwischen App-Neustarts erhalten (Max, Ziehungen, letzte Zahl, eingegebener Text).
- Hell/Dunkel-tauglich; Tastatur wird ausgeblendet, wenn das Feld ausgeblendet ist.

## Einstieg
```bash
./gradlew :app:assembleDebug
```
Installiere die APK aus `app/build/outputs/apk/debug/` auf deinem Gerät oder Emulator.

## Nutzung
1) Höchste Zahl eingeben und **Apply** tippen.
2) **Draw** tippen, um eine eindeutige Zahl zu ziehen; Overlay zeigt das Ergebnis, Raster markiert es.
3) **Reset** tippen (bestätigen), um die Sitzung zu leeren und einen neuen Bereich zu setzen.

## Barrierefreiheit
- Großes, kontrastreiches Overlay mit auto-angepasster Schrift.
- Klare Hervorhebung im Raster für gezogene Zahlen.
- Themenbewusste Eingaben und Beschriftungen.

## Assets
- App-Icon: `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Play-Store-Text: `PLAY_STORE_DESCRIPTION.md`

## Hinweise
- Funktioniert offline; kein Login nötig.
- Für Smartphones und Tablets entworfen.
