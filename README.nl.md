# Bingo Drawer

Een eenvoudige, toegankelijke bingo-app. Stel het hoogste nummer in, trek zonder herhaling en zie de getrokken nummers gemarkeerd in een strak raster. Een groot overlay toont kort het laatst getrokken nummer voor goede leesbaarheid.

## Functies
- Stel een maximum in en pas toe; trek unieke nummers tot ze op zijn.
- Groot overlay voor het laatst getrokken nummer, automatisch passend bij het rastergebied.
- Raster markeert getrokken nummers; ongetrokken blijven zichtbaar.
- Reset met bevestiging; het invoerveld verdwijnt na toepassen en verschijnt weer bij reset.
- Status blijft bewaard tussen app-herstarts (max, trekkingen, laatste nummer, ingevoerde tekst).
- Werkt met licht/donker thema; toetsenbord verbergt wanneer het veld verdwijnt.

## Aan de slag
```bash
./gradlew :app:assembleDebug
```
Installeer de APK vanuit `app/build/outputs/apk/debug/` op je toestel of emulator.

## Gebruik
1) Voer het hoogste nummer in en tik op **Apply**.
2) Tik op **Draw** voor een uniek nummer; het overlay toont het resultaat en het raster markeert het.
3) Tik op **Reset** (bevestig) om de sessie te wissen en een nieuw bereik te zetten.

## Toegankelijkheid
- Groot, contrastrijk overlay met automatisch aangepaste tekst.
- Duidelijke markering in het raster voor getrokken nummers.
- Thema-bewuste invoer en labels.

## Assets
- App-icoon: `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Play Store-tekst: `PLAY_STORE_DESCRIPTION.md`

## Notities
- Werkt offline; geen login nodig.
- Ontworpen voor telefoons en tablets.
