# Android-MVVM-Example ( ViewModel and LiveData )


This is a simple app that presents the user with a scrollable list of Text/images from Rest API. The app doesn't require any <a href="https://developer.android.com/training/permissions/requesting">dangerous permissions</a> or to logging in.
This app uses <a href="https://developer.android.com/topic/libraries/architecture/ ">android architecture components</a> ( ViewModel and LiveData). The code has been kept modular, extensible, and testable by using interfaces for all third-party libraries and MVVM design pattern. This extensibility and modularity greatly help in swapping various concrete implementations and eases app maintenance.

I look forward to your feedback and comments on the code, architecture improvements and/or any general suggestions.

Getting started
1. Open the project in Android Studio
Select Open Existing Project from the options. To install Android Studio please follow the instructions <a href="https://developer.android.com/studio/install">here</a> 

2. Running project on a device
After the project has been successfully imported it will build itself automatically. Connect the mobile device and press the Run 'app' button from Run menu. Please also note the min supported API level is 15 (Android 4.0.3 - 4.0.4, Ice Cream Sandwich).


Third-party Libraries used

Retrofit - https://github.com/square/retrofit

Butterknife - https://github.com/JakeWharton/butterknife

Glide - https://github.com/bumptech/glide

Dagger 2 - https://google.github.io/dagger/

gson - https://github.com/google/gson
