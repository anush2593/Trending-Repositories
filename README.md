# Trending-Repositories

For the architecture, I've chose MVVM, because it was the most simple, yet clean one. Every layer has its own responsibility. In the viewmodel we have the business logic, repository helps to delegate calls to the API endpoints. 
Also I've tried to take into account SOLID principles, so that's why I have some abstractions. 
The technical stack is: Java, RxJava for threading, Retrofit for API calls, Jetpack Pagination, MVVM.
I've chosen RxJava, because it has a lot of advantages compared to AsyncTask, Threads and other threading options in Android. 

I didn't implement: Presenting the detailed screen, Adding to favorites, Creating a UI suitable for tablets and Search. 
I would choose MVI + Clean Architectural pattern instead of MVVM, if I had more time, the other things would possible remain the same. And I would prefer to do the task using Kotlin, instead of Java, because Kotlin has powerful library for asynchronous programming.
