#### MoviesApp

Listing movies and showing the detail

## Table of contents
- [Tech Stack](#tech-stack)
- [Open project and install](#open-project-and-install)
- [Code guidelines](#code-guidelines)
- [Build Variants](#build-variants)
- [Architecture](#architecture)
- [Recommendations](#recommendations)
- [Next Steps](#next-steps)
- [ScreenShoots](#screenshoots)

### Tech Stack

- ***Languages:*** Kotlin, ***Version:*** 1.9.24
- ***Libraries:***
    - ***Networking***
        - Retrofit: A type-safe HTTP client for making API calls. For more details, visit:
            - https://github.com/square/retrofit/releases
            - https://github.com/square/retrofit/tree/trunk/retrofit-converters/kotlinx-serialization
        - Coroutines: Used for managing background threads and asynchronous programming. For more
          details, visit:
            - https://github.com/Kotlin/kotlinx.coroutines/releases
        - Serialization: For serialization of requests and responses to communication with the backend.
          For more details, visit:
            - https://github.com/Kotlin/kotlinx.serialization/releases
        - OkHttp. For more details, visit:
            - https://square.github.io/okhttp/changelogs/changelog/
- Secrets Gradle Plugin: A Gradle plugin for providing your secrets securely to your Android
  project. For more details, visit:
    - https://github.com/google/secrets-gradle-plugin
- [Detekt](#detekt): a static code analysis tool for the Kotlin programming language. It is already
  included in the project. You could install Detekt plugin to facilitate the usage. For more details, visit:
  - https://detekt.dev/docs/intro
  - https://plugins.jetbrains.com/plugin/10761-detekt

### Open project and install

- In Android Studio, click on File in the top menu.
- Select Open from the dropdown.
- Navigate to the project directory (the root folder of the project where the app module and
  build.gradle file reside).
- Select the root directory of the project and click OK.

#### Installation

Don not forget to create/add the secret file (secrets.properties) in the root of the project, also
you should add the google-services.json file in the app folder.
You could request the files to: [Alejandra Toledano](mailto:toledanomarino@gmail.com) (also there
are included in the email that was already sent)



### Code guidelines

Following https://developer.android.com/kotlin/style-guide

### Build Variants

Build variants for different environments allow you to create separate versions of the app for
staging, and production. This helps in testing and deploying the app in different stages.

As a good practice, we always keep this kind of task automate on the Gradle file


### Architecture

#### Overview

This app follows a Clean Architecture approach with MVVM (Model-View-ViewModel) principles to ensure a well-structured and maintainable codebase.

![image](https://github.com/user-attachments/assets/1cece204-f73d-44d1-b959-62708c06f1e7)

Components of the diagram

- UI (User Interface): This is the part of the app that users interact with (screens, buttons, etc.).
- ViewModel: It acts as an intermediary between the UI and the rest of the app. It prepares data for the UI and handles user interactions.
- Use Cases: These are the actions the app can perform.
- Repository: It's like a central hub for data. It decides whether to fetch data from the network (API) or local storage (database).
- API: This represents a remote server that provides data to the app.
- Local Database: This is where the app can store data on the device for offline use or caching.

**How it all works together**

- The UI requests data or actions from the ViewModel.
- The ViewModel uses Use Cases to fulfill those requests.
- Use Cases get the necessary data from the Repository.
- The Repository fetches data from the API or the Local Database.
- The data flows back up the chain to be displayed in the UI.

  

### Recommendations
- **Testing**: Write unit and integration tests to ensure code quality and reliability.
- **Security**: Follow best practices for securing sensitive user data, such as using encryption and secure authentication methods.
- **Code Reviews**: Regularly conduct code reviews to maintain code quality and share knowledge.
- **Documentation**: Keep the documentation up-to-date with any new features or changes in the codebase.
- **Continuous Integration**: Use CI tools to automate builds and tests, ensuring that the codebase remains stable.

  

### Next Steps
Here are some potential features and improvements planned for future releases:
- **User Authentication**: Implement user login
- **Use local storage**: Use local storage to avoid do multiples request to the API
- **Use genres endpoint**: Use genres endpoint to get them
- **Dependency injection**: Implement Hilt for dependency injection.
- **Improve Flow implementation**: Improve Flow/StateFlow implementation to manage different customs states, in order to manage them better
- **Add testing**: Implement unit tests

### Screenshots

![image](https://github.com/user-attachments/assets/b4fdc2ea-6824-4359-b042-ede85865bcac)

![image](https://github.com/user-attachments/assets/bb7bf369-a9a7-4e10-86b8-36a3d5fc6609)

![image](https://github.com/user-attachments/assets/26ffe8ad-d6b1-4d31-9a02-a885cca36672)






