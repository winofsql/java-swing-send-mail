package action;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import javax.mail.*;

public class AppWindow extends BaseWindow {

    private JPanel jContentPane = null;
    private JPanel jContentPane2 = null;
    private JButton jButton = null;
    public JTextField jText = null;
    public JTextField jText2 = null;
    public JTextField jText3 = null;
    public JPasswordField jText4 = null;
    public JTextField jText5 = null;
    public JTextField jText6 = null;
    public JTextField jText7 = null;

    public JTextArea jText8 = null;
    public JScrollPane textArea = null;

    public int mainWidth = 600;
    public int mainHeight = 800;
    public String titleString = "Swing メール送信用簡易入力サンプル( GridLayout )";

    // *****************************************************
    // ボタン作成とクリックイベント
    // *****************************************************
    private JButton getJButton() {
        if (jButton == null) {
            jButton = new JButton();
            jButton.setText("実行");
            jButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {

                    System.out.println("ボタンがクリックされました");

                    try {
                        // ボタン処理用クラスの呼び出し
                        Action.testProc(AppWindow.this);

                    }
                    catch ( Exception ex ) {
                        ex.printStackTrace();
                    }

                    AppWindow.this.MsgOk("ボタンがクリックされました");

                }
            });
        }
        return jButton;
    }

    // *****************************************************
    // コンストラクタ
    // *****************************************************
    public AppWindow() {
        super();
        initialize();
    }

    // *****************************************************
    // 初期処理
    // *****************************************************
    private void initialize() {
        // ウインドウサイズの決定
        this.setSize(mainWidth, mainHeight);

        // ウインドウ位置の変更
        centerWindow();

        // パネルを適用
        this.setContentPane(getJContentPane());

        // タイトルセット
        this.setTitle(titleString);

        // カレントディレクトリの表示
        File cur = new File("");
        System.out.println(cur.getAbsolutePath());
    }

    // *****************************************************
    // 画面( パネル作成 )
    // *****************************************************
    private JPanel getJContentPane() {
        if (jContentPane == null) {

            jContentPane2 = new JPanel();
            jContentPane2.setLayout(new GridLayout(2,1));
            jContentPane2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            jContentPane = new JPanel();
            jContentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            GridLayout layout = new GridLayout(13,1);
            layout.setVgap(4);  //垂直方向の間隔
            jContentPane.setLayout(layout);

            jContentPane.add(new JLabel("サーバ"));

            // 入力フィールド
            jText = new JTextField();
            jText.setText("smtp.lolipop.jp");
            jContentPane.add(jText);
            // 入力フィールド2
            jText2 = new JTextField();
            jText2.setText("465");
            jContentPane.add(jText2);

            jContentPane.add(new JLabel("アカウント"));

            jText3 = new JTextField();
            jContentPane.add(jText3);
            jText4 = new JPasswordField();
            jContentPane.add(jText4);

            jContentPane.add(new JLabel("宛先"));
            jText5 = new JTextField();
            jContentPane.add(jText5);

            jContentPane.add(new JLabel("件名"));
            jText6 = new JTextField();
            jContentPane.add(jText6);

            jContentPane.add(new JLabel("差出人"));
            jText7 = new JTextField();
            jContentPane.add(jText7);

            jContentPane.add(getJButton());

            jContentPane2.add(jContentPane);

            jText8 = new JTextArea();
            textArea = new JScrollPane(jText8);
            jContentPane2.add(textArea);

        }


        return jContentPane2;
    }

    // ***********************************************
    // 認証用のプライベートクラス
    // ***********************************************
    public class SimpleAuthenticator extends Authenticator {
    
        private String user_string = null;
        private String pass_string = null;
    
        public SimpleAuthenticator( String user_s, String pass_s ) {
            super();
            user_string = user_s;
            pass_string = pass_s;
        }
    
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication( this.user_string, this.pass_string );
        }
    }    
}
