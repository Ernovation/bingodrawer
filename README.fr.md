# Bingo Drawer

Application de bingo simple et accessible. Définissez le plus grand numéro, tirez sans répétition et voyez les numéros tirés mis en évidence dans une grille épurée. Un grand overlay affiche brièvement le dernier numéro pour une lecture facile.

## Fonctionnalités
- Définir un numéro maximum et l’appliquer ; tirer des numéros uniques jusqu’à épuisement.
- Grand overlay pour le dernier numéro tiré, auto-ajusté à la zone de grille.
- La grille met en évidence les numéros tirés ; les non tirés restent visibles.
- Réinitialisation avec confirmation ; le champ de saisie se cache après application et réapparaît à la réinitialisation.
- L’état est conservé entre les redémarrages (max, tirages, dernier numéro, texte saisi).
- Compatible thèmes clair/sombre ; le clavier se masque lorsque le champ est caché.

## Prise en main
```bash
./gradlew :app:assembleDebug
```
Installez l’APK depuis `app/build/outputs/apk/debug/` sur votre appareil ou émulateur.

## Utilisation
1) Saisissez le plus grand numéro et touchez **Apply**.
2) Touchez **Draw** pour obtenir un numéro unique ; l’overlay affiche le résultat et la grille le met en évidence.
3) Touchez **Reset** (puis confirmez) pour effacer la session et définir une nouvelle plage.

## Accessibilité
- Grand overlay à fort contraste avec texte auto-ajusté.
- Mise en évidence claire dans la grille pour les numéros tirés.
- Champ et libellés sensibles au thème.

## Ressources
- Icône de l’appli : `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Texte Play Store : `PLAY_STORE_DESCRIPTION.md`

## Notes
- Fonctionne hors ligne ; aucune connexion requise.
- Conçu pour téléphones et tablettes.
