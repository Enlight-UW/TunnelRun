package club.enlight;

import com.badlogic.gdx.controllers.PovDirection;
import com.sun.org.apache.xpath.internal.objects.XBoolean;

/**
 * Created by Sam on 4/18/2015.
 */
public class ControllerButtons {
    // XBox 360 controller
    public static final String XboxName = "XBOX";
    // Buttons
    public static final int XboxA = 0;
    public static final int XboxB = 1;
    public static final int XboxX = 2;
    public static final int XboxY = 3;
    public static final int XboxLB = 4;
    public static final int XboxRB = 5;
    public static final int XboxBack = 6;
    public static final int XboxStart = 7;
    public static final int XboxL3 = 8;
    public static final int XboxR3 = 9;

    //Dpad
    public static final PovDirection XboxDUP = PovDirection.north;
    public static final PovDirection XboxDDOWN = PovDirection.south;
    public static final PovDirection XboxDRIGHT = PovDirection.east;
    public static final PovDirection XboxDLEFT = PovDirection.west;

    //Analog sticks, triggers
    public static final int XboxLSY = 0;
    public static final int XboxLSX = 1;
    public static final int XboxRSY = 2;
    public static final int XboxRSX = 3;
    public static final int XboxRLT = 4; // 4 for both triggers, left goes from 0 to 1 right goes from -1 to 0

    // Kenneth's crappy P880 controller
    public static final String P880Name = "P880";

    // Buttons
    public static final int P880B1 = 0;
    public static final int P880B2 = 1;
    public static final int P880B3 = 2;
    public static final int P880B4 = 3;
    public static final int P880B5 = 4;
    public static final int P880B6 = 5;
    public static final int P880B7 = 6; //left bumper
    public static final int P880B8 = 7; //right bumper
    public static final int P880L3 = 8;
    public static final int P880R3 = 9;
    public static final int P880Start = 10;

    //Analog sticks
    public static final int P880LSY = 1;
    public static final int P880LSX = 0;
    public static final int P880RSY = 2;
    public static final int P880RSX = 3;

    //Dpad (I'm just assuming they're the same as xbox for now)
    public static final PovDirection P880DUP = PovDirection.north;
    public static final PovDirection P880DDOWN = PovDirection.south;
    public static final PovDirection P880DRIGHT = PovDirection.east;
    public static final PovDirection P880DLEFT = PovDirection.west;

    public static int getAButton(String name){
        if (name.contains(XboxName)){
            return XboxA;
        }else if (name.contains(P880Name)){
            return P880B3;
        }
        return 0;
    }

    public static int getBButton(String name){
        if (name.contains(XboxName)){
            return XboxB;
        }else if (name.contains(P880Name)){
            return P880B4;
        }
        return 1;
    }

    public static int getXButton(String name){
        if (name.contains(XboxName)){
            return XboxX;
        }else if (name.contains(P880Name)){
            return P880B1;
        }
        return 1;
    }

    public static int getStart(String name){
        if (name.contains(XboxName)){
            return XboxStart;
        }else if (name.contains(P880Name)){
            return P880Start;
        }
        return 7;
    }

    public static int getLeftY(String name){
        if (name.contains(XboxName)){
            return XboxLSY;
        }else if (name.contains(P880Name)){
            return P880LSY;
        }
        return 0;
    }

    public static int getLeftX(String name){
        if (name.contains(XboxName)){
            return XboxLSX;
        }else if (name.contains(P880Name)){
            return P880LSX;
        }
        return 0;
    }

    public static int getRightY(String name){
        if (name.contains(XboxName)){
            return XboxRSY;
        }else if (name.contains(P880Name)){
            return P880RSY;
        }
        return 0;
    }

    public static int getRightX(String name){
        if (name.contains(XboxName)){
            return XboxRSX;
        }else if (name.contains(P880Name)){
            return P880RSX;
        }
        return 0;
    }

}
