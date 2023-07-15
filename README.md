# LerWord [MVVM,Koin,Room,Coroutines]
This repository is an example of the implementation of the MVP pattern on Android with Moxy.

## Screenshots
Screenshot of each window

<img src="app/src/main/res/raw/screenshot_login.jpg"  height="500"></img>
<img src="app/src/main/res/raw/screenshot_registration.jpg" height="500" ></img>
<img src="app/src/main/res/raw/screenshot_cardview.jpg" height="500"></img>


## Structure

#### Model
The model is presented in the form of two repositories that provide suspend functions for receiving data
- `UserRepository` - implemented using the Room library
- `WordsRepository` -implemented by a regular class, like a simulated database

#### View
View are classic activities
- `Activity` 
- `Adapter`
  
#### ViewModel
ViewModels contain the logic of their screens
 - `LoginViewModel`
 - `MainViewModel`
 - `SignUpViewModel`

## Koin dependencies
Dependencies are declared in modules from the Koin library
 - `DatabaseModule`
 - `DataModule`
 - `ViewModelModule`
