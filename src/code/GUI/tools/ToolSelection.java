package code.GUI.tools;

import java.util.Arrays;

/**
 * Wrapper for more readable gate selection.
 */
public enum ToolSelection {

    INVALID(-1, false),
    AND_BUTTON(0, false),
    OR_BUTTON(1, false),
    NOT_BUTTON(2, false),
    XOR_BUTTON(3, false),
    NAND_BUTTON(4, false),
    NOR_BUTTON(5, false),
    XNOR_BUTTON(6, false),
    INPUT_BUTTON(7, false),
    INPUT_LOGIC_0(7, false),
    INPUT_LOGIC_1(8, false),
    OUTPUT_BUTTON(9, false),
    OUTPUT_LOGIC_0 (10, false),
    OUTPUT_LOGIC_1 (11, false),
    OUTPUT_LOGIC_X (12, false),

    DELETE (1, true),
    CONNECT (2, true),
    CANCEL (3, true),
    PARENT_SELECTED (4, true),
    TOGGLE_INPUT (5, true),

    ;

    private int id;
    private boolean option;

    ToolSelection(int id, boolean option){
        this.id = id;
        this.option = option;
    }

    public int getId() {
        return id;
    }

    public boolean isOption() {
        return option;
    }

    public static ToolSelection getSelectedGate(int id, boolean option){
        if (id == -1){
            return INVALID;
        }
        return Arrays.stream(values()).filter(gateSelection -> gateSelection.id == id && gateSelection.option == option).findAny().orElse(null);
    }

    @Override
    public String toString() {
        if (this == INVALID){
            return "None";
        }
        return super.toString();
    }
}
