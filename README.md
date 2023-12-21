# IwakGo Mobile Development (MD)

This is a repository of IwakGo source code for building Android Application, IwakGo is an application that can help you to check condition of fish aiming for fish health and fish farming.

## Table of Contents
- [User Interface](#user-interface)
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Dependencies](#dependencies)
- [Getting Started](#getting-started)
- [Features](#features)

## User Interface


## Architecture
The project follows the MVVM (Model-View-ViewModel) design pattern. 
The MVVM pattern promotes a clear separation of concerns between the UI and the business logic.

## Requirements
To build and run the IwakGo Android application, ensure you have the following software installed:

- Android Studio: 2022.2.1 (Flamingo)
- Minimum SDK: 24
- Target SDK: 34
- JDK: 17
- Kotlin: 1.8.10
- Android Gradle Plugin: 8.1.0
- Gradle: 8.1.1

## Dependencies
The project utilizes several libraries and frameworks to enhance its functionality. Here are the key dependencies:

- [Material 3](https://m3.material.io/): Provides a modern and consistent design system for the app's UI, ensuring a visually appealing user experience.

- [Jetpack Compose](https://developer.android.com/jetpack/compose?gclid=CjwKCAjwg-GjBhBnEiwAMUvNW3yzekVX4nip-iL9Zw-ANUPQ_4eFDIJ0NU5Do0dTMhZCX6caIh3J8BoCdgoQAvD_BwE&gclsrc=aw.ds&hl=id): Enables building the UI using a declarative and efficient approach, allowing for flexible and responsive user interfaces.

- [Firebase Auth](https://firebase.google.com/docs/auth/android/google-signin?hl=id): Provides authentication and Google Sign-In capabilities, ensuring secure user authentication and access to app features.

- [Google Play Services](https://developers.google.com/android/guides/setup): Offers additional services and APIs for integrating with various Google features, enhancing the app's functionality and user experience.

- [Coil Compose](https://coil-kt.github.io/coil/compose/): A Kotlin-first image loading library for handling image loading and caching within Compose UI, enabling efficient and seamless image loading.

- [Dagger Hilt](https://dagger.dev/hilt/): A dependency injection framework that simplifies the management of dependencies in the app, promoting modularity and testability.
  Apologies for the cutoff. Here's the rest of the information:

- [Retrofit](https://square.github.io/retrofit/): A type-safe HTTP client for making network requests and interacting with RESTful APIs, facilitating seamless communication with backend services.

- [Kotlin Flow](https://developer.android.com/kotlin/flow?hl=id): Provides a streamlined way to handle asynchronous data streams in a reactive programming style, enabling efficient and responsive data handling in the app.

## Getting Started
To build and run the project locally, follow these steps:

1. Clone the repository: `git clone https://github.com/IwakGoBangkit/IwakGo_MD.git`
2. Open the project in Android Studio.
3. Ensure that the required SDK versions and dependencies are installed.
4. Add your own `google-services.json` file to the project. This file is required for Firebase integration. You can obtain this file by creating a new Firebase project and enabling Firebase Authentication.
5. Build and run the app on an emulator or physical device.

Feel free to explore the codebase and documentation for more detailed information on the project.

## Features
The IwakGo Android application offers the following key features:

- User authentication using Firebase Auth, allowing users to create accounts and securely log in.
- Seamless image loading and caching with Coil Compose, ensuring fast and efficient image rendering within the app.
- Dependency injection with Dagger Hilt, promoting modularity and testability of the codebase.
- Camera functionality using Camera X, enabling users to capture and work with photos within the app.
- Integration of TensorFlow Lite for incorporating machine learning models, providing advanced food detection and human pose estimation.
- Data storage and retrieval with Room, offering a reliable and efficient database solution for managing app data.
- Network requests and API interactions facilitated by Retrofit, ensuring seamless communication with backend services.
- Asynchronous data handling with Kotlin Flow, allowing for responsive and reactive programming.
- Media playback using ExoPlayer, providing a powerful and customizable media player for playing audio content within the app.

Please refer to the codebase and documentation for more detailed information on each feature.
