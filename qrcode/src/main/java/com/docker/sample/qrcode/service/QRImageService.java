package com.docker.sample.qrcode.service;

import com.docker.sample.qrcode.model.Merchant;
import com.google.zxing.WriterException;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;

public interface QRImageService {
    /**
     * Generate QR Code
     *
     * @param Merchant
     * @param width
     * @param height
     * @return
  */

    byte[] generateMerchantQRCode(Merchant merchant, int width, int height) throws WriterException, IOException;
}