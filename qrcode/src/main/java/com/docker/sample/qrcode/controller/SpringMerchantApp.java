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
package com.docker.sample.qrcode.controller;


import java.util.UUID;

import com.docker.sample.qrcode.model.Merchant;
import com.docker.sample.qrcode.service.impl.QRImageServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
@ComponentScan({"com.docker.sample"})
@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST})
public class SpringMerchantApp 
{

	public static final String QRCODE_ENDPOINT = "/qrcode";
	public static final long THIRTY_MINUTES = 1800000;


	
	@Autowired
	QRImageServiceImpl imageService;



	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/generateQRCode", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getMerchantQRCode(@RequestBody @Valid @NotNull Merchant merchant) throws Exception {
		try 
		{

				Merchant mer = new Merchant(merchant);
				String uuid = UUID.randomUUID().toString();
				System.out.println("-----"+uuid);
				return ResponseEntity.ok().body(imageService.generateMerchantQRCode(merchant, 256, 256));

		} 
		catch (Exception ex) 
		{
			throw new Exception("Error while generating QR code image.", ex);
		}
	}


	
}

	
