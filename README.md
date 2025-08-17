# Task Tracker CLI

A simple **Command Line Interface (CLI) Task Tracker** built in Java.  
This project allows you to create, update, delete, and manage your tasks directly from the terminal.  
Tasks are stored in a local JSON file, ensuring persistence across sessions.

---

## ✨ Features
- Add new tasks
- Update existing tasks
- Delete tasks
- Mark tasks as **in-progress** or **done**
- List all tasks
- List tasks by status:
    - ✅ Done
    - ⏳ In Progress
    - 📝 To Do

---

## 📂 Task Properties
Each task contains the following properties:
- **id**: Unique identifier
- **description**: Short description of the task
- **status**: One of `todo`, `in-progress`, or `done`
- **createdAt**: Date and time when the task was created
- **updatedAt**: Date and time when the task was last updated

---

## 🚀 Getting Started

### Prerequisites
- [Java 17+](https://adoptium.net/) installed
- A terminal or command prompt

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/SouzaTalles/task-tracker-cli.git
   cd task-tracker-cli
   ```
2. Compile the project:
   ```bash
   javac Main.java
   ```
3. Run the CLI:
   ```bash
   java Main <command> [arguments]
   ```

---

## 🛠️ Usage

### Add a new task
```bash
java Main add [description]
```

### Update a task
```bash
java Main update [id] [description]
```

### Delete a task
```bash
java Main delete [id]
```

### Mark a task as in progress
```bash
java Main mark-in-progress [id]
```

### Mark a task as done
```bash
java Main mark-done [id]
```

### List all tasks
```bash
java Main list
```

### List tasks by status
```bash
java Main list done
java Main list todo
java Main list in-progress
```

### Help
``` bash
java Main help
```

---

## 📁 Data Storage
- Tasks are stored in a local file called `tasks.json` in the project directory.
- The file will be created automatically if it doesn’t exist.

---

## ⚠️ Error Handling
- Invalid task IDs will return a clear error message (e.g., `[ERROR] No task found with ID 5`).
- Missing or invalid arguments will print usage instructions.

---

## 📌 Future Improvements
- Add search by keyword
- Add sorting by `createdAt` or `status`
- Add unit tests

---

## 🧑‍💻 Author
Developed by **Talles Souza** as a practice project for learning file handling, user input, and CLI applications in Java.  

---

## URL Project
[Roadmap project](https://roadmap.sh/projects/task-tracker)