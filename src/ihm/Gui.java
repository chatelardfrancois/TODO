package ihm;

import bll.BLLException;
import bll.TodoManager;
import bo.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gui extends JFrame{
    private JPanel panneauPrincipal, panneauTodos, panneauModification;
    private JLabel lblDate, lblModification, lblErreur, lblListeVide, lblListePleine;
    private JButton btnAjouter, btnValider, btnModifier, btnSupprimer, btnRetour, btnTodos1, btnTodos2, btnTodos3, btnTodos4, btnTodos5;
    private JTextField txtTodo;
    private List<Todo> liste=null;
    private GridBagConstraints gbc = new GridBagConstraints();

    public Gui()  {
        this.setTitle("Mes 5 choses à faire");
        this.setSize(400,500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getPanneauPrincipal());
        this.pack();
        this.setVisible(true);
    }

    public JPanel getPanneauPrincipal() {
        if(panneauPrincipal==null){
            panneauPrincipal=new JPanel();
            // Je lui affecte le layout GridBagLayout (le plus pratique)
            panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            // Je mets des marges a 5
            gbc.insets= new Insets(5,5,5,5);

            gbc.gridy=0;
            gbc.gridx=0;
            panneauPrincipal.add(getlblModification(), gbc);
            lblModification.setVisible(false);
            panneauPrincipal.add(getLblDate(), gbc);
            gbc.gridy=1;
            panneauPrincipal.add(getTxtTodo(), gbc);
            gbc.gridy=2;
            panneauPrincipal.add(getPanneauModification(),gbc);
            getPanneauModification().setVisible(false);
            panneauPrincipal.add(getBtnAjouter(), gbc);
            gbc.gridy=3;
            panneauPrincipal.add(getPanneauTodos(),gbc);


        }
        return panneauPrincipal;
    }

    private JLabel getlblModification() {
        if(lblModification==null){
            lblModification=new JLabel("Ecran de modification");
        }
        return lblModification;
    }

    public JPanel getPanneauTodos(){
        if(panneauTodos==null){
            panneauTodos=new JPanel();
            panneauTodos.setLayout(new GridBagLayout());
            panneauTodos.setPreferredSize(new Dimension(400,220));
            this.gbc.insets= new Insets(5,5,5,5);

            afficherTodos();
        }


        return panneauTodos;
    }

    public JPanel getPanneauModification(){
        if(panneauModification==null){
            panneauModification=new JPanel();
            panneauModification.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets= new Insets(5,5,5,5);
            gbc.gridx=2;
            panneauModification.add(getBtnValider(), gbc);
            gbc.gridx=0;
            panneauModification.add(getBtnSupprimer(), gbc);
            gbc.gridx=1;
            panneauModification.add(getBtnModifier(), gbc);
            gbc.gridy=1;
            panneauModification.add(getBtnRetour(), gbc);

        }
        return panneauModification;
    }

    private JButton getBtnRetour() {
        if(btnRetour==null){
            btnRetour= new JButton("Retour");
            btnRetour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnAjouter.setVisible(true);
                    panneauTodos.setVisible(true);
                    lblDate.setVisible(true);
                    lblModification.setVisible(false);
                    txtTodo.setText(null);
                    panneauModification.setVisible(false);
                }
            });
        }
        return btnRetour;
    }

    private JButton getBtnValider() {
        if(btnValider==null){
            btnValider= new JButton("Valider");
        }
        return btnValider;
    }

    private JButton getBtnModifier() {
        if(btnModifier==null){
            btnModifier= new JButton("Modifier");
        }
        return btnModifier;
    }

    private JButton getBtnSupprimer() {
        if(btnSupprimer==null){
            btnSupprimer= new JButton("Supprimer");
        }
        return btnSupprimer;
    }

    public List<Todo> getListe(){
        TodoManager tm = TodoManager.getInstance();
        try {
            liste = tm.getAllTodo();
        } catch (BLLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public JLabel getLblListeVide(){
        if(lblListeVide==null){
            lblListeVide= new JLabel("La liste des choses à faire est vide");
        }
        return lblListeVide;
    }

    public JLabel getLblListePleine(){
        if(lblListePleine==null){
            lblListePleine= new JLabel("La liste des choses à faire est pleine");
            lblListePleine.setForeground(new Color(0xFF0303));
        }
        return lblListePleine;
    }

    public JButton getBtnTodos1(){
        if(btnTodos1==null){
            btnTodos1= new JButton(getListe().get(0).getTexte());
            btnTodos1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btnAjouter.setVisible(false);
                    panneauTodos.setVisible(false);
                    lblDate.setVisible(false);
                    lblModification.setVisible(true);
                    txtTodo.setText(btnTodos1.getText());
                    panneauModification.setVisible(true);
                }
            });
        }
        return btnTodos1;
    }

    public JButton getBtnTodos2(){
        if(btnTodos2==null){
            btnTodos2= new JButton(getListe().get(1).getTexte());
        }
        return btnTodos2;
    }

    public JButton getBtnTodos3(){
        if(btnTodos3==null){
            btnTodos3= new JButton(getListe().get(2).getTexte());
        }
        return btnTodos3;
    }

    public JButton getBtnTodos4(){
        if(btnTodos4==null){
            btnTodos4= new JButton(getListe().get(3).getTexte());
        }
        return btnTodos4;
    }

    public JButton getBtnTodos5(){
        if(btnTodos5==null){
            btnTodos5= new JButton(getListe().get(4).getTexte());
        }
        return btnTodos5;
    }

    public JLabel getLblDate(){
        if(lblDate==null){
            lblDate= new JLabel(String.format("Nous somme le : %02d/%02d/%d", LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(), LocalDate.now().getYear()));
        }
        return lblDate;
    }

    public JTextField getTxtTodo(){
        if(txtTodo==null){
            txtTodo=new JTextField(30);
            txtTodo.setPreferredSize(new Dimension(20,20));
        }
        return txtTodo;
    }

    public JButton getBtnAjouter(){
        if(btnAjouter==null){
            btnAjouter= new JButton("Ajouter");
            // Quand je clique sur le bouton ajoute en base de donnée le todoo et mets a jour le panel
            btnAjouter.addActionListener(e -> {
                TodoManager tm = TodoManager.getInstance();
                try {
                    tm.addTodo(txtTodo.getText());
                    panneauTodos.removeAll();
                    afficherTodos();
                    panneauTodos.revalidate();
                    txtTodo.setText(null);

                } catch (BLLException bllException) {
                    bllException.printStackTrace();
                }
            });
        }
        return btnAjouter;
    }

    public void afficherTodos(){
        this.gbc.gridy=1;
        switch (getListe().size()) {
            case 1 -> {
                panneauTodos.add(getBtnTodos1());
                panneauTodos.revalidate();
                panneauTodos.repaint();
            }
            case 2 -> {
                panneauTodos.add(getBtnTodos1(), this.gbc);
                this.gbc.gridy = 2;
                panneauTodos.add(getBtnTodos2(), this.gbc);
                panneauTodos.revalidate();
                panneauTodos.repaint();
            }
            case 3 -> {
                panneauTodos.add(getBtnTodos1(), this.gbc);
                this.gbc.gridy = 2;
                panneauTodos.add(getBtnTodos2(), this.gbc);
                this.gbc.gridy = 3;
                panneauTodos.add(getBtnTodos3(), this.gbc);
                panneauTodos.revalidate();
                panneauTodos.repaint();
            }
            case 4 -> {
                panneauTodos.add(getBtnTodos1(), this.gbc);
                this.gbc.gridy = 2;
                panneauTodos.add(getBtnTodos2(), this.gbc);
                this.gbc.gridy = 3;
                panneauTodos.add(getBtnTodos3(), this.gbc);
                this.gbc.gridy = 4;
                panneauTodos.add(getBtnTodos4(), this.gbc);
                panneauTodos.revalidate();
                panneauTodos.repaint();
            }
            case 5 -> {
                panneauTodos.add(getBtnTodos1(), this.gbc);
                this.gbc.gridy = 2;
                panneauTodos.add(getBtnTodos2(), this.gbc);
                this.gbc.gridy = 3;
                panneauTodos.add(getBtnTodos3(), this.gbc);
                this.gbc.gridy = 4;
                panneauTodos.add(getBtnTodos4(), this.gbc);
                this.gbc.gridy = 5;
                panneauTodos.add(getBtnTodos5(), this.gbc);
                panneauTodos.revalidate();
                panneauTodos.repaint();
                getTxtTodo().setEnabled(false);
                getBtnAjouter().setEnabled(false);
                this.gbc.gridy=0;
                panneauTodos.add(getLblListePleine(), this.gbc);
            }
            default -> {
                this.gbc.gridy=0;
                panneauTodos.add(getLblListeVide());
            }
        }
    }




}
