package com.yqc.proxyserver.cert;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * @author yangqc
 */
public class CertUtil {

    public static SslContext createSslContext() {
        try {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            SslContext sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
            return sslCtx;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private KeyStore getKeyStore() {
        try {
            FileInputStream inputStream = new FileInputStream(new File(""));
            KeyStore keyStore = KeyStore.getInstance("SSL");
            keyStore.load(inputStream, "123456".toCharArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
