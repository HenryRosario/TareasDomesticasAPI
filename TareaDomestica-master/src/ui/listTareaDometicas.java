
package ui;


import data.Tarea;
import network.TareaRetrofit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class listTareaDometicas extends JFrame {

    private JButton agregar;
    private JButton buscar;
    private JScrollPane scrollPane = new JScrollPane();
    private JList<Tarea> lista;
    private JTextField buscarTextField;
    JPanel panel;

    JLabel imagenRegistroLabel;

    public listTareaDometicas() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gestor de Tareas Domésticas");
        this.setLayout(null);
        this.setBounds(600, 200, 540, 600);
        this.setContentPane(PanelPrincipal());
        this.setVisible(true);
    }


    private JPanel PanelPrincipal() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(0x93FFFFFF, true));
        ConfigurarButton();
        llenarLista();

        panel.add(imagenRegistroLabel);
        panel.add(buscarTextField);
        panel.add(scrollPane);
        panel.add(agregar);
        panel.add(buscar);

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
        scrollPane.setBounds(10, 100, 500, 500);
        scrollPane.setBackground(new Color(0xB2FD6A99, true));
        //scrollPane.setBorder(new LineBorder(new Color(0xB2FD6A99, true), 5, true));
        lista.setCellRenderer(new TareaRenderer());

        scrollPane.setViewportView(lista);


    }

    private void ConfigurarButton() {
        ImageIcon icono = new ImageIcon("out/production/ProAPI/resources/buscarIcono.png");

        buscarTextField = textField();
        buscarTextField.setBounds(10, 50, 250, 30);

        buscar = new JButton("");
        buscar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        buscar.setBounds(270, 50, 75, 30);
        buscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                //OpcionEliminar();
            }
        });

        agregar = new JButton("");
        icono = new ImageIcon("out/production/ProAPI/resources/AgregarIcono.png");
        agregar.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        agregar.setBounds(360, 50, 75, 30);
        agregar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                OpcionAgregar();
            }
        });

        icono = new ImageIcon("out/production/ProAPI/resources/logoIcono.png");

        imagenRegistroLabel = new JLabel();
        imagenRegistroLabel.setIcon(new ImageIcon(icono.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        imagenRegistroLabel.setBounds(150, 0, 300, 50);
        imagenRegistroLabel.setText("Tareas Asignadas");

    }

    private void OpcionAgregar() {
        new registroTareaDometicas();
    }

    private JTextField textField() {
        JTextField jTextField = new JTextField();
        jTextField.setForeground(Color.BLACK);
        jTextField.setFont(new Font("arial", 1, 16));
        return jTextField;
    }
}