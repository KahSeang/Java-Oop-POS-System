    package assignmentoop;

    import java.io.*;
    import java.util.Scanner;
    import java.util.regex.Pattern;

    class User {
        private static int nextUserId = 1;
        private int userId;
        private String username;
        private String password;
        private String ic;
        private String email;
        private String phone;
        private static int numOfUser;
        private static final String USER_FILE_PATH = "users.txt"; // Path to the file where users are stored

        // Constructor
        public User(String username, String password, String ic, String email, String phone) {
            this.userId = nextUserId++;
            this.username = username;
            this.password = password;
            this.ic = ic;
            this.email = email;
            this.phone = phone;
            numOfUser++;
        }

        // No-arg constructor
        public User() {
            this("", "", "", "", ""); 
        }

        // Parameterized constructor with userId
        public User(int userId, String username, String password, String ic, String email, String phone) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.ic = ic;
            this.email = email;
            this.phone = phone;
        }

        // Getters and Setters
        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            if (isValidUsername(username)) {
                this.username = username;
            } else {
                System.out.println("Invalid username. It should be 3-15 characters long and alphanumeric.");
            }
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            if (isValidPassword(password)) {
                this.password = password;
            } else {
                System.out.println("Invalid password. It should be 8-20 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.");
            }
        }

        public String getIc() {
            return ic;
        }

        public void setIc(String ic) {
            if (isValidIc(ic)) {
                this.ic = ic;
            } else {
                System.out.println("Invalid IC format. It should be a 12-digit number.");
            }
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            if (isValidEmail(email)) {
                this.email = email;
            } else {
                System.out.println("Invalid email format.");
            }
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            if (isValidPhone(phone)) {
                this.phone = phone;
            } else {
                System.out.println("Invalid phone number format. It should be 10-11 digits long.");
            }
        }

        public static int getNumOfUser() {
            return numOfUser;
        }

        public String toFileString() {
            return userId + "," + username + "," + password + "," + ic + "," + email + "," + phone;
        }

     
        // Static methods for user management
        public static void registerUser() {
            Scanner scanner = new Scanner(System.in);

            System.out.println("\n\t\t\t\t\t\t=================================");
            System.out.println("\t\t\t\t\t\t Register a New User ");
            System.out.println("\t\t\t\t\t\t=================================");

            String username;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter username: ");
                username = scanner.nextLine();
                if (isValidUsername(username) && !isUsernameExists(username)) {
                    break; // Valid username entered
                } else {
                    System.out.println("\t\t\t\t\t\tInvalid or existing username. It should be 3-15 characters long and alphanumeric.");
                }
            }

            String password;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter password: ");
                password = scanner.nextLine();
                if (isValidPassword(password)) {
                    break; // Valid password entered
                } else {
                    System.out.println("\t\t\t\t\t\tInvalid password. It should be 8-20 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character.");
                }
            }

            String ic;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter IC: ");
                ic = scanner.nextLine();
                if (isValidIc(ic)) {
                    break; // Valid IC entered
                } else {
                    System.out.println("\t\t\t\t\t\tInvalid IC format. It should be a 12-digit number.");
                }
            }

            String email;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter email: ");
                email = scanner.nextLine();
                if (isValidEmail(email)) {
                    break; // Valid email entered
                } else {
                    System.out.println("\t\t\t\t\t\tInvalid email format.");
                }
            }

            String phone;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter phone: ");
                phone = scanner.nextLine();
                if (isValidPhone(phone)) {
                    break; // Valid phone entered
                } else {
                    System.out.println("\t\t\t\t\t\tInvalid phone number format. It should be 10-11 digits long.");
                }
            }

            // Generate new user ID
            int newUserId = generateNewUserId();

            // Create new User object
            User newUser = new User(newUserId, username, password, ic, email, phone);

            // Save user to file
            saveUserToFile(newUser);
            System.out.println("\t\t\t\t\t\tUser registered successfully with User ID: " + newUserId);
        }

        private static boolean isValidUsername(String username) {
            return username != null && username.matches("[a-zA-Z0-9]{3,15}");
        }

        private static boolean isValidPassword(String password) {
            return password != null &&
                   password.length() >= 8 &&
                   password.length() <= 20 &&
                   Pattern.compile("[A-Z]").matcher(password).find() &&
                   Pattern.compile("[a-z]").matcher(password).find() &&
                   Pattern.compile("[0-9]").matcher(password).find() &&
                   Pattern.compile("[^a-zA-Z0-9]").matcher(password).find();
        }

        private static boolean isValidIc(String ic) {
            return ic != null && ic.matches("\\d{12}");
        }

        private static boolean isValidEmail(String email) {
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            return email != null && email.matches(emailRegex);
        }

        private static boolean isValidPhone(String phone) {
            return phone != null && phone.matches("\\d{10,11}");
        }

        private static int generateNewUserId() {
            int maxId = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 6) {
                        int id = Integer.parseInt(parts[0]);
                        if (id > maxId) {
                            maxId = id;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("\t\t\t\t\t\tError generating new user ID: " + e.getMessage());
            }
            return maxId + 1; // Return the next ID
        }

        public static boolean isUsernameExists(String username) {
            try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 2) {
                        String existingUsername = parts[1];
                        if (existingUsername.equals(username)) {
                            return true;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Error checking existing usernames: " + e.getMessage());
            }
            return false;
        }

        private static void saveUserToFile(User user) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
                bw.write(user.toFileString());
                bw.newLine();
            } catch (IOException e) {
                System.out.println("Error saving user data: " + e.getMessage());
            }
        }
        
        
    public static void userManagement() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("\n\t\t\t\t\t\t========================");
        System.out.println("\t\t\t\t\t\t User Management System");
        System.out.println("\t\t\t\t\t\t========================");
        System.out.println("\t\t\t\t\t\t1. Register User");
        System.out.println("\t\t\t\t\t\t2. View All Users");
        System.out.println("\t\t\t\t\t\t3. Update User");
        System.out.println("\t\t\t\t\t\t4. Delete User");
        System.out.println("\t\t\t\t\t\t5. Exit");
        System.out.print("\t\t\t\t\t\tChoose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        switch (option) {
            case 1:
                registerUser();
                break;
            case 2:
                viewAllUsers();
                break;
            case 3:
                updateUser();
                break;
            case 4:
                deleteUser();
                break;
            case 5:
                System.out.println("\t\t\t\t\t\tExiting User Management System...");
                return;
            default:
                System.out.println("\t\t\t\t\t\tInvalid option. Please try again.");
        }
    }
}

// Method to display all users
public static void viewAllUsers() {
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t All Registered Users ");
    System.out.println("\t\t\t\t\t\t=================================");
    try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 6) {
                System.out.println("\t\t\t\t\t\tUser ID: " + parts[0] +
                                   ", Username: " + parts[1] +
                                   ", IC: " + parts[3] +
                                   ", Email: " + parts[4] +
                                   ", Phone: " + parts[5] );
            }
        }
    } catch (IOException e) {
        System.out.println("\t\t\t\t\t\tError reading user data: " + e.getMessage());
    }
}

// Method to update a user by their user ID
public static void updateUser() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\n\t\t\t\t\t\tEnter the User ID to update: ");
    int userId = scanner.nextInt();
    scanner.nextLine(); // Consume newline character
    
    // Load all users into memory to modify the desired user
    try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
        StringBuilder sb = new StringBuilder();
        String line;
        boolean userFound = false;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == userId) {
                userFound = true;
                
                // Show menu for which field to update
                System.out.println("\n\t\t\t\t\t\tChoose which field to update:");
                System.out.println("\t\t\t\t\t\t1. Password");
                System.out.println("\t\t\t\t\t\t2. IC");
                System.out.println("\t\t\t\t\t\t3. Email");
                System.out.println("\t\t\t\t\t\t4. Phone");
                System.out.println("\t\t\t\t\t\t5. Update All");

                System.out.print("\t\t\t\t\t\tEnter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
//                    case 1:
//                        System.out.print("\t\t\t\t\t\tEnter new username: ");
//                        parts[1] = scanner.nextLine();
//                        break;
                    case 1:
                        System.out.print("\t\t\t\t\t\tEnter new password: ");
                        parts[2] = scanner.nextLine();
                        break;
                    case 2:
                        System.out.print("\t\t\t\t\t\tEnter new IC: ");
                        parts[3] = scanner.nextLine();
                        break;
                    case 3:
                        System.out.print("\t\t\t\t\t\tEnter new email: ");
                        parts[4] = scanner.nextLine();
                        break;
                    case 4:
                        System.out.print("\t\t\t\t\t\tEnter new phone: ");
                        parts[5] = scanner.nextLine();
                        break;
                    case 5:
                        System.out.print("\t\t\t\t\t\tEnter new username: ");
                        parts[1] = scanner.nextLine();
                        System.out.print("\t\t\t\t\t\tEnter new password: ");
                        parts[2] = scanner.nextLine();
                        System.out.print("\t\t\t\t\t\tEnter new IC: ");
                        parts[3] = scanner.nextLine();
                        System.out.print("\t\t\t\t\t\tEnter new email: ");
                        parts[4] = scanner.nextLine();
                        System.out.print("\t\t\t\t\t\tEnter new phone: ");
                        parts[5] = scanner.nextLine();
                        break;
                    default:
                        System.out.println("\t\t\t\t\t\tInvalid choice.");
                        return;
                }

                // Update user info in the file
                sb.append(String.join(",", parts)).append("\n");
            } else {
                sb.append(line).append("\n");
            }
        }

        if (userFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
                bw.write(sb.toString()); // Rewrite the file with updated user information
            }
            System.out.println("\t\t\t\t\t\tUser updated successfully.");
        } else {
            System.out.println("\t\t\t\t\t\tUser with ID " + userId + " not found.");
        }
    } catch (IOException e) {
        System.out.println("\t\t\t\t\t\tError updating user data: " + e.getMessage());
    }
}

// Method to delete a user by their user ID
public static void deleteUser() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("\n\t\t\t\t\t\tEnter the User ID to delete: ");
    int userId = scanner.nextInt();
    scanner.nextLine(); // Consume newline character
    
    // Load all users into memory to remove the desired user
    try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
        StringBuilder sb = new StringBuilder();
        String line;
        boolean userFound = false;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == userId) {
                userFound = true;
                System.out.println("\t\t\t\t\t\tUser with ID " + userId + " deleted.");
            } else {
                sb.append(line).append("\n"); // Keep original line
            }
        }
        
        if (userFound) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
                bw.write(sb.toString()); // Rewrite file with user removed
            }
        } else {
            System.out.println("\t\t\t\t\t\tUser with ID " + userId + " not found.");
        }
    } catch (IOException e) {
        System.out.println("\t\t\t\t\t\tError deleting user data: " + e.getMessage());
    }
}
   @Override
        public String toString() {
            return "\n\t\t\t\t\t\tUser ID: " + userId +
                   "\n\t\t\t\t\t\tUserName: " + username +
                   "\n\t\t\t\t\t\tIC: " + ic +
                   "\n\t\t\t\t\t\tEmail: " + email +
                   "\n\t\t\t\t\t\tPhone: " + phone;
        }

    }
