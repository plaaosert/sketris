package com.skirmish.sketris.controls.gamepad

import com.badlogic.gdx.controllers.Controller
import com.badlogic.gdx.utils.SharedLibraryLoader


object XboxMapping {
    // Buttons
    var A = 0
    var B = 0
    var X = 0
    var Y = 0
    var GUIDE = 0
    var L_BUMPER = 0
    var R_BUMPER = 0
    var BACK = 0
    var START = 0
    var DPAD_UP = 0
    var DPAD_DOWN = 0
    var DPAD_LEFT = 0
    var DPAD_RIGHT = 0
    var L3 = 0
    var R3 = 0
    // Axes
    /**
     * left trigger, -1 if not pressed, 1 if pressed, 0 is initial value
     */
    var L_TRIGGER = 0

    /**
     * right trigger, -1 if not pressed, 1 if pressed, 0 is initial value
     */
    var R_TRIGGER = 0

    /**
     * left stick vertical axis, -1 if up, 1 if down
     */
    var L_STICK_VERTICAL_AXIS = 0

    /**
     * left stick horizontal axis, -1 if left, 1 if right
     */
    var L_STICK_HORIZONTAL_AXIS = 0

    /**
     * right stick vertical axis, -1 if up, 1 if down
     */
    var R_STICK_VERTICAL_AXIS = 0

    /**
     * right stick horizontal axis, -1 if left, 1 if right
     */
    var R_STICK_HORIZONTAL_AXIS = 0

    /**
     * Different names:
     * - Microsoft PC-joystick driver
     *
     * @return whether the [Controller] is an Xbox controller
     */
    fun isXboxController(controller: Controller): Boolean {
        return controller.name.matches(Regex("(.*)(X-?[Bb]ox|Microsoft PC-joystick)(.*)"))
    }

    init {
        if (SharedLibraryLoader.isWindows) {
            A = 0
            B = 1
            X = 2
            Y = 3
            GUIDE = -1
            L_BUMPER = 4
            R_BUMPER = 5
            BACK = 6
            START = 7
            DPAD_UP = 10
            DPAD_DOWN = 12
            DPAD_LEFT = 13
            DPAD_RIGHT = 11
            L_TRIGGER = 2 // postive value
            R_TRIGGER = 2 // negative value
            L_STICK_VERTICAL_AXIS = 0 // Down = -1, up = 1
            L_STICK_HORIZONTAL_AXIS = 1 // Left = -1, right = 1
            R_STICK_VERTICAL_AXIS = 3 // Assume same story
            R_STICK_HORIZONTAL_AXIS = 4
            L3 = 8
            R3 = 9
        } else if (SharedLibraryLoader.isLinux) {
            A = 0
            B = 1
            X = 2
            Y = 3
            GUIDE = 5
            L_BUMPER = 9
            R_BUMPER = 10
            BACK = 4
            START = 6
            DPAD_UP = 11
            DPAD_DOWN = 12
            DPAD_LEFT = 13
            DPAD_RIGHT = 14
            L_TRIGGER = 2
            R_TRIGGER = 5
            L_STICK_VERTICAL_AXIS = 1
            L_STICK_HORIZONTAL_AXIS = 0
            R_STICK_VERTICAL_AXIS = 4
            R_STICK_HORIZONTAL_AXIS = 3
            L3 = 9
            R3 = 10
        } else if (SharedLibraryLoader.isMac) {
            A = 11
            B = 12
            X = 13
            Y = 14
            GUIDE = 10
            L_BUMPER = 8
            R_BUMPER = 9
            BACK = 5
            START = 4
            DPAD_UP = 0
            DPAD_DOWN = 1
            DPAD_LEFT = 2
            DPAD_RIGHT = 3
            L_TRIGGER = 0
            R_TRIGGER = 1
            L_STICK_VERTICAL_AXIS = 3
            L_STICK_HORIZONTAL_AXIS = 2
            R_STICK_VERTICAL_AXIS = 5
            R_STICK_HORIZONTAL_AXIS = 4
            L3 = -1
            R3 = -1
        } else {
            A = -1
            B = -1
            X = -1
            Y = -1
            GUIDE = -1
            L_BUMPER = -1
            R_BUMPER = -1
            L_TRIGGER = -1
            R_TRIGGER = -1
            BACK = -1
            START = -1
            DPAD_UP = -1
            DPAD_DOWN = -1
            DPAD_LEFT = -1
            DPAD_RIGHT = -1
            L_STICK_VERTICAL_AXIS = -1
            L_STICK_HORIZONTAL_AXIS = -1
            R_STICK_VERTICAL_AXIS = -1
            R_STICK_HORIZONTAL_AXIS = -1
            L3 = -1
            R3 = -1
        }
    }
}