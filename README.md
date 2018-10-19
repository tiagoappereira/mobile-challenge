# Unbabel

Thank you for taking the time to apply to Unbabel. 😄

To make the whole process more inclusive, we provide three options for applying. Mind you, you only need
to submit one. Choose what suits you best:

1. [Do the Unbabel Demo Project](#unbabel-project)
2. [Send us a project you've already done](#self-project)
3. [Show us some open source work you did](#open-source)

Please read each one carefully and pick the one you feel most comfortable with.

If everything goes well the next step of the process will be an on-site interview. It'll start with a discussion around your project. We normally ask you to implement a new feature (even if it's pseudo-code), to refactor and unit-test a particular component, and in general we aim to have an informal discussion about your approach.

Next is usually a question around iOS architecture (i.e. how to implement an often encountered feature).

Our main aim is to understand how well we would work with you and you with us, so this is also a chance for you to learn about us. This pair programming / interview session usually lasts from one to two hours.

Above all, be yourself! 🌈

## Unbabel Demo Project <a name="unbabel-project"></a>

From a high level point of view the demo consists of a list of posts, where each post has its own detail.

#### Posts Screen

A post has a title and it's up to you how to display it. To retrieve the posts, you can use the following:

* http://jsonplaceholder.typicode.com/posts

When a post is tapped, you should go to the detail screen.

#### Detail Screen

A post detail screen, will have:

* Its author.
* Its description (via the `body`).
* Number of comments it has.

You can retrieve the remaining information from:

* http://jsonplaceholder.typicode.com/users
* http://jsonplaceholder.typicode.com/comments

#### Requirements

The following requirements should be met:

* Use Swift 4.0 for iOS.
* The information (posts and post details) should be available offline. It's assumed that if it's the first time you are accessing the app, and you are offline, you shouldn't see anything.
* The code should be production grade.
* It should compile and run.

## Already Written Project <a name="self-project"></a>

We would be happy if you would submit a project you already have (e.g. potentially a demo project for another company). Still the project has to obey to the following requirements:

* Use Swift 4.0 for iOS.
* It should have at least two distinct network calls.
* It should parse the network response and present the information to the user.
* It should have some sort of persistence mechanism.
* It should compile and run.
* **Ideally** it should have a point of synchronization (e.g. making two concurrent requests and waiting for both of them to finish)


If you have a project with these requirements then perfect! Please ensure you have a description of what the project does, in order to give us some context. ❤️

## Open Source Work <a name="open-source"></a>

We would like to see a **non-trivial** pull request you have done to a public open source project. This should be something you are proud and where you show your technical skills. **It should also be related to iOS development**: it should be aligned with what you will do on a day-to-day basis. 😊 ✨ 🌳

---
<br>

## General Tips/Advice

* We like code that is simple, [but simple is different than easy](https://www.infoq.com/presentations/Simple-Made-Easy).
* Keep in mind the [SOLID principles](https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)) when doing the project.
* We did leave out of the requirements if you should try to download everything (posts + each post detail). This is up to you to decide and justify why you did so.
* Testing is very important for us. Even if you don't write a single test (e.g. because of time constraints), your app should be easy to test (we love [Dependency injection](https://en.wikipedia.org/wiki/Dependency_inversion_principle)).
* Error scenarios should be taken into consideration as well and how easy is to add them, even if you don't explicitly show them (e.g. using an `UIAlertController` ).
* Although UI and UX are important, in this demo, we are more concerned on how you architect your application and your thought process. It should take into consideration features that might be added in the future.
* You can use any 3rd party library you wish (Alamofire/Retrofit, RxSwift/RxJava, Realm, etc). In case you do, be prepared to justify why you used it. You can use any dependency manager you want, just please don't drag and drop code around.
* Be consistent with your code. We advise using something like [raywenderlich's swift style guide](https://github.com/raywenderlich/swift-style-guide) while doing the demo. It's absolutely fine to use any other style, as long as you are consistent.
* Clean the file project structure (if you are creating one from scratch) and remove any unused methods. This shows attention to detail.

#### Thanks for your time, we look forward to hearing from you!

The [Unbabel Mobile team](https://gitlab.com/Unbabel/)
