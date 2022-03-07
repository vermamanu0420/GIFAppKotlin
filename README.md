
<h1 align="center">GIF App (Kotlin)</h1>

## App Architecture 

This app uses MVVM architecture (Model View ViewModel) which is also recommended by google which facilitates the separation between the view and the business logic or the backend, Here viewmodel is responsible for exposing only the data objects(LiveData oe Mutable data) to the view and view responds to the changes to the data rather than caring about the businesslogic. 

#### Components of MVVM:

* **Model:** This layer is responsible for the abstraction of the data sources. Model and ViewModel work together to get and save the data.

* **View:** The purpose of this layer is to inform the ViewModel about the user’s action. This layer observes the ViewModel and does not contain any kind of application logic.

* **ViewModel:** It exposes those data streams which are relevant to the View. Moreover, it servers as a link between the Model and the View.


## Libraries used

* **Dagger2:** Dagger2 for dependency injection. Dagger here help us in automatic code generation by generating or injecting the dependencies for us into the class that requires those dependencies. Dagger handles all the instance creation and injection for us.

* **Retrofit:**  Retrofit is a popular and fast Library for remote server communication. Retrofit supports both synchronous, asynchronous network requests and also support dynamic URLs. It also caches responses to avoid sending duplicate requests and provide better user experience.

* **Glide:** Used Glide for seamless image/gif loading. Glide is a fast and efficient open source media management and image loading framework for Android. Glide handles all the download proces and memory caching as well to avoid repeating downloads.

* **RxJava:** RxJava is a JVM library that uses observable sequences to perform asynchronous and event-based programming. Its primary building blocks are Observer, and Observables and I used them to complete asynchronous tasks to observe the Api data list and update the view when ever there were changes in the observables in our project.

* **Room:** Room is one of the Jetpack Architecture Components in Android. This provides an abstract layer over the SQLite Database to save and perform the operations on persistent data locally.

* **Coroutine:** 

