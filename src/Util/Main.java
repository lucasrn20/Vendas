package Util;

import Controller.Controller;
import Controller.ControllerBack;

public class Main {

    public static void main(String[] args) {
        Controller controller = new Controller();
        ControllerBack controllerBack = new ControllerBack();
        controller.view();
        controllerBack.view();
        
    }
    
}
