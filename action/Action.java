package action;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import action.AppWindow.*;

//*****************************************************
// サンプルテストクラス
//*****************************************************
public class Action {

    public static void testProc(AppWindow form) {

        System.out.println("処理開始");
        // サーバー
        System.out.println(form.jText.getText());
        // ポート
        System.out.println(form.jText2.getText());
        // アカウント
        System.out.println(form.jText3.getText());
        // パスワード
        System.out.println(new String( form.jText4.getPassword()) );
        // 宛先
        System.out.println(form.jText5.getText());
        // 件名
        System.out.println(form.jText6.getText());
        // 差出人
        System.out.println(form.jText7.getText());
        // 本文
        System.out.println(form.jText8.getText());

        // *******************************************
        // プロパティオブジェクトを作成
        // プロパティオブジェクトは、
        // extends Hashtable(連想配列)
        // *******************************************
        Properties props = new Properties();
    
        // *******************************************
        // ( Gmail では、安全性の低いアプリのアクセスを有効にする必要があります )
        // ▼ G Suite では、安全性の低いアプリのアクセスは使用できません
        // https://gsuiteupdates-ja.googleblog.com/2019/12/g-suite_24.html
        // *******************************************
        props.put("mail.smtp.host",form.jText.getText());	// ロリポップ
        props.put("mail.smtp.auth", "true" );	// SMTP 認証を行う

        // ▼ 465( SSL )
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.port", form.jText2.getText());

        // *******************************************
        // メール用のセッションを作成
        // *******************************************
        SimpleAuthenticator sa = form.new SimpleAuthenticator( form.jText3.getText(), new String( form.jText4.getPassword() )  );
        Session MailSession = Session.getInstance( props, sa );
    
        try {
    
            // *******************************************
            // メール用のメッセージオブジェクトを作成
            // *******************************************
            MimeMessage msg = new MimeMessage(MailSession);
    
            // *******************************************
            // 宛先
            // *******************************************
            msg.setRecipients(
                Message.RecipientType.TO, String.format("%s <%s>",
                MimeUtility.encodeText(
                    "あなた",
                    "iso-2022-jp",
                    "B"
                ), form.jText5.getText() )
            );
    
            // *******************************************
            // 送信者
            // *******************************************
            msg.setFrom(
                new InternetAddress( String.format("%s <%s>",
                MimeUtility.encodeText(
                    "わたし",
                    "iso-2022-jp",
                    "B"
                ), form.jText7.getText() )  )
            );
    
            // *******************************************
            // 件名
            // *******************************************
            msg.setSubject(
                MimeUtility.encodeText(
                    form.jText6.getText(),
                    "iso-2022-jp",
                    "B"
                )
            );
    
            // *******************************************
            // 本文
            // *******************************************
            msg.setContent(
                form.jText8.getText(),
                "text/plain; charset=\"iso-2022-jp\""
            );
    
            // *******************************************
            // 送信
            // *******************************************
            Transport.send( msg );
    
        }
        catch (Exception e) {
            System.out.println( e.getMessage() );
            System.out.println("送信エラー");
        }

        System.out.println("処理終了");

    }

}
