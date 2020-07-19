# Demo Notes App

Simple notes android app for creating notes and storing notes using a clean architecture.

## Todo

Todos for the app

- [x] Use coroutines
- [x] Use [Dagger](https://dagger.dev/) and possibly [hilt](https://dagger.dev/hilt)
- [x] Use shortcut with deeplink to add notes
- [x] Add [Room](https://developer.android.com/topic/libraries/architecture/room) for persistant storage of notes
  - Soft deletion and Archiving of notes
    - Quick undo toast
- [ ] Usage of [Arrow](https://arrow-kt.io/docs/0.10/apidocs/arrow-core-data/arrow.core/-either/)
- [x] CI using [GitHub Actions](https://github.com/marketplace/actions/) for linting and testing before MR/PR
- [ ] Testing, testing, testing. 
  - Tier 1 [Espresso](https://developer.android.com/training/testing/espresso)
  - Tier 2 [Roboelectric](https://robolectric.org/)
  - Tier 3 [Spek](https://www.spekframework.org/) / [Mockito](https://site.mockito.org/) / JUnit test
- [ ] Multi module app

### Future / Ideas

- [ ] [Firebase](https://firebase.google.com/) support for storing notes and logging in
  - [Swipe to refresh](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
- [ ] Add badges on notes, for filtering of notes of different kinds
- [ ] Pinning of note (To keep it on the top)
- [ ] Fancy animation(s) with [MotionLayout](https://developer.android.com/training/constraint-layout/motionlayout)
- [ ] Switch out UI and use [compose](https://developer.android.com/jetpack/compose)

# License

See [LICENSE.md](LICENSE.md)
