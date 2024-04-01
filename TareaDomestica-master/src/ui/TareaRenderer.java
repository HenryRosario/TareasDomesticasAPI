package ui;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import data.Tarea;


public class TareaRenderer extends JPanel implements ListCellRenderer<Tarea> {

    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    private JLabel lbDescripcion = new JLabel();
    private JLabel lbEstado = new JLabel();
    JPanel panelText = new JPanel(new GridLayout(0, 1));
    private static final Color COLOR_POR_HACER = new Color( 0xfd8c6a); // Color rojizo, estado por hacer
    private static final Color COLOR_EN_PROCESO = new Color(0xFAF0FD45, true); // Color amarillo claro, estado en proceso
    private static final Color COLOR_TERMINADA = new Color(0x88F872); // Color verde claro, estado terminada

    public TareaRenderer() {
        setLayout(new BorderLayout(0,0));
        panelText.add(lbDescripcion);
        panelText.add(lbAuthor);
        panelText.add(lbName);
        panelText.add(lbEstado);
        //panelText.setBorder(new LineBorder(Color.CYAN, 5, true));
        this.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(panelText, BorderLayout.CENTER);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Tarea> list,
                                                  Tarea tarea, int index, boolean isSelected, boolean cellHasFocus) {

        lbName.setText(tarea.getNombre());
        lbName.setBorder(new EmptyBorder(5, 5, 0, 0));

        lbDescripcion.setText(tarea.getDescripcion());
        lbDescripcion.setBorder(new EmptyBorder(6, 5, 0, 0));

        lbEstado.setText(tarea.getEstado());
        lbEstado.setBorder(new EmptyBorder(7, 5, 5, 0));

        lbAuthor.setBorder(new EmptyBorder(8, 5, 5, 0));
        lbAuthor.setText(tarea.getFecha());

        Color backgroundColor;
        switch (tarea.getEstado()) {
            case "Por Hacer":
                backgroundColor = COLOR_POR_HACER;
                break;
            case "En Proceso":
                backgroundColor = COLOR_EN_PROCESO;
                break;
            case "Terminada":
                backgroundColor = COLOR_TERMINADA;
                break;
            default:
                backgroundColor = isSelected ? list.getSelectionBackground() : list.getBackground();
        }
        panelText.setBackground(backgroundColor);

        return this;
    }
}