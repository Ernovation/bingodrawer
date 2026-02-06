# Bingo Drawer

Aplicativo simples e acessível para sortear números de bingo. Defina o maior número, sorteie sem repetição e veja os números destacados em uma grade limpa. Um sobreposto grande mostra rapidamente o último número para leitura fácil.

## Recursos
- Defina um número máximo e aplique; sorteie números únicos até acabar.
- Sobreposição grande para o último número sorteado, autoajusta para caber na área da grade.
- Grade destaca os números já sorteados; os não sorteados permanecem visíveis.
- Reinicie com confirmação; o campo de entrada some após aplicar e reaparece ao reiniciar.
- Estado é preservado entre reinícios do app (máximo, sorteios, último número, texto digitado).
- Amigável a temas claro/escuro; o teclado oculta quando o campo some.

## Primeiros passos
```bash
./gradlew :app:assembleDebug
```
Instale o APK em `app/build/outputs/apk/debug/` no seu dispositivo ou emulador.

## Uso
1) Digite o maior número e toque em **Aplicar**.
2) Toque em **Sortear** para obter um número único; a sobreposição mostra o resultado e a grade destaca.
3) Toque em **Reiniciar** (confirme) para limpar a sessão e definir um novo intervalo.

## Acessibilidade
- Sobreposição grande e de alto contraste com texto autoajustável.
- Destaque claro na grade para números sorteados.
- Entrada e rótulos sensíveis ao tema.

## Assets
- Ícone do app: `app/src/main/res/drawable/ic_launcher_foreground.xml`
- Texto da Play Store: `PLAY_STORE_DESCRIPTION.md`

## Notas
- Funciona offline; não requer login.
- Feito para telefones e tablets.
