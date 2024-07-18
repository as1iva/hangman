# Проект "Виселица"
## Описание
Проект представляет из себя реализацию игры "Виселица" с консольным интерфейсом, где игроку предлагается отгадать загаданное слово за 6 попыток, иначе он проиграет.


## Установка

Склонируйте репозиторий

```
git clone git@github.com:as1iva/hangman.git
```

Перейдите в директорию с проектом

```
cd hangman
```

Скомпилируйте программу

```
javac src/main/*.java -d classes
```

```
jar -cvfm hangman.jar resources/META-INF/MANIFEST.MF -C classes main -C resources russian-words.txt
```

Запустите игру

```
java -jar hangman.jar
```
