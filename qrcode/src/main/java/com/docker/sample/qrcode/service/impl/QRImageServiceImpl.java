/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.docker.sample.qrcode.service.impl;

import java.io.*;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.docker.sample.qrcode.model.Merchant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Service

public class QRImageServiceImpl  {


	private static final Logger LOGGER = LoggerFactory.getLogger(QRImageServiceImpl.class);

	

	// test
	public byte[] generateMerchantQRCode(Merchant merchant, int width, int height) throws WriterException, IOException {

		
		merchant.toString();

		//BitMatrix a = new MultiFormatReader(merchant.toString(),BarcodeFormat.QR_CODE, width, height);
		LOGGER.info("Will generate image  text=[{}], width=[{}], height=[{}]", "test", width, height);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		System.out.println(merchant.toString());
		BitMatrix matrix = new MultiFormatWriter().encode(merchant.toString(), BarcodeFormat.QR_CODE, width, height);
		MatrixToImageWriter.writeToStream(matrix, MediaType.IMAGE_PNG.getSubtype(), baos, new MatrixToImageConfig());
		return baos.toByteArray();
	}

	
	

}

