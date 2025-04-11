
---

#### **Project Overview**
The **Library Management System** is a comprehensive Java-based application designed to automate the process of managing books, users, and transactions in a library. The system offers functionalities for administrators and users to interact with the library database efficiently. The project utilizes key concepts in **Object-Oriented Programming (OOP)** to ensure scalability, maintainability, and simplicity. It features functionalities such as book borrowing, returning, user registration, and managing the inventory of books.

#### **Key Features**
- **Admin Panel**: 
   - **Add, Edit, Delete Books**: Admins can manage the entire book catalog, including adding new books, updating existing book details like title, author, genre; and removing books from the library.
   - **View Book Inventory**: Admins can view a complete list of available books along with their current availability status (borrowed or available).
  
- **User Management**:
   - **User Registration & Authentication**: Users can create an account and log in to access the library system.
   - **Profile Management**: Users can manage their personal details, view borrowing history, and track due dates.
  
- **Book Borrowing & Returning**:
   - **Search Functionality**: Users can search for books by title, author, genre, or availability, making it easier to find specific books in the catalog.
   - **Borrow Books**: Once a book is found, users can borrow it, and the system will record the borrowing details along with the due date for return.
   - **Return Books**: Users can return borrowed books, and the system will update the book's availability status.
   - **Due Date Tracking**: The system automatically tracks due dates for borrowed books and notifies users if the return date is approaching.

- **Search & Filter Capabilities**: 
   - **Advanced Search**: Users can search for books using various filters such as title, author, genre, and availability.
   - **Book Recommendations**: Based on the user's borrowing history, the system can suggest similar books that the user may be interested in.

#### **Technologies & Tools Used**
- **Programming Language**: Java (JDK 24 or higher)
- **File Handling**: Data is stored in text files (`.txt`) for book and user details, using Java’s I/O classes for reading and writing.
- **Basic Object-Oriented Programming**: Encapsulation, inheritance, and polymorphism are used extensively to design the system.
- **Command-Line Interface (CLI)**: A simple, text-based interface that makes the system accessible through terminal/console commands.

#### **System Architecture & Design**
- **Modular Design**: The system is organized into multiple Java classes representing key components such as `Book`, `User`, `LibraryManagementSystem`, etc.
- **Data Persistence**: The system uses flat files (such as `.txt` files) for persistent storage of books, users, and borrowing records, ensuring data is retained between sessions.
- **Separation of Concerns**: The project follows an MVC-like structure (Model-View-Controller) where the data layer (Model) is separated from the user interface layer (View), with logic (Controller) managing interactions between the two.

#### **Core Classes:**
- **`LibraryManagementSystem.java`**: The main entry point for the application, which integrates and controls the flow of operations between users and the admin system.
- **`Book.java`**: Defines the `Book` class with attributes like title, author, genre, availability status, and methods to manage book data.
- **`User.java`**: Defines the `User` class, storing details such as username, password, borrowed books, and interaction methods.
- **`Transaction.java`**: Manages borrowing and returning transactions, including due dates and status updates.
- **`Database.java`**: Provides methods to read/write data to text files, ensuring persistent storage of books, users, and transaction records.

#### **Setup Instructions**
To set up and run this project locally, follow the steps below:
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/username/LibraryManagementSystem.git
   ```
2. **Navigate to the Project Directory**:
   ```bash
   cd LibraryManagementSystem
   ```
3. **Compile the Java Files**:
   Use the following command to compile the source code:
   ```bash
   javac *.java
   ```
4. **Run the Program**:
   After compiling, run the program using:
   ```bash
   java LibraryManagementSystem
   ```

#### **Project Structure**
- `src/`: Contains all the Java source files.
  - `LibraryManagementSystem.java`: Main class responsible for running the application.
  - `Book.java`: Class to represent individual books in the library.
  - `User.java`: Class for managing user data and interactions.
  - `Transaction.java`: Manages transactions related to borrowing and returning books.
  - `Database.java`: Handles reading and writing data to text files.

- `data/`: Folder containing text files for storing book and user data.
  - `books.txt`: A file containing all book records.
  - `users.txt`: A file containing user accounts and their borrowed books.

#### **Contributors**
- **Timi_Bee**: Project creator and lead developer.

#### **Future Enhancements**
- **Graphical User Interface (GUI)**: A future iteration could include a GUI for better user interaction using JavaFX or Swing.
- **Database Integration**: Instead of text files, the system could integrate a database MySQL, SQLite) for more scalable data management.
- **Online Access**: Convert the application into a web-based system using technologies such as Spring Boot for back-end and React for front-end.



---
