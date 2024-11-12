
# Weather Compose 🌤️

## Overview
Weather Compose is built with Jetpack Compose and features real-time weather data, location search, animations, and an intuitive UI.

### Features
- **Hourly and Daily Forecasts**: Displays up-to-date weather data with animated icons.
- **City Search**: Search for and save locations with weather data.
- **Swipe-to-Dismiss**: Interactive widget for easy item management - remove saved locations easily.
- **Custom Themes**: Gradient backgrounds and icon scaling for a unique look.
- **Offline Support**: Data persistence through Room, allowing cached results for offline access.



### Technologies Highlights
- **Jetpack Compose**: UI built using Compose for declarative, responsive, and efficient development.
- **MVVM Architecture**: Separates business logic using ViewModels and a repository pattern.
- **Dependency Injection**: Integrated with Hilt for simplified DI.
- **API Integration**: Fetches live weather data from Visual Crossing API
- **Room**: Persistent storage for offline functionality.
- **Retrofit & Coroutines**: Efficient network calls to fetch weather data.
- **Testing**: Unit tests with JUnit, UI tests with Compose Testing  &  End to End testing with Espresso to be added
- **Code Coverage**: Utilized JaCoCo to monitor code quality and test coverage.

## Development Setup

### Prerequisites
API Key for [Visual Crossing Weather API](https://www.visualcrossing.com)

### Project Structure
The project follows a modularized architecture with key components:

- `UI`: Jetpack Compose components.
- `Data`: Room database for caching and a Retrofit client for network operations.
- `Domain`: Business logic and ViewModels.

### Tests
Tests are written for both the UI and business logic:
- **Unit Tests**: Ensures core functionality of ViewModels and Repositories.
  

## Future Improvements
- **UI Tests**: Validates composable UI elements using Compose Testing
- **E2E Test**: Validates the entire flow using Espresso
- **Code Coverage Badge**: Enable GitHub Actions workflow and generate a code coverage badge using JaCoCo

## Demo
Watch a demo of the app in action [here](https://drive.google.com/file/d/1UmkVOtgMlJmjPS0wcieiDjwIWhL5G4C9/view?usp=sharing)

## User flow
![High Level User Flow](assets/Screen%20Flow.png)



