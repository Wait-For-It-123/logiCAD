package code.GUI.tools;

import code.GUI.GUI;
import com.sun.java.accessibility.util.EventID;

import javax.tools.Tool;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static code.GUI.GUI.*;

/**
 * Observer to observe the changes in gui to call repaint. There's too many
 * layers and no global handler for ui, so this would be the most intuitive
 * implementation
 */
public class GuiObserver {

    private GUI gui;

    public GuiObserver(GUI gui){
        this.gui = gui;
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener()
        {
            public void eventDispatched(AWTEvent e)
            {
                if (!(e instanceof KeyEvent)){
                    return;
                }
                KeyEvent keyEvent = (KeyEvent) e;

                switch (keyEvent.getKeyCode()){
                    case KeyEvent.VK_ESCAPE:
                        gui.setCircuitElementButtonClicked(INVALID);
                        gui.setOptionButtons(INVALID);
                        break;
                    case KeyEvent.VK_1:
                        gui.setCircuitElementButtonClicked(ToolSelection.AND_BUTTON.getId());
                        break;
                    case KeyEvent.VK_2:
                        gui.setCircuitElementButtonClicked(ToolSelection.OR_BUTTON.getId());
                        break;
                    case KeyEvent.VK_3:
                        gui.setCircuitElementButtonClicked(ToolSelection.NOT_BUTTON.getId());
                        break;
                    case KeyEvent.VK_4:
                        gui.setCircuitElementButtonClicked(ToolSelection.XOR_BUTTON.getId());
                        break;
                    case KeyEvent.VK_5:
                        gui.setCircuitElementButtonClicked(ToolSelection.NAND_BUTTON.getId());
                        break;
                    case KeyEvent.VK_6:
                        gui.setCircuitElementButtonClicked(ToolSelection.NOR_BUTTON.getId());
                        break;
                    case KeyEvent.VK_7:
                        gui.setCircuitElementButtonClicked(ToolSelection.XNOR_BUTTON.getId());
                        break;
                    case KeyEvent.VK_8:
                        gui.setCircuitElementButtonClicked(ToolSelection.INPUT_BUTTON.getId());
                        break;
                    case KeyEvent.VK_9:
                        gui.setCircuitElementButtonClicked(ToolSelection.OUTPUT_BUTTON.getId());
                        break;
                }

                if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
                    gui.setCircuitElementButtonClicked(INVALID);
                    gui.setOptionButtons(INVALID);
                }
            }
        }, AWTEvent.MOUSE_MOTION_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK + AWTEvent.KEY_EVENT_MASK);
        Executors.newSingleThreadExecutor().submit(this::observe);
    }

    public void observe(){
        while (true){ //Observe until JVM exits.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int tool = gui.getCircuitElementButtonClicked(), option = gui.getOptionButtons();
            if (tool == -1) {
                ToolSelectionDisplay.setTool(ToolSelection.getSelectedGate(option, true));
            } else {
                ToolSelectionDisplay.setTool(ToolSelection.getSelectedGate(tool, false));
            }

            
        }
    }



}
