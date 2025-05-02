# D\&D Session Manager

Session manager for Dungeons & Dragons (D\&D) that streamlines your tabletop campaigns, making it easy to track characters, sessions, and story progress in one place.

---

## 🚀 Features

* **Player Management**: Create, edit, and organize player characters with ease.
* **Session Tracking**: Log sessions, session notes, and key events.
* **Character Stats & Inventory**: Keep track of level, experience, hit points and loot all in one dashboard.
* **Authentication & Security**: Powered by Firebase Auth for secure login.

---

## 🔧 Prerequisites

Before you begin, ensure you have met the following requirements:

* [Docker & Docker Compose](https://docs.docker.com/compose/install/) installed on your machine.
* A Firebase project with Authentication enabled. You can follow the [Firebase Auth setup guide](https://firebase.google.com/docs/auth).

---

## 🏗️ Installation

1. **Clone the repository**

   ```bash
   git clone git@github.com:kavunchick/dnd-session-manager.git
   cd dnd-session-manager
   ```

2. **Configure environment variables**

   1. Create a `.env` file in the `frontend/` directory.

   2. Open `frontend/.env` and add your Firebase credentials:

      ```dotenv
      VITE_API_KEY=your_api_key_here
      VITE_AUTH_DOMAIN=your_auth_domain_here
      VITE_PROJECT_ID=your_project_id_here
      VITE_STORAGE_BUCKET=your_storage_bucket_here
      VITE_MESSAGING_SENDER_ID=your_messaging_sender_id_here
      VITE_APP_ID=your_app_id_here
      ```

   3. In `backend/src/main/resources/application-prod.properties`, set the `token.issuer` to your Firebase project URL:

      ```properties
      mp.jwt.verify.issuer=https://securetoken.google.com/your_project_id
      ```

3. **Start the application**

   ```bash
   docker compose up --build
   ```

   > The backend API will be available at `http://localhost:8080` and the frontend at `http://localhost:5173` by default.

---

## 🎮 Usage

1. **Login**

   * Use your Firebase credentials to sign up or log in.
2. **Create Characters**

   * Navigate to the **Character list** section and click on empty card.
3. **Manage Sessions**

   * In the **Manage Session** tab, log a new session, and assign participating characters.
4. **Track Progress**

   * View comprehensive character dashboards.

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork this repository.
2. Create a new branch (`git checkout -b feature/YourFeatureName`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeatureName`).
5. Open a Pull Request.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute it as you see fit.
