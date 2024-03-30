package ui;


import data.Tarea;
import network.TareaRetrofit;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class listTareaDometicas extends JFrame {

    private JButton agregar;
    private JButton eliminar;

    private JScrollPane scrollPane = new JScrollPane();
    private JList<Tarea> lista;

    public listTareaDometicas() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Lista De Tarea Domesticas");
        this.setLayout(null);
        this.setBounds(600, 200, 480, 500);
        this.setContentPane(PanelPrincipal());
        this.setVisible(true);
    }


    private JPanel PanelPrincipal() {
        JPanel panel = new JPanel();
        ConfigurarButton();
        llenarLista();
        panel.setLayout(null);
        panel.add(scrollPane);
        panel.add(agregar);
        panel.add(eliminar);
        return panel;
    }

    private void llenarLista() {

        DefaultListModel model = new DefaultListModel<Tarea>();

        TareaRetrofit api = new TareaRetrofit();

        api.listaCitas().forEach(tarea -> {
                    tarea.setTareaId(tarea.getTareaId());
                    tarea.setNombre(tarea.getNombre());
                    tarea.setEstado(tarea.getEstado());
                    tarea.setDescripcion(tarea.getDescripcion());
                    tarea.setFecha(tarea.getFecha());
                    tarea.setEmpleadoId(tarea.getEmpleadoId());
                    model.addElement(tarea);
                }

        );

        lista = new JList(model);
        scrollPane.setBounds(0, 0, 330, 450);
        scrollPane.setBorder(new LineBorder(Color.CYAN, 5, true));
        lista.setCellRenderer(new TareaRenderer());

        scrollPane.setViewportView(lista);


    }


    private void ConfigurarButton() {
        agregar = new JButton("");
        ImageIcon icono = new ImageIcon("src/resources/AgregarIcono.png");
        agregar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        agregar.setBounds(350, 10, 75, 30);
        agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                OpcionAgregar();
            }
        });

        eliminar = new JButton("");
        icono = new ImageIcon("src/resources/EliminarIcono.png");
        eliminar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        eliminar.setBounds(350, 50, 75, 30);
        eliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                //OpcionEliminar();
            }
        });
    }

    private void OpcionAgregar() {
        new registroTareaDometicas();
    }
}
