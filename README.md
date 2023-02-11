# BerlinClock
This is a sample app which converts normal time to Berlin clock illustration, app is developed by using Clean Architecture with MVVM and Jetpack Compose for UI screens.

# Rules
- The Berlin Clock is a clock that tells the time using a series of illuminated coloured blocks.
- The top lamp blinks to show seconds- it is illuminated on even seconds and off on odd seconds.
- The next two rows represent hours. The upper row represents 5 hour blocks and is made up of 4 red lamps. The lower row represents 1 hour blocks and is also made up of 4 red lamps.
- The final two rows represent the minutes. The upper row represents 5 minute blocks, and is made up of 11 lamps- every third lamp is red, the rest are yellow. The bottom row represents 1 minute blocks, and is made up of 4 yellow lamps.

# How to run app
In Android studio, create a new project using from version control option, provide git link.
```sh
https://github.com/2023-DEV2-016/BerlinClock.git
```
After successful gradle sync run the app in emulator or connected device.

You can also use CLI to build to install app
```sh
 ./gradlew installDebug
```


# Application Screens
![app_screen](https://github.com/2023-DEV2-016/BerlinClock/blob/master/berlinclock.gif "App Screen")


### Libraries
- [Jetpack](https://developer.android.com/jetpack)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manage UI data to survive configuration changes.
    - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow#stateflow) - is a state-holder observable flow that emits the current and new state updates to its collectors.
    - [Compose](https://developer.android.com/jetpack/compose) - Jetpack Compose is the modern toolkit for building native Android UI
- [Hilt](https://dagger.dev/hilt/) - A fast dependency injector for Android.

### Links
- [Berlin Clock Kata](http://agilekatas.co.uk/katas/BerlinClock-Kata) - Complete description about Berlin Clock Kata