# Demo Notes App

Simple notes android app for creating notes and storing notes using a clean architecture.

## Todo

Todos for the app

- Use coroutines
- Use [Dagger](https://dagger.dev/) and possibly [hilt](https://dagger.dev/hilt)
- Add [Room](https://developer.android.com/topic/libraries/architecture/room) for persistant storage of notes
  - Soft deletion and Archiving of notes
    - Quick undo toast
- Usage of [Arrow](https://arrow-kt.io/docs/0.10/apidocs/arrow-core-data/arrow.core/-either/)
- CI using [GitHub Actions](https://github.com/marketplace/actions/) for linting and testing before MR/PR
- Testing, testing, testing. 
  - Tier 1 [Espresso](https://developer.android.com/training/testing/espresso)
  - Tier 2 [Roboelectric](https://robolectric.org/)
  - Tier 3 [Spek](https://www.spekframework.org/) / [Mockito](https://site.mockito.org/) / JUnit test

### Future / Ideas

- [Firebase](https://firebase.google.com/) support for storing notes and logging in
  - Swipe to refresh
- Add badges on notes, for filtering of notes of different kinds
- Pinning of note (To keep it on the top)
- Fancy animation(s)?
- Switch out to use [compose](https://developer.android.com/jetpack/compose)
