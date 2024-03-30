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

        if(cellHasFocus){
            panelText.setBackground(Color.GREEN);
        }else{
            panelText.setBackground(Color.CYAN);
        }
        return this;
    }
}