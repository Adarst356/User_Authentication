#ScreenSHots of App

![Home_screen](https://github.com/user-attachments/assets/e286b04e-f166-47d2-8129-4e5b4bbfe4ab)                      ![login](https://github.com/user-attachments/assets/1b54054b-eb4f-4d5d-a629-2fac8b6fd9bd)



![Register_Authentication](https://github.com/user-attachments/assets/057a8bf1-170d-4540-b01b-56cb5f95f271)            ![Dashbord](https://github.com/user-attachments/assets/4e25a9d6-bcf5-4752-afe7-dc733765fc35)



# UserAuthentication App 🚀

This is a simple Android app built using **Java**, **Room Database**, and **Fragments**. It allows users to **register** with full name, email, and mobile number, and then **login** with their credentials. The app uses local database storage with Room and includes a dashboard with bottom navigation.

## 🔧 Features

- 📝 User Registration (Full Name, Email, Mobile,Password and Confirm Password,address)
- 🔐 User Login with Room DB validation
- 🚫 Prevents duplicate email registration
- 📂 Local storage using Room Database
- 🧭 Dashboard using BottomNavigationView and Fragments

## 🛠 Built With

- Java
- Android Studio
- Room Persistence Library
- ViewPager + Fragments
- ConstraintLayout
- Material Components

## 📁 Folder Structure
├── java
│ └── com.devdroid.task6
│ ├── Activity (Login, Register, Dashboard)
│ ├── Adapter (ViewPager / Fragment Adapter)
│ ├── Database (Room Setup)
│ └── Fragments (Home, Profile, etc.)
├── res
│ ├── layout
│ ├── drawable
│ ├── font
│ └── menu

## 💾 Database Schema (Room)
**User Table**
- `id` (Int, Primary Key)
- `fullName` (Text)
- `email` (Text)
- `mobile` (Text)
- `address`(Text)

## ▶️ How to Run

1. Clone the repository
2. Open in Android Studio
3. Build and run on Android device or emulator
4. Register a user, then login with the same credentials

## 📄 License

This project is open-source and free to use.


##ScreenShot Of RoomDatabse
![Screenshot 2025-06-10 103222](https://github.com/user-attachments/assets/12ff418f-d610-44d5-b313-c68b29a5c30c)





