# MQRGen — QR Code Generator

A Java-based QR Code Generator application that allows users to generate QR codes using a simple desktop interface.

MQRGen is distributed as a bundled executable JAR, so users can launch the application without manually configuring Maven or downloading individual Java libraries.

## ✨ Features

- Generate QR codes using a desktop application.
- Simple application launch using a Windows VBScript (`.vbs`) file.
- Bundled application JAR with dependencies.
- No need to install Maven to run the packaged application.
- Designed for Windows environments with a compatible Java runtime.

## 📦 Download

Download or clone this repository to your local machine.

The release package contains the following files:

```text
MQRGen/
├── MQRGen-1.0-SNAPSHOT-all.jar
├── run-hidden.vbs
├── README.md
└── LICENSE
```

> The JAR filename and launcher filename must match the names used in the repository. If the JAR is attached under GitHub Releases instead of committed to the repository, download it from the Releases section.

## 🚀 Getting Started

### 1. Install Java

Install a compatible Java runtime on your Windows computer.

This application is compiled for Java 11, so Java 11 or a compatible newer runtime is required.

Verify your Java installation by opening Command Prompt and running:

```bat
java -version
```

If Java is not installed, install a suitable JDK or runtime distribution before continuing.

### 2. Download the application

Download the repository as a ZIP file using **Code → Download ZIP**, or clone the repository:

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
```

Extract the ZIP file if you downloaded it.

### 3. Launch the application

Ensure these two files are in the same folder:

- `MQRGen-1.0-SNAPSHOT-all.jar`
- `run-hidden.vbs`

Double-click **`run-hidden.vbs`** to launch MQRGen.

The launcher starts the Java application without displaying a Command Prompt window.

**That's it!** You do not need IntelliJ IDEA, Maven, or the original source code to run the packaged application.

## 🛠️ Troubleshooting

### Application does not open

- Verify that Java is installed.
- Confirm that the JAR filename matches the filename referenced in `run-hidden.vbs`.
- Ensure that the JAR and VBS files are in the same folder.
- Check `startup.log` in the application folder if the launcher is configured to create one.
- If the application still fails to start, run the following command from Command Prompt in the application folder:

```bat
java -jar MQRGen-1.0-SNAPSHOT-all.jar
```

This displays startup errors that may be hidden when using the VBS launcher.

### Windows blocks the VBS file

Windows security settings may warn you before running a downloaded script. Only run the launcher after reviewing its contents and verifying that you trust the repository and its publisher.

### Missing Java libraries

The bundled JAR is intended to include the application's required Java dependencies. If a missing-class error appears, the packaged JAR may need to be rebuilt with the missing dependency.

## 👨‍💻 Development

### Requirements

- JDK 11 or a compatible development JDK
- Apache Maven
- IntelliJ IDEA or another Java IDE (optional)

### Build from source

Clone the repository:

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd MQRGen
```

Build the application:

```bash
mvn clean package
```

The executable JAR is expected at:

```text
target/MQRGen-1.0-SNAPSHOT-all.jar
```

The exact output depends on the Maven Shade Plugin configuration.

To run the application directly:

```bash
java -jar target/MQRGen-1.0-SNAPSHOT-all.jar
```

## 🧰 Technology Stack

- Java
- Maven
- ZXing QR Code library
- Maven Shade Plugin
- Windows VBScript launcher

## 🤝 Contributions

Suggestions, bug reports, and improvements are welcome. Please open an issue to discuss a proposed change before submitting a pull request.

---

**MQRGen — Simple QR Code Generation with Java.**
