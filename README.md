# WordsSeacher

Приложение для поиска и перевода англоязычных слов, используя API https://dictionary.skyeng.ru/doc/api/external
-

Как собрать проект

- Склонировать репозиторий с Github, далее открыть его в Android Studion 4.0 и выше,
после дождаться синхронизации с gradle и во вкладке Build выбрать Build Bundle/APK
выбрать Build APK;

- Так же проект можно собрать без Android Studio, но с установленным Gradle, для этого
необходимо выполнить команду gradle assembleDebug в терминале;

- В обоих случаех, после сборки проекта debug apk файл появится в папке app/build/outputs/apk/debug;

Библиотеки, используемые в проекте

Кроме стандартных библиотек поддержки androidx, используются следующее:

- android material, для работы с material дизайн компонентами android;

- lifecycle extensions, для работы с viewmodel и livedata;

- retrofit, для осуществления сетевых вызовов;

- gson converter , для десериализации json , полученных из сети;

- glide, для загрузки с сети изображений;

- arch core-testing, для тестирования livedata