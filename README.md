# 📱 Palindrome Checkber App

An Android application that demonstrates modern Android development practices using Material 3, Retrofit, and Jetpack Navigation.
This project was built as a learning showcase for implementing clean UI logic, API integration, and navigation between multiple screens.

---

## ✨ Features

- ✅ **Palindrome Checker**  
  Checks whether the entered text is a palindrome.

- 🧑 **Name Input Field**  
  Simple form field for name input.

- 🔀 **Screen Navigation**  
  Navigate between three screens using the Jetpack Navigation component.

- 🌐 **User List via API**  
  Fetches user data from Reqres.in API using Retrofit.

- 🔄 **Pull to Refresh**  
  Swipe down to refresh user data.

---

## 🛠️ Tech Stack

| Category        | Technology Used                         |
|-----------------|------------------------------------------|
| Language        | Kotlin                                   |
| UI Framework    | Material 3 + ConstraintLayout            |
| Networking      | Retrofit (with static `x-api-key` header)|
| Architecture    | MVVM-lite / Fragment-based Navigation    |
| API             | [Reqres.in](https://reqres.in/)          |
| State Mgmt      | LiveData + ViewBinding                   |

---

## 🚀 Screens

1. **First Screen**
   - Input Name
   - Input Palindrome
   - Buttons: `Check` and `Next`

2. **Second Screen**
   - Shows name from previous screen
   - Displays selected user from third screen

3. **Third Screen**
   - Lists users from API
   - Pull to refresh
   - Click to choose a user

---

## 🔗 API Usage

- Uses [`https://reqres.in/api/users`](https://reqres.in/api/users) to fetch user data.
- Includes static header: `x-api-key: reqres-free-v1` (not hidden/encrypted).

---

## 📦 Build & Run

### Requirements
- Android Studio Hedgehog or newer
- Android device or emulator (API 21+)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/samlehoy/Suitmedia_Intern_Test.git
