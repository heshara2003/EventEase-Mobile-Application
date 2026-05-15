# EventEase 📅

EventEase is a modern, real-time Android application designed to help users manage their schedules, meetings, and personal events with ease. Built with **Java** and integrated with **Google Firebase**, the app provides a seamless experience for tracking event statuses and ensuring productivity.

---

## 🌟 Key Features

### 🔐 Secure User Access
*   **Firebase Authentication:** Secure Sign-Up and Login using Email and Password.
*   **Persistent Sessions:** Smart Splash Screen logic that keeps you logged in.
*   **User Profiles:** View your account details and manage your identity within the app.

### 📝 Smart Event Management
*   **Real-time Database:** Powered by **Cloud Firestore**. Add, update, or delete events and see changes instantly across devices.
*   **Intuitive Input:** 
    *   **Date Picker:** Calendar view for easy date selection.
    *   **Interactive Clock:** A matching analog-style Time Picker for precise scheduling.
*   **Status Indicators:** Track events with statuses like **Pending**, **Accepted**, or **Declined**, featuring dynamic color-coding.
*   **Confirmation Dialogs:** Safety prompts for sensitive actions like deleting events or signing out.

### 🎨 Premium UI/UX
*   **Material Design 3:** A clean, professional look following modern Android standards.
*   **Popup Menu Navigation:** An organized top-right menu for User and Developer information, keeping the main dashboard focused.
*   **Optimized Lists:** Smooth scrolling and efficient data rendering using `RecyclerView` and `CardView`.

---

## 🛠 Tech Stack

*   **Language:** Java
*   **Development Environment:** Android Studio
*   **Backend & Cloud:** 
    *   Firebase Authentication (User Management)
    *   Cloud Firestore (Real-time NoSQL Database)
*   **UI Framework:** Material Design Components
*   **Connectivity:** Real-time synchronization via Internet & Network State API

---

## 🚀 Installation & Setup

1.  **Clone the Repo:**
    ```bash
    git clone https://github.com/yourusername/Eventease.git
    ```
2.  **Firebase Configuration:**
    *   Go to [Firebase Console](https://console.firebase.google.com/).
    *   Create a project and register the app with package name `com.example.eventease`.
    *   Download `google-services.json` and place it in the `app/` directory of the project.
    *   Enable **Email/Password Auth** and **Cloud Firestore** in the Firebase settings.
3.  **Build & Run:**
    *   Open the project in **Android Studio**.
    *   Sync Gradle and click the **Run** button to install on an emulator or physical device.

---

## 👨‍💻 About the Developer

**Heshara Sandeepa**  
**Student No:** 2023t01866  

Developed as a project to demonstrate proficiency in Android application architecture, UI design, and cloud-based backend integration.

---

## 📄 License

Copyright © 2024 EventEase. This project is licensed under the **MIT License**.
