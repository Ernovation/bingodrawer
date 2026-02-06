# Bingo Drawer

En enkel og tilgjengelig bingo-app. Angi høyeste tall, trekk uten gjentakelser, og se de trukne tallene markert i et ryddig rutenett. Et stort overlegg viser siste tall kort for lett lesbarhet.

## Funksjoner
- Sett et maksimumstall og bruk det; trekk unike tall til de er brukt opp.
- Stort overlegg for sist trukket tall, auto-tilpasset til rutenettområdet.
- Rutenettet markerer trukne tall; utrukne forblir synlige.
- Nullstill med bekreftelse; inputfeltet skjules etter bruk og vises igjen ved nullstilling.
- Tilstand bevares mellom app-omstarter (maks, trekk, siste tall, skrevet tekst).
- Tema-vennlig for lyst/mørkt; tastaturet skjules når feltet skjules.

## Kom i gang
```bash
./gradlew :app:assembleDebug
```
Installer APK-en fra `app/build/outputs/apk/debug/` på enheten eller emulatoren din.

## Bruk
1) Skriv inn høyeste tall og trykk **Apply**.
2) Trykk **Draw** for et unikt tall; overlegget viser resultatet og rutenettet markerer det.
3) Trykk **Reset** (bekreft) for å tømme økten og sette et nytt område.

## Tilgjengelighet
- Stort, høy-kontrast overlegg med autojustert tekst.
- Tydelig markering i rutenettet for trukne tall.
- Tema-bevisst input og etiketter.

## Ressurser
- App-ikon: `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Play Store-tekst: `PLAY_STORE_DESCRIPTION.md`

## Notater
- Fungerer offline; ingen innlogging kreves.
- Designet for telefoner og nettbrett.
