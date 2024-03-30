package ui;

import javax.swing.*;
import java.awt.*;
import data.Tarea;
import network.TareaRetrofit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class registroTareaDometicas extends JFrame {

    private JButton agregarButton , limpiarCampoButton;

    private JComboBox<String> estadoComboBox;

    LocalDate fechaActual = LocalDate.now();

    private JTextField tareaDomesticaIdTextField, descripcionTextField, fechaTextField, nombreTextField, estadoTextField;

    public registroTareaDometicas() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Registro Tarea Domestica");
        this.setLayout(null);
        this.setBounds(500, 200, 450, 450);
        this.setContentPane(panelPricipal());
        this.setVisible(true);
    }
    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String fechaActualStr = fechaActual.format(formatoFecha);
    private JPanel panelPricipal() {
        JPanel panel = new JPanel();
        panel.setLayout(null);


        JLabel descripcion = texto("Descripcion:");
        descripcion.setBounds(25, 10, 100, 130);
        panel.add(descripcion);

        descripcionTextField = textField();
        descripcionTextField.setBounds(130, 60, 250, 30);
        panel.add(descripcionTextField);


        JLabel Fecha = texto("Fecha:");
        Fecha.setBounds(25, 10, 100, 230);
        panel.add(Fecha);

        JTextField fechaTextField = new JTextField(fechaActualStr);
        fechaTextField.setBounds(130, 110, 100, 30);
        panel.add(fechaTextField);



        JLabel nombre = texto("Nombre:");
        nombre.setBounds(25, 10, 100, 330);
        panel.add(nombre);

        nombreTextField = textField();
        nombreTextField.setBounds(130, 160, 250, 30);
        panel.add(nombreTextField);


        JLabel estado = texto("Estado:");
        estado.setBounds(25, 10, 100, 430);
        panel.add(estado);

        JComboBox comboBoxestado = new JComboBox();
        comboBoxestado.addItem("Por Hacer");
        comboBoxestado.setBounds(130, 210, 100, 30);
        panel.add(comboBoxestado);

        agregarButton = new JButton("Asignar");
        agregarButton.setBounds(250, 260, 100, 30);
        panel.add(agregarButton);

        limpiarCampoButton = new JButton("Limpiar Campos");
        limpiarCampoButton.setBounds(25, 260, 100, 30);
        panel.add(limpiarCampoButton);




/*
        nombreTextField = textField();
        panel.add(panel("Nombre ", 25, 60, nombreTextField, new BorderLayout()));

        NombreAutorTextField = textField();
        panel.add(panel("Nombre Autor ", 25, 110, NombreAutorTextField, new BorderLayout()));

        EdiccionTextField = textField();
        panel.add(panel("Ediccion ", 25, 160, EdiccionTextField, new BorderLayout()));

        precioTextField = textField();
        panel.add(panel("Precio ", 25, 210, precioTextField, new BorderLayout()));

        panelButton();
        panel.add(guardar);
        panel.add(buscar);
        panel.add(nuevo);
        panel.add(eliminar);*/
        return panel;
    }

    private JLabel texto(String _texto) {
        JLabel texto = new JLabel(_texto);
        texto.setForeground(Color.BLACK);
        texto.setFont(new Font("arial", 1, 16));
        return texto;
    }

    private JTextField textField() {
        JTextField jTextField = new JTextField();
        jTextField.setForeground(Color.BLACK);
        jTextField.setFont(new Font("arial", 1, 16));
        return jTextField;
    }

    private void addEstadoComboBox() {
        String[] estados = {"Por Hacer", "En Proceso", "Terminada"};
        estadoComboBox = new JComboBox<>(estados);
        estadoComboBox.setSelectedIndex(0); // Establecer "Por Hacer" como opción seleccionada por defecto
        add(estadoComboBox);
    }
    private void ConfigurarButton() {
        agregarButton = new JButton("");
        ImageIcon icono = new ImageIcon("src/resources/AgregarIcono.png");
        agregarButton.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
        agregarButton.setBounds(350, 10, 75, 30);
        agregarButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                OpcionAgregar();
            }
        });

        limpiarCampoButton= new JButton("");
        icono = new ImageIcon("src/resources/EliminarIcono.png");
        limpiarCampoButton.setIcon(new ImageIcon(icono.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));
       limpiarCampoButton.setBounds(350, 50, 75, 30);
       limpiarCampoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                limpiarCampos();
            }
        });
    }


    private void OpcionAgregar() {
        // Obtener los datos del formulario
        String descripcion = descripcionTextField.getText();
        String fecha = fechaTextField.getText(); // Suponiendo que la fecha está en formato de texto
        String nombre = nombreTextField.getText();
        String estado = estadoComboBox.getSelectedItem().toString(); // Obtener el estado seleccionado del JComboBox

        // Crear una instancia de Tarea con los datos obtenidos
        Tarea nuevaTarea = new Tarea(descripcion, fecha, nombre, estado);

        // Enviar la tarea al servidor utilizando TareaRetrofit (suponiendo que TareaRetrofit es una clase válida para hacer solicitudes HTTP)
        TareaRetrofit tareaRetrofit = new TareaRetrofit();
        tareaRetrofit.enviarTarea(nuevaTarea);

        // Limpiar los campos del formulario después de agregar la tarea
        limpiarCampos();
    }
    private void limpiarCampos() {
        descripcionTextField.setText("");
        fechaTextField.setText("");
        nombreTextField.setText("");
        estadoComboBox.setSelectedIndex(0); // Establecer el estado "Por Hacer" como seleccionado por defecto
    }

}