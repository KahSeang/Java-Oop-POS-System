/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;


public class PasswordResetAction implements UserActionable {
    private User user;

    public PasswordResetAction(User user) {
        this.user = user;
    }

    @Override
    public void performAction() {
        System.out.println("\n\t\t\t\t\t\tPassword reset successfully for user: " + user.getUsername());
    }
}