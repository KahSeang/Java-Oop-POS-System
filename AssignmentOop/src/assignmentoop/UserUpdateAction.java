/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;
class UserUpdateAction implements UserActionable {
    private User user;

    public UserUpdateAction(User user) {
        this.user = user;
    }

    @Override
    public void performAction() {
        System.out.println("User " + user.getUsername() + " has been updated.");
    }
}