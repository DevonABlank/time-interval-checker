# Time Interval Checker

A JavaFX desktop application for calculating time intervals between two times, with support for both 12-hour and 24-hour formats.

## Features

- **Dual Time Format Support**: Works with both 12-hour (AM/PM) and 24-hour formats
- **Time Interval Calculations**: Calculate the duration between two times
- **Custom Exception Handling**: Robust input validation with custom `InvalidTime` exception
- **Immutable Design**: Thread-safe `Time` and `Interval` classes using immutable patterns
- **User-Friendly GUI**: Intuitive JavaFX interface with real-time validation

## Technical Highlights

- **Object-Oriented Design**: Implements encapsulation, immutability, and single responsibility principle
- **Exception Hierarchy**: Custom exception class for handling invalid time inputs
- **Defensive Programming**: Input validation and error handling throughout
- **Maven Project Structure**: Standard Java project organization

## Technologies

- Java
- JavaFX
- Maven
- Object-Oriented Programming principles

## Project Structure
```
src/main/java/umgc/assignments/project3/
├── Project4.java          # Main application with JavaFX GUI
├── Time.java              # Immutable Time class
├── Interval.java          # Immutable Interval class
└── InvalidTime.java       # Custom exception class
```

## Usage

This application allows users to:
1. Enter two times in either 12-hour or 24-hour format
2. Calculate the interval between them
3. Receive immediate feedback for invalid inputs

## Academic Context

Developed as the final project (Project 4) for CMSC 215 - Intermediate Programming (Java) at UMGC, demonstrating advanced object-oriented programming concepts including immutable class design, custom exceptions, and GUI development.

## License

MIT License
