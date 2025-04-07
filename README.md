
### 🍽️ Recipe Finder App

Discover a world of flavors! **Recipe Finder App** is a clean, modern Android application that allows users to **search and filter recipes** 
from the vast **Spoonacular** database. Whether you're looking for Italian pasta, vegan dishes, or just some culinary inspiration—this app has you covered.

---

## 📚 Table of Contents

- [🔍 About the App](#-about-the-app)
- [✨ Features](#-features)
- [🧱 Architecture Overview](#-architecture-overview)
    - [Layers](#layers)
    - [Design Principles](#design-principles)
- [🔌 Tech Stack](#-tech-stack)
- [📦 Project Structure](#-project-structure)
- [🚀 Getting Started](#-getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the App](#running-the-app)
- [🔐 API Integration](#-api-integration)
- [🖼️ UI Preview](#-ui-preview)
- [🧪 Testing](#-testing)

---

## 🔍 About the App

The **Recipe Finder App** lets users:

- Search for recipes by entering a keyword (e.g., "chicken", "pasta")
- Apply filters for **Cuisine** (e.g., Italian, Indian) and **Category** (e.g., Dessert, Main Course, Breakfast)
- View a clean list of matching recipes with images and names
- Explore results retrieved from the [Spoonacular API](https://spoonacular.com/food-api)

Built with scalability and maintainability in mind, this app demonstrates modern Android development using **Clean Architecture**, **Kotlin Coroutines**, and **Dependency Injection with Koin**.

---

## ✨ Features

- 🔎 Real-time recipe search
- 🧾 Filter support for:
    - Cuisine types (e.g., Thai, Mexican)
    - Meal categories (e.g., Appetizer, Snack)
- 📋 Scrollable list of recipe results
- 📡 API integration using Retrofit
- ⚙️ Reactive UI using Kotlin Flows/Coroutines
- 🧪 Modular and testable architecture

---

## 🧱 Architecture Overview

This project follows the **MVVM** pattern with a **Clean Architecture** separation of concerns:

### Layers

- **Presentation**: UI components (activities/fragments), ViewModels, and user interaction logic.
- **Domain**: Contains business logic via use cases and interfaces for data.
- **Data**: Handles data sources, API integration, and repository implementations.

### Design Principles

- SOLID principles
- Clear separation of concerns
- Dependency inversion for testability
- Use cases as the bridge between ViewModel and repository

---

## 🔌 Tech Stack

| Layer        | Tech Used                 |
|--------------|---------------------------|
| Language     | Kotlin                    |
| Architecture | MVVM + Clean Architecture |
| Networking   | Retrofit                  |
| DI           | Koin                      |
| Async        | Coroutines                |
| Parsing      | Moshi                     |
| API          | Spoonacular               |
| UI           | Jetpack Compose           |

---

## 📦 Project Structure

```plaintext
📁 data
 ├── service/             # Retrofit services
 ├── remote/dto/          # Data models (DTOs)
 ├── repo/                # Repository implementations

📁 domain
 ├── model/               # Domain entities
 ├── repo/                # Repository interfaces
 └── usecases/            # Business logic

📁 presentation
 ├── ui/                  # UI screens & composables
 ├── view_states/         # Data classes for updating ui states
 └──                      # Activities and view models   
 
📁 di                     # Koin module declarations

📁 helper                 # Utility class
```

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- Android Studio Giraffe (or higher)
- Kotlin 1.9+
- Internet connection for fetching recipes
- Spoonacular API Key

---

### Installation

1. **Clone the repository**  
   ```bash
   git clone https://github.com/yogesh-rts/recipe-finder-app.git (HTTP)
   git clone git@github.com:yogesh-rts/recipe-finder-app.git (SSH)

2. **Open in Android Studio**

3. **Add your API key**
Create a file in local.properties:
- SPOONACULAR_API_KEY= **your_actual_api_key_here** Or
- Inject it securely via BuildConfig or Dependency Injection (recommended for production)

4. **Build and run the app**


### Running the App
   
- Connect a physical device or start an emulator
- Click the Run ▶️ button in Android Studio

### 🔐 API Integration

This app uses the Spoonacular Recipe API to fetch:
- 🔍 Search results
- 🎯 Filtered recipes based on cuisine and category

All networking is handled using Retrofit, with endpoints defined in:
 - data/service/recipes/Recipes.kt

### 🖼️ UI Preview
(Screenshots coming soon!)



