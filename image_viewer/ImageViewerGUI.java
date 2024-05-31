import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;


public class ImageViewerGUI extends JFrame implements ActionListener{
    JButton selectFileButton = new JButton("Choose File");
    JButton showImageButton = new JButton("Show Image");
    JButton resizeButton = new JButton("Resize");
    JButton grayscaleButton = new JButton("Grayscale");
    JButton brightnessButton = new JButton("Brightness");
    JButton closeButton = new JButton("Exit");
    JButton showResizeButton = new JButton("Result");
    JButton showBrightnessButton = new JButton("Result");
    JLabel picturelabel=new JLabel();
    JButton backButton = new JButton("back");
    JTextField widthTextField=new JTextField();
    JTextField heightTextField=new JTextField();
    JTextField brightnessTextField = new JTextField();
    String filePath = "/home/pariya/photo";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;


    ImageViewerGUI(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);
        this.setResizable(false);

        mainPanel();
        fileChooser.setFileFilter(new FileNameExtensionFilter("JPG & GIF Images","jpg","gif"));
        addActionEvent();
    }

    public void mainPanel(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);


        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(200, 100, 300, 100);
        buttonsPanel.setLayout(new GridLayout(3, 2));


        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        mainPanel.add(buttonsPanel);

        this.add(mainPanel);
    }

    public void resizePanel(){
        JPanel resizePanel = new JPanel();
        resizePanel.setLayout(null);
        resizePanel.setBounds(0,0,700,300);

        heightTextField.setBounds(300,70,170,50);
        widthTextField.setBounds(300,140,170,50);
        resizePanel.add(heightTextField);
        resizePanel.add(widthTextField);

        JLabel heightlabel= new JLabel("Enter height:");
        JLabel widthlabel= new JLabel("Enter width:");
        heightlabel.setBounds(200,70,100,50);
        widthlabel.setBounds(200,140,100,50);
        resizePanel.add(heightlabel);
        resizePanel.add(widthlabel);

        showResizeButton.setBounds(580,220,90,30);
        resizePanel.add(showResizeButton);
        backButton.setBounds(20,220,90,30);
        resizePanel.add(backButton);

        this.getContentPane().removeAll();
        this.add(resizePanel);
        this.repaint();
        this.revalidate();
    }
    public void brightnessPanel(){


        JPanel brightnessPanel = new JPanel();

        brightnessPanel.setLayout(null);
        brightnessPanel.setBounds(0,0,700,300);

        JLabel brightnesslabel=new JLabel("Enter f");
        JLabel brightnesslabel2=new JLabel("must be between 0 and 1");
        brightnesslabel.setBounds(50,60,100,100);
        brightnesslabel2.setBounds(50,80,200,100);
        brightnessTextField.setBounds(250,100,200,50);
        brightnessPanel.add(brightnessTextField);
        brightnessPanel.add(brightnesslabel);
        brightnessPanel.add(brightnesslabel2);

        showBrightnessButton.setBounds(580,220,90,30);
        brightnessPanel.add(showBrightnessButton);
        backButton.setBounds(20,220,90,30);
        brightnessPanel.add(backButton);




        this.getContentPane().removeAll();
        this.add(brightnessPanel);
        this.repaint();
        this.revalidate();

    }

    public void chooseFileImage(int option){

        option = fileChooser.showOpenDialog(ImageViewerGUI.this);

    }
    public void showOriginalImage(int option){



        if (option == JFileChooser.APPROVE_OPTION) ;{
            file = fileChooser.getSelectedFile();
            if (file==null){
                err("choose your file first");
            }
            else {
                JFrame tempFrame = new JFrame();
                JPanel tempPanel = new JPanel();
                JPanel tempmainPanel = new JPanel();


                tempmainPanel.setLayout(new BorderLayout());
                tempmainPanel.setBorder(new EmptyBorder(20,20,20,20));

                tempPanel.add(picturelabel);


                tempFrame.add(tempmainPanel, BorderLayout.NORTH);
                tempFrame.setSize(900,900);
                tempFrame.setDefaultCloseOperation(3);
                tempFrame.setVisible(true);

                tempmainPanel.add(tempPanel, BorderLayout.CENTER);

                ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT));
                picturelabel.setIcon(imageIcon);
                tempPanel.setSize(1800, 1000);
                tempFrame.setTitle("Image Viewer");
                tempFrame.setSize(1800, 1000);
                tempFrame.setVisible(true);
                tempFrame.setResizable(true);
                tempFrame.add(tempPanel);
            }
        }


    }

    public void grayScaleImage(int option) throws IOException {




        file = fileChooser.getSelectedFile();
        if (file==null) {
            err("choose your file first");
        }
        else{
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JPanel tempmainPanel = new JPanel();


            tempmainPanel.setLayout(new BorderLayout());
            tempmainPanel.setBorder(new EmptyBorder(20,20,20,20));

            tempPanel.add(picturelabel);


            tempFrame.add(tempmainPanel, BorderLayout.NORTH);
            tempFrame.setSize(900,900);
            tempFrame.setDefaultCloseOperation(3);
            tempFrame.setVisible(true);

            tempmainPanel.add(tempPanel, BorderLayout.CENTER);

            BufferedImage gray = ImageIO.read(file);
            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
            ColorConvertOp op = new ColorConvertOp(cs, null);

            BufferedImage image = op.filter(gray, null);
            ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(800,600,Image.SCALE_DEFAULT));

            picturelabel.setIcon(imageIcon);
            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        }


    }
    public void showResizeImage(int w, int h){



        file = fileChooser.getSelectedFile();
        if (file==null){
            err("choose your file first");
        }
        else {
            JFrame tempFrame = new JFrame();
            JPanel tempPanel = new JPanel();
            JPanel tempmainPanel = new JPanel();

            tempmainPanel.setLayout(new BorderLayout());
            tempmainPanel.setBorder(new EmptyBorder(20,20,20,20));

            tempPanel.add(picturelabel);


            tempFrame.add(tempmainPanel, BorderLayout.NORTH);
            tempFrame.setSize(900,900);
            tempFrame.setDefaultCloseOperation(3);
            tempFrame.setVisible(true);

            tempmainPanel.add(tempPanel, BorderLayout.CENTER);

            ImageIcon imageIcon = new ImageIcon(new ImageIcon(String.valueOf(file)).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
            picturelabel.setIcon(imageIcon);


            tempPanel.setSize(1800, 1000);
            tempFrame.setTitle("Image Viewer");
            tempFrame.setSize(1800, 1000);
            tempFrame.setVisible(true);
            tempFrame.setResizable(true);
            tempFrame.add(tempPanel);
        }
    }
    public void showBrightnessImage(float f) throws IOException {



            file = fileChooser.getSelectedFile();
            if (file==null){
                err("choose your file first");
            }

            else {
                JFrame tempFrame = new JFrame();
                JPanel tempPanel = new JPanel();
                JPanel tempmainPanel = new JPanel();


                tempmainPanel.setLayout(new BorderLayout());
                tempmainPanel.setBorder(new EmptyBorder(20,20,20,20));

                tempPanel.add(picturelabel);


                tempFrame.add(tempmainPanel, BorderLayout.NORTH);
                tempFrame.setSize(900,900);
                tempFrame.setDefaultCloseOperation(3);


                tempmainPanel.add(tempPanel, BorderLayout.CENTER);

                tempFrame.setVisible(true);
                BufferedImage image = ImageIO.read(file);
                RescaleOp op = new RescaleOp(f, 0, null);
                image = op.filter(image, image);
                ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(800, 600, Image.SCALE_DEFAULT));


                picturelabel.setIcon(imageIcon);
                tempPanel.setSize(1800, 1000);
                tempFrame.setTitle("Image Viewer");
                tempFrame.setSize(1800, 1000);
                tempFrame.setVisible(true);
                tempFrame.setResizable(true);
                tempFrame.add(tempPanel);
            }






    }
    public void err(String str){

        JPanel errpanel = new JPanel();

        errpanel.setLayout(null);
        errpanel.setBounds(0,0,700,300);

        JLabel brightnesslabel=new JLabel(str);
        brightnesslabel.setBounds(200,100,300,100);

        errpanel.add(brightnesslabel);
        backButton.setBounds(20,220,90,30);
        errpanel.add(backButton);

        this.getContentPane().removeAll();
        this.add(errpanel);
        this.repaint();
        this.revalidate();

    }
    public void addActionEvent() {

        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        resizeButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        closeButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);
        showResizeButton.addActionListener(this);
        backButton.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        int option=0;
        if(e.getSource()==resizeButton){
            resizePanel();

        }else if(e.getSource()== showImageButton){

            try {
                showOriginalImage(option);
            }
            catch (Exception ex){
                err("choose your file first");
            }

        }else if(e.getSource()==grayscaleButton){
            try{
                grayScaleImage(option);
            }
            catch (Exception ex){
                err("sth is wrong");
            }


        }else if(e.getSource()== showResizeButton){
            String strh=heightTextField.getText();
            String strw=widthTextField.getText();

            try {
                h = Integer.parseInt(strh);
                w = Integer.parseInt(strw);
                showResizeImage(w,h);
            }
            catch(Exception ex){
                err("sth is wrong with your numbers");
            }


        }else if(e.getSource()==brightnessButton){

            brightnessPanel();

        }else if(e.getSource()== showBrightnessButton){
            String str=brightnessTextField.getText();

            try {
                brightenFactor = Float.parseFloat(str);
                if (brightenFactor>1 || brightenFactor<0){
                    err("sth is wrong with your f");
                }
                else{
                    showBrightnessImage(brightenFactor);
                }

            }
            catch(Exception ex){
                err("sth is wrong with your f");
            }

        }else if(e.getSource()== selectFileButton){
            chooseFileImage(option);

        }else if(e.getSource()==closeButton){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        else if(e.getSource()==  backButton){
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}