# JavaFX Library Management System

This project is a Java-based Library Management System using JavaFX for the graphical user interface. The application follows the MVC (Model-View-Controller) pattern and uses Maven for dependency management and build.

---

## Project Structure

```
ROOT
├── .idea/                # IDE configuration (IntelliJ) - not needed for deployment
├── README.md             # This file: documentation and usage instructions
├── hs_err_pid*.log       # Java crash logs (for debugging JVM crashes)
├── javaFX/               # Bundled JavaFX runtime, libraries, and legal documentation
│   ├── bin/              # Native DLLs for JavaFX (Windows)
│   ├── legal/            # JavaFX module licenses (LICENSE, ASSEMBLY_EXCEPTION, etc.)
│   ├── lib/              # JavaFX JARs (core libraries and modules)
│   └── src.zip           # (Likely) zipped JavaFX source code for reference
├── pom.xml               # Maven build and dependency configuration
├── replay_pid*.log       # JVM replay logs (for reproducing JVM errors)
├── src/
│   ├── main/
│   │   ├── java/         # Main Java source files
│   │   │   ├── Bibliothecaire.java
│   │   │   ├── ConnexionBDD.java
│   │   │   ├── Emprunt.java
│   │   │   ├── Etudiant.java
│   │   │   ├── Livre.java
│   │   │   ├── Main.java
│   │   │   ├── Sanction.java
│   │   │   ├── testDB.java
│   │   │   ├── Controller/   # Controllers for UI/business logic
│   │   │   ├── Model/        # Data/entity classes
│   │   │   └── dao/          # Data Access Object classes
│   │   └── resources/
│   │       ├── images/       # Application images
│   │       └── view/         # FXML or other view description files
│   ├── resources/
│   │   └── db/
│   │       └── library.sql   # SQL script for database schema
└── target/                   # Maven build output (compiled classes, JARs, etc.)
```

---

## Main Components

### 1. JavaFX Runtime (`javaFX/`)
- **bin/**: Native libraries (DLLs) for running JavaFX apps on Windows.
- **lib/**: Core JavaFX modules as JARs (controls, graphics, media, etc.).
- **legal/**: Legal documents for each JavaFX module.
- **src.zip**: JavaFX source (for reference/debugging).

### 2. Source Code (`src/main/java/`)
- **Main.java**: Application entry point.
- **Bibliothecaire.java, Etudiant.java, Livre.java, Emprunt.java, Sanction.java**: Represents entities (Librarian, Student, Book, Borrowing, Penalties).
- **ConnexionBDD.java**: Handles database connection.
- **testDB.java**: Used for testing database operations.
- **Controller/**: Contains controllers for UI and business logic.
- **Model/**: Data model/entity classes.
- **dao/**: Data Access Object classes for database operations.

### 3. Resources
- **images/**: Images for the application UI.
- **view/**: FXML or similar files for UI layout.
- **db/library.sql**: Database schema and initial data.

### 4. Build System
- **pom.xml**: Maven configuration for building the project, managing dependencies, and plugins.
- **target/**: Output directory for compiled code and build artifacts.

### 5. Other
- **.idea/**: IDE configuration, not required for running/building.
- **hs_err_pid*.log, replay_pid*.log**: Crash and replay logs for JVM error analysis.

---

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven
- (Optional) IntelliJ IDEA for development

### Build and Run

1. **Clone the repository:**
   ```sh
   git clone https://github.com/N-Aymane/java.git
   cd java
   ```

2. **Build the project:**
   ```sh
   mvn clean install
   ```

3. **Run the application:**
   ```sh
   mvn javafx:run
   ```

> **Note:** If you encounter issues with JavaFX modules, ensure the `javaFX/lib` JARs are on the module path or update your `pom.xml` as needed.

---

## Database Setup

- The SQL schema for the library is in:
  ```
  src/resources/db/library.sql
  ```
- Import or execute this script in your database before running the application.

---

## License

See the `javaFX/legal/` directory for licenses related to JavaFX modules.
Your own application code is under your chosen license.

---

## Contributing

Contributions are welcome! Please open issues or pull requests as needed.

---

## Troubleshooting

- **JVM Crash Logs:** If a file named `hs_err_pid*.log` or `replay_pid*.log` appears, this means the JVM has crashed. Examine these files for diagnostic information.
- **JavaFX Errors:** Ensure all required JavaFX libraries are available and properly referenced.

---

## Acknowledgments

- [JavaFX](https://openjfx.io/) for the UI framework.
- [Maven](https://maven.apache.org/) for build automation.
