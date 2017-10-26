package code.GUI.tools;


import java.awt.*;

public class ToolSelectionDisplay {

    private static ToolSelectionDisplay instance;

    private ToolSelection selectedTool;

    private ToolSelectionDisplay(){
        selectedTool = ToolSelection.INVALID;
    }

    public static void setTool(ToolSelection toolSelection){
        getInstance().selectedTool = toolSelection;
    }

    public static ToolSelection getSelectedTool(){
        return getInstance().selectedTool;
    }

    private static ToolSelectionDisplay getInstance(){
        return instance != null ? instance : (instance = new ToolSelectionDisplay());
    }

    public static void paint(Graphics2D g2, int x, int y){
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        ToolSelection selectedGate = getSelectedTool();
        String displayDebugText = "Selected Tool: ";
        displayDebugText += (selectedGate != null ? selectedGate.toString() : "NONE");
        g2.drawString(displayDebugText, x, y);
    }

}
