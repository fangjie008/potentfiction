package com.tiexue.potentfiction.service;

public interface IWxCallbackService {
	Boolean checkSignature(String signature, String timestamp, String nonce);
}
