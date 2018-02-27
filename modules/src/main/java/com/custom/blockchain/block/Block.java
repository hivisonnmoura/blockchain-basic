package com.custom.blockchain.block;

import java.util.Date;

import com.custom.blockchain.util.DigestUtil;

public class Block {

	public String hash;
	public String previousHash;
	private String data;
	private long timeStamp;
	private int nonce;

	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
	}

	public String calculateHash() {
		String calculatedhash = DigestUtil
				.applySha256(previousHash + Long.toString(timeStamp) + Integer.toString(nonce) + data);
		return calculatedhash;
	}

	public void mineBlock(int difficulty) {
		long currentTime = System.currentTimeMillis();
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (hash == null || !hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined!!! : " + hash);
		System.out.println("Mining Time: " + (System.currentTimeMillis() - currentTime));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + nonce;
		result = prime * result + ((previousHash == null) ? 0 : previousHash.hashCode());
		result = prime * result + (int) (timeStamp ^ (timeStamp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Block other = (Block) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (nonce != other.nonce)
			return false;
		if (previousHash == null) {
			if (other.previousHash != null)
				return false;
		} else if (!previousHash.equals(other.previousHash))
			return false;
		if (timeStamp != other.timeStamp)
			return false;
		return true;
	}

}
