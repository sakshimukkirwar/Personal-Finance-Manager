# Personal Finance Manager

## Overview
The Personal Finance Manager is a Java-based application designed to help users track and manage their expenses effectively. It allows users to add expenses with detailed descriptions, amounts, and dates, and provides functionalities to view all expenses, generate detailed financial summaries, and save data persistently in a CSV file.

## Features
- **Add Expense**: Users can input expenses with descriptions, amounts, and dates.
- **List Expenses**: Displays all recorded expenses.
- **Display Summary**: Provides a financial summary showing total expenditures, categorized by date.
- **Data Persistence**: Saves all expenses to a CSV file and retrieves them upon application startup, ensuring data is not lost between sessions.

## Technologies Used
- Java SE
- Java I/O
- Java `LocalDate` and `DateTimeFormatter` for date handling
- Exception Handling for robustness

## Usage
1. Run the application to start the main menu.
2. Select from the following options:
   - `1` to add a new expense.
   - `2` to list all expenses.
   - `3` to display the summary of expenses.
   - `4` to exit and save the session.
3. Follow the on-screen prompts to enter expense details or perform other actions.

## Data Format
- Expenses are saved in a CSV format: `description,amount,date`.
- Example entry: `Coffee,3.50,2023-01-08`.

## Setup
To set up and run this application:
1. Ensure you have Java installed.
2. Download the `.java` file and any associated data files.
3. Compile and run the application using a Java IDE or the command line.

This project is a practical tool for anyone looking to better manage their personal finances through a simple, user-friendly interface.

