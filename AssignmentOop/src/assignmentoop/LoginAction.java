/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;

public class LoginAction implements UserActionable {
    private User user;

    public LoginAction(User user) {
        this.user = user;
    }

    @Override
    public void performAction() {
        System.out.println("\n\t\t\t\t\t\tLogin successful. Welcome, " + user.getUsername() + " (User ID: " + user.getUserId() + ")!");
        // Additional actions can be performed here, such as logging the login time or updating the user status.
    }
}
