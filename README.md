1. `./gradlew :mpp-library:syncMultiPlatformLibraryDebugFrameworkIosX64`
2. `cd ios-app`
3. `pod install`
4. Открыть workspace в xcode
5. подставить в ios-app/src/TestViewController.swift:68 API_KEY от гуглкарты
6. Удалить содержимое `mpp-library/build/cocoapods`
7. Собрать на девайс из xcode
8. Жать кнопку press me, на каком-то из нажатий все зависнет и выпадет ошибка в `UpdateHeapRef`
