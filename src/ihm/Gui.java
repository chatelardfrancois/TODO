package ihm;

import bll.BLLException;
import bll.TodoManager;
import bo.Todo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class Gui extends JFrame{
    private JPanel panneauPrincipal, panneauTodos, panneauModification;
    private JLabel lblDate, lblModification, lblListeVide, lblListePleine;
    private JButton btnAjouter, btnValider, btnModifier, btnSupprimer, btnRetour, btnTodos1, btnTodos2, btnTodos3, btnTodos4, btnTodos5;
    private JTextField txtTodo;
    private List<Todo> liste=null;
    private final GridBagConstraints gbc = new GridBagConstraints();
    private Todo todoAAfficher;

    //TODO Amelioratios graphiques et tests unitaires

    public Gui()  {
        this.setTitle("Mes 5 TODO avant 50 ans");
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
            panneauPrincipal.add(getLblListePleine(),gbc);
            getLblListePleine().setVisible(false);
            gbc.gridy=3;
            panneauPrincipal.add(getBtnAjouter(), gbc);
            gbc.gridy=4;
            panneauPrincipal.add(getPanneauTodos(),gbc);


        }
        return panneauPrincipal;
    }

    private JLabel getlblModification() {
        if(lblModification==null){
            lblModification=new JLabel("Modification de la TODO");
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
            btnRetour.addActionListener(e -> {
                btnAjouter.setVisible(true);
                panneauTodos.setVisible(true);
                lblDate.setVisible(true);
                lblModification.setVisible(false);
                txtTodo.setText(null);
                panneauModification.setVisible(false);
            });
        }
        return btnRetour;
    }

    private JButton getBtnValider() {
        if(btnValider==null){
            btnValider= new JButton("Valider");
            btnValider.addActionListener(e -> {
                int input = JOptionPane.showConfirmDialog(null,
                        "Vous ??tes sur le point de valider la t??che, ??tes vous s??r de l'avoir termin??e ?", "Honnetement...", JOptionPane.OK_CANCEL_OPTION);
                if(input==0){
                    TodoManager tm = TodoManager.getInstance();
                    try {
                        tm.updateTodoReussi(todoAAfficher);
                        btnValider.setEnabled(false);
                        afficherTodos();
                    } catch (BLLException bllException) {
                        bllException.printStackTrace();
                    }
                }
            });

        }
        return btnValider;
    }

    private JButton getBtnModifier() {
        if(btnModifier==null){
            btnModifier= new JButton("Modifier");
            btnModifier.addActionListener(e -> {
                TodoManager tm = TodoManager.getInstance();
                try {
                    tm.updateTexteTodo(txtTodo.getText(), todoAAfficher);
                    afficherTodos();
                    btnRetour.doClick();
                } catch (BLLException bllException) {
                    bllException.printStackTrace();
                }
            });
        }
        return btnModifier;
    }

    private JButton getBtnSupprimer() {
        if(btnSupprimer==null){
            btnSupprimer= new JButton("Supprimer");
            btnSupprimer.addActionListener(e -> {
                TodoManager tm = TodoManager.getInstance();
                try {
                    tm.deleteTodo(todoAAfficher);
                    afficherTodos();
                    btnRetour.doClick();
                    getBtnAjouter().setEnabled(true);
                } catch (BLLException bllException) {
                    bllException.printStackTrace();
                }
            });
        }
        //TODO event bouton supprimer et requete SQL Delete
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
            lblListeVide= new JLabel("La liste des TODO est vide");
        }
        return lblListeVide;
    }

    public JLabel getLblListePleine(){
        if(lblListePleine==null){
            lblListePleine= new JLabel("La liste des TODO ?? faire est pleine");
            lblListePleine.setForeground(new Color(0xFF0303));
        }
        return lblListePleine;
    }

    public JButton getBtnTodos1(){
        if(btnTodos1==null){
            if(getListe().get(0).getReussi()==null){
                btnTodos1= new JButton(String.format("%s - En cours", getListe().get(0).getTexte()));
            } else {
                btnTodos1= new JButton(String.format("%s - Reussi le %S", getListe().get(0).getTexte(), getListe().get(0).getReussi().toString()));
            }
            btnTodos1.addActionListener(e -> {
                btnAjouter.setVisible(false);
                panneauTodos.setVisible(false);
                lblDate.setVisible(false);
                lblModification.setVisible(true);
                todoAAfficher= getListe().get(0);
                txtTodo.setText(todoAAfficher.getTexte());
                panneauModification.setVisible(true);
                btnValider.setEnabled(todoAAfficher.getReussi() == null);

            });
        }
        return btnTodos1;
    }

    public JButton getBtnTodos2(){
        if(btnTodos2==null){
            if(getListe().get(1).getReussi()==null){
                btnTodos2= new JButton(String.format("%s - En cours", getListe().get(1).getTexte()));
            } else {
                btnTodos2= new JButton(String.format("%s - Reussi le %S", getListe().get(1).getTexte(), getListe().get(1).getReussi().toString()));
            }
            btnTodos2.addActionListener(e -> {
                btnAjouter.setVisible(false);
                panneauTodos.setVisible(false);
                lblDate.setVisible(false);
                lblModification.setVisible(true);
                todoAAfficher= getListe().get(1);
                txtTodo.setText(todoAAfficher.getTexte());
                panneauModification.setVisible(true);
                btnValider.setEnabled(todoAAfficher.getReussi() == null);

            });
        }

        return btnTodos2;
    }

    public JButton getBtnTodos3(){
        if(btnTodos3==null){
            if(getListe().get(2).getReussi()==null){
                btnTodos3= new JButton(String.format("%s - En cours", getListe().get(2).getTexte()));
            } else {
                btnTodos3= new JButton(String.format("%s - Reussi le %S", getListe().get(2).getTexte(), getListe().get(2).getReussi().toString()));
            }
            btnTodos3.addActionListener(e -> {
                btnAjouter.setVisible(false);
                panneauTodos.setVisible(false);
                lblDate.setVisible(false);
                lblModification.setVisible(true);
                todoAAfficher= getListe().get(2);
                txtTodo.setText(todoAAfficher.getTexte());
                panneauModification.setVisible(true);
                btnValider.setEnabled(todoAAfficher.getReussi() == null);

            });
        }
        return btnTodos3;
    }

    public JButton getBtnTodos4(){
        if(btnTodos4==null){
            if(getListe().get(3).getReussi()==null){
                btnTodos4= new JButton(String.format("%s - En cours", getListe().get(3).getTexte()));
            } else {
                btnTodos4= new JButton(String.format("%s - Reussi le %S", getListe().get(3).getTexte(), getListe().get(3).getReussi().toString()));
            }
            btnTodos4.addActionListener(e -> {
                btnAjouter.setVisible(false);
                panneauTodos.setVisible(false);
                lblDate.setVisible(false);
                lblModification.setVisible(true);
                todoAAfficher= getListe().get(3);
                txtTodo.setText(todoAAfficher.getTexte());
                panneauModification.setVisible(true);
                btnValider.setEnabled(todoAAfficher.getReussi() == null);

            });
        }
        return btnTodos4;
    }

    public JButton getBtnTodos5(){
        if(btnTodos5==null){
            if(getListe().get(4).getReussi()==null){
                btnTodos5= new JButton(String.format("%s - En cours", getListe().get(4).getTexte()));
            } else {
                btnTodos5= new JButton(String.format("%s - Reussi le %S", getListe().get(4).getTexte(), getListe().get(4).getReussi().toString()));
            }
            btnTodos5.addActionListener(e -> {
                btnAjouter.setVisible(false);
                panneauTodos.setVisible(false);
                lblDate.setVisible(false);
                lblModification.setVisible(true);
                todoAAfficher= getListe().get(4);
                txtTodo.setText(todoAAfficher.getTexte());
                panneauModification.setVisible(true);
                btnValider.setEnabled(todoAAfficher.getReussi() == null);

            });
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
            // Quand je clique sur le bouton ajoute en base de donn??e le todoo et mets a jour le panel
            btnAjouter.addActionListener(e -> {
                TodoManager tm = TodoManager.getInstance();
                try {
                    tm.addTodo(txtTodo.getText());

                    afficherTodos();

                    txtTodo.setText(null);

                } catch (BLLException bllException) {
                    bllException.printStackTrace();
                }
            });
        }
        return btnAjouter;
    }

    public void afficherTodos(){
        panneauTodos.removeAll();
        btnTodos1=null;
        btnTodos2=null;
        btnTodos3=null;
        btnTodos4=null;
        btnTodos5=null;
        getLblListePleine().setVisible(false);
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
                getBtnAjouter().setEnabled(false);
                getLblListePleine().setVisible(true);
            }
            default -> {
                this.gbc.gridy=0;
                panneauTodos.add(getLblListeVide());
            }
        }
    }




}
